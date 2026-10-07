package com.vk.auth.captcha.impl.image;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b$\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\bHÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014JB\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\bHÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u000eJ\u0010\u0010\u0019\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0019\u0010\u0014J\u001a\u0010\u001b\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001b\u0010\u001cR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u000e\"\u0004\b \u0010!R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b\u0005\u0010\u0010R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0012R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010\u0014R\u0017\u0010\n\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b*\u0010(\u001a\u0004\b+\u0010\u0014¨\u0006,"}, d2 = {"Lcom/vk/auth/captcha/impl/image/ImageCaptchaArguments;", "", "", "initUrl", "", "isRefreshEnabled", "", "ratio", "", "width", "height", "<init>", "(Ljava/lang/String;ZDII)V", "component1", "()Ljava/lang/String;", "component2", "()Z", "component3", "()D", "component4", "()I", "component5", "copy", "(Ljava/lang/String;ZDII)Lcom/vk/auth/captcha/impl/image/ImageCaptchaArguments;", "toString", "hashCode", "other", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmoca", "Ljava/lang/String;", "getInitUrl", "setInitUrl", "(Ljava/lang/String;)V", "lpmiahctpackvmocb", "Z", "lpmiahctpackvmocc", "D", "getRatio", "lpmiahctpackvmocd", "I", "getWidth", "lpmiahctpackvmoce", "getHeight", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class ImageCaptchaArguments {

    /* JADX INFO: renamed from: lpmiahctpackvmoca, reason: from kotlin metadata */
    @NotNull
    private String initUrl;

    /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
    private final boolean isRefreshEnabled;

    /* JADX INFO: renamed from: lpmiahctpackvmocc, reason: from kotlin metadata */
    private final double ratio;

    /* JADX INFO: renamed from: lpmiahctpackvmocd, reason: from kotlin metadata */
    private final int width;

    /* JADX INFO: renamed from: lpmiahctpackvmoce, reason: from kotlin metadata */
    private final int height;

    public ImageCaptchaArguments(@NotNull String initUrl, boolean z10, double d10, int i10, int i11) {
        Intrinsics.checkNotNullParameter(initUrl, "initUrl");
        this.initUrl = initUrl;
        this.isRefreshEnabled = z10;
        this.ratio = d10;
        this.width = i10;
        this.height = i11;
    }

    public static /* synthetic */ ImageCaptchaArguments copy$default(ImageCaptchaArguments imageCaptchaArguments, String str, boolean z10, double d10, int i10, int i11, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            str = imageCaptchaArguments.initUrl;
        }
        if ((i12 & 2) != 0) {
            z10 = imageCaptchaArguments.isRefreshEnabled;
        }
        if ((i12 & 4) != 0) {
            d10 = imageCaptchaArguments.ratio;
        }
        if ((i12 & 8) != 0) {
            i10 = imageCaptchaArguments.width;
        }
        if ((i12 & 16) != 0) {
            i11 = imageCaptchaArguments.height;
        }
        double d11 = d10;
        return imageCaptchaArguments.copy(str, z10, d11, i10, i11);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getInitUrl() {
        return this.initUrl;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsRefreshEnabled() {
        return this.isRefreshEnabled;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final double getRatio() {
        return this.ratio;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    @NotNull
    public final ImageCaptchaArguments copy(@NotNull String initUrl, boolean isRefreshEnabled, double ratio, int width, int height) {
        Intrinsics.checkNotNullParameter(initUrl, "initUrl");
        return new ImageCaptchaArguments(initUrl, isRefreshEnabled, ratio, width, height);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageCaptchaArguments)) {
            return false;
        }
        ImageCaptchaArguments imageCaptchaArguments = (ImageCaptchaArguments) other;
        return Intrinsics.areEqual(this.initUrl, imageCaptchaArguments.initUrl) && this.isRefreshEnabled == imageCaptchaArguments.isRefreshEnabled && Double.compare(this.ratio, imageCaptchaArguments.ratio) == 0 && this.width == imageCaptchaArguments.width && this.height == imageCaptchaArguments.height;
    }

    public final int getHeight() {
        return this.height;
    }

    @NotNull
    public final String getInitUrl() {
        return this.initUrl;
    }

    public final double getRatio() {
        return this.ratio;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return Integer.hashCode(this.height) + ((Integer.hashCode(this.width) + ((Double.hashCode(this.ratio) + ((Boolean.hashCode(this.isRefreshEnabled) + (this.initUrl.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final boolean isRefreshEnabled() {
        return this.isRefreshEnabled;
    }

    public final void setInitUrl(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.initUrl = str;
    }

    @NotNull
    public String toString() {
        return "ImageCaptchaArguments(initUrl=" + this.initUrl + ", isRefreshEnabled=" + this.isRefreshEnabled + ", ratio=" + this.ratio + ", width=" + this.width + ", height=" + this.height + ')';
    }
}
