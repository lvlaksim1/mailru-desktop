package ru.mail.mailbox.cmd;

import java.util.Map;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public interface CommandExecutionInfo {
    String getLoggerEventName();

    String getLoggerParamName();

    Map<String, String> getParamsForLogger();
}
