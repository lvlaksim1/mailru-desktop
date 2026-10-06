package ru.mail.serverapi;

import android.content.Context;
import androidx.annotation.Keep;
import ru.mail.network.HostProvider;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.serverapi.ServerCommandBaseParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class GetServerRequest<P extends ServerCommandBaseParams, T> extends ServerCommandBase<P, T> {
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String ENCODING_GZIP = "gzip";
    public static final String ENCODING_IDENTITY = "identity";

    @Param(getterName = "getAcceptEncoding", method = HttpMethod.HEADER_ADD, name = "Accept-Encoding", useGetter = true)
    private String mAcceptEncoding;

    public GetServerRequest(Context context, P p10) {
        super(context, p10);
    }

    @Keep
    public String getAcceptEncoding() {
        return "gzip";
    }

    public GetServerRequest(Context context, P p10, HostProvider hostProvider) {
        super(context, p10, hostProvider);
    }
}
