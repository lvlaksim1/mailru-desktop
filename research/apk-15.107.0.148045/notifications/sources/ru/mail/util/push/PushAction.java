package ru.mail.util.push;

import com.vk.pushme.logic.PendingAction;
import ru.mail.data.cmd.server.TornadoSendRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public enum PushAction {
    MARK_ALL_AS_READ("mark_all_read"),
    MARK_READ("read"),
    MARK_FLAG("mark_flag"),
    MARK_SPAM("mark_spam"),
    REPLY(TornadoSendRequest.FIELD_REPLY),
    DELETE("delete"),
    DELETE_ARCHIVE("delete_archive"),
    UNSUBSCRIBE(PendingAction.UNSUBSCRIBE_TYPE);

    private String mConfigurationName;

    PushAction(String str) {
        this.mConfigurationName = str;
    }

    public String getConfigurationName() {
        return this.mConfigurationName;
    }
}
