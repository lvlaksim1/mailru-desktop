package ru.mail.data.cmd.server.parser;

import android.content.Context;
import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.vk.lists.PaginationHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.data.cmd.server.AddFilterCommand;
import ru.mail.data.cmd.server.TornadoSendRequest;
import ru.mail.data.entities.AttachLink;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.DraftType;
import ru.mail.data.entities.InlineAttach2cid;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.data.entities.SendMessagePersistParamsImpl;
import ru.mail.kit.shortcut.deeplink.ShortcutContract;
import ru.mail.locator.Locator;
import ru.mail.logic.content.HtmlFormatter;
import ru.mail.logic.content.HtmlFormatterWithAsserter;
import ru.mail.logic.share.MailToMyselfParameters;
import ru.mail.search.metasearch.util.analytics.AnalyticsUtilsExtKt;
import ru.mail.ui.fragments.mailbox.mailview.clicker.UrlOpener;
import ru.mail.util.HtmlToPlainBodyConverter;
import ru.mail.util.TrustedUrlsMatcher;
import ru.mail.util.log.Log;
import ru.mail.utils.JsonUtils;
import ru.mail.utils.StringEscapeUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class MailMessageContentParser extends BaseMailMessageContentParser {
    private static final String AMP_KEY = "amp";
    private static final String BODY_KEY = "body";
    private static final String CATEGORY_KEY = "Category";
    private static final String HTML_KEY = "html";
    private static final Log LOG = Log.getLog("MailMessageContentParser");
    private static final String PERSON_KEY = "Person";
    private static final String TYPE_KEY = "@type";
    private static final String UPMETRIC_KEY = "Creative";
    private String mAccount;
    private Context mContext;
    private boolean mIsIcsPlateEnabled;
    private boolean mNewSanitizeScriptEnabled;
    private final TransactionCategoryParser mTransactionCategoryParser = new TransactionCategoryParser();
    private TrustedUrlsMatcher mTrustedUrlsMatcher;

    public MailMessageContentParser(String str, Context context) {
        this.mAccount = str;
        this.mContext = context;
        this.mTrustedUrlsMatcher = new TrustedUrlsMatcher(context);
        ConfigurationWithRawData configuration = ((ConfigurationRepository) Locator.from(this.mContext).locate(ConfigurationRepository.class)).getConfiguration();
        this.mIsIcsPlateEnabled = configuration.getCalendarPlatesConfig().getIcs().getInMailView().isEnabled();
        this.mNewSanitizeScriptEnabled = configuration.isSanitizedScriptForAllAccountEnabled();
    }

    private String getCategoryMeta(JSONArray jSONArray) {
        return getMetaInternal(jSONArray, CATEGORY_KEY);
    }

    private String getContactMeta(JSONArray jSONArray) {
        return getMetaInternal(jSONArray, PERSON_KEY);
    }

    private boolean getFlag(JSONObject jSONObject, String str, boolean z10) {
        return jSONObject != null ? JsonUtils.getBooleanFromJsonObject(jSONObject, str, z10) : z10;
    }

    private String getFull(JSONObject jSONObject) {
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject, "name", "");
        String stringFromJsonObject2 = JsonUtils.getStringFromJsonObject(jSONObject, "email", "");
        if (TextUtils.isEmpty(stringFromJsonObject)) {
            return stringFromJsonObject2;
        }
        if (!stringFromJsonObject.contains("\"")) {
            return "" + stringFromJsonObject + " <" + stringFromJsonObject2 + SimpleComparison.GREATER_THAN_OPERATION;
        }
        return "\"" + StringEscapeUtils.replaceQuotes(stringFromJsonObject) + "\" <" + stringFromJsonObject2 + SimpleComparison.GREATER_THAN_OPERATION;
    }

    private String getFullList(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder("");
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            sb2.append(getFull(jSONArray.optJSONObject(i10)));
            sb2.append(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER);
        }
        return sb2.length() > 0 ? sb2.substring(0, sb2.length() - 2) : sb2.toString();
    }

    private String getList(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb2 = new StringBuilder("");
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONArray.optJSONObject(i10), "email", "");
            if (!stringFromJsonObject.isEmpty()) {
                sb2.append(stringFromJsonObject);
                sb2.append(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER);
            }
        }
        return sb2.length() > 0 ? sb2.substring(0, sb2.length() - 2) : sb2.toString();
    }

    private String getMetaInternal(JSONArray jSONArray, String str) {
        if (jSONArray != null && jSONArray.length() != 0) {
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                JSONObject jsonObjectFromJsonArray = JsonUtils.getJsonObjectFromJsonArray(jSONArray, i10, null);
                if (TextUtils.equals(str, JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, TYPE_KEY, ""))) {
                    return jsonObjectFromJsonArray.toString();
                }
            }
        }
        return "";
    }

    private String getUpmetricMeta(JSONArray jSONArray) {
        return getMetaInternal(jSONArray, "Creative");
    }

    private void setAttaches2cid(JSONObject jSONObject, MailMessageContent mailMessageContent) throws JSONException {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("images");
        if (jSONObjectOptJSONObject2 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject2.optJSONObject(SendMessagePersistParamsImpl.COL_NAME_ATTACHES2CID)) == null) {
            return;
        }
        Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
        ArrayList arrayList = new ArrayList(jSONObjectOptJSONObject.length());
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String string = jSONObjectOptJSONObject.getString(next);
            if (!TextUtils.isEmpty(next) && !TextUtils.isEmpty(string)) {
                InlineAttach2cid inlineAttach2cid = new InlineAttach2cid(0, next, string);
                inlineAttach2cid.setMessageContent(mailMessageContent);
                arrayList.add(inlineAttach2cid);
            }
        }
        LOG.i("Parsed inlineAttaches:" + InlineAttach2cid.collectionToString(arrayList));
        mailMessageContent.setInlineAttaches2cid(arrayList);
    }

    private void setAttachments(JSONObject jSONObject, MailMessageContent mailMessageContent) throws JSONException {
        AttachParser attachParser = new AttachParser(this.mContext, mailMessageContent, this.mAccount);
        AttachLinkParser attachLinkParser = new AttachLinkParser(this.mContext, mailMessageContent);
        AttachCloudParser attachCloudParser = new AttachCloudParser(this.mContext, mailMessageContent);
        AttachCloudStockParser attachCloudStockParser = new AttachCloudStockParser(this.mContext, mailMessageContent);
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jSONObject.optJSONObject("attaches"), "list");
        mailMessageContent.setAttachments(Collections.unmodifiableList(attachParser.parse(jsonArrayFromJsonObject)));
        List<AttachLink> list = attachLinkParser.parse(jsonArrayFromJsonObject);
        mailMessageContent.setAttachLinks(Collections.unmodifiableList(list));
        mailMessageContent.setAttachmentsCloud(Collections.unmodifiableList(attachCloudParser.parse(jsonArrayFromJsonObject)));
        mailMessageContent.setAttachmentsCloudStock(Collections.unmodifiableCollection(attachCloudStockParser.parse(jsonArrayFromJsonObject)));
        StringBuilder sb2 = new StringBuilder("setAttachments: Links: ");
        for (AttachLink attachLink : list) {
            sb2.append(attachLink.getFileId());
            sb2.append(StringUtils.SPACE);
            sb2.append(attachLink.getDownloadLink());
            sb2.append(MailToMyselfParameters.ATTACH_SUBJECT_DELIMITER);
        }
        LOG.d(sb2.toString());
        setAttachLinkInfo(mailMessageContent);
    }

    private void setAuthResults(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject.optJSONObject("auth_results"), "auth", "");
        if (stringFromJsonObject.isEmpty()) {
            mailMessageContent.setDkim(null);
            return;
        }
        if (!stringFromJsonObject.equals("fail") && !stringFromJsonObject.equals("pass")) {
            stringFromJsonObject = "other";
        }
        mailMessageContent.setDkim(MailMessageContent.Dkim.valueOf(stringFromJsonObject.toUpperCase(Locale.ENGLISH)));
    }

    private void setBodies(JSONObject jSONObject, MailMessageContent mailMessageContent) throws Exception {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("body");
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObjectOptJSONObject, "html", "");
        mailMessageContent.setBodyHTML(stringFromJsonObject);
        Document bodyFragment = Jsoup.parseBodyFragment(stringFromJsonObject);
        bodyFragment.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        mailMessageContent.setBodyPlain(new HtmlToPlainBodyConverter().convert(bodyFragment, stringFromJsonObject.length()));
        if (mailMessageContent.getBodyPlain() != null) {
            mailMessageContent.setBodyPlain(deleteBodyOffsets(mailMessageContent.getBodyPlain()));
        }
        String calendarHtmlThumbnail = this.mIsIcsPlateEnabled ? "" : mailMessageContent.getCalendarHtmlThumbnail();
        Context context = this.mContext;
        HtmlFormatter.FormatResult formatResult = new HtmlFormatterWithAsserter(context, new HtmlFormatter.FormatterParams(context, this.mNewSanitizeScriptEnabled, !this.mIsIcsPlateEnabled, true), calendarHtmlThumbnail).format(bodyFragment, mailMessageContent.getId());
        mailMessageContent.setFormattedBody(formatResult.getFormattedHtml());
        mailMessageContent.setHasInlineAttaches(formatResult.hasInlineAttaches());
        mailMessageContent.setHasImages(formatResult.hasImages());
        if (jSONObjectOptJSONObject.has(AMP_KEY)) {
            mailMessageContent.setAmpBody(JsonUtils.getStringFromJsonObject(jSONObjectOptJSONObject, AMP_KEY, null));
        }
    }

    private void setCorrespondents(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("correspondents");
        mailMessageContent.setTo(getFullList(JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "to")));
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "from");
        mailMessageContent.setFromFull(getFullList(jsonArrayFromJsonObject));
        mailMessageContent.setFrom(getList(jsonArrayFromJsonObject));
        mailMessageContent.setCC(getFullList(JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "cc")));
        mailMessageContent.setBcc(getFullList(JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "bcc")));
    }

    private void setDraftType(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject, "draft_type", "");
        if (stringFromJsonObject.isEmpty()) {
            mailMessageContent.setDraftType(null);
            return;
        }
        if (!stringFromJsonObject.equals("forward") && !stringFromJsonObject.equals(TornadoSendRequest.FIELD_REPLY) && !stringFromJsonObject.equals("replyall")) {
            stringFromJsonObject = "null";
        }
        mailMessageContent.setDraftType(DraftType.valueOf(stringFromJsonObject.toUpperCase(Locale.ENGLISH)));
    }

    private void setFlags(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Collector.FLAGS);
        mailMessageContent.mIsUnreaded = getFlag(jSONObjectOptJSONObject, AnalyticsUtilsExtKt.FILTER_UNREAD, false);
        mailMessageContent.setIsReplied(getFlag(jSONObjectOptJSONObject, TornadoSendRequest.FIELD_REPLY, false));
        mailMessageContent.setIsForwarded(getFlag(jSONObjectOptJSONObject, "forward", false));
        mailMessageContent.setIsFlagged(getFlag(jSONObjectOptJSONObject, AnalyticsUtilsExtKt.FILTER_FLAGGED, false));
        mailMessageContent.setSmartReply(getFlag(jSONObjectOptJSONObject, "smart_reply", false));
        mailMessageContent.setSmartReplyStage(getFlag(jSONObjectOptJSONObject, MailMessageContent.COL_NAME_SMART_REPLY_STAGE, false));
        mailMessageContent.setNewsletter(getFlag(jSONObjectOptJSONObject, "newsletter", false));
        mailMessageContent.setMaybePhishing(getFlag(jSONObjectOptJSONObject, "maybe_phishing", false));
        mailMessageContent.setBimiMessage(getFlag(jSONObjectOptJSONObject, "bimi_msg", false));
        mailMessageContent.setBimiImportantMessage(getFlag(jSONObjectOptJSONObject, "bimi_important_msg", false));
        mailMessageContent.setOfficial(getFlag(jSONObjectOptJSONObject, "official", false));
        mailMessageContent.setOfficialNewsletter(getFlag(jSONObjectOptJSONObject, "official_newsletter", false));
        mailMessageContent.setTrustedSenderForCorp(getFlag(jSONObjectOptJSONObject, MailMessageContent.COL_NAME_TRUSTED_SENDER_FOR_CORP, false));
        mailMessageContent.setEnglish(getFlag(jSONObjectOptJSONObject, "in_english", false));
        mailMessageContent.setRelevant(getFlag(jSONObjectOptJSONObject, "is_relevant", false));
        mailMessageContent.setPinned(getFlag(jSONObjectOptJSONObject, ShortcutContract.TYPE_PINNED, false));
        mailMessageContent.setHasExternalLinkWarning(getFlag(jSONObjectOptJSONObject, UrlOpener.EXTERNAL_LINKS_WARNING_KEY, false));
        mailMessageContent.setSenderHasEmojis(getFlag(jSONObjectOptJSONObject, "sender_has_emojis", false));
        mailMessageContent.setSubjectHasEmojis(getFlag(jSONObjectOptJSONObject, "subject_has_emojis", false));
        mailMessageContent.setSnippetHasEmojis(getFlag(jSONObjectOptJSONObject, "snippet_has_emojis", false));
        mailMessageContent.setNeedReadVerify(getFlag(jSONObjectOptJSONObject, "receipt", false));
        mailMessageContent.setInternalAuthPassed(getFlag(jSONObjectOptJSONObject, "internal_auth_passed", false));
    }

    private void setForwardMessageId(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        mailMessageContent.setForwardMessageId(JsonUtils.getStringFromJsonObject(jSONObject, "X-SenderField-FwdMsg", ""));
    }

    private void setImagesFlags(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        if (jSONObject == null) {
            return;
        }
        boolean booleanFromJsonObject = JsonUtils.getBooleanFromJsonObject(jSONObject, "hidden", false);
        boolean booleanFromJsonObject2 = JsonUtils.getBooleanFromJsonObject(jSONObject, AddFilterCommand.EXISTS, false);
        mailMessageContent.setImagesHidden(booleanFromJsonObject);
        mailMessageContent.setImagesExists(booleanFromJsonObject2);
    }

    private void setReplies(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("replies");
        mailMessageContent.mReplyAllTo = getFullList(JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "all"));
        mailMessageContent.setReplyAllCC(getFullList(JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "cc")));
        mailMessageContent.setReplyTo(getFullList(JsonUtils.getJsonArrayFromJsonObject(jSONObjectOptJSONObject, "to")));
    }

    private void setReplyMessageId(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        mailMessageContent.setReplyMessageId(JsonUtils.getStringFromJsonObject(jSONObject, "X-SenderField-ReMsg", ""));
    }

    private void setSubjects(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        mailMessageContent.setSubject(StringEscapeUtils.replaceSpecialCodes(JsonUtils.getStringFromJsonObject(jSONObject, "subject", "")));
        MailMessageContent.checkSubjectMaxLength(mailMessageContent);
    }

    private void setTrustedMailSender(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        mailMessageContent.setTrustedMailSender(JsonUtils.getStringFromJsonObject(jSONObject, "X-Mailru-Bimi-Organization", ""));
    }

    private void setUserFeedbackInfo(JSONObject jSONObject, MailMessageContent mailMessageContent) {
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject, "plate_number", "");
        if (TextUtils.isEmpty(stringFromJsonObject)) {
            LOG.e("Empty plate_number for user_feedback");
            return;
        }
        try {
            int i10 = Integer.parseInt(stringFromJsonObject);
            String stringFromJsonObject2 = JsonUtils.getStringFromJsonObject(jSONObject, "category", "");
            if (TextUtils.isEmpty(stringFromJsonObject2)) {
                LOG.i("Empty category for user_feedback");
            }
            mailMessageContent.setUserFeedbackCategory(stringFromJsonObject2);
            mailMessageContent.setUserFeedbackPlate(i10);
        } catch (NumberFormatException e10) {
            LOG.e("Wrong format of plate_number for user_feedback", e10);
        }
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    public MailMessageContent parse(JSONObject jSONObject) throws Exception {
        MailMessageContent mailMessageContent = new MailMessageContent();
        setReplies(jSONObject, mailMessageContent);
        setFlags(jSONObject, mailMessageContent);
        setSubjects(jSONObject, mailMessageContent);
        setDraftType(jSONObject, mailMessageContent);
        setCorrespondents(jSONObject, mailMessageContent);
        setAuthResults(jSONObject, mailMessageContent);
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jSONObject, "id", null);
        mailMessageContent.setId(stringFromJsonObject);
        mailMessageContent.setAccount(this.mAccount);
        mailMessageContent.setFolderId(JsonUtils.getLongFromJsonObject(jSONObject, "folder", 0L));
        mailMessageContent.setMlEventData(JsonUtils.getStringFromJsonObject(jSONObject, "calendar_ml_events", "[]"));
        mailMessageContent.setReceiptInfo(JsonUtils.getStringFromJsonObject(jSONObject, "receipt_info", "{}"));
        String stringFromJsonObject2 = JsonUtils.getStringFromJsonObject(jSONObject, "next", null);
        mailMessageContent.mNextMessageId = stringFromJsonObject2;
        if (TextUtils.isEmpty(stringFromJsonObject2)) {
            mailMessageContent.mNextMessageId = PaginationHelper.DEFAULT_NEXT_FROM;
        }
        String stringFromJsonObject3 = JsonUtils.getStringFromJsonObject(jSONObject, "prev", null);
        mailMessageContent.mPrevMessageId = stringFromJsonObject3;
        if (TextUtils.isEmpty(stringFromJsonObject3)) {
            mailMessageContent.mPrevMessageId = PaginationHelper.DEFAULT_NEXT_FROM;
        }
        mailMessageContent.setActualReason(JsonUtils.getStringFromJsonObject(jSONObject, MailMessageContent.COL_NAME_ACTUAL_REASON, null));
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("user_feedback");
        if (jSONObjectOptJSONObject != null) {
            setUserFeedbackInfo(jSONObjectOptJSONObject, mailMessageContent);
        }
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jSONObject, "meta");
        mailMessageContent.setContactMeta(getContactMeta(jsonArrayFromJsonObject));
        mailMessageContent.setCategoriesMeta(getCategoryMeta(jsonArrayFromJsonObject));
        mailMessageContent.setTransactionCategory(this.mTransactionCategoryParser.parse(jSONObject));
        mailMessageContent.setUpmetricMeta(getUpmetricMeta(jsonArrayFromJsonObject));
        mailMessageContent.setSendDate(JsonUtils.getLongFromJsonObject(jSONObject, "send_date", 0L) * 1000);
        mailMessageContent.setFullDate(JsonUtils.getLongFromJsonObject(jSONObject, "date", 0L) * 1000);
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("headers");
        setReplyMessageId(jSONObjectOptJSONObject2, mailMessageContent);
        setForwardMessageId(jSONObjectOptJSONObject2, mailMessageContent);
        setTrustedMailSender(jSONObjectOptJSONObject2, mailMessageContent);
        setImagesFlags(jSONObject.optJSONObject("images"), mailMessageContent);
        boolean z10 = !TextUtils.isEmpty(JsonUtils.getStringFromJsonObject(jSONObjectOptJSONObject2, "List-Unsubscribe", "")) || mailMessageContent.isNewsletter();
        LOG.d("message " + stringFromJsonObject + " parsed. unsubscribe = " + z10);
        mailMessageContent.setCanUnsubscribe(z10);
        setAttachments(jSONObject, mailMessageContent);
        setBodies(jSONObject, mailMessageContent);
        setAttaches2cid(jSONObject, mailMessageContent);
        mailMessageContent.setTrustedUrlName(this.mTrustedUrlsMatcher.findTrustedUrl(mailMessageContent.getBodyHTML()));
        return mailMessageContent;
    }
}
