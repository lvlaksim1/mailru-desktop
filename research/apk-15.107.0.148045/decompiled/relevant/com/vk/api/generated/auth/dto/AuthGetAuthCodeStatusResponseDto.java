package com.vk.api.generated.auth.dto;

import a.detarenegipakvmoca;
import a.detarenegipakvmocb;
import a.detarenegipakvmocc;
import a.detarenegipakvmocg;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.dto.common.id.UserId;
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
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0004\b\f\u0010\rJ\r\u0010\u000e\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u001d\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\u001a\u0010\u0017J\u0012\u0010\u001b\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u001b\u0010\u001cJ\u0018\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJR\u0010\u001f\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001¢\u0006\u0004\b\u001f\u0010 J\u0010\u0010!\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\b!\u0010\u0019J\u0010\u0010\"\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\"\u0010\u000fJ\u001a\u0010&\u001a\u00020%2\b\u0010$\u001a\u0004\u0018\u00010#HÖ\u0003¢\u0006\u0004\b&\u0010'R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010\u0017R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010\u0019R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b.\u0010)\u001a\u0004\b/\u0010\u0017R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u0010\u001cR\"\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u0010\u001e¨\u00066"}, d2 = {"Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeStatusResponseDto;", "Landroid/os/Parcelable;", "", "status", "", CommonConstant.KEY_ACCESS_TOKEN, "expiresIn", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "", "Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeStatusUserSessionDto;", "userSession", "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/util/List;)V", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Ljava/lang/Integer;", "component2", "()Ljava/lang/String;", "component3", "component4", "()Lcom/vk/dto/common/id/UserId;", "component5", "()Ljava/util/List;", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/util/List;)Lcom/vk/api/generated/auth/dto/AuthGetAuthCodeStatusResponseDto;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "Ljava/lang/Integer;", "getStatus", "detarenegipakvmocb", "Ljava/lang/String;", "getAccessToken", "detarenegipakvmocc", "getExpiresIn", "detarenegipakvmocd", "Lcom/vk/dto/common/id/UserId;", "getUserId", "detarenegipakvmoce", "Ljava/util/List;", "getUserSession", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthGetAuthCodeStatusResponseDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AuthGetAuthCodeStatusResponseDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName("status")
    @Nullable
    private final Integer status;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("access_token")
    @Nullable
    private final String accessToken;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("expires_in")
    @Nullable
    private final Integer expiresIn;

    /* JADX INFO: renamed from: detarenegipakvmocd, reason: from kotlin metadata */
    @SerializedName("user_id")
    @Nullable
    private final UserId userId;

    /* JADX INFO: renamed from: detarenegipakvmoce, reason: from kotlin metadata */
    @SerializedName("user_session")
    @Nullable
    private final List<AuthGetAuthCodeStatusUserSessionDto> userSession;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AuthGetAuthCodeStatusResponseDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthGetAuthCodeStatusResponseDto createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            ArrayList arrayList = null;
            Integer numValueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String string = parcel.readString();
            Integer numValueOf2 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            UserId userId = (UserId) parcel.readParcelable(AuthGetAuthCodeStatusResponseDto.class.getClassLoader());
            if (parcel.readInt() != 0) {
                int i10 = parcel.readInt();
                arrayList = new ArrayList(i10);
                int iDetarenegipakvmoca = 0;
                while (iDetarenegipakvmoca != i10) {
                    iDetarenegipakvmoca = detarenegipakvmocc.detarenegipakvmoca(AuthGetAuthCodeStatusUserSessionDto.CREATOR, parcel, arrayList, iDetarenegipakvmoca, 1);
                }
            }
            return new AuthGetAuthCodeStatusResponseDto(numValueOf, string, numValueOf2, userId, arrayList);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthGetAuthCodeStatusResponseDto[] newArray(int i10) {
            return new AuthGetAuthCodeStatusResponseDto[i10];
        }
    }

    public AuthGetAuthCodeStatusResponseDto() {
        this(null, null, null, null, null, 31, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AuthGetAuthCodeStatusResponseDto copy$default(AuthGetAuthCodeStatusResponseDto authGetAuthCodeStatusResponseDto, Integer num, String str, Integer num2, UserId userId, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            num = authGetAuthCodeStatusResponseDto.status;
        }
        if ((i10 & 2) != 0) {
            str = authGetAuthCodeStatusResponseDto.accessToken;
        }
        if ((i10 & 4) != 0) {
            num2 = authGetAuthCodeStatusResponseDto.expiresIn;
        }
        if ((i10 & 8) != 0) {
            userId = authGetAuthCodeStatusResponseDto.userId;
        }
        if ((i10 & 16) != 0) {
            list = authGetAuthCodeStatusResponseDto.userSession;
        }
        List list2 = list;
        Integer num3 = num2;
        return authGetAuthCodeStatusResponseDto.copy(num, str, num3, userId, list2);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Integer getStatus() {
        return this.status;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Integer getExpiresIn() {
        return this.expiresIn;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final UserId getUserId() {
        return this.userId;
    }

    @Nullable
    public final List<AuthGetAuthCodeStatusUserSessionDto> component5() {
        return this.userSession;
    }

    @NotNull
    public final AuthGetAuthCodeStatusResponseDto copy(@Nullable Integer status, @Nullable String accessToken, @Nullable Integer expiresIn, @Nullable UserId userId, @Nullable List<AuthGetAuthCodeStatusUserSessionDto> userSession) {
        return new AuthGetAuthCodeStatusResponseDto(status, accessToken, expiresIn, userId, userSession);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthGetAuthCodeStatusResponseDto)) {
            return false;
        }
        AuthGetAuthCodeStatusResponseDto authGetAuthCodeStatusResponseDto = (AuthGetAuthCodeStatusResponseDto) other;
        return Intrinsics.areEqual(this.status, authGetAuthCodeStatusResponseDto.status) && Intrinsics.areEqual(this.accessToken, authGetAuthCodeStatusResponseDto.accessToken) && Intrinsics.areEqual(this.expiresIn, authGetAuthCodeStatusResponseDto.expiresIn) && Intrinsics.areEqual(this.userId, authGetAuthCodeStatusResponseDto.userId) && Intrinsics.areEqual(this.userSession, authGetAuthCodeStatusResponseDto.userSession);
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
    public final Integer getStatus() {
        return this.status;
    }

    @Nullable
    public final UserId getUserId() {
        return this.userId;
    }

    @Nullable
    public final List<AuthGetAuthCodeStatusUserSessionDto> getUserSession() {
        return this.userSession;
    }

    public int hashCode() {
        Integer num = this.status;
        int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
        String str = this.accessToken;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Integer num2 = this.expiresIn;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        UserId userId = this.userId;
        int iHashCode4 = (iHashCode3 + (userId == null ? 0 : userId.hashCode())) * 31;
        List<AuthGetAuthCodeStatusUserSessionDto> list = this.userSession;
        return iHashCode4 + (list != null ? list.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        StringBuilder sb2 = new StringBuilder("AuthGetAuthCodeStatusResponseDto(status=");
        sb2.append(this.status);
        sb2.append(", accessToken=");
        sb2.append(this.accessToken);
        sb2.append(", expiresIn=");
        sb2.append(this.expiresIn);
        sb2.append(", userId=");
        sb2.append(this.userId);
        sb2.append(", userSession=");
        return detarenegipakvmocg.detarenegipakvmoca(sb2, this.userSession, ')');
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
        dest.writeString(this.accessToken);
        Integer num2 = this.expiresIn;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num2);
        }
        dest.writeParcelable(this.userId, flags);
        List<AuthGetAuthCodeStatusUserSessionDto> list = this.userSession;
        if (list == null) {
            dest.writeInt(0);
            return;
        }
        Iterator itDetarenegipakvmoca = detarenegipakvmoca.detarenegipakvmoca(dest, 1, list);
        while (itDetarenegipakvmoca.hasNext()) {
            ((AuthGetAuthCodeStatusUserSessionDto) itDetarenegipakvmoca.next()).writeToParcel(dest, flags);
        }
    }

    public AuthGetAuthCodeStatusResponseDto(@Nullable Integer num, @Nullable String str, @Nullable Integer num2, @Nullable UserId userId, @Nullable List<AuthGetAuthCodeStatusUserSessionDto> list) {
        this.status = num;
        this.accessToken = str;
        this.expiresIn = num2;
        this.userId = userId;
        this.userSession = list;
    }

    public /* synthetic */ AuthGetAuthCodeStatusResponseDto(Integer num, String str, Integer num2, UserId userId, List list, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : num, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : num2, (i10 & 8) != 0 ? null : userId, (i10 & 16) != 0 ? null : list);
    }
}
