package ru.mail.util.push.vkpns.filter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.MailApplication;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.util.LoopSharedPreferences;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushProcessor;
import ru.mail.util.push.PushType;
import ru.mail.util.push.vkpns.VkpnsHostResolver;
import ru.mail.util.push.vkpns.filter.VkpnsPushDeduplicatorImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J$\u0010\u0012\u001a\u00020\u00052\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u0004\u0018\u00010\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lru/mail/util/push/vkpns/filter/VkpnsPushDeduplicatorImpl;", "Lru/mail/util/push/vkpns/filter/PushDeduplicator;", "context", "Landroid/content/Context;", "keepReceivedPushesTillTheLast", "", "<init>", "(Landroid/content/Context;Z)V", "prefs", "Landroid/content/SharedPreferences;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "vkpnsHost", "Lru/mail/util/push/vkpns/VkpnsHostResolver$HostInfo;", "getVkpnsHost", "()Lru/mail/util/push/vkpns/VkpnsHostResolver$HostInfo;", "vkpnsHost$delegate", "Lkotlin/Lazy;", "isDuplicate", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "", "", "pushType", "Lru/mail/util/push/PushType;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsPushDeduplicatorImpl implements PushDeduplicator {

    @NotNull
    private final MailAppAnalytics analytics;
    private final boolean keepReceivedPushesTillTheLast;

    @NotNull
    private final SharedPreferences prefs;

    /* JADX INFO: renamed from: vkpnsHost$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy vkpnsHost;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkpnsPushDeduplicatorImpl");

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushType.values().length];
            try {
                iArr[PushType.GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushType.HMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public VkpnsPushDeduplicatorImpl(@NotNull final Context context, boolean z10) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.keepReceivedPushesTillTheLast = z10;
        this.prefs = LoopSharedPreferences.INSTANCE.createForMailFetchedPushes(context);
        this.analytics = MailAppDependencies.analytics(context);
        this.vkpnsHost = LazyKt.lazy(new Function0() { // from class: lh.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkpnsPushDeduplicatorImpl.vkpnsHost_delegate$lambda$0(context);
            }
        });
    }

    private final VkpnsHostResolver.HostInfo getVkpnsHost() {
        return (VkpnsHostResolver.HostInfo) this.vkpnsHost.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VkpnsHostResolver.HostInfo vkpnsHost_delegate$lambda$0(Context context) {
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type ru.mail.MailApplication");
        return ((MailApplication) applicationContext).getPushComponent().getVkpnsComponent().getHostResolver().getPreferredHost();
    }

    @Override // ru.mail.util.push.vkpns.filter.PushDeduplicator
    @SuppressLint({"ApplySharedPref"})
    @WorkerThread
    public synchronized boolean isDuplicate(@NotNull Map<String, String> payload, @NotNull PushType pushType) {
        String packageName;
        String packageName2;
        Intrinsics.checkNotNullParameter(payload, "payload");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        long jCurrentTimeMillis = System.currentTimeMillis();
        String str = payload.get(PushProcessor.DATAKEY_PUSH_ID);
        if (str == null) {
            LOG.d("Received push. Field \"pushme_push_id\" is null, transport: " + pushType.getAnalyticsName());
            return false;
        }
        Log log = LOG;
        log.i("Received push with id: " + str + ", transport: " + pushType.getAnalyticsName());
        String string = this.prefs.getString(str, null);
        if (string == null) {
            this.prefs.edit().putString(str, PushAnalytic.INSTANCE.toString(jCurrentTimeMillis, pushType)).apply();
            return false;
        }
        log.i("Push received by second transport! Push ID: " + str + ", transport: " + pushType.getAnalyticsName());
        PushAnalytic pushAnalyticFromString = PushAnalytic.INSTANCE.fromString(string);
        if (pushAnalyticFromString == null) {
            return true;
        }
        Pair pair = pushType == PushType.VKPNS ? TuplesKt.to(pushAnalyticFromString.getType(), Long.valueOf(jCurrentTimeMillis - pushAnalyticFromString.getTimestamp())) : TuplesKt.to(pushType, Long.valueOf(pushAnalyticFromString.getTimestamp() - jCurrentTimeMillis));
        PushType pushType2 = (PushType) pair.component1();
        long jLongValue = ((Number) pair.component2()).longValue();
        int i10 = WhenMappings.$EnumSwitchMapping$0[pushType2.ordinal()];
        if (i10 == 1) {
            MailAppAnalytics mailAppAnalytics = this.analytics;
            VkpnsHostResolver.HostInfo vkpnsHost = getVkpnsHost();
            if (vkpnsHost == null || (packageName = vkpnsHost.getPackageName()) == null) {
                packageName = "unknown";
            }
            String str2 = packageName;
            VkpnsHostResolver.HostInfo vkpnsHost2 = getVkpnsHost();
            int versionCode = vkpnsHost2 != null ? vkpnsHost2.getVersionCode() : 0;
            VkpnsHostResolver.HostInfo vkpnsHost3 = getVkpnsHost();
            mailAppAnalytics.onSecondTransportFCMPushReceived(jLongValue, str, str2, versionCode, vkpnsHost3 != null ? vkpnsHost3.getHasBackgroundPermission() : false);
        } else if (i10 == 2) {
            MailAppAnalytics mailAppAnalytics2 = this.analytics;
            VkpnsHostResolver.HostInfo vkpnsHost4 = getVkpnsHost();
            if (vkpnsHost4 == null || (packageName2 = vkpnsHost4.getPackageName()) == null) {
                packageName2 = "unknown";
            }
            String str3 = packageName2;
            VkpnsHostResolver.HostInfo vkpnsHost5 = getVkpnsHost();
            int versionCode2 = vkpnsHost5 != null ? vkpnsHost5.getVersionCode() : 0;
            VkpnsHostResolver.HostInfo vkpnsHost6 = getVkpnsHost();
            mailAppAnalytics2.onSecondTransportHMSPushReceived(jLongValue, str, str3, versionCode2, vkpnsHost6 != null ? vkpnsHost6.getHasBackgroundPermission() : false);
        }
        if (!this.keepReceivedPushesTillTheLast) {
            this.prefs.edit().remove(str).apply();
        }
        return true;
    }
}
