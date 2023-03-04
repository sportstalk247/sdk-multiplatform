# User Client

``` tabs::

    .. code-tab:: kotlin Android

        val userClient = SportsTalk247.UserClient(
           config = ClientConfig(
              appId = "c84cb9c852932a6b0411e75e", // This is just a sample app id
              apiToken = "5MGq3XbsspBEQf3kj154_OSQV-jygEKwHJyuHjuAeWHA", // This is just a sample token
              endpoint = "http://api.custom.endpoint/v3/" // This is just a sample API endpoint
           )
        )

    .. code-tab:: swift iOS
        
        let userClient = SportsTalk247.UserClient(
           config: ClientConfig(
              appId: "c84cb9c852932a6b0411e75e", // This is just a sample app id
              apiToken: "5MGq3XbsspBEQf3kj154_OSQV-jygEKwHJyuHjuAeWHA", // This is just a sample token
              endpoint: "http://api.custom.endpoint/v3/" // This is just a sample API endpoint
           )
        )
        
```

## Create or Update User

Invoke this function if you want to create a user or update an existing user.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#8cc680a6-6ce8-4af7-ab1e-e793a7f0e7d2>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val createdUser = withContext(Dispatchers.IO) {
                userClient.createOrUpdateUser(
                    request = CreateUpdateUserRequest(
                                userid = "023976080242ac120002",
                                handle = "sample_handle_123",
                                displayname = "Test Name 123", // OPTIONAL
                                pictureurl = "<Image URL>", // OPTIONAL
                                profileurl = "<Image URL>" // OPTIONAL
                    )
                )
            }

            // Resolve `createdUser` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS
        
        // Callback approach
        userClient.createOrUpdateUser(
            request: CreateUpdateUserRequest(
                        userid: "023976080242ac120002",
                        handle: "sample_handle_123",
                        displayname: "Test Name 123", // OPTIONAL
                        pictureurl: "<Image URL>", // OPTIONAL
                        profileurl: "<Image URL>" // OPTIONAL
            )
        ) { createdUser, error in
            // Resolve `createdUser` (ex. Display prompt OR Update UI)            
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let createdUser = try await asyncFunction(
                    for: userClient.createOrUpdateUserAsync(
                            request: CreateUpdateUserRequest(
                                        userid: "023976080242ac120002",
                                        handle: "sample_handle_123",
                                        displayname: "Test Name 123", // OPTIONAL
                                        pictureurl: "<Image URL>", // OPTIONAL
                                        profileurl: "<Image URL>" // OPTIONAL
                            )
                        )
                )
                // Resolve `createdUser` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Get User Details

This will return all the information about the user.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#3323caa9-cc3d-4569-826c-69070ca51758>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val userDetails = withContext(Dispatchers.IO) {
                userClient.getUserDetails(
                    userid = "023976080242ac120002"
                )
            }

            // Resolve `userDetails` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.getUserDetails(
            userid: "023976080242ac120002"
        ) { userDetails, error in
            // Resolve `userDetails` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let userDetails = try await asyncFunction(
                    for: userClient.getUserDetailsAsync(
                            userid: "023976080242ac120002"
                        )
                )
                // Resolve `userDetails` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## List Users

