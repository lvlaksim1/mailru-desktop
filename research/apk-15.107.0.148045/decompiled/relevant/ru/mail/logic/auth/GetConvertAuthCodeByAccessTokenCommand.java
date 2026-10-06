package ru.mail.logic.auth;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Calendar;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.network.NetworkCommand;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014¨\u0006\u0011"}, d2 = {"Lru/mail/logic/auth/GetConvertAuthCodeByAccessTokenCommand;", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand;", "app", "", "context", "Landroid/content/Context;", "params", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;", "usePostParams", "", "<init>", "(Ljava/lang/String;Landroid/content/Context;Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Params;Z)V", "onPostExecuteRequest", "Lru/mail/logic/auth/GetAuthCodeByAccessTokenCommand$Result;", "resp", "Lru/mail/network/NetworkCommand$Response;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class GetConvertAuthCodeByAccessTokenCommand extends GetAuthCodeByAccessTokenCommand {

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GetConvertAuthCodeByAccessTokenCommand");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/logic/auth/GetConvertAuthCodeByAccessTokenCommand$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetConvertAuthCodeByAccessTokenCommand(@NotNull String app, @NotNull Context context, @NotNull GetAuthCodeByAccessTokenCommand.Params params, boolean z10) {
        super(app, context, params, z10);
        Intrinsics.checkNotNullParameter(app, "app");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public GetAuthCodeByAccessTokenCommand.Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(13, new JSONObject(resp.getRespString()).getInt("expires_in"));
            String string = new JSONObject(resp.getRespString()).getString("access_token");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            String strOptString = new JSONObject(resp.getRespString()).optString("refresh_token");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
            String activeLogin = CommonDataManager.from(getContext()).getActiveLogin();
            Date time = calendar.getTime();
            Intrinsics.checkNotNullExpressionValue(time, "getTime(...)");
            return new GetAuthCodeByAccessTokenCommand.Result.ConvertResult(string, strOptString, activeLogin, time);
        } catch (JSONException e10) {
            LOG.e("Failed to convert auth code by access token", e10);
            getAnalytics().logCodeExchangeResult("get_convert_auth_code_by_access_token_json_exception");
            getAnalytics().logCodeExchangeResult(((GetAuthCodeByAccessTokenCommand.Params) getParams()).getGrantType(), getApp(), "get_convert_auth_code_by_access_token_json_exception");
            throw new NetworkCommand.PostExecuteException("Json error", e10);
        }
    }
}
