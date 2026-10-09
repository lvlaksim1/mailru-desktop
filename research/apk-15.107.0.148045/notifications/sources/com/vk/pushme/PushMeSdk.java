package com.vk.pushme;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.WorkerThread;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.ExistingWorkPolicy;
import com.fasterxml.jackson.core.io.doubleparser.FastDoubleMath;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.analytcis.StubAnalyticsHandler;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.PushMeSdkDatabase;
import com.vk.pushme.database.dao.PushDao;
import com.vk.pushme.database.dao.PushTokenDao;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.logic.ApplicationImpl;
import com.vk.pushme.logic.usecase.DeleteTokenUseCase;
import com.vk.pushme.logic.usecase.NewTokenUseCase;
import com.vk.pushme.logic.usecase.PushClickedUseCase;
import com.vk.pushme.logic.usecase.PushReceivedUseCase;
import com.vk.pushme.logic.usecase.SendAnalyticsUseCase;
import com.vk.pushme.logic.usecase.SubscriptionUseCase;
import com.vk.pushme.logic.usecase.UnsubscribeUseCase;
import com.vk.pushme.model.Application;
import com.vk.pushme.model.Transport;
import com.vk.pushme.network.AnalyticsApi;
import com.vk.pushme.network.ApiVersion;
import com.vk.pushme.network.HostInfoProvider;
import com.vk.pushme.network.NetworkModule;
import com.vk.pushme.network.PushMeApi;
import com.vk.pushme.provider.AuthProvider;
import com.vk.pushme.util.ManifestReader;
import com.vk.pushme.util.provider.impl.ClientInfoProviderImpl;
import com.vk.pushme.util.provider.impl.DeviceIdProviderImpl;
import com.vk.pushme.work.ShrinkWorker;
import com.vk.pushme.work.SyncWorker;
import com.vk.pushme.work.util.WorkSchedulerImpl;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\b\u000f\u0018\u0000 j2\u00020\u0001:\u0001jB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u0012H\u0002J\u0010\u0010S\u001a\u00020J2\u0006\u0010T\u001a\u00020MH\u0002J\u0018\u0010U\u001a\u00020J2\u0006\u0010V\u001a\u00020M2\u0006\u0010W\u001a\u00020XH\u0002J+\u0010Y\u001a\u0004\u0018\u00010Z2\u0012\u0010[\u001a\u000e\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020M0\\2\u0006\u0010W\u001a\u00020XH\u0003¢\u0006\u0002\u0010]J\u0017\u0010^\u001a\u00020J2\b\u0010_\u001a\u0004\u0018\u00010ZH\u0002¢\u0006\u0002\u0010`J\u0010\u0010a\u001a\u00020O2\u0006\u0010T\u001a\u00020MH\u0002J\b\u0010b\u001a\u00020JH\u0002J\b\u0010c\u001a\u00020JH\u0002J\b\u0010d\u001a\u00020JH\u0002J\u0010\u0010e\u001a\u00020J2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010f\u001a\u00020J2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0010\u0010g\u001a\u00020J2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010h\u001a\u00020JH\u0002J\u0010\u0010i\u001a\u0002042\u0006\u0010\b\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007@BX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0013\u001a\u00020\u00148@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0019\u001a\u00020\u001a8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0018\u001a\u0004\b\u001b\u0010\u001cR\u001b\u0010\u001e\u001a\u00020\u001f8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0018\u001a\u0004\b \u0010!R\u001b\u0010#\u001a\u00020$8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0018\u001a\u0004\b%\u0010&R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010\u0018\u001a\u0004\b*\u0010+R\u001a\u0010-\u001a\u00020.X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u000e\u00103\u001a\u000204X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u00105\u001a\u0002068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u0018\u001a\u0004\b7\u00108R\u001b\u0010:\u001a\u00020;8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b>\u0010\u0018\u001a\u0004\b<\u0010=R\u001b\u0010?\u001a\u00020@8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bC\u0010\u0018\u001a\u0004\bA\u0010BR\u001b\u0010D\u001a\u00020E8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\u0018\u001a\u0004\bF\u0010GR\u0010\u0010L\u001a\u0004\u0018\u00010MX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010N\u001a\u00020O8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bR\u0010\u0018\u001a\u0004\bP\u0010Q¨\u0006k"}, d2 = {"Lcom/vk/pushme/PushMeSdk;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "value", "Lcom/vk/pushme/PushMeSdkConfig;", "config", "getConfig$push_me_sdk_release", "()Lcom/vk/pushme/PushMeSdkConfig;", "setConfig", "(Lcom/vk/pushme/PushMeSdkConfig;)V", "database", "Lcom/vk/pushme/database/PushMeSdkDatabase;", "getDatabase$push_me_sdk_release", "()Lcom/vk/pushme/database/PushMeSdkDatabase;", "authProvider", "Lcom/vk/pushme/provider/AuthProvider;", "subscriptionUseCase", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;", "getSubscriptionUseCase$push_me_sdk_release", "()Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;", "subscriptionUseCase$delegate", "Lkotlin/Lazy;", "unsubscribeUseCase", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;", "getUnsubscribeUseCase$push_me_sdk_release", "()Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;", "unsubscribeUseCase$delegate", "deleteTokenUseCase", "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase;", "getDeleteTokenUseCase$push_me_sdk_release", "()Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase;", "deleteTokenUseCase$delegate", "sendAnalyticsUseCase", "Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase;", "getSendAnalyticsUseCase$push_me_sdk_release", "()Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase;", "sendAnalyticsUseCase$delegate", "workScheduler", "Lcom/vk/pushme/work/util/WorkSchedulerImpl;", "getWorkScheduler", "()Lcom/vk/pushme/work/util/WorkSchedulerImpl;", "workScheduler$delegate", "logger", "Lcom/vk/pushme/common/Logger;", "getLogger$push_me_sdk_release", "()Lcom/vk/pushme/common/Logger;", "setLogger$push_me_sdk_release", "(Lcom/vk/pushme/common/Logger;)V", "pusherApi", "Lcom/vk/pushme/network/PushMeApi;", "analyticsApi", "Lcom/vk/pushme/network/AnalyticsApi;", "getAnalyticsApi", "()Lcom/vk/pushme/network/AnalyticsApi;", "analyticsApi$delegate", "newTokenUseCase", "Lcom/vk/pushme/logic/usecase/NewTokenUseCase;", "getNewTokenUseCase", "()Lcom/vk/pushme/logic/usecase/NewTokenUseCase;", "newTokenUseCase$delegate", "pushReceivedUseCase", "Lcom/vk/pushme/logic/usecase/PushReceivedUseCase;", "getPushReceivedUseCase", "()Lcom/vk/pushme/logic/usecase/PushReceivedUseCase;", "pushReceivedUseCase$delegate", "pushClickedUseCase", "Lcom/vk/pushme/logic/usecase/PushClickedUseCase;", "getPushClickedUseCase", "()Lcom/vk/pushme/logic/usecase/PushClickedUseCase;", "pushClickedUseCase$delegate", "setAuthProvider", "", "provider", "defaultApplicationName", "", "defaultApplication", "Lcom/vk/pushme/model/Application;", "getDefaultApplication", "()Lcom/vk/pushme/model/Application;", "defaultApplication$delegate", "setDefaultApplication", "name", "onNewToken", "token", "transport", "Lcom/vk/pushme/model/Transport;", "onMessageReceived", "", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "", "(Ljava/util/Map;Lcom/vk/pushme/model/Transport;)Ljava/lang/Long;", "onNotificationClicked", "pushId", "(Ljava/lang/Long;)V", "createApplication", "onInitialized", "onConfigChanged", "readManifestProperties", "enqueueWorkers", "enqueueOneTimeSyncWorker", "enqueuePeriodicSyncWorker", "enqueueShrinkWorker", "createPusherApi", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMeSdk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeSdk.kt\ncom/vk/pushme/PushMeSdk\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,346:1\n1#2:347\n*E\n"})
public final class PushMeSdk {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String DEFAULT_APPLICATION_MANIFEST_KEY = "com.vk.pushme.default_application";
    private static final int SHRINK_INTERVAL_IN_HOURS = 24;

    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static PushMeSdk _instance;

    /* JADX INFO: renamed from: analyticsApi$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy analyticsApi;

    @Nullable
    private AuthProvider authProvider;

    @NotNull
    private PushMeSdkConfig config;

    @NotNull
    private final Context context;

    @NotNull
    private final PushMeSdkDatabase database;

    /* JADX INFO: renamed from: defaultApplication$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy defaultApplication;

    @Nullable
    private String defaultApplicationName;

    /* JADX INFO: renamed from: deleteTokenUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy deleteTokenUseCase;

    @NotNull
    private volatile Logger logger;

    /* JADX INFO: renamed from: newTokenUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy newTokenUseCase;

    /* JADX INFO: renamed from: pushClickedUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushClickedUseCase;

    /* JADX INFO: renamed from: pushReceivedUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushReceivedUseCase;

    @NotNull
    private volatile PushMeApi pusherApi;

    /* JADX INFO: renamed from: sendAnalyticsUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy sendAnalyticsUseCase;

    /* JADX INFO: renamed from: subscriptionUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy subscriptionUseCase;

    /* JADX INFO: renamed from: unsubscribeUseCase$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy unsubscribeUseCase;

    /* JADX INFO: renamed from: workScheduler$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy workScheduler;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\r\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0005J\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fJ+\u0010 \u001a\u0004\u0018\u00010!2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007¢\u0006\u0002\u0010$J\u0015\u0010%\u001a\u00020\u000e2\b\u0010&\u001a\u0004\u0018\u00010!¢\u0006\u0002\u0010'J\u0006\u0010(\u001a\u00020)J\u000e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006+"}, d2 = {"Lcom/vk/pushme/PushMeSdk$Companion;", "", "<init>", "()V", "DEFAULT_APPLICATION_MANIFEST_KEY", "", "SHRINK_INTERVAL_IN_HOURS", "", "_instance", "Lcom/vk/pushme/PushMeSdk;", "instance", "getInstance$push_me_sdk_release", "()Lcom/vk/pushme/PushMeSdk;", "initialize", "", "appContext", "Landroid/content/Context;", "isInitialized", "", "isInitialized$push_me_sdk_release", "setDefaultApplication", "app", "applyConfig", "config", "Lcom/vk/pushme/PushMeSdkConfig;", "setAuthProvider", "provider", "Lcom/vk/pushme/provider/AuthProvider;", "onNewToken", "token", "transport", "Lcom/vk/pushme/model/Transport;", "onMessageReceived", "", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "", "(Ljava/util/Map;Lcom/vk/pushme/model/Transport;)Ljava/lang/Long;", "onNotificationClicked", "pushId", "(Ljava/lang/Long;)V", "getApp", "Lcom/vk/pushme/model/Application;", "name", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPushMeSdk.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeSdk.kt\ncom/vk/pushme/PushMeSdk$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,346:1\n1#2:347\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void applyConfig(@NotNull PushMeSdkConfig config) {
            Intrinsics.checkNotNullParameter(config, "config");
            if (isInitialized$push_me_sdk_release()) {
                getInstance$push_me_sdk_release().setConfig(config);
            } else {
                Log.w("PushMeSDK", "SDK is not initialized, applyConfig call dropped");
            }
        }

        @NotNull
        public final Application getApp() {
            return getInstance$push_me_sdk_release().getDefaultApplication();
        }

        @NotNull
        public final PushMeSdk getInstance$push_me_sdk_release() {
            PushMeSdk pushMeSdk = PushMeSdk._instance;
            if (pushMeSdk != null) {
                return pushMeSdk;
            }
            throw new IllegalStateException("PushMe SDK is not initialized");
        }

        public final synchronized void initialize(@NotNull Context appContext) {
            Intrinsics.checkNotNullParameter(appContext, "appContext");
            if (isInitialized$push_me_sdk_release()) {
                throw new IllegalStateException("SDK has already been initialized");
            }
            PushMeSdk._instance = new PushMeSdk(appContext, null);
            getInstance$push_me_sdk_release().onInitialized();
        }

        public final boolean isInitialized$push_me_sdk_release() {
            return PushMeSdk._instance != null;
        }

        @WorkerThread
        @Nullable
        public final Long onMessageReceived(@NotNull Map<String, String> payload, @NotNull Transport transport) {
            Intrinsics.checkNotNullParameter(payload, "payload");
            Intrinsics.checkNotNullParameter(transport, "transport");
            if (isInitialized$push_me_sdk_release()) {
                return getInstance$push_me_sdk_release().onMessageReceived(payload, transport);
            }
            Log.w("PushMeSDK", "SDK is not initialized, onMessageReceived call dropped");
            return null;
        }

        public final void onNewToken(@NotNull String token, @NotNull Transport transport) {
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(transport, "transport");
            if (isInitialized$push_me_sdk_release()) {
                getInstance$push_me_sdk_release().onNewToken(token, transport);
            } else {
                Log.w("PushMeSDK", "SDK is not initialized, onNewToken call dropped");
            }
        }

        public final void onNotificationClicked(@Nullable Long pushId) {
            if (isInitialized$push_me_sdk_release()) {
                getInstance$push_me_sdk_release().onNotificationClicked(pushId);
            } else {
                Log.w("PushMeSDK", "SDK is not initialized, onNotificationClicked call dropped");
            }
        }

        public final void setAuthProvider(@NotNull AuthProvider provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            if (isInitialized$push_me_sdk_release()) {
                getInstance$push_me_sdk_release().setAuthProvider(provider);
            } else {
                Log.w("PushMeSDK", "SDK is not initialized, setAuthProvider call dropped");
            }
        }

        public final void setDefaultApplication(@NotNull String app) {
            Intrinsics.checkNotNullParameter(app, "app");
            if (isInitialized$push_me_sdk_release()) {
                getInstance$push_me_sdk_release().setDefaultApplication(app);
            } else {
                Log.w("PushMeSDK", "SDK is not initialized, setDefaultApplication call dropped");
            }
        }

        private Companion() {
        }

        @NotNull
        public final Application getApp(@NotNull String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            if (StringsKt.isBlank(name)) {
                throw new IllegalArgumentException("Application name cannot be empty");
            }
            return getInstance$push_me_sdk_release().createApplication(name);
        }
    }

    public /* synthetic */ PushMeSdk(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AnalyticsApi analyticsApi_delegate$lambda$0(PushMeSdk pushMeSdk) {
        PushMeSdkConfig pushMeSdkConfig = pushMeSdk.config;
        return NetworkModule.INSTANCE.provideAnalyticsApi(pushMeSdkConfig.getClient(), pushMeSdkConfig.getLogger(), pushMeSdkConfig.getDebugLogsEnabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Application createApplication(String name) {
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return new ApplicationImpl(lowerCase, getWorkScheduler(), getSubscriptionUseCase$push_me_sdk_release(), getUnsubscribeUseCase$push_me_sdk_release(), this.database.subscriptionDao(), this.database.pendingActionDao(), this.config.getShouldAppendSdkDeviceId(), this.logger, CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))));
    }

    private final PushMeApi createPusherApi(final PushMeSdkConfig config) {
        return NetworkModule.INSTANCE.providePushMeApi(config.getClient(), config.getLogger(), config.getDebugLogsEnabled(), new HostInfoProvider() { // from class: com.vk.pushme.PushMeSdk$createPusherApi$1$1
            private final String host;
            private final Integer port;
            private final String scheme;

            /* JADX INFO: compiled from: ProGuard */
            /* JADX INFO: loaded from: classes19.dex */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ApiVersion.values().length];
                    try {
                        iArr[ApiVersion.V1.ordinal()] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[ApiVersion.V2.ordinal()] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                this.scheme = this.$this_run.getPusherHost().getScheme();
                this.host = this.$this_run.getPusherHost().getHost();
                this.port = this.$this_run.getPusherHost().getPort();
            }

            @Override // com.vk.pushme.network.HostInfoProvider
            public String getApiPathByVersion(ApiVersion version) {
                Intrinsics.checkNotNullParameter(version, "version");
                int i10 = WhenMappings.$EnumSwitchMapping$0[version.ordinal()];
                if (i10 == 1) {
                    return this.$this_run.getPusherHost().getApiPathV1();
                }
                if (i10 == 2) {
                    return this.$this_run.getPusherHost().getApiPathV2();
                }
                throw new NoWhenBranchMatchedException();
            }

            @Override // com.vk.pushme.network.HostInfoProvider
            public String getHost() {
                return this.host;
            }

            @Override // com.vk.pushme.network.HostInfoProvider
            public Integer getPort() {
                return this.port;
            }

            @Override // com.vk.pushme.network.HostInfoProvider
            public String getScheme() {
                return this.scheme;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Application defaultApplication_delegate$lambda$0(PushMeSdk pushMeSdk) {
        String str = pushMeSdk.defaultApplicationName;
        if (str != null) {
            return pushMeSdk.createApplication(str);
        }
        throw new IllegalArgumentException("Default application is not set. Did you forget to provide default application name?");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DeleteTokenUseCase deleteTokenUseCase_delegate$lambda$0(final PushMeSdk pushMeSdk) {
        SubscriptionDao subscriptionDao = pushMeSdk.database.subscriptionDao();
        Function0 function0 = new Function0() { // from class: com.vk.pushme.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f51436a.pusherApi;
            }
        };
        Logger logger = pushMeSdk.logger;
        AnalyticsHandler analyticsHandler = pushMeSdk.config.getAnalyticsHandler();
        if (analyticsHandler == null) {
            analyticsHandler = new StubAnalyticsHandler(pushMeSdk.logger);
        }
        return new DeleteTokenUseCase(subscriptionDao, function0, logger, analyticsHandler);
    }

    private final void enqueueOneTimeSyncWorker(PushMeSdkConfig config) {
        getWorkScheduler().enqueueUniqueWork(SyncWorker.UNIQUE_WORK_ID, ExistingWorkPolicy.REPLACE, SyncWorker.INSTANCE.buildOneTimeWorkRequest(false, config.getSkipConnectionCheckByGoogle(), 60L));
    }

    private final void enqueuePeriodicSyncWorker(PushMeSdkConfig config) {
        Long sendSubscriptionsIntervalInMinutes = config.getSendSubscriptionsIntervalInMinutes();
        if (sendSubscriptionsIntervalInMinutes == null || sendSubscriptionsIntervalInMinutes.longValue() <= 0) {
            getWorkScheduler().cancelUniqueWork(SyncWorker.PERIODIC_UNIQUE_WORK_ID);
        } else {
            getWorkScheduler().enqueueUniquePeriodicWork(SyncWorker.PERIODIC_UNIQUE_WORK_ID, ExistingPeriodicWorkPolicy.UPDATE, SyncWorker.INSTANCE.buildPeriodicWorkRequest(sendSubscriptionsIntervalInMinutes.longValue(), config.getSkipConnectionCheckByGoogle()));
        }
    }

    private final void enqueueShrinkWorker() {
        getWorkScheduler().enqueueUniquePeriodicWork(ShrinkWorker.UNIQUE_WORK_ID, ExistingPeriodicWorkPolicy.UPDATE, ShrinkWorker.INSTANCE.buildWorkRequest(24));
    }

    private final void enqueueWorkers(PushMeSdkConfig config) {
        enqueueOneTimeSyncWorker(config);
        enqueuePeriodicSyncWorker(config);
        enqueueShrinkWorker();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AnalyticsApi getAnalyticsApi() {
        return (AnalyticsApi) this.analyticsApi.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Application getDefaultApplication() {
        return (Application) this.defaultApplication.getValue();
    }

    private final NewTokenUseCase getNewTokenUseCase() {
        return (NewTokenUseCase) this.newTokenUseCase.getValue();
    }

    private final PushClickedUseCase getPushClickedUseCase() {
        return (PushClickedUseCase) this.pushClickedUseCase.getValue();
    }

    private final PushReceivedUseCase getPushReceivedUseCase() {
        return (PushReceivedUseCase) this.pushReceivedUseCase.getValue();
    }

    private final WorkSchedulerImpl getWorkScheduler() {
        return (WorkSchedulerImpl) this.workScheduler.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NewTokenUseCase newTokenUseCase_delegate$lambda$0(PushMeSdk pushMeSdk) {
        return new NewTokenUseCase(pushMeSdk.database.pushTokenDao(), pushMeSdk.getWorkScheduler(), CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))), pushMeSdk.logger);
    }

    private final void onConfigChanged() {
        this.logger = this.config.getLogger();
        this.pusherApi = createPusherApi(this.config);
        String defaultApplication = this.config.getDefaultApplication();
        if (defaultApplication != null) {
            this.defaultApplicationName = defaultApplication;
        }
        enqueueWorkers(this.config);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onInitialized() throws PackageManager.NameNotFoundException {
        readManifestProperties();
        enqueueWorkers(this.config);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public final Long onMessageReceived(Map<String, String> payload, Transport transport) {
        return getPushReceivedUseCase().invoke(payload, transport);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNewToken(String token, Transport transport) {
        getNewTokenUseCase().invoke(token, transport);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onNotificationClicked(Long pushId) {
        if (pushId != null) {
            getPushClickedUseCase().invoke(pushId.longValue());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushClickedUseCase pushClickedUseCase_delegate$lambda$0(PushMeSdk pushMeSdk) {
        return new PushClickedUseCase(pushMeSdk.database.pushDao(), pushMeSdk.getWorkScheduler(), CoroutineScopeKt.CoroutineScope(Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null))), pushMeSdk.logger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PushReceivedUseCase pushReceivedUseCase_delegate$lambda$0(PushMeSdk pushMeSdk) {
        PushDao pushDao = pushMeSdk.database.pushDao();
        WorkSchedulerImpl workScheduler = pushMeSdk.getWorkScheduler();
        Logger logger = pushMeSdk.logger;
        AnalyticsHandler analyticsHandler = pushMeSdk.config.getAnalyticsHandler();
        if (analyticsHandler == null) {
            analyticsHandler = new StubAnalyticsHandler(pushMeSdk.logger);
        }
        return new PushReceivedUseCase(pushDao, workScheduler, logger, analyticsHandler);
    }

    private final void readManifestProperties() throws PackageManager.NameNotFoundException {
        String string = new ManifestReader(this.context).readString(DEFAULT_APPLICATION_MANIFEST_KEY);
        if (string != null) {
            this.defaultApplicationName = string;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SendAnalyticsUseCase sendAnalyticsUseCase_delegate$lambda$0(final PushMeSdk pushMeSdk) {
        Function0 function0 = new Function0() { // from class: com.vk.pushme.f
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f51497a.getAnalyticsApi();
            }
        };
        Logger logger = pushMeSdk.logger;
        AnalyticsHandler analyticsHandler = pushMeSdk.config.getAnalyticsHandler();
        if (analyticsHandler == null) {
            analyticsHandler = new StubAnalyticsHandler(pushMeSdk.logger);
        }
        return new SendAnalyticsUseCase(function0, logger, analyticsHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setAuthProvider(AuthProvider provider) {
        this.authProvider = provider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setConfig(PushMeSdkConfig pushMeSdkConfig) {
        this.config = pushMeSdkConfig;
        onConfigChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDefaultApplication(String name) {
        this.defaultApplicationName = name;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SubscriptionUseCase subscriptionUseCase_delegate$lambda$0(final PushMeSdk pushMeSdk) {
        AuthProvider authProvider = pushMeSdk.authProvider;
        if (authProvider == null) {
            throw new IllegalArgumentException("You must call setAuthProvider on SDK");
        }
        PushTokenDao pushTokenDao = pushMeSdk.database.pushTokenDao();
        ClientInfoProviderImpl clientInfoProviderImpl = new ClientInfoProviderImpl(pushMeSdk.context, pushMeSdk.config.getAppVersion());
        DeviceIdProviderImpl deviceIdProviderImpl = new DeviceIdProviderImpl(pushMeSdk.context);
        Function0 function0 = new Function0() { // from class: com.vk.pushme.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f51496a.pusherApi;
            }
        };
        Logger logger = pushMeSdk.logger;
        AnalyticsHandler analyticsHandler = pushMeSdk.config.getAnalyticsHandler();
        if (analyticsHandler == null) {
            analyticsHandler = new StubAnalyticsHandler(pushMeSdk.logger);
        }
        return new SubscriptionUseCase(authProvider, pushTokenDao, clientInfoProviderImpl, deviceIdProviderImpl, function0, logger, analyticsHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UnsubscribeUseCase unsubscribeUseCase_delegate$lambda$0(final PushMeSdk pushMeSdk) {
        DeviceIdProviderImpl deviceIdProviderImpl = new DeviceIdProviderImpl(pushMeSdk.context);
        Function0 function0 = new Function0() { // from class: com.vk.pushme.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f51433a.pusherApi;
            }
        };
        Logger logger = pushMeSdk.logger;
        AnalyticsHandler analyticsHandler = pushMeSdk.config.getAnalyticsHandler();
        if (analyticsHandler == null) {
            analyticsHandler = new StubAnalyticsHandler(pushMeSdk.logger);
        }
        return new UnsubscribeUseCase(deviceIdProviderImpl, function0, logger, analyticsHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WorkSchedulerImpl workScheduler_delegate$lambda$0(PushMeSdk pushMeSdk) {
        return new WorkSchedulerImpl(pushMeSdk.context);
    }

    @NotNull
    /* JADX INFO: renamed from: getConfig$push_me_sdk_release, reason: from getter */
    public final PushMeSdkConfig getConfig() {
        return this.config;
    }

    @NotNull
    /* JADX INFO: renamed from: getDatabase$push_me_sdk_release, reason: from getter */
    public final PushMeSdkDatabase getDatabase() {
        return this.database;
    }

    @NotNull
    public final DeleteTokenUseCase getDeleteTokenUseCase$push_me_sdk_release() {
        return (DeleteTokenUseCase) this.deleteTokenUseCase.getValue();
    }

    @NotNull
    /* JADX INFO: renamed from: getLogger$push_me_sdk_release, reason: from getter */
    public final Logger getLogger() {
        return this.logger;
    }

    @NotNull
    public final SendAnalyticsUseCase getSendAnalyticsUseCase$push_me_sdk_release() {
        return (SendAnalyticsUseCase) this.sendAnalyticsUseCase.getValue();
    }

    @NotNull
    public final SubscriptionUseCase getSubscriptionUseCase$push_me_sdk_release() {
        return (SubscriptionUseCase) this.subscriptionUseCase.getValue();
    }

    @NotNull
    public final UnsubscribeUseCase getUnsubscribeUseCase$push_me_sdk_release() {
        return (UnsubscribeUseCase) this.unsubscribeUseCase.getValue();
    }

    public final void setLogger$push_me_sdk_release(@NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "<set-?>");
        this.logger = logger;
    }

    private PushMeSdk(Context context) {
        this.context = context;
        this.config = new PushMeSdkConfig(null, null, null, null, false, false, null, false, null, null, FastDoubleMath.DOUBLE_EXPONENT_BIAS, null);
        this.database = PushMeSdkDatabase.INSTANCE.build(context);
        this.subscriptionUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.g
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.subscriptionUseCase_delegate$lambda$0(this.f51498a);
            }
        });
        this.unsubscribeUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.h
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.unsubscribeUseCase_delegate$lambda$0(this.f51499a);
            }
        });
        this.deleteTokenUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.i
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.deleteTokenUseCase_delegate$lambda$0(this.f51500a);
            }
        });
        this.sendAnalyticsUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.j
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.sendAnalyticsUseCase_delegate$lambda$0(this.f51501a);
            }
        });
        this.workScheduler = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.k
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.workScheduler_delegate$lambda$0(this.f51502a);
            }
        });
        this.logger = this.config.getLogger();
        this.pusherApi = createPusherApi(this.config);
        this.analyticsApi = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.l
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.analyticsApi_delegate$lambda$0(this.f51503a);
            }
        });
        this.newTokenUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.m
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.newTokenUseCase_delegate$lambda$0(this.f51513a);
            }
        });
        this.pushReceivedUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.n
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.pushReceivedUseCase_delegate$lambda$0(this.f51517a);
            }
        });
        this.pushClickedUseCase = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.pushClickedUseCase_delegate$lambda$0(this.f51434a);
            }
        });
        this.defaultApplication = LazyKt.lazy(new Function0() { // from class: com.vk.pushme.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PushMeSdk.defaultApplication_delegate$lambda$0(this.f51435a);
            }
        });
    }
}
