using System.Text.RegularExpressions;

namespace MailRuDesktop.App;

/// <summary>Extract only addresses from header text, never display names/labels.</summary>
internal static class EmailAddressText
{
    private static readonly Regex Pattern = new(
        @"(?<![A-Z0-9._%+\-])([A-Z0-9.!#$%&'*+/=?^_\x60{|}~-]+@[A-Z0-9](?:[A-Z0-9-]*[A-Z0-9])?(?:\.[A-Z0-9](?:[A-Z0-9-]*[A-Z0-9])?)*)",
        RegexOptions.Compiled | RegexOptions.IgnoreCase | RegexOptions.CultureInvariant);

    internal static string[] Extract(string? text) =>
        Pattern.Matches(text ?? string.Empty)
            .Select(match => match.Groups[1].Value)
            .Distinct(StringComparer.OrdinalIgnoreCase)
            .ToArray();

    internal static string ForClipboard(string? text) =>
        string.Join(", ", Extract(text));
}
