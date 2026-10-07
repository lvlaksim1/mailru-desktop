namespace MailRuDesktop.Protocol;

public static class MailRuFixedProfile
{
    public const string UserAgent =
        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) " +
        "AppleWebKit/604.1.34 (KHTML, like Gecko) GSA/50.0.197507736 " +
        "Mobile/17D50 Safari/604.1";

    public const string MobileUserAgent =
        "mobmail android 11.13.0.29089 ru.mail.mailapp";
}

public enum MailRuAuthState
{
    Success,
    InvalidCredentials,
    ReCaptcha,
    Captcha,
    TwoFactor,
    Blocked,
    RecoveryRequired,
    NetworkError,
    ProtocolError,
    Unknown
}

public enum MailRuChallengeKind
{
    ReCaptcha,
    Captcha,
    TwoFactor,
    AdditionalVerification
}

public sealed record MailRuAuthChallenge(
    MailRuChallengeKind Kind,
    string SessionId,
    string? Url,
    string? SiteKey,
    string? CaptchaImageBase64,
    string? DiagnosticReason);

public sealed record MailRuChallengeCompletion(
    string SessionId,
    string Answer);

public sealed record MailRuAuthBrowserCookie(
    string Name,
    string Value,
    string Domain,
    string Path,
    bool IsSecure,
    bool IsHttpOnly);
