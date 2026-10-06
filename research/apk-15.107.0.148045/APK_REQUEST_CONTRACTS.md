# Договоры запросов AJ, автоматически извлечённые из APK

Источник: ru.mail.mailapp 15.107.0.148045.

Общее правило: access_token добавляется TornadoSession; lang добавляется ServerCommandBaseParams; email добавляется командами на ServerCommandEmailParams.

## GET /api/v1/m/threads/status/smart

Класс: BatchSmartStatusCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| metathread_subjects | DEFAULT |  |  | private static final boolean mMetathreadSubjects = true; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| folders | DEFAULT |  | getFolders | private final List<Folder> mFolders; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| BatchSmartStatusCommand.PARAM_KEY_LAST_MODIFIED | DEFAULT |  |  | private final long mLastModified; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| refresh_mailbox | DEFAULT |  |  | private final Integer mRefreshMailbox; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| reset_nc | DEFAULT |  |  | private final boolean mResetNewEmailsCount; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| snippet_limit | DEFAULT |  | getSnippetLimitValue | private final int mSnippetLimit; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| u_known | DEFAULT |  |  | private final boolean mUserKnown; | decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, folders, id, value

Исходники: decompiled/packages/ru/mail/data/cmd/server/BatchSmartStatusCommand.java

## GET /api/v1/m/threads/thread

Класс: ThreadRequestCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ThreadRequestCommand.PARAM_KEY_REFRESH_MAILBOX | GET |  | getRefreshMailBoxQueryValue | private static final int PARAM_VALUE_REFRESH_MAILBOX = 1; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| ThreadRequestCommand.PARAM_KEY_LIMIT | DEFAULT |  |  | private final int mCount; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| folder | DEFAULT |  | getFolderIdIfNecessary | private String mFolderId; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| ThreadRequestCommand.PARAM_KEY_LAST_MODIFIED | DEFAULT |  | getLastModified | private final long mLastModified; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| ThreadRequestCommand.PARAM_KEY_OFFSET | DEFAULT |  |  | private final int mOffset; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| ThreadRequestCommand.PARAM_KEY_SNIPPET_LIMIT | DEFAULT |  |  | private final int mSnippetLimit; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |
| id | DEFAULT |  |  | private final String mThreadId; | decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, id, thread_id, value

Исходники: decompiled/packages/ru/mail/data/cmd/server/ThreadRequestCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/m/threads/marks

Класс: MarkThreadCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| email | POST |  |  | private final String mEmail; | decompiled/packages/ru/mail/data/cmd/server/MarkThreadCommand.java |
| email | POST |  |  | private final String mEmail; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |
| PARAM_KEY_THREAD_IDS | POST |  | getSerializedRepresentations | private final Collection<MailThreadRepresentation> mRepresentations; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |
| PARAM_KEY_MARKS | POST |  | getMarks | private String mMarks; | decompiled/packages/ru/mail/data/cmd/server/MarkCommandBaseParams.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/MarkThreadCommand.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostServerRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java, decompiled/packages/ru/mail/data/cmd/server/MarkCommandBaseParams.java

## POST /api/v1/m/threads/move

Класс: MoveThreadCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder | POST |  |  | private final long mFolderId; | decompiled/packages/ru/mail/data/cmd/server/MoveThreadCommand.java |
| email | POST |  |  | private final String mEmail; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |
| PARAM_KEY_THREAD_IDS | POST |  | getSerializedRepresentations | private final Collection<MailThreadRepresentation> mRepresentations; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/MoveThreadCommand.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostServerRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java

## POST /api/v1/m/threads/remove

Класс: RemoveThreadCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| email | POST |  |  | private final String mEmail; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |
| PARAM_KEY_THREAD_IDS | POST |  | getSerializedRepresentations | private final Collection<MailThreadRepresentation> mRepresentations; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/RemoveThreadCommand.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostServerRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java

## POST /api/v1/m/threads/services/spam

