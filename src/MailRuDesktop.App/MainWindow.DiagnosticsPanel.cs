using System.IO;
using System.Text;
using System.Windows;
using System.Windows.Controls;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private double _diagnosticsExpandedHeight = 320;

    private void ApplyDiagnosticsPanelHeight(bool expanded)
    {
        DiagnosticsPaneRow.Height = new GridLength(
            expanded ? _diagnosticsExpandedHeight : 32);
        DiagnosticsSplitterRow.Height = new GridLength(expanded ? 6 : 0);
        DiagnosticsPaneSplitter.Visibility = expanded
            ? Visibility.Visible : Visibility.Collapsed;
    }

    private void UnifiedDiagnosticsExpander_Expanded(object sender, RoutedEventArgs e)
    {
        ApplyDiagnosticsPanelHeight(true);
        RefreshPushDiagnosticsView();
        RefreshApplicationDiagnosticsView();
    }

    private void UnifiedDiagnosticsExpander_Collapsed(object sender, RoutedEventArgs e)
    {
        if (DiagnosticsPaneRow.ActualHeight > 80)
            _diagnosticsExpandedHeight = DiagnosticsPaneRow.ActualHeight;
        ApplyDiagnosticsPanelHeight(false);
    }

    private void UnifiedDiagnosticsTabs_SelectionChanged(
        object sender, SelectionChangedEventArgs e)
    {
        if (!ReferenceEquals(e.Source, UnifiedDiagnosticsTabs)) return;
        if (UnifiedDiagnosticsTabs.SelectedIndex == 0)
            RefreshPushDiagnosticsView();
        else if (UnifiedDiagnosticsTabs.SelectedIndex == 2)
            RefreshApplicationDiagnosticsView();
    }

    private static string ReadApplicationDiagnostics()
    {
        var path = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "diagnostics.log");
        try
        {
            if (!File.Exists(path)) return string.Empty;
            using var reader = new FileStream(path, FileMode.Open, FileAccess.Read,
                FileShare.ReadWrite | FileShare.Delete);
            if (reader.Length > 300_000)
                reader.Seek(-300_000, SeekOrigin.End);
            using var textReader = new StreamReader(reader, Encoding.UTF8);
            return textReader.ReadToEnd();
        }
        catch (IOException) { return string.Empty; }
        catch (UnauthorizedAccessException) { return string.Empty; }
    }

    private string AppDiagnosticsReport(bool includeAccounts, bool technical)
    {
        return HumanDiagnostics.Application(
            ReadApplicationDiagnostics(), includeAccounts, technical);
    }

    private void RefreshApplicationDiagnosticsView()
    {
        if (_pushShuttingDown || ApplicationDiagnosticsLogTextBox is null) return;
        ApplicationDiagnosticsLogTextBox.Text = AppDiagnosticsReport(
            includeAccounts: true,
            technical: ApplicationDiagnosticsTechnicalCheckBox.IsChecked == true);
        ApplicationDiagnosticsLogTextBox.ScrollToEnd();
    }

    private void ApplicationDiagnosticsDisplayMode_Changed(object sender, RoutedEventArgs e)
    {
        RefreshApplicationDiagnosticsView();
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
            Clipboard.SetText(AppDiagnosticsReport(
                includeAccounts: true,
                technical: ApplicationDiagnosticsTechnicalCheckBox.IsChecked == true));
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
            File.WriteAllText(dialog.FileName, AppDiagnosticsReport(
                includeAccounts: true,
                technical: ApplicationDiagnosticsTechnicalCheckBox.IsChecked == true),
                new UTF8Encoding(false));
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
