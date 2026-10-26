using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;
using System.Text;
using System.Text.Json;
using System.Text.Json.Nodes;
using MailRuDesktop.App.Mailing;

namespace MailRuDesktop.App;

internal sealed class PortableSettingsService
{
    private readonly AuthorizationStore _auth;
    private readonly AppSettingsStore _settings;
    private readonly string _root;
    private readonly string _templatesDir;
    private readonly string _signaturesDir;
    private readonly string _mailingDir;

    internal PortableSettingsService(AuthorizationStore auth, AppSettingsStore settings)
    {
        _auth = auth;
        _settings = settings;
        _root = Path.Combine(Environment.GetFolderPath(
            Environment.SpecialFolder.LocalApplicationData), "MailRuDesktop");
        _templatesDir = Path.Combine(_root, "Templates");
        _signaturesDir = Path.Combine(_root, "Signatures");
        _mailingDir = Path.Combine(_root, "Mailing");
    }

    internal PortablePackage Capture()
    {
        var store = new MarkdownTemplateStore(_settings.LoadTemplateDirectory());
        var package = new PortablePackage
        {
            SettingsJson = ReadOptional(Path.Combine(_root, "settings.json"), "{}"),
            Accounts = _auth.ExportPortableAccounts().ToList(),
            Layout = _settings.LoadAccountRailLayout(),
            Signatures = new MarkdownSignatureStore().LoadAll().ToList(),
            MailingProfiles = new MailingProfileStore().Load().ToList(),
            MailingExclusions = ReadOptional(Path.Combine(_mailingDir, "excluded-addresses.txt"), "")
        };
        foreach (var template in store.LoadAll())
        {
            var item = new PortableTemplate
            {
                Name = template.Name, Subject = template.Subject, Body = template.Body
            };
            foreach (var path in template.Attachments)
            {
                if (!File.Exists(path))
                    throw new IOException("Не найдено вложение шаблона «" +
                        template.Name + "»: " + Path.GetFileName(path));
                var info = new FileInfo(path);
                if (info.Length > 64 * 1024 * 1024)
                    throw new InvalidDataException("Вложение превышает 64 МБ: " + info.Name);
                item.Attachments.Add(new PortableAttachment
                {
                    Name = Path.GetFileName(path),
                    Content = Convert.ToBase64String(File.ReadAllBytes(path))
                });
            }
            package.Templates.Add(item);
        }
        Validate(package);
        return package;
    }

    internal static void Validate(PortablePackage package)
    {
        if (package.FormatVersion != 1 || package.Accounts is null ||
            package.Templates is null || package.Signatures is null ||
            package.Layout is null || package.MailingProfiles is null)
            throw new InvalidDataException("Неверная структура резервной копии.");
        if (package.Accounts.Count > 300 || package.Templates.Count > 2000 ||
            package.Signatures.Count > 2000 || package.MailingProfiles.Count > 2000)
            throw new InvalidDataException("Слишком много записей в архиве.");
        if (package.Accounts.Any(a => a is null ||
                string.IsNullOrWhiteSpace(a.Login) ||
                a.Login.Length > 320 || !a.Login.Contains('@')) ||
            package.Accounts.Select(a => a.Login)
                .Distinct(StringComparer.OrdinalIgnoreCase).Count() != package.Accounts.Count)
            throw new InvalidDataException("Некорректный список аккаунтов.");
        if (package.Templates.Any(t => t is null || !SafeFileStem(t.Name) ||
               t.Attachments is null || t.Attachments.Count > 100) ||
            package.Templates.Select(t => t.Name)
                .Distinct(StringComparer.OrdinalIgnoreCase).Count() != package.Templates.Count)
            throw new InvalidDataException("Некорректный список шаблонов.");
        if (package.Signatures.Any(t => t is null || !SafeFileStem(t.Name)) ||
            package.Signatures.Select(t => t.Name)
                .Distinct(StringComparer.OrdinalIgnoreCase).Count() != package.Signatures.Count)
            throw new InvalidDataException("Некорректный список подписей.");
        foreach (var template in package.Templates)
            foreach (var attachment in template.Attachments)
            {
                if (attachment is null || !SafeFileStem(attachment.Name) ||
                    attachment.Content is null || attachment.Content.Length > 90 * 1024 * 1024)
                    throw new InvalidDataException("Некорректное вложение в архиве.");
            }
        if (package.Layout.Count > 2000 ||
            package.Layout.Any(e => e is null ||
                (e.Kind != "account" && e.Kind != "section")))
            throw new InvalidDataException("Некорректная структура разделов аккаунтов.");
        if (JsonNode.Parse(package.SettingsJson) is not JsonObject)
            throw new InvalidDataException("Некорректные настройки оформления.");
    }

