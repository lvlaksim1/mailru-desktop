package ru.mail.settings.notifications.portal.di;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorageFactory;
import ru.mail.settings.notifications.portal.data.PortalAppNotificationsRepositoryImpl;
import ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\t\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u000f"}, d2 = {"Lru/mail/settings/notifications/portal/di/PortalAppNotificationsModule;", "", "<init>", "()V", "providePortalAppNotificationsRepository", "Lru/mail/settings/notifications/portal/domain/PortalAppNotificationsRepository;", "tagsStorage", "Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorage;", "provideAppTagsStorage", "context", "Landroid/content/Context;", "provideConfigPortalNotification", "Lru/mail/config/Configuration$Portal$Notifications;", "configRepo", "Lru/mail/config/ConfigurationRepository;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({SingletonComponent.class})
public final class PortalAppNotificationsModule {
    public static final int $stable = 0;

    @Provides
    @NotNull
    public final AppTagsStorage provideAppTagsStorage(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppTagsStorageFactory.INSTANCE.createStorage(context);
    }

    @Provides
    @NotNull
    public final Configuration.Portal.Notifications provideConfigPortalNotification(@NotNull ConfigurationRepository configRepo) {
        Intrinsics.checkNotNullParameter(configRepo, "configRepo");
        return configRepo.getConfiguration().getPortal().getNotifications();
    }

    @Provides
    @NotNull
    public final PortalAppNotificationsRepository providePortalAppNotificationsRepository(@NotNull AppTagsStorage tagsStorage) {
        Intrinsics.checkNotNullParameter(tagsStorage, "tagsStorage");
        return new PortalAppNotificationsRepositoryImpl(tagsStorage);
    }
}
