# Latest handoff

Published MailRu Desktop `v0.1.12`.

Most important correction: after another direct comparison with Hackus, the prior mobile-access-token assumption was removed. Hackus uses the cookie session + touch search token after Login(); its normal Search() request calls gosearch with q_folder=all and no q_query wildcard. MailRu Desktop now does the same and reconstructs standard folders locally from message.folder.

The resulting path is:

Hackus Reset/CreateSession -> CAPTCHA/reCAPTCHA if required -> GetSearchToken -> touch gosearch(q_folder=all, no q_query=*) -> parse messages/folder ids -> mailbox UI.

Full-message and message actions also prefer the Hackus touch endpoints.

Owner-requested UI work included in v0.1.12:
- softer dark-gray Windows caption;
- fully themed calendar popup;
- fixed folder ComboBox display;
- removed empty message-grid flag column;
- dedicated full-message window on double click;
- sender/recipient/date/time/subject plus compact attachments and full body;
- Reply / Forward / Archive / Move to folder actions;
- Reply form appears inside the same message window;
- touch-cookie attachment downloads;
- external remote images blocked in the mail reader.

Release:
- Update: `MailRuDesktop_Update_v0.1.12.exe`
- Setup: `MailRuDesktop_Setup_v0.1.12.exe`
- release workflow `37398787496`: success
- Actions artifacts: none

Acceptance focus: runtime mailbox loading through Hackus touch search and message-window actions.
