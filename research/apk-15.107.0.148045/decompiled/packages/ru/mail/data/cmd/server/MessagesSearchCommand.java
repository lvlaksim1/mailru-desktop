package ru.mail.data.cmd.server;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MetaSearchAnalyticsHolder;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.server.parser.JsonSearchMsgParser;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailboxSearch;
import ru.mail.glasha.di.SharedFoldersModuleEntryPoint;
import ru.mail.glasha.domain.managers.FolderGrantsManager;
import ru.mail.logic.cmd.SearchResult;
import ru.mail.logic.content.ContextualMailBoxFolder;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "search"})
public class MessagesSearchCommand extends ServerCommandBase<Params, SearchResult> {
    private static final Log LOG = Log.getLog("MessagesSearchCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static final class Params extends ServerCommandEmailParams {
        private static final String QUERY_PARAM_ATTACH = "attach";
        private static final String QUERY_PARAM_FLAGGED = "flagged";
        private static final String QUERY_PARAM_FROM = "from";
        private static final String QUERY_PARAM_PIN = "pin";
        private static final String QUERY_PARAM_TO = "to";
        private static final String QUERY_PARAM_UNREAD = "unread";

        @Param(name = "aqid")
        @Nullable
        private final String mAqid;

        @Param(name = "search_categories")
        @Nullable
        private final JSONArray mCategory;

        @Param(name = "correspondents")
        @Nullable
        private final String mCorrespondents;

        @Param(name = "custom_tags")
        @Nullable
        private final JSONArray mCustomTags;

        @Param(name = "interval")
        @Nullable
        private final String mDateRange;

        @Param(name = Collector.FLAGS)
        @Nullable
        private final String mFlags;

        @Param(name = "folder")
        @Nullable
        private final String mFolder;

        @Param(name = "htmlencoded")
        @Nullable
        private final Boolean mHtmlEncodingEnabled;

        @Param(name = "in_excluded_folders")
        private final Boolean mInExcludedFolders;

        @Param(name = "limit")
        @NotNull
        private final Integer mLimit;

        @Param(name = "offset")
        @NotNull
        private final Integer mOffset;

        @Param(name = "query")
        @Nullable
        private final String mQuery;

        @Param(name = "remove_emoji_opts")
        private final String mRemoveEmojiFlags;

        @NotNull
        private final MailboxSearch mSearch;

        @Param(name = "snippet_limit")
        private final Integer mSnippetLimit;

        @Param(name = "subject")
        @Nullable
        private final String mSubject;

        @Param(name = "with_threads")
        @Nullable
        private final Boolean mThreadIdEnabled;

        @Param(name = "transaction_category")
        @Nullable
        private final String mTransactCategory;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull MailboxSearch mailboxSearch, int i10, int i11, int i12) {
            JSONArray jSONArray;
            super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            this.mHtmlEncodingEnabled = Boolean.FALSE;
            this.mAqid = MetaSearchAnalyticsHolder.INSTANCE.getAppQid();
            this.mSearch = mailboxSearch;
            this.mQuery = getQueryParameter();
            this.mOffset = Integer.valueOf(i10);
            this.mLimit = Integer.valueOf(i11);
            this.mSnippetLimit = Integer.valueOf(i12);
            this.mSubject = mailboxSearch.getSubject();
            this.mFolder = mailboxSearch.getFolderQueryValue();
            JSONArray jSONArray2 = null;
            if (mailboxSearch.getMailBoxFolder() == null || !(ContextualMailBoxFolder.isTrash(mailboxSearch.getMailBoxFolder().getId().longValue()) || ContextualMailBoxFolder.isSpam(mailboxSearch.getMailBoxFolder().getId().longValue()))) {
                this.mInExcludedFolders = null;
            } else {
                this.mInExcludedFolders = Boolean.TRUE;
            }
            this.mFlags = convertFlagsToJson();
            this.mDateRange = convertDateRangeToJson();
            this.mCorrespondents = convertCorrespondentsToJson();
            this.mThreadIdEnabled = mailboxSearch.getWithThread();
            this.mRemoveEmojiFlags = dataManager.getRemoveEmojisParam();
            if (TextUtils.isEmpty(mailboxSearch.getTransactionCategoryQueryValue())) {
                this.mTransactCategory = null;
            } else {
                this.mTransactCategory = mailboxSearch.getTransactionCategoryQueryValue();
            }
            String categoriesQueryValue = mailboxSearch.getCategoriesQueryValue();
            categoriesQueryValue = TextUtils.isEmpty(categoriesQueryValue) ? mailboxSearch.getCategoryQueryValue() : categoriesQueryValue;
            if (TextUtils.isEmpty(categoriesQueryValue)) {
                this.mCategory = null;
            } else {
                try {
                    jSONArray = new JSONArray(categoriesQueryValue);
                } catch (JSONException unused) {
                    MessagesSearchCommand.LOG.e("Wrong search category query array");
                    jSONArray = null;
                }
                this.mCategory = jSONArray;
            }
            String customTagIdsQueryValue = this.mSearch.getCustomTagIdsQueryValue();
            if (TextUtils.isEmpty(customTagIdsQueryValue)) {
                this.mCustomTags = null;
                return;
            }
            try {
                jSONArray2 = new JSONArray(customTagIdsQueryValue);
            } catch (JSONException unused2) {
                MessagesSearchCommand.LOG.e("Wrong custom tags query array");
            }
            this.mCustomTags = jSONArray2;
        }

