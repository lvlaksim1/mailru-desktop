package ru.mail.authorizationsdk.data.common;

import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import ru.mail.authorizationsdk.data.client.calladapter.model.NetResponse;
import ru.mail.authorizationsdk.data.model.MailOAuthCredentials;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lru/mail/authorizationsdk/data/client/calladapter/model/NetResponse;", "Lru/mail/authorizationsdk/data/model/MailOAuthCredentials;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "ru.mail.authorizationsdk.data.common.CommonAuthorizationRepository$getMailTokensByPassword$result$1", f = "CommonAuthorizationRepository.kt", i = {0, 0}, l = {57}, m = "invokeSuspend", n = {"bodyParams", "tsaCookieValue"}, s = {"L$0", "L$1"}, v = 1)
final class CommonAuthorizationRepository$getMailTokensByPassword$result$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NetResponse<? extends MailOAuthCredentials>>, Object> {
    final /* synthetic */ Map<String, String> $additionalParams;
    final /* synthetic */ String $bindToken;
    final /* synthetic */ String $email;
    final /* synthetic */ String $password;
    final /* synthetic */ String $tsaCookie;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ CommonAuthorizationRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CommonAuthorizationRepository$getMailTokensByPassword$result$1(CommonAuthorizationRepository commonAuthorizationRepository, String str, Map<String, String> map, String str2, String str3, String str4, Continuation<? super CommonAuthorizationRepository$getMailTokensByPassword$result$1> continuation) {
        super(2, continuation);
        this.this$0 = commonAuthorizationRepository;
        this.$tsaCookie = str;
        this.$additionalParams = map;
        this.$email = str2;
        this.$password = str3;
        this.$bindToken = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CommonAuthorizationRepository$getMailTokensByPassword$result$1(this.this$0, this.$tsaCookie, this.$additionalParams, this.$email, this.$password, this.$bindToken, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super NetResponse<? extends MailOAuthCredentials>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super NetResponse<MailOAuthCredentials>>) continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        if (i10 != 0) {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return obj;
        }
        ResultKt.throwOnFailure(obj);
        Map map = MapsKt.toMap(this.this$0.basePasswordOauthParams.getParamsForRequest());
        String str2 = this.$tsaCookie;
        if (str2 == null || str2.length() == 0) {
            str = null;
        } else {
            str = "tsa=" + this.$tsaCookie;
        }
        String str3 = str;
        CommonAuthorizationMailApi commonAuthorizationMailApi = this.this$0.commonAuthorizationMailApi;
        Map<String, String> mapPlus = MapsKt.plus(map, this.$additionalParams);
        String str4 = this.this$0.clientId;
        String str5 = this.$email;
        String str6 = this.$password;
        String str7 = this.$bindToken;
        this.L$0 = SpillingKt.nullOutSpilledVariable(map);
        this.L$1 = SpillingKt.nullOutSpilledVariable(str3);
        this.label = 1;
        Object mailPasswordAuthorization = commonAuthorizationMailApi.getMailPasswordAuthorization(str5, str6, str7, str4, str3, mapPlus, this);
        return mailPasswordAuthorization == coroutine_suspended ? coroutine_suspended : mailPasswordAuthorization;
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super NetResponse<MailOAuthCredentials>> continuation) {
        return ((CommonAuthorizationRepository$getMailTokensByPassword$result$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }
}
