namespace MailRuDesktop.App;

/// <summary>
/// Fixed operation precedence. Checked ids always win; the open preview is a
/// fallback only when no boxes are checked. Never add preview to checked ids.
/// </summary>
internal static class MailTargetResolver
{
    public static string[] Resolve(
        IEnumerable<string> checkedIds, string? activePreviewId)
    {
        var checkedTargets = checkedIds
            .Where(id => !string.IsNullOrWhiteSpace(id))
            .Distinct(StringComparer.Ordinal)
            .ToArray();

        if (checkedTargets.Length > 0)
            return checkedTargets;

        return string.IsNullOrWhiteSpace(activePreviewId)
            ? []
            : [activePreviewId];
    }
}
