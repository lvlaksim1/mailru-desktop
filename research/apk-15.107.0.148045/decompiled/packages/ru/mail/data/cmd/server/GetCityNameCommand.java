package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContextUtil;
import ru.mail.logic.profile.CityInfo;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.config.MigrateToPostUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u000eB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\rH\u0014¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/GetCityNameCommand;", "Lru/mail/serverapi/ServerCommandBase;", "Lru/mail/data/cmd/server/GetCityNameCommand$Params;", "Lru/mail/logic/profile/CityInfo;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/GetCityNameCommand$Params;)V", "onPostExecuteRequest", "resp", "Lru/mail/network/NetworkCommand$Response;", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "golang", "geo", "cities", "city"})
public final class GetCityNameCommand extends ServerCommandBase<Params, CityInfo> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/GetCityNameCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "dataManager", "Lru/mail/logic/content/DataManager;", "cityId", "", "<init>", "(Lru/mail/logic/content/DataManager;Ljava/lang/String;)V", "getCityId", "()Ljava/lang/String;", "equals", "", "other", "", "hashCode", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @Param(method = HttpMethod.POST, name = "city_id")
        @NotNull
        private final String cityId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull DataManager dataManager, @NotNull String cityId) {
            super(MailboxContextUtil.getAccountInfo(dataManager.getMailboxContext(), dataManager), MailboxContextUtil.getFolderState(dataManager.getMailboxContext()));
            Intrinsics.checkNotNullParameter(dataManager, "dataManager");
            Intrinsics.checkNotNullParameter(cityId, "cityId");
            this.cityId = cityId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Params) && super.equals(other) && Intrinsics.areEqual(this.cityId, ((Params) other).cityId);
        }

        @NotNull
        public final String getCityId() {
            return this.cityId;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + this.cityId.hashCode();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GetCityNameCommand(@NotNull Context context, @NotNull Params params) {
        super(context, params, MigrateToPostUtils.is12130Enabled(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public CityInfo onPostExecuteRequest(@NotNull NetworkCommand.Response resp) throws JSONException, NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(resp, "resp");
        JSONObject jSONObjectOptJSONObject = new JSONObject(resp.getRespString()).getJSONObject("body").optJSONObject("city");
        if (jSONObjectOptJSONObject != null) {
            int i10 = jSONObjectOptJSONObject.getInt("city_id");
            String string = jSONObjectOptJSONObject.getString("name");
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return new CityInfo(i10, string);
        }
        throw new NetworkCommand.PostExecuteException("City city_id=" + ((Params) getParams()).getCityId() + " not found");
    }
}
