package ru.mail.portal.app.adapter.notifications.config;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.portal.app.adapter.notifications.tags.Tag;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\t\nB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b¨\u0006\u000b"}, d2 = {"Lru/mail/portal/app/adapter/notifications/config/NotificationsConfigurationFactory;", "", "<init>", "()V", "createEmptyNotificationsConfiguration", "Lru/mail/portal/app/adapter/notifications/config/NotificationsConfiguration;", "createDefaultNotificationsConfiguration", "config", "Lru/mail/portal/app/adapter/notifications/config/AppNotificationsConfig;", "DefaultNotificationsConfiguration", "EmptyNotificationsConfiguration", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationsConfigurationFactory {

    @NotNull
    public static final NotificationsConfigurationFactory INSTANCE = new NotificationsConfigurationFactory();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/portal/app/adapter/notifications/config/NotificationsConfigurationFactory$DefaultNotificationsConfiguration;", "Lru/mail/portal/app/adapter/notifications/config/NotificationsConfiguration;", "config", "Lru/mail/portal/app/adapter/notifications/config/AppNotificationsConfig;", "<init>", "(Lru/mail/portal/app/adapter/notifications/config/AppNotificationsConfig;)V", "getAppTags", "", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class DefaultNotificationsConfiguration implements NotificationsConfiguration {

        @NotNull
        private final AppNotificationsConfig config;

        public DefaultNotificationsConfiguration(@NotNull AppNotificationsConfig config) {
            Intrinsics.checkNotNullParameter(config, "config");
            this.config = config;
        }

        @Override // ru.mail.portal.app.adapter.notifications.config.NotificationsConfiguration
        @NotNull
        public Set<Tag> getAppTags() {
            return this.config.getTags();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u0007"}, d2 = {"Lru/mail/portal/app/adapter/notifications/config/NotificationsConfigurationFactory$EmptyNotificationsConfiguration;", "Lru/mail/portal/app/adapter/notifications/config/NotificationsConfiguration;", "<init>", "()V", "getAppTags", "", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class EmptyNotificationsConfiguration implements NotificationsConfiguration {
        @Override // ru.mail.portal.app.adapter.notifications.config.NotificationsConfiguration
        @NotNull
        public Set<Tag> getAppTags() {
            return SetsKt.emptySet();
        }
    }

    private NotificationsConfigurationFactory() {
    }

    @NotNull
    public final NotificationsConfiguration createDefaultNotificationsConfiguration(@NotNull AppNotificationsConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return new DefaultNotificationsConfiguration(config);
    }

    @NotNull
    public final NotificationsConfiguration createEmptyNotificationsConfiguration() {
        return new EmptyNotificationsConfiguration();
    }
}
