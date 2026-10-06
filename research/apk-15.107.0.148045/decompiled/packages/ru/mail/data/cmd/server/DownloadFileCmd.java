package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import ru.mail.data.cmd.save.FileReceiver;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.GetServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@WithSampling
public class DownloadFileCmd extends GetServerRequest<Params, Result> {
    private static final Log LOG = Log.getLog("DownloadFileCmd");
    private FileReceiver mStreamReceiver;

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {
        private final File mTargetFile;
        private final String mUrl;

        public Params(@NonNull File file, @NonNull String str) {
            this.mTargetFile = file;
            this.mUrl = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
                return false;
            }
            Params params = (Params) obj;
            return Objects.equals(this.mTargetFile, params.mTargetFile) && Objects.equals(this.mUrl, params.mUrl);
        }

        public File getTargetFile() {
            return this.mTargetFile;
        }

        public String getUrl() {
            return this.mUrl;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return Objects.hash(Integer.valueOf(super.hashCode()), this.mTargetFile, this.mUrl);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final File mFile;

        public Result(File file) {
            this.mFile = file;
        }

        public File getLoadFile() {
            return this.mFile;
        }
    }

    public DownloadFileCmd(Context context, Params params) {
        super(context, params);
        File targetFile = params.getTargetFile();
        try {
            if (!targetFile.getParentFile().exists() && !targetFile.getParentFile().mkdirs()) {
                throw new IOException("Could not create directory: " + targetFile.getParent());
            }
            if (targetFile.isDirectory()) {
                throw new IllegalArgumentException("Target file must not be a directory!");
            }
            if (targetFile.exists() && !targetFile.delete()) {
                throw new IllegalStateException("Could not delete old file!");
            }
            if (!targetFile.createNewFile()) {
                throw new IllegalStateException("Could not create target file!");
            }
            this.mStreamReceiver = new FileReceiver(targetFile, context);
        } catch (IOException e10) {
            LOG.w("Downloading file failed", e10);
            setResult((CommandStatus<?>) new CommandStatus.ERROR());
        }
    }

    @Override // ru.mail.network.NetworkCommand
    @NonNull
    protected HostProvider getHostProvider() {
        final HostProvider hostProvider = super.getHostProvider();
        return new HostProvider() { // from class: ru.mail.data.cmd.server.DownloadFileCmd.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.network.HostProvider
            public Uri.Builder getUrlBuilder() {
                Uri uri = Uri.parse(((Params) DownloadFileCmd.this.getParams()).getUrl());
                return new Uri.Builder().scheme(uri.getScheme()).encodedAuthority(uri.getEncodedAuthority()).encodedPath(uri.getPath());
            }

            @Override // ru.mail.network.HostProvider
            public String getUserAgent() {
                return hostProvider.getUserAgent();
            }

            @Override // ru.mail.network.HostProvider
            public void getPlatformSpecificParams(Uri.Builder builder) {
            }

            @Override // ru.mail.network.HostProvider
            public void sign(Uri.Builder builder, HostProvider.SignCreator signCreator) {
            }
        };
    }

    @Override // ru.mail.network.NetworkCommand
    protected byte[] getResponseData(InputStream inputStream) throws IOException {
        if (!isCancelled()) {
            this.mStreamReceiver.receive(inputStream);
        }
        return new byte[0];
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    @Override // ru.mail.mailbox.cmd.Command
    public void onCancelled() {
        super.onCancelled();
        this.mStreamReceiver.abort();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, Result>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Result>.LegacyDelegate() { // from class: ru.mail.data.cmd.server.DownloadFileCmd.2
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onError(NetworkCommand.Response response) {
                DownloadFileCmd.this.mStreamReceiver.abort();
                return super.onError(response);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand, ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        super.onExecute(executorSelector);
        return this.mStreamReceiver.isDstFileExists() ? new CommandStatus.OK(new Result(this.mStreamReceiver.getFile().getFile())) : new CommandStatus.ERROR();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NonNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        if (this.mStreamReceiver.isReceived()) {
            return new Result(this.mStreamReceiver.getFile().getFile());
        }
        throw new NetworkCommand.PostExecuteException("Error while saving file");
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
