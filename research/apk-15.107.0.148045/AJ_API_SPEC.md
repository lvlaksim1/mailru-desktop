# AJ API: спецификация по Mail.ru Android 15.107.0.148045

Исследуемый пакет: `ru.mail.mailapp` 15.107.0.148045.

Эта спецификация собирается для проекта Mail.ru Desktop из двух независимых типов доказательств:

- **APK** — непосредственно код и константы текущего официального Android-клиента.
- **Живой AJ** — ранее подтверждённые рабочие запросы к `aj-https.mail.ru` из нашего обратного разбора.
- **Кандидат** — операция или структура подтверждена APK, но точный маршрут либо метод ещё не восстановлен.

Неподтверждённые сведения не выдаются за установленный протокол.

## 1. Узлы и авторизация

### AJ

Основной почтовый протокол относится к семейству узлов:

- `aj-https.mail.ru` — основной узел, подтверждён прежними живыми запросами;
- `alt-aj-https.mail.ru` — прямо присутствует в текущем APK;
- схема: HTTPS.

Текущий APK содержит прямую строку:

`https://alt-aj-https.mail.ru/cgi-bin/auth?Lang=en_US&mp=android&mmp=mail`

Также присутствует `/cgi-bin/auth?Login=`.

### Токен

Текущий APK:

- `TornadoSession.TOKEN_PARAM_NAME = "access_token"`;
- `TornadoMpopSession.TOKEN_PARAM_NAME = "token"`;
- `ServerCommandBaseParams.PARAM_KEY_EMAIL = "email"`;
- `ServerCommandBaseParams.PARAM_KEY_LANG = "lang"`.

Для нашего почтового режима используется `access_token`.

### Вход

**Живой AJ, подтверждено:**

`POST /cgi-bin/auth?mp=android&udid=mailru_app`

Форма включает:

- `Login`
- `Password`

При необходимости сервер может потребовать CAPTCHA. В приложении Mail.ru Desktop при таком ответе вход прекращается и пользователь получает сообщение о необходимости пройти CAPTCHA вне клиента.

Текущий APK также содержит отдельные состояния входа:

- `CAPTCHA`
- `ERROR_INVALID_LOGIN`
- `ERROR_RATE_LIMIT`
- `MAIL_SECOND_STEP_REQUIRED`
- `MIGRANT_REG_REQUIRED`
- `OAUTH_REQUIRED`
- `OAUTH_OUTLOOK_REQUIRED`
- `OAUTH_YAHOO_REQUIRED`
- `OAUTH_YANDEX_REQUIRED`
- `EXTERNAL_AUTH_PROHIBIT`
- `EXTERNAL_ACCOUNT_REGISTRATION_REQUIRED`
- `SEND_SMS_ERROR`

Это подтверждает, что CAPTCHA и дополнительный шаг входа являются отдельными состояниями протокола.

---

## 2. Получение списка писем и цепочек

### Умный список цепочек

**Живой AJ, подтверждено:**

`GET /api/v1/m/threads/status/smart`

Используется `access_token`.

Текущий APK содержит классы:

- `BatchSmartStatusCommand`
- `SmartMessagesStatusCommand`
- `MessagesStatusCommand`
- парсеры цепочек и сообщений.

### Параметры списка

Из текущего APK, `MessagesStatusCommand`:

- `folder`
- `last_modified`
- `limit`
- `offset`
- `prefetch`
- `refresh_mailbox`
- `snippet_limit`
- `sort`

Значения по умолчанию:

- `prefetch = 1`
- `refresh_mailbox = 1`
- `sort = {"type":"id", "order":"desc"}`

Дополнительные поля:

- `form_sign`
- `form_token`

### Пакетное состояние

Из `BatchSmartStatusCommand`:

Запрос:

- `last_modified`

Ответ:

- `body`
- `error`
- `folders_content`
- `folders`
- `id`
- `threads`
- `value`

Дополнительный признак:

- `threads_mode_enabled`.

---

## 3. Получение одной цепочки

Текущий APK: `ThreadRequestCommand`.

Параметры:

- `id` — идентификатор цепочки;
- `folder`;
- `last_modified`;
- `limit`;
- `offset`;
- `refresh_mailbox`;
- `snippet_limit`.

Также встречается `thread_id`.