Класс: SpamThreadCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| SpamThreadCommand.PARAM_KEY_VERIFIED | POST |  |  | private final boolean mIsVerified; | decompiled/packages/ru/mail/data/cmd/server/SpamThreadCommand.java |
| email | POST |  |  | private final String mEmail; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |
| PARAM_KEY_THREAD_IDS | POST |  | getSerializedRepresentations | private final Collection<MailThreadRepresentation> mRepresentations; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/SpamThreadCommand.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostServerRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java

## POST /api/v1/m/threads/services/unspam

Класс: UnspamThreadCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| email | POST |  |  | private final String mEmail; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |
| PARAM_KEY_THREAD_IDS | POST |  | getSerializedRepresentations | private final Collection<MailThreadRepresentation> mRepresentations; | decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/UnspamThreadCommand.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostServerRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/ThreadPostBaseParams.java

## GET /api/v1/messages/status

Класс: MessagesStatusCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| MessagesStatusCommand.PARAM_KEY_PREFETCH | GET |  | getPrefetchQueryValue | private static final int PARAM_VALUE_PREFETCH = 1; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| MessagesStatusCommand.PARAM_KEY_REFRESH_MAILBOX | GET |  | getRefreshMailBoxQueryValue | private static final int PARAM_VALUE_REFRESH_MAILBOX = 1; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| MessagesStatusCommand.PARAM_KEY_SORT | GET |  |  | private static final String PARAM_VALUE_SORT = "{\"type\":\"id\", \"order\":\"desc\"}"; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| MessagesStatusCommand.PARAM_KEY_LIMIT | GET |  |  | private final int mCount; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| folder | GET |  |  | private final long mFolderId; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| MessagesStatusCommand.PARAM_KEY_LAST_MODIFIED | DEFAULT |  | getLastModified | private final long mLastModified; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| MessagesStatusCommand.PARAM_KEY_OFFSET | GET |  |  | private final int mOffset; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| MessagesStatusCommand.PARAM_KEY_SNIPPET_LIMIT | GET |  | getSnippetLimitQueryValue | private final Integer mSnippetLimit; | decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java |
| Accept-Encoding | HEADER_ADD |  | getAcceptEncoding | private String mAcceptEncoding; | decompiled/packages/ru/mail/data/cmd/server/RequestWithImapActivation.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, folders, messages

Исходники: decompiled/packages/ru/mail/data/cmd/server/MessagesStatusCommand.java, decompiled/packages/ru/mail/data/cmd/server/RequestWithImapActivation.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/message

Класс: MailMessageRequestCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| let_body_type | GET |  |  | private static final String BODY_TYPE = "let_body"; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| no_banner | GET |  |  | private static final String NO_BANNER = "Y"; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| htmlencoded | GET |  |  | private static final boolean mHtmlEncoded = false; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| bulk_show_images | DEFAULT |  |  | private final int mBulkShowImages; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| disable_quotation_parser | DEFAULT |  |  | private final boolean mDisabledQuotationParser; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| X-DomPurify-Version | HEADER_ADD |  |  | private final String mDomPurifyHeader; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| folder_id | GET |  |  | private final String mFolderId; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| thumbnails | GET |  |  | private final int mHtmlThumbnails; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| id | GET |  |  | private final String mId; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| mark_read | GET |  |  | private final boolean mMarkRead; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| use_color_scheme | GET |  |  | private final int mUseColorScheme; | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| ajax_call | GET |  |  | private static final String AJAX_CALL = String.valueOf(1); | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| multi_msg_prev | GET |  |  | private static final String MULTI_MSG_PREV = String.valueOf(0); | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| multi_msg_past | GET |  |  | private static final String MULTI_MSG_PAST = String.valueOf(0); | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| mobile | GET |  |  | private static final String MOBILE = String.valueOf(1); | decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, value

Исходники: decompiled/packages/ru/mail/data/cmd/server/MailMessageRequestCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/attaches

Класс: MessageAttachesRequestCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| attach_types | GET |  | getAttachTypes | private final String[] mAttachTypes; | decompiled/packages/ru/mail/data/cmd/server/MessageAttachesRequestCommand.java |
| id | GET |  |  | private final String mId; | decompiled/packages/ru/mail/data/cmd/server/MessageAttachesRequestCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: attaches, body, error, list, value

