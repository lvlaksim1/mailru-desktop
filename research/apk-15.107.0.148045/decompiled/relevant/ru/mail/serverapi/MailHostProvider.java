package ru.mail.serverapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import ru.mail.ads.info.provider.api.AdvertisingInfoProvider;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.deviceinfo.DeviceInfoFactory;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.omicron.JsonObjectData;
import ru.mail.utils.FirebaseInfoProvider;
import ru.mail.utils.safeutils.Handler;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailHostProvider extends PreferenceHostProvider {
    private final PlatformInfo mPlatformInfo;

    /* JADX INFO: compiled from: ProGuard */
    private static class AdvertisingIdHandler implements Handler<PackageManager, String> {
        private final MailHostProvider mMailHostProvider;

        public AdvertisingIdHandler(MailHostProvider mailHostProvider) {
            this.mMailHostProvider = mailHostProvider;
        }

        @Override // ru.mail.utils.safeutils.Handler
        public String call(PackageManager packageManager) {
            return this.mMailHostProvider.getAdvertisingIdFromSuper();
        }
    }

    public MailHostProvider(Context context, String str, PlatformInfo platformInfo) {
        super(context, str, R.string.mail_api_default_scheme, R.string.mail_api_default_host);
        this.mPlatformInfo = platformInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getAdvertisingIdFromSuper() {
        return super.getAdvertisingId();
    }

    @Override // ru.mail.network.PreferenceHostProvider
    public String getAdvertisingId() {
        return (String) PackageManagerUtil.from(getApplicationContext()).doWithPackageManager(new AdvertisingIdHandler(this)).onErrorReturn(null).perform();
    }

    @Override // ru.mail.network.PreferenceHostProvider
    public void getPlatformParams(Uri.Builder builder) {
        super.getPlatformParams(builder);
        builder.appendQueryParameter("device_year", String.valueOf(this.mPlatformInfo.getDeviceAge()));
        builder.appendQueryParameter("connection_class", this.mPlatformInfo.getConnectionQuality());
        builder.appendQueryParameter(PreferenceHostProvider.URL_PARAM_CURRENT, this.mPlatformInfo.getCurrentDistributor());
        builder.appendQueryParameter("dark", String.valueOf(this.mPlatformInfo.getDarkThemeEnabled()));
        builder.appendQueryParameter(PreferenceHostProvider.URL_PARAM_FIRST, this.mPlatformInfo.getFirstDistributor());
        appendParamIfExists(builder, "behaviorName", this.mPlatformInfo.getBehaviorName());
        appendParamIfExists(builder, "segments", TextUtils.join(",", this.mPlatformInfo.getSegments()));
        appendParamIfExists(builder, JsonObjectData.CONFIG_SHORT_SEGMENTS_KEY, TextUtils.join(",", this.mPlatformInfo.getShortSegments()));
        builder.appendQueryParameter(PreferenceHostProvider.URL_PARAM_APPS_FLYER_ID, this.mPlatformInfo.getAppsFlyerId());
        builder.appendQueryParameter("reqmode", this.mPlatformInfo.isAppBackgrounded() ? "bg" : "fg");
        this.mPlatformInfo.appendLoginExperiment(builder);
    }

    @VisibleForTesting
    public MailHostProvider(Context context, DeviceIdProvider deviceIdProvider, DeviceInfoFactory deviceInfoFactory, AdvertisingInfoProvider advertisingInfoProvider, FirebaseInfoProvider firebaseInfoProvider, String str, PlatformInfo platformInfo) {
        super(context, deviceIdProvider, deviceInfoFactory, advertisingInfoProvider, firebaseInfoProvider, str, R.string.mail_api_default_scheme, R.string.mail_api_default_host);
        this.mPlatformInfo = platformInfo;
    }

    public MailHostProvider(Context context, String str, int i10, int i11, PlatformInfo platformInfo) {
        super(context, str, i10, i11);
        this.mPlatformInfo = platformInfo;
    }

    public MailHostProvider(Context context, String str, int i10, int i11, Bundle bundle, HostProvider.Configuration configuration, PlatformInfo platformInfo) {
        super(context, str, i10, i11, bundle, configuration);
        this.mPlatformInfo = platformInfo;
    }

    public MailHostProvider(Context context, HostProviderAnnotation hostProviderAnnotation, Bundle bundle, PlatformInfo platformInfo) {
        super(context, hostProviderAnnotation, bundle);
        this.mPlatformInfo = platformInfo;
    }
}