Use this function to cursor through a list of users. This function will return users in the order in which they were created, so it is safe to add new users while cursoring through the list.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#51718594-63ac-4c28-b249-8f47c3cb02b1>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val listUsers = withContext(Dispatchers.IO) {
                userClient.listUsers(
                    limit = 10, /* Defaults to 200 on backend API server */
                    cursor = null // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                )
            }

            // Resolve `listUsers` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.listUsers(
            limit: KotlinInt(int: 10), /* Defaults to 200 on backend API server */
            cursor: nil // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
        ) { listUsers, error in
            // Resolve `listUsers` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let listUsers = try await asyncFunction(
                    for: userClient.listUsersAsync(
                        limit: KotlinInt(int: 10), /* Defaults to 200 on backend API server */
                        cursor: nil // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                    )
                )
                // Resolve `listUsers` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
```

## Ban User

This function toggles the specified user's `banned` flag.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#211d5614-b251-4815-bf76-d8f6f66f97ab>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val bannedUser = withContext(Dispatchers.IO) {
                userClient.setBanStatus(
                    userid = "023976080242ac120002",
                    applyeffect = true, // If set to true, attempt to ban the user. If set to false, attempt to remove the ban from user
                    expireseconds = 3600 // [Optional] if not specified, the ban is permanent until user is restored. If specified, then the ban will be temporarily applied for the specified number of seconds.

                )
            }

            // Resolve `bannedUser` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.setBanStatus(
            userid: "023976080242ac120002",
            applyeffect: true, // If set to true, attempt to ban the user. If set to false, attempt to remove the ban from user
            expireseconds: KotlinLong(integerLiteral: 3600) // [Optional] if not specified, the ban is permanent until user is restored. If specified, then the ban will be temporarily applied for the specified number of seconds.

        ) { bannedUser, error in
            // Resolve `bannedUser` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let bannedUser = try await asyncFunction(
                    for: userClient.setBanStatusAsync(
                        userid: "023976080242ac120002",
                        applyeffect: true, // If set to true, attempt to ban the user. If set to false, attempt to remove the ban from user
                        expireseconds: KotlinLong(integerLiteral: 3600) // [Optional] if not specified, the ban is permanent until user is restored. If specified, then the ban will be temporarily applied for the specified number of seconds.
                    )
                )
                // Resolve `bannedUser` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
```

## Globally Purge User Content

This function will purge all chat content published by the specified user.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#c36d94e2-4fd9-4c9f-8009-f1d8ae9da6f5>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val response = withContext(Dispatchers.IO) {
                userClient.globallyPurgeUserContent(
                    userid = "023976080242ac120002", // ID of the User who's content is about to be purged
                    byuserid = "1234567890" // ID of the User who is about to perform the purge action(requires admin privileges)
                )
            }

            // Resolve `response` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.globallyPurgeUserContent(
            userid: "023976080242ac120002", // ID of the User who's content is about to be purged
            byuserid: "1234567890" // ID of the User who is about to perform the purge action(requires admin privileges)
        ) { response, error in
            // Resolve `response` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let response = try await asyncFunction(
                    for: userClient.globallyPurgeUserContentAsync(
                        userid; "023976080242ac120002", // ID of the User who's content is about to be purged
                        byuserid: "1234567890" // ID of the User who is about to perform the purge action(requires admin privileges)
                    )
                )
                // Resolve `response` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
