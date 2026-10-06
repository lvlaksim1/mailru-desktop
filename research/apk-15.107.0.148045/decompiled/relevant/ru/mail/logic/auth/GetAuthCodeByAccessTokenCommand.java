package ru.mail.logic.auth;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.android.billingclient.api.BillingFlowParams;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.sdk.OAuthRequest;
import ru.mail.credentialsexchanger.data.network.MailOAuthRequest;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.serverapi.TornadoResponseProcessor;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000 #2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003#$%B'\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0002\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJT\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0018\u0010\u0017\u001a\u0014\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0019\u0018\u00010\u00182&\u0010\u001a\u001a\"0\u001bR\u001e\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00030\u00030\u0019H\u0014J:\u0010\u001d\u001a\u00020\u001e2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162&\u0010\u001a\u001a\"0\u001bR\u001e\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00030\u00030\u0019H\u0014J(\u0010\u001f\u001a\"0\u001bR\u001e\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u001c*\u0004\u0018\u00010\u00030\u00030\u0019H\u0014J\b\u0010 \u001a\u00020\nH\u0014J\b\u0010!\u001a\u00020\"H\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006&"}, d2 = {"Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", "app", "", "context", "Landroid/content/Context;", "params", "usePostParams", "", "<init>", "(Ljava/lang/String;Landroid/content/Context;Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;Z)V", "getApp", "()Ljava/lang/String;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "getAnalytics", "()Lru/mail/analytics/MailAppAnalytics;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "resp", "Lru/mail/network/NetworkCommand$Response;", "serverApi", "Lru/mail/network/ServerApi;", "Lru/mail/network/NetworkCommand;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "kotlin.jvm.PlatformType", "customResponseProcessor", "Lru/mail/serverapi/TornadoResponseProcessor;", "getCustomDelegate", "isSupportOAuthAuthorization", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Companion", "Result", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/oauth_default_host", defSchemeStrRes = "string/oauth_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = MailOAuthRequest.BODY_KEY)
@UrlPath(pathSegments = {"token"})
public abstract class GetAuthCodeByAccessTokenCommand extends PostServerRequest<Params, Result> {
    public static final int NO_AUTH_ERROR_CODE = 6;

    @NotNull
    private final String app;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GetAuthCodeByAccessTokenCommand");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0017\u0018\u00002\u00020\u0001Bi\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0018\u001a\u00020\u0006H\u0014R\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0012\u0010\t\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "forClientId", "", "login", "useCodeChallenge", "", "grantType", "clientId", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "subjectToken", "subjectTokenType", "requestedTokenType", "<init>", "(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getGrantType$mails_release", "()Ljava/lang/String;", "getClientId", "codeChallenge", "codeChallengeMethod", "codeVerifier", "getCodeVerifier", "setCodeVerifier", "(Ljava/lang/String;)V", "needAppendActMode", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "client_id")
        @NotNull
        private final String clientId;

        @Param(method = HttpMethod.POST, name = "code_challenge")
        @Nullable
        private String codeChallenge;

        @Param(method = HttpMethod.POST, name = "code_challenge_method")
        @Nullable
        private String codeChallengeMethod;

        @Nullable
        private String codeVerifier;

        @Param(method = HttpMethod.POST, name = "for_client_id")
        @Nullable
        private final String forClientId;

        @Param(method = HttpMethod.POST, name = "grant_type")
        @NotNull
        private final String grantType;

        @Param(method = HttpMethod.POST, name = "requested_token_type")
        @Nullable
        private final String requestedTokenType;

        @Param(method = HttpMethod.POST, name = CommonConstant.ReqAccessTokenParam.SCOPE_LABEL)
        @Nullable
        private final String scope;

        @Param(method = HttpMethod.POST, name = "subject_token")
        @Nullable
        private final String subjectToken;

        @Param(method = HttpMethod.POST, name = "subject_token_type")
        @Nullable
        private final String subjectTokenType;

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login) {
            this(str, login, false, null, null, null, null, null, null, 508, null);
            Intrinsics.checkNotNullParameter(login, "login");
        }

