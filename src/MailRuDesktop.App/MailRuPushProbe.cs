using System.Buffers.Binary;
using System.Security.Authentication;
using System.Globalization;
using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Security;
using System.Net.Sockets;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// Native .NET, explicitly started investigation of the original Mail.ru Android
/// push transport. No background folder polling or automatic registration.
/// Each run creates an independent Google recipient and subscribes one mailbox.
/// No mail credentials, Google credentials or message payloads are persisted.
/// </summary>
internal sealed partial class MailRuPushProbe : IDisposable
{
    internal const string SenderId = "1098335887158";
    internal const string AndroidPackage = "ru.mail.mailapp";
    internal const string PublicApkCertSha1 = "daa4e5d1b055cdce8cdf297e412238a3476e70cf";
    private const string GoogleCheckin = "https://android.clients.google.com/checkin";
    private const string GoogleRegister = "https://android.clients.google.com/c2dm/register3";
    private const string SubscribeUrl = "https://push-me.mail.ru/api/v2/set_settings";
    private const string UnsubscribeUrl = "https://push-me.mail.ru/api/v2/unsubscribe_by_token";
    private const string AppUserAgent = "mobmail android 11.13.0.29089 ru.mail.mailapp";
    private static readonly TimeSpan RequestPause = TimeSpan.FromSeconds(5);
    private readonly HttpClient _http = new() { Timeout = TimeSpan.FromSeconds(25) };
    private static readonly SemaphoreSlim NetworkRequestGate = new(1, 1);
    private static DateTimeOffset _lastNetworkRequest = DateTimeOffset.MinValue;

    // One global pace for independent mailbox sessions. No requests are
    // started less than five seconds apart even during simultaneous reconnects.
    private static async Task PaceNetworkRequestAsync(CancellationToken token)
    {
        await NetworkRequestGate.WaitAsync(token);
        try
        {
            var remaining = RequestPause - (DateTimeOffset.UtcNow - _lastNetworkRequest);
            if (remaining > TimeSpan.Zero)
                await Task.Delay(remaining, token);
            _lastNetworkRequest = DateTimeOffset.UtcNow;
        }
        finally
        {
            NetworkRequestGate.Release();
        }
    }


    internal static Dictionary<string, object?> BuildSubscription(
        string login, string oauth, string googleToken, string androidId, string pushMeCommonId)
    {
        if (string.IsNullOrWhiteSpace(login) || string.IsNullOrWhiteSpace(oauth) ||
            string.IsNullOrWhiteSpace(googleToken))
            throw new ArgumentException("Не указаны данные испытательного аккаунта.");

        // Exact names and layout are recovered from MailCapabilitiesProvider,
        // ClientInfoProviderImpl and SubscriptionUseCase in the original APK.
        // All filter groups are explicitly disabled FOR THIS TEST.
        var filters = new Dictionary<string, object>
        {
            ["Folder"] = new { filterList = Array.Empty<int>(), enabled = false },
            ["SocialNetwork"] = new { excludeList = Array.Empty<int>(), enabled = false },
            ["SocialService"] = new { excludeList = Array.Empty<int>(), enabled = false }
        };
        var tz = TimeZoneInfo.Local.BaseUtcOffset;
        var zone = $"GMT{(tz < TimeSpan.Zero ? "-" : "+")}{Math.Abs(tz.Hours):00}{Math.Abs(tz.Minutes):00}";
        return new Dictionary<string, object?>
        {
            ["account"] = login.ToLowerInvariant(),
            ["application"] = "mail",
            ["platform"] = "android", // wire name of FIREBASE; not internal "fcm"
            ["token"] = googleToken,
            ["access_token"] = oauth,
            ["android_id"] = androidId,
            ["sdk_device_id"] = pushMeCommonId,
            ["settings"] = new
            {
                capabilities = new { can_mail = new { Filter = filters } },
                client = new Dictionary<string, string>
                {
                    ["name"] = AndroidPackage,
                    ["version"] = "15.107.0.148045",
                    ["platform"] = "Android 13",
                    ["type"] = "Smartphone",
                    ["lang"] = "ru_RU",
                    // Synthetic test device profile, NOT extracted from a real phone.
                    ["info"] = "Windows Research;0 cameras;360.0x800.0;NONE"
                },
                device_id = pushMeCommonId,
                client_time_zone = zone,
                badge = new { status = true, mode = "unread" }
            },
            ["status"] = 0
        };
    }

