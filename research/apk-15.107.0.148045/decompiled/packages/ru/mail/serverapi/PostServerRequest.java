package ru.mail.serverapi;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.List;
import org.apache.http.NameValuePair;
import ru.mail.network.HostProvider;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Formats;
import ru.mail.util.log.LogFilter;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class PostServerRequest<P extends ServerCommandBaseParams, T> extends ServerCommandBase<P, T> {
    private List<NameValuePair> mPostParams;

    public PostServerRequest(Context context, P p10) {
        super(context, p10);
    }

    protected List<NameValuePair> getPostParams() {
        if (this.mPostParams == null) {
            this.mPostParams = providePostParams();
        }
        return this.mPostParams;
    }

    protected NetworkService.RequestMethod getRequestMethod() {
        return NetworkService.RequestMethod.POST;
    }

    @Override // ru.mail.network.NetworkCommand
    protected final void prepareConnectionForPostRequest(@NonNull NetworkService networkService) throws IOException {
        prepareConnectionForPostRequest(networkService, getRequestMethod());
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        logFilterPrepareTokenFilter.addTokenConstraints(Formats.newJsonFormat("token"), Formats.newJsonFormat("access_token"));
        return logFilterPrepareTokenFilter;
    }

    public PostServerRequest(Context context, P p10, HostProvider hostProvider) {
        super(context, p10, hostProvider);
    }

    public PostServerRequest(Context context, P p10, boolean z10) {
        super(context, p10, z10);
    }

    public PostServerRequest(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
    }
}
