using System.Windows;

namespace MailRuDesktop.App;

public partial class App : Application
{
    protected override void OnStartup(StartupEventArgs e)
    {
        ThemeManager.Apply(new AppSettingsStore().LoadTheme());
        base.OnStartup(e);
    }
}
