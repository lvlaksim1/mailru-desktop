package ru.mail.libverify.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@RequiresApi(28)
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/libverify/notifications/ChangePushPermissionsReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "libverify_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ChangePushPermissionsReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (context == null || intent == null || intent.getExtras() == null || !ArraysKt.contains(new String[]{"android.app.action.APP_BLOCK_STATE_CHANGED", "android.app.action.NOTIFICATION_CHANNEL_BLOCK_STATE_CHANGED"}, intent.getAction())) {
            return;
        }
        Intent intent2 = new Intent("SERVICE_SETTINGS_CHECK");
        intent2.putExtra("settings_action_type", "NOTIFICATION_SETTINGS_CHANGE");
        ru.mail.verify.core.utils.d.a(context, intent2);
    }
}
