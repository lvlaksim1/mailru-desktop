package ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.notification;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.SessionRestoreHelper;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.TimeDelayEvaluator;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.ReturnParams;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreAnalytics;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/NotificationHelper;", "", "sessionRestoreAnalytics", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreAnalytics;", "sessionRestoreHelper", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/SessionRestoreHelper;", "restoreSessionNotificationProvider", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/RestoreSessionNotificationProvider;", "<init>", "(Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreAnalytics;Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/SessionRestoreHelper;Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/RestoreSessionNotificationProvider;)V", "showRestoreAuthFlowNotification", "", "secondsPassed", "", "returnUserParams", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/ReturnParams;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationHelper {
    public static final int $stable = 8;

    @Nullable
    private final RestoreSessionNotificationProvider restoreSessionNotificationProvider;

    @NotNull
    private final SessionRestoreAnalytics sessionRestoreAnalytics;

    @NotNull
    private final SessionRestoreHelper sessionRestoreHelper;

    public NotificationHelper(@NotNull SessionRestoreAnalytics sessionRestoreAnalytics, @NotNull SessionRestoreHelper sessionRestoreHelper, @Nullable RestoreSessionNotificationProvider restoreSessionNotificationProvider) {
        Intrinsics.checkNotNullParameter(sessionRestoreAnalytics, "sessionRestoreAnalytics");
        Intrinsics.checkNotNullParameter(sessionRestoreHelper, "sessionRestoreHelper");
        this.sessionRestoreAnalytics = sessionRestoreAnalytics;
        this.sessionRestoreHelper = sessionRestoreHelper;
        this.restoreSessionNotificationProvider = restoreSessionNotificationProvider;
    }

    public final void showRestoreAuthFlowNotification(long secondsPassed, @NotNull ReturnParams returnUserParams) {
        Intrinsics.checkNotNullParameter(returnUserParams, "returnUserParams");
        this.sessionRestoreHelper.incrementUsages();
        SessionRestoreAnalytics sessionRestoreAnalytics = this.sessionRestoreAnalytics;
        String strEvaluate = new TimeDelayEvaluator().evaluate((int) secondsPassed);
        ReturnParams.Companion companion = ReturnParams.INSTANCE;
        sessionRestoreAnalytics.sendAnalyticRestoreShown(strEvaluate, companion.resolveName(returnUserParams), companion.resolveIsRestore(returnUserParams), companion.resolveHasAccounts(returnUserParams));
        RestoreSessionNotificationProvider restoreSessionNotificationProvider = this.restoreSessionNotificationProvider;
        if (restoreSessionNotificationProvider != null) {
            restoreSessionNotificationProvider.showRestoreFlowNotification(returnUserParams);
        }
    }
}
