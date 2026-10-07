package ru.mail.authorizationsdk.feature.authactivity.data.request;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.screens.Screen;
import ru.mail.authorizationsdk.feature.authactivity.screens.ScreensUtil;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerResult;
import ru.mail.authorizationsdk.feature.google.common.presentation.GoogleResult;
import ru.mail.authorizationsdk.feature.login.presentation.common.LoginResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.CloudLoginResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloudvk.CloudLoginVkResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.LoginVkResult;
import ru.mail.authorizationsdk.feature.loginbindflow.presentation.LoginBindFlowResult;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeResult;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookResult;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordResult;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordResult;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkResult;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepResult;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.ChoiceAccountResult;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.presentation.model.ChoiceMode;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.EsiaScreenResult;
import ru.mail.authorizationsdk.feature.socialauth.presentation.SocialAuthResult;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOResult;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginResult;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordResult;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.social_auth.domain.AuthResult;
import ru.mail.social_auth.domain.VkBindTokens;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0013\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0011\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0002¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/data/request/RequestMapper;", "", "isLocalImapEnabled", "", "analytics", "Lru/mail/authorizationsdk/feature/authactivity/AuthActivityAnalytics;", "<init>", "(ZLru/mail/authorizationsdk/feature/authactivity/AuthActivityAnalytics;)V", "getAuthRequestType", "Lkotlin/Result;", "Lru/mail/authorizationsdk/feature/authactivity/data/request/AuthRequestType;", "screens", "Lru/mail/authorizationsdk/feature/authactivity/screens/ScreensUtil;", "getAuthRequestType-IoAF18A", "(Lru/mail/authorizationsdk/feature/authactivity/screens/ScreensUtil;)Ljava/lang/Object;", "getAdditionalParams", "", "", "toVkBindTokens", "ru/mail/authorizationsdk/feature/authactivity/data/request/RequestMapper$toVkBindTokens$1", "Lru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowResult$BindResult;", "(Lru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowResult$BindResult;)Lru/mail/authorizationsdk/feature/authactivity/data/request/RequestMapper$toVkBindTokens$1;", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRequestMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestMapper.kt\nru/mail/authorizationsdk/feature/authactivity/data/request/RequestMapper\n+ 2 ScreensUtil.kt\nru/mail/authorizationsdk/feature/authactivity/screens/ScreensUtil\n+ 3 ScreensStack.kt\nru/mail/authorizationsdk/feature/authactivity/screens/ScreensStack\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,431:1\n73#2:432\n73#2:434\n73#2:436\n73#2:438\n73#2:440\n73#2:442\n73#2:444\n73#2:446\n73#2:448\n73#2:450\n73#2:452\n73#2:454\n73#2:456\n73#2:458\n73#2:460\n73#2:462\n73#2:464\n73#2:466\n73#2:468\n73#2:470\n73#2:472\n73#2:475\n73#2:477\n73#2:479\n85#3:433\n85#3:435\n85#3:437\n85#3:439\n85#3:441\n85#3:443\n85#3:445\n85#3:447\n85#3:449\n85#3:451\n85#3:453\n85#3:455\n85#3:457\n85#3:459\n85#3:461\n85#3:463\n85#3:465\n85#3:467\n85#3:469\n85#3:471\n85#3:473\n85#3:476\n85#3:478\n85#3:480\n1#4:474\n*S KotlinDebug\n*F\n+ 1 RequestMapper.kt\nru/mail/authorizationsdk/feature/authactivity/data/request/RequestMapper\n*L\n38#1:432\n40#1:434\n41#1:436\n42#1:438\n43#1:440\n44#1:442\n45#1:444\n46#1:446\n47#1:448\n48#1:450\n49#1:452\n50#1:454\n51#1:456\n52#1:458\n54#1:460\n55#1:462\n56#1:464\n57#1:466\n58#1:468\n59#1:470\n60#1:472\n412#1:475\n414#1:477\n416#1:479\n38#1:433\n40#1:435\n41#1:437\n42#1:439\n43#1:441\n44#1:443\n45#1:445\n46#1:447\n47#1:449\n48#1:451\n49#1:453\n50#1:455\n51#1:457\n52#1:459\n54#1:461\n55#1:463\n56#1:465\n57#1:467\n58#1:469\n59#1:471\n60#1:473\n412#1:476\n414#1:478\n416#1:480\n*E\n"})
public final class RequestMapper {

