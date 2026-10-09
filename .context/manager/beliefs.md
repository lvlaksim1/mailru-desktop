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

## B-036 — Mail.ru embedded JSON 403 token caused false active auth and empty folder

statement: Owner v0.3.22 screenshot reported empty mailbox for previously authorized accounts while status said "Авторизация активна" and diagnostic JSON returned {"status":403,"email":"","htmlencoded":true,"body":"token"}. Source investigation established GetFolderThreadsAsync only checked HTTP response status, not JSON status, so HTTP200/status403 masqueraded as empty mailbox. Catch/retry previously relied on the HTTP status exception string. Inactive mailbox unread-counter fetches used stored access tokens but did not refresh them separately. In v0.3.23 PR #80, a typed authorization exception detects both HTTP 401/403 and embedded JSON status 401/403 for folder and full-message reads. New access tokens are persisted ONLY after the mailbox responds successfully. A non-active account can refresh its own token without modifying another account's credentials or the last selected login. Failed recovery preserves stored auth, signals that only this account needs login, and never logs secrets. Windows tests for false HTTP200/JSON403, refresh isolation with encrypted two-account persistence, build, startup and release passed. Owner's actual Mail.ru credentials were not tested; a revoked refresh token can still require sign-in.

source: Owner screenshot and JSON error, source PR #80, main SHA afdf7572953e79c6860bf6fb1a2c5fb93fea544a, CI 37768224358, release 37768430779.
authority: owner evidence + verified-source + verified-ci; NOT confirmed in live Owner accounts.

## B-037 — Owner-confirmed saved auth recovery and v0.3.24 GUI corrections

statement: Owner explicitly confirmed after v0.3.23 "всё починилось!", making prior 403 token multiaccount incident Owner-runtime-verified CLOSED. Returned GUI defects were palette footer clipping, white DatePicker popup, lost selected checkboxes after record changes, whole-list reload after batch operations, excessive Enter paragraph spacing, fixed-height reply editor and unnecessary permanent Trash deletion confirmation. Owner previously verified actual rich-formatted mail delivery. PR #83 merged e89c5ed2ef426e3def7957663b17254c66f44892; CI and release published v0.3.24. Changes include picker layout, explicit CalendarStyle, normal paragraph spacing, reply resize grip, ID-keyed selection, local batch mutation and stale-account guard, no Trash delete confirmation, initial same-message sender representation parsing. Owner visual validation of v0.3.24 is pending. Some sender summaries truly omit any correspondent, so full lookup remains necessary. Issue #77 scheduled Outbox send-now remains blocked.

source: Owner confirmation/screenshots on 2026-10-08; PR #83, CI 37771307863, release 37771458709, issue #77.
authority: Owner-runtime-verified for auth; repository/CI-verified for new GUI fixes.

## B-038 — Owner-defined checked-first mail target and canonical interface registry

statement: Owner required that for any applicable mail action all checked letters are targets when at least one checkbox is marked; otherwise the active-preview letter is the one target; with neither, notify the user. Opening any mail must never clear checks. Shift-click marks a range; Ctrl-based selection is unnecessary. Owner also required cataloging ALL windows as well as all constituent controls, including their parent, geometry, color role, typography, and behavior, enforced at build time. These are direct Owner decisions, not optional proposals.

v0.3.25 PR #86 merged at 2f25612777b30a15c9f3c1d979d1758ad9b6b5cb and public update/full installer release workflow 37789849920 PASSED. Checked-first MailTargetResolver, separate MailPreviewState/checked ID set and row click handlers, Shift ranges, context-menu/toolbar shared action targets, periodic explicit-only folder counter reconciliation, all-window UI registry generator schema 2, 7 windows and 1349 UI/style elements, role key checks for 26 palette roles, semantic hover/focus colors and persisted font size slider 10–18 were implemented. CI 37789612268 passed, Owner live use of v0.3.25 not yet confirmed. Existing scheduled Outbox send-now UI still disabled: APK source.schedule field alone does not prove safe duplicate-free send now (#77).

source: Owner project-manager requirements after v0.3.24; PR #86, main CI 37789612268, release 37789849920, docs/ui-registry.json and UI sources.
authority: owner directives + verified-repository + verified-ci; no Owner v0.3.25 GUI confirmation.


## B-039 — Owner acceptance and v0.3.26 release

statement: On 2026-10-08 Owner confirmed blank-subject sender resolution and all other v0.3.25 improvements, then directed nine new modifications: final-only formatted message display; collapsed-section 275 ms drag moving all child accounts; remove Mail and Contacts navigation; choose recipients from server contacts in detached New Mail only; compact immediate-apply directory selectors; collapsed Signatures/Templates; adjustable pressed-button background. PR #89 merged as a1ed3b9bbba67aa29bb38b88f499e038f3709f53. Windows CI 37797350430 and main CI 37797743640 passed; release run 37797787312 published v0.3.26 full and update installers. Existing AppControlPressedBrush among 26 roles is used rather than inventing a 27th. Follow-up PR #91 merged b084203c916d70b3ecfb5c3eec1d28481dea43f3; CI 37798726121 passed; UI registry now covers nine windows, 1354 elements and runtime compose hosting. No post-release user GUI validation yet; detached compose remains tied to MainWindow active account context, so switching accounts with an open compose window requires explicit user testing. Outbox send-now issue #77 remains blocked.

source: Owner direct instructions and confirmations, PRs #89/#91, workflows 37797350430/37797743640/37797787312/37798726121.
authority: Owner-runtime for old fixes + owner-directive + verified-repository/CI/release for new code, not Owner-runtime for v0.3.26.
supersedes: B-038 as the latest published release and seven-window registry, and the blank-subject sender pending status.


## B-041 — v0.3.27 updater check fallback published; Owner runtime gate pending

statement: Owner reported in installed v0.3.25 that Settings→Update→Check for updates displayed only «Не удалось проверить обновления», although v0.3.26 was published. Source review showed GitHubUpdateService.GetLatestReleaseAsync relied exclusively on api.github.com/releases/latest and MainWindow suppressed the underlying reason. The exact network failure on Owner Windows is NOT established without the category github_update_check in %LOCALAPPDATA%/MailRuDesktop/diagnostics.log. Owner-facing repair v0.3.27 merged PR #92 as c85cf0e7097c21e4cb26cc2ce30172f50368758f. It retains primary GitHub REST request, adds a strictly validated backup lookup through github.com/.../releases/latest redirect, presents sanitized status reasons when both fail, adds an Open releases page button and offline regression tests for API success, API 403/site success, unsafe redirect and simultaneous 403/502 failures. PR CI 37837933893 and main CI 37838100623 passed. Installer release workflow 37838253203 published verified full/update assets for v0.3.27 on 2026-10-08. The old v0.3.25 cannot acquire this code without installing a newer version (manual browser download if the old check cannot work); Owner real-network verification of fallback/download is pending. No silent update installation was added; checking and applying still require explicit user interaction.

