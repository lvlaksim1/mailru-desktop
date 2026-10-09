package ru.mail.logic.sendmessage.queue.notification;

import android.content.Context;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mails.R;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'CANCEL_ALL' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0013B%\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0014"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction;", "", "intentAction", "", "titleRes", "", "iconRes", "<init>", "(Ljava/lang/String;ILjava/lang/String;II)V", "getIntentAction", "()Ljava/lang/String;", "getIconRes", "()I", "CANCEL_ALL", "DISCARD_FAILURES", "RETRY", "title", "context", "Landroid/content/Context;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SendQueueNotificationAction {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ SendQueueNotificationAction[] $VALUES;
    public static final SendQueueNotificationAction CANCEL_ALL;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE;
    public static final SendQueueNotificationAction DISCARD_FAILURES;
    public static final SendQueueNotificationAction RETRY;
    private final int iconRes;

    @NotNull
    private final String intentAction;
    private final int titleRes;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction$Companion;", "", "<init>", "()V", "fromIntentAction", "Lru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction;", "action", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSendQueueNotificationAction.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SendQueueNotificationAction.kt\nru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,38:1\n295#2,2:39\n*S KotlinDebug\n*F\n+ 1 SendQueueNotificationAction.kt\nru/mail/logic/sendmessage/queue/notification/SendQueueNotificationAction$Companion\n*L\n34#1:39,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final SendQueueNotificationAction fromIntentAction(@Nullable String action) {
            SendQueueNotificationAction next;
            Iterator<SendQueueNotificationAction> it = SendQueueNotificationAction.getEntries().iterator();
            while (it.hasNext()) {
                next = it.next();
                if (Intrinsics.areEqual(next.getIntentAction(), action)) {
                    return next;
                }
            }
            next = null;
            return next;
        }

        private Companion() {
        }
    }

    private static final /* synthetic */ SendQueueNotificationAction[] $values() {
        return new SendQueueNotificationAction[]{CANCEL_ALL, DISCARD_FAILURES, RETRY};
    }

    static {
        int i10 = R.string.notification_action_cancel;
        int i11 = R.drawable.ic_notification_cancel;
        CANCEL_ALL = new SendQueueNotificationAction("CANCEL_ALL", 0, "ru.mail.sendqueue.CANCEL_ALL", i10, i11);
        DISCARD_FAILURES = new SendQueueNotificationAction("DISCARD_FAILURES", 1, "ru.mail.sendqueue.DISCARD_FAILURES", R.string.send_queue_discard_failures, i11);
        RETRY = new SendQueueNotificationAction("RETRY", 2, "ru.mail.sendqueue.RETRY", R.string.no_internet_conection_btn, R.drawable.ic_notification_try_again);
        SendQueueNotificationAction[] sendQueueNotificationActionArr$values = $values();
        $VALUES = sendQueueNotificationActionArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(sendQueueNotificationActionArr$values);
        INSTANCE = new Companion(null);
    }

    private SendQueueNotificationAction(String str, @StringRes int i10, @DrawableRes String str2, int i11, int i12) {
        super(str, i10);
        this.intentAction = str2;
        this.titleRes = i11;
        this.iconRes = i12;
    }

    @NotNull
    public static EnumEntries<SendQueueNotificationAction> getEntries() {
        return $ENTRIES;
    }

    public static SendQueueNotificationAction valueOf(String str) {
        return (SendQueueNotificationAction) Enum.valueOf(SendQueueNotificationAction.class, str);
    }

    public static SendQueueNotificationAction[] values() {
        return (SendQueueNotificationAction[]) $VALUES.clone();
    }

    public final int getIconRes() {
        return this.iconRes;
    }

    @NotNull
    public final String getIntentAction() {
        return this.intentAction;
    }

    @NotNull
    public final String title(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = context.getString(this.titleRes);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }
}
