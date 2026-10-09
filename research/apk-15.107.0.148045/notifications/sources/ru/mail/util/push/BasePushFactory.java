package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.token.PushTokenManager;
import ru.mail.util.push.token.PushTokenManagerImpl;
import ru.mail.util.push.token.storage.SharedPreferencesTokenStorage;
import ru.mail.util.push.updater.DefaultPushUpdater;
import ru.mail.util.push.updater.PushUpdater;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b'\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH$J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bR\u0012\u0010\u0004\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lru/mail/util/push/BasePushFactory;", "Lru/mail/util/push/PushFactory;", "<init>", "()V", "storageName", "", "getStorageName", "()Ljava/lang/String;", "createAvailabilityChecker", "Lru/mail/util/push/AvailabilityChecker;", "context", "Landroid/content/Context;", "createUpdater", "Lru/mail/util/push/updater/PushUpdater;", "transport", "Lru/mail/util/push/PushMessagesTransport;", "createPushTokenManager", "Lru/mail/util/push/token/PushTokenManager;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BasePushFactory implements PushFactory {
    public static final int $stable = 0;

    @NotNull
    public static final String KEY_AVAILABLE = "available";

    @NotNull
    protected abstract AvailabilityChecker createAvailabilityChecker(@NotNull Context context);

    @NotNull
    public final PushTokenManager createPushTokenManager(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new PushTokenManagerImpl(new SharedPreferencesTokenStorage(context, getStorageName()));
    }

    @Override // ru.mail.util.push.PushFactory
    @NotNull
    public PushUpdater createUpdater(@NotNull Context context, @NotNull PushMessagesTransport transport) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(transport, "transport");
        return new DefaultPushUpdater(context, transport, new SingleFlagHistory(context, KEY_AVAILABLE));
    }

    @NotNull
    protected abstract String getStorageName();
}
