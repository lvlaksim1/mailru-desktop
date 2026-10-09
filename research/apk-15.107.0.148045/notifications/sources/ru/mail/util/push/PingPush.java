package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.AlreadyDoneObservableFuture;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class PingPush extends PushMessage {
    public static final Parcelable.Creator<PingPush> CREATOR = new Parcelable.Creator<PingPush>() { // from class: ru.mail.util.push.PingPush.1
        @Override // android.os.Parcelable.Creator
        public PingPush createFromParcel(Parcel parcel) {
            return new PingPush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PingPush[] newArray(int i10) {
            return new PingPush[i10];
        }
    };
    private final String mRequestUrl;

    @Override // ru.mail.util.push.PushMessageVisitable
    public ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor) {
        return !TextUtils.isEmpty(this.mRequestUrl) ? pushMessageVisitor.visit(this) : new AlreadyDoneObservableFuture(null);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getRequestUrl() {
        return this.mRequestUrl;
    }

    public String toString() {
        return "Ping";
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeString(this.mRequestUrl);
    }

    public PingPush(String str) {
        super(101);
        this.mRequestUrl = str;
    }

    private PingPush(Parcel parcel) {
        super(parcel);
        this.mRequestUrl = parcel.readString();
    }
}
