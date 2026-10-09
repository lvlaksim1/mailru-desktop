# Semantic memory

## Mail.ru access-token API boundary — 2026-10-06

- Current scope is credential-based, not host-based: include official-client mechanisms that use the same mailbox OAuth credential `ru.mail.oauth2.access`.
- Verified token transports in APK 15.107.0.148045: 97 `access_token`, one `t` for `go.mail.ru` search, two `Authorization: Bearer`.
- Full static audit: 151 routed network classes; 101 candidates; 100 confirmed same-token; `QrGetInfoCommand` excluded; 101/101 candidate sources retained.
- High-value previously unresolved operations are statically known: permanent delete, flag/pin, search, address book, folders, drafts/schedule, attachment lifecycle, filters and others.
- Static APK evidence proves official-client contract/token flow but does not substitute for live validation before product enablement.
- Evidence base: branch `research/mail-apk-15.107.0.148045`.

## v0.2.0 live-validation findings — 2026-10-07

- Release-set run `37539371650` passed with zero failures for folder list, address book, fast recipients, classic search, flag/pin with restoration, folder CRUD/clear, draft save, move-to-trash and permanent remove.
- Permanent remove is validated after the message is in Trash; direct remove of a draft produced `status=400,error=denied`.
- New search on `go.mail.ru` remains in scope statically but returned HTTP 520 in controlled live calls and is therefore not enabled in v0.2.0.
- Classic `/api/v1/messages/search` is the released server-search mechanism.

## Stable product facts

- Native Windows client: .NET 8 + WPF, protocol isolated in `MailRuDesktop.Protocol`.
- Latest verified public release as of 2026-10-09: v0.3.31 (v0.2.0 was historical).
- Normal runtime has no artificial five-second delay; research/probe/test requests keep at least five seconds spacing.
- CAPTCHA/reCAPTCHA/additional interactive verification stops authorization; no bypass.

Current Owner-gated release (2026-10-09): v0.3.32. Mail body no longer blocks on sequential images; Save As copies Markdown, target-name overwrite requires confirmation. Issue #77 Send Now still disabled pending duplicate-safe protocol evidence.

Current user-gated release: v0.3.32 staged reader (PR #100, Windows CI passed); K-001.mp4 shows old v0.3.30 image-driven flicker. Real correction unconfirmed until Owner re-test. Issue #77 blocked.

New product branch (2026-10-09): v0.3.32 / PR #102, one WebView2 persistent browser-shell DOM staging (zero per-letter top-level navigation, no WPF overlay/Visibility flip), branch and main CI passed, Owner visual acceptance pending. Issue #77 Send Now blocked.


## 2026-10-09 — входящие уведомления оригинального APK и закрытие v0.3.32

Владелец сообщил о решении задач 1–3 (мерцание, встроенные изображения, скорость); считать их закрытыми на основании сообщения владельца, не повторять исправление без нового дефекта.

В сохранённом декомпилированном коде Android 15.107.0.148045 `PushMeParamsPreparerImpl` формирует подписку для каждого провайдера с отдельным токеном доставки, данными учётной записи, OAuth/cookie, настройками и устройством; выявлены значения платформ `android`, `huawei`, `vkpns`. APK содержит `push_default_host=alt-push-me.mail.ru`. Это доказывает наличие механизма регистрации уведомлений, но НЕ доказывает конкретный приёмник событий, задержку, поведение при восстановлении сети или возможность прямого подключения Windows. Техническая заметка: `docs/research/android-new-mail-notifications-2026-10-09.md`.

Наш Windows-клиент в `MainWindow.FolderCounters.cs` проверяет счётчики активного аккаунта раз в 90 с. Проверка числа непрочитанных сама по себе не распознаёт новое письмо и не является уведомлением Windows.


## 2026-10-09 — доказанная цепочка сигналов в Android APK Mail.ru

Повторно распакован оригинальный APK 15.107.0.148045, SHA-256 PASS, Github Actions 37870637190 PASS; сохранено 380 выбранных классов, 2.51 МБ, исследовательская ветка коммит 2ef598f7b58ec2e4371cdc4bbaa49484e958a4c3. PushFactoryCreatorKt маршрутизирует GCM/HMS/VKPNS; RuStore MailMessagingService получает onMessageReceived, onNewToken, onDeletedMessages; CopyPushTokensToPushMeSDK передаёт токены провайдера в PushMeSdk; NewMailPush содержит ID и метаданные нового письма; PushMessageServiceVisitor вызывает NotificationHandler.showNotification. Эта статическая проверка **не устанавливает** путь независимой доставки Windows или точный HTTP-договор регистрации: большие методы PushMeApiImpl декомпилированы неполно. Первичные сведения от разработчиков VK подтверждают RuStore как Android-доставщика, а отдельно существующий браузерный сервис Mail.ru Notifier через WebSocket (не тождественен APK транспорту). Подробности в docs/research/android-new-mail-notifications-2026-10-09.md.


## 2026-10-09 — исходный Google FCM и независимый Windows-приёмник

Владелец подтвердил работу оригинальной Почты Mail на телефоне без RuStore/VK. APK выборочно декомпилирован снова: 37872182332 PASS, коммит research 7d5de6c41af591c0e4eaca30ee601bba4a2ea004, 93 файла. SetUpPushComponent предпочитает Google GCM при доступности системных Google Play Services, иначе HMS, опционально VKPNS. GcmPushKitWrapper получает FCM токен через FirebaseInfoProvider.getToken(push_sender_id), GCMAvailabilityChecker требует Google Play Services, MailMessagingService extends FirebaseMessagingService onMessageReceived→PushMeSdk и уведомитель, onNewToken→PushMeSdk. SetUpPushMeSdk извлекает для каждого аккаунта ru.mail.oauth2.access; PushMeSDKPusherTransport регистрирует аккаунты и токены в PushMe. Документ docs/research/android-new-mail-notifications-2026-10-09.md. Независимый настольный Google FCM приёмник Superhuman/push-receiver v2.1.7 использует Firebase Installations/Registrations, TLS mtalk.google.com:5228, устранение дублей; проект активно исправлял реальные пакеты Google в июне-июле 2026. Однако прямое получение Mail.ru сигналов в Windows и принятие собственного FCM токена ещё не доказаны. Не подменять таймерным опросом и не копировать чужие токены.


## 09.10.2026 — два ID Firebase и точный мобильный договор

По решению владельца MailRu Desktop должен использовать исходную идентичность официального мобильного приложения без создания отдельной регистрации продукта; только новый персональный адрес доставки компьютера, не копию токена телефона. Из проверенного APK через Actions 37874537322 PASS извлечены push_sender_id=1098335887158, gcm_defaultSenderId=61247752867, google_app_id=1:61247752867:android:d199c9f145040309, project_id=fluorcorpmailru, PushMe host=alt-push-me.mail.ru, api=api. GcmPushKitWrapper использует sender 1098335887158. При сериализации PushMe SDK Firebase platform=android, внутренний транспорт=fcm. Действующие аккаунты Windows уже используют мобильный access_token из AJ, но PushMe подписка Windows не подтверждена. Договор docs/research/mailru-mobile-push-contract-2026-10-09.md; FirebaseInfoProvider deep dive Actions 37874791066.