`refresh_mailbox` по умолчанию использует значение `1`.

Ответ содержит:

- `body`
- `error`
- `messages`
- `value`.

Для разбора используются `ThreadParser` и `MailMessageParser`.

**Точный маршрут: кандидат.** Операция полностью подтверждена APK, но строка маршрута собирается сетевым слоем и ещё восстанавливается.

---

## 4. Получение полного письма

**Живой AJ, подтверждено:**

`GET /api/v1/messages/message`

Параметры:

- `id`
- `mark_read=false`
- `mp=android`
- `access_token`

Подтверждённые данные ответа:

- тема;
- дата;
- отправитель;
- полный `body.text`;
- вложения.

Текущий APK дополнительно подтверждает структуру полного содержимого:

- `body`
- `html`
- `amp`
- `attaches`
- `attaches.list`
- `images`
- `attaches2cid`
- `draft_type`
- `flags`.

Типы `draft_type`:

- `forward`
- `reply`
- `replyall`.

Флаги полного письма включают:

- `unread`
- `reply`
- `forward`
- `flagged`
- `smart_reply`
- `newsletter`
- `maybe_phishing`
- `official`
- `official_newsletter`
- `trusted_sender_for_corp`
- `pinned`
- `external_links_warning`
- `receipt`
- `internal_auth_passed`.

---

## 5. Модель сообщения

Текущий APK, `JsonMessageParser` / `JsonMessageParserNew`:

- `id`
- `thread_id`
- `folder`
- `date`
- `subject`
- `snippet` или `search_snippet`
- `attachments_count`
- `priority`
- `send_date`
- `snooze_date`
- `receipt_info`
- `meta`
- календарные данные.

---

## 6. Модель цепочки

Текущий APK, `MailThreadRepresentationParser`:

- `attach`
- `attachments_count`
- `bcc`
- `cc`
- `to`
- `from`
- `correspondents`
- `date`
- `flags`
- `folder`
- `forward`
- `reply`
- `unread`
- `pinned`
- `length`
- `length_flagged`
- `length_pinned`
- `length_unread`
- `message_id_last`
- `meta`
- `snippet`
- `subject`
- `snooze_date`
- `receipt_info`
- `have_unsubscribe_list`
- `maybe_phishing`
- `external_links_warning`
- `newsletter`
- `official`
- `official_newsletter`
- `internal_auth_passed`
- `is_relevant`
- `show_definitely_spam`
- календарные данные;
- признаки эмодзи в отправителе, теме и фрагменте текста.

---

## 7. Прочитано / непрочитано и флаг

**Живой AJ, подтверждено:**

`POST /api/v1/messages/marks`

Основное поле:

- `marks`

В `marks` используются операции `set` / `unset`.

Подтверждён признак:

- `unread`.

В той же структуре наблюдался `flagged`; текущий APK также повсеместно содержит `flagged`, поэтому поддержка флага подтверждается моделью клиента. Успешный AJ-вызов изменения флага отдельно ещё следует зафиксировать.

Текущий APK:

- `MarkMessageCommand` использует `PostServerRequest`;
- `MarkThreadCommand` использует `ThreadPostServerRequest`;
- `MarkCommandBaseParams.PARAM_KEY_MARKS = "marks"`;
- `MarkOperation` содержит `UNREAD_SET` и `UNREAD_UNSET`.

Для операций над цепочкой `ThreadPostServerRequest` формирует:

`{ "id": "%s", "folder": %d, "message_id_last":"%s"}`

Поля:

- `id`
- `folder`
- `message_id_last`.

---

## 8. Перемещение, архив, корзина и удаление

### Перемещение сообщения

**Живой AJ, подтверждено:**

`POST /api/v1/messages/move?htmlencoded=false&mp=android&access_token=...`

Форма:

- `folder=<ID целевой папки>`
- `ids=["<ID письма>"]`

### Операции текущего APK

Присутствуют отдельные команды:

- `MoveThreadCommand`
- `TornadoMoveMessage`
- `RemoveThreadCommand`
- `TornadoRemoveMessage`
- `TornadoRemoveRequest`
- `TornadoCleanFolder`.

`MoveThreadCommand` использует `ThreadPostServerRequest`.

`TornadoRemoveRequest` и `TornadoCleanFolder` используют `PostServerRequest`.