    // The literal wire-value profile that successfully delivered a single
    // new-mail event in v0.3.35. Scoped ONLY to the user-selected manual
    // test; the shared 32-account implementation remains unchanged.
    internal static Dictionary<string, object?> BuildVerifiedSingleAccountSubscription(
        string login, string oauth, string googleToken, ulong googleDeviceId,
        string temporaryDeviceId) =>
        BuildSubscription(login, oauth, googleToken,
            googleDeviceId.ToString(CultureInfo.InvariantCulture), temporaryDeviceId);

    internal static string ClassifySubscription(string response, string login)
    {
        // Do not print, persist or forward the response. It may contain account details.
        try
        {
            using var doc = JsonDocument.Parse(response);
            var root = doc.RootElement;
            if (root.ValueKind != JsonValueKind.Object ||
                !root.TryGetProperty("error", out var error) ||
                !error.TryGetProperty("code", out var code) ||
                !code.TryGetInt32(out var number))
                return "Ответ сервера не распознан";
            if (number != 0)
                return "Подписка отклонена сервером";
            if (!root.TryGetProperty("validate_result", out var validation))
                return "Сервер не подтвердил выбранный аккаунт";
            if (validation.ValueKind != JsonValueKind.Array)
                return "Ответ сервера не распознан";
            foreach (var item in validation.EnumerateArray())
            {
                if (item.ValueKind != JsonValueKind.Object ||
                    !item.TryGetProperty("account", out var name) ||
                    !string.Equals(name.GetString(), login, StringComparison.OrdinalIgnoreCase))
                    continue;
                if (item.TryGetProperty("is_valid", out var valid))
                    return valid.ValueKind == JsonValueKind.True
                        ? "ACCOUNT_ACCEPTED" : "Аккаунт не принят";
            }
            return "Сервер не подтвердил выбранный аккаунт";
        }
        catch (JsonException)
        {
            return "Ответ сервера не распознан";
        }
    }

    // Only fixed, non-personal technical categories are returned to the UI.
    // Never display exception.Message: HTTP libraries may include secret URLs.
    internal static string ClassifyNetworkError(HttpRequestException error)
    {
        if (error.InnerException is AuthenticationException ||
            error.HttpRequestError == HttpRequestError.SecureConnectionError)
            return "ошибка проверки сертификата TLS";
        return error.HttpRequestError switch
        {
            HttpRequestError.NameResolutionError => "ошибка DNS: адрес сервера не найден",
            HttpRequestError.ConnectionError => "не удалось соединиться с сервером",
            HttpRequestError.HttpProtocolError => "ошибка протокола HTTP",
            HttpRequestError.InvalidResponse => "неверный ответ HTTP",
            HttpRequestError.ResponseEnded => "сервер преждевременно закрыл соединение",
            _ => "сетевая ошибка (точный вид не определён)"
        };
    }

