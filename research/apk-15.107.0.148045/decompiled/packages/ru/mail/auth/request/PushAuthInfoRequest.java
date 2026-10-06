package ru.mail.auth.request;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.data.cmd.server.AddFilterCommand;
import ru.mail.data.cmd.server.JsonStatusResponseProcessor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.ResponseProcessor;
import ru.mail.network.ServerApi;
import ru.mail.network.UrlPath;
import ru.mail.ui.promosheet.xmailmigration.XmailMigrationPromoSheet;
import ru.mail.util.push.BasePushFactory;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@HostProviderAnnotation(defHostStrRes = "string/swa_default_host", defSchemeStrRes = "string/swa_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "swa")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "pushauth", XmailMigrationPromoSheet.BUTTON_INFO})
public class PushAuthInfoRequest extends SingleRequest<Params, Result> {

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final boolean hasEsiaBind;
        private final boolean m2FAUser;
        private final boolean mAvailable;
        private final boolean mEmailExists;
        private final ExternalAuthInfo mExternalAuth;
        private final boolean mHasPhone;
        private final boolean mHasVkc;
        private final OAuthProvider mOAuthProvider;
        private final String mSat;
        private final boolean mSoftVkid;
        private final AuthType mType;
        private final VkPasswordAuthInfo mVkPasswordAuth;

        @NotNull
        private final List<String> mWebAuthNKeys;

        public Result(boolean z10, boolean z11, boolean z12, boolean z13, AuthType authType, OAuthProvider oAuthProvider, @NotNull List<String> list, boolean z14, String str, String str2, @Nullable String str3, boolean z15, boolean z16) {
            this.mHasPhone = z10;
            this.mAvailable = z11;
            this.mEmailExists = z12;
            this.m2FAUser = z13;
            this.mType = authType;
            this.mOAuthProvider = oAuthProvider;
            this.mWebAuthNKeys = list;
            this.mHasVkc = z14;
            this.mExternalAuth = new ExternalAuthInfo(str);
            this.mVkPasswordAuth = new VkPasswordAuthInfo(str2);
            this.mSat = str3;
            this.mSoftVkid = z15;
            this.hasEsiaBind = z16;
        }

        public ExternalAuthInfo getExternalAuth() {
            return this.mExternalAuth;
        }

        public OAuthProvider getOAuthProvider() {
            return this.mOAuthProvider;
        }

        @Nullable
        public String getSat() {
            return this.mSat;
        }

        public boolean getSoftVkid() {
            return this.mSoftVkid;
        }

        public AuthType getType() {
            return this.mType;
        }

        public VkPasswordAuthInfo getVkPasswordAuth() {
            return this.mVkPasswordAuth;
        }

        public List<String> getWebAuthNKeys() {
            return this.mWebAuthNKeys;
        }

        public boolean hasEsia() {
            return this.hasEsiaBind;
        }

        public boolean hasPhone() {
            return this.mHasPhone;
        }

        public boolean is2FAUser() {
            return this.m2FAUser;
        }

        public boolean isAvailable() {
            return this.mAvailable;
        }

        public boolean isEmailExists() {
            return this.mEmailExists;
        }

        public boolean isHasVkc() {
            return this.mHasVkc;
        }

        public boolean isWebAuthNKeysExist() {
            return this.mWebAuthNKeys.size() > 0;
        }
    }

    public PushAuthInfoRequest(Context context, Params params, boolean z10) {
        super(context, params, z10);
    }

    private List<String> parseWebAuthNKeys(@Nullable JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                String strOptString = jSONArray.optString(i10);
                if (strOptString != null) {
                    arrayList.add(strOptString);
                }
            }
        }
        return arrayList;
    }

    @Override // ru.mail.network.NetworkCommand
    protected NetworkCommand<Params, Result>.NetworkCommandBaseDelegate getCustomDelegate() {
        return new NetworkCommand<Params, Result>.NetworkCommandBaseDelegate() { // from class: ru.mail.auth.request.PushAuthInfoRequest.1
            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String str) {
                try {
                    return new JSONObject(str).getString("status");
                } catch (JSONException unused) {
                    return "-1";
                }
            }

            @Override // ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            public CommandStatus<?> onFolderAccessDenied() {
                return new CommandStatus.ERROR();
            }
        };
    }

    @Override // ru.mail.network.NetworkCommand
    protected ResponseProcessor getResponseProcessor(NetworkCommand.Response response, ServerApi serverApi, NetworkCommand<Params, Result>.NetworkCommandBaseDelegate networkCommandBaseDelegate) {
        return new JsonStatusResponseProcessor(response, networkCommandBaseDelegate);
    }

    public PushAuthInfoRequest(Context context, Params params, HostProvider hostProvider) {
        super(context, params, hostProvider);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        try {
            JSONObject jSONObject = new JSONObject(response.getRespString()).getJSONObject("body");
            return new Result(jSONObject.getBoolean("phone"), jSONObject.getBoolean(BasePushFactory.KEY_AVAILABLE), jSONObject.getBoolean(AddFilterCommand.EXISTS), jSONObject.getBoolean("twostep"), AuthType.from(jSONObject.optString("auth")), OAuthProvider.from(jSONObject.optString("provider")), parseWebAuthNKeys(jSONObject.optJSONArray(WebAuthNSendAgentRequest.TYPE)), jSONObject.getBoolean("has_vkc"), jSONObject.optString("external_auth"), jSONObject.optString("vkid_auth"), jSONObject.optString("sat", null), jSONObject.optBoolean("soft_vkid_bind"), jSONObject.optBoolean("has_esia"));
        } catch (JSONException unused) {
            throw new NetworkCommand.PostExecuteException();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {

        @Param(method = HttpMethod.GET, name = PreferenceHostProvider.URL_PARAM_MMP)
        private final String mClient;

        @Param(method = HttpMethod.GET, name = "login")
        private final String mLogin;

        @Param(method = HttpMethod.POST, name = "sat")
        private final boolean mNeedSuperAppToken;

        @Param(method = HttpMethod.GET, name = PreferenceHostProvider.URL_PARAM_MP)
        private final String mPlatform;

        @Param(method = HttpMethod.HEADER_SET, name = "X-Mobile-App")
        private final String xMobileApp;

        public Params(@NotNull String str, @NotNull String str2) {
            this.mPlatform = "android";
            this.mClient = "mail";
            this.mLogin = str;
            this.mNeedSuperAppToken = false;
            this.xMobileApp = str2;
        }

        public String getLogin() {
            return this.mLogin;
        }

        public Params(@NotNull String str, boolean z10, @NotNull String str2) {
            this.mPlatform = "android";
            this.mClient = "mail";
            this.mLogin = str;
            this.mNeedSuperAppToken = z10;
            this.xMobileApp = str2;
        }
    }
}
