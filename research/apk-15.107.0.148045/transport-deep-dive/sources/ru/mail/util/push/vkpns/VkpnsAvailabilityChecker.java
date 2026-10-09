package ru.mail.util.push.vkpns;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.section.RuStoreSdkDto;
import ru.mail.rustoresdk.RuStoreSdkConnector;
import ru.mail.util.log.Log;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.utils.feature.Features;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0002\n\u000bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/util/push/vkpns/VkpnsAvailabilityChecker;", "Lru/mail/util/push/AvailabilityChecker;", "ruStoreConfig", "Lru/mail/config/section/RuStoreSdkDto;", "<init>", "(Lru/mail/config/section/RuStoreSdkDto;)V", "checkForAvailability", "Lru/mail/util/push/AvailabilityCheckResult;", "isAvailable", "", "VkpnsAvailabilityCheckResult", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkpnsAvailabilityChecker implements AvailabilityChecker {

    @NotNull
    private final RuStoreSdkDto ruStoreConfig;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkpnsAvailabilityChecker");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/vkpns/VkpnsAvailabilityChecker$VkpnsAvailabilityCheckResult;", "Lru/mail/util/push/AvailabilityCheckResult;", "isAvailable", "", "<init>", "(Z)V", "isUserRecoverable", "showUserRecoveryNotification", "", "context", "Landroid/content/Context;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class VkpnsAvailabilityCheckResult implements AvailabilityCheckResult {
        private final boolean isAvailable;

        public VkpnsAvailabilityCheckResult(boolean z10) {
            this.isAvailable = z10;
        }

        @Override // ru.mail.util.push.AvailabilityCheckResult
        /* JADX INFO: renamed from: isAvailable, reason: from getter */
        public boolean getIsAvailable() {
            return this.isAvailable;
        }

        @Override // ru.mail.util.push.AvailabilityCheckResult
        public boolean isUserRecoverable() {
            return false;
        }

        @Override // ru.mail.util.push.AvailabilityCheckResult
        public void showUserRecoveryNotification(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
        }
    }

    public VkpnsAvailabilityChecker(@NotNull RuStoreSdkDto ruStoreConfig) {
        Intrinsics.checkNotNullParameter(ruStoreConfig, "ruStoreConfig");
        this.ruStoreConfig = ruStoreConfig;
    }

    private final boolean isAvailable() {
        if (!this.ruStoreConfig.isPushSdkEnabled()) {
            LOG.i("VKPNS is disabled in config");
            return false;
        }
        boolean z10 = ((RuStoreSdkConnector) Features.getProvider().provideOptional(RuStoreSdkConnector.class)) != null;
        LOG.i("VKPNS is available: " + z10);
        return z10;
    }

    @Override // ru.mail.util.push.AvailabilityChecker
    @NotNull
    public AvailabilityCheckResult checkForAvailability() {
        return new VkpnsAvailabilityCheckResult(isAvailable());
    }
}
