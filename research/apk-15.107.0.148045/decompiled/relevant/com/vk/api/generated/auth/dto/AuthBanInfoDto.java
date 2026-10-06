package com.vk.api.generated.auth.dto;

import a.detarenegipakvmocf;
import a.detarenegipakvmocm;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
@Parcelize
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\b¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0012J.\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0002HÆ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0017\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u0017\u0010\u0012J\u0010\u0010\u0018\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\u0018\u0010\nJ\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010\u0012R\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b!\u0010\u001f\u001a\u0004\b\"\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b#\u0010\u001f\u001a\u0004\b$\u0010\u0012¨\u0006%"}, d2 = {"Lcom/vk/api/generated/auth/dto/AuthBanInfoDto;", "Landroid/os/Parcelable;", "", "memberName", "message", CommonConstant.KEY_ACCESS_TOKEN, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Ljava/lang/String;", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/vk/api/generated/auth/dto/AuthBanInfoDto;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "Ljava/lang/String;", "getMemberName", "detarenegipakvmocb", "getMessage", "detarenegipakvmocc", "getAccessToken", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AuthBanInfoDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AuthBanInfoDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName("member_name")
    @NotNull
    private final String memberName;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("message")
    @NotNull
    private final String message;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("access_token")
    @NotNull
    private final String accessToken;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AuthBanInfoDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthBanInfoDto createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AuthBanInfoDto(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AuthBanInfoDto[] newArray(int i10) {
            return new AuthBanInfoDto[i10];
        }
    }

    public AuthBanInfoDto(@NotNull String memberName, @NotNull String message, @NotNull String accessToken) {
        Intrinsics.checkNotNullParameter(memberName, "memberName");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        this.memberName = memberName;
        this.message = message;
        this.accessToken = accessToken;
    }

    public static /* synthetic */ AuthBanInfoDto copy$default(AuthBanInfoDto authBanInfoDto, String str, String str2, String str3, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = authBanInfoDto.memberName;
        }
        if ((i10 & 2) != 0) {
            str2 = authBanInfoDto.message;
        }
        if ((i10 & 4) != 0) {
            str3 = authBanInfoDto.accessToken;
        }
        return authBanInfoDto.copy(str, str2, str3);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMemberName() {
        return this.memberName;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    public final AuthBanInfoDto copy(@NotNull String memberName, @NotNull String message, @NotNull String accessToken) {
        Intrinsics.checkNotNullParameter(memberName, "memberName");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(accessToken, "accessToken");
        return new AuthBanInfoDto(memberName, message, accessToken);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AuthBanInfoDto)) {
            return false;
        }
        AuthBanInfoDto authBanInfoDto = (AuthBanInfoDto) other;
        return Intrinsics.areEqual(this.memberName, authBanInfoDto.memberName) && Intrinsics.areEqual(this.message, authBanInfoDto.message) && Intrinsics.areEqual(this.accessToken, authBanInfoDto.accessToken);
    }

    @NotNull
    public final String getAccessToken() {
        return this.accessToken;
    }

    @NotNull
    public final String getMemberName() {
        return this.memberName;
    }

    @NotNull
    public final String getMessage() {
        return this.message;
    }

    public int hashCode() {
        return this.accessToken.hashCode() + detarenegipakvmocm.detarenegipakvmoca(this.message, this.memberName.hashCode() * 31, 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb2 = new StringBuilder("AuthBanInfoDto(memberName=");
        sb2.append(this.memberName);
        sb2.append(", message=");
        sb2.append(this.message);
        sb2.append(", accessToken=");
        return detarenegipakvmocf.detarenegipakvmoca(sb2, this.accessToken, ')');
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.memberName);
        dest.writeString(this.message);
        dest.writeString(this.accessToken);
    }
}
