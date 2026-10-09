package ru.mail.push;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.NotificationShowManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0004J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u0007H\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/push/BaseNotificationShowManager;", "Lru/mail/util/push/NotificationShowManager;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "isShowNotificationsForApp", "", "app", "", "updateShowNotificationsForApp", "", "value", "getPreference", "Landroid/content/SharedPreferences;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseNotificationShowManager implements NotificationShowManager {

    @NotNull
    private static final String DEFAULT_PREF_NAME = "notification_show_manager";

    @NotNull
    private static final String IS_SHOW_NOTIFICATIONS_KEY = "is_show_notifications";

    @NotNull
    private final Context context;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lru/mail/push/BaseNotificationShowManager$Companion;", "", "<init>", "()V", "DEFAULT_PREF_NAME", "", "IS_SHOW_NOTIFICATIONS_KEY", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public BaseNotificationShowManager(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final SharedPreferences getPreference() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences(DEFAULT_PREF_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        return sharedPreferences;
    }

    protected final boolean isShowNotificationsForApp(@NotNull String app) {
        Intrinsics.checkNotNullParameter(app, "app");
        return getPreference().getBoolean("is_show_notifications_" + app, true);
    }

    protected final void updateShowNotificationsForApp(@NotNull String app, boolean value) {
        Intrinsics.checkNotNullParameter(app, "app");
        getPreference().edit().putBoolean("is_show_notifications_" + app, value).apply();
    }
}
