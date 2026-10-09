package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.j256.ormlite.field.DatabaseField;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public abstract class PushMessage implements Serializable, PushMessageVisitable, Parcelable {
    public static final String COL_NAME_PROFILE_ID = "profile_id";
    private static final long serialVersionUID = -5187762692702426658L;
    private int mEventId;

    @DatabaseField(columnName = "profile_id", uniqueCombo = true)
    private volatile String mProfileId;

    public PushMessage(int i10) {
        this(i10, null);
    }

    public int describeContents() {
        return 0;
    }

    public int getEventId() {
        return this.mEventId;
    }

    public String getProfileId() {
        return this.mProfileId;
    }

    public void overrideProfileId(String str) {
        this.mProfileId = str;
    }

    public void setProfileId(String str) {
        this.mProfileId = str;
        if (TextUtils.isEmpty(this.mProfileId)) {
            return;
        }
        this.mProfileId = this.mProfileId.toLowerCase(Locale.ENGLISH);
    }

    public void writeToParcel(Parcel parcel, int i10) {
        parcel.writeString(this.mProfileId);
        parcel.writeInt(this.mEventId);
    }

    public PushMessage(int i10, String str) {
        this.mEventId = i10;
        this.mProfileId = str;
    }

    public PushMessage(Parcel parcel) {
        this.mProfileId = parcel.readString();
        this.mEventId = parcel.readInt();
    }

    public PushMessage(PushMessage pushMessage) {
        this.mProfileId = pushMessage.mProfileId;
        this.mEventId = pushMessage.mEventId;
    }
}
