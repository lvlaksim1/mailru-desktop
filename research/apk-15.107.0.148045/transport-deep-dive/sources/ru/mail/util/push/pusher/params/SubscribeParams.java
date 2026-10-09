package ru.mail.util.push.pusher.params;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.util.push.PusherApplicationType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lru/mail/util/push/pusher/params/SubscribeParams;", "", "userIdentifiers", "", "", "application", "Lru/mail/util/push/PusherApplicationType;", "enabledTags", "", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "<init>", "(Ljava/util/Collection;Lru/mail/util/push/PusherApplicationType;Ljava/util/Set;)V", "getUserIdentifiers", "()Ljava/util/Collection;", "getApplication", "()Lru/mail/util/push/PusherApplicationType;", "getEnabledTags", "()Ljava/util/Set;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SubscribeParams {
    public static final int $stable = 8;

    @NotNull
    private final PusherApplicationType application;

    @NotNull
    private final Set<Tag> enabledTags;

    @NotNull
    private final Collection<String> userIdentifiers;

    /* JADX WARN: Multi-variable type inference failed */
    public SubscribeParams(@NotNull Collection<String> userIdentifiers, @NotNull PusherApplicationType application, @NotNull Set<? extends Tag> enabledTags) {
        Intrinsics.checkNotNullParameter(userIdentifiers, "userIdentifiers");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(enabledTags, "enabledTags");
        this.userIdentifiers = userIdentifiers;
        this.application = application;
        this.enabledTags = enabledTags;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SubscribeParams copy$default(SubscribeParams subscribeParams, Collection collection, PusherApplicationType pusherApplicationType, Set set, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            collection = subscribeParams.userIdentifiers;
        }
        if ((i10 & 2) != 0) {
            pusherApplicationType = subscribeParams.application;
        }
        if ((i10 & 4) != 0) {
            set = subscribeParams.enabledTags;
        }
        return subscribeParams.copy(collection, pusherApplicationType, set);
    }

    @NotNull
    public final Collection<String> component1() {
        return this.userIdentifiers;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final PusherApplicationType getApplication() {
        return this.application;
    }

    @NotNull
    public final Set<Tag> component3() {
        return this.enabledTags;
    }

    @NotNull
    public final SubscribeParams copy(@NotNull Collection<String> userIdentifiers, @NotNull PusherApplicationType application, @NotNull Set<? extends Tag> enabledTags) {
        Intrinsics.checkNotNullParameter(userIdentifiers, "userIdentifiers");
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(enabledTags, "enabledTags");
        return new SubscribeParams(userIdentifiers, application, enabledTags);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubscribeParams)) {
            return false;
        }
        SubscribeParams subscribeParams = (SubscribeParams) other;
        return Intrinsics.areEqual(this.userIdentifiers, subscribeParams.userIdentifiers) && this.application == subscribeParams.application && Intrinsics.areEqual(this.enabledTags, subscribeParams.enabledTags);
    }

    @NotNull
    public final PusherApplicationType getApplication() {
        return this.application;
    }

    @NotNull
    public final Set<Tag> getEnabledTags() {
        return this.enabledTags;
    }

    @NotNull
    public final Collection<String> getUserIdentifiers() {
        return this.userIdentifiers;
    }

    public int hashCode() {
        return (((this.userIdentifiers.hashCode() * 31) + this.application.hashCode()) * 31) + this.enabledTags.hashCode();
    }

    @NotNull
    public String toString() {
        return "SubscribeParams(userIdentifiers=" + this.userIdentifiers + ", application=" + this.application + ", enabledTags=" + this.enabledTags + ")";
    }
}
