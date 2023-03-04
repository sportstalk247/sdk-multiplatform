#import <Foundation/NSArray.h>
#import <Foundation/NSDictionary.h>
#import <Foundation/NSError.h>
#import <Foundation/NSObject.h>
#import <Foundation/NSSet.h>
#import <Foundation/NSString.h>
#import <Foundation/NSValue.h>

@class Sportstalk247KitSportsTalk247, Sportstalk247KitClientConfig, Sportstalk247KitJWTProvider, Sportstalk247KitWebRegexConstants, Sportstalk247KitChatRoom, Sportstalk247KitBounceUserRequest, Sportstalk247KitBounceUserResponse, Sportstalk247KitCreateChatRoomRequest, Sportstalk247KitDeleteChatRoomResponse, Sportstalk247KitExecuteChatCommandRequest, Sportstalk247KitExecuteChatCommandResponse, Sportstalk247KitDeleteEventResponse, Sportstalk247KitChatEvent, Sportstalk247KitRoomDetailEntityType, Sportstalk247KitGetRoomDetailsExtendedBatchResponse, Sportstalk247KitGetUpdatesResponse, Sportstalk247KitJoinChatRoomResponse, Sportstalk247KitJoinChatRoomRequest, Sportstalk247KitListEventsByTimestamp, Sportstalk247KitListEvents, Sportstalk247KitListMessagesByUser, Sportstalk247KitListChatRoomParticipantsResponse, Sportstalk247KitListRoomsResponse, Sportstalk247KitListUserSubscribedRoomsResponse, Sportstalk247KitReactToAMessageRequest, Sportstalk247KitReportMessageRequest, Sportstalk247KitSearchEventHistoryRequest, Sportstalk247KitSearchEventHistoryResponse, Sportstalk247KitSendQuotedReplyRequest, Sportstalk247KitSendThreadedReplyRequest, Sportstalk247KitChatSubscription, Sportstalk247KitUpdateChatMessageRequest, Sportstalk247KitUpdateChatRoomRequest, Sportstalk247KitUser, Sportstalk247KitListMessagesNeedingModerationResponse, Sportstalk247KitChatClientImplCompanion, Sportstalk247KitBatchGetConversationEntity, Sportstalk247KitBatchGetConversationDetailsResponse, Sportstalk247KitCreateCommentRequest, Sportstalk247KitComment, Sportstalk247KitCreateOrUpdateConversationRequest, Sportstalk247KitConversation, Sportstalk247KitDeleteConversationResponse, Sportstalk247KitDeleteCommentResponse, Sportstalk247KitDirectionType, Sportstalk247KitSortType, Sportstalk247KitListComments, Sportstalk247KitListConversations, Sportstalk247KitListRepliesBatchResponse, Sportstalk247KitReactToCommentRequest, Sportstalk247KitReactToConversationTopicRequest, Sportstalk247KitReportCommentRequest, Sportstalk247KitUpdateCommentRequest, Sportstalk247KitVoteOnCommentRequest, Sportstalk247KitApproveMessageRequest, Sportstalk247KitCommentFilterModerationState, Sportstalk247KitCreateUpdateUserRequest, Sportstalk247KitDeleteUserResponse, Sportstalk247KitUserNotification, Sportstalk247KitGloballyPurgeUserContentResponse, Sportstalk247KitListUserNotificationsResponse, Sportstalk247KitListUsersResponse, Sportstalk247KitApiResponseCompanion, Sportstalk247KitApiResponse<T>, Sportstalk247KitKotlinArray<T>, Sportstalk247KitKind, Sportstalk247KitKotlinThrowable, Sportstalk247KitKotlinException, Sportstalk247KitKotlinx_serialization_jsonJsonElement, Sportstalk247KitSportsTalkExceptionCompanion, Sportstalk247KitSportsTalkException, Sportstalk247KitApproveMessageRequestCompanion, Sportstalk247KitBatchGetConversationDetailsResponseCompanion, Sportstalk247KitKotlinEnumCompanion, Sportstalk247KitKotlinEnum<E>, Sportstalk247KitBatchGetConversationEntityCompanion, Sportstalk247KitReaction, Sportstalk247KitVoteDetail, Sportstalk247KitReport, Sportstalk247KitCommentCompanion, Sportstalk247KitCommentFilterModerationStateCompanion, Sportstalk247KitCommentType, Sportstalk247KitConversationCompanion, Sportstalk247KitCreateCommentRequestCompanion, Sportstalk247KitCreateOrUpdateConversationRequestCompanion, Sportstalk247KitDeleteCommentResponseCompanion, Sportstalk247KitDeleteConversationResponseCompanion, Sportstalk247KitDirectionTypeCompanion, Sportstalk247KitListCommentsCompanion, Sportstalk247KitListConversationsCompanion, Sportstalk247KitListRepliesBatchResponseCommentReplyGroup, Sportstalk247KitListRepliesBatchResponseCompanion, Sportstalk247KitListRepliesBatchResponseCommentReplyGroupCompanion, Sportstalk247KitReactToCommentRequestCompanion, Sportstalk247KitReactToConversationTopicRequestCompanion, Sportstalk247KitReportCommentRequestCompanion, Sportstalk247KitSortTypeCompanion, Sportstalk247KitUpdateCommentRequestCompanion, Sportstalk247KitVoteType, Sportstalk247KitVoteDetailCompanion, Sportstalk247KitVoteOnCommentRequestCompanion, Sportstalk247KitVoteTypeCompanion, Sportstalk247KitReactionCompanion, Sportstalk247KitReactionType, Sportstalk247KitBanUserRequestCompanion, Sportstalk247KitBanUserRequest, Sportstalk247KitCreateUpdateUserRequestCompanion, Sportstalk247KitDeleteUserResponseCompanion, Sportstalk247KitGloballyPurgeUserContentRequestCompanion, Sportstalk247KitGloballyPurgeUserContentRequest, Sportstalk247KitGloballyPurgeUserContentResponseCompanion, Sportstalk247KitListUserNotificationsResponseCompanion, Sportstalk247KitListUsersResponseCompanion, Sportstalk247KitMuteUserRequestCompanion, Sportstalk247KitMuteUserRequest, Sportstalk247KitReportUserRequestCompanion, Sportstalk247KitReportUserRequest, Sportstalk247KitSearchUsersRequestCompanion, Sportstalk247KitSearchUsersRequest, Sportstalk247KitSetShadowBanStatusRequestCompanion, Sportstalk247KitSetShadowBanStatusRequest, Sportstalk247KitUserReport, Sportstalk247KitUserCompanion, Sportstalk247KitUserRole, Sportstalk247KitUserNotificationCompanion, Sportstalk247KitUserNotificationType, Sportstalk247KitUserReportCompanion, Sportstalk247KitBounceUserRequestCompanion, Sportstalk247KitBounceUserResponseCompanion, Sportstalk247KitChatEventCompanion, Sportstalk247KitReportedUser, Sportstalk247KitChatRoomCompanion, Sportstalk247KitChatRoomParticipantCompanion, Sportstalk247KitChatRoomParticipant, Sportstalk247KitChatSubscriptionCompanion, Sportstalk247KitCreateChatRoomRequestCompanion, Sportstalk247KitDeleteChatRoomResponseCompanion, Sportstalk247KitDeleteEventResponseCompanion, Sportstalk247KitEventType, Sportstalk247KitExecuteChatCommandRequestCompanion, Sportstalk247KitExecuteChatCommandResponseCompanion, Sportstalk247KitExitChatRoomRequestCompanion, Sportstalk247KitExitChatRoomRequest, Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetail, Sportstalk247KitGetRoomDetailsExtendedBatchResponseCompanion, Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetailCompanion, Sportstalk247KitGetUpdatesResponseCompanion, Sportstalk247KitJoinChatRoomRequestCompanion, Sportstalk247KitJoinChatRoomResponseCompanion, Sportstalk247KitListChatRoomParticipantsResponseCompanion, Sportstalk247KitListEventsCompanion, Sportstalk247KitListEventsByTimestampCompanion, Sportstalk247KitListMessagesByUserCompanion, Sportstalk247KitListRoomsResponseCompanion, Sportstalk247KitListUserSubscribedRoomsResponseData, Sportstalk247KitListUserSubscribedRoomsResponseCompanion, Sportstalk247KitListUserSubscribedRoomsResponseRoomStatus, Sportstalk247KitListUserSubscribedRoomsResponseDataCompanion, Sportstalk247KitListUserSubscribedRoomsResponseRoomStatusCompanion, Sportstalk247KitModerationType, Sportstalk247KitMuteUserInRoomRequestCompanion, Sportstalk247KitMuteUserInRoomRequest, Sportstalk247KitReactToAMessageRequestCompanion, Sportstalk247KitReportMessageRequestCompanion, Sportstalk247KitReportUserInRoomRequestCompanion, Sportstalk247KitReportUserInRoomRequest, Sportstalk247KitReportedUserCompanion, Sportstalk247KitSearchEventHistoryRequestDirection, Sportstalk247KitSearchEventHistoryRequestCompanion, Sportstalk247KitSearchEventHistoryRequestDirectionCompanion, Sportstalk247KitSearchEventHistoryResponseCompanion, Sportstalk247KitSendQuotedReplyRequestCompanion, Sportstalk247KitSendThreadedReplyRequestCompanion, Sportstalk247KitShadowBanUserInRoomRequestCompanion, Sportstalk247KitShadowBanUserInRoomRequest, Sportstalk247KitUpdateChatMessageRequestCompanion, Sportstalk247KitUpdateChatRoomRequestCompanion, Sportstalk247KitApproveMessageRequest_Companion, Sportstalk247KitApproveMessageRequest_, Sportstalk247KitListMessagesNeedingModerationResponseCompanion, Sportstalk247KitReportCompanion, Sportstalk247KitReportType, Sportstalk247KitKotlinRuntimeException, Sportstalk247KitKotlinIllegalStateException, Sportstalk247KitKotlinx_serialization_jsonJsonElementCompanion, Sportstalk247KitKotlinx_serialization_coreSerializersModule, Sportstalk247KitKotlinx_serialization_coreSerialKind, Sportstalk247KitKotlinNothing;

@protocol Sportstalk247KitChatClient, Sportstalk247KitCommentClient, Sportstalk247KitUserClient, Sportstalk247KitKotlinx_coroutines_coreFlow, Sportstalk247KitChatService, Sportstalk247KitChatModerationService, Sportstalk247KitCommentService, Sportstalk247KitCommentModerationService, Sportstalk247KitUserService, Sportstalk247KitKotlinSuspendFunction1, Sportstalk247KitKotlinx_serialization_coreKSerializer, Sportstalk247KitKotlinComparable, Sportstalk247KitCommonParcelable, Sportstalk247KitKotlinx_coroutines_coreFlowCollector, Sportstalk247KitKotlinFunction, Sportstalk247KitKotlinx_serialization_coreEncoder, Sportstalk247KitKotlinx_serialization_coreSerialDescriptor, Sportstalk247KitKotlinx_serialization_coreSerializationStrategy, Sportstalk247KitKotlinx_serialization_coreDecoder, Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy, Sportstalk247KitKotlinIterator, Sportstalk247KitKotlinx_serialization_coreCompositeEncoder, Sportstalk247KitKotlinAnnotation, Sportstalk247KitKotlinx_serialization_coreCompositeDecoder, Sportstalk247KitKotlinx_serialization_coreSerializersModuleCollector, Sportstalk247KitKotlinKClass, Sportstalk247KitKotlinKDeclarationContainer, Sportstalk247KitKotlinKAnnotatedElement, Sportstalk247KitKotlinKClassifier;

NS_ASSUME_NONNULL_BEGIN
#pragma clang diagnostic push
#pragma clang diagnostic ignored "-Wunknown-warning-option"
#pragma clang diagnostic ignored "-Wincompatible-property-type"
#pragma clang diagnostic ignored "-Wnullability"

#pragma push_macro("_Nullable_result")
#if !__has_feature(nullability_nullable_result)
#undef _Nullable_result
#define _Nullable_result _Nullable
#endif

__attribute__((swift_name("KotlinBase")))
@interface Sportstalk247KitBase : NSObject
- (instancetype)init __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
+ (void)initialize __attribute__((objc_requires_super));
@end

@interface Sportstalk247KitBase (Sportstalk247KitBaseCopying) <NSCopying>
@end

__attribute__((swift_name("KotlinMutableSet")))
@interface Sportstalk247KitMutableSet<ObjectType> : NSMutableSet<ObjectType>
@end

__attribute__((swift_name("KotlinMutableDictionary")))
@interface Sportstalk247KitMutableDictionary<KeyType, ObjectType> : NSMutableDictionary<KeyType, ObjectType>
@end

@interface NSError (NSErrorSportstalk247KitKotlinException)
@property (readonly) id _Nullable kotlinException;
@end

