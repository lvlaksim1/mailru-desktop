package ru.mail.authorizationsdk.feature.authactivity.mapper;

import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.authactivity.screens.Screen;
import ru.mail.authorizationsdk.feature.authactivity.screens.ScreensUtil;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDResult;
import ru.mail.authorizationsdk.feature.bindemail.presentation.BindEmailResult;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult;
import ru.mail.authorizationsdk.feature.changepassword.presentation.ChangePasswordResult;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerResult;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccountMigrationResult;
import ru.mail.authorizationsdk.feature.google.common.presentation.GoogleResult;
import ru.mail.authorizationsdk.feature.login.presentation.common.LoginResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.CloudLoginResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.createcloud.CreateCloudResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloudvk.CloudLoginVkResult;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.LoginVkResult;
import ru.mail.authorizationsdk.feature.loginbindflow.presentation.LoginBindFlowResult;
import ru.mail.authorizationsdk.feature.mrim.MrimDialogResult;
import ru.mail.authorizationsdk.feature.ok.OKAuthResult;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeResult;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookResult;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordResult;
import ru.mail.authorizationsdk.feature.phone.accountlist.presentation.AccountListResult;
import ru.mail.authorizationsdk.feature.phone.codereceivetype.presentation.CodeReceiveTypeDialogResult;
import ru.mail.authorizationsdk.feature.phone.codereceivetype.presentation.CodeReceiveTypeFromEmailDialogResult;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeResult;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeResult;
import ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccResult;
import ru.mail.authorizationsdk.feature.phone.notreceivedcode.presentation.NotReceivedCodeResult;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainResult;
import ru.mail.authorizationsdk.feature.registration.presentation.screen.parentselection.ParentSelectionResult;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordResult;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkResult;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepResult;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.ChoiceAccountResult;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.EsiaScreenResult;
import ru.mail.authorizationsdk.feature.socialauth.presentation.SocialAuthResult;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOResult;
import ru.mail.authorizationsdk.feature.unblockuser.presentation.UnblockUserResult;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginResult;
import ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.VkFragmentSupportResult;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.WrongVkidAccountResult;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordResult;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.authorizationsdk.feature.yandexhelp.YandexHelpResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bJ<\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0005\"\u0010\b\u0000\u0010\r\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u000e0\u0005\"\u0004\b\u0001\u0010\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u0002H\u000eH\u0082\b¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/mapper/MainResultMapper;", "", "<init>", "()V", "onResultKey", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "key", "", "result", "Landroid/os/Bundle;", "screensUtil", "Lru/mail/authorizationsdk/feature/authactivity/screens/ScreensUtil;", "execute", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "R", "(Lru/mail/authorizationsdk/feature/authactivity/screens/ScreensUtil;Ljava/lang/Object;)Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMainResultMapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MainResultMapper.kt\nru/mail/authorizationsdk/feature/authactivity/mapper/MainResultMapper\n+ 2 ScreensUtil.kt\nru/mail/authorizationsdk/feature/authactivity/screens/ScreensUtil\n+ 3 ScreensStack.kt\nru/mail/authorizationsdk/feature/authactivity/screens/ScreensStack\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,277:1\n275#1:278\n275#1:282\n275#1:286\n275#1:290\n275#1:294\n275#1:298\n275#1:302\n275#1:306\n275#1:310\n275#1:314\n275#1:318\n275#1:322\n275#1:326\n275#1:330\n275#1:334\n275#1:338\n275#1:342\n275#1:346\n275#1:350\n275#1:354\n275#1:358\n275#1:362\n275#1:366\n275#1:370\n275#1:374\n275#1:378\n275#1:382\n275#1:386\n275#1:390\n275#1:394\n275#1:398\n275#1:402\n275#1:406\n275#1:410\n275#1:414\n275#1:418\n275#1:422\n275#1:426\n275#1:430\n275#1:434\n275#1:438\n275#1:442\n275#1:446\n73#2:279\n73#2:283\n73#2:287\n73#2:291\n73#2:295\n73#2:299\n73#2:303\n73#2:307\n73#2:311\n73#2:315\n73#2:319\n73#2:323\n73#2:327\n73#2:331\n73#2:335\n73#2:339\n73#2:343\n73#2:347\n73#2:351\n73#2:355\n73#2:359\n73#2:363\n73#2:367\n73#2:371\n73#2:375\n73#2:379\n73#2:383\n73#2:387\n73#2:391\n73#2:395\n73#2:399\n73#2:403\n73#2:407\n73#2:411\n73#2:415\n73#2:419\n73#2:423\n73#2:427\n73#2:431\n73#2:435\n73#2:439\n73#2:443\n73#2:447\n73#2:450\n85#3:280\n85#3:284\n85#3:288\n85#3:292\n85#3:296\n85#3:300\n85#3:304\n85#3:308\n85#3:312\n85#3:316\n85#3:320\n85#3:324\n85#3:328\n85#3:332\n85#3:336\n85#3:340\n85#3:344\n85#3:348\n85#3:352\n85#3:356\n85#3:360\n85#3:364\n85#3:368\n85#3:372\n85#3:376\n85#3:380\n85#3:384\n85#3:388\n85#3:392\n85#3:396\n85#3:400\n85#3:404\n85#3:408\n85#3:412\n85#3:416\n85#3:420\n85#3:424\n85#3:428\n85#3:432\n85#3:436\n85#3:440\n85#3:444\n85#3:448\n85#3:451\n1#4:281\n1#4:285\n1#4:289\n1#4:293\n1#4:297\n1#4:301\n1#4:305\n1#4:309\n1#4:313\n1#4:317\n1#4:321\n1#4:325\n1#4:329\n1#4:333\n1#4:337\n1#4:341\n1#4:345\n1#4:349\n1#4:353\n1#4:357\n1#4:361\n1#4:365\n1#4:369\n1#4:373\n1#4:377\n1#4:381\n1#4:385\n1#4:389\n1#4:393\n1#4:397\n1#4:401\n1#4:405\n1#4:409\n1#4:413\n1#4:417\n1#4:421\n1#4:425\n1#4:429\n1#4:433\n1#4:437\n1#4:441\n1#4:445\n1#4:449\n1#4:452\n*S KotlinDebug\n*F\n+ 1 MainResultMapper.kt\nru/mail/authorizationsdk/feature/authactivity/mapper/MainResultMapper\n*L\n53#1:278\n58#1:282\n63#1:286\n68#1:290\n73#1:294\n78#1:298\n83#1:302\n87#1:306\n92#1:310\n97#1:314\n102#1:318\n107#1:322\n112#1:326\n117#1:330\n122#1:334\n127#1:338\n132#1:342\n138#1:346\n144#1:350\n150#1:354\n155#1:358\n160#1:362\n165#1:366\n170#1:370\n175#1:374\n180#1:378\n185#1:382\n190#1:386\n195#1:390\n201#1:394\n207#1:398\n212#1:402\n217#1:406\n222#1:410\n227#1:414\n231#1:418\n235#1:422\n239#1:426\n243#1:430\n247#1:434\n252#1:438\n257#1:442\n262#1:446\n53#1:279\n58#1:283\n63#1:287\n68#1:291\n73#1:295\n78#1:299\n83#1:303\n87#1:307\n92#1:311\n97#1:315\n102#1:319\n107#1:323\n112#1:327\n117#1:331\n122#1:335\n127#1:339\n132#1:343\n138#1:347\n144#1:351\n150#1:355\n155#1:359\n160#1:363\n165#1:367\n170#1:371\n175#1:375\n180#1:379\n185#1:383\n190#1:387\n195#1:391\n201#1:395\n207#1:399\n212#1:403\n217#1:407\n222#1:411\n227#1:415\n231#1:419\n235#1:423\n239#1:427\n243#1:431\n247#1:435\n252#1:439\n257#1:443\n262#1:447\n275#1:450\n53#1:280\n58#1:284\n63#1:288\n68#1:292\n73#1:296\n78#1:300\n83#1:304\n87#1:308\n92#1:312\n97#1:316\n102#1:320\n107#1:324\n112#1:328\n117#1:332\n122#1:336\n127#1:340\n132#1:344\n138#1:348\n144#1:352\n150#1:356\n155#1:360\n160#1:364\n165#1:368\n170#1:372\n175#1:376\n180#1:380\n185#1:384\n190#1:388\n195#1:392\n201#1:396\n207#1:400\n212#1:404\n217#1:408\n222#1:412\n227#1:416\n231#1:420\n235#1:424\n239#1:428\n243#1:432\n247#1:436\n252#1:440\n257#1:444\n262#1:448\n275#1:451\n53#1:281\n58#1:285\n63#1:289\n68#1:293\n73#1:297\n78#1:301\n83#1:305\n87#1:309\n92#1:313\n97#1:317\n102#1:321\n107#1:325\n112#1:329\n117#1:333\n122#1:337\n127#1:341\n132#1:345\n138#1:349\n144#1:353\n150#1:357\n155#1:361\n160#1:365\n165#1:369\n170#1:373\n175#1:377\n180#1:381\n185#1:385\n190#1:389\n195#1:393\n201#1:397\n207#1:401\n212#1:405\n217#1:409\n222#1:413\n227#1:417\n231#1:421\n235#1:425\n239#1:429\n243#1:433\n247#1:437\n252#1:441\n257#1:445\n262#1:449\n*E\n"})
public final class MainResultMapper {
    public static final int $stable = 0;

