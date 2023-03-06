# How to use SportsTalk SDK

## Instantiate SportsTalkManager Clients

This Sportstalk SDK is meant to power custom chat applications. Sportstalk does not enforce any restricitons on your UI design, but instead empowers your developers to focus on the user experience without worrying about the underlying chat behavior.

``` tabs::

    .. code-tab:: kotlin Android

        import com.sportstalk.coroutine.SportsTalk247
        // ...
        
        // YOUR APP ID
        val appId = "c84cb9c852932a6b0411e75e" // This is just a sample app id
        // YOUR API TOKEN
        val apiToken = "5MGq3XbsspBEQf3kj154_OSQV-jygEKwHJyuHjuAeWHA" // This is just a sample token
        val endpoint = "http://api.custom.endpoint/v1/" // please ensure out of the box the SDKs are configured for production URL
        
        // Instantiate User Client
        val userClient = SportsTalk247.UserClient(
            config = ClientConfig(
                appId = appId,
                apiToken = apiToken,
                endpoint = endpoint
            )
        )
    
        // Instantiate Chat Client
        val chatClient = SportsTalk247.ChatClient(
            config = ClientConfig(
                appId = appId,
                apiToken = apiToken,
                endpoint = endpoint
            )
        )

    .. code-tab:: kotlin iOS

        import Sportstalk247Kit
        // ...
        
        // YOUR APP ID
        let appId = "c84cb9c852932a6b0411e75e" // This is just a sample app id
        // YOUR API TOKEN
        let apiToken = "5MGq3XbsspBEQf3kj154_OSQV-jygEKwHJyuHjuAeWHA" // This is just a sample token
        let endpoint = "http://api.custom.endpoint/v1/" // please ensure out of the box the SDKs are configured for production URL
        
        // Instantiate User Client
        let userClient = SportsTalk247.shared.UserClient(
            config: ClientConfig(
                appId: appId,
                apiToken: apiToken,
                endpoint: endpoint
            )
        )
    
        // Instantiate Chat Client
        let chatClient = SportsTalk247.shared.ChatClient(
            config: ClientConfig(
                appId: appId,
                apiToken: apiToken,
                endpoint: endpoint
            )
        )

```

## Using the SDK

