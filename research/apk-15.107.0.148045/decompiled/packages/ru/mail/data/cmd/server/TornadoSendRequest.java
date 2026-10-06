package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "send"})
@WithSampling
public class TornadoSendRequest extends PostServerRequest<TornadoSendParams, EmptyResult> {
    public static final String FIELD_ANALYZER_ASSUMPTION = "analyzer_assumption";
    public static final String FIELD_ATTACHES = "attaches";
    public static final String FIELD_ATTACHES_CONTENT_ID = "content_id";
    public static final String FIELD_ATTACHES_ID = "id";
    public static final String FIELD_ATTACHES_LIST = "list";
    public static final String FIELD_ATTACHES_PART_ID = "part_id";
    public static final String FIELD_ATTACHES_TYPE = "type";
    public static final String FIELD_BCC = "bcc";
    public static final String FIELD_BODY = "body";
    public static final String FIELD_BODY_HTML = "html";
    public static final String FIELD_BODY_TEXT = "text";
    public static final String FIELD_CC = "cc";
    public static final String FIELD_CORRESPONDENTS = "correspondents";
    public static final String FIELD_DRAFT = "draft";
    public static final String FIELD_EDITED_CONTACTS = "edited_contacts";
    public static final String FIELD_FORWARD = "forward";
    public static final String FIELD_FROM = "from";
    public static final String FIELD_HAS_ATTACHMENTS = "has_attachments";
    public static final String FIELD_ID = "id";
    public static final String FIELD_PRIORITY = "priority";
    public static final String FIELD_QUOTE = "quote";
    public static final String FIELD_READ_VERIFY = "receipt";
    public static final String FIELD_RECEIPT = "receipt";
    public static final String FIELD_REMIND = "remind";
    public static final String FIELD_REPLY = "reply";
    public static final String FIELD_SCHEDULE = "schedule";
    public static final String FIELD_SEND_DATE = "send_date";
    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SOURCE = "source";
    public static final String FIELD_SUBJECT = "subject";
    public static final String FIELD_TEMPLATE = "template";
    public static final String FIELD_TO = "to";
    public static final String FIELD_TYPE = "type";
    private static final String INTERNAL_ERROR = "internal_error";
    private static final Log LOG = Log.getLog("TornadoSendRequest");
    public static final String TAG_BODY = "body";
    public static final String TAG_ERROR = "error";
    public static final String TAG_INVALID = "invalid";
    public static final int VALUE_PRIORITY_HIGH = 1;
    public static final int VALUE_PRIORITY_LOW = 5;
    public static final int VALUE_PRIORITY_NORMAL = 3;
    public static final String VALUE_TYPE_ATTACH = "attach";
    public static final String VALUE_TYPE_ATTACH_INLINE = "inline";
    public static final String VALUE_TYPE_CLOUD_STOCK_ATTACH = "cloud_stock";
    public static final String VALUE_TYPE_NATURAL = "natural";
    public static final String VALUE_TYPE_NOREPLY = "noreply";

    public TornadoSendRequest(Context context, TornadoSendParams tornadoSendParams, boolean z10) {
        this(context, tornadoSendParams, null, z10);
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<TornadoSendParams, EmptyResult>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<TornadoSendParams, EmptyResult>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.TornadoSendRequest.1
            /* JADX WARN: Multi-variable type inference failed */
            private CommandStatus<?> getCommandStatusOrThrow(JSONObject jSONObject) throws JSONException {
                return (!TornadoSendRequest.INTERNAL_ERROR.equals(jSONObject.getString("body")) || ((TornadoSendParams) TornadoSendRequest.this.getParams()).getBlockQuote() == null) ? super.onBadRequest(jSONObject) : MailCommandStatus.SimpleErrorStatusFactory.FAILED_BACKEND_QUOTE.getStatus(jSONObject);
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                try {
                    return getCommandStatusOrThrow(jSONObject);
                } catch (JSONException unused) {
                    return super.onBadRequest(jSONObject);
                }
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(final NetworkCommand.Response response, ServerApi serverApi, final NetworkCommand<TornadoSendParams, EmptyResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate) { // from class: ru.mail.data.cmd.server.TornadoSendRequest.2
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                CommandStatus<?> commandStatusExecute = new ErrorHandler(new ErrorStringProviderImpl(TornadoSendRequest.this.getContext())).execute(ErrorHandler.EnumErrorHandlerName.TORNADO_SEND_REQUEST, response, networkCommandBaseDelegate);
                if (commandStatusExecute != null) {
                    TornadoSendInlineImagesErrorsAsserter.assertHasNotExistsInlineImages(TornadoSendRequest.this.getContext(), response);
                }
                return commandStatusExecute != null ? commandStatusExecute : super.process();
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NonNull
    protected ServerCommandBase.DefaultTrafficListener onCreateTrafficListener(Context context) {
        return new ServerCommandBase.DefaultTrafficListener(context) { // from class: ru.mail.data.cmd.server.TornadoSendRequest.3
            @Override // ru.mail.serverapi.ServerCommandBase.DefaultTrafficListener, ru.mail.network.NetworkTrafficListener
            public void onTrafficSent(long j10) {
                getTracker().sizeOfNewTxSendViaApi(j10);
            }
        };
    }

    @Override // ru.mail.serverapi.PostServerRequest, ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newJsonFormat("cancellation_token"));
        return logFilterPrepareTokenFilter;
    }

    TornadoSendRequest(Context context, TornadoSendParams tornadoSendParams, HostProvider hostProvider, boolean z10) {
        super(context, tornadoSendParams, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
