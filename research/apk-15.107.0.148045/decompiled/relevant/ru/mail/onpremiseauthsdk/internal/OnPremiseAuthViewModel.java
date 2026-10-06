package ru.mail.onpremiseauthsdk.internal;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.concurrent.CancellationException;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.onpremiseauthsdk.api.OnPremiseAuthDependencies;
import ru.mail.onpremiseauthsdk.api.OnPremiseTokenStorage;
import ru.mail.onpremiseauthsdk.api.models.AuthData;
import ru.mail.onpremiseauthsdk.internal.network.OnPremiseAuth;
import ru.mail.onpremiseauthsdk.internal.network.OnPremiseAuthApi;
import ru.mail.onpremiseauthsdk.internal.network.OnPremiseParser;
import ru.mail.util.log.Logger;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@HiltViewModel
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J>\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00140\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u0019J6\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u00162\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00140\u00192\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00140\u0019J\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0010\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011¨\u0006$"}, d2 = {"Lru/mail/onpremiseauthsdk/internal/OnPremiseAuthViewModel;", "Landroidx/lifecycle/ViewModel;", "dependencies", "Lru/mail/onpremiseauthsdk/api/OnPremiseAuthDependencies;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lru/mail/onpremiseauthsdk/api/OnPremiseAuthDependencies;Lru/mail/util/log/Logger;)V", ApiUris.AUTHORITY_API, "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuthApi;", "getApi", "()Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuthApi;", "api$delegate", "Lkotlin/Lazy;", "parser", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseParser;", "getParser", "()Lru/mail/onpremiseauthsdk/internal/network/OnPremiseParser;", "parser$delegate", "getTokenForTest", "", "email", "", "password", "onSuccess", "Lkotlin/Function1;", "Lru/mail/onpremiseauthsdk/api/models/AuthData;", "onFailure", "", "exchangeCodeForTokens", "code", "Lru/mail/onpremiseauthsdk/internal/network/OnPremiseAuth;", "parseResponse", "response", "saveAuthData", "authData", "onpremiseauthsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class OnPremiseAuthViewModel extends ViewModel {

    /* JADX INFO: renamed from: api$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy api;

    @NotNull
    private final OnPremiseAuthDependencies dependencies;

    @NotNull
    private final Logger logger;

    /* JADX INFO: renamed from: parser$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy parser;

    /* JADX INFO: renamed from: ru.mail.onpremiseauthsdk.internal.OnPremiseAuthViewModel$exchangeCodeForTokens$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.onpremiseauthsdk.internal.OnPremiseAuthViewModel$exchangeCodeForTokens$1", f = "OnPremiseAuthViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $code;
        final /* synthetic */ Function1<OnPremiseAuth, Unit> $onFailure;
        final /* synthetic */ Function1<AuthData, Unit> $onSuccess;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(String str, Function1<? super AuthData, Unit> function1, Function1<? super OnPremiseAuth, Unit> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$code = str;
            this.$onSuccess = function1;
            this.$onFailure = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return OnPremiseAuthViewModel.this.new AnonymousClass1(this.$code, this.$onSuccess, this.$onFailure, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            OnPremiseAuth illegalState;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            try {
                illegalState = OnPremiseAuthViewModel.this.parseResponse(OnPremiseAuthViewModel.this.getApi().getAccessAndRefresh(this.$code));
            } catch (CancellationException e10) {
                throw e10;
            } catch (Exception e11) {
                illegalState = new OnPremiseAuth.IllegalState(e11);
            }
            if (illegalState instanceof OnPremiseAuth.Success) {
                this.$onSuccess.invoke(((OnPremiseAuth.Success) illegalState).getAuthData());
            } else {
                if (!(illegalState instanceof OnPremiseAuth.Failed) && !(illegalState instanceof OnPremiseAuth.IllegalState)) {
                    throw new NoWhenBranchMatchedException();
                }
                this.$onFailure.invoke(illegalState);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.onpremiseauthsdk.internal.OnPremiseAuthViewModel$getTokenForTest$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.onpremiseauthsdk.internal.OnPremiseAuthViewModel$getTokenForTest$1", f = "OnPremiseAuthViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class C23661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $email;
        final /* synthetic */ Function1<Throwable, Unit> $onFailure;
        final /* synthetic */ Function1<AuthData, Unit> $onSuccess;
        final /* synthetic */ String $password;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C23661(String str, String str2, Function1<? super AuthData, Unit> function1, Function1<? super Throwable, Unit> function2, Continuation<? super C23661> continuation) {
            super(2, continuation);
            this.$email = str;
            this.$password = str2;
            this.$onSuccess = function1;
            this.$onFailure = function2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C23661 c23661 = OnPremiseAuthViewModel.this.new C23661(this.$email, this.$password, this.$onSuccess, this.$onFailure, continuation);
            c23661.L$0 = obj;
            return c23661;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objM13123constructorimpl;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            OnPremiseAuthViewModel onPremiseAuthViewModel = OnPremiseAuthViewModel.this;
            String str = this.$email;
            String str2 = this.$password;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(onPremiseAuthViewModel.getApi().getTokenForTest(str, str2));
            } catch (Throwable th2) {
                Result.Companion companion2 = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
            }
            OnPremiseAuthViewModel onPremiseAuthViewModel2 = OnPremiseAuthViewModel.this;
            Function1<AuthData, Unit> function1 = this.$onSuccess;
            if (Result.m13129isSuccessimpl(objM13123constructorimpl)) {
                JSONObject jSONObjectOptJSONObject = new JSONObject((String) objM13123constructorimpl).optJSONObject(MailOAuthRequest.BODY_KEY);
                Intrinsics.checkNotNull(jSONObjectOptJSONObject);
                String strOptString = jSONObjectOptJSONObject.optString("access_token");
                String strOptString2 = jSONObjectOptJSONObject.optString("refresh_token");
                int iOptInt = jSONObjectOptJSONObject.optInt("expires_in");
                Intrinsics.checkNotNull(strOptString);
                Intrinsics.checkNotNull(strOptString2);
                AuthData authData = new AuthData(strOptString, strOptString2, iOptInt);
                onPremiseAuthViewModel2.saveAuthData(authData);
                function1.invoke(authData);
            }
            Function1<Throwable, Unit> function2 = this.$onFailure;
            Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
            if (thM13126exceptionOrNullimpl != null) {
                function2.invoke(thM13126exceptionOrNullimpl);
                thM13126exceptionOrNullimpl.printStackTrace();
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C23661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public OnPremiseAuthViewModel(@NotNull OnPremiseAuthDependencies dependencies, @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(dependencies, "dependencies");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.dependencies = dependencies;
        this.logger = logger;
        this.api = LazyKt.lazy(new Function0() { // from class: ru.mail.onpremiseauthsdk.internal.g
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OnPremiseAuthViewModel.api_delegate$lambda$0(this.f96143a);
            }
        });
        this.parser = LazyKt.lazy(new Function0() { // from class: ru.mail.onpremiseauthsdk.internal.h
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return OnPremiseAuthViewModel.parser_delegate$lambda$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OnPremiseAuthApi api_delegate$lambda$0(OnPremiseAuthViewModel onPremiseAuthViewModel) {
        return new OnPremiseAuthApi(onPremiseAuthViewModel.dependencies, onPremiseAuthViewModel.logger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnPremiseAuthApi getApi() {
        return (OnPremiseAuthApi) this.api.getValue();
    }

    private final OnPremiseParser getParser() {
        return (OnPremiseParser) this.parser.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OnPremiseAuth parseResponse(String response) {
        OnPremiseAuth webView = getParser().parseWebView(new JSONObject(response));
        if (webView instanceof OnPremiseAuth.Success) {
            saveAuthData(((OnPremiseAuth.Success) webView).getAuthData());
            return webView;
        }
        if ((webView instanceof OnPremiseAuth.Failed) || (webView instanceof OnPremiseAuth.IllegalState)) {
            return webView;
        }
        throw new NoWhenBranchMatchedException();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OnPremiseParser parser_delegate$lambda$0() {
        return new OnPremiseParser();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveAuthData(AuthData authData) {
        OnPremiseTokenStorage tokenStorage = this.dependencies.getTokenStorage();
        tokenStorage.saveAccessToken(authData.getAccessToken());
        tokenStorage.saveRefreshToken(authData.getRefreshToken());
        tokenStorage.saveExpiresIn(authData.getExpiresIn());
    }

    public final void exchangeCodeForTokens(@NotNull String code, @NotNull Function1<? super AuthData, Unit> onSuccess, @NotNull Function1<? super OnPremiseAuth, Unit> onFailure) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(code, onSuccess, onFailure, null), 2, null);
    }

    public final void getTokenForTest(@NotNull String email, @NotNull String password, @NotNull Function1<? super AuthData, Unit> onSuccess, @NotNull Function1<? super Throwable, Unit> onFailure) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onFailure, "onFailure");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new C23661(email, password, onSuccess, onFailure, null), 2, null);
    }
}
