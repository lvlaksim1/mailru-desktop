using System.IO;
using System.Windows;
using System.Windows.Controls;
using Microsoft.Web.WebView2.Core;
using Microsoft.Web.WebView2.Wpf;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

internal sealed class MailRuVerificationWindow : Window
{
    private readonly Uri _startUri;
    private readonly string _profilePath;
    private readonly bool _officialSecondStep;
    private readonly IReadOnlyList<MailRuAuthBrowserCookie> _initialCookies;
    private readonly WebView2 _webView = new();
    private readonly TextBlock _statusText = new();
    private readonly Button _continueButton = new();
    private bool _terminalResultInProgress;

    public string? SessionCookieHeader { get; private set; }
    public string? TsaCookie { get; private set; }
    public IReadOnlyDictionary<string, string> AdditionalParams { get; private set; } =
        new Dictionary<string, string>(StringComparer.Ordinal);

    public MailRuVerificationWindow(
        string url,
        string login,
        bool officialSecondStep = false,
        IReadOnlyList<MailRuAuthBrowserCookie>? initialCookies = null)
    {
        if (!Uri.TryCreate(url, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps)
        {
            throw new ArgumentException(
                "Verification URL must use HTTPS.",
                nameof(url));
        }

        _startUri = uri;
        _officialSecondStep = officialSecondStep;
        _initialCookies = initialCookies ?? Array.Empty<MailRuAuthBrowserCookie>();
        _profilePath = MailRuAuthProfileStore.GetProfilePath(login);

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

        _statusText.Text = _officialSecondStep
            ? "Пройдите проверку на странице Mail.ru и нажмите штатную кнопку «Войти». " +
              "После подтверждения окно закроется автоматически."
            : "Окно использует отдельный профиль этого почтового аккаунта и не связано с браузерами на компьютере.";
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
        _continueButton.Visibility = _officialSecondStep
            ? Visibility.Collapsed
            : Visibility.Visible;
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

            DiagnosticLog.Write(
                "auth_verification_profile",
                "persistent-per-account-profile=1");

            var environment = await CoreWebView2Environment.CreateAsync(
                browserExecutableFolder: null,
                userDataFolder: _profilePath);

            await _webView.EnsureCoreWebView2Async(environment);

            var existingCookies = await _webView.CoreWebView2.CookieManager
                .GetCookiesAsync(_startUri.ToString());

            DiagnosticLog.Write(
                "auth_verification_profile_state",
                $"cookies={existingCookies.Count}; " +
                $"tsa={(existingCookies.Any(cookie => cookie.Name.Equals("tsa", StringComparison.OrdinalIgnoreCase)) ? "present" : "absent")}; " +
                $"garage={(existingCookies.Any(cookie => cookie.Name.Equals("GarageID", StringComparison.OrdinalIgnoreCase)) ? "present" : "absent")}");

            _webView.CoreWebView2.Settings.AreDevToolsEnabled = false;
            _webView.CoreWebView2.Settings.AreDefaultContextMenusEnabled = false;
            _webView.CoreWebView2.Settings.IsPasswordAutosaveEnabled = false;
            _webView.CoreWebView2.Settings.IsGeneralAutofillEnabled = false;

            SeedInitialCookies(_webView.CoreWebView2.CookieManager);

            _webView.CoreWebView2.NavigationStarting += CoreWebView2_NavigationStarting;
            _webView.CoreWebView2.NavigationCompleted += CoreWebView2_NavigationCompleted;
            _webView.CoreWebView2.NewWindowRequested += CoreWebView2_NewWindowRequested;

            _webView.Source = _startUri;
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

    private void SeedInitialCookies(CoreWebView2CookieManager cookieManager)
    {
        var applied = 0;
        var rejected = 0;

        foreach (var source in _initialCookies)
        {
            try
            {
                if (string.IsNullOrWhiteSpace(source.Name) ||
                    string.IsNullOrWhiteSpace(source.Domain))
                {
                    rejected++;
                    continue;
                }

                var cookie = cookieManager.CreateCookie(
                    source.Name,
                    source.Value,
                    source.Domain,
                    string.IsNullOrWhiteSpace(source.Path) ? "/" : source.Path);

                cookie.IsSecure = source.IsSecure;
                cookie.IsHttpOnly = source.IsHttpOnly;
                cookieManager.AddOrUpdateCookie(cookie);
                applied++;
            }
            catch (Exception ex)
            {
                rejected++;
                DiagnosticLog.Write(
                    "auth_verification_cookie_seed",
                    ex.GetType().Name + ": " + ex.Message);
            }
        }

        DiagnosticLog.Write(
            "auth_verification_cookie_seed",
            $"available={_initialCookies.Count}; applied={applied}; rejected={rejected}");
    }

    private void CoreWebView2_NavigationStarting(
        object? sender,
        CoreWebView2NavigationStartingEventArgs e)
    {
        if (!Uri.TryCreate(e.Uri, UriKind.Absolute, out var uri))
        {
            e.Cancel = true;
            return;
        }

        if (_officialSecondStep && IsMobileAuthResult(uri))
        {
            e.Cancel = true;
            _ = HandleMobileAuthResultAsync(uri);
            return;
        }

        if (uri.Scheme.Equals("internal-api", StringComparison.OrdinalIgnoreCase))
        {
            e.Cancel = true;
            _statusText.Text =
                "Mail.ru переключил состояние проверки. Завершите доступные действия на странице либо отмените вход.";
            return;
        }

        if (uri.Scheme != Uri.UriSchemeHttps)
        {
            e.Cancel = true;
            _statusText.Text =
                "Небезопасный переход заблокирован. В окне проверки разрешены только HTTPS-страницы.";
            return;
        }

        _statusText.Text = _officialSecondStep
            ? "Завершите проверку на странице Mail.ru. Результат будет принят автоматически."
            : $"Проверка · {uri.Host}";
    }

    private void CoreWebView2_NewWindowRequested(
        object? sender,
        CoreWebView2NewWindowRequestedEventArgs e)
    {
        if (!Uri.TryCreate(e.Uri, UriKind.Absolute, out var uri))
        {
            e.Handled = true;
            return;
        }

        if (_officialSecondStep && IsMobileAuthResult(uri))
        {
            e.Handled = true;
            _ = HandleMobileAuthResultAsync(uri);
            return;
        }

        if (uri.Scheme != Uri.UriSchemeHttps)
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

        if (_officialSecondStep)
        {
            _statusText.Text =
                "Пройдите проверку и нажмите штатную кнопку «Войти» на странице Mail.ru.";
            return;
        }

        _continueButton.IsEnabled = true;
    }

    private async Task HandleMobileAuthResultAsync(Uri uri)
    {
        if (_terminalResultInProgress)
            return;

        _terminalResultInProgress = true;

        try
        {
            var path = uri.AbsolutePath;
            if (path.Equals("/success", StringComparison.OrdinalIgnoreCase))
            {
                AdditionalParams = ReadQueryParams(uri);
                TsaCookie = await ReadTsaCookieAsync();

                DiagnosticLog.Write(
                    "auth_second_step_success",
                    $"params={AdditionalParams.Count}; tsa={(string.IsNullOrWhiteSpace(TsaCookie) ? "absent" : "present")}");

                DialogResult = true;
                return;
            }

            if (path.Equals("/fail", StringComparison.OrdinalIgnoreCase))
            {
                _statusText.Text =
                    "Mail.ru отклонил дополнительную проверку. Можно повторить действия на странице либо отменить вход.";
                return;
            }

            if (path.Equals("/error", StringComparison.OrdinalIgnoreCase))
            {
                _statusText.Text =
                    "Mail.ru сообщил об ошибке дополнительной проверки.";
                return;
            }
        }
        catch (Exception ex)
        {
            _statusText.Text =
                "Не удалось обработать результат дополнительной проверки.";
            DiagnosticLog.Write(
                "auth_second_step_result",
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            _terminalResultInProgress = false;
        }
    }

    private async Task<string?> ReadTsaCookieAsync()
    {
        if (_webView.CoreWebView2 is null)
            return null;

        var cookies = await _webView.CoreWebView2.CookieManager
            .GetCookiesAsync(_startUri.ToString());

        return cookies
            .FirstOrDefault(cookie =>
                cookie.Name.Equals("tsa", StringComparison.OrdinalIgnoreCase))
            ?.Value;
    }

    private static IReadOnlyDictionary<string, string> ReadQueryParams(Uri uri)
    {
        var result = new Dictionary<string, string>(StringComparer.Ordinal);

        var query = uri.Query.TrimStart('?');
        if (query.Length == 0)
            return result;

        foreach (var item in query.Split('&', StringSplitOptions.RemoveEmptyEntries))
        {
            var parts = item.Split('=', 2);
            if (parts.Length == 0)
                continue;

            var name = DecodeQueryPart(parts[0]);
            if (string.IsNullOrWhiteSpace(name))
                continue;

            var value = parts.Length == 2
                ? DecodeQueryPart(parts[1])
                : string.Empty;

            result[name] = value;
        }

        return result;
    }

    private static string DecodeQueryPart(string value) =>
        Uri.UnescapeDataString(value.Replace("+", " ", StringComparison.Ordinal));

    private static bool IsMobileAuthResult(Uri uri) =>
        uri.Host.Equals("mobile-auth", StringComparison.OrdinalIgnoreCase) &&
        (uri.AbsolutePath.Equals("/success", StringComparison.OrdinalIgnoreCase) ||
         uri.AbsolutePath.Equals("/fail", StringComparison.OrdinalIgnoreCase) ||
         uri.AbsolutePath.Equals("/error", StringComparison.OrdinalIgnoreCase));

    private async void ContinueButton_Click(
        object sender,
        RoutedEventArgs e)
    {
        if (_webView.CoreWebView2 is null || _officialSecondStep)
            return;

        _continueButton.IsEnabled = false;
        try
        {
            var cookies = await _webView.CoreWebView2.CookieManager
                .GetCookiesAsync(_startUri.ToString());

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
        if (_webView.CoreWebView2 is not null)
        {
            _webView.CoreWebView2.NavigationStarting -= CoreWebView2_NavigationStarting;
            _webView.CoreWebView2.NavigationCompleted -= CoreWebView2_NavigationCompleted;
            _webView.CoreWebView2.NewWindowRequested -= CoreWebView2_NewWindowRequested;
        }

        _webView.Dispose();
    }
}
