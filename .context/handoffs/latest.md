# Latest handoff

Latest public release: v0.3.23, published with full and update installers on 2026-10-08.

Current merged product baseline: PR #62 and PR #63, main CI passed, release workflow 37707435464 succeeded.

Authorization baseline: persistent isolated browser profile per mailbox account. Owner confirmed phone verification and successful authorization without password recovery. Preserve this architecture.

Merged interface work includes window and layout persistence, darker dark-theme scrollbars, compact sender and account icons, persistent account ordering, named account sections, animated account movement, preview and reply layout changes, signatures, and message templates.

Account movement: hold 250 to 300 ms, implementation uses 275 ms. The row detaches while the mouse remains pressed. Neighboring rows move smoothly. Mouse release fixes the new position. Escape is not used.

Account sections: expand/collapse arrow is to the right of the section name. Section state and account membership persist. Deleting a section does not delete its accounts.

Additional released changes: Mail.ru filin photo URLs with initials fallback; Markdown templates sourced from manually editable Templates/*.md files, attachment copies, legacy migration and automatic refresh. Stationary long-press no longer reorders accounts. No confirmation on template insertion.

Next: Owner checks the complete v0.3.18 interface, actual avatar availability and file-based templates in Windows runtime before unrelated feature expansion.


## v0.3.19 handoff

PR #67 merged to main (3640b7e56e8059777d878d9f67f4f8d6bf01c32a); main CI, offline interaction regression checks, startup and storage policy succeeded. Release workflow 37709585421 published both installers.

User issues addressed: down/up account dragging with stable centers, mouse press-to-release avoiding accidental selection of the row below; optional read-receipt request (bool receipt=true), scheduled send default tomorrow 09:00, and sender name absent -> email address fallback. Static APK source confirms receipt boolean; scheduled send still uses existing /api/v1/messages/schedule and send_date. Unchecked receipt leaves previous payload unchanged.

NEXT: Owner Windows runtime testing for actual drag interaction, sender display and scheduling/delivery; do not claim receipt notification is guaranteed. Preserve v0.3.18 Markdown file templates and real-photo fallback work.

## v0.3.20 handoff

Owner provided two screenshots and reported issues with custom template location, reapplying selected signature/template, closing inline reply, section-name modal dark titlebar, light scrollbars, missing sender email in list, and absent inline reply/forward send receipts and schedule controls. Owner explicitly confirmed account dragging and dividers work perfectly; preserve them.

PR #70 merged as eb3415ac30f81cc3426d4726b53d590d42e57b11; CI 37711634109, storage policy 37711634121 and release workflow 37711732617 succeeded. Published release tag v0.3.20 with full and update installers.

Implemented: choose and persist template directory; copy templates/attachments without collision/overwrite; restart watcher; repeat selected signature/template on mouse/Enter; inline reply ×; dark modal native caption correction; fully custom WPF scrollbar thumbs/arrows plus both WebView reader CSS; parent-thread and full-message sender-email fallback; receipt and scheduled-send controls in inline reply/forward with tomorrow 09:00 default.

Regression tests include manual Markdown addition, directory change, attachment continuity/conflict rejection and parent-thread sender. Runtime UI and mail delivery validation still pending. No network spam; keep >=5 sec research/test request spacing. No IMAP/SMTP/app-passwords.

## v0.3.21 handoff — semantic palette project

Owner confirmed exact design contract: user controls separate color values, not which widgets belong to each role; developer owns all sets and role combinations. Approved 26-color role model (6 backgrounds, 4 interaction, 5 text, 3 accent/selection, 3 scrolling, 3 statuses, 2 marks). The system theme must use the matching light/dark palette; no third contradictory palette.

Merged PR #73 commit e29a23ab81697b0dd38f0f1b2d9ce38efefb8c52. v0.3.21 full/update installers published and release workflow 37716328414 succeeded. Windows CI and offline regression tests passed.

Technical contract: ThemePalette.Roles = 26 immutable semantic roles; ThemeManager.Apply writes all dynamic WPF brush keys, native caption, message viewer and scrollbar colors. DarkPalette/LightPalette stored separately in settings.json; color entries accept only #RRGGBB. Settings contains grouped role descriptions/swatches, RGB picker, role/theme reset and accessibility warnings; light/dark editor selection is independent from current Windows system mode. Backgrounds, inputs, dialogs, marks and message send state colors share semantic roles; original pictures and brand marks must remain intact. Documentation: docs/theme-palette.md.

Owner runtime/visual verification pending. Keep previously approved account drag and splitters unchanged. Other 12-task backlog remains separate and unfinished.

## v0.3.22 release handoff

Owner asked to collapse color settings and replace RGB sliders with a clickable 2D palette, and to complete the 12-point mail backlog. PR #76 merged to main bd6ff6ff98f93a69458efc921e7c35a21e159afc, main CI 37719927017, storage policy 37719927127, release workflow 37720023234 succeeded. Both installer assets are published.

Implemented: color section starts collapsed, hue/saturation click area with brightness strip; automatic bounded missing-sender full-message lookups without changing unread mark; readable Cyrillic UTF-8 BOM template metadata with migration of old escaped metadata and backup, while preserving message body; chosen attachment download directory, Windows Explorer after successful single/zip save; incoming attachment section above message body; common rich text toolbar in new, inline reply/forward and detached reply/forward, with synchronized legacy plain text and outgoing formatted HTML; checkbox/Ctrl/Shift/Ctrl+A multi-selection; grouped trash/archive/read requests, stateful prevention of repeated operations; grouped permanent Trash deletion with confirmation.

**Not completed:** #10-11 actual Send Now on an already scheduled message. Research confirms /messages/send, /schedule, source.schedule but not the specific safe atomic transition. Disabled Outbox UI entry points are present only as a scaffold; issue #77 tracks controlled testing. Do not attempt copy-and-send or promise that scheduling is cancelled. No real outgoing email was sent in this work.

Automated Windows tests passed including Cyrillic migration, data backup, and batch marks. Owner GUI and recipient HTML display remain unverified. Do not regress account dragging and splitter animation.

## v0.3.23 — authorization hotfix handoff

Owner screenshot: previously authorized accounts had no list messages and false "Авторизация активна" in status, response {"status":403,"email":"","htmlencoded":true,"body":"token"}. Investigation proved a transport/application status mismatch: MailRuClient checked only HTTP status but the API sometimes sends HTTP 200 with internal status 403; the thread parser then returned an empty Inbox.

PR #80 merged to main as afdf7572953e79c6860bf6fb1a2c5fb93fea544a. CI 37768224358 and storage 37768224319 passed. Release workflow 37768430779 successfully published both installers under v0.3.23.

The protocol now recognizes internal JSON status 401/403 and throws a distinct token-rejection exception without writing credentials to logs. Active account refresh retries mailbox read and saves only proven working credentials; unread-counter checks use independent credentials and refresh tokens per account, preserving LastLogin and other logins. Rejected or missing refresh tokens require login to the affected account only, preserving auth.json. No live test using Owner accounts has been run.

Next: Owner Windows runtime check for previously broken accounts and account independence. Do not claim server-issued tokens can always be refreshed. The scheduled Outbox "Отправить сейчас" feature remains blocked until validated duplicate-safe semantics, issue #77. Other 12-point list UI improvements remain intact.
