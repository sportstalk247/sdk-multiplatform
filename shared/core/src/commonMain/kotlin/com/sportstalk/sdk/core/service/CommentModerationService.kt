package com.sportstalk.sdk.core.service

import com.sportstalk.sdk.model.comment.ApproveMessageRequest
import com.sportstalk.sdk.model.comment.Comment
import com.sportstalk.sdk.model.comment.CommentFilterModerationState
import com.sportstalk.sdk.model.comment.ListComments

interface CommentModerationService {

    /*
     * [GET] /{{api_appid}}/comment/moderation/queues/comments
     * - https://apiref.sportstalk247.com/?version=latest#d98100c4-6be8-415c-9c08-f6bcbc039566
     * - List all the comments in the moderation queue
     */
    suspend fun listCommentsInModerationQueue(
        limit: Int? = null, // OPTIONAL
        cursor: String? = null, // OPTIONAL
        conversationid: String? = null, // OPTIONAL, limit to comments under specified conversation ID
        filterHandle: String? = null, // OPTIONAL
        filterKeyword: String? = null, // OPTIONAL
        filterModerationState: CommentFilterModerationState? = null // OPTIONAL, Must be "approved", "rejected", "pending", OR "flagged"
    ): ListComments

    /*
     * [POST] /{{api_appid}}/comment/moderation/queues/comments/{{comment_id}}/applydecision
     * - https://apiref.sportstalk247.com/?version=latest#71eb7668-f9d1-4ecf-9e07-1f64699ff071
     * - APPROVE/REJECT a message in the moderation queue.
     */
    suspend fun approveMessageInQueue(
        commentid: String,
        request: ApproveMessageRequest,
    ): Comment

}