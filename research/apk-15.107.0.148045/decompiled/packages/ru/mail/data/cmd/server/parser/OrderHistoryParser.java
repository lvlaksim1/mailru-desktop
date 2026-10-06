package ru.mail.data.cmd.server.parser;

import com.huawei.hms.push.constant.RemoteMessageConst;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.credentialsexchanger.analytics.AnalyticsConstants;
import ru.mail.data.entities.MailMessage;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\"\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\f\u001a\u00020\u0007¨\u0006\u000e"}, d2 = {"Lru/mail/data/cmd/server/parser/OrderHistoryParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/data/cmd/server/parser/OrderHistoryParser$Status;", "<init>", "()V", "parse", "jsonObject", "Lorg/json/JSONObject;", "addStatuses", "", "Lru/mail/data/entities/MailMessage;", "msgs", "json", AnalyticsConstants.KEY.STATUS, "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nOrderHistoryParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OrderHistoryParser.kt\nru/mail/data/cmd/server/parser/OrderHistoryParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,34:1\n1#2:35\n1563#3:36\n1634#3,3:37\n1869#3,2:40\n*S KotlinDebug\n*F\n+ 1 OrderHistoryParser.kt\nru/mail/data/cmd/server/parser/OrderHistoryParser\n*L\n21#1:36\n21#1:37,3\n24#1:40,2\n*E\n"})
public final class OrderHistoryParser extends JSONParser<Status> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/server/parser/OrderHistoryParser$Status;", "", "status", "", "extendStatus", RemoteMessageConst.MSGID, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getStatus", "()Ljava/lang/String;", "getExtendStatus", "getMsgId", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Status {

        @NotNull
        private final String extendStatus;

        @NotNull
        private final String msgId;

        @NotNull
        private final String status;

        public Status(@NotNull String status, @NotNull String extendStatus, @NotNull String msgId) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(extendStatus, "extendStatus");
            Intrinsics.checkNotNullParameter(msgId, "msgId");
            this.status = status;
            this.extendStatus = extendStatus;
            this.msgId = msgId;
        }

        public static /* synthetic */ Status copy$default(Status status, String str, String str2, String str3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = status.status;
            }
            if ((i10 & 2) != 0) {
                str2 = status.extendStatus;
            }
            if ((i10 & 4) != 0) {
                str3 = status.msgId;
            }
            return status.copy(str, str2, str3);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getStatus() {
            return this.status;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getExtendStatus() {
            return this.extendStatus;
        }

        @NotNull
        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getMsgId() {
            return this.msgId;
        }

        @NotNull
        public final Status copy(@NotNull String status, @NotNull String extendStatus, @NotNull String msgId) {
            Intrinsics.checkNotNullParameter(status, "status");
            Intrinsics.checkNotNullParameter(extendStatus, "extendStatus");
            Intrinsics.checkNotNullParameter(msgId, "msgId");
            return new Status(status, extendStatus, msgId);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Status)) {
                return false;
            }
            Status status = (Status) other;
            return Intrinsics.areEqual(this.status, status.status) && Intrinsics.areEqual(this.extendStatus, status.extendStatus) && Intrinsics.areEqual(this.msgId, status.msgId);
        }

        @NotNull
        public final String getExtendStatus() {
            return this.extendStatus;
        }

        @NotNull
        public final String getMsgId() {
            return this.msgId;
        }

        @NotNull
        public final String getStatus() {
            return this.status;
        }

        public int hashCode() {
            return (((this.status.hashCode() * 31) + this.extendStatus.hashCode()) * 31) + this.msgId.hashCode();
        }

        @NotNull
        public String toString() {
            return "Status(status=" + this.status + ", extendStatus=" + this.extendStatus + ", msgId=" + this.msgId + ")";
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0050  */
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public final Collection<MailMessage> addStatuses(@NotNull Collection<? extends MailMessage> msgs, @NotNull JSONObject json) {
        Map mapEmptyMap;
        JSONArray jSONArrayOptJSONArray;
        List<Status> list;
        Intrinsics.checkNotNullParameter(msgs, "msgs");
        Intrinsics.checkNotNullParameter(json, "json");
        JSONObject jSONObjectOptJSONObject = json.optJSONObject(StatementStatusesPlateParser.TRANSACTION_METADATA);
        if (jSONObjectOptJSONObject == null || (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("status_history")) == null || (list = parse(jSONArrayOptJSONArray)) == null) {
            mapEmptyMap = MapsKt.emptyMap();
        } else {
            List<Status> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (Status status : list2) {
                arrayList.add(TuplesKt.to(status.getMsgId(), status));
            }
            mapEmptyMap = MapsKt.toMap(arrayList);
            if (mapEmptyMap == null) {
                mapEmptyMap = MapsKt.emptyMap();
            }
        }
        for (MailMessage mailMessage : msgs) {
            Status status2 = (Status) mapEmptyMap.get(mailMessage.getMailMessageId());
            if (status2 != null) {
                mailMessage.setOrderStatus(status2.getStatus());
                mailMessage.setOrderExtendStatus(status2.getExtendStatus());
            }
        }
        return msgs;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public Status parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        String strOptString = jsonObject.optString("status");
        if (strOptString == null) {
            strOptString = "";
        }
        String strOptString2 = jsonObject.optString("extended_status");
        String str = strOptString2 != null ? strOptString2 : "";
        String string = jsonObject.getString("uidl");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return new Status(strOptString, str, string);
    }
}
