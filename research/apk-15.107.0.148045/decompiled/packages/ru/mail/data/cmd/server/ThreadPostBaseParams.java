package ru.mail.data.cmd.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class ThreadPostBaseParams extends ServerCommandBaseParams {
    private static final String PARAM_KEY_THREAD_IDS = "ids";

    @Param(method = HttpMethod.POST, name = "email")
    private final String mEmail;

    @Param(getterName = "getSerializedRepresentations", method = HttpMethod.POST, name = PARAM_KEY_THREAD_IDS, useGetter = true)
    private final Collection<MailThreadRepresentation> mRepresentations;

    /* JADX INFO: compiled from: ProGuard */
    public static class Builder {
        private final Collection<MailThreadRepresentation> mRepresentations = new LinkedList();

        public void add(MailThread mailThread, MailBoxFolder mailBoxFolder) {
            this.mRepresentations.add(mailThread.getRepresentationByFolder(mailBoxFolder));
        }

        public Collection<MailThreadRepresentation> getRepresentations() {
            return this.mRepresentations;
        }

        public void add(MailThreadRepresentation mailThreadRepresentation) {
            this.mRepresentations.add(mailThreadRepresentation);
        }

        public void add(MailThreadRepresentation mailThreadRepresentation, long j10) {
            MailThreadRepresentation mailThreadRepresentation2 = new MailThreadRepresentation(mailThreadRepresentation);
            mailThreadRepresentation2.setFolderId(j10);
            this.mRepresentations.add(mailThreadRepresentation2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final Collection<String> mThreadIds;

        public Result(Collection<String> collection) {
            this.mThreadIds = collection;
        }

        public Collection<String> getThreadIds() {
            return this.mThreadIds;
        }
    }

    public ThreadPostBaseParams(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull Collection<MailThreadRepresentation> collection) {
        super(MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
        this.mEmail = mailboxContext.getProfile().getLogin();
        this.mRepresentations = new ArrayList(collection);
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        ThreadPostBaseParams threadPostBaseParams = (ThreadPostBaseParams) obj;
        return this.mEmail.equals(threadPostBaseParams.mEmail) && getSerializedRepresentations().equals(threadPostBaseParams.getSerializedRepresentations());
    }

    public Set<Long> getFolderRepresentationIds() {
        TreeSet treeSet = new TreeSet();
        Iterator<MailThreadRepresentation> it = this.mRepresentations.iterator();
        while (it.hasNext()) {
            treeSet.add(Long.valueOf(it.next().getFolderId()));
        }
        return treeSet;
    }

    public Set<Integer> getRepresentationIds() {
        HashSet hashSet = new HashSet();
        Iterator<MailThreadRepresentation> it = this.mRepresentations.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        return hashSet;
    }

    public String getSerializedRepresentations() {
        JSONArray jSONArray = new JSONArray();
        Iterator<MailThreadRepresentation> it = this.mRepresentations.iterator();
        while (it.hasNext()) {
            jSONArray.put(ThreadPostServerRequest.convertToJson(it.next()));
        }
        return jSONArray.toString();
    }

    public Set<String> getThreadIds() {
        HashSet hashSet = new HashSet();
        Iterator<MailThreadRepresentation> it = this.mRepresentations.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getMailThread().getId());
        }
        return hashSet;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        return (((super.hashCode() * 31) + this.mEmail.hashCode()) * 31) + getSerializedRepresentations().hashCode();
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public String toString() {
        return getClass().getSimpleName() + " serialized: '" + getSerializedRepresentations() + "', items: " + this.mRepresentations;
    }
}
