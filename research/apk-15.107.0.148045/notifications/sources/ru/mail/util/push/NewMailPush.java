package ru.mail.util.push;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import ru.mail.ads.banner.list.ui.MissingFieldsInfo;
import ru.mail.data.entities.RawId;
import ru.mail.logic.content.MailItemTransactionCategory;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.util.log.LogBuilder;
import ru.mail.util.log.LogPlaceholder;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@DatabaseTable(tableName = "notification")
public class NewMailPush extends PushMessage implements RawId<Integer> {
    public static final String COL_NAME_CUSTOM_SENDER = "custom_sender";
    public static final String COL_NAME_CUSTOM_SUBJECT = "custom_subject";
    public static final String COL_NAME_FOLDER_ID = "folder_id";
    public static final String COL_NAME_HAS_ATTACHMENTS = "has_attachments";
    public static final String COL_NAME_IMAGE_URL = "img_url";
    public static final String COL_NAME_IS_IMPORTANT = "important";
    public static final String COL_NAME_MAIL_CATEGORY = "transaction_category";
    public static final String COL_NAME_MESSAGE_ID = "message_id";
    public static final String COL_NAME_PUSH_ACK_URL = "push_ack_url";
    public static final String COL_NAME_REPLY_LAST_REQUEST_TIME = "last_request_time";
    public static final String COL_NAME_REPLY_REQUEST_EXECUTED_COUNTER = "request_executed_counter";
    public static final String COL_NAME_REPLY_SELECTED = "reply_selected";
    public static final String COL_NAME_SENDER = "sender";
    public static final String COL_NAME_SNIPPET = "snippet";
    public static final String COL_NAME_SUBJECT = "subject";
    public static final String COL_NAME_THREAD_HAS_MULTIPLE_MESSAGES = "thread_has_multiple_messages";
    public static final String COL_NAME_THREAD_ID = "thread_id";
    public static final String COL_NAME_TIME = "time";
    public static final Parcelable.Creator<NewMailPush> CREATOR = new Parcelable.Creator<NewMailPush>() { // from class: ru.mail.util.push.NewMailPush.1
        @Override // android.os.Parcelable.Creator
        public NewMailPush createFromParcel(Parcel parcel) {
            return new NewMailPush(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public NewMailPush[] newArray(int i10) {
            return new NewMailPush[i10];
        }
    };
    public static final String FOLDER_ID_INDEX = "new_mail_push_folder_id_index";
    public static final String MESSAGE_ID_INDEX = "new_mail_push_message_id_index";
    public static final String PUSH_ME_SDK_PUSH_ID = "push_me_sdk_push_id";
    public static final String TABLE_NAME = "notification";
    private static final long serialVersionUID = -1564609631578449792L;

    @DatabaseField(columnName = "transaction_category", dataType = DataType.ENUM_STRING)
    private MailItemTransactionCategory mCategory;

    @Nullable
    @DatabaseField(columnName = COL_NAME_CUSTOM_SENDER)
    private String mCustomSender;

    @Nullable
    @DatabaseField(columnName = COL_NAME_CUSTOM_SUBJECT)
    private String mCustomSubject;

    @DatabaseField(columnName = "folder_id", indexName = FOLDER_ID_INDEX)
    private long mFolderId;

    @DatabaseField(columnName = "has_attachments")
    private boolean mHasAttachments;

    @DatabaseField(columnName = "_id", generatedId = true)
    private int mId;

    @Nullable
    @DatabaseField(columnName = COL_NAME_IMAGE_URL)
    private String mImageUrl;

    @DatabaseField(columnName = COL_NAME_IS_IMPORTANT)
    private boolean mIsImportant;

    @DatabaseField(columnName = COL_NAME_REPLY_SELECTED)
    private boolean mIsReplySelected;

    @DatabaseField(columnName = COL_NAME_REPLY_LAST_REQUEST_TIME)
    private long mLastRequestedTime;

    @DatabaseField(columnName = "message_id", indexName = MESSAGE_ID_INDEX, uniqueCombo = true)
    private String mMessageId;

    @Nullable
    @DatabaseField(columnName = COL_NAME_PUSH_ACK_URL)
    private String mOpenUrl;

    @Nullable
    @DatabaseField(columnName = PUSH_ME_SDK_PUSH_ID)
    private Long mPushMeSdkPushId;

    @DatabaseField(columnName = COL_NAME_REPLY_REQUEST_EXECUTED_COUNTER)
    private int mRequestExecutedCount;

    @DatabaseField(columnName = "sender")
    private String mSender;

    @DatabaseField(columnName = "snippet")
    private String mSnippet;

    @DatabaseField(columnName = "subject")
    private String mSubject;

    @DatabaseField(columnName = COL_NAME_THREAD_HAS_MULTIPLE_MESSAGES)
    private boolean mThreadHasMultipleMessages;

    @Nullable
    @DatabaseField(columnName = "thread_id")
    private String mThreadId;

    @DatabaseField(columnName = "time")
    private long mTimestamp;

    public NewMailPush() {
        super(4);
        this.mCategory = MailItemTransactionCategory.NO_CATEGORIES;
    }

    @Override // ru.mail.util.push.PushMessageVisitable
    public ObservableFuture<Void> accept(@NonNull PushMessageVisitor pushMessageVisitor) {
        return pushMessageVisitor.visit(this);
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            NewMailPush newMailPush = (NewMailPush) obj;
            if (Objects.equals(this.mMessageId, newMailPush.mMessageId) && Objects.equals(this.mCustomSubject, newMailPush.mCustomSubject)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public String getCustomSender() {
        return this.mCustomSender;
    }

    @Nullable
    public String getCustomSubject() {
        return this.mCustomSubject;
    }

    public long getFolderId() {
        return this.mFolderId;
    }

    @Nullable
    public String getImageUrl() {
        return this.mImageUrl;
    }

    public long getLastRequestedTime() {
        return this.mLastRequestedTime;
    }

    public MailItemTransactionCategory getMailCategory() {
        return this.mCategory;
    }

    public String getMessageId() {
        return this.mMessageId;
    }

    @Nullable
    public String getOpenUrl() {
        return this.mOpenUrl;
    }

    @Nullable
    public Long getPushMeSdkPushId() {
        return this.mPushMeSdkPushId;
    }

    public int getRequestExecutedCount() {
        return this.mRequestExecutedCount;
    }

    public String getSender() {
        return this.mSender;
    }

    public String getSnippet() {
        return this.mSnippet;
    }

    public String getSubject() {
        return this.mSubject;
    }

    @Nullable
    public String getThreadId() {
        return this.mThreadId;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public boolean hasAttachments() {
        return this.mHasAttachments;
    }

    public int hashCode() {
        String str = this.mMessageId;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mCustomSubject;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public boolean isReminder() {
        return this.mCustomSubject != null;
    }

    public boolean isReplySelected() {
        return this.mIsReplySelected;
    }

    public boolean isThreadHasMultipleMessages() {
        return this.mThreadHasMultipleMessages;
    }

    public boolean ismIsImportant() {
        return this.mIsImportant;
    }

    public void setCustomSender(@Nullable String str) {
        this.mCustomSender = str;
    }

    public void setCustomSubject(@Nullable String str) {
        this.mCustomSubject = str;
    }

    public void setFolderId(long j10) {
        this.mFolderId = j10;
    }

    public void setHasAttachments(boolean z10) {
        this.mHasAttachments = z10;
    }

    public void setImageUrl(@Nullable String str) {
        this.mImageUrl = str;
    }

    public void setIsImportant(boolean z10) {
        this.mIsImportant = z10;
    }

    public void setLastRequestedTime(long j10) {
        this.mLastRequestedTime = j10;
    }

    public void setMailCategory(MailItemTransactionCategory mailItemTransactionCategory) {
        this.mCategory = mailItemTransactionCategory;
    }

    public void setMessageId(String str) {
        this.mMessageId = str;
    }

    public void setOpenUrl(@Nullable String str) {
        this.mOpenUrl = str;
    }

    public void setPushMeSdkPushId(@Nullable Long l10) {
        this.mPushMeSdkPushId = l10;
    }

    public void setRequestExecutedCount(int i10) {
        this.mRequestExecutedCount = i10;
    }

    public void setSender(String str) {
        this.mSender = str;
        if (str == null) {
            this.mSender = "";
        }
    }

    public void setSnippet(String str) {
        this.mSnippet = str;
    }

    public void setSubject(String str) {
        this.mSubject = str;
        if (str == null) {
            this.mSubject = "";
        }
    }

    public void setThreadHasMultipleMessages(boolean z10) {
        this.mThreadHasMultipleMessages = z10;
    }

    public void setThreadId(@Nullable String str) {
        this.mThreadId = str;
    }

    public void setTimestamp(long j10) {
        this.mTimestamp = j10;
    }

    @NotNull
    public String toString() {
        LogBuilder logBuilderAddLong = new LogBuilder().addObject("NewMailPush").addInteger("id", Integer.valueOf(this.mId)).addString("messageId", this.mMessageId).addLong("timestamp", Long.valueOf(this.mTimestamp)).addString("sender", this.mSender).addString("subject", LogPlaceholder.MSG_SUBJECT_PLACEHOLDER).addLong("folderId", Long.valueOf(this.mFolderId)).addBool("hasAttachments", Boolean.valueOf(this.mHasAttachments)).addBool("isImportant", Boolean.valueOf(this.mIsImportant)).addString("snippet", LogPlaceholder.MSG_SNIPPET_PLACEHOLDER).addString("threadId", this.mThreadId).addBool("threadHasMultipleMessages", Boolean.valueOf(this.mThreadHasMultipleMessages)).addString("category", this.mCategory.toString()).addString("ackUrl", this.mOpenUrl).addBool("isReplySelected", Boolean.valueOf(this.mIsReplySelected)).addLong("lastRequestedTime", Long.valueOf(this.mLastRequestedTime)).addInteger("requestExecutedCount", Integer.valueOf(this.mRequestExecutedCount)).addLong("pushMeSdkPushId", this.mPushMeSdkPushId);
        String str = this.mCustomSubject;
        if (str == null) {
            str = "null";
        }
        LogBuilder logBuilderAddString = logBuilderAddLong.addString("customSubject", str);
        String str2 = this.mCustomSender;
        if (str2 == null) {
            str2 = "null";
        }
        LogBuilder logBuilderAddString2 = logBuilderAddString.addString("customSender", str2);
        String str3 = this.mImageUrl;
        return logBuilderAddString2.addString(MissingFieldsInfo.FIELD_IMAGE_URL, str3 != null ? str3 : "null").endObject().build();
    }

    @Override // ru.mail.util.push.PushMessage, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i10) {
        super.writeToParcel(parcel, i10);
        parcel.writeInt(this.mId);
        parcel.writeString(this.mMessageId);
        parcel.writeLong(this.mTimestamp);
        parcel.writeString(this.mSender);
        parcel.writeString(this.mSubject);
        parcel.writeLong(this.mFolderId);
        parcel.writeByte(this.mHasAttachments ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsImportant ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mSnippet);
        parcel.writeString(this.mThreadId);
        parcel.writeByte(this.mThreadHasMultipleMessages ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mCategory.ordinal());
        parcel.writeString(this.mOpenUrl);
        parcel.writeByte(this.mIsReplySelected ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mLastRequestedTime);
        parcel.writeInt(this.mRequestExecutedCount);
        Long l10 = this.mPushMeSdkPushId;
        parcel.writeLong(l10 == null ? -1L : l10.longValue());
        parcel.writeString(this.mCustomSubject);
        parcel.writeString(this.mCustomSender);
        parcel.writeString(this.mImageUrl);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // ru.mail.data.entities.RawId
    public Integer getGeneratedId() {
        return Integer.valueOf(this.mId);
    }

    @Override // ru.mail.data.entities.RawId
    public void setGeneratedId(@NonNull Integer num) {
        this.mId = num.intValue();
    }

    NewMailPush(Parcel parcel) {
        super(parcel);
        this.mCategory = MailItemTransactionCategory.NO_CATEGORIES;
        this.mId = parcel.readInt();
        this.mMessageId = parcel.readString();
        this.mTimestamp = parcel.readLong();
        this.mSender = parcel.readString();
        this.mSubject = parcel.readString();
        this.mFolderId = parcel.readLong();
        this.mHasAttachments = parcel.readByte() == 1;
        this.mIsImportant = parcel.readByte() == 1;
        this.mSnippet = parcel.readString();
        this.mThreadId = parcel.readString();
        this.mThreadHasMultipleMessages = parcel.readByte() == 1;
        this.mCategory = MailItemTransactionCategory.values()[parcel.readInt()];
        this.mOpenUrl = parcel.readString();
        this.mIsReplySelected = parcel.readByte() == 1;
        this.mLastRequestedTime = parcel.readLong();
        this.mRequestExecutedCount = parcel.readInt();
        long j10 = parcel.readLong();
        this.mPushMeSdkPushId = j10 == -1 ? null : Long.valueOf(j10);
        this.mCustomSubject = parcel.readString();
        this.mCustomSender = parcel.readString();
        this.mImageUrl = parcel.readString();
    }
}
