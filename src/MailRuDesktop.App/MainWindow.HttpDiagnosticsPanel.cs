using System;
using System.IO;
using System.Text;
using System.Windows;
using MailRuDesktop.Protocol;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private void RefreshProtocolDiagnosticsView()
    {
        if (HttpProtocolLogTextBox is null) return;
        HttpProtocolLogTextBox.Text = MailRuHttpWireLog.CurrentHourReport();
        HttpProtocolLogTextBox.ScrollToEnd();
    }

    private void RefreshProtocolDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        RefreshProtocolDiagnosticsView();
        HttpProtocolStatusText.Text = "Журнал сетевого обмена за текущий час обновлён.";
    }

    private void CopyProtocolDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        try
        {
            Clipboard.SetText(MailRuHttpWireLog.CurrentHourReport());
            HttpProtocolStatusText.Text = "Полный отчёт за текущий час скопирован.";
        }
        catch (System.Runtime.InteropServices.ExternalException)
        {
            HttpProtocolStatusText.Text = "Не удалось скопировать отчёт.";
        }
    }

    private void SaveProtocolDiagnosticsButton_Click(object sender, RoutedEventArgs e)
    {
        var dialog = new Microsoft.Win32.SaveFileDialog
        {
            Title = "Сохранить полный HTTP-протокол за текущий час",
            Filter = "Текстовый отчёт (*.txt)|*.txt",
            FileName = "MailRuDesktop-HTTP-Hour.txt",
            DefaultExt = ".txt",
            AddExtension = true
        };
        if (dialog.ShowDialog(this) != true) return;
        try
        {
            File.WriteAllText(dialog.FileName, MailRuHttpWireLog.CurrentHourReport(),
                new UTF8Encoding(false));
            HttpProtocolStatusText.Text = "Отчёт за текущий час сохранён.";
        }
        catch (IOException)
        {
            HttpProtocolStatusText.Text = "Не удалось сохранить отчёт.";
        }
        catch (UnauthorizedAccessException)
        {
            HttpProtocolStatusText.Text = "Нет разрешения для сохранения отчёта.";
        }
    }
}
