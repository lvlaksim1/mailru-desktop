# Матрица готовности AJ-функций для Mail.ru Desktop

Источник: официальный Android-клиент `ru.mail.mailapp` 15.107.0.148045 и ранее подтверждённые живые AJ-запросы.

Обозначения:

- **A** — подтверждено и APK, и рабочим запросом к AJ; можно переносить в клиент.
- **B** — точный договор восстановлен из текущего APK; можно реализовывать, затем проверить одним рабочим запросом.
- **C** — маршрут известен, но для полноценной реализации ещё не хватает части ответа/редкого поведения.
- **X** — не соответствует правилу проекта «почтовые операции только через aj-https.mail.ru».

| Функция | Маршрут | Состояние | Что известно / что осталось |
|---|---|---:|---|
| Вход | `POST /cgi-bin/auth` | A | Login, Password, состояния CAPTCHA/доп. проверки; при CAPTCHA клиент прекращает вход |
| Умный список цепочек | `GET /api/v1/m/threads/status/smart` | A | маршрут, параметры и структура состояния восстановлены |
| Список сообщений | `GET /api/v1/messages/status` | B | полный набор основных параметров и ответ `messages/folders/last_modified` |
| Одна цепочка | `GET /api/v1/m/threads/thread` | B | id, folder, last_modified, limit, offset, snippet_limit, refresh_mailbox |
| Полное письмо | `GET /api/v1/messages/message` | A | минимальный живой вызов + полный набор параметров текущего APK |
| Список вложений письма | `GET /api/v1/messages/attaches` | B | id, attach_types, attaches.list |
| Прочитано/непрочитано | `POST /api/v1/messages/marks` | A | marks с set/unset |
| Флаг | `POST /api/v1/messages/marks` | B | `flagged` подтверждён текущим APK |
| Закрепление | `POST /api/v1/messages/marks` | B | `pinned` подтверждён текущим APK |
| Метки цепочки | `POST /api/v1/m/threads/marks` | B | ids объектов {id,folder,message_id_last}, unread/flagged/pinned |
| Перемещение письма | `POST /api/v1/messages/move` | A | ids + folder |
| Массовое перемещение | `POST /api/v1/messages/move/all` | B | folder_from, folder, from, only_newsletters, older_than |
| Удаление письма | `POST /api/v1/messages/remove` | B | ids |
| Массовое удаление | `POST /api/v1/messages/remove/all` | B | folder_from, only_newsletters, spam_folder, older_than |
| Перемещение цепочки | `POST /api/v1/m/threads/move` | B | ids объектов цепочек + folder |
| Удаление цепочки | `POST /api/v1/m/threads/remove` | B | ids объектов цепочек |
| В спам | `POST /api/v1/messages/services/spam` | B | ids, возможный folder |
| Не спам | `POST /api/v1/messages/services/unspam` | B | ids |
| Спам для цепочки | `POST /api/v1/m/threads/services/spam` | B | ids + verified |
| Не спам для цепочки | `POST /api/v1/m/threads/services/unspam` | B | ids |
| Список папок | `GET /api/v1/folders` | B | полная основная модель папки |
| Создать папку | `POST /api/v1/folders/add` | B | folders[], email; ответ body[0]=id |
| Переименовать/изменить папку | `POST /api/v1/folders/edit` | B | folders[] с id/name |
| Удалить папку | `POST /api/v1/folders/remove` | B | ids |
| Очистить папку/корзину | `POST /api/v1/folders/clear` | B | ids |
| Обеспечить архивную папку | `POST /api/v1/folders/archive/ensure` | B | folder; ответ ID |
| Открыть защищённую папку | `POST /api/v1/folders/open` | B | folders[] с id и folder_password |
| Закрыть защищённые папки | `POST /api/v1/folders/close` | B | ids |
| Старый поиск AJ | `GET /api/v1/messages/search` | B | полный основной набор фильтров и схема выдачи |
| Новый поиск | `GET /api/v1/go/search/emails` | X | фактический узел `go.mail.ru`, поэтому исключён |
| Подсказки поиска | `GET /api/v1/messages/search/suggest` | B | query |
| Отправить письмо | `POST /api/v1/messages/send` | A | точное тело из APK; успешный ответ клиентом почти игнорируется |
| Сохранить черновик | `POST /api/v1/messages/draft` | B | та же модель отправки |
| Отложенная отправка | `POST /api/v1/messages/schedule` | A | та же модель отправки + send_date/source.schedule |
| Загрузить вложение | `POST /api/v1/messages/attaches/add` | A | multipart, message_id, часть file; ответ body.attach.id |
| Удалить вложение | `POST /api/v1/messages/attaches/remove` | B | message_id + ids |
| Перепривязать вложения | `POST /api/v1/messages/attaches/reattach` | B | message_id + forwarded_id; okay_files/error_files |
| Просмотреть вложение через AJ | `GET /api/v1/messages/attaches/view` | B | id + type=attach; двоичный ответ |
| Скачать входящее вложение | `af.attachmail.ru/cgi-bin/readmsg` | A | отдельное разрешённое исключение проекта |
| Скачать письмо EML | `POST /api/v1/messages/message/download` | B | id + folder_id; message/rfc822 или octet-stream |
| Метаданные письма | `GET /api/v1/messages/meta` | B | uidl; body.meta[] |
| Подтверждение прочтения | `GET /api/v1/messages/notify/read` | B | id |
| Умные ответы | `POST /api/v1/messages/replies/smart` | B | id; replies[] + is_default |
| Отложить письмо | `POST /api/v1/messages/snoozes/update` | B | id + date в секундах |
| Убрать откладывание | `POST /api/v1/messages/snoozes/remove` | B | id |
| Отписаться от рассылки | `POST /api/v1/messages/services/unsubscribe` | B | ids |
| Изменить категорию | `POST /api/v1/messages/services/category/change` | B | ids, category, drop_category, add_filter |
| Список фильтров | `GET /api/v1/filters` | B | conditions + actions |
| Создать фильтр | `POST /api/v1/filters/add` | B | filters + apply_folders; exists/over_limit |
| Изменить фильтр | `POST /api/v1/filters/edit` | B | та же модель + id |
| Удалить фильтр | `POST /api/v1/filters/remove` | B | ids |
| Адресная книга | `GET /api/v1/ab/smart` | B | contacts + labels, полная основная модель контакта |
| Быстрый список адресатов | `GET /api/v1/ab/fast` | B | компактные пары имя/email |
| Последняя активность адресатов | `GET /api/v1/ab/lastseen` | C | маршрут найден; подробный договор ещё не нужен первому варианту клиента |
| Сохранить вложение в Облако | `POST /api/v1/attaches/add/to-cloud` | C | маршрут найден; относится к дополнительной функции |
| Суммаризация письма | `GET /api/v1/messages/message/summarize` | C | маршрут найден; не является базовой почтовой функцией |
| Серверная отмена уже отправленного письма | не найден | C | APK подтверждает локальную отмену до HTTP-send; отдельный AJ-маршрут не найден |

## Вывод для разработки

Для первого полноценного варианта Mail.ru Desktop уже восстановлен достаточный договор, чтобы реализовать без дальнейшего поиска маршрутов:

1. загрузку папок, сообщений и цепочек;
2. полное письмо и вложения;
3. прочитано/непрочитано, флаг и закрепление;
4. перемещение, архивирование через папку, корзину и окончательное удаление;
5. спам / не спам;
6. поиск через AJ;
7. отправку, ответ, пересылку, черновики и отложенную отправку;
8. загрузку/удаление/перепривязку вложений;
9. адресную книгу;
10. фильтры;
11. отписку от рассылки;
12. категории;
13. EML, метаданные, подтверждение прочтения и умные ответы.

Живые проверки для строк категории B нужны как проверка нашей реализации, а не как дальнейший поиск неизвестного API.
