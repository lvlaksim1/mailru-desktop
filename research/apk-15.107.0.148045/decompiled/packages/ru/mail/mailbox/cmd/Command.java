package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.Callable;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class Command<P, R> {
    private P mParams;
    private R mResult;
    private final Log mLog = Log.getLog("Command");
    private boolean mCancelled = false;

    /* JADX INFO: compiled from: ProGuard */
    private static class RestrictedSelector implements ExecutorSelector {
        private String mPool;
        private Throwable mTrace;

        @Override // ru.mail.mailbox.cmd.ExecutorSelector
        public CommandExecutor getCommandGroupExecutor() {
            throw new IllegalExecutionPool("Unable to execute command on CommandGroup pool from " + this.mPool + " pool", this.mTrace);
        }

        @Override // ru.mail.mailbox.cmd.ExecutorSelector
        public CommandExecutor getSingleCommandExecutor(String str) {
            if (str.equals(this.mPool)) {
                return new ImmediateExecutor();
            }
            throw new IllegalExecutionPool("Unable to execute command on " + str + " pool from " + this.mPool + " pool", this.mTrace);
        }

        private RestrictedSelector(String str) {
            this.mTrace = new Throwable().fillInStackTrace();
            this.mPool = str;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class SelectorSpy implements ExecutorSelector {
        boolean mIsRestricted;
        String mPool;
        ExecutorSelector mWrapped;

        public SelectorSpy(ExecutorSelector executorSelector) {
            this.mWrapped = executorSelector;
        }

        @Override // ru.mail.mailbox.cmd.ExecutorSelector
        public CommandExecutor getCommandGroupExecutor() {
            return this.mWrapped.getCommandGroupExecutor();
        }

        public String getPool() {
            return this.mPool;
        }

        @Override // ru.mail.mailbox.cmd.ExecutorSelector
        public CommandExecutor getSingleCommandExecutor(String str) {
            this.mIsRestricted = !str.equals("SYNC");
            this.mPool = str;
            return this.mWrapped.getSingleCommandExecutor(str);
        }

        public boolean isRestricted() {
            return this.mIsRestricted;
        }
    }

    public Command(P p10) {
        this.mParams = p10;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        P p10 = this.mParams;
        Object params = ((Command) obj).getParams();
        return p10 == null ? params == null : p10.equals(params);
    }

    public final ObservableFuture<R> execute(ExecutorSelector executorSelector) {
        return execute(executorSelector, Priority.MEDIUM);
    }

    public P getParams() {
        return this.mParams;
    }

    public synchronized R getResult() {
        return this.mResult;
    }

    @NonNull
    protected ReusePolicy getReusePolicy() {
        return new ReusePolicy.ByCommand(this);
    }

    public int hashCode() {
        int iHashCode = getClass().hashCode() * 31;
        P p10 = this.mParams;
        return iHashCode + (p10 != null ? p10.hashCode() : 0);
    }

    public boolean isCancelled() {
        boolean z10;
        synchronized (this) {
            z10 = this.mCancelled;
        }
        return z10;
    }

    @Nullable
    protected abstract R onExecute(ExecutorSelector executorSelector);

    @NonNull
    protected abstract CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector);

    protected void setCancelled(boolean z10) {
        synchronized (this) {
            this.mCancelled = z10;
        }
    }

    protected synchronized void setResult(R r10) {
        this.mResult = r10;
    }

    public final ObservableFuture<R> execute(final ExecutorSelector executorSelector, Priority priority) {
        this.mLog.v("Start execution " + getClass().getSimpleName());
        SelectorSpy selectorSpy = new SelectorSpy(executorSelector);
        CommandExecutor commandExecutorSelectCodeExecutor = selectCodeExecutor(selectorSpy);
        if (selectorSpy.isRestricted()) {
            executorSelector = new RestrictedSelector(selectorSpy.getPool());
        }
        return commandExecutorSelectCodeExecutor.execute(getReusePolicy(), priority, new Callable<R>() { // from class: ru.mail.mailbox.cmd.Command.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public R call() throws Exception {
                if (Command.this.isCancelled()) {
                    return null;
                }
                Command command = Command.this;
                command.setResult(command.onExecute(executorSelector));
                Command.this.onExecutionComplete();
                return (R) Command.this.mResult;
            }
        }).observe(Schedulers.immediate(), new ObservableFuture.Observer<R>() { // from class: ru.mail.mailbox.cmd.Command.1
            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onCancelled() {
                Command.this.setCancelled(true);
                Command.this.onCancelled();
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onDone(R r10) {
                Command.this.setResult(r10);
                Command.this.onDone();
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(Exception exc) {
            }
        });
    }

    protected void onCancelled() {
    }

    protected void onDone() {
    }

    protected void onExecutionComplete() {
    }
}
