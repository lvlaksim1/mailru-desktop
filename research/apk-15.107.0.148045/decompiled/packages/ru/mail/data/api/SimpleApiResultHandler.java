package ru.mail.data.api;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.processors.auth.NoAuthHandler;
import ru.mail.network.retrofit.ApiResult;
import ru.mail.serverapi.retrofit.session.SessionError;
import ru.mail.serverapi.retrofit.session.SessionException;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J$\u0010\u0006\u001a\u0004\u0018\u0001H\u0007\"\u0004\b\u0000\u0010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00070\tH\u0086@¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/data/api/SimpleApiResultHandler;", "", "noAuthHandler", "Lru/mail/logic/processors/auth/NoAuthHandler;", "<init>", "(Lru/mail/logic/processors/auth/NoAuthHandler;)V", "handle", "R", "apiResult", "Lru/mail/network/retrofit/ApiResult;", "(Lru/mail/network/retrofit/ApiResult;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processSessionException", "", OkListenerKt.KEY_EXCEPTION, "Lru/mail/serverapi/retrofit/session/SessionException;", "(Lru/mail/serverapi/retrofit/session/SessionException;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SimpleApiResultHandler {

    @NotNull
    private final NoAuthHandler noAuthHandler;

    /* JADX INFO: renamed from: ru.mail.data.api.SimpleApiResultHandler$handle$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.data.api.SimpleApiResultHandler", f = "SimpleApiResultHandler.kt", i = {0, 0}, l = {19}, m = "handle", n = {"apiResult", "throwable"}, s = {"L$0", "L$1"}, v = 1)
    static final class AnonymousClass1<R> extends ContinuationImpl {
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
            return SimpleApiResultHandler.this.handle(null, this);
        }
    }

    public SimpleApiResultHandler(@NotNull NoAuthHandler noAuthHandler) {
        Intrinsics.checkNotNullParameter(noAuthHandler, "noAuthHandler");
        this.noAuthHandler = noAuthHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object processSessionException(SessionException sessionException, Continuation<? super Unit> continuation) {
        SessionError reason = sessionException.getReason();
        if (reason instanceof SessionError.NoAuth) {
            Object objNoAuth = this.noAuthHandler.noAuth(((SessionError.NoAuth) reason).getLogin(), "NoAuth", continuation);
            return objNoAuth == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objNoAuth : Unit.INSTANCE;
        }
        if ((reason instanceof SessionError.SwitchToImap) || (reason instanceof SessionError.ConnectionError) || (reason instanceof SessionError.Unexpected)) {
            return Unit.INSTANCE;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Nullable
    public final <R> Object handle(@NotNull ApiResult<R> apiResult, @NotNull Continuation<? super R> continuation) {
        AnonymousClass1 anonymousClass1;
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
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj);
            if (apiResult instanceof ApiResult.Success) {
                return ((ApiResult.Success) apiResult).getData();
            }
            if (apiResult instanceof ApiResult.Error) {
                return null;
            }
            if (!(apiResult instanceof ApiResult.Exception)) {
                throw new NoWhenBranchMatchedException();
            }
            Throwable cause = ((ApiResult.Exception) apiResult).getCause();
            if (cause instanceof SessionException) {
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(apiResult);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(cause);
                anonymousClass1.label = 1;
                if (processSessionException((SessionException) cause, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                boolean z10 = cause instanceof IOException;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return null;
    }
}