    private static bool SafeFileStem(string? value) =>
        !string.IsNullOrWhiteSpace(value) && value.Length <= 150 &&
        value != "." && value != ".." &&
        value.IndexOfAny(Path.GetInvalidFileNameChars()) < 0 &&
        !value.Contains('/') && !value.Contains('\\') && !value.Contains(':');

    internal string Export(string path, string password)
    {
        var package = Capture();
        var encrypted = PortableBackupCrypto.Encrypt(package, password);
        var temporary = path + "." + Guid.NewGuid().ToString("N") + ".tmp";
        try
        {
            File.WriteAllBytes(temporary, encrypted);
            File.Move(temporary, path, true);
        }
        finally { if (File.Exists(temporary)) File.Delete(temporary); }
        return path;
    }

    internal static PortablePackage Read(string path, string password)
    {
        var info = new FileInfo(path);
        if (info.Length > 256L * 1024 * 1024 + 53)
            throw new InvalidDataException("Архив превышает допустимый размер.");
        var package = PortableBackupCrypto.Decrypt(File.ReadAllBytes(path), password);
        Validate(package);
        return package;
    }

    // Always writes a full LOCAL rollback snapshot, including original auth.json
    // with current DPAPI tokens; never includes raw diagnostics or network journal.
    // A local snapshot is intentionally not a portable export.
    private sealed record BackupEntry(string Original, string? Saved);
    private sealed class LocalSnapshot
    {
        internal string DirectoryPath { get; }
        internal List<BackupEntry> Entries { get; } = [];
        internal LocalSnapshot(string directoryPath) => DirectoryPath = directoryPath;
        internal void CaptureFile(string original)
        {
            string? stored = null;
            if (File.Exists(original))
            {
                stored = Path.Combine(DirectoryPath, "files",
                    Entries.Count.ToString("D5"));
                Directory.CreateDirectory(Path.GetDirectoryName(stored)!);
                File.Copy(original, stored, true);
            }
            Entries.Add(new BackupEntry(original, stored));
        }
        internal void CaptureFolder(string folder)
        {
            var files = Directory.Exists(folder)
                ? Directory.EnumerateFiles(folder, "*", SearchOption.AllDirectories).ToArray()
                : [];
            // Track the entire folder, not just the files that existed at capture.
            Folders.Add(folder);
            foreach (var file in files) CaptureFile(file);
        }
        internal List<string> Folders { get; } = [];
        internal void WriteManifest()
        {
            File.WriteAllText(Path.Combine(DirectoryPath, "manifest.json"),
                JsonSerializer.Serialize(new
                {
                    Created = DateTimeOffset.UtcNow,
                    Files = Entries.Select(e => new { e.Original, Present = e.Saved is not null }),
                    Folders
                }, new JsonSerializerOptions { WriteIndented = true }));
        }
        internal void Rollback()
        {
            foreach (var folder in Folders.OrderByDescending(x => x.Length))
                if (Directory.Exists(folder))
                    Directory.Delete(folder, true);
            foreach (var entry in Entries.Where(x => x.Saved is null))
                if (File.Exists(entry.Original)) File.Delete(entry.Original);
            foreach (var entry in Entries.Where(x => x.Saved is not null))
            {
                Directory.CreateDirectory(Path.GetDirectoryName(entry.Original)!);
                File.Copy(entry.Saved!, entry.Original, true);
            }
        }
    }

