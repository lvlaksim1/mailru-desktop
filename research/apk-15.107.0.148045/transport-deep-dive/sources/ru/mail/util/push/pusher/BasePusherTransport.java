package ru.mail.util.push.pusher;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.util.log.Log;
import ru.mail.util.push.PusherApplicationType;
import ru.mail.util.push.apps.PusherApplication;
import ru.mail.util.push.apps.state.PortalAppsNotificationsStateManagerImpl;
import ru.mail.util.push.apps.state.State;
import ru.mail.util.push.model.MultiAccountSettings;
import ru.mail.util.push.provider.AdvertisingIdProvider;
import ru.mail.util.push.provider.AuthProvider;
import ru.mail.util.push.provider.PushInfoProvider;
import ru.mail.util.push.provider.PusherAccountProvider;
import ru.mail.util.push.provider.PusherAppNameProvider;
import ru.mail.util.push.provider.impl.AdvertisingIdProviderImpl;
import ru.mail.util.push.provider.impl.AuthProviderImpl;
import ru.mail.util.push.provider.impl.DeviceIdProviderImpl;
import ru.mail.util.push.provider.impl.PusherAccountProviderImpl;
import ru.mail.util.push.pusher.network.PusherInterface;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b'\u0018\u0000 @2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001@B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0011\u001a\u00020\u0012H$J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH$J,\u0010\u001c\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J,\u0010 \u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00052\u0006\u0010\u0019\u001a\u00020\u001aH$J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0006H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010%\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020$2\u0006\u0010)\u001a\u00020\u001aH\u0016J\u0018\u0010*\u001a\u00020+2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u0006H\u0016J\b\u0010,\u001a\u00020-H\u0004J\b\u0010.\u001a\u00020/H\u0004J\b\u00100\u001a\u000201H$J\b\u00102\u001a\u00020$H\u0016J\u0010\u00103\u001a\u00020$2\u0006\u00104\u001a\u000205H\u0016J\n\u00106\u001a\u0004\u0018\u000105H\u0016J\n\u00107\u001a\u0004\u0018\u000105H\u0016J\b\u00108\u001a\u00020$H\u0016J\u0010\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020;H\u0004J\u000e\u0010<\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0005H\u0002J\u001b\u0010=\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020'H\u0096\u0001J\u000b\u0010?\u001a\u0004\u0018\u00010\u001aH\u0096\u0001R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lru/mail/util/push/pusher/BasePusherTransport;", "Lru/mail/util/push/pusher/PusherTransport;", "Lru/mail/util/push/provider/PusherAccountProvider;", "Lru/mail/util/push/provider/AdvertisingIdProvider;", "pushInfoProviders", "", "Lru/mail/util/push/provider/PushInfoProvider;", "context", "Landroid/content/Context;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "<init>", "(Ljava/util/Collection;Landroid/content/Context;Lru/mail/deviceinfo/DeviceIdProvider;)V", "getPushInfoProviders", "()Ljava/util/Collection;", "getContext", "()Landroid/content/Context;", "getInternalTransport", "Lru/mail/util/push/pusher/network/PusherInterface;", "appsStateManager", "Lru/mail/util/push/apps/state/PortalAppsNotificationsStateManagerImpl;", "registerForPushes", "Lru/mail/util/push/pusher/PusherTransport$Result;", "settings", "Lru/mail/util/push/model/MultiAccountSettings;", "userIdentifier", "", "registerMailAppForPushes", "registerPortalAppsForPushes", "accounts", "apps", "Lru/mail/util/push/apps/PusherApplication;", "registerPortalAppsForPushesInternal", "invalidateExpiredToken", "provider", "unsubscribeAllByDeviceId", "", "unsubscribeAppByDeviceId", "application", "Lru/mail/util/push/PusherApplicationType;", "unsubscribeByToken", "token", "checkIsTokenExists", "", "getDeviceIdProvider", "Lru/mail/util/push/provider/DeviceIdProvider;", "getAuthProvider", "Lru/mail/util/push/provider/AuthProvider;", "getPusherAppNameProvider", "Lru/mail/util/push/provider/PusherAppNameProvider;", "syncPortalAppsIfNeeded", "saveSyncedPortalAppsState", "state", "Lru/mail/util/push/apps/state/State;", "getLastSyncedPortalAppsState", "getLastLocalPortalAppsState", "clearSavedPortalAppsState", "mapResult", "result", "Lru/mail/util/push/pusher/network/PusherInterface$PushResult;", "getPortalAppsWithEnabledNotifications", "getAccount", "app", "getAdvertisingId", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBasePusherTransport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BasePusherTransport.kt\nru/mail/util/push/pusher/BasePusherTransport\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,202:1\n1869#2,2:203\n1869#2,2:205\n*S KotlinDebug\n*F\n+ 1 BasePusherTransport.kt\nru/mail/util/push/pusher/BasePusherTransport\n*L\n90#1:203,2\n120#1:205,2\n*E\n"})
public abstract class BasePusherTransport implements PusherTransport, PusherAccountProvider, AdvertisingIdProvider {
    private final /* synthetic */ PusherAccountProviderImpl $$delegate_0;
    private final /* synthetic */ AdvertisingIdProviderImpl $$delegate_1;

