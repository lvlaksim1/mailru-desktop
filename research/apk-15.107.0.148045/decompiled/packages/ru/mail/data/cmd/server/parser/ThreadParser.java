package ru.mail.data.cmd.server.parser;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.OrderItemImpl;
import ru.mail.data.entities.Property;
import ru.mail.logic.content.MailPriority;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000bH\u0002J\u0018\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000bH\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/parser/ThreadParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/data/entities/MailThread;", "account", "", "isColoredTagsOn", "", "<init>", "(Ljava/lang/String;Z)V", "parse", "jsonObject", "Lorg/json/JSONObject;", "addOrderMetaData", "", "thread", "meta", "addStatementStatusesMeta", "mailThread", "transactionMeta", "getStatusMetaOrNull", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nThreadParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadParser.kt\nru/mail/data/cmd/server/parser/ThreadParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,71:1\n1#2:72\n*E\n"})
public final class ThreadParser extends JSONParser<MailThread> {

    @NotNull
    private final String account;
    private final boolean isColoredTagsOn;

    public ThreadParser(@NotNull String account, boolean z10) {
        Intrinsics.checkNotNullParameter(account, "account");
        this.account = account;
        this.isColoredTagsOn = z10;
    }

    private final void addOrderMetaData(MailThread thread, JSONObject meta) {
        List listEmptyList;
        List<OrderItemImpl> list;
        thread.setOrderUrl(meta.optString("order_detail_href"));
        thread.setOrderShopUrl(meta.optString("order_list_href"));
        thread.setDeliveryDate(((long) meta.optInt("delivery_timestamp")) * ((long) 1000));
        JSONArray jSONArrayOptJSONArray = meta.optJSONArray("item_list");
        if (jSONArrayOptJSONArray == null || (list = new OrderItemParser(this.account).parse(jSONArrayOptJSONArray)) == null || (listEmptyList = CollectionsKt.filterNotNull(list)) == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        thread.setOrderItems(listEmptyList);
    }

    private final void addStatementStatusesMeta(MailThread mailThread, JSONObject transactionMeta) {
        String statusMetaOrNull = getStatusMetaOrNull(transactionMeta);
        if (statusMetaOrNull != null) {
            mailThread.setStatementStatusesMeta(statusMetaOrNull);
        }
    }

    private final String getStatusMetaOrNull(JSONObject transactionMeta) {
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(transactionMeta, StatementStatusesPlateParser.STATEMENT_STATUS_ARRAY);
        if (jsonArrayFromJsonObject != null) {
            return jsonArrayFromJsonObject.toString();
        }
        return null;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public MailThread parse(@NotNull JSONObject jsonObject) throws JSONException {
        Property property;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        MailThread mailThread = new MailThread();
        mailThread.setId(JsonUtils.getStringFromJsonObject(jsonObject, "id", null));
        mailThread.setPriority(new MailPriority.Parser().parsePriority(JsonUtils.getIntFromJsonObject(jsonObject, "priority", -1)));
        mailThread.setAccountName(this.account);
        mailThread.setMessagesCount(JsonUtils.getIntFromJsonObject(jsonObject, MailThread.COL_NAME_LENGTH, -1));
        if (jsonObject.has("smart_reply")) {
            property = jsonObject.getBoolean("smart_reply") ? Property.SET : Property.UNSET;
        } else {
            property = Property.UNDEFINED;
        }
        mailThread.setHasSmartReply(property);
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jsonObject, "representations");
        if (jsonArrayFromJsonObject != null) {
            mailThread.setMailThreadRepresentations(new MailThreadRepresentationParser(mailThread, this.isColoredTagsOn).parse(jsonArrayFromJsonObject));
            List<Long> list = new MailThreadRepresentationParser.SnoozeDateParser().parse(jsonArrayFromJsonObject);
            Intrinsics.checkNotNullExpressionValue(list, "parse(...)");
            Long l10 = (Long) CollectionsKt.maxOrNull((Iterable) list);
            if (l10 != null) {
                mailThread.setSnoozeDate(l10.longValue());
            }
        }
        JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject(StatementStatusesPlateParser.TRANSACTION_METADATA);
        if (jSONObjectOptJSONObject != null) {
            addOrderMetaData(mailThread, jSONObjectOptJSONObject);
            addStatementStatusesMeta(mailThread, jSONObjectOptJSONObject);
        }
        return mailThread;
    }
}
