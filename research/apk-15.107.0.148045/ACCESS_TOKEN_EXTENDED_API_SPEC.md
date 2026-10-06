# Расширенная карта API Mail.ru по почтовому access_token

Версия исследованного приложения: `ru.mail.mailapp 15.107.0.148045`.

Критерий включения: официальный клиент фактически передаёт значение учётного токена `ru.mail.oauth2.access`. Домен и имя поля токена значения не имеют.

## Итог повторного прохода

- сетевых классов `@UrlPath`: **151**;
- потенциально OAuth-совместимых: **101**;
- после проверки фактической передачи того же почтового токена: **100**;
- ложноположительных: **1** (`QrGetInfoCommand`, передача сеанса отключена);
- ранее разобранных почтовых маршрутов: **58**, все они совместимы с нашим токеном;
- дополнительных подтверждённых команд: **42**.

Для всей подтверждённой сотни способы передачи токена: обычный параметр `access_token`, специальный параметр `t` у нового поиска и заголовок `Authorization: Bearer` у подсистемы звонков.

## Приоритет

**A** — имеет прямую ценность для настольного почтового клиента и стоит рассматривать для реализации.  
**B** — может пригодиться как дополнительная функция.  
**C** — служебная, административная либо потенциально разрушительная операция; документируем, но в основной почтовый интерфейс не включаем без отдельной задачи.

## 42 дополнительные команды

| приоритет | класс | маршрут | узел | передача токена |
|---|---|---|---|---|
| A | `UploadContactsToServerCommand` | `/api/v1/ab/contacts/backup` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `CheckPhoneConfirmCommand` | `/api/v1/tokens/check` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `GetUserVerifiedPhone` | `/api/v1/payment/phone` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `GolangGetUserDataCommand` | `/api/v1/golang/user` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `DeleteAccountCommand` | `/api/v1/user/mobile/remove` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `RemoveFromCloudBundle` | `/api/v1/cloud/attachment/remove` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `CancelTransactionRequest` | `/api/v1/money/p2p/cancel` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `RequestSanitizeUrlCommand` | `/api/v1/mobauth/get` | `alt-auth.mail.ru` | `query:access_token` |
| A | `GolangUserShortCommand` | `/api/v1/golang/user/short` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `UserSecurityCommand` | `/api/v1/golang/user` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `CheckPhoneCommand` | `/api/v1/user/mobile/phone/change/confirm-current` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `GetCloudDispatcherCommand` | `(путь собирается из константы)` | `dispatcher.cloud.mail.ru` | `query:access_token` |
| A | `GetUserDataCommand` | `/api/v1/user` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `DeleteAccountConfirmCommand` | `/api/v1/user/mobile/remove` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `TokensSendCommand` | `/api/v1/tokens/send` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `AddressGeocodingRequestCommand` | `/api/v1/golang/maps/decode` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `RequestClickerTokenCommand` | `/api/v1/clickerproxy/autogen` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `ChildboxAuthCommand` | `/api/v1/childbox/auth` | `alt-auth.mail.ru` | `query:access_token` |
| B | `QrAuthWebCommand` | `/api/v1/auth/qr/allow` | `account.mail.ru` | `query:access_token` |
| B | `ShareMailCommand` | `/api/v1/invites/letter` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `AddToCloudBundle` | `/api/v1/cloud/attachment` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `GetCloudInfoCommand` | `/api/v1/cloud/status` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `CreateCloudBundle` | `/api/v1/cloud/attachment/create` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `SaveHelperOnServerCommand` | `/api/v1/helpers/update` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `ShareSmsCommand` | `/api/v1/sms/send` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `GetCityNameCommand` | `/api/v1/golang/geo/cities/city` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `LoadHelpersFromServerCommand` | `/api/v1/helpers` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `RequestHasFiltersCommand` | `/api/v1/filters` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `UserEditCommand` | `/api/v1/user/edit` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `RequestCollectorsCommand` | `/api/v1/collectors` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `ChildboxListCommand` | `/api/v1/childbox/list` | `alt-auth.mail.ru` | `query:access_token` |
| A | `UserShortCommand` | `/api/v1/user/short` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `TranslateLetterCommand` | `/api/v1/utils/translate` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `ChangePhoneConfirmCommand` | `/api/v1/user/mobile/phone/change` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `UserEditMetathreadsCommand` | `/api/v1/golang/user/edit/metathreads` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `RequestAliasesCommand` | `/api/v1/aliases` | `alt-aj-https.mail.ru` | `query:access_token` |
| A | `ChangeAvatarCommand` | `/api/v1/user` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `ChangePhoneCommand` | `/api/v1/user/mobile/phone/change` | `alt-aj-https.mail.ru` | `query:access_token` |
| B | `CallsCsrfTokenRequest` | `/api/v1/token` | `alt-aj-https.mail.ru` | `custom-header` |
| B | `CallsTokenRequest` | `/api/v1/user/token` | `alt-aj-https.mail.ru` | `custom-header` |
| B | `GetAuthCodeByAccessTokenCommand` | `/token` | `alt-aj-https.mail.ru` | `query:access_token` |
| C | `ChangeAuthTypeCommand` | `/api/v1/pushauth/method/set` | `alt-aj-https.mail.ru` | `query:access_token` |

