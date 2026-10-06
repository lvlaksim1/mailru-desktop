package ru.mail.data.cmd.server.pusher;

import android.accounts.Account;
import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.MailAccountConstants;
import ru.mail.config.ConfigurationRepository;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.component.PushComponent;
import ru.mail.util.push.model.MultiAccountSettings;
import ru.mail.util.push.pusher.PusherTransport;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 $2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001$B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0014\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u001dH\u0014J\b\u0010\u001e\u001a\u00020\u0013H\u0002J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\n2\u0006\u0010 \u001a\u00020\nH\u0002J\b\u0010!\u001a\u00020\"H\u0014J\b\u0010#\u001a\u00020\rH\u0016R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/data/cmd/server/pusher/SendPushSettingsCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/util/push/model/MultiAccountSettings;", "Lru/mail/mailbox/cmd/EmptyResult;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/util/push/model/MultiAccountSettings;)V", "currentLogin", "", "skippedBadSessionDomains", "", "Lru/mail/network/NoAuthInfo;", "onExecute", "Lru/mail/mailbox/cmd/CommandStatus;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "invalidateExpiredTokens", "", "component", "Lru/mail/util/push/component/PushComponent;", "mapResult", "result", "Lru/mail/util/push/pusher/PusherTransport$Result;", "wrapWithNoAuthInfo", "account", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "fillPushSettings", "peekAuthToken", "login", "isSupportOAuthAuthorization", "", "getNoAuthInfo", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSendPushSettingsCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SendPushSettingsCommand.kt\nru/mail/data/cmd/server/pusher/SendPushSettingsCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,125:1\n1563#2:126\n1634#2,3:127\n*S KotlinDebug\n*F\n+ 1 SendPushSettingsCommand.kt\nru/mail/data/cmd/server/pusher/SendPushSettingsCommand\n*L\n74#1:126\n74#1:127,3\n*E\n"})
public final class SendPushSettingsCommand extends ServerCommandBase<MultiAccountSettings, EmptyResult> {

    @Nullable
    private String currentLogin;

    @NotNull
    private final List<NoAuthInfo> skippedBadSessionDomains;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SendPushSettingsCommand");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SendPushSettingsCommand(@NotNull Context context, @NotNull MultiAccountSettings params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.skippedBadSessionDomains = new ArrayList();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void fillPushSettings() {
        MailAuthorizationApiType apiType = getApiType();
        ArrayList arrayList = new ArrayList();
        for (String str : ((MultiAccountSettings) getParams()).getAccounts()) {
            this.currentLogin = str;
            Intrinsics.checkNotNull(str);
            String strPeekAuthToken = peekAuthToken(str);
            if (strPeekAuthToken == null || StringsKt.isBlank(strPeekAuthToken) || Intrinsics.areEqual(strPeekAuthToken, MailAccountConstants.INVALID_TOKEN)) {
                MailAppDependencies.analytics(getContext()).sendAnalyticTokenAbsentEvent(apiType.name());
                this.skippedBadSessionDomains.add(new NoAuthInfo(str, getAuthCommandCreator(), null));
                arrayList.add(str);
                LOG.w("Remove settings for " + str);
            }
            LOG.i("fillPushSettings settings : " + str);
        }
        ((MultiAccountSettings) getParams()).getAccounts().removeAll(arrayList);
    }

    private final void invalidateExpiredTokens(PushComponent component) {
        PusherTransport transport = component.getTransport();
        for (PushMessagesTransport pushMessagesTransport : component.getPushMessagesTransports()) {
            if (Intrinsics.areEqual(transport.invalidateExpiredToken(pushMessagesTransport), PusherTransport.Result.OK.INSTANCE)) {
                pushMessagesTransport.clearExpiredToken();
            }
        }
    }

    private final CommandStatus<?> mapResult(PusherTransport.Result result) {
        if (Intrinsics.areEqual(result, PusherTransport.Result.OK.INSTANCE)) {
            return new CommandStatus.OK();
        }
        if (result instanceof PusherTransport.Result.NoAuth) {
            MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(getContext());
            if (ConfigurationRepository.from(getContext()).getConfiguration().getUseNewAuthInfo()) {
                mailAppAnalyticsAnalytics.sendAnalyticPushTokenEvent("no_auth_new_no_auth_info");
                return new NetworkCommandStatus.NO_AUTH(getNoAuthInfo());
            }
            String userIdentifier = ((PusherTransport.Result.NoAuth) result).getUserIdentifier();
            mailAppAnalyticsAnalytics.sendAnalyticPushTokenEvent("no_auth");
            return new NetworkCommandStatus.NO_AUTH(getNoAuthInfo(userIdentifier, peekAuthToken(userIdentifier)));
        }
        if (!(result instanceof PusherTransport.Result.NoAuthMultiple)) {
            return new CommandStatus.ERROR();
        }
        List<NoAuthInfo> list = this.skippedBadSessionDomains;
        Collection<String> userIdentifiers = ((PusherTransport.Result.NoAuthMultiple) result).getUserIdentifiers();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(userIdentifiers, 10));
        Iterator<T> it = userIdentifiers.iterator();
        while (it.hasNext()) {
            arrayList.add(wrapWithNoAuthInfo((String) it.next()));
        }
        list.addAll(arrayList);
        return new NetworkCommandStatus.NO_AUTH_MULTIPLE(this.skippedBadSessionDomains);
    }

    private final String peekAuthToken(String login) {
        Account account = new Account(login, BuildConfigVariablesHolder.accountType);
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(getContext().getApplicationContext());
        String tokenType = getTokenType();
        Intrinsics.checkNotNullExpressionValue(tokenType, "getTokenType(...)");
        return accountManagerWrapper.peekAuthToken(account, tokenType);
    }

    private final NoAuthInfo wrapWithNoAuthInfo(String account) {
        return new NoAuthInfo(account, getAuthCommandCreator(), peekAuthToken(account));
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    @NotNull
    public NoAuthInfo getNoAuthInfo() {
        return new NoAuthInfo(this.currentLogin, getAuthCommandCreator(), getAuthToken());
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected boolean isSupportOAuthAuthorization() {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NotNull
    public CommandStatus<?> onExecute(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        fillPushSettings();
        PushComponent pushComponent = (PushComponent) Locator.INSTANCE.locate(getContext(), PushComponent.class);
        invalidateExpiredTokens(pushComponent);
        PusherTransport transport = pushComponent.getTransport();
        P params = getParams();
        Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
        String str = this.currentLogin;
        Intrinsics.checkNotNull(str);
        PusherTransport.Result resultRegisterForPushes = transport.registerForPushes((MultiAccountSettings) params, str);
        LOG.i("Register device for pushes result: " + resultRegisterForPushes);
        return mapResult(resultRegisterForPushes);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        return new EmptyResult();
    }
}
