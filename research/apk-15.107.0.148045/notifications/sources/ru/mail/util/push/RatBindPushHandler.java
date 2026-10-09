package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.socialbind.RatBindPushWorker;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.ObservableFutureTask;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.sdk.MailSdkEntryPoint;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lru/mail/util/push/RatBindPushHandler;", "", "<init>", "()V", "handleRatBindPush", "Lru/mail/mailbox/cmd/ObservableFuture;", "Ljava/lang/Void;", "context", "Landroid/content/Context;", "message", "Lru/mail/util/push/RatBindPush;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RatBindPushHandler {
    public static final int $stable = 0;

    @NotNull
    public static final RatBindPushHandler INSTANCE = new RatBindPushHandler();

    private RatBindPushHandler() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Void handleRatBindPush$lambda$0(RatBindPush ratBindPush, Context context) {
        RatBindPushWorker.Params params = new RatBindPushWorker.Params();
        String profileId = ratBindPush.getProfileId();
        Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
        params.setEmail(profileId);
        HandlePushEntryPoint.INSTANCE.workScheduler(context).schedule(new WorkRequest.Builder(RatBindPushWorker.class, RatBindPushWorker.UNIQUE_ID).data(params.toData()).constraints(WorkRequest.Constraints.NETWORK).getRequest());
        return null;
    }

    @NotNull
    public final ObservableFuture<Void> handleRatBindPush(@NotNull final Context context, @NotNull final RatBindPush message) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(message, "message");
        MailSdkEntryPoint.INSTANCE.mailAnalyticsKt(context).onVKIDRatBindPushHandleStarted();
        ObservableFutureTask observableFutureTask = new ObservableFutureTask(new Callable() { // from class: ru.mail.util.push.h1
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RatBindPushHandler.handleRatBindPush$lambda$0(message, context);
            }
        });
        ExecutorsKt.asExecutor(Dispatchers.getDefault()).execute(observableFutureTask);
        return observableFutureTask;
    }
}