    @NotNull
    private final PortalAppsNotificationsStateManagerImpl appsStateManager;

    @NotNull
    private final Context context;

    @NotNull
    private final DeviceIdProvider deviceIdProvider;

    @NotNull
    private final Collection<PushInfoProvider> pushInfoProviders;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("BasePusherTransport");

    /* JADX WARN: Multi-variable type inference failed */
    public BasePusherTransport(@NotNull Collection<? extends PushInfoProvider> pushInfoProviders, @NotNull Context context, @NotNull DeviceIdProvider deviceIdProvider) {
        Intrinsics.checkNotNullParameter(pushInfoProviders, "pushInfoProviders");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        this.$$delegate_0 = new PusherAccountProviderImpl();
        this.$$delegate_1 = new AdvertisingIdProviderImpl(context);
        this.pushInfoProviders = pushInfoProviders;
        this.context = context;
        this.deviceIdProvider = deviceIdProvider;
        this.appsStateManager = new PortalAppsNotificationsStateManagerImpl(context);
    }

    private final Collection<PusherApplication> getPortalAppsWithEnabledNotifications() {
        return this.appsStateManager.getCurrentState().getAppsWithEnabledNotifications();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence registerPortalAppsForPushes$lambda$0(PusherApplication it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getType().name();
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    public boolean checkIsTokenExists(@NotNull String userIdentifier, @NotNull PushInfoProvider provider) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(provider, "provider");
        Log log = LOG;
        log.i("Checking is token exists for user " + userIdentifier);
        String pushToken = provider.getPushToken();
        if (pushToken == null || StringsKt.isBlank(pushToken)) {
            log.w("Push token is missing");
            throw new IllegalStateException("Push token is missing");
        }
        boolean zCheckIsTokenExists = getInternalTransport().checkIsTokenExists(pushToken, userIdentifier);
        log.i("Check token exists result: " + zCheckIsTokenExists);
        return zCheckIsTokenExists;
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    public void clearSavedPortalAppsState() {
        this.appsStateManager.clearSavedState();
    }

    @Override // ru.mail.util.push.provider.PusherAccountProvider
    @Nullable
    public String getAccount(@NotNull String userIdentifier, @NotNull PusherApplicationType app) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(app, "app");
        return this.$$delegate_0.getAccount(userIdentifier, app);
    }

    @Override // ru.mail.util.push.provider.AdvertisingIdProvider
    @Nullable
    public String getAdvertisingId() {
        return this.$$delegate_1.getAdvertisingId();
    }

    @NotNull
    protected final AuthProvider getAuthProvider() {
        return new AuthProviderImpl(this.context);
    }

    @NotNull
    protected final Context getContext() {
        return this.context;
    }

    @NotNull
    protected final ru.mail.util.push.provider.DeviceIdProvider getDeviceIdProvider() {
        return new DeviceIdProviderImpl(this.context, this.deviceIdProvider);
    }

    @NotNull
    protected abstract PusherInterface getInternalTransport();

    @Override // ru.mail.util.push.pusher.PusherTransport
    @Nullable
    public State getLastLocalPortalAppsState() {
        return this.appsStateManager.getLastLocalState();
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    @Nullable
    public State getLastSyncedPortalAppsState() {
        return this.appsStateManager.getLastSyncedState();
    }

    @NotNull
    protected final Collection<PushInfoProvider> getPushInfoProviders() {
        return this.pushInfoProviders;
    }

    @NotNull
    protected abstract PusherAppNameProvider getPusherAppNameProvider();

    @Override // ru.mail.util.push.pusher.PusherTransport
    @NotNull
    public PusherTransport.Result invalidateExpiredToken(@NotNull PushInfoProvider provider) {
        Intrinsics.checkNotNullParameter(provider, "provider");
        String expiredPushToken = provider.getExpiredPushToken();
        if (expiredPushToken == null || StringsKt.isBlank(expiredPushToken)) {
            LOG.i("Expired token is null, invalidation is not required");
            return PusherTransport.Result.OK.INSTANCE;
        }
        String pushToken = provider.getPushToken();
        if (pushToken == null || StringsKt.isBlank(pushToken) || Intrinsics.areEqual(pushToken, expiredPushToken)) {
            LOG.i("Actual token is missing or it equals expired token");
            return PusherTransport.Result.OK.INSTANCE;
        }
        unsubscribeByToken(expiredPushToken);
        return PusherTransport.Result.OK.INSTANCE;
    }

    @NotNull
    protected final PusherTransport.Result mapResult(@NotNull PusherInterface.PushResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (Intrinsics.areEqual(result, PusherInterface.PushResult.OK.INSTANCE)) {
            return PusherTransport.Result.OK.INSTANCE;
        }
        if (result instanceof PusherInterface.PushResult.NoAuth) {
            return new PusherTransport.Result.NoAuth(((PusherInterface.PushResult.NoAuth) result).getUserIdentifier());
        }
        if (result instanceof PusherInterface.PushResult.NoAuthMultiple) {
            return new PusherTransport.Result.NoAuthMultiple(((PusherInterface.PushResult.NoAuthMultiple) result).getUserIdentifiers());
        }
        if (Intrinsics.areEqual(result, PusherInterface.PushResult.UnknownError.INSTANCE)) {
            return PusherTransport.Result.UnknownError.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    @NotNull
    public PusherTransport.Result registerForPushes(@NotNull MultiAccountSettings settings, @NotNull String userIdentifier) {
        Intrinsics.checkNotNullParameter(settings, "settings");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        LOG.i("Register device for pusher");
        this.appsStateManager.syncAppsIfNeeded();
        return registerMailAppForPushes(settings, userIdentifier);
    }

    @NotNull
    protected abstract PusherTransport.Result registerMailAppForPushes(@NotNull MultiAccountSettings settings, @NotNull String userIdentifier);

    @Override // ru.mail.util.push.pusher.PusherTransport
    @NotNull
    public PusherTransport.Result registerPortalAppsForPushes(@NotNull Collection<String> accounts, @NotNull Collection<PusherApplication> apps, @NotNull String userIdentifier) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(apps, "apps");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        LOG.i("Register specific apps for pusher: " + CollectionsKt.joinToString$default(apps, null, null, null, 0, null, new Function1() { // from class: ru.mail.util.push.pusher.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BasePusherTransport.registerPortalAppsForPushes$lambda$0((PusherApplication) obj);
            }
        }, 31, null));
        return registerPortalAppsForPushesInternal(accounts, apps, userIdentifier);
    }

    @NotNull
    protected abstract PusherTransport.Result registerPortalAppsForPushesInternal(@NotNull Collection<String> accounts, @NotNull Collection<PusherApplication> apps, @NotNull String userIdentifier);

    @Override // ru.mail.util.push.pusher.PusherTransport
    public void saveSyncedPortalAppsState(@NotNull State state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this.appsStateManager.saveSyncedState(state);
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    public void syncPortalAppsIfNeeded() {
        this.appsStateManager.syncAppsIfNeeded();
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    public void unsubscribeAllByDeviceId(@NotNull String userIdentifier) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Iterator<T> it = getPortalAppsWithEnabledNotifications().iterator();
        while (it.hasNext()) {
            unsubscribeAppByDeviceId(userIdentifier, ((PusherApplication) it.next()).getType());
        }
        unsubscribeAppByDeviceId(userIdentifier, PusherApplicationType.MAIL);
        LOG.i("All apps were unsubscribed by device ID for " + userIdentifier);
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    public void unsubscribeAppByDeviceId(@NotNull String userIdentifier, @NotNull PusherApplicationType application) {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(application, "application");
        Log log = LOG;
        log.i("Unsubscribing app " + application.name() + " by device ID for user " + userIdentifier);
        String deviceId = getDeviceIdProvider().getDeviceId();
        String account = getAccount(userIdentifier, application);
        if (account == null || StringsKt.isBlank(account)) {
            log.w("Could not get account for app " + application + ", userIdentified = " + userIdentifier);
            return;
        }
        String pusherAppName = getPusherAppNameProvider().getPusherAppName(application);
        PusherInterface.PushResult pushResultUnregisterWithDeviceId = getInternalTransport().unregisterWithDeviceId(account, pusherAppName, deviceId);
        log.i("Unsubscribing app " + application.name() + " (pusher name: " + pusherAppName + ") result: " + pushResultUnregisterWithDeviceId);
    }

    @Override // ru.mail.util.push.pusher.PusherTransport
    public void unsubscribeByToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        for (PusherApplication pusherApplication : getPortalAppsWithEnabledNotifications()) {
            PusherInterface.PushResult pushResultUnregisterWithPushToken = getInternalTransport().unregisterWithPushToken(getPusherAppNameProvider().getPusherAppName(pusherApplication.getType()), token);
            LOG.i("Invalidate token for " + pusherApplication.getType().name() + " result: " + pushResultUnregisterWithPushToken);
        }
        PusherInterface.PushResult pushResultUnregisterWithPushToken2 = getInternalTransport().unregisterWithPushToken(getPusherAppNameProvider().getPusherAppName(PusherApplicationType.MAIL), token);
        LOG.i("Invalidate token for mail app result: " + pushResultUnregisterWithPushToken2);
    }
}
