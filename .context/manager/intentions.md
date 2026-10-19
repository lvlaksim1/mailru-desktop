# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — tolerant parser released and retained

The mailbox-list transport remains /api/v1/m/threads/status/smart with the mailbox OAuth token. Parser behavior must remain tolerant across all observed server shapes: base_message, messages[], direct thread-like objects, and threads[].representations[].

## I-003 — complete access-token API coverage
status: active — static discovery completed; staged product integration active

APK 15.107.0.148045 is statically mapped. Newly enabled operations still require live or Owner-runtime validation before they are treated as stable.

## I-004 — send/scheduled-send flow
status: active

Immediate send, reply with attachments, server draft saving, signatures and named message templates are implemented. Exact delayed-send send_date semantics still require dedicated validation.

## I-005 — installer/update release channel
status: completed for v0.3.18 delivery

GitHub Release v0.3.18 is published with both full and update installers. Release workflow 37707435464 succeeded. Future versions continue through this established installer pipeline.

## I-006 — full-message read and incoming attachments
status: released

The preview pane is the normal full-message workspace. Full body, selectable addresses/subject, reply, forward, move/archive/trash, attachments and reply attachments remain supported.

## I-007 — mailbox OAuth authorization
status: released and Owner-runtime confirmed for interactive verification

Authorization obtains the mailbox OAuth access_token. Interactive verification is handled in a persistent browser profile isolated per mailbox account. Owner runtime on 2026-10-08 confirmed a previously problematic account completed a phone-confirmation step and authorized without password recovery. Do not revert to a disposable profile or mix these profiles with normal desktop browsers. No challenge solver or verification bypass is allowed. Stored refresh_token remains the normal expired-token recovery mechanism.

## I-008 — incoming message actions
status: active — core per-message actions released

Read/unread, move/archive/trash, permanent removal from Trash, flagged and pinned marks remain released. Opening a message marks it read automatically; the selected row can be marked unread again. Bulk/thread, spam/unspam and category operations remain later work.

## I-009 — desktop UX/settings
status: active — v0.3.18 released; Owner runtime validation pending

The v0.3.18 merged baseline includes:
- saving/restoring window normal/maximized state and panel/column widths;
- preserving the divider between message list and message preview;
- darker scrollbar thumb/arrows in dark theme;
- compact icons to the left of sender names and account addresses without increasing row height;
- persistent account ordering;
- account sections/headings with the expand/collapse arrow on the right of the section name;
- account dragging after 250–300 ms hold (implemented at 275 ms): the row detaches while the mouse remains held, follows the pointer, neighboring accounts animate out of the way, and mouse release fixes the new position; Escape has no role;
- preview toolbar relocation requested by Owner;
- reply/forward form shown above the message body;
- Settings sections for named signatures and named message templates;
- templates carry subject, body and attachments;
- reply form selectors insert a signature or populate from a template.

## I-010 — high-value access-token expansion
status: active — secondary to v0.3.18 Owner runtime validation

After v0.3.18 is published and validated, resume thread/bulk actions, spam/unspam, subscription/category actions, attachment lifecycle, EML/metadata/read receipt and search improvements. go.mail.ru new search remains deferred while its live HTTP 520 behavior is unresolved.

## I-011 — multi-account correctness
status: active

Each account must independently preserve folders, messages, preview content, unread counts, authorization browser profile, ordering and section membership. Rapid account switching must not leak or mix state.


## I-012 — real avatars and manually managed Markdown templates
status: active — released; real-user behavior not fully validated

The Owner requires actual available contact portraits for message senders and accounts, using initials only when no usable photo exists. The filin.mail.ru/pic?email URL was located in official APK strings and wired to both views with a 16 px footprint. Verify retrieval of real avatars and fallback on the Owner's actual Windows machine.

Each named message template is represented by a top-level Markdown file with the exact template name as its filename, stored in %LOCALAPPDATA%/MailRuDesktop/Templates. Manual creation/edit/rename/delete must update the in-app list, not depend on settings.json. An optional header defines subject/attachment paths, and files selected inside the app are copied to the template attachments directory. Insertion must never ask for confirmation. Code, CI, startup and release are complete, but Owner interaction testing remains outstanding.


## I-013 — account drag correctness and outgoing compose controls
status: active — v0.3.19 released; Owner runtime confirmation pending

The Owner reported broken downward account drag and occasional selection of the row underneath a long-pressed account. v0.3.19 computes target from original row centers without animation feedback, resets completed transforms and confines short clicks to the original pressed account. Offline index regression checks passed.

The Owner requested a read-receipt checkbox for new outgoing messages, schedule-send checkbox with local tomorrow 09:00 as default, and display of sender email when sender name is missing. v0.3.19 implements these in UI, payload and tolerant parser, and verifies the outgoing form through a fake HTTP handler. Test of real receipt response and actual delayed delivery was not performed; do not mark them as runtime-verified.

## I-014 — v0.3.21 Owner feedback on inline composer, templates and dark controls
status: released — Owner interactive validation pending

The Owner requested: selected template/signature should work again on repeated choice; configurable folder for Markdown templates with manual file discovery; inline reply/forward close × and receipt/delayed delivery controls; darker thumb/arrows everywhere; keep modal dialog caption dark even during input; sender email when name is missing. Owner confirmed account drag and dividers are now good.

The release implements these via PR #70 and CI 37711634109 with offline file and parsing checks; installer release workflow 37711732617 succeeded. ChangeDirectory copies existing templates and attachments into the selected folder without overwriting existing files, persists the setting, rebinds watcher, and preserves old folder. Reply/forward schedule defaults to tomorrow 09:00 and sends through existing Mail.ru scheduling protocol. Receipt request is optional. Sender fallback draws from parent thread and selected full message.

Next: real-user visual and behavior check, including WPF scrollbars, prompts, list sender, repeated choices, and controlled mail receipt/delivery tests. Do not infer successful recipient acknowledgment from sending a receipt request.

## I-015 — enforce the immutable palette element-to-role contract
status: active — code released v0.3.21; user visual gate pending

The Owner requires that every theme have a complete named/presented default set of color roles and that users may edit colors but cannot reassign the fixed collection of UI elements governed by each role. The Product Manager selected 26 roles. Maintain the canonical ThemePalette.Roles registry, separate persisted dark/light dictionaries and system-theme switching. All new widgets must use a corresponding named dynamic resource instead of inventing literal element colors.

v0.3.21 implements grouped color settings, swatches/descriptions, RGB/hex input, individual and whole-theme reset and live propagation for the active theme, with nonblocking accessibility contrast alerts. The release passed automated checks; Owner in-app/visual testing remains open. Preserve account dragging and pane splitter behavior.

