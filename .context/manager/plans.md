# Manager plans

## Protocol plan

1. Keep API eligibility based on the same mailbox OAuth credential, not hostname.
2. Keep protocol changes isolated inside MailRuDesktop.Protocol.
3. Treat APK evidence as static evidence; newly enabled operations need controlled live or Owner-runtime validation.
4. Preserve at least five seconds between repeated research/probe/test network requests. Normal application runtime has no artificial fixed delay.
5. Preserve the v0.3.17 persistent isolated authorization profile for each account. Do not return to a fresh profile for every verification and do not share these profiles with normal desktop browsers.

## Current product baseline

Latest public GitHub Release: v0.3.25, published on 2026-10-08.

Current product code on main: merged PR #62 and PR #63. Main CI 37707321540 and release workflow 37707435464 passed; full and update installers are published.

The merged v0.3.19 bundle covers:
- window/maximized state and layout-width persistence;
- message-list/preview divider persistence;
- dark-theme scrollbar palette correction;
- compact sender/account icons;
- persistent account order and account sections;
- 275 ms hold-to-detach animated account reordering, with release-to-drop and no Escape cancellation;
- right-side expand/collapse arrow in section headers;
- requested preview/reply control relocation;
- named signatures;
- named message templates with subject, body and attachments;
- reply-form signature/template selectors.

## Immediate plan

1. Owner installs published v0.3.19 and validates in real Windows runtime.
2. Owner validates window state, divider and column persistence, scrollbar appearance, real sender/account avatar portraits with initials fallback, account dragging and saved order, account sections, preview/reply layout, signatures, and manually managed Markdown template files including attachments and legacy migration.
3. Fix defects established by Owner runtime evidence before expanding unrelated protocol functionality. The avatar URL is static APK evidence until its live image behavior is verified.

## Later plan

1. Thread and bulk operations.
2. Spam/unspam.
3. Unsubscribe and categories.
4. Attachment lifecycle.
5. EML, metadata and read receipt.
6. Search suggestions and separate investigation of go.mail.ru HTTP 520.
7. Snooze, color tags, filters, aliases/collectors, cloud operations and other mapped mechanisms.
8. Dedicated validation of delayed-send send_date and compose-session message-id semantics.

## File-template and avatar release note

The template source of truth is the top-level .md files in %LOCALAPPDATA%/MailRuDesktop/Templates. The app watches the folder and also refreshes when Settings/template dropdown opens. Selected local attachments are copied into Templates/_attachments and referenced from optional Markdown metadata; inserting a template replaces its subject/body/attachments without confirmation. Legacy settings.json templates are migrated once. Photo URLs use official-client evidence for filin.mail.ru/pic?email; failed/missing images should reveal the initials underneath. Preserve these constraints in future modifications.


## v0.3.19 verification priorities

1. Reproduce the original downward drag defect using real mouse interaction with at least three accounts, then confirm end-to-end reordering, no wrong account switch, same-position long hold and persistence after restart. Offline math tests already passed.
2. Validate the new-mail read receipt option: checked sends receipt=true as official APK boolean field; actual acknowledgment depends on recipient and Mail.ru behavior and must not be promised.
3. Confirm the schedule option is unchecked by default but its date is locally tomorrow at 09:00, refreshed when reopening New Mail; verify exact /schedule server handling and later delivery with an explicitly authorized test recipient.
4. Verify sender fallback with absent names in both array/object variants and across multiple accounts.
5. Preserve all previously released OAuth, Markdown templates, avatars and auth-profile constraints.

## v0.3.21 immediate verification plan

1. Owner installs update and tests dark vertical/horizontal scrollbars in lists, settings and mail viewer.
2. Owner tests selected same signature/template twice with mouse and Enter, and top-right × in inline reply/forward.
3. Owner selects custom template path, saves, reopens program, adds/renames Markdown files manually and verifies no data loss, attachments and directory-watcher refresh.
4. Owner checks the section-name dialog caption remains dark after focus on its text field.
5. Owner checks initially empty sender rows; select an affected message so full FromEmail populates row and verify parent thread fallback where present.
6. Owner checks receipt and scheduled send both in inline reply/forward and standalone compose; test with authorized recipient and observe real server acceptance/delivery.
7. Preserve existing account drag and splitter behavior that Owner positively confirmed. Fix actual runtime defects before unrelated API expansion.

## Color system enforcement after v0.3.21

1. Treat docs/theme-palette.md and ThemePalette.Roles as the authoritative inventory of 26 immutable semantic roles. Users may edit only hexadecimal color values; fixed role membership is a product invariant.
2. Audit every future XAML window/control and WPF programmatic element for color references. Prefer DynamicResource role keys; never add hard-coded UI hex values outside ThemePalette defaults or branded artwork.
3. Owner tests both built-in theme palettes, edits single/multiple roles, validates instant refresh and persisted dark/light settings after restart, color-picker functionality and system-theme automatic switching.
4. Audit contrast warning messaging and browser content/scrollbars without recoloring sender images or other branded media.
5. Do not regress owner-approved account drag physics and splitter geometry; postpone unrelated unfinished mail backlog until palette visual gate.

## v0.3.22 and remaining 12-point plan

1. Maintain released v0.3.22 features: 2D color palette, automatic missing sender lookup, readable/upgradeable Markdown files, configurable attachment downloads and upper attachment bar, formatted HTML editor while preserving legacy signatures and templates.
2. Owner GUI tests checkbox/Ctrl/Shift selection, ensuring a checkbox alone does not mark an email read; group move/archive/read and permanent trash deletion with disposable mail and server confirmation.
3. Owner tests formatting at a recipient, not just the editor preview. Preserve dual outgoing HTML + plain text fields.
4. Owner tests initial sender enrichment for empty-subject and missing-sender messages, markRead=false, max 20 per folder, 5-second requests and stale-account cancellation.
5. **Open issue #77:** identify and confirm a safe server command to immediately send an existing scheduled message without duplicate or remaining schedule. Static APK evidence for source.schedule alone is insufficient. Use controlled disposable test mail only.
6. Enable Outbox bulk, preview, detached and context-menu Send Now actions only after atomic server behavior is verified. Add end-to-end regression and publish follow-up; v0.3.22 must NOT be marked as fully completing all 12 items.
7. Preserve approved account drag animation and pane splitters unchanged.

## v0.3.23 authorization regression and account isolation plan

