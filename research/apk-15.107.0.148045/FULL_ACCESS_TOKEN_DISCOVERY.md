# Полное повторное исследование APK по access_token

Фильтр: домен не важен; учитывается способность команды работать с ru.mail.oauth2.access / access_token.

Всего классов @UrlPath: **151**.
Из них access_token-совместимых: **101**.
Не подтверждены как access_token: **50**.

## Access-token команды

| путь | класс | тип авторизации | ресурс узла | исходник |
|---|---|---|---|---|
| /api/v1/ab/contacts/backup | UploadContactsToServerCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/addressbook/backup/server/UploadContactsToServerCommand.java |
| /api/v1/folders/close | FoldersLogoutCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/FoldersLogoutCommand.java |
| /api/v1/tokens/check | CheckPhoneConfirmCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CheckPhoneConfirmCommand.java |
| /api/v1/messages/send | TornadoSendRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoSendRequest.java |
| /api/v1/m/threads/marks | MarkThreadCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MarkThreadCommand.java |
| /api/v1/payment/phone | GetUserVerifiedPhone | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GetUserVerifiedPhone.java |
| /api/v1/golang/user | GolangGetUserDataCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GolangGetUserDataCommand.java |
| /api/v1/user/mobile/remove | DeleteAccountCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DeleteAccountCommand.java |
| /api/v1/ab/smart | AddressBookFetchV2 | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/AddressBookFetchV2.java |
| /api/v1/cloud/attachment/remove | RemoveFromCloudBundle | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RemoveFromCloudBundle.java |
| /api/v1/messages/move/all | MoveAllMessageCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MoveAllMessageCommand.java |
| /api/v1/money/p2p/cancel | CancelTransactionRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CancelTransactionRequest.java |
| /api/v1/messages/move | TornadoMoveMessage | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoMoveMessage.java |
| /api/v1/mobauth/get | RequestSanitizeUrlCommand | TORNADO_MPOP | swa_default_host | ru/mail/data/cmd/server/RequestSanitizeUrlCommand.java |
| /api/v1/golang/user/short | GolangUserShortCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GolangUserShortCommand.java |
| /api/v1/messages/services/unspam | TornadoNoSpam | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoNoSpam.java |
| /api/v1/messages/remove/all | RemoveAllMessageCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RemoveAllMessageCommand.java |
| /api/v1/messages/attaches/reattach | TornadoReattachRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoReattachRequest.java |
| /api/v1/messages/marks/all | MarkAllMessageCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MarkAllMessageCommand.java |
| /api/v1/messages/snoozes/update | UpdateSnoozeRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UpdateSnoozeRequest.java |
| /api/v1/messages/attaches/remove | TornadoRemoveRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoRemoveRequest.java |
| /api/v1/golang/user | UserSecurityCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UserSecurityCommand.java |
| /api/v1/messages/attaches | TornadoUploadRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoUploadRequest.java |
| /api/v1/messages/attaches | DownloadFileInMemoryCmd | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DownloadFileInMemoryCmd.java |
| /api/v1/messages/notify/read | MailReadVerifyRequest | TORNADO | mail_api_default_host | ru/mail/data/cmd/server/MailReadVerifyRequest.java |
| /api/v1/auth/qr/get | QrGetInfoCommand | TORNADO | mail_api_default_host | ru/mail/data/cmd/server/QrGetInfoCommand.java |
| /api/v1/messages/snoozes/remove | RemoveSnoozeRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RemoveSnoozeRequest.java |
| /api/v1/messages/search | MessagesSearchCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MessagesSearchCommand.java |
| /api/v1/folders/open | FolderLoginCommandImpl | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/FolderLoginCommandImpl.java |
| /api/v1/attaches/to-cloud | SaveAttachmentsToCloudCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/SaveAttachmentsToCloudCommand.java |
| /api/v1/filters/remove | DeleteFilter | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DeleteFilter.java |
| /api/v1/messages/attaches | MessageAttachesRequestCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MessageAttachesRequestCommand.java |
| /api/v1/user/mobile/phone/change/confirm-current | CheckPhoneCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CheckPhoneCommand.java |
| /api/v1/messages/services | UnsubscribeMessageCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UnsubscribeMessageCommand.java |
| RequestConfiguration.MAX_AD_CONTENT_RATING_G | GetCloudDispatcherCommand | TORNADO_MPOP | cloud_dispatcher_default_host | ru/mail/data/cmd/server/GetCloudDispatcherCommand.java |
| /api/v1/folders | CreateFolder | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CreateFolder.java |
| /api/v1/messages/marks | MarkMessageCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MarkMessageCommand.java |
| /api/v1/messages/services/category/feedback | MailCategoryFeedbackCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MailCategoryFeedbackCommand.java |
| /api/v1/user | GetUserDataCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GetUserDataCommand.java |
| /api/v1/user/mobile/remove | DeleteAccountConfirmCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DeleteAccountConfirmCommand.java |
| /api/v1/filters | RequestFiltersCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RequestFiltersCommand.java |
| /api/v1/m/threads/thread | ThreadRequestCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/ThreadRequestCommand.java |
| /api/v1/ab/lastseen | UsersLastSeenRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UsersLastSeenRequest.java |
| /api/v1/tokens/send | TokensSendCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TokensSendCommand.java |
| /api/v1/golang/maps/decode | AddressGeocodingRequestCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/AddressGeocodingRequestCommand.java |
| /api/v1/clickerproxy/autogen | RequestClickerTokenCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RequestClickerTokenCommand.java |
| /api/v1/childbox/auth | ChildboxAuthCommand | TORNADO_MPOP | swa_default_host | ru/mail/data/cmd/server/ChildboxAuthCommand.java |
| /api/v1/auth/qr/allow | QrAuthWebCommand | TORNADO | mail_api_default_host | ru/mail/data/cmd/server/QrAuthWebCommand.java |
| /api/v1/messages/message | MailMessageRequestCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MailMessageRequestCommand.java |
| /api/v1/invites/letter | ShareMailCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/ShareMailCommand.java |
| /api/v1/cloud/attachment | AddToCloudBundle | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/AddToCloudBundle.java |
| /api/v1/messages/colortags/set | SetCustomTagsCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/SetCustomTagsCommand.java |
| /api/v1/go/search/emails | MessagesSearchCommandNew | TORNADO_MPOP | search_new_host | ru/mail/data/cmd/server/MessagesSearchCommandNew.java |
| /api/v1/cloud/status | GetCloudInfoCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GetCloudInfoCommand.java |
| /api/v1/cloud/attachment/create | CreateCloudBundle | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CreateCloudBundle.java |
| /api/v1/folders/edit | UpdateFolder | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UpdateFolder.java |
| /api/v1/messages/status | MessagesStatusCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MessagesStatusCommand.java |
| /api/v1/m/threads/services/unspam | UnspamThreadCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UnspamThreadCommand.java |
| /api/v1/messages/search/suggest | GetSuggestionsCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GetSuggestionsCommand.java |
| /api/v1/filters/edit | UpdateFilterCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UpdateFilterCommand.java |
| /api/v1/helpers/update | SaveHelperOnServerCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/SaveHelperOnServerCommand.java |
| /api/v1/sms/send | ShareSmsCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/ShareSmsCommand.java |
| /api/v1/golang/geo/cities/city | GetCityNameCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/GetCityNameCommand.java |
| /api/v1/messages | TornadoScheduleRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoScheduleRequest.java |
| /api/v1/messages/services/category/change | ChangeMessageCategoryRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/ChangeMessageCategoryRequest.java |
| /api/v1/filters | AddFilterCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/AddFilterCommand.java |
| /api/v1/helpers | LoadHelpersFromServerCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/LoadHelpersFromServerCommand.java |
| /api/v1/messages/message | DownloadMessageEmlCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DownloadMessageEmlCommand.java |
| /api/v1/folders/remove | DeleteFolder | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DeleteFolder.java |
| /api/v1/messages/smart | SmartReplyRequestCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/SmartReplyRequestCommand.java |
| /api/v1/messages/colortags/unset | UnsetCustomTagsCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UnsetCustomTagsCommand.java |
| /api/v1/filters | RequestHasFiltersCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RequestHasFiltersCommand.java |
| /api/v1/user/edit | UserEditCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UserEditCommand.java |
| /api/v1/m/threads/move | MoveThreadCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/MoveThreadCommand.java |
| /api/v1/collectors | RequestCollectorsCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RequestCollectorsCommand.java |
| /api/v1/messages | TornadoDraftRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoDraftRequest.java |
| /api/v1/childbox/list | ChildboxListCommand | TORNADO_MPOP | swa_default_host | ru/mail/data/cmd/server/ChildboxListCommand.java |
| /api/v1/user/short | UserShortCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UserShortCommand.java |
| /api/v1/m/threads/remove | RemoveThreadCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RemoveThreadCommand.java |
| /api/v1/m/threads/services/spam | SpamThreadCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/SpamThreadCommand.java |
| /api/v1/folders/clear | TornadoCleanFolder | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoCleanFolder.java |
| /api/v1/messages/meta | RequestMessageMetaCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RequestMessageMetaCommand.java |
| /api/v1/utils/translate | TranslateLetterCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TranslateLetterCommand.java |
| /api/v1/user/mobile/phone/change | ChangePhoneConfirmCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/ChangePhoneConfirmCommand.java |
| /api/v1/golang/user/edit/metathreads | UserEditMetathreadsCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/UserEditMetathreadsCommand.java |
| /api/v1/aliases | RequestAliasesCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/RequestAliasesCommand.java |
| /api/v1/messages/services/bounce | TornadoRedirectRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoRedirectRequest.java |
| /api/v1/user | ChangeAvatarCommand | TORNADO_MPOP | change_avatar_default_host | ru/mail/data/cmd/server/ChangeAvatarCommand.java |
| /api/v1/folders | DirectoriesListRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/DirectoriesListRequest.java |
| /api/v1/messages/services/spam | TornadoSpamAbuse | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoSpamAbuse.java |
| /api/v1/messages/count/all | CountAllRequestCmd | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CountAllRequestCmd.java |
| /api/v1/folders/ensure | CreateArchiveFolderCmd | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/CreateArchiveFolderCmd.java |
| /api/v1/user/mobile/phone/change | ChangePhoneCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/ChangePhoneCommand.java |
| /api/v1/m/status/smart | BatchSmartStatusCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/BatchSmartStatusCommand.java |
| /api/v1/messages/remove | TornadoRemoveMessage | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/TornadoRemoveMessage.java |
| /api/v1/ab/fast | SearchPeopleCommand | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/SearchPeopleCommand.java |
| /api/v1/messages/message | MailSummarizeRequest | TORNADO_MPOP | mail_api_default_host | ru/mail/data/cmd/server/summarize/MailSummarizeRequest.java |
| /api/v1/token | CallsCsrfTokenRequest | TORNADO | mail_api_default_host | ru/mail/data/cmd/server/calls/CallsCsrfTokenRequest.java |
| /api/v1/user/token | CallsTokenRequest | TORNADO | mail_api_default_host | ru/mail/data/cmd/server/calls/CallsTokenRequest.java |
| /token | GetAuthCodeByAccessTokenCommand | TORNADO | oauth_default_host | ru/mail/logic/auth/GetAuthCodeByAccessTokenCommand.java |
| /api/v1/pushauth/method/set | ChangeAuthTypeCommand | TORNADO_MPOP | account_default_host | ru/mail/logic/auth/ChangeAuthTypeCommand.java |

