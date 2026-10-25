using System;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Text.Json;
using System.Threading;
using System.Threading.Tasks;

namespace MailRuDesktop.Protocol;

/// <summary>
/// Locally records unmodified HTTP addresses, headers, and streamed body bytes.
/// Includes credentials and session cookies. Files must never be published.
/// </summary>
public static class MailRuHttpWireLog
{
    private static readonly object Sync = new();
    private static string Folder => Path.Combine(
        Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
        "MailRuDesktop", "http-traffic");

    public sealed record Entry(DateTimeOffset When, string Id, string Method, string Url,
        string RequestHeaders, string? RequestMime, string? RequestFile,
        string? Account, string Status, string? ResponseHeaders,
        string? ResponseMime, string? ResponseFile, long DurationMs, string? Exception);

    internal sealed class Exchange
    {
        internal required DateTimeOffset When;
        internal required string Id;
        internal required string Method;
        internal required string Url;
        internal required string Headers;
        internal string? Account;
        internal string? RequestMime;
        internal string? RequestFile;
        internal readonly Stopwatch Watch = Stopwatch.StartNew();
    }

    private static string BodyPath(DateTimeOffset when, string id, string kind) =>
        Path.Combine(Folder, when.ToString("yyyyMMdd-HH", CultureInfo.InvariantCulture)
            + "-" + id + "-" + kind + ".bin");

    private static string HeaderLines(HttpHeaders headers) =>
        string.Join("\r\n", headers.Select(h => h.Key + ": " + string.Join(", ", h.Value)));

    private static void Append(Entry entry)
    {
        lock (Sync)
        {
            Directory.CreateDirectory(Folder);
            var path = Path.Combine(Folder, entry.When.ToString("yyyyMMdd-HH",
                CultureInfo.InvariantCulture) + ".jsonl");
            File.AppendAllText(path, JsonSerializer.Serialize(entry) + "\n",
                new UTF8Encoding(false));
        }
    }

    internal static Exchange? Begin(HttpRequestMessage request,
        Func<HttpRequestMessage, string?>? getAccount = null)
    {
        try
        {
            var now = DateTimeOffset.Now;
            var entry = new Exchange
            {
                When = now, Id = Guid.NewGuid().ToString("N"),
                Method = request.Method.Method,
                Url = request.RequestUri?.OriginalString ?? "(no URL)",
                Headers = HeaderLines(request.Headers),
                Account = getAccount?.Invoke(request)
            };
            if (request.Content is { } original)
            {
                entry.RequestMime = original.Headers.ContentType?.ToString();
                entry.RequestFile = BodyPath(now, entry.Id, "request");
                entry.Headers += "\r\n" + HeaderLines(original.Headers);
                request.Content = new MirrorContent(original, entry.RequestFile);
            }
            Append(new Entry(entry.When, entry.Id, entry.Method, entry.Url,
                entry.Headers, entry.RequestMime, entry.RequestFile, entry.Account,
                "REQUEST", null, null, null, 0, null));
            return entry;
        }
        catch { return null; } // A diagnostic write cannot block a mail request.
    }

    internal static void Completed(Exchange? entry, HttpResponseMessage response)
    {
        if (entry is null) return;
        try
        {
            string? path = null, mime = null;
            var headers = HeaderLines(response.Headers);
            if (response.Content is { } original)
            {
                mime = original.Headers.ContentType?.ToString();
                headers += "\r\n" + HeaderLines(original.Headers);
                path = BodyPath(entry.When, entry.Id, "response");
                response.Content = new MirrorContent(original, path);
            }
            entry.Watch.Stop();
            Append(new Entry(entry.When, entry.Id, entry.Method, entry.Url,
                entry.Headers, entry.RequestMime, entry.RequestFile, entry.Account,
                ((int)response.StatusCode).ToString(CultureInfo.InvariantCulture) +
                    " " + response.ReasonPhrase + " (HTTP/" + response.Version + ")",
                headers, mime, path, entry.Watch.ElapsedMilliseconds, null));
        }
        catch { }
    }

