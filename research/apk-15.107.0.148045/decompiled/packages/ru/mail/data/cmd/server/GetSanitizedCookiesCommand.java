package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.compose.runtime.internal.StabilityInferred;
import com.sun.mail.imap.IMAPStore;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.cookie.SM;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.deviceinfo.DeviceIdProvider;
import ru.mail.di.AuthDeviceInfoEntryPoint;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.AuthCommandCreator;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.ServerApi;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.AuthorizedCommandImpl;
import ru.mail.util.config.MigrateToPostUtils;
import ru.mail.util.kotlin.cookie.MailCookie;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJA\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\n\b\u0000\u0010\u0012*\u0004\u0018\u00010\u00132\u0012\u0010\u0014\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0012\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014¢\u0006\u0002\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lru/mail/data/cmd/server/GetSanitizedCookiesCommand;", "Lru/mail/serverapi/AuthorizedCommandImpl;", "context", "Landroid/content/Context;", "mailContext", "Lru/mail/logic/content/MailboxContext;", "page", "", "sanitizedCookies", "", "Lru/mail/util/kotlin/cookie/MailCookie;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "<init>", "(Landroid/content/Context;Lru/mail/logic/content/MailboxContext;Ljava/lang/String;Ljava/util/List;Lru/mail/deviceinfo/DeviceIdProvider;)V", "getPage", "()Ljava/lang/String;", "onExecuteCommand", "R", "", IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "priority", "Lru/mail/mailbox/cmd/Priority;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/Priority;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "FollowRedirectCommand", "Result", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetSanitizedCookiesCommand extends AuthorizedCommandImpl {
    public static final int $stable = 8;

    @NotNull
    private final DeviceIdProvider deviceIdProvider;

    @NotNull
    private final String page;

    @NotNull
    private final List<MailCookie> sanitizedCookies;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B5\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0014J\u0018\u0010\u0012\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00140\u0013H\u0014J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0012\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\u0016\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\u0012\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u001bH\u0014J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0014J\u0010\u0010&\u001a\u00020!2\u0006\u0010$\u001a\u00020%H\u0014J\b\u0010'\u001a\u00020\rH\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lru/mail/data/cmd/server/GetSanitizedCookiesCommand$FollowRedirectCommand;", "Lru/mail/network/NetworkCommandWithSession;", "", "Lru/mail/data/cmd/server/GetSanitizedCookiesCommand$Result;", "context", "Landroid/content/Context;", "sanitizeUrl", "sanitizedCookies", "", "Lru/mail/util/kotlin/cookie/MailCookie;", "deviceIdProvider", "Lru/mail/deviceinfo/DeviceIdProvider;", "usePostParams", "", "<init>", "(Landroid/content/Context;Ljava/lang/String;Ljava/util/List;Lru/mail/deviceinfo/DeviceIdProvider;Z)V", "createAuthCommandCreator", "Lru/mail/network/AuthCommandCreator;", "getServerApi", "Lru/mail/network/ServerApi;", "Lru/mail/network/NetworkCommand;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "onPrepareUrl", "Landroid/net/Uri;", "builder", "Landroid/net/Uri$Builder;", "processResponse", "Lru/mail/mailbox/cmd/CommandStatus;", "getHostProvider", "Lru/mail/network/HostProvider;", "onSetupSessionInUrl", "", "url", "setUpSession", "networkService", "Lru/mail/network/service/NetworkService;", "onPrepareConnection", "prepareUrlMigrateToPost", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class FollowRedirectCommand extends NetworkCommandWithSession<String, Result> {
        public static final int $stable = 8;

        @NotNull
        private final DeviceIdProvider deviceIdProvider;

        @NotNull
        private final List<MailCookie> sanitizedCookies;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FollowRedirectCommand(@NotNull Context context, @NotNull String sanitizeUrl, @NotNull List<MailCookie> sanitizedCookies, @NotNull DeviceIdProvider deviceIdProvider, boolean z10) {
            super(context, sanitizeUrl, z10);
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(sanitizeUrl, "sanitizeUrl");
            Intrinsics.checkNotNullParameter(sanitizedCookies, "sanitizedCookies");
            Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
            this.sanitizedCookies = sanitizedCookies;
            this.deviceIdProvider = deviceIdProvider;
        }

        @Override // ru.mail.network.NetworkCommandWithSession
        @NotNull
        protected AuthCommandCreator createAuthCommandCreator() {
            return new SingleRequest.DefaultAuthCommandCreator();
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected HostProvider getHostProvider() {
            Bundle bundle = new Bundle();
            AuthDeviceInfoEntryPoint.Companion companion = AuthDeviceInfoEntryPoint.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            bundle.putSerializable("deviceInfo", companion.authDeviceInfoFactory(context).getDeviceInfo());
            return new PreferenceHostProvider(getContext(), SingleRequest.DefaultServerApi.PREF_KEY, R.string.registration_default_scheme, R.string.registration_default_host, bundle);
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected ServerApi<? extends NetworkCommand<?, ?>> getServerApi() {
            return new SingleRequest.DefaultServerApi();
        }

        @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
        protected void onPrepareConnection(@NotNull NetworkService networkService) throws IOException {
            Intrinsics.checkNotNullParameter(networkService, "networkService");
            networkService.setInstanceFollowRedirects(false);
            super.onPrepareConnection(networkService);
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected Uri onPrepareUrl(@Nullable Uri.Builder builder) {
            Uri uri = Uri.parse(getParams());
            Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
            return uri;
        }

        @Override // ru.mail.network.NetworkCommand
        protected boolean prepareUrlMigrateToPost() {
            return true;
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected CommandStatus<?> processResponse(@Nullable NetworkCommand.Response resp) {
            if (isRedirect(getStatusCode())) {
                List<String> list = getNetworkService().getHeaderFields().get("Set-Cookie");
                if (list != null) {
                    return new CommandStatus.OK(new Result(list));
                }
                MailAppDependencies.analytics(getContext()).logHeaderCookieAbsent();
            } else {
                MailAppDependencies.analytics(getContext()).logNonRedirectStatus(getStatusCode());
            }
            return new CommandStatus.ERROR();
        }

        @Override // ru.mail.network.NetworkCommandWithSession
        protected void setUpSession(@NotNull NetworkService networkService) {
            Intrinsics.checkNotNullParameter(networkService, "networkService");
            networkService.setRequestProperty(SM.COOKIE, CollectionsKt.joinToString$default(CollectionsKt.plus((Collection<? extends MailCookie>) this.sanitizedCookies, new MailCookie("GarageID", this.deviceIdProvider.getUdid(), null, false, false, 28, null)), MailThreadRepresentation.PAYLOAD_DELIM_CHAR, null, null, 0, null, GetSanitizedCookiesCommand$FollowRedirectCommand$setUpSession$1.INSTANCE, 30, null));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.network.NetworkCommand
        @NotNull
        public Result onPostExecuteRequest(@Nullable NetworkCommand.Response resp) {
            throw new IllegalStateException();
        }

        @Override // ru.mail.network.NetworkCommandWithSession
        protected void onSetupSessionInUrl(@Nullable Uri.Builder url) {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lru/mail/data/cmd/server/GetSanitizedCookiesCommand$Result;", "", "cookies", "", "", "<init>", "(Ljava/util/List;)V", "getCookies", "()Ljava/util/List;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Result {
        public static final int $stable = 8;

        @NotNull
        private final List<String> cookies;

        public Result(@NotNull List<String> cookies) {
            Intrinsics.checkNotNullParameter(cookies, "cookies");
            this.cookies = cookies;
        }

        @NotNull
        public final List<String> getCookies() {
            return this.cookies;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetSanitizedCookiesCommand(@NotNull Context context, @NotNull MailboxContext mailContext, @NotNull String page, @NotNull List<MailCookie> sanitizedCookies, @NotNull DeviceIdProvider deviceIdProvider) {
        super(context, MailboxContextUtil.getLogin(mailContext), MailboxContextUtil.getFolderState(mailContext));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mailContext, "mailContext");
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(sanitizedCookies, "sanitizedCookies");
        Intrinsics.checkNotNullParameter(deviceIdProvider, "deviceIdProvider");
        this.page = page;
        this.sanitizedCookies = sanitizedCookies;
        this.deviceIdProvider = deviceIdProvider;
        addCommand(new RequestSanitizeUrlCommand(context, new RequestSanitizeUrlCommand.Params(page, mailContext), MigrateToPostUtils.is12130Enabled(context)));
    }

    @NotNull
    public final String getPage() {
        return this.page;
    }

    @Override // ru.mail.serverapi.AuthorizedCommandImpl, ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <R> R onExecuteCommand(@Nullable Command<?, R> command, @NotNull Priority priority, @Nullable ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        R r10 = (R) super.onExecuteCommand(command, priority, selector);
        if (command instanceof RequestSanitizeUrlCommand) {
            if (NetworkCommand.statusOK(r10)) {
                Context context = getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                String sanitizeUrl = ((RequestSanitizeUrlCommand) command).getOkData().getSanitizeUrl();
                List<MailCookie> list = this.sanitizedCookies;
                DeviceIdProvider deviceIdProvider = this.deviceIdProvider;
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                addCommand(new FollowRedirectCommand(context, sanitizeUrl, list, deviceIdProvider, MigrateToPostUtils.is12169Enabled(context2)));
                return r10;
            }
        } else if (command instanceof FollowRedirectCommand) {
            setResult(r10);
        }
        return r10;
    }
}
