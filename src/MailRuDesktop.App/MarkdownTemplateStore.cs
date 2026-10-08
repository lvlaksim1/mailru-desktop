using System.IO;
using System.Text;
using System.Text.Json;

namespace MailRuDesktop.App;

/// <summary>
/// Every top-level .md file in Templates is an independently editable template.
/// Plain Markdown is a valid body-only template; optional front matter stores
/// the subject and attachments. The filename (without extension) is its name.
/// </summary>
internal sealed class MarkdownTemplateStore
{
    public string DirectoryPath { get; private set; }

    private const string LegacyMarker = ".legacy-imported";

    public MarkdownTemplateStore(string? initialDirectory = null)
    {
        DirectoryPath = Path.GetFullPath(initialDirectory ?? Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "Templates"));
        Directory.CreateDirectory(DirectoryPath);
    }

    // Copy first and switch only when every source file has been preserved.
    // Existing template files in the target must not be silently overwritten.
    public void ChangeDirectory(string destination)
    {
        if (string.IsNullOrWhiteSpace(destination))
            throw new ArgumentException("Укажите папку шаблонов.", nameof(destination));

        var next = Path.GetFullPath(destination.Trim());
        var current = Path.GetFullPath(DirectoryPath);
        if (string.Equals(next, current, StringComparison.OrdinalIgnoreCase))
            return;

        var rootWithSeparator = current.TrimEnd(Path.DirectorySeparatorChar) +
                                Path.DirectorySeparatorChar;
        var nextWithSeparator = next.TrimEnd(Path.DirectorySeparatorChar) +
                                Path.DirectorySeparatorChar;
        if (nextWithSeparator.StartsWith(rootWithSeparator, StringComparison.OrdinalIgnoreCase) ||
            rootWithSeparator.StartsWith(nextWithSeparator, StringComparison.OrdinalIgnoreCase))
            throw new IOException("Нельзя выбрать родительскую или вложенную папку шаблонов.");

        var paths = Directory.EnumerateFiles(current, "*", SearchOption.AllDirectories)
            .Select(path => (Source: path, Relative: Path.GetRelativePath(current, path)))
            .ToArray();

        foreach (var file in paths)
        {
            var target = Path.Combine(next, file.Relative);
            if (File.Exists(target))
                throw new IOException($"В выбранной папке уже существует файл: {file.Relative}. Слияние не выполнено.");
        }

        Directory.CreateDirectory(next);
        foreach (var file in paths)
        {
            var target = Path.Combine(next, file.Relative);
            Directory.CreateDirectory(Path.GetDirectoryName(target)!);
            File.Copy(file.Source, target, overwrite: false);
        }

        // The previous directory is intentionally kept as a backup.
        DirectoryPath = next;
    }

    public IReadOnlyList<SavedMailTemplate> LoadAll()
    {
        Directory.CreateDirectory(DirectoryPath);
        var results = new List<SavedMailTemplate>();
        foreach (var path in Directory.EnumerateFiles(DirectoryPath)
                     .Where(path => Path.GetExtension(path).Equals(".md", StringComparison.OrdinalIgnoreCase))
                     .OrderBy(path => path, StringComparer.CurrentCultureIgnoreCase))
        {
            try
            {
                using var stream = new FileStream(path, FileMode.Open, FileAccess.Read,
                    FileShare.ReadWrite | FileShare.Delete);
                using var reader = new StreamReader(stream, Encoding.UTF8, true);
                var content = reader.ReadToEnd();
                results.Add(Parse(Path.GetFileNameWithoutExtension(path), content));
            }
            catch (IOException ex)
            {
                DiagnosticLog.Write("template_read", $"{Path.GetFileName(path)}: {ex.Message}");
            }
            catch (UnauthorizedAccessException ex)
            {
                DiagnosticLog.Write("template_read", $"{Path.GetFileName(path)}: {ex.Message}");
            }
        }

        return results;
    }

    public void ImportLegacyOnce(IEnumerable<SavedMailTemplate> legacy)
    {
        var marker = Path.Combine(DirectoryPath, LegacyMarker);
        if (File.Exists(marker))
            return;

        foreach (var template in legacy)
        {
            try
            {
                var path = ResolvePath(template.Name);
                if (!File.Exists(path))
                    Save(template, previousName: null);
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException or ArgumentException)
            {
                DiagnosticLog.Write("template_migration", $"{template.Name}: {ex.Message}");
                // Retain the old settings.json as a recovery source.
                return;
            }
        }

        File.WriteAllText(marker, "The settings.json templates were imported. Markdown files are now authoritative.\n");
    }

    public SavedMailTemplate Save(SavedMailTemplate template, string? previousName)
    {
        ArgumentNullException.ThrowIfNull(template);
        var name = template.Name.Trim();
        var target = ResolvePath(name);
        var previous = previousName is null ? null : ResolvePath(previousName);
        var sameFile = previous is not null &&
                       string.Equals(target, previous, StringComparison.OrdinalIgnoreCase);

        if (File.Exists(target) && !sameFile)
            throw new IOException($"Шаблон с названием «{name}» уже существует.");

        var attachments = new List<string>();
        var copiedNames = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        var attachmentRoot = Path.Combine(DirectoryPath, "_attachments", name);

        foreach (var supplied in template.Attachments.Distinct(StringComparer.OrdinalIgnoreCase))
        {
            var absolute = ResolveAttachmentPath(supplied);
            if (!File.Exists(absolute))
            {
                // Keep an unresolved user-authored reference so it can become
                // available again after the file is restored.
                attachments.Add(supplied);
                continue;
            }

            var managedRoot = Path.GetFullPath(Path.Combine(DirectoryPath, "_attachments"))
                              + Path.DirectorySeparatorChar;
            var full = Path.GetFullPath(absolute);
            if (full.StartsWith(managedRoot, StringComparison.OrdinalIgnoreCase))
            {
                attachments.Add(Path.GetRelativePath(DirectoryPath, full).Replace('\\', '/'));
                continue;
            }

            Directory.CreateDirectory(attachmentRoot);
            var fileName = Path.GetFileName(full);
            var candidate = Path.Combine(attachmentRoot, fileName);
            var stem = Path.GetFileNameWithoutExtension(fileName);
            var extension = Path.GetExtension(fileName);
            var suffix = 2;
            while (!copiedNames.Add(candidate) || File.Exists(candidate))
                candidate = Path.Combine(attachmentRoot, $"{stem} ({suffix++}){extension}");

            File.Copy(full, candidate);
            attachments.Add(Path.GetRelativePath(DirectoryPath, candidate).Replace('\\', '/'));
        }

        var output = new StringBuilder();
        output.AppendLine("---");
        output.Append("subject: ").AppendLine(JsonSerializer.Serialize(template.Subject));
        output.AppendLine("attachments:");
        foreach (var attachment in attachments)
            output.Append("  - ").AppendLine(JsonSerializer.Serialize(attachment));
        output.AppendLine("---");
        output.Append(template.Body);

        var temp = target + "." + Guid.NewGuid().ToString("N") + ".tmp";
        try
        {
            File.WriteAllText(temp, output.ToString(), new UTF8Encoding(false));
            File.Move(temp, target, sameFile);
            if (previous is not null && !sameFile && File.Exists(previous))
                File.Delete(previous);
        }
        finally
        {
            if (File.Exists(temp))
                File.Delete(temp);
        }

        return Parse(name, output.ToString());
    }

    public void Delete(string name)
    {
        var path = ResolvePath(name);
        if (File.Exists(path))
            File.Delete(path);
        // Attachment files are kept: another manually edited template may
        // reference them. No silent destructive cleanup.
    }

    private string ResolvePath(string name)
    {
        var value = name.Trim();
        if (value.Length == 0 || value is "." or ".." ||
            value.EndsWith('.') ||
            value.IndexOfAny(Path.GetInvalidFileNameChars()) >= 0 ||
            value.Contains('/') || value.Contains('\\'))
            throw new ArgumentException("Название шаблона содержит недопустимые символы.");

        var full = Path.GetFullPath(Path.Combine(DirectoryPath, value + ".md"));
        if (!string.Equals(Path.GetDirectoryName(full), DirectoryPath, StringComparison.OrdinalIgnoreCase))
            throw new ArgumentException("Некорректное название шаблона.");
        return full;
    }

    private string ResolveAttachmentPath(string value) =>
        Path.GetFullPath(Path.IsPathRooted(value)
            ? value
            : Path.Combine(DirectoryPath, value.Replace('/', Path.DirectorySeparatorChar)));

    private SavedMailTemplate Parse(string name, string text)
    {
        var normalized = text.Replace("\r\n", "\n");
        var subject = "";
        var attachments = new List<string>();
        var body = normalized;

        if (normalized.StartsWith("---\n", StringComparison.Ordinal))
        {
            var end = normalized.IndexOf("\n---\n", 4, StringComparison.Ordinal);
            if (end >= 0)
            {
                var metadata = normalized[4..end].Split('\n');
                var inAttachments = false;
                foreach (var line in metadata)
                {
                    if (line.StartsWith("subject:", StringComparison.OrdinalIgnoreCase))
                    {
                        subject = DecodeScalar(line["subject:".Length..].Trim());
                        inAttachments = false;
                    }
                    else if (line.Trim().Equals("attachments:", StringComparison.OrdinalIgnoreCase))
                    {
                        inAttachments = true;
                    }
                    else if (inAttachments && line.TrimStart().StartsWith("- ", StringComparison.Ordinal))
                    {
                        var reference = DecodeScalar(line.TrimStart()[2..].Trim());
                        if (!string.IsNullOrWhiteSpace(reference))
                        {
                            try
                            {
                                attachments.Add(ResolveAttachmentPath(reference));
                            }
                            catch (ArgumentException ex)
                            {
                                DiagnosticLog.Write("template_attachment", $"{name}: {ex.Message}");
                            }
                        }
                    }
                }
                body = normalized[(end + "\n---\n".Length)..];
            }
        }

        return new SavedMailTemplate
        {
            Id = name,
            Name = name,
            Subject = subject,
            Body = body,
            Attachments = attachments
        };
    }

    private static string DecodeScalar(string value)
    {
        if (value.StartsWith('"'))
        {
            try { return JsonSerializer.Deserialize<string>(value) ?? ""; }
            catch (JsonException) { /* Allow hand-written unquoted values. */ }
        }
        return value;
    }
}
