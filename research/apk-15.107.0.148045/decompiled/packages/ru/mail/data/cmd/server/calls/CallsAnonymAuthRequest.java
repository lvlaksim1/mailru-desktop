package ru.mail.data.cmd.server.calls;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.calleridentification.CallsAuthProvider;
import ru.mail.calleridentification.CallsRepository;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.CompositeCommand;
import ru.mail.serverapi.FolderState;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/data/cmd/server/calls/CallsAnonymAuthRequest;", "Lru/mail/mailbox/cmd/CompositeCommand;", "Lru/mail/mailbox/cmd/CommandStatus;", "", "context", "Landroid/content/Context;", "login", "folderState", "Lru/mail/serverapi/FolderState;", "authProvider", "Lru/mail/calleridentification/CallsAuthProvider;", "<init>", "(Landroid/content/Context;Ljava/lang/String;Lru/mail/serverapi/FolderState;Lru/mail/calleridentification/CallsAuthProvider;)V", "onExecuteComposite", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallsAnonymAuthRequest extends CompositeCommand<CommandStatus<String>> {
    public static final int $stable = 8;

    @NotNull
    private final CallsAuthProvider authProvider;

    @NotNull
    private final Context context;

    @Nullable
    private final FolderState folderState;

    @NotNull
    private final String login;

    public CallsAnonymAuthRequest(@NotNull Context context, @NotNull String login, @Nullable FolderState folderState, @NotNull CallsAuthProvider authProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(authProvider, "authProvider");
        this.context = context;
        this.login = login;
        this.folderState = folderState;
        this.authProvider = authProvider;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CompositeCommand
    @NotNull
    public CommandStatus<String> onExecuteComposite() {
        CallsAuthProvider.AuthStore authStore = this.authProvider.getAuthStore();
        if (!(authStore instanceof CallsAnonymAuthStrategy.AnonAuthStore)) {
            return new CommandStatus.ERROR("Anonymous auth store required");
        }
        CommandStatus commandStatus = (CommandStatus) executeCommand(new CallsAnonymTokenRequest(this.context, new CallsAnonymTokenRequest.Params(this.login, this.folderState)));
        if (!(commandStatus instanceof CommandStatus.OK)) {
            return new CommandStatus.ERROR("Anonymous token was not obtained: " + commandStatus);
        }
        V data = ((CommandStatus.OK) commandStatus).getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type ru.mail.calleridentification.CallsRepository.RequestAnonTokenResult");
        CallsAnonymAuthStrategy.AnonAuthStore anonAuthStore = (CallsAnonymAuthStrategy.AnonAuthStore) authStore;
        anonAuthStore.setAnonToken(((CallsRepository.RequestAnonTokenResult) data).getAnonToken());
        CommandStatus commandStatus2 = (CommandStatus) executeCommand(new CallsCsrfTokenRequest(this.context, new CallsCsrfTokenRequest.Params(this.login, this.folderState), this.authProvider));
        if (commandStatus2 instanceof CommandStatus.OK) {
            V data2 = ((CommandStatus.OK) commandStatus2).getData();
            Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type ru.mail.calleridentification.CallsRepository.RequestCsrfTokenResult");
            anonAuthStore.setCsrfToken(((CallsRepository.RequestCsrfTokenResult) data2).getCsrfToken());
            return new CommandStatus.OK("OK");
        }
        return new CommandStatus.ERROR("CSRF token was not obtained: " + commandStatus2);
    }
}
