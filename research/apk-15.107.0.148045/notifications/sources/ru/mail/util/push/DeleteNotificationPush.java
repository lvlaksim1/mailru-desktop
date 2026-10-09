package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class DeleteNotificationPush extends PushMessage {
    public static final Parcelable.Creator<DeleteNotificationPush> CREATOR = new Parcelable.Creator<DeleteNotificationPush>() { // from class: ru.mail.util.push.DeleteNotificationPush.1
        @Override // android.os.Parcelable.Creator
        public DeleteNotificationPush createFromParcel(Parcel parcel) {
            return new DeleteNotificationPush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public DeleteNotificationPush[] newArray(int i10) {
            return new DeleteNotificationPush[i10];
        }
    };
    private static final long serialVersionUID = 7614355126682295529L;
    private String mCollapseKey;
    private boolean mIsNeedDeleteMsg;
    private String mMessageId;

    @Override // ru.mail.util.push.PushMessageVisitable
    public ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor) {
        return pushMessageVisitor.visit(this);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCollapseKey() {
        return this.mCollapseKey;
    }

    public boolean getIsNeedDeleteMsg() {
        return this.mIsNeedDeleteMsg;
    }

    public String getMessageId() {
        return this.mMessageId;
    }

    public void setCollapseKey(String str) {
        this.mCollapseKey = str;
    }

    public void setIsNeedDeleteMsg(String str) {
        setIsNeedDeleteMsg(str != null && str.equalsIgnoreCase("true"));
    }

    public void setMessageId(String str) {
        this.mMessageId = str;
    }

    public String toString() {
        return "DeleteNotification msgId:" + this.mMessageId + ", collapseKey:" + this.mCollapseKey + ", isNeedDeleteMsg:" + this.mIsNeedDeleteMsg;
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeString(this.mMessageId);
        parcel.writeString(this.mCollapseKey);
        parcel.writeBoolean(this.mIsNeedDeleteMsg);
    }

    private DeleteNotificationPush(Parcel parcel) {
        super(parcel);
        this.mMessageId = parcel.readString();
        this.mCollapseKey = parcel.readString();
        this.mIsNeedDeleteMsg = parcel.readBoolean();
    }

    public void setIsNeedDeleteMsg(boolean z10) {
        this.mIsNeedDeleteMsg = z10;
    }

    public DeleteNotificationPush() {
        super(6);
    }
}
