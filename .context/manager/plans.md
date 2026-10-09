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
