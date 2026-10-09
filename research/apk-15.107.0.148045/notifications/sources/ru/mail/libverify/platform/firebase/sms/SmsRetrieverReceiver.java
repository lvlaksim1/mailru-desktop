package ru.mail.libverify.platform.firebase.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.platform.core.ISmsRetrieverService;
import ru.mail.libverify.platform.firebase.FirebaseCoreService;
import ru.mail.libverify.platform.firebase.a;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/libverify/platform/firebase/sms/SmsRetrieverReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "platform-firebase_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SmsRetrieverReceiver extends BroadcastReceiver {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f87708a = 0;

    @Override // android.content.BroadcastReceiver
    public final void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (context == null || intent == null || !Intrinsics.areEqual(SmsRetriever.SMS_RETRIEVED_ACTION, intent.getAction())) {
            return;
        }
        FirebaseCoreService.INSTANCE.getClass();
        FirebaseCoreService.Companion.a().v("SmsRetrieverReceiver", "sms retrieved action received");
        ISmsRetrieverService iSmsRetrieverService = a.f87692e;
        if (iSmsRetrieverService == null) {
            iSmsRetrieverService = a.f87693f;
        }
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        iSmsRetrieverService.enqueueWork(applicationContext, intent);
    }
}
