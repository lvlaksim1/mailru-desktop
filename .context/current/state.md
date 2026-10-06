# Current state

Repository: `lvlaksim1/mailru-desktop`
Visibility: public
Product authority: `main`
Manager-state authority: `main`

Current public release: `v0.1.10`

Verified release evidence:

- product CI run `37393121013` — success
- release workflow `37393220561` — success
- release assets: `MailRuDesktop_Setup_v0.1.10.exe` and `MailRuDesktop_Update_v0.1.10.exe`
- GitHub Actions artifacts: none
- older binary Releases pruned; source tags retained

v0.1.10 auth/mailbox correction:

- owner confirmed v0.1.9 reCAPTCHA completes and Hackus search/touch token is issued;
- diagnostic search response proved touch token acquisition but q_query=* returned no messages/folders;
- root cause: v0.1.9 had removed pre-challenge mobile auth correctly, but therefore no mobile access_token existed for the proven aj-https folder/thread APIs;
- after successful Hackus Login, MailRu Desktop now performs the VBA mobile-token request in the already verified cookie session;
- this post-verification request uses /cgi-bin/auth?mp=android&udid=mailru_app and the VBA oauth2/mobile/mob_json/simple form, but only after challenge completion;
- access_token and refresh_token are propagated into the saved authorization;
- folder loading again prefers the proven mobile access_token path;
- touch/search token remains for search/contacts but is no longer misused as a folder-list fallback;
- if only touch token exists, the UI reports a missing folder transport instead of showing a false empty mailbox.

v0.1.10 theme correction:

- checkbox and radio indicators use themed custom templates;
- TabItem uses a themed header template, removing white selected tabs in dark mode;
- ComboBox uses a themed template, removing the white dark-mode combo surface;
- HTML mail preview adds dark-mode overrides for inline/container backgrounds so HTML-heavy Inbox messages follow dark theme rather than retaining white mail backgrounds.

Delayed sending:

- Compose now offers “Отложить отправку”, date and local time controls;
- the existing mobile send implementation now passes SendDate and switches from /api/v1/messages/send to /api/v1/messages/schedule;
- payload remains aligned with the owner VBA pattern: same attaches/body/correspondents/id/source/subject/send_date/priority fields;
- runtime validation of the selected schedule timestamp is pending owner test.

The v0.1.9 Hackus-equivalent challenge state machine remains unchanged.
