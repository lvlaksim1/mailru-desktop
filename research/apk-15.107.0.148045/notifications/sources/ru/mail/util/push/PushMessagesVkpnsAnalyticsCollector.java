package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.api.sdk.exceptions.VKApiCodes;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.config.section.PushAnalyticsConfigDto;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.rustoresdk.VkpnsException;
import ru.mail.util.log.Log;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.vkpns.component.VkpnsComponent;
import ru.mail.util.push.vkpns.component.VkpnsErrorListener;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \"2\u00020\u00012\u00020\u0002:\u0001\"B9\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0016J\u0016\u0010\u0018\u001a\u00020\u00142\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016J$\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0 H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lru/mail/util/push/PushMessagesVkpnsAnalyticsCollector;", "Lru/mail/util/push/PushMessagesAnalyticsCollectorImpl;", "Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "vkpnsComponent", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "hostInfo", "Lru/mail/util/push/PushMessagesAnalyticsCollector$VkpnsHostInfo;", "pushAnalyticsConfig", "Lru/mail/config/section/PushAnalyticsConfigDto;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "<init>", "(Lru/mail/util/push/vkpns/component/VkpnsComponent;Lru/mail/util/push/notifier/PushMessageReceivedNotifier;Lru/mail/analytics/MailAppAnalytics;Lru/mail/util/push/PushMessagesAnalyticsCollector$VkpnsHostInfo;Lru/mail/config/section/PushAnalyticsConfigDto;Lru/mail/config/section/RuStoreSdkDto;)V", "sendingAnalyticsEnabled", "", "startCollectAnalytics", "", "transports", "", "Lru/mail/util/push/PushMessagesTransport;", BatchApiRequest.FIELD_NAME_ON_ERROR, VKApiCodes.PARAM_ERROR_MULTI, "", "Lru/mail/rustoresdk/VkpnsException;", "onMessageReceived", "type", "Lru/mail/util/push/PushType;", "data", "", "", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMessagesVkpnsAnalyticsCollector.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMessagesVkpnsAnalyticsCollector.kt\nru/mail/util/push/PushMessagesVkpnsAnalyticsCollector\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,65:1\n1761#2,3:66\n1761#2,3:69\n*S KotlinDebug\n*F\n+ 1 PushMessagesVkpnsAnalyticsCollector.kt\nru/mail/util/push/PushMessagesVkpnsAnalyticsCollector\n*L\n36#1:66,3\n39#1:69,3\n*E\n"})
public final class PushMessagesVkpnsAnalyticsCollector extends PushMessagesAnalyticsCollectorImpl implements VkpnsErrorListener {

    @NotNull
    private final MailAppAnalytics analytics;
    private volatile boolean sendingAnalyticsEnabled;

    @NotNull
    private final VkpnsComponent vkpnsComponent;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PushMessagesVkpnsAnalyticsCollector");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMessagesVkpnsAnalyticsCollector(@NotNull VkpnsComponent vkpnsComponent, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NotNull MailAppAnalytics analytics, @Nullable PushMessagesAnalyticsCollector.VkpnsHostInfo vkpnsHostInfo, @NotNull PushAnalyticsConfigDto pushAnalyticsConfig, @NotNull RuStoreSdkDto ruStoreConfig) {
        super(vkpnsComponent, pushMessageReceivedNotifier, analytics, vkpnsHostInfo, pushAnalyticsConfig, ruStoreConfig);
        Intrinsics.checkNotNullParameter(vkpnsComponent, "vkpnsComponent");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(pushAnalyticsConfig, "pushAnalyticsConfig");
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        this.vkpnsComponent = vkpnsComponent;
        this.analytics = analytics;
        this.sendingAnalyticsEnabled = true;
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsErrorListener
    public void onError(@NotNull List<? extends VkpnsException> errors) {
        Intrinsics.checkNotNullParameter(errors, "errors");
        List<? extends VkpnsException> list = errors;
        boolean z10 = list instanceof Collection;
        if (!z10 || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((VkpnsException) it.next()).getIsCritical()) {
                    LOG.i("Stop collecting analytics because it seems VKPNS transport is not working");
                    this.sendingAnalyticsEnabled = false;
                    return;
                }
            }
        }
        if (z10 && list.isEmpty()) {
            return;
        }
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            if (!((VkpnsException) it2.next()).getIsCritical()) {
                LOG.i("VKPNS transport should work, but it may be unstable. Consider it when sending analytics");
                this.analytics.onVkpnsNotCriticalErrorDetected(getHostPackageName(), getHostVersionCode());
                return;
            }
        }
    }

    @Override // ru.mail.util.push.PushMessagesAnalyticsCollectorImpl
    protected void onMessageReceived(@NotNull PushType type, @NotNull Map<String, String> data) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(data, "data");
        super.onMessageReceived(type, data);
        if (this.sendingAnalyticsEnabled) {
            if (isVkpnsTokenExists()) {
                sendMessageReceivedAnalytics(type, data, true);
                return;
            } else {
                LOG.w("Unable to find VKPNS push token, skip sending analytics");
                return;
            }
        }
        LOG.i("On new push message received from " + type + ", but sending analytics is disabled");
    }

    @Override // ru.mail.util.push.PushMessagesAnalyticsCollectorImpl, ru.mail.util.push.PushMessagesAnalyticsCollector
    public void startCollectAnalytics(@NotNull Collection<? extends PushMessagesTransport> transports) {
        Intrinsics.checkNotNullParameter(transports, "transports");
        super.startCollectAnalytics(transports);
        LOG.i("Start collecting push analytics, host = " + getHostPackageName());
        this.vkpnsComponent.setErrorListener(this);
    }
}