## Классы с прямым упоминанием access_token

Количество: **145**.

- com/vk/api/sdk/auth/VKAuthParams.java; URLs=https://vk.com/dev/access_token; Retrofit=[]; host=[]; oauth-token-type=False
- com/vk/api/sdk/ui/VKWebViewAuthActivity.java; URLs=https://api.vk.com/oauth/authorize; Retrofit=[]; host=[]; oauth-token-type=False
- com/appsflyer/internal/AFa1eSDK.java; URLs=https://%sstats.%s/stats; Retrofit=[]; host=[]; oauth-token-type=False
- ru/ok/android/sdk/OkAuthActivity.java; URLs=https://connect.ok.ru/oauth/authorize?client_id=; Retrofit=[]; host=[]; oauth-token-type=False
- ru/ok/android/sdk/AbstractWidgetActivity.java; URLs=https://connect.ok.ru/dk?st.cmd=; Retrofit=[]; host=[]; oauth-token-type=False
- ru/ok/android/sdk/SharedKt.java; URLs=https://api.ok.ru/, https://connect.ok.ru/; Retrofit=[]; host=[]; oauth-token-type=False
- ru/mail/auth/MailAccountConstants.java; URLs=; Retrofit=[]; host=[]; oauth-token-type=True
- ru/mail/auth/Authenticator.java; URLs=; Retrofit=[]; host=[]; oauth-token-type=True
- ru/mail/serverapi/retrofit/session/OAuthSession.java; URLs=; Retrofit=[]; host=[]; oauth-token-type=True
- ru/mail/data/cmd/BoundAccsListCmd.java; URLs=; Retrofit=[]; host=['defHostStrRes = "string/mail_api_default_host", defSchemeStrRes = "string/mail_api_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = MailOAuthRequest.BODY_KEY']; oauth-token-type=False
- ru/mail/search/metasearch/data/api/UrlsBuilder.java; URLs=https://filin.mail.ru/pic, https://go.mail.ru/api/v1/go, https://o2.mail.ru/token, https://suggests.go.mail.ru; Retrofit=[]; host=[]; oauth-token-type=False
- ru/mail/portal/kit/auth/PortalAuthProvider.java; URLs=; Retrofit=[]; host=[]; oauth-token-type=True
- ru/mail/logic/auth/GetAuthCodeByAccessTokenCommand.java; URLs=; Retrofit=[]; host=['defHostStrRes = "string/oauth_default_host", defSchemeStrRes = "string/oauth_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = MailOAuthRequest.BODY_KEY']; oauth-token-type=False
- ru/mail/logic/cmd/socialbind/SocialBindAddApi.java; URLs=; Retrofit=[('POST', 'api/v1/user/social/bind/add')]; host=[]; oauth-token-type=False
- ru/mail/authorizationsdk/feature/registration/data/api/SignupApi.java; URLs=; Retrofit=[('POST', 'api/v1/user/signup'), ('POST', 'api/v1/user/signup/confirm'), ('POST', 'api/v1/user/exists')]; host=[]; oauth-token-type=False
- ru/mail/authorizationsdk/data/externalaccount/ExternalAccMailApi.java; URLs=; Retrofit=[('POST', '{path}'), ('POST', 'oauth2_google_token'), ('POST', 'cgi-bin/oauth2_ok_token'), ('POST', 'oauth2_outlook_token'), ('POST', 'oauth2_yahoo_token'), ('POST', 'oauth2_yandex_token')]; host=[]; oauth-token-type=False

