package ru.mail.data.cmd.server;

import android.content.Context;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.mailbox.cmd.ProgressObservable;
import ru.mail.network.HostProvider;
import ru.mail.network.ProgressOutputStream;
import ru.mail.network.ProgressUpdatable;
import ru.mail.network.requestbody.MultipartRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.serverapi.WithSampling;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@WithSampling
public abstract class UploadFileCommand<P extends ServerCommandBaseParams, T, V> extends PostServerRequest<P, T> implements ProgressOutputStream.ClosableConnection, ProgressObservable<V>, ProgressUpdatable {
    private long mBodySize;
    private final CopyOnWriteArrayList<ProgressListener<V>> mProgressObservers;

    UploadFileCommand(Context context, P p10, HostProvider hostProvider, boolean z10) {
        super(context, p10, hostProvider, z10);
        this.mProgressObservers = new CopyOnWriteArrayList<>();
    }

    private void setContentLength(NetworkService networkService, long j10) {
        networkService.setFixedLengthStreamingMode(j10);
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public void addObserver(ProgressListener<V> progressListener) {
        this.mProgressObservers.add(progressListener);
    }

    @Override // ru.mail.network.ProgressOutputStream.ClosableConnection
    public void disconnect() {
        getNetworkService().disconnect();
    }

    @Override // ru.mail.network.NetworkCommand
    protected void encodeRequestBody(NetworkService networkService, RequestBody requestBody) throws IOException {
        if (isCancelled()) {
            return;
        }
        long jPrepareBodySize = prepareBodySize(networkService);
        this.mBodySize = jPrepareBodySize;
        networkService.setRequestProperty("Content-Length", String.valueOf(jPrepareBodySize));
        setContentLength(networkService, this.mBodySize);
        NetworkService.ContentType contentType = networkService.getContentType(requestBody, this);
        networkService.addRequestProperty(contentType.getName(), contentType.getValue());
        super.encodeRequestBody(networkService, requestBody);
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public List<ProgressListener<V>> getObservers() {
        return this.mProgressObservers;
    }

    @Override // ru.mail.network.ProgressOutputStream.ClosableConnection
    public boolean isCloseRequested() {
        return isCancelled();
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public void notifyObservers(V v10) {
        for (ProgressListener<V> progressListener : this.mProgressObservers) {
            if (progressListener != null) {
                progressListener.updateProgress(v10);
            }
        }
    }

    protected abstract void onPrepareMultipartBody(MultipartRequestBody multipartRequestBody) throws IOException;

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected RequestBody onPrepareRequestBody() throws IOException {
        MultipartRequestBody multipartRequestBody = new MultipartRequestBody(getPostParams());
        onPrepareMultipartBody(multipartRequestBody);
        return multipartRequestBody;
    }

    protected long prepareBodySize(NetworkService networkService) throws IOException {
        return networkService.getContentLength(onPrepareRequestBody(), this);
    }

    @Override // ru.mail.mailbox.cmd.ProgressObservable
    public void removeObserver(ProgressListener<V> progressListener) {
        this.mProgressObservers.remove(progressListener);
    }

    @Override // ru.mail.network.NetworkCommand, ru.mail.network.OutputStreamWrapper
    public OutputStream wrapOutputStream(OutputStream outputStream) {
        return new ProgressOutputStream(this, this, super.wrapOutputStream(outputStream), this.mBodySize);
    }
}
