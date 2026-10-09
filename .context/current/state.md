

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


## 2026-10-09 — изолированный Windows стенд готов, полная доставка не доказана

Исследовательская ветка содержит tools/push_research/local_mailru_event_probe.py, test_local_probe.py, Google probe и PushMe contract probe; ввод токенов Mail.ru запрещён в GitHub. Windows DPAPI читается локально из существующего auth.json по выбранному пользователем логину, только при --live --subscribe. Гарантирован запрос unsubscribe_by_token для собственного временного токена после завершения опыта. Подтверждено Google checkin/token issuance/login MCS; Windows/Linux offline tests Actions 37877281592 и 37877932559 PASS, включая реальный тест DPAPI на вымышленных значениях; EXE Actions 37877413352 PASS, краткосрочный артефакт, НЕ релиз. Подробности docs/research/windows-push-trial-2026-10-09.md. Нет реальной подписки и event=4, profile capabilities пока оставлен пустым и НЕ считается точным APK. Повторное извлечение нужных источников Actions 37877449399 в работе. CI main 37877585319 после первичного падения WPF UI при повторе SUCCESS. Основной продукт и инсталлятор не изменялись.


## 2026-10-09 — подготовленный точный Windows опыт уведомлений, критерий реального письма ещё открыт

Извлечена реализация ru.mail.util.push.provider.impl.MailCapabilitiesProvider (37878519042 SUCCESS), реальные поля can_mail.Filter.Folder/SocialNetwork/SocialService; выбран вариант без фильтров как испытательная настройка. Подтверждены SDK V2, account.lower(), status=0; client/name=ru.mail.mailapp, формат GMT+HHMM (из оригинального com.vk.pushme ClientInfoProviderImpl). Исправлены исходники research tools/push_research/, Windows/Linux offline tests 37878867912 PASS, переносимый Windows exe с явным Start_Push_Trial.cmd Actions 37878867920 PASS, доступен 1 день. Чтение действующего мобильного OAuth только локально через Windows DPAPI, тестовые секреты в публичных CI не используются; каждый новый токен Google отдельный, cleanup unsubscribe_by_token только своего токена. Не подтверждены принятие настоящим сервером Mail.ru и event4. Подробности main docs/research/windows-push-trial-2026-10-09.md. Продукт и релиз нетронуты.


## 2026-10-09 — внедрена в main нативная проверка уведомлений v0.3.33

С учётом прямого требования владельца о тестировании в основном приложении интегрированы native .NET8 MailRuPushProbe.cs, PushProbeWindow.cs, MainWindow.PushProbe.cs и новая настройка в MainWindow.xaml; никаких сторонних exe, Python в установке, второго WebView2 или таймерного опроса почтовых папок. Только выбранный аккаунт и явное согласие; обособленный Google recipient, TLS MCS, одна PushMe V2 подписка, event4, попытка собственной отписки, обновление списка по событию. Версия проекта 0.3.33, исходная документация docs/research/native-push-integration-2026-10-09.md. Новые проверки tests/InteractionLogicSmoke и WindowsUiSmoke; сборка не завершена итоговым CI, выпуск не подтверждён. Генератор реестра интерфейса прошёл на GitHub Actions 37880300567, новый коммит 33bd848. Реальный серверный Mail.ru ACCOUNT_ACCEPTED/event4 не проверен — требуется опыт владельца после установки.


## 09.10.2026 — штатный выпуск v0.3.33 доступен пользователю

Main содержит native .NET PushMe + Google/MCS и WPF окно проверки в Настройках (без автозапуска). CI 37880498382 SUCCESS. Штатный релиз GitHub Actions 37880613099 SUCCESS, выпуск v0.3.33 опубликован 09.10.2026, два файла реально найдены: https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.33/MailRuDesktop_Setup_v0.3.33.exe и https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.33/MailRuDesktop_Update_v0.3.33.exe. Основной сценарий испытания теперь через интерфейс и OAuth из действующего AuthorizationStore; личные данные не выгружаются. Реальная серверная подписка Mail.ru и получение event4 пока не тестировались на компьютере пользователя.


## 2026-10-09 — in-app Mail.ru network failure diagnosed, official Prod repair shipped v0.3.34

User's v0.3.33 in-app test: Google check-in OK, TOKEN_ISSUED, MCS LOGIN_OK, network failure at Mail.ru subscription; cleanup not confirmed. Independent public endpoint diagnostics Actions 37881120525/37881273739: AltProd alt-push-me.mail.ru certificate chain error 20, Prod push-me.mail.ru valid TLS and POST /api/v2/set_settings with empty [] returns HTTP200. Original APK PusherHost.Prod explicitly defines Prod. Main changed subscription and cleanup host to Prod without bypassing certificates, added sanitized error classifications. CI 37881506882 SUCCESS, release 37881628473 SUCCESS, download https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.34. No actual Mail.ru ACCOUNT_ACCEPTED or delivered event4 proved yet. User already received update links.


## 09.10.2026 — передача в новый чат
Текущая версия MailRu Desktop: v0.3.35. Полный установщик и обновление опубликованы в GitHub Releases. Первое реальное уведомление о новом письме доставлено и подтверждено пользователем; длительная работа нескольких аккаунтов ещё не проверена. Следующий разговор продолжить с вопросов о кодах событий и организации общего получения уведомлений мобильным приложением. Не изменять продукт только ради переноса контекста.
