package ru.mail.data.cmd.server.calls;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.calleridentification.CallsAuthProvider;
import ru.mail.calleridentification.CallsAuthStrategy;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsBaseAuthStrategy;", "Lru/mail/calleridentification/CallsAuthStrategy;", "<init>", "()V", "getRequestAuthHeaders", "", "", "authToken", "getAuthStore", "Lru/mail/data/cmd/server/calls/CallsBaseAuthStrategy$EmptyAuthStore;", "EmptyAuthStore", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallsBaseAuthStrategy implements CallsAuthStrategy {
    public static final int $stable = 0;

    @NotNull
    public static final CallsBaseAuthStrategy INSTANCE = new CallsBaseAuthStrategy();

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsBaseAuthStrategy$EmptyAuthStore;", "Lru/mail/calleridentification/CallsAuthProvider$AuthStore;", "<init>", "()V", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class EmptyAuthStore implements CallsAuthProvider.AuthStore {
        public static final int $stable = 0;

        @NotNull
        public static final EmptyAuthStore INSTANCE = new EmptyAuthStore();

        private EmptyAuthStore() {
        }
    }

    private CallsBaseAuthStrategy() {
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    public /* bridge */ void discardAuthorization() {
        super.discardAuthorization();
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    @NotNull
    public Map<String, String> getRequestAuthHeaders(@Nullable String authToken) {
        if (authToken == null) {
            return MapsKt.emptyMap();
        }
        return MapsKt.hashMapOf(new Pair("Authorization", "Bearer " + authToken));
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    public /* bridge */ void initialize() {
        super.initialize();
    }

    @Override // ru.mail.calleridentification.CallsAuthStrategy
    @NotNull
    public EmptyAuthStore getAuthStore() {
        return EmptyAuthStore.INSTANCE;
    }
}
