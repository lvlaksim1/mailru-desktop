package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.preference.PreferenceManager;
import com.sun.mail.imap.IMAPStore;
import com.vk.auth.restore.RestoreConstants;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.HostsParser;
import ru.mail.ParseDns;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.sdk.MailSdkHostsParserEntryPoint;
import ru.mail.util.log.Log;
import ru.mail.utils.DynamicHostProviderSharedPreferences;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 &2\u00020\u0001:\u0005\"#$%&BS\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\b\u0002\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0003H\u0002JA\u0010\u0018\u001a\u0004\u0018\u0001H\u0019\"\n\b\u0000\u0010\u0019*\u0004\u0018\u00010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014¢\u0006\u0002\u0010!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup;", "Lru/mail/mailbox/cmd/CommandGroup;", "login", "", "context", "Landroid/content/Context;", "successListener", "Lkotlin/Function0;", "", "errorListener", "shouldLookDns", "", "domain", "hostResolverUrls", "Lru/mail/data/cmd/server/HostResolverUrls;", "<init>", "(Ljava/lang/String;Landroid/content/Context;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLjava/lang/String;Lru/mail/data/cmd/server/HostResolverUrls;)V", "log", "Lru/mail/util/log/Log;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", "isSubdomain", "Ljava/util/concurrent/atomic/AtomicBoolean;", "saveDomain", "onExecuteCommand", "R", "", IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "priority", "Lru/mail/mailbox/cmd/Priority;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/Priority;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "GetDiscoveryHostLinkFromDns", "GetDiscoveryHostFromMyTeamConfig", "GetAndParseAllHosts", "ValidJsonObjectResponseProcessor", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetHostsFromDomainCommandGroup extends CommandGroup {

    @NotNull
    public static final String ON_PREMISE_DOMAIN = "ON_PREMISE_DOMAIN";

    @NotNull
    private final Context context;

    @NotNull
    private final String domain;

    @Nullable
    private final Function0<Unit> errorListener;

    @NotNull
    private final Handler handler;

    @NotNull
    private final HostResolverUrls hostResolverUrls;

    @NotNull
    private AtomicBoolean isSubdomain;

    @NotNull
    private final Log log;
    private final boolean shouldLookDns;

    @Nullable
    private final Function0<Unit> successListener;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\u0003H\u0014J\u0018\u0010\f\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00010\rH\u0014JF\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0001\u0018\u00010\r2\u0018\u0010\u0013\u001a\u0014\u0018\u00010\u0014R\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0014J\u0017\u0010\u0015\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014¢\u0006\u0002\u0010\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup$GetAndParseAllHosts;", "Lru/mail/network/NetworkCommand;", "", "", "domain", "<init>", "(Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup;Ljava/lang/String;)V", "pref", "Lru/mail/utils/DynamicHostProviderSharedPreferences;", "getHostProvider", "Lru/mail/network/HostProvider;", "needPlatformParams", "getServerApi", "Lru/mail/network/ServerApi;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "serverApi", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "onPostExecuteRequest", "(Lru/mail/network/NetworkCommand$Response;)Ljava/lang/Boolean;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGetHostsFromDomainCommandGroup.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetHostsFromDomainCommandGroup.kt\nru/mail/data/cmd/server/GetHostsFromDomainCommandGroup$GetAndParseAllHosts\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,260:1\n216#2,2:261\n*S KotlinDebug\n*F\n+ 1 GetHostsFromDomainCommandGroup.kt\nru/mail/data/cmd/server/GetHostsFromDomainCommandGroup$GetAndParseAllHosts\n*L\n222#1:261,2\n*E\n"})
    private final class GetAndParseAllHosts extends NetworkCommand<String, Boolean> {

        @NotNull
        private final DynamicHostProviderSharedPreferences pref;
        final /* synthetic */ GetHostsFromDomainCommandGroup this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GetAndParseAllHosts(@NotNull GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup, String domain) {
            super(getHostsFromDomainCommandGroup.context, domain);
            Intrinsics.checkNotNullParameter(domain, "domain");
            this.this$0 = getHostsFromDomainCommandGroup;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            this.pref = new DynamicHostProviderSharedPreferences(context);
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected HostProvider getHostProvider() {
            return new HostProvider() { // from class: ru.mail.data.cmd.server.GetHostsFromDomainCommandGroup$GetAndParseAllHosts$getHostProvider$1
                @Override // ru.mail.network.HostProvider
                public Uri.Builder getUrlBuilder() {
                    Uri.Builder builderBuildUpon = Uri.parse(this.this$0.getParams()).buildUpon();
                    Intrinsics.checkNotNullExpressionValue(builderBuildUpon, "buildUpon(...)");
                    return builderBuildUpon;
                }

                @Override // ru.mail.network.HostProvider
                public String getUserAgent() {
                    return "";
                }

                @Override // ru.mail.network.HostProvider
                public void getPlatformSpecificParams(Uri.Builder url) {
                }

                @Override // ru.mail.network.HostProvider
                public void sign(Uri.Builder builder, HostProvider.SignCreator signCreator) {
                }
            };
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<? extends NetworkCommand<?, ?>> serverApi, @Nullable NetworkCommand<String, Boolean>.NetworkCommandBaseDelegate customDelegate) {
            return this.this$0.new ValidJsonObjectResponseProcessor(resp, customDelegate);
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected ServerApi<? extends NetworkCommand<?, ?>> getServerApi() {
            return new SingleRequest.DefaultServerApi();
        }

        @Override // ru.mail.network.NetworkCommand
        protected boolean needPlatformParams() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.network.NetworkCommand
        @NotNull
        public Boolean onPostExecuteRequest(@Nullable NetworkCommand.Response resp) {
            String respString;
            if (resp == null || (respString = resp.getRespString()) == null || StringsKt.isBlank(respString)) {
                return Boolean.FALSE;
            }
            MailSdkHostsParserEntryPoint.Companion companion = MailSdkHostsParserEntryPoint.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            HostsParser hostsParser = companion.hostsParser(context);
            String respString2 = resp.getRespString();
            Intrinsics.checkNotNullExpressionValue(respString2, "getRespString(...)");
            for (Map.Entry<String, String> entry : hostsParser.parse(respString2).entrySet()) {
                this.pref.saveSchemeOrHost(entry.getKey(), entry.getValue());
            }
            MailSdkHostsParserEntryPoint.Companion companion2 = MailSdkHostsParserEntryPoint.INSTANCE;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
            AvatarHostInitializer avatarHostInitializer = companion2.avatarHostInitializer(context2);
            Context context3 = getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
            return Boolean.valueOf(AvatarHostInitializer.initialize$default(avatarHostInitializer, context3, null, 2, null));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0014J\b\u0010\b\u001a\u00020\tH\u0014J\u0018\u0010\n\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00010\u000bH\u0014JF\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0001\u0018\u00010\u000b2\u0018\u0010\u0011\u001a\u0014\u0018\u00010\u0012R\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001H\u0014J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0014¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup$GetDiscoveryHostFromMyTeamConfig;", "Lru/mail/network/NetworkCommand;", "", "domain", "<init>", "(Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup;Ljava/lang/String;)V", "getHostProvider", "Lru/mail/network/HostProvider;", "needPlatformParams", "", "getServerApi", "Lru/mail/network/ServerApi;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "serverApi", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "onPostExecuteRequest", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class GetDiscoveryHostFromMyTeamConfig extends NetworkCommand<String, String> {
        final /* synthetic */ GetHostsFromDomainCommandGroup this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GetDiscoveryHostFromMyTeamConfig(@NotNull GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup, String domain) {
            super(getHostsFromDomainCommandGroup.context, domain);
            Intrinsics.checkNotNullParameter(domain, "domain");
            this.this$0 = getHostsFromDomainCommandGroup;
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected HostProvider getHostProvider() {
            return new HostProvider() { // from class: ru.mail.data.cmd.server.GetHostsFromDomainCommandGroup$GetDiscoveryHostFromMyTeamConfig$getHostProvider$1
                @Override // ru.mail.network.HostProvider
                public Uri.Builder getUrlBuilder() {
                    Uri.Builder builderAppendPath = new Uri.Builder().scheme(RestoreConstants.DEFAULT_URL_SCHEME).authority(this.this$0.getParams()).appendPath("myteam-config.json");
                    Intrinsics.checkNotNullExpressionValue(builderAppendPath, "appendPath(...)");
                    return builderAppendPath;
                }

                @Override // ru.mail.network.HostProvider
                public String getUserAgent() {
                    return "";
                }

                @Override // ru.mail.network.HostProvider
                public void getPlatformSpecificParams(Uri.Builder url) {
                }

                @Override // ru.mail.network.HostProvider
                public void sign(Uri.Builder builder, HostProvider.SignCreator signCreator) {
                }
            };
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<? extends NetworkCommand<?, ?>> serverApi, @Nullable NetworkCommand<String, String>.NetworkCommandBaseDelegate customDelegate) {
            return this.this$0.new ValidJsonObjectResponseProcessor(resp, customDelegate);
        }

        @Override // ru.mail.network.NetworkCommand
        @NotNull
        protected ServerApi<? extends NetworkCommand<?, ?>> getServerApi() {
            return new SingleRequest.DefaultServerApi();
        }

        @Override // ru.mail.network.NetworkCommand
        protected boolean needPlatformParams() {
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.network.NetworkCommand
        @NotNull
        public String onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
            Intrinsics.checkNotNullParameter(resp, "resp");
            try {
                String strOptString = new JSONObject(resp.getRespString()).optString("vk-workmail-discovery-host", "");
                Intrinsics.checkNotNull(strOptString);
                return strOptString;
            } catch (JSONException unused) {
                return "";
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0014J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\tH\u0014¨\u0006\u000b"}, d2 = {"Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup$GetDiscoveryHostLinkFromDns;", "Lru/mail/mailbox/cmd/Command;", "", "domain", "<init>", "(Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup;Ljava/lang/String;)V", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onExecute", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class GetDiscoveryHostLinkFromDns extends Command<String, String> {
        final /* synthetic */ GetHostsFromDomainCommandGroup this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GetDiscoveryHostLinkFromDns(@NotNull GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup, String domain) {
            super(domain);
            Intrinsics.checkNotNullParameter(domain, "domain");
            this.this$0 = getHostsFromDomainCommandGroup;
        }

        @Override // ru.mail.mailbox.cmd.Command
        @NotNull
        protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
            Intrinsics.checkNotNullParameter(selector, "selector");
            CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor("NETWORK");
            Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
            return singleCommandExecutor;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // ru.mail.mailbox.cmd.Command
        @Nullable
        public String onExecute(@NotNull ExecutorSelector selector) {
            Intrinsics.checkNotNullParameter(selector, "selector");
            Regex mainLinkKey = MailSdkHostsParserEntryPoint.INSTANCE.mainLinkKeyProvider(this.this$0.context).getMainLinkKey();
            ParseDns parseDns = new ParseDns();
            String params = getParams();
            Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
            return parseDns.getMainLinkFromDns(params, mainLinkKey);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B'\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0018\u00010\u0005R\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006¢\u0006\u0004\b\u0007\u0010\bJ\f\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0002¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup$ValidJsonObjectResponseProcessor;", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "delegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "<init>", "(Lru/mail/data/cmd/server/GetHostsFromDomainCommandGroup;Lru/mail/network/NetworkCommand$Response;Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;)V", "process", "Lru/mail/mailbox/cmd/CommandStatus;", "isJsonValid", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class ValidJsonObjectResponseProcessor extends ResponseProcessor {
        public ValidJsonObjectResponseProcessor(@Nullable NetworkCommand.Response response, NetworkCommand<?, ?>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
            super(response, networkCommandBaseDelegate);
        }

        private final boolean isJsonValid() {
            try {
                new JSONObject(getResponse().getRespString());
                return true;
            } catch (JSONException unused) {
                return false;
            }
        }

        @Override // ru.mail.network.ResponseProcessor
        @NotNull
        public CommandStatus<?> process() {
            if (getResponse().getStatusCode() == 200 && isJsonValid()) {
                CommandStatus<?> commandStatusOnResponseOk = getDelegate().onResponseOk(getResponse());
                Intrinsics.checkNotNullExpressionValue(commandStatusOnResponseOk, "onResponseOk(...)");
                return commandStatusOnResponseOk;
            }
            CommandStatus<?> commandStatusOnError = getDelegate().onError(getResponse());
            Intrinsics.checkNotNullExpressionValue(commandStatusOnError, "onError(...)");
            return commandStatusOnError;
        }
    }

    public GetHostsFromDomainCommandGroup(@NotNull String login, @NotNull Context context, @Nullable Function0<Unit> function0, @Nullable Function0<Unit> function1, boolean z10, @NotNull String domain, @NotNull HostResolverUrls hostResolverUrls) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(domain, "domain");
        Intrinsics.checkNotNullParameter(hostResolverUrls, "hostResolverUrls");
        this.context = context;
        this.successListener = function0;
        this.errorListener = function1;
        this.shouldLookDns = z10;
        this.domain = domain;
        this.hostResolverUrls = hostResolverUrls;
        Log log = Log.INSTANCE.getLog("GetHostsFromDomainCommandGroup");
        this.log = log;
        this.handler = new Handler(Looper.getMainLooper());
        this.isSubdomain = new AtomicBoolean(false);
        saveDomain(context, login);
        if (!z10) {
            addCommand(new GetAndParseAllHosts(this, hostResolverUrls.discoveryHostUrl()));
        } else {
            addCommand(new GetDiscoveryHostLinkFromDns(this, domain));
            log.d("Start ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExecuteCommand$lambda$0(GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup) {
        Function0<Unit> function0 = getHostsFromDomainCommandGroup.errorListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExecuteCommand$lambda$1(GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup) {
        Function0<Unit> function0 = getHostsFromDomainCommandGroup.errorListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExecuteCommand$lambda$2(GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup) {
        Function0<Unit> function0 = getHostsFromDomainCommandGroup.successListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExecuteCommand$lambda$3(GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup) {
        Function0<Unit> function0 = getHostsFromDomainCommandGroup.errorListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onExecuteCommand$lambda$4(GetHostsFromDomainCommandGroup getHostsFromDomainCommandGroup) {
        Function0<Unit> function0 = getHostsFromDomainCommandGroup.errorListener;
        if (function0 != null) {
            function0.invoke();
        }
    }

    private final void saveDomain(Context context, String domain) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(ON_PREMISE_DOMAIN, domain).apply();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.mailbox.cmd.CommandGroup
    @Nullable
    protected <R> R onExecuteCommand(@Nullable Command<?, R> command, @NotNull Priority priority, @Nullable ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        R r10 = (R) super.onExecuteCommand(command, priority, selector);
        if (command instanceof GetDiscoveryHostLinkFromDns) {
            if (r10 != 0) {
                addCommand(new GetAndParseAllHosts(this, (String) r10));
                return r10;
            }
            if (this.isSubdomain.get()) {
                this.isSubdomain.set(false);
                addCommand(new GetDiscoveryHostFromMyTeamConfig(this, this.domain));
                return r10;
            }
            this.isSubdomain.set(true);
            addCommand(new GetDiscoveryHostLinkFromDns(this, this.hostResolverUrls.subDomainForDnsResolve()));
            return r10;
        }
        if (command instanceof GetDiscoveryHostFromMyTeamConfig) {
            if (r10 instanceof CommandStatus.OK) {
                String str = (String) ((CommandStatus.OK) r10).getData();
                Intrinsics.checkNotNull(str);
                if (str.length() != 0) {
                    addCommand(new GetAndParseAllHosts(this, str));
                    return r10;
                }
                this.handler.post(new Runnable() { // from class: ru.mail.data.cmd.server.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        GetHostsFromDomainCommandGroup.onExecuteCommand$lambda$0(this.f85209a);
                    }
                });
                this.log.e("MyTeam Config doesn't have vk-workmail-discovery-host");
                return r10;
            }
            if (r10 instanceof CommandStatus.ERROR) {
                if (!this.isSubdomain.get()) {
                    this.isSubdomain.set(true);
                    addCommand(new GetDiscoveryHostFromMyTeamConfig(this, this.hostResolverUrls.subdomainForMyTeamConfig()));
                    return r10;
                }
                this.handler.post(new Runnable() { // from class: ru.mail.data.cmd.server.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        GetHostsFromDomainCommandGroup.onExecuteCommand$lambda$1(this.f85210a);
                    }
                });
                this.log.e("Can't get myteam-config.json " + ((CommandStatus.ERROR) r10).getData());
                return r10;
            }
        } else if (command instanceof GetAndParseAllHosts) {
            if (r10 instanceof CommandStatus.OK) {
                if (((Boolean) ((CommandStatus.OK) r10).getData()).booleanValue()) {
                    this.handler.post(new Runnable() { // from class: ru.mail.data.cmd.server.l
                        @Override // java.lang.Runnable
                        public final void run() {
                            GetHostsFromDomainCommandGroup.onExecuteCommand$lambda$2(this.f85211a);
                        }
                    });
                    return r10;
                }
                this.handler.post(new Runnable() { // from class: ru.mail.data.cmd.server.m
                    @Override // java.lang.Runnable
                    public final void run() {
                        GetHostsFromDomainCommandGroup.onExecuteCommand$lambda$3(this.f85212a);
                    }
                });
                this.log.e("Error in parsing job");
                return r10;
            }
            if (r10 instanceof CommandStatus.ERROR) {
                if (!this.shouldLookDns && !this.isSubdomain.get()) {
                    this.isSubdomain.set(true);
                    addCommand(new GetAndParseAllHosts(this, this.hostResolverUrls.discoveryHostSubDomainUrl()));
                    return r10;
                }
                this.handler.post(new Runnable() { // from class: ru.mail.data.cmd.server.n
                    @Override // java.lang.Runnable
                    public final void run() {
                        GetHostsFromDomainCommandGroup.onExecuteCommand$lambda$4(this.f85213a);
                    }
                });
                this.log.e("Can't parse hosts catch error " + ((CommandStatus.ERROR) r10).getData());
            }
        }
        return r10;
    }

    public /* synthetic */ GetHostsFromDomainCommandGroup(String str, Context context, Function0 function0, Function0 function1, boolean z10, String str2, HostResolverUrls hostResolverUrls, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, context, (i10 & 4) != 0 ? null : function0, (i10 & 8) != 0 ? null : function1, z10, str2, hostResolverUrls);
    }
}
