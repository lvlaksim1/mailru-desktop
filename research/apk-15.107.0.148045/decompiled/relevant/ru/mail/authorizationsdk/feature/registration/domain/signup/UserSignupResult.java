package ru.mail.authorizationsdk.feature.registration.domain.signup;

import androidx.compose.runtime.internal.StabilityInferred;
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
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult;", "", "<init>", "()V", "Captcha", "Success", "Error", "ErrorPhoneRequired", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$Captcha;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$Error;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$ErrorPhoneRequired;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$Success;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class UserSignupResult {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$Captcha;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult;", "regToken", "", "siteKey", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getRegToken", "()Ljava/lang/String;", "getSiteKey", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Captcha extends UserSignupResult {
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
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$Error;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult;", VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/mail/authorizationsdk/feature/registration/domain/model/ErrorValue;", "<init>", "(Ljava/util/List;)V", "getErrors", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Error extends UserSignupResult {
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
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$ErrorPhoneRequired;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ErrorPhoneRequired extends UserSignupResult {
        public static final int $stable = 0;

        @Nullable
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public ErrorPhoneRequired() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ ErrorPhoneRequired copy$default(ErrorPhoneRequired errorPhoneRequired, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = errorPhoneRequired.message;
            }
            return errorPhoneRequired.copy(str);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        @NotNull
        public final ErrorPhoneRequired copy(@Nullable String message) {
            return new ErrorPhoneRequired(message);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof ErrorPhoneRequired) && Intrinsics.areEqual(this.message, ((ErrorPhoneRequired) other).message);
        }

        @Nullable
        public final String getMessage() {
            return this.message;
        }

        public int hashCode() {
            String str = this.message;
            if (str == null) {
                return 0;
            }
            return str.hashCode();
        }

        @NotNull
        public String toString() {
            return "ErrorPhoneRequired(message=" + this.message + ")";
        }

        public ErrorPhoneRequired(@Nullable String str) {
            super(null);
            this.message = str;
        }

        public /* synthetic */ ErrorPhoneRequired(String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this((i10 & 1) != 0 ? null : str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult$Success;", "Lru/mail/authorizationsdk/feature/registration/domain/signup/UserSignupResult;", "regToken", "", "<init>", "(Ljava/lang/String;)V", "getRegToken", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Success extends UserSignupResult {
        public static final int $stable = 0;

        @NotNull
        private final String regToken;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull String regToken) {
            super(null);
            Intrinsics.checkNotNullParameter(regToken, "regToken");
            this.regToken = regToken;
        }

        public static /* synthetic */ Success copy$default(Success success, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = success.regToken;
            }
            return success.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getRegToken() {
            return this.regToken;
        }

        @NotNull
        public final Success copy(@NotNull String regToken) {
            Intrinsics.checkNotNullParameter(regToken, "regToken");
            return new Success(regToken);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && Intrinsics.areEqual(this.regToken, ((Success) other).regToken);
        }

        @NotNull
        public final String getRegToken() {
            return this.regToken;
        }

        public int hashCode() {
            return this.regToken.hashCode();
        }

        @NotNull
        public String toString() {
            return "Success(regToken=" + this.regToken + ")";
        }
    }

    public /* synthetic */ UserSignupResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private UserSignupResult() {
    }
}
