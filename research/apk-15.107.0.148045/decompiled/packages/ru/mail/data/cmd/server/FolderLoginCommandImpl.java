package ru.mail.data.cmd.server;

import android.content.Context;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.content.FolderLogin;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001a2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0019\u001aB)\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fB3\b\u0010\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\u000fJ\b\u0010\u0010\u001a\u00020\u0011H\u0014J(\u0010\u0012\u001a\"0\u0013R\u001e\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00030\u00030\u0014H\u0014J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0018H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/data/cmd/server/FolderLoginCommandImpl;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/FolderLoginCommand$Params;", "Lru/mail/data/cmd/server/FolderLoginCommand$Result;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "errorMessage", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/FolderLoginCommand$Params;ZLjava/lang/String;)V", "hostProvider", "Lru/mail/network/HostProvider;", "(Landroid/content/Context;Lru/mail/data/cmd/server/FolderLoginCommand$Params;Lru/mail/network/HostProvider;ZLjava/lang/String;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "getCustomDelegate", "Lru/mail/serverapi/ServerCommandBase$ServerCommandBaseDelegate;", "Lru/mail/serverapi/ServerCommandBase;", "kotlin.jvm.PlatformType", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Params", "Companion", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "folders", "open"})
public final class FolderLoginCommandImpl extends PostServerRequest<FolderLoginCommand.Params, FolderLoginCommand.Result> {

    @NotNull
    public static final String FOLDER_PASSWORD_KEY = "folders[0].secret.folder_password";

    @NotNull
    private final String errorMessage;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FolderLoginCommandImpl(@NotNull Context context, @NotNull FolderLoginCommand.Params params, boolean z10, @NotNull String errorMessage) {
        this(context, params, null, z10, errorMessage);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FolderLoginCommandImpl(@NotNull Context context, @NotNull FolderLoginCommand.Params params, @Nullable HostProvider hostProvider, boolean z10, @NotNull String errorMessage) {
        super(context, params, hostProvider, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(errorMessage, "errorMessage");
        this.errorMessage = errorMessage;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public ServerCommandBase<FolderLoginCommand.Params, FolderLoginCommand.Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<FolderLoginCommand.Params, FolderLoginCommand.Result>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.FolderLoginCommandImpl.getCustomDelegate.1
            {
                super();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onBadRequest(JSONObject responseBody) {
                Intrinsics.checkNotNullParameter(responseBody, "responseBody");
                if (!responseBody.has(FolderLoginCommandImpl.FOLDER_PASSWORD_KEY) || !Intrinsics.areEqual(responseBody.getJSONObject(FolderLoginCommandImpl.FOLDER_PASSWORD_KEY).getString("error"), "invalid")) {
                    CommandStatus<?> commandStatusOnBadRequest = super.onBadRequest(responseBody);
                    Intrinsics.checkNotNullExpressionValue(commandStatusOnBadRequest, "onBadRequest(...)");
                    return commandStatusOnBadRequest;
                }
                ((FolderLoginCommand.Params) FolderLoginCommandImpl.this.getParams()).getFolderLogin().setLoginError(FolderLoginCommandImpl.this.errorMessage);
                CommandStatus<?> commandStatusOnFolderAccessDenied = onFolderAccessDenied();
                Intrinsics.checkNotNullExpressionValue(commandStatusOnFolderAccessDenied, "onFolderAccessDenied(...)");
                return commandStatusOnFolderAccessDenied;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public FolderLoginCommand.Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        return new FolderLoginCommand.Result(((FolderLoginCommand.Params) getParams()).getFolderLogin());
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/server/FolderLoginCommandImpl$Params;", "Lru/mail/data/cmd/server/FolderLoginCommand$Params;", "folderLogin", "Lru/mail/logic/content/FolderLogin;", "account", "Lru/mail/auth/request/AccountInfo;", "folder", "Lru/mail/serverapi/FolderState;", "<init>", "(Lru/mail/logic/content/FolderLogin;Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "getFolderLogin", "()Lru/mail/logic/content/FolderLogin;", "folders", "", "getFolders$annotations", "()V", "getFolders", "()Ljava/lang/String;", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends FolderLoginCommand.Params {

        @NotNull
        private final FolderLogin folderLogin;

        @Param(method = HttpMethod.POST, name = "folders")
        @NotNull
        private final String folders;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull FolderLogin folderLogin, @NotNull AccountInfo account, @Nullable FolderState folderState) throws JSONException {
            super(account, folderState);
            Intrinsics.checkNotNullParameter(folderLogin, "folderLogin");
            Intrinsics.checkNotNullParameter(account, "account");
            this.folderLogin = folderLogin;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", String.valueOf(getFolderLogin().getFolderId()));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("folder_password", getPasswordEncoded());
            jSONObject.put(AccountManagerRepositoryImpl.SECRET_ARG, jSONObject2);
            String string = new JSONArray((Collection) CollectionsKt.listOf(jSONObject)).toString();
            Intrinsics.checkNotNullExpressionValue(string, "run(...)");
            this.folders = string;
        }

        @Override // ru.mail.data.cmd.server.FolderLoginCommand.Params
        @NotNull
        public FolderLogin getFolderLogin() {
            return this.folderLogin;
        }

        @NotNull
        public final String getFolders() {
            return this.folders;
        }

        public static /* synthetic */ void getFolders$annotations() {
        }
    }
}
