package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.LinkedList;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.StringUtils;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public class TypedCommandGroup<T> extends Command<Void, T> {
    private static final int INFINITE_LOOP_WARN = 5;
    private static final Log LOG = Log.getLog("TypedCommandGroup");
    private final LinkedList<CommandEntry> mCommandChain;
    private Command<?, ?> mCurrentCommand;
    private ObservableFuture<?> mCurrentFuture;

    /* JADX INFO: compiled from: ProGuard */
    private static class CommandEntry {
        private final Command<?, ?> mCommand;
        private int mExecuteCount = 0;

        public CommandEntry(Command<?, ?> command) {
            this.mCommand = command;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return this.mCommand.equals(((CommandEntry) obj).mCommand);
        }

        public Command<?, ?> getCommand() {
            return this.mCommand;
        }

        public int getExecutionCount() {
            return this.mExecuteCount;
        }

        public int hashCode() {
            return this.mCommand.hashCode();
        }

        public void incrementExecuteCount() {
            this.mExecuteCount++;
        }
    }

    public TypedCommandGroup() {
        super(null);
        this.mCommandChain = new LinkedList<>();
    }

    private CommandEntry getNextCommand() {
        synchronized (this) {
            try {
                if (isCancelled() || !hasMoreCommands()) {
                    return null;
                }
                CommandEntry commandEntryPeek = this.mCommandChain.peek();
                this.mCurrentCommand = commandEntryPeek.getCommand();
                return commandEntryPeek;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private void incrementExecuteCount(Command<?, ?> command) {
        synchronized (this) {
            try {
                int iIndexOf = this.mCommandChain.indexOf(new CommandEntry(command));
                if (iIndexOf != -1) {
                    this.mCommandChain.get(iIndexOf).incrementExecuteCount();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    private void setCurrentFuture(ObservableFuture<?> observableFuture) {
        synchronized (this) {
            this.mCurrentFuture = observableFuture;
        }
    }

    public void addCommand(Command<?, ?> command) {
        synchronized (this) {
            this.mCommandChain.addLast(new CommandEntry(command));
        }
    }

    public void addCommandAtFront(Command<?, ?> command) {
        synchronized (this) {
            this.mCommandChain.addFirst(new CommandEntry(command));
        }
    }

    @Override // ru.mail.mailbox.cmd.Command
    public boolean equals(Object obj) {
        return this == obj;
    }

    protected <R> R executeCommand(Command<?, R> command, ExecutorSelector executorSelector) {
        ObservableFuture<R> observableFutureExecute = command.execute(executorSelector);
        setCurrentFuture(observableFutureExecute);
        incrementExecuteCount(command);
        R r10 = (R) getResultFromFuture(observableFutureExecute);
        setCurrentFuture(null);
        return r10;
    }

    protected Command<?, ?> getCurrentCommand() {
        return this.mCurrentCommand;
    }

    @Nullable
    @CheckForNull
    protected <R> R getResultFromFuture(ObservableFuture<R> observableFuture) {
        ExecutionResult<R> executionResultObtainResult = observableFuture.obtainResult();
        if (executionResultObtainResult instanceof ExecutionResult.Success) {
            return (R) ((ExecutionResult.Success) executionResultObtainResult).getResult();
        }
        if (executionResultObtainResult instanceof ExecutionResult.Cancelled) {
            Throwable exception = ((ExecutionResult.Cancelled) executionResultObtainResult).getException();
            LOG.i("Command inside command group was cancelled", exception);
            removeAllCommands();
            throw new CommandCancellationException(exception);
        }
        if (executionResultObtainResult instanceof ExecutionResult.Exception) {
            LOG.e("Exception occurred in command inside command group");
            throw new CommandExecutionException(((ExecutionResult.Exception) executionResultObtainResult).getException());
        }
        if (!(executionResultObtainResult instanceof ExecutionResult.Interrupted)) {
            throw new IllegalStateException("Unexpected execution result");
        }
        LOG.e("Unable to get command result because command group was canceled");
        setCancelled(true);
        onCancelled();
        Thread.currentThread().interrupt();
        throw new CommandCancellationException(((ExecutionResult.Interrupted) executionResultObtainResult).getException());
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected ReusePolicy getReusePolicy() {
        return new ReusePolicy.Unique();
    }

    public boolean hasMoreCommands() {
        boolean z10;
        synchronized (this) {
            z10 = !this.mCommandChain.isEmpty();
        }
        return z10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    public int hashCode() {
        return System.identityHashCode(this);
    }

    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    protected T onExecute(ExecutorSelector executorSelector) {
        while (true) {
            CommandEntry nextCommand = getNextCommand();
            if (nextCommand == null) {
                break;
            }
            if (nextCommand.getExecutionCount() >= 5) {
                LOG.w(String.format("It seems like the this command entered in infinite loop. Command %s has already been executed %d times. Force break the chain for command %s", nextCommand.getCommand(), Integer.valueOf(nextCommand.getExecutionCount()), toString()));
                break;
            }
            onExecuteCommand(nextCommand.getCommand(), executorSelector);
        }
        return getResult();
    }

    @Nullable
    @CheckForNull
    protected <R> R onExecuteCommand(Command<?, R> command, ExecutorSelector executorSelector) {
        R r10 = (R) executeCommand(command, executorSelector);
        removeCommand(command);
        return r10;
    }

    protected synchronized Command<?, ?> peekActualCommand() {
        CommandEntry commandEntryPeek;
        commandEntryPeek = this.mCommandChain.peek();
        return commandEntryPeek != null ? commandEntryPeek.mCommand : null;
    }

    public void removeAllCommands() {
        synchronized (this) {
            this.mCommandChain.clear();
        }
    }

    public void removeCommand(Command<?, ?> command) {
        synchronized (this) {
            this.mCommandChain.remove(new CommandEntry(command));
        }
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected final CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getCommandGroupExecutor();
    }

    public String toString() {
        String str;
        synchronized (this) {
            str = super.toString() + StringUtils.SPACE + this.mCommandChain;
        }
        return str;
    }
}
