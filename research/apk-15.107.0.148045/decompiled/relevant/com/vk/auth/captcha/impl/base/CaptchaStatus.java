package com.vk.auth.captcha.impl.base;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u000e\u000f\u0010\u0011\u0012J\u0017\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\r\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0005\u0013\u0014\u0015\u0016\u0017¨\u0006\u0018"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "", "newRefreshCountdown", "updateCountdown", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "isCountdownStop", "()Z", "lpmiahctpackvmoca", "I", "getRefreshCountdown", "()I", "refreshCountdown", "Inactive", "Loading", "LoadingError", "Ready", "Checking", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Checking;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Inactive;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Loading;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus$LoadingError;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Ready;", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class CaptchaStatus {

    /* JADX INFO: renamed from: lpmiahctpackvmoca, reason: from kotlin metadata */
    private final int refreshCountdown;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0011\u0010\fJ\u0010\u0010\u0012\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u000eJ\u001a\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013HÖ\u0003¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\fR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Checking;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "input", "", "refreshCountdown", "<init>", "(Ljava/lang/String;I)V", "newRefreshCountdown", "updateCountdown", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "component1", "()Ljava/lang/String;", "component2", "()I", "copy", "(Ljava/lang/String;I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Checking;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmocb", "Ljava/lang/String;", "getInput", "lpmiahctpackvmocc", "I", "getRefreshCountdown", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Checking extends CaptchaStatus {

        /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
        @NotNull
        private final String input;

        /* JADX INFO: renamed from: lpmiahctpackvmocc, reason: from kotlin metadata */
        private final int refreshCountdown;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Checking(@NotNull String input, int i10) {
            super(i10, null);
            Intrinsics.checkNotNullParameter(input, "input");
            this.input = input;
            this.refreshCountdown = i10;
        }

        public static /* synthetic */ Checking copy$default(Checking checking, String str, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = checking.input;
            }
            if ((i11 & 2) != 0) {
                i10 = checking.refreshCountdown;
            }
            return checking.copy(str, i10);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getInput() {
            return this.input;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        @NotNull
        public final Checking copy(@NotNull String input, int refreshCountdown) {
            Intrinsics.checkNotNullParameter(input, "input");
            return new Checking(input, refreshCountdown);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Checking)) {
                return false;
            }
            Checking checking = (Checking) other;
            return Intrinsics.areEqual(this.input, checking.input) && this.refreshCountdown == checking.refreshCountdown;
        }

        @NotNull
        public final String getInput() {
            return this.input;
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        public int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        public int hashCode() {
            return Integer.hashCode(this.refreshCountdown) + (this.input.hashCode() * 31);
        }

        @NotNull
        public String toString() {
            return "Checking(input=" + this.input + ", refreshCountdown=" + this.refreshCountdown + ')';
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        @NotNull
        public CaptchaStatus updateCountdown(int newRefreshCountdown) {
            return copy$default(this, null, newRefreshCountdown, 1, null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Inactive;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "refreshCountdown", "<init>", "(I)V", "newRefreshCountdown", "updateCountdown", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "component1", "()I", "copy", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Inactive;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmocb", "I", "getRefreshCountdown", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Inactive extends CaptchaStatus {

        /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
        private final int refreshCountdown;

        public Inactive(int i10) {
            super(i10, null);
            this.refreshCountdown = i10;
        }

        public static /* synthetic */ Inactive copy$default(Inactive inactive, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = inactive.refreshCountdown;
            }
            return inactive.copy(i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        @NotNull
        public final Inactive copy(int refreshCountdown) {
            return new Inactive(refreshCountdown);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Inactive) && this.refreshCountdown == ((Inactive) other).refreshCountdown;
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        public int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        public int hashCode() {
            return Integer.hashCode(this.refreshCountdown);
        }

        @NotNull
        public String toString() {
            return "Inactive(refreshCountdown=" + this.refreshCountdown + ')';
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        @NotNull
        public CaptchaStatus updateCountdown(int newRefreshCountdown) {
            return copy(newRefreshCountdown);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Loading;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "refreshCountdown", "<init>", "(I)V", "newRefreshCountdown", "updateCountdown", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "component1", "()I", "copy", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Loading;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmocb", "I", "getRefreshCountdown", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Loading extends CaptchaStatus {

        /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
        private final int refreshCountdown;

        public Loading(int i10) {
            super(i10, null);
            this.refreshCountdown = i10;
        }

        public static /* synthetic */ Loading copy$default(Loading loading, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = loading.refreshCountdown;
            }
            return loading.copy(i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        @NotNull
        public final Loading copy(int refreshCountdown) {
            return new Loading(refreshCountdown);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Loading) && this.refreshCountdown == ((Loading) other).refreshCountdown;
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        public int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        public int hashCode() {
            return Integer.hashCode(this.refreshCountdown);
        }

        @NotNull
        public String toString() {
            return "Loading(refreshCountdown=" + this.refreshCountdown + ')';
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        @NotNull
        public CaptchaStatus updateCountdown(int newRefreshCountdown) {
            return copy(newRefreshCountdown);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\t\u0010\nJ\u001a\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0010\u0010\nJ\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\n¨\u0006\u0019"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaStatus$LoadingError;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "refreshCountdown", "<init>", "(I)V", "newRefreshCountdown", "updateCountdown", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "component1", "()I", "copy", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus$LoadingError;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmocb", "I", "getRefreshCountdown", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class LoadingError extends CaptchaStatus {

        /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
        private final int refreshCountdown;

        public LoadingError(int i10) {
            super(i10, null);
            this.refreshCountdown = i10;
        }

        public static /* synthetic */ LoadingError copy$default(LoadingError loadingError, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                i10 = loadingError.refreshCountdown;
            }
            return loadingError.copy(i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        @NotNull
        public final LoadingError copy(int refreshCountdown) {
            return new LoadingError(refreshCountdown);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LoadingError) && this.refreshCountdown == ((LoadingError) other).refreshCountdown;
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        public int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        public int hashCode() {
            return Integer.hashCode(this.refreshCountdown);
        }

        @NotNull
        public String toString() {
            return "LoadingError(refreshCountdown=" + this.refreshCountdown + ')';
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        @NotNull
        public CaptchaStatus updateCountdown(int newRefreshCountdown) {
            return copy(newRefreshCountdown);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\r\u0010\u000eJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0012\u001a\u00020\u0011HÖ\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u000eJ\u001a\u0010\u0017\u001a\u00020\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015HÖ\u0003¢\u0006\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0003\u0010\fR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Ready;", "Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "", "isPlaying", "", "refreshCountdown", "<init>", "(ZI)V", "newRefreshCountdown", "updateCountdown", "(I)Lcom/vk/auth/captcha/impl/base/CaptchaStatus;", "component1", "()Z", "component2", "()I", "copy", "(ZI)Lcom/vk/auth/captcha/impl/base/CaptchaStatus$Ready;", "", "toString", "()Ljava/lang/String;", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "lpmiahctpackvmocb", "Z", "lpmiahctpackvmocc", "I", "getRefreshCountdown", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final /* data */ class Ready extends CaptchaStatus {

        /* JADX INFO: renamed from: lpmiahctpackvmocb, reason: from kotlin metadata */
        private final boolean isPlaying;

        /* JADX INFO: renamed from: lpmiahctpackvmocc, reason: from kotlin metadata */
        private final int refreshCountdown;

        public Ready(boolean z10, int i10) {
            super(i10, null);
            this.isPlaying = z10;
            this.refreshCountdown = i10;
        }

        public static /* synthetic */ Ready copy$default(Ready ready, boolean z10, int i10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                z10 = ready.isPlaying;
            }
            if ((i11 & 2) != 0) {
                i10 = ready.refreshCountdown;
            }
            return ready.copy(z10, i10);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsPlaying() {
            return this.isPlaying;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        @NotNull
        public final Ready copy(boolean isPlaying, int refreshCountdown) {
            return new Ready(isPlaying, refreshCountdown);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Ready)) {
                return false;
            }
            Ready ready = (Ready) other;
            return this.isPlaying == ready.isPlaying && this.refreshCountdown == ready.refreshCountdown;
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        public int getRefreshCountdown() {
            return this.refreshCountdown;
        }

        public int hashCode() {
            return Integer.hashCode(this.refreshCountdown) + (Boolean.hashCode(this.isPlaying) * 31);
        }

        public final boolean isPlaying() {
            return this.isPlaying;
        }

        @NotNull
        public String toString() {
            return "Ready(isPlaying=" + this.isPlaying + ", refreshCountdown=" + this.refreshCountdown + ')';
        }

        @Override // com.vk.auth.captcha.impl.base.CaptchaStatus
        @NotNull
        public CaptchaStatus updateCountdown(int newRefreshCountdown) {
            return copy$default(this, false, newRefreshCountdown, 1, null);
        }
    }

    public CaptchaStatus(int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this.refreshCountdown = i10;
    }

    public int getRefreshCountdown() {
        return this.refreshCountdown;
    }

    public final boolean isCountdownStop() {
        return getRefreshCountdown() == 0;
    }

    @NotNull
    public abstract CaptchaStatus updateCountdown(int newRefreshCountdown);
}
