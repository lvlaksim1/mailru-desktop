namespace MailRuDesktop.App;

/// <summary>
/// Fixed semantic typography roles. Layout components use the same size
/// resources; changing the body size scales them without remapping widgets.
/// </summary>
internal static class ThemeTypography
{
    public const int DefaultSize = 12;
    public const int MinimumSize = 10;
    public const int MaximumSize = 18;

    public static int Normalize(int requested) =>
        Math.Clamp(requested, MinimumSize, MaximumSize);

    public static double AvatarSize(int requested) =>
        Math.Clamp(Normalize(requested) * 4.0 / 3.0, 14.0, 24.0);

    public static IReadOnlyDictionary<string, double> Resolve(int requested)
    {
        var size = Normalize(requested);
        return new Dictionary<string, double>(StringComparer.Ordinal)
        {
            ["AppFontTinySize"] = Math.Max(8, size - 4),
            ["AppFontSmallSize"] = Math.Max(9, size - 1),
            ["AppFontBodySize"] = size,
            ["AppFontMediumSize"] = size + 1,
            ["AppFontFormSize"] = size + 2,
            ["AppFontEmphasisSize"] = size + 4,
            ["AppFontHeadingSize"] = size + 8,
            ["AppFontTitleSize"] = size + 10,
            ["AppFontHeroSize"] = size + 14
        };
    }
}