**Точные маршруты удаления и очистки папки: кандидат.**

---

## 9. Спам / не спам

Текущий APK подтверждает:

- `SpamThreadCommand`
- `UnspamThreadCommand`
- `TornadoSpamAbuse`
- `TornadoNoSpam`
- `GroupAction.MOVE_SPAM`.

`SpamThreadCommand` и `UnspamThreadCommand` используют `ThreadPostServerRequest`.

**Точные AJ-маршруты: кандидат.**

---

## 10. Поиск

Текущий APK:

- `MessagesSearchCommand`
- `MessagesSearchCommandNew`
- `JsonSearchMsgParser`
- `JsonSearchMsgParserNew`.

Флаги поиска:

- `unread`
- `flagged`
- `attach`
- `pin`.

Диапазон и корреспонденты используют:

- `from`
- `to`.

Старый формат количества результатов читает:

- `folders`
- `found`
- `shared`
- `id`.

Выдача содержит:

- `search_subject`
- `search_snippet`
- `color`
- `correspondents`
- `from`.

**Кандидат маршрута:** семейство `/api/v1/messages/search`. Точный текущий AJ-маршрут и метод должны быть подтверждены декомпилированным сетевым кодом.

---

## 11. Отправка письма

### Маршрут

**Живой AJ, подтверждено:**

`POST /api/v1/messages/send`

Для отложенной отправки:

`POST /api/v1/messages/schedule`

### Поля текущего APK

`TornadoSendRequest` подтверждает следующую модель:

- `id`
- `source`
  - `draft`
  - `reply`
  - `forward`
  - `schedule`
- `headers` — встречается в родственном протоколе, требует отдельной проверки AJ;
- `subject`
- `priority`
- `send_date`
- `body`
  - `html`
  - `text`
- `from`
- `correspondents`
  - `to`
  - `cc`
  - `bcc`
- `receipt`
- `remind`
- `sign`
- `template`
- `quote`
- `edited_contacts`
- `has_attachments`
- `analyzer_assumption`
- `attaches`
  - `list`
  - элементы с `id`, `content_id`, `part_id`, `type`.

Типы вложения:

- `attach`
- `inline`
- `cloud_stock`.

Типы письма/отправителя, встречающиеся в модели:

- `natural`
- `noreply`.

Стратегии `TornadoSendCommand`:

- `SEND_NEW`
- `SAVE_DRAFT`
- `SEND_LATER`.

Таким образом один сетевой механизм используется для новой отправки, черновика и отложенной отправки с различной стратегией.

---

## 12. Черновики

Текущий APK:

- `TornadoDraftRequest` наследует/использует `TornadoSendRequest`;
- стратегия `SAVE_DRAFT`;
- `TornadoSendParamsImpl.MESSAGE_ID = "message_id"`.

Связи исходного письма:

- `source.draft`
- `source.reply`
- `source.forward`
- `source.schedule`.

**Живой AJ:** `source.reply=<ID исходного письма>` ранее подтверждён.

**Маршрут сохранения/обновления/удаления черновика: кандидат.**

---

## 13. Вложения

### Загрузка исходящего вложения

**Живой AJ, подтверждено:**

`POST /api/v1/messages/attaches/add`

Текущий APK:

- `TornadoUploadRequest.TAG_FILE = "file"`;
- параметры содержат `messageId` и запись вложения;
- результат содержит `attachId`.

### Скачивание входящего вложения

Разрешённый отдельный узел:

`https://af.attachmail.ru/cgi-bin/readmsg`

Прямо присутствует в текущем APK.

### Облако

Текущий APK содержит:

- `SaveAttachmentsToCloudCommand`
- `GetCloudAttachmentInfo`
- `CloudAttachmentsUploader`
- `CloudAttachmentsRemover`
- тип `cloud_stock`.

Также есть прямой адрес:

`https://cloud.mail.ru/api/v1/messages/attaches/get?id=`

Это относится к облачному механизму и не должно автоматически переноситься в почтовой клиент как AJ-маршрут.

Максимальный размер, встречающийся в логике вложений: `26214400` байт (25 МиБ); нужно отдельно проверить смысл ограничения перед использованием в интерфейсе.

---

## 14. Папки

Текущий APK содержит:

