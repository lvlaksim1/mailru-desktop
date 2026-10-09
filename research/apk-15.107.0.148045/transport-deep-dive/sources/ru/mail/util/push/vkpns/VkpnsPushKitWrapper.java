package ru.mail.util.push.vkpns;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.rustoresdk.RuStorePushSdkWrapper;
import ru.mail.rustoresdk.RuStoreSdkConnector;
import ru.mail.util.log.Log;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.utils.feature.Features;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/vkpns/VkpnsPushKitWrapper;", "Lru/mail/util/push/PushKitWrapper;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "<init>", "(Lru/mail/config/section/RuStoreSdkDto;)V", "availabilityChecker", "Lru/mail/util/push/vkpns/VkpnsAvailabilityChecker;", "getPushTokenFromPushKit", "", "deleteToken", "", "checkForAvailability", "Lru/mail/util/push/AvailabilityCheckResult;", "getSdkWrapper", "Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsPushKitWrapper implements PushKitWrapper {

    @NotNull
    private final VkpnsAvailabilityChecker availabilityChecker;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkpnsPushKitWrapper");

    public VkpnsPushKitWrapper(@NotNull RuStoreSdkDto ruStoreConfig) {
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        this.availabilityChecker = new VkpnsAvailabilityChecker(ruStoreConfig);
    }

    private final RuStorePushSdkWrapper getSdkWrapper() {
        RuStoreSdkConnector ruStoreSdkConnector = (RuStoreSdkConnector) Features.getProvider().provideOptional(RuStoreSdkConnector.class);
        if (ruStoreSdkConnector != null) {
            return ruStoreSdkConnector.getPushSdkWrapper();
        }
        return null;
    }

    @Override // ru.mail.util.push.AvailabilityChecker
    @NotNull
    public AvailabilityCheckResult checkForAvailability() {
        return this.availabilityChecker.checkForAvailability();
    }

    @Override // ru.mail.util.push.PushKitWrapper
    public void deleteToken() {
        Log log = LOG;
        log.i("Deleting push token from SDK...");
        try {
            RuStorePushSdkWrapper sdkWrapper = getSdkWrapper();
            log.i("Deleting push token from SDK has finished, result = " + (sdkWrapper != null ? Boolean.valueOf(sdkWrapper.deleteToken()) : null));
        } catch (Exception e10) {
            LOG.e("Failed to delete push token from SDK", e10);
        }
    }

    @Override // ru.mail.util.push.PushKitWrapper
    @Nullable
    public String getPushTokenFromPushKit() {
        Log log = LOG;
        log.i("Getting push token from SDK...");
        try {
            RuStorePushSdkWrapper sdkWrapper = getSdkWrapper();
            String token = sdkWrapper != null ? sdkWrapper.getToken() : null;
            log.i("Getting push token has finished, is it null or blank: " + (token == null || StringsKt.isBlank(token)));
            return token;
        } catch (Exception e10) {
            LOG.e("Unable to get push token from SDK", e10);
            return null;
        }
    }
}