    public async Task RunAsync(
        string login, string oauth, Action<string> onState, Action onNewMail,
        CancellationToken cancellationToken, bool continuous = false,
        Func<byte[], bool>? shouldDeliver = null)
    {
        ArgumentException.ThrowIfNullOrWhiteSpace(login);
        ArgumentException.ThrowIfNullOrWhiteSpace(oauth);
        ArgumentNullException.ThrowIfNull(onState);
        ArgumentNullException.ThrowIfNull(onNewMail);

        string? temporaryToken = null;
        var registered = false;
        try
        {
            onState("Создание отдельного получателя уведомлений Google…");
            var identity = await CreateGoogleIdentityAsync(onState, cancellationToken);
            temporaryToken = identity.RegistrationToken;
            await Task.Delay(RequestPause, cancellationToken);

            await PaceNetworkRequestAsync(cancellationToken);
            using var socket = new TcpClient();
            await socket.ConnectAsync("mtalk.google.com", 5228, cancellationToken);
            using var stream = new SslStream(socket.GetStream(), false);
            await stream.AuthenticateAsClientAsync(
                new SslClientAuthenticationOptions { TargetHost = "mtalk.google.com" },
                cancellationToken);

            var loginBody = PushWire.LoginRequest(identity.DeviceId, identity.SecurityToken);
            await stream.WriteAsync(new byte[] { 41, 2 }, cancellationToken);
            await PushWire.WriteFrameAsync(stream, loginBody, cancellationToken);
            var version = await PushWire.ReadByteAsync(stream, cancellationToken);
            var tag = await PushWire.ReadByteAsync(stream, cancellationToken);
            var reply = await PushWire.ReadFrameAsync(stream, cancellationToken);
            if (!PushWire.ClassifyMcsLoginResponse(version, tag, reply).Accepted)
                throw new InvalidOperationException("Ответ Google MCS не подтвердил вход.");
            onState("Google: защищённый канал открыт (LOGIN_OK).");

            await Task.Delay(RequestPause, cancellationToken);
            onState("Подписка выбранного аккаунта: официальный сервер Mail.ru Prod (TLS)…");
            // Exact v0.3.35 single-account request profile. That historical
            // version was confirmed to receive a real new-mail event.
            // Use one temporary Google token, ONE mailbox and this temporary
            // trial ID, without changing the separate shared identity.
            var trialDevice = "mailru-windows-" +
                Convert.ToHexString(RandomNumberGenerator.GetBytes(12)).ToLowerInvariant();
            var json = JsonSerializer.Serialize(new[] {
                BuildVerifiedSingleAccountSubscription(login, oauth, temporaryToken,
                    identity.DeviceId, trialDevice)
            });
            using (var request = new HttpRequestMessage(HttpMethod.Post, SubscribeUrl))
            {
                request.Content = new StringContent(json, Encoding.UTF8, "application/json");
                request.Headers.TryAddWithoutValidation("User-Agent", AppUserAgent);
                await PaceNetworkRequestAsync(cancellationToken);
                using var response = await _http.SendAsync(request, cancellationToken);
                if (!response.IsSuccessStatusCode)
                {
                    onState("Mail.ru Prod: отказ HTTP " + (int)response.StatusCode);
                    return;
                }
                var raw = await response.Content.ReadAsStringAsync(cancellationToken);
                var state = ClassifySubscription(raw, login);
                onState("Mail.ru: " + state);
                if (state != "ACCOUNT_ACCEPTED")
                    return;
            }
            registered = true;
            onState(continuous
                ? "Подписка подтверждена. Постоянный приём уведомлений включён."
                : "Подписка подтверждена. Ожидание нового письма — до 3 минут.");
            using var watch = CancellationTokenSource.CreateLinkedTokenSource(cancellationToken);
            if (!continuous) watch.CancelAfter(TimeSpan.FromMinutes(3));
            try
            {
                var heartbeatAwaiting = false;
                while (!watch.Token.IsCancellationRequested)
                {
                    int messageTag;
                    byte[] data;
                    // An idle connection must not silently die forever: probe
                    // the MCS channel after four quiet minutes, require a reply.
                    using (var idle = CancellationTokenSource.CreateLinkedTokenSource(watch.Token))
                    {
                        if (continuous)
                            idle.CancelAfter(heartbeatAwaiting
                                ? TimeSpan.FromMinutes(1)
                                : TimeSpan.FromMinutes(4));
                        try
                        {
                            messageTag = await PushWire.ReadByteAsync(stream, idle.Token);
                            data = await PushWire.ReadFrameAsync(stream, idle.Token);
                        }
                        catch (OperationCanceledException) when (continuous &&
                            !watch.Token.IsCancellationRequested)
                        {
                            if (heartbeatAwaiting)
                                throw new IOException("MCS keepalive reply was not received");
                            await stream.WriteAsync(new byte[] { 0, 0 }, watch.Token);
                            heartbeatAwaiting = true;
                            continue;
                        }
                    }
                    heartbeatAwaiting = false;
                    if (messageTag == 0)
                    {
                        await stream.WriteAsync(new byte[] { 1, 0 }, watch.Token);
                        continue;
                    }
                    if (messageTag == 4)
                    {
                        onState("Google завершил соединение.");
                        break;
                    }
                    if (messageTag != 8)
                        continue;
                    if (continuous)
                    {
                        var acknowledgment = PushWire.SelectiveAcknowledgment(data);
                        if (acknowledgment is not null)
                        {
                            await stream.WriteAsync(new byte[] { 7 }, watch.Token);
                            await PushWire.WriteFrameAsync(stream, acknowledgment, watch.Token);
                        }
                    }
                    if (!PushWire.IsMailNewMessage(data))
                        continue;
                    if (continuous && shouldDeliver is not null && !shouldDeliver(data))
                        continue;
                    onState("MAILRU_NEW_MAIL_EVENT_RECEIVED=YES. Событие нового письма получено!");
                    onNewMail();
                    if (!continuous) return;
                }
            }
            catch (OperationCanceledException) when (!cancellationToken.IsCancellationRequested)
            {
                onState("Время ожидания истекло, событие нового письма не получено.");
            }
        }
        catch (OperationCanceledException) when (!cancellationToken.IsCancellationRequested)
        {
            onState("Превышено время ожидания ответа сервера.");
        }
        catch (OperationCanceledException)
        {
            onState("Проверка остановлена.");
        }
        catch (Exception ex)
        {
            // Do not forward raw exception text. It can contain URL parameters,
            // account addresses or auth material.
            onState("Ошибка этапа: " + (ex switch
            {
                HttpRequestException requestError => ClassifyNetworkError(requestError),
                AuthenticationException => "ошибка проверки сертификата TLS",
                SocketException => "соединение с Google",
                IOException => "соединение прервано",
                InvalidOperationException => "сервер не подтвердил операцию",
                _ => "непредвиденный ответ или формат"
            }));
        }
        finally
        {
            if (temporaryToken is not null)
            {
                // Never cancel cleanup with the user's Stop cancellation.
                try
                {
                    await Task.Delay(RequestPause);
                    using var cleanup = new CancellationTokenSource(TimeSpan.FromSeconds(20));
                    using var request = new HttpRequestMessage(HttpMethod.Post, UnsubscribeUrl);
                    request.Headers.TryAddWithoutValidation("User-Agent", AppUserAgent);
                    request.Content = new FormUrlEncodedContent(new Dictionary<string, string>
                    {
                        ["token"] = temporaryToken, ["application"] = "mail"
                    });
                    await PaceNetworkRequestAsync(cleanup.Token);
                    using var response = await _http.SendAsync(request, cleanup.Token);
                    var body = await response.Content.ReadAsStringAsync(cleanup.Token);
                    var state = ClassifyCleanup(body);
                    onState("Временная подписка: " +
                        (response.IsSuccessStatusCode && state ? "снята" : "удаление не подтверждено"));
                }
                catch
                {
                    onState("Временная подписка: удаление не подтверждено.");
                }
            }
            onState(registered ? "Проверка закончена." : "Проверка закончена без подтверждённой подписки.");
        }
    }

