package ru.mail.data.cmd.server;

import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
class CloudHostProvider implements HostProvider {
    private final HostProvider mBaseProvider;
    private final String mLoaderUrl;
    private final String[] mPathSegments;
    private final Map<String, String> mQueryParams = new HashMap();

    public CloudHostProvider(HostProvider hostProvider, String str, String... strArr) {
        this.mBaseProvider = hostProvider;
        this.mLoaderUrl = str;
        this.mPathSegments = strArr;
    }

    public void addQueryParam(String str, String str2) {
        this.mQueryParams.put(str, str2);
    }

    @Override // ru.mail.network.HostProvider
    public void getPlatformSpecificParams(Uri.Builder builder) {
        this.mBaseProvider.getPlatformSpecificParams(builder);
    }

    @Override // ru.mail.network.HostProvider
    public Uri.Builder getUrlBuilder() {
        Uri uri = Uri.parse(this.mLoaderUrl);
        Uri.Builder builderEncodedPath = new Uri.Builder().scheme(uri.getScheme()).encodedAuthority(uri.getEncodedAuthority()).encodedPath(uri.getPath());
        for (String str : this.mPathSegments) {
            builderEncodedPath.appendPath(str);
        }
        for (String str2 : this.mQueryParams.keySet()) {
            builderEncodedPath.appendQueryParameter(str2, this.mQueryParams.get(str2));
        }
        return builderEncodedPath;
    }

    @Override // ru.mail.network.HostProvider
    public String getUserAgent() {
        return this.mBaseProvider.getUserAgent();
    }

    @Override // ru.mail.network.HostProvider
    public void sign(Uri.Builder builder, HostProvider.SignCreator signCreator) {
        this.mBaseProvider.sign(builder, signCreator);
    }
}
