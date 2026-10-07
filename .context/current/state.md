# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: **v0.3.3**
Released product-code baseline: `d5c9e25cae00015a0bcab71711ac9d9b84d3dbbf`

`main` may contain later context-only commits; do not interpret those as a newer released product build.

## Product state

MailRu Desktop is now a multi-account native Windows mail client with a dark-first UI and mailbox OAuth authorization.

Released evolution after v0.2.0:

- **v0.3.0** — major modern-UI rebuild: dark theme by default, three-pane mail layout, right-side account rail, server unread counters, hardened startup loading, explicit account states, and automatic OAuth access-token refresh through `POST https://o2.mail.ru/token` using the stored `refresh_token` and `client_id=mail-android`.
- **v0.3.1** — owner-driven UI polish: compact textual account list with resizable width, per-message unread/flag/pin/archive actions, auto-read on open, interactive filters, full-width compose, removable outgoing attachments, reply attachments, selectable addresses, conditional attachment-download UI.
- **v0.3.2** — compact three-line message rows: date/time first, sender/subject/snippet second, pin/archive actions third; preview pane became the main full-message workspace; separate double-click window removed from the normal path; unified text search; account badge explicitly represents Inbox unread count.
- **v0.3.3** — current release: improved pin icon, yellow active star, red active pin, pinned-first chronological ordering, resizable message-list/preview split, right-edge action column behavior, restored date-range filter, dark calendar styling, startup title-bar dark-theme refresh, selectable preview subject, redundant sidebar product title removed.

## Authorization/runtime state

- API scope is defined by the same mailbox OAuth credential `ru.mail.oauth2.access`, not by a fixed host list.
- CAPTCHA/reCAPTCHA/additional verification stops authorization; no challenge solving or bypass.
- No IMAP/SMTP, app passwords, or independent web-cookie fallback.
- Saved `refresh_token` is used to recover an expired `access_token` through `https://o2.mail.ru/token`; controlled live validation passed before v0.3.0.
- Normal runtime has no fixed five-second delay. Research/probe/test requests keep at least five seconds spacing.

## Multi-account behavior

- Saved accounts are shown as compact email-address rows in a resizable right-side panel.
- Clicking an account switches active mailbox and loads Inbox.
- Badge value means **number of unread messages in Inbox**, derived from Inbox `messages_unread`; it is not total messages and not a generic account state.
- Known zero is shown as zero where the UI exposes a count.

## Current regression found after v0.3.3

Owner added a second account. Folders and unread counts loaded correctly, but the message list was empty even though the server response contained messages.

Root cause is confirmed: Mail.ru has at least two valid smart-thread response shapes.

Previously supported shapes:
- `threads[].base_message`;
- `threads[].messages[]`;
- direct thread-like objects with subject/snippet.

Newly observed shape from the Owner's second account:
- `body.folders_content[].threads[].representations[]`.

The uploaded response for Inbox shows valid folders and counts (`messages_total=319`, `messages_unread=11`) and thread representations containing `flags`, `subject`, `date`, `folder`, `snippet`, `correspondents`, `message_id_last`, attachment counts, etc. The v0.3.3 parser ignored those `representations[]`, causing zero recognized messages while folders still rendered.

## v0.3.4 hotfix in progress

Branch: `fix/v0.3.4-representations-parser`
PR: **#34** — `MailRu Desktop v0.3.4 — fix messages for representations responses`
Head: `b54d082144b1368a035cf8ebc0634a46eec4c5b2`

Implemented on the branch:
- tolerant parsing of `threads[].representations[]`;
- prefer representation matching the requested/current folder;
- use `message_id_last` as message id when representation does not expose `id`;
- preserve sender, subject, snippet, date, folder, unread, flagged, pinned and attachment state;
- regression coverage for the newly observed response shape;
- app version bumped to 0.3.4.

Validation status:
- CI run `37562784849`: **success**;
- repository-storage policy for the PR: **success**;
- PR #34 is still open and has not yet been merged/released at the time of this handoff.

## Stable product policies

- Release updates as `MailRuDesktop_Update_vX.Y.Z.exe`; full installer remains available for first install.
- Keep only the latest binary release where release-pruning policy applies.
- Research/test traffic must not look like DDoS; keep at least five seconds between repeated network requests.
- Do not silently replace known Mail.ru response variants with one assumed canonical schema: parsing must be tolerant and validated against real captured responses.
- Folder `last_modified` behavior remains unchanged unless explicitly authorized.
