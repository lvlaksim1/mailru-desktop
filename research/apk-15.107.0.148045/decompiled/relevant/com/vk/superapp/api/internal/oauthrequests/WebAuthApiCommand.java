package com.vk.superapp.api.internal.oauthrequests;

import android.net.Uri;
import com.vk.api.external.ExternalApiManagerKt;
import com.vk.api.external.call.HttpUrlPostCall;
import com.vk.api.external.exceptions.VKWebAuthException;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.internal.ApiCommand;
import com.vk.api.sdk.internal.QueryStringGenerator;
import com.vk.api.sdk.utils.VKApiCredentialsExtKt;
import com.vk.superapp.api.analytics.RegistrationStatParamsFactory;
import com.vk.superapp.api.chain.auth.WebAuthHttpUrlChainCall;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.superapp.api.states.VkGetOauthTokenArgs;
import com.vk.superapp.core.api.models.WebAuthAnswer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u000eB!\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0014¢\u0006\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/vk/superapp/api/internal/oauthrequests/WebAuthApiCommand;", "Lcom/vk/api/sdk/internal/ApiCommand;", "Lcom/vk/superapp/core/api/models/WebAuthAnswer;", "", "url", "Lcom/vk/superapp/api/states/VkGetOauthTokenArgs;", "args", "accessTokenParameterName", "<init>", "(Ljava/lang/String;Lcom/vk/superapp/api/states/VkGetOauthTokenArgs;Ljava/lang/String;)V", "Lcom/vk/api/sdk/VKApiManager;", "manager", "onExecute", "(Lcom/vk/api/sdk/VKApiManager;)Lcom/vk/superapp/core/api/models/WebAuthAnswer;", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebAuthApiCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebAuthApiCommand.kt\ncom/vk/superapp/api/internal/oauthrequests/WebAuthApiCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,156:1\n1869#2,2:157\n1#3:159\n*S KotlinDebug\n*F\n+ 1 WebAuthApiCommand.kt\ncom/vk/superapp/api/internal/oauthrequests/WebAuthApiCommand\n*L\n139#1:157,2\n*E\n"})
public class WebAuthApiCommand extends ApiCommand<WebAuthAnswer> {

    @NotNull
    public static final String DEFAULT_ACCESS_TOKEN_PARAMETER_NAME = "access_token";

    @NotNull
    private final String ipakvmoca;

    @NotNull
    private final VkGetOauthTokenArgs ipakvmocb;

    @NotNull
    private final String ipakvmocc;

    @NotNull
    private final String ipakvmocd;

    public /* synthetic */ WebAuthApiCommand(String str, VkGetOauthTokenArgs vkGetOauthTokenArgs, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, vkGetOauthTokenArgs, (i10 & 4) != 0 ? "access_token" : str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RequestBody ipakvmoca(WebAuthApiCommand webAuthApiCommand, VKApiManager vKApiManager, String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = webAuthApiCommand.ipakvmocb.getCom.huawei.hms.support.feature.result.CommonConstant.KEY_ACCESS_TOKEN java.lang.String();
        String str7 = webAuthApiCommand.ipakvmocb.getCom.vk.accountmanager.data.AccountManagerRepositoryImpl.SECRET_ARG java.lang.String();
        String strActiveAccessToken = VKApiCredentialsExtKt.activeAccessToken(vKApiManager.getExecutor().getCredentials().getValue());
        String strActiveSecret = VKApiCredentialsExtKt.activeSecret(vKApiManager.getExecutor().getCredentials().getValue());
        if (strActiveAccessToken.length() <= 0 || Intrinsics.areEqual(strActiveAccessToken, str) || Intrinsics.areEqual(strActiveAccessToken, str6)) {
            str2 = str6;
            str3 = str7;
        } else {
            str2 = strActiveAccessToken;
            str3 = strActiveSecret;
        }
        String value = vKApiManager.getConfig().getDeviceId().getValue();
        Map<String, String> map = webAuthApiCommand.ipakvmocb.toMap();
        ArrayList arrayList = new ArrayList(4);
        if (value != null && value.length() != 0 && ((str5 = map.get("device_id")) == null || str5.length() == 0)) {
            arrayList.add(TuplesKt.to("device_id", value));
        }
        Iterator<T> it = new RegistrationStatParamsFactory().getAnalyticParams().iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            String str8 = (String) pair.component1();
            String str9 = (String) pair.component2();
            if (str9 != null && str9.length() != 0 && ((str4 = map.get(str8)) == null || str4.length() == 0)) {
                arrayList.add(TuplesKt.to(str8, str9));
            }
        }
        if (!arrayList.isEmpty()) {
            map = MapsKt.toMutableMap(map);
            MapsKt.putAll(map, arrayList);
        }
        return RequestBody.INSTANCE.create(QueryStringGenerator.buildSignedQueryString$default(QueryStringGenerator.INSTANCE, webAuthApiCommand.ipakvmocd, map, vKApiManager.getConfig().getVersion(), str2, str3, vKApiManager.getConfig().getAppId(), null, false, null, false, VKApiCodes.CODE_CALL_REQUIRES_AUTH, null), MediaType.INSTANCE.get("application/x-www-form-urlencoded; charset=utf-8"));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vk.api.sdk.internal.ApiCommand
    @NotNull
    public WebAuthAnswer onExecute(@NotNull final VKApiManager manager) throws VKWebAuthException {
        Intrinsics.checkNotNullParameter(manager, "manager");
        final String strActiveAccessToken = VKApiCredentialsExtKt.activeAccessToken(manager.getExecutor().getCredentials().getValue());
        HttpUrlPostCall httpUrlPostCall = new HttpUrlPostCall(this.ipakvmoca, 0L, SuperappApiCore.INSTANCE.debugConfig().getAuthRetryCount(), 0, new Function0() { // from class: com.vk.superapp.api.internal.oauthrequests.h
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return WebAuthApiCommand.ipakvmoca(this.f51973a, manager, strActiveAccessToken);
            }
        }, (List) null, 42, (DefaultConstructorMarker) null);
        return (WebAuthAnswer) ExternalApiManagerKt.execute$default(manager, httpUrlPostCall, new WebAuthHttpUrlChainCall(manager, httpUrlPostCall, this.ipakvmocc), false, 4, null);
    }

    public WebAuthApiCommand(@NotNull String url, @NotNull VkGetOauthTokenArgs args, @NotNull String accessTokenParameterName) {
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(args, "args");
        Intrinsics.checkNotNullParameter(accessTokenParameterName, "accessTokenParameterName");
        this.ipakvmoca = url;
        this.ipakvmocb = args;
        this.ipakvmocc = accessTokenParameterName;
        String path = Uri.parse(url).getPath();
        this.ipakvmocd = path == null ? "" : path;
    }
}