```

## Mute User

This function toggles the specified user's mute effect.

A muted user is in a read-only state. The muted user can join chat rooms and observe but cannot communicate. This method applies mute on the global level (applies to all talk contexts). You can optionally specify an expiration time. If the expiration time is specified, then each time the shadow banned user tries to send a message the API will check if the shadow ban has expired and will lift the ban.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#0d4c6409-18c6-41f4-9a61-7e2445c5bc0d>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val mutedUser = withContext(Dispatchers.IO) {
                userClient.muteUser(
                    userId = "023976080242ac120002",
                    applyeffect = true, // If set to true, user will be set to muted state. Otherwise, will be set to non-banned state.
                    expireseconds = 3600 // [OPTIONAL]: Duration of mute in seconds. If specified, the mute will be lifted when this time is reached. If not specified, mute effect remains until explicitly lifted. Maximum seconds is a double byte value.
                )
            }

            // Resolve `mutedUser` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.muteUser(
            userId: "023976080242ac120002",
            applyeffect: true, // If set to true, user will be set to muted state. Otherwise, will be set to non-banned state.
            expireseconds: KotlinLong(integerLiteral: 3600) // [OPTIONAL]: Duration of mute in seconds. If specified, the mute will be lifted when this time is reached. If not specified, mute effect remains until explicitly lifted. Maximum seconds is a double byte value.
        ) { mutedUser, error in
            // Resolve `mutedUser` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let mutedUser = try await asyncFunction(
                    for: userClient.muteUserAsync(
                        userId: "023976080242ac120002",
                        applyeffect: true, // If set to true, user will be set to muted state. Otherwise, will be set to non-banned state.
                        expireseconds: KotlinLong(integerLiteral: 3600) // [OPTIONAL]: Duration of mute in seconds. If specified, the mute will be lifted when this time is reached. If not specified, mute effect remains until explicitly lifted. Maximum seconds is a double byte value.
                    )
                )
                // Resolve `mutedUser` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Set Shadow Ban Status

This function toggles the specified user's `shadowbanned` flag.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#211a5696-59ce-4988-82c9-7c614cab3efb>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val shadowBannedUser = withContext(Dispatchers.IO) {
                userClient.setShadowBanStatus(
                    userId = "023976080242ac120002",
                    applyeffect = true, // If set to true, user can send messages into a chat room, however those messages are flagged as shadow banned.
                    expireseconds = 3600 // [OPTIONAL]: Duration of shadowban value in seconds. If specified, the shadow ban will be lifted when this time is reached. If not specified, shadowban remains until explicitly lifted. Maximum seconds is a double byte value.
                )
            }

            // Resolve `shadowBannedUser` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.setShadowBanStatus(
            userId: "023976080242ac120002",
            applyeffect: true, // If set to true, user can send messages into a chat room, however those messages are flagged as shadow banned.
            expireseconds: KotlinLong(integerLiteral: 3600) // [OPTIONAL]: Duration of shadowban value in seconds. If specified, the shadow ban will be lifted when this time is reached. If not specified, shadowban remains until explicitly lifted. Maximum seconds is a double byte value.

        ) { shadowBannedUser, error in
            // Resolve `shadowBannedUser` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let shadowBannedUser = try await asyncFunction(
                    for: userClient.setShadowBanStatusAsync(
                        userId: "023976080242ac120002",
                        applyeffect: true, // If set to true, user can send messages into a chat room, however those messages are flagged as shadow banned.
                        expireseconds: KotlinLong(integerLiteral: 3600) // [OPTIONAL]: Duration of shadowban value in seconds. If specified, the shadow ban will be lifted when this time is reached. If not specified, shadowban remains until explicitly lifted. Maximum seconds is a double byte value.
                    )
                )
                // Resolve `shadowBannedUser` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Search User(s)

This function searches the users in an app.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#dea07871-86bb-4c12-bef3-d7290d762a06>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            // Search by Handle
            val searchedUsersByHandle = withContext(Dispatchers.IO) {
                userClient.searchUsers(
                    handle = "testhandle1",
                    limit = 20, // Defaults to 200 on backend API server
                    cursor = null   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                )
            }

            // Search by Name
            val searchedUsersByName = withContext(Dispatchers.IO) {
                userClient.searchUsers(
                    name = "Josie Rizal",
                    limit = 20, // Defaults to 200 on backend API server
                    cursor = null   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                )
            }

            // Search by User ID
            val searchedUsersByUserId = withContext(Dispatchers.IO) {
                userClient.searchUsers(
                    userid = "userid_georgew",
                    limit = 20, // Defaults to 200 on backend API server
                    cursor = null   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                )
            }
        }

    .. code-tab:: swift iOS
        
        //
        // Callback approach
        //
        
        // Search by Handle
        userClient.searchUsers(
            handle: "testhandle1",
            limit: KotlinInt(int: 20), // Defaults to 200 on backend API server
            cursor: nil   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
        ) { searchedUsersByHandle, error in
            // Resolve `searchedUsersByHandle` (ex. Display prompt OR Update UI)
        }
        
        // Search by Name
        userClient.searchUsers(
            name: "Josie Rizal",
            limit: KotlinInt(int: 20), // Defaults to 200 on backend API server
            cursor: nil   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
        ) { searchedUsersByName, error in
            // Resolve `searchedUsersByName` (ex. Display prompt OR Update UI)
        }
        
        // Search by User ID
        userClient.searchUsers(
            userid: "userid_georgew",
            limit: KotlinInt(int: 20), // Defaults to 200 on backend API server
            cursor: nil   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
        ) { searchedUsersByUserId, error in
            // Resolve `searchedUsersByUserId` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        // Search by Handle
        let handle1 = Task {
            do {
                let searchedUsersByHandle = try await asyncFunction(
                    for: userClient.searchUsersAsync(
                        handle: "testhandle1",
                        limit: KotlinInt(int: 20), // Defaults to 200 on backend API server
                        cursor: nil   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                    )
                )
                // Resolve `searchedUsersByHandle` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        // To cancel the suspend function just cancel the async task
        handle1.cancel()
        
        // Search by Name
        let handle2 = Task {
            do {
                let searchedUsersByName = try await asyncFunction(
                    for: userClient.searchUsersAsync(
                        name: "Josie Rizal",
                        limit: KotlinInt(int: 20), // Defaults to 200 on backend API server
                        cursor: nil   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                    )
                )
                // Resolve `searchedUsersByName` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        // To cancel the suspend function just cancel the async task
        handle2.cancel()
        
        // Search by User ID
        let handle3 = Task {
            do {
                let searchedUsersByUserId = try await asyncFunction(
                    for: userClient.searchUsersAsync(
                        userid: "userid_georgew",
                        limit: KotlinInt(int: 20), // Defaults to 200 on backend API server
                        cursor: nil   // OPTIONAL: The cursor value from previous search attempt to indicate next paginated fetch. Null if fetching the first list of user(s).
                    )
                )
                // Resolve `searchedUsersByUserId` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        // To cancel the suspend function just cancel the async task
        handle3.cancel()
        
```

