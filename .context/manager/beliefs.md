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

## B-029 — v0.3.17 persistent isolated authorization profile is runtime-confirmed

statement: MailRu Desktop v0.3.17 replaced the disposable browser profile used for interactive Mail.ru verification with a persistent profile isolated per mailbox account. In Owner runtime on 2026-10-08, an account that had previously been routed through password recovery instead received a normal phone-confirmation step and completed authorization successfully without password recovery. The account profile remains isolated from normal desktop browsers and from other MailRu Desktop accounts.

source: merged PR #60; GitHub Release v0.3.17; direct Owner runtime report on 2026-10-08.
authority: verified-repository + verified-runtime
supersedes: B-024 insofar as it stated that interactive verification always stops authorization

## B-030 — v0.3.18 interface/workspace bundle is merged but not yet released

statement: PR #62 was merged to main as commit 3593e4689c813b4c587518cba4c424742de2f621. It implements the Owner-requested interface bundle: corrected window/layout persistence, darker dark-theme scrollbars, compact sender/account icons, persistent account ordering, account sections with a right-side expand/collapse arrow, 275 ms hold-to-detach account dragging with animated neighbor displacement and no Escape cancellation, preview/reply control relocation, and editable signatures/templates with reply-form insertion. Main CI and repository-storage-policy checks passed. No v0.3.18 GitHub Release exists yet; latest public release remains v0.3.17.

source: direct Owner directives in project conversation; merged PR #62; main CI run 37703044365; repository-storage-policy run 37703044638; live release metadata on 2026-10-08.
authority: owner-directive + verified-repository + verified-ci
supersedes: none



## B-031 — v0.3.18 is published with real-avatar and Markdown-template work

statement: GitHub Release v0.3.18 was published 2026-10-08 with full and in-place update installers after PR #63 merged. The bundle implements attempts to fetch real Mail.ru sender/account avatars from the official APK-discovered filin.mail.ru/pic?email URL, with 16px initials fallback. It migrates user mail templates from settings.json into separate Templates/<name>.md files, discovers manually edited/added/deleted files, copies newly added local attachment files under Templates/_attachments, refreshes the reply selector without reapplying a template to text already being edited, and inserts templates without confirmation. It also guards against moving an account on a stationary 275 ms long-press. Avatar endpoint photo availability and the full manual-file workflow remain pending Owner runtime validation.

source: direct Owner directives in conversation on 2026-10-08; merged PR #63; main CI 37707321540; release workflow 37707435464; GitHub Release v0.3.18; docs/markdown-templates.md.
authority: owner-directive + verified-repository + verified-ci (not verified Owner runtime)
supersedes: B-030 regarding release pending and initials-only icons


## B-032 — v0.3.19 fixes account drag and outgoing compose requests

statement: GitHub Release v0.3.19 was published 2026-10-08 with both installers from PR #67. Main CI 37709503446 (build, launch and offline regression checks), repository-storage policy 37709503443, and release workflow 37709585421 passed. The account drag target now uses original row centers rather than animation-shifted positions, and prevents undesired short-click selection after a long-press. New compose adds checkbox-controlled read-receipt request serialized as POST receipt=true; the boolean receipt parameter was confirmed in TornadoSendParamsImpl in the official APK. The existing scheduled send has tomorrow at 09:00 as UI default and remains on the existing /api/v1/messages/schedule route. If sender name is empty or missing, the email is displayed; parser now accepts array/object/string sender forms. Offline protocol imitation covered form serialization; no live read-receipt or delayed-delivery validation has been completed.

source: Owner defect report and feature directives; PR #67 merged commit 3640b7e56e8059777d878d9f67f4f8d6bf01c32a; workflow 37709503446; release workflow 37709585421; official APK decompilation (TornadoSendParamsImpl.java).
authority: owner-directive + verified-repository + verified-ci + static-official-client (NOT live delivery validation)
supersedes: I-009 and prior current views wherever they imply v0.3.18 is the latest release.

