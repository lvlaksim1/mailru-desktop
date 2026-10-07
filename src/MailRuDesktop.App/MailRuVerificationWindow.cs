using System.IO;
using System.Text.Json;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Threading;
using Microsoft.Web.WebView2.Core;
using Microsoft.Web.WebView2.Wpf;

namespace MailRuDesktop.App;

internal sealed class MailRuVerificationWindow : Window
{
    private readonly Uri _startUri;
    private readonly string _profilePath;
    private readonly bool _waitForRecaptchaToken;
    private readonly WebView2 _webView = new();
    private readonly TextBlock _statusText = new();
    private readonly Button _continueButton = new();
    private readonly DispatcherTimer _recaptchaProbeTimer;
    private bool _probeInFlight;

    public string? RecaptchaResponse { get; private set; }
    public string? SessionCookieHeader { get; private set; }

    public MailRuVerificationWindow(
        string url,
        bool waitForRecaptchaToken = false)
    {
        if (!Uri.TryCreate(url, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            throw new ArgumentException(
                "Verification URL must use HTTPS.",
                nameof(url));
        }

        _startUri = uri;
        _waitForRecaptchaToken = waitForRecaptchaToken;
        _profilePath = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop",
            "AuthSessions",
            Guid.NewGuid().ToString("N"));

        _recaptchaProbeTimer = new DispatcherTimer
        {
            Interval = TimeSpan.FromMilliseconds(750)
        };
        _recaptchaProbeTimer.Tick += RecaptchaProbeTimer_Tick;

        Title = "Проверка Mail.ru";
        Width = 940;
        Height = 720;
        MinWidth = 720;
        MinHeight = 520;
        WindowStartupLocation = WindowStartupLocation.CenterOwner;
        ShowInTaskbar = false;

        SetResourceReference(BackgroundProperty, "AppWindowBrush");
        SetResourceReference(ForegroundProperty, "AppTextBrush");

        Content = BuildContent();

        Loaded += VerificationWindow_Loaded;
        Closed += VerificationWindow_Closed;
    }

