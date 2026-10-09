package com.vk.pushme.logic.usecase;

import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.common.Logger;
import com.vk.pushme.database.dao.SubscriptionDao;
import com.vk.pushme.network.PushMeApi;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0014B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0086B¢\u0006\u0002\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase;", "", "subscriptionDao", "Lcom/vk/pushme/database/dao/SubscriptionDao;", "apiCreator", "Lkotlin/Function0;", "Lcom/vk/pushme/network/PushMeApi;", "logger", "Lcom/vk/pushme/common/Logger;", "analyticsHandler", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "<init>", "(Lcom/vk/pushme/database/dao/SubscriptionDao;Lkotlin/jvm/functions/Function0;Lcom/vk/pushme/common/Logger;Lcom/vk/pushme/analytcis/AnalyticsHandler;)V", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "invoke", "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result;", "pushToken", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Result", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDeleteTokenUseCase.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeleteTokenUseCase.kt\ncom/vk/pushme/logic/usecase/DeleteTokenUseCase\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,92:1\n1563#2:93\n1634#2,3:94\n*S KotlinDebug\n*F\n+ 1 DeleteTokenUseCase.kt\ncom/vk/pushme/logic/usecase/DeleteTokenUseCase\n*L\n25#1:93\n25#1:94,3\n*E\n"})
public final class DeleteTokenUseCase {

    @NotNull
    private final AnalyticsHandler analyticsHandler;

    @NotNull
    private final Function0<PushMeApi> apiCreator;

    @NotNull
    private final Logger logger;

    @NotNull
    private final Mutex mutex;

    @NotNull
    private final SubscriptionDao subscriptionDao;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result;", "", "<init>", "()V", "OK", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result$UnknownError;", "Lcom/vk/pushme/logic/usecase/DeleteTokenUseCase$Result;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.DeleteTokenUseCase$invoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.DeleteTokenUseCase", f = "DeleteTokenUseCase.kt", i = {0, 1, 2, 2, 2, 2, 2}, l = {23, 25, 35}, m = "invoke", n = {"pushToken", "pushToken", "pushToken", "applications", ApiUris.AUTHORITY_API, "result", "application"}, s = {"L$0", "L$0", "L$0", "L$1", "L$2", "L$3", "L$5"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
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
            return DeleteTokenUseCase.this.invoke(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public DeleteTokenUseCase(@NotNull SubscriptionDao subscriptionDao, @NotNull Function0<? extends PushMeApi> apiCreator, @NotNull Logger logger, @NotNull AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(subscriptionDao, "subscriptionDao");
        Intrinsics.checkNotNullParameter(apiCreator, "apiCreator");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(analyticsHandler, "analyticsHandler");
        this.subscriptionDao = subscriptionDao;
        this.apiCreator = apiCreator;
        this.analyticsHandler = analyticsHandler;
        this.logger = logger.createLogger("DeleteTokenUseCase");
        this.mutex = MutexKt.Mutex$default(false, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00b2 A[Catch: all -> 0x004f, Exception -> 0x0052, LOOP:0: B:35:0x00ac->B:37:0x00b2, LOOP_END, Merged into TryCatch #1 {all -> 0x004f, Exception -> 0x0052, blocks: (B:14:0x004a, B:49:0x0133, B:51:0x013d, B:44:0x00ec, B:46:0x00f2, B:61:0x01fe, B:53:0x015a, B:55:0x015e, B:56:0x01aa, B:58:0x01ae, B:59:0x01f8, B:60:0x01fd, B:64:0x021b, B:67:0x0225, B:23:0x0061, B:34:0x009b, B:35:0x00ac, B:37:0x00b2, B:38:0x00c0, B:40:0x00ca, B:43:0x00d9, B:31:0x008d), top: B:74:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:40:0x00ca A[Catch: all -> 0x004f, Exception -> 0x0052, Merged into TryCatch #1 {all -> 0x004f, Exception -> 0x0052, blocks: (B:14:0x004a, B:49:0x0133, B:51:0x013d, B:44:0x00ec, B:46:0x00f2, B:61:0x01fe, B:53:0x015a, B:55:0x015e, B:56:0x01aa, B:58:0x01ae, B:59:0x01f8, B:60:0x01fd, B:64:0x021b, B:67:0x0225, B:23:0x0061, B:34:0x009b, B:35:0x00ac, B:37:0x00b2, B:38:0x00c0, B:40:0x00ca, B:43:0x00d9, B:31:0x008d), top: B:74:0x002a }, TRY_LEAVE] */
    /* JADX WARN: Code duplicated, block: B:43:0x00d9 A[Catch: all -> 0x004f, Exception -> 0x0052, Merged into TryCatch #1 {all -> 0x004f, Exception -> 0x0052, blocks: (B:14:0x004a, B:49:0x0133, B:51:0x013d, B:44:0x00ec, B:46:0x00f2, B:61:0x01fe, B:53:0x015a, B:55:0x015e, B:56:0x01aa, B:58:0x01ae, B:59:0x01f8, B:60:0x01fd, B:64:0x021b, B:67:0x0225, B:23:0x0061, B:34:0x009b, B:35:0x00ac, B:37:0x00b2, B:38:0x00c0, B:40:0x00ca, B:43:0x00d9, B:31:0x008d), top: B:74:0x002a }, TRY_ENTER] */
    /* JADX WARN: Code duplicated, block: B:46:0x00f2 A[Catch: all -> 0x004f, Exception -> 0x0052, Merged into TryCatch #1 {all -> 0x004f, Exception -> 0x0052, blocks: (B:14:0x004a, B:49:0x0133, B:51:0x013d, B:44:0x00ec, B:46:0x00f2, B:61:0x01fe, B:53:0x015a, B:55:0x015e, B:56:0x01aa, B:58:0x01ae, B:59:0x01f8, B:60:0x01fd, B:64:0x021b, B:67:0x0225, B:23:0x0061, B:34:0x009b, B:35:0x00ac, B:37:0x00b2, B:38:0x00c0, B:40:0x00ca, B:43:0x00d9, B:31:0x008d), top: B:74:0x002a }] */
    /* JADX WARN: Code duplicated, block: B:61:0x01fe A[Catch: all -> 0x004f, Exception -> 0x0052, Merged into TryCatch #1 {all -> 0x004f, Exception -> 0x0052, blocks: (B:14:0x004a, B:49:0x0133, B:51:0x013d, B:44:0x00ec, B:46:0x00f2, B:61:0x01fe, B:53:0x015a, B:55:0x015e, B:56:0x01aa, B:58:0x01ae, B:59:0x01f8, B:60:0x01fd, B:64:0x021b, B:67:0x0225, B:23:0x0061, B:34:0x009b, B:35:0x00ac, B:37:0x00b2, B:38:0x00c0, B:40:0x00ca, B:43:0x00d9, B:31:0x008d), top: B:74:0x002a }, TRY_LEAVE] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0130, code lost:
    
        if (r0 == r3) goto L48;
     */
    /* JADX WARN: Instruction removed from duplicated block: B:46:0x00f2, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:61:0x01fe, please report this as an issue */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x0130 -> B:49:0x0133). Please report as a decompilation issue!!! */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(@org.jetbrains.annotations.NotNull java.lang.String r17, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super com.vk.pushme.logic.usecase.DeleteTokenUseCase.Result> r18) {
        /*
            Method dump skipped, instruction units count: 586
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vk.pushme.logic.usecase.DeleteTokenUseCase.invoke(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
