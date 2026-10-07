# Current state

Repository: lvlaksim1/mailru-desktop
Visibility: public
Product authority: main
Manager-state authority: main

Latest public release: **v0.3.17**
Current merged product baseline on main: **intended v0.3.18**, PR #62, merge commit 3593e4689c813b4c587518cba4c424742de2f621.
v0.3.18 has not yet been published as a GitHub Release.

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

## Release state

Latest published installers are still v0.3.17. The immediate release task is to publish v0.3.18 from the already merged product code, then perform Owner runtime/visual validation.

## Stable project policies

- Scope is based on the mailbox OAuth credential, not a fixed Mail.ru host list.
- No IMAP/SMTP or app passwords.
- Update releases use MailRuDesktop_Update_vX.Y.Z.exe; full installer remains for first install.
- Repeated research/probe/test network requests keep at least five seconds spacing.
- Normal application runtime has no mandatory five-second delay.
- Do not collapse known Mail.ru response variants into one assumed schema.
- Keep folder last_modified behavior unchanged unless explicitly authorized.
