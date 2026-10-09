package ru.mail.util.push.notifier;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.PushType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0018\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\u0006H\u0016R \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/notifier/PushTokenRefreshedNotifierImpl;", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier;", "<init>", "()V", "listeners", "Ljava/util/concurrent/ConcurrentHashMap;", "Lru/mail/util/push/PushType;", "", "Lru/mail/util/push/notifier/PushTokenRefreshedNotifier$Listener;", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "pushType", "removeListener", "onNewToken", "token", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushTokenRefreshedNotifierImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushTokenRefreshedNotifierImpl.kt\nru/mail/util/push/notifier/PushTokenRefreshedNotifierImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,24:1\n1869#2,2:25\n*S KotlinDebug\n*F\n+ 1 PushTokenRefreshedNotifierImpl.kt\nru/mail/util/push/notifier/PushTokenRefreshedNotifierImpl\n*L\n22#1:25,2\n*E\n"})
public final class PushTokenRefreshedNotifierImpl implements PushTokenRefreshedNotifier {
    public static final int $stable = 8;

    @NotNull
    private final ConcurrentHashMap<PushType, Set<PushTokenRefreshedNotifier.Listener>> listeners = new ConcurrentHashMap<>();

    @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier
    public void addListener(@NotNull PushTokenRefreshedNotifier.Listener listener, @NotNull PushType pushType) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        Set<PushTokenRefreshedNotifier.Listener> linkedHashSet = new LinkedHashSet<>();
        Set<PushTokenRefreshedNotifier.Listener> setPutIfAbsent = this.listeners.putIfAbsent(pushType, linkedHashSet);
        if (setPutIfAbsent != null) {
            linkedHashSet = setPutIfAbsent;
        }
        linkedHashSet.add(listener);
    }

    @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier
    public void onNewToken(@NotNull String token, @NotNull PushType pushType) {
        Intrinsics.checkNotNullParameter(token, "token");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        Set<PushTokenRefreshedNotifier.Listener> set = this.listeners.get(pushType);
        if (set != null) {
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                ((PushTokenRefreshedNotifier.Listener) it.next()).onNewToken(token);
            }
        }
    }

    @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier
    public void removeListener(@NotNull PushTokenRefreshedNotifier.Listener listener, @NotNull PushType pushType) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        Set<PushTokenRefreshedNotifier.Listener> set = this.listeners.get(pushType);
        if (set != null) {
            set.remove(listener);
        }
    }
}