## I-016 — close the 12-point mail feature backlog safely
status: active — v0.3.22 delivered; send-now (#10–11) remains blocked on verified server semantics

A full v0.3.22 release has shipped the palette-collapse/new color-picker enhancements and the tested code for sender enrichment, legible Markdown, selectable attachment download directory, automatic Explorer opening, top-of-letter attachments, formatted HTML editor in all compose views, multi-select Ctrl/Shift/checkbox, bulk move/archive/read, and permanent deletion in Trash. Offline/CI testing succeeded; Owner GUI and real HTML reception confirmation pending.

The open, highest-risk remaining subtask is issue #77: safe immediate sending of a message already scheduled to send later. The application currently shows disabled "Отправить сейчас" controls when in Outbox; enabling a method that creates an additional outgoing mail instead of reusing/cancelling the existing schedule is unacceptable. Research with controlled test messages, original scheduled IDs, protocol responses and post-send state verification must precede implementation. The complete 12-point backlog remains open until this condition is met and Owner signs off.

## I-017 — verify independent auto-refresh for saved Mail.ru accounts
status: released v0.3.23, Owner runtime confirmation pending

Prevent "Авторизация активна" when the Mail.ru API rejects a saved token with HTTP 200 and embedded JSON status 403. Recognize the rejection, try account-specific refresh if available, validate fresh token against the folder endpoint, then save only successful replacement. Never delete other accounts, alter their credentials, overwrite LastLogin during inactive account checks, or log secrets. If refresh unavailable or rejected, show clearly which account requires new login. The v0.3.23 implementation and offline Windows checks are complete, but live Owner account validation remains to be done.

Issue #77 for previously scheduled outgoing mail remains separate and unresolved; do not claim all 12 backlog features complete.

## I-018 — verify GUI regression fixes and finish scheduled Outbox action
status: released v0.3.24; visual validation and issue #77 pending

v0.3.24 addresses Owner WPF palette footer clipping, dark date picker, lost selection on MailRuMessageSummary replacement, bulk folder reloading, abnormal paragraph spacing, inline/detached reply resize and permanent Trash deletion confirmation. Initial sender parser examines corresponding-message representations before separate full-message queries. Owner visual confirmation remains necessary; do not impose long sequential waits when summary metadata is absent.

Issue #77 remains open. Disabled Outbox "Отправить сейчас" cannot be enabled before the server's original scheduled-mail transition is confirmed duplicate-free. Prior account token 403 issue is closed based on Owner explicit success report.

## I-019 — verify systematized WPF UI, mailbox target semantics and finish scheduled send-now
status: v0.3.25 built and published; Owner runtime acceptance and scheduled send-now outstanding

Central invariant is Owner-specified checked-first target resolution, active preview as fallback and user notification when neither exists; selection checkboxes and active preview are independent, Shift range works and no Ctrl-dependent selection. The UI registry must include windows and every documented control/style with geometry, states, 26 immutable semantic color roles and nine relative font-size roles; new UI edits must regenerate the schema 2 registry and pass CI. Live unread counters must be revised only using confirmed user actions or explicit server field values, not missing-as-zero placeholders.

v0.3.25 passed build/startup/logic and published with installer; Owner GUI still needed, especially row click/Shift, independent preview, dynamic counters, font changes, and navigation palette. First-paint missing sender support is partial. The original send-now for existing scheduled messages remains NOT IMPLEMENTED, issue #77, until atomic scheduling semantics are proven under a controlled test account without duplicate delivery.


## I-020 — finish Owner acceptance of v0.3.26
status: active — code published and CI passed; new interactions not yet Owner-runtime confirmed

The nine Owner-authorized v0.3.26 changes are released; verify formatting-first reader, collapsed-only moving sections as account blocks, detached New Mail and contact picker, compact auto-applied folder selectors, collapsed signature/template editors and pressed-color role. PR #91 subsequently repaired runtime-window inventory to nine windows without changing the installer. Preserve previously Owner-confirmed features and avoid claiming visual/mouse validation from CI. Verify sender identity when changing active account while composing in the separate window. Keep scheduled Outbox send-now #77 blocked until a duplicate-free server transition is proven.


## I-021 — verify resilient in-app updater in Owner network
status: released v0.3.27; Owner real-network confirmation pending

After Owner observed v0.3.25 generic update-check failure, PR #92 created a GitHub.com redirect fallback independent of api.github.com plus error diagnostics and a manual release-page button, verified by injected HTTP fakes and Windows CI. Release v0.3.27 is published. The Owner must manually run the v0.3.27 Update installer if the old checker cannot reach the API; verify subsequent in-app update checking under actual network conditions. If both hosts are still inaccessible, request ONLY sanitized diagnostics.log line with category github_update_check and/or codes, not mailbox secrets or full diagnostics. Leave auto-install on startup outside current scope; do not imply this feature exists. No original-user-machine root cause is yet confirmed.


## I-022 — v0.3.28 Owner display, composition and palette acceptance
status: released v0.3.28; Owner runtime verification pending

Owner directly closed prior sender-photo retrieval, section drag, updater v0.3.27, Contacts menu removal, contact chooser and collapsed template/signature UI. Do not spend research time on sender portraits unless new evidence. New v0.3.28 scales existing photographs with font size, pins the sender and token of a detached compose window, and implements the remaining UI changes. Source implementation PR #94, CI 37847574160 and 37847753416, release 37847893518 passed. Owner must verify new-mail sender identity when switching accounts, full-cycle sending/draft/template insertion, no old-letter flicker, images from private afNN.mail.ru links, dark native captions on their Windows, compact notifications and merged palette color migration. No automatic release validation can prove logged-in email image retrieval. Send Now issue #77 remains independent and blocked.

## I-021 — updater v0.3.27 real-network confirmation
status: Owner confirmed working 2026-10-09; CLOSED for version detection


## I-023 — проверить у владельца фактическое исправление 22 замечаний в v0.3.29
status: реализованы дополнительные исправления, испытания Windows прошли; проверка на реальной почтовой сессии ожидается

По обратной связи владельца перечень 22 пунктов нельзя закрыть одной успешной сборкой. PR #96 исправил обнаруженные проблемы и ввёл испытания интерфейса с открытием настоящих окон Windows (не только запуск процесса). Проверены: расположение настройки цветов внутри «Внешнего вида», соседство «Подписей» и «Шаблонов», открытие нового письма, закреплённое поле «От», выбор подписи/шаблона, обычный фон кнопки отправки, размер короткого уведомления, перерисовка яркостной шкалы. Пользователю ещё нужно подтвердить отсутствие мерцания HTML-писем, получение вложенных изображений из его учётной записи, цвет системных заголовков именно в его версии Windows, фактический переход к выбранной папке и импорт шаблонов в его хранилище. Незавершённую отправку ранее отложенного письма (#77) НЕ включать без доказанного отсутствия дублирования.


## I-024 — приёмка v0.3.30: показ письма, встроенные изображения, шаблоны
status: выпущено v0.3.30; проверка владельцем на действующей почтовой сессии ожидается

Владелец подтверждал остальные функции v0.3.29, но повторно открыл мерцание, поломанные встроенные картинки, задержки писем и неверные загрузку/сохранение .md. PR #98 устранил последовательное ожидание изображений и удаление исходного шаблона при Save As; поставил отдельную авторизованную загрузку afNN через запросы ресурсов WebView2 и отмену устаревших запросов писем, добавил диагностические замеры, начальную папку шаблонов и подтверждение перезаписи. Windows CI и выпуск v0.3.30 прошли. Проверить у владельца переходы между письмами, чеком Магнита, точные правила нового имени и совпадающего имени, работу вложений шаблонов и замеры скорости, если задержка сохранится. Не возобновлять исследование корректно загружаемых фотографий отправителей.

## I-025 — Issue #77: безопасная немедленная отправка отложенных писем
status: ЗАБЛОКИРОВАНО, не реализовано

Доказаны лишь запросы создания новой немедленной отправки и новой отложенной отправки. Не подтверждена атомарная отмена/перевод существующего задания в отправку. Составлен план испытаний в docs/protocol/scheduled-send-now-safety.md: оригинальный ID и state, команда официального клиента, отмена расписания, единственная доставка, повторные вызовы и сетевой тайм-аут. Кнопки отключены, не разрешать операцию из обычного send API. Использовать тестовый ящик только после разрешённого исследования.


## I-026 — verify v0.3.31 single-paint email preview
status: закрыто: последующая v0.3.32 принята владельцем 2026-10-09 по задачам 1–3

Owner provided reproducible K-001.mp4 demonstrating v0.3.30 HTML-first and late-image layout changes. PR #100 merged an atomic visibility guard with bounded 3s image settling/Stop deadline and navigation-ID filtering. Compare the actual same letter when selecting and reselecting quickly: neither raw text, old letter nor late large images may rearrange a visible receipt. Verify completeness of embedded images: on time-out they may be deliberately abandoned, requiring follow-up optimization if this prevents full receipt viewing. Measure reader_visual_ready reason=complete/limited and elapsed-ms, without exposing email contents, cookies, full HTML or token. Do NOT mark problem closed merely because CI passes. Keep scheduled Send Now #77 blocked until duplicate-free source operation proven.


## I-027 — повторная визуальная проверка v0.3.32 без второго WebView2
status: закрыто: владелец сообщил об устранении задач 1–3 2026-10-09

Цель: устранить мерцание старого письма, пустой WPF кадр и несогласованность заголовка и содержимого. Код переводит предварительную загрузку внутрь единственной постоянной страницы WebView2 и меняет HTML одним действием браузера. Запрос выбранного письма проверяется по поколению; все поздние ответы отклоняются. В WindowsUiSmoke реальный WebView2 открыл две синтетические HTML-страницы подряд без корневой навигации, с одной активной внутренней областью и без Visibility переключений. Владелец должен повторить именно серию открытий Магнит ↔ техподдержка/СДЭК, оценить остатки предыдущих кадров, скорость, появление изображений, положение прокрутки и заголовки. Если возникнут сбои, проверять новое управление содержимым, но не возвращать показ по DOMContentLoaded, WPF Overlay, Stop и повторный NavigateToString. Issue #77 не относится к этому выпуску и остаётся заблокированной.


## I-028 — изучить уведомления о новых письмах в исходном APK
status: active — принято по прямому поручению владельца 2026-10-09

Исследовать оригинальный Android APK Mail.ru 15.107.0.148045: серверную регистрацию, транспорт событий, показ уведомлений, восстановление после паузы, отличие от опроса папки. Разделять установленные по сохранённым исходникам факты от гипотез; документировать выводы и возможное применение для Windows. Пока нет поручения включать эту функцию в выпуск.


## I-029 — восстановить исходный GCM/FCM транспорт и доказать Windows-доставку
status: active — статические источники и внешний получатель исследованы, совместимость с Mail.ru не доказана

Владелец подтвердил работу оригинального APK без RuStore/VK. Не предполагать, что его телефон не содержит Google Play Services/Huawei HMS. В APK есть GCM/HMS/VKPNS фабрики и события нового письма. Запущен целевой проект исследовательского извлечения GCM transport через GitHub Actions 37872182332 (ветка research/mail-apk-15.107.0.148045), не считать результат успешным до окончания. Изучен Superhuman/push-receiver, актуальная версия 2.1.7 от июля 2026 (обновления в 2026), реально используемые новые FCM регистрации и mtalk.google.com TLS. Следующее техническое препятствие — выпуск независимого действительного токена подходящего проекта и серверная привязка к Mail.ru с уже сохранённым OAuth; не подменять частым опросом. Продукт без изменений; тестовое письмо только с разрешением владельца.


## I-030 — испытать прямую Windows FCM доставку Mail.ru
status: active — Android Google FCM и почтовая подписка восстановлены статически, живой Windows серверный путь не доказан

Проверены SetUpPushComponent, GCMAvailabilityChecker, GcmPushKitWrapper, GcmPushTransport, FirebaseMessagingService, PushMeSdk и регистрация аккаунтов с ru.mail.oauth2.access. Статическое исследование 37872182332 PASS. Следующий рубеж: отдельная действительная FCM-регистрация независимого Windows-клиента, затем серверная привязка тестового аккаунта Mail.ru и фактическая доставка нового письма; сохранение токенов отдельно и отсутствие дублей. Не смешивать Google Play Services на телефоне с самостоятельным SDK APK. Не менять рабочую программу до подтверждения способа.


## I-031 — Независимый Windows-получатель как официальный мобильный клиент
status: research, без изменения продукта

Сначала выяснить расхождение двух sender ID из оригинального APK и реализацию FirebaseInfoProvider. Исследовательское задание GitHub Actions 37874791066 запущено; пока нет подтверждённого результата. Далее только новый собственный токен Windows и испытательная PushMe-подписка существующего приложения 'mail', без регистрации новой идентичности Mail.ru и без копирования токена телефона. Существующие мобильные OAuth-токены MailRuDesktop применимы для проверки, но принятие их PushMe ещё не доказано.


## I-032 — завершить исходный протокол подписки и испытать серверную доставку
status: active; независимая регистрация и MCS вход Google PASS; Mail.ru PushMe/E2E pending

В контролируемом испытании создан отдельный получатель Google с оригинальным sender=1098335887158: register3 TOKEN_ISSUED, MCS LoginResponse LOGIN_OK (Actions 37875870944 и 37876061718), без личного почтового аккаунта. Следующая задача — восстановить точную сериализацию PushMe, в частности параметры клиента и capabilities из APK; Actions 37876287988 запущено и не подтверждено. Затем только на локальном компьютере владельца с его сохранённым мобильным OAuth подтвердить подписку Mail.ru и действительное событие нового письма, не раскрывая токены в общедоступном GitHub. Ни выпуск, ни дефолтное включение событийной доставки до полного E2E.


## I-033 — готовность точного локального испытания PushMe и реального event=4
status: active; локальный стенд построен и проверен на искусственных ответах, серверная доставка не испытана

Необходимо получить из APK полный профиль capabilities/client/device для PushMe; результат исходного задания 37877449399 не проверен и не должен угадываться. После корректировки программы и контрольной Windows сборки испытать на разрешённом локальном тестовом Mail.ru аккаунте с существующим DPAPI-хранилищем, не размещая OAuth в GitHub. Строго различать TOKEN_ISSUED, MCS LOGIN_OK, аккаунт подтверждён ACCOUNT_ACCEPTED и реально доставленное событие MAILRU_NEW_MAIL_EVENT_RECEIVED=YES. Отдельный прототип не переносить в продукт и не выпускать релиз до доказательства E2E.


## I-034 — проверить на собственном тестовом аккаунте реальную серверную подписку и event=4
status: active, подготовительный Windows инструмент с точными capabilities готов; реальное исполнение требует Windows профиля владельца

Изолированная исследовательская Windows программа теперь формирует capabilities.can_mail.Filter из оригинального APK; исходник и проверки Actions 37878519042, 37878867912 SUCCESS. Новый портативный EXE Actions 37878867920 PASS, односуточный артефакт на странице задания; исходники tools/push_research/ на research branch. Для перехода к следующей стадии понадобится запустить Start_Push_Trial.cmd под тем же Windows-пользователем, у которого есть авторизованный MailRuDesktop и выбранный тестовый почтовый аккаунт; никакие пароли или OAuth в чат/GitHub не передавать. Критерии: Google TOKEN_ISSUED, MCS LOGIN_OK, PushMe ACCOUNT_ACCEPTED, MAILRU_NEW_MAIL_EVENT_RECEIVED=YES, подтверждение удаления временного токена. Только затем интеграция в основной продукт, отдельные уведомления для всех аккаунтов и повторное подключение.


## I-035 — Выпустить v0.3.33 со встроенным отключённым по умолчанию испытанием уведомлений
status: интегрировано в main, требуется CI + выпуск и локальный опыт пользователя

Больше не отправлять владельца к отдельному одноразовому экспериментальному exe: главное окно MailRuDesktop теперь имеет настройки и нативное окно тестирования PushMe/Google. Подтвердить Windows .NET сборку, тесты кодирования и проверки выбора аккаунта, UI реестр, режим выключен до явной галочки; затем подготовить обычный полный установщик и обновление v0.3.33 по установленной схеме последнего GitHub релиза. Перед пользователем честно различать проверенную сборку и недоказанное живое событие event4. После первого теста внутри программы обработать обезличенные статусы, а не запрашивать пароли или OAuth.


## I-036 — собрать результаты первого локального испытания встроенного уведомителя
status: v0.3.33 full/update RELEASED; пользовательское серверное испытание pending

Пользователю переданы прямые ссылки на оба установщика https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.33. Следом получить обезличенные технические состояния из окна Настройки → Уведомления о новых письмах: Google TOKEN_ISSUED, LOGIN_OK, PushMe ACCOUNT_ACCEPTED либо отклонение, истинный event4, успешность удаления собственного временного токена. Не просить пароли/скопированные OAuth и не предлагать прежний отдельный архив. При провале расследовать точную причину; при реальном event4 планировать устойчивое подключение всех ящиков, переподключение и фильтрацию повторов.


## I-037 — verify v0.3.34 PushMe Prod subscription on the authorized user mailbox
status: code, CI and installers delivered; live ACCOUNT_ACCEPTED / event4 pending

Update v0.3.34 link https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.34/MailRuDesktop_Update_v0.3.34.exe. User repeats in-app trial under already saved account after update, sends control mail ONLY when ACCOUNT_ACCEPTED, reports anonymized technical states and cleanup outcome. Distinguish valid TLS, HTTP200, validated account and actual event4; successful network POST without account validation is insufficient. No return to separate Python tool; no raw mailbox tokens sent to GitHub/chat.

## I-038 — принять общий Google-получатель v0.3.36 на нескольких реальных аккаунтах
status: active; реализация, автономные испытания и установщики выполнены, проверка живой многопочтовой доставки открыта

Владелец согласовал один постоянный Google-токен на все почтовые аккаунты; опубликована v0.3.36. Не выпускать фиктивный отчёт о полной надёжности. Требуется опыт владельца с минимум двумя уже авторизованными ящиками: принять event=4 для каждого, проверить независимое обновление значков/списков, повторные события, завершение и запуск программы, разрыв MCS, добавление и удаление аккаунта без выключения оставшихся; телефонные уведомления не трогать. Не запрашивать OAuth/Google токены или текст сообщений. При расхождении — исправить исходники и подтвердить CI перед новым релизом.

## I-039 — строго воспроизвести оригинальный протокол Google/PushMe по исходному коду
status: active; первый подтверждённый дефект исправлен в v0.3.37, остаточные пробелы исследования открыты

Владелец прямо запретил подбор сетевых полей/запросов экспериментами. Для каждого изменения должны быть исходный метод из оригинального APK или соответствующего независимого системного компонента Google, его поля и условия. Не смешивать старый и новый PushMe протоколы. Осталось восстановить из DEX (без почтовых запросов) неполные корутинные методы `SubscriptionUseCase.invoke`, `UnsubscribeUseCase.invoke`, `PushMeApiImpl.setSettingsInternal/unsubscribeByDeviceId`, точную адресную отписку аккаунта и правила повторной синхронизации. Затем заменить временную схему полной смены Google-токена при удалении ящика на подтверждённое исходниками адресное снятие. Механизмы Google Play services, отсутствующие в Mail.ru APK, идентифицировать отдельно, не выдавая авторский Windows код за оригинал. Важно: v0.3.37 — корректировка установленного несовпадения, не доказательство 32 аккаунтов.

## I-040 — принять v0.3.38 с адресным PushMe отписыванием
status: code+offline CI+Windows installers complete, real runtime pending

При удалении одного аккаунта не перезапускать общий Google MCS и не отзывать общий токен. Точный запрос из оригинального APK DEX /api/v1/unsubscribe_by_device_id с account/device_id/application реализован и автономно проверен. Потери соединения не должны приводить к повторной подписке удалённого аккаунта; неудачная отписка остаётся в DPAPI для повторной попытки, а остальных не блокирует. Следующая приёмка — пользовательское испытание нескольких настоящих ящиков: состояние подписок, удаление одного, уведомления остальных, неизменность общего Google-получателя, переподключение. Никаких новых экспериментальных запросов: сетевые протоколы реализовывать только после исходного доказательства APK/системных Google компонентов. v0.3.38 release successful; не путать успешную сборку с реальным E2E.

## I-041 — use v0.3.39 diagnostics to identify recurrent Google/PushMe failure
status: diagnostics implemented and released, user TXT evidence pending
Owner reports recurring Google LOGIN_OK→disconnect→0/32. First obtain TXT from installed v0.3.39 Settings → «Диагностика Google и PushMe», after at least two failed reconnects. Identify exact phase and error category or server code without passwords, token or account data. Then compare failing protocol step strictly against original APK source and, where appropriate, Google Play services implementation. No request guessing or active network probing, as directed by owner. v0.3.39 is diagnostic only; do not claim reconnect problem fixed.

## I-042 — inspect safe server error.message for PushMe code 499
status: v0.3.40 available, user TXT awaited
Capture one normal failed PushMe subscription response's numeric code and strictly redacted SERVER_REASON from v0.3.40's local diagnostic report; never ask for raw OAuth, Google token, account address, or raw PushMe body. Then correlate safe message with original APK fields/API implementation. Do NOT use experimental network requests or randomly split 32 mail account subscriptions. If SERVER_REASON_UNCLASSIFIED, acknowledge limits; further source study needed. Distinguish application rejection from Google MCS connectivity. Avoid claiming 499 is client timeout HTTP499 or 20-item hard limit.

## I-043 — проверить принятие исходного формата CommonId сервером
status: v0.3.41 source-derived correction released; live acceptance pending
Владелец устанавливает v0.3.41 и наблюдает штатный запрос регистрации 32 аккаунтов (без ручного сетевого подбора). Сравнить новый SERVER_API_CODE и SERVER_REASON с прежним 499/INVALID_FORMAT. Если code=0 — проверить число принятых аккаунтов и доставку событий; если 499 остаётся — сопоставить остальные свойства вложенного settings с APK, не гадать и не внедрять произвольные размеры пакетов. Исходный CommonId у Android основан на AndroidID:MD5(Build.*); в Windows виртуальный Android-профиль, не выдавать за настоящий телефон.

## I-044 — Verify fixed MCS classifier on normal v0.3.42 runtime
status: code/offline Windows CI+installers complete; live user trace pending
In v0.3.42, inspect MCS LOGIN_ID_PRESENT, LOGIN_ERROR_PRESENT and optional LOGIN_ERROR_CODE from one ordinary attempt, plus phase MCS_LOGIN_OK or MCS_SERVER_LOGIN_ERROR/MCS_LOGIN_INVALID_RESPONSE. Upstream Chromium accepts ErrorInfo.code=0 even if error field present, but prior v0.3.41 log did not capture code, thus not proven original cause. If MCS_LOGIN_OK, continue registration: SERVER_API_CODE / SERVER_REASON / PUSHME_ACCEPTED; earlier PushMe 499 INVALID_FORMAT remains open until new evidence. Keep reverse engineering source-first, no experimental connections or guessed field changes. Read-only diagnostic records safe codes, no token/body.

## I-045 — user-selected one-mailbox push regression acceptance
status: v0.3.43 full/update published, user test awaited

Diagnostic release purposely disables automatic shared push for all 32 logins and leaves previous shared Google registration untouched. User manually opens Settings → Notifications → Select account for testing, checks consent, chooses exactly one of saved accounts, starts old v0.3.35 three-minute single-mailbox test. On event show new-mail balloon via tray if Windows notification setting enabled. Only temporary Google token is unsubscribed at end. User to report ACCOUNT_ACCEPTED and MAILRU_NEW_MAIL_EVENT_RECEIVED=YES and Windows popup. Do not start shared account background and do not claim success until real single-mailbox test. Keep original APK source-based multiaccount investigation separate.

## I-046 — accept nineteen selected mailboxes trial
Use v0.3.44 to register exactly 19 user-selected mailboxes via the group receiver and record sanitized SERVER_API_CODE, SERVER_REASON, PUSHME_ACCEPTED and new-mail event. Compare 19 against previous 32 PushMe 499 INVALID_FORMAT without changes to group JSON. Single selected mailbox three-minute test from v0.3.43 remains intact. No automatic retries and no speculative protocol requests. Distinguish web 20-account limit from unverified PushMe limits.

## I-047 — сохранение контрольной рабочей группы 19
status: успешно проверена реальная регистрация и одно событие нового письма; ничего автоматически не расширять
Поддерживать v0.3.44 как проверенный одноканальный рабочий сценарий для 19 выбранных аккаунтов; исходная проверка одного аккаунта также работает. Следующая задача только по согласованию с владельцем: выяснить документально/контролируемой ручной проверкой точную границу количества аккаунтов и метод расширения обслуживания остальных, не изменяя рабочие 19, не запуская автоматические серийные запросы, не отзывая общий Google-токен.

## I-048 — реальная проверка постоянных групп и Google MCS, без потери успешных 19
status: v0.3.45 выпущена, ждём данные пользователя
Владелец обновляет программу, открывает «Google и группы PushMe»: убеждается, что ранее сохранённый Google-получатель загружен без Checkin/register3, группа 19 импортирована при наличии предыдущего подтверждённого roster. Подключает MCS и проверяет реальное письмо/Windows уведомление. Нажимает «Остановить приём» и проверяет, что не происходит адресного/токенного unsubscribe, а группа/токены сохраняются; повторное подключение не делает POST set_settings. После этого пользователь сам создаёт следующую небольшую группу свободных аккаунтов и проверяет доставку в старой и новой группах отдельно. Не считать отсутствие серверных групп в API подтверждением их сохранности: группа — локальный реестр. Не запускать экспериментальные проверки без согласования.

## I-049 — требования следующей версии: информативная диагностика и бесшовное обновление Windows
status: зафиксированы владельцем 10.10.2026; реализация НЕ начата
1. Журнал операций Google/PushMe по каждому ящику должен связывать START/OK/FAIL, адрес, номер группы, индекс операции и результат/код, чтобы можно было установить какие аккаунты зарегистрированы, сняты и на чём прервалась операция. В локальном журнале показывать полный адрес; при копировании/экспорте давать ЯВНЫЙ выбор: полный или обезличенный отчёт. Никаких токенов, паролей, ответов целиком, содержимого писем в любом варианте. Сохранить стабильную связь событий по одной операции, включая поздние ответы и прерывания.
2. Обновление из приложения по кнопке «Обновить»: приложение корректно закрывается, без диалогов мастера установки показывается только окно хода обновления с полосой прогресса, после завершения автоматически открывается обновлённая версия. Сохранять сессию, настройки, защищённые Google токены и реестр групп. Windows UAC при требуемом повышении прав — системный запрос и вне полного контроля установщика; не обещать его обход.
3. При обновлении НЕ удалять/пересоздавать существующий ярлык рабочего стола и не менять его имя/положение/значок. Изменять цель ярлыка на месте, только если требуется, создавать ярлык лишь при первоначальной установке или если его реально нет. Проверять как Public Desktop так и пользовательский Desktop, избегать дубликатов и сдвигов расположения значка Explorer.
Не менять текущую v0.3.45 без новой команды реализации; при разработке проверить реальный сценарий in-app updater на Windows.

## I-050 — пользовательская приёмка v0.3.46 перед чисткой старых групп
Владелец намерен самостоятельно удалить старую группу №1 (13) и группу №2 (7), затем заново зарегистрировать одну более крупную пачку для поиска максимального размера, но только при наличии информативного журнала. v0.3.46 исправляет логи и тихий апдейтер; не выполняет автоматическую очистку групп. После установки проверить полный локальный журнал и возможность выбрать обезличенную копию, затем самостоятельно нажимать «Удалить группу» поочерёдно и по результатам START/HTTP/OK/UNCONFIRMED выявить адреса. Отписка первой серверно замещённой группы может быть отклонена/не подтверждена; записи должны оставаться для разрешения неопределённости. Не менять существующий Google получатель, не запускать экспериментальные пакетные запросы без владельца.

## I-051 — пользовательская приёмка v0.3.47 (после релиза)
Владелец ставит v0.3.47 поверх 0.3.46; существующая Google-регистрация сохраняется. Далее самостоятельно создаёт дополнительного Google-получателя, формирует новую группу не более 30 свободных почтовых аккаунтов. Проверяет в интерфейсе занятую и свободную регистрацию, подключение MCS каждого получателя, адресный приход писем и независимость доставки по первой и второй группам, остановку только выбранной группы и восстановление после перезапуска. Не запускать массовые сетевые пробы самостоятельно; серверная приёмка остаётся неподтверждённой до реальных логов владельца. Проверить окно долгой отписки. Сценарий единой полосы прогресса может быть проверен только при обновлении ИЗ v0.3.47 к последующему выпуску, не при переходе ИЗ 0.3.46.

## I-052 — приёмка v0.3.48 на компьютере владельца
Установить Update_v0.3.48 поверх предыдущей программы; проверить сохранение почтовых аккаунтов/Google/PushMe групп, обычный приём event=4. При новом письме проверить информативный Windows-баннер без дублирующего заголовка, обязательно с аккаунтом получателя, sender/subject/snippet/uts и flags, когда поля пришли. Нажать при показанном/свёрнутом приложении и после его закрытия, проверить открытие точно данного письма. После перемещения в другую папку повторно проверить переход по тому же id; при недоступности не выбирать похожее письмо. Проверить новое сворачиваемое окно диагностики справа и кнопки/вкладки внутри. Проверить «Ответить» в предосмотре и отдельном окне, сохранённый ReplyToId, защиту существующего черновика. Другие события PushMe не включать. Проверить новый обновлятор, встроенный начиная с 0.3.47; следующее обновление уже должно использовать единую полосу прогресса.

## I-052 — приёмка диалогов и редактора v0.3.49
Пользователь устанавливает обновление и проверяет отдельное окно ответа: заголовок «Ответ: <тема>» без «Новое письмо», поля адресата/Re:/цитата и ReplyToId; при подтверждённой отправке окно закрывается, при отказе сервером остаётся. Проверяет на реальном диалоге из пяти сообщений число в существующем столбце, свернутые старые письма, их независимые даты/отправителей/полные тексты/вложения, открытие из списка и двойным нажатием в отдельном окне; кликом на уведомление старого письма активна именно соответствующая запись. Сверить реальные поля smart-thread JSON при недостатке member IDs; не додумывать историю. За исключением выбранного письма старые письма читаются с mark_read=false; максимум 18 исторических полных тел за открытие, остальное — честный предварительный текст. Не инициировать сетевые пробы без действий владельца.

## I-054 — принять v0.3.50 и расследовать настоящие диалоги
Пользователь устанавливает Update 0.3.50, проверяет уведомления с повторным полным выходом/запуском, отдельные лог-коды SELECTIVE_ACK_WRITTEN и REPLAY_DISK_SUPPRESSED, панель 660x440. Затем проверяет Mail.ru диалоги 5/2 на тех же аккаунтах. Если отсутствуют — нужен только обезличенный schema-level анализ ответа /api/v1/m/threads/status/smart, без пересылки токенов, почты и личного текста. Не выполнять запросов к ящикам без участия владельца.

## I-055 — пользовательская приёмка v0.3.51
Установить v0.3.51 поверх предыдущей. Проверить растянутую справа диагностическую панель и журналы высотой 590px. Получить контролируемое новое письмо, полностью выйти и запустить заново, сохранить обезличенный журнал. Проверить MCS RECEIPT_RETAINED, LOGIN_PENDING_RECEIPTS count, LOGIN_RECEIPTS_CONFIRMED/UNCONFIRMED и сравнить NEW_MAIL_EVENT/REPLAY_DISK_SUPPRESSED. LOGIN_RECEIPTS_CONFIRMED значит подтверждён приём пакета входа, не обязательно каждый предыдущий SELECTIVE_ACK. Не инициировать новые Google/PushMe регистрации и сетевые пробы без владельца.


## I-056 — пользовательская приёмка v0.3.53 и завершение исправления переписок (active)
После опубликования v0.3.53 проверить на ПК владельца две исходные переписки 5 и 2: число писем в существующем столбце, одну строку на логический диалог, самостоятельные отправители/даты/полные тела всех отдельных писем в обычном и отдельном окне. Запрос `/api/v1/m/threads/thread` подтверждён статически оригинальным APK, но не живым ответом владельца; при серверной ошибке получить только безопасные коды и форму JSON без адресов/токенов/содержимого писем. Не объявлять цель completed до реальной приёмки и не выдумывать ID. Указание владельца по локальному фильтру: OFF и перезапуск не вызвали наблюдаемых повторов; для выводов о сервере нужны отдельные диагностические подтверждения. Google регистрации, PushMe группы и подписки не менять.

## I-057 — приёмка v0.3.54 на ПК владельца (active)
Проверить, что письмо «Лучшее от МКБ» и другие рассылки находятся только в своей папке, сводные метакатегории не превращаются в реальные письма во «Входящих», а реальные обычные переписки отображаются. Проверить единственный основной цвет и согласованную динамическую палитру в тёмной, светлой и системной темах, сохранение после перезапуска, неизменные булавку #C62832 и звезду #D49E00, цветные отправителя/тему/время непрочитанных сообщений. Автономные CI тесты прошли, но принятие результата пользователем не подтверждено. I-056 (реальная полнота переписок 5/2) остаётся active.

## I-058 — поэтапная переработка архитектуры MailRu Desktop (active)
Владелец согласовал системную переработку без переписывания приложения и дал директиву начать с полного аудита. Аудит выполнен 13.10.2026: docs/architecture/refactoring-audit-2026-10-13.md, коммит 42772adaf01e177d869e39f61af4b7dcbd8b7d6e. Дальнейшее принятое обязательство: вернуть булавку в действующий ModernMailRowTemplate с фиксированным цветом #C62832, зафиксировать проверки, затем поэтапно отделять модель списка, состояние папки, единые действия над письмами и интерфейс. Протокол и особенно Google/PushMe — только после дополнительных проверок и без изменения пользовательских регистраций. Приёмка каждого этапа по Windows CI и фактическим пользовательским сценариям. I-056 по перепискам 5/2 и I-057 по v0.3.54 не закрывать без отдельных доказательств.

## I-059 — приёмка возвращённой булавки v0.3.55 (active)
По прямой директиве владельца и по архитектурному плану этап 0 реализован и опубликован в v0.3.55. Дождаться проверки владельцем: кнопка булавки в действующем табличном списке справа от звезды, незакреплённое состояние — контур; закреплённое — #C62832, изменение выполняется по одному нажатию, серверный отказ не меняет статус, список пересортировывается и сохраняет выделение, контекстное меню «Закрепить/Открепить» действует согласованно. До пользовательской приёмки оставить active. I-058 (поэтапный рефакторинг архитектуры) остаётся active; следующий изолированный этап — один действующий список и отделение рабочего состояния от диагностического поля. I-056 и иные неподтверждённые сценарии также остаются открытыми.

## I-060 — пользовательская приёмка v0.3.56 и дальнейшее сохранение состояния (active)
После сообщения владельца, что v0.3.55 работает, согласованы и разработаны 6 изменений интерфейса и подписей, плюс задача восстановления загрузки картинок аккаунтов, строк писем и картинок внутри письма. Выпуск v0.3.56 подтверждён GitHub, но пользовательская приёмка ещё не получена. Проверить: положение разделителя настроек после перезапуска; независимые геометрии окон нового письма, ответа, просмотра; сохранение состояний вкладки диагностики и разделов «Шаблоны/Подписи»; форматирование шаблонов Markdown действительно видно после вставки в отправляемое письмо; поле адресата в настройках шаблона отсутствует (оно отсутствовало до изменений и осталось только в настоящем редакторе письма); повторная вставка подписи заменяет прежнюю в конце; вручную добавленный .md появляется в списке подписей; старые подписи не потеряны при миграции; картинки реально загружаются. Обязательно продолжить выявление остальных геометрических и интерфейсных состояний для полного исполнения требования «все состояния должны сохраняться». I-058 (архитектурное разобщение списка и операций) остаётся active; I-059 (булавка v0.3.55) пользователь подтвердил как работающую.

## I-061 — приёмка отдельного редактора шаблонов v0.3.57 (active)
Владелец выбрал отдельное окно редактора вместо растяжения полей внутри настроек. Разработаны единый список шаблонов в настройках, отдельное окно с полным форматированием/подписью/вложениями, хранение в прежних .md, независимое сохранение положения и размера. Реализация PR #138 объединена с main, проверка Windows CI прошла. Требуется установка новой версии и приёмка владельцем: создать/изменить/сохранить/открыть существующий шаблон, убедиться в корректности форматирования после повторного открытия, выборе подписи и сохранении вложений; переместить окно, изменить размер/развернуть, закрыть и открыть снова; убедиться, что в настройках только компактный список, поле получателя отсутствует, отмена не портит .md и перезапись спрашивает согласие. Пользовательскую приёмку v0.3.56 (I-060) и ещё открытые исследования не закрывать без доказательств.

## I-062 — приёмка шаблонов/подписей/вложений v0.3.58 (active)

Шесть требований владельца реализованы в PR #140 и опубликованы v0.3.58: открытие вложений по двойному щелчку, отдельный текст ответа от цитаты и подписи, независимая от курсора вставка, неизменная тема письма и отсутствие темы в шаблонах/.md новых сохранений. Проверки Windows CI 38406654517 прошли. Требуется приёмка на ПК владельца: входящее вложение в основном и отдельном просмотре; открытие без диалога сохранения в программе по умолчанию; явное сохранение; смена шаблонов и подписей при курсоре в истории, включая ранее созданные .md с subject; отсутствие дубля подписи; сохранение текста цитаты; тема Re: и самостоятельная тема нового письма не меняются. Не объявлять работу подтверждённой сервером или пользователем до наблюдения. При тестировании не выполнять автоматическую отправку реального письма и не менять регистрации Google/PushMe. I-060 (изображения/сохранение всех состояний), I-061 (дополнительная приёмка редактора и сложного Markdown), I-058 (архитектура списка) и другие старые незакрытые задачи остаются действующими.

## I-063 — пользовательская приёмка орфографии и оформления v0.3.59 (active)

Проверить на Windows владельца новую v0.3.59: ошибка в русском и английском тексте должна подчёркиваться при наличии системных словарей; в контекстном меню должны работать предлагаемые варианты исправления; переключатель «Проверять орфографию при написании писем» должен действовать немедленно на все редакторы и сохраняться после перезапуска. Отдельно проверить отсутствие слова «Новое письмо» в теле формы ответа при сохранении «Ответ: ...» в заголовке окна, наличие заголовка при новом письме и видимые рамки текстового поля в шаблонах/ответах/новом письме/пересылке при разных темах. Подчёркивания не должны попасть в отправляемый HTML. Проверить, что цитата, тема, подпись, вложения, геометрия окон и авторизация не затронуты. При отсутствии русского словаря Windows честно сообщить ограничение, не считать отсутствие подчёркивания доказательством ошибки алгоритма. I-058, I-060, I-061, I-062 и иные прежние открытые задачи сохраняются.

## I-064 — приёмка единого списка писем v0.3.60 (active)

В PR #144 удалены только два старых шаблона строки, их неиспользуемые обработчики и сортировка; после Windows CI 38416635589 успешно опубликована v0.3.60 (issue #145). Требуется реальная приёмка владельцем после установки: входящие, рассылки, чеки, госписьма; закреплённые, звёзды, прочтение, галочки и приоритет выбранного письма; перестановка и сохранение размеров колонок; группировка дат, число писем в переписке и вложения; тёмная/светлая тема; отсутствие изменений в уведомлениях и авторизации. Не утверждать устранение ранее открытой проблемы переписок 5/2 без отдельной проверки.

Следующее намерение I-058: после успешной пользовательской приёмки этапа 1 выполнить этап 2 — независимую модель данных текущей папки, строго отделённую от окна диагностики. Отдельной версией затем этап 3 — единые команды над письмами. Прежние I-060–I-063 и Google MCS/PushMe остаются открытыми в пределах ранее зафиксированного.

## I-065 — пользовательская приёмка исправлений v0.3.61 (active)

На ПК владельца повторить три жалобы из v0.3.60: (а) проверить конкретные исчезнувшие аватары — прежде всего ранее отображавшиеся; если повторные попытки не помогают, получить обезличенный avatar_image-отчёт, не выдумывать отсутствие или наличие серверного файла; (б) повторить перемещение центрального разделителя из K-003.mp4, убедиться, что строки не перекрывают соседнюю панель, при этом ширины сохраняются и после расширения содержимое возвращается; (в) проверить русский ответ из скриншота: правильные русские слова не должны массово подчёркиваться, намеренная ошибка должна обрабатываться при наличии словаря русского Windows. Проверить отдельные шаблоны/новые письма, сохранность орфографического переключателя, остальные функции. Только после наблюдений закрывать I-065. PR #146, CI 38421779837, release #147 v0.3.61. I-064 — этап 1 в остальном подтверждён владельцем, кроме трёх выявленных замечаний. Этап 2 — отдельная задача после приёмки.

## I-066 — приёмка v0.3.62: пустые строки, очистка, разделитель, аватары (active)

Проверить после установки v0.3.62 на реальном ПК: (1) выделить оформленный текст, нажать «Удалить форматирование», убедиться, что остались буквы, абзацы и дополнительные пустые строки, а семейство/размер/цвет соответствуют редактору и текущей теме; кнопка должна быть также в шаблонах, ответе, пересылке, новом письме; (2) набрать письмо со 2–3 пустыми строками Enter, отправить на контролируемый адрес с разрешения владельца и проверить отображение именно отправленного письма; (3) перемещать центральный разделитель при широкой и узкой панели, плавно и без накладывания содержимого на аккаунты, проверить сохранение размеров; (4) проверить аватар Госуслуг и другие ранее отображавшиеся после возврата штатной WPF Image.Source. Если не появились, нужна доказательная работа с подписанными avatar URL конкретной учётной записи; sign нельзя угадывать или фиксировать из скриншота. Русскую орфографию владелец подтвердил в v0.3.61, её не меняли. Прочие I-058/I-064/I-065 и старые задания сохраняются до соответствующей приёмки.

## I-067 — сравнение плавности центрального разделителя на v0.3.63 (active)

Владелец инициировал эксперимент вернуть настройки центрального разделителя точно как в v0.3.60; иные работающие изменения не откатывать. PR #150 merged 3e051475eb1f3a5189e13d72a034df5dc9677394, Windows CI 38434156875 SUCCESS, инициирован штатный выпуск #151 v0.3.63. Требуется установка владельцем и непосредственное сравнение: перемещение центрального разделителя от очень широкого к узкому положению, визуальная плавность в сравнении с v0.3.60 и v0.3.62; появляется ли снова заезд области просмотра переписки на панель аккаунтов; сохраняется ли положение после перезапуска. Не считать пользовательскую плавность проверенной по результатам CI. Если плавность восстановилась, отдельно искать причину выходящего за границы окна просмотра, не добавляя прежние общие ClipToBounds-обходы. Если плавность не восстановилась, исследовать фактический цикл перерисовки WebView2/всплывающих элементов при DragDelta вместо очередного слепого изменения минимальных ширин.

## I-068 — пользовательская приёмка серверного поиска v0.3.64 (active)

После выпуска v0.3.64 владелец должен проверить: (1) поиск слова только в полном тексте письма, отсутствующего в заголовке/кратком фрагменте/отправителе, показывает все найденные сервером письма; (2) очистка строки не изменяет полученный серверный список; (3) ввод другого слова до нажатия кнопки не фильтрует старый результат, следующий серверный поиск заменяет его; (4) «Сбросить» возвращает текущую папку; (5) для поиска с >200 письмами порции загружаются постепенно с прогрессом и без повторов, число «найдено» не смешивается с числом «загружено»; (6) переключение папки или аккаунта во время поиска не показывает запоздавший результат старого аккаунта. Следить за неполной выдачей и сетевыми ошибками без предположений об ответе сервера. PR #152, CI 38438223907, docs/fixes/v0.3.64-server-search.md. Эталонную плавность разделителя v0.3.63 владелец подтвердил, запрет на дальнейшие изменения без отдельного согласования.

## I-069 — рефакторинг, этап 2: независимый снимок переписок (active)
Владелец 14.10.2026 предложил продолжить архитектурную переработку после положительных проверок. Ветка refactor/stage2-folder-snapshot-20261014 и черновик PR #154 содержат ограниченное изменение: BuildFolderThreadState из ответа папки, публикация после проверки поколения аккаунта, отсутствие чтения ResponseTextBox.Text при обновлении списка, автономные испытания и документ docs/architecture/stage2-independent-thread-state-2026-10-14.md. PR не объединён; Windows CI требуется до допуска к main/релизу. Эталонную плавность центрального разделителя и механизм уведомлений не менять. Далее этап 3 единых операций только отдельной работой. Пользователь подтвердил орфографию, видимую группировку, историю уведомлений без дублей, все аватары и сохранение настроек при обновлении. I-016/#77 «Отправить сейчас» остаётся открытой; I-068 по реальному поиску >200 не закрыт.

## 14.10.2026 — уточнение I-069 после выпуска v0.3.65
Реализация этапа 2 и поставка обновления завершены: PR #154 merged, main CI 38529232432 PASS, выпуск #155 v0.3.65 RELEASED с Setup/Update. Намерение пользовательской приёмки этапа 2 остаётся активным. Этап 3 (объединение Pin/Flag/Unread/Move) — отдельная следующая работа после приёмки. Функция немедленной отправки отложенных писем не реализована (#77). Другие подтверждения владельца от 14.10.2026 сохраняются.

## I-070 — принять диагностическую v0.3.66 и установить причину массового отказа авторизации (active)
Издано по явному поручению владельца: v0.3.66 с поэтапной анонимизированной диагностикой и пунктом «Повторная авторизация» в контекстном меню выбранного аккаунта. Код PR #156 merged; Windows CI main 38537034941 SUCCESS; issue #157 RELEASED. Ожидается установка на ПК владельца, анализ новых auth_token_flow по каждому ACCOUNT_NNN и контрольная повторная авторизация одного проблемного аккаунта. До подтверждения причины не начинать этап 3 структурного рефакторинга и не требовать массовой повторной авторизации. История уведомлений, орфография, группировка, аватары и эталонный разделитель не изменялись. #77 «Отправить сейчас» и живой поиск >200 остаются отдельными.

## 14.10.2026 — I-070: все аккаунты восстановились после перезапуска, причина не установлена
Пользователь сообщил о восстановлении всех авторизаций после перезагрузки приложения. Массовую повторную авторизацию не требовать. Статус инцидента: симптом не воспроизводится по последнему наблюдению, причина не установлена. Диагностика v0.3.66 остаётся для возможного повторения; анализировать её при новых фактах. Не объявлять исходную неисправность исправленной и не приписывать восстановление v0.3.66 без подтверждения установленной версии.

## I-071 — приёмка реальных команд после v0.3.67 (active)
После положительной приёмки этапа 2 владелец поручил продолжать рефакторинг. Этап 3 объединён и выпущен v0.3.67 (PR #158, Windows CI 38546542155 PASS, Release Actions 38546864745 PASS, issue #159 CLOSED). Следует проверить одиночные и групповые команды, приоритет галочек и сохранение настроек, прежде чем начинать этап 4 декомпозиции интерфейса. Причина прежнего временного отказа авторизации после перезапуска так и не установлена.

## I-072 — 14.10.2026: приёмка этапа 4.1 и отдельное продолжение рефакторинга (active)
Технически завершены исправления редактора v0.3.68 и извлечение WPF-заголовка просмотра v0.3.69 (PR #162, CI 38555215812 PASS, release 38555602330 PASS). Следующее действие — пользовательская проверка темы, отсутствия мерцания, плавности разделителя, двойного щелчка адресов, переписок, вложений, ответа и смены папок/аккаунтов. После приёмки выделить следующий небольшой подэтап 4 в отдельную ветку. Не дублировать и не переносить WebView2, не менять Google MCS/PushMe, серверные HTTP-маршруты и авторизацию.

## I-073 — 14.10.2026: завершить приёмку 4.1 и испытать исправление 4.2 (active)
По поручению владельца создан PR #164 (черновик), ветка refactor/stage4-2-folder-navigation-20261014. Новая обработка последнего выбора папки при загрузке прошла Windows CI 38568322417 PASS. До пользовательской визуальной приёмки v0.3.69 и реальной проверки A→Б→В, аккаунта, сетевой ошибки, WebView2 и плавности разделителя не объединять PR и не публиковать новый выпуск. #77, поиск >200, причина временных отказов авторизации остаются открытыми.

## 14.10.2026 — I-073: испытательный файл готов, приёмка активна
По поручению владельца подготовлена временная сборка v0.3.70 TRIAL в ветке PR #164, run 38570888795 SUCCESS, файл `MailRuDesktop_Update_STAGE4-2_TRIAL_v0.3.70.exe`, SHA256 c2e6be3020f4d282e9557fa475b4dbd8113624a8e5f356c06deb0e648249b7bb. Владелец получил ссылку на файл в чате; результат установки, сохранность настроек и живое поведение ещё не подтверждены. Следующие обязательства: получить результаты проверки этапов 4.1/4.2 и сценариев А→Б→В, ошибки/переключения аккаунта, подтвердить WebView2 и разделитель. PR не объединять до пользовательской приёмки; временный workflow удалить из PR до слияния. Основной выпуск остаётся v0.3.69.

## I-072 — 14.10.2026: завершено подтверждением владельца
Владелец сообщил «по v0.3.69 всё ок. всё работает корректно». Приёмка версии и этапа 4.1 подтверждена без замечаний. Прежняя формулировка I-072 о необходимости пользовательской приёмки устарела. I-073 / этап 4.2 остаётся активным: испытательная v0.3.70 TRIAL не проверена на ПК. Не смешивать эти два статуса.

## I-073 — 14.10.2026: этап 4.2 принят и опубликован (CLOSED)
Пользователь подтвердил испытательную v0.3.70: переписка, быстрая смена папок, возврат, смена аккаунта, отсутствие мерцания, плавный разделитель. PR #164 после удаления временного workflow и успешной CI 38574532295 объединён в main b1d2a3dd9c56d197e785c502ced7f804d4178828. Main CI 38574886051 PASS; GitHub release v0.3.70 выпущен Actions 38575208365 PASS, issue #165 закрыта. Этапы 4.1 и 4.2 закрыты по пользовательской приёмке перечисленных сценариев. Сетевые отказы отдельно не проверены; не выдавать за подтверждённые. Новая задача: повторно оценить целесообразность дальнейшего дробления MainWindow; протокол и уведомления без конкретного дефекта не трогать. Открыты #77, живой поиск >200, первопричина временных массовых отказов токенов.

## I-074 — 14.10.2026: предложено воспроизводимое исследование #166 (proposed)
После завершения этапов 4.1/4.2 выполнен повторный аудит v0.3.70 по поручению владельца. Вывод: массовый рефакторинг больше не оправдан. Следующая потенциально полезная узкая работа — контролируемая проверка завершения старой загрузки после удаления активного тестового аккаунта (issue #166), исключительно с искусственным ответом без реальных данных. Пока это отобранная рекомендация, а не подтверждённая неисправность и не разрешение на удаление живых аккаунтов или публикацию изменения. При воспроизведении ограничить исправление проверкой актуальности результата. Другие функции без доказанной причины не менять. Документ docs/architecture/refactoring-audit-v0.3.70-2026-10-14.md.

## I-075 — 14.10.2026: завершить приёмку ограниченного исправления #166 (active)
Владелец поручил продолжить проверку остаточных рисков после аудита v0.3.70. Предмет #166 ограничен предотвращением поздней публикации старой папки после удаления активного аккаунта; не включает массовый рефакторинг. Созданы ветка fix/issue-166-stale-folder-after-account-removal, черновик PR #167, автономные и WPF-испытания с фиктивными данными, CI 38581755244 и 38582419031 успешны, испытательный updater 0.3.71 TRIAL Actions 38582409624 успешен. Владельцу передан файл MailRuDesktop_Update_ISSUE166_TRIAL_v0.3.71.exe sha256 cc2903178f1edcfffdb265dab2f8c756d176d12a79f42f9dbb63f11ae3f5599f. Приёмка на ПК ещё не получена; нельзя считать этап принятым, объединять PR, удалять живые аккаунты или публиковать выпуск. Следующие действия после сообщения владельца: сверка результатов, исключение временного workflow из PR, повторная CI и отдельное решение об официальном выпуске. Не затрагивать WebView2, эталонный разделитель, HTTP, авторизацию или MCS/PushMe. Остальные #77, поиск >200, причина прежней временной ошибки токенов открыты.

## I-075 — 14.10.2026: код и официальный выпуск v0.3.71 завершены; пользовательская проверка ожидается
По распоряжению владельца PR #167 принят и слит в main; выпуск v0.3.71 через штатный механизм GitHub Actions 38586303436 SUCCESS, полная CI ветки 38585367328 и main 38585972071 SUCCESS. Официальная версия стала доступна для кнопки «Обновить» у v0.3.70. Оставшаяся проверка — сообщение владельца о фактической установке через кнопку и корректности работы. Без новых указаний не создавать экспериментальные сборки; при проблемах исправлять следующей официальной версией или согласованным откатом. Не утверждать проверку на живых аккаунтах только по CI.

## I-075 — 14.10.2026: официальная v0.3.71 принята владельцем (CLOSED)
После публикации выпуска владелец подтвердил «всё работает корректно», ранее ожидавшаяся пользовательская приёмка закрыта. Issue #166 закрыта с оговоркой: автономно доказано неприменение запоздавшего ответа, реальный сценарий удаления не проводился. Массовый рефакторинг после принятых 1–4.2 и #166 считать завершённым по текущим требованиям. Остаточные исследования скрытого LoginComboBox и пересечений переходов уведомлений с навигацией факультативны, только при воспроизводимых причинах. Далее приоритет функциональным задачам #77, живому поиску >200, диагностике прежних временных отказов авторизации, поштучной приёмке групповых команд при необходимости. Обязательный порядок выпусков: официальные Releases, без TRIAL сборок.

## I-077 — 14.10.2026: получить пользовательскую приёмку официальной v0.3.73 (active)
По прямому указанию владельца изменена функция №77: «клон отправляется сразу, оригинал переносится из Исходящих в Корзину, никаких мешающих всплывающих уведомлений». PR #172 слит, CI 38602726700 (ветка) и 38603124036 (main) PASS; выпуск GitHub v0.3.73 Actions 38603501963 SUCCESS, issue релиза #173 closed. Пользователю предоставлена ссылка на штатный updater и предложена кнопка обновления. Результатов установки и живой проверки пока нет. Требуется подтвердить отправку клона и перенос исходного письма. Отдельно, спустя первоначально назначенное время, проверить на одном письме самому себе отсутствие второй доставки; нельзя считать это гарантированным по CI. Issue #77 остаётся открытой до фактической проверки. Только официальные Releases, без TRIAL; WebView2, GridSplitter, авторизация, MCS не тронуты.

## 16.10.2026 — I-068, I-070, I-071 and I-077 reconciled with Owner observations
I-068 (search >200): the Owner decided to keep the existing result even when the server reports 202 but returns 200 IDs. Stop actively researching pagination unless the Owner reopens it. Do not assert an intrinsic server limit. I-070 (mass auth failures): none have recurred; no speculative auth changes, revisit on actual new evidence; root cause still unknown. I-071 (group operations): Owner reports ordinary behavior correct; leave accepted implementation unchanged unless a reproducible defect arises. I-077 (#77): Owner reports send-now works. Keep only the unresolved original-schedule second-delivery risk explicitly open until checked at the originally scheduled date; do not reimplement the accepted send-then-trash workflow.

## I-078 — 16.10.2026: v0.3.74 owner acceptance of forward, text selection and grouped recipients (active)
Owner directed three changes, including the explicit clarification that group reply collects only To and Cc, excluding senders. Implemented in PR #174; Windows CI pull request 38888080703 and main 38888331070 SUCCESS; squash merged 742813fe78cacd06679b9f08ebd4bd11577e9ea7; official v0.3.74 release Actions 38888586612 SUCCESS with both installers. Pending Owner real-mail acceptance: forward a message with attachments and verify received contents; select/copy subjects and textual data across forms; reply to a real multi-message thread and confirm To/Cc union, duplicates removed and sender/Bcc omitted. Underlying data may be incomplete if individual historical messages cannot be loaded, and UI warns. Interactive data-template rows were not blindly changed into textboxes; comprehensive copyability of every such row is not yet proven. Preserve WebView2, splitter, auth and MCS.


## I-078 — 16.10.2026: официальная v0.3.74 принята владельцем (CLOSED)
Владелец подтвердил после установки: «проверил, всё работает». Закрыта пользовательская приёмка выпуска v0.3.74 без замечаний; предыдущие обязательства по пересылке вложений, выделяемым текстовым полям и списку адресатов из Кому/Копия удовлетворены на уровне общей приёмки владельца. Не заявлять, что были проверены каждый текстовый элемент и все исключительные состояния. Независимый №77 по отсутствию второй доставки к первоначальному сроку остаётся без доказательства, не закрывать по этому сообщению.

## I-079 — 19.10.2026: пользовательская приёмка v0.3.75 (active)
Техническая реализация семи функций закончена и опубликована в официальном v0.3.75 через PR #176 (main 9a5bdd02798e53d0fef50da5003e528f6be7d0e4). Пользовательская проверка пока не получена. Проверить архив после успешного ответа на конкретное письмо, сохранение непрочитанности при предосмотре и отметку при полном открытии, удаление через системное уведомление без подъёма окна, отдельные настройки, один экземпляр, первую подпись и рабочие ссылки. Серверные изменения и toast на ПК владельца CI не проверяла. Граница #77 по повторному письму в исходный срок остаётся открытой.

## I-079 — 19.10.2026: в0.3.75 принята владельцем (closed)
Владелец подтвердил «всё работает» после официальной v0.3.75. Требования предыдущего выпуска приняты без дополнительного исправления. Независимый риск #77 не закрывается.

## I-080 — 19.10.2026: приёмка v0.3.76 (active)
Все пять уточненных изменений владельца реализованы и опубликованы: PR #178, main 3895eecf56d0630468226c9a7c904d9bfbea35f0, официальная v0.3.76 Actions 39260245465 SUCCESS. Проверки Windows ветки 39259531892 и main 39259916918 SUCCESS. Требуется владелец для пользовательской проверки: автоматическое закрытие отдельного письма при успешном архивировании; корреспонденты входящих/отправленных/архивных/цепочек; двойной щелчок копирует только email; повторная загрузка не показывает пустую таблицу; пункт копирования аккаунта. Сохранять текущие отлаженные WebView2, разделитель v0.3.60, сеть и MCS без необходимости.

## I-080 — 19.10.2026: v0.3.76 принята владельцем (closed)
Владелец подтвердил «всё работает». Исправления показа корреспондентов, копирования, мерцания и архивного окна приняты.

## I-081 — 19.10.2026: приёмка ZIP из отдельного письма v0.3.77 (active)
Реализация завершена и официально опубликована. PR #180 merged в main 83f1941f9e956ebacde545bd5c0c75c81dd6df34, Windows CI PR 39271430847 и main 39271817275 PASS, release Actions 39272123132 SUCCESS. Для реальной приёмки открыть письмо с двумя и более вложениями, воспользоваться «Скачать всё (ZIP)», проверить сохранение архива, состав, открытие папки и отсутствие потери данных. Один файл использует существующее действие одиночной загрузки; пакетная кнопка скрыта при 0/1. CI доказывает UI/логику имён, не живую загрузку с Mail.ru. Стабильные функции не менять без необходимости.

## I-081 — 19.10.2026: v0.3.77 принята владельцем (closed)
Владелец подтвердил «работает», пакетная ZIP-загрузка в самостоятельном письме принята.

## I-082 — 19.10.2026: приёмка v0.3.78 (active)
Согласованные и уточнённые изменения выполнены: новые письма и ответы автоматически начинаются «Добрый день» плюс две строки переноса; приветствие до подписи, без повторов после шаблонов, история переписки сохранена; пересылка без приветствия. Отдельные настройки — постоянный левый отступ 16 px. PR #182 merged main bc5508275fbac56614e7ae198927444c121a2ba7, CI PR 39313711656 SUCCESS, CI main 39314130757 SUCCESS, выпуск 39314396190 SUCCESS. Реальная приёмка в приложении ожидается.

## I-082 — 19.10.2026: v0.3.78 выявлено ненадёжное начальное форматирование (superseded by I-083)
После выпуска приветствия и отступа владелец обнаружил периодический жирный ввод в ответе. Согласовал локальное исправление. Два первоначальных изменения сохраняются без переработки; регресс обрабатывается следующим выпуском.
## I-083 — 19.10.2026: приёмка v0.3.79 (active)
Устранить самопроизвольный жирный начальный ввод в ответе и новом письме, не изменив форматирование подписи, шаблона, истории и ручное включение жирного. Код, тесты и официальный выпуск завершены: PR #184 merged main 71d82194f0a268dfb0f8484cd7cbe2f0e23a2647, CI ветка 39323173678 SUCCESS, main 39323504971 SUCCESS, Release 39323812358 SUCCESS. Владелец ещё должен проверить реальные письма с основной жирной подписью.

## I-084 — 20.10.2026: приёмка первого этапа Excel Mailing v0.3.80 (active)
По прямому согласию владельца реализована первая стадия новой спецификации без отправки и без изменения Excel. PR #186 merged main a3c19e36904780ad5b6a16c958c9a8af36109f30; CI 39330459123/39330797808 SUCCESS; release run 39331047163 SUCCESS. Пользователю дать обновляющий установщик v0.3.80 и предложить открыть настоящую книгу Microsoft Excel с исходной структурой, выбрать её в «Рассылках Excel», проверить 5 заголовков и скрытые строки, профили, логины/квоты, шаблоны, дату/время, подстановки и предпросмотр. Внешний справочник Т-Банка может изменить ответы/потребовать дополнительные данные; неподтверждённый ИНН — только требует проверки, не готов. Переход к массовому POST запрещён до исследования идентификаторов, серверного подтверждения и отдельного согласования/этапа 2.

## I-084 — 20.10.2026: частичная приёмка Excel Mailing (continuing)
Владелец сообщил о проверке 50 записей: названия 49 организаций по ИНН определились, одной — нет. Алгоритм блокирует неопределённую организацию как требующую проверки; в этапе 1 ничего не отправляется и не записывается в Excel. Владелец ещё не принял работу фильтра и нового порядка сканирования после v0.3.81.

## I-085 — 20.10.2026: приёмка v0.3.81 (active)
Регулируемая перетаскиванием высота таблицы аккаунтов с сохранением, чтение выбранного Excel с активной строки вниз порциями 192 строки, фильтры/ручное скрытие исключаются до попадания в кандидаты, контроль активного листа/книги, остановка после заполнения квот. PR #190 merged main 3843f2835ec58a82d505bc1761ae5f5e9b66b05a, Windows CI PR 39340618954 и main 39340852139 SUCCESS. В GitHub уже существовала официальная v0.3.81 по тегу fe308284163366e914fbca653393c2265b45069f, которая включает идентичные хэши изменённых файлов и два EXE. Повторный Build Installer Release 39341076326 завершился только неудачей Create GitHub release: тег уже существовал; артефакты релиза проверены. Дожидаться пользовательского теста на больших рабочих книгах. Предложение ручного подтверждения имени по ИНН согласования ещё не получило.
