# Next actions — 2026-10-08

1. Owner installs published MailRuDesktop_Update_v0.3.26.exe and confirms the nine new changes on Windows; CI is not a substitute for real UI acceptance.
2. Verify no unformatted initial body, only final HTML/plain mail; confirm no regression in previously verified sender without a subject.
3. Drag collapsed sections after ~275 ms across other sections; ensure expanded sections are immovable, no orphan accounts, correct animations and persistence after restart.
4. Check New Mail opens in its own window; main mail remains available; rich formatting, attachments, drafts and schedule work. Search/select Mail.ru contacts in New Mail only, not in reply/forward.
5. Verify the Mail/Contacts entries are absent; Settings opens and second click returns to mailbox.
6. Verify both directory settings show Browse + plain-text path with immediate application and preserve template watcher, attachments and migrations.
7. Verify signature/template editing panels are initially collapsed and can be independently expanded.
8. Check button press states in both themes against editable AppControlPressedBrush («Фон нажатой кнопки»), including any remaining pink widgets.
9. Test switching the active account while detached compose is open; correct sender/credential binding if necessary before proclaiming multi-account compose safe.
10. Keep docs/ui-registry.json authoritative and regenerated for every UI edit: currently nine windows and 1354 elements, including dynamically created windows; CI check remains required.
11. Continue scheduled Outbox Send Now #77 only after duplicate-free server semantics are demonstrated with disposable authorized test messages; otherwise leave disabled.
12. Preserve previously Owner-accepted behavior, OAuth isolation, safe test pacing, old Windows compatibility and release/update policy.
