# Open blockers and Owner acceptance — 2026-10-09, v0.3.31

1. **Video-confirmed reader blink: v0.3.31 acceptance pending.** Owner K-001.mp4 from v0.3.30 demonstrates first text then images and receipt table reflow. New staging waits for document.images and completed navigation or caps at 3 seconds with Stop before reveal. Windows CI passed, but Owner private WebView2 reproduction NOT retested. Never mark fixed on CI alone.
2. **Tradeoff of a bounded deadline.** Late images might remain missing when a resource is stopped after deadline. Need to verify the Magnet receipt both looks stable and still includes important images; address any fallback fidelity regression without showing partial content.
3. **Authenticated image loading.** v0.3.30 uses MailRuInlineImageSource and OAuth to retrieve afNN.mail.ru attachments; live user session still determines actual coverage of embedded GIFs.
4. **Performance.** v0.3.31 adds up to 3 seconds image-settle wait after body navigation to avoid reflow. Measure perceived latency and reader_visual_ready reason=complete/limited in sanitized diagnostics, without private email content.
5. **Scheduled Outbox Send Now issue #77 remains disabled.** No proven atomic unscheduling and duplicate-free immediate delivery. See docs/protocol/scheduled-send-now-safety.md.
6. **Other protocol technical debts:** exact delayed delivery, recipient read receipts and unverified search endpoints. No live send without Owner-authorized disposable recipient.
7. **Preserve accepted product:** avatar retrieval, account drag, contact chooser, independent sender in composer, rich templates, explicit template Save As, updater and color themes. Do not re-research working avatar retrieval.
