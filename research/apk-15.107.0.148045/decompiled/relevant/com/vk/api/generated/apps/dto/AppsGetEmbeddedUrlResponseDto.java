package com.vk.api.generated.apps.dto;

import a.detarenegipakvmocm;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.vk.superapp.browser.ui.VkBrowserFragment;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.bonus.BonusConstants;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
@Parcelize
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\b\u0087\b\u0018\u00002\u00020\u0001:\u00010B3\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\r\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001d\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0012\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u0016\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0016\u0010\u0015J\u0010\u0010\u0017\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0017\u0010\u0015J\u0010\u0010\u0018\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\u0018\u0010\u0015J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJD\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\u001d\u0010\u0015J\u0010\u0010\u001e\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\u001e\u0010\rJ\u001a\u0010\"\u001a\u00020!2\b\u0010 \u001a\u0004\u0018\u00010\u001fHÖ\u0003¢\u0006\u0004\b\"\u0010#R\u001a\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010\u0015R\u001a\u0010\u0004\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b'\u0010%\u001a\u0004\b(\u0010\u0015R\u001a\u0010\u0005\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b)\u0010%\u001a\u0004\b*\u0010\u0015R\u001a\u0010\u0006\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b+\u0010%\u001a\u0004\b,\u0010\u0015R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u0010\u001a¨\u00061"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto;", "Landroid/os/Parcelable;", "", "originalUrl", "viewUrl", "appAccessToken", "screenTitle", "Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto$TypeDto;", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto$TypeDto;)V", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Ljava/lang/String;", "component2", "component3", "component4", "component5", "()Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto$TypeDto;", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto$TypeDto;)Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto;", "toString", "hashCode", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "Ljava/lang/String;", "getOriginalUrl", "detarenegipakvmocb", "getViewUrl", "detarenegipakvmocc", "getAppAccessToken", "detarenegipakvmocd", "getScreenTitle", "detarenegipakvmoce", "Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto$TypeDto;", "getType", "TypeDto", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppsGetEmbeddedUrlResponseDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AppsGetEmbeddedUrlResponseDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName(VkBrowserFragment.KEY_ORIGINAL_URL)
    @NotNull
    private final String originalUrl;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("view_url")
    @NotNull
    private final String viewUrl;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("app_access_token")
    @NotNull
    private final String appAccessToken;

    /* JADX INFO: renamed from: detarenegipakvmocd, reason: from kotlin metadata */
    @SerializedName("screen_title")
    @NotNull
    private final String screenTitle;

    /* JADX INFO: renamed from: detarenegipakvmoce, reason: from kotlin metadata */
    @SerializedName("type")
    @Nullable
    private final TypeDto type;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AppsGetEmbeddedUrlResponseDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppsGetEmbeddedUrlResponseDto createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new AppsGetEmbeddedUrlResponseDto(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : TypeDto.CREATOR.createFromParcel(parcel));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppsGetEmbeddedUrlResponseDto[] newArray(int i10) {
            return new AppsGetEmbeddedUrlResponseDto[i10];
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetEmbeddedUrlResponseDto$TypeDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetEmbeddedUrlResponseDto$TypeDto[]) from 0x001e: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetEmbeddedUrlResponseDto$TypeDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:31)
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: ProGuard */
    @Parcelize
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u0011\u001a\u00020\f8\u0006¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetEmbeddedUrlResponseDto$TypeDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "", "detarenegipakvmoca", "Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "value", "GAME", GrsBaseInfo.CountryCodeSource.APP, "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class TypeDto implements Parcelable {
        GAME(BonusConstants.GAME_NAME),
        APP("app");


        @NotNull
        public static final Parcelable.Creator<TypeDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocc;

        /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
        @NotNull
        private final String value;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<TypeDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final TypeDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return TypeDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final TypeDto[] newArray(int i10) {
                return new TypeDto[i10];
            }
        }

        static {
            detarenegipakvmocc = EnumEntriesKt.enumEntries(new TypeDto[]{r0, r1});
        }

        private TypeDto(String str) {
            super(str, i);
            this.value = str;
        }

        @NotNull
        public static EnumEntries<TypeDto> getEntries() {
            return detarenegipakvmocc;
        }

        public static TypeDto valueOf(String str) {
            return (TypeDto) Enum.valueOf(TypeDto.class, str);
        }

        public static TypeDto[] values() {
            return (TypeDto[]) detarenegipakvmocb.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    public AppsGetEmbeddedUrlResponseDto(@NotNull String originalUrl, @NotNull String viewUrl, @NotNull String appAccessToken, @NotNull String screenTitle, @Nullable TypeDto typeDto) {
        Intrinsics.checkNotNullParameter(originalUrl, "originalUrl");
        Intrinsics.checkNotNullParameter(viewUrl, "viewUrl");
        Intrinsics.checkNotNullParameter(appAccessToken, "appAccessToken");
        Intrinsics.checkNotNullParameter(screenTitle, "screenTitle");
        this.originalUrl = originalUrl;
        this.viewUrl = viewUrl;
        this.appAccessToken = appAccessToken;
        this.screenTitle = screenTitle;
        this.type = typeDto;
    }

    public static /* synthetic */ AppsGetEmbeddedUrlResponseDto copy$default(AppsGetEmbeddedUrlResponseDto appsGetEmbeddedUrlResponseDto, String str, String str2, String str3, String str4, TypeDto typeDto, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = appsGetEmbeddedUrlResponseDto.originalUrl;
        }
        if ((i10 & 2) != 0) {
            str2 = appsGetEmbeddedUrlResponseDto.viewUrl;
        }
        if ((i10 & 4) != 0) {
            str3 = appsGetEmbeddedUrlResponseDto.appAccessToken;
        }
        if ((i10 & 8) != 0) {
            str4 = appsGetEmbeddedUrlResponseDto.screenTitle;
        }
        if ((i10 & 16) != 0) {
            typeDto = appsGetEmbeddedUrlResponseDto.type;
        }
        TypeDto typeDto2 = typeDto;
        String str5 = str3;
        return appsGetEmbeddedUrlResponseDto.copy(str, str2, str5, str4, typeDto2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getOriginalUrl() {
        return this.originalUrl;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getViewUrl() {
        return this.viewUrl;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getAppAccessToken() {
        return this.appAccessToken;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getScreenTitle() {
        return this.screenTitle;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final TypeDto getType() {
        return this.type;
    }

    @NotNull
    public final AppsGetEmbeddedUrlResponseDto copy(@NotNull String originalUrl, @NotNull String viewUrl, @NotNull String appAccessToken, @NotNull String screenTitle, @Nullable TypeDto type) {
        Intrinsics.checkNotNullParameter(originalUrl, "originalUrl");
        Intrinsics.checkNotNullParameter(viewUrl, "viewUrl");
        Intrinsics.checkNotNullParameter(appAccessToken, "appAccessToken");
        Intrinsics.checkNotNullParameter(screenTitle, "screenTitle");
        return new AppsGetEmbeddedUrlResponseDto(originalUrl, viewUrl, appAccessToken, screenTitle, type);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppsGetEmbeddedUrlResponseDto)) {
            return false;
        }
        AppsGetEmbeddedUrlResponseDto appsGetEmbeddedUrlResponseDto = (AppsGetEmbeddedUrlResponseDto) other;
        return Intrinsics.areEqual(this.originalUrl, appsGetEmbeddedUrlResponseDto.originalUrl) && Intrinsics.areEqual(this.viewUrl, appsGetEmbeddedUrlResponseDto.viewUrl) && Intrinsics.areEqual(this.appAccessToken, appsGetEmbeddedUrlResponseDto.appAccessToken) && Intrinsics.areEqual(this.screenTitle, appsGetEmbeddedUrlResponseDto.screenTitle) && this.type == appsGetEmbeddedUrlResponseDto.type;
    }

    @NotNull
    public final String getAppAccessToken() {
        return this.appAccessToken;
    }

    @NotNull
    public final String getOriginalUrl() {
        return this.originalUrl;
    }

    @NotNull
    public final String getScreenTitle() {
        return this.screenTitle;
    }

    @Nullable
    public final TypeDto getType() {
        return this.type;
    }

    @NotNull
    public final String getViewUrl() {
        return this.viewUrl;
    }

    public int hashCode() {
        int iDetarenegipakvmoca = detarenegipakvmocm.detarenegipakvmoca(this.screenTitle, detarenegipakvmocm.detarenegipakvmoca(this.appAccessToken, detarenegipakvmocm.detarenegipakvmoca(this.viewUrl, this.originalUrl.hashCode() * 31, 31), 31), 31);
        TypeDto typeDto = this.type;
        return iDetarenegipakvmoca + (typeDto == null ? 0 : typeDto.hashCode());
    }

    @NotNull
    public String toString() {
        return "AppsGetEmbeddedUrlResponseDto(originalUrl=" + this.originalUrl + ", viewUrl=" + this.viewUrl + ", appAccessToken=" + this.appAccessToken + ", screenTitle=" + this.screenTitle + ", type=" + this.type + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.originalUrl);
        dest.writeString(this.viewUrl);
        dest.writeString(this.appAccessToken);
        dest.writeString(this.screenTitle);
        TypeDto typeDto = this.type;
        if (typeDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            typeDto.writeToParcel(dest, flags);
        }
    }

    public /* synthetic */ AppsGetEmbeddedUrlResponseDto(String str, String str2, String str3, String str4, TypeDto typeDto, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, (i10 & 16) != 0 ? null : typeDto);
    }
}