## Ресурсы узлов

| ресурс | значение |
|---|---|
| access_default_host | access.mail.ru |
| account_default_host | account.mail.ru |
| account_test_def_host | account.test.mail.ru |
| ad_deeplink_host | ads.mail.ru |
| attach_preview_default_host | alt-apf.mail.ru |
| attach_preview_default_host_mini_mail | https |
| auth_capcha_host | alt-c.mail.ru |
| auth_default_host |  |
| auth_default_host_mini_mail | Log in with QR code |
| auth_pre_default_host | aj-pre.test.mail.ru |
| authstat_default_host |  |
| authstat_default_host_mini_mail | Open |
| avatar_default_host | alt-mpandroid-filin.mail.ru |
| avatar_default_host_mini_mail | mpandroid.filin.mail.ru |
| avatar_default_host_v1 | mpandroid.filin.mail.ru |
| avatar_default_host_v1_mini_mail | https |
| calls_default_host | alt-calls.mail.ru |
| captcha_default_host | alt-swa.mail.ru |
| change_avatar_default_host |  |
| change_avatar_default_host_mini_mail | Tap on the avatar to change it |
| cloud_dispatcher_default_host | dispatcher.cloud.mail.ru |
| cloud_dispatcher_default_host_mini_mail | https |
| cloud_public_host | cloud.mail.ru |
| cloud_referer_default_host | alt-cloud.mail.ru |
| cloud_referer_default_host_mini_mail | https |
| credentials_exchanger_account_mail_host | account.mail.ru |
| credentials_exchanger_alt_aj_auth_host | alt-aj-https.mail.ru |
| credentials_exchanger_auth_host | alt-auth.mail.ru |
| credentials_exchanger_help_mail_host | alt-help.mail.ru |
| domain_settings_default_host |  |
| domain_settings_default_host_mini_mail | xmail.ru |
| doreg_captcha_default_host | alt-swa.mail.ru |
| doreg_captcha_default_host_mini_mail | https |
| doreg_default_host | alt-android-mobile-api.e.mail.ru |
| doreg_default_host_mini_mail | https |
| doreg_name_default_host | pre.test.mail.ru |
| error_code_unknow_domain_field_host | host |
| error_host_lookup | Server or proxy hostname lookup failed |
| files_default_host | alt-files.mail.ru |
| files_default_host_mini_mail | https |
| for_google_web_auth_server_host | accounts.google.com |
| for_google_web_mailru_redirect_host | fluor.mail.ru |
| for_google_web_mycom_redirect_host | fluor.my.com |
| for_google_web_token_server_host | accounts.google.com |
| for_outlook_auth_server_host | login.live.com |
| for_outlook_mailru_redirect_host | auth.mail.ru |
| for_outlook_mycom_redirect_host | alt-auth.my.com |
| for_outlook_token_server_host | login.live.com |
| for_yahoo_auth_server_host | api.login.yahoo.com |
| for_yahoo_mailru_redirect_host | auth.mail.ru |
| for_yahoo_mycom_redirect_host | mail.my.com |
| for_yahoo_token_server_host | api.login.yahoo.com |
| for_yandex_auth_server_host | oauth.yandex.ru |
| for_yandex_mailru_redirect_host | auth.mail.ru |
| for_yandex_mycom_redirect_host | mail.my.com |
| for_yandex_token_server_host | oauth.yandex.ru |
| generate_strong_password_host | e.mail.ru |
| goauth_default_host |  |
| goauth_default_host_mini_mail | Gmail |
| google_api_default_host | www.googleapis.com |
| host | Host |
| libero_default_host | login.libero.it |
| mail_api_default_host | alt-aj-https.mail.ru |
| mail_api_default_host_mini_mail | https |
| new_mail_api_default_host |  |
| new_mail_api_default_host_mini_mail | Incorrect emails. Please check the blind carbon copy field. |
| nf_new_mobs_host | https://media-mobs.mail.ru |
| not_vk_service_privacy_policy_host | help.mail.ru |
| not_vk_service_user_agreement_host | help.mail.ru |
| null_webview_base_host | about:null |
| oauth_default_host | o2.mail.ru |
| omicron_api_default_host | portal.mail.ru |
| omicron_fallback_api_host | portal.mail.ru |
| outlook_default_host | apis.live.net |
| portal_widget_host | alt-ad.mail.ru |
| pre_test_host | pre.test.mail.ru |
| pre_test_host_mini_mail | https |
| push_default_host | alt-push-me.mail.ru |
| push_default_host_dev | push-me.devmail.ru |
| push_default_host_mini_mail | https |
| qr_fallback_host | account.mail.ru |
| radar_default_host | alt-xray.imgsmail.ru |
| radar_default_host_mini_mail | https |
| rb_default_host | alt-ad.mail.ru |
| rb_default_host_mini_mail | https |
| registration_default_host |  |
| registration_default_host_mini_mail |  |
| sdk_account_default_host | account.mail.ru |
| sdk_account_default_test_host | account.mini-mail.ru |
| sdk_auth_capcha_host | alt-c.mail.ru |
| sdk_auth_default_host | alt-aj-https.mail.ru |
| sdk_auth_mail_host | auth.mail.ru |
| sdk_auth_mail_test_host | auth.mini-mail.ru |
| sdk_auth_test_capcha_host | c.mini-mail.ru |
| sdk_auth_test_default_host | aj-https.mini-mail.ru |
| sdk_avatar_default_host | alt-mpandroid-filin.mail.ru |
| sdk_avatar_test_default_host | mpandroid-filin.mini-mail.ru |
| sdk_doreg_captcha_default_host | alt-swa.mail.ru |
| sdk_doreg_captcha_default_test_host | swa.mini-mail.ru |
| sdk_for_google_web_auth_server_host | accounts.google.com |
| sdk_for_google_web_mailru_redirect_host | fluor.mail.ru |
| sdk_for_google_web_mailru_test_redirect_host | fluor.mini-mail.ru |
| sdk_for_google_web_token_server_host | accounts.google.com |
| sdk_for_outlook_auth_server_host | login.live.com |
| sdk_for_outlook_mailru_redirect_host | auth.mail.ru |
| sdk_for_outlook_mailru_test_redirect_host | auth.mini-mail.ru |
| sdk_for_outlook_token_server_host | login.live.com |
| sdk_for_yahoo_auth_server_host | api.login.yahoo.com |
| sdk_for_yahoo_mailru_redirect_host | auth.mail.ru |
| sdk_for_yahoo_mailru_test_redirect_host | auth.mini-mail.ru |
| sdk_for_yahoo_token_server_host | api.login.yahoo.com |
| sdk_for_yandex_auth_server_host | oauth.yandex.ru |
| sdk_for_yandex_mailru_redirect_host | auth.mail.ru |
| sdk_for_yandex_mailru_test_redirect_host | auth.mini-mail.ru |
| sdk_for_yandex_token_server_host | oauth.yandex.ru |
| sdk_google_api_default_host | www.googleapis.com |
| sdk_oauth_mail_host | o2.mail.ru |
| sdk_outlook_default_host | apis.live.net |
| sdk_swa_default_host | alt-auth.mail.ru |
| sdk_swa_test_def_host | auth.mini-mail.ru |
| sdk_yahoo_social_api_default_host | api.login.yahoo.com |
| sdk_yandex_api_default_host | login.yandex.ru |
| search_new_host | go.mail.ru |
| sharing_mail_link_host | sharing.mail.ru |
| swa_default_host | alt-auth.mail.ru |
| swa_test_def_host | test.auth.mail.ru |
| test_host | test |
| test_host_mini_mail | msg |
| universal_link_calendar_host | calendar.mail.ru |
| universal_link_host | universal-link.mail.ru |
| universal_link_notes_host | notes.mail.ru |
| universal_link_wallet_host | wallet.mail.ru |
| virgilio_default_host | login.virgilio.it |
| vk_service_privacy_policy_host | static.vk.ru |
| vk_service_user_agreement_host | static.vk.ru |
| webview_base_host | e.mail.ru |
| yahoo_social_api_default_host | api.login.yahoo.com |
| yandex_api_default_host | login.yandex.ru |
