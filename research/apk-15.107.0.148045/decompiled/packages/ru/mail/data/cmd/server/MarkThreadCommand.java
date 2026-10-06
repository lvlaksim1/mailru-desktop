package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.cmd.MarkOperation;
import ru.mail.logic.content.ChangesBitmask;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "m", "threads", "marks"})
public class MarkThreadCommand extends ThreadPostServerRequest<Params> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends MarkCommandBaseParams<JSONObject> {

        @Param(method = HttpMethod.POST, name = "email")
        private final String mEmail;
        private final HashSet<Integer> mIds;

        /* JADX INFO: compiled from: ProGuard */
        public static class Builder extends MarkCommandBaseParams.Builder<JSONObject> {
            private final HashSet<Integer> mIds = new HashSet<>();

            public static Builder getBuilder() {
                return new Builder();
            }

            public void add(MailThread mailThread, MailBoxFolder mailBoxFolder) {
                add(mailThread.getRepresentationByFolder(mailBoxFolder));
            }

            public Params build(MailboxContext mailboxContext, DataManager dataManager) {
                return new Params(mailboxContext, dataManager, getOperations(), this.mIds);
            }

            public void add(MailThreadRepresentation mailThreadRepresentation) {
                this.mIds.add(mailThreadRepresentation.getId());
                ChangesBitmask changesBitmaskBuild = new ChangesBitmask.Builder(mailThreadRepresentation.getLocalChangesBitmask()).build();
                JSONObject jSONObjectConvertToJson = ThreadPostServerRequest.convertToJson(mailThreadRepresentation);
                if (jSONObjectConvertToJson != null) {
                    if (changesBitmaskBuild.isReadUnreadChanged()) {
                        if (mailThreadRepresentation.isUnread()) {
                            add(MarkOperation.UNREAD_SET, jSONObjectConvertToJson);
                        } else {
                            add(MarkOperation.UNREAD_UNSET, jSONObjectConvertToJson);
                        }
                    }
                    if (changesBitmaskBuild.isFlagUnflagChanged()) {
                        if (mailThreadRepresentation.getFlaggedCount() != 0) {
                            add(MarkOperation.FLAG_SET, jSONObjectConvertToJson);
                        } else {
                            add(MarkOperation.FLAG_UNSET, ThreadPostServerRequest.convertToJsonUsingLatestMsgIdInThread(mailThreadRepresentation));
                        }
                    }
                    if (changesBitmaskBuild.isPinUnpinChanged()) {
                        if (mailThreadRepresentation.getPinnedCount() != 0) {
                            add(MarkOperation.PIN_SET, jSONObjectConvertToJson);
                        } else {
                            add(MarkOperation.PIN_UNSET, ThreadPostServerRequest.convertToJsonUsingLatestMsgIdInThread(mailThreadRepresentation));
                        }
                    }
                }
            }
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull Map<MarkOperation, Map<Long, List<JSONObject>>> map, @NotNull Set<Integer> set) {
            super(map, MailboxContextUtil.getAccountInfo(mailboxContext, dataManager), MailboxContextUtil.getFolderState(mailboxContext));
            HashSet<Integer> hashSet = new HashSet<>();
            this.mIds = hashSet;
            this.mEmail = mailboxContext.getProfile().getLogin();
            hashSet.addAll(set);
        }

        @Override // ru.mail.data.cmd.server.MarkCommandBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Params params = (Params) obj;
                if (!getMarks().equals(params.getMarks())) {
                    return false;
                }
                if (getFolderState() == null ? params.getFolderState() != null : !getFolderState().equals(params.getFolderState())) {
                    return false;
                }
                if (!this.mEmail.equals(params.mEmail)) {
                    return false;
                }
                HashSet<Integer> hashSet = this.mIds;
                HashSet<Integer> hashSet2 = params.mIds;
                if (hashSet == null ? hashSet2 == null : hashSet.equals(hashSet2)) {
                    return true;
                }
            }
            return false;
        }

        public HashSet<Integer> getIds() {
            return this.mIds;
        }

        @Override // ru.mail.data.cmd.server.MarkCommandBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = ((super.hashCode() * 31) + this.mEmail.hashCode()) * 31;
            HashSet<Integer> hashSet = this.mIds;
            return iHashCode + (hashSet != null ? hashSet.hashCode() : 0);
        }
    }

    public MarkThreadCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public MarkThreadCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }
}
