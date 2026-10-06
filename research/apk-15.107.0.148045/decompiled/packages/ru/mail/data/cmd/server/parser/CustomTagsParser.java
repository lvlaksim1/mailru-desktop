package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.data.entities.MessageCustomTag;
import ru.mail.data.entities.ThreadCustomTag;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0002\u0011\u0012B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\r0\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0012\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\bH\u0002¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/parser/CustomTagsParser;", "", "<init>", "()V", "parse", "", "Lru/mail/data/cmd/server/parser/CustomTagsParser$CustomTagData;", "jsonObject", "Lorg/json/JSONObject;", "parseAssociations", "Lru/mail/data/entities/MessageCustomTag;", "mail", "Lru/mail/data/entities/MailMessage;", "Lru/mail/data/entities/ThreadCustomTag;", "representation", "Lru/mail/data/entities/MailThreadRepresentation;", "collectCustomTags", "CustomTagData", "Companion", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCustomTagsParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomTagsParser.kt\nru/mail/data/cmd/server/parser/CustomTagsParser\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,85:1\n1563#2:86\n1634#2,3:87\n1563#2:90\n1634#2,3:91\n*S KotlinDebug\n*F\n+ 1 CustomTagsParser.kt\nru/mail/data/cmd/server/parser/CustomTagsParser\n*L\n30#1:86\n30#1:87,3\n44#1:90\n44#1:91,3\n*E\n"})
public final class CustomTagsParser {

    @NotNull
    private static final String KEY_COLOR = "color";

    @NotNull
    private static final String KEY_CUSTOM_TAGS = "custom_tags";

    @NotNull
    private static final String KEY_ID = "id";

    @NotNull
    private static final String KEY_NAME = "name";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/parser/CustomTagsParser$CustomTagData;", "", "id", "", "name", "", "color", "<init>", "(ILjava/lang/String;I)V", "getId", "()I", "getName", "()Ljava/lang/String;", "getColor", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "message_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class CustomTagData {
        private final int color;
        private final int id;

        @NotNull
        private final String name;

        public CustomTagData(int i10, @NotNull String name, int i11) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.id = i10;
            this.name = name;
            this.color = i11;
        }

        public static /* synthetic */ CustomTagData copy$default(CustomTagData customTagData, int i10, String str, int i11, int i12, Object obj) {
            if ((i12 & 1) != 0) {
                i10 = customTagData.id;
            }
            if ((i12 & 2) != 0) {
                str = customTagData.name;
            }
            if ((i12 & 4) != 0) {
                i11 = customTagData.color;
            }
            return customTagData.copy(i10, str, i11);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getId() {
            return this.id;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getColor() {
            return this.color;
        }

        @NotNull
        public final CustomTagData copy(int id2, @NotNull String name, int color) {
            Intrinsics.checkNotNullParameter(name, "name");
            return new CustomTagData(id2, name, color);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof CustomTagData)) {
                return false;
            }
            CustomTagData customTagData = (CustomTagData) other;
            return this.id == customTagData.id && Intrinsics.areEqual(this.name, customTagData.name) && this.color == customTagData.color;
        }

        public final int getColor() {
            return this.color;
        }

        public final int getId() {
            return this.id;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + Integer.hashCode(this.color);
        }

        @NotNull
        public String toString() {
            return "CustomTagData(id=" + this.id + ", name=" + this.name + ", color=" + this.color + ")";
        }
    }

    private final List<CustomTagData> collectCustomTags(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.has("custom_tags")) {
            return CollectionsKt.emptyList();
        }
        JSONArray jSONArray = jSONObject.getJSONArray("custom_tags");
        ArrayList arrayList = new ArrayList();
        int length = jSONArray.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i10);
            int iOptInt = jSONObject2.optInt("id", -1);
            if (iOptInt != -1) {
                String strOptString = jSONObject2.optString("name", "");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                arrayList.add(new CustomTagData(iOptInt, strOptString, jSONObject2.optInt("color", 0)));
            }
        }
        return arrayList;
    }

    @NotNull
    public final List<CustomTagData> parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return collectCustomTags(jsonObject);
    }

    @NotNull
    public final List<MessageCustomTag> parseAssociations(@NotNull JSONObject jsonObject, @NotNull MailMessage mail) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(mail, "mail");
        List<CustomTagData> list = parse(jsonObject);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            int id2 = ((CustomTagData) it.next()).getId();
            String mailMessageId = mail.getMailMessageId();
            Intrinsics.checkNotNullExpressionValue(mailMessageId, "getMailMessageId(...)");
            arrayList.add(new MessageCustomTag(id2, mail, mailMessageId));
        }
        return arrayList;
    }

    @NotNull
    public final List<ThreadCustomTag> parseAssociations(@NotNull JSONObject jsonObject, @NotNull MailThreadRepresentation representation) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        Intrinsics.checkNotNullParameter(representation, "representation");
        List<CustomTagData> list = parse(jsonObject);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ThreadCustomTag(((CustomTagData) it.next()).getId(), representation, representation.getMailThreadId()));
        }
        return arrayList;
    }
}