__attribute__((swift_name("KotlinNumber")))
@interface Sportstalk247KitNumber : NSNumber
- (instancetype)initWithChar:(char)value __attribute__((unavailable));
- (instancetype)initWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
- (instancetype)initWithShort:(short)value __attribute__((unavailable));
- (instancetype)initWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
- (instancetype)initWithInt:(int)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
- (instancetype)initWithLong:(long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
- (instancetype)initWithLongLong:(long long)value __attribute__((unavailable));
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
- (instancetype)initWithFloat:(float)value __attribute__((unavailable));
- (instancetype)initWithDouble:(double)value __attribute__((unavailable));
- (instancetype)initWithBool:(BOOL)value __attribute__((unavailable));
- (instancetype)initWithInteger:(NSInteger)value __attribute__((unavailable));
- (instancetype)initWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
+ (instancetype)numberWithChar:(char)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedChar:(unsigned char)value __attribute__((unavailable));
+ (instancetype)numberWithShort:(short)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedShort:(unsigned short)value __attribute__((unavailable));
+ (instancetype)numberWithInt:(int)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInt:(unsigned int)value __attribute__((unavailable));
+ (instancetype)numberWithLong:(long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLong:(unsigned long)value __attribute__((unavailable));
+ (instancetype)numberWithLongLong:(long long)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value __attribute__((unavailable));
+ (instancetype)numberWithFloat:(float)value __attribute__((unavailable));
+ (instancetype)numberWithDouble:(double)value __attribute__((unavailable));
+ (instancetype)numberWithBool:(BOOL)value __attribute__((unavailable));
+ (instancetype)numberWithInteger:(NSInteger)value __attribute__((unavailable));
+ (instancetype)numberWithUnsignedInteger:(NSUInteger)value __attribute__((unavailable));
@end

__attribute__((swift_name("KotlinByte")))
@interface Sportstalk247KitByte : Sportstalk247KitNumber
- (instancetype)initWithChar:(char)value;
+ (instancetype)numberWithChar:(char)value;
@end

__attribute__((swift_name("KotlinUByte")))
@interface Sportstalk247KitUByte : Sportstalk247KitNumber
- (instancetype)initWithUnsignedChar:(unsigned char)value;
+ (instancetype)numberWithUnsignedChar:(unsigned char)value;
@end

__attribute__((swift_name("KotlinShort")))
@interface Sportstalk247KitShort : Sportstalk247KitNumber
- (instancetype)initWithShort:(short)value;
+ (instancetype)numberWithShort:(short)value;
@end

__attribute__((swift_name("KotlinUShort")))
@interface Sportstalk247KitUShort : Sportstalk247KitNumber
- (instancetype)initWithUnsignedShort:(unsigned short)value;
+ (instancetype)numberWithUnsignedShort:(unsigned short)value;
@end

__attribute__((swift_name("KotlinInt")))
@interface Sportstalk247KitInt : Sportstalk247KitNumber
- (instancetype)initWithInt:(int)value;
+ (instancetype)numberWithInt:(int)value;
@end

__attribute__((swift_name("KotlinUInt")))
@interface Sportstalk247KitUInt : Sportstalk247KitNumber
- (instancetype)initWithUnsignedInt:(unsigned int)value;
+ (instancetype)numberWithUnsignedInt:(unsigned int)value;
@end

__attribute__((swift_name("KotlinLong")))
@interface Sportstalk247KitLong : Sportstalk247KitNumber
- (instancetype)initWithLongLong:(long long)value;
+ (instancetype)numberWithLongLong:(long long)value;
@end

__attribute__((swift_name("KotlinULong")))
@interface Sportstalk247KitULong : Sportstalk247KitNumber
- (instancetype)initWithUnsignedLongLong:(unsigned long long)value;
+ (instancetype)numberWithUnsignedLongLong:(unsigned long long)value;
@end

__attribute__((swift_name("KotlinFloat")))
@interface Sportstalk247KitFloat : Sportstalk247KitNumber
- (instancetype)initWithFloat:(float)value;
+ (instancetype)numberWithFloat:(float)value;
@end

__attribute__((swift_name("KotlinDouble")))
@interface Sportstalk247KitDouble : Sportstalk247KitNumber
- (instancetype)initWithDouble:(double)value;
+ (instancetype)numberWithDouble:(double)value;
@end

__attribute__((swift_name("KotlinBoolean")))
@interface Sportstalk247KitBoolean : Sportstalk247KitNumber
- (instancetype)initWithBool:(BOOL)value;
+ (instancetype)numberWithBool:(BOOL)value;
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SportsTalk247")))
@interface Sportstalk247KitSportsTalk247 : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)sportsTalk247 __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSportsTalk247 *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitChatClient>)ChatClientConfig:(Sportstalk247KitClientConfig *)config __attribute__((swift_name("ChatClient(config:)")));
- (id<Sportstalk247KitCommentClient>)CommentClientConfig:(Sportstalk247KitClientConfig *)config __attribute__((swift_name("CommentClient(config:)")));
- (id<Sportstalk247KitUserClient>)UserClientConfig:(Sportstalk247KitClientConfig *)config __attribute__((swift_name("UserClient(config:)")));
- (void)setJWTProviderConfig:(Sportstalk247KitClientConfig *)config provider:(Sportstalk247KitJWTProvider *)provider __attribute__((swift_name("setJWTProvider(config:provider:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("WebRegexConstants")))
@interface Sportstalk247KitWebRegexConstants : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)webRegexConstants __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitWebRegexConstants *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *AUTOLINK_WEB_URL __attribute__((swift_name("AUTOLINK_WEB_URL")));
@property (readonly) NSString *IPV6_ADDRESS __attribute__((swift_name("IPV6_ADDRESS")));
@property (readonly) NSString *IP_ADDRESS __attribute__((swift_name("IP_ADDRESS")));
@property (readonly) NSString *WEB_URL __attribute__((swift_name("WEB_URL")));
@end

__attribute__((swift_name("ChatService")))
@protocol Sportstalk247KitChatService
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)bounceUserChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitBounceUserRequest *)request completionHandler:(void (^)(Sportstalk247KitBounceUserResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("bounceUser(chatRoomId:request:completionHandler:)")));
- (void)clearChatRoomEventUpdateCursorFromRoomId:(NSString *)fromRoomId __attribute__((swift_name("clearChatRoomEventUpdateCursor(fromRoomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRoomRequest:(Sportstalk247KitCreateChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRoom(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteRoomChatRoomId:(NSString *)chatRoomId completionHandler:(void (^)(Sportstalk247KitDeleteChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteRoom(chatRoomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeChatCommandChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitExecuteChatCommandRequest *)request completionHandler:(void (^)(Sportstalk247KitExecuteChatCommandResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("executeChatCommand(chatRoomId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)exitRoomChatRoomId:(NSString *)chatRoomId userId:(NSString *)userId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("exitRoom(chatRoomId:userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)flagEventLogicallyDeletedChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId userid:(NSString *)userid deleted:(BOOL)deleted permanentifnoreplies:(Sportstalk247KitBoolean * _Nullable)permanentifnoreplies completionHandler:(void (^)(Sportstalk247KitDeleteEventResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("flagEventLogicallyDeleted(chatRoomId:eventId:userid:deleted:permanentifnoreplies:completionHandler:)")));
- (NSString * _Nullable)getChatRoomEventUpdateCursorForRoomId:(NSString *)forRoomId __attribute__((swift_name("getChatRoomEventUpdateCursor(forRoomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getEventByIdChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getEventById(chatRoomId:eventId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomDetailsChatRoomId:(NSString *)chatRoomId completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomDetails(chatRoomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomDetailsByCustomIdChatRoomCustomId:(NSString *)chatRoomCustomId completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomDetailsByCustomId(chatRoomCustomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomDetailsExtendedBatchEntityTypes:(NSArray<Sportstalk247KitRoomDetailEntityType *> *)entityTypes roomIds:(NSArray<NSString *> *)roomIds customIds:(NSArray<NSString *> *)customIds completionHandler:(void (^)(Sportstalk247KitGetRoomDetailsExtendedBatchResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomDetailsExtendedBatch(entityTypes:roomIds:customIds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUpdatesChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitGetUpdatesResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getUpdates(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinRoomChatRoomIdOrLabel:(NSString *)chatRoomIdOrLabel completionHandler:(void (^)(Sportstalk247KitJoinChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("joinRoom(chatRoomIdOrLabel:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinRoomChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitJoinChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitJoinChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("joinRoom(chatRoomId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinRoomByCustomIdChatRoomCustomId:(NSString *)chatRoomCustomId request:(Sportstalk247KitJoinChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitJoinChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("joinRoomByCustomId(chatRoomCustomId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listEventsByTimestampChatRoomId:(NSString *)chatRoomId timestamp:(int64_t)timestamp limitolder:(Sportstalk247KitInt * _Nullable)limitolder limitnewer:(Sportstalk247KitInt * _Nullable)limitnewer completionHandler:(void (^)(Sportstalk247KitListEventsByTimestamp * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listEventsByTimestamp(chatRoomId:timestamp:limitolder:limitnewer:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listEventsByTypeChatRoomId:(NSString *)chatRoomId eventType:(NSString *)eventType customtype:(NSString * _Nullable)customtype limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListEvents * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listEventsByType(chatRoomId:eventType:customtype:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listEventsHistoryChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListEvents * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listEventsHistory(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listMessagesByUserChatRoomId:(NSString *)chatRoomId userId:(NSString *)userId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListMessagesByUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listMessagesByUser(chatRoomId:userId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listPreviousEventsChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListEvents * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listPreviousEvents(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRoomParticipantsChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListChatRoomParticipantsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listRoomParticipants(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRoomsLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListRoomsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listRooms(limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listUserSubscribedRoomsUserid:(NSString *)userid limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListUserSubscribedRoomsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listUserSubscribedRooms(userid:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)messageIsReactedToWhich:(Sportstalk247KitChatEvent *)which userid:(NSString *)userid reaction:(NSString *)reaction completionHandler:(void (^)(Sportstalk247KitBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("messageIsReactedTo(which:userid:reaction:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)messageIsReportedWhich:(Sportstalk247KitChatEvent *)which userid:(NSString *)userid completionHandler:(void (^)(Sportstalk247KitBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("messageIsReported(which:userid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)muteUserChatRoomId:(NSString *)chatRoomId userid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("muteUser(chatRoomId:userid:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)permanentlyDeleteEventChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId userid:(NSString *)userid completionHandler:(void (^)(Sportstalk247KitDeleteEventResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("permanentlyDeleteEvent(chatRoomId:eventId:userid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reactToEventChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId request:(Sportstalk247KitReactToAMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reactToEvent(chatRoomId:eventId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportMessageChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId request:(Sportstalk247KitReportMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportMessage(chatRoomId:eventId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportUserInRoomChatRoomId:(NSString *)chatRoomId userid:(NSString *)userid reporterid:(NSString *)reporterid reporttype:(NSString *)reporttype completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportUserInRoom(chatRoomId:userid:reporterid:reporttype:completionHandler:)")));
- (NSSet<NSString *> *)roomSubscriptions __attribute__((swift_name("roomSubscriptions()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)searchEventHistoryRequest:(Sportstalk247KitSearchEventHistoryRequest *)request completionHandler:(void (^)(Sportstalk247KitSearchEventHistoryResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("searchEventHistory(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendQuotedReplyChatRoomId:(NSString *)chatRoomId replyTo:(NSString *)replyTo request:(Sportstalk247KitSendQuotedReplyRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendQuotedReply(chatRoomId:replyTo:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendThreadedReplyChatRoomId:(NSString *)chatRoomId replyTo:(NSString *)replyTo request:(Sportstalk247KitSendThreadedReplyRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendThreadedReply(chatRoomId:replyTo:request:completionHandler:)")));
- (void)setChatRoomEventUpdateCursorForRoomId:(NSString *)forRoomId cursor:(NSString *)cursor __attribute__((swift_name("setChatRoomEventUpdateCursor(forRoomId:cursor:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)shadowBanUserChatRoomId:(NSString *)chatRoomId userid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("shadowBanUser(chatRoomId:userid:applyeffect:expireseconds:completionHandler:)")));
- (void)startListeningToChatUpdatesForRoomId:(NSString *)forRoomId __attribute__((swift_name("startListeningToChatUpdates(forRoomId:)")));
- (void)stopListeningToChatUpdatesForRoomId:(NSString *)forRoomId __attribute__((swift_name("stopListeningToChatUpdates(forRoomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)touchSessionChatRoomId:(NSString *)chatRoomId userId:(NSString *)userId completionHandler:(void (^)(Sportstalk247KitChatSubscription * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("touchSession(chatRoomId:userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateChatMessageChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId request:(Sportstalk247KitUpdateChatMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateChatMessage(chatRoomId:eventId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRoomChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitUpdateChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRoom(chatRoomId:request:completionHandler:)")));
@property id<Sportstalk247KitKotlinx_coroutines_coreFlow> chatEventsEmitter __attribute__((swift_name("chatEventsEmitter")));
@property Sportstalk247KitUser * _Nullable currentUser __attribute__((swift_name("currentUser")));
@property Sportstalk247KitMutableSet<NSString *> *preRenderedMessages __attribute__((swift_name("preRenderedMessages")));
@end

__attribute__((swift_name("ChatModerationService")))
@protocol Sportstalk247KitChatModerationService
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)approveMessageEventId:(NSString *)eventId approve:(BOOL)approve completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("approveMessage(eventId:approve:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listMessagesNeedingModerationRoomId:(NSString * _Nullable)roomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListMessagesNeedingModerationResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listMessagesNeedingModeration(roomId:limit:cursor:completionHandler:)")));
@end

__attribute__((swift_name("ChatClient")))
@protocol Sportstalk247KitChatClient <Sportstalk247KitChatService, Sportstalk247KitChatModerationService>
@required
@property Sportstalk247KitChatRoom * _Nullable currentRoom __attribute__((swift_name("currentRoom")));
@property NSString * _Nullable defaultImageBannerUrl __attribute__((swift_name("defaultImageBannerUrl")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatClientImpl")))
@interface Sportstalk247KitChatClientImpl : Sportstalk247KitBase <Sportstalk247KitChatClient>
@property (class, readonly, getter=companion) Sportstalk247KitChatClientImplCompanion *companion __attribute__((swift_name("companion")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)approveMessageEventId:(NSString *)eventId approve:(BOOL)approve completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("approveMessage(eventId:approve:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)bounceUserChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitBounceUserRequest *)request completionHandler:(void (^)(Sportstalk247KitBounceUserResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("bounceUser(chatRoomId:request:completionHandler:)")));
- (void)clearChatRoomEventUpdateCursorFromRoomId:(NSString *)fromRoomId __attribute__((swift_name("clearChatRoomEventUpdateCursor(fromRoomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createRoomRequest:(Sportstalk247KitCreateChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createRoom(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteRoomChatRoomId:(NSString *)chatRoomId completionHandler:(void (^)(Sportstalk247KitDeleteChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteRoom(chatRoomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)executeChatCommandChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitExecuteChatCommandRequest *)request completionHandler:(void (^)(Sportstalk247KitExecuteChatCommandResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("executeChatCommand(chatRoomId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)exitRoomChatRoomId:(NSString *)chatRoomId userId:(NSString *)userId completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("exitRoom(chatRoomId:userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)flagEventLogicallyDeletedChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId userid:(NSString *)userid deleted:(BOOL)deleted permanentifnoreplies:(Sportstalk247KitBoolean * _Nullable)permanentifnoreplies completionHandler:(void (^)(Sportstalk247KitDeleteEventResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("flagEventLogicallyDeleted(chatRoomId:eventId:userid:deleted:permanentifnoreplies:completionHandler:)")));
- (NSString * _Nullable)getChatRoomEventUpdateCursorForRoomId:(NSString *)forRoomId __attribute__((swift_name("getChatRoomEventUpdateCursor(forRoomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getEventByIdChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getEventById(chatRoomId:eventId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomDetailsChatRoomId:(NSString *)chatRoomId completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomDetails(chatRoomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomDetailsByCustomIdChatRoomCustomId:(NSString *)chatRoomCustomId completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomDetailsByCustomId(chatRoomCustomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getRoomDetailsExtendedBatchEntityTypes:(NSArray<Sportstalk247KitRoomDetailEntityType *> *)entityTypes roomIds:(NSArray<NSString *> *)roomIds customIds:(NSArray<NSString *> *)customIds completionHandler:(void (^)(Sportstalk247KitGetRoomDetailsExtendedBatchResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getRoomDetailsExtendedBatch(entityTypes:roomIds:customIds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUpdatesChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitGetUpdatesResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getUpdates(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinRoomChatRoomIdOrLabel:(NSString *)chatRoomIdOrLabel completionHandler:(void (^)(Sportstalk247KitJoinChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("joinRoom(chatRoomIdOrLabel:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinRoomChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitJoinChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitJoinChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("joinRoom(chatRoomId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)joinRoomByCustomIdChatRoomCustomId:(NSString *)chatRoomCustomId request:(Sportstalk247KitJoinChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitJoinChatRoomResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("joinRoomByCustomId(chatRoomCustomId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listEventsByTimestampChatRoomId:(NSString *)chatRoomId timestamp:(int64_t)timestamp limitolder:(Sportstalk247KitInt * _Nullable)limitolder limitnewer:(Sportstalk247KitInt * _Nullable)limitnewer completionHandler:(void (^)(Sportstalk247KitListEventsByTimestamp * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listEventsByTimestamp(chatRoomId:timestamp:limitolder:limitnewer:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listEventsByTypeChatRoomId:(NSString *)chatRoomId eventType:(NSString *)eventType customtype:(NSString * _Nullable)customtype limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListEvents * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listEventsByType(chatRoomId:eventType:customtype:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listEventsHistoryChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListEvents * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listEventsHistory(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listMessagesByUserChatRoomId:(NSString *)chatRoomId userId:(NSString *)userId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListMessagesByUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listMessagesByUser(chatRoomId:userId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listMessagesNeedingModerationRoomId:(NSString * _Nullable)roomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListMessagesNeedingModerationResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listMessagesNeedingModeration(roomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listPreviousEventsChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListEvents * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listPreviousEvents(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRoomParticipantsChatRoomId:(NSString *)chatRoomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListChatRoomParticipantsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listRoomParticipants(chatRoomId:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRoomsLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListRoomsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listRooms(limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listUserSubscribedRoomsUserid:(NSString *)userid limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListUserSubscribedRoomsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listUserSubscribedRooms(userid:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)messageIsReactedToWhich:(Sportstalk247KitChatEvent *)which userid:(NSString *)userid reaction:(NSString *)reaction completionHandler:(void (^)(Sportstalk247KitBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("messageIsReactedTo(which:userid:reaction:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)messageIsReportedWhich:(Sportstalk247KitChatEvent *)which userid:(NSString *)userid completionHandler:(void (^)(Sportstalk247KitBoolean * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("messageIsReported(which:userid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)muteUserChatRoomId:(NSString *)chatRoomId userid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("muteUser(chatRoomId:userid:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)permanentlyDeleteEventChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId userid:(NSString *)userid completionHandler:(void (^)(Sportstalk247KitDeleteEventResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("permanentlyDeleteEvent(chatRoomId:eventId:userid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reactToEventChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId request:(Sportstalk247KitReactToAMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reactToEvent(chatRoomId:eventId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportMessageChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId request:(Sportstalk247KitReportMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportMessage(chatRoomId:eventId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportUserInRoomChatRoomId:(NSString *)chatRoomId userid:(NSString *)userid reporterid:(NSString *)reporterid reporttype:(NSString *)reporttype completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportUserInRoom(chatRoomId:userid:reporterid:reporttype:completionHandler:)")));
- (NSSet<NSString *> *)roomSubscriptions __attribute__((swift_name("roomSubscriptions()")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)searchEventHistoryRequest:(Sportstalk247KitSearchEventHistoryRequest *)request completionHandler:(void (^)(Sportstalk247KitSearchEventHistoryResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("searchEventHistory(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendQuotedReplyChatRoomId:(NSString *)chatRoomId replyTo:(NSString *)replyTo request:(Sportstalk247KitSendQuotedReplyRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendQuotedReply(chatRoomId:replyTo:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)sendThreadedReplyChatRoomId:(NSString *)chatRoomId replyTo:(NSString *)replyTo request:(Sportstalk247KitSendThreadedReplyRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("sendThreadedReply(chatRoomId:replyTo:request:completionHandler:)")));
- (void)setChatRoomEventUpdateCursorForRoomId:(NSString *)forRoomId cursor:(NSString *)cursor __attribute__((swift_name("setChatRoomEventUpdateCursor(forRoomId:cursor:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)shadowBanUserChatRoomId:(NSString *)chatRoomId userid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("shadowBanUser(chatRoomId:userid:applyeffect:expireseconds:completionHandler:)")));
- (void)startListeningToChatUpdatesForRoomId:(NSString *)forRoomId __attribute__((swift_name("startListeningToChatUpdates(forRoomId:)")));
- (void)stopListeningToChatUpdatesForRoomId:(NSString *)forRoomId __attribute__((swift_name("stopListeningToChatUpdates(forRoomId:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)touchSessionChatRoomId:(NSString *)chatRoomId userId:(NSString *)userId completionHandler:(void (^)(Sportstalk247KitChatSubscription * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("touchSession(chatRoomId:userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateChatMessageChatRoomId:(NSString *)chatRoomId eventId:(NSString *)eventId request:(Sportstalk247KitUpdateChatMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateChatMessage(chatRoomId:eventId:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateRoomChatRoomId:(NSString *)chatRoomId request:(Sportstalk247KitUpdateChatRoomRequest *)request completionHandler:(void (^)(Sportstalk247KitChatRoom * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateRoom(chatRoomId:request:completionHandler:)")));
@property id<Sportstalk247KitKotlinx_coroutines_coreFlow> chatEventsEmitter __attribute__((swift_name("chatEventsEmitter")));
@property Sportstalk247KitChatRoom * _Nullable currentRoom __attribute__((swift_name("currentRoom")));
@property Sportstalk247KitUser * _Nullable currentUser __attribute__((swift_name("currentUser")));
@property NSString * _Nullable defaultImageBannerUrl __attribute__((swift_name("defaultImageBannerUrl")));
@property Sportstalk247KitMutableSet<NSString *> *preRenderedMessages __attribute__((swift_name("preRenderedMessages")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatClientImpl.Companion")))
@interface Sportstalk247KitChatClientImplCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitChatClientImplCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatModerationRestApiServiceImpl")))
@interface Sportstalk247KitChatModerationRestApiServiceImpl : Sportstalk247KitBase <Sportstalk247KitChatModerationService>

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)approveMessageEventId:(NSString *)eventId approve:(BOOL)approve completionHandler:(void (^)(Sportstalk247KitChatEvent * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("approveMessage(eventId:approve:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listMessagesNeedingModerationRoomId:(NSString * _Nullable)roomId limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListMessagesNeedingModerationResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listMessagesNeedingModeration(roomId:limit:cursor:completionHandler:)")));
@end

__attribute__((swift_name("CommentService")))
@protocol Sportstalk247KitCommentService
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)batchGetConversationDetailsIds:(NSArray<NSString *> * _Nullable)ids cid:(NSArray<NSString *> * _Nullable)cid entities:(NSArray<Sportstalk247KitBatchGetConversationEntity *> * _Nullable)entities completionHandler:(void (^)(Sportstalk247KitBatchGetConversationDetailsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("batchGetConversationDetails(ids:cid:entities:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createCommentConversationid:(NSString *)conversationid request:(Sportstalk247KitCreateCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createComment(conversationid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrUpdateConversationRequest:(Sportstalk247KitCreateOrUpdateConversationRequest *)request completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrUpdateConversation(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteConversationConversationid:(NSString *)conversationid completionHandler:(void (^)(Sportstalk247KitDeleteConversationResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteConversation(conversationid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)flagCommentLogicallyDeletedConversationid:(NSString *)conversationid commentid:(NSString *)commentid userid:(NSString *)userid deleted:(BOOL)deleted permanentifnoreplies:(Sportstalk247KitBoolean * _Nullable)permanentifnoreplies completionHandler:(void (^)(Sportstalk247KitDeleteCommentResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("flagCommentLogicallyDeleted(conversationid:commentid:userid:deleted:permanentifnoreplies:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getComment(conversationid:commentid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getConversationConversationid:(NSString *)conversationid completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getConversation(conversationid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getConversationByCustomIdCustomid:(NSString *)customid completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getConversationByCustomId(customid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listCommentsConversationid:(NSString *)conversationid cursor:(NSString * _Nullable)cursor limit:(Sportstalk247KitInt * _Nullable)limit direction:(Sportstalk247KitDirectionType * _Nullable)direction sort:(Sportstalk247KitSortType * _Nullable)sort includechildren:(Sportstalk247KitBoolean * _Nullable)includechildren includeinactive:(Sportstalk247KitBoolean * _Nullable)includeinactive completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listComments(conversationid:cursor:limit:direction:sort:includechildren:includeinactive:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listConversationsPropertyid:(NSString * _Nullable)propertyid cursor:(NSString * _Nullable)cursor limit:(Sportstalk247KitInt * _Nullable)limit sort:(Sportstalk247KitSortType * _Nullable)sort completionHandler:(void (^)(Sportstalk247KitListConversations * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listConversations(propertyid:cursor:limit:sort:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRepliesConversationid:(NSString *)conversationid commentid:(NSString *)commentid cursor:(NSString * _Nullable)cursor limit:(Sportstalk247KitInt * _Nullable)limit direction:(Sportstalk247KitDirectionType * _Nullable)direction sort:(Sportstalk247KitSortType * _Nullable)sort includechildren:(Sportstalk247KitBoolean * _Nullable)includechildren includeinactive:(Sportstalk247KitBoolean * _Nullable)includeinactive completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listReplies(conversationid:commentid:cursor:limit:direction:sort:includechildren:includeinactive:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRepliesBatchConversationid:(NSString *)conversationid childlimit:(Sportstalk247KitInt * _Nullable)childlimit parentids:(NSArray<NSString *> *)parentids includeinactive:(Sportstalk247KitBoolean * _Nullable)includeinactive completionHandler:(void (^)(Sportstalk247KitListRepliesBatchResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listRepliesBatch(conversationid:childlimit:parentids:includeinactive:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)permanentlyDeleteCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid completionHandler:(void (^)(Sportstalk247KitDeleteCommentResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("permanentlyDeleteComment(conversationid:commentid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reactToCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitReactToCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reactToComment(conversationid:commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reactToConversationTopicConversationid:(NSString *)conversationid request:(Sportstalk247KitReactToConversationTopicRequest *)request completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reactToConversationTopic(conversationid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)replyToCommentConversationid:(NSString *)conversationid replyto:(NSString *)replyto request:(Sportstalk247KitCreateCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("replyToComment(conversationid:replyto:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitReportCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportComment(conversationid:commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitUpdateCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateComment(conversationid:commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)voteOnCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitVoteOnCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("voteOnComment(conversationid:commentid:request:completionHandler:)")));
@end

__attribute__((swift_name("CommentModerationService")))
@protocol Sportstalk247KitCommentModerationService
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)approveMessageInQueueCommentid:(NSString *)commentid request:(Sportstalk247KitApproveMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("approveMessageInQueue(commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listCommentsInModerationQueueLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor conversationid:(NSString * _Nullable)conversationid filterHandle:(NSString * _Nullable)filterHandle filterKeyword:(NSString * _Nullable)filterKeyword filterModerationState:(Sportstalk247KitCommentFilterModerationState * _Nullable)filterModerationState completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listCommentsInModerationQueue(limit:cursor:conversationid:filterHandle:filterKeyword:filterModerationState:completionHandler:)")));
@end

__attribute__((swift_name("CommentClient")))
@protocol Sportstalk247KitCommentClient <Sportstalk247KitCommentService, Sportstalk247KitCommentModerationService>
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommentClientImpl")))
@interface Sportstalk247KitCommentClientImpl : Sportstalk247KitBase <Sportstalk247KitCommentClient>

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)approveMessageInQueueCommentid:(NSString *)commentid request:(Sportstalk247KitApproveMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("approveMessageInQueue(commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)batchGetConversationDetailsIds:(NSArray<NSString *> * _Nullable)ids cid:(NSArray<NSString *> * _Nullable)cid entities:(NSArray<Sportstalk247KitBatchGetConversationEntity *> * _Nullable)entities completionHandler:(void (^)(Sportstalk247KitBatchGetConversationDetailsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("batchGetConversationDetails(ids:cid:entities:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createCommentConversationid:(NSString *)conversationid request:(Sportstalk247KitCreateCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createComment(conversationid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrUpdateConversationRequest:(Sportstalk247KitCreateOrUpdateConversationRequest *)request completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrUpdateConversation(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteConversationConversationid:(NSString *)conversationid completionHandler:(void (^)(Sportstalk247KitDeleteConversationResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteConversation(conversationid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)flagCommentLogicallyDeletedConversationid:(NSString *)conversationid commentid:(NSString *)commentid userid:(NSString *)userid deleted:(BOOL)deleted permanentifnoreplies:(Sportstalk247KitBoolean * _Nullable)permanentifnoreplies completionHandler:(void (^)(Sportstalk247KitDeleteCommentResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("flagCommentLogicallyDeleted(conversationid:commentid:userid:deleted:permanentifnoreplies:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getComment(conversationid:commentid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getConversationConversationid:(NSString *)conversationid completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getConversation(conversationid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getConversationByCustomIdCustomid:(NSString *)customid completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getConversationByCustomId(customid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listCommentsConversationid:(NSString *)conversationid cursor:(NSString * _Nullable)cursor limit:(Sportstalk247KitInt * _Nullable)limit direction:(Sportstalk247KitDirectionType * _Nullable)direction sort:(Sportstalk247KitSortType * _Nullable)sort includechildren:(Sportstalk247KitBoolean * _Nullable)includechildren includeinactive:(Sportstalk247KitBoolean * _Nullable)includeinactive completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listComments(conversationid:cursor:limit:direction:sort:includechildren:includeinactive:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listCommentsInModerationQueueLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor conversationid:(NSString * _Nullable)conversationid filterHandle:(NSString * _Nullable)filterHandle filterKeyword:(NSString * _Nullable)filterKeyword filterModerationState:(Sportstalk247KitCommentFilterModerationState * _Nullable)filterModerationState completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listCommentsInModerationQueue(limit:cursor:conversationid:filterHandle:filterKeyword:filterModerationState:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listConversationsPropertyid:(NSString * _Nullable)propertyid cursor:(NSString * _Nullable)cursor limit:(Sportstalk247KitInt * _Nullable)limit sort:(Sportstalk247KitSortType * _Nullable)sort completionHandler:(void (^)(Sportstalk247KitListConversations * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listConversations(propertyid:cursor:limit:sort:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRepliesConversationid:(NSString *)conversationid commentid:(NSString *)commentid cursor:(NSString * _Nullable)cursor limit:(Sportstalk247KitInt * _Nullable)limit direction:(Sportstalk247KitDirectionType * _Nullable)direction sort:(Sportstalk247KitSortType * _Nullable)sort includechildren:(Sportstalk247KitBoolean * _Nullable)includechildren includeinactive:(Sportstalk247KitBoolean * _Nullable)includeinactive completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listReplies(conversationid:commentid:cursor:limit:direction:sort:includechildren:includeinactive:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listRepliesBatchConversationid:(NSString *)conversationid childlimit:(Sportstalk247KitInt * _Nullable)childlimit parentids:(NSArray<NSString *> *)parentids includeinactive:(Sportstalk247KitBoolean * _Nullable)includeinactive completionHandler:(void (^)(Sportstalk247KitListRepliesBatchResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listRepliesBatch(conversationid:childlimit:parentids:includeinactive:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)permanentlyDeleteCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid completionHandler:(void (^)(Sportstalk247KitDeleteCommentResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("permanentlyDeleteComment(conversationid:commentid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reactToCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitReactToCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reactToComment(conversationid:commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reactToConversationTopicConversationid:(NSString *)conversationid request:(Sportstalk247KitReactToConversationTopicRequest *)request completionHandler:(void (^)(Sportstalk247KitConversation * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reactToConversationTopic(conversationid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)replyToCommentConversationid:(NSString *)conversationid replyto:(NSString *)replyto request:(Sportstalk247KitCreateCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("replyToComment(conversationid:replyto:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitReportCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportComment(conversationid:commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)updateCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitUpdateCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("updateComment(conversationid:commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)voteOnCommentConversationid:(NSString *)conversationid commentid:(NSString *)commentid request:(Sportstalk247KitVoteOnCommentRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("voteOnComment(conversationid:commentid:request:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommentModerationRestApiServiceImpl")))
@interface Sportstalk247KitCommentModerationRestApiServiceImpl : Sportstalk247KitBase <Sportstalk247KitCommentModerationService>

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)approveMessageInQueueCommentid:(NSString *)commentid request:(Sportstalk247KitApproveMessageRequest *)request completionHandler:(void (^)(Sportstalk247KitComment * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("approveMessageInQueue(commentid:request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listCommentsInModerationQueueLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor conversationid:(NSString * _Nullable)conversationid filterHandle:(NSString * _Nullable)filterHandle filterKeyword:(NSString * _Nullable)filterKeyword filterModerationState:(Sportstalk247KitCommentFilterModerationState * _Nullable)filterModerationState completionHandler:(void (^)(Sportstalk247KitListComments * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listCommentsInModerationQueue(limit:cursor:conversationid:filterHandle:filterKeyword:filterModerationState:completionHandler:)")));
@end

__attribute__((swift_name("UserService")))
@protocol Sportstalk247KitUserService
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrUpdateUserRequest:(Sportstalk247KitCreateUpdateUserRequest *)request completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrUpdateUser(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUserUserId:(NSString *)userId completionHandler:(void (^)(Sportstalk247KitDeleteUserResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteUser(userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUserNotificationUserId:(NSString *)userId notificationId:(NSString *)notificationId completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteUserNotification(userId:notificationId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUserNotificationByChatEventUserId:(NSString *)userId chatEventId:(NSString *)chatEventId completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteUserNotificationByChatEvent(userId:chatEventId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUserDetailsUserId:(NSString *)userId completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getUserDetails(userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)globallyPurgeUserContentUserId:(NSString *)userId byuserid:(NSString *)byuserid completionHandler:(void (^)(Sportstalk247KitGloballyPurgeUserContentResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("globallyPurgeUserContent(userId:byuserid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listUserNotificationsUserId:(NSString *)userId limit:(int32_t)limit filterNotificationTypes:(NSArray<NSString *> * _Nullable)filterNotificationTypes cursor:(NSString * _Nullable)cursor includeread:(Sportstalk247KitBoolean * _Nullable)includeread filterChatRoomId:(NSString * _Nullable)filterChatRoomId filterChatRoomCustomId:(NSString * _Nullable)filterChatRoomCustomId completionHandler:(void (^)(Sportstalk247KitListUserNotificationsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listUserNotifications(userId:limit:filterNotificationTypes:cursor:includeread:filterChatRoomId:filterChatRoomCustomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listUsersLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListUsersResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listUsers(limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)markAllUserNotificationsAsReadUserid:(NSString *)userid delete:(BOOL)delete_ completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("markAllUserNotificationsAsRead(userid:delete:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)muteUserUserId:(NSString *)userId applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("muteUser(userId:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportUserUserId:(NSString *)userId reporttype:(NSString *)reporttype completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportUser(userId:reporttype:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)searchUsersHandle:(NSString * _Nullable)handle name:(NSString * _Nullable)name userid:(NSString * _Nullable)userid limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListUsersResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("searchUsers(handle:name:userid:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setBanStatusUserId:(NSString *)userId applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setBanStatus(userId:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setShadowBanStatusUserId:(NSString *)userId applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setShadowBanStatus(userId:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setUserNotificationAsReadUserId:(NSString *)userId notificationId:(NSString *)notificationId read:(BOOL)read completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setUserNotificationAsRead(userId:notificationId:read:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setUserNotificationAsReadByChatEventUserId:(NSString *)userId chatEventId:(NSString *)chatEventId read:(BOOL)read completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setUserNotificationAsReadByChatEvent(userId:chatEventId:read:completionHandler:)")));
@end

__attribute__((swift_name("UserClient")))
@protocol Sportstalk247KitUserClient <Sportstalk247KitUserService>
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserClientImpl")))
@interface Sportstalk247KitUserClientImpl : Sportstalk247KitBase <Sportstalk247KitUserClient>

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)createOrUpdateUserRequest:(Sportstalk247KitCreateUpdateUserRequest *)request completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("createOrUpdateUser(request:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUserUserId:(NSString *)userId completionHandler:(void (^)(Sportstalk247KitDeleteUserResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteUser(userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUserNotificationUserId:(NSString *)userId notificationId:(NSString *)notificationId completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteUserNotification(userId:notificationId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)deleteUserNotificationByChatEventUserId:(NSString *)userId chatEventId:(NSString *)chatEventId completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("deleteUserNotificationByChatEvent(userId:chatEventId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)getUserDetailsUserId:(NSString *)userId completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("getUserDetails(userId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)globallyPurgeUserContentUserId:(NSString *)userId byuserid:(NSString *)byuserid completionHandler:(void (^)(Sportstalk247KitGloballyPurgeUserContentResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("globallyPurgeUserContent(userId:byuserid:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listUserNotificationsUserId:(NSString *)userId limit:(int32_t)limit filterNotificationTypes:(NSArray<NSString *> * _Nullable)filterNotificationTypes cursor:(NSString * _Nullable)cursor includeread:(Sportstalk247KitBoolean * _Nullable)includeread filterChatRoomId:(NSString * _Nullable)filterChatRoomId filterChatRoomCustomId:(NSString * _Nullable)filterChatRoomCustomId completionHandler:(void (^)(Sportstalk247KitListUserNotificationsResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listUserNotifications(userId:limit:filterNotificationTypes:cursor:includeread:filterChatRoomId:filterChatRoomCustomId:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)listUsersLimit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListUsersResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("listUsers(limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)markAllUserNotificationsAsReadUserid:(NSString *)userid delete:(BOOL)delete_ completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("markAllUserNotificationsAsRead(userid:delete:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)muteUserUserId:(NSString *)userId applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("muteUser(userId:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)reportUserUserId:(NSString *)userId reporttype:(NSString *)reporttype completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("reportUser(userId:reporttype:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)searchUsersHandle:(NSString * _Nullable)handle name:(NSString * _Nullable)name userid:(NSString * _Nullable)userid limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor completionHandler:(void (^)(Sportstalk247KitListUsersResponse * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("searchUsers(handle:name:userid:limit:cursor:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setBanStatusUserId:(NSString *)userId applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setBanStatus(userId:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setShadowBanStatusUserId:(NSString *)userId applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds completionHandler:(void (^)(Sportstalk247KitUser * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setShadowBanStatus(userId:applyeffect:expireseconds:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setUserNotificationAsReadUserId:(NSString *)userId notificationId:(NSString *)notificationId read:(BOOL)read completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setUserNotificationAsRead(userId:notificationId:read:completionHandler:)")));

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)setUserNotificationAsReadByChatEventUserId:(NSString *)userId chatEventId:(NSString *)chatEventId read:(BOOL)read completionHandler:(void (^)(Sportstalk247KitUserNotification * _Nullable, NSError * _Nullable))completionHandler __attribute__((swift_name("setUserNotificationAsReadByChatEvent(userId:chatEventId:read:completionHandler:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JWTProvider")))
@interface Sportstalk247KitJWTProvider : Sportstalk247KitBase
- (instancetype)initWithToken:(NSString * _Nullable)token tokenRefreshAction:(id<Sportstalk247KitKotlinSuspendFunction1> _Nullable)tokenRefreshAction __attribute__((swift_name("init(token:tokenRefreshAction:)"))) __attribute__((objc_designated_initializer));
- (NSString * _Nullable)getToken __attribute__((swift_name("getToken()")));
- (id<Sportstalk247KitKotlinx_coroutines_coreFlow>)observe __attribute__((swift_name("observe()")));
- (void)refreshToken __attribute__((swift_name("refreshToken()")));
- (void)setTokenValue:(NSString * _Nullable)value __attribute__((swift_name("setToken(value:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApiResponse")))
@interface Sportstalk247KitApiResponse<T> : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind message:(NSString * _Nullable)message code:(Sportstalk247KitInt * _Nullable)code data:(T _Nullable)data __attribute__((swift_name("init(kind:message:code:data:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitApiResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitApiResponse<T> *)doCopyKind:(NSString * _Nullable)kind message:(NSString * _Nullable)message code:(Sportstalk247KitInt * _Nullable)code data:(T _Nullable)data __attribute__((swift_name("doCopy(kind:message:code:data:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitInt * _Nullable code __attribute__((swift_name("code")));
@property (readonly) T _Nullable data __attribute__((swift_name("data")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApiResponseCompanion")))
@interface Sportstalk247KitApiResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitApiResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeSerial0:(id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)typeSerial0 __attribute__((swift_name("serializer(typeSerial0:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ClientConfig")))
@interface Sportstalk247KitClientConfig : Sportstalk247KitBase
- (instancetype)initWithAppId:(NSString *)appId apiToken:(NSString *)apiToken endpoint:(NSString *)endpoint __attribute__((swift_name("init(appId:apiToken:endpoint:)"))) __attribute__((objc_designated_initializer));
- (Sportstalk247KitClientConfig *)doCopyAppId:(NSString *)appId apiToken:(NSString *)apiToken endpoint:(NSString *)endpoint __attribute__((swift_name("doCopy(appId:apiToken:endpoint:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *apiToken __attribute__((swift_name("apiToken")));
@property (readonly) NSString *appId __attribute__((swift_name("appId")));
@property (readonly) NSString *endpoint __attribute__((swift_name("endpoint")));
@end

__attribute__((swift_name("CommonParcelable")))
@protocol Sportstalk247KitCommonParcelable
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kind")))
@interface Sportstalk247KitKind : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)kind __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitKind *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *API __attribute__((swift_name("API")));
@property (readonly) NSString *BATCH_CONVERSATION_DETAILS __attribute__((swift_name("BATCH_CONVERSATION_DETAILS")));
@property (readonly) NSString *BOUNCE_USER __attribute__((swift_name("BOUNCE_USER")));
@property (readonly) NSString *CHAT __attribute__((swift_name("CHAT")));
@property (readonly) NSString *CHAT_COMMAND __attribute__((swift_name("CHAT_COMMAND")));
@property (readonly) NSString *CHAT_LIST __attribute__((swift_name("CHAT_LIST")));
@property (readonly) NSString *CHAT_LIST_BY_TIMESTAMP __attribute__((swift_name("CHAT_LIST_BY_TIMESTAMP")));
@property (readonly) NSString *CHAT_LIST_PARTICIPANTS __attribute__((swift_name("CHAT_LIST_PARTICIPANTS")));
@property (readonly) NSString *CHAT_PARTICIPANT __attribute__((swift_name("CHAT_PARTICIPANT")));
@property (readonly) NSString *CHAT_ROOM_STATUS __attribute__((swift_name("CHAT_ROOM_STATUS")));
@property (readonly) NSString *CHAT_SUBSCRIPTION __attribute__((swift_name("CHAT_SUBSCRIPTION")));
@property (readonly) NSString *CHAT_SUBSCRIPTION_AND_STATUS __attribute__((swift_name("CHAT_SUBSCRIPTION_AND_STATUS")));
@property (readonly) NSString *COMMENT __attribute__((swift_name("COMMENT")));
@property (readonly) NSString *COMMENT_REPLIES_BY_PARENT __attribute__((swift_name("COMMENT_REPLIES_BY_PARENT")));
@property (readonly) NSString *COMMENT_REPLY_GROUP __attribute__((swift_name("COMMENT_REPLY_GROUP")));
@property (readonly) NSString *CONVERSATION __attribute__((swift_name("CONVERSATION")));
@property (readonly) NSString *CONVERSATION_LIST __attribute__((swift_name("CONVERSATION_LIST")));
@property (readonly) NSString *DELETED_COMMENT __attribute__((swift_name("DELETED_COMMENT")));
@property (readonly) NSString *DELETED_CONVERSATION __attribute__((swift_name("DELETED_CONVERSATION")));
@property (readonly) NSString *DELETED_ROOM __attribute__((swift_name("DELETED_ROOM")));
@property (readonly) NSString *DELETED_USER __attribute__((swift_name("DELETED_USER")));
@property (readonly) NSString *JOIN_ROOM __attribute__((swift_name("JOIN_ROOM")));
@property (readonly) NSString *LIST_COMMENTS __attribute__((swift_name("LIST_COMMENTS")));
@property (readonly) NSString *LIST_USER_NOTIFICATIONS __attribute__((swift_name("LIST_USER_NOTIFICATIONS")));
@property (readonly) NSString *LIST_USER_ROOM_SUBSCRIPTIONS __attribute__((swift_name("LIST_USER_ROOM_SUBSCRIPTIONS")));
@property (readonly) NSString *NOTIFICATION __attribute__((swift_name("NOTIFICATION")));
@property (readonly) NSString *ROOM __attribute__((swift_name("ROOM")));
@property (readonly) NSString *ROOM_EXTENDED_DETAILS __attribute__((swift_name("ROOM_EXTENDED_DETAILS")));
@property (readonly) NSString *ROOM_LIST __attribute__((swift_name("ROOM_LIST")));
@property (readonly) NSString *USER __attribute__((swift_name("USER")));
@property (readonly) NSString *USER_LIST __attribute__((swift_name("USER_LIST")));
@property (readonly) NSString *WEBHOOK __attribute__((swift_name("WEBHOOK")));
@property (readonly) NSString *WEBHOOK_COMMENT_PAYLOAD __attribute__((swift_name("WEBHOOK_COMMENT_PAYLOAD")));
@property (readonly) NSString *WEBHOOK_LOGS __attribute__((swift_name("WEBHOOK_LOGS")));
@end

__attribute__((swift_name("KotlinThrowable")))
@interface Sportstalk247KitKotlinThrowable : Sportstalk247KitBase
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (Sportstalk247KitKotlinArray<NSString *> *)getStackTrace __attribute__((swift_name("getStackTrace()")));
- (void)printStackTrace __attribute__((swift_name("printStackTrace()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitKotlinThrowable * _Nullable cause __attribute__((swift_name("cause")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
- (NSError *)asError __attribute__((swift_name("asError()")));
@end

__attribute__((swift_name("KotlinException")))
@interface Sportstalk247KitKotlinException : Sportstalk247KitKotlinThrowable
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SportsTalkException")))
@interface Sportstalk247KitSportsTalkException : Sportstalk247KitKotlinException
- (instancetype)initWithKind:(NSString * _Nullable)kind message:(NSString * _Nullable)message code:(Sportstalk247KitInt * _Nullable)code data:(NSDictionary<NSString *, Sportstalk247KitKotlinx_serialization_jsonJsonElement *> * _Nullable)data err:(Sportstalk247KitKotlinThrowable * _Nullable)err __attribute__((swift_name("init(kind:message:code:data:err:)"))) __attribute__((objc_designated_initializer));
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
+ (instancetype)new __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
- (instancetype)initWithCause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitSportsTalkExceptionCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSportsTalkException *)doCopyKind:(NSString * _Nullable)kind message:(NSString * _Nullable)message code:(Sportstalk247KitInt * _Nullable)code data:(NSDictionary<NSString *, Sportstalk247KitKotlinx_serialization_jsonJsonElement *> * _Nullable)data err:(Sportstalk247KitKotlinThrowable * _Nullable)err __attribute__((swift_name("doCopy(kind:message:code:data:err:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitInt * _Nullable code __attribute__((swift_name("code")));
@property (readonly) NSDictionary<NSString *, Sportstalk247KitKotlinx_serialization_jsonJsonElement *> * _Nullable data __attribute__((swift_name("data")));
@property (readonly) Sportstalk247KitKotlinThrowable * _Nullable err __attribute__((swift_name("err")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SportsTalkException.Companion")))
@interface Sportstalk247KitSportsTalkExceptionCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSportsTalkExceptionCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApproveMessageRequest")))
@interface Sportstalk247KitApproveMessageRequest : Sportstalk247KitBase
- (instancetype)initWithApprove:(BOOL)approve __attribute__((swift_name("init(approve:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitApproveMessageRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitApproveMessageRequest *)doCopyApprove:(BOOL)approve __attribute__((swift_name("doCopy(approve:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL approve __attribute__((swift_name("approve")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApproveMessageRequest.Companion")))
@interface Sportstalk247KitApproveMessageRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitApproveMessageRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BatchGetConversationDetailsResponse")))
@interface Sportstalk247KitBatchGetConversationDetailsResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind itemcount:(Sportstalk247KitLong * _Nullable)itemcount conversations:(NSArray<Sportstalk247KitConversation *> *)conversations __attribute__((swift_name("init(kind:itemcount:conversations:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitBatchGetConversationDetailsResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitBatchGetConversationDetailsResponse *)doCopyKind:(NSString * _Nullable)kind itemcount:(Sportstalk247KitLong * _Nullable)itemcount conversations:(NSArray<Sportstalk247KitConversation *> *)conversations __attribute__((swift_name("doCopy(kind:itemcount:conversations:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Sportstalk247KitConversation *> *conversations __attribute__((swift_name("conversations")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BatchGetConversationDetailsResponse.Companion")))
@interface Sportstalk247KitBatchGetConversationDetailsResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitBatchGetConversationDetailsResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((swift_name("KotlinComparable")))
@protocol Sportstalk247KitKotlinComparable
@required
- (int32_t)compareToOther:(id _Nullable)other __attribute__((swift_name("compareTo(other:)")));
@end

__attribute__((swift_name("KotlinEnum")))
@interface Sportstalk247KitKotlinEnum<E> : Sportstalk247KitBase <Sportstalk247KitKotlinComparable>
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitKotlinEnumCompanion *companion __attribute__((swift_name("companion")));
- (int32_t)compareToOther:(E)other __attribute__((swift_name("compareTo(other:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *name __attribute__((swift_name("name")));
@property (readonly) int32_t ordinal __attribute__((swift_name("ordinal")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BatchGetConversationEntity")))
@interface Sportstalk247KitBatchGetConversationEntity : Sportstalk247KitKotlinEnum<Sportstalk247KitBatchGetConversationEntity *> <Sportstalk247KitCommonParcelable>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitBatchGetConversationEntityCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) Sportstalk247KitBatchGetConversationEntity *reactions __attribute__((swift_name("reactions")));
@property (class, readonly) Sportstalk247KitBatchGetConversationEntity *likecount __attribute__((swift_name("likecount")));
@property (class, readonly) Sportstalk247KitBatchGetConversationEntity *commentcount __attribute__((swift_name("commentcount")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitBatchGetConversationEntity *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *rawValue __attribute__((swift_name("rawValue")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BatchGetConversationEntity.Companion")))
@interface Sportstalk247KitBatchGetConversationEntityCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitBatchGetConversationEntityCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Comment")))
@interface Sportstalk247KitComment : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id appid:(NSString * _Nullable)appid conversationid:(NSString * _Nullable)conversationid commenttype:(NSString * _Nullable)commenttype added:(NSString * _Nullable)added modified:(NSString * _Nullable)modified tsunix:(Sportstalk247KitLong * _Nullable)tsunix userid:(NSString * _Nullable)userid user:(Sportstalk247KitUser * _Nullable)user body:(NSString * _Nullable)body originalbody:(NSString * _Nullable)originalbody hashtags:(NSArray<NSString *> * _Nullable)hashtags shadowban:(Sportstalk247KitBoolean * _Nullable)shadowban customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customtags:(NSArray<NSString *> * _Nullable)customtags customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 edited:(Sportstalk247KitBoolean * _Nullable)edited censored:(Sportstalk247KitBoolean * _Nullable)censored deleted:(Sportstalk247KitBoolean * _Nullable)deleted parentid:(NSString * _Nullable)parentid hierarchy:(NSArray<NSString *> * _Nullable)hierarchy reactions:(NSArray<Sportstalk247KitReaction *> * _Nullable)reactions likecount:(Sportstalk247KitLong * _Nullable)likecount replycount:(Sportstalk247KitLong * _Nullable)replycount votecount:(Sportstalk247KitLong * _Nullable)votecount votescore:(Sportstalk247KitLong * _Nullable)votescore votes:(NSArray<Sportstalk247KitVoteDetail *> * _Nullable)votes moderation:(NSString * _Nullable)moderation active:(Sportstalk247KitBoolean * _Nullable)active reports:(NSArray<Sportstalk247KitReport *> * _Nullable)reports __attribute__((swift_name("init(kind:id:appid:conversationid:commenttype:added:modified:tsunix:userid:user:body:originalbody:hashtags:shadowban:customtype:customid:custompayload:customtags:customfield1:customfield2:edited:censored:deleted:parentid:hierarchy:reactions:likecount:replycount:votecount:votescore:votes:moderation:active:reports:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitCommentCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitComment *)doCopyKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id appid:(NSString * _Nullable)appid conversationid:(NSString * _Nullable)conversationid commenttype:(NSString * _Nullable)commenttype added:(NSString * _Nullable)added modified:(NSString * _Nullable)modified tsunix:(Sportstalk247KitLong * _Nullable)tsunix userid:(NSString * _Nullable)userid user:(Sportstalk247KitUser * _Nullable)user body:(NSString * _Nullable)body originalbody:(NSString * _Nullable)originalbody hashtags:(NSArray<NSString *> * _Nullable)hashtags shadowban:(Sportstalk247KitBoolean * _Nullable)shadowban customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customtags:(NSArray<NSString *> * _Nullable)customtags customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 edited:(Sportstalk247KitBoolean * _Nullable)edited censored:(Sportstalk247KitBoolean * _Nullable)censored deleted:(Sportstalk247KitBoolean * _Nullable)deleted parentid:(NSString * _Nullable)parentid hierarchy:(NSArray<NSString *> * _Nullable)hierarchy reactions:(NSArray<Sportstalk247KitReaction *> * _Nullable)reactions likecount:(Sportstalk247KitLong * _Nullable)likecount replycount:(Sportstalk247KitLong * _Nullable)replycount votecount:(Sportstalk247KitLong * _Nullable)votecount votescore:(Sportstalk247KitLong * _Nullable)votescore votes:(NSArray<Sportstalk247KitVoteDetail *> * _Nullable)votes moderation:(NSString * _Nullable)moderation active:(Sportstalk247KitBoolean * _Nullable)active reports:(NSArray<Sportstalk247KitReport *> * _Nullable)reports __attribute__((swift_name("doCopy(kind:id:appid:conversationid:commenttype:added:modified:tsunix:userid:user:body:originalbody:hashtags:shadowban:customtype:customid:custompayload:customtags:customfield1:customfield2:edited:censored:deleted:parentid:hierarchy:reactions:likecount:replycount:votecount:votescore:votes:moderation:active:reports:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitBoolean * _Nullable active __attribute__((swift_name("active")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable appid __attribute__((swift_name("appid")));
@property (readonly) NSString * _Nullable body __attribute__((swift_name("body")));
@property (readonly) Sportstalk247KitBoolean * _Nullable censored __attribute__((swift_name("censored")));
@property (readonly) NSString * _Nullable commenttype __attribute__((swift_name("commenttype")));
@property (readonly) NSString * _Nullable conversationid __attribute__((swift_name("conversationid")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) Sportstalk247KitBoolean * _Nullable deleted __attribute__((swift_name("deleted")));
@property (readonly) Sportstalk247KitBoolean * _Nullable edited __attribute__((swift_name("edited")));
@property (readonly) NSArray<NSString *> * _Nullable hashtags __attribute__((swift_name("hashtags")));
@property (readonly) NSArray<NSString *> * _Nullable hierarchy __attribute__((swift_name("hierarchy")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitLong * _Nullable likecount __attribute__((swift_name("likecount")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) NSString * _Nullable modified __attribute__((swift_name("modified")));
@property (readonly) NSString * _Nullable originalbody __attribute__((swift_name("originalbody")));
@property (readonly) NSString * _Nullable parentid __attribute__((swift_name("parentid")));
@property (readonly) NSArray<Sportstalk247KitReaction *> * _Nullable reactions __attribute__((swift_name("reactions")));
@property (readonly) Sportstalk247KitLong * _Nullable replycount __attribute__((swift_name("replycount")));
@property (readonly) NSArray<Sportstalk247KitReport *> * _Nullable reports __attribute__((swift_name("reports")));
@property (readonly) Sportstalk247KitBoolean * _Nullable shadowban __attribute__((swift_name("shadowban")));
@property (readonly) Sportstalk247KitLong * _Nullable tsunix __attribute__((swift_name("tsunix")));
@property (readonly) Sportstalk247KitUser * _Nullable user __attribute__((swift_name("user")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@property (readonly) Sportstalk247KitLong * _Nullable votecount __attribute__((swift_name("votecount")));
@property (readonly) NSArray<Sportstalk247KitVoteDetail *> * _Nullable votes __attribute__((swift_name("votes")));
@property (readonly) Sportstalk247KitLong * _Nullable votescore __attribute__((swift_name("votescore")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Comment.Companion")))
@interface Sportstalk247KitCommentCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCommentCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommentFilterModerationState")))
@interface Sportstalk247KitCommentFilterModerationState : Sportstalk247KitKotlinEnum<Sportstalk247KitCommentFilterModerationState *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitCommentFilterModerationStateCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) Sportstalk247KitCommentFilterModerationState *approved __attribute__((swift_name("approved")));
@property (class, readonly) Sportstalk247KitCommentFilterModerationState *rejected __attribute__((swift_name("rejected")));
@property (class, readonly) Sportstalk247KitCommentFilterModerationState *pending __attribute__((swift_name("pending")));
@property (class, readonly) Sportstalk247KitCommentFilterModerationState *flagged __attribute__((swift_name("flagged")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitCommentFilterModerationState *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *rawValue __attribute__((swift_name("rawValue")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommentFilterModerationState.Companion")))
@interface Sportstalk247KitCommentFilterModerationStateCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCommentFilterModerationStateCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CommentType")))
@interface Sportstalk247KitCommentType : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)commentType __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCommentType *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *COMMENT __attribute__((swift_name("COMMENT")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Conversation")))
@interface Sportstalk247KitConversation : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithKind:(NSString * _Nullable)kind appid:(NSString * _Nullable)appid owneruserid:(NSString * _Nullable)owneruserid conversationid:(NSString * _Nullable)conversationid property:(NSString * _Nullable)property moderation:(NSString * _Nullable)moderation maxreports:(Sportstalk247KitLong * _Nullable)maxreports enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter title:(NSString * _Nullable)title maxcommentlen:(Sportstalk247KitLong * _Nullable)maxcommentlen commentcount:(Sportstalk247KitLong * _Nullable)commentcount reactions:(NSArray<Sportstalk247KitReaction *> * _Nullable)reactions likecount:(Sportstalk247KitLong * _Nullable)likecount open:(Sportstalk247KitBoolean * _Nullable)open added:(NSString * _Nullable)added whenmodified:(NSString * _Nullable)whenmodified customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid customtags:(NSArray<NSString *> * _Nullable)customtags custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 __attribute__((swift_name("init(kind:appid:owneruserid:conversationid:property:moderation:maxreports:enableprofanityfilter:title:maxcommentlen:commentcount:reactions:likecount:open:added:whenmodified:customtype:customid:customtags:custompayload:customfield1:customfield2:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitConversationCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitConversation *)doCopyKind:(NSString * _Nullable)kind appid:(NSString * _Nullable)appid owneruserid:(NSString * _Nullable)owneruserid conversationid:(NSString * _Nullable)conversationid property:(NSString * _Nullable)property moderation:(NSString * _Nullable)moderation maxreports:(Sportstalk247KitLong * _Nullable)maxreports enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter title:(NSString * _Nullable)title maxcommentlen:(Sportstalk247KitLong * _Nullable)maxcommentlen commentcount:(Sportstalk247KitLong * _Nullable)commentcount reactions:(NSArray<Sportstalk247KitReaction *> * _Nullable)reactions likecount:(Sportstalk247KitLong * _Nullable)likecount open:(Sportstalk247KitBoolean * _Nullable)open added:(NSString * _Nullable)added whenmodified:(NSString * _Nullable)whenmodified customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid customtags:(NSArray<NSString *> * _Nullable)customtags custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 __attribute__((swift_name("doCopy(kind:appid:owneruserid:conversationid:property:moderation:maxreports:enableprofanityfilter:title:maxcommentlen:commentcount:reactions:likecount:open:added:whenmodified:customtype:customid:customtags:custompayload:customfield1:customfield2:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable appid __attribute__((swift_name("appid")));
@property (readonly) Sportstalk247KitLong * _Nullable commentcount __attribute__((swift_name("commentcount")));
@property (readonly) NSString * _Nullable conversationid __attribute__((swift_name("conversationid")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableprofanityfilter __attribute__((swift_name("enableprofanityfilter")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitLong * _Nullable likecount __attribute__((swift_name("likecount")));
@property (readonly) Sportstalk247KitLong * _Nullable maxcommentlen __attribute__((swift_name("maxcommentlen")));
@property (readonly) Sportstalk247KitLong * _Nullable maxreports __attribute__((swift_name("maxreports")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) Sportstalk247KitBoolean * _Nullable open __attribute__((swift_name("open")));
@property (readonly) NSString * _Nullable owneruserid __attribute__((swift_name("owneruserid")));
@property (readonly) NSString * _Nullable property __attribute__((swift_name("property")));
@property (readonly) NSArray<Sportstalk247KitReaction *> * _Nullable reactions __attribute__((swift_name("reactions")));
@property (readonly) NSString * _Nullable title __attribute__((swift_name("title")));
@property (readonly) NSString * _Nullable whenmodified __attribute__((swift_name("whenmodified")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Conversation.Companion")))
@interface Sportstalk247KitConversationCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitConversationCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateCommentRequest")))
@interface Sportstalk247KitCreateCommentRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid displayname:(NSString * _Nullable)displayname body:(NSString *)body customtype:(NSString * _Nullable)customtype customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSArray<NSString *> * _Nullable)customtags custompayload:(NSString * _Nullable)custompayload __attribute__((swift_name("init(userid:displayname:body:customtype:customfield1:customfield2:customtags:custompayload:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitCreateCommentRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitCreateCommentRequest *)doCopyUserid:(NSString *)userid displayname:(NSString * _Nullable)displayname body:(NSString *)body customtype:(NSString * _Nullable)customtype customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSArray<NSString *> * _Nullable)customtags custompayload:(NSString * _Nullable)custompayload __attribute__((swift_name("doCopy(userid:displayname:body:customtype:customfield1:customfield2:customtags:custompayload:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *body __attribute__((swift_name("body")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) NSString * _Nullable displayname __attribute__((swift_name("displayname")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateCommentRequest.Companion")))
@interface Sportstalk247KitCreateCommentRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCreateCommentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateOrUpdateConversationRequest")))
@interface Sportstalk247KitCreateOrUpdateConversationRequest : Sportstalk247KitBase
- (instancetype)initWithConversationid:(NSString * _Nullable)conversationid property:(NSString *)property moderation:(NSString *)moderation maxreports:(Sportstalk247KitLong * _Nullable)maxreports enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter title:(NSString * _Nullable)title maxcommentlen:(Sportstalk247KitLong * _Nullable)maxcommentlen open:(Sportstalk247KitBoolean * _Nullable)open added:(NSString * _Nullable)added whenmodified:(NSString * _Nullable)whenmodified customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid customtags:(NSArray<NSString *> * _Nullable)customtags custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 __attribute__((swift_name("init(conversationid:property:moderation:maxreports:enableprofanityfilter:title:maxcommentlen:open:added:whenmodified:customtype:customid:customtags:custompayload:customfield1:customfield2:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitCreateOrUpdateConversationRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitCreateOrUpdateConversationRequest *)doCopyConversationid:(NSString * _Nullable)conversationid property:(NSString *)property moderation:(NSString *)moderation maxreports:(Sportstalk247KitLong * _Nullable)maxreports enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter title:(NSString * _Nullable)title maxcommentlen:(Sportstalk247KitLong * _Nullable)maxcommentlen open:(Sportstalk247KitBoolean * _Nullable)open added:(NSString * _Nullable)added whenmodified:(NSString * _Nullable)whenmodified customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid customtags:(NSArray<NSString *> * _Nullable)customtags custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 __attribute__((swift_name("doCopy(conversationid:property:moderation:maxreports:enableprofanityfilter:title:maxcommentlen:open:added:whenmodified:customtype:customid:customtags:custompayload:customfield1:customfield2:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable conversationid __attribute__((swift_name("conversationid")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableprofanityfilter __attribute__((swift_name("enableprofanityfilter")));
@property (readonly) Sportstalk247KitLong * _Nullable maxcommentlen __attribute__((swift_name("maxcommentlen")));
@property (readonly) Sportstalk247KitLong * _Nullable maxreports __attribute__((swift_name("maxreports")));
@property (readonly) NSString *moderation __attribute__((swift_name("moderation")));
@property (readonly) Sportstalk247KitBoolean * _Nullable open __attribute__((swift_name("open")));
@property (readonly) NSString *property __attribute__((swift_name("property")));
@property (readonly) NSString * _Nullable title __attribute__((swift_name("title")));
@property (readonly) NSString * _Nullable whenmodified __attribute__((swift_name("whenmodified")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateOrUpdateConversationRequest.Companion")))
@interface Sportstalk247KitCreateOrUpdateConversationRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCreateOrUpdateConversationRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteCommentResponse")))
@interface Sportstalk247KitDeleteCommentResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind permanentdelete:(Sportstalk247KitBoolean * _Nullable)permanentdelete comment:(Sportstalk247KitComment * _Nullable)comment __attribute__((swift_name("init(kind:permanentdelete:comment:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitDeleteCommentResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitDeleteCommentResponse *)doCopyKind:(NSString * _Nullable)kind permanentdelete:(Sportstalk247KitBoolean * _Nullable)permanentdelete comment:(Sportstalk247KitComment * _Nullable)comment __attribute__((swift_name("doCopy(kind:permanentdelete:comment:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitComment * _Nullable comment __attribute__((swift_name("comment")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable permanentdelete __attribute__((swift_name("permanentdelete")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteCommentResponse.Companion")))
@interface Sportstalk247KitDeleteCommentResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitDeleteCommentResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteConversationResponse")))
@interface Sportstalk247KitDeleteConversationResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind conversationid:(NSString * _Nullable)conversationid userid:(NSString * _Nullable)userid deletedconversations:(Sportstalk247KitLong * _Nullable)deletedconversations deletedcomments:(Sportstalk247KitLong * _Nullable)deletedcomments __attribute__((swift_name("init(kind:conversationid:userid:deletedconversations:deletedcomments:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitDeleteConversationResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitDeleteConversationResponse *)doCopyKind:(NSString * _Nullable)kind conversationid:(NSString * _Nullable)conversationid userid:(NSString * _Nullable)userid deletedconversations:(Sportstalk247KitLong * _Nullable)deletedconversations deletedcomments:(Sportstalk247KitLong * _Nullable)deletedcomments __attribute__((swift_name("doCopy(kind:conversationid:userid:deletedconversations:deletedcomments:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable conversationid __attribute__((swift_name("conversationid")));
@property (readonly) Sportstalk247KitLong * _Nullable deletedcomments __attribute__((swift_name("deletedcomments")));
@property (readonly) Sportstalk247KitLong * _Nullable deletedconversations __attribute__((swift_name("deletedconversations")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteConversationResponse.Companion")))
@interface Sportstalk247KitDeleteConversationResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitDeleteConversationResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DirectionType")))
@interface Sportstalk247KitDirectionType : Sportstalk247KitKotlinEnum<Sportstalk247KitDirectionType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitDirectionTypeCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) Sportstalk247KitDirectionType *forward __attribute__((swift_name("forward")));
@property (class, readonly) Sportstalk247KitDirectionType *backward __attribute__((swift_name("backward")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitDirectionType *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *rawValue __attribute__((swift_name("rawValue")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DirectionType.Companion")))
@interface Sportstalk247KitDirectionTypeCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitDirectionTypeCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListComments")))
@interface Sportstalk247KitListComments : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitInt * _Nullable)itemcount conversation:(Sportstalk247KitConversation * _Nullable)conversation comments:(NSArray<Sportstalk247KitComment *> *)comments __attribute__((swift_name("init(kind:cursor:more:itemcount:conversation:comments:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListCommentsCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListComments *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitInt * _Nullable)itemcount conversation:(Sportstalk247KitConversation * _Nullable)conversation comments:(NSArray<Sportstalk247KitComment *> *)comments __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:conversation:comments:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Sportstalk247KitComment *> *comments __attribute__((swift_name("comments")));
@property (readonly) Sportstalk247KitConversation * _Nullable conversation __attribute__((swift_name("conversation")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) Sportstalk247KitInt * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListComments.Companion")))
@interface Sportstalk247KitListCommentsCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListCommentsCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListConversations")))
@interface Sportstalk247KitListConversations : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more conversations:(NSArray<Sportstalk247KitConversation *> *)conversations __attribute__((swift_name("init(kind:cursor:more:conversations:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListConversationsCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListConversations *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more conversations:(NSArray<Sportstalk247KitConversation *> *)conversations __attribute__((swift_name("doCopy(kind:cursor:more:conversations:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Sportstalk247KitConversation *> *conversations __attribute__((swift_name("conversations")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListConversations.Companion")))
@interface Sportstalk247KitListConversationsCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListConversationsCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListRepliesBatchResponse")))
@interface Sportstalk247KitListRepliesBatchResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind repliesgroupedbyparentid:(NSArray<Sportstalk247KitListRepliesBatchResponseCommentReplyGroup *> *)repliesgroupedbyparentid __attribute__((swift_name("init(kind:repliesgroupedbyparentid:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListRepliesBatchResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListRepliesBatchResponse *)doCopyKind:(NSString * _Nullable)kind repliesgroupedbyparentid:(NSArray<Sportstalk247KitListRepliesBatchResponseCommentReplyGroup *> *)repliesgroupedbyparentid __attribute__((swift_name("doCopy(kind:repliesgroupedbyparentid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSArray<Sportstalk247KitListRepliesBatchResponseCommentReplyGroup *> *repliesgroupedbyparentid __attribute__((swift_name("repliesgroupedbyparentid")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListRepliesBatchResponse.CommentReplyGroup")))
@interface Sportstalk247KitListRepliesBatchResponseCommentReplyGroup : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind parentid:(NSString * _Nullable)parentid comments:(NSArray<Sportstalk247KitComment *> *)comments __attribute__((swift_name("init(kind:parentid:comments:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListRepliesBatchResponseCommentReplyGroupCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListRepliesBatchResponseCommentReplyGroup *)doCopyKind:(NSString * _Nullable)kind parentid:(NSString * _Nullable)parentid comments:(NSArray<Sportstalk247KitComment *> *)comments __attribute__((swift_name("doCopy(kind:parentid:comments:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Sportstalk247KitComment *> *comments __attribute__((swift_name("comments")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable parentid __attribute__((swift_name("parentid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListRepliesBatchResponse.CommentReplyGroupCompanion")))
@interface Sportstalk247KitListRepliesBatchResponseCommentReplyGroupCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListRepliesBatchResponseCommentReplyGroupCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListRepliesBatchResponse.Companion")))
@interface Sportstalk247KitListRepliesBatchResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListRepliesBatchResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactToCommentRequest")))
@interface Sportstalk247KitReactToCommentRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid reaction:(NSString *)reaction reacted:(BOOL)reacted __attribute__((swift_name("init(userid:reaction:reacted:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReactToCommentRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReactToCommentRequest *)doCopyUserid:(NSString *)userid reaction:(NSString *)reaction reacted:(BOOL)reacted __attribute__((swift_name("doCopy(userid:reaction:reacted:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL reacted __attribute__((swift_name("reacted")));
@property (readonly) NSString *reaction __attribute__((swift_name("reaction")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactToCommentRequest.Companion")))
@interface Sportstalk247KitReactToCommentRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReactToCommentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactToConversationTopicRequest")))
@interface Sportstalk247KitReactToConversationTopicRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid reaction:(NSString *)reaction reacted:(BOOL)reacted __attribute__((swift_name("init(userid:reaction:reacted:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReactToConversationTopicRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReactToConversationTopicRequest *)doCopyUserid:(NSString *)userid reaction:(NSString *)reaction reacted:(BOOL)reacted __attribute__((swift_name("doCopy(userid:reaction:reacted:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL reacted __attribute__((swift_name("reacted")));
@property (readonly) NSString *reaction __attribute__((swift_name("reaction")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactToConversationTopicRequest.Companion")))
@interface Sportstalk247KitReactToConversationTopicRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReactToConversationTopicRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportCommentRequest")))
@interface Sportstalk247KitReportCommentRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid reporttype:(NSString *)reporttype __attribute__((swift_name("init(userid:reporttype:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReportCommentRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReportCommentRequest *)doCopyUserid:(NSString *)userid reporttype:(NSString *)reporttype __attribute__((swift_name("doCopy(userid:reporttype:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *reporttype __attribute__((swift_name("reporttype")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportCommentRequest.Companion")))
@interface Sportstalk247KitReportCommentRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportCommentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SortType")))
@interface Sportstalk247KitSortType : Sportstalk247KitKotlinEnum<Sportstalk247KitSortType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitSortTypeCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) Sportstalk247KitSortType *oldest __attribute__((swift_name("oldest")));
@property (class, readonly) Sportstalk247KitSortType *newest __attribute__((swift_name("newest")));
@property (class, readonly) Sportstalk247KitSortType *likes __attribute__((swift_name("likes")));
@property (class, readonly) Sportstalk247KitSortType *votescore __attribute__((swift_name("votescore")));
@property (class, readonly) Sportstalk247KitSortType *mostreplies __attribute__((swift_name("mostreplies")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitSortType *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *rawValue __attribute__((swift_name("rawValue")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SortType.Companion")))
@interface Sportstalk247KitSortTypeCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSortTypeCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UpdateCommentRequest")))
@interface Sportstalk247KitUpdateCommentRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid body:(NSString *)body __attribute__((swift_name("init(userid:body:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitUpdateCommentRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitUpdateCommentRequest *)doCopyUserid:(NSString *)userid body:(NSString *)body __attribute__((swift_name("doCopy(userid:body:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *body __attribute__((swift_name("body")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UpdateCommentRequest.Companion")))
@interface Sportstalk247KitUpdateCommentRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUpdateCommentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VoteDetail")))
@interface Sportstalk247KitVoteDetail : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithType:(Sportstalk247KitVoteType * _Nullable)type count:(Sportstalk247KitLong * _Nullable)count users:(NSArray<Sportstalk247KitUser *> * _Nullable)users __attribute__((swift_name("init(type:count:users:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitVoteDetailCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitVoteDetail *)doCopyType:(Sportstalk247KitVoteType * _Nullable)type count:(Sportstalk247KitLong * _Nullable)count users:(NSArray<Sportstalk247KitUser *> * _Nullable)users __attribute__((swift_name("doCopy(type:count:users:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitLong * _Nullable count __attribute__((swift_name("count")));
@property (readonly) Sportstalk247KitVoteType * _Nullable type __attribute__((swift_name("type")));
@property (readonly) NSArray<Sportstalk247KitUser *> * _Nullable users __attribute__((swift_name("users")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VoteDetail.Companion")))
@interface Sportstalk247KitVoteDetailCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitVoteDetailCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VoteOnCommentRequest")))
@interface Sportstalk247KitVoteOnCommentRequest : Sportstalk247KitBase
- (instancetype)initWithVote:(Sportstalk247KitVoteType *)vote userid:(NSString *)userid __attribute__((swift_name("init(vote:userid:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitVoteOnCommentRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitVoteOnCommentRequest *)doCopyVote:(Sportstalk247KitVoteType *)vote userid:(NSString *)userid __attribute__((swift_name("doCopy(vote:userid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@property (readonly) Sportstalk247KitVoteType *vote __attribute__((swift_name("vote")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VoteOnCommentRequest.Companion")))
@interface Sportstalk247KitVoteOnCommentRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitVoteOnCommentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VoteType")))
@interface Sportstalk247KitVoteType : Sportstalk247KitKotlinEnum<Sportstalk247KitVoteType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitVoteTypeCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) Sportstalk247KitVoteType *up __attribute__((swift_name("up")));
@property (class, readonly) Sportstalk247KitVoteType *down __attribute__((swift_name("down")));
@property (class, readonly) Sportstalk247KitVoteType *none __attribute__((swift_name("none")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitVoteType *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *rawValue __attribute__((swift_name("rawValue")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("VoteType.Companion")))
@interface Sportstalk247KitVoteTypeCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitVoteTypeCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Reaction")))
@interface Sportstalk247KitReaction : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithType:(NSString * _Nullable)type count:(Sportstalk247KitLong * _Nullable)count users:(NSArray<Sportstalk247KitUser *> *)users __attribute__((swift_name("init(type:count:users:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReactionCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReaction *)doCopyType:(NSString * _Nullable)type count:(Sportstalk247KitLong * _Nullable)count users:(NSArray<Sportstalk247KitUser *> *)users __attribute__((swift_name("doCopy(type:count:users:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitLong * _Nullable count __attribute__((swift_name("count")));
@property (readonly) NSString * _Nullable type __attribute__((swift_name("type")));
@property (readonly) NSArray<Sportstalk247KitUser *> *users __attribute__((swift_name("users")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Reaction.Companion")))
@interface Sportstalk247KitReactionCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReactionCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactionType")))
@interface Sportstalk247KitReactionType : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)reactionType __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReactionType *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *LIKE __attribute__((swift_name("LIKE")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BanUserRequest")))
@interface Sportstalk247KitBanUserRequest : Sportstalk247KitBase
- (instancetype)initWithApplyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("init(applyeffect:expireseconds:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitBanUserRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitBanUserRequest *)doCopyApplyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("doCopy(applyeffect:expireseconds:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL applyeffect __attribute__((swift_name("applyeffect")));
@property (readonly) Sportstalk247KitLong * _Nullable expireseconds __attribute__((swift_name("expireseconds")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BanUserRequest.Companion")))
@interface Sportstalk247KitBanUserRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitBanUserRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateUpdateUserRequest")))
@interface Sportstalk247KitCreateUpdateUserRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid handle:(NSString * _Nullable)handle displayname:(NSString * _Nullable)displayname pictureurl:(NSString * _Nullable)pictureurl profileurl:(NSString * _Nullable)profileurl __attribute__((swift_name("init(userid:handle:displayname:pictureurl:profileurl:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitCreateUpdateUserRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitCreateUpdateUserRequest *)doCopyUserid:(NSString *)userid handle:(NSString * _Nullable)handle displayname:(NSString * _Nullable)displayname pictureurl:(NSString * _Nullable)pictureurl profileurl:(NSString * _Nullable)profileurl __attribute__((swift_name("doCopy(userid:handle:displayname:pictureurl:profileurl:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable displayname __attribute__((swift_name("displayname")));
@property (readonly) NSString * _Nullable handle __attribute__((swift_name("handle")));
@property (readonly) NSString * _Nullable pictureurl __attribute__((swift_name("pictureurl")));
@property (readonly) NSString * _Nullable profileurl __attribute__((swift_name("profileurl")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateUpdateUserRequest.Companion")))
@interface Sportstalk247KitCreateUpdateUserRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCreateUpdateUserRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteUserResponse")))
@interface Sportstalk247KitDeleteUserResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind user:(Sportstalk247KitUser * _Nullable)user __attribute__((swift_name("init(kind:user:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitDeleteUserResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitDeleteUserResponse *)doCopyKind:(NSString * _Nullable)kind user:(Sportstalk247KitUser * _Nullable)user __attribute__((swift_name("doCopy(kind:user:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitUser * _Nullable user __attribute__((swift_name("user")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteUserResponse.Companion")))
@interface Sportstalk247KitDeleteUserResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitDeleteUserResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GloballyPurgeUserContentRequest")))
@interface Sportstalk247KitGloballyPurgeUserContentRequest : Sportstalk247KitBase
- (instancetype)initWithByuserid:(NSString *)byuserid __attribute__((swift_name("init(byuserid:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitGloballyPurgeUserContentRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitGloballyPurgeUserContentRequest *)doCopyByuserid:(NSString *)byuserid __attribute__((swift_name("doCopy(byuserid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GloballyPurgeUserContentRequest.Companion")))
@interface Sportstalk247KitGloballyPurgeUserContentRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitGloballyPurgeUserContentRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GloballyPurgeUserContentResponse")))
@interface Sportstalk247KitGloballyPurgeUserContentResponse : Sportstalk247KitBase
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
@property (class, readonly, getter=companion) Sportstalk247KitGloballyPurgeUserContentResponseCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GloballyPurgeUserContentResponse.Companion")))
@interface Sportstalk247KitGloballyPurgeUserContentResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitGloballyPurgeUserContentResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserNotificationsResponse")))
@interface Sportstalk247KitListUserNotificationsResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitInt * _Nullable)itemcount totalunread:(Sportstalk247KitInt * _Nullable)totalunread notifications:(NSArray<Sportstalk247KitUserNotification *> *)notifications __attribute__((swift_name("init(kind:cursor:more:itemcount:totalunread:notifications:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListUserNotificationsResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListUserNotificationsResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitInt * _Nullable)itemcount totalunread:(Sportstalk247KitInt * _Nullable)totalunread notifications:(NSArray<Sportstalk247KitUserNotification *> *)notifications __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:totalunread:notifications:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) Sportstalk247KitInt * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@property (readonly) NSArray<Sportstalk247KitUserNotification *> *notifications __attribute__((swift_name("notifications")));
@property (readonly) Sportstalk247KitInt * _Nullable totalunread __attribute__((swift_name("totalunread")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserNotificationsResponse.Companion")))
@interface Sportstalk247KitListUserNotificationsResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListUserNotificationsResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUsersResponse")))
@interface Sportstalk247KitListUsersResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor users:(NSArray<Sportstalk247KitUser *> *)users __attribute__((swift_name("init(kind:cursor:users:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListUsersResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListUsersResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor users:(NSArray<Sportstalk247KitUser *> *)users __attribute__((swift_name("doCopy(kind:cursor:users:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSArray<Sportstalk247KitUser *> *users __attribute__((swift_name("users")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUsersResponse.Companion")))
@interface Sportstalk247KitListUsersResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListUsersResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MuteUserRequest")))
@interface Sportstalk247KitMuteUserRequest : Sportstalk247KitBase
- (instancetype)initWithApplyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("init(applyeffect:expireseconds:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitMuteUserRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitMuteUserRequest *)doCopyApplyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("doCopy(applyeffect:expireseconds:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL applyeffect __attribute__((swift_name("applyeffect")));
@property (readonly) Sportstalk247KitLong * _Nullable expireseconds __attribute__((swift_name("expireseconds")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MuteUserRequest.Companion")))
@interface Sportstalk247KitMuteUserRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitMuteUserRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportUserRequest")))
@interface Sportstalk247KitReportUserRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString * _Nullable)userid reporttype:(NSString * _Nullable)reporttype __attribute__((swift_name("init(userid:reporttype:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReportUserRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReportUserRequest *)doCopyUserid:(NSString * _Nullable)userid reporttype:(NSString * _Nullable)reporttype __attribute__((swift_name("doCopy(userid:reporttype:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable reporttype __attribute__((swift_name("reporttype")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportUserRequest.Companion")))
@interface Sportstalk247KitReportUserRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportUserRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchUsersRequest")))
@interface Sportstalk247KitSearchUsersRequest : Sportstalk247KitBase
- (instancetype)initWithHandle:(NSString * _Nullable)handle name:(NSString * _Nullable)name userid:(NSString * _Nullable)userid limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor __attribute__((swift_name("init(handle:name:userid:limit:cursor:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitSearchUsersRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSearchUsersRequest *)doCopyHandle:(NSString * _Nullable)handle name:(NSString * _Nullable)name userid:(NSString * _Nullable)userid limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor __attribute__((swift_name("doCopy(handle:name:userid:limit:cursor:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSString * _Nullable handle __attribute__((swift_name("handle")));
@property (readonly) Sportstalk247KitInt * _Nullable limit __attribute__((swift_name("limit")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchUsersRequest.Companion")))
@interface Sportstalk247KitSearchUsersRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSearchUsersRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SetShadowBanStatusRequest")))
@interface Sportstalk247KitSetShadowBanStatusRequest : Sportstalk247KitBase
- (instancetype)initWithApplyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("init(applyeffect:expireseconds:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitSetShadowBanStatusRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSetShadowBanStatusRequest *)doCopyApplyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("doCopy(applyeffect:expireseconds:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL applyeffect __attribute__((swift_name("applyeffect")));
@property (readonly) Sportstalk247KitLong * _Nullable expireseconds __attribute__((swift_name("expireseconds")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SetShadowBanStatusRequest.Companion")))
@interface Sportstalk247KitSetShadowBanStatusRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSetShadowBanStatusRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("User")))
@interface Sportstalk247KitUser : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithKind:(NSString * _Nullable)kind userid:(NSString * _Nullable)userid handle:(NSString * _Nullable)handle handlelowercase:(NSString * _Nullable)handlelowercase displayname:(NSString * _Nullable)displayname pictureurl:(NSString * _Nullable)pictureurl profileurl:(NSString * _Nullable)profileurl role:(NSString * _Nullable)role customtags:(NSArray<NSString *> * _Nullable)customtags banned:(Sportstalk247KitBoolean * _Nullable)banned banexpires:(NSString * _Nullable)banexpires shadowbanned:(Sportstalk247KitBoolean * _Nullable)shadowbanned shadowbanexpires:(NSString * _Nullable)shadowbanexpires muted:(Sportstalk247KitBoolean * _Nullable)muted muteexpires:(NSString * _Nullable)muteexpires moderation:(NSString * _Nullable)moderation reports:(NSArray<Sportstalk247KitUserReport *> * _Nullable)reports __attribute__((swift_name("init(kind:userid:handle:handlelowercase:displayname:pictureurl:profileurl:role:customtags:banned:banexpires:shadowbanned:shadowbanexpires:muted:muteexpires:moderation:reports:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitUserCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitUser *)doCopyKind:(NSString * _Nullable)kind userid:(NSString * _Nullable)userid handle:(NSString * _Nullable)handle handlelowercase:(NSString * _Nullable)handlelowercase displayname:(NSString * _Nullable)displayname pictureurl:(NSString * _Nullable)pictureurl profileurl:(NSString * _Nullable)profileurl role:(NSString * _Nullable)role customtags:(NSArray<NSString *> * _Nullable)customtags banned:(Sportstalk247KitBoolean * _Nullable)banned banexpires:(NSString * _Nullable)banexpires shadowbanned:(Sportstalk247KitBoolean * _Nullable)shadowbanned shadowbanexpires:(NSString * _Nullable)shadowbanexpires muted:(Sportstalk247KitBoolean * _Nullable)muted muteexpires:(NSString * _Nullable)muteexpires moderation:(NSString * _Nullable)moderation reports:(NSArray<Sportstalk247KitUserReport *> * _Nullable)reports __attribute__((swift_name("doCopy(kind:userid:handle:handlelowercase:displayname:pictureurl:profileurl:role:customtags:banned:banexpires:shadowbanned:shadowbanexpires:muted:muteexpires:moderation:reports:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable banexpires __attribute__((swift_name("banexpires")));
@property (readonly) Sportstalk247KitBoolean * _Nullable banned __attribute__((swift_name("banned")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable displayname __attribute__((swift_name("displayname")));
@property (readonly) NSString * _Nullable handle __attribute__((swift_name("handle")));
@property (readonly) NSString * _Nullable handlelowercase __attribute__((swift_name("handlelowercase")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) Sportstalk247KitBoolean * _Nullable muted __attribute__((swift_name("muted")));
@property (readonly) NSString * _Nullable muteexpires __attribute__((swift_name("muteexpires")));
@property (readonly) NSString * _Nullable pictureurl __attribute__((swift_name("pictureurl")));
@property (readonly) NSString * _Nullable profileurl __attribute__((swift_name("profileurl")));
@property (readonly) NSArray<Sportstalk247KitUserReport *> * _Nullable reports __attribute__((swift_name("reports")));
@property (readonly) NSString * _Nullable role __attribute__((swift_name("role")));
@property (readonly) NSString * _Nullable shadowbanexpires __attribute__((swift_name("shadowbanexpires")));
@property (readonly) Sportstalk247KitBoolean * _Nullable shadowbanned __attribute__((swift_name("shadowbanned")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("User.Companion")))
@interface Sportstalk247KitUserCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUserCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("User.Role")))
@interface Sportstalk247KitUserRole : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)role __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUserRole *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ADMIN __attribute__((swift_name("ADMIN")));
@property (readonly) NSString *MODERATOR __attribute__((swift_name("MODERATOR")));
@property (readonly) NSString *USER __attribute__((swift_name("USER")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserNotification")))
@interface Sportstalk247KitUserNotification : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithId:(NSString * _Nullable)id kind:(NSString * _Nullable)kind chatroomid:(NSString * _Nullable)chatroomid added:(NSString * _Nullable)added userid:(NSString * _Nullable)userid ts:(Sportstalk247KitLong * _Nullable)ts whenread:(NSString * _Nullable)whenread isread:(Sportstalk247KitBoolean * _Nullable)isread notificationtype:(NSString * _Nullable)notificationtype chatroomcustomid:(NSString * _Nullable)chatroomcustomid commentconversationid:(NSString * _Nullable)commentconversationid commentconversationcustomid:(NSString * _Nullable)commentconversationcustomid chateventid:(NSString * _Nullable)chateventid commentid:(NSString * _Nullable)commentid __attribute__((swift_name("init(id:kind:chatroomid:added:userid:ts:whenread:isread:notificationtype:chatroomcustomid:commentconversationid:commentconversationcustomid:chateventid:commentid:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitUserNotificationCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitUserNotification *)doCopyId:(NSString * _Nullable)id kind:(NSString * _Nullable)kind chatroomid:(NSString * _Nullable)chatroomid added:(NSString * _Nullable)added userid:(NSString * _Nullable)userid ts:(Sportstalk247KitLong * _Nullable)ts whenread:(NSString * _Nullable)whenread isread:(Sportstalk247KitBoolean * _Nullable)isread notificationtype:(NSString * _Nullable)notificationtype chatroomcustomid:(NSString * _Nullable)chatroomcustomid commentconversationid:(NSString * _Nullable)commentconversationid commentconversationcustomid:(NSString * _Nullable)commentconversationcustomid chateventid:(NSString * _Nullable)chateventid commentid:(NSString * _Nullable)commentid __attribute__((swift_name("doCopy(id:kind:chatroomid:added:userid:ts:whenread:isread:notificationtype:chatroomcustomid:commentconversationid:commentconversationcustomid:chateventid:commentid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable chateventid __attribute__((swift_name("chateventid")));
@property (readonly) NSString * _Nullable chatroomcustomid __attribute__((swift_name("chatroomcustomid")));
@property (readonly) NSString * _Nullable chatroomid __attribute__((swift_name("chatroomid")));
@property (readonly) NSString * _Nullable commentconversationcustomid __attribute__((swift_name("commentconversationcustomid")));
@property (readonly) NSString * _Nullable commentconversationid __attribute__((swift_name("commentconversationid")));
@property (readonly) NSString * _Nullable commentid __attribute__((swift_name("commentid")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) Sportstalk247KitBoolean * _Nullable isread __attribute__((swift_name("isread")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable notificationtype __attribute__((swift_name("notificationtype")));
@property (readonly) Sportstalk247KitLong * _Nullable ts __attribute__((swift_name("ts")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@property (readonly) NSString * _Nullable whenread __attribute__((swift_name("whenread")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserNotification.Companion")))
@interface Sportstalk247KitUserNotificationCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUserNotificationCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserNotification.Type_")))
@interface Sportstalk247KitUserNotificationType : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)type __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUserNotificationType *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *CHAT_QUOTE __attribute__((swift_name("CHAT_QUOTE")));
@property (readonly) NSString *CHAT_REPLY __attribute__((swift_name("CHAT_REPLY")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserReport")))
@interface Sportstalk247KitUserReport : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithUserid:(NSString * _Nullable)userid reason:(NSString * _Nullable)reason __attribute__((swift_name("init(userid:reason:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitUserReportCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitUserReport *)doCopyUserid:(NSString * _Nullable)userid reason:(NSString * _Nullable)reason __attribute__((swift_name("doCopy(userid:reason:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable reason __attribute__((swift_name("reason")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UserReport.Companion")))
@interface Sportstalk247KitUserReportCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUserReportCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BounceUserRequest")))
@interface Sportstalk247KitBounceUserRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid bounce:(BOOL)bounce announcement:(NSString * _Nullable)announcement __attribute__((swift_name("init(userid:bounce:announcement:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitBounceUserRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitBounceUserRequest *)doCopyUserid:(NSString *)userid bounce:(BOOL)bounce announcement:(NSString * _Nullable)announcement __attribute__((swift_name("doCopy(userid:bounce:announcement:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable announcement __attribute__((swift_name("announcement")));
@property (readonly) BOOL bounce __attribute__((swift_name("bounce")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BounceUserRequest.Companion")))
@interface Sportstalk247KitBounceUserRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitBounceUserRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BounceUserResponse")))
@interface Sportstalk247KitBounceUserResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind event:(Sportstalk247KitChatEvent * _Nullable)event room:(Sportstalk247KitChatRoom * _Nullable)room __attribute__((swift_name("init(kind:event:room:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitBounceUserResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitBounceUserResponse *)doCopyKind:(NSString * _Nullable)kind event:(Sportstalk247KitChatEvent * _Nullable)event room:(Sportstalk247KitChatRoom * _Nullable)room __attribute__((swift_name("doCopy(kind:event:room:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitChatEvent * _Nullable event __attribute__((swift_name("event")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitChatRoom * _Nullable room __attribute__((swift_name("room")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("BounceUserResponse.Companion")))
@interface Sportstalk247KitBounceUserResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitBounceUserResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatEvent")))
@interface Sportstalk247KitChatEvent : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id roomid:(NSString * _Nullable)roomid body:(NSString * _Nullable)body originalbody:(NSString * _Nullable)originalbody added:(NSString * _Nullable)added modified:(NSString * _Nullable)modified ts:(Sportstalk247KitLong * _Nullable)ts eventtype:(NSString * _Nullable)eventtype userid:(NSString * _Nullable)userid user:(Sportstalk247KitUser * _Nullable)user customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customtags:(NSArray<NSString *> * _Nullable)customtags customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 replyto:(Sportstalk247KitChatEvent * _Nullable)replyto parentid:(NSString * _Nullable)parentid hierarchy:(NSArray<NSString *> * _Nullable)hierarchy depth:(Sportstalk247KitInt * _Nullable)depth edited:(Sportstalk247KitBoolean * _Nullable)edited editedbymoderator:(Sportstalk247KitBoolean * _Nullable)editedbymoderator deleted:(Sportstalk247KitBoolean * _Nullable)deleted active:(Sportstalk247KitBoolean * _Nullable)active shadowban:(Sportstalk247KitBoolean * _Nullable)shadowban hashtags:(NSArray<NSString *> * _Nullable)hashtags censored:(Sportstalk247KitBoolean * _Nullable)censored likecount:(Sportstalk247KitLong * _Nullable)likecount replycount:(Sportstalk247KitLong * _Nullable)replycount reactions:(NSArray<Sportstalk247KitReaction *> *)reactions moderation:(NSString * _Nullable)moderation reports:(NSArray<Sportstalk247KitReport *> *)reports __attribute__((swift_name("init(kind:id:roomid:body:originalbody:added:modified:ts:eventtype:userid:user:customtype:customid:custompayload:customtags:customfield1:customfield2:replyto:parentid:hierarchy:depth:edited:editedbymoderator:deleted:active:shadowban:hashtags:censored:likecount:replycount:reactions:moderation:reports:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitChatEventCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitChatEvent *)doCopyKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id roomid:(NSString * _Nullable)roomid body:(NSString * _Nullable)body originalbody:(NSString * _Nullable)originalbody added:(NSString * _Nullable)added modified:(NSString * _Nullable)modified ts:(Sportstalk247KitLong * _Nullable)ts eventtype:(NSString * _Nullable)eventtype userid:(NSString * _Nullable)userid user:(Sportstalk247KitUser * _Nullable)user customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customtags:(NSArray<NSString *> * _Nullable)customtags customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 replyto:(Sportstalk247KitChatEvent * _Nullable)replyto parentid:(NSString * _Nullable)parentid hierarchy:(NSArray<NSString *> * _Nullable)hierarchy depth:(Sportstalk247KitInt * _Nullable)depth edited:(Sportstalk247KitBoolean * _Nullable)edited editedbymoderator:(Sportstalk247KitBoolean * _Nullable)editedbymoderator deleted:(Sportstalk247KitBoolean * _Nullable)deleted active:(Sportstalk247KitBoolean * _Nullable)active shadowban:(Sportstalk247KitBoolean * _Nullable)shadowban hashtags:(NSArray<NSString *> * _Nullable)hashtags censored:(Sportstalk247KitBoolean * _Nullable)censored likecount:(Sportstalk247KitLong * _Nullable)likecount replycount:(Sportstalk247KitLong * _Nullable)replycount reactions:(NSArray<Sportstalk247KitReaction *> *)reactions moderation:(NSString * _Nullable)moderation reports:(NSArray<Sportstalk247KitReport *> *)reports __attribute__((swift_name("doCopy(kind:id:roomid:body:originalbody:added:modified:ts:eventtype:userid:user:customtype:customid:custompayload:customtags:customfield1:customfield2:replyto:parentid:hierarchy:depth:edited:editedbymoderator:deleted:active:shadowban:hashtags:censored:likecount:replycount:reactions:moderation:reports:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSString *)getEventCursor __attribute__((swift_name("getEventCursor()")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitBoolean * _Nullable active __attribute__((swift_name("active")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable body __attribute__((swift_name("body")));
@property (readonly) Sportstalk247KitBoolean * _Nullable censored __attribute__((swift_name("censored")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) Sportstalk247KitBoolean * _Nullable deleted __attribute__((swift_name("deleted")));
@property (readonly) Sportstalk247KitInt * _Nullable depth __attribute__((swift_name("depth")));
@property (readonly) Sportstalk247KitBoolean * _Nullable edited __attribute__((swift_name("edited")));
@property (readonly) Sportstalk247KitBoolean * _Nullable editedbymoderator __attribute__((swift_name("editedbymoderator")));
@property (readonly) NSString * _Nullable eventtype __attribute__((swift_name("eventtype")));
@property (readonly) NSArray<NSString *> * _Nullable hashtags __attribute__((swift_name("hashtags")));
@property (readonly) NSArray<NSString *> * _Nullable hierarchy __attribute__((swift_name("hierarchy")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitLong * _Nullable likecount __attribute__((swift_name("likecount")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) NSString * _Nullable modified __attribute__((swift_name("modified")));
@property (readonly) NSString * _Nullable originalbody __attribute__((swift_name("originalbody")));
@property (readonly) NSString * _Nullable parentid __attribute__((swift_name("parentid")));
@property (readonly) NSArray<Sportstalk247KitReaction *> *reactions __attribute__((swift_name("reactions")));
@property (readonly) Sportstalk247KitLong * _Nullable replycount __attribute__((swift_name("replycount")));
@property (readonly) Sportstalk247KitChatEvent * _Nullable replyto __attribute__((swift_name("replyto")));
@property (readonly) NSArray<Sportstalk247KitReport *> *reports __attribute__((swift_name("reports")));
@property (readonly) NSString * _Nullable roomid __attribute__((swift_name("roomid")));
@property (readonly) Sportstalk247KitBoolean * _Nullable shadowban __attribute__((swift_name("shadowban")));
@property (readonly) Sportstalk247KitLong * _Nullable ts __attribute__((swift_name("ts")));
@property (readonly) Sportstalk247KitUser * _Nullable user __attribute__((swift_name("user")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatEvent.Companion")))
@interface Sportstalk247KitChatEventCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitChatEventCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatRoom")))
@interface Sportstalk247KitChatRoom : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id appid:(NSString * _Nullable)appid ownerid:(NSString * _Nullable)ownerid name:(NSString * _Nullable)name description:(NSString * _Nullable)description customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customtags:(NSArray<NSString *> * _Nullable)customtags customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 enableactions:(Sportstalk247KitBoolean * _Nullable)enableactions enableenterandexit:(Sportstalk247KitBoolean * _Nullable)enableenterandexit open:(Sportstalk247KitBoolean * _Nullable)open inroom:(Sportstalk247KitLong * _Nullable)inroom added:(NSString * _Nullable)added whenmodified:(NSString * _Nullable)whenmodified moderation:(NSString * _Nullable)moderation maxreports:(Sportstalk247KitLong * _Nullable)maxreports enableautoexpiresessions:(Sportstalk247KitBoolean * _Nullable)enableautoexpiresessions enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter delaymessageseconds:(Sportstalk247KitLong * _Nullable)delaymessageseconds bouncedusers:(NSArray<NSString *> * _Nullable)bouncedusers reportedusers:(NSArray<Sportstalk247KitReportedUser *> * _Nullable)reportedusers __attribute__((swift_name("init(kind:id:appid:ownerid:name:description:customtype:customid:custompayload:customtags:customfield1:customfield2:enableactions:enableenterandexit:open:inroom:added:whenmodified:moderation:maxreports:enableautoexpiresessions:enableprofanityfilter:delaymessageseconds:bouncedusers:reportedusers:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitChatRoomCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitChatRoom *)doCopyKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id appid:(NSString * _Nullable)appid ownerid:(NSString * _Nullable)ownerid name:(NSString * _Nullable)name description:(NSString * _Nullable)description customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customtags:(NSArray<NSString *> * _Nullable)customtags customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 enableactions:(Sportstalk247KitBoolean * _Nullable)enableactions enableenterandexit:(Sportstalk247KitBoolean * _Nullable)enableenterandexit open:(Sportstalk247KitBoolean * _Nullable)open inroom:(Sportstalk247KitLong * _Nullable)inroom added:(NSString * _Nullable)added whenmodified:(NSString * _Nullable)whenmodified moderation:(NSString * _Nullable)moderation maxreports:(Sportstalk247KitLong * _Nullable)maxreports enableautoexpiresessions:(Sportstalk247KitBoolean * _Nullable)enableautoexpiresessions enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter delaymessageseconds:(Sportstalk247KitLong * _Nullable)delaymessageseconds bouncedusers:(NSArray<NSString *> * _Nullable)bouncedusers reportedusers:(NSArray<Sportstalk247KitReportedUser *> * _Nullable)reportedusers __attribute__((swift_name("doCopy(kind:id:appid:ownerid:name:description:customtype:customid:custompayload:customtags:customfield1:customfield2:enableactions:enableenterandexit:open:inroom:added:whenmodified:moderation:maxreports:enableautoexpiresessions:enableprofanityfilter:delaymessageseconds:bouncedusers:reportedusers:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable appid __attribute__((swift_name("appid")));
@property (readonly) NSArray<NSString *> * _Nullable bouncedusers __attribute__((swift_name("bouncedusers")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) Sportstalk247KitLong * _Nullable delaymessageseconds __attribute__((swift_name("delaymessageseconds")));
@property (readonly) NSString * _Nullable description_ __attribute__((swift_name("description_")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableactions __attribute__((swift_name("enableactions")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableautoexpiresessions __attribute__((swift_name("enableautoexpiresessions")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableenterandexit __attribute__((swift_name("enableenterandexit")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableprofanityfilter __attribute__((swift_name("enableprofanityfilter")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) Sportstalk247KitLong * _Nullable inroom __attribute__((swift_name("inroom")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitLong * _Nullable maxreports __attribute__((swift_name("maxreports")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) Sportstalk247KitBoolean * _Nullable open __attribute__((swift_name("open")));
@property (readonly) NSString * _Nullable ownerid __attribute__((swift_name("ownerid")));
@property (readonly) NSArray<Sportstalk247KitReportedUser *> * _Nullable reportedusers __attribute__((swift_name("reportedusers")));
@property (readonly) NSString * _Nullable whenmodified __attribute__((swift_name("whenmodified")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatRoom.Companion")))
@interface Sportstalk247KitChatRoomCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitChatRoomCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatRoomParticipant")))
@interface Sportstalk247KitChatRoomParticipant : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind user:(Sportstalk247KitUser * _Nullable)user __attribute__((swift_name("init(kind:user:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitChatRoomParticipantCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitChatRoomParticipant *)doCopyKind:(NSString * _Nullable)kind user:(Sportstalk247KitUser * _Nullable)user __attribute__((swift_name("doCopy(kind:user:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitUser * _Nullable user __attribute__((swift_name("user")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatRoomParticipant.Companion")))
@interface Sportstalk247KitChatRoomParticipantCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitChatRoomParticipantCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatSubscription")))
@interface Sportstalk247KitChatSubscription : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id roomid:(NSString * _Nullable)roomid roomcustomid:(NSString * _Nullable)roomcustomid userid:(NSString * _Nullable)userid updated:(NSString * _Nullable)updated added:(NSString * _Nullable)added roomname:(NSString * _Nullable)roomname roomcustomtags:(NSArray<NSString *> * _Nullable)roomcustomtags persist:(Sportstalk247KitBoolean * _Nullable)persist __attribute__((swift_name("init(kind:id:roomid:roomcustomid:userid:updated:added:roomname:roomcustomtags:persist:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitChatSubscriptionCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitChatSubscription *)doCopyKind:(NSString * _Nullable)kind id:(NSString * _Nullable)id roomid:(NSString * _Nullable)roomid roomcustomid:(NSString * _Nullable)roomcustomid userid:(NSString * _Nullable)userid updated:(NSString * _Nullable)updated added:(NSString * _Nullable)added roomname:(NSString * _Nullable)roomname roomcustomtags:(NSArray<NSString *> * _Nullable)roomcustomtags persist:(Sportstalk247KitBoolean * _Nullable)persist __attribute__((swift_name("doCopy(kind:id:roomid:roomcustomid:userid:updated:added:roomname:roomcustomtags:persist:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable id __attribute__((swift_name("id")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable persist __attribute__((swift_name("persist")));
@property (readonly) NSString * _Nullable roomcustomid __attribute__((swift_name("roomcustomid")));
@property (readonly) NSArray<NSString *> * _Nullable roomcustomtags __attribute__((swift_name("roomcustomtags")));
@property (readonly) NSString * _Nullable roomid __attribute__((swift_name("roomid")));
@property (readonly) NSString * _Nullable roomname __attribute__((swift_name("roomname")));
@property (readonly) NSString * _Nullable updated __attribute__((swift_name("updated")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatSubscription.Companion")))
@interface Sportstalk247KitChatSubscriptionCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitChatSubscriptionCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateChatRoomRequest")))
@interface Sportstalk247KitCreateChatRoomRequest : Sportstalk247KitBase
- (instancetype)initWithCustomid:(NSString * _Nullable)customid userid:(NSString * _Nullable)userid name:(NSString * _Nullable)name description:(NSString * _Nullable)description moderation:(NSString * _Nullable)moderation enableactions:(Sportstalk247KitBoolean * _Nullable)enableactions enableenterandexit:(Sportstalk247KitBoolean * _Nullable)enableenterandexit enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter enableautoexpiresessions:(Sportstalk247KitBoolean * _Nullable)enableautoexpiresessions delaymessageseconds:(Sportstalk247KitLong * _Nullable)delaymessageseconds roomisopen:(Sportstalk247KitBoolean * _Nullable)roomisopen maxreports:(Sportstalk247KitLong * _Nullable)maxreports customtype:(NSString * _Nullable)customtype custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSArray<NSString *> * _Nullable)customtags private:(Sportstalk247KitBoolean * _Nullable)private_ __attribute__((swift_name("init(customid:userid:name:description:moderation:enableactions:enableenterandexit:enableprofanityfilter:enableautoexpiresessions:delaymessageseconds:roomisopen:maxreports:customtype:custompayload:customfield1:customfield2:customtags:private:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitCreateChatRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitCreateChatRoomRequest *)doCopyCustomid:(NSString * _Nullable)customid userid:(NSString * _Nullable)userid name:(NSString * _Nullable)name description:(NSString * _Nullable)description moderation:(NSString * _Nullable)moderation enableactions:(Sportstalk247KitBoolean * _Nullable)enableactions enableenterandexit:(Sportstalk247KitBoolean * _Nullable)enableenterandexit enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter enableautoexpiresessions:(Sportstalk247KitBoolean * _Nullable)enableautoexpiresessions delaymessageseconds:(Sportstalk247KitLong * _Nullable)delaymessageseconds roomisopen:(Sportstalk247KitBoolean * _Nullable)roomisopen maxreports:(Sportstalk247KitLong * _Nullable)maxreports customtype:(NSString * _Nullable)customtype custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSArray<NSString *> * _Nullable)customtags private:(Sportstalk247KitBoolean * _Nullable)private_ __attribute__((swift_name("doCopy(customid:userid:name:description:moderation:enableactions:enableenterandexit:enableprofanityfilter:enableautoexpiresessions:delaymessageseconds:roomisopen:maxreports:customtype:custompayload:customfield1:customfield2:customtags:private:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) Sportstalk247KitLong * _Nullable delaymessageseconds __attribute__((swift_name("delaymessageseconds")));
@property (readonly) NSString * _Nullable description_ __attribute__((swift_name("description_")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableactions __attribute__((swift_name("enableactions")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableautoexpiresessions __attribute__((swift_name("enableautoexpiresessions")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableenterandexit __attribute__((swift_name("enableenterandexit")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableprofanityfilter __attribute__((swift_name("enableprofanityfilter")));
@property (readonly) Sportstalk247KitLong * _Nullable maxreports __attribute__((swift_name("maxreports")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly, getter=private) Sportstalk247KitBoolean * _Nullable private_ __attribute__((swift_name("private_")));
@property (readonly) Sportstalk247KitBoolean * _Nullable roomisopen __attribute__((swift_name("roomisopen")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("CreateChatRoomRequest.Companion")))
@interface Sportstalk247KitCreateChatRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitCreateChatRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteChatRoomResponse")))
@interface Sportstalk247KitDeleteChatRoomResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind deletedEventsCount:(Sportstalk247KitLong * _Nullable)deletedEventsCount room:(Sportstalk247KitChatRoom * _Nullable)room __attribute__((swift_name("init(kind:deletedEventsCount:room:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitDeleteChatRoomResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitDeleteChatRoomResponse *)doCopyKind:(NSString * _Nullable)kind deletedEventsCount:(Sportstalk247KitLong * _Nullable)deletedEventsCount room:(Sportstalk247KitChatRoom * _Nullable)room __attribute__((swift_name("doCopy(kind:deletedEventsCount:room:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitLong * _Nullable deletedEventsCount __attribute__((swift_name("deletedEventsCount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitChatRoom * _Nullable room __attribute__((swift_name("room")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteChatRoomResponse.Companion")))
@interface Sportstalk247KitDeleteChatRoomResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitDeleteChatRoomResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteEventResponse")))
@interface Sportstalk247KitDeleteEventResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind permanentdelete:(Sportstalk247KitBoolean * _Nullable)permanentdelete event:(Sportstalk247KitChatEvent * _Nullable)event __attribute__((swift_name("init(kind:permanentdelete:event:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitDeleteEventResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitDeleteEventResponse *)doCopyKind:(NSString * _Nullable)kind permanentdelete:(Sportstalk247KitBoolean * _Nullable)permanentdelete event:(Sportstalk247KitChatEvent * _Nullable)event __attribute__((swift_name("doCopy(kind:permanentdelete:event:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitChatEvent * _Nullable event __attribute__((swift_name("event")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable permanentdelete __attribute__((swift_name("permanentdelete")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("DeleteEventResponse.Companion")))
@interface Sportstalk247KitDeleteEventResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitDeleteEventResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("EventType")))
@interface Sportstalk247KitEventType : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)eventType __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitEventType *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ACTION __attribute__((swift_name("ACTION")));
@property (readonly) NSString *AD __attribute__((swift_name("AD")));
@property (readonly) NSString *ADVERTISEMENT __attribute__((swift_name("ADVERTISEMENT")));
@property (readonly) NSString *ANNOUNCEMENT __attribute__((swift_name("ANNOUNCEMENT")));
@property (readonly) NSString *BOUNCE __attribute__((swift_name("BOUNCE")));
@property (readonly) NSString *CUSTOM __attribute__((swift_name("CUSTOM")));
@property (readonly) NSString *GOAL __attribute__((swift_name("GOAL")));
@property (readonly) NSString *MOD_APPROVED __attribute__((swift_name("MOD_APPROVED")));
@property (readonly) NSString *MOD_FLAGGED __attribute__((swift_name("MOD_FLAGGED")));
@property (readonly) NSString *MOD_REJECTED __attribute__((swift_name("MOD_REJECTED")));
@property (readonly) NSString *PURGE __attribute__((swift_name("PURGE")));
@property (readonly) NSString *QUOTE __attribute__((swift_name("QUOTE")));
@property (readonly) NSString *REACTION __attribute__((swift_name("REACTION")));
@property (readonly) NSString *REMOVE __attribute__((swift_name("REMOVE")));
@property (readonly) NSString *REPLACE __attribute__((swift_name("REPLACE")));
@property (readonly) NSString *REPLY __attribute__((swift_name("REPLY")));
@property (readonly) NSString *ROOM_CLOSED __attribute__((swift_name("ROOM_CLOSED")));
@property (readonly) NSString *ROOM_OPENED __attribute__((swift_name("ROOM_OPENED")));
@property (readonly) NSString *SPEECH __attribute__((swift_name("SPEECH")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecuteChatCommandRequest")))
@interface Sportstalk247KitExecuteChatCommandRequest : Sportstalk247KitBase
- (instancetype)initWithCommand:(NSString *)command userid:(NSString *)userid eventtype:(NSString * _Nullable)eventtype customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload replyto:(NSString * _Nullable)replyto __attribute__((swift_name("init(command:userid:eventtype:customtype:customid:custompayload:replyto:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitExecuteChatCommandRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitExecuteChatCommandRequest *)doCopyCommand:(NSString *)command userid:(NSString *)userid eventtype:(NSString * _Nullable)eventtype customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload replyto:(NSString * _Nullable)replyto __attribute__((swift_name("doCopy(command:userid:eventtype:customtype:customid:custompayload:replyto:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *command __attribute__((swift_name("command")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) NSString * _Nullable eventtype __attribute__((swift_name("eventtype")));
@property (readonly) NSString * _Nullable replyto __attribute__((swift_name("replyto")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecuteChatCommandRequest.Companion")))
@interface Sportstalk247KitExecuteChatCommandRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitExecuteChatCommandRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecuteChatCommandResponse")))
@interface Sportstalk247KitExecuteChatCommandResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind message:(NSString * _Nullable)message op:(NSString * _Nullable)op room:(Sportstalk247KitChatRoom * _Nullable)room speech:(Sportstalk247KitChatEvent * _Nullable)speech action:(Sportstalk247KitChatEvent * _Nullable)action __attribute__((swift_name("init(kind:message:op:room:speech:action:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitExecuteChatCommandResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitExecuteChatCommandResponse *)doCopyKind:(NSString * _Nullable)kind message:(NSString * _Nullable)message op:(NSString * _Nullable)op room:(Sportstalk247KitChatRoom * _Nullable)room speech:(Sportstalk247KitChatEvent * _Nullable)speech action:(Sportstalk247KitChatEvent * _Nullable)action __attribute__((swift_name("doCopy(kind:message:op:room:speech:action:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitChatEvent * _Nullable action __attribute__((swift_name("action")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable message __attribute__((swift_name("message")));
@property (readonly) NSString * _Nullable op __attribute__((swift_name("op")));
@property (readonly) Sportstalk247KitChatRoom * _Nullable room __attribute__((swift_name("room")));
@property (readonly) Sportstalk247KitChatEvent * _Nullable speech __attribute__((swift_name("speech")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExecuteChatCommandResponse.Companion")))
@interface Sportstalk247KitExecuteChatCommandResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitExecuteChatCommandResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExitChatRoomRequest")))
@interface Sportstalk247KitExitChatRoomRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid __attribute__((swift_name("init(userid:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitExitChatRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitExitChatRoomRequest *)doCopyUserid:(NSString *)userid __attribute__((swift_name("doCopy(userid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ExitChatRoomRequest.Companion")))
@interface Sportstalk247KitExitChatRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitExitChatRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetRoomDetailsExtendedBatchResponse")))
@interface Sportstalk247KitGetRoomDetailsExtendedBatchResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind details:(NSArray<Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetail *> *)details __attribute__((swift_name("init(kind:details:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitGetRoomDetailsExtendedBatchResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitGetRoomDetailsExtendedBatchResponse *)doCopyKind:(NSString * _Nullable)kind details:(NSArray<Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetail *> *)details __attribute__((swift_name("doCopy(kind:details:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetail *> *details __attribute__((swift_name("details")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetRoomDetailsExtendedBatchResponse.Companion")))
@interface Sportstalk247KitGetRoomDetailsExtendedBatchResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitGetRoomDetailsExtendedBatchResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetRoomDetailsExtendedBatchResponse.Detail")))
@interface Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetail : Sportstalk247KitBase
- (instancetype)initWithRoom:(Sportstalk247KitChatRoom * _Nullable)room mostrecentmessagetime:(NSString * _Nullable)mostrecentmessagetime inroom:(Sportstalk247KitLong * _Nullable)inroom __attribute__((swift_name("init(room:mostrecentmessagetime:inroom:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetailCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetail *)doCopyRoom:(Sportstalk247KitChatRoom * _Nullable)room mostrecentmessagetime:(NSString * _Nullable)mostrecentmessagetime inroom:(Sportstalk247KitLong * _Nullable)inroom __attribute__((swift_name("doCopy(room:mostrecentmessagetime:inroom:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitLong * _Nullable inroom __attribute__((swift_name("inroom")));
@property (readonly) NSString * _Nullable mostrecentmessagetime __attribute__((swift_name("mostrecentmessagetime")));
@property (readonly) Sportstalk247KitChatRoom * _Nullable room __attribute__((swift_name("room")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetRoomDetailsExtendedBatchResponse.DetailCompanion")))
@interface Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetailCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitGetRoomDetailsExtendedBatchResponseDetailCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetUpdatesResponse")))
@interface Sportstalk247KitGetUpdatesResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("init(kind:cursor:more:itemcount:events:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitGetUpdatesResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitGetUpdatesResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSArray<Sportstalk247KitChatEvent *> *events __attribute__((swift_name("events")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("GetUpdatesResponse.Companion")))
@interface Sportstalk247KitGetUpdatesResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitGetUpdatesResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JoinChatRoomRequest")))
@interface Sportstalk247KitJoinChatRoomRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid handle:(NSString * _Nullable)handle displayname:(NSString * _Nullable)displayname pictureurl:(NSString * _Nullable)pictureurl profileurl:(NSString * _Nullable)profileurl limit:(Sportstalk247KitInt * _Nullable)limit __attribute__((swift_name("init(userid:handle:displayname:pictureurl:profileurl:limit:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitJoinChatRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitJoinChatRoomRequest *)doCopyUserid:(NSString *)userid handle:(NSString * _Nullable)handle displayname:(NSString * _Nullable)displayname pictureurl:(NSString * _Nullable)pictureurl profileurl:(NSString * _Nullable)profileurl limit:(Sportstalk247KitInt * _Nullable)limit __attribute__((swift_name("doCopy(userid:handle:displayname:pictureurl:profileurl:limit:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable displayname __attribute__((swift_name("displayname")));
@property (readonly) NSString * _Nullable handle __attribute__((swift_name("handle")));
@property (readonly) Sportstalk247KitInt * _Nullable limit __attribute__((swift_name("limit")));
@property (readonly) NSString * _Nullable pictureurl __attribute__((swift_name("pictureurl")));
@property (readonly) NSString * _Nullable profileurl __attribute__((swift_name("profileurl")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JoinChatRoomRequest.Companion")))
@interface Sportstalk247KitJoinChatRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitJoinChatRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JoinChatRoomResponse")))
@interface Sportstalk247KitJoinChatRoomResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind user:(Sportstalk247KitUser * _Nullable)user room:(Sportstalk247KitChatRoom * _Nullable)room eventscursor:(Sportstalk247KitGetUpdatesResponse * _Nullable)eventscursor previouseventscursor:(NSString * _Nullable)previouseventscursor subscription:(Sportstalk247KitChatSubscription * _Nullable)subscription __attribute__((swift_name("init(kind:user:room:eventscursor:previouseventscursor:subscription:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitJoinChatRoomResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitJoinChatRoomResponse *)doCopyKind:(NSString * _Nullable)kind user:(Sportstalk247KitUser * _Nullable)user room:(Sportstalk247KitChatRoom * _Nullable)room eventscursor:(Sportstalk247KitGetUpdatesResponse * _Nullable)eventscursor previouseventscursor:(NSString * _Nullable)previouseventscursor subscription:(Sportstalk247KitChatSubscription * _Nullable)subscription __attribute__((swift_name("doCopy(kind:user:room:eventscursor:previouseventscursor:subscription:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) Sportstalk247KitGetUpdatesResponse * _Nullable eventscursor __attribute__((swift_name("eventscursor")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSString * _Nullable previouseventscursor __attribute__((swift_name("previouseventscursor")));
@property (readonly) Sportstalk247KitChatRoom * _Nullable room __attribute__((swift_name("room")));
@property (readonly) Sportstalk247KitChatSubscription * _Nullable subscription __attribute__((swift_name("subscription")));
@property (readonly) Sportstalk247KitUser * _Nullable user __attribute__((swift_name("user")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("JoinChatRoomResponse.Companion")))
@interface Sportstalk247KitJoinChatRoomResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitJoinChatRoomResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListChatRoomParticipantsResponse")))
@interface Sportstalk247KitListChatRoomParticipantsResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor participants:(NSArray<Sportstalk247KitChatRoomParticipant *> *)participants __attribute__((swift_name("init(kind:cursor:participants:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListChatRoomParticipantsResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListChatRoomParticipantsResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor participants:(NSArray<Sportstalk247KitChatRoomParticipant *> *)participants __attribute__((swift_name("doCopy(kind:cursor:participants:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) NSArray<Sportstalk247KitChatRoomParticipant *> *participants __attribute__((swift_name("participants")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListChatRoomParticipantsResponse.Companion")))
@interface Sportstalk247KitListChatRoomParticipantsResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListChatRoomParticipantsResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListEvents")))
@interface Sportstalk247KitListEvents : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount room:(Sportstalk247KitChatRoom * _Nullable)room events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("init(kind:cursor:more:itemcount:room:events:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListEventsCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListEvents *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount room:(Sportstalk247KitChatRoom * _Nullable)room events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:room:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSArray<Sportstalk247KitChatEvent *> *events __attribute__((swift_name("events")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@property (readonly) Sportstalk247KitChatRoom * _Nullable room __attribute__((swift_name("room")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListEvents.Companion")))
@interface Sportstalk247KitListEventsCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListEventsCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListEventsByTimestamp")))
@interface Sportstalk247KitListEventsByTimestamp : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursorolder:(NSString * _Nullable)cursorolder cursornewer:(NSString * _Nullable)cursornewer timestampolder:(Sportstalk247KitLong * _Nullable)timestampolder timestampnewer:(Sportstalk247KitLong * _Nullable)timestampnewer hasmoreolder:(Sportstalk247KitBoolean * _Nullable)hasmoreolder hasmorenewer:(Sportstalk247KitBoolean * _Nullable)hasmorenewer itemcount:(Sportstalk247KitLong * _Nullable)itemcount events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("init(kind:cursorolder:cursornewer:timestampolder:timestampnewer:hasmoreolder:hasmorenewer:itemcount:events:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListEventsByTimestampCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListEventsByTimestamp *)doCopyKind:(NSString * _Nullable)kind cursorolder:(NSString * _Nullable)cursorolder cursornewer:(NSString * _Nullable)cursornewer timestampolder:(Sportstalk247KitLong * _Nullable)timestampolder timestampnewer:(Sportstalk247KitLong * _Nullable)timestampnewer hasmoreolder:(Sportstalk247KitBoolean * _Nullable)hasmoreolder hasmorenewer:(Sportstalk247KitBoolean * _Nullable)hasmorenewer itemcount:(Sportstalk247KitLong * _Nullable)itemcount events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("doCopy(kind:cursorolder:cursornewer:timestampolder:timestampnewer:hasmoreolder:hasmorenewer:itemcount:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursornewer __attribute__((swift_name("cursornewer")));
@property (readonly) NSString * _Nullable cursorolder __attribute__((swift_name("cursorolder")));
@property (readonly) NSArray<Sportstalk247KitChatEvent *> *events __attribute__((swift_name("events")));
@property (readonly) Sportstalk247KitBoolean * _Nullable hasmorenewer __attribute__((swift_name("hasmorenewer")));
@property (readonly) Sportstalk247KitBoolean * _Nullable hasmoreolder __attribute__((swift_name("hasmoreolder")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitLong * _Nullable timestampnewer __attribute__((swift_name("timestampnewer")));
@property (readonly) Sportstalk247KitLong * _Nullable timestampolder __attribute__((swift_name("timestampolder")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListEventsByTimestamp.Companion")))
@interface Sportstalk247KitListEventsByTimestampCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListEventsByTimestampCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListMessagesByUser")))
@interface Sportstalk247KitListMessagesByUser : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("init(kind:cursor:events:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListMessagesByUserCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListMessagesByUser *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("doCopy(kind:cursor:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSArray<Sportstalk247KitChatEvent *> *events __attribute__((swift_name("events")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListMessagesByUser.Companion")))
@interface Sportstalk247KitListMessagesByUserCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListMessagesByUserCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListRoomsResponse")))
@interface Sportstalk247KitListRoomsResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount rooms:(NSArray<Sportstalk247KitChatRoom *> *)rooms __attribute__((swift_name("init(kind:cursor:more:itemcount:rooms:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListRoomsResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListRoomsResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount rooms:(NSArray<Sportstalk247KitChatRoom *> *)rooms __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:rooms:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@property (readonly) NSArray<Sportstalk247KitChatRoom *> *rooms __attribute__((swift_name("rooms")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListRoomsResponse.Companion")))
@interface Sportstalk247KitListRoomsResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListRoomsResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserSubscribedRoomsResponse")))
@interface Sportstalk247KitListUserSubscribedRoomsResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount subscriptions:(NSArray<Sportstalk247KitListUserSubscribedRoomsResponseData *> *)subscriptions __attribute__((swift_name("init(kind:cursor:more:itemcount:subscriptions:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListUserSubscribedRoomsResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListUserSubscribedRoomsResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount subscriptions:(NSArray<Sportstalk247KitListUserSubscribedRoomsResponseData *> *)subscriptions __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:subscriptions:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@property (readonly) NSArray<Sportstalk247KitListUserSubscribedRoomsResponseData *> *subscriptions __attribute__((swift_name("subscriptions")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserSubscribedRoomsResponse.Companion")))
@interface Sportstalk247KitListUserSubscribedRoomsResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListUserSubscribedRoomsResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserSubscribedRoomsResponse.Data")))
@interface Sportstalk247KitListUserSubscribedRoomsResponseData : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind subscription:(Sportstalk247KitChatSubscription * _Nullable)subscription roomstatus:(Sportstalk247KitListUserSubscribedRoomsResponseRoomStatus * _Nullable)roomstatus __attribute__((swift_name("init(kind:subscription:roomstatus:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListUserSubscribedRoomsResponseDataCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListUserSubscribedRoomsResponseData *)doCopyKind:(NSString * _Nullable)kind subscription:(Sportstalk247KitChatSubscription * _Nullable)subscription roomstatus:(Sportstalk247KitListUserSubscribedRoomsResponseRoomStatus * _Nullable)roomstatus __attribute__((swift_name("doCopy(kind:subscription:roomstatus:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitListUserSubscribedRoomsResponseRoomStatus * _Nullable roomstatus __attribute__((swift_name("roomstatus")));
@property (readonly) Sportstalk247KitChatSubscription * _Nullable subscription __attribute__((swift_name("subscription")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserSubscribedRoomsResponse.DataCompanion")))
@interface Sportstalk247KitListUserSubscribedRoomsResponseDataCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListUserSubscribedRoomsResponseDataCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserSubscribedRoomsResponse.RoomStatus")))
@interface Sportstalk247KitListUserSubscribedRoomsResponseRoomStatus : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind messagecount:(Sportstalk247KitLong * _Nullable)messagecount participantcount:(Sportstalk247KitLong * _Nullable)participantcount newestmessage:(Sportstalk247KitChatEvent * _Nullable)newestmessage __attribute__((swift_name("init(kind:messagecount:participantcount:newestmessage:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListUserSubscribedRoomsResponseRoomStatusCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListUserSubscribedRoomsResponseRoomStatus *)doCopyKind:(NSString * _Nullable)kind messagecount:(Sportstalk247KitLong * _Nullable)messagecount participantcount:(Sportstalk247KitLong * _Nullable)participantcount newestmessage:(Sportstalk247KitChatEvent * _Nullable)newestmessage __attribute__((swift_name("doCopy(kind:messagecount:participantcount:newestmessage:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitLong * _Nullable messagecount __attribute__((swift_name("messagecount")));
@property (readonly) Sportstalk247KitChatEvent * _Nullable newestmessage __attribute__((swift_name("newestmessage")));
@property (readonly) Sportstalk247KitLong * _Nullable participantcount __attribute__((swift_name("participantcount")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListUserSubscribedRoomsResponse.RoomStatusCompanion")))
@interface Sportstalk247KitListUserSubscribedRoomsResponseRoomStatusCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListUserSubscribedRoomsResponseRoomStatusCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ModerationType")))
@interface Sportstalk247KitModerationType : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)moderationType __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitModerationType *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *approved __attribute__((swift_name("approved")));
@property (readonly) NSString *na __attribute__((swift_name("na")));
@property (readonly) NSString *pending __attribute__((swift_name("pending")));
@property (readonly) NSString *post __attribute__((swift_name("post")));
@property (readonly) NSString *pre __attribute__((swift_name("pre")));
@property (readonly) NSString *rejected __attribute__((swift_name("rejected")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MuteUserInRoomRequest")))
@interface Sportstalk247KitMuteUserInRoomRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("init(userid:applyeffect:expireseconds:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitMuteUserInRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitMuteUserInRoomRequest *)doCopyUserid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("doCopy(userid:applyeffect:expireseconds:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL applyeffect __attribute__((swift_name("applyeffect")));
@property (readonly) Sportstalk247KitLong * _Nullable expireseconds __attribute__((swift_name("expireseconds")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("MuteUserInRoomRequest.Companion")))
@interface Sportstalk247KitMuteUserInRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitMuteUserInRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactToAMessageRequest")))
@interface Sportstalk247KitReactToAMessageRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid reaction:(NSString *)reaction reacted:(BOOL)reacted __attribute__((swift_name("init(userid:reaction:reacted:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReactToAMessageRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReactToAMessageRequest *)doCopyUserid:(NSString *)userid reaction:(NSString *)reaction reacted:(BOOL)reacted __attribute__((swift_name("doCopy(userid:reaction:reacted:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL reacted __attribute__((swift_name("reacted")));
@property (readonly) NSString *reaction __attribute__((swift_name("reaction")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReactToAMessageRequest.Companion")))
@interface Sportstalk247KitReactToAMessageRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReactToAMessageRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportMessageRequest")))
@interface Sportstalk247KitReportMessageRequest : Sportstalk247KitBase
- (instancetype)initWithReporttype:(NSString *)reporttype userid:(NSString *)userid __attribute__((swift_name("init(reporttype:userid:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReportMessageRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReportMessageRequest *)doCopyReporttype:(NSString *)reporttype userid:(NSString *)userid __attribute__((swift_name("doCopy(reporttype:userid:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *reporttype __attribute__((swift_name("reporttype")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportMessageRequest.Companion")))
@interface Sportstalk247KitReportMessageRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportMessageRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportUserInRoomRequest")))
@interface Sportstalk247KitReportUserInRoomRequest : Sportstalk247KitBase
- (instancetype)initWithReporterid:(NSString *)reporterid reporttype:(NSString *)reporttype __attribute__((swift_name("init(reporterid:reporttype:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReportUserInRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReportUserInRoomRequest *)doCopyReporterid:(NSString *)reporterid reporttype:(NSString *)reporttype __attribute__((swift_name("doCopy(reporterid:reporttype:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *reporterid __attribute__((swift_name("reporterid")));
@property (readonly) NSString *reporttype __attribute__((swift_name("reporttype")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportUserInRoomRequest.Companion")))
@interface Sportstalk247KitReportUserInRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportUserInRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportedUser")))
@interface Sportstalk247KitReportedUser : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithUserid:(NSString * _Nullable)userid reportedbyuserid:(NSString * _Nullable)reportedbyuserid reason:(NSString * _Nullable)reason added:(NSString * _Nullable)added __attribute__((swift_name("init(userid:reportedbyuserid:reason:added:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReportedUserCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReportedUser *)doCopyUserid:(NSString * _Nullable)userid reportedbyuserid:(NSString * _Nullable)reportedbyuserid reason:(NSString * _Nullable)reason added:(NSString * _Nullable)added __attribute__((swift_name("doCopy(userid:reportedbyuserid:reason:added:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable added __attribute__((swift_name("added")));
@property (readonly) NSString * _Nullable reason __attribute__((swift_name("reason")));
@property (readonly) NSString * _Nullable reportedbyuserid __attribute__((swift_name("reportedbyuserid")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportedUser.Companion")))
@interface Sportstalk247KitReportedUserCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportedUserCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("RoomDetailEntityType")))
@interface Sportstalk247KitRoomDetailEntityType : Sportstalk247KitKotlinEnum<Sportstalk247KitRoomDetailEntityType *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly) Sportstalk247KitRoomDetailEntityType *room __attribute__((swift_name("room")));
@property (class, readonly) Sportstalk247KitRoomDetailEntityType *numParticipants __attribute__((swift_name("numParticipants")));
@property (class, readonly) Sportstalk247KitRoomDetailEntityType *lastMessageTime __attribute__((swift_name("lastMessageTime")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitRoomDetailEntityType *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *keyword __attribute__((swift_name("keyword")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEventHistoryRequest")))
@interface Sportstalk247KitSearchEventHistoryRequest : Sportstalk247KitBase
- (instancetype)initWithFromuserid:(NSString * _Nullable)fromuserid fromhandle:(NSString * _Nullable)fromhandle roomid:(NSString * _Nullable)roomid body:(NSString * _Nullable)body limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor direction:(Sportstalk247KitSearchEventHistoryRequestDirection * _Nullable)direction types:(NSArray<NSString *> * _Nullable)types __attribute__((swift_name("init(fromuserid:fromhandle:roomid:body:limit:cursor:direction:types:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitSearchEventHistoryRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSearchEventHistoryRequest *)doCopyFromuserid:(NSString * _Nullable)fromuserid fromhandle:(NSString * _Nullable)fromhandle roomid:(NSString * _Nullable)roomid body:(NSString * _Nullable)body limit:(Sportstalk247KitInt * _Nullable)limit cursor:(NSString * _Nullable)cursor direction:(Sportstalk247KitSearchEventHistoryRequestDirection * _Nullable)direction types:(NSArray<NSString *> * _Nullable)types __attribute__((swift_name("doCopy(fromuserid:fromhandle:roomid:body:limit:cursor:direction:types:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable body __attribute__((swift_name("body")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) Sportstalk247KitSearchEventHistoryRequestDirection * _Nullable direction __attribute__((swift_name("direction")));
@property (readonly) NSString * _Nullable fromhandle __attribute__((swift_name("fromhandle")));
@property (readonly) NSString * _Nullable fromuserid __attribute__((swift_name("fromuserid")));
@property (readonly) Sportstalk247KitInt * _Nullable limit __attribute__((swift_name("limit")));
@property (readonly) NSString * _Nullable roomid __attribute__((swift_name("roomid")));
@property (readonly) NSArray<NSString *> * _Nullable types __attribute__((swift_name("types")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEventHistoryRequest.Companion")))
@interface Sportstalk247KitSearchEventHistoryRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSearchEventHistoryRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEventHistoryRequest.Direction")))
@interface Sportstalk247KitSearchEventHistoryRequestDirection : Sportstalk247KitKotlinEnum<Sportstalk247KitSearchEventHistoryRequestDirection *>
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (instancetype)initWithName:(NSString *)name ordinal:(int32_t)ordinal __attribute__((swift_name("init(name:ordinal:)"))) __attribute__((objc_designated_initializer)) __attribute__((unavailable));
@property (class, readonly, getter=companion) Sportstalk247KitSearchEventHistoryRequestDirectionCompanion *companion __attribute__((swift_name("companion")));
@property (class, readonly) Sportstalk247KitSearchEventHistoryRequestDirection *forward __attribute__((swift_name("forward")));
@property (class, readonly) Sportstalk247KitSearchEventHistoryRequestDirection *backward __attribute__((swift_name("backward")));
+ (Sportstalk247KitKotlinArray<Sportstalk247KitSearchEventHistoryRequestDirection *> *)values __attribute__((swift_name("values()")));
@property (readonly) NSString *rawValue __attribute__((swift_name("rawValue")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEventHistoryRequest.DirectionCompanion")))
@interface Sportstalk247KitSearchEventHistoryRequestDirectionCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSearchEventHistoryRequestDirectionCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializerTypeParamsSerializers:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeParamsSerializers __attribute__((swift_name("serializer(typeParamsSerializers:)")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEventHistoryResponse")))
@interface Sportstalk247KitSearchEventHistoryResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("init(kind:cursor:more:itemcount:events:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitSearchEventHistoryResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSearchEventHistoryResponse *)doCopyKind:(NSString * _Nullable)kind cursor:(NSString * _Nullable)cursor more:(Sportstalk247KitBoolean * _Nullable)more itemcount:(Sportstalk247KitLong * _Nullable)itemcount events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("doCopy(kind:cursor:more:itemcount:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable cursor __attribute__((swift_name("cursor")));
@property (readonly) NSArray<Sportstalk247KitChatEvent *> *events __attribute__((swift_name("events")));
@property (readonly) Sportstalk247KitLong * _Nullable itemcount __attribute__((swift_name("itemcount")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@property (readonly) Sportstalk247KitBoolean * _Nullable more __attribute__((swift_name("more")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SearchEventHistoryResponse.Companion")))
@interface Sportstalk247KitSearchEventHistoryResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSearchEventHistoryResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SendQuotedReplyRequest")))
@interface Sportstalk247KitSendQuotedReplyRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid body:(NSString *)body customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSArray<NSString *> * _Nullable)customtags __attribute__((swift_name("init(userid:body:customid:custompayload:customfield1:customfield2:customtags:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitSendQuotedReplyRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSendQuotedReplyRequest *)doCopyUserid:(NSString *)userid body:(NSString *)body customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSArray<NSString *> * _Nullable)customtags __attribute__((swift_name("doCopy(userid:body:customid:custompayload:customfield1:customfield2:customtags:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *body __attribute__((swift_name("body")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSArray<NSString *> * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SendQuotedReplyRequest.Companion")))
@interface Sportstalk247KitSendQuotedReplyRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSendQuotedReplyRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SendThreadedReplyRequest")))
@interface Sportstalk247KitSendThreadedReplyRequest : Sportstalk247KitBase
- (instancetype)initWithBody:(NSString *)body userid:(NSString *)userid customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload __attribute__((swift_name("init(body:userid:customtype:customid:custompayload:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitSendThreadedReplyRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitSendThreadedReplyRequest *)doCopyBody:(NSString *)body userid:(NSString *)userid customtype:(NSString * _Nullable)customtype customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload __attribute__((swift_name("doCopy(body:userid:customtype:customid:custompayload:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *body __attribute__((swift_name("body")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSString * _Nullable customtype __attribute__((swift_name("customtype")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("SendThreadedReplyRequest.Companion")))
@interface Sportstalk247KitSendThreadedReplyRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitSendThreadedReplyRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ShadowBanUserInRoomRequest")))
@interface Sportstalk247KitShadowBanUserInRoomRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("init(userid:applyeffect:expireseconds:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitShadowBanUserInRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitShadowBanUserInRoomRequest *)doCopyUserid:(NSString *)userid applyeffect:(BOOL)applyeffect expireseconds:(Sportstalk247KitLong * _Nullable)expireseconds __attribute__((swift_name("doCopy(userid:applyeffect:expireseconds:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL applyeffect __attribute__((swift_name("applyeffect")));
@property (readonly) Sportstalk247KitLong * _Nullable expireseconds __attribute__((swift_name("expireseconds")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ShadowBanUserInRoomRequest.Companion")))
@interface Sportstalk247KitShadowBanUserInRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitShadowBanUserInRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UpdateChatMessageRequest")))
@interface Sportstalk247KitUpdateChatMessageRequest : Sportstalk247KitBase
- (instancetype)initWithUserid:(NSString *)userid body:(NSString *)body customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSString * _Nullable)customtags __attribute__((swift_name("init(userid:body:customid:custompayload:customfield1:customfield2:customtags:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitUpdateChatMessageRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitUpdateChatMessageRequest *)doCopyUserid:(NSString *)userid body:(NSString *)body customid:(NSString * _Nullable)customid custompayload:(NSString * _Nullable)custompayload customfield1:(NSString * _Nullable)customfield1 customfield2:(NSString * _Nullable)customfield2 customtags:(NSString * _Nullable)customtags __attribute__((swift_name("doCopy(userid:body:customid:custompayload:customfield1:customfield2:customtags:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString *body __attribute__((swift_name("body")));
@property (readonly) NSString * _Nullable customfield1 __attribute__((swift_name("customfield1")));
@property (readonly) NSString * _Nullable customfield2 __attribute__((swift_name("customfield2")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) NSString * _Nullable custompayload __attribute__((swift_name("custompayload")));
@property (readonly) NSString * _Nullable customtags __attribute__((swift_name("customtags")));
@property (readonly) NSString *userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UpdateChatMessageRequest.Companion")))
@interface Sportstalk247KitUpdateChatMessageRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUpdateChatMessageRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UpdateChatRoomRequest")))
@interface Sportstalk247KitUpdateChatRoomRequest : Sportstalk247KitBase
- (instancetype)initWithCustomid:(NSString * _Nullable)customid userid:(NSString * _Nullable)userid name:(NSString * _Nullable)name description:(NSString * _Nullable)description moderation:(NSString * _Nullable)moderation enableactions:(Sportstalk247KitBoolean * _Nullable)enableactions enableenterandexit:(Sportstalk247KitBoolean * _Nullable)enableenterandexit enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter enableautoexpiresessions:(Sportstalk247KitBoolean * _Nullable)enableautoexpiresessions delaymessageseconds:(Sportstalk247KitLong * _Nullable)delaymessageseconds roomisopen:(Sportstalk247KitBoolean * _Nullable)roomisopen maxreports:(Sportstalk247KitLong * _Nullable)maxreports __attribute__((swift_name("init(customid:userid:name:description:moderation:enableactions:enableenterandexit:enableprofanityfilter:enableautoexpiresessions:delaymessageseconds:roomisopen:maxreports:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitUpdateChatRoomRequestCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitUpdateChatRoomRequest *)doCopyCustomid:(NSString * _Nullable)customid userid:(NSString * _Nullable)userid name:(NSString * _Nullable)name description:(NSString * _Nullable)description moderation:(NSString * _Nullable)moderation enableactions:(Sportstalk247KitBoolean * _Nullable)enableactions enableenterandexit:(Sportstalk247KitBoolean * _Nullable)enableenterandexit enableprofanityfilter:(Sportstalk247KitBoolean * _Nullable)enableprofanityfilter enableautoexpiresessions:(Sportstalk247KitBoolean * _Nullable)enableautoexpiresessions delaymessageseconds:(Sportstalk247KitLong * _Nullable)delaymessageseconds roomisopen:(Sportstalk247KitBoolean * _Nullable)roomisopen maxreports:(Sportstalk247KitLong * _Nullable)maxreports __attribute__((swift_name("doCopy(customid:userid:name:description:moderation:enableactions:enableenterandexit:enableprofanityfilter:enableautoexpiresessions:delaymessageseconds:roomisopen:maxreports:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable customid __attribute__((swift_name("customid")));
@property (readonly) Sportstalk247KitLong * _Nullable delaymessageseconds __attribute__((swift_name("delaymessageseconds")));
@property (readonly) NSString * _Nullable description_ __attribute__((swift_name("description_")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableactions __attribute__((swift_name("enableactions")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableautoexpiresessions __attribute__((swift_name("enableautoexpiresessions")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableenterandexit __attribute__((swift_name("enableenterandexit")));
@property (readonly) Sportstalk247KitBoolean * _Nullable enableprofanityfilter __attribute__((swift_name("enableprofanityfilter")));
@property (readonly) Sportstalk247KitLong * _Nullable maxreports __attribute__((swift_name("maxreports")));
@property (readonly) NSString * _Nullable moderation __attribute__((swift_name("moderation")));
@property (readonly) NSString * _Nullable name __attribute__((swift_name("name")));
@property (readonly) Sportstalk247KitBoolean * _Nullable roomisopen __attribute__((swift_name("roomisopen")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("UpdateChatRoomRequest.Companion")))
@interface Sportstalk247KitUpdateChatRoomRequestCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitUpdateChatRoomRequestCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApproveMessageRequest_")))
@interface Sportstalk247KitApproveMessageRequest_ : Sportstalk247KitBase
- (instancetype)initWithApprove:(BOOL)approve __attribute__((swift_name("init(approve:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitApproveMessageRequest_Companion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitApproveMessageRequest_ *)doCopyApprove:(BOOL)approve __attribute__((swift_name("doCopy(approve:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) BOOL approve __attribute__((swift_name("approve")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ApproveMessageRequest_.Companion")))
@interface Sportstalk247KitApproveMessageRequest_Companion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitApproveMessageRequest_Companion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListMessagesNeedingModerationResponse")))
@interface Sportstalk247KitListMessagesNeedingModerationResponse : Sportstalk247KitBase
- (instancetype)initWithKind:(NSString * _Nullable)kind events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("init(kind:events:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitListMessagesNeedingModerationResponseCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitListMessagesNeedingModerationResponse *)doCopyKind:(NSString * _Nullable)kind events:(NSArray<Sportstalk247KitChatEvent *> *)events __attribute__((swift_name("doCopy(kind:events:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSArray<Sportstalk247KitChatEvent *> *events __attribute__((swift_name("events")));
@property (readonly) NSString * _Nullable kind __attribute__((swift_name("kind")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ListMessagesNeedingModerationResponse.Companion")))
@interface Sportstalk247KitListMessagesNeedingModerationResponseCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitListMessagesNeedingModerationResponseCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable
*/
__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Report")))
@interface Sportstalk247KitReport : Sportstalk247KitBase <Sportstalk247KitCommonParcelable>
- (instancetype)initWithUserid:(NSString * _Nullable)userid reason:(NSString * _Nullable)reason __attribute__((swift_name("init(userid:reason:)"))) __attribute__((objc_designated_initializer));
@property (class, readonly, getter=companion) Sportstalk247KitReportCompanion *companion __attribute__((swift_name("companion")));
- (Sportstalk247KitReport *)doCopyUserid:(NSString * _Nullable)userid reason:(NSString * _Nullable)reason __attribute__((swift_name("doCopy(userid:reason:)")));
- (BOOL)isEqual:(id _Nullable)other __attribute__((swift_name("isEqual(_:)")));
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@property (readonly) NSString * _Nullable reason __attribute__((swift_name("reason")));
@property (readonly) NSString * _Nullable userid __attribute__((swift_name("userid")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Report.Companion")))
@interface Sportstalk247KitReportCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ReportType")))
@interface Sportstalk247KitReportType : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)reportType __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitReportType *shared __attribute__((swift_name("shared")));
@property (readonly) NSString *ABUSE __attribute__((swift_name("ABUSE")));
@property (readonly) NSString *SPAM __attribute__((swift_name("SPAM")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("ChatClientCoroutineExtKt")))
@interface Sportstalk247KitChatClientCoroutineExtKt : Sportstalk247KitBase
+ (id<Sportstalk247KitKotlinx_coroutines_coreFlow>)mergeFlows:(Sportstalk247KitKotlinArray<id<Sportstalk247KitKotlinx_coroutines_coreFlow>> *)flows __attribute__((swift_name("merge(flows:)")));
+ (id<Sportstalk247KitKotlinx_coroutines_coreFlow>)allEventUpdates:(id<Sportstalk247KitChatClient>)receiver chatRoomId:(NSString *)chatRoomId frequency:(int64_t)frequency limit:(Sportstalk247KitInt * _Nullable)limit smoothEventUpdates:(BOOL)smoothEventUpdates eventSpacingMs:(int64_t)eventSpacingMs maxEventBufferSize:(int32_t)maxEventBufferSize onChatEvent:(void (^ _Nullable)(Sportstalk247KitChatEvent *))onChatEvent onGoalEvent:(void (^ _Nullable)(Sportstalk247KitChatEvent *))onGoalEvent onAdEvent:(void (^ _Nullable)(Sportstalk247KitChatEvent *))onAdEvent onReply:(void (^ _Nullable)(Sportstalk247KitChatEvent *))onReply onReaction:(void (^ _Nullable)(Sportstalk247KitChatEvent *))onReaction onPurgeEvent:(void (^ _Nullable)(Sportstalk247KitChatEvent *))onPurgeEvent __attribute__((swift_name("allEventUpdates(_:chatRoomId:frequency:limit:smoothEventUpdates:eventSpacingMs:maxEventBufferSize:onChatEvent:onGoalEvent:onAdEvent:onReply:onReaction:onPurgeEvent:)")));
@end

__attribute__((swift_name("KotlinRuntimeException")))
@interface Sportstalk247KitKotlinRuntimeException : Sportstalk247KitKotlinException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("KotlinIllegalStateException")))
@interface Sportstalk247KitKotlinIllegalStateException : Sportstalk247KitKotlinRuntimeException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.4")
*/
__attribute__((swift_name("KotlinCancellationException")))
@interface Sportstalk247KitKotlinCancellationException : Sportstalk247KitKotlinIllegalStateException
- (instancetype)init __attribute__((swift_name("init()"))) __attribute__((objc_designated_initializer));
+ (instancetype)new __attribute__((availability(swift, unavailable, message="use object initializers instead")));
- (instancetype)initWithMessage:(NSString * _Nullable)message __attribute__((swift_name("init(message:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithMessage:(NSString * _Nullable)message cause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(message:cause:)"))) __attribute__((objc_designated_initializer));
- (instancetype)initWithCause:(Sportstalk247KitKotlinThrowable * _Nullable)cause __attribute__((swift_name("init(cause:)"))) __attribute__((objc_designated_initializer));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlow")))
@protocol Sportstalk247KitKotlinx_coroutines_coreFlow
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)collectCollector:(id<Sportstalk247KitKotlinx_coroutines_coreFlowCollector>)collector completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("collect(collector:completionHandler:)")));
@end

__attribute__((swift_name("KotlinFunction")))
@protocol Sportstalk247KitKotlinFunction
@required
@end

__attribute__((swift_name("KotlinSuspendFunction1")))
@protocol Sportstalk247KitKotlinSuspendFunction1 <Sportstalk247KitKotlinFunction>
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)invokeP1:(id _Nullable)p1 completionHandler:(void (^)(id _Nullable_result, NSError * _Nullable))completionHandler __attribute__((swift_name("invoke(p1:completionHandler:)")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializationStrategy")))
@protocol Sportstalk247KitKotlinx_serialization_coreSerializationStrategy
@required
- (void)serializeEncoder:(id<Sportstalk247KitKotlinx_serialization_coreEncoder>)encoder value:(id _Nullable)value __attribute__((swift_name("serialize(encoder:value:)")));
@property (readonly) id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDeserializationStrategy")))
@protocol Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy
@required
- (id _Nullable)deserializeDecoder:(id<Sportstalk247KitKotlinx_serialization_coreDecoder>)decoder __attribute__((swift_name("deserialize(decoder:)")));
@property (readonly) id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor> descriptor __attribute__((swift_name("descriptor")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreKSerializer")))
@protocol Sportstalk247KitKotlinx_serialization_coreKSerializer <Sportstalk247KitKotlinx_serialization_coreSerializationStrategy, Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy>
@required
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinArray")))
@interface Sportstalk247KitKotlinArray<T> : Sportstalk247KitBase
+ (instancetype)arrayWithSize:(int32_t)size init:(T _Nullable (^)(Sportstalk247KitInt *))init __attribute__((swift_name("init(size:init:)")));
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
- (T _Nullable)getIndex:(int32_t)index __attribute__((swift_name("get(index:)")));
- (id<Sportstalk247KitKotlinIterator>)iterator __attribute__((swift_name("iterator()")));
- (void)setIndex:(int32_t)index value:(T _Nullable)value __attribute__((swift_name("set(index:value:)")));
@property (readonly) int32_t size __attribute__((swift_name("size")));
@end


/**
 * @note annotations
 *   kotlinx.serialization.Serializable(with=NormalClass(value=kotlinx/serialization/json/JsonElementSerializer))
*/
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement")))
@interface Sportstalk247KitKotlinx_serialization_jsonJsonElement : Sportstalk247KitBase
@property (class, readonly, getter=companion) Sportstalk247KitKotlinx_serialization_jsonJsonElementCompanion *companion __attribute__((swift_name("companion")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinEnumCompanion")))
@interface Sportstalk247KitKotlinEnumCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitKotlinEnumCompanion *shared __attribute__((swift_name("shared")));
@end

__attribute__((swift_name("Kotlinx_coroutines_coreFlowCollector")))
@protocol Sportstalk247KitKotlinx_coroutines_coreFlowCollector
@required

/**
 * @note This method converts instances of CancellationException to errors.
 * Other uncaught Kotlin exceptions are fatal.
*/
- (void)emitValue:(id _Nullable)value completionHandler:(void (^)(NSError * _Nullable))completionHandler __attribute__((swift_name("emit(value:completionHandler:)")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreEncoder")))
@protocol Sportstalk247KitKotlinx_serialization_coreEncoder
@required
- (id<Sportstalk247KitKotlinx_serialization_coreCompositeEncoder>)beginCollectionDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor collectionSize:(int32_t)collectionSize __attribute__((swift_name("beginCollection(descriptor:collectionSize:)")));
- (id<Sportstalk247KitKotlinx_serialization_coreCompositeEncoder>)beginStructureDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (void)encodeBooleanValue:(BOOL)value __attribute__((swift_name("encodeBoolean(value:)")));
- (void)encodeByteValue:(int8_t)value __attribute__((swift_name("encodeByte(value:)")));
- (void)encodeCharValue:(unichar)value __attribute__((swift_name("encodeChar(value:)")));
- (void)encodeDoubleValue:(double)value __attribute__((swift_name("encodeDouble(value:)")));
- (void)encodeEnumEnumDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)enumDescriptor index:(int32_t)index __attribute__((swift_name("encodeEnum(enumDescriptor:index:)")));
- (void)encodeFloatValue:(float)value __attribute__((swift_name("encodeFloat(value:)")));
- (id<Sportstalk247KitKotlinx_serialization_coreEncoder>)encodeInlineDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("encodeInline(descriptor:)")));
- (void)encodeIntValue:(int32_t)value __attribute__((swift_name("encodeInt(value:)")));
- (void)encodeLongValue:(int64_t)value __attribute__((swift_name("encodeLong(value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNotNullMark __attribute__((swift_name("encodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNull __attribute__((swift_name("encodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableValueSerializer:(id<Sportstalk247KitKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableValue(serializer:value:)")));
- (void)encodeSerializableValueSerializer:(id<Sportstalk247KitKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableValue(serializer:value:)")));
- (void)encodeShortValue:(int16_t)value __attribute__((swift_name("encodeShort(value:)")));
- (void)encodeStringValue:(NSString *)value __attribute__((swift_name("encodeString(value:)")));
@property (readonly) Sportstalk247KitKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerialDescriptor")))
@protocol Sportstalk247KitKotlinx_serialization_coreSerialDescriptor
@required

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NSArray<id<Sportstalk247KitKotlinAnnotation>> *)getElementAnnotationsIndex:(int32_t)index __attribute__((swift_name("getElementAnnotations(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)getElementDescriptorIndex:(int32_t)index __attribute__((swift_name("getElementDescriptor(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (int32_t)getElementIndexName:(NSString *)name __attribute__((swift_name("getElementIndex(name:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (NSString *)getElementNameIndex:(int32_t)index __attribute__((swift_name("getElementName(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)isElementOptionalIndex:(int32_t)index __attribute__((swift_name("isElementOptional(index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSArray<id<Sportstalk247KitKotlinAnnotation>> *annotations __attribute__((swift_name("annotations")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) int32_t elementsCount __attribute__((swift_name("elementsCount")));
@property (readonly) BOOL isInline __attribute__((swift_name("isInline")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) BOOL isNullable __attribute__((swift_name("isNullable")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) Sportstalk247KitKotlinx_serialization_coreSerialKind *kind __attribute__((swift_name("kind")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
@property (readonly) NSString *serialName __attribute__((swift_name("serialName")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreDecoder")))
@protocol Sportstalk247KitKotlinx_serialization_coreDecoder
@required
- (id<Sportstalk247KitKotlinx_serialization_coreCompositeDecoder>)beginStructureDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("beginStructure(descriptor:)")));
- (BOOL)decodeBoolean __attribute__((swift_name("decodeBoolean()")));
- (int8_t)decodeByte __attribute__((swift_name("decodeByte()")));
- (unichar)decodeChar __attribute__((swift_name("decodeChar()")));
- (double)decodeDouble __attribute__((swift_name("decodeDouble()")));
- (int32_t)decodeEnumEnumDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)enumDescriptor __attribute__((swift_name("decodeEnum(enumDescriptor:)")));
- (float)decodeFloat __attribute__((swift_name("decodeFloat()")));
- (id<Sportstalk247KitKotlinx_serialization_coreDecoder>)decodeInlineDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeInline(descriptor:)")));
- (int32_t)decodeInt __attribute__((swift_name("decodeInt()")));
- (int64_t)decodeLong __attribute__((swift_name("decodeLong()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeNotNullMark __attribute__((swift_name("decodeNotNullMark()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (Sportstalk247KitKotlinNothing * _Nullable)decodeNull __attribute__((swift_name("decodeNull()")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableValueDeserializer:(id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeNullableSerializableValue(deserializer:)")));
- (id _Nullable)decodeSerializableValueDeserializer:(id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy>)deserializer __attribute__((swift_name("decodeSerializableValue(deserializer:)")));
- (int16_t)decodeShort __attribute__((swift_name("decodeShort()")));
- (NSString *)decodeString __attribute__((swift_name("decodeString()")));
@property (readonly) Sportstalk247KitKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("KotlinIterator")))
@protocol Sportstalk247KitKotlinIterator
@required
- (BOOL)hasNext __attribute__((swift_name("hasNext()")));
- (id _Nullable)next __attribute__((swift_name("next()")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("Kotlinx_serialization_jsonJsonElement.Companion")))
@interface Sportstalk247KitKotlinx_serialization_jsonJsonElementCompanion : Sportstalk247KitBase
+ (instancetype)alloc __attribute__((unavailable));
+ (instancetype)allocWithZone:(struct _NSZone *)zone __attribute__((unavailable));
+ (instancetype)companion __attribute__((swift_name("init()")));
@property (class, readonly, getter=shared) Sportstalk247KitKotlinx_serialization_jsonJsonElementCompanion *shared __attribute__((swift_name("shared")));
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("serializer()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeEncoder")))
@protocol Sportstalk247KitKotlinx_serialization_coreCompositeEncoder
@required
- (void)encodeBooleanElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(BOOL)value __attribute__((swift_name("encodeBooleanElement(descriptor:index:value:)")));
- (void)encodeByteElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int8_t)value __attribute__((swift_name("encodeByteElement(descriptor:index:value:)")));
- (void)encodeCharElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(unichar)value __attribute__((swift_name("encodeCharElement(descriptor:index:value:)")));
- (void)encodeDoubleElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(double)value __attribute__((swift_name("encodeDoubleElement(descriptor:index:value:)")));
- (void)encodeFloatElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(float)value __attribute__((swift_name("encodeFloatElement(descriptor:index:value:)")));
- (id<Sportstalk247KitKotlinx_serialization_coreEncoder>)encodeInlineElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("encodeInlineElement(descriptor:index:)")));
- (void)encodeIntElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int32_t)value __attribute__((swift_name("encodeIntElement(descriptor:index:value:)")));
- (void)encodeLongElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int64_t)value __attribute__((swift_name("encodeLongElement(descriptor:index:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)encodeNullableSerializableElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<Sportstalk247KitKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeNullableSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeSerializableElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index serializer:(id<Sportstalk247KitKotlinx_serialization_coreSerializationStrategy>)serializer value:(id _Nullable)value __attribute__((swift_name("encodeSerializableElement(descriptor:index:serializer:value:)")));
- (void)encodeShortElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(int16_t)value __attribute__((swift_name("encodeShortElement(descriptor:index:value:)")));
- (void)encodeStringElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index value:(NSString *)value __attribute__((swift_name("encodeStringElement(descriptor:index:value:)")));
- (void)endStructureDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)shouldEncodeElementDefaultDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("shouldEncodeElementDefault(descriptor:index:)")));
@property (readonly) Sportstalk247KitKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreSerializersModule")))
@interface Sportstalk247KitKotlinx_serialization_coreSerializersModule : Sportstalk247KitBase

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (void)dumpToCollector:(id<Sportstalk247KitKotlinx_serialization_coreSerializersModuleCollector>)collector __attribute__((swift_name("dumpTo(collector:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<Sportstalk247KitKotlinx_serialization_coreKSerializer> _Nullable)getContextualKClass:(id<Sportstalk247KitKotlinKClass>)kClass typeArgumentsSerializers:(NSArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *)typeArgumentsSerializers __attribute__((swift_name("getContextual(kClass:typeArgumentsSerializers:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<Sportstalk247KitKotlinx_serialization_coreSerializationStrategy> _Nullable)getPolymorphicBaseClass:(id<Sportstalk247KitKotlinKClass>)baseClass value:(id)value __attribute__((swift_name("getPolymorphic(baseClass:value:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy> _Nullable)getPolymorphicBaseClass:(id<Sportstalk247KitKotlinKClass>)baseClass serializedClassName:(NSString * _Nullable)serializedClassName __attribute__((swift_name("getPolymorphic(baseClass:serializedClassName:)")));
@end

__attribute__((swift_name("KotlinAnnotation")))
@protocol Sportstalk247KitKotlinAnnotation
@required
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerialKind")))
@interface Sportstalk247KitKotlinx_serialization_coreSerialKind : Sportstalk247KitBase
- (NSUInteger)hash __attribute__((swift_name("hash()")));
- (NSString *)description __attribute__((swift_name("description()")));
@end

__attribute__((swift_name("Kotlinx_serialization_coreCompositeDecoder")))
@protocol Sportstalk247KitKotlinx_serialization_coreCompositeDecoder
@required
- (BOOL)decodeBooleanElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeBooleanElement(descriptor:index:)")));
- (int8_t)decodeByteElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeByteElement(descriptor:index:)")));
- (unichar)decodeCharElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeCharElement(descriptor:index:)")));
- (int32_t)decodeCollectionSizeDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeCollectionSize(descriptor:)")));
- (double)decodeDoubleElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeDoubleElement(descriptor:index:)")));
- (int32_t)decodeElementIndexDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("decodeElementIndex(descriptor:)")));
- (float)decodeFloatElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeFloatElement(descriptor:index:)")));
- (id<Sportstalk247KitKotlinx_serialization_coreDecoder>)decodeInlineElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeInlineElement(descriptor:index:)")));
- (int32_t)decodeIntElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeIntElement(descriptor:index:)")));
- (int64_t)decodeLongElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeLongElement(descriptor:index:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (id _Nullable)decodeNullableSerializableElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeNullableSerializableElement(descriptor:index:deserializer:previousValue:)")));

/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
- (BOOL)decodeSequentially __attribute__((swift_name("decodeSequentially()")));
- (id _Nullable)decodeSerializableElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index deserializer:(id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy>)deserializer previousValue:(id _Nullable)previousValue __attribute__((swift_name("decodeSerializableElement(descriptor:index:deserializer:previousValue:)")));
- (int16_t)decodeShortElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeShortElement(descriptor:index:)")));
- (NSString *)decodeStringElementDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor index:(int32_t)index __attribute__((swift_name("decodeStringElement(descriptor:index:)")));
- (void)endStructureDescriptor:(id<Sportstalk247KitKotlinx_serialization_coreSerialDescriptor>)descriptor __attribute__((swift_name("endStructure(descriptor:)")));
@property (readonly) Sportstalk247KitKotlinx_serialization_coreSerializersModule *serializersModule __attribute__((swift_name("serializersModule")));
@end

__attribute__((objc_subclassing_restricted))
__attribute__((swift_name("KotlinNothing")))
@interface Sportstalk247KitKotlinNothing : Sportstalk247KitBase
@end


/**
 * @note annotations
 *   kotlinx.serialization.ExperimentalSerializationApi
*/
__attribute__((swift_name("Kotlinx_serialization_coreSerializersModuleCollector")))
@protocol Sportstalk247KitKotlinx_serialization_coreSerializersModuleCollector
@required
- (void)contextualKClass:(id<Sportstalk247KitKotlinKClass>)kClass provider:(id<Sportstalk247KitKotlinx_serialization_coreKSerializer> (^)(NSArray<id<Sportstalk247KitKotlinx_serialization_coreKSerializer>> *))provider __attribute__((swift_name("contextual(kClass:provider:)")));
- (void)contextualKClass:(id<Sportstalk247KitKotlinKClass>)kClass serializer:(id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)serializer __attribute__((swift_name("contextual(kClass:serializer:)")));
- (void)polymorphicBaseClass:(id<Sportstalk247KitKotlinKClass>)baseClass actualClass:(id<Sportstalk247KitKotlinKClass>)actualClass actualSerializer:(id<Sportstalk247KitKotlinx_serialization_coreKSerializer>)actualSerializer __attribute__((swift_name("polymorphic(baseClass:actualClass:actualSerializer:)")));
- (void)polymorphicDefaultBaseClass:(id<Sportstalk247KitKotlinKClass>)baseClass defaultDeserializerProvider:(id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefault(baseClass:defaultDeserializerProvider:)"))) __attribute__((deprecated("Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer")));
- (void)polymorphicDefaultDeserializerBaseClass:(id<Sportstalk247KitKotlinKClass>)baseClass defaultDeserializerProvider:(id<Sportstalk247KitKotlinx_serialization_coreDeserializationStrategy> _Nullable (^)(NSString * _Nullable))defaultDeserializerProvider __attribute__((swift_name("polymorphicDefaultDeserializer(baseClass:defaultDeserializerProvider:)")));
- (void)polymorphicDefaultSerializerBaseClass:(id<Sportstalk247KitKotlinKClass>)baseClass defaultSerializerProvider:(id<Sportstalk247KitKotlinx_serialization_coreSerializationStrategy> _Nullable (^)(id))defaultSerializerProvider __attribute__((swift_name("polymorphicDefaultSerializer(baseClass:defaultSerializerProvider:)")));
@end

__attribute__((swift_name("KotlinKDeclarationContainer")))
@protocol Sportstalk247KitKotlinKDeclarationContainer
@required
@end

__attribute__((swift_name("KotlinKAnnotatedElement")))
@protocol Sportstalk247KitKotlinKAnnotatedElement
@required
@end


/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
__attribute__((swift_name("KotlinKClassifier")))
@protocol Sportstalk247KitKotlinKClassifier
@required
@end

__attribute__((swift_name("KotlinKClass")))
@protocol Sportstalk247KitKotlinKClass <Sportstalk247KitKotlinKDeclarationContainer, Sportstalk247KitKotlinKAnnotatedElement, Sportstalk247KitKotlinKClassifier>
@required

/**
 * @note annotations
 *   kotlin.SinceKotlin(version="1.1")
*/
- (BOOL)isInstanceValue:(id _Nullable)value __attribute__((swift_name("isInstance(value:)")));
@property (readonly) NSString * _Nullable qualifiedName __attribute__((swift_name("qualifiedName")));
@property (readonly) NSString * _Nullable simpleName __attribute__((swift_name("simpleName")));
@end

#pragma pop_macro("_Nullable_result")
#pragma clang diagnostic pop
NS_ASSUME_NONNULL_END
