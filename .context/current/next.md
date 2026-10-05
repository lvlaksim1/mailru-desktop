# Next actions

1. Owner installs `MailRuDesktop_Update_v0.1.9.exe`.
2. Re-test `expert.sout@mail.ru`.
3. Confirm that the reCAPTCHA window is now only a minimal CAPTCHA solver and never becomes a Mail.ru inbox/login browser.
4. After solving, verify status changes to “Проверка принята — завершаю авторизацию и получаю токены...” and mailbox data loads after the required request spacing.
5. If Mail.ru returns reCAPTCHA again, capture the visible diagnostic; v0.1.9 reports this explicitly as `recaptcha_rejected` instead of silently reopening challenge windows.
6. Verify optional desktop shortcut checkbox in the installer.
7. Verify Light, Dark and System themes across all tabs, filters, grids, dropdowns, date pickers, dialogs, challenge window, title bars and message reader.
8. Continue validation of full-message body, incoming attachments/download and Trash/delete behavior.
