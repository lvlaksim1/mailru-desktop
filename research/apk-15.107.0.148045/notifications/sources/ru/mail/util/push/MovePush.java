package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.Collector;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB)\b\u0016\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\u0007\u0010\rJ\u0018\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001aH\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lru/mail/util/push/MovePush;", "Lru/mail/util/push/PushMessage;", RemoteMessageConst.MSGID, "", "folderFrom", "", "folderTo", "<init>", "(Ljava/lang/String;JJ)V", "account", "(Ljava/lang/String;Ljava/lang/String;JJ)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "getMsgId", "()Ljava/lang/String;", "getFolderTo", "()J", "accept", "Lru/mail/mailbox/cmd/ObservableFuture;", "Ljava/lang/Void;", "visitor", "Lru/mail/util/push/PushMessageVisitor;", "writeToParcel", "", Collector.FLAGS, "", "describeContents", "CREATOR", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovePush extends PushMessage {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private final long folderFrom;
    private final long folderTo;

    @Nullable
    private final String msgId;

    /* JADX INFO: renamed from: ru.mail.util.push.MovePush$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lru/mail/util/push/MovePush$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lru/mail/util/push/MovePush;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lru/mail/util/push/MovePush;", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<MovePush> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public MovePush createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new MovePush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public MovePush[] newArray(int size) {
            return new MovePush[size];
        }
    }

    public MovePush(@Nullable String str, long j10, long j11) {
        super(13);
        this.msgId = str;
        this.folderFrom = j10;
        this.folderTo = j11;
    }

    @Override // ru.mail.util.push.PushMessageVisitable
    @Nullable
    public ObservableFuture<Void> accept(@NotNull PushMessageVisitor visitor) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        return visitor.visit(this);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final long getFolderTo() {
        return this.folderTo;
    }

    @Nullable
    public final String getMsgId() {
        return this.msgId;
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        super.writeToParcel(parcel, flags);
        parcel.writeString(this.msgId);
        parcel.writeLong(this.folderFrom);
        parcel.writeLong(this.folderTo);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MovePush(@NotNull String account, @NotNull String msgId, long j10, long j11) {
        this(msgId, j10, j11);
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        setProfileId(account);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MovePush(@NotNull Parcel parcel) {
        this(parcel.readString(), parcel.readLong(), parcel.readLong());
        Intrinsics.checkNotNullParameter(parcel, "parcel");
    }
}
