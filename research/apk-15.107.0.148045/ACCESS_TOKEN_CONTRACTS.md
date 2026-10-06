# Договоры API, работающего через access_token

Автоматическая инвентаризация 101 команды из полного повторного разбора APK.

Исходники доступны для: **101**.
Требуют точечной декомпиляции: **0**.

## /api/v1/ab/contacts/backup

Класс: UploadContactsToServerCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| contacts | POST |  |  | private final String contactsJSON; |
| device_id | POST |  |  | private final String deviceId; |

## /api/v1/folders/close

Класс: FoldersLogoutCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ids | POST |  |  | private final String ids; |

## /api/v1/tokens/check

Класс: CheckPhoneConfirmCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body

## /api/v1/messages/send

Класс: TornadoSendRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body

## /api/v1/m/threads/marks

Класс: MarkThreadCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| email | POST |  |  | private final String mEmail; |

## /api/v1/payment/phone

Класс: GetUserVerifiedPhone; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, operator, phone

## /api/v1/golang/user

Класс: GolangGetUserDataCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| Accept-Encoding | HEADER_ADD |  | getAcceptEncoding | private final String acceptEncoding; |

Ключи JSON в коде: 90x90, b2b_flags, birthday, body, city, common_purpose_flags, day, email, metathreads_visible, name, parental_control_can_disable, parental_control_mode, phone, phones, status, theme

## /api/v1/user/mobile/remove

Класс: DeleteAccountCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, id

## /api/v1/ab/smart

Класс: AddressBookFetchV2; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| Accept-Encoding | HEADER_ADD |  | getAcceptEncoding | private String mAcceptEncoding; |
| limit | GET |  |  | static final int LIMIT = Integer.MAX_VALUE; |

Ключи JSON в коде: birthday, body, contacts, emails, labels, name, phones, priority, social

## /api/v1/cloud/attachment/remove

Класс: RemoveFromCloudBundle; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| bundle_id | POST |  |  | private final String mBundleId; |
| file_id | POST |  |  | private final String mFileId; |

## /api/v1/messages/move/all

Класс: MoveAllMessageCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder_from | POST |  |  | private final long mFolderIdFrom; |
| folder | POST |  |  | private final long mFolderIdTo; |
| from | POST |  | getFromJsonArray | private List<String> mFrom; |
| only_newsletters | POST |  |  | private Boolean mIsNewslettersOnly; |
| older_than | POST |  |  | private Long mMoveTime; |

## /api/v1/money/p2p/cancel

Класс: CancelTransactionRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| transaction_id | GET |  |  | private final String mId; |

## /api/v1/messages/move

Класс: TornadoMoveMessage; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder | POST | STRING |  | private final long mDestinationFolder; |

Ключи JSON в коде: folder

## /api/v1/mobauth/get

Класс: RequestSanitizeUrlCommand; исходный тип: TORNADO_MPOP; ресурс узла: swa_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| page | DEFAULT |  |  | private final String page; |

Ключи JSON в коде: body, expires, url

## /api/v1/golang/user/short

Класс: GolangUserShortCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: account_type, b2b_flags, body, common_purpose_flags, domain, esia, login, metathreads_visible, name, parental_control_can_disable, parental_control_mode, privacy_settings, reader_mode, social_bind, theme, vkid

## /api/v1/messages/services/unspam

Класс: TornadoNoSpam; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/messages/remove/all

Класс: RemoveAllMessageCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder_from | POST |  |  | private Long mFolderFromId; |
| only_newsletters | POST |  |  | private Boolean mIsNewslettersOnly; |
| spam_folder | POST |  |  | private Boolean mIsSpamFolder; |
| older_than | POST |  |  | private Long mRemoveTime; |

## /api/v1/messages/attaches/reattach

Класс: TornadoReattachRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| forwarded_id | POST |  |  | private final String mForwardedId; |
| message_id | POST |  |  | private final String mMessageId; |

Ключи JSON в коде: attach, body, error, error_files, id, okay_files, origin_id, type

## /api/v1/messages/marks/all

Класс: MarkAllMessageCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| action | POST |  |  | private final String mAction; |
| folder | POST |  |  | private final long mFolderId; |
| older_than | POST |  |  | private final Long mMarkTime; |
| marks | POST |  | getMarks | private final String[] mMarks; |

## /api/v1/messages/snoozes/update

