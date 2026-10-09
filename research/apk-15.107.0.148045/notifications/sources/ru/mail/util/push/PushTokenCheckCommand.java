package ru.mail.util.push;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.vk.superapp.browser.ui.VkUIContactsDelegate;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import kotlin.Deprecated;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.cmd.server.pusher.CheckPushTokenCommand;
import ru.mail.locator.Locator;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.util.log.Log;
import ru.mail.util.push.component.PushComponent;
import ru.mail.utils.NetworkUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@Deprecated(message = "Can be removed after PushMe SDK integration")
public class PushTokenCheckCommand extends Command<Void, CommandStatus<?>> {
    private static final Log LOG = Log.getLog("PushTokenCheckCommand");
    private final Context mContext;

    public PushTokenCheckCommand(Context context) {
        super(null);
        this.mContext = context;
    }

    private boolean isSameTokenStoredOnServer(String str, PushMessagesTransport pushMessagesTransport) throws IOException {
        RequestArbiter requestArbiter = (RequestArbiter) Locator.locate(this.mContext, RequestArbiter.class);
        CheckPushTokenCommand checkPushTokenCommand = new CheckPushTokenCommand(this.mContext, str, pushMessagesTransport);
        try {
            CheckPushTokenCommand.Result orThrow = checkPushTokenCommand.execute(requestArbiter).getOrThrow();
            if (checkPushTokenCommand.isCancelled()) {
                throw new IOException(VkUIContactsDelegate.CONTACTS_ERROR_CANCELLED);
            }
            return orThrow.tokenExists();
        } catch (InterruptedException unused) {
            throw new IOException(VkUIContactsDelegate.CONTACTS_ERROR_CANCELLED);
        } catch (ExecutionException e10) {
            throw new RuntimeException(e10);
        }
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        String str;
        Collection<PushMessagesTransport> pushMessagesTransports = ((PushComponent) Locator.from(this.mContext).locate(PushComponent.class)).getPushMessagesTransports();
        String lastActiveProfileLogin = CommonDataManager.getLastActiveProfileLogin(this.mContext);
        for (PushMessagesTransport pushMessagesTransport : pushMessagesTransports) {
            if (pushMessagesTransport.getPushType() != PushType.VKPNS) {
                try {
                    if (!pushMessagesTransport.isRegistered() || !NetworkUtils.hasInternetConnection(this.mContext) || isSameTokenStoredOnServer(lastActiveProfileLogin, pushMessagesTransport)) {
                        str = "Tokens are equal";
                        LOG.i("Check success. Tokens are equal.");
                    } else if (pushMessagesTransport.register()) {
                        MailAppDependencies.analytics(this.mContext).onPushRegisteredAfterTokenCheck();
                        str = "New registration requested";
                        LOG.i("Check success. New registration requested.");
                    } else {
                        str = "New registration requested, but was declined";
                        LOG.i("Check success. New registration requested, but was declined.");
                    }
                } catch (IOException e10) {
                    String str2 = "Fail: " + e10.getMessage();
                    LOG.i("Check result fail.", e10);
                    str = str2;
                }
                if (!TextUtils.isEmpty(str)) {
                    MailAppDependencies.analytics(this.mContext).onPushRegisteredCheckComplete(str);
                }
            }
        }
        return new CommandStatus.OK();
    }
}
