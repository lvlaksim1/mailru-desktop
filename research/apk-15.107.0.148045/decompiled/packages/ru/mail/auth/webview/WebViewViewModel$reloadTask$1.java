package ru.mail.auth.webview;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "ru.mail.auth.webview.WebViewViewModel$reloadTask$1", f = "WebViewViewModel.kt", i = {}, l = {25}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class WebViewViewModel$reloadTask$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ WebViewViewModel<E> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WebViewViewModel$reloadTask$1(WebViewViewModel<E> webViewViewModel, Continuation<? super WebViewViewModel$reloadTask$1> continuation) {
        super(1, continuation);
        this.this$0 = webViewViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new WebViewViewModel$reloadTask$1(this.this$0, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        if (i10 == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(10000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((WebViewViewModel) this.this$0).attemptNumber <= 3) {
            this.this$0.newAttempt();
        } else {
            this.this$0.onPageError();
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((WebViewViewModel$reloadTask$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
