using System.IO.Compression;
using System.Windows;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private async void DownloadAllAttachmentsButton_Click(object sender, RoutedEventArgs e)
    {
        if (_currentFullMessage is null ||
            _currentFullMessage.Attachments.Count <= 1 ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            FolderStatusText.Text = "В письме нет нескольких вложений.";
            return;
        }

        var dialog = new SaveFileDialog
        {
            FileName = SafeFileName(
                string.IsNullOrWhiteSpace(_currentFullMessage.Subject)
                    ? "Вложения.zip"
                    : _currentFullMessage.Subject + " - вложения.zip"),
            DefaultExt = ".zip",
            Filter = "ZIP-архив (*.zip)|*.zip",
            Title = "Сохранить все вложения"
        };

        if (dialog.ShowDialog(this) != true)
            return;

        DownloadAttachmentButton.IsEnabled = false;
        DownloadAllAttachmentsButton.IsEnabled = false;

        try
        {
            await using var file = new FileStream(
                dialog.FileName,
                FileMode.Create,
                FileAccess.Write,
                FileShare.None,
                1024 * 128,
                useAsync: true);
            using var archive = new ZipArchive(file, ZipArchiveMode.Create, leaveOpen: true);
            var usedNames = new HashSet<string>(StringComparer.OrdinalIgnoreCase);

            for (var i = 0; i < _currentFullMessage.Attachments.Count; i++)
            {
                var attachment = _currentFullMessage.Attachments[i];
                if (string.IsNullOrWhiteSpace(attachment.Id))
                    continue;

                FolderStatusText.Text =
                    $"Скачивание вложений {i + 1}/{_currentFullMessage.Attachments.Count}...";

                var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                    _accessToken,
                    _currentFullMessage.Id,
                    attachment.Id);

                var entryName = MakeUniqueArchiveName(
                    SafeFileName(attachment.DisplayName),
                    usedNames);
                var entry = archive.CreateEntry(entryName, CompressionLevel.Optimal);
                await using var entryStream = entry.Open();
                await entryStream.WriteAsync(bytes);
            }

            FolderStatusText.Text =
                $"Вложения сохранены: {Path.GetFileName(dialog.FileName)}";
        }
        catch (Exception ex)
        {
            FolderStatusText.Text = "Ошибка скачивания всех вложений.";
            DiagnosticLog.Write(
                "incoming_attachments_zip",
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            DownloadAttachmentButton.IsEnabled = true;
            DownloadAllAttachmentsButton.IsEnabled = true;
        }
    }

    private static string MakeUniqueArchiveName(
        string requestedName,
        HashSet<string> usedNames)
    {
        var name = string.IsNullOrWhiteSpace(requestedName)
            ? "attachment"
            : requestedName;

        if (usedNames.Add(name))
            return name;

        var extension = Path.GetExtension(name);
        var stem = Path.GetFileNameWithoutExtension(name);
        for (var index = 2; ; index++)
        {
            var candidate = $"{stem} ({index}){extension}";
            if (usedNames.Add(candidate))
                return candidate;
        }
    }
}
