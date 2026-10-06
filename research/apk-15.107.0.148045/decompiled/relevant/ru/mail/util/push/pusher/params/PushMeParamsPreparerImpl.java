package ru.mail.util.push.pusher.params;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushType;
import ru.mail.util.push.PusherApplicationType;
import ru.mail.util.push.model.Badge;
import ru.mail.util.push.provider.AdvertisingIdProvider;
import ru.mail.util.push.provider.AuthProvider;
import ru.mail.util.push.provider.BadgeProvider;
import ru.mail.util.push.provider.CapabilitiesProvider;
import ru.mail.util.push.provider.ClientInfoProvider;
import ru.mail.util.push.provider.DeviceIdProvider;
import ru.mail.util.push.provider.PushInfoProvider;
import ru.mail.util.push.provider.PusherAccountProvider;
import ru.mail.util.push.provider.PusherAppNameProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 52\u00020\u0001:\u00015BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\rH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJT\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010#\u001a\u0004\u0018\u00010!2\u0006\u0010$\u001a\u00020!2\u0006\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020!2\u0006\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,H\u0002J*\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020!2\b\u0010/\u001a\u0004\u0018\u00010!2\u0006\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020!H\u0002J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u000204H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lru/mail/util/push/pusher/params/PushMeParamsPreparerImpl;", "Lru/mail/util/push/pusher/params/PushParamsPreparer;", "advertisingIdProvider", "Lru/mail/util/push/provider/AdvertisingIdProvider;", "badgeProvider", "Lru/mail/util/push/provider/BadgeProvider;", "capabilitiesProvider", "Lru/mail/util/push/provider/CapabilitiesProvider;", "clientInfoProvider", "Lru/mail/util/push/provider/ClientInfoProvider;", "deviceIdProvider", "Lru/mail/util/push/provider/DeviceIdProvider;", "pushInfoProviders", "", "Lru/mail/util/push/provider/PushInfoProvider;", "authProvider", "Lru/mail/util/push/provider/AuthProvider;", "pusherAppNameProvider", "Lru/mail/util/push/provider/PusherAppNameProvider;", "pusherAccountProvider", "Lru/mail/util/push/provider/PusherAccountProvider;", "<init>", "(Lru/mail/util/push/provider/AdvertisingIdProvider;Lru/mail/util/push/provider/BadgeProvider;Lru/mail/util/push/provider/CapabilitiesProvider;Lru/mail/util/push/provider/ClientInfoProvider;Lru/mail/util/push/provider/DeviceIdProvider;Ljava/util/Collection;Lru/mail/util/push/provider/AuthProvider;Lru/mail/util/push/provider/PusherAppNameProvider;Lru/mail/util/push/provider/PusherAccountProvider;)V", "preparePushSettings", "Lkotlin/Result;", "Lorg/json/JSONArray;", "params", "Lru/mail/util/push/pusher/params/SubscribeParams;", "preparePushSettings-IoAF18A", "(Ljava/util/Collection;)Ljava/lang/Object;", "fillRootJSON", "Lorg/json/JSONObject;", "account", "", CommonConstant.KEY_ACCESS_TOKEN, "cookie", "platform", "pushToken", "settings", "pusherAppName", "androidId", "sdkDeviceId", "getPlatform", "type", "Lru/mail/util/push/PushType;", "getSettingsJSON", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "idfa", "subscribeParams", "userIdentifier", "getBadgeJSON", "badge", "Lru/mail/util/push/model/Badge;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMeParamsPreparerImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeParamsPreparerImpl.kt\nru/mail/util/push/pusher/params/PushMeParamsPreparerImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,209:1\n774#2:210\n865#2,2:211\n1#3:213\n*S KotlinDebug\n*F\n+ 1 PushMeParamsPreparerImpl.kt\nru/mail/util/push/pusher/params/PushMeParamsPreparerImpl\n*L\n53#1:210\n53#1:211,2\n*E\n"})
public final class PushMeParamsPreparerImpl implements PushParamsPreparer {

    @NotNull
    private static final String JSON_HUAWEI_VALUE_PLATFORM = "huawei";

    @NotNull
    private static final String JSON_KEY_ACCESS_TOKEN = "access_token";

    @NotNull
    private static final String JSON_KEY_ACCOUNT = "account";

    @NotNull
    private static final String JSON_KEY_ANDROID_ID = "android_id";

    @NotNull
    private static final String JSON_KEY_APPLICATION = "application";

    @NotNull
    private static final String JSON_KEY_BADGE = "badge";

    @NotNull
    private static final String JSON_KEY_BADGE_MODE = "mode";

    @NotNull
    private static final String JSON_KEY_BADGE_STATUS = "status";

    @NotNull
    private static final String JSON_KEY_CAPABILITIES = "capabilities";

    @NotNull
    private static final String JSON_KEY_CLIENT = "client";

    @NotNull
    private static final String JSON_KEY_DEVICE_ID = "device_id";

