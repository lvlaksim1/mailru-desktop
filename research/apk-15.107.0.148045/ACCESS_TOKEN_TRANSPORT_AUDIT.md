# Проверка фактической передачи почтового access_token

Проверяется не только тип TORNADO, но и ближайшие переопределения onSetupSessionInUrl/setUpSession.

Кандидатов: **101**.
Фактически используют тот же почтовый токен по сохранённому коду: **100**.
Ложноположительные/без передачи токена: **1**.

## Способы передачи

- custom-header: 2 команд
- query:access_token: 97 команд
- query:t: 1 команд

## Узлы

- account.mail.ru: 1 команд
- alt-aj-https.mail.ru: 94 команд
- alt-auth.mail.ru: 3 команд
- dispatcher.cloud.mail.ru: 1 команд
- go.mail.ru: 1 команд

## Исключённые после проверки

- QrGetInfoCommand — /api/v1/auth/qr/get — URL session setup disabled in QrGetInfoCommand; network session setup disabled in QrGetInfoCommand

## Полная таблица

| класс | маршрут | узел | передача токена | примечание |
|---|---|---|---|---|
| UploadContactsToServerCommand | /api/v1/ab/contacts/backup | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| FoldersLogoutCommand | /api/v1/folders/close | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CheckPhoneConfirmCommand | /api/v1/tokens/check | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoSendRequest | /api/v1/messages/send | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MarkThreadCommand | /api/v1/m/threads/marks | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| GetUserVerifiedPhone | /api/v1/payment/phone | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| GolangGetUserDataCommand | /api/v1/golang/user | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DeleteAccountCommand | /api/v1/user/mobile/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| AddressBookFetchV2 | /api/v1/ab/smart | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RemoveFromCloudBundle | /api/v1/cloud/attachment/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MoveAllMessageCommand | /api/v1/messages/move/all | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CancelTransactionRequest | /api/v1/money/p2p/cancel | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoMoveMessage | /api/v1/messages/move | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestSanitizeUrlCommand | /api/v1/mobauth/get | alt-auth.mail.ru | query:access_token | delegates to TornadoSession URL setup in RequestSanitizeUrlCommand |
| GolangUserShortCommand | /api/v1/golang/user/short | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoNoSpam | /api/v1/messages/services/unspam | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RemoveAllMessageCommand | /api/v1/messages/remove/all | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoReattachRequest | /api/v1/messages/attaches/reattach | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MarkAllMessageCommand | /api/v1/messages/marks/all | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UpdateSnoozeRequest | /api/v1/messages/snoozes/update | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoRemoveRequest | /api/v1/messages/attaches/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UserSecurityCommand | /api/v1/golang/user | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoUploadRequest | /api/v1/messages/attaches | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DownloadFileInMemoryCmd | /api/v1/messages/attaches | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MailReadVerifyRequest | /api/v1/messages/notify/read | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RemoveSnoozeRequest | /api/v1/messages/snoozes/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MessagesSearchCommand | /api/v1/messages/search | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| FolderLoginCommandImpl | /api/v1/folders/open | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| SaveAttachmentsToCloudCommand | /api/v1/attaches/to-cloud | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DeleteFilter | /api/v1/filters/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MessageAttachesRequestCommand | /api/v1/messages/attaches | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CheckPhoneCommand | /api/v1/user/mobile/phone/change/confirm-current | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UnsubscribeMessageCommand | /api/v1/messages/services | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| GetCloudDispatcherCommand | RequestConfiguration.MAX_AD_CONTENT_RATING_G | dispatcher.cloud.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CreateFolder | /api/v1/folders | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MarkMessageCommand | /api/v1/messages/marks | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MailCategoryFeedbackCommand | /api/v1/messages/services/category/feedback | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| GetUserDataCommand | /api/v1/user | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DeleteAccountConfirmCommand | /api/v1/user/mobile/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestFiltersCommand | /api/v1/filters | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ThreadRequestCommand | /api/v1/m/threads/thread | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UsersLastSeenRequest | /api/v1/ab/lastseen | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TokensSendCommand | /api/v1/tokens/send | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| AddressGeocodingRequestCommand | /api/v1/golang/maps/decode | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestClickerTokenCommand | /api/v1/clickerproxy/autogen | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChildboxAuthCommand | /api/v1/childbox/auth | alt-auth.mail.ru | query:access_token | inherited TornadoSession URL setup |
| QrAuthWebCommand | /api/v1/auth/qr/allow | account.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MailMessageRequestCommand | /api/v1/messages/message | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ShareMailCommand | /api/v1/invites/letter | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| AddToCloudBundle | /api/v1/cloud/attachment | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| SetCustomTagsCommand | /api/v1/messages/colortags/set | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MessagesSearchCommandNew | /api/v1/go/search/emails | go.mail.ru | query:t | custom URL token setup in MessagesSearchCommandNew |
| GetCloudInfoCommand | /api/v1/cloud/status | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CreateCloudBundle | /api/v1/cloud/attachment/create | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UpdateFolder | /api/v1/folders/edit | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MessagesStatusCommand | /api/v1/messages/status | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UnspamThreadCommand | /api/v1/m/threads/services/unspam | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| GetSuggestionsCommand | /api/v1/messages/search/suggest | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UpdateFilterCommand | /api/v1/filters/edit | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| SaveHelperOnServerCommand | /api/v1/helpers/update | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ShareSmsCommand | /api/v1/sms/send | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| GetCityNameCommand | /api/v1/golang/geo/cities/city | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoScheduleRequest | /api/v1/messages | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChangeMessageCategoryRequest | /api/v1/messages/services/category/change | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| AddFilterCommand | /api/v1/filters | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| LoadHelpersFromServerCommand | /api/v1/helpers | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DownloadMessageEmlCommand | /api/v1/messages/message | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DeleteFolder | /api/v1/folders/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| SmartReplyRequestCommand | /api/v1/messages/smart | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UnsetCustomTagsCommand | /api/v1/messages/colortags/unset | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestHasFiltersCommand | /api/v1/filters | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UserEditCommand | /api/v1/user/edit | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MoveThreadCommand | /api/v1/m/threads/move | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestCollectorsCommand | /api/v1/collectors | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoDraftRequest | /api/v1/messages | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChildboxListCommand | /api/v1/childbox/list | alt-auth.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UserShortCommand | /api/v1/user/short | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RemoveThreadCommand | /api/v1/m/threads/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| SpamThreadCommand | /api/v1/m/threads/services/spam | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoCleanFolder | /api/v1/folders/clear | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestMessageMetaCommand | /api/v1/messages/meta | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TranslateLetterCommand | /api/v1/utils/translate | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChangePhoneConfirmCommand | /api/v1/user/mobile/phone/change | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| UserEditMetathreadsCommand | /api/v1/golang/user/edit/metathreads | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| RequestAliasesCommand | /api/v1/aliases | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoRedirectRequest | /api/v1/messages/services/bounce | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChangeAvatarCommand | /api/v1/user | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| DirectoriesListRequest | /api/v1/folders | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoSpamAbuse | /api/v1/messages/services/spam | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CountAllRequestCmd | /api/v1/messages/count/all | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CreateArchiveFolderCmd | /api/v1/folders/ensure | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChangePhoneCommand | /api/v1/user/mobile/phone/change | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| BatchSmartStatusCommand | /api/v1/m/status/smart | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| TornadoRemoveMessage | /api/v1/messages/remove | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| SearchPeopleCommand | /api/v1/ab/fast | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| MailSummarizeRequest | /api/v1/messages/message | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| CallsCsrfTokenRequest | /api/v1/token | alt-aj-https.mail.ru | custom-header | URL session setup disabled in CallsBaseGetRequest; header/session token use in CallsBaseGetRequest |
| CallsTokenRequest | /api/v1/user/token | alt-aj-https.mail.ru | custom-header | URL session setup disabled in CallsBaseGetRequest; header/session token use in CallsBaseGetRequest |
| GetAuthCodeByAccessTokenCommand | /token | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
| ChangeAuthTypeCommand | /api/v1/pushauth/method/set | alt-aj-https.mail.ru | query:access_token | inherited TornadoSession URL setup |
