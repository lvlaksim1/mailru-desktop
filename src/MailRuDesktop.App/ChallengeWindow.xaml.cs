using System.IO;
using System.Net;
using System.Text.Json;
using System.Windows;
using System.Windows.Media.Imaging;
using MailRuDesktop.Protocol;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

public partial class ChallengeWindow : Window
{
    private const string SolverHost = "account.mail.ru";
    private readonly MailRuAuthChallenge _challenge;
    private readonly string _profilePath;
    private readonly string _solverPath;
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
        _solverPath = Path.Combine(_profilePath, "solver");

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
                "Ручная замена solver из Hackus: решите reCAPTCHA. Полученный token будет " +
                "передан прямо в исходный CreateSession(token); страница входа Mail.ru здесь не выполняется.";
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
            if (string.IsNullOrWhiteSpace(_challenge.SiteKey))
                throw new InvalidOperationException("Mail.ru не передал recaptchaSitekey.");

            Directory.CreateDirectory(_profilePath);
            Directory.CreateDirectory(_solverPath);

            var htmlPath = Path.Combine(_solverPath, "manual-recaptcha.html");
            await File.WriteAllTextAsync(
                htmlPath,
                BuildManualReCaptchaHtml(_challenge.SiteKey));

            var environment = await CoreWebView2Environment.CreateAsync(
                browserExecutableFolder: null,
                userDataFolder: _profilePath);

            await Browser.EnsureCoreWebView2Async(environment);

            Browser.CoreWebView2.Settings.UserAgent = MailRuFixedProfile.UserAgent;
            Browser.CoreWebView2.Settings.AreDevToolsEnabled = false;
            Browser.CoreWebView2.Settings.AreDefaultContextMenusEnabled = false;
            Browser.CoreWebView2.Settings.IsStatusBarEnabled = false;
            Browser.CoreWebView2.WebMessageReceived += Browser_WebMessageReceived;

            // Hackus asks the external solver for a token for page URL
            // https://account.mail.ru.  The manual solver gets the same secure
            // origin without loading Mail.ru's own login/challenge JavaScript,
            // so the token cannot be consumed before CreateSession(token).
            Browser.CoreWebView2.SetVirtualHostNameToFolderMapping(
                SolverHost,
                _solverPath,
                CoreWebView2HostResourceAccessKind.Allow);

            Browser.NavigationCompleted += Browser_NavigationCompleted;
            Browser.Source = new Uri($"https://{SolverHost}/manual-recaptcha.html");
            StatusText.Text = "Ожидание решения reCAPTCHA...";
        }
        catch (Exception ex)
        {
            StatusText.Text = "Не удалось открыть ручной solver reCAPTCHA: " + ex.Message;
        }
    }

    private static string BuildManualReCaptchaHtml(string siteKey)
    {
        var encodedKey = WebUtility.HtmlEncode(siteKey);
        var dark = ThemeManager.IsDarkEffective;
        var background = dark ? "#1E1E1E" : "#FFFFFF";
        var foreground = dark ? "#F2F2F2" : "#202124";
        var captchaTheme = dark ? "dark" : "light";

        return
            "<!doctype html><html><head><meta charset='utf-8'>" +
            "<meta name='viewport' content='width=device-width,initial-scale=1'>" +
            $"<style>html,body{{height:100%;margin:0;background:{background};color:{foreground};" +
            "font-family:'Segoe UI',Arial,sans-serif}.wrap{height:100%;display:flex;" +
            "align-items:center;justify-content:center;flex-direction:column;gap:18px}" +
            ".hint{font-size:15px;text-align:center;max-width:560px}</style>" +
            "<script>function solved(token){chrome.webview.postMessage({type:'recaptcha',value:token});}" +
            "function expired(){chrome.webview.postMessage({type:'expired'});}</script>" +
            "<script src='https://www.google.com/recaptcha/api.js?hl=ru' async defer></script>" +
            "</head><body><div class='wrap'>" +
            "<div class='hint'>Пройдите проверку. После успешного решения окно закроется автоматически.</div>" +
            $"<div class='g-recaptcha' data-sitekey='{encodedKey}' data-theme='{captchaTheme}' " +
            "data-callback='solved' data-expired-callback='expired'></div>" +
            "</div></body></html>";
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

    private void Browser_NavigationCompleted(
        object? sender,
        CoreWebView2NavigationCompletedEventArgs e)
    {
        if (_completing)
            return;

        StatusText.Text = e.IsSuccess
            ? "Решите reCAPTCHA. Token будет передан автоматически."
            : "Не удалось загрузить reCAPTCHA.";
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

            if (!root.TryGetProperty("type", out var type))
                return;

            if (string.Equals(type.GetString(), "expired", StringComparison.Ordinal))
            {
                StatusText.Text = "reCAPTCHA истекла. Пройдите её ещё раз.";
                return;
            }

            if (!string.Equals(type.GetString(), "recaptcha", StringComparison.Ordinal) ||
                !root.TryGetProperty("value", out var value))
            {
                return;
            }

            var token = value.GetString();
            if (string.IsNullOrWhiteSpace(token))
                return;

            _completing = true;
            StatusText.Text = "reCAPTCHA получена. Выполняю CreateSession(token)...";

            Completion = CreateCompletion(token);
            DialogResult = true;
            Close();
        }
        catch (Exception ex)
        {
            StatusText.Text = "Ошибка получения reCAPTCHA token: " + ex.Message;
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
        new(_challenge.SessionId, answer);

    private void ChallengeWindow_Closed(object? sender, EventArgs e)
    {
        try
        {
            if (Browser.CoreWebView2 is not null)
                Browser.CoreWebView2.ClearVirtualHostNameToFolderMapping(SolverHost);
        }
        catch
        {
        }

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
