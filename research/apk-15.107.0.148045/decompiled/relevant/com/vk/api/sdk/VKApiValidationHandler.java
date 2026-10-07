package com.vk.api.sdk;

import com.google.android.gms.ads.RequestConfiguration;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.dto.common.id.UserId;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0006\u0016\u0017\u0018\u0019\u001a\u001bJ\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u001e\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H&J\u001e\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000b2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H&J\u0018\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016¨\u0006\u001c"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler;", "", "handleCaptcha", "", "captcha", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "cb", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "handleValidation", "validationUrl", "", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "handleConfirm", "confirmationText", "", "tryToHandleException", "ex", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "apiManager", "Lcom/vk/api/sdk/VKApiManager;", "handleCaptchaSolved", "Callback", "Credentials", "ValidationLock", "Captcha", LudwigCaptchaResult.RESULT_KEY, "CaptchaResolver", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface VKApiValidationHandler {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes3.dex */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0015\u0010\u0011\u001a\u00020\u00102\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u0004\u0018\u00018\u0000X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "lock", "Lcom/vk/api/sdk/VKApiValidationHandler$ValidationLock;", "<init>", "(Lcom/vk/api/sdk/VKApiValidationHandler$ValidationLock;)V", "getLock", "()Lcom/vk/api/sdk/VKApiValidationHandler$ValidationLock;", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "cancel", "", "submit", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static class Callback<T> {

        @NotNull
        private final ValidationLock lock;

        @Nullable
        private volatile T value;

        public Callback(@NotNull ValidationLock lock) {
            Intrinsics.checkNotNullParameter(lock, "lock");
            this.lock = lock;
        }

        public void cancel() {
            this.lock.release();
        }

        @NotNull
        public final ValidationLock getLock() {
            return this.lock;
        }

        @Nullable
        public final T getValue() {
            return this.value;
        }

        public final void setValue(@Nullable T t10) {
            this.value = t10;
        }

        public void submit(T value) {
            this.value = value;
            this.lock.release();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResolver;", "", "captchaEnabled", "", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface CaptchaResolver {
        boolean captchaEnabled();
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes3.dex */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "", AccountManagerRepositoryImpl.SECRET_ARG, "", "token", "uid", "Lcom/vk/dto/common/id/UserId;", "expiresInSec", "", "createdMs", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lcom/vk/dto/common/id/UserId;IJ)V", "getSecret", "()Ljava/lang/String;", "getToken", "getUid", "()Lcom/vk/dto/common/id/UserId;", "getExpiresInSec", "()I", "getCreatedMs", "()J", "isValid", "", "()Z", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Credentials {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);

        @NotNull
        private static final Credentials EMPTY = new Credentials("", "", null, 0, 0);
        private final long createdMs;
        private final int expiresInSec;
        private final boolean isValid;

        @Nullable
        private final String secret;

        @Nullable
        private final String token;

        @Nullable
        private final UserId uid;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$Credentials$Companion;", "", "<init>", "()V", "EMPTY", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "getEMPTY", "()Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final Credentials getEMPTY() {
                return Credentials.EMPTY;
            }

            private Companion() {
            }
        }

        public Credentials(@Nullable String str, @Nullable String str2, @Nullable UserId userId, int i10, long j10) {
            this.secret = str;
            this.token = str2;
            this.uid = userId;
            this.expiresInSec = i10;
            this.createdMs = j10;
            this.isValid = true ^ (str2 == null || StringsKt.isBlank(str2));
        }

        public final long getCreatedMs() {
            return this.createdMs;
        }

        public final int getExpiresInSec() {
            return this.expiresInSec;
        }

        @Nullable
        public final String getSecret() {
            return this.secret;
        }

        @Nullable
        public final String getToken() {
            return this.token;
        }

        @Nullable
        public final UserId getUid() {
            return this.uid;
        }

        /* JADX INFO: renamed from: isValid, reason: from getter */
        public final boolean getIsValid() {
            return this.isValid;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bR\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$ValidationLock;", "", "<init>", "()V", "latchRef", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/CountDownLatch;", "await", "", "acquire", "", "release", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class ValidationLock {

        @NotNull
        private final AtomicReference<CountDownLatch> latchRef = new AtomicReference<>();

        public final boolean acquire() {
            return androidx.ads.identifier.a.a(this.latchRef, null, new CountDownLatch(1));
        }

        public final void await() throws InterruptedException {
            CountDownLatch countDownLatch = this.latchRef.get();
            if (countDownLatch != null) {
                countDownLatch.await();
            }
        }

        public final void release() {
            CountDownLatch andSet = this.latchRef.getAndSet(null);
            if (andSet == null) {
                throw new NullPointerException("Latch is null!");
            }
            andSet.countDown();
        }
    }

    void handleCaptcha(@NotNull Captcha captcha, @NotNull Callback<CaptchaResult> cb2);

    void handleCaptchaSolved();

    void handleConfirm(@NotNull String confirmationText, @NotNull Callback<Boolean> cb2);

    void handleValidation(@NotNull String validationUrl, @NotNull Callback<Credentials> cb2);

    void tryToHandleException(@NotNull VKApiExecutionException ex, @NotNull VKApiManager apiManager) throws VKApiExecutionException;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes3.dex */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J3\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$CaptchaResult;", "", "key", "", "isSoundCaptcha", "", "isNotRobotCaptcha", "isHitmanChallenge", "<init>", "(Ljava/lang/String;ZZZ)V", "getKey", "()Ljava/lang/String;", "()Z", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class CaptchaResult {
        private final boolean isHitmanChallenge;
        private final boolean isNotRobotCaptcha;
        private final boolean isSoundCaptcha;

        @Nullable
        private final String key;

        public CaptchaResult(@Nullable String str, boolean z10, boolean z11, boolean z12) {
            this.key = str;
            this.isSoundCaptcha = z10;
            this.isNotRobotCaptcha = z11;
            this.isHitmanChallenge = z12;
        }

        public static /* synthetic */ CaptchaResult copy$default(CaptchaResult captchaResult, String str, boolean z10, boolean z11, boolean z12, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = captchaResult.key;
            }
            if ((i10 & 2) != 0) {
                z10 = captchaResult.isSoundCaptcha;
            }
            if ((i10 & 4) != 0) {
                z11 = captchaResult.isNotRobotCaptcha;
            }
            if ((i10 & 8) != 0) {
                z12 = captchaResult.isHitmanChallenge;
            }
            return captchaResult.copy(str, z10, z11, z12);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getKey() {
            return this.key;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsSoundCaptcha() {
            return this.isSoundCaptcha;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getIsNotRobotCaptcha() {
            return this.isNotRobotCaptcha;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getIsHitmanChallenge() {
            return this.isHitmanChallenge;
        }

        @NotNull
        public final CaptchaResult copy(@Nullable String key, boolean isSoundCaptcha, boolean isNotRobotCaptcha, boolean isHitmanChallenge) {
            return new CaptchaResult(key, isSoundCaptcha, isNotRobotCaptcha, isHitmanChallenge);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CaptchaResult)) {
                return false;
            }
            CaptchaResult captchaResult = (CaptchaResult) other;
            return Intrinsics.areEqual(this.key, captchaResult.key) && this.isSoundCaptcha == captchaResult.isSoundCaptcha && this.isNotRobotCaptcha == captchaResult.isNotRobotCaptcha && this.isHitmanChallenge == captchaResult.isHitmanChallenge;
        }

        @Nullable
        public final String getKey() {
            return this.key;
        }

        public int hashCode() {
            String str = this.key;
            return ((((((str == null ? 0 : str.hashCode()) * 31) + Boolean.hashCode(this.isSoundCaptcha)) * 31) + Boolean.hashCode(this.isNotRobotCaptcha)) * 31) + Boolean.hashCode(this.isHitmanChallenge);
        }

        public final boolean isHitmanChallenge() {
            return this.isHitmanChallenge;
        }

        public final boolean isNotRobotCaptcha() {
            return this.isNotRobotCaptcha;
        }

        public final boolean isSoundCaptcha() {
            return this.isSoundCaptcha;
        }

        @NotNull
        public String toString() {
            return "CaptchaResult(key=" + this.key + ", isSoundCaptcha=" + this.isSoundCaptcha + ", isNotRobotCaptcha=" + this.isNotRobotCaptcha + ", isHitmanChallenge=" + this.isHitmanChallenge + ")";
        }

        public /* synthetic */ CaptchaResult(String str, boolean z10, boolean z11, boolean z12, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, z10, z11, (i10 & 8) != 0 ? false : z12);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes3.dex */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b1\b\u0086\b\u0018\u00002\u00020\u0001B\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010*\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0018J\u0010\u0010+\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u001cJ\t\u0010,\u001a\u00020\nHÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\u0010\u0010.\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010/\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0003HÆ\u0003J¤\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\n2\b\u00108\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00109\u001a\u00020\u0005HÖ\u0001J\t\u0010:\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0018R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u001d\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u001eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016R\u0015\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\f\u0010 R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0016R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0016R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0016R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0016R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0016¨\u0006;"}, d2 = {"Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "", "img", "", "height", "", "width", "ratio", "", "isRefreshEnabled", "", "captchaSid", "isSoundCaptcha", "captchaTrack", "token", "redirectUri", "hitmanChallengeUrl", "hitmanChallengeDomain", "requestDomain", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getImg", "()Ljava/lang/String;", "getHeight", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getWidth", "getRatio", "()Ljava/lang/Double;", "Ljava/lang/Double;", "()Z", "getCaptchaSid", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getCaptchaTrack", "getToken", "getRedirectUri", "getHitmanChallengeUrl", "getHitmanChallengeDomain", "getRequestDomain", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Double;ZLjava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "equals", "other", "hashCode", "toString", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Captcha {

        @NotNull
        private final String captchaSid;

        @Nullable
        private final String captchaTrack;

        @Nullable
        private final Integer height;

        @Nullable
        private final String hitmanChallengeDomain;

        @Nullable
        private final String hitmanChallengeUrl;

        @NotNull
        private final String img;
        private final boolean isRefreshEnabled;

        @Nullable
        private final Boolean isSoundCaptcha;

        @Nullable
        private final Double ratio;

        @Nullable
        private final String redirectUri;

        @Nullable
        private final String requestDomain;

        @Nullable
        private final String token;

        @Nullable
        private final Integer width;

        public Captcha(@NotNull String img, @Nullable Integer num, @Nullable Integer num2, @Nullable Double d10, boolean z10, @NotNull String captchaSid, @Nullable Boolean bool, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
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
            this.redirectUri = str3;
            this.hitmanChallengeUrl = str4;
            this.hitmanChallengeDomain = str5;
            this.requestDomain = str6;
        }

        public static /* synthetic */ Captcha copy$default(Captcha captcha, String str, Integer num, Integer num2, Double d10, boolean z10, String str2, Boolean bool, String str3, String str4, String str5, String str6, String str7, String str8, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = captcha.img;
            }
            return captcha.copy(str, (i10 & 2) != 0 ? captcha.height : num, (i10 & 4) != 0 ? captcha.width : num2, (i10 & 8) != 0 ? captcha.ratio : d10, (i10 & 16) != 0 ? captcha.isRefreshEnabled : z10, (i10 & 32) != 0 ? captcha.captchaSid : str2, (i10 & 64) != 0 ? captcha.isSoundCaptcha : bool, (i10 & 128) != 0 ? captcha.captchaTrack : str3, (i10 & 256) != 0 ? captcha.token : str4, (i10 & 512) != 0 ? captcha.redirectUri : str5, (i10 & 1024) != 0 ? captcha.hitmanChallengeUrl : str6, (i10 & 2048) != 0 ? captcha.hitmanChallengeDomain : str7, (i10 & 4096) != 0 ? captcha.requestDomain : str8);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getImg() {
            return this.img;
        }

        @Nullable
        /* JADX INFO: renamed from: component10, reason: from getter */
        public final String getRedirectUri() {
            return this.redirectUri;
        }

        @Nullable
        /* JADX INFO: renamed from: component11, reason: from getter */
        public final String getHitmanChallengeUrl() {
            return this.hitmanChallengeUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component12, reason: from getter */
        public final String getHitmanChallengeDomain() {
            return this.hitmanChallengeDomain;
        }

        @Nullable
        /* JADX INFO: renamed from: component13, reason: from getter */
        public final String getRequestDomain() {
            return this.requestDomain;
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
        public final Captcha copy(@NotNull String img, @Nullable Integer height, @Nullable Integer width, @Nullable Double ratio, boolean isRefreshEnabled, @NotNull String captchaSid, @Nullable Boolean isSoundCaptcha, @Nullable String captchaTrack, @Nullable String token, @Nullable String redirectUri, @Nullable String hitmanChallengeUrl, @Nullable String hitmanChallengeDomain, @Nullable String requestDomain) {
            Intrinsics.checkNotNullParameter(img, "img");
            Intrinsics.checkNotNullParameter(captchaSid, "captchaSid");
            return new Captcha(img, height, width, ratio, isRefreshEnabled, captchaSid, isSoundCaptcha, captchaTrack, token, redirectUri, hitmanChallengeUrl, hitmanChallengeDomain, requestDomain);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Captcha)) {
                return false;
            }
            Captcha captcha = (Captcha) other;
            return Intrinsics.areEqual(this.img, captcha.img) && Intrinsics.areEqual(this.height, captcha.height) && Intrinsics.areEqual(this.width, captcha.width) && Intrinsics.areEqual((Object) this.ratio, (Object) captcha.ratio) && this.isRefreshEnabled == captcha.isRefreshEnabled && Intrinsics.areEqual(this.captchaSid, captcha.captchaSid) && Intrinsics.areEqual(this.isSoundCaptcha, captcha.isSoundCaptcha) && Intrinsics.areEqual(this.captchaTrack, captcha.captchaTrack) && Intrinsics.areEqual(this.token, captcha.token) && Intrinsics.areEqual(this.redirectUri, captcha.redirectUri) && Intrinsics.areEqual(this.hitmanChallengeUrl, captcha.hitmanChallengeUrl) && Intrinsics.areEqual(this.hitmanChallengeDomain, captcha.hitmanChallengeDomain) && Intrinsics.areEqual(this.requestDomain, captcha.requestDomain);
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

        @Nullable
        public final String getHitmanChallengeDomain() {
            return this.hitmanChallengeDomain;
        }

        @Nullable
        public final String getHitmanChallengeUrl() {
            return this.hitmanChallengeUrl;
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
        public final String getRedirectUri() {
            return this.redirectUri;
        }

        @Nullable
        public final String getRequestDomain() {
            return this.requestDomain;
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
            int iHashCode4 = (((((iHashCode3 + (d10 == null ? 0 : d10.hashCode())) * 31) + Boolean.hashCode(this.isRefreshEnabled)) * 31) + this.captchaSid.hashCode()) * 31;
            Boolean bool = this.isSoundCaptcha;
            int iHashCode5 = (iHashCode4 + (bool == null ? 0 : bool.hashCode())) * 31;
            String str = this.captchaTrack;
            int iHashCode6 = (iHashCode5 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.token;
            int iHashCode7 = (iHashCode6 + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.redirectUri;
            int iHashCode8 = (iHashCode7 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.hitmanChallengeUrl;
            int iHashCode9 = (iHashCode8 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.hitmanChallengeDomain;
            int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
            String str6 = this.requestDomain;
            return iHashCode10 + (str6 != null ? str6.hashCode() : 0);
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
            return "Captcha(img=" + this.img + ", height=" + this.height + ", width=" + this.width + ", ratio=" + this.ratio + ", isRefreshEnabled=" + this.isRefreshEnabled + ", captchaSid=" + this.captchaSid + ", isSoundCaptcha=" + this.isSoundCaptcha + ", captchaTrack=" + this.captchaTrack + ", token=" + this.token + ", redirectUri=" + this.redirectUri + ", hitmanChallengeUrl=" + this.hitmanChallengeUrl + ", hitmanChallengeDomain=" + this.hitmanChallengeDomain + ", requestDomain=" + this.requestDomain + ")";
        }

        public /* synthetic */ Captcha(String str, Integer num, Integer num2, Double d10, boolean z10, String str2, Boolean bool, String str3, String str4, String str5, String str6, String str7, String str8, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, num, num2, d10, z10, str2, bool, str3, str4, str5, (i10 & 1024) != 0 ? null : str6, (i10 & 2048) != 0 ? null : str7, (i10 & 4096) != 0 ? null : str8);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes3.dex */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        public static void tryToHandleException(@NotNull VKApiValidationHandler vKApiValidationHandler, @NotNull VKApiExecutionException ex, @NotNull VKApiManager apiManager) throws VKApiExecutionException {
            Intrinsics.checkNotNullParameter(ex, "ex");
            Intrinsics.checkNotNullParameter(apiManager, "apiManager");
            throw ex;
        }

        public static void handleCaptchaSolved(@NotNull VKApiValidationHandler vKApiValidationHandler) {
        }
    }
}
