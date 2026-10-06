package ru.mail.mailbox.cmd;

import androidx.collection.MutableScatterMap;
import java.util.Map;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class CommandWithExecutionInfo<P, V> extends Command<P, V> implements CommandExecutionInfo {
    private final Map<String, String> mLoggerParams;

    public CommandWithExecutionInfo(P p10) {
        super(p10);
        this.mLoggerParams = new MutableScatterMap().asMutableMap();
    }

    @Override // ru.mail.mailbox.cmd.CommandExecutionInfo
    public Map<String, String> getParamsForLogger() {
        return this.mLoggerParams;
    }
}
