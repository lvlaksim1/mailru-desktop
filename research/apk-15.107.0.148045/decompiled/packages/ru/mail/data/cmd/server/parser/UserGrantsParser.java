package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.glasha.db.domain.SystemFolder;
import ru.mail.glasha.db.domain.UserPermissionsEnum;
import ru.mail.glasha.db.entities.UserGrantsDbDto;
import ru.mail.logic.content.FolderType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u000e"}, d2 = {"Lru/mail/data/cmd/server/parser/UserGrantsParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/glasha/db/entities/UserGrantsDbDto;", "<init>", "()V", "needToParse", "", "jsonObject", "Lorg/json/JSONObject;", "parse", "parsePermissions", "", "Lru/mail/glasha/db/domain/UserPermissionsEnum;", "Companion", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUserGrantsParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UserGrantsParser.kt\nru/mail/data/cmd/server/parser/UserGrantsParser\n+ 2 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,103:1\n32#2,2:104\n32#2,2:106\n*S KotlinDebug\n*F\n+ 1 UserGrantsParser.kt\nru/mail/data/cmd/server/parser/UserGrantsParser\n*L\n39#1:104,2\n75#1:106,2\n*E\n"})
public final class UserGrantsParser extends JSONParser<UserGrantsDbDto> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String JSON_BOX = "box";

    @NotNull
    private static final String JSON_EMAIL = "email";

    @NotNull
    private static final String JSON_OWNER = "owner";

    @NotNull
    private static final String JSON_PERMISSIONS = "permissions";

    @NotNull
    private static final String JSON_ROOT = "root";

    @NotNull
    private static final String JSON_SHARED_ITEMS = "shared_items";

    @NotNull
    private static final String JSON_SYSTEM_FOLDERS = "system_folders";

    @NotNull
    private static final String JSON_USER_GRANTS = "user_grants";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/parser/UserGrantsParser$Companion;", "", "<init>", "()V", "JSON_OWNER", "", "JSON_EMAIL", "JSON_USER_GRANTS", "JSON_SYSTEM_FOLDERS", "JSON_PERMISSIONS", "JSON_SHARED_ITEMS", "JSON_BOX", "JSON_ROOT", "parseFromBody", "", "Lru/mail/glasha/db/entities/UserGrantsDbDto;", "body", "Lorg/json/JSONObject;", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final List<UserGrantsDbDto> parseFromBody(@NotNull JSONObject body) throws JSONException {
            Intrinsics.checkNotNullParameter(body, "body");
            if (!body.has("user_grants")) {
                return CollectionsKt.emptyList();
            }
            List<UserGrantsDbDto> list = new UserGrantsParser().parse(body.getJSONArray("user_grants"));
            Intrinsics.checkNotNullExpressionValue(list, "parse(...)");
            return list;
        }

        private Companion() {
        }
    }

    @JvmStatic
    @NotNull
    public static final List<UserGrantsDbDto> parseFromBody(@NotNull JSONObject jSONObject) {
        return INSTANCE.parseFromBody(jSONObject);
    }

    private final Set<UserPermissionsEnum> parsePermissions(JSONObject jsonObject) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<String> itKeys = jsonObject.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (jsonObject.getBoolean(next)) {
                UserPermissionsEnum.Companion companion = UserPermissionsEnum.INSTANCE;
                Intrinsics.checkNotNull(next);
                linkedHashSet.add(companion.fromString(next));
            }
        }
        return linkedHashSet;
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    protected boolean needToParse(@NotNull JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject(JSON_SYSTEM_FOLDERS);
        if (jSONObjectOptJSONObject == null) {
            return false;
        }
        return jSONObjectOptJSONObject.has(JSON_ROOT);
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public UserGrantsDbDto parse(@NotNull JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        JSONObject jSONObject = jsonObject.getJSONObject(JSON_SYSTEM_FOLDERS);
        String strOptString = jsonObject.getJSONObject("owner").optString("email", "");
        JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject(JSON_SHARED_ITEMS);
        boolean zOptBoolean = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optBoolean(JSON_BOX) : false;
        ArrayList arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        SystemFolder systemFolder = new SystemFolder(FolderType.ARCHIVE.getType(), 500010L);
        Iterator<String> itKeys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        long j10 = 500010;
        long folderId = -1;
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Intrinsics.checkNotNull(next);
            long j11 = j10;
            SystemFolder systemFolder2 = new SystemFolder(next, jSONObject.optLong(next, -1L));
            if (Intrinsics.areEqual(next, JSON_ROOT)) {
                folderId = systemFolder2.getFolderId();
            }
            if (Intrinsics.areEqual(next, FolderType.ARCHIVE.getType())) {
                systemFolder = systemFolder2;
            }
            arrayList.add(systemFolder2);
            j10 = j11;
        }
        if (systemFolder.getFolderId() == j10) {
            systemFolder.setFolderId(systemFolder.getFolderId() + folderId);
        }
        if (jsonObject.has(JSON_PERMISSIONS)) {
            JSONObject jSONObject2 = jsonObject.getJSONObject(JSON_PERMISSIONS);
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "getJSONObject(...)");
            CollectionsKt.addAll(linkedHashSet, parsePermissions(jSONObject2));
        }
        if (zOptBoolean) {
            linkedHashSet.add(UserPermissionsEnum.WRITE);
        }
        Intrinsics.checkNotNull(strOptString);
        return new UserGrantsDbDto(folderId, strOptString, arrayList, CollectionsKt.toList(linkedHashSet));
    }
}
