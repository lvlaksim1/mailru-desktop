# Project architecture

## Product stack

Initial implementation: **.NET 8 + WPF**.

### Layers

- `MailRuDesktop.App`: native Windows UI and interaction flow.
- `MailRuDesktop.Protocol`: HTTP transport, endpoint registry, request/response models, protocol validation.
- `docs/protocol`: human-readable reverse API specification with evidence levels.

The UI must not construct Mail.ru URLs or protocol payloads directly.

## Protocol authority

Endpoint evidence is classified:

- A / VERIFIED_LOCAL — confirmed by our working code or captured traffic.
- B / EXTERNAL_CONFIRMED — independently implemented elsewhere but not yet locally revalidated.
- C / CANDIDATE — plausible third-party finding requiring capture validation.
- D / REJECTED_OR_OBSOLETE — disproved, removed, or superseded.

Only A endpoints are enabled by default in production flows.

## Initial verified vertical slice

`aj-https.mail.ru`:

- mobile-style auth;
- smart thread/folder status;
- attachment upload;
- send;
- scheduled send.

## Future state boundaries

- credentials/tokens: OS-protected local secret storage; never repository/config plaintext;
- mailbox cache/index: local persistent store, separated from credentials;
- protocol captures: local research input only, sanitized before any durable repository documentation;
- UI state: no protocol authority.

Third-party source code is research evidence only and is not copied into the application.
