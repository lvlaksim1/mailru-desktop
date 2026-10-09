# MCS_LOGIN_REJECTED в MailRu Desktop v0.3.41: разбор без пробных запросов

Дата: 09.10.2026. Источник: обезличенный журнал пользователя версии 0.3.41.0.

## Что фактически произошло

- 19:18:26 — 32 сохранённых аккаунта, новая регистрация Google.
- 19:18:38 — TCP/TLS к Google MCS выполнены, ответ MCS version=41, tag=3 (LoginResponse).
- 19:18:38 — **наш собственный** проверяющий код пометил ответ `MCS_LOGIN_REJECTED`, но журнал v0.3.41 НЕ записывал отдельные признаки `id`, вложенный `ErrorInfo.code`, поэтому нельзя достоверно утверждать, что Google действительно отказал.
- Запрос регистрации в PushMe **вообще не выполнялся**. Отклонение v0.3.40 `PushMe 499 INVALID_FORMAT` ни подтверждено, ни опровергнуто обновлённым приложением.
- 19:18:44 — `RECONCILE_DISABLED`, активный приём остановлен. Источник (нажатие галочки, смена состояния UI) текущим журналом не установлен.

## Открытые реализации протокола

Первоисточник схемы MCS: [Chromium google_apis/gcm/protocol/mcs.proto](https://chromium.googlesource.com/chromium/src/+/66.0.3359.158/google_apis/gcm/protocol/mcs.proto):
- MCS tag `3` = `LoginResponse`.
- `LoginResponse.id` = обязательное поле **1**; `LoginResponse.error` = необязательное вложенное поле **3**.
- `ErrorInfo.code` = обязательное поле **1**, целое 32 бит; `ErrorInfo.message` = поле 2, строка, потенциально конфиденциальна.
- Ошибка не определяется одним фактом наличия `error`.

[Реализация Chromium MCSClient](https://chromium.googlesource.com/chromium/chromium/+/trunk/google_apis/gcm/engine/mcs_client.cc): `if (login_response->has_error() && login_response->error().code() != 0)` сбрасывает соединение; иначе `state_ = CONNECTED`. Исходники [microG GmsCore McsService.java](https://github.com/microg/GmsCore/blob/master/play-services-core/src/main/java/org/microg/gms/gcm/McsService.java) также обрабатывают отдельный `LoginResponse.error`, не рассматривая сам tag=3 как отказ.

## Найденное несоответствие нашего обработчика

До исправления `MailRuPushProbe.Shared.cs` и `MailRuPushProbe.cs` проверяли:
`!HasField(reply, 1) || HasField(reply, 3)`.

Таким образом, даже если `error.code == 0`, локальная программа объявляла отказ и не переходила к PushMe. Наш лог v0.3.41 не показывает, встречался ли именно нулевой `error.code`; это пока только **подтверждённая ошибка в условиях проверки**, но не доказанная конкретная причина одного отказа.

## Исправление

- Добавлен чистый анализатор `PushWire.ClassifyMcsLoginResponse` для вложенного `ErrorInfo`, без доступа к сети.
- Успешный вход: допустимая версия, tag=3, наличие обязательного `id` и **отсутствие ненулевого кода ошибки**. Повреждённые ErrorInfo без обязательного code трактуются как некорректный ответ.
- В локальную диагностику пишутся только `LOGIN_ID_PRESENT`, `LOGIN_ERROR_PRESENT`, `LOGIN_ERROR_CODE` либо `LOGIN_ERROR_CODE_MISSING`; никакие содержимое id, сообщение об ошибке, токены или сырые байты не выводятся.
- Отличаются `MCS_SERVER_LOGIN_ERROR` (получен ненулевой код) и `MCS_LOGIN_INVALID_RESPONSE` (проблема структуры).
- Обе ветки приложения (общий приём/ручная проверка) используют один проверяющий метод.
- Автономные тесты с вымышленными protobuf-кадрами: без ErrorInfo — OK; code=0 — OK; code=7 — отказ; ErrorInfo без code — отказ; нет id или неверный tag/version — отказ.

## Повторное создание Google Identity

В штатной реализации галочка «получать новые письма» в состоянии `off` вызывает `MailRuPushBackgroundService.Reconcile(...,enabled:false)`, далее `UnsubscribeSharedAsync` и при успешном отзыве токена — `SharedGooglePushIdentityStore.Delete()`. Поэтому после выключения/включения приложения или галочки в следующем цикле может наблюдаться `IDENTITY_CREATE`; этого нельзя приписать обновлению схемы CommonId без фактов.

## Ограничения

Никаких экспериментальных запросов к Google/PushMe и никаких манипуляций существующими токенами пользователя при разработке. Даже после успешного CI необходимо увидеть реальный `MCS_LOGIN_OK`, затем `PUSHME_SERVER_API_CODE` и `PUSHME_ACCEPTED` из штатного безопасного журнала. Не считать отказ PushMe 499 устранённым по одному ответу MCS.
