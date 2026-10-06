package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.MailMessage;
import ru.mail.logic.cmd.MarkOperation;
import ru.mail.logic.content.ChangesBitmask;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "marks"})
public class MarkMessageCommand extends PostServerRequest<Params, EmptyResult> {
    private static final Log LOG = Log.getLog("MarkMessageCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends MarkCommandBaseParams<String> {

        /* JADX INFO: compiled from: ProGuard */
        public static class Builder extends MarkCommandBaseParams.Builder<String> {
            public void add(MailMessage mailMessage) {
                ChangesBitmask changesBitmaskBuild = new ChangesBitmask.Builder(mailMessage.getLocalChangesBitmask()).build();
                if (changesBitmaskBuild.isReadUnreadChanged()) {
                    if (mailMessage.isUnread()) {
                        add(MarkOperation.UNREAD_SET, mailMessage.getId(), Long.valueOf(mailMessage.getFolderId()));
                    } else {
                        add(MarkOperation.UNREAD_UNSET, mailMessage.getId(), Long.valueOf(mailMessage.getFolderId()));
                    }
                }
                if (changesBitmaskBuild.isFlagUnflagChanged()) {
                    if (mailMessage.isFlagged()) {
                        add(MarkOperation.FLAG_SET, mailMessage.getId(), Long.valueOf(mailMessage.getFolderId()));
                    } else {
                        add(MarkOperation.FLAG_UNSET, mailMessage.getId(), Long.valueOf(mailMessage.getFolderId()));
                    }
                }
                if (changesBitmaskBuild.isPinUnpinChanged()) {
                    if (mailMessage.isPinned()) {
                        add(MarkOperation.PIN_SET, mailMessage.getId(), Long.valueOf(mailMessage.getFolderId()));
                    } else {
                        add(MarkOperation.PIN_UNSET, mailMessage.getId(), Long.valueOf(mailMessage.getFolderId()));
                    }
                }
            }
        }

        public Params(@NotNull Map<MarkOperation, Map<Long, List<String>>> map, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(map, accountInfo, folderState);
        }

        public static Builder getBuilder() {
            return new Builder();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendEmail() {
            return true;
        }
    }

    public MarkMessageCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    MarkMessageCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        return new EmptyResult();
    }
}
