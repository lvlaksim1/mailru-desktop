# Карта API по критерию access_token

Критерий: любой узел допустим, если официальный клиент может выполнить операцию с токеном ru.mail.oauth2.access.

Механизм: OAuthSession получает ru.mail.oauth2.access; TornadoSession передает его как access_token; ServerCommandBase переключает OAuth-совместимые команды на TORNADO.

Всего маршрутов: 58
Совместимы с access_token: 58
Не подтверждены как access_token: 0

## Совместимы с access_token

| метод | маршрут | класс | тип | узел |
|---|---|---|---|---|
| GET | /api/v1/m/threads/status/smart | BatchSmartStatusCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/m/threads/thread | ThreadRequestCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/m/threads/marks | MarkThreadCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/m/threads/move | MoveThreadCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/m/threads/remove | RemoveThreadCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/m/threads/services/spam | SpamThreadCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/m/threads/services/unspam | UnspamThreadCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/status | MessagesStatusCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/message | MailMessageRequestCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/attaches | MessageAttachesRequestCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/attaches/add | TornadoUploadRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/attaches/remove | TornadoRemoveRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/attaches/reattach | TornadoReattachRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/attaches/view | DownloadFileInMemoryCmd | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/marks | MarkMessageCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/marks/all | MarkAllMessageCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/move | TornadoMoveMessage | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/move/all | MoveAllMessageCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/remove | TornadoRemoveMessage | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/remove/all | RemoveAllMessageCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/services/spam | TornadoSpamAbuse | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/services/unspam | TornadoNoSpam | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/services/unsubscribe | UnsubscribeMessageCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/services/bounce | TornadoRedirectRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/services/category/change | ChangeMessageCategoryRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/services/category/feedback | MailCategoryFeedbackCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/send | TornadoSendRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/draft | TornadoDraftRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/schedule | TornadoScheduleRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/search | MessagesSearchCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/go/search/emails | MessagesSearchCommandNew | TORNADO_MPOP | go.mail.ru |
| GET | /api/v1/messages/search/suggest | GetSuggestionsCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/replies/smart | SmartReplyRequestCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/snoozes/update | UpdateSnoozeRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/snoozes/remove | RemoveSnoozeRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/colortags/set | SetCustomTagsCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/colortags/unset | UnsetCustomTagsCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/meta | RequestMessageMetaCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/notify/read | MailReadVerifyRequest | TORNADO | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/message/download | DownloadMessageEmlCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/messages/message/summarize | MailSummarizeRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/messages/count/all | CountAllRequestCmd | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/folders | DirectoriesListRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/add | CreateFolder | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/edit | UpdateFolder | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/remove | DeleteFolder | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/clear | TornadoCleanFolder | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/archive/ensure | CreateArchiveFolderCmd | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/open | FolderLoginCommandImpl | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/folders/close | FoldersLogoutCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/filters | RequestFiltersCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/filters/add | AddFilterCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/filters/edit | UpdateFilterCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/filters/remove | DeleteFilter | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/ab/smart | AddressBookFetchV2 | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/ab/fast | SearchPeopleCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| GET | /api/v1/ab/lastseen | UsersLastSeenRequest | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
| POST | /api/v1/attaches/add/to-cloud | SaveAttachmentsToCloudCommand | TORNADO_MPOP | aj-https.mail.ru / alt-aj-https.mail.ru family |