- `CreateFolder`
- `UpdateFolder`
- `DeleteFolder`
- `CreateArchiveFolderCmd`.

Модель папки `MailboxFolderParser`:

- `id`
- `name`
- `parent`
- `type`
- `system`
- `archive`
- `child`
- `messages_total`
- `messages_unread`
- `threads_total`
- `threads_unread`
- `security`
- `share`
- `grants`
- `owner`
- `email`.

`UpdateFolder.PARENT_DEFUALT = "-1"`.

`DeleteFolder.Params.ids = ""` по умолчанию.

**Точные маршруты получения/создания/переименования/удаления: кандидат.**

---

## 15. Адресная книга

Текущий APK: `AddressBookFetchV2`.

Контакт:

- `id`
- `priority`
- `name`
- `first`
- `last`
- `birthday`
  - `day`
  - `month`
  - `year`
- `nick`
- `emails`
- `sex`
- `company`
- `job_title`
- `boss`
- `address`
- `comment`.

Метки:

- `labels[]`
  - `id`
  - `name`.

Телефоны:

- `phones[]`
  - `type`
  - `phone`.

Типы:

- `mobile`
- `home`
- `work`
- `fax`
- `other`.

Социальные данные:

- `social[]`
  - `type`
  - `account`
  - `displayname`.

**Точный маршрут: кандидат.**

---

## 16. Фильтры

Текущий APK:

- `RequestFiltersCommand`
- `RequestHasFiltersCommand`
- `AddFilterCommand`
- `UpdateFilterCommand`
- `DeleteFilter`.

Условия:

- `name`
- `from`
- `not`
- `value`.

Действия:

- `remove`
- `move`
- `read`
- `flag`
- `reject`
- `forward`
- `reply`
- `notify`.

Ошибки добавления:

- `exists`
- `over_limit`.

**Точные маршруты: кандидат.**

---

## 17. Категории писем

Текущий APK, `ChangeMessageCategoryRequest.Params`:

- `ids`
- `category`
- `drop_category`
- `add_filter`.

Отдельная обратная связь:

- `category`
- `id`
- `other_category`.

**Точный маршрут: кандидат.**

---

## 18. Отписка от рассылки

Текущий APK содержит:

- `UnsubscribeMessageCommand`;
- `CleanKarmaAfterUnsubscribeMessageCmd`;
- в модели цепочки признак `have_unsubscribe_list`.

`UnsubscribeMessageCommand` использует `PostServerRequest`.

**Точный маршрут и тело: кандидат.**

---

## 19. Дополнительные возможности, подтверждённые APK

В клиенте существуют отдельные механизмы:

- закрепление письма/цепочки;
- `snooze_date`, а также `UpdateSnoozeRequest` и `RemoveSnoozeRequest`;
- сохранение вложений в Облако;
- скачивание письма как EML: `DownloadMessageEmlCommand`;
- изменение категории письма;
- отписка от рассылки;
- фильтры;
- защищённые папки;
- архивные папки;
- подтверждение прочтения `receipt`;
- отмена отправки в пользовательской логике (`send_cancellation_edit_count`, `undo_send_duration`).

Для этих функций точные AJ-маршруты ещё нужно привязать к сетевым командам.

---

## 20. Ошибки сервера

Текущий APK содержит отдельные серверные состояния:

- `ATTEMPTS_EXCEEDED`
- `EMPTY_RESULT_ERROR`
- `ERROR_ATTACH_NOT_FOUND`
- `ERROR_CLOUD_IS_FULL`
- `ERROR_DATE_RANGE`
- `ERROR_FOLDER_NOT_EXIST`
- `EXPANDED_SIMPLE_ERROR`
- `FAILED_BACKEND_QUOTE`
- `IMAP_ACTIVATION_NOT_READY`
- `INVALID_SEND_DATE`
- `INVALID_THREAD`
- `MESSAGE_NOT_EXIST`
- `MESSAGE_NOT_IN_THREAD`
- `NO_AUTH_BIND_REQUIRED`
- `NO_AUTH_TWO_STEP_REQUIRED`
- `NO_BODY`
- `NO_HEADER`
- `NO_MSG`
- `QR_TOKEN_NOT_FOUND`
- `STORAGE_UNAVAILABLE`
- `SWITCH_TO_IMAP`
- `THREAD_NOT_EXIST`
- `WAIT_AND_RETRY`.

