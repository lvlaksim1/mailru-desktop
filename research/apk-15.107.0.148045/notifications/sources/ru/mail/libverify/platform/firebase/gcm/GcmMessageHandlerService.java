package ru.mail.libverify.platform.firebase.gcm;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.libverify.platform.core.IInternalFactory;
import ru.mail.libverify.platform.firebase.FirebaseCoreService;
import ru.mail.libverify.platform.firebase.a;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/libverify/platform/firebase/gcm/GcmMessageHandlerService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "<init>", "()V", "platform-firebase_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class GcmMessageHandlerService extends FirebaseMessagingService {
    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public final void onMessageReceived(@NotNull RemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        String from = message.getFrom();
        Map<String, String> data = message.getData();
        Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
        FirebaseCoreService.INSTANCE.getClass();
        FirebaseCoreService.Companion.a().v("GcmMessageHandlerService", "message received from " + from + " with data " + data);
        IInternalFactory iInternalFactory = a.f87690c;
        if (iInternalFactory == null) {
            iInternalFactory = a.f87691d;
        }
        iInternalFactory.deliverGcmMessageIntent(this, from, data);
    }

    @Override // com.google.firebase.messaging.FirebaseMessagingService
    public final void onNewToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        FirebaseCoreService.INSTANCE.getClass();
        FirebaseCoreService.Companion.a().v("GcmMessageHandlerService", "token refresh. onNewToken: " + token);
        IInternalFactory iInternalFactory = a.f87690c;
        if (iInternalFactory == null) {
            iInternalFactory = a.f87691d;
        }
        iInternalFactory.refreshGcmToken(this);
    }
}