source: Owner screenshot and report, GitHub source and PR #92, CI 37837933893 / 37838100623, release 37838253203 and live release metadata.
authority: Owner runtime evidence for the v0.3.25 symptom, verified source/CI/release for v0.3.27; not Owner runtime validation of the repair.
supersedes: latest public release v0.3.26 and any assumption that GitHub API success is assured in Owner environment.


## B-042 — Owner v0.3.27 GUI acceptance and v0.3.28 implementation

statement: On 2026-10-09 Owner confirmed real-world working account-section drag, working updater check v0.3.27, Contacts navigation removed, contact picking, collapsed signature/template settings and notably correctly retrieving sender photographs. Do NOT reopen sender-photo search without new evidence. Owner added that real sender/account portrait dimensions must scale with interface font size and requested a visible sender email in detached New Mail to guard against cross-account sending. Owner also documented severe WebView2 blank/previous/current flashing, missing images from afNN.mail.ru/cgi-bin/readmsg inline GIF links, color picker brightness-strip desynchronization, settings-to-folder navigation, moving New Mail button above letter list, blue send button, white caption of New Mail/contact dialog, missing New Mail signature/template, editor hover outline, overly tall notification dialogs, excessive folder hints, template ordering/loading, diagnostic copy and excessive separate theme colors. PR #94 merged 1d07dc2d17d4ffd2fc0cfa238e09d67032ece4f2 with implementation under v0.3.28; PR CI 37847574160 and main CI 37847753416 PASSED, Windows installer release workflow 37847893518 PASSED. It includes font-derived avatar size, sender and token pinned at compose window creation, template/signature and external Markdown import without modifying source, updated reader loading/navigation guard, OAuth attachment data-URI inlining with message/account validation, unified window chrome hooks, reduced dialog minimum height, synchronized brightness strip, moved nav/composer controls, and 20 editable shared colors mapping to 26 historical WPF resource keys. Generated catalog: 9 windows, 1371 UI elements, 26 resource names. This is source/build verified only: Owner has NOT yet confirmed v0.3.28 visual outcomes, especially missing private inline images and prevention of WebView2 flicker. Earlier scheduled Outbox Send Now issue #77 remains blocked.

source: Owner annotated screenshots/directives 2026-10-08/09; PR #94, main CI 37847753416, release workflow 37847893518, docs/theme-palette.md and docs/ui-registry.json.
authority: Owner runtime for accepted prior features; Owner directive + verified repository/CI/release for new v0.3.28 code, pending Owner GUI evidence.
supersedes: B-041 for latest published product and outstanding v0.3.27 updater check; original fixed 26-editable-color rule is superseded by the Owner's 20 shared-color consolidation request.


## B-043 — Проверка полноты ранее заявленных исправлений и выпуск v0.3.29

statement: 2026-10-09 владелец указал, что v0.3.28 работает, но реально из большого согласованного перечня он признаёт только два исправления. В подтверждение повторно предоставил список 22 замечаний и технических долгов. Нельзя считать изменение исходного кода или прохождение сборки доказательством правильной работы интерфейса. В повторной проверке найден реальный дефект: фотография отправителя масштабировалась внутри неподвижной рамки 16×16 и могла обрезаться. Исправлены рамка и колонка, переход в уже выделенную папку из настроек, расположение «Новое письмо» справа над списком, видимые подписи выбора подписи и шаблона, лишняя перерисовка HTML-письма при активации окна в системной теме и во время незавершённой загрузки, обработка ошибок переходов просмотрщика, лишняя высота окна текстового запроса, повторяющиеся обработчики оформления заголовков и неоднозначный перенос старых пользовательских цветов. PR #96 объединён в main (8f0ff0c07e59b38fd6e14edd14bebd7b74018c19). Автоматические проверки ветки 37850178802 и main 37850342043 успешны. Добавлены испытания реального интерфейса Windows: открытие настроек, проверка расположения блоков, возврат к почте, новое отдельное окно и поле отправителя, отсутствие принудительного синего фона кнопки, высота уведомления, изменение правой шкалы яркости при выборе цвета слева. Реестр содержит 9 окон и 1374 элемента. Задание выпуска 37850493662 создало полную и обновляющую версии v0.3.29. Сохранены работающие фотографии отправителей, перетаскивание групп, контакты и обновления. Выполнение 22 замечаний в целом НЕ считается подтверждённым владельцем: реальные письма, заголовки окон на его Windows и операции с авторизацией должны проверяться отдельно.

source: Прямая обратная связь владельца, повторный приложенный перечень 22 замечаний; GitHub PR #96; CI 37850178802/37850342043; выпуск 37850493662.
authority: owner-directive and repository/CI/release; not Owner runtime validation of v0.3.29.
supersedes: B-042 как последнюю версию и любые заявления, что все 22 пункта уже подтверждены.


## B-044 — v0.3.30: асинхронная загрузка изображений и безопасное сохранение шаблонов

statement: 2026-10-09 владелец проверил v0.3.29 и подтвердил, что остальные функции в целом работают, но шесть проблем остаются: мерцание тела писем; часть встроенных картинок (в том числе af12.mail.ru GIF чека Магнита); длительное открытие отдельных писем относительно браузера; загрузка .md открывает папку вложений; сохранение загруженного шаблона под новым именем изменяет исходный файл; немедленная отправка уже отложенных писем недоступна. Владелец подтвердил требования и приказал выполнить. PR #98 объединён с main, merge 8b509be0592d3b3bdc4674419dc09d6ec637512f. В исходном коде выявлены две причины: последовательное ожидание изображений до показа HTML и Save с прежним именем, удалявший исходный шаблон при новом имени. v0.3.30 показывает HTML после получения тела и DOMContentLoaded без ожидания изображений; WebView2 загружает afNN.mail.ru изображения отдельно через доступный OAuth API после валидации письма/аккаунта и сигнатуры; старые запросы получения полного письма отменяются; журнал фиксирует безопасные длительности запроса/передачи HTML. Кнопка загрузки .md начинает с TemplateFiles.DirectoryPath. Новое имя Save создаёт копию и независимые вложения, существующее имя требует подтверждения, хранилище не переименовывает/не удаляет исходный шаблон. CI ветки 37855438488 и main 37855678396 PASS; выпуск полных и обновляющих установщиков v0.3.30 — успешный workflow 37855812685. Личные HTML письма, реальное исчезновение мерцания и доступность GIF с авторизацией владельца требуют пользовательского подтверждения, а не вывода из CI. Задача #77 не реализована, документ docs/protocol/scheduled-send-now-safety.md фиксирует отсутствие подтверждённого атомарного серверного перевода существующего расписания, включение кнопки без доказательств могло бы привести к дублированию.