Отправка отдельно обрабатывает:

- `correspondents.to`
- `correspondents.cc`
- `correspondents.bcc`
- `send_date`
- `file`
- `file_exists`
- `mbox_quotas.attach`
- `mbox_quotas.link_attach`
- `mbox_quotas.box_send`
- `mbox_size_limit_exceeded`
- `disabled`
- `disabled_from_reginfo`
- `invalid`
- `value`.

Mail.ru Desktop должен разделять транспортный HTTP-код и прикладной статус/ошибку из тела ответа.

---

## 21. Что ещё необходимо восстановить из декомпилированного сетевого слоя

Операции подтверждены текущим APK, но ещё требуют точной привязки `метод + AJ-маршрут + тело`:

1. полное получение папок;
2. создание, переименование и удаление папки;
3. окончательное удаление письма;
4. очистка корзины;
5. спам / не спам;
6. изменение флага `flagged` живым AJ-вызовом;
7. поиск;
8. сохранение и обновление черновика;
9. удаление черновика;
10. удаление исходящего вложения;
11. отмена только что отправленного письма;
12. изменение/отмена отложенной отправки;
13. адресная книга и подсказки адресов;
14. фильтры;
15. отписка;
16. изменение категории;
17. закрепление;
18. отложить письмо;
19. защищённые папки.

---

## 22. Правило внедрения в Mail.ru Desktop

Функция переносится в основной клиент только когда установлены:

1. HTTP-метод;
2. точный AJ-маршрут;
3. обязательные параметры;
4. тело запроса;
5. схема успешного ответа;
6. основные ошибки;
7. способ передачи `access_token`;
8. хотя бы одно независимое подтверждение из APK или живого запроса.

Исключение по узлам: скачивание входящих вложений через `af.attachmail.ru` разрешено отдельным решением проекта.


---

## 23. Точные договоры запросов, восстановленные из APK

Ниже — уточнения, полученные непосредственно из аннотаций `@Param`, базовых классов команд и кода разбора ответов текущего APK 15.107.0.148045.

### Общие параметры AJ

Для семейства Tornado:

- `access_token` добавляется `TornadoSession` в строку запроса;
- `lang` добавляется общим `ServerCommandBaseParams`;
- `email` добавляется всеми командами на основе `ServerCommandEmailParams`;
- отдельные команды дополнительно передают `htmlencoded=false`.

Таким образом `access_token` не обязан присутствовать как поле конкретного класса команды: он накладывается слоем сеанса.

### Метки письма и цепочки

`POST /api/v1/messages/marks`

Тело содержит `marks` — JSON-массив объектов. Поддержаны три признака:

- `unread`;
- `flagged`;
- `pinned`.

Каждый объект имеет форму:

```json
{"name":"unread","set":["<message-id>"],"unset":[],"folder":0}
```

`set` и `unset` формируются из `MarkOperation`; `folder` добавляется, когда операция привязана к папке.

`POST /api/v1/m/threads/marks`

Для цепочек элементы `set`/`unset` представлены объектами:

```json
{"id":"<thread-id>","folder":0,"message_id_last":"<last-message-id>"}
```

Тем же механизмом подтверждены `unread`, `flagged` и `pinned`.

`POST /api/v1/messages/marks/all`

Поля:

- `folder`;
- `marks` — JSON-массив названий меток;
- `action`;
- `older_than` — необязательный порог.

### Перемещение и удаление

`POST /api/v1/messages/move`

Поля:

- `ids` — JSON-массив строковых ID сообщений;
- `folder` — целевая папка.

`POST /api/v1/messages/remove`

Поля:

- `ids` — JSON-массив строковых ID сообщений.

`POST /api/v1/messages/move/all`

Поля:

- `folder_from`;
- `folder`;
- `from` — необязательный JSON-массив отправителей;
- `only_newsletters` — необязательный признак;
- `older_than` — необязательный порог.

`POST /api/v1/messages/remove/all`

Поля:

- `folder_from`;
- `only_newsletters`;
- `spam_folder`;
- `older_than`.

`POST /api/v1/m/threads/move`

Поля:

- `ids` — JSON-массив объектов `{id, folder, message_id_last}`;
- `folder` — целевая папка;
- `email`.

