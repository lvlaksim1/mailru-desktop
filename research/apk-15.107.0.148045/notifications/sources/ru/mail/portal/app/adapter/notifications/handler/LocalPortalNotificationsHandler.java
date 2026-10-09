package ru.mail.portal.app.adapter.notifications.handler;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0005H&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/handler/LocalPortalNotificationsHandler;", "", "handlePush", "", "app", "", "title", "text", "deepLink", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LocalPortalNotificationsHandler {

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ void handlePush$default(LocalPortalNotificationsHandler localPortalNotificationsHandler, String str, String str2, String str3, String str4, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handlePush");
        }
        if ((i10 & 4) != 0) {
            str3 = null;
        }
        localPortalNotificationsHandler.handlePush(str, str2, str3, str4);
    }

    void handlePush(@Nullable String app, @NotNull String title, @Nullable String text, @NotNull String deepLink);
}
