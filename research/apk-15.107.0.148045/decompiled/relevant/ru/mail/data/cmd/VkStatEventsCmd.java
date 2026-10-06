package ru.mail.data.cmd;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.VKAuthenticator;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cmd.server.VkHostProvider;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00112\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u000f\u0010\u0011B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0002¨\u0006\u0012"}, d2 = {"Lru/mail/data/cmd/VkStatEventsCmd;", "Lru/mail/authorizesdk/data/request/common/SingleRequest;", "Lru/mail/data/cmd/VkStatEventsCmd$Params;", "Lru/mail/data/cmd/VkStatEventsCmd$Result;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/VkStatEventsCmd$Params;)V", "getHostProvider", "Lru/mail/network/HostProvider;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResultFromResponse", "Params", "Result", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkStatEventsCmd extends SingleRequest<Params, Result> {
    private static final int STATUS_SUCCESS = 1;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkStatEventsCmd");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0087\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\u0010\u001a\u00020\tH\u0002J\t\u0010\u0011\u001a\u00020\u0003HÂ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÂ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÂ\u0003J\t\u0010\u0014\u001a\u00020\tHÂ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001b\u001a\u00020\tHÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lru/mail/data/cmd/VkStatEventsCmd$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", "event", "Lru/mail/data/cmd/VkStatEventsManager$Event;", "vkAccount", "Lru/mail/auth/VKAuthenticator$VKAccount;", "appId", "", "versionApi", "", "<init>", "(Lru/mail/data/cmd/VkStatEventsManager$Event;Lru/mail/auth/VKAuthenticator$VKAccount;ILjava/lang/String;)V", "queryParams", "", "getQueryParams", "()Ljava/util/Map;", "constructEvents", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandBaseParams {

        @NotNull
        private static final String VK_PLATFORM = "mobile_android";
        private final int appId;

        @NotNull
        private final VkStatEventsManager.Event event;

        @NotNull
        private final String versionApi;

        @NotNull
        private final VKAuthenticator.VKAccount vkAccount;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        @NotNull
        private static final String[] pathSegments = {"method", "statEvents.addMiniAppsCustom"};

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lru/mail/data/cmd/VkStatEventsCmd$Params$Companion;", "", "<init>", "()V", "VK_PLATFORM", "", "pathSegments", "", "getPathSegments", "()[Ljava/lang/String;", "[Ljava/lang/String;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final String[] getPathSegments() {
                return Params.pathSegments;
            }

            private Companion() {
            }
        }

        public Params(@NotNull VkStatEventsManager.Event event, @NotNull VKAuthenticator.VKAccount vkAccount, int i10, @NotNull String versionApi) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(vkAccount, "vkAccount");
            Intrinsics.checkNotNullParameter(versionApi, "versionApi");
            this.event = event;
            this.vkAccount = vkAccount;
            this.appId = i10;
            this.versionApi = versionApi;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final VkStatEventsManager.Event getEvent() {
            return this.event;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final VKAuthenticator.VKAccount getVkAccount() {
            return this.vkAccount;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        private final int getAppId() {
            return this.appId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        private final String getVersionApi() {
            return this.versionApi;
        }

        private final String constructEvents() throws JSONException {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("user_id", this.vkAccount.getVkId());
            jSONObject.put("mini_app_id", this.appId);
            jSONObject.put("event", this.event.getValue());
            jSONObject.put("vk_platform", VK_PLATFORM);
            jSONArray.put(jSONObject);
            String string = jSONArray.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }

        public static /* synthetic */ Params copy$default(Params params, VkStatEventsManager.Event event, VKAuthenticator.VKAccount vKAccount, int i10, String str, int i11, Object obj) {
            if ((i11 & 1) != 0) {
                event = params.event;
            }
            if ((i11 & 2) != 0) {
                vKAccount = params.vkAccount;
            }
            if ((i11 & 4) != 0) {
                i10 = params.appId;
            }
            if ((i11 & 8) != 0) {
                str = params.versionApi;
            }
            return params.copy(event, vKAccount, i10, str);
        }

        @NotNull
        public final Params copy(@NotNull VkStatEventsManager.Event event, @NotNull VKAuthenticator.VKAccount vkAccount, int appId, @NotNull String versionApi) {
            Intrinsics.checkNotNullParameter(event, "event");
            Intrinsics.checkNotNullParameter(vkAccount, "vkAccount");
            Intrinsics.checkNotNullParameter(versionApi, "versionApi");
            return new Params(event, vkAccount, appId, versionApi);
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
            return this.event == params.event && Intrinsics.areEqual(this.vkAccount, params.vkAccount) && this.appId == params.appId && Intrinsics.areEqual(this.versionApi, params.versionApi);
        }

        @NotNull
        public final Map<String, String> getQueryParams() {
            HashMap map = new HashMap();
            map.put("events", constructEvents());
            map.put("access_token", this.vkAccount.getVkToken());
            map.put("app_id", String.valueOf(this.appId));
            map.put(Logger.METHOD_V, this.versionApi);
            return map;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (((((this.event.hashCode() * 31) + this.vkAccount.hashCode()) * 31) + Integer.hashCode(this.appId)) * 31) + this.versionApi.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(event=" + this.event + ", vkAccount=" + this.vkAccount + ", appId=" + this.appId + ", versionApi=" + this.versionApi + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 1)
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/VkStatEventsCmd$Result;", "", "<init>", "()V", "Success", "Fail", "Lru/mail/data/cmd/VkStatEventsCmd$Result$Fail;", "Lru/mail/data/cmd/VkStatEventsCmd$Result$Success;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class Result {
        public static final int $stable = 0;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/VkStatEventsCmd$Result$Fail;", "Lru/mail/data/cmd/VkStatEventsCmd$Result;", "errorCode", "", "errorMsg", "", "<init>", "(ILjava/lang/String;)V", "getErrorCode", "()I", "getErrorMsg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Fail extends Result {
            public static final int $stable = 0;
            private final int errorCode;

            @NotNull
            private final String errorMsg;

            /* JADX WARN: Multi-variable type inference failed */
            public Fail() {
                this(0, null, 3, 0 == true ? 1 : 0);
            }

            public static /* synthetic */ Fail copy$default(Fail fail, int i10, String str, int i11, Object obj) {
                if ((i11 & 1) != 0) {
                    i10 = fail.errorCode;
                }
                if ((i11 & 2) != 0) {
                    str = fail.errorMsg;
                }
                return fail.copy(i10, str);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getErrorCode() {
                return this.errorCode;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getErrorMsg() {
                return this.errorMsg;
            }

            @NotNull
            public final Fail copy(int errorCode, @NotNull String errorMsg) {
                Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
                return new Fail(errorCode, errorMsg);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Fail)) {
                    return false;
                }
                Fail fail = (Fail) other;
                return this.errorCode == fail.errorCode && Intrinsics.areEqual(this.errorMsg, fail.errorMsg);
            }

            public final int getErrorCode() {
                return this.errorCode;
            }

            @NotNull
            public final String getErrorMsg() {
                return this.errorMsg;
            }

            public int hashCode() {
                return (Integer.hashCode(this.errorCode) * 31) + this.errorMsg.hashCode();
            }

            @NotNull
            public String toString() {
                return "Fail(errorCode=" + this.errorCode + ", errorMsg=" + this.errorMsg + ")";
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Fail(int i10, @NotNull String errorMsg) {
                super(null);
                Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
                this.errorCode = i10;
                this.errorMsg = errorMsg;
            }

            public /* synthetic */ Fail(int i10, String str, int i11, DefaultConstructorMarker defaultConstructorMarker) {
                this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? "" : str);
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 1)
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/VkStatEventsCmd$Result$Success;", "Lru/mail/data/cmd/VkStatEventsCmd$Result;", "status", "", "<init>", "(I)V", "getStatus", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Success extends Result {
            public static final int $stable = 0;
            private final int status;

            public Success() {
                this(0, 1, null);
            }

            public static /* synthetic */ Success copy$default(Success success, int i10, int i11, Object obj) {
                if ((i11 & 1) != 0) {
                    i10 = success.status;
                }
                return success.copy(i10);
            }

            /* JADX INFO: renamed from: component1, reason: from getter */
            public final int getStatus() {
                return this.status;
            }

            @NotNull
            public final Success copy(int status) {
                return new Success(status);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Success) && this.status == ((Success) other).status;
            }

            public final int getStatus() {
                return this.status;
            }

            public int hashCode() {
                return Integer.hashCode(this.status);
            }

            @NotNull
            public String toString() {
                return "Success(status=" + this.status + ")";
            }

            public Success(int i10) {
                super(null);
                this.status = i10;
            }

            public /* synthetic */ Success(int i10, int i11, DefaultConstructorMarker defaultConstructorMarker) {
                this((i11 & 1) != 0 ? 1 : i10);
            }
        }

        public /* synthetic */ Result(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Result() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VkStatEventsCmd(@NotNull Context context, @NotNull Params params) {
        super(context, params);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Result getResultFromResponse(NetworkCommand.Response resp) throws JSONException {
        JSONObject jSONObject = new JSONObject(resp.getRespString());
        int iOptInt = jSONObject.optInt("response");
        if (iOptInt == 1) {
            LOG.d("Command execution completed successfully");
            return new Result.Success(iOptInt);
        }
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("error");
        if (jSONObjectOptJSONObject == null) {
            LOG.d("Command execution failed with something really strange response: " + resp.getRespString());
            return new Result.Fail(0, null, 3, 0 == true ? 1 : 0);
        }
        LOG.d("Command execution failed with error: " + jSONObjectOptJSONObject.optInt("error_code"));
        int iOptInt2 = jSONObjectOptJSONObject.optInt("error_code");
        String strOptString = jSONObjectOptJSONObject.optString("error_msg");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return new Result.Fail(iOptInt2, strOptString);
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new VkHostProvider(getParams().getQueryParams(), Params.INSTANCE.getPathSegments(), ConfigurationRepository.from(getContext()).getConfiguration().getSocialLoginConfig().getVkConnectHost());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public Result onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        LOG.d("On post execute request with response as " + resp);
        try {
            return getResultFromResponse(resp);
        } catch (JSONException e10) {
            LOG.e("Error parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