    private LocalSnapshot BackupBeforeImport()
    {
        var folder = Path.Combine(_root, "Backups",
            DateTime.Now.ToString("yyyyMMdd-HHmmss") + "-" +
            Guid.NewGuid().ToString("N")[..8]);
        var snapshot = new LocalSnapshot(folder);
        Directory.CreateDirectory(folder);
        snapshot.CaptureFile(Path.Combine(_root, "settings.json"));
        snapshot.CaptureFile(Path.Combine(_root, "auth.json"));
        snapshot.CaptureFolder(_templatesDir);
        snapshot.CaptureFolder(_signaturesDir);
        snapshot.CaptureFile(Path.Combine(_mailingDir, "profiles.json"));
        snapshot.CaptureFile(Path.Combine(_mailingDir, "excluded-addresses.txt"));
        // Custom Markdown folders remain untouched during import and are not
        // relocated or deleted. Their contents are preserved independently.
        snapshot.WriteManifest();
        return snapshot;
    }

    internal IReadOnlyList<string> Apply(PortablePackage package, PortableSelection choice)
    {
        Validate(package);
        var selectedAccounts = choice.Accounts
            ? package.Accounts.Where(x => choice.AccountNames.Contains(x.Login)).ToArray()
            : [];
        var selectedNames = selectedAccounts.Select(x => x.Login)
            .ToHashSet(StringComparer.OrdinalIgnoreCase);
        var preserved = _auth.Logins.Where(login =>
            _auth.TryRestore(login, out var previous) &&
            !string.IsNullOrWhiteSpace(previous?.AccessToken))
            .ToHashSet(StringComparer.OrdinalIgnoreCase);
        var selectedTemplates = choice.Templates
            ? package.Templates.Where(x => choice.TemplateNames.Contains(x.Name)).ToArray()
            : [];
        var selectedSignatures = choice.Signatures
            ? package.Signatures.Where(x => choice.SignatureNames.Contains(x.Name)).ToArray()
            : [];
        var layout = BuildLayout(package.Layout, selectedNames, preserved);
        // Parse everything before taking a snapshot or changing existing files.
        var settings = choice.Appearance
            ? JsonNode.Parse(package.SettingsJson) as JsonObject ??
              throw new InvalidDataException("Неверные параметры оформления.")
            : new JsonObject();
        settings["AccountRailLayout"] = JsonSerializer.SerializeToNode(layout);
        settings["TemplateDirectory"] = null;
        settings["AttachmentDownloadDirectory"] = null;
        settings["MailTemplates"] = new JsonArray();
        settings["Signatures"] = new JsonArray();
        string? mainSignature = null;
        if (choice.Signatures)
        {
            var original = JsonNode.Parse(package.SettingsJson) as JsonObject;
            var candidate = original?["DefaultSignatureName"]?.GetValue<string>();
            if (selectedSignatures.Any(s => string.Equals(s.Name, candidate,
                    StringComparison.OrdinalIgnoreCase)))
                mainSignature = candidate;
        }
        settings["DefaultSignatureName"] = mainSignature;
        var serialized = settings.ToJsonString(new JsonSerializerOptions { WriteIndented = true });
        var localSnapshot = BackupBeforeImport();

        try
        {
            ReplaceManagedFiles(_templatesDir);
            ReplaceManagedFiles(_signaturesDir);
            Directory.CreateDirectory(_root);
            var tempSettings = Path.Combine(_root, "settings.json.import");
            File.WriteAllText(tempSettings, serialized, new UTF8Encoding(false));
            File.Move(tempSettings, Path.Combine(_root, "settings.json"), true);

            // Template attachments are extracted only to a private temporary folder
            // and then copied by the existing MarkdownTemplateStore.
            var tempAttachments = Path.Combine(_root, "Backups",
                "import-staging-" + Guid.NewGuid().ToString("N"));
            Directory.CreateDirectory(tempAttachments);
            try
            {
                var templates = new MarkdownTemplateStore(_templatesDir);
                foreach (var input in selectedTemplates)
                {
                    var paths = new List<string>();
                    foreach (var file in input.Attachments)
                    {
                        var decoded = Convert.FromBase64String(file.Content);
                        if (decoded.Length > 64 * 1024 * 1024)
                            throw new InvalidDataException("Вложение больше 64 МБ.");
                        var directory = Path.Combine(tempAttachments, Guid.NewGuid().ToString("N"));
                        Directory.CreateDirectory(directory);
                        var full = Path.Combine(directory, file.Name);
                        File.WriteAllBytes(full, decoded);
                        paths.Add(full);
                    }
                    templates.Save(new SavedMailTemplate
                    {
                        Name = input.Name, Subject = input.Subject, Body = input.Body,
                        Attachments = paths
                    }, previousName: null);
                }
            }
            finally { if (Directory.Exists(tempAttachments)) Directory.Delete(tempAttachments, true); }

            var signatures = new MarkdownSignatureStore(_signaturesDir);
            foreach (var entry in selectedSignatures)
                signatures.Save(entry);

            var mailing = new MailingProfileStore(_mailingDir);
            mailing.Save(choice.Mailing ? package.MailingProfiles : []);
            Directory.CreateDirectory(_mailingDir);
            File.WriteAllText(mailing.ExclusionsPath,
                choice.Mailing ? package.MailingExclusions : "", new UTF8Encoding(false));

            _auth.ReplaceForImport(selectedAccounts);
            var missing = selectedAccounts
                .Where(a => !preserved.Contains(a.Login) && !string.IsNullOrWhiteSpace(a.Password))
                .Select(a => a.Login).ToArray();
            // One-shot startup queue is safe: it contains logins only, no passwords or tokens.
            File.WriteAllText(Path.Combine(_root, "pending-import-auth.json"),
                JsonSerializer.Serialize(missing), new UTF8Encoding(false));
            return missing;
        }
        catch
        {
            localSnapshot.Rollback();
            _auth.ReloadFromDisk();
            throw;
        }
    }

