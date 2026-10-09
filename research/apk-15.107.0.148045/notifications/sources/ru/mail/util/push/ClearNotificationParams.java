package ru.mail.util.push;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public final class ClearNotificationParams {
    private Long mFolderId;
    private List<String> mMessageIds;
    private final String mProfileId;
    private boolean mShouldUpdateNotifications;
    private String mThreadId;

    /* JADX INFO: compiled from: ProGuard */
    public static class Builder {
        private final ClearNotificationParams mConfig;

        public Builder(String str) {
            this.mConfig = new ClearNotificationParams(str);
        }

        public ClearNotificationParams build() {
            return this.mConfig;
        }

        public Builder dontUpdateNotifications() {
            this.mConfig.mShouldUpdateNotifications = false;
            return this;
        }

        public Builder setFolderId(long j10) {
            if (j10 >= 0) {
                this.mConfig.mFolderId = Long.valueOf(j10);
            }
            return this;
        }

        public Builder setMessageIds(@Nullable List<String> list) {
            if (list != null && list.size() > 0) {
                this.mConfig.mMessageIds = new ArrayList(list);
            }
            return this;
        }

        public Builder setThreadId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mConfig.mThreadId = str;
            }
            return this;
        }
    }

    @Nullable
    public Long getFolderId() {
        return this.mFolderId;
    }

    @Nullable
    public List<String> getMessageIds() {
        if (this.mMessageIds != null) {
            return new ArrayList(this.mMessageIds);
        }
        return null;
    }

    public String getProfileId() {
        return this.mProfileId;
    }

    @Nullable
    public String getThreadId() {
        return this.mThreadId;
    }

    public boolean shouldUpdateNotifications() {
        return this.mShouldUpdateNotifications;
    }

    public String toString() {
        return "ClearNotificationParams{mProfileId='" + this.mProfileId + "', mMessageIds=" + this.mMessageIds + ", mFolderId=" + this.mFolderId + ", mThreadId='" + this.mThreadId + "', mShouldUpdateNotifications=" + this.mShouldUpdateNotifications + AbstractJsonLexerKt.END_OBJ;
    }

    private ClearNotificationParams(String str) {
        this.mShouldUpdateNotifications = true;
        this.mProfileId = str;
    }
}
