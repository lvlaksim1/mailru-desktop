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
