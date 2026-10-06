package com.vk.api.generated.auth.dto;

import a.detarenegipakvmocb;
import a.detarenegipakvmocj;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.dto.common.id.UserId;
import com.vk.silentauth.SilentAuthInfo;
import com.vk.superapp.api.analytics.RegistrationStatParamsFactory;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
@Parcelize
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b!\b\u0087\b\u0018\u00002\u00020\u0001B£\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001f\u0010\u001eJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b \u0010!J\u0012\u0010\"\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010$\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b$\u0010#J\u0012\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b%\u0010#J\u0012\u0010&\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b&\u0010'J\u0012\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b(\u0010#J\u0012\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b)\u0010#J\u0012\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b*\u0010#J\u0012\u0010+\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b+\u0010\u001eJ\u0012\u0010,\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b,\u0010'J\u0012\u0010-\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b-\u0010\u001eJ¬\u0001\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b.\u0010/J\u0010\u00100\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b0\u0010#J\u0010\u00101\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b1\u0010\u0016J\u001a\u00104\u001a\u00020\u000b2\b\u00103\u001a\u0004\u0018\u000102HÖ\u0003¢\u0006\u0004\b4\u00105R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u0010\u001eR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b9\u00107\u001a\u0004\b:\u0010\u001eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010!R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010#R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bA\u0010?\u001a\u0004\bB\u0010#R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bC\u0010?\u001a\u0004\bD\u0010#R\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010'R\u001c\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bH\u0010?\u001a\u0004\bI\u0010#R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bJ\u0010?\u001a\u0004\bK\u0010#R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bL\u0010?\u001a\u0004\bM\u0010#R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\bN\u00107\u001a\u0004\bO\u0010\u001eR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bP\u0010F\u001a\u0004\b\u0011\u0010'R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\bQ\u00107\u001a\u0004\bR\u0010\u001e¨\u0006S"}, d2 = {"Lcom/vk/api/generated/auth/dto/AuthCheckAuthCodeResponseDto;", "Landroid/os/Parcelable;", "", "status", "expiresIn", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "phone", "photo200", "superAppToken", "", "needPassword", CommonConstant.KEY_ACCESS_TOKEN, "silentToken", SilentAuthInfo.KEY_UUID, "silentTokenTtl", "isPartial", "providerAppId", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;)V", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Ljava/lang/Integer;", "component2", "component3", "()Lcom/vk/dto/common/id/UserId;", "component4", "()Ljava/lang/String;", "component5", "component6", "component7", "()Ljava/lang/Boolean;", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Boolean;Ljava/lang/Integer;)Lcom/vk/api/generated/auth/dto/AuthCheckAuthCodeResponseDto;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "Ljava/lang/Integer;", "getStatus", "detarenegipakvmocb", "getExpiresIn", "detarenegipakvmocc", "Lcom/vk/dto/common/id/UserId;", "getUserId", "detarenegipakvmocd", "Ljava/lang/String;", "getPhone", "detarenegipakvmoce", "getPhoto200", "detarenegipakvmocf", "getSuperAppToken", "detarenegipakvmocg", "Ljava/lang/Boolean;", "getNeedPassword", "detarenegipakvmoch", "getAccessToken", "detarenegipakvmoci", "getSilentToken", "detarenegipakvmocj", "getUuid", "detarenegipakvmock", "getSilentTokenTtl", "detarenegipakvmocl", "detarenegipakvmocm", "getProviderAppId", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthCheckAuthCodeResponseDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AuthCheckAuthCodeResponseDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName("status")
    @Nullable
    private final Integer status;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("expires_in")
    @Nullable
    private final Integer expiresIn;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("user_id")
    @Nullable
    private final UserId userId;

    /* JADX INFO: renamed from: detarenegipakvmocd, reason: from kotlin metadata */
    @SerializedName("phone")
    @Nullable
    private final String phone;

    /* JADX INFO: renamed from: detarenegipakvmoce, reason: from kotlin metadata */
    @SerializedName("photo_200")
    @Nullable
    private final String photo200;

    /* JADX INFO: renamed from: detarenegipakvmocf, reason: from kotlin metadata */
    @SerializedName("super_app_token")
    @Nullable
    private final String superAppToken;

    /* JADX INFO: renamed from: detarenegipakvmocg, reason: from kotlin metadata */
    @SerializedName(VKWebAuthException.ERROR_NEED_PASSWORD)
    @Nullable
    private final Boolean needPassword;

    /* JADX INFO: renamed from: detarenegipakvmoch, reason: from kotlin metadata */
    @SerializedName("access_token")
    @Nullable
    private final String accessToken;

    /* JADX INFO: renamed from: detarenegipakvmoci, reason: from kotlin metadata */
    @SerializedName("silent_token")
    @Nullable
    private final String silentToken;

    /* JADX INFO: renamed from: detarenegipakvmocj, reason: from kotlin metadata */
    @SerializedName(SilentAuthInfo.KEY_UUID)
    @Nullable
    private final String uuid;

    /* JADX INFO: renamed from: detarenegipakvmock, reason: from kotlin metadata */
    @SerializedName("silent_token_ttl")
    @Nullable
    private final Integer silentTokenTtl;

    /* JADX INFO: renamed from: detarenegipakvmocl, reason: from kotlin metadata */
    @SerializedName("is_partial")
    @Nullable
    private final Boolean isPartial;

    /* JADX INFO: renamed from: detarenegipakvmocm, reason: from kotlin metadata */
    @SerializedName(RegistrationStatParamsFactory.PROVIDER_APP_ID)
    @Nullable
    private final Integer providerAppId;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AuthCheckAuthCodeResponseDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthCheckAuthCodeResponseDto createFromParcel(Parcel parcel) {
            Boolean boolValueOf;
            Boolean boolValueOf2;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            Integer numValueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer numValueOf2 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            UserId userId = (UserId) parcel.readParcelable(AuthCheckAuthCodeResponseDto.class.getClassLoader());
            String string = parcel.readString();
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            if (parcel.readInt() == 0) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(parcel.readInt() != 0);
            }
            String string4 = parcel.readString();
            String string5 = parcel.readString();
            boolean z10 = false;
            String string6 = parcel.readString();
            Integer numValueOf3 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            if (parcel.readInt() == 0) {
                boolValueOf2 = null;
            } else {
                if (parcel.readInt() != 0) {
                    z10 = true;
                }
                boolValueOf2 = Boolean.valueOf(z10);
            }
            return new AuthCheckAuthCodeResponseDto(numValueOf, numValueOf2, userId, string, string2, string3, boolValueOf, string4, string5, string6, numValueOf3, boolValueOf2, parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthCheckAuthCodeResponseDto[] newArray(int i10) {
            return new AuthCheckAuthCodeResponseDto[i10];
        }
    }

    public AuthCheckAuthCodeResponseDto() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    public static /* synthetic */ AuthCheckAuthCodeResponseDto copy$default(AuthCheckAuthCodeResponseDto authCheckAuthCodeResponseDto, Integer num, Integer num2, UserId userId, String str, String str2, String str3, Boolean bool, String str4, String str5, String str6, Integer num3, Boolean bool2, Integer num4, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = authCheckAuthCodeResponseDto.status;
        }
        return authCheckAuthCodeResponseDto.copy(num, (i10 & 2) != 0 ? authCheckAuthCodeResponseDto.expiresIn : num2, (i10 & 4) != 0 ? authCheckAuthCodeResponseDto.userId : userId, (i10 & 8) != 0 ? authCheckAuthCodeResponseDto.phone : str, (i10 & 16) != 0 ? authCheckAuthCodeResponseDto.photo200 : str2, (i10 & 32) != 0 ? authCheckAuthCodeResponseDto.superAppToken : str3, (i10 & 64) != 0 ? authCheckAuthCodeResponseDto.needPassword : bool, (i10 & 128) != 0 ? authCheckAuthCodeResponseDto.accessToken : str4, (i10 & 256) != 0 ? authCheckAuthCodeResponseDto.silentToken : str5, (i10 & 512) != 0 ? authCheckAuthCodeResponseDto.uuid : str6, (i10 & 1024) != 0 ? authCheckAuthCodeResponseDto.silentTokenTtl : num3, (i10 & 2048) != 0 ? authCheckAuthCodeResponseDto.isPartial : bool2, (i10 & 4096) != 0 ? authCheckAuthCodeResponseDto.providerAppId : num4);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getStatus() {
        return this.status;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final String getUuid() {
        return this.uuid;
    }

    @Nullable
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final Integer getSilentTokenTtl() {
        return this.silentTokenTtl;
    }

    @Nullable
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Boolean getIsPartial() {
        return this.isPartial;
    }

    @Nullable
    /* JADX INFO: renamed from: component13, reason: from getter */
    public final Integer getProviderAppId() {
        return this.providerAppId;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getExpiresIn() {
        return this.expiresIn;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final UserId getUserId() {
        return this.userId;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getPhone() {
        return this.phone;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getPhoto200() {
        return this.photo200;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getSuperAppToken() {
        return this.superAppToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final Boolean getNeedPassword() {
        return this.needPassword;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final String getSilentToken() {
        return this.silentToken;
    }

    @NotNull
    public final AuthCheckAuthCodeResponseDto copy(@Nullable Integer status, @Nullable Integer expiresIn, @Nullable UserId userId, @Nullable String phone, @Nullable String photo200, @Nullable String superAppToken, @Nullable Boolean needPassword, @Nullable String accessToken, @Nullable String silentToken, @Nullable String uuid, @Nullable Integer silentTokenTtl, @Nullable Boolean isPartial, @Nullable Integer providerAppId) {
        return new AuthCheckAuthCodeResponseDto(status, expiresIn, userId, phone, photo200, superAppToken, needPassword, accessToken, silentToken, uuid, silentTokenTtl, isPartial, providerAppId);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthCheckAuthCodeResponseDto)) {
            return false;
        }
        AuthCheckAuthCodeResponseDto authCheckAuthCodeResponseDto = (AuthCheckAuthCodeResponseDto) other;
        return Intrinsics.areEqual(this.status, authCheckAuthCodeResponseDto.status) && Intrinsics.areEqual(this.expiresIn, authCheckAuthCodeResponseDto.expiresIn) && Intrinsics.areEqual(this.userId, authCheckAuthCodeResponseDto.userId) && Intrinsics.areEqual(this.phone, authCheckAuthCodeResponseDto.phone) && Intrinsics.areEqual(this.photo200, authCheckAuthCodeResponseDto.photo200) && Intrinsics.areEqual(this.superAppToken, authCheckAuthCodeResponseDto.superAppToken) && Intrinsics.areEqual(this.needPassword, authCheckAuthCodeResponseDto.needPassword) && Intrinsics.areEqual(this.accessToken, authCheckAuthCodeResponseDto.accessToken) && Intrinsics.areEqual(this.silentToken, authCheckAuthCodeResponseDto.silentToken) && Intrinsics.areEqual(this.uuid, authCheckAuthCodeResponseDto.uuid) && Intrinsics.areEqual(this.silentTokenTtl, authCheckAuthCodeResponseDto.silentTokenTtl) && Intrinsics.areEqual(this.isPartial, authCheckAuthCodeResponseDto.isPartial) && Intrinsics.areEqual(this.providerAppId, authCheckAuthCodeResponseDto.providerAppId);
    }

    @Nullable
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    public final Integer getExpiresIn() {
        return this.expiresIn;
    }

    @Nullable
    public final Boolean getNeedPassword() {
        return this.needPassword;
    }

    @Nullable
    public final String getPhone() {
        return this.phone;
    }

    @Nullable
    public final String getPhoto200() {
        return this.photo200;
    }

    @Nullable
    public final Integer getProviderAppId() {
        return this.providerAppId;
    }

    @Nullable
    public final String getSilentToken() {
        return this.silentToken;
    }

    @Nullable
    public final Integer getSilentTokenTtl() {
        return this.silentTokenTtl;
    }

    @Nullable
    public final Integer getStatus() {
        return this.status;
    }

    @Nullable
    public final String getSuperAppToken() {
        return this.superAppToken;
    }

    @Nullable
    public final UserId getUserId() {
        return this.userId;
    }

    @Nullable
    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        Integer num = this.status;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        Integer num2 = this.expiresIn;
        int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
        UserId userId = this.userId;
        int iHashCode3 = (iHashCode2 + (userId == null ? 0 : userId.hashCode())) * 31;
        String str = this.phone;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.photo200;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.superAppToken;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Boolean bool = this.needPassword;
        int iHashCode7 = (iHashCode6 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str4 = this.accessToken;
        int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.silentToken;
        int iHashCode9 = (iHashCode8 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.uuid;
        int iHashCode10 = (iHashCode9 + (str6 == null ? 0 : str6.hashCode())) * 31;
        Integer num3 = this.silentTokenTtl;
        int iHashCode11 = (iHashCode10 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Boolean bool2 = this.isPartial;
        int iHashCode12 = (iHashCode11 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num4 = this.providerAppId;
        return iHashCode12 + (num4 != null ? num4.hashCode() : 0);
    }

    @Nullable
    public final Boolean isPartial() {
        return this.isPartial;
    }

    @NotNull
    public String toString() {
        return "AuthCheckAuthCodeResponseDto(status=" + this.status + ", expiresIn=" + this.expiresIn + ", userId=" + this.userId + ", phone=" + this.phone + ", photo200=" + this.photo200 + ", superAppToken=" + this.superAppToken + ", needPassword=" + this.needPassword + ", accessToken=" + this.accessToken + ", silentToken=" + this.silentToken + ", uuid=" + this.uuid + ", silentTokenTtl=" + this.silentTokenTtl + ", isPartial=" + this.isPartial + ", providerAppId=" + this.providerAppId + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        Integer num = this.status;
        if (num == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num);
        }
        Integer num2 = this.expiresIn;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num2);
        }
        dest.writeParcelable(this.userId, flags);
        dest.writeString(this.phone);
        dest.writeString(this.photo200);
        dest.writeString(this.superAppToken);
        Boolean bool = this.needPassword;
        if (bool == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocj.detarenegipakvmoca(dest, 1, bool);
        }
        dest.writeString(this.accessToken);
        dest.writeString(this.silentToken);
        dest.writeString(this.uuid);
        Integer num3 = this.silentTokenTtl;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num3);
        }
        Boolean bool2 = this.isPartial;
        if (bool2 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocj.detarenegipakvmoca(dest, 1, bool2);
        }
        Integer num4 = this.providerAppId;
        if (num4 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num4);
        }
    }

    public AuthCheckAuthCodeResponseDto(@Nullable Integer num, @Nullable Integer num2, @Nullable UserId userId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Boolean bool, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable Integer num3, @Nullable Boolean bool2, @Nullable Integer num4) {
        this.status = num;
        this.expiresIn = num2;
        this.userId = userId;
        this.phone = str;
        this.photo200 = str2;
        this.superAppToken = str3;
        this.needPassword = bool;
        this.accessToken = str4;
        this.silentToken = str5;
        this.uuid = str6;
        this.silentTokenTtl = num3;
        this.isPartial = bool2;
        this.providerAppId = num4;
    }

    public /* synthetic */ AuthCheckAuthCodeResponseDto(Integer num, Integer num2, UserId userId, String str, String str2, String str3, Boolean bool, String str4, String str5, String str6, Integer num3, Boolean bool2, Integer num4, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, (i10 & 4) != 0 ? null : userId, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? null : str2, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : bool, (i10 & 128) != 0 ? null : str4, (i10 & 256) != 0 ? null : str5, (i10 & 512) != 0 ? null : str6, (i10 & 1024) != 0 ? null : num3, (i10 & 2048) != 0 ? null : bool2, (i10 & 4096) != 0 ? null : num4);
    }
}
