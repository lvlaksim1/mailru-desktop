using System.IO;
using System.Windows;
using MailRuDesktop.Protocol;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

public partial class ChallengeWindow : Window
{
    private readonly MailRuAuthChallenge _challenge;
    private readonly string _profilePath;
    private bool _completing;

    public MailRuChallengeCompletion? Completion { get; private set; }

    public ChallengeWindow(MailRuAuthChallenge challenge)
    {
        _challenge = challenge;
        _profilePath = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop",
            "WebView2Challenge",
            Guid.NewGuid().ToString("N"));
        InitializeComponent();

        TitleText.Text = challenge.Kind switch
        {
            MailRuChallengeKind.ReCaptcha => "Mail.ru требует reCAPTCHA",
            MailRuChallengeKind.Captcha => "Mail.ru требует CAPTCHA",
            MailRuChallengeKind.TwoFactor => "Mail.ru требует двухфакторную проверку",
            MailRuChallengeKind.InteractiveLogin => "Подтверждение входа Mail.ru",
            _ => "Дополнительная проверка Mail.ru"
        };

        HintText.Text = challenge.Kind == MailRuChallengeKind.InteractiveLogin
            ? "Завершите обычный вход Mail.ru в этом окне. Если Mail.ru запросит CAPTCHA или " +
              "двухфакторную проверку, пройдите её здесь. После успешного входа сессия будет сохранена."
            : "Пройдите проверку вручную в этом окне. MailRu Desktop не передаёт CAPTCHA " +
              "сторонним сервисам. После успешного входа сессия будет сохранена Windows DPAPI.";

        Loaded += ChallengeWindow_Loaded;
        Closed += ChallengeWindow_Closed;
    }

    private async void ChallengeWindow_Loaded(object sender, RoutedEventArgs e)
    {
        try
        {
            Directory.CreateDirectory(_profilePath);

            var environment = await CoreWebView2Environment.CreateAsync(
                browserExecutableFolder: null,
                userDataFolder: _profilePath);

            await Browser.EnsureCoreWebView2Async(environment);

            Browser.CoreWebView2.Settings.UserAgent = MailRuFixedProfile.UserAgent;
            Browser.CoreWebView2.Settings.AreDevToolsEnabled = false;
            Browser.CoreWebView2.Settings.AreDefaultContextMenusEnabled = true;
            Browser.CoreWebView2.Settings.IsStatusBarEnabled = true;

            SeedCookies();

            Browser.NavigationCompleted += Browser_NavigationCompleted;
            Browser.Source = new Uri(_challenge.Url);
            StatusText.Text = "Пройдите проверку Mail.ru.";
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось открыть встроенную проверку: " + ex.Message;
        }
    }

    private void SeedCookies()
    {
        if (Browser.CoreWebView2 is null ||
            string.IsNullOrWhiteSpace(_challenge.SeedCookieHeader))
        {
            return;
        }

        var challengeHost = Uri.TryCreate(_challenge.Url, UriKind.Absolute, out var uri)
            ? uri.Host
            : "account.mail.ru";

        foreach (var item in _challenge.SeedCookieHeader.Split(
                     ';',
                     StringSplitOptions.RemoveEmptyEntries | StringSplitOptions.TrimEntries))
        {
            var separator = item.IndexOf('=');
            if (separator <= 0)
                continue;

            var name = item[..separator].Trim();
            var value = item[(separator + 1)..].Trim();

            if (name.Length == 0)
                continue;

            foreach (var domain in new[] { challengeHost, ".mail.ru" }.Distinct(StringComparer.OrdinalIgnoreCase))
            {
                try
                {
                    var cookie = Browser.CoreWebView2.CookieManager.CreateCookie(
                        name,
                        value,
                        domain,
                        "/");
                    cookie.IsSecure = true;
                    Browser.CoreWebView2.CookieManager.AddOrUpdateCookie(cookie);
                }
                catch
                {
                    // A duplicate/host-specific cookie may be rejected for one
                    // domain but still be valid for the other.
                }
            }
        }
    }

    private async void Browser_NavigationCompleted(
        object? sender,
        CoreWebView2NavigationCompletedEventArgs e)
    {
        if (!e.IsSuccess || Browser.Source is null || _completing)
            return;

        var uri = Browser.Source;

        if (uri.Host.Equals("e.mail.ru", StringComparison.OrdinalIgnoreCase) &&
            !uri.AbsolutePath.Contains("login", StringComparison.OrdinalIgnoreCase))
        {
            await CompleteAsync();
            return;
        }

        StatusText.Text = uri.Host.Contains("mail.ru", StringComparison.OrdinalIgnoreCase)
            ? "Проверка Mail.ru выполняется..."
            : "Ожидание завершения проверки...";
    }

    private async void ContinueButton_Click(object sender, RoutedEventArgs e)
    {
        await CompleteAsync();
    }

    private async Task CompleteAsync()
    {
        if (_completing || Browser.CoreWebView2 is null)
            return;

        _completing = true;
        StatusText.Text = "Получение сессионных cookies...";

        try
        {
            var webHeader = await BuildCookieHeaderAsync("https://e.mail.ru/");
            var touchHeader = await BuildCookieHeaderAsync("https://touch.mail.ru/");

            if (string.IsNullOrWhiteSpace(webHeader) &&
                string.IsNullOrWhiteSpace(touchHeader))
            {
                StatusText.Text = "Сессионные cookies ещё не получены. Завершите проверку Mail.ru.";
                _completing = false;
                return;
            }

            Completion = new MailRuChallengeCompletion(webHeader, touchHeader);
            DialogResult = true;
            Close();
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось получить сессию: " + ex.Message;
            _completing = false;
        }
    }

    private async Task<string> BuildCookieHeaderAsync(string uri)
    {
        var cookies = await Browser.CoreWebView2.CookieManager.GetCookiesAsync(uri);

        return string.Join(
            "; ",
            cookies
                .Where(cookie => !string.IsNullOrWhiteSpace(cookie.Name))
                .GroupBy(cookie => cookie.Name, StringComparer.OrdinalIgnoreCase)
                .Select(group => group.First())
                .Select(cookie => $"{cookie.Name}={cookie.Value}"));
    }

    private void ChallengeWindow_Closed(object? sender, EventArgs e)
    {
        try
        {
            Browser.Dispose();
        }
        catch
        {
        }

        // The challenge browser is intentionally ephemeral. The durable
        // authorization lives in DPAPI-protected auth.json, not in WebView2.
        try
        {
            if (Directory.Exists(_profilePath))
                Directory.Delete(_profilePath, recursive: true);
        }
        catch
        {
            // WebView2 may release its profile a moment after window close.
            // The application uninstaller removes the parent data directory.
        }
    }

}
