package ru.mail.data.cmd.server;

import android.content.Context;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.MailboxContext;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0014\u0015B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/server/RequestSanitizeUrlCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/RequestSanitizeUrlCommand$Params;", "Lru/mail/data/cmd/server/RequestSanitizeUrlCommand$Result;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/RequestSanitizeUrlCommand$Params;Z)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onSetupSessionInUrl", "", "url", "Landroid/net/Uri$Builder;", "Params", "Result", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/swa_default_host", defSchemeStrRes = "string/swa_default_scheme", prefKey = "swa")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "mobauth", "get"})
public final class RequestSanitizeUrlCommand extends ServerCommandBase<Params, Result> {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0014R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/server/RequestSanitizeUrlCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "page", "", "mailboxContext", "Lru/mail/logic/content/MailboxContext;", "<init>", "(Ljava/lang/String;Lru/mail/logic/content/MailboxContext;)V", "getPage", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "needAppendActMode", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandBaseParams {

        @Param(name = "page")
        @NotNull
        private final String page;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull String page, @NotNull MailboxContext mailboxContext) {
            super(MailboxContextUtil.getAccountInfo(mailboxContext), MailboxContextUtil.getFolderState(mailboxContext));
            Intrinsics.checkNotNullParameter(page, "page");
            Intrinsics.checkNotNullParameter(mailboxContext, "mailboxContext");
            this.page = page;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!Intrinsics.areEqual(Params.class, other != null ? other.getClass() : null) || !super.equals(other)) {
                return false;
            }
            Intrinsics.checkNotNull(other, "null cannot be cast to non-null type ru.mail.data.cmd.server.RequestSanitizeUrlCommand.Params");
            return Intrinsics.areEqual(this.page, ((Params) other).page);
        }

        @NotNull
        public final String getPage() {
            return this.page;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + this.page.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lru/mail/data/cmd/server/RequestSanitizeUrlCommand$Result;", "", "sanitizeUrl", "", "expires", "", "<init>", "(Ljava/lang/String;J)V", "getSanitizeUrl", "()Ljava/lang/String;", "getExpires", "()J", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Result {
        private final long expires;

        @NotNull
        private final String sanitizeUrl;

        public Result(@NotNull String sanitizeUrl, long j10) {
            Intrinsics.checkNotNullParameter(sanitizeUrl, "sanitizeUrl");
            this.sanitizeUrl = sanitizeUrl;
            this.expires = j10;
        }

        public final long getExpires() {
            return this.expires;
        }

        @NotNull
        public final String getSanitizeUrl() {
            return this.sanitizeUrl;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestSanitizeUrlCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@Nullable Uri.Builder url) {
        if (getApiType() == MailAuthorizationApiType.TORNADO) {
            super.onSetupSessionInUrl(url);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
            String string = jSONObject.getString("url");
            long j10 = jSONObject.getLong("expires");
            Intrinsics.checkNotNull(string);
            return new Result(string, j10);
        } catch (JSONException unused) {
            throw new NetworkCommand.PostExecuteException();
        }
    }
}