Класс: UpdateSnoozeRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| id | POST |  |  | private final String mailId; |
| date | POST |  | getDateInSec | private final long snoozeDate; |

## /api/v1/messages/attaches/remove

Класс: TornadoRemoveRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ids | POST |  | getAttachesJson | private final List<Attach> mAttaches; |
| message_id | POST |  |  | private final String mMessageId; |

## /api/v1/golang/user

Класс: UserSecurityCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, extra_emails, phones

## /api/v1/messages/attaches

Класс: TornadoUploadRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| message_id | POST |  |  | private final String mMessageId; |

Ключи JSON в коде: attach, body, id

## /api/v1/messages/attaches

Класс: DownloadFileInMemoryCmd; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| type | GET |  |  | private static final String TYPE = "attach"; |
| id | GET |  |  | private final String mId; |

Ключи JSON в коде: body

## /api/v1/messages/notify/read

Класс: MailReadVerifyRequest; исходный тип: TORNADO; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| id | DEFAULT |  |  | private final String msgId; |

## /api/v1/auth/qr/get

Класс: QrGetInfoCommand; исходный тип: TORNADO; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| token | POST |  |  | private final String autogenToken; |

Ключи JSON в коде: body, login

## /api/v1/messages/snoozes/remove

Класс: RemoveSnoozeRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| id | POST |  |  | private final String mailId; |

## /api/v1/messages/search

Класс: MessagesSearchCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| aqid | DEFAULT |  |  | private final String mAqid; |
| search_categories | DEFAULT |  |  | private final JSONArray mCategory; |
| correspondents | DEFAULT |  |  | private final String mCorrespondents; |
| custom_tags | DEFAULT |  |  | private final JSONArray mCustomTags; |
| interval | DEFAULT |  |  | private final String mDateRange; |
| Collector.FLAGS | DEFAULT |  |  | private final String mFlags; |
| folder | DEFAULT |  |  | private final String mFolder; |
| htmlencoded | DEFAULT |  |  | private final Boolean mHtmlEncodingEnabled; |
| in_excluded_folders | DEFAULT |  |  | private final Boolean mInExcludedFolders; |
| limit | DEFAULT |  |  | private final Integer mLimit; |
| offset | DEFAULT |  |  | private final Integer mOffset; |
| query | DEFAULT |  |  | private final String mQuery; |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; |
| snippet_limit | DEFAULT |  |  | private final Integer mSnippetLimit; |
| subject | DEFAULT |  |  | private final String mSubject; |
| with_threads | DEFAULT |  |  | private final Boolean mThreadIdEnabled; |
| transaction_category | DEFAULT |  |  | private final String mTransactCategory; |

Ключи JSON в коде: body, folders, found, from, messages, to

## /api/v1/folders/open

Класс: FolderLoginCommandImpl; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folders | POST |  |  | private final String folders; |

Ключи JSON в коде: error, folder_password, id

## /api/v1/attaches/to-cloud

Класс: SaveAttachmentsToCloudCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ids | POST | STRING | getFileIds | private final Collection<Attach> mAttaches; |
| folder | POST | STRING | getFolder | private final String mFolderName; |

Ключи JSON в коде: body, error, ids[0]

## /api/v1/filters/remove

Класс: DeleteFilter; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ids | POST |  | getMIdsStr | private final String[] mIds; |

Ключи JSON в коде: body

## /api/v1/messages/attaches

Класс: MessageAttachesRequestCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| attach_types | GET |  | getAttachTypes | private final String[] mAttachTypes; |
| id | GET |  |  | private final String mId; |

Ключи JSON в коде: attaches, body, error, list, value

## /api/v1/user/mobile/phone/change/confirm-current

Класс: CheckPhoneCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body

## /api/v1/messages/services

Класс: UnsubscribeMessageCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ids | POST |  | getIds | private final String[] mMailMessageIds; |

Ключи JSON в коде: error, ids[0]

## RequestConfiguration.MAX_AD_CONTENT_RATING_G

Класс: GetCloudDispatcherCommand; исходный тип: TORNADO_MPOP; ресурс узла: cloud_dispatcher_default_host.

## /api/v1/folders

Класс: CreateFolder; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, only_web

## /api/v1/messages/marks

Класс: MarkMessageCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/messages/services/category/feedback

Класс: MailCategoryFeedbackCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| category | POST |  |  | private final String category; |
| id | POST |  |  | private final String id; |
| QUERY_PARAM_OTHER_CATEGORY | POST |  |  | private final boolean otherCategory; |

