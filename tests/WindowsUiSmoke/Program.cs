using System.IO;
using System.Diagnostics;
using System.Reflection;
using System.Windows.Threading;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using MailRuDesktop.App;

internal static class Program
{
    [STAThread]
    private static int Main()
    {
        Window? mail = null;
        Window? editor = null;
        Window? notice = null;
        Window? colorPicker = null;
        try
        {
            var app = new App();
            app.InitializeComponent();
            // Markdown signatures are discovered from files, not an independent JSON list.
            // Old values migrate without deleting the settings.json backup.
            var signaturesTemp = Path.Combine(Path.GetTempPath(),
                "MailRuSignatures-" + Guid.NewGuid().ToString("N"));
            try
            {
                var store = new MarkdownSignatureStore(signaturesTemp);
                store.ImportLegacyOnce(new[]
                {
                    new SavedSignature { Name = "Рабочая", Body = "**С уважением**" }
                });
                Expect(store.LoadAll().Count, 1, "legacy signature migrates to one .md file");
                Expect(store.LoadAll()[0].Body, "**С уважением**",
                    "Markdown signature body is preserved byte-for-byte");
                File.WriteAllText(Path.Combine(signaturesTemp, "Личная.md"), "С уважением,\nПользователь");
                Expect(store.LoadAll().Count, 2,
                    "manually added .md signature is visible without application UI");
                store.ImportLegacyOnce(new[]
                {
                    new SavedSignature { Name = "Рабочая", Body = "OLD AGAIN" }
                });
                Expect(store.LoadAll().Single(x => x.Name == "Рабочая").Body,
                    "**С уважением**", "migration does not overwrite managed signature files");
                store.Save(new SavedSignature { Name = "Личная", Body = "Новый вариант" });
                Expect(File.ReadAllText(Path.Combine(signaturesTemp, "Личная.md")),
                    "Новый вариант", "saving a signature updates its standalone file");
                store.Delete("Личная");
                Expect(store.LoadAll().Count, 1, "deleting a signature removes its .md file");
                try
                {
                    store.Save(new SavedSignature { Name = "../bad", Body = "x" });
                    throw new Exception("Traversal should have failed");
                }
                catch (ArgumentException) { }
            }
            finally
            {
                if (Directory.Exists(signaturesTemp))
                    Directory.Delete(signaturesTemp, recursive: true);
            }
            
            Expect(SignaturePlacement.Replace("Текст\n\nС уважением", "С уважением",
                "Новая подпись"), "Текст" + Environment.NewLine + Environment.NewLine +
                "Новая подпись", "replacement avoids duplicate signature at end");
            Expect(SignaturePlacement.Replace("Текст\n\nС уважением", null,
                "Новая подпись", new[] { "С уважением" }),
                "Текст" + Environment.NewLine + Environment.NewLine + "Новая подпись",
                "existing known signature from a template is replaced");
            Expect(SignaturePlacement.Replace("Цитата: С уважением\nДалее текст", null,
                "Подпись", new[] { "С уважением" }),
                "Цитата: С уважением\nДалее текст" + Environment.NewLine +
                Environment.NewLine + "Подпись", "quoted signatures inside body are preserved");
            Expect(SignaturePlacement.Replace("Письмо\n\nПодпись", "Подпись",
                "Подпись"), "Письмо" + Environment.NewLine +
                Environment.NewLine + "Подпись", "reselecting signature does not duplicate it");
            Expect(SignaturePlacement.Replace("Письмо\nПодпись", "Подпись",
                "Новая"), "Письмо" + Environment.NewLine +
                Environment.NewLine + "Новая",
                "existing signature with a single separator line is replaced");

            
            var geometryTemp = Path.Combine(Path.GetTempPath(),
                "MailRuWindowBounds-" + Guid.NewGuid().ToString("N"));
            try
            {
                var state = new AppSettingsStore(geometryTemp);
                state.SaveWindowGeometry("compose", new SavedWindowGeometry
                { Left = 100, Top = 110, Width = 880, Height = 720 });
                state.SaveWindowGeometry("reply", new SavedWindowGeometry
                { Left = 220, Top = 230, Width = 940, Height = 760 });
                var reopened = new AppSettingsStore(geometryTemp);
                Expect(reopened.LoadWindowGeometry("compose")!.Left, 100.0,
                    "new-mail location survives restart");
                Expect(reopened.LoadWindowGeometry("reply")!.Width, 940.0,
                    "reply window size is independent from new-mail size");
            }
            finally
            {
                if (Directory.Exists(geometryTemp))
                    Directory.Delete(geometryTemp, recursive: true);
            }
            
            
            // The native spelling option belongs to application settings, not a cloud service.
            var spellSettingsRoot = Path.Combine(Path.GetTempPath(),
                "MailRuSpellCheck-" + Guid.NewGuid().ToString("N"));
            try
            {
                var spellSettings = new AppSettingsStore(spellSettingsRoot);
                Check(spellSettings.LoadSpellCheckEnabled(),
                    "spelling is enabled by default for an existing installation");
                spellSettings.SaveSpellCheckEnabled(false);
                Check(!new AppSettingsStore(spellSettingsRoot).LoadSpellCheckEnabled(),
                    "spelling toggle survives restart");
                spellSettings.SaveSpellCheckEnabled(true);
                Check(new AppSettingsStore(spellSettingsRoot).LoadSpellCheckEnabled(),
                    "spelling can be re-enabled after restart");
            }
            finally
            {
                if (Directory.Exists(spellSettingsRoot))
                    Directory.Delete(spellSettingsRoot, recursive: true);
            }

            // The test owns the STA thread without Application.Run().
            // Preserve WPF's dispatcher synchronization context across await.
            SynchronizationContext.SetSynchronizationContext(
                new DispatcherSynchronizationContext(Dispatcher.CurrentDispatcher));
            // Formatting survives signature changes; a selection must replace
            // rather than append the same signature twice.
            var previewTestGrid = new Grid();
            previewTestGrid.RowDefinitions.Add(new RowDefinition());
            var previewTestPlain = new TextBox();
            previewTestGrid.Children.Add(previewTestPlain);
            var previewTestWindow = new Window { Content = previewTestGrid };
            var previewTestRich = RichComposeEditor.Attach(previewTestPlain, previewTestWindow);
            var checkEditor = previewTestRich.TextControl;
            Check(checkEditor is not null, "rich editor surface exists for spell checking");
            Check(checkEditor!.Language.IetfLanguageTag == "ru-RU",
                "Russian compose content uses ru-RU rather than the en-US fallback");
            Check(AvatarImageBehavior.IsSupportedAvatarUrl(
                      "https://filin.mail.ru/pic?email=recipient%40example.org"),
                "existing public Filin image route remains supported");
            Check(!AvatarImageBehavior.IsSupportedAvatarUrl(
                      "https://outside.example.org/pic?email=user") &&
                  !AvatarImageBehavior.IsSupportedAvatarUrl(
                      "http://filin.mail.ru/pic?email=user"),
                "avatar retry never relaxes host or HTTPS safety");
            Check(VisualTreeHelper.GetParent(checkEditor!) is Border
                  { BorderThickness.Left: 1, CornerRadius.TopLeft: 5 },
                "rich mail editing area has a rounded visible border");
            ComposeSpellChecking.SetEnabled(false);
            Check(!checkEditor!.SpellCheck.IsEnabled,
                "global spelling switch turns off a live editor");
            ComposeSpellChecking.SetEnabled(true);
            Check(checkEditor.SpellCheck.IsEnabled,
                "global spelling switch turns on a live editor");
            previewTestRich.LoadMarkdown("# Заголовок\n- Пункт\n<u>Подчёркнуто</u>\n[Ссылка](https://example.org)");
            var roundTrip = previewTestRich.ToMarkdown();
            Check(roundTrip.Contains("# Заголовок", StringComparison.Ordinal) &&
                  roundTrip.Contains("- Пункт", StringComparison.Ordinal) &&
                  roundTrip.Contains("<u>Подчёркнуто</u>", StringComparison.Ordinal) &&
                  roundTrip.Contains("[Ссылка](https://example.org/)", StringComparison.Ordinal),
                "rich editor preserves heading, bullets, underline and links as Markdown");
            previewTestRich.LoadMarkdown("**Выделенный текст**");
            Check(previewTestRich.ToHtml().Contains("<strong>", StringComparison.Ordinal) &&
                  previewTestRich.ToHtml().Contains("Выделенный текст", StringComparison.Ordinal),
                "template Markdown becomes actual formatted mail HTML");
            previewTestRich.ApplyMarkdownSignature("**Подпись**", new[] { "**Подпись**" });
            previewTestRich.ApplyMarkdownSignature("**Подпись**", new[] { "**Подпись**" });
            var resultingHtml = previewTestRich.ToHtml();
            Check(resultingHtml.Contains("<strong>", StringComparison.Ordinal) &&
                  resultingHtml.Contains("Выделенный текст", StringComparison.Ordinal),
                "changing signature preserves original bold text");
            Check(resultingHtml.Split("Подпись", StringSplitOptions.None).Length == 2,
                "reselecting signature keeps exactly one formatted signature");
            previewTestRich.LoadReplyQuotedText("----- Исходное письмо -----\n> Прежняя переписка\n> С уважением");
            previewTestRich.ApplyTemplateMarkdown("**Новый ответ**\n\n**Подпись**",
                new[] { "**Подпись**" });
            previewTestRich.ApplyMarkdownSignature("Другая подпись",
                new[] { "**Подпись**", "Другая подпись" });
            var structuredReply = previewTestPlain.Text;
            Check(structuredReply.IndexOf("Новый ответ", StringComparison.Ordinal) <
                  structuredReply.IndexOf("Другая подпись", StringComparison.Ordinal) &&
                  structuredReply.IndexOf("Другая подпись", StringComparison.Ordinal) <
                  structuredReply.IndexOf("Исходное письмо", StringComparison.Ordinal) &&
                  structuredReply.Contains("> Прежняя переписка", StringComparison.Ordinal),
                "reply template and signature precede the unchanged historical correspondence");
            Check(!structuredReply.Contains("**Подпись**", StringComparison.Ordinal) &&
                  structuredReply.Split("Другая подпись", StringSplitOptions.None).Length == 2,
                "changing an embedded template signature does not duplicate it");
            previewTestWindow.Content = null;

            mail = new MainWindow();
            mail.Show();
            mail.UpdateLayout();

            var action = Require<Button>(mail, "MainComposeButton");
            Check(action.IsVisible, "New Mail action is visible above the message list");
            Check(Require<Grid>(mail, "SettingsWorkspace") is { }, "settings workspace loads");
            var reader = Require<Microsoft.Web.WebView2.Wpf.WebView2>(
                mail, "MessageWebView");
            Check(reader.Visibility == Visibility.Visible,
                "single WebView2 stays visible; loading occurs inside browser");
            Check(mail.FindName("ReaderLoadingOverlay") is null,
                "no WPF overlay can create WebView2 airspace flashes");

            // Stage 0 regression: the ACTIVE modern mail-row template,
            // not the obsolete XAML card, must expose a real pin control.
            var modernList = Require<ListBox>(mail, "MessagesGrid");
            var mailWorkspace = Require<Grid>(mail, "MailWorkspace");
            Check(mailWorkspace.ClipToBounds,
                "the two-pane mail workspace clips overflowing layout at splitter boundaries");
            Check(modernList.ClipToBounds &&
                  ScrollViewer.GetHorizontalScrollBarVisibility(modernList) ==
                      ScrollBarVisibility.Disabled,
                "mail rows cannot extend horizontally outside the resizable list pane");
            var pinRow = modernList.ItemTemplate?.LoadContent() as Grid;
            Check(pinRow is not null, "active mail template can be instantiated");
            var pinAction = FindDescendant<Button>(pinRow!,
                b => b.Name == "ModernPinActionButton");
            Check(pinAction is not null, "active mail row contains a pin action");
            Check(Grid.GetColumn(pinAction!) == 4 &&
                  pinAction.ToolTip?.ToString() == "Закрепить / открепить",
                "pin sits next to the star and advertises both operations");
            var pinShape = pinAction!.Content as System.Windows.Shapes.Path;
            Check(pinShape is not null,
                "pin button uses the shared vector icon");
            var pinnedTestMail = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "pin-test-123", "Письмо", "Текст", "Отправитель",
                "sender@example.invalid", null, null, 0, false, false, false)
                with { Pinned = true };
            pinRow!.DataContext = pinnedTestMail;
            pinRow.Measure(new Size(1200, 42));
            pinRow.Arrange(new Rect(0, 0, 1200, 42));
            pinRow.UpdateLayout();
            Check(pinShape!.Fill is SolidColorBrush pinnedFill &&
                  pinnedFill.Color == (Color)ColorConverter.ConvertFromString("#C62832"),
                "pinned mail icon uses the fixed #C62832 in the modern row");
            pinRow.DataContext = pinnedTestMail with { Pinned = false };
            pinRow.UpdateLayout();
            Check(pinShape.Fill is SolidColorBrush unpinnedFill &&
                  unpinnedFill.Color.A == 0,
                "unpinning switches the icon back to a hollow outline");
            Check(FindDescendant<Button>(pinRow,
                b => b.Name == "FlagActionButton") is not null,
                "restoring pin does not remove the star button");

            // Stage 1: only the active modern row owns columns and commands.
            // Legacy V033/XAML item templates must never silently replace it.
            Check(modernList.GroupStyle.Count == 1 &&
                  modernList.GroupStyle[0].HeaderTemplate is not null,
                "the single active list retains its date-group header");
            Check(pinRow.ColumnDefinitions.Count == 15,
                "the canonical row retains fifteen configurable column slots");
            Check(FindDescendant<CheckBox>(pinRow,
                c => c.Name == "BulkSelectCheckBox") is not null,
                "the canonical row retains checkbox-based bulk selection");
            Check(FindDescendant<Button>(pinRow,
                b => b.Name == "ModernUnreadActionButton") is not null,
                "the canonical row retains its unread marker command");
            Check(FindDescendant<Button>(pinRow,
                b => b.Name == "PinActionButton" ||
                     b.Name == "UnreadActionButton" ||
                     b.Name == "ArchiveActionButton") is null,
                "obsolete V033 row actions cannot coexist in the active template");
            Check(FindDescendant<System.Windows.Shapes.Path>(pinRow,
                p => Grid.GetColumn(p) == 10) is not null,
                "attachment indicator remains in the canonical row");

            var singleRowOrdering = new MailDateThenPinnedComparer();
            var earlier = pinnedTestMail with { Pinned = false, DateUnix = 1700000000 };
            var later = pinnedTestMail with { Pinned = false, DateUnix = 1700003600 };
            Check(singleRowOrdering.Compare(pinnedTestMail, later) < 0,
                "pinned mail stays ahead of a newer unpinned message");
            Check(singleRowOrdering.Compare(later, earlier) < 0,
                "unpinned messages remain ordered by descending timestamp");
            var dateGroups = new MailSectionGroupDescription();
            Check((string)dateGroups.GroupNameFromItem(
                      pinnedTestMail, 0, System.Globalization.CultureInfo.InvariantCulture)
                  == "Закреплённые",
                "pinned group remains distinct from ordinary date groups");



            var settingsAction = FindDescendant<Button>(mail,
                b => b.Content?.ToString()?.Contains("Настройки", StringComparison.Ordinal) == true);
            Check(settingsAction is not null, "Settings navigation is visible");
            settingsAction!.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, settingsAction));
            mail.UpdateLayout();
            Check(Require<Grid>(mail, "SettingsWorkspace").Visibility == Visibility.Visible,
                "Settings can actually be opened by its button");
            var settingsGrid = Require<Grid>(mail, "SettingsWorkspace");
            Check(settingsGrid.ColumnDefinitions.Count == 3,
                "settings and diagnostics have an independent splitter column");
            Check(FindDescendant<GridSplitter>(settingsGrid, x => Grid.GetColumn(x) == 1) is not null,
                "settings diagnostic divider can be dragged");
            Check(settingsGrid.ColumnDefinitions[0].Width.Value >= 390,
                "settings diagnostic splitter width is restored");

            var backgroundSwitch = Require<CheckBox>(mail, "BackgroundPushEnabledCheckBox");
            Check(!backgroundSwitch.IsVisible,
                "legacy automatic-subscription checkbox is removed from everyday settings");
            Check(Require<CheckBox>(mail, "TaskbarNotificationsEnabledCheckBox").IsVisible,
                "Windows popup preference remains visible in ordinary settings");
            var filterSwitch = Require<CheckBox>(
                mail, "ReplaySuppressionEnabledCheckBox");
            Check(filterSwitch.IsVisible && filterSwitch.IsEnabled,
                "local replay filter switch is visible and adjustable");
            var originalReplaySetting = filterSwitch.IsChecked == true;
            filterSwitch.IsChecked = !originalReplaySetting;
            Check(new AppSettingsStore().LoadReplaySuppressionEnabled() ==
                  !originalReplaySetting,
                "toggling the replay filter saves a real preference immediately");
            filterSwitch.IsChecked = originalReplaySetting;
            Check(new AppSettingsStore().LoadReplaySuppressionEnabled() ==
                  originalReplaySetting,
                "replay-filter preference can be restored without app restart");
            Check(Require<TextBlock>(mail, "BackgroundPushStatusText").IsVisible,
                "brief connection status remains visible");
            var diagnosticExpander = Require<Expander>(mail, "UnifiedDiagnosticsExpander");
            Check(!diagnosticExpander.IsExpanded && Grid.GetColumn(diagnosticExpander.Parent as UIElement ?? diagnosticExpander) == 2,
                "all diagnostics are collapsed and placed on the right");
            diagnosticExpander.IsExpanded = true;
            mail.UpdateLayout();
            var diagTabs = Require<TabControl>(mail, "UnifiedDiagnosticsTabs");
            Check(diagTabs.Items.Count == 3,
                "Google PushMe, protocol and application diagnostics share one tool");
            diagTabs.SelectedIndex = 2;
            mail.UpdateLayout();
            Check(Require<TextBox>(mail, "ApplicationDiagnosticsLogTextBox").IsReadOnly,
                "old application diagnostic log is read-only in unified tool");
            diagTabs.SelectedIndex = 0;
            mail.UpdateLayout();
            var pushLog = Require<TextBox>(mail, "PushDiagnosticsLogTextBox");
            Check(pushLog.IsReadOnly && pushLog.IsVisible,
                "read-only Google PushMe diagnostic journal visible in Settings");
            foreach (var name in new[]
                     {
                         "RefreshPushDiagnosticsButton",
                         "CopyPushDiagnosticsButton",
                         "SavePushDiagnosticsButton",
                         "ClearPushDiagnosticsButton"
                     })
                Check(Require<Button>(mail, name).IsVisible,
                    "push diagnostics action exists: " + name);
            var exportMode = Require<ComboBox>(
                mail, "PushDiagnosticsExportModeComboBox");
            Check(exportMode.IsVisible && exportMode.Items.Count == 2 &&
                  exportMode.SelectedIndex == 0,
                "diagnostic report defaults to anonymized, with explicit full-address option");
            var refreshPushLog = Require<Button>(mail, "RefreshPushDiagnosticsButton");
            refreshPushLog.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, refreshPushLog));
            Check(pushLog.Text.Contains("Google / PushMe", StringComparison.Ordinal),
                "diagnostic viewer refreshes without making network calls");
            var pushSettings = Require<Button>(mail, "OpenPushProbeButton");
            Check(pushSettings.IsVisible &&
                  pushSettings.Content?.ToString() == "Проверить один аккаунт",
                "single-account diagnostic test is inside collapsed unified tool");
            pushSettings.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, pushSettings));
            mail.UpdateLayout();
            var pushWindow = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w => w.Title.Contains("Проверка мгновенных уведомлений", StringComparison.Ordinal));
            Check(pushWindow is not null && pushWindow.IsVisible,
                "Native push experiment opens a separate diagnostics window");
            var runControl = FindDescendant<Button>(pushWindow!,
                b => b.Content?.ToString() == "Начать проверку");
            Check(runControl is not null && !runControl.IsEnabled,
                "No push subscription can start without explicit consent");
            var selectAccount = FindDescendant<ComboBox>(pushWindow!,
                cb => cb.MinWidth >= 300 && cb.IsEnabled);
            Check(selectAccount is not null,
                "manual test displays account selector before registration");
            var trayNotifyCheckbox = Require<CheckBox>(
                mail, "TaskbarNotificationsEnabledCheckBox");
            Check(trayNotifyCheckbox.IsEnabled,
                "taskbar popup preference remains adjustable during manual-only test");
            pushWindow!.Close();

            Check(Require<TextBlock>(mail, "GoogleRecipientStatusText").IsVisible,
                "protected Google registration has a separate visible status");
            Check(Require<TextBlock>(mail, "GoogleMcsStatusText").IsVisible,
                "Google MCS channel has an independent visible connection state");
            var groupButton = Require<Button>(mail, "OpenPushGroupManagerButton");
            Check(groupButton.IsVisible && groupButton.IsEnabled &&
                groupButton.Content?.ToString() == "Google и группы PushMe",
                "durable Google recipient and group manager is available");
            groupButton.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, groupButton));
            mail.UpdateLayout();
            var groupManager = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w =>
                    w.Title.Contains("Google и группы PushMe", StringComparison.Ordinal));
            Check(groupManager is not null && groupManager.IsVisible,
                "persistent group manager opens without network startup");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Зарегистрировать Google") is not null,
                "stage-one explicit Google registration exists");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Остановить приём") is not null,
                "MCS-only Stop button is separate from group deletion");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Удалить выбранную группу") is not null,
                "address-specific PushMe group deletion has an independent action");
            Check(FindDescendant<Button>(groupManager!,
                    b => b.Content?.ToString() == "Удалить регистрацию Google") is not null,
                "Google deletion requires its own explicit action");
            groupManager!.Close();

            // Functional offline UI test: with 20 synthetic mailboxes the
            // selector starts with 19, requires consent, and never selects 20.
            IReadOnlyList<string>? submitted = null;
            var fakeLogins = Enumerable.Range(1, 20)
                .Select(i => "dummy" + i + "@example.invalid");
            var simulated = new Group19PushProbeWindow(fakeLogins,
                selected => { submitted = selected.ToArray(); return true; },
                () => { }) { Owner = mail };
            simulated.Show();
            simulated.UpdateLayout();
            var simulatedStart = FindDescendant<Button>(simulated,
                b => b.Content?.ToString() == "Зарегистрировать 19 аккаунтов");
            var confirmation = FindDescendant<CheckBox>(simulated,
                c => c.Content?.ToString()?.Contains("Подтверждаю регистрацию только",
                    StringComparison.Ordinal) == true);
            Check(simulatedStart is not null && !simulatedStart.IsEnabled,
                "exactly nineteen are preselected but registration requires user consent");
            Check(confirmation is not null, "explicit nineteen-account consent exists");
            confirmation!.IsChecked = true;
            Check(simulatedStart!.IsEnabled,
                "nineteen preselected accounts enable registration after consent");
            simulatedStart.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, simulatedStart));
            Check(submitted is { Count: 19 } &&
                  submitted.Distinct(StringComparer.OrdinalIgnoreCase).Count() == 19,
                "manual group test passes exactly nineteen unique account IDs");
            simulated.Close();

            var palette = FindDescendant<Expander>(mail,
                e => string.Equals(e.Header?.ToString(),
                    "Цветовая схема", StringComparison.Ordinal));
            Check(palette is not null, "palette editor is present");
            palette!.IsExpanded = true;
            mail.UpdateLayout();
            Check(FindDescendant<TextBox>(palette,
                b => b.Name == "PrimaryColorTextBox") is not null,
                "a single primary color input is present");
            Check(FindParent<GroupBox>(palette!)?.Header?.ToString() == "Внешний вид",
                "palette section is inside Appearance rather than beside it");

            var spellToggle = Require<CheckBox>(mail, "SpellCheckEnabledCheckBox");
            Check(spellToggle.IsChecked == ComposeSpellChecking.Enabled,
                "settings display the actual spelling state");
            var livePreviewEditor = FindDescendant<RichTextBox>(
                Require<FrameworkElement>(mail, "PreviewComposePanel"), _ => true);
            Check(livePreviewEditor is not null &&
                  VisualTreeHelper.GetParent(livePreviewEditor) is Border
                  { BorderThickness.Left: 1 },
                "inline forwarding/reply editor has the same themed border");
            var previousSpellSetting = spellToggle.IsChecked == true;
            spellToggle.IsChecked = !previousSpellSetting;
            Check(ComposeSpellChecking.Enabled == !previousSpellSetting,
                "spelling checkbox immediately changes the runtime setting");
            spellToggle.IsChecked = previousSpellSetting;
            Check(ComposeSpellChecking.Enabled == previousSpellSetting,
                "spelling checkbox restores the runtime setting");

            var signature = FindDescendant<Expander>(mail,
                e => e.Header?.ToString() == "Подписи");
            var template = FindDescendant<Expander>(mail,
                e => e.Header?.ToString() == "Шаблоны");
            Check(signature is not null && template is not null,
                "signature/template expanders are present");
            Check(ReferenceEquals(VisualTreeHelper.GetParent(signature!),
                     VisualTreeHelper.GetParent(template!)),
                "signature and template are siblings");
            var settingsStack = (Panel)VisualTreeHelper.GetParent(signature!);
            Check(settingsStack.Children.IndexOf(template!) ==
                  settingsStack.Children.IndexOf(signature!) + 1,
                "Templates appears immediately after Signatures");
            template!.IsExpanded = true;
            mail.UpdateLayout();
            Check(Require<ListBox>(mail, "MailTemplatesListBox") is { },
                "settings keep a template list");
            Check(Require<Button>(mail, "EditSelectedTemplateButton") is { IsEnabled: false },
                "Edit is disabled with no selected template");
            Check(Require<Button>(mail, "DeleteSelectedTemplateButton") is { IsEnabled: false },
                "Delete is disabled with no selected template");
            Check(mail.FindName("TemplateBodyTextBox") is null,
                "template body no longer occupies the settings sidebar");

            var templateTestRoot = Path.Combine(Path.GetTempPath(),
                "MailRuTemplateEditor-" + Guid.NewGuid().ToString("N"));
            try
            {
                var files = new MarkdownTemplateStore(Path.Combine(templateTestRoot, "Templates"));
                var signs = new MarkdownSignatureStore(Path.Combine(templateTestRoot, "Signatures"));
                var localSettings = new AppSettingsStore(templateTestRoot);
                signs.Save(new SavedSignature { Name = "Рабочая", Body = "**Подпись**" });
                var sampleTemplate = new SavedMailTemplate
                {
                    Name = "Проверочный", Subject = "Тема", Body = "**Текст письма**",
                    Attachments = new List<string>()
                };
                var detached = new TemplateEditorWindow(mail, files, signs, localSettings, sampleTemplate);
                detached.Show();
                detached.UpdateLayout();
                Check(detached.IsVisible && detached.MinWidth >= 600,
                    "template opens a separate resizable window");
                var templateRich = FindDescendant<RichTextBox>(detached, _ => true);
                Check(templateRich is not null, "template editor reuses rich mail formatting");
                Check(templateRich!.SpellCheck.IsEnabled == ComposeSpellChecking.Enabled,
                    "template editor inherits the shared spelling setting");
                Check(VisualTreeHelper.GetParent(templateRich) is Border
                      { BorderThickness.Left: 1 },
                    "standalone template body has a visible themed border");
                Check(FindDescendant<ComboBox>(detached,
                          x => x.DisplayMemberPath == nameof(SavedSignature.Name)) is not null,
                    "template editor exposes file-backed signature choices");
                Check(FindDescendant<TextBox>(detached,
                          x => x.Name == "ComposeToTextBox") is null,
                    "template editor never includes a recipient address field");
                Check(FindDescendant<TextBlock>(detached,
                          x => x.Text == "Тема письма") is null,
                    "template editor contains no subject field");
                detached.Close();
                var position = localSettings.LoadWindowGeometry("template-editor");
                Check(position is not null && position.Width > 0 && position.Height > 0,
                    "template editor remembers its independent window geometry");
            }
            finally
            {
                if (Directory.Exists(templateTestRoot))
                    Directory.Delete(templateTestRoot, recursive: true);
            }


            settingsAction!.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, settingsAction));
            mail.UpdateLayout();
            Check(Require<Grid>(mail, "MailWorkspace").Visibility == Visibility.Visible,
                "Settings button returns to mail without duplicate navigation");

            action.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, action));
            editor = Application.Current.Windows
                .OfType<Window>()
                .FirstOrDefault(w => w.Title.StartsWith("Новое письмо", StringComparison.Ordinal));
            Check(editor is not null && editor.IsVisible, "New Mail opens its own window");
            var from = Require<TextBox>(mail, "ComposeFromTextBox");
            Check(from.IsReadOnly && from.Text.Length > 0,
                "From address is always visible and read-only");
            Check(ReferenceEquals(Window.GetWindow(from), editor),
                "From field belongs to the independent New Mail window");
            Check(Require<ComboBox>(mail, "ComposeSignatureComboBox").IsVisible,
                "signature chooser is visible in New Mail");
            Check(Require<ComboBox>(mail, "ComposeTemplateComboBox").IsVisible,
                "template chooser is visible in New Mail");
            Check(Require<TextBlock>(mail, "ComposeHeaderText").IsVisible,
                "New Mail keeps its visible heading");
            var outgoingRich = FindDescendant<RichTextBox>(editor!, _ => true);
            Check(outgoingRich is not null &&
                  VisualTreeHelper.GetParent(outgoingRich) is Border
                  { BorderThickness.Left: 1 },
                "new-mail body has the same themed outline");
            var send = Require<Button>(mail, "SendButton");
            Check(ReferenceEquals(
                    send.ReadLocalValue(Control.BackgroundProperty),
                    DependencyProperty.UnsetValue),
                "send button uses the shared theme without a forced blue fill");

            editor.Close();
            editor = null;
            var showReply = typeof(MainWindow).GetMethod("OpenReplyComposeWindow",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(showReply is not null,
                "standalone New Mail reply handler is available");
            var sample = new MailRuDesktop.Protocol.MailRuMessageSummary(
                "reply-test-123", "Договор", "Текст письма", "Тест",
                "sender@example.invalid", null, null, 0, true, false, false);
            showReply!.Invoke(mail, new object?[] { sample, null });
            editor = Application.Current.Windows.OfType<Window>()
                .FirstOrDefault(w => w.Title.StartsWith("Ответ: Договор", StringComparison.Ordinal));
            Check(editor is { IsVisible: true },
                "Reply launches the independent composer without the New Mail caption");
            Check(!Require<TextBlock>(mail, "ComposeHeaderText").IsVisible,
                "reply form does not display the redundant New Mail heading");
            Check(Require<TextBox>(mail, "ComposeToTextBox").Text ==
                    "sender@example.invalid" &&
                  Require<TextBox>(mail, "ComposeSubjectTextBox").Text == "Re: Договор" &&
                  Require<TextBox>(mail, "ComposeBodyTextBox").Text.Contains("Текст письма"),
                "reply fills recipient, Re subject, and original quote");
            var applyTemplate = typeof(MainWindow).GetMethod("ApplyComposeTemplate",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(applyTemplate is not null, "compose template method exists");
            var replyEditor = FindDescendant<RichTextBox>(editor!, _ => true);
            Check(replyEditor is not null, "reply uses the rich editor");
            replyEditor!.CaretPosition = replyEditor.Document.ContentEnd;
            applyTemplate!.Invoke(mail, new object[] { new SavedMailTemplate
            {
                Name = "Ответ", Subject = "Запрещённая смена темы",
                Body = "**Текст из шаблона**", Attachments = new List<string>()
            } });
            var composedText = Require<TextBox>(mail, "ComposeBodyTextBox").Text;
            Check(Require<TextBox>(mail, "ComposeSubjectTextBox").Text == "Re: Договор" &&
                  composedText.Contains("Текст из шаблона", StringComparison.Ordinal) &&
                  composedText.Contains("> Текст письма", StringComparison.Ordinal) &&
                  composedText.IndexOf("Текст из шаблона", StringComparison.Ordinal) <
                  composedText.IndexOf("Исходное письмо", StringComparison.Ordinal),
                "template never changes reply subject or removes quoted correspondence");
            var applySignature = typeof(MainWindow).GetMethod("ApplyComposeSignature",
                BindingFlags.Instance | BindingFlags.NonPublic);
            replyEditor.CaretPosition = replyEditor.Document.ContentEnd;
            applySignature!.Invoke(mail, new object[] { new SavedSignature
            {
                Name = "Рабочая", Body = "С уважением,\nОтдел"
            }, true });
            var afterSignature = Require<TextBox>(mail, "ComposeBodyTextBox").Text;
            Check(afterSignature.IndexOf("С уважением,", StringComparison.Ordinal) <
                  afterSignature.IndexOf("Исходное письмо", StringComparison.Ordinal) &&
                  afterSignature.Contains("> Текст письма", StringComparison.Ordinal),
                "signature position is independent of caret in the quoted history");
            var replyId = typeof(MainWindow).GetField("_composeReplyToId",
                BindingFlags.Instance | BindingFlags.NonPublic)?.GetValue(mail) as string;
            Check(replyId == "reply-test-123",
                "sending from standalone reply retains precise source message ID");
            Check(Require<FrameworkElement>(mail, "PreviewComposePanel").Visibility !=
                  Visibility.Visible,
                "Reply no longer opens the inline editor above the mail body");
            editor.Close();
            editor = null;

            var dialogType = typeof(MainWindow).Assembly.GetType("MailRuDesktop.App.AppDialog");
            var createNotice = dialogType?.GetMethod("CreateWindow",
                BindingFlags.Static | BindingFlags.NonPublic);
            Check(createNotice is not null, "shared notification window factory exists");
            notice = createNotice!.Invoke(null,
                new object[] { mail, "Тест", "Короткое сообщение" }) as Window;
            Check(notice is not null, "short notification is created");
            ((StackPanel)notice!.Tag).Children.Add(new Button { Content = "Понятно",
                Padding = new Thickness(14, 7, 14, 7) });
            notice.Show();
            notice.UpdateLayout();
            Check(notice.SizeToContent == SizeToContent.Height,
                "notification height adapts to content");
            Check(notice.MinHeight <= 170 && notice.ActualHeight < 260,
                "short notification has no oversized lower empty region");
            notice.Close();
            notice = null;

            var pickerType = typeof(MainWindow).Assembly.GetType(
                "MailRuDesktop.App.PaletteColorPickerWindow");
            Check(pickerType is not null, "color picker window exists");
            colorPicker = Activator.CreateInstance(pickerType!,
                new object[] { mail, "Проверка цвета", "#FF0000" }) as Window;
            Check(colorPicker is not null, "palette picker can be constructed");
            var stripField = pickerType!.GetField("_brightnessBitmap",
                BindingFlags.Instance | BindingFlags.NonPublic);
            var strip = stripField?.GetValue(colorPicker) as WriteableBitmap;
            Check(strip is not null, "right-side brightness strip is available");
            var initial = new byte[strip!.PixelWidth * strip.PixelHeight * 4];
            strip.CopyPixels(initial, strip.PixelWidth * 4, 0);
            var moveColor = pickerType.GetMethod("PickSurface",
                BindingFlags.Instance | BindingFlags.NonPublic);
            Check(moveColor is not null, "surface click operation exists");
            moveColor!.Invoke(colorPicker, new object[] { new Point(210, 50) });
            var updated = new byte[initial.Length];
            strip.CopyPixels(updated, strip.PixelWidth * 4, 0);
            Check(!initial.SequenceEqual(updated),
                "right brightness gradient redraws when main color surface changes");
            colorPicker!.Close();
            colorPicker = null;

            VerifyPermanentBrowserShell(mail, reader);

            var tray = typeof(MainWindow).GetField("_pushNotificationArea",
                BindingFlags.NonPublic | BindingFlags.Instance)?.GetValue(mail)
                as System.Windows.Forms.NotifyIcon;
            Check(tray is not null && tray.Visible,
                "tray icon remains visible independently of push subscription");
            Check(tray.ContextMenuStrip?.Items.Count == 2 &&
                  tray.ContextMenuStrip.Items[0].Text == "Развернуть" &&
                  tray.ContextMenuStrip.Items[1].Text == "Выход",
                "tray offers Restore and Exit");
            mail.Close();
            Check(!mail.IsVisible && !mail.ShowInTaskbar,
                "window X hides application into tray without terminating it");
            typeof(MainWindow).GetMethod("RestoreFromTray",
                BindingFlags.Instance | BindingFlags.NonPublic)?.Invoke(mail, null);
            Check(mail.IsVisible && mail.ShowInTaskbar,
                "tray Restore makes application visible again");
            typeof(MainWindow).GetMethod("ExitFromTray",
                BindingFlags.Instance | BindingFlags.NonPublic)?.Invoke(mail, null);

            Console.WriteLine("Windows WPF UI interaction smoke: PASS");
            return 0;
        }
        catch (Exception ex)
        {
            Console.Error.WriteLine("Windows WPF UI interaction smoke: FAIL");
            Console.Error.WriteLine(ex);
            return 1;
        }
        finally
        {
            colorPicker?.Close();
            notice?.Close();
            editor?.Close();
            mail?.Close();
        }
    }

    private static void VerifyPermanentBrowserShell(
        Window owner, Microsoft.Web.WebView2.Wpf.WebView2 reader)
    {
        var ready = AwaitOnDispatcher(WaitForShell(reader));
        Check(ready, "WebView2 has initialized exactly one persistent reader shell");

        var browser = reader.CoreWebView2!;
        var extraNavigations = 0;
        reader.NavigationStarting += (_, _) => extraNavigations++;
        var type = owner.GetType();
        var begin = type.GetMethod("BeginReaderTransition",
            BindingFlags.Instance | BindingFlags.NonPublic);
        var show = type.GetMethod("ShowReaderHtml",
            BindingFlags.Instance | BindingFlags.NonPublic);
        Check(begin is not null && show is not null,
            "browser-shell staging entry points are present");

        begin!.Invoke(owner, new object?[] { null, "Загрузка письма…" });
        show!.Invoke(owner, new object?[]
        {
            "<p id='integration-message'>FIRST</p>" +
            "<img src='data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs='>"
        });
        Check(AwaitOnDispatcher(WaitForMessage(browser, "FIRST")),
            "first message appears with its image in a single shell");

        begin.Invoke(owner, new object?[] { null, "Загрузка письма…" });
        show.Invoke(owner, new object?[]
        {
            "<p id='integration-message'>SECOND</p>"
        });
        Check(AwaitOnDispatcher(WaitForMessage(browser, "SECOND")),
            "new letter replaces previous document atomically");
        Check(extraNavigations == 0,
            "switching mail must not navigate the top-level WebView2");
        Check(reader.Visibility == Visibility.Visible,
            "the same WPF browser remains continuously visible");
    }

    private static async Task<bool> WaitForShell(
        Microsoft.Web.WebView2.Wpf.WebView2 reader)
    {
        for (var i = 0; i < 120; i++)
        {
            if (reader.CoreWebView2 is not null)
            {
                var result = await reader.CoreWebView2.ExecuteScriptAsync(
                    "(Boolean(document.getElementById('frames')))");
                if (result == "true")
                    return true;
            }
            await Task.Delay(70);
        }
        return false;
    }

    private static async Task<bool> WaitForMessage(
        Microsoft.Web.WebView2.Core.CoreWebView2 browser, string expected)
    {
        for (var i = 0; i < 100; i++)
        {
            var response = await browser.ExecuteScriptAsync(
                "(() => {const frames=document.querySelectorAll('iframe[data-active]');" +
                "return frames.length === 1 && " +
                "frames[0].contentDocument?.getElementById('integration-message')?.textContent " +
                "=== " + System.Text.Json.JsonSerializer.Serialize(expected) + ";})()");
            if (response == "true")
                return true;
            await Task.Delay(80);
        }
        return false;
    }

    private static T AwaitOnDispatcher<T>(Task<T> task)
    {
        var frame = new DispatcherFrame();
        var watch = Stopwatch.StartNew();
        var ticker = new DispatcherTimer(DispatcherPriority.Background)
        {
            Interval = TimeSpan.FromMilliseconds(30)
        };
        ticker.Tick += (_, _) =>
        {
            if (task.IsCompleted || watch.Elapsed > TimeSpan.FromSeconds(15))
                frame.Continue = false;
        };
        ticker.Start();
        try { Dispatcher.PushFrame(frame); }
        finally { ticker.Stop(); }
        if (!task.IsCompleted)
            throw new TimeoutException("Timed out waiting for WebView2 browser shell.");
        return task.GetAwaiter().GetResult();
    }

    private static T Require<T>(FrameworkElement root, string name)
        where T : FrameworkElement
    {
        var found = root.FindName(name) as T;
        Check(found is not null, $"XAML named control {name}");
        return found!;
    }

    private static T? FindDescendant<T>(DependencyObject source, Func<T,bool> match)
        where T : DependencyObject
    {
        if (source is T item && match(item))
            return item;
        for (var i = 0; i < VisualTreeHelper.GetChildrenCount(source); i++)
        {
            var found = FindDescendant(VisualTreeHelper.GetChild(source, i), match);
            if (found is not null)
                return found;
        }
        return null;
    }

    private static T? FindParent<T>(DependencyObject source) where T : DependencyObject
    {
        var parent = VisualTreeHelper.GetParent(source);
        while (parent is not null)
        {
            if (parent is T typed)
                return typed;
            parent = VisualTreeHelper.GetParent(parent);
        }
        return null;
    }

    private static void Expect<T>(T actual, T expected, string label)
    {
        Check(EqualityComparer<T>.Default.Equals(actual, expected), label);
    }

    private static void Check(bool condition, string description)
    {
        if (!condition)
            throw new InvalidOperationException(description);
        Console.WriteLine("PASS " + description);
    }
}
