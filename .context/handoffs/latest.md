# Последняя передача проекта MailRu Desktop — 2026-10-09

manager: project-manager
repository: lvlaksim1/mailru-desktop
product authority: main
manager-state authority: main
latest binary: v0.3.29
PR #96, merge 8f0ff0c07e59b38fd6e14edd14bebd7b74018c19
CI PR 37850178802 PASS; main 37850342043 PASS
Release workflow 37850493662: full and update v0.3.29 assets available at https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.29

Owner feedback: v0.3.28 works, but only two of the much longer previous package are recognized. Owner reattached the prior 22-point explanation and asked for implementation. Treat its wording as actual acceptance criteria. Source code changes alone do not prove real-world UI function. Stop searching for photos of senders: Owner said retrieval works. The two refinements of avatar scaling and visible outgoing From address had been added in v0.3.28.

PR #96 found an actual 16x16 parent image frame clipping an avatar that had only been resized inside it; parent Border and sender column now scale. Clicking an already-selected folder while Settings is open now returns to Mail (mouse and Enter). New Mail button docks right above messages instead of left among bulk actions. New mail signature/template selectors now have visible labels. ThemeManager no longer re-applies an unchanged System theme on every application activation, avoiding needless HTML reload; reader does not expose unfinished raw HTML during a theme change and reports WebView2 failed navigation. TextPromptWindow adapts height to content and follows shared native window chrome; Color picker/contact dialogs no longer duplicate chrome hooks. Legacy shared theme color migration deterministically chooses first valid older value unless explicitly overridden by canonical color.

New tests/WindowsUiSmoke creates real WPF windows on Windows runner, opens Settings, verifies Appearance/Palette placement and Signatures immediately before Templates, returns to Mail, opens New Mail, checks From field/choosers and absence of a forced blue button background, creates a short notice and checks height, then changes the color picker surface and confirms pixels of the right brightness strip change. All passed in CI 37850178802 and 37850342043, together with offline regression tests and source-derived registry (9 windows, 1374 elements).

Outstanding OWNER validation: rapid HTML letter switching, images requiring private authorization, titlebar appearance on Owner Windows, full message/template use and 22-point list as a whole. This release has not been confirmed by Owner in the actual app. Do not send unsolicited mail. Protocol debt #77 scheduled Outbox Send Now remains blocked due duplicate risk; read receipts and exact delayed delivery are not fully verified.
