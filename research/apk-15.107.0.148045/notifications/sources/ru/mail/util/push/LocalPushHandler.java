package ru.mail.util.push;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/util/push/LocalPushHandler;", "", "handleLocalPush", "", "pushes", "", "Lru/mail/util/push/PushMessage;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LocalPushHandler {
    void handleLocalPush(@NotNull List<? extends PushMessage> pushes);
}
