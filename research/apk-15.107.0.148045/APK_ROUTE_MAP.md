# Точная карта почтовых маршрутов из APK 15.107.0.148045

Источник: `ru.mail.mailapp` 15.107.0.148045.

Маршрут извлечён непосредственно из аннотации класса:

`@UrlPath(pathSegments = {...})`.

Метод определён по цепочке наследования:

- `PostServerRequest` / `ThreadPostServerRequest` / `UploadFileCommand` → POST;
- `ServerCommandBase` / `GetServerRequest` → GET.

Таким образом таблица ниже является результатом разбора текущего APK, а не реконструкцией по старым веб-запросам.

| Метод | Маршрут | Класс APK |
|---|---|---|
| GET | `/api/v1/m/threads/status/smart` | `BatchSmartStatusCommand` |
| GET | `/api/v1/m/threads/thread` | `ThreadRequestCommand` |
| POST | `/api/v1/m/threads/marks` | `MarkThreadCommand` |
| POST | `/api/v1/m/threads/move` | `MoveThreadCommand` |
| POST | `/api/v1/m/threads/remove` | `RemoveThreadCommand` |
| POST | `/api/v1/m/threads/services/spam` | `SpamThreadCommand` |
| POST | `/api/v1/m/threads/services/unspam` | `UnspamThreadCommand` |
| GET | `/api/v1/messages/status` | `MessagesStatusCommand` |
| GET | `/api/v1/messages/message` | `MailMessageRequestCommand` |
| GET | `/api/v1/messages/attaches` | `MessageAttachesRequestCommand` |
| POST | `/api/v1/messages/attaches/add` | `TornadoUploadRequest` |
| POST | `/api/v1/messages/attaches/remove` | `TornadoRemoveRequest` |
| POST | `/api/v1/messages/attaches/reattach` | `TornadoReattachRequest` |
| GET | `/api/v1/messages/attaches/view` | `DownloadFileInMemoryCmd` |
| POST | `/api/v1/messages/marks` | `MarkMessageCommand` |
| POST | `/api/v1/messages/marks/all` | `MarkAllMessageCommand` |
| POST | `/api/v1/messages/move` | `TornadoMoveMessage` |
| POST | `/api/v1/messages/move/all` | `MoveAllMessageCommand` |
| POST | `/api/v1/messages/remove` | `TornadoRemoveMessage` |
| POST | `/api/v1/messages/remove/all` | `RemoveAllMessageCommand` |
| POST | `/api/v1/messages/services/spam` | `TornadoSpamAbuse` |
| POST | `/api/v1/messages/services/unspam` | `TornadoNoSpam` |
| POST | `/api/v1/messages/services/unsubscribe` | `UnsubscribeMessageCommand` |
| POST | `/api/v1/messages/services/bounce` | `TornadoRedirectRequest` |
| POST | `/api/v1/messages/services/category/change` | `ChangeMessageCategoryRequest` |
| POST | `/api/v1/messages/services/category/feedback` | `MailCategoryFeedbackCommand` |
| POST | `/api/v1/messages/send` | `TornadoSendRequest` |
| POST | `/api/v1/messages/draft` | `TornadoDraftRequest` |
| POST | `/api/v1/messages/schedule` | `TornadoScheduleRequest` |
| GET | `/api/v1/messages/search` | `MessagesSearchCommand` |
| GET | `/api/v1/go/search/emails` | `MessagesSearchCommandNew` |
| GET | `/api/v1/messages/search/suggest` | `GetSuggestionsCommand` |
| POST | `/api/v1/messages/replies/smart` | `SmartReplyRequestCommand` |
| POST | `/api/v1/messages/snoozes/update` | `UpdateSnoozeRequest` |
| POST | `/api/v1/messages/snoozes/remove` | `RemoveSnoozeRequest` |
| POST | `/api/v1/messages/colortags/set` | `SetCustomTagsCommand` |
| POST | `/api/v1/messages/colortags/unset` | `UnsetCustomTagsCommand` |
| GET | `/api/v1/messages/meta` | `RequestMessageMetaCommand` |
| GET | `/api/v1/messages/notify/read` | `MailReadVerifyRequest` |
| POST | `/api/v1/messages/message/download` | `DownloadMessageEmlCommand` |
| GET | `/api/v1/messages/message/summarize` | `MailSummarizeRequest` |
| POST | `/api/v1/messages/count/all` | `CountAllRequestCmd` |
| GET | `/api/v1/folders` | `DirectoriesListRequest` |
| POST | `/api/v1/folders/add` | `CreateFolder` |
| POST | `/api/v1/folders/edit` | `UpdateFolder` |
| POST | `/api/v1/folders/remove` | `DeleteFolder` |
| POST | `/api/v1/folders/clear` | `TornadoCleanFolder` |
| POST | `/api/v1/folders/archive/ensure` | `CreateArchiveFolderCmd` |
| POST | `/api/v1/folders/open` | `FolderLoginCommandImpl` |
| POST | `/api/v1/folders/close` | `FoldersLogoutCommand` |
| GET | `/api/v1/filters` | `RequestFiltersCommand` |
| POST | `/api/v1/filters/add` | `AddFilterCommand` |
| POST | `/api/v1/filters/edit` | `UpdateFilterCommand` |
| POST | `/api/v1/filters/remove` | `DeleteFilter` |
| GET | `/api/v1/ab/smart` | `AddressBookFetchV2` |
| GET | `/api/v1/ab/fast` | `SearchPeopleCommand` |
| GET | `/api/v1/ab/lastseen` | `UsersLastSeenRequest` |
| POST | `/api/v1/attaches/add/to-cloud` | `SaveAttachmentsToCloudCommand` |

## Связь с AJ

Почтовые классы наследуются от `ServerCommandBase`. Его `MailHostProvider` использует ресурсы `mail_api_default_scheme` и `mail_api_default_host`. В проекте ранее независимо подтверждено, что почтовый узел — `aj-https.mail.ru`; в текущем APK также присутствует резервный `alt-aj-https.mail.ru`.

## Важное различие поиска

В APK одновременно существуют два механизма:

- старый: `GET /api/v1/messages/search`;
- новый: `GET /api/v1/go/search/emails`.

Нужно отдельно установить правило выбора между ними перед реализацией поиска в Mail.ru Desktop.
