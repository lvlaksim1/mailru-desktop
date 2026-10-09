package ru.mail.util.push;

import android.content.Intent;
import com.huawei.hms.support.api.entity.core.CommonCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u001a\u0010\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"EXTRA_FROM_NOTIFICATION", "", "isLaunchFromPush", "", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "isLaunchFromNotification", "mails_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PushLaunchKt {

    @NotNull
    public static final String EXTRA_FROM_NOTIFICATION = "from_notification";

    public static final boolean isLaunchFromNotification(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return isLaunchFromPush(intent) || intent.getBooleanExtra(EXTRA_FROM_NOTIFICATION, false);
    }

    public static final boolean isLaunchFromPush(@Nullable Intent intent) {
        if (intent == null) {
            return false;
        }
        return Intrinsics.areEqual(IntentActionsProvider.actionShowPushMessageInFolder, intent.getAction()) || Intrinsics.areEqual(IntentActionsProvider.actionShowPushMessage, intent.getAction()) || Intrinsics.areEqual(IntentActionsProvider.actionShowPushThreadMessage, intent.getAction()) || intent.getBooleanExtra(NotificationUpdater.EXTRA_FROM_PUSH, false);
    }
}
