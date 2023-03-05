package com.sportstalk.sdk.core.api

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.sportstalk.sdk.core.service.ChatModerationService
import com.sportstalk.sdk.core.service.ChatService
import com.sportstalk.sdk.model.chat.ChatEvent
import com.sportstalk.sdk.model.chat.ChatRoom
import kotlinx.coroutines.flow.Flow

interface ChatClient: ChatService, ChatModerationService {
    var currentRoom: ChatRoom?
    var defaultImageBannerUrl: String?

    /**
     * You can use this function to poll the room to get the recent events in the room.
     * The recommended poll interval is 500ms. Each event has an ID and a timestamp.
     * To detect new messages using polling, call this function and then process items
     * with a newer timestamp than the most recent one you have already processed.
     */
    @NativeCoroutines
    fun allEventUpdates(
        chatRoomId: String,
        /*
         * Polling Frequency
         * - If provided value is below 1000ms, throw a SportstalkException to indicate that frequency must be equal to or greater than 1000ms.
         */
        frequency: Long = 1000L,
        limit: Int? = null, // (optional) Number of events to return for each poll. Default is 100, maximum is 500.
        /**
         * If [true], render events with some spacing.
         * - However, if we have a massive batch, we want to catch up, so we do not put spacing and just jump ahead.
         */
        smoothEventUpdates: Boolean = true,
        /**
         * (optional, 200ms by default) This only applies if `smoothEventUpdates` = true.
         * This defines how long to pause before emitting the next event in a batch.
         */
        eventSpacingMs: Long = 200L,
        /**
         * (optional, 30 by default) This only applies if `smoothEventUpdates` = true.
         * Holds the size of the event buffer we will accept before displaying everything in order to catch up.
         */
        maxEventBufferSize: Int = 30,
    ): Flow<List<ChatEvent>>
}