    @NotNull
    private static final String LUDWIG_TOKEN_PARAM = "ludwig_token";

    @NotNull
    private final AuthActivityAnalytics analytics;
    private final boolean isLocalImapEnabled;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ChoiceMode.values().length];
            try {
                iArr[ChoiceMode.ESIA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ChoiceMode.AUTOLOGIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ChoiceMode.RESTORE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ChoiceMode.VK_ID.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[GoogleResult.Success.GoogleAuthType.values().length];
            try {
                iArr2[GoogleResult.Success.GoogleAuthType.WEB.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[GoogleResult.Success.GoogleAuthType.NATIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public RequestMapper(boolean z10, @NotNull AuthActivityAnalytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.isLocalImapEnabled = z10;
        this.analytics = analytics;
    }

    private final Map<String, String> getAdditionalParams(ScreensUtil screens) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Screen<?> screen = screens.getStack().getScreensContainer().get(Screen.SecondFactor.class);
        if (!(screen instanceof Screen.SecondFactor)) {
            screen = null;
        }
        Screen.SecondFactor secondFactor = (Screen.SecondFactor) screen;
        SecondStepResult result = secondFactor != null ? secondFactor.getResult() : null;
        SecondStepResult.Success success = result instanceof SecondStepResult.Success ? (SecondStepResult.Success) result : null;
        Screen<?> screen2 = screens.getStack().getScreensContainer().get(Screen.RestorePassword.class);
        if (!(screen2 instanceof Screen.RestorePassword)) {
            screen2 = null;
        }
        Screen.RestorePassword restorePassword = (Screen.RestorePassword) screen2;
        RestorePasswordResult result2 = restorePassword != null ? restorePassword.getResult() : null;
        RestorePasswordResult.Success success2 = result2 instanceof RestorePasswordResult.Success ? (RestorePasswordResult.Success) result2 : null;
        Screen<?> screen3 = screens.getStack().getScreensContainer().get(Screen.Captcha.class);
        if (!(screen3 instanceof Screen.Captcha)) {
            screen3 = null;
        }
        Screen.Captcha captcha = (Screen.Captcha) screen3;
        LudwigCaptchaResult result3 = captcha != null ? captcha.getResult() : null;
        LudwigCaptchaResult.CaptchaDone captchaDone = result3 instanceof LudwigCaptchaResult.CaptchaDone ? (LudwigCaptchaResult.CaptchaDone) result3 : null;
        if (captchaDone != null) {
            linkedHashMap.put("ludwig_token", captchaDone.getLudwigToken());
        }
        if (success != null) {
            linkedHashMap.putAll(success.getAdditionalParams());
        }
        if (success2 != null) {
            linkedHashMap.putAll(success2.getQueryParams());
        }
        return linkedHashMap;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [ru.mail.authorizationsdk.feature.authactivity.data.request.RequestMapper$toVkBindTokens$1] */
    private final AnonymousClass1 toVkBindTokens(LoginBindFlowResult.BindResult bindResult) {
        return new VkBindTokens(bindResult) { // from class: ru.mail.authorizationsdk.feature.authactivity.data.request.RequestMapper.toVkBindTokens.1
            private final String bindToken;
            private final String bindType;

            {
                this.bindToken = bindResult.getBindToken();
                this.bindType = bindResult.getBindType();
            }

            @Override // ru.mail.social_auth.domain.VkBindTokens
            public String getBindToken() {
                return this.bindToken;
            }

            @Override // ru.mail.social_auth.domain.VkBindTokens
            public String getBindType() {
                return this.bindType;
            }
        };
    }

    @NotNull
    /* JADX INFO: renamed from: getAuthRequestType-IoAF18A, reason: not valid java name */
    public final Object m14720getAuthRequestTypeIoAF18A(@NotNull ScreensUtil screens) {
        GoogleResult result;
        MatchGroupCollection groups;
        MatchGroup matchGroup;
        String value;
        Object imapLocal;
        VkBindTokens vkBindTokens;
        Object imapLocal2;
        Object web;
        Object outlook;
        Object yandex;
        Object yahoo;
        Object esiaAuth;
        Intrinsics.checkNotNullParameter(screens, "screens");
        Screen<?> screen = screens.getStack().getScreensContainer().get(Screen.LoginBindFlow.class);
        if (!(screen instanceof Screen.LoginBindFlow)) {
            screen = null;
        }
        Screen.LoginBindFlow loginBindFlow = (Screen.LoginBindFlow) screen;
        LoginBindFlowResult result2 = loginBindFlow != null ? loginBindFlow.getResult() : null;
        LoginBindFlowResult.BindResult bindResult = result2 instanceof LoginBindFlowResult.BindResult ? (LoginBindFlowResult.BindResult) result2 : null;
        Screen<?> screen2 = screens.getStack().getScreensContainer().get(Screen.OneTimeCode.class);
        if (!(screen2 instanceof Screen.OneTimeCode)) {
            screen2 = null;
        }
        Screen.OneTimeCode oneTimeCode = (Screen.OneTimeCode) screen2;
        OneTimeCodeResult result3 = oneTimeCode != null ? oneTimeCode.getResult() : null;
        Screen<?> screen3 = screens.getStack().getScreensContainer().get(Screen.Password.class);
        if (!(screen3 instanceof Screen.Password)) {
            screen3 = null;
        }
        Screen.Password password = (Screen.Password) screen3;
        PasswordResult result4 = password != null ? password.getResult() : null;
        Screen<?> screen4 = screens.getStack().getScreensContainer().get(Screen.RestorePassword.class);
        if (!(screen4 instanceof Screen.RestorePassword)) {
            screen4 = null;
        }
        Screen.RestorePassword restorePassword = (Screen.RestorePassword) screen4;
        RestorePasswordResult result5 = restorePassword != null ? restorePassword.getResult() : null;
        Screen<?> screen5 = screens.getStack().getScreensContainer().get(Screen.VkBindInLogin.class);
        if (!(screen5 instanceof Screen.VkBindInLogin)) {
            screen5 = null;
        }
        Screen.VkBindInLogin vkBindInLogin = (Screen.VkBindInLogin) screen5;
        VkBindInLoginResult result6 = vkBindInLogin != null ? vkBindInLogin.getResult() : null;
        Screen<?> screen6 = screens.getStack().getScreensContainer().get(Screen.EsiaAuthScreen.class);
        if (!(screen6 instanceof Screen.EsiaAuthScreen)) {
            screen6 = null;
        }
        Screen.EsiaAuthScreen esiaAuthScreen = (Screen.EsiaAuthScreen) screen6;
        EsiaScreenResult result7 = esiaAuthScreen != null ? esiaAuthScreen.getResult() : null;
        Screen<?> screen7 = screens.getStack().getScreensContainer().get(Screen.VkPassword.class);
        if (!(screen7 instanceof Screen.VkPassword)) {
            screen7 = null;
        }
        Screen.VkPassword vkPassword = (Screen.VkPassword) screen7;
        VkPasswordResult result8 = vkPassword != null ? vkPassword.getResult() : null;
        Screen<?> screen8 = screens.getStack().getScreensContainer().get(Screen.SocialAuth.class);
        if (!(screen8 instanceof Screen.SocialAuth)) {
            screen8 = null;
        }
        Screen.SocialAuth socialAuth = (Screen.SocialAuth) screen8;
        SocialAuthResult result9 = socialAuth != null ? socialAuth.getResult() : null;
        Screen<?> screen9 = screens.getStack().getScreensContainer().get(Screen.RestoreVkId.class);
        if (!(screen9 instanceof Screen.RestoreVkId)) {
            screen9 = null;
        }
        Screen.RestoreVkId restoreVkId = (Screen.RestoreVkId) screen9;
        RestoreVkResult result10 = restoreVkId != null ? restoreVkId.getResult() : null;
        Screen<?> screen10 = screens.getStack().getScreensContainer().get(Screen.SSO.class);
        if (!(screen10 instanceof Screen.SSO)) {
            screen10 = null;
        }
        Screen.SSO sso = (Screen.SSO) screen10;
        SSOResult result11 = sso != null ? sso.getResult() : null;
        Screen<?> screen11 = screens.getStack().getScreensContainer().get(Screen.CustomServer.class);
        if (!(screen11 instanceof Screen.CustomServer)) {
            screen11 = null;
        }
        Screen.CustomServer customServer = (Screen.CustomServer) screen11;
        CustomServerResult result12 = customServer != null ? customServer.getResult() : null;
        Screen<?> screen12 = screens.getStack().getScreensContainer().get(Screen.Yahoo.class);
        if (!(screen12 instanceof Screen.Yahoo)) {
            screen12 = null;
        }
        Screen.Yahoo yahoo2 = (Screen.Yahoo) screen12;
        YahooResult result13 = yahoo2 != null ? yahoo2.getResult() : null;
        Screen<?> screen13 = screens.getStack().getScreensContainer().get(Screen.Yandex.class);
        if (!(screen13 instanceof Screen.Yandex)) {
            screen13 = null;
        }
        Screen.Yandex yandex2 = (Screen.Yandex) screen13;
        YandexResult result14 = yandex2 != null ? yandex2.getResult() : null;
        Screen<?> screen14 = screens.getStack().getScreensContainer().get(Screen.Outlook.class);
        if (!(screen14 instanceof Screen.Outlook)) {
            screen14 = null;
        }
        Screen.Outlook outlook2 = (Screen.Outlook) screen14;
        OutlookResult result15 = outlook2 != null ? outlook2.getResult() : null;
        SSOResult sSOResult = result11;
        Screen<?> screen15 = screens.getStack().getScreensContainer().get(Screen.GoogleNative.class);
        if (!(screen15 instanceof Screen.GoogleNative)) {
            screen15 = null;
        }
        Screen.GoogleNative googleNative = (Screen.GoogleNative) screen15;
        if (googleNative == null || (result = googleNative.getResult()) == null) {
            Screen<?> screen16 = screens.getStack().getScreensContainer().get(Screen.GoogleWeb.class);
            if (!(screen16 instanceof Screen.GoogleWeb)) {
                screen16 = null;
            }
            Screen.GoogleWeb googleWeb = (Screen.GoogleWeb) screen16;
            result = googleWeb != null ? googleWeb.getResult() : null;
        }
        VkPasswordResult vkPasswordResult = result8;
        Screen<?> screen17 = screens.getStack().getScreensContainer().get(Screen.SecondFactor.class);
        if (!(screen17 instanceof Screen.SecondFactor)) {
            screen17 = null;
        }
        Screen.SecondFactor secondFactor = (Screen.SecondFactor) screen17;
        SecondStepResult result16 = secondFactor != null ? secondFactor.getResult() : null;
        VkBindInLoginResult vkBindInLoginResult = result6;
        Screen<?> screen18 = screens.getStack().getScreensContainer().get(Screen.Login.class);
        if (!(screen18 instanceof Screen.Login)) {
            screen18 = null;
        }
        Screen.Login login = (Screen.Login) screen18;
        LoginResult result17 = login != null ? login.getResult() : null;
        Screen<?> screen19 = screens.getStack().getScreensContainer().get(Screen.LoginVk.class);
        if (!(screen19 instanceof Screen.LoginVk)) {
            screen19 = null;
        }
        Screen.LoginVk loginVk = (Screen.LoginVk) screen19;
        LoginVkResult result18 = loginVk != null ? loginVk.getResult() : null;
        SocialAuthResult socialAuthResult = result9;
        Screen<?> screen20 = screens.getStack().getScreensContainer().get(Screen.CloudLoginVk.class);
        if (!(screen20 instanceof Screen.CloudLoginVk)) {
            screen20 = null;
        }
        Screen.CloudLoginVk cloudLoginVk = (Screen.CloudLoginVk) screen20;
        CloudLoginVkResult result19 = cloudLoginVk != null ? cloudLoginVk.getResult() : null;
        LoginBindFlowResult.BindResult bindResult2 = bindResult;
        Screen<?> screen21 = screens.getStack().getScreensContainer().get(Screen.CloudLogin.class);
        if (!(screen21 instanceof Screen.CloudLogin)) {
            screen21 = null;
        }
        Screen.CloudLogin cloudLogin = (Screen.CloudLogin) screen21;
        CloudLoginResult result20 = cloudLogin != null ? cloudLogin.getResult() : null;
        Screen<?> screen22 = screens.getStack().getScreensContainer().get(Screen.ChoiceAccount.class);
        if (!(screen22 instanceof Screen.ChoiceAccount)) {
            screen22 = null;
        }
        Screen.ChoiceAccount choiceAccount = (Screen.ChoiceAccount) screen22;
        ChoiceAccountResult result21 = choiceAccount != null ? choiceAccount.getResult() : null;
        Map<String, String> additionalParams = getAdditionalParams(screens);
        PasswordResult passwordResult = result4;
        ChoiceAccountResult choiceAccountResult = result21;
        if (result21 instanceof ChoiceAccountResult.Login) {
            Result.Companion companion = Result.INSTANCE;
            ChoiceAccountResult.Login login2 = (ChoiceAccountResult.Login) choiceAccountResult;
            int i10 = WhenMappings.$EnumSwitchMapping$0[login2.getMode().ordinal()];
            if (i10 == 1) {
                esiaAuth = new AuthRequestType.EsiaAuth(login2.getLogin(), login2.getAgToken(), additionalParams);
            } else if (i10 == 2) {
                esiaAuth = new AuthRequestType.AutologinAuth(login2.getLogin(), login2.getAgToken(), additionalParams);
            } else if (i10 == 3) {
                esiaAuth = new AuthRequestType.AfterRestoreAuth(login2.getLogin(), login2.getAgToken(), additionalParams);
            } else {
                if (i10 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                esiaAuth = new AuthRequestType.VkIdAuth(login2.getLogin(), login2.getAgToken(), additionalParams);
            }
            return Result.m13123constructorimpl(esiaAuth);
        }
        if (result5 instanceof RestorePasswordResult.Success) {
            Result.Companion companion2 = Result.INSTANCE;
            return Result.m13123constructorimpl(new AuthRequestType.AfterRestoreWithoutPassAuth(((RestorePasswordResult.Success) result5).getEmail(), additionalParams));
        }
        if (result10 instanceof RestoreVkResult.RestoreSuccess) {
            Result.Companion companion3 = Result.INSTANCE;
            RestoreVkResult.RestoreSuccess restoreSuccess = (RestoreVkResult.RestoreSuccess) result10;
            return Result.m13123constructorimpl(new AuthRequestType.VkSdkAuth(restoreSuccess.getEmail(), restoreSuccess.getAgToken(), additionalParams));
        }
        if (result3 instanceof OneTimeCodeResult.Success) {
            Result.Companion companion4 = Result.INSTANCE;
            OneTimeCodeResult.Success success = (OneTimeCodeResult.Success) result3;
            return Result.m13123constructorimpl(new AuthRequestType.OneTimeCodeAuth(success.getEmail(), success.getParams(), success.getCookie(), bindResult2 != null ? bindResult2.getBindToken() : null, bindResult2 != null ? bindResult2.getBindType() : null, additionalParams));
        }
        if (result7 instanceof EsiaScreenResult.FinishEsiaAuth) {
            Result.Companion companion5 = Result.INSTANCE;
            EsiaScreenResult.FinishEsiaAuth finishEsiaAuth = (EsiaScreenResult.FinishEsiaAuth) result7;
            return Result.m13123constructorimpl(new AuthRequestType.EsiaAuth(finishEsiaAuth.getEmail(), finishEsiaAuth.getAgToken(), additionalParams));
        }
        if (result13 instanceof YahooResult.Success) {
            Result.Companion companion6 = Result.INSTANCE;
            if (this.isLocalImapEnabled) {
                YahooResult.Success success2 = (YahooResult.Success) result13;
                yahoo = new AuthRequestType.ImapOauthLocal(success2.getEmail(), success2.getYahooAccessToken(), success2.getYahooRefreshToken());
            } else {
                YahooResult.Success success3 = (YahooResult.Success) result13;
                yahoo = new AuthRequestType.Yahoo(success3.getEmail(), success3.getAuthUrlWithQuery(), success3.getBaseParams(), success3.getYahooAccessToken(), success3.getYahooRefreshToken(), additionalParams);
            }
            return Result.m13123constructorimpl(yahoo);
        }
        if (result14 instanceof YandexResult.Success) {
            Result.Companion companion7 = Result.INSTANCE;
            if (this.isLocalImapEnabled) {
                YandexResult.Success success4 = (YandexResult.Success) result14;
                yandex = new AuthRequestType.ImapOauthLocal(success4.getEmail(), success4.getYandexAccessToken(), success4.getYandexRefreshToken());
            } else {
                YandexResult.Success success5 = (YandexResult.Success) result14;
                yandex = new AuthRequestType.Yandex(success5.getEmail(), success5.getAuthUrlWithQuery(), success5.getBaseParams(), success5.getYandexAccessToken(), success5.getYandexRefreshToken(), additionalParams);
            }
            return Result.m13123constructorimpl(yandex);
        }
        if (result15 instanceof OutlookResult.Success) {
            Result.Companion companion8 = Result.INSTANCE;
            if (this.isLocalImapEnabled) {
                OutlookResult.Success success6 = (OutlookResult.Success) result15;
                outlook = new AuthRequestType.ImapOauthLocal(success6.getEmail(), success6.getOutlookAccessToken(), success6.getOutlookRefreshToken());
            } else {
                OutlookResult.Success success7 = (OutlookResult.Success) result15;
                outlook = new AuthRequestType.Outlook(success7.getEmail(), success7.getAuthUrlWithQuery(), success7.getBaseParams(), success7.getOutlookAccessToken(), success7.getOutlookRefreshToken(), additionalParams);
            }
            return Result.m13123constructorimpl(outlook);
        }
        if (result instanceof GoogleResult.Success) {
            if (this.isLocalImapEnabled) {
                Result.Companion companion9 = Result.INSTANCE;
                GoogleResult.Success success8 = (GoogleResult.Success) result;
                return Result.m13123constructorimpl(new AuthRequestType.ImapOauthLocal(success8.getEmail(), success8.getGoogleAccessToken(), success8.getGoogleRefreshToken()));
            }
            GoogleResult.Success success9 = (GoogleResult.Success) result;
            int i11 = WhenMappings.$EnumSwitchMapping$1[success9.getGoogleAuthType().ordinal()];
            if (i11 == 1) {
                web = new AuthRequestType.Google.Web(success9.getEmail(), success9.getAuthUrlWithQuery(), success9.getBaseParams(), success9.getGoogleAccessToken(), success9.getGoogleRefreshToken(), success9.getXmailMigrationFrom(), additionalParams);
            } else {
                if (i11 != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                web = new AuthRequestType.Google.Native(success9.getEmail(), success9.getAuthUrlWithQuery(), success9.getBaseParams(), success9.getGoogleAccessToken(), success9.getGoogleRefreshToken(), success9.getXmailMigrationFrom(), additionalParams);
            }
            return Result.m13123constructorimpl(web);
        }
        if (result12 instanceof CustomServerResult.Success) {
            CustomServerResult.Success success10 = (CustomServerResult.Success) result12;
            String email = success10.getEmail();
            String str = StringsKt.isBlank(email) ? null : email;
            if (str == null) {
                Result.Companion companion10 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalArgumentException("No email entered!")));
            }
            Result.Companion companion11 = Result.INSTANCE;
            if (this.isLocalImapEnabled) {
                imapLocal2 = new AuthRequestType.ImapLocal(str, success10.getPassword());
            } else {
                String password2 = success10.getPassword();
                SecondStepResult.Success success11 = result16 instanceof SecondStepResult.Success ? (SecondStepResult.Success) result16 : null;
                imapLocal2 = new AuthRequestType.PasswordAuth(str, password2, success11 != null ? success11.getTsaCookie() : null, null, null, additionalParams, 24, null);
            }
            return Result.m13123constructorimpl(imapLocal2);
        }
        if (result18 instanceof LoginVkResult.VkAuthSuccess) {
            Result.Companion companion12 = Result.INSTANCE;
            LoginVkResult.VkAuthSuccess vkAuthSuccess = (LoginVkResult.VkAuthSuccess) result18;
            return Result.m13123constructorimpl(new AuthRequestType.VkSdkAuth(vkAuthSuccess.getLogin(), vkAuthSuccess.getAgToken(), additionalParams));
        }
        if (result19 instanceof CloudLoginVkResult.VkAuthSuccess) {
            Result.Companion companion13 = Result.INSTANCE;
            CloudLoginVkResult.VkAuthSuccess vkAuthSuccess2 = (CloudLoginVkResult.VkAuthSuccess) result19;
            return Result.m13123constructorimpl(new AuthRequestType.VkSdkAuth(vkAuthSuccess2.getLogin(), vkAuthSuccess2.getAgToken(), additionalParams));
        }
        if (passwordResult instanceof PasswordResult.Success) {
            PasswordResult.Success success12 = (PasswordResult.Success) passwordResult;
            String email2 = success12.getEmail();
            String str2 = StringsKt.isBlank(email2) ? null : email2;
            if (str2 == null) {
                Result.Companion companion14 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalArgumentException("No login entered!")));
            }
            Result.Companion companion15 = Result.INSTANCE;
            if (this.isLocalImapEnabled) {
                imapLocal = new AuthRequestType.ImapLocal(str2, success12.getPassword());
            } else {
                if (bindResult2 == null || (vkBindTokens = toVkBindTokens(bindResult2)) == null) {
                    SocialAuthResult.Result result22 = socialAuthResult instanceof SocialAuthResult.Result ? (SocialAuthResult.Result) socialAuthResult : null;
                    AuthResult value2 = result22 != null ? result22.getValue() : null;
                    vkBindTokens = value2 instanceof VkBindTokens ? (VkBindTokens) value2 : null;
                    if (vkBindTokens == null) {
                        vkBindTokens = vkBindInLoginResult instanceof VkBindInLoginResult.BindLogin ? (VkBindInLoginResult.BindLogin) vkBindInLoginResult : null;
                    }
                }
                String password3 = success12.getPassword();
                String bindToken = vkBindTokens != null ? vkBindTokens.getBindToken() : null;
                String bindType = vkBindTokens != null ? vkBindTokens.getBindType() : null;
                SecondStepResult.Success success13 = result16 instanceof SecondStepResult.Success ? (SecondStepResult.Success) result16 : null;
                imapLocal = new AuthRequestType.PasswordAuth(str2, password3, success13 != null ? success13.getTsaCookie() : null, bindToken, bindType, additionalParams);
            }
            return Result.m13123constructorimpl(imapLocal);
        }
        if (vkPasswordResult instanceof VkPasswordResult.Success) {
            VkPasswordResult.Success success14 = (VkPasswordResult.Success) vkPasswordResult;
            String login3 = success14.getLogin();
            String str3 = StringsKt.isBlank(login3) ? null : login3;
            if (str3 == null) {
                Result.Companion companion16 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalArgumentException("No VkPass login entered!")));
            }
            Result.Companion companion17 = Result.INSTANCE;
            return Result.m13123constructorimpl(new AuthRequestType.VkPasswordAuth(str3, success14.getAgToken(), additionalParams));
        }
        if (sSOResult instanceof SSOResult.Success) {
            SSOResult.Success success15 = (SSOResult.Success) sSOResult;
            String login4 = success15.getLogin();
            String str4 = StringsKt.isBlank(login4) ? null : login4;
            if (str4 == null) {
                Result.Companion companion18 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalArgumentException("No sso email entered!")));
            }
            Result.Companion companion19 = Result.INSTANCE;
            return Result.m13123constructorimpl(new AuthRequestType.SSOAuth(str4, success15.getAgToken(), additionalParams));
        }
        if (result17 instanceof LoginResult.VkSilentAuthSuccess) {
            Result.Companion companion20 = Result.INSTANCE;
            LoginResult.VkSilentAuthSuccess vkSilentAuthSuccess = (LoginResult.VkSilentAuthSuccess) result17;
            return Result.m13123constructorimpl(new AuthRequestType.VkSilentAuth(vkSilentAuthSuccess.getLogin(), vkSilentAuthSuccess.getAuthUrlWithQuery(), vkSilentAuthSuccess.getBaseParams(), vkSilentAuthSuccess.getSilentToken(), null, 16, null));
        }
        if (result20 instanceof CloudLoginResult.VkSilentAuthSuccess) {
            Result.Companion companion21 = Result.INSTANCE;
            CloudLoginResult.VkSilentAuthSuccess vkSilentAuthSuccess2 = (CloudLoginResult.VkSilentAuthSuccess) result20;
            return Result.m13123constructorimpl(new AuthRequestType.VkSilentAuth(vkSilentAuthSuccess2.getLogin(), vkSilentAuthSuccess2.getAuthUrlWithQuery(), vkSilentAuthSuccess2.getBaseParams(), vkSilentAuthSuccess2.getSilentToken(), null, 16, null));
        }
        if (result18 instanceof LoginVkResult.VkSilentAuthSuccess) {
            Result.Companion companion22 = Result.INSTANCE;
            LoginVkResult.VkSilentAuthSuccess vkSilentAuthSuccess3 = (LoginVkResult.VkSilentAuthSuccess) result18;
            return Result.m13123constructorimpl(new AuthRequestType.VkSilentAuth(vkSilentAuthSuccess3.getLogin(), vkSilentAuthSuccess3.getAuthUrlWithQuery(), vkSilentAuthSuccess3.getBaseParams(), vkSilentAuthSuccess3.getSilentToken(), null, 16, null));
        }
        if (result19 instanceof CloudLoginVkResult.VkSilentAuthSuccess) {
            Result.Companion companion23 = Result.INSTANCE;
            CloudLoginVkResult.VkSilentAuthSuccess vkSilentAuthSuccess4 = (CloudLoginVkResult.VkSilentAuthSuccess) result19;
            return Result.m13123constructorimpl(new AuthRequestType.VkSilentAuth(vkSilentAuthSuccess4.getLogin(), vkSilentAuthSuccess4.getAuthUrlWithQuery(), vkSilentAuthSuccess4.getBaseParams(), vkSilentAuthSuccess4.getSilentToken(), null, 16, null));
        }
        if (socialAuthResult instanceof SocialAuthResult.Result) {
            SocialAuthResult.Result result23 = (SocialAuthResult.Result) socialAuthResult;
            if (result23.getValue() instanceof AuthResult.LoginResult) {
                AuthResult.LoginResult loginResult = (AuthResult.LoginResult) result23.getValue();
                MatchResult matchResultFind$default = Regex.find$default(new Regex("[?&]token=([^&]+)"), loginResult.getAuthUrl(), 0, 2, null);
                String str5 = (matchResultFind$default == null || (groups = matchResultFind$default.getGroups()) == null || (matchGroup = groups.get(1)) == null || (value = matchGroup.getValue()) == null) ? "" : value;
                Result.Companion companion24 = Result.INSTANCE;
                return Result.m13123constructorimpl(new AuthRequestType.SocialAuth(loginResult.getEmail(), str5, result23.getAuthType(), loginResult.getSocialBindType(), loginResult.isResetSoftVKIDBind(), additionalParams));
            }
        }
        if (result17 instanceof LoginResult.FinishVkAutologinAuth) {
            Result.Companion companion25 = Result.INSTANCE;
            LoginResult.FinishVkAutologinAuth finishVkAutologinAuth = (LoginResult.FinishVkAutologinAuth) result17;
            return Result.m13123constructorimpl(new AuthRequestType.AutologinAuth(finishVkAutologinAuth.getLogin(), finishVkAutologinAuth.getAgToken(), additionalParams));
        }
        if (result20 instanceof CloudLoginResult.FinishVkAutologinAuth) {
            Result.Companion companion26 = Result.INSTANCE;
            CloudLoginResult.FinishVkAutologinAuth finishVkAutologinAuth2 = (CloudLoginResult.FinishVkAutologinAuth) result20;
            return Result.m13123constructorimpl(new AuthRequestType.AutologinAuth(finishVkAutologinAuth2.getLogin(), finishVkAutologinAuth2.getAgToken(), additionalParams));
        }
        if (result18 instanceof LoginVkResult.FinishVkAutologinAuth) {
            Result.Companion companion27 = Result.INSTANCE;
            LoginVkResult.FinishVkAutologinAuth finishVkAutologinAuth3 = (LoginVkResult.FinishVkAutologinAuth) result18;
            return Result.m13123constructorimpl(new AuthRequestType.AutologinAuth(finishVkAutologinAuth3.getLogin(), finishVkAutologinAuth3.getAgToken(), additionalParams));
        }
        if (result19 instanceof CloudLoginVkResult.FinishVkAutologinAuth) {
            Result.Companion companion28 = Result.INSTANCE;
            CloudLoginVkResult.FinishVkAutologinAuth finishVkAutologinAuth4 = (CloudLoginVkResult.FinishVkAutologinAuth) result19;
            return Result.m13123constructorimpl(new AuthRequestType.AutologinAuth(finishVkAutologinAuth4.getLogin(), finishVkAutologinAuth4.getAgToken(), additionalParams));
        }
        this.analytics.onRequestMapperError("Wrong auth data!");
        this.analytics.wrongSdkWork("Wrong auth data!", "");
        Result.Companion companion29 = Result.INSTANCE;
        return Result.m13123constructorimpl(ResultKt.createFailure(new IllegalStateException("Wrong auth data!")));
    }
}
