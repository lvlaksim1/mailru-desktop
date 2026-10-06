# Semantic memory

## Mail.ru access-token API boundary — 2026-10-06

- Current scope is credential-based, not host-based: include official-client mechanisms that use the same mailbox OAuth credential `ru.mail.oauth2.access`.
- Verified token transports in APK 15.107.0.148045: 97 `access_token`, one `t` for `go.mail.ru` search, two `Authorization: Bearer`.
- Full static audit: 151 routed network classes; 101 candidates; 100 confirmed same-token; `QrGetInfoCommand` excluded; 101/101 candidate sources retained.
- High-value previously unresolved operations are now statically known: permanent delete, flag/pin, search, address book, folders, drafts/schedule, attachment lifecycle, filters and others.
- Static APK evidence proves official-client contract/token flow but does not substitute for live validation before product enablement.
- Evidence base: branch `research/mail-apk-15.107.0.148045`, especially `ACCESS_TOKEN_TRANSPORT_AUDIT`, `ACCESS_TOKEN_CONTRACTS`, `ACCESS_TOKEN_EXTENDED_API_SPEC`, `AJ_API_SPEC`.

## Stable product facts

- Native Windows client: .NET 8 + WPF, protocol isolated in `MailRuDesktop.Protocol`.
- Latest public release as of 2026-10-06: v0.1.15.
- Normal runtime has no artificial five-second delay; research/probe/test requests keep at least five seconds spacing.
- Authorization challenge policy: CAPTCHA/reCAPTCHA/additional interactive verification stops authorization; no bypass.
