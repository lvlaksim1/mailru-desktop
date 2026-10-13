using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _primaryColorEditorReady;

    private void InitializePaletteEditor()
    {
        _primaryColorEditorReady = false;
        try
        {
            var primary = _settingsStore.LoadPrimaryColor();
            PrimaryColorTextBox.Text = primary;
            PrimaryColorSwatch.Background =
                new SolidColorBrush((Color)ColorConverter.ConvertFromString(primary));
            UpdatePrimaryColorContrast();
        }
        finally
        {
            _primaryColorEditorReady = true;
        }
    }

    private void PrimaryColorTextBox_TextChanged(object sender, TextChangedEventArgs e)
    {
        if (!_primaryColorEditorReady ||
            !ThemePalette.TryNormalize(PrimaryColorTextBox.Text, out var hex) ||
            hex == _settingsStore.LoadPrimaryColor())
            return;
        ApplyPrimaryColor(hex);
    }

    private void PickPrimaryColorButton_Click(object sender, RoutedEventArgs e)
    {
        var picker = new PaletteColorPickerWindow(
            this, "Основной цвет приложения", _settingsStore.LoadPrimaryColor());
        if (picker.ShowDialog() == true)
            ApplyPrimaryColor(picker.SelectedHex);
    }

    private void ResetPrimaryColorButton_Click(object sender, RoutedEventArgs e) =>
        ApplyPrimaryColor(ThemePalette.DefaultPrimaryColor);

    private void ApplyPrimaryColor(string hex)
    {
        if (!ThemePalette.TryNormalize(hex, out var normalized))
            return;

        try
        {
            _settingsStore.SavePrimaryColor(normalized);
            _primaryColorEditorReady = false;
            PrimaryColorTextBox.Text = normalized;
            PrimaryColorSwatch.Background =
                new SolidColorBrush((Color)ColorConverter.ConvertFromString(normalized));
            ThemeManager.Apply(ThemeManager.CurrentMode);
            UpdatePrimaryColorContrast();
        }
        catch (Exception ex)
        {
            PrimaryColorStatusText.Text = "Ошибка сохранения цвета: " + ex.Message;
        }
        finally
        {
            _primaryColorEditorReady = true;
        }
    }

    private void UpdatePrimaryColorContrast()
    {
        var colors = _settingsStore.LoadPaletteOverrides(ThemeManager.IsDarkEffective);
        var pairs = new (string Text, string Surface)[]
        {
            ("AppTextBrush", "AppWindowBrush"),
            ("AppMutedTextBrush", "AppPanelBrush"),
            ("AppAccentTextBrush", "AppAccentBrush"),
            ("AppUnreadTextBrush", "AppPanelBrush")
        };
        var minimum = pairs.Min(p => ThemePalette.Contrast(
            colors[p.Text], colors[p.Surface]));
        PrimaryColorStatusText.Text = minimum >= 4.5
            ? "Все элементы используют восемь оттенков основного цвета."
            : "Внимание: для выбранного цвета контраст некоторых элементов ниже 4,5:1.";
    }
}