    private static bool ClassifyCleanup(string content)
    {
        try
        {
            using var doc = JsonDocument.Parse(content);
            return doc.RootElement.TryGetProperty("error", out var e) &&
                   e.TryGetProperty("code", out var c) &&
                   c.TryGetInt32(out var n) && n == 0;
        }
        catch (JsonException) { return false; }
    }

    internal async Task<GoogleIdentity> CreateGoogleIdentityAsync(
        Action<string> onState, CancellationToken ct)
    {
        var build = PushWire.Fields((1, 1UL));
        build = PushWire.Append(build, PushWire.TextField(2, "63.0.3234.0"), PushWire.VarintField(3, 1));
        var checkin = PushWire.Append(PushWire.VarintField(12, 3), PushWire.BytesField(13, build));
        var payload = PushWire.Append(PushWire.BytesField(4, checkin), PushWire.VarintField(14, 3), PushWire.VarintField(22, 0));
        using (var request = new HttpRequestMessage(HttpMethod.Post, GoogleCheckin))
        {
            request.Content = new ByteArrayContent(payload);
            request.Content.Headers.ContentType =
                new System.Net.Http.Headers.MediaTypeHeaderValue("application/x-protobuf");
            request.Headers.TryAddWithoutValidation("User-Agent", "Android-Checkin/1.0");
            await PaceNetworkRequestAsync(ct);
            using var response = await _http.SendAsync(request, ct);
            response.EnsureSuccessStatusCode();
            var body = await response.Content.ReadAsByteArrayAsync(ct);
            if (PushWire.GetUnsigned(body, 1) != 1)
                throw new InvalidOperationException("Google Checkin отказал.");
            var deviceId = PushWire.GetUnsigned(body, 7);
            var secret = PushWire.GetUnsigned(body, 8);
            if (deviceId == 0 || secret == 0)
                throw new InvalidOperationException("Google Checkin не предоставил данные.");
            onState("Google: регистрация получателя выполнена.");
            await Task.Delay(RequestPause, ct);
            var values = new Dictionary<string, string>
            {
                ["app"] = AndroidPackage,
                ["sender"] = SenderId,
                ["device"] = deviceId.ToString(CultureInfo.InvariantCulture),
                ["cert"] = PublicApkCertSha1,
                ["app_ver"] = "151070",
                ["X-subtype"] = SenderId,
                ["X-scope"] = "FCM",
                ["X-appid"] = Convert.ToHexString(RandomNumberGenerator.GetBytes(18)).ToLowerInvariant()
            };
            using var registration = new HttpRequestMessage(HttpMethod.Post, GoogleRegister);
            registration.Content = new FormUrlEncodedContent(values);
            registration.Headers.TryAddWithoutValidation("Authorization",
                $"AidLogin {deviceId}:{secret}");
            registration.Headers.TryAddWithoutValidation("User-Agent", "Android-GCM/1.5 (Windows Research)");
            await PaceNetworkRequestAsync(ct);
            using var reply = await _http.SendAsync(registration, ct);
            reply.EnsureSuccessStatusCode();
            var raw = await reply.Content.ReadAsStringAsync(ct);
            if (!raw.StartsWith("token=", StringComparison.Ordinal) || raw.Length < 18)
                throw new InvalidOperationException("Google не выдал токен отправителя.");
            onState("Google: токен отправителя Mail.ru получен (TOKEN_ISSUED).");
            return new GoogleIdentity(deviceId, secret, raw[6..].Trim());
        }
    }

