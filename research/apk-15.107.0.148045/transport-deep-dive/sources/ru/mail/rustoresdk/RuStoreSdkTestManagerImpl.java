package ru.mail.rustoresdk;

import com.vk.push.pushsdk.utils.JsonMessageParser;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.rustore.sdk.pushclient.RuStorePushClient;
import ru.rustore.sdk.pushclient.messaging.model.TestNotificationPayload;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016¨\u0006\t"}, d2 = {"Lru/mail/rustoresdk/RuStoreSdkTestManagerImpl;", "Lru/mail/rustoresdk/RuStoreSdkTestManager;", "<init>", "()V", "sendPush", "", JsonMessageParser.Keys.PAYLOAD_JSON_KEY, "", "", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStoreSdkTestManagerImpl implements RuStoreSdkTestManager {
    @Override // ru.mail.rustoresdk.RuStoreSdkTestManager
    public void sendPush(@NotNull Map<String, String> payload) {
        Intrinsics.checkNotNullParameter(payload, "payload");
        RuStorePushClient.f102575a.sendTestNotification(new TestNotificationPayload(null, null, null, payload, 7, null));
    }
}