Исходники: decompiled/packages/ru/mail/data/cmd/server/MessageAttachesRequestCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/messages/attaches/add

Класс: TornadoUploadRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| message_id | POST |  |  | private final String mMessageId; | decompiled/packages/ru/mail/data/cmd/server/TornadoUploadRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: attach, body, id

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoUploadRequest.java, decompiled/packages/ru/mail/data/cmd/server/UploadFileCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/messages/attaches/remove

Класс: TornadoRemoveRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST |  | getAttachesJson | private final List<Attach> mAttaches; | decompiled/packages/ru/mail/data/cmd/server/TornadoRemoveRequest.java |
| message_id | POST |  |  | private final String mMessageId; | decompiled/packages/ru/mail/data/cmd/server/TornadoRemoveRequest.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoRemoveRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/attaches/reattach

Класс: TornadoReattachRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| forwarded_id | POST |  |  | private final String mForwardedId; | decompiled/packages/ru/mail/data/cmd/server/TornadoReattachRequest.java |
| message_id | POST |  |  | private final String mMessageId; | decompiled/packages/ru/mail/data/cmd/server/TornadoReattachRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: attach, body, error, error_files, id, okay_files, origin_id, type

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoReattachRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/attaches/view

Класс: DownloadFileInMemoryCmd

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| type | GET |  |  | private static final String TYPE = "attach"; | decompiled/packages/ru/mail/data/cmd/server/DownloadFileInMemoryCmd.java |
| id | GET |  |  | private final String mId; | decompiled/packages/ru/mail/data/cmd/server/DownloadFileInMemoryCmd.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/DownloadFileInMemoryCmd.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/messages/marks

Класс: MarkMessageCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| PARAM_KEY_MARKS | POST |  | getMarks | private String mMarks; | decompiled/packages/ru/mail/data/cmd/server/MarkCommandBaseParams.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/MarkMessageCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java, decompiled/packages/ru/mail/data/cmd/server/MarkCommandBaseParams.java

## POST /api/v1/messages/marks/all

Класс: MarkAllMessageCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| action | POST |  |  | private final String mAction; | decompiled/packages/ru/mail/data/cmd/server/MarkAllMessageCommand.java |
| folder | POST |  |  | private final long mFolderId; | decompiled/packages/ru/mail/data/cmd/server/MarkAllMessageCommand.java |
| older_than | POST |  |  | private final Long mMarkTime; | decompiled/packages/ru/mail/data/cmd/server/MarkAllMessageCommand.java |
| marks | POST |  | getMarks | private final String[] mMarks; | decompiled/packages/ru/mail/data/cmd/server/MarkAllMessageCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/MarkAllMessageCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/move

Класс: TornadoMoveMessage

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder | POST | STRING |  | private final long mDestinationFolder; | decompiled/packages/ru/mail/data/cmd/server/TornadoMoveMessage.java |
| ids | POST | STRING | getMessageIdsAsJsonString | private final String[] mMessageIds; | decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java |

Ключи JSON, читаемые непосредственно этой цепочкой: folder

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoMoveMessage.java, decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/move/all

Класс: MoveAllMessageCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder_from | POST |  |  | private final long mFolderIdFrom; | decompiled/packages/ru/mail/data/cmd/server/MoveAllMessageCommand.java |
| folder | POST |  |  | private final long mFolderIdTo; | decompiled/packages/ru/mail/data/cmd/server/MoveAllMessageCommand.java |
| from | POST |  | getFromJsonArray | private List<String> mFrom; | decompiled/packages/ru/mail/data/cmd/server/MoveAllMessageCommand.java |
| only_newsletters | POST |  |  | private Boolean mIsNewslettersOnly; | decompiled/packages/ru/mail/data/cmd/server/MoveAllMessageCommand.java |
| older_than | POST |  |  | private Long mMoveTime; | decompiled/packages/ru/mail/data/cmd/server/MoveAllMessageCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/MoveAllMessageCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/remove

Класс: TornadoRemoveMessage

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST | STRING | getMessageIdsAsJsonString | private final String[] mMessageIds; | decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoRemoveMessage.java, decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/remove/all

