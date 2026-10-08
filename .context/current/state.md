# Current state

Repository: lvlaksim1/mailru-desktop
Visibility: public
Product authority: main
Manager-state authority: main

Latest public release: **v0.3.19**, published 2026-10-08 with full and update installers.
Product changes: PR #62 plus PR #63, merge commit 34e583abc232c9edc939816a92b4e08165836210.
Release workflow 37707435464, main CI 37707321540 and repository storage check 37707321584: success.

## Authorization state

v0.3.17 introduced a persistent browser profile isolated per mailbox account for Mail.ru interactive authorization. Owner runtime on 2026-10-08 confirmed the important problem case: instead of forcing password recovery, Mail.ru requested phone confirmation inside the isolated window and authorization completed successfully without changing the password.

This is now the stable authorization architecture:
- account profiles persist across application restarts;
- profiles do not share cookies with normal desktop browsers;
- profiles are isolated from other MailRu Desktop accounts;
- no CAPTCHA solver or verification bypass;
- do not revert to a disposable profile per verification.

## Mail list and multi-account baseline

The parser remains tolerant across base_message, messages[], direct thread-like objects and threads[].representations[].

Current compact message rows use the established seven-column order:
1. time;
2. flag;
3. fixed-size read/unread dot;
4. thread message count;
5. attachment indicator;
6. sender name with email fallback;
7. subject plus first text line.

Messages remain grouped by date, with pinned messages in their separate section when present. No first message is automatically selected/opened merely because the list loaded.

## v0.3.18 merged interface bundle

PR #62 is merged to main. Main CI run 37703044365 and repository-storage-policy run 37703044638 passed.

Implemented:
- corrected saving/restoring of normal versus maximized window state;
- layout state is saved before window destruction;
- message-list/preview divider persistence no longer gets overwritten by the older compatibility layer;
- all seven user-adjusted message-column widths remain part of saved settings;
- dark-theme scrollbar arrows/thumb use a darker, lower-contrast palette;
- compact 16 px local sender/account icons are placed left of sender/account text without increasing row height;
- persistent account ordering;
- account sections/headings, with the expand/collapse arrow on the right of the section name;
- section rename/delete; deleting a section does not delete its accounts;
- account section expanded/collapsed state persists;
- account movement uses a 275 ms hold before detaching; the mouse stays pressed during the drag; the detached row follows the pointer; neighboring rows animate out of the way; release fixes the position; Escape is not part of the interaction;
- preview controls were rearranged per Owner request;
- reply/forward editor appears above the message body;
- Settings contains Signatures and Templates;
- signatures are named, editable and removable;
- message templates are named and contain subject, body and local attachments;
- reply editor contains Signature and From template selectors;
- selecting a signature appends/replaces the chosen signature at the end of text;
- selecting a template populates subject, body and available attachments.

## v0.3.18 release additions from PR #63

- Genuine sender/account images are requested from Mail.ru filin /pic?email (source: official APK URL strings), with initials as fallback when the photo fails to load. Photo availability in real accounts is not yet verified by Owner runtime.
- Each template is now a separate filename-equals-name Markdown file under %LOCALAPPDATA%/MailRuDesktop/Templates. Manually created files appear automatically through directory watching, and the list also refreshes when Settings or the reply template selector opens.
- Plain Markdown is valid; optional front matter stores subject and attachment references. Attachments selected in Settings are copied to Templates/_attachments. Legacy settings.json templates are migrated once without deleting the old backup.
- Insertion requires no confirmation. Background file refresh does not reinsert the template into an in-progress reply.
- Releasing a long-pressed account without moving it no longer changes account order.

## Release state

v0.3.18 is published. CI and release packaging passed. Owner runtime/visual verification of the listed interactions remains outstanding.

## Stable project policies

- Scope is based on the mailbox OAuth credential, not a fixed Mail.ru host list.
- No IMAP/SMTP or app passwords.
- Update releases use MailRuDesktop_Update_vX.Y.Z.exe; full installer remains for first install.
- Repeated research/probe/test network requests keep at least five seconds spacing.
- Normal application runtime has no mandatory five-second delay.
- Do not collapse known Mail.ru response variants into one assumed schema.
- Keep folder last_modified behavior unchanged unless explicitly authorized.

## v0.3.19 release — account drag and outgoing mail

PR #67 merged as product commit 3640b7e56e8059777d878d9f67f4f8d6bf01c32a. Main CI 37709503446, storage policy 37709503443 and release workflow 37709585421 completed successfully. Installer version is 0.3.19.

- Account dragging now calculates destination from unanimated row centers. Downward/upward destination computation is deterministic; neighbor transforms do not feed back into hit geometry. Short click is tied to the original pressed row; release after pointer drift does not switch to an unintended account. Animated transforms are cleared when committing the drag.
- New-mail compose has a read receipt request checkbox, backed by the official client's boolean POST parameter receipt. With checkbox enabled, receipt=true is sent; unchecked leaves the previous wire payload unchanged.
- The existing scheduled send checkbox now has tomorrow (local current date + one day) at 09:00 as its default, date/time controls gated by the checkbox, refreshed on new compose entry and reset after success. The existing /messages/schedule endpoint is retained.
- Sender display shows the email address when the name is absent/whitespace. Thread response parser now tolerates array, object and string variants for correspondents.from/direct from.
- Offline deterministic regression checks were added to CI for bidirectional target calculation, sender fallback and outgoing receipt/scheduling parameter serialization. They send no real mail.
- Release/build/test evidence is NOT live proof that Mail.ru honors read receipt requests or scheduled delivery, nor a substitute for Owner's hands-on dragging validation.
