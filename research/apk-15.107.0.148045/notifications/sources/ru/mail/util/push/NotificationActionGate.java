package ru.mail.util.push;

import android.content.Intent;
import com.huawei.hms.support.api.entity.core.CommonCode;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/NotificationActionGate;", "", "acceptNotificationAction", "", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "shouldPublishNewMailNotification", "account", "", "messageId", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface NotificationActionGate {
    boolean acceptNotificationAction(@NotNull Intent intent);

    boolean shouldPublishNewMailNotification(@NotNull String account, @NotNull String messageId);
}
