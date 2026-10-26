using System.IO;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App.Mailing;

/// <summary>Persistent confirmed-server-acceptance counts, independent of
/// optional per-letter mailing journal. Each message ID counts at most once.</summary>
internal sealed class MailingSenderCounters
{
    private readonly string _path;
    private readonly Dictionary<string, int> _sent = new(StringComparer.OrdinalIgnoreCase);
    private readonly HashSet<string> _counted = new(StringComparer.Ordinal);
    internal MailingSenderCounters(string? directory = null)
    {
        directory ??= Path.Combine(Environment.GetFolderPath(
            Environment.SpecialFolder.LocalApplicationData), "MailRuDesktop", "Mailing");
        _path = Path.Combine(directory, "sender-counts.json");
        if (!File.Exists(_path)) return;
        try
        {
            var snapshot = JsonSerializer.Deserialize<CounterSnapshot>(
                File.ReadAllText(_path, Encoding.UTF8))
                ?? throw new InvalidDataException("Пустой файл счётчиков.");
            foreach (var pair in snapshot.Counts)
            {
                if (pair.Value < 0) throw new InvalidDataException("Некорректный счётчик.");
                _sent[pair.Key] = pair.Value;
            }
            foreach (var id in snapshot.MessageIds) _counted.Add(id);
        }
        catch (Exception ex) when (ex is IOException or JsonException or
            UnauthorizedAccessException)
        {
            throw new IOException("Не удалось прочитать счётчики рассылки; исходные данные сохранены.", ex);
        }
    }

    internal int Get(string login) => _sent.GetValueOrDefault(login);

    internal bool RecordAccepted(string login, string messageId)
    {
        ArgumentException.ThrowIfNullOrWhiteSpace(login);
        ArgumentException.ThrowIfNullOrWhiteSpace(messageId);
        if (!_counted.Add(messageId)) return false;
        _sent[login] = checked(Get(login) + 1);
        try { Save(); }
        catch
        {
            _counted.Remove(messageId);
            if (--_sent[login] == 0) _sent.Remove(login);
            throw;
        }
        return true;
    }

    internal void Reset()
    {
        var oldCounts = new Dictionary<string, int>(_sent, StringComparer.OrdinalIgnoreCase);
        var oldIds = _counted.ToArray();
        _sent.Clear();
        _counted.Clear();
        try { Save(); }
        catch
        {
            foreach (var pair in oldCounts) _sent[pair.Key] = pair.Value;
            foreach (var id in oldIds) _counted.Add(id);
            throw;
        }
    }

    private void Save()
    {
        var directory = Path.GetDirectoryName(_path)!;
        Directory.CreateDirectory(directory);
        var temp = _path + "." + Guid.NewGuid().ToString("N") + ".tmp";
        try
        {
            var snapshot = new CounterSnapshot
            {
                Counts = _sent,
                MessageIds = _counted.ToList()
            };
            File.WriteAllText(temp, JsonSerializer.Serialize(snapshot),
                new UTF8Encoding(false));
            File.Move(temp, _path, true);
        }
        finally { if (File.Exists(temp)) File.Delete(temp); }
    }

    private sealed class CounterSnapshot
    {
        public Dictionary<string, int> Counts { get; set; } =
            new(StringComparer.OrdinalIgnoreCase);
        public List<string> MessageIds { get; set; } = [];
    }
}
