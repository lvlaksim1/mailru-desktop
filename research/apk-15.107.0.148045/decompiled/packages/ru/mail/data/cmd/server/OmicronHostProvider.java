package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.StringRes;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.mails.R;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.analytics.Partnership;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class OmicronHostProvider extends PreferenceHostProvider {
    public OmicronHostProvider(Context context, String str) {
        super(context, str, R.string.omicron_api_default_scheme, R.string.omicron_api_default_host);
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected void getPlatformParams(Uri.Builder builder) {
        super.getPlatformParams(builder);
        builder.appendQueryParameter("current_app_source", Distributors.CURRENT_DISTRIBUTOR).appendQueryParameter("first_app_source", Distributors.getFirstDistributor(getApplicationContext()).getName());
        Partnership partnership = Partnership.INSTANCE;
        String partnership2 = partnership.getPartnership(getApplicationContext());
        if (TextUtils.isEmpty(partnership2)) {
            return;
        }
        builder.appendQueryParameter(RbParams.Default.URL_PARAM_KEY_PARTNERSHIP, partnership2).appendQueryParameter(RbParams.Default.URL_PARAM_KEY_DAYS_INSTALLED, String.valueOf(partnership.getDaysInstalled(getApplicationContext())));
    }

    @Override // ru.mail.network.PreferenceHostProvider
    protected boolean shouldAppendDeviceId() {
        return false;
    }

    public OmicronHostProvider(@NotNull Context context, @NotNull String str, @StringRes int i10, @StringRes int i11) {
        super(context, str, i10, i11);
    }
}
