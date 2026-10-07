package com.vk.superapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.vk.accountmanager.di.AccountManagerComponentImpl;
import com.vk.accountmanager.domain.interactor.AccountManagerInteractorImpl;
import com.vk.auth.captcha.api.di.CaptchaComponent;
import com.vk.auth.captcha.impl.di.CaptchaComponentImpl;
import com.vk.auth.internal.AuthLibBridge;
import com.vk.auth.main.UsersStore;
import com.vk.auth.main.VkClientAuthLib;
import com.vk.auth.oauth.component.di.OAuthUiComponent;
import com.vk.auth.oauth.component.impl.di.OAuthUiComponentImpl;
import com.vk.auth.related.profile.di.RelatedProfileComponentImpl;
import com.vk.auth.smartflow.api.SmartflowComponent;
import com.vk.auth.smartflow.impl.SmartflowComponentImpl;
import com.vk.auth.smartflow.impl.mail.MailSmartflowComponentImpl;
import com.vk.auth.smartflow.mail.MailSmartflowComponent;
import com.vk.auth.suspicious_auth.SuspiciousAuthComponent;
import com.vk.auth.suspicious_auth.SuspiciousAuthComponentImpl;
import com.vk.autologin.di.VkAutoLoginComponent;
import com.vk.autologin.di.VkAutoLoginComponentImpl;
import com.vk.confirmaccount.api.di.ConfirmAccountComponent;
import com.vk.confirmaccount.impl.di.ConfirmAccountComponentImpl;
import com.vk.di.InitKt;
import com.vk.di.component.app.AppContextDiComponent;
import com.vk.di.component.factory.DiComponentFactory;
import com.vk.di.component.factory.DiScopedComponentFactory;
import com.vk.di.context.DiContextKt;
import com.vk.di.context.configuration.DiContextConfiguration;
import com.vk.di.scope.SingletonScopeKey;
import com.vk.emailactualization.impl.di.EmailActualizationComponentImpl;
import com.vk.emailforwarding.api.di.EmailForwardingComponent;
import com.vk.emailforwarding.di.EmailForwardingComponentFactory;
import com.vk.mail.auth.api.di.MailAuthComponent;
import com.vk.mail.auth.contract.di.MailAuthInternalComponent;
import com.vk.mail.auth.impl.di.MailAuthComponentFactory;
import com.vk.mail.auth.impl.di.internal.component.MailAuthInternalComponentFactory;
import com.vk.method.selector.api.MethodSelectorComponent;
import com.vk.method.selector.impl.MethodSelectorComponentImpl;
import com.vk.oauth.di.OAuthComponentImpl;
import com.vk.odnoklassniki.heads.di.OkHeadsComponent;
import com.vk.odnoklassniki.heads.di.OkHeadsComponentImpl;
import com.vk.odnoklassniki.registration.OkRegistrationComponent;
import com.vk.odnoklassniki.registration.di.OkRegistrationComponentImpl;
import com.vk.passkey.api.di.PasskeyComponent;
import com.vk.passkey.di.PasskeyComponentImpl;
import com.vk.phoneactualization.impl.di.PhoneActualizationComponentImpl;
import com.vk.qr.auth.di.QrAuthComponent;
import com.vk.qr.auth.di.QrAuthComponentImpl;
import com.vk.qr.rustore.api.VkRustoreQrComponent;
import com.vk.qr.rustore.impl.VkRustoreQrComponentImpl;
import com.vk.registration.funnels.di.SakAnalyticsComponentImpl;
import com.vk.silentauth.client.CachedSilentAuthInfoProvider;
import com.vk.silentauthbylogin.di.SilentAuthByLoginComponent;
import com.vk.silentauthbylogin.di.SilentAuthByLoginComponentImpl;
import com.vk.stat.di.StatComponent;
import com.vk.superapp.advertisement.api.di.fullscreen_ad.FullscreenAdFactoryComponent;
import com.vk.superapp.advertisement.api.di.nativead.NativeAdFactoryComponent;
import com.vk.superapp.advertisement.api.di.sticky_banner_ad.StickyBannerAdFactoriesComponent;
import com.vk.superapp.advertisement.di.fullscreen_ad.FullscreenAdFactoryComponentImpl;
import com.vk.superapp.advertisement.di.nativead.NativeAdFactoryComponentImpl;
import com.vk.superapp.advertisement.di.sticky_banner_ad.StickyBannerAdFactoriesComponentImpl;
import com.vk.superapp.advertisement.formats.api.AdvertisementOptionalFormatsComponent;
import com.vk.superapp.analytics.di.SakAnalyticsComponent;
import com.vk.superapp.annotations.InternalVkSdkApi;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.superapp.browser_events.di.BrowserEventsComponent;
import com.vk.superapp.catalog.api.di.AppsCatalogComponent;
import com.vk.superapp.catalog.impl.di.AppsCatalogComponentImpl;
import com.vk.superapp.core.SuperappConfig;
import com.vk.superapp.deps.SDKSyncStoragesAction;
import com.vk.superapp.jsbridges.JsBridgesDiFactoriesKt;
import com.vk.superapp.multiaccount.api.MultiAccountComponent;
import com.vk.superapp.multiaccount.api.RelatedProfileComponent;
import com.vk.superapp.multiaccount.impl.MultiAccountComponentImpl;
import com.vk.superapp.navigation.api.di.VkAnalyticsComponent;
import com.vk.superapp.navigation.impl.di.VkAnalyticsComponentImpl;
import com.vk.superapp.qr.web2app.QrWebToAppComponent;
import com.vk.superapp.sessionmanagment.api.domain.di.SessionManagementComponent;
import com.vk.superapp.sessionmanagment.api.domain.repository.SessionReadOnlyRepository;
import com.vk.superapp.sessionmanagment.api.domain.repository.SessionWriteOnlyRepository;
import com.vk.superapp.sessionmanagment.impl.di.SessionManagementComponentImpl;
import com.vk.superapp.statinteractor.api.di.StatInteractorComponent;
import com.vk.superapp.statinteractor.impl.di.StatInteractorComponentImpl;
import com.vk.superapp.verification.account.di.VerificationAccountComponent;
import com.vk.superapp.verification.account.di.VerificationAccountComponentImpl;
import com.vk.superapp.vkhealth.permissions.api.di.VkHealthPermissionsComponent;
import com.vk.superapp.vkhealth.permissions.impl.di.VkHealthPermissionsComponentImpl;
import com.vk.superapp.vksteps.di.VkStepsComponent;
import com.vk.superapp.vksteps.di.VkStepsComponentImpl;
import com.vk.superapp.vkworkout.di.VkWorkoutComponent;
import com.vk.superapp.vkworkout.di.VkWorkoutComponentImpl;
import com.vk.superapp.vkworkout.di.VkWorkoutWidgetBridgeComponent;
import com.vk.trustedhash.di.TrustedHashComponent;
import com.vk.trustedhash.di.TrustedHashComponentImpl;
import com.vk.whitelabelauth.di.WhiteLabelAuthComponent;
import com.vk.whitelabelauth.di.WhiteLabelAuthComponentImpl;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/vk/superapp/SDKDependencyGraphInitialization;", "", "Landroid/app/Application;", "application", "<init>", "(Landroid/app/Application;)V", "", "init", "()V", "superappkit_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SuppressLint({"NewApi"})
@VisibleForTesting(otherwise = 3)
@InternalVkSdkApi
@SourceDebugExtension({"SMAP\nSDKDependencyGraphInitialization.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SDKDependencyGraphInitialization.kt\ncom/vk/superapp/SDKDependencyGraphInitialization\n+ 2 SDKDependencyGraphHelper.kt\ncom/vk/superapp/SDKDependencyGraphHelperKt\n+ 3 DiContextConfiguration.kt\ncom/vk/di/context/configuration/DiContextConfigurationKt\n*L\n1#1,347:1\n90#2:348\n93#2:350\n83#2:351\n84#2:353\n47#2:359\n50#2:361\n47#2:362\n50#2:364\n47#2:365\n50#2:367\n83#2:368\n84#2:370\n83#2:371\n84#2:373\n83#2:374\n84#2:376\n83#2:377\n84#2:379\n47#2:380\n50#2:382\n83#2:383\n84#2:385\n47#2:386\n50#2:388\n83#2:389\n84#2:391\n83#2:392\n84#2:394\n47#2:395\n50#2:397\n83#2:398\n84#2:400\n83#2:404\n84#2:406\n90#2:407\n93#2:409\n90#2:410\n93#2:412\n90#2:413\n93#2:415\n56#2:417\n59#2:419\n56#2:420\n59#2:422\n90#2:423\n93#2:425\n90#2:426\n93#2:428\n90#2:429\n93#2:431\n47#2:432\n50#2:434\n90#2:435\n93#2:437\n90#2:438\n93#2:440\n90#2:441\n93#2:443\n90#2:444\n93#2:446\n90#2:447\n93#2:449\n83#2:452\n84#2:454\n77#3:349\n73#3:352\n77#3:354\n77#3:355\n73#3:356\n77#3:357\n73#3:358\n113#3:360\n113#3:363\n113#3:366\n73#3:369\n73#3:372\n73#3:375\n73#3:378\n113#3:381\n73#3:384\n113#3:387\n73#3:390\n73#3:393\n113#3:396\n73#3:399\n77#3:401\n77#3:402\n77#3:403\n73#3:405\n77#3:408\n77#3:411\n77#3:414\n77#3:416\n108#3:418\n108#3:421\n77#3:424\n77#3:427\n77#3:430\n113#3:433\n77#3:436\n77#3:439\n77#3:442\n77#3:445\n77#3:448\n73#3:450\n73#3:451\n73#3:453\n*S KotlinDebug\n*F\n+ 1 SDKDependencyGraphInitialization.kt\ncom/vk/superapp/SDKDependencyGraphInitialization\n*L\n302#1:348\n302#1:350\n310#1:351\n310#1:353\n156#1:359\n156#1:361\n160#1:362\n160#1:364\n164#1:365\n164#1:367\n168#1:368\n168#1:370\n172#1:371\n172#1:373\n176#1:374\n176#1:376\n180#1:377\n180#1:379\n184#1:380\n184#1:382\n188#1:383\n188#1:385\n192#1:386\n192#1:388\n196#1:389\n196#1:391\n200#1:392\n200#1:394\n204#1:395\n204#1:397\n211#1:398\n211#1:400\n221#1:404\n221#1:406\n225#1:407\n225#1:409\n228#1:410\n228#1:412\n231#1:413\n231#1:415\n236#1:417\n236#1:419\n239#1:420\n239#1:422\n242#1:423\n242#1:425\n245#1:426\n245#1:428\n249#1:429\n249#1:431\n256#1:432\n256#1:434\n261#1:435\n261#1:437\n266#1:438\n266#1:440\n273#1:441\n273#1:443\n280#1:444\n280#1:446\n285#1:447\n285#1:449\n295#1:452\n295#1:454\n302#1:349\n310#1:352\n328#1:354\n344#1:355\n149#1:356\n150#1:357\n154#1:358\n156#1:360\n160#1:363\n164#1:366\n168#1:369\n172#1:372\n176#1:375\n180#1:378\n184#1:381\n188#1:384\n192#1:387\n196#1:390\n200#1:393\n204#1:396\n211#1:399\n215#1:401\n217#1:402\n219#1:403\n221#1:405\n225#1:408\n228#1:411\n231#1:414\n234#1:416\n236#1:418\n239#1:421\n242#1:424\n245#1:427\n249#1:430\n256#1:433\n261#1:436\n266#1:439\n273#1:442\n280#1:445\n285#1:448\n292#1:450\n293#1:451\n295#1:453\n*E\n"})
public final class SDKDependencyGraphInitialization {

