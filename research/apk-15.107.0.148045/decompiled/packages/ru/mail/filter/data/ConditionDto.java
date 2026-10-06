package ru.mail.filter.data;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0017"}, d2 = {"Lru/mail/filter/data/ConditionDto;", "", "name", "", "not", "", "value", "<init>", "(Ljava/lang/String;ZLjava/lang/String;)V", "getName", "()Ljava/lang/String;", "getNot", "()Z", "getValue", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ConditionDto {

    @NotNull
    private final String name;
    private final boolean not;

    @NotNull
    private final String value;

    public ConditionDto(@NotNull String name, boolean z10, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        this.name = name;
        this.not = z10;
        this.value = value;
    }

    public static /* synthetic */ ConditionDto copy$default(ConditionDto conditionDto, String str, boolean z10, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = conditionDto.name;
        }
        if ((i10 & 2) != 0) {
            z10 = conditionDto.not;
        }
        if ((i10 & 4) != 0) {
            str2 = conditionDto.value;
        }
        return conditionDto.copy(str, z10, str2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getNot() {
        return this.not;
    }

    @NotNull
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getValue() {
        return this.value;
    }

    @NotNull
    public final ConditionDto copy(@NotNull String name, boolean not, @NotNull String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        return new ConditionDto(name, not, value);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ConditionDto)) {
            return false;
        }
        ConditionDto conditionDto = (ConditionDto) other;
        return Intrinsics.areEqual(this.name, conditionDto.name) && this.not == conditionDto.not && Intrinsics.areEqual(this.value, conditionDto.value);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final boolean getNot() {
        return this.not;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + Boolean.hashCode(this.not)) * 31) + this.value.hashCode();
    }

    @NotNull
    public String toString() {
        return "ConditionDto(name=" + this.name + ", not=" + this.not + ", value=" + this.value + ")";
    }
}
