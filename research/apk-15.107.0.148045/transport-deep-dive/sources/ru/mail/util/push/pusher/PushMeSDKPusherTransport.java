package ru.mail.util.push.pusher;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.model.Application;
import com.vk.pushme.model.SpecifyTransportOption;
import com.vk.pushme.model.SubscriptionSettings;
import com.vk.pushme.model.Transport;
import com.vk.pushme.model.result.SubscriptionResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElementBuildersKt;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectBuilder;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.logic.pushfilters.FilterAccessor;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.util.analytics.Distributors;
import ru.mail.util.log.Log;
import ru.mail.util.push.PusherApplicationType;
import ru.mail.util.push.apps.PusherApplication;
import ru.mail.util.push.model.MultiAccountSettings;
import ru.mail.util.push.provider.CapabilitiesProvider;
import ru.mail.util.push.provider.PushInfoProvider;
import ru.mail.util.push.provider.PusherAppNameProvider;
import ru.mail.util.push.provider.factory.CapabilitiesProviderFactory;
import ru.mail.util.push.provider.impl.pushme.PushMeAppNameProvider;
import ru.mail.util.push.pusher.network.PusherInterface;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 02\u00020\u0001:\u00010B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0014H\u0014J,\u0010\u001a\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00140\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00032\u0006\u0010\u0019\u001a\u00020\u0014H\u0014J\u0018\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0018\u0010!\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u0004H\u0016J\u0010\u0010#\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0004H\u0016J\b\u0010$\u001a\u00020%H\u0014J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020'H\u0002J\f\u0010*\u001a\u00020'*\u00020+H\u0002J\f\u0010,\u001a\u00020\u0012*\u00020-H\u0002J\b\u0010.\u001a\u00020/H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lru/mail/util/push/pusher/PushMeSDKPusherTransport;", "Lru/mail/util/push/pusher/BasePusherTransport;", "pushInfoProviders", "", "Lru/mail/util/push/provider/PushInfoProvider;", "context", "Landroid/content/Context;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "isVkpnsSdkEnabledForAllApps", "", "<init>", "(Ljava/util/Collection;Landroid/content/Context;Lru/mail/deviceinfo/DeviceIdProvider;Z)V", "pusherAppNameProvider", "Lru/mail/util/push/provider/impl/pushme/PushMeAppNameProvider;", "getPusherAppNameProvider", "Lru/mail/util/push/provider/PusherAppNameProvider;", "unsubscribeByToken", "", "token", "", "registerMailAppForPushes", "Lru/mail/util/push/pusher/PusherTransport$Result;", "settings", "Lru/mail/util/push/model/MultiAccountSettings;", "userIdentifier", "registerPortalAppsForPushesInternal", "accounts", "apps", "Lru/mail/util/push/apps/PusherApplication;", "unsubscribeAppByDeviceId", "application", "Lru/mail/util/push/PusherApplicationType;", "checkIsTokenExists", "provider", "invalidateExpiredToken", "getInternalTransport", "Lru/mail/util/push/pusher/network/PusherInterface;", "createExtrasForMailApp", "Lkotlinx/serialization/json/JsonObject;", "account", "createExtrasForPortalApp", "toKotlinxJsonObject", "Lorg/json/JSONObject;", "addExtraField", "Lkotlinx/serialization/json/JsonObjectBuilder;", "getSpecifyTransportOptionForPortalApp", "Lcom/vk/pushme/model/SpecifyTransportOption;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMeSDKPusherTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeSDKPusherTransport.kt\nru/mail/util/push/pusher/PushMeSDKPusherTransport\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,257:1\n1563#2:258\n1634#2,3:259\n1563#2:262\n1634#2,3:263\n29#3,2:266\n31#3:269\n29#3,3:270\n1#4:268\n*S KotlinDebug\n*F\n+ 1 PushMeSDKPusherTransport.kt\nru/mail/util/push/pusher/PushMeSDKPusherTransport\n*L\n53#1:258\n53#1:259,3\n104#1:262\n104#1:263,3\n209#1:266,2\n209#1:269\n223#1:270,3\n*E\n"})
public final class PushMeSDKPusherTransport extends BasePusherTransport {

