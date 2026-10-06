# Manager beliefs

## B-001 — repository bootstrap

statement: The project repository is `lvlaksim1/mailru-desktop`, public, created through `lvlaksim1/repo-factory` with the `project-manager` profile and without standard Telegram secrets.

source: Repo Factory issue #182 and live repository metadata.
authority: verified-repository
supersedes: none

## B-002 — protocol source restrictions

statement: The project must not use IMAP/SMTP, app-specific passwords, or official Mail.ru mail-client documentation as protocol authority. Internal Mail.ru HTTP behavior is established through reverse engineering, current APK analysis, verified working code, captures, and controlled runtime evidence.

source: direct Owner directives in the project conversation.
authority: owner-directive
supersedes: the older host-specific form of B-002 that restricted protocol authority to `aj-https.mail.ru`

## B-003 — implementation approach

statement: The product is implemented from scratch as a native Windows application. Third-party projects are research/evidence sources for practices and endpoint discovery; their product code is not copied.

source: direct Owner directive; product history on `main`.
authority: owner-directive + verified-repository
supersedes: prior exploratory idea of forking an existing mail client

## B-004 — technical baseline

statement: The implementation uses .NET 8 + WPF with protocol logic isolated in `MailRuDesktop.Protocol` and UI in `MailRuDesktop.App`. The repository also owns an evidence-ranked reverse API specification.

source: verified repository history.
authority: verified-repository
supersedes: none

## B-005 — initial protocol vertical slice

statement: The application implements the A-level protocol slice for mobile auth, smart thread status, attachment upload, send, and scheduled send.

source: product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622`.
authority: verified-ci
supersedes: none

## B-006 — compose UI is wired to verified transport

statement: The compose UI supports recipient, subject, body and multiple file attachments. Attachment uploads and immediate send use locally verified internal Mail.ru HTTP operations.

source: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260`.
authority: verified-ci
supersedes: the old AJ-only wording

## B-007 — installer and update policy

statement: First installation is distributed as `MailRuDesktop_Setup_vX.Y.Z.exe`; subsequent releases must provide `MailRuDesktop_Update_vX.Y.Z.exe` for in-place update without uninstall/reinstall.

source: direct Owner directive; release pipeline commit `0cc323a0e8c9a409261b2e7a1bbf809bd3145295`.
authority: owner-directive + verified-repository
supersedes: no previous release packaging policy

## B-008 — v0.1.0 release

statement: GitHub Release `v0.1.0` was published with both full and update installers.

source: GitHub Release id `403897280`.
authority: verified-repository
supersedes: none

## B-009 — current runtime validates auth and folder 0

statement: Owner-operated MailRu Desktop v0.1.0 successfully authenticated against the implemented mobile-style auth flow and successfully loaded folder id 0 through `/api/v1/m/threads/status/smart`, returning status 200 and current mailbox data.

source: direct Owner runtime report and screenshot on 2026-10-05; sanitized observation stored at `docs/protocol/observations/2026-10-05-folder0.md`.
authority: verified-runtime
supersedes: none

## B-010 — current smart-thread response shape

statement: The current folder-0 response visibly includes `messages_total`, `messages_unread`, and message objects with at least `id`, `subject`, `snippet`, `date`, `size`, `folder`, `flags`, and `correspondents`; correspondents include from/to/cc/bcc collections, and visible flags include unread/flagged/attach/reply/forward.

source: Owner-provided current runtime screenshot; sanitized observation document.
authority: verified-runtime
supersedes: none

## B-011 — v0.1.1 structured mailbox release

statement: v0.1.1 adds a tolerant parser for the verified smart-thread response, a structured mailbox table and metadata/snippet preview, raw JSON diagnostics, a project-owned cloud/envelope application mark based on the Owner-approved visual direction, and an assembly-derived version in the native window title. CI run `37342048282` and release workflow run `37342356199` both completed successfully.

source: commits `065748b056f47fefba0972157078e8a5064037fc` and `45204412fe8394e403eb46d8916961c9d75d86a7`; GitHub Release `v0.1.1`.
authority: verified-ci + verified-repository
supersedes: none

## B-012 — historical AJ-only runtime boundary
status: superseded by B-019

statement: On 2026-10-06 the Owner temporarily restricted runtime API use to `aj-https.mail.ru`, later adding `af.attachmail.ru` as an explicit exception. This host-based restriction is no longer current.

source: prior direct Owner directives.
authority: historical owner-directive
supersedes: none

## B-013 — live AJ mobile auth probe

