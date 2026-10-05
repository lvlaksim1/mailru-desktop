# Latest handoff

## Last completed work

Published MailRu Desktop `v0.1.4`.

Requested feature set implemented:

- explicit auth challenge state machine;
- interactive reCAPTCHA / CAPTCHA / 2FA completion in an isolated embedded Mail.ru browser;
- explicit `Blocked` state and recovery distinction;
- touch-token acquisition after cookie-session authentication;
- fixed iPhone mobile Safari/GSA User-Agent for web/touch Mail.ru requests;
- fresh cookie-session/profile on every new login attempt;
- sanitized persistent diagnostics for unknown auth outcomes;
- full-message reading;
- incoming attachment parsing;
- direct incoming attachment download;
- preservation of original attachment filename and MIME type;
- server-side search;
- contacts/address-book retrieval;
- move to Trash / move to folder / permanent remove.

Security/session policy:

- passwords are never persisted;
- durable access/refresh/web/touch tokens and session headers remain Windows-DPAPI protected;
- challenge browser profile is ephemeral and is deleted after challenge completion where possible;
- diagnostics redact password/token/cookie values.

## Release/storage state

Current binary release: `v0.1.4`.

Normal upgrade package: `MailRuDesktop_Update_v0.1.4.exe`.
Full/recovery package: `MailRuDesktop_Setup_v0.1.4.exe`.

Release workflow `37367733108` succeeded. The release contains no GitHub Actions artifacts. Older binary Releases are pruned automatically.

## Acceptance gate

The critical next owner test is the same `expert.sout@mail.ru` account that currently triggers reCAPTCHA. Complete the interactive Mail.ru challenge, confirm mailbox access, then restart the application to verify the saved authorization.

After login succeeds, validate full-message read, one incoming attachment download, search, contacts, Trash and permanent remove using disposable test mail where mutations are involved.
