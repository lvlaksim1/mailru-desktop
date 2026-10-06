package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0002\u0018\u0019B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0006J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0006J,\u0010\u0012\u001a\u00020\u000e2\u000e\u0010\u0013\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017RN\u0010\u0004\u001aB\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b \u0007* \u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\b0\b\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/RequestListenerManager;", "", "<init>", "()V", "listeners", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "Lru/mail/data/cmd/server/RequestListenerManager$RequestListener;", "kotlin.jvm.PlatformType", "", "responses", "Ljava/util/concurrent/ConcurrentHashMap;", "Lru/mail/network/NetworkCommand;", "Lru/mail/data/cmd/server/RequestListenerManager$ResponseStatus;", "addListener", "", "isColdObserver", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "removeListener", "pushResponse", Event.Companion.Network.Fail.REQUEST_TAG, "response", "Lru/mail/mailbox/cmd/CommandStatus;", "accountLogin", "", "RequestListener", "ResponseStatus", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRequestListenerManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestListenerManager.kt\nru/mail/data/cmd/server/RequestListenerManager\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,35:1\n216#2,2:36\n1869#3,2:38\n*S KotlinDebug\n*F\n+ 1 RequestListenerManager.kt\nru/mail/data/cmd/server/RequestListenerManager\n*L\n15#1:36,2\n27#1:38,2\n*E\n"})
public final class RequestListenerManager {
    public static final int $stable = 8;
    private final ConcurrentHashMap.KeySetView<RequestListener, Boolean> listeners = ConcurrentHashMap.newKeySet();

    @NotNull
    private final ConcurrentHashMap<NetworkCommand<?, ?>, ResponseStatus> responses = new ConcurrentHashMap<>();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/RequestListenerManager$RequestListener;", "", "onRequestExecuted", "", Event.Companion.Network.Fail.REQUEST_TAG, "Lru/mail/network/NetworkCommand;", "response", "Lru/mail/data/cmd/server/RequestListenerManager$ResponseStatus;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface RequestListener {
        void onRequestExecuted(@NotNull NetworkCommand<?, ?> request, @NotNull ResponseStatus response);
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lru/mail/data/cmd/server/RequestListenerManager$ResponseStatus;", "", "accountLogin", "", "commandStatus", "Lru/mail/mailbox/cmd/CommandStatus;", "<init>", "(Ljava/lang/String;Lru/mail/mailbox/cmd/CommandStatus;)V", "getAccountLogin", "()Ljava/lang/String;", "getCommandStatus", "()Lru/mail/mailbox/cmd/CommandStatus;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class ResponseStatus {
        public static final int $stable = 8;

        @Nullable
        private final String accountLogin;

        @NotNull
        private final CommandStatus<?> commandStatus;

        public ResponseStatus(@Nullable String str, @NotNull CommandStatus<?> commandStatus) {
            Intrinsics.checkNotNullParameter(commandStatus, "commandStatus");
            this.accountLogin = str;
            this.commandStatus = commandStatus;
        }

        @Nullable
        public final String getAccountLogin() {
            return this.accountLogin;
        }

        @NotNull
        public final CommandStatus<?> getCommandStatus() {
            return this.commandStatus;
        }
    }

    public static /* synthetic */ void addListener$default(RequestListenerManager requestListenerManager, boolean z10, RequestListener requestListener, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = false;
        }
        requestListenerManager.addListener(z10, requestListener);
    }

    public final void addListener(boolean isColdObserver, @NotNull RequestListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
        if (isColdObserver) {
            for (Map.Entry<NetworkCommand<?, ?>, ResponseStatus> entry : this.responses.entrySet()) {
                listener.onRequestExecuted(entry.getKey(), entry.getValue());
            }
        }
    }

    public final void pushResponse(@NotNull NetworkCommand<?, ?> request, @NotNull CommandStatus<?> response, @Nullable String accountLogin) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(response, "response");
        ResponseStatus responseStatus = new ResponseStatus(accountLogin, response);
        this.responses.put(request, responseStatus);
        ConcurrentHashMap.KeySetView<RequestListener, Boolean> listeners = this.listeners;
        Intrinsics.checkNotNullExpressionValue(listeners, "listeners");
        Iterator<T> it = listeners.iterator();
        while (it.hasNext()) {
            ((RequestListener) it.next()).onRequestExecuted(request, responseStatus);
        }
    }

    public final void removeListener(@NotNull RequestListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.remove(listener);
    }
}
