package ru.mail.util.push.gcm;

import android.content.Context;
import android.util.AndroidRuntimeException;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.SortedSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Description;
import ru.mail.asserter.description.Descriptions;
import ru.mail.locator.Locator;
import ru.mail.util.log.LogCollector;
import ru.mail.util.push.PushMessagesTransport;
import ru.ok.android.sdk.OkListenerKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/util/push/gcm/PushAsserterThrowable;", "Lru/mail/util/push/gcm/AsserterThrowable;", "context", "Landroid/content/Context;", "pushMessagesTransport", "Lru/mail/util/push/PushMessagesTransport;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/PushMessagesTransport;)V", "handleThrowable", "", "thread", "Ljava/lang/Thread;", OkListenerKt.KEY_EXCEPTION, "", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushAsserterThrowable implements AsserterThrowable {

    @NotNull
    public static final String PUSH_NOTIFICATION_MESSAGE = "Couldn't expand RemoteViews";

    @NotNull
    public static final String REMOTE_SERVICE_EXCEPTION_PATH = "android.app.RemoteServiceException";

    @NotNull
    private final Context context;

    @NotNull
    private final PushMessagesTransport pushMessagesTransport;
    public static final int $stable = 8;

    public PushAsserterThrowable(@NotNull Context context, @NotNull PushMessagesTransport pushMessagesTransport) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pushMessagesTransport, "pushMessagesTransport");
        this.context = context;
        this.pushMessagesTransport = pushMessagesTransport;
    }

    @Override // ru.mail.util.push.gcm.AsserterThrowable
    public boolean handleThrowable(@NotNull Thread thread, @NotNull Throwable exception) {
        Intrinsics.checkNotNullParameter(thread, "thread");
        Intrinsics.checkNotNullParameter(exception, "exception");
        Throwable cause = exception.getCause();
        String message = cause != null ? cause.getMessage() : null;
        String message2 = exception.getMessage();
        boolean z10 = ((exception instanceof AndroidRuntimeException) || (exception.getCause() instanceof AndroidRuntimeException)) && ((message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) PUSH_NOTIFICATION_MESSAGE, false, 2, (Object) null)) || ((message != null && StringsKt.contains$default((CharSequence) message, (CharSequence) REMOTE_SERVICE_EXCEPTION_PATH, false, 2, (Object) null)) || ((message2 != null && StringsKt.contains$default((CharSequence) message2, (CharSequence) PUSH_NOTIFICATION_MESSAGE, false, 2, (Object) null)) || (message2 != null && StringsKt.contains$default((CharSequence) message2, (CharSequence) REMOTE_SERVICE_EXCEPTION_PATH, false, 2, (Object) null)))));
        if (z10) {
            SortedSet<Integer> lastUniquePushIds = this.pushMessagesTransport.getLastUniquePushIds();
            Intrinsics.checkNotNullExpressionValue(lastUniquePushIds, "getLastUniquePushIds(...)");
            MailAppDependencies.analytics(this.context).assertionPushRemoteException(lastUniquePushIds.toString());
            Locator.Companion companion = Locator.INSTANCE;
            LogCollector logCollector = (LogCollector) companion.from(this.context).locate(LogCollector.class);
            AsserterFactory.createAsserter(((AsserterConfigFactory) companion.locate(this.context, AsserterConfigFactory.class)).createAsserterConfiguration("PushAsserterThrowable")).fail("On handle throwable in PushAsserter", exception, Descriptions.compositionOf(CollectionsKt.listOf((Object[]) new Description[]{Descriptions.constant("Push message ids = " + lastUniquePushIds), Descriptions.logs(logCollector)})));
        }
        return z10;
    }
}
