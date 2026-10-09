package ru.mail.util.push.pusher;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.mail.util.push.PusherApplicationType;
import ru.mail.util.push.apps.PusherApplication;
import ru.mail.util.push.apps.state.State;
import ru.mail.util.push.model.MultiAccountSettings;
import ru.mail.util.push.provider.PushInfoProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0001 J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J,\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H&J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH'J\b\u0010\u0017\u001a\u00020\u0011H&J\u0010\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001aH&J\n\u0010\u001b\u001a\u0004\u0018\u00010\u001aH&J\n\u0010\u001c\u001a\u0004\u0018\u00010\u001aH&J\b\u0010\u001d\u001a\u00020\u0011H&J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0007H&¨\u0006!À\u0006\u0003"}, d2 = {"Lru/mail/util/push/pusher/PusherTransport;", "", "registerForPushes", "Lru/mail/util/push/pusher/PusherTransport$Result;", "settings", "Lru/mail/util/push/model/MultiAccountSettings;", "userIdentifier", "", "registerPortalAppsForPushes", "accounts", "", "apps", "Lru/mail/util/push/apps/PusherApplication;", "invalidateExpiredToken", "provider", "Lru/mail/util/push/provider/PushInfoProvider;", "unsubscribeAllByDeviceId", "", "unsubscribeAppByDeviceId", "application", "Lru/mail/util/push/PusherApplicationType;", "checkIsTokenExists", "", "syncPortalAppsIfNeeded", "saveSyncedPortalAppsState", "state", "Lru/mail/util/push/apps/state/State;", "getLastSyncedPortalAppsState", "getLastLocalPortalAppsState", "clearSavedPortalAppsState", "unsubscribeByToken", "token", "Result", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PusherTransport {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/util/push/pusher/PusherTransport$Result;", "", "<init>", "()V", "OK", "NoAuth", "NoAuthMultiple", GoogleErrorDescriptions.UNKNOWN_ERROR, "Lru/mail/util/push/pusher/PusherTransport$Result$NoAuth;", "Lru/mail/util/push/pusher/PusherTransport$Result$NoAuthMultiple;", "Lru/mail/util/push/pusher/PusherTransport$Result$OK;", "Lru/mail/util/push/pusher/PusherTransport$Result$UnknownError;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/pusher/PusherTransport$Result$NoAuth;", "Lru/mail/util/push/pusher/PusherTransport$Result;", "userIdentifier", "", "<init>", "(Ljava/lang/String;)V", "getUserIdentifier", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NoAuth extends Result {
            public static final int $stable = 0;

            @NotNull
            private final String userIdentifier;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NoAuth(@NotNull String userIdentifier) {
                super(null);
                Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
                this.userIdentifier = userIdentifier;
            }

            public static /* synthetic */ NoAuth copy$default(NoAuth noAuth, String str, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = noAuth.userIdentifier;
                }
                return noAuth.copy(str);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getUserIdentifier() {
                return this.userIdentifier;
            }

            @NotNull
            public final NoAuth copy(@NotNull String userIdentifier) {
                Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
                return new NoAuth(userIdentifier);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NoAuth) && Intrinsics.areEqual(this.userIdentifier, ((NoAuth) other).userIdentifier);
            }

            @NotNull
            public final String getUserIdentifier() {
                return this.userIdentifier;
            }

            public int hashCode() {
                return this.userIdentifier.hashCode();
            }

            @NotNull
            public String toString() {
                return "NoAuth(userIdentifier=" + this.userIdentifier + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lru/mail/util/push/pusher/PusherTransport$Result$NoAuthMultiple;", "Lru/mail/util/push/pusher/PusherTransport$Result;", "userIdentifiers", "", "", "<init>", "(Ljava/util/Collection;)V", "getUserIdentifiers", "()Ljava/util/Collection;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class NoAuthMultiple extends Result {
            public static final int $stable = 8;

            @NotNull
            private final Collection<String> userIdentifiers;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public NoAuthMultiple(@NotNull Collection<String> userIdentifiers) {
                super(null);
                Intrinsics.checkNotNullParameter(userIdentifiers, "userIdentifiers");
                this.userIdentifiers = userIdentifiers;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ NoAuthMultiple copy$default(NoAuthMultiple noAuthMultiple, Collection collection, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    collection = noAuthMultiple.userIdentifiers;
                }
                return noAuthMultiple.copy(collection);
            }

            @NotNull
            public final Collection<String> component1() {
                return this.userIdentifiers;
            }

            @NotNull
            public final NoAuthMultiple copy(@NotNull Collection<String> userIdentifiers) {
                Intrinsics.checkNotNullParameter(userIdentifiers, "userIdentifiers");
                return new NoAuthMultiple(userIdentifiers);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof NoAuthMultiple) && Intrinsics.areEqual(this.userIdentifiers, ((NoAuthMultiple) other).userIdentifiers);
            }

            @NotNull
            public final Collection<String> getUserIdentifiers() {
                return this.userIdentifiers;
            }

            public int hashCode() {
                return this.userIdentifiers.hashCode();
            }

            @NotNull
            public String toString() {
                return "NoAuthMultiple(userIdentifiers=" + this.userIdentifiers + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/util/push/pusher/PusherTransport$Result$OK;", "Lru/mail/util/push/pusher/PusherTransport$Result;", "<init>", "()V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class OK extends Result {
            public static final int $stable = 0;

            @NotNull
            public static final OK INSTANCE = new OK();

            private OK() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/util/push/pusher/PusherTransport$Result$UnknownError;", "Lru/mail/util/push/pusher/PusherTransport$Result;", "<init>", "()V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class UnknownError extends Result {
            public static final int $stable = 0;

            @NotNull
            public static final UnknownError INSTANCE = new UnknownError();

            private UnknownError() {
                super(null);
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    @Deprecated(message = "Can be removed after PushMe SDK integration")
    boolean checkIsTokenExists(@NotNull String userIdentifier, @NotNull PushInfoProvider provider);

    void clearSavedPortalAppsState();

    @Nullable
    State getLastLocalPortalAppsState();

    @Nullable
    State getLastSyncedPortalAppsState();

    @NotNull
    Result invalidateExpiredToken(@NotNull PushInfoProvider provider);

    @NotNull
    Result registerForPushes(@NotNull MultiAccountSettings settings, @NotNull String userIdentifier);

    @NotNull
    Result registerPortalAppsForPushes(@NotNull Collection<String> accounts, @NotNull Collection<PusherApplication> apps, @NotNull String userIdentifier);

    void saveSyncedPortalAppsState(@NotNull State state);

    void syncPortalAppsIfNeeded();

    void unsubscribeAllByDeviceId(@NotNull String userIdentifier);

    void unsubscribeAppByDeviceId(@NotNull String userIdentifier, @NotNull PusherApplicationType application);

    void unsubscribeByToken(@NotNull String token);
}
