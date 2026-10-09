package ru.mail.util.push.gcm;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailapp.R;
import ru.mail.util.log.Log;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.utils.FirebaseInfoProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/util/push/gcm/GcmPushKitWrapper;", "Lru/mail/util/push/PushKitWrapper;", "context", "Landroid/content/Context;", "gcmAvailabilityChecker", "Lru/mail/util/push/AvailabilityChecker;", "firebaseInfoProvider", "Lru/mail/utils/FirebaseInfoProvider;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/AvailabilityChecker;Lru/mail/utils/FirebaseInfoProvider;)V", "logger", "Lru/mail/util/log/Log;", "getPushTokenFromPushKit", "", "deleteToken", "", "getSenderId", "checkForAvailability", "Lru/mail/util/push/AvailabilityCheckResult;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GcmPushKitWrapper implements PushKitWrapper {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    @NotNull
    private final FirebaseInfoProvider firebaseInfoProvider;

    @NotNull
    private final AvailabilityChecker gcmAvailabilityChecker;

    @NotNull
    private final Log logger;

    public GcmPushKitWrapper(@NotNull Context context, @NotNull AvailabilityChecker gcmAvailabilityChecker, @NotNull FirebaseInfoProvider firebaseInfoProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(gcmAvailabilityChecker, "gcmAvailabilityChecker");
        Intrinsics.checkNotNullParameter(firebaseInfoProvider, "firebaseInfoProvider");
        this.context = context;
        this.gcmAvailabilityChecker = gcmAvailabilityChecker;
        this.firebaseInfoProvider = firebaseInfoProvider;
        this.logger = Log.INSTANCE.getLog("GcmPushKitWrapper");
    }

    private final String getSenderId() {
        String string = this.context.getString(R.string.push_sender_id);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.util.push.AvailabilityChecker
    @NotNull
    public AvailabilityCheckResult checkForAvailability() {
        return this.gcmAvailabilityChecker.checkForAvailability();
    }

    @Override // ru.mail.util.push.PushKitWrapper
    public void deleteToken() {
        this.logger.d("Deleting push token from push kit...");
        this.firebaseInfoProvider.deleteToken();
    }

    @Override // ru.mail.util.push.PushKitWrapper
    @Nullable
    public String getPushTokenFromPushKit() {
        this.logger.d("Getting push token from push kit...");
        return this.firebaseInfoProvider.getToken(getSenderId());
    }
}
