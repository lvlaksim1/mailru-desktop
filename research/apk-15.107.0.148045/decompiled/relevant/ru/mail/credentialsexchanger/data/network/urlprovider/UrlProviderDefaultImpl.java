package ru.mail.credentialsexchanger.data.network.urlprovider;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.auth.restore.RestoreConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lru/mail/credentialsexchanger/data/network/urlprovider/UrlProviderDefaultImpl;", "Lru/mail/credentialsexchanger/data/network/urlprovider/UrlProvider;", "<init>", "()V", "getDefaultScheme", "", "getAjMailHost", "getAccountMailHost", "getAuthHost", "credentials-exchanger_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UrlProviderDefaultImpl implements UrlProvider {
    public static final int $stable = 0;

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getAccountMailHost() {
        return "account.mail.ru";
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getAjMailHost() {
        return "alt-aj-https.mail.ru";
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getAuthHost() {
        return "alt-auth.mail.ru";
    }

    @Override // ru.mail.credentialsexchanger.data.network.urlprovider.UrlProvider
    @NotNull
    public String getDefaultScheme() {
        return RestoreConstants.DEFAULT_URL_SCHEME;
    }
}
