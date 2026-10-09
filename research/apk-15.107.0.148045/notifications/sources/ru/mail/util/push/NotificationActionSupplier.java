package ru.mail.util.push;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.DrawableRes;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.PushConfigurationType;
import ru.mail.data.entities.SmartReply;
import ru.mail.logic.content.MailItemTransactionCategory;
import ru.mail.mailapp.service.MailServiceImpl;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 Y2\u00020\u0001:\u0002XYB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ*\u0010\u001f\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u001e\u0010\"\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ*\u0010#\u001a\u0004\u0018\u00010\u00132\u0006\u0010$\u001a\u00020!2\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020!0&2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0002J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020!0&2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010'\u001a\u00020(H\u0002J,\u0010*\u001a\u00020\u00192\u0006\u0010+\u001a\u00020\u00152\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00150&2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ1\u0010-\u001a\u00020\u00192\u0006\u0010.\u001a\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020/012\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u00102J\u0012\u00103\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020!0&H\u0002J\b\u00104\u001a\u000205H\u0002J\b\u00106\u001a\u000205H\u0002J\n\u00107\u001a\u0004\u0018\u000108H\u0003J \u00109\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020:2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010;\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010<\u001a\u00020=H\u0002J#\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020/2\f\u0010A\u001a\b\u0012\u0004\u0012\u00020/01H\u0002¢\u0006\u0002\u0010BJ\u0012\u0010C\u001a\u00020\u001e2\b\b\u0001\u0010D\u001a\u00020\u001eH\u0002J\u0010\u0010E\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0015H\u0002J \u0010F\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J*\u0010G\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\b\b\u0001\u0010H\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J*\u0010I\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\b\b\u0001\u0010H\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010J\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J0\u0010I\u001a\u00020\u00132\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00150&2\b\b\u0001\u0010H\u001a\u00020\u001e2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J*\u0010M\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u00152\b\b\u0001\u0010H\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\u001eH\u0002JK\u0010N\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020/012\u0006\u0010H\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020/2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010RJC\u0010G\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020/012\u0006\u0010H\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010K\u001a\u00020L2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010SJK\u0010T\u001a\u00020\u00132\u0006\u0010.\u001a\u00020/2\u0006\u0010Q\u001a\u00020/2\u0006\u0010U\u001a\u00020\u001e2\u0006\u0010V\u001a\u00020\u001e2\f\u00100\u001a\b\u0012\u0004\u0012\u00020/012\u0006\u0010K\u001a\u00020L2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0002\u0010WR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000e\u0010\u0010¨\u0006Z"}, d2 = {"Lru/mail/util/push/NotificationActionSupplier;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "getAnalytics", "()Lru/mail/analytics/MailAppAnalytics;", "analytics$delegate", "Lkotlin/Lazy;", "isPushActionIconAllowed", "", "()Z", "isPushActionIconAllowed$delegate", "getSmartReplyAction", "Landroidx/core/app/NotificationCompat$Action;", "pushData", "Lru/mail/util/push/Entity$NotificationData;", "type", "Lru/mail/util/push/PushMessageType;", "addButtonsActions", "", "push", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "notificationId", "", "mapToAction", "button", "Lru/mail/util/push/PushAction;", "addWearableActions", "mapToWearableAction", "actionName", "getSummaryButtons", "", "category", "Lru/mail/logic/content/MailItemTransactionCategory;", "getButtonsForWear", "addSummarySingleMessageThreadButton", "latestPush", "pushList", "addSummaryMessagesButtons", "profileId", "", "mailIds", "", "(Ljava/lang/String;[Ljava/lang/String;Landroidx/core/app/NotificationCompat$Builder;I)V", "canMarkAllAsRead", "getWearableExtender", "Landroidx/core/app/NotificationCompat$WearableExtender;", "getWearableExtenderWithBackground", "getWearableBg", "Landroid/graphics/Bitmap;", "getReplyAction", "Lru/mail/util/push/NewMailPush;", "getReplyActionForWearable", "getWearableChoices", "Landroidx/core/app/RemoteInput;", "getReplyChoices", "Landroidx/core/app/RemoteInput$Builder;", "key", "replyChoices", "(Ljava/lang/String;[Ljava/lang/String;)Landroidx/core/app/RemoteInput$Builder;", "getIconOrNull", "resId", "hasArchiveAction", "getWearableDeleteAction", "getMarkReadAction", "drawableResId", "getDeleteAction", "getUnsubscribeAction", "meta", "Lru/mail/util/push/NotificationMeta;", "getArchiveAction", "getDeleteOrArchiveAction", "messagesIds", "srtResId", "action", "(Ljava/lang/String;[Ljava/lang/String;IILjava/lang/String;Lru/mail/util/push/NotificationMeta;I)Landroidx/core/app/NotificationCompat$Action;", "(Ljava/lang/String;[Ljava/lang/String;IILru/mail/util/push/NotificationMeta;I)Landroidx/core/app/NotificationCompat$Action;", "getMarkAction", "labelStringId", "drawableId", "(Ljava/lang/String;Ljava/lang/String;II[Ljava/lang/String;Lru/mail/util/push/NotificationMeta;I)Landroidx/core/app/NotificationCompat$Action;", "SingleMessageButtonsExtractor", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNotificationActionSupplier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationActionSupplier.kt\nru/mail/util/push/NotificationActionSupplier\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,594:1\n1563#2:595\n1634#2,3:596\n1617#2,9:602\n1869#2:611\n1870#2:613\n1626#2:614\n1869#2,2:615\n1617#2,9:617\n1869#2:626\n1870#2:628\n1626#2:629\n1869#2,2:630\n1563#2:632\n1634#2,3:633\n37#3,2:599\n37#3,2:636\n1#4:601\n1#4:612\n1#4:627\n*S KotlinDebug\n*F\n+ 1 NotificationActionSupplier.kt\nru/mail/util/push/NotificationActionSupplier\n*L\n38#1:595\n38#1:596,3\n66#1:602,9\n66#1:611\n66#1:613\n66#1:614\n74#1:615,2\n147#1:617,9\n147#1:626\n147#1:628\n147#1:629\n228#1:630,2\n457#1:632\n457#1:633,3\n38#1:599,2\n457#1:636,2\n66#1:612\n147#1:627\n*E\n"})
public final class NotificationActionSupplier {

    /* JADX INFO: renamed from: analytics$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy analytics;

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: isPushActionIconAllowed$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isPushActionIconAllowed;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("NotificationActionSupplier");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0012J<\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\t2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lru/mail/util/push/NotificationActionSupplier$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "getButtons", "", "Lru/mail/util/push/PushAction;", "context", "Landroid/content/Context;", "type", "Lru/mail/util/push/PushMessageType;", "category", "Lru/mail/logic/content/MailItemTransactionCategory;", "buttonsExtractor", "Lkotlin/Function1;", "Lru/mail/config/PushConfigurationType;", "pushTypes", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nNotificationActionSupplier.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationActionSupplier.kt\nru/mail/util/push/NotificationActionSupplier$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,594:1\n774#2:595\n865#2,2:596\n1#3:598\n*S KotlinDebug\n*F\n+ 1 NotificationActionSupplier.kt\nru/mail/util/push/NotificationActionSupplier$Companion\n*L\n573#1:595\n573#1:596,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final List<PushAction> getButtons(@NotNull Context context, @NotNull PushMessageType type, @NotNull MailItemTransactionCategory category, @NotNull Function1<? super PushConfigurationType, ? extends List<? extends PushAction>> buttonsExtractor) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(buttonsExtractor, "buttonsExtractor");
            List<PushConfigurationType> pushTypes = ConfigurationRepository.from(context).getConfiguration().getPushTypes();
            ArrayList arrayList = new ArrayList();
            for (Object obj : pushTypes) {
                if (((PushConfigurationType) obj).getType() == type) {
                    arrayList.add(obj);
                }
            }
            return getButtons(category, arrayList, buttonsExtractor);
        }

        @NotNull
        public final Log getLOG() {
            return NotificationActionSupplier.LOG;
        }

        private Companion() {
        }

        @NotNull
        public final List<PushAction> getButtons(@NotNull MailItemTransactionCategory category, @NotNull List<PushConfigurationType> pushTypes, @NotNull Function1<? super PushConfigurationType, ? extends List<? extends PushAction>> buttonsExtractor) {
            Object obj;
            Object next;
            List<PushAction> list;
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(pushTypes, "pushTypes");
            Intrinsics.checkNotNullParameter(buttonsExtractor, "buttonsExtractor");
            List<PushConfigurationType> list2 = pushTypes;
            Iterator<T> it = list2.iterator();
            do {
                obj = null;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!((PushConfigurationType) next).getCategories().contains(category));
            PushConfigurationType pushConfigurationType = (PushConfigurationType) next;
            if (pushConfigurationType != null) {
                return (List) buttonsExtractor.invoke(pushConfigurationType);
            }
            for (Object obj2 : list2) {
                if (((PushConfigurationType) obj2).getCategories().isEmpty()) {
                    obj = obj2;
                    break;
                }
            }
            PushConfigurationType pushConfigurationType2 = (PushConfigurationType) obj;
            return (pushConfigurationType2 == null || (list = (List) buttonsExtractor.invoke(pushConfigurationType2)) == null) ? CollectionsKt.emptyList() : list;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\b\u001a\u00020\u0002H\u0096\u0002¨\u0006\t"}, d2 = {"Lru/mail/util/push/NotificationActionSupplier$SingleMessageButtonsExtractor;", "Lkotlin/Function1;", "Lru/mail/config/PushConfigurationType;", "", "Lru/mail/util/push/PushAction;", "<init>", "()V", "invoke", "type", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class SingleMessageButtonsExtractor implements Function1<PushConfigurationType, List<? extends PushAction>> {
        @Override // kotlin.jvm.functions.Function1
        @NotNull
        public List<PushAction> invoke(@NotNull PushConfigurationType type) {
            Intrinsics.checkNotNullParameter(type, "type");
            return type.getButtons();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushAction.values().length];
            try {
                iArr[PushAction.REPLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushAction.MARK_READ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PushAction.MARK_FLAG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PushAction.MARK_SPAM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PushAction.DELETE_ARCHIVE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PushAction.UNSUBSCRIBE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PushAction.DELETE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public NotificationActionSupplier(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.analytics = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.m
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationActionSupplier.analytics_delegate$lambda$0(this.f101091a);
            }
        });
        this.isPushActionIconAllowed = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.n
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NotificationActionSupplier.isPushActionIconAllowed_delegate$lambda$0(this.f101097a));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence addButtonsActions$lambda$0$0(PushAction it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String configurationName = it.getConfigurationName();
        Intrinsics.checkNotNullExpressionValue(configurationName, "getConfigurationName(...)");
        return configurationName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final NotificationCompat.Action addSummaryMessagesButtons$lambda$0(NotificationActionSupplier notificationActionSupplier, String str, String[] strArr, int i10, int i11) {
        String actionMarkReadMail = MailServiceImpl.getActionMarkReadMail();
        Intrinsics.checkNotNullExpressionValue(actionMarkReadMail, "getActionMarkReadMail(...)");
        return notificationActionSupplier.getMarkAction(str, actionMarkReadMail, ru.mail.mails.R.string.notification_action_mark_all_read, i11, strArr, new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, PushMessageType.SUMMARY_MESSAGES, false, false, false, false, 60, null), i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence addSummarySingleMessageThreadButton$lambda$0(PushAction it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String configurationName = it.getConfigurationName();
        Intrinsics.checkNotNullExpressionValue(configurationName, "getConfigurationName(...)");
        return configurationName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence addWearableActions$lambda$0$0(PushAction it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String configurationName = it.getConfigurationName();
        Intrinsics.checkNotNullExpressionValue(configurationName, "getConfigurationName(...)");
        return configurationName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MailAppAnalytics analytics_delegate$lambda$0(NotificationActionSupplier notificationActionSupplier) {
        return MailAppDependencies.analytics(notificationActionSupplier.context);
    }

    private final boolean canMarkAllAsRead(List<? extends PushAction> list) {
        return list.contains(PushAction.MARK_ALL_AS_READ);
    }

    private final MailAppAnalytics getAnalytics() {
        return (MailAppAnalytics) this.analytics.getValue();
    }

    private final NotificationCompat.Action getArchiveAction(Entity.NotificationData push, @DrawableRes int drawableResId, PushMessageType type, int notificationId) {
        String profileId = push.getPush().getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
        String messageId = push.getPush().getMessageId();
        Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
        int i10 = ru.mail.mails.R.string.notification_action_archive;
        String actionArchiveMail = MailServiceImpl.getActionArchiveMail();
        Intrinsics.checkNotNullExpressionValue(actionArchiveMail, "getActionArchiveMail(...)");
        return getDeleteOrArchiveAction(profileId, new String[]{messageId}, drawableResId, i10, actionArchiveMail, push.asMeta(type), notificationId);
    }

    private final List<PushAction> getButtonsForWear(PushMessageType type, MailItemTransactionCategory category) {
        return INSTANCE.getButtons(this.context, type, category, new Function1() { // from class: ru.mail.util.push.l
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NotificationActionSupplier.getButtonsForWear$lambda$0((PushConfigurationType) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getButtonsForWear$lambda$0(PushConfigurationType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getButtonsForWear();
    }

    private final NotificationCompat.Action getDeleteAction(Entity.NotificationData push, @DrawableRes int drawableResId, PushMessageType type, int notificationId) {
        String profileId = push.getPush().getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
        String messageId = push.getPush().getMessageId();
        Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
        int i10 = ru.mail.mails.R.string.notification_action_delete;
        String actionRemoveMail = MailServiceImpl.getActionRemoveMail();
        Intrinsics.checkNotNullExpressionValue(actionRemoveMail, "getActionRemoveMail(...)");
        return getDeleteOrArchiveAction(profileId, new String[]{messageId}, drawableResId, i10, actionRemoveMail, push.asMeta(type), notificationId);
    }

    private final NotificationCompat.Action getDeleteOrArchiveAction(String profileId, String[] messagesIds, int drawableResId, int srtResId, String action, NotificationMeta meta, int notificationId) {
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(drawableResId, this.context.getString(srtResId), NotificationIntentFactory.INSTANCE.forDeleteOrArchiveAction$mails_release(this.context, action, profileId, messagesIds, meta, notificationId)).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }

    private final int getIconOrNull(@DrawableRes int resId) {
        if (isPushActionIconAllowed()) {
            return resId;
        }
        return 0;
    }

    private final NotificationCompat.Action getMarkAction(String profileId, String action, int labelStringId, int drawableId, String[] mailIds, NotificationMeta meta, int notificationId) {
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(drawableId, this.context.getString(labelStringId), NotificationIntentFactory.INSTANCE.forMessageMarking$mails_release(this.context, profileId, action, mailIds, meta, notificationId)).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }

    private final NotificationCompat.Action getMarkReadAction(Entity.NotificationData push, @DrawableRes int drawableResId, PushMessageType type, int notificationId) {
        String profileId = push.getPush().getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
        String messageId = push.getPush().getMessageId();
        Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
        return getMarkReadAction(profileId, new String[]{messageId}, drawableResId, ru.mail.mails.R.string.notification_action_mark_read_single, new NotificationMeta(push.getCategory(), type, push.hasSmartReplies(), push.getHasStageSmartReply(), false, false, 48, null), notificationId);
    }

    private final NotificationCompat.Action getReplyAction(NewMailPush push, MailItemTransactionCategory category, PushMessageType type) {
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(getIconOrNull(ru.mail.mails.R.drawable.ic_notification_reply), this.context.getString(ru.mail.mails.R.string.notification_action_reply), NotificationIntentFactory.INSTANCE.forReplyWithEdit(this.context, push, false, new NotificationMeta(category, type, false, false, false, false, 60, null))).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }

    private final NotificationCompat.Action getReplyActionForWearable(Entity.NotificationData push, PushMessageType type) {
        PendingIntent pendingIntentForReplyNoEdit$mails_release = NotificationIntentFactory.INSTANCE.forReplyNoEdit$mails_release(this.context, push.getPush(), false, new NotificationMeta(push.getCategory(), type, false, false, false, false, 60, null));
        String string = this.context.getString(ru.mail.mails.R.string.notification_action_reply);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(ru.mail.mails.R.drawable.wear_ic_action_reply, string, pendingIntentForReplyNoEdit$mails_release).addRemoteInput(getWearableChoices()).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }

    private final RemoteInput.Builder getReplyChoices(String key, String[] replyChoices) {
        String string = this.context.getString(ru.mail.mails.R.string.notification_action_reply);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        RemoteInput.Builder choices = new RemoteInput.Builder(key).setLabel(string).setChoices(replyChoices);
        Intrinsics.checkNotNullExpressionValue(choices, "setChoices(...)");
        return choices;
    }

    private final NotificationCompat.Action getSmartReplyAction(Entity.NotificationData pushData, PushMessageType type) {
        getAnalytics().sendSmartReplyPushAnalytics(String.valueOf(pushData.getHasStageSmartReply()), pushData.isDefaultSmartReplies());
        List<SmartReply> smartReplies = pushData.getSmartReplies();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(smartReplies, 10));
        Iterator<T> it = smartReplies.iterator();
        while (it.hasNext()) {
            arrayList.add(((SmartReply) it.next()).getSmartReplyContent());
        }
        RemoteInput remoteInputBuild = getReplyChoices(NotificationUpdater.EXTRA_REPLY_MSG, (String[]) arrayList.toArray(new String[0])).setAllowFreeFormInput(false).build();
        Intrinsics.checkNotNullExpressionValue(remoteInputBuild, "build(...)");
        return new NotificationCompat.Action.Builder(ru.mail.mails.R.drawable.wear_ic_action_reply, this.context.getString(ru.mail.mails.R.string.notification_action_reply), NotificationIntentFactory.INSTANCE.forSmartReply$mails_release(this.context, pushData.getPush(), pushData.asMeta(type))).addRemoteInput(remoteInputBuild).build();
    }

    private final List<PushAction> getSummaryButtons(PushMessageType type, MailItemTransactionCategory category) {
        return INSTANCE.getButtons(this.context, type, category, new Function1() { // from class: ru.mail.util.push.p
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NotificationActionSupplier.getSummaryButtons$lambda$0((PushConfigurationType) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getSummaryButtons$lambda$0(PushConfigurationType it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getButtons();
    }

    private final NotificationCompat.Action getUnsubscribeAction(Entity.NotificationData push, PushMessageType type, int notificationId) {
        NotificationIntentFactory notificationIntentFactory = NotificationIntentFactory.INSTANCE;
        Context context = this.context;
        String profileId = push.getPush().getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
        String actionUnsubscribe = MailServiceImpl.getActionUnsubscribe();
        Intrinsics.checkNotNullExpressionValue(actionUnsubscribe, "getActionUnsubscribe(...)");
        String messageId = push.getPush().getMessageId();
        Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(ru.mail.mails.R.drawable.ic_notification_unsubscribe, this.context.getString(ru.mail.mails.R.string.notification_action_mark_unsubscribe), notificationIntentFactory.forMessageMarking$mails_release(context, profileId, actionUnsubscribe, new String[]{messageId}, push.asMeta(type), notificationId)).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }

    @SuppressLint({"ResourceType"})
    private final Bitmap getWearableBg() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeStream(this.context.getResources().openRawResource(ru.mail.mails.R.drawable.wear_bg_notification), null, options);
    }

    private final RemoteInput getWearableChoices() {
        String[] stringArray = this.context.getResources().getStringArray(ru.mail.mails.R.array.wearable_reply_templates);
        Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
        RemoteInput remoteInputBuild = getReplyChoices(NotificationUpdater.EXTRA_REPLY_MSG, stringArray).build();
        Intrinsics.checkNotNullExpressionValue(remoteInputBuild, "build(...)");
        return remoteInputBuild;
    }

    private final NotificationCompat.Action getWearableDeleteAction(Entity.NotificationData push, PushMessageType type, int notificationId) {
        return getDeleteAction(push, ru.mail.mails.R.drawable.wear_ic_action_delete, type, notificationId);
    }

    private final NotificationCompat.WearableExtender getWearableExtender() {
        return new NotificationCompat.WearableExtender();
    }

    private final NotificationCompat.WearableExtender getWearableExtenderWithBackground() {
        NotificationCompat.WearableExtender wearableExtender = getWearableExtender();
        Bitmap wearableBg = getWearableBg();
        if (wearableBg != null) {
            wearableExtender.setBackground(wearableBg);
        }
        return wearableExtender;
    }

    private final boolean hasArchiveAction(Entity.NotificationData push) {
        return false;
    }

    private final boolean isPushActionIconAllowed() {
        return ((Boolean) this.isPushActionIconAllowed.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPushActionIconAllowed_delegate$lambda$0(NotificationActionSupplier notificationActionSupplier) {
        return ConfigurationRepository.from(notificationActionSupplier.context).getConfiguration().isPushActionIconAllowed();
    }

    private final NotificationCompat.Action mapToAction(PushAction button, Entity.NotificationData push, PushMessageType type, int notificationId) {
        switch (WhenMappings.$EnumSwitchMapping$0[button.ordinal()]) {
            case 1:
                return push.hasSmartReplies() ? getSmartReplyAction(push, type) : getReplyAction(push.getPush(), push.getCategory(), type);
            case 2:
                return getMarkReadAction(push, getIconOrNull(ru.mail.mails.R.drawable.ic_notification_read), type, notificationId);
            case 3:
                String profileId = push.getPush().getProfileId();
                Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
                String actionMarkFlagMail = MailServiceImpl.getActionMarkFlagMail();
                Intrinsics.checkNotNullExpressionValue(actionMarkFlagMail, "getActionMarkFlagMail(...)");
                int i10 = ru.mail.mails.R.string.notification_action_mark_flag;
                int i11 = ru.mail.mails.R.drawable.wear_ic_action_flag;
                String messageId = push.getPush().getMessageId();
                Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
                return getMarkAction(profileId, actionMarkFlagMail, i10, i11, new String[]{messageId}, push.asMeta(type), notificationId);
            case 4:
                String profileId2 = push.getPush().getProfileId();
                Intrinsics.checkNotNullExpressionValue(profileId2, "getProfileId(...)");
                String actionMarkSpamMail = MailServiceImpl.getActionMarkSpamMail();
                Intrinsics.checkNotNullExpressionValue(actionMarkSpamMail, "getActionMarkSpamMail(...)");
                int i12 = ru.mail.mails.R.string.notification_action_mark_spam;
                int i13 = ru.mail.mails.R.drawable.wear_ic_action_spam;
                String messageId2 = push.getPush().getMessageId();
                Intrinsics.checkNotNullExpressionValue(messageId2, "getMessageId(...)");
                return getMarkAction(profileId2, actionMarkSpamMail, i12, i13, new String[]{messageId2}, push.asMeta(type), notificationId);
            case 5:
                return hasArchiveAction(push) ? getArchiveAction(push, getIconOrNull(ru.mail.mails.R.drawable.ic_notification_archive), type, notificationId) : getDeleteAction(push, getIconOrNull(ru.mail.mails.R.drawable.ic_notification_delete), type, notificationId);
            case 6:
                return getUnsubscribeAction(push, type, notificationId);
            default:
                return null;
        }
    }

    private final NotificationCompat.Action mapToWearableAction(PushAction actionName, Entity.NotificationData push, PushMessageType type, int notificationId) {
        switch (WhenMappings.$EnumSwitchMapping$0[actionName.ordinal()]) {
            case 1:
                return push.hasSmartReplies() ? getSmartReplyAction(push, type) : getReplyActionForWearable(push, type);
            case 2:
                return getMarkReadAction(push, ru.mail.mails.R.drawable.ic_notification_read, type, notificationId);
            case 3:
                String profileId = push.getPush().getProfileId();
                Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
                String actionMarkFlagMail = MailServiceImpl.getActionMarkFlagMail();
                Intrinsics.checkNotNullExpressionValue(actionMarkFlagMail, "getActionMarkFlagMail(...)");
                int i10 = ru.mail.mails.R.string.notification_action_mark_flag;
                int i11 = ru.mail.mails.R.drawable.wear_ic_action_flag;
                String messageId = push.getPush().getMessageId();
                Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
                return getMarkAction(profileId, actionMarkFlagMail, i10, i11, new String[]{messageId}, push.asMeta(type), notificationId);
            case 4:
                String profileId2 = push.getPush().getProfileId();
                Intrinsics.checkNotNullExpressionValue(profileId2, "getProfileId(...)");
                String actionMarkSpamMail = MailServiceImpl.getActionMarkSpamMail();
                Intrinsics.checkNotNullExpressionValue(actionMarkSpamMail, "getActionMarkSpamMail(...)");
                int i12 = ru.mail.mails.R.string.notification_action_mark_spam;
                int i13 = ru.mail.mails.R.drawable.wear_ic_action_spam;
                String messageId2 = push.getPush().getMessageId();
                Intrinsics.checkNotNullExpressionValue(messageId2, "getMessageId(...)");
                return getMarkAction(profileId2, actionMarkSpamMail, i12, i13, new String[]{messageId2}, push.asMeta(type), notificationId);
            case 5:
                return hasArchiveAction(push) ? getArchiveAction(push, ru.mail.mails.R.drawable.wear_ic_action_archive, type, notificationId) : getWearableDeleteAction(push, type, notificationId);
            case 6:
                return getUnsubscribeAction(push, type, notificationId);
            default:
                return null;
        }
    }

    public final void addButtonsActions(@NotNull Entity.NotificationData push, @NotNull NotificationCompat.Builder notificationBuilder, int notificationId) {
        Intrinsics.checkNotNullParameter(push, "push");
        Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
        List<PushAction> buttons = INSTANCE.getButtons(this.context, PushMessageType.SINGLE_MESSAGE, push.getCategory(), new SingleMessageButtonsExtractor());
        List<PushAction> list = buttons;
        LOG.i("Add actions for single message: " + CollectionsKt.joinToString$default(list, AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, null, null, 0, null, new Function1() { // from class: ru.mail.util.push.o
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NotificationActionSupplier.addButtonsActions$lambda$0$0((PushAction) obj);
            }
        }, 30, null));
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            NotificationCompat.Action actionMapToAction = mapToAction((PushAction) it.next(), push, PushMessageType.SINGLE_MESSAGE, notificationId);
            if (actionMapToAction != null) {
                arrayList.add(actionMapToAction);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            notificationBuilder.addAction((NotificationCompat.Action) it2.next());
        }
    }

    public final void addSummaryMessagesButtons(@NotNull final String profileId, @NotNull final String[] mailIds, @NotNull NotificationCompat.Builder notificationBuilder, final int notificationId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        Intrinsics.checkNotNullParameter(mailIds, "mailIds");
        Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
        Function1 function1 = new Function1() { // from class: ru.mail.util.push.q
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NotificationActionSupplier.addSummaryMessagesButtons$lambda$0(this.f101192a, profileId, mailIds, notificationId, ((Integer) obj).intValue());
            }
        };
        PushMessageType pushMessageType = PushMessageType.SUMMARY_MESSAGES;
        MailItemTransactionCategory mailItemTransactionCategory = MailItemTransactionCategory.NO_CATEGORIES;
        if (canMarkAllAsRead(getSummaryButtons(pushMessageType, mailItemTransactionCategory))) {
            notificationBuilder.addAction((NotificationCompat.Action) function1.invoke(0));
        }
        if (canMarkAllAsRead(getButtonsForWear(pushMessageType, mailItemTransactionCategory))) {
            NotificationCompat.WearableExtender wearableExtenderWithBackground = getWearableExtenderWithBackground();
            wearableExtenderWithBackground.addAction((NotificationCompat.Action) function1.invoke(Integer.valueOf(ru.mail.mails.R.drawable.wear_ic_action_read)));
            notificationBuilder.extend(wearableExtenderWithBackground);
        }
    }

    public final void addSummarySingleMessageThreadButton(@NotNull Entity.NotificationData latestPush, @NotNull List<Entity.NotificationData> pushList, @NotNull NotificationCompat.Builder notificationBuilder, int notificationId) {
        Intrinsics.checkNotNullParameter(latestPush, "latestPush");
        Intrinsics.checkNotNullParameter(pushList, "pushList");
        Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
        PushMessageType pushMessageType = PushMessageType.SUMMARY_MESSAGES_IN_THREAD;
        List<PushAction> summaryButtons = getSummaryButtons(pushMessageType, latestPush.getCategory());
        List<PushAction> list = summaryButtons;
        LOG.i("Add summary actions: " + CollectionsKt.joinToString$default(list, AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, null, null, 0, null, new Function1() { // from class: ru.mail.util.push.k
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NotificationActionSupplier.addSummarySingleMessageThreadButton$lambda$0((PushAction) obj);
            }
        }, 30, null));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            int i10 = WhenMappings.$EnumSwitchMapping$0[((PushAction) it.next()).ordinal()];
            if (i10 == 1) {
                notificationBuilder.addAction(getReplyAction(latestPush.getPush(), latestPush.getCategory(), pushMessageType));
            } else if (i10 != 7) {
                Unit unit = Unit.INSTANCE;
            } else {
                notificationBuilder.addAction(getDeleteAction(pushList, getIconOrNull(ru.mail.mails.R.drawable.ic_notification_delete), new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, pushMessageType, false, false, false, false, 60, null), notificationId));
            }
        }
    }

    public final void addWearableActions(@NotNull Entity.NotificationData push, @NotNull NotificationCompat.Builder notificationBuilder, int notificationId) {
        Intrinsics.checkNotNullParameter(push, "push");
        Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
        List<PushAction> buttonsForWear = getButtonsForWear(PushMessageType.SINGLE_MESSAGE, push.getCategory());
        List<PushAction> list = buttonsForWear;
        LOG.i("Add wearable actions for single message: " + CollectionsKt.joinToString$default(list, AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, null, null, 0, null, new Function1() { // from class: ru.mail.util.push.j
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NotificationActionSupplier.addWearableActions$lambda$0$0((PushAction) obj);
            }
        }, 30, null));
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            NotificationCompat.Action actionMapToWearableAction = mapToWearableAction((PushAction) it.next(), push, PushMessageType.SINGLE_MESSAGE, notificationId);
            if (actionMapToWearableAction != null) {
                arrayList.add(actionMapToWearableAction);
            }
        }
        notificationBuilder.extend(getWearableExtender().addActions(arrayList));
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    private final NotificationCompat.Action getDeleteAction(List<Entity.NotificationData> pushList, @DrawableRes int drawableResId, NotificationMeta meta, int notificationId) {
        List<Entity.NotificationData> list = pushList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Entity.NotificationData) it.next()).getPush().getMessageId());
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        String profileId = pushList.get(0).getPush().getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
        int i10 = ru.mail.mails.R.string.notification_action_delete;
        String actionRemoveMail = MailServiceImpl.getActionRemoveMail();
        Intrinsics.checkNotNullExpressionValue(actionRemoveMail, "getActionRemoveMail(...)");
        return getDeleteOrArchiveAction(profileId, strArr, drawableResId, i10, actionRemoveMail, meta, notificationId);
    }

    private final NotificationCompat.Action getMarkReadAction(String profileId, String[] messagesIds, int drawableResId, int srtResId, NotificationMeta meta, int notificationId) {
        NotificationIntentFactory notificationIntentFactory = NotificationIntentFactory.INSTANCE;
        Context context = this.context;
        String actionMarkReadMailSingle = MailServiceImpl.getActionMarkReadMailSingle();
        Intrinsics.checkNotNullExpressionValue(actionMarkReadMailSingle, "getActionMarkReadMailSingle(...)");
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(drawableResId, this.context.getString(srtResId), notificationIntentFactory.forMessageMarking$mails_release(context, profileId, actionMarkReadMailSingle, messagesIds, meta, notificationId)).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "build(...)");
        return actionBuild;
    }
}
