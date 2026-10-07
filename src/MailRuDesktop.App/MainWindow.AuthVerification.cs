using System.Diagnostics;
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
                "Приложение не обходит проверку автоматически. " +
                "В ответе сервера нет безопасной ссылки, которую можно открыть пользователю. " +
                "Обезличенная структура ответа сохранена в разделе диагностики.");
            return null;
        }

        if (!AppDialog.Confirm(
                this,
                "Дополнительная проверка Mail.ru",
                $"Mail.ru запросил {verificationName}.\n\n" +
                "Открыть штатную страницу Mail.ru для прохождения проверки? " +
                "После завершения вернитесь в приложение.",
                "Открыть Mail.ru",
                "Отмена"))
        {
            return null;
        }

        try
        {
            Process.Start(new ProcessStartInfo(url)
            {
                UseShellExecute = true
            });
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write(
                "auth_verification_open",
                ex.GetType().Name + ": " + ex.Message);
            AppDialog.Info(
                this,
                "Дополнительная проверка Mail.ru",
                "Не удалось открыть страницу проверки Mail.ru.");
            return null;
        }

        if (!AppDialog.Confirm(
                this,
                "Повторить вход",
                "Завершите проверку на странице Mail.ru, затем нажмите «Повторить вход». " +
                "Если проверка ещё не завершена, выберите «Позже».",
                "Повторить вход",
                "Позже"))
        {
            return null;
        }

        var retry = await _mailRu.AuthenticateAsync(login, password);
        WriteAuthDiagnostics(retry);
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
