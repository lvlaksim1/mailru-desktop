package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.glasha.db.domain.GrantsEnum;
import ru.mail.glasha.db.entities.UserGrantsDbDto;
import ru.mail.glasha.domain.models.business.FolderGrants;
import ru.mail.logic.content.EnumShareType;
import ru.mail.logic.content.FolderType;
import ru.mail.util.FolderMatcher;
import ru.mail.utils.UtilExtensionsKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 -2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002,-B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0004\b\n\u0010\u000bJ\u001c\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0014J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J \u0010 \u001a\u0004\u0018\u00010\u0011*\u00020\u001f2\b\u0010!\u001a\u0004\u0018\u00010\u00062\u0006\u0010\"\u001a\u00020\tH\u0002J\u0012\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\b*\u00020\u001fH\u0002J(\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020\u001dH\u0002J\u0016\u0010*\u001a\u0004\u0018\u00010\u0006*\u00020\u001f2\u0006\u0010+\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lru/mail/data/cmd/server/parser/MailboxFolderParser;", "Lru/mail/data/cmd/server/parser/JSONParser;", "Lru/mail/data/entities/MailBoxFolder;", "folderMatcher", "Lru/mail/util/FolderMatcher;", "login", "", "excludedFolderIds", "", "", "<init>", "(Lru/mail/util/FolderMatcher;Ljava/lang/String;Ljava/util/Set;)V", "nestingLevels", "", "", "folderGrants", "", "Lru/mail/glasha/domain/models/business/FolderGrants;", "sharedFoldersStructureFixer", "Lru/mail/data/cmd/server/parser/SharedFoldersStructureFixer;", "parseWithGrants", "Lru/mail/data/cmd/server/parser/MailboxFolderParser$FoldersParserContainer;", "jsonArray", "Lorg/json/JSONArray;", "userGrants", "", "Lru/mail/glasha/db/entities/UserGrantsDbDto;", "parse", "needToParse", "", "jsonObject", "Lorg/json/JSONObject;", "parseFolderGrants", "ownerEmail", "folderId", "parseGrants", "Lru/mail/glasha/db/domain/GrantsEnum;", "getFolderType", "rawType", "isSystem", "isSubFolder", "isShared", "stringOrNull", "key", "FoldersParserContainer", "Companion", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailboxFolderParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailboxFolderParser.kt\nru/mail/data/cmd/server/parser/MailboxFolderParser\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Iterators.kt\nkotlin/collections/CollectionsKt__IteratorsKt\n*L\n1#1,251:1\n1#2:252\n32#3,2:253\n*S KotlinDebug\n*F\n+ 1 MailboxFolderParser.kt\nru/mail/data/cmd/server/parser/MailboxFolderParser\n*L\n174#1:253,2\n*E\n"})
public final class MailboxFolderParser extends JSONParser<MailBoxFolder> {

    @NotNull
    private static final String KEY_ARCHIVE = "archive";

    @NotNull
    private static final String KEY_CHILD = "child";

    @NotNull
    private static final String KEY_EMAIL = "email";

    @NotNull
    private static final String KEY_GRANTS = "grants";

    @NotNull
    private static final String KEY_ID = "id";

    @NotNull
    private static final String KEY_MESSAGES_TOTAL = "messages_total";

    @NotNull
    private static final String KEY_MESSAGES_UNREAD = "messages_unread";

    @NotNull
    private static final String KEY_NAME = "name";

    @NotNull
    private static final String KEY_OWNER = "owner";

    @NotNull
    private static final String KEY_PARENT = "parent";

    @NotNull
    private static final String KEY_SECURITY = "security";

    @NotNull
    private static final String KEY_SHARE = "share";

    @NotNull
    private static final String KEY_SYSTEM = "system";

    @NotNull
    private static final String KEY_THREADS_TOTAL = "threads_total";

    @NotNull
    private static final String KEY_THREADS_UNREAD = "threads_unread";

    @NotNull
    private static final String KEY_TYPE = "type";
    private static final long UNKNOWN_PARENT = -1;

    @NotNull
    private final Set<Long> excludedFolderIds;

    @Nullable
    private List<FolderGrants> folderGrants;

    @NotNull
    private final FolderMatcher folderMatcher;