``` tabs::
    
    .. tab:: Android

        This Sportstalk SDK artifact is an Asynchronous-driven API, powered by `Kotlin Coroutines <https://developer.android.com/kotlin/coroutines>`_ to gracefully handle asynchronous operations.
        
        Client SDK functions are declared with `suspend` keyword. This means that the function should be invoked from within coroutine scope. See the example below:

        .. code-block:: kotlin

            class MyFragment: Fragment() {

                // ...
                // ...
                // Instantiate User Client
                val userClient = SportsTalk247.UserClient(/*...*/) 

                override fun onViewCreated(view: View) {
                    // ...
                    // Launch thru coroutine block
                    // https://developer.android.com/topic/libraries/architecture/coroutines
                    lifecycleScope.launch {
                        // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
                        val createdUser = withContext(Dispatchers.IO) {
                            userClient.createOrUpdateUser(
                                request = CreateUpdateUserRequest(
                                            userid = "8cb689cc-21b7-11eb-adc1-0242ac120002", // sample user ID
                                            handle = "sample_handle_123",
                                            displayname = "Test Name 123", // OPTIONAL
                                            pictureurl = "https://i.imgur.com/ohlx5wW.jpeg", // OPTIONAL
                                            profileurl = "https://i.imgur.com/ohlx5wW.jpeg" // OPTIONAL
                                        )
                            )
                        }

                        // Resolve `createdUser` from HERE onwards(ex. update UI displaying the response data)...
                    }

                }

            }

    .. tab:: iOS

        This Sportstalk SDK framework 

        Client SDK functions provide completion handler. See the example below:

        .. code-block:: swift

            // ...
            // ...

            // Instantiate User Client
            let userClient = SportsTalk247.shared.UserClient(/*...*/)
            
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
            // Developer may also use Swift Async/Await
            //
            
            let handle = Task {
                do {
                    let createdUser = try await userClient.createOrUpdateUser(
                        request: CreateUpdateUserRequest(
                            userid: "023976080242ac120002",
                            handle: "sample_handle_123",
                            displayname: "Test Name 123", // OPTIONAL
                            pictureurl: "<Image URL>", // OPTIONAL
                            profileurl: "<Image URL>" // OPTIONAL
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

## Implement Custom JWT

``` tabs::
    
    .. tab:: Android
    
        You can instantiate a JWTProvider instance and provide a token refresh action function that returns a new token. Then you just have to launch the coroutine flow by calling `JWTProvider.observe()` method.

        .. code-block:: kotlin

            // ...
            // ...
        
            // YOUR APP ID
            val appId = "c84cb9c852932a6b0411e75e" // This is just a sample app id
            // YOUR API TOKEN
            val apiToken = "5MGq3XbsspBEQf3kj154_OSQV-jygEKwHJyuHjuAeWHA" // This is just a sample token
            val endpoint = "http://api.custom.endpoint/v1/" // please ensure out of the box the SDKs are configured for production URL
            
            val config = ClientConfig(
                appId = appId,
                apiToken = apiToken,
                endpoint = endpoint
            )
            
            // Prepare JWTProvider
            val myJwtProvider = JWTProvider(
                token = "...",  // Developer may immediately provide a token on init
                tokenRefreshAction = /* This is a suspend function */ { 
                    val newToken = doPerformFetchNewToken() // Developer may perform a long-running operation to generate a new JWT
                    return@JWTProvider newToken
                }
            )
            
            // Set custom JWTProvider
            SportsTalk247.setJWTProvider(
                config = config,
                provider = myJwtProvider
            )
            
            //
            // In order to make refresh callback work, developer must bind through a coroutine scope by calling `observe()` function.
            // 
            val coroutineScope = viewLifecycleOwner.lifecycleScope  // If called from within a Fragment
            // val coroutineScope = this.lifecycleScope  // If called from within a Fragment
            // val coroutineScope = CoroutineScope(context = EmptyCoroutineContext) // Developer may also provide a custom coroutine scope of choice
            myJwtProvider.observe()
                .launchIn(coroutineScope) 
            
            // ...
            // Instantiate Chat Client
            val chatClient = SportsTalk247.ChatClient(
                config = ClientConfig(
                    appId = appId,
                    apiToken = apiToken,
                    endpoint = endpoint
                )
            )
            
            // Launch thru coroutine block
            // https://developer.android.com/topic/libraries/architecture/coroutines
            lifecycleScope.launch {
                try {
                    // Switch to IO Coroutine Context(Operation will be executed on IO Thread)
                    val joinRoomResponse = withContext(Dispatchers.IO) {
                        chatClient.joinRoom(
                            chatRoomId = "080001297623242ac002",    // ID of an existing chat room
                            request = JoinChatRoomRequest(
                                userid = "023976080242ac120002" // ID of an existing user from this chatroom
                            )
                        )
                    }                    
                } catch(err: SportsTalkException) {
                    err.printStackTrace()
                    
                    //
                    // Handle Unauthorized Error
                    //  - Attempt request refresh token
                    // 
                    if(err.code == 401) {
                        jwtProvider.refreshToken()
                        // Then, prompt UI layer to perform the operation again after a short while(this is to ensure that the token gets refreshed first before retry attempt)
                    }
                }
    
            }

    .. tab:: iOS
        
        You can instantiate a JWTProvider instance and provide a token refresh action observable that returns a new token. Then you just have to launch the function `JWTProvider.observeAsync()` method to observe to start observing for JWT token changes.
        
        .. code-block:: swift
            
            // ...
            // ...
            
            // YOUR APP ID
            let appId = "c84cb9c852932a6b0411e75e" // This is just a sample app id
            // YOUR API TOKEN
            let apiToken = "5MGq3XbsspBEQf3kj154_OSQV-jygEKwHJyuHjuAeWHA" // This is just a sample token
            let endpoint = "http://api.custom.endpoint/v1/" // please ensure out of the box the SDKs are configured for production URL
            
            let config = ClientConfig(
                appId: appId,
                apiToken apiToken,
                endpoint: endpoint
            )
            
            //
            // Prepare JWTProvider
            //
            
            // Create a class implementing KotlinSuspendFunction1 protocol
            class OnJWTRefreshTokenFunction: KotlinSuspendFunction1 {
                func invoke(p1: Any?, completionHandler: @escaping (Any?, Error?) -> Void) {
                    guard let oldToken = p1 as? String else {
                        return
                    }
                    
                    // ...
                    // ...
                    let newToken: String? = "..."// Long-running operation ~ Fetch New JWT Token
                    let error: Error? = nil
                    completionHandler(newToken, error)
                }
                
            }
            let refreshTokenFunction = OnJWTRefreshTokenFunction()
            let myJwtProvider = JWTProvider(
                token: "...",  // Developer may immediately provide a token on init
                tokenRefreshAction: refreshTokenFunction
            )
            
            // Set custom JWTProvider
            SportsTalk247.shared.setJWTProvider(
                config: config,
                provider: myJwtProvider
            )
            
            // ...
            // Instantiate Chat Client
            let chatClient = SportsTalk247.shared.ChatClient(
                config: ClientConfig(
                    appId: appId,
                    apiToken: apiToken,
                    endpoint: endpoint
                )
            )
            
            let observeHandle = Task.detached {
                // Create an Async Stream to observe token changes
                let stream = asyncStream(
                    // This is generated swift async function of `JwtProvider.observe()` that returns a stream. 
                    for: myJwtProvider.observeAsync()
                )
                
                for try await newToken in stream {
                    // Resolve `newToken`
                }
            }
                        
            let handle = Task {
            do { 
                
                let joinRoomResponse = try await chatClient.joinRoom(
                        chatRoomId = "080001297623242ac002",    // ID of an existing chat room
                        request = JoinChatRoomRequest(
                            userid = "023976080242ac120002" // ID of an existing user from this chatroom
                        )
                    )
            } catch {
                //
                // Handle Unauthorized Error
                //  - Attempt request refresh token
                // 
                if let error as? SportsTalkException,
                    error.code == 401 {
                    jwtProvider.refreshToken()
                    // Then, prompt UI layer to perform the operation again after a short while(this is to ensure that the token gets refreshed first before retry attempt)
                }
            }
            
            // ...
            // To cancel the flow (collection) just cancel the async task
            handle.cancel()
            // ...
            observeHandle.cancel()

                
```

You can also directly specify the JWT value by calling `JWTProvider.setToken(newToken)`.
There is also a function provided to explicitly refresh token by calling `JWTProvider.refreshToken()`, which will trigger the provided token refresh action above to fetch a new token and will automatically add that on the SDK.

Once the User Token has been added to the SDK, the SDK will automatically append it to all requests.

## Handling SDK Exception

If any client operations receive an error response, whether it be Network, Server, or Validation Error, these functions will throw an instance of `SportsTalkException`.

```kotlin

data class SportsTalkException(
    val kind: String? = null, // "api.result"  
    val message: String? = null, // ex. "The specified comment was not found."  
    val code: Int? = null, // ex. 404
    val data: Map<String, String?>? = null,
    val err: Throwable? = null
): Exception(message, err)

```

``` tabs::

    .. code-tab:: kotlin Android
        
        // Under Fragment class
        // Execute within coroutine scope
        lifecycleScope.launch {
            val testComment = Comment(id = "0987654321",...)
            
            val setCommentDeletedResponse = try {  
                withContext(Dispatchers.IO) {  
                    // These should throw Error 404 - "The specified conversation was not found and was not deleted.".  
                    commentClient.permanentlyDeleteComment(  
                        conversationid = "Non-existent-Conversation-ID",  
                        commentid = testComment.id!!  
                    )
                }
            } catch(err: SportsTalkException) {
                // Resolve ERROR from HERE.
                return
            }
        }

    .. code-tab:: swift iOS
    
        // ...
        // ...

        // These should throw Error 404
        userClient.createOrUpdateUser(
            request: CreateUpdateUserRequest(
                        userid: "not-existing-user-id",
                        handle: "sample_handle_123",
                        displayname: "Test Name 123", // OPTIONAL
                        pictureurl: "<Image URL>", // OPTIONAL
                        profileurl: "<Image URL>" // OPTIONAL
            )
        ) { createdUser, error in
        
            guard let error != nil, error as? SportsTalkException else {
                // Handle `error`
                
                return
            }
            
            // ...            
        }

```
