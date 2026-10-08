# Latest handoff — MailRu Desktop v0.3.26

Persistent manager_id: project-manager. Manager-state authority main; product authority main. Owner direct interaction remains first-class.

Owner on 2026-10-08 confirmed blank-subject sender issue resolved and all other v0.3.25 improvements working, then approved nine interface refinements.

Product PR #89 merged as a1ed3b9bbba67aa29bb38b88f499e038f3709f53. CI on PR 37797350430 and main 37797743640 passed. Release workflow 37797787312 published v0.3.26 full/update installers at https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.26.

Implemented: no temporary unformatted snippet before final mail page; collapsed-only 275ms drag of account section plus its members; Mail and Contacts nav removed; independent owned New Mail window reusing original compose controls; server-contact recipient picker only in New Mail; compact immediately applied attachment/template directory chooser with text display; initially collapsed signature/template settings; explicit existing 26-role palette entry «Фон нажатой кнопки». No new 27th role.

Follow-up registry-only PR #91 merged as b084203c916d70b3ecfb5c3eec1d28481dea43f3 after source-derived CI 37798726121 passed; catalog now lists 9 windows, 1354 elements, 51 ComposeWorkspace entries with runtime host, 26 fixed palette roles. This code change is documentation/generator only; v0.3.26 installer remains valid.

**Owner runtime acceptance of the nine new changes is pending.** Check especially a detached compose window open during active-account switching. Existing per-account authorization profile, account-row drag, splitter geometry, rich HTML and checked-first actions must not regress.

**Blocked issue #77:** Send Now of an existing scheduled Outbox item disabled until duplicate-free atomic transition is proven; static APK data are insufficient. True scheduled delivery and read receipt outcome need separate live evidence.
