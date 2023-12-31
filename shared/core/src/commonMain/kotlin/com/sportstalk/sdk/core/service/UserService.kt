package com.sportstalk.sdk.core.service

import com.sportstalk.sdk.model.user.*

interface UserService {

    /**
     * [POST] /{{api_appid}}/user/users/{{userId}}
     * - https://apiref.sportstalk247.com/?version=latest#8cc680a6-6ce8-4af7-ab1e-e793a7f0e7d2
     * - Invoke this API method if you want to create a user or update an existing user.
     */
    suspend fun createOrUpdateUser(request: CreateUpdateUserRequest): User

    /**
     * [DEL] /{{api_appid}}/user/users/{{userId}}
     * - https://apiref.sportstalk247.com/?version=latest#8cc680a6-6ce8-4af7-ab1e-e793a7f0e7d2
     * - Deletes the specified user
     */
    suspend fun deleteUser(userId: String): DeleteUserResponse

    /**
     * [GET] /{{api_appid}}/user/users/{{userId}}
     * - https://apiref.sportstalk247.com/?version=latest#3323caa9-cc3d-4569-826c-69070ca51758
     * - This will return all the information about the user.
     */
    suspend fun getUserDetails(userId: String): User

    /**
     * [GET] /{{api_appid}}/user/users/?limit={{limit}}&cursor={{cursor}}
     * - https://apiref.sportstalk247.com/?version=latest#51718594-63ac-4c28-b249-8f47c3cb02b1
     * - Gets a list of users
     */
    suspend fun listUsers(
        limit: Int? = null /* Defaults to 200 on backend API server */,
        cursor: String? = null
    ): ListUsersResponse

    /**
     * [POST] /{{api_appid}}/user/users/{{userId}}/ban
     * - https://apiref.sportstalk247.com/?version=latest#211d5614-b251-4815-bf76-d8f6f66f97ab
     * - Will toggle the user's banned flag
     */
    suspend fun setBanStatus(
        userId: String,
        applyeffect: Boolean,
        expireseconds: Long? = null
    ): User

    /**
     * [POST] /{{api_appid}}/user/search
     * - https://apiref.sportstalk247.com/?version=latest#dea07871-86bb-4c12-bef3-d7290d762a06
     * - Searches the users in an app
     */
    suspend fun searchUsers(
        handle: String? = null,
        name: String? = null,
        userid: String? = null,
        limit: Int? = null /* Defaults to 200 on backend API server */,
        cursor: String? = null
    ): ListUsersResponse

    /**
     * [POST] /{{api_appid}}/user/users/{userId}/shadowban
     * - https://apiref.sportstalk247.com/?version=latest#211a5696-59ce-4988-82c9-7c614cab3efb
     * - Will toggle the user's shadow banned flag
     */
    suspend fun setShadowBanStatus(
        userId: String,
        applyeffect: Boolean,
        expireseconds: Long? = null
    ): User

    /**
     * [POST] /{{api_appid}}/user/users/{userId}/globalpurge
     * - https://apiref.sportstalk247.com/?version=latest#c36d94e2-4fd9-4c9f-8009-f1d8ae9da6f5
     * - Will purge all chat content published by the specified user
     */
    suspend fun globallyPurgeUserContent(
        userId: String, // ID of the User who's content is about to be purged
        byuserid: String,   // ID of the User who is about to perform the purge action(requires admin privileges)
    ): GloballyPurgeUserContentResponse

    /**
     * [POST] /{{api_appid}}/user/users/{userId}/report
     * - https://apiref.sportstalk247.com/?version=latest#5bfd5d93-dbfb-445c-84ff-c69f184e4277
     * - REPORTS a USER to the moderation team.
     */
    suspend fun reportUser(
        userId: String,
        /** [ReportType] */
        reporttype: String
    ): ReportUserResponse

    /**
     * [GET] /{{api_appid}}/user/users/{userId}/notification/listnotifications?filterNotificationTypes=&limit=&includeread=
     * - https://apiref.sportstalk247.com/?version=latest#f09d36c2-de40-4866-8818-74527b2a6df5
     * - Returns a list of user notifications
     */
    suspend fun listUserNotifications(
        userId: String,
        limit: Int,
        filterNotificationTypes: List<UserNotificationType>? = null,
        cursor: String? = null,
        includeread: Boolean? = null,
        filterChatRoomId: String? = null,
        filterChatRoomCustomId: String? = null
    ): ListUserNotificationsResponse

    /**
     * [PUT] /{{api_appid}}/user/users/{userId}/notification/notifications/{notificationId}/update?read=
     * - https://apiref.sportstalk247.com/?version=latest#e0c669ff-4722-46b0-ab3e-d1d74d9d340a
     * - This marks a notification as being in READ status.
     */
    suspend fun setUserNotificationAsRead(
        userId: String,
        notificationId: String,
        read: Boolean
    ): UserNotification

    /**
     * [PUT] /{{api_appid}}/user/users/{userId}/notification/notificationsbyid/chateventid/{chatEventId}/update?read=
     * - https://apiref.sportstalk247.com/?version=latest#4172ba35-f6e1-472d-8442-03dc1d3eda10
     * - This marks a notification as being in READ status.
     */
    suspend fun setUserNotificationAsReadByChatEvent(
        userId: String,
        chatEventId: String,
        read: Boolean
    ): UserNotification

    /**
     * [DEL] /{{api_appid}}/user/users/{userId}/notification/notifications/{notificationId}
     * - https://apiref.sportstalk247.com/?version=latest#7cbb108d-8b72-4c59-8537-fa9ea4a71364
     * - Deletes a User Notification
     */
    suspend fun deleteUserNotification(
        userId: String,
        notificationId: String
    ): UserNotification

    /**
     * [DEL] /{{api_appid}}/user/users/{userId}/notification/notificationsbyid/chateventid/{chatEventId}/update?read=
     * - https://apiref.sportstalk247.com/?version=latest#6fe38a3a-f365-4fd0-ba96-6c1a22cd2bac
     * - Deletes a User Notification by Chat Event
     */
    suspend fun deleteUserNotificationByChatEvent(
        userId: String,
        chatEventId: String
    ): UserNotification

    /**
     * [GET] /{{api_appid}}/user/users/{userId}/notification/notifications_all/markread?delete=
     * - https://apiref.sportstalk247.com/?version=latest#e0c669ff-4722-46b0-ab3e-d1d74d9d340a
     * - This marks a notification as being in READ status. That will prevent the notification from being returned in a call to List User Notifications unless the default filters are overridden. Notifications that are marked as read will be automatically deleted after some time.
     */
    suspend fun markAllUserNotificationsAsRead(
        userid: String,
        delete: Boolean
    )

    /**
     * [GET] /{{api_appid}}/user/users/{userId}/mute
     * - https://apiref.sportstalk247.com/?version=latest#0d4c6409-18c6-41f4-9a61-7e2445c5bc0d
     * - Will toggle the user's mute effect
     */
    suspend fun muteUser(
        userId: String,
        applyeffect: Boolean,
        expireseconds: Long? = null
    ): User

}