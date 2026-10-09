package ru.mail.util.push.gcm;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.AvailabilityChecker;
import ru.mail.util.push.BasePushFactory;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class GcmPushFactory extends BasePushFactory {
    private static final String STORAGE_NAME = "com.google.android.gcm";

    @Override // ru.mail.util.push.BasePushFactory
    @NotNull
    public AvailabilityChecker createAvailabilityChecker(@NonNull Context context) {
        return new GCMAvailabilityChecker(context);
    }

    @Override // ru.mail.util.push.PushFactory
    public PushMessagesTransport createTransport(@NonNull Application application, @NonNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NonNull PushMessageReceivedNotifier pushMessageReceivedNotifier) {
        Context applicationContext = application.getApplicationContext();
        return new GcmPushTransport(applicationContext, pushTokenRefreshedNotifier, pushMessageReceivedNotifier, createPushTokenManager(applicationContext), createAvailabilityChecker(applicationContext));
    }

    @Override // ru.mail.util.push.BasePushFactory
    @NotNull
    public String getStorageName() {
        return STORAGE_NAME;
    }
}