    @NotNull
    private static final String JSON_KEY_IDFA = "idfa";

    @NotNull
    private static final String JSON_KEY_MPOP = "mpop";

    @NotNull
    private static final String JSON_KEY_PLATFORM = "platform";

    @NotNull
    private static final String JSON_KEY_SDK_DEVICE_ID = "sdk_device_id";

    @NotNull
    private static final String JSON_KEY_SETTINGS = "settings";

    @NotNull
    private static final String JSON_KEY_STATUS = "status";

    @NotNull
    private static final String JSON_KEY_TOKEN = "token";

    @NotNull
    private static final String JSON_VALUE_BADGE_MODE_UNREAD = "unread";

    @NotNull
    private static final String JSON_VALUE_PLATFORM = "android";

    @NotNull
    private static final String JSON_VKPNS_VALUE_PLATFORM = "vkpns";
    private static final int STATUS_ON = 0;

    @NotNull
    private final AdvertisingIdProvider advertisingIdProvider;

    @NotNull
    private final AuthProvider authProvider;

    @NotNull
    private final BadgeProvider badgeProvider;

    @NotNull
    private final CapabilitiesProvider capabilitiesProvider;

    @NotNull
    private final ClientInfoProvider clientInfoProvider;

    @NotNull
    private final DeviceIdProvider deviceIdProvider;

    @NotNull
    private final Collection<PushInfoProvider> pushInfoProviders;

    @NotNull
    private final PusherAccountProvider pusherAccountProvider;