Ключи JSON в коде: body

## /api/v1/user

Класс: GetUserDataCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| Accept-Encoding | HEADER_ADD |  | getAcceptEncoding | private String mAcceptEncoding; |

Ключи JSON в коде: 90x90, b2b_flags, birthday, body, city, common_purpose_flags, day, email, name, parental_control_can_disable, parental_control_mode, phone, phones, status, theme

## /api/v1/user/mobile/remove

Класс: DeleteAccountConfirmCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| reg_token | GET |  | getRegTokenStr | private final String mId; |

Ключи JSON в коде: body, id, value

## /api/v1/filters

Класс: RequestFiltersCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: applyToSpam, body, conditions, enabled, flag, forward, id, move, name, not, notify, read, reject, remove, value

## /api/v1/m/threads/thread

Класс: ThreadRequestCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ThreadRequestCommand.PARAM_KEY_REFRESH_MAILBOX | GET |  | getRefreshMailBoxQueryValue | private static final int PARAM_VALUE_REFRESH_MAILBOX = 1; |
| ThreadRequestCommand.PARAM_KEY_LIMIT | DEFAULT |  |  | private final int mCount; |
| folder | DEFAULT |  | getFolderIdIfNecessary | private String mFolderId; |
| ThreadRequestCommand.PARAM_KEY_LAST_MODIFIED | DEFAULT |  | getLastModified | private final long mLastModified; |
| ThreadRequestCommand.PARAM_KEY_OFFSET | DEFAULT |  |  | private final int mOffset; |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; |
| ThreadRequestCommand.PARAM_KEY_SNIPPET_LIMIT | DEFAULT |  |  | private final int mSnippetLimit; |
| id | DEFAULT |  |  | private final String mThreadId; |

Ключи JSON в коде: body, error, id, thread_id, value

## /api/v1/ab/lastseen

Класс: UsersLastSeenRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| emails | GET |  |  | private final String emails; |

Ключи JSON в коде: body, email, last_seen, status, status_id

## /api/v1/tokens/send

Класс: TokensSendCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ivr | GET |  |  | private static final String IVR = String.valueOf(true); |
| ConfirmPhoneFragment.EXT_REG_TOKEN | GET |  | getRegTokenStr | private final String mRegTokenId; |

Ключи JSON в коде: address, body, id, index, transport

## /api/v1/golang/maps/decode

Класс: AddressGeocodingRequestCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| street | POST |  |  | private final String address; |
| city | POST |  |  | private final String city; |

Ключи JSON в коде: body, lat, lon

## /api/v1/clickerproxy/autogen

Класс: RequestClickerTokenCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, ttl

## /api/v1/childbox/auth

Класс: ChildboxAuthCommand; исходный тип: TORNADO_MPOP; ресурс узла: swa_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| login | GET |  |  | private final String accountLogin; |

Ключи JSON в коде: auth_url, body, children, login

## /api/v1/auth/qr/allow

Класс: QrAuthWebCommand; исходный тип: TORNADO; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| token | POST |  |  | private final String autogenToken; |

Ключи JSON в коде: body, login

## /api/v1/messages/message

Класс: MailMessageRequestCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| let_body_type | GET |  |  | private static final String BODY_TYPE = "let_body"; |
| no_banner | GET |  |  | private static final String NO_BANNER = "Y"; |
| htmlencoded | GET |  |  | private static final boolean mHtmlEncoded = false; |
| bulk_show_images | DEFAULT |  |  | private final int mBulkShowImages; |
| disable_quotation_parser | DEFAULT |  |  | private final boolean mDisabledQuotationParser; |
| X-DomPurify-Version | HEADER_ADD |  |  | private final String mDomPurifyHeader; |
| folder_id | GET |  |  | private final String mFolderId; |
| thumbnails | GET |  |  | private final int mHtmlThumbnails; |
| id | GET |  |  | private final String mId; |
| mark_read | GET |  |  | private final boolean mMarkRead; |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; |
| use_color_scheme | GET |  |  | private final int mUseColorScheme; |
| ajax_call | GET |  |  | private static final String AJAX_CALL = String.valueOf(1); |
| multi_msg_prev | GET |  |  | private static final String MULTI_MSG_PREV = String.valueOf(0); |
| multi_msg_past | GET |  |  | private static final String MULTI_MSG_PAST = String.valueOf(0); |
| mobile | GET |  |  | private static final String MOBILE = String.valueOf(1); |

