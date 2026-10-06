package com.vk.superapp.api.dto.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.core.extensions.JsonObjectExtKt;
import com.vk.dto.common.id.UserId;
import com.vk.silentauth.SilentAuthInfo;
import com.vk.superapp.api.analytics.RegistrationStatParamsFactory;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.parcelize.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Parcelize
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0002\b#\b\u0087\b\u0018\u0000 V2\u00020\u0001:\u0001VB£\u0001\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u001d\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001e\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b \u0010\u001fJ\u0012\u0010!\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0004\b!\u0010\"J\u0012\u0010#\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b#\u0010$J\u0012\u0010%\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b%\u0010$J\u0012\u0010&\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b&\u0010$J\u0012\u0010'\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b'\u0010(J\u0012\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b)\u0010$J\u0012\u0010*\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0004\b*\u0010(J\u0012\u0010+\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b+\u0010\u001fJ\u0012\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b,\u0010$J\u0012\u0010-\u001a\u0004\u0018\u00010\u0011HÆ\u0003¢\u0006\u0004\b-\u0010.J\u0012\u0010/\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b/\u0010$J¬\u0001\u00100\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b0\u00101J\u0010\u00102\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b2\u0010$J\u0010\u00103\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b3\u0010\u0017J\u001a\u00106\u001a\u00020\u000b2\b\u00105\u001a\u0004\u0018\u000104HÖ\u0003¢\u0006\u0004\b6\u00107R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b8\u00109\u001a\u0004\b:\u0010\u001fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b;\u00109\u001a\u0004\b<\u0010\u001fR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010\"R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010$R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bC\u0010A\u001a\u0004\bD\u0010$R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bE\u0010A\u001a\u0004\bF\u0010$R\u001c\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bG\u0010H\u001a\u0004\bI\u0010(R\u001c\u0010\r\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bJ\u0010A\u001a\u0004\bK\u0010$R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000b8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bL\u0010H\u001a\u0004\b\u000e\u0010(R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\bM\u00109\u001a\u0004\bN\u0010\u001fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bO\u0010A\u001a\u0004\bP\u0010$R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010.R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\bT\u0010A\u001a\u0004\bU\u0010$¨\u0006W"}, d2 = {"Lcom/vk/superapp/api/dto/auth/AuthCheckAuthCodeResponseDto;", "Landroid/os/Parcelable;", "", "status", "expiresIn", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "phone", "photo200", "superAppToken", "", "needPassword", CommonConstant.KEY_ACCESS_TOKEN, "isPartial", "providerAppId", "silentToken", "", "ttl", SilentAuthInfo.KEY_UUID, "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)V", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Ljava/lang/Integer;", "component2", "component3", "()Lcom/vk/dto/common/id/UserId;", "component4", "()Ljava/lang/String;", "component5", "component6", "component7", "()Ljava/lang/Boolean;", "component8", "component9", "component10", "component11", "component12", "()Ljava/lang/Long;", "component13", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;)Lcom/vk/superapp/api/dto/auth/AuthCheckAuthCodeResponseDto;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "otdipakvmoca", "Ljava/lang/Integer;", "getStatus", "otdipakvmocb", "getExpiresIn", "otdipakvmocc", "Lcom/vk/dto/common/id/UserId;", "getUserId", "otdipakvmocd", "Ljava/lang/String;", "getPhone", "otdipakvmoce", "getPhoto200", "otdipakvmocf", "getSuperAppToken", "otdipakvmocg", "Ljava/lang/Boolean;", "getNeedPassword", "otdipakvmoch", "getAccessToken", "otdipakvmoci", "otdipakvmocj", "getProviderAppId", "otdipakvmock", "getSilentToken", "otdipakvmocl", "Ljava/lang/Long;", "getTtl", "otdipakvmocm", "getUuid", "Companion", "api-dto_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthCheckAuthCodeResponseDto implements Parcelable {

    /* JADX INFO: renamed from: otdipakvmoca, reason: from kotlin metadata */
    @SerializedName("status")
    @Nullable
    private final Integer status;

    /* JADX INFO: renamed from: otdipakvmocb, reason: from kotlin metadata */
    @SerializedName("expires_in")
    @Nullable
    private final Integer expiresIn;

    /* JADX INFO: renamed from: otdipakvmocc, reason: from kotlin metadata */
    @SerializedName("user_id")
    @Nullable
    private final UserId userId;

    /* JADX INFO: renamed from: otdipakvmocd, reason: from kotlin metadata */
    @SerializedName("phone")
    @Nullable
    private final String phone;

    /* JADX INFO: renamed from: otdipakvmoce, reason: from kotlin metadata */
    @SerializedName("photo_200")
    @Nullable
    private final String photo200;

    /* JADX INFO: renamed from: otdipakvmocf, reason: from kotlin metadata */
    @SerializedName("super_app_token")
    @Nullable
    private final String superAppToken;

    /* JADX INFO: renamed from: otdipakvmocg, reason: from kotlin metadata */
    @SerializedName(VKWebAuthException.ERROR_NEED_PASSWORD)
    @Nullable
    private final Boolean needPassword;

    /* JADX INFO: renamed from: otdipakvmoch, reason: from kotlin metadata */
    @SerializedName("access_token")
    @Nullable
    private final String accessToken;

    /* JADX INFO: renamed from: otdipakvmoci, reason: from kotlin metadata */
    @SerializedName("is_partial")
    @Nullable
    private final Boolean isPartial;

    /* JADX INFO: renamed from: otdipakvmocj, reason: from kotlin metadata */
    @SerializedName(RegistrationStatParamsFactory.PROVIDER_APP_ID)
    @Nullable
    private final Integer providerAppId;

    /* JADX INFO: renamed from: otdipakvmock, reason: from kotlin metadata */
    @SerializedName("silent_token")
    @Nullable
    private final String silentToken;

    /* JADX INFO: renamed from: otdipakvmocl, reason: from kotlin metadata */
    @SerializedName("silent_token_ttl")
    @Nullable
    private final Long ttl;

    /* JADX INFO: renamed from: otdipakvmocm, reason: from kotlin metadata */
    @SerializedName(SilentAuthInfo.KEY_UUID)
    @Nullable
    private final String uuid;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    public static final Parcelable.Creator<AuthCheckAuthCodeResponseDto> CREATOR = new Creator();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/vk/superapp/api/dto/auth/AuthCheckAuthCodeResponseDto$Companion;", "", "<init>", "()V", "parse", "Lcom/vk/superapp/api/dto/auth/AuthCheckAuthCodeResponseDto;", "json", "Lorg/json/JSONObject;", "api-dto_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nAuthCheckAuthCodeResponseDto.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthCheckAuthCodeResponseDto.kt\ncom/vk/superapp/api/dto/auth/AuthCheckAuthCodeResponseDto$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final AuthCheckAuthCodeResponseDto parse(@NotNull JSONObject json) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            Integer intOrNull = JsonObjectExtKt.getIntOrNull(json, "status");
            Integer intOrNull2 = JsonObjectExtKt.getIntOrNull(json, "expires_in");
            Long longOrNull = JsonObjectExtKt.getLongOrNull(json, "user_id");
            return new AuthCheckAuthCodeResponseDto(intOrNull, intOrNull2, longOrNull != null ? new UserId(longOrNull.longValue()) : null, JsonObjectExtKt.getOptStringOrNull(json, "phone"), JsonObjectExtKt.getOptStringOrNull(json, "photo_200"), JsonObjectExtKt.getOptStringOrNull(json, "super_app_token"), JsonObjectExtKt.getBooleanOrNull(json, VKWebAuthException.ERROR_NEED_PASSWORD), JsonObjectExtKt.getOptStringOrNull(json, "access_token"), JsonObjectExtKt.getBooleanOrNull(json, "is_partial"), JsonObjectExtKt.getIntOrNull(json, RegistrationStatParamsFactory.PROVIDER_APP_ID), JsonObjectExtKt.getOptStringOrNull(json, "silent_token"), JsonObjectExtKt.getLongOrNull(json, "silent_token_ttl"), JsonObjectExtKt.getOptStringOrNull(json, SilentAuthInfo.KEY_UUID));
        }

        private Companion() {
        }
    }

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
            if (parcel.readInt() == 0) {
                boolValueOf2 = null;
            } else {
                boolValueOf2 = Boolean.valueOf(parcel.readInt() != 0);
            }
            return new AuthCheckAuthCodeResponseDto(numValueOf, numValueOf2, userId, string, string2, string3, boolValueOf, string4, boolValueOf2, parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()), parcel.readString(), parcel.readInt() != 0 ? Long.valueOf(parcel.readLong()) : null, parcel.readString());
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

    public static /* synthetic */ AuthCheckAuthCodeResponseDto copy$default(AuthCheckAuthCodeResponseDto authCheckAuthCodeResponseDto, Integer num, Integer num2, UserId userId, String str, String str2, String str3, Boolean bool, String str4, Boolean bool2, Integer num3, String str5, Long l10, String str6, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = authCheckAuthCodeResponseDto.status;
        }
        return authCheckAuthCodeResponseDto.copy(num, (i10 & 2) != 0 ? authCheckAuthCodeResponseDto.expiresIn : num2, (i10 & 4) != 0 ? authCheckAuthCodeResponseDto.userId : userId, (i10 & 8) != 0 ? authCheckAuthCodeResponseDto.phone : str, (i10 & 16) != 0 ? authCheckAuthCodeResponseDto.photo200 : str2, (i10 & 32) != 0 ? authCheckAuthCodeResponseDto.superAppToken : str3, (i10 & 64) != 0 ? authCheckAuthCodeResponseDto.needPassword : bool, (i10 & 128) != 0 ? authCheckAuthCodeResponseDto.accessToken : str4, (i10 & 256) != 0 ? authCheckAuthCodeResponseDto.isPartial : bool2, (i10 & 512) != 0 ? authCheckAuthCodeResponseDto.providerAppId : num3, (i10 & 1024) != 0 ? authCheckAuthCodeResponseDto.silentToken : str5, (i10 & 2048) != 0 ? authCheckAuthCodeResponseDto.ttl : l10, (i10 & 4096) != 0 ? authCheckAuthCodeResponseDto.uuid : str6);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getStatus() {
        return this.status;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final Integer getProviderAppId() {
        return this.providerAppId;
    }

    @Nullable
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSilentToken() {
        return this.silentToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final Long getTtl() {
        return this.ttl;
    }

    @Nullable
    /* JADX INFO: renamed from: component13, reason: from getter */
    public final String getUuid() {
        return this.uuid;
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
    public final Boolean getIsPartial() {
        return this.isPartial;
    }

    @NotNull
    public final AuthCheckAuthCodeResponseDto copy(@Nullable Integer status, @Nullable Integer expiresIn, @Nullable UserId userId, @Nullable String phone, @Nullable String photo200, @Nullable String superAppToken, @Nullable Boolean needPassword, @Nullable String accessToken, @Nullable Boolean isPartial, @Nullable Integer providerAppId, @Nullable String silentToken, @Nullable Long ttl, @Nullable String uuid) {
        return new AuthCheckAuthCodeResponseDto(status, expiresIn, userId, phone, photo200, superAppToken, needPassword, accessToken, isPartial, providerAppId, silentToken, ttl, uuid);
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
        return Intrinsics.areEqual(this.status, authCheckAuthCodeResponseDto.status) && Intrinsics.areEqual(this.expiresIn, authCheckAuthCodeResponseDto.expiresIn) && Intrinsics.areEqual(this.userId, authCheckAuthCodeResponseDto.userId) && Intrinsics.areEqual(this.phone, authCheckAuthCodeResponseDto.phone) && Intrinsics.areEqual(this.photo200, authCheckAuthCodeResponseDto.photo200) && Intrinsics.areEqual(this.superAppToken, authCheckAuthCodeResponseDto.superAppToken) && Intrinsics.areEqual(this.needPassword, authCheckAuthCodeResponseDto.needPassword) && Intrinsics.areEqual(this.accessToken, authCheckAuthCodeResponseDto.accessToken) && Intrinsics.areEqual(this.isPartial, authCheckAuthCodeResponseDto.isPartial) && Intrinsics.areEqual(this.providerAppId, authCheckAuthCodeResponseDto.providerAppId) && Intrinsics.areEqual(this.silentToken, authCheckAuthCodeResponseDto.silentToken) && Intrinsics.areEqual(this.ttl, authCheckAuthCodeResponseDto.ttl) && Intrinsics.areEqual(this.uuid, authCheckAuthCodeResponseDto.uuid);
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
    public final Integer getStatus() {
        return this.status;
    }

    @Nullable
    public final String getSuperAppToken() {
        return this.superAppToken;
    }

    @Nullable
    public final Long getTtl() {
        return this.ttl;
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
        Boolean bool2 = this.isPartial;
        int iHashCode9 = (iHashCode8 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Integer num3 = this.providerAppId;
        int iHashCode10 = (iHashCode9 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str5 = this.silentToken;
        int iHashCode11 = (iHashCode10 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Long l10 = this.ttl;
        int iHashCode12 = (iHashCode11 + (l10 == null ? 0 : l10.hashCode())) * 31;
        String str6 = this.uuid;
        return iHashCode12 + (str6 != null ? str6.hashCode() : 0);
    }

    @Nullable
    public final Boolean isPartial() {
        return this.isPartial;
    }

    @NotNull
    public String toString() {
        return "AuthCheckAuthCodeResponseDto(status=" + this.status + ", expiresIn=" + this.expiresIn + ", userId=" + this.userId + ", phone=" + this.phone + ", photo200=" + this.photo200 + ", superAppToken=" + this.superAppToken + ", needPassword=" + this.needPassword + ", accessToken=" + this.accessToken + ", isPartial=" + this.isPartial + ", providerAppId=" + this.providerAppId + ", silentToken=" + this.silentToken + ", ttl=" + this.ttl + ", uuid=" + this.uuid + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        Integer num = this.status;
        if (num == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num.intValue());
        }
        Integer num2 = this.expiresIn;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num2.intValue());
        }
        dest.writeParcelable(this.userId, flags);
        dest.writeString(this.phone);
        dest.writeString(this.photo200);
        dest.writeString(this.superAppToken);
        Boolean bool = this.needPassword;
        if (bool == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(bool.booleanValue() ? 1 : 0);
        }
        dest.writeString(this.accessToken);
        Boolean bool2 = this.isPartial;
        if (bool2 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(bool2.booleanValue() ? 1 : 0);
        }
        Integer num3 = this.providerAppId;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeInt(num3.intValue());
        }
        dest.writeString(this.silentToken);
        Long l10 = this.ttl;
        if (l10 == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            dest.writeLong(l10.longValue());
        }
        dest.writeString(this.uuid);
    }

    public AuthCheckAuthCodeResponseDto(@Nullable Integer num, @Nullable Integer num2, @Nullable UserId userId, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Boolean bool, @Nullable String str4, @Nullable Boolean bool2, @Nullable Integer num3, @Nullable String str5, @Nullable Long l10, @Nullable String str6) {
        this.status = num;
        this.expiresIn = num2;
        this.userId = userId;
        this.phone = str;
        this.photo200 = str2;
        this.superAppToken = str3;
        this.needPassword = bool;
        this.accessToken = str4;
        this.isPartial = bool2;
        this.providerAppId = num3;
        this.silentToken = str5;
        this.ttl = l10;
        this.uuid = str6;
    }

    public /* synthetic */ AuthCheckAuthCodeResponseDto(Integer num, Integer num2, UserId userId, String str, String str2, String str3, Boolean bool, String str4, Boolean bool2, Integer num3, String str5, Long l10, String str6, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : num2, (i10 & 4) != 0 ? null : userId, (i10 & 8) != 0 ? null : str, (i10 & 16) != 0 ? null : str2, (i10 & 32) != 0 ? null : str3, (i10 & 64) != 0 ? null : bool, (i10 & 128) != 0 ? null : str4, (i10 & 256) != 0 ? null : bool2, (i10 & 512) != 0 ? null : num3, (i10 & 1024) != 0 ? null : str5, (i10 & 2048) != 0 ? null : l10, (i10 & 4096) != 0 ? null : str6);
    }
}
