# Current state

Repository: lvlaksim1/mailru-desktop
Visibility: public
Product authority: main
Manager-state authority: main

Latest public release: **v0.3.25**, published 2026-10-08 with full and update installers; PR #83 merged as e89c5ed2ef426e3def7957663b17254c66f44892; CI 37771307863 and installer workflow 37771458709; release workflow 37768430779 success, main CI 37768224358 and storage policy 37768224319 success. Product PR #76 merge bd6ff6ff98f93a69458efc921e7c35a21e159afc, main CI 37719927017, storage 37719927127, installer workflow 37720023234: success. PR #73 merged on main as e29a23ab81697b0dd38f0f1b2d9ce38efefb8c52. PR #70 merged as eb3415ac30f81cc3426d4726b53d590d42e57b11. CI 37711634109 and release workflow 37711732617 succeeded.
Product changes: PR #62 plus PR #63, merge commit 34e583abc232c9edc939816a92b4e08165836210.
Release workflow 37707435464, main CI 37707321540 and repository storage check 37707321584: success.

## Authorization state

v0.3.17 introduced a persistent browser profile isolated per mailbox account for Mail.ru interactive authorization. Owner runtime on 2026-10-08 confirmed the important problem case: instead of forcing password recovery, Mail.ru requested phone confirmation inside the isolated window and authorization completed successfully without changing the password.

This is now the stable authorization architecture:
- account profiles persist across application restarts;
- profiles do not share cookies with normal desktop browsers;
- profiles are isolated from other MailRu Desktop accounts;
- no CAPTCHA solver or verification bypass;
- do not revert to a disposable profile per verification.

## Mail list and multi-account baseline

The parser remains tolerant across base_message, messages[], direct thread-like objects and threads[].representations[].

Current compact message rows use the established seven-column order:
1. time;
2. flag;
3. fixed-size read/unread dot;
4. thread message count;
5. attachment indicator;
6. sender name with email fallback;
7. subject plus first text line.

Messages remain grouped by date, with pinned messages in their separate section when present. No first message is automatically selected/opened merely because the list loaded.

## v0.3.18 merged interface bundle

PR #62 is merged to main. Main CI run 37703044365 and repository-storage-policy run 37703044638 passed.

Implemented:
- corrected saving/restoring of normal versus maximized window state;
- layout state is saved before window destruction;
- message-list/preview divider persistence no longer gets overwritten by the older compatibility layer;
- all seven user-adjusted message-column widths remain part of saved settings;
- dark-theme scrollbar arrows/thumb use a darker, lower-contrast palette;
- compact 16 px local sender/account icons are placed left of sender/account text without increasing row height;
- persistent account ordering;
- account sections/headings, with the expand/collapse arrow on the right of the section name;
- section rename/delete; deleting a section does not delete its accounts;
- account section expanded/collapsed state persists;
- account movement uses a 275 ms hold before detaching; the mouse stays pressed during the drag; the detached row follows the pointer; neighboring rows animate out of the way; release fixes the position; Escape is not part of the interaction;
- preview controls were rearranged per Owner request;
- reply/forward editor appears above the message body;
- Settings contains Signatures and Templates;
- signatures are named, editable and removable;
- message templates are named and contain subject, body and local attachments;
- reply editor contains Signature and From template selectors;
- selecting a signature appends/replaces the chosen signature at the end of text;
- selecting a template populates subject, body and available attachments.

## v0.3.18 release additions from PR #63

- Genuine sender/account images are requested from Mail.ru filin /pic?email (source: official APK URL strings), with initials as fallback when the photo fails to load. Photo availability in real accounts is not yet verified by Owner runtime.
- Each template is now a separate filename-equals-name Markdown file under %LOCALAPPDATA%/MailRuDesktop/Templates. Manually created files appear automatically through directory watching, and the list also refreshes when Settings or the reply template selector opens.
- Plain Markdown is valid; optional front matter stores subject and attachment references. Attachments selected in Settings are copied to Templates/_attachments. Legacy settings.json templates are migrated once without deleting the old backup.
- Insertion requires no confirmation. Background file refresh does not reinsert the template into an in-progress reply.
- Releasing a long-pressed account without moving it no longer changes account order.

## Release state

v0.3.18 is published. CI and release packaging passed. Owner runtime/visual verification of the listed interactions remains outstanding.

## Stable project policies

