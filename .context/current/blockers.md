# Current blockers and open risks

1. **v0.3.18 is merged but not published.** PR #62 and main checks are green, but the latest public GitHub Release remains v0.3.17. Packaging/publishing v0.3.18 is the immediate outstanding delivery step.
2. **v0.3.18 Owner runtime validation.** Automated checks prove build/startup/policy integrity, not interaction quality. Window-state persistence, divider persistence, seven column widths, dark scrollbars, icons, account drag animation/order persistence, account sections, preview/reply placement, signatures and templates still need Owner validation in the real application.
3. **Account dragging quality.** The required interaction is precise: hold the account row for 250–300 ms (implemented at 275 ms), keep the button pressed, row detaches, move through other accounts with smooth physically plausible displacement animation, release to drop. Escape must not cancel. Any deviation is a product defect.
4. **Authorization recovery routing.** v0.3.17 solved the observed problematic account through persistent per-account authorization state and Owner confirmed phone verification succeeds. Server-side Mail.ru may still legitimately route some accounts to recovery; the application must not attempt to bypass a server-required recovery decision.
5. **Parser diversity.** Mail.ru exposes multiple smart-thread response shapes. Future parser changes must preserve all known forms.
6. **New search.** go.mail.ru/api/v1/go/search/emails remains statically in scope but controlled live probes returned HTTP 520; it remains disabled by default.
7. **Delayed send.** Exact live send_date semantics remain unvalidated.
8. **Compose message id.** Modern constraints for the shared upload/send/draft message id remain unresolved.
9. **Broader API surface.** Statically mapped OAuth-compatible commands still need live validation before normal UI enablement.