        private String convertCorrespondentsToJson() {
            try {
                JSONObject jSONObject = new JSONObject();
                putIfExist(jSONObject, "from", TextUtils.isEmpty(this.mSearch.getFrom()) ? null : this.mSearch.getFrom());
                putIfExist(jSONObject, "to", TextUtils.isEmpty(this.mSearch.getTo()) ? null : this.mSearch.getTo());
                if (jSONObject.names() != null && jSONObject.names().length() != 0) {
                    return jSONObject.toString();
                }
                return null;
            } catch (JSONException unused) {
                return null;
            }
        }

        private String convertDateRangeToJson() {
            MailboxSearch.SearchBeginDate beginDate = this.mSearch.getBeginDate();
            MailboxSearch.SearchEndDate endDate = this.mSearch.getEndDate();
            if (beginDate != null && endDate != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    MailboxSearch.DateCalculatorFactory.Default r10 = new MailboxSearch.DateCalculatorFactory.Default();
                    jSONObject.put("from", r10.toTornadoFormat(beginDate));
                    jSONObject.put("to", r10.toTornadoFormat(endDate));
                    return jSONObject.toString();
                } catch (JSONException unused) {
                }
            }
            return null;
        }

        private String convertFlagsToJson() {
            try {
                JSONObject jSONObject = new JSONObject();
                putIfExist(jSONObject, "unread", this.mSearch.getUnreadQueryValue(), MailboxSearch.QueryParamsConverter.READ_UNREAD);
                putIfExist(jSONObject, "flagged", this.mSearch.getFlagQueryValue(), MailboxSearch.QueryParamsConverter.FLAG_UNFLAG);
                putIfExist(jSONObject, "attach", this.mSearch.getWithAttachmentsQueryValue(), MailboxSearch.QueryParamsConverter.WITH_ATTACH_WITHOUT_ATTACH);
                putIfExist(jSONObject, "pin", this.mSearch.getPinQueryValue(), MailboxSearch.QueryParamsConverter.PIN_UNPIN);
                if (jSONObject.names() != null && jSONObject.names().length() != 0) {
                    return jSONObject.toString();
                }
                return null;
            } catch (JSONException e10) {
                MessagesSearchCommand.LOG.e("error", e10);
                return null;
            }
        }

        @Nullable
        private String getQueryParameter() {
            if ((TextUtils.isEmpty(this.mSearch.getSearchText()) || this.mSearch.getSearchText().equals("*")) && TextUtils.isEmpty(this.mSearch.getSubject()) && TextUtils.isEmpty(this.mSearch.getSubject())) {
                return null;
            }
            return this.mSearch.getSearchText();
        }

        private <T extends String> void putIfExist(JSONObject jSONObject, String str, T t10, MailboxSearch.QueryParamsConverter queryParamsConverter) throws JSONException {
            if (t10 == null) {
                return;
            }
            jSONObject.put(str, queryParamsConverter.toTornadoFormat(t10));
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
            if (Objects.equals(this.mSearch, params.mSearch) && Objects.equals(this.mQuery, params.mQuery) && this.mOffset.equals(params.mOffset) && this.mLimit.equals(params.mLimit) && Objects.equals(this.mFlags, params.mFlags) && Objects.equals(this.mFolder, params.mFolder)) {
                return Objects.equals(this.mDateRange, params.mDateRange);
            }
            return false;
        }

        @NotNull
        public MailboxSearch getSearch() {
            return this.mSearch;
        }

        public Integer getSnippetLimit() {
            return this.mSnippetLimit;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = ((super.hashCode() * 31) + this.mSearch.hashCode()) * 31;
            String str = this.mQuery;
            int iHashCode2 = (((((iHashCode + (str != null ? str.hashCode() : 0)) * 31) + this.mOffset.hashCode()) * 31) + this.mLimit.hashCode()) * 31;
            String str2 = this.mFlags;
            int iHashCode3 = (iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mFolder;
            int iHashCode4 = (iHashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.mDateRange;
            return iHashCode4 + (str4 != null ? str4.hashCode() : 0);
        }

        private <T extends String> void putIfExist(JSONObject jSONObject, String str, T t10) throws JSONException {
            if (t10 == null) {
                return;
            }
            jSONObject.put(str, t10);
        }
    }

    public MessagesSearchCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int getSearchCount(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("folders");
        String str = ((Params) getParams()).mFolder;
        int i10 = 0;
        for (int i11 = 0; i11 < jSONArray.length(); i11++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i11);
            int iOptInt = jSONObject2.optInt("found", 0);
            if (str != null) {
                if (str.equals(jSONObject2.optString("id", ""))) {
                    return iOptInt;
                }
            } else if (!jSONObject2.optBoolean("shared", false)) {
                i10 += iOptInt;
            }
        }
        return i10;
    }

    private boolean isColoredTagsOn() {
        return ConfigurationRepository.from(getContext()).getConfiguration().getColoredTagsConfig().getParseByDefault();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isEmptySearchQuery() {
        FolderGrantsManager folderGrantsManager = SharedFoldersModuleEntryPoint.folderGrantsManager(getContext());
        if (TextUtils.isEmpty(((Params) getParams()).mQuery) && TextUtils.isEmpty(((Params) getParams()).mFlags)) {
            return (TextUtils.isEmpty(((Params) getParams()).mFolder) || folderGrantsManager.isRoot(((Params) getParams()).mFolder)) && TextUtils.isEmpty(((Params) getParams()).mDateRange) && TextUtils.isEmpty(((Params) getParams()).mCorrespondents) && TextUtils.isEmpty(((Params) getParams()).mTransactCategory) && ((Params) getParams()).mCategory == null && ((Params) getParams()).mCustomTags == null;
        }
        return false;
    }

    private void setMetaSearchAnalyticsData(Map<String, List<String>> map) {
        List<String> list = map.get("x-mru-request-id");
        String str = "";
        String str2 = (list == null || list.size() <= 0) ? "" : list.get(0);
        List<String> list2 = map.get("x-host");
        if (list2 != null && list2.size() > 0) {
            str = list2.get(0);
        }
        MetaSearchAnalyticsHolder metaSearchAnalyticsHolder = MetaSearchAnalyticsHolder.INSTANCE;
        metaSearchAnalyticsHolder.setXRequestId(str2);
        metaSearchAnalyticsHolder.setHost(str);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, SearchResult>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new TornadoResponseProcessor(response, networkCommandBaseDelegate);
    }

    public MessagesSearchCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        return isEmptySearchQuery() ? new CommandStatus.OK(new SearchResult(Collections.EMPTY_LIST, 0, ((Params) getParams()).getSearch())) : super.onExecute(executorSelector);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public SearchResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            int searchCount = getSearchCount(jSONObject.getJSONObject("found"));
            JSONArray jSONArray = jSONObject.getJSONArray("messages");
            setMetaSearchAnalyticsData(response.getHeaders());
            int length = jSONArray.length();
            ArrayList arrayList = new ArrayList(length);
            JsonSearchMsgParser jsonSearchMsgParser = new JsonSearchMsgParser(((Params) getParams()).getLogin(), ((Params) getParams()).getSnippetLimit().intValue(), isColoredTagsOn());
            for (int i10 = 0; i10 < length; i10++) {
                arrayList.add(jsonSearchMsgParser.parse(jSONArray.getJSONObject(i10)));
            }
            return new SearchResult(arrayList, searchCount, ((Params) getParams()).getSearch());
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