    @NotNull
    public static final MainResultMapper INSTANCE = new MainResultMapper();

    private MainResultMapper() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final /* synthetic */ <T extends Screen<R>, R> Screen<?> execute(ScreensUtil screensUtil, R result) {
        Map<Class<? extends Screen<?>>, Screen<?>> screensContainer = screensUtil.getStack().getScreensContainer();
        Intrinsics.reifiedOperationMarker(4, RequestConfiguration.MAX_AD_CONTENT_RATING_T);
        Screen<?> screen = screensContainer.get(Screen.class);
        Intrinsics.reifiedOperationMarker(2, RequestConfiguration.MAX_AD_CONTENT_RATING_T);
        Screen<?> screen2 = screen;
        if (screen2 != null) {
            screen2.onResult(result);
        }
        return screen2;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Nullable
    public final Screen<?> onResultKey(@NotNull String key, @Nullable Bundle result, @NotNull ScreensUtil screensUtil) {
        ExternalAccountMigrationResult result2;
        LoginVkResult result3;
        CloudLoginResult result4;
        CodeReceiveTypeFromEmailDialogResult result5;
        PasswordResult result6;
        SSOResult result7;
        OutlookResult result8;
        WrongVkidAccountResult result9;
        LoginResult result10;
        EnterPhoneCodeResult result11;
        AccountListResult result12;
        CreateCloudResult result13;
        GoogleResult result14;
        EsiaScreenResult result15;
        UnblockUserResult result16;
        VkFragmentSupportResult result17;
        SecondStepResult result18;
        OKAuthResult result19;
        SocialAuthResult result20;
        RestoreVkResult result21;
        LoginBindFlowResult result22;
        NotReceivedCodeResult result23;
        VkPasswordResult result24;
        RegistrationMainResult result25;
        ChoiceAccountResult result26;
        ParentSelectionResult result27;
        YahooResult result28;
        CloudLoginVkResult result29;
        BindEmailResult result30;
        EnterEmailCodeResult result31;
        ChangePasswordResult result32;
        MrimDialogResult result33;
        YandexHelpResult result34;
        CodeReceiveTypeDialogResult result35;
        CustomServerResult result36;
        LudwigCaptchaResult result37;
        YandexResult result38;
        BeforeRecoveryVKIDResult result39;
        RestorePasswordResult result40;
        VkBindInLoginResult result41;
        GoogleResult result42;
        OneTimeCodeResult result43;
        EnterEmailCodeAfterListAccResult result44;
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(screensUtil, "screensUtil");
        switch (key.hashCode()) {
            case -2145573527:
                if (!key.equals(ExternalAccountMigrationResult.RESULT_KEY) || (result2 = ExternalAccountMigrationResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen = screensUtil.getStack().getScreensContainer().get(Screen.ExternalAccMigration.class);
                Screen.ExternalAccMigration externalAccMigration = (Screen.ExternalAccMigration) (screen instanceof Screen.ExternalAccMigration ? screen : null);
                if (externalAccMigration != null) {
                    externalAccMigration.onResult(result2);
                    Unit unit = Unit.INSTANCE;
                }
                return externalAccMigration;
            case -2012597861:
                if (!key.equals(LoginVkResult.RESULT_KEY) || (result3 = LoginVkResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen2 = screensUtil.getStack().getScreensContainer().get(Screen.LoginVk.class);
                Screen.LoginVk loginVk = (Screen.LoginVk) (screen2 instanceof Screen.LoginVk ? screen2 : null);
                if (loginVk != null) {
                    loginVk.onResult(result3);
                    Unit unit2 = Unit.INSTANCE;
                }
                return loginVk;
            case -1821361263:
                if (!key.equals(CloudLoginResult.RESULT_KEY) || (result4 = CloudLoginResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen3 = screensUtil.getStack().getScreensContainer().get(Screen.CloudLogin.class);
                Screen.CloudLogin cloudLogin = (Screen.CloudLogin) (screen3 instanceof Screen.CloudLogin ? screen3 : null);
                if (cloudLogin != null) {
                    cloudLogin.onResult(result4);
                    Unit unit3 = Unit.INSTANCE;
                }
                return cloudLogin;
            case -1739202521:
                if (!key.equals(CodeReceiveTypeFromEmailDialogResult.RESULT_KEY) || (result5 = CodeReceiveTypeFromEmailDialogResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen4 = screensUtil.getStack().getScreensContainer().get(Screen.CodeReceiveTypeFromEmailDialog.class);
                Screen.CodeReceiveTypeFromEmailDialog codeReceiveTypeFromEmailDialog = (Screen.CodeReceiveTypeFromEmailDialog) (screen4 instanceof Screen.CodeReceiveTypeFromEmailDialog ? screen4 : null);
                if (codeReceiveTypeFromEmailDialog != null) {
                    codeReceiveTypeFromEmailDialog.onResult(result5);
                    Unit unit4 = Unit.INSTANCE;
                }
                return codeReceiveTypeFromEmailDialog;
            case -1573545288:
                if (!key.equals(PasswordResult.RESULT_KEY) || (result6 = PasswordResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen5 = screensUtil.getStack().getScreensContainer().get(Screen.Password.class);
                Screen.Password password = (Screen.Password) (screen5 instanceof Screen.Password ? screen5 : null);
                if (password != null) {
                    password.onResult(result6);
                    Unit unit5 = Unit.INSTANCE;
                }
                return password;
            case -1527740692:
                if (!key.equals("SSOResult") || (result7 = SSOResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen6 = screensUtil.getStack().getScreensContainer().get(Screen.SSO.class);
                Screen.SSO sso = (Screen.SSO) (screen6 instanceof Screen.SSO ? screen6 : null);
                if (sso != null) {
                    sso.onResult(result7);
                    Unit unit6 = Unit.INSTANCE;
                }
                return sso;
            case -1485284022:
                if (!key.equals(OutlookResult.RESULT_KEY) || (result8 = OutlookResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen7 = screensUtil.getStack().getScreensContainer().get(Screen.Outlook.class);
                Screen.Outlook outlook = (Screen.Outlook) (screen7 instanceof Screen.Outlook ? screen7 : null);
                if (outlook != null) {
                    outlook.onResult(result8);
                    Unit unit7 = Unit.INSTANCE;
                }
                return outlook;
            case -1466192403:
                if (!key.equals(WrongVkidAccountResult.RESULT_KEY) || (result9 = WrongVkidAccountResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen8 = screensUtil.getStack().getScreensContainer().get(Screen.WrongVkidAccountDialog.class);
                Screen.WrongVkidAccountDialog wrongVkidAccountDialog = (Screen.WrongVkidAccountDialog) (screen8 instanceof Screen.WrongVkidAccountDialog ? screen8 : null);
                if (wrongVkidAccountDialog != null) {
                    wrongVkidAccountDialog.onResult(result9);
                    Unit unit8 = Unit.INSTANCE;
                }
                return wrongVkidAccountDialog;
            case -1354671930:
                if (!key.equals(LoginResult.RESULT_KEY) || (result10 = LoginResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen9 = screensUtil.getStack().getScreensContainer().get(Screen.Login.class);
                Screen.Login login = (Screen.Login) (screen9 instanceof Screen.Login ? screen9 : null);
                if (login != null) {
                    login.onResult(result10);
                    Unit unit9 = Unit.INSTANCE;
                }
                return login;
            case -1319797088:
                if (!key.equals(EnterPhoneCodeResult.RESULT_KEY) || (result11 = EnterPhoneCodeResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen10 = screensUtil.getStack().getScreensContainer().get(Screen.EnterPhoneCode.class);
                Screen.EnterPhoneCode enterPhoneCode = (Screen.EnterPhoneCode) (screen10 instanceof Screen.EnterPhoneCode ? screen10 : null);
                if (enterPhoneCode != null) {
                    enterPhoneCode.onResult(result11);
                    Unit unit10 = Unit.INSTANCE;
                }
                return enterPhoneCode;
            case -1217230776:
                if (!key.equals(AccountListResult.RESULT_KEY) || (result12 = AccountListResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen11 = screensUtil.getStack().getScreensContainer().get(Screen.AccountList.class);
                Screen.AccountList accountList = (Screen.AccountList) (screen11 instanceof Screen.AccountList ? screen11 : null);
                if (accountList != null) {
                    accountList.onResult(result12);
                    Unit unit11 = Unit.INSTANCE;
                }
                return accountList;
            case -1165531146:
                if (!key.equals(CreateCloudResult.RESULT_KEY) || (result13 = CreateCloudResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen12 = screensUtil.getStack().getScreensContainer().get(Screen.CreateCloud.class);
                Screen.CreateCloud createCloud = (Screen.CreateCloud) (screen12 instanceof Screen.CreateCloud ? screen12 : null);
                if (createCloud != null) {
                    createCloud.onResult(result13);
                    Unit unit12 = Unit.INSTANCE;
                }
                return createCloud;
            case -1062135240:
                if (!key.equals(GoogleResult.WEB_RESULT_KEY) || (result14 = GoogleResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen13 = screensUtil.getStack().getScreensContainer().get(Screen.GoogleWeb.class);
                Screen.GoogleWeb googleWeb = (Screen.GoogleWeb) (screen13 instanceof Screen.GoogleWeb ? screen13 : null);
                if (googleWeb != null) {
                    googleWeb.onResult(result14);
                    Unit unit13 = Unit.INSTANCE;
                }
                return googleWeb;
            case -793544401:
                if (!key.equals("EsiaScreenResult") || (result15 = EsiaScreenResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen14 = screensUtil.getStack().getScreensContainer().get(Screen.EsiaAuthScreen.class);
                Screen.EsiaAuthScreen esiaAuthScreen = (Screen.EsiaAuthScreen) (screen14 instanceof Screen.EsiaAuthScreen ? screen14 : null);
                if (esiaAuthScreen != null) {
                    esiaAuthScreen.onResult(result15);
                    Unit unit14 = Unit.INSTANCE;
                }
                return esiaAuthScreen;
            case -672301988:
                if (!key.equals(UnblockUserResult.RESULT_KEY) || (result16 = UnblockUserResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen15 = screensUtil.getStack().getScreensContainer().get(Screen.UnblockUser.class);
                Screen.UnblockUser unblockUser = (Screen.UnblockUser) (screen15 instanceof Screen.UnblockUser ? screen15 : null);
                if (unblockUser != null) {
                    unblockUser.onResult(result16);
                    Unit unit15 = Unit.INSTANCE;
                }
                return unblockUser;
            case -659753131:
                if (!key.equals(VkFragmentSupportResult.RESULT_KEY) || (result17 = VkFragmentSupportResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen16 = screensUtil.getStack().getScreensContainer().get(Screen.VkFragmentSupport.class);
                Screen.VkFragmentSupport vkFragmentSupport = (Screen.VkFragmentSupport) (screen16 instanceof Screen.VkFragmentSupport ? screen16 : null);
                if (vkFragmentSupport != null) {
                    vkFragmentSupport.onResult(result17);
                    Unit unit16 = Unit.INSTANCE;
                }
                return vkFragmentSupport;
            case -433007171:
                if (!key.equals(SecondStepResult.RESULT_KEY) || (result18 = SecondStepResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen17 = screensUtil.getStack().getScreensContainer().get(Screen.SecondFactor.class);
                Screen.SecondFactor secondFactor = (Screen.SecondFactor) (screen17 instanceof Screen.SecondFactor ? screen17 : null);
                if (secondFactor != null) {
                    secondFactor.onResult(result18);
                    Unit unit17 = Unit.INSTANCE;
                }
                return secondFactor;
            case -368970015:
                if (!key.equals(OKAuthResult.RESULT_KEY) || (result19 = OKAuthResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen18 = screensUtil.getStack().getScreensContainer().get(Screen.OKAuth.class);
                Screen.OKAuth oKAuth = (Screen.OKAuth) (screen18 instanceof Screen.OKAuth ? screen18 : null);
                if (oKAuth != null) {
                    oKAuth.onResult(result19);
                    Unit unit18 = Unit.INSTANCE;
                }
                return oKAuth;
            case -353679534:
                if (!key.equals("SocialAuthResult") || (result20 = SocialAuthResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen19 = screensUtil.getStack().getScreensContainer().get(Screen.SocialAuth.class);
                Screen.SocialAuth socialAuth = (Screen.SocialAuth) (screen19 instanceof Screen.SocialAuth ? screen19 : null);
                if (socialAuth != null) {
                    socialAuth.onResult(result20);
                    Unit unit19 = Unit.INSTANCE;
                }
                return socialAuth;
            case -258758720:
                if (!key.equals(RestoreVkResult.RESULT_KEY) || (result21 = RestoreVkResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen20 = screensUtil.getStack().getScreensContainer().get(Screen.RestoreVkId.class);
                Screen.RestoreVkId restoreVkId = (Screen.RestoreVkId) (screen20 instanceof Screen.RestoreVkId ? screen20 : null);
                if (restoreVkId != null) {
                    restoreVkId.onResult(result21);
                    Unit unit20 = Unit.INSTANCE;
                }
                return restoreVkId;
            case -231798095:
                if (!key.equals("LoginBindFlowResult") || (result22 = LoginBindFlowResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen21 = screensUtil.getStack().getScreensContainer().get(Screen.LoginBindFlow.class);
                Screen.LoginBindFlow loginBindFlow = (Screen.LoginBindFlow) (screen21 instanceof Screen.LoginBindFlow ? screen21 : null);
                if (loginBindFlow != null) {
                    loginBindFlow.onResult(result22);
                    Unit unit21 = Unit.INSTANCE;
                }
                return loginBindFlow;
            case -203496130:
                if (!key.equals(NotReceivedCodeResult.RESULT_KEY) || (result23 = NotReceivedCodeResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen22 = screensUtil.getStack().getScreensContainer().get(Screen.NotReceivedCodeDialog.class);
                Screen.NotReceivedCodeDialog notReceivedCodeDialog = (Screen.NotReceivedCodeDialog) (screen22 instanceof Screen.NotReceivedCodeDialog ? screen22 : null);
                if (notReceivedCodeDialog != null) {
                    notReceivedCodeDialog.onResult(result23);
                    Unit unit22 = Unit.INSTANCE;
                }
                return notReceivedCodeDialog;
            case -135557939:
                if (!key.equals("VkPasswordResult") || (result24 = VkPasswordResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen23 = screensUtil.getStack().getScreensContainer().get(Screen.VkPassword.class);
                Screen.VkPassword vkPassword = (Screen.VkPassword) (screen23 instanceof Screen.VkPassword ? screen23 : null);
                if (vkPassword != null) {
                    vkPassword.onResult(result24);
                    Unit unit23 = Unit.INSTANCE;
                }
                return vkPassword;
            case 38824175:
                if (!key.equals(RegistrationMainResult.RESULT_KEY) || (result25 = RegistrationMainResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen24 = screensUtil.getStack().getScreensContainer().get(Screen.RegistrationMain.class);
                Screen.RegistrationMain registrationMain = (Screen.RegistrationMain) (screen24 instanceof Screen.RegistrationMain ? screen24 : null);
                if (registrationMain != null) {
                    registrationMain.onResult(result25);
                    Unit unit24 = Unit.INSTANCE;
                }
                return registrationMain;
            case 50311273:
                if (!key.equals("ChoiceAccountResult") || (result26 = ChoiceAccountResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen25 = screensUtil.getStack().getScreensContainer().get(Screen.ChoiceAccount.class);
                Screen.ChoiceAccount choiceAccount = (Screen.ChoiceAccount) (screen25 instanceof Screen.ChoiceAccount ? screen25 : null);
                if (choiceAccount != null) {
                    choiceAccount.onResult(result26);
                    Unit unit25 = Unit.INSTANCE;
                }
                return choiceAccount;
            case 179315359:
                if (!key.equals(ParentSelectionResult.RESULT_KEY) || (result27 = ParentSelectionResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen26 = screensUtil.getStack().getScreensContainer().get(Screen.ParentSelection.class);
                Screen.ParentSelection parentSelection = (Screen.ParentSelection) (screen26 instanceof Screen.ParentSelection ? screen26 : null);
                if (parentSelection != null) {
                    parentSelection.onResult(result27);
                    Unit unit26 = Unit.INSTANCE;
                }
                return parentSelection;
            case 457962589:
                if (!key.equals(YahooResult.RESULT_KEY) || (result28 = YahooResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen27 = screensUtil.getStack().getScreensContainer().get(Screen.Yahoo.class);
                Screen.Yahoo yahoo = (Screen.Yahoo) (screen27 instanceof Screen.Yahoo ? screen27 : null);
                if (yahoo != null) {
                    yahoo.onResult(result28);
                    Unit unit27 = Unit.INSTANCE;
                }
                return yahoo;
            case 470519206:
                if (!key.equals(CloudLoginVkResult.RESULT_KEY) || (result29 = CloudLoginVkResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen28 = screensUtil.getStack().getScreensContainer().get(Screen.CloudLoginVk.class);
                Screen.CloudLoginVk cloudLoginVk = (Screen.CloudLoginVk) (screen28 instanceof Screen.CloudLoginVk ? screen28 : null);
                if (cloudLoginVk != null) {
                    cloudLoginVk.onResult(result29);
                    Unit unit28 = Unit.INSTANCE;
                }
                return cloudLoginVk;
            case 490798652:
                if (!key.equals("BindEmailResult") || (result30 = BindEmailResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen29 = screensUtil.getStack().getScreensContainer().get(Screen.BindEmail.class);
                Screen.BindEmail bindEmail = (Screen.BindEmail) (screen29 instanceof Screen.BindEmail ? screen29 : null);
                if (bindEmail != null) {
                    bindEmail.onResult(result30);
                    Unit unit29 = Unit.INSTANCE;
                }
                return bindEmail;
            case 517679950:
                if (!key.equals(EnterEmailCodeResult.RESULT_KEY) || (result31 = EnterEmailCodeResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen30 = screensUtil.getStack().getScreensContainer().get(Screen.EnterEmailCode.class);
                Screen.EnterEmailCode enterEmailCode = (Screen.EnterEmailCode) (screen30 instanceof Screen.EnterEmailCode ? screen30 : null);
                if (enterEmailCode != null) {
                    enterEmailCode.onResult(result31);
                    Unit unit30 = Unit.INSTANCE;
                }
                return enterEmailCode;
            case 676179592:
                if (!key.equals(ChangePasswordResult.RESULT_KEY) || (result32 = ChangePasswordResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen31 = screensUtil.getStack().getScreensContainer().get(Screen.ChangePassword.class);
                Screen.ChangePassword changePassword = (Screen.ChangePassword) (screen31 instanceof Screen.ChangePassword ? screen31 : null);
                if (changePassword != null) {
                    changePassword.onResult(result32);
                    Unit unit31 = Unit.INSTANCE;
                }
                return changePassword;
            case 754807758:
                if (!key.equals(MrimDialogResult.RESULT_KEY) || (result33 = MrimDialogResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen32 = screensUtil.getStack().getScreensContainer().get(Screen.MrimDialog.class);
                Screen.MrimDialog mrimDialog = (Screen.MrimDialog) (screen32 instanceof Screen.MrimDialog ? screen32 : null);
                if (mrimDialog != null) {
                    mrimDialog.onResult(result33);
                    Unit unit32 = Unit.INSTANCE;
                }
                return mrimDialog;
            case 1102169519:
                if (!key.equals(YandexHelpResult.RESULT_KEY) || (result34 = YandexHelpResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen33 = screensUtil.getStack().getScreensContainer().get(Screen.YandexHelp.class);
                Screen.YandexHelp yandexHelp = (Screen.YandexHelp) (screen33 instanceof Screen.YandexHelp ? screen33 : null);
                if (yandexHelp != null) {
                    yandexHelp.onResult(result34);
                    Unit unit33 = Unit.INSTANCE;
                }
                return yandexHelp;
            case 1152106517:
                if (!key.equals(CodeReceiveTypeDialogResult.RESULT_KEY) || (result35 = CodeReceiveTypeDialogResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen34 = screensUtil.getStack().getScreensContainer().get(Screen.CodeReceiveTypeDialog.class);
                Screen.CodeReceiveTypeDialog codeReceiveTypeDialog = (Screen.CodeReceiveTypeDialog) (screen34 instanceof Screen.CodeReceiveTypeDialog ? screen34 : null);
                if (codeReceiveTypeDialog != null) {
                    codeReceiveTypeDialog.onResult(result35);
                    Unit unit34 = Unit.INSTANCE;
                }
                return codeReceiveTypeDialog;
            case 1373249393:
                if (!key.equals(CustomServerResult.RESULT_KEY) || (result36 = CustomServerResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen35 = screensUtil.getStack().getScreensContainer().get(Screen.CustomServer.class);
                Screen.CustomServer customServer = (Screen.CustomServer) (screen35 instanceof Screen.CustomServer ? screen35 : null);
                if (customServer != null) {
                    customServer.onResult(result36);
                    Unit unit35 = Unit.INSTANCE;
                }
                return customServer;
            case 1389372183:
                if (!key.equals(LudwigCaptchaResult.RESULT_KEY) || (result37 = LudwigCaptchaResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen36 = screensUtil.getStack().getScreensContainer().get(Screen.Captcha.class);
                Screen.Captcha captcha = (Screen.Captcha) (screen36 instanceof Screen.Captcha ? screen36 : null);
                if (captcha != null) {
                    captcha.onResult(result37);
                    Unit unit36 = Unit.INSTANCE;
                }
                return captcha;
            case 1395220366:
                if (!key.equals(YandexResult.RESULT_KEY) || (result38 = YandexResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen37 = screensUtil.getStack().getScreensContainer().get(Screen.Yandex.class);
                Screen.Yandex yandex = (Screen.Yandex) (screen37 instanceof Screen.Yandex ? screen37 : null);
                if (yandex != null) {
                    yandex.onResult(result38);
                    Unit unit37 = Unit.INSTANCE;
                }
                return yandex;
            case 1427204641:
                if (!key.equals(BeforeRecoveryVKIDResult.RESULT_KEY) || (result39 = BeforeRecoveryVKIDResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen38 = screensUtil.getStack().getScreensContainer().get(Screen.BeforeRecoveryVKID.class);
                Screen.BeforeRecoveryVKID beforeRecoveryVKID = (Screen.BeforeRecoveryVKID) (screen38 instanceof Screen.BeforeRecoveryVKID ? screen38 : null);
                if (beforeRecoveryVKID != null) {
                    beforeRecoveryVKID.onResult(result39);
                    Unit unit38 = Unit.INSTANCE;
                }
                return beforeRecoveryVKID;
            case 1675570342:
                if (!key.equals(RestorePasswordResult.RESULT_KEY) || (result40 = RestorePasswordResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen39 = screensUtil.getStack().getScreensContainer().get(Screen.RestorePassword.class);
                Screen.RestorePassword restorePassword = (Screen.RestorePassword) (screen39 instanceof Screen.RestorePassword ? screen39 : null);
                if (restorePassword != null) {
                    restorePassword.onResult(result40);
                    Unit unit39 = Unit.INSTANCE;
                }
                return restorePassword;
            case 1756578543:
                if (!key.equals("VkBindInLoginResult") || (result41 = VkBindInLoginResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen40 = screensUtil.getStack().getScreensContainer().get(Screen.VkBindInLogin.class);
                Screen.VkBindInLogin vkBindInLogin = (Screen.VkBindInLogin) (screen40 instanceof Screen.VkBindInLogin ? screen40 : null);
                if (vkBindInLogin != null) {
                    vkBindInLogin.onResult(result41);
                    Unit unit40 = Unit.INSTANCE;
                }
                return vkBindInLogin;
            case 1834629133:
                if (!key.equals(GoogleResult.NATIVE_RESULT_KEY) || (result42 = GoogleResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen41 = screensUtil.getStack().getScreensContainer().get(Screen.GoogleNative.class);
                Screen.GoogleNative googleNative = (Screen.GoogleNative) (screen41 instanceof Screen.GoogleNative ? screen41 : null);
                if (googleNative != null) {
                    googleNative.onResult(result42);
                    Unit unit41 = Unit.INSTANCE;
                }
                return googleNative;
            case 2003689213:
                if (!key.equals(OneTimeCodeResult.RESULT_KEY) || (result43 = OneTimeCodeResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen42 = screensUtil.getStack().getScreensContainer().get(Screen.OneTimeCode.class);
                Screen.OneTimeCode oneTimeCode = (Screen.OneTimeCode) (screen42 instanceof Screen.OneTimeCode ? screen42 : null);
                if (oneTimeCode != null) {
                    oneTimeCode.onResult(result43);
                    Unit unit42 = Unit.INSTANCE;
                }
                return oneTimeCode;
            case 2113796661:
                if (!key.equals(EnterEmailCodeAfterListAccResult.RESULT_KEY) || (result44 = EnterEmailCodeAfterListAccResult.INSTANCE.getResult(result)) == null) {
                    return null;
                }
                Screen<?> screen43 = screensUtil.getStack().getScreensContainer().get(Screen.EnterEmailCodeAfterListAcc.class);
                Screen.EnterEmailCodeAfterListAcc enterEmailCodeAfterListAcc = (Screen.EnterEmailCodeAfterListAcc) (screen43 instanceof Screen.EnterEmailCodeAfterListAcc ? screen43 : null);
                if (enterEmailCodeAfterListAcc != null) {
                    enterEmailCodeAfterListAcc.onResult(result44);
                    Unit unit43 = Unit.INSTANCE;
                }
                return enterEmailCodeAfterListAcc;
            default:
                return null;
        }
    }
}
