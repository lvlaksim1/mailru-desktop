package ru.mail.logic.content;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.AttachCloud;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.data.entities.AttachLink;
import ru.mail.data.entities.Identifier;
import ru.mail.glasha.di.SharedFoldersModuleEntryPoint;
import ru.mail.network.HostProviderWrapper;
import ru.mail.network.HostProviderWrapperImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002¢\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\u000b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0019H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u001aH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u001bH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u001f\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016¢\u0006\u0002\u0010!R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lru/mail/logic/content/AttachUriBuilderVisitor;", "Lru/mail/logic/content/AttachUriBuilder$AttachUriVisitor;", "context", "Landroid/content/Context;", "mIsUnifiedDownloadEnabled", "", "<init>", "(Landroid/content/Context;Z)V", "mWrapper", "Lru/mail/network/HostProviderWrapper;", "visit", "Landroid/net/Uri;", "attach", "Lru/mail/data/entities/Attach;", "folderId", "", "(Lru/mail/data/entities/Attach;Ljava/lang/Long;)Landroid/net/Uri;", "shouldUseTornadoAttach", "(Ljava/lang/Long;)Z", "tornadoAttachRequestUri", "id", "Lru/mail/data/entities/Identifier;", "", "type", "legacyAttachUri", "Lru/mail/data/entities/AttachCloud;", "Lru/mail/data/entities/AttachCloudStock;", "Lru/mail/data/entities/AttachLink;", "localAttach", "Lru/mail/logic/content/MailAttacheEntry;", "buildUri", "information", "Lru/mail/logic/content/AttachInformation;", "(Lru/mail/logic/content/AttachInformation;Ljava/lang/Long;)Landroid/net/Uri;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAttachUriBuilderVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AttachUriBuilderVisitor.kt\nru/mail/logic/content/AttachUriBuilderVisitor\n+ 2 Uri.kt\nandroidx/core/net/UriKt\n*L\n1#1,109:1\n29#2:110\n29#2:111\n*S KotlinDebug\n*F\n+ 1 AttachUriBuilderVisitor.kt\nru/mail/logic/content/AttachUriBuilderVisitor\n*L\n54#1:110\n71#1:111\n*E\n"})
public final class AttachUriBuilderVisitor implements AttachUriBuilder.AttachUriVisitor {
    public static final int $stable = 8;

    @NotNull
    private final Context context;
    private final boolean mIsUnifiedDownloadEnabled;

    @NotNull
    private final HostProviderWrapper mWrapper;

    public AttachUriBuilderVisitor(@NotNull Context context, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mIsUnifiedDownloadEnabled = z10;
        this.mWrapper = new HostProviderWrapperImpl(context);
    }

    private final Uri legacyAttachUri(Attach attach) {
        String downloadLink = attach.getDownloadLink();
        Intrinsics.checkNotNullExpressionValue(downloadLink, "getDownloadLink(...)");
        Uri uri = Uri.parse(downloadLink);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(uri.getScheme()).authority(uri.getAuthority());
        Iterator<String> it = uri.getPathSegments().iterator();
        while (it.hasNext()) {
            builder.appendPath(it.next());
        }
        builder.encodedQuery(uri.getEncodedQuery());
        builder.appendQueryParameter("notype", "1");
        Uri uriBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }

    private final boolean shouldUseTornadoAttach(Long folderId) {
        return (AuthenticatorConfig.getInstance().isOAuthEnabled() || this.mIsUnifiedDownloadEnabled) && !SharedFoldersModuleEntryPoint.INSTANCE.folderGrantsManager(this.context).isSharedFolder(folderId);
    }

    private final Uri tornadoAttachRequestUri(Identifier<String> id2, String type) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(this.mWrapper.getSchemeOrHost(ru.mail.mails.R.string.mail_api_default_scheme)).authority(this.mWrapper.getSchemeOrHost(ru.mail.mails.R.string.mail_api_default_host)).appendEncodedPath("api/v1/messages/attaches/get").appendQueryParameter("type", type).appendQueryParameter("id", (String) id2.getId());
        Uri uriBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }

    @Override // ru.mail.logic.content.AttachUriBuilder.AttachUriVisitor
    @NotNull
    public Uri buildUri(@NotNull AttachInformation information, @Nullable Long folderId) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(information, "information");
        if (information instanceof Attach) {
            return visit((Attach) information, folderId);
        }
        if (information instanceof AttachLink) {
            return visit((AttachLink) information);
        }
        if (information instanceof MailAttacheEntry) {
            return visit((MailAttacheEntry) information);
        }
        if (information instanceof AttachCloud) {
            return visit((AttachCloud) information);
        }
        if (information instanceof AttachCloudStock) {
            return visit((AttachCloudStock) information);
        }
        throw new IllegalStateException("Unable to prepare uri");
    }

    @Override // ru.mail.logic.content.AttachUriBuilder.AttachUriVisitor
    @NotNull
    public Uri visit(@NotNull Attach attach, @Nullable Long folderId) {
        Intrinsics.checkNotNullParameter(attach, "attach");
        return shouldUseTornadoAttach(folderId) ? tornadoAttachRequestUri(attach, "attach") : legacyAttachUri(attach);
    }

    @Override // ru.mail.logic.content.AttachUriBuilder.AttachUriVisitor
    @NotNull
    public Uri visit(@NotNull AttachCloud attach) {
        Intrinsics.checkNotNullParameter(attach, "attach");
        if (this.mIsUnifiedDownloadEnabled) {
            return tornadoAttachRequestUri(attach, "cloud");
        }
        String dispatcherUrl = attach.getDispatcherUrl();
        Intrinsics.checkNotNullExpressionValue(dispatcherUrl, "getDispatcherUrl(...)");
        Uri uri = Uri.parse(dispatcherUrl);
        Uri uriBuild = new Uri.Builder().scheme(uri.getScheme()).authority(uri.getAuthority()).path(uri.getPath()).appendEncodedPath(attach.getStaticPartDownloadLink()).build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }

    @Override // ru.mail.logic.content.AttachUriBuilder.AttachUriVisitor
    @NotNull
    public Uri visit(@NotNull AttachCloudStock attach) {
        Intrinsics.checkNotNullParameter(attach, "attach");
        if (this.mIsUnifiedDownloadEnabled) {
            return tornadoAttachRequestUri(attach, "cloud_stock");
        }
        Uri uri = Uri.parse(attach.getUri());
        Intrinsics.checkNotNull(uri);
        return uri;
    }

    @Override // ru.mail.logic.content.AttachUriBuilder.AttachUriVisitor
    @NotNull
    public Uri visit(@NotNull AttachLink attach) {
        Intrinsics.checkNotNullParameter(attach, "attach");
        Uri uri = Uri.parse(attach.getDownloadLink());
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        return uri;
    }

    @Override // ru.mail.logic.content.AttachUriBuilder.AttachUriVisitor
    @NotNull
    public Uri visit(@NotNull MailAttacheEntry localAttach) {
        Intrinsics.checkNotNullParameter(localAttach, "localAttach");
        Uri uri = Uri.parse(localAttach.getUri());
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        return uri;
    }
}
