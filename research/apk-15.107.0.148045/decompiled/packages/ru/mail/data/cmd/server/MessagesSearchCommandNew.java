package ru.mail.data.cmd.server;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.analytics.MetaSearchAnalyticsHolder;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.server.parser.JsonSearchMsgParserNew;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailboxSearch;
import ru.mail.logic.cmd.SearchResult;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 #2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002#$B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0016\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0015JH\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0019\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001a2(\u0010\u001b\u001a$\u0018\u00010\u001cR\u001e\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00030\u00030\u001dH\u0014J\u0018\u0010\u001f\u001a\u00120 R\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\u0014J\b\u0010!\u001a\u00020\"H\u0014¨\u0006%"}, d2 = {"Lru/mail/data/cmd/server/MessagesSearchCommandNew;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/MessagesSearchCommandNew$Params;", "Lru/mail/logic/cmd/SearchResult;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/MessagesSearchCommandNew$Params;Z)V", "isEmptySearchQuery", "onSetupSessionInUrl", "", "url", "Landroid/net/Uri$Builder;", "onExecute", "Lru/mail/mailbox/cmd/CommandStatus;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "getCustomDelegate", "Lru/mail/serverapi/ServerCommandBase$ServerCommandBaseDelegate;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Companion", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/search_new_host", defSchemeStrRes = "string/search_new_default_scheme", prefKey = "search")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "go", "search", "emails"})
@SuppressLint({"NonConstantResourceId"})
@SourceDebugExtension({"SMAP\nMessagesSearchCommandNew.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MessagesSearchCommandNew.kt\nru/mail/data/cmd/server/MessagesSearchCommandNew\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,336:1\n76#2,4:337\n*S KotlinDebug\n*F\n+ 1 MessagesSearchCommandNew.kt\nru/mail/data/cmd/server/MessagesSearchCommandNew\n*L\n102#1:337,4\n*E\n"})
public final class MessagesSearchCommandNew extends ServerCommandBase<Params, SearchResult> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MessagesSearchCommandNew");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/MessagesSearchCommandNew$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "getLOG", "()Lru/mail/util/log/Log;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Log getLOG() {
            return MessagesSearchCommandNew.LOG;
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessagesSearchCommandNew(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isEmptySearchQuery() {
        return TextUtils.isEmpty(((Params) getParams()).getQuery()) && TextUtils.isEmpty(((Params) getParams()).getFilters());
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@NotNull NetworkCommand.Response resp, @Nullable ServerApi<?> serverApi, @Nullable NetworkCommand<Params, SearchResult>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        ServerCommandBase<Params, SearchResult>.ServerCommandBaseDelegate customDelegate2 = getCustomDelegate();
        ConfigurationRepository configurationRepositoryFrom = ConfigurationRepository.from(getContext());
        Intrinsics.checkNotNullExpressionValue(configurationRepositoryFrom, "from(...)");
        return new SearchMailsResponseProcessor(resp, customDelegate2, configurationRepositoryFrom, MailAppDependencies.analytics(getContext()));
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@NotNull Uri.Builder url) {
        Intrinsics.checkNotNullParameter(url, "url");
        url.appendQueryParameter("t", peekAuthToken());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public ServerCommandBase<Params, SearchResult>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, SearchResult>.TornadoDelegate(this) { // from class: ru.mail.data.cmd.server.MessagesSearchCommandNew.getCustomDelegate.1
            {
                super();
            }

            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate
            public String getError(String resp) {
                try {
                    if (resp == null) {
                        resp = "";
                    }
                    String strOptString = new JSONObject(resp).getJSONObject("error").optString("code");
                    Intrinsics.checkNotNull(strOptString);
                    return strOptString;
                } catch (JSONException e10) {
                    return "Error while parsing response status " + e10.getMessage();
                }
            }

            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String resp) {
                try {
                    if (resp == null) {
                        resp = "";
                    }
                    return String.valueOf(new JSONObject(resp).getJSONObject("response").getJSONObject("mail_search_messages").getJSONObject("result").optInt("status"));
                } catch (JSONException e10) {
                    return "Error while parsing response status " + e10.getMessage();
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    @NotNull
    public CommandStatus<?> onExecute(@Nullable ExecutorSelector selector) {
        if (isEmptySearchQuery()) {
            return new CommandStatus.OK(new SearchResult(CollectionsKt.emptyList(), 0, ((Params) getParams()).getSearch()));
        }
        CommandStatus<?> commandStatusOnExecute = super.onExecute(selector);
        Intrinsics.checkNotNull(commandStatusOnExecute);
        return commandStatusOnExecute;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public SearchResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("response").getJSONObject("mail_search_messages").getJSONObject("result").getJSONObject("body");
            JSONObject jSONObject2 = new JSONObject(resp.getRespString()).getJSONObject("response").getJSONObject("mail_search_messages");
            int i10 = jSONObject.getJSONObject("found").getInt("count");
            JSONArray jSONArray = jSONObject.getJSONArray("messages");
            ArrayList arrayList = new ArrayList(jSONArray.length());
            String login = ((Params) getParams()).getLogin();
            if (login == null) {
                login = "";
            }
            JsonSearchMsgParserNew jsonSearchMsgParserNew = new JsonSearchMsgParserNew(login, ((Params) getParams()).getMSnippetLimit(), ((Params) getParams()).isColoredTagsOn());
            MetaSearchAnalyticsHolder metaSearchAnalyticsHolder = MetaSearchAnalyticsHolder.INSTANCE;
            String strOptString = jSONObject2.optString("host");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            metaSearchAnalyticsHolder.setHost(strOptString);
            String strOptString2 = jSONObject2.optString("mruRequestId");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
            metaSearchAnalyticsHolder.setXRequestId(strOptString2);
            metaSearchAnalyticsHolder.setBqid(new JSONObject(resp.getRespString()).optString("qid"));
            metaSearchAnalyticsHolder.setFoundCount(i10);
            Intrinsics.checkNotNull(jSONArray);
            int length = jSONArray.length();
            for (int i11 = 0; i11 < length; i11++) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i11);
                Intrinsics.checkNotNullExpressionValue(jSONObject3, "getJSONObject(...)");
                arrayList.add(jsonSearchMsgParserNew.parse(jSONObject3));
            }
            return new SearchResult(arrayList, i10, ((Params) getParams()).getSearch());
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 32\u00020\u0001:\u00013Bk\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0007HÆ\u0003J\u000b\u0010$\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010&\u001a\u00020\fHÆ\u0003J\u0010\u0010'\u001a\u0004\u0018\u00010\fHÂ\u0003¢\u0006\u0002\u0010(J\u0010\u0010)\u001a\u0004\u0018\u00010\fHÂ\u0003¢\u0006\u0002\u0010(J\u000b\u0010*\u001a\u0004\u0018\u00010\tHÂ\u0003J\t\u0010+\u001a\u00020\u0011HÆ\u0003J|\u0010,\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00072\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0003\u0010\n\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001¢\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020\u00112\b\u0010/\u001a\u0004\u0018\u000100HÖ\u0003J\t\u00101\u001a\u00020\fHÖ\u0001J\t\u00102\u001a\u00020\tHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\r\u001a\u0004\u0018\u00010\f8\u0002X\u0083\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0014\u0010\u000e\u001a\u0004\u0018\u00010\f8\u0002X\u0083\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0012\u0010\u000f\u001a\u0004\u0018\u00010\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010 ¨\u00064"}, d2 = {"Lru/mail/data/cmd/server/MessagesSearchCommandNew$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "dataManager", "Lru/mail/logic/content/DataManager;", "search", "Lru/mail/data/entities/MailboxSearch;", "query", "", "filters", "mSnippetLimit", "", "mOffset", "mLimit", "aqid", "isColoredTagsOn", "", "<init>", "(Lru/mail/logic/content/MailboxContext;Lru/mail/logic/content/DataManager;Lru/mail/data/entities/MailboxSearch;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Z)V", "getMailboxContext", "()Lru/mail/logic/content/MailboxContext;", "getDataManager", "()Lru/mail/logic/content/DataManager;", "getSearch", "()Lru/mail/data/entities/MailboxSearch;", "getQuery", "()Ljava/lang/String;", "getFilters", "getMSnippetLimit", "()I", "Ljava/lang/Integer;", "()Z", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "()Ljava/lang/Integer;", "component8", "component9", "component10", "copy", "(Lru/mail/logic/content/MailboxContext;Lru/mail/logic/content/DataManager;Lru/mail/data/entities/MailboxSearch;Ljava/lang/String;Ljava/lang/String;ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Z)Lru/mail/data/cmd/server/MessagesSearchCommandNew$Params;", "equals", "other", "", "hashCode", "toString", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandEmailParams {

        @NotNull
        private static final String QUERY_PARAM_ATTACH = "attach";

        @NotNull
        private static final String QUERY_PARAM_FLAGGED = "flagged";

        @NotNull
        private static final String QUERY_PARAM_PINNED = "pin";

        @NotNull
        private static final String QUERY_PARAM_UNREAD = "unread";

        @Param(name = "aqid")
        @Nullable
        private final String aqid;

        @NotNull
        private final DataManager dataManager;

        @Param(name = "filters")
        @Nullable
        private final String filters;
        private final boolean isColoredTagsOn;

        @Param(name = "limit")
        @Nullable
        private final Integer mLimit;

        @Param(name = "offset")
        @Nullable
        private final Integer mOffset;

        @Param(name = "snippet_limit")
        private final int mSnippetLimit;

        @NotNull
        private final MailboxContext mailboxContext;

        @Param(name = "q")
        @Nullable
        private final String query;

        @NotNull
        private final MailboxSearch search;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0012\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007J4\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\"\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u0010j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0011`\u0012H\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\n\u001a\u00020\u000bH\u0002J;\u0010\u0015\u001a\u00020\u000e\"\n\b\u0000\u0010\u0016*\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u0001H\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lru/mail/data/cmd/server/MessagesSearchCommandNew$Params$Companion;", "", "<init>", "()V", "QUERY_PARAM_UNREAD", "", "QUERY_PARAM_FLAGGED", "QUERY_PARAM_ATTACH", "QUERY_PARAM_PINNED", "getQueryParameter", "search", "Lru/mail/data/entities/MailboxSearch;", "convertFlagsToJson", "prepareSearchFlags", "", Collector.FLAGS, "Ljava/util/HashMap;", "Lkotlinx/serialization/json/JsonElement;", "Lkotlin/collections/HashMap;", "getDateRange", "Lkotlinx/serialization/json/JsonObject;", "putIfExist", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "body", "Lorg/json/JSONObject;", "key", "value", "converter", "Lru/mail/data/entities/MailboxSearch$QueryParamsConverter;", "(Lorg/json/JSONObject;Ljava/lang/String;Ljava/lang/String;Lru/mail/data/entities/MailboxSearch$QueryParamsConverter;)V", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private final JsonObject getDateRange(MailboxSearch search) {
                MailboxSearch.SearchBeginDate beginDate = search.getBeginDate();
                MailboxSearch.SearchEndDate endDate = search.getEndDate();
                if (beginDate != null && endDate != null) {
                    HashMap map = new HashMap();
                    try {
                        MailboxSearch.DateCalculatorFactory.Default r10 = new MailboxSearch.DateCalculatorFactory.Default();
                        map.put("from", JsonElementKt.JsonPrimitive(Long.valueOf(r10.toTornadoFormat(beginDate))));
                        map.put("to", JsonElementKt.JsonPrimitive(Long.valueOf(r10.toTornadoFormat(endDate))));
                        return new JsonObject(map);
                    } catch (JSONException e10) {
                        MessagesSearchCommandNew.INSTANCE.getLOG().e("Error while convert date range to JSON", e10);
                        return null;
                    }
                }
                MessagesSearchCommandNew.INSTANCE.getLOG().w("Incorrect date range [from:" + beginDate + ", to:" + endDate + "]");
                return null;
            }

            private final void prepareSearchFlags(MailboxSearch search, HashMap<String, JsonElement> flags) {
                String unreadQueryValue = search.getUnreadQueryValue();
                if (unreadQueryValue != null) {
                    flags.put("unread", JsonElementKt.JsonPrimitive(Boolean.valueOf(MailboxSearch.QueryParamsConverter.READ_UNREAD.toTornadoFormat(unreadQueryValue))));
                }
                String flagQueryValue = search.getFlagQueryValue();
                if (flagQueryValue != null) {
                    flags.put("flagged", JsonElementKt.JsonPrimitive(Boolean.valueOf(MailboxSearch.QueryParamsConverter.FLAG_UNFLAG.toTornadoFormat(flagQueryValue))));
                }
                String withAttachmentsQueryValue = search.getWithAttachmentsQueryValue();
                if (withAttachmentsQueryValue != null) {
                    flags.put("attach", JsonElementKt.JsonPrimitive(Boolean.valueOf(MailboxSearch.QueryParamsConverter.WITH_ATTACH_WITHOUT_ATTACH.toTornadoFormat(withAttachmentsQueryValue))));
                }
                String pinQueryValue = search.getPinQueryValue();
                if (pinQueryValue != null) {
                    flags.put("pin", JsonElementKt.JsonPrimitive(Boolean.valueOf(MailboxSearch.QueryParamsConverter.PIN_UNPIN.toTornadoFormat(pinQueryValue))));
                }
            }

            @JvmStatic
            @Nullable
            public final String convertFlagsToJson(@NotNull MailboxSearch search) {
                Long longOrNull;
                Intrinsics.checkNotNullParameter(search, "search");
                try {
                    HashMap map = new HashMap();
                    HashMap<String, JsonElement> map2 = new HashMap<>();
                    prepareSearchFlags(search, map2);
                    if (!map2.isEmpty()) {
                        map.put(Collector.FLAGS, new JsonObject(map2));
                    }
                    if (Intrinsics.areEqual(search.getSearchText(), "*") && !TextUtils.isEmpty(search.getSubject())) {
                        map.put("subject", JsonElementKt.JsonPrimitive(search.getSubject()));
                    } else if ((TextUtils.isEmpty(search.getSearchText()) || Intrinsics.areEqual(search.getSearchText(), "*")) && !TextUtils.isEmpty(search.getFrom())) {
                        map.put("correspondents", new JsonObject(MapsKt.mapOf(TuplesKt.to("from", JsonElementKt.JsonPrimitive(search.getFrom())))));
                    } else if ((TextUtils.isEmpty(search.getSearchText()) || Intrinsics.areEqual(search.getSearchText(), "*")) && !TextUtils.isEmpty(search.getTo())) {
                        map.put("correspondents", new JsonObject(MapsKt.mapOf(TuplesKt.to("to", JsonElementKt.JsonPrimitive(search.getTo())))));
                    } else {
                        map.put("query", JsonElementKt.JsonPrimitive(search.getSearchText()));
                    }
                    JsonObject dateRange = getDateRange(search);
                    if (dateRange != null) {
                        map.put("interval", dateRange);
                    }
                    String folderQueryValue = search.getFolderQueryValue();
                    if (folderQueryValue != null && (longOrNull = StringsKt.toLongOrNull(folderQueryValue)) != null) {
                        map.put("folder", JsonElementKt.JsonPrimitive(Long.valueOf(longOrNull.longValue())));
                    }
                    String transactionCategoryQueryValue = search.getTransactionCategoryQueryValue();
                    if (transactionCategoryQueryValue != null) {
                        map.put("transaction_category", JsonElementKt.JsonPrimitive(transactionCategoryQueryValue));
                    }
                    if (map.isEmpty()) {
                        return null;
                    }
                    return new JsonObject(map).toString();
                } catch (JSONException e10) {
                    MessagesSearchCommandNew.INSTANCE.getLOG().e("Error while convert flags to JSON", e10);
                    return null;
                }
            }

            @JvmStatic
            @Nullable
            public final String getQueryParameter(@NotNull MailboxSearch search) {
                Intrinsics.checkNotNullParameter(search, "search");
                if (Intrinsics.areEqual(search.getSearchText(), "*") && !TextUtils.isEmpty(search.getSubject())) {
                    return search.getSubject();
                }
                if ((TextUtils.isEmpty(search.getSearchText()) || Intrinsics.areEqual(search.getSearchText(), "*")) && TextUtils.isEmpty(search.getFrom())) {
                    return search.getTo();
                }
                if ((TextUtils.isEmpty(search.getSearchText()) || Intrinsics.areEqual(search.getSearchText(), "*")) && TextUtils.isEmpty(search.getTo())) {
                    return search.getFrom();
                }
                if ((TextUtils.isEmpty(search.getSearchText()) || Intrinsics.areEqual(search.getSearchText(), "*")) && TextUtils.isEmpty(search.getSubject())) {
                    return null;
                }
                return search.getSearchText();
            }

            @JvmStatic
            public final <T extends String> void putIfExist(@NotNull JSONObject body, @NotNull String key, @Nullable T value, @NotNull MailboxSearch.QueryParamsConverter converter) throws JSONException {
                Intrinsics.checkNotNullParameter(body, "body");
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(converter, "converter");
                if (value == null) {
                    return;
                }
                body.put(key, converter.toTornadoFormat(value));
            }

            private Companion() {
            }
        }

        public /* synthetic */ Params(MailboxContext mailboxContext, DataManager dataManager, MailboxSearch mailboxSearch, String str, String str2, int i10, Integer num, Integer num2, String str3, boolean z10, int i11, DefaultConstructorMarker defaultConstructorMarker) {
            this(mailboxContext, dataManager, mailboxSearch, (i11 & 8) != 0 ? INSTANCE.getQueryParameter(mailboxSearch) : str, (i11 & 16) != 0 ? INSTANCE.convertFlagsToJson(mailboxSearch) : str2, i10, num, num2, str3, z10);
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        private final Integer getMOffset() {
            return this.mOffset;
        }

        /* JADX INFO: renamed from: component8, reason: from getter */
        private final Integer getMLimit() {
            return this.mLimit;
        }

        /* JADX INFO: renamed from: component9, reason: from getter */
        private final String getAqid() {
            return this.aqid;
        }

        @JvmStatic
        @Nullable
        public static final String convertFlagsToJson(@NotNull MailboxSearch mailboxSearch) {
            return INSTANCE.convertFlagsToJson(mailboxSearch);
        }

        public static /* synthetic */ Params copy$default(Params params, MailboxContext mailboxContext, DataManager dataManager, MailboxSearch mailboxSearch, String str, String str2, int i10, Integer num, Integer num2, String str3, boolean z10, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                mailboxContext = params.mailboxContext;
            }
            if ((i11 & 2) != 0) {
                dataManager = params.dataManager;
            }
            if ((i11 & 4) != 0) {
                mailboxSearch = params.search;
            }
            if ((i11 & 8) != 0) {
                str = params.query;
            }
            if ((i11 & 16) != 0) {
                str2 = params.filters;
            }
            if ((i11 & 32) != 0) {
                i10 = params.mSnippetLimit;
            }
            if ((i11 & 64) != 0) {
                num = params.mOffset;
            }
            if ((i11 & 128) != 0) {
                num2 = params.mLimit;
            }
            if ((i11 & 256) != 0) {
                str3 = params.aqid;
            }
            if ((i11 & 512) != 0) {
                z10 = params.isColoredTagsOn;
            }
            String str4 = str3;
            boolean z11 = z10;
            Integer num3 = num;
            Integer num4 = num2;
            String str5 = str2;
            int i12 = i10;
            return params.copy(mailboxContext, dataManager, mailboxSearch, str, str5, i12, num3, num4, str4, z11);
        }

        @JvmStatic
        @Nullable
        public static final String getQueryParameter(@NotNull MailboxSearch mailboxSearch) {
            return INSTANCE.getQueryParameter(mailboxSearch);
        }

        @JvmStatic
        public static final <T extends String> void putIfExist(@NotNull JSONObject jSONObject, @NotNull String str, @Nullable T t10, @NotNull MailboxSearch.QueryParamsConverter queryParamsConverter) throws JSONException {
            INSTANCE.putIfExist(jSONObject, str, t10, queryParamsConverter);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final MailboxContext getMailboxContext() {
            return this.mailboxContext;
        }

        /* JADX INFO: renamed from: component10, reason: from getter */
        public final boolean getIsColoredTagsOn() {
            return this.isColoredTagsOn;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final DataManager getDataManager() {
            return this.dataManager;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final MailboxSearch getSearch() {
            return this.search;
        }

        @Nullable
        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getQuery() {
            return this.query;
        }

        @Nullable
        /* JADX INFO: renamed from: component5, reason: from getter */
        public final String getFilters() {
            return this.filters;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final int getMSnippetLimit() {
            return this.mSnippetLimit;
        }

        @NotNull
        public final Params copy(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull MailboxSearch search, @Nullable String query, @Nullable String filters, int mSnippetLimit, @Nullable Integer mOffset, @Nullable Integer mLimit, @Nullable String aqid, boolean isColoredTagsOn) {
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            Intrinsics.checkNotNullParameter(search, "search");
            return new Params(mailboxContext, dataManager, search, query, filters, mSnippetLimit, mOffset, mLimit, aqid, isColoredTagsOn);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.mailboxContext, params.mailboxContext) && Intrinsics.areEqual(this.dataManager, params.dataManager) && Intrinsics.areEqual(this.search, params.search) && Intrinsics.areEqual(this.query, params.query) && Intrinsics.areEqual(this.filters, params.filters) && this.mSnippetLimit == params.mSnippetLimit && Intrinsics.areEqual(this.mOffset, params.mOffset) && Intrinsics.areEqual(this.mLimit, params.mLimit) && Intrinsics.areEqual(this.aqid, params.aqid) && this.isColoredTagsOn == params.isColoredTagsOn;
        }

        @NotNull
        public final DataManager getDataManager() {
            return this.dataManager;
        }

        @Nullable
        public final String getFilters() {
            return this.filters;
        }

        public final int getMSnippetLimit() {
            return this.mSnippetLimit;
        }

        @NotNull
        public final MailboxContext getMailboxContext() {
            return this.mailboxContext;
        }

        @Nullable
        public final String getQuery() {
            return this.query;
        }

        @NotNull
        public final MailboxSearch getSearch() {
            return this.search;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = ((((this.mailboxContext.hashCode() * 31) + this.dataManager.hashCode()) * 31) + this.search.hashCode()) * 31;
            String str = this.query;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.filters;
            int iHashCode3 = (((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.mSnippetLimit)) * 31;
            Integer num = this.mOffset;
            int iHashCode4 = (iHashCode3 + (num == null ? 0 : num.hashCode())) * 31;
            Integer num2 = this.mLimit;
            int iHashCode5 = (iHashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
            String str3 = this.aqid;
            return ((iHashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31) + Boolean.hashCode(this.isColoredTagsOn);
        }

        public final boolean isColoredTagsOn() {
            return this.isColoredTagsOn;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(mailboxContext=" + this.mailboxContext + ", dataManager=" + this.dataManager + ", search=" + this.search + ", query=" + this.query + ", filters=" + this.filters + ", mSnippetLimit=" + this.mSnippetLimit + ", mOffset=" + this.mOffset + ", mLimit=" + this.mLimit + ", aqid=" + this.aqid + ", isColoredTagsOn=" + this.isColoredTagsOn + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull MailboxSearch search, @Nullable String str, @Nullable String str2, int i10, @Nullable Integer num, @Nullable Integer num2, @Nullable String str3, boolean z10) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            Intrinsics.checkNotNullParameter(search, "search");
            this.mailboxContext = mailboxContext;
            this.dataManager = dataManager;
            this.search = search;
            this.query = str;
            this.filters = str2;
            this.mSnippetLimit = i10;
            this.mOffset = num;
            this.mLimit = num2;
            this.aqid = str3;
            this.isColoredTagsOn = z10;
        }
    }
}
