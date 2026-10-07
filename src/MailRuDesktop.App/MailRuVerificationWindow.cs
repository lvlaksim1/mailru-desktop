using System.IO;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using Microsoft.Web.WebView2.Core;
using Microsoft.Web.WebView2.Wpf;

namespace MailRuDesktop.App;

internal sealed class MailRuVerificationWindow : Window
{
    private readonly Uri _startUri;
    private readonly string _profilePath;
    private readonly WebView2 _webView = new();
    private readonly TextBlock _statusText = new();
    private readonly Button _continueButton = new();

    public string? SessionCookieHeader { get; private set; }

    public MailRuVerificationWindow(string url)
    {
        if (!Uri.TryCreate(url, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps ||
            !IsMailRuHost(uri.Host))
        {
            throw new ArgumentException(
                "Verification URL must be an HTTPS Mail.ru URL.",
                nameof(url));
        }

        _startUri = uri;
        _profilePath = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop",
            "AuthSessions",
            Guid.NewGuid().ToString("N"));

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

        _statusText.Text =
            "Окно использует отдельное временное хранилище cookies и не связано с браузерами на компьютере.";
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

        _continueButton.Content = "Проверка завершена — продолжить";
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

            _webView.Source = _startUri;
        }
        catch (Exception ex)
        {
            _statusText.Text = "Не удалось открыть страницу проверки Mail.ru.";
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
            uri.Scheme != Uri.UriSchemeHttps ||
            !IsMailRuHost(uri.Host))
        {
            e.Cancel = true;
            _statusText.Text =
                "Переход за пределы Mail.ru заблокирован. Проверка остаётся в изолированном окне.";
            return;
        }

        _statusText.Text = $"Mail.ru · {uri.Host}";
    }

    private void CoreWebView2_NavigationCompleted(
        object? sender,
        CoreWebView2NavigationCompletedEventArgs e)
    {
        if (!e.IsSuccess)
        {
            _statusText.Text =
                $"Страница Mail.ru не загрузилась: {e.WebErrorStatus}.";
            return;
        }

        _continueButton.IsEnabled = true;

        var current = _webView.Source;
        if (current is not null &&
            !current.Query.Contains("captcha=1", StringComparison.OrdinalIgnoreCase) &&
            !current.Query.Contains("authcaptcha", StringComparison.OrdinalIgnoreCase))
        {
            _statusText.Text =
                "Страница проверки изменилась. Если Mail.ru сообщил об успешной проверке, нажмите «Продолжить».";
        }
        else
        {
            _statusText.Text =
                "Пройдите проверку Mail.ru в этом окне. После успешного завершения нажмите «Продолжить».";
        }
    }

    private async void ContinueButton_Click(
        object sender,
        RoutedEventArgs e)
    {
        if (_webView.CoreWebView2 is null)
            return;

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
        if (_webView.CoreWebView2 is not null)
        {
            _webView.CoreWebView2.NavigationStarting -= CoreWebView2_NavigationStarting;
            _webView.CoreWebView2.NavigationCompleted -= CoreWebView2_NavigationCompleted;
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

    private static bool IsMailRuHost(string host) =>
        host.Equals("mail.ru", StringComparison.OrdinalIgnoreCase) ||
        host.EndsWith(".mail.ru", StringComparison.OrdinalIgnoreCase);
}
