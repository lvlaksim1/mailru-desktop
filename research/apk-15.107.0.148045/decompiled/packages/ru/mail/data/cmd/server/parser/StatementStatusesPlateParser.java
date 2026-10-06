package ru.mail.data.cmd.server.parser;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.StatementStatusesPlateAnalytics;
import ru.mail.logic.plates.StatementStatusesPlate;
import ru.mail.util.log.Log;
import ru.mail.utils.JsonUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/parser/StatementStatusesPlateParser;", "", "analytics", "Lru/mail/analytics/StatementStatusesPlateAnalytics;", "<init>", "(Lru/mail/analytics/StatementStatusesPlateAnalytics;)V", "parse", "Lru/mail/logic/plates/StatementStatusesPlate;", "meta", "Lorg/json/JSONArray;", "messageId", "", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStatementStatusesPlateParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StatementStatusesPlateParser.kt\nru/mail/data/cmd/server/parser/StatementStatusesPlateParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,120:1\n1761#2,3:121\n*S KotlinDebug\n*F\n+ 1 StatementStatusesPlateParser.kt\nru/mail/data/cmd/server/parser/StatementStatusesPlateParser\n*L\n76#1:121,3\n*E\n"})
public final class StatementStatusesPlateParser {

    @NotNull
    private static final String ACTION_REQUIRED = "action_required";

    @NotNull
    private static final String ACTUAL_COMMENT = "actual_comment";

    @NotNull
    private static final String DEPARTMENT = "department";

    @NotNull
    private static final String ESTIMATED_FINISH_DATE = "estimated_finish_date";

    @NotNull
    private static final String GOSUSLUGI_URL = "https://gosuslugi.ru";

    @NotNull
    private static final String IS_FINISH = "is_finish";

    @NotNull
    private static final String REQUEST_NAME = "request_name";

    @NotNull
    private static final String REQUEST_NUMBER = "request_number";

    @NotNull
    private static final String REQUEST_URL = "request_url";

    @NotNull
    private static final String START_DATE = "start_date";

    @NotNull
    public static final String STATEMENT_STATUS_ARRAY = "statement_status";

    @NotNull
    private static final String STATUS_NAMES = "status_names";

    @NotNull
    public static final String TRANSACTION_METADATA = "transaction_metadata";

    @NotNull
    private final StatementStatusesPlateAnalytics analytics;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("StatementStatusesPlateParser");

    public StatementStatusesPlateParser(@NotNull StatementStatusesPlateAnalytics analytics) {
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        this.analytics = analytics;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:34:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:50:0x00e6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:? A[LOOP:0: B:35:0x00bd->B:51:?, LOOP_END, SYNTHETIC] */
    @Nullable
    public final StatementStatusesPlate parse(@NotNull JSONArray meta, @Nullable String messageId) {
        List<String> list;
        Intrinsics.checkNotNullParameter(meta, "meta");
        if (meta.length() == 0) {
            LOG.w("You don't have meta with statement statuses");
            return null;
        }
        JSONObject jsonObjectFromJsonArray = JsonUtils.getJsonObjectFromJsonArray(meta, 0, null);
        String stringFromJsonObject = JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, REQUEST_URL, GOSUSLUGI_URL);
        long longFromJsonObject = JsonUtils.getLongFromJsonObject(jsonObjectFromJsonArray, REQUEST_NUMBER, Long.MIN_VALUE);
        String stringFromJsonObject2 = JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, ACTUAL_COMMENT, "");
        String stringFromJsonObject3 = JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, REQUEST_NAME, "");
        String stringFromJsonObject4 = JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, "department", "");
        JSONArray jsonArrayFromJsonObject = JsonUtils.getJsonArrayFromJsonObject(jsonObjectFromJsonArray, STATUS_NAMES);
        String str = messageId == null ? "" : messageId;
        if (jsonArrayFromJsonObject == null) {
            this.analytics.sendStatementStatusesPlateParseError(str, StatementStatusesParserError.STATUS_NAMES_NULL.getCode());
            return null;
        }
        List<String> listString = JsonUtils.getListString(jsonArrayFromJsonObject);
        try {
            boolean z10 = jsonObjectFromJsonArray.getBoolean(IS_FINISH);
            String stringFromJsonObject5 = JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, "start_date", "");
            boolean booleanFromJsonObject = JsonUtils.getBooleanFromJsonObject(jsonObjectFromJsonArray, ACTION_REQUIRED, false);
            String stringFromJsonObject6 = JsonUtils.getStringFromJsonObject(jsonObjectFromJsonArray, ESTIMATED_FINISH_DATE, "");
            if (longFromJsonObject != Long.MIN_VALUE) {
                Intrinsics.checkNotNull(stringFromJsonObject3);
                if (stringFromJsonObject3.length() != 0 && !listString.isEmpty()) {
                    Intrinsics.checkNotNull(stringFromJsonObject5);
                    if (stringFromJsonObject5.length() != 0) {
                        if (!z10) {
                            Intrinsics.checkNotNull(listString);
                            list = listString;
                            if (list instanceof Collection) {
                                for (String str2 : list) {
                                    Intrinsics.checkNotNull(str2);
                                    if (str2.length() == 0) {
                                    }
                                }
                            } else {
                                while (r6.hasNext()) {
                                    Intrinsics.checkNotNull(str2);
                                    if (str2.length() == 0) {
                                    }
                                }
                            }
                            Intrinsics.checkNotNull(stringFromJsonObject);
                            Intrinsics.checkNotNull(stringFromJsonObject2);
                            return new StatementStatusesPlate(stringFromJsonObject3, longFromJsonObject, stringFromJsonObject, stringFromJsonObject5, listString, stringFromJsonObject2, stringFromJsonObject4, z10, booleanFromJsonObject, stringFromJsonObject6);
                        }
                        Intrinsics.checkNotNull(stringFromJsonObject6);
                        if (stringFromJsonObject6.length() != 0) {
                            Intrinsics.checkNotNull(listString);
                            list = listString;
                            if ((list instanceof Collection) || !list.isEmpty()) {
                                while (r6.hasNext()) {
                                    Intrinsics.checkNotNull(str2);
                                    if (str2.length() == 0) {
                                    }
                                }
                            }
                            Intrinsics.checkNotNull(stringFromJsonObject);
                            Intrinsics.checkNotNull(stringFromJsonObject2);
                            return new StatementStatusesPlate(stringFromJsonObject3, longFromJsonObject, stringFromJsonObject, stringFromJsonObject5, listString, stringFromJsonObject2, stringFromJsonObject4, z10, booleanFromJsonObject, stringFromJsonObject6);
                        }
                    }
                }
            }
            this.analytics.sendStatementStatusesPlateParseError(str, StatementStatusesParserError.NOT_VALID_META.getCode());
            LOG.w("Statement statuses parse wrong meta: " + jsonObjectFromJsonArray);
            return null;
        } catch (JSONException e10) {
            this.analytics.sendStatementStatusesPlateParseError(str, StatementStatusesParserError.JSON_EXCEPTION.getCode());
            LOG.w("Statement statuses parse wrong meta, exception: " + e10);
            return null;
        }
    }
}