`POST /api/v1/m/threads/remove`

Поля:

- `ids` — JSON-массив объектов `{id, folder, message_id_last}`;
- `email`.

Успешный ответ операций над цепочками содержит `body` — массив ID обработанных цепочек.

### Спам

`POST /api/v1/messages/services/spam`

Наследует формат `ids` от операций над сообщениями; дополнительно может передаваться `folder`.

`POST /api/v1/messages/services/unspam`

Наследует формат `ids`.

`POST /api/v1/m/threads/services/spam`

Поля:

- `ids` — объекты цепочек;
- `email`;
- `verified`.

`POST /api/v1/m/threads/services/unspam`

Поля:

- `ids` — объекты цепочек;
- `email`.

### Папки

`GET /api/v1/folders`

Ответ `body` — массив папок. Парсер подтверждает поля:

- `id`, `name`, `parent`, `type`, `system`, `archive`, `child`;
- `messages_total`, `messages_unread`;
- `threads_total`, `threads_unread`;
- `security`, `share`, `grants`, `owner`, `email`.

`POST /api/v1/folders/add`

Тело:

- `email`;
- `folders` — JSON-массив.

Создаваемый объект содержит как минимум:

```json
{"id":-1,"name":"<name>","parent":"-1","only_web":false}
```

Успешный `body[0]` содержит ID созданной папки.

`POST /api/v1/folders/edit`

Поля:

- `email`;
- `folders` — JSON-массив объектов с `id` и `name`.

Успешный `body` содержит ID изменённых папок.

`POST /api/v1/folders/remove`

Поля:

- `ids` — массив ID удаляемых папок;
- `email`.

`POST /api/v1/folders/clear`

Поле `ids` — JSON-массив числовых ID папок.

`POST /api/v1/folders/archive/ensure`

Необязательный параметр строки запроса:

- `folder`.

Ответ `body` преобразуется в числовой ID архивной папки.

`POST /api/v1/folders/open`

Поле `folders` — JSON-массив объектов защищённых папок. Для каждой папки передаются `id` и секрет с `folder_password`.

`POST /api/v1/folders/close`

Поле `ids` — JSON-массив строковых ID папок.

### Поиск

Старый поиск:

`GET /api/v1/messages/search`

Параметры, подтверждённые APK:

- `aqid`;
- `search_categories`;
- `correspondents`;
- `custom_tags`;
- `interval`;
- `flags`;
- `folder`;
- `htmlencoded`;
- `in_excluded_folders`;
- `limit`;
- `offset`;
- `query`;
- `remove_emoji_opts`;
- `snippet_limit`;
- `subject`;
- `with_threads`;
- `transaction_category`.

`correspondents` формируется как JSON с `from` и/или `to`.
`interval` — JSON с `from` и `to`.
`flags` — JSON с признаками `unread`, `flagged`, `attach`, `pin`.

Ответ:

- `body.found`;
- `body.messages[]`.

Новый поиск:

`GET /api/v1/go/search/emails`

Параметры:

- `q`;
- `filters`;
- `aqid`;
- `limit`;
- `offset`;
- `snippet_limit`.

Ответ читается из:

`response.mail_search_messages.result.body`

и содержит:

- `found.count`;
- `messages[]`.

**Важно:** класс нового поиска имеет отдельный `HostProviderAnnotation` с настройкой `search_new_host`. Поэтому этот маршрут пока нельзя считать маршрутом `aj-https.mail.ru`. При принятом правиле проекта AJ-only для реализации следует использовать старый `/api/v1/messages/search`, пока отдельно не будет доказано, что `search_new_host` указывает на разрешённый узел.

Подсказки:

`GET /api/v1/messages/search/suggest`

Параметр:

- `query` — URL-кодированная строка.

### Отправка, черновики и отложенная отправка

Одна модель `TornadoSendParamsImpl` используется для:

- `POST /api/v1/messages/send`;
- `POST /api/v1/messages/draft`;
- `POST /api/v1/messages/schedule`.

Общие POST-поля:

- `id`;
- `subject`;
- `priority`;
- `send_date`;
- `from`;
- `receipt`;
- `quote`;
- `body`;
- `correspondents`;
- `source`;
- `attaches`.