## B-033 — v0.3.20 fixes inline reply, templates and visual issues without changing drag

statement: The Owner provided screenshots and reported a blank sender row despite the full message containing the sender email, missing read receipt and delayed-send controls in inline reply, light scrollbar elements in dark theme, a light titlebar when naming an account section, inability to reapply the currently selected template/signature and inability to choose the templates folder. The Owner confirmed account reordering/divider behavior was excellent. PR #70 merged on 2026-10-08 and v0.3.20 was published. New features include safe configurable Markdown template directory with file/attachment copy, repeated user-choice handling, inline reply close button and receipt/scheduled delivery, dark WPF and HTML scrollbar templates, DWM native caption reapplication and list-row sender enrichment from parent thread and full message. Offline file and sender tests, Windows build/startup, storage policy and release workflow passed; Owner visual tests and real receipt/delayed delivery are pending.

source: Owner messages and screenshots 2026-10-08; GitHub PR #70; main commit eb3415ac30f81cc3426d4726b53d590d42e57b11; CI 37711634109; release 37711732617.
authority: owner-directive + verified-repository + verified-ci (not Owner runtime verified)
supersedes: B-032 only where v0.3.19 was described as latest published version.

## B-034 — Owner-approved 26-role editable semantic color architecture

statement: On 2026-10-08 the Owner explicitly specified a thorough cleanup of all UI element colors with independent value editing in settings and immutable mapping of element groups to color roles. Approved response established exactly 26 semantic roles for each dark/light theme, with a system theme that chooses the currently effective Windows light/dark set. Implemented in PR #73, release v0.3.21, published 2026-10-08. ThemePalette.Roles is the canonical catalog and defaults. User can edit only #RRGGBB values via settings color table/RGB chooser, preview and explanatory role membership; role mapping is uneditable. Colors persist independently per effective theme, are applied dynamically through ThemeManager/WPF/HTML, and can be restored individually or together. Contrast warnings do not override user choices. UI illustrations and mail content images are exempt from recoloring. Offline 26-role and independent-theme persistence/regression tests, Windows build/startup and installer release passed. Owner visual/UI runtime testing remains pending. Account drag and splitters are deliberately unchanged.

source: Owner's explicit requirement in chat 2026-10-08; merged PR #73; main product merge e29a23ab81697b0dd38f0f1b2d9ce38efefb8c52; release workflow 37716328414; docs/theme-palette.md.
authority: owner-directive + verified-repository + verified-ci (not Owner visual runtime confirmed)
supersedes: no other requirements; v0.3.21 replaces v0.3.20 as latest public release.

## B-035 — v0.3.22 released with safe bulk actions; scheduled send-now is NOT proven

statement: On 2026-10-08, the Owner asked to collapse 26-role palette settings and replace RGB sliders with click-to-pick 2D color selection, then implement the 12-point MailRu Desktop backlog. PR #76 merged as bd6ff6ff98f93a69458efc921e7c35a21e159afc and v0.3.22 was published, release workflow 37720023234, main CI 37719927017 and policy 37719927127 succeeded. The implementation adds sender name/email auto-enrichment (bounded, read=false), human-readable Cyrillic metadata (including migration and backup), downloaded attachment directory plus Explorer, top-mounted attachment controls, synchronized RichTextBox HTML formatting across editors, checkbox/Ctrl/Shift selection, grouped move/mark-read and permanent deletion with confirmation. Color chooser is a 2D hue/saturation surface plus brightness strip. Research did NOT prove duplicate-free immediate sending of an already scheduled message. Outbox Send Now surfaces are explicitly disabled. The Owner's 12-point backlog is therefore NOT fully closed; issue #77 is outstanding.

source: User directives and examples 2026-10-08; merged GitHub PR #76; main CI 37719927017; release workflow 37720023234; static official APK /api/v1/messages/send and /schedule research; open issue #77.
authority: owner-directive + verified-repository + verified-ci + static-protocol; not verified Owner GUI or recipient delivery.