Класс: RemoveAllMessageCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder_from | POST |  |  | private Long mFolderFromId; | decompiled/packages/ru/mail/data/cmd/server/RemoveAllMessageCommand.java |
| only_newsletters | POST |  |  | private Boolean mIsNewslettersOnly; | decompiled/packages/ru/mail/data/cmd/server/RemoveAllMessageCommand.java |
| spam_folder | POST |  |  | private Boolean mIsSpamFolder; | decompiled/packages/ru/mail/data/cmd/server/RemoveAllMessageCommand.java |
| older_than | POST |  |  | private Long mRemoveTime; | decompiled/packages/ru/mail/data/cmd/server/RemoveAllMessageCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/RemoveAllMessageCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/services/spam

Класс: TornadoSpamAbuse

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder | GET | STRING |  | private final Long mFolderTo; | decompiled/packages/ru/mail/data/cmd/server/TornadoSpamAbuse.java |
| ids | POST | STRING | getMessageIdsAsJsonString | private final String[] mMessageIds; | decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoSpamAbuse.java, decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/services/unspam

Класс: TornadoNoSpam

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST | STRING | getMessageIdsAsJsonString | private final String[] mMessageIds; | decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoNoSpam.java, decompiled/packages/ru/mail/data/cmd/server/TornadoBaseMoveMessage.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/services/unsubscribe

