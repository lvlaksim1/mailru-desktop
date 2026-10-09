package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.SdkUtils;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.cmd.database.AsyncDbHandler;
import ru.mail.data.cmd.database.LoadMessageDbCmd;
import ru.mail.data.cmd.database.SelectMailContent;
import ru.mail.data.cmd.server.RequestInitiator;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.data.entities.MailboxProfile;
import ru.mail.locator.Locator;
import ru.mail.logic.cmd.getmailmessage.GetMailMessageCmd;
import ru.mail.logic.content.impl.BaseMailboxContext;
import ru.mail.logic.header.HeaderInfoBuilder;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.logic.navigation.pending.StartActivityPendingAction;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.AccountAndIDParams;
import ru.mail.ui.attachmentsgallery.AttachHolder;
import ru.mail.ui.attachmentsgallery.AttachmentGalleryActivity;
import ru.mail.ui.webview.AttachNavigatorImpl;
import ru.mail.util.push.calendar.Action;
import ru.mail.util.push.calendar.payload.Payload;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0003\n\u000b\fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lru/mail/util/push/PushCalendarActionsReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "CalendarAwaitCommand", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushCalendarActionsReceiver extends BroadcastReceiver {

    @NotNull
    public static final String EXTRA_ACTIONS = "calendar_actions";
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u001a\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0002J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0012H\u0002J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0019\u001a\u00020\u0012H\u0002J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/util/push/PushCalendarActionsReceiver$CalendarAwaitCommand;", "Lru/mail/util/push/BaseAwaitCommand;", "Lru/mail/util/push/PushCalendarActionsReceiver$Params;", "context", "Landroid/content/Context;", "intentExtras", "Landroid/os/Bundle;", "pendingResult", "Landroid/content/BroadcastReceiver$PendingResult;", "params", "<init>", "(Landroid/content/Context;Landroid/os/Bundle;Landroid/content/BroadcastReceiver$PendingResult;Lru/mail/util/push/PushCalendarActionsReceiver$Params;)V", "execute", "", "contextExecutor", "Lru/mail/logic/navigation/executor/AppContextExecutor;", "openMsg", "messageId", "", "openAttach", "payLoad", "Lru/mail/util/push/calendar/payload/Payload;", "executor", "findMsg", "Lru/mail/data/entities/MailMessage;", RemoteMessageConst.MSGID, "findMsgContent", "Lru/mail/data/entities/MailMessageContent;", "sendErrorLog", "errorMsg", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nPushCalendarActionsReceiver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushCalendarActionsReceiver.kt\nru/mail/util/push/PushCalendarActionsReceiver$CalendarAwaitCommand\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,253:1\n1#2:254\n*E\n"})
    private static final class CalendarAwaitCommand extends BaseAwaitCommand<Params> {

        @NotNull
        private final Context context;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[Action.values().length];
                try {
                    iArr[Action.MESSAGE.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[Action.ATTACH.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[Action.URL.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr[Action.UNKNOWN.ordinal()] = 4;
                } catch (NoSuchFieldError unused4) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public CalendarAwaitCommand(@NotNull Context context, @Nullable Bundle bundle, @NotNull BroadcastReceiver.PendingResult pendingResult, @NotNull Params params) {
            super(context, params.getNotificationId(), bundle, pendingResult, params);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(pendingResult, "pendingResult");
            Intrinsics.checkNotNullParameter(params, "params");
            this.context = context;
        }

        private final MailMessage findMsg(String msgId) {
            try {
                AsyncDbHandler.CommonResponse commonResponse = (AsyncDbHandler.CommonResponse) new LoadMessageDbCmd(this.context, new AccountAndIDParams(msgId, getParams().getAccount())).execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
                if (!commonResponse.isSuccess()) {
                    return null;
                }
                MailMessage mailMessage = (MailMessage) commonResponse.getItem();
                if (mailMessage != null) {
                    return mailMessage;
                }
                List list = commonResponse.getList();
                if (list != null) {
                    return (MailMessage) CollectionsKt.last(list);
                }
                return null;
            } catch (NoSuchElementException e10) {
                BaseAwaitCommand.INSTANCE.getLOG().e("Can't find message by id " + msgId + " for " + getParams().getAccount(), e10);
                return null;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final MailMessageContent findMsgContent(String msgId) {
            try {
                GetMailMessageCmd.Params params = new GetMailMessageCmd.Params(msgId, getParams().getAccount(), new SelectMailContent.ContentType[0]);
                Object orThrow = new GetMailMessageCmd(this.context, new BaseMailboxContext(new MailboxProfile(params.getAccount())), params, RequestInitiator.MANUAL).execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
                if (orThrow instanceof CommandStatus.OK) {
                    return (MailMessageContent) ((CommandStatus.OK) orThrow).getData();
                }
                return null;
            } catch (Exception e10) {
                BaseAwaitCommand.INSTANCE.getLOG().e("Can't find message by id " + msgId + " for " + getParams().getAccount(), e10);
                return null;
            }
        }

        private final void openAttach(Payload payLoad, AppContextExecutor executor) {
            Object next;
            String baseMetaData = payLoad.getBaseMetaData();
            String metaData = payLoad.getMetaData("message_id");
            if (metaData == null || baseMetaData == null) {
                sendErrorLog("Can't open attach as null detected messageId: " + metaData + " и attachId: " + baseMetaData);
                return;
            }
            MailMessageContent mailMessageContentFindMsgContent = findMsgContent(metaData);
            if (mailMessageContentFindMsgContent != null) {
                Collection<Attach> attachList = mailMessageContentFindMsgContent.getAttachList(Attach.Disposition.ATTACHMENT);
                Intrinsics.checkNotNull(attachList);
                Iterator<T> it = attachList.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((Attach) next).getPartId(), baseMetaData));
                Attach attach = (Attach) next;
                if (attach == null) {
                    sendErrorLog("Attach with ID = " + attach + " not found! Total attaches count: " + attachList.size());
                    return;
                }
                Intent intent = new Intent(this.context, (Class<?>) AttachmentGalleryActivity.class);
                intent.putExtra(AttachNavigatorImpl.EXTRA_CURRENT_ATTACHMENT, new AttachHolder(attach, null, true, null, 8, null));
                intent.putExtra("mail_id", mailMessageContentFindMsgContent.getId());
                intent.putExtra("ml_data", mailMessageContentFindMsgContent.getMlEventData());
                intent.putExtra("mail_account", getParams().getAccount());
                intent.putExtra("folder_id", mailMessageContentFindMsgContent.getFolderId());
                intent.putExtra("from", mailMessageContentFindMsgContent.getFrom());
                intent.putExtra("attachments_count", mailMessageContentFindMsgContent.getAttachCount());
                intent.setFlags(67174400);
                new StartActivityPendingAction(this.context, intent).perform(executor);
            }
        }

        private final void openMsg(String messageId, AppContextExecutor contextExecutor) {
            if (messageId == null) {
                sendErrorLog("Can't open message as messageId is null");
                return;
            }
            MailMessage mailMessageFindMsg = findMsg(messageId);
            if (mailMessageFindMsg != null) {
                new StartActivityPendingAction(this.context, HeaderInfoBuilder.createFromMessage(mailMessageFindMsg).getViewActivityIntent(this.context)).perform(contextExecutor);
            }
        }

        private final void sendErrorLog(String errorMsg) {
            MailAppDependencies.analytics(this.context).sendCalendarPushClickedError(errorMsg);
            BaseAwaitCommand.INSTANCE.getLOG().e(errorMsg);
        }

        @Override // ru.mail.util.push.BaseAwaitCommand
        protected void execute(@NotNull AppContextExecutor contextExecutor) {
            Intrinsics.checkNotNullParameter(contextExecutor, "contextExecutor");
            Payload payLoad = getParams().getPayLoad();
            String eventUrl = getParams().getEventUrl();
            if (payLoad == null) {
                if (eventUrl == null) {
                    sendErrorLog("Push has not any actions and eventUrl");
                    return;
                } else {
                    MailAppDependencies.analytics(this.context).sendCalendarPushClicked(getParams().getEventUid(), getParams().getType(), getParams().getSubtype(), getParams().getMlType(), getParams().getMlSubtype());
                    findPathFor(eventUrl).perform(contextExecutor);
                    return;
                }
            }
            Action action = payLoad.getAction();
            MailAppDependencies.analytics(this.context).sendCalendarPushActionClicked(action.name(), payLoad.getAllMetaData().toString(), getParams().getEventUid(), getParams().getType(), getParams().getSubtype(), getParams().getMlType(), getParams().getMlSubtype());
            int i10 = WhenMappings.$EnumSwitchMapping$0[action.ordinal()];
            if (i10 == 1) {
                openMsg(payLoad.getBaseMetaData(), contextExecutor);
                return;
            }
            if (i10 == 2) {
                openAttach(payLoad, contextExecutor);
                return;
            }
            if (i10 != 3) {
                if (i10 != 4) {
                    throw new NoWhenBranchMatchedException();
                }
                sendErrorLog("Unknown action was clicked");
            } else {
                String baseMetaData = payLoad.getBaseMetaData();
                if (baseMetaData != null) {
                    findPathFor(baseMetaData).perform(contextExecutor);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Jg\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020\bHÖ\u0001J\t\u0010*\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011¨\u0006+"}, d2 = {"Lru/mail/util/push/PushCalendarActionsReceiver$Params;", "", "account", "", "eventUrl", "payLoad", "Lru/mail/util/push/calendar/payload/Payload;", "notificationId", "", "eventUid", "type", "subtype", "mlType", "mlSubtype", "<init>", "(Ljava/lang/String;Ljava/lang/String;Lru/mail/util/push/calendar/payload/Payload;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccount", "()Ljava/lang/String;", "getEventUrl", "getPayLoad", "()Lru/mail/util/push/calendar/payload/Payload;", "getNotificationId", "()I", "getEventUid", "getType", "getSubtype", "getMlType", "getMlSubtype", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params {
        public static final int $stable = 8;

        @NotNull
        private final String account;

        @NotNull
        private final String eventUid;

        @Nullable
        private final String eventUrl;

        @NotNull
        private final String mlSubtype;

        @NotNull
        private final String mlType;
        private final int notificationId;

        @Nullable
        private final Payload payLoad;

        @NotNull
        private final String subtype;

        @NotNull
        private final String type;

        public Params(@NotNull String account, @Nullable String str, @Nullable Payload payload, int i10, @NotNull String eventUid, @NotNull String type, @NotNull String subtype, @NotNull String mlType, @NotNull String mlSubtype) {
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(eventUid, "eventUid");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(subtype, "subtype");
            Intrinsics.checkNotNullParameter(mlType, "mlType");
            Intrinsics.checkNotNullParameter(mlSubtype, "mlSubtype");
            this.account = account;
            this.eventUrl = str;
            this.payLoad = payload;
            this.notificationId = i10;
            this.eventUid = eventUid;
            this.type = type;
            this.subtype = subtype;
            this.mlType = mlType;
            this.mlSubtype = mlSubtype;
        }

        public static /* synthetic */ Params copy$default(Params params, String str, String str2, Payload payload, int i10, String str3, String str4, String str5, String str6, String str7, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                str = params.account;
            }
            if ((i11 & 2) != 0) {
                str2 = params.eventUrl;
            }
            if ((i11 & 4) != 0) {
                payload = params.payLoad;
            }
            if ((i11 & 8) != 0) {
                i10 = params.notificationId;
            }
            if ((i11 & 16) != 0) {
                str3 = params.eventUid;
            }
            if ((i11 & 32) != 0) {
                str4 = params.type;
            }
            if ((i11 & 64) != 0) {
                str5 = params.subtype;
            }
            if ((i11 & 128) != 0) {
                str6 = params.mlType;
            }
            if ((i11 & 256) != 0) {
                str7 = params.mlSubtype;
            }
            String str8 = str6;
            String str9 = str7;
            String str10 = str4;
            String str11 = str5;
            String str12 = str3;
            Payload payload2 = payload;
            return params.copy(str, str2, payload2, i10, str12, str10, str11, str8, str9);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getAccount() {
            return this.account;
        }

        @Nullable
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getEventUrl() {
            return this.eventUrl;
        }

        @Nullable
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Payload getPayLoad() {
            return this.payLoad;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getNotificationId() {
            return this.notificationId;
        }

        @NotNull
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getEventUid() {
            return this.eventUid;
        }

        @NotNull
        /* JADX INFO: renamed from: component6, reason: from getter */
        public final String getType() {
            return this.type;
        }

        @NotNull
        /* JADX INFO: renamed from: component7, reason: from getter */
        public final String getSubtype() {
            return this.subtype;
        }

        @NotNull
        /* JADX INFO: renamed from: component8, reason: from getter */
        public final String getMlType() {
            return this.mlType;
        }

        @NotNull
        /* JADX INFO: renamed from: component9, reason: from getter */
        public final String getMlSubtype() {
            return this.mlSubtype;
        }

        @NotNull
        public final Params copy(@NotNull String account, @Nullable String eventUrl, @Nullable Payload payLoad, int notificationId, @NotNull String eventUid, @NotNull String type, @NotNull String subtype, @NotNull String mlType, @NotNull String mlSubtype) {
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(eventUid, "eventUid");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(subtype, "subtype");
            Intrinsics.checkNotNullParameter(mlType, "mlType");
            Intrinsics.checkNotNullParameter(mlSubtype, "mlSubtype");
            return new Params(account, eventUrl, payLoad, notificationId, eventUid, type, subtype, mlType, mlSubtype);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.account, params.account) && Intrinsics.areEqual(this.eventUrl, params.eventUrl) && Intrinsics.areEqual(this.payLoad, params.payLoad) && this.notificationId == params.notificationId && Intrinsics.areEqual(this.eventUid, params.eventUid) && Intrinsics.areEqual(this.type, params.type) && Intrinsics.areEqual(this.subtype, params.subtype) && Intrinsics.areEqual(this.mlType, params.mlType) && Intrinsics.areEqual(this.mlSubtype, params.mlSubtype);
        }

        @NotNull
        public final String getAccount() {
            return this.account;
        }

        @NotNull
        public final String getEventUid() {
            return this.eventUid;
        }

        @Nullable
        public final String getEventUrl() {
            return this.eventUrl;
        }

        @NotNull
        public final String getMlSubtype() {
            return this.mlSubtype;
        }

        @NotNull
        public final String getMlType() {
            return this.mlType;
        }

        public final int getNotificationId() {
            return this.notificationId;
        }

        @Nullable
        public final Payload getPayLoad() {
            return this.payLoad;
        }

        @NotNull
        public final String getSubtype() {
            return this.subtype;
        }

        @NotNull
        public final String getType() {
            return this.type;
        }

        public int hashCode() {
            int iHashCode = this.account.hashCode() * 31;
            String str = this.eventUrl;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            Payload payload = this.payLoad;
            return ((((((((((((iHashCode2 + (payload != null ? payload.hashCode() : 0)) * 31) + Integer.hashCode(this.notificationId)) * 31) + this.eventUid.hashCode()) * 31) + this.type.hashCode()) * 31) + this.subtype.hashCode()) * 31) + this.mlType.hashCode()) * 31) + this.mlSubtype.hashCode();
        }

        @NotNull
        public String toString() {
            return "Params(account=" + this.account + ", eventUrl=" + this.eventUrl + ", payLoad=" + this.payLoad + ", notificationId=" + this.notificationId + ", eventUid=" + this.eventUid + ", type=" + this.type + ", subtype=" + this.subtype + ", mlType=" + this.mlType + ", mlSubtype=" + this.mlSubtype + ")";
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NotNull Context context, @NotNull Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (Intrinsics.areEqual(IntentActionsProvider.actionOpenCalendarPushReceiver, intent.getAction())) {
            Bundle bundleExtra = intent.getBundleExtra("intent_extras");
            Payload payload = null;
            String string = bundleExtra != null ? bundleExtra.getString("account_login") : null;
            String stringExtra = intent.getStringExtra("push_uri");
            if (SdkUtils.hasTiramisu()) {
                payload = (Payload) intent.getSerializableExtra(EXTRA_ACTIONS, Payload.class);
            } else {
                Serializable serializableExtra = intent.getSerializableExtra(EXTRA_ACTIONS);
                if (serializableExtra instanceof Payload) {
                    payload = (Payload) serializableExtra;
                }
            }
            Payload payload2 = payload;
            String stringExtra2 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_UID);
            String str = stringExtra2 == null ? "" : stringExtra2;
            String stringExtra3 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_TYPE);
            String str2 = stringExtra3 == null ? "" : stringExtra3;
            String stringExtra4 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_SUBTYPE);
            String str3 = stringExtra4 == null ? "" : stringExtra4;
            String stringExtra5 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_TYPE);
            String str4 = stringExtra5 == null ? "" : stringExtra5;
            String stringExtra6 = intent.getStringExtra(BaseAwaitCommand.EXTRA_CALENDAR_PUSH_ML_SUBTYPE);
            String str5 = stringExtra6 == null ? "" : stringExtra6;
            if (bundleExtra == null || string == null) {
                return;
            }
            BroadcastReceiver.PendingResult pendingResultGoAsync = goAsync();
            Intrinsics.checkNotNullExpressionValue(pendingResultGoAsync, "goAsync(...)");
            CalendarAwaitCommand calendarAwaitCommand = new CalendarAwaitCommand(context, bundleExtra, pendingResultGoAsync, new Params(string, stringExtra, payload2, intent.getIntExtra("auto_close_notificationId", -1), str, str2, str3, str4, str5));
            String stringExtra7 = intent.getStringExtra("EXTRA_PUSH_ANALYTIC_OPEN_URL");
            if (stringExtra7 != null) {
                AnalyticUrlRequest.execute(context, stringExtra7);
            }
            calendarAwaitCommand.execute((ExecutorSelector) Locator.INSTANCE.locate(context, RequestArbiter.class));
        }
    }
}
