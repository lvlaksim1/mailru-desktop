package ru.mail.util.push.vkpns;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.core.di.ConfigModuleEntryPoint;
import ru.mail.locator.Locator;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.util.push.PushFactory;
import ru.mail.util.push.PushFactoryCreatorKt;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.analytics.PushRateLimitAnalytics;
import ru.mail.util.push.analytics.PushRateLimitAnalyticsImpl;
import ru.mail.util.push.analytics.PushRateLimitAnalyticsStub;
import ru.mail.util.push.component.PushComponent;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.token.PushTokenManager;
import ru.mail.utils.TimeProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0014J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J5\u0010(\u001a\u00020%2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020'0*2\b\u0010+\u001a\u0004\u0018\u00010'2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016¢\u0006\u0002\u0010.J\b\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020\u0011H\u0016J\u0010\u00102\u001a\u00020\u001c2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0010\u0010\u0012R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0017\u0010\u0012R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001d\u0010\u001e¨\u00063"}, d2 = {"Lru/mail/util/push/vkpns/VkpnsPushTransport;", "Lru/mail/util/push/PushMessagesTransport;", "context", "Landroid/content/Context;", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "pushTokenManager", "Lru/mail/util/push/token/PushTokenManager;", "availabilityChecker", "Lru/mail/util/push/AvailabilityChecker;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;Lru/mail/util/push/notifier/PushMessageReceivedNotifier;Lru/mail/util/push/token/PushTokenManager;Lru/mail/util/push/AvailabilityChecker;)V", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "isPushSdkEnabledForAllAppsState", "", "()Z", "isPushSdkEnabledForAllAppsState$delegate", "Lkotlin/Lazy;", "pushKitWrapper", "Lru/mail/util/push/vkpns/VkpnsPushKitWrapper;", "isProcessingPushesEnabled", "isProcessingPushesEnabled$delegate", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "pushRateLimitAnalytics", "Lru/mail/util/push/analytics/PushRateLimitAnalytics;", "getPushRateLimitAnalytics", "()Lru/mail/util/push/analytics/PushRateLimitAnalytics;", "pushRateLimitAnalytics$delegate", "getPushMessageType", "Lru/mail/util/push/PushType;", "getPushFactory", "Lru/mail/util/push/PushFactory;", "onNewToken", "", "token", "", "onMessageReceived", "data", "", "from", "pushMeSdkPushId", "", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;)V", "getPushKitWrapper", "Lru/mail/util/push/PushKitWrapper;", "isPushSdkEnabledForAllApps", "initPushRateLimitAnalytics", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsPushTransport extends PushMessagesTransport {
    public static final int $stable = 8;

    @NotNull
    private final MailAppAnalytics analytics;

    /* JADX INFO: renamed from: isProcessingPushesEnabled$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isProcessingPushesEnabled;

    /* JADX INFO: renamed from: isPushSdkEnabledForAllAppsState$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isPushSdkEnabledForAllAppsState;

    @NotNull
    private final VkpnsPushKitWrapper pushKitWrapper;

    /* JADX INFO: renamed from: pushRateLimitAnalytics$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushRateLimitAnalytics;

    @NotNull
    private final RuStoreSdkDto ruStoreConfig;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VkpnsPushTransport(@NotNull final Context context, @NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NotNull PushTokenManager pushTokenManager, @NotNull AvailabilityChecker availabilityChecker) {
        super(context, pushTokenRefreshedNotifier, pushMessageReceivedNotifier, pushTokenManager, availabilityChecker);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Intrinsics.checkNotNullParameter(pushTokenManager, "pushTokenManager");
        Intrinsics.checkNotNullParameter(availabilityChecker, "availabilityChecker");
        RuStoreSdkDto ruStoreSdkDtoProvideRuStoreConfig = ConfigModuleEntryPoint.INSTANCE.provideRuStoreConfig(context);
        this.ruStoreConfig = ruStoreSdkDtoProvideRuStoreConfig;
        this.isPushSdkEnabledForAllAppsState = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: ru.mail.util.push.vkpns.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(VkpnsPushTransport.isPushSdkEnabledForAllAppsState_delegate$lambda$0(this.f101208a));
            }
        });
        this.pushKitWrapper = new VkpnsPushKitWrapper(ruStoreSdkDtoProvideRuStoreConfig);
        this.isProcessingPushesEnabled = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.vkpns.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(VkpnsPushTransport.isProcessingPushesEnabled_delegate$lambda$0(context, this));
            }
        });
        this.analytics = MailAppDependencies.analytics(context);
        this.pushRateLimitAnalytics = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.vkpns.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f101211a.initPushRateLimitAnalytics(context);
            }
        });
    }

    private final PushRateLimitAnalytics getPushRateLimitAnalytics() {
        return (PushRateLimitAnalytics) this.pushRateLimitAnalytics.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PushRateLimitAnalytics initPushRateLimitAnalytics(Context context) {
        DTOConfiguration.Config.PushRateLimitAnalytics pushRateLimitAnalytics = ConfigurationRepository.from(context).getConfiguration().getPushRateLimitAnalytics();
        if (!pushRateLimitAnalytics.getEnabled()) {
            return new PushRateLimitAnalyticsStub();
        }
        return new PushRateLimitAnalyticsImpl(pushRateLimitAnalytics.getTimePeriodInSeconds(), pushRateLimitAnalytics.getPushLimit(), this.analytics, (TimeProvider) Locator.INSTANCE.from(context).locate(TimeProvider.class));
    }

    private final boolean isProcessingPushesEnabled() {
        return ((Boolean) this.isProcessingPushesEnabled.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isProcessingPushesEnabled_delegate$lambda$0(Context context, VkpnsPushTransport vkpnsPushTransport) {
        return vkpnsPushTransport.ruStoreConfig.isPushSdkEnabled() && ((PushComponent) Locator.INSTANCE.from(context).locate(PushComponent.class)).getVkpnsComponent().isShowPushEnabled();
    }

    private final boolean isPushSdkEnabledForAllAppsState() {
        return ((Boolean) this.isPushSdkEnabledForAllAppsState.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isPushSdkEnabledForAllAppsState_delegate$lambda$0(VkpnsPushTransport vkpnsPushTransport) {
        return vkpnsPushTransport.ruStoreConfig.isPushSdkEnabledForAllApps();
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    @NotNull
    protected PushFactory getPushFactory() {
        return PushFactoryCreatorKt.createPushFactory(getPushMessageType());
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    @NotNull
    /* JADX INFO: renamed from: getPushKitWrapper */
    public PushKitWrapper getGcmPushKitWrapper() {
        return this.pushKitWrapper;
    }

    @Override // ru.mail.util.push.PushMessagesTransport
    @NotNull
    public PushType getPushMessageType() {
        return PushType.VKPNS;
    }

    @Override // ru.mail.util.push.PushMessagesTransport, ru.mail.util.push.provider.PushInfoProvider
    public boolean isPushSdkEnabledForAllApps() {
        return isPushSdkEnabledForAllAppsState();
    }

    @Override // ru.mail.util.push.PushMessagesTransport, ru.mail.util.push.notifier.PushMessageReceivedNotifier.Listener
    public void onMessageReceived(@NotNull Map<String, String> data, @Nullable String from, @Nullable Long pushMeSdkPushId) {
        Intrinsics.checkNotNullParameter(data, "data");
        getPushRateLimitAnalytics().onMessageReceived(!isProcessingPushesEnabled());
        if (isProcessingPushesEnabled()) {
            super.onMessageReceived(data, from, pushMeSdkPushId);
        }
    }

    @Override // ru.mail.util.push.PushMessagesTransport, ru.mail.util.push.notifier.PushTokenRefreshedNotifier.Listener
    public void onNewToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        super.onNewToken(token);
        this.analytics.onNewVKPNSPushToken();
    }
}
