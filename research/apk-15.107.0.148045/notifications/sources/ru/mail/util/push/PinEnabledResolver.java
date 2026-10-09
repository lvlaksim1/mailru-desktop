package ru.mail.util.push;

import android.content.Context;
import android.preference.PreferenceManager;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.logic.content.impl.CommonDataManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/util/push/PinEnabledResolver;", "", "<init>", "()V", "KEY_SHOW_NOTIFICATIONS_CONTENT", "", "haveToCheckPin", "", "shouldShowNotificationContent", "context", "Landroid/content/Context;", "isPinEnabled", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PinEnabledResolver {

    @NotNull
    public static final String KEY_SHOW_NOTIFICATIONS_CONTENT = "show_notifications_content";

    @NotNull
    public static final PinEnabledResolver INSTANCE = new PinEnabledResolver();

    @JvmField
    public static boolean haveToCheckPin = true;
    public static final int $stable = 8;

    private PinEnabledResolver() {
    }

    @JvmStatic
    public static final boolean isPinEnabled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (haveToCheckPin) {
            return CommonDataManager.from(context.getApplicationContext()).getPinStorage().pinEnabled();
        }
        return true;
    }

    @JvmStatic
    public static final boolean shouldShowNotificationContent(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (haveToCheckPin) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_SHOW_NOTIFICATIONS_CONTENT, context.getResources().getBoolean(ru.mail.mails.R.bool.show_notifications_content_default));
        }
        return true;
    }
}
