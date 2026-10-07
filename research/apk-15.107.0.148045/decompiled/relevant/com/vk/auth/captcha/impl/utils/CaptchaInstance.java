package com.vk.auth.captcha.impl.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b3\b\u0080\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0018\u001a\u00020\tHÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0012J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u0012J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u0012Jv\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b!\u0010\u0012J\u0010\u0010\"\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\"\u0010#J\u001a\u0010%\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b%\u0010&R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010\u0012R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b*\u0010+\u001a\u0004\b,\u0010\u0014R\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b-\u0010+\u001a\u0004\b.\u0010\u0014R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b/\u00100\u001a\u0004\b1\u0010\u0017R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b\n\u0010\u0019R\u0017\u0010\u000b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b4\u0010(\u001a\u0004\b5\u0010\u0012R\u0019\u0010\f\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b\f\u0010\u001cR\u0019\u0010\r\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b8\u0010(\u001a\u0004\b9\u0010\u0012R\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b:\u0010(\u001a\u0004\b;\u0010\u0012¨\u0006<"}, d2 = {"Lcom/vk/auth/captcha/impl/utils/CaptchaInstance;", "", "", "img", "", "height", "width", "", "ratio", "", "isRefreshEnabled", "captchaSid", "isSoundCaptcha", "captchaTrack", "token", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "component1", "()Ljava/lang/String;", "component2", "()Ljava/lang/Integer;", "component3", "component4", "()Ljava/lang/Double;", "component5", "()Z", "component6", "component7", "()Ljava/lang/Boolean;", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/auth/captcha/impl/utils/CaptchaInstance;", "toString", "hashCode", "()I", "other", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmoca", "Ljava/lang/String;", "getImg", "lpmiahctpackvmocb", "Ljava/lang/Integer;", "getHeight", "lpmiahctpackvmocc", "getWidth", "lpmiahctpackvmocd", "Ljava/lang/Double;", "getRatio", "lpmiahctpackvmoce", "Z", "lpmiahctpackvmocf", "getCaptchaSid", "lpmiahctpackvmocg", "Ljava/lang/Boolean;", "lpmiahctpackvmoch", "getCaptchaTrack", "lpmiahctpackvmoci", "getToken", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class CaptchaInstance {

    /* JADX INFO: renamed from: lpmiahctpackvmoca, reason: from kotlin metadata */
    @NotNull
    private final String img;

    /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
    @Nullable
    private final Integer height;

    /* JADX INFO: renamed from: lpmiahctpackvmocc, reason: from kotlin metadata */
    @Nullable
    private final Integer width;

    /* JADX INFO: renamed from: lpmiahctpackvmocd, reason: from kotlin metadata */
    @Nullable
    private final Double ratio;

    /* JADX INFO: renamed from: lpmiahctpackvmoce, reason: from kotlin metadata */
    private final boolean isRefreshEnabled;

    /* JADX INFO: renamed from: lpmiahctpackvmocf, reason: from kotlin metadata */
    @NotNull
    private final String captchaSid;

    /* JADX INFO: renamed from: lpmiahctpackvmocg, reason: from kotlin metadata */
    @Nullable
    private final Boolean isSoundCaptcha;

    /* JADX INFO: renamed from: lpmiahctpackvmoch, reason: from kotlin metadata */
    @Nullable
    private final String captchaTrack;

    /* JADX INFO: renamed from: lpmiahctpackvmoci, reason: from kotlin metadata */
    @Nullable
    private final String token;

    public CaptchaInstance(@NotNull String img, @Nullable Integer num, @Nullable Integer num2, @Nullable Double d10, boolean z10, @NotNull String captchaSid, @Nullable Boolean bool, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(captchaSid, "captchaSid");
        this.img = img;
        this.height = num;
        this.width = num2;
        this.ratio = d10;
        this.isRefreshEnabled = z10;
        this.captchaSid = captchaSid;
        this.isSoundCaptcha = bool;
        this.captchaTrack = str;
        this.token = str2;
    }

    public static /* synthetic */ CaptchaInstance copy$default(CaptchaInstance captchaInstance, String str, Integer num, Integer num2, Double d10, boolean z10, String str2, Boolean bool, String str3, String str4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = captchaInstance.img;
        }
        if ((i10 & 2) != 0) {
            num = captchaInstance.height;
        }
        if ((i10 & 4) != 0) {
            num2 = captchaInstance.width;
        }
        if ((i10 & 8) != 0) {
            d10 = captchaInstance.ratio;
        }
        if ((i10 & 16) != 0) {
            z10 = captchaInstance.isRefreshEnabled;
        }
        if ((i10 & 32) != 0) {
            str2 = captchaInstance.captchaSid;
        }
        if ((i10 & 64) != 0) {
            bool = captchaInstance.isSoundCaptcha;
        }
        if ((i10 & 128) != 0) {
            str3 = captchaInstance.captchaTrack;
        }
        if ((i10 & 256) != 0) {
            str4 = captchaInstance.token;
        }
        String str5 = str3;
        String str6 = str4;
        String str7 = str2;
        Boolean bool2 = bool;
        boolean z11 = z10;
        Integer num3 = num2;
        return captchaInstance.copy(str, num, num3, d10, z11, str7, bool2, str5, str6);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getImg() {
        return this.img;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getHeight() {
        return this.height;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getWidth() {
        return this.width;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Double getRatio() {
        return this.ratio;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final boolean getIsRefreshEnabled() {
        return this.isRefreshEnabled;
    }

    @NotNull
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getCaptchaSid() {
        return this.captchaSid;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Boolean getIsSoundCaptcha() {
        return this.isSoundCaptcha;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getCaptchaTrack() {
        return this.captchaTrack;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    @NotNull
    public final CaptchaInstance copy(@NotNull String img, @Nullable Integer height, @Nullable Integer width, @Nullable Double ratio, boolean isRefreshEnabled, @NotNull String captchaSid, @Nullable Boolean isSoundCaptcha, @Nullable String captchaTrack, @Nullable String token) {
        Intrinsics.checkNotNullParameter(img, "img");
        Intrinsics.checkNotNullParameter(captchaSid, "captchaSid");
        return new CaptchaInstance(img, height, width, ratio, isRefreshEnabled, captchaSid, isSoundCaptcha, captchaTrack, token);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CaptchaInstance)) {
            return false;
        }
        CaptchaInstance captchaInstance = (CaptchaInstance) other;
        return Intrinsics.areEqual(this.img, captchaInstance.img) && Intrinsics.areEqual(this.height, captchaInstance.height) && Intrinsics.areEqual(this.width, captchaInstance.width) && Intrinsics.areEqual((Object) this.ratio, (Object) captchaInstance.ratio) && this.isRefreshEnabled == captchaInstance.isRefreshEnabled && Intrinsics.areEqual(this.captchaSid, captchaInstance.captchaSid) && Intrinsics.areEqual(this.isSoundCaptcha, captchaInstance.isSoundCaptcha) && Intrinsics.areEqual(this.captchaTrack, captchaInstance.captchaTrack) && Intrinsics.areEqual(this.token, captchaInstance.token);
    }

    @NotNull
    public final String getCaptchaSid() {
        return this.captchaSid;
    }

    @Nullable
    public final String getCaptchaTrack() {
        return this.captchaTrack;
    }

    @Nullable
    public final Integer getHeight() {
        return this.height;
    }

    @NotNull
    public final String getImg() {
        return this.img;
    }

    @Nullable
    public final Double getRatio() {
        return this.ratio;
    }

    @Nullable
    public final String getToken() {
        return this.token;
    }

    @Nullable
    public final Integer getWidth() {
        return this.width;
    }

    public int hashCode() {
        int iHashCode = this.img.hashCode() * 31;
        Integer num = this.height;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.width;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Double d10 = this.ratio;
        int iHashCode4 = (this.captchaSid.hashCode() + ((Boolean.hashCode(this.isRefreshEnabled) + ((iHashCode3 + (d10 == null ? 0 : d10.hashCode())) * 31)) * 31)) * 31;
        Boolean bool = this.isSoundCaptcha;
        int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.captchaTrack;
        int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.token;
        return iHashCode6 + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isRefreshEnabled() {
        return this.isRefreshEnabled;
    }

    @Nullable
    public final Boolean isSoundCaptcha() {
        return this.isSoundCaptcha;
    }

    @NotNull
    public String toString() {
        return "CaptchaInstance(img=" + this.img + ", height=" + this.height + ", width=" + this.width + ", ratio=" + this.ratio + ", isRefreshEnabled=" + this.isRefreshEnabled + ", captchaSid=" + this.captchaSid + ", isSoundCaptcha=" + this.isSoundCaptcha + ", captchaTrack=" + this.captchaTrack + ", token=" + this.token + ')';
    }

    public /* synthetic */ CaptchaInstance(String str, Integer num, Integer num2, Double d10, boolean z10, String str2, Boolean bool, String str3, String str4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : num2, (i10 & 8) != 0 ? null : d10, z10, str2, bool, str3, str4);
    }
}
