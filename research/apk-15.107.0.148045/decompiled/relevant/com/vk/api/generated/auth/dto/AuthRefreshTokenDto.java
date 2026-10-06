package com.vk.api.generated.auth.dto;

import a.detarenegipakvmoca;
import a.detarenegipakvmocc;
import a.detarenegipakvmocj;
import a.detarenegipakvmoco;
import a.detarenegipakvmocu;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.dto.common.id.UserId;
import com.vk.superapp.sessionmanagment.impl.data.source.SessionSQLiteHelper;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u001a\n\u0002\u0010\u0000\n\u0002\b$\b\u0087\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u0019\u001a\u00020\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u001d\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0002¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b!\u0010\u001aJ\u0010\u0010\"\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u0006HÆ\u0003¢\u0006\u0004\b$\u0010%J\u0012\u0010&\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b&\u0010'J\u0012\u0010(\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b(\u0010)J\u0012\u0010*\u001a\u0004\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b*\u0010)J\u0012\u0010+\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0004\b+\u0010,J\u0012\u0010-\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\b-\u0010.J\u0012\u0010/\u001a\u0004\u0018\u00010\u0010HÆ\u0003¢\u0006\u0004\b/\u00100J\u0012\u00101\u001a\u0004\u0018\u00010\u0012HÆ\u0003¢\u0006\u0004\b1\u00102J\u0018\u00103\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\b3\u00104J\u0094\u0001\u00105\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014HÆ\u0001¢\u0006\u0004\b5\u00106J\u0010\u00107\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b7\u0010)J\u0010\u00108\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b8\u0010\u001aJ\u001a\u0010;\u001a\u00020\u00062\b\u0010:\u001a\u0004\u0018\u000109HÖ\u0003¢\u0006\u0004\b;\u0010<R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b=\u0010>\u001a\u0004\b?\u0010\u001aR\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010#R\u001a\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bC\u0010D\u001a\u0004\bE\u0010%R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bF\u0010G\u001a\u0004\bH\u0010'R\u001c\u0010\n\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bI\u0010J\u001a\u0004\bK\u0010)R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bL\u0010J\u001a\u0004\bM\u0010)R\u001c\u0010\r\u001a\u0004\u0018\u00010\f8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bN\u0010O\u001a\u0004\bP\u0010,R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010.R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006X\u0087\u0004¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u00100R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006X\u0087\u0004¢\u0006\f\n\u0004\bW\u0010X\u001a\u0004\bY\u00102R\"\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u00104¨\u0006]"}, d2 = {"Lcom/vk/api/generated/auth/dto/AuthRefreshTokenDto;", "Landroid/os/Parcelable;", "", "index", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "banned", "deactivated", "", "reactivationDate", "phoneToActualize", "Lcom/vk/api/generated/auth/dto/AuthRefreshAccessTokenDto;", CommonConstant.KEY_ACCESS_TOKEN, "Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewAccessTokenDto;", "webviewAccessToken", "Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewRefreshTokenDto;", "webviewRefreshToken", "Lcom/vk/api/generated/auth/dto/AuthRefreshSilentTokenDto;", "silentToken", "", "Lcom/vk/api/generated/auth/dto/AuthRefreshUserSessionDto;", "userSession", "<init>", "(ILcom/vk/dto/common/id/UserId;ZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/generated/auth/dto/AuthRefreshAccessTokenDto;Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewAccessTokenDto;Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewRefreshTokenDto;Lcom/vk/api/generated/auth/dto/AuthRefreshSilentTokenDto;Ljava/util/List;)V", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "component2", "()Lcom/vk/dto/common/id/UserId;", "component3", "()Z", "component4", "()Ljava/lang/Boolean;", "component5", "()Ljava/lang/String;", "component6", "component7", "()Lcom/vk/api/generated/auth/dto/AuthRefreshAccessTokenDto;", "component8", "()Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewAccessTokenDto;", "component9", "()Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewRefreshTokenDto;", "component10", "()Lcom/vk/api/generated/auth/dto/AuthRefreshSilentTokenDto;", "component11", "()Ljava/util/List;", "copy", "(ILcom/vk/dto/common/id/UserId;ZLjava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/generated/auth/dto/AuthRefreshAccessTokenDto;Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewAccessTokenDto;Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewRefreshTokenDto;Lcom/vk/api/generated/auth/dto/AuthRefreshSilentTokenDto;Ljava/util/List;)Lcom/vk/api/generated/auth/dto/AuthRefreshTokenDto;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "I", "getIndex", "detarenegipakvmocb", "Lcom/vk/dto/common/id/UserId;", "getUserId", "detarenegipakvmocc", "Z", "getBanned", "detarenegipakvmocd", "Ljava/lang/Boolean;", "getDeactivated", "detarenegipakvmoce", "Ljava/lang/String;", "getReactivationDate", "detarenegipakvmocf", "getPhoneToActualize", "detarenegipakvmocg", "Lcom/vk/api/generated/auth/dto/AuthRefreshAccessTokenDto;", "getAccessToken", "detarenegipakvmoch", "Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewAccessTokenDto;", "getWebviewAccessToken", "detarenegipakvmoci", "Lcom/vk/api/generated/auth/dto/AuthRefreshWebviewRefreshTokenDto;", "getWebviewRefreshToken", "detarenegipakvmocj", "Lcom/vk/api/generated/auth/dto/AuthRefreshSilentTokenDto;", "getSilentToken", "detarenegipakvmock", "Ljava/util/List;", "getUserSession", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthRefreshTokenDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AuthRefreshTokenDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName("index")
    private final int index;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("user_id")
    @NotNull
    private final UserId userId;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("banned")
    private final boolean banned;

    /* JADX INFO: renamed from: detarenegipakvmocd, reason: from kotlin metadata */
    @SerializedName("deactivated")
    @Nullable
    private final Boolean deactivated;

    /* JADX INFO: renamed from: detarenegipakvmoce, reason: from kotlin metadata */
    @SerializedName("reactivation_date")
    @Nullable
    private final String reactivationDate;

    /* JADX INFO: renamed from: detarenegipakvmocf, reason: from kotlin metadata */
    @SerializedName("phone_to_actualize")
    @Nullable
    private final String phoneToActualize;

    /* JADX INFO: renamed from: detarenegipakvmocg, reason: from kotlin metadata */
    @SerializedName("access_token")
    @Nullable
    private final AuthRefreshAccessTokenDto accessToken;

    /* JADX INFO: renamed from: detarenegipakvmoch, reason: from kotlin metadata */
    @SerializedName(SessionSQLiteHelper.COLUMN_WEBVIEW_AT)
    @Nullable
    private final AuthRefreshWebviewAccessTokenDto webviewAccessToken;

    /* JADX INFO: renamed from: detarenegipakvmoci, reason: from kotlin metadata */
    @SerializedName(SessionSQLiteHelper.COLUMN_WEBVIEW_RT)
    @Nullable
    private final AuthRefreshWebviewRefreshTokenDto webviewRefreshToken;

    /* JADX INFO: renamed from: detarenegipakvmocj, reason: from kotlin metadata */
    @SerializedName("silent_token")
    @Nullable
    private final AuthRefreshSilentTokenDto silentToken;

    /* JADX INFO: renamed from: detarenegipakvmock, reason: from kotlin metadata */
    @SerializedName("user_session")
    @Nullable
    private final List<AuthRefreshUserSessionDto> userSession;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AuthRefreshTokenDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r13v5, types: [java.util.List] */
        /* JADX WARN: Type inference failed for: r14v1, types: [java.util.ArrayList] */
        /* JADX WARN: Type inference failed for: r14v2 */
        /* JADX WARN: Type inference failed for: r14v3 */
        @Override // android.os.Parcelable.Creator
        public final AuthRefreshTokenDto createFromParcel(Parcel parcel) {
            Boolean boolValueOf;
            Parcelable parcelable;
            ?? arrayList;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            int i10 = parcel.readInt();
            UserId userId = (UserId) parcel.readParcelable(AuthRefreshTokenDto.class.getClassLoader());
            int iDetarenegipakvmoca = 0;
            boolean z10 = parcel.readInt() != 0;
            if (parcel.readInt() == 0) {
                boolValueOf = null;
                parcelable = null;
            } else {
                boolValueOf = Boolean.valueOf(parcel.readInt() != 0);
                parcelable = null;
            }
            String string = parcel.readString();
            Parcelable parcelable2 = parcelable;
            String string2 = parcel.readString();
            AuthRefreshAccessTokenDto authRefreshAccessTokenDto = (AuthRefreshAccessTokenDto) (parcel.readInt() == 0 ? parcelable2 : AuthRefreshAccessTokenDto.CREATOR.createFromParcel(parcel));
            AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto = (AuthRefreshWebviewAccessTokenDto) (parcel.readInt() == 0 ? parcelable2 : AuthRefreshWebviewAccessTokenDto.CREATOR.createFromParcel(parcel));
            AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto = (AuthRefreshWebviewRefreshTokenDto) (parcel.readInt() == 0 ? parcelable2 : AuthRefreshWebviewRefreshTokenDto.CREATOR.createFromParcel(parcel));
            AuthRefreshSilentTokenDto authRefreshSilentTokenDto = (AuthRefreshSilentTokenDto) (parcel.readInt() == 0 ? parcelable2 : AuthRefreshSilentTokenDto.CREATOR.createFromParcel(parcel));
            if (parcel.readInt() == 0) {
                arrayList = parcelable2;
            } else {
                int i11 = parcel.readInt();
                arrayList = new ArrayList(i11);
                while (iDetarenegipakvmoca != i11) {
                    iDetarenegipakvmoca = detarenegipakvmocc.detarenegipakvmoca(AuthRefreshUserSessionDto.CREATOR, parcel, arrayList, iDetarenegipakvmoca, 1);
                }
            }
            return new AuthRefreshTokenDto(i10, userId, z10, boolValueOf, string, string2, authRefreshAccessTokenDto, authRefreshWebviewAccessTokenDto, authRefreshWebviewRefreshTokenDto, authRefreshSilentTokenDto, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthRefreshTokenDto[] newArray(int i10) {
            return new AuthRefreshTokenDto[i10];
        }
    }

    public AuthRefreshTokenDto(int i10, @NotNull UserId userId, boolean z10, @Nullable Boolean bool, @Nullable String str, @Nullable String str2, @Nullable AuthRefreshAccessTokenDto authRefreshAccessTokenDto, @Nullable AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto, @Nullable AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto, @Nullable AuthRefreshSilentTokenDto authRefreshSilentTokenDto, @Nullable List<AuthRefreshUserSessionDto> list) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.index = i10;
        this.userId = userId;
        this.banned = z10;
        this.deactivated = bool;
        this.reactivationDate = str;
        this.phoneToActualize = str2;
        this.accessToken = authRefreshAccessTokenDto;
        this.webviewAccessToken = authRefreshWebviewAccessTokenDto;
        this.webviewRefreshToken = authRefreshWebviewRefreshTokenDto;
        this.silentToken = authRefreshSilentTokenDto;
        this.userSession = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AuthRefreshTokenDto copy$default(AuthRefreshTokenDto authRefreshTokenDto, int i10, UserId userId, boolean z10, Boolean bool, String str, String str2, AuthRefreshAccessTokenDto authRefreshAccessTokenDto, AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto, AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto, AuthRefreshSilentTokenDto authRefreshSilentTokenDto, List list, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = authRefreshTokenDto.index;
        }
        if ((i11 & 2) != 0) {
            userId = authRefreshTokenDto.userId;
        }
        if ((i11 & 4) != 0) {
            z10 = authRefreshTokenDto.banned;
        }
        if ((i11 & 8) != 0) {
            bool = authRefreshTokenDto.deactivated;
        }
        if ((i11 & 16) != 0) {
            str = authRefreshTokenDto.reactivationDate;
        }
        if ((i11 & 32) != 0) {
            str2 = authRefreshTokenDto.phoneToActualize;
        }
        if ((i11 & 64) != 0) {
            authRefreshAccessTokenDto = authRefreshTokenDto.accessToken;
        }
        if ((i11 & 128) != 0) {
            authRefreshWebviewAccessTokenDto = authRefreshTokenDto.webviewAccessToken;
        }
        if ((i11 & 256) != 0) {
            authRefreshWebviewRefreshTokenDto = authRefreshTokenDto.webviewRefreshToken;
        }
        if ((i11 & 512) != 0) {
            authRefreshSilentTokenDto = authRefreshTokenDto.silentToken;
        }
        if ((i11 & 1024) != 0) {
            list = authRefreshTokenDto.userSession;
        }
        AuthRefreshSilentTokenDto authRefreshSilentTokenDto2 = authRefreshSilentTokenDto;
        List list2 = list;
        AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto2 = authRefreshWebviewAccessTokenDto;
        AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto2 = authRefreshWebviewRefreshTokenDto;
        String str3 = str2;
        AuthRefreshAccessTokenDto authRefreshAccessTokenDto2 = authRefreshAccessTokenDto;
        String str4 = str;
        boolean z11 = z10;
        return authRefreshTokenDto.copy(i10, userId, z11, bool, str4, str3, authRefreshAccessTokenDto2, authRefreshWebviewAccessTokenDto2, authRefreshWebviewRefreshTokenDto2, authRefreshSilentTokenDto2, list2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getIndex() {
        return this.index;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final AuthRefreshSilentTokenDto getSilentToken() {
        return this.silentToken;
    }

    @Nullable
    public final List<AuthRefreshUserSessionDto> component11() {
        return this.userSession;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final UserId getUserId() {
        return this.userId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getBanned() {
        return this.banned;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Boolean getDeactivated() {
        return this.deactivated;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getReactivationDate() {
        return this.reactivationDate;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getPhoneToActualize() {
        return this.phoneToActualize;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final AuthRefreshAccessTokenDto getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final AuthRefreshWebviewAccessTokenDto getWebviewAccessToken() {
        return this.webviewAccessToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final AuthRefreshWebviewRefreshTokenDto getWebviewRefreshToken() {
        return this.webviewRefreshToken;
    }

    @NotNull
    public final AuthRefreshTokenDto copy(int index, @NotNull UserId userId, boolean banned, @Nullable Boolean deactivated, @Nullable String reactivationDate, @Nullable String phoneToActualize, @Nullable AuthRefreshAccessTokenDto accessToken, @Nullable AuthRefreshWebviewAccessTokenDto webviewAccessToken, @Nullable AuthRefreshWebviewRefreshTokenDto webviewRefreshToken, @Nullable AuthRefreshSilentTokenDto silentToken, @Nullable List<AuthRefreshUserSessionDto> userSession) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        return new AuthRefreshTokenDto(index, userId, banned, deactivated, reactivationDate, phoneToActualize, accessToken, webviewAccessToken, webviewRefreshToken, silentToken, userSession);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthRefreshTokenDto)) {
            return false;
        }
        AuthRefreshTokenDto authRefreshTokenDto = (AuthRefreshTokenDto) other;
        return this.index == authRefreshTokenDto.index && Intrinsics.areEqual(this.userId, authRefreshTokenDto.userId) && this.banned == authRefreshTokenDto.banned && Intrinsics.areEqual(this.deactivated, authRefreshTokenDto.deactivated) && Intrinsics.areEqual(this.reactivationDate, authRefreshTokenDto.reactivationDate) && Intrinsics.areEqual(this.phoneToActualize, authRefreshTokenDto.phoneToActualize) && Intrinsics.areEqual(this.accessToken, authRefreshTokenDto.accessToken) && Intrinsics.areEqual(this.webviewAccessToken, authRefreshTokenDto.webviewAccessToken) && Intrinsics.areEqual(this.webviewRefreshToken, authRefreshTokenDto.webviewRefreshToken) && Intrinsics.areEqual(this.silentToken, authRefreshTokenDto.silentToken) && Intrinsics.areEqual(this.userSession, authRefreshTokenDto.userSession);
    }

    @Nullable
    public final AuthRefreshAccessTokenDto getAccessToken() {
        return this.accessToken;
    }

    public final boolean getBanned() {
        return this.banned;
    }

    @Nullable
    public final Boolean getDeactivated() {
        return this.deactivated;
    }

    public final int getIndex() {
        return this.index;
    }

    @Nullable
    public final String getPhoneToActualize() {
        return this.phoneToActualize;
    }

    @Nullable
    public final String getReactivationDate() {
        return this.reactivationDate;
    }

    @Nullable
    public final AuthRefreshSilentTokenDto getSilentToken() {
        return this.silentToken;
    }

    @NotNull
    public final UserId getUserId() {
        return this.userId;
    }

    @Nullable
    public final List<AuthRefreshUserSessionDto> getUserSession() {
        return this.userSession;
    }

    @Nullable
    public final AuthRefreshWebviewAccessTokenDto getWebviewAccessToken() {
        return this.webviewAccessToken;
    }

    @Nullable
    public final AuthRefreshWebviewRefreshTokenDto getWebviewRefreshToken() {
        return this.webviewRefreshToken;
    }

    public int hashCode() {
        int iDetarenegipakvmoca = detarenegipakvmoco.detarenegipakvmoca(this.banned, detarenegipakvmocu.detarenegipakvmoca(this.userId, Integer.hashCode(this.index) * 31, 31), 31);
        Boolean bool = this.deactivated;
        int iHashCode = (iDetarenegipakvmoca + (bool == null ? 0 : bool.hashCode())) * 31;
        String str = this.reactivationDate;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.phoneToActualize;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        AuthRefreshAccessTokenDto authRefreshAccessTokenDto = this.accessToken;
        int iHashCode4 = (iHashCode3 + (authRefreshAccessTokenDto == null ? 0 : authRefreshAccessTokenDto.hashCode())) * 31;
        AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto = this.webviewAccessToken;
        int iHashCode5 = (iHashCode4 + (authRefreshWebviewAccessTokenDto == null ? 0 : authRefreshWebviewAccessTokenDto.hashCode())) * 31;
        AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto = this.webviewRefreshToken;
        int iHashCode6 = (iHashCode5 + (authRefreshWebviewRefreshTokenDto == null ? 0 : authRefreshWebviewRefreshTokenDto.hashCode())) * 31;
        AuthRefreshSilentTokenDto authRefreshSilentTokenDto = this.silentToken;
        int iHashCode7 = (iHashCode6 + (authRefreshSilentTokenDto == null ? 0 : authRefreshSilentTokenDto.hashCode())) * 31;
        List<AuthRefreshUserSessionDto> list = this.userSession;
        return iHashCode7 + (list != null ? list.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "AuthRefreshTokenDto(index=" + this.index + ", userId=" + this.userId + ", banned=" + this.banned + ", deactivated=" + this.deactivated + ", reactivationDate=" + this.reactivationDate + ", phoneToActualize=" + this.phoneToActualize + ", accessToken=" + this.accessToken + ", webviewAccessToken=" + this.webviewAccessToken + ", webviewRefreshToken=" + this.webviewRefreshToken + ", silentToken=" + this.silentToken + ", userSession=" + this.userSession + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.index);
        dest.writeParcelable(this.userId, flags);
        dest.writeInt(this.banned ? 1 : 0);
        Boolean bool = this.deactivated;
        if (bool == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocj.detarenegipakvmoca(dest, 1, bool);
        }
        dest.writeString(this.reactivationDate);
        dest.writeString(this.phoneToActualize);
        AuthRefreshAccessTokenDto authRefreshAccessTokenDto = this.accessToken;
        if (authRefreshAccessTokenDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            authRefreshAccessTokenDto.writeToParcel(dest, flags);
        }
        AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto = this.webviewAccessToken;
        if (authRefreshWebviewAccessTokenDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            authRefreshWebviewAccessTokenDto.writeToParcel(dest, flags);
        }
        AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto = this.webviewRefreshToken;
        if (authRefreshWebviewRefreshTokenDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            authRefreshWebviewRefreshTokenDto.writeToParcel(dest, flags);
        }
        AuthRefreshSilentTokenDto authRefreshSilentTokenDto = this.silentToken;
        if (authRefreshSilentTokenDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            authRefreshSilentTokenDto.writeToParcel(dest, flags);
        }
        List<AuthRefreshUserSessionDto> list = this.userSession;
        if (list == null) {
            dest.writeInt(0);
            return;
        }
        Iterator itDetarenegipakvmoca = detarenegipakvmoca.detarenegipakvmoca(dest, 1, list);
        while (itDetarenegipakvmoca.hasNext()) {
            ((AuthRefreshUserSessionDto) itDetarenegipakvmoca.next()).writeToParcel(dest, flags);
        }
    }

    public /* synthetic */ AuthRefreshTokenDto(int i10, UserId userId, boolean z10, Boolean bool, String str, String str2, AuthRefreshAccessTokenDto authRefreshAccessTokenDto, AuthRefreshWebviewAccessTokenDto authRefreshWebviewAccessTokenDto, AuthRefreshWebviewRefreshTokenDto authRefreshWebviewRefreshTokenDto, AuthRefreshSilentTokenDto authRefreshSilentTokenDto, List list, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this(i10, userId, z10, (i11 & 8) != 0 ? null : bool, (i11 & 16) != 0 ? null : str, (i11 & 32) != 0 ? null : str2, (i11 & 64) != 0 ? null : authRefreshAccessTokenDto, (i11 & 128) != 0 ? null : authRefreshWebviewAccessTokenDto, (i11 & 256) != 0 ? null : authRefreshWebviewRefreshTokenDto, (i11 & 512) != 0 ? null : authRefreshSilentTokenDto, (i11 & 1024) != 0 ? null : list);
    }
}
