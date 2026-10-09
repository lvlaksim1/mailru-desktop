package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.hms.push.constant.RemoteMessageConst;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.search.common.extension.KotlinExtKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010\u0010\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0016J\"\u0010\u0011\u001a\u00020\n2\u0017\u0010\u0012\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0013¢\u0006\u0002\b\u0014H\u0082\bJ\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0002J\u0011\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\nH\u0096\u0001J\u0011\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\nH\u0096\u0001J\u0011\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\nH\u0096\u0001J\u0011\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\nH\u0096\u0001J\u0011\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\nH\u0096\u0001J\u0011\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\nH\u0096\u0001J\u0013\u0010\u001f\u001a\u00020\u00162\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0096\u0001J\t\u0010 \u001a\u00020\u0016H\u0096\u0001J\t\u0010!\u001a\u00020\u0016H\u0096\u0001J\u0013\u0010\"\u001a\u00020\u00162\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0096\u0001J\t\u0010#\u001a\u00020\u0016H\u0096\u0001J\u0013\u0010$\u001a\u00020\u00162\b\u0010\r\u001a\u0004\u0018\u00010\nH\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/util/push/CustomNotificationChannelsId;", "Lru/mail/util/push/MutableNotificationChannelsId;", "notificationManagerCompat", "Landroidx/core/app/NotificationManagerCompat;", "mutableNotificationChannelsId", "defaultNotificationChannelsId", "Lru/mail/util/push/NotificationChannelsId;", "<init>", "(Landroidx/core/app/NotificationManagerCompat;Lru/mail/util/push/MutableNotificationChannelsId;Lru/mail/util/push/NotificationChannelsId;)V", "getSendingChannelId", "", "getInfoChannelId", "getNewMessageChannelId", "username", "getCallerInfoChannelId", "getCalendarNotificationChanelId", "getWalletNotificationChanelId", "getCustomOrDefault", "getter", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "invalidateOldNotificationChannel", "", RemoteMessageConst.Notification.CHANNEL_ID, "isCalendarNotificationChanelId", "", "isCallerInfoChannelId", "isInfoChannelId", "isNewMessageChannelId", "isSendingChannelId", "isWalletChannelId", "updateCalendarNotificationChanelId", "updateCallerInfoChannelId", "updateInfoChannelId", "updateNewMessageChannelId", "updateSendingChannelId", "updateWalletNotificationChanelId", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCustomNotificationChannelsId.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomNotificationChannelsId.kt\nru/mail/util/push/CustomNotificationChannelsId\n*L\n1#1,51:1\n37#1,7:52\n37#1,7:59\n37#1,7:66\n37#1,7:73\n37#1,7:80\n37#1,7:87\n*S KotlinDebug\n*F\n+ 1 CustomNotificationChannelsId.kt\nru/mail/util/push/CustomNotificationChannelsId\n*L\n13#1:52,7\n17#1:59,7\n21#1:66,7\n25#1:73,7\n29#1:80,7\n33#1:87,7\n*E\n"})
public final class CustomNotificationChannelsId implements MutableNotificationChannelsId {
    public static final int $stable = 8;

    @NotNull
    private final NotificationChannelsId defaultNotificationChannelsId;

    @NotNull
    private final MutableNotificationChannelsId mutableNotificationChannelsId;

    @NotNull
    private final NotificationManagerCompat notificationManagerCompat;

    public CustomNotificationChannelsId(@NotNull NotificationManagerCompat notificationManagerCompat, @NotNull MutableNotificationChannelsId mutableNotificationChannelsId, @NotNull NotificationChannelsId defaultNotificationChannelsId) {
        Intrinsics.checkNotNullParameter(notificationManagerCompat, "notificationManagerCompat");
        Intrinsics.checkNotNullParameter(mutableNotificationChannelsId, "mutableNotificationChannelsId");
        Intrinsics.checkNotNullParameter(defaultNotificationChannelsId, "defaultNotificationChannelsId");
        this.notificationManagerCompat = notificationManagerCompat;
        this.mutableNotificationChannelsId = mutableNotificationChannelsId;
        this.defaultNotificationChannelsId = defaultNotificationChannelsId;
    }

