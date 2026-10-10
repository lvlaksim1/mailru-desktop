using System.IO;
using System.Text;
using System.Text.RegularExpressions;
using System.Windows;
using System.Windows.Controls;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private void UnifiedDiagnosticsTabs_SelectionChanged(
        object sender, SelectionChangedEventArgs e)
    {
        if (!ReferenceEquals(e.Source, UnifiedDiagnosticsTabs)) return;
        if (UnifiedDiagnosticsTabs.SelectedIndex == 0)
            RefreshPushDiagnosticsView();
        else if (UnifiedDiagnosticsTabs.SelectedIndex == 2)
            RefreshApplicationDiagnosticsView();
    }

    private static string AppDiagnosticsReport()
    {
        var path = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "diagnostics.log");
        try
        {
            if (!File.Exists(path)) return "Журнал приложения пока пуст.";
            using var reader = new FileStream(path, FileMode.Open, FileAccess.Read,
                FileShare.ReadWrite | FileShare.Delete);
            if (reader.Length > 300_000)
                reader.Seek(-300_000, SeekOrigin.End);
            using var textReader = new StreamReader(reader, Encoding.UTF8);
            var report = textReader.ReadToEnd();
            // Existing diagnostic log is already sanitized at write time. The
            // unified exporter additionally hides addresses and token patterns
            // before showing/copying/saving anything from historical records.
            report = Regex.Replace(report,
                @"(?i)\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}\b",
                "<account>");
            report = Regex.Replace(report,
                @"(?i)\b(access_token|refresh_token|securitytoken|token|password|cookie)\s*[:=]\s*[^\s&;,]+",
                "$1=<hidden>");
            return report;
        }
        catch (IOException) { return "Журнал приложения недоступен."; }
        catch (UnauthorizedAccessException) { return "Нет доступа к журналу приложения."; }
    }

    private void RefreshApplicationDiagnosticsView()
    {
        if (_pushShuttingDown || ApplicationDiagnosticsLogTextBox is null) return;
        ApplicationDiagnosticsLogTextBox.Text = AppDiagnosticsReport();
        ApplicationDiagnosticsLogTextBox.ScrollToEnd();
    }

    private void RefreshApplicationDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        RefreshApplicationDiagnosticsView();
        ApplicationDiagnosticsStatusText.Text = "Журнал обновлён.";
    }

    private void CopyApplicationDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            Clipboard.SetText(AppDiagnosticsReport());
            ApplicationDiagnosticsStatusText.Text = "Отчёт скопирован.";
        }
        catch (System.Runtime.InteropServices.ExternalException)
        {
            ApplicationDiagnosticsStatusText.Text = "Не удалось скопировать отчёт.";
        }
    }

    private void SaveApplicationDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new Microsoft.Win32.SaveFileDialog
        {
            Title = "Сохранить диагностику MailRu Desktop",
            Filter = "Текстовый отчёт (*.txt)|*.txt",
            FileName = "MailRuDesktop-Diagnostics.txt",
            DefaultExt = ".txt",
            AddExtension = true
        };
        if (dialog.ShowDialog(this) != true) return;
        try
        {
            File.WriteAllText(dialog.FileName, AppDiagnosticsReport(), new UTF8Encoding(false));
            ApplicationDiagnosticsStatusText.Text = "Отчёт сохранён.";
        }
        catch (IOException)
        {
            ApplicationDiagnosticsStatusText.Text = "Не удалось сохранить отчёт.";
        }
        catch (UnauthorizedAccessException)
        {
            ApplicationDiagnosticsStatusText.Text = "Нет прав для сохранения отчёта.";
        }
    }
}
