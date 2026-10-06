# Current blockers and open risks

1. **Obsolete host guard:** v0.1.15 and CI still embody the old host allow-list. Before adding valid same-token endpoints on other hosts, protocol policy/guard must be refactored from host-only validation to explicit endpoint/evidence/credential-aware validation.
2. **Static vs live evidence:** the APK establishes 100 same-token commands, but many newly discovered operations have not yet been exercised by our client against the live service. Product enablement requires controlled validation.
3. **Authorization challenges:** CAPTCHA/reCAPTCHA variants are not exhaustively characterized. Policy is fixed: any interactive verification stops authorization; no bypass.
4. **Delayed send:** exact live `send_date` semantics still need validation.
5. **Compose message id:** modern constraints for the compose-session/message id shared by upload and send remain to be determined before replacing the legacy 32-character sentinel.
6. **Owner runtime regression check:** v0.1.15's released full-message, attachment, marks and move/archive/trash behavior should still be checked in the Owner environment before broad protocol expansion.
