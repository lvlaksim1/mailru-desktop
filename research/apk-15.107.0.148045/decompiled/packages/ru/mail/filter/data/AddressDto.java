package ru.mail.filter.data;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lru/mail/filter/data/AddressDto;", "", "email", "", "verified", "", "<init>", "(Ljava/lang/String;Z)V", "getEmail", "()Ljava/lang/String;", "getVerified", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class AddressDto {

    @NotNull
    private final String email;
    private final boolean verified;

    public AddressDto(@NotNull String email, boolean z10) {
        Intrinsics.checkNotNullParameter(email, "email");
        this.email = email;
        this.verified = z10;
    }

    public static /* synthetic */ AddressDto copy$default(AddressDto addressDto, String str, boolean z10, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = addressDto.email;
        }
        if ((i10 & 2) != 0) {
            z10 = addressDto.verified;
        }
        return addressDto.copy(str, z10);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getEmail() {
        return this.email;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getVerified() {
        return this.verified;
    }

    @NotNull
    public final AddressDto copy(@NotNull String email, boolean verified) {
        Intrinsics.checkNotNullParameter(email, "email");
        return new AddressDto(email, verified);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AddressDto)) {
            return false;
        }
        AddressDto addressDto = (AddressDto) other;
        return Intrinsics.areEqual(this.email, addressDto.email) && this.verified == addressDto.verified;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    public final boolean getVerified() {
        return this.verified;
    }

    public int hashCode() {
        return (this.email.hashCode() * 31) + Boolean.hashCode(this.verified);
    }

    @NotNull
    public String toString() {
        return "AddressDto(email=" + this.email + ", verified=" + this.verified + ")";
    }
}
