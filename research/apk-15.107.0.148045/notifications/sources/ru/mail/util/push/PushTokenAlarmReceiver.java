package ru.mail.util.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import kotlin.Deprecated;
import ru.mail.locator.Locator;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Deprecated(message = "Can be removed after PushMe SDK integration")
public class PushTokenAlarmReceiver extends BroadcastReceiver {
    public static void cancelChecking(Context context) {
        PendingIntent pendingIntent = new AlarmPendingIntentFactory(context).getPendingIntent();
        if (pendingIntent != null) {
            getAlarmManager(context).cancel(pendingIntent);
        }
    }

    private static AlarmManager getAlarmManager(Context context) {
        return (AlarmManager) context.getSystemService("alarm");
    }

    public static void startChecking(Context context) {
        long pushTokenCheckingPeriod = BaseSettingsActivity.getPushTokenCheckingPeriod(context);
        PendingIntent pendingIntent = new AlarmPendingIntentFactory(context).getPendingIntent();
        if (pendingIntent != null) {
            getAlarmManager(context).setInexactRepeating(3, SystemClock.elapsedRealtime() + pushTokenCheckingPeriod, pushTokenCheckingPeriod, pendingIntent);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            ((WorkScheduler) Locator.locate(context, WorkScheduler.class)).schedule(new WorkRequest.Builder(PushTokenCheckWorker.class, PushTokenCheckWorker.uniqueId).constraints(WorkRequest.Constraints.NETWORK).getRequest());
        }
    }
}
