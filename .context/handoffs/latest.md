# Latest handoff

MailRu Desktop remains released at **v0.1.15**; `main` is both product and manager-state authority.

## Governing protocol decision

The old rule "only `aj-https.mail.ru` plus explicit host exceptions" is superseded.

Current rule: an internal Mail.ru mechanism is in scope when the official client uses the same mailbox OAuth credential `ru.mail.oauth2.access` obtained by our authorization flow. Hostname and parameter spelling are not selection boundaries. Independent credentials/sessions remain out of scope unless the Owner changes the rule.

Confirmed transports of the same token: 97 commands through `access_token`, one through `t` for the new `go.mail.ru` search, and two through an authorization header.

## Research completion

Official Android APK `ru.mail.mailapp` 15.107.0.148045 was decompiled and its network layer mapped on branch `research/mail-apk-15.107.0.148045`, head `e549fd60dc7380d9a24c58c4ef931e25c4f5b67d`.

- 151 routed network classes;
- 101 access-token candidates;
- 100 confirmed to use the same mailbox OAuth token;
- one false positive, `QrGetInfoCommand`;
- all 101 candidate source classes retained; no source gaps.

Static APK confirmation is not automatically a live-server pass. Before enabling a new function in the released client, perform one controlled live validation and preserve sanitized evidence.

## Immediate continuation

1. Replace the obsolete fixed-host guard with endpoint/evidence/credential-aware protocol policy.
2. Validate and implement permanent delete plus flagged/pinned marks.
3. Validate server search, including `go.mail.ru` with `t=<same token>`.
4. Validate address book and folder management.
5. Continue with drafts/schedule/attachment lifecycle, then aliases/collectors/filters/cloud/translation as useful.

Authorization challenge policy remains unchanged: CAPTCHA/reCAPTCHA/additional verification stops authorization; no challenge solving.

Research/probe/test requests keep at least five seconds spacing. Normal application runtime does not use that artificial delay.
