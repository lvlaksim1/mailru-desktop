package ru.mail.authorizationsdk.feature.authactivity.result;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.QrLoginAnalytics;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerResult;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccountMigrationResult;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.mail.authorizationsdk.feature.google.common.presentation.GoogleResult;
import ru.mail.authorizationsdk.feature.mrim.MrimDialogResult;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeResult;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookResult;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainResult;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordResult;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkResult;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepResult;
import ru.mail.authorizationsdk.feature.socialauth.presentation.SocialAuthResult;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOResult;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordResult;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.authorizationsdk.feature.yandexhelp.YandexHelpResult;
import ru.mail.data.entities.Collector;
import ru.mail.social_auth.domain.SocialAuthType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Parcelize
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u001f\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u001f#$%&'()*+,-./0123456789:;<=>?@A¨\u0006B"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "Landroid/os/Parcelable;", "<init>", "()V", "Ludwig", "OneTimeCode", "DefaultLoginRequired", "Login", "OneTimeCodeSuccess", "PasswordAuth", "CloudAuth", "RestoreWithoutPasswordSuccess", "ImapLocalSuccess", "OAuthImapLocalSuccess", "Yahoo", "OK", "Yandex", "YandexHelp", "MrimDialog", "SSO", "VkPassword", "Outlook", "OpenRestoreVkidOldAuth", "GoogleNative", "SecondStep", "ExternalAccountMigration", "SocialAuth", "CustomServer", "RestorePassword", "RestoreVkId", "BeforeRecoveryVKID", "UnblockUser", "Registration", "Error", "VkBindInLogin", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$BeforeRecoveryVKID;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CloudAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CustomServer;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$DefaultLoginRequired;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$ExternalAccountMigration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$ImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Ludwig;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$MrimDialog;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OAuthImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OK;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OneTimeCode;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OneTimeCodeSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OpenRestoreVkidOldAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestorePassword;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestoreVkId;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestoreWithoutPasswordSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SSO;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SecondStep;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$UnblockUser;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkPassword;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$YandexHelp;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AuthResult implements Parcelable {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$BeforeRecoveryVKID;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", HTTP.CONN_CLOSE, "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$BeforeRecoveryVKID$Close;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class BeforeRecoveryVKID extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$BeforeRecoveryVKID$Close;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$BeforeRecoveryVKID;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Close extends BeforeRecoveryVKID {

            @NotNull
            public static final Close INSTANCE = new Close();

            @NotNull
            public static final Parcelable.Creator<Close> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Close> {
                @Override // android.os.Parcelable.Creator
                public final Close createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Close.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final Close[] newArray(int i10) {
                    return new Close[i10];
                }
            }

            private Close() {
                super(null);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Close);
            }

            public int hashCode() {
                return -1696077692;
            }

            @NotNull
            public String toString() {
                return HTTP.CONN_CLOSE;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        public /* synthetic */ BeforeRecoveryVKID(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private BeforeRecoveryVKID() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CloudAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "Success", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class CloudAuth extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<CloudAuth> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CloudAuth> {
            @Override // android.os.Parcelable.Creator
            public final CloudAuth createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new CloudAuth();
            }

            @Override // android.os.Parcelable.Creator
            public final CloudAuth[] newArray(int i10) {
                return new CloudAuth[i10];
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0006\u0010\u0016\u001a\u00020\u0017J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0017HÖ\u0001J\t\u0010\u001d\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CloudAuth$Success;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CloudAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "login", "", "token", "refreshToken", "expires", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getLogin", "()Ljava/lang/String;", "getToken", "getRefreshToken", "getExpires", "()J", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Success extends CloudAuth implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<Success> CREATOR = new Creator();
            private final long expires;

            @NotNull
            private final String login;

            @NotNull
            private final String refreshToken;

            @NotNull
            private final String token;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Success> {
                @Override // android.os.Parcelable.Creator
                public final Success createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Success(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readLong());
                }

                @Override // android.os.Parcelable.Creator
                public final Success[] newArray(int i10) {
                    return new Success[i10];
                }
            }

            public Success(@NotNull String login, @NotNull String token, @NotNull String refreshToken, long j10) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(token, "token");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                this.login = login;
                this.token = token;
                this.refreshToken = refreshToken;
                this.expires = j10;
            }

            public static /* synthetic */ Success copy$default(Success success, String str, String str2, String str3, long j10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = success.login;
                }
                if ((i10 & 2) != 0) {
                    str2 = success.token;
                }
                if ((i10 & 4) != 0) {
                    str3 = success.refreshToken;
                }
                if ((i10 & 8) != 0) {
                    j10 = success.expires;
                }
                String str4 = str3;
                return success.copy(str, str2, str4, j10);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getToken() {
                return this.token;
            }

            @NotNull
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final long getExpires() {
                return this.expires;
            }

            @NotNull
            public final Success copy(@NotNull String login, @NotNull String token, @NotNull String refreshToken, long expires) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(token, "token");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                return new Success(login, token, refreshToken, expires);
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.CloudAuth, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Success)) {
                    return false;
                }
                Success success = (Success) other;
                return Intrinsics.areEqual(this.login, success.login) && Intrinsics.areEqual(this.token, success.token) && Intrinsics.areEqual(this.refreshToken, success.refreshToken) && this.expires == success.expires;
            }

            public final long getExpires() {
                return this.expires;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @NotNull
            public final String getToken() {
                return this.token;
            }

            public int hashCode() {
                return (((((this.login.hashCode() * 31) + this.token.hashCode()) * 31) + this.refreshToken.hashCode()) * 31) + Long.hashCode(this.expires);
            }

            @NotNull
            public String toString() {
                return "Success(login=" + this.login + ", token=" + this.token + ", refreshToken=" + this.refreshToken + ", expires=" + this.expires + ")";
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.CloudAuth, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.login);
                dest.writeString(this.token);
                dest.writeString(this.refreshToken);
                dest.writeLong(this.expires);
            }
        }

        public CloudAuth() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$CustomServer;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;", "<init>", "(Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/customserver/presentation/CustomServerResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class CustomServer extends AuthResult {

        @NotNull
        private final CustomServerResult result;

        @NotNull
        public static final Parcelable.Creator<CustomServer> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<CustomServer> {
            @Override // android.os.Parcelable.Creator
            public final CustomServer createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new CustomServer((CustomServerResult) parcel.readParcelable(CustomServer.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final CustomServer[] newArray(int i10) {
                return new CustomServer[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CustomServer(@NotNull CustomServerResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NotNull
        public final CustomServerResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$DefaultLoginRequired;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultLoginRequired extends AuthResult {

        @NotNull
        private final String email;

        @NotNull
        public static final Parcelable.Creator<DefaultLoginRequired> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<DefaultLoginRequired> {
            @Override // android.os.Parcelable.Creator
            public final DefaultLoginRequired createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new DefaultLoginRequired(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final DefaultLoginRequired[] newArray(int i10) {
                return new DefaultLoginRequired[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DefaultLoginRequired(@NotNull String email) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            this.email = email;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\b\u0004\u0005\u0006\u0007\b\t\n\u000bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "CommonError", GoogleErrorDescriptions.OAUTH_IMAP_FAILED, "NeedDoRegistration", "ImapRedirect", "YandexOauthReq", "YahooOauthReq", "GoogleOauthReq", "OutlookOauthReq", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$CommonError;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$GoogleOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$ImapRedirect;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$NeedDoRegistration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$OauthImapFailed;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$OutlookOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$YahooOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$YandexOauthReq;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Error extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$CommonError;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class CommonError extends Error {

            @NotNull
            private final String error;

            @NotNull
            public static final Parcelable.Creator<CommonError> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<CommonError> {
                @Override // android.os.Parcelable.Creator
                public final CommonError createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new CommonError(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final CommonError[] newArray(int i10) {
                    return new CommonError[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public CommonError(@NotNull String error) {
                super(null);
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            public static /* synthetic */ CommonError copy$default(CommonError commonError, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = commonError.error;
                }
                return commonError.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getError() {
                return this.error;
            }

            @NotNull
            public final CommonError copy(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                return new CommonError(error);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof CommonError) && Intrinsics.areEqual(this.error, ((CommonError) other).error);
            }

            @NotNull
            public final String getError() {
                return this.error;
            }

            public int hashCode() {
                return this.error.hashCode();
            }

            @NotNull
            public String toString() {
                return "CommonError(error=" + this.error + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.error);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$GoogleOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class GoogleOauthReq extends Error {

            @NotNull
            private final String email;

            @NotNull
            public static final Parcelable.Creator<GoogleOauthReq> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<GoogleOauthReq> {
                @Override // android.os.Parcelable.Creator
                public final GoogleOauthReq createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new GoogleOauthReq(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final GoogleOauthReq[] newArray(int i10) {
                    return new GoogleOauthReq[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public GoogleOauthReq(@NotNull String email) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\u0010\u001a\u00020\u0011J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u001d"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$ImapRedirect;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "password", "settings", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getSettings", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class ImapRedirect extends Error {

            @NotNull
            private final String email;

            @NotNull
            private final String password;

            @NotNull
            private final String settings;

            @NotNull
            public static final Parcelable.Creator<ImapRedirect> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<ImapRedirect> {
                @Override // android.os.Parcelable.Creator
                public final ImapRedirect createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new ImapRedirect(parcel.readString(), parcel.readString(), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final ImapRedirect[] newArray(int i10) {
                    return new ImapRedirect[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ImapRedirect(@NotNull String email, @NotNull String password, @NotNull String settings) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                Intrinsics.checkNotNullParameter(settings, "settings");
                this.email = email;
                this.password = password;
                this.settings = settings;
            }

            public static /* synthetic */ ImapRedirect copy$default(ImapRedirect imapRedirect, String str, String str2, String str3, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = imapRedirect.email;
                }
                if ((i10 & 2) != 0) {
                    str2 = imapRedirect.password;
                }
                if ((i10 & 4) != 0) {
                    str3 = imapRedirect.settings;
                }
                return imapRedirect.copy(str, str2, str3);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getPassword() {
                return this.password;
            }

            @NotNull
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getSettings() {
                return this.settings;
            }

            @NotNull
            public final ImapRedirect copy(@NotNull String email, @NotNull String password, @NotNull String settings) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                Intrinsics.checkNotNullParameter(settings, "settings");
                return new ImapRedirect(email, password, settings);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ImapRedirect)) {
                    return false;
                }
                ImapRedirect imapRedirect = (ImapRedirect) other;
                return Intrinsics.areEqual(this.email, imapRedirect.email) && Intrinsics.areEqual(this.password, imapRedirect.password) && Intrinsics.areEqual(this.settings, imapRedirect.settings);
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getPassword() {
                return this.password;
            }

            @NotNull
            public final String getSettings() {
                return this.settings;
            }

            public int hashCode() {
                return (((this.email.hashCode() * 31) + this.password.hashCode()) * 31) + this.settings.hashCode();
            }

            @NotNull
            public String toString() {
                return "ImapRedirect(email=" + this.email + ", password=" + this.password + ", settings=" + this.settings + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.password);
                dest.writeString(this.settings);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\u0013\u0010\u0016\u001a\u00020\u00072\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006 "}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$NeedDoRegistration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "password", "regId", "isNeedCaptcha", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getRegId", "()Z", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedDoRegistration extends Error {

            @NotNull
            private final String email;
            private final boolean isNeedCaptcha;

            @NotNull
            private final String password;

            @NotNull
            private final String regId;

            @NotNull
            public static final Parcelable.Creator<NeedDoRegistration> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<NeedDoRegistration> {
                @Override // android.os.Parcelable.Creator
                public final NeedDoRegistration createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new NeedDoRegistration(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                }

                @Override // android.os.Parcelable.Creator
                public final NeedDoRegistration[] newArray(int i10) {
                    return new NeedDoRegistration[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NeedDoRegistration(@NotNull String email, @NotNull String password, @NotNull String regId, boolean z10) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                Intrinsics.checkNotNullParameter(regId, "regId");
                this.email = email;
                this.password = password;
                this.regId = regId;
                this.isNeedCaptcha = z10;
            }

            public static /* synthetic */ NeedDoRegistration copy$default(NeedDoRegistration needDoRegistration, String str, String str2, String str3, boolean z10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = needDoRegistration.email;
                }
                if ((i10 & 2) != 0) {
                    str2 = needDoRegistration.password;
                }
                if ((i10 & 4) != 0) {
                    str3 = needDoRegistration.regId;
                }
                if ((i10 & 8) != 0) {
                    z10 = needDoRegistration.isNeedCaptcha;
                }
                return needDoRegistration.copy(str, str2, str3, z10);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getPassword() {
                return this.password;
            }

            @NotNull
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getRegId() {
                return this.regId;
            }

            /* JADX INFO: renamed from: component4, reason: from getter */
            public final boolean getIsNeedCaptcha() {
                return this.isNeedCaptcha;
            }

            @NotNull
            public final NeedDoRegistration copy(@NotNull String email, @NotNull String password, @NotNull String regId, boolean isNeedCaptcha) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                Intrinsics.checkNotNullParameter(regId, "regId");
                return new NeedDoRegistration(email, password, regId, isNeedCaptcha);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof NeedDoRegistration)) {
                    return false;
                }
                NeedDoRegistration needDoRegistration = (NeedDoRegistration) other;
                return Intrinsics.areEqual(this.email, needDoRegistration.email) && Intrinsics.areEqual(this.password, needDoRegistration.password) && Intrinsics.areEqual(this.regId, needDoRegistration.regId) && this.isNeedCaptcha == needDoRegistration.isNeedCaptcha;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getPassword() {
                return this.password;
            }

            @NotNull
            public final String getRegId() {
                return this.regId;
            }

            public int hashCode() {
                return (((((this.email.hashCode() * 31) + this.password.hashCode()) * 31) + this.regId.hashCode()) * 31) + Boolean.hashCode(this.isNeedCaptcha);
            }

            public final boolean isNeedCaptcha() {
                return this.isNeedCaptcha;
            }

            @NotNull
            public String toString() {
                return "NeedDoRegistration(email=" + this.email + ", password=" + this.password + ", regId=" + this.regId + ", isNeedCaptcha=" + this.isNeedCaptcha + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.password);
                dest.writeString(this.regId);
                dest.writeInt(this.isNeedCaptcha ? 1 : 0);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$OauthImapFailed;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class OauthImapFailed extends Error {

            @NotNull
            private final String email;

            @NotNull
            public static final Parcelable.Creator<OauthImapFailed> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<OauthImapFailed> {
                @Override // android.os.Parcelable.Creator
                public final OauthImapFailed createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new OauthImapFailed(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final OauthImapFailed[] newArray(int i10) {
                    return new OauthImapFailed[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OauthImapFailed(@NotNull String email) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ OauthImapFailed copy$default(OauthImapFailed oauthImapFailed, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = oauthImapFailed.email;
                }
                return oauthImapFailed.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final OauthImapFailed copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new OauthImapFailed(email);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof OauthImapFailed) && Intrinsics.areEqual(this.email, ((OauthImapFailed) other).email);
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            public int hashCode() {
                return this.email.hashCode();
            }

            @NotNull
            public String toString() {
                return "OauthImapFailed(email=" + this.email + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$OutlookOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OutlookOauthReq extends Error {

            @NotNull
            private final String email;

            @NotNull
            public static final Parcelable.Creator<OutlookOauthReq> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<OutlookOauthReq> {
                @Override // android.os.Parcelable.Creator
                public final OutlookOauthReq createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new OutlookOauthReq(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final OutlookOauthReq[] newArray(int i10) {
                    return new OutlookOauthReq[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public OutlookOauthReq(@NotNull String email) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$YahooOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class YahooOauthReq extends Error {

            @NotNull
            private final String email;

            @NotNull
            public static final Parcelable.Creator<YahooOauthReq> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<YahooOauthReq> {
                @Override // android.os.Parcelable.Creator
                public final YahooOauthReq createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new YahooOauthReq(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final YahooOauthReq[] newArray(int i10) {
                    return new YahooOauthReq[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public YahooOauthReq(@NotNull String email) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error$YandexOauthReq;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class YandexOauthReq extends Error {

            @NotNull
            private final String email;

            @NotNull
            public static final Parcelable.Creator<YandexOauthReq> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<YandexOauthReq> {
                @Override // android.os.Parcelable.Creator
                public final YandexOauthReq createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new YandexOauthReq(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final YandexOauthReq[] newArray(int i10) {
                    return new YandexOauthReq[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public YandexOauthReq(@NotNull String email) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
            }
        }

        public /* synthetic */ Error(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Error() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$ExternalAccountMigration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;", "<init>", "(Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/externalmigration/presentation/ExternalAccountMigrationResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ExternalAccountMigration extends AuthResult {

        @NotNull
        private final ExternalAccountMigrationResult result;

        @NotNull
        public static final Parcelable.Creator<ExternalAccountMigration> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ExternalAccountMigration> {
            @Override // android.os.Parcelable.Creator
            public final ExternalAccountMigration createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new ExternalAccountMigration((ExternalAccountMigrationResult) parcel.readParcelable(ExternalAccountMigration.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final ExternalAccountMigration[] newArray(int i10) {
                return new ExternalAccountMigration[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExternalAccountMigration(@NotNull ExternalAccountMigrationResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final ExternalAccountMigrationResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "AuthDoneGoogle", "OtherGoogle", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class GoogleNative extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<GoogleNative> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006\u001a"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative$AuthDoneGoogle;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "googleAccessToken", "googleRefreshToken", "xmailMigrationFrom", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getGoogleAccessToken", "getGoogleRefreshToken", "getXmailMigrationFrom", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AuthDoneGoogle extends GoogleNative implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<AuthDoneGoogle> CREATOR = new Creator();

            @NotNull
            private final String accessToken;

            @NotNull
            private final String email;

            @NotNull
            private final String googleAccessToken;

            @NotNull
            private final String googleRefreshToken;

            @NotNull
            private final String refreshToken;

            @Nullable
            private final String xmailMigrationFrom;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AuthDoneGoogle> {
                @Override // android.os.Parcelable.Creator
                public final AuthDoneGoogle createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AuthDoneGoogle(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final AuthDoneGoogle[] newArray(int i10) {
                    return new AuthDoneGoogle[i10];
                }
            }

            public AuthDoneGoogle(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String googleAccessToken, @NotNull String googleRefreshToken, @Nullable String str) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(googleAccessToken, "googleAccessToken");
                Intrinsics.checkNotNullParameter(googleRefreshToken, "googleRefreshToken");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.googleAccessToken = googleAccessToken;
                this.googleRefreshToken = googleRefreshToken;
                this.xmailMigrationFrom = str;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.GoogleNative, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getGoogleAccessToken() {
                return this.googleAccessToken;
            }

            @NotNull
            public final String getGoogleRefreshToken() {
                return this.googleRefreshToken;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @Nullable
            public final String getXmailMigrationFrom() {
                return this.xmailMigrationFrom;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.GoogleNative, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeString(this.googleAccessToken);
                dest.writeString(this.googleRefreshToken);
                dest.writeString(this.xmailMigrationFrom);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<GoogleNative> {
            @Override // android.os.Parcelable.Creator
            public final GoogleNative createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new GoogleNative();
            }

            @Override // android.os.Parcelable.Creator
            public final GoogleNative[] newArray(int i10) {
                return new GoogleNative[i10];
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative$OtherGoogle;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$GoogleNative;", "result", "Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;", "<init>", "(Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OtherGoogle extends GoogleNative {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<OtherGoogle> CREATOR = new Creator();

            @NotNull
            private final GoogleResult result;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<OtherGoogle> {
                @Override // android.os.Parcelable.Creator
                public final OtherGoogle createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new OtherGoogle((GoogleResult) parcel.readParcelable(OtherGoogle.class.getClassLoader()));
                }

                @Override // android.os.Parcelable.Creator
                public final OtherGoogle[] newArray(int i10) {
                    return new OtherGoogle[i10];
                }
            }

            public OtherGoogle(@NotNull GoogleResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.GoogleNative, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final GoogleResult getResult() {
                return this.result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.GoogleNative, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeParcelable(this.result, flags);
            }
        }

        public GoogleNative() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0006\u0010\u0011\u001a\u00020\u0012J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u001e"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$ImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", "password", "providerInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getProviderInfo", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ImapLocalSuccess extends AuthResult implements AuthResultSuccess {

        @NotNull
        private final String email;

        @NotNull
        private final String password;

        @NotNull
        private final String providerInfo;

        @NotNull
        public static final Parcelable.Creator<ImapLocalSuccess> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<ImapLocalSuccess> {
            @Override // android.os.Parcelable.Creator
            public final ImapLocalSuccess createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new ImapLocalSuccess(parcel.readString(), parcel.readString(), parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final ImapLocalSuccess[] newArray(int i10) {
                return new ImapLocalSuccess[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ImapLocalSuccess(@NotNull String email, @NotNull String password, @NotNull String providerInfo) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            this.email = email;
            this.password = password;
            this.providerInfo = providerInfo;
        }

        public static /* synthetic */ ImapLocalSuccess copy$default(ImapLocalSuccess imapLocalSuccess, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = imapLocalSuccess.email;
            }
            if ((i10 & 2) != 0) {
                str2 = imapLocalSuccess.password;
            }
            if ((i10 & 4) != 0) {
                str3 = imapLocalSuccess.providerInfo;
            }
            return imapLocalSuccess.copy(str, str2, str3);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getProviderInfo() {
            return this.providerInfo;
        }

        @NotNull
        public final ImapLocalSuccess copy(@NotNull String email, @NotNull String password, @NotNull String providerInfo) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            return new ImapLocalSuccess(email, password, providerInfo);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ImapLocalSuccess)) {
                return false;
            }
            ImapLocalSuccess imapLocalSuccess = (ImapLocalSuccess) other;
            return Intrinsics.areEqual(this.email, imapLocalSuccess.email) && Intrinsics.areEqual(this.password, imapLocalSuccess.password) && Intrinsics.areEqual(this.providerInfo, imapLocalSuccess.providerInfo);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        public final String getProviderInfo() {
            return this.providerInfo;
        }

        public int hashCode() {
            return (((this.email.hashCode() * 31) + this.password.hashCode()) * 31) + this.providerInfo.hashCode();
        }

        @NotNull
        public String toString() {
            return "ImapLocalSuccess(email=" + this.email + ", password=" + this.password + ", providerInfo=" + this.providerInfo + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.password);
            dest.writeString(this.providerInfo);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001:\u0004\u000b\f\r\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "NeedRegistration", "AlreadyLoggedIn", "BackClick", "VkSilentAuthDone", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Login extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<Login> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login$AlreadyLoggedIn;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class AlreadyLoggedIn extends Login {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<AlreadyLoggedIn> CREATOR = new Creator();

            @NotNull
            private final String email;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AlreadyLoggedIn> {
                @Override // android.os.Parcelable.Creator
                public final AlreadyLoggedIn createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AlreadyLoggedIn(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final AlreadyLoggedIn[] newArray(int i10) {
                    return new AlreadyLoggedIn[i10];
                }
            }

            public AlreadyLoggedIn(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ AlreadyLoggedIn copy$default(AlreadyLoggedIn alreadyLoggedIn, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = alreadyLoggedIn.email;
                }
                return alreadyLoggedIn.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final AlreadyLoggedIn copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new AlreadyLoggedIn(email);
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof AlreadyLoggedIn) && Intrinsics.areEqual(this.email, ((AlreadyLoggedIn) other).email);
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            public int hashCode() {
                return this.email.hashCode();
            }

            @NotNull
            public String toString() {
                return "AlreadyLoggedIn(email=" + this.email + ")";
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login$BackClick;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class BackClick extends Login {
            public static final int $stable = 0;

            @NotNull
            public static final BackClick INSTANCE = new BackClick();

            @NotNull
            public static final Parcelable.Creator<BackClick> CREATOR = new Creator();

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<BackClick> {
                @Override // android.os.Parcelable.Creator
                public final BackClick createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return BackClick.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final BackClick[] newArray(int i10) {
                    return new BackClick[i10];
                }
            }

            private BackClick() {
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof BackClick);
            }

            public int hashCode() {
                return -61151386;
            }

            @NotNull
            public String toString() {
                return "BackClick";
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Login> {
            @Override // android.os.Parcelable.Creator
            public final Login createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new Login();
            }

            @Override // android.os.Parcelable.Creator
            public final Login[] newArray(int i10) {
                return new Login[i10];
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login$NeedRegistration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedRegistration extends Login {
            public static final int $stable = 0;

            @NotNull
            public static final NeedRegistration INSTANCE = new NeedRegistration();

            @NotNull
            public static final Parcelable.Creator<NeedRegistration> CREATOR = new Creator();

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<NeedRegistration> {
                @Override // android.os.Parcelable.Creator
                public final NeedRegistration createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return NeedRegistration.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final NeedRegistration[] newArray(int i10) {
                    return new NeedRegistration[i10];
                }
            }

            private NeedRegistration() {
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof NeedRegistration);
            }

            public int hashCode() {
                return 128386634;
            }

            @NotNull
            public String toString() {
                return "NeedRegistration";
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login$VkSilentAuthDone;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Login;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "silentToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getSilentToken", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class VkSilentAuthDone extends Login implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<VkSilentAuthDone> CREATOR = new Creator();

            @NotNull
            private final String accessToken;

            @NotNull
            private final String email;

            @NotNull
            private final String refreshToken;

            @NotNull
            private final String silentToken;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<VkSilentAuthDone> {
                @Override // android.os.Parcelable.Creator
                public final VkSilentAuthDone createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new VkSilentAuthDone(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final VkSilentAuthDone[] newArray(int i10) {
                    return new VkSilentAuthDone[i10];
                }
            }

            public VkSilentAuthDone(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String silentToken) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(silentToken, "silentToken");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.silentToken = silentToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @NotNull
            public final String getSilentToken() {
                return this.silentToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Login, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeString(this.silentToken);
            }
        }

        public Login() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Ludwig;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "<init>", "(Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/captcha/LudwigCaptchaResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Ludwig extends AuthResult {

        @NotNull
        private final LudwigCaptchaResult result;

        @NotNull
        public static final Parcelable.Creator<Ludwig> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Ludwig> {
            @Override // android.os.Parcelable.Creator
            public final Ludwig createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new Ludwig((LudwigCaptchaResult) parcel.readParcelable(Ludwig.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final Ludwig[] newArray(int i10) {
                return new Ludwig[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Ludwig(@NotNull LudwigCaptchaResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final LudwigCaptchaResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$MrimDialog;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/mrim/MrimDialogResult;", "<init>", "(Lru/mail/authorizationsdk/feature/mrim/MrimDialogResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/mrim/MrimDialogResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MrimDialog extends AuthResult {

        @NotNull
        private final MrimDialogResult result;

        @NotNull
        public static final Parcelable.Creator<MrimDialog> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<MrimDialog> {
            @Override // android.os.Parcelable.Creator
            public final MrimDialog createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new MrimDialog((MrimDialogResult) parcel.readParcelable(MrimDialog.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final MrimDialog[] newArray(int i10) {
                return new MrimDialog[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MrimDialog(@NotNull MrimDialogResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final MrimDialogResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0006\u0010\u0014\u001a\u00020\u0015J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0015R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006!"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OAuthImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", "vendorAccessToken", "vendorRefreshToken", "providerInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getVendorAccessToken", "getVendorRefreshToken", "getProviderInfo", "component1", "component2", "component3", "component4", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OAuthImapLocalSuccess extends AuthResult implements AuthResultSuccess {

        @NotNull
        private final String email;

        @NotNull
        private final String providerInfo;

        @NotNull
        private final String vendorAccessToken;

        @NotNull
        private final String vendorRefreshToken;

        @NotNull
        public static final Parcelable.Creator<OAuthImapLocalSuccess> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OAuthImapLocalSuccess> {
            @Override // android.os.Parcelable.Creator
            public final OAuthImapLocalSuccess createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new OAuthImapLocalSuccess(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final OAuthImapLocalSuccess[] newArray(int i10) {
                return new OAuthImapLocalSuccess[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OAuthImapLocalSuccess(@NotNull String email, @NotNull String vendorAccessToken, @NotNull String vendorRefreshToken, @NotNull String providerInfo) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(vendorAccessToken, "vendorAccessToken");
            Intrinsics.checkNotNullParameter(vendorRefreshToken, "vendorRefreshToken");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            this.email = email;
            this.vendorAccessToken = vendorAccessToken;
            this.vendorRefreshToken = vendorRefreshToken;
            this.providerInfo = providerInfo;
        }

        public static /* synthetic */ OAuthImapLocalSuccess copy$default(OAuthImapLocalSuccess oAuthImapLocalSuccess, String str, String str2, String str3, String str4, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = oAuthImapLocalSuccess.email;
            }
            if ((i10 & 2) != 0) {
                str2 = oAuthImapLocalSuccess.vendorAccessToken;
            }
            if ((i10 & 4) != 0) {
                str3 = oAuthImapLocalSuccess.vendorRefreshToken;
            }
            if ((i10 & 8) != 0) {
                str4 = oAuthImapLocalSuccess.providerInfo;
            }
            return oAuthImapLocalSuccess.copy(str, str2, str3, str4);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getVendorAccessToken() {
            return this.vendorAccessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getVendorRefreshToken() {
            return this.vendorRefreshToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getProviderInfo() {
            return this.providerInfo;
        }

        @NotNull
        public final OAuthImapLocalSuccess copy(@NotNull String email, @NotNull String vendorAccessToken, @NotNull String vendorRefreshToken, @NotNull String providerInfo) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(vendorAccessToken, "vendorAccessToken");
            Intrinsics.checkNotNullParameter(vendorRefreshToken, "vendorRefreshToken");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            return new OAuthImapLocalSuccess(email, vendorAccessToken, vendorRefreshToken, providerInfo);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OAuthImapLocalSuccess)) {
                return false;
            }
            OAuthImapLocalSuccess oAuthImapLocalSuccess = (OAuthImapLocalSuccess) other;
            return Intrinsics.areEqual(this.email, oAuthImapLocalSuccess.email) && Intrinsics.areEqual(this.vendorAccessToken, oAuthImapLocalSuccess.vendorAccessToken) && Intrinsics.areEqual(this.vendorRefreshToken, oAuthImapLocalSuccess.vendorRefreshToken) && Intrinsics.areEqual(this.providerInfo, oAuthImapLocalSuccess.providerInfo);
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getProviderInfo() {
            return this.providerInfo;
        }

        @NotNull
        public final String getVendorAccessToken() {
            return this.vendorAccessToken;
        }

        @NotNull
        public final String getVendorRefreshToken() {
            return this.vendorRefreshToken;
        }

        public int hashCode() {
            return (((((this.email.hashCode() * 31) + this.vendorAccessToken.hashCode()) * 31) + this.vendorRefreshToken.hashCode()) * 31) + this.providerInfo.hashCode();
        }

        @NotNull
        public String toString() {
            return "OAuthImapLocalSuccess(email=" + this.email + ", vendorAccessToken=" + this.vendorAccessToken + ", vendorRefreshToken=" + this.vendorRefreshToken + ", providerInfo=" + this.providerInfo + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.vendorAccessToken);
            dest.writeString(this.vendorRefreshToken);
            dest.writeString(this.providerInfo);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001:\u0001\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OK;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "AuthDone", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class OK extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<OK> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0016"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OK$AuthDone;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OK;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", CommonConstant.KEY_ACCESS_TOKEN, "", "refreshToken", "expiresIn", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;J)V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "getExpiresIn", "()J", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AuthDone extends OK implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<AuthDone> CREATOR = new Creator();

            @NotNull
            private final String accessToken;
            private final long expiresIn;

            @NotNull
            private final String refreshToken;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AuthDone> {
                @Override // android.os.Parcelable.Creator
                public final AuthDone createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AuthDone(parcel.readString(), parcel.readString(), parcel.readLong());
                }

                @Override // android.os.Parcelable.Creator
                public final AuthDone[] newArray(int i10) {
                    return new AuthDone[i10];
                }
            }

            public AuthDone(@NotNull String accessToken, @NotNull String refreshToken, long j10) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.expiresIn = j10;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.OK, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            public final long getExpiresIn() {
                return this.expiresIn;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.OK, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeLong(this.expiresIn);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OK> {
            @Override // android.os.Parcelable.Creator
            public final OK createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new OK();
            }

            @Override // android.os.Parcelable.Creator
            public final OK[] newArray(int i10) {
                return new OK[i10];
            }
        }

        public OK() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OneTimeCode;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;", "<init>", "(Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/onetimecode/OneTimeCodeResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OneTimeCode extends AuthResult {

        @NotNull
        private final OneTimeCodeResult result;

        @NotNull
        public static final Parcelable.Creator<OneTimeCode> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OneTimeCode> {
            @Override // android.os.Parcelable.Creator
            public final OneTimeCode createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new OneTimeCode((OneTimeCodeResult) parcel.readParcelable(OneTimeCode.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final OneTimeCode[] newArray(int i10) {
                return new OneTimeCode[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OneTimeCode(@NotNull OneTimeCodeResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final OneTimeCodeResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OpenRestoreVkidOldAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "emailForRestore", "", "<init>", "(Ljava/lang/String;)V", "getEmailForRestore", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OpenRestoreVkidOldAuth extends AuthResult {

        @Nullable
        private final String emailForRestore;

        @NotNull
        public static final Parcelable.Creator<OpenRestoreVkidOldAuth> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OpenRestoreVkidOldAuth> {
            @Override // android.os.Parcelable.Creator
            public final OpenRestoreVkidOldAuth createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new OpenRestoreVkidOldAuth(parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final OpenRestoreVkidOldAuth[] newArray(int i10) {
                return new OpenRestoreVkidOldAuth[i10];
            }
        }

        public OpenRestoreVkidOldAuth(@Nullable String str) {
            super(null);
            this.emailForRestore = str;
        }

        public static /* synthetic */ OpenRestoreVkidOldAuth copy$default(OpenRestoreVkidOldAuth openRestoreVkidOldAuth, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = openRestoreVkidOldAuth.emailForRestore;
            }
            return openRestoreVkidOldAuth.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmailForRestore() {
            return this.emailForRestore;
        }

        @NotNull
        public final OpenRestoreVkidOldAuth copy(@Nullable String emailForRestore) {
            return new OpenRestoreVkidOldAuth(emailForRestore);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof OpenRestoreVkidOldAuth) && Intrinsics.areEqual(this.emailForRestore, ((OpenRestoreVkidOldAuth) other).emailForRestore);
        }

        @Nullable
        public final String getEmailForRestore() {
            return this.emailForRestore;
        }

        public int hashCode() {
            String str = this.emailForRestore;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @NotNull
        public String toString() {
            return "OpenRestoreVkidOldAuth(emailForRestore=" + this.emailForRestore + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.emailForRestore);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "AuthDoneOutlook", "OtherOutlook", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Outlook extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<Outlook> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0018"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook$AuthDoneOutlook;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "outlookAccessToken", "outlookRefreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getOutlookAccessToken", "getOutlookRefreshToken", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AuthDoneOutlook extends Outlook implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<AuthDoneOutlook> CREATOR = new Creator();

            @NotNull
            private final String accessToken;

            @NotNull
            private final String email;

            @NotNull
            private final String outlookAccessToken;

            @NotNull
            private final String outlookRefreshToken;

            @NotNull
            private final String refreshToken;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AuthDoneOutlook> {
                @Override // android.os.Parcelable.Creator
                public final AuthDoneOutlook createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AuthDoneOutlook(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final AuthDoneOutlook[] newArray(int i10) {
                    return new AuthDoneOutlook[i10];
                }
            }

            public AuthDoneOutlook(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String outlookAccessToken, @NotNull String outlookRefreshToken) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(outlookAccessToken, "outlookAccessToken");
                Intrinsics.checkNotNullParameter(outlookRefreshToken, "outlookRefreshToken");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.outlookAccessToken = outlookAccessToken;
                this.outlookRefreshToken = outlookRefreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Outlook, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getOutlookAccessToken() {
                return this.outlookAccessToken;
            }

            @NotNull
            public final String getOutlookRefreshToken() {
                return this.outlookRefreshToken;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Outlook, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeString(this.outlookAccessToken);
                dest.writeString(this.outlookRefreshToken);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Outlook> {
            @Override // android.os.Parcelable.Creator
            public final Outlook createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new Outlook();
            }

            @Override // android.os.Parcelable.Creator
            public final Outlook[] newArray(int i10) {
                return new Outlook[i10];
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook$OtherOutlook;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Outlook;", "result", "Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;", "<init>", "(Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OtherOutlook extends Outlook {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<OtherOutlook> CREATOR = new Creator();

            @NotNull
            private final OutlookResult result;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<OtherOutlook> {
                @Override // android.os.Parcelable.Creator
                public final OtherOutlook createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new OtherOutlook((OutlookResult) parcel.readParcelable(OtherOutlook.class.getClassLoader()));
                }

                @Override // android.os.Parcelable.Creator
                public final OtherOutlook[] newArray(int i10) {
                    return new OtherOutlook[i10];
                }
            }

            public OtherOutlook(@NotNull OutlookResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Outlook, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final OutlookResult getResult() {
                return this.result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Outlook, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeParcelable(this.result, flags);
            }
        }

        public Outlook() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "AfterRegAuth", "BackClick", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration$AfterRegAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration$BackClick;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Registration extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration$AfterRegAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration;", "result", "Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;", "<init>", "(Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/registration/presentation/RegistrationMainResult;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class AfterRegAuth extends Registration {

            @NotNull
            private final RegistrationMainResult result;

            @NotNull
            public static final Parcelable.Creator<AfterRegAuth> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AfterRegAuth> {
                @Override // android.os.Parcelable.Creator
                public final AfterRegAuth createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AfterRegAuth((RegistrationMainResult) parcel.readParcelable(AfterRegAuth.class.getClassLoader()));
                }

                @Override // android.os.Parcelable.Creator
                public final AfterRegAuth[] newArray(int i10) {
                    return new AfterRegAuth[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AfterRegAuth(@NotNull RegistrationMainResult result) {
                super(null);
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            public static /* synthetic */ AfterRegAuth copy$default(AfterRegAuth afterRegAuth, RegistrationMainResult registrationMainResult, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    registrationMainResult = afterRegAuth.result;
                }
                return afterRegAuth.copy(registrationMainResult);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final RegistrationMainResult getResult() {
                return this.result;
            }

            @NotNull
            public final AfterRegAuth copy(@NotNull RegistrationMainResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                return new AfterRegAuth(result);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof AfterRegAuth) && Intrinsics.areEqual(this.result, ((AfterRegAuth) other).result);
            }

            @NotNull
            public final RegistrationMainResult getResult() {
                return this.result;
            }

            public int hashCode() {
                return this.result.hashCode();
            }

            @NotNull
            public String toString() {
                return "AfterRegAuth(result=" + this.result + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeParcelable(this.result, flags);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration$BackClick;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Registration;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class BackClick extends Registration {

            @NotNull
            public static final BackClick INSTANCE = new BackClick();

            @NotNull
            public static final Parcelable.Creator<BackClick> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<BackClick> {
                @Override // android.os.Parcelable.Creator
                public final BackClick createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return BackClick.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final BackClick[] newArray(int i10) {
                    return new BackClick[i10];
                }
            }

            private BackClick() {
                super(null);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof BackClick);
            }

            public int hashCode() {
                return 224848802;
            }

            @NotNull
            public String toString() {
                return "BackClick";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        public /* synthetic */ Registration(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Registration() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestorePassword;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;", "<init>", "(Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/restorepassword/presentation/RestorePasswordResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class RestorePassword extends AuthResult {

        @NotNull
        private final RestorePasswordResult result;

        @NotNull
        public static final Parcelable.Creator<RestorePassword> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<RestorePassword> {
            @Override // android.os.Parcelable.Creator
            public final RestorePassword createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new RestorePassword((RestorePasswordResult) parcel.readParcelable(RestorePassword.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final RestorePassword[] newArray(int i10) {
                return new RestorePassword[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestorePassword(@NotNull RestorePasswordResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NotNull
        public final RestorePasswordResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestoreVkId;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;", "<init>", "(Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/restorevkpassword/presentation/RestoreVkResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class RestoreVkId extends AuthResult {

        @NotNull
        private final RestoreVkResult result;

        @NotNull
        public static final Parcelable.Creator<RestoreVkId> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<RestoreVkId> {
            @Override // android.os.Parcelable.Creator
            public final RestoreVkId createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new RestoreVkId((RestoreVkResult) parcel.readParcelable(RestoreVkId.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final RestoreVkId[] newArray(int i10) {
                return new RestoreVkId[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestoreVkId(@NotNull RestoreVkResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NotNull
        public final RestoreVkResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0014"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$RestoreWithoutPasswordSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class RestoreWithoutPasswordSuccess extends AuthResult implements AuthResultSuccess {

        @NotNull
        private final String accessToken;

        @NotNull
        private final String email;

        @NotNull
        private final String refreshToken;

        @NotNull
        public static final Parcelable.Creator<RestoreWithoutPasswordSuccess> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<RestoreWithoutPasswordSuccess> {
            @Override // android.os.Parcelable.Creator
            public final RestoreWithoutPasswordSuccess createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new RestoreWithoutPasswordSuccess(parcel.readString(), parcel.readString(), parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final RestoreWithoutPasswordSuccess[] newArray(int i10) {
                return new RestoreWithoutPasswordSuccess[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public RestoreWithoutPasswordSuccess(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.email = email;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.accessToken);
            dest.writeString(this.refreshToken);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SSO;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;", "<init>", "(Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/sso/presentation/SSOResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SSO extends AuthResult {
        public static final int $stable = 0;

        @NotNull
        public static final Parcelable.Creator<SSO> CREATOR = new Creator();

        @NotNull
        private final SSOResult result;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<SSO> {
            @Override // android.os.Parcelable.Creator
            public final SSO createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new SSO((SSOResult) parcel.readParcelable(SSO.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final SSO[] newArray(int i10) {
                return new SSO[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SSO(@NotNull SSOResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final SSOResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SecondStep;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;", "<init>", "(Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SecondStep extends AuthResult {

        @NotNull
        private final SecondStepResult result;

        @NotNull
        public static final Parcelable.Creator<SecondStep> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<SecondStep> {
            @Override // android.os.Parcelable.Creator
            public final SecondStep createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new SecondStep((SecondStepResult) parcel.readParcelable(SecondStep.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final SecondStep[] newArray(int i10) {
                return new SecondStep[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SecondStep(@NotNull SecondStepResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final SecondStepResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "ExternalResult", "AuthDone", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth$AuthDone;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth$ExternalResult;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class SocialAuth extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0016\u001a\u00020\bHÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0004HÆ\u0003J=\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020\u001aHÖ\u0001J\t\u0010 \u001a\u00020\u0004HÖ\u0001J\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001aR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\r¨\u0006&"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth$AuthDone;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "authType", "Lru/mail/social_auth/domain/SocialAuthType;", "bindType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lru/mail/social_auth/domain/SocialAuthType;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getAuthType", "()Lru/mail/social_auth/domain/SocialAuthType;", "getBindType", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class AuthDone extends SocialAuth implements AuthResultSuccess {

            @NotNull
            private final String accessToken;

            @NotNull
            private final SocialAuthType authType;

            @Nullable
            private final String bindType;

            @NotNull
            private final String email;

            @NotNull
            private final String refreshToken;

            @NotNull
            public static final Parcelable.Creator<AuthDone> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AuthDone> {
                @Override // android.os.Parcelable.Creator
                public final AuthDone createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AuthDone(parcel.readString(), parcel.readString(), parcel.readString(), (SocialAuthType) parcel.readParcelable(AuthDone.class.getClassLoader()), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final AuthDone[] newArray(int i10) {
                    return new AuthDone[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AuthDone(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull SocialAuthType authType, @Nullable String str) {
                super(null);
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(authType, "authType");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.authType = authType;
                this.bindType = str;
            }

            public static /* synthetic */ AuthDone copy$default(AuthDone authDone, String str, String str2, String str3, SocialAuthType socialAuthType, String str4, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = authDone.email;
                }
                if ((i10 & 2) != 0) {
                    str2 = authDone.accessToken;
                }
                if ((i10 & 4) != 0) {
                    str3 = authDone.refreshToken;
                }
                if ((i10 & 8) != 0) {
                    socialAuthType = authDone.authType;
                }
                if ((i10 & 16) != 0) {
                    str4 = authDone.bindType;
                }
                String str5 = str4;
                String str6 = str3;
                return authDone.copy(str, str2, str6, socialAuthType, str5);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @NotNull
            /* JADX INFO: renamed from: component4, reason: from getter */
            public final SocialAuthType getAuthType() {
                return this.authType;
            }

            @Nullable
            /* JADX INFO: renamed from: component5, reason: from getter */
            public final String getBindType() {
                return this.bindType;
            }

            @NotNull
            public final AuthDone copy(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull SocialAuthType authType, @Nullable String bindType) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(authType, "authType");
                return new AuthDone(email, accessToken, refreshToken, authType, bindType);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof AuthDone)) {
                    return false;
                }
                AuthDone authDone = (AuthDone) other;
                return Intrinsics.areEqual(this.email, authDone.email) && Intrinsics.areEqual(this.accessToken, authDone.accessToken) && Intrinsics.areEqual(this.refreshToken, authDone.refreshToken) && this.authType == authDone.authType && Intrinsics.areEqual(this.bindType, authDone.bindType);
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final SocialAuthType getAuthType() {
                return this.authType;
            }

            @Nullable
            public final String getBindType() {
                return this.bindType;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            public int hashCode() {
                int iHashCode = ((((((this.email.hashCode() * 31) + this.accessToken.hashCode()) * 31) + this.refreshToken.hashCode()) * 31) + this.authType.hashCode()) * 31;
                String str = this.bindType;
                return iHashCode + (str == null ? 0 : str.hashCode());
            }

            @NotNull
            public String toString() {
                return "AuthDone(email=" + this.email + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", authType=" + this.authType + ", bindType=" + this.bindType + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeParcelable(this.authType, flags);
                dest.writeString(this.bindType);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth$ExternalResult;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$SocialAuth;", "value", "Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;", "<init>", "(Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;)V", "getValue", "()Lru/mail/authorizationsdk/feature/socialauth/presentation/SocialAuthResult;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class ExternalResult extends SocialAuth {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<ExternalResult> CREATOR = new Creator();

            @NotNull
            private final SocialAuthResult value;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<ExternalResult> {
                @Override // android.os.Parcelable.Creator
                public final ExternalResult createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new ExternalResult((SocialAuthResult) parcel.readParcelable(ExternalResult.class.getClassLoader()));
                }

                @Override // android.os.Parcelable.Creator
                public final ExternalResult[] newArray(int i10) {
                    return new ExternalResult[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ExternalResult(@NotNull SocialAuthResult value) {
                super(null);
                Intrinsics.checkNotNullParameter(value, "value");
                this.value = value;
            }

            public static /* synthetic */ ExternalResult copy$default(ExternalResult externalResult, SocialAuthResult socialAuthResult, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    socialAuthResult = externalResult.value;
                }
                return externalResult.copy(socialAuthResult);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final SocialAuthResult getValue() {
                return this.value;
            }

            @NotNull
            public final ExternalResult copy(@NotNull SocialAuthResult value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new ExternalResult(value);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof ExternalResult) && Intrinsics.areEqual(this.value, ((ExternalResult) other).value);
            }

            @NotNull
            public final SocialAuthResult getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.value.hashCode();
            }

            @NotNull
            public String toString() {
                return "ExternalResult(value=" + this.value + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeParcelable(this.value, flags);
            }
        }

        public /* synthetic */ SocialAuth(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private SocialAuth() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0001\u0005¨\u0006\u0006"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$UnblockUser;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", HTTP.CONN_CLOSE, "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$UnblockUser$Close;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class UnblockUser extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$UnblockUser$Close;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$UnblockUser;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Close extends UnblockUser {

            @NotNull
            public static final Close INSTANCE = new Close();

            @NotNull
            public static final Parcelable.Creator<Close> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Close> {
                @Override // android.os.Parcelable.Creator
                public final Close createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Close.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final Close[] newArray(int i10) {
                    return new Close[i10];
                }
            }

            private Close() {
                super(null);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Close);
            }

            public int hashCode() {
                return 886340371;
            }

            @NotNull
            public String toString() {
                return HTTP.CONN_CLOSE;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        public /* synthetic */ UnblockUser(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private UnblockUser() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "StartBinding", "LoginWithAnotherWay", QrLoginAnalytics.Actions.Back, "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin$Back;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin$LoginWithAnotherWay;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin$StartBinding;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class VkBindInLogin extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin$Back;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Back extends VkBindInLogin {

            @NotNull
            public static final Back INSTANCE = new Back();

            @NotNull
            public static final Parcelable.Creator<Back> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Back> {
                @Override // android.os.Parcelable.Creator
                public final Back createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return Back.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final Back[] newArray(int i10) {
                    return new Back[i10];
                }
            }

            private Back() {
                super(null);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Back);
            }

            public int hashCode() {
                return 497335801;
            }

            @NotNull
            public String toString() {
                return QrLoginAnalytics.Actions.Back;
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin$LoginWithAnotherWay;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin;", "login", "", "<init>", "(Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class LoginWithAnotherWay extends VkBindInLogin {

            @NotNull
            private final String login;

            @NotNull
            public static final Parcelable.Creator<LoginWithAnotherWay> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<LoginWithAnotherWay> {
                @Override // android.os.Parcelable.Creator
                public final LoginWithAnotherWay createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new LoginWithAnotherWay(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final LoginWithAnotherWay[] newArray(int i10) {
                    return new LoginWithAnotherWay[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public LoginWithAnotherWay(@NotNull String login) {
                super(null);
                Intrinsics.checkNotNullParameter(login, "login");
                this.login = login;
            }

            public static /* synthetic */ LoginWithAnotherWay copy$default(LoginWithAnotherWay loginWithAnotherWay, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = loginWithAnotherWay.login;
                }
                return loginWithAnotherWay.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final LoginWithAnotherWay copy(@NotNull String login) {
                Intrinsics.checkNotNullParameter(login, "login");
                return new LoginWithAnotherWay(login);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof LoginWithAnotherWay) && Intrinsics.areEqual(this.login, ((LoginWithAnotherWay) other).login);
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            public int hashCode() {
                return this.login.hashCode();
            }

            @NotNull
            public String toString() {
                return "LoginWithAnotherWay(login=" + this.login + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.login);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u000bJ\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u000bHÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin$StartBinding;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkBindInLogin;", "login", "", "<init>", "(Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class StartBinding extends VkBindInLogin {

            @NotNull
            private final String login;

            @NotNull
            public static final Parcelable.Creator<StartBinding> CREATOR = new Creator();
            public static final int $stable = 8;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<StartBinding> {
                @Override // android.os.Parcelable.Creator
                public final StartBinding createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new StartBinding(parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final StartBinding[] newArray(int i10) {
                    return new StartBinding[i10];
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public StartBinding(@NotNull String login) {
                super(null);
                Intrinsics.checkNotNullParameter(login, "login");
                this.login = login;
            }

            public static /* synthetic */ StartBinding copy$default(StartBinding startBinding, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = startBinding.login;
                }
                return startBinding.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final StartBinding copy(@NotNull String login) {
                Intrinsics.checkNotNullParameter(login, "login");
                return new StartBinding(login);
            }

            @Override // android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof StartBinding) && Intrinsics.areEqual(this.login, ((StartBinding) other).login);
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            public int hashCode() {
                return this.login.hashCode();
            }

            @NotNull
            public String toString() {
                return "StartBinding(login=" + this.login + ")";
            }

            @Override // android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.login);
            }
        }

        public /* synthetic */ VkBindInLogin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private VkBindInLogin() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$VkPassword;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;", "<init>", "(Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/vkpassword/presentation/VkPasswordResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class VkPassword extends AuthResult {
        public static final int $stable = 0;

        @NotNull
        public static final Parcelable.Creator<VkPassword> CREATOR = new Creator();

        @NotNull
        private final VkPasswordResult result;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkPassword> {
            @Override // android.os.Parcelable.Creator
            public final VkPassword createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new VkPassword((VkPasswordResult) parcel.readParcelable(VkPassword.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final VkPassword[] newArray(int i10) {
                return new VkPassword[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public VkPassword(@NotNull VkPasswordResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final VkPasswordResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "AuthDone", "Other", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Yahoo extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<Yahoo> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0018"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo$AuthDone;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "yahooAccessToken", "yahooRefreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getYahooAccessToken", "getYahooRefreshToken", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AuthDone extends Yahoo implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<AuthDone> CREATOR = new Creator();

            @NotNull
            private final String accessToken;

            @NotNull
            private final String email;

            @NotNull
            private final String refreshToken;

            @NotNull
            private final String yahooAccessToken;

            @NotNull
            private final String yahooRefreshToken;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AuthDone> {
                @Override // android.os.Parcelable.Creator
                public final AuthDone createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AuthDone(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final AuthDone[] newArray(int i10) {
                    return new AuthDone[i10];
                }
            }

            public AuthDone(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String yahooAccessToken, @NotNull String yahooRefreshToken) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(yahooAccessToken, "yahooAccessToken");
                Intrinsics.checkNotNullParameter(yahooRefreshToken, "yahooRefreshToken");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.yahooAccessToken = yahooAccessToken;
                this.yahooRefreshToken = yahooRefreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yahoo, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @NotNull
            public final String getYahooAccessToken() {
                return this.yahooAccessToken;
            }

            @NotNull
            public final String getYahooRefreshToken() {
                return this.yahooRefreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yahoo, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeString(this.yahooAccessToken);
                dest.writeString(this.yahooRefreshToken);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Yahoo> {
            @Override // android.os.Parcelable.Creator
            public final Yahoo createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new Yahoo();
            }

            @Override // android.os.Parcelable.Creator
            public final Yahoo[] newArray(int i10) {
                return new Yahoo[i10];
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo$Other;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yahoo;", "result", "Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;", "<init>", "(Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Other extends Yahoo {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<Other> CREATOR = new Creator();

            @NotNull
            private final YahooResult result;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<Other> {
                @Override // android.os.Parcelable.Creator
                public final Other createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new Other((YahooResult) parcel.readParcelable(Other.class.getClassLoader()));
                }

                @Override // android.os.Parcelable.Creator
                public final Other[] newArray(int i10) {
                    return new Other[i10];
                }
            }

            public Other(@NotNull YahooResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yahoo, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final YahooResult getResult() {
                return this.result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yahoo, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeParcelable(this.result, flags);
            }
        }

        public Yahoo() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "AuthDoneYandex", "OtherYandex", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Yandex extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<Yandex> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\f¨\u0006\u0018"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex$AuthDoneYandex;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "yandexAccessToken", "yandexRefreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getYandexAccessToken", "getYandexRefreshToken", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AuthDoneYandex extends Yandex implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<AuthDoneYandex> CREATOR = new Creator();

            @NotNull
            private final String accessToken;

            @NotNull
            private final String email;

            @NotNull
            private final String refreshToken;

            @NotNull
            private final String yandexAccessToken;

            @NotNull
            private final String yandexRefreshToken;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<AuthDoneYandex> {
                @Override // android.os.Parcelable.Creator
                public final AuthDoneYandex createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new AuthDoneYandex(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
                }

                @Override // android.os.Parcelable.Creator
                public final AuthDoneYandex[] newArray(int i10) {
                    return new AuthDoneYandex[i10];
                }
            }

            public AuthDoneYandex(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String yandexAccessToken, @NotNull String yandexRefreshToken) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(yandexAccessToken, "yandexAccessToken");
                Intrinsics.checkNotNullParameter(yandexRefreshToken, "yandexRefreshToken");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.yandexAccessToken = yandexAccessToken;
                this.yandexRefreshToken = yandexRefreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yandex, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @NotNull
            public final String getYandexAccessToken() {
                return this.yandexAccessToken;
            }

            @NotNull
            public final String getYandexRefreshToken() {
                return this.yandexRefreshToken;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yandex, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeString(this.yandexAccessToken);
                dest.writeString(this.yandexRefreshToken);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<Yandex> {
            @Override // android.os.Parcelable.Creator
            public final Yandex createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new Yandex();
            }

            @Override // android.os.Parcelable.Creator
            public final Yandex[] newArray(int i10) {
                return new Yandex[i10];
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex$OtherYandex;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$Yandex;", "result", "Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;", "<init>", "(Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OtherYandex extends Yandex {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<OtherYandex> CREATOR = new Creator();

            @NotNull
            private final YandexResult result;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<OtherYandex> {
                @Override // android.os.Parcelable.Creator
                public final OtherYandex createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new OtherYandex((YandexResult) parcel.readParcelable(OtherYandex.class.getClassLoader()));
                }

                @Override // android.os.Parcelable.Creator
                public final OtherYandex[] newArray(int i10) {
                    return new OtherYandex[i10];
                }
            }

            public OtherYandex(@NotNull YandexResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                this.result = result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yandex, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            @NotNull
            public final YandexResult getResult() {
                return this.result;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.Yandex, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeParcelable(this.result, flags);
            }
        }

        public Yandex() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$YandexHelp;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "result", "Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;", "<init>", "(Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;)V", "getResult", "()Lru/mail/authorizationsdk/feature/yandexhelp/YandexHelpResult;", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class YandexHelp extends AuthResult {

        @NotNull
        private final YandexHelpResult result;

        @NotNull
        public static final Parcelable.Creator<YandexHelp> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<YandexHelp> {
            @Override // android.os.Parcelable.Creator
            public final YandexHelp createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new YandexHelp((YandexHelpResult) parcel.readParcelable(YandexHelp.class.getClassLoader()));
            }

            @Override // android.os.Parcelable.Creator
            public final YandexHelp[] newArray(int i10) {
                return new YandexHelp[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public YandexHelp(@NotNull YandexHelpResult result) {
            super(null);
            Intrinsics.checkNotNullParameter(result, "result");
            this.result = result;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final YandexHelpResult getResult() {
            return this.result;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeParcelable(this.result, flags);
        }
    }

    public /* synthetic */ AuthResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AuthResult() {
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Parcelize
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0010H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$OneTimeCodeSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", "bindType", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getBindType", "getAccessToken", "getRefreshToken", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class OneTimeCodeSuccess extends AuthResult implements AuthResultSuccess {

        @NotNull
        private final String accessToken;

        @Nullable
        private final String bindType;

        @NotNull
        private final String email;

        @NotNull
        private final String refreshToken;

        @NotNull
        public static final Parcelable.Creator<OneTimeCodeSuccess> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<OneTimeCodeSuccess> {
            @Override // android.os.Parcelable.Creator
            public final OneTimeCodeSuccess createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new OneTimeCodeSuccess(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final OneTimeCodeSuccess[] newArray(int i10) {
                return new OneTimeCodeSuccess[i10];
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OneTimeCodeSuccess(@NotNull String email, @Nullable String str, @NotNull String accessToken, @NotNull String refreshToken) {
            super(null);
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.email = email;
            this.bindType = str;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @Nullable
        public final String getBindType() {
            return this.bindType;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.bindType);
            dest.writeString(this.accessToken);
            dest.writeString(this.refreshToken);
        }

        public /* synthetic */ OneTimeCodeSuccess(String str, String str2, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? null : str2, str3, str4);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\r"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult;", "<init>", "()V", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "PasswordAuthSuccess", "BackClick", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class PasswordAuth extends AuthResult {

        @NotNull
        public static final Parcelable.Creator<PasswordAuth> CREATOR = new Creator();
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0013\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tHÖ\u0003J\t\u0010\n\u001a\u00020\u0005HÖ\u0001J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth$BackClick;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth;", "<init>", "()V", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class BackClick extends PasswordAuth {
            public static final int $stable = 0;

            @NotNull
            public static final BackClick INSTANCE = new BackClick();

            @NotNull
            public static final Parcelable.Creator<BackClick> CREATOR = new Creator();

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<BackClick> {
                @Override // android.os.Parcelable.Creator
                public final BackClick createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    parcel.readInt();
                    return BackClick.INSTANCE;
                }

                @Override // android.os.Parcelable.Creator
                public final BackClick[] newArray(int i10) {
                    return new BackClick[i10];
                }
            }

            private BackClick() {
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.PasswordAuth, android.os.Parcelable
            public final int describeContents() {
                return 0;
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof BackClick);
            }

            public int hashCode() {
                return 382672204;
            }

            @NotNull
            public String toString() {
                return "BackClick";
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.PasswordAuth, android.os.Parcelable
            public final void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeInt(1);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<PasswordAuth> {
            @Override // android.os.Parcelable.Creator
            public final PasswordAuth createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                parcel.readInt();
                return new PasswordAuth();
            }

            @Override // android.os.Parcelable.Creator
            public final PasswordAuth[] newArray(int i10) {
                return new PasswordAuth[i10];
            }
        }

        public PasswordAuth() {
            super(null);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeInt(1);
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Parcelize
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002BG\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0017H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0015¨\u0006\u001d"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth$PasswordAuthSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResult$PasswordAuth;", "Lru/mail/authorizationsdk/feature/authactivity/result/AuthResultSuccess;", "email", "", "password", "tsaCookie", "bindType", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "isAutologin", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getTsaCookie", "getBindType", "getAccessToken", "getRefreshToken", "()Z", "describeContents", "", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static class PasswordAuthSuccess extends PasswordAuth implements AuthResultSuccess {
            public static final int $stable = 0;

            @NotNull
            public static final Parcelable.Creator<PasswordAuthSuccess> CREATOR = new Creator();

            @NotNull
            private final String accessToken;

            @Nullable
            private final String bindType;

            @NotNull
            private final String email;
            private final boolean isAutologin;

            @NotNull
            private final String password;

            @NotNull
            private final String refreshToken;

            @Nullable
            private final String tsaCookie;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            public static final class Creator implements Parcelable.Creator<PasswordAuthSuccess> {
                @Override // android.os.Parcelable.Creator
                public final PasswordAuthSuccess createFromParcel(Parcel parcel) {
                    Intrinsics.checkNotNullParameter(parcel, "parcel");
                    return new PasswordAuthSuccess(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0);
                }

                @Override // android.os.Parcelable.Creator
                public final PasswordAuthSuccess[] newArray(int i10) {
                    return new PasswordAuthSuccess[i10];
                }
            }

            public PasswordAuthSuccess(@NotNull String email, @NotNull String password, @Nullable String str, @Nullable String str2, @NotNull String accessToken, @NotNull String refreshToken, boolean z10) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                this.email = email;
                this.password = password;
                this.tsaCookie = str;
                this.bindType = str2;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.isAutologin = z10;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.PasswordAuth, android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @Nullable
            public final String getBindType() {
                return this.bindType;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getPassword() {
                return this.password;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @Nullable
            public final String getTsaCookie() {
                return this.tsaCookie;
            }

            /* JADX INFO: renamed from: isAutologin, reason: from getter */
            public final boolean getIsAutologin() {
                return this.isAutologin;
            }

            @Override // ru.mail.authorizationsdk.feature.authactivity.result.AuthResult.PasswordAuth, android.os.Parcelable
            public void writeToParcel(@NotNull Parcel dest, int flags) {
                Intrinsics.checkNotNullParameter(dest, "dest");
                dest.writeString(this.email);
                dest.writeString(this.password);
                dest.writeString(this.tsaCookie);
                dest.writeString(this.bindType);
                dest.writeString(this.accessToken);
                dest.writeString(this.refreshToken);
                dest.writeInt(this.isAutologin ? 1 : 0);
            }

            public /* synthetic */ PasswordAuthSuccess(String str, String str2, String str3, String str4, String str5, String str6, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, (i10 & 4) != 0 ? null : str3, str4, str5, str6, (i10 & 64) != 0 ? false : z10);
            }
        }
    }
}
