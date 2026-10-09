package ru.mail.util.push.huawei;

import android.content.Context;
import androidx.annotation.WorkerThread;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.model.Transport;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.MailApplication;
import ru.mail.util.push.PushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0007¨\u0006\u0006"}, d2 = {"processHmsPush", "", "context", "Landroid/content/Context;", "params", "Lru/mail/util/push/huawei/ProcessHmsPushCommand$Params;", "mail-app_mail_ruRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class ProcessHmsPushKt {
    @WorkerThread
    public static final void processHmsPush(@NotNull Context context, @NotNull ProcessHmsPushCommand.Params params) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Map<String, String> dataOfMap = params.getRemoteMessage().getDataOfMap();
        Intrinsics.checkNotNullExpressionValue(dataOfMap, "getDataOfMap(...)");
        ((MailApplication) context).getPushComponent().getPushMessageReceivedNotifier().onMessageReceived(dataOfMap, PushType.HMS, params.getRemoteMessage().getFrom(), PushMeSdk.INSTANCE.onMessageReceived(dataOfMap, Transport.HUAWEI));
    }
}
