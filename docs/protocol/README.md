# Mail.ru Reverse API Specification

This directory is the project-owned protocol specification. It is deliberately evidence-driven.

## Evidence levels

- **A — VERIFIED_LOCAL**: confirmed by our own known-working code or captured traffic.
- **B — EXTERNAL_CONFIRMED**: implemented by an independent reverse-engineered project, not yet revalidated by us.
- **C — CANDIDATE**: plausible endpoint/shape found in third-party code; requires local traffic validation.
- **D — REJECTED_OR_OBSOLETE**: tested and no longer valid, or superseded by stronger evidence.

No endpoint is promoted to A without local evidence.

## A — verified on `aj-https.mail.ru`

| Method | Endpoint | Purpose | Notes |
|---|---|---|---|
| POST | `/cgi-bin/auth?mp=android&udid=mailru_app` | Authenticate | form fields include `Password`, `Login`, `oauth2=1`, `useragent=android`, `mobile=1`, `mob_json=1`, `simple=1` |
| GET | `/api/v1/m/threads/status/smart` | Folder/thread status | `folders` JSON, `last_modified`, `access_token` |
| POST | `/api/v1/messages/attaches/add` | Upload attachment | multipart; returns attachment id |
| POST | `/api/v1/messages/send` | Send message | form-urlencoded compose payload |
| POST | `/api/v1/messages/schedule` | Schedule message | same compose family plus server send date |

Known mobile User-Agent from working code:

`mobmail android 11.13.0.29089 ru.mail.mailapp`

### Compose fields currently known

`attaches`, `body`, `correspondents`, `id`, `source`, `subject`, `send_date`, `priority`.

For replies, verified legacy behavior uses `source={"reply":"<message-id>"}`.

## B — externally confirmed internal API family

These are research inputs, not yet assumed to work on the current `aj-https.mail.ru` generation.

### Hackus

Source: https://github.com/ahmedelkfafy/Hackus

Observed:

- `POST https://aj-https.mail.ru/cgi-bin/auth`
- `GET https://touch.mail.ru/api/v1/tokens`
- `GET https://touch.mail.ru/cgi-bin/gosearch`
- `GET https://touch.mail.ru/api/v1/messages/message`
- routed `/messages/move`
- routed `/messages/remove`
- routed `/k8s/ab/smart` for address-book email discovery

Search parameters observed include `q_from`, `q_subj`, `q_query`, `q_attach`, folder selection, result count, and date ranges.

### e.mail.ru reverse projects

Sources:

- https://github.com/xRubin/unapi-mailru
- https://github.com/GeorgeKaspar/SmartMailAddOn
- https://github.com/VectorASD/Magistracy
- https://github.com/dukei/any-balance-providers
- https://github.com/own2pwn/smartmailhack2
- https://github.com/Lamardo43/loadtest-QE-IPR1

Observed endpoint families:

- `/api/v1/threads/status/golang`
- `/api/v1/threads/status/smart`
- `/api/v1/threads/thread`
- `/api/v1/messages/status`
- `/api/v1/messages/message`
- `/api/v1/messages/search`
- `/api/v1/folders/add`
- `/api/v1/folders/clear`
- `/api/v1/user/short`
- `/api/v1/k8s/messages/send`

The K8s send variant exposes additional compose semantics such as `receipt`, `remind`, `sign`, `delay_for_cancellation`, attachment restore/expiry and richer `source` metadata.

## C — candidates requiring capture validation

A fresh 2026 project (https://github.com/kirill-sorochuk/fa.schedule) contains a cookie-based `e.mail.ru/api/v1` manager using candidate routes:

- `/user/folders`
- `/messages/list`
- `/messages/read`
- `/messages/search`
- `/messages/flags`
- `/messages/delete`
- `/messages/move`
- `/messages/send`

The repository history does not independently prove that every route works, so these remain C until captured locally.

## Research policy

1. Capture the real client/browser request.
2. Record method, host, path, query, headers, body schema and response schema.
3. Remove credentials, tokens, mailbox content and personal identifiers.
4. Compare against this registry.
5. Promote/demote evidence explicitly.
6. Add a regression test before using a newly verified operation in the UI.
