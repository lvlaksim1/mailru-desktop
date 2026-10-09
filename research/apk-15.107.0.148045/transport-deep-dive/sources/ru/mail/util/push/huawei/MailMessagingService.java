package ru.mail.util.push.huawei;

import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import com.huawei.hms.push.RemoteMessage;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.model.Transport;
import dagger.hilt.android.AndroidEntryPoint;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;
import ru.mail.MailApplication;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@AndroidEntryPoint
public class MailMessagingService extends Hilt_MailMessagingService {
    private static final Log LOG = Log.getLog("MailMessagingService");

    @Inject
    RequestArbiter requestArbiter;

    private Boolean inMainThread() {
        return Boolean.valueOf(Thread.currentThread().equals(Looper.getMainLooper().getThread()));
    }

    private void logPushMessage(RemoteMessage remoteMessage, String str) {
        LOG.d(str + " push: sentTime=" + remoteMessage.getSentTime() + ", ttl=" + remoteMessage.getTtl());
    }

    @Override // com.huawei.hms.push.HmsMessageService, android.app.Service
    public IBinder onBind(Intent intent) {
        LOG.d("ProcessWake onBind MailMessagingService (hms)");
        return super.onBind(intent);
    }

    @Override // ru.mail.util.push.huawei.Hilt_MailMessagingService, android.app.Service
    public void onCreate() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        Log log = LOG;
        log.d("ProcessWake onCreate start MailMessagingService (hms)");
        super.onCreate();
        log.d("ProcessWake onCreate done MailMessagingService (hms) in " + (SystemClock.uptimeMillis() - jUptimeMillis) + " ms");
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onMessageReceived(@NotNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        ProcessHmsPushCommand.Params params = new ProcessHmsPushCommand.Params(remoteMessage);
        logPushMessage(remoteMessage, remoteMessage.getDataOfMap().get("event"));
        if (inMainThread().booleanValue()) {
            new ProcessHmsPushCommand(getApplicationContext(), params).execute(this.requestArbiter);
        } else {
            ProcessHmsPushKt.processHmsPush(getApplicationContext(), params);
        }
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public void onNewToken(@NotNull String str) {
        super.onNewToken(str);
        PushMeSdk.INSTANCE.onNewToken(str, Transport.HUAWEI);
        ((MailApplication) getApplicationContext()).getPushComponent().getPushTokenRefreshedNotifier().onNewToken(str, PushType.HMS);
    }
}
