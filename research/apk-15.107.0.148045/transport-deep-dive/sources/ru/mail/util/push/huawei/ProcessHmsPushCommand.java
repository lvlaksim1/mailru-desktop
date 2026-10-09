package ru.mail.util.push.huawei;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.push.RemoteMessage;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.arbiter.Pools;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.ExecutorSelector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u000eB\u0019\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lru/mail/util/push/huawei/ProcessHmsPushCommand;", "Lru/mail/mailbox/cmd/Command;", "Lru/mail/util/push/huawei/ProcessHmsPushCommand$Params;", "", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/util/push/huawei/ProcessHmsPushCommand$Params;)V", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onExecute", "Params", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProcessHmsPushCommand extends Command<Params, Unit> {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/util/push/huawei/ProcessHmsPushCommand$Params;", "", "remoteMessage", "Lcom/huawei/hms/push/RemoteMessage;", "<init>", "(Lcom/huawei/hms/push/RemoteMessage;)V", "getRemoteMessage", "()Lcom/huawei/hms/push/RemoteMessage;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params {
        public static final int $stable = 8;

        @NotNull
        private final RemoteMessage remoteMessage;

        public Params(@NotNull RemoteMessage remoteMessage) {
            Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
            this.remoteMessage = remoteMessage;
        }

        public static /* synthetic */ Params copy$default(Params params, RemoteMessage remoteMessage, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                remoteMessage = params.remoteMessage;
            }
            return params.copy(remoteMessage);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final RemoteMessage getRemoteMessage() {
            return this.remoteMessage;
        }

        @NotNull
        public final Params copy(@NotNull RemoteMessage remoteMessage) {
            Intrinsics.checkNotNullParameter(remoteMessage, "remoteMessage");
            return new Params(remoteMessage);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Params) && Intrinsics.areEqual(this.remoteMessage, ((Params) other).remoteMessage);
        }

        @NotNull
        public final RemoteMessage getRemoteMessage() {
            return this.remoteMessage;
        }

        public int hashCode() {
            return this.remoteMessage.hashCode();
        }

        @NotNull
        public String toString() {
            return "Params(remoteMessage=" + this.remoteMessage + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProcessHmsPushCommand(@NotNull Context context, @Nullable Params params) {
        super(params);
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @Override // ru.mail.mailbox.cmd.Command
    public /* bridge */ /* synthetic */ Unit onExecute(ExecutorSelector executorSelector) {
        onExecute2(executorSelector);
        return Unit.INSTANCE;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor(Pools.COMPUTATION);
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    /* JADX INFO: renamed from: onExecute, reason: avoid collision after fix types in other method */
    protected void onExecute2(@Nullable ExecutorSelector selector) {
        Context context = this.context;
        Params params = getParams();
        Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
        ProcessHmsPushKt.processHmsPush(context, params);
    }
}
