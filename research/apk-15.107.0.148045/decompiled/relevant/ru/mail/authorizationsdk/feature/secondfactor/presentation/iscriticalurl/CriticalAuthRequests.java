package ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl;

import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver;
import ru.mail.data.cmd.server.AttachLinkLoadCommand;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/authorizationsdk/feature/secondfactor/presentation/iscriticalurl/CriticalAuthRequests;", "", "urlsResolver", "Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;", "<init>", "(Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;)V", ApiUris.AUTHORITY_API, "Lru/mail/authorizationsdk/feature/secondfactor/presentation/iscriticalurl/UriMatcher;", "captcha", AttachLinkLoadCommand.STATIC, "list", "", "isCriticalUrl", "", "uri", "Landroid/net/Uri;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCriticalAuthRequests.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CriticalAuthRequests.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/iscriticalurl/CriticalAuthRequests\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,30:1\n1#2:31\n1761#3,3:32\n*S KotlinDebug\n*F\n+ 1 CriticalAuthRequests.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/iscriticalurl/CriticalAuthRequests\n*L\n25#1:32,3\n*E\n"})
public final class CriticalAuthRequests {
    public static final int $stable = 8;

    @NotNull
    private final UriMatcher api;

    @NotNull
    private final UriMatcher captcha;

    @NotNull
    private final List<UriMatcher> list;

    @NotNull
    private final UriMatcher static;

    @NotNull
    private final AuthorizationSdkUrlsResolver urlsResolver;

    @Inject
    public CriticalAuthRequests(@NotNull AuthorizationSdkUrlsResolver urlsResolver) {
        Intrinsics.checkNotNullParameter(urlsResolver, "urlsResolver");
        this.urlsResolver = urlsResolver;
        UriMatcher uriMatcher = new UriMatcher() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl.a
            @Override // ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl.UriMatcher
            public final boolean matches(Uri uri) {
                return CriticalAuthRequests.api$lambda$0(uri);
            }
        };
        this.api = uriMatcher;
        UriMatcher uriMatcher2 = new UriMatcher() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl.b
            @Override // ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl.UriMatcher
            public final boolean matches(Uri uri) {
                return CriticalAuthRequests.captcha$lambda$0(this.f82755a, uri);
            }
        };
        this.captcha = uriMatcher2;
        UriMatcher uriMatcher3 = new UriMatcher() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl.c
            @Override // ru.mail.authorizationsdk.feature.secondfactor.presentation.iscriticalurl.UriMatcher
            public final boolean matches(Uri uri) {
                return CriticalAuthRequests.static$lambda$0(uri);
            }
        };
        this.static = uriMatcher3;
        this.list = CollectionsKt.listOf((Object[]) new UriMatcher[]{uriMatcher, uriMatcher2, uriMatcher3});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean api$lambda$0(Uri toCheck) {
        Intrinsics.checkNotNullParameter(toCheck, "toCheck");
        String path = toCheck.getPath();
        return path != null && (StringsKt.contains$default((CharSequence) path, (CharSequence) "cgi-bin/secstep", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) path, (CharSequence) "cgi-bin/auth", false, 2, (Object) null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean captcha$lambda$0(CriticalAuthRequests criticalAuthRequests, Uri toCheck) {
        Intrinsics.checkNotNullParameter(toCheck, "toCheck");
        return Intrinsics.areEqual(toCheck.getAuthority(), criticalAuthRequests.urlsResolver.getCaptchaHost());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean static$lambda$0(Uri toCheck) {
        Intrinsics.checkNotNullParameter(toCheck, "toCheck");
        String lastPathSegment = toCheck.getLastPathSegment();
        return lastPathSegment != null && (StringsKt.endsWith$default(lastPathSegment, ".js", false, 2, (Object) null) || StringsKt.endsWith$default(lastPathSegment, ".css", false, 2, (Object) null));
    }

    public final boolean isCriticalUrl(@Nullable Uri uri) {
        if (uri != null) {
            List<UriMatcher> list = this.list;
            if ((list instanceof Collection) && list.isEmpty()) {
                return false;
            }
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((UriMatcher) it.next()).matches(uri)) {
                    return true;
                }
            }
        }
        return false;
    }
}
