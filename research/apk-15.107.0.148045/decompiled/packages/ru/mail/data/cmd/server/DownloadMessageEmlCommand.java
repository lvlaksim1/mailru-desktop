package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.cmd.StreamReceiver;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.r7editor.impl.domain.analytics.R7Analytics;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000  2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001e\u001f B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000eH\u0014J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000eH\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J(\u0010\u001a\u001a\"0\u001bR\u001e\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001d*\u0004\u0018\u00010\u00030\u00030\u001cH\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/data/cmd/server/DownloadMessageEmlCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/DownloadMessageEmlCommand$Params;", "Ljava/io/File;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/DownloadMessageEmlCommand$Params;)V", "fileReceiver", "Lru/mail/data/cmd/server/DownloadMessageEmlCommand$EmlFileReceiver;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "isSupportOAuthAuthorization", "", "isStringResponse", "getResponseData", "", "inputStream", "Ljava/io/InputStream;", "isExpectedContentType", "onCancelled", "", "onPostExecuteRequest", "response", "Lru/mail/network/NetworkCommand$Response;", "getCustomDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "EmlFileReceiver", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "message", R7Analytics.EVENT_DOWNLOAD})
@SourceDebugExtension({"SMAP\nDownloadMessageEmlCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DownloadMessageEmlCommand.kt\nru/mail/data/cmd/server/DownloadMessageEmlCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,130:1\n1761#2,3:131\n*S KotlinDebug\n*F\n+ 1 DownloadMessageEmlCommand.kt\nru/mail/data/cmd/server/DownloadMessageEmlCommand\n*L\n49#1:131,3\n*E\n"})
public final class DownloadMessageEmlCommand extends PostServerRequest<Params, File> {

    @NotNull
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    @NotNull
    private final EmlFileReceiver fileReceiver;
    public static final int $stable = 8;

    @NotNull
    private static final List<String> EXPECTED_CONTENT_TYPES = CollectionsKt.listOf((Object[]) new String[]{"message/rfc822", "application/octet-stream"});

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0002H\u0014J\b\u0010\b\u001a\u00020\tH\u0014J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lru/mail/data/cmd/server/DownloadMessageEmlCommand$EmlFileReceiver;", "Lru/mail/logic/cmd/StreamReceiver;", "Ljava/io/OutputStream;", "destination", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "createOutputStream", "closeOutputStream", "", "toInputStream", "Ljava/io/InputStream;", "clearResourcesOnError", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nDownloadMessageEmlCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DownloadMessageEmlCommand.kt\nru/mail/data/cmd/server/DownloadMessageEmlCommand$EmlFileReceiver\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,130:1\n1#2:131\n*E\n"})
    public static final class EmlFileReceiver extends StreamReceiver<OutputStream> {
        public static final int $stable = 8;

        @NotNull
        private final File destination;

        public EmlFileReceiver(@NotNull File destination) {
            Intrinsics.checkNotNullParameter(destination, "destination");
            this.destination = destination;
        }

        @Override // ru.mail.logic.cmd.StreamReceiver
        protected void clearResourcesOnError() {
            this.destination.delete();
        }

        @Override // ru.mail.logic.cmd.StreamReceiver
        protected void closeOutputStream() {
            Object objM13123constructorimpl;
            synchronized (this) {
                try {
                    T t10 = this.mOutputStream;
                    if (t10 != 0) {
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            t10.close();
                            objM13123constructorimpl = Result.m13123constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th2) {
                            Result.Companion companion2 = Result.INSTANCE;
                            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
                        }
                        Result.m13122boximpl(objM13123constructorimpl);
                    }
                    this.mOutputStream = null;
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th3) {
                    throw th3;
                }
            }
        }

        @Override // ru.mail.logic.cmd.StreamReceiver
        @NotNull
        protected OutputStream createOutputStream() {
            File parentFile = this.destination.getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            return new FileOutputStream(this.destination);
        }

        @Override // ru.mail.logic.cmd.StreamReceiver
        @NotNull
        protected InputStream toInputStream() {
            return new FileInputStream(this.destination);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/server/DownloadMessageEmlCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "messageId", "", "folderId", "", "destination", "Ljava/io/File;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;JLjava/io/File;Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "getDestination", "()Ljava/io/File;", "equals", "", "other", "", "hashCode", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @NotNull
        private final File destination;

        @Param(method = HttpMethod.POST, name = "folder_id")
        private final long folderId;

        @Param(method = HttpMethod.POST, name = "id")
        @NotNull
        private final String messageId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String messageId, long j10, @NotNull File destination, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(messageId, "messageId");
            Intrinsics.checkNotNullParameter(destination, "destination");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            this.messageId = messageId;
            this.folderId = j10;
            this.destination = destination;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params) || !super.equals(other)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.messageId, params.messageId) && this.folderId == params.folderId && Intrinsics.areEqual(this.destination, params.destination);
        }

        @NotNull
        public final File getDestination() {
            return this.destination;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (((((super.hashCode() * 31) + this.messageId.hashCode()) * 31) + Long.hashCode(this.folderId)) * 31) + this.destination.hashCode();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadMessageEmlCommand(@NotNull Context context, @NotNull Params params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.fileReceiver = new EmlFileReceiver(params.getDestination());
    }

    private final boolean isExpectedContentType() {
        String headerField = getNetworkService().getHeaderField("Content-Type");
        if (headerField == null) {
            headerField = "";
        }
        String lowerCase = headerField.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        List<String> list = EXPECTED_CONTENT_TYPES;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) it.next(), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkCommand<Params, File>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, File>.TornadoDelegate() { // from class: ru.mail.data.cmd.server.DownloadMessageEmlCommand.getCustomDelegate.1
            {
                super();
            }

            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onError(NetworkCommand.Response resp) {
                Intrinsics.checkNotNullParameter(resp, "resp");
                DownloadMessageEmlCommand.this.fileReceiver.abort();
                CommandStatus<?> commandStatusOnError = super.onError(resp);
                Intrinsics.checkNotNullExpressionValue(commandStatusOnError, "onError(...)");
                return commandStatusOnError;
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected byte[] getResponseData(@NotNull InputStream inputStream) throws IOException {
        Intrinsics.checkNotNullParameter(inputStream, "inputStream");
        if (!isCancelled() && isExpectedContentType()) {
            this.fileReceiver.receive(inputStream);
        }
        return new byte[0];
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean isStringResponse() {
        return false;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected boolean isSupportOAuthAuthorization() {
        return true;
    }

    @Override // ru.mail.mailbox.cmd.Command
    protected void onCancelled() {
        super.onCancelled();
        this.fileReceiver.abort();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public File onPostExecuteRequest(@NotNull NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(response, "response");
        if (this.fileReceiver.isReceived()) {
            return ((Params) getParams()).getDestination();
        }
        throw new NetworkCommand.PostExecuteException("Error while saving message eml");
    }
}
