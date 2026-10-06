package ru.mail.data.cmd.server;

import android.content.Context;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "m", "threads", "services", "spam"})
public class SpamThreadCommand extends ThreadPostServerRequest<Params> {
    private static final Log LOG = Log.getLog("SpamThreadCommand");
    private static final String PARAM_KEY_VERIFIED = "verified";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ThreadPostBaseParams {

        @Param(method = HttpMethod.POST, name = SpamThreadCommand.PARAM_KEY_VERIFIED)
        private final boolean mIsVerified;

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull Collection<MailThreadRepresentation> collection) {
            this(mailboxContext, dataManager, false, collection);
        }

        @Override // ru.mail.data.cmd.server.ThreadPostBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Params) && super.equals(obj) && this.mIsVerified == ((Params) obj).mIsVerified;
        }

        @Override // ru.mail.data.cmd.server.ThreadPostBaseParams, ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + (this.mIsVerified ? 1231 : 1237);
        }

        public Params(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, boolean z10, @NotNull Collection<MailThreadRepresentation> collection) {
            super(mailboxContext, dataManager, collection);
            this.mIsVerified = z10;
        }
    }

    public SpamThreadCommand(Context context, Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public SpamThreadCommand(Context context, Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }
}