1. Treat Mail.ru HTTP200 + JSON {"status":403,"body":"token"} as an authorization failure, NOT an empty Inbox. This requirement applies to folder and full-message reads.
2. Detect expiring/invalid access tokens per account, try the saved refresh token only for the affected account, read its folder with the candidate token to confirm it works, then store encrypted replacement. Preserve LastLogin and unrelated account credentials.
3. Where the refresh token cannot restore access, show "Требуется повторный вход" only on that mailbox. Never clear every account or silently delete old encrypted credentials.
4. Owner installs v0.3.23 and verifies affected saved accounts; test one account's failure does not break or replace others, and the status message matches the actual mailbox result.
5. Never disclose, request uploading or log auth.json, access_token, refresh_token, session cookies, passwords or full secrets. Gather only anonymized status/error classifications for further debugging.
6. Keep the Outbox scheduled send-now blocker (#77) separate; preserve delivered bulk actions, sender parsing, rich editor, Markdown and UI.

## v0.3.24 acceptance and remaining deferred-send research

1. Record Owner explicit validation that the saved-account authorization/403-token issue was resolved by v0.3.23; do not reopen without new evidence.
2. Owner tests new v0.3.24 picker footer, dark calendar, ordinary Enter lines, resizable reply editor and no confirmation on permanent Trash deletion.
3. Owner repeatedly selects several messages while sender enrichment runs and after message state changes; checked IDs should remain stable. Group Read/Archive/Trash/Permanent Delete must modify ONLY confirmed affected rows, preserving scroll and others' marks.
4. Investigate remaining first-paint missing senders only if Owner still sees staged loading after same-message smart-thread representation fix. Prefer finding an existing batch payload; do not delay initial folder paint for 20 sequential full-message requests at 5-second intervals.
5. Maintain issue #77, duplicate-free atomic server transition of a scheduled Outbox message to immediate sending. Disabled buttons/context entry do NOT satisfy original feature request. Prove semantics with authorized test account and post-request schedule state verification before enabling.
6. Preserve Owner-approved account dragging and pane splitters and confirmed rich-format sending. If needed, publish further fixes with conservative labels and explicit Owner runtime test requirements.

## v0.3.25 acceptance and subsequent work

1. Published v0.3.25 (PR #86, main CI 37789612268, storage 37789612042, installer 37789849920). Owner tests independent active preview (accent outline) and checked selection (checkboxes); checkboxes have precedence for toolbar, preview and context actions, otherwise one open mail, otherwise display a non-destructive explanatory message. Ctrl selection is removed; Shift-click selects a contiguous visible checkbox range; double-click opens a detached reader.
2. Validate dynamic unread counts per folder after confirmed reads, archives, moves and deletes, plus explicit-only server reconciliation every 90 seconds; NEVER interpret absent JSON field as unread=0. Verify no full list reload or loss of selection.
3. Validate semantic theme colors including left navigation hover, light/dark picker, shared dialog styles; test persisted 10–18 logical-pixel font slider across navigation, lists, settings and dialogs, accounting for DPI and avoiding clipped labels. Incoming HTML's author formatting must be preserved.
4. Keep docs/ui-registry.json schema 2 as source-generated authority: 7 windows, 1349 element/style/resource declarations, XAML + runtime WPF, App.xaml + ThemeStyles.xaml; 26 known fixed color roles. CI python tools/ui_registry.py --check must pass; when editing UI first run python tools/ui_registry.py --write. Unknown role references are errors. Do not use an ongoing bot or unnecessary binary artifacts.
5. Recheck Owner's remaining slow sender: compact summaries may not contain correspondents; one full-message prefetch (3s bounded) before first paint may help, subsequent background fetches remain paced >=5 seconds. Do not claim 100% sender-first-paint solved absent Owner evidence and verified batch protocol.
6. Continue issue #77. Investigate and validate the EXISTING scheduled message's safe transition to send-now in a controlled test mailbox; ensure original queued schedule is cancelled or reused transactionally and no second message can be sent later. Until server action is verified, all Send Now controls remain disabled. Do not substitute composing and sending another email.
7. Preserve owner-confirmed account authentication fix v0.3.23, rich formatting, account drag animation, splitters, signature/Markdown template handling and other accepted functionality.


## CURRENT OVERRIDING PLAN — 2026-10-08, v0.3.26

Published Windows release v0.3.26 (PR #89 / CI 37797743640 / installer 37797787312). Registry-only PR #91 corrected runtime Window coverage (9 windows, 1354 elements, 26 immutable color roles), CI 37798726121 PASS. Older version references above are historical, not latest-state assertions.

1. Owner installs the v0.3.26 update and checks first visible message is fully formatted, without interim snippet.
2. Verify collapsed account sections move after ~275 ms and retain child accounts, expanded sections do not move; reorder persists and individual account dragging/splitters remain correct.
3. Check no Mail/Contacts navigation, Settings toggles to mail, and New Mail opens separately. Check attachments, rich editor, draft, schedule, and server contacts only in new-mail chooser.
4. Check folder picker is Browse + plain path with immediate save and preserved template migration/watcher. Check Signatures/Templates collapsed and button pressed-color selection in both themes.
5. Especially verify composing across active-account changes does not use an unexpected sender; do not claim this case safe without evidence.
6. Preserve existing checked-first mail actions, OAuth credential isolation, per-account authorization profile and accepted rich HTML. Keep issue #77 send-now disabled; validate only with disposable test messages and no duplicates before enabling.
7. Future UI edits must regenerate/check docs/ui-registry.json, including every programmatically created Window and runtime-hosted XAML subtree. No IMAP/SMTP/app passwords; 5-second spacing for research/test HTTP calls.


## OVERRIDING UPDATER PLAN — v0.3.27 (2026-10-08)

Product GitHub Release v0.3.27 supersedes v0.3.26. PR #92, main CI 37838100623 and installer release 37838253203 passed. Old primary-only release lookup has backup route through validated github.com releases/latest redirect, user-friendly safe status diagnostics and browser navigation. Do not conflate offline fallback test success with Owner network confirmation.

1. User installs MailRuDesktop_Update_v0.3.27.exe manually from https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.27 if v0.3.25 updater is unable to self-update.
2. In v0.3.27 check Settings→Update→Check updates; it should either show an actual version or disclose GitHub API/site status reasons; Open releases page should work via external browser.
3. If both hosts fail, collect only last sanitized github_update_check entry from %LOCALAPPDATA%/MailRuDesktop/diagnostics.log (avoid full log which may carry personal data). Investigate Windows networking/proxy/browser-only routing using concrete codes; do not claim specific root cause without evidence.
4. Preserve explicit two-step update model: check and then user clicks install. Do not enable unattended auto-install without Owner approval.
5. Continue pending Owner v0.3.26 UX acceptance, detached-compose cross-account sender investigation, and issue #77 safe scheduled send-now independently.


## CURRENT OVERRIDING PLAN — v0.3.28, 2026-10-09

Latest public binary release v0.3.28, merged PR #94 (1d07dc2d17d4ffd2fc0cfa238e09d67032ece4f2), main Windows CI 37847753416 PASS, two-installer release workflow 37847893518 PASS. Historical version statements above remain historical. Current interface catalogue: 9 windows / 1371 entries / 26 compatible WPF resource keys; 20 actually editable shared color controls.

1. Owner installs v0.3.28 Update installer and quickly switches between an HTML-heavy message, an image-heavy message and a plain-text letter. Ensure initial reader shows only current message or neutral loading label; no former mail or blank flash; no animation regression when resizing.
2. Check the two provided private af12.mail.ru readmsg inline GIF images from the Magnet receipt against real browser view. New source uses original email's OAuth attachment endpoint with mail ID/account checks and image signature validation; no claim of actual private-image success until Owner confirms. If images fail, request only sanitized status/case diagnostics, never tokens or private message content.
3. Increase interface font from 10 to 18: sender and account photo icons scale proportionally and preserve working portraits and compact rows. The portrait retrieval URL and fallback logic are intentionally unchanged.
4. Open detached New Mail under account A, switch main mailbox to account B, verify the 'От' address remains A and any test draft/send uses A. Avoid real outgoing mail except authorized disposable test recipient. Verify signatures/templates, repeated application and attachments.
5. Verify no blue default Send fill, no hover editor contour, single consistent native title color for standalone New Mail and contact chooser, compact AppDialog layout without trailing blank space, and responsive color picker brightness strip.
6. Validate folder clicks from Settings navigate immediately, New Mail command is above messages, signature and template expanders remain ordered and operational, external Markdown import never changes the source until Save, lower directory help messages are gone, all diagnostics copy.
7. Verify 20 unified theme settings in Appearance, existing custom colors migrated sensibly from 26 legacy roles in DarkPalette/LightPalette, consistent hover/control/scroll hues and persistent theme settings.
8. Preserve Owner-confirmed account-group drag animation, independent account auth, checkbox-first mail targets, updater v0.3.27+, reply contact UX and accepted sender avatar retrieval.
9. Keep Send Now for a previously scheduled Outbox letter disabled (#77) until an atomic duplicate-free server transition is proven through authorized test messages; delayed delivery and read receipts require separate recipient proof.
10. After any subsequent change to windows or controls regenerate and verify docs/ui-registry.json and test the generator's coverage of runtime-created Windows. Distinguish source/CI results from Owner visual runtime confirmation.


## АКТУАЛЬНЫЙ ПЛАН — выпуск v0.3.29, 2026-10-09

Последняя опубликованная версия v0.3.29, PR #96, объединение 8f0ff0c07e59b38fd6e14edd14bebd7b74018c19, проверки ветки 37850178802 и main 37850342043 — PASS, публикация установщиков 37850493662. В реестре 9 окон, 1374 элемента; пользовательских настроек цветов 20, технических ресурсов 26.

1. Владелец обновляется до v0.3.29 и сверяет исходные 22 пункта. Исходный текст перечня, а не заявление о готовности в журнале разработки, является критерием приёмки.
2. Открыть несколько писем быстро подряд и подтвердить отсутствие пустого фона, предыдущего письма и «сырого» HTML; отдельно проверить картинки с af12.mail.ru из ранее предъявленного чека.
3. Изменить шрифт 10→18, проверить размер реальных фотографий отправителей, отсутствие обрезки рамкой/колонкой и сохранение корректной загрузки фотографий.
4. Из настроек нажать уже выбранную папку и другую папку. Оба действия должны возвращать к письмам; проверить мышь и Enter. Кнопка «Новое письмо» должна находиться справа над списком сообщений.
5. Открыть отдельное новое письмо, проверить явные подписи «Подпись»/«Шаблон», шаблоны, вложения, поле «От» и безопасность переключения аккаунтов. Не отправлять реальные сообщения без отдельного согласия владельца.
6. Проверить заголовки окон и отсутствие лишних контуров при наведении, компактность уведомлений и работы окна выбора цвета; учесть, что системные заголовки зависят от версии Windows.
7. Проверить порядок настроек, выбор папок, импорт готового Markdown без изменения источника, кнопку копирования диагностики и 20 объединённых цветовых настроек с сохранением прежних пользовательских цветов.
8. Принятые ранее функции — фотографии отправителей, перетаскивание разделов, поиск контактов и определение обновлений — не перерабатывать без нового дефекта.
9. Задачу #77 держать заблокированной: нет доказанной атомарной серверной операции немедленной отправки уже запланированного письма без повторной доставки.
10. При всех следующих изменениях интерфейса обновлять docs/ui-registry.json и запускать WindowsUiSmoke плюс автономные испытания. Отделять проверенные сценарии интерфейса от сценариев, требующих учётной записи владельца.


## АКТУАЛЬНЫЙ ПЛАН — v0.3.30, 2026-10-09

Последняя опубликованная версия v0.3.30, PR #98, коммит 8b509be0592d3b3bdc4674419dc09d6ec637512f, Windows CI 37855438488 (ветка) и 37855678396 (main) PASS; выпуск 37855812685 PASS. Большинство остальных функций v0.3.29 владелец предварительно принял.

1. Владелец устанавливает v0.3.30 и быстро выбирает несколько писем подряд. Между ними не должно появляться прежнее письмо или неформатированное содержимое; HTML показывается по готовности DOM, изображения вправе появиться позднее.
2. На прежнем чеке Магнита проверить две встроенные картинки af12.mail.ru ...;0;1 и ...;0;2, сравнить с браузером. Новая загрузка через MailRuInlineImageSource/WebResourceRequested и OAuth не подтверждена без личной сессии; при сбое запросить только категории mail_image_inline/mail_render_timing без токенов и личных данных.
3. Измерить ощущаемую задержку загрузки проблемных писем против браузера; разделить время API тела и готовности разметки по безопасным записям mail_render_timing. Старые запросы теперь отменяются при смене выделения; непроверенная сеть и WebView2 остаются возможными причинами.
4. В настройках Шаблонов нажать «Загрузить .md»: окно должно изначально открывать настроенную папку шаблонов. Загрузить файл, изменить имя на новое, сохранить: исходный .md и его вложения остаются без изменения; создаётся новая копия со своими вложениями. Если имя уже занято — предупреждение с Перезаписать/Отмена, исходная версия остаётся до согласия.
5. Принятые функции аккаунтов, фотографий отправителей, контактов, оформления, обновлений не перерабатывать без новых замечаний.
6. Issue #77 не включать по предположению: сначала получить точную атомарную операцию существующего задания, тестировать отказ/повтор/срок отправки без дублей; подробный план docs/protocol/scheduled-send-now-safety.md. Технический долг остаётся открытым.
7. Прочие долги: фактическая доставка по расписанию, прочтение получателем, неподтверждённые серверные способы поиска. Следующий выпуск только с воспроизводимыми проверками. Запросы исследования не чаще, чем раз в пять секунд.


## OVERRIDING WORK PLAN — 2026-10-09 v0.3.31

Patch PR #100 merged 277a3dc67cac7792715accf6b2edc71193742900, branch Windows CI 37860604229 PASS, main CI 37860751548 PASS. Version v0.3.31 installer build requested via [BUILD_RELEASE] issue #101 / workflow 37860887859.

1. Owner installs v0.3.31 and reproduces video K-001.mp4: open Magnet receipt, switch to another letter, reopen same receipt. There must be a single stable visible paint instead of HTML followed by layout-shifting images.
2. Verify image fidelity: hidden loading may wait up to 3s after navigation for images; if still pending, Stop cancels unfinished resources before reveal, so a slow image may remain unavailable. Flag any such lost essential images; adapt selective preloading/caching only with actual reproduction, without reintroducing flash or infinite waits.
3. If flicker still exists, gather only reader_visual_ready reason and elapsed-ms plus safe mail_image_inline results and optionally another short video; do not ask for full private body or credentials. Distinguish old-letter navigation race from image layout changes.
4. Preserve v0.3.30 parallel auth image loading, cancellation of stale message fetches, Save As template copy and explicit overwrite confirmation. Keep Owner accepted avatar retrieval, contact picker, account drag, version checking, palettes.
5. Issue #77 Send Now of an already-scheduled Outbox message remains disabled. Proven atomic unschedule and unique delivery required before activation. Other technical debts: actual scheduled delivery, read receipts, server search alternative.
6. Any follow-up release requires Windows build, InteractionLogicSmoke, WindowsUiSmoke, UI registry and launch checks, plus Owner-specific WebView2 visual acceptance.


## OVERRIDING PLAN — v0.3.32, 2026-10-09

Последний продуктовый переход: PR #102, merge ae6968c0352a05badd278f31697e5f182b59f4eb, Windows CI ветки 37865194581 PASS и main 37865347046 PASS; release issue #103, installer workflow 37865488404 underway at the time of drafting.

1. После публикации и установки v0.3.32 воспроизвести видеосценарий: Магнит → поддержка ФГИС → Магнит → СДЭК → другие письма. Письма не должны показывать содержимое предыдущего документа, WPF пустой кадр или сырую разметку. Обязательно сравнить изображение и геометрию чека.
2. Подтвердить сохраняющееся получение аутентифицированных встроенных GIF и приемлемую скорость; при сбоях безопасная диагностика reader_visual_ready/mail_render_timing/mail_image_inline без полного письма/токенов. Быстрые старые запросы отменяются, серверное получение не переписывалось.
3. Новый обязательный регрессионный тест: реальный WindowsUiSmoke содержит WebView2, два синтетических документа с data: GIF, проверяет единственный активный iframe, один основной браузер, ноль новых верхнеуровневых навигаций после создания shell, и отсутствие скрытия контрола.
4. Инвариант для следующих исправлений: НЕЛЬЗЯ использовать второй WebView2, WPF-Overlay поверх WebView2, установку WebView2.Visibility=Hidden при смене писем, Stop или NavigateToString для каждого письма. Постоянная страница создаётся однократно; изолированные HTML элементы управляются внутри неё, переход заголовка после публикации.
5. Предварительно принятые почтовые и UI функции (подписи, шаблоны и Save As, авторизация аккаунтов, изображения отправителей, контакты, перетаскивание, темы, обновления) сохранять.
6. Issue #77 немедленной отправки отложенного письма по-прежнему ЗАБЛОКИРОВАНА: без атомарной отмены старого задания новая обычная отправка опасна дубликатом. Прочтение/точная доставка/неподтверждённый поиск остаются техническими долгами.
7. Требовать прохождения Windows CI, реестра элементов и запуска при каждом выпуске. Не считать реальную картинку владельца проверенной по одному автономному испытанию.


## АКТУАЛЬНЫЙ ПЛАН — 2026-10-09: пользовательская приёмка 1–3 закрыта

1. Владелец подтвердил устранение мерцания, проблем встроенных картинок и задержек отображения в v0.3.32. Не вносить новые изменения в просмотрщик без воспроизводимой регрессии.
2. Исследовать в сохранённом APK Mail.ru 15.107.0.148045 механизм своевременных уведомлений о новых письмах: платформенный доставщик сигналов, регистрация учётных записей, синхронизация списка, воспроизведение предупреждений.
3. Зафиксировать точные кодовые доказательства, пробелы сохранённой декомпиляции и сравнительный план для Windows, без неразрешённых серверных запросов или изменений рабочего приложения.
4. Issue #77 остаётся заблокированной: не включать немедленную отправку отложенного письма до доказанной атомарной операции без дублирования.


## 2026-10-09 — уточнённый план прямой доставки уведомлений

1. Исследование APK фазы сетевого приёма/распознавания нового письма закрыто статическими доказательствами: GCM/HMS/VKPNS, PushMe регистрация, NewMailPush, NotificationHandler; см. отчёт исследования. Тест №37870637190 прошёл.
2. Осталось проверить точный регистрационный договор PushMe (крупные методы JADX частично не восстановлены) и возможную независимую Windows-доставку. Не утверждать, что Android FCM/HMS/VKPNS токены можно получить или использовать на Windows.
3. Изучать подлинный событийный канал Mail.ru; браузерный Notifier через WebSocket упоминается разработчиками Mail.ru, но его аутентификация текущим mailbox OAuth не доказана, и это другой транспорт, не сам Android SDK.
4. Следующая реализация только после доказательства реально доставляемого события с тестовой учётной записью и подтверждённого владельцем безопасного опыта; простой опрос по таймеру не считать эквивалентом требованию владельца.


## Актуальное направление — 2026-10-09: оригинальный GCM/FCM без RuStore/VK

1. Полностью разобрать GcmPushTransport, GcmPushFactory, GcmPushKitWrapper, MailMessagingService из исходного APK. Для этого исследовательский workflow 37872182332; источник и итоговые проверки фиксировать раздельно.
2. При сопоставлении с Android сохранять различие между встроенной библиотекой и Google Play Services — внешний фоновый транспорт может быть установлен системой, даже если пользователь видит только приложение Mail.ru.
3. Оценить независимое получение FCM через superhuman/push-receiver версии 2.1.7: современная регистрация через Firebase Installations и FCM Registrations, TLS mtalk.google.com:5228, восстановление и дедупликация; доказательства реальной эксплуатации — коммиты июня–июля 2026. Устаревшие javajuice1337/push-receiver-v2 и Liam Cottle не использовать как готовые решения.
4. Предварительные испытания должны показать созданный без чужих токенов идентификатор и получение контрольного сигнала. Только после этого, при разрешении, проверять, принимает ли Mail.ru такую подписку на тестовом аккаунте и доставляет событие 4. Пользовательский почтовый OAuth и токен FCM хранить раздельно; запрет дублирования.
5. Продуктовый код пока не изменять. Дальнейший результат — событийная доставка, а не частый опрос. Сохранить все принятые функции, #77 отдельно заблокирована.


## 2026-10-09 — итог исследования GCM и условия следующего шага

GitHub Actions 37872182332 SUCCESS, SHA-256 APK проверен, 93 файла, 528770 байт, исследовательский коммит 7d5de6c41af591c0e4eaca30ee601bba4a2ea004. В оригинале Google Play Services выбирается первым и обеспечивает FCM-токен, для которого PushMeSDK регистрирует все аккаунты с ru.mail.oauth2.access. Следующий этап: изолированный Windows опыт с собственным токеном на согласованном тестовом ящике и доказательством server event=4; не выдавать независимый сторонний FCM приёмник за готовую интеграцию Mail.ru. Исходное приложение и принятый WebView2 не изменять; #77 остаётся заблокирована.


## Принятый порядок исследования 2026-10-09

1. Подтверждены значения APK: push_sender_id=1098335887158, default sender=61247752867, приложение mail, сервер alt-push-me.mail.ru, сетевое platform=android; Actions 37874537322 PASS.
2. Проверить FirebaseInfoProvider.getToken(senderId) по targeted-source workflow 37874791066 и выяснить значение расхождения отправителей.
3. Не заводить новое приложение Mail.ru: попробовать независимый корректный получатель Windows по конфигурации официального APK без телефона.
4. На разрешённом тестовом ящике проверить действительный мобильный OAuth, принятие сервером PushMe подписки и фактический event=4, затем дубли/переподключение/несколько аккаунтов.
5. Только после подтверждения встроить событийную доставку в MailRuDesktop. Опрос по таймеру не считать эквивалентом. Не нарушать WebView2 и другие сданные функции, issue #77 остаётся заблокирована.


## Уточнение 2026-10-09 после успешных прямых проверок Google

Выполнено: исходная цепочка FirebaseInstanceId.getToken(senderId, FCM) подтверждена кодом; независимый одноразовый Google Checkin=OK; register3 для отправителя Mail.ru 1098335887158 вернул TOKEN_ISSUED; TLS/MCS LoginResponse=LOGIN_OK. Источники Actions 37874791066, 37875870944, 37876061718 и отчёт docs/research/mailru-mobile-push-contract-2026-10-09.md. Прежнее предположение, что Google не выдаст токен без Android, опровергнуто применительно к этой прямой регистрации, но не доказана полная FCM доставка.

Далее: (1) восстановить точные client/capabilities/device поля PushMe (Actions 37876287988, исследование); (2) подготовить локальную испытательную цепочку Google MCS + серверная подписка без помещения OAuth-токенов в публичные задания, с повторным использованием существующего AuthorizationStore; (3) доказать валидный серверный ответ для аккаунта и живой event=4, отсутствие дублей и сохранение работы телефона; (4) только затем интегрировать в .NET, без опроса по таймеру.


## Уточнение 2026-10-09 — самостоятельная Windows проверка PushMe

Состоялось: регистрация Google для отправителя APK, MCS логин, подтверждена Windows переносимая программа со встроенным Python и подлинной DPAPI проверкой на вымышленных данных (Actions 37877413352, 37877932559 PASS). Программа не требует загрузки APK на компьютер и берет подтверждённый публичный fingerprint SHA-1 из Actions 37876843642. Гарантирована попытка unsubscribe_by_token только своего временного токена в finally и классификация ответа validate_result. Изменения только research branch; docs/research/windows-push-trial-2026-10-09.md в main.

Далее: (1) подтвердить исходники capability из Actions 37877449399, устранить пустые/неверные поля без догадок; (2) повторить автономные Windows/Linux тесты и пересобрать экспериментальный EXE с новым профилем; (3) только на компьютере владельца с его сохранёнными данными провести настоящее испытание PushMe и event=4; (4) подготовить модуль .NET и обновление MailRuDesktop лишь после успешных тестов. Прежняя версия продукта не изменена. В CI main при документальном коммите была единичная ошибка WPF layout smoke; повторное выполнение 37877585319 SUCCESS, не считать неповторившуюся ошибку автоматически исправленной.


## Уточнение 2026-10-09 — полная форма PushMe установлена; следующий этап должен быть локальным

Выполнено: целевая декомпиляция MailCapabilitiesProvider и Distributors 37878519042 SUCCESS; подписка can_mail с Filter.Folder/SocialNetwork/SocialService без исключений, формат клиента из SDK и GMT+HHMM; SDK только V2, account.lower(), status0. Код research tools/push_research/local_mailru_event_probe.py и pushme_contract_probe.py исправлен, Windows/Linux автоматические испытания 37878867912 SUCCESS, портативный Windows EXE 37878867920 SUCCESS. Новая версия EXE только артефакт с хранением один день, рабочее приложение не обновлялось. Отчёт docs/research/windows-push-trial-2026-10-09.md.

Осталось: локально выбрать уже авторизованный тестовый аккаунт, получить подтверждение Mail.ru сервера ACCOUNT_ACCEPTED и наблюдать event4, сохранив уведомления телефона. Секреты остаются в DPAPI на ПК, никакого OAuth в GitHub Actions. Если PushMe отвергнет токен, по обезличенному статусу расследовать отличие Android FirebaseInstanceId от прямой регистрации register3 и синтетических сведений устройства. Если получено event4, проверить переподключение/дубли и перенести подтверждённый механизм в .NET. Отложить выпуск/обновление клиента до полного E2E.


## План 2026-10-09 после требования интегрировать опыт в программу

Готово в основном исходном коде: src/MailRuDesktop.App/MailRuPushProbe.cs (нативный TLS/MCS/PushMe, HTTP, protobuf, ограничение объёма сообщений); PushProbeWindow.cs (WPF окно выбора аккаунта и согласия); MainWindow.PushProbe.cs (открытие и одно обновление списка по событию); элемент настроек в MainWindow.xaml; регистр PushMe адресов в MailRuEndpointCatalog, новые offline/UI проверки; версия проекта 0.3.33. ui-registry.json перегенерирован автоматическим workflow 37880300567 PASS, коммит 33bd848. Спецификация docs/research/native-push-integration-2026-10-09.md.

Далее: подтвердить CI для актуального main, исправить найденные ошибки и выпустить единственный актуальный GitHub release с полной установкой и обновлением v0.3.33. Затем пользователь откроет Настройки → Уведомления (испытание), выберет ранее подключённый тестовый ящик, согласится на временную подписку и отправит контрольное письмо. Успех — только ACCOUNT_ACCEPTED и MAILRU_NEW_MAIL_EVENT_RECEIVED=YES, а не один Google Login OK. В перспективе после успеха — постоянное получение для всех аккаунтов, восстановление связи и отсутствие дублей. Нет опроса по таймеру.


## Переход к испытанию пользователя — выпуск v0.3.33 завершён 09.10.2026

Выполнено: интегрирован native .NET PushMe, WPF настройки, offline tests, UI registry, CI 37880498382 SUCCESS; GitHub Actions release 37880613099 SUCCESS; оба бинарных установщика для v0.3.33 фактически опубликованы на https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.33. Применена обычная политика latest full + update.

Теперь программирование новой альтернативной схемы без испытаний не требуется: владелец ставит UPDATE или FULL, вручную в Настройках запускает проверку своего подключённого испытательного ящика, отправляет контрольное письмо; сообщает только технические состояния. После этого разбирать серверные результаты и делать исправления непосредственно в основном .NET приложении. Не выдавать Google token + LOGIN_OK за готовую доставку event4.


## Update 09.10.2026: TLS-certificate correction shipped as v0.3.34

Observations: user screenshot shows full Google path PASS, Mail.ru subscription network failure, token cleanup unconfirmed. Verified original APK AltProd hostname's chain failure from GitHub Actions 37881120525 and 37881273739. Exact original Prod https://push-me.mail.ru TLS PASS and empty/no-account POST /api/v2/set_settings HTTP200; no claim of authenticated subscription. Updated main MailRuPushProbe.cs to use Prod in subscription and own-token cleanup, added safe DNS/TLS/connection/HTTP status messages, cataloged Prod, verified logic in offline CI 37881506882 SUCCESS. Official release 37881628473 SUCCESS and v0.3.34 full/update links issued. Next: user installs update, reruns integrated consent-based check; if ACCOUNT_ACCEPTED send control letter, verify real event4; if not, classify failure using safe on-screen statuses. After real event4 test durability, reconnect and multiaccount.

## План 09.10.2026 — приёмка общего получателя v0.3.36

Реализация и выпуск закончены: один TLS MCS поток + одна DPAPI идентичность Google + пакетный PushMe V2 с собственным OAuth для каждого ящика; строгая маршрутизация по полю account и защита от повторов. CI 37923954067 и релиз 37924183045 SUCCESS. Владелец получает обновление v0.3.36 по штатному установщику.

Следующий шаг — проверка событий на нескольких авторизованных аккаунтах и устойчивости единого канала, включая повторный запуск приложения, обновление OAuth и изменение списка. При удалении аккаунта текущая безопасная, но потенциально затратная схема — отзыв общего токена и переподписка остальных; не заменять её адресным отзывом без прямого подтверждения исходного SDK и безопасного живого опыта. Отсутствие повторов или потерь после длительного разрыва не объявлять доказанным до пользовательской проверки.

## 09.10.2026 — исследование исходных PushMe алгоритмов, выпуск v0.3.37

После отчёта 0/32 и требования владельца «изучать исходники, а не экспериментировать» исследованы `GcmPushKitWrapper`, `MailMessagingService`, `SetUpPushMeSdk`, `PushMeSDKPusherTransport`, `NewSubscriptionRequest`, `UnsubscribeRequest`, `SubscriptionUseCase`, `PushMeApiImpl`. Подтверждённую ошибку обработки отсутствующего `validate_result` исправили по `parseSubscriptionResponseToResult`, добавили внутренние автономные тесты. Исправили случайный отзыв постоянного Google-получателя при временной ручной диагностике. Добавили трей, меню Развернуть/Выход, скрытие по X и отдельную сохраняемую настройку системных уведомлений. CI 37929798142 SUCCESS и release 37930018273 SUCCESS, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.37.

Далее только статическая проверка из исходного DEX плохо декомпилированных методов текущего SDK и изучение системной части Firebase/Google Play services, которая НЕ содержится в приложении Mail.ru. Сравнить алгоритмы переподписки, восстановления токена и адресной отписки без запроса к реальным почтовым серверам. Живую работу 32 аккаунтов не считать доказанной до наблюдаемого результата штатной эксплуатации. Не смешивать факт компиляции с доказательством доставки.

## 09.10.2026 — v0.3.38: завершено восстановление оригинальной адресной отписки

Офлайн разбор APK classes19.dex workflow 37934915452 SUCCESS с SHA256; восстановлены Dalvik инструкции UnsubscribeUseCase.invoke и PushMeApiImpl.unsubscribeByDeviceId. Встречное доказательство: версия удаления V1 несмотря на V2 регистрации. Запрос форм-urlencoded account lowercase, device_id=DeviceIdProvider.getDeviceId (те же settings.device_id), application mail; только error.code=0 считается успехом. Реализована адресная отмена отдельных подписок без отзыва общего Google-токена, продолжение общего MCS без разрыва для остальных, очередь повторных попыток DPAPI, реконструкция актуального набора аккаунтов после сетевого разрыва. Добавлены автономные тесты на fake-токенах. CI 37936826274 SUCCESS; штатный release 37937170853 SUCCESS; v0.3.38 full/update опубликованы. Дальше: дождаться пользовательских наблюдений штатной работы и исследовать остаточные вопросы Google Play services исключительно по исходникам.

## 09.10.2026 — v0.3.39 instrumentation released
Completed: bounded privacy-safe Google MCS/PushMe recorder, explicit phase codes for identity/TCP/TLS/MCS/HTTP/server response/ping/close, preserved precise failure stage and retry sequence, Settings live log and Refresh/Copy/Save TXT/Clear. Offline regression tests against artificial data and Windows UI test passed in Actions 37939894913; installer release 37940096624 success with both Setup/Update https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.39. No additional outgoing traffic; source-derived business logic unchanged. Next: wait for user's report of 2+ reconnects then locate exact failing step and address using original APK / Google Play services as evidence, without experimental protocol guesses.

## 09.10.2026 — source-backed PushMe 499 investigation and v0.3.40 release
Trace of v0.3.39 two attempts: Google MCS LOGIN_OK each; PushMe HTTP200 + JSON error.code499 for 32 accounts; deliberate app exception closes MCS; 45 then 90 sec retry. Native APK parser returns ServerError(code,message), not mapped fixed 499; original batching limit20 groups by application and does not imply 20+12 for one app. Native DeviceIdProviderImpl.getDeviceId() returns getSdkDeviceId() — do not split blindly. Added safe original error.message diagnostic classifier without raw data, made status distinguish PushMe app rejection; network protocol unchanged. CI 37953889320 SUCCESS, release 37954095452 SUCCESS, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.40 . Next: user provides new diagnostics with SERVER_REASON_ classification to determine remedy, then source-justified patch only.

## 09.10.2026 — v0.3.41: source-first устранение неверной формы PushMe идентификаторов
После real log 499 INVALID_FORMAT статически восстановлен через workflow Original APK PushMe static bytecode audit полный CommonIdProvider: local __common_id_value__, android_id + ":" + MD5(Android Build.*). Продемонстрировано несовпадение Windows v0.3.40 Google ID as android_id и mailru-windows-* as sdk_device_id, тогда как оригинал имеет одинаковые CommonId в sdk_device_id и settings.device_id. Добавлена независимая защищённая виртуальная Windows AndroidID, CommonId формат, сохранение MCS credentials без отзыва Google, миграция старого DPAPI. Обновлены автономные проверки MD5, схемы JSON и повторного запуска; CI 37957052697 SUCCESS, installer release 37957368274 SUCCESS, оба файла v0.3.41 https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.41. Новый реальный результат PushMe ожидается, не считать устранение 499 доказанным.

## 09.10.2026 — v0.3.42 Google MCS response parsing and optics
User's 19:18 v0.3.41 log: MCS tag3/version41, local rejection pre-PushMe. Original Chromium protobuf mcs.proto + mcs_client.cc show error.code must be nonzero, while our prior code rejected any ErrorInfo. Implemented original-based parser and sensitive-safe diagnostics id-present, error-present, nested numeric code; differentiate server error vs malformed. Manual + shared probe aligned. Separate checkbox diagnostic & identity deletion on opt-out. Offline tests verify success code0, absent error, nonzero code, malformed. CI first failed CS0136 shadowed variable, corrected; CI 37959083089 SUCCESS, installer release 37959302501 SUCCESS, v0.3.42 released https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.42. Continue after user's normal runtime log; avoid claiming actual server outcome known. Existing PushMe 499 INVALID_FORMAT investigation remains.

## 09.10.2026 — v0.3.43: roll back ONLY single selected-account test to verified v0.3.35
User requested another controlled real confirmation that one-account registration still works, with explicit choice not sequential automatic all. Maintained existing PushProbeWindow select and checkbox; disabled multiaccount worker at application startup and disabled automatic receiving checkbox without overwriting prior preference or revoking shared DPAPI Google identity. Reused old v0.3.35 single-account JSON profile in scoped BuildVerifiedSingleAccountSubscription; other shared logic untouched. Existing tray notification ShowBalloonTip on OnPushNewMail remains with separate checkbox. Offline JSON and Windows UI smoke passed CI 37962087207; release 37962300787 SUCCESS: v0.3.43 Setup/Update https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.43 . Next: user's ordinary selected-one-mailbox 3-minute test and observations; if ACCOUNT_ACCEPTED+NEW_MAIL_EVENT again, compare only necessary deltas for multiaccount work.

## 09.10.2026 — complete v0.3.44 one-versus-nineteen trial implementation
New Group19PushProbeWindow provides checkboxes preselecting first 19 (editable), strict exactly19 and explicit consent, manual Start/Stop. Start invokes existing shared 1-token receiver only for selected 19; auto all32 remains disabled. Service accepts retryOnFailure=false, avoids repeated invalid batches. Shared method preserveOtherAccounts prevents unsubscribe of nonselected prior roster; reject rolls back prospective roster; stop schedules address-specific cleanup of chosen group (network pacing may require 2 minutes), not global Google token. Existing single temporary-token probe and Windows balloon unchanged. CI 37964813669 PASS, release 37965013293 PASS, installers https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.44 . Next user server trace.

## 10.10.2026 — группа 19: реальный PASS
Владелец v0.3.44 подтвердил: Google MCS LOGIN_OK, PushMe HTTP200+SERVER_API_CODE 0, PUSHME_ACCEPTED 19, REJECTED_COUNT 0, NEW_MAIL_EVENT и всплывающее уведомление Windows. До этого 32 давали 499 INVALID_FORMAT. Не делать очередной релиз без новой задачи и не ломать 19. Следующее обсуждение: точный лимит (20 пока не подтверждён) и как обслуживать прочие аккаунты, изучив из APK возможную группировку, с отдельной приёмкой по каждому изменению.

## 10.10.2026 — релиз v0.3.45, отдельные стадии получателя и уведомлений
Готово: protected Google identity create-or-reuse строго по явной команде; независимый PushMe batch registration с произвольными галочками без нового Google Checkin/MCS, сохранение подтверждённых аккаунтов в защищённых группах; отдельное чтение MCS с listenOnly и кодами статуса; возобновление после перезапуска по сохранённому ReceiveEnabled; остановка только MCS без unsubscribe; удаление группы адресными API запросами после подтверждения; полное удаление Google отдельно и запрещено при наличии групп или подписок; однократная миграция legacy roster, предотвращение двукратной регистрации; старый 1-аккаунтный диагностика сохранился. CI 37996803222 PASS, релиз 37997098967 PASS, v0.3.45 Setup/Update. В следующей итерации принять результаты с реальными несколькими группами; не гарантировать, что сервер не заменяет подписки первой группы при регистрации второй. В UI состояние предыдущих групп после нового запроса маркируется как требующее проверки.

## 10.10.2026 — согласованные требования будущей версии (без немедленной разработки)
По прямому указанию владельца сохранять будущий список: подробная привязка каждого PushMe события к конкретному адресу и группе с полной/обезличенной формой копирования/экспорта, никогда не включая секреты; полностью автоматический процесс in-app обновления (нажал кнопку → приложение закрылось → прогрессбар без окон установщика → новое приложение открыто); неизменное расположение существующего ярлыка рабочего стола после обновления (не удалять и не создавать заново). При разработке проверить сохранность защищённых Google credentials, PushMe group registry и состояния MCS, а также ограничения UAC. На пользовательский вопрос о текущем v0.3.45: выбор строки группы №1 не является фильтром MCS, подключение охватывает все сохранённые группы; сейчас группа одна с 13 локальными участниками, активность серверных подписок ещё необходимо подтвердить письмом.

## 10.10.2026 — v0.3.46 released: подробный PushMe журнал / silent updater
После доказанного реального замещения группы 13 второй группой 7 владелец запросил обновление ДО удаления обеих групп. Реализовано: адресные START, HTTP status, OK/REJECTED/UNCONFIRMED в UnsubscribeAccountAsync с group ID, operation ID, i/total, отдельные queued/accepted/rejected для каждой учётной записи RegisterGroupAsync, события MCS с адресами; регистрируемый group ID создаётся до POST и сохраняется при подтверждении; PushDiagnostics.Report(redactAccounts) со стабильной заменой псевдонимов; UI ComboBox безопасный/полный экспорт, просмотр детальных сообщений и в окне групп; предупреждение о замещении групп. Вызов обновления /SILENT+ /SUPPRESSMSGBOXES, обновляющий Inno без Icons Tasks, автозапуск новой версии. Windows CI https://github.com/lvlaksim1/mailru-desktop/actions/runs/38000806394 PASS, installer release https://github.com/lvlaksim1/mailru-desktop/actions/runs/38001062877 PASS, ссылки v0.3.46 https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.46 . Не выдавать CI за пользовательское подтверждение обновлятора и сетевой чистки.

## 10.10.2026 — выпущена v0.3.47: несколько получателей Google, по одному на PushMe-группу
Новый рабочий предел группы 30 подтверждён пользователем (31/32 получают отказ). Миграция: старый первичный защищённый Google-токен сохранён, дополнительные отдельные DPAPI-слоты; реестр группы хранит идентификатор конкретного получателя, каждому отдельный поток MCS и сохранённые настройки приёма. Добавлены списки свободных/занятых Google-регистраций, адресное управление MCS групп, протоколирование и окно удаления с ходом по аккаунтам. Установщик обновления переходит на одно отдельное окно управления скачиванием и установкой; переход с v0.3.46 неизбежно запускается старым обновлятором. PR #118 merged, CI 38006441640 PASS, релиз 38006601274 SUCCESS, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.47. Следующая работа — приёмка на ПК владельца, затем исправление только подтверждённых дефектов.

## 10.10.2026 — v0.3.48 опубликована, PR #120
Изменения: отдельный ограниченный декодер PushMailEvent только для event=4; системные Windows уведомления без избыточного заголовка, адрес почты-получателя/время, sender/subject/snippet/признаки. По клику восстановление приложения, смена аккаунта и строгое сравнение mail id, поиск после перемещения через серверный поиск с проверкой id. Рефакторинг SettingsWorkspace: единый правый Expander «Диагностика» со вкладками Google/PushMe, протокола и приложения; удалён из основного вида отдельный тест, лишняя техническая информация и нерабочая автоматическая галочка. Reply перенесён из встроенного редактора в существующее отдельное окно нового письма, сохранены Re:, цитата, ReplyToId, защита другого черновика. Никаких новых MCS регистраций или PushMe событий. PR #120 merged commit a4f8aa6e532dac0f973fc896b44d9469ab549aad, CI 38011476643 success, workflow выпуска 38011613735 success, GitHub release https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.48 . Следом только приёмка на Windows, не заявлять реальную доставку без журналов.

## 10.10.2026 — v0.3.49: интерфейс диалогов и жизненный цикл ответа
PR #122 merged squash 63db7069783f11b72c8216919beb4c828bbbab01, CI ветки 38014417175 SUCCESS, CI main 38014576876 SUCCESS, installer workflow 38014588785 SUCCESS. Опубликованы полноразмерный и обновляющий установщики v0.3.49. Reply отдельным окном получает заголовок «Ответ: [тема]», окно закрывается только после успешной отправки/планирования; при ошибке сохраняет содержимое. Столбец thread count и история формируются из фактических идентификаторов сообщений, не из цитат/копий representations. Старые письма отображаются в <details> с собственными метаданными/полным содержимым при успешном чтении; при отсутствии ID или тела показывается явное ограничение. Один постоянный WebView2; trusted history проходит напрямую, отдельные HTML письма изолированы iframe sandbox. Следующий этап: испытание пользователем на реальном диалоге и адресная диагностика фактического API ответа, без спекуляций.

## 10.10.2026 — MailRu Desktop v0.3.50 released
PR #124 merged dc2d3a2eb43a7fc6b283d4ed13ca13f4cce48119; CI ветки 38016455774 и main 38016592078 SUCCESS, installer release 38016606891 SUCCESS, ссылки https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.50 . Введён DPAPI persistent dedup по хешированному mail identity, максимум 4096/45 суток; при replay отправляется обычный Google MCS ACK, UI не показывает старое повторно. Техническая панель справа 660px, текстовые журналы 440px. Объединение по серверному thread key между раздельными smart thread objects. Реальная полнота диалогов 5/2 остаётся непроверенной. Следующие действия — приёмка пользователя и при необходимости только подтверждённая структура JSON, без догадок.

## 10.10.2026 — опубликована MailRu Desktop v0.3.51
PR #126 merged 9b534cb469f3815377aee16691b3b066bfec57e8, CI branch 38018235382 PASS, CI main 38018358281 PASS, release 38018369767 SUCCESS. Setup+Update https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.51. Поддержка Chromium-style LoginRequest field10 repeated received_persistent_id через per-Google-recipient DPAPI-журнал pending receipts (192/14 дней). Снимаются после stream ACK входа; SelectiveAck и защита от повторов сохранены. Диагностика справа растянута на ширину после левого блока 620px, журналы с 440 увеличены до 590px. Причину старого replay живой сервер ещё не подтвердил. Отдельный дефект группировки 5/2 открыт: пользователь предоставил реальный smart JSON с threads[].length и representations[].length, message_id_last, старый парсер ждёт другие поля.


## 10.10.2026 — исправление группировки v0.3.53 опубликовано, ожидается реальное испытание
В версии v0.3.53 обработчик `MailRuConversationParser` теперь считает реальное `threads[].length`, индексирует `representations[].message_id_last` как ID реально известного последнего письма и не создаёт фиктивных членов из количества или цитат. При открытии цепочки `MailRuClient.GetThreadMessagesAsync` адресует `GET /api/v1/m/threads/thread` по подтверждённому логическому ID из исходного APK; `ExpandFromThreadDetail` принимает только независимые `body.messages[]` с проверкой принадлежности цепочке. Обычное и отдельное окна чтения сохраняют прежний безопасный показ при недоступности расширенной истории. Офлайн-проверки реальной обезличенной формы 5/2, CI Windows и реестр UI PASS. PR #130 cf3977856d230b216bd9599494ca71afd2a1d23b, CI main 38021684954 SUCCESS, installer 38021784049 SUCCESS, опубликован v0.3.53. Следующий шаг — пользовательский тест именно двух проблемных цепочек; без него статус «не полностью принято». При неуспехе изучить безопасный серверный ответ, не возвращаться к эвристике тем. Отдельная транспортная проверка: после OFF и перезапуска владельцем повторов пока не видно; не объявлять исправление MCS подтверждённым без трассы событий.

## 13.10.2026 — v0.3.54 реализована; следующий шаг — пользовательская приёмка
PR #132 merged e007104e6fdabddbf0a300e5f008560b78a83902. В обработчике smart status исключены сводные metathread, сохранена обработка обычных писем. Цвета интерфейса сведены к восьми оттенкам одного сохраняемого основного цвета с доступной единственной настройкой и немедленным пересчётом ресурсов; фиксированы булавка #C62832, звезда #D49E00 и информационные цвета; непрочитанные письма выделены оттенком текста. Обновлён docs/ui-registry.json и тесты. Windows CI 38375751655 и main CI 38376043391 PASS; выпуск 38376262148 SUCCESS, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.54. Следующий шаг: пользовательская проверка установки, цветов, папок и непрочитанных; не объявлять пользовательскую приёмку по одному CI. Не менять Google/PushMe подписки и регистрации.

## 13.10.2026 — архитектурный аудит и план рефакторинга
Создан документ docs/architecture/refactoring-audit-2026-10-13.md (коммит 42772adaf01e177d869e39f61af4b7dcbd8b7d6e). Этап 0: вернуть булавку в реально используемый ModernMailRowTemplate и добавить проверку; существующее контекстное действие сохранить и исключить дублирование на следующих этапах. Этап 1: один действующий шаблон списка и модуль группировки. Этап 2: FolderSnapshot/ConversationIndex отдельно от текста ResponseTextBox; испытания устаревших асинхронных ответов. Этап 3: единые команды Pin/Flag/Unread/Move и устойчивость выделения. Этап 4: обособление окон и просмотра без изменения числа WebView2. Этап 5: декомпозиция внутренностей MailRuClient без смены HTTP-контрактов. Этап 6: Google/MCS/PushMe только с доказанными сценариями жизненного цикла и разрешением на реальные испытания. Этап 7: очистка подтверждённых остатков и документации, проверка обновления и сохранности локальных данных. Каждый шаг малый, обратимый, со штатными тестами; переписывание с нуля запрещено. Аудит не вносил изменений в исполняемый код.

## 13.10.2026 — v0.3.55, выполнен этап 0 плана переработки
PR #134 merged ecf0bb2945af537d9de8230927b6671367775365. В ModernMailRowTemplate добавлен самостоятельный столбец булавки рядом со звездой. Значок отображает состояние Pinned с динамическим AppPinBrush=#C62832 и пустым контуром в выключенном состоянии; действие обрабатывает существующий SetPinnedFromContextAsync. Новых запросов, хранилищ, WebView2 или изменений уведомлений нет. Добавлены WindowsUiSmoke проверки структуры и Pinned-состояния; UI реестр автоматически обновлён; временный генератор удалён. Сборка и проверки feature CI 38381957209 SUCCESS, main 38382174773 SUCCESS; выпуск 38382377249 SUCCESS, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.55. Следующее: пользовательская проверка и затем этап 1 архитектурного аудита — устранить конкурирующие списковые шаблоны без изменения видимого поведения, добавить адресные регрессионные тесты; дальше отделить FolderSnapshot/ConversationIndex от ResponseTextBox. Протокол и MCS/PushMe не изменять в этих этапах.

## 13.10.2026 — v0.3.56 реализована, следующий шаг — испытание на ПК
PR #136 merged f2ac3e964ab4ccbefaa9d09780763ee5596e0889. Внедрены сохранение размеров и позиций основных дополнительных окон, регулируемый разделитель настроек и сохранение состояний подпунктов, хранение подписей в отдельной папке Signatures в .md с безопасным импортом прежних JSON-значений и обновлением списков при изменениях файлов. Реализована замена конечной подписи и вставка подписи в шаблон, форматирование Markdown в шаблонах с отображением в RichComposeEditor при отправке. Изменён ограничивающий CSP отдельного просмотра, добавлена загрузка и проверка публичных аватаров по HTTPS без аккаунтных токенов. Сборка и проверки Windows CI feature 38392035386 и main 38392584472 SUCCESS; Build Installer Release 38392791775 SUCCESS, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.56. Новые регрессионные тесты для миграции подписей, дублей, размеров окон и HTML-форматирования; пересоздан docs/ui-registry.json, временные workflow удалены. Следующие этапы: пользовательская проверка v0.3.56, доказательство реальной работы изображений, инвентаризация сохранения остальных состояний элементов и затем структурный этап 1 из docs/architecture/refactoring-audit-2026-10-13.md — один действующий список без старых дублирующих шаблонов; потом состояние почтового списка без зависимости от поля диагностики. MailRu HTTP, авторизация, MCS/PushMe и подписки не изменялись.

## 13.10.2026 — отдельный редактор шаблонов, выполненное изменение v0.3.57
В PR #138 объединён новый TemplateEditorWindow: список шаблонов остаётся в настройках, двойной щелчок или «Редактировать» открывает его, «Создать» — пустое окно, импорт .md открывает копию для редактирования. RichComposeEditor предоставляет форматирование; MarkdownRichWriter записывает поддерживаемые стили в .md, MarkdownRichLoader их читает. Папка, файловое сохранение и размещение вложений остаются прежними, подписи читаются из MarkdownSignatureStore. Окно восстанавливает геометрию, закрытие с несохранённым текстом требует подтверждения. Windows UI smoke проверяет отдельное окно, отсутствие получателя, подписи, геометрию, повторное сохранение Markdown; feature CI 38400151357 и main CI 38400574102 прошли. Следующий шаг — реальная пользовательская приёмка, в особенности сохранение всего форматирования при повторном редактировании старых .md, незапланированное нормализующее сохранение и подписи; затем продолжить структурную переработку списка писем по docs/architecture/refactoring-audit-2026-10-13.md. Не менять Mail.ru HTTP / Google PushMe без отдельного обоснования.

## 13.10.2026 — завершено изменение редактора и вложений, v0.3.58

Согласовано и реализовано в PR #140 (merge 92724f818991b4b54a25106d4f5759f582c75fa5): структурное разделение тела текущего ответа/подписи/предыдущей переписки внутри RichComposeEditor, применение шаблонов без замены истории, независимая от курсора подпись перед цитатой, отсутствие изменения темы письма, удаление темы из редактора шаблонов и записываемых .md; исторические метаданные subject при чтении игнорируются. Входящие вложения открываются двойным щелчком в системном приложении из временного каталога, без диалога сохранения, с отдельной возможностью «Сохранить». Небезопасные исполняемые расширения не открываются напрямую. Никакого изменения Mail.ru OAuth, HTTP-контрактов, регистрации Google или PushMe.

Windows CI 38406654517 PASS по сборке, автономным и WPF-тестам, реестру UI, запуску и ограничениям адресов. Выпуск v0.3.58 подтверждён issue #141: полная установка и обновление опубликованы. Следующие шаги: приёмка владельцем шести сценариев на настоящих письмах/вложениях, проверка старых Markdown-шаблонов и темы, затем отдельно вопросы изображений и сохранения состояний; только после этого — архитектурный этап единого списка писем из docs/architecture/refactoring-audit-2026-10-13.md. Считать CI подтверждением кода, а не реальной загрузки на ПК владельца.

## 13.10.2026 — выпущена v0.3.59 (орфография, заголовок ответа, рамки)

Один узкий выпуск редактора, PR #142 merged main 57476911e62134d9f79952a93b88c1368bf13ec7. Включена штатная Windows/WPF проверка орфографии на RichTextBox для всех форм с сохраняемым переключателем, включённым по умолчанию; для русского/английского необходимы словари ОС. Широкая валидация грамматики не внедрялась. Добавлена единая видимая рамка редактируемого текста через RichComposeEditor во всех местах его использования, а в самостоятельной форме ответа скрыт заголовок «Новое письмо» (верхнее название окна «Ответ: ...» сохраняется). Сетевой почтовый и PushMe код не менялся.

Тесты Windows CI 38412392707 PASS: основной продукт, автономная логика, WPF, ui-registry, запуск и контроль адресов. Выпуск с Setup/Update подтверждён в закрывающем отчёте issue #143, https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.59 . Следующее действие — пользовательская приёмка орфографии в русских/английских словах и рамках во всех формах и темах. Затем вернуться к остающимся обязательствам v0.3.56–v0.3.58 и этапам системного рефакторинга. Словари операционной системы не подменять недоказанными обещаниями полноценной проверки на всех ПК.

## 13.10.2026 — результат этапа 1 (v0.3.60)

Действующий список ModernMailRowTemplate сохранён без функционального переписывания; два резервных исторических шаблона удалены. Из MainWindow.V033.cs удалены неиспользуемые установки старого шаблона и вторичная сортировка, сохранены остальные действующие элементы V033. Старый неподключённый обработчик булавки удалён, штатный SetPinnedFromContextAsync работает по прежнему пути. Метод поиска кнопок перемещён к действующему современному списку. Расширены тесты элементов строки, признаков и сортировки, пересоздан docs/ui-registry.json. Документ: docs/architecture/stage1-single-mail-row-v0.3.60.md.

PR #144 merge bb8765381e5ea46175b62146c42c3a81447ba448; Windows CI 38416635589 SUCCESS; выпуск v0.3.60 подтверждён штатной заявкой #145: https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.60 . Следующий порядок: 1) приёмка владельца v0.3.60 на реальных папках; 2) отдельная ветка этапа 2: убрать зависимость группировки, счётчиков и состояния папки от ResponseTextBox.Text, добавить тесты поздних ответов; 3) отдельная ветка этапа 3: единые операции Pin/Flag/Unread/Move с сохранением приоритета галочек; 4) только после этого дробление MainWindow, затем MailRuClient, систему уведомлений — последней и с отдельным набором доказательств. Не смешивать сетевые гипотезы и структурные работы. Реальная приёмка не приравнивается к CI.

## 13.10.2026 — оперативное исправление v0.3.61 перед этапом 2

По трём замечаниям владельца v0.3.60 и предоставленному K-003.mp4 выполнена узкая корректировка: (1) ограниченные повторы загрузки аватаров, защищённые от повторного использования строки для иного отправителя, без расширения сетевой модели; (2) видимая граница списка при движении разделителя через ClipToBounds и без горизонтального выхода за область; (3) правильная метка языка ru-RU на WPF-контроле и документе редактора. Добавлены WindowsUiSmoke-проверки языка редактора/документа, допустимых адресов аватаров и ограничений панели. PR #146 merged d39cffabf41253727595da4f619eaead16d0ad97, CI 38421779837 PASS; GitHub issue #147 подтвердил RELEASED v0.3.61 с обоими установщиками https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.61. Подробнее docs/fixes/v0.3.61-user-regressions.md.

Следующее — реальная приёмка трёх симптомов: постоянное отсутствие аватаров требует обезличенной диагностики, поскольку повторы исправляют только временные сбои; русская орфография требует установленного словаря Windows. После приёмки продолжить этап 2 рефакторинга: рабочее состояние папки/диалогов без зависимости от ResponseTextBox.Text. Новые серверные функции, MCS, PushMe, переписки 5/2 и иные прежние ограничения остаются отдельными.

## 13.10.2026 — v0.3.62 исправления после приёмки v0.3.61

В PR #148 отдельно от структурного этапа 2 внесены четыре согласованных правки: новая кнопка очистки форматирования в общем RichComposeEditor (стандартные Segoe UI, AppFontBodySize, наследование цвета темы); передача пустого HTML-абзаца как <p><br></p> с автономными тестами нескольких Enter; смягчение ограничений GridSplitter (снятие общего ClipToBounds с WebView2 и минимум ширин 200/220, сохранение локального ограничения списка); восстановление native Image.Source вместо AvatarImageBehavior из v0.3.56 для аккаунтов/отправителей. Не создавать подписи Filin и не объявлять, что все изображения восстановлены; при повторном отказе нужно отдельно доказать источник server-signed URL.

PR #148 merged 19afd6ef90562c344f634bee501ad17fb2161f45, Windows CI 38429067387 PASS, регрессии проверены на WPF и без сети. Запущена штатная заявка выпуска #149 для v0.3.62, результат уточнять по подтверждению RELEASED, не по одному созданию issue. Дальнейшее: приёмка живого HTML письма/пустых строк, плавности при движении разделителя и аватаров; затем только отдельно этап 2 по состоянию писем без зависимости от ResponseTextBox.Text. Старый почтовый протокол, Google MCS/PushMe и русская орфография не менялись.

13.10.2026 — штатная заявка #149 завершилась подтверждением RELEASED v0.3.62. Оба файла опубликованы: https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.62/MailRuDesktop_Update_v0.3.62.exe и https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.62/MailRuDesktop_Setup_v0.3.62.exe . Тест на ПК владельца ещё ожидается; не объявлять подтверждённой плавность разделителя или получение всех подписанных аватаров.

## 13.10.2026 — экспериментальный выпуск v0.3.63, восстановление эталона разделителя

Вернуть центральный разделитель к v0.3.60 по фактическому сравнению GitHub tag v0.3.60 с v0.3.62/main: XAML левого контейнера Grid.Column=0 без ClipToBounds и MinWidth=0; MessagesGrid без явных HorizontalScrollBarVisibility/CanContentScroll; GridSplitter разметка в прежнем виде; в ConfigureResizableMailColumns мин. ширины 320/360. MainWindow.UserInterfaceState идентичен и не редактировался. Изменения только в MainWindow.xaml, MainWindow.V033.cs, тесте, версии и документации. Не откатывать оставшиеся улучшения v0.3.62.

PR #150 merged main 3e051475eb1f3a5189e13d72a034df5dc9677394; Windows CI 38434156875 PASS. Инициирован штатный релиз issue #151 версии v0.3.63; публикацию подтверждать только после ответа RELEASED со ссылками на оба файла, а не по созданию issue. Затем приёмка владельца: вернулась ли плавность, не накладывается ли переписка на аккаунты, сохраняется ли разделитель. Следующий структурный этап 2 о ResponseTextBox.Text отложен до результатов сравнения. Документ docs/fixes/v0.3.63-splitter-v0.3.60-control.md.

13.10.2026 — штатная заявка выпуска #151 завершилась RELEASED v0.3.63. Подтверждены два файла: https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.63/MailRuDesktop_Setup_v0.3.63.exe и https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.63/MailRuDesktop_Update_v0.3.63.exe . Требуется пользовательская проверка плавности, границ окна переписки и сохранения положения разделителя; факт публикации не означает успешную приёмку.

## 13.10.2026 — v0.3.64: исправлена двойная фильтрация серверного поиска

По согласованному плану реализовано разделение локальной фильтрации списка папки и серверного поиска по полному тексту. Удалена дополнительная локальная фильтрация поверх серверных результатов, сохраняется видимость при очистке поля; кнопка «Сбросить» возвращает папку. Подгрузка следующей порции: aj-https.mail.ru/api/v1/messages/search, offset/limit по 200, дубликаты по ID удаляются, интервал 5 секунд, остановка при конце/пустой или повторной странице/100 страниц, отдельные серверный count и реально загруженное число. Защита поколения и CancellationToken не допускает устаревшую выдачу после сброса, смены папки/аккаунта или закрытия окна. Регрессионные WPF-тесты используют искусственные результаты серверного поиска по полному тексту, отсутствующему в Subject/Snippet; проверяют очистку, фильтрацию папки, страницы, смещения, дубликаты и неполный ответ.

PR #152 merged main a3596412dfd5d4d363de49ddf05690d0d4797939; CI Windows 38438223907 PASS; issue #153 запускает релиз v0.3.64, подтверждение RELEASED отдельно. Документ docs/fixes/v0.3.64-server-search.md. Далее приёмка реальной почты владельца и проверка >200 результатов. Только затем следующий этап 2 архитектуры — отделение состояния папки от ResponseTextBox.Text. Центральный разделитель v0.3.63 пользователем подтверждён абсолютно плавным, его не переделывать. Google MCS/PushMe, авторизация и редактор не менялись.

13.10.2026 — штатная заявка #153 подтвердила RELEASED v0.3.64. Опубликованы оба файла: https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.64/MailRuDesktop_Update_v0.3.64.exe и https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.64/MailRuDesktop_Setup_v0.3.64.exe . Сборка и автоматические тесты PASS (CI 38438223907). Приёмка живого серверного поиска, в том числе >200 писем и отмены при смене аккаунта, ещё не проведена.

## 14.10.2026 — этап 2: отдельная модель данных переписок, проверка PR #154
После подтверждений владельца продолжена программа docs/architecture/refactoring-audit-2026-10-13.md. Проблема A-03 воспроизводима по коду: обновление списка ранее разбирало ResponseTextBox.Text, хотя диагностика перезаписывается многими запросами. На рабочей ветке refactor/stage2-folder-snapshot-20261014 предложен отдельный снимок папки, публикация после проверки смены аккаунта и автономный тест против подмены индексного JSON диагностикой. В main не менять код до подтверждённой Windows CI и принятия границ изменения; PR #154 — черновик. Контракт HTTP, Google MCS/PushMe, авторизация, редакторы, эталонный разделитель, хранилища неизменны. После этапа 2 — этап 3 единых команд с отдельными регрессионными тестами. Реальный серверный поиск >200 и безопасная немедленная отправка отложенных писем остаются отдельными задачами.

## 14.10.2026 — приёмка этапа 2 после v0.3.65
Релиз опубликован: https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.65 . PR #154 c9c3a5bfa431897ef5c99646c2cb26ed68b83925, версия main 352e018a6200601e3ad67c26db01e208238a72be, CI 38529232432 PASS, выпуск 38529543713 PASS. Владелец проверяет счётчики/группировку после просмотра тела письма, отметок, закрепления, смены папок/аккаунтов. Затем отдельным этапом 3 свести к общим реализациям Pin/Flag/Unread/Move с сохранением приоритета отмеченных писем. Не менять HTTP, Google MCS/PushMe, центральный разделитель. Задачи #77 и поиск >200 остаются отдельными.

## 14.10.2026 — диагностическая v0.3.66 выпущена, план проверки
PR #156 merged main 3dc018c6ad3abff24a4d243cc8920787bc5aa09e. Полная Windows CI 38537034941 SUCCESS после исправления реестра UI и проверки тестов, включая HTTP 429, отсутствие адресов и токенов в новых событиях и защиту от запоздавшего сохранения. Релиз Actions 38537316043 SUCCESS; issue #157 закрыта RELEASED, оба файла v0.3.66 загружены. https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.66 . После установки следует (1) сохранить диагностические строки категории auth_token_flow при обычной проверке проблемных аккаунтов, (2) установить точную стадию отказа, (3) испытать «Повторная авторизация» на одном выбранном аккаунте и проверить, что остальные аккаунты сохранены. Фоновая массовая проверка после отказа делает паузу 5 секунд; запоздавшая запись защищена. Реальный серверный ответ и источник первоначального отказа НЕ доказаны. Этап 3 по унификации команд приостановлен на время диагностики. Не делать вывод, что код сам устранил отказ.

## 14.10.2026 — план после выпуска этапа 3
v0.3.67: общий MailCommandExecution.RunAsync и ChangeMessageMarkAsync, все действия unread/flagged/pinned, групповая отметка прочтения и MoveMessagesAsync используют проверку подтверждения сервера и актуальности аккаунта/папки/токена. Взаимодействие MailRuClient и HTTP неизменно. PR #158, CI 38546542155 PASS, сборка 38546864745 PASS. https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.67 . Владелец проверяет звезду/булавку/прочтение, галочки и активное письмо, перемещение в папку/архив/корзину, изменение счётчиков и отсутствие регрессий в авторизации. Затем — этап 4 отдельной веткой, постоянный WebView2 и эталонный разделитель сохранять. #77 и поиск >200 — отдельно.

## 14.10.2026 — план после выпуска v0.3.69
Актуальный GitHub Release https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.69; PR #162 merged, main CI 38555215812 PASS, штатная сборка 38555602330 PASS, issue #163 CLOSED. В v0.3.68 исправлен редактор/подписи; в v0.3.69 завершён только этап 4.1: MailReaderHeader.xaml/.cs и MainWindow.ReaderHeaderAccess.cs выделяют тему, отправителя, получателя, дату и метаданные письма. Один постоянный MessageWebView, DOM-shell и GridSplitter прежние. Приёмка: проверить обе темы, отсутствие мигания, переходы полное письмо/переписка/папка/аккаунт, двойной щелчок адресов, вложения и ответ. Затем согласовать и выполнить подэтап 4.2 отдельно, с Windows CI и самостоятельной пользовательской проверкой. Открыты #77 «Отправить сейчас», проверка поиска >200 и причина ранее временного отказа токенов.

## 14.10.2026 — уточнённый план после CI этапа 4.2
Вместо массового разделения MainWindow сначала устранить доказанный по коду пропуск выбора папки при _loadingFolder: PR #164 (черновик), отдельная ветка, CI 38568322417 PASS. Последовательность: (1) пользовательская приёмка 4.1 на v0.3.69 — темы, заголовок, переписки, вложения, ответ, отсутствие мерцания, эталонный разделитель; (2) живые сценарии 4.2: A→Б→В во время загрузки, выбор A, смена аккаунта, задержка/ошибка сервера; (3) при успешных проверках решать об объединении и выпуске; (4) переоценить необходимость дальнейшего разделения состояния. Протокол, авторизацию, MCS/PushMe и WebView2 без подтверждённой причины не менять.

## 14.10.2026 — контрольный план после создания испытательного установщика
Тестовый updater Stage4-2 TRIAL v0.3.70 собран в GitHub run 38570888795 и подтверждён по SHA256. На ПК владельца провести тесты: (1) сохранить существующее состояние и обновить с v0.3.69; (2) проверить авторизацию, настройки и разделитель; (3) проверить заголовок письма (4.1), отсутствие мерцания, вложения, переписки и ответ; (4) быстро выбрать папки А→Б→В и возвращение к А, сменить аккаунт при загрузке, испытать ошибку сети и переход из уведомления; (5) проверить реальную группировку и счётчики. До подтверждения не объединять PR #164 и не создавать обычный релиз. После приёмки удалить из PR временный workflow и повторить CI. Продолжать остальной рефакторинг только по новым доказанным проблемам.

## 14.10.2026 — решение после пользовательской приёмки v0.3.69
Этап 4.1 принят владельцем: «всё работает корректно». Отдельных новых изменений основной версии не требуется. Продолжить исключительно проверкой 4.2 на TRIAL v0.3.70; по результату решить о PR #164. Оставить WebView2, разделитель и работающие подсистемы неизменными. Живые испытания 4.2 не объявлять завершёнными до ответа владельца. Не создавать официальный выпуск из испытательной ветки до проверки и удаления временного workflow.

## 14.10.2026 — следующий план после выпуска v0.3.70
Этап 4.1 принят на v0.3.69, 4.2 принят на TRIAL v0.3.70 и опубликован как официальный Release v0.3.70. Прежде чем продолжать структурные изменения, сопоставить текущую связанность MainWindow с прежним аудитом; выбрать только минимальные полезные операции, которые можно доказательно отделить и проверить. Предпочтительно не менять механизмы WebView2 и эталонного GridSplitter, работающую авторизацию и уведомления. Сетевой отказ при быстрой смене папок ещё не принят отдельным испытанием; не подменять это успехом общих сценариев. Остались: #77 немедленная отправка ранее отложенного письма, серверный поиск >200 в реальной среде и неизвестная причина временного отказа токенов. Принцип: небольшие проверяемые изменения, CI, пользовательская приёмка.

## 14.10.2026 — итог повторного аудита v0.3.70, решение о минимальном объёме
Подробный результат: docs/architecture/refactoring-audit-v0.3.70-2026-10-14.md. После закрытия 4.1 и 4.2 НЕ выделять новые WPF-компоненты для сокращения MainWindow и НЕ переписывать MailRuClient, WebView2, эталонный разделитель, авторизацию или MCS/PushMe ради чистоты структуры. Приоритетный кандидат для отдельного этапа: issue #166 — автономное моделирование запоздавшего ответа LoadFolderAsync после удаления активного аккаунта. Только если ошибка воспроизводится: малое локальное исправление, отдельная ветка, автономная проверка, Windows CI и проверка пользователя. Скрытый LoginComboBox и пересечения навигации по уведомлению — предмет будущих проверок только при свидетельствах или плановом расширении функциональности. Незавершённые #77, живой поиск >200, причины временного отказа токенов остаются самостоятельными задачами, не основанием для массового рефакторинга.

## 14.10.2026 — этап проверки #166 после выпуска официальной v0.3.70
Аудит v0.3.70 выявил потенциально опасный поздний результат обычной загрузки после удаления аккаунта. Минимальная защита реализована только в PR #167 (draft), временный updater 0.3.71 TRIAL собран Actions 38582409624 PASS и проверен по SHA-256. Проверка CI PR 38582419031 PASS. Для проверки владельцем использовать испытательное обновление существующей установки, не удалять действующие аккаунты; проверить сохранность аккаунтов/настроек, обычную навигацию, открытие переписок, отсутствие мерцания и эталонную плавность разделителя. Изолированная проверка удаления выполнена с искусственными данными и НЕ является пользовательским подтверждением реального удаления. После положительной приёмки: (1) удалить временный workflow issue-166-trial-update.yml из ветки PR, (2) вновь провести CI и оценить итоговую разницу, (3) лишь по отдельному решению объединить и подготовить штатный выпуск. В нынешнем main продукт v0.3.70 неизменен. Автоматическое обновление токенов не менять; FolderLoadIdentity сверяет поколение и аккаунт, НЕ access_token.

## 14.10.2026 — процедура поставки после v0.3.71
Владелец явно запретил экспериментальные сборки в обычном процессе. Разрабатывать на отдельной ветке и проверять CI, но пользовательские обновления передавать через обычный GitHub Release с Setup и Update, чтобы их видела кнопка приложения. При серьёзном регрессе использовать историю Git и выпуск новой версии с проверенным прежним кодом; учесть что штатная очистка releases удаляет старые установщики, а откат файлов не гарантирует прежнее состояние авторизации/пользовательских данных. v0.3.71 опубликована: main 6cfe4557f3f3a3c10ce93ad390457be2b1552662, CI 38585972071 PASS, Actions release 38586303436 PASS, issue #168 CLOSED. Следующее — приёмка обычного обновления владельцем; не трогать WebView2, разделитель, HTTP, авторизацию и MCS/PushMe без конкретных оснований. Открытые #77, поиск >200, неустановленная причина временной ошибки токенов остаются отдельно.

## 14.10.2026 — план после приёмки v0.3.71
Состояние: официальный релиз v0.3.71 успешно установлен и принят владельцем («всё работает корректно»), issue #166 closed/completed. Обязательного структурного рефакторинга в текущем объёме не осталось. Закрыты этапы 1, 2, 3, 4.1, 4.2 и точечная защита №166. Не начинать массовый перенос MainWindow, MailRuClient или жизненного цикла Google MCS. При возникновении нового воспроизводимого случая — локальная диагностика и минимальное исправление; возможные R-02 (скрытый LoginComboBox) и R-03 (переход по уведомлению во время ручной навигации) сохранять как объекты оценки, не считать неисправностями. Ближайшие самостоятельные функциональные работы: #77 безопасная немедленная отправка отложенного письма, испытание серверного поиска с >200 результатами, анализ неповторившегося массового отказа авторизации при наличии логов, уточнение пользовательской приёмки групповых операций. Только обычные GitHub Releases с Setup/Update.

## 14.10.2026 — план после v0.3.73
Официальная v0.3.73 опубликована, полная CI успешна, выпускаются Setup/Update. Следующее — владелец обновляется штатной кнопкой из v0.3.72, проверяет на одном отложенном письме себе: после «Отправить сейчас» копия приходит немедленно, оригинал перемещается из «Исходящих» в «Корзину», не открываются дополнительные окна. Позднее наступления старого срока нужно удостовериться в отсутствии второй доставки. Пока этого нет — не объявлять серверную отмену расписания доказанной, не рекомендовать массовую отправку. При неизвестном ответе отправки/ошибке перемещения повторно не нажимать вслепую; смотреть Исходящие/Отправленные. PR #172 merged, CI ветки 38602726700 PASS, CI main 38603124036 PASS, Actions выпуска 38603501963 SUCCESS, issue #173 closed. Issue #77 остаётся открытой до приёмки. Пользователь запретил экспериментальные сборки. Другие #77-неотносящиеся задачи: поиск >200, прежняя временная auth-проблема по логам; не возобновлять массовый рефакторинг.

## 16.10.2026 — official v0.3.74 released; acceptance and remaining boundaries
Published code via feature branch and PR #174, CI on Windows (pull request 38888080703, main 38888331070), Release v0.3.74 by Actions 38888586612. No trial build. Next: Owner uses the normal Update button; forwards a real mail with attachments, verifies their names and contents at recipient; tests subject/metadata selection and Ctrl+C, including other forms as appropriate; replies to a grouped conversation with at least two original mails and checks that only distinct To and Cc recipients appear (self omitted, From/Bcc never inferred). If some member messages cannot be retrieved, the compose status warns and requires manual verification. Do not claim live behavior from CI alone. Issue #77 remains only for originally scheduled-time duplicate delivery evidence; search >200 is consciously left unchanged and auth issues are observation-only. Preserve main reader WebView2, GridSplitter, token refresh and PushMe/MCS.


## 16.10.2026 — результат приёмки v0.3.74
Пользователь подтвердил «проверил, всё работает». Регрессионных замечаний не заявлено; дальнейшие исправления v0.3.74 и новый выпуск сейчас не нужны. Не инициировать самопроизвольный рефакторинг. Продолжать только по новым поручениям или при воспроизводимой неисправности. Ранее оставленное вне рамок приёмки испытание повторной доставки отложенного письма по №77 не объявлять выполненным.

## 19.10.2026 — приёмка официальной v0.3.75
Владелец обновляется штатной кнопкой либо MailRuDesktop_Update_v0.3.75.exe; проверяет на тестовых письмах семь изменений. Особенно проверять перенос только исходного письма после успешного обычного ответа, неизменность прочитанности в правой панели, отдельное полное открытие и удаление из уведомления при свёрнутом приложении. Отложенный ответ заранее не архивируется. Не выпускать новых установщиков без воспроизводимого дефекта, не использовать испытательные сборки, не менять принятый WebView2/GridSplitter/авторизацию/MCS ради рефакторинга.

## 19.10.2026 — проверка после официальной v0.3.76
Передан штатный установщик обновления с GitHub Release v0.3.76. Дождаться результатов проверки владельца пяти функций. Для списка проверить как минимум входящее, исходящее самому себе, исходящее нескольким адресатам, письмо в Архиве и групповую переписку. Для визуального обновления проверить закрытие полноформатного письма после пометки прочитанным и после архива с сохранением прокрутки/выделения без пустого промежуточного списка. При выявленном дефекте провести целевую диагностику, исправить без постороннего рефакторинга и публиковать только официальный выпуск после Windows CI. Не подменять получателей отправителем и не извлекать адреса из цитируемого текста.

## 19.10.2026 — дальнейшая проверка v0.3.77
Владелец обновляется штатной кнопкой или через https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.77. Для проверки использовать письмо с несколькими реальными вложениями, включая пару с одинаковыми именами: отдельный читатель должен показать кнопку «Скачать всё (ZIP)», открыть сохранение в настроенной папке, скачать все файлы в один архив, разрешить дубли имён и открыть каталог после успешного завершения. Сценарий с одним вложением остаётся старым. Принятую v0.3.76 не нарушать. При дефекте — целевая диагностика, малая правка, Windows CI и только официальный установщик.

## 19.10.2026 — контроль после v0.3.78
Владелец может обновить клиент штатной кнопкой или через https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.78. Приёмка: открыть новое письмо без подписи и с основной подписью — «Добрый день», пустая строка и поле текста выше подписи; открыть ответ через предосмотр и через отдельное письмо — приветствие, подпись, история в указанном порядке; применять шаблон с приветствием и без — нет дублирования, не исчезает история; пересылка не получает автоматическое приветствие; левый отступ окна настроек равен 16 px. При обнаружении дефектов — точечная диагностика, Windows CI и следующий официальный выпуск без изменения принятых функций.

## 19.10.2026 — приёмка исправления начального начертания v0.3.79
Поставить официальный v0.3.79 штатным обновлением. Проверить новый ответ на письмо с основной подписью, содержащей жирные фрагменты, и новое письмо: после «Добрый день» обычный шрифт Segoe UI и размер из настроек, без непреднамеренного жирного; подпись остаётся форматированной. При ручном нажатии «Ж» выделение и дальнейший ввод должны работать как прежде; после применения шаблона проверять, не перенимает ли текст случайные стили. В случае воспроизведения получить точную последовательность действий и повторно диагностировать наследование в WPF, не сбрасывать форматирование всей области.

## 20.10.2026 — план после v0.3.80
1. Владелец обновляется официальным v0.3.80. 2. В Excel открывает рабочую книгу, в приложении выбирает пункт «Рассылки Excel» и «Найти открытые книги Excel», выбирает лист. 3. Сохраняет профиль с одним .md-шаблоном, выбранными аккаунтами, квотами, датой/временем; нажимает «Проверить и подготовить». 4. Проверяет видимые строки, пропуск помеченных, некорректные ИНН, выбранного отправителя, совпадение первых времён разных отправителей, интервал 65 с и конкретные подстановки на реальных ИНН. 5. При ошибках Excel COM/справочника получить диагностическую причину и точечно исправить. 6. Не включать send/schedule/обратную запись до дальнейшего отдельного этапа с подтвержденными правилами идентификаторов, долговечным журналом, сверкой исходов и защитой от повторов. 7. Уточнить UI списка исключений и представление вложений/Markdown в предпросмотре при приёмке.

## 20.10.2026 — проверка v0.3.81 и неподтверждённых организаций
После установки v0.3.81 владелец проверяет: выбранная активная ячейка в Excel задаёт первую рассматриваемую строку, при другой активной вкладке появляется явная ошибка; скрытые вручную или AutoFilter строки не рассматриваются; просмотр продолжается по видимым строкам до набора суммарных квот, даже если отдельная организация не подтверждена; размеры таблицы аккаунтов меняются перетаскиванием и сохраняются после закрытия. Отдельный запрос пользователя: судьба 1 из 50 ИНН, для которой не найдено название — отображать «Требует проверки», без задания на отправку и без расходования квоты. Возможность ручного подтверждения названия лишь предложена, не реализовывать без согласования. Этап 2 с server /schedule до подтверждения уникальности и журнала не начинать.

## 20.10.2026 — план после v0.3.82
В первую очередь пользовательская приёмка единичного восстановления организации в Excel и повторной отправки из «Исходящих» с архивом оригинала. Владелец уже экспериментально подтвердил отмену первоначального расписания переносом исходного письма. Отправка/запись Excel остаются отключёнными. Следующий отдельный этап Excel — доказательство семантики id в /schedule, способа сверки неизвестного ответа, долговечный журнал, затем постановка и запись подтверждённых результатов. Только официальные выпуски; не переделывать стабильные WPF/WebView2/MCS компоненты.

## 20.10.2026 — plan after v0.3.84 official release
1. Owner updates MailRu Desktop via built-in update or official https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.84/MailRuDesktop_Update_v0.3.84.exe .
2. Verify eight Excel preview columns allow text selection and Ctrl+C, full-cell selection on double click, without altering underlying prepared rows.
3. With a saved open Excel workbook and 1–3 controlled permitted own recipients, prepare from active visible row and check filter, organization lookup, account quotas, exact letter body and server schedule. Do not start against real customers before controlled acceptance.
4. After user confirms launch, compare jobs in Mail.ru Исходящие and history, written «рассылка по СОУТ» (planned date) and «исх электронка» (sender) in corresponding original Excel rows, and no unintended changes. Save Excel manually.
5. Test pause/resume, disconnected Excel recovery of accepted-but-unwritten marks, and unknown server response no duplicate POST. CI proved simulated cases only; investigate real faults, deliver standard incremental official releases. v0.3.84 already includes two-column writeback originally planned for v0.3.85.

## 20.10.2026 — next after v0.3.85
Install v0.3.85 from in-app update or https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.85/MailRuDesktop_Update_v0.3.85.exe. Choose an existing multi-letter thread whose latest message is from the owner's account, press Reply, compare To to all verified To/Cc from the actual thread; own account, Bcc and From-only senders must be absent, duplicates collapsed. Verify a different single incoming letter still uses original sender as normal reply recipient. If full thread unavailable, selected outgoing To/Cc remain and status warns of incomplete history. Should a real thread still lose historical addresses, obtain exact type/status and non-sensitive diagnostics to inspect missing explicit thread_id or incomplete server member indexing, without guessing participants. Do not change working send/schedule or mass mailing. Only official installer updates.

## 20.10.2026 — next plan after official v0.3.87 (supersedes v0.3.86 links)
1. Install official Update https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.87/MailRuDesktop_Update_v0.3.87.exe or built-in updater. Confirm v0.3.87.
2. With 2+ saved Excel books open, select relevant workbook and sheet in MailRu Desktop and active cell on intended row in Excel; Prepare must start from that row and ignore filtered/hidden rows. Also test separate Excel processes/windows if available. On failure inspect real COM Window.Hwnd/ActiveSheet/ActiveCell behavior and selected workbook identity, never revert to global Application.ActiveWorkbook or nonexistent Window.Active.
3. Drag divider above selected-letter preview, verify resizing; check bold/italic/underline/colored text, links, alignment and line breaks show visually rather than literal Markdown markup.
4. In an explicitly approved tiny mailing of 1–3 own permitted test addresses, compare rendered preview to actual scheduled/received letter, Mail.ru Исходящие date, journal state, and exactly two Excel writeback fields on confirmed rows. Preserve manual workbook saving and no blind repeat after unknown outcome.
5. Investigate reproduced differences using source and safe diagnostics; issue only regular public installers, do not change unrelated mail, MCS or Excel functionality. Main code SHA before manager handoff commit 886fe193fbdebc32c3c9663db65961f8de3aef29; latest published v0.3.87.

## 20.10.2026 — план после официальной v0.3.88
Владелец устанавливает официальный Update v0.3.88 https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.88/MailRuDesktop_Update_v0.3.88.exe . Проверяет отсутствие прежнего нижнего блока и одну кнопку «Отправить»; выбранную книгу/лист/активную исходную строку при нескольких книгах и фильтрах. Затем на 1–3 разрешённых собственных адресах испытывает резервы наименования организации из Excel при отсутствии справочника, тему/HTML/дату/письмо в Исходящих, две точные отметки в оригинальных строках. Книгу сохраняет вручную. При неизвестном ответе сервера повторять POST запрещено; анализировать журнал и конкретную неисправность. Массовых живых тестов на посторонних адресах не проводить. Выпускать только очередные официальные установщики.

## 20.10.2026 — план после официального v0.3.89
Владелец обновляется на https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.89/MailRuDesktop_Update_v0.3.89.exe . Проверяет ранее воспроизводившуюся проблему: выбрать файл Excel и лист, выделить стартовую ячейку, менять окно Excel и другие книги, менять обычные данные и сохранить без Save As; при нажатии «Отправить» выбранная книга не должна ложно теряться. Проверить повторное обнаружение с сохранением выбора. В малом разрешённом испытании 1–3 личных адресов подтвердить отложенное письмо Mail.ru и запись двух полей Excel без привязки к Hwnd. При реальной смене FullName после Save As требуется заново выбрать файл, иначе безопасный отказ. Не запускать массовые реальные рассылки до подтверждения I-090/I-091; исправлять только воспроизведённые отклонения и выпускать официальные установщики.

## 20.10.2026 — приёмка v0.3.90
Обновиться кнопкой приложения или https://github.com/lvlaksim1/mailru-desktop/releases/download/v0.3.90/MailRuDesktop_Update_v0.3.90.exe . Проверить «Открыть журнал»: доступны прошлые отправки, изменение темы/тела/полей, сохранение, удаление выбранной записи/всего запуска без изменения писем сервера Mail.ru. Для безопасной проверки использовать личные тестовые письма, не массовые сторонние адреса. С флажком «Записывать в журнал» отправка сохраняет защищённые записи. Снятый флажок выполняет отправку и Excel отметки без записи даже служебного журнала и без защиты от дублей; состояние в профиле сохраняется кнопкой «Сохранить профиль». Проверить при смене профиля. Старые журналы остаются доступными. Не утверждать реальную приёмку заранее.

## 21.10.2026 — план приёмки v0.3.91
Дождаться официального Setup/Update https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.91 . Обновить кнопкой приложения или Update-вариантом. На собственной переписке из 3+ реальных писем раскрыть старое письмо, нажать «Ответить» именно возле него: окно ответа должно содержать его собственный текст и адреса по прежним правилам To/Cc, а отправляемое письмо должно отвечать на выбранный ID. Там же «Переслать» старое письмо: окно того же составления, поля Кому из To этого письма, Fwd: тема, исходный текст и ВЛОЖЕНИЯ этого письма; пересылка не архивирует исходник. Проверить действия и в отдельном окне чтения. Сравнить результат с общими кнопками, которые остаются для текущего/последнего. Использовать свои разрешённые адреса и не запускать массовую отправку без приёмки; исправлять реальные ошибки.

## 21.10.2026 — приёмка исправления кликов v0.3.92
После публикации официального выпуска v0.3.92 владелец обновляет клиент штатной кнопкой или MailRuDesktop_Update_v0.3.92.exe. Открыть уже воспроизведённую переписку с несколькими письмами. Сначала нажать на отдельные кнопки «Ответить» и «Переслать» у одного раннего письма: должны открываться окна составления письма, а не оставаться бездействующими. Повторить у последнего письма и в самостоятельном окне чтения. Сверить источник цитирования, состав адресатов, Fwd: тему, вложения; действия общих кнопок не изменились. Проверить 1–2 собственных адреса без массовой рассылки. Если клики снова не работают, изучить событие FrameNavigationStarting/NavigationStarting, маркер выбранного просмотра, членство и диагностические записи без повторного возвращения к JavaScript в письмах.


## 25.10.2026 — план приёмки v0.3.94
Пользователь обновляется штатной кнопкой или MailRuDesktop_Update_v0.3.94.exe, открывает показанную в переписке картинку Excel в основном просмотре и в отдельном окне чтения. Проверяет картинки в нескольких подтверждённых письмах одной переписки, отсутствие запроса чужих аккаунтов, повторное открытие и закрытие окон. При неисправности анализировать только обезличенные mail_image_proxy / mail_image_inline коды, не просить OAuth или содержимое почты. В данном выпуске нет обещаний об импорте картинок в WPF-редактор составления ответа. Если пользователь подтвердит, закрыть I-096, не менять работающий основной предпросмотр без фактического дефекта.

## 25.10.2026 — журнал v0.3.95
Испытать на реальных данных владельца оба журнала (Приложение, Google/PushMe): при успешной и отвергнутой авторизации нескольких ящиков, при открытии письма и загрузке вложенной картинки, при ошибке отправки показывать аккаунт и конкретное действие. Исторические записи могут не иметь достоверного аккаунта — это обозначать, не угадывать. Проверить при копировании и сохранении отсутствие утечки адресов в режиме по умолчанию, явный полный отчёт и режим технических кодов. Не затрагивать рабочие механизмы связи/отправки и не вводить новые API или запросы только ради журнала.


## 25.10.2026 — порядок проверки новой диагностики
Установить v0.3.96, проверить три вкладки в основной панели под письмами, размер/сворачивание и сохранение размеров. Повторить сбой авторизации одного проблемного ящика. Сохранить полный журнал из вкладки приложения и определить по его данным причину HTTP 200 без доступного access_token. Не менять авторизацию до фактического анализа. Проверить копирование и сохранение всех данных без обезличивания.


## 26.10.2026 — приёмка v0.3.97
После обновления приложения штатной кнопкой открыть «Диагностика», проверить неподвижность верхних статусов и нижних кнопок в Google/PushMe, Протокол, Приложение при прокрутке длинных журналов. В «Протокол» осуществить контролируемый вход/загрузку письма, подтвердить наличие URL, метода, точных headers/Set-Cookie, тел запросов и ответов, связи с фактическим аккаунтом; копирование и сохранение должны соответствовать текущему часу. В «Приложение» должны отображаться только записи с 00:00 текущей локальной даты и экспорт того же диапазона. Проверить файлы JSONL и *.bin в LocalAppData; не пересылать ключи, куки, пароли и бинарные тела в публичный GitHub. При неполном теле проверить, было ли оно реально прочитано клиентом. Протокол и механизм авторизации не менять до конкретного свидетельства нового дефекта.

## 26.10.2026 — следующий этап: пользовательская приёмка v0.3.99
Перейти в новый чат; исходное состояние — main fc75509c58572bf4d265ca7bed0c51655105cf3b, опубликован v0.3.99, девять изменений рассылки и обработка ошибок справочника уже находятся в main. Проверить обновление из программы; в окне Excel выбор книги при открытии списка, отсутствие надстроек и скрытых книг, дата завтра, многострочный журнал и его прокрутка, выделение ячейки на текущей видимой строке при работе с несколькими книгами, кнопку «Прервать», отсутствие новых постановок после остановки, точность счётчиков по каждому аккаунту и их сохранение между запусками/сброс, ошибки записи Excel, явный отказ сервера и неполучение ответа с безопасной остановкой, ошибку сервиса ИНН с остановкой и нормальное отсутствие названия с fallback на колонку работодателя. Контролировать отсутствие повторной постановки неопределённого задания и различать серверное принятие от Excel-отметки. Для v0.3.98 отдельно остаётся живая проверка входа после CAPTCHA и смены пароля, защищённого сохранения пароля, WebView2-протокола. Локальные сетевые журналы могут содержать секреты, не публиковать их открыто.

## 26.10.2026 — план после официального выпуска v0.3.100
Точка продолжения: main cfb215e220c976bc5ba83cac38d41ec1fc63e527, Release https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.100, CI PR 40316071859/main 40316475420 и официальный выпуск 40316734601 успешны. Предложить пользователю установить обновление штатной кнопкой или MailRuDesktop_Update_v0.3.100.exe. Проверить в реальном приложении состояние каждого аккаунта и лампочки, уровень начала списка папок, адрес активного аккаунта, предзаполнение и показ/скрытие пароля, смену пароля в WebView2 без повторного окна, выдачу новых клиентских ключей и подтверждённый доступ к папке. Особое внимание: запрос browser restore/confirm содержит password; пароль использовать только для продолжения авторизации и сохранять проверенным в DPAPI. Сетевой журнал НЕ сокращать и НЕ очищать по явному требованию владельца; сам журнал не публиковать в открытый репозиторий. До пользовательской приёмки не объявлять реальный восстановительный сценарий доказанным. Следующие изменения согласовывать на основании конкретного результата приёмки.

## 26.10.2026 — дальнейшие действия после v0.3.101
Установить официальный v0.3.101 (Update или Setup) на первый компьютер; открыть «Настройки» → «Экспортировать настройки», создать защищённый .mrbackup. На втором компьютере использовать «Импортировать настройки», ввести пароль, проверить пять групп с флажками и выборочных импортируемых аккаунтов/шаблонов/подписей; отдельно проверить сохранение уже авторизованных местных аккаунтов, правильные разделы, отсутствие чужих токенов, самостоятельную AJ-авторизацию новых аккаунтов с 2FA/CAPTCHA при необходимости, вложения и профили Excel. При ошибке проверить локальную копию в %LocalAppData%\MailRuDesktop\Backups, не уничтожая оригинальную диагностику. Приёмка реального сценария ещё не получена.

## 26.10.2026 — дальнейшие действия после исправления PR #233
Исходное состояние: main b65c1182e7d784b4be7342da2c7dc25f83699f68, версия 0.3.102 в проекте, PR #233 merged. Дождаться успешного Windows CI основной ветки и выпустить через штатную задачу [BUILD_RELEASE] Setup/Update v0.3.102. Проверить наличие двух установщиков. Предложить владельцу проверить импорт из открытого окна «Настройки»: после подтверждения оно закрывается автоматически; импорт и перезапуск выполняются. При открытых окнах сообщений/рассылки предупреждение сохраняется. Не изменять пароли, очередь авторизации, токены и перенос аккаунтов: владелец подтвердил корректное поведение.

## 26.10.2026 — после выпуска v0.3.102
26.10.2026: официальный выпуск MailRu Desktop v0.3.102 завершён; CI main 40343500826 SUCCESS, выпуск Actions 40343842728 SUCCESS, issue #234 закрыт. Доступны https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.102 , MailRuDesktop_Setup_v0.3.102.exe и MailRuDesktop_Update_v0.3.102.exe. Кодовое изменение PR #233 / main b65c1182 — автоматическое закрытие окна «Настройки» до записи импортированной конфигурации, без изменения авторизации. Требуется пользовательская проверка именно этого окна; прочие функции не изменялись. Следующее действие: пользователь устанавливает Update и выполняет импорт при открытом окне «Настройки». Никакой дополнительной доработки паролей, очереди или авторизации не требуется.

## 26.10.2026 — план приёмки v0.3.103
Основной код main dcc58b666e6b4efc00c4604ecf8a9c7f15d0889f, официальный выпуск https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.103 . После обновления провести четыре проверки подтверждений без необходимости завершать удаление: аккаунт с закрытыми настройками, аккаунт с открытыми настройками, импортированная подпись в открытых настройках, созданная позднее подпись. Убедиться, что подтверждение видно и его можно отменить. При оставшемся зависании не повторять правки без диагностики фактического окна и журналов. Механизмы импорта/паролей/авторизации не изменять.

## 26.10.2026 — дальнейшая приёмка
Установить официальный v0.3.104, выполнить импорт .mrbackup из открытого окна настроек, подтвердить замену и проверить закрытие окна, резервную копию, применение настроек и перезапуск. Повторно переносить пароли или править механизм авторизации не требуется. При проблеме анализировать только конкретную ошибку.

## 26.10.2026 — план проверки v0.3.105
Установить официальный Update v0.3.105, открыть окно Настройки → Подписи → несколько раз вызвать подтверждение «Удалить» и отменить; проверить фон, текст, доступность и стабильность. Повторить с контекстным меню аккаунта. Никакой повторный импорт не нужен: владелец уже подтвердил, что в v0.3.104 импорт полноценно работает. Если дефект повторится, собрать экран, условия (масштаб, тема, окно, сколько раз) и не менять подтверждённые процедуры удаления/импорта.

## 26.10.2026 — план приёмки v0.3.106
Обновить установленный клиент из официального выпуска. В тестовой Excel-рассылке на контролируемых строках воспроизвести явно недоступного получателя: в журнале должна появиться строка, адрес и recipient_disabled; отправка остальных строк продолжается, окно ошибки не возникает. Отдельно проверить квоты при 49/50, 50/50, 51/50: новые отправки допускаются только в пределах оставшейся ёмкости. При неопределённом ответе операцию не повторять автоматически, резерв квоты сохранять. Обязательно сохранять прежние механизмы импорта и авторизации.

## 26.10.2026 — дальнейшие действия после официального выпуска v0.3.107
Установить Update v0.3.107. Проверить при реальном 403 token журнал: запись отклонения, автоматический refresh, GET списка писем со статусом 200, ровно один повтор того же письма, подтверждение отправки и продолжение очереди; сравнить счётчики и квоты. В сценарии ошибки обновления рассылка останавливается без повторного POST. Пароли и ручную авторизацию не менять.

## 28.10.2026 — план диагностики новых замечаний без преждевременного изменения программы

Первым этапом после указания владельца на реализацию: локализовать редактор форматированного текста и механизм вставки, проверить сохранение стека Undo после вставки и горячие клавиши. Затем проверить, почему отдельное окно чтения влияет на события контекстного меню аккаунтов (окно/владелец/модальность/фокус). В пересылке проследить путь получения оригинального HTML и его преобразование в формат редактора; сохранять безопасные стили, структурные цитаты и ссылки, а не подменять форматирование обычным текстом. Перед выпуском проверить ручные и автоматические сценарии, включая Ctrl+Z, активное окно письма и несколько типов HTML. Новая версия продукта не назначена, изменения в коде ещё не сделаны.

## 28.10.2026 — план пользовательской приёмки официального v0.3.108

После обновления на Windows проверить: 1) Ctrl+Z сразу после вставки фрагмента с обычным и форматированным текстом, включая новое письмо/ответ/ответ всем/пересылку/редактор шаблона; 2) с открытым отдельным окном письма выполнить правый клик по аккаунту/разделу и доступ к контекстному меню главного окна; 3) переслать конкретное HTML-письмо (включая сообщение из диалога), визуально сопоставить цитаты, жирность/курсив, размеры/цвета шрифта, ссылки, списки/таблицы, затем проверить сохранённый черновик или отправленное тестовое письмо на собственный адрес; 4) проверить обычный ответ и ответ всем, повторную отправку отложенного письма и выбор/смену подписи/шаблона. Не считать сохранение произвольных встроенных CID-изображений и сторонних CSS доказанным. При новых дефектах взять локальный диагностический материал без секретов; не переписывать исправленную Excel-рассылку. Не проводить реальную отправку автоматически за пользователя в рамках приёмки.

## 28.10.2026 — результат плана приёмки v0.3.108

План практической проверки из предыдущего поколения признан выполненным на основании прямого сообщения владельца «всё работает». Три пользовательских дефекта закрыты, успешность подтверждена владельцем после получения v0.3.108. Дополнительные самостоятельные тесты на любые внешние стили, изображения CID и полный диапазон сложного HTML не утверждаются. Для новых дефектов создавать отдельное обязательство на основе фактического описания, не переоткрывать без причины закрытую приёмку.

## 28.10.2026 — следующий этап после официального v0.3.109
Получить практическую приёмку на Windows: адрес активного аккаунта полностью виден в заголовке при обычной ширине левой панели; при искусственно/естественно возникшем сбое справочника в Excel-рассылке журнал показывает один повтор через 2 с, после второго сетевого отказа — использование «Наименование работодателя» и продолжение постановки без дублирования прежних подтверждённых писем. Если название отсутствует и в Excel, остановка явно сообщает причину. Не инициировать повторную рассылку уже отмеченных строк, не сбрасывать счётчики и не менять подтверждённые авторизацию/HTML-редактор. Не объявлять реальные сетевые сценарии принятыми без пользовательских наблюдений.
