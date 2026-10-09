package ru.mail.util.push;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.android_utils.PendingIntentCreator;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.config.ConfigurationRepository;
import ru.mail.locator.Locator;
import ru.mail.logic.content.MailItemTransactionCategory;
import ru.mail.logic.header.HeaderInfo;
import ru.mail.logic.header.HeaderInfoBuilder;
import ru.mail.logic.navigation.Navigator;
import ru.mail.logic.navigation.restoreauth.ReturnParams;
import ru.mail.logic.navigation.restoreauth.ServiceChooserParams;
import ru.mail.logic.share.NewMailParameters;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.service.MailServiceImpl;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;
import ru.mail.ui.RequestCode;
import ru.mail.ui.fragments.mailbox.newmail.WayToOpenNewEmail;
import ru.mail.ui.writemail.WriteActivity;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0007J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\rH\u0002J'\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u001eJG\u0010\u001f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00070\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0004\b$\u0010%JG\u0010&\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010 \u001a\u00020\u00072\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00070\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0000¢\u0006\u0004\b(\u0010%J(\u0010)\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\u0012J/\u0010,\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b-J\u0018\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u00102\u001a\u00020/2\u0006\u00103\u001a\u0002012\u0006\u00104\u001a\u000201H\u0007J\u0014\u00105\u001a\u000201*\u0002012\u0006\u00106\u001a\u00020+H\u0002J\u0014\u00107\u001a\u000201*\u0002012\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\"\u00108\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u00109\u001a\u00020:H\u0007JP\u0010;\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010<\u001a\u00020\u00072\b\u0010=\u001a\u0004\u0018\u00010\u00072\u0006\u0010>\u001a\u00020\u00072\b\u0010?\u001a\u0004\u0018\u00010\u00072\b\u0010@\u001a\u0004\u0018\u00010\u00072\u000e\u0010A\u001a\n\u0012\u0004\u0012\u00020C\u0018\u00010BH\u0007J:\u0010D\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010E\u001a\u00020\u00072\u0006\u00109\u001a\u00020\u00072\u0006\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u00072\u0006\u0010H\u001a\u00020\u0007H\u0007J2\u0010I\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010E\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\u00072\u0006\u0010H\u001a\u00020\u00072\u0006\u0010J\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006K"}, d2 = {"Lru/mail/util/push/NotificationIntentFactory;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "EXTRA_REPLY_ALL", "", "EXTRA_MAIL_HEADER_INFO", "EXTRA_NEW_MAIL_PARAMS", "forMessageRemoval", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "profileId", "messageIds", "", "notificationMeta", "Lru/mail/util/push/NotificationMeta;", "(Landroid/content/Context;Ljava/lang/String;[Ljava/lang/String;Lru/mail/util/push/NotificationMeta;)Landroid/app/PendingIntent;", "forRestoreIgnored", "forRestoreAuthFlow", "returnUserParams", "Lru/mail/logic/navigation/restoreauth/ReturnParams;", "smartReplyAction", "Lru/mail/mailapp/DTOConfiguration$Config$NotificationSmartReplies$ActionType;", "forSmartReply", "push", "Lru/mail/util/push/NewMailPush;", "meta", "forSmartReply$mails_release", "forDeleteOrArchiveAction", "action", "messagesIds", "notificationId", "", "forDeleteOrArchiveAction$mails_release", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Lru/mail/util/push/NotificationMeta;I)Landroid/app/PendingIntent;", "forMessageMarking", "mailIds", "forMessageMarking$mails_release", "forReplyWithEdit", "isSmartReply", "", "forReplyNoEdit", "forReplyNoEdit$mails_release", "putDataReplyWithEdit", "", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "copyDataForReply", "from", "to", "putIsSmartReply", "isFromNotification", "putMeta", "forSummaryRemoval", "type", "Lru/mail/util/push/PushMessageType;", "forPortalPushRemoval", "uri", "path", "appId", "pushCampaign", "email", "buttons", "", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "forCalendarPushRemoval", "eventUid", "subtype", "mlType", "mlSubtype", "forWalletPushRemoval", "mlReminder", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationIntentFactory {

    @NotNull
    public static final String EXTRA_MAIL_HEADER_INFO = "extra_mail_header_info";

    @NotNull
    public static final String EXTRA_NEW_MAIL_PARAMS = "extra_new_mail_params";

    @NotNull
    public static final String EXTRA_REPLY_ALL = "reply_all";

    @NotNull
    public static final NotificationIntentFactory INSTANCE = new NotificationIntentFactory();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("NotificationIntentFactory");
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DTOConfiguration.Config.NotificationSmartReplies.ActionType.values().length];
            try {
                iArr[DTOConfiguration.Config.NotificationSmartReplies.ActionType.EDIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[DTOConfiguration.Config.NotificationSmartReplies.ActionType.SEND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private NotificationIntentFactory() {
    }

    @JvmStatic
    public static final void copyDataForReply(@NotNull Intent from, @NotNull Intent to) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        to.putExtra(NotificationUpdater.EXTRA_FROM_PUSH, from.getBooleanExtra(NotificationUpdater.EXTRA_FROM_PUSH, true)).putExtra(EXTRA_REPLY_ALL, from.getBooleanExtra(EXTRA_REPLY_ALL, true)).putExtra(EXTRA_MAIL_HEADER_INFO, from.getParcelableExtra(EXTRA_MAIL_HEADER_INFO)).putExtra(EXTRA_NEW_MAIL_PARAMS, from.getSerializableExtra(EXTRA_NEW_MAIL_PARAMS));
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forCalendarPushRemoval(@NotNull Context context, @NotNull String eventUid, @NotNull String type, @NotNull String subtype, @NotNull String mlType, @NotNull String mlSubtype) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eventUid, "eventUid");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(subtype, "subtype");
        Intrinsics.checkNotNullParameter(mlType, "mlType");
        Intrinsics.checkNotNullParameter(mlSubtype, "mlSubtype");
        LOG.i("Create calendar push removal intent");
        Intent intentPutExtra = ((Navigator) Locator.INSTANCE.from(context).locate(Navigator.class)).createInternalIntent(IntentActionsProvider.actionClearCalendarNotification).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_UID, eventUid).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_TYPE, type).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_SUBTYPE, subtype).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_TYPE, mlType).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_SUBTYPE, mlSubtype);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return PendingIntentCreator.getBroadcast(context, eventUid.hashCode(), intentPutExtra, PendingIntentUtils.INSTANCE.getPendingIntentFlags(false) | 1073741824);
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forMessageRemoval(@NotNull Context context, @NotNull String profileId, @NotNull String[] messageIds, @NotNull NotificationMeta notificationMeta) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(messageIds, "messageIds");
        Intrinsics.checkNotNullParameter(notificationMeta, "notificationMeta");
        LOG.i("Create message removal intent");
        Navigator navigator = (Navigator) Locator.INSTANCE.from(context).locate(Navigator.class);
        NotificationIntentFactory notificationIntentFactory = INSTANCE;
        Intent intentPutExtra = navigator.createInternalIntent(IntentActionsProvider.actionClearNotification).putExtra(NotificationUpdater.EXTRA_ACCOUNT_ID, profileId).putExtra("message_id", messageIds);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return PendingIntentCreator.getBroadcast(context, profileId.hashCode() + (messageIds.hashCode() * 31), notificationIntentFactory.putMeta(intentPutExtra, notificationMeta), PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null));
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forPortalPushRemoval(@NotNull Context context, @NotNull String uri, @Nullable String path, @NotNull String appId, @Nullable String pushCampaign, @Nullable String email, @Nullable List<PortalPushButton> buttons) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intrinsics.checkNotNullParameter(appId, "appId");
        LOG.i("Create portal push removal intent");
        Intent intentPutParcelableArrayListExtra = new Intent(IntentActionsProvider.actionClearPortalNotification).setPackage(context.getPackageName()).putExtra("push_uri", uri).putExtra("portal_push_app_id", appId).putExtra("portal_push_path", path).putExtra("portal_push_campaign", pushCampaign).putExtra("portal_push_email", email).putParcelableArrayListExtra("portal_push_buttons", (ArrayList) buttons);
        Intrinsics.checkNotNullExpressionValue(intentPutParcelableArrayListExtra, "putParcelableArrayListExtra(...)");
        return PendingIntentCreator.getBroadcast(context, (uri + email).hashCode(), intentPutParcelableArrayListExtra, PendingIntentUtils.INSTANCE.getPendingIntentFlags(false) | 1073741824);
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forRestoreAuthFlow(@NotNull Context context, @NotNull ReturnParams returnUserParams) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(returnUserParams, "returnUserParams");
        if (ConfigurationRepository.from(context).getConfiguration().getRestoreAuthFlowConfig().isForceServiceChooser()) {
            returnUserParams = new ServiceChooserParams(returnUserParams.getHasActiveAccounts());
        }
        return PendingIntentCreator.getActivity$default(context, RequestCode.RESTORE_AUTH_NOTIFICATION.id(), returnUserParams.createIntentFromNotification(context), PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null), false, 16, null);
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forRestoreIgnored(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intentCreateInternalIntent = ((Navigator) Locator.INSTANCE.from(context).locate(Navigator.class)).createInternalIntent(IntentActionsProvider.actionRemoveRestoreNotification);
        int iId = RequestCode.CANCEL_RESTORE_AUTH_NOTIFICATION.id();
        Intrinsics.checkNotNull(intentCreateInternalIntent);
        return PendingIntentCreator.getBroadcast(context, iId, intentCreateInternalIntent, PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null));
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forSummaryRemoval(@NotNull Context context, @NotNull String profileId, @NotNull PushMessageType type) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(type, "type");
        LOG.i("Create summary removal intent");
        NotificationIntentFactory notificationIntentFactory = INSTANCE;
        Intent intentPutExtra = new Intent(IntentActionsProvider.actionClearNotification).setPackage(context.getPackageName()).putExtra(NotificationUpdater.EXTRA_ACCOUNT_ID, profileId);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return PendingIntentCreator.getBroadcast(context, profileId.hashCode(), notificationIntentFactory.putMeta(intentPutExtra, new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, type, false, false, false, false, 32, null)), PendingIntentUtils.INSTANCE.getPendingIntentFlags(false) | 1073741824);
    }

    @JvmStatic
    @Nullable
    public static final PendingIntent forWalletPushRemoval(@NotNull Context context, @NotNull String eventUid, @NotNull String mlType, @NotNull String mlSubtype, @NotNull String mlReminder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(eventUid, "eventUid");
        Intrinsics.checkNotNullParameter(mlType, "mlType");
        Intrinsics.checkNotNullParameter(mlSubtype, "mlSubtype");
        Intrinsics.checkNotNullParameter(mlReminder, "mlReminder");
        LOG.d("Create wallet push removal intent");
        Intent intentPutExtra = ((Navigator) Locator.INSTANCE.from(context).locate(Navigator.class)).createInternalIntent(IntentActionsProvider.actionClearWalletNotification).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_UID, eventUid).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_TYPE, mlType).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_SUBTYPE, mlSubtype).putExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_REMINDER, mlReminder);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return PendingIntentCreator.getBroadcast(context, eventUid.hashCode(), intentPutExtra, PendingIntentUtils.INSTANCE.getPendingIntentFlags(false) | 1073741824);
    }

    private final void putDataReplyWithEdit(Intent intent, NewMailPush push) {
        NewMailParameters.Builder headerInfo = new NewMailParameters.Builder().setHeaderInfo(HeaderInfoBuilder.createFromPush(push));
        Intent intentPutExtra = intent.putExtra(NotificationUpdater.EXTRA_FROM_PUSH, true).putExtra(EXTRA_REPLY_ALL, true);
        HeaderInfo headerInfoCreateFromPush = HeaderInfoBuilder.createFromPush(push);
        Intrinsics.checkNotNull(headerInfoCreateFromPush, "null cannot be cast to non-null type android.os.Parcelable");
        Intent intentPutExtra2 = intentPutExtra.putExtra(EXTRA_MAIL_HEADER_INFO, (Parcelable) headerInfoCreateFromPush);
        NewMailParameters newMailParametersBuild = headerInfo.build();
        Intrinsics.checkNotNull(newMailParametersBuild, "null cannot be cast to non-null type java.io.Serializable");
        intentPutExtra2.putExtra(EXTRA_NEW_MAIL_PARAMS, (Serializable) newMailParametersBuild);
    }

    private final Intent putIsSmartReply(Intent intent, boolean z10) {
        Intent intentPutExtra = intent.putExtra(MailServiceImpl.EXTRA_IS_SMART_REPLY_FROM_NOTIFICATION, z10);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return intentPutExtra;
    }

    private final Intent putMeta(Intent intent, NotificationMeta notificationMeta) {
        Intent intentPutExtra = intent.putExtra(MailServiceImpl.EXTRA_NOTIFICATION_META, notificationMeta);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        return intentPutExtra;
    }

    private final DTOConfiguration.Config.NotificationSmartReplies.ActionType smartReplyAction(Context context) {
        return ConfigurationRepository.from(context).getConfiguration().getNotificationSmartRepliesSettings().getActionType();
    }

    @Nullable
    public final PendingIntent forDeleteOrArchiveAction$mails_release(@NotNull Context context, @NotNull String action, @NotNull String profileId, @NotNull String[] messagesIds, @NotNull NotificationMeta notificationMeta, int notificationId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(messagesIds, "messagesIds");
        Intrinsics.checkNotNullParameter(notificationMeta, "notificationMeta");
        Intent intentPutExtra = new Intent(context.getApplicationContext(), (Class<?>) MailServiceImpl.class).addCategory("android.intent.category.DEFAULT").setAction(action).putExtra("account", profileId).putExtra(MailServiceImpl.EXTRA_MAILS, messagesIds).putExtra(MailServiceImpl.EXTRA_NOTIFICATION_ID, notificationId);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        Intent intent = putMeta(intentPutExtra, notificationMeta).setPackage(context.getPackageName());
        Intrinsics.checkNotNullExpressionValue(intent, "setPackage(...)");
        return PendingIntentCreator.getService(context.getApplicationContext(), messagesIds[messagesIds.length - 1].hashCode(), intent, PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null));
    }

    @Nullable
    public final PendingIntent forMessageMarking$mails_release(@NotNull Context context, @NotNull String profileId, @NotNull String action, @NotNull String[] mailIds, @NotNull NotificationMeta notificationMeta, int notificationId) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(action, "action");
        Intrinsics.checkNotNullParameter(mailIds, "mailIds");
        Intrinsics.checkNotNullParameter(notificationMeta, "notificationMeta");
        Intent intentPutExtra = new Intent(action).putExtra("account", profileId).putExtra(MailServiceImpl.EXTRA_MAILS, mailIds).putExtra(MailServiceImpl.EXTRA_NOTIFICATION_ID, notificationId);
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        Intent intent = putMeta(intentPutExtra, notificationMeta).setPackage(context.getPackageName());
        Intrinsics.checkNotNullExpressionValue(intent, "setPackage(...)");
        return PendingIntentCreator.getService(context, mailIds[mailIds.length - 1].hashCode(), intent, PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null));
    }

    @Nullable
    public final PendingIntent forReplyNoEdit$mails_release(@NotNull Context context, @NotNull NewMailPush push, boolean isSmartReply, @NotNull NotificationMeta notificationMeta) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(push, "push");
        Intrinsics.checkNotNullParameter(notificationMeta, "notificationMeta");
        Intent intentPutExtra = new Intent(MailServiceImpl.getActionReplyMail()).setPackage(context.getPackageName()).putExtra("account", push.getProfileId()).putExtra(MailServiceImpl.EXTRA_MAILS, new String[]{push.getMessageId()});
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "putExtra(...)");
        Intent intentPutMeta = putMeta(putIsSmartReply(intentPutExtra, isSmartReply), notificationMeta);
        putDataReplyWithEdit(intentPutMeta, push);
        return PendingIntentCreator.getService(context, push.getMessageId().hashCode(), intentPutMeta, PendingIntentUtils.INSTANCE.getPendingIntentFlagsForSmartReplies(isSmartReply) | 1073741824);
    }

    @Nullable
    public final PendingIntent forReplyWithEdit(@NotNull Context context, @NotNull NewMailPush push, boolean isSmartReply, @NotNull NotificationMeta notificationMeta) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(push, "push");
        Intrinsics.checkNotNullParameter(notificationMeta, "notificationMeta");
        Intent intentAddCategory = WriteActivity.makeIntent(context, IntentActionsProvider.actionReply).addCategory("android.intent.category.DEFAULT");
        Intrinsics.checkNotNullExpressionValue(intentAddCategory, "addCategory(...)");
        Intent intentPutMeta = putMeta(putIsSmartReply(intentAddCategory, isSmartReply), notificationMeta);
        putDataReplyWithEdit(intentPutMeta, push);
        if (isSmartReply) {
            WriteActivity.addOpenedFromExtra(intentPutMeta, WayToOpenNewEmail.SMART_REPLY_FROM_PUSH);
        }
        return PendingIntentCreator.getActivity$default(context, push.getMessageId().hashCode(), intentPutMeta, PendingIntentUtils.INSTANCE.getPendingIntentFlagsForSmartReplies(isSmartReply) | 1073741824, false, 16, null);
    }

    @Nullable
    public final PendingIntent forSmartReply$mails_release(@NotNull Context context, @NotNull NewMailPush push, @NotNull NotificationMeta meta) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(push, "push");
        Intrinsics.checkNotNullParameter(meta, "meta");
        int i10 = WhenMappings.$EnumSwitchMapping$0[smartReplyAction(context).ordinal()];
        if (i10 == 1) {
            return forReplyWithEdit(context, push, true, meta);
        }
        if (i10 == 2) {
            return forReplyNoEdit$mails_release(context, push, true, meta);
        }
        throw new NoWhenBranchMatchedException();
    }
}
