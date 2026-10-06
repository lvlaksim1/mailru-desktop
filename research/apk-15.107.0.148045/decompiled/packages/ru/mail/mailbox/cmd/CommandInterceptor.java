package ru.mail.mailbox.cmd;

import com.google.android.gms.ads.RequestConfiguration;
import com.sun.mail.imap.IMAPStore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u0004:\u0001\u0012J\u001a\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\bH\u0016J/\u0010\t\u001a\u00028\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0010\u0010\u0007\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\u0004\u0018\u00018\u00022\u0006\u0010\u000f\u001a\u00020\u0010H&¢\u0006\u0002\u0010\u0011¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/CommandInterceptor;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "E", "R", "", "beforeExecute", "", IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "afterExecute", "executor", "Lru/mail/mailbox/cmd/CommandInterceptor$Executor;", "result", "(Lru/mail/mailbox/cmd/CommandInterceptor$Executor;Lru/mail/mailbox/cmd/Command;Ljava/lang/Object;)Ljava/lang/Object;", "handleError", "error", "", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "Executor", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CommandInterceptor<T, E, R> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static <T, E, R> void beforeExecute(@NotNull CommandInterceptor<T, E, R> commandInterceptor, @NotNull Command<?, T> command) {
            Intrinsics.checkNotNullParameter(command, "command");
            CommandInterceptor.super.beforeExecute(command);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J%\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0003\u0010\u00032\u0010\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u00030\u0005H&¢\u0006\u0002\u0010\u0006¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/mailbox/cmd/CommandInterceptor$Executor;", "", "executeCommand", RequestConfiguration.MAX_AD_CONTENT_RATING_T, IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "(Lru/mail/mailbox/cmd/Command;)Ljava/lang/Object;", "command_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Executor {
        <T> T executeCommand(@NotNull Command<?, T> command);
    }

    E afterExecute(@NotNull Executor executor, @NotNull Command<?, T> command, T result);

    default void beforeExecute(@NotNull Command<?, T> command) {
        Intrinsics.checkNotNullParameter(command, "command");
    }

    @Nullable
    R handleError(@NotNull Throwable error);
}
