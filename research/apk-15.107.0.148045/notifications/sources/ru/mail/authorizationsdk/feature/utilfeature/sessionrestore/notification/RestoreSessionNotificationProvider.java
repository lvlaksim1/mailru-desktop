package ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.notification;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.ReturnParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/notification/RestoreSessionNotificationProvider;", "", "showRestoreFlowNotification", "", "returnUserParams", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/ReturnParams;", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface RestoreSessionNotificationProvider {
    void showRestoreFlowNotification(@NotNull ReturnParams returnUserParams);
}
