package ru.mail.auth.request;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.PostRequest;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00162\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u0014\u0015\u0016B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014JJ\u0010\f\u001a\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f2(\u0010\u0010\u001a$\u0018\u00010\u0011R\u001e\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00020\u0002\u0012\f\u0012\n \u0013*\u0004\u0018\u00010\u00030\u00030\u0012H\u0014¨\u0006\u0017"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest;", "Lru/mail/authorizesdk/data/request/common/PostRequest;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Params;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/auth/request/GetEmailAllowedInfoRequest$Params;)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResponseProcessor", "Lru/mail/network/ResponseProcessor;", "serverApi", "Lru/mail/network/ServerApi;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "kotlin.jvm.PlatformType", "Params", "Result", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/swa_default_host", defSchemeStrRes = "string/swa_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "swa")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "auth", "user", "allowed"})
public final class GetEmailAllowedInfoRequest extends PostRequest<Params, Result> {

    @NotNull
    private static final String DECISION_PARAM = "decision";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest$Params;", "", "email", "", "<init>", "(Ljava/lang/String;)V", "getEmail", "()Ljava/lang/String;", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params {

        @NotNull
        private static final String EMAIL_PARAM = "email";

        @Param(method = HttpMethod.POST, name = "email")
        @Nullable
        private final String email;

        public Params(@Nullable String str) {
            this.email = str;
        }

        @Nullable
        public final String getEmail() {
            return this.email;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result;", "", "<init>", "()V", "Allowed", "Forbidden", "CanMigrate", "AllowedMigrant", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$Allowed;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$AllowedMigrant;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$CanMigrate;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$Forbidden;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$Allowed;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Allowed extends Result {

            @NotNull
            public static final Allowed INSTANCE = new Allowed();

            private Allowed() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$AllowedMigrant;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result;", "migrantEmail", "", "<init>", "(Ljava/lang/String;)V", "getMigrantEmail", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class AllowedMigrant extends Result {

            @NotNull
            private final String migrantEmail;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AllowedMigrant(@NotNull String migrantEmail) {
                super(null);
                Intrinsics.checkNotNullParameter(migrantEmail, "migrantEmail");
                this.migrantEmail = migrantEmail;
            }

            @NotNull
            public final String getMigrantEmail() {
                return this.migrantEmail;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$CanMigrate;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class CanMigrate extends Result {

            @NotNull
            public static final CanMigrate INSTANCE = new CanMigrate();

            private CanMigrate() {
                super(null);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result$Forbidden;", "Lru/mail/auth/request/GetEmailAllowedInfoRequest$Result;", "<init>", "()V", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Forbidden extends Result {

            @NotNull
            public static final Forbidden INSTANCE = new Forbidden();

            private Forbidden() {
                super(null);
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetEmailAllowedInfoRequest(@NotNull Context context, @NotNull Params params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected ResponseProcessor getResponseProcessor(@Nullable NetworkCommand.Response resp, @Nullable ServerApi<?> serverApi, @Nullable NetworkCommand<Params, Result>.NetworkCommandBaseDelegate customDelegate) {
        return new JsonStatusResponseProcessor(resp, customDelegate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(@Nullable NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        try {
            Intrinsics.checkNotNull(resp);
            JSONObject jSONObject = new JSONObject(resp.getRespString()).getJSONObject("body");
            String string = jSONObject.getString(DECISION_PARAM);
            if (string != null) {
                switch (string.hashCode()) {
                    case -1521406916:
                        if (string.equals("can_migrate")) {
                            return Result.CanMigrate.INSTANCE;
                        }
                        break;
                    case -911343192:
                        if (string.equals("allowed")) {
                            return Result.Allowed.INSTANCE;
                        }
                        break;
                    case 1503566841:
                        if (string.equals("forbidden")) {
                            return Result.Forbidden.INSTANCE;
                        }
                        break;
                    case 1706654136:
                        if (string.equals("allowed_via_migrant")) {
                            String string2 = jSONObject.getString("migrant_email");
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                            return new Result.AllowedMigrant(string2);
                        }
                        break;
                }
            }
            return Result.Forbidden.INSTANCE;
        } catch (JSONException e10) {
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
