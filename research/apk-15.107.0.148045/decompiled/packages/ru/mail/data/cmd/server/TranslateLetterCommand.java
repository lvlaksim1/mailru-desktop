package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001c2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001a\u001b\u001cB/\b\u0007\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0014JF\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152&\u0010\u0016\u001a\"0\u0017R\u001e\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00030\u00030\u0018H\u0014¨\u0006\u001d"}, d2 = {"Lru/mail/data/cmd/server/TranslateLetterCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/TranslateLetterCommand$Params;", "Lru/mail/data/cmd/server/TranslateLetterCommand$Result;", "appContext", "Landroid/content/Context;", "params", "hostProvider", "Lru/mail/network/HostProvider;", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/TranslateLetterCommand$Params;Lru/mail/network/HostProvider;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "Params", "Result", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "utils", "translate"})
public final class TranslateLetterCommand extends PostServerRequest<Params, Result> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("TranslateLetterCommand");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0013\u0010\u0014\u001a\u00020\t2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0010\u0010\u0005\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/data/cmd/server/TranslateLetterCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "query", "", "fromLang", "toLang", Params.QUERY_PARAM_TRANSLATOR, "", Params.QUERY_PARAM_HTML_ENCODED, "", "messageId", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IZLjava/lang/String;Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "getQuery", "()Ljava/lang/String;", "getFromLang", "equals", "other", "", "hashCode", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {

        @NotNull
        private static final String QUERY_PARAM_FROM_LANG = "fromlang";

        @NotNull
        private static final String QUERY_PARAM_HTML_ENCODED = "htmlencoded";

        @NotNull
        private static final String QUERY_PARAM_MESSAGE_ID = "message_id";

        @NotNull
        private static final String QUERY_PARAM_QUERY = "query";

        @NotNull
        private static final String QUERY_PARAM_TO_LANG = "tolang";

        @NotNull
        private static final String QUERY_PARAM_TRANSLATOR = "translator";

        @Param(method = HttpMethod.POST, name = QUERY_PARAM_FROM_LANG)
        @NotNull
        private final String fromLang;

        @Param(method = HttpMethod.POST, name = QUERY_PARAM_HTML_ENCODED)
        private final boolean htmlencoded;

        @Param(method = HttpMethod.POST, name = "message_id")
        @NotNull
        private final String messageId;

        @Param(method = HttpMethod.POST, name = "query")
        @NotNull
        private final String query;

        @Param(method = HttpMethod.POST, name = QUERY_PARAM_TO_LANG)
        @NotNull
        private final String toLang;

        @Param(method = HttpMethod.POST, name = QUERY_PARAM_TRANSLATOR)
        private final int translator;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String query, @NotNull String fromLang, @NotNull String toLang, int i10, boolean z10, @NotNull String messageId, @NotNull AccountInfo accountInfo, @NotNull FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(query, "query");
            Intrinsics.checkNotNullParameter(fromLang, "fromLang");
            Intrinsics.checkNotNullParameter(toLang, "toLang");
            Intrinsics.checkNotNullParameter(messageId, "messageId");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            Intrinsics.checkNotNullParameter(folderState, "folderState");
            this.query = query;
            this.fromLang = fromLang;
            this.toLang = toLang;
            this.translator = i10;
            this.htmlencoded = z10;
            this.messageId = messageId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (other == null || !Intrinsics.areEqual(Params.class, other.getClass()) || !super.equals(other)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.query, params.query) && Intrinsics.areEqual(this.fromLang, params.fromLang) && Intrinsics.areEqual(this.toLang, params.fromLang) && this.translator == params.translator && Intrinsics.areEqual(this.messageId, params.messageId) && this.htmlencoded == params.htmlencoded;
        }

        @NotNull
        public final String getFromLang() {
            return this.fromLang;
        }

        @NotNull
        public final String getQuery() {
            return this.query;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.fromLang, this.toLang, Integer.valueOf(this.translator), this.query, Boolean.valueOf(this.htmlencoded), this.messageId);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/TranslateLetterCommand$Result;", "", "body", "", "<init>", "(Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Result {

        @NotNull
        private final String body;

        public Result(@NotNull String body) {
            Intrinsics.checkNotNullParameter(body, "body");
            this.body = body;
        }

        public static /* synthetic */ Result copy$default(Result result, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = result.body;
            }
            return result.copy(str);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getBody() {
            return this.body;
        }

        @NotNull
        public final Result copy(@NotNull String body) {
            Intrinsics.checkNotNullParameter(body, "body");
            return new Result(body);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Result) && Intrinsics.areEqual(this.body, ((Result) other).body);
        }

        @NotNull
        public final String getBody() {
            return this.body;
        }

        public int hashCode() {
            return this.body.hashCode();
        }

        @NotNull
        public String toString() {
            return "Result(body=" + this.body + ")";
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TranslateLetterCommand(@Nullable Context context, @NotNull Params params, boolean z10) {
        this(context, params, null, z10, 4, null);
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@NotNull NetworkCommand.Response resp, @Nullable ServerApi<?> serverApi, @NotNull NetworkCommand<Params, Result>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new TornadoResponseProcessor(resp, customDelegate);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public TranslateLetterCommand(@Nullable Context context, @NotNull Params params, @Nullable HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            String string = new JSONObject(resp.getRespString()).getString("body");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            LOG.d("Translate Letter Command Completed");
            return new Result(string);
        } catch (JSONException e10) {
            LOG.e("Translate Letter Command Error", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    public /* synthetic */ TranslateLetterCommand(Context context, Params params, HostProvider hostProvider, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, params, (i10 & 4) != 0 ? null : hostProvider, z10);
    }
}
