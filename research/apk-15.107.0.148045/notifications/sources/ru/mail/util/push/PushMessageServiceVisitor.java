package ru.mail.util.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import ru.mail.MailApplication;
import ru.mail.config.ConfigurationRepository;
import ru.mail.locator.Locator;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.logic.navigation.Navigator;
import ru.mail.logic.navigation.PendingActionObserver;
import ru.mail.logic.navigation.executor.AppContextExecutor;
import ru.mail.mailbox.cmd.AlreadyDoneObservableFuture;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.remote_exec.RemoteExecutionWorker;
import ru.mail.util.push.wallet.WalletNotificationPush;
import ru.mail.utils.serialization.SerializableIntent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class PushMessageServiceVisitor implements PushMessageVisitor {
    private final Context mContext;

    PushMessageServiceVisitor(Context context) {
        this.mContext = context;
    }

    private DataManager getDataManager() {
        return CommonDataManager.from(this.mContext);
    }

    private Navigator getNavigator() {
        return (Navigator) Locator.from(this.mContext).locate(Navigator.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$visit$0(SendAllPongRequestWorker.Params params) {
        ((WorkScheduler) Locator.locate(this.mContext, WorkScheduler.class)).schedule(new WorkRequest.Builder(SendAllPongRequestWorker.class, SendAllPongRequestWorker.uniqueId).data(params.toData()).constraints(WorkRequest.Constraints.NETWORK).initialDelay(1L, TimeUnit.MINUTES).getRequest());
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull PingPush pingPush) {
        boolean zIsUseSupervisorJobInWorkersEnabled = ConfigurationRepository.from(this.mContext).getConfiguration().isUseSupervisorJobInWorkersEnabled();
        String requestUrl = pingPush.getRequestUrl();
        final SendAllPongRequestWorker.Params params = new SendAllPongRequestWorker.Params();
        params.setUrlTriggeredWorker(requestUrl);
        params.setNeedUseSupervisorJob(zIsUseSupervisorJobInWorkersEnabled);
        getDataManager().savePongUrl(requestUrl, new DataManager.SavePongUrlListener() { // from class: ru.mail.util.push.e1
            @Override // ru.mail.logic.content.DataManager.SavePongUrlListener
            public final void onSuccess() {
                this.f101060a.lambda$visit$0(params);
            }
        });
        return new AlreadyDoneObservableFuture(null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull NewMailPush newMailPush) {
        return NotificationHandler.from(this.mContext).showNotification(newMailPush, null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull CountPush countPush) {
        return NotificationHandler.from(this.mContext).updateNotificationCount(countPush);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull DeleteNotificationPush deleteNotificationPush) {
        return NotificationHandler.from(this.mContext).deleteNotification(deleteNotificationPush);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull RemoteCommandPush remoteCommandPush) {
        Intent intent = new Intent(remoteCommandPush.getAction());
        intent.setPackage(remoteCommandPush.getPackage());
        String uri = remoteCommandPush.getUri();
        if (!TextUtils.isEmpty(uri) || !TextUtils.isEmpty(remoteCommandPush.getType())) {
            intent.setDataAndType(TextUtils.isEmpty(uri) ? null : Uri.parse(uri), remoteCommandPush.getType());
        }
        if (!TextUtils.isEmpty(remoteCommandPush.getCategory())) {
            intent.addCategory(remoteCommandPush.getCategory());
        }
        if (!TextUtils.isEmpty(remoteCommandPush.getComponentClassName()) && !TextUtils.isEmpty(remoteCommandPush.getComponentPackage())) {
            intent.setComponent(new ComponentName(remoteCommandPush.getComponentPackage(), remoteCommandPush.getComponentClassName()));
        }
        if (remoteCommandPush.getExtras().size() > 0) {
            for (Map.Entry<String, String> entry : remoteCommandPush.getExtras().entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        RemoteExecutionWorker.Params params = new RemoteExecutionWorker.Params();
        params.setIntent(new SerializableIntent(intent));
        ((WorkScheduler) Locator.locate(this.mContext, WorkScheduler.class)).schedule(new WorkRequest.Builder(RemoteExecutionWorker.class, RemoteExecutionWorker.uniqueId).data(params.toData()).getRequest());
        return new AlreadyDoneObservableFuture(null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull PromoteUrlPushMessage promoteUrlPushMessage) {
        return NotificationHandler.from(this.mContext).showPromoteNotification(promoteUrlPushMessage, null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull PasswordRestorePush passwordRestorePush) {
        if (((MailApplication) this.mContext.getApplicationContext()).getLifecycleHandler().isAppBackgrounded()) {
            return NotificationHandler.from(this.mContext).restorePassNotification(passwordRestorePush);
        }
        Bundle bundle = new Bundle();
        bundle.putString("account_login", passwordRestorePush.getEmail());
        AppContextExecutor appContextExecutor = new AppContextExecutor(this.mContext);
        appContextExecutor.getIntentExtra().putAll(bundle);
        getNavigator().findPathFor(passwordRestorePush.getUri()).observe(Schedulers.mainThread(), new PendingActionObserver(appContextExecutor));
        return new AlreadyDoneObservableFuture(null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull MovePush movePush) {
        if (ConfigurationRepository.from(this.mContext).getConfiguration().isMovePushSupported()) {
            return NotificationHandler.from(this.mContext).updateNotificationsAfterMoveMsg(movePush);
        }
        return new AlreadyDoneObservableFuture(null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull OrderPush orderPush) {
        UpdateOrderStatusWorker.Params params = new UpdateOrderStatusWorker.Params();
        params.setPushOrder(orderPush);
        ((WorkScheduler) Locator.locate(this.mContext, WorkScheduler.class)).schedule(new WorkRequest.Builder(UpdateOrderStatusWorker.class, UpdateOrderStatusWorker.uniqueId).data(params.toData()).getRequest());
        return new AlreadyDoneObservableFuture(null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull SendLogsPush sendLogsPush) {
        return SendLogsPushHandler.INSTANCE.handleSendLogsPush(this.mContext);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull RatBindPush ratBindPush) {
        return RatBindPushHandler.INSTANCE.handleRatBindPush(this.mContext, ratBindPush);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull CalendarNotificationPush calendarNotificationPush) {
        return NotificationHandler.from(this.mContext).showCalendarNotification(calendarNotificationPush, null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull WalletNotificationPush walletNotificationPush) {
        return NotificationHandler.from(this.mContext).showWalletNotification(walletNotificationPush, null);
    }

    @Override // ru.mail.util.push.PushMessageVisitor
    public ObservableFuture<Void> visit(@NonNull PortalPush portalPush) {
        return NotificationHandler.from(this.mContext).showPortalNotification(portalPush);
    }
}
