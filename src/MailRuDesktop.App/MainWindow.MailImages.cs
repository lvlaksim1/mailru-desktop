using System.IO;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
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
        MessageWebView.CoreWebView2.AddWebResourceRequestedFilter(
            "https://proxy.imgsmail.ru/*",
            CoreWebView2WebResourceContext.Image);
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
            request.Headers.Referrer = new Uri("https://e.mail.ru/");
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/avif"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/webp"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/apng"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/svg+xml"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("image/*"));
            request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("*/*", 0.8));

            using var response = await _mailImageHttp.SendAsync(
                request,
                HttpCompletionOption.ResponseHeadersRead);

            if (!response.IsSuccessStatusCode)
            {
                DiagnosticLog.Write(
                    "mail_image_proxy",
                    $"proxy.imgsmail.ru HTTP {(int)response.StatusCode}");
                return;
            }

            var bytes = await response.Content.ReadAsByteArrayAsync();
            var stream = new MemoryStream(bytes, writable: false);
            var contentType = response.Content.Headers.ContentType?.ToString();
            if (string.IsNullOrWhiteSpace(contentType))
                contentType = GuessImageContentType(uri.AbsolutePath);

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

    private static HttpClient CreateMailImageHttpClient()
    {
        var handler = new HttpClientHandler
        {
            AllowAutoRedirect = true,
            AutomaticDecompression = DecompressionMethods.All
        };

        var client = new HttpClient(handler)
        {
            Timeout = TimeSpan.FromSeconds(30)
        };
        client.DefaultRequestHeaders.UserAgent.ParseAdd(MailRuDesktop.Protocol.MailRuFixedProfile.UserAgent);
        return client;
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
            _ => "application/octet-stream"
        };
    }
}
