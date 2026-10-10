using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// Persistent MCS transport receipts per Google device. Chromium's MCS client
/// sends not-yet-server-confirmed received_persistent_id values with the NEXT
/// LoginRequest. This is distinct from the mailbox-level replay guard.
/// Raw transport IDs are DPAPI-encrypted and never written to diagnostics.
/// </summary>
internal sealed class McsPendingReceiptStore
{
    private sealed record Receipt(string Id, long At);
    private sealed record Document(int Version, ulong DeviceId, Receipt[] Pending);
    private const int MaxPending = 768;
    private const int RetentionDays = 14;
    private readonly object _sync = new();
    private readonly string _path;
    private readonly ulong _deviceId;
    private readonly Dictionary<string, long> _pending = new(StringComparer.Ordinal);
    private bool _loaded;

    internal McsPendingReceiptStore(string recipientId, ulong deviceId,
        string? directoryOverride = null)
    {
        if (!SharedGooglePushIdentityStore.ValidRecipientId(recipientId) || deviceId == 0)
            throw new ArgumentException("Invalid recipient identity for MCS receipt state.");
        _deviceId = deviceId;
        var root = directoryOverride ?? Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");
        _path = Path.Combine(root, "mcs-receipts-" + recipientId + ".dat");
    }

    /// <summary>Protected pending receipts to retransmit on LoginRequest.</summary>
    internal IReadOnlyList<string> Snapshot()
    {
        lock (_sync)
        {
            try
            {
                Load();
                return _pending.Keys.ToArray();
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                or JsonException or CryptographicException or InvalidDataException)
            {
                PushDiagnostics.Record("MCS", "RECEIPTS_STORE_READ_ERROR");
                return [];
            }
        }
    }

    /// <summary>Record a frame before sending its selective transport ACK.</summary>
    internal bool Remember(byte[] data)
    {
        var field = PushWire.Parse(data).FirstOrDefault(x => x.Field == 9).Bytes;
        if (field is not { Length: > 0 and <= 256 }) return false;
        string id;
        try
        {
            id = new UTF8Encoding(false, true).GetString(field);
        }
        catch (DecoderFallbackException) { return false; }
        if (id.Any(c => c < '!' || c > '~')) return false;

        lock (_sync)
        {
            try
            {
                Load();
                if (_pending.ContainsKey(id)) return true;
                _pending[id] = DateTimeOffset.UtcNow.ToUnixTimeSeconds();
                Trim();
                Save();
                return true;
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                or JsonException or CryptographicException or InvalidDataException)
            {
                PushDiagnostics.Record("MCS", "RECEIPTS_STORE_WRITE_ERROR");
                return false;
            }
        }
    }

    /// <summary>
    /// The server acknowledges receipt of the LoginRequest (stream ID 1).
    /// Only then can ids retransmitted INSIDE that login be discarded.
    /// This does not prove acceptance of previous individual SelectiveAcks.
    /// </summary>
    internal void ConfirmLogin(IReadOnlyList<string> submitted)
    {
        if (submitted.Count == 0) return;
        lock (_sync)
        {
            try
            {
                Load();
                var changed = false;
                foreach (var id in submitted)
                    changed |= _pending.Remove(id);
                if (changed) Save();
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                or JsonException or CryptographicException or InvalidDataException)
            {
                PushDiagnostics.Record("MCS", "RECEIPTS_STORE_WRITE_ERROR");
            }
        }
    }

    private void Load()
    {
        if (_loaded) return;
        if (!File.Exists(_path))
        {
            _loaded = true;
            return;
        }
        var protectedText = File.ReadAllText(_path, Encoding.UTF8);
        var decoded = AuthorizationStore.Dpapi.Unprotect(protectedText);
        var document = JsonSerializer.Deserialize<Document>(decoded);
        if (document is null || document.Version != 1 ||
            document.Pending is null)
            throw new InvalidDataException("Unsupported MCS receipts state.");
        // Never send receipts belonging to an earlier Google device identity.
        if (document.DeviceId != _deviceId)
        {
            PushDiagnostics.Record("MCS", "RECEIPTS_DEVICE_CHANGED");
            _loaded = true;
            return;
        }
        foreach (var entry in document.Pending)
            if (!string.IsNullOrEmpty(entry.Id) && entry.Id.Length <= 256 &&
                entry.Id.All(c => c >= '!' && c <= '~'))
                _pending[entry.Id] = entry.At;
        Trim();
        _loaded = true;
    }

    private void Trim()
    {
        var olderThan = DateTimeOffset.UtcNow.AddDays(-RetentionDays).ToUnixTimeSeconds();
        foreach (var item in _pending.Where(x => x.Value < olderThan)
                     .Select(x => x.Key).ToArray()) _pending.Remove(item);
        foreach (var item in _pending.OrderBy(x => x.Value)
                     .Take(Math.Max(0, _pending.Count - MaxPending)).ToArray())
            _pending.Remove(item.Key);
    }

    private void Save()
    {
        Directory.CreateDirectory(Path.GetDirectoryName(_path)!);
        var entries = _pending.Select(x => new Receipt(x.Key, x.Value)).ToArray();
        var protectedText = AuthorizationStore.Dpapi.Protect(
            JsonSerializer.Serialize(new Document(1, _deviceId, entries)));
        var tmp = _path + "." + Guid.NewGuid().ToString("N") + ".tmp";
        try
        {
            File.WriteAllText(tmp, protectedText, new UTF8Encoding(false));
            File.Move(tmp, _path, true);
        }
        finally
        {
            try { if (File.Exists(tmp)) File.Delete(tmp); }
            catch (IOException) { }
            catch (UnauthorizedAccessException) { }
        }
    }
}
