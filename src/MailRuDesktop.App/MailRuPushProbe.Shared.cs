using System.IO;
using System.Net.Http;
using System.Net.Security;
using System.Net.Sockets;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

internal sealed partial class MailRuPushProbe
{
    /// <summary>
    /// One protected Google identity and one MCS connection serve all authorized
    /// mailboxes. Only the account field inside an actual event=4 can route it.
    /// Closing the app never revokes the identity; explicit disable/removal does.
    /// </summary>
    internal async Task RunSharedAsync(
        IReadOnlyDictionary<string, string> accounts,
        Action<string, string> onStatus,
        Action<string> onNewMail,
        Func<string, byte[], bool> shouldDeliver,
        CancellationToken cancellationToken)
    {
        if (accounts.Count == 0) return;
        void ReportAll(string status)
        {
            foreach (var login in accounts.Keys) onStatus(login, status);
        }

        var store = new SharedGooglePushIdentityStore();
        var saved = store.Load();
        if (saved is not null && SharedGooglePushIdentityStore.NeedsRotation(
                saved.SubscribedAccounts, accounts.Keys))
        {
            ReportAll("Состав аккаунтов изменился; защищённая смена общего получателя.");
            if (!await UnsubscribeSharedAsync(cancellationToken))
                throw new IOException("Shared token revocation was not confirmed.");
            saved = null;
        }
        if (saved is null)
        {
            ReportAll("Регистрация одного Google-получателя для всех аккаунтов.");
            var registered = await CreateGoogleIdentityAsync(ReportAll, cancellationToken);
            saved = new SharedGooglePushIdentityStore.State(
                registered.DeviceId, registered.SecurityToken,
                registered.RegistrationToken, Array.Empty<string>());
            store.Save(saved); // Persist before any server-side subscription.
        }

        await PaceNetworkRequestAsync(cancellationToken);
        using var socket = new TcpClient();
        await socket.ConnectAsync("mtalk.google.com", 5228, cancellationToken);
        using var stream = new SslStream(socket.GetStream(), false);
        await stream.AuthenticateAsClientAsync(
            new SslClientAuthenticationOptions { TargetHost = "mtalk.google.com" },
            cancellationToken);
        await stream.WriteAsync(new byte[] { 41, 2 }, cancellationToken);
        await PushWire.WriteFrameAsync(
            stream, PushWire.LoginRequest(saved.DeviceId, saved.SecurityToken),
            cancellationToken);
        var version = await PushWire.ReadByteAsync(stream, cancellationToken);
        var tag = await PushWire.ReadByteAsync(stream, cancellationToken);
        var reply = await PushWire.ReadFrameAsync(stream, cancellationToken);
        if ((version != 41 && version != 38) || tag != 3 ||
            !PushWire.HasField(reply, 1) || PushWire.HasField(reply, 3))
            throw new InvalidOperationException("Google не подтвердил общий канал.");
        ReportAll("Google: один защищённый канал для всех аккаунтов (LOGIN_OK).");

        var deviceName = "mailru-windows-" + saved.DeviceId.ToString("x");
        var subscriptions = accounts.Select(item =>
            BuildSubscription(item.Key, item.Value, saved.RegistrationToken,
                saved.DeviceId, deviceName)).ToArray();
        var json = JsonSerializer.Serialize(subscriptions);
        // Persist the prospective account roster BEFORE the network write.
        // If Windows exits after PushMe accepts the POST, the next startup
        // still knows which accounts might be bound to this Google token.
        store.Save(saved with
        {
            SubscribedAccounts = saved.SubscribedAccounts.Concat(accounts.Keys)
                .Distinct(StringComparer.OrdinalIgnoreCase).ToArray()
        });
        using (var request = new HttpRequestMessage(HttpMethod.Post, SubscribeUrl))
        {
            request.Content = new StringContent(json, Encoding.UTF8, "application/json");
            request.Headers.TryAddWithoutValidation("User-Agent", AppUserAgent);
            await PaceNetworkRequestAsync(cancellationToken);
            using var response = await _http.SendAsync(request, cancellationToken);
            if (!response.IsSuccessStatusCode)
                throw new HttpRequestException(
                    "PushMe subscription HTTP failure", null, response.StatusCode);
            var raw = await response.Content.ReadAsStringAsync(cancellationToken);
            var accepted = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
            foreach (var login in accounts.Keys)
            {
                var outcome = ClassifySubscription(raw, login);
                onStatus(login, "Mail.ru: " + outcome);
                if (outcome == "ACCOUNT_ACCEPTED") accepted.Add(login);
            }
            if (accepted.Count == 0)
                throw new InvalidOperationException("PushMe не подтвердил аккаунты.");

            // Keep earlier server-confirmed subscriptions (all still desired),
            // plus newly accepted ones. Removed accounts force rotation above.
            var associated = saved.SubscribedAccounts.Concat(accepted)
                .Distinct(StringComparer.OrdinalIgnoreCase).ToArray();
            store.Save(saved with { SubscribedAccounts = associated });
            foreach (var login in accepted)
                onStatus(login, "Подписка подтверждена. Постоянный приём уведомлений включён.");
            if (accepted.Count < accounts.Count)
                ReportAll("Часть почтовых аккаунтов требует повторной подписки.");

            // Even partial acceptance is useful; retry nonaccepted accounts on the
            // next reconnection. Never deliver mail for unaccepted accounts.
            var heartbeatAwaiting = false;
            while (!cancellationToken.IsCancellationRequested)
            {
                int messageTag;
                byte[] data;
                using (var idle = CancellationTokenSource.CreateLinkedTokenSource(cancellationToken))
                {
                    idle.CancelAfter(heartbeatAwaiting
                        ? TimeSpan.FromMinutes(1) : TimeSpan.FromMinutes(4));
                    try
                    {
                        messageTag = await PushWire.ReadByteAsync(stream, idle.Token);
                        data = await PushWire.ReadFrameAsync(stream, idle.Token);
                    }
                    catch (OperationCanceledException)
                        when (!cancellationToken.IsCancellationRequested)
                    {
                        if (heartbeatAwaiting)
                            throw new IOException("MCS keepalive timed out");
                        await stream.WriteAsync(new byte[] { 0, 0 }, cancellationToken);
                        heartbeatAwaiting = true;
                        continue;
                    }
                }
                heartbeatAwaiting = false;
                if (messageTag == 0)
                {
                    await stream.WriteAsync(new byte[] { 1, 0 }, cancellationToken);
                    continue;
                }
                if (messageTag == 4)
                    throw new IOException("Google MCS closed the connection");
                if (messageTag != 8) continue;

                var ack = PushWire.SelectiveAcknowledgment(data);
                if (ack is not null)
                {
                    await stream.WriteAsync(new byte[] { 7 }, cancellationToken);
                    await PushWire.WriteFrameAsync(stream, ack, cancellationToken);
                }
                var account = PushWire.NewMailAccount(data);
                if (account is null || !accepted.Contains(account) ||
                    !shouldDeliver(account, data))
                    continue;
                onStatus(account, "MAILRU_NEW_MAIL_EVENT_RECEIVED=YES.");
                onNewMail(account);
            }
        }
    }