- Scope is based on the mailbox OAuth credential, not a fixed Mail.ru host list.
- No IMAP/SMTP or app passwords.
- Update releases use MailRuDesktop_Update_vX.Y.Z.exe; full installer remains for first install.
- Repeated research/probe/test network requests keep at least five seconds spacing.
- Normal application runtime has no mandatory five-second delay.
- Do not collapse known Mail.ru response variants into one assumed schema.
- Keep folder last_modified behavior unchanged unless explicitly authorized.

## v0.3.19 release — account drag and outgoing mail

PR #67 merged as product commit 3640b7e56e8059777d878d9f67f4f8d6bf01c32a. Main CI 37709503446, storage policy 37709503443 and release workflow 37709585421 completed successfully. Installer version is 0.3.19.

- Account dragging now calculates destination from unanimated row centers. Downward/upward destination computation is deterministic; neighbor transforms do not feed back into hit geometry. Short click is tied to the original pressed row; release after pointer drift does not switch to an unintended account. Animated transforms are cleared when committing the drag.
- New-mail compose has a read receipt request checkbox, backed by the official client's boolean POST parameter receipt. With checkbox enabled, receipt=true is sent; unchecked leaves the previous wire payload unchanged.
- The existing scheduled send checkbox now has tomorrow (local current date + one day) at 09:00 as its default, date/time controls gated by the checkbox, refreshed on new compose entry and reset after success. The existing /messages/schedule endpoint is retained.
- Sender display shows the email address when the name is absent/whitespace. Thread response parser now tolerates array, object and string variants for correspondents.from/direct from.
- Offline deterministic regression checks were added to CI for bidirectional target calculation, sender fallback and outgoing receipt/scheduling parameter serialization. They send no real mail.
- Release/build/test evidence is NOT live proof that Mail.ru honors read receipt requests or scheduled delivery, nor a substitute for Owner's hands-on dragging validation.

## v0.3.20 — Owner feedback and implementation

Owner confirmed account drag animation and pane dividers now work well; do not change those mechanisms without new evidence.

Addressed eight new feedback items:
1. Configurable template directory under Settings, persisted in settings.json. The app copies existing Markdown templates, attachments and metadata without overwriting target files; retains source as backup; rebinds file watcher and immediately discovers files in the selected directory.
2. Re-selecting an already selected signature or email template re-applies the item through mouse/keyboard activation instead of depending on SelectionChanged alone. Repeating a signature appends it again; repeating a template re-populates subject/body/attachments.
3. Inline reply/forward form has a top-right close ×.
4. Section-title editing dialog reapplies native dark titlebar on source initialization, activation and load.
5. Dark WPF scrollbar templates fully own horizontal/vertical thumb and arrows. HTML/WebView message viewers (inline and detached) also get dark scrollbar CSS.
6. Sender fallback: parser uses correspondence data from the parent thread if a base_message omits it. When loading full selected mail, known FromEmail and FromName update the displayed row (not guessed). Name absent -> email.
7. Inline reply/forward form has the read receipt request checkbox; the existing outgoing receipt POST flag is transmitted.
8. Inline reply/forward form has server-side scheduled send controls, defaults to local tomorrow 09:00 and checks user input.

CI tests for thread-only sender and file-backed template migration, attachments, manual file discovery, and conflict protection passed on Windows. Automated tests + successful launch are not equivalent to Owner UI confirmation or successful real scheduled delivery/read receipts.

Release: v0.3.20 with full and in-place update installers. Both installer artifacts were verified on GitHub.

## v0.3.21 — fixed role-based interface palette

The Owner approved a complete taxonomy, not arbitrary per-widget colors. Implemented exactly 26 semantic color roles in ThemePalette.Roles, with immutable key→element responsibility, names, descriptions and separate default dark/light colors. The Owner can edit only values, not the element mapping.

Settings → «Цвета элементов интерфейса»: grouped list of all 26 roles, color previews and descriptions, text input #RRGGBB, RGB chooser, per-role reset, per-theme reset with confirmation, contrast warnings without forced correction, immediate live application of the active theme. Independent persisted DarkPalette and LightPalette in settings.json; the system theme selects the appropriate one for current Windows mode.

ThemeManager applies all 26 resource brushes, synchronizes window titlebar and message viewer with effective roles. Existing WPF styles, dialog windows, context menus, input focus, stars/pins, destructive actions, mail sending success/error indicators, and embedded HTML scrollbars have been mapped to role resources. Images, contact portraits and logos are not recolored.

