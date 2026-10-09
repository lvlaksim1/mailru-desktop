package ru.mail.util.push;

import android.text.TextUtils;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;
import ru.mail.data.cmd.database.InsertPosition;
import ru.mail.data.cmd.database.SyncObserver;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.content.MailListItem;
import ru.mail.logic.content.visitors.MailMessageItemVisitor;
import ru.mail.ui.fragments.settings.threads.ThreadsLocalStorage;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class LocalPushNotifierAdapter extends LocalNotificationsBundle implements SyncObserver<MailListItem<String>> {
    private static final Log LOG = Log.getLog("LocalPushNotifierAdapter");
    private final LocalPushHandler mLocalPushHandler;
    private final String mLogin;
    private final ThreadsLocalStorage mMailGroupingChangeStorage;
    private final List<MailMessage> mMessagesToShow = new LinkedList();
    private final List<MailMessage> mMessagesToHide = new LinkedList();
    private final MailMessageItemVisitor mMailMessageItemVisitor = new MailMessageItemVisitor();

    public LocalPushNotifierAdapter(LocalPushHandler localPushHandler, ThreadsLocalStorage threadsLocalStorage, String str) {
        this.mLocalPushHandler = localPushHandler;
        this.mLogin = str;
        this.mMailGroupingChangeStorage = threadsLocalStorage;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LocalPushNotifierAdapter localPushNotifierAdapter = (LocalPushNotifierAdapter) obj;
        if (this.mMessagesToShow.equals(localPushNotifierAdapter.mMessagesToShow)) {
            return this.mMessagesToHide.equals(localPushNotifierAdapter.mMessagesToHide);
        }
        return false;
    }

    @Override // ru.mail.util.push.LocalNotificationsBundle
    public List<MailMessage> getMessagesToHide() {
        return Collections.unmodifiableList(this.mMessagesToHide);
    }

    @Override // ru.mail.util.push.LocalNotificationsBundle
    public List<MailMessage> getMessagesToShow() {
        return Collections.unmodifiableList(this.mMessagesToShow);
    }

    public int hashCode() {
        return (this.mMessagesToShow.hashCode() * 31) + this.mMessagesToHide.hashCode();
    }

    @Override // ru.mail.data.cmd.database.SyncObserver
    public void onSyncFinished() {
        if (this.mMailGroupingChangeStorage.isEnabled(this.mLogin)) {
            this.mMailGroupingChangeStorage.setEnabled(this.mLogin, false);
        } else {
            populate(this.mLocalPushHandler);
        }
        this.mMessagesToShow.clear();
        this.mMessagesToHide.clear();
    }

    public String toString() {
        return String.format("LocalPushAdapter { show: [%s], hide: [%s] }", TextUtils.join(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, MailMessage.extractIds(this.mMessagesToShow)), TextUtils.join(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, MailMessage.extractIds(this.mMessagesToHide)));
    }

    @Override // ru.mail.data.cmd.database.MergeEvent.Visitor
    public void onItemChanged(MailListItem<String> mailListItem, MailListItem<String> mailListItem2) {
        if (((Boolean) mailListItem.acceptVisitor(this.mMailMessageItemVisitor)).booleanValue() && ((Boolean) mailListItem2.acceptVisitor(this.mMailMessageItemVisitor)).booleanValue()) {
            MailMessage mailMessage = (MailMessage) mailListItem2;
            if (!((MailMessage) mailListItem).isUnread() || mailMessage.isUnread()) {
                return;
            }
            LOG.v("Message " + mailListItem2 + " will be hidden");
            this.mMessagesToHide.add(mailMessage);
        }
    }

    @Override // ru.mail.data.cmd.database.MergeEvent.Visitor
    public void onItemDeleted(MailListItem<String> mailListItem) {
        if (((Boolean) mailListItem.acceptVisitor(this.mMailMessageItemVisitor)).booleanValue()) {
            MailMessage mailMessage = (MailMessage) mailListItem;
            LOG.v("Message " + mailMessage + " will be hidden");
            this.mMessagesToHide.add(mailMessage);
        }
    }

    @Override // ru.mail.data.cmd.database.MergeEvent.Visitor
    public void onItemInserted(MailListItem<String> mailListItem, InsertPosition insertPosition) {
        if (((Boolean) mailListItem.acceptVisitor(this.mMailMessageItemVisitor)).booleanValue()) {
            MailMessage mailMessage = (MailMessage) mailListItem;
            if (insertPosition == InsertPosition.ABOVE_ALL && mailMessage.isUnread()) {
                LOG.v("Message " + mailMessage + " will be shown");
                this.mMessagesToShow.add(mailMessage);
            }
        }
    }

    @Override // ru.mail.data.cmd.database.SyncObserver
    public void onSyncStarted() {
    }
}
