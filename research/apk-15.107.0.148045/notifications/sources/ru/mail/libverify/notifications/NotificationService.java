package ru.mail.libverify.notifications;

import android.app.Service;
import android.content.Intent;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import ru.mail.verify.core.utils.FileLog;
import ru.mail.verify.core.utils.IntentProcessService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/libverify/notifications/NotificationService;", "Lru/mail/verify/core/utils/IntentProcessService;", "<init>", "()V", "libverify_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class NotificationService extends IntentProcessService {
    @Override // ru.mail.verify.core.utils.IntentProcessService, android.app.IntentService
    protected final void onHandleIntent(@Nullable Intent intent) {
        String stringExtra;
        super.onHandleIntent(intent);
        if ((intent != null ? intent.getAction() : null) == null || intent.getExtras() == null || (stringExtra = intent.getStringExtra("notification_id")) == null) {
            return;
        }
        FileLog.v("NotificationService", "received extra %s from notification %s", intent.getAction(), stringExtra);
        String action = intent.getAction();
        if (action != null) {
            int iHashCode = action.hashCode();
            if (iHashCode != -964594249) {
                if (iHashCode != 1064330403) {
                    ru.mail.libverify.d0.a.a((Service) this, ru.mail.libverify.p0.e.a(ru.mail.libverify.p0.a.SERVICE_NOTIFICATION_CANCEL, stringExtra));
                    return;
                } else {
                    ru.mail.libverify.d0.a.a((Service) this, ru.mail.libverify.p0.e.a(ru.mail.libverify.p0.a.SERVICE_NOTIFICATION_CANCEL, stringExtra));
                    return;
                }
            }
            if (action.equals("action_confirm")) {
                ru.mail.libverify.d0.a.a((Service) this, ru.mail.libverify.p0.e.a(ru.mail.libverify.p0.a.SERVICE_NOTIFICATION_CONFIRM, stringExtra));
                return;
            }
        }
        ru.mail.libverify.n0.b.a("NotificationService", "wrong action type", new IllegalArgumentException("Wrong action type " + intent.getAction() + " for NotificationService detected"));
    }
}
