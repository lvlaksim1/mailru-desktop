# Manager intentions and commitments

## I-001 — bootstrap repository and first protocol slice
status: completed

## I-002 — mailbox list/folder MVP
status: active — tolerant parser released and retained

The mailbox-list transport remains /api/v1/m/threads/status/smart with the mailbox OAuth token. Parser behavior must remain tolerant across all observed server shapes: base_message, messages[], direct thread-like objects, and threads[].representations[].

## I-003 — complete access-token API coverage
status: active — static discovery completed; staged product integration active

APK 15.107.0.148045 is statically mapped. Newly enabled operations still require live or Owner-runtime validation before they are treated as stable.

## I-004 — send/scheduled-send flow
status: active

Immediate send, reply with attachments, server draft saving, signatures and named message templates are implemented. Exact delayed-send send_date semantics still require dedicated validation.

## I-005 — installer/update release channel
status: completed for v0.3.18 delivery

GitHub Release v0.3.18 is published with both full and update installers. Release workflow 37707435464 succeeded. Future versions continue through this established installer pipeline.

## I-006 — full-message read and incoming attachments
status: released

The preview pane is the normal full-message workspace. Full body, selectable addresses/subject, reply, forward, move/archive/trash, attachments and reply attachments remain supported.

## I-007 — mailbox OAuth authorization
status: released and Owner-runtime confirmed for interactive verification

Authorization obtains the mailbox OAuth access_token. Interactive verification is handled in a persistent browser profile isolated per mailbox account. Owner runtime on 2026-10-08 confirmed a previously problematic account completed a phone-confirmation step and authorized without password recovery. Do not revert to a disposable profile or mix these profiles with normal desktop browsers. No challenge solver or verification bypass is allowed. Stored refresh_token remains the normal expired-token recovery mechanism.

## I-008 — incoming message actions
status: active — core per-message actions released

Read/unread, move/archive/trash, permanent removal from Trash, flagged and pinned marks remain released. Opening a message marks it read automatically; the selected row can be marked unread again. Bulk/thread, spam/unspam and category operations remain later work.

## I-009 — desktop UX/settings
status: active — v0.3.18 released; Owner runtime validation pending

The v0.3.18 merged baseline includes:
- saving/restoring window normal/maximized state and panel/column widths;
- preserving the divider between message list and message preview;
- darker scrollbar thumb/arrows in dark theme;
- compact icons to the left of sender names and account addresses without increasing row height;
- persistent account ordering;
- account sections/headings with the expand/collapse arrow on the right of the section name;
- account dragging after 250–300 ms hold (implemented at 275 ms): the row detaches while the mouse remains held, follows the pointer, neighboring accounts animate out of the way, and mouse release fixes the new position; Escape has no role;
- preview toolbar relocation requested by Owner;
- reply/forward form shown above the message body;
- Settings sections for named signatures and named message templates;
- templates carry subject, body and attachments;
- reply form selectors insert a signature or populate from a template.

## I-010 — high-value access-token expansion
status: active — secondary to v0.3.18 Owner runtime validation

After v0.3.18 is published and validated, resume thread/bulk actions, spam/unspam, subscription/category actions, attachment lifecycle, EML/metadata/read receipt and search improvements. go.mail.ru new search remains deferred while its live HTTP 520 behavior is unresolved.

## I-011 — multi-account correctness
status: active

Each account must independently preserve folders, messages, preview content, unread counts, authorization browser profile, ordering and section membership. Rapid account switching must not leak or mix state.


## I-012 — real avatars and manually managed Markdown templates
status: active — released; real-user behavior not fully validated

The Owner requires actual available contact portraits for message senders and accounts, using initials only when no usable photo exists. The filin.mail.ru/pic?email URL was located in official APK strings and wired to both views with a 16 px footprint. Verify retrieval of real avatars and fallback on the Owner's actual Windows machine.

Each named message template is represented by a top-level Markdown file with the exact template name as its filename, stored in %LOCALAPPDATA%/MailRuDesktop/Templates. Manual creation/edit/rename/delete must update the in-app list, not depend on settings.json. An optional header defines subject/attachment paths, and files selected inside the app are copied to the template attachments directory. Insertion must never ask for confirmation. Code, CI, startup and release are complete, but Owner interaction testing remains outstanding.


## I-013 — account drag correctness and outgoing compose controls
status: active — v0.3.19 released; Owner runtime confirmation pending

The Owner reported broken downward account drag and occasional selection of the row underneath a long-pressed account. v0.3.19 computes target from original row centers without animation feedback, resets completed transforms and confines short clicks to the original pressed account. Offline index regression checks passed.

