package ru.mail.utils;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.huawei.hms.framework.common.BundleUtil;
import java.io.File;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.presentationlayer.CloudNavigator;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\bJ(\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bJ\u0018\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002¨\u0006\u0012"}, d2 = {"Lru/mail/utils/DownloadManagerUtils;", "", "<init>", "()V", "setFileNameToRequest", "Landroid/app/DownloadManager$Request;", Event.Companion.Network.Fail.REQUEST_TAG, "contentDisposition", "", "url", "setMimeTypeToRequest", "mimeType", "setDestinationToRequest", CloudNavigator.PARAMS_FILE_NAME, "formatFileName", "resolveFileNameFromUrl", "getFileNameFromHeader", "Companion", "mail-utils_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDownloadManagerUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DownloadManagerUtils.kt\nru/mail/utils/DownloadManagerUtils\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,113:1\n29#2:114\n*S KotlinDebug\n*F\n+ 1 DownloadManagerUtils.kt\nru/mail/utils/DownloadManagerUtils\n*L\n86#1:114\n*E\n"})
public final class DownloadManagerUtils {

    @NotNull
    private static final String ATTACHMAIL_URL = "af.attachmail.ru/cgi-bin/readmsg";

    @NotNull
    private static final String DEFAULT_FILE_NAME = "document.pdf";

    @NotNull
    private static final String REGEX_FILE_NAME_DOT_REPLACER = "\\.(?=.*\\.)";

    @NotNull
    private static final String UTF_FORMAT = "UTF-8";

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("DownloadManagerUtils");

    private final String formatFileName(String fileName) {
        return Build.VERSION.SDK_INT <= 29 ? new Regex(REGEX_FILE_NAME_DOT_REPLACER).replace(fileName, BundleUtil.UNDERLINE_TAG) : fileName;
    }

    private final String getFileNameFromHeader(String contentDisposition) {
        if (contentDisposition == null || contentDisposition.length() == 0) {
            LOG.w("Header \"Content-Disposition\" is null or empty");
            return null;
        }
        String strReplaceFirst = new Regex("(?i)^.*filename[*]=UTF-8''([^']+).*$").replaceFirst(contentDisposition, "$1");
        if (TextUtils.isEmpty(strReplaceFirst) || strReplaceFirst.length() >= contentDisposition.length()) {
            return null;
        }
        return URLDecoder.decode(strReplaceFirst, "UTF-8");
    }

    private final String resolveFileNameFromUrl(String url) {
        List<String> pathSegments = Uri.parse(url).getPathSegments();
        Intrinsics.checkNotNullExpressionValue(pathSegments, "getPathSegments(...)");
        Object objLast = CollectionsKt.last((List<? extends Object>) pathSegments);
        Intrinsics.checkNotNullExpressionValue(objLast, "last(...)");
        return (String) objLast;
    }

    private final DownloadManager.Request setDestinationToRequest(DownloadManager.Request request, String fileName) {
        DownloadManager.Request destinationInExternalPublicDir = request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, formatFileName(fileName));
        Intrinsics.checkNotNullExpressionValue(destinationInExternalPublicDir, "setDestinationInExternalPublicDir(...)");
        return destinationInExternalPublicDir;
    }

    @NotNull
    public final DownloadManager.Request setFileNameToRequest(@NotNull DownloadManager.Request request, @Nullable String contentDisposition, @NotNull String url) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(url, "url");
        String fileNameFromHeader = getFileNameFromHeader(contentDisposition);
        if (fileNameFromHeader != null) {
            return setDestinationToRequest(request, fileNameFromHeader);
        }
        Log log = LOG;
        log.w("fileName from header \"Content-Disposition\" is not resolved, try resolve from url");
        String strResolveFileNameFromUrl = resolveFileNameFromUrl(url);
        if (strResolveFileNameFromUrl.length() > 0) {
            return setDestinationToRequest(request, strResolveFileNameFromUrl);
        }
        log.w("fileName from url is not resolved, use default fileName");
        return setDestinationToRequest(request, DEFAULT_FILE_NAME);
    }

    @NotNull
    public final DownloadManager.Request setMimeTypeToRequest(@NotNull DownloadManager.Request request, @Nullable String contentDisposition, @NotNull String url, @NotNull String mimeType) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        if (!StringsKt.contains$default((CharSequence) url, (CharSequence) ATTACHMAIL_URL, false, 2, (Object) null)) {
            request.setMimeType(mimeType);
        }
        String fileNameFromHeader = getFileNameFromHeader(contentDisposition);
        if (fileNameFromHeader != null) {
            DownloadManager.Request mimeType2 = request.setMimeType(URLConnection.guessContentTypeFromName(new File(fileNameFromHeader).getName()));
            Intrinsics.checkNotNullExpressionValue(mimeType2, "setMimeType(...)");
            return mimeType2;
        }
        String strResolveFileNameFromUrl = resolveFileNameFromUrl(url);
        if (strResolveFileNameFromUrl.length() > 0) {
            DownloadManager.Request mimeType3 = request.setMimeType(URLConnection.guessContentTypeFromName(new File(strResolveFileNameFromUrl).getName()));
            Intrinsics.checkNotNullExpressionValue(mimeType3, "setMimeType(...)");
            return mimeType3;
        }
        DownloadManager.Request mimeType4 = request.setMimeType(mimeType);
        Intrinsics.checkNotNullExpressionValue(mimeType4, "setMimeType(...)");
        return mimeType4;
    }
}
