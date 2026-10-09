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
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 -2\u00020\u0001:\u0001-B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\t\u0010\rB9\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\u0010J\u0018\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\b\u0010 \u001a\u00020\u0006H\u0016J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0006HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J;\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020\u0006HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012¨\u0006."}, d2 = {"Lru/mail/util/push/OrderPush;", "Lru/mail/util/push/PushMessage;", RemoteMessageConst.MSGID, "", "threadId", "folderId", "", "newOrderStatus", "newExtendStatus", "<init>", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "account", "newExtendOrderStatus", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getMsgId", "()Ljava/lang/String;", "getThreadId", "getFolderId", "()I", "getNewOrderStatus", "getNewExtendStatus", "accept", "Lru/mail/mailbox/cmd/ObservableFuture;", "Ljava/lang/Void;", "visitor", "Lru/mail/util/push/PushMessageVisitor;", "writeToParcel", "", Collector.FLAGS, "describeContents", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "toString", "CREATOR", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class OrderPush extends PushMessage {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private final int folderId;

    @NotNull
    private final String msgId;

    @NotNull
    private final String newExtendStatus;

    @NotNull
    private final String newOrderStatus;

    @NotNull
    private final String threadId;

    /* JADX INFO: renamed from: ru.mail.util.push.OrderPush$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001d\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lru/mail/util/push/OrderPush$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lru/mail/util/push/OrderPush;", "<init>", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lru/mail/util/push/OrderPush;", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion implements Parcelable.Creator<OrderPush> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public OrderPush createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new OrderPush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public OrderPush[] newArray(int size) {
            return new OrderPush[size];
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OrderPush(@NotNull String msgId, @NotNull String threadId, int i10, @NotNull String newOrderStatus, @NotNull String newExtendStatus) {
        super(2002);
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        Intrinsics.checkNotNullParameter(threadId, "threadId");
        Intrinsics.checkNotNullParameter(newOrderStatus, "newOrderStatus");
        Intrinsics.checkNotNullParameter(newExtendStatus, "newExtendStatus");
        this.msgId = msgId;
        this.threadId = threadId;
        this.folderId = i10;
        this.newOrderStatus = newOrderStatus;
        this.newExtendStatus = newExtendStatus;
    }

    public static /* synthetic */ OrderPush copy$default(OrderPush orderPush, String str, String str2, int i10, String str3, String str4, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = orderPush.msgId;
        }
        if ((i11 & 2) != 0) {
            str2 = orderPush.threadId;
        }
        if ((i11 & 4) != 0) {
            i10 = orderPush.folderId;
        }
        if ((i11 & 8) != 0) {
            str3 = orderPush.newOrderStatus;
        }
        if ((i11 & 16) != 0) {
            str4 = orderPush.newExtendStatus;
        }
        String str5 = str4;
        int i12 = i10;
        return orderPush.copy(str, str2, i12, str3, str5);
    }

    @Override // ru.mail.util.push.PushMessageVisitable
    @Nullable
    public ObservableFuture<Void> accept(@NotNull PushMessageVisitor visitor) {
        Intrinsics.checkNotNullParameter(visitor, "visitor");
        return visitor.visit(this);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getMsgId() {
        return this.msgId;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getThreadId() {
        return this.threadId;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final int getFolderId() {
        return this.folderId;
    }

    @NotNull
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getNewOrderStatus() {
        return this.newOrderStatus;
    }

    @NotNull
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getNewExtendStatus() {
        return this.newExtendStatus;
    }

    @NotNull
    public final OrderPush copy(@NotNull String msgId, @NotNull String threadId, int folderId, @NotNull String newOrderStatus, @NotNull String newExtendStatus) {
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        Intrinsics.checkNotNullParameter(threadId, "threadId");
        Intrinsics.checkNotNullParameter(newOrderStatus, "newOrderStatus");
        Intrinsics.checkNotNullParameter(newExtendStatus, "newExtendStatus");
        return new OrderPush(msgId, threadId, folderId, newOrderStatus, newExtendStatus);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OrderPush)) {
            return false;
        }
        OrderPush orderPush = (OrderPush) other;
        return Intrinsics.areEqual(this.msgId, orderPush.msgId) && Intrinsics.areEqual(this.threadId, orderPush.threadId) && this.folderId == orderPush.folderId && Intrinsics.areEqual(this.newOrderStatus, orderPush.newOrderStatus) && Intrinsics.areEqual(this.newExtendStatus, orderPush.newExtendStatus);
    }

    public final int getFolderId() {
        return this.folderId;
    }

    @NotNull
    public final String getMsgId() {
        return this.msgId;
    }

    @NotNull
    public final String getNewExtendStatus() {
        return this.newExtendStatus;
    }

    @NotNull
    public final String getNewOrderStatus() {
        return this.newOrderStatus;
    }

    @NotNull
    public final String getThreadId() {
        return this.threadId;
    }

    public int hashCode() {
        return (((((((this.msgId.hashCode() * 31) + this.threadId.hashCode()) * 31) + Integer.hashCode(this.folderId)) * 31) + this.newOrderStatus.hashCode()) * 31) + this.newExtendStatus.hashCode();
    }

    @NotNull
    public String toString() {
        return "OrderPush(msgId=" + this.msgId + ", threadId=" + this.threadId + ", folderId=" + this.folderId + ", newOrderStatus=" + this.newOrderStatus + ", newExtendStatus=" + this.newExtendStatus + ")";
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(getProfileId());
        parcel.writeString(this.msgId);
        parcel.writeString(this.threadId);
        parcel.writeInt(this.folderId);
        parcel.writeString(this.newOrderStatus);
        parcel.writeString(this.newExtendStatus);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OrderPush(@NotNull Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        Intrinsics.checkNotNull(string);
        String string2 = parcel.readString();
        Intrinsics.checkNotNull(string2);
        String string3 = parcel.readString();
        Intrinsics.checkNotNull(string3);
        int i10 = parcel.readInt();
        String string4 = parcel.readString();
        Intrinsics.checkNotNull(string4);
        String string5 = parcel.readString();
        Intrinsics.checkNotNull(string5);
        this(string, string2, string3, i10, string4, string5);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OrderPush(@NotNull String account, @NotNull String msgId, @NotNull String threadId, int i10, @NotNull String newOrderStatus, @NotNull String newExtendOrderStatus) {
        this(msgId, threadId, i10, newOrderStatus, newExtendOrderStatus);
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(msgId, "msgId");
        Intrinsics.checkNotNullParameter(threadId, "threadId");
        Intrinsics.checkNotNullParameter(newOrderStatus, "newOrderStatus");
        Intrinsics.checkNotNullParameter(newExtendOrderStatus, "newExtendOrderStatus");
        setProfileId(account);
    }
}