    private final String getCustomOrDefault(Function1<? super NotificationChannelsId, String> getter) {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(getter.invoke(this.mutableNotificationChannelsId));
        String strInvoke = getter.invoke(this.defaultNotificationChannelsId);
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, strInvoke)) {
            return strInvoke;
        }
        invalidateOldNotificationChannel(strInvoke);
        return strTakeIfNotEmpty;
    }

    private final void invalidateOldNotificationChannel(String channelId) {
        if (this.notificationManagerCompat.getNotificationChannelCompat(channelId) != null) {
            this.notificationManagerCompat.deleteNotificationChannel(channelId);
        }
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getCalendarNotificationChanelId(@Nullable String username) {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(this.mutableNotificationChannelsId.getCalendarNotificationChanelId(username));
        String calendarNotificationChanelId = this.defaultNotificationChannelsId.getCalendarNotificationChanelId(username);
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, calendarNotificationChanelId)) {
            return calendarNotificationChanelId;
        }
        invalidateOldNotificationChannel(calendarNotificationChanelId);
        return strTakeIfNotEmpty;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getCallerInfoChannelId() {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(this.mutableNotificationChannelsId.getCallerInfoChannelId());
        String callerInfoChannelId = this.defaultNotificationChannelsId.getCallerInfoChannelId();
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, callerInfoChannelId)) {
            return callerInfoChannelId;
        }
        invalidateOldNotificationChannel(callerInfoChannelId);
        return strTakeIfNotEmpty;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getInfoChannelId() {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(this.mutableNotificationChannelsId.getInfoChannelId());
        String infoChannelId = this.defaultNotificationChannelsId.getInfoChannelId();
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, infoChannelId)) {
            return infoChannelId;
        }
        invalidateOldNotificationChannel(infoChannelId);
        return strTakeIfNotEmpty;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getNewMessageChannelId(@Nullable String username) {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(this.mutableNotificationChannelsId.getNewMessageChannelId(username));
        String newMessageChannelId = this.defaultNotificationChannelsId.getNewMessageChannelId(username);
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, newMessageChannelId)) {
            return newMessageChannelId;
        }
        invalidateOldNotificationChannel(newMessageChannelId);
        return strTakeIfNotEmpty;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getSendingChannelId() {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(this.mutableNotificationChannelsId.getSendingChannelId());
        String sendingChannelId = this.defaultNotificationChannelsId.getSendingChannelId();
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, sendingChannelId)) {
            return sendingChannelId;
        }
        invalidateOldNotificationChannel(sendingChannelId);
        return strTakeIfNotEmpty;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getWalletNotificationChanelId(@Nullable String username) {
        String strTakeIfNotEmpty = KotlinExtKt.takeIfNotEmpty(this.mutableNotificationChannelsId.getWalletNotificationChanelId(username));
        String walletNotificationChanelId = this.defaultNotificationChannelsId.getWalletNotificationChanelId(username);
        if (strTakeIfNotEmpty == null || strTakeIfNotEmpty.length() == 0 || Intrinsics.areEqual(strTakeIfNotEmpty, walletNotificationChanelId)) {
            return walletNotificationChanelId;
        }
        invalidateOldNotificationChannel(walletNotificationChanelId);
        return strTakeIfNotEmpty;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isCalendarNotificationChanelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return this.mutableNotificationChannelsId.isCalendarNotificationChanelId(channelId);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isCallerInfoChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return this.mutableNotificationChannelsId.isCallerInfoChannelId(channelId);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isInfoChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return this.mutableNotificationChannelsId.isInfoChannelId(channelId);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isNewMessageChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return this.mutableNotificationChannelsId.isNewMessageChannelId(channelId);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isSendingChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return this.mutableNotificationChannelsId.isSendingChannelId(channelId);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isWalletChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return this.mutableNotificationChannelsId.isWalletChannelId(channelId);
    }

    @Override // ru.mail.util.push.MutableNotificationChannelsId
    public void updateCalendarNotificationChanelId(@Nullable String username) {
        this.mutableNotificationChannelsId.updateCalendarNotificationChanelId(username);
    }

    @Override // ru.mail.util.push.MutableNotificationChannelsId
    public void updateCallerInfoChannelId() {
        this.mutableNotificationChannelsId.updateCallerInfoChannelId();
    }

    @Override // ru.mail.util.push.MutableNotificationChannelsId
    public void updateInfoChannelId() {
        this.mutableNotificationChannelsId.updateInfoChannelId();
    }

    @Override // ru.mail.util.push.MutableNotificationChannelsId
    public void updateNewMessageChannelId(@Nullable String username) {
        this.mutableNotificationChannelsId.updateNewMessageChannelId(username);
    }

    @Override // ru.mail.util.push.MutableNotificationChannelsId
    public void updateSendingChannelId() {
        this.mutableNotificationChannelsId.updateSendingChannelId();
    }

    @Override // ru.mail.util.push.MutableNotificationChannelsId
    public void updateWalletNotificationChanelId(@Nullable String username) {
        this.mutableNotificationChannelsId.updateWalletNotificationChanelId(username);
    }
}