Ключи JSON в коде: body, error, value

## /api/v1/invites/letter

Класс: ShareMailCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| addresses | GET |  | getAddresses | private final List<Contact> contacts; |
| TornadoSendRequest.FIELD_TEMPLATE | GET |  |  | private final String template; |

Ключи JSON в коде: email

## /api/v1/cloud/attachment

Класс: AddToCloudBundle; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| bundle_id | POST |  |  | private final String mBundleId; |
| EventParams.HASH | POST |  |  | private final String mHash; |
| name | POST |  |  | private final String mName; |
| size | POST |  |  | private final long mSize; |

Ключи JSON в коде: body, file_id, name

## /api/v1/messages/colortags/set

Класс: SetCustomTagsCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| custom_tags | POST |  |  | private final String customTags; |
| default_tags | POST |  |  | private final String defaultTags; |
| folder_id | POST |  |  | private final long folderId; |
| ids | POST |  |  | private final String msgIds; |

## /api/v1/go/search/emails

Класс: MessagesSearchCommandNew; исходный тип: TORNADO_MPOP; ресурс узла: search_new_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| aqid | DEFAULT |  |  | private final String aqid; |
| filters | DEFAULT |  |  | private final String filters; |
| limit | DEFAULT |  |  | private final Integer mLimit; |
| offset | DEFAULT |  |  | private final Integer mOffset; |
| snippet_limit | DEFAULT |  |  | private final int mSnippetLimit; |
| q | DEFAULT |  |  | private final String query; |

Ключи JSON в коде: attach, body, code, correspondents, count, error, flagged, folder, found, from, host, interval, mail_search_messages, messages, mruRequestId, pin, qid, query, response, result, status, subject, to, transaction_category, unread

## /api/v1/cloud/status

Класс: GetCloudInfoCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| cloud_overquota_state | POST |  |  | private final Boolean mCloudOverquotaState; |
| remember_overquota | POST |  |  | private final Boolean mRememberOverQuota; |

Ключи JSON в коде: account_type, body, cloud_overquota_block_time, cloud_overquota_start_time, cloud_overquota_state, cloudflags, email, frozen, space, used, used_mail

## /api/v1/cloud/attachment/create

Класс: CreateCloudBundle; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, bundle_id, loader_url

## /api/v1/folders/edit

Класс: UpdateFolder; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| (выражение/не задано) | POST |  |  | private String email; |
| (выражение/не задано) | POST |  |  | private String folders; |

Ключи JSON в коде: body, error, folders[0].id, id, name

## /api/v1/messages/status

Класс: MessagesStatusCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| MessagesStatusCommand.PARAM_KEY_PREFETCH | GET |  | getPrefetchQueryValue | private static final int PARAM_VALUE_PREFETCH = 1; |
| MessagesStatusCommand.PARAM_KEY_REFRESH_MAILBOX | GET |  | getRefreshMailBoxQueryValue | private static final int PARAM_VALUE_REFRESH_MAILBOX = 1; |
| MessagesStatusCommand.PARAM_KEY_SORT | GET |  |  | private static final String PARAM_VALUE_SORT = "{\"type\":\"id\", \"order\":\"desc\"}"; |
| MessagesStatusCommand.PARAM_KEY_LIMIT | GET |  |  | private final int mCount; |
| folder | GET |  |  | private final long mFolderId; |
| MessagesStatusCommand.PARAM_KEY_LAST_MODIFIED | DEFAULT |  | getLastModified | private final long mLastModified; |
| MessagesStatusCommand.PARAM_KEY_OFFSET | GET |  |  | private final int mOffset; |
| MessagesStatusCommand.PARAM_KEY_SNIPPET_LIMIT | GET |  | getSnippetLimitQueryValue | private final Integer mSnippetLimit; |

Ключи JSON в коде: body, folders, messages

## /api/v1/m/threads/services/unspam

Класс: UnspamThreadCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/messages/search/suggest

Класс: GetSuggestionsCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| query | GET |  | getQuery | private final String mSubWord; |

Ключи JSON в коде: body

## /api/v1/filters/edit

Класс: UpdateFilterCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: id

## /api/v1/helpers/update

Класс: SaveHelperOnServerCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| index | POST |  |  | private final int mIndex; |
| update | POST |  |  | private final String mUpdateInfo; |

