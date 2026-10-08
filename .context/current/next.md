# Next actions — v0.3.31, 2026-10-09

1. Install published MailRuDesktop_Update_v0.3.31.exe after verifying official full/update release assets. Keep previous installation and user data.
2. Repeat K-001.mp4 scenario: open same Magnet HTML receipt, another message, then original again. Expect neutral loading state and one stable fully laid-out result, not text followed by images/table reflow.
3. Assess image completeness. When images exceed 3-second wait, Stop prevents late flash but may leave image missing; if essential pictures absent, report the message and only sanitized reader_visual_ready reason/elapsed-ms plus mail_image_inline outcomes, no credentials or full message JSON.
4. Compare performance with browser and v0.3.30. If the new delay feels excessive, investigate caching and selective image readiness rather than returning to DOMContentLoaded early visibility.
5. Preserve successful v0.3.30 loading/Save As behavior, folder selection, contacts, sender address, avatar scaling, palette. Reopen only on new reproducible defects.
6. Continue Issue #77 only with proof of atomic existing-schedule cancellation / one-time delivery using controlled authorized test mail. Otherwise leave control disabled. Read receipts, delayed delivery and alternative search remain technical debts.
7. Maintain CI checks including source-derived UI registry, actual WPF smoke, offline logic, startup and Mail.ru endpoint policy.
