package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0007\u0018\u0000 \u00182\u00020\u00012\u00020\u0002:\u0001\u0018B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0016J\u0010\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\u0013\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\u0014\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u0015\u001a\u00020\bH\u0016J\b\u0010\u0016\u001a\u00020\bH\u0016J\b\u0010\u0017\u001a\u00020\bH\u0016¨\u0006\u0019"}, d2 = {"Lru/mail/util/push/MailNotificationChannelsId;", "Lru/mail/util/push/NotificationChannelsId;", "Lru/mail/util/push/NotificationChannelGroupIds;", "<init>", "()V", "isSendingChannelId", "", RemoteMessageConst.Notification.CHANNEL_ID, "", "getSendingChannelId", "isInfoChannelId", "getInfoChannelId", "isNewMessageChannelId", "getNewMessageChannelId", "username", "isCallerInfoChannelId", "getCallerInfoChannelId", "isCalendarNotificationChanelId", "isWalletChannelId", "getCalendarNotificationChanelId", "getWalletNotificationChanelId", "getNewMessageGroupChannelId", "getCalendarGroupChannelId", "getWalletGroupChannelId", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailNotificationChannelsId implements NotificationChannelsId, NotificationChannelGroupIds {
    public static final int $stable = 0;

    @NotNull
    private static final String CALENDAR_CHANNEL_ID = "calendar_channel_id";

    @NotNull
    private static final String CALENDAR_GROUP_CHANNEL_ID = "calendar_group_channel_id";

    @NotNull
    private static final String CALLER_INFO_CHANNEL_ID = "caller_info_channel_id";

    @NotNull
    private static final Companion Companion = new Companion(null);

    @NotNull
    private static final String INFO_CHANNEL_ID = "information_channel_id";

    @NotNull
    private static final String NEW_MESSAGE_CHANNEL_ID = "new_message_channel_id";

    @NotNull
    private static final String NEW_MESSAGE_GROUP_CHANNEL_ID = "new_message_channel_group_id";

    @NotNull
    private static final String SENDING_CHANNEL_ID = "sending_channel_id";

    @NotNull
    private static final String WALLET_CHANNEL_ID = "wallet_channel_id";

    @NotNull
    private static final String WALLET_GROUP_CHANNEL_ID = "wallet_group_channel_id";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/util/push/MailNotificationChannelsId$Companion;", "", "<init>", "()V", "SENDING_CHANNEL_ID", "", "INFO_CHANNEL_ID", "NEW_MESSAGE_GROUP_CHANNEL_ID", "NEW_MESSAGE_CHANNEL_ID", "CALLER_INFO_CHANNEL_ID", "CALENDAR_GROUP_CHANNEL_ID", "WALLET_GROUP_CHANNEL_ID", "CALENDAR_CHANNEL_ID", "WALLET_CHANNEL_ID", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // ru.mail.util.push.NotificationChannelGroupIds
    @NotNull
    public String getCalendarGroupChannelId() {
        return CALENDAR_GROUP_CHANNEL_ID;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getCalendarNotificationChanelId(@Nullable String username) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.getDefault(), "%s_%s", Arrays.copyOf(new Object[]{username, CALENDAR_CHANNEL_ID}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getCallerInfoChannelId() {
        return CALLER_INFO_CHANNEL_ID;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getInfoChannelId() {
        return INFO_CHANNEL_ID;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getNewMessageChannelId(@Nullable String username) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.getDefault(), "%s_%s", Arrays.copyOf(new Object[]{username, NEW_MESSAGE_CHANNEL_ID}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @Override // ru.mail.util.push.NotificationChannelGroupIds
    @NotNull
    public String getNewMessageGroupChannelId() {
        return NEW_MESSAGE_GROUP_CHANNEL_ID;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getSendingChannelId() {
        return SENDING_CHANNEL_ID;
    }

    @Override // ru.mail.util.push.NotificationChannelGroupIds
    @NotNull
    public String getWalletGroupChannelId() {
        return WALLET_GROUP_CHANNEL_ID;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getWalletNotificationChanelId(@Nullable String username) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(Locale.getDefault(), "%s_%s", Arrays.copyOf(new Object[]{username, WALLET_CHANNEL_ID}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        return str;
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isCalendarNotificationChanelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return StringsKt.contains$default((CharSequence) channelId, (CharSequence) CALENDAR_CHANNEL_ID, false, 2, (Object) null);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isCallerInfoChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return StringsKt.contains$default((CharSequence) channelId, (CharSequence) CALLER_INFO_CHANNEL_ID, false, 2, (Object) null);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isInfoChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return StringsKt.contains$default((CharSequence) channelId, (CharSequence) INFO_CHANNEL_ID, false, 2, (Object) null);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isNewMessageChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return StringsKt.contains$default((CharSequence) channelId, (CharSequence) NEW_MESSAGE_CHANNEL_ID, false, 2, (Object) null);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isSendingChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return StringsKt.contains$default((CharSequence) channelId, (CharSequence) SENDING_CHANNEL_ID, false, 2, (Object) null);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isWalletChannelId(@NotNull String channelId) {
        Intrinsics.checkNotNullParameter(channelId, "channelId");
        return StringsKt.contains$default((CharSequence) channelId, (CharSequence) WALLET_CHANNEL_ID, false, 2, (Object) null);
    }
}