    private UIElement BuildContent()
    {
        var root = new Grid();
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });
        root.RowDefinitions.Add(new RowDefinition { Height = new GridLength(1, GridUnitType.Star) });
        root.RowDefinitions.Add(new RowDefinition { Height = GridLength.Auto });

        var header = new Border
        {
            Padding = new Thickness(14, 11, 14, 11)
        };
        header.SetResourceReference(Border.BackgroundProperty, "AppControlBrush");

        var title = new TextBlock
        {
            Text = "Штатная проверка Mail.ru",
            FontWeight = FontWeights.SemiBold,
            FontSize = 15
        };
        title.SetResourceReference(TextBlock.ForegroundProperty, "AppTextBrush");
        header.Child = title;

        Grid.SetRow(header, 0);
        root.Children.Add(header);

        _webView.Margin = new Thickness(8);
        Grid.SetRow(_webView, 1);
        root.Children.Add(_webView);

        var footer = new Grid
        {
            Margin = new Thickness(12, 4, 12, 12)
        };
        footer.ColumnDefinitions.Add(new ColumnDefinition { Width = new GridLength(1, GridUnitType.Star) });
        footer.ColumnDefinitions.Add(new ColumnDefinition { Width = GridLength.Auto });

        _statusText.Text = _waitForRecaptchaToken
            ? "Поставьте галочку reCAPTCHA. Когда Mail.ru выдаст результат проверки, кнопка «Продолжить» станет доступна."
            : "Окно использует отдельное временное хранилище cookies и не связано с браузерами на компьютере.";
        _statusText.TextWrapping = TextWrapping.Wrap;
        _statusText.VerticalAlignment = VerticalAlignment.Center;
        _statusText.Margin = new Thickness(0, 0, 14, 0);
        _statusText.SetResourceReference(TextBlock.ForegroundProperty, "AppMutedTextBrush");
        footer.Children.Add(_statusText);

        var buttons = new StackPanel
        {
            Orientation = Orientation.Horizontal,
            HorizontalAlignment = HorizontalAlignment.Right
        };
        Grid.SetColumn(buttons, 1);

        _continueButton.Content = "Продолжить";
        _continueButton.Padding = new Thickness(14, 7, 14, 7);
        _continueButton.IsEnabled = false;
        _continueButton.Click += ContinueButton_Click;

        var cancel = new Button
        {
            Content = "Отмена",
            Padding = new Thickness(14, 7, 14, 7),
            Margin = new Thickness(8, 0, 0, 0),
            IsCancel = true
        };
        cancel.Click += (_, _) => DialogResult = false;

        buttons.Children.Add(_continueButton);
        buttons.Children.Add(cancel);
        footer.Children.Add(buttons);

        Grid.SetRow(footer, 2);
        root.Children.Add(footer);

        return root;
    }

    private async void VerificationWindow_Loaded(
        object sender,
        RoutedEventArgs e)
    {
        try
        {
            Directory.CreateDirectory(_profilePath);

            var environment = await CoreWebView2Environment.CreateAsync(
                browserExecutableFolder: null,
                userDataFolder: _profilePath);

            await _webView.EnsureCoreWebView2Async(environment);

            _webView.CoreWebView2.Settings.AreDevToolsEnabled = false;
            _webView.CoreWebView2.Settings.AreDefaultContextMenusEnabled = false;
            _webView.CoreWebView2.Settings.IsPasswordAutosaveEnabled = false;
            _webView.CoreWebView2.Settings.IsGeneralAutofillEnabled = false;

            _webView.CoreWebView2.NavigationStarting += CoreWebView2_NavigationStarting;
            _webView.CoreWebView2.NavigationCompleted += CoreWebView2_NavigationCompleted;
            _webView.CoreWebView2.NewWindowRequested += CoreWebView2_NewWindowRequested;

            _webView.Source = _startUri;

            if (_waitForRecaptchaToken)
                _recaptchaProbeTimer.Start();
        }
        catch (Exception ex)
        {
            _statusText.Text = "Не удалось открыть страницу проверки.";
            _continueButton.IsEnabled = false;
            DiagnosticLog.Write(
                "auth_verification_window",
                ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void CoreWebView2_NavigationStarting(
        object? sender,
        CoreWebView2NavigationStartingEventArgs e)
    {
        if (!Uri.TryCreate(e.Uri, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            e.Cancel = true;
            _statusText.Text =
                "Небезопасный переход заблокирован. В окне проверки разрешены только HTTPS-страницы.";
            return;
        }

        if (!_waitForRecaptchaToken)
            _statusText.Text = $"Проверка · {uri.Host}";
    }

    private void CoreWebView2_NewWindowRequested(
        object? sender,
        CoreWebView2NewWindowRequestedEventArgs e)
    {
        if (!Uri.TryCreate(e.Uri, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            e.Handled = true;
            _statusText.Text = "Небезопасное новое окно заблокировано.";
            return;
        }

        e.Handled = true;
        _webView.Source = uri;
    }

    private void CoreWebView2_NavigationCompleted(
        object? sender,
        CoreWebView2NavigationCompletedEventArgs e)
    {
        if (!e.IsSuccess)
        {
            _statusText.Text =
                $"Страница проверки не загрузилась: {e.WebErrorStatus}.";
            return;
        }

        if (_waitForRecaptchaToken)
        {
            _continueButton.IsEnabled =
                !string.IsNullOrWhiteSpace(RecaptchaResponse);
            return;
        }

        _continueButton.IsEnabled = true;
        var current = _webView.Source;
        _statusText.Text = current is null
            ? "Завершите штатную проверку и нажмите «Продолжить»."
            : $"Проверка · {current.Host}. После успешного завершения нажмите «Продолжить».";
    }

    private async void RecaptchaProbeTimer_Tick(
        object? sender,
        EventArgs e)
    {
        if (_probeInFlight ||
            _webView.CoreWebView2 is null ||
            !string.IsNullOrWhiteSpace(RecaptchaResponse))
        {
            return;
        }

        _probeInFlight = true;
        try
        {
            const string script = """
                (() => {
                    let value = "";
                    const nodes = document.querySelectorAll(
                        'textarea[name="g-recaptcha-response"], input[name="g-recaptcha-response"]');
                    for (const node of nodes) {
                        const candidate = (node.value || "").trim();
                        if (candidate) {
                            value = candidate;
                            break;
                        }
                    }

                    if (!value &&
                        window.grecaptcha &&
                        typeof window.grecaptcha.getResponse === "function") {
                        try {
                            value = (window.grecaptcha.getResponse() || "").trim();
                        } catch (_) {
                        }
                    }

                    return value;
                })();
                """;

            var raw = await _webView.CoreWebView2.ExecuteScriptAsync(script);
            var token = JsonSerializer.Deserialize<string>(raw);

            if (string.IsNullOrWhiteSpace(token) || token.Length < 20)
                return;

            RecaptchaResponse = token;
            _recaptchaProbeTimer.Stop();
            _continueButton.IsEnabled = true;
            _statusText.Text =
                "reCAPTCHA подтверждена. Нажмите «Продолжить» — результат проверки будет передан в исходную попытку входа.";
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write(
                "auth_recaptcha_probe",
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            _probeInFlight = false;
        }
    }

    private async void ContinueButton_Click(
        object sender,
        RoutedEventArgs e)
    {
        if (_webView.CoreWebView2 is null)
            return;

        if (_waitForRecaptchaToken)
        {
            if (string.IsNullOrWhiteSpace(RecaptchaResponse))
            {
                _continueButton.IsEnabled = false;
                _statusText.Text =
                    "Результат reCAPTCHA ещё не получен. Завершите проверку на странице.";
                return;
            }

            DialogResult = true;
            return;
        }

        _continueButton.IsEnabled = false;
        try
        {
            var cookies = await _webView.CoreWebView2.CookieManager
                .GetCookiesAsync("https://aj-https.mail.ru/");

            SessionCookieHeader = string.Join(
                "; ",
                cookies
                    .Where(cookie => !string.IsNullOrWhiteSpace(cookie.Name))
                    .Select(cookie => cookie.Name + "=" + cookie.Value));

            DialogResult = true;
        }
        catch (Exception ex)
        {
            _statusText.Text =
                "Не удалось получить результат сессии проверки. Повторите попытку.";
            _continueButton.IsEnabled = true;
            DiagnosticLog.Write(
                "auth_verification_cookies",
                ex.GetType().Name + ": " + ex.Message);
        }
    }

    private void VerificationWindow_Closed(object? sender, EventArgs e)
    {
        _recaptchaProbeTimer.Stop();
        _recaptchaProbeTimer.Tick -= RecaptchaProbeTimer_Tick;

        if (_webView.CoreWebView2 is not null)
        {
            _webView.CoreWebView2.NavigationStarting -= CoreWebView2_NavigationStarting;
            _webView.CoreWebView2.NavigationCompleted -= CoreWebView2_NavigationCompleted;
            _webView.CoreWebView2.NewWindowRequested -= CoreWebView2_NewWindowRequested;
        }

        _webView.Dispose();
        _ = DeleteTemporaryProfileAsync(_profilePath);
    }

    private static async Task DeleteTemporaryProfileAsync(string path)
    {
        for (var attempt = 0; attempt < 8; attempt++)
        {
            try
            {
                if (Directory.Exists(path))
                    Directory.Delete(path, recursive: true);
                return;
            }
            catch
            {
                await Task.Delay(350);
            }
        }

        DiagnosticLog.Write(
            "auth_verification_cleanup",
            "temporary-profile-delete-deferred");
    }
}
