package ru.mail.auth.loginactivity;

import android.os.Bundle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import dagger.hilt.android.lifecycle.HiltViewModel;
import javax.inject.Inject;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;
import ru.mail.auth.BaseAuthActivity;
import ru.mail.auth.EmailServiceResources;
import ru.mail.auth.restore.RestorePasswordFragment;
import ru.mail.auth.restore.RestorePasswordResult;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordViewModel;
import ru.mail.authorizesdk.presentation.accountmigration.ExternalAccMigrationFragment;
import ru.mail.authorizesdk.presentation.servicechooser.ServiceChooserFragmentSDK;
import ru.mail.authorizesdk.presentation.vkauth.VkMainLoginScreenFragmentSDK;
import ru.mail.authorizesdk.util.extensions.ScreenKt;
import ru.mail.authorizesdk.util.mvi.navigation.MutableResultFlow;
import ru.mail.authorizesdk.util.mvi.navigation.Screen;
import ru.mail.authorizesdk.util.mvi.navigation.ViewEvent;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.Result;
import ru.mail.ludvig_captcha.presentation.captcha.common.webview.capcha.WebCaptchaFragment;
import ru.mail.march.concurrent.ViewModelDispatcher;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.march.viewmodel.MutableEventFlow;
import ru.mail.util.log.AppLogger;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@HiltViewModel
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u0019\u001a\u00020\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001c2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0018\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$2\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020&H\u0002J\u0018\u0010'\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020(2\u0006\u0010)\u001a\u00020!H\u0002J\u0018\u0010*\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020+2\u0006\u0010)\u001a\u00020!H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityViewModel;", "Landroidx/lifecycle/ViewModel;", "viewModelDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "logger", "Lru/mail/util/log/Logger;", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lru/mail/util/log/Logger;)V", "log", "loadingEvent", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "getLoadingEvent", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "viewEvent", "Lru/mail/march/viewmodel/MutableEventFlow;", "Lru/mail/authorizesdk/util/mvi/navigation/ViewEvent;", "getViewEvent", "()Lru/mail/march/viewmodel/MutableEventFlow;", "navEvent", "Lru/mail/authorizesdk/util/mvi/navigation/MutableResultFlow;", "getNavEvent", "()Lru/mail/authorizesdk/util/mvi/navigation/MutableResultFlow;", "dataState", "Lru/mail/auth/loginactivity/LoginActivityDataState;", "navigateTo", "", "screen", "Lru/mail/authorizesdk/util/mvi/navigation/Screen;", "eventHandler", "requestKey", "", "result", "Landroid/os/Bundle;", "serviceChooserResult", "event", "Lru/mail/authorizesdk/presentation/servicechooser/ServiceChooserFragmentSDK$Result;", "vkMainLoginScreenFragmentResult", "Lru/mail/authorizesdk/presentation/vkauth/VkMainLoginScreenFragmentSDK$Result;", "ludwigWebCaptchaFragmentResult", "Lru/mail/ludvig_captcha/presentation/captcha/common/webview/capcha/Result;", "extra", "restorePasswordFragmentResult", "Lru/mail/auth/restore/RestorePasswordResult;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(message = "use LoginActivityAuthorizationViewModel")
@SourceDebugExtension({"SMAP\nLoginActivityViewModel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LoginActivityViewModel.kt\nru/mail/auth/loginactivity/LoginActivityViewModel\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,226:1\n1#2:227\n*E\n"})
public final class LoginActivityViewModel extends ViewModel {

    @Nullable
    private LoginActivityDataState dataState;

    @NotNull
    private final MutableStateFlow<Boolean> loadingEvent;

    @NotNull
    private Logger log;

    @NotNull
    private final MutableResultFlow navEvent;

    @NotNull
    private final MutableEventFlow<ViewEvent> viewEvent;

