package ru.mail.util.push;

import androidx.annotation.NonNull;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.wallet.WalletNotificationPush;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public interface PushMessageVisitor {
    ObservableFuture<Void> visit(@NonNull CountPush countPush);

    ObservableFuture<Void> visit(@NonNull DeleteNotificationPush deleteNotificationPush);

    ObservableFuture<Void> visit(@NonNull MovePush movePush);

    ObservableFuture<Void> visit(@NonNull NewMailPush newMailPush);

    ObservableFuture<Void> visit(@NonNull OrderPush orderPush);

    ObservableFuture<Void> visit(@NonNull PasswordRestorePush passwordRestorePush);

    ObservableFuture<Void> visit(@NonNull PingPush pingPush);

    ObservableFuture<Void> visit(@NonNull PortalPush portalPush);

    ObservableFuture<Void> visit(@NonNull PromoteUrlPushMessage promoteUrlPushMessage);

    ObservableFuture<Void> visit(@NonNull RatBindPush ratBindPush);

    ObservableFuture<Void> visit(@NonNull RemoteCommandPush remoteCommandPush);

    ObservableFuture<Void> visit(@NonNull SendLogsPush sendLogsPush);

    ObservableFuture<Void> visit(@NonNull CalendarNotificationPush calendarNotificationPush);

    ObservableFuture<Void> visit(@NonNull WalletNotificationPush walletNotificationPush);
}
