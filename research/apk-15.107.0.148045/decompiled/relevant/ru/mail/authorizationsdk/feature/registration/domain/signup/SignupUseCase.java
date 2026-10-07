package ru.mail.authorizationsdk.feature.registration.domain.signup;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.domain.model.OAuthCredentials;
import ru.mail.authorizationsdk.domain.model.Result;
import ru.mail.authorizationsdk.feature.registration.domain.model.SignupParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086B¢\u0006\u0002\u0010\nJ2\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\rH\u0082@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\rH\u0082@¢\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupUseCase;", "", "repository", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupRepository;", "<init>", "(Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupRepository;)V", "invoke", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult;", "params", "Lru/mail/authorizationsdk/feature/registration/domain/model/SignupParams;", "(Lru/mail/authorizationsdk/feature/registration/domain/model/SignupParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runSignupConfirm", "regToken", "", "email", "xmailFrom", "vkAccessToken", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTokens", "authUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SignupUseCase {
    public static final int $stable = 8;

    @NotNull
    private final SignupRepository repository;

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.registration.domain.signup.SignupUseCase$getTokens$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.registration.domain.signup.SignupUseCase", f = "SignupUseCase.kt", i = {0}, l = {41}, m = "getTokens", n = {"authUrl"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
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
            return SignupUseCase.this.getTokens(null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.registration.domain.signup.SignupUseCase$invoke$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.registration.domain.signup.SignupUseCase", f = "SignupUseCase.kt", i = {0, 1, 1}, l = {8, 12}, m = "invoke", n = {"params", "params", "result"}, s = {"L$0", "L$0", "L$1"}, v = 1)
    static final class C17771 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C17771(Continuation<? super C17771> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SignupUseCase.this.invoke(null, this);
        }
    }

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.registration.domain.signup.SignupUseCase$runSignupConfirm$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.registration.domain.signup.SignupUseCase", f = "SignupUseCase.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {28, 35}, m = "runSignupConfirm", n = {"regToken", "email", "xmailFrom", "vkAccessToken", "regToken", "email", "xmailFrom", "vkAccessToken", "result"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4"}, v = 1)
    static final class C17781 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        C17781(Continuation<? super C17781> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SignupUseCase.this.runSignupConfirm(null, null, null, null, this);
        }
    }

    public SignupUseCase(@NotNull SignupRepository repository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        this.repository = repository;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object getTokens(String str, Continuation<? super SignupResult> continuation) {
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
        Object mailTokensByUrl = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = anonymousClass1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(mailTokensByUrl);
            SignupRepository signupRepository = this.repository;
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(str);
            anonymousClass1.label = 1;
            mailTokensByUrl = signupRepository.getMailTokensByUrl(str, anonymousClass1);
            if (mailTokensByUrl == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i11 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(mailTokensByUrl);
        }
        Result result = (Result) mailTokensByUrl;
        if (result instanceof Result.Failure) {
            return new SignupResult.Error((List) ((Result.Failure) result).getError());
        }
        if (!(result instanceof Result.Success)) {
            throw new NoWhenBranchMatchedException();
        }
        Result.Success success = (Result.Success) result;
        return new SignupResult.Success(((OAuthCredentials) success.getResult()).getAccessToken(), ((OAuthCredentials) success.getResult()).getRefreshToken());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object runSignupConfirm(String str, String str2, String str3, String str4, Continuation<? super SignupResult> continuation) {
        C17781 c17781;
        String str5;
        String str6;
        String str7;
        String str8;
        if (continuation instanceof C17781) {
            c17781 = (C17781) continuation;
            int i10 = c17781.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c17781.label = i10 - Integer.MIN_VALUE;
            } else {
                c17781 = new C17781(continuation);
            }
        } else {
            c17781 = new C17781(continuation);
        }
        C17781 c17782 = c17781;
        Object signupConfirm = c17782.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c17782.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(signupConfirm);
            SignupRepository signupRepository = this.repository;
            c17782.L$0 = SpillingKt.nullOutSpilledVariable(str);
            c17782.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            c17782.L$2 = SpillingKt.nullOutSpilledVariable(str3);
            c17782.L$3 = SpillingKt.nullOutSpilledVariable(str4);
            c17782.label = 1;
            signupConfirm = signupRepository.getSignupConfirm(str, str2, str3, str4, c17782);
            if (signupConfirm != coroutine_suspended) {
                str5 = str;
                str6 = str2;
                str7 = str3;
                str8 = str4;
            }
        }
        if (i11 != 1) {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(signupConfirm);
            return signupConfirm;
        }
        str8 = (String) c17782.L$3;
        str7 = (String) c17782.L$2;
        str6 = (String) c17782.L$1;
        str5 = (String) c17782.L$0;
        ResultKt.throwOnFailure(signupConfirm);
        SignupConfirmResult signupConfirmResult = (SignupConfirmResult) signupConfirm;
        if (!(signupConfirmResult instanceof SignupConfirmResult.Success)) {
            if (signupConfirmResult instanceof SignupConfirmResult.Error) {
                return new SignupResult.Error(((SignupConfirmResult.Error) signupConfirmResult).getErrors());
            }
            throw new NoWhenBranchMatchedException();
        }
        String authUrl = ((SignupConfirmResult.Success) signupConfirmResult).getAuthUrl();
        c17782.L$0 = SpillingKt.nullOutSpilledVariable(str5);
        c17782.L$1 = SpillingKt.nullOutSpilledVariable(str6);
        c17782.L$2 = SpillingKt.nullOutSpilledVariable(str7);
        c17782.L$3 = SpillingKt.nullOutSpilledVariable(str8);
        c17782.L$4 = SpillingKt.nullOutSpilledVariable(signupConfirmResult);
        c17782.label = 2;
        Object tokens = getTokens(authUrl, c17782);
        return tokens == coroutine_suspended ? coroutine_suspended : tokens;
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    @Nullable
    public final Object invoke(@NotNull SignupParams signupParams, @NotNull Continuation<? super SignupResult> continuation) {
        C17771 c17771;
        if (continuation instanceof C17771) {
            c17771 = (C17771) continuation;
            int i10 = c17771.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                c17771.label = i10 - Integer.MIN_VALUE;
            } else {
                c17771 = new C17771(continuation);
            }
        } else {
            c17771 = new C17771(continuation);
        }
        C17771 c17772 = c17771;
        Object userSignup = c17772.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = c17772.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(userSignup);
            SignupRepository signupRepository = this.repository;
            c17772.L$0 = signupParams;
            c17772.label = 1;
            userSignup = signupRepository.getUserSignup(signupParams, c17772);
            if (userSignup != coroutine_suspended) {
            }
        }
        if (i11 != 1) {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(userSignup);
            return userSignup;
        }
        signupParams = (SignupParams) c17772.L$0;
        ResultKt.throwOnFailure(userSignup);
        UserSignupResult userSignupResult = (UserSignupResult) userSignup;
        if (userSignupResult instanceof UserSignupResult.Captcha) {
            UserSignupResult.Captcha captcha = (UserSignupResult.Captcha) userSignupResult;
            return new SignupResult.Captcha(captcha.getRegToken(), captcha.getSiteKey());
        }
        if (userSignupResult instanceof UserSignupResult.Error) {
            return new SignupResult.Error(((UserSignupResult.Error) userSignupResult).getErrors());
        }
        if (userSignupResult instanceof UserSignupResult.ErrorPhoneRequired) {
            return SignupResult.PhoneRequired.INSTANCE;
        }
        if (!(userSignupResult instanceof UserSignupResult.Success)) {
            throw new NoWhenBranchMatchedException();
        }
        String regToken = ((UserSignupResult.Success) userSignupResult).getRegToken();
        String email = signupParams.getEmail();
        String xmailFrom = signupParams.getXmailFrom();
        String vkAccessToken = signupParams.getVkAccessToken();
        c17772.L$0 = SpillingKt.nullOutSpilledVariable(signupParams);
        c17772.L$1 = SpillingKt.nullOutSpilledVariable(userSignupResult);
        c17772.label = 2;
        Object objRunSignupConfirm = runSignupConfirm(regToken, email, xmailFrom, vkAccessToken, c17772);
        return objRunSignupConfirm == coroutine_suspended ? coroutine_suspended : objRunSignupConfirm;
    }
}
