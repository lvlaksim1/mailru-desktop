package ru.mail.auth.authorizationsdk;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.vk.api.sdk.auth.VKAccessToken;
import com.vk.auth.main.VkClientAuthLib;
import dagger.hilt.android.lifecycle.HiltViewModel;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.Authenticator;
import ru.mail.auth.EmailServiceResources;
import ru.mail.auth.loginactivity.LoginActivityDataState;
import ru.mail.auth.loginactivity.LoginActivityEvents;
import ru.mail.authorizationsdk.feature.authactivity.result.AuthResult;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerResult;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccountMigrationResult;
import ru.mail.authorizationsdk.feature.google.common.presentation.GoogleResult;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeResult;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookResult;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainResult;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordResult;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepResult;
import ru.mail.authorizationsdk.feature.socialauth.presentation.SocialAuthResult;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOResult;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordResult;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.authorizesdk.util.extensions.ScreenKt;
import ru.mail.authorizesdk.util.mvi.navigation.Screen;
import ru.mail.authorizesdk.util.mvi.navigation.ViewEvent;
import ru.mail.cloud.app.viewer.ui.ViewerActivity;
import ru.mail.mailbox.cmd.PoolConstants;
import ru.mail.march.concurrent.ViewModelDispatcher;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.march.viewmodel.MutableEventFlow;
import ru.mail.util.kotlin.extension.StringKt;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.AuthSdkFilter;
import ru.mail.util.log.LogFilter;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@HiltViewModel
@Metadata(d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B'\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001e\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\fJ\u000e\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 J\u0010\u0010!\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020+H\u0002J.\u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u00172\u0006\u00103\u001a\u000204J\u0010\u00105\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u000206H\u0002J\u0010\u00107\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u000208H\u0002J\u0010\u00109\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020:H\u0002J\u0010\u0010;\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020<H\u0002J\u0010\u0010=\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020>H\u0002J\u0010\u0010?\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020@H\u0002J\u0010\u0010A\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020DH\u0002J\u0010\u0010E\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020FH\u0002J\u0010\u0010G\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020HH\u0002J\u0010\u0010I\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020LH\u0002J\u0010\u0010M\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020PH\u0002J\u0010\u0010Q\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020RH\u0002J\u0010\u0010S\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020TH\u0002J\u0010\u0010U\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020VH\u0002J\u0010\u0010W\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020XH\u0002J\u0010\u0010Y\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020ZH\u0002J\u0010\u0010[\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\\H\u0002J\u0010\u0010]\u001a\u00020\u001a2\u0006\u0010^\u001a\u000200H\u0002J\f\u0010_\u001a\u00020\u001a*\u00020`H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0018¨\u0006a"}, d2 = {"Lru/mail/auth/authorizationsdk/LoginActivityAuthorizationViewModel;", "Landroidx/lifecycle/ViewModel;", "viewModelDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "logger", "Lru/mail/util/log/Logger;", ViewerActivity.FILTER, "Lru/mail/util/log/LogFilter;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/util/log/Logger;Lru/mail/util/log/LogFilter;)V", "log", "currentDataState", "Lru/mail/auth/loginactivity/LoginActivityDataState;", "viewEvent", "Lru/mail/march/viewmodel/MutableEventFlow;", "Lru/mail/authorizesdk/util/mvi/navigation/ViewEvent;", "getViewEvent", "()Lru/mail/march/viewmodel/MutableEventFlow;", "navEvent", "Lru/mail/authorizesdk/util/mvi/navigation/ViewEvent$Navigation;", "getNavEvent", "isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "navigateTo", "", "screen", "Lru/mail/authorizesdk/util/mvi/navigation/Screen;", "dataState", "onAuthorizeSdkResult", "result", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "onRestoreVkidInOldAuth", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OpenRestoreVkidOldAuth;", "oneTimeCodeSuccessResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OneTimeCodeSuccess;", "phoneAuthResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CloudAuth$Success;", "vkBindInLoginResult", "res", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin;", "onDefaultLoginRequired", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$DefaultLoginRequired;", "showCustomServerScreen", "serviceType", "Lru/mail/auth/EmailServiceResources$MailServiceResources;", "login", "", "password", "isImapLocal", "bundle", "Landroid/os/Bundle;", "ludwigWebCaptchaComposeResult", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "restorePasswordComposeResult", "Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;", "oneTimeCodeComposeResult", "Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;", "yahooResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo;", "yandexResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex;", "outlookResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook;", "googleResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative;", "externalAccMigrationResult", "Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;", "customServerResult", "Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;", "secondStepResult", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;", "socialAuthResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth;", "socialExternalResult", "Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;", "ssoResult", "Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;", "loginResults", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "onImapLocalSuccess", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$ImapLocalSuccess;", "onOAuthImapLocalSuccess", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OAuthImapLocalSuccess;", "passwordAuthResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth;", "restoreWithoutPasswordResult", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestoreWithoutPasswordSuccess;", "afterRegAuthResult", "Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;", "vkPasswordResult", "Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;", "debugLog", "message", "emitViewEvent", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginActivityAuthorizationViewModel extends ViewModel {

    @Nullable
    private LoginActivityDataState currentDataState;

    @NotNull
    private final LogFilter filter;

    @NotNull
    private final MutableStateFlow<Boolean> isLoading;

    @NotNull
    private Logger log;

    @NotNull
    private final MutableEventFlow<ViewEvent.Navigation> navEvent;

    @NotNull
    private final MutableEventFlow<ViewEvent> viewEvent;

    @NotNull
    private final CoroutineDispatcher viewModelDispatcher;

    /* JADX INFO: renamed from: ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel$navigateTo$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel$navigateTo$1", f = "LoginActivityAuthorizationViewModel.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nLoginActivityAuthorizationViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoginActivityAuthorizationViewModel.kt\nru/mail/auth/authorizationsdk/LoginActivityAuthorizationViewModel$navigateTo$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,671:1\n1#2:672\n*E\n"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LoginActivityDataState $dataState;
        final /* synthetic */ Screen<?> $screen;
        int label;
        final /* synthetic */ LoginActivityAuthorizationViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LoginActivityDataState loginActivityDataState, LoginActivityAuthorizationViewModel loginActivityAuthorizationViewModel, Screen<?> screen, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$dataState = loginActivityDataState;
            this.this$0 = loginActivityAuthorizationViewModel;
            this.$screen = screen;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$dataState, this.this$0, this.$screen, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                LoginActivityDataState loginActivityDataState = this.$dataState;
                if (loginActivityDataState != null) {
                    this.this$0.currentDataState = loginActivityDataState;
                }
                MutableEventFlow<ViewEvent.Navigation> navEvent = this.this$0.getNavEvent();
                ViewEvent.Navigation navigation = ScreenKt.toNavigation(this.$screen);
                this.label = 1;
                if (navEvent.emit(navigation, this) == coroutine_suspended) {
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
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX INFO: renamed from: ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel$showCustomServerScreen$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel$showCustomServerScreen$1", f = "LoginActivityAuthorizationViewModel.kt", i = {1}, l = {193, 201}, m = "invokeSuspend", n = {"screen"}, s = {"L$0"}, v = 1)
    static final class C15851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Bundle $bundle;
        final /* synthetic */ boolean $isImapLocal;
        final /* synthetic */ String $login;
        final /* synthetic */ String $password;
        final /* synthetic */ EmailServiceResources.MailServiceResources $serviceType;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C15851(String str, String str2, EmailServiceResources.MailServiceResources mailServiceResources, boolean z10, Bundle bundle, Continuation<? super C15851> continuation) {
            super(2, continuation);
            this.$login = str;
            this.$password = str2;
            this.$serviceType = mailServiceResources;
            this.$isImapLocal = z10;
            this.$bundle = bundle;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LoginActivityAuthorizationViewModel.this.new C15851(this.$login, this.$password, this.$serviceType, this.$isImapLocal, this.$bundle, continuation);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x006c, code lost:
        
            if (r1.emit(r3, r6) == r0) goto L15;
         */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L22
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                java.lang.Object r0 = r6.L$0
                ru.mail.auth.composescreens.CustomServerComposeScreen r0 = (ru.mail.auth.composescreens.CustomServerComposeScreen) r0
                kotlin.ResultKt.throwOnFailure(r7)
                goto L6f
            L16:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L1e:
                kotlin.ResultKt.throwOnFailure(r7)
                goto L38
            L22:
                kotlin.ResultKt.throwOnFailure(r7)
                ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel r7 = ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r7 = r7.isLoading()
                java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                r6.label = r3
                java.lang.Object r7 = r7.emit(r1, r6)
                if (r7 != r0) goto L38
                goto L6e
            L38:
                ru.mail.auth.composescreens.CustomServerComposeScreen r7 = new ru.mail.auth.composescreens.CustomServerComposeScreen
                java.lang.String r1 = r6.$login
                java.lang.String r3 = r6.$password
                ru.mail.auth.EmailServiceResources$MailServiceResources r4 = r6.$serviceType
                java.lang.String r4 = r4.toString()
                boolean r5 = r6.$isImapLocal
                r7.<init>(r1, r3, r4, r5)
                ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel r1 = ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel.this
                ru.mail.auth.loginactivity.LoginActivityDataState$Common r3 = new ru.mail.auth.loginactivity.LoginActivityDataState$Common
                android.os.Bundle r4 = r6.$bundle
                r3.<init>(r4)
                r1.navigateTo(r7, r3)
                ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel r1 = ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel.this
                kotlinx.coroutines.flow.MutableStateFlow r1 = r1.isLoading()
                r3 = 0
                java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                java.lang.Object r7 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r7)
                r6.L$0 = r7
                r6.label = r2
                java.lang.Object r7 = r1.emit(r3, r6)
                if (r7 != r0) goto L6f
            L6e:
                return r0
            L6f:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.auth.authorizationsdk.LoginActivityAuthorizationViewModel.C15851.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C15851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @Inject
    public LoginActivityAuthorizationViewModel(@ViewModelDispatcher @NotNull CoroutineDispatcher viewModelDispatcher, @AppLogger @NotNull Logger logger, @AuthSdkFilter @NotNull LogFilter filter) {
        Intrinsics.checkNotNullParameter(viewModelDispatcher, "viewModelDispatcher");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(filter, "filter");
        this.viewModelDispatcher = viewModelDispatcher;
        this.filter = filter;
        this.log = logger.createLogger("LoginActivityAuthorizationViewModel");
        this.viewEvent = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, this.log, new Flow[0]);
        this.navEvent = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, this.log, new Flow[0]);
        this.isLoading = StateFlowKt.MutableStateFlow(Boolean.FALSE);
    }

    private final void afterRegAuthResult(RegistrationMainResult result) {
        if (result instanceof RegistrationMainResult.Success) {
            RegistrationMainResult.Success success = (RegistrationMainResult.Success) result;
            Authenticator.Type typeByDomain = Authenticator.Type.getTypeByDomain(StringKt.getEmailDomain(success.getEmail()));
            String email = success.getEmail();
            VKAccessToken accessToken$default = VkClientAuthLib.getAccessToken$default(VkClientAuthLib.INSTANCE, null, 1, null);
            emitViewEvent(new LoginActivityEvents.AfterRegAuth(email, accessToken$default != null ? accessToken$default.getAccessToken() : null, success.getAccessToken(), success.getRefreshToken(), typeByDomain.name(), success.getFirstName(), success.getLastName(), success.getPhone(), success.getParentEmail(), success.getForceCreateCollector(), success.getMigrationFrom(), success.getBindType(), success.getRegFlow(), null, 8192, null));
        }
    }

    private final void customServerResult(CustomServerResult result) {
        debugLog("customServerResult = " + result);
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        Bundle bundle = common != null ? common.getBundle() : null;
        this.currentDataState = null;
        if (result instanceof CustomServerResult.Success) {
            CustomServerResult.Success success = (CustomServerResult.Success) result;
            emitViewEvent(new LoginActivityEvents.CustomServerEvents.Success(success.getEmail(), success.getPassword(), bundle));
            Unit unit = Unit.INSTANCE;
        } else if (result instanceof CustomServerResult.LocalImapSuccess) {
            CustomServerResult.LocalImapSuccess localImapSuccess = (CustomServerResult.LocalImapSuccess) result;
            new LoginActivityEvents.ImapLocalSuccess(localImapSuccess.getEmail(), localImapSuccess.getPassword(), localImapSuccess.getCustomServerParams(), bundle);
        } else {
            if (!(result instanceof CustomServerResult.BackClick)) {
                throw new NoWhenBranchMatchedException();
            }
            Unit unit2 = Unit.INSTANCE;
        }
    }

    private final void debugLog(String message) {
        Logger.d$default(this.log, this.filter.filter(message), null, 2, null);
    }

    private final void emitViewEvent(LoginActivityEvents loginActivityEvents) {
        ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, loginActivityEvents);
    }

    private final void externalAccMigrationResult(ExternalAccountMigrationResult result) {
        debugLog("ExternalAccountMigrationResult = " + result);
        this.currentDataState = null;
        if (result instanceof ExternalAccountMigrationResult.MigrationClick) {
            emitViewEvent(LoginActivityEvents.StartXmailMigrationFromLogin.INSTANCE);
            return;
        }
        if (result instanceof ExternalAccountMigrationResult.RegistrationClick) {
            emitViewEvent(LoginActivityEvents.StartRegistrationNewExternalAuth.INSTANCE);
        } else if (result instanceof ExternalAccountMigrationResult.EnterClick) {
            emitViewEvent(new LoginActivityEvents.StartLoginScreenWithXmail(((ExternalAccountMigrationResult.EnterClick) result).getEmail()));
        } else if (!(result instanceof ExternalAccountMigrationResult.BackClick)) {
            throw new NoWhenBranchMatchedException();
        }
    }

    private final void googleResult(AuthResult.GoogleNative result) {
        Bundle bundleBundleOf;
        if (!(result instanceof AuthResult.GoogleNative.AuthDoneGoogle)) {
            if (result instanceof AuthResult.GoogleNative.OtherGoogle) {
                GoogleResult result2 = ((AuthResult.GoogleNative.OtherGoogle) result).getResult();
                if (result2 instanceof GoogleResult.Error) {
                    GoogleResult.Error error = (GoogleResult.Error) result2;
                    emitViewEvent(new LoginActivityEvents.GoogleEvents.Error(error.getLocalizedMessage(), error.getEmail(), error.getCode()));
                    return;
                } else {
                    if (result2 instanceof GoogleResult.MigrantRegistrationRequired) {
                        GoogleResult.MigrantRegistrationRequired migrantRegistrationRequired = (GoogleResult.MigrantRegistrationRequired) result2;
                        emitViewEvent(new LoginActivityEvents.GoogleEvents.MigrantRegistrationRequired(migrantRegistrationRequired.getEmail(), migrantRegistrationRequired.getXmailMigrationFrom(), migrantRegistrationRequired.getMigrantToken()));
                        return;
                    }
                    return;
                }
            }
            return;
        }
        AuthResult.GoogleNative.AuthDoneGoogle authDoneGoogle = (AuthResult.GoogleNative.AuthDoneGoogle) result;
        String email = authDoneGoogle.getEmail();
        String accessToken = authDoneGoogle.getAccessToken();
        String refreshToken = authDoneGoogle.getRefreshToken();
        String googleAccessToken = authDoneGoogle.getGoogleAccessToken();
        String googleRefreshToken = authDoneGoogle.getGoogleRefreshToken();
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        if (common == null || (bundleBundleOf = common.getBundle()) == null) {
            bundleBundleOf = BundleKt.bundleOf();
        }
        emitViewEvent(new LoginActivityEvents.GoogleEvents.Success(email, accessToken, refreshToken, googleAccessToken, googleRefreshToken, "OAUTH", bundleBundleOf, authDoneGoogle.getXmailMigrationFrom()));
    }

    private final void loginResults(AuthResult.Login result) {
        this.currentDataState = null;
        if (Intrinsics.areEqual(result, AuthResult.Login.NeedRegistration.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.Login.NeedRegistration.INSTANCE);
            return;
        }
        if (result instanceof AuthResult.Login.AlreadyLoggedIn) {
            emitViewEvent(new LoginActivityEvents.Login.AlreadyLoggedIn(((AuthResult.Login.AlreadyLoggedIn) result).getEmail()));
            return;
        }
        if (Intrinsics.areEqual(result, AuthResult.Login.BackClick.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.Login.BackClick.INSTANCE);
        } else if (result instanceof AuthResult.Login.VkSilentAuthDone) {
            AuthResult.Login.VkSilentAuthDone vkSilentAuthDone = (AuthResult.Login.VkSilentAuthDone) result;
            emitViewEvent(new LoginActivityEvents.VkSilentSuccess(vkSilentAuthDone.getEmail(), vkSilentAuthDone.getAccessToken(), vkSilentAuthDone.getRefreshToken(), vkSilentAuthDone.getSilentToken()));
        }
    }

    private final void ludwigWebCaptchaComposeResult(LudwigCaptchaResult result) {
        debugLog("LudwigCaptchaResult = " + result);
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        this.currentDataState = null;
        if (result instanceof LudwigCaptchaResult.CaptchaDone) {
            if (!(loginActivityDataState instanceof LoginActivityDataState.LudwigCaptcha)) {
                emitViewEvent(new LoginActivityEvents.LudwigEvents.LudwigCaptchaError("No Ludwig token!"));
                return;
            } else {
                LoginActivityDataState.LudwigCaptcha ludwigCaptcha = (LoginActivityDataState.LudwigCaptcha) loginActivityDataState;
                emitViewEvent(new LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess(ludwigCaptcha.getLudwigToken(), ludwigCaptcha.getLogin(), ludwigCaptcha.getPassword(), ludwigCaptcha.getType(), ludwigCaptcha.getBundle()));
                return;
            }
        }
        if (result instanceof LudwigCaptchaResult.Error) {
            emitViewEvent(new LoginActivityEvents.LudwigEvents.LudwigCaptchaError(((LudwigCaptchaResult.Error) result).getError()));
        } else if (!(result instanceof LudwigCaptchaResult.BackClick)) {
            throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ void navigateTo$default(LoginActivityAuthorizationViewModel loginActivityAuthorizationViewModel, Screen screen, LoginActivityDataState loginActivityDataState, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            loginActivityDataState = null;
        }
        loginActivityAuthorizationViewModel.navigateTo(screen, loginActivityDataState);
    }

    private final void onDefaultLoginRequired(AuthResult.DefaultLoginRequired result) {
        emitViewEvent(new LoginActivityEvents.StartDefaultLoginScreenRequired(result.getEmail()));
    }

    private final void onImapLocalSuccess(AuthResult.ImapLocalSuccess result) {
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        emitViewEvent(new LoginActivityEvents.ImapLocalSuccess(result.getEmail(), result.getPassword(), result.getProviderInfo(), common != null ? common.getBundle() : null));
    }

    private final void onOAuthImapLocalSuccess(AuthResult.OAuthImapLocalSuccess result) {
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        emitViewEvent(new LoginActivityEvents.OAuthImapLocalSuccess(result.getEmail(), result.getVendorAccessToken(), result.getVendorRefreshToken(), result.getProviderInfo(), common != null ? common.getBundle() : null));
    }

    private final void onRestoreVkidInOldAuth(AuthResult.OpenRestoreVkidOldAuth result) {
        emitViewEvent(new LoginActivityEvents.SocialAuthEvent.OpenRestoreVkidOldAuth(result.getEmailForRestore()));
    }

    private final void oneTimeCodeComposeResult(OneTimeCodeResult result) {
        if (result instanceof OneTimeCodeResult.Success) {
            OneTimeCodeResult.Success success = (OneTimeCodeResult.Success) result;
            String email = success.getEmail();
            Map<String, String> params = success.getParams();
            Intrinsics.checkNotNull(params, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>");
            Map<String, String> cookie = success.getCookie();
            Intrinsics.checkNotNull(cookie, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>");
            emitViewEvent(new LoginActivityEvents.OneTimeCodeEvents.Success(email, (HashMap) params, (HashMap) cookie));
            return;
        }
        if (result instanceof OneTimeCodeResult.SwitchToPassword) {
            emitViewEvent(new LoginActivityEvents.OneTimeCodeEvents.SwitchToPassword(((OneTimeCodeResult.SwitchToPassword) result).getEmail()));
        } else if (Intrinsics.areEqual(result, OneTimeCodeResult.OnClose.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.OneTimeCodeEvents.OnClose.INSTANCE);
        } else {
            if (!(result instanceof OneTimeCodeResult.Error)) {
                throw new NoWhenBranchMatchedException();
            }
            emitViewEvent(new LoginActivityEvents.OneTimeCodeEvents.Error(((OneTimeCodeResult.Error) result).getError()));
        }
    }

    private final void oneTimeCodeSuccessResult(AuthResult.OneTimeCodeSuccess result) {
        Bundle bundleBundleOf;
        String email = result.getEmail();
        String accessToken = result.getAccessToken();
        String refreshToken = result.getRefreshToken();
        String bindType = result.getBindType();
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        if (common == null || (bundleBundleOf = common.getBundle()) == null) {
            bundleBundleOf = BundleKt.bundleOf();
        }
        emitViewEvent(new LoginActivityEvents.OneTimeCodeSuccessAuth(email, bindType, accessToken, refreshToken, PoolConstants.DEFAULT, bundleBundleOf));
    }

    private final void outlookResult(AuthResult.Outlook result) {
        Bundle bundleBundleOf;
        if (!(result instanceof AuthResult.Outlook.AuthDoneOutlook)) {
            if (result instanceof AuthResult.Outlook.OtherOutlook) {
                OutlookResult result2 = ((AuthResult.Outlook.OtherOutlook) result).getResult();
                if (result2 instanceof OutlookResult.Error) {
                    OutlookResult.Error error = (OutlookResult.Error) result2;
                    emitViewEvent(new LoginActivityEvents.OutlookEvents.Error(error.getMessage(), error.getEmail(), error.getCode()));
                    return;
                }
                return;
            }
            return;
        }
        AuthResult.Outlook.AuthDoneOutlook authDoneOutlook = (AuthResult.Outlook.AuthDoneOutlook) result;
        String email = authDoneOutlook.getEmail();
        String accessToken = authDoneOutlook.getAccessToken();
        String refreshToken = authDoneOutlook.getRefreshToken();
        String outlookAccessToken = authDoneOutlook.getOutlookAccessToken();
        String outlookRefreshToken = authDoneOutlook.getOutlookRefreshToken();
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        if (common == null || (bundleBundleOf = common.getBundle()) == null) {
            bundleBundleOf = BundleKt.bundleOf();
        }
        emitViewEvent(new LoginActivityEvents.OutlookEvents.Success(email, accessToken, refreshToken, outlookAccessToken, outlookRefreshToken, "OUTLOOK_OAUTH", bundleBundleOf));
    }

    private final void passwordAuthResult(AuthResult.PasswordAuth result) {
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        Bundle bundle = common != null ? common.getBundle() : null;
        this.currentDataState = null;
        if (result instanceof AuthResult.PasswordAuth.PasswordAuthSuccess) {
            AuthResult.PasswordAuth.PasswordAuthSuccess passwordAuthSuccess = (AuthResult.PasswordAuth.PasswordAuthSuccess) result;
            emitViewEvent(new LoginActivityEvents.PasswordAuth(passwordAuthSuccess.getEmail(), passwordAuthSuccess.getPassword(), passwordAuthSuccess.getTsaCookie(), passwordAuthSuccess.getBindType(), passwordAuthSuccess.getAccessToken(), passwordAuthSuccess.getRefreshToken(), Authenticator.Type.getTypeByDomain(StringKt.getEmailDomain(passwordAuthSuccess.getEmail())).name(), passwordAuthSuccess.getIsAutologin(), bundle));
        }
    }

    private final void phoneAuthResult(AuthResult.CloudAuth.Success result) {
        Bundle bundleBundleOf;
        String login = result.getLogin();
        String token = result.getToken();
        String refreshToken = result.getRefreshToken();
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        if (common == null || (bundleBundleOf = common.getBundle()) == null) {
            bundleBundleOf = BundleKt.bundleOf();
        }
        emitViewEvent(new LoginActivityEvents.OneTimeCodeSuccessAuth(login, null, token, refreshToken, PoolConstants.DEFAULT, bundleBundleOf));
    }

    private final void restorePasswordComposeResult(RestorePasswordResult result) {
        debugLog("RestorePasswordResult = " + result);
        this.currentDataState = null;
        if (result instanceof RestorePasswordResult.Success) {
            RestorePasswordResult.Success success = (RestorePasswordResult.Success) result;
            emitViewEvent(new LoginActivityEvents.RestorePasswordComposeEvents.Success(success.getEmail(), success.getQueryParams()));
            return;
        }
        if (result instanceof RestorePasswordResult.Error) {
            emitViewEvent(new LoginActivityEvents.RestorePasswordComposeEvents.Error(((RestorePasswordResult.Error) result).getError()));
            return;
        }
        if (result instanceof RestorePasswordResult.Cancel) {
            emitViewEvent(new LoginActivityEvents.RestorePasswordComposeEvents.Closed(((RestorePasswordResult.Cancel) result).getAnalyticTag()));
        } else {
            if (result instanceof RestorePasswordResult.BackClick) {
                return;
            }
            if (!(result instanceof RestorePasswordResult.GoToRestoreVkid)) {
                throw new NoWhenBranchMatchedException();
            }
            emitViewEvent(new LoginActivityEvents.RestorePasswordComposeEvents.GoToRestoreVkid(((RestorePasswordResult.GoToRestoreVkid) result).getEmail()));
        }
    }

    private final void restoreWithoutPasswordResult(AuthResult.RestoreWithoutPasswordSuccess result) {
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        Bundle bundle = common != null ? common.getBundle() : null;
        this.currentDataState = null;
        emitViewEvent(new LoginActivityEvents.RestoreWithoutPasswordSuccess(result.getEmail(), result.getAccessToken(), result.getRefreshToken(), Authenticator.Type.getTypeByDomain(StringKt.getEmailDomain(result.getEmail())).name(), bundle));
    }

    private final void secondStepResult(SecondStepResult result) {
        debugLog("SecondStepResult = " + result);
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        Bundle bundle = common != null ? common.getBundle() : null;
        this.currentDataState = null;
        if (result instanceof SecondStepResult.Success) {
            SecondStepResult.Success success = (SecondStepResult.Success) result;
            emitViewEvent(new LoginActivityEvents.SecondStepEvents.Success(success.getLogin(), success.getAdditionalParams(), success.getTsaCookie(), success.getXmailLogin(), success.getXmailMigrationFrom(), bundle));
            Unit unit = Unit.INSTANCE;
            return;
        }
        if (result instanceof SecondStepResult.SwitchToPassword) {
            emitViewEvent(new LoginActivityEvents.SecondStepEvents.SwitchToPassword(((SecondStepResult.SwitchToPassword) result).getEmail()));
            Unit unit2 = Unit.INSTANCE;
            return;
        }
        if (result instanceof SecondStepResult.SwitchToRecovery) {
            new LoginActivityEvents.SecondStepEvents.SwitchToRecovery(((SecondStepResult.SwitchToRecovery) result).getEmail());
            return;
        }
        if (result instanceof SecondStepResult.Error) {
            emitViewEvent(new LoginActivityEvents.SecondStepEvents.Error(((SecondStepResult.Error) result).getError()));
            Unit unit3 = Unit.INSTANCE;
        } else {
            if (!(result instanceof SecondStepResult.BackClick)) {
                throw new NoWhenBranchMatchedException();
            }
            if (((SecondStepResult.BackClick) result).getIsXmailMigrationFlow()) {
                emitViewEvent(new LoginActivityEvents.SecondStepEvents.NeedEndActivity());
            }
            Unit unit4 = Unit.INSTANCE;
        }
    }

    private final void socialAuthResult(AuthResult.SocialAuth result) {
        if (result instanceof AuthResult.SocialAuth.AuthDone) {
            AuthResult.SocialAuth.AuthDone authDone = (AuthResult.SocialAuth.AuthDone) result;
            emitViewEvent(new LoginActivityEvents.SocialAuthEvent.Success(authDone.getEmail(), authDone.getAccessToken(), authDone.getRefreshToken(), authDone.getAuthType(), authDone.getBindType()));
        } else {
            if (!(result instanceof AuthResult.SocialAuth.ExternalResult)) {
                throw new NoWhenBranchMatchedException();
            }
            socialExternalResult(((AuthResult.SocialAuth.ExternalResult) result).getValue());
        }
    }

    private final void socialExternalResult(SocialAuthResult result) {
        if (result instanceof SocialAuthResult.Result) {
            emitViewEvent(new LoginActivityEvents.SocialAuthEvent.Result(((SocialAuthResult.Result) result).getValue()));
            return;
        }
        if (result instanceof SocialAuthResult.Error) {
            SocialAuthResult.Error error = (SocialAuthResult.Error) result;
            emitViewEvent(new LoginActivityEvents.SocialAuthEvent.Error(error.getErrorCode(), error.getErrorMsg(), error.getErrorType()));
            return;
        }
        if (result instanceof SocialAuthResult.Close) {
            emitViewEvent(new LoginActivityEvents.SocialAuthEvent.Close(((SocialAuthResult.Close) result).isAutoLogin()));
            return;
        }
        if (Intrinsics.areEqual(result, SocialAuthResult.StartDefaultRegistration.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.SocialAuthEvent.StartRegistration.INSTANCE);
            return;
        }
        if ((result instanceof SocialAuthResult.StartSocialRegistration) || (result instanceof SocialAuthResult.NewRestoreLogic) || (result instanceof SocialAuthResult.SendRegEvent)) {
            return;
        }
        if (Intrinsics.areEqual(result, SocialAuthResult.PasswordChanged.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.SocialAuthEvent.PasswordSuccessfullyChanged.INSTANCE);
            return;
        }
        if (Intrinsics.areEqual(result, SocialAuthResult.NotAuthorizedErrorDuringPasswordChange.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.SocialAuthEvent.NotAuthorizedErrorDuringPasswordChange.INSTANCE);
        } else {
            if (!(result instanceof SocialAuthResult.OpenMailRestore)) {
                throw new NoWhenBranchMatchedException();
            }
            SocialAuthResult.OpenMailRestore openMailRestore = (SocialAuthResult.OpenMailRestore) result;
            emitViewEvent(new LoginActivityEvents.SocialAuthEvent.OpenMailRestore(openMailRestore.getEmailForRestore(), openMailRestore.isRebind()));
        }
    }

    private final void ssoResult(SSOResult result) {
        debugLog("ssoResult = " + result);
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        Bundle bundle = common != null ? common.getBundle() : null;
        if (result instanceof SSOResult.Success) {
            SSOResult.Success success = (SSOResult.Success) result;
            emitViewEvent(new LoginActivityEvents.SSOEvents.Success(success.getLogin(), success.getAgToken(), bundle));
        } else if (result instanceof SSOResult.Error) {
            emitViewEvent(new LoginActivityEvents.SSOEvents.Error(((SSOResult.Error) result).getError()));
        } else if (Intrinsics.areEqual(result, SSOResult.BackClick.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.SSOEvents.BackClick.INSTANCE);
        } else {
            if (!(result instanceof SSOResult.SwitchToRecovery)) {
                throw new NoWhenBranchMatchedException();
            }
            emitViewEvent(LoginActivityEvents.SSOEvents.BackClick.INSTANCE);
        }
    }

    private final void vkBindInLoginResult(AuthResult.VkBindInLogin res) {
        if (Intrinsics.areEqual(res, AuthResult.VkBindInLogin.Back.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.VkBindInLogin.Back.INSTANCE);
        } else if (res instanceof AuthResult.VkBindInLogin.StartBinding) {
            emitViewEvent(new LoginActivityEvents.VkBindInLogin.StartBinding(((AuthResult.VkBindInLogin.StartBinding) res).getLogin()));
        } else {
            if (!(res instanceof AuthResult.VkBindInLogin.LoginWithAnotherWay)) {
                throw new NoWhenBranchMatchedException();
            }
            emitViewEvent(new LoginActivityEvents.VkBindInLogin.LoginWithAnotherWay(((AuthResult.VkBindInLogin.LoginWithAnotherWay) res).getLogin()));
        }
    }

    private final void vkPasswordResult(VkPasswordResult result) {
        debugLog("vkPassword = " + result);
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        Bundle bundle = common != null ? common.getBundle() : null;
        if (result instanceof VkPasswordResult.Success) {
            VkPasswordResult.Success success = (VkPasswordResult.Success) result;
            emitViewEvent(new LoginActivityEvents.VkPasswordEvents.Success(success.getLogin(), success.getAgToken(), bundle));
        } else if (result instanceof VkPasswordResult.Error) {
            emitViewEvent(new LoginActivityEvents.VkPasswordEvents.Error(((VkPasswordResult.Error) result).getError()));
        } else if (Intrinsics.areEqual(result, VkPasswordResult.BackClick.INSTANCE)) {
            emitViewEvent(LoginActivityEvents.VkPasswordEvents.BackClick.INSTANCE);
        } else if (!(result instanceof VkPasswordResult.ForgetPassword)) {
            throw new NoWhenBranchMatchedException();
        }
    }

    private final void yahooResult(AuthResult.Yahoo result) {
        Bundle bundleBundleOf;
        if (result instanceof AuthResult.Yahoo.AuthDone) {
            AuthResult.Yahoo.AuthDone authDone = (AuthResult.Yahoo.AuthDone) result;
            String email = authDone.getEmail();
            String accessToken = authDone.getAccessToken();
            String refreshToken = authDone.getRefreshToken();
            String yahooAccessToken = authDone.getYahooAccessToken();
            String yahooRefreshToken = authDone.getYahooRefreshToken();
            LoginActivityDataState loginActivityDataState = this.currentDataState;
            LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
            if (common == null || (bundleBundleOf = common.getBundle()) == null) {
                bundleBundleOf = BundleKt.bundleOf();
            }
            emitViewEvent(new LoginActivityEvents.YahooEvents.Success(email, accessToken, refreshToken, yahooAccessToken, yahooRefreshToken, "YAHOO_OAUTH", bundleBundleOf));
            return;
        }
        if (result instanceof AuthResult.Yahoo.Other) {
            YahooResult result2 = ((AuthResult.Yahoo.Other) result).getResult();
            if (result2 instanceof YahooResult.Error) {
                YahooResult.Error error = (YahooResult.Error) result2;
                emitViewEvent(new LoginActivityEvents.YahooEvents.Error(String.valueOf(error.getMessage()), error.getEmail(), error.getCode()));
            } else if (result2 instanceof YahooResult.NeedJapanYahooLogin) {
                String email2 = ((YahooResult.NeedJapanYahooLogin) result2).getEmail();
                if (email2 == null) {
                    email2 = "";
                }
                emitViewEvent(new LoginActivityEvents.YahooEvents.JapanAccount(email2));
            }
        }
    }

    private final void yandexResult(AuthResult.Yandex result) {
        Bundle bundleBundleOf;
        if (!(result instanceof AuthResult.Yandex.AuthDoneYandex)) {
            if (result instanceof AuthResult.Yandex.OtherYandex) {
                YandexResult result2 = ((AuthResult.Yandex.OtherYandex) result).getResult();
                if (result2 instanceof YandexResult.Error) {
                    YandexResult.Error error = (YandexResult.Error) result2;
                    emitViewEvent(new LoginActivityEvents.YandexEvents.Error(error.getMessage(), error.getEmail(), error.getCode()));
                    return;
                }
                return;
            }
            return;
        }
        AuthResult.Yandex.AuthDoneYandex authDoneYandex = (AuthResult.Yandex.AuthDoneYandex) result;
        String email = authDoneYandex.getEmail();
        String accessToken = authDoneYandex.getAccessToken();
        String refreshToken = authDoneYandex.getRefreshToken();
        String yandexAccessToken = authDoneYandex.getYandexAccessToken();
        String yandexRefreshToken = authDoneYandex.getYandexRefreshToken();
        LoginActivityDataState loginActivityDataState = this.currentDataState;
        LoginActivityDataState.Common common = loginActivityDataState instanceof LoginActivityDataState.Common ? (LoginActivityDataState.Common) loginActivityDataState : null;
        if (common == null || (bundleBundleOf = common.getBundle()) == null) {
            bundleBundleOf = BundleKt.bundleOf();
        }
        emitViewEvent(new LoginActivityEvents.YandexEvents.Success(email, accessToken, refreshToken, yandexAccessToken, yandexRefreshToken, "YANDEX_OAUTH", bundleBundleOf));
    }

    @NotNull
    public final MutableEventFlow<ViewEvent.Navigation> getNavEvent() {
        return this.navEvent;
    }

    @NotNull
    public final MutableEventFlow<ViewEvent> getViewEvent() {
        return this.viewEvent;
    }

    @NotNull
    public final MutableStateFlow<Boolean> isLoading() {
        return this.isLoading;
    }

    public final void navigateTo(@NotNull Screen<?> screen, @Nullable LoginActivityDataState dataState) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        ExtensionsKt.launch$default(this, this.viewModelDispatcher, null, new AnonymousClass1(dataState, this, screen, null), 2, null);
    }

    public final void onAuthorizeSdkResult(@NotNull AuthResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        if (result instanceof AuthResult.SecondStep) {
            secondStepResult(((AuthResult.SecondStep) result).getResult());
            return;
        }
        if (result instanceof AuthResult.Error.YandexOauthReq) {
            emitViewEvent(new LoginActivityEvents.Error.NeedYandexOauth(((AuthResult.Error.YandexOauthReq) result).getEmail()));
            return;
        }
        if (result instanceof AuthResult.Error.YahooOauthReq) {
            emitViewEvent(new LoginActivityEvents.Error.NeedYahooOauth(((AuthResult.Error.YahooOauthReq) result).getEmail()));
            return;
        }
        if (result instanceof AuthResult.Error.OutlookOauthReq) {
            emitViewEvent(new LoginActivityEvents.Error.NeedOutlookOauth(((AuthResult.Error.OutlookOauthReq) result).getEmail()));
            return;
        }
        if (result instanceof AuthResult.Error.OauthImapFailed) {
            emitViewEvent(new LoginActivityEvents.Error.OauthImapFailed(((AuthResult.Error.OauthImapFailed) result).getEmail()));
            return;
        }
        if (result instanceof AuthResult.Error.NeedDoRegistration) {
            AuthResult.Error.NeedDoRegistration needDoRegistration = (AuthResult.Error.NeedDoRegistration) result;
            emitViewEvent(new LoginActivityEvents.Error.NeedDoRegistration(needDoRegistration.getEmail(), needDoRegistration.getPassword(), needDoRegistration.getRegId(), needDoRegistration.isNeedCaptcha()));
            return;
        }
        if (result instanceof AuthResult.Error.ImapRedirect) {
            AuthResult.Error.ImapRedirect imapRedirect = (AuthResult.Error.ImapRedirect) result;
            emitViewEvent(new LoginActivityEvents.Error.ImapRedirect(imapRedirect.getEmail(), imapRedirect.getPassword(), imapRedirect.getSettings()));
            return;
        }
        if (result instanceof AuthResult.Error.GoogleOauthReq) {
            emitViewEvent(new LoginActivityEvents.Error.NeedGoogleOauth(((AuthResult.Error.GoogleOauthReq) result).getEmail()));
            return;
        }
        if (result instanceof AuthResult.Error.CommonError) {
            emitViewEvent(new LoginActivityEvents.Error.CommonError(((AuthResult.Error.CommonError) result).getError()));
            return;
        }
        if (result instanceof AuthResult.Login) {
            loginResults((AuthResult.Login) result);
            return;
        }
        if (result instanceof AuthResult.ImapLocalSuccess) {
            onImapLocalSuccess((AuthResult.ImapLocalSuccess) result);
            return;
        }
        if (result instanceof AuthResult.OAuthImapLocalSuccess) {
            onOAuthImapLocalSuccess((AuthResult.OAuthImapLocalSuccess) result);
            return;
        }
        if (result instanceof AuthResult.PasswordAuth) {
            passwordAuthResult((AuthResult.PasswordAuth) result);
            return;
        }
        if (result instanceof AuthResult.RestoreWithoutPasswordSuccess) {
            restoreWithoutPasswordResult((AuthResult.RestoreWithoutPasswordSuccess) result);
            return;
        }
        if (result instanceof AuthResult.OneTimeCodeSuccess) {
            oneTimeCodeSuccessResult((AuthResult.OneTimeCodeSuccess) result);
            return;
        }
        if (result instanceof AuthResult.CloudAuth.Success) {
            phoneAuthResult((AuthResult.CloudAuth.Success) result);
            return;
        }
        if (result instanceof AuthResult.Ludwig) {
            ludwigWebCaptchaComposeResult(((AuthResult.Ludwig) result).getResult());
            return;
        }
        if (result instanceof AuthResult.RestorePassword) {
            restorePasswordComposeResult(((AuthResult.RestorePassword) result).getResult());
            return;
        }
        if ((result instanceof AuthResult.RestoreVkId) || (result instanceof AuthResult.BeforeRecoveryVKID) || (result instanceof AuthResult.UnblockUser)) {
            return;
        }
        if (result instanceof AuthResult.OneTimeCode) {
            oneTimeCodeComposeResult(((AuthResult.OneTimeCode) result).getResult());
            return;
        }
        if (result instanceof AuthResult.Yahoo) {
            yahooResult((AuthResult.Yahoo) result);
            return;
        }
        if (result instanceof AuthResult.Yandex) {
            yandexResult((AuthResult.Yandex) result);
            return;
        }
        if ((result instanceof AuthResult.YandexHelp) || (result instanceof AuthResult.MrimDialog)) {
            return;
        }
        if (result instanceof AuthResult.GoogleNative) {
            googleResult((AuthResult.GoogleNative) result);
            return;
        }
        if (result instanceof AuthResult.ExternalAccountMigration) {
            externalAccMigrationResult(((AuthResult.ExternalAccountMigration) result).getResult());
            return;
        }
        if (result instanceof AuthResult.SSO) {
            ssoResult(((AuthResult.SSO) result).getResult());
            return;
        }
        if (result instanceof AuthResult.VkPassword) {
            vkPasswordResult(((AuthResult.VkPassword) result).getResult());
            return;
        }
        if (result instanceof AuthResult.CustomServer) {
            customServerResult(((AuthResult.CustomServer) result).getResult());
            return;
        }
        if (result instanceof AuthResult.Outlook) {
            outlookResult((AuthResult.Outlook) result);
            return;
        }
        if (result instanceof AuthResult.SocialAuth) {
            socialAuthResult((AuthResult.SocialAuth) result);
            return;
        }
        if (result instanceof AuthResult.Registration.AfterRegAuth) {
            afterRegAuthResult(((AuthResult.Registration.AfterRegAuth) result).getResult());
            return;
        }
        if (result instanceof AuthResult.VkBindInLogin) {
            vkBindInLoginResult((AuthResult.VkBindInLogin) result);
            return;
        }
        if (result instanceof AuthResult.DefaultLoginRequired) {
            onDefaultLoginRequired((AuthResult.DefaultLoginRequired) result);
            return;
        }
        if (result instanceof AuthResult.OpenRestoreVkidOldAuth) {
            onRestoreVkidInOldAuth((AuthResult.OpenRestoreVkidOldAuth) result);
            return;
        }
        debugLog("Auth result is not processed. Result = " + result);
    }

    public final void showCustomServerScreen(@NotNull EmailServiceResources.MailServiceResources serviceType, @NotNull String login, @NotNull String password, boolean isImapLocal, @NotNull Bundle bundle) {
        Intrinsics.checkNotNullParameter(serviceType, "serviceType");
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(password, "password");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        ExtensionsKt.launch$default(this, this.viewModelDispatcher, null, new C15851(login, password, serviceType, isImapLocal, bundle, null), 2, null);
    }
}
