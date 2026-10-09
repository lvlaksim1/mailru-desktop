# MailRu Desktop manager handoff — v0.3.32 (2026-10-09)

Manager: project-manager; repo: lvlaksim1/mailru-desktop
Product authority/main; manager state authority/main.
Product PR #102 merged: ae6968c0352a05badd278f31697e5f182b59f4eb
Windows PR CI 37865194581 PASS; main CI 37865347046 PASS
Release issue #103, installer workflow 37865488404 for v0.3.32.
Official releases: https://github.com/lvlaksim1/mailru-desktop/releases/tag/v0.3.32.

Owner recorded repeated overlay/flash mixing new Magnet receipt graphics with older technical-support email, emphatically rejected multiple WebView2 instances and requested root-cause correction. Reviewing v0.3.31 source revealed per-selection WebView2.Visibility Hidden then Visible, WPF ReaderLoadingOverlay on top of WebView2 and CoreWebView2.Stop + NavigateToString for each mail. WPF and WebView2 paint asynchronously; those operations plausibly expose stale/blank frames. Do not claim that camera frame blending alone establishes exact source; use source and actual test. No second WebView2 is introduced.

v0.3.32 replaces MainWindow.ReaderPresentation.cs with a persistent browser shell: one WebView2, single NavigateToString at startup, no WPF overlay, no per-email Stop/navigation/Visibility change. Host-generated JSON-escaped HTML is staged in a sandboxed frame of the same browser document; the shell shows its own loading surface and atomically replaces the previous iframe when images are ready (or bounded fallback). Scripts from letters remain blocked; context includes strict generation to reject late requests. Header moves to commit time rather than changing early above former body. New ReaderShellScripts.cs supplies safe shell scripts; .xaml overlay removed; WindowsUiSmoke now initializes an actual WebView2 and proves the first/second synthetic email stage/commit and no new root navigation. UI registry now 9 windows/1371 elements and 26 WPF color resource names, no unresolved roles. PR and main CI fully passed.

**Owner-side visual test still pending.** Validate the original video sequence, real embedded images, web links and speed. Do not call the flicker eliminated based on the two synthetic letters alone. Keep v0.3.30/31 template fixes and OAuth image loading; already accepted account/contact/photo features preserved. Send Now #77 remains blocked, no duplicate-safe server transition proven; read receipts, exact scheduled delivery and alternative search remain unpaid technical debts.
