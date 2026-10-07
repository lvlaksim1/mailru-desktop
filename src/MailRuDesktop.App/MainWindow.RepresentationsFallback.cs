using System.ComponentModel;
using System.Net;
using System.Text.Json;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Threading;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private DependencyPropertyDescriptor? _representationsItemsSourceDescriptor;
    private bool _representationsFallbackAttached;
    private bool _representationsFallbackRecovering;
    private bool _representationsFallbackRetryScheduled;

    static MainWindow()
    {
        EventManager.RegisterClassHandler(
            typeof(MainWindow),
            FrameworkElement.LoadedEvent,
            new RoutedEventHandler(RepresentationsFallback_WindowLoaded));
    }

    private static void RepresentationsFallback_WindowLoaded(object sender, RoutedEventArgs e)
    {
        if (sender is MainWindow window)
            window.AttachRepresentationsFallback();
    }

    private void AttachRepresentationsFallback()
    {
        if (_representationsFallbackAttached)
            return;

        _representationsFallbackAttached = true;
        _representationsItemsSourceDescriptor = DependencyPropertyDescriptor.FromProperty(
            ItemsControl.ItemsSourceProperty,
            typeof(ItemsControl));
        _representationsItemsSourceDescriptor?.AddValueChanged(
            MessagesGrid,
            RepresentationsFallback_ItemsSourceChanged);

        Closed += RepresentationsFallback_Closed;
        ScheduleRepresentationsFallback();
    }

    private void RepresentationsFallback_Closed(object? sender, EventArgs e)
    {
        _representationsItemsSourceDescriptor?.RemoveValueChanged(
            MessagesGrid,
            RepresentationsFallback_ItemsSourceChanged);
        Closed -= RepresentationsFallback_Closed;
    }

    private void RepresentationsFallback_ItemsSourceChanged(object? sender, EventArgs e) =>
        ScheduleRepresentationsFallback();

    private void ScheduleRepresentationsFallback()
    {
        if (_representationsFallbackRetryScheduled)
            return;

        _representationsFallbackRetryScheduled = true;
        Dispatcher.BeginInvoke(
            DispatcherPriority.ContextIdle,
            new Action(() =>
            {
                _representationsFallbackRetryScheduled = false;
                TryRecoverRepresentationMessages();
            }));
    }

    private void TryRecoverRepresentationMessages()
    {
        if (_representationsFallbackRecovering)
            return;

        if (_loadingFolder)
        {
            ScheduleRepresentationsFallback();
            return;
        }

        if (MessagesGrid.ItemsSource is IEnumerable<MailRuMessageSummary> current && current.Any())
            return;

        var raw = ResponseTextBox.Text;
        if (string.IsNullOrWhiteSpace(raw) ||
            !raw.Contains("\"representations\"", StringComparison.Ordinal) ||
            !raw.Contains("\"folders_content\"", StringComparison.Ordinal))
        {
            return;
        }

        IReadOnlyList<MailRuMessageSummary> recovered;
        try
        {
            recovered = ParseRepresentationMessages(raw, _currentFolderId);
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write(
                "representations_fallback_parse",
                ex.GetType().Name + ": " + ex.Message);
            return;
        }

        if (recovered.Count == 0)
            return;

        _representationsFallbackRecovering = true;
        try
        {
            _currentMessages = recovered.ToList();
            ApplyFilters();

            var folder = (FolderListBox.ItemsSource as IEnumerable<MailRuFolderSummary>)?
                .FirstOrDefault(item => item.Id == _currentFolderId);
            var total = folder?.MessagesTotal.ToString() ?? "?";
            var unread = folder?.MessagesUnread.ToString() ?? "?";
            FolderStatusText.Text =
                $"Всего: {total} · непрочитанных: {unread} · показано: {_currentMessages.Count}";

            if (_currentMessages.Count > 0)
                MessagesGrid.SelectedIndex = 0;

            DiagnosticLog.Write(
                "representations_fallback",
                $"Recovered {_currentMessages.Count} messages for folder {_currentFolderId}.");
        }
        finally
        {
            _representationsFallbackRecovering = false;
        }
    }

    internal static IReadOnlyList<MailRuMessageSummary> ParseRepresentationMessages(
        string payload,
        int requestedFolderId)
    {
        using var document = JsonDocument.Parse(payload);
        var root = document.RootElement;
        var body = root.ValueKind == JsonValueKind.Object &&
                   root.TryGetProperty("body", out var bodyElement)
            ? bodyElement
            : root;

        if (body.ValueKind != JsonValueKind.Object ||
            !body.TryGetProperty("folders_content", out var foldersContent) ||
            foldersContent.ValueKind != JsonValueKind.Array)
        {
            return Array.Empty<MailRuMessageSummary>();
        }

        JsonElement? selectedContent = null;
        foreach (var content in foldersContent.EnumerateArray())
        {
            if (content.ValueKind != JsonValueKind.Object)
                continue;

            var id = RepresentationReadInteger(content, "id");
            if (id == requestedFolderId)
            {
                selectedContent = content;
                break;
            }

            selectedContent ??= content;
        }

        if (selectedContent is null ||
            !selectedContent.Value.TryGetProperty("threads", out var threads) ||
            threads.ValueKind != JsonValueKind.Array)
        {
            return Array.Empty<MailRuMessageSummary>();
        }

        var result = new List<MailRuMessageSummary>();
        var seenMessageIds = new HashSet<string>(StringComparer.Ordinal);

        foreach (var thread in threads.EnumerateArray())
        {
            if (thread.ValueKind != JsonValueKind.Object ||
                !thread.TryGetProperty("representations", out var representations) ||
                representations.ValueKind != JsonValueKind.Array)
            {
                continue;
            }

            JsonElement? selectedRepresentation = null;
            long selectedDate = long.MinValue;

            foreach (var representation in representations.EnumerateArray())
            {
                if (representation.ValueKind != JsonValueKind.Object)
                    continue;

                var folder = RepresentationReadInteger(representation, "folder");
                if (folder != requestedFolderId)
                    continue;

                var date = RepresentationReadInteger(representation, "date") ?? long.MinValue;
                if (selectedRepresentation is null || date > selectedDate)
                {
                    selectedRepresentation = representation;
                    selectedDate = date;
                }
            }

            if (selectedRepresentation is null)
            {
                var representationsArray = representations.EnumerateArray()
                    .Where(item => item.ValueKind == JsonValueKind.Object)
                    .ToArray();
                if (representationsArray.Length == 1)
                    selectedRepresentation = representationsArray[0];
            }

            if (selectedRepresentation is null)
                continue;

            var representationMessage = selectedRepresentation.Value;
            var messageId =
                RepresentationReadString(representationMessage, "message_id_last") ??
                RepresentationReadString(representationMessage, "id") ??
                RepresentationReadString(thread, "id");

            if (string.IsNullOrWhiteSpace(messageId) || !seenMessageIds.Add(messageId))
                continue;

            var subject = RepresentationDecode(
                RepresentationReadString(representationMessage, "subject") ?? "(без темы)");
            var snippet = RepresentationDecode(
                RepresentationReadString(representationMessage, "snippet") ?? string.Empty);
            var dateUnix = RepresentationReadInteger(representationMessage, "date");
            var size = RepresentationReadInteger(representationMessage, "size");
            var folderId = RepresentationReadInteger(representationMessage, "folder");
            var unread = RepresentationReadFlag(representationMessage, "unread") ?? false;
            var flagged = RepresentationReadFlag(representationMessage, "flagged") ?? false;
            var pinned = RepresentationReadFlag(representationMessage, "pinned") ?? false;
            var attachmentsCount = RepresentationReadInteger(
                representationMessage,
                "attachments_count") ?? 0;
            var hasAttachment = attachmentsCount > 0 ||
                                RepresentationReadFlag(representationMessage, "attach") == true;
            var (senderName, senderEmail) = RepresentationReadSender(representationMessage);

            result.Add(new MailRuMessageSummary(
                messageId,
                subject,
                snippet,
                senderName,
                senderEmail,
                dateUnix,
                size,
                folderId is null ? requestedFolderId : checked((int)folderId.Value),
                unread,
                flagged,
                hasAttachment,
                pinned));
        }

        result.Sort((left, right) =>
        {
            var pinnedCompare = right.Pinned.CompareTo(left.Pinned);
            return pinnedCompare != 0
                ? pinnedCompare
                : Nullable.Compare(right.DateUnix, left.DateUnix);
        });

        return result;
    }

    private static (string Name, string Email) RepresentationReadSender(JsonElement element)
    {
        if (!element.TryGetProperty("correspondents", out var correspondents) ||
            correspondents.ValueKind != JsonValueKind.Object ||
            !correspondents.TryGetProperty("from", out var from) ||
            from.ValueKind != JsonValueKind.Array)
        {
            return (string.Empty, string.Empty);
        }

        foreach (var sender in from.EnumerateArray())
        {
            if (sender.ValueKind != JsonValueKind.Object)
                continue;

            return (
                RepresentationDecode(RepresentationReadString(sender, "name") ?? string.Empty),
                RepresentationDecode(RepresentationReadString(sender, "email") ?? string.Empty));
        }

        return (string.Empty, string.Empty);
    }

    private static bool? RepresentationReadFlag(JsonElement element, string name)
    {
        if (!element.TryGetProperty("flags", out var flags) ||
            flags.ValueKind != JsonValueKind.Object ||
            !flags.TryGetProperty(name, out var value))
        {
            return null;
        }

        return value.ValueKind switch
        {
            JsonValueKind.True => true,
            JsonValueKind.False => false,
            JsonValueKind.Number when value.TryGetInt32(out var number) => number != 0,
            JsonValueKind.String when bool.TryParse(value.GetString(), out var parsed) => parsed,
            JsonValueKind.String when value.GetString() == "1" => true,
            JsonValueKind.String when value.GetString() == "0" => false,
            _ => null
        };
    }

    private static string? RepresentationReadString(JsonElement element, string name)
    {
        if (!element.TryGetProperty(name, out var value))
            return null;

        return value.ValueKind switch
        {
            JsonValueKind.String => value.GetString(),
            JsonValueKind.Number => value.GetRawText(),
            _ => null
        };
    }

    private static long? RepresentationReadInteger(JsonElement element, string name)
    {
        if (!element.TryGetProperty(name, out var value))
            return null;

        if (value.ValueKind == JsonValueKind.Number && value.TryGetInt64(out var number))
            return number;

        if (value.ValueKind == JsonValueKind.String &&
            long.TryParse(value.GetString(), out number))
        {
            return number;
        }

        return null;
    }

    private static string RepresentationDecode(string value) =>
        WebUtility.HtmlDecode(value).Replace("&nbsp;", " ", StringComparison.Ordinal);
}
