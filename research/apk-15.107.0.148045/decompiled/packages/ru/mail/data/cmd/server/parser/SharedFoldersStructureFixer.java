package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.glasha.db.domain.SystemFolder;
import ru.mail.glasha.db.entities.UserGrantsDbDto;
import ru.mail.logic.content.EnumShareType;
import ru.mail.logic.content.FolderType;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010%\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000fJ,\u0010\u0016\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lru/mail/data/cmd/server/parser/SharedFoldersStructureFixer;", "", "login", "", "userGrants", "", "Lru/mail/glasha/db/entities/UserGrantsDbDto;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "remainingSystemFoldersByOwner", "", "", "", "Lru/mail/glasha/db/domain/SystemFolder;", "ownerSegments", "", "onStartParse", "", "onSharedFolderParsed", "ownerEmail", "folderId", "currentIndex", "fixAndAppendMissingSystemFolders", "Lkotlin/Pair;", "Lru/mail/data/entities/MailBoxFolder;", "folders", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSharedFoldersStructureFixer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedFoldersStructureFixer.kt\nru/mail/data/cmd/server/parser/SharedFoldersStructureFixer\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,139:1\n1193#2,2:140\n1267#2,2:142\n1236#2,4:144\n1270#2:148\n1267#2,2:149\n1634#2,3:151\n1270#2:154\n1869#2:155\n1617#2,9:156\n1869#2:165\n1870#2:167\n1626#2:168\n1870#2:176\n1878#2,2:177\n1880#2:186\n1563#2:187\n1634#2,3:188\n1#3:166\n382#4,7:169\n382#4,7:179\n*S KotlinDebug\n*F\n+ 1 SharedFoldersStructureFixer.kt\nru/mail/data/cmd/server/parser/SharedFoldersStructureFixer\n*L\n25#1:140,2\n25#1:142,2\n26#1:144,4\n25#1:148\n60#1:149,2\n61#1:151,3\n60#1:154\n71#1:155\n79#1:156,9\n79#1:165\n79#1:167\n79#1:168\n71#1:176\n106#1:177,2\n106#1:186\n127#1:187\n127#1:188,3\n79#1:166\n97#1:169,7\n114#1:179,7\n*E\n"})
public final class SharedFoldersStructureFixer {

    @Nullable
    private final String login;

    @NotNull
    private final Map<String, Integer> ownerSegments;

    @NotNull
    private final Map<String, Map<Long, SystemFolder>> remainingSystemFoldersByOwner;

    @NotNull
    private final List<UserGrantsDbDto> userGrants;

