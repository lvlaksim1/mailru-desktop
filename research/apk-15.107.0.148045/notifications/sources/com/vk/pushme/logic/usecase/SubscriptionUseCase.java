package com.vk.pushme.logic.usecase;

import com.huawei.hms.framework.common.BundleUtil;
import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.PushTokenDao;
import com.vk.pushme.database.entity.PushToken;
import com.vk.pushme.logic.Subscription;
import com.vk.pushme.model.DeliveryTime;
import com.vk.pushme.model.SpecifyTransportOption;
import com.vk.pushme.model.Transport;
import com.vk.pushme.network.PushMeApi;
import com.vk.pushme.network.model.request.SubscriptionRequest;
import com.vk.pushme.network.model.result.SubscriptionResult;
import com.vk.pushme.provider.AuthProvider;
import com.vk.pushme.util.provider.ClientInfoProvider;
import com.vk.pushme.util.provider.DeviceIdProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 P2\u00020\u0001:\u0002OPBE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012J$\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0086B¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u001c\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u00060\"j\u0002`#H\u0002J\u0010\u0010$\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020 H\u0002J\u0010\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020 H\u0002J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010(\u001a\u00020 H\u0002J\u0010\u0010*\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020/H\u0002J\u0018\u00100\u001a\u00020,2\u0006\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u000201H\u0002J\u0018\u00102\u001a\u00020,2\u0006\u00103\u001a\u00020 2\u0006\u0010.\u001a\u00020/H\u0002J\u0018\u00104\u001a\u00020,2\u0006\u00103\u001a\u00020 2\u0006\u0010.\u001a\u000201H\u0002J`\u00105\u001a\b\u0012\u0004\u0012\u0002060\u00182\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00182\u0006\u00109\u001a\u00020 2\u0006\u0010:\u001a\u00020 2\b\u0010;\u001a\u0004\u0018\u00010 2\u0012\u0010<\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0=2\u0006\u0010>\u001a\u00020 H\u0002J.\u0010?\u001a\u0004\u0018\u00010 2\u0006\u0010@\u001a\u00020 2\u0012\u0010A\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0B2\u0006\u0010(\u001a\u00020 H\u0002J\u0014\u0010C\u001a\u0004\u0018\u00010D2\b\u0010E\u001a\u0004\u0018\u00010FH\u0002J\u0010\u0010G\u001a\u00020\u001b2\u0006\u0010(\u001a\u00020 H\u0002J\u0010\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020KH\u0002J\u0018\u0010L\u001a\u00020\u001b2\u0006\u0010J\u001a\u00020K2\u0006\u0010M\u001a\u00020\u0019H\u0002J\u0010\u0010N\u001a\u00020,2\u0006\u0010(\u001a\u00020 H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;", "", "authProvider", "Lcom/vk/pushme/provider/AuthProvider;", "pushTokenDao", "Lcom/vk/pushme/database/dao/PushTokenDao;", "clientInfoProvider", "Lcom/vk/pushme/util/provider/ClientInfoProvider;", "deviceIdProvider", "Lcom/vk/pushme/util/provider/DeviceIdProvider;", "apiCreator", "Lkotlin/Function0;", "Lcom/vk/pushme/network/PushMeApi;", "logger", "Lcom/vk/pushme/common/Logger;", "analyticsHandler", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "<init>", "(Lcom/vk/pushme/provider/AuthProvider;Lcom/vk/pushme/database/dao/PushTokenDao;Lcom/vk/pushme/util/provider/ClientInfoProvider;Lcom/vk/pushme/util/provider/DeviceIdProvider;Lkotlin/jvm/functions/Function0;Lcom/vk/pushme/common/Logger;Lcom/vk/pushme/analytcis/AnalyticsHandler;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "invoke", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "subscriptions", "", "Lcom/vk/pushme/logic/Subscription;", "appendSdkDeviceId", "", "(Ljava/util/Collection;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "handleNoSubscriptionsFound", "handleCouldNotCreateSubscription", "allApps", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleNoAccountsSubscribed", "handleAllAccountsSubscribed", "applications", "handlePushTokensEmpty", "application", "handleNoDeviceId", "handleNoAndroidId", "handleV1ServerError", "", "v1Apps", "result", "Lcom/vk/pushme/network/model/result/SubscriptionResult$ServerError;", "handleV1UnknownError", "Lcom/vk/pushme/network/model/result/SubscriptionResult$UnknownError;", "handleV2ServerError", "v2Apps", "handleV2UnknownError", "mapToNetworkModels", "Lcom/vk/pushme/network/model/request/SubscriptionRequest;", "pushTokens", "Lcom/vk/pushme/database/entity/PushToken;", "androidId", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "sdkDeviceId", "clientInfo", "", "timeZone", "tryToPeekAuthToken", "account", "cache", "", "mapDeliveryTime", "Lcom/vk/pushme/network/model/request/SubscriptionRequest$Settings$DeliveryTime;", "time", "Lcom/vk/pushme/model/DeliveryTime;", "isV1", "mapTransport", "Lcom/vk/pushme/network/model/request/SubscriptionRequest$Transport;", "transport", "Lcom/vk/pushme/model/Transport;", "isSuitableForTransport", "subscription", "logBlancAccount", "Result", "Companion", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSubscriptionUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SubscriptionUseCase.kt\ncom/vk/pushme/logic/usecase/SubscriptionUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,443:1\n774#2:444\n865#2,2:445\n774#2:447\n865#2,2:448\n1563#2:451\n1634#2,3:452\n1869#2:455\n1563#2:456\n1634#2,3:457\n1870#2:460\n1869#2:461\n1563#2:462\n1634#2,3:463\n1870#2:466\n1#3:450\n*S KotlinDebug\n*F\n+ 1 SubscriptionUseCase.kt\ncom/vk/pushme/logic/usecase/SubscriptionUseCase\n*L\n64#1:444\n64#1:445,2\n65#1:447\n65#1:448,2\n81#1:451\n81#1:452,3\n86#1:455\n87#1:456\n87#1:457,3\n86#1:460\n111#1:461\n112#1:462\n112#1:463,3\n111#1:466\n*E\n"})
public final class SubscriptionUseCase {
    private static final int PUSH_ME_BATCH_LIMIT = 20;
    private static final int STATUS_ON = 0;

