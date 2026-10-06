package ru.mail.logic.auth;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.kit.auth.analytics.AuthAnalytics;
import ru.mail.kit.auth.info.AuthErrorReason;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.network.requestbody.ParamsRequestBody;
import ru.mail.network.service.NetworkService;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\tH\u0014J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0015H\u0014J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0014J\b\u0010\u001e\u001a\u00020\u001fH\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0014R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001dX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lru/mail/logic/auth/TokenExchangeCommand;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand;", "app", "", "context", "Landroid/content/Context;", "params", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;", "usePostParams", "", "clientSecret", "authAnalytics", "Lru/mail/kit/auth/analytics/AuthAnalytics;", "<init>", "(Ljava/lang/String;Landroid/content/Context;Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;ZLjava/lang/String;Lru/mail/kit/auth/analytics/AuthAnalytics;)V", "getHostProvider", "Lru/mail/network/HostProvider;", "isSupportOAuthAuthorization", "onSetupSessionInUrl", "", "url", "Landroid/net/Uri$Builder;", "onPrepareUrl", "Landroid/net/Uri;", "builder", "onPrepareConnection", "networkService", "Lru/mail/network/service/NetworkService;", "allowedPostParams", "", "onPrepareRequestBody", "Lru/mail/network/requestbody/ParamsRequestBody;", "onPostExecuteRequest", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", "resp", "Lru/mail/network/NetworkCommand$Response;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTokenExchangeCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TokenExchangeCommand.kt\nru/mail/logic/auth/TokenExchangeCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,98:1\n774#2:99\n865#2,2:100\n*S KotlinDebug\n*F\n+ 1 TokenExchangeCommand.kt\nru/mail/logic/auth/TokenExchangeCommand\n*L\n69#1:99\n69#1:100,2\n*E\n"})
public final class TokenExchangeCommand extends GetAuthCodeByAccessTokenCommand {

    @NotNull
    private final Set<String> allowedPostParams;

    @NotNull
    private final AuthAnalytics authAnalytics;

    @NotNull
    private final String clientSecret;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("TokenExchangeCommand");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/logic/auth/TokenExchangeCommand$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TokenExchangeCommand(@NotNull String app, @NotNull Context context, @NotNull GetAuthCodeByAccessTokenCommand.Params params, boolean z10, @NotNull String clientSecret, @NotNull AuthAnalytics authAnalytics) {
        super(app, context, params, z10);
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(clientSecret, "clientSecret");
        Intrinsics.checkNotNullParameter(authAnalytics, "authAnalytics");
        this.clientSecret = clientSecret;
        this.authAnalytics = authAnalytics;
        this.allowedPostParams = SetsKt.setOf((Object[]) new String[]{"grant_type", "client_id", CommonConstant.ReqAccessTokenParam.SCOPE_LABEL, "subject_token", "subject_token_type", "requested_token_type"});
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        HostProvider hostProvider = super.getHostProvider();
        Intrinsics.checkNotNullExpressionValue(hostProvider, "getHostProvider(...)");
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        return new OidcHostProvider(hostProvider, context);
    }

    @Override // ru.mail.logic.auth.GetAuthCodeByAccessTokenCommand, ru.mail.serverapi.ServerCommandBase
    protected boolean isSupportOAuthAuthorization() {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommandWithSession, ru.mail.network.NetworkCommand
    protected void onPrepareConnection(@NotNull NetworkService networkService) throws IOException {
        Intrinsics.checkNotNullParameter(networkService, "networkService");
        byte[] bytes = (((GetAuthCodeByAccessTokenCommand.Params) getParams()).getClientId() + ":" + this.clientSecret).getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        networkService.setRequestProperty("Authorization", "Basic " + Base64.encodeToString(bytes, 2));
        super.onPrepareConnection(networkService);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected Uri onPrepareUrl(@NotNull Uri.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        super.onPrepareUrl(builder);
        Uri uriBuild = builder.build().buildUpon().clearQuery().build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        return uriBuild;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void onSetupSessionInUrl(@NotNull Uri.Builder url) {
        Intrinsics.checkNotNullParameter(url, "url");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public GetAuthCodeByAccessTokenCommand.Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            JSONObject jSONObject = new JSONObject(resp.getRespString());
            Calendar calendar = Calendar.getInstance();
            calendar.add(13, jSONObject.getInt("expires_in"));
            String string = jSONObject.getString("access_token");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String strOptString = jSONObject.optString("refresh_token");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String activeLogin = CommonDataManager.from(getContext()).getActiveLogin();
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "getTime(...)");
            return new GetAuthCodeByAccessTokenCommand.Result.ConvertResult(string, strOptString, activeLogin, time);
        } catch (JSONException e10) {
            LOG.e("Failed to perform token exchange", e10);
            this.authAnalytics.onTokenExchangeFailure(((GetAuthCodeByAccessTokenCommand.Params) getParams()).getClientId(), AuthErrorReason.TOKEN_EXCHANGE_FAILURE);
            throw new NetworkCommand.PostExecuteException("Json error", e10);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NotNull
    public ParamsRequestBody onPrepareRequestBody() throws IOException {
        List<NameValuePair> listProvidePostParams = providePostParams();
        Intrinsics.checkNotNullExpressionValue(listProvidePostParams, "providePostParams(...)");
        ArrayList arrayList = new ArrayList();
        for (Object obj : listProvidePostParams) {
            NameValuePair nameValuePair = (NameValuePair) obj;
            if (this.allowedPostParams.contains(nameValuePair.getName())) {
                String value = nameValuePair.getValue();
                Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
                if (value.length() > 0) {
                    arrayList.add(obj);
                }
            }
        }
        return new ParamsRequestBody(arrayList, "UTF-8");
    }
}