## A — полезно для Mail.ru Desktop

### UploadContactsToServerCommand

Маршрут: `/api/v1/ab/contacts/backup`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/addressbook/backup/server/UploadContactsToServerCommand.java` — пока не сохранён в выборке.

### GolangGetUserDataCommand

Маршрут: `/api/v1/golang/user`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `Accept-Encoding` (HEADER_ADD).

Ключи ответа, которые читает клиент: `90x90`, `b2b_flags`, `birthday`, `body`, `city`, `common_purpose_flags`, `day`, `email`, `metathreads_visible`, `name`, `parental_control_can_disable`, `parental_control_mode`, `phone`, `phones`, `status`, `theme`.

Исходник: `ru/mail/data/cmd/server/GolangGetUserDataCommand.java`.

### RemoveFromCloudBundle

Маршрут: `/api/v1/cloud/attachment/remove`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `bundle_id` (POST), `file_id` (POST).

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/data/cmd/server/RemoveFromCloudBundle.java`.

### GolangUserShortCommand

Маршрут: `/api/v1/golang/user/short`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `account_type`, `b2b_flags`, `body`, `common_purpose_flags`, `domain`, `esia`, `login`, `metathreads_visible`, `name`, `parental_control_can_disable`, `parental_control_mode`, `privacy_settings`, `reader_mode`, `social_bind`, `theme`, `vkid`.

Исходник: `ru/mail/data/cmd/server/GolangUserShortCommand.java`.

### UserSecurityCommand

Маршрут: `/api/v1/golang/user`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `extra_emails`, `phones`.

Исходник: `ru/mail/data/cmd/server/UserSecurityCommand.java`.

### GetCloudDispatcherCommand

Маршрут: `(путь собирается из константы)`.

Узел: `dispatcher.cloud.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/data/cmd/server/GetCloudDispatcherCommand.java`.

### GetUserDataCommand

Маршрут: `/api/v1/user`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `Accept-Encoding` (HEADER_ADD).

Ключи ответа, которые читает клиент: `90x90`, `b2b_flags`, `birthday`, `body`, `city`, `common_purpose_flags`, `day`, `email`, `name`, `parental_control_can_disable`, `parental_control_mode`, `phone`, `phones`, `status`, `theme`.

Исходник: `ru/mail/data/cmd/server/GetUserDataCommand.java`.

### RequestClickerTokenCommand

Маршрут: `/api/v1/clickerproxy/autogen`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `ttl`.

Исходник: `ru/mail/data/cmd/server/RequestClickerTokenCommand.java`.

### AddToCloudBundle

Маршрут: `/api/v1/cloud/attachment`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `bundle_id` (POST), `EventParams.HASH` (POST), `name` (POST), `size` (POST).

Ключи ответа, которые читает клиент: `body`, `file_id`, `name`.

Исходник: `ru/mail/data/cmd/server/AddToCloudBundle.java`.

### GetCloudInfoCommand

Маршрут: `/api/v1/cloud/status`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `cloud_overquota_state` (POST), `remember_overquota` (POST).

Ключи ответа, которые читает клиент: `account_type`, `body`, `cloud_overquota_block_time`, `cloud_overquota_start_time`, `cloud_overquota_state`, `cloudflags`, `email`, `frozen`, `space`, `used`, `used_mail`.

Исходник: `ru/mail/data/cmd/server/GetCloudInfoCommand.java`.

### CreateCloudBundle

