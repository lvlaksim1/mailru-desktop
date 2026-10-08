using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Media;

namespace MailRuDesktop.App;

internal sealed class ThemePaletteRow : INotifyPropertyChanged
{
    private string _hex;
    public ThemeColorRole Role { get; }
    public string Key => Role.Key;
    public string Group => Role.Group;
    public string Label => Role.Label;
    public string AppliesTo => Role.AppliesTo;
    public string Hex
    {
        get => _hex;
        private set
        {
            if (_hex == value) return;
            _hex = value;
            OnChanged();
            OnChanged(nameof(Swatch));
        }
    }
    public Brush Swatch =>
        new SolidColorBrush((Color)ColorConverter.ConvertFromString(Hex));

    public ThemePaletteRow(ThemeColorRole role, string hex)
    {
        Role = role;
        _hex = hex;
    }

    public void Update(string hex) => Hex = hex;

    public event PropertyChangedEventHandler? PropertyChanged;
    private void OnChanged([CallerMemberName] string? name = null) =>
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
}

public partial class MainWindow
{
    private readonly ObservableCollection<ThemePaletteRow> _paletteRows = [];
    private bool _paletteEditorReady;
    private bool _editingDarkPalette;

    private void InitializePaletteEditor()
    {
        _paletteEditorReady = false;
        _editingDarkPalette = ThemeManager.IsDarkEffective;

        var view = CollectionViewSource.GetDefaultView(_paletteRows);
        view.GroupDescriptions.Clear();
        view.GroupDescriptions.Add(new PropertyGroupDescription(nameof(ThemePaletteRow.Group)));
        PaletteRoleItemsControl.ItemsSource = view;
        PaletteThemeComboBox.SelectedIndex = _editingDarkPalette ? 0 : 1;
        _paletteEditorReady = true;
        ReloadPaletteRoles();
    }

    private void ReloadPaletteRoles()
    {
        _paletteEditorReady = false;
        try
        {
            var colors = _settingsStore.LoadPaletteOverrides(_editingDarkPalette);
            _paletteRows.Clear();
            foreach (var role in ThemePalette.EditableRoles)
                _paletteRows.Add(new ThemePaletteRow(role, colors[role.Key]));

            PaletteRoleCountText.Text = $"{ThemePalette.EditableRoles.Count} общих настроек цветов.";
            UpdatePaletteContrastStatus(colors);
        }
        finally
        {
            _paletteEditorReady = true;
        }
    }

    private void PaletteThemeComboBox_SelectionChanged(
        object sender, SelectionChangedEventArgs e)
    {
        if (!_paletteEditorReady || PaletteThemeComboBox.SelectedIndex < 0)
            return;

        _editingDarkPalette = PaletteThemeComboBox.SelectedIndex == 0;
        ReloadPaletteRoles();
    }

    private void PaletteHexTextBox_TextChanged(object sender, TextChangedEventArgs e)
    {
        if (!_paletteEditorReady || sender is not TextBox box ||
            box.DataContext is not ThemePaletteRow row ||
            !ThemePalette.TryNormalize(box.Text, out var normalized) ||
            normalized == row.Hex)
            return;

        try
        {
            ChangePaletteRole(row, normalized);
        }
        catch (Exception ex)
        {
            PaletteContrastText.Text = "Ошибка сохранения цвета: " + ex.Message;
        }
    }

    private void PalettePickColorButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not ThemePaletteRow row)
            return;

        var picker = new PaletteColorPickerWindow(this, row.Label, row.Hex);
        if (picker.ShowDialog() == true)
        {
            try { ChangePaletteRole(row, picker.SelectedHex); }
            catch (Exception ex)
            {
                AppDialog.Info(this, "Настройки цвета", ex.Message);
            }
        }
    }

    private void PaletteResetRoleButton_Click(object sender, RoutedEventArgs e)
    {
        if ((sender as Button)?.Tag is not ThemePaletteRow row)
            return;

        _settingsStore.ResetPaletteOverride(_editingDarkPalette, row.Key);
        row.Update(_editingDarkPalette ? row.Role.DarkDefault : row.Role.LightDefault);
        RefreshPaletteAfterEdit();
    }

    private void PaletteResetThemeButton_Click(object sender, RoutedEventArgs e)
    {
        if (!AppDialog.Confirm(this, "Стандартная палитра",
                "Восстановить все 26 стандартных цветов выбранной темы?",
                "Восстановить", "Отмена"))
            return;

        _settingsStore.ResetPaletteOverride(_editingDarkPalette);
        ReloadPaletteRoles();
        if (_editingDarkPalette == ThemeManager.IsDarkEffective)
            ThemeManager.Apply(ThemeManager.CurrentMode);
    }

    private void ChangePaletteRole(ThemePaletteRow row, string hex)
    {
        if (!ThemePalette.TryNormalize(hex, out var normalized))
            throw new ArgumentException("Укажите цвет в формате #RRGGBB.");

        _settingsStore.SavePaletteOverride(_editingDarkPalette, row.Key, normalized);
        row.Update(normalized);
        RefreshPaletteAfterEdit();
    }

    private void RefreshPaletteAfterEdit()
    {
        UpdatePaletteContrastStatus(_settingsStore.LoadPaletteOverrides(_editingDarkPalette));
        // Editing the other palette does not unexpectedly change the active UI.
        if (_editingDarkPalette == ThemeManager.IsDarkEffective)
            ThemeManager.Apply(ThemeManager.CurrentMode);
    }

    private void UpdatePaletteContrastStatus(IReadOnlyDictionary<string, string> colors)
    {
        (string Foreground, string Background, string Label, double Min)[] pairs =
        [
            ("AppTextBrush", "AppWindowBrush", "Основной текст / окно", 4.5),
            ("AppTextBrush", "AppInputBrush", "Текст / поле ввода", 4.5),
            ("AppMutedTextBrush", "AppPanelBrush", "Дополнительный текст / панель", 4.5),
            ("AppAccentTextBrush", "AppAccentBrush", "Текст / главная кнопка", 4.5),
            ("AppSelectionTextBrush", "AppSelectionBrush", "Текст / выделение", 4.5),
            ("AppFocusBrush", "AppPanelBrush", "Контур фокуса / панель", 3.0)
        ];

        var warnings = pairs
            .Select(p => (p.Label, Ratio: ThemePalette.Contrast(
                colors[p.Foreground], colors[p.Background]), p.Min))
            .Where(p => p.Ratio < p.Min)
            .Select(p => $"{p.Label}: {p.Ratio:F1}:1 (рекомендовано {p.Min:F1}:1)")
            .ToList();

        PaletteContrastText.Text = warnings.Count == 0
            ? "Контраст основных сочетаний достаточный."
            : "Внимание: слабый контраст — " + string.Join("; ", warnings) +
              ". Цвета сохранены без автоматической замены.";
    }
}
