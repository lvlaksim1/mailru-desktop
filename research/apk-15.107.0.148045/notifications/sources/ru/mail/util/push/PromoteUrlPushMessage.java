package ru.mail.util.push;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.Locale;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailbox.cmd.ObservableFuture;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class PromoteUrlPushMessage extends PushMessage implements Parcelable {
    public static final Parcelable.Creator<PromoteUrlPushMessage> CREATOR = new Parcelable.Creator<PromoteUrlPushMessage>() { // from class: ru.mail.util.push.PromoteUrlPushMessage.1
        @Override // android.os.Parcelable.Creator
        public PromoteUrlPushMessage createFromParcel(Parcel parcel) {
            return new PromoteUrlPushMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public PromoteUrlPushMessage[] newArray(int i10) {
            return new PromoteUrlPushMessage[i10];
        }
    };

    @Nullable
    private final String mLangFilter;

    @Nullable
    private final String mMedia;
    private final String mOpenUrl;

    @Nullable
    private final Long mPushMeSdkPushId;
    private final String mText;
    private final String mTitle;
    private final String mType;
    private final Uri mUri;

    public PromoteUrlPushMessage(int i10, Uri uri, String str, String str2, @Nullable String str3, @Nullable String str4, @NonNull String str5, @Nullable String str6, @Nullable Long l10, @Nullable String str7) {
        super(i10, str4);
        this.mUri = uri;
        this.mTitle = str;
        this.mText = str2;
        this.mLangFilter = str3;
        this.mType = str5;
        this.mOpenUrl = str6;
        this.mPushMeSdkPushId = l10;
        this.mMedia = str7;
    }

    @Override // ru.mail.util.push.PushMessageVisitable
    public ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor) {
        return pushMessageVisitor.visit(this);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public String getMedia() {
        return this.mMedia;
    }

    public String getOpenUrl() {
        return this.mOpenUrl;
    }

    @Nullable
    public Long getPushMeSdkPushId() {
        return this.mPushMeSdkPushId;
    }

    public String getText() {
        return this.mText;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @NonNull
    public String getType() {
        return this.mType;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public boolean isApplicable(Context context) {
        if (TextUtils.isEmpty(this.mLangFilter)) {
            return true;
        }
        return context.getResources().getConfiguration().locale.getLanguage().equals(new Locale(this.mLangFilter).getLanguage());
    }

    @NonNull
    public String toString() {
        return "PromoteUrlPushMessage mUri:" + this.mUri + ", mTitle:'" + this.mTitle + "', mText:'" + this.mText + "', mAcc:'" + getProfileId() + "', mLangFilter:'" + this.mLangFilter + "', mType:'" + this.mType + "', mPushMeSdkPushId:'" + this.mPushMeSdkPushId + "', mMedia:'" + this.mMedia + '\'';
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeParcelable(this.mUri, i10);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mText);
        parcel.writeString(this.mLangFilter);
        parcel.writeString(this.mType);
        Long l10 = this.mPushMeSdkPushId;
        parcel.writeLong(l10 == null ? -1L : l10.longValue());
        parcel.writeString(this.mMedia);
    }

    protected PromoteUrlPushMessage(Parcel parcel) {
        super(parcel);
        this.mUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.mTitle = parcel.readString();
        this.mText = parcel.readString();
        this.mLangFilter = parcel.readString();
        this.mType = parcel.readString();
        this.mOpenUrl = parcel.readString();
        long j10 = parcel.readLong();
        this.mPushMeSdkPushId = j10 == -1 ? null : Long.valueOf(j10);
        this.mMedia = parcel.readString();
    }
}
