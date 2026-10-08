namespace MailRuDesktop.App;

/// <summary>
/// A ready HTML DOM is not the same as a visually settled email. Showing
/// DOMContentLoaded immediately would reveal text first and image-driven
/// table reflow later. A bounded deadline avoids hanging on broken sources.
/// </summary>
internal static class ReaderPresentationPolicy
{
    public static readonly TimeSpan MaximumResourceWait = TimeSpan.FromSeconds(3);

    public static bool CanReveal(
        bool domReady, bool imagesSettled, bool deadlineReached) =>
        domReady && (imagesSettled || deadlineReached);

    public static bool ShouldStopLoading(
        bool domReady, bool resourcesFinished, bool deadlineReached) =>
        domReady && !imagesSettled && deadlineReached;
}
