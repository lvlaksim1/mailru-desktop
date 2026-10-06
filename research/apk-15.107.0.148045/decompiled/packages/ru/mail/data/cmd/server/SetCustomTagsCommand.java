package ru.mail.data.cmd.server;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u000b\u001a\u00020\fH\u0014J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0014¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/SetCustomTagsCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/SetCustomTagsCommand$Params;", "", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/SetCustomTagsCommand$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Params", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "colortags", "set"})
public final class SetCustomTagsCommand extends PostServerRequest<Params, Unit> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/SetCustomTagsCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "msgIds", "", "defaultTags", "customTags", "folderId", "", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "getMsgIds", "()Ljava/lang/String;", "getDefaultTags", "getCustomTags", "getFolderId", "()J", "mail-cmd_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {

        @Param(method = HttpMethod.POST, name = "custom_tags")
        @Nullable
        private final String customTags;

        @Param(method = HttpMethod.POST, name = "default_tags")
        @Nullable
        private final String defaultTags;

        @Param(method = HttpMethod.POST, name = "folder_id")
        private final long folderId;

        @Param(method = HttpMethod.POST, name = "ids")
        @NotNull
        private final String msgIds;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String msgIds, @Nullable String str, @Nullable String str2, long j10, @NotNull AccountInfo accountInfo, @NotNull FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(msgIds, "msgIds");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            Intrinsics.checkNotNullParameter(folderState, "folderState");
            this.msgIds = msgIds;
            this.defaultTags = str;
            this.customTags = str2;
            this.folderId = j10;
        }

        @Nullable
        public final String getCustomTags() {
            return this.customTags;
        }

        @Nullable
        public final String getDefaultTags() {
            return this.defaultTags;
        }

        public final long getFolderId() {
            return this.folderId;
        }

        @NotNull
        public final String getMsgIds() {
            return this.msgIds;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetCustomTagsCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: renamed from: onPostExecuteRequest, reason: collision with other method in class */
    protected void m15535onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
    }

    public /* synthetic */ SetCustomTagsCommand(Context context, Params params, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, params, (i10 & 4) != 0 ? true : z10);
    }

    @Override // ru.mail.network.NetworkCommand
    public /* bridge */ /* synthetic */ Object onPostExecuteRequest(NetworkCommand.Response response) {
        m15535onPostExecuteRequest(response);
        return Unit.INSTANCE;
    }
}