## Delete User

This function will delete the specified user. All rooms with messages by that user will have the messages from this user purged in the rooms.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#ab387784-ad82-4025-bb3b-56659129279c>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val deletedUser = withContext(Dispatchers.IO) {
                userClient.deleteUser(
                    userid = "023976080242ac120002"
                )
            }

            // Resolve `deletedUser` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.deleteUser(
            userid: "023976080242ac120002"
        ) { deletedUser, error in
            // Resolve `deletedUser` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let deletedUser = try await asyncFunction(
                    for: userClient.deleteUserAsync(
                        userid: "023976080242ac120002"
                    )
                )
                // Resolve `deletedUser` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Report User

This function REPORTS a USER to the moderation team.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#5bfd5d93-dbfb-445c-84ff-c69f184e4277>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val reportedUser = withContext(Dispatchers.IO) {
                userClient.reportUser(
                    userid = "023976080242ac120002"
                )
            }

            // Resolve `reportedUser` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.reportUser(
            userid: "023976080242ac120002"
        ) { reportedUser, error in
            // Resolve `reportedUser` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let reportedUser = try await asyncFunction(
                    for: userClient.reportUserAsync(
                        userid: "023976080242ac120002"
                    )
                )
                // Resolve `reportedUser` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## List User Notifications

This function returns a list of user notifications.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#f09d36c2-de40-4866-8818-74527b2a6df5>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val listUserNotifications = withContext(Dispatchers.IO) {
                userClient.listUserNotifications(
                    userid = "023976080242ac120002",
                    limit = 10, // Can be any arbitrary number
                    filterNotificationTypes = listOf(UserNotification.Type.CHAT_REPLY, UserNotification.Type.CHAT_QUOTE), // [OPTIONAL] List could also have either `CHAT_REPLY` or `CHAT_QUOTE` ONLY
                    cursor = null,
                    includeread = false, // If [true], will only return a list of user notifications whose value `isread = true`. Otherwise, returns a list of user notifications whose value `isread = false`.
                    filterChatRoomId = "080001297623242ac002", // ID of an existing chat room
                    filterChatRoomCustomId = null   // OR you may also use an existing chat room's custom ID
                )
            }

            // Resolve `listUserNotifications` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.listUserNotifications(
            userid: "023976080242ac120002",
            limit: KotlinInt(int: 10), // Can be any arbitrary number
            filterNotificationTypes: [UserNotification.Type.CHAT_REPLY, UserNotification.Type.CHAT_QUOTE], // [OPTIONAL] List could also have either `CHAT_REPLY` or `CHAT_QUOTE` ONLY
            cursor: nil,
            includeread: false, // If [true], will only return a list of user notifications whose value `isread = true`. Otherwise, returns a list of user notifications whose value `isread = false`.
            filterChatRoomId: "080001297623242ac002", // ID of an existing chat room
            filterChatRoomCustomId: nil   // OR you may also use an existing chat room's custom ID
        ) { listUserNotifications, error in
            // Resolve `listUserNotifications` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let listUserNotifications = try await asyncFunction(
                    for: userClient.listUserNotificationsAsync(
                        userid: "023976080242ac120002",
                        limit: KotlinInt(int: 10), // Can be any arbitrary number
                        filterNotificationTypes: [UserNotification.Type.CHAT_REPLY, UserNotification.Type.CHAT_QUOTE], // [OPTIONAL] List could also have either `CHAT_REPLY` or `CHAT_QUOTE` ONLY
                        cursor: nil,
                        includeread: false, // If [true], will only return a list of user notifications whose value `isread = true`. Otherwise, returns a list of user notifications whose value `isread = false`.
                        filterChatRoomId: "080001297623242ac002", // ID of an existing chat room
                        filterChatRoomCustomId: nil   // OR you may also use an existing chat room's custom ID
                    )
                )
                // Resolve `listUserNotifications` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Set User Notification as Read

