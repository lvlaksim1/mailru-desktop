package ru.mail.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"Lru/mail/push/MailNotificationShowManager;", "Lru/mail/push/BaseNotificationShowManager;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "isShowNotifications", "", "updateShowNotifications", "", "enabled", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailNotificationShowManager extends BaseNotificationShowManager {

    @NotNull
    private static final String MAIL_APP_NAME = "mail_app";

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/push/MailNotificationShowManager$Companion;", "", "<init>", "()V", "MAIL_APP_NAME", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MailNotificationShowManager(@NotNull Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    @Override // ru.mail.util.push.NotificationShowManager
    public boolean isShowNotifications() {
        return isShowNotificationsForApp(MAIL_APP_NAME);
    }

    @Override // ru.mail.util.push.NotificationShowManager
    public void updateShowNotifications(boolean enabled) {
        updateShowNotificationsForApp(MAIL_APP_NAME, enabled);
    }
}
