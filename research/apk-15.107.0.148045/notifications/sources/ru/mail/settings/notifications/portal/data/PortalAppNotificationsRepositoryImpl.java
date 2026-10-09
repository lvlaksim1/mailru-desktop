package ru.mail.settings.notifications.portal.data;

import androidx.compose.runtime.internal.StabilityInferred;
import dagger.hilt.android.scopes.ViewModelScoped;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.portal.app.adapter.notifications.tags.AppTagsStorage;
import ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@ViewModelScoped
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/settings/notifications/portal/data/PortalAppNotificationsRepositoryImpl;", "Lru/mail/settings/notifications/portal/domain/PortalAppNotificationsRepository;", "tagsStorage", "Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorage;", "<init>", "(Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorage;)V", "getDisabledTagIds", "", "", "appId", "", "setTagEnabled", "", "tagId", "enabled", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PortalAppNotificationsRepositoryImpl implements PortalAppNotificationsRepository {
    public static final int $stable = 8;

    @NotNull
    private final AppTagsStorage tagsStorage;

    @Inject
    public PortalAppNotificationsRepositoryImpl(@NotNull AppTagsStorage tagsStorage) {
        Intrinsics.checkNotNullParameter(tagsStorage, "tagsStorage");
        this.tagsStorage = tagsStorage;
    }

    @Override // ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository
    @NotNull
    public Set<Integer> getDisabledTagIds(@NotNull String appId) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        return this.tagsStorage.getDisabledTagIds(appId);
    }

    @Override // ru.mail.settings.notifications.portal.domain.PortalAppNotificationsRepository
    public void setTagEnabled(@NotNull String appId, int tagId, boolean enabled) {
        Intrinsics.checkNotNullParameter(appId, "appId");
        if (enabled) {
            this.tagsStorage.setTagEnabled(appId, tagId);
        } else {
            this.tagsStorage.setTagDisabled(appId, tagId);
        }
    }
}
