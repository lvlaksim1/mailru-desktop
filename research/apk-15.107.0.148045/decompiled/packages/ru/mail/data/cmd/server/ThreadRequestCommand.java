package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.SdkHosts;
import ru.mail.SdkHostsKt;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.server.parser.MailMessageParser;
import ru.mail.data.cmd.server.parser.OrderHistoryParser;
import ru.mail.data.cmd.server.parser.ThreadParser;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThread;
import ru.mail.glasha.domain.managers.FolderGrantsManager;
import ru.mail.logic.cmd.LoadMailsParams;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UncodedHtmlHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "m", "threads", "thread"})
public class ThreadRequestCommand extends ServerCommandBase<Params, Result> {
    private static final String JSON_BODY_KEY = "body";
    private static final String JSON_ERROR_KEY = "error";
    private static final String JSON_MESSAGES_KEY = "messages";
    private static final String JSON_VALUE_KEY = "value";
    private static final Log LOG = Log.getLog("ThreadRequestCommand");
    private static final String PARAM_KEY_FOLDER_ID = "folder";
    private static final String PARAM_KEY_LAST_MODIFIED = "last_modified";
    private static final String PARAM_KEY_LIMIT = "limit";
    private static final String PARAM_KEY_OFFSET = "offset";
    private static final String PARAM_KEY_REFRESH_MAILBOX = "refresh_mailbox";
    private static final String PARAM_KEY_SNIPPET_LIMIT = "snippet_limit";
    private static final String PARAM_KEY_THREAD_ID = "id";
    private static final String THREAD_ID = "thread_id";
    private final MailMessageParser mailMessageParser;
    private final ThreadParser threadParser;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getRefreshMailBoxQueryValue", method = HttpMethod.GET, name = ThreadRequestCommand.PARAM_KEY_REFRESH_MAILBOX, useGetter = true)
        private static final int PARAM_VALUE_REFRESH_MAILBOX = 1;

        @Param(name = ThreadRequestCommand.PARAM_KEY_LIMIT)
        private final int mCount;

        @Param(getterName = "getFolderIdIfNecessary", name = "folder", useGetter = true)
        private String mFolderId;
        private final FolderGrantsManager mGrantsManager;

        @Param(getterName = "getLastModified", name = ThreadRequestCommand.PARAM_KEY_LAST_MODIFIED, useGetter = true)
        private final long mLastModified;

        @Param(name = ThreadRequestCommand.PARAM_KEY_OFFSET)
        private final int mOffset;

        @Param(name = "remove_emoji_opts")
        private final String mRemoveEmojiFlags;
        private final RequestInitiator mRequestInitiator;

        @Param(name = ThreadRequestCommand.PARAM_KEY_SNIPPET_LIMIT)
        private final int mSnippetLimit;

        @Param(name = "id")
        private final String mThreadId;

        public Params(@NotNull LoadMailsParams<String> loadMailsParams, @NotNull DataManager dataManager, int i10, FolderGrantsManager folderGrantsManager) {
            this(loadMailsParams, dataManager, i10, null, folderGrantsManager);
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
            return this.mOffset == params.mOffset && this.mCount == params.mCount && this.mSnippetLimit == params.mSnippetLimit && this.mLastModified == params.mLastModified && this.mThreadId.equals(params.mThreadId) && this.mRemoveEmojiFlags.equals(params.mRemoveEmojiFlags);
        }

        @Keep
        public String getFolderIdIfNecessary() {
            Long lValueOf = Long.valueOf(getFolderState().getFolderId());
            if (this.mGrantsManager.isSharedFolder(lValueOf)) {
                return String.valueOf(lValueOf);
            }
            return null;
        }

        public long getLastModified() {
            if (this.mOffset != 0 || this.mRequestInitiator == RequestInitiator.MANUAL) {
                return 1L;
            }
            return this.mLastModified;
        }

        public Integer getRefreshMailBoxQueryValue() {
            return RequestInitiator.MANUAL.equals(this.mRequestInitiator) ? 1 : null;
        }

