# Next actions

1. Validate v0.1.3 with the owner regression account that previously returned `token_missing`; classify any remaining result as success, CAPTCHA, reCAPTCHA, 2FA, recovery, invalid credentials, or protocol mismatch.
2. If that account reaches a web/touch session, verify that mailbox listing works through the selected fallback path and record the observed response shape.
3. Locally reproduce `GET touch.mail.ru/api/v1/messages/message` (or the current equivalent) for one real message and determine whether reading changes unread state.
4. Add typed full-message/body/attachment models and wire the right-hand reading pane only after the read endpoint is promoted to A-level evidence.
5. Verify attachment download through the returned `href.download` representation.
6. Reproduce and promote read/unread, flag/star, move/archive/trash/delete operations one by one before enabling them in the UI.
7. Reproduce touch search and address-book/autocomplete behavior against current traffic.
8. Add interactive CAPTCHA/challenge handling if owner testing produces that state; do not automate third-party CAPTCHA solving.
9. Preserve Windows-protected authorization state across every update and never persist mailbox passwords.
10. Continue publishing only Setup + Update as the newest binary GitHub Release with no workflow artifacts.
