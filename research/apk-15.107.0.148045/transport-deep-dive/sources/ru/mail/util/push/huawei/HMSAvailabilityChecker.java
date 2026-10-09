package ru.mail.util.push.huawei;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import com.huawei.hms.api.HuaweiApiAvailability;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.AvailabilityCheckResultNotAvailable;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.utils.safeutils.Handler;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/util/push/huawei/HMSAvailabilityChecker;", "Lru/mail/util/push/AvailabilityChecker;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "packageManager", "Lru/mail/utils/safeutils/PackageManagerUtil$RequestInitiator;", "kotlin.jvm.PlatformType", "checkForAvailability", "Lru/mail/util/push/AvailabilityCheckResult;", "AvailabilityCheckHandler", "HuaweiAvailabilityCheckResult", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HMSAvailabilityChecker implements AvailabilityChecker {
    public static final int $stable = 8;

    @NotNull
    private final Context context;
    private final PackageManagerUtil.RequestInitiator packageManager;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\u00032\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/util/push/huawei/HMSAvailabilityChecker$AvailabilityCheckHandler;", "Lru/mail/utils/safeutils/Handler;", "Landroid/content/pm/PackageManager;", "Lru/mail/util/push/AvailabilityCheckResult;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "call", MediationConfiguration.CUSTOM_EVENT_SERVER_PARAMETER_FIELD, "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class AvailabilityCheckHandler implements Handler<PackageManager, AvailabilityCheckResult> {

        @NotNull
        private final Context context;

        public AvailabilityCheckHandler(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            this.context = context;
        }

        @Override // ru.mail.utils.safeutils.Handler
        @NotNull
        public AvailabilityCheckResult call(@Nullable PackageManager parameter) {
            return new HuaweiAvailabilityCheckResult(HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this.context));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/util/push/huawei/HMSAvailabilityChecker$HuaweiAvailabilityCheckResult;", "Lru/mail/util/push/AvailabilityCheckResult;", "resultCode", "", "<init>", "(I)V", "isAvailable", "", "isUserRecoverable", "showUserRecoveryNotification", "", "context", "Landroid/content/Context;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class HuaweiAvailabilityCheckResult implements AvailabilityCheckResult {
        private final int resultCode;

        public HuaweiAvailabilityCheckResult(int i10) {
            this.resultCode = i10;
        }

        @Override // ru.mail.util.push.AvailabilityCheckResult
        public boolean isAvailable() {
            return this.resultCode == 0;
        }

        @Override // ru.mail.util.push.AvailabilityCheckResult
        public boolean isUserRecoverable() {
            return HuaweiApiAvailability.getInstance().isUserResolvableError(this.resultCode);
        }

        @Override // ru.mail.util.push.AvailabilityCheckResult
        public void showUserRecoveryNotification(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            HuaweiApiAvailability.getInstance().showErrorNotification(context, this.resultCode);
        }
    }

    public HMSAvailabilityChecker(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.packageManager = PackageManagerUtil.from(context);
    }

    @Override // ru.mail.util.push.AvailabilityChecker
    @NotNull
    public AvailabilityCheckResult checkForAvailability() {
        Object objPerform = this.packageManager.doWithPackageManager(new AvailabilityCheckHandler(this.context)).onErrorReturn(new AvailabilityCheckResultNotAvailable()).perform();
        Intrinsics.checkNotNullExpressionValue(objPerform, "perform(...)");
        return (AvailabilityCheckResult) objPerform;
    }
}
