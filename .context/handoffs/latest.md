# Latest handoff

Latest public release: v0.3.18, published with full and update installers on 2026-10-08.

Current merged product baseline: PR #62 and PR #63, main CI passed, release workflow 37707435464 succeeded.

Authorization baseline: persistent isolated browser profile per mailbox account. Owner confirmed phone verification and successful authorization without password recovery. Preserve this architecture.

Merged interface work includes window and layout persistence, darker dark-theme scrollbars, compact sender and account icons, persistent account ordering, named account sections, animated account movement, preview and reply layout changes, signatures, and message templates.

Account movement: hold 250 to 300 ms, implementation uses 275 ms. The row detaches while the mouse remains pressed. Neighboring rows move smoothly. Mouse release fixes the new position. Escape is not used.

Account sections: expand/collapse arrow is to the right of the section name. Section state and account membership persist. Deleting a section does not delete its accounts.

Additional released changes: Mail.ru filin photo URLs with initials fallback; Markdown templates sourced from manually editable Templates/*.md files, attachment copies, legacy migration and automatic refresh. Stationary long-press no longer reorders accounts. No confirmation on template insertion.

Next: Owner checks the complete v0.3.18 interface, actual avatar availability and file-based templates in Windows runtime before unrelated feature expansion.
