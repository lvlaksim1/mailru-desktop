# Next steps — v0.3.32 (2026-10-09)

1. Install the official v0.3.32 update once both installer assets are verified at https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.32.
2. Replay the exact user video scenario (Magnet ⇄ technical support ⇄ CDEK) while recording the screen without frame loss. Expected: no WPF background flash, stale letter, combined old/new text or incomplete rendering. Single continuously visible WebView2 should show its own neutral loading state until internal DOM swap.
3. Test private embedded images, including two af12 GIFs on Magnet receipt, links, scrolling and layout compared with browser. If slow images exceed three seconds they may be replaced by a transparent placeholder; record if this loses essential information. Report only sanitized diagnostic categories, never tokens/private HTML.
4. Compare loading performance with v0.3.31. The only repeated navigation of the *top-level* WebView2 must be none after initial browser-shell initialization. Its Visibility remains Visible throughout selections. WindowsUiSmoke confirms two synthetic HTML documents and no new root navigation.
5. Preserve accepted sender portraits, accounts, contacts, Save As templates and account-independent composer. Reopen only if Owner finds a reproducible regression.
6. Keep scheduled Outbox Send Now #77 disabled pending proof of atomic unscheduling/one-time delivery. Other technical debt in read receipts, delayed delivery and unverified search remains.
7. After code changes, run Windows CI, offline interaction tests, real WPF WebView2 smoke test, registry check and startup/endpoints verification before releasing.
