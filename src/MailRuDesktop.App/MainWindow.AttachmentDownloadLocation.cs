using System.Diagnostics;
using System.IO;
using System.Windows;
using Microsoft.Win32;

namespace MailRuDesktop.App;

internal static class AttachmentDownloadLocation
{
    public static string GetDirectory() => new AppSettingsStore()
        .LoadAttachmentDownloadDirectory();

    public static void OpenAfterSaving(string savedPath)
    {
        var directory = Path.GetDirectoryName(Path.GetFullPath(savedPath));
        if (directory is null || !Directory.Exists(directory))
            return;

        try
        {
            var process = new ProcessStartInfo
            {
                FileName = "explorer.exe",
                UseShellExecute = true
            };
            process.ArgumentList.Add(directory);
            Process.Start(process);
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write("attachment_open_folder", ex.Message);
            // A saved attachment is still a success if Explorer cannot start.
        }
    }
}

public partial class MainWindow
{
    private void InitializeDownloadDirectorySettings() =>
        AttachmentDownloadDirectoryText.Text = _settingsStore.LoadAttachmentDownloadDirectory();

    private void BrowseDownloadDirectoryButton_Click(object sender, RoutedEventArgs e)
    {
        var picker = new OpenFolderDialog
        {
            Title = "Выберите папку для скачивания вложений",
            InitialDirectory = _settingsStore.LoadAttachmentDownloadDirectory()
        };
        if (picker.ShowDialog(this) != true)
            return;
        AttachmentDownloadDirectoryText.Text = picker.FolderName;
        SaveDownloadDirectory();
    }

    private void SaveDownloadDirectory()
    {
        try
        {
            _settingsStore.SaveAttachmentDownloadDirectory(AttachmentDownloadDirectoryText.Text);
            AttachmentDownloadDirectoryText.Text = _settingsStore.LoadAttachmentDownloadDirectory();
            AttachmentDirectoryStatusText.Text = "Папка скачивания сохранена.";
        }
        catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or ArgumentException)
        {
            AttachmentDirectoryStatusText.Text = "Не удалось сохранить папку.";
            AppDialog.Info(this, "Папка скачивания", ex.Message);
        }
    }
}
