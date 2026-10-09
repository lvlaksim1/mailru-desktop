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
            // The test owns the STA thread without Application.Run().
            // Preserve WPF's dispatcher synchronization context across await.
            SynchronizationContext.SetSynchronizationContext(
                new DispatcherSynchronizationContext(Dispatcher.CurrentDispatcher));
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

            var settingsAction = FindDescendant<Button>(mail,
                b => b.Content?.ToString()?.Contains("Настройки", StringComparison.Ordinal) == true);
            Check(settingsAction is not null, "Settings navigation is visible");
            settingsAction!.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, settingsAction));
            mail.UpdateLayout();
            Check(Require<Grid>(mail, "SettingsWorkspace").Visibility == Visibility.Visible,
                "Settings can actually be opened by its button");

            var backgroundSwitch = Require<CheckBox>(mail, "BackgroundPushEnabledCheckBox");
            Check(backgroundSwitch.IsVisible && !backgroundSwitch.IsEnabled &&
                  backgroundSwitch.IsChecked != true,
                "single-account test release disables multi-account automatic subscription");
            Check(Require<TextBlock>(mail, "BackgroundPushStatusText").Text
                    .Contains("Проверочная версия", StringComparison.Ordinal),
                "manual-only testing mode is clearly explained in Settings");
            Check(Require<CheckBox>(mail, "TaskbarNotificationsEnabledCheckBox").IsVisible,
                "Windows popup notification setting is visible and independent");
            Check(Require<TextBlock>(mail, "BackgroundPushStatusText").IsVisible,
                "Persistent push per-account connection state is visible");
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
            var refreshPushLog = Require<Button>(mail, "RefreshPushDiagnosticsButton");
            refreshPushLog.RaiseEvent(new RoutedEventArgs(ButtonBase.ClickEvent, refreshPushLog));
            Check(pushLog.Text.Contains("Google / PushMe", StringComparison.Ordinal),
                "diagnostic viewer refreshes without making network calls");
            var pushSettings = Require<Button>(mail, "OpenPushProbeButton");
            Check(pushSettings.IsVisible &&
                  pushSettings.Content?.ToString() == "Выбрать аккаунт для проверки",
                "explicit single-account selection button is accessible in Settings");
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

            var palette = FindDescendant<Expander>(mail,
                e => string.Equals(e.Header?.ToString(),
                    "Цвета элементов интерфейса", StringComparison.Ordinal));
            Check(palette is not null, "palette editor is present");
            Check(FindParent<GroupBox>(palette!)?.Header?.ToString() == "Внешний вид",
                "palette section is inside Appearance rather than beside it");

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
            var send = Require<Button>(mail, "SendButton");
            Check(ReferenceEquals(
                    send.ReadLocalValue(Control.BackgroundProperty),
                    DependencyProperty.UnsetValue),
                "send button uses the shared theme without a forced blue fill");

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

    private static void Check(bool condition, string description)
    {
        if (!condition)
            throw new InvalidOperationException(description);
        Console.WriteLine("PASS " + description);
    }
}
