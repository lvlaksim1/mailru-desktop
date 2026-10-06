package ru.mail.data.cmd.server.parser;

import java.util.Date;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.util.log.Log;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0015\u0016B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0002H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/parser/MailThreadRepresentationParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/data/entities/MailThreadRepresentation;", "mMailThread", "Lru/mail/data/entities/MailThread;", "isColoredTagsOn", "", "<init>", "(Lru/mail/data/entities/MailThread;Z)V", "mTransactionCategoryParser", "Lru/mail/data/cmd/server/parser/TransactionCategoryParser;", "tagsParser", "Lru/mail/data/cmd/server/parser/ColoredTagsParser;", "customTagsParser", "Lru/mail/data/cmd/server/parser/CustomTagsParser;", "parse", "jsonObject", "Lorg/json/JSONObject;", "parseFlags", "", "representation", "SnoozeDateParser", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailThreadRepresentationParser extends JSONParser<MailThreadRepresentation> {

    @NotNull
    private static final String KEY_ATTACH = "attach";

    @NotNull
    private static final String KEY_ATTACHMENTS_COUNT = "attachments_count";

    @NotNull
    private static final String KEY_BCC = "bcc";

    @NotNull
    private static final String KEY_BIMI_IMPORTANT_MSG = "bimi_important_msg";

    @NotNull
    private static final String KEY_BIMI_MSG = "bimi_msg";

    @NotNull
    private static final String KEY_CALENDAR_ICS_META = "calendar_ics_events";

    @NotNull
    private static final String KEY_CALENDAR_ML_META = "calendar_ml_events";

    @NotNull
    private static final String KEY_CC = "cc";

    @NotNull
    private static final String KEY_CORRESPONDENTS = "correspondents";

    @NotNull
    private static final String KEY_DATE = "date";

    @NotNull
    private static final String KEY_ENGLISH = "in_english";

    @NotNull
    private static final String KEY_EXTERNAL_LINKS_WARNING = "external_links_warning";

    @NotNull
    private static final String KEY_FLAGS = "flags";

    @NotNull
    private static final String KEY_FOLDER = "folder";

    @NotNull
    private static final String KEY_FORWARD = "forward";

    @NotNull
    private static final String KEY_FROM = "from";

    @NotNull
    private static final String KEY_HAVE_UNSUBSCRIBE_LIST = "have_unsubscribe_list";

    @NotNull
    private static final String KEY_INTERNAL_AUTH_PASS = "internal_auth_passed";

    @NotNull
    private static final String KEY_LENGTH = "length";

    @NotNull
    private static final String KEY_LENGTH_FLAGGED = "length_flagged";

    @NotNull
    private static final String KEY_LENGTH_PINNED = "length_pinned";

    @NotNull
    private static final String KEY_LENGTH_UNREAD = "length_unread";

    @NotNull
    private static final String KEY_MAYBE_PHISHING = "maybe_phishing";

    @NotNull
    private static final String KEY_MESSAGE_ID_LAST = "message_id_last";

    @NotNull
    private static final String KEY_META = "meta";

    @NotNull
    private static final String KEY_NEWSLETTER = "newsletter";

    @NotNull
    private static final String KEY_OFFICIAL = "official";

    @NotNull
    private static final String KEY_OFFICIAL_NEWSLETTER = "official_newsletter";

    @NotNull
    private static final String KEY_PINNED = "pinned";

    @NotNull
    private static final String KEY_RECEIPT_INFO = "receipt_info";

    @NotNull
    private static final String KEY_RELEVANT = "is_relevant";

    @NotNull
    private static final String KEY_REPLY = "reply";

    @NotNull
    private static final String KEY_SENDER_HAS_EMOJIS = "sender_has_emojis";

    @NotNull
    private static final String KEY_SHOW_DEFINITELY_SPAM = "show_definitely_spam";

    @NotNull
    private static final String KEY_SNIPPET = "snippet";

    @NotNull
    private static final String KEY_SNIPPET_HAS_EMOJIS = "snippet_has_emojis";

    @NotNull
    private static final String KEY_SNOOZE_DATE = "snooze_date";

    @NotNull
    private static final String KEY_SUBJECT = "subject";

    @NotNull
    private static final String KEY_SUBJECT_HAS_EMOJIS = "subject_has_emojis";

    @NotNull
    private static final String KEY_TO = "to";

    @NotNull
    private static final String KEY_UNREAD = "unread";

    @NotNull
    private final CustomTagsParser customTagsParser;

    @NotNull
    private final MailThread mMailThread;

    @NotNull
    private final TransactionCategoryParser mTransactionCategoryParser;

    @NotNull
    private final ColoredTagsParser tagsParser;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("MailThreadRepresentationParser");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b)\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\u0018\u00106\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00107\u001a\u000205H\u0002J\u0018\u00108\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\u0018\u00109\u001a\u0002012\u0006\u0010:\u001a\u00020;2\u0006\u00104\u001a\u000205H\u0002J\u0018\u0010<\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\u0018\u0010=\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002J\u0018\u0010>\u001a\u0002012\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lru/mail/data/cmd/server/parser/MailThreadRepresentationParser$Companion;", "", "<init>", "()V", "KEY_MESSAGE_ID_LAST", "", "KEY_SUBJECT", "KEY_UNREAD", "KEY_SNIPPET", "KEY_FOLDER", "KEY_DATE", "KEY_CORRESPONDENTS", "KEY_FROM", "KEY_TO", "KEY_CC", "KEY_BCC", "KEY_LENGTH", "KEY_LENGTH_UNREAD", "KEY_LENGTH_FLAGGED", "KEY_LENGTH_PINNED", "KEY_FLAGS", "KEY_REPLY", "KEY_FORWARD", "KEY_ATTACH", "KEY_NEWSLETTER", "KEY_MAYBE_PHISHING", "KEY_SNOOZE_DATE", "KEY_META", "KEY_CALENDAR_ICS_META", "KEY_CALENDAR_ML_META", "KEY_RECEIPT_INFO", "KEY_ATTACHMENTS_COUNT", "KEY_BIMI_MSG", "KEY_BIMI_IMPORTANT_MSG", "KEY_OFFICIAL", "KEY_OFFICIAL_NEWSLETTER", "KEY_SHOW_DEFINITELY_SPAM", "KEY_HAVE_UNSUBSCRIBE_LIST", "KEY_ENGLISH", "KEY_EXTERNAL_LINKS_WARNING", "KEY_RELEVANT", "KEY_PINNED", "KEY_SENDER_HAS_EMOJIS", "KEY_SUBJECT_HAS_EMOJIS", "KEY_SNIPPET_HAS_EMOJIS", "KEY_INTERNAL_AUTH_PASS", "LOG", "Lru/mail/util/log/Log;", "parseAddresses", "", "jsonObject", "Lorg/json/JSONObject;", "representation", "Lru/mail/data/entities/MailThreadRepresentation;", "parseCounts", "mailThreadRepresentation", "parseMeta", "parseGoToAction", "metaArray", "Lorg/json/JSONArray;", "parseEventICS", "parseMLEventsMeta", "parseReceiptInfo", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void parseAddresses(JSONObject jsonObject, MailThreadRepresentation representation) throws JSONException {
            if (jsonObject.has("correspondents")) {
                JSONObject jSONObject = jsonObject.getJSONObject("correspondents");
                JSONArray jSONArray = jSONObject.getJSONArray("from");
                Intrinsics.checkNotNull(jSONArray);
                representation.setFrom(Commons.convertAddressesToString(jSONArray));
                JSONArray jSONArray2 = jSONObject.getJSONArray("to");
                Intrinsics.checkNotNull(jSONArray2);
                representation.setTo(Commons.convertAddressesToString(jSONArray2));
                JSONArray jSONArray3 = jSONObject.getJSONArray("cc");
                Intrinsics.checkNotNull(jSONArray3);
                representation.setCC(Commons.convertAddressesToString(jSONArray3));
                JSONArray jSONArray4 = jSONObject.getJSONArray("bcc");
                Intrinsics.checkNotNull(jSONArray4);
                representation.setBCC(Commons.convertAddressesToString(jSONArray4));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void parseCounts(JSONObject jsonObject, MailThreadRepresentation mailThreadRepresentation) {
            mailThreadRepresentation.setMessagesCount(JsonUtils.getIntFromJsonObject(jsonObject, "length", 0));
            mailThreadRepresentation.setUnreadCount(JsonUtils.getIntFromJsonObject(jsonObject, "length_unread", 0));
            mailThreadRepresentation.setFlaggedCount(JsonUtils.getIntFromJsonObject(jsonObject, "length_flagged", 0));
            mailThreadRepresentation.setPinnedCount(JsonUtils.getIntFromJsonObject(jsonObject, "length_pinned", 0));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void parseEventICS(JSONObject jsonObject, MailThreadRepresentation representation) {
            JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jsonObject, MailThreadRepresentationParser.KEY_CALENDAR_ICS_META);
            if (jsonArrayFromJsonObject != null) {
                representation.setEventICSMeta(jsonArrayFromJsonObject.toString());
            }
        }

        private final void parseGoToAction(JSONArray metaArray, MailThreadRepresentation representation) {
            JSONObject firstGoToActionMetaJSONObjectOrNull = Commons.INSTANCE.getFirstGoToActionMetaJSONObjectOrNull(metaArray);
            if (firstGoToActionMetaJSONObjectOrNull == null) {
                return;
            }
            representation.setGoToActionMeta(firstGoToActionMetaJSONObjectOrNull.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void parseMLEventsMeta(JSONObject jsonObject, MailThreadRepresentation representation) {
            JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jsonObject, MailThreadRepresentationParser.KEY_CALENDAR_ML_META);
            if (jsonArrayFromJsonObject != null) {
                representation.setEventMLMeta(jsonArrayFromJsonObject.toString());
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void parseMeta(JSONObject jsonObject, MailThreadRepresentation representation) {
            JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jsonObject, "meta");
            if (jsonArrayFromJsonObject != null) {
                parseGoToAction(jsonArrayFromJsonObject, representation);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void parseReceiptInfo(JSONObject jsonObject, MailThreadRepresentation representation) {
            String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jsonObject, "receipt_info", "{}");
            if (stringFromJsonObject != null) {
                representation.setReceiptInfo(stringFromJsonObject);
            }
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lru/mail/data/cmd/server/parser/MailThreadRepresentationParser$SnoozeDateParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "", "<init>", "()V", "parse", "jsonObject", "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)Ljava/lang/Long;", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class SnoozeDateParser extends JSONParser<Long> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // ru.mail.data.cmd.server.parser.JSONParser
        @NotNull
        public Long parse(@NotNull JSONObject jsonObject) throws JSONException {
            Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
            return Long.valueOf(JsonUtils.getLongFromJsonObject(jsonObject, "snooze_date", 0L) * ((long) 1000));
        }
    }

    public MailThreadRepresentationParser(@NotNull MailThread mMailThread, boolean z10) {
        Intrinsics.checkNotNullParameter(mMailThread, "mMailThread");
        this.mMailThread = mMailThread;
        this.mTransactionCategoryParser = new TransactionCategoryParser();
        this.tagsParser = new ColoredTagsParser(z10);
        this.customTagsParser = new CustomTagsParser();
    }

    private final void parseFlags(JSONObject jsonObject, MailThreadRepresentation representation) throws JSONException {
        JSONObject jSONObject = jsonObject.getJSONObject("flags");
        representation.setReplied(jSONObject.optBoolean("reply"));
        representation.setForwarded(jSONObject.optBoolean("forward"));
        representation.setHasAttach(jSONObject.optBoolean("attach"));
        representation.setNewsletter(jSONObject.optBoolean(KEY_NEWSLETTER));
        representation.setMaybePhishing(jSONObject.optBoolean(KEY_MAYBE_PHISHING));
        representation.setBimiMessage(jSONObject.optBoolean(KEY_BIMI_MSG));
        representation.setBimiImportantMessage(jSONObject.optBoolean(KEY_BIMI_IMPORTANT_MSG));
        representation.setOfficial(jSONObject.optBoolean(KEY_OFFICIAL));
        representation.setOfficialNewsletter(jSONObject.optBoolean(KEY_OFFICIAL_NEWSLETTER));
        representation.setSenderVerified(jSONObject.optBoolean(KEY_SHOW_DEFINITELY_SPAM));
        representation.setCanUnsubscribe(jSONObject.optBoolean(KEY_HAVE_UNSUBSCRIBE_LIST));
        ColoredTagsParser coloredTagsParser = this.tagsParser;
        Intrinsics.checkNotNull(jSONObject);
        representation.setLabels(coloredTagsParser.parse(jSONObject, representation));
        representation.setEnglish(jSONObject.optBoolean(KEY_ENGLISH));
        representation.setHasExternalLinkWarning(jSONObject.optBoolean("external_links_warning", false));
        representation.setRelevant(jSONObject.optBoolean("is_relevant"));
        representation.setSenderHasEmojis(jSONObject.optBoolean("sender_has_emojis"));
        representation.setSubjectHasEmojis(jSONObject.optBoolean("subject_has_emojis"));
        representation.setSnippetHasEmojis(jSONObject.optBoolean("snippet_has_emojis"));
        representation.setInternalAuthPassed(jSONObject.optBoolean("internal_auth_passed"));
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public MailThreadRepresentation parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        MailThreadRepresentation mailThreadRepresentation = new MailThreadRepresentation();
        mailThreadRepresentation.setLastMessageId(JsonUtils.getStringFromJsonObject(jsonObject, KEY_MESSAGE_ID_LAST, null));
        mailThreadRepresentation.setSubject(JsonUtils.getStringFromJsonObject(jsonObject, "subject", null));
        mailThreadRepresentation.setUnread(JsonUtils.getBooleanFromJsonObject(jsonObject, "unread", false));
        mailThreadRepresentation.setSnippet(JsonUtils.getStringFromJsonObject(jsonObject, "snippet", null));
        mailThreadRepresentation.setFolderId(JsonUtils.getLongFromJsonObject(jsonObject, "folder", -1L));
        mailThreadRepresentation.setMailThread(this.mMailThread);
        mailThreadRepresentation.setDate(new Date(JsonUtils.getLongFromJsonObject(jsonObject, "date", -1L) * ((long) 1000)));
        Companion companion = INSTANCE;
        companion.parseCounts(jsonObject, mailThreadRepresentation);
        if (mailThreadRepresentation.getMessagesCount() == 0) {
            boolean zHas = jsonObject.has("length");
            Object obj = jsonObject.get("length");
            LOG.w("MailThreadRepresentation assert messagesCount == 0 after parsing. Available = " + zHas + " Length = " + obj);
        }
        companion.parseAddresses(jsonObject, mailThreadRepresentation);
        parseFlags(jsonObject, mailThreadRepresentation);
        mailThreadRepresentation.setTransactionCategory(this.mTransactionCategoryParser.parse(jsonObject));
        companion.parseMeta(jsonObject, mailThreadRepresentation);
        companion.parseEventICS(jsonObject, mailThreadRepresentation);
        companion.parseMLEventsMeta(jsonObject, mailThreadRepresentation);
        companion.parseReceiptInfo(jsonObject, mailThreadRepresentation);
        mailThreadRepresentation.setAttachmentsCount(JsonUtils.getIntFromJsonObject(jsonObject, "attachments_count", 0));
        mailThreadRepresentation.setCustomTags(this.customTagsParser.parse(jsonObject));
        mailThreadRepresentation.setCustomTagAssociations(CollectionsKt.toHashSet(this.customTagsParser.parseAssociations(jsonObject, mailThreadRepresentation)));
        return mailThreadRepresentation;
    }
}
