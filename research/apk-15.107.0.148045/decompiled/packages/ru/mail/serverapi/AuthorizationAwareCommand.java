package ru.mail.serverapi;

import ru.mail.mailbox.cmd.CommandStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public interface AuthorizationAwareCommand {
    CommandStatus<?> getAuthorizationStatus();
}
