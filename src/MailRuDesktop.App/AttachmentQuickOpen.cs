using System.Diagnostics;
using System.IO;

namespace MailRuDesktop.App;

/// <summary>
/// Open an incoming attachment with the Windows file association without
/// involving a Save As dialog or placing it in the user's Downloads folder.
/// External programs need a real file, so the bytes are staged in a private
/// temporary application directory and removed on a later invocation.
/// </summary>
internal static class AttachmentQuickOpen
{
    private static readonly HashSet<string> UnsafeExtensions =
        new(StringComparer.OrdinalIgnoreCase)
        {
            ".exe", ".com", ".bat", ".cmd", ".ps1", ".psm1", ".vbs",
            ".vbe", ".js", ".jse", ".wsf", ".wsh", ".scr", ".msi",
            ".msp", ".hta", ".htm", ".html", ".lnk", ".url", ".reg",
            ".cpl", ".pif", ".jar", ".svg"
        };

    public static async Task OpenAsync(string originalName, Func<Task<byte[]>> fetch)
    {
        ArgumentNullException.ThrowIfNull(fetch);
        var leaf = Path.GetFileName((originalName ?? string.Empty).Replace('\\', '/'));
        var cleaned = new string(leaf.Where(c =>
            !Path.GetInvalidFileNameChars().Contains(c) && !char.IsControl(c)).ToArray()).Trim();
        if (cleaned is "" or "." or "..")
            cleaned = "attachment";

        var suffix = Path.GetExtension(cleaned);
        if (suffix.Length == 0 || UnsafeExtensions.Contains(suffix))
            throw new InvalidOperationException(
                "Этот тип вложения нельзя запускать двойным щелчком. " +
                "Используйте «Сохранить вложение» и проверьте файл перед открытием.");

        var root = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop", "TempAttachments");
        Directory.CreateDirectory(root);
        RemoveExpiredDirectories(root);

        var temporaryDirectory = Path.Combine(root, Guid.NewGuid().ToString("N"));
        Directory.CreateDirectory(temporaryDirectory);
        var target = Path.Combine(temporaryDirectory, cleaned);
        try
        {
            var bytes = await fetch().ConfigureAwait(false);
            await File.WriteAllBytesAsync(target, bytes).ConfigureAwait(false);
            var process = Process.Start(new ProcessStartInfo
            {
                FileName = target,
                UseShellExecute = true
            });
            // ShellExecute can return null for a successful document activation.
            _ = process;
        }
        catch
        {
            try { Directory.Delete(temporaryDirectory, recursive: true); }
            catch (IOException) { }
            catch (UnauthorizedAccessException) { }
            throw;
        }
    }

    private static void RemoveExpiredDirectories(string root)
    {
        foreach (var directory in Directory.EnumerateDirectories(root))
        {
            try
            {
                if (Directory.GetCreationTimeUtc(directory) < DateTime.UtcNow.AddDays(-2))
                    Directory.Delete(directory, recursive: true);
            }
            catch (IOException) { } // The reader application may still own the file.
            catch (UnauthorizedAccessException) { }
        }
    }
}
