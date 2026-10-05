# Latest handoff

## Last completed work

Published MailRu Desktop `v0.1.3`.

The release is based on a reconciliation of the owner's working VBA Mail.ru implementation with the independent Hackus reverse client.

Key changes:

- `token_missing` is no longer treated as an immediate terminal login failure;
- mobile `aj-https.mail.ru` token auth remains the primary path;
- a Hackus-derived cookie-session path is attempted as an independent fallback;
- cookie sessions can derive touch and web API tokens;
- CAPTCHA/reCAPTCHA/2FA/recovery/bad-credential states are distinguished instead of collapsing into `token_missing`;
- access, refresh, web and touch tokens plus required cookie headers are persisted with Windows DPAPI; passwords are not stored;
- mailbox loading can fall back from the locally verified mobile smart endpoint to web-thread or touch-search transports;
- touch full-message, search, move/remove and address-book operations are represented in the protocol layer as B-level evidence;
- the Hackus/VBA endpoint reconciliation is preserved in `docs/protocol/observations/2026-10-05-hackus-vba-reconciliation.md`.

CI for the v0.1.3 head passed and release workflow `37353057630` completed successfully.

## Release/storage state

Current binary release: `v0.1.3`.

Normal upgrade package: `MailRuDesktop_Update_v0.1.3.exe`.
Full/recovery package: `MailRuDesktop_Setup_v0.1.3.exe`.

No GitHub Actions artifacts are produced. Publishing v0.1.3 pruned older binary GitHub Releases while preserving source tags.

## Acceptance gate

The owner should retest the same account that previously returned `Ошибка: token_missing` (the `expert.sout@mail.ru` regression case).

Do not claim the runtime defect fully closed until that live account succeeds or yields a correctly classified challenge state.

## Next operation

After the auth regression test, locally validate the Hackus full-message endpoint and read-state behavior. Promote it to A-level only on owner/runtime evidence, then wire actual message body and attachment reading into the right-hand pane.
