package com.vk.api.sdk.chain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010=\u001a\u00020 R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u001e\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u0010\n\u0002\u0010$\u001a\u0004\b\u001f\u0010!\"\u0004\b\"\u0010#R\u001e\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u0010\n\u0002\u0010+\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u0010\n\u0002\u00102\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00103\u001a\u00020 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<¨\u0006>"}, d2 = {"Lcom/vk/api/sdk/chain/ChainArgs;", "", "<init>", "()V", "captchaSid", "", "getCaptchaSid", "()Ljava/lang/String;", "setCaptchaSid", "(Ljava/lang/String;)V", "captchaKey", "getCaptchaKey", "setCaptchaKey", "captchaSuccessToken", "getCaptchaSuccessToken", "setCaptchaSuccessToken", "captchaTrack", "getCaptchaTrack", "setCaptchaTrack", "hitmanChallengeUrl", "getHitmanChallengeUrl", "setHitmanChallengeUrl", "hitmanChallengeDomain", "getHitmanChallengeDomain", "setHitmanChallengeDomain", "requestDomain", "getRequestDomain", "setRequestDomain", "hitmanChallengeToken", "getHitmanChallengeToken", "setHitmanChallengeToken", "isSoundCaptcha", "", "()Ljava/lang/Boolean;", "setSoundCaptcha", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "captchaAttempt", "", "getCaptchaAttempt", "()Ljava/lang/Integer;", "setCaptchaAttempt", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "captchaTimestamp", "", "getCaptchaTimestamp", "()Ljava/lang/Double;", "setCaptchaTimestamp", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "userConfirmed", "getUserConfirmed", "()Z", "setUserConfirmed", "(Z)V", "retryCount", "getRetryCount", "()I", "setRetryCount", "(I)V", "hasCaptcha", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ChainArgs {

    @Nullable
    private Integer captchaAttempt;

    @Nullable
    private Double captchaTimestamp;

    @Nullable
    private String captchaTrack;

    @Nullable
    private String hitmanChallengeDomain;

    @Nullable
    private String hitmanChallengeToken;

    @Nullable
    private String hitmanChallengeUrl;

    @Nullable
    private Boolean isSoundCaptcha;

    @Nullable
    private String requestDomain;
    private int retryCount;
    private boolean userConfirmed;

    @NotNull
    private String captchaSid = "";

    @NotNull
    private String captchaKey = "";

    @NotNull
    private String captchaSuccessToken = "";

    @Nullable
    public final Integer getCaptchaAttempt() {
        return this.captchaAttempt;
    }

    @NotNull
    public final String getCaptchaKey() {
        return this.captchaKey;
    }

    @NotNull
    public final String getCaptchaSid() {
        return this.captchaSid;
    }

    @NotNull
    public final String getCaptchaSuccessToken() {
        return this.captchaSuccessToken;
    }

    @Nullable
    public final Double getCaptchaTimestamp() {
        return this.captchaTimestamp;
    }

    @Nullable
    public final String getCaptchaTrack() {
        return this.captchaTrack;
    }

    @Nullable
    public final String getHitmanChallengeDomain() {
        return this.hitmanChallengeDomain;
    }

    @Nullable
    public final String getHitmanChallengeToken() {
        return this.hitmanChallengeToken;
    }

    @Nullable
    public final String getHitmanChallengeUrl() {
        return this.hitmanChallengeUrl;
    }

    @Nullable
    public final String getRequestDomain() {
        return this.requestDomain;
    }

    public final int getRetryCount() {
        return this.retryCount;
    }

    public final boolean getUserConfirmed() {
        return this.userConfirmed;
    }

    public final boolean hasCaptcha() {
        if (this.captchaSid.length() > 0) {
            return this.captchaKey.length() > 0 || this.captchaSuccessToken.length() > 0;
        }
        return false;
    }

    @Nullable
    /* JADX INFO: renamed from: isSoundCaptcha, reason: from getter */
    public final Boolean getIsSoundCaptcha() {
        return this.isSoundCaptcha;
    }

    public final void setCaptchaAttempt(@Nullable Integer num) {
        this.captchaAttempt = num;
    }

    public final void setCaptchaKey(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.captchaKey = str;
    }

    public final void setCaptchaSid(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.captchaSid = str;
    }

    public final void setCaptchaSuccessToken(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.captchaSuccessToken = str;
    }

    public final void setCaptchaTimestamp(@Nullable Double d10) {
        this.captchaTimestamp = d10;
    }

    public final void setCaptchaTrack(@Nullable String str) {
        this.captchaTrack = str;
    }

    public final void setHitmanChallengeDomain(@Nullable String str) {
        this.hitmanChallengeDomain = str;
    }

    public final void setHitmanChallengeToken(@Nullable String str) {
        this.hitmanChallengeToken = str;
    }

    public final void setHitmanChallengeUrl(@Nullable String str) {
        this.hitmanChallengeUrl = str;
    }

    public final void setRequestDomain(@Nullable String str) {
        this.requestDomain = str;
    }

    public final void setRetryCount(int i10) {
        this.retryCount = i10;
    }

    public final void setSoundCaptcha(@Nullable Boolean bool) {
        this.isSoundCaptcha = bool;
    }

    public final void setUserConfirmed(boolean z10) {
        this.userConfirmed = z10;
    }
}
