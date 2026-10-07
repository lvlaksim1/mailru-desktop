package ru.mail.auth.loginactivity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.StringRes;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import org.apache.http.protocol.HTTP;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.QrLoginAnalytics;
import ru.mail.auth.Authenticator;
import ru.mail.auth.EmailServiceResources;
import ru.mail.auth.MailLoginFragment;
import ru.mail.auth.webview.TokensHolder;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.mail.authorizationsdk.feature.google.common.presentation.GoogleResult;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookResult;
import ru.mail.authorizationsdk.feature.registration.presentation.model.RegFlow;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooResult;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexResult;
import ru.mail.authorizesdk.util.mvi.navigation.ViewEvent;
import ru.mail.data.entities.Collector;
import ru.mail.social_auth.domain.AuthResult;
import ru.mail.social_auth.domain.SocialAuthType;
import ru.mail.ui.dialogs.MarkSpamDialog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:!\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"\u0082\u0001!#$%&'()*+,-./0123456789:;<=>?@ABC¨\u0006DÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents;", "Lru/mail/authorizesdk/util/mvi/navigation/ViewEvent;", "StartGoogleAuthScreen", "StartLoginOAuthWebView", "StartLoginScreen", "StartXmailMigrationFromLogin", "StartDefaultLoginScreenRequired", "StartLoginScreenWithXmail", "StartRegistration", "StartRegistrationNewExternalAuth", "StartVKAnotherLogin", "ShowActionBar", "LudwigEvents", "RestorePasswordEvents", "RestorePasswordComposeEvents", "OneTimeCodeSuccessAuth", "OneTimeCodeEvents", "YahooEvents", "YandexEvents", "OutlookEvents", "VkSilentSuccess", "Login", "ImapLocalSuccess", "OAuthImapLocalSuccess", "PasswordAuth", "RestoreWithoutPasswordSuccess", "AfterRegAuth", "GoogleEvents", "SecondStepEvents", "SSOEvents", "VkPasswordEvents", "CustomServerEvents", "Error", "SocialAuthEvent", "VkBindInLogin", "Lru/mail/auth/loginactivity/LoginActivityEvents$AfterRegAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$CustomServerEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$ImapLocalSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Login;", "Lru/mail/auth/loginactivity/LoginActivityEvents$LudwigEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OAuthImapLocalSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeSuccessAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OutlookEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$PasswordAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestoreWithoutPasswordSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$ShowActionBar;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartDefaultLoginScreenRequired;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartGoogleAuthScreen;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartLoginOAuthWebView;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartLoginScreen;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartLoginScreenWithXmail;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartRegistration;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartRegistrationNewExternalAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartVKAnotherLogin;", "Lru/mail/auth/loginactivity/LoginActivityEvents$StartXmailMigrationFromLogin;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkSilentSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YandexEvents;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LoginActivityEvents extends ViewEvent {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001:\b\u0002\u0003\u0004\u0005\u0006\u0007\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "CommonError", GoogleErrorDescriptions.OAUTH_IMAP_FAILED, "NeedDoRegistration", "ImapRedirect", "NeedYandexOauth", "NeedYahooOauth", "NeedOutlookOauth", "NeedGoogleOauth", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Error extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$CommonError;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class CommonError implements Error {

            @NotNull
            private final String message;

            public CommonError(@NotNull String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
            }

            public static /* synthetic */ CommonError copy$default(CommonError commonError, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = commonError.message;
                }
                return commonError.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getMessage() {
                return this.message;
            }

            @NotNull
            public final CommonError copy(@NotNull String message) {
                Intrinsics.checkNotNullParameter(message, "message");
                return new CommonError(message);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof CommonError) && Intrinsics.areEqual(this.message, ((CommonError) other).message);
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }

            public int hashCode() {
                return this.message.hashCode();
            }

            @NotNull
            public String toString() {
                return "CommonError(message=" + this.message + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$ImapRedirect;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "password", "settings", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getSettings", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class ImapRedirect implements Error {

            @NotNull
            private final String email;

            @NotNull
            private final String password;

            @NotNull
            private final String settings;

            public ImapRedirect(@NotNull String email, @NotNull String password, @NotNull String settings) {
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
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u001a"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$NeedDoRegistration;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "password", "regId", "isNeedCaptcha", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getRegId", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedDoRegistration implements Error {

            @NotNull
            private final String email;
            private final boolean isNeedCaptcha;

            @NotNull
            private final String password;

            @NotNull
            private final String regId;

            public NeedDoRegistration(@NotNull String email, @NotNull String password, @NotNull String regId, boolean z10) {
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
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$NeedGoogleOauth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedGoogleOauth implements Error {

            @NotNull
            private final String email;

            public NeedGoogleOauth(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ NeedGoogleOauth copy$default(NeedGoogleOauth needGoogleOauth, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = needGoogleOauth.email;
                }
                return needGoogleOauth.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final NeedGoogleOauth copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new NeedGoogleOauth(email);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NeedGoogleOauth) && Intrinsics.areEqual(this.email, ((NeedGoogleOauth) other).email);
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
                return "NeedGoogleOauth(email=" + this.email + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$NeedOutlookOauth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedOutlookOauth implements Error {

            @NotNull
            private final String email;

            public NeedOutlookOauth(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ NeedOutlookOauth copy$default(NeedOutlookOauth needOutlookOauth, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = needOutlookOauth.email;
                }
                return needOutlookOauth.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final NeedOutlookOauth copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new NeedOutlookOauth(email);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NeedOutlookOauth) && Intrinsics.areEqual(this.email, ((NeedOutlookOauth) other).email);
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
                return "NeedOutlookOauth(email=" + this.email + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$NeedYahooOauth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedYahooOauth implements Error {

            @NotNull
            private final String email;

            public NeedYahooOauth(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ NeedYahooOauth copy$default(NeedYahooOauth needYahooOauth, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = needYahooOauth.email;
                }
                return needYahooOauth.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final NeedYahooOauth copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new NeedYahooOauth(email);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NeedYahooOauth) && Intrinsics.areEqual(this.email, ((NeedYahooOauth) other).email);
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
                return "NeedYahooOauth(email=" + this.email + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$NeedYandexOauth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedYandexOauth implements Error {

            @NotNull
            private final String email;

            public NeedYandexOauth(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ NeedYandexOauth copy$default(NeedYandexOauth needYandexOauth, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = needYandexOauth.email;
                }
                return needYandexOauth.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final NeedYandexOauth copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new NeedYandexOauth(email);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NeedYandexOauth) && Intrinsics.areEqual(this.email, ((NeedYandexOauth) other).email);
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
                return "NeedYandexOauth(email=" + this.email + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Error$OauthImapFailed;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class OauthImapFailed implements Error {

            @NotNull
            private final String email;

            public OauthImapFailed(@NotNull String email) {
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
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Login;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "BackClick", "NeedRegistration", "AlreadyLoggedIn", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Login extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Login$AlreadyLoggedIn;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Login;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class AlreadyLoggedIn implements Login {

            @NotNull
            private final String email;

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
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Login$BackClick;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Login;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class BackClick implements Login {

            @NotNull
            public static final BackClick INSTANCE = new BackClick();

            private BackClick() {
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof BackClick);
            }

            public int hashCode() {
                return -147500593;
            }

            @NotNull
            public String toString() {
                return "BackClick";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$Login$NeedRegistration;", "Lru/mail/auth/loginactivity/LoginActivityEvents$Login;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NeedRegistration implements Login {

            @NotNull
            public static final NeedRegistration INSTANCE = new NeedRegistration();

            private NeedRegistration() {
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof NeedRegistration);
            }

            public int hashCode() {
                return -612580415;
            }

            @NotNull
            public String toString() {
                return "NeedRegistration";
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$LudwigEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "LudwigCaptchaSuccess", "LudwigCaptchaError", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface LudwigEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$LudwigEvents$LudwigCaptchaError;", "Lru/mail/auth/loginactivity/LoginActivityEvents$LudwigEvents;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class LudwigCaptchaError implements LudwigEvents {

            @NotNull
            private final String error;

            public LudwigCaptchaError(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$LudwigEvents$LudwigCaptchaSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents$LudwigEvents;", "ludwigToken", "", "login", "password", "type", "Lru/mail/auth/Authenticator$Type;", "extraData", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lru/mail/auth/Authenticator$Type;Landroid/os/Bundle;)V", "getLudwigToken", "()Ljava/lang/String;", "getLogin", "getPassword", "getType", "()Lru/mail/auth/Authenticator$Type;", "getExtraData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class LudwigCaptchaSuccess implements LudwigEvents {

            @Nullable
            private final Bundle extraData;

            @Nullable
            private final String login;

            @NotNull
            private final String ludwigToken;

            @Nullable
            private final String password;

            @NotNull
            private final Authenticator.Type type;

            public LudwigCaptchaSuccess(@NotNull String ludwigToken, @Nullable String str, @Nullable String str2, @NotNull Authenticator.Type type, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
                Intrinsics.checkNotNullParameter(type, "type");
                this.ludwigToken = ludwigToken;
                this.login = str;
                this.password = str2;
                this.type = type;
                this.extraData = bundle;
            }

            @Nullable
            public final Bundle getExtraData() {
                return this.extraData;
            }

            @Nullable
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final String getLudwigToken() {
                return this.ludwigToken;
            }

            @Nullable
            public final String getPassword() {
                return this.password;
            }

            @NotNull
            public final Authenticator.Type getType() {
                return this.type;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "SwitchToPassword", "Error", "OnClose", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OneTimeCodeEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements OneTimeCodeEvents {

            @NotNull
            private final String error;

            public Error(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents$OnClose;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OnClose implements OneTimeCodeEvents {

            @NotNull
            public static final OnClose INSTANCE = new OnClose();

            private OnClose() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006\u0012\"\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR-\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents;", "email", "", "queryParams", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "actCookie", "<init>", "(Ljava/lang/String;Ljava/util/HashMap;Ljava/util/HashMap;)V", "getEmail", "()Ljava/lang/String;", "getQueryParams", "()Ljava/util/HashMap;", "getActCookie", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements OneTimeCodeEvents {

            @NotNull
            private final HashMap<String, String> actCookie;

            @NotNull
            private final String email;

            @NotNull
            private final HashMap<String, String> queryParams;

            public Success(@NotNull String email, @NotNull HashMap<String, String> queryParams, @NotNull HashMap<String, String> actCookie) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(queryParams, "queryParams");
                Intrinsics.checkNotNullParameter(actCookie, "actCookie");
                this.email = email;
                this.queryParams = queryParams;
                this.actCookie = actCookie;
            }

            @NotNull
            public final HashMap<String, String> getActCookie() {
                return this.actCookie;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final HashMap<String, String> getQueryParams() {
                return this.queryParams;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents$SwitchToPassword;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SwitchToPassword implements OneTimeCodeEvents {

            @NotNull
            private final String email;

            public SwitchToPassword(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OutlookEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "Error", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OutlookEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OutlookEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OutlookEvents;", "message", "", "email", "code", "Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult$OutlookErrorCodes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult$OutlookErrorCodes;)V", "getMessage", "()Ljava/lang/String;", "getEmail", "getCode", "()Lru/mail/authorizationsdk/feature/outlook/presentation/OutlookResult$OutlookErrorCodes;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements OutlookEvents {

            @Nullable
            private final OutlookResult.OutlookErrorCodes code;

            @Nullable
            private final String email;

            @NotNull
            private final String message;

            public Error(@NotNull String message, @Nullable String str, @Nullable OutlookResult.OutlookErrorCodes outlookErrorCodes) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.email = str;
                this.code = outlookErrorCodes;
            }

            @Nullable
            public final OutlookResult.OutlookErrorCodes getCode() {
                return this.code;
            }

            @Nullable
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OutlookEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$OutlookEvents;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "outlookAccessToken", "outlookRefreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getOutlookAccessToken", "getOutlookRefreshToken", "getAccountType", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements OutlookEvents {

            @NotNull
            private final String accessToken;

            @NotNull
            private final String accountType;

            @NotNull
            private final Bundle data;

            @NotNull
            private final String email;

            @NotNull
            private final String outlookAccessToken;

            @NotNull
            private final String outlookRefreshToken;

            @NotNull
            private final String refreshToken;

            public Success(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String outlookAccessToken, @NotNull String outlookRefreshToken, @NotNull String accountType, @NotNull Bundle data) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(outlookAccessToken, "outlookAccessToken");
                Intrinsics.checkNotNullParameter(outlookRefreshToken, "outlookRefreshToken");
                Intrinsics.checkNotNullParameter(accountType, "accountType");
                Intrinsics.checkNotNullParameter(data, "data");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.outlookAccessToken = outlookAccessToken;
                this.outlookRefreshToken = outlookRefreshToken;
                this.accountType = accountType;
                this.data = data;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getAccountType() {
                return this.accountType;
            }

            @NotNull
            public final Bundle getData() {
                return this.data;
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
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "GoToRestoreVkid", "Closed", "Error", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface RestorePasswordComposeEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents$Closed;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents;", MarkSpamDialog.ANALYTICS_TAG, "", "<init>", "(Ljava/lang/String;)V", "getAnalyticsTag", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Closed implements RestorePasswordComposeEvents {

            @NotNull
            private final String analyticsTag;

            public Closed(@NotNull String analyticsTag) {
                Intrinsics.checkNotNullParameter(analyticsTag, "analyticsTag");
                this.analyticsTag = analyticsTag;
            }

            @NotNull
            public final String getAnalyticsTag() {
                return this.analyticsTag;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements RestorePasswordComposeEvents {

            @NotNull
            private final String error;

            public Error(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents$GoToRestoreVkid;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class GoToRestoreVkid implements RestorePasswordComposeEvents {

            @NotNull
            private final String email;

            public GoToRestoreVkid(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordComposeEvents;", "login", "", "queryParams", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "<init>", "(Ljava/lang/String;Ljava/util/HashMap;)V", "getLogin", "()Ljava/lang/String;", "getQueryParams", "()Ljava/util/HashMap;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements RestorePasswordComposeEvents {

            @NotNull
            private final String login;

            @NotNull
            private final HashMap<String, String> queryParams;

            public Success(@NotNull String login, @NotNull HashMap<String, String> queryParams) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(queryParams, "queryParams");
                this.login = login;
                this.queryParams = queryParams;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final HashMap<String, String> getQueryParams() {
                return this.queryParams;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "RestorePasswordSuccess", "GoToRestoreVkid", "RestorePasswordClosed", "RestorePasswordError", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface RestorePasswordEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents$GoToRestoreVkid;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class GoToRestoreVkid implements RestorePasswordEvents {

            @NotNull
            private final String email;

            public GoToRestoreVkid(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents$RestorePasswordClosed;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents;", MarkSpamDialog.ANALYTICS_TAG, "", "<init>", "(Ljava/lang/String;)V", "getAnalyticsTag", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RestorePasswordClosed implements RestorePasswordEvents {

            @NotNull
            private final String analyticsTag;

            public RestorePasswordClosed(@NotNull String analyticsTag) {
                Intrinsics.checkNotNullParameter(analyticsTag, "analyticsTag");
                this.analyticsTag = analyticsTag;
            }

            @NotNull
            public final String getAnalyticsTag() {
                return this.analyticsTag;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents$RestorePasswordError;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents;", "errorRes", "", "<init>", "(I)V", "getErrorRes", "()I", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RestorePasswordError implements RestorePasswordEvents {
            private final int errorRes;

            public RestorePasswordError(@StringRes int i10) {
                this.errorRes = i10;
            }

            public final int getErrorRes() {
                return this.errorRes;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents$RestorePasswordSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents$RestorePasswordEvents;", "login", "", "extraData", "Landroid/os/Bundle;", "isRebind", "", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;Z)V", "getLogin", "()Ljava/lang/String;", "getExtraData", "()Landroid/os/Bundle;", "()Z", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class RestorePasswordSuccess implements RestorePasswordEvents {

            @NotNull
            private final Bundle extraData;
            private final boolean isRebind;

            @NotNull
            private final String login;

            public RestorePasswordSuccess(@NotNull String login, @NotNull Bundle extraData, boolean z10) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(extraData, "extraData");
                this.login = login;
                this.extraData = extraData;
                this.isRebind = z10;
            }

            @NotNull
            public final Bundle getExtraData() {
                return this.extraData;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            /* JADX INFO: renamed from: isRebind, reason: from getter */
            public final boolean getIsRebind() {
                return this.isRebind;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "SwitchToRestoreVkId", "Error", "BackClick", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$BackClick;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$SwitchToRestoreVkId;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SSOEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$BackClick;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class BackClick implements SSOEvents {

            @NotNull
            public static final BackClick INSTANCE = new BackClick();

            private BackClick() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements SSOEvents {

            @NotNull
            private final String error;

            public Error(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents;", "login", "", "agToken", "oldBundle", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getLogin", "()Ljava/lang/String;", "getAgToken", "getOldBundle", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements SSOEvents {

            @NotNull
            private final String agToken;

            @NotNull
            private final String login;

            @Nullable
            private final Bundle oldBundle;

            public Success(@NotNull String login, @NotNull String agToken, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(agToken, "agToken");
                this.login = login;
                this.agToken = agToken;
                this.oldBundle = bundle;
            }

            @NotNull
            public final String getAgToken() {
                return this.agToken;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @Nullable
            public final Bundle getOldBundle() {
                return this.oldBundle;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents$SwitchToRestoreVkId;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SSOEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SwitchToRestoreVkId implements SSOEvents {

            @NotNull
            private final String email;

            public SwitchToRestoreVkId(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001:\u0005\u0002\u0003\u0004\u0005\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "NeedEndActivity", "SwitchToRecovery", "SwitchToPassword", "Error", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SecondStepEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements SecondStepEvents {

            @NotNull
            private final String error;

            public Error(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents$NeedEndActivity;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NeedEndActivity implements SecondStepEvents {
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B[\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005j\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003`\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "login", "", "queryParams", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "tsaCookie", "xmailLogin", "xmailMigrationFrom", "oldBundle", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/util/HashMap;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getLogin", "()Ljava/lang/String;", "getQueryParams", "()Ljava/util/HashMap;", "getTsaCookie", "getXmailLogin", "getXmailMigrationFrom", "getOldBundle", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements SecondStepEvents {

            @NotNull
            private final String login;

            @Nullable
            private final Bundle oldBundle;

            @NotNull
            private final HashMap<String, String> queryParams;

            @Nullable
            private final String tsaCookie;

            @Nullable
            private final String xmailLogin;

            @Nullable
            private final String xmailMigrationFrom;

            public Success(@NotNull String login, @NotNull HashMap<String, String> queryParams, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(queryParams, "queryParams");
                this.login = login;
                this.queryParams = queryParams;
                this.tsaCookie = str;
                this.xmailLogin = str2;
                this.xmailMigrationFrom = str3;
                this.oldBundle = bundle;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @Nullable
            public final Bundle getOldBundle() {
                return this.oldBundle;
            }

            @NotNull
            public final HashMap<String, String> getQueryParams() {
                return this.queryParams;
            }

            @Nullable
            public final String getTsaCookie() {
                return this.tsaCookie;
            }

            @Nullable
            public final String getXmailLogin() {
                return this.xmailLogin;
            }

            @Nullable
            public final String getXmailMigrationFrom() {
                return this.xmailMigrationFrom;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents$SwitchToPassword;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SwitchToPassword implements SecondStepEvents {

            @NotNull
            private final String email;

            public SwitchToPassword(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents$SwitchToRecovery;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SecondStepEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class SwitchToRecovery implements SecondStepEvents {

            @NotNull
            private final String email;

            public SwitchToRecovery(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$ShowActionBar;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ShowActionBar implements LoginActivityEvents {

        @NotNull
        public static final ShowActionBar INSTANCE = new ShowActionBar();

        private ShowActionBar() {
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof ShowActionBar);
        }

        public int hashCode() {
            return -1165463149;
        }

        @NotNull
        public String toString() {
            return "ShowActionBar";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\t\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u0082\u0001\t\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Result", "Success", "Error", HTTP.CONN_CLOSE, "StartRegistration", "PasswordSuccessfullyChanged", "NotAuthorizedErrorDuringPasswordChange", "OpenMailRestore", "OpenRestoreVkidOldAuth", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Close;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$NotAuthorizedErrorDuringPasswordChange;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$OpenMailRestore;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$OpenRestoreVkidOldAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$PasswordSuccessfullyChanged;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Result;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$StartRegistration;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Success;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SocialAuthEvent extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Close;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "isAutoLogin", "", "<init>", "(Z)V", "()Z", "component1", "copy", "equals", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Close implements SocialAuthEvent {
            private final boolean isAutoLogin;

            public Close(boolean z10) {
                this.isAutoLogin = z10;
            }

            public static /* synthetic */ Close copy$default(Close close, boolean z10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    z10 = close.isAutoLogin;
                }
                return close.copy(z10);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final boolean getIsAutoLogin() {
                return this.isAutoLogin;
            }

            @NotNull
            public final Close copy(boolean isAutoLogin) {
                return new Close(isAutoLogin);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Close) && this.isAutoLogin == ((Close) other).isAutoLogin;
            }

            public int hashCode() {
                return Boolean.hashCode(this.isAutoLogin);
            }

            public final boolean isAutoLogin() {
                return this.isAutoLogin;
            }

            @NotNull
            public String toString() {
                return "Close(isAutoLogin=" + this.isAutoLogin + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "errorCode", "", "errorMsg", "", "errorType", "Lru/mail/social_auth/domain/SocialAuthEvent$ErrorType;", "<init>", "(ILjava/lang/String;Lru/mail/social_auth/domain/SocialAuthEvent$ErrorType;)V", "getErrorCode", "()I", "getErrorMsg", "()Ljava/lang/String;", "getErrorType", "()Lru/mail/social_auth/domain/SocialAuthEvent$ErrorType;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Error implements SocialAuthEvent {
            private final int errorCode;

            @Nullable
            private final String errorMsg;

            @NotNull
            private final ru.mail.social_auth.domain.SocialAuthEvent.ErrorType errorType;

            public Error(int i10, @Nullable String str, @NotNull ru.mail.social_auth.domain.SocialAuthEvent.ErrorType errorType) {
                Intrinsics.checkNotNullParameter(errorType, "errorType");
                this.errorCode = i10;
                this.errorMsg = str;
                this.errorType = errorType;
            }

            public static /* synthetic */ Error copy$default(Error error, int i10, String str, ru.mail.social_auth.domain.SocialAuthEvent.ErrorType errorType, int i11, Object obj) {
                if ((i11 & 1) != 0) {
                    i10 = error.errorCode;
                }
                if ((i11 & 2) != 0) {
                    str = error.errorMsg;
                }
                if ((i11 & 4) != 0) {
                    errorType = error.errorType;
                }
                return error.copy(i10, str, errorType);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getErrorCode() {
                return this.errorCode;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getErrorMsg() {
                return this.errorMsg;
            }

            @NotNull
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final ru.mail.social_auth.domain.SocialAuthEvent.ErrorType getErrorType() {
                return this.errorType;
            }

            @NotNull
            public final Error copy(int errorCode, @Nullable String errorMsg, @NotNull ru.mail.social_auth.domain.SocialAuthEvent.ErrorType errorType) {
                Intrinsics.checkNotNullParameter(errorType, "errorType");
                return new Error(errorCode, errorMsg, errorType);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Error)) {
                    return false;
                }
                Error error = (Error) other;
                return this.errorCode == error.errorCode && Intrinsics.areEqual(this.errorMsg, error.errorMsg) && this.errorType == error.errorType;
            }

            public final int getErrorCode() {
                return this.errorCode;
            }

            @Nullable
            public final String getErrorMsg() {
                return this.errorMsg;
            }

            @NotNull
            public final ru.mail.social_auth.domain.SocialAuthEvent.ErrorType getErrorType() {
                return this.errorType;
            }

            public int hashCode() {
                int iHashCode = Integer.hashCode(this.errorCode) * 31;
                String str = this.errorMsg;
                return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.errorType.hashCode();
            }

            @NotNull
            public String toString() {
                return "Error(errorCode=" + this.errorCode + ", errorMsg=" + this.errorMsg + ", errorType=" + this.errorType + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$NotAuthorizedErrorDuringPasswordChange;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NotAuthorizedErrorDuringPasswordChange implements SocialAuthEvent {

            @NotNull
            public static final NotAuthorizedErrorDuringPasswordChange INSTANCE = new NotAuthorizedErrorDuringPasswordChange();

            private NotAuthorizedErrorDuringPasswordChange() {
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof NotAuthorizedErrorDuringPasswordChange);
            }

            public int hashCode() {
                return -686555044;
            }

            @NotNull
            public String toString() {
                return "NotAuthorizedErrorDuringPasswordChange";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0014"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$OpenMailRestore;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "emailForRestore", "", "isRebind", "", "<init>", "(Ljava/lang/String;Z)V", "getEmailForRestore", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class OpenMailRestore implements SocialAuthEvent {

            @Nullable
            private final String emailForRestore;
            private final boolean isRebind;

            public OpenMailRestore(@Nullable String str, boolean z10) {
                this.emailForRestore = str;
                this.isRebind = z10;
            }

            public static /* synthetic */ OpenMailRestore copy$default(OpenMailRestore openMailRestore, String str, boolean z10, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = openMailRestore.emailForRestore;
                }
                if ((i10 & 2) != 0) {
                    z10 = openMailRestore.isRebind;
                }
                return openMailRestore.copy(str, z10);
            }

            @Nullable
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmailForRestore() {
                return this.emailForRestore;
            }

            /* JADX INFO: renamed from: component2, reason: from getter */
            public final boolean getIsRebind() {
                return this.isRebind;
            }

            @NotNull
            public final OpenMailRestore copy(@Nullable String emailForRestore, boolean isRebind) {
                return new OpenMailRestore(emailForRestore, isRebind);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof OpenMailRestore)) {
                    return false;
                }
                OpenMailRestore openMailRestore = (OpenMailRestore) other;
                return Intrinsics.areEqual(this.emailForRestore, openMailRestore.emailForRestore) && this.isRebind == openMailRestore.isRebind;
            }

            @Nullable
            public final String getEmailForRestore() {
                return this.emailForRestore;
            }

            public int hashCode() {
                String str = this.emailForRestore;
                return ((str == null ? 0 : str.hashCode()) * 31) + Boolean.hashCode(this.isRebind);
            }

            public final boolean isRebind() {
                return this.isRebind;
            }

            @NotNull
            public String toString() {
                return "OpenMailRestore(emailForRestore=" + this.emailForRestore + ", isRebind=" + this.isRebind + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$OpenRestoreVkidOldAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "emailForRestore", "", "<init>", "(Ljava/lang/String;)V", "getEmailForRestore", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class OpenRestoreVkidOldAuth implements SocialAuthEvent {

            @Nullable
            private final String emailForRestore;

            public OpenRestoreVkidOldAuth(@Nullable String str) {
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
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$PasswordSuccessfullyChanged;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class PasswordSuccessfullyChanged implements SocialAuthEvent {

            @NotNull
            public static final PasswordSuccessfullyChanged INSTANCE = new PasswordSuccessfullyChanged();

            private PasswordSuccessfullyChanged() {
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof PasswordSuccessfullyChanged);
            }

            public int hashCode() {
                return 994053532;
            }

            @NotNull
            public String toString() {
                return "PasswordSuccessfullyChanged";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Result;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "value", "Lru/mail/social_auth/domain/AuthResult;", "<init>", "(Lru/mail/social_auth/domain/AuthResult;)V", "getValue", "()Lru/mail/social_auth/domain/AuthResult;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Result implements SocialAuthEvent {

            @NotNull
            private final AuthResult value;

            public Result(@NotNull AuthResult value) {
                Intrinsics.checkNotNullParameter(value, "value");
                this.value = value;
            }

            public static /* synthetic */ Result copy$default(Result result, AuthResult authResult, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    authResult = result.value;
                }
                return result.copy(authResult);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final AuthResult getValue() {
                return this.value;
            }

            @NotNull
            public final Result copy(@NotNull AuthResult value) {
                Intrinsics.checkNotNullParameter(value, "value");
                return new Result(value);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Result) && Intrinsics.areEqual(this.value, ((Result) other).value);
            }

            @NotNull
            public final AuthResult getValue() {
                return this.value;
            }

            public int hashCode() {
                return this.value.hashCode();
            }

            @NotNull
            public String toString() {
                return "Result(value=" + this.value + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$StartRegistration;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class StartRegistration implements SocialAuthEvent {

            @NotNull
            public static final StartRegistration INSTANCE = new StartRegistration();

            private StartRegistration() {
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof StartRegistration);
            }

            public int hashCode() {
                return -1367877659;
            }

            @NotNull
            public String toString() {
                return "StartRegistration";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J=\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$SocialAuthEvent;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "authType", "Lru/mail/social_auth/domain/SocialAuthType;", "bindType", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lru/mail/social_auth/domain/SocialAuthType;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getAuthType", "()Lru/mail/social_auth/domain/SocialAuthType;", "getBindType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Success implements SocialAuthEvent {

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

            public Success(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull SocialAuthType authType, @Nullable String str) {
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

            public static /* synthetic */ Success copy$default(Success success, String str, String str2, String str3, SocialAuthType socialAuthType, String str4, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = success.email;
                }
                if ((i10 & 2) != 0) {
                    str2 = success.accessToken;
                }
                if ((i10 & 4) != 0) {
                    str3 = success.refreshToken;
                }
                if ((i10 & 8) != 0) {
                    socialAuthType = success.authType;
                }
                if ((i10 & 16) != 0) {
                    str4 = success.bindType;
                }
                String str5 = str4;
                String str6 = str3;
                return success.copy(str, str2, str6, socialAuthType, str5);
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
            public final Success copy(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull SocialAuthType authType, @Nullable String bindType) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(authType, "authType");
                return new Success(email, accessToken, refreshToken, authType, bindType);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Success)) {
                    return false;
                }
                Success success = (Success) other;
                return Intrinsics.areEqual(this.email, success.email) && Intrinsics.areEqual(this.accessToken, success.accessToken) && Intrinsics.areEqual(this.refreshToken, success.refreshToken) && this.authType == success.authType && Intrinsics.areEqual(this.bindType, success.bindType);
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
                return "Success(email=" + this.email + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", authType=" + this.authType + ", bindType=" + this.bindType + ")";
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartDefaultLoginScreenRequired;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StartDefaultLoginScreenRequired implements LoginActivityEvents {

        @Nullable
        private final String email;

        public StartDefaultLoginScreenRequired(@Nullable String str) {
            this.email = str;
        }

        public static /* synthetic */ StartDefaultLoginScreenRequired copy$default(StartDefaultLoginScreenRequired startDefaultLoginScreenRequired, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = startDefaultLoginScreenRequired.email;
            }
            return startDefaultLoginScreenRequired.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final StartDefaultLoginScreenRequired copy(@Nullable String email) {
            return new StartDefaultLoginScreenRequired(email);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StartDefaultLoginScreenRequired) && Intrinsics.areEqual(this.email, ((StartDefaultLoginScreenRequired) other).email);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        public int hashCode() {
            String str = this.email;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @NotNull
        public String toString() {
            return "StartDefaultLoginScreenRequired(email=" + this.email + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartGoogleAuthScreen;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "bundle", "Landroid/os/Bundle;", "<init>", "(Landroid/os/Bundle;)V", "getBundle", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class StartGoogleAuthScreen implements LoginActivityEvents {

        @NotNull
        private final Bundle bundle;

        public StartGoogleAuthScreen(@NotNull Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            this.bundle = bundle;
        }

        @NotNull
        public final Bundle getBundle() {
            return this.bundle;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartLoginOAuthWebView;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "tokensHolder", "Lru/mail/auth/webview/TokensHolder;", "<init>", "(Lru/mail/auth/webview/TokensHolder;)V", "getTokensHolder", "()Lru/mail/auth/webview/TokensHolder;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class StartLoginOAuthWebView implements LoginActivityEvents {

        @Nullable
        private final TokensHolder tokensHolder;

        public StartLoginOAuthWebView(@Nullable TokensHolder tokensHolder) {
            this.tokensHolder = tokensHolder;
        }

        @Nullable
        public final TokensHolder getTokensHolder() {
            return this.tokensHolder;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartLoginScreen;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "service", "Lru/mail/auth/EmailServiceResources$MailServiceResources;", "<init>", "(Lru/mail/auth/EmailServiceResources$MailServiceResources;)V", "getService", "()Lru/mail/auth/EmailServiceResources$MailServiceResources;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class StartLoginScreen implements LoginActivityEvents {

        @NotNull
        private final EmailServiceResources.MailServiceResources service;

        public StartLoginScreen(@NotNull EmailServiceResources.MailServiceResources service) {
            Intrinsics.checkNotNullParameter(service, "service");
            this.service = service;
        }

        @NotNull
        public final EmailServiceResources.MailServiceResources getService() {
            return this.service;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartLoginScreenWithXmail;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StartLoginScreenWithXmail implements LoginActivityEvents {

        @Nullable
        private final String email;

        public StartLoginScreenWithXmail(@Nullable String str) {
            this.email = str;
        }

        public static /* synthetic */ StartLoginScreenWithXmail copy$default(StartLoginScreenWithXmail startLoginScreenWithXmail, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = startLoginScreenWithXmail.email;
            }
            return startLoginScreenWithXmail.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final StartLoginScreenWithXmail copy(@Nullable String email) {
            return new StartLoginScreenWithXmail(email);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof StartLoginScreenWithXmail) && Intrinsics.areEqual(this.email, ((StartLoginScreenWithXmail) other).email);
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }

        public int hashCode() {
            String str = this.email;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @NotNull
        public String toString() {
            return "StartLoginScreenWithXmail(email=" + this.email + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartRegistration;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StartRegistration implements LoginActivityEvents {

        @NotNull
        public static final StartRegistration INSTANCE = new StartRegistration();

        private StartRegistration() {
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof StartRegistration);
        }

        public int hashCode() {
            return 350358222;
        }

        @NotNull
        public String toString() {
            return "StartRegistration";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartRegistrationNewExternalAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StartRegistrationNewExternalAuth implements LoginActivityEvents {

        @NotNull
        public static final StartRegistrationNewExternalAuth INSTANCE = new StartRegistrationNewExternalAuth();

        private StartRegistrationNewExternalAuth() {
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof StartRegistrationNewExternalAuth);
        }

        public int hashCode() {
            return -1806475995;
        }

        @NotNull
        public String toString() {
            return "StartRegistrationNewExternalAuth";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartVKAnotherLogin;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StartVKAnotherLogin implements LoginActivityEvents {

        @NotNull
        public static final StartVKAnotherLogin INSTANCE = new StartVKAnotherLogin();

        private StartVKAnotherLogin() {
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof StartVKAnotherLogin);
        }

        public int hashCode() {
            return 1701903920;
        }

        @NotNull
        public String toString() {
            return "StartVKAnotherLogin";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$StartXmailMigrationFromLogin;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class StartXmailMigrationFromLogin implements LoginActivityEvents {

        @NotNull
        public static final StartXmailMigrationFromLogin INSTANCE = new StartXmailMigrationFromLogin();

        private StartXmailMigrationFromLogin() {
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof StartXmailMigrationFromLogin);
        }

        public int hashCode() {
            return -738861813;
        }

        @NotNull
        public String toString() {
            return "StartXmailMigrationFromLogin";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", QrLoginAnalytics.Actions.Back, "StartBinding", "LoginWithAnotherWay", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin$Back;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin$LoginWithAnotherWay;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin$StartBinding;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface VkBindInLogin extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin$Back;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Back implements VkBindInLogin {

            @NotNull
            public static final Back INSTANCE = new Back();

            private Back() {
            }

            public boolean equals(@Nullable Object other) {
                return this == other || (other instanceof Back);
            }

            public int hashCode() {
                return 753520368;
            }

            @NotNull
            public String toString() {
                return QrLoginAnalytics.Actions.Back;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin$LoginWithAnotherWay;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class LoginWithAnotherWay implements VkBindInLogin {

            @NotNull
            private final String email;

            public LoginWithAnotherWay(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ LoginWithAnotherWay copy$default(LoginWithAnotherWay loginWithAnotherWay, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = loginWithAnotherWay.email;
                }
                return loginWithAnotherWay.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final LoginWithAnotherWay copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new LoginWithAnotherWay(email);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof LoginWithAnotherWay) && Intrinsics.areEqual(this.email, ((LoginWithAnotherWay) other).email);
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
                return "LoginWithAnotherWay(email=" + this.email + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin$StartBinding;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkBindInLogin;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class StartBinding implements VkBindInLogin {

            @NotNull
            private final String email;

            public StartBinding(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            public static /* synthetic */ StartBinding copy$default(StartBinding startBinding, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = startBinding.email;
                }
                return startBinding.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final StartBinding copy(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                return new StartBinding(email);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof StartBinding) && Intrinsics.areEqual(this.email, ((StartBinding) other).email);
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
                return "StartBinding(email=" + this.email + ")";
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0003\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "Error", "BackClick", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents$BackClick;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents$Success;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface VkPasswordEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents$BackClick;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class BackClick implements VkPasswordEvents {

            @NotNull
            public static final BackClick INSTANCE = new BackClick();

            private BackClick() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements VkPasswordEvents {

            @NotNull
            private final String error;

            public Error(@NotNull String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$VkPasswordEvents;", "login", "", "agToken", "oldBundle", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getLogin", "()Ljava/lang/String;", "getAgToken", "getOldBundle", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements VkPasswordEvents {

            @NotNull
            private final String agToken;

            @NotNull
            private final String login;

            @Nullable
            private final Bundle oldBundle;

            public Success(@NotNull String login, @NotNull String agToken, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(agToken, "agToken");
                this.login = login;
                this.agToken = agToken;
                this.oldBundle = bundle;
            }

            @NotNull
            public final String getAgToken() {
                return this.agToken;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @Nullable
            public final Bundle getOldBundle() {
                return this.oldBundle;
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$VkSilentSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "silentToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getSilentToken", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class VkSilentSuccess implements LoginActivityEvents {

        @NotNull
        private final String accessToken;

        @NotNull
        private final String email;

        @NotNull
        private final String refreshToken;

        @NotNull
        private final String silentToken;

        public VkSilentSuccess(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String silentToken) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(silentToken, "silentToken");
            this.email = email;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.silentToken = silentToken;
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
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "JapanAccount", "Error", QrLoginAnalytics.Actions.Back, "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface YahooEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents$Back;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Back implements YahooEvents {

            @NotNull
            public static final Back INSTANCE = new Back();

            private Back() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents;", "message", "", "email", "code", "Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult$YahooErrorCodes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult$YahooErrorCodes;)V", "getMessage", "()Ljava/lang/String;", "getEmail", "getCode", "()Lru/mail/authorizationsdk/feature/yahoo/presentation/YahooResult$YahooErrorCodes;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements YahooEvents {

            @Nullable
            private final YahooResult.YahooErrorCodes code;

            @Nullable
            private final String email;

            @NotNull
            private final String message;

            public Error(@NotNull String message, @Nullable String str, @Nullable YahooResult.YahooErrorCodes yahooErrorCodes) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.email = str;
                this.code = yahooErrorCodes;
            }

            @Nullable
            public final YahooResult.YahooErrorCodes getCode() {
                return this.code;
            }

            @Nullable
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents$JapanAccount;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class JapanAccount implements YahooEvents {

            @NotNull
            private final String email;

            public JapanAccount(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YahooEvents;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "yahooAccessToken", "yahooRefreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getYahooAccessToken", "getYahooRefreshToken", "getAccountType", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements YahooEvents {

            @NotNull
            private final String accessToken;

            @NotNull
            private final String accountType;

            @NotNull
            private final Bundle data;

            @NotNull
            private final String email;

            @NotNull
            private final String refreshToken;

            @NotNull
            private final String yahooAccessToken;

            @NotNull
            private final String yahooRefreshToken;

            public Success(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String yahooAccessToken, @NotNull String yahooRefreshToken, @NotNull String accountType, @NotNull Bundle data) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(yahooAccessToken, "yahooAccessToken");
                Intrinsics.checkNotNullParameter(yahooRefreshToken, "yahooRefreshToken");
                Intrinsics.checkNotNullParameter(accountType, "accountType");
                Intrinsics.checkNotNullParameter(data, "data");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.yahooAccessToken = yahooAccessToken;
                this.yahooRefreshToken = yahooRefreshToken;
                this.accountType = accountType;
                this.data = data;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getAccountType() {
                return this.accountType;
            }

            @NotNull
            public final Bundle getData() {
                return this.data;
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
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YandexEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "Error", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface YandexEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YandexEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YandexEvents;", "message", "", "email", "code", "Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult$YandexErrorCodes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult$YandexErrorCodes;)V", "getMessage", "()Ljava/lang/String;", "getEmail", "getCode", "()Lru/mail/authorizationsdk/feature/yandex/presentation/YandexResult$YandexErrorCodes;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements YandexEvents {

            @Nullable
            private final YandexResult.YandexErrorCodes code;

            @Nullable
            private final String email;

            @NotNull
            private final String message;

            public Error(@NotNull String message, @Nullable String str, @Nullable YandexResult.YandexErrorCodes yandexErrorCodes) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.email = str;
                this.code = yandexErrorCodes;
            }

            @Nullable
            public final YandexResult.YandexErrorCodes getCode() {
                return this.code;
            }

            @Nullable
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$YandexEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$YandexEvents;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "yandexAccessToken", "yandexRefreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getYandexAccessToken", "getYandexRefreshToken", "getAccountType", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements YandexEvents {

            @NotNull
            private final String accessToken;

            @NotNull
            private final String accountType;

            @NotNull
            private final Bundle data;

            @NotNull
            private final String email;

            @NotNull
            private final String refreshToken;

            @NotNull
            private final String yandexAccessToken;

            @NotNull
            private final String yandexRefreshToken;

            public Success(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String yandexAccessToken, @NotNull String yandexRefreshToken, @NotNull String accountType, @NotNull Bundle data) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(yandexAccessToken, "yandexAccessToken");
                Intrinsics.checkNotNullParameter(yandexRefreshToken, "yandexRefreshToken");
                Intrinsics.checkNotNullParameter(accountType, "accountType");
                Intrinsics.checkNotNullParameter(data, "data");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.yandexAccessToken = yandexAccessToken;
                this.yandexRefreshToken = yandexRefreshToken;
                this.accountType = accountType;
                this.data = data;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getAccountType() {
                return this.accountType;
            }

            @NotNull
            public final Bundle getData() {
                return this.data;
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
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0001\u0002\u0082\u0001\u0001\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$CustomServerEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "Lru/mail/auth/loginactivity/LoginActivityEvents$CustomServerEvents$Success;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface CustomServerEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$CustomServerEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$CustomServerEvents;", "email", "", "password", "oldBundle", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getOldBundle", "()Landroid/os/Bundle;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Success implements CustomServerEvents {

            @NotNull
            private final String email;

            @Nullable
            private final Bundle oldBundle;

            @NotNull
            private final String password;

            public Success(@NotNull String email, @NotNull String password, @Nullable Bundle bundle) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                this.email = email;
                this.password = password;
                this.oldBundle = bundle;
            }

            public static /* synthetic */ Success copy$default(Success success, String str, String str2, Bundle bundle, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = success.email;
                }
                if ((i10 & 2) != 0) {
                    str2 = success.password;
                }
                if ((i10 & 4) != 0) {
                    bundle = success.oldBundle;
                }
                return success.copy(str, str2, bundle);
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

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Bundle getOldBundle() {
                return this.oldBundle;
            }

            @NotNull
            public final Success copy(@NotNull String email, @NotNull String password, @Nullable Bundle oldBundle) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(password, "password");
                return new Success(email, password, oldBundle);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Success)) {
                    return false;
                }
                Success success = (Success) other;
                return Intrinsics.areEqual(this.email, success.email) && Intrinsics.areEqual(this.password, success.password) && Intrinsics.areEqual(this.oldBundle, success.oldBundle);
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @Nullable
            public final Bundle getOldBundle() {
                return this.oldBundle;
            }

            @NotNull
            public final String getPassword() {
                return this.password;
            }

            public int hashCode() {
                int iHashCode = ((this.email.hashCode() * 31) + this.password.hashCode()) * 31;
                Bundle bundle = this.oldBundle;
                return iHashCode + (bundle == null ? 0 : bundle.hashCode());
            }

            @NotNull
            public String toString() {
                return "Success(email=" + this.email + ", password=" + this.password + ", oldBundle=" + this.oldBundle + ")";
            }

            public /* synthetic */ Success(String str, String str2, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, (i10 & 4) != 0 ? null : bundle);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004¨\u0006\u0005À\u0006\u0003"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Success", "MigrantRegistrationRequired", "Error", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface GoogleEvents extends LoginActivityEvents {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents$MigrantRegistrationRequired;", "Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents;", "email", "", "xmailMigrationFrom", "migrantToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getXmailMigrationFrom", "getMigrantToken", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class MigrantRegistrationRequired implements GoogleEvents {

            @NotNull
            private final String email;

            @NotNull
            private final String migrantToken;

            @Nullable
            private final String xmailMigrationFrom;

            public MigrantRegistrationRequired(@NotNull String email, @Nullable String str, @NotNull String migrantToken) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(migrantToken, "migrantToken");
                this.email = email;
                this.xmailMigrationFrom = str;
                this.migrantToken = migrantToken;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getMigrantToken() {
                return this.migrantToken;
            }

            @Nullable
            public final String getXmailMigrationFrom() {
                return this.xmailMigrationFrom;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u0018"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents$Success;", "Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "googleAccessToken", "googleRefreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "data", "Landroid/os/Bundle;", "xmailMigrationFrom", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getGoogleAccessToken", "getGoogleRefreshToken", "getAccountType", "getData", "()Landroid/os/Bundle;", "getXmailMigrationFrom", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success implements GoogleEvents {

            @NotNull
            private final String accessToken;

            @NotNull
            private final String accountType;

            @NotNull
            private final Bundle data;

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

            public Success(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String googleAccessToken, @NotNull String googleRefreshToken, @NotNull String accountType, @NotNull Bundle data, @Nullable String str) {
                Intrinsics.checkNotNullParameter(email, "email");
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(googleAccessToken, "googleAccessToken");
                Intrinsics.checkNotNullParameter(googleRefreshToken, "googleRefreshToken");
                Intrinsics.checkNotNullParameter(accountType, "accountType");
                Intrinsics.checkNotNullParameter(data, "data");
                this.email = email;
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.googleAccessToken = googleAccessToken;
                this.googleRefreshToken = googleRefreshToken;
                this.accountType = accountType;
                this.data = data;
                this.xmailMigrationFrom = str;
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final String getAccountType() {
                return this.accountType;
            }

            @NotNull
            public final Bundle getData() {
                return this.data;
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
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents$Error;", "Lru/mail/auth/loginactivity/LoginActivityEvents$GoogleEvents;", "message", "", "email", "code", "Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult$GoogleErrorCodes;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult$GoogleErrorCodes;)V", "getMessage", "()Ljava/lang/String;", "getEmail", "getCode", "()Lru/mail/authorizationsdk/feature/google/common/presentation/GoogleResult$GoogleErrorCodes;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Error implements GoogleEvents {

            @Nullable
            private final GoogleResult.GoogleErrorCodes code;

            @Nullable
            private final String email;

            @NotNull
            private final String message;

            public Error(@NotNull String message, @Nullable String str, @Nullable GoogleResult.GoogleErrorCodes googleErrorCodes) {
                Intrinsics.checkNotNullParameter(message, "message");
                this.message = message;
                this.email = str;
                this.code = googleErrorCodes;
            }

            @Nullable
            public final GoogleResult.GoogleErrorCodes getCode() {
                return this.code;
            }

            @Nullable
            public final String getEmail() {
                return this.email;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }

            public /* synthetic */ Error(String str, String str2, GoogleResult.GoogleErrorCodes googleErrorCodes, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : googleErrorCodes);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$ImapLocalSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", "password", "providerInfo", "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getProviderInfo", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ImapLocalSuccess implements LoginActivityEvents {

        @Nullable
        private final Bundle data;

        @NotNull
        private final String email;

        @NotNull
        private final String password;

        @NotNull
        private final String providerInfo;

        public ImapLocalSuccess(@NotNull String email, @NotNull String password, @NotNull String providerInfo, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            this.email = email;
            this.password = password;
            this.providerInfo = providerInfo;
            this.data = bundle;
        }

        @Nullable
        public final Bundle getData() {
            return this.data;
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

        public /* synthetic */ ImapLocalSuccess(String str, String str2, String str3, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, (i10 & 8) != 0 ? null : bundle);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OAuthImapLocalSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", "vendorAccessToken", "vendorRefreshToken", "providerInfo", "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getVendorAccessToken", "getVendorRefreshToken", "getProviderInfo", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OAuthImapLocalSuccess implements LoginActivityEvents {

        @Nullable
        private final Bundle data;

        @NotNull
        private final String email;

        @NotNull
        private final String providerInfo;

        @NotNull
        private final String vendorAccessToken;

        @NotNull
        private final String vendorRefreshToken;

        public OAuthImapLocalSuccess(@NotNull String email, @NotNull String vendorAccessToken, @NotNull String vendorRefreshToken, @NotNull String providerInfo, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(vendorAccessToken, "vendorAccessToken");
            Intrinsics.checkNotNullParameter(vendorRefreshToken, "vendorRefreshToken");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            this.email = email;
            this.vendorAccessToken = vendorAccessToken;
            this.vendorRefreshToken = vendorRefreshToken;
            this.providerInfo = providerInfo;
            this.data = bundle;
        }

        @Nullable
        public final Bundle getData() {
            return this.data;
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

        public /* synthetic */ OAuthImapLocalSuccess(String str, String str2, String str3, String str4, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i10 & 16) != 0 ? null : bundle);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$RestoreWithoutPasswordSuccess;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "getAccountType", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RestoreWithoutPasswordSuccess implements LoginActivityEvents {

        @NotNull
        private final String accessToken;

        @NotNull
        private final String accountType;

        @Nullable
        private final Bundle data;

        @NotNull
        private final String email;

        @NotNull
        private final String refreshToken;

        public RestoreWithoutPasswordSuccess(@NotNull String email, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String accountType, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            this.email = email;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.accountType = accountType;
            this.data = bundle;
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getAccountType() {
            return this.accountType;
        }

        @Nullable
        public final Bundle getData() {
            return this.data;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public /* synthetic */ RestoreWithoutPasswordSuccess(String str, String str2, String str3, String str4, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, (i10 & 16) != 0 ? null : bundle);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$OneTimeCodeSuccessAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", "bindType", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getBindType", "getAccessToken", "getRefreshToken", "getAccountType", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class OneTimeCodeSuccessAuth implements LoginActivityEvents {

        @NotNull
        private final String accessToken;

        @NotNull
        private final String accountType;

        @Nullable
        private final String bindType;

        @Nullable
        private final Bundle data;

        @NotNull
        private final String email;

        @NotNull
        private final String refreshToken;

        public OneTimeCodeSuccessAuth(@NotNull String email, @Nullable String str, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String accountType, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            this.email = email;
            this.bindType = str;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.accountType = accountType;
            this.data = bundle;
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getAccountType() {
            return this.accountType;
        }

        @Nullable
        public final String getBindType() {
            return this.bindType;
        }

        @Nullable
        public final Bundle getData() {
            return this.data;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public /* synthetic */ OneTimeCodeSuccessAuth(String str, String str2, String str3, String str4, String str5, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i10 & 2) != 0 ? null : str2, str3, str4, str5, (i10 & 32) != 0 ? null : bundle);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B]\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$PasswordAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "email", "", "password", "tsaCookie", "bindType", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "isAutoLogin", "", "data", "Landroid/os/Bundle;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLandroid/os/Bundle;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getTsaCookie", "getBindType", "getAccessToken", "getRefreshToken", "getAccountType", "()Z", "getData", "()Landroid/os/Bundle;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class PasswordAuth implements LoginActivityEvents {

        @NotNull
        private final String accessToken;

        @NotNull
        private final String accountType;

        @Nullable
        private final String bindType;

        @Nullable
        private final Bundle data;

        @NotNull
        private final String email;
        private final boolean isAutoLogin;

        @NotNull
        private final String password;

        @NotNull
        private final String refreshToken;

        @Nullable
        private final String tsaCookie;

        public PasswordAuth(@NotNull String email, @NotNull String password, @Nullable String str, @Nullable String str2, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String accountType, boolean z10, @Nullable Bundle bundle) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            this.email = email;
            this.password = password;
            this.tsaCookie = str;
            this.bindType = str2;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.accountType = accountType;
            this.isAutoLogin = z10;
            this.data = bundle;
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getAccountType() {
            return this.accountType;
        }

        @Nullable
        public final String getBindType() {
            return this.bindType;
        }

        @Nullable
        public final Bundle getData() {
            return this.data;
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

        /* JADX INFO: renamed from: isAutoLogin, reason: from getter */
        public final boolean getIsAutoLogin() {
            return this.isAutoLogin;
        }

        public /* synthetic */ PasswordAuth(String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z10, Bundle bundle, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4, str5, str6, str7, (i10 & 128) != 0 ? false : z10, (i10 & 256) != 0 ? null : bundle);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Parcelize
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0093\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\u0004\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\t\u0010'\u001a\u00020\u0004HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010)\u001a\u00020\u0004HÆ\u0003J\t\u0010*\u001a\u00020\u0004HÆ\u0003J\t\u0010+\u001a\u00020\u0004HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010/\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u00100\u001a\u00020\u000eHÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u00103\u001a\u00020\u0012HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0004HÆ\u0003J¥\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0011\u001a\u00020\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\u0006\u00106\u001a\u000207J\u0013\u00108\u001a\u00020\u000e2\b\u00109\u001a\u0004\u0018\u00010:HÖ\u0003J\t\u0010;\u001a\u000207HÖ\u0001J\t\u0010<\u001a\u00020\u0004HÖ\u0001J\u0016\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u000207R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0013\u0010\f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017¨\u0006B"}, d2 = {"Lru/mail/auth/loginactivity/LoginActivityEvents$AfterRegAuth;", "Lru/mail/auth/loginactivity/LoginActivityEvents;", "Landroid/os/Parcelable;", "email", "", "password", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", MailLoginFragment.EXTRA_ACCOUNT_TYPE, "firstName", "lastName", "phone", "parentEmail", "forceCreateCollector", "", "migrationFrom", "bindType", "regFlow", "Lru/mail/authorizationsdk/feature/registration/presentation/model/RegFlow;", "vkAccessToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Lru/mail/authorizationsdk/feature/registration/presentation/model/RegFlow;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getAccessToken", "getRefreshToken", "getAccountType", "getFirstName", "getLastName", "getPhone", "getParentEmail", "getForceCreateCollector", "()Z", "getMigrationFrom", "getBindType", "getRegFlow", "()Lru/mail/authorizationsdk/feature/registration/presentation/model/RegFlow;", "getVkAccessToken", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "Landroid/os/Parcel;", Collector.FLAGS, "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class AfterRegAuth implements LoginActivityEvents, Parcelable {

        @NotNull
        public static final Parcelable.Creator<AfterRegAuth> CREATOR = new Creator();

        @NotNull
        private final String accessToken;

        @NotNull
        private final String accountType;

        @Nullable
        private final String bindType;

        @NotNull
        private final String email;

        @Nullable
        private final String firstName;
        private final boolean forceCreateCollector;

        @Nullable
        private final String lastName;

        @Nullable
        private final String migrationFrom;

        @Nullable
        private final String parentEmail;

        @Nullable
        private final String password;

        @Nullable
        private final String phone;

        @NotNull
        private final String refreshToken;

        @NotNull
        private final RegFlow regFlow;

        @Nullable
        private final String vkAccessToken;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<AfterRegAuth> {
            @Override // android.os.Parcelable.Creator
            public final AfterRegAuth createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return new AfterRegAuth(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString(), RegFlow.valueOf(parcel.readString()), parcel.readString());
            }

            @Override // android.os.Parcelable.Creator
            public final AfterRegAuth[] newArray(int i10) {
                return new AfterRegAuth[i10];
            }
        }

        public AfterRegAuth(@NotNull String email, @Nullable String str, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String accountType, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, boolean z10, @Nullable String str6, @Nullable String str7, @NotNull RegFlow regFlow, @Nullable String str8) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            Intrinsics.checkNotNullParameter(regFlow, "regFlow");
            this.email = email;
            this.password = str;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.accountType = accountType;
            this.firstName = str2;
            this.lastName = str3;
            this.phone = str4;
            this.parentEmail = str5;
            this.forceCreateCollector = z10;
            this.migrationFrom = str6;
            this.bindType = str7;
            this.regFlow = regFlow;
            this.vkAccessToken = str8;
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getEmail() {
            return this.email;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getForceCreateCollector() {
            return this.forceCreateCollector;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getMigrationFrom() {
            return this.migrationFrom;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getBindType() {
            return this.bindType;
        }

        @NotNull
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final RegFlow getRegFlow() {
            return this.regFlow;
        }

        @Nullable
        /* JADX INFO: renamed from: component14, reason: from getter */
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getPassword() {
            return this.password;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getAccountType() {
            return this.accountType;
        }

        @Nullable
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getFirstName() {
            return this.firstName;
        }

        @Nullable
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getLastName() {
            return this.lastName;
        }

        @Nullable
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getPhone() {
            return this.phone;
        }

        @Nullable
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getParentEmail() {
            return this.parentEmail;
        }

        @NotNull
        public final AfterRegAuth copy(@NotNull String email, @Nullable String password, @NotNull String accessToken, @NotNull String refreshToken, @NotNull String accountType, @Nullable String firstName, @Nullable String lastName, @Nullable String phone, @Nullable String parentEmail, boolean forceCreateCollector, @Nullable String migrationFrom, @Nullable String bindType, @NotNull RegFlow regFlow, @Nullable String vkAccessToken) {
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            Intrinsics.checkNotNullParameter(accountType, "accountType");
            Intrinsics.checkNotNullParameter(regFlow, "regFlow");
            return new AfterRegAuth(email, password, accessToken, refreshToken, accountType, firstName, lastName, phone, parentEmail, forceCreateCollector, migrationFrom, bindType, regFlow, vkAccessToken);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof AfterRegAuth)) {
                return false;
            }
            AfterRegAuth afterRegAuth = (AfterRegAuth) other;
            return Intrinsics.areEqual(this.email, afterRegAuth.email) && Intrinsics.areEqual(this.password, afterRegAuth.password) && Intrinsics.areEqual(this.accessToken, afterRegAuth.accessToken) && Intrinsics.areEqual(this.refreshToken, afterRegAuth.refreshToken) && Intrinsics.areEqual(this.accountType, afterRegAuth.accountType) && Intrinsics.areEqual(this.firstName, afterRegAuth.firstName) && Intrinsics.areEqual(this.lastName, afterRegAuth.lastName) && Intrinsics.areEqual(this.phone, afterRegAuth.phone) && Intrinsics.areEqual(this.parentEmail, afterRegAuth.parentEmail) && this.forceCreateCollector == afterRegAuth.forceCreateCollector && Intrinsics.areEqual(this.migrationFrom, afterRegAuth.migrationFrom) && Intrinsics.areEqual(this.bindType, afterRegAuth.bindType) && this.regFlow == afterRegAuth.regFlow && Intrinsics.areEqual(this.vkAccessToken, afterRegAuth.vkAccessToken);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getAccountType() {
            return this.accountType;
        }

        @Nullable
        public final String getBindType() {
            return this.bindType;
        }

        @NotNull
        public final String getEmail() {
            return this.email;
        }

        @Nullable
        public final String getFirstName() {
            return this.firstName;
        }

        public final boolean getForceCreateCollector() {
            return this.forceCreateCollector;
        }

        @Nullable
        public final String getLastName() {
            return this.lastName;
        }

        @Nullable
        public final String getMigrationFrom() {
            return this.migrationFrom;
        }

        @Nullable
        public final String getParentEmail() {
            return this.parentEmail;
        }

        @Nullable
        public final String getPassword() {
            return this.password;
        }

        @Nullable
        public final String getPhone() {
            return this.phone;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @NotNull
        public final RegFlow getRegFlow() {
            return this.regFlow;
        }

        @Nullable
        public final String getVkAccessToken() {
            return this.vkAccessToken;
        }

        public int hashCode() {
            int iHashCode = this.email.hashCode() * 31;
            String str = this.password;
            int iHashCode2 = (((((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.accessToken.hashCode()) * 31) + this.refreshToken.hashCode()) * 31) + this.accountType.hashCode()) * 31;
            String str2 = this.firstName;
            int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.lastName;
            int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.phone;
            int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.parentEmail;
            int iHashCode6 = (((iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31) + Boolean.hashCode(this.forceCreateCollector)) * 31;
            String str6 = this.migrationFrom;
            int iHashCode7 = (iHashCode6 + (str6 == null ? 0 : str6.hashCode())) * 31;
            String str7 = this.bindType;
            int iHashCode8 = (((iHashCode7 + (str7 == null ? 0 : str7.hashCode())) * 31) + this.regFlow.hashCode()) * 31;
            String str8 = this.vkAccessToken;
            return iHashCode8 + (str8 != null ? str8.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "AfterRegAuth(email=" + this.email + ", password=" + this.password + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", accountType=" + this.accountType + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", phone=" + this.phone + ", parentEmail=" + this.parentEmail + ", forceCreateCollector=" + this.forceCreateCollector + ", migrationFrom=" + this.migrationFrom + ", bindType=" + this.bindType + ", regFlow=" + this.regFlow + ", vkAccessToken=" + this.vkAccessToken + ")";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(this.email);
            dest.writeString(this.password);
            dest.writeString(this.accessToken);
            dest.writeString(this.refreshToken);
            dest.writeString(this.accountType);
            dest.writeString(this.firstName);
            dest.writeString(this.lastName);
            dest.writeString(this.phone);
            dest.writeString(this.parentEmail);
            dest.writeInt(this.forceCreateCollector ? 1 : 0);
            dest.writeString(this.migrationFrom);
            dest.writeString(this.bindType);
            dest.writeString(this.regFlow.name());
            dest.writeString(this.vkAccessToken);
        }

        public /* synthetic */ AfterRegAuth(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, boolean z10, String str10, String str11, RegFlow regFlow, String str12, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, str3, str4, str5, str6, str7, (i10 & 128) != 0 ? null : str8, (i10 & 256) != 0 ? null : str9, (i10 & 512) != 0 ? false : z10, (i10 & 1024) != 0 ? null : str10, (i10 & 2048) != 0 ? null : str11, regFlow, (i10 & 8192) != 0 ? null : str12);
        }
    }
}
