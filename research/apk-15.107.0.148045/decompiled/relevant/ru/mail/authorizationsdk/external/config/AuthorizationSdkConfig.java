package ru.mail.authorizationsdk.external.config;

import androidx.compose.runtime.internal.StabilityInferred;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.external.config.common.CommonConfig;
import ru.mail.authorizationsdk.external.config.flavor.FlavorConfig;
import ru.mail.authorizationsdk.external.config.login.LoginConfig;
import ru.mail.authorizationsdk.feature.captcha.config.LudwigConfig;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.model.VkLoginScreenConfig;
import ru.mail.authorizationsdk.feature.secondfactor.config.SecondStepConfig;
import ru.mail.authorizationsdk.feature.socialauth.config.SocialAuthConfig;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreConfig;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b:\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u0012\u0006\u0010\u001e\u001a\u00020\u001f\u0012\u0006\u0010 \u001a\u00020!\u0012\u0006\u0010\"\u001a\u00020#\u0012\u0006\u0010$\u001a\u00020%¢\u0006\u0004\b&\u0010'J\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0005HÆ\u0003J\t\u0010N\u001a\u00020\u0007HÆ\u0003J\t\u0010O\u001a\u00020\tHÆ\u0003J\t\u0010P\u001a\u00020\u000bHÆ\u0003J\t\u0010Q\u001a\u00020\rHÆ\u0003J\t\u0010R\u001a\u00020\u000fHÆ\u0003J\t\u0010S\u001a\u00020\u0011HÆ\u0003J\t\u0010T\u001a\u00020\u0013HÆ\u0003J\t\u0010U\u001a\u00020\u0015HÆ\u0003J\t\u0010V\u001a\u00020\u0017HÆ\u0003J\t\u0010W\u001a\u00020\u0019HÆ\u0003J\t\u0010X\u001a\u00020\u001bHÆ\u0003J\t\u0010Y\u001a\u00020\u001dHÆ\u0003J\t\u0010Z\u001a\u00020\u001fHÆ\u0003J\t\u0010[\u001a\u00020!HÆ\u0003J\t\u0010\\\u001a\u00020#HÆ\u0003J\t\u0010]\u001a\u00020%HÆ\u0003J½\u0001\u0010^\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%HÆ\u0001J\u0013\u0010_\u001a\u00020`2\b\u0010a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010b\u001a\u00020cHÖ\u0001J\t\u0010d\u001a\u00020eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b>\u0010?R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b@\u0010AR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\bF\u0010GR\u0011\u0010\"\u001a\u00020#¢\u0006\b\n\u0000\u001a\u0004\bH\u0010IR\u0011\u0010$\u001a\u00020%¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010K¨\u0006f"}, d2 = {"Lru/mail/authorizationsdk/external/config/AuthorizationSdkConfig;", "", "commonConfig", "Lru/mail/authorizationsdk/external/config/common/CommonConfig;", "loginConfig", "Lru/mail/authorizationsdk/external/config/login/LoginConfig;", "vkLoginConfig", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/model/VkLoginScreenConfig;", "imapConfig", "Lru/mail/authorizationsdk/external/config/ImapConfig;", "sessionRestoreConfig", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreConfig;", "ludwigCaptchaConfig", "Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;", "yandexHelpConfig", "Lru/mail/authorizationsdk/external/config/YandexHelpConfiguration;", "secondStepConfig", "Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;", "pushAuthInfoConfig", "Lru/mail/authorizationsdk/external/config/PushAuthInfoConfig;", "mailUrls", "Lru/mail/authorizationsdk/external/config/MailUrls;", "mrimConfig", "Lru/mail/authorizationsdk/external/config/MrimConfig;", "socialAuthConfig", "Lru/mail/authorizationsdk/feature/socialauth/config/SocialAuthConfig;", "browserConfig", "Lru/mail/authorizationsdk/external/config/BrowserConfig;", "buildConfigurationVariables", "Lru/mail/authorizationsdk/external/config/BuildConfigurationVariables;", "regConfig", "Lru/mail/authorizationsdk/external/config/RegConfig;", "mailAuthConfig", "Lru/mail/authorizationsdk/external/config/MailAuthConfig;", "vkBindInLogin", "Lru/mail/authorizationsdk/external/config/VkBindInLoginConfig;", "flavorConfig", "Lru/mail/authorizationsdk/external/config/flavor/FlavorConfig;", "<init>", "(Lru/mail/authorizationsdk/external/config/common/CommonConfig;Lru/mail/authorizationsdk/external/config/login/LoginConfig;Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/model/VkLoginScreenConfig;Lru/mail/authorizationsdk/external/config/ImapConfig;Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreConfig;Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;Lru/mail/authorizationsdk/external/config/YandexHelpConfiguration;Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;Lru/mail/authorizationsdk/external/config/PushAuthInfoConfig;Lru/mail/authorizationsdk/external/config/MailUrls;Lru/mail/authorizationsdk/external/config/MrimConfig;Lru/mail/authorizationsdk/feature/socialauth/config/SocialAuthConfig;Lru/mail/authorizationsdk/external/config/BrowserConfig;Lru/mail/authorizationsdk/external/config/BuildConfigurationVariables;Lru/mail/authorizationsdk/external/config/RegConfig;Lru/mail/authorizationsdk/external/config/MailAuthConfig;Lru/mail/authorizationsdk/external/config/VkBindInLoginConfig;Lru/mail/authorizationsdk/external/config/flavor/FlavorConfig;)V", "getCommonConfig", "()Lru/mail/authorizationsdk/external/config/common/CommonConfig;", "getLoginConfig", "()Lru/mail/authorizationsdk/external/config/login/LoginConfig;", "getVkLoginConfig", "()Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/model/VkLoginScreenConfig;", "getImapConfig", "()Lru/mail/authorizationsdk/external/config/ImapConfig;", "getSessionRestoreConfig", "()Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreConfig;", "getLudwigCaptchaConfig", "()Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;", "getYandexHelpConfig", "()Lru/mail/authorizationsdk/external/config/YandexHelpConfiguration;", "getSecondStepConfig", "()Lru/mail/authorizationsdk/feature/secondfactor/config/SecondStepConfig;", "getPushAuthInfoConfig", "()Lru/mail/authorizationsdk/external/config/PushAuthInfoConfig;", "getMailUrls", "()Lru/mail/authorizationsdk/external/config/MailUrls;", "getMrimConfig", "()Lru/mail/authorizationsdk/external/config/MrimConfig;", "getSocialAuthConfig", "()Lru/mail/authorizationsdk/feature/socialauth/config/SocialAuthConfig;", "getBrowserConfig", "()Lru/mail/authorizationsdk/external/config/BrowserConfig;", "getBuildConfigurationVariables", "()Lru/mail/authorizationsdk/external/config/BuildConfigurationVariables;", "getRegConfig", "()Lru/mail/authorizationsdk/external/config/RegConfig;", "getMailAuthConfig", "()Lru/mail/authorizationsdk/external/config/MailAuthConfig;", "getVkBindInLogin", "()Lru/mail/authorizationsdk/external/config/VkBindInLoginConfig;", "getFlavorConfig", "()Lru/mail/authorizationsdk/external/config/flavor/FlavorConfig;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "copy", "equals", "", "other", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AuthorizationSdkConfig {
    public static final int $stable = 8;

    @NotNull
    private final BrowserConfig browserConfig;

    @NotNull
    private final BuildConfigurationVariables buildConfigurationVariables;

    @NotNull
    private final CommonConfig commonConfig;

    @NotNull
    private final FlavorConfig flavorConfig;

    @NotNull
    private final ImapConfig imapConfig;

    @NotNull
    private final LoginConfig loginConfig;

    @NotNull
    private final LudwigConfig ludwigCaptchaConfig;

    @NotNull
    private final MailAuthConfig mailAuthConfig;

    @NotNull
    private final MailUrls mailUrls;

    @NotNull
    private final MrimConfig mrimConfig;

    @NotNull
    private final PushAuthInfoConfig pushAuthInfoConfig;

    @NotNull
    private final RegConfig regConfig;

    @NotNull
    private final SecondStepConfig secondStepConfig;

    @NotNull
    private final SessionRestoreConfig sessionRestoreConfig;

    @NotNull
    private final SocialAuthConfig socialAuthConfig;

    @NotNull
    private final VkBindInLoginConfig vkBindInLogin;

    @NotNull
    private final VkLoginScreenConfig vkLoginConfig;

    @NotNull
    private final YandexHelpConfiguration yandexHelpConfig;

    public AuthorizationSdkConfig(@NotNull CommonConfig commonConfig, @NotNull LoginConfig loginConfig, @NotNull VkLoginScreenConfig vkLoginConfig, @NotNull ImapConfig imapConfig, @NotNull SessionRestoreConfig sessionRestoreConfig, @NotNull LudwigConfig ludwigCaptchaConfig, @NotNull YandexHelpConfiguration yandexHelpConfig, @NotNull SecondStepConfig secondStepConfig, @NotNull PushAuthInfoConfig pushAuthInfoConfig, @NotNull MailUrls mailUrls, @NotNull MrimConfig mrimConfig, @NotNull SocialAuthConfig socialAuthConfig, @NotNull BrowserConfig browserConfig, @NotNull BuildConfigurationVariables buildConfigurationVariables, @NotNull RegConfig regConfig, @NotNull MailAuthConfig mailAuthConfig, @NotNull VkBindInLoginConfig vkBindInLogin, @NotNull FlavorConfig flavorConfig) {
        Intrinsics.checkNotNullParameter(commonConfig, "commonConfig");
        Intrinsics.checkNotNullParameter(loginConfig, "loginConfig");
        Intrinsics.checkNotNullParameter(vkLoginConfig, "vkLoginConfig");
        Intrinsics.checkNotNullParameter(imapConfig, "imapConfig");
        Intrinsics.checkNotNullParameter(sessionRestoreConfig, "sessionRestoreConfig");
        Intrinsics.checkNotNullParameter(ludwigCaptchaConfig, "ludwigCaptchaConfig");
        Intrinsics.checkNotNullParameter(yandexHelpConfig, "yandexHelpConfig");
        Intrinsics.checkNotNullParameter(secondStepConfig, "secondStepConfig");
        Intrinsics.checkNotNullParameter(pushAuthInfoConfig, "pushAuthInfoConfig");
        Intrinsics.checkNotNullParameter(mailUrls, "mailUrls");
        Intrinsics.checkNotNullParameter(mrimConfig, "mrimConfig");
        Intrinsics.checkNotNullParameter(socialAuthConfig, "socialAuthConfig");
        Intrinsics.checkNotNullParameter(browserConfig, "browserConfig");
        Intrinsics.checkNotNullParameter(buildConfigurationVariables, "buildConfigurationVariables");
        Intrinsics.checkNotNullParameter(regConfig, "regConfig");
        Intrinsics.checkNotNullParameter(mailAuthConfig, "mailAuthConfig");
        Intrinsics.checkNotNullParameter(vkBindInLogin, "vkBindInLogin");
        Intrinsics.checkNotNullParameter(flavorConfig, "flavorConfig");
        this.commonConfig = commonConfig;
        this.loginConfig = loginConfig;
        this.vkLoginConfig = vkLoginConfig;
        this.imapConfig = imapConfig;
        this.sessionRestoreConfig = sessionRestoreConfig;
        this.ludwigCaptchaConfig = ludwigCaptchaConfig;
        this.yandexHelpConfig = yandexHelpConfig;
        this.secondStepConfig = secondStepConfig;
        this.pushAuthInfoConfig = pushAuthInfoConfig;
        this.mailUrls = mailUrls;
        this.mrimConfig = mrimConfig;
        this.socialAuthConfig = socialAuthConfig;
        this.browserConfig = browserConfig;
        this.buildConfigurationVariables = buildConfigurationVariables;
        this.regConfig = regConfig;
        this.mailAuthConfig = mailAuthConfig;
        this.vkBindInLogin = vkBindInLogin;
        this.flavorConfig = flavorConfig;
    }

    public static /* synthetic */ AuthorizationSdkConfig copy$default(AuthorizationSdkConfig authorizationSdkConfig, CommonConfig commonConfig, LoginConfig loginConfig, VkLoginScreenConfig vkLoginScreenConfig, ImapConfig imapConfig, SessionRestoreConfig sessionRestoreConfig, LudwigConfig ludwigConfig, YandexHelpConfiguration yandexHelpConfiguration, SecondStepConfig secondStepConfig, PushAuthInfoConfig pushAuthInfoConfig, MailUrls mailUrls, MrimConfig mrimConfig, SocialAuthConfig socialAuthConfig, BrowserConfig browserConfig, BuildConfigurationVariables buildConfigurationVariables, RegConfig regConfig, MailAuthConfig mailAuthConfig, VkBindInLoginConfig vkBindInLoginConfig, FlavorConfig flavorConfig, int i10, Object obj) {
        FlavorConfig flavorConfig2;
        VkBindInLoginConfig vkBindInLoginConfig2;
        CommonConfig commonConfig2 = (i10 & 1) != 0 ? authorizationSdkConfig.commonConfig : commonConfig;
        LoginConfig loginConfig2 = (i10 & 2) != 0 ? authorizationSdkConfig.loginConfig : loginConfig;
        VkLoginScreenConfig vkLoginScreenConfig2 = (i10 & 4) != 0 ? authorizationSdkConfig.vkLoginConfig : vkLoginScreenConfig;
        ImapConfig imapConfig2 = (i10 & 8) != 0 ? authorizationSdkConfig.imapConfig : imapConfig;
        SessionRestoreConfig sessionRestoreConfig2 = (i10 & 16) != 0 ? authorizationSdkConfig.sessionRestoreConfig : sessionRestoreConfig;
        LudwigConfig ludwigConfig2 = (i10 & 32) != 0 ? authorizationSdkConfig.ludwigCaptchaConfig : ludwigConfig;
        YandexHelpConfiguration yandexHelpConfiguration2 = (i10 & 64) != 0 ? authorizationSdkConfig.yandexHelpConfig : yandexHelpConfiguration;
        SecondStepConfig secondStepConfig2 = (i10 & 128) != 0 ? authorizationSdkConfig.secondStepConfig : secondStepConfig;
        PushAuthInfoConfig pushAuthInfoConfig2 = (i10 & 256) != 0 ? authorizationSdkConfig.pushAuthInfoConfig : pushAuthInfoConfig;
        MailUrls mailUrls2 = (i10 & 512) != 0 ? authorizationSdkConfig.mailUrls : mailUrls;
        MrimConfig mrimConfig2 = (i10 & 1024) != 0 ? authorizationSdkConfig.mrimConfig : mrimConfig;
        SocialAuthConfig socialAuthConfig2 = (i10 & 2048) != 0 ? authorizationSdkConfig.socialAuthConfig : socialAuthConfig;
        BrowserConfig browserConfig2 = (i10 & 4096) != 0 ? authorizationSdkConfig.browserConfig : browserConfig;
        BuildConfigurationVariables buildConfigurationVariables2 = (i10 & 8192) != 0 ? authorizationSdkConfig.buildConfigurationVariables : buildConfigurationVariables;
        CommonConfig commonConfig3 = commonConfig2;
        RegConfig regConfig2 = (i10 & 16384) != 0 ? authorizationSdkConfig.regConfig : regConfig;
        MailAuthConfig mailAuthConfig2 = (i10 & 32768) != 0 ? authorizationSdkConfig.mailAuthConfig : mailAuthConfig;
        VkBindInLoginConfig vkBindInLoginConfig3 = (i10 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? authorizationSdkConfig.vkBindInLogin : vkBindInLoginConfig;
        if ((i10 & 131072) != 0) {
            vkBindInLoginConfig2 = vkBindInLoginConfig3;
            flavorConfig2 = authorizationSdkConfig.flavorConfig;
        } else {
            flavorConfig2 = flavorConfig;
            vkBindInLoginConfig2 = vkBindInLoginConfig3;
        }
        return authorizationSdkConfig.copy(commonConfig3, loginConfig2, vkLoginScreenConfig2, imapConfig2, sessionRestoreConfig2, ludwigConfig2, yandexHelpConfiguration2, secondStepConfig2, pushAuthInfoConfig2, mailUrls2, mrimConfig2, socialAuthConfig2, browserConfig2, buildConfigurationVariables2, regConfig2, mailAuthConfig2, vkBindInLoginConfig2, flavorConfig2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final CommonConfig getCommonConfig() {
        return this.commonConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final MailUrls getMailUrls() {
        return this.mailUrls;
    }

    @NotNull
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final MrimConfig getMrimConfig() {
        return this.mrimConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final SocialAuthConfig getSocialAuthConfig() {
        return this.socialAuthConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component13, reason: from getter */
    public final BrowserConfig getBrowserConfig() {
        return this.browserConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component14, reason: from getter */
    public final BuildConfigurationVariables getBuildConfigurationVariables() {
        return this.buildConfigurationVariables;
    }

    @NotNull
    /* JADX INFO: renamed from: component15, reason: from getter */
    public final RegConfig getRegConfig() {
        return this.regConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component16, reason: from getter */
    public final MailAuthConfig getMailAuthConfig() {
        return this.mailAuthConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component17, reason: from getter */
    public final VkBindInLoginConfig getVkBindInLogin() {
        return this.vkBindInLogin;
    }

    @NotNull
    /* JADX INFO: renamed from: component18, reason: from getter */
    public final FlavorConfig getFlavorConfig() {
        return this.flavorConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final LoginConfig getLoginConfig() {
        return this.loginConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VkLoginScreenConfig getVkLoginConfig() {
        return this.vkLoginConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ImapConfig getImapConfig() {
        return this.imapConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final SessionRestoreConfig getSessionRestoreConfig() {
        return this.sessionRestoreConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final LudwigConfig getLudwigCaptchaConfig() {
        return this.ludwigCaptchaConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final YandexHelpConfiguration getYandexHelpConfig() {
        return this.yandexHelpConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final SecondStepConfig getSecondStepConfig() {
        return this.secondStepConfig;
    }

    @NotNull
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final PushAuthInfoConfig getPushAuthInfoConfig() {
        return this.pushAuthInfoConfig;
    }

    @NotNull
    public final AuthorizationSdkConfig copy(@NotNull CommonConfig commonConfig, @NotNull LoginConfig loginConfig, @NotNull VkLoginScreenConfig vkLoginConfig, @NotNull ImapConfig imapConfig, @NotNull SessionRestoreConfig sessionRestoreConfig, @NotNull LudwigConfig ludwigCaptchaConfig, @NotNull YandexHelpConfiguration yandexHelpConfig, @NotNull SecondStepConfig secondStepConfig, @NotNull PushAuthInfoConfig pushAuthInfoConfig, @NotNull MailUrls mailUrls, @NotNull MrimConfig mrimConfig, @NotNull SocialAuthConfig socialAuthConfig, @NotNull BrowserConfig browserConfig, @NotNull BuildConfigurationVariables buildConfigurationVariables, @NotNull RegConfig regConfig, @NotNull MailAuthConfig mailAuthConfig, @NotNull VkBindInLoginConfig vkBindInLogin, @NotNull FlavorConfig flavorConfig) {
        Intrinsics.checkNotNullParameter(commonConfig, "commonConfig");
        Intrinsics.checkNotNullParameter(loginConfig, "loginConfig");
        Intrinsics.checkNotNullParameter(vkLoginConfig, "vkLoginConfig");
        Intrinsics.checkNotNullParameter(imapConfig, "imapConfig");
        Intrinsics.checkNotNullParameter(sessionRestoreConfig, "sessionRestoreConfig");
        Intrinsics.checkNotNullParameter(ludwigCaptchaConfig, "ludwigCaptchaConfig");
        Intrinsics.checkNotNullParameter(yandexHelpConfig, "yandexHelpConfig");
        Intrinsics.checkNotNullParameter(secondStepConfig, "secondStepConfig");
        Intrinsics.checkNotNullParameter(pushAuthInfoConfig, "pushAuthInfoConfig");
        Intrinsics.checkNotNullParameter(mailUrls, "mailUrls");
        Intrinsics.checkNotNullParameter(mrimConfig, "mrimConfig");
        Intrinsics.checkNotNullParameter(socialAuthConfig, "socialAuthConfig");
        Intrinsics.checkNotNullParameter(browserConfig, "browserConfig");
        Intrinsics.checkNotNullParameter(buildConfigurationVariables, "buildConfigurationVariables");
        Intrinsics.checkNotNullParameter(regConfig, "regConfig");
        Intrinsics.checkNotNullParameter(mailAuthConfig, "mailAuthConfig");
        Intrinsics.checkNotNullParameter(vkBindInLogin, "vkBindInLogin");
        Intrinsics.checkNotNullParameter(flavorConfig, "flavorConfig");
        return new AuthorizationSdkConfig(commonConfig, loginConfig, vkLoginConfig, imapConfig, sessionRestoreConfig, ludwigCaptchaConfig, yandexHelpConfig, secondStepConfig, pushAuthInfoConfig, mailUrls, mrimConfig, socialAuthConfig, browserConfig, buildConfigurationVariables, regConfig, mailAuthConfig, vkBindInLogin, flavorConfig);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthorizationSdkConfig)) {
            return false;
        }
        AuthorizationSdkConfig authorizationSdkConfig = (AuthorizationSdkConfig) other;
        return Intrinsics.areEqual(this.commonConfig, authorizationSdkConfig.commonConfig) && Intrinsics.areEqual(this.loginConfig, authorizationSdkConfig.loginConfig) && Intrinsics.areEqual(this.vkLoginConfig, authorizationSdkConfig.vkLoginConfig) && Intrinsics.areEqual(this.imapConfig, authorizationSdkConfig.imapConfig) && Intrinsics.areEqual(this.sessionRestoreConfig, authorizationSdkConfig.sessionRestoreConfig) && Intrinsics.areEqual(this.ludwigCaptchaConfig, authorizationSdkConfig.ludwigCaptchaConfig) && Intrinsics.areEqual(this.yandexHelpConfig, authorizationSdkConfig.yandexHelpConfig) && Intrinsics.areEqual(this.secondStepConfig, authorizationSdkConfig.secondStepConfig) && Intrinsics.areEqual(this.pushAuthInfoConfig, authorizationSdkConfig.pushAuthInfoConfig) && Intrinsics.areEqual(this.mailUrls, authorizationSdkConfig.mailUrls) && Intrinsics.areEqual(this.mrimConfig, authorizationSdkConfig.mrimConfig) && Intrinsics.areEqual(this.socialAuthConfig, authorizationSdkConfig.socialAuthConfig) && Intrinsics.areEqual(this.browserConfig, authorizationSdkConfig.browserConfig) && Intrinsics.areEqual(this.buildConfigurationVariables, authorizationSdkConfig.buildConfigurationVariables) && Intrinsics.areEqual(this.regConfig, authorizationSdkConfig.regConfig) && Intrinsics.areEqual(this.mailAuthConfig, authorizationSdkConfig.mailAuthConfig) && Intrinsics.areEqual(this.vkBindInLogin, authorizationSdkConfig.vkBindInLogin) && Intrinsics.areEqual(this.flavorConfig, authorizationSdkConfig.flavorConfig);
    }

    @NotNull
    public final BrowserConfig getBrowserConfig() {
        return this.browserConfig;
    }

    @NotNull
    public final BuildConfigurationVariables getBuildConfigurationVariables() {
        return this.buildConfigurationVariables;
    }

    @NotNull
    public final CommonConfig getCommonConfig() {
        return this.commonConfig;
    }

    @NotNull
    public final FlavorConfig getFlavorConfig() {
        return this.flavorConfig;
    }

    @NotNull
    public final ImapConfig getImapConfig() {
        return this.imapConfig;
    }

    @NotNull
    public final LoginConfig getLoginConfig() {
        return this.loginConfig;
    }

    @NotNull
    public final LudwigConfig getLudwigCaptchaConfig() {
        return this.ludwigCaptchaConfig;
    }

    @NotNull
    public final MailAuthConfig getMailAuthConfig() {
        return this.mailAuthConfig;
    }

    @NotNull
    public final MailUrls getMailUrls() {
        return this.mailUrls;
    }

    @NotNull
    public final MrimConfig getMrimConfig() {
        return this.mrimConfig;
    }

    @NotNull
    public final PushAuthInfoConfig getPushAuthInfoConfig() {
        return this.pushAuthInfoConfig;
    }

    @NotNull
    public final RegConfig getRegConfig() {
        return this.regConfig;
    }

    @NotNull
    public final SecondStepConfig getSecondStepConfig() {
        return this.secondStepConfig;
    }

    @NotNull
    public final SessionRestoreConfig getSessionRestoreConfig() {
        return this.sessionRestoreConfig;
    }

    @NotNull
    public final SocialAuthConfig getSocialAuthConfig() {
        return this.socialAuthConfig;
    }

    @NotNull
    public final VkBindInLoginConfig getVkBindInLogin() {
        return this.vkBindInLogin;
    }

    @NotNull
    public final VkLoginScreenConfig getVkLoginConfig() {
        return this.vkLoginConfig;
    }

    @NotNull
    public final YandexHelpConfiguration getYandexHelpConfig() {
        return this.yandexHelpConfig;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((this.commonConfig.hashCode() * 31) + this.loginConfig.hashCode()) * 31) + this.vkLoginConfig.hashCode()) * 31) + this.imapConfig.hashCode()) * 31) + this.sessionRestoreConfig.hashCode()) * 31) + this.ludwigCaptchaConfig.hashCode()) * 31) + this.yandexHelpConfig.hashCode()) * 31) + this.secondStepConfig.hashCode()) * 31) + this.pushAuthInfoConfig.hashCode()) * 31) + this.mailUrls.hashCode()) * 31) + this.mrimConfig.hashCode()) * 31) + this.socialAuthConfig.hashCode()) * 31) + this.browserConfig.hashCode()) * 31) + this.buildConfigurationVariables.hashCode()) * 31) + this.regConfig.hashCode()) * 31) + this.mailAuthConfig.hashCode()) * 31) + this.vkBindInLogin.hashCode()) * 31) + this.flavorConfig.hashCode();
    }

    @NotNull
    public String toString() {
        return "AuthorizationSdkConfig(commonConfig=" + this.commonConfig + ", loginConfig=" + this.loginConfig + ", vkLoginConfig=" + this.vkLoginConfig + ", imapConfig=" + this.imapConfig + ", sessionRestoreConfig=" + this.sessionRestoreConfig + ", ludwigCaptchaConfig=" + this.ludwigCaptchaConfig + ", yandexHelpConfig=" + this.yandexHelpConfig + ", secondStepConfig=" + this.secondStepConfig + ", pushAuthInfoConfig=" + this.pushAuthInfoConfig + ", mailUrls=" + this.mailUrls + ", mrimConfig=" + this.mrimConfig + ", socialAuthConfig=" + this.socialAuthConfig + ", browserConfig=" + this.browserConfig + ", buildConfigurationVariables=" + this.buildConfigurationVariables + ", regConfig=" + this.regConfig + ", mailAuthConfig=" + this.mailAuthConfig + ", vkBindInLogin=" + this.vkBindInLogin + ", flavorConfig=" + this.flavorConfig + ")";
    }
}
