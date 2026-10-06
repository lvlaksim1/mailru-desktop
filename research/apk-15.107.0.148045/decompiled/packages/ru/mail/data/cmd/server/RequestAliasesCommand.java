package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.collection.MutableObjectList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.Alias;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001:\u0001\u0011B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/RequestAliasesCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/serverapi/ServerCommandEmailParams;", "", "Lru/mail/data/entities/Alias;", "context", "Landroid/content/Context;", "params", "usePost", "", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Companion", "alias_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "aliases"})
public final class RequestAliasesCommand extends ServerCommandBase<ServerCommandEmailParams, List<? extends Alias>> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("RequestAliasesCommand");

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestAliasesCommand(@NotNull Context context, @NotNull ServerCommandEmailParams params, boolean z10) {
        super(context, params, z10);
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
    public List<Alias> onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONArray jSONArray = new JSONObject(resp.getRespString()).getJSONArray("body");
            MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i10);
                Alias.Builder builder = new Alias.Builder();
                String login = getLogin();
                Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
                Alias.Builder account = builder.setAccount(login);
                String string = jSONObject.getString("alias");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                mutableObjectList.add(account.setAlias(string).build());
            }
            LOG.d("Requesting aliases finished, total size: " + mutableObjectList.getSize());
            return mutableObjectList.asMutableList();
        } catch (JSONException e10) {
            LOG.e("Requesting aliases failed!", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
