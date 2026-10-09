package ru.mail.util.push;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import ru.mail.data.dao.AuthorityProvider;
import ru.mail.locator.Locator;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.AlreadyDoneObservableFuture;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.util.push.component.PushComponent;
import ru.mail.utils.lifecycle.StackedActivityLifecycleHandler;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class ForegroundPushListener implements StackedActivityLifecycleHandler.AppVisibilityListener {
    private final Context mContext;
    private final PushMessagesTransport.PushMessagesEventHandler mPushMessageEventHandler;

    /* JADX INFO: compiled from: ProGuard */
    private static class SyncOnPushListener implements PushMessagesTransport.PushMessagesEventHandler {
        private Context mContext;

        public SyncOnPushListener(Context context) {
            this.mContext = context;
        }

        private boolean isAccountExistInDataManager(String str, Context context) {
            return CommonDataManager.from(context).getAccountFromDB(str) != null;
        }

        @Override // ru.mail.util.push.PushMessagesTransport.PushMessagesEventHandler
        public List<ObservableFuture<Void>> handlePushMessagesReceived(List<PushMessage> list) {
            for (PushMessage pushMessage : list) {
                if ((pushMessage instanceof NewMailPush) && isAccountExistInDataManager(pushMessage.getProfileId(), this.mContext)) {
                    CommonDataManager.from(this.mContext).requestSync(new Account(pushMessage.getProfileId(), "ru.mail"), AuthorityProvider.getMailContentProviderAuthority(this.mContext), new Bundle());
                }
            }
            return Collections.singletonList(new AlreadyDoneObservableFuture(null));
        }
    }

    public ForegroundPushListener(Context context) {
        this.mContext = context;
        this.mPushMessageEventHandler = new SyncOnPushListener(context);
    }

    private Collection<PushMessagesTransport> getPushTransports() {
        return ((PushComponent) Locator.from(this.mContext).locate(PushComponent.class)).getPushMessagesTransports();
    }

    @Override // ru.mail.utils.lifecycle.StackedActivityLifecycleHandler.AppVisibilityListener
    public void onBackground(Activity activity) {
        Iterator<PushMessagesTransport> it = getPushTransports().iterator();
        while (it.hasNext()) {
            it.next().removeListener(this.mPushMessageEventHandler);
        }
    }

    @Override // ru.mail.utils.lifecycle.StackedActivityLifecycleHandler.AppVisibilityListener
    public void onForeground(Activity activity) {
        for (PushMessagesTransport pushMessagesTransport : getPushTransports()) {
            if (pushMessagesTransport.getPushType() != PushType.VKPNS) {
                pushMessagesTransport.addListener(this.mPushMessageEventHandler);
            }
        }
    }
}