        public int getSnippetLimit() {
            return this.mSnippetLimit;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = ((((((((((super.hashCode() * 31) + this.mOffset) * 31) + this.mCount) * 31) + this.mThreadId.hashCode()) * 31) + this.mSnippetLimit) * 31) + this.mRemoveEmojiFlags.hashCode()) * 31;
            long j10 = this.mLastModified;
            return iHashCode + ((int) (j10 ^ (j10 >>> 32)));
        }

        public Params(@NotNull LoadMailsParams<String> loadMailsParams, @NotNull DataManager dataManager, int i10, @Nullable RequestInitiator requestInitiator, FolderGrantsManager folderGrantsManager) {
            super(MailboxContextUtil.getAccountInfo(loadMailsParams.getMailboxContext(), dataManager), MailboxContextUtil.getFolderState(loadMailsParams.getMailboxContext()));
            this.mLastModified = loadMailsParams.getLastModified();
            this.mOffset = loadMailsParams.getOffset();
            this.mCount = loadMailsParams.getLimit();
            this.mThreadId = loadMailsParams.getContainerId();
            this.mSnippetLimit = i10;
            this.mRequestInitiator = requestInitiator;
            this.mRemoveEmojiFlags = dataManager.getRemoveEmojisParam();
            this.mGrantsManager = folderGrantsManager;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result implements RequestMailItemsResult<MailMessage, MailThread> {
        private final long lastModified;
        private final Collection<MailMessage> mMailMessages;
        private final MailThread mMailThread;

        public Result(MailThread mailThread, Collection<MailMessage> collection, long j10) {
            this.mMailThread = mailThread;
            this.mMailMessages = collection;
            this.lastModified = j10;
        }

        @Override // ru.mail.data.cmd.server.RequestItemsResult
        public Collection<MailThread> getContainers() {
            return Collections.singletonList(this.mMailThread);
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsResult
        public long getLastModified() {
            return this.lastModified;
        }

        @Override // ru.mail.data.cmd.server.RequestItemsResult
        public Collection<MailMessage> getMailItems() {
            return this.mMailMessages;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsResult
        public boolean isContainersPartiallyLoaded() {
            return false;
        }
    }

    public ThreadRequestCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    private boolean isColoredTagsOn(Context context) {
        return ConfigurationRepository.from(context).getConfiguration().getColoredTagsConfig().getParseByDefault();
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new UncodedHtmlHostProvider(super.getHostProvider());
    }

    public ThreadRequestCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        boolean zIsColoredTagsOn = isColoredTagsOn(context);
        this.threadParser = new ThreadParser(params.getLogin(), zIsColoredTagsOn);
        this.mailMessageParser = new MailMessageParser(params.getSnippetLimit(), params.getLogin(), zIsColoredTagsOn);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Result>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.ThreadRequestCommand.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return new NetworkCommandStatus.BAD_REQUEST();
                }
                try {
                    if (jSONObject.has("id")) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("id");
                        if (jSONObject2.getString("error").equals("invalid")) {
                            return new MailCommandStatus.INVALID_THREAD(jSONObject2.optString("value"));
                        }
                    }
                    return new NetworkCommandStatus.BAD_REQUEST(jSONObject);
                } catch (JSONException e10) {
                    return new CommandStatus.ERROR(e10);
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        LOG.d("Threads status response: " + response.getRespString());
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString());
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            JSONArray jSONArray = jSONObject2.getJSONArray(JSON_MESSAGES_KEY);
            MailThread mailThread = this.threadParser.parse(jSONObject2);
            String id2 = mailThread.getId();
            if (SdkHostsKt.isOnPrem(SdkHosts.INSTANCE.getSdkHostsInstance())) {
                for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                    JSONObject jSONObject3 = jSONArray.getJSONObject(i10);
                    if (!jSONObject3.has("thread_id")) {
                        jSONObject3.put("thread_id", id2);
                    }
                }
            }
            List<MailMessage> list = this.mailMessageParser.parse(jSONArray);
            new OrderHistoryParser().addStatuses(list, jSONObject2);
            return new Result(mailThread, list, jSONObject.getLong(PARAM_KEY_LAST_MODIFIED));
        } catch (JSONException e10) {
            LOG.e(e10.toString());
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
