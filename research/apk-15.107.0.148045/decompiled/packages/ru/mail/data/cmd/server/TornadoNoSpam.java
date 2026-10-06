package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.network.HostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "services", "unspam"})
public class TornadoNoSpam extends TornadoBaseMoveMessage<TornadoBaseMoveMessage.Params> {
    public TornadoNoSpam(Context context, TornadoBaseMoveMessage.Params params, boolean z10) {
        this(context, params, null, z10);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    public TornadoNoSpam(Context context, TornadoBaseMoveMessage.Params params, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
    }
}
