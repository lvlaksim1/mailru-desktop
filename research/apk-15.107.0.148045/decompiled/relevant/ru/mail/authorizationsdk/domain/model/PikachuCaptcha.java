package ru.mail.authorizationsdk.domain.model;

import android.graphics.Bitmap;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lru/mail/authorizationsdk/domain/model/PikachuCaptcha;", "", "captcha", "Landroid/graphics/Bitmap;", "mrcuCookie", "", "xCaptchaId", "<init>", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;)V", "getCaptcha", "()Landroid/graphics/Bitmap;", "getMrcuCookie", "()Ljava/lang/String;", "getXCaptchaId", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PikachuCaptcha {
    public static final int $stable = 8;

    @NotNull
    private final Bitmap captcha;

    @NotNull
    private final String mrcuCookie;

    @NotNull
    private final String xCaptchaId;

    public PikachuCaptcha(@NotNull Bitmap captcha, @NotNull String mrcuCookie, @NotNull String xCaptchaId) {
        Intrinsics.checkNotNullParameter(captcha, "captcha");
        Intrinsics.checkNotNullParameter(mrcuCookie, "mrcuCookie");
        Intrinsics.checkNotNullParameter(xCaptchaId, "xCaptchaId");
        this.captcha = captcha;
        this.mrcuCookie = mrcuCookie;
        this.xCaptchaId = xCaptchaId;
    }

    @NotNull
    public final Bitmap getCaptcha() {
        return this.captcha;
    }

    @NotNull
    public final String getMrcuCookie() {
        return this.mrcuCookie;
    }

    @NotNull
    public final String getXCaptchaId() {
        return this.xCaptchaId;
    }
}
