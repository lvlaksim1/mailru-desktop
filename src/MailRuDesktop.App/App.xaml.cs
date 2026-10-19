using System.Threading;
using System.Windows;

namespace MailRuDesktop.App;

public partial class App : Application
{
    private const string InstanceMutexName = @"Local\MailRuDesktop_MainProcess_v1";
    private const string ActivationEventName = @"Local\MailRuDesktop_ActivateMain_v1";
    private Mutex? _instanceMutex;
    private EventWaitHandle? _activationEvent;
    private CancellationTokenSource? _activationStop;
    private bool _ownsInstance;

    protected override void OnStartup(StartupEventArgs e)
    {
        _instanceMutex = new Mutex(true, InstanceMutexName, out var firstInstance);
        _ownsInstance = firstInstance;
        if (!firstInstance)
        {
            try
            {
                using var signal = EventWaitHandle.OpenExisting(ActivationEventName);
                signal.Set();
            }
            catch (WaitHandleCannotBeOpenedException)
            {
                // Another instance is starting and has not opened its signal yet.
            }
            Shutdown();
            return;
        }

        _activationEvent = new EventWaitHandle(false,
            EventResetMode.AutoReset, ActivationEventName);
        _activationStop = new CancellationTokenSource();
        var activationEvent = _activationEvent;
        var stopToken = _activationStop.Token;
        _ = Task.Run(() =>
        {
            var waits = new WaitHandle[] { activationEvent, stopToken.WaitHandle };
            while (WaitHandle.WaitAny(waits) == 0)
            {
                _ = Dispatcher.BeginInvoke(new Action(() =>
                {
                    if (MainWindow is MailRuDesktop.App.MainWindow main)
                        main.ActivateFromSecondLaunch();
                }));
            }
        });

        PushMailToastService.Initialize();
        ThemeManager.Initialize();
        ThemeManager.Apply(new AppSettingsStore().LoadTheme());
        base.OnStartup(e);
    }

    protected override void OnExit(ExitEventArgs e)
    {
        _activationStop?.Cancel();
        _activationEvent?.Set();
        if (_ownsInstance)
            _instanceMutex?.ReleaseMutex();
        _instanceMutex?.Dispose();
        base.OnExit(e);
    }
}
