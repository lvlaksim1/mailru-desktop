package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.OauthParams;
import ru.mail.network.HostProvider;
import ru.mail.network.UrlPath;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lru/mail/auth/request/OAuthAccessRefreshYahoo;", "Lru/mail/auth/request/OAuthAccessRefresh;", "context", "Landroid/content/Context;", "hostProvider", "Lru/mail/network/HostProvider;", "mailParams", "Lru/mail/OauthParams;", "refreshToken", "", "<init>", "(Landroid/content/Context;Lru/mail/network/HostProvider;Lru/mail/OauthParams;Ljava/lang/String;)V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {})
public final class OAuthAccessRefreshYahoo extends OAuthAccessRefresh {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OAuthAccessRefreshYahoo(@NotNull Context context, @NotNull HostProvider hostProvider, @NotNull OauthParams mailParams, @NotNull String refreshToken) {
        super(context, hostProvider, mailParams, new OAuthAccessRefresh.Params(mailParams.getClientId(), refreshToken, mailParams.getSecretId()));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(hostProvider, "hostProvider");
        Intrinsics.checkNotNullParameter(mailParams, "mailParams");
        Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
    }
}
