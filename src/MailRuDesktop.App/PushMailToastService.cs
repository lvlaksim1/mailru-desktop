using System.Globalization;
using System.Windows;
using Microsoft.Toolkit.Uwp.Notifications;

namespace MailRuDesktop.App;

/// <summary>
/// Native, independently clickable Windows toasts. Activation carries a mailbox
/// and exact mail ID; no process-local "last notification" lookup is used.
/// </summary>
internal static class PushMailToastService
{
    private sealed record ToastRequest(PushMailEvent Mail, bool Delete);
    private static readonly Queue<ToastRequest> Pending = new();
    private static readonly object Gate = new();
    private static Action<PushMailEvent>? _onOpen;
    private static Action<PushMailEvent>? _onDelete;
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
                var delete = string.Equals(Get("action"), "delete",
                    StringComparison.Ordinal);
                var dispatcher = Application.Current?.Dispatcher;
                if (dispatcher is null)
                {
                    lock (Gate) Pending.Enqueue(new ToastRequest(mail, delete));
                }
                else
                    _ = dispatcher.BeginInvoke(new Action(() => Deliver(mail, delete)));
            }
            catch (Exception error)
            {
                DiagnosticLog.Write("push_toast_open", error.GetType().Name);
            }
        };
    }

    internal static void Attach(Action<PushMailEvent> onOpen,
        Action<PushMailEvent> onDelete)
    {
        lock (Gate)
        {
            _onOpen = onOpen;
            _onDelete = onDelete;
        }
        while (true)
        {
            ToastRequest? request;
            lock (Gate)
            {
                if (Pending.Count == 0) return;
                request = Pending.Dequeue();
            }
            if (request.Delete) onDelete(request.Mail);
            else onOpen(request.Mail);
        }
    }

    internal static void Detach()
    {
        lock (Gate)
        {
            _onOpen = null;
            _onDelete = null;
        }
    }

    private static void Deliver(PushMailEvent mail, bool delete)
    {
        Action<PushMailEvent>? target;
        lock (Gate)
        {
            target = delete ? _onDelete : _onOpen;
            if (target is null) Pending.Enqueue(new ToastRequest(mail, delete));
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
                .AddText(PushMailEvent.MailboxLabel(mail) +
                    (mail.ReceivedAt is DateTimeOffset received
                        ? " · " + received.ToLocalTime().ToString("HH:mm", CultureInfo.CurrentCulture)
                        : ""))
                .AddText(PushMailEvent.SenderAndSubject(mail))
                .AddText(PushMailEvent.PreviewLine(mail));
            if (!string.IsNullOrWhiteSpace(mail.MessageId))
            {
                toast.AddButton(new ToastButton()
                    .SetContent("Удалить")
                    .AddArgument("action", "delete")
                    .AddArgument("account", mail.Account)
                    .AddArgument("mailId", mail.MessageId));
            }
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