Плюс строка запроса:

- `htmlencoded=false`.

`body`:

```json
{"html":"...","text":"..."}
```

`correspondents`:

```json
{"to":"...","cc":"...","bcc":"..."}
```

`source`:

```json
{"draft":"...","reply":"...","forward":"...","schedule":"..."}
```

`attaches`:

```json
{"list":[
  {"id":"<attach-id>","type":"attach"},
  {"id":"<cloud-id>","type":"cloud_stock"},
  {"content_id":"<cid>","type":"inline","part_id":"<part-id>"}
]}
```

Приоритеты:

- `1` — высокий;
- `3` — обычный;
- `5` — низкий.

### Вложения

`POST /api/v1/messages/attaches/remove`

Поля:

- `message_id`;
- `ids` — JSON-массив серверных ID вложений.

`POST /api/v1/messages/attaches/reattach`

Поля:

- `message_id`;
- `forwarded_id`.

Ответ `body` содержит как минимум:

- `okay_files[]`;
- `error_files[]`.

Успешные элементы разбираются по типам обычного, встроенного, облачного и `cloud_stock` вложения.

### Адресная книга

`GET /api/v1/ab/smart`

Параметр:

- `limit = Integer.MAX_VALUE`.

Заголовок:

- `Accept-Encoding` — задаётся командой отдельно.

Ответ:

- `body.contacts[]`;
- `body.labels[]`.

Контакт включает имя, фамилию, ник, приоритет, день рождения, адреса почты, пол, компанию, должность, руководителя, адрес, комментарий, метки, телефоны и социальные данные.

`GET /api/v1/ab/fast`

Ответ `body` — массив компактных подсказок вида:

```json
["<display-name>",["<email>", ...]]
```

### Фильтры

`GET /api/v1/filters`

Ответ `body[]` содержит:

- `conditions[]`: `name`, `value`, `not`;
- `actions`: `remove`, `move`, `read`, `flag`, `reject`, `forward`, `reply`, `notify`.

`POST /api/v1/filters/add`

Поля:

- `filters` — JSON-массив создаваемых фильтров;
- `apply_folders` — необязательный JSON-массив ID папок.

Создаваемый фильтр содержит:

- `enabled=true`;
- `applyToSpam=false`;
- `conditionsOr=true`;
- `conditions[]`;
- `actions`.

Для условия отправителя:

```json
{"name":"from","not":false,"value":"<address>"}
```

Успешный `body[0]` — ID созданного фильтра.
Ошибки: `exists`, `over_limit`.

`POST /api/v1/filters/edit`

Использует ту же модель, но в объект фильтра добавляется `id`.

`POST /api/v1/filters/remove`

Поле:

- `ids` — JSON-массив ID фильтров.

Успешный `body` возвращает удалённые ID.

### Отписка и категории

`POST /api/v1/messages/services/unsubscribe`

Поле:

- `ids` — JSON-массив строковых ID сообщений.

Ошибка `ids[0].error=invalid` трактуется как отсутствие сообщения.

`POST /api/v1/messages/services/category/change`

Поля:

- `ids` — массив ID;
- `category`;
- `drop_category`;
- `add_filter`.

Категория `newsletters` на уровне клиента преобразуется в `newsletter`.

### Отложенные письма в списке

`POST /api/v1/messages/snoozes/update`

Поля:

- `id`;
- `date` — время в секундах, клиент делит миллисекунды на 1000.

`POST /api/v1/messages/snoozes/remove`

Поле:

- `id`.

---

## 24. Что теперь действительно осталось неизвестным

После разбора маршрутов и параметров основная неизвестность уже не в адресах API. Осталось точечно восстановить или подтвердить:

1. точный ответ `/messages/send`, включая механизм отмены отправки и `cancellation_token`;
2. отдельный маршрут отмены только что отправленного письма;
3. точное удаление/отмена уже созданной отложенной отправки, если оно отличается от обычных операций с сообщением;
4. полная схема `/messages/attaches/add` для каждого типа исходящего файла;
5. точные параметры `/messages/attaches/view`;
6. полный договор `/messages/message/download`;
7. полный договор `/messages/meta`;
8. полный договор подтверждения прочтения `/messages/notify/read`;
9. точная схема `/messages/replies/smart`;
10. точный разрешённый узел для нового `/api/v1/go/search/emails`;
11. некоторые редко используемые ответы и коды ошибок.

