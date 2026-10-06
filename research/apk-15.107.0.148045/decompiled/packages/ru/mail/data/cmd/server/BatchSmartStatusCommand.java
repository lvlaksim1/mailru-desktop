package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.ActiveProfileManager;
import ru.mail.data.cmd.server.parser.BoxQuotasParser;
import ru.mail.data.cmd.server.parser.MailboxFolderParser;
import ru.mail.data.cmd.server.parser.PinnedMailsParser;
import ru.mail.data.cmd.server.parser.SmartStatusParser;
import ru.mail.data.cmd.server.parser.UserGrantsParser;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MetaThread;
import ru.mail.data.entities.PinnedMailsVirtualThread;
import ru.mail.glasha.db.entities.UserGrantsDbDto;
import ru.mail.glasha.domain.models.business.FolderGrants;
import ru.mail.logic.cmd.LoadMailsParams;
import ru.mail.logic.content.FoldersNotifyer;
import ru.mail.logic.content.MailListItem;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.mailboxquotas.SharedPrefMailQuotasStorage;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.ui.promosheet.subscriptions.SubscriptionsPromoSheetPrefs;
import ru.mail.util.FolderMatcher;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "m", BatchSmartStatusCommand.JSON_THREADS_KEY, "status", "smart"})
public class BatchSmartStatusCommand<T extends MailListItem<?>> extends RequestWithImapActivation<Params, Result> {
    private static final String JSON_BODY_KEY = "body";
    private static final String JSON_ERROR_KEY = "error";
    private static final String JSON_FOLDERS_CONTENT_KEY = "folders_content";
    private static final String JSON_FOLDERS_KEY = "folders";
    private static final String JSON_FOLDER_ID_KEY = "id";
    private static final String JSON_THREADS_KEY = "threads";
    private static final String JSON_VALUE_KEY = "value";
    private static final String PARAM_KEY_LAST_MODIFIED = "last_modified";
    public static final String THREADS_MODE_ENABLED = "threads_mode_enabled";
    private final FolderMatcher folderMatcher;
    private final FoldersNotifyer foldersNotifyer;
    private final boolean isColoredTagOn;
    private final boolean isSubscriptionsPromoSheetEnabled;
    private final boolean isUserChild;
    private static final Log LOG = Log.getLog("BatchSmartStatusCommand");
    private static final Pattern sErrorPattern = Pattern.compile("folders\\[\\d+]\\.folder");

    /* JADX INFO: compiled from: ProGuard */
    private class NetworkDelegate extends RequestWithImapActivation<Params, Result>.ImapActivationDelegate {
        @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
        public CommandStatus<?> onBadRequest(JSONObject jSONObject) {
            if (jSONObject != null) {
                try {
                    Iterator<String> itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (BatchSmartStatusCommand.sErrorPattern.matcher(next).matches()) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject(next);
                            String string = jSONObject2.getString("error");
                            if (string.equals("not_exists")) {
                                return new MailCommandStatus.ERROR_FOLDER_NOT_EXIST(0L);
                            }
                            if (string.equals("not_open")) {
                                return new NetworkCommandStatus.FOLDER_ACCESS_DENIED(Long.valueOf(jSONObject2.getLong("value")));
                            }
                        }
                    }
                    return new NetworkCommandStatus.BAD_REQUEST(jSONObject);
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
            }
            return new NetworkCommandStatus.BAD_REQUEST(null);
        }