source: прямые замечания и согласование владельца, PR #98, CI и workflow 37855438488/37855678396/37855812685, GitHub Release v0.3.30, docs/protocol/scheduled-send-now-safety.md.
authority: Owner runtime for defects in v0.3.29; verified source/CI/release for v0.3.30; NOT Owner-runtime for new fixes.
supersedes: B-043 for latest release and review priorities.


## B-045 — v0.3.31: deferred image/layout presentation after Owner video K-001

statement: On 2026-10-09 Owner supplied video K-001.mp4 showing repeatable blinking in v0.3.30: neutral Loading, HTML text/receipt visible without embedded images, then images arrived and moved the receipt layout. Owner explicitly requested an update fixing this. Root cause in source: MainWindow.InitializeReaderAsync subscribed CoreWebView2.DOMContentLoaded to immediately set MessageWebView.Visibility=Visible. v0.3.31 PR #100 replaces early reveal with MainWindow.ReaderPresentation state machine: navigation ID and revision checks exclude prior letter; waits until full navigation and document.images report ready; host-originated JavaScript sets lazy images eager; a three-second bounded deadline stops incomplete resource loading before showing, allowing a hidden rendering interval; shows ready letter only once, with fallback for deadline and diagnosis reader_visual_ready complete/limited, elapsed-ms. Mail scripts remain disabled, tokens and contents never enter diagnostics. New ReaderPresentationPolicy and offline gating tests forbid reveal solely from DOMContentLoaded. PR CI 37860604229 PASS, main CI 37860751548 PASS; merged 277a3dc67cac7792715accf6b2edc71193742900. Release workflow 37860887859 requested full/update v0.3.31 installers. Owner has NOT yet re-tested actual K-001 video scenario; CI does not prove WebView2's pixel-level result. Bounded deadline can mean some late images remain absent; avoiding late content reflow is prioritized and must be checked with Owner. Previously accepted contacts, avatar retrieval, template fixes, updater unchanged. Scheduled Send Now issue #77 still blocked.

source: Owner video and explicit approval; GitHub PR #100, Actions 37860604229/37860751548, code MainWindow.ReaderPresentation.cs/ReaderPresentationPolicy.cs.
authority: Owner runtime for v0.3.30 defect, repository/CI for v0.3.31 patch; Owner runtime acceptance pending.
supersedes: B-044 as latest version; in particular v0.3.30 DOMContentLoaded-as-visible behavior was a regression, NOT a fix for flicker.


## B-046 — v0.3.32: WebView2/WPF отрисовка и постоянная страница письма

statement: На новом непрерывном видео пользователь воспроизвёл мерцание/наложение при переключении писем в v0.3.31, особенно на кадре с чеком Магнита поверх предыдущего письма технической поддержки. Пользователь прямо отверг предложение второго экземпляра WebView2 и потребовал точной причины без «костылей». Анализ main показал, что DisplaySummary при каждом выборе устанавливал MessageWebView.Visibility=Hidden, поверх показывал ReaderLoadingOverlay (WPF), вызывал WebView2.Stop; ShowReaderDocument выполнял NavigateToString заново, затем RevealReaderOnceAsync снова устанавливал Visibility=Visible. WPF и WebView2 используют несинхронные области рисования; это создаёт условия для пропусков/остатков кадров, хотя по записи экрана камера может дополнительно накладывать соседние кадры. PR #102 устранил WPF перекрытие и все переходы Visibility/повторную навигацию для отдельных писем. Остался ровно один WebView2 с единожды созданной постоянной HTML-страницей. Управляющий код готовит изолированное содержимое внутри неё, отслеживает готовность изображений, ограничивает ожидание и одним выполнением браузерного сценария заменяет прежний документ; заголовок обновляется после успешной публикации. Почтовый HTML помещён в изолированную область без выполнения его сценариев, управляющий сценарий запускает только приложение; JSON кодирование исключает интерпретацию пользовательского HTML как управляющего JavaScript. Поколение выбранного письма и ID предотвращают показ запоздалых результатов. Новый WindowsUiSmoke с реальным WebView2 на двух тестовых письмах прошёл: один экземпляр, два последовательных HTML, ноль новых навигаций корневого браузера, постоянно Visible. PR CI 37865194581 PASS; main CI 37865347046 PASS; merge ae6968c0352a05badd278f31697e5f182b59f4eb. Визуальное исчезновение мерцания на личной записи владельца НЕ подтверждено до его проверки установленной v0.3.32. Не предлагать два экземпляра WebView2. Принятые функции Mail.ru, шаблонов, фото отправителей и аккаунтов не перерабатывались. Issue #77 по-прежнему заблокирована.

source: Owner frame/video + explicit direction, PR #102, main CI 37865347046, MainWindow.ReaderPresentation.cs, ReaderShellScripts.cs, WindowsUiSmoke.
authority: Owner reproduction v0.3.31 + source/CI for v0.3.32; user runtime acceptance pending.
supersedes: B-045 for latest reader architecture; v0.3.31 Stop/Hidden and WPF overlay are retired.


## B-047 — владелец подтвердил решение задач 1–3 в v0.3.32

statement: 2026-10-09 владелец прямо подтвердил, что мерцание писем, некорректные встроенные изображения и задержки отображения устранены. Это подтверждение владельца на его установленном приложении, не независимый замер менеджера. Не изменять принятую схему одного WebView2 без нового дефекта.
source: прямое сообщение владельца в чате 2026-10-09: «задачи 1-3 решены».
authority: owner-runtime-report.
supersedes: B-046 в части ожидания пользовательской приёмки задач 1–3.


## B-048 — механизм уведомлений нового письма в APK восстановлен статически

statement: В Android APK Mail.ru 15.107.0.148045 действуют GCM, HMS и VKPNS варианты транспорта; сервис RuStore MailMessagingService получает событие, передаёт слушателю, отдельный CopyPushTokensToPushMeSDK регистрирует токены соответствующих каналов, NewMailPush содержит идентификатор, отправителя, тему, папку, время и дополнительные поля, PushMessageServiceVisitor вызывает NotificationHandler.showNotification. PushMeSDK обслуживает регистрацию аккаунтов/токенов и служебный учёт событий. Наличие совместимого доставщика/токена на Windows не доказано, точные HTTP-методы PushMeApiImpl частично не декомпилированы. Не подменять механизм опросом почты.
source: выборочная полная декомпиляция APK, Actions 37870637190 PASS; исследовательский коммит 2ef598f7b58ec2e4371cdc4bbaa49484e958a4c3; docs/research/android-new-mail-notifications-2026-10-09.md.
authority: verified-repository-static-apk; NOT live network validation or Windows feasibility.
supersedes: предварительное предположение о неизвестной внутренней цепочке уведомлений в B-047/предыдущем исследовании.


## B-049 — подтверждён альтернативный Google-канал на телефоне без RuStore/VK

