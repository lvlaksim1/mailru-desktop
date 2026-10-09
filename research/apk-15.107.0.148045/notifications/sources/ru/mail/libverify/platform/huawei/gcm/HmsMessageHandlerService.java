package ru.mail.libverify.platform.huawei.gcm;

import com.huawei.hms.push.HmsMessageService;
import com.huawei.hms.push.RemoteMessage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import ru.mail.libverify.platform.core.IInternalFactory;
import ru.mail.libverify.platform.huawei.HuaweiCoreService;
import ru.mail.libverify.platform.huawei.a;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/libverify/platform/huawei/gcm/HmsMessageHandlerService;", "Lcom/huawei/hms/push/HmsMessageService;", "<init>", "()V", "platform-huawei_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class HmsMessageHandlerService extends HmsMessageService {
    @Override // com.huawei.hms.push.HmsMessageService
    public final void onMessageReceived(RemoteMessage message) {
        Intrinsics.checkNotNullParameter(message, "message");
        String from = message.getFrom();
        Map<String, String> dataOfMap = message.getDataOfMap();
        HuaweiCoreService.INSTANCE.getClass();
        HuaweiCoreService.Companion.a().v("HmsMessageHandlerService", "message received from " + from + " with data " + dataOfMap);
        IInternalFactory iInternalFactory = a.f87717d;
        if (iInternalFactory == null) {
            iInternalFactory = a.f87716c;
        }
        Intrinsics.checkNotNull(dataOfMap);
        iInternalFactory.deliverGcmMessageIntent(this, from, dataOfMap);
    }

    @Override // com.huawei.hms.push.HmsMessageService
    public final void onNewToken(String str) {
        HuaweiCoreService.INSTANCE.getClass();
        HuaweiCoreService.Companion.a().v("HmsMessageHandlerService", "token refresh. onNewToken: " + str);
        IInternalFactory iInternalFactory = a.f87717d;
        if (iInternalFactory == null) {
            iInternalFactory = a.f87716c;
        }
        iInternalFactory.refreshGcmToken(this);
    }
}
