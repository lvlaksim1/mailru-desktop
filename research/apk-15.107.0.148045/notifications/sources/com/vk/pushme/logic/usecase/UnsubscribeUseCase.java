package com.vk.pushme.logic.usecase;

import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.common.Logger;
import com.vk.pushme.network.PushMeApi;
import com.vk.pushme.util.provider.DeviceIdProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ$\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0014H\u0086B¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;", "", "deviceIdProvider", "Lcom/vk/pushme/util/provider/DeviceIdProvider;", "apiCreator", "Lkotlin/Function0;", "Lcom/vk/pushme/network/PushMeApi;", "logger", "Lcom/vk/pushme/common/Logger;", "analyticsHandler", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "<init>", "(Lcom/vk/pushme/util/provider/DeviceIdProvider;Lkotlin/jvm/functions/Function0;Lcom/vk/pushme/common/Logger;Lcom/vk/pushme/analytcis/AnalyticsHandler;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "invoke", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;", "application", "", "accounts", "", "(Ljava/lang/String;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logEmptyDeviceId", "", "Result", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UnsubscribeUseCase {

    @NotNull
    private final AnalyticsHandler analyticsHandler;

    @NotNull
    private final Function0<PushMeApi> apiCreator;

    @NotNull
    private final DeviceIdProvider deviceIdProvider;

    @NotNull
    private final Logger logger;

    @NotNull
    private final Mutex mutex;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;", "", "<init>", "()V", "OK", "MissingDeviceIdError", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$MissingDeviceIdError;", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$MissingDeviceIdError;", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class MissingDeviceIdError extends Result {

            @NotNull
            public static final MissingDeviceIdError INSTANCE = new MissingDeviceIdError();

            private MissingDeviceIdError() {
                super(null);
            }

            @NotNull
            public String toString() {
                return "MissingDeviceIdError";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result$UnknownError;", "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase$Result;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.UnsubscribeUseCase$invoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.UnsubscribeUseCase", f = "UnsubscribeUseCase.kt", i = {0, 0, 1, 1, 1, 1, 1, 1}, l = {23, 34}, m = "invoke", n = {"application", "accounts", "application", "accounts", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, ApiUris.AUTHORITY_API, "result", "account"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$6"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
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
            return UnsubscribeUseCase.this.invoke(null, null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public UnsubscribeUseCase(@NotNull DeviceIdProvider deviceIdProvider, @NotNull Function0<? extends PushMeApi> apiCreator, @NotNull Logger logger, @NotNull AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        Intrinsics.checkNotNullParameter(apiCreator, "apiCreator");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(analyticsHandler, "analyticsHandler");
        this.deviceIdProvider = deviceIdProvider;
        this.apiCreator = apiCreator;
        this.analyticsHandler = analyticsHandler;
        this.logger = logger.createLogger("UnsubscribeUseCase");
        this.mutex = MutexKt.Mutex$default(false, 1, null);
    }

    private final void logEmptyDeviceId(String application) {
        Logger.error$default(this.logger, "Unable to get device ID", null, 2, null);
        this.analyticsHandler.unsubscribeError(application, AnalyticsErrorType.MISSING_DEVICE_ID_ERROR, "Unable to get device ID");
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00d8 A[Catch: all -> 0x005a, Exception -> 0x005d, TRY_LEAVE, TryCatch #1 {Exception -> 0x005d, blocks: (B:13:0x0053, B:35:0x00d2, B:37:0x00d8), top: B:81:0x0053 }] */
    /* JADX WARN: Code duplicated, block: B:42:0x0126  */
    /* JADX WARN: Code duplicated, block: B:45:0x0132 A[Catch: all -> 0x005a, Exception -> 0x0144, TryCatch #4 {all -> 0x005a, blocks: (B:13:0x0053, B:43:0x0128, B:45:0x0132, B:35:0x00d2, B:37:0x00d8, B:39:0x00f7, B:73:0x020b, B:62:0x01d8, B:49:0x0149, B:51:0x014d, B:53:0x01a8, B:55:0x01ae, B:57:0x01be, B:60:0x01d0, B:61:0x01d7, B:28:0x00ad, B:30:0x00b5, B:33:0x00be, B:68:0x01fc), top: B:87:0x0031 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x0149 A[Catch: all -> 0x005a, Exception -> 0x0144, TryCatch #4 {all -> 0x005a, blocks: (B:13:0x0053, B:43:0x0128, B:45:0x0132, B:35:0x00d2, B:37:0x00d8, B:39:0x00f7, B:73:0x020b, B:62:0x01d8, B:49:0x0149, B:51:0x014d, B:53:0x01a8, B:55:0x01ae, B:57:0x01be, B:60:0x01d0, B:61:0x01d7, B:28:0x00ad, B:30:0x00b5, B:33:0x00be, B:68:0x01fc), top: B:87:0x0031 }] */
    /* JADX WARN: Code duplicated, block: B:51:0x014d A[Catch: all -> 0x005a, Exception -> 0x0144, TryCatch #4 {all -> 0x005a, blocks: (B:13:0x0053, B:43:0x0128, B:45:0x0132, B:35:0x00d2, B:37:0x00d8, B:39:0x00f7, B:73:0x020b, B:62:0x01d8, B:49:0x0149, B:51:0x014d, B:53:0x01a8, B:55:0x01ae, B:57:0x01be, B:60:0x01d0, B:61:0x01d7, B:28:0x00ad, B:30:0x00b5, B:33:0x00be, B:68:0x01fc), top: B:87:0x0031 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x01a8 A[Catch: all -> 0x005a, Exception -> 0x0144, TryCatch #4 {all -> 0x005a, blocks: (B:13:0x0053, B:43:0x0128, B:45:0x0132, B:35:0x00d2, B:37:0x00d8, B:39:0x00f7, B:73:0x020b, B:62:0x01d8, B:49:0x0149, B:51:0x014d, B:53:0x01a8, B:55:0x01ae, B:57:0x01be, B:60:0x01d0, B:61:0x01d7, B:28:0x00ad, B:30:0x00b5, B:33:0x00be, B:68:0x01fc), top: B:87:0x0031 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x01ae A[Catch: all -> 0x005a, Exception -> 0x0144, TRY_LEAVE, TryCatch #4 {all -> 0x005a, blocks: (B:13:0x0053, B:43:0x0128, B:45:0x0132, B:35:0x00d2, B:37:0x00d8, B:39:0x00f7, B:73:0x020b, B:62:0x01d8, B:49:0x0149, B:51:0x014d, B:53:0x01a8, B:55:0x01ae, B:57:0x01be, B:60:0x01d0, B:61:0x01d7, B:28:0x00ad, B:30:0x00b5, B:33:0x00be, B:68:0x01fc), top: B:87:0x0031 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x01d0 A[Catch: all -> 0x005a, Exception -> 0x01ce, TryCatch #0 {Exception -> 0x01ce, blocks: (B:62:0x01d8, B:57:0x01be, B:60:0x01d0, B:61:0x01d7), top: B:80:0x01be }] */
    /* JADX WARN: Code duplicated, block: B:7:0x001d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0126 -> B:88:0x0128). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull java.lang.String r20, @org.jetbrains.annotations.NotNull java.util.Set<java.lang.String> r21, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.vk.pushme.logic.usecase.UnsubscribeUseCase.Result> r22) {
        /*
            Method dump skipped, instruction units count: 564
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.usecase.UnsubscribeUseCase.invoke(java.lang.String, java.util.Set, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