    public void Dispose() => _http.Dispose();

    internal sealed record GoogleIdentity(ulong DeviceId, ulong SecurityToken, string RegistrationToken);
}

/// <summary>
/// Minimal bounded Google MCS/protobuf wire encoding and decoding. No dependencies
/// on Android and no messages, credentials or personal information are logged.
/// </summary>
internal static partial class PushWire
{
    internal static byte[] Fields(params (int Field, ulong Value)[] pairs) =>
        Append(pairs.Select(p => VarintField(p.Field, p.Value)).ToArray());

    internal static byte[] Append(params byte[][] chunks)
    {
        using var buffer = new MemoryStream();
        foreach (var chunk in chunks) buffer.Write(chunk);
        return buffer.ToArray();
    }

    internal static byte[] Varint(ulong value)
    {
        var b = new List<byte>(10);
        while (value > 127)
        {
            b.Add((byte)((value & 127) | 128));
            value >>= 7;
        }
        b.Add((byte)value);
        return b.ToArray();
    }

    internal static byte[] VarintField(int number, ulong value) =>
        Append(Varint((ulong)(number << 3)), Varint(value));

    internal static byte[] BytesField(int number, byte[] value) =>
        Append(Varint((ulong)((number << 3) | 2)), Varint((ulong)value.Length), value);

    internal static byte[] TextField(int number, string value) =>
        BytesField(number, Encoding.UTF8.GetBytes(value));