statement: Владелец сообщил о своевременных уведомлениях оригинальной Почты Mail на телефоне без установленных RuStore и VK. APK предоставляет GCM/FCM, HMS и VKPNS через PushFactoryCreatorKt; PushProcessor по event=4 собирает NewMailPush, поэтому RuStore/VK не обязательны. Наблюдение НЕ исключает системных служб Google Play/Huawei. Документация Google подтверждает зависимость FCM от Google Play services. Дополнительно выявлена сопровождаемая в 2026 году реализация независимого настольного приёмника Superhuman/push-receiver v2.1.7 (Firebase Installations, FCM Registrations, TLS mtalk.google.com:5228, подтверждения и устранение дублей), исправления от июня–июля 2026 подтверждают фактическую работу с изменёнными пакетами Google. Приём FCM токена сервером Mail.ru и событие на Windows НЕ проверены. Исследование docs/research/android-new-mail-notifications-2026-10-09.md.
source: прямой отчёт владельца 2026-10-09; APK статический PushType, PushFactoryCreatorKt, PushProcessor; GitHub superhuman/push-receiver commit 87395486756f137b70c8a51d998dc6a6c57a5da6; официальное описание Firebase зависимости.
authority: Owner report + verified repository source + public source for third-party candidate; no Windows live validation.
supersedes: любые предположения, что RuStore/VK требуются обязательно; не превращать это в утверждение, что на телефоне нет Google/Huawei служб.


## B-050 — оригинальный Google FCM канал подтвердился полной декомпиляцией

statement: Успешный GitHub Actions 37872182332, коммит исследовательской ветки 7d5de6c41af591c0e4eaca30ee601bba4a2ea004, исходные 93 файла. SetUpPushComponent сначала выбирает GCM при доступности Google Play Services, иначе HMS при доступности Huawei, может добавить VKPNS. GCMAvailabilityChecker напрямую проверяет Google Play Services. GcmPushKitWrapper получает токен через FirebaseInfoProvider.getToken(push_sender_id). MailMessagingService наследуется от FirebaseMessagingService и на onMessageReceived передаёт данные в PushMeSdk и уведомитель, на onNewToken регистрирует обновление. SetUpPushMeSdk получает реальный ru.mail.oauth2.access на каждый почтовый аккаунт, PushMeSDKPusherTransport регистрирует эти аккаунты в серверном PushMe. Это статическое подтверждение механизма оригинального APK, но не доказательство совместимости независимого Windows FCM-токена с Mail.ru.
source: исследовательская ветка APK 15.107.0.148045, GitHub Actions 37872182332 PASS; docs/research/android-new-mail-notifications-2026-10-09.md.
authority: verified-source-static-APK, no Windows Mail.ru live verification.


## B-051 — Точная мобильная идентичность PushMe

Владелец требует использования конфигурации официального APK без регистрации нового продукта Mail.ru. В каждой Windows-установке при этом необходим собственный действительный токен доставки: повторное использование телефонного токена недопустимо. Снимок APK 15.107.0.148045 SHA-256 PASS, Actions 37874537322 SUCCESS: push_sender_id=1098335887158 не равен gcm_defaultSenderId=61247752867; google_app_id=1:61247752867:android:d199c9f145040309; project_id=fluorcorpmailru; push host=alt-push-me.mail.ru; api=api. GcmPushKitWrapper запрашивает токен именно с push_sender_id. PushMeSDK внутренне FIREBASE='fcm', но поле platform JSON='android'. Реальная регистрация Windows и принятие токена сервером не испытаны. Источник docs/research/mailru-mobile-push-contract-2026-10-09.md.


## B-052 — независимая регистрация Google и MCS под оригинальным отправителем доказана

Исходный APK 15.107.0.148045 статически вызывает FirebaseInstanceId.getInstance().getToken(R.string.push_sender_id, FirebaseMessaging.INSTANCE_ID_SCOPE), где push_sender_id=1098335887158 (не дефолтный 61247752867). GitHub Actions 37874791066 SUCCESS добыл класс FirebaseInstanceInfoProvider. Отдельный GitHub Actions 37875870944 SUCCESS: временный независимый Google check-in OK, ответ register3 для ru.mail.mailapp и sender=1098335887158 — TOKEN_ISSUED. GitHub Actions 37876061718 SUCCESS: тот же checkin OK, регистрация TOKEN_ISSUED, защищённый вход на mtalk.google.com:5228 — LOGIN_OK. Исходники tools/push_research/google_sender_probe.py и отчёты в исследовательской ветке. Это НЕ подтверждает длительную доставку, равенство токена Android Firebase IID, подписку Mail.ru PushMe или event=4; данные Google эфемерны и не сохранялись. Договор docs/research/mailru-mobile-push-contract-2026-10-09.md, основной продукт не изменён.


## B-053 — готова переносимая испытательная программа Windows, но нет подтверждённого события Mail.ru

После подтверждения Google TOKEN_ISSUED и MCS LOGIN_OK собрана отдельная программа tools/push_research/local_mailru_event_probe.py (исследовательская ветка). Её цепочка: читать существующий мобильный OAuth из защищённого DPAPI-хранилища только на ПК владельца; получить новый токен Google sender=1098335887158; войти в MCS; отправить одну PushMe подписку application=mail/platform=android для выбранного аккаунта; дождаться event=4 с category=ru.mail.mailapp; снять ТОЛЬКО собственный временный токен. Программа не запускается в режиме настоящей подписки без явных --live --subscribe --login, не записывает почтовые токены/письма, а GitHub используется только для проверки искусственных данных. Offline Windows/Linux tests Actions 37877281592 и 37877932559 PASS, включая реальный цикл Windows DPAPI на несуществующем тестовом значении; переносимый EXE собран и проверен Actions 37877413352 PASS, односуточный CI артефакт, НЕ релиз. Перечень ограничений docs/research/windows-push-trial-2026-10-09.md. Точная фактическая структура capabilities из APK ещё не восстановлена; нельзя считать этот EXE готовым к испытанию реального события. Source extraction Actions 37877449399 pending. Составленная подписка Mail.ru не испытывалась на настоящем ящике.


## B-054 — официальная структура уведомлений can_mail восстановлена и подготовлена к локальному испытанию

SHA-256 оригинального APK Mail.ru 15.107.0.148045 и целевая декомпиляция класса ru.mail.util.push.provider.impl.MailCapabilitiesProvider подтверждены workflow 37878519042 SUCCESS. При needPushMsg=true capabilities.can_mail содержит Filter.Folder(filterList/enabled), Filter.SocialNetwork(excludeList/enabled), Filter.SocialService(excludeList/enabled); при выключенных уведомлениях can_mail=0. Опционально actual_support и tags. Текущий SDK com.vk.pushme.logic.usecase.SubscriptionUseCase.isV1(application) всегда false: подписка именно POST /api/v2/set_settings; account приводится к нижнему регистру, status=0, используется OAuth текущего аккаунта. Точный sdk ClientInfoProviderImpl использует поля info,platform,version,name,type,lang; часовой пояс GMT+HHMM без двоеточия. В исследовательской программе применён can_mail c отключёнными тремя фильтрами; это выбранный для испытания вариант, не утверждение о сохранённых настройках телефона. Фактические значения описания телефона в Windows опыте синтетические, не официальные. Windows/Linux автономные тесты 37878867912 SUCCESS, исправленная переносимая Windows-сборка 37878867920 SUCCESS, краткосрочный CI артефакт, не релиз. Заявление о принятии нового токена Mail.ru и реальном event=4 НЕ доказано; без запуска на компьютере владельца с его легитимным DPAPI OAuth результата быть не может.