    @NotNull
    private final PusherAppNameProvider pusherAppNameProvider;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PushMeParamsPreparerImpl");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[PushType.values().length];
            try {
                iArr[PushType.HMS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushType.VKPNS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Badge.Mode.values().length];
            try {
                iArr2[Badge.Mode.UNREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PushMeParamsPreparerImpl(@NotNull AdvertisingIdProvider advertisingIdProvider, @NotNull BadgeProvider badgeProvider, @NotNull CapabilitiesProvider capabilitiesProvider, @NotNull ClientInfoProvider clientInfoProvider, @NotNull DeviceIdProvider deviceIdProvider, @NotNull Collection<? extends PushInfoProvider> pushInfoProviders, @NotNull AuthProvider authProvider, @NotNull PusherAppNameProvider pusherAppNameProvider, @NotNull PusherAccountProvider pusherAccountProvider) {
        Intrinsics.checkNotNullParameter(advertisingIdProvider, "advertisingIdProvider");
        Intrinsics.checkNotNullParameter(badgeProvider, "badgeProvider");
        Intrinsics.checkNotNullParameter(capabilitiesProvider, "capabilitiesProvider");
        Intrinsics.checkNotNullParameter(clientInfoProvider, "clientInfoProvider");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        Intrinsics.checkNotNullParameter(pushInfoProviders, "pushInfoProviders");
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
        Intrinsics.checkNotNullParameter(pusherAppNameProvider, "pusherAppNameProvider");
        Intrinsics.checkNotNullParameter(pusherAccountProvider, "pusherAccountProvider");
        this.advertisingIdProvider = advertisingIdProvider;
        this.badgeProvider = badgeProvider;
        this.capabilitiesProvider = capabilitiesProvider;
        this.clientInfoProvider = clientInfoProvider;
        this.deviceIdProvider = deviceIdProvider;
        this.pushInfoProviders = pushInfoProviders;
        this.authProvider = authProvider;
        this.pusherAppNameProvider = pusherAppNameProvider;
        this.pusherAccountProvider = pusherAccountProvider;
    }

    private final JSONObject fillRootJSON(String account, String accessToken, String cookie, String platform, String pushToken, JSONObject settings, String pusherAppName, String androidId, String sdkDeviceId) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", 0);
        jSONObject.put("account", account);
        jSONObject.put("platform", platform);
        jSONObject.put("application", pusherAppName);
        jSONObject.put("token", pushToken);
        jSONObject.put("settings", settings);
        jSONObject.put("access_token", accessToken);
        jSONObject.put(JSON_KEY_MPOP, cookie);
        jSONObject.put("android_id", androidId);
        jSONObject.put(JSON_KEY_SDK_DEVICE_ID, sdkDeviceId);
        return jSONObject;
    }

    private final JSONObject getBadgeJSON(Badge badge) throws JSONException {
        if (WhenMappings.$EnumSwitchMapping$1[badge.getMode().ordinal()] != 1) {
            throw new NoWhenBranchMatchedException();
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", badge.getStatus());
        jSONObject.put("mode", "unread");
        return jSONObject;
    }

    private final String getPlatform(PushType type) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[type.ordinal()];
        if (i10 != 1) {
            return i10 != 2 ? "android" : "vkpns";
        }
        return "huawei";
    }

    private final JSONObject getSettingsJSON(String deviceId, String idfa, SubscribeParams subscribeParams, String userIdentifier) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (subscribeParams.getApplication() == PusherApplicationType.MAIL) {
            jSONObject.put("badge", getBadgeJSON(this.badgeProvider.getBadge()));
        }
        jSONObject.put(JSON_KEY_CAPABILITIES, this.capabilitiesProvider.getCapabilities(userIdentifier, subscribeParams.getEnabledTags()));
        jSONObject.put("client", new JSONObject(this.clientInfoProvider.getClientInfo()));
        jSONObject.put("device_id", deviceId);
        if (idfa != null) {
            if (StringsKt.isBlank(idfa)) {
                idfa = null;
            }
            if (idfa != null) {
                jSONObject.put("idfa", idfa);
            }
        }
        return jSONObject;
    }

    @Override // ru.mail.util.push.pusher.params.PushParamsPreparer
    @NotNull
    /* JADX INFO: renamed from: preparePushSettings-IoAF18A, reason: not valid java name */
    public Object mo15887preparePushSettingsIoAF18A(@NotNull Collection<SubscribeParams> params) {
        Collection<SubscribeParams> arrayList;
        PushMeParamsPreparerImpl pushMeParamsPreparerImpl = this;
        Collection<SubscribeParams> params2 = params;
        Intrinsics.checkNotNullParameter(params2, "params");
        String deviceId = pushMeParamsPreparerImpl.deviceIdProvider.getDeviceId();
        String androidId = pushMeParamsPreparerImpl.deviceIdProvider.getAndroidId();
        String sdkDeviceId = pushMeParamsPreparerImpl.deviceIdProvider.getSdkDeviceId();
        String advertisingId = pushMeParamsPreparerImpl.advertisingIdProvider.getAdvertisingId();
        JSONArray jSONArray = new JSONArray();
        for (PushInfoProvider pushInfoProvider : pushMeParamsPreparerImpl.pushInfoProviders) {
            String pushToken = pushInfoProvider.getPushToken();
            if (pushToken == null || StringsKt.isBlank(pushToken)) {
                LOG.w("Push token is missing for provider " + pushInfoProvider.getPushType());
            } else {
                String platform = pushMeParamsPreparerImpl.getPlatform(pushInfoProvider.getPushType());
                if (pushInfoProvider.isPushSdkEnabledForAllApps()) {
                    arrayList = params2;
                } else {
                    arrayList = new ArrayList();
                    for (Object obj : params2) {
                        if (((SubscribeParams) obj).getApplication() == PusherApplicationType.MAIL) {
                            arrayList.add(obj);
                        }
                    }
                }
                for (SubscribeParams subscribeParams : arrayList) {
                    PusherApplicationType application = subscribeParams.getApplication();
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    for (String str : subscribeParams.getUserIdentifiers()) {
                        AuthProvider.TokenResult serviceToken = pushMeParamsPreparerImpl.authProvider.getServiceToken(str);
                        if (serviceToken == null) {
                            String str2 = "Failed to get service token for account: " + str;
                            LOG.w(str2);
                            Result.Companion companion = Result.INSTANCE;
                            return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalStateException(str2)));
                        }
                        String str3 = platform;
                        String str4 = pushToken;
                        String token = serviceToken.getType() == AuthProvider.TokenResult.TokenType.ACCESS_TOKEN ? serviceToken.getToken() : null;
                        String token2 = serviceToken.getType() == AuthProvider.TokenResult.TokenType.MPOP_COOKIE ? serviceToken.getToken() : null;
                        String account = pushMeParamsPreparerImpl.pusherAccountProvider.getAccount(str, application);
                        if (account == null || StringsKt.isBlank(account)) {
                            String str5 = "Could not create params for " + application + " due to missing account";
                            LOG.w(str5);
                            Result.Companion companion2 = Result.INSTANCE;
                            return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalStateException(str5)));
                        }
                        if (linkedHashSet.contains(account)) {
                            platform = str3;
                            pushToken = str4;
                        } else {
                            linkedHashSet.add(account);
                            platform = str3;
                            pushToken = str4;
                            jSONArray.put(pushMeParamsPreparerImpl.fillRootJSON(account, token, token2, platform, pushToken, pushMeParamsPreparerImpl.getSettingsJSON(deviceId, advertisingId, subscribeParams, account), pushMeParamsPreparerImpl.pusherAppNameProvider.getPusherAppName(application), androidId, sdkDeviceId));
                            pushMeParamsPreparerImpl = this;
                            application = application;
                            subscribeParams = subscribeParams;
                            linkedHashSet = linkedHashSet;
                        }
                    }
                    pushMeParamsPreparerImpl = this;
                }
                LOG.i("Prepare push params for provider " + pushInfoProvider.getPushType() + " is successful");
            }
            pushMeParamsPreparerImpl = this;
            params2 = params;
        }
        if (jSONArray.length() != 0) {
            return Result.m13123constructorimpl(jSONArray);
        }
        LOG.e("Unable to create push params: result JSON array is empty");
        Result.Companion companion3 = Result.INSTANCE;
        return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalStateException("Unable to create push params: result JSON array is empty")));
    }
}
