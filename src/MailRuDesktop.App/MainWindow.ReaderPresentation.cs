using System.Diagnostics;
using System.Threading;
using System.Windows.Threading;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private long _readerGeneration;
    private CancellationTokenSource? _readerStageCancellation;
    private Task _readerLoadingOperation = Task.CompletedTask;
    private MailRuMessageSummary? _readerPendingHeader;
    private Stopwatch? _readerStageWatch;

    /// <summary>
    /// Only browser DOM state is changed on selection. WebView2 itself
    /// remains visible and its top-level document remains unchanged.
    /// </summary>
    private void BeginReaderTransition(MailRuMessageSummary? target,
        string loadingText = "Загрузка письма…")
    {
        _readerGeneration++;
        _readerStageCancellation?.Cancel();
        _readerStageCancellation = new CancellationTokenSource();
        _readerPendingHeader = target;
        _readerStageWatch = Stopwatch.StartNew();
        _readerWaitingForFullMessage = target is not null;

        if (target is not null)
        {
            // Do not show the NEXT subject above the PREVIOUS mail body.
            SelectedSubjectText.Text = "Загрузка письма…";
            SelectedSenderNameText.Text = string.Empty;
            SelectedSenderText.Text = string.Empty;
            SelectedToText.Text = string.Empty;
            SelectedDateText.Text = string.Empty;
            SelectedMetaText.Text = string.Empty;
        }

        _readerLoadingOperation = SetReaderLoadingAsync(
            _readerGeneration, loadingText);
    }

    private async Task SetReaderLoadingAsync(long generation, string text)
    {
        if (!_readerReady || MessageWebView.CoreWebView2 is null)
            return;
        try
        {
            await MessageWebView.CoreWebView2.ExecuteScriptAsync(
                ReaderShellScripts.Begin(generation,
                    ThemeManager.ReaderBackgroundHtml, text));
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("reader_shell_begin", ex.GetType().Name);
        }
    }

    private async Task SetReaderEmptyAsync(string text)
    {
        var generation = _readerGeneration;
        try
        {
            await _readerLoadingOperation;
            if (generation == _readerGeneration &&
                MessageWebView.CoreWebView2 is not null)
                await MessageWebView.CoreWebView2.ExecuteScriptAsync(
                    ReaderShellScripts.StatusOnly(generation, text));
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("reader_shell_empty", ex.GetType().Name);
        }
    }

    private async Task StageReaderDocumentAsync(long generation, string html)
    {
        if (!_readerReady || MessageWebView.CoreWebView2 is null)
            return;

        var cancellation = _readerStageCancellation?.Token ?? CancellationToken.None;
        try
        {
            await _readerLoadingOperation;
            if (generation != _readerGeneration || cancellation.IsCancellationRequested)
                return;

            var browser = MessageWebView.CoreWebView2;
            var staged = await browser.ExecuteScriptAsync(
                ReaderShellScripts.Stage(generation, html));
            if (staged != "true")
                return;

            var wait = Stopwatch.StartNew();
            var ready = false;
            while (generation == _readerGeneration &&
                   !cancellation.IsCancellationRequested &&
                   wait.Elapsed < TimeSpan.FromSeconds(3))
            {
                var outcome = await browser.ExecuteScriptAsync(
                    ReaderShellScripts.Poll(generation));
                if (outcome == "\"ready\"")
                {
                    ready = true;
                    break;
                }
                if (outcome == "\"stale\"")
                    return;
                await Task.Delay(90, cancellation);
            }

            if (generation != _readerGeneration || cancellation.IsCancellationRequested)
                return;

            if (!ready)
            {
                // Replace unfinished image sources before publication; never
                // stop the entire browser or discard the persistent page.
                await browser.ExecuteScriptAsync(
                    ReaderShellScripts.FinishPendingImages(generation));
                await Task.Delay(70, cancellation);
            }

            // The old iframe and loading layer are replaced by a single DOM
            // operation inside the browser. No WPF airspace overlay involved.
            var committed = await browser.ExecuteScriptAsync(
                ReaderShellScripts.Commit(generation));
            if (committed != "true" ||
                generation != _readerGeneration || cancellation.IsCancellationRequested)
                return;

            if (_readerPendingHeader is { } mail &&
                string.Equals(_activePreviewMailId, mail.Id, StringComparison.Ordinal))
            {
                UpdateSelectedMessageHeader(mail);
                ApplyLoadedPreviewFields();
            }

            DiagnosticLog.Write("reader_visual_ready",
                $"reason={(ready ? "complete" : "limited")}; " +
                $"elapsed-ms={_readerStageWatch?.ElapsedMilliseconds ?? 0}");
        }
        catch (OperationCanceledException)
        {
            // A newer selection owns the single browser shell.
        }
        catch (Exception ex)
        {
            if (generation != _readerGeneration)
                return;

            DiagnosticLog.Write("reader_shell_stage", ex.GetType().Name);
            try
            {
                await MessageWebView.CoreWebView2.ExecuteScriptAsync(
                    ReaderShellScripts.StatusOnly(generation,
                        "Не удалось отобразить содержимое письма."));
            }
            catch (Exception nested)
            {
                DiagnosticLog.Write("reader_shell_error", nested.GetType().Name);
            }
        }
    }

    private void CancelReaderPresentation()
    {
        _readerStageCancellation?.Cancel();
        _readerStageCancellation = null;
    }
}
