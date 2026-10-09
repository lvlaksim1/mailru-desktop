package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class CountPush extends PushMessage {
    public static final Parcelable.Creator<CountPush> CREATOR = new Parcelable.Creator<CountPush>() { // from class: ru.mail.util.push.CountPush.1
        @Override // android.os.Parcelable.Creator
        public CountPush createFromParcel(Parcel parcel) {
            return new CountPush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CountPush[] newArray(int i10) {
            return new CountPush[i10];
        }
    };
    private static final long serialVersionUID = 568715346506504204L;
    private int mCounter;
    private int mCounterAccount;

    @Override // ru.mail.util.push.PushMessageVisitable
    public ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor) {
        return pushMessageVisitor.visit(this);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCounter() {
        return this.mCounter;
    }

    public int getCounterAccount() {
        return this.mCounterAccount;
    }

    public void setCounter(int i10) {
        this.mCounter = i10;
    }

    public void setCounterAccount(int i10) {
        this.mCounterAccount = i10;
    }

    public String toString() {
        return String.format("CountPush [EventId=%d, Counter=%d]", Integer.valueOf(getEventId()), Integer.valueOf(this.mCounter));
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeInt(this.mCounter);
        parcel.writeInt(this.mCounterAccount);
    }

    private CountPush(Parcel parcel) {
        super(parcel);
        this.mCounter = parcel.readInt();
        this.mCounterAccount = parcel.readInt();
    }

    public CountPush() {
        super(10);
    }
}
