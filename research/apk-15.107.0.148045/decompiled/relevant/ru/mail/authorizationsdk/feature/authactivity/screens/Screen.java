package ru.mail.authorizationsdk.feature.authactivity.screens;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.DrawableRes;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import com.vk.search.cities.VkCitySelectFragment;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.di.VeryBadTemporaryMediator;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDResult;
import ru.mail.authorizationsdk.feature.bindemail.presentation.BindEmailResult;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult;
import ru.mail.authorizationsdk.feature.changepassword.presentation.ChangePasswordResult;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerResult;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneResult;
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
import ru.mail.authorizationsdk.feature.registration.domain.model.KnownFieldsValues;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainResult;
import ru.mail.authorizationsdk.feature.registration.presentation.screen.parentselection.ParentSelectionResult;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordResult;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkResult;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepResult;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.ChoiceAccountResult;
import ru.mail.authorizationsdk.feature.socialauth.domain.SocialAuthInitMode;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.EsiaScreenResult;
import ru.mail.authorizationsdk.feature.socialauth.presentation.SocialAuthResult;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOResult;
import ru.mail.authorizationsdk.feature.unblockuser.presentation.UnblockUserResult;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginResult;
import ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.VkFragmentSupportResult;
import ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.model.VkFragmentMode;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.WrongVkidAccountResult;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordResult;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.authorizationsdk.feature.yandexhelp.YandexHelpResult;
import ru.mail.cloud.app.data.openapi.File;
import ru.mail.data.entities.Collector;
import ru.mail.kotlett.runtime.divkit.InterpolatorFields;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000Ü\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:-\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?@ABCDB\t\b\u0004¢\u0006\u0004\b\u0003\u0010\u0004J\u0013\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00028\u0000¢\u0006\u0002\u0010\u0014R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000bR\u0012\u0010\u000f\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u001a\u0010\u0010\u001a\u0004\u0018\u00018\u0000X¦\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014\u0082\u0001-EFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopq¨\u0006r"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "R", "Landroid/os/Parcelable;", "<init>", "()V", "id", "", "getId", "()Ljava/lang/String;", "isInternalNavigation", "", "()Z", "setInternalNavigation", "(Z)V", "isSupportBack", "isReplace", "result", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "onResult", "", "res", "Start", "EsiaAuthScreen", "BindEmail", "Captcha", "OneTimeCode", "SSO", "VkPassword", "Yahoo", "Outlook", "OKAuth", "Yandex", "YandexHelp", "MrimDialog", "GoogleNative", "GoogleWeb", "SecondFactor", "CloudLoginVk", "LoginVk", "Login", "CloudLogin", "Password", "LoginBindFlow", "RestorePassword", "RestoreVkId", "VkFragmentSupport", "ExternalAccMigration", "SocialAuth", "BeforeRecoveryVKID", "UnblockUser", "ChangePassword", "CustomServer", "RegistrationMain", "ParentSelection", "VkBindInLogin", "ChoiceAccount", "EnterPhone", "EnterPhoneCode", "CreateCloud", "EnterEmailCode", "AccountList", "EnterEmailCodeAfterListAcc", "CodeReceiveTypeDialog", "CodeReceiveTypeFromEmailDialog", "NotReceivedCodeDialog", "WrongVkidAccountDialog", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$AccountList;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$BeforeRecoveryVKID;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$BindEmail;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Captcha;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChangePassword;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChoiceAccount;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CloudLogin;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CloudLoginVk;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CodeReceiveTypeDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CodeReceiveTypeFromEmailDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CreateCloud;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CustomServer;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterEmailCode;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterEmailCodeAfterListAcc;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterPhone;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterPhoneCode;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EsiaAuthScreen;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ExternalAccMigration;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$GoogleNative;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$GoogleWeb;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Login;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$LoginBindFlow;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$LoginVk;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$MrimDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$NotReceivedCodeDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$OKAuth;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$OneTimeCode;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Outlook;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ParentSelection;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Password;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RegistrationMain;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RestorePassword;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RestoreVkId;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SSO;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SecondFactor;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SocialAuth;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Start;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$UnblockUser;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkBindInLogin;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkFragmentSupport;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkPassword;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$WrongVkidAccountDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Yahoo;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Yandex;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$YandexHelp;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class Screen<R> implements Parcelable {
    public static final int $stable = 8;
    private boolean isInternalNavigation;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u001f\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$AccountList;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/accountlist/presentation/AccountListResult;", "isReplace", "", "result", "<init>", "(ZLru/mail/authorizationsdk/feature/phone/accountlist/presentation/AccountListResult;)V", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/phone/accountlist/presentation/AccountListResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/accountlist/presentation/AccountListResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AccountList extends Screen<AccountListResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private AccountListResult result;

        @NotNull
        public static final Parcelable.Creator<AccountList> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<AccountList> {
            @Override // android.os.Parcelable.Creator
            public final AccountList createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new AccountList(parcel.readInt() != 0, (AccountListResult) parcel.readParcelable(AccountList.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final AccountList[] newArray(int i10) {
                return new AccountList[i10];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AccountList() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ AccountList copy$default(AccountList accountList, boolean z10, AccountListResult accountListResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = accountList.isReplace;
            }
            if ((i10 & 2) != 0) {
                accountListResult = accountList.result;
            }
            return accountList.copy(z10, accountListResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final AccountListResult getResult() {
            return this.result;
        }

        @NotNull
        public final AccountList copy(boolean isReplace, @Nullable AccountListResult result) {
            return new AccountList(isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AccountList)) {
                return false;
            }
            AccountList accountList = (AccountList) other;
            return this.isReplace == accountList.isReplace && Intrinsics.areEqual(this.result, accountList.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.isReplace) * 31;
            AccountListResult accountListResult = this.result;
            return iHashCode + (accountListResult == null ? 0 : accountListResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "AccountList(isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public AccountList(boolean z10, @Nullable AccountListResult accountListResult) {
            super(null);
            this.isReplace = z10;
            this.result = accountListResult;
            this.isSupportBack = true;
            this.id = "AC";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public AccountListResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable AccountListResult accountListResult) {
            this.result = accountListResult;
        }

        public /* synthetic */ AccountList(boolean z10, AccountListResult accountListResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? null : accountListResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\t\u0010\u001c\u001a\u00020\bHÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\bX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u0012R\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$BeforeRecoveryVKID;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/beforerecovery/presentation/BeforeRecoveryVKIDResult;", "blockedEmail", "", "failUrl", "result", "isReplace", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/feature/beforerecovery/presentation/BeforeRecoveryVKIDResult;Z)V", "getBlockedEmail", "()Ljava/lang/String;", "getFailUrl", "getResult", "()Lru/mail/authorizationsdk/feature/beforerecovery/presentation/BeforeRecoveryVKIDResult;", "setResult", "(Lru/mail/authorizationsdk/feature/beforerecovery/presentation/BeforeRecoveryVKIDResult;)V", "()Z", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BeforeRecoveryVKID extends Screen<BeforeRecoveryVKIDResult> {

        @NotNull
        private final String blockedEmail;

        @NotNull
        private final String failUrl;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private BeforeRecoveryVKIDResult result;

        @NotNull
        public static final Parcelable.Creator<BeforeRecoveryVKID> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<BeforeRecoveryVKID> {
            @Override // android.os.Parcelable.Creator
            public final BeforeRecoveryVKID createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new BeforeRecoveryVKID(parcel.readString(), parcel.readString(), (BeforeRecoveryVKIDResult) parcel.readParcelable(BeforeRecoveryVKID.class.getClassLoader()), parcel.readInt() != 0);
            }

            @Override // android.os.Parcelable.Creator
            public final BeforeRecoveryVKID[] newArray(int i10) {
                return new BeforeRecoveryVKID[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BeforeRecoveryVKID(@NotNull String blockedEmail, @NotNull String failUrl, @Nullable BeforeRecoveryVKIDResult beforeRecoveryVKIDResult, boolean z10) {
            super(null);
            Intrinsics.checkNotNullParameter(blockedEmail, "blockedEmail");
            Intrinsics.checkNotNullParameter(failUrl, "failUrl");
            this.blockedEmail = blockedEmail;
            this.failUrl = failUrl;
            this.result = beforeRecoveryVKIDResult;
            this.isReplace = z10;
            this.isSupportBack = true;
            this.id = "b";
        }

        public static /* synthetic */ BeforeRecoveryVKID copy$default(BeforeRecoveryVKID beforeRecoveryVKID, String str, String str2, BeforeRecoveryVKIDResult beforeRecoveryVKIDResult, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = beforeRecoveryVKID.blockedEmail;
            }
            if ((i10 & 2) != 0) {
                str2 = beforeRecoveryVKID.failUrl;
            }
            if ((i10 & 4) != 0) {
                beforeRecoveryVKIDResult = beforeRecoveryVKID.result;
            }
            if ((i10 & 8) != 0) {
                z10 = beforeRecoveryVKID.isReplace;
            }
            return beforeRecoveryVKID.copy(str, str2, beforeRecoveryVKIDResult, z10);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBlockedEmail() {
            return this.blockedEmail;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFailUrl() {
            return this.failUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final BeforeRecoveryVKIDResult getResult() {
            return this.result;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @NotNull
        public final BeforeRecoveryVKID copy(@NotNull String blockedEmail, @NotNull String failUrl, @Nullable BeforeRecoveryVKIDResult result, boolean isReplace) {
            Intrinsics.checkNotNullParameter(blockedEmail, "blockedEmail");
            Intrinsics.checkNotNullParameter(failUrl, "failUrl");
            return new BeforeRecoveryVKID(blockedEmail, failUrl, result, isReplace);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BeforeRecoveryVKID)) {
                return false;
            }
            BeforeRecoveryVKID beforeRecoveryVKID = (BeforeRecoveryVKID) other;
            return Intrinsics.areEqual(this.blockedEmail, beforeRecoveryVKID.blockedEmail) && Intrinsics.areEqual(this.failUrl, beforeRecoveryVKID.failUrl) && Intrinsics.areEqual(this.result, beforeRecoveryVKID.result) && this.isReplace == beforeRecoveryVKID.isReplace;
        }

        @NotNull
        public final String getBlockedEmail() {
            return this.blockedEmail;
        }

        @NotNull
        public final String getFailUrl() {
            return this.failUrl;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.blockedEmail.hashCode() * 31) + this.failUrl.hashCode()) * 31;
            BeforeRecoveryVKIDResult beforeRecoveryVKIDResult = this.result;
            return ((iHashCode + (beforeRecoveryVKIDResult == null ? 0 : beforeRecoveryVKIDResult.hashCode())) * 31) + Boolean.hashCode(this.isReplace);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "BeforeRecoveryVKID(blockedEmail=" + this.blockedEmail + ", failUrl=" + this.failUrl + ", result=" + this.result + ", isReplace=" + this.isReplace + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.blockedEmail);
            dest.writeString(this.failUrl);
            dest.writeParcelable(this.result, flags);
            dest.writeInt(this.isReplace ? 1 : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public BeforeRecoveryVKIDResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable BeforeRecoveryVKIDResult beforeRecoveryVKIDResult) {
            this.result = beforeRecoveryVKIDResult;
        }

        public /* synthetic */ BeforeRecoveryVKID(String str, String str2, BeforeRecoveryVKIDResult beforeRecoveryVKIDResult, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? null : beforeRecoveryVKIDResult, (i10 & 8) != 0 ? false : z10);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003J=\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010!\u001a\u00020\"J\u0013\u0010#\u001a\u00020\b2\b\u0010$\u001a\u0004\u0018\u00010%HÖ\u0003J\t\u0010&\u001a\u00020\"HÖ\u0001J\t\u0010'\u001a\u00020\u0004HÖ\u0001J\u0016\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\"R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0010R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\bX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0015\u0010\u0010R\u001a\u0010\u0018\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u0017\u001a\u0004\b\u001a\u0010\r¨\u0006-"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$BindEmail;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/bindemail/presentation/BindEmailResult;", "mailToken", "", "socialBindType", "vkAccessToken", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/bindemail/presentation/BindEmailResult;)V", "getMailToken", "()Ljava/lang/String;", "getSocialBindType", "getVkAccessToken", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/bindemail/presentation/BindEmailResult;", "setResult", "(Lru/mail/authorizationsdk/feature/bindemail/presentation/BindEmailResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BindEmail extends Screen<BindEmailResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String mailToken;

        @Nullable
        private BindEmailResult result;

        @NotNull
        private final String socialBindType;

        @NotNull
        private final String vkAccessToken;

        @NotNull
        public static final Parcelable.Creator<BindEmail> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<BindEmail> {
            @Override // android.os.Parcelable.Creator
            public final BindEmail createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new BindEmail(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, (BindEmailResult) parcel.readParcelable(BindEmail.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final BindEmail[] newArray(int i10) {
                return new BindEmail[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BindEmail(@NotNull String mailToken, @NotNull String socialBindType, @NotNull String vkAccessToken, boolean z10, @Nullable BindEmailResult bindEmailResult) {
            super(null);
            Intrinsics.checkNotNullParameter(mailToken, "mailToken");
            Intrinsics.checkNotNullParameter(socialBindType, "socialBindType");
            Intrinsics.checkNotNullParameter(vkAccessToken, "vkAccessToken");
            this.mailToken = mailToken;
            this.socialBindType = socialBindType;
            this.vkAccessToken = vkAccessToken;
            this.isReplace = z10;
            this.result = bindEmailResult;
            this.isSupportBack = true;
            this.id = "EsiaBind";
        }

        public static /* synthetic */ BindEmail copy$default(BindEmail bindEmail, String str, String str2, String str3, boolean z10, BindEmailResult bindEmailResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = bindEmail.mailToken;
            }
            if ((i10 & 2) != 0) {
                str2 = bindEmail.socialBindType;
            }
            if ((i10 & 4) != 0) {
                str3 = bindEmail.vkAccessToken;
            }
            if ((i10 & 8) != 0) {
                z10 = bindEmail.isReplace;
            }
            if ((i10 & 16) != 0) {
                bindEmailResult = bindEmail.result;
            }
            BindEmailResult bindEmailResult2 = bindEmailResult;
            String str4 = str3;
            return bindEmail.copy(str, str2, str4, z10, bindEmailResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMailToken() {
            return this.mailToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final BindEmailResult getResult() {
            return this.result;
        }

        @NotNull
        public final BindEmail copy(@NotNull String mailToken, @NotNull String socialBindType, @NotNull String vkAccessToken, boolean isReplace, @Nullable BindEmailResult result) {
            Intrinsics.checkNotNullParameter(mailToken, "mailToken");
            Intrinsics.checkNotNullParameter(socialBindType, "socialBindType");
            Intrinsics.checkNotNullParameter(vkAccessToken, "vkAccessToken");
            return new BindEmail(mailToken, socialBindType, vkAccessToken, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BindEmail)) {
                return false;
            }
            BindEmail bindEmail = (BindEmail) other;
            return Intrinsics.areEqual(this.mailToken, bindEmail.mailToken) && Intrinsics.areEqual(this.socialBindType, bindEmail.socialBindType) && Intrinsics.areEqual(this.vkAccessToken, bindEmail.vkAccessToken) && this.isReplace == bindEmail.isReplace && Intrinsics.areEqual(this.result, bindEmail.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getMailToken() {
            return this.mailToken;
        }

        @NotNull
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @NotNull
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        public int hashCode() {
            int iHashCode = ((((((this.mailToken.hashCode() * 31) + this.socialBindType.hashCode()) * 31) + this.vkAccessToken.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            BindEmailResult bindEmailResult = this.result;
            return iHashCode + (bindEmailResult == null ? 0 : bindEmailResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "BindEmail(mailToken=" + this.mailToken + ", socialBindType=" + this.socialBindType + ", vkAccessToken=" + this.vkAccessToken + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.mailToken);
            dest.writeString(this.socialBindType);
            dest.writeString(this.vkAccessToken);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public BindEmailResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable BindEmailResult bindEmailResult) {
            this.result = bindEmailResult;
        }

        public /* synthetic */ BindEmail(String str, String str2, String str3, boolean z10, BindEmailResult bindEmailResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? null : bindEmailResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Captcha;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "ludwigToken", "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;)V", "getLudwigToken", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "setResult", "(Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Captcha extends Screen<LudwigCaptchaResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String ludwigToken;

        @Nullable
        private LudwigCaptchaResult result;

        @NotNull
        public static final Parcelable.Creator<Captcha> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Captcha> {
            @Override // android.os.Parcelable.Creator
            public final Captcha createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Captcha(parcel.readString(), parcel.readInt() != 0, (LudwigCaptchaResult) parcel.readParcelable(Captcha.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Captcha[] newArray(int i10) {
                return new Captcha[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Captcha(@NotNull String ludwigToken, boolean z10, @Nullable LudwigCaptchaResult ludwigCaptchaResult) {
            super(null);
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            this.ludwigToken = ludwigToken;
            this.isReplace = z10;
            this.result = ludwigCaptchaResult;
            this.id = "C";
        }

        public static /* synthetic */ Captcha copy$default(Captcha captcha, String str, boolean z10, LudwigCaptchaResult ludwigCaptchaResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = captcha.ludwigToken;
            }
            if ((i10 & 2) != 0) {
                z10 = captcha.isReplace;
            }
            if ((i10 & 4) != 0) {
                ludwigCaptchaResult = captcha.result;
            }
            return captcha.copy(str, z10, ludwigCaptchaResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final LudwigCaptchaResult getResult() {
            return this.result;
        }

        @NotNull
        public final Captcha copy(@NotNull String ludwigToken, boolean isReplace, @Nullable LudwigCaptchaResult result) {
            Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
            return new Captcha(ludwigToken, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Captcha)) {
                return false;
            }
            Captcha captcha = (Captcha) other;
            return Intrinsics.areEqual(this.ludwigToken, captcha.ludwigToken) && this.isReplace == captcha.isReplace && Intrinsics.areEqual(this.result, captcha.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        public int hashCode() {
            int iHashCode = ((this.ludwigToken.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            LudwigCaptchaResult ludwigCaptchaResult = this.result;
            return iHashCode + (ludwigCaptchaResult == null ? 0 : ludwigCaptchaResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "Captcha(ludwigToken=" + this.ludwigToken + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.ludwigToken);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public LudwigCaptchaResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable LudwigCaptchaResult ludwigCaptchaResult) {
            this.result = ludwigCaptchaResult;
        }

        public /* synthetic */ Captcha(String str, boolean z10, LudwigCaptchaResult ludwigCaptchaResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : ludwigCaptchaResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\u00052\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u001a\u0010\r\u001a\u00020\u0005X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChangePassword;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/changepassword/presentation/ChangePasswordResult;", "result", "isReplace", "", "<init>", "(Lru/mail/authorizationsdk/feature/changepassword/presentation/ChangePasswordResult;Z)V", "getResult", "()Lru/mail/authorizationsdk/feature/changepassword/presentation/ChangePasswordResult;", "setResult", "(Lru/mail/authorizationsdk/feature/changepassword/presentation/ChangePasswordResult;)V", "()Z", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ChangePassword extends Screen<ChangePasswordResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private ChangePasswordResult result;

        @NotNull
        public static final Parcelable.Creator<ChangePassword> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ChangePassword> {
            @Override // android.os.Parcelable.Creator
            public final ChangePassword createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new ChangePassword((ChangePasswordResult) parcel.readParcelable(ChangePassword.class.getClassLoader()), parcel.readInt() != 0);
            }

            @Override // android.os.Parcelable.Creator
            public final ChangePassword[] newArray(int i10) {
                return new ChangePassword[i10];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ChangePassword() {
            this(null, false, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ ChangePassword copy$default(ChangePassword changePassword, ChangePasswordResult changePasswordResult, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                changePasswordResult = changePassword.result;
            }
            if ((i10 & 2) != 0) {
                z10 = changePassword.isReplace;
            }
            return changePassword.copy(changePasswordResult, z10);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ChangePasswordResult getResult() {
            return this.result;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @NotNull
        public final ChangePassword copy(@Nullable ChangePasswordResult result, boolean isReplace) {
            return new ChangePassword(result, isReplace);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChangePassword)) {
                return false;
            }
            ChangePassword changePassword = (ChangePassword) other;
            return Intrinsics.areEqual(this.result, changePassword.result) && this.isReplace == changePassword.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            ChangePasswordResult changePasswordResult = this.result;
            return ((changePasswordResult == null ? 0 : changePasswordResult.hashCode()) * 31) + Boolean.hashCode(this.isReplace);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "ChangePassword(result=" + this.result + ", isReplace=" + this.isReplace + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
            dest.writeInt(this.isReplace ? 1 : 0);
        }

        public ChangePassword(@Nullable ChangePasswordResult changePasswordResult, boolean z10) {
            super(null);
            this.result = changePasswordResult;
            this.isReplace = z10;
            this.id = "p";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public ChangePasswordResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable ChangePasswordResult changePasswordResult) {
            this.result = changePasswordResult;
        }

        public /* synthetic */ ChangePassword(ChangePasswordResult changePasswordResult, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : changePasswordResult, (i10 & 2) != 0 ? false : z10);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b,\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bi\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u0004\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u000f\u001a\u00020\t\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0016J\t\u0010(\u001a\u00020\u0004HÆ\u0003J\t\u0010)\u001a\u00020\tHÆ\u0003J\t\u0010*\u001a\u00020\tHÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\u0004HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\t\u0010.\u001a\u00020\u0004HÆ\u0003J\t\u0010/\u001a\u00020\tHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u0080\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0003\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\t2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0002\u00102J\u0006\u00103\u001a\u00020\u0006J\u0013\u00104\u001a\u00020\t2\b\u00105\u001a\u0004\u0018\u000106HÖ\u0003J\t\u00107\u001a\u00020\u0006HÖ\u0001J\t\u00108\u001a\u00020\u0004HÖ\u0001J\u0016\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0006R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0019R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\r\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0014\u0010\u000f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0019R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020\tX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b!\u0010\"\u001a\u0004\b \u0010\u0019R\u001a\u0010#\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\"\u001a\u0004\b%\u0010\u0014¨\u0006>"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChoiceAccount;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/socialauth/choicescreen/ChoiceAccountResult;", "enteredEmail", "", "appIconRes", "", "socialBindType", "isSimpleChoiceFragment", "", "isSocialAccountEnabled", "isVkMailApp", "choiceMode", "isVkIdWithoutPasswordEnabled", "vkAccessToken", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZZZLjava/lang/String;ZLjava/lang/String;ZLru/mail/authorizationsdk/feature/socialauth/choicescreen/ChoiceAccountResult;)V", "getEnteredEmail", "()Ljava/lang/String;", "getAppIconRes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getSocialBindType", "()Z", "getChoiceMode", "getVkAccessToken", "getResult", "()Lru/mail/authorizationsdk/feature/socialauth/choicescreen/ChoiceAccountResult;", "setResult", "(Lru/mail/authorizationsdk/feature/socialauth/choicescreen/ChoiceAccountResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;ZZZLjava/lang/String;ZLjava/lang/String;ZLru/mail/authorizationsdk/feature/socialauth/choicescreen/ChoiceAccountResult;)Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ChoiceAccount;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ChoiceAccount extends Screen<ChoiceAccountResult> {

        @Nullable
        private final Integer appIconRes;

        @NotNull
        private final String choiceMode;

        @NotNull
        private final String enteredEmail;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSimpleChoiceFragment;
        private final boolean isSocialAccountEnabled;
        private final boolean isSupportBack;
        private final boolean isVkIdWithoutPasswordEnabled;
        private final boolean isVkMailApp;

        @Nullable
        private ChoiceAccountResult result;

        @NotNull
        private final String socialBindType;

        @NotNull
        private final String vkAccessToken;

        @NotNull
        public static final Parcelable.Creator<ChoiceAccount> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ChoiceAccount> {
            @Override // android.os.Parcelable.Creator
            public final ChoiceAccount createFromParcel(Parcel parcel) {
                boolean z10;
                boolean z11;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                Integer numValueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
                String string2 = parcel.readString();
                boolean z12 = false;
                boolean z13 = true;
                if (parcel.readInt() != 0) {
                    z12 = true;
                }
                if (parcel.readInt() == 0) {
                    z13 = z12;
                }
                if (parcel.readInt() == 0) {
                    z13 = z12;
                }
                String string3 = parcel.readString();
                if (parcel.readInt() != 0) {
                    z10 = true;
                    z11 = true;
                } else {
                    z10 = z13;
                    z11 = z12;
                }
                return new ChoiceAccount(string, numValueOf, string2, z12, z13, z13, string3, z11, parcel.readString(), parcel.readInt() != 0 ? z10 : false, (ChoiceAccountResult) parcel.readParcelable(ChoiceAccount.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final ChoiceAccount[] newArray(int i10) {
                return new ChoiceAccount[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ChoiceAccount(@NotNull String enteredEmail, @DrawableRes @Nullable Integer num, @NotNull String socialBindType, boolean z10, boolean z11, boolean z12, @NotNull String choiceMode, boolean z13, @NotNull String vkAccessToken, boolean z14, @Nullable ChoiceAccountResult choiceAccountResult) {
            super(null);
            Intrinsics.checkNotNullParameter(enteredEmail, "enteredEmail");
            Intrinsics.checkNotNullParameter(socialBindType, "socialBindType");
            Intrinsics.checkNotNullParameter(choiceMode, "choiceMode");
            Intrinsics.checkNotNullParameter(vkAccessToken, "vkAccessToken");
            this.enteredEmail = enteredEmail;
            this.appIconRes = num;
            this.socialBindType = socialBindType;
            this.isSimpleChoiceFragment = z10;
            this.isSocialAccountEnabled = z11;
            this.isVkMailApp = z12;
            this.choiceMode = choiceMode;
            this.isVkIdWithoutPasswordEnabled = z13;
            this.vkAccessToken = vkAccessToken;
            this.isReplace = z14;
            this.result = choiceAccountResult;
            this.isSupportBack = true;
            this.id = "Ch";
        }

        public static /* synthetic */ ChoiceAccount copy$default(ChoiceAccount choiceAccount, String str, Integer num, String str2, boolean z10, boolean z11, boolean z12, String str3, boolean z13, String str4, boolean z14, ChoiceAccountResult choiceAccountResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = choiceAccount.enteredEmail;
            }
            if ((i10 & 2) != 0) {
                num = choiceAccount.appIconRes;
            }
            if ((i10 & 4) != 0) {
                str2 = choiceAccount.socialBindType;
            }
            if ((i10 & 8) != 0) {
                z10 = choiceAccount.isSimpleChoiceFragment;
            }
            if ((i10 & 16) != 0) {
                z11 = choiceAccount.isSocialAccountEnabled;
            }
            if ((i10 & 32) != 0) {
                z12 = choiceAccount.isVkMailApp;
            }
            if ((i10 & 64) != 0) {
                str3 = choiceAccount.choiceMode;
            }
            if ((i10 & 128) != 0) {
                z13 = choiceAccount.isVkIdWithoutPasswordEnabled;
            }
            if ((i10 & 256) != 0) {
                str4 = choiceAccount.vkAccessToken;
            }
            if ((i10 & 512) != 0) {
                z14 = choiceAccount.isReplace;
            }
            if ((i10 & 1024) != 0) {
                choiceAccountResult = choiceAccount.result;
            }
            boolean z15 = z14;
            ChoiceAccountResult choiceAccountResult2 = choiceAccountResult;
            boolean z16 = z13;
            String str5 = str4;
            boolean z17 = z12;
            String str6 = str3;
            boolean z18 = z11;
            String str7 = str2;
            return choiceAccount.copy(str, num, str7, z10, z18, z17, str6, z16, str5, z15, choiceAccountResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEnteredEmail() {
            return this.enteredEmail;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final ChoiceAccountResult getResult() {
            return this.result;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getAppIconRes() {
            return this.appIconRes;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsSimpleChoiceFragment() {
            return this.isSimpleChoiceFragment;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsSocialAccountEnabled() {
            return this.isSocialAccountEnabled;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsVkMailApp() {
            return this.isVkMailApp;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getChoiceMode() {
            return this.choiceMode;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getIsVkIdWithoutPasswordEnabled() {
            return this.isVkIdWithoutPasswordEnabled;
        }

        @NotNull
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        @NotNull
        public final ChoiceAccount copy(@NotNull String enteredEmail, @DrawableRes @Nullable Integer appIconRes, @NotNull String socialBindType, boolean isSimpleChoiceFragment, boolean isSocialAccountEnabled, boolean isVkMailApp, @NotNull String choiceMode, boolean isVkIdWithoutPasswordEnabled, @NotNull String vkAccessToken, boolean isReplace, @Nullable ChoiceAccountResult result) {
            Intrinsics.checkNotNullParameter(enteredEmail, "enteredEmail");
            Intrinsics.checkNotNullParameter(socialBindType, "socialBindType");
            Intrinsics.checkNotNullParameter(choiceMode, "choiceMode");
            Intrinsics.checkNotNullParameter(vkAccessToken, "vkAccessToken");
            return new ChoiceAccount(enteredEmail, appIconRes, socialBindType, isSimpleChoiceFragment, isSocialAccountEnabled, isVkMailApp, choiceMode, isVkIdWithoutPasswordEnabled, vkAccessToken, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChoiceAccount)) {
                return false;
            }
            ChoiceAccount choiceAccount = (ChoiceAccount) other;
            return Intrinsics.areEqual(this.enteredEmail, choiceAccount.enteredEmail) && Intrinsics.areEqual(this.appIconRes, choiceAccount.appIconRes) && Intrinsics.areEqual(this.socialBindType, choiceAccount.socialBindType) && this.isSimpleChoiceFragment == choiceAccount.isSimpleChoiceFragment && this.isSocialAccountEnabled == choiceAccount.isSocialAccountEnabled && this.isVkMailApp == choiceAccount.isVkMailApp && Intrinsics.areEqual(this.choiceMode, choiceAccount.choiceMode) && this.isVkIdWithoutPasswordEnabled == choiceAccount.isVkIdWithoutPasswordEnabled && Intrinsics.areEqual(this.vkAccessToken, choiceAccount.vkAccessToken) && this.isReplace == choiceAccount.isReplace && Intrinsics.areEqual(this.result, choiceAccount.result);
        }

        @Nullable
        public final Integer getAppIconRes() {
            return this.appIconRes;
        }

        @NotNull
        public final String getChoiceMode() {
            return this.choiceMode;
        }

        @NotNull
        public final String getEnteredEmail() {
            return this.enteredEmail;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @NotNull
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        public int hashCode() {
            int iHashCode = this.enteredEmail.hashCode() * 31;
            Integer num = this.appIconRes;
            int iHashCode2 = (((((((((((((((((iHashCode + (num == null ? 0 : num.hashCode())) * 31) + this.socialBindType.hashCode()) * 31) + Boolean.hashCode(this.isSimpleChoiceFragment)) * 31) + Boolean.hashCode(this.isSocialAccountEnabled)) * 31) + Boolean.hashCode(this.isVkMailApp)) * 31) + this.choiceMode.hashCode()) * 31) + Boolean.hashCode(this.isVkIdWithoutPasswordEnabled)) * 31) + this.vkAccessToken.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            ChoiceAccountResult choiceAccountResult = this.result;
            return iHashCode2 + (choiceAccountResult != null ? choiceAccountResult.hashCode() : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        public final boolean isSimpleChoiceFragment() {
            return this.isSimpleChoiceFragment;
        }

        public final boolean isSocialAccountEnabled() {
            return this.isSocialAccountEnabled;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        public final boolean isVkIdWithoutPasswordEnabled() {
            return this.isVkIdWithoutPasswordEnabled;
        }

        public final boolean isVkMailApp() {
            return this.isVkMailApp;
        }

        @NotNull
        public String toString() {
            return "ChoiceAccount(enteredEmail=" + this.enteredEmail + ", appIconRes=" + this.appIconRes + ", socialBindType=" + this.socialBindType + ", isSimpleChoiceFragment=" + this.isSimpleChoiceFragment + ", isSocialAccountEnabled=" + this.isSocialAccountEnabled + ", isVkMailApp=" + this.isVkMailApp + ", choiceMode=" + this.choiceMode + ", isVkIdWithoutPasswordEnabled=" + this.isVkIdWithoutPasswordEnabled + ", vkAccessToken=" + this.vkAccessToken + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            int iIntValue;
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.enteredEmail);
            Integer num = this.appIconRes;
            if (num == null) {
                iIntValue = 0;
            } else {
                dest.writeInt(1);
                iIntValue = num.intValue();
            }
            dest.writeInt(iIntValue);
            dest.writeString(this.socialBindType);
            dest.writeInt(this.isSimpleChoiceFragment ? 1 : 0);
            dest.writeInt(this.isSocialAccountEnabled ? 1 : 0);
            dest.writeInt(this.isVkMailApp ? 1 : 0);
            dest.writeString(this.choiceMode);
            dest.writeInt(this.isVkIdWithoutPasswordEnabled ? 1 : 0);
            dest.writeString(this.vkAccessToken);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public ChoiceAccountResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable ChoiceAccountResult choiceAccountResult) {
            this.result = choiceAccountResult;
        }

        public /* synthetic */ ChoiceAccount(String str, Integer num, String str2, boolean z10, boolean z11, boolean z12, String str3, boolean z13, String str4, boolean z14, ChoiceAccountResult choiceAccountResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? null : num, str2, z10, z11, z12, str3, z13, str4, (i10 & 512) != 0 ? false : z14, (i10 & 1024) != 0 ? null : choiceAccountResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B_\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ja\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0006\u0010(\u001a\u00020)J\u0013\u0010*\u001a\u00020\t2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020)HÖ\u0001J\t\u0010.\u001a\u00020\u0005HÖ\u0001J\u0016\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020)R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\tX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u0005X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001e\u0010\u0011¨\u00064"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CloudLogin;", "Lru/mail/authorizationsdk/feature/authactivity/screens/LoginLogicScreen;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/CloudLoginResult;", "email", "", "from", "serviceType", "isManualLogout", "", "isDeeplinkOpened", "isNeedStartRestore", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/CloudLoginResult;)V", "getEmail", "()Ljava/lang/String;", "getFrom", "getServiceType", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/CloudLoginResult;", "setResult", "(Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/CloudLoginResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CloudLogin extends Screen<CloudLoginResult> implements LoginLogicScreen {

        @Nullable
        private final String email;

        @Nullable
        private final String from;

        @NotNull
        private final String id;
        private final boolean isDeeplinkOpened;
        private final boolean isManualLogout;
        private final boolean isNeedStartRestore;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private CloudLoginResult result;

        @Nullable
        private final String serviceType;

        @NotNull
        public static final Parcelable.Creator<CloudLogin> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CloudLogin> {
            @Override // android.os.Parcelable.Creator
            public final CloudLogin createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new CloudLogin(string, string2, string3, z10, z11, z11, parcel.readInt() != 0, (CloudLoginResult) parcel.readParcelable(CloudLogin.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CloudLogin[] newArray(int i10) {
                return new CloudLogin[i10];
            }
        }

        public CloudLogin() {
            this(null, null, null, false, false, false, false, null, 255, null);
        }

        public static /* synthetic */ CloudLogin copy$default(CloudLogin cloudLogin, String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, CloudLoginResult cloudLoginResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = cloudLogin.email;
            }
            if ((i10 & 2) != 0) {
                str2 = cloudLogin.from;
            }
            if ((i10 & 4) != 0) {
                str3 = cloudLogin.serviceType;
            }
            if ((i10 & 8) != 0) {
                z10 = cloudLogin.isManualLogout;
            }
            if ((i10 & 16) != 0) {
                z11 = cloudLogin.isDeeplinkOpened;
            }
            if ((i10 & 32) != 0) {
                z12 = cloudLogin.isNeedStartRestore;
            }
            if ((i10 & 64) != 0) {
                z13 = cloudLogin.isReplace;
            }
            if ((i10 & 128) != 0) {
                cloudLoginResult = cloudLogin.result;
            }
            boolean z14 = z13;
            CloudLoginResult cloudLoginResult2 = cloudLoginResult;
            boolean z15 = z11;
            boolean z16 = z12;
            return cloudLogin.copy(str, str2, str3, z10, z15, z16, z14, cloudLoginResult2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsManualLogout() {
            return this.isManualLogout;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final CloudLoginResult getResult() {
            return this.result;
        }

        @NotNull
        public final CloudLogin copy(@Nullable String email, @Nullable String from, @Nullable String serviceType, boolean isManualLogout, boolean isDeeplinkOpened, boolean isNeedStartRestore, boolean isReplace, @Nullable CloudLoginResult result) {
            return new CloudLogin(email, from, serviceType, isManualLogout, isDeeplinkOpened, isNeedStartRestore, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CloudLogin)) {
                return false;
            }
            CloudLogin cloudLogin = (CloudLogin) other;
            return Intrinsics.areEqual(this.email, cloudLogin.email) && Intrinsics.areEqual(this.from, cloudLogin.from) && Intrinsics.areEqual(this.serviceType, cloudLogin.serviceType) && this.isManualLogout == cloudLogin.isManualLogout && this.isDeeplinkOpened == cloudLogin.isDeeplinkOpened && this.isNeedStartRestore == cloudLogin.isNeedStartRestore && this.isReplace == cloudLogin.isReplace && Intrinsics.areEqual(this.result, cloudLogin.result);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        public final String getFrom() {
            return this.from;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @Nullable
        public final String getServiceType() {
            return this.serviceType;
        }

        public int hashCode() {
            String str = this.email;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.from;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.serviceType;
            int iHashCode3 = (((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.isManualLogout)) * 31) + Boolean.hashCode(this.isDeeplinkOpened)) * 31) + Boolean.hashCode(this.isNeedStartRestore)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            CloudLoginResult cloudLoginResult = this.result;
            return iHashCode3 + (cloudLoginResult != null ? cloudLoginResult.hashCode() : 0);
        }

        public final boolean isDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        public final boolean isManualLogout() {
            return this.isManualLogout;
        }

        public final boolean isNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "CloudLogin(email=" + this.email + ", from=" + this.from + ", serviceType=" + this.serviceType + ", isManualLogout=" + this.isManualLogout + ", isDeeplinkOpened=" + this.isDeeplinkOpened + ", isNeedStartRestore=" + this.isNeedStartRestore + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.from);
            dest.writeString(this.serviceType);
            dest.writeInt(this.isManualLogout ? 1 : 0);
            dest.writeInt(this.isDeeplinkOpened ? 1 : 0);
            dest.writeInt(this.isNeedStartRestore ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public CloudLogin(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z10, boolean z11, boolean z12, boolean z13, @Nullable CloudLoginResult cloudLoginResult) {
            super(null);
            this.email = str;
            this.from = str2;
            this.serviceType = str3;
            this.isManualLogout = z10;
            this.isDeeplinkOpened = z11;
            this.isNeedStartRestore = z12;
            this.isReplace = z13;
            this.result = cloudLoginResult;
            this.isSupportBack = true;
            this.id = "L";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public CloudLoginResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable CloudLoginResult cloudLoginResult) {
            this.result = cloudLoginResult;
        }

        public /* synthetic */ CloudLogin(String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, CloudLoginResult cloudLoginResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11, (i10 & 32) != 0 ? false : z12, (i10 & 64) != 0 ? false : z13, (i10 & 128) != 0 ? null : cloudLoginResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B_\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ja\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0006\u0010(\u001a\u00020)J\u0013\u0010*\u001a\u00020\t2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020)HÖ\u0001J\t\u0010.\u001a\u00020\u0005HÖ\u0001J\u0016\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020)R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\tX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u0005X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001e\u0010\u0011¨\u00064"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CloudLoginVk;", "Lru/mail/authorizationsdk/feature/authactivity/screens/LoginLogicScreen;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloudvk/CloudLoginVkResult;", "email", "", "from", "serviceType", "isManualLogout", "", "isDeeplinkOpened", "isNeedStartRestore", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLru/mail/authorizationsdk/feature/login/presentation/flavor/cloudvk/CloudLoginVkResult;)V", "getEmail", "()Ljava/lang/String;", "getFrom", "getServiceType", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloudvk/CloudLoginVkResult;", "setResult", "(Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloudvk/CloudLoginVkResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CloudLoginVk extends Screen<CloudLoginVkResult> implements LoginLogicScreen {

        @Nullable
        private final String email;

        @Nullable
        private final String from;

        @NotNull
        private final String id;
        private final boolean isDeeplinkOpened;
        private final boolean isManualLogout;
        private final boolean isNeedStartRestore;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private CloudLoginVkResult result;

        @Nullable
        private final String serviceType;

        @NotNull
        public static final Parcelable.Creator<CloudLoginVk> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CloudLoginVk> {
            @Override // android.os.Parcelable.Creator
            public final CloudLoginVk createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new CloudLoginVk(string, string2, string3, z10, z11, z11, parcel.readInt() != 0, (CloudLoginVkResult) parcel.readParcelable(CloudLoginVk.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CloudLoginVk[] newArray(int i10) {
                return new CloudLoginVk[i10];
            }
        }

        public CloudLoginVk() {
            this(null, null, null, false, false, false, false, null, 255, null);
        }

        public static /* synthetic */ CloudLoginVk copy$default(CloudLoginVk cloudLoginVk, String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, CloudLoginVkResult cloudLoginVkResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = cloudLoginVk.email;
            }
            if ((i10 & 2) != 0) {
                str2 = cloudLoginVk.from;
            }
            if ((i10 & 4) != 0) {
                str3 = cloudLoginVk.serviceType;
            }
            if ((i10 & 8) != 0) {
                z10 = cloudLoginVk.isManualLogout;
            }
            if ((i10 & 16) != 0) {
                z11 = cloudLoginVk.isDeeplinkOpened;
            }
            if ((i10 & 32) != 0) {
                z12 = cloudLoginVk.isNeedStartRestore;
            }
            if ((i10 & 64) != 0) {
                z13 = cloudLoginVk.isReplace;
            }
            if ((i10 & 128) != 0) {
                cloudLoginVkResult = cloudLoginVk.result;
            }
            boolean z14 = z13;
            CloudLoginVkResult cloudLoginVkResult2 = cloudLoginVkResult;
            boolean z15 = z11;
            boolean z16 = z12;
            return cloudLoginVk.copy(str, str2, str3, z10, z15, z16, z14, cloudLoginVkResult2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsManualLogout() {
            return this.isManualLogout;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final CloudLoginVkResult getResult() {
            return this.result;
        }

        @NotNull
        public final CloudLoginVk copy(@Nullable String email, @Nullable String from, @Nullable String serviceType, boolean isManualLogout, boolean isDeeplinkOpened, boolean isNeedStartRestore, boolean isReplace, @Nullable CloudLoginVkResult result) {
            return new CloudLoginVk(email, from, serviceType, isManualLogout, isDeeplinkOpened, isNeedStartRestore, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CloudLoginVk)) {
                return false;
            }
            CloudLoginVk cloudLoginVk = (CloudLoginVk) other;
            return Intrinsics.areEqual(this.email, cloudLoginVk.email) && Intrinsics.areEqual(this.from, cloudLoginVk.from) && Intrinsics.areEqual(this.serviceType, cloudLoginVk.serviceType) && this.isManualLogout == cloudLoginVk.isManualLogout && this.isDeeplinkOpened == cloudLoginVk.isDeeplinkOpened && this.isNeedStartRestore == cloudLoginVk.isNeedStartRestore && this.isReplace == cloudLoginVk.isReplace && Intrinsics.areEqual(this.result, cloudLoginVk.result);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        public final String getFrom() {
            return this.from;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @Nullable
        public final String getServiceType() {
            return this.serviceType;
        }

        public int hashCode() {
            String str = this.email;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.from;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.serviceType;
            int iHashCode3 = (((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.isManualLogout)) * 31) + Boolean.hashCode(this.isDeeplinkOpened)) * 31) + Boolean.hashCode(this.isNeedStartRestore)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            CloudLoginVkResult cloudLoginVkResult = this.result;
            return iHashCode3 + (cloudLoginVkResult != null ? cloudLoginVkResult.hashCode() : 0);
        }

        public final boolean isDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        public final boolean isManualLogout() {
            return this.isManualLogout;
        }

        public final boolean isNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "CloudLoginVk(email=" + this.email + ", from=" + this.from + ", serviceType=" + this.serviceType + ", isManualLogout=" + this.isManualLogout + ", isDeeplinkOpened=" + this.isDeeplinkOpened + ", isNeedStartRestore=" + this.isNeedStartRestore + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.from);
            dest.writeString(this.serviceType);
            dest.writeInt(this.isManualLogout ? 1 : 0);
            dest.writeInt(this.isDeeplinkOpened ? 1 : 0);
            dest.writeInt(this.isNeedStartRestore ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public CloudLoginVk(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z10, boolean z11, boolean z12, boolean z13, @Nullable CloudLoginVkResult cloudLoginVkResult) {
            super(null);
            this.email = str;
            this.from = str2;
            this.serviceType = str3;
            this.isManualLogout = z10;
            this.isDeeplinkOpened = z11;
            this.isNeedStartRestore = z12;
            this.isReplace = z13;
            this.result = cloudLoginVkResult;
            this.isSupportBack = true;
            this.id = "CLOUDL";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public CloudLoginVkResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable CloudLoginVkResult cloudLoginVkResult) {
            this.result = cloudLoginVkResult;
        }

        public /* synthetic */ CloudLoginVk(String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, CloudLoginVkResult cloudLoginVkResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11, (i10 & 32) != 0 ? false : z12, (i10 & 64) != 0 ? false : z13, (i10 & 128) != 0 ? null : cloudLoginVkResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B_\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\t\u0010(\u001a\u00020\u0006HÆ\u0003J\t\u0010)\u001a\u00020\u0006HÆ\u0003J\t\u0010*\u001a\u00020\u0004HÆ\u0003J\t\u0010+\u001a\u00020\u0006HÆ\u0003J\t\u0010,\u001a\u00020\u0006HÆ\u0003J\t\u0010-\u001a\u00020\fHÆ\u0003J\t\u0010.\u001a\u00020\u0004HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0002HÆ\u0003Je\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00042\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u00101\u001a\u00020\fJ\u0013\u00102\u001a\u00020\u00042\b\u00103\u001a\u0004\u0018\u000104HÖ\u0003J\t\u00105\u001a\u00020\fHÖ\u0001J\t\u00106\u001a\u00020#HÖ\u0001J\u0016\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\r\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0012R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010!\u001a\u0004\b\u001f\u0010\u0012R\u001a\u0010\"\u001a\u00020#X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010!\u001a\u0004\b%\u0010&¨\u0006<"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CodeReceiveTypeDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeDialogResult;", "hasSmsInfo", "", "smsDelayFrom", "", "smsDelay", "hasWaitCallInfo", "callDelayFrom", "callDelay", "receiveType", "", "isReplace", "result", "<init>", "(ZJJZJJIZLru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeDialogResult;)V", "getHasSmsInfo", "()Z", "getSmsDelayFrom", "()J", "getSmsDelay", "getHasWaitCallInfo", "getCallDelayFrom", "getCallDelay", "getReceiveType", "()I", "getResult", "()Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeDialogResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeDialogResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CodeReceiveTypeDialog extends Screen<CodeReceiveTypeDialogResult> {
        private final long callDelay;
        private final long callDelayFrom;
        private final boolean hasSmsInfo;
        private final boolean hasWaitCallInfo;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;
        private final int receiveType;

        @Nullable
        private CodeReceiveTypeDialogResult result;
        private final long smsDelay;
        private final long smsDelayFrom;

        @NotNull
        public static final Parcelable.Creator<CodeReceiveTypeDialog> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CodeReceiveTypeDialog> {
            @Override // android.os.Parcelable.Creator
            public final CodeReceiveTypeDialog createFromParcel(Parcel parcel) {
                boolean z10;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                boolean z11 = false;
                if (parcel.readInt() != 0) {
                    z11 = true;
                    z10 = true;
                } else {
                    z10 = true;
                }
                long j10 = parcel.readLong();
                boolean z12 = z10;
                long j11 = parcel.readLong();
                if (parcel.readInt() == 0) {
                    z12 = z11;
                }
                long j12 = parcel.readLong();
                long j13 = parcel.readLong();
                boolean z13 = z12;
                int i10 = parcel.readInt();
                if (parcel.readInt() == 0) {
                    z13 = false;
                }
                return new CodeReceiveTypeDialog(z11, j10, j11, z12, j12, j13, i10, z13, (CodeReceiveTypeDialogResult) parcel.readParcelable(CodeReceiveTypeDialog.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CodeReceiveTypeDialog[] newArray(int i10) {
                return new CodeReceiveTypeDialog[i10];
            }
        }

        public CodeReceiveTypeDialog(boolean z10, long j10, long j11, boolean z11, long j12, long j13, int i10, boolean z12, @Nullable CodeReceiveTypeDialogResult codeReceiveTypeDialogResult) {
            super(null);
            this.hasSmsInfo = z10;
            this.smsDelayFrom = j10;
            this.smsDelay = j11;
            this.hasWaitCallInfo = z11;
            this.callDelayFrom = j12;
            this.callDelay = j13;
            this.receiveType = i10;
            this.isReplace = z12;
            this.result = codeReceiveTypeDialogResult;
            this.isSupportBack = true;
            this.id = "CRTD";
        }

        public static /* synthetic */ CodeReceiveTypeDialog copy$default(CodeReceiveTypeDialog codeReceiveTypeDialog, boolean z10, long j10, long j11, boolean z11, long j12, long j13, int i10, boolean z12, CodeReceiveTypeDialogResult codeReceiveTypeDialogResult, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                z10 = codeReceiveTypeDialog.hasSmsInfo;
            }
            return codeReceiveTypeDialog.copy(z10, (i11 & 2) != 0 ? codeReceiveTypeDialog.smsDelayFrom : j10, (i11 & 4) != 0 ? codeReceiveTypeDialog.smsDelay : j11, (i11 & 8) != 0 ? codeReceiveTypeDialog.hasWaitCallInfo : z11, (i11 & 16) != 0 ? codeReceiveTypeDialog.callDelayFrom : j12, (i11 & 32) != 0 ? codeReceiveTypeDialog.callDelay : j13, (i11 & 64) != 0 ? codeReceiveTypeDialog.receiveType : i10, (i11 & 128) != 0 ? codeReceiveTypeDialog.isReplace : z12, (i11 & 256) != 0 ? codeReceiveTypeDialog.result : codeReceiveTypeDialogResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getHasSmsInfo() {
            return this.hasSmsInfo;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getSmsDelayFrom() {
            return this.smsDelayFrom;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getSmsDelay() {
            return this.smsDelay;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getHasWaitCallInfo() {
            return this.hasWaitCallInfo;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final long getCallDelayFrom() {
            return this.callDelayFrom;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final long getCallDelay() {
            return this.callDelay;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final int getReceiveType() {
            return this.receiveType;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final CodeReceiveTypeDialogResult getResult() {
            return this.result;
        }

        @NotNull
        public final CodeReceiveTypeDialog copy(boolean hasSmsInfo, long smsDelayFrom, long smsDelay, boolean hasWaitCallInfo, long callDelayFrom, long callDelay, int receiveType, boolean isReplace, @Nullable CodeReceiveTypeDialogResult result) {
            return new CodeReceiveTypeDialog(hasSmsInfo, smsDelayFrom, smsDelay, hasWaitCallInfo, callDelayFrom, callDelay, receiveType, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CodeReceiveTypeDialog)) {
                return false;
            }
            CodeReceiveTypeDialog codeReceiveTypeDialog = (CodeReceiveTypeDialog) other;
            return this.hasSmsInfo == codeReceiveTypeDialog.hasSmsInfo && this.smsDelayFrom == codeReceiveTypeDialog.smsDelayFrom && this.smsDelay == codeReceiveTypeDialog.smsDelay && this.hasWaitCallInfo == codeReceiveTypeDialog.hasWaitCallInfo && this.callDelayFrom == codeReceiveTypeDialog.callDelayFrom && this.callDelay == codeReceiveTypeDialog.callDelay && this.receiveType == codeReceiveTypeDialog.receiveType && this.isReplace == codeReceiveTypeDialog.isReplace && Intrinsics.areEqual(this.result, codeReceiveTypeDialog.result);
        }

        public final long getCallDelay() {
            return this.callDelay;
        }

        public final long getCallDelayFrom() {
            return this.callDelayFrom;
        }

        public final boolean getHasSmsInfo() {
            return this.hasSmsInfo;
        }

        public final boolean getHasWaitCallInfo() {
            return this.hasWaitCallInfo;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public final int getReceiveType() {
            return this.receiveType;
        }

        public final long getSmsDelay() {
            return this.smsDelay;
        }

        public final long getSmsDelayFrom() {
            return this.smsDelayFrom;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((((Boolean.hashCode(this.hasSmsInfo) * 31) + Long.hashCode(this.smsDelayFrom)) * 31) + Long.hashCode(this.smsDelay)) * 31) + Boolean.hashCode(this.hasWaitCallInfo)) * 31) + Long.hashCode(this.callDelayFrom)) * 31) + Long.hashCode(this.callDelay)) * 31) + Integer.hashCode(this.receiveType)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            CodeReceiveTypeDialogResult codeReceiveTypeDialogResult = this.result;
            return iHashCode + (codeReceiveTypeDialogResult == null ? 0 : codeReceiveTypeDialogResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "CodeReceiveTypeDialog(hasSmsInfo=" + this.hasSmsInfo + ", smsDelayFrom=" + this.smsDelayFrom + ", smsDelay=" + this.smsDelay + ", hasWaitCallInfo=" + this.hasWaitCallInfo + ", callDelayFrom=" + this.callDelayFrom + ", callDelay=" + this.callDelay + ", receiveType=" + this.receiveType + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.hasSmsInfo ? 1 : 0);
            dest.writeLong(this.smsDelayFrom);
            dest.writeLong(this.smsDelay);
            dest.writeInt(this.hasWaitCallInfo ? 1 : 0);
            dest.writeLong(this.callDelayFrom);
            dest.writeLong(this.callDelay);
            dest.writeInt(this.receiveType);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public CodeReceiveTypeDialogResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable CodeReceiveTypeDialogResult codeReceiveTypeDialogResult) {
            this.result = codeReceiveTypeDialogResult;
        }

        public /* synthetic */ CodeReceiveTypeDialog(boolean z10, long j10, long j11, boolean z11, long j12, long j13, int i10, boolean z12, CodeReceiveTypeDialogResult codeReceiveTypeDialogResult, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this(z10, (i11 & 2) != 0 ? 0L : j10, (i11 & 4) != 0 ? 0L : j11, z11, (i11 & 16) != 0 ? 0L : j12, (i11 & 32) != 0 ? 0L : j13, (i11 & 64) != 0 ? -1 : i10, (i11 & 128) != 0 ? false : z12, (i11 & 256) != 0 ? null : codeReceiveTypeDialogResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u001f\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CodeReceiveTypeFromEmailDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeFromEmailDialogResult;", "isReplace", "", "result", "<init>", "(ZLru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeFromEmailDialogResult;)V", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeFromEmailDialogResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/codereceivetype/presentation/CodeReceiveTypeFromEmailDialogResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CodeReceiveTypeFromEmailDialog extends Screen<CodeReceiveTypeFromEmailDialogResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private CodeReceiveTypeFromEmailDialogResult result;

        @NotNull
        public static final Parcelable.Creator<CodeReceiveTypeFromEmailDialog> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CodeReceiveTypeFromEmailDialog> {
            @Override // android.os.Parcelable.Creator
            public final CodeReceiveTypeFromEmailDialog createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CodeReceiveTypeFromEmailDialog(parcel.readInt() != 0, (CodeReceiveTypeFromEmailDialogResult) parcel.readParcelable(CodeReceiveTypeFromEmailDialog.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CodeReceiveTypeFromEmailDialog[] newArray(int i10) {
                return new CodeReceiveTypeFromEmailDialog[i10];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CodeReceiveTypeFromEmailDialog() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ CodeReceiveTypeFromEmailDialog copy$default(CodeReceiveTypeFromEmailDialog codeReceiveTypeFromEmailDialog, boolean z10, CodeReceiveTypeFromEmailDialogResult codeReceiveTypeFromEmailDialogResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = codeReceiveTypeFromEmailDialog.isReplace;
            }
            if ((i10 & 2) != 0) {
                codeReceiveTypeFromEmailDialogResult = codeReceiveTypeFromEmailDialog.result;
            }
            return codeReceiveTypeFromEmailDialog.copy(z10, codeReceiveTypeFromEmailDialogResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final CodeReceiveTypeFromEmailDialogResult getResult() {
            return this.result;
        }

        @NotNull
        public final CodeReceiveTypeFromEmailDialog copy(boolean isReplace, @Nullable CodeReceiveTypeFromEmailDialogResult result) {
            return new CodeReceiveTypeFromEmailDialog(isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CodeReceiveTypeFromEmailDialog)) {
                return false;
            }
            CodeReceiveTypeFromEmailDialog codeReceiveTypeFromEmailDialog = (CodeReceiveTypeFromEmailDialog) other;
            return this.isReplace == codeReceiveTypeFromEmailDialog.isReplace && Intrinsics.areEqual(this.result, codeReceiveTypeFromEmailDialog.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.isReplace) * 31;
            CodeReceiveTypeFromEmailDialogResult codeReceiveTypeFromEmailDialogResult = this.result;
            return iHashCode + (codeReceiveTypeFromEmailDialogResult == null ? 0 : codeReceiveTypeFromEmailDialogResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "CodeReceiveTypeFromEmailDialog(isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public CodeReceiveTypeFromEmailDialog(boolean z10, @Nullable CodeReceiveTypeFromEmailDialogResult codeReceiveTypeFromEmailDialogResult) {
            super(null);
            this.isReplace = z10;
            this.result = codeReceiveTypeFromEmailDialogResult;
            this.isSupportBack = true;
            this.id = "CRTFED";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public CodeReceiveTypeFromEmailDialogResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable CodeReceiveTypeFromEmailDialogResult codeReceiveTypeFromEmailDialogResult) {
            this.result = codeReceiveTypeFromEmailDialogResult;
        }

        public /* synthetic */ CodeReceiveTypeFromEmailDialog(boolean z10, CodeReceiveTypeFromEmailDialogResult codeReceiveTypeFromEmailDialogResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? null : codeReceiveTypeFromEmailDialogResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u001f\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CreateCloud;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/createcloud/CreateCloudResult;", "isReplace", "", "result", "<init>", "(ZLru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/createcloud/CreateCloudResult;)V", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/createcloud/CreateCloudResult;", "setResult", "(Lru/mail/authorizationsdk/feature/login/presentation/flavor/cloud/createcloud/CreateCloudResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CreateCloud extends Screen<CreateCloudResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private CreateCloudResult result;

        @NotNull
        public static final Parcelable.Creator<CreateCloud> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CreateCloud> {
            @Override // android.os.Parcelable.Creator
            public final CreateCloud createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CreateCloud(parcel.readInt() != 0, (CreateCloudResult) parcel.readParcelable(CreateCloud.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CreateCloud[] newArray(int i10) {
                return new CreateCloud[i10];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public CreateCloud() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ CreateCloud copy$default(CreateCloud createCloud, boolean z10, CreateCloudResult createCloudResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = createCloud.isReplace;
            }
            if ((i10 & 2) != 0) {
                createCloudResult = createCloud.result;
            }
            return createCloud.copy(z10, createCloudResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final CreateCloudResult getResult() {
            return this.result;
        }

        @NotNull
        public final CreateCloud copy(boolean isReplace, @Nullable CreateCloudResult result) {
            return new CreateCloud(isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CreateCloud)) {
                return false;
            }
            CreateCloud createCloud = (CreateCloud) other;
            return this.isReplace == createCloud.isReplace && Intrinsics.areEqual(this.result, createCloud.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.isReplace) * 31;
            CreateCloudResult createCloudResult = this.result;
            return iHashCode + (createCloudResult == null ? 0 : createCloudResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "CreateCloud(isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public CreateCloud(boolean z10, @Nullable CreateCloudResult createCloudResult) {
            super(null);
            this.isReplace = z10;
            this.result = createCloudResult;
            this.isSupportBack = true;
            this.id = "CC";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public CreateCloudResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable CreateCloudResult createCloudResult) {
            this.result = createCloudResult;
        }

        public /* synthetic */ CreateCloud(boolean z10, CreateCloudResult createCloudResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? null : createCloudResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003JQ\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010&\u001a\u00020'J\u0013\u0010(\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020'HÖ\u0001J\t\u0010,\u001a\u00020\u0004HÖ\u0001J\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020'R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\n\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\bX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u001b\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001a\u001a\u0004\b\u001d\u0010\u000f¨\u00062"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$CustomServer;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;", "login", "", "password", "serviceType", "isForImapOnly", "", "needCaptcha", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;)V", "getLogin", "()Ljava/lang/String;", "getPassword", "getServiceType", "()Z", "getNeedCaptcha", "getResult", "()Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;", "setResult", "(Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CustomServer extends Screen<CustomServerResult> {

        @NotNull
        private final String id;
        private final boolean isForImapOnly;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String login;
        private final boolean needCaptcha;

        @NotNull
        private final String password;

        @Nullable
        private CustomServerResult result;

        @NotNull
        private final String serviceType;

        @NotNull
        public static final Parcelable.Creator<CustomServer> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CustomServer> {
            @Override // android.os.Parcelable.Creator
            public final CustomServer createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new CustomServer(string, string2, string3, z10, z11, parcel.readInt() != 0, (CustomServerResult) parcel.readParcelable(CustomServer.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CustomServer[] newArray(int i10) {
                return new CustomServer[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CustomServer(@NotNull String login, @NotNull String password, @NotNull String serviceType, boolean z10, boolean z11, boolean z12, @Nullable CustomServerResult customServerResult) {
            super(null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(serviceType, "serviceType");
            this.login = login;
            this.password = password;
            this.serviceType = serviceType;
            this.isForImapOnly = z10;
            this.needCaptcha = z11;
            this.isReplace = z12;
            this.result = customServerResult;
            this.id = "c";
        }

        public static /* synthetic */ CustomServer copy$default(CustomServer customServer, String str, String str2, String str3, boolean z10, boolean z11, boolean z12, CustomServerResult customServerResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = customServer.login;
            }
            if ((i10 & 2) != 0) {
                str2 = customServer.password;
            }
            if ((i10 & 4) != 0) {
                str3 = customServer.serviceType;
            }
            if ((i10 & 8) != 0) {
                z10 = customServer.isForImapOnly;
            }
            if ((i10 & 16) != 0) {
                z11 = customServer.needCaptcha;
            }
            if ((i10 & 32) != 0) {
                z12 = customServer.isReplace;
            }
            if ((i10 & 64) != 0) {
                customServerResult = customServer.result;
            }
            boolean z13 = z12;
            CustomServerResult customServerResult2 = customServerResult;
            boolean z14 = z11;
            String str4 = str3;
            return customServer.copy(str, str2, str4, z10, z14, z13, customServerResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsForImapOnly() {
            return this.isForImapOnly;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getNeedCaptcha() {
            return this.needCaptcha;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final CustomServerResult getResult() {
            return this.result;
        }

        @NotNull
        public final CustomServer copy(@NotNull String login, @NotNull String password, @NotNull String serviceType, boolean isForImapOnly, boolean needCaptcha, boolean isReplace, @Nullable CustomServerResult result) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(serviceType, "serviceType");
            return new CustomServer(login, password, serviceType, isForImapOnly, needCaptcha, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomServer)) {
                return false;
            }
            CustomServer customServer = (CustomServer) other;
            return Intrinsics.areEqual(this.login, customServer.login) && Intrinsics.areEqual(this.password, customServer.password) && Intrinsics.areEqual(this.serviceType, customServer.serviceType) && this.isForImapOnly == customServer.isForImapOnly && this.needCaptcha == customServer.needCaptcha && this.isReplace == customServer.isReplace && Intrinsics.areEqual(this.result, customServer.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        public final boolean getNeedCaptcha() {
            return this.needCaptcha;
        }

        @NotNull
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        public final String getServiceType() {
            return this.serviceType;
        }

        public int hashCode() {
            int iHashCode = ((((((((((this.login.hashCode() * 31) + this.password.hashCode()) * 31) + this.serviceType.hashCode()) * 31) + Boolean.hashCode(this.isForImapOnly)) * 31) + Boolean.hashCode(this.needCaptcha)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            CustomServerResult customServerResult = this.result;
            return iHashCode + (customServerResult == null ? 0 : customServerResult.hashCode());
        }

        public final boolean isForImapOnly() {
            return this.isForImapOnly;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "CustomServer(login=" + this.login + ", password=" + this.password + ", serviceType=" + this.serviceType + ", isForImapOnly=" + this.isForImapOnly + ", needCaptcha=" + this.needCaptcha + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.login);
            dest.writeString(this.password);
            dest.writeString(this.serviceType);
            dest.writeInt(this.isForImapOnly ? 1 : 0);
            dest.writeInt(this.needCaptcha ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public CustomServerResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable CustomServerResult customServerResult) {
            this.result = customServerResult;
        }

        public /* synthetic */ CustomServer(String str, String str2, String str3, boolean z10, boolean z11, boolean z12, CustomServerResult customServerResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11, (i10 & 32) != 0 ? false : z12, (i10 & 64) != 0 ? null : customServerResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00042\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0015HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\u000bR\u001a\u0010\u0014\u001a\u00020\u0015X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterEmailCode;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeResult;", "shouldSwitchToSms", "", "shouldResendCode", "isReplace", "result", "<init>", "(ZZZLru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeResult;)V", "getShouldSwitchToSms", "()Z", "getShouldResendCode", "getResult", "()Lru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterEmailCode extends Screen<EnterEmailCodeResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private EnterEmailCodeResult result;
        private final boolean shouldResendCode;
        private final boolean shouldSwitchToSms;

        @NotNull
        public static final Parcelable.Creator<EnterEmailCode> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<EnterEmailCode> {
            @Override // android.os.Parcelable.Creator
            public final EnterEmailCode createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new EnterEmailCode(parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt() != 0, (EnterEmailCodeResult) parcel.readParcelable(EnterEmailCode.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final EnterEmailCode[] newArray(int i10) {
                return new EnterEmailCode[i10];
            }
        }

        public EnterEmailCode() {
            this(false, false, false, null, 15, null);
        }

        public static /* synthetic */ EnterEmailCode copy$default(EnterEmailCode enterEmailCode, boolean z10, boolean z11, boolean z12, EnterEmailCodeResult enterEmailCodeResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = enterEmailCode.shouldSwitchToSms;
            }
            if ((i10 & 2) != 0) {
                z11 = enterEmailCode.shouldResendCode;
            }
            if ((i10 & 4) != 0) {
                z12 = enterEmailCode.isReplace;
            }
            if ((i10 & 8) != 0) {
                enterEmailCodeResult = enterEmailCode.result;
            }
            return enterEmailCode.copy(z10, z11, z12, enterEmailCodeResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getShouldSwitchToSms() {
            return this.shouldSwitchToSms;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getShouldResendCode() {
            return this.shouldResendCode;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final EnterEmailCodeResult getResult() {
            return this.result;
        }

        @NotNull
        public final EnterEmailCode copy(boolean shouldSwitchToSms, boolean shouldResendCode, boolean isReplace, @Nullable EnterEmailCodeResult result) {
            return new EnterEmailCode(shouldSwitchToSms, shouldResendCode, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnterEmailCode)) {
                return false;
            }
            EnterEmailCode enterEmailCode = (EnterEmailCode) other;
            return this.shouldSwitchToSms == enterEmailCode.shouldSwitchToSms && this.shouldResendCode == enterEmailCode.shouldResendCode && this.isReplace == enterEmailCode.isReplace && Intrinsics.areEqual(this.result, enterEmailCode.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public final boolean getShouldResendCode() {
            return this.shouldResendCode;
        }

        public final boolean getShouldSwitchToSms() {
            return this.shouldSwitchToSms;
        }

        public int hashCode() {
            int iHashCode = ((((Boolean.hashCode(this.shouldSwitchToSms) * 31) + Boolean.hashCode(this.shouldResendCode)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            EnterEmailCodeResult enterEmailCodeResult = this.result;
            return iHashCode + (enterEmailCodeResult == null ? 0 : enterEmailCodeResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "EnterEmailCode(shouldSwitchToSms=" + this.shouldSwitchToSms + ", shouldResendCode=" + this.shouldResendCode + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.shouldSwitchToSms ? 1 : 0);
            dest.writeInt(this.shouldResendCode ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public EnterEmailCode(boolean z10, boolean z11, boolean z12, @Nullable EnterEmailCodeResult enterEmailCodeResult) {
            super(null);
            this.shouldSwitchToSms = z10;
            this.shouldResendCode = z11;
            this.isReplace = z12;
            this.result = enterEmailCodeResult;
            this.isSupportBack = true;
            this.id = "EEC";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public EnterEmailCodeResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable EnterEmailCodeResult enterEmailCodeResult) {
            this.result = enterEmailCodeResult;
        }

        public /* synthetic */ EnterEmailCode(boolean z10, boolean z11, boolean z12, EnterEmailCodeResult enterEmailCodeResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? false : z11, (i10 & 4) != 0 ? false : z12, (i10 & 8) != 0 ? null : enterEmailCodeResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0013HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0005\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\nR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000f\u0010\nR\u001a\u0010\u0012\u001a\u00020\u0013X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0011\u001a\u0004\b\u0015\u0010\u0016¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterEmailCodeAfterListAcc;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccResult;", "shouldResendCode", "", "isReplace", "result", "<init>", "(ZZLru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccResult;)V", "getShouldResendCode", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterEmailCodeAfterListAcc extends Screen<EnterEmailCodeAfterListAccResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private EnterEmailCodeAfterListAccResult result;
        private final boolean shouldResendCode;

        @NotNull
        public static final Parcelable.Creator<EnterEmailCodeAfterListAcc> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<EnterEmailCodeAfterListAcc> {
            @Override // android.os.Parcelable.Creator
            public final EnterEmailCodeAfterListAcc createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new EnterEmailCodeAfterListAcc(parcel.readInt() != 0, parcel.readInt() != 0, (EnterEmailCodeAfterListAccResult) parcel.readParcelable(EnterEmailCodeAfterListAcc.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final EnterEmailCodeAfterListAcc[] newArray(int i10) {
                return new EnterEmailCodeAfterListAcc[i10];
            }
        }

        public EnterEmailCodeAfterListAcc() {
            this(false, false, null, 7, null);
        }

        public static /* synthetic */ EnterEmailCodeAfterListAcc copy$default(EnterEmailCodeAfterListAcc enterEmailCodeAfterListAcc, boolean z10, boolean z11, EnterEmailCodeAfterListAccResult enterEmailCodeAfterListAccResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = enterEmailCodeAfterListAcc.shouldResendCode;
            }
            if ((i10 & 2) != 0) {
                z11 = enterEmailCodeAfterListAcc.isReplace;
            }
            if ((i10 & 4) != 0) {
                enterEmailCodeAfterListAccResult = enterEmailCodeAfterListAcc.result;
            }
            return enterEmailCodeAfterListAcc.copy(z10, z11, enterEmailCodeAfterListAccResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getShouldResendCode() {
            return this.shouldResendCode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final EnterEmailCodeAfterListAccResult getResult() {
            return this.result;
        }

        @NotNull
        public final EnterEmailCodeAfterListAcc copy(boolean shouldResendCode, boolean isReplace, @Nullable EnterEmailCodeAfterListAccResult result) {
            return new EnterEmailCodeAfterListAcc(shouldResendCode, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnterEmailCodeAfterListAcc)) {
                return false;
            }
            EnterEmailCodeAfterListAcc enterEmailCodeAfterListAcc = (EnterEmailCodeAfterListAcc) other;
            return this.shouldResendCode == enterEmailCodeAfterListAcc.shouldResendCode && this.isReplace == enterEmailCodeAfterListAcc.isReplace && Intrinsics.areEqual(this.result, enterEmailCodeAfterListAcc.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public final boolean getShouldResendCode() {
            return this.shouldResendCode;
        }

        public int hashCode() {
            int iHashCode = ((Boolean.hashCode(this.shouldResendCode) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            EnterEmailCodeAfterListAccResult enterEmailCodeAfterListAccResult = this.result;
            return iHashCode + (enterEmailCodeAfterListAccResult == null ? 0 : enterEmailCodeAfterListAccResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "EnterEmailCodeAfterListAcc(shouldResendCode=" + this.shouldResendCode + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.shouldResendCode ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public EnterEmailCodeAfterListAcc(boolean z10, boolean z11, @Nullable EnterEmailCodeAfterListAccResult enterEmailCodeAfterListAccResult) {
            super(null);
            this.shouldResendCode = z10;
            this.isReplace = z11;
            this.result = enterEmailCodeAfterListAccResult;
            this.isSupportBack = true;
            this.id = "EECAL";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public EnterEmailCodeAfterListAccResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable EnterEmailCodeAfterListAccResult enterEmailCodeAfterListAccResult) {
            this.result = enterEmailCodeAfterListAccResult;
        }

        public /* synthetic */ EnterEmailCodeAfterListAcc(boolean z10, boolean z11, EnterEmailCodeAfterListAccResult enterEmailCodeAfterListAccResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? false : z11, (i10 & 4) != 0 ? null : enterEmailCodeAfterListAccResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u001f\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterPhone;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneResult;", "isReplace", "", "result", "<init>", "(ZLru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneResult;)V", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneResult;", "setResult", "(Lru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterPhone extends Screen<EnterPhoneResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private EnterPhoneResult result;

        @NotNull
        public static final Parcelable.Creator<EnterPhone> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<EnterPhone> {
            @Override // android.os.Parcelable.Creator
            public final EnterPhone createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new EnterPhone(parcel.readInt() != 0, (EnterPhoneResult) parcel.readParcelable(EnterPhone.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final EnterPhone[] newArray(int i10) {
                return new EnterPhone[i10];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public EnterPhone() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ EnterPhone copy$default(EnterPhone enterPhone, boolean z10, EnterPhoneResult enterPhoneResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = enterPhone.isReplace;
            }
            if ((i10 & 2) != 0) {
                enterPhoneResult = enterPhone.result;
            }
            return enterPhone.copy(z10, enterPhoneResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final EnterPhoneResult getResult() {
            return this.result;
        }

        @NotNull
        public final EnterPhone copy(boolean isReplace, @Nullable EnterPhoneResult result) {
            return new EnterPhone(isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnterPhone)) {
                return false;
            }
            EnterPhone enterPhone = (EnterPhone) other;
            return this.isReplace == enterPhone.isReplace && Intrinsics.areEqual(this.result, enterPhone.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.isReplace) * 31;
            EnterPhoneResult enterPhoneResult = this.result;
            return iHashCode + (enterPhoneResult == null ? 0 : enterPhoneResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "EnterPhone(isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public EnterPhone(boolean z10, @Nullable EnterPhoneResult enterPhoneResult) {
            super(null);
            this.isReplace = z10;
            this.result = enterPhoneResult;
            this.id = "E";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public EnterPhoneResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable EnterPhoneResult enterPhoneResult) {
            this.result = enterPhoneResult;
        }

        public /* synthetic */ EnterPhone(boolean z10, EnterPhoneResult enterPhoneResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? null : enterPhoneResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001d\u001a\u00020\u0004J\u0013\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\t\u0010\"\u001a\u00020\u0015HÖ\u0001J\u0016\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0015X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018¨\u0006("}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EnterPhoneCode;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeResult;", "codeReceiveType", "", "isReplace", "", "result", "<init>", "(IZLru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeResult;)V", "getCodeReceiveType", "()I", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EnterPhoneCode extends Screen<EnterPhoneCodeResult> {
        private final int codeReceiveType;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private EnterPhoneCodeResult result;

        @NotNull
        public static final Parcelable.Creator<EnterPhoneCode> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<EnterPhoneCode> {
            @Override // android.os.Parcelable.Creator
            public final EnterPhoneCode createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new EnterPhoneCode(parcel.readInt(), parcel.readInt() != 0, (EnterPhoneCodeResult) parcel.readParcelable(EnterPhoneCode.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final EnterPhoneCode[] newArray(int i10) {
                return new EnterPhoneCode[i10];
            }
        }

        public EnterPhoneCode() {
            this(0, false, null, 7, null);
        }

        public static /* synthetic */ EnterPhoneCode copy$default(EnterPhoneCode enterPhoneCode, int i10, boolean z10, EnterPhoneCodeResult enterPhoneCodeResult, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = enterPhoneCode.codeReceiveType;
            }
            if ((i11 & 2) != 0) {
                z10 = enterPhoneCode.isReplace;
            }
            if ((i11 & 4) != 0) {
                enterPhoneCodeResult = enterPhoneCode.result;
            }
            return enterPhoneCode.copy(i10, z10, enterPhoneCodeResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCodeReceiveType() {
            return this.codeReceiveType;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final EnterPhoneCodeResult getResult() {
            return this.result;
        }

        @NotNull
        public final EnterPhoneCode copy(int codeReceiveType, boolean isReplace, @Nullable EnterPhoneCodeResult result) {
            return new EnterPhoneCode(codeReceiveType, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EnterPhoneCode)) {
                return false;
            }
            EnterPhoneCode enterPhoneCode = (EnterPhoneCode) other;
            return this.codeReceiveType == enterPhoneCode.codeReceiveType && this.isReplace == enterPhoneCode.isReplace && Intrinsics.areEqual(this.result, enterPhoneCode.result);
        }

        public final int getCodeReceiveType() {
            return this.codeReceiveType;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((Integer.hashCode(this.codeReceiveType) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            EnterPhoneCodeResult enterPhoneCodeResult = this.result;
            return iHashCode + (enterPhoneCodeResult == null ? 0 : enterPhoneCodeResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "EnterPhoneCode(codeReceiveType=" + this.codeReceiveType + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.codeReceiveType);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public EnterPhoneCode(int i10, boolean z10, @Nullable EnterPhoneCodeResult enterPhoneCodeResult) {
            super(null);
            this.codeReceiveType = i10;
            this.isReplace = z10;
            this.result = enterPhoneCodeResult;
            this.isSupportBack = true;
            this.id = "EC";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public EnterPhoneCodeResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable EnterPhoneCodeResult enterPhoneCodeResult) {
            this.result = enterPhoneCodeResult;
        }

        public /* synthetic */ EnterPhoneCode(int i10, boolean z10, EnterPhoneCodeResult enterPhoneCodeResult, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this((i11 & 1) != 0 ? -1 : i10, (i11 & 2) != 0 ? false : z10, (i11 & 4) != 0 ? null : enterPhoneCodeResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$EsiaAuthScreen;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/socialauth/esiascreen/EsiaScreenResult;", "url", "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/socialauth/esiascreen/EsiaScreenResult;)V", "getUrl", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/socialauth/esiascreen/EsiaScreenResult;", "setResult", "(Lru/mail/authorizationsdk/feature/socialauth/esiascreen/EsiaScreenResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class EsiaAuthScreen extends Screen<EsiaScreenResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private EsiaScreenResult result;

        @NotNull
        private final String url;

        @NotNull
        public static final Parcelable.Creator<EsiaAuthScreen> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<EsiaAuthScreen> {
            @Override // android.os.Parcelable.Creator
            public final EsiaAuthScreen createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new EsiaAuthScreen(parcel.readString(), parcel.readInt() != 0, (EsiaScreenResult) parcel.readParcelable(EsiaAuthScreen.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final EsiaAuthScreen[] newArray(int i10) {
                return new EsiaAuthScreen[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EsiaAuthScreen(@NotNull String url, boolean z10, @Nullable EsiaScreenResult esiaScreenResult) {
            super(null);
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
            this.isReplace = z10;
            this.result = esiaScreenResult;
            this.id = "Esia";
        }

        public static /* synthetic */ EsiaAuthScreen copy$default(EsiaAuthScreen esiaAuthScreen, String str, boolean z10, EsiaScreenResult esiaScreenResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = esiaAuthScreen.url;
            }
            if ((i10 & 2) != 0) {
                z10 = esiaAuthScreen.isReplace;
            }
            if ((i10 & 4) != 0) {
                esiaScreenResult = esiaAuthScreen.result;
            }
            return esiaAuthScreen.copy(str, z10, esiaScreenResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final EsiaScreenResult getResult() {
            return this.result;
        }

        @NotNull
        public final EsiaAuthScreen copy(@NotNull String url, boolean isReplace, @Nullable EsiaScreenResult result) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new EsiaAuthScreen(url, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof EsiaAuthScreen)) {
                return false;
            }
            EsiaAuthScreen esiaAuthScreen = (EsiaAuthScreen) other;
            return Intrinsics.areEqual(this.url, esiaAuthScreen.url) && this.isReplace == esiaAuthScreen.isReplace && Intrinsics.areEqual(this.result, esiaAuthScreen.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            int iHashCode = ((this.url.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            EsiaScreenResult esiaScreenResult = this.result;
            return iHashCode + (esiaScreenResult == null ? 0 : esiaScreenResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "EsiaAuthScreen(url=" + this.url + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.url);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public EsiaScreenResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable EsiaScreenResult esiaScreenResult) {
            this.result = esiaScreenResult;
        }

        public /* synthetic */ EsiaAuthScreen(String str, boolean z10, EsiaScreenResult esiaScreenResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : esiaScreenResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003J=\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010 \u001a\u00020!J\u0013\u0010\"\u001a\u00020\u00072\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020!HÖ\u0001J\t\u0010&\u001a\u00020\u0004HÖ\u0001J\u0016\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020!R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000fR\u0014\u0010\b\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0014\u0010\u000fR\u001a\u0010\u0017\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0018\u0010\u0016\u001a\u0004\b\u0019\u0010\r¨\u0006,"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ExternalAccMigration;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;", "mode", "", "email", "isNeedChangeInsets", "", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZZLru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;)V", "getMode", "()Ljava/lang/String;", "getEmail", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;", "setResult", "(Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ExternalAccMigration extends Screen<ExternalAccountMigrationResult> {

        @NotNull
        private final String email;

        @NotNull
        private final String id;
        private final boolean isNeedChangeInsets;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String mode;

        @Nullable
        private ExternalAccountMigrationResult result;

        @NotNull
        public static final Parcelable.Creator<ExternalAccMigration> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ExternalAccMigration> {
            @Override // android.os.Parcelable.Creator
            public final ExternalAccMigration createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                boolean z10 = false;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                return new ExternalAccMigration(string, string2, z10, parcel.readInt() != 0, (ExternalAccountMigrationResult) parcel.readParcelable(ExternalAccMigration.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final ExternalAccMigration[] newArray(int i10) {
                return new ExternalAccMigration[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExternalAccMigration(@NotNull String mode, @NotNull String email, boolean z10, boolean z11, @Nullable ExternalAccountMigrationResult externalAccountMigrationResult) {
            super(null);
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(email, "email");
            this.mode = mode;
            this.email = email;
            this.isNeedChangeInsets = z10;
            this.isReplace = z11;
            this.result = externalAccountMigrationResult;
            this.id = "e";
        }

        public static /* synthetic */ ExternalAccMigration copy$default(ExternalAccMigration externalAccMigration, String str, String str2, boolean z10, boolean z11, ExternalAccountMigrationResult externalAccountMigrationResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = externalAccMigration.mode;
            }
            if ((i10 & 2) != 0) {
                str2 = externalAccMigration.email;
            }
            if ((i10 & 4) != 0) {
                z10 = externalAccMigration.isNeedChangeInsets;
            }
            if ((i10 & 8) != 0) {
                z11 = externalAccMigration.isReplace;
            }
            if ((i10 & 16) != 0) {
                externalAccountMigrationResult = externalAccMigration.result;
            }
            ExternalAccountMigrationResult externalAccountMigrationResult2 = externalAccountMigrationResult;
            boolean z12 = z10;
            return externalAccMigration.copy(str, str2, z12, z11, externalAccountMigrationResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMode() {
            return this.mode;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsNeedChangeInsets() {
            return this.isNeedChangeInsets;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final ExternalAccountMigrationResult getResult() {
            return this.result;
        }

        @NotNull
        public final ExternalAccMigration copy(@NotNull String mode, @NotNull String email, boolean isNeedChangeInsets, boolean isReplace, @Nullable ExternalAccountMigrationResult result) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            Intrinsics.checkNotNullParameter(email, "email");
            return new ExternalAccMigration(mode, email, isNeedChangeInsets, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ExternalAccMigration)) {
                return false;
            }
            ExternalAccMigration externalAccMigration = (ExternalAccMigration) other;
            return Intrinsics.areEqual(this.mode, externalAccMigration.mode) && Intrinsics.areEqual(this.email, externalAccMigration.email) && this.isNeedChangeInsets == externalAccMigration.isNeedChangeInsets && this.isReplace == externalAccMigration.isReplace && Intrinsics.areEqual(this.result, externalAccMigration.result);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getMode() {
            return this.mode;
        }

        public int hashCode() {
            int iHashCode = ((((((this.mode.hashCode() * 31) + this.email.hashCode()) * 31) + Boolean.hashCode(this.isNeedChangeInsets)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            ExternalAccountMigrationResult externalAccountMigrationResult = this.result;
            return iHashCode + (externalAccountMigrationResult == null ? 0 : externalAccountMigrationResult.hashCode());
        }

        public final boolean isNeedChangeInsets() {
            return this.isNeedChangeInsets;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "ExternalAccMigration(mode=" + this.mode + ", email=" + this.email + ", isNeedChangeInsets=" + this.isNeedChangeInsets + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.mode);
            dest.writeString(this.email);
            dest.writeInt(this.isNeedChangeInsets ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public ExternalAccountMigrationResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable ExternalAccountMigrationResult externalAccountMigrationResult) {
            this.result = externalAccountMigrationResult;
        }

        public /* synthetic */ ExternalAccMigration(String str, String str2, boolean z10, boolean z11, ExternalAccountMigrationResult externalAccountMigrationResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, z10, (i10 & 8) != 0 ? false : z11, (i10 & 16) != 0 ? null : externalAccountMigrationResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B1\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$GoogleNative;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;", VkCitySelectFragment.HINT_KEY, "", "xmailMigrationFrom", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;)V", "getHint", "()Ljava/lang/String;", "getXmailMigrationFrom", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;", "setResult", "(Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class GoogleNative extends Screen<GoogleResult> {

        @NotNull
        private final String hint;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private GoogleResult result;

        @NotNull
        private final String xmailMigrationFrom;

        @NotNull
        public static final Parcelable.Creator<GoogleNative> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<GoogleNative> {
            @Override // android.os.Parcelable.Creator
            public final GoogleNative createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new GoogleNative(parcel.readString(), parcel.readString(), parcel.readInt() != 0, (GoogleResult) parcel.readParcelable(GoogleNative.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final GoogleNative[] newArray(int i10) {
                return new GoogleNative[i10];
            }
        }

        public GoogleNative() {
            this(null, null, false, null, 15, null);
        }

        public static /* synthetic */ GoogleNative copy$default(GoogleNative googleNative, String str, String str2, boolean z10, GoogleResult googleResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = googleNative.hint;
            }
            if ((i10 & 2) != 0) {
                str2 = googleNative.xmailMigrationFrom;
            }
            if ((i10 & 4) != 0) {
                z10 = googleNative.isReplace;
            }
            if ((i10 & 8) != 0) {
                googleResult = googleNative.result;
            }
            return googleNative.copy(str, str2, z10, googleResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHint() {
            return this.hint;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getXmailMigrationFrom() {
            return this.xmailMigrationFrom;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final GoogleResult getResult() {
            return this.result;
        }

        @NotNull
        public final GoogleNative copy(@NotNull String hint, @NotNull String xmailMigrationFrom, boolean isReplace, @Nullable GoogleResult result) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            Intrinsics.checkNotNullParameter(xmailMigrationFrom, "xmailMigrationFrom");
            return new GoogleNative(hint, xmailMigrationFrom, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GoogleNative)) {
                return false;
            }
            GoogleNative googleNative = (GoogleNative) other;
            return Intrinsics.areEqual(this.hint, googleNative.hint) && Intrinsics.areEqual(this.xmailMigrationFrom, googleNative.xmailMigrationFrom) && this.isReplace == googleNative.isReplace && Intrinsics.areEqual(this.result, googleNative.result);
        }

        @NotNull
        public final String getHint() {
            return this.hint;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getXmailMigrationFrom() {
            return this.xmailMigrationFrom;
        }

        public int hashCode() {
            int iHashCode = ((((this.hint.hashCode() * 31) + this.xmailMigrationFrom.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            GoogleResult googleResult = this.result;
            return iHashCode + (googleResult == null ? 0 : googleResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "GoogleNative(hint=" + this.hint + ", xmailMigrationFrom=" + this.xmailMigrationFrom + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.hint);
            dest.writeString(this.xmailMigrationFrom);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GoogleNative(@NotNull String hint, @NotNull String xmailMigrationFrom, boolean z10, @Nullable GoogleResult googleResult) {
            super(null);
            Intrinsics.checkNotNullParameter(hint, "hint");
            Intrinsics.checkNotNullParameter(xmailMigrationFrom, "xmailMigrationFrom");
            this.hint = hint;
            this.xmailMigrationFrom = xmailMigrationFrom;
            this.isReplace = z10;
            this.result = googleResult;
            this.id = RequestConfiguration.MAX_AD_CONTENT_RATING_G;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public GoogleResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable GoogleResult googleResult) {
            this.result = googleResult;
        }

        public /* synthetic */ GoogleNative(String str, String str2, boolean z10, GoogleResult googleResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? "" : str, (i10 & 2) != 0 ? "" : str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? null : googleResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$GoogleWeb;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;", VkCitySelectFragment.HINT_KEY, "", "xmailMigrationFrom", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;)V", "getHint", "()Ljava/lang/String;", "getXmailMigrationFrom", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;", "setResult", "(Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class GoogleWeb extends Screen<GoogleResult> {

        @NotNull
        private final String hint;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private GoogleResult result;

        @NotNull
        private final String xmailMigrationFrom;

        @NotNull
        public static final Parcelable.Creator<GoogleWeb> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<GoogleWeb> {
            @Override // android.os.Parcelable.Creator
            public final GoogleWeb createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new GoogleWeb(parcel.readString(), parcel.readString(), parcel.readInt() != 0, (GoogleResult) parcel.readParcelable(GoogleWeb.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final GoogleWeb[] newArray(int i10) {
                return new GoogleWeb[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GoogleWeb(@NotNull String hint, @NotNull String xmailMigrationFrom, boolean z10, @Nullable GoogleResult googleResult) {
            super(null);
            Intrinsics.checkNotNullParameter(hint, "hint");
            Intrinsics.checkNotNullParameter(xmailMigrationFrom, "xmailMigrationFrom");
            this.hint = hint;
            this.xmailMigrationFrom = xmailMigrationFrom;
            this.isReplace = z10;
            this.result = googleResult;
            this.id = "g";
        }

        public static /* synthetic */ GoogleWeb copy$default(GoogleWeb googleWeb, String str, String str2, boolean z10, GoogleResult googleResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = googleWeb.hint;
            }
            if ((i10 & 2) != 0) {
                str2 = googleWeb.xmailMigrationFrom;
            }
            if ((i10 & 4) != 0) {
                z10 = googleWeb.isReplace;
            }
            if ((i10 & 8) != 0) {
                googleResult = googleWeb.result;
            }
            return googleWeb.copy(str, str2, z10, googleResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHint() {
            return this.hint;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getXmailMigrationFrom() {
            return this.xmailMigrationFrom;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final GoogleResult getResult() {
            return this.result;
        }

        @NotNull
        public final GoogleWeb copy(@NotNull String hint, @NotNull String xmailMigrationFrom, boolean isReplace, @Nullable GoogleResult result) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            Intrinsics.checkNotNullParameter(xmailMigrationFrom, "xmailMigrationFrom");
            return new GoogleWeb(hint, xmailMigrationFrom, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof GoogleWeb)) {
                return false;
            }
            GoogleWeb googleWeb = (GoogleWeb) other;
            return Intrinsics.areEqual(this.hint, googleWeb.hint) && Intrinsics.areEqual(this.xmailMigrationFrom, googleWeb.xmailMigrationFrom) && this.isReplace == googleWeb.isReplace && Intrinsics.areEqual(this.result, googleWeb.result);
        }

        @NotNull
        public final String getHint() {
            return this.hint;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getXmailMigrationFrom() {
            return this.xmailMigrationFrom;
        }

        public int hashCode() {
            int iHashCode = ((((this.hint.hashCode() * 31) + this.xmailMigrationFrom.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            GoogleResult googleResult = this.result;
            return iHashCode + (googleResult == null ? 0 : googleResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "GoogleWeb(hint=" + this.hint + ", xmailMigrationFrom=" + this.xmailMigrationFrom + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.hint);
            dest.writeString(this.xmailMigrationFrom);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public GoogleResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable GoogleResult googleResult) {
            this.result = googleResult;
        }

        public /* synthetic */ GoogleWeb(String str, String str2, boolean z10, GoogleResult googleResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? null : googleResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B_\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ja\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0006\u0010(\u001a\u00020)J\u0013\u0010*\u001a\u00020\t2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020)HÖ\u0001J\t\u0010.\u001a\u00020\u0005HÖ\u0001J\u0016\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020)R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\tX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u0005X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001e\u0010\u0011¨\u00064"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Login;", "Lru/mail/authorizationsdk/feature/authactivity/screens/LoginLogicScreen;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/login/presentation/common/LoginResult;", "email", "", "from", "serviceType", "isManualLogout", "", "isDeeplinkOpened", "isNeedStartRestore", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLru/mail/authorizationsdk/feature/login/presentation/common/LoginResult;)V", "getEmail", "()Ljava/lang/String;", "getFrom", "getServiceType", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/login/presentation/common/LoginResult;", "setResult", "(Lru/mail/authorizationsdk/feature/login/presentation/common/LoginResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Login extends Screen<LoginResult> implements LoginLogicScreen {

        @Nullable
        private final String email;

        @Nullable
        private final String from;

        @NotNull
        private final String id;
        private final boolean isDeeplinkOpened;
        private final boolean isManualLogout;
        private final boolean isNeedStartRestore;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private LoginResult result;

        @Nullable
        private final String serviceType;

        @NotNull
        public static final Parcelable.Creator<Login> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Login> {
            @Override // android.os.Parcelable.Creator
            public final Login createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new Login(string, string2, string3, z10, z11, z11, parcel.readInt() != 0, (LoginResult) parcel.readParcelable(Login.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Login[] newArray(int i10) {
                return new Login[i10];
            }
        }

        public Login() {
            this(null, null, null, false, false, false, false, null, 255, null);
        }

        public static /* synthetic */ Login copy$default(Login login, String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, LoginResult loginResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = login.email;
            }
            if ((i10 & 2) != 0) {
                str2 = login.from;
            }
            if ((i10 & 4) != 0) {
                str3 = login.serviceType;
            }
            if ((i10 & 8) != 0) {
                z10 = login.isManualLogout;
            }
            if ((i10 & 16) != 0) {
                z11 = login.isDeeplinkOpened;
            }
            if ((i10 & 32) != 0) {
                z12 = login.isNeedStartRestore;
            }
            if ((i10 & 64) != 0) {
                z13 = login.isReplace;
            }
            if ((i10 & 128) != 0) {
                loginResult = login.result;
            }
            boolean z14 = z13;
            LoginResult loginResult2 = loginResult;
            boolean z15 = z11;
            boolean z16 = z12;
            return login.copy(str, str2, str3, z10, z15, z16, z14, loginResult2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsManualLogout() {
            return this.isManualLogout;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final LoginResult getResult() {
            return this.result;
        }

        @NotNull
        public final Login copy(@Nullable String email, @Nullable String from, @Nullable String serviceType, boolean isManualLogout, boolean isDeeplinkOpened, boolean isNeedStartRestore, boolean isReplace, @Nullable LoginResult result) {
            return new Login(email, from, serviceType, isManualLogout, isDeeplinkOpened, isNeedStartRestore, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Login)) {
                return false;
            }
            Login login = (Login) other;
            return Intrinsics.areEqual(this.email, login.email) && Intrinsics.areEqual(this.from, login.from) && Intrinsics.areEqual(this.serviceType, login.serviceType) && this.isManualLogout == login.isManualLogout && this.isDeeplinkOpened == login.isDeeplinkOpened && this.isNeedStartRestore == login.isNeedStartRestore && this.isReplace == login.isReplace && Intrinsics.areEqual(this.result, login.result);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        public final String getFrom() {
            return this.from;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @Nullable
        public final String getServiceType() {
            return this.serviceType;
        }

        public int hashCode() {
            String str = this.email;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.from;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.serviceType;
            int iHashCode3 = (((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.isManualLogout)) * 31) + Boolean.hashCode(this.isDeeplinkOpened)) * 31) + Boolean.hashCode(this.isNeedStartRestore)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            LoginResult loginResult = this.result;
            return iHashCode3 + (loginResult != null ? loginResult.hashCode() : 0);
        }

        public final boolean isDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        public final boolean isManualLogout() {
            return this.isManualLogout;
        }

        public final boolean isNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "Login(email=" + this.email + ", from=" + this.from + ", serviceType=" + this.serviceType + ", isManualLogout=" + this.isManualLogout + ", isDeeplinkOpened=" + this.isDeeplinkOpened + ", isNeedStartRestore=" + this.isNeedStartRestore + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.from);
            dest.writeString(this.serviceType);
            dest.writeInt(this.isManualLogout ? 1 : 0);
            dest.writeInt(this.isDeeplinkOpened ? 1 : 0);
            dest.writeInt(this.isNeedStartRestore ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public Login(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z10, boolean z11, boolean z12, boolean z13, @Nullable LoginResult loginResult) {
            super(null);
            this.email = str;
            this.from = str2;
            this.serviceType = str3;
            this.isManualLogout = z10;
            this.isDeeplinkOpened = z11;
            this.isNeedStartRestore = z12;
            this.isReplace = z13;
            this.result = loginResult;
            this.isSupportBack = true;
            this.id = "L";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public LoginResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable LoginResult loginResult) {
            this.result = loginResult;
        }

        public /* synthetic */ Login(String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, LoginResult loginResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11, (i10 & 32) != 0 ? false : z12, (i10 & 64) != 0 ? false : z13, (i10 & 128) != 0 ? null : loginResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BY\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000b\u0010!\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010$\u001a\u00020\u0004HÆ\u0003J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0002HÆ\u0003Ja\u0010)\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010*\u001a\u00020+J\u0013\u0010,\u001a\u00020\u000b2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u00020+HÖ\u0001J\t\u00100\u001a\u00020\u0004HÖ\u0001J\u0016\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020+R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0016R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u000bX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001b\u0010\u0016R\u001a\u0010\u001e\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001f\u0010\u001d\u001a\u0004\b \u0010\u0010¨\u00066"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$LoginBindFlow;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowResult;", "email", "", "from", "serviceType", "bindToken", "socialBindType", "vkAccessToken", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowResult;)V", "getEmail", "()Ljava/lang/String;", "getFrom", "getServiceType", "getBindToken", "getSocialBindType", "getVkAccessToken", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowResult;", "setResult", "(Lru/mail/authorizationsdk/feature/loginbindflow/presentation/LoginBindFlowResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class LoginBindFlow extends Screen<LoginBindFlowResult> {

        @NotNull
        private final String bindToken;

        @Nullable
        private final String email;

        @Nullable
        private final String from;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private LoginBindFlowResult result;

        @Nullable
        private final String serviceType;

        @NotNull
        private final String socialBindType;

        @NotNull
        private final String vkAccessToken;

        @NotNull
        public static final Parcelable.Creator<LoginBindFlow> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<LoginBindFlow> {
            @Override // android.os.Parcelable.Creator
            public final LoginBindFlow createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new LoginBindFlow(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, (LoginBindFlowResult) parcel.readParcelable(LoginBindFlow.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final LoginBindFlow[] newArray(int i10) {
                return new LoginBindFlow[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LoginBindFlow(@Nullable String str, @Nullable String str2, @Nullable String str3, @NotNull String bindToken, @NotNull String socialBindType, @NotNull String vkAccessToken, boolean z10, @Nullable LoginBindFlowResult loginBindFlowResult) {
            super(null);
            Intrinsics.checkNotNullParameter(bindToken, "bindToken");
            Intrinsics.checkNotNullParameter(socialBindType, "socialBindType");
            Intrinsics.checkNotNullParameter(vkAccessToken, "vkAccessToken");
            this.email = str;
            this.from = str2;
            this.serviceType = str3;
            this.bindToken = bindToken;
            this.socialBindType = socialBindType;
            this.vkAccessToken = vkAccessToken;
            this.isReplace = z10;
            this.result = loginBindFlowResult;
            this.isSupportBack = true;
            this.id = "l";
        }

        public static /* synthetic */ LoginBindFlow copy$default(LoginBindFlow loginBindFlow, String str, String str2, String str3, String str4, String str5, String str6, boolean z10, LoginBindFlowResult loginBindFlowResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = loginBindFlow.email;
            }
            if ((i10 & 2) != 0) {
                str2 = loginBindFlow.from;
            }
            if ((i10 & 4) != 0) {
                str3 = loginBindFlow.serviceType;
            }
            if ((i10 & 8) != 0) {
                str4 = loginBindFlow.bindToken;
            }
            if ((i10 & 16) != 0) {
                str5 = loginBindFlow.socialBindType;
            }
            if ((i10 & 32) != 0) {
                str6 = loginBindFlow.vkAccessToken;
            }
            if ((i10 & 64) != 0) {
                z10 = loginBindFlow.isReplace;
            }
            if ((i10 & 128) != 0) {
                loginBindFlowResult = loginBindFlow.result;
            }
            boolean z11 = z10;
            LoginBindFlowResult loginBindFlowResult2 = loginBindFlowResult;
            String str7 = str5;
            String str8 = str6;
            return loginBindFlow.copy(str, str2, str3, str4, str7, str8, z11, loginBindFlowResult2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getBindToken() {
            return this.bindToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @NotNull
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final LoginBindFlowResult getResult() {
            return this.result;
        }

        @NotNull
        public final LoginBindFlow copy(@Nullable String email, @Nullable String from, @Nullable String serviceType, @NotNull String bindToken, @NotNull String socialBindType, @NotNull String vkAccessToken, boolean isReplace, @Nullable LoginBindFlowResult result) {
            Intrinsics.checkNotNullParameter(bindToken, "bindToken");
            Intrinsics.checkNotNullParameter(socialBindType, "socialBindType");
            Intrinsics.checkNotNullParameter(vkAccessToken, "vkAccessToken");
            return new LoginBindFlow(email, from, serviceType, bindToken, socialBindType, vkAccessToken, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoginBindFlow)) {
                return false;
            }
            LoginBindFlow loginBindFlow = (LoginBindFlow) other;
            return Intrinsics.areEqual(this.email, loginBindFlow.email) && Intrinsics.areEqual(this.from, loginBindFlow.from) && Intrinsics.areEqual(this.serviceType, loginBindFlow.serviceType) && Intrinsics.areEqual(this.bindToken, loginBindFlow.bindToken) && Intrinsics.areEqual(this.socialBindType, loginBindFlow.socialBindType) && Intrinsics.areEqual(this.vkAccessToken, loginBindFlow.vkAccessToken) && this.isReplace == loginBindFlow.isReplace && Intrinsics.areEqual(this.result, loginBindFlow.result);
        }

        @NotNull
        public final String getBindToken() {
            return this.bindToken;
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        public final String getFrom() {
            return this.from;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @Nullable
        public final String getServiceType() {
            return this.serviceType;
        }

        @NotNull
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @NotNull
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        public int hashCode() {
            String str = this.email;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.from;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.serviceType;
            int iHashCode3 = (((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + this.bindToken.hashCode()) * 31) + this.socialBindType.hashCode()) * 31) + this.vkAccessToken.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            LoginBindFlowResult loginBindFlowResult = this.result;
            return iHashCode3 + (loginBindFlowResult != null ? loginBindFlowResult.hashCode() : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "LoginBindFlow(email=" + this.email + ", from=" + this.from + ", serviceType=" + this.serviceType + ", bindToken=" + this.bindToken + ", socialBindType=" + this.socialBindType + ", vkAccessToken=" + this.vkAccessToken + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.from);
            dest.writeString(this.serviceType);
            dest.writeString(this.bindToken);
            dest.writeString(this.socialBindType);
            dest.writeString(this.vkAccessToken);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public LoginBindFlowResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable LoginBindFlowResult loginBindFlowResult) {
            this.result = loginBindFlowResult;
        }

        public /* synthetic */ LoginBindFlow(String str, String str2, String str3, String str4, String str5, String str6, boolean z10, LoginBindFlowResult loginBindFlowResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, str4, str5, str6, (i10 & 64) != 0 ? false : z10, (i10 & 128) != 0 ? null : loginBindFlowResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B_\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\tHÆ\u0003J\t\u0010%\u001a\u00020\tHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003Ja\u0010'\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0006\u0010(\u001a\u00020)J\u0013\u0010*\u001a\u00020\t2\b\u0010+\u001a\u0004\u0018\u00010,HÖ\u0003J\t\u0010-\u001a\u00020)HÖ\u0001J\t\u0010.\u001a\u00020\u0005HÖ\u0001J\u0016\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020)R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0014R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0014R\u0011\u0010\u000b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0014R\u0014\u0010\f\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0014R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\tX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u0005X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001d\u0010\u001b\u001a\u0004\b\u001e\u0010\u0011¨\u00064"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$LoginVk;", "Lru/mail/authorizationsdk/feature/authactivity/screens/LoginLogicScreen;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/LoginVkResult;", "email", "", "from", "serviceType", "isManualLogout", "", "isDeeplinkOpened", "isNeedStartRestore", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZZLru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/LoginVkResult;)V", "getEmail", "()Ljava/lang/String;", "getFrom", "getServiceType", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/LoginVkResult;", "setResult", "(Lru/mail/authorizationsdk/feature/login/presentation/flavor/vkmail/LoginVkResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class LoginVk extends Screen<LoginVkResult> implements LoginLogicScreen {

        @Nullable
        private final String email;

        @Nullable
        private final String from;

        @NotNull
        private final String id;
        private final boolean isDeeplinkOpened;
        private final boolean isManualLogout;
        private final boolean isNeedStartRestore;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private LoginVkResult result;

        @Nullable
        private final String serviceType;

        @NotNull
        public static final Parcelable.Creator<LoginVk> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<LoginVk> {
            @Override // android.os.Parcelable.Creator
            public final LoginVk createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new LoginVk(string, string2, string3, z10, z11, z11, parcel.readInt() != 0, (LoginVkResult) parcel.readParcelable(LoginVk.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final LoginVk[] newArray(int i10) {
                return new LoginVk[i10];
            }
        }

        public LoginVk() {
            this(null, null, null, false, false, false, false, null, 255, null);
        }

        public static /* synthetic */ LoginVk copy$default(LoginVk loginVk, String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, LoginVkResult loginVkResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = loginVk.email;
            }
            if ((i10 & 2) != 0) {
                str2 = loginVk.from;
            }
            if ((i10 & 4) != 0) {
                str3 = loginVk.serviceType;
            }
            if ((i10 & 8) != 0) {
                z10 = loginVk.isManualLogout;
            }
            if ((i10 & 16) != 0) {
                z11 = loginVk.isDeeplinkOpened;
            }
            if ((i10 & 32) != 0) {
                z12 = loginVk.isNeedStartRestore;
            }
            if ((i10 & 64) != 0) {
                z13 = loginVk.isReplace;
            }
            if ((i10 & 128) != 0) {
                loginVkResult = loginVk.result;
            }
            boolean z14 = z13;
            LoginVkResult loginVkResult2 = loginVkResult;
            boolean z15 = z11;
            boolean z16 = z12;
            return loginVk.copy(str, str2, str3, z10, z15, z16, z14, loginVkResult2);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsManualLogout() {
            return this.isManualLogout;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final LoginVkResult getResult() {
            return this.result;
        }

        @NotNull
        public final LoginVk copy(@Nullable String email, @Nullable String from, @Nullable String serviceType, boolean isManualLogout, boolean isDeeplinkOpened, boolean isNeedStartRestore, boolean isReplace, @Nullable LoginVkResult result) {
            return new LoginVk(email, from, serviceType, isManualLogout, isDeeplinkOpened, isNeedStartRestore, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LoginVk)) {
                return false;
            }
            LoginVk loginVk = (LoginVk) other;
            return Intrinsics.areEqual(this.email, loginVk.email) && Intrinsics.areEqual(this.from, loginVk.from) && Intrinsics.areEqual(this.serviceType, loginVk.serviceType) && this.isManualLogout == loginVk.isManualLogout && this.isDeeplinkOpened == loginVk.isDeeplinkOpened && this.isNeedStartRestore == loginVk.isNeedStartRestore && this.isReplace == loginVk.isReplace && Intrinsics.areEqual(this.result, loginVk.result);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        public final String getFrom() {
            return this.from;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @Nullable
        public final String getServiceType() {
            return this.serviceType;
        }

        public int hashCode() {
            String str = this.email;
            int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
            String str2 = this.from;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.serviceType;
            int iHashCode3 = (((((((((iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Boolean.hashCode(this.isManualLogout)) * 31) + Boolean.hashCode(this.isDeeplinkOpened)) * 31) + Boolean.hashCode(this.isNeedStartRestore)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            LoginVkResult loginVkResult = this.result;
            return iHashCode3 + (loginVkResult != null ? loginVkResult.hashCode() : 0);
        }

        public final boolean isDeeplinkOpened() {
            return this.isDeeplinkOpened;
        }

        public final boolean isManualLogout() {
            return this.isManualLogout;
        }

        public final boolean isNeedStartRestore() {
            return this.isNeedStartRestore;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "LoginVk(email=" + this.email + ", from=" + this.from + ", serviceType=" + this.serviceType + ", isManualLogout=" + this.isManualLogout + ", isDeeplinkOpened=" + this.isDeeplinkOpened + ", isNeedStartRestore=" + this.isNeedStartRestore + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.from);
            dest.writeString(this.serviceType);
            dest.writeInt(this.isManualLogout ? 1 : 0);
            dest.writeInt(this.isDeeplinkOpened ? 1 : 0);
            dest.writeInt(this.isNeedStartRestore ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public LoginVk(@Nullable String str, @Nullable String str2, @Nullable String str3, boolean z10, boolean z11, boolean z12, boolean z13, @Nullable LoginVkResult loginVkResult) {
            super(null);
            this.email = str;
            this.from = str2;
            this.serviceType = str3;
            this.isManualLogout = z10;
            this.isDeeplinkOpened = z11;
            this.isNeedStartRestore = z12;
            this.isReplace = z13;
            this.result = loginVkResult;
            this.isSupportBack = true;
            this.id = "L";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public LoginVkResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable LoginVkResult loginVkResult) {
            this.result = loginVkResult;
        }

        public /* synthetic */ LoginVk(String str, String str2, String str3, boolean z10, boolean z11, boolean z12, boolean z13, LoginVkResult loginVkResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? false : z10, (i10 & 16) != 0 ? false : z11, (i10 & 32) != 0 ? false : z12, (i10 & 64) != 0 ? false : z13, (i10 & 128) != 0 ? null : loginVkResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$MrimDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/mrim/MrimDialogResult;", "email", "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/mrim/MrimDialogResult;)V", "getEmail", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/mrim/MrimDialogResult;", "setResult", "(Lru/mail/authorizationsdk/feature/mrim/MrimDialogResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class MrimDialog extends Screen<MrimDialogResult> {

        @NotNull
        private final String email;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private MrimDialogResult result;

        @NotNull
        public static final Parcelable.Creator<MrimDialog> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<MrimDialog> {
            @Override // android.os.Parcelable.Creator
            public final MrimDialog createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new MrimDialog(parcel.readString(), parcel.readInt() != 0, (MrimDialogResult) parcel.readParcelable(MrimDialog.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final MrimDialog[] newArray(int i10) {
                return new MrimDialog[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MrimDialog(@NotNull String email, boolean z10, @Nullable MrimDialogResult mrimDialogResult) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            this.email = email;
            this.isReplace = z10;
            this.result = mrimDialogResult;
            this.id = "m";
        }

        public static /* synthetic */ MrimDialog copy$default(MrimDialog mrimDialog, String str, boolean z10, MrimDialogResult mrimDialogResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = mrimDialog.email;
            }
            if ((i10 & 2) != 0) {
                z10 = mrimDialog.isReplace;
            }
            if ((i10 & 4) != 0) {
                mrimDialogResult = mrimDialog.result;
            }
            return mrimDialog.copy(str, z10, mrimDialogResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MrimDialogResult getResult() {
            return this.result;
        }

        @NotNull
        public final MrimDialog copy(@NotNull String email, boolean isReplace, @Nullable MrimDialogResult result) {
            Intrinsics.checkNotNullParameter(email, "email");
            return new MrimDialog(email, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MrimDialog)) {
                return false;
            }
            MrimDialog mrimDialog = (MrimDialog) other;
            return Intrinsics.areEqual(this.email, mrimDialog.email) && this.isReplace == mrimDialog.isReplace && Intrinsics.areEqual(this.result, mrimDialog.result);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.email.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            MrimDialogResult mrimDialogResult = this.result;
            return iHashCode + (mrimDialogResult == null ? 0 : mrimDialogResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "MrimDialog(email=" + this.email + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public MrimDialogResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable MrimDialogResult mrimDialogResult) {
            this.result = mrimDialogResult;
        }

        public /* synthetic */ MrimDialog(String str, boolean z10, MrimDialogResult mrimDialogResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : mrimDialogResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B=\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\rJ\t\u0010!\u001a\u00020\u0004HÆ\u0003J\t\u0010\"\u001a\u00020\u0004HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\t\u0010$\u001a\u00020\u0007HÆ\u0003J\t\u0010%\u001a\u00020\nHÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0002HÆ\u0003JG\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010(\u001a\u00020\u0004J\u0013\u0010)\u001a\u00020\n2\b\u0010*\u001a\u0004\u0018\u00010+HÖ\u0003J\t\u0010,\u001a\u00020\u0004HÖ\u0001J\t\u0010-\u001a\u00020\u001dHÖ\u0001J\u0016\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0014R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\nX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u0019\u0010\u0014R\u001a\u0010\u001c\u001a\u00020\u001dX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001b\u001a\u0004\b\u001f\u0010 ¨\u00063"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$NotReceivedCodeDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/phone/notreceivedcode/presentation/NotReceivedCodeResult;", "source", "", "receiveType", "delayFrom", "", "delay", "isReplace", "", "result", "<init>", "(IIJJZLru/mail/authorizationsdk/feature/phone/notreceivedcode/presentation/NotReceivedCodeResult;)V", "getSource", "()I", "getReceiveType", "getDelayFrom", "()J", "getDelay", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/phone/notreceivedcode/presentation/NotReceivedCodeResult;", "setResult", "(Lru/mail/authorizationsdk/feature/phone/notreceivedcode/presentation/NotReceivedCodeResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class NotReceivedCodeDialog extends Screen<NotReceivedCodeResult> {
        private final long delay;
        private final long delayFrom;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;
        private final int receiveType;

        @Nullable
        private NotReceivedCodeResult result;
        private final int source;

        @NotNull
        public static final Parcelable.Creator<NotReceivedCodeDialog> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<NotReceivedCodeDialog> {
            @Override // android.os.Parcelable.Creator
            public final NotReceivedCodeDialog createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new NotReceivedCodeDialog(parcel.readInt(), parcel.readInt(), parcel.readLong(), parcel.readLong(), parcel.readInt() != 0, (NotReceivedCodeResult) parcel.readParcelable(NotReceivedCodeDialog.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final NotReceivedCodeDialog[] newArray(int i10) {
                return new NotReceivedCodeDialog[i10];
            }
        }

        public NotReceivedCodeDialog(int i10, int i11, long j10, long j11, boolean z10, @Nullable NotReceivedCodeResult notReceivedCodeResult) {
            super(null);
            this.source = i10;
            this.receiveType = i11;
            this.delayFrom = j10;
            this.delay = j11;
            this.isReplace = z10;
            this.result = notReceivedCodeResult;
            this.isSupportBack = true;
            this.id = "NRCD";
        }

        public static /* synthetic */ NotReceivedCodeDialog copy$default(NotReceivedCodeDialog notReceivedCodeDialog, int i10, int i11, long j10, long j11, boolean z10, NotReceivedCodeResult notReceivedCodeResult, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                i10 = notReceivedCodeDialog.source;
            }
            if ((i12 & 2) != 0) {
                i11 = notReceivedCodeDialog.receiveType;
            }
            if ((i12 & 4) != 0) {
                j10 = notReceivedCodeDialog.delayFrom;
            }
            if ((i12 & 8) != 0) {
                j11 = notReceivedCodeDialog.delay;
            }
            if ((i12 & 16) != 0) {
                z10 = notReceivedCodeDialog.isReplace;
            }
            if ((i12 & 32) != 0) {
                notReceivedCodeResult = notReceivedCodeDialog.result;
            }
            long j12 = j11;
            long j13 = j10;
            return notReceivedCodeDialog.copy(i10, i11, j13, j12, z10, notReceivedCodeResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getReceiveType() {
            return this.receiveType;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final long getDelayFrom() {
            return this.delayFrom;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final long getDelay() {
            return this.delay;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final NotReceivedCodeResult getResult() {
            return this.result;
        }

        @NotNull
        public final NotReceivedCodeDialog copy(int source, int receiveType, long delayFrom, long delay, boolean isReplace, @Nullable NotReceivedCodeResult result) {
            return new NotReceivedCodeDialog(source, receiveType, delayFrom, delay, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NotReceivedCodeDialog)) {
                return false;
            }
            NotReceivedCodeDialog notReceivedCodeDialog = (NotReceivedCodeDialog) other;
            return this.source == notReceivedCodeDialog.source && this.receiveType == notReceivedCodeDialog.receiveType && this.delayFrom == notReceivedCodeDialog.delayFrom && this.delay == notReceivedCodeDialog.delay && this.isReplace == notReceivedCodeDialog.isReplace && Intrinsics.areEqual(this.result, notReceivedCodeDialog.result);
        }

        public final long getDelay() {
            return this.delay;
        }

        public final long getDelayFrom() {
            return this.delayFrom;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public final int getReceiveType() {
            return this.receiveType;
        }

        public final int getSource() {
            return this.source;
        }

        public int hashCode() {
            int iHashCode = ((((((((Integer.hashCode(this.source) * 31) + Integer.hashCode(this.receiveType)) * 31) + Long.hashCode(this.delayFrom)) * 31) + Long.hashCode(this.delay)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            NotReceivedCodeResult notReceivedCodeResult = this.result;
            return iHashCode + (notReceivedCodeResult == null ? 0 : notReceivedCodeResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "NotReceivedCodeDialog(source=" + this.source + ", receiveType=" + this.receiveType + ", delayFrom=" + this.delayFrom + ", delay=" + this.delay + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.source);
            dest.writeInt(this.receiveType);
            dest.writeLong(this.delayFrom);
            dest.writeLong(this.delay);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public NotReceivedCodeResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable NotReceivedCodeResult notReceivedCodeResult) {
            this.result = notReceivedCodeResult;
        }

        public /* synthetic */ NotReceivedCodeDialog(int i10, int i11, long j10, long j11, boolean z10, NotReceivedCodeResult notReceivedCodeResult, int i12, DefaultConstructorMarker defaultConstructorMarker) {
            this(i10, i11, j10, j11, (i12 & 16) != 0 ? false : z10, (i12 & 32) != 0 ? null : notReceivedCodeResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$OKAuth;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/ok/OKAuthResult;", VkCitySelectFragment.HINT_KEY, "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/ok/OKAuthResult;)V", "getHint", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/ok/OKAuthResult;", "setResult", "(Lru/mail/authorizationsdk/feature/ok/OKAuthResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OKAuth extends Screen<OKAuthResult> {

        @NotNull
        private final String hint;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private OKAuthResult result;

        @NotNull
        public static final Parcelable.Creator<OKAuth> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OKAuth> {
            @Override // android.os.Parcelable.Creator
            public final OKAuth createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new OKAuth(parcel.readString(), parcel.readInt() != 0, (OKAuthResult) parcel.readParcelable(OKAuth.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final OKAuth[] newArray(int i10) {
                return new OKAuth[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OKAuth(@NotNull String hint, boolean z10, @Nullable OKAuthResult oKAuthResult) {
            super(null);
            Intrinsics.checkNotNullParameter(hint, "hint");
            this.hint = hint;
            this.isReplace = z10;
            this.result = oKAuthResult;
            this.id = "k";
        }

        public static /* synthetic */ OKAuth copy$default(OKAuth oKAuth, String str, boolean z10, OKAuthResult oKAuthResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = oKAuth.hint;
            }
            if ((i10 & 2) != 0) {
                z10 = oKAuth.isReplace;
            }
            if ((i10 & 4) != 0) {
                oKAuthResult = oKAuth.result;
            }
            return oKAuth.copy(str, z10, oKAuthResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHint() {
            return this.hint;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final OKAuthResult getResult() {
            return this.result;
        }

        @NotNull
        public final OKAuth copy(@NotNull String hint, boolean isReplace, @Nullable OKAuthResult result) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            return new OKAuth(hint, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OKAuth)) {
                return false;
            }
            OKAuth oKAuth = (OKAuth) other;
            return Intrinsics.areEqual(this.hint, oKAuth.hint) && this.isReplace == oKAuth.isReplace && Intrinsics.areEqual(this.result, oKAuth.result);
        }

        @NotNull
        public final String getHint() {
            return this.hint;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.hint.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            OKAuthResult oKAuthResult = this.result;
            return iHashCode + (oKAuthResult == null ? 0 : oKAuthResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "OKAuth(hint=" + this.hint + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.hint);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public OKAuthResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable OKAuthResult oKAuthResult) {
            this.result = oKAuthResult;
        }

        public /* synthetic */ OKAuth(String str, boolean z10, OKAuthResult oKAuthResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : oKAuthResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$OneTimeCode;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;", "email", "", "from", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;)V", "getEmail", "()Ljava/lang/String;", "getFrom", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;", "setResult", "(Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OneTimeCode extends Screen<OneTimeCodeResult> {

        @NotNull
        private final String email;

        @NotNull
        private final String from;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private OneTimeCodeResult result;

        @NotNull
        public static final Parcelable.Creator<OneTimeCode> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OneTimeCode> {
            @Override // android.os.Parcelable.Creator
            public final OneTimeCode createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new OneTimeCode(parcel.readString(), parcel.readString(), parcel.readInt() != 0, (OneTimeCodeResult) parcel.readParcelable(OneTimeCode.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final OneTimeCode[] newArray(int i10) {
                return new OneTimeCode[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OneTimeCode(@NotNull String email, @NotNull String from, boolean z10, @Nullable OneTimeCodeResult oneTimeCodeResult) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(from, "from");
            this.email = email;
            this.from = from;
            this.isReplace = z10;
            this.result = oneTimeCodeResult;
            this.id = "o";
        }

        public static /* synthetic */ OneTimeCode copy$default(OneTimeCode oneTimeCode, String str, String str2, boolean z10, OneTimeCodeResult oneTimeCodeResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = oneTimeCode.email;
            }
            if ((i10 & 2) != 0) {
                str2 = oneTimeCode.from;
            }
            if ((i10 & 4) != 0) {
                z10 = oneTimeCode.isReplace;
            }
            if ((i10 & 8) != 0) {
                oneTimeCodeResult = oneTimeCode.result;
            }
            return oneTimeCode.copy(str, str2, z10, oneTimeCodeResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final OneTimeCodeResult getResult() {
            return this.result;
        }

        @NotNull
        public final OneTimeCode copy(@NotNull String email, @NotNull String from, boolean isReplace, @Nullable OneTimeCodeResult result) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(from, "from");
            return new OneTimeCode(email, from, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OneTimeCode)) {
                return false;
            }
            OneTimeCode oneTimeCode = (OneTimeCode) other;
            return Intrinsics.areEqual(this.email, oneTimeCode.email) && Intrinsics.areEqual(this.from, oneTimeCode.from) && this.isReplace == oneTimeCode.isReplace && Intrinsics.areEqual(this.result, oneTimeCode.result);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getFrom() {
            return this.from;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((((this.email.hashCode() * 31) + this.from.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            OneTimeCodeResult oneTimeCodeResult = this.result;
            return iHashCode + (oneTimeCodeResult == null ? 0 : oneTimeCodeResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "OneTimeCode(email=" + this.email + ", from=" + this.from + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.from);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public OneTimeCodeResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable OneTimeCodeResult oneTimeCodeResult) {
            this.result = oneTimeCodeResult;
        }

        public /* synthetic */ OneTimeCode(String str, String str2, boolean z10, OneTimeCodeResult oneTimeCodeResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? null : oneTimeCodeResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Outlook;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;", VkCitySelectFragment.HINT_KEY, "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;)V", "getHint", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;", "setResult", "(Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Outlook extends Screen<OutlookResult> {

        @NotNull
        private final String hint;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private OutlookResult result;

        @NotNull
        public static final Parcelable.Creator<Outlook> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Outlook> {
            @Override // android.os.Parcelable.Creator
            public final Outlook createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Outlook(parcel.readString(), parcel.readInt() != 0, (OutlookResult) parcel.readParcelable(Outlook.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Outlook[] newArray(int i10) {
                return new Outlook[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Outlook(@NotNull String hint, boolean z10, @Nullable OutlookResult outlookResult) {
            super(null);
            Intrinsics.checkNotNullParameter(hint, "hint");
            this.hint = hint;
            this.isReplace = z10;
            this.result = outlookResult;
            this.id = "O";
        }

        public static /* synthetic */ Outlook copy$default(Outlook outlook, String str, boolean z10, OutlookResult outlookResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = outlook.hint;
            }
            if ((i10 & 2) != 0) {
                z10 = outlook.isReplace;
            }
            if ((i10 & 4) != 0) {
                outlookResult = outlook.result;
            }
            return outlook.copy(str, z10, outlookResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHint() {
            return this.hint;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final OutlookResult getResult() {
            return this.result;
        }

        @NotNull
        public final Outlook copy(@NotNull String hint, boolean isReplace, @Nullable OutlookResult result) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            return new Outlook(hint, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Outlook)) {
                return false;
            }
            Outlook outlook = (Outlook) other;
            return Intrinsics.areEqual(this.hint, outlook.hint) && this.isReplace == outlook.isReplace && Intrinsics.areEqual(this.result, outlook.result);
        }

        @NotNull
        public final String getHint() {
            return this.hint;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.hint.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            OutlookResult outlookResult = this.result;
            return iHashCode + (outlookResult == null ? 0 : outlookResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "Outlook(hint=" + this.hint + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.hint);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public OutlookResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable OutlookResult outlookResult) {
            this.result = outlookResult;
        }

        public /* synthetic */ Outlook(String str, boolean z10, OutlookResult outlookResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : outlookResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B+\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J/\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0013\u0010\u001f\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u001eHÖ\u0001J\t\u0010#\u001a\u00020\u0005HÖ\u0001J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001eR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\rR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0012\u0010\rR\u001a\u0010\u0015\u001a\u00020\u0005X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0014\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$ParentSelection;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/registration/presentation/screen/parentselection/ParentSelectionResult;", "parents", "", "", "isReplace", "", "result", "<init>", "(Ljava/util/List;ZLru/mail/authorizationsdk/feature/registration/presentation/screen/parentselection/ParentSelectionResult;)V", "getParents", "()Ljava/util/List;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/registration/presentation/screen/parentselection/ParentSelectionResult;", "setResult", "(Lru/mail/authorizationsdk/feature/registration/presentation/screen/parentselection/ParentSelectionResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ParentSelection extends Screen<ParentSelectionResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final List<String> parents;

        @Nullable
        private ParentSelectionResult result;

        @NotNull
        public static final Parcelable.Creator<ParentSelection> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ParentSelection> {
            @Override // android.os.Parcelable.Creator
            public final ParentSelection createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new ParentSelection(parcel.createStringArrayList(), parcel.readInt() != 0, (ParentSelectionResult) parcel.readParcelable(ParentSelection.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final ParentSelection[] newArray(int i10) {
                return new ParentSelection[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParentSelection(@NotNull List<String> parents, boolean z10, @Nullable ParentSelectionResult parentSelectionResult) {
            super(null);
            Intrinsics.checkNotNullParameter(parents, "parents");
            this.parents = parents;
            this.isReplace = z10;
            this.result = parentSelectionResult;
            this.isSupportBack = true;
            this.id = "s";
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ParentSelection copy$default(ParentSelection parentSelection, List list, boolean z10, ParentSelectionResult parentSelectionResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = parentSelection.parents;
            }
            if ((i10 & 2) != 0) {
                z10 = parentSelection.isReplace;
            }
            if ((i10 & 4) != 0) {
                parentSelectionResult = parentSelection.result;
            }
            return parentSelection.copy(list, z10, parentSelectionResult);
        }

        @NotNull
        public final List<String> component1() {
            return this.parents;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final ParentSelectionResult getResult() {
            return this.result;
        }

        @NotNull
        public final ParentSelection copy(@NotNull List<String> parents, boolean isReplace, @Nullable ParentSelectionResult result) {
            Intrinsics.checkNotNullParameter(parents, "parents");
            return new ParentSelection(parents, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParentSelection)) {
                return false;
            }
            ParentSelection parentSelection = (ParentSelection) other;
            return Intrinsics.areEqual(this.parents, parentSelection.parents) && this.isReplace == parentSelection.isReplace && Intrinsics.areEqual(this.result, parentSelection.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final List<String> getParents() {
            return this.parents;
        }

        public int hashCode() {
            int iHashCode = ((this.parents.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            ParentSelectionResult parentSelectionResult = this.result;
            return iHashCode + (parentSelectionResult == null ? 0 : parentSelectionResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "ParentSelection(parents=" + this.parents + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeStringList(this.parents);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public ParentSelectionResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable ParentSelectionResult parentSelectionResult) {
            this.result = parentSelectionResult;
        }

        public /* synthetic */ ParentSelection(List list, boolean z10, ParentSelectionResult parentSelectionResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : parentSelectionResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\t\u0010 \u001a\u00020\u0004HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003JQ\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010&\u001a\u00020'J\u0013\u0010(\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020'HÖ\u0001J\t\u0010,\u001a\u00020\u0004HÖ\u0001J\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020'R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0014\u0010\n\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\bX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u001b\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001a\u001a\u0004\b\u001d\u0010\u000f¨\u00062"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Password;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/password/presentation/PasswordResult;", "email", "", "serviceType", "password", "isSupportRestore", "", "startAuthImmediate", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZLru/mail/authorizationsdk/feature/password/presentation/PasswordResult;)V", "getEmail", "()Ljava/lang/String;", "getServiceType", "getPassword", "()Z", "getStartAuthImmediate", "getResult", "()Lru/mail/authorizationsdk/feature/password/presentation/PasswordResult;", "setResult", "(Lru/mail/authorizationsdk/feature/password/presentation/PasswordResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Password extends Screen<PasswordResult> {

        @NotNull
        private final String email;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;
        private final boolean isSupportRestore;

        @NotNull
        private final String password;

        @Nullable
        private PasswordResult result;

        @NotNull
        private final String serviceType;
        private final boolean startAuthImmediate;

        @NotNull
        public static final Parcelable.Creator<Password> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Password> {
            @Override // android.os.Parcelable.Creator
            public final Password createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new Password(string, string2, string3, z10, z11, parcel.readInt() != 0, (PasswordResult) parcel.readParcelable(Password.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Password[] newArray(int i10) {
                return new Password[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Password(@NotNull String email, @NotNull String serviceType, @NotNull String password, boolean z10, boolean z11, boolean z12, @Nullable PasswordResult passwordResult) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(serviceType, "serviceType");
            Intrinsics.checkNotNullParameter(password, "password");
            this.email = email;
            this.serviceType = serviceType;
            this.password = password;
            this.isSupportRestore = z10;
            this.startAuthImmediate = z11;
            this.isReplace = z12;
            this.result = passwordResult;
            this.isSupportBack = true;
            this.id = "P";
        }

        public static /* synthetic */ Password copy$default(Password password, String str, String str2, String str3, boolean z10, boolean z11, boolean z12, PasswordResult passwordResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = password.email;
            }
            if ((i10 & 2) != 0) {
                str2 = password.serviceType;
            }
            if ((i10 & 4) != 0) {
                str3 = password.password;
            }
            if ((i10 & 8) != 0) {
                z10 = password.isSupportRestore;
            }
            if ((i10 & 16) != 0) {
                z11 = password.startAuthImmediate;
            }
            if ((i10 & 32) != 0) {
                z12 = password.isReplace;
            }
            if ((i10 & 64) != 0) {
                passwordResult = password.result;
            }
            boolean z13 = z12;
            PasswordResult passwordResult2 = passwordResult;
            boolean z14 = z11;
            String str4 = str3;
            return password.copy(str, str2, str4, z10, z14, z13, passwordResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getServiceType() {
            return this.serviceType;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getPassword() {
            return this.password;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsSupportRestore() {
            return this.isSupportRestore;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getStartAuthImmediate() {
            return this.startAuthImmediate;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final PasswordResult getResult() {
            return this.result;
        }

        @NotNull
        public final Password copy(@NotNull String email, @NotNull String serviceType, @NotNull String password, boolean isSupportRestore, boolean startAuthImmediate, boolean isReplace, @Nullable PasswordResult result) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(serviceType, "serviceType");
            Intrinsics.checkNotNullParameter(password, "password");
            return new Password(email, serviceType, password, isSupportRestore, startAuthImmediate, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Password)) {
                return false;
            }
            Password password = (Password) other;
            return Intrinsics.areEqual(this.email, password.email) && Intrinsics.areEqual(this.serviceType, password.serviceType) && Intrinsics.areEqual(this.password, password.password) && this.isSupportRestore == password.isSupportRestore && this.startAuthImmediate == password.startAuthImmediate && this.isReplace == password.isReplace && Intrinsics.areEqual(this.result, password.result);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        public final String getServiceType() {
            return this.serviceType;
        }

        public final boolean getStartAuthImmediate() {
            return this.startAuthImmediate;
        }

        public int hashCode() {
            int iHashCode = ((((((((((this.email.hashCode() * 31) + this.serviceType.hashCode()) * 31) + this.password.hashCode()) * 31) + Boolean.hashCode(this.isSupportRestore)) * 31) + Boolean.hashCode(this.startAuthImmediate)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            PasswordResult passwordResult = this.result;
            return iHashCode + (passwordResult == null ? 0 : passwordResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        public final boolean isSupportRestore() {
            return this.isSupportRestore;
        }

        @NotNull
        public String toString() {
            return "Password(email=" + this.email + ", serviceType=" + this.serviceType + ", password=" + this.password + ", isSupportRestore=" + this.isSupportRestore + ", startAuthImmediate=" + this.startAuthImmediate + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.serviceType);
            dest.writeString(this.password);
            dest.writeInt(this.isSupportRestore ? 1 : 0);
            dest.writeInt(this.startAuthImmediate ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public PasswordResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable PasswordResult passwordResult) {
            this.result = passwordResult;
        }

        public /* synthetic */ Password(String str, String str2, String str3, boolean z10, boolean z11, boolean z12, PasswordResult passwordResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, z10, (i10 & 16) != 0 ? false : z11, (i10 & 32) != 0 ? false : z12, (i10 & 64) != 0 ? null : passwordResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b'\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010%\u001a\u00020\u0004HÆ\u0003J\t\u0010&\u001a\u00020\u0004HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010)\u001a\u00020\u0004HÆ\u0003J\u000b\u0010*\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010-\u001a\u00020\u0004HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0002HÆ\u0003Jy\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000e\u001a\u00020\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u00100\u001a\u000201J\u0013\u00102\u001a\u00020\u00042\b\u00103\u001a\u0004\u0018\u000104HÖ\u0003J\t\u00105\u001a\u000201HÖ\u0001J\t\u00106\u001a\u00020\tHÖ\u0001J\u0016\u00107\u001a\u0002082\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u000201R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0014\u0010\u000e\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0012R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b \u0010!\u001a\u0004\b\u001f\u0010\u0012R\u001a\u0010\"\u001a\u00020\tX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b#\u0010!\u001a\u0004\b$\u0010\u0016¨\u0006<"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RegistrationMain;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;", "isSocialRegPlateEnabled", "", "isSocialDoreg", "knownFields", "Lru/mail/authorizationsdk/feature/registration/domain/model/KnownFieldsValues;", "signupToken", "", "afterSocialLogin", "socialBindType", "openedFrom", "vkAccessToken", "isReplace", "result", "<init>", "(ZZLru/mail/authorizationsdk/feature/registration/domain/model/KnownFieldsValues;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;)V", "()Z", "getKnownFields", "()Lru/mail/authorizationsdk/feature/registration/domain/model/KnownFieldsValues;", "getSignupToken", "()Ljava/lang/String;", "getAfterSocialLogin", "getSocialBindType", "getOpenedFrom", "getVkAccessToken", "getResult", "()Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;", "setResult", "(Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RegistrationMain extends Screen<RegistrationMainResult> {
        private final boolean afterSocialLogin;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSocialDoreg;
        private final boolean isSocialRegPlateEnabled;
        private final boolean isSupportBack;

        @Nullable
        private final KnownFieldsValues knownFields;

        @Nullable
        private final String openedFrom;

        @Nullable
        private RegistrationMainResult result;

        @Nullable
        private final String signupToken;

        @Nullable
        private final String socialBindType;

        @Nullable
        private final String vkAccessToken;

        @NotNull
        public static final Parcelable.Creator<RegistrationMain> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<RegistrationMain> {
            @Override // android.os.Parcelable.Creator
            public final RegistrationMain createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                KnownFieldsValues knownFieldsValuesCreateFromParcel = parcel.readInt() == 0 ? null : KnownFieldsValues.CREATOR.createFromParcel(parcel);
                boolean z12 = true;
                String string = parcel.readString();
                if (parcel.readInt() == 0) {
                    z12 = z10;
                }
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z13 = true;
                String string4 = parcel.readString();
                if (parcel.readInt() == 0) {
                    z13 = false;
                }
                return new RegistrationMain(z10, z11, knownFieldsValuesCreateFromParcel, string, z12, string2, string3, string4, z13, (RegistrationMainResult) parcel.readParcelable(RegistrationMain.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final RegistrationMain[] newArray(int i10) {
                return new RegistrationMain[i10];
            }
        }

        public RegistrationMain(boolean z10, boolean z11, @Nullable KnownFieldsValues knownFieldsValues, @Nullable String str, boolean z12, @Nullable String str2, @Nullable String str3, @Nullable String str4, boolean z13, @Nullable RegistrationMainResult registrationMainResult) {
            super(null);
            this.isSocialRegPlateEnabled = z10;
            this.isSocialDoreg = z11;
            this.knownFields = knownFieldsValues;
            this.signupToken = str;
            this.afterSocialLogin = z12;
            this.socialBindType = str2;
            this.openedFrom = str3;
            this.vkAccessToken = str4;
            this.isReplace = z13;
            this.result = registrationMainResult;
            this.isSupportBack = true;
            this.id = "R";
        }

        public static /* synthetic */ RegistrationMain copy$default(RegistrationMain registrationMain, boolean z10, boolean z11, KnownFieldsValues knownFieldsValues, String str, boolean z12, String str2, String str3, String str4, boolean z13, RegistrationMainResult registrationMainResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = registrationMain.isSocialRegPlateEnabled;
            }
            if ((i10 & 2) != 0) {
                z11 = registrationMain.isSocialDoreg;
            }
            if ((i10 & 4) != 0) {
                knownFieldsValues = registrationMain.knownFields;
            }
            if ((i10 & 8) != 0) {
                str = registrationMain.signupToken;
            }
            if ((i10 & 16) != 0) {
                z12 = registrationMain.afterSocialLogin;
            }
            if ((i10 & 32) != 0) {
                str2 = registrationMain.socialBindType;
            }
            if ((i10 & 64) != 0) {
                str3 = registrationMain.openedFrom;
            }
            if ((i10 & 128) != 0) {
                str4 = registrationMain.vkAccessToken;
            }
            if ((i10 & 256) != 0) {
                z13 = registrationMain.isReplace;
            }
            if ((i10 & 512) != 0) {
                registrationMainResult = registrationMain.result;
            }
            boolean z14 = z13;
            RegistrationMainResult registrationMainResult2 = registrationMainResult;
            String str5 = str3;
            String str6 = str4;
            boolean z15 = z12;
            String str7 = str2;
            return registrationMain.copy(z10, z11, knownFieldsValues, str, z15, str7, str5, str6, z14, registrationMainResult2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsSocialRegPlateEnabled() {
            return this.isSocialRegPlateEnabled;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final RegistrationMainResult getResult() {
            return this.result;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsSocialDoreg() {
            return this.isSocialDoreg;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final KnownFieldsValues getKnownFields() {
            return this.knownFields;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSignupToken() {
            return this.signupToken;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getAfterSocialLogin() {
            return this.afterSocialLogin;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getOpenedFrom() {
            return this.openedFrom;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @NotNull
        public final RegistrationMain copy(boolean isSocialRegPlateEnabled, boolean isSocialDoreg, @Nullable KnownFieldsValues knownFields, @Nullable String signupToken, boolean afterSocialLogin, @Nullable String socialBindType, @Nullable String openedFrom, @Nullable String vkAccessToken, boolean isReplace, @Nullable RegistrationMainResult result) {
            return new RegistrationMain(isSocialRegPlateEnabled, isSocialDoreg, knownFields, signupToken, afterSocialLogin, socialBindType, openedFrom, vkAccessToken, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RegistrationMain)) {
                return false;
            }
            RegistrationMain registrationMain = (RegistrationMain) other;
            return this.isSocialRegPlateEnabled == registrationMain.isSocialRegPlateEnabled && this.isSocialDoreg == registrationMain.isSocialDoreg && Intrinsics.areEqual(this.knownFields, registrationMain.knownFields) && Intrinsics.areEqual(this.signupToken, registrationMain.signupToken) && this.afterSocialLogin == registrationMain.afterSocialLogin && Intrinsics.areEqual(this.socialBindType, registrationMain.socialBindType) && Intrinsics.areEqual(this.openedFrom, registrationMain.openedFrom) && Intrinsics.areEqual(this.vkAccessToken, registrationMain.vkAccessToken) && this.isReplace == registrationMain.isReplace && Intrinsics.areEqual(this.result, registrationMain.result);
        }

        public final boolean getAfterSocialLogin() {
            return this.afterSocialLogin;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @Nullable
        public final KnownFieldsValues getKnownFields() {
            return this.knownFields;
        }

        @Nullable
        public final String getOpenedFrom() {
            return this.openedFrom;
        }

        @Nullable
        public final String getSignupToken() {
            return this.signupToken;
        }

        @Nullable
        public final String getSocialBindType() {
            return this.socialBindType;
        }

        @Nullable
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        public int hashCode() {
            int iHashCode = ((Boolean.hashCode(this.isSocialRegPlateEnabled) * 31) + Boolean.hashCode(this.isSocialDoreg)) * 31;
            KnownFieldsValues knownFieldsValues = this.knownFields;
            int iHashCode2 = (iHashCode + (knownFieldsValues == null ? 0 : knownFieldsValues.hashCode())) * 31;
            String str = this.signupToken;
            int iHashCode3 = (((iHashCode2 + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.afterSocialLogin)) * 31;
            String str2 = this.socialBindType;
            int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.openedFrom;
            int iHashCode5 = (iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.vkAccessToken;
            int iHashCode6 = (((iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            RegistrationMainResult registrationMainResult = this.result;
            return iHashCode6 + (registrationMainResult != null ? registrationMainResult.hashCode() : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        public final boolean isSocialDoreg() {
            return this.isSocialDoreg;
        }

        public final boolean isSocialRegPlateEnabled() {
            return this.isSocialRegPlateEnabled;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "RegistrationMain(isSocialRegPlateEnabled=" + this.isSocialRegPlateEnabled + ", isSocialDoreg=" + this.isSocialDoreg + ", knownFields=" + this.knownFields + ", signupToken=" + this.signupToken + ", afterSocialLogin=" + this.afterSocialLogin + ", socialBindType=" + this.socialBindType + ", openedFrom=" + this.openedFrom + ", vkAccessToken=" + this.vkAccessToken + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isSocialRegPlateEnabled ? 1 : 0);
            dest.writeInt(this.isSocialDoreg ? 1 : 0);
            KnownFieldsValues knownFieldsValues = this.knownFields;
            if (knownFieldsValues == null) {
                dest.writeInt(0);
            } else {
                dest.writeInt(1);
                knownFieldsValues.writeToParcel(dest, flags);
            }
            dest.writeString(this.signupToken);
            dest.writeInt(this.afterSocialLogin ? 1 : 0);
            dest.writeString(this.socialBindType);
            dest.writeString(this.openedFrom);
            dest.writeString(this.vkAccessToken);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public RegistrationMainResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable RegistrationMainResult registrationMainResult) {
            this.result = registrationMainResult;
        }

        public /* synthetic */ RegistrationMain(boolean z10, boolean z11, KnownFieldsValues knownFieldsValues, String str, boolean z12, String str2, String str3, String str4, boolean z13, RegistrationMainResult registrationMainResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? false : z11, (i10 & 4) != 0 ? null : knownFieldsValues, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? false : z12, (i10 & 32) != 0 ? null : str2, (i10 & 64) != 0 ? null : str3, str4, (i10 & 256) != 0 ? false : z13, (i10 & 512) != 0 ? null : registrationMainResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003J=\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001f\u001a\u00020 J\u0013\u0010!\u001a\u00020\u00062\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020 HÖ\u0001J\t\u0010%\u001a\u00020\u0004HÖ\u0001J\u0016\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020 R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u000eR\u0014\u0010\b\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\r¨\u0006+"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RestorePassword;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;", "email", "", "isRestoreVkidEnabled", "", "isRebind", "isReplace", "result", "<init>", "(Ljava/lang/String;ZZZLru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;)V", "getEmail", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;", "setResult", "(Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RestorePassword extends Screen<RestorePasswordResult> {

        @NotNull
        private final String email;

        @NotNull
        private final String id;
        private final boolean isRebind;
        private final boolean isReplace;
        private final boolean isRestoreVkidEnabled;
        private final boolean isSupportBack;

        @Nullable
        private RestorePasswordResult result;

        @NotNull
        public static final Parcelable.Creator<RestorePassword> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<RestorePassword> {
            @Override // android.os.Parcelable.Creator
            public final RestorePassword createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                boolean z10 = false;
                boolean z11 = true;
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                if (parcel.readInt() == 0) {
                    z11 = z10;
                }
                return new RestorePassword(string, z10, z11, parcel.readInt() != 0, (RestorePasswordResult) parcel.readParcelable(RestorePassword.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final RestorePassword[] newArray(int i10) {
                return new RestorePassword[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestorePassword(@NotNull String email, boolean z10, boolean z11, boolean z12, @Nullable RestorePasswordResult restorePasswordResult) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            this.email = email;
            this.isRestoreVkidEnabled = z10;
            this.isRebind = z11;
            this.isReplace = z12;
            this.result = restorePasswordResult;
            this.id = "r";
        }

        public static /* synthetic */ RestorePassword copy$default(RestorePassword restorePassword, String str, boolean z10, boolean z11, boolean z12, RestorePasswordResult restorePasswordResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = restorePassword.email;
            }
            if ((i10 & 2) != 0) {
                z10 = restorePassword.isRestoreVkidEnabled;
            }
            if ((i10 & 4) != 0) {
                z11 = restorePassword.isRebind;
            }
            if ((i10 & 8) != 0) {
                z12 = restorePassword.isReplace;
            }
            if ((i10 & 16) != 0) {
                restorePasswordResult = restorePassword.result;
            }
            RestorePasswordResult restorePasswordResult2 = restorePasswordResult;
            boolean z13 = z11;
            return restorePassword.copy(str, z10, z13, z12, restorePasswordResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsRestoreVkidEnabled() {
            return this.isRestoreVkidEnabled;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsRebind() {
            return this.isRebind;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final RestorePasswordResult getResult() {
            return this.result;
        }

        @NotNull
        public final RestorePassword copy(@NotNull String email, boolean isRestoreVkidEnabled, boolean isRebind, boolean isReplace, @Nullable RestorePasswordResult result) {
            Intrinsics.checkNotNullParameter(email, "email");
            return new RestorePassword(email, isRestoreVkidEnabled, isRebind, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RestorePassword)) {
                return false;
            }
            RestorePassword restorePassword = (RestorePassword) other;
            return Intrinsics.areEqual(this.email, restorePassword.email) && this.isRestoreVkidEnabled == restorePassword.isRestoreVkidEnabled && this.isRebind == restorePassword.isRebind && this.isReplace == restorePassword.isReplace && Intrinsics.areEqual(this.result, restorePassword.result);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((((((this.email.hashCode() * 31) + Boolean.hashCode(this.isRestoreVkidEnabled)) * 31) + Boolean.hashCode(this.isRebind)) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            RestorePasswordResult restorePasswordResult = this.result;
            return iHashCode + (restorePasswordResult == null ? 0 : restorePasswordResult.hashCode());
        }

        public final boolean isRebind() {
            return this.isRebind;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        public final boolean isRestoreVkidEnabled() {
            return this.isRestoreVkidEnabled;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "RestorePassword(email=" + this.email + ", isRestoreVkidEnabled=" + this.isRestoreVkidEnabled + ", isRebind=" + this.isRebind + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeInt(this.isRestoreVkidEnabled ? 1 : 0);
            dest.writeInt(this.isRebind ? 1 : 0);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public RestorePasswordResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable RestorePasswordResult restorePasswordResult) {
            this.result = restorePasswordResult;
        }

        public /* synthetic */ RestorePassword(String str, boolean z10, boolean z11, boolean z12, RestorePasswordResult restorePasswordResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? false : z11, (i10 & 8) != 0 ? false : z12, (i10 & 16) != 0 ? null : restorePasswordResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B/\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J5\u0010\u001d\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$RestoreVkId;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;", "email", "", "source", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;)V", "getEmail", "()Ljava/lang/String;", "getSource", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;", "setResult", "(Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class RestoreVkId extends Screen<RestoreVkResult> {

        @Nullable
        private final String email;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private RestoreVkResult result;

        @NotNull
        private final String source;

        @NotNull
        public static final Parcelable.Creator<RestoreVkId> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<RestoreVkId> {
            @Override // android.os.Parcelable.Creator
            public final RestoreVkId createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new RestoreVkId(parcel.readString(), parcel.readString(), parcel.readInt() != 0, (RestoreVkResult) parcel.readParcelable(RestoreVkId.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final RestoreVkId[] newArray(int i10) {
                return new RestoreVkId[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestoreVkId(@Nullable String str, @NotNull String source, boolean z10, @Nullable RestoreVkResult restoreVkResult) {
            super(null);
            Intrinsics.checkNotNullParameter(source, "source");
            this.email = str;
            this.source = source;
            this.isReplace = z10;
            this.result = restoreVkResult;
            this.id = "rv";
        }

        public static /* synthetic */ RestoreVkId copy$default(RestoreVkId restoreVkId, String str, String str2, boolean z10, RestoreVkResult restoreVkResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = restoreVkId.email;
            }
            if ((i10 & 2) != 0) {
                str2 = restoreVkId.source;
            }
            if ((i10 & 4) != 0) {
                z10 = restoreVkId.isReplace;
            }
            if ((i10 & 8) != 0) {
                restoreVkResult = restoreVkId.result;
            }
            return restoreVkId.copy(str, str2, z10, restoreVkResult);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSource() {
            return this.source;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final RestoreVkResult getResult() {
            return this.result;
        }

        @NotNull
        public final RestoreVkId copy(@Nullable String email, @NotNull String source, boolean isReplace, @Nullable RestoreVkResult result) {
            Intrinsics.checkNotNullParameter(source, "source");
            return new RestoreVkId(email, source, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof RestoreVkId)) {
                return false;
            }
            RestoreVkId restoreVkId = (RestoreVkId) other;
            return Intrinsics.areEqual(this.email, restoreVkId.email) && Intrinsics.areEqual(this.source, restoreVkId.source) && this.isReplace == restoreVkId.isReplace && Intrinsics.areEqual(this.result, restoreVkId.result);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getSource() {
            return this.source;
        }

        public int hashCode() {
            String str = this.email;
            int iHashCode = (((((str == null ? 0 : str.hashCode()) * 31) + this.source.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            RestoreVkResult restoreVkResult = this.result;
            return iHashCode + (restoreVkResult != null ? restoreVkResult.hashCode() : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "RestoreVkId(email=" + this.email + ", source=" + this.source + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.source);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public RestoreVkResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable RestoreVkResult restoreVkResult) {
            this.result = restoreVkResult;
        }

        public /* synthetic */ RestoreVkId(String str, String str2, boolean z10, RestoreVkResult restoreVkResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? null : restoreVkResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SSO;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;", "login", "", "url", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/sso/presentation/SSOResult;)V", "getLogin", "()Ljava/lang/String;", "getUrl", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;", "setResult", "(Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SSO extends Screen<SSOResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String login;

        @Nullable
        private SSOResult result;

        @NotNull
        private final String url;

        @NotNull
        public static final Parcelable.Creator<SSO> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<SSO> {
            @Override // android.os.Parcelable.Creator
            public final SSO createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new SSO(parcel.readString(), parcel.readString(), parcel.readInt() != 0, (SSOResult) parcel.readParcelable(SSO.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final SSO[] newArray(int i10) {
                return new SSO[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SSO(@NotNull String login, @NotNull String url, boolean z10, @Nullable SSOResult sSOResult) {
            super(null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(url, "url");
            this.login = login;
            this.url = url;
            this.isReplace = z10;
            this.result = sSOResult;
            this.id = "S";
        }

        public static /* synthetic */ SSO copy$default(SSO sso, String str, String str2, boolean z10, SSOResult sSOResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = sso.login;
            }
            if ((i10 & 2) != 0) {
                str2 = sso.url;
            }
            if ((i10 & 4) != 0) {
                z10 = sso.isReplace;
            }
            if ((i10 & 8) != 0) {
                sSOResult = sso.result;
            }
            return sso.copy(str, str2, z10, sSOResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final SSOResult getResult() {
            return this.result;
        }

        @NotNull
        public final SSO copy(@NotNull String login, @NotNull String url, boolean isReplace, @Nullable SSOResult result) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(url, "url");
            return new SSO(login, url, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SSO)) {
                return false;
            }
            SSO sso = (SSO) other;
            return Intrinsics.areEqual(this.login, sso.login) && Intrinsics.areEqual(this.url, sso.url) && this.isReplace == sso.isReplace && Intrinsics.areEqual(this.result, sso.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            int iHashCode = ((((this.login.hashCode() * 31) + this.url.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            SSOResult sSOResult = this.result;
            return iHashCode + (sSOResult == null ? 0 : sSOResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "SSO(login=" + this.login + ", url=" + this.url + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.login);
            dest.writeString(this.url);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public SSOResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable SSOResult sSOResult) {
            this.result = sSOResult;
        }

        public /* synthetic */ SSO(String str, String str2, boolean z10, SSOResult sSOResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? null : sSOResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BI\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\f\u0010\rJ\t\u0010\u001e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0004HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010!\u001a\u00020\bHÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\u0002HÆ\u0003JU\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\n\u001a\u00020\b2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010&\u001a\u00020'J\u0013\u0010(\u001a\u00020\b2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020'HÖ\u0001J\t\u0010,\u001a\u00020\u0004HÖ\u0001J\u0016\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020'R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0014\u0010\n\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\bX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0018\u0010\u0012R\u001a\u0010\u001b\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u001c\u0010\u001a\u001a\u0004\b\u001d\u0010\u000f¨\u00062"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SecondFactor;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;", "login", "", "url", "secondStepCookieHeader", "isXmailMigration", "", "paramXmailFrom", "isReplace", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;ZLru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;)V", "getLogin", "()Ljava/lang/String;", "getUrl", "getSecondStepCookieHeader", "()Z", "getParamXmailFrom", "getResult", "()Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;", "setResult", "(Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SecondFactor extends Screen<SecondStepResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;
        private final boolean isXmailMigration;

        @NotNull
        private final String login;

        @Nullable
        private final String paramXmailFrom;

        @Nullable
        private SecondStepResult result;

        @Nullable
        private final String secondStepCookieHeader;

        @NotNull
        private final String url;

        @NotNull
        public static final Parcelable.Creator<SecondFactor> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<SecondFactor> {
            @Override // android.os.Parcelable.Creator
            public final SecondFactor createFromParcel(Parcel parcel) {
                boolean z10;
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                String string = parcel.readString();
                String string2 = parcel.readString();
                String string3 = parcel.readString();
                boolean z11 = false;
                if (parcel.readInt() != 0) {
                    z11 = true;
                    z10 = true;
                } else {
                    z10 = true;
                }
                String string4 = parcel.readString();
                if (parcel.readInt() == 0) {
                    z10 = false;
                }
                return new SecondFactor(string, string2, string3, z11, string4, z10, (SecondStepResult) parcel.readParcelable(SecondFactor.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final SecondFactor[] newArray(int i10) {
                return new SecondFactor[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SecondFactor(@NotNull String login, @NotNull String url, @Nullable String str, boolean z10, @Nullable String str2, boolean z11, @Nullable SecondStepResult secondStepResult) {
            super(null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(url, "url");
            this.login = login;
            this.url = url;
            this.secondStepCookieHeader = str;
            this.isXmailMigration = z10;
            this.paramXmailFrom = str2;
            this.isReplace = z11;
            this.result = secondStepResult;
            this.id = File.TYPE_FILE;
        }

        public static /* synthetic */ SecondFactor copy$default(SecondFactor secondFactor, String str, String str2, String str3, boolean z10, String str4, boolean z11, SecondStepResult secondStepResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = secondFactor.login;
            }
            if ((i10 & 2) != 0) {
                str2 = secondFactor.url;
            }
            if ((i10 & 4) != 0) {
                str3 = secondFactor.secondStepCookieHeader;
            }
            if ((i10 & 8) != 0) {
                z10 = secondFactor.isXmailMigration;
            }
            if ((i10 & 16) != 0) {
                str4 = secondFactor.paramXmailFrom;
            }
            if ((i10 & 32) != 0) {
                z11 = secondFactor.isReplace;
            }
            if ((i10 & 64) != 0) {
                secondStepResult = secondFactor.result;
            }
            boolean z12 = z11;
            SecondStepResult secondStepResult2 = secondStepResult;
            String str5 = str4;
            String str6 = str3;
            return secondFactor.copy(str, str2, str6, z10, str5, z12, secondStepResult2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getSecondStepCookieHeader() {
            return this.secondStepCookieHeader;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsXmailMigration() {
            return this.isXmailMigration;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getParamXmailFrom() {
            return this.paramXmailFrom;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final SecondStepResult getResult() {
            return this.result;
        }

        @NotNull
        public final SecondFactor copy(@NotNull String login, @NotNull String url, @Nullable String secondStepCookieHeader, boolean isXmailMigration, @Nullable String paramXmailFrom, boolean isReplace, @Nullable SecondStepResult result) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(url, "url");
            return new SecondFactor(login, url, secondStepCookieHeader, isXmailMigration, paramXmailFrom, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SecondFactor)) {
                return false;
            }
            SecondFactor secondFactor = (SecondFactor) other;
            return Intrinsics.areEqual(this.login, secondFactor.login) && Intrinsics.areEqual(this.url, secondFactor.url) && Intrinsics.areEqual(this.secondStepCookieHeader, secondFactor.secondStepCookieHeader) && this.isXmailMigration == secondFactor.isXmailMigration && Intrinsics.areEqual(this.paramXmailFrom, secondFactor.paramXmailFrom) && this.isReplace == secondFactor.isReplace && Intrinsics.areEqual(this.result, secondFactor.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        @Nullable
        public final String getParamXmailFrom() {
            return this.paramXmailFrom;
        }

        @Nullable
        public final String getSecondStepCookieHeader() {
            return this.secondStepCookieHeader;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            int iHashCode = ((this.login.hashCode() * 31) + this.url.hashCode()) * 31;
            String str = this.secondStepCookieHeader;
            int iHashCode2 = (((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Boolean.hashCode(this.isXmailMigration)) * 31;
            String str2 = this.paramXmailFrom;
            int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            SecondStepResult secondStepResult = this.result;
            return iHashCode3 + (secondStepResult != null ? secondStepResult.hashCode() : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        public final boolean isXmailMigration() {
            return this.isXmailMigration;
        }

        @NotNull
        public String toString() {
            return "SecondFactor(login=" + this.login + ", url=" + this.url + ", secondStepCookieHeader=" + this.secondStepCookieHeader + ", isXmailMigration=" + this.isXmailMigration + ", paramXmailFrom=" + this.paramXmailFrom + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.login);
            dest.writeString(this.url);
            dest.writeString(this.secondStepCookieHeader);
            dest.writeInt(this.isXmailMigration ? 1 : 0);
            dest.writeString(this.paramXmailFrom);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public SecondStepResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable SecondStepResult secondStepResult) {
            this.result = secondStepResult;
        }

        public /* synthetic */ SecondFactor(String str, String str2, String str3, boolean z10, String str4, boolean z11, SecondStepResult secondStepResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, z10, str4, (i10 & 32) != 0 ? false : z11, (i10 & 64) != 0 ? null : secondStepResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u001eHÖ\u0001J\t\u0010#\u001a\u00020\u0015HÖ\u0001J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$SocialAuth;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;", "initMode", "Lru/mail/authorizationsdk/feature/socialauth/domain/SocialAuthInitMode;", "isReplace", "", "result", "<init>", "(Lru/mail/authorizationsdk/feature/socialauth/domain/SocialAuthInitMode;ZLru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;)V", "getInitMode", "()Lru/mail/authorizationsdk/feature/socialauth/domain/SocialAuthInitMode;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;", "setResult", "(Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SocialAuth extends Screen<SocialAuthResult> {

        @NotNull
        private final String id;

        @NotNull
        private final SocialAuthInitMode initMode;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private SocialAuthResult result;

        @NotNull
        public static final Parcelable.Creator<SocialAuth> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<SocialAuth> {
            @Override // android.os.Parcelable.Creator
            public final SocialAuth createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new SocialAuth((SocialAuthInitMode) parcel.readParcelable(SocialAuth.class.getClassLoader()), parcel.readInt() != 0, (SocialAuthResult) parcel.readParcelable(SocialAuth.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final SocialAuth[] newArray(int i10) {
                return new SocialAuth[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SocialAuth(@NotNull SocialAuthInitMode initMode, boolean z10, @Nullable SocialAuthResult socialAuthResult) {
            super(null);
            Intrinsics.checkNotNullParameter(initMode, "initMode");
            this.initMode = initMode;
            this.isReplace = z10;
            this.result = socialAuthResult;
            this.id = initMode.getId();
        }

        public static /* synthetic */ SocialAuth copy$default(SocialAuth socialAuth, SocialAuthInitMode socialAuthInitMode, boolean z10, SocialAuthResult socialAuthResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                socialAuthInitMode = socialAuth.initMode;
            }
            if ((i10 & 2) != 0) {
                z10 = socialAuth.isReplace;
            }
            if ((i10 & 4) != 0) {
                socialAuthResult = socialAuth.result;
            }
            return socialAuth.copy(socialAuthInitMode, z10, socialAuthResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final SocialAuthInitMode getInitMode() {
            return this.initMode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final SocialAuthResult getResult() {
            return this.result;
        }

        @NotNull
        public final SocialAuth copy(@NotNull SocialAuthInitMode initMode, boolean isReplace, @Nullable SocialAuthResult result) {
            Intrinsics.checkNotNullParameter(initMode, "initMode");
            return new SocialAuth(initMode, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SocialAuth)) {
                return false;
            }
            SocialAuth socialAuth = (SocialAuth) other;
            return Intrinsics.areEqual(this.initMode, socialAuth.initMode) && this.isReplace == socialAuth.isReplace && Intrinsics.areEqual(this.result, socialAuth.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final SocialAuthInitMode getInitMode() {
            return this.initMode;
        }

        public int hashCode() {
            int iHashCode = ((this.initMode.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            SocialAuthResult socialAuthResult = this.result;
            return iHashCode + (socialAuthResult == null ? 0 : socialAuthResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "SocialAuth(initMode=" + this.initMode + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.initMode, flags);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public SocialAuthResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable SocialAuthResult socialAuthResult) {
            this.result = socialAuthResult;
        }

        public /* synthetic */ SocialAuth(SocialAuthInitMode socialAuthInitMode, boolean z10, SocialAuthResult socialAuthResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(socialAuthInitMode, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : socialAuthResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\u0013\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0013\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001f\u001a\u00020\fHÖ\u0001J\u0016\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001aR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0007R\u001a\u0010\b\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\b\u0010\u0007R\u001a\u0010\u000b\u001a\u00020\fX\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u0016\n\u0002\u0010\u0016\u0012\u0004\b\u0011\u0010\n\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Start;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "", "isReplace", "", "<init>", "(Z)V", "()Z", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "result", "getResult$annotations", "getResult", "()Lkotlin/Unit;", "setResult", "(Lkotlin/Unit;)V", "Lkotlin/Unit;", "component1", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Start extends Screen<Unit> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private Unit result;

        @NotNull
        public static final Parcelable.Creator<Start> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Start> {
            @Override // android.os.Parcelable.Creator
            public final Start createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Start(parcel.readInt() != 0);
            }

            @Override // android.os.Parcelable.Creator
            public final Start[] newArray(int i10) {
                return new Start[i10];
            }
        }

        public Start() {
            this(false, 1, null);
        }

        public static /* synthetic */ Start copy$default(Start start, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = start.isReplace;
            }
            return start.copy(z10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @NotNull
        public final Start copy(boolean isReplace) {
            return new Start(isReplace);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Start) && this.isReplace == ((Start) other).isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            return Boolean.hashCode(this.isReplace);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "Start(isReplace=" + this.isReplace + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isReplace ? 1 : 0);
        }

        public Start(boolean z10) {
            super(null);
            this.isReplace = z10;
            this.id = "z";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public Unit getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable Unit unit) {
            this.result = unit;
        }

        public /* synthetic */ Start(boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void getResult$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00072\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\u0010R\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$UnblockUser;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/unblockuser/presentation/UnblockUserResult;", "failUrl", "", "result", "isReplace", "", "<init>", "(Ljava/lang/String;Lru/mail/authorizationsdk/feature/unblockuser/presentation/UnblockUserResult;Z)V", "getFailUrl", "()Ljava/lang/String;", "getResult", "()Lru/mail/authorizationsdk/feature/unblockuser/presentation/UnblockUserResult;", "setResult", "(Lru/mail/authorizationsdk/feature/unblockuser/presentation/UnblockUserResult;)V", "()Z", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class UnblockUser extends Screen<UnblockUserResult> {

        @NotNull
        private final String failUrl;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private UnblockUserResult result;

        @NotNull
        public static final Parcelable.Creator<UnblockUser> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<UnblockUser> {
            @Override // android.os.Parcelable.Creator
            public final UnblockUser createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new UnblockUser(parcel.readString(), (UnblockUserResult) parcel.readParcelable(UnblockUser.class.getClassLoader()), parcel.readInt() != 0);
            }

            @Override // android.os.Parcelable.Creator
            public final UnblockUser[] newArray(int i10) {
                return new UnblockUser[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public UnblockUser(@NotNull String failUrl, @Nullable UnblockUserResult unblockUserResult, boolean z10) {
            super(null);
            Intrinsics.checkNotNullParameter(failUrl, "failUrl");
            this.failUrl = failUrl;
            this.result = unblockUserResult;
            this.isReplace = z10;
            this.isSupportBack = true;
            this.id = "u";
        }

        public static /* synthetic */ UnblockUser copy$default(UnblockUser unblockUser, String str, UnblockUserResult unblockUserResult, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = unblockUser.failUrl;
            }
            if ((i10 & 2) != 0) {
                unblockUserResult = unblockUser.result;
            }
            if ((i10 & 4) != 0) {
                z10 = unblockUser.isReplace;
            }
            return unblockUser.copy(str, unblockUserResult, z10);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getFailUrl() {
            return this.failUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final UnblockUserResult getResult() {
            return this.result;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @NotNull
        public final UnblockUser copy(@NotNull String failUrl, @Nullable UnblockUserResult result, boolean isReplace) {
            Intrinsics.checkNotNullParameter(failUrl, "failUrl");
            return new UnblockUser(failUrl, result, isReplace);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof UnblockUser)) {
                return false;
            }
            UnblockUser unblockUser = (UnblockUser) other;
            return Intrinsics.areEqual(this.failUrl, unblockUser.failUrl) && Intrinsics.areEqual(this.result, unblockUser.result) && this.isReplace == unblockUser.isReplace;
        }

        @NotNull
        public final String getFailUrl() {
            return this.failUrl;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = this.failUrl.hashCode() * 31;
            UnblockUserResult unblockUserResult = this.result;
            return ((iHashCode + (unblockUserResult == null ? 0 : unblockUserResult.hashCode())) * 31) + Boolean.hashCode(this.isReplace);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "UnblockUser(failUrl=" + this.failUrl + ", result=" + this.result + ", isReplace=" + this.isReplace + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.failUrl);
            dest.writeParcelable(this.result, flags);
            dest.writeInt(this.isReplace ? 1 : 0);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public UnblockUserResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable UnblockUserResult unblockUserResult) {
            this.result = unblockUserResult;
        }

        public /* synthetic */ UnblockUser(String str, UnblockUserResult unblockUserResult, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? null : unblockUserResult, (i10 & 4) != 0 ? false : z10);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkBindInLogin;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/vkbindavailable/presentation/VkBindInLoginResult;", "login", "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/vkbindavailable/presentation/VkBindInLoginResult;)V", "getLogin", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/vkbindavailable/presentation/VkBindInLoginResult;", "setResult", "(Lru/mail/authorizationsdk/feature/vkbindavailable/presentation/VkBindInLoginResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VkBindInLogin extends Screen<VkBindInLoginResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String login;

        @Nullable
        private VkBindInLoginResult result;

        @NotNull
        public static final Parcelable.Creator<VkBindInLogin> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkBindInLogin> {
            @Override // android.os.Parcelable.Creator
            public final VkBindInLogin createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new VkBindInLogin(parcel.readString(), parcel.readInt() != 0, (VkBindInLoginResult) parcel.readParcelable(VkBindInLogin.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final VkBindInLogin[] newArray(int i10) {
                return new VkBindInLogin[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VkBindInLogin(@NotNull String login, boolean z10, @Nullable VkBindInLoginResult vkBindInLoginResult) {
            super(null);
            Intrinsics.checkNotNullParameter(login, "login");
            this.login = login;
            this.isReplace = z10;
            this.result = vkBindInLoginResult;
            this.isSupportBack = VeryBadTemporaryMediator.INSTANCE.isVkBindInLoginSupportBack();
            this.id = Logger.METHOD_V;
        }

        public static /* synthetic */ VkBindInLogin copy$default(VkBindInLogin vkBindInLogin, String str, boolean z10, VkBindInLoginResult vkBindInLoginResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = vkBindInLogin.login;
            }
            if ((i10 & 2) != 0) {
                z10 = vkBindInLogin.isReplace;
            }
            if ((i10 & 4) != 0) {
                vkBindInLoginResult = vkBindInLogin.result;
            }
            return vkBindInLogin.copy(str, z10, vkBindInLoginResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final VkBindInLoginResult getResult() {
            return this.result;
        }

        @NotNull
        public final VkBindInLogin copy(@NotNull String login, boolean isReplace, @Nullable VkBindInLoginResult result) {
            Intrinsics.checkNotNullParameter(login, "login");
            return new VkBindInLogin(login, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VkBindInLogin)) {
                return false;
            }
            VkBindInLogin vkBindInLogin = (VkBindInLogin) other;
            return Intrinsics.areEqual(this.login, vkBindInLogin.login) && this.isReplace == vkBindInLogin.isReplace && Intrinsics.areEqual(this.result, vkBindInLogin.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        public int hashCode() {
            int iHashCode = ((this.login.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            VkBindInLoginResult vkBindInLoginResult = this.result;
            return iHashCode + (vkBindInLoginResult == null ? 0 : vkBindInLoginResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "VkBindInLogin(login=" + this.login + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.login);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public VkBindInLoginResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable VkBindInLoginResult vkBindInLoginResult) {
            this.result = vkBindInLoginResult;
        }

        public /* synthetic */ VkBindInLogin(String str, boolean z10, VkBindInLoginResult vkBindInLoginResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : vkBindInLoginResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\u001eHÖ\u0001J\t\u0010#\u001a\u00020\u0015HÖ\u0001J\u0016\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u001eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0015X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0013\u001a\u0004\b\u0017\u0010\u0018¨\u0006)"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkFragmentSupport;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/VkFragmentSupportResult;", "mode", "Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/model/VkFragmentMode;", "isReplace", "", "result", "<init>", "(Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/model/VkFragmentMode;ZLru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/VkFragmentSupportResult;)V", "getMode", "()Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/model/VkFragmentMode;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/VkFragmentSupportResult;", "setResult", "(Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/VkFragmentSupportResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VkFragmentSupport extends Screen<VkFragmentSupportResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final VkFragmentMode mode;

        @Nullable
        private VkFragmentSupportResult result;

        @NotNull
        public static final Parcelable.Creator<VkFragmentSupport> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkFragmentSupport> {
            @Override // android.os.Parcelable.Creator
            public final VkFragmentSupport createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new VkFragmentSupport((VkFragmentMode) parcel.readParcelable(VkFragmentSupport.class.getClassLoader()), parcel.readInt() != 0, (VkFragmentSupportResult) parcel.readParcelable(VkFragmentSupport.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final VkFragmentSupport[] newArray(int i10) {
                return new VkFragmentSupport[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VkFragmentSupport(@NotNull VkFragmentMode mode, boolean z10, @Nullable VkFragmentSupportResult vkFragmentSupportResult) {
            super(null);
            Intrinsics.checkNotNullParameter(mode, "mode");
            this.mode = mode;
            this.isReplace = z10;
            this.result = vkFragmentSupportResult;
            this.id = "vfl";
        }

        public static /* synthetic */ VkFragmentSupport copy$default(VkFragmentSupport vkFragmentSupport, VkFragmentMode vkFragmentMode, boolean z10, VkFragmentSupportResult vkFragmentSupportResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                vkFragmentMode = vkFragmentSupport.mode;
            }
            if ((i10 & 2) != 0) {
                z10 = vkFragmentSupport.isReplace;
            }
            if ((i10 & 4) != 0) {
                vkFragmentSupportResult = vkFragmentSupport.result;
            }
            return vkFragmentSupport.copy(vkFragmentMode, z10, vkFragmentSupportResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VkFragmentMode getMode() {
            return this.mode;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final VkFragmentSupportResult getResult() {
            return this.result;
        }

        @NotNull
        public final VkFragmentSupport copy(@NotNull VkFragmentMode mode, boolean isReplace, @Nullable VkFragmentSupportResult result) {
            Intrinsics.checkNotNullParameter(mode, "mode");
            return new VkFragmentSupport(mode, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VkFragmentSupport)) {
                return false;
            }
            VkFragmentSupport vkFragmentSupport = (VkFragmentSupport) other;
            return Intrinsics.areEqual(this.mode, vkFragmentSupport.mode) && this.isReplace == vkFragmentSupport.isReplace && Intrinsics.areEqual(this.result, vkFragmentSupport.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final VkFragmentMode getMode() {
            return this.mode;
        }

        public int hashCode() {
            int iHashCode = ((this.mode.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            VkFragmentSupportResult vkFragmentSupportResult = this.result;
            return iHashCode + (vkFragmentSupportResult == null ? 0 : vkFragmentSupportResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "VkFragmentSupport(mode=" + this.mode + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.mode, flags);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public VkFragmentSupportResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable VkFragmentSupportResult vkFragmentSupportResult) {
            this.result = vkFragmentSupportResult;
        }

        public /* synthetic */ VkFragmentSupport(VkFragmentMode vkFragmentMode, boolean z10, VkFragmentSupportResult vkFragmentSupportResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(vkFragmentMode, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : vkFragmentSupportResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0019\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0004HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001e\u001a\u00020\u001fJ\u0013\u0010 \u001a\u00020\u00072\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001fHÖ\u0001J\t\u0010$\u001a\u00020\u0004HÖ\u0001J\u0016\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0007X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\u000eR\u001a\u0010\u0016\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\f¨\u0006*"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$VkPassword;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;", "login", "", "url", "isReplace", "", "result", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;)V", "getLogin", "()Ljava/lang/String;", "getUrl", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;", "setResult", "(Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VkPassword extends Screen<VkPasswordResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @NotNull
        private final String login;

        @Nullable
        private VkPasswordResult result;

        @NotNull
        private final String url;

        @NotNull
        public static final Parcelable.Creator<VkPassword> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkPassword> {
            @Override // android.os.Parcelable.Creator
            public final VkPassword createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new VkPassword(parcel.readString(), parcel.readString(), parcel.readInt() != 0, (VkPasswordResult) parcel.readParcelable(VkPassword.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final VkPassword[] newArray(int i10) {
                return new VkPassword[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VkPassword(@NotNull String login, @NotNull String url, boolean z10, @Nullable VkPasswordResult vkPasswordResult) {
            super(null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(url, "url");
            this.login = login;
            this.url = url;
            this.isReplace = z10;
            this.result = vkPasswordResult;
            this.isSupportBack = true;
            this.id = "V";
        }

        public static /* synthetic */ VkPassword copy$default(VkPassword vkPassword, String str, String str2, boolean z10, VkPasswordResult vkPasswordResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = vkPassword.login;
            }
            if ((i10 & 2) != 0) {
                str2 = vkPassword.url;
            }
            if ((i10 & 4) != 0) {
                z10 = vkPassword.isReplace;
            }
            if ((i10 & 8) != 0) {
                vkPasswordResult = vkPassword.result;
            }
            return vkPassword.copy(str, str2, z10, vkPasswordResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getUrl() {
            return this.url;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final VkPasswordResult getResult() {
            return this.result;
        }

        @NotNull
        public final VkPassword copy(@NotNull String login, @NotNull String url, boolean isReplace, @Nullable VkPasswordResult result) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(url, "url");
            return new VkPassword(login, url, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VkPassword)) {
                return false;
            }
            VkPassword vkPassword = (VkPassword) other;
            return Intrinsics.areEqual(this.login, vkPassword.login) && Intrinsics.areEqual(this.url, vkPassword.url) && this.isReplace == vkPassword.isReplace && Intrinsics.areEqual(this.result, vkPassword.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        public final String getUrl() {
            return this.url;
        }

        public int hashCode() {
            int iHashCode = ((((this.login.hashCode() * 31) + this.url.hashCode()) * 31) + Boolean.hashCode(this.isReplace)) * 31;
            VkPasswordResult vkPasswordResult = this.result;
            return iHashCode + (vkPasswordResult == null ? 0 : vkPasswordResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "VkPassword(login=" + this.login + ", url=" + this.url + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.login);
            dest.writeString(this.url);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public VkPasswordResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable VkPasswordResult vkPasswordResult) {
            this.result = vkPasswordResult;
        }

        public /* synthetic */ VkPassword(String str, String str2, boolean z10, VkPasswordResult vkPasswordResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? null : vkPasswordResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$WrongVkidAccountDialog;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/WrongVkidAccountResult;", "email", "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/WrongVkidAccountResult;)V", "getEmail", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/WrongVkidAccountResult;", "setResult", "(Lru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/WrongVkidAccountResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class WrongVkidAccountDialog extends Screen<WrongVkidAccountResult> {

        @NotNull
        private final String email;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private WrongVkidAccountResult result;

        @NotNull
        public static final Parcelable.Creator<WrongVkidAccountDialog> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<WrongVkidAccountDialog> {
            @Override // android.os.Parcelable.Creator
            public final WrongVkidAccountDialog createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new WrongVkidAccountDialog(parcel.readString(), parcel.readInt() != 0, (WrongVkidAccountResult) parcel.readParcelable(WrongVkidAccountDialog.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final WrongVkidAccountDialog[] newArray(int i10) {
                return new WrongVkidAccountDialog[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public WrongVkidAccountDialog(@NotNull String email, boolean z10, @Nullable WrongVkidAccountResult wrongVkidAccountResult) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            this.email = email;
            this.isReplace = z10;
            this.result = wrongVkidAccountResult;
            this.id = "wvc";
        }

        public static /* synthetic */ WrongVkidAccountDialog copy$default(WrongVkidAccountDialog wrongVkidAccountDialog, String str, boolean z10, WrongVkidAccountResult wrongVkidAccountResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = wrongVkidAccountDialog.email;
            }
            if ((i10 & 2) != 0) {
                z10 = wrongVkidAccountDialog.isReplace;
            }
            if ((i10 & 4) != 0) {
                wrongVkidAccountResult = wrongVkidAccountDialog.result;
            }
            return wrongVkidAccountDialog.copy(str, z10, wrongVkidAccountResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final WrongVkidAccountResult getResult() {
            return this.result;
        }

        @NotNull
        public final WrongVkidAccountDialog copy(@NotNull String email, boolean isReplace, @Nullable WrongVkidAccountResult result) {
            Intrinsics.checkNotNullParameter(email, "email");
            return new WrongVkidAccountDialog(email, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WrongVkidAccountDialog)) {
                return false;
            }
            WrongVkidAccountDialog wrongVkidAccountDialog = (WrongVkidAccountDialog) other;
            return Intrinsics.areEqual(this.email, wrongVkidAccountDialog.email) && this.isReplace == wrongVkidAccountDialog.isReplace && Intrinsics.areEqual(this.result, wrongVkidAccountDialog.result);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.email.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            WrongVkidAccountResult wrongVkidAccountResult = this.result;
            return iHashCode + (wrongVkidAccountResult == null ? 0 : wrongVkidAccountResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "WrongVkidAccountDialog(email=" + this.email + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public WrongVkidAccountResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable WrongVkidAccountResult wrongVkidAccountResult) {
            this.result = wrongVkidAccountResult;
        }

        public /* synthetic */ WrongVkidAccountDialog(String str, boolean z10, WrongVkidAccountResult wrongVkidAccountResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : wrongVkidAccountResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Yahoo;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;", VkCitySelectFragment.HINT_KEY, "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;)V", "getHint", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;", "setResult", "(Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Yahoo extends Screen<YahooResult> {

        @NotNull
        private final String hint;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private YahooResult result;

        @NotNull
        public static final Parcelable.Creator<Yahoo> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Yahoo> {
            @Override // android.os.Parcelable.Creator
            public final Yahoo createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Yahoo(parcel.readString(), parcel.readInt() != 0, (YahooResult) parcel.readParcelable(Yahoo.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Yahoo[] newArray(int i10) {
                return new Yahoo[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Yahoo(@NotNull String hint, boolean z10, @Nullable YahooResult yahooResult) {
            super(null);
            Intrinsics.checkNotNullParameter(hint, "hint");
            this.hint = hint;
            this.isReplace = z10;
            this.result = yahooResult;
            this.id = InterpolatorFields.Path.Y;
        }

        public static /* synthetic */ Yahoo copy$default(Yahoo yahoo, String str, boolean z10, YahooResult yahooResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = yahoo.hint;
            }
            if ((i10 & 2) != 0) {
                z10 = yahoo.isReplace;
            }
            if ((i10 & 4) != 0) {
                yahooResult = yahoo.result;
            }
            return yahoo.copy(str, z10, yahooResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHint() {
            return this.hint;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final YahooResult getResult() {
            return this.result;
        }

        @NotNull
        public final Yahoo copy(@NotNull String hint, boolean isReplace, @Nullable YahooResult result) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            return new Yahoo(hint, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Yahoo)) {
                return false;
            }
            Yahoo yahoo = (Yahoo) other;
            return Intrinsics.areEqual(this.hint, yahoo.hint) && this.isReplace == yahoo.isReplace && Intrinsics.areEqual(this.result, yahoo.result);
        }

        @NotNull
        public final String getHint() {
            return this.hint;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.hint.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            YahooResult yahooResult = this.result;
            return iHashCode + (yahooResult == null ? 0 : yahooResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "Yahoo(hint=" + this.hint + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.hint);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public YahooResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable YahooResult yahooResult) {
            this.result = yahooResult;
        }

        public /* synthetic */ Yahoo(String str, boolean z10, YahooResult yahooResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : yahooResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0002HÆ\u0003J)\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0013\u0010\u001d\u001a\u00020\u00062\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020\u001cHÖ\u0001J\t\u0010!\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u001cR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0014\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u000b¨\u0006'"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$Yandex;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;", VkCitySelectFragment.HINT_KEY, "", "isReplace", "", "result", "<init>", "(Ljava/lang/String;ZLru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;)V", "getHint", "()Ljava/lang/String;", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;", "setResult", "(Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "getId$annotations", "getId", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Yandex extends Screen<YandexResult> {

        @NotNull
        private final String hint;

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private YandexResult result;

        @NotNull
        public static final Parcelable.Creator<Yandex> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Yandex> {
            @Override // android.os.Parcelable.Creator
            public final Yandex createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Yandex(parcel.readString(), parcel.readInt() != 0, (YandexResult) parcel.readParcelable(Yandex.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Yandex[] newArray(int i10) {
                return new Yandex[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Yandex(@NotNull String hint, boolean z10, @Nullable YandexResult yandexResult) {
            super(null);
            Intrinsics.checkNotNullParameter(hint, "hint");
            this.hint = hint;
            this.isReplace = z10;
            this.result = yandexResult;
            this.id = InterpolatorFields.Path.X;
        }

        public static /* synthetic */ Yandex copy$default(Yandex yandex, String str, boolean z10, YandexResult yandexResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = yandex.hint;
            }
            if ((i10 & 2) != 0) {
                z10 = yandex.isReplace;
            }
            if ((i10 & 4) != 0) {
                yandexResult = yandex.result;
            }
            return yandex.copy(str, z10, yandexResult);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getHint() {
            return this.hint;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final YandexResult getResult() {
            return this.result;
        }

        @NotNull
        public final Yandex copy(@NotNull String hint, boolean isReplace, @Nullable YandexResult result) {
            Intrinsics.checkNotNullParameter(hint, "hint");
            return new Yandex(hint, isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Yandex)) {
                return false;
            }
            Yandex yandex = (Yandex) other;
            return Intrinsics.areEqual(this.hint, yandex.hint) && this.isReplace == yandex.isReplace && Intrinsics.areEqual(this.result, yandex.result);
        }

        @NotNull
        public final String getHint() {
            return this.hint;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = ((this.hint.hashCode() * 31) + Boolean.hashCode(this.isReplace)) * 31;
            YandexResult yandexResult = this.result;
            return iHashCode + (yandexResult == null ? 0 : yandexResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "Yandex(hint=" + this.hint + ", isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.hint);
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public YandexResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable YandexResult yandexResult) {
            this.result = yandexResult;
        }

        public /* synthetic */ Yandex(String str, boolean z10, YandexResult yandexResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? false : z10, (i10 & 4) != 0 ? null : yandexResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003J\u001f\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÆ\u0001J\u0006\u0010\u0018\u001a\u00020\u0019J\u0013\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001e\u001a\u00020\u0011HÖ\u0001J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0019R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\bR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0002X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u0004X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\r\u0010\bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0096D¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/screens/Screen$YandexHelp;", "Lru/mail/authorizationsdk/feature/authactivity/screens/Screen;", "Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;", "isReplace", "", "result", "<init>", "(ZLru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;)V", "()Z", "getResult", "()Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;", "setResult", "(Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;)V", "isSupportBack", "isSupportBack$annotations", "()V", "id", "", "getId$annotations", "getId", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class YandexHelp extends Screen<YandexHelpResult> {

        @NotNull
        private final String id;
        private final boolean isReplace;
        private final boolean isSupportBack;

        @Nullable
        private YandexHelpResult result;

        @NotNull
        public static final Parcelable.Creator<YandexHelp> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<YandexHelp> {
            @Override // android.os.Parcelable.Creator
            public final YandexHelp createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new YandexHelp(parcel.readInt() != 0, (YandexHelpResult) parcel.readParcelable(YandexHelp.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final YandexHelp[] newArray(int i10) {
                return new YandexHelp[i10];
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public YandexHelp() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ YandexHelp copy$default(YandexHelp yandexHelp, boolean z10, YandexHelpResult yandexHelpResult, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = yandexHelp.isReplace;
            }
            if ((i10 & 2) != 0) {
                yandexHelpResult = yandexHelp.result;
            }
            return yandexHelp.copy(z10, yandexHelpResult);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsReplace() {
            return this.isReplace;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final YandexHelpResult getResult() {
            return this.result;
        }

        @NotNull
        public final YandexHelp copy(boolean isReplace, @Nullable YandexHelpResult result) {
            return new YandexHelp(isReplace, result);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof YandexHelp)) {
                return false;
            }
            YandexHelp yandexHelp = (YandexHelp) other;
            return this.isReplace == yandexHelp.isReplace && Intrinsics.areEqual(this.result, yandexHelp.result);
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @NotNull
        public String getId() {
            return this.id;
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.isReplace) * 31;
            YandexHelpResult yandexHelpResult = this.result;
            return iHashCode + (yandexHelpResult == null ? 0 : yandexHelpResult.hashCode());
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public boolean isReplace() {
            return this.isReplace;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        /* JADX INFO: renamed from: isSupportBack, reason: from getter */
        public boolean getIsSupportBack() {
            return this.isSupportBack;
        }

        @NotNull
        public String toString() {
            return "YandexHelp(isReplace=" + this.isReplace + ", result=" + this.result + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(this.isReplace ? 1 : 0);
            dest.writeParcelable(this.result, flags);
        }

        public YandexHelp(boolean z10, @Nullable YandexHelpResult yandexHelpResult) {
            super(null);
            this.isReplace = z10;
            this.result = yandexHelpResult;
            this.isSupportBack = true;
            this.id = "h";
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        @Nullable
        public YandexHelpResult getResult() {
            return this.result;
        }

        @Override // ru.mail.authorizationsdk.feature.authactivity.screens.Screen
        public void setResult(@Nullable YandexHelpResult yandexHelpResult) {
            this.result = yandexHelpResult;
        }

        public /* synthetic */ YandexHelp(boolean z10, YandexHelpResult yandexHelpResult, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? null : yandexHelpResult);
        }

        public static /* synthetic */ void getId$annotations() {
        }

        public static /* synthetic */ void isSupportBack$annotations() {
        }
    }

    public /* synthetic */ Screen(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @NotNull
    public abstract String getId();

    @Nullable
    public abstract R getResult();

    /* JADX INFO: renamed from: isInternalNavigation, reason: from getter */
    public final boolean getIsInternalNavigation() {
        return this.isInternalNavigation;
    }

    public abstract boolean isReplace();

    /* JADX INFO: renamed from: isSupportBack */
    public abstract boolean getIsSupportBack();

    public final void onResult(R res) {
        setResult(res);
    }

    public final void setInternalNavigation(boolean z10) {
        this.isInternalNavigation = z10;
    }

    public abstract void setResult(@Nullable R r10);

    private Screen() {
        VeryBadTemporaryMediator veryBadTemporaryMediator = VeryBadTemporaryMediator.INSTANCE;
        this.isInternalNavigation = veryBadTemporaryMediator.isLoginScreenEnabled() || veryBadTemporaryMediator.isPasswordScreenEnabled() || veryBadTemporaryMediator.isRegScreenEnabled();
    }
}
