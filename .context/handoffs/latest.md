# Latest handoff

Latest public release: v0.3.20, published with full and update installers on 2026-10-08.

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

## v0.3.20 handoff

Owner provided two screenshots and reported issues with custom template location, reapplying selected signature/template, closing inline reply, section-name modal dark titlebar, light scrollbars, missing sender email in list, and absent inline reply/forward send receipts and schedule controls. Owner explicitly confirmed account dragging and dividers work perfectly; preserve them.

PR #70 merged as eb3415ac30f81cc3426d4726b53d590d42e57b11; CI 37711634109, storage policy 37711634121 and release workflow 37711732617 succeeded. Published release tag v0.3.20 with full and update installers.

Implemented: choose and persist template directory; copy templates/attachments without collision/overwrite; restart watcher; repeat selected signature/template on mouse/Enter; inline reply ×; dark modal native caption correction; fully custom WPF scrollbar thumbs/arrows plus both WebView reader CSS; parent-thread and full-message sender-email fallback; receipt and scheduled-send controls in inline reply/forward with tomorrow 09:00 default.

Regression tests include manual Markdown addition, directory change, attachment continuity/conflict rejection and parent-thread sender. Runtime UI and mail delivery validation still pending. No network spam; keep >=5 sec research/test request spacing. No IMAP/SMTP/app-passwords.
