using System.Net.Http;
using System.Net.Http.Headers;
using System.Text.Json;

namespace MailRuDesktop.App;

internal sealed record GitHubReleaseInfo(
    Version Version,
    string Tag,
    string UpdateDownloadUrl);

internal static class GitHubUpdateService
{
    private const string LatestReleaseApi =
        "https://api.github.com/repos/lvlaksim1/mailru-desktop/releases/latest";

    private static readonly HttpClient Http = CreateClient();

    public static async Task<GitHubReleaseInfo> GetLatestReleaseAsync(
        CancellationToken cancellationToken = default)
    {
        using var response = await Http.GetAsync(
            LatestReleaseApi,
            HttpCompletionOption.ResponseHeadersRead,
            cancellationToken);
        response.EnsureSuccessStatusCode();

        await using var stream = await response.Content.ReadAsStreamAsync(cancellationToken);
        using var document = await JsonDocument.ParseAsync(stream, cancellationToken: cancellationToken);
        var root = document.RootElement;

        var tag = root.TryGetProperty("tag_name", out var tagElement)
            ? tagElement.GetString()
            : null;

        if (string.IsNullOrWhiteSpace(tag))
            throw new InvalidDataException("В последнем выпуске GitHub отсутствует tag_name.");

        var versionText = tag.Trim().TrimStart('v', 'V');
        if (!Version.TryParse(versionText, out var version))
            throw new InvalidDataException($"Не удалось распознать версию выпуска «{tag}».");

        string? updateUrl = null;
        if (root.TryGetProperty("assets", out var assets) &&
            assets.ValueKind == JsonValueKind.Array)
        {
            foreach (var asset in assets.EnumerateArray())
            {
                var name = asset.TryGetProperty("name", out var nameElement)
                    ? nameElement.GetString()
                    : null;
                if (string.IsNullOrWhiteSpace(name) ||
                    !name.StartsWith("MailRuDesktop_Update_v", StringComparison.OrdinalIgnoreCase) ||
                    !name.EndsWith(".exe", StringComparison.OrdinalIgnoreCase))
                {
                    continue;
                }

                updateUrl = asset.TryGetProperty("browser_download_url", out var urlElement)
                    ? urlElement.GetString()
                    : null;
                if (!string.IsNullOrWhiteSpace(updateUrl))
                    break;
            }
        }

        if (string.IsNullOrWhiteSpace(updateUrl))
            throw new InvalidDataException("В последнем выпуске GitHub отсутствует установщик обновления.");

        var uri = new Uri(updateUrl, UriKind.Absolute);
        if (!uri.Scheme.Equals(Uri.UriSchemeHttps, StringComparison.OrdinalIgnoreCase) ||
            !uri.Host.Equals("github.com", StringComparison.OrdinalIgnoreCase))
        {
            throw new InvalidDataException("Получена недопустимая ссылка на установщик обновления.");
        }

        return new GitHubReleaseInfo(version, tag, updateUrl);
    }

    public static async Task<string> DownloadUpdateAsync(
        GitHubReleaseInfo release,
        CancellationToken cancellationToken = default)
    {
        var updateRoot = Path.Combine(
            Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData),
            "MailRuDesktop",
            "Updates");
        Directory.CreateDirectory(updateRoot);

        foreach (var oldFile in Directory.EnumerateFiles(
                     updateRoot,
                     "MailRuDesktop_Update_v*.exe",
                     SearchOption.TopDirectoryOnly))
        {
            try
            {
                File.Delete(oldFile);
            }
            catch
            {
                // A previously launched installer may still be held by Windows.
            }
        }

        var fileName = $"MailRuDesktop_Update_v{release.Version}.exe";
        var destination = Path.Combine(updateRoot, fileName);

        using var response = await Http.GetAsync(
            release.UpdateDownloadUrl,
            HttpCompletionOption.ResponseHeadersRead,
            cancellationToken);
        response.EnsureSuccessStatusCode();

        await using var source = await response.Content.ReadAsStreamAsync(cancellationToken);
        await using var target = new FileStream(
            destination,
            FileMode.Create,
            FileAccess.Write,
            FileShare.None,
            1024 * 128,
            useAsync: true);
        await source.CopyToAsync(target, cancellationToken);

        return destination;
    }

    private static HttpClient CreateClient()
    {
        var client = new HttpClient
        {
            Timeout = TimeSpan.FromMinutes(5)
        };
        client.DefaultRequestHeaders.UserAgent.Add(
            new ProductInfoHeaderValue("MailRuDesktop", "1.0"));
        client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/vnd.github+json"));
        client.DefaultRequestHeaders.Add("X-GitHub-Api-Version", "2022-11-28");
        return client;
    }
}
