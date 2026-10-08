# Latest handoff — MailRu Desktop v0.3.31 (2026-10-09)

manager: project-manager; repository: lvlaksim1/mailru-desktop
product and manager-state authority: main
product merge: PR #100, commit 277a3dc67cac7792715accf6b2edc71193742900
branch CI 37860604229 PASS; main CI 37860751548 PASS
installer release workflow: 37860887859, triggered by issue #101 for v0.3.31

Owner provided video K-001.mp4 and demonstrated v0.3.30 flicker. Receipt text is shown first, then the pictures load and force a visible table layout change. Earlier v0.3.30 intentionally revealed HTML at DOMContentLoaded, which caused the regression; previous statement that old flicker was fixed is superseded by the new video. Owner requested an immediate update including this correction.

v0.3.31 source: new MainWindow.ReaderPresentation.cs and ReaderPresentationPolicy.cs. Current WebView2 navigation is gated by revision and ID, default/loading overlay remains until images are settled. Host-originated script sets lazy HTML images eager; readiness is inspected via document.images[*].complete, in addition to NavigationCompleted. On completion, reveal once after hidden settling. On 3-second bounded deadline, stop pending WebView2 loads BEFORE publishing so late image display cannot reflow visible receipt. Safe reader_visual_ready diagnostic includes reason=complete or limited and elapsed-ms. Mail page scripts remain disabled. Unit tests cover no visibility on DOM readiness alone, full completion, timeout stopping and zero reveal before DOM. Full Windows CI passes; authenticated Owner UI test still required.

Important compromise: if images cannot complete within deadline, Stop may cause missing images. Do NOT claim both zero flicker and full image fidelity without Owner testing. v0.3.30 fixes to Markdown Save As, signed-in image loading and email latency are preserved; other Owner-accepted UI remains unchanged.

Outstanding: #77 Send Now scheduled Outbox remains disabled until atomic duplicate-safe transition proven; recipient read receipt, real scheduled delivery and alternate search remain technical debts.