Ключи JSON в коде: body, close, count, index, show, state, status, time

## /api/v1/sms/send

Класс: ShareSmsCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| phones | GET |  | getPhones | private final List<Contact> contacts; |
| country | GET |  |  | private final String country; |
| SharingPreferenceNavigator.EXTRA_MSG_TYPE | GET |  |  | private final String msgType; |

## /api/v1/golang/geo/cities/city

Класс: GetCityNameCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| city_id | POST |  |  | private final String cityId; |

Ключи JSON в коде: body, city, city_id, name

## /api/v1/messages

Класс: TornadoScheduleRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/messages/services/category/change

Класс: ChangeMessageCategoryRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| KEY_ADD_FILTER | POST |  |  | private final Boolean mAddFilter; |
| category | POST |  |  | private final String mCategory; |
| KEY_DROP_CATEGORY | POST |  |  | private final Boolean mDropCategory; |
| KEY_IDS | POST |  | getMailIds | private final String mMailId; |

Ключи JSON в коде: error, ids[0], status

## /api/v1/filters

Класс: AddFilterCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| apply_folders | POST |  |  | private String applyFolders; |
| (выражение/не задано) | POST |  |  | private String filters; |

Ключи JSON в коде: applyToSpam, body, conditions, conditionsOr, enabled, error, filters[0], flag, move, name, not, read, reject, remove, value

## /api/v1/helpers

Класс: LoadHelpersFromServerCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, close, count, index, show, state, status, time

## /api/v1/messages/message

Класс: DownloadMessageEmlCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder_id | POST |  |  | private final long folderId; |
| id | POST |  |  | private final String messageId; |

## /api/v1/folders/remove

Класс: DeleteFolder; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| (выражение/не задано) | POST |  |  | private static final String ids = ""; |

Ключи JSON в коде: body, error, ids[0]

## /api/v1/messages/smart

Класс: SmartReplyRequestCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| id | POST |  |  | private final String mId; |

Ключи JSON в коде: body, error, id

## /api/v1/messages/colortags/unset

Класс: UnsetCustomTagsCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| custom_tags | POST |  |  | private final String customTags; |
| default_tags | POST |  |  | private final String defaultTags; |
| folder_id | POST |  |  | private final long folderId; |
| ids | POST |  |  | private final String msgIds; |

## /api/v1/filters

Класс: RequestHasFiltersCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, enabled

## /api/v1/user/edit

Класс: UserEditCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/m/threads/move

Класс: MoveThreadCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder | POST |  |  | private final long mFolderId; |

## /api/v1/collectors

Класс: RequestCollectorsCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body

## /api/v1/messages

Класс: TornadoDraftRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/childbox/list

Класс: ChildboxListCommand; исходный тип: TORNADO_MPOP; ресурс узла: swa_default_host.

Ключи JSON в коде: body, children, first_name, image, last_name, login, mail_counter

## /api/v1/user/short

Класс: UserShortCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| Accept-Encoding | HEADER_ADD |  | getAcceptEncoding | private String mAcceptEncoding; |

Ключи JSON в коде: account_type, b2b_flags, body, common_purpose_flags, parental_control_can_disable, parental_control_mode, phone_main, soft_vkid_bind, theme

## /api/v1/m/threads/remove

Класс: RemoveThreadCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/m/threads/services/spam

Класс: SpamThreadCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| SpamThreadCommand.PARAM_KEY_VERIFIED | POST |  |  | private final boolean mIsVerified; |

## /api/v1/folders/clear

Класс: TornadoCleanFolder; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| ids | POST | STRING | getFolderIds | private final long[] mFolderIds; |

## /api/v1/messages/meta

Класс: RequestMessageMetaCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| uidl | GET |  |  | private final String id; |

Ключи JSON в коде: body, meta

## /api/v1/utils/translate

Класс: TranslateLetterCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| QUERY_PARAM_FROM_LANG | POST |  |  | private final String fromLang; |
| QUERY_PARAM_HTML_ENCODED | POST |  |  | private final boolean htmlencoded; |
| message_id | POST |  |  | private final String messageId; |
| query | POST |  |  | private final String query; |
| QUERY_PARAM_TO_LANG | POST |  |  | private final String toLang; |
| QUERY_PARAM_TRANSLATOR | POST |  |  | private final int translator; |

Ключи JSON в коде: body

## /api/v1/user/mobile/phone/change

Класс: ChangePhoneConfirmCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body, new_phone

