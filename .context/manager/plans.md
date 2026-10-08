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
