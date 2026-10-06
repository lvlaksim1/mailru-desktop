package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.cmd.server.parser.BetaStateParser;
import ru.mail.logic.betastate.BetaState;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001 B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB!\b\u0011\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\fJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\u0014\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/data/cmd/server/RequestBetaStateCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/RequestBetaStateCommand$Params;", "Lru/mail/logic/betastate/BetaState;", "context", "Landroid/content/Context;", "usePostParams", "", "<init>", "(Landroid/content/Context;Z)V", "provider", "Lru/mail/network/HostProvider;", "(Landroid/content/Context;Lru/mail/network/HostProvider;Z)V", "log", "Lru/mail/util/log/Log;", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onPrepareRequestBody", "Lru/mail/network/requestbody/RequestBody;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "setUpSession", "", "networkService", "Lru/mail/network/service/NetworkService;", "processResponse", "Lru/mail/mailbox/cmd/CommandStatus;", "getTag", "", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {"mobile", "{slot}"})
public final class RequestBetaStateCommand extends ServerCommandBase<Params, BetaState> {
    public static final int $stable = 8;

    @NotNull
    private final Log log;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/data/cmd/server/RequestBetaStateCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", RbParams.Default.URL_PARAM_KEY_SLOT, "", "<init>", "(Ljava/lang/String;)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.URL, name = RbParams.Default.URL_PARAM_KEY_SLOT)
        @NotNull
        private final String slot;

        public Params(@NotNull String slot) {
            Intrinsics.checkNotNullParameter(slot, "slot");
            this.slot = slot;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestBetaStateCommand(@NotNull Context context, boolean z10) {
        super(context, new Params(String.valueOf(BuildConfigVariablesHolder.betaSlot)), new RbHostProvider(context, "beta_status"), z10);
        Intrinsics.checkNotNullParameter(context, "context");
        this.log = Log.INSTANCE.getLog("RequestBetaStateCommand");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected String getTag() {
        return "ad_beta";
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected RequestBody onPrepareRequestBody() {
        List<NameValuePair> listProvidePostParams = providePostParams();
        Intrinsics.checkNotNullExpressionValue(listProvidePostParams, "providePostParams(...)");
        return new MyTargetRequestBodyCreator(this.log).create(listProvidePostParams);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected CommandStatus<?> processResponse(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            return new CommandStatus.OK(onPostExecuteRequest(resp));
        } catch (NetworkCommand.PostExecuteException e10) {
            return new CommandStatus.ERROR(e10);
        }
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor("NETWORK");
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(@NotNull NetworkService networkService) {
        Intrinsics.checkNotNullParameter(networkService, "networkService");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public BetaState onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            return new BetaStateParser().parse(new JSONObject(resp.getRespString()));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @VisibleForTesting
    public RequestBetaStateCommand(@NotNull Context context, @NotNull HostProvider provider, boolean z10) {
        super(context, new Params(String.valueOf(BuildConfigVariablesHolder.betaSlot)), provider, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(provider, "provider");
        this.log = Log.INSTANCE.getLog("RequestBetaStateCommand");
    }
}
