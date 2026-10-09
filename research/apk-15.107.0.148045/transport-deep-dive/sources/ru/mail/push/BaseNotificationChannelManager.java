package ru.mail.push;

import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationChannelGroupCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.logic.content.DataManager;
import ru.mail.util.push.MutableNotificationChannelsId;
import ru.mail.util.push.NotificationChannelManager;
import ru.mail.util.push.NotificationChannelsExtensionsKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ)\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\b\u0016H\u0016J@\u0010\u000f\u001a\u00020\u00102\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u00072\u0019\b\b\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\b\u0016H\u0082\bJ\u000e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00120\u001aH\u0004J\u001e\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0004J\u0010\u0010 \u001a\u00020\u001f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0004J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0012H\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lru/mail/push/BaseNotificationChannelManager;", "Lru/mail/util/push/NotificationChannelManager;", "notificationChannelsId", "Lru/mail/util/push/MutableNotificationChannelsId;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "dataManagerProvider", "Lkotlin/Function0;", "Lru/mail/logic/content/DataManager;", "<init>", "(Lru/mail/util/push/MutableNotificationChannelsId;Landroidx/core/app/NotificationManagerCompat;Lkotlin/jvm/functions/Function0;)V", "getNotificationChannelsId", "()Lru/mail/util/push/MutableNotificationChannelsId;", "getNotificationManager", "()Landroidx/core/app/NotificationManagerCompat;", "updateNotificationChannel", "", RemoteMessageConst.Notification.CHANNEL_ID, "", "buildChannel", "Lkotlin/Function1;", "Landroidx/core/app/NotificationChannelCompat$Builder;", "Lkotlin/ExtensionFunctionType;", "getActualChannelId", "updateActualChannelId", "getAccounts", "", "isValidAccountChannel", "", "accounts", "notificationChannel", "Landroidx/core/app/NotificationChannelCompat;", "getNotificationChannel", "getNotificationChannelGroup", "Landroidx/core/app/NotificationChannelGroupCompat;", "channelGroupId", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nBaseNotificationChannelManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 BaseNotificationChannelManager.kt\nru/mail/push/BaseNotificationChannelManager\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,113:1\n80#1,9:114\n80#1,9:123\n80#1,9:132\n80#1,9:141\n80#1,9:150\n80#1,9:159\n1563#2:168\n1634#2,3:169\n*S KotlinDebug\n*F\n+ 1 BaseNotificationChannelManager.kt\nru/mail/push/BaseNotificationChannelManager\n*L\n26#1:114,9\n34#1:123,9\n42#1:132,9\n50#1:141,9\n58#1:150,9\n66#1:159,9\n91#1:168\n91#1:169,3\n*E\n"})
public abstract class BaseNotificationChannelManager implements NotificationChannelManager {
    public static final int $stable = 8;

    @NotNull
    private final Function0<DataManager> dataManagerProvider;

    @NotNull
    private final MutableNotificationChannelsId notificationChannelsId;

    @NotNull
    private final NotificationManagerCompat notificationManager;

    /* JADX WARN: Multi-variable type inference failed */
    public BaseNotificationChannelManager(@NotNull MutableNotificationChannelsId notificationChannelsId, @NotNull NotificationManagerCompat notificationManager, @NotNull Function0<? extends DataManager> dataManagerProvider) {
        Intrinsics.checkNotNullParameter(notificationChannelsId, "notificationChannelsId");
        Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
        Intrinsics.checkNotNullParameter(dataManagerProvider, "dataManagerProvider");
        this.notificationChannelsId = notificationChannelsId;
        this.notificationManager = notificationManager;
        this.dataManagerProvider = dataManagerProvider;
    }

