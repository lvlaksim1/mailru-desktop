package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.cmd.server.parser.AttachCloudParser;
import ru.mail.data.cmd.server.parser.AttachCloudStockParser;
import ru.mail.data.cmd.server.parser.AttachLinkParser;
import ru.mail.data.cmd.server.parser.AttachParser;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.AttachCloud;
import ru.mail.data.entities.AttachCloudStock;
import ru.mail.data.entities.AttachLink;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.AccountAndIDParams;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "attaches"})
@WithSampling
public class MessageAttachesRequestCommand extends ServerCommandBase<Params, Result> {
    private static final Log LOG = Log.getLog("MessageAttachesRequestCommand");
    private final boolean mIsCloudStockAvailable;

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final List<AttachLink> mAttachLinks;
        private final List<Attach> mAttachments;
        private final List<AttachCloud> mAttachmentsCloud;
        private final List<AttachCloudStock> mAttachmentsCloudStock;

        public Result(Collection<Attach> collection, Collection<AttachLink> collection2, Collection<AttachCloud> collection3, Collection<AttachCloudStock> collection4) {
            this.mAttachments = new ArrayList(collection);
            this.mAttachLinks = new ArrayList(collection2);
            this.mAttachmentsCloud = new ArrayList(collection3);
            this.mAttachmentsCloudStock = new ArrayList(collection4);
        }

        public List<AttachLink> getAttachLinks() {
            return this.mAttachLinks;
        }

        public List<Attach> getAttachments() {
            return this.mAttachments;
        }

        public List<AttachCloud> getAttachmentsCloud() {
            return this.mAttachmentsCloud;
        }

        public List<AttachCloudStock> getAttachmentsCloudStock() {
            return this.mAttachmentsCloudStock;
        }
    }

    public MessageAttachesRequestCommand(Context context, Params params, boolean z10, boolean z11) {
        super(context, params, z10);
        this.mIsCloudStockAvailable = z11;
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Result>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.MessageAttachesRequestCommand.1
            /* JADX WARN: Code duplicated, block: B:17:0x003b  */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                byte b10;
                try {
                    String key = ThreadDelegateUtil.getKey(jSONObject);
                    JSONObject jSONObject2 = jSONObject.getJSONObject(key);
                    String string = jSONObject2.getString("error");
                    String string2 = jSONObject2.getString("value");
                    int iHashCode = key.hashCode();
                    if (iHashCode != -1268966290) {
                        if (iHashCode == 3355 && key.equals("id")) {
                            b10 = 0;
                        } else {
                            b10 = -1;
                        }
                    } else if (key.equals("folder")) {
                        b10 = 1;
                    } else {
                        b10 = -1;
                    }
                    if (b10 != 0) {
                        if (b10 != 1) {
                            return super.onBadRequest(jSONObject);
                        }
                        if ("not_open".equals(string)) {
                            return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(Long.parseLong(string2)));
                        }
                    } else if ("not_exist".equals(string)) {
                        return new MailCommandStatus.NO_MSG(new AccountAndIDParams(string2, ((Params) MessageAttachesRequestCommand.this.getParams()).getLogin()));
                    }
                    return super.onBadRequest(jSONObject);
                } catch (NumberFormatException e10) {
                    e = e10;
                    return new CommandStatus.ERROR(e);
                } catch (JSONException e11) {
                    e = e11;
                    return new CommandStatus.ERROR(e);
                }
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public String toString() {
        return ((Params) getParams()).toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            JSONArray jSONArray = this.mIsCloudStockAvailable ? jSONObject.getJSONArray("body") : jSONObject.getJSONObject("body").getJSONObject("attaches").getJSONArray("list");
            String login = ((Params) getParams()).getLogin();
            MailMessageContent mailMessageContent = new MailMessageContent();
            mailMessageContent.setAccount(login);
            mailMessageContent.setId(((Params) getParams()).mId);
            mailMessageContent.setFrom(((Params) getParams()).mFrom);
            return new Result(new AttachParser(getContext(), mailMessageContent, login).parse(jSONArray), new AttachLinkParser(getContext(), mailMessageContent).parse(jSONArray), new AttachCloudParser(getContext(), mailMessageContent).parse(jSONArray), new AttachCloudStockParser(getContext(), mailMessageContent).parse(jSONArray));
        } catch (JSONException e10) {
            LOG.e("Unable to parse attaches request", e10);
            throw new NetworkCommand.PostExecuteException("json", e10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getAttachTypes", method = HttpMethod.GET, name = "attach_types", useGetter = true)
        private final String[] mAttachTypes;
        private final String mFrom;

        @Param(method = HttpMethod.GET, name = "id")
        private final String mId;

        public Params(@Nullable String str, @NotNull String[] strArr, @Nullable String str2, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mId = str;
            this.mAttachTypes = (String[]) strArr.clone();
            this.mFrom = str2;
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
            String str = this.mId;
            if (str == null ? params.mId != null : !str.equals(params.mId)) {
                return false;
            }
            if (this.mFrom.equals(params.mFrom)) {
                return Arrays.equals(this.mAttachTypes, params.mAttachTypes);
            }
            return false;
        }

        public String getAttachTypes() {
            if (this.mAttachTypes.length > 0) {
                return new JSONArray((Collection) Arrays.asList(this.mAttachTypes)).toString();
            }
            return null;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            String str = this.mId;
            return ((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.mFrom.hashCode()) * 31) + Arrays.hashCode(this.mAttachTypes);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public String toString() {
            return "Params{super=" + super.toString() + ", mId='" + this.mId + "', mAttachTypes=" + Arrays.toString(this.mAttachTypes) + AbstractJsonLexerKt.END_OBJ;
        }

        public Params(@Nullable String str, @Nullable String str2, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            this(str, new String[0], str2, accountInfo, folderState);
        }
    }
}
