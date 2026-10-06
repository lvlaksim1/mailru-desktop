package ru.mail.registration.request;

import android.content.Context;
import androidx.annotation.Keep;
import com.vk.api.sdk.exceptions.VKApiCodes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.auth.request.util.AccountInfoUtilsKt;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.registration.ui.AccountData;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001:\u0001\u0013B=\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014¨\u0006\u0014"}, d2 = {"Lru/mail/registration/request/RegCheckSocialCmd;", "Lru/mail/registration/request/RegServerCookieRequest;", "Lru/mail/registration/request/RegCheckSocialCmd$Params;", "context", "Landroid/content/Context;", "accountData", "Lru/mail/registration/ui/AccountData;", "usePostParams", "", "xmailFrom", "", "isVkTokenSendInConfirmRequestEnabled", "vkToken", "<init>", "(Landroid/content/Context;Lru/mail/registration/ui/AccountData;ZLjava/lang/String;ZLjava/lang/String;)V", "onPostExecuteRequest", "Lru/mail/registration/request/RegServerCookieRequest$Result;", "response", "Lru/mail/network/NetworkCommand$Response;", "Params", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", "signup", VKApiCodes.EXTRA_CONFIRM})
public final class RegCheckSocialCmd extends RegServerCookieRequest<Params> {

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000f8\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00078\u0002X\u0083D¢\u0006\u0002\n\u0000R\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u00078\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/registration/request/RegCheckSocialCmd$Params;", "", "context", "Landroid/content/Context;", "accountData", "Lru/mail/registration/ui/AccountData;", "xmailFrom", "", "vkToken", "<init>", "(Landroid/content/Context;Lru/mail/registration/ui/AccountData;Ljava/lang/String;Ljava/lang/String;)V", "regTokenCheck", "email", "vkAccessToken", "htmlEncoded", "", "client", "xmailFromParam", "actMode", "Companion", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params {

        @NotNull
        private static final String PARAM_KEY_CLIENT = "client";

        @NotNull
        private static final String PARAM_KEY_EMAIL = "email";

        @NotNull
        private static final String PARAM_KEY_HTML_ENCODED = "htmlencoded";

        @NotNull
        private static final String PARAM_KEY_VK_ACCESS_TOKEN = "vk_access_token";

        @NotNull
        private static final String PARAM_REGTOKEN_CHECK = "reg_token_check";

        @Keep
        @Param(method = HttpMethod.GET, name = AccountInfoUtilsKt.PARAM_KEY_ACT_MODE)
        @Nullable
        private final String actMode;

        @Param(method = HttpMethod.GET, name = "client")
        @NotNull
        private final String client;

        @Param(method = HttpMethod.POST, name = "email")
        @NotNull
        private final String email;

        @Param(method = HttpMethod.GET, name = PARAM_KEY_HTML_ENCODED)
        private final boolean htmlEncoded;

        @Param(method = HttpMethod.POST, name = "reg_token_check")
        @NotNull
        private final String regTokenCheck;

        @Keep
        @Param(method = HttpMethod.POST, name = PARAM_KEY_VK_ACCESS_TOKEN)
        @Nullable
        private final String vkAccessToken;

        @Keep
        @Param(method = HttpMethod.POST, name = "from")
        @Nullable
        private final String xmailFromParam;

        public Params(@Nullable Context context, @NotNull AccountData accountData, @Nullable String str, @Nullable String str2) {
            Intrinsics.checkNotNullParameter(accountData, "accountData");
            String id2 = accountData.getId();
            Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
            this.regTokenCheck = id2;
            String email = accountData.getEmail();
            Intrinsics.checkNotNullExpressionValue(email, "getEmail(...)");
            this.email = email;
            this.vkAccessToken = str2;
            this.client = "mobile";
            this.xmailFromParam = str;
            String email2 = accountData.getEmail();
            if (context == null) {
                throw new IllegalStateException("Required value was null.");
            }
            this.actMode = AccountInfoUtilsKt.getActiveMode(new AccountInfo(email2, context));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RegCheckSocialCmd(@Nullable Context context, @NotNull AccountData accountData, boolean z10, @Nullable String str, boolean z11, @Nullable String str2) {
        super(context, new Params(context, accountData, str, z11 ? str2 : null), z10);
        Intrinsics.checkNotNullParameter(accountData, "accountData");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.registration.request.RegServerCookieRequest, ru.mail.network.NetworkCommand
    @NotNull
    public RegServerCookieRequest.Result onPostExecuteRequest(@Nullable NetworkCommand.Response response) throws JSONException {
        String string;
        if (response != null) {
            string = new JSONObject(response.getRespString()).getString("body");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        } else {
            string = "";
        }
        return new RegServerCookieRequest.Result(string);
    }
}
