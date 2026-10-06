package com.vk.api.generated.apps.dto;

import a.detarenegipakvmocf;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
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
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u0000\n\u0002\b\f\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u001d\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\n¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u0004HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0018J0\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u001b\u0010\u0018J\u0010\u0010\u001c\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u001c\u0010\fJ\u001a\u0010\u001f\u001a\u00020\u00022\b\u0010\u001e\u001a\u0004\u0018\u00010\u001dHÖ\u0003¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u0003\u0010\u0014R\u001a\u0010\u0005\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b(\u0010\u0018¨\u0006)"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsJoinAndGetResponseDto;", "Landroid/os/Parcelable;", "", "isJoined", "Lcom/vk/api/generated/apps/dto/AppsAppDto;", "app", "", "appAccessToken", "<init>", "(ZLcom/vk/api/generated/apps/dto/AppsAppDto;Ljava/lang/String;)V", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Z", "component2", "()Lcom/vk/api/generated/apps/dto/AppsAppDto;", "component3", "()Ljava/lang/String;", "copy", "(ZLcom/vk/api/generated/apps/dto/AppsAppDto;Ljava/lang/String;)Lcom/vk/api/generated/apps/dto/AppsJoinAndGetResponseDto;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "Z", "detarenegipakvmocb", "Lcom/vk/api/generated/apps/dto/AppsAppDto;", "getApp", "detarenegipakvmocc", "Ljava/lang/String;", "getAppAccessToken", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppsJoinAndGetResponseDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AppsJoinAndGetResponseDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName("is_joined")
    private final boolean isJoined;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("app")
    @NotNull
    private final AppsAppDto app;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("app_access_token")
    @Nullable
    private final String appAccessToken;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AppsJoinAndGetResponseDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppsJoinAndGetResponseDto createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AppsJoinAndGetResponseDto(parcel.readInt() != 0, (AppsAppDto) parcel.readParcelable(AppsJoinAndGetResponseDto.class.getClassLoader()), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppsJoinAndGetResponseDto[] newArray(int i10) {
            return new AppsJoinAndGetResponseDto[i10];
        }
    }

    public AppsJoinAndGetResponseDto(boolean z10, @NotNull AppsAppDto app, @Nullable String str) {
        Intrinsics.checkNotNullParameter(app, "app");
        this.isJoined = z10;
        this.app = app;
        this.appAccessToken = str;
    }

    public static /* synthetic */ AppsJoinAndGetResponseDto copy$default(AppsJoinAndGetResponseDto appsJoinAndGetResponseDto, boolean z10, AppsAppDto appsAppDto, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = appsJoinAndGetResponseDto.isJoined;
        }
        if ((i10 & 2) != 0) {
            appsAppDto = appsJoinAndGetResponseDto.app;
        }
        if ((i10 & 4) != 0) {
            str = appsJoinAndGetResponseDto.appAccessToken;
        }
        return appsJoinAndGetResponseDto.copy(z10, appsAppDto, str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsJoined() {
        return this.isJoined;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final AppsAppDto getApp() {
        return this.app;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAppAccessToken() {
        return this.appAccessToken;
    }

    @NotNull
    public final AppsJoinAndGetResponseDto copy(boolean isJoined, @NotNull AppsAppDto app, @Nullable String appAccessToken) {
        Intrinsics.checkNotNullParameter(app, "app");
        return new AppsJoinAndGetResponseDto(isJoined, app, appAccessToken);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppsJoinAndGetResponseDto)) {
            return false;
        }
        AppsJoinAndGetResponseDto appsJoinAndGetResponseDto = (AppsJoinAndGetResponseDto) other;
        return this.isJoined == appsJoinAndGetResponseDto.isJoined && Intrinsics.areEqual(this.app, appsJoinAndGetResponseDto.app) && Intrinsics.areEqual(this.appAccessToken, appsJoinAndGetResponseDto.appAccessToken);
    }

    @NotNull
    public final AppsAppDto getApp() {
        return this.app;
    }

    @Nullable
    public final String getAppAccessToken() {
        return this.appAccessToken;
    }

    public int hashCode() {
        int iHashCode = (this.app.hashCode() + (Boolean.hashCode(this.isJoined) * 31)) * 31;
        String str = this.appAccessToken;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isJoined() {
        return this.isJoined;
    }

    @NotNull
    public String toString() {
        StringBuilder sb2 = new StringBuilder("AppsJoinAndGetResponseDto(isJoined=");
        sb2.append(this.isJoined);
        sb2.append(", app=");
        sb2.append(this.app);
        sb2.append(", appAccessToken=");
        return detarenegipakvmocf.detarenegipakvmoca(sb2, this.appAccessToken, ')');
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeInt(this.isJoined ? 1 : 0);
        dest.writeParcelable(this.app, flags);
        dest.writeString(this.appAccessToken);
    }

    public /* synthetic */ AppsJoinAndGetResponseDto(boolean z10, AppsAppDto appsAppDto, String str, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(z10, appsAppDto, (i10 & 4) != 0 ? null : str);
    }
}
