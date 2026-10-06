package ru.mail.data.cmd.server.pusher.pushme;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import androidx.annotation.Keep;
import androidx.compose.runtime.internal.StabilityInferred;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.request.AccountInfo;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.NoAuthInfo;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.ByteRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;
import ru.mail.util.log.FileHandlerArchive;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0017\u0018\u0000 22\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u000212B!\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nB)\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\rJ\b\u0010\u0010\u001a\u00020\u0011H\u0014J\b\u0010\u0012\u001a\u00020\bH\u0014J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0006\u001a\u00020\u000fH\u0002J\u0014\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!H\u0014J\u0014\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!H\u0002J\u0014\u0010#\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010 \u001a\u00020!H\u0014J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020\bH\u0014J\u0014\u0010*\u001a\u0004\u0018\u00010\u000f2\b\u0010+\u001a\u0004\u0018\u00010\u000fH\u0004J\u0016\u0010,\u001a\u00020\u00142\f\u0010-\u001a\b\u0012\u0004\u0012\u00020&0%H\u0002J\n\u0010.\u001a\u0004\u0018\u00010\u000fH\u0007J\u0012\u0010/\u001a\u00020\u00032\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u00100\u001a\u00020\u000fH\u0014R\u0010\u0010\u000e\u001a\u00020\u000f8\u0002X\u0083D¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand$Params;", "Lru/mail/mailbox/cmd/EmptyResult;", "context", "Landroid/content/Context;", "params", "usePostParamsOnly", "", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand$Params;Z)V", "hostProvider", "Lru/mail/network/HostProvider;", "(Landroid/content/Context;Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand$Params;Lru/mail/network/HostProvider;Z)V", "mApiPath", "", "prepareTokenFilter", "Lru/mail/util/log/LogFilter;", "isSupportOAuthAuthorization", "setUpSession", "", "networkService", "Lru/mail/network/service/NetworkService;", "onSetupSessionInUrl", "url", "Landroid/net/Uri$Builder;", "onPrepareConnection", "onPrepareRequestBody", "Lru/mail/network/requestbody/RequestBody;", "createRequestBody", "processResponse", "Lru/mail/mailbox/cmd/CommandStatus;", "resp", "Lru/mail/network/NetworkCommand$Response;", "parseResponse", "parseResponseData", "parseValidateResult", "", "Lru/mail/network/NoAuthInfo;", "body", "Lorg/json/JSONArray;", "needPlatformParams", "peekAuthToken", "login", "sendAnalyticInvalidPushTokenEvent", "noAuthInfoList", "getApiFromPreference", "onPostExecuteRequest", "getPathTag", "Params", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@HostProviderAnnotation(defHostStrRes = "string/push_default_host", defSchemeStrRes = "string/push_default_scheme", prefKey = "push")
@UrlPath(pathSegments = {"{api}", FileHandlerArchive.VERSION_POSTFIX, "set_settings"})
@SourceDebugExtension({"SMAP\nPushMeSendPushSettingsCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMeSendPushSettingsCommand.kt\nru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,222:1\n1761#2,3:223\n*S KotlinDebug\n*F\n+ 1 PushMeSendPushSettingsCommand.kt\nru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand\n*L\n184#1:223,3\n*E\n"})
public class PushMeSendPushSettingsCommand extends PostServerRequest<Params, EmptyResult> {

    @NotNull
    private static final String MPOP_COOKIE_NAME = "mpop";

    @Param(getterName = "getApiFromPreference", method = HttpMethod.URL, name = ApiUris.AUTHORITY_API, useGetter = true)
    @NotNull
    private final String mApiPath;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PushMeSendPushSettingsCommand");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\nH\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/data/cmd/server/pusher/pushme/PushMeSendPushSettingsCommand$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "login", "", "settings", "Lorg/json/JSONArray;", "<init>", "(Ljava/lang/String;Lorg/json/JSONArray;)V", "toString", "needAppendActMode", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static class Params extends ServerCommandBaseParams {
        public static final int $stable = 8;

        @Nullable
        private final JSONArray settings;

        public Params(@Nullable String str, @Nullable JSONArray jSONArray) {
            super(new AccountInfo(str, false, 2, null), null);
            this.settings = jSONArray;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            String string;
            JSONArray jSONArray = this.settings;
            return (jSONArray == null || (string = jSONArray.toString()) == null) ? "null" : string;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeSendPushSettingsCommand(@NotNull Context context, @NotNull Params params, boolean z10) {
        super(context, params, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        this.mApiPath = ApiUris.AUTHORITY_API;
    }

    private final RequestBody createRequestBody(String params) {
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = params.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        return new ByteRequestBody(bytes);
    }

    private final CommandStatus<?> parseResponse(NetworkCommand.Response resp) {
        if (resp.getStatusCode() == 200) {
            try {
                return parseResponseData(resp);
            } catch (JSONException e10) {
                LOG.e("Error reading response: " + e10.getMessage(), e10);
                return new CommandStatus.ERROR();
            }
        }
        if (resp.getStatusCode() == 401) {
            MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("no_auth");
            return new NetworkCommandStatus.NO_AUTH(getNoAuthInfo());
        }
        MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("error responseCode " + resp.getStatusCode());
        return new CommandStatus.ERROR();
    }

    private final List<NoAuthInfo> parseValidateResult(JSONArray body) throws JSONException {
        ArrayList arrayList = new ArrayList();
        int length = body.length();
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject = body.getJSONObject(i10);
            String string = jSONObject.getString("account");
            if (!jSONObject.getBoolean("is_valid")) {
                arrayList.add(new NoAuthInfo(string, getAuthCommandCreator(), peekAuthToken(string)));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void sendAnalyticInvalidPushTokenEvent(List<? extends NoAuthInfo> noAuthInfoList) {
        List<? extends NoAuthInfo> list = noAuthInfoList;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((NoAuthInfo) it.next()).getLogin(), ((Params) getParams()).getLogin())) {
                    MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("invalid");
                    return;
                }
            }
        }
        MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("ok");
    }

