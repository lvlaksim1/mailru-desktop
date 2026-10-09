package ru.mail.logic.sendmessage.queue.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.mails.R;
import ru.mail.messagesend.queue.SendQueueState;
import ru.mail.messagesend.queue.SendQueueStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Singleton
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0011\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationRenderer;", "", "context", "Landroid/content/Context;", "channelManager", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelManager;", "<init>", "(Landroid/content/Context;Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationChannelManager;)V", "render", "Landroid/app/Notification;", "state", "Lru/mail/messagesend/queue/SendQueueState;", RemoteMessageConst.Notification.CHANNEL_ID, "", "ensureChannel", "title", "content", "action", "Landroidx/core/app/NotificationCompat$Action;", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SendQueueNotificationRenderer {
    public static final int $stable = 8;

    @NotNull
    private final SendQueueNotificationChannelManager channelManager;

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SendQueueStatus.values().length];
            try {
                iArr[SendQueueStatus.SENDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[SendQueueStatus.QUEUED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[SendQueueStatus.WAITING_FOR_NETWORK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[SendQueueStatus.PAUSED_FAILED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[SendQueueStatus.PAUSED_TIMEOUT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[SendQueueStatus.COMPLETED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[SendQueueStatus.EMPTY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public SendQueueNotificationRenderer(@ApplicationContext @NotNull Context context, @NotNull SendQueueNotificationChannelManager channelManager) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(channelManager, "channelManager");
        this.context = context;
        this.channelManager = channelManager;
    }

    private final NotificationCompat.Action action(SendQueueNotificationAction action) {
        Intent intent = new Intent(this.context, (Class<?>) SendQueueNotificationActionReceiver.class).setAction(action.getIntentAction()).setPackage(this.context.getPackageName());
        Intrinsics.checkNotNullExpressionValue(intent, "setPackage(...)");
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(action.getIconRes(), action.title(this.context), PendingIntent.getBroadcast(this.context, action.ordinal(), intent, 201326592)).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }

    private final String content(SendQueueState state) {
        switch (WhenMappings.$EnumSwitchMapping$0[state.getStatus().ordinal()]) {
            case 1:
                return this.context.getString(R.string.send_queue_progress, Integer.valueOf(RangesKt.coerceAtLeast(state.getCurrent(), 1)), Integer.valueOf(state.getTotal()), Integer.valueOf(state.getSucceeded()), Integer.valueOf(state.getFailed()));
            case 2:
                return this.context.getString(R.string.send_queue_remaining, Integer.valueOf(state.getPending()));
            case 3:
                return this.context.getString(R.string.send_queue_remaining_with_results, Integer.valueOf(state.getPending()), Integer.valueOf(state.getSucceeded()), Integer.valueOf(state.getFailed()));
            case 4:
                return this.context.getString(R.string.send_queue_result, Integer.valueOf(state.getSucceeded()), Integer.valueOf(state.getFailed()));
            case 5:
                return this.context.getString(R.string.send_queue_timeout_result, Integer.valueOf(state.getSucceeded()), Integer.valueOf(state.getPending()));
            case 6:
                return null;
            case 7:
                throw new IllegalStateException("Empty state cannot be rendered");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static /* synthetic */ Notification render$default(SendQueueNotificationRenderer sendQueueNotificationRenderer, SendQueueState sendQueueState, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = sendQueueNotificationRenderer.ensureChannel();
        }
        return sendQueueNotificationRenderer.render(sendQueueState, str);
    }

    private final String title(SendQueueState state) {
        switch (WhenMappings.$EnumSwitchMapping$0[state.getStatus().ordinal()]) {
            case 1:
                String string = this.context.getString(R.string.notification_sending_message);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            case 2:
            case 3:
                String string2 = this.context.getString(R.string.notification_waiting_for_send);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                return string2;
            case 4:
                String string3 = this.context.getString(R.string.send_queue_completed_with_errors);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                return string3;
            case 5:
                String string4 = this.context.getString(R.string.send_queue_timeout);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
                return string4;
            case 6:
                String quantityString = this.context.getResources().getQuantityString(R.plurals.send_queue_completed, state.getSucceeded(), Integer.valueOf(state.getSucceeded()));
                Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
                return quantityString;
            case 7:
                throw new IllegalStateException("Empty state cannot be rendered");
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    public final String ensureChannel() {
        return this.channelManager.ensureChannel();
    }

    @NotNull
    public final Notification render(@NotNull SendQueueState state, @NotNull String channelId) {
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        if (state.getStatus() == SendQueueStatus.EMPTY) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        String strContent = content(state);
        boolean z10 = state.getStatus() == SendQueueStatus.QUEUED || state.getStatus() == SendQueueStatus.SENDING || state.getStatus() == SendQueueStatus.WAITING_FOR_NETWORK;
        NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(this.context, channelId).setSmallIcon(R.drawable.ic_status_bar_send).setColor(ContextCompat.getColor(this.context, R.color.contrast_primary)).setContentTitle(title(state)).setCategory("progress").setOnlyAlertOnce(true).setOngoing(z10).setAutoCancel(!z10);
        Intrinsics.checkNotNullExpressionValue(autoCancel, "setAutoCancel(...)");
        if (strContent != null) {
            autoCancel.setContentText(strContent).setStyle(new NotificationCompat.BigTextStyle().bigText(strContent));
        }
        switch (WhenMappings.$EnumSwitchMapping$0[state.getStatus().ordinal()]) {
            case 1:
                autoCancel.setProgress(state.getTotal(), state.getCurrent(), false);
                Intrinsics.checkNotNull(autoCancel.addAction(action(SendQueueNotificationAction.CANCEL_ALL)));
                break;
            case 2:
            case 3:
                Intrinsics.checkNotNullExpressionValue(autoCancel.addAction(action(SendQueueNotificationAction.CANCEL_ALL)), "addAction(...)");
                break;
            case 4:
            case 5:
                autoCancel.addAction(action(SendQueueNotificationAction.DISCARD_FAILURES));
                Intrinsics.checkNotNull(autoCancel.addAction(action(SendQueueNotificationAction.RETRY)));
                break;
            case 6:
                Unit unit = Unit.INSTANCE;
                break;
            case 7:
                throw new IllegalStateException("Empty state cannot be rendered");
            default:
                throw new NoWhenBranchMatchedException();
        }
        Notification notificationBuild = autoCancel.build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        return notificationBuild;
    }
}
