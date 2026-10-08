# Semantic memory

## Mail.ru access-token API boundary — 2026-10-06

- Current scope is credential-based, not host-based: include official-client mechanisms that use the same mailbox OAuth credential `ru.mail.oauth2.access`.
- Verified token transports in APK 15.107.0.148045: 97 `access_token`, one `t` for `go.mail.ru` search, two `Authorization: Bearer`.
- Full static audit: 151 routed network classes; 101 candidates; 100 confirmed same-token; `QrGetInfoCommand` excluded; 101/101 candidate sources retained.
- High-value previously unresolved operations are statically known: permanent delete, flag/pin, search, address book, folders, drafts/schedule, attachment lifecycle, filters and others.
- Static APK evidence proves official-client contract/token flow but does not substitute for live validation before product enablement.
- Evidence base: branch `research/mail-apk-15.107.0.148045`.

## v0.2.0 live-validation findings — 2026-10-07

- Release-set run `37539371650` passed with zero failures for folder list, address book, fast recipients, classic search, flag/pin with restoration, folder CRUD/clear, draft save, move-to-trash and permanent remove.
- Permanent remove is validated after the message is in Trash; direct remove of a draft produced `status=400,error=denied`.
- New search on `go.mail.ru` remains in scope statically but returned HTTP 520 in controlled live calls and is therefore not enabled in v0.2.0.
- Classic `/api/v1/messages/search` is the released server-search mechanism.

## Stable product facts

- Native Windows client: .NET 8 + WPF, protocol isolated in `MailRuDesktop.Protocol`.
- Latest verified public release as of 2026-10-09: v0.3.29 (v0.2.0 was historical).
- Normal runtime has no artificial five-second delay; research/probe/test requests keep at least five seconds spacing.
- CAPTCHA/reCAPTCHA/additional interactive verification stops authorization; no bypass.