## B-055 — Подписка уведомлений теперь испытательно встроена в основной клиент .NET

Владелец отказался от отдельной программы и потребовал испытания внутри MailRu Desktop. По этому решению разработан на .NET 8 встроенный (не внешний Python) однократный приёмник src/MailRuDesktop.App/MailRuPushProbe.cs, нативное окно PushProbeWindow.cs и связь MainWindow.PushProbe.cs. Кнопка «Проверить получение новых писем» расположена в Настройках. Каждый опыт только после согласия и выбора сохранённого аккаунта; Google checkin/register3, TLS/MCS, один запрос PushMe V2, ожидание event4 до 3 минут, попытка снять только свой временный токен; события инициируют однократное обновление списка. Пароли/почтовые токены не выводятся и не отправляются в GitHub, новые постоянные файлы и второй WebView2 не создаются. Версия подготовлена 0.3.33. Продуктовый код включён в main, но реальный PushMe ACCOUNT_ACCEPTED / event4 на пользовательском ящике по-прежнему НЕ доказан; это именно испытательная функция. Сборка/CI и выпуск установщика ещё должны быть проверены.


## B-056 — v0.3.33 опубликована с нативной проверкой мобильной доставки

После успешной CI GitHub Actions 37880498382 (SUCCESS) и штатной сборки релиза Actions 37880613099 (SUCCESS) опубликован реальный выпуск v0.3.33 с двумя проверенными файлами: MailRuDesktop_Setup_v0.3.33.exe и MailRuDesktop_Update_v0.3.33.exe. Ссылки https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.33 . Событийный эксперимент встроен в настройках программы, не требует отдельного exe, Python или CMD. Реальный Mail.ru ACCOUNT_ACCEPTED и MCS event4 внутри программы пока не испытывались: выпуском доказана доставка программных файлов и проверок сборки, а не пуш-доставка писем.


## B-057 — Mail.ru AltProd has a certificate-chain failure; official Prod HTTPS works

User in-app v0.3.33 trial: Google check-in OK, TOKEN_ISSUED, MCS LOGIN_OK; Mail.ru PushMe account subscription failed with network error, cleanup unconfirmed. Independent GitHub Actions 37881120525 and 37881273739 verified alt-push-me.mail.ru DNS OK but TLS chain invalid (verify code20 unable to get local issuer certificate); official original APK PusherHost.Prod push-me.mail.ru TLS OK and empty JSON POST /api/v2/set_settings returned HTTP200. No passwords or OAuth were used in these tests. These observations strongly implicate AltProd TLS, though remote diagnostics cannot prove actual cause on user's Windows PC. v0.3.34 native .NET code now uses official Prod for both subscription and cleanup, retains strict TLS validation, reports sanitized DNS/TLS/HTTP categories. CI 37881506882 PASS, release Actions 37881628473 PASS; both full/update installers published v0.3.34. Actual account subscription and event4 remain unverified.

## B-058 — Выпущена v0.3.36 с одним постоянным Google-получателем

statement: По прямому решению владельца Windows MailRu Desktop перешёл от одного Google/MCS сеанса на почтовый аккаунт к одной сохраняемой идентичности Google на Windows-пользователя, одному TLS MCS и групповому PushMe V2 запросу настроек всех подключённых аккаунтов с отдельными OAuth. При событии event=4 требуется явный совпадающий account, иначе доставка в UI запрещена. Google device/token/список подписок защищены DPAPI в app-owned файле. Добавление аккаунта или обновление OAuth используют тот же токен. Удаление аккаунта требует подтверждённого unsubscribe_by_token прежнего ОБЩЕГО токена, затем выпуска нового и переподписки оставшихся: адресное удаление одного аккаунта сервером пока не валидировано. Обычный выход не отзывает идентичность. Автономные Windows CI 37923954067 SUCCESS; штатный релиз 37924183045 SUCCESS, v0.3.36 опубликован full/update. Доставка event4 на несколько настоящих ящиков в общей схеме НЕ проверена: предыдущее живое подтверждение относится к v0.3.35 с отдельным получателем.
source: прямое решение владельца 2026-10-09; main MailRuPushProbe.Shared.cs, SharedGooglePushIdentityStore.cs, MailRuPushBackgroundService.cs, InteractionLogicSmoke; docs/research/shared-google-push-2026-10-09.md; CI 37923954067 и release 37924183045; GitHub release v0.3.36.
authority: owner-directive + repository-source + CI; multi-account live runtime pending.

## B-059 — Сопоставление v0.3.36 с исходным PushMe SDK и выпуск v0.3.37

statement: После отчёта владельца «Google LOGIN_OK, затем обрыв; 0 из 32» по прямому правилу владельца исследован исходный APK без сетевых экспериментов с Mail.ru/Google. `PushMeApiImpl.parseSubscriptionResponseToResult` при `error.code=0` и отсутствии `validate_result` считает подписку OK; v0.3.36 ошибочно требовала `is_valid=true` для каждого аккаунта. Исправлена именно общая схема, добавлены автономные проверки исходных правил, безопасные категории HTTP/TLS/Google MCS ошибок. `NewSubscriptionRequest` хранит и объединяет подписки по аккаунту; `UnsubscribeRequest` предусматривает адресное снятие, а не обязательную смену общего токена. Полная реализация последнего вызова статически ещё не восстановлена и НЕ внедрена. Часть системного Google MCS находится в Google Play services, вне Android APK Mail.ru, и не может быть буквально скопирована из него. В v0.3.37 добавлены трей с «Развернуть»/«Выход», сворачивание X, отдельная настройка всплывающих уведомлений. CI 37929798142 SUCCESS, release 37930018273 SUCCESS, опубликованы Setup/Update v0.3.37; онлайн-проверки 32 аккаунтов не было. Не объявлять исходную причину обрыва установленной без диагностических статусов.
source: owner directive 2026-10-09; исследовательская ветка APK, com/vk/pushme/network/PushMeApiImpl.java, NewSubscriptionRequest.java, UnsubscribeRequest.java, ru/mail/util/push/gcm/MailMessagingService.java; docs/research/pushme-source-v036-comparison-2026-10-09.md; GitHub Actions CI/release IDs.
authority: owner directive + verified original APK code + CI; live delivery not validated.

