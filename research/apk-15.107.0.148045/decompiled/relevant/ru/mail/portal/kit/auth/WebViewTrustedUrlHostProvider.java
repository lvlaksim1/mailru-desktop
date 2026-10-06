package ru.mail.portal.kit.auth;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.mails.R;
import ru.mail.network.HostProviderConfiguration;
import ru.mail.serverapi.MailHostProvider;
import ru.mail.webcomponent.params.PlatformParamsProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lru/mail/portal/kit/auth/WebViewTrustedUrlHostProvider;", "Lru/mail/serverapi/MailHostProvider;", "Lru/mail/webcomponent/params/PlatformParamsProvider;", "appContext", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "shouldAppendParamsRequestedInBackgroundThread", "", "getClientParameterValue", "", "appendPlatformParams", "", "url", "Landroid/net/Uri$Builder;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewTrustedUrlHostProvider extends MailHostProvider implements PlatformParamsProvider {
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WebViewTrustedUrlHostProvider(@NotNull Context appContext) {
        super(appContext, "trusted", R.string.mail_api_default_scheme, R.string.mail_api_default_host, (Bundle) null, new HostProviderConfiguration(false, false, true), new WebViewPlatformInfo(appContext));
        Intrinsics.checkNotNullParameter(appContext, "appContext");
    }

    @Override // ru.mail.webcomponent.params.PlatformParamsProvider
    public void appendPlatformParams(@NotNull Uri.Builder url) {
        Intrinsics.checkNotNullParameter(url, "url");
        super.getPlatformParams(url);
    }

    @Override // ru.mail.network.PreferenceHostProvider
    @NotNull
    protected String getClientParameterValue() {
        return "mobile.app";
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected boolean shouldAppendParamsRequestedInBackgroundThread() {
        return false;
    }
}
