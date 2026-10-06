package ru.mail.auth.request;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.PoolConstants;
import ru.mail.network.NetworkCommandStatus;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0014J\u000e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016¨\u0006\r"}, d2 = {"Lru/mail/auth/request/VKBadStateReturnCommand;", "Lru/mail/mailbox/cmd/Command;", "", "Lru/mail/mailbox/cmd/CommandStatus;", "", "<init>", "()V", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onExecute", "getResult", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VKBadStateReturnCommand extends Command<Unit, CommandStatus<String>> {
    public VKBadStateReturnCommand() {
        super(null);
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor(PoolConstants.DEFAULT);
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    public CommandStatus<String> getResult() {
        return new NetworkCommandStatus.ERROR_INVALID_LOGIN();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    public CommandStatus<String> onExecute(@Nullable ExecutorSelector selector) {
        return new NetworkCommandStatus.ERROR_INVALID_LOGIN();
    }
}
