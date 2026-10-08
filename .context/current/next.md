# Next actions — 2026-10-08, v0.3.27

1. Owner installs the published MailRuDesktop_Update_v0.3.27.exe through GitHub Releases in a browser because v0.3.25's original API-only checker currently fails.
2. In v0.3.27, select Settings→Update→Check updates. Confirm current version or useful per-host diagnostic. Test Open releases page browser action. If manual check cannot work, request only relevant sanitized github_update_check lines from %LOCALAPPDATA%/MailRuDesktop/diagnostics.log to determine actual failed host/status without exposing mailbox/private data.
3. Verify the built-in Download/Install action with a future version when authorized; unit tests cover metadata fallback but do not prove Owner network/CDN access or a future update cycle.
4. Do not describe the application as automatically installing unattended updates: it checks after explicit click and requires a user installation action.
5. Continue Owner GUI acceptance of nine v0.3.26 changes: formatting-first letters, collapsed group reorder, detached compose and server contacts, automatic folder save, collapsed template/signature sections, pressed color role.
6. Verify sender/account safety when switching accounts with detached New Mail open. Preserve individual account drag and panel splitters.
7. Keep complete 9-window/1354-element source-generated registry synchronized with UI changes; preserve 26 fixed color roles.
8. Keep scheduled Outbox Send Now issue #77 disabled until controlled server proof of duplicate-free state transition; retain privacy and 5-second research request spacing.
