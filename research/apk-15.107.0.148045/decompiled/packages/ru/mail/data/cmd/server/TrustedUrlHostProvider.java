package ru.mail.data.cmd.server;

import android.content.Context;
import android.os.Bundle;
import ru.mail.mailapp.R;
import ru.mail.network.HostProviderConfiguration;
import ru.mail.serverapi.MailHostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TrustedUrlHostProvider extends MailHostProvider {
    public TrustedUrlHostProvider(Context context) {
        super(context, "trusted", R.string.mail_api_default_scheme, R.string.mail_api_default_host, (Bundle) null, new HostProviderConfiguration(false, false, true), new PlatformInfoImpl(context));
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected String getClientParameterValue() {
        return "mobile.app";
    }
}
