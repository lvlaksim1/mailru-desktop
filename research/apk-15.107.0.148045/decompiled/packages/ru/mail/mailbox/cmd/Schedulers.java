package ru.mail.mailbox.cmd;

import android.os.Handler;
import android.os.Looper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.TornadoSendRequest;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\n\u000bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\bH\u0007J\b\u0010\t\u001a\u00020\bH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lru/mail/mailbox/cmd/Schedulers;", "", "<init>", "()V", "mainThread", "Lru/mail/mailbox/cmd/Schedulers$MainThreadScheduler;", "immediateScheduler", "Lru/mail/mailbox/cmd/Schedulers$ImmediateScheduler;", "Lru/mail/mailbox/cmd/Scheduler;", "immediate", "MainThreadScheduler", "ImmediateScheduler", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class Schedulers {

    @NotNull
    public static final Schedulers INSTANCE = new Schedulers();

    @NotNull
    private static final MainThreadScheduler mainThread = new MainThreadScheduler();

    @NotNull
    private static final ImmediateScheduler immediateScheduler = new ImmediateScheduler();

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lru/mail/mailbox/cmd/Schedulers$ImmediateScheduler;", "Lru/mail/mailbox/cmd/Scheduler;", "<init>", "()V", TornadoSendRequest.FIELD_SCHEDULE, "", "run", "Ljava/lang/Runnable;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class ImmediateScheduler implements Scheduler {
        @Override // ru.mail.mailbox.cmd.Scheduler
        public void schedule(@NotNull Runnable run) {
            Intrinsics.checkNotNullParameter(run, "run");
            run.run();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/mailbox/cmd/Schedulers$MainThreadScheduler;", "Lru/mail/mailbox/cmd/Scheduler;", "<init>", "()V", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", TornadoSendRequest.FIELD_SCHEDULE, "", "run", "Ljava/lang/Runnable;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class MainThreadScheduler implements Scheduler {

        @NotNull
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override // ru.mail.mailbox.cmd.Scheduler
        public void schedule(@NotNull Runnable run) {
            Intrinsics.checkNotNullParameter(run, "run");
            this.handler.post(run);
        }
    }

    private Schedulers() {
    }

    @JvmStatic
    @NotNull
    public static final Scheduler immediate() {
        return immediateScheduler;
    }

    @JvmStatic
    @NotNull
    public static final Scheduler mainThread() {
        return mainThread;
    }
}
