package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.ActiveProfileManager;
import ru.mail.data.cmd.server.parser.MailMessageParser;
import ru.mail.data.cmd.server.parser.MailboxFolderParser;
import ru.mail.data.cmd.server.parser.UserGrantsParser;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailMessage;
import ru.mail.glasha.domain.models.business.FolderGrants;
import ru.mail.logic.cmd.LoadMailsParams;
import ru.mail.logic.content.FoldersNotifyer;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.FolderMatcher;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "status"})
public class MessagesStatusCommand extends RequestWithImapActivation<Params, Result> {
    private static final String FORM_SIGN = "form_sign";
    private static final String FORM_TOKEN = "form_token";
    private static final Log LOG = Log.getLog("MessagesStatusCommand");
    private static final String PARAM_KEY_FOLDER = "folder";
    private static final String PARAM_KEY_LAST_MODIFIED = "last_modified";
    private static final String PARAM_KEY_LIMIT = "limit";
    private static final String PARAM_KEY_OFFSET = "offset";
    private static final String PARAM_KEY_PREFETCH = "prefetch";
    private static final String PARAM_KEY_REFRESH_MAILBOX = "refresh_mailbox";
    private static final String PARAM_KEY_SNIPPET_LIMIT = "snippet_limit";
    private static final String PARAM_KEY_SORT = "sort";
    private final FoldersNotifyer mFoldersNotifyer;
    private final boolean mIsUserChild;
    private final MailMessageParser mMailMessageParser;
    private final MailboxFolderParser mMailboxFolderParser;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(getterName = "getPrefetchQueryValue", method = HttpMethod.GET, name = MessagesStatusCommand.PARAM_KEY_PREFETCH, useGetter = true)
        private static final int PARAM_VALUE_PREFETCH = 1;

        @Param(getterName = "getRefreshMailBoxQueryValue", method = HttpMethod.GET, name = MessagesStatusCommand.PARAM_KEY_REFRESH_MAILBOX, useGetter = true)
        private static final int PARAM_VALUE_REFRESH_MAILBOX = 1;

        @Param(method = HttpMethod.GET, name = MessagesStatusCommand.PARAM_KEY_SORT)
        private static final String PARAM_VALUE_SORT = "{\"type\":\"id\", \"order\":\"desc\"}";

        @Param(method = HttpMethod.GET, name = MessagesStatusCommand.PARAM_KEY_LIMIT)
        private final int mCount;

        @Param(method = HttpMethod.GET, name = "folder")
        private final long mFolderId;

        @Param(getterName = "getLastModified", name = MessagesStatusCommand.PARAM_KEY_LAST_MODIFIED, useGetter = true)
        private final long mLastModified;

        @Param(method = HttpMethod.GET, name = MessagesStatusCommand.PARAM_KEY_OFFSET)
        private final int mOffset;
        private final RequestInitiator mRequestInitiator;

        @Param(getterName = "getSnippetLimitQueryValue", method = HttpMethod.GET, name = MessagesStatusCommand.PARAM_KEY_SNIPPET_LIMIT, useGetter = true)
        private final Integer mSnippetLimit;

        public Params(@NotNull LoadMailsParams<Long> loadMailsParams, int i10, @Nullable RequestInitiator requestInitiator, @NotNull ActiveProfileManager activeProfileManager) {
            super(MailboxContextUtil.getAccountInfo(loadMailsParams.getMailboxContext(), activeProfileManager), MailboxContextUtil.getFolderState(loadMailsParams.getMailboxContext()));
            this.mLastModified = loadMailsParams.getLastModified();
            this.mCount = loadMailsParams.getLimit();
            this.mOffset = loadMailsParams.getOffset();
            this.mFolderId = loadMailsParams.getContainerId().longValue();
            this.mSnippetLimit = Integer.valueOf(i10);
            this.mRequestInitiator = requestInitiator;
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
            if (this.mCount != params.mCount || this.mOffset != params.mOffset || this.mFolderId != params.mFolderId || this.mLastModified != params.mLastModified) {
                return false;
            }
            Integer num = this.mSnippetLimit;
            Integer num2 = params.mSnippetLimit;
            return num == null ? num2 == null : num.equals(num2);
        }

        public int getCount() {
            return this.mCount;
        }

        public long getFolderId() {
            return this.mFolderId;
        }

        public long getLastModified() {
            if (this.mOffset == 0) {
                return this.mLastModified;
            }
            return 1L;
        }

        public int getOffset() {
            return this.mOffset;
        }

        public Integer getPrefetchQueryValue() {
            return RequestInitiator.BACKGROUND.equals(this.mRequestInitiator) ? 1 : null;
        }

        public Integer getRefreshMailBoxQueryValue() {
            return RequestInitiator.MANUAL.equals(this.mRequestInitiator) ? 1 : null;
        }

        public int getSnippetLimit() {
            return this.mSnippetLimit.intValue();
        }

