package com.sportstalk.sdk.model.user

@kotlinx.serialization.Serializable
data class ListUserNotificationsResponse(
    /** [Kind.LIST_USER_NOTIFICATIONS] "list.usernotifications" */
    val kind: String? = null,
    val cursor: String? = null,
    val more: Boolean? = null,
    val itemcount: Int? = null,
    val totalunread: Int? = null,
    val notifications: List<UserNotification> = listOf()
)