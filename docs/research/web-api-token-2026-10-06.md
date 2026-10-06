# Web API token research — 2026-10-06

Branch: `research/web-api-token`

## Owner direction

- Product direction is web API only.
- Mobile `access_token` is excluded from the target architecture.
- Touch/search token is not the target mailbox transport.
- Minimum spacing between Mail.ru requests in probes: 6 seconds.
- Secrets and credential values must never be printed.

## Live GitHub-hosted runner evidence

### Hackus-style `aj-https.mail.ru/cgi-bin/auth`

Runs from multiple GitHub-hosted Azure regions were tested.

Observed results:

- HTTP 302 to `account.mail.ru/login`.
- Most runs classified as reCAPTCHA.
- One earlier run returned the `user/login` challenge branch and a cookie named `ukey`.
- On reCAPTCHA runs, no useful authenticated cookies were issued before challenge completion.
- Therefore GitHub-hosted CI cannot reach a post-CAPTCHA session using login/password secrets alone.

Relevant workflow runs:

- 37406675806
- 37406762831
- 37407094819
- 37407442110

### VBA legacy web-auth flow

Exact legacy family tested from `mail.ru.xlsm`:

- `POST https://auth.mail.ru/cgi-bin/auth?saveauth=1&Login=...&Domain=...&Password=...`
- then `GET https://auth.mail.ru/sdc?from=...`
- then `GET https://e.mail.ru/inbox/`

Observed on current Mail.ru from GitHub-hosted runner:

- initial auth: HTTP 302 -> `account.mail.ru/login`;
- no `Mpop`, `ssdc`, or `sdcs` issued;
- unauthenticated inbox visit produced only anonymous/session-preauth cookie names such as
  `act`, `autologin`, `mrcu`, `mrhc`, `oid`;
- `/sdc` redirected back to login;
- `e.mail.ru/inbox/` did not contain the `/api/v1/user/short` marker and no web API token was found.

Conclusion: the old direct VBA credential flow is no longer sufficient from a fresh 2026 cloud session when Mail.ru requires the current login flow.

### Historical `act_token` web flow

The older open-source `xRubin/unapi-mailru` flow was reproduced:

1. GET `account.mail.ru/login/` and extract hidden `act_token`.
2. POST credentials + `act_token` to `auth.mail.ru/cgi-bin/auth`.
3. Parse `patron.updateToken(...)`.

Current result:

- `account.mail.ru/login/` immediately returns HTTP 302 to
  `auth.mail.ru/api/v1/vkid_auth/start`;
- no historical hidden `act_token` is returned.

Conclusion: this old flow is obsolete. Current Mail.ru web authentication has moved to the VK ID flow.

## Current auth architecture finding

Fresh 2026 Mail.ru web login currently enters:

`account.mail.ru/login -> auth.mail.ru/api/v1/vkid_auth/start -> id.vk.ru/auth`

This is independently consistent with current public network captures.

Therefore repeated CI password attempts will mostly reproduce the interactive anti-bot/authentication boundary and are not useful API research.

## What is still valid from mail.ru.xlsm

Once an authenticated web session exists, the target architecture remains:

`web cookies -> GET e.mail.ru/inbox/ -> web API token -> e.mail.ru/api/v1/*`

The workbook-proven mailbox endpoint is:

`GET https://e.mail.ru/api/v1/threads/status/smart`

with folder, limit, sort, last_modified, offset, email, htmlencoded and token parameters.

The research probes also contain validation calls for:

- `/api/v1/user/short`
- `/api/v1/threads/status/smart`
- `/api/v1/threads/status/golang`

These have not yet been live-validated on the test account because a current authenticated web session has not been obtained in GitHub-hosted CI.

## Next decisive experiment

Supply an already authenticated web-session cookie header as a GitHub secret, without exposing it in chat.

Recommended secret name:

`MAILRU_TEST_WEB_COOKIES`

Preferred content: the complete cookie header for an authenticated `e.mail.ru` session. At minimum, if the legacy names are still present:

`Mpop=...; ssdc=...; sdcs=...`

Then CI should:

1. send those cookies to `GET https://e.mail.ru/inbox/`;
2. derive the web API token locally;
3. validate `/api/v1/user/short`;
4. validate `threads/status/smart` and `threads/status/golang`;
5. expand read-only endpoint coverage;
6. only after read-side mapping is stable, test mutations on disposable test data.

If Mail.ru rejects the same session from a GitHub-hosted IP, the research runner must move to the owner's stable local network; repeated cloud password attempts are not a useful substitute.