    internal static byte[] LoginRequest(ulong deviceId, ulong secret,
        IReadOnlyList<string>? receivedPendingIds = null)
    {
        var ordinaryLogin = Append(TextField(1, "chrome-63.0.3234.0"),
            TextField(2, "mcs.android.com"),
            TextField(3, deviceId.ToString(CultureInfo.InvariantCulture)),
            TextField(4, deviceId.ToString(CultureInfo.InvariantCulture)),
            TextField(5, secret.ToString(CultureInfo.InvariantCulture)),
            TextField(6, "android-" + deviceId.ToString("x", CultureInfo.InvariantCulture)),
            BytesField(8, Append(TextField(1, "new_vc"), TextField(2, "1"))),
            VarintField(12, 0), VarintField(14, 1),
            VarintField(16, 2), VarintField(17, 1));
        // mcs.proto LoginRequest field 10 = received_persistent_id.
        // Chromium retransmits receipts that the server has not confirmed
        // during an earlier MCS connection; do not log the raw IDs.
        if (receivedPendingIds is null || receivedPendingIds.Count == 0)
            return ordinaryLogin;
        return Append(ordinaryLogin,
            Append(receivedPendingIds.Select(id => TextField(10, id)).ToArray()));
    }

    private static ulong ReadVarint(ReadOnlySpan<byte> bytes, ref int index)
    {
        ulong value = 0;
        for (var shift = 0; shift < 70; shift += 7)
        {
            if (index >= bytes.Length || shift > 63)
                throw new InvalidDataException("Некорректное поле MCS.");
            var b = bytes[index++];
            value |= (ulong)(b & 127) << shift;
            if ((b & 128) == 0)
                return value;
        }
        throw new InvalidDataException("Некорректный размер MCS.");
    }

    internal static IEnumerable<(int Field, ulong Number, byte[]? Bytes)> Parse(byte[] payload)
    {
        if (payload.Length > 65536)
            throw new InvalidDataException("Слишком большое сообщение.");
        var index = 0;
        while (index < payload.Length)
        {
            var key = ReadVarint(payload, ref index);
            var field = (int)(key >> 3);
            var wire = (int)(key & 7);
            if (field < 1 || field > 2048)
                throw new InvalidDataException("Неизвестное поле MCS.");
            if (wire == 0)
                yield return (field, ReadVarint(payload, ref index), null);
            else if (wire == 1)
            {
                if (index + 8 > payload.Length) throw new InvalidDataException("Ошибка поля.");
                var v = BinaryPrimitives.ReadUInt64LittleEndian(payload.AsSpan(index, 8));
                index += 8;
                yield return (field, v, null);
            }
            else if (wire == 2)
            {
                var length = ReadVarint(payload, ref index);
                if (length > (ulong)(payload.Length - index))
                    throw new InvalidDataException("Ошибка длины поля.");
                var slice = payload.AsSpan(index, (int)length).ToArray();
                index += (int)length;
                yield return (field, 0, slice);
            }
            else if (wire == 5)
            {
                if (index + 4 > payload.Length) throw new InvalidDataException("Ошибка поля.");
                var v = BinaryPrimitives.ReadUInt32LittleEndian(payload.AsSpan(index, 4));
                index += 4;
                yield return (field, v, null);
            }
            else throw new InvalidDataException("Неподдерживаемое поле MCS.");
        }
    }

    internal static ulong GetUnsigned(byte[] bytes, int field) =>
        Parse(bytes).FirstOrDefault(p => p.Field == field).Number;

    internal static bool HasField(byte[] bytes, int field) =>
        Parse(bytes).Any(p => p.Field == field);

    // Chromium's original google_apis/gcm/protocol/mcs.proto:
    // LoginResponse(tag 3): field 1 = required id, field 3 = ErrorInfo.
    // ErrorInfo field 1 = required int32 code. Chromium's MCS client only
    // fails login when error is present AND error.code != 0.
    // Never expose the id, jid, error message, or raw protobuf to diagnostics.
    internal sealed record McsLoginResult(
        bool Accepted, bool IdPresent, bool ErrorPresent, int? ErrorCode);

