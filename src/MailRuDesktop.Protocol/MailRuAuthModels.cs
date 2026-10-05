namespace MailRuDesktop.Protocol;

public static class MailRuFixedProfile
{
    public const string UserAgent =
        "Mozilla/5.0 (iPhone; CPU iPhone OS 13_3_1 like Mac OS X) " +
        "AppleWebKit/604.1.34 (KHTML, like Gecko) GSA/50.0.197507736 " +
        "Mobile/17D50 Safari/604.1";
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
    TwoFactor
}

public sealed record MailRuAuthChallenge(
    MailRuChallengeKind Kind,
    string Url,
    string SeedCookieHeader,
    string? DiagnosticReason);

public sealed record MailRuChallengeCompletion(
    string WebCookieHeader,
    string TouchCookieHeader);
