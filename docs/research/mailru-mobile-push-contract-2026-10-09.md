# Технический договор мобильных уведомлений Mail.ru — 09.10.2026

Состояние: восстановлено **статически по оригинальному APK** `ru.mail.mailapp` 15.107.0.148045. Действующий поток от серверов Google и Mail.ru на Windows **не испытан**. Документ описывает контракт, не утверждает успешного подключения и не разрешает перенос чужих токенов устройств.

## Принцип реализации

Использовать существующую конфигурацию **официального мобильного приложения** и серверную систему Mail.ru без создания нового проекта Mail.ru, если протокол допускает независимую установку клиента. Отличать регистрацию уже известного серверу приложения от обязательной **персональной регистрации нового получателя** уведомлений. Для каждого компьютера — собственная установка, собственный действительный токен Google, стабильные идентификаторы устройства; почтовая авторизация — владельца этого почтового ящика. Нельзя копировать токен доставки существующего телефона, иначе нарушится независимая доставка.

Все будущие почтовые запросы должны соответствовать установленному оригинальному мобильному протоколу и содержать подтверждённую авторизацию. Совпадение заголовков/полей **не заменяет** проверку серверного допуска и Google-доставки.

## Исходные сведения APK (проверены SHA-256)

Результат: `research/apk-15.107.0.148045/PUSH_REGISTRATION_RESOURCES.md`; GitHub Actions № `37874537322` — SUCCESS. Только общедоступные идентификаторы, секреты и пользовательские данные не извлекались.

| Параметр | Значение | Назначение |
|---|---|---|
| `push_sender_id` | `1098335887158` | **Непосредственно** передаётся в `FirebaseInfoProvider.getToken(senderId)` из `GcmPushKitWrapper` |
| `gcm_defaultSenderId` | `61247752867` | Отдельный системный идентификатор отправителя Google по умолчанию |
| `google_app_id` | `1:61247752867:android:d199c9f145040309` | Настройка регистрации Firebase приложения |
| `project_id` | `fluorcorpmailru` | Название проекта Firebase в ресурсах |
| `push_default_api` | `api` | Подстановка пути серверного запроса прежнего клиента |
| `push_default_host` | `alt-push-me.mail.ru` | Сервер PushMe |
| `push_default_scheme` | `https` | Защищённое соединение |
| `PushMeSdkConfig` | `application="mail"`, `PusherHost.AltProd` | Приложение и узел регистрации в текущей подсистеме |

**Критическое расхождение:** почтовый `push_sender_id` **не равен** `gcm_defaultSenderId` и не совпадает с отправителем в `google_app_id`. Нельзя заменять одно другим. Следует далее выяснить внутреннюю реализацию `FirebaseInfoProvider.getToken(senderId)` и связь этих идентификаторов с собственным токеном Windows-установки.

AndroidManifest.xml подтверждает зарегистрированную службу `ru.mail.util.push.gcm.MailMessagingService` с событием `com.google.firebase.MESSAGING_EVENT`. `GCMAvailabilityChecker` проверяет наличие системных Google Play Services. Следовательно, работоспособность на телефоне без RuStore/VK не означает отсутствия внешней системной доставки Google.

## Подписка в действующей подсистеме PushMeSdk

Первичные классы:
- `ru/mail/setup/SetUpPushMeSdk.java`
- `ru/mail/util/push/pusher/PushMeSDKPusherTransport.java`
- `com/vk/pushme/logic/usecase/SubscriptionUseCase.java`
- `com/vk/pushme/network/model/request/SubscriptionRequest.java`
- `com/vk/pushme/network/model/request/InternalSubscriptionRequest.java`
- `com/vk/pushme/network/PushMeApiImpl.java`

`SetUpPushMeSdk` считывает **для каждого аккаунта** учётный токен типа `ru.mail.oauth2.access`; `PushMeSdk.setAuthProvider` поставляет его по запросу. `PushMeSDKPusherTransport.registerMailAppForPushes` вызывает `PushMeSdk.getApp().registerAccounts(...).execute()`, передавая все настроенные аккаунты.

Декомпилированный сериализатор `InternalSubscriptionRequest` задаёт поля:

| Поле JSON | Источник |
|---|---|
| `account` | Идентификатор почтового аккаунта |
| `application` | `mail` |
| `platform` | **`android` для Firebase**, `huawei` / `vkpns` для остальных |
| `token` | Действительный токен доставки конкретной установки (не OAuth) |
| `access_token` | Почтовая авторизация соответствующего аккаунта |
| `android_id` | Идентификатор установки, согласованный со схемой SDK |
| `sdk_device_id` | Необязательный отдельный идентификатор установки SDK |
| `settings` | Возможности, сведения о клиенте, идентификатор устройства, часовой пояс, отметка непрочитанных |
| `status` | `0`, активная подписка |

