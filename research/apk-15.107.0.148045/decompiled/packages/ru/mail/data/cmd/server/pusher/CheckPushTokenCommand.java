package ru.mail.data.cmd.server.pusher;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import kotlin.Deprecated;
import org.jetbrains.annotations.NotNull;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.util.log.Log;
import ru.mail.util.push.component.PushComponent;
import ru.mail.util.push.provider.PushInfoProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Deprecated(message = "Can be removed after PushMe SDK integration")
public class CheckPushTokenCommand extends Command<Object, Result> {
    private static final Log LOG = Log.getLog("CheckPushTokenCommand");
    private final Context mContext;
    private final String mLogin;
    private final PushInfoProvider mPushInfoProvider;

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final boolean mTokenExists;

        public Result(boolean z10) {
            this.mTokenExists = z10;
        }

        public boolean tokenExists() {
            return this.mTokenExists;
        }
    }

    public CheckPushTokenCommand(Context context, String str, PushInfoProvider pushInfoProvider) {
        super(null);
        this.mContext = context;
        this.mLogin = str;
        this.mPushInfoProvider = pushInfoProvider;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    @NotNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    public Result onExecute(ExecutorSelector executorSelector) {
        boolean zCheckIsTokenExists = ((PushComponent) Locator.locate(this.mContext, PushComponent.class)).getPusherTransport().checkIsTokenExists(this.mLogin, this.mPushInfoProvider);
        LOG.i("Is token exists: " + zCheckIsTokenExists);
        return new Result(zCheckIsTokenExists);
    }
}
