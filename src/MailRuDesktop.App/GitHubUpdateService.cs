using System.IO;
using System.Net;
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
    internal const string LatestReleasePage =
        "https://github.com/lvlaksim1/mailru-desktop/releases/latest";
    private const string ReleaseTagPrefix =
        "/lvlaksim1/mailru-desktop/releases/tag/";

    private static readonly HttpClient Http = CreateClient();
    // The website returns a redirect whose Location contains the release tag.
    // Do not follow it: GitHub API may be blocked even when github.com works.
    private static readonly HttpClient WebsiteHttp = CreateWebsiteClient();

    public static Task<GitHubReleaseInfo> GetLatestReleaseAsync(
        CancellationToken cancellationToken = default) =>
        GetLatestReleaseAsync(Http, WebsiteHttp, cancellationToken);

    // Separate injected clients allow deterministic offline tests without
    // access to GitHub or real mailbox credentials.
    internal static async Task<GitHubReleaseInfo> GetLatestReleaseAsync(
        HttpClient apiHttp,
        HttpClient websiteHttp,
        CancellationToken cancellationToken = default)
    {
        Exception apiError;
        try
        {
            return await GetLatestFromApiAsync(apiHttp, cancellationToken);
        }
        catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
        {
            throw;
        }
        catch (Exception error) when (
            error is HttpRequestException or InvalidDataException or JsonException
                or TaskCanceledException)
        {
            apiError = error;
        }

        try
        {
            return await GetLatestFromWebsiteAsync(websiteHttp, cancellationToken);
        }
        catch (OperationCanceledException) when (cancellationToken.IsCancellationRequested)
        {
            throw;
        }
        catch (Exception error) when (
            error is HttpRequestException or InvalidDataException or TaskCanceledException)
        {
            throw new InvalidOperationException(
                "Не удалось получить версию: api.github.com — " +
                ExplainFailure(apiError) + "; github.com — " +
                ExplainFailure(error) + ".",
                new AggregateException(apiError, error));
        }
    }

    private static async Task<GitHubReleaseInfo> GetLatestFromApiAsync(
        HttpClient http, CancellationToken cancellationToken)
    {
        using var response = await http.GetAsync(
            LatestReleaseApi,
            HttpCompletionOption.ResponseHeadersRead,
            cancellationToken);
        response.EnsureSuccessStatusCode();

        await using var stream = await response.Content.ReadAsStreamAsync(cancellationToken);
        using var document = await JsonDocument.ParseAsync(
            stream, cancellationToken: cancellationToken);
        var root = document.RootElement;

        var tag = root.TryGetProperty("tag_name", out var tagElement)
            ? tagElement.GetString()
            : null;

        if (string.IsNullOrWhiteSpace(tag) || !TryParseTag(tag, out var version))
            throw new InvalidDataException("Последний выпуск GitHub содержит недопустимый номер версии.");

        string? updateUrl = null;
        if (root.TryGetProperty("assets", out var assets) &&
            assets.ValueKind == JsonValueKind.Array)
        {
            foreach (var asset in assets.EnumerateArray())
            {
                var name = asset.TryGetProperty("name", out var nameElement)
                    ? nameElement.GetString()
                    : null;
                if (!string.Equals(name, "MailRuDesktop_Update_" + tag + ".exe",
                        StringComparison.OrdinalIgnoreCase))
                    continue;

                updateUrl = asset.TryGetProperty("browser_download_url", out var urlElement)
                    ? urlElement.GetString()
                    : null;
                if (!string.IsNullOrWhiteSpace(updateUrl))
                    break;
            }
        }

        if (string.IsNullOrWhiteSpace(updateUrl))
            throw new InvalidDataException("В последнем выпуске отсутствует установщик обновления.");

        if (!Uri.TryCreate(updateUrl, UriKind.Absolute, out var uri) ||
            uri.Scheme != Uri.UriSchemeHttps ||
            !uri.Host.Equals("github.com", StringComparison.OrdinalIgnoreCase) ||
            !uri.AbsolutePath.Equals(
                "/lvlaksim1/mailru-desktop/releases/download/" + tag +
                "/MailRuDesktop_Update_" + tag + ".exe",
                StringComparison.OrdinalIgnoreCase))
            throw new InvalidDataException("Недопустимая ссылка на установщик обновления.");

        return new GitHubReleaseInfo(version, tag, updateUrl);
    }

    private static async Task<GitHubReleaseInfo> GetLatestFromWebsiteAsync(
        HttpClient http, CancellationToken cancellationToken)
    {
        using var request = new HttpRequestMessage(HttpMethod.Get, LatestReleasePage);
        using var response = await http.SendAsync(
            request, HttpCompletionOption.ResponseHeadersRead, cancellationToken);

        // GitHub /releases/latest redirects to /releases/tag/vX.Y.Z.
        // A redirect to another domain, another repository, or a malformed tag
        // is never allowed to supply an installer URL.
        if (response.StatusCode is not (
            HttpStatusCode.MovedPermanently or HttpStatusCode.Redirect or
            HttpStatusCode.RedirectMethod or HttpStatusCode.TemporaryRedirect or
            HttpStatusCode.PermanentRedirect))
        {
            response.EnsureSuccessStatusCode();
            throw new InvalidDataException("Страница GitHub не сообщила номер последнего выпуска.");
        }

        var location = response.Headers.Location ??
            throw new InvalidDataException("GitHub не сообщил адрес нового выпуска.");
        var destination = location.IsAbsoluteUri
            ? location
            : new Uri(new Uri(LatestReleasePage), location);

        if (destination.Scheme != Uri.UriSchemeHttps ||
            !destination.Host.Equals("github.com", StringComparison.OrdinalIgnoreCase) ||
            !destination.AbsolutePath.StartsWith(
                ReleaseTagPrefix, StringComparison.OrdinalIgnoreCase) ||
            !string.IsNullOrEmpty(destination.Query) ||
            !string.IsNullOrEmpty(destination.Fragment))
            throw new InvalidDataException("Недопустимое перенаправление от GitHub.");

        var tag = destination.AbsolutePath[ReleaseTagPrefix.Length..];
        if (!TryParseTag(tag, out var version))
            throw new InvalidDataException("GitHub сообщил недопустимую версию выпуска.");

        // The version tag comes from GitHub's own validated redirect; asset
        // naming is defined by this repository's installer release workflow.
        var download = "https://github.com/lvlaksim1/mailru-desktop/" +
            "releases/download/" + tag + "/MailRuDesktop_Update_" + tag + ".exe";
        return new GitHubReleaseInfo(version, tag, download);
    }

    private static bool TryParseTag(string? tag, out Version version)
    {
        version = new Version(0, 0, 0);
        if (string.IsNullOrWhiteSpace(tag) || tag[0] is not ('v' or 'V'))
            return false;

        var parts = tag[1..].Split('.');
        if (parts.Length != 3 || parts.Any(p =>
                p.Length == 0 || p.Any(ch => ch < '0' || ch > '9')))
            return false;

        if (!Version.TryParse(tag[1..], out var parsed))
            return false;

        version = parsed;
        return true;
    }

    private static string ExplainFailure(Exception error) => error switch
    {
        HttpRequestException { StatusCode: { } code } => "HTTP " + (int)code,
        TaskCanceledException => "превышено время ожидания",
        InvalidDataException => "неподходящий ответ",
        JsonException => "повреждённый ответ",
        HttpRequestException => "ошибка соединения",
        _ => "неизвестная ошибка"
    };

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
        var client = new HttpClient { Timeout = TimeSpan.FromSeconds(30) };
        ConfigureHeaders(client);
        return client;
    }

    private static HttpClient CreateWebsiteClient()
    {
        var client = new HttpClient(new HttpClientHandler { AllowAutoRedirect = false })
        {
            Timeout = TimeSpan.FromSeconds(30)
        };
        ConfigureHeaders(client);
        return client;
    }

    private static void ConfigureHeaders(HttpClient client)
    {
        client.DefaultRequestHeaders.UserAgent.Add(
            new ProductInfoHeaderValue("MailRuDesktop", "1.0"));
        client.DefaultRequestHeaders.Accept.Add(
            new MediaTypeWithQualityHeaderValue("application/vnd.github+json"));
        client.DefaultRequestHeaders.Add("X-GitHub-Api-Version", "2022-11-28");
    }
}
