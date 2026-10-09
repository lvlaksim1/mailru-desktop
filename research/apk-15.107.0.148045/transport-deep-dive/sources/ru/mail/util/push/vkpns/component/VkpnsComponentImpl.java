package ru.mail.util.push.vkpns.component;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.config.section.VkpnsHostSdkDto;
import ru.mail.rustoresdk.RuStoreSdkConnector;
import ru.mail.rustoresdk.VkpnsEventsListenerHolder;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.vkpns.VkpnsHostResolver;
import ru.mail.util.push.vkpns.VkpnsPushKitWrapper;
import ru.mail.util.push.vkpns.component.VkpnsComponentImpl;
import ru.mail.utils.RuStoreUtil;
import ru.mail.utils.feature.Features;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020(H\u0016J\b\u0010\u0012\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020\u0016H\u0016J\b\u0010+\u001a\u00020\u001bH\u0016J\b\u0010,\u001a\u00020\u001bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0012\u0010\u0013R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u000f\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001c\u0010\u001d¨\u0006."}, d2 = {"Lru/mail/util/push/vkpns/component/VkpnsComponentImpl;", "Lru/mail/util/push/vkpns/component/VkpnsComponent;", "context", "Landroid/content/Context;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "vkpnsHostConfig", "Lru/mail/config/section/VkpnsHostSdkDto;", "<init>", "(Landroid/content/Context;Lru/mail/config/section/RuStoreSdkDto;Lru/mail/config/section/VkpnsHostSdkDto;)V", "vkpnsEventsBridge", "Lru/mail/util/push/vkpns/component/VkpnsEventsBridge;", "getVkpnsEventsBridge", "()Lru/mail/util/push/vkpns/component/VkpnsEventsBridge;", "vkpnsEventsBridge$delegate", "Lkotlin/Lazy;", "pushKitWrapper", "Lru/mail/util/push/vkpns/VkpnsPushKitWrapper;", "getPushKitWrapper", "()Lru/mail/util/push/vkpns/VkpnsPushKitWrapper;", "pushKitWrapper$delegate", "vkpnsHostResolver", "Lru/mail/util/push/vkpns/VkpnsHostResolver;", "getVkpnsHostResolver", "()Lru/mail/util/push/vkpns/VkpnsHostResolver;", "vkpnsHostResolver$delegate", "showPushEnabled", "", "getShowPushEnabled", "()Z", "showPushEnabled$delegate", "setPushTokenListener", "", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "setMessagesListener", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "setErrorListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/util/push/vkpns/component/VkpnsErrorListener;", "Lru/mail/util/push/PushKitWrapper;", "getHostResolver", "isShowPushEnabled", "isShowPushEnabledInternal", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsComponentImpl implements VkpnsComponent {

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: pushKitWrapper$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushKitWrapper;

    @NotNull
    private final RuStoreSdkDto ruStoreConfig;

    /* JADX INFO: renamed from: showPushEnabled$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy showPushEnabled;

    /* JADX INFO: renamed from: vkpnsEventsBridge$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy vkpnsEventsBridge;

    @NotNull
    private final VkpnsHostSdkDto vkpnsHostConfig;

    /* JADX INFO: renamed from: vkpnsHostResolver$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy vkpnsHostResolver;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkpnsComponentImpl");

    public VkpnsComponentImpl(@NotNull Context context, @NotNull RuStoreSdkDto ruStoreConfig, @NotNull VkpnsHostSdkDto vkpnsHostConfig) {
        VkpnsEventsListenerHolder vkpnsEventsListenerHolder;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        Intrinsics.checkNotNullParameter(vkpnsHostConfig, "vkpnsHostConfig");
        this.context = context;
        this.ruStoreConfig = ruStoreConfig;
        this.vkpnsHostConfig = vkpnsHostConfig;
        this.vkpnsEventsBridge = LazyKt.lazy(new Function0() { // from class: kh.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkpnsComponentImpl.vkpnsEventsBridge_delegate$lambda$0(this.f72437a);
            }
        });
        this.pushKitWrapper = LazyKt.lazy(new Function0() { // from class: kh.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkpnsComponentImpl.pushKitWrapper_delegate$lambda$0(this.f72438a);
            }
        });
        this.vkpnsHostResolver = LazyKt.lazy(new Function0() { // from class: kh.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return VkpnsComponentImpl.vkpnsHostResolver_delegate$lambda$0(this.f72439a);
            }
        });
        this.showPushEnabled = LazyKt.lazy(new Function0() { // from class: kh.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(this.f72440a.isShowPushEnabledInternal());
            }
        });
        RuStoreSdkConnector ruStoreSdkConnector = (RuStoreSdkConnector) Features.getProvider().provideOptional(RuStoreSdkConnector.class);
        if (ruStoreSdkConnector == null || (vkpnsEventsListenerHolder = ruStoreSdkConnector.getVkpnsEventsListenerHolder()) == null) {
            return;
        }
        vkpnsEventsListenerHolder.setListener(getVkpnsEventsBridge());
    }

    private final VkpnsPushKitWrapper getPushKitWrapper() {
        return (VkpnsPushKitWrapper) this.pushKitWrapper.getValue();
    }

    private final boolean getShowPushEnabled() {
        return ((Boolean) this.showPushEnabled.getValue()).booleanValue();
    }

    private final VkpnsEventsBridge getVkpnsEventsBridge() {
        return (VkpnsEventsBridge) this.vkpnsEventsBridge.getValue();
    }

    private final VkpnsHostResolver getVkpnsHostResolver() {
        return (VkpnsHostResolver) this.vkpnsHostResolver.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isShowPushEnabledInternal() {
        Log log = LOG;
        log.i("Calculating is show push enabled...");
        RuStoreSdkDto.ShowPushConfigDto showPushConfig = this.ruStoreConfig.getShowPushConfig();
        if (!showPushConfig.isEnabled()) {
            log.i("Show push is disabled in config");
            return false;
        }
        VkpnsHostResolver.HostInfo preferredHost = getVkpnsHostResolver().getPreferredHost();
        if (preferredHost == null) {
            log.i("Host is unknown because it was selected by SDK automatically. Show push is enabled.");
            return true;
        }
        if (Intrinsics.areEqual("ru.vk.store", preferredHost.getPackageName())) {
            long ruStoreMinVersion = showPushConfig.getRuStoreMinVersion();
            Integer ruStoreVersion = RuStoreUtil.getRuStoreVersion(this.context);
            int iIntValue = ruStoreVersion != null ? ruStoreVersion.intValue() : 0;
            if (iIntValue <= 0 || iIntValue < ruStoreMinVersion) {
                log.i("Show push is disabled because RuStore version (" + iIntValue + ") is lower than required");
                return false;
            }
        }
        if (!showPushConfig.getHostPermissionRequired() || preferredHost.getHasBackgroundPermission()) {
            log.i("Show push is enabled, host: " + preferredHost.getPackageName());
            return true;
        }
        log.i("Show push is disabled because host (" + preferredHost.getPackageName() + ") does not have permission");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VkpnsPushKitWrapper pushKitWrapper_delegate$lambda$0(VkpnsComponentImpl vkpnsComponentImpl) {
        return new VkpnsPushKitWrapper(vkpnsComponentImpl.ruStoreConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VkpnsEventsBridge vkpnsEventsBridge_delegate$lambda$0(VkpnsComponentImpl vkpnsComponentImpl) {
        return new VkpnsEventsBridge(vkpnsComponentImpl.context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VkpnsHostResolver vkpnsHostResolver_delegate$lambda$0(VkpnsComponentImpl vkpnsComponentImpl) {
        return new VkpnsHostResolver(vkpnsComponentImpl.context, vkpnsComponentImpl.ruStoreConfig, vkpnsComponentImpl.vkpnsHostConfig);
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    @NotNull
    public VkpnsHostResolver getHostResolver() {
        return getVkpnsHostResolver();
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public boolean isShowPushEnabled() {
        return getShowPushEnabled();
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public void setErrorListener(@NotNull VkpnsErrorListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        getVkpnsEventsBridge().setErrorListener(listener);
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public void setMessagesListener(@NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier) {
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        getVkpnsEventsBridge().setPushMessageReceivedNotifier(pushMessageReceivedNotifier);
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    public void setPushTokenListener(@NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier) {
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
        getVkpnsEventsBridge().setPushTokenRefreshedNotifier(pushTokenRefreshedNotifier);
    }

    @Override // ru.mail.util.push.vkpns.component.VkpnsComponent
    @NotNull
    /* JADX INFO: renamed from: getPushKitWrapper, reason: collision with other method in class */
    public PushKitWrapper mo15888getPushKitWrapper() {
        return getPushKitWrapper();
    }
}
