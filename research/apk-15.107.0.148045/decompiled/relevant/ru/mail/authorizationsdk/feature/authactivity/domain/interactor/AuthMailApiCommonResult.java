package ru.mail.authorizationsdk.feature.authactivity.domain.interactor;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.core.domain.AuthFlowType;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0082\u0001\u0004\u0006\u0007\b\t¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult;", "", "Success", "ImapLocalSuccess", "OauthImapLocalSuccess", "AdditionalCase", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$ImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$OauthImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$Success;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AuthMailApiCommonResult {

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\n¨\u0006\u0018"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$ImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/SuccessResult;", "login", "", "password", "providerInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getPassword", "getProviderInfo", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ImapLocalSuccess implements AuthMailApiCommonResult, SuccessResult {
        public static final int $stable = 0;

        @NotNull
        private final String login;

        @NotNull
        private final String password;

        @NotNull
        private final String providerInfo;

        public ImapLocalSuccess(@NotNull String login, @NotNull String password, @NotNull String providerInfo) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            this.login = login;
            this.password = password;
            this.providerInfo = providerInfo;
        }

        public static /* synthetic */ ImapLocalSuccess copy$default(ImapLocalSuccess imapLocalSuccess, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = imapLocalSuccess.login;
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
        public final String getProviderInfo() {
            return this.providerInfo;
        }

        @NotNull
        public final ImapLocalSuccess copy(@NotNull String login, @NotNull String password, @NotNull String providerInfo) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(password, "password");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            return new ImapLocalSuccess(login, password, providerInfo);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ImapLocalSuccess)) {
                return false;
            }
            ImapLocalSuccess imapLocalSuccess = (ImapLocalSuccess) other;
            return Intrinsics.areEqual(this.login, imapLocalSuccess.login) && Intrinsics.areEqual(this.password, imapLocalSuccess.password) && Intrinsics.areEqual(this.providerInfo, imapLocalSuccess.providerInfo);
        }

        @NotNull
        public final String getLogin() {
            return this.login;
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
            return (((this.login.hashCode() * 31) + this.password.hashCode()) * 31) + this.providerInfo.hashCode();
        }

        @NotNull
        public String toString() {
            return "ImapLocalSuccess(login=" + this.login + ", password=" + this.password + ", providerInfo=" + this.providerInfo + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u000f\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u001b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$OauthImapLocalSuccess;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/SuccessResult;", "login", "", "vendorAccessToken", "vendorRefreshToken", "providerInfo", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getVendorAccessToken", "getVendorRefreshToken", "getProviderInfo", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class OauthImapLocalSuccess implements AuthMailApiCommonResult, SuccessResult {
        public static final int $stable = 0;

        @NotNull
        private final String login;

        @NotNull
        private final String providerInfo;

        @NotNull
        private final String vendorAccessToken;

        @NotNull
        private final String vendorRefreshToken;

        public OauthImapLocalSuccess(@NotNull String login, @NotNull String vendorAccessToken, @NotNull String vendorRefreshToken, @NotNull String providerInfo) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(vendorAccessToken, "vendorAccessToken");
            Intrinsics.checkNotNullParameter(vendorRefreshToken, "vendorRefreshToken");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            this.login = login;
            this.vendorAccessToken = vendorAccessToken;
            this.vendorRefreshToken = vendorRefreshToken;
            this.providerInfo = providerInfo;
        }

        public static /* synthetic */ OauthImapLocalSuccess copy$default(OauthImapLocalSuccess oauthImapLocalSuccess, String str, String str2, String str3, String str4, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = oauthImapLocalSuccess.login;
            }
            if ((i10 & 2) != 0) {
                str2 = oauthImapLocalSuccess.vendorAccessToken;
            }
            if ((i10 & 4) != 0) {
                str3 = oauthImapLocalSuccess.vendorRefreshToken;
            }
            if ((i10 & 8) != 0) {
                str4 = oauthImapLocalSuccess.providerInfo;
            }
            return oauthImapLocalSuccess.copy(str, str2, str3, str4);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
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
        public final OauthImapLocalSuccess copy(@NotNull String login, @NotNull String vendorAccessToken, @NotNull String vendorRefreshToken, @NotNull String providerInfo) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(vendorAccessToken, "vendorAccessToken");
            Intrinsics.checkNotNullParameter(vendorRefreshToken, "vendorRefreshToken");
            Intrinsics.checkNotNullParameter(providerInfo, "providerInfo");
            return new OauthImapLocalSuccess(login, vendorAccessToken, vendorRefreshToken, providerInfo);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OauthImapLocalSuccess)) {
                return false;
            }
            OauthImapLocalSuccess oauthImapLocalSuccess = (OauthImapLocalSuccess) other;
            return Intrinsics.areEqual(this.login, oauthImapLocalSuccess.login) && Intrinsics.areEqual(this.vendorAccessToken, oauthImapLocalSuccess.vendorAccessToken) && Intrinsics.areEqual(this.vendorRefreshToken, oauthImapLocalSuccess.vendorRefreshToken) && Intrinsics.areEqual(this.providerInfo, oauthImapLocalSuccess.providerInfo);
        }

        @NotNull
        public final String getLogin() {
            return this.login;
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
            return (((((this.login.hashCode() * 31) + this.vendorAccessToken.hashCode()) * 31) + this.vendorRefreshToken.hashCode()) * 31) + this.providerInfo.hashCode();
        }

        @NotNull
        public String toString() {
            return "OauthImapLocalSuccess(login=" + this.login + ", vendorAccessToken=" + this.vendorAccessToken + ", vendorRefreshToken=" + this.vendorRefreshToken + ", providerInfo=" + this.providerInfo + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001d"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$Success;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/SuccessResult;", "authFlowType", "Lru/mail/authorizationsdk/feature/core/domain/AuthFlowType;", "email", "", CommonConstant.KEY_ACCESS_TOKEN, "refreshToken", "<init>", "(Lru/mail/authorizationsdk/feature/core/domain/AuthFlowType;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAuthFlowType", "()Lru/mail/authorizationsdk/feature/core/domain/AuthFlowType;", "getEmail", "()Ljava/lang/String;", "getAccessToken", "getRefreshToken", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Success implements AuthMailApiCommonResult, SuccessResult {
        public static final int $stable = 0;

        @NotNull
        private final String accessToken;

        @NotNull
        private final AuthFlowType authFlowType;

        @NotNull
        private final String email;

        @NotNull
        private final String refreshToken;

        public Success(@NotNull AuthFlowType authFlowType, @NotNull String email, @NotNull String accessToken, @NotNull String refreshToken) {
            Intrinsics.checkNotNullParameter(authFlowType, "authFlowType");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.authFlowType = authFlowType;
            this.email = email;
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public static /* synthetic */ Success copy$default(Success success, AuthFlowType authFlowType, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                authFlowType = success.authFlowType;
            }
            if ((i10 & 2) != 0) {
                str = success.email;
            }
            if ((i10 & 4) != 0) {
                str2 = success.accessToken;
            }
            if ((i10 & 8) != 0) {
                str3 = success.refreshToken;
            }
            return success.copy(authFlowType, str, str2, str3);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final AuthFlowType getAuthFlowType() {
            return this.authFlowType;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEmail() {
            return this.email;
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
        public final Success copy(@NotNull AuthFlowType authFlowType, @NotNull String email, @NotNull String accessToken, @NotNull String refreshToken) {
            Intrinsics.checkNotNullParameter(authFlowType, "authFlowType");
            Intrinsics.checkNotNullParameter(email, "email");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            return new Success(authFlowType, email, accessToken, refreshToken);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Success)) {
                return false;
            }
            Success success = (Success) other;
            return Intrinsics.areEqual(this.authFlowType, success.authFlowType) && Intrinsics.areEqual(this.email, success.email) && Intrinsics.areEqual(this.accessToken, success.accessToken) && Intrinsics.areEqual(this.refreshToken, success.refreshToken);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final AuthFlowType getAuthFlowType() {
            return this.authFlowType;
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
            return (((((this.authFlowType.hashCode() * 31) + this.email.hashCode()) * 31) + this.accessToken.hashCode()) * 31) + this.refreshToken.hashCode();
        }

        @NotNull
        public String toString() {
            return "Success(authFlowType=" + this.authFlowType + ", email=" + this.email + ", accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0006\u0002\u0003\u0004\u0005\u0006\u0007¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult;", "DefaultLoginRequired", "NeedCaptcha", "NeedSecondStep", "ServerSettingsRequired", "OAuthRequired", "Error", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface AdditionalCase extends AuthMailApiCommonResult {

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$DefaultLoginRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultLoginRequired implements AdditionalCase {
            public static final int $stable = 0;

            @NotNull
            private final String email;

            public DefaultLoginRequired(@NotNull String email) {
                Intrinsics.checkNotNullParameter(email, "email");
                this.email = email;
            }

            @NotNull
            public final String getEmail() {
                return this.email;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001:\f\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "ExternalAuthProhibit", "BindTokenExpired", "B2bUserBlocked", "ExternalAccountRegistrationRequired", GoogleErrorDescriptions.USER_BLOCKED_ERROR, "TwoFactorBindForbidden", "ErrorWithImapSettings", GoogleErrorDescriptions.NETWORK_ERROR, GoogleErrorDescriptions.UNKNOWN_ERROR, "ErrorWithMessage", "ErrorInvalidLogin", "OAuthImapFailed", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface Error extends AdditionalCase {

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$B2bUserBlocked;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class B2bUserBlocked implements Error {
                public static final int $stable = 0;

                @NotNull
                public static final B2bUserBlocked INSTANCE = new B2bUserBlocked();

                private B2bUserBlocked() {
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$BindTokenExpired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class BindTokenExpired implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public BindTokenExpired(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                @NotNull
                public final String getEmail() {
                    return this.email;
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$ErrorInvalidLogin;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class ErrorInvalidLogin implements Error {
                public static final int $stable = 0;

                @NotNull
                public static final ErrorInvalidLogin INSTANCE = new ErrorInvalidLogin();

                private ErrorInvalidLogin() {
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$ErrorWithImapSettings;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "password", "settings", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getSettings", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class ErrorWithImapSettings implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                @NotNull
                private final String password;

                @NotNull
                private final String settings;

                public ErrorWithImapSettings(@NotNull String email, @NotNull String password, @NotNull String settings) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    Intrinsics.checkNotNullParameter(password, "password");
                    Intrinsics.checkNotNullParameter(settings, "settings");
                    this.email = email;
                    this.password = password;
                    this.settings = settings;
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
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$ErrorWithMessage;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "message", "", "isLoginPasswordError", "", "<init>", "(Ljava/lang/String;Z)V", "getMessage", "()Ljava/lang/String;", "()Z", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class ErrorWithMessage implements Error {
                public static final int $stable = 0;
                private final boolean isLoginPasswordError;

                @NotNull
                private final String message;

                public ErrorWithMessage(@NotNull String message, boolean z10) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    this.message = message;
                    this.isLoginPasswordError = z10;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }

                /* JADX INFO: renamed from: isLoginPasswordError, reason: from getter */
                public final boolean getIsLoginPasswordError() {
                    return this.isLoginPasswordError;
                }

                public /* synthetic */ ErrorWithMessage(String str, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                    this(str, (i10 & 2) != 0 ? false : z10);
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$ExternalAccountRegistrationRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "password", "registerId", "isCaptchaRequired", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getEmail", "()Ljava/lang/String;", "getPassword", "getRegisterId", "()Z", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class ExternalAccountRegistrationRequired implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;
                private final boolean isCaptchaRequired;

                @NotNull
                private final String password;

                @NotNull
                private final String registerId;

                public ExternalAccountRegistrationRequired(@NotNull String email, @NotNull String password, @NotNull String registerId, boolean z10) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    Intrinsics.checkNotNullParameter(password, "password");
                    Intrinsics.checkNotNullParameter(registerId, "registerId");
                    this.email = email;
                    this.password = password;
                    this.registerId = registerId;
                    this.isCaptchaRequired = z10;
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
                public final String getRegisterId() {
                    return this.registerId;
                }

                /* JADX INFO: renamed from: isCaptchaRequired, reason: from getter */
                public final boolean getIsCaptchaRequired() {
                    return this.isCaptchaRequired;
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$ExternalAuthProhibit;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class ExternalAuthProhibit implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public ExternalAuthProhibit(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                @NotNull
                public final String getEmail() {
                    return this.email;
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$NetworkError;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class NetworkError implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String message;

                public NetworkError(@NotNull String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    this.message = message;
                }

                @NotNull
                public final String getMessage() {
                    return this.message;
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$OAuthImapFailed;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final /* data */ class OAuthImapFailed implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public OAuthImapFailed(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                public static /* synthetic */ OAuthImapFailed copy$default(OAuthImapFailed oAuthImapFailed, String str, int i10, Object obj) {
                    if ((i10 & 1) != 0) {
                        str = oAuthImapFailed.email;
                    }
                    return oAuthImapFailed.copy(str);
                }

                @NotNull
                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getEmail() {
                    return this.email;
                }

                @NotNull
                public final OAuthImapFailed copy(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    return new OAuthImapFailed(email);
                }

                public boolean equals(@Nullable Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof OAuthImapFailed) && Intrinsics.areEqual(this.email, ((OAuthImapFailed) other).email);
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
                    return "OAuthImapFailed(email=" + this.email + ")";
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$TwoFactorBindForbidden;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "socialType", "errorMessage", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "getSocialType", "getErrorMessage", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class TwoFactorBindForbidden implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                @NotNull
                private final String errorMessage;

                @NotNull
                private final String socialType;

                public TwoFactorBindForbidden(@NotNull String email, @NotNull String socialType, @NotNull String errorMessage) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    Intrinsics.checkNotNullParameter(socialType, "socialType");
                    Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
                    this.email = email;
                    this.socialType = socialType;
                    this.errorMessage = errorMessage;
                }

                @NotNull
                public final String getEmail() {
                    return this.email;
                }

                @NotNull
                public final String getErrorMessage() {
                    return this.errorMessage;
                }

                @NotNull
                public final String getSocialType() {
                    return this.socialType;
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 0)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$UnknownError;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "data", "", "<init>", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class UnknownError implements Error {
                public static final int $stable = 8;

                @NotNull
                private final Object data;

                public UnknownError(@NotNull Object data) {
                    Intrinsics.checkNotNullParameter(data, "data");
                    this.data = data;
                }

                @NotNull
                public final Object getData() {
                    return this.data;
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error$UserBlockedError;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$Error;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class UserBlockedError implements Error {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public UserBlockedError(@NotNull String email) {
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
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$NeedSecondStep;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "login", "", "url", "cookieHeader", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getUrl", "getCookieHeader", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NeedSecondStep implements AdditionalCase {
            public static final int $stable = 0;

            @NotNull
            private final String cookieHeader;

            @NotNull
            private final String login;

            @NotNull
            private final String url;

            public NeedSecondStep(@NotNull String login, @NotNull String url, @NotNull String cookieHeader) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(url, "url");
                Intrinsics.checkNotNullParameter(cookieHeader, "cookieHeader");
                this.login = login;
                this.url = url;
                this.cookieHeader = cookieHeader;
            }

            @NotNull
            public final String getCookieHeader() {
                return this.cookieHeader;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final String getUrl() {
                return this.url;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "OAuthGoogleRequired", "OAuthYahooRequired", "OAuthYandexRequired", "OAuthOutlookRequired", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public interface OAuthRequired extends AdditionalCase {

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired$OAuthGoogleRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final /* data */ class OAuthGoogleRequired implements OAuthRequired {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public OAuthGoogleRequired(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                public static /* synthetic */ OAuthGoogleRequired copy$default(OAuthGoogleRequired oAuthGoogleRequired, String str, int i10, Object obj) {
                    if ((i10 & 1) != 0) {
                        str = oAuthGoogleRequired.email;
                    }
                    return oAuthGoogleRequired.copy(str);
                }

                @NotNull
                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getEmail() {
                    return this.email;
                }

                @NotNull
                public final OAuthGoogleRequired copy(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    return new OAuthGoogleRequired(email);
                }

                public boolean equals(@Nullable Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof OAuthGoogleRequired) && Intrinsics.areEqual(this.email, ((OAuthGoogleRequired) other).email);
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
                    return "OAuthGoogleRequired(email=" + this.email + ")";
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired$OAuthOutlookRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final /* data */ class OAuthOutlookRequired implements OAuthRequired {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public OAuthOutlookRequired(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                public static /* synthetic */ OAuthOutlookRequired copy$default(OAuthOutlookRequired oAuthOutlookRequired, String str, int i10, Object obj) {
                    if ((i10 & 1) != 0) {
                        str = oAuthOutlookRequired.email;
                    }
                    return oAuthOutlookRequired.copy(str);
                }

                @NotNull
                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getEmail() {
                    return this.email;
                }

                @NotNull
                public final OAuthOutlookRequired copy(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    return new OAuthOutlookRequired(email);
                }

                public boolean equals(@Nullable Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof OAuthOutlookRequired) && Intrinsics.areEqual(this.email, ((OAuthOutlookRequired) other).email);
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
                    return "OAuthOutlookRequired(email=" + this.email + ")";
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired$OAuthYahooRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final /* data */ class OAuthYahooRequired implements OAuthRequired {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public OAuthYahooRequired(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                public static /* synthetic */ OAuthYahooRequired copy$default(OAuthYahooRequired oAuthYahooRequired, String str, int i10, Object obj) {
                    if ((i10 & 1) != 0) {
                        str = oAuthYahooRequired.email;
                    }
                    return oAuthYahooRequired.copy(str);
                }

                @NotNull
                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getEmail() {
                    return this.email;
                }

                @NotNull
                public final OAuthYahooRequired copy(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    return new OAuthYahooRequired(email);
                }

                public boolean equals(@Nullable Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof OAuthYahooRequired) && Intrinsics.areEqual(this.email, ((OAuthYahooRequired) other).email);
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
                    return "OAuthYahooRequired(email=" + this.email + ")";
                }
            }

            /* JADX INFO: compiled from: ProGuard */
            @StabilityInferred(parameters = 1)
            @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired$OAuthYandexRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$OAuthRequired;", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final /* data */ class OAuthYandexRequired implements OAuthRequired {
                public static final int $stable = 0;

                @NotNull
                private final String email;

                public OAuthYandexRequired(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    this.email = email;
                }

                public static /* synthetic */ OAuthYandexRequired copy$default(OAuthYandexRequired oAuthYandexRequired, String str, int i10, Object obj) {
                    if ((i10 & 1) != 0) {
                        str = oAuthYandexRequired.email;
                    }
                    return oAuthYandexRequired.copy(str);
                }

                @NotNull
                /* JADX INFO: renamed from: component1, reason: from getter */
                public final String getEmail() {
                    return this.email;
                }

                @NotNull
                public final OAuthYandexRequired copy(@NotNull String email) {
                    Intrinsics.checkNotNullParameter(email, "email");
                    return new OAuthYandexRequired(email);
                }

                public boolean equals(@Nullable Object other) {
                    if (this == other) {
                        return true;
                    }
                    return (other instanceof OAuthYandexRequired) && Intrinsics.areEqual(this.email, ((OAuthYandexRequired) other).email);
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
                    return "OAuthYandexRequired(email=" + this.email + ")";
                }
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u000e"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$NeedCaptcha;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "login", "", "ludwigToken", "urlForFallBack", "cookieHeaderForFallback", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getLudwigToken", "getUrlForFallBack", "getCookieHeaderForFallback", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NeedCaptcha implements AdditionalCase {
            public static final int $stable = 0;

            @Nullable
            private final String cookieHeaderForFallback;

            @NotNull
            private final String login;

            @NotNull
            private final String ludwigToken;

            @Nullable
            private final String urlForFallBack;

            public NeedCaptcha(@NotNull String login, @NotNull String ludwigToken, @Nullable String str, @Nullable String str2) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(ludwigToken, "ludwigToken");
                this.login = login;
                this.ludwigToken = ludwigToken;
                this.urlForFallBack = str;
                this.cookieHeaderForFallback = str2;
            }

            @Nullable
            public final String getCookieHeaderForFallback() {
                return this.cookieHeaderForFallback;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final String getLudwigToken() {
                return this.ludwigToken;
            }

            @Nullable
            public final String getUrlForFallBack() {
                return this.urlForFallBack;
            }

            public /* synthetic */ NeedCaptcha(String str, String str2, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : str4);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0010¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase$ServerSettingsRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/interactor/AuthMailApiCommonResult$AdditionalCase;", "login", "", "password", "message", "needCaptcha", "", "isLocalImapOnlyFlow", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZ)V", "getLogin", "()Ljava/lang/String;", "getPassword", "getMessage", "getNeedCaptcha", "()Z", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ServerSettingsRequired implements AdditionalCase {
            public static final int $stable = 0;
            private final boolean isLocalImapOnlyFlow;

            @NotNull
            private final String login;

            @NotNull
            private final String message;
            private final boolean needCaptcha;

            @NotNull
            private final String password;

            public ServerSettingsRequired(@NotNull String login, @NotNull String password, @NotNull String message, boolean z10, boolean z11) {
                Intrinsics.checkNotNullParameter(login, "login");
                Intrinsics.checkNotNullParameter(password, "password");
                Intrinsics.checkNotNullParameter(message, "message");
                this.login = login;
                this.password = password;
                this.message = message;
                this.needCaptcha = z10;
                this.isLocalImapOnlyFlow = z11;
            }

            @NotNull
            public final String getLogin() {
                return this.login;
            }

            @NotNull
            public final String getMessage() {
                return this.message;
            }

            public final boolean getNeedCaptcha() {
                return this.needCaptcha;
            }

            @NotNull
            public final String getPassword() {
                return this.password;
            }

            /* JADX INFO: renamed from: isLocalImapOnlyFlow, reason: from getter */
            public final boolean getIsLocalImapOnlyFlow() {
                return this.isLocalImapOnlyFlow;
            }

            public /* synthetic */ ServerSettingsRequired(String str, String str2, String str3, boolean z10, boolean z11, int i10, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, str3, z10, (i10 & 16) != 0 ? false : z11);
            }
        }
    }
}
