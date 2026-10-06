package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Objects;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.util.log.Log;
import ru.mail.util.push.component.PushComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class RemovePushSettingsCmd extends Command<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("RemovePushSettingsCmd");
    private final Context mContext;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @NonNull
        private final String mAccount;

        public Params(@NonNull String str) {
            this.mAccount = str;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mAccount.equals(((Params) obj).mAccount);
        }

        public int hashCode() {
            return Objects.hash(this.mAccount);
        }
    }

    public RemovePushSettingsCmd(Context context, Params params) {
        super(params);
        this.mContext = context;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("NETWORK");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    public EmptyResult onExecute(ExecutorSelector executorSelector) {
        ((PushComponent) Locator.locate(this.mContext, PushComponent.class)).getPusherTransport().unsubscribeAllByDeviceId(getParams().mAccount);
        return new EmptyResult();
    }
}
