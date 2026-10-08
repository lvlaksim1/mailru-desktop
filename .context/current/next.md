# Next actions

1. Validate the published v0.3.22 update on the Owner's Windows machine; GitHub Release includes both installers.
2. Validate saved window state, size, position, preview divider and message-column widths after restart.
3. Validate darker scrollbars and real sender/account avatar photos, with initials fallback when no photo exists, without increasing row height.
4. Validate account reordering exactly as specified by Owner, including hold-to-detach timing, smooth neighbor movement, release-to-place and persisted order.
5. Validate account sections, their right-side arrow, saved state and safe section deletion.
6. Validate preview/reply control positions and reply editor placement.
7. Validate named signatures and Markdown file templates: manually add/modify/rename/delete .md files, inspect list refresh, subject/body, saved copies of attachments, legacy migration, and insertion without confirmation.
8. Preserve the v0.3.17 persistent isolated authorization-profile architecture unless new runtime evidence identifies a defect.
9. After v0.3.19 validation, resume staged protocol expansion and delayed-send/search work.

10. Owner tests account dragging from first to last and last to first, including crossing section headings; ensure a long hold without movement never selects an adjacent account or reorders the row.
11. Owner tests read-receipt request on new outgoing mail against a consenting test recipient. Request is protocol-confirmed, but end-to-end receipt behavior is not independently verified.
12. Owner checks new-compose default tomorrow 09:00, scheduling checkbox gating, future-time validation and delivery outcome of scheduled messages. Do not claim delivery on successful scheduling alone.
13. Owner confirms missing sender names are replaced with sender email in mailbox list across accounts.

14. Owner visually checks horizontal/vertical thumb and arrow darkness across all WPF scroll containers and both embedded mail readers.
15. Owner checks same-value template/signature re-selection (mouse and keyboard) in inline reply, and × closes it.
16. Owner selects a custom template directory, restarts, adds a .md file manually, edits/renames files and confirms watcher, attachment continuity and backups.
17. Owner validates titlebar color on account-section title prompt while text field is focused.
18. Owner verifies email fallback on mailbox rows that initially lack names, before/after selecting full mail.
19. Owner verifies receipt and scheduled send controls in inline reply/forward, not only New Mail. Validate server acceptance and delivery separately.

20. Owner installs v0.3.22 and visually inspects all 26 palette roles in dark and light settings, including dialog windows, lists, buttons, focus state, date picker and both scrollbar orientations.
21. Owner edits a role, confirms immediate update across affected elements, restart persistence, per-role and entire theme reset, independent dark/light profiles, and Windows-driven system theme.
22. Check contrast warnings: low contrast must be reported, but never silently corrected. Review original appearance of images, logos and contact avatars.
23. Resume separately agreed 12-point mail backlog after the palette system has been visually validated; do not conflate its unimplemented items with the completed color-system release.

24. Owner installs v0.3.22 and validates collapsible palette and direct click-to-choose color field in both themes.
25. Verify senders populate without opening a message, including blank subjects; auto enrichment is capped at 20 and has 5-second intervals without changing read marks.
26. Open legacy and new Markdown templates in Windows Notepad: Cyrillic metadata readable, body and attachments preserved, old escaped file backed up once.
27. Verify configured download directory, Explorer opening after successful downloads and attachments appearing above the message.
28. Send a controlled rich-formatted test message from new-mail, inline reply and detached reply, checking actual HTML receipt and legacy template/signature compatibility.
29. Test checkbox/Ctrl/Shift/Ctrl+A selection and grouped archive, trash, read and permanent trash deletion, including confirmation and prevention of duplicate operations.
30. **Issue #77 remains blocked**: investigate and validate the exact duplicate-free server operation to immediately send already scheduled mail. Only then enable Outbox controls in bulk bar, message view and context menu.
