using System.IO;
using System.Text.Json;
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

        HintText.Text = challenge.Kind == MailRuChallengeKind.ReCaptcha
            ? "Пройдите reCAPTCHA вручную. Ответ будет перехвачен сразу после решения и " +
              "передан в тот же CreateSession(token), как в Hackus."
            : "Завершите проверку Mail.ru в этом окне.";

        ContinueButton.Visibility = challenge.Kind == MailRuChallengeKind.ReCaptcha
            ? Visibility.Collapsed
            : Visibility.Visible;

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

            if (_challenge.Kind == MailRuChallengeKind.ReCaptcha)
            {
                Browser.CoreWebView2.WebMessageReceived += Browser_WebMessageReceived;
                await Browser.CoreWebView2.AddScriptToExecuteOnDocumentCreatedAsync(
                    "(() => {" +
                    " let sent = false;" +
                    " const send = () => {" +
                    "  if (sent) return;" +
                    "  let v = '';" +
                    "  const a = document.querySelector('[name=\\\"g-recaptcha-response\\\"]');" +
                    "  if (a && a.value) v = a.value;" +
                    "  try { if (!v && typeof grecaptcha !== 'undefined' && grecaptcha.getResponse) v = grecaptcha.getResponse(); } catch(e) {}" +
                    "  if (v) { sent = true; chrome.webview.postMessage({ type: 'recaptcha', value: v }); }" +
                    " };" +
                    " new MutationObserver(send).observe(document.documentElement, {subtree:true, childList:true, attributes:true});" +
                    " document.addEventListener('change', send, true);" +
                    " document.addEventListener('submit', send, true);" +
                    " setInterval(send, 200);" +
                    "})();");
            }

            Browser.NavigationCompleted += Browser_NavigationCompleted;
            Browser.Source = new Uri(_challenge.Url);
            StatusText.Text = _challenge.Kind == MailRuChallengeKind.ReCaptcha
                ? "Пройдите reCAPTCHA. Ответ будет передан автоматически."
                : "Пройдите проверку Mail.ru.";
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

            foreach (var domain in new[]
                     {
                         challengeHost,
                         ".mail.ru",
                         "account.mail.ru",
                         "e.mail.ru",
                         "touch.mail.ru",
                         "aj-https.mail.ru"
                     }.Distinct(StringComparer.OrdinalIgnoreCase))
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
                }
            }
        }
    }

    private void Browser_NavigationCompleted(
        object? sender,
        CoreWebView2NavigationCompletedEventArgs e)
    {
        if (!e.IsSuccess || Browser.Source is null || _completing)
            return;

        StatusText.Text = _challenge.Kind == MailRuChallengeKind.ReCaptcha
            ? "Ожидание ответа reCAPTCHA..."
            : "Проверка Mail.ru выполняется...";
    }

    private async void Browser_WebMessageReceived(
        object? sender,
        CoreWebView2WebMessageReceivedEventArgs e)
    {
        if (_completing || _challenge.Kind != MailRuChallengeKind.ReCaptcha)
            return;

        try
        {
            using var document = JsonDocument.Parse(e.WebMessageAsJson);
            var root = document.RootElement;

            if (!root.TryGetProperty("type", out var type) ||
                !string.Equals(type.GetString(), "recaptcha", StringComparison.Ordinal) ||
                !root.TryGetProperty("value", out var value))
            {
                return;
            }

            var token = value.GetString();
            if (string.IsNullOrWhiteSpace(token))
                return;

            _completing = true;
            StatusText.Text = "reCAPTCHA получена. Продолжаю исходную сессию...";

            Completion = new MailRuChallengeCompletion(
                _challenge.SessionId,
                string.Empty,
                string.Empty,
                string.Empty,
                string.Empty,
                string.Empty,
                Browser.Source?.ToString() ?? _challenge.Url,
                token,
                token);

            DialogResult = true;
            Close();
        }
        catch
        {
        }
    }

    private async void ContinueButton_Click(object sender, RoutedEventArgs e)
    {
        if (_challenge.Kind == MailRuChallengeKind.ReCaptcha)
        {
            StatusText.Text = "Завершите reCAPTCHA — продолжение произойдёт автоматически.";
            return;
        }

        await CompleteAsync();
    }

    private async Task CompleteAsync()
    {
        if (_completing || Browser.CoreWebView2 is null)
            return;

        _completing = true;
        StatusText.Text = "Возвращаю результат проверки в исходную сессию...";

        try
        {
            var finalUrl = Browser.Source?.ToString() ?? _challenge.Url;
            var reachedMailbox =
                Uri.TryCreate(finalUrl, UriKind.Absolute, out var current) &&
                IsAuthenticatedMailboxUri(current);

            var reCaptchaResponse = await ReadReCaptchaResponseAsync();

            if (_challenge.Kind == MailRuChallengeKind.ReCaptcha &&
                string.IsNullOrWhiteSpace(reCaptchaResponse) &&
                !reachedMailbox)
            {
                StatusText.Text =
                    "Ответ reCAPTCHA ещё не получен. Завершите проверку и нажмите «Продолжить».";
                _completing = false;
                return;
            }

            var accountHeader = await BuildCookieHeaderAsync("https://account.mail.ru/");
            var mailHeader = await BuildCookieHeaderAsync("https://mail.ru/");
            var webHeader = await BuildCookieHeaderAsync("https://e.mail.ru/");
            var touchHeader = await BuildCookieHeaderAsync("https://touch.mail.ru/");
            var ajHeader = await BuildCookieHeaderAsync("https://aj-https.mail.ru/");

            if (string.IsNullOrWhiteSpace(accountHeader) &&
                string.IsNullOrWhiteSpace(mailHeader) &&
                string.IsNullOrWhiteSpace(webHeader) &&
                string.IsNullOrWhiteSpace(touchHeader) &&
                string.IsNullOrWhiteSpace(ajHeader))
            {
                StatusText.Text =
                    "Сессионные cookies ещё не получены. Завершите проверку Mail.ru.";
                _completing = false;
                return;
            }

            Completion = new MailRuChallengeCompletion(
                _challenge.SessionId,
                accountHeader,
                mailHeader,
                webHeader,
                touchHeader,
                ajHeader,
                finalUrl,
                reCaptchaResponse);

            DialogResult = true;
            Close();
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось получить результат проверки: " + ex.Message;
            _completing = false;
        }
    }

    private static bool IsAuthenticatedMailboxUri(Uri uri)
    {
        if (uri.AbsolutePath.Contains("login", StringComparison.OrdinalIgnoreCase) ||
            uri.AbsolutePath.Contains("auth", StringComparison.OrdinalIgnoreCase))
        {
            return false;
        }

        return uri.Host.Equals("e.mail.ru", StringComparison.OrdinalIgnoreCase) ||
               uri.Host.Equals("touch.mail.ru", StringComparison.OrdinalIgnoreCase) ||
               uri.Host.Equals("m.mail.ru", StringComparison.OrdinalIgnoreCase);
    }

    private async Task<string?> ReadReCaptchaResponseAsync()
    {
        if (Browser.CoreWebView2 is null)
            return null;

        const string script =
            "(() => {" +
            " const a = document.querySelector('[name=\"g-recaptcha-response\"]');" +
            " if (a && a.value) return a.value;" +
            " try { if (typeof grecaptcha !== 'undefined' && grecaptcha.getResponse) return grecaptcha.getResponse(); } catch(e) {}" +
            " return '';" +
            "})()";

        try
        {
            var json = await Browser.CoreWebView2.ExecuteScriptAsync(script);
            return JsonSerializer.Deserialize<string>(json);
        }
        catch
        {
            return null;
        }
    }

    private async Task<string> BuildCookieHeaderAsync(string uri)
    {
        var cookies = await Browser.CoreWebView2.CookieManager.GetCookiesAsync(uri);

        return string.Join(
            "; ",
            cookies
                .Where(cookie => !string.IsNullOrWhiteSpace(cookie.Name))
                .GroupBy(
                    cookie => cookie.Name + "\n" + cookie.Domain + "\n" + cookie.Path,
                    StringComparer.OrdinalIgnoreCase)
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

        try
        {
            if (Directory.Exists(_profilePath))
                Directory.Delete(_profilePath, recursive: true);
        }
        catch
        {
        }
    }
}
