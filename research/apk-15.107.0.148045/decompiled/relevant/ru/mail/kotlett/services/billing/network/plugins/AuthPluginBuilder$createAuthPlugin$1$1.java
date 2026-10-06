package ru.mail.kotlett.services.billing.network.plugins;

import io.ktor.client.plugins.api.OnRequestContext;
import io.ktor.client.request.HttpRequestBuilder;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import ru.mail.kotlett.services.billing.analytics.Event;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n"}, d2 = {"<anonymous>", "", "Lio/ktor/client/plugins/api/OnRequestContext;", Event.Companion.Network.Fail.REQUEST_TAG, "Lio/ktor/client/request/HttpRequestBuilder;", "<unused var>", ""}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "ru.mail.kotlett.services.billing.network.plugins.AuthPluginBuilder$createAuthPlugin$1$1", f = "AuthPlugin.kt", i = {0}, l = {13}, m = "invokeSuspend", n = {Event.Companion.Network.Fail.REQUEST_TAG}, s = {"L$0"}, v = 1)
final class AuthPluginBuilder$createAuthPlugin$1$1 extends SuspendLambda implements Function4<OnRequestContext, HttpRequestBuilder, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<String> $email;
    final /* synthetic */ Function1<Continuation<? super String>, Object> $token;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AuthPluginBuilder$createAuthPlugin$1$1(Function1<? super Continuation<? super String>, ? extends Object> function1, Function0<String> function0, Continuation<? super AuthPluginBuilder$createAuthPlugin$1$1> continuation) {
        super(4, continuation);
        this.$token = function1;
        this.$email = function0;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(OnRequestContext onRequestContext, HttpRequestBuilder httpRequestBuilder, Object obj, Continuation<? super Unit> continuation) {
        AuthPluginBuilder$createAuthPlugin$1$1 authPluginBuilder$createAuthPlugin$1$1 = new AuthPluginBuilder$createAuthPlugin$1$1(this.$token, this.$email, continuation);
        authPluginBuilder$createAuthPlugin$1$1.L$0 = httpRequestBuilder;
        return authPluginBuilder$createAuthPlugin$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws AuthTokenException {
        String str;
        HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i10 = this.label;
        try {
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                str = (String) httpRequestBuilder.getAttributes().getOrNull(AuthPluginBuilder.INSTANCE.getOVERRIDE_TOKEN$service_billing_release());
                if (str == null) {
                    Function1<Continuation<? super String>, Object> function1 = this.$token;
                    this.L$0 = httpRequestBuilder;
                    this.label = 1;
                    obj = function1.invoke(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                httpRequestBuilder.getUrl().getParameters().set("access_token", str);
                httpRequestBuilder.getHeaders().set("email", this.$email.invoke());
                httpRequestBuilder.getHeaders().set("isKotlett", "true");
                return Unit.INSTANCE;
            }
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            str = (String) obj;
            httpRequestBuilder.getUrl().getParameters().set("access_token", str);
            httpRequestBuilder.getHeaders().set("email", this.$email.invoke());
            httpRequestBuilder.getHeaders().set("isKotlett", "true");
            return Unit.INSTANCE;
        } catch (CancellationException e10) {
            throw e10;
        } catch (Throwable th2) {
            throw new AuthTokenException(th2);
        }
    }
}
