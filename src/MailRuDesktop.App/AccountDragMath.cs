namespace MailRuDesktop.App;

/// <summary>
/// Reordering decisions are based on unchanged row centers, not the row positions
/// modified by visual animations. The only drag threshold is crossing another
/// row's midpoint; the dragged row's midpoint must not become a drop target.
/// </summary>
internal static class AccountDragMath
{
    public static int FindTarget(int source, double pointerY, IReadOnlyList<double?> midpoints)
    {
        if (source < 0 || source >= midpoints.Count)
            return -1;

        if (midpoints[source] is double origin && pointerY < origin)
        {
            for (var i = 0; i < source; i++)
            {
                if (midpoints[i] is double center && pointerY < center)
                    return i;
            }
            return source;
        }

        var target = source;
        for (var i = source + 1; i < midpoints.Count; i++)
        {
            if (midpoints[i] is double center && pointerY >= center)
                target = i;
        }
        return target;
    }
}
