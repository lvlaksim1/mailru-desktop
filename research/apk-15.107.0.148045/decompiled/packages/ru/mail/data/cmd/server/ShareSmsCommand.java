package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import ru.mail.domain.Contact;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.fragments.settings.navigation.SharingPreferenceNavigator;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "sms", "send"})
public class ShareSmsCommand extends ServerCommandBase<Params, EmptyResult> {
    public static final String SHARE_MY_ADDRESS = "share_my_address";

    public ShareSmsCommand(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    ShareSmsCommand(Context context, Params params, HostProvider hostProvider) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getPhones", method = HttpMethod.GET, name = "phones", useGetter = true)
        private final List<Contact> contacts;

        @Param(method = HttpMethod.GET, name = "country")
        private final String country;

        @Param(method = HttpMethod.GET, name = SharingPreferenceNavigator.EXTRA_MSG_TYPE)
        private final String msgType;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull List<Contact> list, @NotNull String str) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.country = Locale.getDefault().getDisplayCountry();
            this.contacts = list;
            this.msgType = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Params) || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            List<Contact> list = this.contacts;
            if (list == null ? params.contacts != null : !list.equals(params.contacts)) {
                return false;
            }
            String str = this.country;
            if (str == null ? params.country != null : !str.equals(params.country)) {
                return false;
            }
            String str2 = this.msgType;
            String str3 = params.msgType;
            return str2 == null ? str3 == null : str2.equals(str3);
        }

        public String getPhones() {
            JSONArray jSONArray = new JSONArray();
            Iterator<Contact> it = this.contacts.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().getConnectData());
            }
            return jSONArray.toString();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            List<Contact> list = this.contacts;
            int iHashCode2 = (iHashCode + (list != null ? list.hashCode() : 0)) * 31;
            String str = this.msgType;
            int iHashCode3 = (iHashCode2 + (str != null ? str.hashCode() : 0)) * 31;
            String str2 = this.country;
            return iHashCode3 + (str2 != null ? str2.hashCode() : 0);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendLocale() {
            return true;
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull List<Contact> list) {
            this(mailboxContext, dataManager, list, ShareSmsCommand.SHARE_MY_ADDRESS);
        }
    }
}
