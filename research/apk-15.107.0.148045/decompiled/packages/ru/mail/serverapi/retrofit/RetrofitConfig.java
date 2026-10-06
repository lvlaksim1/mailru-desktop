package ru.mail.serverapi.retrofit;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lru/mail/serverapi/retrofit/RetrofitConfig;", "", "rethrowRequestSessionException", "", "<init>", "(Z)V", "getRethrowRequestSessionException", "()Z", "component1", "copy", "equals", "other", "hashCode", "", "toString", "", "server-api_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RetrofitConfig {
    private final boolean rethrowRequestSessionException;

    public RetrofitConfig(boolean z10) {
        this.rethrowRequestSessionException = z10;
    }

    public static /* synthetic */ RetrofitConfig copy$default(RetrofitConfig retrofitConfig, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = retrofitConfig.rethrowRequestSessionException;
        }
        return retrofitConfig.copy(z10);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getRethrowRequestSessionException() {
        return this.rethrowRequestSessionException;
    }

    @NotNull
    public final RetrofitConfig copy(boolean rethrowRequestSessionException) {
        return new RetrofitConfig(rethrowRequestSessionException);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof RetrofitConfig) && this.rethrowRequestSessionException == ((RetrofitConfig) other).rethrowRequestSessionException;
    }

    public final boolean getRethrowRequestSessionException() {
        return this.rethrowRequestSessionException;
    }

    public int hashCode() {
        return Boolean.hashCode(this.rethrowRequestSessionException);
    }

    @NotNull
    public String toString() {
        return "RetrofitConfig(rethrowRequestSessionException=" + this.rethrowRequestSessionException + ")";
    }
}
