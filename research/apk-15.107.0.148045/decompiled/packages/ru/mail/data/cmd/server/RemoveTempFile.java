package ru.mail.data.cmd.server;

import androidx.annotation.NonNull;
import java.io.File;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class RemoveTempFile extends Command<String, Object> {
    private static final Log LOG = Log.getLog("RemoveTempFile");

    public RemoveTempFile(String str) {
        super(str);
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected Object onExecute(ExecutorSelector executorSelector) {
        if (getParams() == null || new File(getParams()).delete()) {
            return null;
        }
        LOG.w("cannot delete file " + getParams());
        return null;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("FILE_IO");
    }
}
