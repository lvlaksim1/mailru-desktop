# MailRu Desktop — передача менеджера 2026-10-09: Google/FCM без RuStore/VK

Репозиторий lvlaksim1/mailru-desktop; менеджер project-manager; ветка main продукт/менеджер, исследование оригинального APK в research/mail-apk-15.107.0.148045. Пользовательские задачи 1–3 версии v0.3.32 (мерцание, встроенные картинки, задержки) закрыты владельцем, продуктовую логику не менять без дефекта.

Владелец подтвердил оригинальный Android Mail без установленных приложений RuStore/VK и своевременные уведомления. Не делать из этого вывода об отсутствии предустановленных Google Play services/Huawei HMS. APK содержит GCM/HMS/VKPNS, PushProcessor с кодами 4/40/6/10/13/101 и NewMailPush с данными письма. Прямой транспорт через Google на телефоне правдоподобен, но конкретное устройство не инструментировано.

Запущена повторная выборочная декомпиляция оригинального APK: GitHub Actions 37872182332, коммит исследования 78e2034d78d5706d653905bc1a7b66a71d2639e7. Восстановление архива и JADX прошли, последняя проверка основного разбора — in_progress. Проверить итог прежде чем утверждать завершение.

Найден реальный поддерживаемый в 2026 году образец независимого FCM приёмника: https://github.com/superhuman/push-receiver , версия 2.1.7, коммит 87395486756f137b70c8a51d998dc6a6c57a5da6 (изменения июня 2026), механизм Firebase Installations/Registrations и TLS mtalk.google.com:5228, повторное соединение и persistent IDs. Образец НЕ доказывает, что Mail.ru примет Windows FCM токен. Библиотеки javajuice1337/push-receiver-v2 и Liam Cottle устарели. Исследование: docs/research/android-new-mail-notifications-2026-10-09.md. Рабочая программа без изменений.

Дальнейший этап — доказать FCM токен и конкретную серверную подписку Mail.ru на разрешённом тестовом ящике, затем фактическую доставку события, не подменяя таймером. Issue #77 остаётся блокированной. Не копировать чужие токены, сохранять персональные данные только локально.


Последнее подтверждение 2026-10-09: GitHub Actions 37872182332 SUCCESS; исследовательский коммит 7d5de6c41af591c0e4eaca30ee601bba4a2ea004, 93 файла исходников GCM и PushMe. SetUpPushComponent выбирает GCM при наличии Google Play Services, иначе HMS, опционально VKPNS. GcmPushKitWrapper получает FCM токен по push_sender_id; MailMessagingService принимает данные Firebase и передаёт в PushMeSdk и почтовый уведомитель; SetUpPushMeSdk и PushMeSDKPusherTransport подписывают все аккаунты через ru.mail.oauth2.access. Полное исследование docs/research/android-new-mail-notifications-2026-10-09.md. Реальный Windows путь и принятие нового FCM токена сервером Mail.ru ещё не проверены; следующий безопасный отдельный опыт. Основной MailRuDesktop не изменён.


## Передача исследования: 09.10.2026

Правило владельца: максимальное использование исходного APK, никаких новых приложений/проектов Mail.ru, запросы строго по мобильному протоколу; собственный токен доставки Windows допустим и обязателен, копировать телефонный нельзя. Проверка оригинальных ресурсов Actions 37874537322 PASS: push_sender_id=1098335887158, gcm_defaultSenderId=61247752867, google_app_id=1:61247752867:android:d199c9f145040309, project fluorcorpmailru, alt-push-me.mail.ru, api. GcmPushKitWrapper использует push_sender_id; InternalSubscriptionRequest network platform=android, application=mail, token FCM и access_token почты раздельно. Windows-клиент уже имеет мобильную авторизацию, но PushMe её не подтверждал. Спецификация docs/research/mailru-mobile-push-contract-2026-10-09.md. Запущена проверка FirebaseInfoProvider (Actions 37874791066), итог пока не подтверждён. Рабочая программа не изменена.
