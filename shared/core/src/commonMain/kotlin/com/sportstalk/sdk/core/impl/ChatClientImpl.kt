package com.sportstalk.sdk.core.impl

import com.sportstalk.sdk.core.ConfigUtils
import com.sportstalk.sdk.core.ServiceFactory
import com.sportstalk.sdk.core.api.ChatClient
import com.sportstalk.sdk.model.ApiResponse
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.SportsTalkException
import com.sportstalk.sdk.model.chat.*
import com.sportstalk.sdk.model.chat.moderation.ApproveMessageRequest
import com.sportstalk.sdk.model.chat.moderation.ListMessagesNeedingModerationResponse
import com.sportstalk.sdk.model.chat.moderation.PurgeUserMessagesRequest
import com.sportstalk.sdk.model.user.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.datetime.Clock
import kotlin.math.abs

class ChatClientImpl
internal constructor(
    private val config: ClientConfig,
    private val client: HttpClient = ServiceFactory.RestApi.getKtorClientInstance(config),
) : ChatClient {

    private val appId: String = config.appId
    private val endpoint: String = ConfigUtils.validateEndpoint(config.endpoint)

    private val ioDispatcher by lazy { newFixedThreadPoolContext(20, "ChatService-IOCoroutineDispatcher") }

    // Current User state tracking
    private var _currentUser: User? = null
    override var currentUser: User?
        get() = _currentUser
        set(value) {
            _currentUser = value
        }

    // Room state tracking
    private var _currentRoom: ChatRoom? = null
    override var currentRoom: ChatRoom?
        get() = _currentRoom
        set(value) {
            _currentRoom = value
        }

    // Default image banner URL
    private var _defaultImageBannerUrl: String? = null
    override var defaultImageBannerUrl: String?
        get() = _defaultImageBannerUrl
        set(value) {
            _defaultImageBannerUrl = value
        }

    // Throttle message body for execute chat command
    private var _lastExecuteCommandMessage: String? = null

    // Throttle timestamp for execute chat command
    private var _lastExecuteCommandTimestamp: Long = 0L

    /**
     * Only used if event smoothing is enabled.
     * Keeps a list of messages we already rendered so we can ignore them in getUpdates
     */
    override var preRenderedMessages: MutableSet<String> = mutableSetOf()

    private var _chatEventsEmitter = MutableSharedFlow<List<ChatEvent>>()
    override var chatEventsEmitter: Flow<List<ChatEvent>>
        get() = _chatEventsEmitter.asSharedFlow()
        set(_) {}

    private val roomSubscriptions: MutableSet<String> = mutableSetOf()
    override fun roomSubscriptions(): Set<String> = roomSubscriptions

    private val chatRoomEventUpdateCursor: HashMap<String, String> = hashMapOf()
    override fun getChatRoomEventUpdateCursor(forRoomId: String): String? =
        if (chatRoomEventUpdateCursor.contains(forRoomId)) chatRoomEventUpdateCursor[forRoomId]!!
        else null

    override fun setChatRoomEventUpdateCursor(forRoomId: String, cursor: String) {
        chatRoomEventUpdateCursor[forRoomId] = cursor
    }

    override fun clearChatRoomEventUpdateCursor(fromRoomId: String) {
        chatRoomEventUpdateCursor.remove(fromRoomId)
    }

    override fun startListeningToChatUpdates(forRoomId: String) {
        roomSubscriptions.add(forRoomId)
    }

    override fun allEventUpdates(
        chatRoomId: String,
        frequency: Long,
        limit: Int?,
        smoothEventUpdates: Boolean,
        eventSpacingMs: Long,
        maxEventBufferSize: Int
    ): Flow<List<ChatEvent>> {
        // Frequency check
        if (frequency < 1000L) {
            throw SportsTalkException(
                code = 500,
                err = IllegalArgumentException("Frequency must be equal to or greater than 1000ms.")
            )
        }

        // Insanity check, event spacing delay must have a valid value.
        val delayEventSpacingMs = when {
            eventSpacingMs >= 0 -> eventSpacingMs
            else -> 100L
        }

        return merge(chatEventsEmitter,  // Execute Chat Command SPEECH event emitter
            flow<List<ChatEvent>> {
                do {
                    // Attempt operation call ONLY IF `startListeningToChatUpdates(roomId)` is called.
                    if (roomSubscriptions().contains(chatRoomId) && currentCoroutineContext().isActive) {
                        try {
                            // Perform GET UPDATES operation
                            val response = withContext(ioDispatcher) {
                                getUpdates(chatRoomId = chatRoomId, limit = limit,
                                    // Apply event cursor
                                    cursor = getChatRoomEventUpdateCursor(chatRoomId)?.takeIf { it.isNotEmpty() })
                            }

                            // Emit response value
                            response.cursor?.takeIf { it.isNotEmpty() }?.let { cursor ->
                                setChatRoomEventUpdateCursor(chatRoomId, cursor)
                            } ?: run {
                                clearChatRoomEventUpdateCursor(chatRoomId)
                            }

                            val allEventUpdates = response.events.filterNot { ev ->
                                // We already rendered this on send.
                                val eventId = ev.id ?: ""
                                val alreadyPreRendered = preRenderedMessages.contains(eventId)
                                if (alreadyPreRendered) preRenderedMessages.remove(eventId)
                                alreadyPreRendered
                            }

                            emit(allEventUpdates)
                        } catch (err: SportsTalkException) {
                            err.printStackTrace()
                        } catch (err: CancellationException) {
                            err.printStackTrace()
                        }
                    } else {
                        // ELSE, Either event updates has NOT yet started or `stopEventUpdates()` has been explicitly invoked
                        break
                    }

                    delay(frequency)
                } while (true)
            },
            /*
            * Upon start listen to event updates, dispatch call to Touch Session API every 60 seconds to keep user session alive.
            * Add a flow that does NOT EMIT anything, but will just continuously dispatch call to Touch Session API.
            */
            callbackFlow<List<ChatEvent>> {
                do {
                    try {
                        this.ensureActive()
                        currentUser?.userid?.let { userid ->
                            if (this.isActive) {
                                withContext(ioDispatcher) {
                                    this.coroutineContext.ensureActive()
                                    if (this.coroutineContext.isActive) {
                                        touchSession(
                                            chatRoomId = chatRoomId, userId = userid
                                        )
                                    }
                                }
                            }
                        }
                    } catch (err: CancellationException) {
                        err.printStackTrace()
                    } catch (err: Throwable) {
                        err.printStackTrace()
                    } finally {
                        delay(60_000L)
                    }
                } while (true)
            })
            // Filter out shadowban events for shadowbanned user
            .map { events ->
                events.filterNot { ev ->
                    ev.shadowban == true && ev.userid != currentUser?.userid
                }
            }.flatMapMerge { allEventUpdates ->
                // If smoothing is enabled, render events with some spacing.
                // However, if we have a massive batch, we want to catch up, so we do not put spacing and just jump ahead.
                if (smoothEventUpdates && allEventUpdates.isNotEmpty() && allEventUpdates.size < maxEventBufferSize) {

                    // Emit spaced event updates(i.e. emit per batch list of chat events)
                    flow<List<ChatEvent>> {
                        for (chatEvent in allEventUpdates) {
                            // Emit each Chat Event Items
                            emit(listOf(chatEvent))
                            // Apply spaced delay for each chat event item being emitted
                            delay(delayEventSpacingMs)
                        }
                    }
                } else {
                    // Just emit all events as-is
                    flowOf(allEventUpdates)
                }
            }
    }

    override fun stopListeningToChatUpdates(forRoomId: String) {
        roomSubscriptions.remove(forRoomId)
    }

    override suspend fun createRoom(request: CreateChatRoomRequest): ChatRoom = try {
        val response = client.request(
            "${endpoint}${appId}/chat/rooms"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<ChatRoom>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun getRoomDetails(chatRoomId: String): ChatRoom = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}"
        ) {
            method = HttpMethod.Get
        }

        response.body<ApiResponse<ChatRoom>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun getRoomDetailsExtendedBatch(
        entityTypes: List<RoomDetailEntityType>, roomIds: List<String>, customIds: List<String>
    ): GetRoomDetailsExtendedBatchResponse = try {
        val response = client.request(
            "${endpoint}${appId}/chat/rooms/batch/details"
        ) {
            method = HttpMethod.Get

            entityTypes.forEach { parameter("entity", it.keyword) }
            roomIds.forEach { parameter("roomid", it) }
            customIds.forEach { parameter("customid", it) }
        }

        response.body<ApiResponse<GetRoomDetailsExtendedBatchResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun getRoomDetailsByCustomId(chatRoomCustomId: String): ChatRoom = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/roomsbycustomid/${chatRoomCustomId.encodeURLPath()}"
        ) {
            method = HttpMethod.Get
        }

        response.body<ApiResponse<ChatRoom>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun deleteRoom(chatRoomId: String): DeleteChatRoomResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}"
        ) {
            method = HttpMethod.Delete
        }

        response.body<ApiResponse<DeleteChatRoomResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun updateRoom(chatRoomId: String, request: UpdateChatRoomRequest): ChatRoom =
        try {
            val response = client.request(
                "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}"
            ) {
                method = HttpMethod.Post

                setBody(request)
            }

            response.body<ApiResponse<ChatRoom>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message, err = err
            )
        }

    override suspend fun touchSession(chatRoomId: String, userId: String): ChatSubscription = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/sessions/${userId.encodeURLPath()}/touch"
        ) {
            method = HttpMethod.Post
        }

        response.body<ApiResponse<ChatSubscription>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listRooms(limit: Int?, cursor: String?): ListRoomsResponse = try {
        val response = client.request(
            "${endpoint}${appId}/chat/rooms"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        response.body<ApiResponse<ListRoomsResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun joinRoom(
        chatRoomId: String, request: JoinChatRoomRequest
    ): JoinChatRoomResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/join"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<JoinChatRoomResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun joinRoom(chatRoomIdOrLabel: String): JoinChatRoomResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomIdOrLabel.encodeURLPath()}/join"
        ) {
            method = HttpMethod.Post
        }

        response.body<ApiResponse<JoinChatRoomResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun joinRoomByCustomId(
        chatRoomCustomId: String, request: JoinChatRoomRequest
    ): JoinChatRoomResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/roomsbycustomid/${chatRoomCustomId}/join"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<JoinChatRoomResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listRoomParticipants(
        chatRoomId: String, limit: Int?, cursor: String?
    ): ListChatRoomParticipantsResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/participants"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        response.body<ApiResponse<ListChatRoomParticipantsResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listUserSubscribedRooms(
        userid: String, limit: Int?, cursor: String?
    ): ListUserSubscribedRoomsResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/user/${userid.encodeURLPath()}/subscriptions"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        response.body<ApiResponse<ListUserSubscribedRoomsResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun exitRoom(chatRoomId: String, userId: String) {
        try {
            val response = client.request(
                "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/exit"
            ) {
                method = HttpMethod.Post
                setBody(
                    ExitChatRoomRequest(userid = userId)
                )
            }

            if (response.status.isSuccess()) {
                // Unset current user
                _currentUser = null
                // Unset currently active chat room
                _currentRoom = null
                // Reset execute command throttle
                _lastExecuteCommandMessage = null
                _lastExecuteCommandTimestamp = 0L

                // Remove internally stored event cursor
                clearChatRoomEventUpdateCursor(fromRoomId = chatRoomId)
                // Clear Pre-rendered events
                preRenderedMessages.clear()
            }
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message, err = err
            )
        }
    }

    override suspend fun getUpdates(
        chatRoomId: String, limit: Int?, cursor: String?
    ): GetUpdatesResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/updates"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        val responseBody = response.body<ApiResponse<GetUpdatesResponse?>>()
        val code = responseBody.code
        if(responseBody.data != null && code in 200..399) {
            val getUpdatesResponse = responseBody.data!!
            getUpdatesResponse.copy(
                events = getUpdatesResponse.events
                    // Filter out shadowban events for shadowbanned user
                    .filterNot { ev ->
                        ev.shadowban == true && ev.userid != currentUser?.userid
                    }
            )
        } else {
            val kind = responseBody.kind
            val message = responseBody.message
            throw SportsTalkException(
                kind = kind,
                message = message,
                code = code,
            )
        }

    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun messageIsReported(which: ChatEvent, userid: String): Boolean =
        which.reports.any { _report -> _report.userid == userid }

    override suspend fun messageIsReactedTo(
        which: ChatEvent, userid: String, reaction: String
    ): Boolean = if (which.replyto != null) {
        which.reactions.any { _reaction ->
            _reaction.type == reaction && _reaction.users.find { _usr -> _usr.userid == userid } != null
        } || messageIsReactedTo(which.replyto!!, userid, reaction)
    } else {
        which.reactions.any { _reaction ->
            _reaction.type == reaction && _reaction.users.find { _usr -> _usr.userid == userid } != null
        }
    }

    override suspend fun listPreviousEvents(
        chatRoomId: String, limit: Int?, cursor: String?
    ): ListEvents = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/listpreviousevents"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        val listPreviousEventsResponse = response.body<ApiResponse<ListEvents>>().data!!
        listPreviousEventsResponse.copy(
            events = listPreviousEventsResponse.events
                // Filter out shadowban events for shadowbanned user
                .filterNot { ev ->
                    ev.shadowban == true && ev.userid != currentUser?.userid
                }
        )
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun getEventById(chatRoomId: String, eventId: String): ChatEvent = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/events/${eventId.encodeURLPath()}"
        ) {
            method = HttpMethod.Get
        }

        response.body<ApiResponse<ChatEvent>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun reportUserInRoom(
        chatRoomId: String, userid: String, reporterid: String, reporttype: String
    ): ChatRoom = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/users/${userid.encodeURLPath()}/report"
        ) {
            method = HttpMethod.Post
            setBody(
                ReportUserInRoomRequest(
                    reporterid = reporterid,
                    reporttype = reporttype,
                )
            )
        }

        response.body<ApiResponse<ChatRoom>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listEventsHistory(
        chatRoomId: String, limit: Int?, cursor: String?
    ): ListEvents = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/listeventshistory"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        val listEventsHistoryResponse = response.body<ApiResponse<ListEvents>>().data!!
        listEventsHistoryResponse.copy(
            events = listEventsHistoryResponse.events
                // Filter out shadowban events for shadowbanned user
                .filterNot { ev ->
                    ev.shadowban == true && ev.userid != currentUser?.userid
                }
        )
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listEventsByType(
        chatRoomId: String, eventType: String, customtype: String?, limit: Int?, cursor: String?
    ): ListEvents = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/listeventsbytype"
        ) {
            method = HttpMethod.Get

            parameter("eventtype", eventType)
            customtype?.let { parameter("customtype", it) }
            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        val listEventsByType = response.body<ApiResponse<ListEvents>>().data!!
        listEventsByType.copy(
            events = listEventsByType.events
                // Filter out shadowban events for shadowbanned user
                .filterNot { ev ->
                    ev.shadowban == true && ev.userid != currentUser?.userid
                }
        )
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listEventsByTimestamp(
        chatRoomId: String, timestamp: Long, limitolder: Int?, limitnewer: Int?
    ): ListEventsByTimestamp = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/eventsbytimestamp/list/$timestamp"
        ) {
            method = HttpMethod.Get

            limitolder?.let { parameter("limitolder", it) }
            limitnewer?.let { parameter("limitnewer", it) }
        }

        val listEventsByTimestamp = response.body<ApiResponse<ListEventsByTimestamp>>().data!!
        listEventsByTimestamp.copy(
            events = listEventsByTimestamp.events
                // Filter out shadowban events for shadowbanned user
                .filterNot { ev ->
                    ev.shadowban == true && ev.userid != currentUser?.userid
                }
        )
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun executeChatCommand(
        chatRoomId: String, request: ExecuteChatCommandRequest
    ): ExecuteChatCommandResponse =
        if(_lastExecuteCommandMessage != request.command.trim()
            || abs(Clock.System.now().toEpochMilliseconds() - _lastExecuteCommandTimestamp) > DURATION_EXECUTE_COMMAND) {

            _lastExecuteCommandMessage = request.command.trim()
            _lastExecuteCommandTimestamp = Clock.System.now().toEpochMilliseconds()

            try {
                val response = client.request(
                    "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/command"
                ) {
                    method = HttpMethod.Post
                    setBody(request)
                }

                val responseBody = response.body<ApiResponse<ExecuteChatCommandResponse?>>()
                val code = responseBody.code
                if(responseBody.data != null && code in 200..399) {
                    responseBody.data!!
                        .also { execCommandResponse ->
                            // [Anti-flood Feature] Add to preRenderedMessages
                            execCommandResponse.speech?.let { chatEvent ->
                                // Emit/Trigger Event Update
                                _chatEventsEmitter.emit(listOf(chatEvent))
                                // Add to Pre-Rendered Messages
                                chatEvent.id?.let { id -> preRenderedMessages.add(id) }
                            }

                        }
                } else if(responseBody.kind != null && responseBody.message != null && code in 200..399) {
                    val kind = responseBody.kind
                    val message = responseBody.message
                    ExecuteChatCommandResponse(
                        kind = kind,
                        message = message,
                    )
                } else {
                    val kind = responseBody.kind
                    val message = responseBody.message
                    throw SportsTalkException(
                        kind = kind,
                        message = message,
                        code = code,
                    )
                }
            } catch (err: SportsTalkException) {
                // Bypass anti-flood feature if API or Internal error encountered
                _lastExecuteCommandMessage = null
                throw err
            } catch (err: Throwable) {
                // Bypass anti-flood feature if API or Internal error encountered
                _lastExecuteCommandMessage = null
                throw SportsTalkException(
                    message = err.message, err = err
                )
            }
        } else {
            throw SportsTalkException(
                code = 418,
                message = "418 - Not Allowed. Please wait to send this message again."
            )
        }

    override suspend fun sendThreadedReply(
        chatRoomId: String, replyTo: String, request: SendThreadedReplyRequest
    ): ChatEvent =
        if(_lastExecuteCommandMessage != request.body.trim()
            || abs(Clock.System.now().toEpochMilliseconds() - _lastExecuteCommandTimestamp) > DURATION_EXECUTE_COMMAND) {

            _lastExecuteCommandMessage = request.body.trim()
            _lastExecuteCommandTimestamp = Clock.System.now().toEpochMilliseconds()

            try {
                val response = client.request(
                    "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${replyTo.encodeURLPath()}/reply"
                ) {
                    method = HttpMethod.Post
                    setBody(request)
                }

                response.body<ApiResponse<ChatEvent>>().data!!
            } catch (err: SportsTalkException) {
                // Bypass anti-flood feature if API or Internal error encountered
                _lastExecuteCommandMessage = null
                throw err
            } catch (err: Throwable) {
                // Bypass anti-flood feature if API or Internal error encountered
                _lastExecuteCommandMessage = null
                throw SportsTalkException(
                    message = err.message, err = err
                )
            }
        } else {
            throw SportsTalkException(
                code = 418,
                message = "418 - Not Allowed. Please wait to send this message again."
            )
        }

    override suspend fun sendQuotedReply(
        chatRoomId: String, replyTo: String, request: SendQuotedReplyRequest
    ): ChatEvent =
        if(_lastExecuteCommandMessage != request.body.trim()
            || abs(Clock.System.now().toEpochMilliseconds() - _lastExecuteCommandTimestamp) > DURATION_EXECUTE_COMMAND) {

            _lastExecuteCommandMessage = request.body.trim()
            _lastExecuteCommandTimestamp = Clock.System.now().toEpochMilliseconds()

            try {
                val response = client.request(
                    "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${replyTo.encodeURLPath()}/quote"
                ) {
                    method = HttpMethod.Post
                    setBody(request)
                }

                response.body<ApiResponse<ChatEvent>>().data!!
            } catch (err: SportsTalkException) {
                // Bypass anti-flood feature if API or Internal error encountered
                _lastExecuteCommandMessage = null
                throw err
            } catch (err: Throwable) {
                // Bypass anti-flood feature if API or Internal error encountered
                _lastExecuteCommandMessage = null
                throw SportsTalkException(
                    message = err.message, err = err
                )
            }

        } else {
            throw SportsTalkException(
                code = 418,
                message = "418 - Not Allowed. Please wait to send this message again."
            )
        }

    override suspend fun listMessagesByUser(
        chatRoomId: String, userId: String, limit: Int?, cursor: String?
    ): ListMessagesByUser = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/messagesbyuser/${userId.encodeURLPath()}"
        ) {
            method = HttpMethod.Get

            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        response.body<ApiResponse<ListMessagesByUser>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun bounceUser(
        chatRoomId: String, request: BounceUserRequest
    ): BounceUserResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/bounce"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<BounceUserResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun searchEventHistory(request: SearchEventHistoryRequest): SearchEventHistoryResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}/chat/searchevents"
            ) {
                method = HttpMethod.Post
                setBody(request)
            }

            response.body<ApiResponse<SearchEventHistoryResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message, err = err
            )
        }

    override suspend fun updateChatMessage(
        chatRoomId: String, eventId: String, request: UpdateChatMessageRequest
    ): ChatEvent = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${eventId.encodeURLPath()}"
        ) {
            method = HttpMethod.Put
            setBody(request)
        }

        response.body<ApiResponse<ChatEvent>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun permanentlyDeleteEvent(
        chatRoomId: String, eventId: String, userid: String
    ): DeleteEventResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${eventId.encodeURLPath()}"
        ) {
            method = HttpMethod.Delete

            parameter("userid", userid)
        }

        response.body<ApiResponse<DeleteEventResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun flagEventLogicallyDeleted(
        chatRoomId: String,
        eventId: String,
        userid: String,
        deleted: Boolean,
        permanentifnoreplies: Boolean?
    ): DeleteEventResponse = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${eventId.encodeURLPath()}/setdeleted"
        ) {
            method = HttpMethod.Put

            parameter("userid", userid)
            parameter("deleted", deleted)
            permanentifnoreplies?.let { parameter("permanentifnoreplies", it) }
        }

        response.body<ApiResponse<DeleteEventResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun reportMessage(
        chatRoomId: String, eventId: String, request: ReportMessageRequest
    ): ChatEvent = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${eventId.encodeURLPath()}/report"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<ChatEvent>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun reactToEvent(
        chatRoomId: String, eventId: String, request: ReactToAMessageRequest
    ): ChatEvent = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}" + "/events/${eventId.encodeURLPath()}/react"
        ) {
            method = HttpMethod.Post
            setBody(request)
        }

        response.body<ApiResponse<ChatEvent>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun shadowBanUser(
        chatRoomId: String, userid: String, applyeffect: Boolean, expireseconds: Long?
    ): ChatRoom = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/shadowban"
        ) {
            method = HttpMethod.Post
            setBody(
                ShadowBanUserInRoomRequest(
                    userid = userid,
                    applyeffect = applyeffect,
                    expireseconds = expireseconds,
                )
            )
        }

        response.body<ApiResponse<ChatRoom>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun muteUser(
        chatRoomId: String, userid: String, applyeffect: Boolean, expireseconds: Long?
    ): ChatRoom = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/rooms/${chatRoomId.encodeURLPath()}/mute"
        ) {
            method = HttpMethod.Post
            setBody(
                MuteUserInRoomRequest(
                    userid = userid,
                    applyeffect = applyeffect,
                    expireseconds = expireseconds,
                )
            )
        }

        response.body<ApiResponse<ChatRoom>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun approveMessage(eventId: String, approve: Boolean): ChatEvent = try {
        val response = client.request(
            "${endpoint}${appId}" + "/chat/moderation/queues/events/${eventId.encodeURLPath()}/applydecision"
        ) {
            method = HttpMethod.Post
            setBody(
                ApproveMessageRequest(approve = approve)
            )
        }

        response.body<ApiResponse<ChatEvent>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun listMessagesNeedingModeration(
        roomId: String?, limit: Int?, cursor: String?
    ): ListMessagesNeedingModerationResponse = try {
        val response = client.request(
            "${endpoint}${appId}/chat/moderation/queues/events"
        ) {
            method = HttpMethod.Get

            roomId?.let { parameter("roomId", it) }
            limit?.let { parameter("limit", it) }
            cursor?.let { parameter("cursor", it) }
        }

        response.body<ApiResponse<ListMessagesNeedingModerationResponse>>().data!!
    } catch (err: SportsTalkException) {
        throw err
    } catch (err: Throwable) {
        throw SportsTalkException(
            message = err.message, err = err
        )
    }

    override suspend fun purgeUserMessages(chatRoomId: String, userId: String, byUserId: String) {
        try {
            val response = client.request(
                "${endpoint}${appId}/chat/rooms/${chatRoomId}/commands/purge"
            ) {
                method = HttpMethod.Post
                setBody(
                    PurgeUserMessagesRequest(
                        userid = userId,
                        byuserid = byUserId,
                    )
                )
            }
            val result = response.body<ApiResponse<Unit>>()
            val code = result.code ?: response.status.value
            if(!HttpStatusCode.fromValue(code).isSuccess()) {
                val kind = result.kind
                val message = result.message ?: response.status.description
                throw SportsTalkException(
                    kind = kind,
                    code = code,
                    message = message,
                )
            }
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message, err = err
            )
        }
    }

    companion object {
        private const val DURATION_EXECUTE_COMMAND = 20_000L
    }
}

internal fun <T> merge(vararg flows: Flow<T>): Flow<T> =
    flowOf(*flows).flattenMerge(concurrency = flows.size)
