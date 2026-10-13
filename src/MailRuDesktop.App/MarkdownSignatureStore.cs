using System.Text;

namespace MailRuDesktop.App;

/// <summary>
/// A signature is exactly one top-level UTF-8 Markdown file. Existing settings
/// values are imported once without overwriting user-maintained files.
/// </summary>
internal sealed class MarkdownSignatureStore
{
    public string DirectoryPath { get; }

    public MarkdownSignatureStore(string? directory = null)
    {
        DirectoryPath = Path.GetFullPath(directory ?? Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "Signatures"));
        Directory.CreateDirectory(DirectoryPath);
    }

    public IReadOnlyList<SavedSignature> LoadAll()
    {
        var result = new List<SavedSignature>();
        foreach (var file in Directory.EnumerateFiles(DirectoryPath, "*.md",
                     SearchOption.TopDirectoryOnly).OrderBy(x => x, StringComparer.OrdinalIgnoreCase))
        {
            try
            {
                var name = Path.GetFileNameWithoutExtension(file);
                var body = File.ReadAllText(file, Encoding.UTF8).TrimStart('\uFEFF');
                result.Add(new SavedSignature
                {
                    Id = name, Name = name, Body = body
                });
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException)
            {
                DiagnosticLog.Write("signature_read", Path.GetFileName(file) +
                    ": " + ex.GetType().Name);
            }
        }
        return result;
    }

    public bool Exists(string name) => File.Exists(Resolve(name));

    public SavedSignature Save(SavedSignature signature)
    {
        ArgumentNullException.ThrowIfNull(signature);
        var target = Resolve(signature.Name);
        var temporary = target + "." + Guid.NewGuid().ToString("N") + ".tmp";
        try
        {
            File.WriteAllText(temporary, signature.Body,
                new UTF8Encoding(encoderShouldEmitUTF8Identifier: false));
            File.Move(temporary, target, overwrite: true);
        }
        finally
        {
            if (File.Exists(temporary)) File.Delete(temporary);
        }
        return new SavedSignature
        {
            Id = signature.Name.Trim(), Name = signature.Name.Trim(),
            Body = signature.Body
        };
    }

    public void Delete(string name) => File.Delete(Resolve(name));

    public void ImportLegacyOnce(IEnumerable<SavedSignature> oldSignatures)
    {
        var marker = Path.Combine(DirectoryPath, ".legacy-signatures-imported");
        if (File.Exists(marker)) return;

        foreach (var sig in oldSignatures)
        {
            try
            {
                if (!Exists(sig.Name))
                    Save(sig);
            }
            catch (Exception ex) when (ex is IOException or UnauthorizedAccessException
                                        or ArgumentException)
            {
                DiagnosticLog.Write("signature_migration",
                    sig.Name + ": " + ex.GetType().Name);
                // The original settings.json remains authoritative backup;
                // rerun import on next start instead of marking it complete.
                return;
            }
        }
        File.WriteAllText(marker,
            "Legacy signatures are retained in settings.json as a backup.\n",
            new UTF8Encoding(false));
    }

    private string Resolve(string name)
    {
        var safe = name.Trim();
        if (safe.Length == 0 || safe is "." or ".." ||
            safe.EndsWith(' ') || safe.EndsWith('.') ||
            safe.IndexOfAny(Path.GetInvalidFileNameChars()) >= 0 ||
            safe.IndexOfAny(new[] { '/', '\\', ':', '*', '?', '"', '<', '>', '|' }) >= 0)
            throw new ArgumentException("Недопустимое название подписи.", nameof(name));
        return Path.Combine(DirectoryPath, safe + ".md");
    }
}
