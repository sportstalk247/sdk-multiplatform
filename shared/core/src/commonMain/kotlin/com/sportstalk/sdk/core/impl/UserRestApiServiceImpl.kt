package com.sportstalk.sdk.core.impl

import com.sportstalk.sdk.core.ConfigUtils
import com.sportstalk.sdk.core.service.UserService
import com.sportstalk.sdk.model.ApiResponse
import com.sportstalk.sdk.model.ClientConfig
import com.sportstalk.sdk.model.Kind
import com.sportstalk.sdk.model.SportsTalkException
import com.sportstalk.sdk.model.user.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class UserRestApiServiceImpl
internal constructor(
    private val config: ClientConfig,
    private val client: HttpClient,
) : UserService {

    private val appId: String = config.appId
    private val endpoint: String = ConfigUtils.validateEndpoint(config.endpoint)

    override suspend fun createOrUpdateUser(request: CreateUpdateUserRequest): User =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${request.userid.encodeURLPath()}"
            ) {
                method = HttpMethod.Post
                setBody(request)
            }

            response.body<ApiResponse<User>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun deleteUser(userId: String): DeleteUserResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}"
            ) {
                method = HttpMethod.Delete
            }

            response.body<ApiResponse<DeleteUserResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun getUserDetails(userId: String): User =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}"
            ) {
                method = HttpMethod.Get
            }

            response.body<ApiResponse<User>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun listUsers(limit: Int?, cursor: String?): ListUsersResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}/user/users"
            ) {
                method = HttpMethod.Get

                limit?.let { parameter("limit", it) }
                cursor?.let { parameter("cursor", it) }
            }

            response.body<ApiResponse<ListUsersResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun setBanStatus(userId: String, applyeffect: Boolean, expireseconds: Long?): User =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}/ban"
            ) {
                method = HttpMethod.Post
                setBody(
                    BanUserRequest(
                        applyeffect = applyeffect,
                        expireseconds = expireseconds,
                    )
                )
            }

            response.body<ApiResponse<User>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun searchUsers(
        handle: String?,
        name: String?,
        userid: String?,
        limit: Int?,
        cursor: String?
    ): ListUsersResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}/user/search"
            ) {
                method = HttpMethod.Post
                setBody(
                    SearchUsersRequest(
                        handle = handle,
                        name = name,
                        userid = userid,
                        limit = limit,
                        cursor = cursor
                    )
                )
            }

            response.body<ApiResponse<ListUsersResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun setShadowBanStatus(
        userId: String,
        applyeffect: Boolean,
        expireseconds: Long?
    ): User =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}/shadowban"
            ) {
                method = HttpMethod.Post
                setBody(
                    SetShadowBanStatusRequest(
                        applyeffect = applyeffect,
                        expireseconds = expireseconds
                    )
                )
            }

            response.body<ApiResponse<User>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun globallyPurgeUserContent(userId: String, byuserid: String): GloballyPurgeUserContentResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}/globalpurge"
            ) {
                method = HttpMethod.Post
                setBody(
                    GloballyPurgeUserContentRequest(byuserid = byuserid)
                )
            }

            GloballyPurgeUserContentResponse()
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun reportUser(userId: String, reporttype: String): ReportUserResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}/report"
            ) {
                method = HttpMethod.Post
                setBody(
                    ReportUserRequest(
                        userid = userId,
                        reporttype = reporttype,
                    )
                )
            }

            response.body<ApiResponse<ReportUserResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun listUserNotifications(userId: String, limit: Int, filterNotificationTypes: List<UserNotificationType>?, cursor: String?, includeread: Boolean?, filterChatRoomId: String?, filterChatRoomCustomId: String?): ListUserNotificationsResponse =
        try {
            val response = client.request(
                "${endpoint}${appId}" +
                        "/user/users/${userId.encodeURLPath()}" +
                        "/notification/listnotifications"
            ) {
                method = HttpMethod.Get
                parameter("limit", limit)
                filterNotificationTypes?.let { parameter("filterNotificationTypes", it) }
                cursor?.let { parameter("cursor", it) }
                includeread?.let { parameter("includeread", it) }
                filterChatRoomId?.let { parameter("filterChatRoomId", it) }
                filterChatRoomCustomId?.let { parameter("filterChatRoomCustomId", it) }
            }

            response.body<ApiResponse<ListUserNotificationsResponse>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun setUserNotificationAsRead(
        userId: String,
        notificationId: String,
        read: Boolean
    ): UserNotification =
        try {
            val response = client.request(
                "${endpoint}${appId}/user/users/${userId.encodeURLPath()}" +
                        "/notification/notifications/${notificationId.encodeURLPath()}/update"
            ) {
                method = HttpMethod.Put
                parameter("read", read)
            }

            response.body<ApiResponse<UserNotification>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun setUserNotificationAsReadByChatEvent(userId: String, chatEventId: String, read: Boolean): UserNotification =
        try {
            val response = client.request(
                "${endpoint}${appId}/user/users/${userId.encodeURLPath()}" +
                        "/notification/notificationsbyid/chateventid/${chatEventId.encodeURLPath()}/update"
            ) {
                method = HttpMethod.Put
                parameter("read", read)
            }

            response.body<ApiResponse<UserNotification>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun deleteUserNotification(userId: String, notificationId: String): UserNotification =
        try {
            val response = client.request("${endpoint}${appId}" +
                    "/user/users/${userId.encodeURLPath()}" +
                    "/notification/notifications/${notificationId.encodeURLPath()}"
            ) {
                method = HttpMethod.Delete
            }

            response.body<ApiResponse<UserNotification>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun deleteUserNotificationByChatEvent(userId: String, chatEventId: String): UserNotification =
        try {
            val response = client.request("${endpoint}${appId}" +
                    "/user/users/${userId.encodeURLPath()}" +
                    "/notification/notificationsbyid/chateventid/${chatEventId.encodeURLPath()}"
            ) {
                method = HttpMethod.Delete
            }

            response.body<ApiResponse<UserNotification>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }

    override suspend fun markAllUserNotificationsAsRead(userid: String, delete: Boolean) {
        try {
            client.request("${endpoint}${appId}" +
                    "/user/users/${userid.encodeURLPath()}" +
                    "/notification/notifications_all/markread"
            ) {
                method = HttpMethod.Put
                parameter("delete", delete)
            }
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }
    }

    override suspend fun muteUser(userId: String, applyeffect: Boolean, expireseconds: Long?): User =
        try {
            val response = client.request("${endpoint}${appId}" +
                    "/user/users/${userId.encodeURLPath()}/mute"
            ) {
                method = HttpMethod.Post
                setBody(
                    MuteUserRequest(
                        applyeffect = applyeffect,
                        expireseconds = expireseconds,
                    )
                )
            }

            response.body<ApiResponse<User>>().data!!
        } catch (err: SportsTalkException) {
            throw err
        } catch (err: Throwable) {
            throw SportsTalkException(
                message = err.message,
                err = err
            )
        }
}