        public Integer getSnippetLimitQueryValue() {
            if (this.mSnippetLimit.intValue() == 0) {
                return null;
            }
            return this.mSnippetLimit;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = ((((super.hashCode() * 31) + this.mCount) * 31) + this.mOffset) * 31;
            long j10 = this.mFolderId;
            int i10 = (iHashCode + ((int) (j10 ^ (j10 >>> 32)))) * 31;
            Integer num = this.mSnippetLimit;
            int iHashCode2 = (i10 + (num != null ? num.hashCode() : 0)) * 31;
            long j11 = this.mLastModified;
            return iHashCode2 + ((int) (j11 ^ (j11 >>> 32)));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result implements RequestMailItemsWithExtraResult<MailMessage, MailBoxFolder, FolderGrants, Object> {
        private final List<FolderGrants> folderGrants;
        private final List<MailBoxFolder> folders;
        private final long lastModified;
        private final List<MailMessage> mails;

        public Result(List<MailMessage> list, List<MailBoxFolder> list2, List<FolderGrants> list3, long j10) {
            this.folders = Collections.unmodifiableList(list2);
            this.folderGrants = list3;
            this.mails = Collections.unmodifiableList(list);
            this.lastModified = j10;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsWithExtraResult
        public List<FolderGrants> getContainerExtra() {
            return this.folderGrants;
        }

        @Override // ru.mail.data.cmd.server.RequestItemsResult
        public Collection<MailBoxFolder> getContainers() {
            return this.folders;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsResult
        public long getLastModified() {
            return this.lastModified;
        }

        @Override // ru.mail.data.cmd.server.RequestItemsResult
        public Collection<MailMessage> getMailItems() {
            return this.mails;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsWithExtraResult
        public List<Object> getSecondContainerExtra() {
            return null;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsResult
        public boolean isContainersPartiallyLoaded() {
            return false;
        }
    }

    public MessagesStatusCommand(Context context, Params params, boolean z10, boolean z11, boolean z12, FolderMatcher folderMatcher, FoldersNotifyer foldersNotifyer) {
        this(context, params, null, z10, z11, z12, folderMatcher, foldersNotifyer);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newUrlFormat(FORM_SIGN), Formats.newJsonFormat(FORM_SIGN), Formats.newUrlFormat(FORM_TOKEN), Formats.newJsonFormat(FORM_TOKEN));
        return logFilterPrepareTokenFilter;
    }

    MessagesStatusCommand(Context context, Params params, HostProvider hostProvider, boolean z10, boolean z11, boolean z12, FolderMatcher folderMatcher, FoldersNotifyer foldersNotifyer) {
        super(context, params, hostProvider, z10);
        this.mMailboxFolderParser = new MailboxFolderParser(folderMatcher, params.getLogin(), Collections.EMPTY_SET);
        this.mMailMessageParser = new MailMessageParser(params.getSnippetLimit(), params.getLogin(), z11);
        this.mIsUserChild = z12;
        this.mFoldersNotifyer = foldersNotifyer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.data.cmd.server.RequestWithImapActivation, ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return new RequestWithImapActivation<Params, Result>.ImapActivationDelegate() { // from class: ru.mail.data.cmd.server.MessagesStatusCommand.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.serverapi.ServerCommandBase.ServerCommandBaseDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onFolderAccessDenied() {
                ((Params) MessagesStatusCommand.this.getParams()).getFolderState().clearFolderLogin(((Params) MessagesStatusCommand.this.getParams()).mFolderId);
                return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(((Params) MessagesStatusCommand.this.getParams()).mFolderId));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.data.cmd.server.RequestWithImapActivation
    @NonNull
    public Result onImapActivationOk(JSONObject jSONObject) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            JSONArray jSONArray = jSONObject2.getJSONArray("folders");
            List<MailMessage> list = this.mMailMessageParser.parse(jSONObject2.getJSONArray("messages"));
            LOG.d("parsed messages ----------------------");
            for (MailMessage mailMessage : list) {
                LOG.d(String.format("Message id=%s subject=%s replied=%b forwarded=%b in folder %s", mailMessage.getId(), mailMessage.getSubject(), Boolean.valueOf(mailMessage.isReplied()), Boolean.valueOf(mailMessage.isForwarded()), Long.valueOf(mailMessage.getFolderId())));
            }
            MailboxFolderParser.FoldersParserContainer withGrants = this.mMailboxFolderParser.parseWithGrants(jSONArray, UserGrantsParser.parseFromBody(jSONObject2));
            this.mFoldersNotifyer.notifyFoldersUpdated(withGrants.getFolders(), ((Params) getParams()).getLogin());
            SurelyFoldersChecker surelyFoldersChecker = new SurelyFoldersChecker(withGrants.getFolders(), this.mIsUserChild);
            if (surelyFoldersChecker.isOk()) {
                return new Result(list, withGrants.getFolders(), withGrants.getFolderGrants(), jSONObject.getLong(PARAM_KEY_LAST_MODIFIED));
            }
            LOG.e(surelyFoldersChecker.getErrorString());
            throw new NetworkCommand.PostExecuteException(surelyFoldersChecker.getErrorString());
        } catch (JSONException e10) {
            LOG.e("Cannot parse MessageStatus json: ", e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
