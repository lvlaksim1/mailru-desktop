package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import ru.mail.network.HostProvider;
import ru.mail.serverapi.MailHostProvider;
import ru.mail.util.log.Log;
import ru.mail.utils.UriUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class PingPongHostProvider extends MailHostProvider {
    public static final Log LOG = Log.getLog("MailApplication");
    private static final String PREF_KEY = "ping";
    private Uri.Builder mBuilder;
    private final boolean mNeedRemovePlatformParams;
    private final Uri mRawUri;

    public PingPongHostProvider(Context context, Uri uri, boolean z10) {
        super(context, PREF_KEY, new PlatformInfoImpl(context));
        this.mRawUri = uri;
        this.mNeedRemovePlatformParams = z10;
    }

    private Uri.Builder getBuilder() {
        if (this.mBuilder == null) {
            Uri.Builder builder = new Uri.Builder();
            getPlatformSpecificParams(builder);
            this.mBuilder = UriUtils.mergeQueryParameters(this.mRawUri, builder.build()).buildUpon();
        }
        return this.mBuilder;
    }

    @Override // ru.mail.network.PreferenceHostProvider
    public String getHost() {
        return getPreferences().getString(getHostKey(), "");
    }

    @Override // ru.mail.serverapi.MailHostProvider, ru.mail.network.PreferenceHostProvider
    public void getPlatformParams(Uri.Builder builder) {
        if (this.mNeedRemovePlatformParams) {
            return;
        }
        super.getPlatformParams(builder);
    }

    @Override // ru.mail.network.PreferenceHostProvider
    public String getScheme() {
        return getPreferences().getString(getSchemeKey(), "");
    }

    @Override // ru.mail.network.PreferenceHostProvider, ru.mail.network.HostProvider
    public Uri.Builder getUrlBuilder() {
        return (TextUtils.isEmpty(getScheme()) || TextUtils.isEmpty(getHost())) ? getBuilder() : super.getUrlBuilder();
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected void signRequest(Uri.Builder builder, HostProvider.SignCreator signCreator) {
        if (this.mNeedRemovePlatformParams) {
            return;
        }
        super.signRequest(builder, signCreator);
    }
}
