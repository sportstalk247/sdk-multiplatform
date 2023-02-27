package com.sportstalk.sdk.core.api

import com.sportstalk.sdk.core.service.ChatModerationService
import com.sportstalk.sdk.core.service.ChatService
import com.sportstalk.sdk.model.chat.ChatRoom

interface ChatClient: ChatService, ChatModerationService {
    var currentRoom: ChatRoom?
    var defaultImageBannerUrl: String?
}