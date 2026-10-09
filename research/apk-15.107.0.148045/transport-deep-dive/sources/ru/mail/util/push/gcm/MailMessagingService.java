package ru.mail.util.push.gcm;

import android.os.SystemClock;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.model.Transport;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import ru.mail.MailApplication;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushTokenUpdater;
import ru.mail.util.push.PushType;
import ru.mail.util.push.component.PushComponent;
import ru.mail.utils.FirebaseAppInitializer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailMessagingService extends FirebaseMessagingService {
    private static final Log LOG = Log.getLog("MailMessagingService");

    private void logPushMessage(RemoteMessage remoteMessage, String str) {
        LOG.d(str + " push: original priority=" + remoteMessage.getOriginalPriority() + ", priority=" + remoteMessage.getPriority() + ", sentTime=" + remoteMessage.getSentTime() + ", ttl=" + remoteMessage.getTtl());
    }

    @Override // android.app.Service
    public void onCreate() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Log log = LOG;
        log.d("ProcessWake onCreate start MailMessagingService (gcm)");
        super.onCreate();
        FirebaseAppInitializer.ensureInitialized(getApplicationContext());
        log.d("ProcessWake onCreate done MailMessagingService (gcm) in " + (SystemClock.uptimeMillis() - jUptimeMillis) + " ms");
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> data = remoteMessage.getData();
        logPushMessage(remoteMessage, data.get("event"));
        ((MailApplication) getApplicationContext()).getPushComponent().getPushMessageReceivedNotifier().onMessageReceived(data, PushType.GCM, remoteMessage.getFrom(), PushMeSdk.INSTANCE.onMessageReceived(data, Transport.FIREBASE));
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(@NotNull String str) {
        super.onNewToken(str);
        Log log = LOG;
        log.i("Firebase instance ID is: " + str);
        if (!BaseSettingsActivity.isUseDevPushes(getApplicationContext())) {
            PushComponent pushComponent = ((MailApplication) getApplicationContext()).getPushComponent();
            PushMeSdk.INSTANCE.onNewToken(str, Transport.FIREBASE);
            pushComponent.getPushTokenRefreshedNotifier().onNewToken(str, PushType.GCM);
        } else {
            log.i("Copy push token to sdk: " + str);
            new PushTokenUpdater().copyPushTokenToSdk(getApplicationContext());
        }
    }
}
