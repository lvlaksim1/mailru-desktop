package ru.mail.util.push;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.content.Context;
import androidx.annotation.StringRes;
import androidx.core.app.NotificationManagerCompat;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.SystemUtils;
import ru.mail.auth.util.DomainUtils;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class NotificationChannelsImpl implements NotificationChannels {
    private static final String CHANNEL_CALENDAR_EN = "Calendar";
    private static final String CHANNEL_CALENDAR_RU = "Календарь";
    private static final String CHANNEL_CALLER_INFO_EN = "Caller Identification";
    private static final String CHANNEL_CALLER_INFO_RU = "Определитель номера";
    private static final String CHANNEL_INFO_EN = "Info";
    private static final String CHANNEL_INFO_RU = "Информация";
    private static final String CHANNEL_NEW_MESSAGE_EN = "New Message";
    private static final String CHANNEL_NEW_MESSAGE_RU = "Новое письмо";
    private static final String CHANNEL_SEND_MESSAGE_EN = "Send Message";
    private static final String CHANNEL_SEND_MESSAGE_RU = "Отправка письма";
    private static final String CHANNEL_WALLET_EN = "Highlights";
    private static final String CHANNEL_WALLET_RU = "Важное";
    private static final Log LOG = Log.getLog("NotificationChannels");
    private final Context mContext;
    private final NotificationChannelGroupIds mNotificationChannelGroupIds;
    private final NotificationChannelsConfig mNotificationChannelsConfig;
    private final NotificationChannelsId mNotificationChannelsId;
    private final NotificationChannelsInitializer mNotificationChannelsInitializer;
    private final NotificationManagerCompat mNotificationManager;

    public NotificationChannelsImpl(Context context, NotificationChannelsId notificationChannelsId, NotificationChannelGroupIds notificationChannelGroupIds, NotificationChannelsInitializer notificationChannelsInitializer, NotificationChannelsConfig notificationChannelsConfig) {
        this.mContext = context;
        this.mNotificationManager = NotificationManagerCompat.from(context);
        this.mNotificationChannelsId = notificationChannelsId;
        this.mNotificationChannelGroupIds = notificationChannelGroupIds;
        this.mNotificationChannelsInitializer = notificationChannelsInitializer;
        this.mNotificationChannelsConfig = notificationChannelsConfig;
    }

    private void clearSoundPreferences() {
        NotificationSound notificationSound = new NotificationSound();
        NotificationSound.Sound sound = notificationSound.getSound(this.mContext);
        NotificationSound.Sound sound2 = NotificationSound.Sound.EMPTY;
        if (sound != sound2) {
            notificationSound.applySound(this.mContext, sound2);
        }
    }

    private void createNewCalendarNotificationChannel(String str) {
        NotificationChannel notificationChannel = new NotificationChannel(getCalendarNotificationChanelId(str), str, 3);
        this.mNotificationChannelsInitializer.initCalendarNotificationChannel(notificationChannel);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    private void createNewMessageUserChannel(String str, String str2) {
        NotificationChannel notificationChannel = new NotificationChannel(str, str2, 3);
        this.mNotificationChannelsInitializer.initNewMessageUserChannel(notificationChannel);
        Log log = LOG;
        log.d("Create notification channel " + notificationChannel);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
        log.d("Notification channel created " + String.valueOf(this.mNotificationManager.getNotificationChannel(str)));
    }

    private void createNewWalletNotificationChannel(String str) {
        NotificationChannel notificationChannel = new NotificationChannel(getWalletNotificationChanelId(str), str, 3);
        this.mNotificationChannelsInitializer.initWalletNotificationChannel(notificationChannel);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    private String getNotEmptyChannelName(@StringRes int i10, String str, String str2) {
        String string = this.mContext.getString(i10);
        if (string == null || string.isEmpty()) {
            return SystemUtils.isRuLocale(this.mContext) ? str2 : str;
        }
        return string;
    }

    private void removeNotValidChannels() {
        for (NotificationChannel notificationChannel : this.mNotificationManager.getNotificationChannels()) {
            if (notificationChannel != null) {
                String group = notificationChannel.getGroup();
                CharSequence name = notificationChannel.getName();
                String id2 = notificationChannel.getId();
                if (group != null && name != null && id2 != null) {
                    boolean zEquals = group.equals(getNewMessageGroupChannelId());
                    boolean zEquals2 = group.equals(getCalendarGroupChannelId());
                    boolean zEquals3 = group.equals(getWalletGroupChannelId());
                    if (zEquals || zEquals2 || zEquals3) {
                        if (!DomainUtils.isDomainExist(name.toString())) {
                            this.mNotificationManager.deleteNotificationChannel(id2);
                        }
                    }
                }
            }
        }
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void deleteCalendarNotificationChannel(String str) {
        this.mNotificationManager.deleteNotificationChannel(getCalendarNotificationChanelId(str));
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void deleteNewMessageUserChannel(String str) {
        String newMessageChannelId = getNewMessageChannelId(str);
        String strValueOf = String.valueOf(this.mNotificationManager.getNotificationChannel(newMessageChannelId));
        LOG.d("Delete notification channel with id " + newMessageChannelId + ": " + strValueOf);
        this.mNotificationManager.deleteNotificationChannel(newMessageChannelId);
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void deleteWalletNotificationChannel(String str) {
        this.mNotificationManager.deleteNotificationChannel(getWalletNotificationChanelId(str));
    }

    @Override // ru.mail.util.push.NotificationChannelGroupIds
    public String getCalendarGroupChannelId() {
        return this.mNotificationChannelGroupIds.getCalendarGroupChannelId();
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public String getCalendarNotificationChanelId(String str) {
        return this.mNotificationChannelsId.getCalendarNotificationChanelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public String getCallerInfoChannelId() {
        return this.mNotificationChannelsId.getCallerInfoChannelId();
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public String getInfoChannelId() {
        return this.mNotificationChannelsId.getInfoChannelId();
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public String getNewMessageChannelId(String str) {
        return this.mNotificationChannelsId.getNewMessageChannelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelGroupIds
    public String getNewMessageGroupChannelId() {
        return this.mNotificationChannelGroupIds.getNewMessageGroupChannelId();
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public String getSendingChannelId() {
        return this.mNotificationChannelsId.getSendingChannelId();
    }

    @Override // ru.mail.util.push.NotificationChannelGroupIds
    public String getWalletGroupChannelId() {
        return this.mNotificationChannelGroupIds.getWalletGroupChannelId();
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    @NotNull
    public String getWalletNotificationChanelId(@Nullable String str) {
        return this.mNotificationChannelsId.getWalletNotificationChanelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initCalendarGroup() {
        this.mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(getCalendarGroupChannelId(), getNotEmptyChannelName(ru.mail.mails.R.string.calendar, "Calendar", CHANNEL_CALENDAR_RU)));
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initCalendarNotificationChannel(String str) {
        if (this.mNotificationManager.getNotificationChannel(getCalendarNotificationChanelId(str)) == null) {
            createNewCalendarNotificationChannel(str);
        }
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initCallerInfoChannel() {
        String callerInfoChannelId = getCallerInfoChannelId();
        NotificationChannel notificationChannel = this.mNotificationManager.getNotificationChannel(callerInfoChannelId);
        String notEmptyChannelName = getNotEmptyChannelName(ru.mail.mails.R.string.channel_caller_info, CHANNEL_CALLER_INFO_EN, CHANNEL_CALLER_INFO_RU);
        if (notificationChannel == null) {
            notificationChannel = new NotificationChannel(callerInfoChannelId, notEmptyChannelName, 4);
            this.mNotificationChannelsInitializer.initCallerInfoChannel(notificationChannel);
        } else {
            notificationChannel.setName(notEmptyChannelName);
        }
        LOG.d("Create notification channel " + notificationChannel);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initInfoChannel() {
        String infoChannelId = getInfoChannelId();
        NotificationChannel notificationChannel = this.mNotificationManager.getNotificationChannel(infoChannelId);
        String notEmptyChannelName = getNotEmptyChannelName(ru.mail.mails.R.string.channel_info, CHANNEL_INFO_EN, CHANNEL_INFO_RU);
        if (notificationChannel == null) {
            notificationChannel = new NotificationChannel(infoChannelId, notEmptyChannelName, 3);
            this.mNotificationChannelsInitializer.initInfoChannel(notificationChannel);
        } else {
            notificationChannel.setName(notEmptyChannelName);
        }
        LOG.d("Create notification channel " + notificationChannel);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initNewMessageGroup() {
        String newMessageGroupChannelId = getNewMessageGroupChannelId();
        NotificationChannelGroup notificationChannelGroup = new NotificationChannelGroup(newMessageGroupChannelId, getNotEmptyChannelName(ru.mail.mails.R.string.channel_new_message, CHANNEL_NEW_MESSAGE_EN, CHANNEL_NEW_MESSAGE_RU));
        Log log = LOG;
        log.d("Create notification channel group " + notificationChannelGroup);
        this.mNotificationManager.createNotificationChannelGroup(notificationChannelGroup);
        log.d("Notification channel group created " + String.valueOf(this.mNotificationManager.getNotificationChannelGroup(newMessageGroupChannelId)));
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initNewMessageUserChannel(String str) {
        String newMessageChannelId = getNewMessageChannelId(str);
        if (this.mNotificationManager.getNotificationChannel(newMessageChannelId) == null) {
            createNewMessageUserChannel(newMessageChannelId, str);
        }
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initSendingChannel() {
        String sendingChannelId = getSendingChannelId();
        NotificationChannel notificationChannel = this.mNotificationManager.getNotificationChannel(sendingChannelId);
        String notEmptyChannelName = getNotEmptyChannelName(ru.mail.mails.R.string.channel_send_message, CHANNEL_SEND_MESSAGE_EN, CHANNEL_SEND_MESSAGE_RU);
        if (notificationChannel == null) {
            notificationChannel = new NotificationChannel(sendingChannelId, notEmptyChannelName, 3);
            this.mNotificationChannelsInitializer.initSendingChannel(notificationChannel);
        } else {
            notificationChannel.setName(notEmptyChannelName);
        }
        LOG.d("Create notification channel " + notificationChannel);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initUserChannels(Collection<String> collection) {
        boolean zIsWalletPushesEnabled = this.mNotificationChannelsConfig.isWalletPushesEnabled();
        for (String str : collection) {
            initNewMessageUserChannel(str);
            initCalendarNotificationChannel(str);
            if (zIsWalletPushesEnabled) {
                initWalletNotificationChannel(str);
            } else {
                deleteWalletNotificationChannel(str);
            }
        }
        removeNotValidChannels();
        clearSoundPreferences();
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initWalletGroup() {
        this.mNotificationManager.createNotificationChannelGroup(new NotificationChannelGroup(getWalletGroupChannelId(), getNotEmptyChannelName(ru.mail.mails.R.string.wallet, CHANNEL_WALLET_EN, CHANNEL_WALLET_RU)));
    }

    @Override // ru.mail.util.push.NotificationChannels
    public void initWalletNotificationChannel(String str) {
        if (this.mNotificationManager.getNotificationChannel(getWalletNotificationChanelId(str)) == null) {
            createNewWalletNotificationChannel(str);
        }
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isCalendarNotificationChanelId(@NotNull String str) {
        return this.mNotificationChannelsId.isCalendarNotificationChanelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isCallerInfoChannelId(@NotNull String str) {
        return this.mNotificationChannelsId.isCallerInfoChannelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isInfoChannelId(@NotNull String str) {
        return this.mNotificationChannelsId.isInfoChannelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isNewMessageChannelId(@NotNull String str) {
        return this.mNotificationChannelsId.isNewMessageChannelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isSendingChannelId(@NotNull String str) {
        return this.mNotificationChannelsId.isSendingChannelId(str);
    }

    @Override // ru.mail.util.push.NotificationChannelsId
    public boolean isWalletChannelId(@NotNull String str) {
        return this.mNotificationChannelsId.isWalletChannelId(str);
    }
}
