package ru.mail.authorizationsdk.feature.captcha.analytics;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0007\"\u0004\b\n\u0010\t¨\u0006\u000b"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/analytics/AnalyticCompanion;", "", "isCaptchaShowed", "", "isAnyNetworkError", "<init>", "(ZZ)V", "()Z", "setCaptchaShowed", "(Z)V", "setAnyNetworkError", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnalyticCompanion {
    public static final int $stable = 8;
    private boolean isAnyNetworkError;
    private boolean isCaptchaShowed;

    /* JADX WARN: Illegal instructions before constructor call */
    public AnalyticCompanion() {
        boolean z10 = false;
        this(z10, z10, 3, null);
    }

    /* JADX INFO: renamed from: isAnyNetworkError, reason: from getter */
    public final boolean getIsAnyNetworkError() {
        return this.isAnyNetworkError;
    }

    /* JADX INFO: renamed from: isCaptchaShowed, reason: from getter */
    public final boolean getIsCaptchaShowed() {
        return this.isCaptchaShowed;
    }

    public final void setAnyNetworkError(boolean z10) {
        this.isAnyNetworkError = z10;
    }

    public final void setCaptchaShowed(boolean z10) {
        this.isCaptchaShowed = z10;
    }

    public AnalyticCompanion(boolean z10, boolean z11) {
        this.isCaptchaShowed = z10;
        this.isAnyNetworkError = z11;
    }

    public /* synthetic */ AnalyticCompanion(boolean z10, boolean z11, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? false : z10, (i10 & 2) != 0 ? false : z11);
    }
}