    @NotNull
    protected final List<String> getAccounts() {
        List<MailboxProfile> accounts = this.dataManagerProvider.invoke().getAccounts();
        Intrinsics.checkNotNullExpressionValue(accounts, "getAccounts(...)");
        List<MailboxProfile> list = accounts;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((MailboxProfile) it.next()).getLogin());
        }
        return arrayList;
    }

    @NotNull
    protected final NotificationChannelCompat getNotificationChannel(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        NotificationChannelCompat notificationChannelCompat = this.notificationManager.getNotificationChannelCompat(channelId);
        if (notificationChannelCompat != null) {
            return notificationChannelCompat;
        }
        throw new IllegalStateException(("Can'not find notification channel with id: " + channelId).toString());
    }

    @NotNull
    protected final NotificationChannelGroupCompat getNotificationChannelGroup(@NotNull String channelGroupId) {
        Intrinsics.checkNotNullParameter(channelGroupId, "channelGroupId");
        NotificationChannelGroupCompat notificationChannelGroupCompat = this.notificationManager.getNotificationChannelGroupCompat(channelGroupId);
        if (notificationChannelGroupCompat != null) {
            return notificationChannelGroupCompat;
        }
        throw new IllegalStateException(("Can'not find notification channel group with id: " + channelGroupId).toString());
    }

    @NotNull
    protected final MutableNotificationChannelsId getNotificationChannelsId() {
        return this.notificationChannelsId;
    }

    @NotNull
    protected final NotificationManagerCompat getNotificationManager() {
        return this.notificationManager;
    }

    protected final boolean isValidAccountChannel(@NotNull List<String> accounts, @NotNull NotificationChannelCompat notificationChannel) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(notificationChannel, "notificationChannel");
        return CollectionsKt.contains(accounts, notificationChannel.getName());
    }

    @Override // ru.mail.util.push.NotificationChannelManager
    public void updateNotificationChannel(@NotNull String channelId, @NotNull Function1<? super NotificationChannelCompat.Builder, ? extends NotificationChannelCompat.Builder> buildChannel) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        Intrinsics.checkNotNullParameter(buildChannel, "buildChannel");
        if (this.notificationChannelsId.isSendingChannelId(channelId)) {
            String sendingChannelId = this.notificationChannelsId.getSendingChannelId();
            NotificationChannelCompat notificationChannel = getNotificationChannel(sendingChannelId);
            this.notificationManager.deleteNotificationChannel(sendingChannelId);
            this.notificationChannelsId.updateSendingChannelId();
            NotificationChannelCompat notificationChannelCompatBuild = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel, this.notificationChannelsId.getSendingChannelId())).build();
            Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild, "build(...)");
            this.notificationManager.createNotificationChannel(notificationChannelCompatBuild);
            return;
        }
        if (this.notificationChannelsId.isInfoChannelId(channelId)) {
            String infoChannelId = this.notificationChannelsId.getInfoChannelId();
            NotificationChannelCompat notificationChannel2 = getNotificationChannel(infoChannelId);
            this.notificationManager.deleteNotificationChannel(infoChannelId);
            this.notificationChannelsId.updateInfoChannelId();
            NotificationChannelCompat notificationChannelCompatBuild2 = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel2, this.notificationChannelsId.getInfoChannelId())).build();
            Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild2, "build(...)");
            this.notificationManager.createNotificationChannel(notificationChannelCompatBuild2);
            return;
        }
        if (this.notificationChannelsId.isCallerInfoChannelId(channelId)) {
            String callerInfoChannelId = this.notificationChannelsId.getCallerInfoChannelId();
            NotificationChannelCompat notificationChannel3 = getNotificationChannel(callerInfoChannelId);
            this.notificationManager.deleteNotificationChannel(callerInfoChannelId);
            this.notificationChannelsId.updateCallerInfoChannelId();
            NotificationChannelCompat notificationChannelCompatBuild3 = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel3, this.notificationChannelsId.getCallerInfoChannelId())).build();
            Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild3, "build(...)");
            this.notificationManager.createNotificationChannel(notificationChannelCompatBuild3);
            return;
        }
        if (this.notificationChannelsId.isCalendarNotificationChanelId(channelId)) {
            String calendarNotificationChanelId = this.notificationChannelsId.getCalendarNotificationChanelId(channelId);
            NotificationChannelCompat notificationChannel4 = getNotificationChannel(calendarNotificationChanelId);
            this.notificationManager.deleteNotificationChannel(calendarNotificationChanelId);
            this.notificationChannelsId.updateCalendarNotificationChanelId(channelId);
            NotificationChannelCompat notificationChannelCompatBuild4 = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel4, this.notificationChannelsId.getCalendarNotificationChanelId(channelId))).build();
            Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild4, "build(...)");
            this.notificationManager.createNotificationChannel(notificationChannelCompatBuild4);
            return;
        }
        if (this.notificationChannelsId.isNewMessageChannelId(channelId)) {
            String newMessageChannelId = this.notificationChannelsId.getNewMessageChannelId(channelId);
            NotificationChannelCompat notificationChannel5 = getNotificationChannel(newMessageChannelId);
            this.notificationManager.deleteNotificationChannel(newMessageChannelId);
            this.notificationChannelsId.updateNewMessageChannelId(channelId);
            NotificationChannelCompat notificationChannelCompatBuild5 = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel5, this.notificationChannelsId.getNewMessageChannelId(channelId))).build();
            Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild5, "build(...)");
            this.notificationManager.createNotificationChannel(notificationChannelCompatBuild5);
            return;
        }
        if (this.notificationChannelsId.isWalletChannelId(channelId)) {
            String walletNotificationChanelId = this.notificationChannelsId.getWalletNotificationChanelId(channelId);
            NotificationChannelCompat notificationChannel6 = getNotificationChannel(walletNotificationChanelId);
            this.notificationManager.deleteNotificationChannel(walletNotificationChanelId);
            this.notificationChannelsId.updateWalletNotificationChanelId(channelId);
            NotificationChannelCompat notificationChannelCompatBuild6 = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel6, this.notificationChannelsId.getWalletNotificationChanelId(channelId))).build();
            Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild6, "build(...)");
            this.notificationManager.createNotificationChannel(notificationChannelCompatBuild6);
        }
    }

    private final void updateNotificationChannel(Function0<String> getActualChannelId, Function0<Unit> updateActualChannelId, Function1<? super NotificationChannelCompat.Builder, ? extends NotificationChannelCompat.Builder> buildChannel) {
        String strInvoke = getActualChannelId.invoke();
        NotificationChannelCompat notificationChannel = getNotificationChannel(strInvoke);
        this.notificationManager.deleteNotificationChannel(strInvoke);
        updateActualChannelId.invoke();
        NotificationChannelCompat notificationChannelCompatBuild = buildChannel.invoke(NotificationChannelsExtensionsKt.copyWithNewId(notificationChannel, getActualChannelId.invoke())).build();
        Intrinsics.checkNotNullExpressionValue(notificationChannelCompatBuild, "build(...)");
        this.notificationManager.createNotificationChannel(notificationChannelCompatBuild);
    }
}
