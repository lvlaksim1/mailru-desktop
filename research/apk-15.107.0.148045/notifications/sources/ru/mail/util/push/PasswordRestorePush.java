package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class PasswordRestorePush extends PushMessage implements Parcelable {
    public static final Parcelable.Creator<PasswordRestorePush> CREATOR = new Parcelable.Creator<PasswordRestorePush>() { // from class: ru.mail.util.push.PasswordRestorePush.1
        @Override // android.os.Parcelable.Creator
        public PasswordRestorePush createFromParcel(Parcel parcel) {
            return new PasswordRestorePush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PasswordRestorePush[] newArray(int i10) {
            return new PasswordRestorePush[i10];
        }
    };
    private final String mEmail;
    private final String mTitle;
    private final String mUri;

    public PasswordRestorePush(int i10, String str, String str2, String str3) {
        super(i10);
        this.mUri = str;
        this.mEmail = str2;
        this.mTitle = str3;
    }

    @Override // ru.mail.util.push.PushMessageVisitable
    public ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor) {
        return pushMessageVisitor.visit(this);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getUri() {
        return this.mUri;
    }

    public String toString() {
        return "PassRestorePush mUri: " + this.mUri + ", mEmail: " + this.mEmail + ", mTitle: " + this.mTitle;
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeString(this.mUri);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mTitle);
    }

    protected PasswordRestorePush(Parcel parcel) {
        super(parcel);
        this.mUri = parcel.readString();
        this.mEmail = parcel.readString();
        this.mTitle = parcel.readString();
    }
}
