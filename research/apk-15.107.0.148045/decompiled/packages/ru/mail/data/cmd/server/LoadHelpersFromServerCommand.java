package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.collection.MutableObjectList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.android_utils.webview.SystemUserAgentProvider;
import ru.mail.config.ConfigurationRepository;
import ru.mail.logic.helpers.DTOHelper;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "helpers"})
public class LoadHelpersFromServerCommand extends ServerCommandBase<ServerCommandEmailParams, List<DTOHelper>> {
    private static final Log LOG = Log.getLog("LoadHelpersFromServerCommand");

    public LoadHelpersFromServerCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10) {
        super(context, serverCommandEmailParams, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    public String getUserAgent() {
        String str;
        return (!ConfigurationRepository.from(getContext()).getConfiguration().isUseSystemUserAgentHelpersUpdate() || (str = SystemUserAgentProvider.get(getContext())) == null) ? super.getUserAgent() : str;
    }

    public LoadHelpersFromServerCommand(Context context, ServerCommandEmailParams serverCommandEmailParams, HostProvider hostProvider, boolean z10) {
        super(context, serverCommandEmailParams, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public List<DTOHelper> onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            if (jSONObject.getInt("status") != 200) {
                LOG.e("Unable to load helpers. Status is no OK.");
                throw new NetworkCommand.PostExecuteException("Unable to load helpers. Status is no OK.");
            }
            JSONArray jSONArray = jSONObject.getJSONArray("body");
            MutableObjectList mutableObjectList = new MutableObjectList();
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
                mutableObjectList.add(new DTOHelper(jSONObject2.getInt("index"), jSONObject2.getBoolean("state"), jSONObject2.getLong("time") * 1000, jSONObject2.getJSONObject("count").getInt("show"), jSONObject2.getJSONObject("count").getInt("close")));
            }
            return mutableObjectList.asMutableList();
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
