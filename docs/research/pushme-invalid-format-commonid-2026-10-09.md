# PushMe 499 / INVALID_FORMAT — сверка идентификаторов по оригинальному APK

Дата исследования: 09.10.2026. Пользовательский журнал MailRu Desktop 0.3.40: 32 аккаунта; Google MCS LOGIN_OK (41/tag=3), PushMe HTTPS HTTP 200, JSON `error.code=499`, классифицированное сообщение `INVALID_FORMAT`. После этого Windows-программа сама завершает MCS; это не подтверждение отказа Google.

## Первоисточник (статический разбор)

Источник: **оригинальный** APK Mail.ru 15.107.0.148045 из заархивированных частей в исследовательской ветке GitHub. SHA-256 архивного комплекта: `42c976a0c2f3d5fb186a88bc855f782c81c8ce35f952fcf22f99c64828880af6`. Декомпиляция и извлечение байткода проводились **без сетевых запросов** к Mail.ru/Google.

[Подробная трассировка DEX](original-apk-pushme-unsubscribe-dex-2026-10-09.md), классы `com/vk/commonid/CommonIdProvider`, `CommonIdPrefs` (classes3.dex) и `com/vk/pushme/util/provider/impl/DeviceIdProviderImpl`.

- `DeviceIdProviderImpl.getAndroidId` читает `Settings.Secure.android_id` Android.
- `DeviceIdProviderImpl.getDeviceId` делегирует `getSdkDeviceId`, который обращается к `CommonIdProvider.getCommonIdGenerated`.
- `CommonIdProvider` сначала читает `__common_id_value__` из `__common_id_prefs__`. При необходимости создаёт новый идентификатор из двух компонентов через `joinToString(":")`: `android_id` (или `default`, если нет) и `MD5` конкатенации системных полей Android `Build.PRODUCT`, `BOARD`, `BOOTLOADER`, `BRAND`, `DEVICE`, `DISPLAY`, `FINGERPRINT`, `HARDWARE`, `HOST`, `ID`, `MANUFACTURER`, `MODEL`, `TAGS`.
- `PushMeApiImpl.mapSubscriptions` и `InternalSubscriptionRequest$$serializer` используют поля `android_id`, `sdk_device_id`; `settings.device_id` получает такой же `CommonId`.
- Оригинальная модель верхнего JSON: `account`, `application`, `platform`, `token`, `access_token`, `android_id`, `sdk_device_id`, `settings`, `status`. Их названия в прежней Windows-реализации совпадали с APK.

## Установленное несовпадение v0.3.40

Windows формировал `sdk_device_id` и `settings.device_id` как `mailru-windows-` + hex Google Device ID, а поле `android_id` как **десятичное** значение того же Google Device ID. Это НЕ схема Android SDK. Особенно важно, что одиночный диагностический приёмник прежних версий использовал другой, более длинный временный идентификатор; разница была не только в количестве аккаунтов.

`INVALID_FORMAT` подтверждает отказ формата на стороне PushMe, но **не сообщает имя отвергнутого поля**, поэтому недопустимо заявлять, что именно идентификатор наверняка вызвал 499. Исправление устраняет доказанную несовместимость независимо от гипотезы.

## Адаптация на Windows

Windows не имеет `Settings.Secure.android_id` и полей Android `Build.*`. Нельзя буквально прочитать их из APK или Windows. Вместо этого для **виртуального Android-получателя** создаётся отдельный локальный 16-символьный hex ID и постоянный профиль виртуальной сборки Android. Общий идентификатор строится по **восстановленной схеме** `android_id:MD5(virtualBuild)` и хранится в DPAPI вместе с Google-получателем. Значения не копируют настоящий телефон и не распространяются между устройствами.

- `android_id` в запросе совпадает с первой частью CommonId.
- `sdk_device_id` и `settings.device_id` равны полному CommonId.
- Google MCS Device ID и Google токен не используются в полях идентификатора PushMe и не меняются при обновлении приложения.
- Старые DPAPI записи без CommonId мигрируют без отзыва Google-токена.
- Временная одиночная диагностика также формирует APK-совместимый CommonId и по-прежнему не влияет на телефон.
- Проверки автономны и используют фиктивные аккаунты и токены; ни одного экспериментального HTTP запроса по подбору формата не создаётся.

## Граница доказательности

Подтверждены исходниками APK формат и связность полей, а также необходимость отличать Google Identity от PushMe CommonId. Не подтверждены ответом сервера после исправления успешные подписки всех 32 ящиков. Другие, ещё не найденные расхождения структуры JSON возможны. Нельзя объявить исправленным отказ 499 до пользовательского штатного отчёта после установки новой версии.
