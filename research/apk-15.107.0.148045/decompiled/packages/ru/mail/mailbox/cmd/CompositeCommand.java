package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import javax.annotation.CheckForNull;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public abstract class CompositeCommand<R> extends Command<Void, R> {
    private static final Log LOG = Log.getLog("CompositeCommand");
    private ObservableFuture<?> mCurrentFuture;
    private CommandInterceptor<?, ?, R> mCurrentInterceptor;
    private final CompositeCommand<R>.InternalCommandExecutor mInternalExecutor;
    private ExecutorSelector mSelector;

    /* JADX INFO: compiled from: ProGuard */
    private class InternalCommandExecutor implements CommandInterceptor.Executor {
        @Override // ru.mail.mailbox.cmd.CommandInterceptor.Executor
        public <T> T executeCommand(@NonNull Command<?, T> command) {
            return (T) CompositeCommand.this.executeCommand(command);
        }

        private InternalCommandExecutor() {
        }
    }

    public CompositeCommand() {
        super(null);
        this.mInternalExecutor = new InternalCommandExecutor();
    }

    @Nullable
    @CheckForNull
    private <T> T getResultFromFuture(ObservableFuture<T> observableFuture) {
        ExecutionResult<T> executionResultObtainResult = observableFuture.obtainResult();
        if (executionResultObtainResult instanceof ExecutionResult.Success) {
            return (T) ((ExecutionResult.Success) executionResultObtainResult).getResult();
        }
        if (executionResultObtainResult instanceof ExecutionResult.Cancelled) {
            Throwable exception = ((ExecutionResult.Cancelled) executionResultObtainResult).getException();
            LOG.i("Command inside composite command was cancelled", exception);
            throw new CommandCancellationException(exception);
        }
        if (executionResultObtainResult instanceof ExecutionResult.Exception) {
            LOG.i("Command inside composite command was executed with exception");
            throw new CommandExecutionException(((ExecutionResult.Exception) executionResultObtainResult).getException());
        }
        if (!(executionResultObtainResult instanceof ExecutionResult.Interrupted)) {
            throw new IllegalStateException("Unexpected execution result");
        }
        LOG.e("Unable to get command result because composite command was canceled");
        setCancelled(true);
        onCancelled();
        Thread.currentThread().interrupt();
        throw new CommandCancellationException(((ExecutionResult.Interrupted) executionResultObtainResult).getException());
    }

    private void setCurrentFuture(ObservableFuture<?> observableFuture) {
        synchronized (this) {
            this.mCurrentFuture = observableFuture;
        }
    }

    @Override // ru.mail.mailbox.cmd.Command
    public boolean equals(Object obj) {
        return this == obj;
    }

    protected <T> T executeCommand(Command<?, T> command) {
        ObservableFuture<T> observableFutureExecute = command.execute(this.mSelector);
        setCurrentFuture(observableFutureExecute);
        T t10 = (T) getResultFromFuture(observableFutureExecute);
        setCurrentFuture(null);
        return t10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected ReusePolicy getReusePolicy() {
        return new ReusePolicy.Unique();
    }

    @Override // ru.mail.mailbox.cmd.Command
    public int hashCode() {
        return System.identityHashCode(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected <T, E> E intercept(Command<?, T> command, T t10, CommandInterceptor<T, E, R> commandInterceptor) {
        this.mCurrentInterceptor = commandInterceptor;
        E e10 = (E) commandInterceptor.afterExecute(this.mInternalExecutor, command, t10);
        this.mCurrentInterceptor = null;
        return e10;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onCancelled() {
        synchronized (this) {
            try {
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
    protected R onExecute(ExecutorSelector executorSelector) {
        R rHandleError;
        this.mSelector = executorSelector;
        try {
            R rOnExecuteComposite = onExecuteComposite();
            setResult(rOnExecuteComposite);
            return rOnExecuteComposite;
        } catch (Throwable th2) {
            CommandInterceptor<?, ?, R> commandInterceptor = this.mCurrentInterceptor;
            if (commandInterceptor == null || (rHandleError = commandInterceptor.handleError(th2)) == null) {
                throw th2;
            }
            setResult(rHandleError);
            return rHandleError;
        }
    }

    @NonNull
    protected abstract R onExecuteComposite();

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected final CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getCommandGroupExecutor();
    }

    protected <T, E> E executeCommand(Command<?, T> command, CommandInterceptor<T, E, R> commandInterceptor) {
        commandInterceptor.beforeExecute(command);
        return (E) intercept(command, executeCommand(command), commandInterceptor);
    }
}
