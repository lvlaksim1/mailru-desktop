package ru.mail.imageloader.downloader;

import androidx.compose.runtime.internal.StabilityInferred;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAnalyticsKt;
import ru.mail.asserter.asserters.Asserter;
import ru.mail.asserter.description.Descriptions;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogCollector;
import ru.mail.vkteams.gost.MailSdkOkHttpManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0016J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u001d\u001a\u00020\u001eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lru/mail/imageloader/downloader/InlineImageDownloaderDelegate;", "", "token", "", "analytics", "Lru/mail/analytics/MailAnalyticsKt;", "okHttpClient", "Lokhttp3/OkHttpClient;", "asserter", "Lru/mail/asserter/asserters/Asserter;", "logCollector", "Lru/mail/util/log/LogCollector;", "preferredSizeProvider", "Lru/mail/imageloader/downloader/ImagePreferredSizeProvider;", "mailSdkOkHttpManager", "Lru/mail/vkteams/gost/MailSdkOkHttpManager;", "<init>", "(Ljava/lang/String;Lru/mail/analytics/MailAnalyticsKt;Lokhttp3/OkHttpClient;Lru/mail/asserter/asserters/Asserter;Lru/mail/util/log/LogCollector;Lru/mail/imageloader/downloader/ImagePreferredSizeProvider;Lru/mail/vkteams/gost/MailSdkOkHttpManager;)V", "getInlineImageResponse", "Ljava/io/InputStream;", "url", "loadAsPreview", "", "handleRedirect", "redirectUrl", "formBody", "Lokhttp3/FormBody;", "buildBody", "getResponseInputStream", "response", "Lokhttp3/Response;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class InlineImageDownloaderDelegate {

    @NotNull
    private static final String PARAM_ACCESS_TOKEN = "access_token";

    @NotNull
    private static final String PARAM_PREFERRED_SIZE = "ps";

    @NotNull
    private static final String PARAM_PREVIEW_IMAGE = "af_preview";

    @NotNull
    private static final String PREVIEW_IMAGE_ENABLE_RESIZE = "1";

    @NotNull
    private final MailAnalyticsKt analytics;

    @NotNull
    private final Asserter asserter;

    @NotNull
    private final LogCollector logCollector;

    @NotNull
    private final MailSdkOkHttpManager mailSdkOkHttpManager;

    @NotNull
    private final OkHttpClient okHttpClient;

    @NotNull
    private final ImagePreferredSizeProvider preferredSizeProvider;

    @NotNull
    private final String token;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("InlineImageDownloaderDelegate");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/imageloader/downloader/InlineImageDownloaderDelegate$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "PARAM_ACCESS_TOKEN", "", "PARAM_PREVIEW_IMAGE", "PARAM_PREFERRED_SIZE", "PREVIEW_IMAGE_ENABLE_RESIZE", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public InlineImageDownloaderDelegate(@NotNull String token, @NotNull MailAnalyticsKt analytics, @NotNull OkHttpClient okHttpClient, @NotNull Asserter asserter, @NotNull LogCollector logCollector, @NotNull ImagePreferredSizeProvider preferredSizeProvider, @NotNull MailSdkOkHttpManager mailSdkOkHttpManager) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        Intrinsics.checkNotNullParameter(asserter, "asserter");
        Intrinsics.checkNotNullParameter(logCollector, "logCollector");
        Intrinsics.checkNotNullParameter(preferredSizeProvider, "preferredSizeProvider");
        Intrinsics.checkNotNullParameter(mailSdkOkHttpManager, "mailSdkOkHttpManager");
        this.token = token;
        this.analytics = analytics;
        this.okHttpClient = okHttpClient;
        this.asserter = asserter;
        this.logCollector = logCollector;
        this.preferredSizeProvider = preferredSizeProvider;
        this.mailSdkOkHttpManager = mailSdkOkHttpManager;
    }

    private final FormBody buildBody(boolean loadAsPreview) {
        FormBody.Builder builder = new FormBody.Builder(null, 1, null);
        builder.add("access_token", this.token);
        if (loadAsPreview) {
            String strValueOf = String.valueOf(this.preferredSizeProvider.getPreferredSize());
            builder.add(PARAM_PREVIEW_IMAGE, "1");
            builder.add(PARAM_PREFERRED_SIZE, strValueOf);
        }
        return builder.build();
    }

    public static /* synthetic */ InputStream getInlineImageResponse$default(InlineImageDownloaderDelegate inlineImageDownloaderDelegate, String str, boolean z10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            z10 = false;
        }
        return inlineImageDownloaderDelegate.getInlineImageResponse(str, z10);
    }

    private final InputStream getResponseInputStream(Response response) {
        InputStream inputStreamByteStream;
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null || (inputStreamByteStream = responseBodyBody.byteStream()) == null) {
            return null;
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamByteStream);
        return StringsKt.equals("gzip", Response.header$default(response, "Content-Encoding", null, 2, null), true) ? new GZIPInputStream(bufferedInputStream) : bufferedInputStream;
    }

    private final InputStream handleRedirect(String redirectUrl, FormBody formBody) throws IOException {
        Response responseExecute = this.mailSdkOkHttpManager.configureOkHttpClient(this.okHttpClient.newBuilder(), redirectUrl).build().newCall(new Request.Builder().url(redirectUrl).post(formBody).build()).execute();
        if (responseExecute.isSuccessful()) {
            this.analytics.onInlineImageDownloadSuccess();
            return getResponseInputStream(responseExecute);
        }
        this.analytics.onInlineImageDownloadRedirectFailed(responseExecute.code());
        LOG.w("Redirect failed with code " + responseExecute.code());
        return null;
    }

    @Nullable
    public final InputStream getInlineImageResponse(@NotNull String url, boolean loadAsPreview) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.analytics.onStartInlineImageDownload();
        try {
            FormBody formBodyBuildBody = buildBody(loadAsPreview);
            Response responseExecute = this.mailSdkOkHttpManager.configureOkHttpClient(this.okHttpClient.newBuilder(), url).build().newCall(new Request.Builder().url(url).post(formBodyBuildBody).build()).execute();
            if (!responseExecute.isSuccessful() && !responseExecute.isRedirect()) {
                LOG.w("No redirect in response with code: " + responseExecute.code());
                this.analytics.onInlineImageDownloadNotRedirected(responseExecute.code());
                return null;
            }
            String str = responseExecute.headers().get("location");
            if (str != null && !StringsKt.isBlank(str)) {
                return handleRedirect(str, formBodyBuildBody);
            }
            this.analytics.onInlineImageDownloadNoRedirectUrl();
            LOG.e("No redirect in response. Something wrong on SWA");
            return getResponseInputStream(responseExecute);
        } catch (Exception e10) {
            this.analytics.onInlineImageDownloadFailed();
            this.asserter.fail("Error while loading image", e10, Descriptions.logs(this.logCollector));
            LOG.e("Download failed", e10);
            return null;
        }
    }
}
