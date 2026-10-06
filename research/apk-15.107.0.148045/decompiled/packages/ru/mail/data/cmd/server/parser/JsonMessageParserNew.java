package ru.mail.data.cmd.server.parser;

import java.util.Date;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.content.MailPriority;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0002H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/parser/JsonMessageParserNew;", "Lru/mail/data/cmd/server/parser/SingleMessageParser;", "Lorg/json/JSONObject;", "account", "", "snippetLimit", "", "isColoredTagsOn", "", "<init>", "(Ljava/lang/String;IZ)V", "getAccount", "()Ljava/lang/String;", "mTransactionCategoryParser", "Lru/mail/data/cmd/server/parser/TransactionCategoryParser;", "parseId", "jsonObject", "parse", "Lru/mail/data/entities/MailMessage;", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class JsonMessageParserNew extends SingleMessageParser<JSONObject> {
    private static final Pattern FUCKED_UP_THREAD_ID = Pattern.compile("\\d+:([\\da-f]+):\\d+");

    @NotNull
    private static final String KEY_ATTACHMENTS_COUNT = "attachments_count";

    @NotNull
    private static final String KEY_DATE = "date";

    @NotNull
    private static final String KEY_FOLDER = "folder";

    @NotNull
    private static final String KEY_ID = "id";

    @NotNull
    private static final String KEY_PRIORITY = "priority";

    @NotNull
    private static final String KEY_SEND_DATE = "send_date";

    @NotNull
    private static final String KEY_SNIPPET = "search_snippet";

    @NotNull
    private static final String KEY_SNOOZE_DATE = "snooze_date";

    @NotNull
    private static final String KEY_SUBJECT = "subject";

    @NotNull
    private static final String KEY_THREAD_ID = "thread_id";

    @NotNull
    private static final String MAIL_MSG_META = "{From JsonMessageParserNew}";

    @NotNull
    private final String account;

    @NotNull
    private final TransactionCategoryParser mTransactionCategoryParser;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonMessageParserNew(@NotNull String account, int i10, boolean z10) {
        super(i10, z10);
        Intrinsics.checkNotNullParameter(account, "account");
        this.account = account;
        this.mTransactionCategoryParser = new TransactionCategoryParser();
    }

    @NotNull
    protected final String getAccount() {
        return this.account;
    }

    @NotNull
    protected String parseId(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        String string = jsonObject.getString("id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    @Override // ru.mail.data.cmd.server.parser.SingleMessageParser
    @NotNull
    public MailMessage parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        MailMessage mailMessage = new MailMessage();
        mailMessage.setId(parseId(jsonObject));
        String string = jsonObject.getString("folder");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Long longOrNull = StringsKt.toLongOrNull(string);
        mailMessage.setFolderId(longOrNull != null ? longOrNull.longValue() : -1L);
        mailMessage.setSubject(getTrimmedSubject(JsonUtils.getStringFromJsonObject(jsonObject, "subject", null)));
        long j10 = 1000;
        mailMessage.setSendDate(JsonUtils.getLongFromJsonObject(jsonObject, "send_date", 0L) * j10);
        mailMessage.setSnoozeDate(JsonUtils.getLongFromJsonObject(jsonObject, "snooze_date", 0L) * j10);
        mailMessage.setDate(new Date(JsonUtils.getLongFromJsonObject(jsonObject, "date", -1L) * j10));
        String string2 = jsonObject.getJSONObject("search_snippet").getString("value");
        if (string2 == null) {
            string2 = "";
        }
        mailMessage.setSnippet(string2);
        mailMessage.setPriority(new MailPriority.Parser().parsePriority(JsonUtils.getIntFromJsonObject(jsonObject, "priority", 0)));
        mailMessage.setAccountName(this.account);
        if (jsonObject.has("thread_id")) {
            String string3 = jsonObject.getString("thread_id");
            if (string3 == null || !FUCKED_UP_THREAD_ID.matcher(string3).matches()) {
                mailMessage.setMailThreadId(string3);
            } else {
                mailMessage.setMailThreadId(null);
            }
        }
        mailMessage.setTransactionCategory(this.mTransactionCategoryParser.parse(jsonObject));
        parseFlags(jsonObject, mailMessage);
        parseAddresses(jsonObject, mailMessage);
        parseCustomTags(jsonObject, mailMessage);
        mailMessage.setMetaData(MAIL_MSG_META);
        mailMessage.setAttachmentsCount(JsonUtils.getIntFromJsonObject(jsonObject, "attachments_count", 0));
        return mailMessage;
    }
}
