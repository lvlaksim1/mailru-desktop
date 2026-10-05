using System.IO;
using System.Text.Json;
using System.Windows;
using System.Windows.Media.Imaging;
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
            _ => "Дополнительная проверка Mail.ru"
        };

        if (challenge.Kind == MailRuChallengeKind.ReCaptcha)
        {
            HintText.Text =
                "Пройдите reCAPTCHA вручную. MailRu Desktop перехватит ответ сразу после решения " +
                "и передаст его в тот же CreateSession(token), как в Hackus.";
            ContinueButton.Visibility = Visibility.Collapsed;
            ImageCaptchaPanel.Visibility = Visibility.Collapsed;
            Browser.Visibility = Visibility.Visible;
        }
        else
        {
            HintText.Text =
                "Введите символы с изображения. Ответ будет отправлен в user/copper, " +
                "после чего продолжится CreateSessionByLink — как в Hackus.";
            ContinueButton.Visibility = Visibility.Visible;
            ImageCaptchaPanel.Visibility = Visibility.Visible;
            Browser.Visibility = Visibility.Collapsed;
            LoadCaptchaImage();
        }

        Loaded += ChallengeWindow_Loaded;
        Closed += ChallengeWindow_Closed;
    }

    private async void ChallengeWindow_Loaded(object sender, RoutedEventArgs e)
    {
        if (_challenge.Kind == MailRuChallengeKind.Captcha)
        {
            CaptchaAnswerTextBox.Focus();
            StatusText.Text = "Введите CAPTCHA и нажмите «Отправить».";
            return;
        }

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

            Browser.CoreWebView2.WebMessageReceived += Browser_WebMessageReceived;
            await Browser.CoreWebView2.AddScriptToExecuteOnDocumentCreatedAsync(
                "(() => {" +
                " let sent = false;" +
                " const get = () => {" +
                "  let v = '';" +
                "  const a = document.querySelector('[name=\\\"g-recaptcha-response\\\"]');" +
                "  if (a && a.value) v = a.value;" +
                "  try { if (!v && typeof grecaptcha !== 'undefined' && grecaptcha.getResponse) v = grecaptcha.getResponse(); } catch(e) {}" +
                "  return v;" +
                " };" +
                " const send = () => {" +
                "  if (sent) return true;" +
                "  const v = get();" +
                "  if (!v) return false;" +
                "  sent = true;" +
                "  chrome.webview.postMessage({ type: 'recaptcha', value: v });" +
                "  return true;" +
                " };" +
                " document.addEventListener('submit', e => { if (send()) { e.preventDefault(); e.stopImmediatePropagation(); } }, true);" +
                " const nativeSubmit = HTMLFormElement.prototype.submit;" +
                " HTMLFormElement.prototype.submit = function() { if (send()) return; return nativeSubmit.apply(this, arguments); };" +
                " const nativeRequestSubmit = HTMLFormElement.prototype.requestSubmit;" +
                " if (nativeRequestSubmit) HTMLFormElement.prototype.requestSubmit = function() { if (send()) return; return nativeRequestSubmit.apply(this, arguments); };" +
                " new MutationObserver(send).observe(document.documentElement, {subtree:true, childList:true, attributes:true, characterData:true});" +
                " setInterval(send, 25);" +
                "})();");

            Browser.NavigationCompleted += Browser_NavigationCompleted;
            Browser.Source = new Uri(_challenge.Url);
            StatusText.Text = "Пройдите reCAPTCHA. Продолжение произойдёт автоматически.";
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось открыть reCAPTCHA: " + ex.Message;
        }
    }

    private void LoadCaptchaImage()
    {
        if (string.IsNullOrWhiteSpace(_challenge.CaptchaImageBase64))
            return;

        try
        {
            var bytes = Convert.FromBase64String(_challenge.CaptchaImageBase64);
            using var stream = new MemoryStream(bytes);

            var image = new BitmapImage();
            image.BeginInit();
            image.CacheOption = BitmapCacheOption.OnLoad;
            image.StreamSource = stream;
            image.EndInit();
            image.Freeze();

            CaptchaImage.Source = image;
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось показать CAPTCHA: " + ex.Message;
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

            foreach (var domain in new[] { challengeHost, ".mail.ru" }
                         .Distinct(StringComparer.OrdinalIgnoreCase))
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
        if (!e.IsSuccess || _completing)
            return;

        StatusText.Text = "Ожидание ответа reCAPTCHA...";
    }

    private void Browser_WebMessageReceived(
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
            StatusText.Text = "reCAPTCHA получена. Продолжаю исходную HTTP-сессию...";

            Completion = CreateCompletion(token);
            DialogResult = true;
            Close();
        }
        catch
        {
        }
    }

    private void ContinueButton_Click(object sender, RoutedEventArgs e)
    {
        if (_challenge.Kind != MailRuChallengeKind.Captcha || _completing)
            return;

        var answer = CaptchaAnswerTextBox.Text.Trim();
        if (answer.Length == 0)
        {
            StatusText.Text = "Введите ответ CAPTCHA.";
            CaptchaAnswerTextBox.Focus();
            return;
        }

        _completing = true;
        Completion = CreateCompletion(answer);
        DialogResult = true;
        Close();
    }

    private MailRuChallengeCompletion CreateCompletion(string answer) =>
        new(
            _challenge.SessionId,
            string.Empty,
            string.Empty,
            string.Empty,
            string.Empty,
            string.Empty,
            string.Empty,
            _challenge.Kind == MailRuChallengeKind.ReCaptcha ? answer : null,
            answer);

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