Маршрут: `/api/v1/cloud/attachment/create`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `bundle_id`, `loader_url`.

Исходник: `ru/mail/data/cmd/server/CreateCloudBundle.java`.

### RequestCollectorsCommand

Маршрут: `/api/v1/collectors`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`.

Исходник: `ru/mail/data/cmd/server/RequestCollectorsCommand.java`.

### UserShortCommand

Маршрут: `/api/v1/user/short`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `Accept-Encoding` (HEADER_ADD).

Ключи ответа, которые читает клиент: `account_type`, `b2b_flags`, `body`, `common_purpose_flags`, `parental_control_can_disable`, `parental_control_mode`, `phone_main`, `soft_vkid_bind`, `theme`.

Исходник: `ru/mail/data/cmd/server/UserShortCommand.java`.

### TranslateLetterCommand

Маршрут: `/api/v1/utils/translate`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `QUERY_PARAM_FROM_LANG` (POST), `QUERY_PARAM_HTML_ENCODED` (POST), `message_id` (POST), `query` (POST), `QUERY_PARAM_TO_LANG` (POST), `QUERY_PARAM_TRANSLATOR` (POST).

Ключи ответа, которые читает клиент: `body`.

Исходник: `ru/mail/data/cmd/server/TranslateLetterCommand.java`.

### RequestAliasesCommand

Маршрут: `/api/v1/aliases`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `alias`, `body`.

Исходник: `ru/mail/data/cmd/server/RequestAliasesCommand.java`.

### ChangeAvatarCommand

Маршрут: `/api/v1/user`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `avatar`, `body`, `error`, `mainphoto`, `status`.

Исходник: `ru/mail/data/cmd/server/ChangeAvatarCommand.java`.

## B — дополнительная функция

### GetUserVerifiedPhone

Маршрут: `/api/v1/payment/phone`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `operator`, `phone`.

Исходник: `ru/mail/data/cmd/server/GetUserVerifiedPhone.java`.

### RequestSanitizeUrlCommand

Маршрут: `/api/v1/mobauth/get`.

Узел: `alt-auth.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `page`.

Ключи ответа, которые читает клиент: `body`, `expires`, `url`.

Исходник: `ru/mail/data/cmd/server/RequestSanitizeUrlCommand.java`.

### AddressGeocodingRequestCommand

Маршрут: `/api/v1/golang/maps/decode`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `street` (POST), `city` (POST).

Ключи ответа, которые читает клиент: `body`, `lat`, `lon`.

Исходник: `ru/mail/data/cmd/server/AddressGeocodingRequestCommand.java`.

### ChildboxAuthCommand

Маршрут: `/api/v1/childbox/auth`.

Узел: `alt-auth.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `login` (GET).

Ключи ответа, которые читает клиент: `auth_url`, `body`, `children`, `login`.

Исходник: `ru/mail/data/cmd/server/ChildboxAuthCommand.java`.

### QrAuthWebCommand

Маршрут: `/api/v1/auth/qr/allow`.

Узел: `account.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `token` (POST).

Ключи ответа, которые читает клиент: `body`, `login`.

Исходник: `ru/mail/data/cmd/server/QrAuthWebCommand.java`.

### ShareMailCommand

Маршрут: `/api/v1/invites/letter`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `addresses` (GET), `TornadoSendRequest.FIELD_TEMPLATE` (GET).

Ключи ответа, которые читает клиент: `email`.

Исходник: `ru/mail/data/cmd/server/ShareMailCommand.java`.

### SaveHelperOnServerCommand

Маршрут: `/api/v1/helpers/update`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `index` (POST), `update` (POST).

Ключи ответа, которые читает клиент: `body`, `close`, `count`, `index`, `show`, `state`, `status`, `time`.

Исходник: `ru/mail/data/cmd/server/SaveHelperOnServerCommand.java`.

### ShareSmsCommand

