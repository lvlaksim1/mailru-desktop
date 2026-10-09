

## 2026-10-09 — исследование альтернативного транспорта Google по замечанию владельца

Владелец подтверждает своевременные новые письма на телефоне без пользовательской установки RuStore/VK. APK имеет GCM/FCM как отдельный канал, не следует считать это доказательством отсутствия системных служб Google. Раскрыт точный формат серверных сигналов PushProcessor (4 новое письмо, 40 напоминание, 6 удаление уведомления, 10 счётчик, 13 перемещение, 101 проверка связи). Источник docs/research/android-new-mail-notifications-2026-10-09.md.

Запущена повторная выборочная декомпиляция самого Google-транспорта, Actions 37872182332, исходный коммит workflow 78e2034d78d5706d653905bc1a7b66a71d2639e7. По последней проверке восстановление архива SHA-256 и установка JADX прошли; анализ исходников ещё шёл. Результат нельзя выдавать за окончательный до проверки ветки.

Обнаружен активно использовавшийся в 2026 году независимый настольный транспорт superhuman/push-receiver v2.1.7 с Firebase Installations, FCM Registrations и постоянным TLS mtalk.google.com:5228, поддержкой повторного соединения/идентификаторов. Доказательство реальной эксплуатации — изменения обработки FCM пакетов от 2026-07-16; не доказана возможность подписать Windows токен на конкретный ящик Mail.ru. Продукт не менялся, серверные данные владельца не запрашивались.


## Итог дополнительного анализа Google FCM 2026-10-09

Целевая декомпиляция оригинального APK завершена SUCCESS (37872182332, исследовательская ветка 7d5de6c41af591c0e4eaca30ee601bba4a2ea004): 93 файла, 528770 байт. Код напрямую подтверждает выбор GCM при доступных Google Play Services, HMS как альтернативы; FirebaseInfoProvider.getToken(senderId), FirebaseMessagingService.onMessageReceived → PushMeSdk + уведомитель, onNewToken → PushMeSdk, регистрацию всех аккаунтов через ru.mail.oauth2.access. Совместимость нового Windows токена с почтовым PushMe и реальная доставка на Windows не проверены. Рабочий продукт не изменён.


## 2026-10-09 — исходная идентичность мобильных уведомлений

По указанию владельца не создаём новое приложение Mail.ru, сохраняем параметры официального APK и формируем запросы по его протоколу. Ресурсный анализ APK (SHA-256 PASS), GitHub Actions 37874537322 SUCCESS: push_sender_id 1098335887158, gcm_defaultSenderId 61247752867, google_app_id 1:61247752867:android:d199c9f145040309, project fluorcorpmailru, PushMe alt-push-me.mail.ru, api=api. GcmPushKitWrapper.getToken использует первый идентификатор. Исследована подписка на все аккаунты, application mail, on-wire platform android, access_token и отдельный token доставки. Current Desktop хранит мобильные OAuth access/refresh токены; принятие их PushMe не проверено. Детальная спецификация docs/research/mailru-mobile-push-contract-2026-10-09.md. Actions 37874791066 (FirebaseInfoProvider) запущено, результата пока нет. Продукт не меняли.


## 2026-10-09 — впервые подтверждено независимое получение Google токена и вход MCS

Original APK FirebaseInstanceInfoProvider подтверждён Actions 37874791066 SUCCESS; вызов FirebaseInstanceId.getInstance().getToken(senderId,FirebaseMessaging.INSTANCE_ID_SCOPE), sender=1098335887158. Исследовательская Python-программа не требует Android, восстановила Google Checkin и сделала единичный register3; Actions 37875870944 PASS, TOKEN_ISSUED. Дополнительный Actions 37876061718 PASS, checkin OK, TOKEN_ISSUED, защищённый login mtalk.google.com:5228 успешно принят (LOGIN_OK). Не использовалась ни почтовая авторизация, ни телефон. Токен и секреты Google не публиковались и удалились после краткого эксперимента. Отсутствуют испытания реального сообщения, принятия PushMe аккаунта, длительного соединения. Договор docs/research/mailru-mobile-push-contract-2026-10-09.md обновлён. Запущено извлечение точных параметров PushMe (Actions 37876287988). Основной код приложения и релиз не изменены.
