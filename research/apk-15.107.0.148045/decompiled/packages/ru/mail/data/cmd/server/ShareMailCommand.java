package ru.mail.data.cmd.server;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.domain.Contact;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "invites", "letter"})
public class ShareMailCommand extends ServerCommandBase<Params, EmptyResult> {
    public static final String INVITE = "invite";

    public ShareMailCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    ShareMailCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getAddresses", method = HttpMethod.GET, name = "addresses", useGetter = true)
        private final List<Contact> contacts;

        @Param(method = HttpMethod.GET, name = TornadoSendRequest.FIELD_TEMPLATE)
        private final String template;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull List<Contact> list, @NotNull String str) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.contacts = list;
            this.template = str;
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
            List<Contact> list = this.contacts;
            if (list == null ? params.contacts != null : !list.equals(params.contacts)) {
                return false;
            }
            String str = this.template;
            String str2 = params.template;
            return str == null ? str2 == null : str.equals(str2);
        }

        public String getAddresses() {
            JSONArray jSONArray = new JSONArray();
            for (Contact contact : this.contacts) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("email", contact.getConnectData());
                    if (!TextUtils.isEmpty(contact.getConnectData())) {
                        String[] strArrSplit = contact.getConnectData().split(StringUtils.SPACE);
                        jSONObject.put(PreferenceHostProvider.URL_PARAM_FIRST, strArrSplit.length > 0 ? strArrSplit[0] : "");
                        jSONObject.put(MailThreadRepresentation.COL_NAME_LAST, strArrSplit.length > 1 ? strArrSplit[1] : "");
                    }
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
                jSONArray.put(jSONObject);
            }
            return jSONArray.toString();
        }

        public String getTemplate() {
            return this.template;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            List<Contact> list = this.contacts;
            int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
            String str = this.template;
            return iHashCode2 + (str != null ? str.hashCode() : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendLocale() {
            return true;
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull List<Contact> list) {
            this(mailboxContext, dataManager, list, ShareMailCommand.INVITE);
        }
    }
}
