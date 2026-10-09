package ru.mail.settings.notifications.portal.di;

import dagger.assisted.AssistedFactory;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@AssistedFactory
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/settings/notifications/portal/di/PortalAppNotificationsViewModelAssistedFactory;", "", "create", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel;", "appId", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PortalAppNotificationsViewModelAssistedFactory {
    @NotNull
    PortalAppNotificationsViewModel create(@NotNull String appId);
}
