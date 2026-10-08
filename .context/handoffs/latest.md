# Latest handoff

Latest public release: v0.3.17.

Current merged product baseline: v0.3.18 candidate from PR 62. Main checks passed. Release packaging is pending.

Authorization baseline: persistent isolated browser profile per mailbox account. Owner confirmed phone verification and successful authorization without password recovery. Preserve this architecture.

Merged interface work includes window and layout persistence, darker dark-theme scrollbars, compact sender and account icons, persistent account ordering, named account sections, animated account movement, preview and reply layout changes, signatures, and message templates.

Account movement: hold 250 to 300 ms, implementation uses 275 ms. The row detaches while the mouse remains pressed. Neighboring rows move smoothly. Mouse release fixes the new position. Escape is not used.

Account sections: expand/collapse arrow is to the right of the section name. Section state and account membership persist. Deleting a section does not delete its accounts.

Next: publish v0.3.18, then validate the new interface in Owner runtime before unrelated feature expansion.
