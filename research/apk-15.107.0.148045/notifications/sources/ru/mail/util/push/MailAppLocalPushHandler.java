package ru.mail.util.push;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.log.Log;
import ru.mail.util.push.component.PushComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/util/push/MailAppLocalPushHandler;", "Lru/mail/util/push/LocalPushHandler;", "pushComponent", "Lru/mail/util/push/component/PushComponent;", "<init>", "(Lru/mail/util/push/component/PushComponent;)V", "logger", "Lru/mail/util/log/Log;", "handleLocalPush", "", "pushes", "", "Lru/mail/util/push/PushMessage;", "getPrimaryPushTransport", "Lru/mail/util/push/PushMessagesTransport;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailAppLocalPushHandler implements LocalPushHandler {
    public static final int $stable = 8;

    @NotNull
    private final Log logger;

    @NotNull
    private final PushComponent pushComponent;

    public MailAppLocalPushHandler(@NotNull PushComponent pushComponent) {
        Intrinsics.checkNotNullParameter(pushComponent, "pushComponent");
        this.pushComponent = pushComponent;
        this.logger = Log.INSTANCE.getLog("MailAppLocalPushHandler");
    }

    private final PushMessagesTransport getPrimaryPushTransport() {
        Collection<PushMessagesTransport> pushMessagesTransports = this.pushComponent.getPushMessagesTransports();
        for (PushMessagesTransport pushMessagesTransport : pushMessagesTransports) {
            if (pushMessagesTransport.getPushType() != PushType.VKPNS) {
                return pushMessagesTransport;
            }
        }
        throw new IllegalStateException("Could not find primary push transport, total size: " + pushMessagesTransports.size());
    }

    @Override // ru.mail.util.push.LocalPushHandler
    public void handleLocalPush(@NotNull List<? extends PushMessage> pushes) {
        Intrinsics.checkNotNullParameter(pushes, "pushes");
        this.logger.i("Handle local pushes: " + pushes.size());
        getPrimaryPushTransport().handlePushMessagesReceiveEvent(pushes);
    }
}