Маршрут: `/api/v1/sms/send`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `phones` (GET), `country` (GET), `SharingPreferenceNavigator.EXTRA_MSG_TYPE` (GET).

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/data/cmd/server/ShareSmsCommand.java`.

### GetCityNameCommand

Маршрут: `/api/v1/golang/geo/cities/city`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `city_id` (POST).

Ключи ответа, которые читает клиент: `body`, `city`, `city_id`, `name`.

Исходник: `ru/mail/data/cmd/server/GetCityNameCommand.java`.

### LoadHelpersFromServerCommand

Маршрут: `/api/v1/helpers`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `close`, `count`, `index`, `show`, `state`, `status`, `time`.

Исходник: `ru/mail/data/cmd/server/LoadHelpersFromServerCommand.java`.

### RequestHasFiltersCommand

Маршрут: `/api/v1/filters`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `enabled`.

Исходник: `ru/mail/data/cmd/server/RequestHasFiltersCommand.java`.

### ChildboxListCommand

Маршрут: `/api/v1/childbox/list`.

Узел: `alt-auth.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `children`, `first_name`, `image`, `last_name`, `login`, `mail_counter`.

Исходник: `ru/mail/data/cmd/server/ChildboxListCommand.java`.

### CallsCsrfTokenRequest

Маршрут: `/api/v1/token`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `custom-header`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/data/cmd/server/calls/CallsCsrfTokenRequest.java`.

### CallsTokenRequest

Маршрут: `/api/v1/user/token`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `custom-header`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `token`.

Исходник: `ru/mail/data/cmd/server/calls/CallsTokenRequest.java`.

### GetAuthCodeByAccessTokenCommand

Маршрут: `/token`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/logic/auth/GetAuthCodeByAccessTokenCommand.java` — пока не сохранён в выборке.

## C — служебное/административное

### CheckPhoneConfirmCommand

Маршрут: `/api/v1/tokens/check`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`.

Исходник: `ru/mail/data/cmd/server/CheckPhoneConfirmCommand.java`.

### DeleteAccountCommand

Маршрут: `/api/v1/user/mobile/remove`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `id`.

Исходник: `ru/mail/data/cmd/server/DeleteAccountCommand.java`.

### CancelTransactionRequest

Маршрут: `/api/v1/money/p2p/cancel`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `transaction_id` (GET).

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/data/cmd/server/CancelTransactionRequest.java`.

### CheckPhoneCommand

Маршрут: `/api/v1/user/mobile/phone/change/confirm-current`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`.

Исходник: `ru/mail/data/cmd/server/CheckPhoneCommand.java`.

### DeleteAccountConfirmCommand

Маршрут: `/api/v1/user/mobile/remove`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `reg_token` (GET).

Ключи ответа, которые читает клиент: `body`, `id`, `value`.

Исходник: `ru/mail/data/cmd/server/DeleteAccountConfirmCommand.java`.

### TokensSendCommand

Маршрут: `/api/v1/tokens/send`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `ivr` (GET), `ConfirmPhoneFragment.EXT_REG_TOKEN` (GET).

Ключи ответа, которые читает клиент: `address`, `body`, `id`, `index`, `transport`.

Исходник: `ru/mail/data/cmd/server/TokensSendCommand.java`.

### UserEditCommand

Маршрут: `/api/v1/user/edit`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/data/cmd/server/UserEditCommand.java`.

### ChangePhoneConfirmCommand

Маршрут: `/api/v1/user/mobile/phone/change`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: `body`, `new_phone`.

Исходник: `ru/mail/data/cmd/server/ChangePhoneConfirmCommand.java`.

### UserEditMetathreadsCommand

Маршрут: `/api/v1/golang/user/edit/metathreads`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `(имя задаётся выражением)` (POST).

Ключи ответа, которые читает клиент: `folder_id`, `state`.

Исходник: `ru/mail/data/cmd/server/UserEditMetathreadsCommand.java`.

### ChangePhoneCommand

Маршрут: `/api/v1/user/mobile/phone/change`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: `phone` (GET), `reg_token_check` (GET).

Ключи ответа, которые читает клиент: `body`, `error`, `id`, `phone`.

Исходник: `ru/mail/data/cmd/server/ChangePhoneCommand.java`.

### ChangeAuthTypeCommand

Маршрут: `/api/v1/pushauth/method/set`.

Узел: `alt-aj-https.mail.ru`. Тот же `ru.mail.oauth2.access` передаётся как `query:access_token`.

Параметры: явных параметров класса не извлечено.

Ключи ответа, которые читает клиент: полезная нагрузка не разбирается или ключи не извлечены.

Исходник: `ru/mail/logic/auth/ChangeAuthTypeCommand.java` — пока не сохранён в выборке.

## Уточнённые механики, важные для реализации

### Новый поиск

