# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.7.exe`.
2. Re-test `expert.sout@mail.ru`.
3. Confirm that CAPTCHA/reCAPTCHA is completed in the embedded Mail.ru window and that the application immediately loads mailbox data after the challenge closes.
4. Confirm the account remains authorized after restarting MailRu Desktop.
5. If challenge completion still fails, inspect the saved sanitized auth diagnostic to determine whether the same-session continuation failed at reCAPTCHA POST, inbox validation, web-token extraction, or touch-token extraction.
6. Then continue validation of full-message body, attachments/download, filtering, Trash/delete and theme switching.
