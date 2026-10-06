package ru.mail.data.cmd.server.summarize;

import android.content.Context;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.mailbox.cmd.ReusePolicy;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0003H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\b\u0010\u0014\u001a\u00020\bH\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/data/cmd/server/summarize/MailSummarizeRequest;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/summarize/SummarizeParams;", "", "context", "Landroid/content/Context;", "params", "attempts", "", "timeout", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/summarize/SummarizeParams;II)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "getTag", "getReusePolicy", "Lru/mail/mailbox/cmd/ReusePolicy;", "getMaxAttemptCount", "onPrepareConnection", "", "service", "Lru/mail/network/service/NetworkService;", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "messages", "message", MailMessageContent.COL_NAME_SUMMARIZE})
public final class MailSummarizeRequest extends ServerCommandBase<SummarizeParams, String> {
    private final int attempts;

    @NotNull
    private final SummarizeParams params;
    private final int timeout;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MailSummarizeRequest(@NotNull Context context, @NotNull SummarizeParams params, int i10, int i11) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.params = params;
        this.attempts = i10;
        this.timeout = i11;
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.network.NetworkCommand
    /* JADX INFO: renamed from: getMaxAttemptCount, reason: from getter */
    protected int getAttempts() {
        return this.attempts;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected ReusePolicy getReusePolicy() {
        return new ReusePolicy.Unique();
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected String getTag() {
        return "Summarize-" + this.params.getMsgId();
    }

    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    protected void onPrepareConnection(@NotNull NetworkService service) throws IOException {
        Intrinsics.checkNotNullParameter(service, "service");
        super.onPrepareConnection(service);
        service.setReadTimeout(this.timeout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public String onPostExecuteRequest(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        String respString = resp.getRespString();
        Intrinsics.checkNotNullExpressionValue(respString, "getRespString(...)");
        return respString;
    }

    public /* synthetic */ MailSummarizeRequest(Context context, SummarizeParams summarizeParams, int i10, int i11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, summarizeParams, (i12 & 4) != 0 ? 1 : i10, (i12 & 8) != 0 ? 45000 : i11);
    }
}
