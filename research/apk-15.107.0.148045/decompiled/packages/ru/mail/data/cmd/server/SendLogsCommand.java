package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ru.mail.arbiter.Pools;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Description;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SendLogsCommand extends Command<Params, CommandStatus> {
    private Context mContext;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        private String mAsserterName;
        private Description mDescription;
        private String mFailMessage;

        public Params(String str, String str2, Description description) {
            this.mAsserterName = str;
            this.mFailMessage = str2;
            this.mDescription = description;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Params params = (Params) obj;
                String str = this.mAsserterName;
                if (str == null ? params.mAsserterName != null : !str.equals(params.mAsserterName)) {
                    return false;
                }
                String str2 = this.mFailMessage;
                String str3 = params.mFailMessage;
                if (str2 != null) {
                    return str2.equals(str3);
                }
                if (str3 == null) {
                    return true;
                }
            }
            return false;
        }

        public String getAsserterName() {
            return this.mAsserterName;
        }

        public Description getDescription() {
            return this.mDescription;
        }

        public String getFailMessage() {
            return this.mFailMessage;
        }

        public int hashCode() {
            String str = this.mAsserterName;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mFailMessage;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }
    }

    public SendLogsCommand(Context context, Params params) {
        super(params);
        this.mContext = context;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor(Pools.COMPUTATION);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    public CommandStatus onExecute(ExecutorSelector executorSelector) {
        AsserterFactory.createAsserter(((AsserterConfigFactory) Locator.locate(this.mContext, AsserterConfigFactory.class)).createAsserterConfiguration(getParams().getAsserterName())).fail(getParams().getFailMessage(), new RuntimeException(getParams().getFailMessage()), getParams().getDescription());
        return new CommandStatus.OK();
    }
}