statement: A controlled GitHub-hosted probe on 2026-10-06 sent the verified mobile auth request to `aj-https.mail.ru/cgi-bin/auth?mp=android&udid=mailru_app` using the repository test credentials. Mail.ru returned HTTP 200 JSON with top-level `oauth` and `status`, including both `access_token` and `refresh_token`, with no CAPTCHA signal.

source: GitHub Actions run `37410564363`.
authority: verified-runtime
supersedes: uncertainty that the current test account could still obtain the mobile access token

## B-014 — historical web/touch research is non-authoritative

statement: The research branch `research/web-api-token` and its Hackus/web-cookie/VK-ID experiments are historical evidence only. They must not be merged into product runtime or used as fallback architecture unless the Owner explicitly changes the credential policy.

source: Owner protocol decisions on 2026-10-06.
authority: owner-directive
supersedes: the temporary plan to pursue `e.mail.ru` web API tokens

## B-015 — v0.1.13 AJ-only release

statement: Pull request #18 was merged into `main` on 2026-10-06 and GitHub Release `v0.1.13` was published. The release contains both `MailRuDesktop_Setup_v0.1.13.exe` and `MailRuDesktop_Update_v0.1.13.exe`.

source: merged PR #18; product commit `6c5638ae8fa85ca9db63378bec8531d8356fe692`; GitHub Release id `404300426`.
authority: verified-repository
supersedes: state that v0.1.13 was only being prepared

## B-016 — v0.1.14 removes runtime pacing delay

statement: GitHub Release `v0.1.14` was published on 2026-10-06. It removes the artificial five-second delay from normal application protocol requests while retaining request serialization. The `last_modified` behavior of folder loading was intentionally left unchanged. The five-second spacing rule remains mandatory for project research, probes, and automated tests.

source: direct Owner directive; commits `0e95bfec07a86f66f89444377a43858be0232d7a` and `9070cae8824950dda1d6dc4c9b3ace66485ff5a2`; GitHub Release id `404651871`.
authority: owner-directive + verified-repository
supersedes: any interpretation that the five-second research pacing rule belongs in normal product runtime

## B-017 — historical `af.attachmail.ru` exception
status: superseded by B-019

statement: `af.attachmail.ru` was explicitly permitted for incoming attachments while the project still used a host allow-list. The host-specific exception remains historically valid but is no longer the governing selection rule.

source: direct Owner directive on 2026-10-06.
authority: historical owner-directive
supersedes: B-012 only insofar as it had prohibited every Mail.ru runtime host except `aj-https.mail.ru`

## B-018 — v0.1.15 message operations release

statement: GitHub Release `v0.1.15` was published on 2026-10-06. The release wires full-message retrieval through `aj-https.mail.ru/api/v1/messages/message`, unread/read marks through `/api/v1/messages/marks`, move/archive/trash through `/api/v1/messages/move`, and incoming attachment download through `af.attachmail.ru/cgi-bin/readmsg`.

source: Owner-provided VBA source; CI run `37465331504`; release workflow run `37465590209`; GitHub Release id `404697757`.
authority: owner-provided-runtime-source + verified-ci + verified-repository
supersedes: state that these operations were verified but not wired into the released client

## B-019 — current API boundary is the mailbox OAuth access token

statement: By Owner correction on 2026-10-06, MailRu Desktop is not restricted to `aj-https.mail.ru` or any fixed host list. A mechanism is in scope when the official Mail.ru client uses the same mailbox OAuth credential `ru.mail.oauth2.access` obtained by our authorization flow. The credential may be transmitted as `access_token`, under another parameter name such as `t`, or in an authorization header. Mechanisms requiring an independent credential/session remain out of scope unless separately authorized.

source: direct Owner directive; static token-flow audit in `research/mail-apk-15.107.0.148045`.
authority: owner-directive + verified-repository
supersedes: B-012, B-017, and all AJ-only host restrictions

## B-020 — APK access-token surface is statically mapped

statement: Static analysis of official Android package `ru.mail.mailapp` version `15.107.0.148045` found 151 `@UrlPath` network classes. 101 were OAuth candidates; strict transport tracing confirmed 100 classes actually use the same `ru.mail.oauth2.access` and rejected one false positive (`QrGetInfoCommand`). Source material is retained for all 101 candidates; no source gaps remain.

source: research branch `research/mail-apk-15.107.0.148045`, head `e549fd60dc7380d9a24c58c4ef931e25c4f5b67d`; `FULL_ACCESS_TOKEN_DISCOVERY.md`, `ACCESS_TOKEN_TRANSPORT_AUDIT.md`, `ACCESS_TOKEN_CONTRACTS.md/json`, `ACCESS_TOKEN_EXTENDED_API_SPEC.md`.
authority: verified-repository
supersedes: the belief that permanent delete, contacts, search, flag mutation and many sibling-host APIs were unresolved at route-contract level

