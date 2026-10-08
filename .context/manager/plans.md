# Manager plans

## Protocol plan

1. Keep API eligibility based on the same mailbox OAuth credential, not hostname.
2. Keep protocol changes isolated inside MailRuDesktop.Protocol.
3. Treat APK evidence as static evidence; newly enabled operations need controlled live or Owner-runtime validation.
4. Preserve at least five seconds between repeated research/probe/test network requests. Normal application runtime has no artificial fixed delay.
5. Preserve the v0.3.17 persistent isolated authorization profile for each account. Do not return to a fresh profile for every verification and do not share these profiles with normal desktop browsers.

## Current product baseline

Latest public GitHub Release: v0.3.19, published on 2026-10-08.

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
