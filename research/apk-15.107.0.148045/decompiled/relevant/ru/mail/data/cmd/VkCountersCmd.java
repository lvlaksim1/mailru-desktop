package ru.mail.data.cmd;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.huawei.hms.support.feature.result.CommonConstant;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.authorizesdk.data.request.common.SingleRequest;
import ru.mail.cloud.app.viewer.ui.ViewerActivity;
import ru.mail.config.ConfigurationRepository;
import ru.mail.data.cache.VkCountersCache;
import ru.mail.data.cmd.server.VkHostProvider;
import ru.mail.logic.content.VkCountersInfo;
import ru.mail.network.HostProvider;
import ru.mail.network.NetworkCommand;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u000f\u0010B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/data/cmd/VkCountersCmd;", "Lru/mail/authorizesdk/data/request/common/SingleRequest;", "Lru/mail/data/cmd/VkCountersCmd$Params;", "Lru/mail/logic/content/VkCountersInfo;", "mContext", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/VkCountersCmd$Params;)V", "getHostProvider", "Lru/mail/network/HostProvider;", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getResultFromResponse", "Params", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkCountersCmd extends SingleRequest<Params, VkCountersInfo> {

    @NotNull
    private final Context mContext;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("VkCountersCmd");

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÂ\u0003J\t\u0010\f\u001a\u00020\u0003HÂ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/VkCountersCmd$Params;", "Lru/mail/serverapi/ServerCommandBaseParams;", ViewerActivity.FILTER, "", CommonConstant.KEY_ACCESS_TOKEN, "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "queryParams", "", "getQueryParams", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandBaseParams {

        @NotNull
        private final String accessToken;

        @NotNull
        private final String filter;

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        @NotNull
        public static final Companion INSTANCE = new Companion(null);
        public static final int $stable = 8;

        @NotNull
        private static final String[] pathSegments = {"method", "account.getCounters"};

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lru/mail/data/cmd/VkCountersCmd$Params$Companion;", "", "<init>", "()V", "pathSegments", "", "", "getPathSegments", "()[Ljava/lang/String;", "[Ljava/lang/String;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
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

        public Params(@NotNull String filter, @NotNull String accessToken) {
            Intrinsics.checkNotNullParameter(filter, "filter");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            this.filter = filter;
            this.accessToken = accessToken;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final String getFilter() {
            return this.filter;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final String getAccessToken() {
            return this.accessToken;
        }

        public static /* synthetic */ Params copy$default(Params params, String str, String str2, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                str = params.filter;
            }
            if ((i10 & 2) != 0) {
                str2 = params.accessToken;
            }
            return params.copy(str, str2);
        }

        @NotNull
        public final Params copy(@NotNull String filter, @NotNull String accessToken) {
            Intrinsics.checkNotNullParameter(filter, "filter");
            Intrinsics.checkNotNullParameter(accessToken, "accessToken");
            return new Params(filter, accessToken);
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
            return Intrinsics.areEqual(this.filter, params.filter) && Intrinsics.areEqual(this.accessToken, params.accessToken);
        }

        @NotNull
        public final Map<String, String> getQueryParams() {
            HashMap map = new HashMap();
            map.put(ViewerActivity.FILTER, this.filter);
            map.put("access_token", this.accessToken);
            map.put(Logger.METHOD_V, VkHostProvider.VK_API_VERSION);
            return map;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (this.filter.hashCode() * 31) + this.accessToken.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(filter=" + this.filter + ", accessToken=" + this.accessToken + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VkCountersCmd(@NotNull Context mContext, @NotNull Params params) {
        super(mContext, params);
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(params, "params");
        this.mContext = mContext;
    }

    private final VkCountersInfo getResultFromResponse(NetworkCommand.Response resp) throws JSONException {
        JSONObject jSONObjectOptJSONObject = new JSONObject(resp.getRespString()).optJSONObject("response");
        int iOptInt = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optInt("messages") : 0;
        new VkCountersCache(this.mContext).saveVkCounterValue(iOptInt);
        VkCountersInfo vkCountersInfo = new VkCountersInfo();
        vkCountersInfo.setMessageCount(iOptInt);
        return vkCountersInfo;
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        return new VkHostProvider(getParams().getQueryParams(), Params.INSTANCE.getPathSegments(), ConfigurationRepository.from(getContext()).getConfiguration().getSocialLoginConfig().getVkConnectHost());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public VkCountersInfo onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        try {
            return getResultFromResponse(resp);
        } catch (JSONException e10) {
            LOG.e("Error parsing response " + e10);
            throw new NetworkCommand.PostExecuteException(e10);
        }
    }
}
