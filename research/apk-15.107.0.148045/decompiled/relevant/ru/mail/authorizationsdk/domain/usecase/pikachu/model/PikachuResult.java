package ru.mail.authorizationsdk.domain.usecase.pikachu.model;

import android.graphics.Bitmap;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult;", "", "<init>", "()V", "Success", "Error", "Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult$Error;", "Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult$Success;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class PikachuResult {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult$Error;", "Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Error extends PikachuResult {
        public static final int $stable = 0;

        @NotNull
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Error(@NotNull String message) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
        }

        @NotNull
        public final String getMessage() {
            return this.message;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult$Success;", "Lru/mail/authorizationsdk/domain/usecase/pikachu/model/PikachuResult;", "captcha", "Landroid/graphics/Bitmap;", "cookie", "", "xCaptchaId", "<init>", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;)V", "getCaptcha", "()Landroid/graphics/Bitmap;", "getCookie", "()Ljava/lang/String;", "getXCaptchaId", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Success extends PikachuResult {
        public static final int $stable = 8;

        @NotNull
        private final Bitmap captcha;

        @NotNull
        private final String cookie;

        @Nullable
        private final String xCaptchaId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull Bitmap captcha, @NotNull String cookie, @Nullable String str) {
            super(null);
            Intrinsics.checkNotNullParameter(captcha, "captcha");
            Intrinsics.checkNotNullParameter(cookie, "cookie");
            this.captcha = captcha;
            this.cookie = cookie;
            this.xCaptchaId = str;
        }

        @NotNull
        public final Bitmap getCaptcha() {
            return this.captcha;
        }

        @NotNull
        public final String getCookie() {
            return this.cookie;
        }

        @Nullable
        public final String getXCaptchaId() {
            return this.xCaptchaId;
        }
    }

    public /* synthetic */ PikachuResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private PikachuResult() {
    }
}
