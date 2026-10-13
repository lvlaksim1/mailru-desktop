namespace MailRuDesktop.App;

/// <summary>
/// Exactly one recognized signature at the END of the editable message.
/// Do not mistake a quoted signature in the middle of a reply for the active one.
/// </summary>
internal static class SignaturePlacement
{
    public static string Replace(string text, string? oldSignature,
        string newSignature, IEnumerable<string>? knownSignatures = null)
    {
        var body = (text ?? string.Empty).TrimEnd('\r', '\n');
        var ordered = new[] { oldSignature ?? string.Empty }
            .Concat(knownSignatures ?? [])
            .Where(x => !string.IsNullOrWhiteSpace(x))
            .Select(x => x.TrimEnd('\r', '\n'))
            .Distinct(StringComparer.Ordinal)
            .OrderByDescending(x => x.Length);

        foreach (var known in ordered)
        {
            if (!body.EndsWith(known, StringComparison.Ordinal)) continue;
            var before = body[..^known.Length];
            if (before.Length != 0 && !before.EndsWith("\n\n", StringComparison.Ordinal) &&
                !before.EndsWith("\r\n\r\n", StringComparison.Ordinal))
                continue;
            body = before.TrimEnd('\r', '\n');
            break;
        }

        if (string.IsNullOrWhiteSpace(newSignature)) return body;
        return body.Length == 0 ? newSignature :
            body + Environment.NewLine + Environment.NewLine + newSignature;
    }
}
