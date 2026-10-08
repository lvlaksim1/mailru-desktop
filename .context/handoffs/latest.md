# Latest handoff

Latest public release: v0.3.19, published with full and update installers on 2026-10-08.

Current merged product baseline: PR #62 and PR #63, main CI passed, release workflow 37707435464 succeeded.

Authorization baseline: persistent isolated browser profile per mailbox account. Owner confirmed phone verification and successful authorization without password recovery. Preserve this architecture.

Merged interface work includes window and layout persistence, darker dark-theme scrollbars, compact sender and account icons, persistent account ordering, named account sections, animated account movement, preview and reply layout changes, signatures, and message templates.

Account movement: hold 250 to 300 ms, implementation uses 275 ms. The row detaches while the mouse remains pressed. Neighboring rows move smoothly. Mouse release fixes the new position. Escape is not used.

Account sections: expand/collapse arrow is to the right of the section name. Section state and account membership persist. Deleting a section does not delete its accounts.

Additional released changes: Mail.ru filin photo URLs with initials fallback; Markdown templates sourced from manually editable Templates/*.md files, attachment copies, legacy migration and automatic refresh. Stationary long-press no longer reorders accounts. No confirmation on template insertion.

Next: Owner checks the complete v0.3.18 interface, actual avatar availability and file-based templates in Windows runtime before unrelated feature expansion.


## v0.3.19 handoff

PR #67 merged to main (3640b7e56e8059777d878d9f67f4f8d6bf01c32a); main CI, offline interaction regression checks, startup and storage policy succeeded. Release workflow 37709585421 published both installers.

User issues addressed: down/up account dragging with stable centers, mouse press-to-release avoiding accidental selection of the row below; optional read-receipt request (bool receipt=true), scheduled send default tomorrow 09:00, and sender name absent -> email address fallback. Static APK source confirms receipt boolean; scheduled send still uses existing /api/v1/messages/schedule and send_date. Unchecked receipt leaves previous payload unchanged.

NEXT: Owner Windows runtime testing for actual drag interaction, sender display and scheduling/delivery; do not claim receipt notification is guaranteed. Preserve v0.3.18 Markdown file templates and real-photo fallback work.
