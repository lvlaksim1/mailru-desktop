package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.collection.MutableObjectList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Collector;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001:\u0001\u000fB\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0014J\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0014¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/RequestCollectorsCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/serverapi/ServerCommandEmailParams;", "", "Lru/mail/data/entities/Collector;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Companion", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "collectors"})
@SourceDebugExtension({"SMAP\nRequestCollectorsCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestCollectorsCommand.kt\nru/mail/data/cmd/server/RequestCollectorsCommand\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,43:1\n76#2,4:44\n*S KotlinDebug\n*F\n+ 1 RequestCollectorsCommand.kt\nru/mail/data/cmd/server/RequestCollectorsCommand\n*L\n27#1:44,4\n*E\n"})
public final class RequestCollectorsCommand extends PostServerRequest<ServerCommandEmailParams, List<? extends Collector>> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("RequestCollectorsCommand");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestCollectorsCommand(@NotNull Context context, @NotNull ServerCommandEmailParams params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public List<Collector> onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONArray jSONArray = new JSONObject(resp.getRespString()).getJSONArray("body");
            MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
            Intrinsics.checkNotNull(jSONArray);
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
                Collector.Companion companion = Collector.INSTANCE;
                String login = getLogin();
                Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
                mutableObjectList.add(companion.parse(jSONObject, login));
            }
            LOG.d("Requesting collectors finished, total size: " + mutableObjectList.getSize());
            return mutableObjectList.asMutableList();
        } catch (JSONException e10) {
            LOG.e("Requesting collectors failed!", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
