# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice

status: completed

verification: repository `lvlaksim1/mailru-desktop`; product commit `48adb2ba1236a2e49b39562b5ddd7ee53c5556af`; CI run `37335195622`.

## I-002 — turn raw thread status into mailbox MVP

status: completed for list/folder MVP

commitment: Render the verified smart-thread response as a usable mailbox and keep protocol parsing outside WPF.

progress: v0.1.2 fixed the live `folders_content -> threads -> base_message` shape, added a real folder sidebar, accurate folder counters and a usable structured list. v0.1.4 additionally wires full-message reading through the touch endpoint for owner validation.

## I-003 — expand reverse API coverage

status: active — implementation present, owner validation pending

commitment: Reconcile externally discovered Mail.ru endpoint families against owner VBA and fresh local traffic, and promote only locally reproduced operations to A-level evidence.

progress: v0.1.4 exposes touch full-message, incoming attachment download, server search, contacts, move-to-Trash and permanent remove. All remain B-level until current owner runtime reproduces them.

## I-004 — expose verified send flow in UI

status: completed

verification: product commit `1420d1619c16deb2dce150cd8882ee86431b2465`; CI run `37335834260`.

## I-005 — establish installer/update release channel

status: completed

verification: current release is `v0.1.4`; release workflow `37367733108` succeeded; Setup + Update are release assets; Actions artifacts are absent; older binary Releases are pruned.

## I-006 — identify and verify full-message read

status: active — UI implementation complete, owner validation pending

commitment: Determine the live full-message/thread schema, attachment representation and read-state side effects.

progress: v0.1.4 wires Hackus-derived `GET touch.mail.ru/api/v1/messages/message` into the reading pane and parses HTML/text, correspondents and attachments while preserving raw diagnostics. Promotion to A-level awaits live owner reproduction.

## I-007 — eliminate false token_missing login failures

status: active — interactive challenge implementation complete, owner validation pending

commitment: Treat missing mobile token as a state transition rather than a terminal failure; support cookie-session challenges and preserve successful authorization securely.

progress: v0.1.4 adds explicit state machine including reCAPTCHA, CAPTCHA, 2FA, Blocked and recovery. Challenge completion is manual inside an isolated embedded Mail.ru WebView2 session; no external CAPTCHA solver is used. New login attempts start with a fresh cookie-session. Unknown auth outcomes are saved as sanitized diagnostic reasons.

## I-008 — incoming mail operations

status: active — implementation complete, owner validation pending

commitment: Provide controlled desktop operations for incoming mail using reverse-engineered touch/web APIs.

scope implemented in v0.1.4: direct attachment download with original filename/MIME, server search, contacts, move to Trash, move to selected folder and permanent remove.

completion contract: owner reproduces each operation successfully on disposable/current account data and response behavior is promoted to A-level evidence.
