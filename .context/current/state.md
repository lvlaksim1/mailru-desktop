# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: **v0.3.5**
Released product-code baseline: `4b5ed4e629112a9151c3659afd6ca408a828d9b5`

`main` may later contain context-only commits; do not interpret those as a newer released product build.

## Product state

MailRu Desktop is a multi-account native Windows mail client with mailbox OAuth authorization and a theme-aware three-pane interface.

### v0.3.4 parser hotfix

v0.3.4 fixed the second-account regression where folders/counts loaded but messages were absent because Mail.ru returned message data through `body.folders_content[].threads[].representations[]`.

The released parser now preserves all known smart-thread variants:
- `threads[].base_message`;
- `threads[].messages[]`;
- direct thread-like objects;
- `threads[].representations[]`, using `message_id_last` when required.

CI and release packaging passed. Owner runtime validation on the exact second account that exposed the variant remains an explicit verification gate.

### v0.3.5 mail-list redesign

v0.3.5 was merged through PR #36 and released after green pull-request checks and green `main` CI.

The message list now follows the Owner-provided compact reference:
1. fixed-width read/unread indicator column with a fixed-size hollow/filled dot;
2. sender name, falling back to sender email;
3. flag icon;
4. message count for a thread when available in the actual Mail.ru response;
5. subject plus first non-empty text/snippet line;
6. blank attachment column or paperclip when attachments exist;
7. time.

Messages are grouped by local calendar date. Each date is a separate header followed by that date's messages.

Unread presentation:
- the dot is filled;
- the fifth-column subject/text line is bold.

Opening a message continues to mark it read automatically. v0.3.5 also exposes `Не прочитано` for the selected message so the user can explicitly return it to unread state.

The implementation uses existing dynamic application theme resources and does not introduce a separate fixed light/dark color scheme.

## Validation evidence

- PR #36 CI run `37567961454`: success.
- PR #36 repository-storage policy run `37567961514`: success.
- merged `main` CI run `37568041502`: success.
- release workflow run `37568123580`: success.
- GitHub Release `v0.3.5` contains both `MailRuDesktop_Setup_v0.3.5.exe` and `MailRuDesktop_Update_v0.3.5.exe`.

These checks prove build/startup/release integrity. They do not substitute for Owner visual/runtime validation of layout details, thread counts, or the second-account parser behavior.

## Authorization/runtime state

- API scope is defined by the same mailbox OAuth credential `ru.mail.oauth2.access`, not by a fixed host list.
- CAPTCHA/reCAPTCHA/additional verification stops authorization; no challenge solving or bypass.
- No IMAP/SMTP, app passwords, or independent web-cookie fallback.
- Saved `refresh_token` recovers an expired `access_token` through `https://o2.mail.ru/token`.
- Normal runtime has no fixed five-second delay. Research/probe/test requests keep at least five seconds spacing.

## Stable product policies

- Release updates as `MailRuDesktop_Update_vX.Y.Z.exe`; full installer remains available for first install.
- Keep only the latest binary release where release-pruning policy applies.
- Research/test traffic must not look like DDoS; keep at least five seconds between repeated network requests.
- Do not replace known Mail.ru response variants with one assumed canonical schema.
- Folder `last_modified` behavior remains unchanged unless explicitly authorized.