Offline regression checks verify role uniqueness/count, both default sets, validation/normalization of color codes, contrast calculation, isolated storage persistence and independent reset. Windows CI, startup smoke and release passed. Owner visual confirmation of all windows/menus and color combinations is pending.

No code changes were made to Owner-approved account drag/animation and splitters. Other unfinished mail features from the 12-point backlog are not included in v0.3.21.

## v0.3.22 — Owner-approved mail backlog and palette interaction

Two palette changes: grouped role editor in Settings is collapsed by default; color selection uses a click-to-choose 2D hue/saturation palette and vertical brightness strip instead of three RGB sliders. Hex input and the fixed 26-role per-theme contract remain.

From the 12-point mail backlog:
1. Missing sender email/name now starts automatic targeted full-message lookup on folder load, capped to 20 missing senders with 5-second gaps, always markRead=false and cancelled when switching account/folder. Parent-thread and selected-message sender fallback remain. This fixes dependence on manually opening messages but Owner live verification is still necessary.
2. Markdown subject/attachment metadata is saved as directly readable Cyrillic UTF-8 with BOM. On reading older escaped metadata, the app migrates the metadata after closing the read stream, preserves the message body and writes a backup. Regression tests pass.
3. Configurable per-theme semantic colors were delivered in v0.3.21; this release changes expander and picker as described.
4-5. User-selectable attachment download directory is persisted, defaulting to Downloads; after successful single/archive save, Windows Explorer opens the containing folder. The setting also applies in the detached message viewer.
6. Attachments and download buttons have been moved above the embedded message viewer (the detached viewer already displays attachments at top).
7. New-mail, inline reply/forward and detached reply/forward use a shared rich editor with toolbar (bold, italic, underline, strike, lists, alignment, links, text color). It synchronizes plain text for signatures/templates and serializes formatted HTML plus plain text into outgoing requests. Automatic compilation/startup succeeded, actual formatted delivery needs Owner test.
8. Leftmost checkbox column, Ctrl/Shift selection, Ctrl+A and multi-select count bar added; clicking the checkbox no longer automatically opens or marks read.
9. Bulk toolbar has Trash, Archive and Read buttons, using a single array-based server move or grouped marks request, with repeat-submit protection.
12. When viewing Trash, a permanent bulk-delete button uses /api/v1/messages/remove with explicit confirmation.

**BLOCKED AND NOT DONE:** #10 and #11 immediate send of already scheduled messages. Buttons appear disabled in the Outbox bulk bar, individual preview and context menu. Official APK confirms /messages/send, /schedule and source.schedule metadata but not a validated atomic transition that cancels/resuses a scheduled item without duplicates. Unsafe conjecture must not be enabled. Issue #77 tracks controlled protocol validation. Do not announce all twelve complete.

PR #76, main CI 37719927017 and Windows release workflow 37720023234 passed. Owner GUI/runtime validation of mail selection and actual HTML delivery remains pending. Account dragging/splitters were not modified.


## v0.3.23 — saved account token rejection fix

Owner reported that previously authorized accounts showed empty Inbox and "Авторизация активна" while protocol diagnostics displayed JSON {"status":403,"email":"","htmlencoded":true,"body":"token"}.

Root cause proven in source: MailRuClient.GetFolderThreadsAsync checked the HTTP transport status only; Mail.ru may send HTTP 200 with embedded JSON status 401/403. That JSON error was sent to the thread parser as an empty mailbox. The refresh path handled HTTP status only and unread counts for inactive mailboxes never tried per-account refresh.

PR #80 merged as afdf7572953e79c6860bf6fb1a2c5fb93fea544a. MailRuProtocol now throws MailRuAuthorizationException for transport or embedded JSON 401/403 in folder and full-message responses. Active-account recovery uses its own refresh token, reads the folder with the candidate new access token FIRST, and persists only after proof of access. Background unread counters use independently stored account tokens; each invalid account may refresh without switching LastLogin or mutating other accounts. Failed refresh preserves saved credentials and informs Owner that this specific account requires login, rather than falsely claiming active authorization. No sensitive credentials are logged.

Offline Windows regressions cover HTTP200/JSON403, HTTP403, JSON string 401, normal response and two-account encrypted credential persistence/isolation. Build, smoke, storage policy and release passed. Actual Owner mailbox tokens are NOT accessible in CI, so runtime recovery must be Owner tested; some server-invalid refresh credentials may still require interactive sign-in. Do not erase auth.json or require all accounts to sign in again.

