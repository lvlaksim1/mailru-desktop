package ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult;", "", "<init>", "()V", "OAuthRequired", "SecondStepRequired", "Error", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$OAuthRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$SecondStepRequired;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class AuthResult {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult;", "<init>", "()V", GoogleErrorDescriptions.NETWORK_ERROR, GoogleErrorDescriptions.UNKNOWN_ERROR, "ErrorWithStatusCode", "ErrorInvalidLogin", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Error extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error$ErrorInvalidLogin;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ErrorInvalidLogin extends Error {
            public static final int $stable = 0;

            @NotNull
            public static final ErrorInvalidLogin INSTANCE = new ErrorInvalidLogin();

            private ErrorInvalidLogin() {
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error$ErrorWithStatusCode;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error;", HiAnalyticsConstant.HaKey.BI_KEY_RESULT, "", "<init>", "(Ljava/lang/String;)V", "getStatusCode", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class ErrorWithStatusCode extends Error {
            public static final int $stable = 0;

            @NotNull
            private final String statusCode;

            public ErrorWithStatusCode(@NotNull String statusCode) {
                Intrinsics.checkNotNullParameter(statusCode, "statusCode");
                this.statusCode = statusCode;
            }

            @NotNull
            public final String getStatusCode() {
                return this.statusCode;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error$NetworkError;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error;", "e", "Ljava/io/IOException;", "<init>", "(Ljava/io/IOException;)V", "getE", "()Ljava/io/IOException;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class NetworkError extends Error {
            public static final int $stable = 8;

            @NotNull
            private final IOException e;

            public NetworkError(@NotNull IOException e10) {
                Intrinsics.checkNotNullParameter(e10, "e");
                this.e = e10;
            }

            @NotNull
            public final IOException getE() {
                return this.e;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error$UnknownError;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$Error;", "data", "", "<init>", "(Ljava/lang/Object;)V", "getData", "()Ljava/lang/Object;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnknownError extends Error {
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

        public Error() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$OAuthRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult;", "<init>", "()V", "OAuthYahooRequired", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class OAuthRequired extends AuthResult {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$OAuthRequired$OAuthYahooRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$OAuthRequired;", "<init>", "()V", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OAuthYahooRequired extends OAuthRequired {
            public static final int $stable = 0;

            @NotNull
            public static final OAuthYahooRequired INSTANCE = new OAuthYahooRequired();

            private OAuthYahooRequired() {
            }
        }

        public OAuthRequired() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\f"}, d2 = {"Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult$SecondStepRequired;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthResult;", "secondStepUrl", "", "ludwigToken", "cookie", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSecondStepUrl", "()Ljava/lang/String;", "getLudwigToken", "getCookie", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SecondStepRequired extends AuthResult {
        public static final int $stable = 0;

        @Nullable
        private final String cookie;

        @Nullable
        private final String ludwigToken;

        @Nullable
        private final String secondStepUrl;

        public SecondStepRequired(@Nullable String str, @Nullable String str2, @Nullable String str3) {
            super(null);
            this.secondStepUrl = str;
            this.ludwigToken = str2;
            this.cookie = str3;
        }

        @Nullable
        public final String getCookie() {
            return this.cookie;
        }

        @Nullable
        public final String getLudwigToken() {
            return this.ludwigToken;
        }

        @Nullable
        public final String getSecondStepUrl() {
            return this.secondStepUrl;
        }
    }

    public /* synthetic */ AuthResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AuthResult() {
    }
}
