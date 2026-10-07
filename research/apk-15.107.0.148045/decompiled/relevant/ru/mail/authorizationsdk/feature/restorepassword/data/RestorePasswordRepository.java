package ru.mail.authorizationsdk.feature.restorepassword.data;

import android.net.Uri;
import android.webkit.CookieManager;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.di.modules.AuthDeviceId;
import ru.mail.authorizationsdk.di.modules.AuthDeviceUdid;
import ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.util.kotlin.cookie.MailCookie;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B%\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003J\u001e\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/restorepassword/data/RestorePasswordRepository;", "", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "", "deviceUdid", "urlsResolver", "Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;)V", "setupCookie", "", "tsaCookie", "getUrl", "email", "isRestoreVkidEnabled", "", "isRebind", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRestorePasswordRepository.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RestorePasswordRepository.kt\nru/mail/authorizationsdk/feature/restorepassword/data/RestorePasswordRepository\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,53:1\n1869#2,2:54\n*S KotlinDebug\n*F\n+ 1 RestorePasswordRepository.kt\nru/mail/authorizationsdk/feature/restorepassword/data/RestorePasswordRepository\n*L\n25#1:54,2\n*E\n"})
public final class RestorePasswordRepository {

    @NotNull
    private static final String COOKIE_DOMAIN = "auth.mail.ru";

    @NotNull
    private static final String DEVICE_COOKIE_NAME = "DeviceID";

    @NotNull
    private static final String GARAGE_COOKIE_NAME = "GarageID";

    @NotNull
    private static final String TSA_COOKIE_NAME = "tsa";

    @NotNull
    private final String deviceId;

    @NotNull
    private final String deviceUdid;

    @NotNull
    private final AuthorizationSdkUrlsResolver urlsResolver;
    public static final int $stable = 8;

    @Inject
    public RestorePasswordRepository(@AuthDeviceId @NotNull String deviceId, @AuthDeviceUdid @NotNull String deviceUdid, @NotNull AuthorizationSdkUrlsResolver urlsResolver) {
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        Intrinsics.checkNotNullParameter(deviceUdid, "deviceUdid");
        Intrinsics.checkNotNullParameter(urlsResolver, "urlsResolver");
        this.deviceId = deviceId;
        this.deviceUdid = deviceUdid;
        this.urlsResolver = urlsResolver;
    }

    @NotNull
    public final String getUrl(@NotNull String email, boolean isRestoreVkidEnabled, boolean isRebind) {
        Intrinsics.checkNotNullParameter(email, "email");
        Uri.Builder builderAppendQueryParameter = Uri.parse(this.urlsResolver.getRestorePasswordUrl()).buildUpon().appendQueryParameter("email", email).appendQueryParameter(PreferenceHostProvider.URL_PARAM_CLIENT, "mobile.app");
        if (isRebind) {
            builderAppendQueryParameter.appendQueryParameter("flow", "vk_rebind_mobile");
            builderAppendQueryParameter.appendQueryParameter("vkid_recovery", "1");
        } else if (isRestoreVkidEnabled) {
            builderAppendQueryParameter.appendQueryParameter("mobile_vkid_recovery", "1");
        }
        String string = builderAppendQueryParameter.build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    public final void setupCookie(@NotNull String tsaCookie) {
        Intrinsics.checkNotNullParameter(tsaCookie, "tsaCookie");
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (tsaCookie.length() > 0) {
            listCreateListBuilder.add(MailCookie.INSTANCE.newSecureInstance(TSA_COOKIE_NAME, tsaCookie, COOKIE_DOMAIN));
        }
        MailCookie.Companion companion = MailCookie.INSTANCE;
        listCreateListBuilder.add(companion.newSecureInstance("DeviceID", this.deviceId, COOKIE_DOMAIN));
        listCreateListBuilder.add(companion.newSecureInstance(GARAGE_COOKIE_NAME, this.deviceUdid, COOKIE_DOMAIN));
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        CookieManager cookieManager = CookieManager.getInstance();
        Iterator it = listBuild.iterator();
        while (it.hasNext()) {
            cookieManager.setCookie(this.urlsResolver.getAuthMailUrl(), ((MailCookie) it.next()).toRFC6265Format());
        }
    }
}