    @Keep
    @Nullable
    public final String getApiFromPreference() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String string = getContext().getString(R.string.push_default_api);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return defaultSharedPreferences.getString(BaseSettingsActivity.KEY_PREF_API_PUSH, string);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected String getPathTag() {
        return "api_v2_set_settings";
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected boolean isSupportOAuthAuthorization() {
        return true;
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean needPlatformParams() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    protected void onPrepareConnection(@NotNull NetworkService networkService) throws NetworkCommandWithSession.BadSessionException, IOException {
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        networkService.setConnectTimeout(4000);
        networkService.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        networkService.setRequestProperty("Content-Length", String.valueOf(networkService.getContentLength(createRequestBody(((Params) getParams()).toString()), this)));
        super.onPrepareConnection(networkService);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected RequestBody onPrepareRequestBody() {
        String string = ((Params) getParams()).toString();
        LOG.v("Requesting setting set with following body: " + filterTokenString(string));
        return createRequestBody(string);
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@NotNull Uri.Builder url) throws NetworkCommandWithSession.BadSessionException {
        Intrinsics.checkNotNullParameter(url, "url");
    }

    @NotNull
    protected CommandStatus<?> parseResponseData(@NotNull NetworkCommand.Response resp) throws JSONException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        JSONObject jSONObject = new JSONObject(resp.getRespString());
        int i10 = jSONObject.getJSONObject("error").getInt("code");
        if (i10 != 0) {
            MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("error jsonCode " + i10);
            return new CommandStatus.ERROR();
        }
        JSONArray jSONArray = jSONObject.getJSONArray("validate_result");
        Intrinsics.checkNotNull(jSONArray);
        List<NoAuthInfo> validateResult = parseValidateResult(jSONArray);
        if (validateResult.isEmpty()) {
            MailAppDependencies.analytics(getContext()).sendAnalyticPushTokenEvent("ok");
            return new CommandStatus.OK();
        }
        sendAnalyticInvalidPushTokenEvent(validateResult);
        return new NetworkCommandStatus.NO_AUTH_MULTIPLE(validateResult);
    }

    @Nullable
    protected final String peekAuthToken(@Nullable String login) {
        Intrinsics.checkNotNull(login);
        Account account = new Account(login, "ru.mail");
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(getContext().getApplicationContext());
        String tokenType = getTokenType();
        Intrinsics.checkNotNullExpressionValue(tokenType, "getTokenType(...)");
        return accountManagerWrapper.peekAuthToken(account, tokenType);
    }

    @Override // ru.mail.serverapi.PostServerRequest, ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    protected LogFilter prepareTokenFilter() {
        LogFilter logFilterPrepareTokenFilter = super.prepareTokenFilter();
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat(MPOP_COOKIE_NAME);
        Intrinsics.checkNotNullExpressionValue(paramFormatNewUrlFormat, "newUrlFormat(...)");
        Formats.ParamFormat paramFormatNewJsonFormat = Formats.newJsonFormat(MPOP_COOKIE_NAME);
        Intrinsics.checkNotNullExpressionValue(paramFormatNewJsonFormat, "newJsonFormat(...)");
        logFilterPrepareTokenFilter.addTokenConstraints(paramFormatNewUrlFormat, paramFormatNewJsonFormat);
        Intrinsics.checkNotNull(logFilterPrepareTokenFilter);
        return logFilterPrepareTokenFilter;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected CommandStatus<?> processResponse(@NotNull NetworkCommand.Response resp) {
        Intrinsics.checkNotNullParameter(resp, "resp");
        CommandStatus<?> response = parseResponse(resp);
        MailAppDependencies.analytics(getContext()).logSettingsSendResponseState(NetworkCommand.statusOK(response));
        return response;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(@NotNull NetworkService networkService) {
        Intrinsics.checkNotNullParameter(networkService, "networkService");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(@Nullable NetworkCommand.Response resp) {
        return new EmptyResult();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeSendPushSettingsCommand(@NotNull Context context, @NotNull Params params, @NotNull HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(hostProvider, "hostProvider");
        this.mApiPath = ApiUris.AUTHORITY_API;
    }
}
