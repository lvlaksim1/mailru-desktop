package ru.mail.util.push;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.updater.PushUpdater;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public interface PushFactory {
    PushMessagesTransport createTransport(@NonNull Application application, @NonNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NonNull PushMessageReceivedNotifier pushMessageReceivedNotifier);

    PushUpdater createUpdater(@NonNull Context context, @NonNull PushMessagesTransport pushMessagesTransport);
}
