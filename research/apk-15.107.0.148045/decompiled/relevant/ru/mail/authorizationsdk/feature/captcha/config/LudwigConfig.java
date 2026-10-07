package ru.mail.authorizationsdk.feature.captcha.config;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J'\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/authorizationsdk/feature/captcha/config/LudwigConfig;", "", "isEnabled", "", "isDomStorageEnabled", "isTextZoomEnabled", "<init>", "(ZZZ)V", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LudwigConfig {
    public static final int $stable = 0;
    private final boolean isDomStorageEnabled;
    private final boolean isEnabled;
    private final boolean isTextZoomEnabled;

    public LudwigConfig() {
        this(false, false, false, 7, null);
    }

    public static /* synthetic */ LudwigConfig copy$default(LudwigConfig ludwigConfig, boolean z10, boolean z11, boolean z12, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = ludwigConfig.isEnabled;
        }
        if ((i10 & 2) != 0) {
            z11 = ludwigConfig.isDomStorageEnabled;
        }
        if ((i10 & 4) != 0) {
            z12 = ludwigConfig.isTextZoomEnabled;
        }
        return ludwigConfig.copy(z10, z11, z12);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsDomStorageEnabled() {
        return this.isDomStorageEnabled;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsTextZoomEnabled() {
        return this.isTextZoomEnabled;
    }

    @NotNull
    public final LudwigConfig copy(boolean isEnabled, boolean isDomStorageEnabled, boolean isTextZoomEnabled) {
        return new LudwigConfig(isEnabled, isDomStorageEnabled, isTextZoomEnabled);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LudwigConfig)) {
            return false;
        }
        LudwigConfig ludwigConfig = (LudwigConfig) other;
        return this.isEnabled == ludwigConfig.isEnabled && this.isDomStorageEnabled == ludwigConfig.isDomStorageEnabled && this.isTextZoomEnabled == ludwigConfig.isTextZoomEnabled;
    }

    public int hashCode() {
        return (((Boolean.hashCode(this.isEnabled) * 31) + Boolean.hashCode(this.isDomStorageEnabled)) * 31) + Boolean.hashCode(this.isTextZoomEnabled);
    }

    public final boolean isDomStorageEnabled() {
        return this.isDomStorageEnabled;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    public final boolean isTextZoomEnabled() {
        return this.isTextZoomEnabled;
    }

    @NotNull
    public String toString() {
        return "LudwigConfig(isEnabled=" + this.isEnabled + ", isDomStorageEnabled=" + this.isDomStorageEnabled + ", isTextZoomEnabled=" + this.isTextZoomEnabled + ")";
    }

    public LudwigConfig(boolean z10, boolean z11, boolean z12) {
        this.isEnabled = z10;
        this.isDomStorageEnabled = z11;
        this.isTextZoomEnabled = z12;
    }

    public /* synthetic */ LudwigConfig(boolean z10, boolean z11, boolean z12, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? true : z10, (i10 & 2) != 0 ? false : z11, (i10 & 4) != 0 ? false : z12);
    }
}