    internal static void Failed(Exchange? entry, Exception error)
    {
        if (entry is null) return;
        try
        {
            entry.Watch.Stop();
            Append(new Entry(entry.When, entry.Id, entry.Method, entry.Url,
                entry.Headers, entry.RequestMime, entry.RequestFile, entry.Account,
                "NETWORK_ERROR", null, null, null, entry.Watch.ElapsedMilliseconds,
                error.ToString()));
        }
        catch { }
    }

    public static async Task<HttpResponseMessage> SendTrackedAsync(
        HttpClient client, HttpRequestMessage request, HttpCompletionOption option,
        CancellationToken cancellationToken = default)
    {
        var entry = Begin(request);
        try
        {
            var response = await client.SendAsync(request, option, cancellationToken)
                .ConfigureAwait(false);
            Completed(entry, response);
            return response;
        }
        catch (Exception error)
        {
            Failed(entry, error);
            throw;
        }
    }

    private static string? GetQuery(string source, string key)
    {
        var start = source.IndexOf('?');
        if (start < 0) return null;
        foreach (var part in source[(start + 1)..].Split('&'))
        {
            var pos = part.IndexOf('=');
            if (pos < 1) continue;
            if (Uri.UnescapeDataString(part[..pos]).Equals(key,
                StringComparison.OrdinalIgnoreCase))
                return Uri.UnescapeDataString(part[(pos + 1)..].Replace('+', ' '));
        }
        return null;
    }

    private static string AccountOf(Entry entry)
    {
        if (!string.IsNullOrWhiteSpace(entry.Account)) return entry.Account;
        foreach (var name in new[] { "email", "login", "Login" })
        {
            var account = GetQuery(entry.Url, name);
            if (!string.IsNullOrWhiteSpace(account)) return account;
        }
        if (entry.Method.Equals("POST", StringComparison.OrdinalIgnoreCase)
            && entry.Url.Contains("/cgi-bin/auth", StringComparison.Ordinal))
        {
            try
            {
                if (entry.RequestFile != null && File.Exists(entry.RequestFile))
                    return GetQuery("?" + File.ReadAllText(entry.RequestFile), "Login")
                        ?? "(не указан)";
            }
            catch (IOException) { }
        }
        return "(не установлен для данного запроса)";
    }

    private static void Body(StringBuilder text, string title, string? path, string? mime)
    {
        text.Append(title).Append(" | Content-Type: ").AppendLine(mime ?? "(нет)");
        if (path is null)
        {
            text.AppendLine("(тело отсутствует)");
            return;
        }
        try
        {
            if (!File.Exists(path))
            {
                text.AppendLine("(ожидание передачи или чтения тела)");
                return;
            }
            var raw = File.ReadAllBytes(path);
            text.Append("Размер: ").Append(raw.Length).AppendLine(" байт");
            if (mime?.Contains("json", StringComparison.OrdinalIgnoreCase) == true ||
                mime?.StartsWith("text/", StringComparison.OrdinalIgnoreCase) == true ||
                mime?.Contains("xml", StringComparison.OrdinalIgnoreCase) == true ||
                mime?.Contains("form", StringComparison.OrdinalIgnoreCase) == true ||
                mime?.Contains("html", StringComparison.OrdinalIgnoreCase) == true)
                text.AppendLine("ТЕКСТ:").AppendLine(Encoding.UTF8.GetString(raw));
            text.AppendLine("ИСХОДНЫЕ БАЙТЫ В BASE64:");
            text.AppendLine(Convert.ToBase64String(raw));
        }
        catch (IOException ex) { text.AppendLine(ex.Message); }
        catch (UnauthorizedAccessException ex) { text.AppendLine(ex.Message); }
    }

