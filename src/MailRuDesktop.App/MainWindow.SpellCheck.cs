using System.Windows;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private bool _loadingSpellCheckSetting;

    private void InitializeSpellCheckSettings()
    {
        _loadingSpellCheckSetting = true;
        try
        {
            var enabled = _settingsStore.LoadSpellCheckEnabled();
            ComposeSpellChecking.SetEnabled(enabled);
            SpellCheckEnabledCheckBox.IsChecked = enabled;
        }
        finally
        {
            _loadingSpellCheckSetting = false;
        }
    }

    private void SpellCheckEnabledCheckBox_Changed(object sender, RoutedEventArgs e)
    {
        if (_loadingSpellCheckSetting || !IsInitialized)
            return;

        var enabled = SpellCheckEnabledCheckBox.IsChecked == true;
        ComposeSpellChecking.SetEnabled(enabled);
        _settingsStore.SaveSpellCheckEnabled(enabled);
    }
}
