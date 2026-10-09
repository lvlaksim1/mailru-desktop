package ru.mail.util.push;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.IntentActionsProvider;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.PendingIntentCreator;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.android_utils.SdkUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u001a\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0003R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lru/mail/util/push/AlarmPendingIntentFactory;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "getPendingIntent", "Landroid/app/PendingIntent;", "getCheckPendingIntent", "pendingIntentType", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AlarmPendingIntentFactory {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    public AlarmPendingIntentFactory(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @SuppressLint({"WrongConstant"})
    private final PendingIntent getCheckPendingIntent(Context context, int pendingIntentType) {
        try {
            Intent intent = new Intent(IntentActionsProvider.actionPushTokenCheck);
            if (SdkUtils.hasUpsideDownCake()) {
                intent.setPackage(context.getPackageName());
            }
            return PendingIntentCreator.getBroadcast(context, 0, intent, pendingIntentType);
        } catch (SecurityException unused) {
            MailAppDependencies.analytics(context).onAlarmPendingIntentCreation();
            return null;
        }
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Nullable
    public final PendingIntent getPendingIntent() {
        return getCheckPendingIntent(this.context, PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null));
    }
}
