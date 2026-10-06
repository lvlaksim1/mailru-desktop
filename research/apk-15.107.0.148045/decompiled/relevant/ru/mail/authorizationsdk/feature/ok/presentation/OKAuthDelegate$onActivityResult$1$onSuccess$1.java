package ru.mail.authorizationsdk.feature.ok.presentation;

import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;
import ru.mail.authorizationsdk.feature.ok.domain.OKDataForAuth;
import ru.mail.authorizationsdk.feature.ok.domain.OKDataForAuthResult;
import ru.mail.march.viewmodel.MutableEventFlow;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "ru.mail.authorizationsdk.feature.ok.presentation.OKAuthDelegate$onActivityResult$1$onSuccess$1", f = "OKAuthDelegate.kt", i = {0, 0}, l = {76}, m = "invokeSuspend", n = {"$this$launch", CommonConstant.KEY_ACCESS_TOKEN}, s = {"L$0", "L$1"}, v = 1)
final class OKAuthDelegate$onActivityResult$1$onSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ JSONObject $json;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ OKAuthDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OKAuthDelegate$onActivityResult$1$onSuccess$1(JSONObject jSONObject, OKAuthDelegate oKAuthDelegate, Continuation<? super OKAuthDelegate$onActivityResult$1$onSuccess$1> continuation) {
        super(2, continuation);
        this.$json = jSONObject;
        this.this$0 = oKAuthDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OKAuthDelegate$onActivityResult$1$onSuccess$1 oKAuthDelegate$onActivityResult$1$onSuccess$1 = new OKAuthDelegate$onActivityResult$1$onSuccess$1(this.$json, this.this$0, continuation);
        oKAuthDelegate$onActivityResult$1$onSuccess$1.L$0 = obj;
        return oKAuthDelegate$onActivityResult$1$onSuccess$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        if (i10 == 0) {
            ResultKt.throwOnFailure(obj);
            String strOptString = this.$json.optString("access_token");
            if (strOptString == null) {
                Logger.e$default(this.this$0.logger, "ok accessToken == null", null, 2, null);
                strOptString = "";
            }
            String str = strOptString;
            MutableEventFlow<OKDataForAuthResult> okAuthDataFlow = this.this$0.getOkAuthDataFlow();
            OKDataForAuthResult.Success success = new OKDataForAuthResult.Success(new OKDataForAuth(str, this.this$0.okScopes, this.this$0.appId, this.this$0.getDefaultExpires(), this.this$0.o2Client, this.this$0.clientSecret));
            this.L$0 = SpillingKt.nullOutSpilledVariable(coroutineScope);
            this.L$1 = SpillingKt.nullOutSpilledVariable(str);
            this.label = 1;
            if (okAuthDataFlow.emit(success, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OKAuthDelegate$onActivityResult$1$onSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