    internal static McsLoginResult ClassifyMcsLoginResponse(
        int version, int tag, byte[] response)
    {
        var parsed = Parse(response).ToArray();
        var idPresent = parsed.Any(f => f.Field == 1 && f.Bytes is not null);
        var errorField = parsed.FirstOrDefault(f => f.Field == 3);
        var errorPresent = parsed.Any(f => f.Field == 3);
        int? errorCode = null;
        if (errorPresent && errorField.Bytes is not null)
        {
            var code = Parse(errorField.Bytes).FirstOrDefault(f => f.Field == 1);
            if (Parse(errorField.Bytes).Any(f => f.Field == 1 &&
                f.Bytes is null))
                errorCode = unchecked((int)code.Number);
        }
        var accepted = (version == 41 || version == 38) && tag == 3 &&
                       idPresent && (!errorPresent || errorCode == 0);
        return new McsLoginResult(accepted, idPresent, errorPresent, errorCode);
    }

    internal static string MessageIdentifier(byte[] bytes)
    {
        // MCS persistent_id is field 9, not the user-facing mailbox ID.
        var id = Parse(bytes).FirstOrDefault(x => x.Field == 9).Bytes;
        return id is { Length: > 0 }
            ? Convert.ToHexString(System.Security.Cryptography.SHA256.HashData(id))
            : Convert.ToHexString(System.Security.Cryptography.SHA256.HashData(bytes));
    }

    internal static byte[]? SelectiveAcknowledgment(byte[] bytes)
    {
        // MCS IqStanza(type=SET, extension.id=12 SelectiveAck).
        var id = Parse(bytes).FirstOrDefault(x => x.Field == 9).Bytes;
        if (id is null || id.Length == 0 || id.Length > 256) return null;
        var ids = BytesField(1, id);
        var extension = Append(VarintField(1, 12), BytesField(2, ids));
        return Append(VarintField(2, 1), TextField(3, "push-mail"),
            BytesField(7, extension));
    }

    internal static bool IsMailNewMessage(byte[] bytes)
    {
        var fields = Parse(bytes).ToArray();
        var category = fields.FirstOrDefault(p => p.Field == 5).Bytes;
        if (category is null || Encoding.UTF8.GetString(category) != "ru.mail.mailapp")
            return false;
        foreach (var record in fields.Where(p => p.Field == 7 && p.Bytes is not null))
        {
            var values = Parse(record.Bytes!).ToArray();
            var key = values.FirstOrDefault(p => p.Field == 1).Bytes;
            var val = values.FirstOrDefault(p => p.Field == 2).Bytes;
            if (key is not null && val is not null &&
                Encoding.UTF8.GetString(key) == "event" &&
                Encoding.UTF8.GetString(val) == "4")
                return true;
        }
        return false;
    }

    internal static async Task WriteFrameAsync(Stream stream, byte[] payload, CancellationToken token)
    {
        if (payload.Length > 65536) throw new InvalidDataException("Слишком большое сообщение.");
        var length = Varint((ulong)payload.Length);
        await stream.WriteAsync(length, token);
        await stream.WriteAsync(payload, token);
        await stream.FlushAsync(token);
    }

    internal static async Task<byte> ReadByteAsync(Stream stream, CancellationToken token)
    {
        var value = new byte[1];
        if (await stream.ReadAsync(value, token) != 1)
            throw new EndOfStreamException();
        return value[0];
    }

    internal static async Task<byte[]> ReadFrameAsync(Stream stream, CancellationToken token)
    {
        ulong length = 0;
        for (var n = 0; n < 5; n++)
        {
            var b = await ReadByteAsync(stream, token);
            length |= (ulong)(b & 127) << (n * 7);
            if ((b & 128) == 0)
            {
                if (length > 65536) throw new InvalidDataException("MCS: слишком большой кадр.");
                var payload = new byte[(int)length];
                await stream.ReadExactlyAsync(payload, token);
                return payload;
            }
        }
        throw new InvalidDataException("MCS: неправильная длина.");
    }
}
