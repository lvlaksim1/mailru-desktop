package ru.mail.data.cmd.server;

import java.util.Map;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.SimultaneousCommandGroup;
import ru.mail.serverapi.AuthorizationAwareCommand;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class AuthorizedSimultaneousCommandGroup extends SimultaneousCommandGroup implements AuthorizationAwareCommand {
    public AuthorizedSimultaneousCommandGroup(Command<?, ?>... commandArr) {
        super(commandArr);
        if (!containsAuthorizationAwareCommand(commandArr)) {
            throw new IllegalArgumentException("AuthorizedSimultaneousCommandGroup should be initialized with at least one command implementing AuthorizationAwareCommand interface");
        }
    }

    private boolean containsAuthorizationAwareCommand(Command<?, ?>... commandArr) {
        for (Command<?, ?> command : commandArr) {
            if (command instanceof AuthorizationAwareCommand) {
                return true;
            }
        }
        return false;
    }

    @Override // ru.mail.serverapi.AuthorizationAwareCommand
    public CommandStatus<?> getAuthorizationStatus() {
        for (Map.Entry<Command<?, ?>, Object> entry : getResult().entrySet()) {
            if (entry.getKey() instanceof AuthorizationAwareCommand) {
                return ((AuthorizationAwareCommand) entry.getKey()).getAuthorizationStatus();
            }
        }
        return new CommandStatus.OK();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    public synchronized void setResult(Map<Command<?, ?>, Object> map) {
        super.setResult(map);
    }
}