    public static string CurrentHourReport()
    {
        var now = DateTimeOffset.Now;
        var path = Path.Combine(Folder, now.ToString("yyyyMMdd-HH",
            CultureInfo.InvariantCulture) + ".jsonl");
        var text = new StringBuilder("Полный сетевой журнал за текущий час: ")
            .Append(now.ToString("dd.MM.yyyy HH:00 zzz",
                CultureInfo.InvariantCulture)).AppendLine();
        text.AppendLine("Все заголовки, URL, данные авторизации и содержимое без обезличивания.");
        text.AppendLine("Файлы с исходными телами находятся в %LOCALAPPDATA%\\MailRuDesktop\\http-traffic.");
        try
        {
            if (!File.Exists(path)) return text.AppendLine(
                "Обращений к серверам в текущем часе пока нет.").ToString();
            foreach (var line in File.ReadLines(path, Encoding.UTF8))
            {
                if (string.IsNullOrWhiteSpace(line)) continue;
                Entry? item;
                try { item = JsonSerializer.Deserialize<Entry>(line); }
                catch (JsonException) { continue; }
                if (item is null) continue;
                text.AppendLine().AppendLine(new string('=', 70));
                text.Append(item.When.ToString("HH:mm:ss.fff",
                    CultureInfo.InvariantCulture)).Append(" ID: ")
                    .AppendLine(item.Id);
                text.Append("Аккаунт: ").AppendLine(AccountOf(item));
                text.Append(item.Method).Append(' ').AppendLine(item.Url);
                text.AppendLine("ЗАГОЛОВКИ ЗАПРОСА:").AppendLine(item.RequestHeaders);
                Body(text, "ТЕЛО ЗАПРОСА", item.RequestFile, item.RequestMime);
                if (item.Status == "REQUEST") continue;
                text.Append("ОТВЕТ: ").AppendLine(item.Status);
                text.Append("Длительность: ").Append(item.DurationMs).AppendLine(" мс");
                text.AppendLine("ЗАГОЛОВКИ ОТВЕТА:")
                    .AppendLine(item.ResponseHeaders ?? "(отсутствуют)");
                Body(text, "ТЕЛО ОТВЕТА", item.ResponseFile, item.ResponseMime);
                if (item.Exception is not null)
                    text.Append("ИСКЛЮЧЕНИЕ: ").AppendLine(item.Exception);
            }
        }
        catch (IOException ex) { text.Append("Ошибка чтения: ").AppendLine(ex.Message); }
        catch (UnauthorizedAccessException ex) { text.Append("Нет доступа: ").AppendLine(ex.Message); }
        return text.ToString();
    }

    private static FileStream? OpenMirror(string path)
    {
        try { return new FileStream(path, FileMode.Create, FileAccess.Write,
            FileShare.Read, 65536); }
        catch (IOException) { return null; }
        catch (UnauthorizedAccessException) { return null; }
    }

    /// <summary>
    /// Preserves original stream serialization and avoids consuming uploads
    /// before they are actually sent.
    /// </summary>
    private sealed class MirrorContent : HttpContent
    {
        private readonly HttpContent _original;
        private readonly string _path;

        public MirrorContent(HttpContent original, string path)
        {
            _original = original;
            _path = path;
            foreach (var header in original.Headers)
                Headers.TryAddWithoutValidation(header.Key, header.Value);
        }

        protected override bool TryComputeLength(out long length)
        {
            if (_original.Headers.ContentLength is long size)
            {
                length = size;
                return true;
            }
            length = 0;
            return false;
        }

        protected override Task SerializeToStreamAsync(Stream stream, TransportContext? context)
            => MirrorAsync(stream, CancellationToken.None);

        protected override Task SerializeToStreamAsync(Stream stream, TransportContext? context,
            CancellationToken cancellationToken) => MirrorAsync(stream, cancellationToken);

        private async Task MirrorAsync(Stream destination, CancellationToken ct)
        {
            using var mirrored = new WriteMirrorStream(destination, _path);
            await _original.CopyToAsync(mirrored, ct).ConfigureAwait(false);
            await mirrored.FlushAsync(ct).ConfigureAwait(false);
        }

        protected override async Task<Stream> CreateContentReadStreamAsync()
            => new ReadMirrorStream(await _original.ReadAsStreamAsync()
                .ConfigureAwait(false), _path);

        protected override async Task<Stream> CreateContentReadStreamAsync(CancellationToken ct)
            => new ReadMirrorStream(await _original.ReadAsStreamAsync(ct)
                .ConfigureAwait(false), _path);

        protected override void Dispose(bool disposing)
        {
            if (disposing) _original.Dispose();
            base.Dispose(disposing);
        }
    }

