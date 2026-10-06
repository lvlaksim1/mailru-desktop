package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import ru.mail.mails.R;
import ru.mail.network.HostProviderConfiguration;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.analytics.Distributors;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class RbHostProvider extends PreferenceHostProvider {
    public RbHostProvider(Context context, String str) {
        super(context, str, R.string.rb_default_scheme, R.string.rb_default_host);
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected void getPlatformParams(Uri.Builder builder) {
        super.getPlatformParams(builder);
        builder.appendQueryParameter(PreferenceHostProvider.URL_PARAM_CURRENT, Distributors.CURRENT_DISTRIBUTOR).appendQueryParameter(PreferenceHostProvider.URL_PARAM_FIRST, Distributors.getFirstDistributor(getApplicationContext()).getName());
    }

    public RbHostProvider(Context context, String str, HostProviderConfiguration hostProviderConfiguration) {
        super(context, str, R.string.rb_default_scheme, R.string.rb_default_host, (Bundle) null, hostProviderConfiguration);
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected void appendIdfaParameter(Uri.Builder builder) {
    }
}
