# Project architecture

## Product stack

Initial implementation: **.NET 8 + WPF**.

### Layers

- `MailRuDesktop.App`: native Windows UI and interaction flow.
- `MailRuDesktop.Protocol`: HTTP transport, endpoint registry, request/response models, host/token-carrier selection and protocol validation.
- `docs/protocol`: human-readable reverse API specification with evidence levels.

The UI must not construct Mail.ru URLs or protocol payloads directly.

## Protocol authority

Endpoint evidence is classified:

- A / VERIFIED_LOCAL — confirmed by our working code or controlled live traffic;
- B / STATIC_OFFICIAL_CLIENT — contract/token flow confirmed by current official APK but not yet live-validated by our implementation;
- C / EXTERNAL_CONFIRMED — independently implemented elsewhere but not locally revalidated;
- D / CANDIDATE — plausible finding requiring validation;
- E / REJECTED_OR_OBSOLETE — disproved, removed or superseded.

Only A operations are enabled by default in production flows. B operations are implementation-ready candidates but still require one controlled live validation.

## Credential-bound API scope

Protocol routing is host-neutral. Eligibility is based on verified use of the same mailbox OAuth credential `ru.mail.oauth2.access`, not on a fixed host allow-list. `MailRuDesktop.Protocol` owns the endpoint registry, resolved host, request method and token carrier.

Confirmed token carriers include normal `access_token`, alternate query key `t`, and `Authorization: Bearer`.

## State boundaries

- credentials/tokens: OS-protected local secret storage; never repository/config plaintext;
- mailbox cache/index: local persistent store separated from credentials;
- protocol captures: local research input only, sanitized before durable repository documentation;
- UI state: no protocol authority.

Third-party source code is research evidence only and is not copied into the application.