`https://go.mail.ru/api/v1/go/search/emails` использует тот же почтовый OAuth-токен, но `MessagesSearchCommandNew` переопределяет установку сеанса и передаёт его как `t=<ru.mail.oauth2.access>`. Следовательно, новый поиск разрешён текущими требованиями проекта.

### Облако

Подтверждена связка создания набора вложений → добавления файлов → удаления файлов, причём управляющие команды используют тот же почтовый токен. `CreateCloudBundle` возвращает `loader_url` и `bundle_id`; `AddToCloudBundle` принимает `bundle_id`, `hash`, `name`, `size` и возвращает `name`, `file_id`; `RemoveFromCloudBundle` принимает `bundle_id` и `file_id`. `/api/v1/cloud/status` возвращает квоту и состояние Облака.

### Сведения учётной записи

`/api/v1/golang/user`, `/api/v1/golang/user/short`, `/api/v1/user`, `/api/v1/user/short`, а также маршрут безопасности дают имя, аватар, телефонные состояния, дату рождения, тему, тип аккаунта, признаки двухфакторной защиты, дополнительные адреса и другие возможности профиля. Для Mail.ru Desktop это позволяет перестать хранить часть сведений как догадки.

### Псевдонимы и сборщики

`/api/v1/aliases` возвращает список дополнительных адресов отправителя. `/api/v1/collectors` возвращает подключённые сборщики почты. Обе операции работают с тем же почтовым токеном.

### Перевод письма

`/api/v1/utils/translate` принимает идентификатор письма, текст/фрагмент `query`, исходный и целевой языки, флаг кодирования HTML и идентификатор переводчика. Это отдельная готовая функция официального клиента.

### Вызовы

Команды `/api/v1/token` и `/api/v1/user/token` не добавляют токен к URL. Подсистема выбирает OAuth-режим и передаёт тот же токен в заголовке `Authorization: Bearer <token>`. Эти функции не нужны для первой версии почтового клиента, но доказательно входят в общую поверхность нашего токена.

### Обмен токена на авторизационный код

`GetAuthCodeByAccessTokenCommand` вызывает `/token` и поддерживает `client_id`, `for_client_id`, `grant_type`, `scope`, `subject_token`, `subject_token_type`, `requested_token_type`, а также PKCE (`code_challenge`, `code_challenge_method=S256`). Ответ может содержать `code`, новый `access_token`, `refresh_token`, `account_id`, срок действия. Это производный механизм от нашего токена, а не отдельная исходная авторизация.

## Что не следует автоматически внедрять

Операции удаления аккаунта, смены телефона, P2P-отмены, изменения способа подтверждения входа и другие административные команды подтверждены технически, но не относятся к базовой работе почтового клиента. Они остаются задокументированными и требуют отдельного решения перед добавлением в интерфейс.

## Остаток

Статическая выборка закрыта полностью: исходники сохранены для всех 101 первоначальных кандидатов. После исключения `QrGetInfoCommand`, у которого передача сеанса отключена, подтверждено 100 команд с тем же `ru.mail.oauth2.access`.


## Последние восстановленные договоры

### Резервное копирование контактов

`POST /api/v1/ab/contacts/device/backup`

Класс: `UploadContactsToServerCommand`.

Авторизация: тот же `ru.mail.oauth2.access` через обычный `access_token`.

Поля POST:

- `device_id` — идентификатор устройства;
- `contacts` — JSON, формируемый из списка `ContactNwDto`;
- общие `email`, `lang`, `access_token`.

Команда не разбирает полезную нагрузку успешного ответа: HTTP/API-успех преобразуется в локальный `Result.success()`.

### Переключение способа подтверждения входа

`POST /api/v1/pushauth/method/set`

Класс: `ChangeAuthTypeCommand`.

Поля POST:

- `login`;
- `method`.

Обычный параметр `email` для этой команды специально отключён; `login` передаётся явным полем. `act_mode` также отключён.

Ответ:

`body.method`

преобразуется в `AuthType`.

Авторизация остаётся OAuth-совместимой: используется тот же почтовый `ru.mail.oauth2.access`.

### Полнота исходников

После точечной декомпиляции:

- кандидатов в `ACCESS_TOKEN_CONTRACTS.json`: **101**;
- исходники доступны: **101**;
- недостающих исходников: **0**;
- фактически используют тот же токен: **100**;
- исключённый ложноположительный: **QrGetInfoCommand**.
