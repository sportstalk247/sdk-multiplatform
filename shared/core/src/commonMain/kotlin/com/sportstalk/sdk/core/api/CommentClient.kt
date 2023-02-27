package com.sportstalk.sdk.core.api

import com.sportstalk.sdk.core.service.CommentModerationService
import com.sportstalk.sdk.core.service.CommentService

interface CommentClient: CommentService, CommentModerationService