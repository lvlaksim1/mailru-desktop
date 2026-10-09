package ru.mail.util.push.huawei;

import android.app.Application;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.common.PackageConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.util.push.BasePushFactory;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0014"}, d2 = {"Lru/mail/util/push/huawei/HmsPushFactory;", "Lru/mail/util/push/BasePushFactory;", "<init>", "()V", "storageName", "", "getStorageName", "()Ljava/lang/String;", "createTransport", "Lru/mail/util/push/PushMessagesTransport;", "application", "Landroid/app/Application;", "pushTokenRefreshedNotifier", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "pushMessageReceivedNotifier", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "createAvailabilityChecker", "Lru/mail/util/push/AvailabilityChecker;", "context", "Landroid/content/Context;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HmsPushFactory extends BasePushFactory {
    public static final int $stable = 0;

    @NotNull
    private final String storageName = PackageConstants.SERVICES_PACKAGE_ALL_SCENE;

    @Override // ru.mail.util.push.BasePushFactory
    @NotNull
    protected AvailabilityChecker createAvailabilityChecker(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new HMSAvailabilityChecker(context);
    }

    @Override // ru.mail.util.push.PushFactory
    @NotNull
    public PushMessagesTransport createTransport(@NotNull Application application, @NotNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NotNull PushMessageReceivedNotifier pushMessageReceivedNotifier) {
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(pushTokenRefreshedNotifier, "pushTokenRefreshedNotifier");
        Intrinsics.checkNotNullParameter(pushMessageReceivedNotifier, "pushMessageReceivedNotifier");
        Context applicationContext = application.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext);
        return new HmsPushTransport(applicationContext, pushTokenRefreshedNotifier, pushMessageReceivedNotifier, createPushTokenManager(applicationContext), createAvailabilityChecker(applicationContext));
    }

    @Override // ru.mail.util.push.BasePushFactory
    @NotNull
    protected String getStorageName() {
        return this.storageName;
    }
}