The Owner requested a read-receipt checkbox for new outgoing messages, schedule-send checkbox with local tomorrow 09:00 as default, and display of sender email when sender name is missing. v0.3.19 implements these in UI, payload and tolerant parser, and verifies the outgoing form through a fake HTTP handler. Test of real receipt response and actual delayed delivery was not performed; do not mark them as runtime-verified.

## I-014 — v0.3.21 Owner feedback on inline composer, templates and dark controls
status: released — Owner interactive validation pending

The Owner requested: selected template/signature should work again on repeated choice; configurable folder for Markdown templates with manual file discovery; inline reply/forward close × and receipt/delayed delivery controls; darker thumb/arrows everywhere; keep modal dialog caption dark even during input; sender email when name is missing. Owner confirmed account drag and dividers are now good.

The release implements these via PR #70 and CI 37711634109 with offline file and parsing checks; installer release workflow 37711732617 succeeded. ChangeDirectory copies existing templates and attachments into the selected folder without overwriting existing files, persists the setting, rebinds watcher, and preserves old folder. Reply/forward schedule defaults to tomorrow 09:00 and sends through existing Mail.ru scheduling protocol. Receipt request is optional. Sender fallback draws from parent thread and selected full message.

Next: real-user visual and behavior check, including WPF scrollbars, prompts, list sender, repeated choices, and controlled mail receipt/delivery tests. Do not infer successful recipient acknowledgment from sending a receipt request.

## I-015 — enforce the immutable palette element-to-role contract
status: active — code released v0.3.21; user visual gate pending

The Owner requires that every theme have a complete named/presented default set of color roles and that users may edit colors but cannot reassign the fixed collection of UI elements governed by each role. The Product Manager selected 26 roles. Maintain the canonical ThemePalette.Roles registry, separate persisted dark/light dictionaries and system-theme switching. All new widgets must use a corresponding named dynamic resource instead of inventing literal element colors.

v0.3.21 implements grouped color settings, swatches/descriptions, RGB/hex input, individual and whole-theme reset and live propagation for the active theme, with nonblocking accessibility contrast alerts. The release passed automated checks; Owner in-app/visual testing remains open. Preserve account dragging and pane splitter behavior.

## I-016 — close the 12-point mail feature backlog safely
status: active — v0.3.22 delivered; send-now (#10–11) remains blocked on verified server semantics

A full v0.3.22 release has shipped the palette-collapse/new color-picker enhancements and the tested code for sender enrichment, legible Markdown, selectable attachment download directory, automatic Explorer opening, top-of-letter attachments, formatted HTML editor in all compose views, multi-select Ctrl/Shift/checkbox, bulk move/archive/read, and permanent deletion in Trash. Offline/CI testing succeeded; Owner GUI and real HTML reception confirmation pending.

The open, highest-risk remaining subtask is issue #77: safe immediate sending of a message already scheduled to send later. The application currently shows disabled "Отправить сейчас" controls when in Outbox; enabling a method that creates an additional outgoing mail instead of reusing/cancelling the existing schedule is unacceptable. Research with controlled test messages, original scheduled IDs, protocol responses and post-send state verification must precede implementation. The complete 12-point backlog remains open until this condition is met and Owner signs off.

## I-017 — verify independent auto-refresh for saved Mail.ru accounts
status: released v0.3.23, Owner runtime confirmation pending

Prevent "Авторизация активна" when the Mail.ru API rejects a saved token with HTTP 200 and embedded JSON status 403. Recognize the rejection, try account-specific refresh if available, validate fresh token against the folder endpoint, then save only successful replacement. Never delete other accounts, alter their credentials, overwrite LastLogin during inactive account checks, or log secrets. If refresh unavailable or rejected, show clearly which account requires new login. The v0.3.23 implementation and offline Windows checks are complete, but live Owner account validation remains to be done.

Issue #77 for previously scheduled outgoing mail remains separate and unresolved; do not claim all 12 backlog features complete.

## I-018 — verify GUI regression fixes and finish scheduled Outbox action
status: released v0.3.24; visual validation and issue #77 pending

v0.3.24 addresses Owner WPF palette footer clipping, dark date picker, lost selection on MailRuMessageSummary replacement, bulk folder reloading, abnormal paragraph spacing, inline/detached reply resize and permanent Trash deletion confirmation. Initial sender parser examines corresponding-message representations before separate full-message queries. Owner visual confirmation remains necessary; do not impose long sequential waits when summary metadata is absent.

Issue #77 remains open. Disabled Outbox "Отправить сейчас" cannot be enabled before the server's original scheduled-mail transition is confirmed duplicate-free. Prior account token 403 issue is closed based on Owner explicit success report.
