package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.network.HostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "m", "threads", "services", "unspam"})
public class UnspamThreadCommand extends ThreadPostServerRequest<ThreadPostBaseParams> {
    private static final Log LOG = Log.getLog("UnspamThreadCommand");

    public UnspamThreadCommand(Context context, ThreadPostBaseParams threadPostBaseParams, boolean z10) {
        this(context, threadPostBaseParams, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public UnspamThreadCommand(Context context, ThreadPostBaseParams threadPostBaseParams, HostProvider hostProvider, boolean z10) {
        super(context, threadPostBaseParams, hostProvider, z10);
    }
}