## B-060 — Addressed unsubscribe derived directly from verified official DEX; released v0.3.38
statement: В результате прямого указания владельца запретить экспериментальный подбор запросов восстановлен оригинальный APK 15.107.0.148045 из GitHub multipart archive, SHA256 42c976a0c2f3d5fb186a88bc855f782c81c8ce35f952fcf22f99c64828880af6. Офлайн-байткод PushMeApiImpl.unsubscribeByDeviceId подтвердил POST /api/v1/unsubscribe_by_device_id, form-urlencoded account/device_id/application, success error.code=0; UnsubscribeUseCase.invoke использует DeviceIdProvider.getDeviceId, а не Google token. Удаление отдельного ящика в Windows теперь НЕ вызывает unsubscribe_by_token или Google token rotation, а отправляет адресную отписку, сохраняет общий MCS-канал для остальных, защищённый список неудачных отписок, исключает удалённого из входящих событий немедленно, использует актуальные аккаунты после реального переподключения. После подтверждённого PushMe результата локально сохраняются только подтверждённые подписки. CI 37936826274 SUCCESS, release 37937170853 SUCCESS, v0.3.38 Setup/Update опубликованы. Это доказательство контракта APK и успешной сборки, но не доказательство реальной доставки 32 аккаунтам или буквального Google Play services MCS.
source: original APK classes19.dex; docs/research/original-apk-pushme-unsubscribe-dex-2026-10-09.md; docs/research/apk-source-aligned-account-unsubscribe-2026-10-09.md; main MailRuPushProbe.Shared.cs, MailRuPushBackgroundService.cs, SharedGooglePushIdentityStore.cs; GitHub CI/release.
authority: owner-directive + APK bytecode + CI; live multiple mailbox state not tested.

## B-061 — v0.3.39 local Google/PushMe diagnostics, released 09.10.2026
statement: User shows ongoing 0/32 and Google/PusMe reconnect 45→90 sec in latest Windows desktop; root cause NOT known from status label. By direct request added safe bounded local phase log in PushDiagnostics.cs (Google identity / TCP / TLS / MCS login tags / PushMe HTTP status/server code/accepted count / MCS receive/keepalive/close / exact failing phase / reconnect attempts). File is %LOCALAPPDATA%/MailRuDesktop/push-diagnostics.log, bounded 256 KiB, no addresses, OAuth/FCM tokens, bodies, exception messages. Settings has live 140-line viewer and buttons Refresh/Copy full/Save TXT/Clear. Original protocol is unchanged; no experimental network traffic. UI status shows original failing phase instead of generic retry handler. Offline tests validate data safety, logging, view and Win UI. Windows CI 37939894913 SUCCESS, release 37940096624 SUCCESS, full/update v0.3.39 published. Cause of MCS/PushMe disconnection still pending report from user's installation.
source: direct owner request 2026-10-09; main PushDiagnostics.cs, MailRuPushProbe.Shared.cs, MailRuPushBackgroundService.cs, MainWindow.PushProbe.cs, MainWindow.xaml, tests; docs/research/google-pushme-diagnostics-2026-10-09.md; GitHub Actions.
authority: source+CI+release for diagnostic feature; live delivery unverified.

## B-062 — v0.3.39 journal identified PushMe error.code 499, v0.3.40 adds safe server reason
statement: User v0.3.39 logs 2026-10-09 18:31–18:33 MSK showed 32 accounts; Google identity created then reused, TCP TLS, MCS LOGIN_OK tag3/version41 on BOTH attempts; PushMe HTTPS HTTP200 on both, but JSON error.code=499 on both. Desktop closes the still healthy Google MCS solely because it throws after PushMe response, then retries in 45/90 secs. 499 is JSON application code, NOT nginx HTTP499. Original APK PushMeApiImpl maps nonzero error.code to ServerError(code,message); APK contains no proven fixed meaning for 499. Original SubscriptionUseCase batch limit 20 and SubscriptionBatcher group by application atomically: do NOT automatically split one mail application's 32 accounts as 20+12 without source. Original DeviceIdProviderImpl.getDeviceId() delegates to getSdkDeviceId(), so equality of device_id and sdk_device_id is original behavior. Windows v0.3.39 discarded error.message. v0.3.40 captures only strictly allowlisted safe vocabulary from error.message, removes URL/emails/opaque tokens first and records SERVER_REASON_; distinguishes UI "PushMe отклонил регистрацию" from physical MCS break; does not change requests. CI 37953889320 PASS, installer release 37954095452 PASS, Setup/Update v0.3.40 published. Still no proven server-side root cause of 499; await report with safe reason.
source: user diagnostics 2026-10-09, original APK research branch PushMeApiImpl.java, SubscriptionUseCase.java, SubscriptionBatcher.java, DeviceIdProviderImpl.java, InternalSubscriptionRequest$$serializer.java; main PushDiagnostics.cs, MailRuPushProbe.Shared.cs, MailRuPushBackgroundService.cs; docs/research/pushme-api-499-2026-10-09.md; Actions.
authority: owner live observed diagnostics + verified source + offline CI. Code 499 meaning unverified.

## B-063 — PushMe 499 INVALID_FORMAT: найдено исходное несовпадение CommonId; v0.3.41 выпущена
statement: Пользовательский журнал v0.3.40 09.10.2026 18:51–18:52 МСК: MCS LOGIN_OK (41/tag3), HTTP200, PushMe error.code499, обезличенное error.message=INVALID_FORMAT для 32 аккаунтов. После отказа сама Windows служба останавливает приём; RECONCILE_DISABLED 18:52:18 без доказательства его инициатора. Исследование оригинального APK 15.107.0.148045, проверенного по SHA256 42c976a0c2f3d5fb186a88bc855f782c81c8ce35f952fcf22f99c64828880af6, из classes3.dex подтвердило CommonIdProvider: сохраняет __common_id_value__; ID создаётся как Android Settings.Secure.android_id + ":" + MD5 конкатенации Build.PRODUCT/BOARD/BOOTLOADER/BRAND/DEVICE/DISPLAY/FINGERPRINT/HARDWARE/HOST/ID/MANUFACTURER/MODEL/TAGS. DeviceIdProviderImpl.getDeviceId()==getSdkDeviceId()==CommonId; отдельное android_id идёт из Settings.Secure. Windows v0.3.40 ошибочно отправлял десятичный Google MCS DeviceId как android_id, а sdk_device_id/settings.device_id = mailru-windows-<hex-google-id>. Исправлена структура: отдельный виртуальный AndroidID (16hex), стабильный CommonId=ID:MD5(фиксированного виртуального Build-профиля) в DPAPI, Google MCS token не меняется, три поля согласованы. Поскольку Windows не имеет реальных Android Settings.Secure/Build, виртуальный профиль — честно обозначенная адаптация, не буквальная копия телефона. Сборка CI 37957052697 SUCCESS, release 37957368274 SUCCESS, v0.3.41 Setup/Update опубликованы. INVALID_FORMAT поля не назвал; факт успешной подписки на 32 аккаунта после исправления НЕ подтверждён.
source: user 0.3.40 diagnostic 2026-10-09; docs/research/original-apk-pushme-unsubscribe-dex-2026-10-09.md; docs/research/pushme-invalid-format-commonid-2026-10-09.md; originals DeviceIdProviderImpl, CommonIdProvider classes3.dex; main SharedGooglePushIdentityStore.cs, MailRuPushProbe.cs, MailRuPushProbe.Shared.cs; Github CI/release.
authority: user real server trace + original APK bytecode + Windows CI; live post-fix pending.

