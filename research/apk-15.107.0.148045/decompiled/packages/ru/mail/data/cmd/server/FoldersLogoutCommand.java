package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0011B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bB#\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0007\u0010\u000bJ\n\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/FoldersLogoutCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/FoldersLogoutCommand$Params;", "Lru/mail/mailbox/cmd/EmptyResult;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/FoldersLogoutCommand$Params;)V", "hostProvider", "Lru/mail/network/HostProvider;", "(Landroid/content/Context;Lru/mail/data/cmd/server/FoldersLogoutCommand$Params;Lru/mail/network/HostProvider;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Params", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders", "close"})
public final class FoldersLogoutCommand extends PostServerRequest<Params, EmptyResult> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/FoldersLogoutCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "folders", "", "Lru/mail/data/entities/MailBoxFolder;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/Iterable;Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "ids", "", "getIds", "()Ljava/lang/String;", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nFoldersLogoutCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FoldersLogoutCommand.kt\nru/mail/data/cmd/server/FoldersLogoutCommand$Params\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,50:1\n1563#2:51\n1634#2,3:52\n*S KotlinDebug\n*F\n+ 1 FoldersLogoutCommand.kt\nru/mail/data/cmd/server/FoldersLogoutCommand$Params\n*L\n47#1:51\n47#1:52,3\n*E\n"})
    public static final class Params extends ServerCommandEmailParams {

        @Param(method = HttpMethod.POST, name = "ids")
        @NotNull
        private final String ids;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull Iterable<? extends MailBoxFolder> folders, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(folders, "folders");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(folders, 10));
            Iterator<? extends MailBoxFolder> it = folders.iterator();
            while (it.hasNext()) {
                arrayList.add(String.valueOf(it.next().getId()));
            }
            String string = new JSONArray((Collection) arrayList).toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            this.ids = string;
        }

        @NotNull
        public final String getIds() {
            return this.ids;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FoldersLogoutCommand(@NotNull Context context, @NotNull Params params) {
        this(context, params, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @Nullable
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldersLogoutCommand(@NotNull Context context, @NotNull Params params, @Nullable HostProvider hostProvider) {
        super(context, params, hostProvider, true);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        return new EmptyResult();
    }
}
