# Latest handoff

MailRu Desktop current public release: **v0.3.5**.

Released product-code baseline: `4b5ed4e629112a9151c3659afd6ca408a828d9b5`.
`main` remains authoritative; later context-only commits may advance the branch head without changing released product code.

## v0.3.4

The multi-account parser hotfix is released.

It adds support for the Mail.ru smart-thread response variant:
`body.folders_content[].threads[].representations[]`.

The parser retains support for `base_message`, `messages[]`, direct thread-like objects and `representations[]`, using `message_id_last` where a representation has no direct message id.

The exact Owner account that originally exposed this variant still requires runtime validation.

## v0.3.5

Owner requested a compact mail-list interface modeled on the supplied reference image while preserving the application's selected theme.

Released row structure:
1. fixed-size read/unread dot;
2. sender name, email fallback when no name exists;
3. flag;
4. number of messages in the thread when available;
5. subject plus first non-empty text/snippet line;
6. attachment paperclip only when attachments exist;
7. time.

Mail is grouped by date: a separate date header is followed by all messages for that date.

Unread rows have both:
- a filled read-state dot;
- bold fifth-column subject/text content.

Opening unread mail still automatically marks it read. The selected-message preview controls now include **`Не прочитано`**, allowing the user to reverse the automatic read state for the currently selected message.

The implementation uses existing theme resources rather than a fixed color palette.

## Verification

- PR #36: merged.
- PR CI `37567961454`: success.
- repository-storage policy `37567961514`: success.
- post-merge `main` CI `37568041502`: success.
- release workflow `37568123580`: success.
- Release `v0.3.5` contains full and update installers.

Automated verification covers build, startup and packaging. Owner visual/runtime validation remains required for layout fidelity, real thread counts, selected-message unread restoration, and the second-account parser case.

## Immediate continuation

1. Owner installs `MailRuDesktop_Update_v0.3.5.exe`.
2. Validate the seven-column/date-grouped list and selected-message `Не прочитано` action.
3. Validate the second account that returned `representations[]`.
4. Fix any runtime/visual regressions before further feature expansion.
5. Then resume the mapped OAuth-compatible feature plan.

## Fixed protocol/product rules

- Scope is the same mailbox OAuth credential `ru.mail.oauth2.access`, not a fixed host.
- CAPTCHA/reCAPTCHA/additional verification stops auth; no bypass.
- No IMAP/SMTP, app passwords or independent web-cookie fallback.
- `refresh_token` recovery is part of normal runtime.
- Normal runtime has no mandatory five-second sleep.
- Research/probe/test repeated network requests keep at least five seconds spacing.
- Permanent remove remains validated as a Trash workflow.
- `go.mail.ru` new search remains deferred after HTTP 520 live results; classic search remains the working default.
