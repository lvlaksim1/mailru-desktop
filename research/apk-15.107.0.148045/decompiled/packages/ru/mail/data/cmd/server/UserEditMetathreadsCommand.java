package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.content.MetaThreadEnableState;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.Param;
import ru.mail.network.ParamNameValuePair;
import ru.mail.network.ServerParamsFactory;
import ru.mail.network.UrlPath;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.PostServerRequest;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.util.config.MigrateToPostUtils;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u000eB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0014¨\u0006\u000f"}, d2 = {"Lru/mail/data/cmd/server/UserEditMetathreadsCommand;", "Lru/mail/serverapi/PostServerRequest;", "Lru/mail/data/cmd/server/UserEditMetathreadsCommand$Params;", "Lru/mail/mailbox/cmd/EmptyResult;", "context", "Landroid/content/Context;", "params", "<init>", "(Landroid/content/Context;Lru/mail/data/cmd/server/UserEditMetathreadsCommand$Params;)V", "getDefaultApiType", "Lru/mail/serverapi/MailAuthorizationApiType;", "onPostExecuteRequest", "response", "Lru/mail/network/NetworkCommand$Response;", "Params", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "golang", "user", "edit", "metathreads"})
public final class UserEditMetathreadsCommand extends PostServerRequest<Params, EmptyResult> {
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @StabilityInferred(parameters = 0)
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\b\u001a\u00020\u0003HÂ\u0003J\t\u0010\t\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/UserEditMetathreadsCommand$Params;", "Lru/mail/serverapi/ServerCommandEmailParams;", "settings", "Lru/mail/data/cmd/server/UserEditMetathreadsCommand$Params$Settings;", "accountInfo", "Lru/mail/auth/request/AccountInfo;", "<init>", "(Lru/mail/data/cmd/server/UserEditMetathreadsCommand$Params$Settings;Lru/mail/auth/request/AccountInfo;)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Settings", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Params extends ServerCommandEmailParams {
        public static final int $stable = 8;

        @NotNull
        private final AccountInfo accountInfo;

        @Param(method = HttpMethod.POST, type = Param.Type.COMPLEX_OBJECT)
        @NotNull
        private final Settings settings;

        /* JADX INFO: compiled from: ProGuard */
        @StabilityInferred(parameters = 0)
        @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0016J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÂ\u0003J\u0019\u0010\u000b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/data/cmd/server/UserEditMetathreadsCommand$Params$Settings;", "Lru/mail/network/ServerParamsFactory;", "settings", "", "Lru/mail/logic/content/MetaThreadEnableState;", "<init>", "(Ljava/util/List;)V", "createParams", "", "Lorg/apache/http/NameValuePair;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Settings implements ServerParamsFactory {
            public static final int $stable = 8;

            @NotNull
            private final List<MetaThreadEnableState> settings;

            public Settings(@NotNull List<MetaThreadEnableState> settings) {
                Intrinsics.checkNotNullParameter(settings, "settings");
                this.settings = settings;
            }

            private final List<MetaThreadEnableState> component1() {
                return this.settings;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Settings copy$default(Settings settings, List list, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    list = settings.settings;
                }
                return settings.copy(list);
            }

            @NotNull
            public final Settings copy(@NotNull List<MetaThreadEnableState> settings) {
                Intrinsics.checkNotNullParameter(settings, "settings");
                return new Settings(settings);
            }

            @Override // ru.mail.network.ServerParamsFactory
            @NotNull
            public List<NameValuePair> createParams() throws JSONException {
                JSONArray jSONArray = new JSONArray();
                for (MetaThreadEnableState metaThreadEnableState : this.settings) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("folder_id", metaThreadEnableState.getFolderId());
                    jSONObject.put("state", metaThreadEnableState.getState().getValue());
                    jSONArray.put(jSONObject);
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(new ParamNameValuePair("metathread_visible", jSONArray.toString()));
                return arrayList;
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Settings) && Intrinsics.areEqual(this.settings, ((Settings) other).settings);
            }

            public int hashCode() {
                return this.settings.hashCode();
            }

            @NotNull
            public String toString() {
                return "Settings(settings=" + this.settings + ")";
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@NotNull Settings settings, @NotNull AccountInfo accountInfo) {
            super(accountInfo, null);
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            this.settings = settings;
            this.accountInfo = accountInfo;
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        private final Settings getSettings() {
            return this.settings;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        private final AccountInfo getAccountInfo() {
            return this.accountInfo;
        }

        public static /* synthetic */ Params copy$default(Params params, Settings settings, AccountInfo accountInfo, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                settings = params.settings;
            }
            if ((i10 & 2) != 0) {
                accountInfo = params.accountInfo;
            }
            return params.copy(settings, accountInfo);
        }

        @NotNull
        public final Params copy(@NotNull Settings settings, @NotNull AccountInfo accountInfo) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            Intrinsics.checkNotNullParameter(accountInfo, "accountInfo");
            return new Params(settings, accountInfo);
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
            return Intrinsics.areEqual(this.settings, params.settings) && Intrinsics.areEqual(this.accountInfo, params.accountInfo);
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (this.settings.hashCode() * 31) + this.accountInfo.hashCode();
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        @NotNull
        public String toString() {
            return "Params(settings=" + this.settings + ", accountInfo=" + this.accountInfo + ")";
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserEditMetathreadsCommand(@NotNull Context context, @NotNull Params params) {
        super(context, params, MigrateToPostUtils.is12150Enabled(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    @NotNull
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public EmptyResult onPostExecuteRequest(@NotNull NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        Intrinsics.checkNotNullParameter(response, "response");
        return new EmptyResult();
    }
}
