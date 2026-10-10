using System.Globalization;
using System.Windows;
using CommunityToolkit.WinUI.Notifications;

namespace MailRuDesktop.App;

/// <summary>
/// Native, independently clickable Windows toasts. Activation carries a mailbox
/// and exact mail ID; no process-local "last notification" lookup is used.
/// </summary>
internal static class PushMailToastService
{
    private static readonly Queue<PushMailEvent> Pending = new();
    private static readonly object Gate = new();
    private static Action<PushMailEvent>? _onOpen;
    private static bool _initialized;

    internal static void Initialize()
    {
        if (_initialized) return;
        _initialized = true;
        ToastNotificationManagerCompat.OnActivated += args =>
        {
            try
            {
                var parsed = ToastArguments.Parse(args.Argument);
                string Get(string key) =>
                    parsed.TryGetValue(key, out var value) ? value : "";
                var account = Get("account");
                if (string.IsNullOrWhiteSpace(account)) return;
                int? folder = int.TryParse(Get("folder"), NumberStyles.Integer,
                    CultureInfo.InvariantCulture, out var parsedFolder)
                    ? parsedFolder : null;
                var mail = new PushMailEvent(account, Get("mailId"), folder,
                    Get("sender"), Get("subject"), Get("snippet"), null, null, null);
                var dispatcher = Application.Current?.Dispatcher;
                if (dispatcher is null)
                {
                    lock (Gate) Pending.Enqueue(mail);
                }
                else
                    _ = dispatcher.BeginInvoke(new Action(() => Deliver(mail)));
            }
            catch (Exception error)
            {
                DiagnosticLog.Write("push_toast_open", error.GetType().Name);
            }
        };
    }

    internal static void Attach(Action<PushMailEvent> onOpen)
    {
        lock (Gate) _onOpen = onOpen;
        while (true)
        {
            PushMailEvent? mail;
            lock (Gate)
            {
                if (Pending.Count == 0) return;
                mail = Pending.Dequeue();
            }
            onOpen(mail);
        }
    }

    internal static void Detach()
    {
        lock (Gate) _onOpen = null;
    }

    private static void Deliver(PushMailEvent mail)
    {
        Action<PushMailEvent>? target;
        lock (Gate)
        {
            target = _onOpen;
            if (target is null) Pending.Enqueue(mail);
        }
        target?.Invoke(mail);
    }

    internal static bool Show(PushMailEvent mail)
    {
        try
        {
            // The OS already identifies the application. Do not add a
            // redundant "Новое письмо — MailRu Desktop" heading.
            var toast = new ToastContentBuilder()
                .AddArgument("account", mail.Account)
                .AddArgument("mailId", mail.MessageId ?? "")
                .AddArgument("folder", mail.FolderId?.ToString(
                    CultureInfo.InvariantCulture) ?? "")
                .AddArgument("sender", mail.Sender ?? "")
                .AddArgument("subject", mail.Subject ?? "")
                .AddArgument("snippet", mail.Snippet ?? "")
                .AddText(PushMailEvent.MailboxLabel(mail))
                .AddText(PushMailEvent.SenderAndSubject(mail))
                .AddText(PushMailEvent.PreviewLine(mail));
            toast.Show();
            return true;
        }
        catch (Exception error)
        {
            DiagnosticLog.Write("push_toast_show", error.GetType().Name);
            return false;
        }
    }
}
