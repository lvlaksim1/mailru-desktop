package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.request.AccountInfo;
import ru.mail.cloud.presentationlayer.CloudNavigator;
import ru.mail.imageloader.cmd.LoadPreviewCommand;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.TornadoResponseProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\"B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ(\u0010\f\u001a\"0\rR\u001e\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00110\u00110\u000eH\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014JD\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u00192&\u0010\u001a\u001a\"0\u001bR\u001e\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u000f0\u000f\u0012\f\u0012\n \u0010*\u0004\u0018\u00010\u00110\u00110\u001cH\u0014J\b\u0010\u001d\u001a\u00020\tH\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0014¨\u0006#"}, d2 = {"Lru/mail/data/cmd/server/LoadPreviewCommandTornado;", "Lru/mail/imageloader/cmd/LoadPreviewCommand;", "out", "Ljava/io/OutputStream;", "context", "Landroid/content/Context;", "params", "Lru/mail/data/cmd/server/LoadPreviewCommandTornado$Params;", "usePostParams", "", "<init>", "(Ljava/io/OutputStream;Landroid/content/Context;Lru/mail/data/cmd/server/LoadPreviewCommandTornado$Params;Z)V", "getCustomDelegate", "Lru/mail/serverapi/ServerCommandBase$TornadoDelegate;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/imageloader/cmd/LoadPreviewCommand$Params;", "kotlin.jvm.PlatformType", "Lru/mail/imageloader/cmd/LoadPreviewCommand$Result;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", CloudNavigator.PARAMS_IS_IMAGE, "onPrepareUrl", "Landroid/net/Uri;", "builder", "Landroid/net/Uri$Builder;", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoadPreviewCommandTornado extends LoadPreviewCommand {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0014¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/LoadPreviewCommandTornado$Params;", "Lru/mail/imageloader/cmd/LoadPreviewCommand$Params;", "url", "", "etag", "maxAge", "", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "folderState", "Lru/mail/serverapi/FolderState;", "<init>", "(Ljava/lang/String;Ljava/lang/String;JLru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;)V", "needAppendEmail", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends LoadPreviewCommand.Params {
        public static final int $stable = 8;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@Nullable String str, @Nullable String str2, long j10, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(str, str2, j10, accountInfo, folderState);
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendEmail() {
            return true;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadPreviewCommandTornado(@NotNull OutputStream out, @NotNull Context context, @NotNull Params params, boolean z10) {
        super(out, context, params, 0, z10);
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isImage() {
        String headerField = getNetworkService().getHeaderField("Content-Type");
        return headerField != null && StringsKt.contains$default((CharSequence) headerField, (CharSequence) "image", false, 2, (Object) null);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.imageloader.cmd.LoadPreviewCommand, ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@NotNull NetworkCommand.Response resp, @NotNull ServerApi<?> serverApi, @NotNull NetworkCommand<LoadPreviewCommand.Params, LoadPreviewCommand.Result>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        Intrinsics.checkNotNullParameter(serverApi, "serverApi");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new TornadoResponseProcessor(resp, customDelegate) { // from class: ru.mail.data.cmd.server.LoadPreviewCommandTornado.getResponseProcessor.1
            @Override // ru.mail.serverapi.TornadoResponseProcessor, ru.mail.network.ResponseProcessor
            public CommandStatus<?> process() {
                if (getResponse().getStatusCode() != 200) {
                    CommandStatus<?> commandStatusOnError = getDelegate().onError(getResponse());
                    Intrinsics.checkNotNullExpressionValue(commandStatusOnError, "onError(...)");
                    return commandStatusOnError;
                }
                if (this.isImage()) {
                    CommandStatus<?> commandStatusOnResponseOk = getDelegate().onResponseOk(getResponse());
                    Intrinsics.checkNotNull(commandStatusOnResponseOk);
                    return commandStatusOnResponseOk;
                }
                CommandStatus<?> commandStatusProcessResponse = processResponse(Integer.parseInt(getDelegate().getResponseStatus(getResponse().getRespString())));
                Intrinsics.checkNotNull(commandStatusProcessResponse);
                return commandStatusProcessResponse;
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.imageloader.cmd.LoadPreviewCommand, ru.mail.network.NetworkCommand
    @NotNull
    protected Uri onPrepareUrl(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Uri uriBuild = builder.build();
        Uri.Builder builderBuildUpon = Uri.parse(((LoadPreviewCommand.Params) getParams()).getUrl()).buildUpon();
        for (String str : uriBuild.getQueryParameterNames()) {
            builderBuildUpon.appendQueryParameter(str, uriBuild.getQueryParameter(str));
        }
        Uri uriBuild2 = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild2, "build(...)");
        return uriBuild2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.imageloader.cmd.LoadPreviewCommand, ru.mail.network.NetworkCommand
    @NotNull
    public ServerCommandBase<LoadPreviewCommand.Params, LoadPreviewCommand.Result>.TornadoDelegate getCustomDelegate() {
        return new ServerCommandBase.TornadoDelegate();
    }
}
