package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "services", "spam"})
public class TornadoSpamAbuse extends TornadoBaseMoveMessage<Params> {
    public TornadoSpamAbuse(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends TornadoBaseMoveMessage.Params {

        @Param(method = HttpMethod.GET, name = "folder", type = Param.Type.STRING)
        private final Long mFolderTo;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @Nullable String... strArr) {
            super(mailboxContext, dataManager, strArr);
            this.mFolderTo = null;
        }

        @Override // ru.mail.data.cmd.server.TornadoBaseMoveMessage.Params, ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if ((obj instanceof Params) && super.equals(obj)) {
                return Objects.equals(this.mFolderTo, ((Params) obj).mFolderTo);
            }
            return false;
        }

        @Override // ru.mail.data.cmd.server.TornadoBaseMoveMessage.Params, ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = super.hashCode() * 31;
            Long l10 = this.mFolderTo;
            return iHashCode + (l10 != null ? l10.hashCode() : 0);
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull Long l10, @Nullable String... strArr) {
            super(mailboxContext, dataManager, strArr);
            this.mFolderTo = l10;
        }
    }

    public TornadoSpamAbuse(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }
}
