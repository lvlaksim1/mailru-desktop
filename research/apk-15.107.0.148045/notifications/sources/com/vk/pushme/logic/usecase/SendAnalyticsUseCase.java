package com.vk.pushme.logic.usecase;

import com.vk.pushme.analytcis.AnalyticsErrorType;
import com.vk.pushme.analytcis.AnalyticsHandler;
import com.vk.pushme.common.Logger;
import com.vk.pushme.network.AnalyticsApi;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0010B%\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086B¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase;", "", "apiCreator", "Lkotlin/Function0;", "Lcom/vk/pushme/network/AnalyticsApi;", "logger", "Lcom/vk/pushme/common/Logger;", "analyticsHandler", "Lcom/vk/pushme/analytcis/AnalyticsHandler;", "<init>", "(Lkotlin/jvm/functions/Function0;Lcom/vk/pushme/common/Logger;Lcom/vk/pushme/analytcis/AnalyticsHandler;)V", "invoke", "Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Result", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SendAnalyticsUseCase {

    @NotNull
    private final AnalyticsHandler analyticsHandler;

    @NotNull
    private final Function0<AnalyticsApi> apiCreator;

    @NotNull
    private final Logger logger;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result;", "", "<init>", "()V", "OK", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result$UnknownError;", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result$OK;", "Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result;", "<init>", "()V", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result$UnknownError;", "Lcom/vk/pushme/logic/usecase/SendAnalyticsUseCase$Result;", "t", "", "<init>", "(Ljava/lang/Throwable;)V", "getT", "()Ljava/lang/Throwable;", "toString", "", "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

    /* JADX INFO: renamed from: com.vk.pushme.logic.usecase.SendAnalyticsUseCase$invoke$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.vk.pushme.logic.usecase.SendAnalyticsUseCase", f = "SendAnalyticsUseCase.kt", i = {0, 0}, l = {20}, m = "invoke", n = {"url", ApiUris.AUTHORITY_API}, s = {"L$0", "L$1"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
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
            return SendAnalyticsUseCase.this.invoke(null, this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SendAnalyticsUseCase(@NotNull Function0<? extends AnalyticsApi> apiCreator, @NotNull Logger logger, @NotNull AnalyticsHandler analyticsHandler) {
        Intrinsics.checkNotNullParameter(apiCreator, "apiCreator");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(analyticsHandler, "analyticsHandler");
        this.apiCreator = apiCreator;
        this.analyticsHandler = analyticsHandler;
        this.logger = logger.createLogger("SendAnalyticsUseCase");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Nullable
    public final Object invoke(@NotNull String str, @NotNull Continuation<? super Result> continuation) {
        AnonymousClass1 anonymousClass1;
        Object objMo12678sendRequestgIAlus;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i10 = anonymousClass1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i10 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        try {
            if (i11 == 0) {
                ResultKt.throwOnFailure(obj);
                if (StringsKt.isBlank(str)) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                Logger.info$default(this.logger, "UseCase started", null, 2, null);
                AnalyticsApi analyticsApiInvoke = this.apiCreator.invoke();
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(analyticsApiInvoke);
                anonymousClass1.label = 1;
                objMo12678sendRequestgIAlus = analyticsApiInvoke.mo12678sendRequestgIAlus(str, anonymousClass1);
                if (objMo12678sendRequestgIAlus == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objMo12678sendRequestgIAlus = ((kotlin.Result) obj).getValue();
            }
            if (kotlin.Result.m13129isSuccessimpl(objMo12678sendRequestgIAlus)) {
                Logger.info$default(this.logger, "Successfully sent analytics request", null, 2, null);
                return Result.OK.INSTANCE;
            }
            Throwable thM13126exceptionOrNullimpl = kotlin.Result.m13126exceptionOrNullimpl(objMo12678sendRequestgIAlus);
            if (thM13126exceptionOrNullimpl == null) {
                throw new IllegalArgumentException("Required value was null.");
            }
            AnalyticsHandler analyticsHandler = this.analyticsHandler;
            String message = thM13126exceptionOrNullimpl.getMessage();
            if (message == null) {
                message = "Unknown error";
            }
            analyticsHandler.analyticsError(AnalyticsErrorType.SEND_API_ANALYTIC_ERROR, message);
            this.logger.error("Failed to send analytics request", thM13126exceptionOrNullimpl);
            return new Result.UnknownError(thM13126exceptionOrNullimpl);
        } catch (Exception e10) {
            this.logger.error("Failed to send analytics due to exception", e10);
            AnalyticsHandler analyticsHandler2 = this.analyticsHandler;
            String message2 = e10.getMessage();
            analyticsHandler2.analyticsError(AnalyticsErrorType.UNKNOWN_ERROR, message2 != null ? message2 : "Unknown error");
            return new Result.UnknownError(e10);
        }
    }
}