        @NotNull
        public final String getClientId() {
            return this.clientId;
        }

        @Nullable
        public final String getCodeVerifier() {
            return this.codeVerifier;
        }

        @NotNull
        /* JADX INFO: renamed from: getGrantType$mails_release, reason: from getter */
        public final String getGrantType() {
            return this.grantType;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }

        public final void setCodeVerifier(@Nullable String str) {
            this.codeVerifier = str;
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10) {
            this(str, login, z10, null, null, null, null, null, null, 504, null);
            Intrinsics.checkNotNullParameter(login, "login");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10, @NotNull String grantType) {
            this(str, login, z10, grantType, null, null, null, null, null, 496, null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(grantType, "grantType");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10, @NotNull String grantType, @NotNull String clientId) {
            this(str, login, z10, grantType, clientId, null, null, null, null, 480, null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(grantType, "grantType");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10, @NotNull String grantType, @NotNull String clientId, @Nullable String str2) {
            this(str, login, z10, grantType, clientId, str2, null, null, null, 448, null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(grantType, "grantType");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10, @NotNull String grantType, @NotNull String clientId, @Nullable String str2, @Nullable String str3) {
            this(str, login, z10, grantType, clientId, str2, str3, null, null, KyberEngine.KyberPolyBytes, null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(grantType, "grantType");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
        }

        /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10, @NotNull String grantType, @NotNull String clientId, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
            this(str, login, z10, grantType, clientId, str2, str3, str4, null, 256, null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(grantType, "grantType");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
        }

        public /* synthetic */ Params(String str, String str2, boolean z10, String str3, String str4, String str5, String str6, String str7, String str8, int i10, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i10 & 4) != 0 ? false : z10, (i10 & 8) != 0 ? "client_code" : str3, (i10 & 16) != 0 ? "mail-android" : str4, (i10 & 32) != 0 ? null : str5, (i10 & 64) != 0 ? null : str6, (i10 & 128) != 0 ? null : str7, (i10 & 256) != 0 ? null : str8);
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        @JvmOverloads
        public Params(@Nullable String str, @NotNull String login, boolean z10, @NotNull String grantType, @NotNull String clientId, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
            super(new AccountInfo(login, false, 2, null), null);
            Intrinsics.checkNotNullParameter(login, "login");
            Intrinsics.checkNotNullParameter(grantType, "grantType");
            Intrinsics.checkNotNullParameter(clientId, "clientId");
            this.forClientId = str;
            this.grantType = grantType;
            this.clientId = clientId;
            this.scope = str2;
            this.subjectToken = str3;
            this.subjectTokenType = str4;
            this.requestedTokenType = str5;
            if (z10) {
                String strGenerateCodeVerifier = OAuthRequest.generateCodeVerifier();
                this.codeVerifier = strGenerateCodeVerifier;
                this.codeChallengeMethod = "S256";
                this.codeChallenge = OAuthRequest.calculateCodeChallenge(strGenerateCodeVerifier);
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", "", "<init>", "()V", "ClientResult", "ConvertResult", "RefreshResult", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result$ClientResult;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result$ConvertResult;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result$RefreshResult;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J)\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result$ClientResult;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", "authCode", "", "codeVerifier", "expiresIn", "Ljava/util/Date;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;)V", "getAuthCode", "()Ljava/lang/String;", "getCodeVerifier", "getExpiresIn", "()Ljava/util/Date;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class ClientResult extends Result {
            public static final int $stable = 8;

            @NotNull
            private final String authCode;

            @Nullable
            private final String codeVerifier;

            @NotNull
            private final Date expiresIn;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ClientResult(@NotNull String authCode, @Nullable String str, @NotNull Date expiresIn) {
                super(null);
                Intrinsics.checkNotNullParameter(authCode, "authCode");
                Intrinsics.checkNotNullParameter(expiresIn, "expiresIn");
                this.authCode = authCode;
                this.codeVerifier = str;
                this.expiresIn = expiresIn;
            }

            public static /* synthetic */ ClientResult copy$default(ClientResult clientResult, String str, String str2, Date date, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = clientResult.authCode;
                }
                if ((i10 & 2) != 0) {
                    str2 = clientResult.codeVerifier;
                }
                if ((i10 & 4) != 0) {
                    date = clientResult.expiresIn;
                }
                return clientResult.copy(str, str2, date);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAuthCode() {
                return this.authCode;
            }

            @Nullable
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getCodeVerifier() {
                return this.codeVerifier;
            }

            @NotNull
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final Date getExpiresIn() {
                return this.expiresIn;
            }

            @NotNull
            public final ClientResult copy(@NotNull String authCode, @Nullable String codeVerifier, @NotNull Date expiresIn) {
                Intrinsics.checkNotNullParameter(authCode, "authCode");
                Intrinsics.checkNotNullParameter(expiresIn, "expiresIn");
                return new ClientResult(authCode, codeVerifier, expiresIn);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ClientResult)) {
                    return false;
                }
                ClientResult clientResult = (ClientResult) other;
                return Intrinsics.areEqual(this.authCode, clientResult.authCode) && Intrinsics.areEqual(this.codeVerifier, clientResult.codeVerifier) && Intrinsics.areEqual(this.expiresIn, clientResult.expiresIn);
            }

            @NotNull
            public final String getAuthCode() {
                return this.authCode;
            }

            @Nullable
            public final String getCodeVerifier() {
                return this.codeVerifier;
            }

            @NotNull
            public final Date getExpiresIn() {
                return this.expiresIn;
            }

            public int hashCode() {
                int iHashCode = this.authCode.hashCode() * 31;
                String str = this.codeVerifier;
                return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.expiresIn.hashCode();
            }

            @NotNull
            public String toString() {
                return "ClientResult(authCode=" + this.authCode + ", codeVerifier=" + this.codeVerifier + ", expiresIn=" + this.expiresIn + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J3\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result$ConvertResult;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", com.huawei.hms.support.feature.result.CommonConstant.KEY_ACCESS_TOKEN, "", "refreshToken", BillingFlowParams.EXTRA_PARAM_KEY_ACCOUNT_ID, "expiresIn", "Ljava/util/Date;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;)V", "getAccessToken", "()Ljava/lang/String;", "getRefreshToken", "getAccountId", "getExpiresIn", "()Ljava/util/Date;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class ConvertResult extends Result {
            public static final int $stable = 8;

            @NotNull
            private final String accessToken;

            @Nullable
            private final String accountId;

            @NotNull
            private final Date expiresIn;

            @NotNull
            private final String refreshToken;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public ConvertResult(@NotNull String accessToken, @NotNull String refreshToken, @Nullable String str, @NotNull Date expiresIn) {
                super(null);
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(expiresIn, "expiresIn");
                this.accessToken = accessToken;
                this.refreshToken = refreshToken;
                this.accountId = str;
                this.expiresIn = expiresIn;
            }

            public static /* synthetic */ ConvertResult copy$default(ConvertResult convertResult, String str, String str2, String str3, Date date, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = convertResult.accessToken;
                }
                if ((i10 & 2) != 0) {
                    str2 = convertResult.refreshToken;
                }
                if ((i10 & 4) != 0) {
                    str3 = convertResult.accountId;
                }
                if ((i10 & 8) != 0) {
                    date = convertResult.expiresIn;
                }
                return convertResult.copy(str, str2, str3, date);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            @Nullable
            /* JADX INFO: renamed from: component3, reason: from getter */
            public final String getAccountId() {
                return this.accountId;
            }

            @NotNull
            /* JADX INFO: renamed from: component4, reason: from getter */
            public final Date getExpiresIn() {
                return this.expiresIn;
            }

            @NotNull
            public final ConvertResult copy(@NotNull String accessToken, @NotNull String refreshToken, @Nullable String accountId, @NotNull Date expiresIn) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(refreshToken, "refreshToken");
                Intrinsics.checkNotNullParameter(expiresIn, "expiresIn");
                return new ConvertResult(accessToken, refreshToken, accountId, expiresIn);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof ConvertResult)) {
                    return false;
                }
                ConvertResult convertResult = (ConvertResult) other;
                return Intrinsics.areEqual(this.accessToken, convertResult.accessToken) && Intrinsics.areEqual(this.refreshToken, convertResult.refreshToken) && Intrinsics.areEqual(this.accountId, convertResult.accountId) && Intrinsics.areEqual(this.expiresIn, convertResult.expiresIn);
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @Nullable
            public final String getAccountId() {
                return this.accountId;
            }

            @NotNull
            public final Date getExpiresIn() {
                return this.expiresIn;
            }

            @NotNull
            public final String getRefreshToken() {
                return this.refreshToken;
            }

            public int hashCode() {
                int iHashCode = ((this.accessToken.hashCode() * 31) + this.refreshToken.hashCode()) * 31;
                String str = this.accountId;
                return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + this.expiresIn.hashCode();
            }

            @NotNull
            public String toString() {
                return "ConvertResult(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", accountId=" + this.accountId + ", expiresIn=" + this.expiresIn + ")";
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result$RefreshResult;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", com.huawei.hms.support.feature.result.CommonConstant.KEY_ACCESS_TOKEN, "", "expiresIn", "Ljava/util/Date;", "<init>", "(Ljava/lang/String;Ljava/util/Date;)V", "getAccessToken", "()Ljava/lang/String;", "getExpiresIn", "()Ljava/util/Date;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class RefreshResult extends Result {
            public static final int $stable = 8;

            @NotNull
            private final String accessToken;

            @NotNull
            private final Date expiresIn;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public RefreshResult(@NotNull String accessToken, @NotNull Date expiresIn) {
                super(null);
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(expiresIn, "expiresIn");
                this.accessToken = accessToken;
                this.expiresIn = expiresIn;
            }

            public static /* synthetic */ RefreshResult copy$default(RefreshResult refreshResult, String str, Date date, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = refreshResult.accessToken;
                }
                if ((i10 & 2) != 0) {
                    date = refreshResult.expiresIn;
                }
                return refreshResult.copy(str, date);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final Date getExpiresIn() {
                return this.expiresIn;
            }

            @NotNull
            public final RefreshResult copy(@NotNull String accessToken, @NotNull Date expiresIn) {
                Intrinsics.checkNotNullParameter(accessToken, "accessToken");
                Intrinsics.checkNotNullParameter(expiresIn, "expiresIn");
                return new RefreshResult(accessToken, expiresIn);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof RefreshResult)) {
                    return false;
                }
                RefreshResult refreshResult = (RefreshResult) other;
                return Intrinsics.areEqual(this.accessToken, refreshResult.accessToken) && Intrinsics.areEqual(this.expiresIn, refreshResult.expiresIn);
            }

            @NotNull
            public final String getAccessToken() {
                return this.accessToken;
            }

            @NotNull
            public final Date getExpiresIn() {
                return this.expiresIn;
            }

            public int hashCode() {
                return (this.accessToken.hashCode() * 31) + this.expiresIn.hashCode();
            }

            @NotNull
            public String toString() {
                return "RefreshResult(accessToken=" + this.accessToken + ", expiresIn=" + this.expiresIn + ")";
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetAuthCodeByAccessTokenCommand(@NotNull String app, @NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.app = app;
    }

    @NotNull
    protected TornadoResponseProcessor customResponseProcessor(@Nullable NetworkCommand.Response resp, @NotNull NetworkCommand<Params, Result>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return new TornadoResponseProcessor(resp, customDelegate) { // from class: ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand.customResponseProcessor.1
            final /* synthetic */ NetworkCommand<Params, Result>.NetworkCommandBaseDelegate $customDelegate;

            {
                this.$customDelegate = customDelegate;
            }

            @Override // ru.mail.serverapi.TornadoResponseProcessor
            protected CommandStatus<?> processResponse(int responseStatus) {
                if (responseStatus == 403) {
                    CommandStatus<?> commandStatusOnUnauthorized = this.$customDelegate.onUnauthorized("token");
                    Intrinsics.checkNotNull(commandStatusOnUnauthorized);
                    return commandStatusOnUnauthorized;
                }
                CommandStatus<?> commandStatusProcessResponse = super.processResponse(responseStatus);
                Intrinsics.checkNotNullExpressionValue(commandStatusProcessResponse, "processResponse(...)");
                return commandStatusProcessResponse;
            }
        };
    }

    @NotNull
    protected final MailAppAnalytics getAnalytics() {
        return MailAppDependencies.analytics(getContext());
    }

    @NotNull
    public final String getApp() {
        return this.app;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, Result>.TornadoDelegate() { // from class: ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand.getCustomDelegate.1
            {
                super();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String resp) {
                try {
                    if (resp == null) {
                        resp = "";
                    }
                    JSONObject jSONObject = new JSONObject(resp);
                    int iOptInt = jSONObject.optInt("error_code", -1);
                    GetAuthCodeByAccessTokenCommand.this.getAnalytics().logCodeExchangeResult(String.valueOf(iOptInt));
                    GetAuthCodeByAccessTokenCommand.this.getAnalytics().logCodeExchangeResult(((Params) GetAuthCodeByAccessTokenCommand.this.getParams()).getGrantType(), GetAuthCodeByAccessTokenCommand.this.getApp(), String.valueOf(iOptInt));
                    if (iOptInt == 6) {
                        return "403";
                    }
                    return (jSONObject.has("code") || jSONObject.has("refresh_token") || jSONObject.has("access_token")) ? "200" : "500";
                } catch (JSONException e10) {
                    GetAuthCodeByAccessTokenCommand.LOG.e("Failed to get response status", e10);
                    GetAuthCodeByAccessTokenCommand.this.getAnalytics().logCodeExchangeResult("get_status_json_exception");
                    GetAuthCodeByAccessTokenCommand.this.getAnalytics().logCodeExchangeResult(((Params) GetAuthCodeByAccessTokenCommand.this.getParams()).getGrantType(), GetAuthCodeByAccessTokenCommand.this.getApp(), "get_status_json_exception");
                    return "-1";
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onError(NetworkCommand.Response resp) {
                if ((resp != null ? Integer.valueOf(resp.getStatusCode()) : null) == null) {
                    CommandStatus<?> commandStatusOnError = super.onError(resp);
                    Intrinsics.checkNotNull(commandStatusOnError);
                    return commandStatusOnError;
                }
                GetAuthCodeByAccessTokenCommand.this.getAnalytics().logCodeExchangeResult("Http_" + resp.getStatusCode());
                GetAuthCodeByAccessTokenCommand.this.getAnalytics().logCodeExchangeResult(GetAuthCodeByAccessTokenCommand.this.getApp(), ((Params) GetAuthCodeByAccessTokenCommand.this.getParams()).getGrantType(), "Http_" + resp.getStatusCode());
                return new CommandStatus.ERROR_WITH_STATUS_CODE(resp.getStatusCode());
            }

            @Override // ru.mail.serverapi.ServerCommandBase.TornadoDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onUnauthorized(String reason) {
                CommandStatus<?> commandStatusOnUnauthorized = super.onUnauthorized("token");
                Intrinsics.checkNotNullExpressionValue(commandStatusOnUnauthorized, "onUnauthorized(...)");
                return commandStatusOnUnauthorized;
            }
        };
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<? extends NetworkCommand<?, ?>> serverApi, @NotNull NetworkCommand<Params, Result>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        return customResponseProcessor(resp, customDelegate);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected boolean isSupportOAuthAuthorization() {
        return true;
    }
}