## B-064 — MCS_LOGIN_REJECTED from v0.3.41, v0.3.42 sourced fix
statement: On 09.10.2026 user shared v0.3.41 19:18 logs with 32 accounts, IDENTITY_CREATE, TCP+TLS success, MCS LoginResponse version41/tag3 then local MCS_LOGIN_REJECTED, no PushMe call. This is NOT proof Google actually rejected authentication. Verified original Chromium mcs.proto LoginResponse tag3 has required id field1 and optional ErrorInfo field3, where nested ErrorInfo.code field1; Chromium mcs_client.cc fails only if has_error AND error.code != 0. Windows v0.3.41 rejected ANY ErrorInfo field irrespective of code. Source-backed fix v0.3.42 parses nested code, accepts zero or absent error, rejects nonzero/malformed; logs only flags and numeric error code, never raw body/token. Applied to shared and manual MCS receiver; added entirely offline protobuf fixtures (absent error, 0, 7, missing code, no id, bad version/tag). Disable checkbox triggers Reconcile false; successful UnsubscribeSharedAsync revokes Google token and deletes persisted identity; explains next IDENTITY_CREATE but log alone cannot prove user toggled. Added UI checkbox and identity-revocation diagnostic codes. Build initially failed CS0136 due local login variable shadowing, fixed; final CI 37959083089 SUCCESS; release 37959302501 SUCCESS; v0.3.42 Setup/Update published. Not proven that user's actual v0.3.41 login error was zero; live post-fix status pending; original PushMe 499 INVALID_FORMAT remains unverified as fixed.
sources: user v0.3.41 diagnostics; Chromium https://chromium.googlesource.com/chromium/src/+/66.0.3359.158/google_apis/gcm/protocol/mcs.proto and https://chromium.googlesource.com/chromium/chromium/+/trunk/google_apis/gcm/engine/mcs_client.cc ; docs/research/google-mcs-login-rejection-v0341-2026-10-09.md; source MailRuPushProbe.cs, MailRuPushProbe.Shared.cs, MainWindow.PushProbe.cs, InteractionLogicSmoke; CI/release.
authority: user trace + primary upstream protocol + CI; live outcome unknown.

## B-065 — v0.3.43 is manual selected-single-account regression release
statement: Following user's explicit request, v0.3.43 disables automatic PushMe subscriptions for all saved mailboxes and instead permits explicit manual test of exactly one selected account from existing PushProbeWindow. The one-account wire profile in MailRuPushProbe.RunAsync is restored from verifiably successful v0.3.35 (decimal Google device ID as android_id, separate 24-hex mailru-windows trial device ID, one-item JSON array, isolated short-lived Google token, individual unsubscribe_by_token cleanup). Multiaccount worker StartBackgroundPush does NOT call Reconcile(false) because normal opt-out would revoke shared token; SyncBackgroundPush early-exits in diagnostic release. Background push setting disabled visually but persistent prior preference retained; Windows tray remains active and TaskbarNotificationsEnabledCheckBox remains separately configurable; OnPushNewMail uses NotifyIcon.ShowBalloonTip for real new-mail event. Existing manual test runs 3 minutes and requires account selection + consent. Offline regression asserts JSON one account/old fields, WPF smoke enforces manual-only/consent/tray-notification preference. Windows CI 37962087207 SUCCESS, installer Actions 37962300787 SUCCESS, full/update release https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.43 . Not a live acceptance of PushMe; user verification pending. Existing shared PushMe error499 INVALID_FORMAT not resolved and common registration intentionally disabled.
source: owner directive in current chat 09.10.2026, historical v0.3.35 source and one real event in archived discussion; main MainWindow.PushProbe.cs, MailRuPushProbe.cs, MainWindow.xaml, tests; GitHub release job.
authority: direct owner + source comparison + CI, no fresh user live event.

## B-066 — v0.3.44 controlled nineteen-account test
User confirmed real single account success in v0.3.43: 19:58:24 ACCOUNT_ACCEPTED, 19:59:18 NEW_MAIL_EVENT_RECEIVED=YES, 19:59:23 temporary unsubscribe completed. User requested 19 account registration to test possible Mail.ru web 20-account cap, while retaining successful one-account test. Web limit is unproven for PushMe. v0.3.44 allows manual selection exactly 19, one shared Google receiver and one unchanged group PushMe JSON request for selected 19, no auto registration of all 32. On refusal no automatic retry. Stops via account-level unsubscription without global Google token revocation, preserves unrelated existing registrations. Windows balloons remain. CI run 37964813669 SUCCESS; release run 37965013293 SUCCESS; v0.3.44 Setup+Update published. Live server result for 19 pending.

## B-067 — Реальная приёмка общей PushMe-подписки 19 аккаунтов (10.10.2026)
Установленный факт по скриншоту владельца установленной MailRu Desktop v0.3.44: в журнале 00:24:54 +03:00 [FLOW] PUSHME_HTTP_SEND count=19, [PUSHME] HTTP_STATUS=200, SERVER_API_CODE=0, [FLOW] PUSHME_ACCEPTED count=19, REJECTED_COUNT=0 и MCS_RECEIVE_STARTED; в 00:26:05 [MCS] NEW_MAIL_EVENT. В отдельном окне отображено "Mail.ru: ACCOUNT_ACCEPTED" и "MAILRU_NEW_MAIL_EVENT_RECEIVED=YES". Владелец подтвердил фактическое всплывающее уведомление Windows. Тем самым один общий Google-токен+канал и групповая подписка 19 работают в реальных условиях. Ранее 32 аккаунта дали HTTP200 JSON error.code=499 SERVER_REASON_INVALID_FORMAT. Это подтверждает зависимость результата от изменённого состава/размера запроса при сравнимом групповом алгоритме, но НЕ доказывает точный предел 20, не объясняет серверное правило и не доказывает доставку по каждому из 19 по отдельности. Не нарушать работающий контрольный вариант и не считать это автоматическим постоянным приёмом после перезапуска: v0.3.44 вручную запускает группу. Исходная гипотеза о веб-лимите 20 остаётся вероятной, а не доказанной причинной связью.
source: пользовательский скриншот и сообщение о реальном получении уведомления 10.10.2026; GitHub v0.3.44.
authority: прямое подтверждение пользовательским реальным тестом.

