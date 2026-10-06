package ru.mail.serverapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.hostprovider.PreferenceHostInfoProvider;
import ru.mail.omicron.JsonObjectData;
import ru.mail.utils.safeutils.Handler;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0019BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0014J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0016H\u0002J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0005H\u0014J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/serverapi/MailHostInfoProvider;", "Lru/mail/network/hostprovider/PreferenceHostInfoProvider;", "platformInfo", "Lru/mail/serverapi/PlatformInfo;", "prefkey", "", "context", "Landroid/content/Context;", "defSchemeResId", "", "defHostResId", "requestOptions", "Landroid/os/Bundle;", "needPlatformParams", "", "needUserAgent", "<init>", "(Lru/mail/serverapi/PlatformInfo;Ljava/lang/String;Landroid/content/Context;IILandroid/os/Bundle;ZZ)V", "getPlatformParams", "", "Lorg/apache/http/NameValuePair;", "getLoginExperimentParams", "", "getAdvertisingId", "getAdvertisingIdFromSuper", "AdvertisingIdHandler", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailHostInfoProvider extends PreferenceHostInfoProvider {

    @NotNull
    private final PlatformInfo platformInfo;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/serverapi/MailHostInfoProvider$AdvertisingIdHandler;", "Lru/mail/utils/safeutils/Handler;", "Landroid/content/pm/PackageManager;", "", "mMailHostProvider", "Lru/mail/serverapi/MailHostInfoProvider;", "<init>", "(Lru/mail/serverapi/MailHostInfoProvider;)V", "call", MediationConfiguration.CUSTOM_EVENT_SERVER_PARAMETER_FIELD, "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class AdvertisingIdHandler implements Handler<PackageManager, String> {

        @NotNull
        private final MailHostInfoProvider mMailHostProvider;

        public AdvertisingIdHandler(@NotNull MailHostInfoProvider mMailHostProvider) {
            Intrinsics.checkNotNullParameter(mMailHostProvider, "mMailHostProvider");
            this.mMailHostProvider = mMailHostProvider;
        }

        @Override // ru.mail.utils.safeutils.Handler
        @Nullable
        public String call(@Nullable PackageManager parameter) {
            return this.mMailHostProvider.getAdvertisingIdFromSuper();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MailHostInfoProvider(@NotNull PlatformInfo platformInfo, @NotNull String prefkey, @NotNull Context context, int i10, int i11, @Nullable Bundle bundle, boolean z10, boolean z11) {
        super(prefkey, context, i10, i11, bundle, z10, z11);
        Intrinsics.checkNotNullParameter(platformInfo, "platformInfo");
        Intrinsics.checkNotNullParameter(prefkey, "prefkey");
        Intrinsics.checkNotNullParameter(context, "context");
        this.platformInfo = platformInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getAdvertisingIdFromSuper() {
        return super.getAdvertisingId();
    }

    private final List<NameValuePair> getLoginExperimentParams() {
        ArrayList arrayList = new ArrayList();
        Uri.Builder builder = new Uri.Builder();
        this.platformInfo.appendLoginExperiment(builder);
        Uri uriBuild = builder.build();
        for (String str : uriBuild.getQueryParameterNames()) {
            arrayList.add(new BasicNameValuePair(str, uriBuild.getQueryParameter(str)));
        }
        return arrayList;
    }

    @Override // ru.mail.network.hostprovider.PreferenceHostInfoProvider
    @Nullable
    protected String getAdvertisingId() {
        return (String) PackageManagerUtil.from(getContext().getApplicationContext()).doWithPackageManager(new AdvertisingIdHandler(this)).onErrorReturn(null).perform();
    }

    @Override // ru.mail.network.hostprovider.PreferenceHostInfoProvider
    @NotNull
    protected List<NameValuePair> getPlatformParams() {
        List<NameValuePair> platformParams = super.getPlatformParams();
        platformParams.add(new BasicNameValuePair("device_year", String.valueOf(this.platformInfo.getDeviceAge())));
        platformParams.add(new BasicNameValuePair("connection_class", this.platformInfo.getConnectionQuality()));
        platformParams.add(new BasicNameValuePair(PreferenceHostProvider.URL_PARAM_CURRENT, this.platformInfo.getCurrentDistributor()));
        platformParams.add(new BasicNameValuePair("dark", String.valueOf(this.platformInfo.getDarkThemeEnabled())));
        platformParams.add(new BasicNameValuePair(PreferenceHostProvider.URL_PARAM_FIRST, this.platformInfo.getFirstDistributor()));
        addParamIfExists(platformParams, "behaviorName", this.platformInfo.getBehaviorName());
        addParamIfExists(platformParams, "segments", TextUtils.join(",", this.platformInfo.getSegments()));
        addParamIfExists(platformParams, JsonObjectData.CONFIG_SHORT_SEGMENTS_KEY, TextUtils.join(",", this.platformInfo.getShortSegments()));
        platformParams.add(new BasicNameValuePair(PreferenceHostProvider.URL_PARAM_APPS_FLYER_ID, this.platformInfo.getAppsFlyerId()));
        platformParams.add(new BasicNameValuePair("reqmode", this.platformInfo.isAppBackgrounded() ? "bg" : "fg"));
        Iterator<NameValuePair> it = getLoginExperimentParams().iterator();
        while (it.hasNext()) {
            platformParams.add(it.next());
        }
        return platformParams;
    }

    public /* synthetic */ MailHostInfoProvider(PlatformInfo platformInfo, String str, Context context, int i10, int i11, Bundle bundle, boolean z10, boolean z11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this(platformInfo, str, context, i10, i11, bundle, z10, (i12 & 128) != 0 ? true : z11);
    }
}
