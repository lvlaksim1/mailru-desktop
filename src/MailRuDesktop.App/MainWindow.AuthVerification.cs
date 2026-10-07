using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private static bool IsInteractiveAuthState(MailRuAuthResult result) =>
        result.State is
            MailRuAuthState.Captcha or
            MailRuAuthState.ReCaptcha or
            MailRuAuthState.TwoFactor or
            MailRuAuthState.RecoveryRequired;

    private async Task<MailRuAuthResult?> TryHandleInteractiveAuthAsync(
        string login,
        string password,
        MailRuAuthResult result)
    {
        WriteAuthDiagnostics(result);

        var verificationName = result.State switch
        {
            MailRuAuthState.ReCaptcha => "reCAPTCHA",
            MailRuAuthState.Captcha => "CAPTCHA",
            MailRuAuthState.TwoFactor => "двухфакторную проверку",
            _ => "дополнительную проверку"
        };

        var url = result.Challenge?.Url;
        if (string.IsNullOrWhiteSpace(url))
        {
            AppDialog.Info(
                this,
                "Дополнительная проверка Mail.ru",
                $"Mail.ru запросил {verificationName}.\n\n" +
                "В ответе нет безопасной страницы Mail.ru, которую можно открыть внутри приложения. " +
                "Обезличенная структура ответа сохранена в диагностике.");
            return null;
        }

        var verificationWindow = new MailRuVerificationWindow(url)
        {
            Owner = this
        };

        var verified = verificationWindow.ShowDialog() == true;
        if (!verified)
            return null;

        AuthStatusText.Text = "Повторная авторизация после проверки Mail.ru...";

        var retry = await _mailRu.AuthenticateWithSessionCookiesAsync(
            login,
            password,
            verificationWindow.SessionCookieHeader);

        WriteAuthDiagnostics(retry);

        if (!retry.Success && IsInteractiveAuthState(retry))
        {
            AppDialog.Info(
                this,
                "Проверка Mail.ru не завершена",
                "Mail.ru снова запросил дополнительную проверку. " +
                "Это означает, что одной браузерной сессии недостаточно либо проверка не была подтверждена сервером. " +
                "Диагностика обновлена; автоматический цикл повторных CAPTCHA не запускается.");
        }

        return retry;
    }

    private void WriteAuthDiagnostics(MailRuAuthResult result)
    {
        var lines = new List<string>();

        if (!string.IsNullOrWhiteSpace(result.DiagnosticReason))
            lines.Add(result.DiagnosticReason);

        if (!string.IsNullOrWhiteSpace(result.DiagnosticDetails))
            lines.Add(result.DiagnosticDetails);

        if (lines.Count == 0)
            return;

        ResponseTextBox.Text = string.Join(Environment.NewLine, lines);
        DiagnosticLog.Write("auth_failure", string.Join(" | ", lines));
    }
}
