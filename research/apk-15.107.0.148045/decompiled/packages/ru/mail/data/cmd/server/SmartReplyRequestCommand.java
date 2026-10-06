package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u0000 \"2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003 !\"B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0014JD\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132&\u0010\u0014\u001a\"0\u0015R\u001e\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u00030\u00030\u0016H\u0014J0\u0010\u0018\u001a\u001a\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c\u0018\u00010\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u000fH\u0002¨\u0006#"}, d2 = {"Lru/mail/data/cmd/server/SmartReplyRequestCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/SmartReplyRequestCommand$Params;", "Lru/mail/data/cmd/server/SmartReplyRequestCommand$SmartResponse;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/SmartReplyRequestCommand$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "handleNotOkResult", "Lru/mail/mailbox/cmd/CommandStatus$OK;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "status", "", "response", "Params", "SmartResponse", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", SmartReplyRequestCommand.TAG_REPLIES, "smart"})
@SourceDebugExtension({"SMAP\nSmartReplyRequestCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SmartReplyRequestCommand.kt\nru/mail/data/cmd/server/SmartReplyRequestCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,143:1\n1563#2:144\n1634#2,3:145\n1#3:148\n*S KotlinDebug\n*F\n+ 1 SmartReplyRequestCommand.kt\nru/mail/data/cmd/server/SmartReplyRequestCommand\n*L\n46#1:144\n46#1:145,3\n*E\n"})
public final class SmartReplyRequestCommand extends PostServerRequest<Params, SmartResponse> {

    @NotNull
    private static final String IS_DEFAULT_REPLIES = "is_default";

    @NotNull
    private static final String TAG_ID = "id";

    @NotNull
    private static final String TAG_REPLIES = "replies";

    @NotNull
    private static final String VALUE_NOT_EXIST = "not_exist";
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MailMessageContent");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/SmartReplyRequestCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "dataManager", "Lru/mail/logic/content/DataManager;", "mId", "", "<init>", "(Lru/mail/logic/content/MailboxContext;Lru/mail/logic/content/DataManager;Ljava/lang/String;)V", "equals", "", "other", "", "hashCode", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "id")
        @Nullable
        private final String mId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String str) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            this.mId = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            boolean z10 = false;
            if (!(other instanceof Params) || !super.equals(other)) {
                return false;
            }
            String str = this.mId;
            String str2 = ((Params) other).mId;
            if (str == null ? str2 != null : !Intrinsics.areEqual(str, str2)) {
                z10 = true;
            }
            return !z10;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mId;
            return iHashCode + (str != null ? str.hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/SmartReplyRequestCommand$SmartResponse;", "", "isDefault", "", SmartReplyRequestCommand.TAG_REPLIES, "", "", "<init>", "(ZLjava/util/List;)V", "()Z", "getReplies", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SmartResponse {
        public static final int $stable = 8;
        private final boolean isDefault;

        @NotNull
        private final List<String> replies;

        public SmartResponse(boolean z10, @NotNull List<String> replies) {
            Intrinsics.checkNotNullParameter(replies, "replies");
            this.isDefault = z10;
            this.replies = replies;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SmartResponse copy$default(SmartResponse smartResponse, boolean z10, List list, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                z10 = smartResponse.isDefault;
            }
            if ((i10 & 2) != 0) {
                list = smartResponse.replies;
            }
            return smartResponse.copy(z10, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsDefault() {
            return this.isDefault;
        }

        @NotNull
        public final List<String> component2() {
            return this.replies;
        }

        @NotNull
        public final SmartResponse copy(boolean isDefault, @NotNull List<String> replies) {
            Intrinsics.checkNotNullParameter(replies, "replies");
            return new SmartResponse(isDefault, replies);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SmartResponse)) {
                return false;
            }
            SmartResponse smartResponse = (SmartResponse) other;
            return this.isDefault == smartResponse.isDefault && Intrinsics.areEqual(this.replies, smartResponse.replies);
        }

        @NotNull
        public final List<String> getReplies() {
            return this.replies;
        }

        public int hashCode() {
            return (Boolean.hashCode(this.isDefault) * 31) + this.replies.hashCode();
        }

        public final boolean isDefault() {
            return this.isDefault;
        }

        @NotNull
        public String toString() {
            return "SmartResponse(isDefault=" + this.isDefault + ", replies=" + this.replies + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SmartReplyRequestCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:22:0x0045  */
    public final CommandStatus.OK<ArrayList<Object>> handleNotOkResult(int status, NetworkCommand.Response response) {
        String string;
        CommandStatus.OK<ArrayList<Object>> ok;
        JSONObject jSONObjectOptJSONObject;
        try {
            JSONObject jSONObjectOptJSONObject2 = new JSONObject(response.getRespString()).optJSONObject("body");
            if (jSONObjectOptJSONObject2 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject("id")) == null) {
                string = "Unknown error";
                ok = null;
            } else {
                if (!jSONObjectOptJSONObject.has("error")) {
                    jSONObjectOptJSONObject = null;
                }
                if (jSONObjectOptJSONObject == null || (string = jSONObjectOptJSONObject.getString("error")) == null) {
                    string = "Unknown error";
                } else if (status == 400 && Intrinsics.areEqual(string, VALUE_NOT_EXIST)) {
                    ok = new CommandStatus.OK<>(new ArrayList());
                }
                ok = null;
            }
            if (ok != null) {
                MailAppDependencies.analytics(getContext()).onLoadSmartReplyError(string, status);
                return ok;
            }
        } catch (JSONException e10) {
            LOG.e("parsing json error", e10);
            string = "parsing json error";
        }
        MailAppDependencies.analytics(getContext()).onLoadSmartReplyError(string, status);
        return null;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@NotNull NetworkCommand.Response resp, @NotNull ServerApi<?> serverApi, @NotNull NetworkCommand<Params, SmartResponse>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(serverApi, "serverApi");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new TornadoResponseProcessor(resp, customDelegate) { // from class: ru.mail.data.cmd.server.SmartReplyRequestCommand.getResponseProcessor.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                int i10;
                if (getResponse().getStatusCode() == 200 && (i10 = Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString()))) != 200) {
                    SmartReplyRequestCommand smartReplyRequestCommand = this;
                    NetworkCommand.Response response = getResponse();
                    Intrinsics.checkNotNullExpressionValue(response, "getResponse(...)");
                    smartReplyRequestCommand.handleNotOkResult(i10, response);
                }
                CommandStatus<?> commandStatusProcess = super.process();
                Intrinsics.checkNotNullExpressionValue(commandStatusProcess, "process(...)");
                return commandStatusProcess;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public SmartResponse onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
            JSONArray jSONArray = jSONObject.getJSONArray(TAG_REPLIES);
            IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
            Iterator<Integer> it = intRangeUntil.iterator();
            while (it.hasNext()) {
                arrayList.add(jSONArray.getString(((IntIterator) it).nextInt()));
            }
            return new SmartResponse(jSONObject.optBoolean("is_default", false), arrayList);
        } catch (JSONException e10) {
            LOG.e("Unable to parse ", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
