package ru.mail.push;

import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationChannelGroupCompat;
import androidx.core.app.NotificationManagerCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.DataManager;
import ru.mail.util.push.MutableNotificationChannelsId;
import ru.mail.util.push.NotificationChannelGroupIds;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u001e\u0010\u0010\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0011j\u0002`\u0013H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/push/MailNotificationChannelManager;", "Lru/mail/push/BaseNotificationChannelManager;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "dataManagerProvider", "Lkotlin/Function0;", "Lru/mail/logic/content/DataManager;", "notificationChannelsId", "Lru/mail/util/push/MutableNotificationChannelsId;", "notificationChannelGroupIds", "Lru/mail/util/push/NotificationChannelGroupIds;", "<init>", "(Landroidx/core/app/NotificationManagerCompat;Lkotlin/jvm/functions/Function0;Lru/mail/util/push/MutableNotificationChannelsId;Lru/mail/util/push/NotificationChannelGroupIds;)V", "getIndependentNotificationChannels", "", "Landroidx/core/app/NotificationChannelCompat;", "getNotificationChannelsByGroup", "", "Landroidx/core/app/NotificationChannelGroupCompat;", "Lru/mail/push/NotificationChannelsByGroup;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailNotificationChannelManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailNotificationChannelManager.kt\nru/mail/push/MailNotificationChannelManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,36:1\n774#2:37\n865#2,2:38\n*S KotlinDebug\n*F\n+ 1 MailNotificationChannelManager.kt\nru/mail/push/MailNotificationChannelManager\n*L\n30#1:37\n30#1:38,2\n*E\n"})
public final class MailNotificationChannelManager extends BaseNotificationChannelManager {
    public static final int $stable = 8;

    @NotNull
    private final NotificationChannelGroupIds notificationChannelGroupIds;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MailNotificationChannelManager(@NotNull NotificationManagerCompat notificationManager, @NotNull Function0<? extends DataManager> dataManagerProvider, @NotNull MutableNotificationChannelsId notificationChannelsId, @NotNull NotificationChannelGroupIds notificationChannelGroupIds) {
        super(notificationChannelsId, notificationManager, dataManagerProvider);
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
        Intrinsics.checkNotNullParameter(dataManagerProvider, "dataManagerProvider");
        Intrinsics.checkNotNullParameter(notificationChannelsId, "notificationChannelsId");
        Intrinsics.checkNotNullParameter(notificationChannelGroupIds, "notificationChannelGroupIds");
        this.notificationChannelGroupIds = notificationChannelGroupIds;
    }

    @Override // ru.mail.util.push.NotificationChannelManager
    @NotNull
    public List<NotificationChannelCompat> getIndependentNotificationChannels() {
        return CollectionsKt.listOf(getNotificationChannel(getNotificationChannelsId().getSendingChannelId()));
    }

    @Override // ru.mail.util.push.NotificationChannelManager
    @NotNull
    public Map<NotificationChannelGroupCompat, List<NotificationChannelCompat>> getNotificationChannelsByGroup() {
        List<String> accounts = getAccounts();
        NotificationChannelGroupCompat notificationChannelGroup = getNotificationChannelGroup(this.notificationChannelGroupIds.getNewMessageGroupChannelId());
        List<NotificationChannelCompat> channels = notificationChannelGroup.getChannels();
        Intrinsics.checkNotNullExpressionValue(channels, "getChannels(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : channels) {
            NotificationChannelCompat notificationChannelCompat = (NotificationChannelCompat) obj;
            Intrinsics.checkNotNull(notificationChannelCompat);
            if (isValidAccountChannel(accounts, notificationChannelCompat)) {
                arrayList.add(obj);
            }
        }
        return MapsKt.mapOf(TuplesKt.to(notificationChannelGroup, arrayList));
    }
}
