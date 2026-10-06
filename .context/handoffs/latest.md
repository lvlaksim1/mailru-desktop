# Latest handoff

Published MailRu Desktop `v0.1.10`.

Owner runtime evidence from v0.1.9 established that reCAPTCHA now succeeds. The diagnostic response contained a valid touch/search token but an empty gosearch(q_query=*) result, explaining why auth showed active while folders were blank.

v0.1.10 fixes the credential split:
- Hackus Login/challenge still runs first;
- only after successful verification, the client requests the proven VBA mobile access_token in the verified cookie session;
- that token is carried back through challenge completion, saved, and used by aj-https folder/thread APIs;
- touch/search token is retained for search/contacts only and is no longer treated as a folder listing transport.

Dark theme:
- custom themed CheckBox/RadioButton templates;
- custom themed TabItem and ComboBox templates;
- HTML Inbox preview forces dark container/background treatment in dark mode.

Delayed sending:
- Compose has optional scheduled date/time;
- endpoint switches send -> schedule and forwards send_date using the same payload structure as the owner VBA implementation.

Release:
- Update: `MailRuDesktop_Update_v0.1.10.exe`
- Setup: `MailRuDesktop_Setup_v0.1.10.exe`
- CI `37393121013`: success
- release workflow `37393220561`: success
- Actions artifacts: none
- only binary Release retained: v0.1.10

Acceptance focus: expert.sout folders after fresh login, dark-theme surfaces, and one scheduled-message runtime test.
