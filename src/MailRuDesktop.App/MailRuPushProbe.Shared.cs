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
    // Exact original APK DEX: PushMeApiImpl.unsubscribeByDeviceId().
    internal const string UnsubscribeAccountUrl =
        "https://push-me.mail.ru/api/v1/unsubscribe_by_device_id";
    private static readonly SemaphoreSlim SharedSubscriptionGate = new(1, 1);

    /// <summary>
    /// One protected Google identity and one MCS connection serve all authorized
    /// mailboxes. Only the account field inside an actual event=4 can route it.
    /// Closing the app or removing one mailbox never revokes Google identity.
    /// </summary>
    internal async Task RunSharedAsync(
        IReadOnlyDictionary<string, string> accounts,
        Action<string, string> onStatus,
        Action<string> onNewMail,
        Func<string, byte[], bool> shouldDeliver,
        CancellationToken cancellationToken)
    {
        if (accounts.Count == 0) return;
        var phase = "BEGIN";
        void Phase(string next, int? value = null)
        {
            phase = next;
            PushDiagnostics.Record("FLOW", next, value);
        }
        try
        {
        Phase("SESSION_START", accounts.Count);
        void ReportAll(string status)
        {
            foreach (var login in accounts.Keys) onStatus(login, status);
        }

        Phase("IDENTITY_LOAD");
        var store = new SharedGooglePushIdentityStore();
        var saved = store.Load();
        if (saved is not null) Phase("IDENTITY_REUSED");
        if (saved is not null)
        {
            foreach (var removed in SharedGooglePushIdentityStore.PendingAccountUnsubscriptions(
                         saved.SubscribedAccounts, accounts.Keys))
            {
                try
                {
                    if (await UnsubscribeAccountAsync(removed, cancellationToken))
                        onStatus(removed, "PushMe: адресная подписка удалена.");
                    else
                        onStatus(removed, "PushMe: отписка аккаунта ожидает повтора.");
                }
                catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
                {
                    throw;
                }
                catch (Exception)
                {
                    // Maintain the remaining account subscriptions; retry later.
                    onStatus(removed, "PushMe: отписка аккаунта ожидает повтора.");
                }
            }
            saved = store.Load(); // The per-account unsubscribe updates the persisted roster.
        }
        if (saved is null)
        {
            Phase("IDENTITY_CREATE");
            ReportAll("Регистрация одного Google-получателя для всех аккаунтов.");
            var registered = await CreateGoogleIdentityAsync(ReportAll, cancellationToken);
            saved = new SharedGooglePushIdentityStore.State(
                registered.DeviceId, registered.SecurityToken,
                registered.RegistrationToken, Array.Empty<string>(),
                SharedGooglePushIdentityStore.GeneratePushMeCommonId());
            store.Save(saved); // Persist before any server-side subscription.
            Phase("IDENTITY_CREATED");
        }

        // Migrate pre-v0.3.41 Windows identities without revoking or
        // recreating the working Google sender/connection.
        if (saved.PushMeCommonId is null)
        {
            saved = saved with
            {
                PushMeCommonId = SharedGooglePushIdentityStore.GeneratePushMeCommonId()
            };
            store.Save(saved);
            Phase("PUSHME_COMMON_ID_MIGRATED");
        }

        Phase("MCS_TCP_CONNECT");
        await PaceNetworkRequestAsync(cancellationToken);
        using var socket = new TcpClient();
        await socket.ConnectAsync("mtalk.google.com", 5228, cancellationToken);
        Phase("MCS_TCP_CONNECTED");
        using var stream = new SslStream(socket.GetStream(), false);
        Phase("MCS_TLS_HANDSHAKE");
        await stream.AuthenticateAsClientAsync(
            new SslClientAuthenticationOptions { TargetHost = "mtalk.google.com" },
            cancellationToken);
        Phase("MCS_TLS_OK");
        Phase("MCS_LOGIN_SEND");
        await stream.WriteAsync(new byte[] { 41, 2 }, cancellationToken);
        await PushWire.WriteFrameAsync(
            stream, PushWire.LoginRequest(saved.DeviceId, saved.SecurityToken),
            cancellationToken);
        var version = await PushWire.ReadByteAsync(stream, cancellationToken);
        var tag = await PushWire.ReadByteAsync(stream, cancellationToken);
        var reply = await PushWire.ReadFrameAsync(stream, cancellationToken);
        PushDiagnostics.Record("MCS", "LOGIN_VERSION", version);
        PushDiagnostics.Record("MCS", "LOGIN_TAG", tag);
        if ((version != 41 && version != 38) || tag != 3 ||
            !PushWire.HasField(reply, 1) || PushWire.HasField(reply, 3))
        {
            Phase("MCS_LOGIN_REJECTED");
            throw new InvalidOperationException("Google не подтвердил общий канал.");
        }
        Phase("MCS_LOGIN_OK");
        ReportAll("Google: один защищённый канал для всех аккаунтов (LOGIN_OK).");

        Phase("PUSHME_BUILD_REQUEST", accounts.Count);
        var deviceName = saved.PushMeCommonId!;
        var androidId = SharedGooglePushIdentityStore.AndroidIdFromCommonId(deviceName);
        var subscriptions = accounts.Select(item =>
            BuildSubscription(item.Key, item.Value, saved.RegistrationToken,
                androidId, deviceName)).ToArray();
        PushDiagnostics.Record("PUSHME", "COMMON_ID_SOURCE_ANDROID_COMPAT");
        PushDiagnostics.Record("PUSHME", "COMMON_ID_STRUCTURE_OK",
            SharedGooglePushIdentityStore.IsValidPushMeCommonId(deviceName) ? 1 : 0);
        var json = JsonSerializer.Serialize(subscriptions);
        HashSet<string> accepted;
        await SharedSubscriptionGate.WaitAsync(cancellationToken);
        try
        {
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
            Phase("PUSHME_HTTP_SEND", accounts.Count);
            await PaceNetworkRequestAsync(cancellationToken);
            using var response = await _http.SendAsync(request, cancellationToken);
            PushDiagnostics.Record("PUSHME", "HTTP_STATUS", (int)response.StatusCode);
            if (!response.IsSuccessStatusCode)
                throw new HttpRequestException(
                    "PushMe subscription HTTP failure", null, response.StatusCode);
            Phase("PUSHME_READ_RESPONSE");
            var raw = await response.Content.ReadAsStringAsync(cancellationToken);
            var registration = ParseSharedSubscriptionResponse(raw, accounts.Keys);
            if (registration.Error is not null)
            {
                Phase("PUSHME_RESPONSE_REJECTED");
                ReportAll("PushMe: " + registration.Error);
                throw new InvalidOperationException("PushMe subscription failed");
            }
            accepted = registration.Accepted;
            Phase("PUSHME_ACCEPTED", accepted.Count);
            PushDiagnostics.Record("PUSHME", "REJECTED_COUNT", accounts.Count - accepted.Count);
            foreach (var login in accounts.Keys)
                onStatus(login, accepted.Contains(login)
                    ? "Mail.ru: ACCOUNT_ACCEPTED"
                    : "Mail.ru: аккаунт отклонён (validate_result.is_valid=false).");
            if (accepted.Count == 0)
            {
                Phase("PUSHME_ZERO_ACCEPTED");
                throw new InvalidOperationException("PushMe rejected all mailboxes.");
            }

            // Original SDK NewSubscriptionRequest persists confirmed account
            // subscriptions only. The pre-POST roster is a crash-safety journal,
            // replaced after a successful response with confirmed entries.
            var associated = saved.SubscribedAccounts
                .Except(accounts.Keys, StringComparer.OrdinalIgnoreCase)
                .Concat(accepted).Distinct(StringComparer.OrdinalIgnoreCase).ToArray();
            store.Save(saved with { SubscribedAccounts = associated });
            foreach (var login in accepted)
                onStatus(login, "Подписка подтверждена. Постоянный приём уведомлений включён.");
            if (accepted.Count < accounts.Count)
                ReportAll("Часть почтовых аккаунтов требует повторной подписки.");
        }
        }
        finally
        {
            SharedSubscriptionGate.Release();
        }

            // Even partial acceptance is useful; retry nonaccepted accounts on the
            // next reconnection. Never deliver mail for unaccepted accounts.
            Phase("MCS_RECEIVE_STARTED");
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
                        {
                            Phase("MCS_KEEPALIVE_TIMEOUT");
                            throw new IOException("MCS keepalive timed out");
                        }
                        PushDiagnostics.Record("MCS", "KEEPALIVE_SENT");
                        await stream.WriteAsync(new byte[] { 0, 0 }, cancellationToken);
                        heartbeatAwaiting = true;
                        continue;
                    }
                }
                heartbeatAwaiting = false;
                if (messageTag == 0)
                {
                    PushDiagnostics.Record("MCS", "PING_FROM_SERVER");
                    await stream.WriteAsync(new byte[] { 1, 0 }, cancellationToken);
                    continue;
                }
                if (messageTag == 4)
                {
                    Phase("MCS_SERVER_CLOSE", messageTag);
                    throw new IOException("Google MCS closed the connection");
                }
                if (messageTag != 8)
                    PushDiagnostics.Record("MCS", "OTHER_FRAME_TAG", messageTag);
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
                PushDiagnostics.Record("MCS", "NEW_MAIL_EVENT");
                onStatus(account, "MAILRU_NEW_MAIL_EVENT_RECEIVED=YES.");
                onNewMail(account);
            }
        }
        catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
        {
            PushDiagnostics.Record("FLOW", "USER_CANCELLED");
            throw;
        }
        catch (Exception failure)
        {
            PushDiagnostics.Failure(phase, failure);
            throw;
        }
    }


    // Direct reconstruction of original APK PushMeApiImpl
    // parseSubscriptionResponseToResult(): code==0 and absent/empty
    // validate_result means OK. Only explicit is_valid=false rejects a mailbox.
    internal sealed record SharedSubscriptionOutcome(
        HashSet<string> Accepted, string? Error);

    internal static SharedSubscriptionOutcome ParseSharedSubscriptionResponse(
        string response, IEnumerable<string> requested)
    {
        var accepted = new HashSet<string>(requested, StringComparer.OrdinalIgnoreCase);
        try
        {
            using var doc = JsonDocument.Parse(response);
            var root = doc.RootElement;
            if (!root.TryGetProperty("error", out var error) ||
                !error.TryGetProperty("code", out var code) ||
                !code.TryGetInt32(out var codeValue))
                return new(accepted, "ответ сервера не соответствует PushMe SDK");
            PushDiagnostics.Record("PUSHME", "SERVER_API_CODE", codeValue);
            if (codeValue != 0)
            {
                // Exact original APK model has error.message. Do not log the
                // arbitrary original text, only its strict safe vocabulary.
                string? message = null;
                if (error.TryGetProperty("message", out var reason) &&
                    reason.ValueKind == JsonValueKind.String)
                    message = reason.GetString();
                var safe = PushDiagnostics.SafeServerReason(message);
                PushDiagnostics.Record("PUSHME", "SERVER_REASON_" + safe);
                return new(accepted, "ошибка сервера, код " + codeValue +
                    ", причина " + safe);
            }
            if (!root.TryGetProperty("validate_result", out var validation) ||
                validation.ValueKind == JsonValueKind.Null)
                return new(accepted, null);
            if (validation.ValueKind != JsonValueKind.Array)
                return new(accepted, "неверное поле validate_result");
            foreach (var item in validation.EnumerateArray())
            {
                if (item.ValueKind != JsonValueKind.Object ||
                    !item.TryGetProperty("account", out var login) ||
                    login.ValueKind != JsonValueKind.String ||
                    !item.TryGetProperty("is_valid", out var valid) ||
                    (valid.ValueKind != JsonValueKind.True &&
                     valid.ValueKind != JsonValueKind.False))
                    return new(accepted, "неверный элемент validate_result");
                if (valid.ValueKind == JsonValueKind.False)
                    accepted.Remove(login.GetString() ?? "");
            }
            return new(accepted, null);
        }
        catch (JsonException)
        {
            return new(accepted, "неверный формат ответа PushMe SDK");
        }
    }

    // Source of fields/endpoint: original APK
    // PushMeApiImpl.unsubscribeByDeviceId and UnsubscribeUseCase.invoke.
    // device_id is DeviceIdProvider.getDeviceId(), corresponding to the
    // value registered as settings.device_id, NOT FCM token/sdK_device_id.
    internal static Dictionary<string, string> BuildAccountUnsubscribeFields(
        string account, string pushMeCommonId)
    {
        if (string.IsNullOrWhiteSpace(account) ||
            !SharedGooglePushIdentityStore.IsValidPushMeCommonId(pushMeCommonId))
            throw new ArgumentException("Invalid account or PushMe CommonId.");
        return new(StringComparer.Ordinal)
        {
            ["account"] = account.ToLowerInvariant(),
            ["device_id"] = pushMeCommonId,
            ["application"] = "mail"
        };
    }

    // IMPORTANT:  HTTP failure must NOT clear the pending account from DPAPI.
    // A next connection retries, while other accounts remain registered.
    internal async Task<bool> UnsubscribeAccountAsync(
        string account, CancellationToken cancellationToken)
    {
        await SharedSubscriptionGate.WaitAsync(cancellationToken);
        try
        {
            var store = new SharedGooglePushIdentityStore();
            var saved = store.Load();
            if (saved is null ||
                !saved.SubscribedAccounts.Contains(account, StringComparer.OrdinalIgnoreCase))
                return true;
            using var request = new HttpRequestMessage(HttpMethod.Post, UnsubscribeAccountUrl);
            request.Headers.TryAddWithoutValidation("User-Agent", AppUserAgent);
            request.Content = new FormUrlEncodedContent(
                BuildAccountUnsubscribeFields(account,
                    saved.PushMeCommonId ??
                    // Pre-migration installations might have previously
                    // registered this account under the legacy identifier.
                    "mailru-windows-" + saved.DeviceId.ToString("x")));
            await PaceNetworkRequestAsync(cancellationToken);
            using var response = await _http.SendAsync(request, cancellationToken);
            if (!response.IsSuccessStatusCode) return false;
            var raw = await response.Content.ReadAsStringAsync(cancellationToken);
            if (!ClassifyCleanup(raw)) return false;
            store.Save(saved with
            {
                SubscribedAccounts = saved.SubscribedAccounts
                    .Where(a => !string.Equals(a, account, StringComparison.OrdinalIgnoreCase))
                    .ToArray()
            });
            return true;
        }
        finally
        {
            SharedSubscriptionGate.Release();
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
        await SharedSubscriptionGate.WaitAsync(cancellationToken);
        try
        {
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
        finally
        {
            SharedSubscriptionGate.Release();
        }
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