This marks a notification as being in READ status. That will prevent the notification from being returned in a call to List User Notifications unless the default filters are overridden. Notifications that are marked as read will be automatically deleted after some time.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#073d5ec4-cef6-46cc-8b52-72083db6f310>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val updatedNotification = withContext(Dispatchers.IO) {
                userClient.setUserNotificationAsRead(
                    userid = "023976080242ac120002",    // The ID of user who owns the notification about to update
                    notificationId = "070200623280c142a902",    // The ID of notifications about to update
                    read = true
                )
            }

            // Resolve `updatedNotification` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.setUserNotificationAsRead(
            userid: "023976080242ac120002",    // The ID of user who owns the notification about to update
            notificationId: "070200623280c142a902",    // The ID of notifications about to update
            read: true
        ) { updatedNotification, error in
            // Resolve `updatedNotification` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let updatedNotification = try await asyncFunction(
                    for: userClient.setUserNotificationAsReadAsync(
                        userid: "023976080242ac120002",    // The ID of user who owns the notification about to update
                        notificationId: "070200623280c142a902",    // The ID of notifications about to update
                        read: true
                    )
                )
                // Resolve `updatedNotification` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Set User Notification as Read by Chat Event

This marks a notification as being in READ status. That will prevent the notification from being returned in a call to List User Notifications unless the default filters are overridden. Notifications that are marked as read will be automatically deleted after some time.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#073d5ec4-cef6-46cc-8b52-72083db6f310>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val updatedNotification = withContext(Dispatchers.IO) {
                userClient.setUserNotificationAsReadByChatEvent(
                    userid = "023976080242ac120002",    // The ID of user who owns the notification about to update
                    chatEventId = "070200623280c142a902",    // The ID of chatevent for which the notification was generated from, about to update
                    read = true
                )
            }

            // Resolve `updatedNotification` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.setUserNotificationAsReadByChatEvent(
            userid: "023976080242ac120002",    // The ID of user who owns the notification about to update
            chatEventId: "070200623280c142a902",    // The ID of chatevent for which the notification was generated from, about to update
            read: true
        ) { updatedNotification, error in
            // Resolve `updatedNotification` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let updatedNotification = try await asyncFunction(
                    for: userClient.setUserNotificationAsReadByChatEventAsync(
                        userid: "023976080242ac120002",    // The ID of user who owns the notification about to update
                        chatEventId: "070200623280c142a902",    // The ID of chatevent for which the notification was generated from, about to update
                        read: true
                    )
                )
                // Resolve `updatedNotification` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Delete User Notification

