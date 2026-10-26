using System;
using System.IO;
using System.Security.Cryptography;
using System.Text;
using System.Text.Json;
using System.Collections.Generic;
using System.Linq;

namespace MailRuDesktop.App;

// Credentials are included ONLY in encrypted portable exports. Tokens and cookies
// remain private to each installation. No diagnostics are added to this file.
internal sealed class PortableAccount
{
    public string Login { get; set; } = "";
    public string? Password { get; set; }
}
internal sealed class PortableAttachment
{
    public string Name { get; set; } = "";
    public string Content { get; set; } = "";
}
internal sealed class PortableTemplate
{
    public string Name { get; set; } = "";
    public string Subject { get; set; } = "";
    public string Body { get; set; } = "";
    public List<PortableAttachment> Attachments { get; set; } = [];
}
internal sealed class PortablePackage
{
    public int FormatVersion { get; set; } = 1;
    public string SettingsJson { get; set; } = "{}";
    public List<PortableAccount> Accounts { get; set; } = [];
    public List<AccountRailLayoutEntryState> Layout { get; set; } = [];
    public List<PortableTemplate> Templates { get; set; } = [];
    public List<SavedSignature> Signatures { get; set; } = [];
    public List<MailRuDesktop.App.Mailing.MailingProfile> MailingProfiles { get; set; } = [];
    public string MailingExclusions { get; set; } = "";
}
internal sealed class PortableSelection
{
    public bool Accounts { get; set; } = true;
    public bool Appearance { get; set; } = true;
    public bool Templates { get; set; } = true;
    public bool Signatures { get; set; } = true;
    public bool Mailing { get; set; } = true;
    public HashSet<string> AccountNames { get; set; } = new(StringComparer.OrdinalIgnoreCase);
    public HashSet<string> TemplateNames { get; set; } = new(StringComparer.OrdinalIgnoreCase);
    public HashSet<string> SignatureNames { get; set; } = new(StringComparer.OrdinalIgnoreCase);
}
internal static class PortableBackupCrypto
{
    private static readonly byte[] Magic = Encoding.ASCII.GetBytes("MRBACKUP1");
    private const int Iterations = 310000;
    private const int MaxSize = 256 * 1024 * 1024;

    internal static byte[] Encrypt(PortablePackage package, string passphrase)
    {
        if (string.IsNullOrWhiteSpace(passphrase) || passphrase.Length < 8)
            throw new InvalidDataException("Пароль резервной копии должен содержать не менее восьми символов.");
        var plain = JsonSerializer.SerializeToUtf8Bytes(package);
        if (plain.Length > MaxSize) throw new InvalidDataException("Резервная копия слишком велика.");
        byte[] salt = RandomNumberGenerator.GetBytes(16);
        byte[] nonce = RandomNumberGenerator.GetBytes(12);
        byte[] tag = new byte[16];
        byte[] cipher = new byte[plain.Length];
        byte[] key = Rfc2898DeriveBytes.Pbkdf2(passphrase, salt, Iterations, HashAlgorithmName.SHA256, 32);
        try
        {
            using var aes = new AesGcm(key, 16);
            aes.Encrypt(nonce, plain, cipher, tag, Magic);
            using var stream = new MemoryStream();
            stream.Write(Magic); stream.Write(salt); stream.Write(nonce); stream.Write(tag);
            stream.Write(cipher);
            return stream.ToArray();
        }
        finally
        {
            CryptographicOperations.ZeroMemory(key);
            CryptographicOperations.ZeroMemory(plain);
        }
    }

    internal static PortablePackage Decrypt(byte[] file, string passphrase)
    {
        const int header = 9 + 16 + 12 + 16;
        if (file.Length < header || file.Length > MaxSize + header ||
            !file.AsSpan(0, 9).SequenceEqual(Magic))
            throw new InvalidDataException("Неверный формат резервной копии.");
        byte[] key = Rfc2898DeriveBytes.Pbkdf2(passphrase, file.AsSpan(9, 16),
            Iterations, HashAlgorithmName.SHA256, 32);
        byte[] plain = new byte[file.Length - header];
        try
        {
            using var aes = new AesGcm(key, 16);
            aes.Decrypt(file.AsSpan(25, 12), file.AsSpan(header),
                file.AsSpan(37, 16), plain, Magic);
            var package = JsonSerializer.Deserialize<PortablePackage>(plain)
                ?? throw new InvalidDataException("Резервная копия пуста.");
            if (package.FormatVersion != 1 || package.Accounts is null ||
                package.Layout is null || package.Templates is null ||
                package.Signatures is null || package.MailingProfiles is null)
                throw new InvalidDataException("Неподдерживаемый формат резервной копии.");
            return package;
        }
        catch (CryptographicException ex)
        {
            throw new InvalidDataException("Неправильный пароль или повреждённый архив.", ex);
        }
        catch (JsonException ex)
        {
            throw new InvalidDataException("Повреждены данные внутри архива.", ex);
        }
        finally
        {
            CryptographicOperations.ZeroMemory(key);
            CryptographicOperations.ZeroMemory(plain);
        }
    }
}
