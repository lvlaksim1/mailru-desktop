package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.constant.RemoteMessageConst;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Singleton
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0013\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelGenerationStore;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "currentChannelId", "", "rotateChannelId", RemoteMessageConst.Notification.CHANNEL_ID, SendQueueNotificationChannelGenerationStore.KEY_GENERATION, "", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SendQueueNotificationChannelGenerationStore {

    @Deprecated
    @NotNull
    public static final String CHANNEL_ID_PREFIX = "send_queue_channel";

    @Deprecated
    public static final int INITIAL_GENERATION = 0;

    @Deprecated
    @NotNull
    public static final String KEY_GENERATION = "generation";

    @Deprecated
    @NotNull
    public static final String PREFERENCES_NAME = "send_queue_notification_channel";
    private final SharedPreferences preferences;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelGenerationStore$Companion;", "", "<init>", "()V", "PREFERENCES_NAME", "", "KEY_GENERATION", "CHANNEL_ID_PREFIX", "INITIAL_GENERATION", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Inject
    public SendQueueNotificationChannelGenerationStore(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.preferences = context.getSharedPreferences(PREFERENCES_NAME, 0);
    }

    private final String channelId(int generation) {
        return "send_queue_channel_" + generation;
    }

    @NotNull
    public final String currentChannelId() {
        return channelId(this.preferences.getInt(KEY_GENERATION, 0));
    }

    @NotNull
    public final synchronized String rotateChannelId() {
        int i10;
        i10 = this.preferences.getInt(KEY_GENERATION, 0) + 1;
        if (!this.preferences.edit().putInt(KEY_GENERATION, i10).commit()) {
            throw new IllegalStateException("Unable to persist send queue notification channel generation");
        }
        return channelId(i10);
    }
}
