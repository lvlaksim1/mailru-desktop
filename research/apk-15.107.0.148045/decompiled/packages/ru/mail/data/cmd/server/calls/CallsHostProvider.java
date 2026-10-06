package ru.mail.data.cmd.server.calls;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.mailapp.R;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsHostProvider;", "Lru/mail/network/HostProvider;", "wrappedProvider", "applicationContext", "Landroid/content/Context;", "<init>", "(Lru/mail/network/HostProvider;Landroid/content/Context;)V", "getPlatformSpecificParams", "", "url", "Landroid/net/Uri$Builder;", "sign", "builder", "signCreator", "Lru/mail/network/HostProvider$SignCreator;", "getUrlBuilder", "getUserAgent", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class CallsHostProvider implements HostProvider {
    public static final int $stable = 8;

    @NotNull
    private final Context applicationContext;

    @NotNull
    private final HostProvider wrappedProvider;

    public CallsHostProvider(@NotNull HostProvider wrappedProvider, @NotNull Context applicationContext) {
        Intrinsics.checkNotNullParameter(wrappedProvider, "wrappedProvider");
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.wrappedProvider = wrappedProvider;
        this.applicationContext = applicationContext;
    }

    @Override // ru.mail.network.HostProvider
    public void getPlatformSpecificParams(@NotNull Uri.Builder url) {
        Intrinsics.checkNotNullParameter(url, "url");
        this.wrappedProvider.getPlatformSpecificParams(url);
    }

    @Override // ru.mail.network.HostProvider
    @NotNull
    public Uri.Builder getUrlBuilder() {
        Uri.Builder builderEncodedAuthority = new Uri.Builder().scheme(this.applicationContext.getString(R.string.calls_default_scheme)).encodedAuthority(this.applicationContext.getString(R.string.calls_default_host));
        Intrinsics.checkNotNullExpressionValue(builderEncodedAuthority, "encodedAuthority(...)");
        return builderEncodedAuthority;
    }

    @Override // ru.mail.network.HostProvider
    @NotNull
    public String getUserAgent() {
        String userAgent = this.wrappedProvider.getUserAgent();
        Intrinsics.checkNotNullExpressionValue(userAgent, "getUserAgent(...)");
        return userAgent;
    }

    @Override // ru.mail.network.HostProvider
    public void sign(@NotNull Uri.Builder builder, @NotNull HostProvider.SignCreator signCreator) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(signCreator, "signCreator");
        this.wrappedProvider.sign(builder, signCreator);
    }
}
