package ru.mail.data.cmd.server;

import android.content.Context;
import android.text.TextUtils;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "services", "category", "change"})
public class ChangeMessageCategoryRequest extends PostServerRequest<Params, CommandStatus> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        private static final String KEY_ADD_FILTER = "add_filter";
        private static final String KEY_CATEGORY = "category";
        private static final String KEY_DROP_CATEGORY = "drop_category";
        private static final String KEY_IDS = "ids";

        @Param(method = HttpMethod.POST, name = KEY_ADD_FILTER)
        private final Boolean mAddFilter;

        @Param(method = HttpMethod.POST, name = "category")
        private final String mCategory;

        @Param(method = HttpMethod.POST, name = KEY_DROP_CATEGORY)
        private final Boolean mDropCategory;

        @Param(getterName = "getMailIds", method = HttpMethod.POST, name = KEY_IDS, useGetter = true)
        private final String mMailId;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable Boolean bool, @Nullable String str, @Nullable Boolean bool2, @Nullable String str2) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mAddFilter = bool;
            this.mCategory = TextUtils.equals(str, "newsletters") ? "newsletter" : str;
            this.mDropCategory = bool2;
            this.mMailId = str2;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            return Objects.equals(this.mAddFilter, params.mAddFilter) && Objects.equals(this.mDropCategory, params.mDropCategory) && Objects.equals(this.mCategory, params.mCategory) && Objects.equals(this.mMailId, params.mMailId);
        }

        public String getMailIds() {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.mMailId);
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mAddFilter, this.mCategory, this.mDropCategory, this.mMailId);
        }
    }

    public ChangeMessageCategoryRequest(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, CommandStatus>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, CommandStatus>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.ChangeMessageCategoryRequest.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                try {
                    if (jSONObject.getJSONObject("ids[0]").getString("error").equals("invalid")) {
                        return new MailCommandStatus.MESSAGE_NOT_EXIST();
                    }
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
                return super.onBadRequest(jSONObject);
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public ChangeMessageCategoryRequest(Context context, Params params, HostProvider hostProvider) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public CommandStatus onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            return (jSONObject.has("status") && jSONObject.getString("status").equals("200")) ? new CommandStatus.OK() : new CommandStatus.ERROR();
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
