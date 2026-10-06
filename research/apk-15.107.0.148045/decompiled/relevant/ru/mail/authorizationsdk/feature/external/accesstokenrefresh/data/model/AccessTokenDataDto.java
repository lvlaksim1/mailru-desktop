package ru.mail.authorizationsdk.feature.external.accesstokenrefresh.data.model;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.MailO2AuthStrategy;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 /2\u00020\u0001:\u0002./B7\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tBC\b\u0010\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\b\u0010\u000eJ\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u0019J>\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u000bHÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001J%\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00002\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0001¢\u0006\u0002\b-R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0010\u001a\u0004\b\u0014\u0010\u0012R\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0016\u0010\u0012R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\u001a\u0012\u0004\b\u0017\u0010\u0010\u001a\u0004\b\u0018\u0010\u0019¨\u00060"}, d2 = {"Lru/mail/authorizationsdk/feature/external/accesstokenrefresh/data/model/AccessTokenDataDto;", "", "refreshToken", "", CommonConstant.KEY_ACCESS_TOKEN, "tokenType", "expiresIn", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getRefreshToken$annotations", "()V", "getRefreshToken", "()Ljava/lang/String;", "getAccessToken$annotations", "getAccessToken", "getTokenType$annotations", "getTokenType", "getExpiresIn$annotations", "getExpiresIn", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;)Lru/mail/authorizationsdk/feature/external/accesstokenrefresh/data/model/AccessTokenDataDto;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$authorizationsdk_release", "$serializer", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
public final /* data */ class AccessTokenDataDto {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    private final String accessToken;

    @Nullable
    private final Long expiresIn;

    @Nullable
    private final String refreshToken;

    @Nullable
    private final String tokenType;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/authorizationsdk/feature/external/accesstokenrefresh/data/model/AccessTokenDataDto$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/authorizationsdk/feature/external/accesstokenrefresh/data/model/AccessTokenDataDto;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final KSerializer<AccessTokenDataDto> serializer() {
            return AccessTokenDataDto$$serializer.INSTANCE;
        }

        private Companion() {
        }
    }

    public AccessTokenDataDto() {
        this((String) null, (String) null, (String) null, (Long) null, 15, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ AccessTokenDataDto copy$default(AccessTokenDataDto accessTokenDataDto, String str, String str2, String str3, Long l10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = accessTokenDataDto.refreshToken;
        }
        if ((i10 & 2) != 0) {
            str2 = accessTokenDataDto.accessToken;
        }
        if ((i10 & 4) != 0) {
            str3 = accessTokenDataDto.tokenType;
        }
        if ((i10 & 8) != 0) {
            l10 = accessTokenDataDto.expiresIn;
        }
        return accessTokenDataDto.copy(str, str2, str3, l10);
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$authorizationsdk_release(AccessTokenDataDto self, CompositeEncoder output, SerialDescriptor serialDesc) {
        if (output.shouldEncodeElementDefault(serialDesc, 0) || self.refreshToken != null) {
            output.encodeNullableSerializableElement(serialDesc, 0, StringSerializer.INSTANCE, self.refreshToken);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1) || self.accessToken != null) {
            output.encodeNullableSerializableElement(serialDesc, 1, StringSerializer.INSTANCE, self.accessToken);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 2) || self.tokenType != null) {
            output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.tokenType);
        }
        if (!output.shouldEncodeElementDefault(serialDesc, 3) && self.expiresIn == null) {
            return;
        }
        output.encodeNullableSerializableElement(serialDesc, 3, LongSerializer.INSTANCE, self.expiresIn);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getTokenType() {
        return this.tokenType;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Long getExpiresIn() {
        return this.expiresIn;
    }

    @NotNull
    public final AccessTokenDataDto copy(@Nullable String refreshToken, @Nullable String accessToken, @Nullable String tokenType, @Nullable Long expiresIn) {
        return new AccessTokenDataDto(refreshToken, accessToken, tokenType, expiresIn);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AccessTokenDataDto)) {
            return false;
        }
        AccessTokenDataDto accessTokenDataDto = (AccessTokenDataDto) other;
        return Intrinsics.areEqual(this.refreshToken, accessTokenDataDto.refreshToken) && Intrinsics.areEqual(this.accessToken, accessTokenDataDto.accessToken) && Intrinsics.areEqual(this.tokenType, accessTokenDataDto.tokenType) && Intrinsics.areEqual(this.expiresIn, accessTokenDataDto.expiresIn);
    }

    @Nullable
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    public final Long getExpiresIn() {
        return this.expiresIn;
    }

    @Nullable
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    @Nullable
    public final String getTokenType() {
        return this.tokenType;
    }

    public int hashCode() {
        String str = this.refreshToken;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.accessToken;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.tokenType;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l10 = this.expiresIn;
        return iHashCode3 + (l10 != null ? l10.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "AccessTokenDataDto(refreshToken=" + this.refreshToken + ", accessToken=" + this.accessToken + ", tokenType=" + this.tokenType + ", expiresIn=" + this.expiresIn + ")";
    }

    public /* synthetic */ AccessTokenDataDto(int i10, String str, String str2, String str3, Long l10, SerializationConstructorMarker serializationConstructorMarker) {
        if ((i10 & 1) == 0) {
            this.refreshToken = null;
        } else {
            this.refreshToken = str;
        }
        if ((i10 & 2) == 0) {
            this.accessToken = null;
        } else {
            this.accessToken = str2;
        }
        if ((i10 & 4) == 0) {
            this.tokenType = null;
        } else {
            this.tokenType = str3;
        }
        if ((i10 & 8) == 0) {
            this.expiresIn = null;
        } else {
            this.expiresIn = l10;
        }
    }

    public AccessTokenDataDto(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Long l10) {
        this.refreshToken = str;
        this.accessToken = str2;
        this.tokenType = str3;
        this.expiresIn = l10;
    }

    public /* synthetic */ AccessTokenDataDto(String str, String str2, String str3, Long l10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : str2, (i10 & 4) != 0 ? null : str3, (i10 & 8) != 0 ? null : l10);
    }

    @SerialName("access_token")
    public static /* synthetic */ void getAccessToken$annotations() {
    }

    @SerialName("expires_in")
    public static /* synthetic */ void getExpiresIn$annotations() {
    }

    @SerialName("refresh_token")
    public static /* synthetic */ void getRefreshToken$annotations() {
    }

    @SerialName(MailO2AuthStrategy.EXTRA_TOKEN_TYPE)
    public static /* synthetic */ void getTokenType$annotations() {
    }
}
