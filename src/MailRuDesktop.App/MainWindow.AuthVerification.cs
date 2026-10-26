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

    private async Task<(MailRuAuthResult Result, string Password)?> TryHandleInteractiveAuthAsync(
        string login,
        string password,
        MailRuAuthResult result)
    {
        WriteAuthDiagnostics(result, login);

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
                "В ответе нет страницы продолжения, которую можно открыть внутри приложения. " +
                "Обезличенная структура ответа сохранена в диагностике.");
            return null;
        }

        var localSessionId = result.Challenge?.SessionId;
        var isOfficialSecondStep =
            string.Equals(
                result.DiagnosticReason,
                "aj_mobile_auth_continue_required",
                StringComparison.Ordinal) &&
            !string.IsNullOrWhiteSpace(localSessionId);

        if (string.Equals(
                result.DiagnosticReason,
                "aj_mobile_auth_continue_required",
                StringComparison.Ordinal) &&
            string.IsNullOrWhiteSpace(localSessionId))
        {
            AppDialog.Info(
                this,
                "Проверка Mail.ru",
                "Mail.ru запросил второй шаг авторизации, но локальная сессия первого запроса не сохранена. " +
                "Авторизация остановлена, чтобы не запускать несвязанную повторную попытку.");
            return null;
        }

        var initialCookies = !string.IsNullOrWhiteSpace(localSessionId)
            ? _mailRu.GetChallengeBrowserCookies(localSessionId)
            : Array.Empty<MailRuAuthBrowserCookie>();

        var verificationWindow = new MailRuVerificationWindow(
            url,
            login,
            officialSecondStep: isOfficialSecondStep,
            initialCookies: initialCookies)
        {
            Owner = this
        };

        var verified = verificationWindow.ShowDialog() == true;
        if (!verified)
            return null;

        AuthStatusText.Text =
            "Продолжение исходной авторизации после проверки Mail.ru...";

        var passwordForClient = password;
        if (verificationWindow.MailboxReached)
        {
            // The browser has completed recovery, but client access is NOT
            // confirmed yet. Use the password observed during the recovery
            // confirmation, if it was changed, without opening another dialog.
            // Verification of the newly issued client token happens upstream.
            if (!string.IsNullOrEmpty(verificationWindow.RecoveredPassword))
                passwordForClient = verificationWindow.RecoveredPassword;
            DiagnosticLog.Write("auth_recovery",
                string.IsNullOrEmpty(verificationWindow.RecoveredPassword)
                    ? "mailbox-browser-stage-completed; original-password-retry"
                    : "mailbox-browser-stage-completed; updated-password-retry", login);
        }

        MailRuAuthResult retry;
        if (isOfficialSecondStep)
        {
            retry = await _mailRu.CompleteSecondStepAsync(
                login, localSessionId!, verificationWindow.TsaCookie,
                verificationWindow.AdditionalParams, passwordForClient,
                verificationWindow.SessionCookieHeader);
        }
        else
        {
            retry = await _mailRu.AuthenticateWithSessionCookiesAsync(
                login, passwordForClient, verificationWindow.SessionCookieHeader);
        }

        WriteAuthDiagnostics(retry, login);

        if (!retry.Success && IsInteractiveAuthState(retry))
        {
            AppDialog.Info(
                this,
                "Проверка Mail.ru не завершена",
                isOfficialSecondStep
                    ? "Mail.ru снова запросил дополнительную проверку после штатного второго шага. Диагностика обновлена."
                    : "Mail.ru снова запросил дополнительную проверку. Диагностика обновлена.");
        }

        return (retry, passwordForClient);
    }

    private void WriteAuthDiagnostics(MailRuAuthResult result, string? login = null)
    {
        var lines = new List<string>();

        if (!string.IsNullOrWhiteSpace(result.DiagnosticReason))
            lines.Add(result.DiagnosticReason);

        if (!string.IsNullOrWhiteSpace(result.DiagnosticDetails))
            lines.Add(result.DiagnosticDetails);

        if (lines.Count == 0)
            return;

        ResponseTextBox.Text = string.Join(Environment.NewLine, lines);
        DiagnosticLog.Write("auth_failure", string.Join(" | ", lines), login);
    }
}
