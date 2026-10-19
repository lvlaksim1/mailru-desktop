using System.IO;
using System.IO.Compression;
using System.Windows;
using Microsoft.Win32;

namespace MailRuDesktop.App;

public partial class MessageWindow
{
    private async void DownloadAllAttachmentsButton_Click(object sender, RoutedEventArgs e)
    {
        var message = _fullMessage;
        if (message is null || message.Attachments.Count <= 1 ||
            string.IsNullOrWhiteSpace(_accessToken))
        {
            StatusText.Text = "В письме нет нескольких вложений.";
            return;
        }

        var dialog = new SaveFileDialog
        {
            FileName = SanitizeFileName(
                string.IsNullOrWhiteSpace(message.Subject)
                    ? "Вложения.zip"
                    : message.Subject + " - вложения.zip"),
            InitialDirectory = AttachmentDownloadLocation.GetDirectory(),
            DefaultExt = ".zip",
            Filter = "ZIP-архив (*.zip)|*.zip",
            Title = "Сохранить все вложения"
        };
        if (dialog.ShowDialog(this) != true)
            return;

        DownloadAllAttachmentsButton.IsEnabled = false;
        AttachmentsItemsControl.IsEnabled = false;
        try
        {
            await using (var output = new FileStream(dialog.FileName,
                FileMode.Create, FileAccess.Write, FileShare.None,
                1024 * 128, useAsync: true))
            using (var archive = new ZipArchive(output, ZipArchiveMode.Create, leaveOpen: true))
            {
                var usedNames = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
                for (var i = 0; i < message.Attachments.Count; i++)
                {
                    var attachment = message.Attachments[i];
                    if (string.IsNullOrWhiteSpace(attachment.Id))
                        continue;

                    StatusText.Text =
                        $"Скачивание вложений {i + 1}/{message.Attachments.Count}...";
                    var bytes = await _mailRu.DownloadIncomingAttachmentAsync(
                        _accessToken, message.Id, attachment.Id);
                    var archiveName = UniqueZipEntryName(
                        SanitizeFileName(attachment.DisplayName), usedNames);
                    var entry = archive.CreateEntry(archiveName, CompressionLevel.Optimal);
                    await using var stream = entry.Open();
                    await stream.WriteAsync(bytes);
                }
            }

            StatusText.Text =
                $"Вложения сохранены: {Path.GetFileName(dialog.FileName)}";
            AttachmentDownloadLocation.OpenAfterSaving(dialog.FileName);
        }
        catch (Exception ex)
        {
            StatusText.Text = "Ошибка скачивания всех вложений.";
            DiagnosticLog.Write("message_window_attachments_zip",
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            DownloadAllAttachmentsButton.IsEnabled = true;
            AttachmentsItemsControl.IsEnabled = true;
        }
    }

    private static string UniqueZipEntryName(string requested,
        HashSet<string> usedNames)
    {
        var name = string.IsNullOrWhiteSpace(requested) ? "attachment" : requested;
        if (usedNames.Add(name)) return name;
        var extension = Path.GetExtension(name);
        var baseName = Path.GetFileNameWithoutExtension(name);
        for (var index = 2; ; index++)
        {
            var candidate = $"{baseName} ({index}){extension}";
            if (usedNames.Add(candidate)) return candidate;
        }
    }
}
