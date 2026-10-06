package ru.mail.data.cmd.server;

import android.content.Context;
import javax.annotation.Nullable;
import ru.mail.config.ConfigurationRepository;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.registration.request.CheckEmailCmd;
import ru.mail.registration.request.GetAltEmailByNameCmd;
import ru.mail.registration.ui.AccountData;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class RegMailRuCmd extends CommandGroup {
    private AccountData mAccountData;
    private Context mContext;

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private GetAltEmailByNameCmd.Result mCheckEmailResult;
        private boolean mNeedUseLibverify;

        public GetAltEmailByNameCmd.Result getCheckEmailResult() {
            return this.mCheckEmailResult;
        }

        public boolean isNeedUseLibverify() {
            return this.mNeedUseLibverify;
        }

        public void setCheckEmailResult(GetAltEmailByNameCmd.Result result) {
            this.mCheckEmailResult = result;
        }

        public void setNeedUseLibverify(boolean z10) {
            this.mNeedUseLibverify = z10;
        }
    }

    public RegMailRuCmd(Context context, AccountData accountData, boolean z10) {
        this.mAccountData = accountData;
        this.mContext = context;
        setResult(new Result());
        addCommand(new CheckEmailCmd(this.mContext, new CheckEmailCmd.Params(context, this.mAccountData), z10));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <T> T onExecuteCommand(Command<?, T> command, Priority priority, ExecutorSelector executorSelector) {
        T t10 = (T) super.onExecuteCommand(command, priority, executorSelector);
        if ((command instanceof CheckEmailCmd) && (t10 instanceof CommandStatus.OK)) {
            ((Result) getResult()).setCheckEmailResult((GetAltEmailByNameCmd.Result) ((CommandStatus.OK) t10).getData());
            ((Result) getResult()).setNeedUseLibverify(((ConfigurationRepository) Locator.from(this.mContext).locate(ConfigurationRepository.class)).getConfiguration().isLibverifyEnabled());
        }
        return t10;
    }
}