    @NotNull
    private static final String JSON_KEY_BADGE = "badge";

    @NotNull
    private static final String JSON_KEY_CAPABILITIES = "capabilities";

    @NotNull
    private static final String JSON_KEY_CLIENT = "client";

    @NotNull
    private static final String JSON_KEY_EXTRA = "extra";

    @NotNull
    private static final String JSON_KEY_IDFA = "idfa";

    @NotNull
    private static final String JSON_KEY_MODE = "mode";

    @NotNull
    private static final String JSON_KEY_MODE_UNREAD = "unread";

    @NotNull
    private static final String JSON_KEY_STATUS = "status";
    private final boolean isVkpnsSdkEnabledForAllApps;

    @NotNull
    private final PushMeAppNameProvider pusherAppNameProvider;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PushMeSDKPusherTransport");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeSDKPusherTransport(@NotNull Collection<? extends PushInfoProvider> pushInfoProviders, @NotNull Context context, @NotNull DeviceIdProvider deviceIdProvider, boolean z10) {
        super(pushInfoProviders, context, deviceIdProvider);
        Intrinsics.checkNotNullParameter(pushInfoProviders, "pushInfoProviders");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        this.isVkpnsSdkEnabledForAllApps = z10;
        this.pusherAppNameProvider = new PushMeAppNameProvider();
    }

    private final void addExtraField(JsonObjectBuilder jsonObjectBuilder) {
        JsonElementBuildersKt.putJsonObject(jsonObjectBuilder, "client", new Function1() { // from class: ru.mail.util.push.pusher.b
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushMeSDKPusherTransport.addExtraField$lambda$0((JsonObjectBuilder) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit addExtraField$lambda$0(JsonObjectBuilder putJsonObject) {
        Intrinsics.checkNotNullParameter(putJsonObject, "$this$putJsonObject");
        JsonElementBuildersKt.put(putJsonObject, JSON_KEY_EXTRA, Distributors.CURRENT_DISTRIBUTOR);
        return Unit.INSTANCE;
    }

    private final JsonObject createExtrasForMailApp(String account, MultiAccountSettings settings) {
        CapabilitiesProviderFactory capabilitiesProviderFactory = CapabilitiesProviderFactory.INSTANCE;
        boolean zIsPushesForMailAppEnabled = settings.isPushesForMailAppEnabled();
        FilterAccessor filterAccessor = settings.getFilterAccessor();
        Intrinsics.checkNotNullExpressionValue(filterAccessor, "getFilterAccessor(...)");
        CapabilitiesProvider capabilitiesProviderCreateProviderForMailApp = capabilitiesProviderFactory.createProviderForMailApp(zIsPushesForMailAppEnabled, filterAccessor, settings.isImportantReminderEnabled());
        Set<Tag> enabledTagsForMailApp = settings.getEnabledTagsForMailApp();
        Intrinsics.checkNotNullExpressionValue(enabledTagsForMailApp, "getEnabledTagsForMailApp(...)");
        JSONObject capabilities = capabilitiesProviderCreateProviderForMailApp.getCapabilities(account, enabledTagsForMailApp);
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        jsonObjectBuilder.put(JSON_KEY_CAPABILITIES, toKotlinxJsonObject(capabilities));
        JsonElementBuildersKt.putJsonObject(jsonObjectBuilder, "badge", new Function1() { // from class: ru.mail.util.push.pusher.c
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PushMeSDKPusherTransport.createExtrasForMailApp$lambda$0$0((JsonObjectBuilder) obj);
            }
        });
        addExtraField(jsonObjectBuilder);
        String advertisingId = getAdvertisingId();
        if (advertisingId != null) {
            if (StringsKt.isBlank(advertisingId)) {
                advertisingId = null;
            }
            if (advertisingId != null) {
                JsonElementBuildersKt.put(jsonObjectBuilder, "idfa", advertisingId);
            }
        }
        return jsonObjectBuilder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createExtrasForMailApp$lambda$0$0(JsonObjectBuilder putJsonObject) {
        Intrinsics.checkNotNullParameter(putJsonObject, "$this$putJsonObject");
        JsonElementBuildersKt.put(putJsonObject, "status", Boolean.TRUE);
        JsonElementBuildersKt.put(putJsonObject, "mode", "unread");
        return Unit.INSTANCE;
    }

    private final JsonObject createExtrasForPortalApp() {
        JsonObjectBuilder jsonObjectBuilder = new JsonObjectBuilder();
        addExtraField(jsonObjectBuilder);
        return jsonObjectBuilder.build();
    }

    private final SpecifyTransportOption getSpecifyTransportOptionForPortalApp() {
        return this.isVkpnsSdkEnabledForAllApps ? SpecifyTransportOption.AllTransports.INSTANCE : new SpecifyTransportOption.Exclude(Transport.VKPNS);
    }

    private final JsonObject toKotlinxJsonObject(JSONObject jSONObject) {
        String string = jSONObject.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return JsonElementKt.getJsonObject(Json.INSTANCE.parseToJsonElement(string));
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport, ru.mail.util.push.pusher.PusherTransport
    public boolean checkIsTokenExists(@NotNull String userIdentifier, @NotNull PushInfoProvider provider) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(provider, "provider");
        return true;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherInterface getInternalTransport() {
        throw new IllegalStateException("You must not call this method if PushMe SDK is enabled");
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherAppNameProvider getPusherAppNameProvider() {
        return this.pusherAppNameProvider;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport, ru.mail.util.push.pusher.PusherTransport
    @NotNull
    public PusherTransport.Result invalidateExpiredToken(@NotNull PushInfoProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        return PusherTransport.Result.OK.INSTANCE;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherTransport.Result registerMailAppForPushes(@NotNull MultiAccountSettings settings, @NotNull String userIdentifier) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        ArrayList arrayList = new ArrayList(settings.getAccounts().size());
        Set<Tag> enabledTagsForMailApp = settings.getEnabledTagsForMailApp();
        Intrinsics.checkNotNullExpressionValue(enabledTagsForMailApp, "getEnabledTagsForMailApp(...)");
        Set<Tag> set = enabledTagsForMailApp;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList2.add(Integer.valueOf(((Tag) it.next()).getIdForPusher()));
        }
        Set set2 = CollectionsKt.toSet(arrayList2);
        for (String str : settings.getAccounts()) {
            Intrinsics.checkNotNull(str);
            arrayList.add(new Application.AccountRequest(str, new SubscriptionSettings(set2, null, createExtrasForMailApp(str, settings), SpecifyTransportOption.AllTransports.INSTANCE)));
        }
        SubscriptionResult subscriptionResultExecute = PushMeSdk.INSTANCE.getApp().registerAccounts(arrayList).execute();
        if (Intrinsics.areEqual(subscriptionResultExecute, SubscriptionResult.OK.INSTANCE)) {
            LOG.i("Register mail app for " + arrayList.size() + " accounts success");
            return PusherTransport.Result.OK.INSTANCE;
        }
        if (!(subscriptionResultExecute instanceof SubscriptionResult.NoAuthError)) {
            if (subscriptionResultExecute instanceof SubscriptionResult.MissingPushTokenError) {
                LOG.e("Unable to register mail app: no push token found");
                return PusherTransport.Result.UnknownError.INSTANCE;
            }
            if (!(subscriptionResultExecute instanceof SubscriptionResult.UnknownError)) {
                throw new NoWhenBranchMatchedException();
            }
            LOG.e("Unable to register mail app: ", ((SubscriptionResult.UnknownError) subscriptionResultExecute).getT());
            return PusherTransport.Result.UnknownError.INSTANCE;
        }
        SubscriptionResult.NoAuthError noAuthError = (SubscriptionResult.NoAuthError) subscriptionResultExecute;
        LOG.e("Handle no auth error for: " + CollectionsKt.joinToString$default(noAuthError.getFailedAccounts(), null, null, null, 0, null, null, 63, null));
        if (noAuthError.getFailedAccounts().size() == 1) {
            return new PusherTransport.Result.NoAuth((String) CollectionsKt.first(noAuthError.getFailedAccounts()));
        }
        return noAuthError.getFailedAccounts().size() > 1 ? new PusherTransport.Result.NoAuthMultiple(noAuthError.getFailedAccounts()) : PusherTransport.Result.UnknownError.INSTANCE;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport
    @NotNull
    protected PusherTransport.Result registerPortalAppsForPushesInternal(@NotNull Collection<String> accounts, @NotNull Collection<PusherApplication> apps, @NotNull String userIdentifier) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(apps, "apps");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        JsonObject jsonObjectCreateExtrasForPortalApp = createExtrasForPortalApp();
        SpecifyTransportOption specifyTransportOptionForPortalApp = getSpecifyTransportOptionForPortalApp();
        boolean z10 = false;
        for (PusherApplication pusherApplication : apps) {
            String pusherAppName = this.pusherAppNameProvider.getPusherAppName(pusherApplication.getType());
            Set<Tag> enabledTags = pusherApplication.getEnabledTags();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(enabledTags, 10));
            Iterator<T> it = enabledTags.iterator();
            while (it.hasNext()) {
                arrayList.add(Integer.valueOf(((Tag) it.next()).getIdForPusher()));
            }
            Set set = CollectionsKt.toSet(arrayList);
            ArrayList arrayList2 = new ArrayList(accounts.size());
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<String> it2 = accounts.iterator();
            while (it2.hasNext()) {
                String account = getAccount(it2.next(), pusherApplication.getType());
                if (account == null || StringsKt.isBlank(account)) {
                    LOG.e("Unable to get account for app " + pusherApplication.getType());
                } else if (!linkedHashSet.contains(account)) {
                    arrayList2.add(new Application.AccountRequest(account, new SubscriptionSettings(set, null, jsonObjectCreateExtrasForPortalApp, specifyTransportOptionForPortalApp)));
                    linkedHashSet.add(account);
                }
            }
            if (arrayList2.isEmpty()) {
                LOG.w("No accounts found to register for pushes");
            } else {
                SubscriptionResult subscriptionResultExecute = PushMeSdk.INSTANCE.getApp(pusherAppName).registerAccounts(arrayList2).execute();
                if (Intrinsics.areEqual(subscriptionResultExecute, SubscriptionResult.OK.INSTANCE)) {
                    LOG.i("Register app " + pusherApplication + " for " + arrayList2.size() + " accounts success");
                } else {
                    if (subscriptionResultExecute instanceof SubscriptionResult.NoAuthError) {
                        LOG.e("Handle no auth error for app " + pusherApplication + ": " + CollectionsKt.joinToString$default(((SubscriptionResult.NoAuthError) subscriptionResultExecute).getFailedAccounts(), null, null, null, 0, null, null, 63, null));
                    } else if (subscriptionResultExecute instanceof SubscriptionResult.MissingPushTokenError) {
                        LOG.e("Unable to register app " + pusherApplication + ": no push token found");
                    } else {
                        if (!(subscriptionResultExecute instanceof SubscriptionResult.UnknownError)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        LOG.e("Unable to register app " + pusherApplication + ": ", ((SubscriptionResult.UnknownError) subscriptionResultExecute).getT());
                    }
                    z10 = true;
                }
            }
        }
        return !z10 ? PusherTransport.Result.OK.INSTANCE : PusherTransport.Result.UnknownError.INSTANCE;
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport, ru.mail.util.push.pusher.PusherTransport
    public void unsubscribeAppByDeviceId(@NotNull String userIdentifier, @NotNull PusherApplicationType application) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(application, "application");
        Log log = LOG;
        log.i("Unsubscribing app " + application.name() + " by device ID for user " + userIdentifier);
        String pusherAppName = getPusherAppNameProvider().getPusherAppName(application);
        String account = getAccount(userIdentifier, application);
        if (account == null || StringsKt.isBlank(account)) {
            log.w("Could not get account for app " + application + ", userIdentified = " + userIdentifier);
            return;
        }
        log.i("Unsubscribe result for " + userIdentifier + " is " + PushMeSdk.INSTANCE.getApp(pusherAppName).unregisterAccount(account).execute());
    }

    @Override // ru.mail.util.push.pusher.BasePusherTransport, ru.mail.util.push.pusher.PusherTransport
    public void unsubscribeByToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        PushMeSdk.INSTANCE.getApp().unsubscribeByToken(token);
    }
}
