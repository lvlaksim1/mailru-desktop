package ru.mail.mailbox.cmd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
public abstract class BaseSimultaneousCommandGroup<R> extends Command<Void, R> {
    private static final Log LOG = Log.getLog("SimultaneousCommandGroup");
    private volatile CountDownLatch mAllCompleteGate;
    private final ReadWriteLock mCallbackLock;
    private Scheduler mCallbackScheduler;
    private SingleCommandCallback mCommandCallback;
    private final Map<Command<?, ?>, ResultHolder> mCommandsResults;

    /* JADX INFO: compiled from: ProGuard */
    protected static class ResultHolder {
        private ObservableFuture<?> mFuture;
        private Object mResult;

        ResultHolder(Object obj) {
            setResult(obj);
        }

        public ObservableFuture<?> getFuture() {
            return this.mFuture;
        }

        public Object getResult() {
            return this.mResult;
        }

        public void setFuture(ObservableFuture<?> observableFuture) {
            this.mFuture = observableFuture;
        }

        void setResult(Object obj) {
            this.mResult = obj;
        }

        public String toString() {
            return String.valueOf(this.mResult);
        }
    }

    public BaseSimultaneousCommandGroup() {
        super(null);
        this.mCommandsResults = new ConcurrentHashMap();
        this.mCallbackLock = new ReentrantReadWriteLock();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void onCommandCompleted(final Command<?, T> command, T t10) {
        this.mCallbackLock.readLock().lock();
        try {
            final ResultHolder resultHolder = this.mCommandsResults.get(command);
            if (isCancelled()) {
                t10 = (T) new CommandStatus.CANCELLED();
            }
            resultHolder.setResult(t10);
            this.mAllCompleteGate.countDown();
            if (this.mCommandCallback != null) {
                this.mCallbackScheduler.schedule(new Runnable() { // from class: ru.mail.mailbox.cmd.BaseSimultaneousCommandGroup.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BaseSimultaneousCommandGroup.this.mCommandCallback.onSingleComplete(command, resultHolder.getResult());
                    }
                });
            }
        } finally {
            this.mCallbackLock.readLock().unlock();
        }
    }

    protected final void addSimultaneousCommand(Command<?, ?> command) {
        if (this.mAllCompleteGate != null) {
            throw new IllegalStateException("Can't add command after onExecute has been called");
        }
        this.mCommandsResults.put(command, new ResultHolder(new CommandStatus.NOT_COMPLETED()));
    }

    protected abstract R convertToResult(Map<Command<?, ?>, ResultHolder> map);

    @Override // ru.mail.mailbox.cmd.Command
    protected void onCancelled() {
        super.onCancelled();
        Iterator<ResultHolder> it = this.mCommandsResults.values().iterator();
        while (it.hasNext()) {
            ObservableFuture<?> future = it.next().getFuture();
            if (future != null) {
                future.cancel();
            }
        }
    }

    @Override // ru.mail.mailbox.cmd.Command
    @Nullable
    protected R onExecute(ExecutorSelector executorSelector) {
        this.mAllCompleteGate = new CountDownLatch(this.mCommandsResults.size());
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.mCommandsResults.keySet());
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            onExecuteCommand((Command) it.next(), executorSelector);
        }
        try {
            this.mAllCompleteGate.await();
        } catch (InterruptedException e10) {
            LOG.e(e10.getMessage(), e10);
        }
        return convertToResult(this.mCommandsResults);
    }

    protected <Param, Res> void onExecuteCommand(final Command<Param, Res> command, ExecutorSelector executorSelector) {
        if (isCancelled()) {
            this.mAllCompleteGate.countDown();
            return;
        }
        ObservableFuture<Res> observableFutureExecute = command.execute(executorSelector);
        this.mCommandsResults.get(command).setFuture(observableFutureExecute);
        observableFutureExecute.observe(Schedulers.immediate(), new ObservableFuture.Observer<Res>() { // from class: ru.mail.mailbox.cmd.BaseSimultaneousCommandGroup.1
            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onCancelled() {
                BaseSimultaneousCommandGroup.this.onCommandCompleted(command, null);
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onDone(Res res) {
                BaseSimultaneousCommandGroup.this.onCommandCompleted(command, res);
            }

            @Override // ru.mail.mailbox.cmd.ObservableFuture.Observer
            public void onError(Exception exc) {
                BaseSimultaneousCommandGroup.this.onCommandCompleted(command, null);
            }
        });
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getCommandGroupExecutor();
    }

    public void setSingleCommandCallback(Scheduler scheduler, SingleCommandCallback singleCommandCallback) {
        this.mCallbackLock.writeLock().lock();
        try {
            this.mCallbackScheduler = scheduler;
            this.mCommandCallback = singleCommandCallback;
        } finally {
            this.mCallbackLock.writeLock().unlock();
        }
    }

    public BaseSimultaneousCommandGroup(Command<?, ?>... commandArr) {
        super(null);
        this.mCommandsResults = new ConcurrentHashMap();
        this.mCallbackLock = new ReentrantReadWriteLock();
        for (Command<?, ?> command : commandArr) {
            addSimultaneousCommand(command);
        }
    }
}
