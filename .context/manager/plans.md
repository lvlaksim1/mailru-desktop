# Manager plans

## Protocol plan

1. Keep API eligibility based on the same mailbox OAuth credential, not hostname.
2. Keep protocol changes isolated inside MailRuDesktop.Protocol.
3. Treat APK evidence as static evidence; newly enabled operations need controlled live or Owner-runtime validation.
4. Preserve at least five seconds between repeated research/probe/test network requests. Normal application runtime has no artificial fixed delay.
5. Preserve the v0.3.17 persistent isolated authorization profile for each account. Do not return to a fresh profile for every verification and do not share these profiles with normal desktop browsers.

## Current product baseline

Latest public GitHub Release: v0.3.23, published on 2026-10-08.

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