        private NetworkDelegate() {
            super();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {

        @Param(name = "metathread_subjects")
        private static final boolean mMetathreadSubjects = true;

        @Param(getterName = "getFolders", name = "folders", useGetter = true)
        private final List<Folder> mFolders;

        @Param(name = BatchSmartStatusCommand.PARAM_KEY_LAST_MODIFIED)
        private final long mLastModified;

        @Nullable
        @Param(name = "refresh_mailbox")
        private final Integer mRefreshMailbox;

        @Param(name = "remove_emoji_opts")
        private final String mRemoveEmojiFlags;

        @Param(name = "reset_nc")
        private final boolean mResetNewEmailsCount;

        @Param(getterName = "getSnippetLimitValue", name = "snippet_limit", useGetter = true)
        private final int mSnippetLimit;

        @Param(name = "u_known")
        private final boolean mUserKnown;

        /* JADX INFO: compiled from: ProGuard */
        public static class Folder {
            public Long mFolderId;
            public int mLimit;
            public int mOffset;

            public Folder(Long l10, int i10, int i11) {
                this.mFolderId = l10;
                this.mOffset = i10;
                this.mLimit = i11;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && getClass() == obj.getClass()) {
                    Folder folder = (Folder) obj;
                    if (this.mOffset == folder.mOffset && this.mLimit == folder.mLimit && Objects.equals(this.mFolderId, folder.mFolderId)) {
                        return true;
                    }
                }
                return false;
            }

            public Long getFolderId() {
                return this.mFolderId;
            }

            public int getLimit() {
                return this.mLimit;
            }

            public int getOffset() {
                return this.mOffset;
            }

            public int hashCode() {
                return Objects.hash(this.mFolderId, Integer.valueOf(this.mOffset), Integer.valueOf(this.mLimit));
            }

            public String toString() {
                return "Folder{mFolderId=" + this.mFolderId + ", mOffset=" + this.mOffset + ", mLimit=" + this.mLimit + AbstractJsonLexerKt.END_OBJ;
            }
        }

        public Params(@NotNull LoadMailsParams<Long> loadMailsParams, @NotNull ActiveProfileManager activeProfileManager, @NotNull List<Folder> list, int i10, @Nullable RequestInitiator requestInitiator, String str) {
            super(MailboxContextUtil.getAccountInfo(loadMailsParams.getMailboxContext(), activeProfileManager), MailboxContextUtil.getFolderState(loadMailsParams.getMailboxContext()));
            this.mLastModified = prepareLastModified(loadMailsParams);
            this.mSnippetLimit = i10;
            this.mRefreshMailbox = prepareRefreshMailbox(requestInitiator);
            this.mUserKnown = loadMailsParams.isUserKnown();
            this.mResetNewEmailsCount = loadMailsParams.shouldResetNewEmailsCounter();
            this.mFolders = list;
            this.mRemoveEmojiFlags = str;
        }

        private long prepareLastModified(LoadMailsParams<Long> loadMailsParams) {
            if (loadMailsParams.getOffset() == 0) {
                return loadMailsParams.getLastModified();
            }
            return 1L;
        }

        @Nullable
        private Integer prepareRefreshMailbox(RequestInitiator requestInitiator) {
            return RequestInitiator.MANUAL.equals(requestInitiator) ? 1 : null;
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
            return this.mSnippetLimit == params.mSnippetLimit && this.mLastModified == params.mLastModified && this.mUserKnown == params.mUserKnown && this.mResetNewEmailsCount == params.mResetNewEmailsCount && Objects.equals(this.mRefreshMailbox, params.mRefreshMailbox) && Objects.equals(this.mFolders, params.mFolders) && Objects.equals(this.mRemoveEmojiFlags, params.mRemoveEmojiFlags);
        }

        public String getFolders() {
            JSONArray jSONArray = new JSONArray();
            for (Folder folder : this.mFolders) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("folder", folder.getFolderId());
                    jSONObject.put("offset", folder.getOffset());
                    jSONObject.put("limit", folder.getLimit());
                    jSONArray.put(jSONObject);
                } catch (JSONException e10) {
                    e10.printStackTrace();
                }
            }
            return jSONArray.toString();
        }

        public int getSnippetLimit() {
            return this.mSnippetLimit;
        }

        @Nullable
        public Integer getSnippetLimitValue() {
            int i10 = this.mSnippetLimit;
            if (i10 == 0) {
                return null;
            }
            return Integer.valueOf(i10);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mRefreshMailbox, this.mFolders, Integer.valueOf(this.mSnippetLimit), Long.valueOf(this.mLastModified), Boolean.valueOf(this.mUserKnown), Boolean.valueOf(this.mResetNewEmailsCount), this.mRemoveEmojiFlags);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public String toString() {
            return "Params{mRefreshMailbox=" + this.mRefreshMailbox + ", mFolders=" + this.mFolders + ", mSnippetLimit=" + this.mSnippetLimit + ", mLastModified=" + this.mLastModified + ", mUserKnown=" + this.mUserKnown + ", mResetNewEmailsCount=" + this.mResetNewEmailsCount + ", mRemoveEmojiFlags=" + this.mRemoveEmojiFlags + AbstractJsonLexerKt.END_OBJ;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result<T> implements RequestMailItemsWithExtraResult<T, MailBoxFolder, FolderGrants, UserGrantsDbDto> {
        private final List<FolderGrants> mFolderGrants;
        private final Map<Long, FoldersContent> mFoldersContent;
        private final Collection<T> mItems;
        private final long mLastModified;
        private final Collection<MailBoxFolder> mMailBoxFolders;

        @Nullable
        private final PinnedMailsVirtualThread mPinnedThread;
        private final List<UserGrantsDbDto> mUserGrants;

        /* JADX INFO: compiled from: ProGuard */
        public static class FoldersContent<T> implements RequestMailItemsResult<T, MailBoxFolder> {
            private final long mFolderId;
            private final Collection<T> mItems;
            private final long mLastModified;
            private final Collection<MailBoxFolder> mMailBoxFolders;
            private final Collection<MailMessage> mMailMessages;
            private final Collection<MailThread> mMailThreads;
            private final Collection<MetaThread> mMetaThreads;
            private final Collection<MailListItem<?>> mOrderedItems;
            private final boolean mThreadsEnabled;

            public FoldersContent(long j10, Collection<T> collection, Collection<MetaThread> collection2, Collection<MailMessage> collection3, Collection<MailThread> collection4, Collection<MailListItem<?>> collection5, Collection<MailBoxFolder> collection6, boolean z10, long j11) {
                this.mFolderId = j10;
                this.mItems = collection;
                this.mMetaThreads = collection2;
                this.mMailMessages = collection3;
                this.mMailThreads = collection4;
                this.mOrderedItems = collection5;
                this.mMailBoxFolders = collection6;
                this.mThreadsEnabled = z10;
                this.mLastModified = j11;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj != null && getClass() == obj.getClass()) {
                    FoldersContent foldersContent = (FoldersContent) obj;
                    if (this.mFolderId == foldersContent.mFolderId && this.mThreadsEnabled == foldersContent.mThreadsEnabled && this.mLastModified == foldersContent.mLastModified && Objects.equals(this.mItems, foldersContent.mItems) && Objects.equals(this.mMetaThreads, foldersContent.mMetaThreads) && Objects.equals(this.mMailMessages, foldersContent.mMailMessages) && Objects.equals(this.mMailThreads, foldersContent.mMailThreads)) {
                        return true;
                    }
                }
                return false;
            }

            @Override // ru.mail.data.cmd.server.RequestItemsResult
            public Collection<MailBoxFolder> getContainers() {
                return this.mMailBoxFolders;
            }

            public long getFolderId() {
                return this.mFolderId;
            }

            @Override // ru.mail.data.cmd.server.RequestMailItemsResult
            public long getLastModified() {
                return this.mLastModified;
            }

            @Override // ru.mail.data.cmd.server.RequestItemsResult
            public Collection<T> getMailItems() {
                return this.mItems;
            }

            public Collection<MailMessage> getMailMessages() {
                return this.mMailMessages;
            }

            public Collection<MailThread> getMailThreads() {
                return this.mMailThreads;
            }

            public Collection<MetaThread> getMetaThreads() {
                return this.mMetaThreads;
            }

            public Collection<MailListItem<?>> getOrderedItems() {
                return this.mOrderedItems;
            }

            public int hashCode() {
                return Objects.hash(Long.valueOf(this.mFolderId), this.mItems, this.mMetaThreads, this.mMailMessages, this.mMailThreads, Boolean.valueOf(this.mThreadsEnabled), Long.valueOf(this.mLastModified));
            }

            @Override // ru.mail.data.cmd.server.RequestMailItemsResult
            public boolean isContainersPartiallyLoaded() {
                return false;
            }

            public boolean isThreadsEnabled() {
                return this.mThreadsEnabled;
            }
        }

        public Result(Map<Long, FoldersContent> map, Collection<MailBoxFolder> collection, List<FolderGrants> list, long j10, Collection<T> collection2, List<UserGrantsDbDto> list2, @Nullable PinnedMailsVirtualThread pinnedMailsVirtualThread) {
            this.mFoldersContent = map;
            this.mMailBoxFolders = collection;
            this.mFolderGrants = list;
            this.mLastModified = j10;
            this.mItems = collection2;
            this.mUserGrants = list2;
            this.mPinnedThread = pinnedMailsVirtualThread;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Result result = (Result) obj;
                if (Objects.equals(this.mMailBoxFolders, result.mMailBoxFolders) && Objects.equals(this.mFoldersContent, result.mFoldersContent)) {
                    return true;
                }
            }
            return false;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsWithExtraResult
        public List<FolderGrants> getContainerExtra() {
            return this.mFolderGrants;
        }

        @Override // ru.mail.data.cmd.server.RequestItemsResult
        public Collection<MailBoxFolder> getContainers() {
            return this.mMailBoxFolders;
        }

        public Map<Long, FoldersContent> getFoldersContent() {
            return this.mFoldersContent;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsResult
        public long getLastModified() {
            return this.mLastModified;
        }

        public Collection<MailBoxFolder> getMailBoxFolders() {
            return this.mMailBoxFolders;
        }

        @Override // ru.mail.data.cmd.server.RequestItemsResult
        public Collection<T> getMailItems() {
            return this.mItems;
        }

        @Nullable
        public PinnedMailsVirtualThread getPinnedThread() {
            return this.mPinnedThread;
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsWithExtraResult
        public List<UserGrantsDbDto> getSecondContainerExtra() {
            return this.mUserGrants;
        }

        public int hashCode() {
            return Objects.hash(this.mMailBoxFolders, this.mFoldersContent);
        }

        @Override // ru.mail.data.cmd.server.RequestMailItemsResult
        public boolean isContainersPartiallyLoaded() {
            return false;
        }
    }

    public BatchSmartStatusCommand(Context context, Params params, boolean z10, boolean z11, boolean z12, FoldersNotifyer foldersNotifyer, boolean z13, FolderMatcher folderMatcher) {
        super(context, params, null, z10);
        this.isColoredTagOn = z11;
        this.isSubscriptionsPromoSheetEnabled = z12;
        this.foldersNotifyer = foldersNotifyer;
        this.isUserChild = z13;
        this.folderMatcher = folderMatcher;
    }

    private Collection<T> getAllItems(Map<Long, Result.FoldersContent<T>> map) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Long, Result.FoldersContent<T>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next().getValue().getMailItems());
        }
        return arrayList;
    }

    private long getSubscriptionsUnreadMessageCount(List<MetaThread> list) {
        for (MetaThread metaThread : list) {
            if (metaThread.getFolderId() == MailBoxFolder.FOLDER_ID_MAILINGS) {
                return metaThread.getUnreadCount();
            }
        }
        return -1L;
    }

    @Nullable
    private PinnedMailsVirtualThread parsePinnedThread(JSONObject jSONObject, @NotNull String str) {
        return new PinnedMailsParser(str).parse(jSONObject);
    }

    @Override // ru.mail.data.cmd.server.RequestWithImapActivation, ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new NetworkDelegate();
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    protected Collection<T> getItemsFromStatus(SmartStatusParser.Result result) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(result.getMessages());
        arrayList.addAll(result.getThreads());
        arrayList.addAll(result.getMetaThreads());
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected MailboxFolderParser.FoldersParserContainer parseFoldersAndGrants(JSONArray jSONArray, List<UserGrantsDbDto> list) throws JSONException, NetworkCommand.PostExecuteException {
        MailboxFolderParser.FoldersParserContainer withGrants = new MailboxFolderParser(this.folderMatcher, ((Params) getParams()).getLogin(), Collections.EMPTY_SET).parseWithGrants(jSONArray, list);
        SurelyFoldersChecker surelyFoldersChecker = new SurelyFoldersChecker(withGrants.getFolders(), this.isUserChild);
        this.foldersNotifyer.notifyFoldersUpdated(withGrants.getFolders(), ((Params) getParams()).getLogin());
        if (surelyFoldersChecker.isOk()) {
            return withGrants;
        }
        LOG.e(surelyFoldersChecker.getErrorString());
        throw new NetworkCommand.PostExecuteException(surelyFoldersChecker.getErrorString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected SmartStatusParser.Result parseStatus(JSONArray jSONArray) throws JSONException {
        return new SmartStatusParser(((Params) getParams()).getLogin(), ((Params) getParams()).getSnippetLimit(), this.isColoredTagOn).parse(jSONArray);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.data.cmd.server.RequestWithImapActivation
    public Result onImapActivationOk(JSONObject jSONObject) throws NetworkCommand.PostExecuteException {
        LOG.d("Smart status response: " + jSONObject.toString());
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("body");
            MailboxFolderParser.FoldersParserContainer foldersAndGrants = parseFoldersAndGrants(jSONObject2.getJSONArray("folders"), UserGrantsParser.parseFromBody(jSONObject2));
            SubscriptionsPromoSheetPrefs subscriptionsPromoSheetPrefs = this.isSubscriptionsPromoSheetEnabled ? new SubscriptionsPromoSheetPrefs(getContext(), getLogin()) : null;
            long j10 = jSONObject.getLong(PARAM_KEY_LAST_MODIFIED);
            boolean z10 = jSONObject2.getBoolean(THREADS_MODE_ENABLED);
            HashMap map = new HashMap();
            int i10 = 0;
            for (JSONArray jSONArray = jSONObject2.getJSONArray(JSON_FOLDERS_CONTENT_KEY); i10 < jSONArray.length(); jSONArray = jSONArray) {
                JSONObject jSONObject3 = jSONArray.getJSONObject(i10);
                SmartStatusParser.Result status = parseStatus(jSONObject3.getJSONArray(JSON_THREADS_KEY));
                if (subscriptionsPromoSheetPrefs != null) {
                    long subscriptionsUnreadMessageCount = getSubscriptionsUnreadMessageCount(status.getMetaThreads());
                    if (subscriptionsUnreadMessageCount >= 0) {
                        subscriptionsPromoSheetPrefs.saveUnreadCount(subscriptionsUnreadMessageCount);
                    }
                }
                long j11 = jSONObject3.getLong("id");
                map.put(Long.valueOf(j11), new Result.FoldersContent<>(j11, getItemsFromStatus(status), status.getMetaThreads(), status.getMessages(), status.getThreads(), status.getOrderedItems(), foldersAndGrants.getFolders(), z10, j10));
                i10++;
            }
            Integer filledBoxSize = new BoxQuotasParser().parseFilledBoxSize(jSONObject2);
            SharedPrefMailQuotasStorage sharedPrefMailQuotasStorage = new SharedPrefMailQuotasStorage(getContext(), getLogin());
            if (filledBoxSize != null) {
                sharedPrefMailQuotasStorage.addFilledBoxSize(filledBoxSize.intValue());
            } else {
                sharedPrefMailQuotasStorage.clearData();
            }
            return new Result(map, foldersAndGrants.getFolders(), foldersAndGrants.getFolderGrants(), j10, getAllItems(map), foldersAndGrants.getUserGrants(), parsePinnedThread(jSONObject2, getLogin()));
        } catch (JSONException e10) {
            LOG.e(e10.toString());
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
