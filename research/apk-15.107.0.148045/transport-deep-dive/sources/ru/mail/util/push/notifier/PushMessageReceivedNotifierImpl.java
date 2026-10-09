package ru.mail.util.push.notifier;

import androidx.annotation.WorkerThread;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.util.push.PushType;
import ru.mail.util.push.vkpns.filter.VkpnsPushDeduplicatorImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0006H\u0016J\u0018\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J=\u0010\u0018\u001a\u00020\u00132\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0017¢\u0006\u0002\u0010\u001fJ=\u0010 \u001a\u00020\u00132\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u0015\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0002\u0010\u001fR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/util/push/notifier/PushMessageReceivedNotifierImpl;", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier;", "pushFilter", "Lru/mail/util/push/vkpns/filter/VkpnsPushDeduplicatorImpl;", "isDeliveryLockNarrowed", "Lkotlin/Function0;", "", "<init>", "(Lru/mail/util/push/vkpns/filter/VkpnsPushDeduplicatorImpl;Lkotlin/jvm/functions/Function0;)V", "listenersReceivingDuplicates", "Ljava/util/concurrent/ConcurrentHashMap;", "Lru/mail/util/push/PushType;", "", "Lru/mail/util/push/notifier/PushMessageReceivedNotifier$Listener;", "listenersNoDuplicates", "listenersLock", "", "deliveryLock", "addListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "pushType", "filterDuplicates", "removeListener", "onMessageReceived", "data", "", "", "from", "pushMeSdkPushId", "", "(Ljava/util/Map;Lru/mail/util/push/PushType;Ljava/lang/String;Ljava/lang/Long;)V", "deliver", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMessageReceivedNotifierImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMessageReceivedNotifierImpl.kt\nru/mail/util/push/notifier/PushMessageReceivedNotifierImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,93:1\n1#2:94\n1869#3,2:95\n1869#3,2:97\n*S KotlinDebug\n*F\n+ 1 PushMessageReceivedNotifierImpl.kt\nru/mail/util/push/notifier/PushMessageReceivedNotifierImpl\n*L\n83#1:95,2\n89#1:97,2\n*E\n"})
public final class PushMessageReceivedNotifierImpl implements PushMessageReceivedNotifier {
    public static final int $stable = 8;

    @NotNull
    private final Object deliveryLock;

    @NotNull
    private final Function0<Boolean> isDeliveryLockNarrowed;

    @NotNull
    private final Object listenersLock;

    @NotNull
    private final ConcurrentHashMap<PushType, Set<PushMessageReceivedNotifier.Listener>> listenersNoDuplicates;

    @NotNull
    private final ConcurrentHashMap<PushType, Set<PushMessageReceivedNotifier.Listener>> listenersReceivingDuplicates;

    @Nullable
    private final VkpnsPushDeduplicatorImpl pushFilter;

    public PushMessageReceivedNotifierImpl(@Nullable VkpnsPushDeduplicatorImpl vkpnsPushDeduplicatorImpl, @NotNull Function0<Boolean> isDeliveryLockNarrowed) {
        Intrinsics.checkNotNullParameter(isDeliveryLockNarrowed, "isDeliveryLockNarrowed");
        this.pushFilter = vkpnsPushDeduplicatorImpl;
        this.isDeliveryLockNarrowed = isDeliveryLockNarrowed;
        this.listenersReceivingDuplicates = new ConcurrentHashMap<>();
        this.listenersNoDuplicates = new ConcurrentHashMap<>();
        this.listenersLock = new Object();
        this.deliveryLock = new Object();
    }

    private final void deliver(Map<String, String> data, PushType pushType, String from, Long pushMeSdkPushId) {
        List list;
        List list2;
        synchronized (this.listenersLock) {
            try {
                Set<PushMessageReceivedNotifier.Listener> set = this.listenersReceivingDuplicates.get(pushType);
                list = set != null ? CollectionsKt.toList(set) : null;
                if (list == null) {
                    list = CollectionsKt.emptyList();
                }
                Set<PushMessageReceivedNotifier.Listener> set2 = this.listenersNoDuplicates.get(pushType);
                list2 = set2 != null ? CollectionsKt.toList(set2) : null;
                if (list2 == null) {
                    list2 = CollectionsKt.emptyList();
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th2) {
                throw th2;
            }
        }
        VkpnsPushDeduplicatorImpl vkpnsPushDeduplicatorImpl = this.pushFilter;
        boolean zIsDuplicate = vkpnsPushDeduplicatorImpl != null ? vkpnsPushDeduplicatorImpl.isDuplicate(data, pushType) : false;
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((PushMessageReceivedNotifier.Listener) it.next()).onMessageReceived(data, from, pushMeSdkPushId);
        }
        if (zIsDuplicate) {
            return;
        }
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            ((PushMessageReceivedNotifier.Listener) it2.next()).onMessageReceived(data, from, pushMeSdkPushId);
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001f A[PHI: r4
      0x001f: PHI (r4v3 java.util.Set<ru.mail.util.push.notifier.PushMessageReceivedNotifier$Listener>) = 
      (r4v2 java.util.Set<ru.mail.util.push.notifier.PushMessageReceivedNotifier$Listener>)
      (r4v5 java.util.Set<ru.mail.util.push.notifier.PushMessageReceivedNotifier$Listener>)
     binds: [B:13:0x002b, B:7:0x001c] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier
    public void addListener(@NotNull PushMessageReceivedNotifier.Listener listener, @NotNull PushType pushType, boolean filterDuplicates) {
        Set<PushMessageReceivedNotifier.Listener> setPutIfAbsent;
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        synchronized (this.listenersLock) {
            try {
                Set<PushMessageReceivedNotifier.Listener> linkedHashSet = new LinkedHashSet<>();
                if (filterDuplicates) {
                    setPutIfAbsent = this.listenersNoDuplicates.putIfAbsent(pushType, linkedHashSet);
                    if (setPutIfAbsent != null) {
                        linkedHashSet = setPutIfAbsent;
                    }
                } else {
                    setPutIfAbsent = this.listenersReceivingDuplicates.putIfAbsent(pushType, linkedHashSet);
                    if (setPutIfAbsent != null) {
                        linkedHashSet = setPutIfAbsent;
                    }
                }
                linkedHashSet.add(listener);
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier
    @WorkerThread
    public void onMessageReceived(@NotNull Map<String, String> data, @NotNull PushType pushType, @Nullable String from, @Nullable Long pushMeSdkPushId) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        if (this.isDeliveryLockNarrowed.invoke().booleanValue()) {
            deliver(data, pushType, from, pushMeSdkPushId);
            return;
        }
        synchronized (this.deliveryLock) {
            deliver(data, pushType, from, pushMeSdkPushId);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier
    public void removeListener(@NotNull PushMessageReceivedNotifier.Listener listener, @NotNull PushType pushType) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Intrinsics.checkNotNullParameter(pushType, "pushType");
        synchronized (this.listenersLock) {
            try {
                Set<PushMessageReceivedNotifier.Listener> set = this.listenersNoDuplicates.get(pushType);
                if (set != null) {
                    set.remove(listener);
                }
                Set<PushMessageReceivedNotifier.Listener> set2 = this.listenersReceivingDuplicates.get(pushType);
                if (set2 != null) {
                    set2.remove(listener);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
