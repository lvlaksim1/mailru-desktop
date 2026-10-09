package ru.mail.util.push.notifier;

import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.PushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\u0013J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J=\u0010\u000b\u001a\u00020\u00032\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H'¢\u0006\u0002\u0010\u0012¨\u0006\u0014À\u0006\u0003"}, d2 = {"Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/util/push/notifier/PushMessageReceivedNotifier$Listener;", "pushType", "Lru/mail/util/push/PushType;", "filterDuplicates", "", "removeListener", "onMessageReceived", "data", "", "", "from", "pushMeSdkPushId", "", "(Ljava/util/Map;Lru/mail/util/push/PushType;Ljava/lang/String;Ljava/lang/Long;)V", "Listener", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushMessageReceivedNotifier {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH&¢\u0006\u0002\u0010\n¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/notifier/PushMessageReceivedNotifier$Listener;", "", "onMessageReceived", "", "data", "", "", "from", "pushMeSdkPushId", "", "(Ljava/util/Map;Ljava/lang/String;Ljava/lang/Long;)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listener {
        void onMessageReceived(@NotNull Map<String, String> data, @Nullable String from, @Nullable Long pushMeSdkPushId);
    }

    void addListener(@NotNull Listener listener, @NotNull PushType pushType, boolean filterDuplicates);

    @WorkerThread
    void onMessageReceived(@NotNull Map<String, String> data, @NotNull PushType pushType, @Nullable String from, @Nullable Long pushMeSdkPushId);

    void removeListener(@NotNull Listener listener, @NotNull PushType pushType);
}
