package ru.mail.mailbox.cmd;

import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\b\u001a\u00020\u0007H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/mailbox/cmd/ExecutorSelectors;", "", "<init>", "()V", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "executorSelector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "defaultSelector", "setDefaultSelector", "", "executorSelectorToSet", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ExecutorSelectors {

    @Nullable
    private static ExecutorSelector executorSelector;

    @NotNull
    public static final ExecutorSelectors INSTANCE = new ExecutorSelectors();

    @NotNull
    private static final ReentrantLock lock = new ReentrantLock();

    private ExecutorSelectors() {
    }

    @JvmStatic
    @NotNull
    public static final ExecutorSelector defaultSelector() {
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            ExecutorSelector executorSelector2 = executorSelector;
            return executorSelector2 == null ? new DefaultExecutorSelector() : executorSelector2;
        } finally {
            reentrantLock.unlock();
        }
    }

    @JvmStatic
    public static final void setDefaultSelector(@NotNull ExecutorSelector executorSelectorToSet) {
        Intrinsics.checkNotNullParameter(executorSelectorToSet, "executorSelectorToSet");
        ReentrantLock reentrantLock = lock;
        reentrantLock.lock();
        try {
            executorSelector = executorSelectorToSet;
            Unit unit = Unit.INSTANCE;
        } finally {
            reentrantLock.unlock();
        }
    }
}
