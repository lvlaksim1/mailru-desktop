# Latest MailRu Desktop Project Manager handoff — 2026-10-09

identity: project-manager
repository: lvlaksim1/mailru-desktop
product authority: main
manager-state authority: main
latest binary: v0.3.28
product PR: #94, merge 1d07dc2d17d4ffd2fc0cfa238e09d67032ece4f2
verified GitHub Actions: PR CI 37847574160 PASS; main CI 37847753416 PASS; installer release 37847893518 PASS.
download: https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.28

Owner confirmed several v0.3.27 features: section dragging works perfectly; update version checking fixed; Contacts nav removed; contact picker and collapsed Signature/Template settings good; sender/account portrait retrieval now reliable — stop photo retrieval research. Owner requested portraits scale proportionately with interface font. Also required visible outgoing sender email in detached New Mail to avoid cross-account sender changes.

Following annotated screen review Owner identified WebView2 body flash (blank, old letter, selected), missing inline af12.mail.ru images, color picker brightness strip failing to respond to hue/saturation, Settings folder navigation, New Mail placement, unwanted blue Send background, white captions in New Mail and chooser, template/signature selectors in detached compose, editor hover/focus outline, excess blank height of notice dialogs, excessive folder captions, order Signatures→Templates, import pre-existing Markdown for editing/copy, color section inside Appearance, copy-all diagnostics and unifying near-duplicate color settings.

PR #94 included all those source-level changes. New font-aware AppAvatarSize=14..24 does NOT change image retrieval. Detached composer snapshots account login and access token, shows read-only From, uses snapshots for drafts/attachments/sends/contacts. Mail reader cancels stale preparation and delays showing WebView2 until new navigation is complete, with neutral loading overlay. Inline afNN attachment references are fetched through existing access-token API and inserted only if bytes match known image signatures. Source import .md leaves original untouched until explicit Save. Caption hooks centralized via ThemeManager.AttachWindowChrome; compact dialogs and no editor hover border; 20 editable palette entries backed by 26 preserved WPF resource keys, migration for older values. XAML UI inventory now 9 windows / 1371 elements.

**Important truth:** CI/build/startup passed, but Owner has NOT yet visually verified the v0.3.28 changes on the actual machine, particularly image loading, WebView timing, titlebar color and account-switch composition. Do not assert 100% successful behavior before Owner confirmation. Do not perform unsolicited outbound email to test.

**Still blocked:** Send Now for already scheduled Outbox letter, issue #77: no proof of duplicate-safe server cancellation/reuse, so controls disabled. Recipient acknowledgment/delivery and go.mail.ru search remain separate investigations. Preserve accepted sender portrait retrieval, group dragging, updater and authorization flows.
