using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// DPAPI-protected, bounded record of already delivered event=4 messages.
/// Transport acknowledgments are still sent for replays; no PushMe/Google
/// subscription or remote mail is ever deleted.
/// </summary>
internal sealed class PushProcessedEventStore
{
    private sealed record Entry(string Key, long UnixTime);
    private sealed record State(int Version, Entry[] Entries);
    private static readonly object Gate = new();
    private static readonly PushProcessedEventStore Default = new();
    private readonly string _path;
    private readonly Dictionary<string, long> _known = new(StringComparer.Ordinal);
    private bool _loaded;
    private const int Capacity = 4096;
    private const int MaxDays = 45;

    internal PushProcessedEventStore(string? directory = null)
    {
        directory ??= Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop");
        _path = Path.Combine(directory, "processed-push-events.dat");
    }

    internal static bool MarkDelivered(string account, string? messageId, byte[] mcsFrame,
        out bool remembered)
        => Default.Record(account, messageId, mcsFrame, out remembered);

    internal bool Record(string account, string? messageId, byte[] mcsFrame,
        out bool remembered)
    {
        // Exact mail identities take priority. A retransmission with a new
        // MCS persistent_id is the same letter, not a new notification.
        var key = EventKey(account, messageId, mcsFrame);
        lock (Gate)
        {
            remembered = false;
            try
            {
                LoadOnce();
                if (_known.ContainsKey(key))
                {
                    remembered = true;
                    return false;
                }
                _known[key] = DateTimeOffset.UtcNow.ToUnixTimeSeconds();
                Trim();
                Save();
                remembered = true;
                return true;
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                or CryptographicException or JsonException or InvalidDataException)
            {
                // A broken protected store must not suppress genuine incoming
                // mail; the caller's existing in-memory replay guard remains.
                PushDiagnostics.Record("MCS", "REPLAY_STORE_UNAVAILABLE");
                return true;
            }
        }
    }

    internal static string EventKey(string account, string? messageId, byte[] frame)
    {
        var kind = string.IsNullOrWhiteSpace(messageId) ? "mcs:" : "mail:";
        var value = string.IsNullOrWhiteSpace(messageId)
            ? PushWire.MessageIdentifier(frame) : messageId.Trim();
        var payload = Encoding.UTF8.GetBytes(
            account.Trim().ToLowerInvariant() + "\n" + kind + value);
        return Convert.ToHexString(SHA256.HashData(payload));
    }

    private void LoadOnce()
    {
        if (_loaded) return;
        if (!File.Exists(_path))
        {
            _loaded = true;
            return;
        }
        var protectedText = File.ReadAllText(_path, Encoding.UTF8);
        var plain = AuthorizationStore.Dpapi.Unprotect(protectedText);
        var state = JsonSerializer.Deserialize<State>(plain);
        if (state is null || state.Version != 1 || state.Entries is null)
            throw new InvalidDataException("Unknown processed-push-events state.");
        foreach (var item in state.Entries)
            if (item.Key.Length == 64 && item.Key.All(Uri.IsHexDigit))
                _known[item.Key] = item.UnixTime;
        Trim();
        _loaded = true;
    }

    private void Trim()
    {
        var cutoff = DateTimeOffset.UtcNow.AddDays(-MaxDays).ToUnixTimeSeconds();
        foreach (var key in _known.Where(e => e.Value < cutoff)
                     .Select(e => e.Key).ToArray())
            _known.Remove(key);
        if (_known.Count > Capacity)
        {
            foreach (var entry in _known.OrderBy(e => e.Value)
                         .Take(_known.Count - Capacity).ToArray())
                _known.Remove(entry.Key);
        }
    }

    private void Save()
    {
        Directory.CreateDirectory(Path.GetDirectoryName(_path)!);
        var state = new State(1, _known.Select(x => new Entry(x.Key, x.Value)).ToArray());
        var encrypted = AuthorizationStore.Dpapi.Protect(
            JsonSerializer.Serialize(state));
        var temp = _path + "." + Guid.NewGuid().ToString("N") + ".tmp";
        try
        {
            File.WriteAllText(temp, encrypted, new UTF8Encoding(false));
            File.Move(temp, _path, true);
        }
        finally
        {
            try { if (File.Exists(temp)) File.Delete(temp); }
            catch (IOException) { }
            catch (UnauthorizedAccessException) { }
        }
    }
}