    public SharedFoldersStructureFixer(@Nullable String str, @NotNull List<UserGrantsDbDto> userGrants) {
        Intrinsics.checkNotNullParameter(userGrants, "userGrants");
        this.login = str;
        this.userGrants = userGrants;
        List<UserGrantsDbDto> list = userGrants;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (UserGrantsDbDto userGrantsDbDto : list) {
            String ownerEmail = userGrantsDbDto.getOwnerEmail();
            List<SystemFolder> folders = userGrantsDbDto.getFolders();
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            for (Object obj : folders) {
                linkedHashMap2.put(Long.valueOf(((SystemFolder) obj).getFolderId()), obj);
            }
            Pair pair = TuplesKt.to(ownerEmail, linkedHashMap2);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        this.remainingSystemFoldersByOwner = linkedHashMap;
        this.ownerSegments = new LinkedHashMap();
    }

    @NotNull
    public final Pair<List<MailBoxFolder>, List<UserGrantsDbDto>> fixAndAppendMissingSystemFolders(@NotNull List<? extends MailBoxFolder> folders) {
        Set set;
        Integer num;
        int i10;
        MailBoxFolder mailBoxFolder;
        Intrinsics.checkNotNullParameter(folders, "folders");
        List<UserGrantsDbDto> list = this.userGrants;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (UserGrantsDbDto userGrantsDbDto : list) {
            String ownerEmail = userGrantsDbDto.getOwnerEmail();
            List<SystemFolder> folders2 = userGrantsDbDto.getFolders();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<T> it = folders2.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(Long.valueOf(((SystemFolder) it.next()).getFolderId()));
            }
            Pair pair = TuplesKt.to(ownerEmail, linkedHashSet);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        int size = 0;
        for (UserGrantsDbDto userGrantsDbDto2 : this.userGrants) {
            String ownerEmail2 = userGrantsDbDto2.getOwnerEmail();
            Map<Long, SystemFolder> map = this.remainingSystemFoldersByOwner.get(ownerEmail2);
            if (map == null || map.isEmpty() || (num = this.ownerSegments.get(ownerEmail2)) == null) {
                i10 = size;
            } else {
                int iIntValue = num.intValue();
                long rootId = userGrantsDbDto2.getRootId();
                List<SystemFolder> folders3 = userGrantsDbDto2.getFolders();
                ArrayList arrayList = new ArrayList();
                for (Iterator it2 = folders3.iterator(); it2.hasNext(); it2 = it2) {
                    SystemFolder systemFolder = map.get(Long.valueOf(((SystemFolder) it2.next()).getFolderId()));
                    if (systemFolder != null) {
                        mailBoxFolder = new MailBoxFolder(systemFolder.getName(), systemFolder.getFolderId());
                        mailBoxFolder.setAccountName(this.login);
                        mailBoxFolder.setOwner(ownerEmail2);
                        mailBoxFolder.setParentId(rootId);
                        mailBoxFolder.setShare(EnumShareType.HIDE.getType());
                        mailBoxFolder.setType(FolderType.INSTANCE.getFolderEnum(systemFolder.getName()).getType());
                        mailBoxFolder.setIsSystem(true);
                        mailBoxFolder.setNestingLevel(0);
                    } else {
                        mailBoxFolder = null;
                    }
                    if (mailBoxFolder != null) {
                        arrayList.add(mailBoxFolder);
                    }
                    size = size;
                }
                i10 = size;
                if (!arrayList.isEmpty()) {
                    Integer numValueOf = Integer.valueOf(RangesKt.coerceAtMost(iIntValue + 1, folders.size()));
                    Object arrayList2 = linkedHashMap3.get(numValueOf);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        linkedHashMap3.put(numValueOf, arrayList2);
                    }
                    ((List) arrayList2).addAll(arrayList);
                    size = i10 + arrayList.size();
                }
            }
            size = i10;
        }
        int i11 = 0;
        ArrayList arrayList3 = new ArrayList(folders.size() + size);
        for (Object obj : folders) {
            int i12 = i11 + 1;
            if (i11 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            MailBoxFolder mailBoxFolder2 = (MailBoxFolder) obj;
            String owner = mailBoxFolder2.getOwner();
            if (owner != null && mailBoxFolder2.isSystem() && (set = (Set) linkedHashMap.get(owner)) != null) {
                Long id2 = mailBoxFolder2.getId();
                Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
                if (set.add(id2)) {
                    Object arrayList4 = linkedHashMap2.get(owner);
                    if (arrayList4 == null) {
                        arrayList4 = new ArrayList();
                        linkedHashMap2.put(owner, arrayList4);
                    }
                    String type = mailBoxFolder2.getType();
                    Intrinsics.checkNotNullExpressionValue(type, "getType(...)");
                    Long id3 = mailBoxFolder2.getId();
                    Intrinsics.checkNotNullExpressionValue(id3, "getId(...)");
                    ((List) arrayList4).add(new SystemFolder(type, id3.longValue()));
                }
            }
            arrayList3.add(mailBoxFolder2);
            List list2 = (List) linkedHashMap3.remove(Integer.valueOf(i12));
            if (list2 != null) {
                CollectionsKt.addAll(arrayList3, list2);
            }
            i11 = i12;
        }
        List<UserGrantsDbDto> list3 = this.userGrants;
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (UserGrantsDbDto userGrantsDbDtoCopy$default : list3) {
            List list4 = (List) linkedHashMap2.get(userGrantsDbDtoCopy$default.getOwnerEmail());
            List list5 = list4;
            if (list5 != null && !list5.isEmpty()) {
                userGrantsDbDtoCopy$default = UserGrantsDbDto.copy$default(userGrantsDbDtoCopy$default, 0L, null, CollectionsKt.plus((Collection) userGrantsDbDtoCopy$default.getFolders(), (Iterable) list4), null, 11, null);
            }
            arrayList5.add(userGrantsDbDtoCopy$default);
        }
        return TuplesKt.to(arrayList3, arrayList5);
    }

    public final void onSharedFolderParsed(@Nullable String ownerEmail, long folderId, int currentIndex) {
        if (ownerEmail == null) {
            return;
        }
        Map<Long, SystemFolder> map = this.remainingSystemFoldersByOwner.get(ownerEmail);
        if (map != null) {
            map.remove(Long.valueOf(folderId));
        }
        this.ownerSegments.put(ownerEmail, Integer.valueOf(currentIndex));
    }

    public final void onStartParse() {
        this.ownerSegments.clear();
    }
}