## B-021 — token transport is not uniform

statement: The same mailbox OAuth token is transmitted in at least three verified ways: normal `access_token` query/session injection for the majority of commands; `t=<same token>` for `https://go.mail.ru/api/v1/go/search/emails`; and `Authorization: Bearer <same token>` for two calls-related commands. Therefore parameter spelling and host name are not valid criteria for excluding an API.

source: `ACCESS_TOKEN_TRANSPORT_AUDIT.md` and decompiled `MessagesSearchCommandNew` / calls auth classes in the research branch.
authority: verified-repository
supersedes: any parameter-name-only or host-only API classification

## B-022 — formerly missing core mail operations are now statically confirmed

statement: The current APK directly confirms access-token-compatible routes and request contracts for permanent remove, bulk remove/clear, spam/unspam, message/thread marks including unread/flagged/pinned, folder list/add/edit/remove/clear/archive/open/close, server search and suggestions, drafts, scheduling, outgoing attachment removal/reattach, address book, filters, unsubscribe, categories, snooze, EML download, message metadata, read notification, smart replies, color tags, cloud attachment operations and related functions.

source: `AJ_API_SPEC.md`, `APK_ROUTE_MAP.md`, `ACCESS_TOKEN_CONTRACTS.md/json`.
authority: verified-repository
supersedes: earlier blocker claims that these routes were unknown

## B-023 — new search on go.mail.ru is in scope

statement: `https://go.mail.ru/api/v1/go/search/emails` is used by the official client and passes the same `ru.mail.oauth2.access` as query parameter `t`. Its host is not `aj-https.mail.ru`, but it satisfies the current credential-based boundary and is therefore a valid implementation candidate.

source: APK `resources.arsc`; decompiled `MessagesSearchCommandNew`; `ACCESS_TOKEN_TRANSPORT_AUDIT.md`.
authority: verified-repository + owner-directive scope
supersedes: the earlier decision to exclude the route merely because `search_new_host=go.mail.ru`

## B-024 — authorization challenge policy remains unchanged

statement: The product still obtains the mailbox OAuth token through the approved mobile-style authorization flow. If CAPTCHA/reCAPTCHA or another interactive verification is required during authorization, the application informs the user and stops; it does not solve or bypass the challenge.

source: direct Owner directive.
authority: owner-directive
supersedes: none

## B-025 — v0.2.0 first access-token feature bundle released

statement: GitHub Release `v0.2.0` was published on 2026-10-07 MSK from product commit `32436a114f1384a36e9fe42c328b064f6c804f3d`. It adds user-facing permanent removal from Trash, flagged/pinned marks, classic server-side search, server address book and fast recipient lookup, user-folder create/rename/delete/clear, and server draft saving. The fixed host allow-list was replaced by an endpoint-registry policy.

source: merged PR #23; main CI run `37539922817`; release workflow run `37540083902`; GitHub Release `v0.2.0`.
authority: verified-ci + verified-repository
supersedes: state that these functions were implementation candidates only

## B-026 — v0.2.0 feature bundle passed controlled live validation

statement: Controlled test-account run `37539371650` exercised the release implementations with at least five seconds between network requests. Folder list, address book, fast recipient lookup, classic server search, flagged toggle/restore, pinned toggle/restore, temporary folder create/rename/clear/delete, draft save/list/move-to-trash and permanent removal all passed. The probe completed with zero failures.

source: GitHub Actions run `37539371650`.
authority: verified-runtime
supersedes: static-only evidence for the v0.2.0 operations

## B-027 — permanent removal is validated in Trash workflow

statement: Direct `/api/v1/messages/remove` on a probe draft still in Drafts returned `status=400, error=denied`. Moving that same probe draft to Trash (folder 500002) and then calling `/api/v1/messages/remove` passed. Product permanent-delete UI is therefore limited to the Trash workflow.

source: diagnostic runs `37538857673`, `37539030544`, and final run `37539371650`.
authority: verified-runtime
supersedes: any assumption that permanent remove is valid from arbitrary folders

## B-028 — new go.mail.ru search remains deferred

statement: The official APK contract for `https://go.mail.ru/api/v1/go/search/emails?t=<same token>` remains statically confirmed and in scope, but controlled live calls from the project runner returned HTTP 520. v0.2.0 therefore uses the classic `/api/v1/messages/search` route, which passed live validation. The new search remains opt-in/internal and is not active in the UI.

source: GitHub Actions runs `37538167641`, `37538857673`, and release implementation commit history.
authority: verified-runtime + verified-repository
supersedes: any plan to make the new search the default before live success

