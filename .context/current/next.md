# Next actions

1. Validate the published v0.3.19 update on the Owner's Windows machine; GitHub Release includes both installers.
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
