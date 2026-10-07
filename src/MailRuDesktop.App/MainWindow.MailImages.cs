using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using Microsoft.Web.WebView2.Core;

namespace MailRuDesktop.App;

public partial class MainWindow
{
    private readonly HttpClient _mailImageHttp = CreateMailImageHttpClient();
    private bool _mailImageProxyConfigured;

    private void ConfigureMailImageProxy()
    {
        if (_mailImageProxyConfigured || MessageWebView.CoreWebView2 is null)
            return;

        _mailImageProxyConfigured = true;

        // SVG and proxied resources are not guaranteed to be classified as
        // CoreWebView2WebResourceContext.Image on every WebView2 build.
        // Intercept all proxy.imgsmail.ru requests and validate the host below.
        MessageWebView.CoreWebView2.AddWebResourceRequestedFilter(
            "https://proxy.imgsmail.ru/*",
            CoreWebView2WebResourceContext.All);
        MessageWebView.CoreWebView2.WebResourceRequested += MailImageProxy_WebResourceRequested;
    }

    private async void MailImageProxy_WebResourceRequested(
        object? sender,
        CoreWebView2WebResourceRequestedEventArgs e)
    {
        if (MessageWebView.CoreWebView2 is null ||
            !Uri.TryCreate(e.Request.Uri, UriKind.Absolute, out var uri) ||
            !uri.Host.Equals("proxy.imgsmail.ru", StringComparison.OrdinalIgnoreCase))
        {
            return;
        }

        var deferral = e.GetDeferral();
        try
        {
            using var request = new HttpRequestMessage(HttpMethod.Get, uri);
            CopyBrowserRequestHeader(e.Request, request, "User-Agent");
            CopyBrowserRequestHeader(e.Request, request, "Referer");
            CopyBrowserRequestHeader(e.Request, request, "Cookie");
            CopyBrowserRequestHeader(e.Request, request, "Accept-Language");

            if (!request.Headers.UserAgent.Any())
            {
                request.Headers.UserAgent.ParseAdd(
                    MailRuDesktop.Protocol.MailRuFixedProfile.UserAgent);
            }

            request.Headers.Accept.Clear();
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/avif"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/webp"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/apng"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/svg+xml"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/*"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*", 0.8));

            using var response = await _mailImageHttp.SendAsync(
                request,
                HttpCompletionOption.ResponseHeadersRead);

            var finalUri = response.RequestMessage?.RequestUri;
            var finalHost = finalUri?.Host ?? uri.Host;

            if (!response.IsSuccessStatusCode)
            {
                DiagnosticLog.Write(
                    "mail_image_proxy",
                    $"http={(int)response.StatusCode}; final-host={finalHost}");
                return;
            }

            var bytes = await response.Content.ReadAsByteArrayAsync();
            var originalUrl = TryDecodeProxyOriginalUrl(uri);
            var contentType = ResolveImageContentType(
                response.Content.Headers.ContentType?.MediaType,
                originalUrl,
                bytes);

            DiagnosticLog.Write(
                "mail_image_proxy",
                $"http={(int)response.StatusCode}; final-host={finalHost}; " +
                $"content-type={contentType}; bytes={bytes.Length}; " +
                $"original-host={SafeHost(originalUrl)}");

            if (!IsImageContentType(contentType) || bytes.Length == 0)
            {
                // Leave Response unset so WebView2 may still try the original
                // request using its own network stack.
                return;
            }

            var stream = new MemoryStream(bytes, writable: false);
            var headers =
                $"Content-Type: {contentType}\r\n" +
                "Cache-Control: private, max-age=300\r\n";

            e.Response = MessageWebView.CoreWebView2.Environment.CreateWebResourceResponse(
                stream,
                (int)response.StatusCode,
                response.ReasonPhrase ?? "OK",
                headers);
        }
        catch (Exception ex)
        {
            DiagnosticLog.Write(
                "mail_image_proxy",
                ex.GetType().Name + ": " + ex.Message);
        }
        finally
        {
            deferral.Complete();
        }
    }

    private static void CopyBrowserRequestHeader(
        CoreWebView2WebResourceRequest source,
        HttpRequestMessage destination,
        string name)
    {
        try
        {
            var value = source.Headers.GetHeader(name);
            if (!string.IsNullOrWhiteSpace(value))
                destination.Headers.TryAddWithoutValidation(name, value);
        }
        catch
        {
            // Header absent or unavailable on this WebView2 runtime.
        }
    }

    private static HttpClient CreateMailImageHttpClient()
    {
        var handler = new HttpClientHandler
        {
            AllowAutoRedirect = true,
            AutomaticDecompression = DecompressionMethods.All,
            UseCookies = false
        };

        return new HttpClient(handler)
        {
            Timeout = TimeSpan.FromSeconds(30)
        };
    }

    private static string ResolveImageContentType(
        string? serverContentType,
        string? originalUrl,
        byte[] bytes)
    {
        if (!string.IsNullOrWhiteSpace(serverContentType) &&
            IsImageContentType(serverContentType))
        {
            return serverContentType;
        }

        var sniffed = SniffImageContentType(bytes);
        if (sniffed is not null)
            return sniffed;

        if (Uri.TryCreate(originalUrl, UriKind.Absolute, out var originalUri))
        {
            var byOriginalPath = GuessImageContentType(originalUri.AbsolutePath);
            if (IsImageContentType(byOriginalPath))
                return byOriginalPath;
        }

        var byProxyPath = GuessImageContentType("/");
        return !string.IsNullOrWhiteSpace(serverContentType)
            ? serverContentType
            : byProxyPath;
    }

    private static string? SniffImageContentType(byte[] bytes)
    {
        if (bytes.Length >= 8 &&
            bytes[0] == 0x89 &&
            bytes[1] == 0x50 &&
            bytes[2] == 0x4E &&
            bytes[3] == 0x47)
        {
            return "image/png";
        }

        if (bytes.Length >= 3 &&
            bytes[0] == 0xFF &&
            bytes[1] == 0xD8 &&
            bytes[2] == 0xFF)
        {
            return "image/jpeg";
        }

        if (bytes.Length >= 6)
        {
            var gif = Encoding.ASCII.GetString(bytes, 0, 6);
            if (gif is "GIF87a" or "GIF89a")
                return "image/gif";
        }

        if (bytes.Length >= 12 &&
            Encoding.ASCII.GetString(bytes, 0, 4) == "RIFF" &&
            Encoding.ASCII.GetString(bytes, 8, 4) == "WEBP")
        {
            return "image/webp";
        }

        if (bytes.Length > 0)
        {
            var prefixLength = Math.Min(bytes.Length, 4096);
            var prefix = Encoding.UTF8
                .GetString(bytes, 0, prefixLength)
                .TrimStart('\uFEFF', ' ', '\t', '\r', '\n');

            if (prefix.StartsWith("<svg", StringComparison.OrdinalIgnoreCase) ||
                (prefix.StartsWith("<?xml", StringComparison.OrdinalIgnoreCase) &&
                 prefix.Contains("<svg", StringComparison.OrdinalIgnoreCase)))
            {
                return "image/svg+xml";
            }
        }

        return null;
    }

    private static bool IsImageContentType(string? contentType) =>
        !string.IsNullOrWhiteSpace(contentType) &&
        contentType.StartsWith("image/", StringComparison.OrdinalIgnoreCase);

    private static string? TryDecodeProxyOriginalUrl(Uri proxyUri)
    {
        foreach (var part in proxyUri.Query.TrimStart('?').Split('&'))
        {
            if (string.IsNullOrWhiteSpace(part))
                continue;

            var pair = part.Split('=', 2);
            if (pair.Length != 2 ||
                !pair[0].Equals("url173", StringComparison.OrdinalIgnoreCase))
            {
                continue;
            }

            try
            {
                var encoded = Uri.UnescapeDataString(pair[1])
                    .Replace('-', '+')
                    .Replace('_', '/');

                encoded = encoded.PadRight(
                    encoded.Length + ((4 - encoded.Length % 4) % 4),
                    '=');

                var decoded = Encoding.UTF8.GetString(
                    Convert.FromBase64String(encoded));

                return Uri.TryCreate(decoded, UriKind.Absolute, out var original) &&
                       original.Scheme == Uri.UriSchemeHttps
                    ? original.ToString()
                    : null;
            }
            catch
            {
                return null;
            }
        }

        return null;
    }

    private static string SafeHost(string? url)
    {
        if (!Uri.TryCreate(url, UriKind.Absolute, out var uri))
            return "none";

        return uri.Host;
    }

    private static string GuessImageContentType(string path)
    {
        var extension = Path.GetExtension(path).ToLowerInvariant();
        return extension switch
        {
            ".svg" => "image/svg+xml",
            ".png" => "image/png",
            ".jpg" or ".jpeg" => "image/jpeg",
            ".gif" => "image/gif",
            ".webp" => "image/webp",
            ".avif" => "image/avif",
            _ => "application/octet-stream"
        };
    }
}
