# Current blockers and open risks

1. **Avatar runtime verification.** v0.3.20 is published, and the Mail.ru filin /pic?email URL comes from official APK strings. Whether it returns actual portraits for real sender/account emails is not yet established by controlled live or Owner runtime tests. Fallback initials must remain visible when no photo exists.
2. **v0.3.19 Owner runtime validation.** Automated checks prove build/startup/policy integrity, not interaction quality. Window-state persistence, divider persistence, seven column widths, dark scrollbars, account drag animation/order persistence, account sections, preview/reply placement, signatures and Markdown templates still need Owner validation.
3. **Account dragging quality.** The required interaction is precise: hold the account row for 250–300 ms (implemented at 275 ms), keep the button pressed, row detaches, move through other accounts with smooth physically plausible displacement animation, release to drop. Escape must not cancel. Any deviation is a product defect.
4. **Authorization recovery routing.** v0.3.17 solved the observed problematic account through persistent per-account authorization state and Owner confirmed phone verification succeeds. Server-side Mail.ru may still legitimately route some accounts to recovery; the application must not attempt to bypass a server-required recovery decision.
5. **Parser diversity.** Mail.ru exposes multiple smart-thread response shapes. Future parser changes must preserve all known forms.
6. **New search.** go.mail.ru/api/v1/go/search/emails remains statically in scope but controlled live probes returned HTTP 520; it remains disabled by default.
7. **Delayed send.** Exact live send_date semantics remain unvalidated.
8. **Compose message id.** Modern constraints for the shared upload/send/draft message id remain unresolved.
9. **Broader API surface.** Statically mapped OAuth-compatible commands still need live validation before normal UI enablement.

10. **Manually managed Markdown templates.** Verify adding/removing/editing top-level .md files while running, file rename, folder watcher, old settings.json one-time migration, attachment file copying, invalid-path handling and reply text protection during refresh. No interactive test has yet confirmed all cases.

11. **Read receipt, delivery and schedule runtime gate.** The boolean receipt field was confirmed in APK source and serialized; tests exercised offline HTTP imitations only. A request does not compel receipt from the recipient. Existing send_date scheduling remains without a fresh live delivery test, so verify actual Mail.ru server behavior independently.
12. **Bidirectional drag runtime gate.** Up/down geometry regression tests pass, but visual hit testing and mouse capture on the Owner's Windows machine require manual verification, especially after long hold and at section boundaries.

13. **v0.3.20 Owner UI runtime gate.** CI and startup checks passed but real visual behavior of dark scrollbar templates, modal caption, repeated ComboBox selection and selected-row sender repair requires Owner validation. Do not label them runtime-verified.
14. **Custom template directory runtime gate.** Offline file-level tests passed for copy, manual discovery, attachments and conflicts; actual WPF folder picker, setting persistence through restart and directory watcher rebinding still need interactive verification.
15. **Reply scheduling/receipt runtime gate.** Inline reply/forward options route to existing receipt/send_date logic; server acceptance of an actual read receipt and scheduled delivery still requires separate controlled live tests. A receipt request cannot guarantee recipient acknowledgment.
