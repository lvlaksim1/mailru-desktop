using System.Buffers.Binary;
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
internal sealed class MailRuPushProbe : IDisposable
{
    internal const string SenderId = "1098335887158";
    internal const string AndroidPackage = "ru.mail.mailapp";
    internal const string PublicApkCertSha1 = "daa4e5d1b055cdce8cdf297e412238a3476e70cf";
    private const string GoogleCheckin = "https://android.clients.google.com/checkin";
    private const string GoogleRegister = "https://android.clients.google.com/c2dm/register3";
    private const string SubscribeUrl = "https://alt-push-me.mail.ru/api/v2/set_settings";
    private const string UnsubscribeUrl = "https://alt-push-me.mail.ru/api/v2/unsubscribe_by_token";
    private const string AppUserAgent = "mobmail android 11.13.0.29089 ru.mail.mailapp";
    private static readonly TimeSpan RequestPause = TimeSpan.FromSeconds(5);
    private readonly HttpClient _http = new() { Timeout = TimeSpan.FromSeconds(25) };

    internal static Dictionary<string, object?> BuildSubscription(
        string login, string oauth, string googleToken, ulong androidId, string trialDevice)
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
            ["android_id"] = androidId.ToString(CultureInfo.InvariantCulture),
            ["sdk_device_id"] = trialDevice,
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
                device_id = trialDevice,
                client_time_zone = zone,
                badge = new { status = true, mode = "unread" }
            },
            ["status"] = 0
        };
    }

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

    public async Task RunAsync(
        string login, string oauth, Action<string> onState, Action onNewMail,
        CancellationToken cancellationToken)
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
            if ((version != 41 && version != 38) || tag != 3 ||
                !PushWire.HasField(reply, 1) || PushWire.HasField(reply, 3))
                throw new InvalidOperationException("Google не подтвердил вход в канал уведомлений.");
            onState("Google: защищённый канал открыт (LOGIN_OK).");

            await Task.Delay(RequestPause, cancellationToken);
            onState("Подписка выбранного аккаунта на сервере Mail.ru…");
            var trialDevice = "mailru-windows-" + Convert.ToHexString(RandomNumberGenerator.GetBytes(12)).ToLowerInvariant();
            var json = JsonSerializer.Serialize(new[] {
                BuildSubscription(login, oauth, temporaryToken, identity.DeviceId, trialDevice)
            });
            using (var request = new HttpRequestMessage(HttpMethod.Post, SubscribeUrl))
            {
                request.Content = new StringContent(json, Encoding.UTF8, "application/json");
                request.Headers.TryAddWithoutValidation("User-Agent", AppUserAgent);
                using var response = await _http.SendAsync(request, cancellationToken);
                if (!response.IsSuccessStatusCode)
                    throw new InvalidOperationException("Mail.ru: отказ HTTP " + (int)response.StatusCode);
                var raw = await response.Content.ReadAsStringAsync(cancellationToken);
                var state = ClassifySubscription(raw, login);
                onState("Mail.ru: " + state);
                if (state != "ACCOUNT_ACCEPTED")
                    return;
            }
            registered = true;
            onState("Подписка подтверждена. Ожидание нового письма — до 3 минут.");
            using var watch = CancellationTokenSource.CreateLinkedTokenSource(cancellationToken);
            watch.CancelAfter(TimeSpan.FromMinutes(3));
            try
            {
                while (!watch.Token.IsCancellationRequested)
                {
                    var messageTag = await PushWire.ReadByteAsync(stream, watch.Token);
                    var data = await PushWire.ReadFrameAsync(stream, watch.Token);
                    if (messageTag == 0)
                    {
                        // Google MCS HeartbeatPing => HeartbeatAck.
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
                    if (PushWire.IsMailNewMessage(data))
                    {
                        onState("MAILRU_NEW_MAIL_EVENT_RECEIVED=YES. Событие нового письма получено!");
                        onNewMail();
                        return;
                    }
                    onState("Доставлено событие Google, но это не подтверждённое новое письмо.");
                }
            }
            catch (OperationCanceledException) when (!cancellationToken.IsCancellationRequested)
            {
                onState("Время ожидания истекло, событие нового письма не получено.");
            }
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
                HttpRequestException => "сетевое соединение",
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

    private async Task<GoogleIdentity> CreateGoogleIdentityAsync(
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

    private sealed record GoogleIdentity(ulong DeviceId, ulong SecurityToken, string RegistrationToken);
}

/// <summary>
/// Minimal bounded Google MCS/protobuf wire encoding and decoding. No dependencies
/// on Android and no messages, credentials or personal information are logged.
/// </summary>
internal static class PushWire
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

    internal static byte[] LoginRequest(ulong deviceId, ulong secret) =>
        Append(TextField(1, "chrome-63.0.3234.0"),
            TextField(2, "mcs.android.com"),
            TextField(3, deviceId.ToString(CultureInfo.InvariantCulture)),
            TextField(4, deviceId.ToString(CultureInfo.InvariantCulture)),
            TextField(5, secret.ToString(CultureInfo.InvariantCulture)),
            TextField(6, "android-" + deviceId.ToString("x", CultureInfo.InvariantCulture)),
            BytesField(8, Append(TextField(1, "new_vc"), TextField(2, "1"))),
            VarintField(12, 0), VarintField(14, 1),
            VarintField(16, 2), VarintField(17, 1));

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