## /api/v1/golang/user/edit/metathreads

Класс: UserEditMetathreadsCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| (выражение/не задано) | POST | COMPLEX_OBJECT |  | private final Settings settings; |

Ключи JSON в коде: folder_id, state

## /api/v1/aliases

Класс: RequestAliasesCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: alias, body

## /api/v1/messages/services/bounce

Класс: TornadoRedirectRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| id | POST |  |  | private final String mId; |
| to | POST |  |  | private final String mTo; |

Ключи JSON в коде: body, error, to

## /api/v1/user

Класс: ChangeAvatarCommand; исходный тип: TORNADO_MPOP; ресурс узла: change_avatar_default_host.

Ключи JSON в коде: avatar, body, error, mainphoto, status

## /api/v1/folders

Класс: DirectoriesListRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body

## /api/v1/messages/services/spam

Класс: TornadoSpamAbuse; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder | GET | STRING |  | private final Long mFolderTo; |

## /api/v1/messages/count/all

Класс: CountAllRequestCmd; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder | POST |  |  | private final long mFolderId; |
| from | POST |  | getFromJsonArray | private final List<String> mFrom; |
| only_newsletters | POST |  | isNewslettersOnly | private final boolean mIsNewslettersOnly; |

Ключи JSON в коде: body, count

## /api/v1/folders/ensure

Класс: CreateArchiveFolderCmd; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| folder | GET | STRING |  | private final Long folderId; |

Ключи JSON в коде: body

## /api/v1/user/mobile/phone/change

Класс: ChangePhoneCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| phone | GET |  |  | private final String mNewPhone; |
| reg_token_check | GET |  |  | private final String mRegTokenCheck; |

Ключи JSON в коде: body, error, id, phone

## /api/v1/m/status/smart

Класс: BatchSmartStatusCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| metathread_subjects | DEFAULT |  |  | private static final boolean mMetathreadSubjects = true; |
| folders | DEFAULT |  | getFolders | private final List<Folder> mFolders; |
| BatchSmartStatusCommand.PARAM_KEY_LAST_MODIFIED | DEFAULT |  |  | private final long mLastModified; |
| refresh_mailbox | DEFAULT |  |  | private final Integer mRefreshMailbox; |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; |
| reset_nc | DEFAULT |  |  | private final boolean mResetNewEmailsCount; |
| snippet_limit | DEFAULT |  | getSnippetLimitValue | private final int mSnippetLimit; |
| u_known | DEFAULT |  |  | private final boolean mUserKnown; |

Ключи JSON в коде: body, error, folder, folders, id, limit, offset, value

## /api/v1/messages/remove

Класс: TornadoRemoveMessage; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/ab/fast

Класс: SearchPeopleCommand; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

Ключи JSON в коде: body

## /api/v1/messages/message

Класс: MailSummarizeRequest; исходный тип: TORNADO_MPOP; ресурс узла: mail_api_default_host.

## /api/v1/token

Класс: CallsCsrfTokenRequest; исходный тип: TORNADO; ресурс узла: mail_api_default_host.

## /api/v1/user/token

Класс: CallsTokenRequest; исходный тип: TORNADO; ресурс узла: mail_api_default_host.

Ключи JSON в коде: token

## /token

Класс: GetAuthCodeByAccessTokenCommand; исходный тип: TORNADO; ресурс узла: oauth_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| client_id | POST |  |  | private final String clientId; |
| code_challenge | POST |  |  | private String codeChallenge; |
| code_challenge_method | POST |  |  | private String codeChallengeMethod; |
| for_client_id | POST |  |  | private final String forClientId; |
| grant_type | POST |  |  | private final String grantType; |
| requested_token_type | POST |  |  | private final String requestedTokenType; |
| CommonConstant.ReqAccessTokenParam.SCOPE_LABEL | POST |  |  | private final String scope; |
| subject_token | POST |  |  | private final String subjectToken; |
| subject_token_type | POST |  |  | private final String subjectTokenType; |

Ключи JSON в коде: access_token, code, refresh_token

## /api/v1/pushauth/method/set

Класс: ChangeAuthTypeCommand; исходный тип: TORNADO_MPOP; ресурс узла: account_default_host.

| параметр | метод | тип | получатель | поле |
|---|---|---|---|---|
| method | POST |  |  | private final AuthType authType; |
| login | POST |  |  | private final String userLogin; |

Ключи JSON в коде: body, method

