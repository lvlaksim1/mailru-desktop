package ru.mail.authorizationsdk.navigation.auth;

import android.os.Bundle;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavType;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.di.AuthorizeSdkComponent;
import ru.mail.authorizationsdk.feature.authactivity.screens.Screen;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDViewModel;
import ru.mail.authorizationsdk.feature.bindemail.presentation.BindEmailViewModel;
import ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccMigrationViewModel;
import ru.mail.authorizationsdk.feature.loginbindflow.presentation.LoginBindFlowViewModel;
import ru.mail.authorizationsdk.feature.mrim.MrimDialogViewModel;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeViewModel;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookConstants;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordViewModel;
import ru.mail.authorizationsdk.feature.phone.codereceivetype.presentation.CodeReceivedTypeBottomSheetViewModel;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeViewModel;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeViewModel;
import ru.mail.authorizationsdk.feature.phone.notreceivedcode.presentation.NotReceivedCodeBottomSheetViewModel;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordViewModel;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkViewModel;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.presentation.ChoiceAccountViewModel;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.presentation.EsiaViewModel;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOViewModel;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginViewModel;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.WrongVkidAccountViewModel;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordViewModel;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooViewModel;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexConstants;
import ru.mail.authorizationsdk.navigation.DestBase;
import ru.mail.authorizationsdk.navigation.auth.Dest;
import ru.mail.authorizationsdk.navigation.domain.NavArgument;
import ru.mail.authorizationsdk.navigation.registration.RegNavGraphKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000ø\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b3\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:*\u001f !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHB\u0087\u0001\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\b0\u0007\u0012Z\u0010\t\u001aV\u0012\u0004\u0012\u00020\u000b\u0012'\u0012%\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R!\u0010\u0006\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bRg\u0010\t\u001aV\u0012\u0004\u0012\u00020\u000b\u0012'\u0012%\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0004\u0012\u00020\u000e0\f¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\u0002\b\u0014¢\u0006\u0002\b\u0015¢\u0006\n\n\u0002\u0010\u001e\u001a\u0004\b\u001c\u0010\u001d\u0082\u0001*IJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqr¨\u0006s"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest;", "S", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/navigation/DestBase;", "dest", "", "navArguments", "", "Lru/mail/authorizationsdk/navigation/domain/NavArgument;", "content", "Lkotlin/Function3;", "Landroidx/navigation/NavBackStackEntry;", "Lkotlin/Function2;", "Landroid/os/Bundle;", "", "Lkotlin/ParameterName;", "name", "onResult", "Lru/mail/authorizationsdk/di/AuthorizeSdkComponent;", "component", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "<init>", "(Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function5;)V", "getDest", "()Ljava/lang/String;", "getNavArguments", "()Ljava/util/List;", "getContent", "()Lkotlin/jvm/functions/Function5;", "Lkotlin/jvm/functions/Function5;", "LoginVk", "CloudLoginVk", "Login", "CloudLogin", "Password", "LoginBindFlow", "RestorePassword", "RestoreVkId", "Captcha", "EsiaAuthScreen", "BindEmail", "OneTimeCode", "Yahoo", "Yandex", "MrimDialog", "WrongVkidAccountDialog", "YandexHelp", "OKAuth", "GoogleNative", "GoogleWeb", "SecondFactor", "SSO", "VkPassword", "ExternalAccMigration", "Outlook", "CustomServer", "BeforeRecoveryVKID", "UnblockUser", "ChangePassword", "RegistrationMain", "VkBindInLogin", "ChoiceAccount", "EnterPhone", "EnterPhoneCode", "CreateCloud", "EnterEmailCode", "AccountList", "EnterEmailCodeAfterListAcc", "CodeReceiveTypeDialog", "CodeReceiveTypeFromEmailDialog", "NotReceivedCodeDialog", "Start", "Lru/mail/authorizationsdk/navigation/auth/Dest$AccountList;", "Lru/mail/authorizationsdk/navigation/auth/Dest$BeforeRecoveryVKID;", "Lru/mail/authorizationsdk/navigation/auth/Dest$BindEmail;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Captcha;", "Lru/mail/authorizationsdk/navigation/auth/Dest$ChangePassword;", "Lru/mail/authorizationsdk/navigation/auth/Dest$ChoiceAccount;", "Lru/mail/authorizationsdk/navigation/auth/Dest$CloudLogin;", "Lru/mail/authorizationsdk/navigation/auth/Dest$CloudLoginVk;", "Lru/mail/authorizationsdk/navigation/auth/Dest$CodeReceiveTypeDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest$CodeReceiveTypeFromEmailDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest$CreateCloud;", "Lru/mail/authorizationsdk/navigation/auth/Dest$CustomServer;", "Lru/mail/authorizationsdk/navigation/auth/Dest$EnterEmailCode;", "Lru/mail/authorizationsdk/navigation/auth/Dest$EnterEmailCodeAfterListAcc;", "Lru/mail/authorizationsdk/navigation/auth/Dest$EnterPhone;", "Lru/mail/authorizationsdk/navigation/auth/Dest$EnterPhoneCode;", "Lru/mail/authorizationsdk/navigation/auth/Dest$EsiaAuthScreen;", "Lru/mail/authorizationsdk/navigation/auth/Dest$ExternalAccMigration;", "Lru/mail/authorizationsdk/navigation/auth/Dest$GoogleNative;", "Lru/mail/authorizationsdk/navigation/auth/Dest$GoogleWeb;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Login;", "Lru/mail/authorizationsdk/navigation/auth/Dest$LoginBindFlow;", "Lru/mail/authorizationsdk/navigation/auth/Dest$LoginVk;", "Lru/mail/authorizationsdk/navigation/auth/Dest$MrimDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest$NotReceivedCodeDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest$OKAuth;", "Lru/mail/authorizationsdk/navigation/auth/Dest$OneTimeCode;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Outlook;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Password;", "Lru/mail/authorizationsdk/navigation/auth/Dest$RegistrationMain;", "Lru/mail/authorizationsdk/navigation/auth/Dest$RestorePassword;", "Lru/mail/authorizationsdk/navigation/auth/Dest$RestoreVkId;", "Lru/mail/authorizationsdk/navigation/auth/Dest$SSO;", "Lru/mail/authorizationsdk/navigation/auth/Dest$SecondFactor;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Start;", "Lru/mail/authorizationsdk/navigation/auth/Dest$UnblockUser;", "Lru/mail/authorizationsdk/navigation/auth/Dest$VkBindInLogin;", "Lru/mail/authorizationsdk/navigation/auth/Dest$VkPassword;", "Lru/mail/authorizationsdk/navigation/auth/Dest$WrongVkidAccountDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Yahoo;", "Lru/mail/authorizationsdk/navigation/auth/Dest$Yandex;", "Lru/mail/authorizationsdk/navigation/auth/Dest$YandexHelp;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Dest<S extends Screen<?>> extends DestBase<S> {
    public static final int $stable = 8;

    @NotNull
    private final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> content;

    @NotNull
    private final String dest;

    @NotNull
    private final List<NavArgument<S, ?>> navArguments;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$AccountList;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$AccountList;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AccountList extends Dest<Screen.AccountList> {

        @NotNull
        public static final AccountList INSTANCE = new AccountList();
        public static final int $stable = 8;

        private AccountList() {
            super("account_list", CollectionsKt.emptyList(), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14801getLambda$58225748$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof AccountList);
        }

        public int hashCode() {
            return 1826394380;
        }

        @NotNull
        public String toString() {
            return "AccountList";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$BeforeRecoveryVKID;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$BeforeRecoveryVKID;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BeforeRecoveryVKID extends Dest<Screen.BeforeRecoveryVKID> {

        @NotNull
        public static final BeforeRecoveryVKID INSTANCE = new BeforeRecoveryVKID();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private BeforeRecoveryVKID() {
            NavType<String> navType = NavType.StringType;
            super("beforeRecoveryVKID", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(BeforeRecoveryVKIDViewModel.BEFORE_RECOVERY_BLOCKED_EMAIL, navType, false, null, new Function1() { // from class: d9.g1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.BeforeRecoveryVKID._init_$lambda$0((Screen.BeforeRecoveryVKID) obj);
                }
            }, 8, null), new NavArgument("FAIL_URL", navType, false, null, new Function1() { // from class: d9.h1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.BeforeRecoveryVKID._init_$lambda$1((Screen.BeforeRecoveryVKID) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1671114291$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.BeforeRecoveryVKID NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getBlockedEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.BeforeRecoveryVKID NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFailUrl();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof BeforeRecoveryVKID);
        }

        public int hashCode() {
            return 15183107;
        }

        @NotNull
        public String toString() {
            return "BeforeRecoveryVKID";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$BindEmail;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$BindEmail;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BindEmail extends Dest<Screen.BindEmail> {

        @NotNull
        public static final BindEmail INSTANCE = new BindEmail();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private BindEmail() {
            NavType<String> navType = NavType.StringType;
            super("bind_email", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(BindEmailViewModel.MAIL_TOKEN_PARAM, navType, false, null, new Function1() { // from class: d9.i1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.BindEmail._init_$lambda$0((Screen.BindEmail) obj);
                }
            }, 8, null), new NavArgument(BindEmailViewModel.VK_A_TOKEN_PARAM, navType, false, null, new Function1() { // from class: d9.j1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.BindEmail._init_$lambda$1((Screen.BindEmail) obj);
                }
            }, 8, null), new NavArgument(BindEmailViewModel.BIND_TYPE_PARAM, navType, false, null, new Function1() { // from class: d9.k1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.BindEmail._init_$lambda$2((Screen.BindEmail) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14795getLambda$2007266504$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.BindEmail NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getMailToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.BindEmail NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getVkAccessToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.BindEmail NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSocialBindType();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof BindEmail);
        }

        public int hashCode() {
            return 1290324544;
        }

        @NotNull
        public String toString() {
            return "BindEmail";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Captcha;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Captcha;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Captcha extends Dest<Screen.Captcha> {

        @NotNull
        public static final Captcha INSTANCE = new Captcha();
        public static final int $stable = 8;

        private Captcha() {
            super("captcha", CollectionsKt.listOf(new NavArgument(WebCaptchaComposeViewModel.LUDWIG_TOKEN, NavType.StringType, false, null, new Function1() { // from class: d9.l1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Captcha._init_$lambda$0((Screen.Captcha) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1374966269$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.Captcha NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getLudwigToken();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Captcha);
        }

        public int hashCode() {
            return 1185069979;
        }

        @NotNull
        public String toString() {
            return "Captcha";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$ChangePassword;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChangePassword;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ChangePassword extends Dest<Screen.ChangePassword> {

        @NotNull
        public static final ChangePassword INSTANCE = new ChangePassword();
        public static final int $stable = 8;

        private ChangePassword() {
            super("changePassword", CollectionsKt.emptyList(), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14799getLambda$365309972$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof ChangePassword);
        }

        public int hashCode() {
            return 1500901930;
        }

        @NotNull
        public String toString() {
            return "ChangePassword";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$ChoiceAccount;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChoiceAccount;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ChoiceAccount extends Dest<Screen.ChoiceAccount> {

        @NotNull
        public static final ChoiceAccount INSTANCE = new ChoiceAccount();
        public static final int $stable = 8;

        private ChoiceAccount() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_ENTERED_EMAIL, navType, false, null, new Function1() { // from class: d9.m1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.ChoiceAccount._init_$lambda$0((Screen.ChoiceAccount) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_APP_ICON_RES, NavType.IntType, true, null, new Function1() { // from class: d9.n1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(Dest.ChoiceAccount._init_$lambda$1((Screen.ChoiceAccount) obj));
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_SOCIAL_BIND_TYPE, navType, false, null, new Function1() { // from class: d9.o1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.ChoiceAccount._init_$lambda$2((Screen.ChoiceAccount) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("ChoiceAccount", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_IS_SIMPLE_CHOICE_FRAGMENT, navType2, false, null, new Function1() { // from class: d9.p1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.ChoiceAccount._init_$lambda$3((Screen.ChoiceAccount) obj));
                }
            }, 8, null), new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_IS_SOCIAL_ACCOUNT_ENABLED, navType2, false, null, new Function1() { // from class: d9.q1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.ChoiceAccount._init_$lambda$4((Screen.ChoiceAccount) obj));
                }
            }, 8, null), new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_IS_VK_MAIL_APP, navType2, false, null, new Function1() { // from class: d9.r1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.ChoiceAccount._init_$lambda$5((Screen.ChoiceAccount) obj));
                }
            }, 8, null), new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_IS_VK_ID_WITHOUT_PASSWORD_ENABLED, navType2, false, null, new Function1() { // from class: d9.s1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.ChoiceAccount._init_$lambda$6((Screen.ChoiceAccount) obj));
                }
            }, 8, null), new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_IS_MODE, navType, false, null, new Function1() { // from class: d9.t1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.ChoiceAccount._init_$lambda$7((Screen.ChoiceAccount) obj);
                }
            }, 8, null), new NavArgument(ChoiceAccountViewModel.CHOICE_ACC_VK_ACCESS_TOKEN, navType, false, null, new Function1() { // from class: d9.u1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.ChoiceAccount._init_$lambda$8((Screen.ChoiceAccount) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$149357675$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEnteredEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int _init_$lambda$1(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            Integer appIconRes = NavArgument.getAppIconRes();
            if (appIconRes != null) {
                return appIconRes.intValue();
            }
            return -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSocialBindType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isSimpleChoiceFragment();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isSocialAccountEnabled();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$5(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isVkMailApp();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$6(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isVkIdWithoutPasswordEnabled();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$7(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getChoiceMode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$8(Screen.ChoiceAccount NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getVkAccessToken();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof ChoiceAccount);
        }

        public int hashCode() {
            return 1976269229;
        }

        @NotNull
        public String toString() {
            return "ChoiceAccount";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$CloudLogin;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CloudLogin;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CloudLogin extends Dest<Screen.CloudLogin> {

        @NotNull
        public static final CloudLogin INSTANCE = new CloudLogin();
        public static final int $stable = 8;

        private CloudLogin() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument("login_screen_email", navType, true, null, new Function1() { // from class: d9.v1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CloudLogin._init_$lambda$0((Screen.CloudLogin) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument("LOGIN_FROM", navType, false, null, new Function1() { // from class: d9.w1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CloudLogin._init_$lambda$1((Screen.CloudLogin) obj);
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument("LOGIN_SERVICE_TYPE", navType, false, null, new Function1() { // from class: d9.x1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CloudLogin._init_$lambda$2((Screen.CloudLogin) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("CloudLogin", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument("is_maual_logout", navType2, false, null, new Function1() { // from class: d9.y1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CloudLogin._init_$lambda$3((Screen.CloudLogin) obj));
                }
            }, 8, null), new NavArgument("is_deeplink_opened", navType2, false, null, new Function1() { // from class: d9.z1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CloudLogin._init_$lambda$4((Screen.CloudLogin) obj));
                }
            }, 8, null), new NavArgument("IS_NEED_START_RESTORE", navType2, false, null, new Function1() { // from class: d9.a2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CloudLogin._init_$lambda$5((Screen.CloudLogin) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14800getLambda$374890813$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.CloudLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.CloudLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.CloudLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.CloudLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isManualLogout();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.CloudLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isDeeplinkOpened();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$5(Screen.CloudLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isNeedStartRestore();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof CloudLogin);
        }

        public int hashCode() {
            return 529391987;
        }

        @NotNull
        public String toString() {
            return "CloudLogin";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$CloudLoginVk;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CloudLoginVk;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CloudLoginVk extends Dest<Screen.CloudLoginVk> {

        @NotNull
        public static final CloudLoginVk INSTANCE = new CloudLoginVk();
        public static final int $stable = 8;

        private CloudLoginVk() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument("login_screen_email", navType, true, null, new Function1() { // from class: d9.b2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CloudLoginVk._init_$lambda$0((Screen.CloudLoginVk) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument("LOGIN_FROM", navType, false, null, new Function1() { // from class: d9.c2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CloudLoginVk._init_$lambda$1((Screen.CloudLoginVk) obj);
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument("LOGIN_SERVICE_TYPE", navType, false, null, new Function1() { // from class: d9.d2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CloudLoginVk._init_$lambda$2((Screen.CloudLoginVk) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("CloudLoginVk", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument("is_maual_logout", navType2, false, null, new Function1() { // from class: d9.e2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CloudLoginVk._init_$lambda$3((Screen.CloudLoginVk) obj));
                }
            }, 8, null), new NavArgument("is_deeplink_opened", navType2, false, null, new Function1() { // from class: d9.f2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CloudLoginVk._init_$lambda$4((Screen.CloudLoginVk) obj));
                }
            }, 8, null), new NavArgument("IS_NEED_START_RESTORE", navType2, false, null, new Function1() { // from class: d9.g2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CloudLoginVk._init_$lambda$5((Screen.CloudLoginVk) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$63607566$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.CloudLoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.CloudLoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.CloudLoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.CloudLoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isManualLogout();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.CloudLoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isDeeplinkOpened();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$5(Screen.CloudLoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isNeedStartRestore();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof CloudLoginVk);
        }

        public int hashCode() {
            return 1939561352;
        }

        @NotNull
        public String toString() {
            return "CloudLoginVk";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$CodeReceiveTypeDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CodeReceiveTypeDialog;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CodeReceiveTypeDialog extends Dest<Screen.CodeReceiveTypeDialog> {

        @NotNull
        public static final CodeReceiveTypeDialog INSTANCE = new CodeReceiveTypeDialog();
        public static final int $stable = 8;

        private CodeReceiveTypeDialog() {
            NavType<Boolean> navType = NavType.BoolType;
            NavArgument navArgument = new NavArgument(CodeReceivedTypeBottomSheetViewModel.HAS_CLOUD_CALL_INFO, navType, false, null, new Function1() { // from class: d9.h2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$0((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null);
            NavType<Long> navType2 = NavType.LongType;
            super("code_receive_type_dialog", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, new NavArgument(CodeReceivedTypeBottomSheetViewModel.CLOUD_CALL_DELAY_FROM, navType2, false, null, new Function1() { // from class: d9.i2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Long.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$1((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null), new NavArgument(CodeReceivedTypeBottomSheetViewModel.CLOUD_CALL_DELAY, navType2, false, null, new Function1() { // from class: d9.j2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Long.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$2((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null), new NavArgument(CodeReceivedTypeBottomSheetViewModel.HAS_CLOUD_SMS_INFO, navType, false, null, new Function1() { // from class: d9.k2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$3((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null), new NavArgument(CodeReceivedTypeBottomSheetViewModel.CLOUD_SMS_DELAY_FROM, navType2, false, null, new Function1() { // from class: d9.l2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Long.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$4((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null), new NavArgument(CodeReceivedTypeBottomSheetViewModel.CLOUD_SMS_DELAY, navType2, false, null, new Function1() { // from class: d9.m2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Long.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$5((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null), new NavArgument(CodeReceivedTypeBottomSheetViewModel.RECEIVE_TYPE, NavType.IntType, false, null, new Function1() { // from class: d9.n2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(Dest.CodeReceiveTypeDialog._init_$lambda$6((Screen.CodeReceiveTypeDialog) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14793getLambda$186721473$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$0(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHasWaitCallInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long _init_$lambda$1(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getCallDelayFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long _init_$lambda$2(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getCallDelay();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHasSmsInfo();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long _init_$lambda$4(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSmsDelayFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long _init_$lambda$5(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSmsDelay();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int _init_$lambda$6(Screen.CodeReceiveTypeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getReceiveType();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof CodeReceiveTypeDialog);
        }

        public int hashCode() {
            return 700950873;
        }

        @NotNull
        public String toString() {
            return "CodeReceiveTypeDialog";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$CodeReceiveTypeFromEmailDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CodeReceiveTypeFromEmailDialog;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CodeReceiveTypeFromEmailDialog extends Dest<Screen.CodeReceiveTypeFromEmailDialog> {

        @NotNull
        public static final CodeReceiveTypeFromEmailDialog INSTANCE = new CodeReceiveTypeFromEmailDialog();
        public static final int $stable = 8;

        private CodeReceiveTypeFromEmailDialog() {
            super("code_receive_type_from_email_dialog", null, ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1561613677$authorizationsdk_release(), 2, null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof CodeReceiveTypeFromEmailDialog);
        }

        public int hashCode() {
            return -414553847;
        }

        @NotNull
        public String toString() {
            return "CodeReceiveTypeFromEmailDialog";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$CreateCloud;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CreateCloud;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CreateCloud extends Dest<Screen.CreateCloud> {

        @NotNull
        public static final CreateCloud INSTANCE = new CreateCloud();
        public static final int $stable = 8;

        private CreateCloud() {
            super("create_cloud", CollectionsKt.emptyList(), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1565047870$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof CreateCloud);
        }

        public int hashCode() {
            return 440814394;
        }

        @NotNull
        public String toString() {
            return "CreateCloud";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$CustomServer;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CustomServer;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CustomServer extends Dest<Screen.CustomServer> {

        @NotNull
        public static final CustomServer INSTANCE = new CustomServer();
        public static final int $stable = 8;

        private CustomServer() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument(CustomServerViewModel.CUSTOM_SERVER_LOGIN, navType, false, null, new Function1() { // from class: d9.o2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CustomServer._init_$lambda$0((Screen.CustomServer) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument(CustomServerViewModel.CUSTOM_SERVER_PASSWORD, navType, false, null, new Function1() { // from class: d9.p2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CustomServer._init_$lambda$1((Screen.CustomServer) obj);
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument(CustomServerViewModel.CUSTOM_SERVER_SERVICE_TYPE, navType, false, null, new Function1() { // from class: d9.q2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.CustomServer._init_$lambda$2((Screen.CustomServer) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("customServer", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument(CustomServerViewModel.CUSTOM_SERVER_NEED_CAPTCHA, navType2, false, null, new Function1() { // from class: d9.r2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CustomServer._init_$lambda$3((Screen.CustomServer) obj));
                }
            }, 8, null), new NavArgument(CustomServerViewModel.CUSTOM_SERVER_IS_FOR_IMAP_ONLY, navType2, false, null, new Function1() { // from class: d9.s2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.CustomServer._init_$lambda$4((Screen.CustomServer) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1542213347$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.CustomServer NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getLogin();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.CustomServer NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getPassword();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.CustomServer NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.CustomServer NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getNeedCaptcha();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.CustomServer NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isForImapOnly();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof CustomServer);
        }

        public int hashCode() {
            return 84009363;
        }

        @NotNull
        public String toString() {
            return "CustomServer";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$EnterEmailCode;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterEmailCode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterEmailCode extends Dest<Screen.EnterEmailCode> {

        @NotNull
        public static final EnterEmailCode INSTANCE = new EnterEmailCode();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private EnterEmailCode() {
            NavType<Boolean> navType = NavType.BoolType;
            super("enter_email_code", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(EnterEmailCodeViewModel.SHOULD_SWITCH_TO_SMS, navType, false, null, new Function1() { // from class: d9.t2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.EnterEmailCode._init_$lambda$0((Screen.EnterEmailCode) obj));
                }
            }, 8, null), new NavArgument("SHOULD_RESEND_CODE", navType, false, null, new Function1() { // from class: d9.u2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.EnterEmailCode._init_$lambda$1((Screen.EnterEmailCode) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14805getLambda$932136858$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$0(Screen.EnterEmailCode NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getShouldSwitchToSms();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$1(Screen.EnterEmailCode NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getShouldResendCode();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof EnterEmailCode);
        }

        public int hashCode() {
            return -1275462800;
        }

        @NotNull
        public String toString() {
            return "EnterEmailCode";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$EnterEmailCodeAfterListAcc;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterEmailCodeAfterListAcc;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterEmailCodeAfterListAcc extends Dest<Screen.EnterEmailCodeAfterListAcc> {

        @NotNull
        public static final EnterEmailCodeAfterListAcc INSTANCE = new EnterEmailCodeAfterListAcc();
        public static final int $stable = 8;

        private EnterEmailCodeAfterListAcc() {
            super("enter_email_code_after_list_acc", CollectionsKt.listOf(new NavArgument("SHOULD_RESEND_CODE", NavType.BoolType, false, null, new Function1() { // from class: d9.v2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.EnterEmailCodeAfterListAcc._init_$lambda$0((Screen.EnterEmailCodeAfterListAcc) obj));
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1446084511$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$0(Screen.EnterEmailCodeAfterListAcc NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getShouldResendCode();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof EnterEmailCodeAfterListAcc);
        }

        public int hashCode() {
            return 2130988311;
        }

        @NotNull
        public String toString() {
            return "EnterEmailCodeAfterListAcc";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$EnterPhone;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterPhone;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterPhone extends Dest<Screen.EnterPhone> {

        @NotNull
        public static final EnterPhone INSTANCE = new EnterPhone();
        public static final int $stable = 8;

        private EnterPhone() {
            super("enter_phone", CollectionsKt.emptyList(), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1547073217$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof EnterPhone);
        }

        public int hashCode() {
            return 29684917;
        }

        @NotNull
        public String toString() {
            return "EnterPhone";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$EnterPhoneCode;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterPhoneCode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterPhoneCode extends Dest<Screen.EnterPhoneCode> {

        @NotNull
        public static final EnterPhoneCode INSTANCE = new EnterPhoneCode();
        public static final int $stable = 8;

        private EnterPhoneCode() {
            super("enter_phone_code", CollectionsKt.listOf(new NavArgument(EnterPhoneCodeViewModel.CODE_RECEIVE_TYPE, NavType.IntType, false, null, new Function1() { // from class: d9.w2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(Dest.EnterPhoneCode._init_$lambda$0((Screen.EnterPhoneCode) obj));
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$491320532$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int _init_$lambda$0(Screen.EnterPhoneCode NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getCodeReceiveType();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof EnterPhoneCode);
        }

        public int hashCode() {
            return -129911742;
        }

        @NotNull
        public String toString() {
            return "EnterPhoneCode";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$EsiaAuthScreen;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EsiaAuthScreen;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EsiaAuthScreen extends Dest<Screen.EsiaAuthScreen> {

        @NotNull
        public static final EsiaAuthScreen INSTANCE = new EsiaAuthScreen();
        public static final int $stable = 8;

        private EsiaAuthScreen() {
            super("esiaauthscreen", CollectionsKt.listOf(new NavArgument(EsiaViewModel.ESIA_SCREEN_URL_KEY, NavType.StringType, false, null, new Function1() { // from class: d9.x2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.EsiaAuthScreen._init_$lambda$0((Screen.EsiaAuthScreen) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14789getLambda$1555034211$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.EsiaAuthScreen NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getUrl();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof EsiaAuthScreen);
        }

        public int hashCode() {
            return -493224231;
        }

        @NotNull
        public String toString() {
            return "EsiaAuthScreen";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$ExternalAccMigration;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ExternalAccMigration;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ExternalAccMigration extends Dest<Screen.ExternalAccMigration> {

        @NotNull
        public static final ExternalAccMigration INSTANCE = new ExternalAccMigration();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private ExternalAccMigration() {
            NavType<String> navType = NavType.StringType;
            super("external_account_migration", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(ExternalAccMigrationViewModel.MODE, navType, false, null, new Function1() { // from class: d9.y2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.ExternalAccMigration._init_$lambda$0((Screen.ExternalAccMigration) obj);
                }
            }, 8, null), new NavArgument(ExternalAccMigrationViewModel.EMAIL, navType, false, null, new Function1() { // from class: d9.z2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.ExternalAccMigration._init_$lambda$1((Screen.ExternalAccMigration) obj);
                }
            }, 8, null), new NavArgument(ExternalAccMigrationViewModel.CHANGE_INSETS, NavType.BoolType, false, null, new Function1() { // from class: d9.a3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.ExternalAccMigration._init_$lambda$2((Screen.ExternalAccMigration) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14796getLambda$2075530305$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.ExternalAccMigration NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getMode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.ExternalAccMigration NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$2(Screen.ExternalAccMigration NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isNeedChangeInsets();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof ExternalAccMigration);
        }

        public int hashCode() {
            return -1381588937;
        }

        @NotNull
        public String toString() {
            return "ExternalAccMigration";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$GoogleNative;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$GoogleNative;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class GoogleNative extends Dest<Screen.GoogleNative> {

        @NotNull
        public static final GoogleNative INSTANCE = new GoogleNative();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private GoogleNative() {
            NavType<String> navType = NavType.StringType;
            super("google_native", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument("GOOGLE_LOGIN_HINT", navType, false, null, new Function1() { // from class: d9.b3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.GoogleNative._init_$lambda$0((Screen.GoogleNative) obj);
                }
            }, 8, null), new NavArgument("GOOGLE_XMAIL_MIGRATION_FROM", navType, false, null, new Function1() { // from class: d9.c3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.GoogleNative._init_$lambda$1((Screen.GoogleNative) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14788getLambda$1226346809$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.GoogleNative NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHint();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.GoogleNative NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getXmailMigrationFrom();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof GoogleNative);
        }

        public int hashCode() {
            return 1714921263;
        }

        @NotNull
        public String toString() {
            return "GoogleNative";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$GoogleWeb;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$GoogleWeb;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class GoogleWeb extends Dest<Screen.GoogleWeb> {

        @NotNull
        public static final GoogleWeb INSTANCE = new GoogleWeb();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private GoogleWeb() {
            NavType<String> navType = NavType.StringType;
            super("google_web", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument("GOOGLE_LOGIN_HINT", navType, false, null, new Function1() { // from class: d9.d3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.GoogleWeb._init_$lambda$0((Screen.GoogleWeb) obj);
                }
            }, 8, null), new NavArgument("GOOGLE_XMAIL_MIGRATION_FROM", navType, false, null, new Function1() { // from class: d9.e3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.GoogleWeb._init_$lambda$1((Screen.GoogleWeb) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14804getLambda$913761348$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.GoogleWeb NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHint();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.GoogleWeb NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getXmailMigrationFrom();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof GoogleWeb);
        }

        public int hashCode() {
            return -575892676;
        }

        @NotNull
        public String toString() {
            return "GoogleWeb";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Login;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Login;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Login extends Dest<Screen.Login> {

        @NotNull
        public static final Login INSTANCE = new Login();
        public static final int $stable = 8;

        private Login() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument("login_screen_email", navType, true, null, new Function1() { // from class: d9.f3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Login._init_$lambda$0((Screen.Login) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument("LOGIN_FROM", navType, false, null, new Function1() { // from class: d9.g3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Login._init_$lambda$1((Screen.Login) obj);
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument("LOGIN_SERVICE_TYPE", navType, false, null, new Function1() { // from class: d9.h3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Login._init_$lambda$2((Screen.Login) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("Login", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument("is_maual_logout", navType2, false, null, new Function1() { // from class: d9.i3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.Login._init_$lambda$3((Screen.Login) obj));
                }
            }, 8, null), new NavArgument("is_deeplink_opened", navType2, false, null, new Function1() { // from class: d9.j3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.Login._init_$lambda$4((Screen.Login) obj));
                }
            }, 8, null), new NavArgument("IS_NEED_START_RESTORE", navType2, false, null, new Function1() { // from class: d9.k3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.Login._init_$lambda$5((Screen.Login) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14786getLambda$1144860306$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.Login NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.Login NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.Login NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.Login NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isManualLogout();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.Login NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isDeeplinkOpened();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$5(Screen.Login NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isNeedStartRestore();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Login);
        }

        public int hashCode() {
            return -1746469686;
        }

        @NotNull
        public String toString() {
            return "Login";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$LoginBindFlow;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$LoginBindFlow;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class LoginBindFlow extends Dest<Screen.LoginBindFlow> {

        @NotNull
        public static final LoginBindFlow INSTANCE = new LoginBindFlow();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private LoginBindFlow() {
            NavType<String> navType = NavType.StringType;
            super("LoginBindFlow", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(LoginBindFlowViewModel.LOGIN_BIND_FLOW_EMAIL, navType, true, null, new Function1() { // from class: d9.l3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginBindFlow._init_$lambda$0((Screen.LoginBindFlow) obj);
                }
            }, 8, null), new NavArgument(LoginBindFlowViewModel.LOGIN_BIND_FLOW_FROM, navType, false, null, new Function1() { // from class: d9.m3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginBindFlow._init_$lambda$1((Screen.LoginBindFlow) obj);
                }
            }, 8, null), new NavArgument(LoginBindFlowViewModel.LOGIN_BIND_FLOW_SERVICE_TYPE, navType, false, null, new Function1() { // from class: d9.n3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginBindFlow._init_$lambda$2((Screen.LoginBindFlow) obj);
                }
            }, 8, null), new NavArgument(LoginBindFlowViewModel.LOGIN_BIND_FLOW_BIND_TOKEN, navType, false, null, new Function1() { // from class: d9.o3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginBindFlow._init_$lambda$3((Screen.LoginBindFlow) obj);
                }
            }, 8, null), new NavArgument(LoginBindFlowViewModel.LOGIN_BIND_FLOW_BIND_TYPE, navType, false, null, new Function1() { // from class: d9.p3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginBindFlow._init_$lambda$4((Screen.LoginBindFlow) obj);
                }
            }, 8, null), new NavArgument(LoginBindFlowViewModel.LOGIN_BIND_FLOW_VK_ACCESS_TOKEN, navType, false, null, new Function1() { // from class: d9.q3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginBindFlow._init_$lambda$5((Screen.LoginBindFlow) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1467042083$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.LoginBindFlow NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.LoginBindFlow NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.LoginBindFlow NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$3(Screen.LoginBindFlow NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getBindToken();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$4(Screen.LoginBindFlow NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSocialBindType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$5(Screen.LoginBindFlow NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getVkAccessToken();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof LoginBindFlow);
        }

        public int hashCode() {
            return -385996299;
        }

        @NotNull
        public String toString() {
            return "LoginBindFlow";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$LoginVk;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$LoginVk;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class LoginVk extends Dest<Screen.LoginVk> {

        @NotNull
        public static final LoginVk INSTANCE = new LoginVk();
        public static final int $stable = 8;

        private LoginVk() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument("login_screen_email", navType, true, null, new Function1() { // from class: d9.r3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginVk._init_$lambda$0((Screen.LoginVk) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument("LOGIN_FROM", navType, false, null, new Function1() { // from class: d9.s3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginVk._init_$lambda$1((Screen.LoginVk) obj);
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument("LOGIN_SERVICE_TYPE", navType, false, null, new Function1() { // from class: d9.t3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.LoginVk._init_$lambda$2((Screen.LoginVk) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("LoginVk", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument("is_maual_logout", navType2, false, null, new Function1() { // from class: d9.u3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.LoginVk._init_$lambda$3((Screen.LoginVk) obj));
                }
            }, 8, null), new NavArgument("is_deeplink_opened", navType2, false, null, new Function1() { // from class: d9.v3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.LoginVk._init_$lambda$4((Screen.LoginVk) obj));
                }
            }, 8, null), new NavArgument("IS_NEED_START_RESTORE", navType2, false, null, new Function1() { // from class: d9.w3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.LoginVk._init_$lambda$5((Screen.LoginVk) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14785getLambda$1142700295$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.LoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.LoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.LoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.LoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isManualLogout();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.LoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isDeeplinkOpened();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$5(Screen.LoginVk NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isNeedStartRestore();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof LoginVk);
        }

        public int hashCode() {
            return 974847263;
        }

        @NotNull
        public String toString() {
            return "LoginVk";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$MrimDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$MrimDialog;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MrimDialog extends Dest<Screen.MrimDialog> {

        @NotNull
        public static final MrimDialog INSTANCE = new MrimDialog();
        public static final int $stable = 8;

        private MrimDialog() {
            super("user_blocked_dialog", CollectionsKt.listOf(new NavArgument(MrimDialogViewModel.USER_BLOCKED_LOGIN, NavType.StringType, false, null, new Function1() { // from class: d9.x3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.MrimDialog._init_$lambda$0((Screen.MrimDialog) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$853049318$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.MrimDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof MrimDialog);
        }

        public int hashCode() {
            return -662353040;
        }

        @NotNull
        public String toString() {
            return "MrimDialog";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$NotReceivedCodeDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$NotReceivedCodeDialog;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NotReceivedCodeDialog extends Dest<Screen.NotReceivedCodeDialog> {

        @NotNull
        public static final NotReceivedCodeDialog INSTANCE = new NotReceivedCodeDialog();
        public static final int $stable = 8;

        private NotReceivedCodeDialog() {
            NavType<Integer> navType = NavType.IntType;
            NavArgument navArgument = new NavArgument(NotReceivedCodeBottomSheetViewModel.NOT_RECEIVED_CODE_SOURCE, navType, false, null, new Function1() { // from class: d9.y3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(Dest.NotReceivedCodeDialog._init_$lambda$0((Screen.NotReceivedCodeDialog) obj));
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument(NotReceivedCodeBottomSheetViewModel.NOT_RECEIVED_CODE_RECEIVE_TYPE, navType, false, null, new Function1() { // from class: d9.z3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Integer.valueOf(Dest.NotReceivedCodeDialog._init_$lambda$1((Screen.NotReceivedCodeDialog) obj));
                }
            }, 8, null);
            NavType<Long> navType2 = NavType.LongType;
            super("not_received_code_dialog", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, new NavArgument(NotReceivedCodeBottomSheetViewModel.NOT_RECEIVED_CODE_DELAY_FROM, navType2, false, null, new Function1() { // from class: d9.a4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Long.valueOf(Dest.NotReceivedCodeDialog._init_$lambda$2((Screen.NotReceivedCodeDialog) obj));
                }
            }, 8, null), new NavArgument(NotReceivedCodeBottomSheetViewModel.NOT_RECEIVED_CODE_DELAY_MILLIS, navType2, false, null, new Function1() { // from class: d9.b4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Long.valueOf(Dest.NotReceivedCodeDialog._init_$lambda$3((Screen.NotReceivedCodeDialog) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14792getLambda$1833743794$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int _init_$lambda$0(Screen.NotReceivedCodeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSource();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final int _init_$lambda$1(Screen.NotReceivedCodeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getReceiveType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long _init_$lambda$2(Screen.NotReceivedCodeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getDelayFrom();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long _init_$lambda$3(Screen.NotReceivedCodeDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getDelay();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof NotReceivedCodeDialog);
        }

        public int hashCode() {
            return -1884424726;
        }

        @NotNull
        public String toString() {
            return "NotReceivedCodeDialog";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$OKAuth;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$OKAuth;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OKAuth extends Dest<Screen.OKAuth> {

        @NotNull
        public static final OKAuth INSTANCE = new OKAuth();
        public static final int $stable = 8;

        private OKAuth() {
            super("OK_auth", CollectionsKt.emptyList(), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14787getLambda$1152472717$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof OKAuth);
        }

        public int hashCode() {
            return 1745535043;
        }

        @NotNull
        public String toString() {
            return "OKAuth";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$OneTimeCode;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$OneTimeCode;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OneTimeCode extends Dest<Screen.OneTimeCode> {

        @NotNull
        public static final OneTimeCode INSTANCE = new OneTimeCode();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private OneTimeCode() {
            NavType<String> navType = NavType.StringType;
            super("one_time_code", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument("login", navType, false, null, new Function1() { // from class: d9.c4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.OneTimeCode._init_$lambda$0((Screen.OneTimeCode) obj);
                }
            }, 8, null), new NavArgument(OneTimeCodeViewModel.ANALYTICS_ONE_TIME_FROM, navType, false, null, new Function1() { // from class: d9.d4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.OneTimeCode._init_$lambda$1((Screen.OneTimeCode) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14798getLambda$339650729$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.OneTimeCode NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.OneTimeCode NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFrom();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof OneTimeCode);
        }

        public int hashCode() {
            return -956804223;
        }

        @NotNull
        public String toString() {
            return "OneTimeCode";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Outlook;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Outlook;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Outlook extends Dest<Screen.Outlook> {

        @NotNull
        public static final Outlook INSTANCE = new Outlook();
        public static final int $stable = 8;

        private Outlook() {
            super("outlook_web_auth", CollectionsKt.listOf(new NavArgument(OutlookConstants.OUTLOOK_LOGIN_HINT, NavType.StringType, false, null, new Function1() { // from class: d9.e4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Outlook._init_$lambda$0((Screen.Outlook) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$304668778$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.Outlook NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHint();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Outlook);
        }

        public int hashCode() {
            return -473737202;
        }

        @NotNull
        public String toString() {
            return "Outlook";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Password;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Password;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Password extends Dest<Screen.Password> {

        @NotNull
        public static final Password INSTANCE = new Password();
        public static final int $stable = 8;

        private Password() {
            NavType<String> navType = NavType.StringType;
            NavArgument navArgument = new NavArgument(PasswordViewModel.PS_EMAIL, navType, false, null, new Function1() { // from class: d9.f4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Password._init_$lambda$0((Screen.Password) obj);
                }
            }, 8, null);
            NavArgument navArgument2 = new NavArgument(PasswordViewModel.PS_PASSWORD, navType, false, null, new Function1() { // from class: d9.g4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Password._init_$lambda$1((Screen.Password) obj);
                }
            }, 8, null);
            NavArgument navArgument3 = new NavArgument(PasswordViewModel.PS_SERVICE_TYPE, navType, false, null, new Function1() { // from class: d9.h4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Password._init_$lambda$2((Screen.Password) obj);
                }
            }, 8, null);
            NavType<Boolean> navType2 = NavType.BoolType;
            super("Password", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, navArgument2, navArgument3, new NavArgument(PasswordViewModel.PS_SUPPORT_RESTORE, navType2, false, null, new Function1() { // from class: d9.i4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.Password._init_$lambda$3((Screen.Password) obj));
                }
            }, 8, null), new NavArgument(PasswordViewModel.PS_FORCE_START_AUTH_IMMEDIATE, navType2, false, null, new Function1() { // from class: d9.j4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.Password._init_$lambda$4((Screen.Password) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$352927164$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.Password NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.Password NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getPassword();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.Password NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getServiceType();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.Password NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isSupportRestore();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$4(Screen.Password NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getStartAuthImmediate();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Password);
        }

        public int hashCode() {
            return -650271334;
        }

        @NotNull
        public String toString() {
            return "Password";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$RegistrationMain;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RegistrationMain;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RegistrationMain extends Dest<Screen.RegistrationMain> {

        @NotNull
        public static final RegistrationMain INSTANCE = new RegistrationMain();
        public static final int $stable = 8;

        private RegistrationMain() {
            super("registration_main", RegNavGraphKt.regNavArguments(), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14803getLambda$802796635$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof RegistrationMain);
        }

        public int hashCode() {
            return 1895291409;
        }

        @NotNull
        public String toString() {
            return "RegistrationMain";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$RestorePassword;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RestorePassword;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RestorePassword extends Dest<Screen.RestorePassword> {

        @NotNull
        public static final RestorePassword INSTANCE = new RestorePassword();
        public static final int $stable = 8;

        private RestorePassword() {
            NavArgument navArgument = new NavArgument(RestorePasswordViewModel.RPS_EMAIL, NavType.StringType, false, null, new Function1() { // from class: d9.k4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.RestorePassword._init_$lambda$0((Screen.RestorePassword) obj);
                }
            }, 8, null);
            NavType<Boolean> navType = NavType.BoolType;
            super("RestorePassword", CollectionsKt.listOf((Object[]) new NavArgument[]{navArgument, new NavArgument(RestorePasswordViewModel.IS_RESTORE_VKID_ENABLED, navType, false, null, new Function1() { // from class: d9.l4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.RestorePassword._init_$lambda$1((Screen.RestorePassword) obj));
                }
            }, 8, null), new NavArgument(RestorePasswordViewModel.IS_REBIND, navType, false, null, new Function1() { // from class: d9.m4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.RestorePassword._init_$lambda$2((Screen.RestorePassword) obj));
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14794getLambda$1973063282$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.RestorePassword NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$1(Screen.RestorePassword NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isRestoreVkidEnabled();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$2(Screen.RestorePassword NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isRebind();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof RestorePassword);
        }

        public int hashCode() {
            return 17763178;
        }

        @NotNull
        public String toString() {
            return "RestorePassword";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$RestoreVkId;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RestoreVkId;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RestoreVkId extends Dest<Screen.RestoreVkId> {

        @NotNull
        public static final RestoreVkId INSTANCE = new RestoreVkId();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private RestoreVkId() {
            NavType<String> navType = NavType.StringType;
            super("RestoreVkId", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(RestoreVkViewModel.EMAIL_FOR_RESTORE_KEY, navType, true, null, new Function1() { // from class: d9.n4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.RestoreVkId._init_$lambda$0((Screen.RestoreVkId) obj);
                }
            }, 8, null), new NavArgument(RestoreVkViewModel.RESTORE_SOURCE_KEY, navType, false, null, new Function1() { // from class: d9.o4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.RestoreVkId._init_$lambda$1((Screen.RestoreVkId) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1220307097$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.RestoreVkId NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.RestoreVkId NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSource();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof RestoreVkId);
        }

        public int hashCode() {
            return 1290489599;
        }

        @NotNull
        public String toString() {
            return "RestoreVkId";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$SSO;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SSO;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SSO extends Dest<Screen.SSO> {

        @NotNull
        public static final SSO INSTANCE = new SSO();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private SSO() {
            NavType<String> navType = NavType.StringType;
            super("sso", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(SSOViewModel.SSO_LOGIN, navType, false, null, new Function1() { // from class: d9.p4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.SSO._init_$lambda$0((Screen.SSO) obj);
                }
            }, 8, null), new NavArgument(SSOViewModel.SSO_URL, navType, false, null, new Function1() { // from class: d9.q4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.SSO._init_$lambda$1((Screen.SSO) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14802getLambda$657298296$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.SSO NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getLogin();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.SSO NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getUrl();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof SSO);
        }

        public int hashCode() {
            return -1548178512;
        }

        @NotNull
        public String toString() {
            return "SSO";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$SecondFactor;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SecondFactor;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SecondFactor extends Dest<Screen.SecondFactor> {

        @NotNull
        public static final SecondFactor INSTANCE = new SecondFactor();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private SecondFactor() {
            NavType<String> navType = NavType.StringType;
            super("second_factor", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(SecondStepViewModel.SECOND_FACTOR_LOGIN, navType, false, null, new Function1() { // from class: d9.r4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.SecondFactor._init_$lambda$0((Screen.SecondFactor) obj);
                }
            }, 8, null), new NavArgument(SecondStepViewModel.SECOND_FACTOR_URL, navType, false, null, new Function1() { // from class: d9.s4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.SecondFactor._init_$lambda$1((Screen.SecondFactor) obj);
                }
            }, 8, null), new NavArgument(SecondStepViewModel.SECOND_FACTOR_COOKIE_HEADER, navType, true, null, new Function1() { // from class: d9.t4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.SecondFactor._init_$lambda$2((Screen.SecondFactor) obj);
                }
            }, 8, null), new NavArgument(SecondStepViewModel.SECOND_FACTOR_IS_XMAIL_MIGRATION, NavType.BoolType, false, null, new Function1() { // from class: d9.u4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Dest.SecondFactor._init_$lambda$3((Screen.SecondFactor) obj));
                }
            }, 8, null), new NavArgument(SecondStepViewModel.SECOND_FACTOR_XMAIL_FROM_PARAM, navType, true, null, new Function1() { // from class: d9.v4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.SecondFactor._init_$lambda$4((Screen.SecondFactor) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1790925428$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.SecondFactor NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getLogin();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.SecondFactor NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getUrl();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$2(Screen.SecondFactor NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getSecondStepCookieHeader();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean _init_$lambda$3(Screen.SecondFactor NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.isXmailMigration();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$4(Screen.SecondFactor NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getParamXmailFrom();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof SecondFactor);
        }

        public int hashCode() {
            return -1384360350;
        }

        @NotNull
        public String toString() {
            return "SecondFactor";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Start;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Start;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Start extends Dest<Screen.Start> {

        @NotNull
        public static final Start INSTANCE = new Start();
        public static final int $stable = 8;

        private Start() {
            super("start", null, ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$129949973$authorizationsdk_release(), 2, null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Start);
        }

        public int hashCode() {
            return -1739861565;
        }

        @NotNull
        public String toString() {
            return "Start";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$UnblockUser;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$UnblockUser;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnblockUser extends Dest<Screen.UnblockUser> {

        @NotNull
        public static final UnblockUser INSTANCE = new UnblockUser();
        public static final int $stable = 8;

        private UnblockUser() {
            super("unblockUser", CollectionsKt.listOf(new NavArgument("FAIL_URL", NavType.StringType, false, null, new Function1() { // from class: d9.w4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.UnblockUser._init_$lambda$0((Screen.UnblockUser) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1896364312$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.UnblockUser NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getFailUrl();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof UnblockUser);
        }

        public int hashCode() {
            return 1901405216;
        }

        @NotNull
        public String toString() {
            return "UnblockUser";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$VkBindInLogin;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkBindInLogin;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VkBindInLogin extends Dest<Screen.VkBindInLogin> {

        @NotNull
        public static final VkBindInLogin INSTANCE = new VkBindInLogin();
        public static final int $stable = 8;

        private VkBindInLogin() {
            super("vk_bind_in_login", CollectionsKt.listOf(new NavArgument(VkBindInLoginViewModel.VK_BIND_LOGIN, NavType.StringType, false, null, new Function1() { // from class: d9.x4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.VkBindInLogin._init_$lambda$0((Screen.VkBindInLogin) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$1242947365$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.VkBindInLogin NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getLogin();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof VkBindInLogin);
        }

        public int hashCode() {
            return -1003951949;
        }

        @NotNull
        public String toString() {
            return "VkBindInLogin";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$VkPassword;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkPassword;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VkPassword extends Dest<Screen.VkPassword> {

        @NotNull
        public static final VkPassword INSTANCE = new VkPassword();
        public static final int $stable = 8;

        /* JADX WARN: Illegal instructions before constructor call */
        private VkPassword() {
            NavType<String> navType = NavType.StringType;
            super("vkPassword", CollectionsKt.listOf((Object[]) new NavArgument[]{new NavArgument(VkPasswordViewModel.VK_PASSWORD_LOGIN, navType, false, null, new Function1() { // from class: d9.y4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.VkPassword._init_$lambda$0((Screen.VkPassword) obj);
                }
            }, 8, null), new NavArgument(VkPasswordViewModel.VK_PASSWORD_URL, navType, false, null, new Function1() { // from class: d9.z4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.VkPassword._init_$lambda$1((Screen.VkPassword) obj);
                }
            }, 8, null)}), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$428446215$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.VkPassword NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getLogin();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$1(Screen.VkPassword NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getUrl();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof VkPassword);
        }

        public int hashCode() {
            return 306040751;
        }

        @NotNull
        public String toString() {
            return "VkPassword";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$WrongVkidAccountDialog;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$WrongVkidAccountDialog;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class WrongVkidAccountDialog extends Dest<Screen.WrongVkidAccountDialog> {

        @NotNull
        public static final WrongVkidAccountDialog INSTANCE = new WrongVkidAccountDialog();
        public static final int $stable = 8;

        private WrongVkidAccountDialog() {
            super("wrong_vk_id_account_dialog", CollectionsKt.listOf(new NavArgument(WrongVkidAccountViewModel.WRONG_VK_ID_USER_EMAIL, NavType.StringType, false, null, new Function1() { // from class: d9.a5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.WrongVkidAccountDialog._init_$lambda$0((Screen.WrongVkidAccountDialog) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14791getLambda$1705611137$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.WrongVkidAccountDialog NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getEmail();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof WrongVkidAccountDialog);
        }

        public int hashCode() {
            return 1304459191;
        }

        @NotNull
        public String toString() {
            return "WrongVkidAccountDialog";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Yahoo;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Yahoo;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Yahoo extends Dest<Screen.Yahoo> {

        @NotNull
        public static final Yahoo INSTANCE = new Yahoo();
        public static final int $stable = 8;

        private Yahoo() {
            super("yahoo_web_auth", CollectionsKt.listOf(new NavArgument(YahooViewModel.YAHOO_LOGIN_HINT, NavType.StringType, false, null, new Function1() { // from class: d9.b5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Yahoo._init_$lambda$0((Screen.Yahoo) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14790getLambda$166951433$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.Yahoo NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHint();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Yahoo);
        }

        public int hashCode() {
            return -1734879839;
        }

        @NotNull
        public String toString() {
            return "Yahoo";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$Yandex;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Yandex;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Yandex extends Dest<Screen.Yandex> {

        @NotNull
        public static final Yandex INSTANCE = new Yandex();
        public static final int $stable = 8;

        private Yandex() {
            super("yandex_web_auth", CollectionsKt.listOf(new NavArgument(YandexConstants.YANDEX_LOGIN_HINT, NavType.StringType, false, null, new Function1() { // from class: d9.c5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return Dest.Yandex._init_$lambda$0((Screen.Yandex) obj);
                }
            }, 8, null)), ComposableSingletons$AuthNavGraphKt.INSTANCE.getLambda$641629222$authorizationsdk_release(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String _init_$lambda$0(Screen.Yandex NavArgument) {
            Intrinsics.checkNotNullParameter(NavArgument, "$this$NavArgument");
            return NavArgument.getHint();
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof Yandex);
        }

        public int hashCode() {
            return 2053467824;
        }

        @NotNull
        public String toString() {
            return "Yandex";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bHÖ\u0003J\t\u0010\t\u001a\u00020\nHÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/navigation/auth/Dest$YandexHelp;", "Lru/mail/authorizationsdk/navigation/auth/Dest;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$YandexHelp;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class YandexHelp extends Dest<Screen.YandexHelp> {

        @NotNull
        public static final YandexHelp INSTANCE = new YandexHelp();
        public static final int $stable = 8;

        private YandexHelp() {
            super("yandex_help", CollectionsKt.emptyList(), ComposableSingletons$AuthNavGraphKt.INSTANCE.m14797getLambda$225966363$authorizationsdk_release(), null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof YandexHelp);
        }

        public int hashCode() {
            return -674178543;
        }

        @NotNull
        public String toString() {
            return "YandexHelp";
        }
    }

    public /* synthetic */ Dest(String str, List list, Function5 function5, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, function5);
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getContent() {
        return this.content;
    }

    @Override // ru.mail.authorizationsdk.navigation.DestBase
    @NotNull
    public String getDest() {
        return this.dest;
    }

    @NotNull
    public final List<NavArgument<S, ?>> getNavArguments() {
        return this.navArguments;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Dest(String str, List<? extends NavArgument<S, ?>> list, Function5<? super NavBackStackEntry, ? super Function2<? super String, ? super Bundle, Unit>, ? super AuthorizeSdkComponent, ? super Composer, ? super Integer, Unit> function5) {
        super(str, list);
        this.dest = str;
        this.navArguments = list;
        this.content = function5;
    }

    public /* synthetic */ Dest(String str, List list, Function5 function5, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? CollectionsKt.emptyList() : list, function5, null);
    }
}