    private static List<AccountRailLayoutEntryState> BuildLayout(
        IReadOnlyList<AccountRailLayoutEntryState> source,
        HashSet<string> selected, HashSet<string> preserved)
    {
        var output = new List<AccountRailLayoutEntryState>();
        var added = new HashSet<string>(StringComparer.OrdinalIgnoreCase);
        // Accounts retained from this computer but absent from the archive remain
        // outside sections. Place them BEFORE all imported section headers.
        foreach (var login in preserved.Where(login => !selected.Contains(login))
                     .OrderBy(login => login, StringComparer.OrdinalIgnoreCase))
        {
            output.Add(new AccountRailLayoutEntryState { Kind = "account", Login = login });
            added.Add(login);
        }
        foreach (var entry in source)
        {
            if (entry.Kind == "section")
            {
                output.Add(new AccountRailLayoutEntryState
                {
                    Kind = "section", Id = entry.Id, Title = entry.Title,
                    IsCollapsed = entry.IsCollapsed
                });
                continue;
            }
            if (entry.Kind == "account" && entry.Login is not null &&
                selected.Contains(entry.Login) && added.Add(entry.Login))
                output.Add(new AccountRailLayoutEntryState
                {
                    Kind = "account", Id = entry.Id, Login = entry.Login
                });
        }
        foreach (var login in selected.Where(login => !added.Contains(login)))
            output.Add(new AccountRailLayoutEntryState { Kind = "account", Login = login });
        return output;
    }

    private static void ReplaceManagedFiles(string directory)
    {
        Directory.CreateDirectory(directory);
        foreach (var file in Directory.EnumerateFiles(directory, "*.md",
                     SearchOption.TopDirectoryOnly))
            File.Delete(file);
        var attachments = Path.Combine(directory, "_attachments");
        if (Directory.Exists(attachments)) Directory.Delete(attachments, true);
    }
    private static string ReadOptional(string path, string fallback) =>
        File.Exists(path) ? File.ReadAllText(path, Encoding.UTF8) : fallback;
}
