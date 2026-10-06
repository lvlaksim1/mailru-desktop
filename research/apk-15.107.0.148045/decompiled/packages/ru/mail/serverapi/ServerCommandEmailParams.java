package ru.mail.serverapi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class ServerCommandEmailParams extends ServerCommandBaseParams {
    public ServerCommandEmailParams(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
        super(accountInfo, folderState);
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    protected boolean needAppendEmail() {
        return true;
    }
}