    @Nullable
    private final String login;

    @Nullable
    private Map<Long, Integer> nestingLevels;

    @Nullable
    private SharedFoldersStructureFixer sharedFoldersStructureFixer;

    @NotNull
    private static final Set<FolderType> NON_SUBFOLDER_SYSTEM_TYPES = SetsKt.setOf((Object[]) new FolderType[]{FolderType.INBOX, FolderType.SENT, FolderType.TRASH, FolderType.CHILD_TRASH, FolderType.DRAFTS, FolderType.ARCHIVE, FolderType.OUTBOX, FolderType.SAFE});

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J9\u0010\u0012\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/parser/MailboxFolderParser$FoldersParserContainer;", "", "folders", "", "Lru/mail/data/entities/MailBoxFolder;", "folderGrants", "Lru/mail/glasha/domain/models/business/FolderGrants;", "userGrants", "Lru/mail/glasha/db/entities/UserGrantsDbDto;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getFolders", "()Ljava/util/List;", "getFolderGrants", "getUserGrants", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class FoldersParserContainer {

        @NotNull
        private final List<FolderGrants> folderGrants;

        @NotNull
        private final List<MailBoxFolder> folders;

        @NotNull
        private final List<UserGrantsDbDto> userGrants;

        /* JADX WARN: Multi-variable type inference failed */
        public FoldersParserContainer(@NotNull List<? extends MailBoxFolder> folders, @NotNull List<FolderGrants> folderGrants, @NotNull List<UserGrantsDbDto> userGrants) {
            Intrinsics.checkNotNullParameter(folders, "folders");
            Intrinsics.checkNotNullParameter(folderGrants, "folderGrants");
            Intrinsics.checkNotNullParameter(userGrants, "userGrants");
            this.folders = folders;
            this.folderGrants = folderGrants;
            this.userGrants = userGrants;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FoldersParserContainer copy$default(FoldersParserContainer foldersParserContainer, List list, List list2, List list3, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                list = foldersParserContainer.folders;
            }
            if ((i10 & 2) != 0) {
                list2 = foldersParserContainer.folderGrants;
            }
            if ((i10 & 4) != 0) {
                list3 = foldersParserContainer.userGrants;
            }
            return foldersParserContainer.copy(list, list2, list3);
        }

        @NotNull
        public final List<MailBoxFolder> component1() {
            return this.folders;
        }

        @NotNull
        public final List<FolderGrants> component2() {
            return this.folderGrants;
        }

        @NotNull
        public final List<UserGrantsDbDto> component3() {
            return this.userGrants;
        }

