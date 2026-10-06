package ru.mail.registration.request.childsignup;

import android.content.Context;
import androidx.annotation.Keep;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.HostProvider;
import ru.mail.network.hostprovider.customquery.CustomQueryHostProvider;
import ru.mail.network.hostprovider.customquery.CustomQueryParams;
import ru.mail.registration.request.RegCodeCmd;
import ru.mail.registration.request.RegServerIdRequest;
import ru.mail.registration.ui.AccountData;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB/\b\u0007\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\b\u0010\f\u001a\u00020\u0006H\u0014J\b\u0010\r\u001a\u00020\tH\u0014¨\u0006\u000f"}, d2 = {"Lru/mail/registration/request/childsignup/ChildSignUpCodeCmd;", "Lru/mail/registration/request/RegServerIdRequest;", "Lru/mail/registration/request/childsignup/ChildSignUpCodeCmd$Params;", "context", "Landroid/content/Context;", "provider", "Lru/mail/network/HostProvider;", "params", "usePostParams", "", "<init>", "(Landroid/content/Context;Lru/mail/network/HostProvider;Lru/mail/registration/request/childsignup/ChildSignUpCodeCmd$Params;Z)V", "getHostProvider", "prepareUrlMigrateToPost", "Params", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChildSignUpCodeCmd extends RegServerIdRequest<Params> {

    /* JADX INFO: compiled from: ProGuard */
    @Keep
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lru/mail/registration/request/childsignup/ChildSignUpCodeCmd$Params;", "Lru/mail/registration/request/RegCodeCmd$Params;", "Lru/mail/network/hostprovider/customquery/CustomQueryParams;", "context", "Landroid/content/Context;", "accountData", "Lru/mail/registration/ui/AccountData;", "parentAccessToken", "", "<init>", "(Landroid/content/Context;Lru/mail/registration/ui/AccountData;Ljava/lang/String;)V", "queryParams", "", "getQueryParams", "()Ljava/util/Map;", "queryPath", "getQueryPath", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Params extends RegCodeCmd.Params implements CustomQueryParams {

        @Nullable
        private final AccountData accountData;

        @NotNull
        private final String parentAccessToken;

        @NotNull
        private final String queryPath;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Params(@Nullable Context context, @Nullable AccountData accountData, @NotNull String parentAccessToken) {
            super(context, accountData);
            Intrinsics.checkNotNullParameter(parentAccessToken, "parentAccessToken");
            this.accountData = accountData;
            this.parentAccessToken = parentAccessToken;
            this.queryPath = "api/v1/user/signup/child";
        }

        @Override // ru.mail.network.hostprovider.customquery.CustomQueryParams
        @NotNull
        public Map<String, String> getQueryParams() {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            AccountData accountData = this.accountData;
            linkedHashMap.put("email", accountData != null ? accountData.getParentEmail() : null);
            linkedHashMap.put("access_token", this.parentAccessToken);
            return linkedHashMap;
        }

        @Override // ru.mail.network.hostprovider.customquery.CustomQueryParams
        @NotNull
        public String getQueryPath() {
            return this.queryPath;
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChildSignUpCodeCmd(@Nullable Context context, @NotNull Params params, boolean z10) {
        this(context, null, params, z10, 2, null);
        Intrinsics.checkNotNullParameter(params, "params");
    }

    @Override // ru.mail.network.NetworkCommand
    @NotNull
    protected HostProvider getHostProvider() {
        HostProvider hostProvider = super.getHostProvider();
        Intrinsics.checkNotNullExpressionValue(hostProvider, "getHostProvider(...)");
        P params = getParams();
        Intrinsics.checkNotNullExpressionValue(params, "getParams(...)");
        return new CustomQueryHostProvider(hostProvider, (CustomQueryParams) params);
    }

    @Override // ru.mail.network.NetworkCommand
    protected boolean prepareUrlMigrateToPost() {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public ChildSignUpCodeCmd(@Nullable Context context, @Nullable HostProvider hostProvider, @NotNull Params params, boolean z10) {
        super(context, params, hostProvider, z10);
        Intrinsics.checkNotNullParameter(params, "params");
    }

    public /* synthetic */ ChildSignUpCodeCmd(Context context, HostProvider hostProvider, Params params, boolean z10, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i10 & 2) != 0 ? null : hostProvider, params, z10);
    }
}