This function immediately deletes a user notification. Unless your workflow specifically implements access to read notifications, you should delete notifications after they are consumed.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#7cbb108d-8b72-4c59-8537-fa9ea4a71364>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val deletedNotification = withContext(Dispatchers.IO) {
                userClient.deleteUserNotification(
                    userid = "023976080242ac120002",    // The ID of user who owns the notification about to delete
                    notificationId = "070200623280c142a902"    // The ID of notifications about to delete
                )
            }

            // Resolve `deletedNotification` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.deleteUserNotification(
            userid: "023976080242ac120002",    // The ID of user who owns the notification about to delete
            notificationId: "070200623280c142a902"    // The ID of notifications about to delete
        ) { deletedNotification, error in
            // Resolve `deletedNotification` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let deletedNotification = try await asyncFunction(
                    for: userClient.deleteUserNotificationAsync(
                        userid: "023976080242ac120002",    // The ID of user who owns the notification about to delete
                        notificationId: "070200623280c142a902"    // The ID of notifications about to delete
                    )
                )
                // Resolve `deletedNotification` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Delete User Notification by Chat Event

This function immediately deletes a user notification. Unless your workflow specifically implements access to read notifications, you should delete notifications after they are consumed.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#073d5ec4-cef6-46cc-8b52-72083db6f310>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            val deletedNotification = withContext(Dispatchers.IO) {
                userClient.deleteUserNotificationByChatEvent(
                    userid = "023976080242ac120002",    // The ID of user who owns the notification about to update
                    chatEventId = "070200623280c142a902"    // The ID of chatevent for which the notification was generated from, about to delete
                )
            }

            // Resolve `deletedNotification` from HERE onwards(ex. update UI displaying the response data)...
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.deleteUserNotification(
            userid: "023976080242ac120002",    // The ID of user who owns the notification about to delete
            notificationId: "070200623280c142a902"    // The ID of notifications about to delete
        ) { deletedNotification, error in
            // Resolve `deletedNotification` (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                let deletedNotification = try await asyncFunction(
                    for: userClient.deleteUserNotificationAsync(
                        userid: "023976080242ac120002",    // The ID of user who owns the notification about to delete
                        notificationId: "070200623280c142a902"    // The ID of notifications about to delete
                    )
                )
                // Resolve `deletedNotification` from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```

## Mark All User Notifications as Read

This marks a all notifications of the user as being in READ status. If delete is set to true, notifications are deleted instead of updated.

Refer to the SportsTalk API Documentation for more details:

<https://apiref.sportstalk247.com/?version=latest#e0c669ff-4722-46b0-ab3e-d1d74d9d340a>

Below is a code sample on how to use this SDK feature:

``` tabs::

    .. code-tab:: kotlin Android

        // Launch thru coroutine block
        // https://developer.android.com/topic/libraries/architecture/coroutines
        lifecycleScope.launch {
            // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
            withContext(Dispatchers.IO) {
                userClient.markAllUserNotificationsAsRead(
                    userid = "023976080242ac120002",    // The ID of user who owns the notification about to update
                    delete = true
                )
            }
        }

    .. code-tab:: swift iOS

        // Callback approach
        userClient.markAllUserNotificationsAsRead(
            userid: "023976080242ac120002",    // The ID of user who owns the notification about to update
            delete: true
        ) { _, error in
            // Resolve (ex. Display prompt OR Update UI)
        }
        
        //
        // If using KMPNativeCoroutines framework
        //
        import KMPNativeCoroutinesAsync
        
        let handle = Task {
            do {
                try await asyncFunction(
                    for: userClient.markAllUserNotificationsAsReadAsync(
                        userid: "023976080242ac120002",    // The ID of user who owns the notification about to update
                        delete: true
                    )
                )
                // Resolve from HERE onwards(ex. update UI displaying the response data)...
            } catch {
                // ...
            }
        }
        
        // To cancel the suspend function just cancel the async task
        handle.cancel()
        
```