    @NotNull
    private final Application kdskvkvmoca;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    static final class kdskvkvmoca implements Function0<CachedSilentAuthInfoProvider> {
        public static final kdskvkvmoca kdskvkvmoca = new kdskvkvmoca();

        kdskvkvmoca() {
        }

        @Override // kotlin.jvm.functions.Function0
        public final CachedSilentAuthInfoProvider invoke() {
            return VkClientAuthLib.INSTANCE.getVkSilentAuthInfoProvider();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    static final class kdskvkvmocb implements Function0<UsersStore> {
        public static final kdskvkvmocb kdskvkvmoca = new kdskvkvmocb();

        kdskvkvmocb() {
        }

        @Override // kotlin.jvm.functions.Function0
        public final UsersStore invoke() {
            return AuthLibBridge.INSTANCE.getUsersStore();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes5.dex */
    static final class kdskvkvmocc implements Function0<ExecutorService> {
        public static final kdskvkvmocc kdskvkvmoca = new kdskvkvmocc();

        kdskvkvmocc() {
        }

        @Override // kotlin.jvm.functions.Function0
        public final ExecutorService invoke() {
            return SuperappConfig.ExecutorProvider.DefaultImpls.newSingleThreadExecutor$default(SuperappApiCore.INSTANCE.getExecutorProvider(), "vk-multiacc-thread", 5, 0L, 4, null);
        }
    }

    public SDKDependencyGraphInitialization(@NotNull Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.kdskvkvmoca = application;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmoca(AccountManagerComponentImpl.Factory factory) {
        return factory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmocb() {
        return QrWebToAppComponent.INSTANCE.getSTUB_FACTORY();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmocc() {
        return new StatComponent.StubFactory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmocd() {
        return VkWorkoutWidgetBridgeComponent.INSTANCE.getSTUB_FACTORY();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmoce() {
        return new OAuthComponentImpl.Factory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmocf() {
        return AdvertisementOptionalFormatsComponent.INSTANCE.getSTUB_DI_FACTORY();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmocg() {
        return new EmailActualizationComponentImpl.Factory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmoch() {
        return new PhoneActualizationComponentImpl.Factory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExecutorService kdskvkvmoci() {
        return SuperappConfig.ExecutorProvider.DefaultImpls.newSingleThreadExecutor$default(SuperappApiCore.INSTANCE.getExecutorProvider(), "sak_session_repository_thread", 10, 0L, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ExecutorService kdskvkvmocj() {
        return SuperappApiCore.INSTANCE.getExecutorProvider().getComputationExecutor();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean kdskvkvmock() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean kdskvkvmocl() {
        return true;
    }

    public final void init() {
        InitKt.initDi$default(null, new Function1() { // from class: com.vk.superapp.m
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SDKDependencyGraphInitialization.kdskvkvmoca(this.f53707a, (DiContextConfiguration) obj);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmoca(SessionManagementComponentImpl.Factory factory) {
        return factory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Executor kdskvkvmocb(Lazy lazy) {
        return (Executor) lazy.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SessionWriteOnlyRepository kdskvkvmocc(SDKDependencyGraphInitialization sDKDependencyGraphInitialization) {
        return ((SessionManagementComponent) DiContextKt.getDiContext(sDKDependencyGraphInitialization.kdskvkvmoca).obtainComponent(Reflection.getOrCreateKotlinClass(SessionManagementComponent.class))).getWriteOnlyRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final StatInteractorComponent kdskvkvmocd(SDKDependencyGraphInitialization sDKDependencyGraphInitialization) {
        return (StatInteractorComponent) DiContextKt.getDiContext(sDKDependencyGraphInitialization.kdskvkvmoca).obtainComponent(Reflection.getOrCreateKotlinClass(StatInteractorComponent.class));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit kdskvkvmoca(final SDKDependencyGraphInitialization sDKDependencyGraphInitialization, final DiContextConfiguration initDi) {
        Intrinsics.checkNotNullParameter(initDi, "$this$initDi");
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.di.component.app.AppContextDiComponent", new Function0() { // from class: com.vk.superapp.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoca(this.f51745a);
            }
        });
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.statinteractor.api.di.StatInteractorComponent", new Function0() { // from class: com.vk.superapp.l
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoca();
            }
        });
        sDKDependencyGraphInitialization.kdskvkvmoca(initDi);
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.auth.oauth.di.OAuthComponent", new Function0() { // from class: com.vk.superapp.n
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoce();
            }
        });
        final DiComponentFactory<VerificationAccountComponent> stub_factory = VerificationAccountComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactory("com.vk.superapp.verification.account.di.VerificationAccountComponent", new Function0<DiComponentFactory<VerificationAccountComponent>>(initDi, stub_factory) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$1
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<VerificationAccountComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VerificationAccountComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<ConfirmAccountComponent> stub_factory2 = ConfirmAccountComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactory("com.vk.confirmaccount.api.di.ConfirmAccountComponent", new Function0<DiComponentFactory<ConfirmAccountComponent>>(initDi, stub_factory2) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$2
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory2;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<ConfirmAccountComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new ConfirmAccountComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<CaptchaComponent> stub_factory3 = CaptchaComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactory("com.vk.auth.captcha.api.di.CaptchaComponent", new Function0<DiComponentFactory<CaptchaComponent>>(initDi, stub_factory3) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$3
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory3;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<CaptchaComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new CaptchaComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<VkHealthPermissionsComponent, SingletonScopeKey> stub_factory4 = VkHealthPermissionsComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.superapp.vkhealth.permissions.api.di.VkHealthPermissionsComponent", new Function0<DiScopedComponentFactory<VkHealthPermissionsComponent, SingletonScopeKey>>(initDi, stub_factory4) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$1
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory4;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<VkHealthPermissionsComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VkHealthPermissionsComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<VkWorkoutComponent, SingletonScopeKey> stub_factory5 = VkWorkoutComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.superapp.vkworkout.di.VkWorkoutComponent", new Function0<DiScopedComponentFactory<VkWorkoutComponent, SingletonScopeKey>>(initDi, stub_factory5) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$2
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory5;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<VkWorkoutComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VkWorkoutComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<VkStepsComponent, SingletonScopeKey> stub_factory6 = VkStepsComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.superapp.vksteps.di.VkStepsComponent", new Function0<DiScopedComponentFactory<VkStepsComponent, SingletonScopeKey>>(initDi, stub_factory6) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$3
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory6;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<VkStepsComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VkStepsComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<OAuthUiComponent, SingletonScopeKey> stub_factory7 = OAuthUiComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.auth.oauth.component.di.OAuthUiComponent", new Function0<DiScopedComponentFactory<OAuthUiComponent, SingletonScopeKey>>(initDi, stub_factory7) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$4
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory7;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<OAuthUiComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new OAuthUiComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<AppsCatalogComponent> stub_di_factory = AppsCatalogComponent.INSTANCE.getSTUB_DI_FACTORY();
        initDi.registerComponentFactory("com.vk.superapp.catalog.api.di.AppsCatalogComponent", new Function0<DiComponentFactory<AppsCatalogComponent>>(initDi, stub_di_factory) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$4
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_di_factory;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<AppsCatalogComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new AppsCatalogComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<BrowserEventsComponent, SingletonScopeKey> stub_factory8 = BrowserEventsComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.superapp.browser_events.di.BrowserEventsComponent", new Function0<DiScopedComponentFactory<BrowserEventsComponent, SingletonScopeKey>>(initDi, stub_factory8) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$5
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory8;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<BrowserEventsComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new BrowserEventsComponent.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<OkHeadsComponent> stub_factory9 = OkHeadsComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactory("com.vk.odnoklassniki.heads.di.OkHeadsComponent", new Function0<DiComponentFactory<OkHeadsComponent>>(initDi, stub_factory9) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$5
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory9;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<OkHeadsComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new OkHeadsComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<VkAnalyticsComponent, SingletonScopeKey> stub_factory10 = VkAnalyticsComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.superapp.navigation.api.di.VkAnalyticsComponent", new Function0<DiScopedComponentFactory<VkAnalyticsComponent, SingletonScopeKey>>(initDi, stub_factory10) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$6
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory10;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<VkAnalyticsComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VkAnalyticsComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<WhiteLabelAuthComponent, SingletonScopeKey> stub_factory11 = WhiteLabelAuthComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.whitelabelauth.di.WhiteLabelAuthComponent", new Function0<DiScopedComponentFactory<WhiteLabelAuthComponent, SingletonScopeKey>>(initDi, stub_factory11) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$7
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory11;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<WhiteLabelAuthComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new WhiteLabelAuthComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<OkRegistrationComponent> stub_factory12 = OkRegistrationComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactory("com.vk.odnoklassniki.registration.OkRegistrationComponent", new Function0<DiComponentFactory<OkRegistrationComponent>>(initDi, stub_factory12) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$6
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory12;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<OkRegistrationComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new OkRegistrationComponentImpl.Factory(LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) SDKDependencyGraphInitialization.kdskvkvmoca.kdskvkvmoca)));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<EmailForwardingComponent, SingletonScopeKey> stub_factory13 = EmailForwardingComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.emailforwarding.api.di.EmailForwardingComponent", new Function0<DiScopedComponentFactory<EmailForwardingComponent, SingletonScopeKey>>(initDi, stub_factory13) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$8
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory13;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<EmailForwardingComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new EmailForwardingComponentFactory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.qr.web2app.QrWebToAppComponent", new Function0() { // from class: com.vk.superapp.o
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocb();
            }
        });
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.stat.di.StatComponent", new Function0() { // from class: com.vk.superapp.p
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocc();
            }
        });
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.vkworkout.di.VkWorkoutWidgetBridgeComponent", new Function0() { // from class: com.vk.superapp.q
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocd();
            }
        });
        final DiScopedComponentFactory<QrAuthComponent, SingletonScopeKey> stub_factory14 = QrAuthComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.qr.auth.di.QrAuthComponent", new Function0<DiScopedComponentFactory<QrAuthComponent, SingletonScopeKey>>(initDi, stub_factory14) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$9
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory14;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<QrAuthComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new QrAuthComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<FullscreenAdFactoryComponent, SingletonScopeKey> stub_di_factory2 = FullscreenAdFactoryComponent.INSTANCE.getSTUB_DI_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.advertisement.api.di.fullscreen_ad.FullscreenAdFactoryComponent", new Function0<DiScopedComponentFactory<FullscreenAdFactoryComponent, SingletonScopeKey>>(initDi, stub_di_factory2) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$1
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_di_factory2;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<FullscreenAdFactoryComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new FullscreenAdFactoryComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<NativeAdFactoryComponent, SingletonScopeKey> stub_factory15 = NativeAdFactoryComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.advertisement.api.di.nativead.NativeAdFactoryComponent", new Function0<DiScopedComponentFactory<NativeAdFactoryComponent, SingletonScopeKey>>(initDi, stub_factory15) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$2
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory15;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<NativeAdFactoryComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new NativeAdFactoryComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<StickyBannerAdFactoriesComponent, SingletonScopeKey> stub_di_factory3 = StickyBannerAdFactoriesComponent.INSTANCE.getSTUB_DI_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.advertisement.api.di.sticky_banner_ad.StickyBannerAdFactoriesComponent", new Function0<DiScopedComponentFactory<StickyBannerAdFactoriesComponent, SingletonScopeKey>>(initDi, stub_di_factory3) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$3
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_di_factory3;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<StickyBannerAdFactoriesComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new StickyBannerAdFactoriesComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.advertisement.formats.api.AdvertisementOptionalFormatsComponent", new Function0() { // from class: com.vk.superapp.s
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocf();
            }
        });
        final DiComponentFactory<MethodSelectorComponent> stub_factory16 = MethodSelectorComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactoryIfNoneExists("com.vk.method.selector.api.MethodSelectorComponent", new Function0<DiComponentFactory<MethodSelectorComponent>>(initDi, stub_factory16) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactoryIfNoneExist$1
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory16;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<MethodSelectorComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new MethodSelectorComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<SmartflowComponent> stub_factory17 = SmartflowComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactoryIfNoneExists("com.vk.auth.smartflow.api.SmartflowComponent", new Function0<DiComponentFactory<SmartflowComponent>>(initDi, stub_factory17) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactoryIfNoneExist$2
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory17;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<SmartflowComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new SmartflowComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<MailSmartflowComponent, SingletonScopeKey> stub_factory18 = MailSmartflowComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.auth.smartflow.mail.MailSmartflowComponent", new Function0<DiScopedComponentFactory<MailSmartflowComponent, SingletonScopeKey>>(initDi, stub_factory18) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$4
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory18;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<MailSmartflowComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new MailSmartflowComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<TrustedHashComponent, SingletonScopeKey> stub_factory19 = TrustedHashComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.trustedhash.di.TrustedHashComponent", new Function0<DiScopedComponentFactory<TrustedHashComponent, SingletonScopeKey>>(initDi, stub_factory19) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$5
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory19;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<TrustedHashComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new TrustedHashComponentImpl.Factory(null, 1, null));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<SilentAuthByLoginComponent, SingletonScopeKey> stub_factory20 = SilentAuthByLoginComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.silentauthbylogin.di.SilentAuthByLoginComponent", new Function0<DiScopedComponentFactory<SilentAuthByLoginComponent, SingletonScopeKey>>(initDi, stub_factory20) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$6
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory20;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<SilentAuthByLoginComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new SilentAuthByLoginComponentImpl.Factory(LazyKt.lazy(SDKDependencyGraphInitialization.kdskvkvmocb.kdskvkvmoca)));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiComponentFactory<VkRustoreQrComponent> stub_factory21 = VkRustoreQrComponent.INSTANCE.getSTUB_FACTORY();
        initDi.registerComponentFactory("com.vk.qr.rustore.api.VkRustoreQrComponent", new Function0<DiComponentFactory<VkRustoreQrComponent>>(initDi, stub_factory21) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalComponentFactory$7
            final /* synthetic */ DiComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory21;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiComponentFactory<VkRustoreQrComponent> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VkRustoreQrComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiComponentFactory diComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diComponentFactory;
                }
                return (DiComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<PasskeyComponent, SingletonScopeKey> stub_factory22 = PasskeyComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.passkey.api.di.PasskeyComponent", new Function0<DiScopedComponentFactory<PasskeyComponent, SingletonScopeKey>>(initDi, stub_factory22, sDKDependencyGraphInitialization) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$7
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;
            final /* synthetic */ SDKDependencyGraphInitialization kdskvkvmocb;

            {
                this.kdskvkvmoca = stub_factory22;
                this.kdskvkvmocb = sDKDependencyGraphInitialization;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<PasskeyComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new PasskeyComponentImpl.Factory(this.kdskvkvmocb.kdskvkvmoca));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<SakAnalyticsComponent, SingletonScopeKey> stub_factory23 = SakAnalyticsComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.analytics.di.SakAnalyticsComponent", new Function0<DiScopedComponentFactory<SakAnalyticsComponent, SingletonScopeKey>>(initDi, stub_factory23) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$8
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory23;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<SakAnalyticsComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new SakAnalyticsComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<MailAuthComponent, SingletonScopeKey> stub_factory24 = MailAuthComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.mail.auth.api.di.MailAuthComponent", new Function0<DiScopedComponentFactory<MailAuthComponent, SingletonScopeKey>>(initDi, stub_factory24) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$9
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory24;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<MailAuthComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new MailAuthComponentFactory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<MailAuthInternalComponent, SingletonScopeKey> stub_factory25 = MailAuthInternalComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.mail.auth.contract.di.MailAuthInternalComponent", new Function0<DiScopedComponentFactory<MailAuthInternalComponent, SingletonScopeKey>>(initDi, stub_factory25) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$10
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory25;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<MailAuthInternalComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new MailAuthInternalComponentFactory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<SuspiciousAuthComponent, SingletonScopeKey> stub_factory26 = SuspiciousAuthComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.auth.suspicious_auth.SuspiciousAuthComponent", new Function0<DiScopedComponentFactory<SuspiciousAuthComponent, SingletonScopeKey>>(initDi, stub_factory26) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$11
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory26;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<SuspiciousAuthComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new SuspiciousAuthComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        JsBridgesDiFactoriesKt.jsBridgesRegisterComponentFactories(initDi);
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.emailactualization.api.di.EmailActualizationComponent", new Function0() { // from class: com.vk.superapp.t
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocg();
            }
        });
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.phoneactualization.api.di.PhoneActualizationComponent", new Function0() { // from class: com.vk.superapp.u
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoch();
            }
        });
        final DiScopedComponentFactory<VkAutoLoginComponent, SingletonScopeKey> stub_factory27 = VkAutoLoginComponent.INSTANCE.getSTUB_FACTORY();
        initDi.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.autologin.di.VkAutoLoginComponent", new Function0<DiScopedComponentFactory<VkAutoLoginComponent, SingletonScopeKey>>(initDi, stub_factory27) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$init$lambda$39$$inlined$registerOptionalSingletonComponentFactory$10
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory27;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<VkAutoLoginComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new VkAutoLoginComponentImpl.Factory());
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SessionReadOnlyRepository kdskvkvmocb(SDKDependencyGraphInitialization sDKDependencyGraphInitialization) {
        return ((SessionManagementComponent) DiContextKt.getDiContext(sDKDependencyGraphInitialization.kdskvkvmoca).obtainComponent(Reflection.getOrCreateKotlinClass(SessionManagementComponent.class))).getReadOnlyRepository();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmoca(SDKDependencyGraphInitialization sDKDependencyGraphInitialization) {
        return new AppContextDiComponent.Factory(sDKDependencyGraphInitialization.kdskvkvmoca);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DiScopedComponentFactory kdskvkvmoca() {
        return new StatInteractorComponentImpl.Factory();
    }

    private final void kdskvkvmoca(final DiContextConfiguration diContextConfiguration) {
        final DiScopedComponentFactory<MultiAccountComponent, SingletonScopeKey> stub_factory = MultiAccountComponent.INSTANCE.getSTUB_FACTORY();
        diContextConfiguration.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.multiaccount.api.MultiAccountComponent", new Function0<DiScopedComponentFactory<MultiAccountComponent, SingletonScopeKey>>(diContextConfiguration, stub_factory) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$registerMultiAccountComponents$$inlined$registerOptionalSingletonComponentFactoryIfNoneExist$1
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;

            {
                this.kdskvkvmoca = stub_factory;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<MultiAccountComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new MultiAccountComponentImpl.Factory(SDKDependencyGraphInitialization.kdskvkvmocc.kdskvkvmoca));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final DiScopedComponentFactory<RelatedProfileComponent, SingletonScopeKey> stub_factory2 = RelatedProfileComponent.INSTANCE.getSTUB_FACTORY();
        diContextConfiguration.forScope("com.vk.di.scope.SingletonScope").registerComponentFactory("com.vk.superapp.multiaccount.api.RelatedProfileComponent", new Function0<DiScopedComponentFactory<RelatedProfileComponent, SingletonScopeKey>>(diContextConfiguration, stub_factory2, this) { // from class: com.vk.superapp.SDKDependencyGraphInitialization$registerMultiAccountComponents$$inlined$registerOptionalSingletonComponentFactory$1
            final /* synthetic */ DiScopedComponentFactory kdskvkvmoca;
            final /* synthetic */ SDKDependencyGraphInitialization kdskvkvmocb;

            {
                this.kdskvkvmoca = stub_factory2;
                this.kdskvkvmocb = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DiScopedComponentFactory<RelatedProfileComponent, SingletonScopeKey> invoke() {
                Object objM13123constructorimpl;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(new RelatedProfileComponentImpl.Factory(this.kdskvkvmocb.kdskvkvmoca));
                } catch (Throwable th2) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                }
                DiScopedComponentFactory diScopedComponentFactory = this.kdskvkvmoca;
                if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                    objM13123constructorimpl = diScopedComponentFactory;
                }
                return (DiScopedComponentFactory) objM13123constructorimpl;
            }
        });
        final Lazy lazy = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.v
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoci();
            }
        });
        Context applicationContext = this.kdskvkvmoca.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        final SessionManagementComponentImpl.Factory factory = new SessionManagementComponentImpl.Factory(applicationContext, new Function0() { // from class: com.vk.superapp.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoca(lazy);
            }
        }, new Function0() { // from class: com.vk.superapp.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocb(lazy);
            }
        }, new Function0() { // from class: com.vk.superapp.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocj();
            }
        }, new Function0() { // from class: com.vk.superapp.f
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SDKDependencyGraphInitialization.kdskvkvmock());
            }
        }, new Function0() { // from class: com.vk.superapp.g
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(SDKDependencyGraphInitialization.kdskvkvmocl());
            }
        });
        diContextConfiguration.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.superapp.sessionmanagment.api.domain.di.SessionManagementComponent", new Function0() { // from class: com.vk.superapp.h
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoca(factory);
            }
        });
        final AccountManagerComponentImpl.Factory factory2 = new AccountManagerComponentImpl.Factory(this.kdskvkvmoca, new SDKSyncStoragesAction(LazyKt.lazy(new Function0() { // from class: com.vk.superapp.i
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocb(this.f53671a);
            }
        }), LazyKt.lazy(new Function0() { // from class: com.vk.superapp.j
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocc(this.f53674a);
            }
        })), new AccountManagerInteractorImpl(), LazyKt.lazy(new Function0() { // from class: com.vk.superapp.k
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmocd(this.f53676a);
            }
        }));
        diContextConfiguration.forScope("com.vk.di.scope.SingletonScope").registerComponentFactoryIfNoneExists("com.vk.accountmanager.di.AccountManagerComponent", new Function0() { // from class: com.vk.superapp.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SDKDependencyGraphInitialization.kdskvkvmoca(factory2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Executor kdskvkvmoca(Lazy lazy) {
        return (Executor) lazy.getValue();
    }
}
