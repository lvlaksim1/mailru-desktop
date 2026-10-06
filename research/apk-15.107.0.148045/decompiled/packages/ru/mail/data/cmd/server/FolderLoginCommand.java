package ru.mail.data.cmd.server;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.content.FolderLogin;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0006\u0007B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/FolderLoginCommand;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "Result", "Params", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FolderLoginCommand {

    @NotNull
    public static final FolderLoginCommand INSTANCE = new FolderLoginCommand();

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("FolderLoginCommand");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/FolderLoginCommand$Result;", "", "folderLogin", "Lru/mail/logic/content/FolderLogin;", "<init>", "(Lru/mail/logic/content/FolderLogin;)V", "getFolderLogin", "()Lru/mail/logic/content/FolderLogin;", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Result {

        @NotNull
        private final FolderLogin folderLogin;

        public Result(@NotNull FolderLogin folderLogin) {
            Intrinsics.checkNotNullParameter(folderLogin, "folderLogin");
            this.folderLogin = folderLogin;
        }

        @NotNull
        public final FolderLogin getFolderLogin() {
            return this.folderLogin;
        }
    }

    private FolderLoginCommand() {
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lru/mail/data/cmd/server/FolderLoginCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "folderLogin", "Lru/mail/logic/content/FolderLogin;", "getFolderLogin", "()Lru/mail/logic/content/FolderLogin;", "passwordEncoded", "", "getPasswordEncoded$annotations", "()V", "getPasswordEncoded", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "folder_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Params extends ServerCommandEmailParams {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null) || !super.equals(other)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ru.mail.data.cmd.server.FolderLoginCommand.Params");
            return Intrinsics.areEqual(getFolderLogin(), ((Params) other).getFolderLogin());
        }

        @NotNull
        public abstract FolderLogin getFolderLogin();

        @Nullable
        public final String getPasswordEncoded() {
            try {
                return URLEncoder.encode(getFolderLogin().getPassword(), "UTF-8");
            } catch (UnsupportedEncodingException e10) {
                FolderLoginCommand.LOG.e("No such encoding", e10);
                return null;
            }
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + getFolderLogin().hashCode();
        }

        public static /* synthetic */ void getPasswordEncoded$annotations() {
        }
    }
}
