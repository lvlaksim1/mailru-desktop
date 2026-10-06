package ru.mail.authorizationsdk.external.urls;

import android.content.Context;
import androidx.annotation.StringRes;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.auth.restore.RestoreConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.R;
import ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\b\u0010\f\u001a\u00020\tH\u0016J\u001c\u0010\r\u001a\u00020\t2\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u0010\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/external/urls/AuthorizationUrlProvider;", "Lru/mail/credentialsexchanger/data/network/urlprovider/UrlProvider;", "context", "Landroid/content/Context;", "isMiniMail", "", "<init>", "(Landroid/content/Context;Z)V", "getDefaultScheme", "", "getAjMailHost", "getAccountMailHost", "getAuthHost", "resolveHost", "defaultHostRes", "", "miniMailHostRes", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthorizationUrlProvider implements UrlProvider {
    public static final int $stable = 8;

    @NotNull
    private final Context context;
    private final boolean isMiniMail;

    public AuthorizationUrlProvider(@NotNull Context context, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.isMiniMail = z10;
    }

    private final String resolveHost(@StringRes int defaultHostRes, @StringRes int miniMailHostRes) {
        if (this.isMiniMail) {
            defaultHostRes = miniMailHostRes;
        }
        String string = this.context.getString(defaultHostRes);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getAccountMailHost() {
        return resolveHost(R.string.sdk_account_default_host, R.string.sdk_account_default_test_host);
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    /* JADX INFO: renamed from: getAjMailHost */
    public String getAltAjMailHost() {
        return resolveHost(R.string.sdk_auth_default_host, R.string.sdk_auth_test_default_host);
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getAuthHost() {
        return resolveHost(R.string.sdk_swa_default_host, R.string.sdk_swa_test_def_host);
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getDefaultScheme() {
        return RestoreConstants.DEFAULT_URL_SCHEME;
    }
}
