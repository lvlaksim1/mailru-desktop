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
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u001c2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001a\u001b\u001cB/\b\u0007\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0014J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u0014JF\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00152&\u0010\u0016\u001a\"0\u0017R\u001e\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0019*\u0004\u0018\u00010\u00030\u00030\u0018H\u0014¨\u0006\u001d"}, d2 = {"Lru/mail/data/cmd/server/MailCategoryFeedbackCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/MailCategoryFeedbackCommand$Params;", "Lru/mail/data/cmd/server/MailCategoryFeedbackCommand$Result;", "appContext", "Landroid/content/Context;", "params", "hostProvider", "Lru/mail/network/HostProvider;", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/MailCategoryFeedbackCommand$Params;Lru/mail/network/HostProvider;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "Params", "Result", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "services", "category", "feedback"})
public final class MailCategoryFeedbackCommand extends PostServerRequest<Params, Result> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MailCategoryFeedbackCommand");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0005\u001a\u00020\u00068\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/MailCategoryFeedbackCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "id", "", "category", "otherCategory", "", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "getCategory", "()Ljava/lang/String;", "equals", "other", "", "hashCode", "", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {

        @NotNull
        private static final String QUERY_PARAM_CATEGORY = "category";

        @NotNull
        private static final String QUERY_PARAM_MESSAGE_ID = "id";

        @NotNull
        private static final String QUERY_PARAM_OTHER_CATEGORY = "other_category";

        @Param(method = HttpMethod.POST, name = "category")
        @NotNull
        private final String category;

        @Param(method = HttpMethod.POST, name = "id")
        @NotNull
        private final String id;

        @Param(method = HttpMethod.POST, name = QUERY_PARAM_OTHER_CATEGORY)
        private final boolean otherCategory;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String id2, @NotNull String category, boolean z10, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(id2, "id");
            Intrinsics.checkNotNullParameter(category, "category");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            this.id = id2;
            this.category = category;
            this.otherCategory = z10;
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
            return Intrinsics.areEqual(this.id, params.id) && Objects.equals(this.category, params.category) && this.otherCategory == params.otherCategory;
        }

        @NotNull
        public final String getCategory() {
            return this.category;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.id, this.category, Boolean.valueOf(this.otherCategory));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/MailCategoryFeedbackCommand$Result;", "", "body", "", "<init>", "(Ljava/lang/String;)V", "getBody", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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
    public MailCategoryFeedbackCommand(@Nullable Context context, @NotNull Params params, boolean z10) {
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
    public MailCategoryFeedbackCommand(@Nullable Context context, @NotNull Params params, @Nullable HostProvider hostProvider, boolean z10) {
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
            LOG.i("MailCategoryFeedbackCommand Completed");
            return new Result(string);
        } catch (JSONException e10) {
            LOG.e("MailCategoryFeedbackCommand Error", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }

    public /* synthetic */ MailCategoryFeedbackCommand(Context context, Params params, HostProvider hostProvider, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, params, (i10 & 4) != 0 ? null : hostProvider, z10);
    }
}
