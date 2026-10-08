using System.Diagnostics;
using System.Windows;
using System.Windows.Threading;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly DispatcherTimer _readerResourceDeadline = new()
    {
        Interval = ReaderPresentationPolicy.MaximumResourceWait
    };
    private ulong _readerStageNavigationId;
    private long _readerStageRevision;
    private bool _readerStageDomReady;
    private bool _readerStageResourcesFinished;
    private bool _readerStageDeadlineReached;
    private bool _readerStageRevealing;
    private Stopwatch? _readerStageWatch;

    private void InitializeReaderPresentation()
    {
        _readerResourceDeadline.Tick += ReaderResourceDeadline_Tick;
    }

    // Called on selecting a new message, before stopping any previous load.
    private void CancelReaderPresentation()
    {
        _readerResourceDeadline.Stop();
        ++_readerStageRevision;
        _readerStageNavigationId = 0;
        _readerStageDomReady = false;
        _readerStageResourcesFinished = false;
        _readerStageDeadlineReached = false;
        _readerStageRevealing = false;
        _readerStageWatch = null;
    }

    private void ReaderNavigationStarting(object? sender,
        CoreWebView2NavigationStartingEventArgs args)
    {
        // Older navigations can still raise events after Stop(). They do
        // not belong to the newly selected message.
        if (!_readerNavigationPending)
            return;

        CancelReaderPresentation();
        _readerStageNavigationId = args.NavigationId;
        _latestReaderNavigationId = args.NavigationId;
        _readerNavigationPending = false;
        _readerWaitingForFullMessage = false;
        _readerStageWatch = Stopwatch.StartNew();

        MessageWebView.Visibility = Visibility.Hidden;
        ReaderLoadingOverlay.Visibility = Visibility.Visible;
        ReaderLoadingText.Text = "Загрузка письма…";
    }

    private async void ReaderDomContentLoaded(object? sender,
        CoreWebView2DOMContentLoadedEventArgs args)
    {
        if (!IsCurrentReaderNavigation(args.NavigationId))
            return;

        _readerStageDomReady = true;
        // Even when a message uses loading=lazy the reader must request its
        // images while hidden, otherwise they only start loading on reveal.
        // Host-initiated WebView2 scripts work independently of the mail's
        // disabled page-script setting. No untrusted email text enters script.
        try
        {
            await MessageWebView.CoreWebView2.ExecuteScriptAsync(
                "(function(){for(const i of document.images){i.loading='eager';}})();");
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("reader_staging_script", ex.GetType().Name);
        }

        if (!IsCurrentReaderNavigation(args.NavigationId))
            return;

        if (_readerStageResourcesFinished)
        {
            await RevealReaderOnceAsync(args.NavigationId, "complete");
            return;
        }

        _readerResourceDeadline.Stop();
        _readerResourceDeadline.Start();
        // Nothing becomes visible merely because DOMContentLoaded fired.
    }

    private async void ReaderNavigationCompleted(object? sender,
        CoreWebView2NavigationCompletedEventArgs args)
    {
        if (!IsCurrentReaderNavigation(args.NavigationId))
            return;

        if (!args.IsSuccess && !_readerStageDeadlineReached)
        {
            _readerResourceDeadline.Stop();
            ReaderLoadingText.Text = "Не удалось отобразить содержимое письма.";
            DiagnosticLog.Write("reader_navigation",
                "WebView2 error=" + args.WebErrorStatus);
            return;
        }

        _readerStageResourcesFinished = true;
        _readerResourceDeadline.Stop();

        if (_readerStageDomReady)
            await RevealReaderOnceAsync(args.NavigationId,
                _readerStageDeadlineReached ? "limited" : "complete");
    }

    private async void ReaderResourceDeadline_Tick(object? sender, EventArgs args)
    {
        _readerResourceDeadline.Stop();
        var navigationId = _readerStageNavigationId;
        if (!IsCurrentReaderNavigation(navigationId) ||
            !ReaderPresentationPolicy.ShouldStopLoading(
                _readerStageDomReady,
                _readerStageResourcesFinished,
                deadlineReached: true))
            return;

        _readerStageDeadlineReached = true;

        // Cancel unfinished image requests *before* showing the document.
        // Otherwise a delayed image would reflow the visible receipt later.
        try { MessageWebView.CoreWebView2?.Stop(); }
        catch (Exception ex)
        {
            DiagnosticLog.Write("reader_staging_stop", ex.GetType().Name);
        }

        // Give the hidden document a rendering turn to settle broken/missing
        // image placeholders after Stop(), not on the visible surface.
        await Task.Delay(110);
        if (IsCurrentReaderNavigation(navigationId))
            await RevealReaderOnceAsync(navigationId, "limited");
    }

    private bool IsCurrentReaderNavigation(ulong navigationId) =>
        _readerReady &&
        !_readerNavigationPending &&
        !_readerWaitingForFullMessage &&
        _readerStageNavigationId == navigationId &&
        _latestReaderNavigationId == navigationId;

    private async Task RevealReaderOnceAsync(ulong navigationId, string reason)
    {
        if (!IsCurrentReaderNavigation(navigationId) ||
            _readerStageRevealing ||
            !ReaderPresentationPolicy.CanReveal(
                _readerStageDomReady, _readerStageResourcesFinished,
                _readerStageDeadlineReached))
            return;

        _readerStageRevealing = true;
        var revision = _readerStageRevision;
        _readerResourceDeadline.Stop();

        // Wait a rendering turn while still hidden so the image sizes and
        // table columns do not change immediately after the first visible paint.
        await Task.Delay(65);

        if (revision != _readerStageRevision ||
            !IsCurrentReaderNavigation(navigationId))
            return;

        MessageWebView.Visibility = Visibility.Visible;
        ReaderLoadingOverlay.Visibility = Visibility.Collapsed;
        DiagnosticLog.Write("reader_visual_ready",
            $"reason={reason}; elapsed-ms={_readerStageWatch?.ElapsedMilliseconds ?? 0}");
    }
}