Важно различать **внутреннее** значение `com.vk.pushme.model.Transport.FIREBASE = "fcm"` и **сетевое** значение `SubscriptionRequest.Transport.FIREBASE = "android"`. Их нельзя подменять друг другом.

Поддерживаются `setSettingsV1`, `setSettingsV2` и снятие подписки. Большой метод `PushMeApiImpl.setSettingsInternal` неполно восстановлен JADX: точный набор сетевых заголовков и способ формирования POST в **нынешнем SDK** нельзя объявить полностью доказанными.

Ответ SDK `SubscriptionResponse` содержит `error.code`, `error.message` и `validate_result` со связкой `account` / `is_valid`. Код `0` и действительная проверка каждого аккаунта — необходимые условия признания успешной регистрации, но пока это формат в исходниках, не живой результат.

## Более ранняя реализация PushMe, сохранённая в том же APK

Для **прежнего** протокола исходники `decompiled/packages/ru/mail/data/cmd/server/pusher/pushme/` содержат конкретные команды:

- `PushMeSendPushSettingsCommand`: сервер `alt-push-me.mail.ru`, аннотация `@UrlPath({"{api}", "v2", "set_settings"})`, где ресурс `push_default_api = api`, то есть путь `/api/v2/set_settings` (для данной команды, не автоматически для SDK).
- `PushMeSendPushSettingsV1Command`: `/api/v1/set_settings`.
- `PushMeCheckPushTokenCommand`: `/ss/check_push` — проверка существования токена в прежней реализации.
- `PushMeRemovePushSettingsCmd`: `/ss/unsubscribe_by_device_id`.
- `PushMeUnsubscribeByTokenCommand`: `/api/v2/unsubscribe_by_token`.

В `PushMeParamsPreparerImpl.fillRootJSON` восстановлены поля `account`, `platform`, `application`, `token`, `settings`, `access_token` или `mpop`, `android_id`, `sdk_device_id`, `status`. Старые команды имеют свои особенности отправки тела, **не смешивать их** с сетевым клиентом `PushMeSdk`.

Примечание: `set_settings` — операция, меняющая подписку. Во время исследования не выполнять её на действующих аккаунтах без контролируемого испытания. Сначала проверить, что Windows может создать отдельный корректный токен доставки без воздействия на телефон.

## Сопоставление с нынешним MailRu Desktop

`src/MailRuDesktop.Protocol/MailRuProtocol.cs`: вход выполняется на `aj-https.mail.ru/cgi-bin/auth` с `oauth2=1`, `useragent=android`, `mobile=1`; существующие почтовые операции получают `access_token` и `mp=android`; профиль `MailRuFixedProfile.MobileUserAgent`.

`src/MailRuDesktop.App/AuthorizationStore.cs`: токен `AccessToken` и токен обновления `RefreshToken` защищены локальным хранилищем Windows, аккаунты хранятся независимо. Это подходящий источник для **исследования** совместимости с `ru.mail.oauth2.access` при подписке PushMe; совпадение прав и принятие сервером **пока не проверены**. Не извлекать эти токены в журнал, GitHub Actions, репозиторий или публичные отчёты.

## Минимальные доказательные испытания

1. Разобрать реализацию `FirebaseInfoProvider.getToken(senderId)`, включая расхождение двух идентификаторов Google.
2. Изолированно получить **новый**, не заимствованный у телефона токен доставки в Windows с настройками официального клиента. Проверить действительность токена, но не создавать новую серверную идентичность Mail.ru.
3. С отдельным разрешённым испытательным почтовым аккаунтом проверить, принимает ли сервер PushMe токен по действующей схеме `PushMeSdk`; сохранить только обезличенный результат и код ответа.
4. Получить серверное `event=4` с ожидаемой структурой `account`, `id`, `sender`, `text`, `snippet`, `folder_id`, `uts`; выполнить сверку с ящиком, исключить дубли.
5. Испытать несколько аккаунтов, разрыв связи, изменение токена, повторную подписку, закрытие и повторное открытие приложения. Подтвердить, что уведомления телефона не нарушены.
6. Только после успешного испытания встроить отдельный транспорт .NET в клиент; периодический опрос папки не считать аналогом событийной доставки.

## Источники

Оригинальный APK, сохранённая ветка `research/mail-apk-15.107.0.148045`, контрольные результаты GitHub Actions №37870637190, №37872182332, №37874537322.

Основной отчёт: `docs/research/android-new-mail-notifications-2026-10-09.md`. Для сравнения независимой доставки в Windows: `superhuman/push-receiver` v2.1.7, но не объявлять его автоматически совместимым с Mail.ru.