        @NotNull
        public final FoldersParserContainer copy(@NotNull List<? extends MailBoxFolder> folders, @NotNull List<FolderGrants> folderGrants, @NotNull List<UserGrantsDbDto> userGrants) {
            Intrinsics.checkNotNullParameter(folders, "folders");
            Intrinsics.checkNotNullParameter(folderGrants, "folderGrants");
            Intrinsics.checkNotNullParameter(userGrants, "userGrants");
            return new FoldersParserContainer(folders, folderGrants, userGrants);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof FoldersParserContainer)) {
                return false;
            }
            FoldersParserContainer foldersParserContainer = (FoldersParserContainer) other;
            return Intrinsics.areEqual(this.folders, foldersParserContainer.folders) && Intrinsics.areEqual(this.folderGrants, foldersParserContainer.folderGrants) && Intrinsics.areEqual(this.userGrants, foldersParserContainer.userGrants);
        }

        @NotNull
        public final List<FolderGrants> getFolderGrants() {
            return this.folderGrants;
        }

        @NotNull
        public final List<MailBoxFolder> getFolders() {
            return this.folders;
        }

        @NotNull
        public final List<UserGrantsDbDto> getUserGrants() {
            return this.userGrants;
        }

        public int hashCode() {
            return (((this.folders.hashCode() * 31) + this.folderGrants.hashCode()) * 31) + this.userGrants.hashCode();
        }

        @NotNull
        public String toString() {
            return "FoldersParserContainer(folders=" + this.folders + ", folderGrants=" + this.folderGrants + ", userGrants=" + this.userGrants + ")";
        }
    }

    public MailboxFolderParser(@NotNull FolderMatcher folderMatcher, @Nullable String str, @NotNull Set<Long> excludedFolderIds) {
        Intrinsics.checkNotNullParameter(folderMatcher, "folderMatcher");
        Intrinsics.checkNotNullParameter(excludedFolderIds, "excludedFolderIds");
        this.folderMatcher = folderMatcher;
        this.login = str;
        this.excludedFolderIds = excludedFolderIds;
    }

    private final String getFolderType(String rawType, boolean isSystem, boolean isSubFolder, boolean isShared) {
        if (isShared && isSystem && Intrinsics.areEqual(rawType, FolderType.DEFAULT.getType())) {
            return FolderType.ACCOUNT.getType();
        }
        return (isSubFolder && !isSystem && NON_SUBFOLDER_SYSTEM_TYPES.contains(FolderType.INSTANCE.getFolderEnum(rawType))) ? FolderType.DEFAULT.getType() : rawType;
    }

    private final FolderGrants parseFolderGrants(JSONObject jSONObject, String str, long j10) {
        JSONObject jSONObjectOptJSONObject;
        if (str == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject(KEY_GRANTS)) == null) {
            return null;
        }
        return new FolderGrants(this.login, str, j10, parseGrants(jSONObjectOptJSONObject));
    }

    private final Set<GrantsEnum> parseGrants(JSONObject jSONObject) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<String> itKeys = jSONObject.keys();
        Intrinsics.checkNotNullExpressionValue(itKeys, "keys(...)");
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (jSONObject.optBoolean(next, false)) {
                GrantsEnum.Companion companion = GrantsEnum.INSTANCE;
                Intrinsics.checkNotNull(next);
                linkedHashSet.add(companion.fromString(next));
            }
        }
        return linkedHashSet;
    }

    private final String stringOrNull(JSONObject jSONObject, String str) {
        return (String) UtilExtensionsKt.takeIfNotEmpty(jSONObject.optString(str, ""));
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    protected boolean needToParse(@NotNull JSONObject jsonObject) {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        return !this.excludedFolderIds.contains(Long.valueOf(jsonObject.optLong("id", -1L)));
    }

    @NotNull
    public final FoldersParserContainer parseWithGrants(@NotNull JSONArray jsonArray, @NotNull List<UserGrantsDbDto> userGrants) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        Intrinsics.checkNotNullParameter(userGrants, "userGrants");
        ArrayList arrayList = new ArrayList();
        SharedFoldersStructureFixer sharedFoldersStructureFixer = new SharedFoldersStructureFixer(this.login, userGrants);
        this.folderGrants = arrayList;
        this.sharedFoldersStructureFixer = sharedFoldersStructureFixer;
        List<MailBoxFolder> list = parse(jsonArray);
        this.folderGrants = null;
        this.sharedFoldersStructureFixer = null;
        Pair<List<MailBoxFolder>, List<UserGrantsDbDto>> pairFixAndAppendMissingSystemFolders = sharedFoldersStructureFixer.fixAndAppendMissingSystemFolders(list);
        return new FoldersParserContainer(pairFixAndAppendMissingSystemFolders.component1(), arrayList, pairFixAndAppendMissingSystemFolders.component2());
    }

    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public List<MailBoxFolder> parse(@NotNull JSONArray jsonArray) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        this.nestingLevels = new NestingFoldersLevelCalculation(jsonArray).getNestingLevelsMap();
        SharedFoldersStructureFixer sharedFoldersStructureFixer = this.sharedFoldersStructureFixer;
        if (sharedFoldersStructureFixer != null) {
            sharedFoldersStructureFixer.onStartParse();
        }
        List<MailBoxFolder> list = super.parse(jsonArray);
        Intrinsics.checkNotNullExpressionValue(list, "parse(...)");
        return list;
    }

    public /* synthetic */ MailboxFolderParser(FolderMatcher folderMatcher, String str, Set set, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(folderMatcher, str, (i10 & 4) != 0 ? SetsKt.emptySet() : set);
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0085  */
    @Override // ru.mail.data.cmd.server.parser.JSONParser
    @NotNull
    public MailBoxFolder parse(@NotNull JSONObject jsonObject) throws JSONException {
        Integer num;
        int iIntValue;
        List<FolderGrants> list;
        Integer num2;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        long j10 = jsonObject.getLong("id");
        Object objOpt = jsonObject.opt("name");
        String str = objOpt instanceof String ? (String) objOpt : null;
        FolderType folderType = FolderType.DEFAULT;
        String strOptString = jsonObject.optString("type", folderType.getType());
        boolean zOptBoolean = jsonObject.optBoolean("system", false);
        JSONObject jSONObjectOptJSONObject = jsonObject.optJSONObject("owner");
        String strStringOrNull = jSONObjectOptJSONObject != null ? stringOrNull(jSONObjectOptJSONObject, "email") : null;
        long jOptLong = jsonObject.optLong("parent", -1L);
        boolean zOptBoolean2 = jsonObject.optBoolean("child", false);
        boolean z10 = zOptBoolean && Intrinsics.areEqual(strOptString, folderType.getType());
        SharedFoldersStructureFixer sharedFoldersStructureFixer = this.sharedFoldersStructureFixer;
        if (sharedFoldersStructureFixer != null) {
            sharedFoldersStructureFixer.onSharedFolderParsed(strStringOrNull, j10, this.collectionSize);
        }
        if (strStringOrNull != null && !z10) {
            Map<Long, Integer> map = this.nestingLevels;
            if (map == null || (num2 = map.get(Long.valueOf(j10))) == null) {
                iIntValue = 0;
            } else {
                iIntValue = RangesKt.coerceAtLeast(num2.intValue() - 1, 0);
            }
        } else {
            Map<Long, Integer> map2 = this.nestingLevels;
            if (map2 == null || (num = map2.get(Long.valueOf(j10))) == null) {
                iIntValue = 0;
            } else {
                iIntValue = num.intValue();
            }
        }
        if (this.folderGrants != null) {
            FolderGrants folderGrants = jSONObjectOptJSONObject != null ? parseFolderGrants(jSONObjectOptJSONObject, strStringOrNull, j10) : null;
            if (folderGrants != null && (list = this.folderGrants) != null) {
                list.add(folderGrants);
            }
        }
        MailBoxFolder mailBoxFolder = new MailBoxFolder(str, j10);
        mailBoxFolder.setAccountName(this.login);
        mailBoxFolder.setParentId(jOptLong);
        mailBoxFolder.setOwner(strStringOrNull);
        mailBoxFolder.setNestingLevel(iIntValue);
        if (mailBoxFolder.isShared()) {
            zOptBoolean2 = mailBoxFolder.getNestingLevel() > 0;
        }
        mailBoxFolder.setSubFolder(zOptBoolean2);
        mailBoxFolder.setAccessType(jsonObject.optBoolean("security", false) ? 1 : 0);
        mailBoxFolder.setArchive(jsonObject.optBoolean("archive", false));
        mailBoxFolder.setShare(jsonObject.optString("share", EnumShareType.DEFAULT.getType()));
        mailBoxFolder.setMessagesCount(jsonObject.optInt(KEY_MESSAGES_TOTAL, 0));
        Intrinsics.checkNotNull(strOptString);
        mailBoxFolder.setType(getFolderType(strOptString, zOptBoolean, mailBoxFolder.isSubFolder(), mailBoxFolder.isShared()));
        mailBoxFolder.setIsSystem(this.folderMatcher.isSystem(j10, mailBoxFolder.getType(), zOptBoolean));
        mailBoxFolder.setIsMetaThread(this.folderMatcher.isMetaThread(j10));
        mailBoxFolder.setUnreadCount(jsonObject.optInt(KEY_MESSAGES_UNREAD, 0));
        Integer numValueOf = Integer.valueOf(jsonObject.optInt(KEY_THREADS_UNREAD, -1));
        if (numValueOf.intValue() < 0) {
            numValueOf = null;
        }
        if (numValueOf != null) {
            mailBoxFolder.setUnreadThreadsCount(numValueOf.intValue());
        }
        Integer numValueOf2 = Integer.valueOf(jsonObject.optInt(KEY_THREADS_TOTAL, -1));
        Integer num3 = numValueOf2.intValue() >= 0 ? numValueOf2 : null;
        if (num3 != null) {
            mailBoxFolder.setThreadsCount(num3.intValue());
        }
        return mailBoxFolder;
    }
}