Класс: UnsubscribeMessageCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST |  | getIds | private final String[] mMailMessageIds; | decompiled/packages/ru/mail/data/cmd/server/UnsubscribeMessageCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: error, ids[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/UnsubscribeMessageCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/services/bounce

Класс: TornadoRedirectRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| id | POST |  |  | private final String mId; | decompiled/packages/ru/mail/data/cmd/server/TornadoRedirectRequest.java |
| to | POST |  |  | private final String mTo; | decompiled/packages/ru/mail/data/cmd/server/TornadoRedirectRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, to

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoRedirectRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/services/category/change

Класс: ChangeMessageCategoryRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| KEY_ADD_FILTER | POST |  |  | private final Boolean mAddFilter; | decompiled/packages/ru/mail/data/cmd/server/ChangeMessageCategoryRequest.java |
| category | POST |  |  | private final String mCategory; | decompiled/packages/ru/mail/data/cmd/server/ChangeMessageCategoryRequest.java |
| KEY_DROP_CATEGORY | POST |  |  | private final Boolean mDropCategory; | decompiled/packages/ru/mail/data/cmd/server/ChangeMessageCategoryRequest.java |
| KEY_IDS | POST |  | getMailIds | private final String mMailId; | decompiled/packages/ru/mail/data/cmd/server/ChangeMessageCategoryRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: error, ids[0], status

Исходники: decompiled/packages/ru/mail/data/cmd/server/ChangeMessageCategoryRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/CommandStatus.java

## POST /api/v1/messages/services/category/feedback

Класс: MailCategoryFeedbackCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| category | POST |  |  | private final String category; | decompiled/packages/ru/mail/data/cmd/server/MailCategoryFeedbackCommand.java |
| id | POST |  |  | private final String id; | decompiled/packages/ru/mail/data/cmd/server/MailCategoryFeedbackCommand.java |
| QUERY_PARAM_OTHER_CATEGORY | POST |  |  | private final boolean otherCategory; | decompiled/packages/ru/mail/data/cmd/server/MailCategoryFeedbackCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/MailCategoryFeedbackCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/messages/send

Класс: TornadoSendRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| htmlencoded | GET |  |  | private static final String HTML_ENCODED = String.valueOf(false); | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| attaches | POST | COMPLEX_OBJECT |  | private final Attaches mAttaches; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_QUOTE | POST |  |  | private String mBackendQuote; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| body | POST | PARENT_OBJECT | getBody | private final Body mBody; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| correspondents | POST | PARENT_OBJECT |  | private final Correspondents mCorrespondents; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| from | POST |  |  | private String mFrom; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| id | POST |  |  | private String mId; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| priority | POST |  |  | private int mPriority; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| receipt | POST |  |  | private boolean mReadVerify; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| send_date | POST |  |  | private long mSendDate; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| source | POST | PARENT_OBJECT |  | private final Source mSource; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| subject | POST |  |  | private String mSubject; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_BODY_HTML | POST |  |  | private String mHtml; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| text | POST |  |  | private String mText; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| bcc | POST |  |  | private String mBcc; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| cc | POST |  |  | private String mCc; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| to | POST |  |  | private String mTo; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_DRAFT | POST |  |  | private String mDraft; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| forward | POST |  |  | private String mForward; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_REPLY | POST |  |  | private String mReply; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_SCHEDULE | POST |  |  | private String mSchedule; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoSendRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendParams.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendEditableParams.java

## POST /api/v1/messages/draft

Класс: TornadoDraftRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| htmlencoded | GET |  |  | private static final String HTML_ENCODED = String.valueOf(false); | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| attaches | POST | COMPLEX_OBJECT |  | private final Attaches mAttaches; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_QUOTE | POST |  |  | private String mBackendQuote; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| body | POST | PARENT_OBJECT | getBody | private final Body mBody; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| correspondents | POST | PARENT_OBJECT |  | private final Correspondents mCorrespondents; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| from | POST |  |  | private String mFrom; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| id | POST |  |  | private String mId; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| priority | POST |  |  | private int mPriority; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| receipt | POST |  |  | private boolean mReadVerify; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| send_date | POST |  |  | private long mSendDate; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| source | POST | PARENT_OBJECT |  | private final Source mSource; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| subject | POST |  |  | private String mSubject; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_BODY_HTML | POST |  |  | private String mHtml; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| text | POST |  |  | private String mText; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| bcc | POST |  |  | private String mBcc; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| cc | POST |  |  | private String mCc; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| to | POST |  |  | private String mTo; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_DRAFT | POST |  |  | private String mDraft; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| forward | POST |  |  | private String mForward; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_REPLY | POST |  |  | private String mReply; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_SCHEDULE | POST |  |  | private String mSchedule; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoDraftRequest.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendParams.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendEditableParams.java

## POST /api/v1/messages/schedule

Класс: TornadoScheduleRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| htmlencoded | GET |  |  | private static final String HTML_ENCODED = String.valueOf(false); | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| attaches | POST | COMPLEX_OBJECT |  | private final Attaches mAttaches; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_QUOTE | POST |  |  | private String mBackendQuote; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| body | POST | PARENT_OBJECT | getBody | private final Body mBody; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| correspondents | POST | PARENT_OBJECT |  | private final Correspondents mCorrespondents; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| from | POST |  |  | private String mFrom; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| id | POST |  |  | private String mId; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| priority | POST |  |  | private int mPriority; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| receipt | POST |  |  | private boolean mReadVerify; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| send_date | POST |  |  | private long mSendDate; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| source | POST | PARENT_OBJECT |  | private final Source mSource; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| subject | POST |  |  | private String mSubject; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_BODY_HTML | POST |  |  | private String mHtml; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| text | POST |  |  | private String mText; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| bcc | POST |  |  | private String mBcc; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| cc | POST |  |  | private String mCc; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| to | POST |  |  | private String mTo; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_DRAFT | POST |  |  | private String mDraft; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| forward | POST |  |  | private String mForward; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_REPLY | POST |  |  | private String mReply; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |
| TornadoSendRequest.FIELD_SCHEDULE | POST |  |  | private String mSchedule; | decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoScheduleRequest.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendParams.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendParamsImpl.java, decompiled/packages/ru/mail/data/cmd/server/TornadoSendEditableParams.java

## GET /api/v1/messages/search

Класс: MessagesSearchCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| aqid | DEFAULT |  |  | private final String mAqid; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| search_categories | DEFAULT |  |  | private final JSONArray mCategory; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| correspondents | DEFAULT |  |  | private final String mCorrespondents; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| custom_tags | DEFAULT |  |  | private final JSONArray mCustomTags; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| interval | DEFAULT |  |  | private final String mDateRange; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| Collector.FLAGS | DEFAULT |  |  | private final String mFlags; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| folder | DEFAULT |  |  | private final String mFolder; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| htmlencoded | DEFAULT |  |  | private final Boolean mHtmlEncodingEnabled; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| in_excluded_folders | DEFAULT |  |  | private final Boolean mInExcludedFolders; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| limit | DEFAULT |  |  | private final Integer mLimit; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| offset | DEFAULT |  |  | private final Integer mOffset; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| query | DEFAULT |  |  | private final String mQuery; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| remove_emoji_opts | DEFAULT |  |  | private final String mRemoveEmojiFlags; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| snippet_limit | DEFAULT |  |  | private final Integer mSnippetLimit; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| subject | DEFAULT |  |  | private final String mSubject; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| with_threads | DEFAULT |  |  | private final Boolean mThreadIdEnabled; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |
| transaction_category | DEFAULT |  |  | private final String mTransactCategory; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, folders, found, messages

Исходники: decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/go/search/emails

Класс: MessagesSearchCommandNew

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| aqid | DEFAULT |  |  | private final String aqid; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java |
| filters | DEFAULT |  |  | private final String filters; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java |
| limit | DEFAULT |  |  | private final Integer mLimit; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java |
| offset | DEFAULT |  |  | private final Integer mOffset; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java |
| snippet_limit | DEFAULT |  |  | private final int mSnippetLimit; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java |
| q | DEFAULT |  |  | private final String query; | decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, code, count, error, found, host, mail_search_messages, messages, mruRequestId, qid, response, result, status

Исходники: decompiled/packages/ru/mail/data/cmd/server/MessagesSearchCommandNew.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/search/suggest

Класс: GetSuggestionsCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| query | GET |  | getQuery | private final String mSubWord; | decompiled/packages/ru/mail/data/cmd/server/GetSuggestionsCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/GetSuggestionsCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/GetSuggestionsCommandResult.java

## POST /api/v1/messages/replies/smart

Класс: SmartReplyRequestCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| id | POST |  |  | private final String mId; | decompiled/packages/ru/mail/data/cmd/server/SmartReplyRequestCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, id

Исходники: decompiled/packages/ru/mail/data/cmd/server/SmartReplyRequestCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/messages/snoozes/update

Класс: UpdateSnoozeRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| id | POST |  |  | private final String mailId; | decompiled/packages/ru/mail/data/cmd/server/UpdateSnoozeRequest.java |
| date | POST |  | getDateInSec | private final long snoozeDate; | decompiled/packages/ru/mail/data/cmd/server/UpdateSnoozeRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: date, error, ids[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/UpdateSnoozeRequest.java, decompiled/packages/ru/mail/data/cmd/server/AbstractSnoozeRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/snoozes/remove

Класс: RemoveSnoozeRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| id | POST |  |  | private final String mailId; | decompiled/packages/ru/mail/data/cmd/server/RemoveSnoozeRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: date, error, ids[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/RemoveSnoozeRequest.java, decompiled/packages/ru/mail/data/cmd/server/AbstractSnoozeRequest.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/colortags/set

Класс: SetCustomTagsCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| custom_tags | POST |  |  | private final String customTags; | decompiled/packages/ru/mail/data/cmd/server/SetCustomTagsCommand.java |
| default_tags | POST |  |  | private final String defaultTags; | decompiled/packages/ru/mail/data/cmd/server/SetCustomTagsCommand.java |
| folder_id | POST |  |  | private final long folderId; | decompiled/packages/ru/mail/data/cmd/server/SetCustomTagsCommand.java |
| ids | POST |  |  | private final String msgIds; | decompiled/packages/ru/mail/data/cmd/server/SetCustomTagsCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/SetCustomTagsCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/messages/colortags/unset

Класс: UnsetCustomTagsCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| custom_tags | POST |  |  | private final String customTags; | decompiled/packages/ru/mail/data/cmd/server/UnsetCustomTagsCommand.java |
| default_tags | POST |  |  | private final String defaultTags; | decompiled/packages/ru/mail/data/cmd/server/UnsetCustomTagsCommand.java |
| folder_id | POST |  |  | private final long folderId; | decompiled/packages/ru/mail/data/cmd/server/UnsetCustomTagsCommand.java |
| ids | POST |  |  | private final String msgIds; | decompiled/packages/ru/mail/data/cmd/server/UnsetCustomTagsCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/UnsetCustomTagsCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/meta

Класс: RequestMessageMetaCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| uidl | GET |  |  | private final String id; | decompiled/packages/ru/mail/data/cmd/server/RequestMessageMetaCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, meta

Исходники: decompiled/packages/ru/mail/data/cmd/server/RequestMessageMetaCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/notify/read

Класс: MailReadVerifyRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| id | DEFAULT |  |  | private final String msgId; | decompiled/packages/ru/mail/data/cmd/server/MailReadVerifyRequest.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/MailReadVerifyRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/messages/message/download

Класс: DownloadMessageEmlCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder_id | POST |  |  | private final long folderId; | decompiled/packages/ru/mail/data/cmd/server/DownloadMessageEmlCommand.java |
| id | POST |  |  | private final String messageId; | decompiled/packages/ru/mail/data/cmd/server/DownloadMessageEmlCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/DownloadMessageEmlCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/messages/message/summarize

Класс: MailSummarizeRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| streaming | DEFAULT |  |  | private final boolean chunked; | decompiled/packages/ru/mail/data/cmd/server/summarize/SummarizeParams.java |
| group | DEFAULT |  |  | private final String group; | decompiled/packages/ru/mail/data/cmd/server/summarize/SummarizeParams.java |
| id | DEFAULT |  |  | private final String msgId; | decompiled/packages/ru/mail/data/cmd/server/summarize/SummarizeParams.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/summarize/MailSummarizeRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/summarize/SummarizeParams.java

## POST /api/v1/messages/count/all

Класс: CountAllRequestCmd

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder | POST |  |  | private final long mFolderId; | decompiled/packages/ru/mail/data/cmd/server/CountAllRequestCmd.java |
| from | POST |  | getFromJsonArray | private final List<String> mFrom; | decompiled/packages/ru/mail/data/cmd/server/CountAllRequestCmd.java |
| only_newsletters | POST |  | isNewslettersOnly | private final boolean mIsNewslettersOnly; | decompiled/packages/ru/mail/data/cmd/server/CountAllRequestCmd.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, count

Исходники: decompiled/packages/ru/mail/data/cmd/server/CountAllRequestCmd.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/folders

Класс: DirectoriesListRequest

Явных @Param в доступной цепочке классов не найдено.

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/DirectoriesListRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/folders/add

Класс: CreateFolder

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| (не задано) | POST |  |  | private String email; | decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java |
| (не задано) | POST |  |  | private String folders; | decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, folders[0].id

Исходники: decompiled/packages/ru/mail/data/cmd/server/CreateFolder.java, decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/folders/edit

Класс: UpdateFolder

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| (не задано) | POST |  |  | private String email; | decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java |
| (не задано) | POST |  |  | private String folders; | decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, folders[0].id

Исходники: decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/folders/remove

Класс: DeleteFolder

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| (не задано) | POST |  |  | private static final String ids = ""; | decompiled/packages/ru/mail/data/cmd/server/DeleteFolder.java |
| (не задано) | POST |  |  | private String email; | decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java |
| (не задано) | POST |  |  | private String folders; | decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, folders[0].id, ids[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/DeleteFolder.java, decompiled/packages/ru/mail/data/cmd/server/UpdateFolder.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/folders/clear

Класс: TornadoCleanFolder

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST | STRING | getFolderIds | private final long[] mFolderIds; | decompiled/packages/ru/mail/data/cmd/server/TornadoCleanFolder.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/TornadoCleanFolder.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## POST /api/v1/folders/archive/ensure

Класс: CreateArchiveFolderCmd

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folder | GET | STRING |  | private final Long folderId; | decompiled/packages/ru/mail/data/cmd/server/CreateArchiveFolderCmd.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/CreateArchiveFolderCmd.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/folders/open

Класс: FolderLoginCommandImpl

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| folders | POST |  |  | private final String folders; | decompiled/packages/ru/mail/data/cmd/server/FolderLoginCommandImpl.java |

Ключи JSON, читаемые непосредственно этой цепочкой: error

Исходники: decompiled/packages/ru/mail/data/cmd/server/FolderLoginCommandImpl.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/data/cmd/server/FolderLoginCommand.java

## POST /api/v1/folders/close

Класс: FoldersLogoutCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST |  |  | private final String ids; | decompiled/packages/ru/mail/data/cmd/server/FoldersLogoutCommand.java |

Исходники: decompiled/packages/ru/mail/data/cmd/server/FoldersLogoutCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## GET /api/v1/filters

Класс: RequestFiltersCommand

Явных @Param в доступной цепочке классов не найдено.

Ключи JSON, читаемые непосредственно этой цепочкой: applyToSpam, body, conditions, enabled, flag, forward, id, move, name, not, notify, read, reject, remove, value

Исходники: decompiled/packages/ru/mail/data/cmd/server/RequestFiltersCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/filters/add

Класс: AddFilterCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| apply_folders | POST |  |  | private String applyFolders; | decompiled/packages/ru/mail/data/cmd/server/AddFilterCommand.java |
| (не задано) | POST |  |  | private String filters; | decompiled/packages/ru/mail/data/cmd/server/AddFilterCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, filters[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/AddFilterCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/filters/edit

Класс: UpdateFilterCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| apply_folders | POST |  |  | private String applyFolders; | decompiled/packages/ru/mail/data/cmd/server/AddFilterCommand.java |
| (не задано) | POST |  |  | private String filters; | decompiled/packages/ru/mail/data/cmd/server/AddFilterCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, filters[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/UpdateFilterCommand.java, decompiled/packages/ru/mail/data/cmd/server/AddFilterCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/filters/remove

Класс: DeleteFilter

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST |  | getMIdsStr | private final String[] mIds; | decompiled/packages/ru/mail/data/cmd/server/DeleteFilter.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/DeleteFilter.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java, decompiled/packages/ru/mail/mailbox/cmd/EmptyResult.java

## GET /api/v1/ab/smart

Класс: AddressBookFetchV2

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| Accept-Encoding | HEADER_ADD |  | getAcceptEncoding | private String mAcceptEncoding; | decompiled/packages/ru/mail/data/cmd/server/AddressBookFetchV2.java |
| limit | GET |  |  | static final int LIMIT = Integer.MAX_VALUE; | decompiled/packages/ru/mail/data/cmd/server/AddressBookFetchV2.java |

Ключи JSON, читаемые непосредственно этой цепочкой: birthday, body, contacts, emails, labels, name, phones, priority, social

Исходники: decompiled/packages/ru/mail/data/cmd/server/AddressBookFetchV2.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/ab/fast

Класс: SearchPeopleCommand

Явных @Param в доступной цепочке классов не найдено.

Ключи JSON, читаемые непосредственно этой цепочкой: body

Исходники: decompiled/packages/ru/mail/data/cmd/server/SearchPeopleCommand.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## GET /api/v1/ab/lastseen

Класс: UsersLastSeenRequest

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| emails | GET |  |  | private final String emails; | decompiled/packages/ru/mail/data/cmd/server/UsersLastSeenRequest.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, email, last_seen, status, status_id

Исходники: decompiled/packages/ru/mail/data/cmd/server/UsersLastSeenRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

## POST /api/v1/attaches/add/to-cloud

Класс: SaveAttachmentsToCloudCommand

| имя/выражение | размещение | тип | получатель | поле | источник |
|---|---|---|---|---|---|
| ids | POST | STRING | getFileIds | private final Collection<Attach> mAttaches; | decompiled/packages/ru/mail/data/cmd/server/SaveAttachmentsToCloudCommand.java |
| folder | POST | STRING | getFolder | private final String mFolderName; | decompiled/packages/ru/mail/data/cmd/server/SaveAttachmentsToCloudCommand.java |

Ключи JSON, читаемые непосредственно этой цепочкой: body, error, ids[0]

Исходники: decompiled/packages/ru/mail/data/cmd/server/SaveAttachmentsToCloudCommand.java, decompiled/packages/ru/mail/serverapi/PostServerRequest.java, decompiled/packages/ru/mail/serverapi/ServerCommandBase.java

