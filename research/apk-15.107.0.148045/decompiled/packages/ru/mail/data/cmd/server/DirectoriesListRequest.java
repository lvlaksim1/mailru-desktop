package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.parser.MailboxFolderParser;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.logic.cmd.FolderContainer;
import ru.mail.logic.content.FoldersNotifyer;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.FolderMatcher;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001e2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u001eBE\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0010\u0010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0016\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u0016\u0010\u001c\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001dH\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lru/mail/data/cmd/server/DirectoriesListRequest;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/serverapi/ServerCommandEmailParams;", "Lru/mail/logic/cmd/FolderContainer;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "folderMatcher", "Lru/mail/util/FolderMatcher;", "notifyer", "Lru/mail/logic/content/FoldersNotifyer;", "isChild", "hostProvider", "Lru/mail/network/HostProvider;", "<init>", "(Landroid/content/Context;Lru/mail/serverapi/ServerCommandEmailParams;ZLru/mail/util/FolderMatcher;Lru/mail/logic/content/FoldersNotifyer;ZLru/mail/network/HostProvider;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "notifyFoldersUpdatedListeners", "", "folders", "", "Lru/mail/data/entities/MailBoxFolder;", "checkForAllBaseFolders", "", "Companion", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders"})
public final class DirectoriesListRequest extends ServerCommandBase<ServerCommandEmailParams, FolderContainer> {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("DirectoriesListRequest");

    @NotNull
    private final FolderMatcher folderMatcher;
    private final boolean isChild;

    @NotNull
    private final FoldersNotifyer notifyer;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DirectoriesListRequest(@NotNull Context context, @NotNull ServerCommandEmailParams params, boolean z10, @NotNull FolderMatcher folderMatcher, @NotNull FoldersNotifyer notifyer, boolean z11) {
        this(context, params, z10, folderMatcher, notifyer, z11, null, 64, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(folderMatcher, "folderMatcher");
        Intrinsics.checkNotNullParameter(notifyer, "notifyer");
    }

    private final void checkForAllBaseFolders(Collection<? extends MailBoxFolder> folders) throws NetworkCommand.PostExecuteException {
        SurelyFoldersChecker surelyFoldersChecker = new SurelyFoldersChecker(folders, this.isChild);
        if (surelyFoldersChecker.isOk()) {
            return;
        }
        LOG.e(surelyFoldersChecker.getErrorString());
        throw new NetworkCommand.PostExecuteException(surelyFoldersChecker.getErrorString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void notifyFoldersUpdatedListeners(List<? extends MailBoxFolder> folders) {
        this.notifyer.notifyFoldersUpdated(folders, ((ServerCommandEmailParams) getParams()).getLogin());
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public DirectoriesListRequest(@NotNull Context context, @NotNull ServerCommandEmailParams params, boolean z10, @NotNull FolderMatcher folderMatcher, @NotNull FoldersNotifyer notifyer, boolean z11, @Nullable HostProvider hostProvider) {
        super(context, params, hostProvider, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(folderMatcher, "folderMatcher");
        Intrinsics.checkNotNullParameter(notifyer, "notifyer");
        this.folderMatcher = folderMatcher;
        this.notifyer = notifyer;
        this.isChild = z11;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public FolderContainer onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws JSONException, NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        JSONArray jSONArray = new JSONObject(resp.getRespString()).getJSONArray("body");
        MailboxFolderParser mailboxFolderParser = new MailboxFolderParser(this.folderMatcher, ((ServerCommandEmailParams) getParams()).getLogin(), SetsKt.setOf(500003L));
        try {
            Intrinsics.checkNotNull(jSONArray);
            MailboxFolderParser.FoldersParserContainer withGrants = mailboxFolderParser.parseWithGrants(jSONArray, CollectionsKt.emptyList());
            checkForAllBaseFolders(withGrants.getFolders());
            notifyFoldersUpdatedListeners(withGrants.getFolders());
            return new FolderContainer(withGrants.getFolders(), withGrants.getFolderGrants(), null, 4, null);
        } catch (JSONException e10) {
            LOG.e("wtf???", e10);
            throw new NetworkCommand.PostExecuteException();
        }
    }

    public /* synthetic */ DirectoriesListRequest(Context context, ServerCommandEmailParams serverCommandEmailParams, boolean z10, FolderMatcher folderMatcher, FoldersNotifyer foldersNotifyer, boolean z11, HostProvider hostProvider, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, serverCommandEmailParams, z10, folderMatcher, foldersNotifyer, z11, (i10 & 64) != 0 ? null : hostProvider);
    }
}
