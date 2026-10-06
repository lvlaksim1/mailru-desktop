package ru.mail.filter.data;

import com.squareup.moshi.JsonClass;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.cmd.server.TornadoSendRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u0014J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003Jn\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u0010'J\u0013\u0010(\u001a\u00020\u00032\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012¨\u0006-"}, d2 = {"Lru/mail/filter/data/ActionsDto;", "", "remove", "", "move", "", "read", "flag", "forward", "", "Lru/mail/filter/data/AddressDto;", "notify", TornadoSendRequest.FIELD_REPLY, "", "reject", "<init>", "(ZLjava/lang/Long;ZZLjava/util/List;Ljava/util/List;Ljava/lang/String;Z)V", "getRemove", "()Z", "getMove", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getRead", "getFlag", "getForward", "()Ljava/util/List;", "getNotify", "getReply", "()Ljava/lang/String;", "getReject", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(ZLjava/lang/Long;ZZLjava/util/List;Ljava/util/List;Ljava/lang/String;Z)Lru/mail/filter/data/ActionsDto;", "equals", "other", "hashCode", "", "toString", "filter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ActionsDto {
    private final boolean flag;

    @NotNull
    private final List<AddressDto> forward;

    @Nullable
    private final Long move;

    @NotNull
    private final List<AddressDto> notify;
    private final boolean read;
    private final boolean reject;
    private final boolean remove;

    @Nullable
    private final String reply;

    public ActionsDto(boolean z10, @Nullable Long l10, boolean z11, boolean z12, @NotNull List<AddressDto> forward, @NotNull List<AddressDto> notify, @Nullable String str, boolean z13) {
        Intrinsics.checkNotNullParameter(forward, "forward");
        Intrinsics.checkNotNullParameter(notify, "notify");
        this.remove = z10;
        this.move = l10;
        this.read = z11;
        this.flag = z12;
        this.forward = forward;
        this.notify = notify;
        this.reply = str;
        this.reject = z13;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ActionsDto copy$default(ActionsDto actionsDto, boolean z10, Long l10, boolean z11, boolean z12, List list, List list2, String str, boolean z13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = actionsDto.remove;
        }
        if ((i10 & 2) != 0) {
            l10 = actionsDto.move;
        }
        if ((i10 & 4) != 0) {
            z11 = actionsDto.read;
        }
        if ((i10 & 8) != 0) {
            z12 = actionsDto.flag;
        }
        if ((i10 & 16) != 0) {
            list = actionsDto.forward;
        }
        if ((i10 & 32) != 0) {
            list2 = actionsDto.notify;
        }
        if ((i10 & 64) != 0) {
            str = actionsDto.reply;
        }
        if ((i10 & 128) != 0) {
            z13 = actionsDto.reject;
        }
        String str2 = str;
        boolean z14 = z13;
        List list3 = list;
        List list4 = list2;
        return actionsDto.copy(z10, l10, z11, z12, list3, list4, str2, z14);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getRemove() {
        return this.remove;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Long getMove() {
        return this.move;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getRead() {
        return this.read;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final boolean getFlag() {
        return this.flag;
    }

    @NotNull
    public final List<AddressDto> component5() {
        return this.forward;
    }

    @NotNull
    public final List<AddressDto> component6() {
        return this.notify;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getReply() {
        return this.reply;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    public final boolean getReject() {
        return this.reject;
    }

    @NotNull
    public final ActionsDto copy(boolean remove, @Nullable Long move, boolean read, boolean flag, @NotNull List<AddressDto> forward, @NotNull List<AddressDto> notify, @Nullable String reply, boolean reject) {
        Intrinsics.checkNotNullParameter(forward, "forward");
        Intrinsics.checkNotNullParameter(notify, "notify");
        return new ActionsDto(remove, move, read, flag, forward, notify, reply, reject);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActionsDto)) {
            return false;
        }
        ActionsDto actionsDto = (ActionsDto) other;
        return this.remove == actionsDto.remove && Intrinsics.areEqual(this.move, actionsDto.move) && this.read == actionsDto.read && this.flag == actionsDto.flag && Intrinsics.areEqual(this.forward, actionsDto.forward) && Intrinsics.areEqual(this.notify, actionsDto.notify) && Intrinsics.areEqual(this.reply, actionsDto.reply) && this.reject == actionsDto.reject;
    }

    public final boolean getFlag() {
        return this.flag;
    }

    @NotNull
    public final List<AddressDto> getForward() {
        return this.forward;
    }

    @Nullable
    public final Long getMove() {
        return this.move;
    }

    @NotNull
    public final List<AddressDto> getNotify() {
        return this.notify;
    }

    public final boolean getRead() {
        return this.read;
    }

    public final boolean getReject() {
        return this.reject;
    }

    public final boolean getRemove() {
        return this.remove;
    }

    @Nullable
    public final String getReply() {
        return this.reply;
    }

    public int hashCode() {
        int iHashCode = Boolean.hashCode(this.remove) * 31;
        Long l10 = this.move;
        int iHashCode2 = (((((((((iHashCode + (l10 == null ? 0 : l10.hashCode())) * 31) + Boolean.hashCode(this.read)) * 31) + Boolean.hashCode(this.flag)) * 31) + this.forward.hashCode()) * 31) + this.notify.hashCode()) * 31;
        String str = this.reply;
        return ((iHashCode2 + (str != null ? str.hashCode() : 0)) * 31) + Boolean.hashCode(this.reject);
    }

    @NotNull
    public String toString() {
        return "ActionsDto(remove=" + this.remove + ", move=" + this.move + ", read=" + this.read + ", flag=" + this.flag + ", forward=" + this.forward + ", notify=" + this.notify + ", reply=" + this.reply + ", reject=" + this.reject + ")";
    }
}
