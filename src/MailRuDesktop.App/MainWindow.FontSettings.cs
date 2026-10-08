using System.Windows;
using System.Windows.Controls;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _restoringInterfaceFontSize;

    private void InitializeInterfaceFontSettings()
    {
        _restoringInterfaceFontSize = true;
        try
        {
            var current = _settingsStore.LoadInterfaceFontSize();
            InterfaceFontSlider.Value = current;
            InterfaceFontValueText.Text = $"{current} пикс.";
            ThemeManager.ApplyFontSize(current);
        }
        finally
        {
            _restoringInterfaceFontSize = false;
        }
    }

    private void InterfaceFontSlider_ValueChanged(
        object sender, RoutedPropertyChangedEventArgs<double> e)
    {
        if (_restoringInterfaceFontSize || !IsLoaded ||
            InterfaceFontValueText is null)
            return;

        var value = ThemeTypography.Normalize((int)Math.Round(e.NewValue));
        try
        {
            _settingsStore.SaveInterfaceFontSize(value);
            InterfaceFontValueText.Text = $"{value} пикс.";
            ThemeManager.ApplyFontSize(value);
        }
        catch (Exception ex)
        {
            AppDialog.Info(this, "Размер текста", ex.Message);
        }
    }

    private void ResetInterfaceFontButton_Click(object sender, RoutedEventArgs e) =>
        InterfaceFontSlider.Value = ThemeTypography.DefaultSize;
}