## B-068 — v0.3.45: раздельная Google-регистрация, сохранённые группы PushMe, статус MCS
statement: по прямому поручению владельца после реального PASS 19/19 на v0.3.44 разработана v0.3.45. 1) явная первоначальная Google Checkin/register3 с хранением Google DeviceId, SecurityToken, RegistrationToken и независимого CommonId через DPAPI; повторные подключения MCS и группы не создают новую Google-регистрацию. 2) отдельная регистрация выбранного числа свободных аккаунтов одним запросом PushMe с тем же сохранённым Google-токеном; групповой реестр %LOCALAPPDATA%/MailRuDesktop/push-groups.dat зашифрован DPAPI, состав каждой группы, дата, состояние, один аккаунт только в одной группе. Старая подтверждённая запись SubscribedAccounts импортируется однократно как «восстановленная группа» без сетевых запросов. 3) MCS-цикл отдельно, только чтение сохранённых подписок, без повторного PushMe POST при старте/переподключении; в интерфейсе «Подключается», «Подключён», «Переподключается», «Остановлен», «Ошибка соединения». 4) «Остановить приём» = только отменить MCS TCP/TLS и сохранить группы/Google; отдельное адресное удаление группы; Google удаляется только отдельным подтверждением после снятия всех подписок. При регистрации второй группы предыдущие группы помечаются «доставка не проверена», реальный event для одного адреса помечает «событие получено» (не гарантия для всей группы). Исторический одиночный тест сохранён и по-прежнему использует отдельный временный получатель Google — исключение для диагностики, НЕ режим постоянных групп. Windows CI 37996803222 SUCCESS, installer release 37997098967 SUCCESS, v0.3.45 Setup+Update опубликованы https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.45. Реальная приемка последовательных групп с одним Google токеном — ОТКРЫТА.
source: user request 10.10.2026, main MainWindow.PushGroups.cs, PushGroupRegistryStore.cs, PushSubscriptionManagerWindow.cs, MailRuPushProbe.Shared.cs, MailRuPushBackgroundService.cs, MainWindow.PushProbe.cs; docs/research/pushme-persistent-groups-mcs-lifecycle-v0345.md; CI and release.
authority: verified implementation + offline Windows CI, NOT live acceptance for multiple groups.

## B-069 — Причина 13 вместо 19 после перехода v0.3.44 → v0.3.45 подтверждена журналом владельца
10.10.2026 пользователь показал исходный лог v0.3.44: в 00:24:54 +03:00 PushMe принял 19/19, в 00:26:05 пришло новое письмо; после ручного MANUAL_GROUP19_STOP в 01:07:23 MCS остановлен и последовали ровно шесть ACCOUNT_UNSUBSCRIBE_OK, затем ACCOUNT_UNSUBSCRIBE_START для седьмого, но до его результата программа перезапущена в 01:08:01. Поэтому сохранилось 13 записей для миграции в v0.3.45. Каким конкретно адресам соответствуют 6 успехов и завершилась ли седьмая операция на сервере, журнал не показывает. Не заявлять, что все 13 обязательно активны на сервере: мигрированный локальный список не проверен приёмом. В v0.3.45 выбор группы в списке не влияет на кнопку Подключить MCS: приемник строится по ВСЕМ сохранённым группам и пересечению с SubscribedAccounts и действующими OAuth. Сейчас группа одна (13), поэтому это 13 кандидатов на события, при серверной активности их подписок. Кнопка Подключить MCS НЕ регистрирует Google и НЕ отправляет PushMe set_settings.
source: пользовательский фрагмент журнала 2026-10-10 00:24–01:08, скриншот реестра v0.3.45, MainWindow.PushGroups.cs, RunSharedAsync(listenOnly), stop logic.
authority: прямое наблюдение + текущий исходный код.

## B-070 — новый реальный результат: вторая группа PushMe заменяет первую
10.10.2026 владелец подтвердил в v0.3.45: восстановленная группа №1 (13 аккаунтов) через подключение только MCS успешно принимала уведомления; зарегистрирована группа №2 (7 аккаунтов), PushMe принял регистрацию и события второй группы пошли, но уведомления первой прекратились. Установлен эффект замещения ранее работающего набора в реальном тесте последовательных POST set_settings с одним Google-токеном. Не утверждать без нового исследования, что API всегда замещает абсолютно все подписки; локальное наличие групп ≠ текущая серверная подписка. Важно корректно отделять локальный учёт от реальной доставки и не обещать параллельную активность групп.
## B-071 — опубликована v0.3.46: подробные адресные логи и тихое обновление
На требование владельца логировать индивидуальные операции добавлен PushDiagnostics.RecordAccount: локально записывает адрес аккаунта, группу-ID, позицию i/N, идентификатор операции, HTTP статус, итоги ACCEPTED/REJECTED/UNCONFIRMED, START/OK/DEFERRED адресных отписок; MCS NEW_MAIL_EVENT и наблюдаемая доставка также привязаны к адресу. Экспорт отчёта из настроек выбирается явно: обезличенный (по умолчанию, псевдонимы ACCOUNT_001...) или полный; токены, пароли и тела писем не логируются. В окне групп живой журнал операций. Исправлен вводящий в заблуждение текст об отсутствии влияния новых групп: теперь предупреждение о возможном замещении. Обновление из приложения запускает Inno с /SILENT /SUPPRESSMSGBOXES /NORESTART /CLOSEAPPLICATIONS, в обновляющем установщике исключено пересоздание [Icons]/[Tasks], отключены обычные страницы мастера, после копирования программа запускается автоматически [Run]. Сохраняется существующий Desktop .lnk, что защищает его положение. ВНИМАНИЕ: код и сборка проверены CI, а фактическая работа обновления на Windows владельца и расположение ярлыка после первого обновления ещё НЕ подтверждены. CI 38000806394 SUCCESS, release 38001062877 SUCCESS, v0.3.46 Setup и Update опубликованы https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.46 .

## B-071 — переход в новый чат после замещения второй группой первой (10.10.2026)
Зафиксировать как непосредственное наблюдение владельца: в v0.3.45 группа №1 из 13 аккаунтов после «Подключить MCS» реально принимала уведомления; затем группа №2 из ещё 7 была успешно зарегистрирована и стала принимать уведомления, но уведомления ранее работающей группы №1 прекратились. Это свидетельствует о серверном замещении действующего набора на общем Google получателе, но без серверной спецификации нельзя утверждать точную семантику/API-ограничение. Пользователь хочет удалить обе группы и пересобрать ОДНУ группу большего размера, но только при наличии достаточно подробных логов. Сохранённая актуальная капсула уже фиксирует релиз v0.3.46, включающий адресные журналы и обновлятор. После его установки пользователем реальная диагностика операций удаления пока не поступала. Скриншот последнего сообщения показывает ограничение длины чата и необходимость перехода в новый разговор. Текущее действие: сохранение капсулы, без сетевых запросов и изменений программы.