    /// <summary>
    /// Token-wide revocation is only used when ALL subscribers are being
    /// retired (user disables push) or a removed account requires rotation.
    /// Never call this for an ordinary access-token refresh or app shutdown.
    /// </summary>
    internal async Task<bool> UnsubscribeSharedAsync(CancellationToken cancellationToken)
    {
        var store = new SharedGooglePushIdentityStore();
        var saved = store.Load();
        if (saved is null) return true;
        using var request = new HttpRequestMessage(HttpMethod.Post, UnsubscribeUrl);
        request.Headers.TryAddWithoutValidation("User-Agent", AppUserAgent);
        request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
        {
            ["token"] = saved.RegistrationToken,
            ["application"] = "mail"
        });
        await PaceNetworkRequestAsync(cancellationToken);
        using var response = await _http.SendAsync(request, cancellationToken);
        if (!response.IsSuccessStatusCode) return false;
        var body = await response.Content.ReadAsStringAsync(cancellationToken);
        if (!ClassifyCleanup(body)) return false;
        store.Delete();
        return true;
    }
}

internal static partial class PushWire
{
    /// <summary>
    /// Fail closed when account is absent: a shared receiver must never
    /// deliver a message to whichever mailbox happens to be active.
    /// </summary>
    internal static string? NewMailAccount(byte[] bytes)
    {
        if (!IsMailNewMessage(bytes)) return null;
        foreach (var field in Parse(bytes))
        {
            if (field.Field != 7 || field.Bytes is null) continue;
            var values = Parse(field.Bytes).ToArray();
            var key = values.FirstOrDefault(x => x.Field == 1).Bytes;
            var value = values.FirstOrDefault(x => x.Field == 2).Bytes;
            if (key is null || value is null ||
                Encoding.UTF8.GetString(key) != "account") continue;
            var account = Encoding.UTF8.GetString(value).Trim();
            return string.IsNullOrWhiteSpace(account) ? null : account;
        }
        return null;
    }
}
