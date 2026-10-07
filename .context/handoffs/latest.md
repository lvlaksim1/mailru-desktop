# Latest handoff

MailRu Desktop current public release: **v0.3.3**.

Released product-code baseline: `d5c9e25cae00015a0bcab71711ac9d9b84d3dbbf`.
`main` remains authoritative; later context-only commits may advance the branch head without changing released product code.

## What changed since v0.2.0

### v0.3.0
- modern three-pane mail interface;
- dark theme by default;
- right-side multi-account rail;
- Inbox unread counters per account;
- explicit account states;
- automatic access-token refresh using stored `refresh_token` through `POST https://o2.mail.ru/token`, `client_id=mail-android`.

### v0.3.1
- account rail converted to compact email-address rows with resizable width;
- per-message unread/flag/pin/archive actions;
- auto-read on opening a message;
- interactive filters;
- full-width compose;
- removable compose attachments;
- reply attachments;
- selectable email addresses;
- attachment download controls hidden when no attachments exist.

### v0.3.2
- compact three-line message rows;
- date/time moved to the first column;
- sender/subject/snippet in the main column;
- pin/archive actions in the right action column;
- preview pane became the normal full-message workspace;
- separate double-click message window removed from normal workflow;
- unified text search;
- account badge explicitly means Inbox unread count.

### v0.3.3
- current release;
- improved pin icon;
- active flagged star is yellow;
- active pin is red;
- pinned messages form the top group and remain chronological inside the group;
- unpin returns a message to its chronological position;
- resizable list/preview boundary;
- action column stays attached to the message-list right edge;
- date-range filter restored;
- calendar dark-theme styling added;
- startup title-bar dark-theme refresh strengthened;
- preview subject is selectable/copyable;
- redundant `MailRu Desktop` text above `Новое письмо` removed.

## Current high-priority regression

Owner added a second account. Folders loaded, but the message list stayed empty.

Owner-provided raw response proves the server did return messages. Inbox metadata includes `messages_total=319` and `messages_unread=11`, and `folders_content[0].threads[]` contains message data under **`representations[]`**.

Root cause in v0.3.3 parser:
- understands `base_message`;
- understands `messages[]`;
- understands direct thread-like objects;
- does **not** understand `representations[]`.

Therefore folder parsing succeeds while message parsing returns zero rows.

## v0.3.4 hotfix status

Branch: `fix/v0.3.4-representations-parser`
PR: **#34**
Head: `b54d082144b1368a035cf8ebc0634a46eec4c5b2`

Implemented:
- parse `threads[].representations[]`;
- prefer representation matching requested/current folder;
- use `message_id_last` when representation has no direct `id`;
- preserve sender/name, subject, snippet, date, folder, unread, flagged, pinned and attachment state;
- regression coverage for the newly observed response form;
- version bumped to 0.3.4.

Checks:
- CI run `37562784849`: **success**;
- repository-storage policy: **success**.

At handoff time PR #34 is open, not yet merged and not yet released.

## Immediate continuation in the next chat

1. Re-open project manager context from `.context/`.
2. Inspect PR #34 and current `main` because context-only commits were added after the PR branch point.
3. Merge/rebase only if clean; do not lose context updates.
4. Build and publish **v0.3.4** update installer.
5. Owner tests the second account that produced the `representations[]` response.
6. Verify account switching both directions and Inbox unread count correctness.

## Fixed protocol/product rules

- Scope is the same mailbox OAuth credential `ru.mail.oauth2.access`, not a fixed host.
- CAPTCHA/reCAPTCHA/additional verification stops auth; no bypass.
- No IMAP/SMTP, app passwords or independent web-cookie fallback.
- `refresh_token` recovery is part of normal runtime.
- Normal runtime has no mandatory five-second sleep.
- Research/probe/test repeated network requests keep at least five seconds spacing.
- Permanent remove remains validated as a Trash workflow.
- `go.mail.ru` new search remains deferred after HTTP 520 live results; classic search remains the working default.
