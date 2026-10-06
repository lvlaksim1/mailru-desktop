package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.network.HostProvider;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.WithSampling;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", TornadoSendRequest.FIELD_DRAFT})
@WithSampling
public class TornadoDraftRequest extends TornadoSendRequest {
    public TornadoDraftRequest(Context context, TornadoSendParams tornadoSendParams, boolean z10) {
        this(context, tornadoSendParams, null, z10);
    }

    @Override // ru.mail.data.cmd.server.TornadoSendRequest, ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    TornadoDraftRequest(Context context, TornadoSendParams tornadoSendParams, HostProvider hostProvider, boolean z10) {
        super(context, tornadoSendParams, hostProvider, z10);
    }
}
