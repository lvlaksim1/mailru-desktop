# Mail.ru Reverse API Specification

This directory is the project-owned protocol specification. The active runtime policy is deliberately narrow and evidence-driven.

## Runtime policy

MailRu Desktop uses `https://aj-https.mail.ru` as the primary Mail.ru API host.

The Owner explicitly permits `https://af.attachmail.ru` as an auxiliary runtime host. The currently verified use is downloading incoming attachments through `/cgi-bin/readmsg` with the existing access token.

No runtime fallback to `touch.mail.ru`, `e.mail.ru`, `account.mail.ru`, `auth.mail.ru`, `c.mail.ru`, or any other Mail.ru host is allowed unless separately approved by the Owner.

If authentication requires CAPTCHA/reCAPTCHA or another interactive verification, the application reports that condition to the user and stops authorization. It does not attempt to solve or bypass the challenge.

Runtime requests are not subject to an artificial fixed delay. The client still serializes protocol requests to avoid overlapping duplicate operations. The five-second spacing rule applies only to project research, probes, and automated tests.

## Evidence levels

- **A — VERIFIED_LOCAL**: confirmed by our own known-working code, owner VBA, captured traffic, or live controlled probes.
- **B — EXTERNAL_CONFIRMED**: implemented by an independent reverse-engineered project, not yet revalidated by us.
- **C — CANDIDATE**: plausible endpoint/shape requiring local validation.
- **D — REJECTED_OR_OBSOLETE**: tested and no longer valid, or intentionally excluded from the product architecture.

Only verified operations on Owner-approved runtime hosts may be used by the application. The approved hosts are currently `aj-https.mail.ru` and `af.attachmail.ru`.

## A — active verified endpoints on `aj-https.mail.ru`

| Method | Endpoint | Purpose | Notes |
|---|---|---|---|
| POST | `/cgi-bin/auth?mp=android&udid=mailru_app` | Authenticate | form fields: `Password`, `Login`, `oauth2=1`, `useragent=android`, `mobile=1`, `mob_json=1`, `simple=1` |
| GET | `/api/v1/m/threads/status/smart` | Folder/thread status | `folders` JSON, `last_modified`, `access_token` |
| GET | `/api/v1/messages/message` | Full message | `id`, `mark_read`, `mp=android`, `access_token` |
| POST | `/api/v1/messages/marks` | Read/unread state | form field `marks`; mobile token auth |
| POST | `/api/v1/messages/move` | Move/archive/trash | form fields `folder`, `ids` |
| POST | `/api/v1/messages/attaches/add` | Upload outgoing attachment | multipart; returns attachment id |
| POST | `/api/v1/messages/send` | Send message | form-urlencoded compose payload |
| POST | `/api/v1/messages/schedule` | Schedule message | compose family plus server send date |

Verified mobile User-Agent:

`mobmail android 11.13.0.29089 ru.mail.mailapp`

A live GitHub-hosted probe on 2026-10-06 confirmed that the AJ mobile-auth request returned HTTP 200 JSON containing both `access_token` and `refresh_token` for the test account.

## Approved auxiliary attachment host

The Owner explicitly permits `https://af.attachmail.ru` for incoming attachment downloads.

Verified VBA request:

`GET https://af.attachmail.ru/cgi-bin/readmsg?access_token=<token>&id=<message-id>;<attachment-id>&notype=1`

The attachment id and display name come from the full-message response.

### Compose fields currently known

`attaches`, `body`, `correspondents`, `id`, `source`, `subject`, `send_date`, `priority`.

For replies, verified legacy behavior uses `source={"reply":"<message-id>"}`.

## Deliberately disabled until AJ endpoints are verified

The current product must not emulate these operations through another host:

- permanent delete;
- server-side contacts/address book;
- server-side search beyond the folder/thread status payload;
- confirmed mutation of the flagged mark.

When an AJ endpoint for one of these operations is found, it must be validated before enabling the feature.

## Historical research

Older project revisions investigated Hackus touch/search sessions and `e.mail.ru` web APIs. Those findings may remain in repository history and observation documents for provenance, but they are **not active transports** and must not be reintroduced as fallbacks without an explicit owner decision changing this policy.

## Research policy

1. Prefer captured or independently reproducible AJ traffic.
2. Record method, path, query, required headers/body schema, and response schema.
3. Remove credentials, tokens, mailbox content, and personal identifiers from evidence.
4. Keep at least five seconds between Mail.ru requests.
5. Promote an endpoint to active use only after verification on `aj-https.mail.ru`.
6. Add a regression guard before exposing the operation in the UI.