    @NotNull
    private final AnalyticsHandler analyticsHandler;

    @NotNull
    private final Function0<PushMeApi> apiCreator;

    @NotNull
    private final AuthProvider authProvider;

    @NotNull
    private final ClientInfoProvider clientInfoProvider;

    @NotNull
    private final DeviceIdProvider deviceIdProvider;

    @NotNull
    private final Logger logger;

    @NotNull
    private final Mutex mutex;

    @NotNull
    private final PushTokenDao pushTokenDao;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "", "<init>", "()V", "OK", "NoAuthError", "MissingPushTokenError", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$MissingPushTokenError;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$NoAuthError;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$MissingPushTokenError;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class MissingPushTokenError extends Result {

            @NotNull
            public static final MissingPushTokenError INSTANCE = new MissingPushTokenError();

            private MissingPushTokenError() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "MissingPushTokenError";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000b\u001a\u00020\u0004H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\t¨\u0006\f"}, d2 = {"Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$NoAuthError;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "failedAccounts", "", "", "successAccounts", "<init>", "(Ljava/util/Set;Ljava/util/Set;)V", "getFailedAccounts", "()Ljava/util/Set;", "getSuccessAccounts", "toString", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NoAuthError extends Result {

            @NotNull
            private final Set<String> failedAccounts;

            @NotNull
            private final Set<String> successAccounts;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NoAuthError(@NotNull Set<String> failedAccounts, @NotNull Set<String> successAccounts) {
                super(null);
                Intrinsics.checkNotNullParameter(failedAccounts, "failedAccounts");
                Intrinsics.checkNotNullParameter(successAccounts, "successAccounts");
                this.failedAccounts = failedAccounts;
                this.successAccounts = successAccounts;
            }

            @NotNull
            public final Set<String> getFailedAccounts() {
                return this.failedAccounts;
            }

            @NotNull
            public final Set<String> getSuccessAccounts() {
                return this.successAccounts;
            }

            @NotNull
            public String toString() {
                return "NoAuthError";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OK extends Result {

            @NotNull
            public static final OK INSTANCE = new OK();

            private OK() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "OK";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result$UnknownError;", "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase$Result;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnknownError extends Result {

            @NotNull
            private final Throwable t;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public UnknownError(@NotNull Throwable t10) {
                super(null);
                Intrinsics.checkNotNullParameter(t10, "t");
                this.t = t10;
            }

            @NotNull
            public final Throwable getT() {
                return this.t;
            }

            @NotNull
            public String toString() {
                return "UnknownError(" + this.t + ")";
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Transport.values().length];
            try {
                iArr[Transport.FIREBASE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Transport.HUAWEI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Transport.VKPNS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.SubscriptionUseCase$invoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.SubscriptionUseCase", f = "SubscriptionUseCase.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, l = {40, 43, 88, 113}, m = "invoke", n = {"subscriptions", "allApps", "appendSdkDeviceId", "subscriptions", "allApps", "appendSdkDeviceId", "subscriptions", "allApps", "pushTokens", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "androidId", "requests", "v1Requests", "v2Requests", "v1Apps", "v2Apps", "batcher", "allAccounts", "successAccounts", ApiUris.AUTHORITY_API, "$this$forEach$iv", "element$iv", "batch", "accountsInRequest", "appendSdkDeviceId", "$i$f$forEach", "$i$a$-forEach-SubscriptionUseCase$invoke$6", "subscriptions", "allApps", "pushTokens", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "androidId", "requests", "v1Requests", "v2Requests", "v1Apps", "v2Apps", "batcher", "allAccounts", "successAccounts", ApiUris.AUTHORITY_API, "$this$forEach$iv", "element$iv", "batch", "accountsInRequest", "appendSdkDeviceId", "$i$f$forEach", "$i$a$-forEach-SubscriptionUseCase$invoke$7"}, s = {"L$0", "L$1", "Z$0", "L$0", "L$1", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$16", "L$17", "L$18", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$11", "L$12", "L$13", "L$14", "L$16", "L$17", "L$18", "Z$0", "I$0", "I$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubscriptionUseCase.this.invoke(null, false, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SubscriptionUseCase(@NotNull AuthProvider authProvider, @NotNull PushTokenDao pushTokenDao, @NotNull ClientInfoProvider clientInfoProvider, @NotNull DeviceIdProvider deviceIdProvider, @NotNull Function0<? extends PushMeApi> apiCreator, @NotNull Logger logger, @NotNull AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
        Intrinsics.checkNotNullParameter(pushTokenDao, "pushTokenDao");
        Intrinsics.checkNotNullParameter(clientInfoProvider, "clientInfoProvider");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        Intrinsics.checkNotNullParameter(apiCreator, "apiCreator");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(analyticsHandler, "analyticsHandler");
        this.authProvider = authProvider;
        this.pushTokenDao = pushTokenDao;
        this.clientInfoProvider = clientInfoProvider;
        this.deviceIdProvider = deviceIdProvider;
        this.apiCreator = apiCreator;
        this.analyticsHandler = analyticsHandler;
        this.logger = logger.createLogger("SubscriptionUseCase");
        this.mutex = MutexKt.Mutex$default(false, 1, null);
    }

    private final Result handleAllAccountsSubscribed(String applications) {
        Logger.info$default(this.logger, "All accounts have been subscribed", null, 2, null);
        this.analyticsHandler.successSubscription(applications);
        return Result.OK.INSTANCE;
    }

    private final Result handleCouldNotCreateSubscription(String allApps, Exception e10) {
        this.logger.error("Failed to perform subscription", e10);
        this.analyticsHandler.subscriptionError(allApps, AnalyticsErrorType.UNKNOWN_ERROR, "Failed to perform subscription " + e10.getMessage());
        return new Result.UnknownError(e10);
    }

    private final Result handleNoAccountsSubscribed(String allApps) {
        Logger.error$default(this.logger, "No accounts have been subscribed", null, 2, null);
        this.analyticsHandler.subscriptionError(allApps, AnalyticsErrorType.INVALID_ACCOUNT_ERROR, "No accounts have been subscribed");
        return new Result.UnknownError(new IllegalStateException("No accounts have been subscribed"));
    }

    private final Result handleNoAndroidId(String allApps) {
        Logger.error$default(this.logger, "Unable to get android ID, subscription failed", null, 2, null);
        this.analyticsHandler.subscriptionError(allApps, AnalyticsErrorType.MISSING_ANDROID_ID_ERROR, "Unable to get android ID");
        return new Result.UnknownError(new IllegalStateException("Unable to get android ID, subscription failed"));
    }

    private final Result handleNoDeviceId(String application) {
        Logger.error$default(this.logger, "Unable to get device ID, subscription failed", null, 2, null);
        this.analyticsHandler.subscriptionError(application, AnalyticsErrorType.MISSING_DEVICE_ID_ERROR, "Unable to get device ID");
        return new Result.UnknownError(new IllegalStateException("Unable to get device ID, subscription failed"));
    }

    private final Result handleNoSubscriptionsFound() {
        Logger.info$default(this.logger, "No subscriptions found available for sending", null, 2, null);
        return Result.OK.INSTANCE;
    }

    private final Result handlePushTokensEmpty(String application) {
        Logger.warn$default(this.logger, "No push tokens found, abort", null, 2, null);
        this.analyticsHandler.subscriptionError(application, AnalyticsErrorType.NO_PUSH_TOKEN_FOUND_ERROR, "Push token is empty");
        return Result.MissingPushTokenError.INSTANCE;
    }

    private final void handleV1ServerError(String v1Apps, SubscriptionResult.ServerError result) {
        Logger.error$default(this.logger, "Server error for V1 request: " + result.getCode() + StringUtils.SPACE + result.getMessage(), null, 2, null);
        this.analyticsHandler.subscriptionError(v1Apps, AnalyticsErrorType.SERVER_ERROR, "Server error for V1 request: " + result.getCode() + StringUtils.SPACE + result.getMessage());
    }

    private final void handleV1UnknownError(String v1Apps, SubscriptionResult.UnknownError result) {
        this.logger.error("Unknown error for V1 request", result.getT());
        this.analyticsHandler.subscriptionError(v1Apps, AnalyticsErrorType.UNKNOWN_ERROR, "Unknown error for V1 request " + result.getT());
    }

    private final void handleV2ServerError(String v2Apps, SubscriptionResult.ServerError result) {
        Logger.error$default(this.logger, "Server error for V2 request: " + result.getCode() + StringUtils.SPACE + result.getMessage(), null, 2, null);
        this.analyticsHandler.subscriptionError(v2Apps, AnalyticsErrorType.SERVER_ERROR, "Server error for V2 request: " + result.getCode() + StringUtils.SPACE + result.getMessage());
    }

    private final void handleV2UnknownError(String v2Apps, SubscriptionResult.UnknownError result) {
        this.logger.error("Unknown error for V2 request", result.getT());
        this.analyticsHandler.subscriptionError(v2Apps, AnalyticsErrorType.UNKNOWN_ERROR, "Unknown error for V2 request " + result.getT());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invoke$lambda$0(Subscription it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getApplication();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invoke$lambda$3(SubscriptionRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getApplication();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invoke$lambda$4(SubscriptionRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getApplication() + BundleUtil.UNDERLINE_TAG + it.getAccount();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invoke$lambda$6(SubscriptionRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getApplication();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence invoke$lambda$7(SubscriptionRequest it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getApplication() + BundleUtil.UNDERLINE_TAG + it.getAccount();
    }

    private final boolean isSuitableForTransport(Transport transport, Subscription subscription) {
        SpecifyTransportOption specifyTransportOption = subscription.getSpecifyTransportOption();
        if (Intrinsics.areEqual(specifyTransportOption, SpecifyTransportOption.AllTransports.INSTANCE)) {
            return true;
        }
        if (specifyTransportOption instanceof SpecifyTransportOption.Include) {
            return ArraysKt.contains(((SpecifyTransportOption.Include) specifyTransportOption).getTransports(), transport);
        }
        if (specifyTransportOption instanceof SpecifyTransportOption.Exclude) {
            return !ArraysKt.contains(((SpecifyTransportOption.Exclude) specifyTransportOption).getTransports(), transport);
        }
        throw new NoWhenBranchMatchedException();
    }

    private final boolean isV1(String application) {
        return false;
    }

    private final void logBlancAccount(String application) {
        Logger.error$default(this.logger, "Account is empty for app " + application, null, 2, null);
        this.analyticsHandler.subscriptionError(application, AnalyticsErrorType.INVALID_ACCOUNT_ERROR, "Account is empty for app " + application);
    }

    private final SubscriptionRequest.Settings.DeliveryTime mapDeliveryTime(DeliveryTime time) {
        if (time == null) {
            return null;
        }
        return new SubscriptionRequest.Settings.DeliveryTime(new SubscriptionRequest.Settings.TimePoint(time.getFrom().getHour(), time.getFrom().getMinute()), new SubscriptionRequest.Settings.TimePoint(time.getTo().getHour(), time.getTo().getMinute()));
    }

    private final Collection<SubscriptionRequest> mapToNetworkModels(Collection<Subscription> subscriptions, Collection<PushToken> pushTokens, String androidId, String deviceId, String sdkDeviceId, Map<String, String> clientInfo, String timeZone) {
        String str;
        if (pushTokens.isEmpty()) {
            throw new IllegalStateException("Check failed.");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList = new ArrayList();
        for (Subscription subscription : subscriptions) {
            String lowerCase = subscription.getAccount().toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (StringsKt.isBlank(lowerCase)) {
                logBlancAccount(subscription.getApplication());
            } else {
                Throwable th2 = null;
                if (isV1(subscription.getApplication())) {
                    str = null;
                } else {
                    String strTryToPeekAuthToken = tryToPeekAuthToken(lowerCase, linkedHashMap, subscription.getApplication());
                    if (strTryToPeekAuthToken == null || StringsKt.isBlank(strTryToPeekAuthToken)) {
                        Logger.error$default(this.logger, "Unable to obtain auth token for account " + lowerCase, null, 2, null);
                        this.analyticsHandler.subscriptionError(subscription.getApplication(), AnalyticsErrorType.NO_PUSH_TOKEN_FOUND_ERROR, "Unable to obtain auth token for account");
                    } else {
                        str = strTryToPeekAuthToken;
                    }
                }
                SubscriptionRequest.Settings settings = new SubscriptionRequest.Settings(subscription.getTags(), mapDeliveryTime(subscription.getDeliveryTime()), subscription.getExtras());
                for (PushToken pushToken : pushTokens) {
                    Transport transportFromString = Transport.INSTANCE.fromString(pushToken.getTransport());
                    if (transportFromString == null) {
                        Logger.error$default(this.logger, "Unable to parse transport: " + pushToken.getTransport(), th2, 2, th2);
                        this.analyticsHandler.subscriptionError(subscription.getApplication(), AnalyticsErrorType.TRANSPORT_PARSE_ERROR, "Unable to parse transport: " + pushToken.getTransport());
                    } else if (isSuitableForTransport(transportFromString, subscription)) {
                        SubscriptionRequest.Settings settings2 = settings;
                        arrayList.add(new SubscriptionRequest(lowerCase, subscription.getApplication(), mapTransport(transportFromString), pushToken.getToken(), str, androidId, deviceId, sdkDeviceId, clientInfo, settings2, 0, timeZone));
                        th2 = th2;
                        settings = settings2;
                    }
                }
            }
        }
        return arrayList;
    }

    private final SubscriptionRequest.Transport mapTransport(Transport transport) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[transport.ordinal()];
        if (i10 == 1) {
            return SubscriptionRequest.Transport.FIREBASE;
        }
        if (i10 == 2) {
            return SubscriptionRequest.Transport.HUAWEI;
        }
        if (i10 == 3) {
            return SubscriptionRequest.Transport.VKPNS;
        }
        throw new NoWhenBranchMatchedException();
    }

    private final String tryToPeekAuthToken(String account, Map<String, String> cache, String application) {
        String authToken;
        String str = cache.get(account);
        if (str != null && !StringsKt.isBlank(str)) {
            return str;
        }
        try {
            authToken = this.authProvider.getAuthToken(account);
        } catch (Exception e10) {
            this.logger.error("Exception from getAuthToken method: ", e10);
            this.analyticsHandler.subscriptionError(application, AnalyticsErrorType.NO_PUSH_TOKEN_FOUND_ERROR, "Exception from getAuthToken method " + e10);
            authToken = null;
        }
        if (authToken != null) {
            cache.put(account, authToken);
        }
        return authToken;
    }

    /* JADX WARN: Code duplicated, block: B:135:0x05a0 A[Catch: all -> 0x00c8, Exception -> 0x017f, TRY_LEAVE, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:140:0x05c8 A[Catch: all -> 0x0420, Exception -> 0x05d6, LOOP:0: B:138:0x05c2->B:140:0x05c8, LOOP_END, TryCatch #0 {all -> 0x0420, blocks: (B:147:0x0653, B:137:0x05b7, B:138:0x05c2, B:140:0x05c8, B:143:0x05db, B:115:0x04a4, B:103:0x03f9, B:104:0x040c, B:106:0x0412, B:111:0x042a), top: B:197:0x04a4 }] */
    /* JADX WARN: Code duplicated, block: B:146:0x0646  */
    /* JADX WARN: Code duplicated, block: B:149:0x065d  */
    /* JADX WARN: Code duplicated, block: B:157:0x0689 A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:159:0x0691 A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:160:0x06f5 A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:162:0x0705 A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:163:0x070b A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:165:0x070f A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:167:0x072c A[Catch: all -> 0x00c8, Exception -> 0x0680, TryCatch #3 {all -> 0x00c8, blocks: (B:17:0x009e, B:150:0x065f, B:152:0x0667, B:133:0x059a, B:135:0x05a0, B:190:0x07b2, B:171:0x0737, B:173:0x0741, B:176:0x074d, B:178:0x0753, B:181:0x075f, B:157:0x0689, B:159:0x0691, B:160:0x06f5, B:162:0x0705, B:163:0x070b, B:165:0x070f, B:167:0x072c, B:168:0x0731, B:27:0x013c, B:118:0x04b2, B:100:0x03e4, B:102:0x03ea, B:131:0x057e, B:119:0x04d3, B:121:0x04df, B:122:0x0544, B:124:0x0550, B:125:0x0556, B:127:0x055a, B:129:0x0578, B:130:0x057d, B:32:0x0176, B:46:0x0203, B:48:0x020d, B:55:0x021f, B:57:0x0227, B:60:0x022f, B:62:0x0237, B:65:0x023f, B:74:0x025c, B:81:0x028a, B:82:0x0296, B:84:0x029c, B:86:0x02ae, B:88:0x02b3, B:89:0x02bf, B:91:0x02c5, B:93:0x02d9, B:95:0x02df, B:96:0x0393, B:98:0x0399, B:99:0x03a7, B:184:0x079a, B:187:0x07a6, B:42:0x01ec), top: B:203:0x0033 }] */
    /* JADX WARN: Code duplicated, block: B:8:0x001e  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v0 */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.vk.pushme.logic.usecase.SubscriptionUseCase] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.vk.pushme.logic.usecase.SubscriptionUseCase] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.vk.pushme.logic.usecase.SubscriptionUseCase] */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.vk.pushme.logic.usecase.SubscriptionUseCase] */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v39, types: [com.vk.pushme.logic.usecase.SubscriptionUseCase] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v41 */
    /* JADX WARN: Type inference failed for: r1v42, types: [com.vk.pushme.logic.usecase.SubscriptionUseCase] */
    /* JADX WARN: Type inference failed for: r1v44 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r1v46 */
    /* JADX WARN: Type inference failed for: r1v47 */
    /* JADX WARN: Type inference failed for: r1v48 */
    /* JADX WARN: Type inference failed for: r1v49 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v54 */
    /* JADX WARN: Type inference failed for: r1v55 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v117 */
    /* JADX WARN: Type inference failed for: r2v118 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v36 */
    /* JADX WARN: Type inference failed for: r2v49, types: [java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v53 */
    /* JADX WARN: Type inference failed for: r2v54 */
    /* JADX WARN: Type inference failed for: r2v55 */
    /* JADX WARN: Type inference failed for: r2v64 */
    /* JADX WARN: Type inference failed for: r2v72 */
    /* JADX WARN: Type inference failed for: r2v94 */
    /* JADX WARN: Type inference failed for: r2v95 */
    /* JADX WARN: Type inference failed for: r35v10 */
    /* JADX WARN: Type inference failed for: r35v11 */
    /* JADX WARN: Type inference failed for: r35v12 */
    /* JADX WARN: Type inference failed for: r35v13 */
    /* JADX WARN: Type inference failed for: r35v15 */
    /* JADX WARN: Type inference failed for: r35v16 */
    /* JADX WARN: Type inference failed for: r35v17 */
    /* JADX WARN: Type inference failed for: r35v18 */
    /* JADX WARN: Type inference failed for: r35v19 */
    /* JADX WARN: Type inference failed for: r35v9 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v34 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:114:0x0490 -> B:197:0x04a4). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:146:0x0646 -> B:207:0x0653). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull java.util.Collection<com.vk.pushme.logic.Subscription> r34, boolean r35, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.vk.pushme.logic.usecase.SubscriptionUseCase.Result> r36) {
        /*
            Method dump skipped, instruction units count: 1996
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.usecase.SubscriptionUseCase.invoke(java.util.Collection, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
