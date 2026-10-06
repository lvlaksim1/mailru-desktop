package ru.mail.data.cmd.server;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import ru.mail.data.entities.MailBoxFolder;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SurelyFoldersChecker {
    private final Set<Long> mMissingFolders;

    public SurelyFoldersChecker(Collection<MailBoxFolder> collection, boolean z10) {
        this.mMissingFolders = getMissingSurelyFolders(collection, z10);
    }

    private Set<Long> getMissingSurelyFolders(Collection<MailBoxFolder> collection, boolean z10) {
        Set<Long> surelyFolders = getSurelyFolders(z10);
        Iterator<MailBoxFolder> it = collection.iterator();
        while (!surelyFolders.isEmpty() && it.hasNext()) {
            surelyFolders.remove(it.next().getId());
        }
        return surelyFolders;
    }

    private Set<Long> getSurelyFolders(boolean z10) {
        HashSet hashSet = new HashSet();
        hashSet.add(0L);
        hashSet.add(Long.valueOf(MailBoxFolder.FOLDER_ID_DRAFTS));
        hashSet.add(Long.valueOf(MailBoxFolder.FOLDER_ID_SENT));
        if (!z10) {
            hashSet.add(950L);
            hashSet.add(Long.valueOf(MailBoxFolder.getDefaultTrashFolderId()));
        }
        return hashSet;
    }

    public String getErrorString() {
        if (isOk()) {
            return null;
        }
        return "missing Surely Folders id=" + this.mMissingFolders;
    }

    public boolean isOk() {
        return this.mMissingFolders.isEmpty();
    }
}
