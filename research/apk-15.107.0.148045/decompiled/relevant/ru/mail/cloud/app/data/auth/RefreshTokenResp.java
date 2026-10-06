package ru.mail.cloud.app.data.auth;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.MailO2AuthStrategy;
import ru.mail.cloud.app.data.GsonSerializable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0015JP\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001eJ\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\nHÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015¨\u0006%"}, d2 = {"Lru/mail/cloud/app/data/auth/RefreshTokenResp;", "Lru/mail/cloud/app/data/GsonSerializable;", "expiresIn", "", CommonConstant.KEY_ACCESS_TOKEN, "", "tokenType", "error", "error_description", "error_code", "", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)V", "getExpiresIn", "()J", "getAccessToken", "()Ljava/lang/String;", "getTokenType", "getError", "getError_description", "getError_code", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;)Lru/mail/cloud/app/data/auth/RefreshTokenResp;", "equals", "", "other", "", "hashCode", "toString", "cloud-app_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class RefreshTokenResp implements GsonSerializable {
    public static final int $stable = 0;

    @SerializedName("access_token")
    @NotNull
    private final String accessToken;

    @Nullable
    private final String error;

    @Nullable
    private final Integer error_code;

    @Nullable
    private final String error_description;

    @SerializedName("expires_in")
    private final long expiresIn;

    @SerializedName(MailO2AuthStrategy.EXTRA_TOKEN_TYPE)
    @NotNull
    private final String tokenType;

    public RefreshTokenResp(long j10, @NotNull String accessToken, @NotNull String tokenType, @Nullable String str, @Nullable String str2, @Nullable Integer num) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        this.expiresIn = j10;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.error = str;
        this.error_description = str2;
        this.error_code = num;
    }

    public static /* synthetic */ RefreshTokenResp copy$default(RefreshTokenResp refreshTokenResp, long j10, String str, String str2, String str3, String str4, Integer num, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            j10 = refreshTokenResp.expiresIn;
        }
        long j11 = j10;
        if ((i10 & 2) != 0) {
            str = refreshTokenResp.accessToken;
        }
        String str5 = str;
        if ((i10 & 4) != 0) {
            str2 = refreshTokenResp.tokenType;
        }
        String str6 = str2;
        if ((i10 & 8) != 0) {
            str3 = refreshTokenResp.error;
        }
        String str7 = str3;
        if ((i10 & 16) != 0) {
            str4 = refreshTokenResp.error_description;
        }
        String str8 = str4;
        if ((i10 & 32) != 0) {
            num = refreshTokenResp.error_code;
        }
        return refreshTokenResp.copy(j11, str5, str6, str7, str8, num);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final long getExpiresIn() {
        return this.expiresIn;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getError() {
        return this.error;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getError_description() {
        return this.error_description;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final Integer getError_code() {
        return this.error_code;
    }

    @NotNull
    public final RefreshTokenResp copy(long expiresIn, @NotNull String accessToken, @NotNull String tokenType, @Nullable String error, @Nullable String error_description, @Nullable Integer error_code) {
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        Intrinsics.checkNotNullParameter(tokenType, "tokenType");
        return new RefreshTokenResp(expiresIn, accessToken, tokenType, error, error_description, error_code);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RefreshTokenResp)) {
            return false;
        }
        RefreshTokenResp refreshTokenResp = (RefreshTokenResp) other;
        return this.expiresIn == refreshTokenResp.expiresIn && Intrinsics.areEqual(this.accessToken, refreshTokenResp.accessToken) && Intrinsics.areEqual(this.tokenType, refreshTokenResp.tokenType) && Intrinsics.areEqual(this.error, refreshTokenResp.error) && Intrinsics.areEqual(this.error_description, refreshTokenResp.error_description) && Intrinsics.areEqual(this.error_code, refreshTokenResp.error_code);
    }

    @NotNull
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    public final String getError() {
        return this.error;
    }

    @Nullable
    public final Integer getError_code() {
        return this.error_code;
    }

    @Nullable
    public final String getError_description() {
        return this.error_description;
    }

    public final long getExpiresIn() {
        return this.expiresIn;
    }

    @NotNull
    public final String getTokenType() {
        return this.tokenType;
    }

    public int hashCode() {
        int iHashCode = ((((Long.hashCode(this.expiresIn) * 31) + this.accessToken.hashCode()) * 31) + this.tokenType.hashCode()) * 31;
        String str = this.error;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.error_description;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num = this.error_code;
        return iHashCode3 + (num != null ? num.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "RefreshTokenResp(expiresIn=" + this.expiresIn + ", accessToken=" + this.accessToken + ", tokenType=" + this.tokenType + ", error=" + this.error + ", error_description=" + this.error_description + ", error_code=" + this.error_code + ")";
    }
}
