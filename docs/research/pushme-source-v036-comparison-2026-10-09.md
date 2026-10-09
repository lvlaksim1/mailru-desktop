# MailRu Desktop — воспроизведение Google/PushMe из официального Android APK

Дата: 09.10.2026. Источник: сохранённый оригинальный APK ru.mail.mailapp 15.107.0.148045, исследовательская ветка research/mail-apk-15.107.0.148045. Документ основан на **чтении исходных классов**, новых сетевых испытаний Google/PushMe не проводилось.

## Установленные исходным кодом механизмы

1. `ru/mail/util/push/gcm/GcmPushKitWrapper.java`: токен приложения получается через `FirebaseInfoProvider.getToken(push_sender_id)` для sender=1098335887158. Изменения токена доставляются `MailMessagingService.onNewToken`; вызываются `PushMeSdk.onNewToken(token,Transport.FIREBASE)` и `PushTokenRefreshedNotifier`.
2. `ru/mail/util/push/gcm/MailMessagingService.java`: `onMessageReceived(RemoteMessage)` отправляет `remoteMessage.getData()` одновременно в `PushMeSdk.onMessageReceived` и уведомитель приложения.
3. `ru/mail/util/push/pusher/PushMeSDKPusherTransport.java`: `registerMailAppForPushes` собирает `Application.AccountRequest` для **всех** почтовых аккаунтов и передаёт `PushMeSdk.getApp().registerAccounts(...).execute()`.
4. `com/vk/pushme/logic/request/NewSubscriptionRequest.java`: `updateOrInsertByAccount` объединяет новые и сохранённые подписки по имени аккаунта, затем сохраняет в БД успешные подписки, при частичной ошибке — только успешные.
5. `com/vk/pushme/network/PushMeApiImpl.java`: `parseSubscriptionResponseToResult` при `error.code != 0` возвращает серверную ошибку. При `error.code=0` и отсутствии `validate_result` или пустом списке ошибочных `is_valid=false` возвращает `OK`. Только явно отвергнутые аккаунты отмечаются как неудачные.
6. `com/vk/pushme/logic/usecase/SubscriptionUseCase.java`: учётная запись нормализуется `toLowerCase(Locale.ROOT)`, для актуальной схемы `isV1(...)=false`, OAuth запрашивается индивидуально на каждый аккаунт.
7. `com/vk/pushme/logic/request/UnsubscribeRequest.java`, `com/vk/pushme/logic/usecase/UnsubscribeUseCase.java` и `PushMeApiImpl.unsubscribeByDeviceId`: имеется отдельная операция удаления подписки аккаунта с использованием идентификатора устройства. Она отлична от `unsubscribeByToken`, который относится к получателю целиком.
8. `com/vk/pushme/logic/SubscriptionBatcher.java`: исходный SDK группирует записи по приложению и имеет ограничение `limit`. Конкретный вызов и значение лимита по недекомпилированному `SubscriptionUseCase.invoke` пока не восстановлены — нельзя выдумывать допустимые размеры пакетов.

## Устранённые расхождения v0.3.36

- Наш Windows-код ранее требовал `validate_result.account.is_valid=true` по **каждому** аккаунту и отвергал ответ без `validate_result`, хотя актуальный SDK считает такой ответ успешным. Исправлена только общая PushMe-схема через `ParseSharedSubscriptionResponse`; она повторяет `PushMeApiImpl.parseSubscriptionResponseToResult`.
- Диагностика скрывала тип сбоя и сводила его к «общий канал недоступен». Теперь отдельно указаны безопасные категории HTTP/TLS/MCS/ошибки формата, без текста запроса, ответа, адресов ящиков и токенов.
- Ручной трёхминутный просмотр уведомлений временно останавливает общий канал, не отзывая его постоянный токен; явное выключение уведомлений и удаление последнего аккаунта остаются отдельными операциями.

## Что ещё НЕ подтверждено первоисточником

- В оригинальном Mail.ru APK вызывается Firebase/Google Play services; собственная реализация сетевого MCS протокола и его восстановление после обрыва выполняются **внешними службами Google**, а не классами Mail.ru APK. Исходников этого системного компонента в изучаемом APK нет. Поэтому нельзя представлять самодельные интервалы MCS, подтверждения и повторные попытки Windows как буквально скопированный алгоритм Mail.ru.
- Крупные корутинные методы `SubscriptionUseCase.invoke`, `UnsubscribeUseCase.invoke`, `PushMeApiImpl.unsubscribeByDeviceId` и `setSettingsInternal` переполнены при JADX 1.5.6. Для точной реализации адресного снятия подписки необходимо статически разобрать соответствующий DEX (без сетевых экспериментов), включая маршрут, заголовки, поля и логику повторов.
- Предыдущая v0.3.36 по-прежнему отзывается от всех аккаунтов при удалении одного. Это **временное расхождение** с SDK; заменять его предполагаемым запросом нельзя.
- Причина показанного владельцем цикла «Google LOGIN_OK → разрыв» точно не идентифицирована, так как прежний код стирал причину ошибки. Исправленный ответный обработчик устраняет достоверную несовместимость, но не доказывает живую работу всех 32 аккаунтов.

## Правило дальнейших изменений

Каждое изменение протокола — только после привязки к конкретному методу сохранённого APK или подтверждённому внешнему системному компоненту Firebase. Доказательство компиляции/автономных тестов не является доказательством серверной доставки. Запрещён подбор адресов, полей и интервалов посредством пробных запросов на пользовательских ящиках.