То есть маршруты удаления, очистки корзины, спама, папок, поиска, черновиков, адресной книги, фильтров, отписки, категорий, закрепления и отложенного показа уже больше не являются «кандидатами» — они подтверждены непосредственно текущим APK.


---

## 25. Дополнительные точные договоры

### Загрузка исходящего вложения

`POST /api/v1/messages/attaches/add`

Поля:

- `message_id` — ID создаваемого/редактируемого письма;
- `email` — добавляется базовым классом;
- `access_token` — добавляется слоем сеанса.

Тело — `multipart/form-data`.

Файл передаётся частью:

- имя части: `file`;
- имя файла: полное имя исходного файла.

Ответ:

`body.attach.id`

— серверный ID загруженного вложения.

### Просмотр вложения через AJ

`GET /api/v1/messages/attaches/view`

Параметры:

- `id`;
- `type=attach`;
- `email`;
- `access_token`.

Ответ не JSON: команда ожидает двоичный поток и возвращает его как файл/поток.

Это отдельный AJ-механизм от разрешённого проектом `af.attachmail.ru/cgi-bin/readmsg`.

### Скачивание письма как EML

`POST /api/v1/messages/message/download`

Поля:

- `id`;
- `folder_id`;
- `email`;
- `access_token`.

Ответ — двоичный EML. Клиент принимает типы содержимого:

- `message/rfc822`;
- `application/octet-stream`.

### Метаданные письма

`GET /api/v1/messages/meta`

Параметры:

- `uidl`;
- `email`;
- `access_token`.

Ответ:

- `body.meta[]`.

Текущий клиент возвращает этот массив вызывающему коду как JSON.

### Подтверждение прочтения

`GET /api/v1/messages/notify/read`

Параметр:

- `id` — ID сообщения.

Используется тип авторизации `TORNADO`; `access_token` добавляется сеансом.

Успешный ответ не требует полезной нагрузки.

### Умные ответы

`POST /api/v1/messages/replies/smart`

Поля:

- `id`;
- `email`;
- `access_token`.

Ответ:

```json
{
  "body": {
    "replies": ["...", "..."],
    "is_default": false
  }
}
```

Если сервер возвращает для `body.id` ошибку `not_exist`, клиент трактует это как отсутствие умных ответов, а не как фатальную ошибку.

### Отмена отправки

Текущий APK не подтверждает отдельный AJ-маршрут отмены уже отправленного письма.

`TornadoSendCommand` реализует пользовательскую отмену через локальную задержку:

1. команда подготавливает письмо и вложения;
2. перед `TornadoSendRequest` запускается `CommandDelayer`;
3. пока задержка не истекла, отправку можно отменить локально;
4. если задержка истекла, выполняется обычный `POST /api/v1/messages/send`.

После начала сетевой отправки `mIsSendCompleted` фиксирует завершение команды.

Строка `cancellation_token` присутствует в фильтре журналирования `TornadoSendRequest`, но текущий декомпилированный код не читает её из успешного ответа и не связывает с отдельной серверной командой отмены. Поэтому существование серверного AJ-маршрута отмены считать подтверждённым нельзя.

---

## 26. Остаток исследования после текущего прохода

На текущем этапе точные маршрут и базовый договор восстановлены почти для всего почтового слоя.

Остаются прежде всего:

1. полная структура успешного ответа `/messages/send` и `/messages/draft` — текущая команда их намеренно игнорирует;
2. все поля и ошибки `/messages/attaches/add` помимо подтверждённого `body.attach.id`;
3. подробная схема ответа `/messages/meta`;
4. дополнительные поля `/messages/replies/smart`, если сервер возвращает их помимо `replies` и `is_default`;
5. правило выбора старого и нового поиска и фактическое значение `search_new_host`;
6. редко используемые служебные команды, не необходимые для первого полного варианта Mail.ru Desktop.

Для функций удаления, перемещения, отметок, папок, спама, поиска, отправки, черновиков, отложенной отправки, вложений, адресной книги, фильтров, отписки, категорий, EML, метаданных, подтверждения прочтения и умных ответов уже известны маршруты и основные параметры текущего официального Android-клиента.
