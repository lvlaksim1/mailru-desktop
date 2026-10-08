# Next actions — 2026-10-09, v0.3.28

1. Owner installs v0.3.28 update from https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.28.
2. Rapidly open HTML mail, plain-text mail and another message: verify no blank/previous message flashes and that neutral «Загрузка письма…» transitions to final content.
3. Open the Owner's Magnet receipt with previously missing af12.mail.ru GIF attachments; compare with browser and note only case/status if broken. API access through mailbox OAuth is included but not live-verified on user's credentials.
4. Increase interface text from 10 to 18; portraits should become 14–24 px without being replaced, clipped or distorting layout.
5. Open separate New Mail on account A, switch main window to B, verify «От» stays A and drafts, attachments, contact chooser and test send use only A's credentials. Never send live unsolicited mail to validate.
6. Check new-mail template/signature selectors (including repeated selection), loaded template body/subject/attachments; load an existing .md and verify original file is not changed until Save, and Save As with new name succeeds.
7. Check editor hover outline removed, send button normal fill, contact/New Mail dark captions, compact standardized AppDialog messages, palette picker brightness-strip synchronization.
8. Check Settings→folder navigates directly, New Mail button over message list, Signatures before Templates, no bottom folder-help captions and full diagnostic copy.
9. Test 20 merged color settings in both themes including legacy overrides, repaint of groups and windows; look for any still-incorrect borders/pressed states.
10. Maintain all Owner-accepted features: photo retrieval, account section drag, updater check, contact chooser, accepted mailbox actions, stable OAuth and splitters. Avoid editing these without new defect evidence.
11. Hold scheduled Outbox send-now #77 until verified duplicate-free atomic server behavior; keep explicit acceptance gates for delayed delivery/receipts.
12. When modifying UI, regenerate checked-in docs/ui-registry.json (currently 9 windows, 1371 elements) and audit its runtime Window coverage. Keep 26 technical resource names for backwards compatibility unless intentional migration is designed.
