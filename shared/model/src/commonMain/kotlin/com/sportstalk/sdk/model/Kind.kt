package com.sportstalk.sdk.model

object Kind {
    const val CHAT = "chat.event"
    const val ROOM = "chat.room"
    const val ROOM_EXTENDED_DETAILS = "chat.room.list.extendeddetails"
    const val USER = "app.user"
    const val NOTIFICATION = "notification"
    const val LIST_USER_NOTIFICATIONS = "list.usernotifications"
    const val API = "api.result"
    const val WEBHOOK = "chat.webhook"
    const val WEBHOOK_LOGS = "list.webhook.logentries"
    const val WEBHOOK_COMMENT_PAYLOAD = "webhook.payload.comment"
    const val CHAT_COMMAND = "chat.executecommand"
    const val CONVERSATION = "comment.conversation"
    const val DELETED_CONVERSATION = "delete.conversation"
    const val LIST_COMMENTS = "list.comments"
    const val COMMENT = "comment.comment"
    const val COMMENT_REPLIES_BY_PARENT = "list.repliesbyparentid"
    const val COMMENT_REPLY_GROUP = "list.commentreplygroup"
    const val DELETED_COMMENT ="delete.comment"
    const val DELETED_ROOM = "deleted.room"
    const val DELETED_USER = "deleted.appuser"
    const val CONVERSATION_LIST = "list.commentconversations"
    const val BATCH_CONVERSATION_DETAILS = "list.comment.conversation.details"
    const val CHAT_PARTICIPANT = "chat.participant"
    const val CHAT_LIST = "list.chatevents"
    const val CHAT_LIST_BY_TIMESTAMP = "list.chateventsbytimestamp"
    const val CHAT_LIST_PARTICIPANTS = "chat.list.participants"
    const val LIST_USER_ROOM_SUBSCRIPTIONS = "chat.list.userroomsubscriptions"
    const val CHAT_SUBSCRIPTION = "chat.subscription"
    const val CHAT_SUBSCRIPTION_AND_STATUS = "chat.subscriptionandstatus"
    const val CHAT_ROOM_STATUS = "chat.roomstatus"
    const val JOIN_ROOM = "chat.joinroom"
    const val ROOM_LIST = "list.chatrooms"
    const val USER_LIST = "list.users"
    const val BOUNCE_USER = "chat.bounceuser"
}