Outstanding "Отправить сейчас" for queued mail stays blocked on server-protocol validation (issue #77); other v0.3.22 Owner runtime items remain.

## Owner validation of saved-account recovery

Owner explicitly confirmed after installing v0.3.23: "всё починилось!" The original HTTP200/JSON403 token and multiaccount login issue is therefore CLOSED by Owner runtime verification.

## v0.3.24 — fixes for Owner-reported GUI regressions

Owner reported: palette buttons clipped, delayed-send datepicker calendar white in dark theme, checkboxes losing selection when background row data changes, batch actions visibly reloading the entire message list, excessive Enter paragraph spacing in formatted composer, inability to resize reply editing area, and unwanted confirmation when permanently removing mail from Trash. Owner already confirmed rich-formatted delivery succeeded and other v0.3.22 enhancements worked.

PR #83 delivered:
- Resizable/scrollable color picker with accessible action buttons;
- explicitly themed DatePicker popup CalendarStyle;
- zero paragraph margins in WPF rich editor and sent HTML;
- draggable reply editor lower boundary in inline and detached windows;
- stable selected message ID set, retained across row replacements, with removal only for actual removed records;
- batch mark-read, archive, Trash and permanent deletion update only impacted rows, no full folder reload; stale account/folder completion guard;
- no permanent-delete confirmation;
- parse original smart-thread JSON's additional representations of the *same* message for missing correspondent data, including blank-subject letters, before resorting to extra full-message lookup.

Windows CI and offline regression, including blank-subject corresponding-message sender parsing, succeeded, release published. Owner GUI runtime verification is still pending for v0.3.24. Some mail summaries truly omit all sender data and need full message; eliminating ALL staged lookups is NOT yet verified. Do not claim this is fully solved without Owner testing. Scheduled Outbox send-now (#10–11) is still blocked by issue #77, no verified duplicate-free server transition.

## v0.3.25 — systematic UI register, font controls and independent mail targets

Owner accepted explicit action-target precedence: all checked messages if any; otherwise the single active mail shown in preview; if neither exists, show "Выберите письмо для выполнения действия". The active preview must not be conflated with selection checks. Shift-click on checkbox adds a visible range; checkbox clicks toggle any arbitrary row, and Ctrl multiselection is removed. A normal click on a row may open its preview without changing checked messages; double-click opens detached viewer. Archive/trash actions in toolbar, preview and context menu use shared target resolution.

Implemented and published PR #86 merged main 2f25612777b30a15c9f3c1d979d1758ad9b6b5cb: MailTargetResolver, independent MailPreviewState accent border, IDs stored separately from displayed row objects; grouped operations update only confirmed affected rows. Live unread folder counts update after mail read/move/delete and are periodically refreshed every 90 seconds, with explicit JSON counter parsing: a missing value NEVER means zero. Existing owner-accepted no-whole-folder reload remains.

Full interface inventory lives in docs/ui-registry.json schema 2 and docs/ui-registry.md, generated by tools/ui_registry.py. Current inventory: seven real Windows windows/dialog types, 1349 UI/style/template nodes including application resources App.xaml and ThemeStyles.xaml; exactly 26 known palette color roles, no unresolved role references. CI checks registry drift, so new/changed UI requires regenerating. Window/element geometry, parent, color/typography references and interaction states are recorded; computed runtime geometry remains declarative rather than invented fixed coordinates.

Colors of left navigation, including Mail button hover/press/focus, are bound to semantic palette resources, removing pale system-cyan hover. Settings have persisted interface font size slider 10–18 logical WPF pixels and nine fixed derived typography roles applied to current and future windows; incoming email HTML remains independent.

Color-picker oversized blank area reduced. Initial sender uses same-message compact representations, then bounded first missing-sender full request with up to 3-second wait (only one request), and still uses paced >=5-second background enrichment for remaining truly absent metadata.

CI build/smoke/offline logic tests 37789612268 and storage policy 37789612042 passed; release workflow 37789849920 passed; both v0.3.25 installer binaries published 2026-10-08. Owner Windows GUI validation pending. Outbox scheduled send-now (#77) remains BLOCKED due no proven atomic cancel/reuse and no duplicate-free server test. Do not claim 14-point request fully closed.
