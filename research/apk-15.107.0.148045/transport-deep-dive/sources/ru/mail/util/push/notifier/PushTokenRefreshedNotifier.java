package ru.mail.util.push.notifier;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.PushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier$Listener;", "pushType", "Lru/mail/util/push/PushType;", "removeListener", "onNewToken", "token", "", "Listener", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PushTokenRefreshedNotifier {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/util/push/notifier/PushTokenRefreshedNotifier$Listener;", "", "onNewToken", "", "token", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Listener {
        void onNewToken(@NotNull String token);
    }

    void addListener(@NotNull Listener listener, @NotNull PushType pushType);

    void onNewToken(@NotNull String token, @NotNull PushType pushType);

    void removeListener(@NotNull Listener listener, @NotNull PushType pushType);
}