    @NotNull
    private final CoroutineDispatcher viewModelDispatcher;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes8.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[ExternalAccMigrationFragment.Result.values().length];
            try {
                iArr[ExternalAccMigrationFragment.Result.BACK_CLICKED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ExternalAccMigrationFragment.Result.ENTER_CLICKED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ExternalAccMigrationFragment.Result.REG_CLICKED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ExternalAccMigrationFragment.Result.MIGRATION_CLICKED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ServiceChooserFragmentSDK.Result.values().length];
            try {
                iArr2[ServiceChooserFragmentSDK.Result.SERVICE_CHOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ServiceChooserFragmentSDK.Result.START_REGISTRATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[RestorePasswordResult.values().length];
            try {
                iArr3[RestorePasswordResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr3[RestorePasswordResult.CANCEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr3[RestorePasswordResult.ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr3[RestorePasswordResult.GO_TO_RESTORE_VKID.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    @Inject
    public LoginActivityViewModel(@ViewModelDispatcher @NotNull CoroutineDispatcher viewModelDispatcher, @AppLogger @NotNull Logger logger) {
        Intrinsics.checkNotNullParameter(viewModelDispatcher, "viewModelDispatcher");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.viewModelDispatcher = viewModelDispatcher;
        this.log = logger.createLogger("LoginActivityViewModel");
        this.loadingEvent = StateFlowKt.MutableStateFlow(Boolean.FALSE);
        this.viewEvent = ExtensionsKt.sideEffect(ViewModelKt.getViewModelScope(this), viewModelDispatcher, this.log, new Flow[0]);
        this.navEvent = new MutableResultFlow(new LoginActivityViewModel$navEvent$1(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void eventHandler(String requestKey, Bundle result) {
        int i10;
        ServiceChooserFragmentSDK.Companion companion = ServiceChooserFragmentSDK.INSTANCE;
        if (Intrinsics.areEqual(requestKey, companion.simpleName())) {
            ServiceChooserFragmentSDK.Result result2 = companion.getResult(result);
            if (result2 == null) {
                return;
            }
            serviceChooserResult(result2, result);
            return;
        }
        VkMainLoginScreenFragmentSDK.Companion companion2 = VkMainLoginScreenFragmentSDK.INSTANCE;
        if (Intrinsics.areEqual(requestKey, companion2.simpleName())) {
            VkMainLoginScreenFragmentSDK.Result event = companion2.getEvent(result);
            if (event == null) {
                return;
            }
            vkMainLoginScreenFragmentResult(event);
            return;
        }
        WebCaptchaFragment.Companion companion3 = WebCaptchaFragment.INSTANCE;
        if (Intrinsics.areEqual(requestKey, companion3.getResultKey())) {
            Result result3 = companion3.getResult(result);
            if (result3 == null) {
                return;
            }
            ludwigWebCaptchaFragmentResult(result3, result);
            return;
        }
        RestorePasswordFragment.Companion companion4 = RestorePasswordFragment.INSTANCE;
        if (Intrinsics.areEqual(requestKey, companion4.getResultKey())) {
            RestorePasswordResult result4 = companion4.getResult(result);
            if (result4 == null) {
                return;
            }
            restorePasswordFragmentResult(result4, result);
            return;
        }
        ExternalAccMigrationFragment.Companion companion5 = ExternalAccMigrationFragment.INSTANCE;
        if (Intrinsics.areEqual(requestKey, companion5.getResultKey())) {
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, LoginActivityEvents.ShowActionBar.INSTANCE);
            ExternalAccMigrationFragment.Result result5 = companion5.getResult(result);
            if (result5 == null || (i10 = WhenMappings.$EnumSwitchMapping$0[result5.ordinal()]) == 1) {
                return;
            }
            if (i10 == 2) {
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.StartLoginScreenWithXmail(companion5.getEmail(result)));
            } else if (i10 == 3) {
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, LoginActivityEvents.StartRegistrationNewExternalAuth.INSTANCE);
            } else {
                if (i10 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, LoginActivityEvents.StartXmailMigrationFromLogin.INSTANCE);
            }
        }
    }

    private final void ludwigWebCaptchaFragmentResult(Result event, Bundle extra) {
        if (event == Result.SUCCESS) {
            LoginActivityDataState loginActivityDataState = this.dataState;
            if (!(loginActivityDataState instanceof LoginActivityDataState.LudwigCaptcha)) {
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.LudwigEvents.LudwigCaptchaError("No Ludwig token!"));
                return;
            } else {
                LoginActivityDataState.LudwigCaptcha ludwigCaptcha = (LoginActivityDataState.LudwigCaptcha) loginActivityDataState;
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.LudwigEvents.LudwigCaptchaSuccess(ludwigCaptcha.getLudwigToken(), ludwigCaptcha.getLogin(), ludwigCaptcha.getPassword(), ludwigCaptcha.getType(), ludwigCaptcha.getBundle()));
            }
        }
        if (event == Result.ERROR) {
            String errorMessage = WebCaptchaFragment.INSTANCE.getErrorMessage(extra);
            if (errorMessage == null) {
                errorMessage = "";
            }
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.LudwigEvents.LudwigCaptchaError(errorMessage));
        }
    }

    public static /* synthetic */ void navigateTo$default(LoginActivityViewModel loginActivityViewModel, Screen screen, LoginActivityDataState loginActivityDataState, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            loginActivityDataState = null;
        }
        loginActivityViewModel.navigateTo(screen, loginActivityDataState);
    }

    private final void restorePasswordFragmentResult(RestorePasswordResult event, Bundle extra) {
        int i10 = WhenMappings.$EnumSwitchMapping$2[event.ordinal()];
        if (i10 == 1) {
            String string = extra.getString("authAccount");
            Bundle bundle = extra.getBundle(BaseAuthActivity.EXTRA_BUNDLE);
            boolean z10 = extra.getBoolean(RestorePasswordViewModel.IS_REBIND, false);
            if (string == null || bundle == null) {
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.RestorePasswordEvents.RestorePasswordError(R.string.authenticator_error));
                return;
            } else {
                ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.RestorePasswordEvents.RestorePasswordSuccess(string, bundle, z10));
                return;
            }
        }
        if (i10 == 2) {
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.RestorePasswordEvents.RestorePasswordClosed(RestorePasswordFragment.INSTANCE.getCancelTag(extra)));
        } else if (i10 == 3) {
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.RestorePasswordEvents.RestorePasswordError(RestorePasswordFragment.INSTANCE.getError(extra)));
        } else {
            if (i10 != 4) {
                throw new NoWhenBranchMatchedException();
            }
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.RestorePasswordEvents.GoToRestoreVkid(RestorePasswordFragment.INSTANCE.getEmail(extra)));
        }
    }

    private final void serviceChooserResult(ServiceChooserFragmentSDK.Result event, Bundle result) {
        int i10 = WhenMappings.$EnumSwitchMapping$1[event.ordinal()];
        if (i10 == 1) {
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, new LoginActivityEvents.StartLoginScreen(EmailServiceResources.MailServiceResources.valueOf(ServiceChooserFragmentSDK.INSTANCE.getChooseService(result))));
        } else {
            if (i10 != 2) {
                throw new NoWhenBranchMatchedException();
            }
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, LoginActivityEvents.StartRegistration.INSTANCE);
        }
    }

    private final void vkMainLoginScreenFragmentResult(VkMainLoginScreenFragmentSDK.Result event) {
        if (event == VkMainLoginScreenFragmentSDK.Result.LOGIN_WITH_EXTERNAL_ACCOUNT) {
            ru.mail.authorizesdk.util.extensions.ViewModelKt.emit(this, this.viewEvent, this.viewModelDispatcher, LoginActivityEvents.StartVKAnotherLogin.INSTANCE);
        }
    }

    @NotNull
    public final MutableStateFlow<Boolean> getLoadingEvent() {
        return this.loadingEvent;
    }

    @NotNull
    public final MutableResultFlow getNavEvent() {
        return this.navEvent;
    }

    @NotNull
    public final MutableEventFlow<ViewEvent> getViewEvent() {
        return this.viewEvent;
    }

    public final void navigateTo(@NotNull Screen<?> screen, @Nullable LoginActivityDataState dataState) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        if (dataState != null) {
            this.dataState = dataState;
        }
        this.navEvent.setValue((ViewEvent) ScreenKt.toNavigation(screen));
    }
}
