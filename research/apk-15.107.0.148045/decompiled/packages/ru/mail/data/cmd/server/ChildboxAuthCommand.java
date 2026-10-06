package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001:\u0002\u0011\u0012B\u001f\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0014J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0014¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/ChildboxAuthCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/ChildboxAuthCommand$Params;", "", "Lru/mail/data/cmd/server/ChildboxAuthCommand$ChildAuth;", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/ChildboxAuthCommand$Params;Z)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "Params", "ChildAuth", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/swa_default_host", defSchemeStrRes = "string/swa_default_scheme", prefKey = "swa")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "childbox", "auth"})
public final class ChildboxAuthCommand extends ServerCommandBase<Params, List<? extends ChildAuth>> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/data/cmd/server/ChildboxAuthCommand$ChildAuth;", "", "login", "", "authUrl", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getLogin", "()Ljava/lang/String;", "getAuthUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ChildAuth {
        public static final int $stable = 0;

        @NotNull
        private final String authUrl;

        @NotNull
        private final String login;

        public ChildAuth(@NotNull String login, @NotNull String authUrl) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(authUrl, "authUrl");
            this.login = login;
            this.authUrl = authUrl;
        }

        public static /* synthetic */ ChildAuth copy$default(ChildAuth childAuth, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = childAuth.login;
            }
            if ((i10 & 2) != 0) {
                str2 = childAuth.authUrl;
            }
            return childAuth.copy(str, str2);
        }

        @NotNull
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getLogin() {
            return this.login;
        }

        @NotNull
        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getAuthUrl() {
            return this.authUrl;
        }

        @NotNull
        public final ChildAuth copy(@NotNull String login, @NotNull String authUrl) {
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(authUrl, "authUrl");
            return new ChildAuth(login, authUrl);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ChildAuth)) {
                return false;
            }
            ChildAuth childAuth = (ChildAuth) other;
            return Intrinsics.areEqual(this.login, childAuth.login) && Intrinsics.areEqual(this.authUrl, childAuth.authUrl);
        }

        @NotNull
        public final String getAuthUrl() {
            return this.authUrl;
        }

        @NotNull
        public final String getLogin() {
            return this.login;
        }

        public int hashCode() {
            return (this.login.hashCode() * 31) + this.authUrl.hashCode();
        }

        @NotNull
        public String toString() {
            return "ChildAuth(login=" + this.login + ", authUrl=" + this.authUrl + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\n\u001a\u00020\u0003HÂ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0005HÂ\u0003J\t\u0010\f\u001a\u00020\u0007HÂ\u0003J)\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0007HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/ChildboxAuthCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "currentFolderState", "Lru/mail/serverapi/FolderState;", "accountLogin", "", "<init>", "(Lru/mail/auth/request/AccountInfo;Lru/mail/serverapi/FolderState;Ljava/lang/String;)V", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @NotNull
        private final AccountInfo accountInfo;

        @Param(method = HttpMethod.GET, name = "login")
        @NotNull
        private final String accountLogin;

        @Nullable
        private final FolderState currentFolderState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull AccountInfo accountInfo, @Nullable FolderState folderState, @NotNull String accountLogin) {
            super(accountInfo, folderState);
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            Intrinsics.checkNotNullParameter(accountLogin, "accountLogin");
            this.accountInfo = accountInfo;
            this.currentFolderState = folderState;
            this.accountLogin = accountLogin;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final AccountInfo getAccountInfo() {
            return this.accountInfo;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final FolderState getCurrentFolderState() {
            return this.currentFolderState;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        private final String getAccountLogin() {
            return this.accountLogin;
        }

        public static /* synthetic */ Params copy$default(Params params, AccountInfo accountInfo, FolderState folderState, String str, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                accountInfo = params.accountInfo;
            }
            if ((i10 & 2) != 0) {
                folderState = params.currentFolderState;
            }
            if ((i10 & 4) != 0) {
                str = params.accountLogin;
            }
            return params.copy(accountInfo, folderState, str);
        }

        @NotNull
        public final Params copy(@NotNull AccountInfo accountInfo, @Nullable FolderState currentFolderState, @NotNull String accountLogin) {
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            Intrinsics.checkNotNullParameter(accountLogin, "accountLogin");
            return new Params(accountInfo, currentFolderState, accountLogin);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Params)) {
                return false;
            }
            Params params = (Params) other;
            return Intrinsics.areEqual(this.accountInfo, params.accountInfo) && Intrinsics.areEqual(this.currentFolderState, params.currentFolderState) && Intrinsics.areEqual(this.accountLogin, params.accountLogin);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            int iHashCode = this.accountInfo.hashCode() * 31;
            FolderState folderState = this.currentFolderState;
            return ((iHashCode + (folderState == null ? 0 : folderState.hashCode())) * 31) + this.accountLogin.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(accountInfo=" + this.accountInfo + ", currentFolderState=" + this.currentFolderState + ", accountLogin=" + this.accountLogin + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChildboxAuthCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public List<ChildAuth> onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONArray jSONArrayOptJSONArray = new JSONObject(resp.getRespString()).getJSONObject("body").optJSONArray("children");
            if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
                throw new NetworkCommand.PostExecuteException("Error with children");
            }
            JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(0);
            String string = jSONObject.getString("login");
            String string2 = jSONObject.getString("auth_url");
            Intrinsics.checkNotNull(string);
            Intrinsics.checkNotNull(string2);
            return CollectionsKt.listOf(new ChildAuth(string, string2));
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
