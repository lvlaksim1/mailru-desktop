package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.StringUtils;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class CommandGroup extends Command<Void, Object> {
    private static final int INFINITE_LOOP_WARN = 5;
    private static final Log LOG = Log.getLog("CommandGroup");
    private final LinkedList<CommandEntry> mCommandChain;
    private Command<?, ?> mCurrentCommand;
    private ObservableFuture<?> mCurrentFuture;

    public CommandGroup() {
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

    protected <R> R executeCommand(Command<?, R> command, Priority priority, ExecutorSelector executorSelector) {
        ObservableFuture<R> observableFutureExecute = command.execute(executorSelector, priority);
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
        try {
            return observableFuture.getOrThrow();
        } catch (InterruptedException unused) {
            LOG.e("Unable to get command result because command group was canceled");
            setResult(new CommandStatus.CANCELLED());
            setCancelled(true);
            onCancelled();
            Thread.currentThread().interrupt();
            return null;
        } catch (ExecutionException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof CommandCancellationException) {
                return (R) new CommandStatus.CANCELLED();
            }
            if (cause instanceof CommandExecutionException) {
                return (R) new CommandStatus.ERROR(new Exception(cause.getCause()));
            }
            LOG.e("Exception was occurred during execution command inside command group", e10);
            setResult(new CommandStatus.ERROR(e10));
            removeAllCommands();
            return null;
        } catch (CancelledException e11) {
            LOG.i("Command inside command group was cancelled", e11);
            setResult(new CommandStatus.CANCELLED());
            removeAllCommands();
            return null;
        }
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
    protected void onCancelled() {
        synchronized (this) {
            try {
                removeAllCommands();
                ObservableFuture<?> observableFuture = this.mCurrentFuture;
                if (observableFuture != null) {
                    observableFuture.cancel();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    protected Object onExecute(ExecutorSelector executorSelector) {
        while (true) {
            CommandEntry nextCommand = getNextCommand();
            if (nextCommand == null) {
                break;
            }
            if (nextCommand.getExecutionCount() >= 5) {
                LOG.w(String.format("It seems like the this command entered in infinite loop. Command %s has already been executed %d times. Force break the chain for command %s", nextCommand.getCommand(), Integer.valueOf(nextCommand.getExecutionCount()), toString()));
                break;
            }
            onExecuteCommand(nextCommand.getCommand(), nextCommand.getPriority(), executorSelector);
        }
        return getResult();
    }

    @Nullable
    @CheckForNull
    protected <R> R onExecuteCommand(Command<?, R> command, Priority priority, ExecutorSelector executorSelector) {
        R r10 = (R) executeCommand(command, priority, executorSelector);
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

    /* JADX INFO: compiled from: ProGuard */
    private static class CommandEntry {
        private final Command<?, ?> mCommand;
        private final Priority mCommandPriority;
        private int mExecuteCount;

        public CommandEntry(Command<?, ?> command, Priority priority) {
            this.mExecuteCount = 0;
            this.mCommand = command;
            this.mCommandPriority = priority;
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

        public Priority getPriority() {
            return this.mCommandPriority;
        }

        public int hashCode() {
            return this.mCommand.hashCode();
        }

        public void incrementExecuteCount() {
            this.mExecuteCount++;
        }

        public CommandEntry(Command<?, ?> command) {
            this(command, Priority.MEDIUM);
        }
    }

    public void addCommand(Command<?, ?> command, Priority priority) {
        synchronized (this) {
            this.mCommandChain.addLast(new CommandEntry(command, priority));
        }
    }

    protected <R> R executeCommand(Command<?, R> command, Priority priority, ExecutorSelector executorSelector, long j10, TimeUnit timeUnit) throws TimeoutException {
        ObservableFuture<R> observableFutureExecute = command.execute(executorSelector, priority);
        setCurrentFuture(observableFutureExecute);
        incrementExecuteCount(command);
        try {
            try {
                R r10 = (R) getResultFromFuture(observableFutureExecute, j10, timeUnit);
                setCurrentFuture(null);
                return r10;
            } catch (TimeoutException e10) {
                observableFutureExecute.cancel();
                throw e10;
            }
        } catch (Throwable th2) {
            setCurrentFuture(null);
            throw th2;
        }
    }

    @Nullable
    @CheckForNull
    protected <R> R getResultFromFuture(ObservableFuture<R> observableFuture, long j10, TimeUnit timeUnit) throws TimeoutException {
        try {
            return observableFuture.getOrThrow(j10, timeUnit);
        } catch (InterruptedException unused) {
            LOG.e("Unable to get command result because command group was canceled");
            setResult(new CommandStatus.CANCELLED());
            setCancelled(true);
            onCancelled();
            Thread.currentThread().interrupt();
            return null;
        } catch (ExecutionException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof CommandCancellationException) {
                return (R) new CommandStatus.CANCELLED();
            }
            if (cause instanceof CommandExecutionException) {
                return (R) new CommandStatus.ERROR(new Exception(cause.getCause()));
            }
            LOG.e("Exception was occurred during execution command inside command group", e10);
            setResult(new CommandStatus.ERROR(e10));
            removeAllCommands();
            return null;
        } catch (CancelledException e11) {
            LOG.i("Command inside command group was cancelled", e11);
            setResult(new CommandStatus.CANCELLED());
            removeAllCommands();
            return null;
        }
    }
}