    private sealed class WriteMirrorStream : Stream
    {
        private readonly Stream _destination;
        private readonly FileStream? _mirror;
        internal WriteMirrorStream(Stream destination, string path)
        {
            _destination = destination;
            _mirror = OpenMirror(path);
        }
        public override bool CanRead => false;
        public override bool CanSeek => false;
        public override bool CanWrite => _destination.CanWrite;
        public override long Length => throw new NotSupportedException();
        public override long Position { get => throw new NotSupportedException();
            set => throw new NotSupportedException(); }
        public override void Flush() { _destination.Flush(); _mirror?.Flush(); }
        public override Task FlushAsync(CancellationToken ct) => _destination.FlushAsync(ct);
        public override int Read(byte[] b, int o, int c) => throw new NotSupportedException();
        public override long Seek(long o, SeekOrigin s) => throw new NotSupportedException();
        public override void SetLength(long s) => throw new NotSupportedException();
        public override void Write(byte[] b, int o, int c)
        {
            _destination.Write(b, o, c);
            try { _mirror?.Write(b, o, c); } catch (IOException) { }
        }
        public override void Write(ReadOnlySpan<byte> buffer)
        {
            _destination.Write(buffer);
            try { _mirror?.Write(buffer); } catch (IOException) { }
        }
        public override async Task WriteAsync(byte[] b,int o,int c,CancellationToken ct)
        {
            await _destination.WriteAsync(b,o,c,ct).ConfigureAwait(false);
            try { if(_mirror != null) await _mirror.WriteAsync(b,o,c,ct).ConfigureAwait(false); }
            catch (IOException) { }
        }
        public override async ValueTask WriteAsync(ReadOnlyMemory<byte> b,CancellationToken ct=default)
        {
            await _destination.WriteAsync(b,ct).ConfigureAwait(false);
            try { if(_mirror != null) await _mirror.WriteAsync(b,ct).ConfigureAwait(false); }
            catch (IOException) { }
        }
        protected override void Dispose(bool disposing)
        {
            if(disposing) _mirror?.Dispose();
            base.Dispose(disposing);
        }
    }

    private sealed class ReadMirrorStream : Stream
    {
        private readonly Stream _original;
        private readonly FileStream? _mirror;
        internal ReadMirrorStream(Stream original, string path)
        {
            _original=original;
            _mirror=OpenMirror(path);
        }
        public override bool CanRead => _original.CanRead;
        public override bool CanSeek => _original.CanSeek;
        public override bool CanWrite => false;
        public override long Length => _original.Length;
        public override long Position { get => _original.Position; set => _original.Position=value; }
        public override void Flush() => _original.Flush();
        public override int Read(byte[] b,int o,int c)
        {
            var n=_original.Read(b,o,c);
            try{ if(n>0) _mirror?.Write(b,o,n); }catch(IOException){}
            return n;
        }
        public override int Read(Span<byte> b)
        {
            var n=_original.Read(b);
            try{ if(n>0) _mirror?.Write(b[..n]); }catch(IOException){}
            return n;
        }
        public override async Task<int> ReadAsync(byte[] b,int o,int c,CancellationToken ct)
        {
            var n=await _original.ReadAsync(b,o,c,ct).ConfigureAwait(false);
            try{ if(n>0 && _mirror!=null) await _mirror.WriteAsync(b,o,n,ct).ConfigureAwait(false); }
            catch(IOException){}
            return n;
        }
        public override async ValueTask<int> ReadAsync(Memory<byte> b,CancellationToken ct=default)
        {
            var n=await _original.ReadAsync(b,ct).ConfigureAwait(false);
            try{ if(n>0 && _mirror!=null) await _mirror.WriteAsync(b[..n],ct).ConfigureAwait(false); }
            catch(IOException){}
            return n;
        }
        public override long Seek(long o,SeekOrigin s)=>_original.Seek(o,s);
        public override void SetLength(long s)=>throw new NotSupportedException();
        public override void Write(byte[] b,int o,int c)=>throw new NotSupportedException();
        protected override void Dispose(bool disposing)
        {
            if(disposing) { _mirror?.Dispose(); _original.Dispose(); }
            base.Dispose(disposing);
        }
    }
}
