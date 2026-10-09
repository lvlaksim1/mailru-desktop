package ru.mail.util.push;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lru/mail/util/push/PushEvent;", "", "<init>", "()V", "EVENT_PING", "", "EVENT_EMAIL", "EVENT_DELETE_NOTIFICATION", "EVENT_COUNT_PUSH", "EVENT_MOVE_MSG_PUSH", "EVENT_BAD_COOKIE", "EVENT_REMOTE_COMMAND", "EVENT_CALENDAR_NOTIFICATION", "EVENT_CALENDAR_NOTIFICATION_WITH_CALL", "EVENT_PROMOTE_URL", "EVENT_RESTORE_PASSWORD", "EVENT_ORDER_STATUS", "EVENT_PORTAL", "EVENT_WALLET", "EVENT_SEND_LOGS", "EVENT_RAT_BIND", "EVENT_EMAIL_REMINDER", "push_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushEvent {
    public static final int EVENT_BAD_COOKIE = 20;
    public static final int EVENT_CALENDAR_NOTIFICATION = 3000;
    public static final int EVENT_CALENDAR_NOTIFICATION_WITH_CALL = 3001;
    public static final int EVENT_COUNT_PUSH = 10;
    public static final int EVENT_DELETE_NOTIFICATION = 6;
    public static final int EVENT_EMAIL = 4;
    public static final int EVENT_EMAIL_REMINDER = 40;
    public static final int EVENT_MOVE_MSG_PUSH = 13;
    public static final int EVENT_ORDER_STATUS = 2002;
    public static final int EVENT_PING = 101;
    public static final int EVENT_PORTAL = 4001;
    public static final int EVENT_PROMOTE_URL = 1002;
    public static final int EVENT_RAT_BIND = 229;
    public static final int EVENT_REMOTE_COMMAND = 30;
    public static final int EVENT_RESTORE_PASSWORD = 1003;
    public static final int EVENT_SEND_LOGS = 228;
    public static final int EVENT_WALLET = 5000;

    @NotNull
    public static final PushEvent INSTANCE = new PushEvent();

    private PushEvent() {
    }
}
