package ru.mail.authorizationsdk.feature.registration.domain.signup;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.registration.domain.model.ErrorValue;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult;", "", "<init>", "()V", "Success", "Captcha", "PhoneRequired", "Error", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$Captcha;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$Error;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$PhoneRequired;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$Success;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SignupResult {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$Captcha;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult;", "regToken", "", "siteKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getRegToken", "()Ljava/lang/String;", "getSiteKey", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Captcha extends SignupResult {
        public static final int $stable = 0;

        @NotNull
        private final String regToken;

        @Nullable
        private final String siteKey;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Captcha(@NotNull String regToken, @Nullable String str) {
            super(null);
            Intrinsics.checkNotNullParameter(regToken, "regToken");
            this.regToken = regToken;
            this.siteKey = str;
        }

        public static /* synthetic */ Captcha copy$default(Captcha captcha, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = captcha.regToken;
            }
            if ((i10 & 2) != 0) {
                str2 = captcha.siteKey;
            }
            return captcha.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRegToken() {
            return this.regToken;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getSiteKey() {
            return this.siteKey;
        }

        @NotNull
        public final Captcha copy(@NotNull String regToken, @Nullable String siteKey) {
            Intrinsics.checkNotNullParameter(regToken, "regToken");
            return new Captcha(regToken, siteKey);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Captcha)) {
                return false;
            }
            Captcha captcha = (Captcha) other;
            return Intrinsics.areEqual(this.regToken, captcha.regToken) && Intrinsics.areEqual(this.siteKey, captcha.siteKey);
        }

        @NotNull
        public final String getRegToken() {
            return this.regToken;
        }

        @Nullable
        public final String getSiteKey() {
            return this.siteKey;
        }

        public int hashCode() {
            int iHashCode = this.regToken.hashCode() * 31;
            String str = this.siteKey;
            return iHashCode + (str == null ? 0 : str.hashCode());
        }

        @NotNull
        public String toString() {
            return "Captcha(regToken=" + this.regToken + ", siteKey=" + this.siteKey + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$Error;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult;", VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/mail/authorizationsdk/feature/registration/domain/model/ErrorValue;", "<init>", "(Ljava/util/List;)V", "getErrors", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Error extends SignupResult {
        public static final int $stable = 8;

        @NotNull
        private final List<ErrorValue> errors;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(@NotNull List<ErrorValue> errors) {
            super(null);
            Intrinsics.checkNotNullParameter(errors, "errors");
            this.errors = errors;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Error copy$default(Error error, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = error.errors;
            }
            return error.copy(list);
        }

        @NotNull
        public final List<ErrorValue> component1() {
            return this.errors;
        }

        @NotNull
        public final Error copy(@NotNull List<ErrorValue> errors) {
            Intrinsics.checkNotNullParameter(errors, "errors");
            return new Error(errors);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Error) && Intrinsics.areEqual(this.errors, ((Error) other).errors);
        }

        @NotNull
        public final List<ErrorValue> getErrors() {
            return this.errors;
        }

        public int hashCode() {
            return this.errors.hashCode();
        }

        @NotNull
        public String toString() {
            return "Error(errors=" + this.errors + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0003J\t\u0010\b\u001a\u00020\tHÖ\u0001J\t\u0010\n\u001a\u00020\u000bHÖ\u0001¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$PhoneRequired;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult;", "<init>", "()V", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class PhoneRequired extends SignupResult {
        public static final int $stable = 0;

        @NotNull
        public static final PhoneRequired INSTANCE = new PhoneRequired();

        private PhoneRequired() {
            super(null);
        }

        public boolean equals(@Nullable Object other) {
            return this == other || (other instanceof PhoneRequired);
        }

        public int hashCode() {
            return -975660612;
        }

        @NotNull
        public String toString() {
            return "PhoneRequired";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult$Success;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/SignupResult;", CommonConstant.KEY_ACCESS_TOKEN, "", "refreshToken", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Success extends SignupResult {
        public static final int $stable = 0;

        @NotNull
        private final String accessToken;

        @NotNull
        private final String refreshToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull String accessToken, @NotNull String refreshToken) {
            super(null);
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }

        public static /* synthetic */ Success copy$default(Success success, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = success.accessToken;
            }
            if ((i10 & 2) != 0) {
                str2 = success.refreshToken;
            }
            return success.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        @NotNull
        public final Success copy(@NotNull String accessToken, @NotNull String refreshToken) {
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
            return new Success(accessToken, refreshToken);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Success)) {
                return false;
            }
            Success success = (Success) other;
            return Intrinsics.areEqual(this.accessToken, success.accessToken) && Intrinsics.areEqual(this.refreshToken, success.refreshToken);
        }

        @NotNull
        public final String getAccessToken() {
            return this.accessToken;
        }

        @NotNull
        public final String getRefreshToken() {
            return this.refreshToken;
        }

        public int hashCode() {
            return (this.accessToken.hashCode() * 31) + this.refreshToken.hashCode();
        }

        @NotNull
        public String toString() {
            return "Success(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ")";
        }
    }

    public /* synthetic */ SignupResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private SignupResult() {
    }
}
