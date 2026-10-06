package com.vk.superapp.api.internal;

import android.content.Context;
import com.google.android.gms.ads.RequestConfiguration;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.api.external.InternalMethodCall;
import com.vk.api.sdk.VKApiConfig;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.okhttp.OkHttpExecutor;
import com.vk.api.sdk.requests.VKRequest;
import com.vk.dto.common.id.UserId;
import com.vk.lists.PaginationHelper;
import com.vk.superapp.api.analytics.RegistrationStatParamsFactory;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.superapp.api.core.WebPersistentRequest;
import com.vk.superapp.api.internal.extensions.ApiCommandExtKt;
import com.vk.superapp.core.R;
import com.vk.usersstore.blockstore.deletereceiver.BlockstoreDeleteReceiver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import java.io.IOException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ok.android.sdk.SharedKt;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0016\u0018\u0000 i*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001iB\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J'\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0014\u001a\u00020\u0010¢\u0006\u0004\b\u0015\u0010\u0013J\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0016\u001a\u00020\u0004¢\u0006\u0004\b\u0017\u0010\u0018J\r\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u0014\u0010\u001d\u001a\t\u0018\u00018\u0000¢\u0006\u0002\b\u001c¢\u0006\u0004\b\u001d\u0010\u001eJ\u001b\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001f\u001a\u00020\u0010¢\u0006\u0004\b \u0010\u0013J%\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b#\u0010\u000bJ#\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020$¢\u0006\u0004\b#\u0010%J#\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020&¢\u0006\u0004\b#\u0010'J#\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\f¢\u0006\u0004\b#\u0010(J'\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020)2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030*¢\u0006\u0004\b#\u0010,J'\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020)2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030-¢\u0006\u0004\b#\u0010.J#\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020)2\u0006\u0010+\u001a\u00020/¢\u0006\u0004\b#\u00100J#\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0010¢\u0006\u0004\b#\u00101J\u001f\u00102\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\u0010\"\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0004\b2\u0010\u0018J\u0015\u00103\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016¢\u0006\u0004\b3\u00104J\u001d\u00105\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b5\u0010\u0013J\u001d\u00107\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00106\u001a\u00020\u0010H\u0016¢\u0006\u0004\b7\u0010\u0013J\u001d\u00108\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0014\u001a\u00020\u0010H\u0016¢\u0006\u0004\b8\u0010\u0013J\u001d\u0010:\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00109\u001a\u00020\u0010H\u0016¢\u0006\u0004\b:\u0010\u0013J!\u0010>\u001a\b\u0012\u0004\u0012\u00028\u00000=2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;H\u0017¢\u0006\u0004\b>\u0010?J!\u0010A\u001a\b\u0012\u0004\u0012\u00028\u00000@2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;H\u0017¢\u0006\u0004\bA\u0010BJ!\u0010C\u001a\b\u0012\u0004\u0012\u00028\u00000@2\n\b\u0002\u0010<\u001a\u0004\u0018\u00010;H\u0017¢\u0006\u0004\bC\u0010BJ\u0017\u0010F\u001a\u00028\u00002\u0006\u0010E\u001a\u00020DH\u0004¢\u0006\u0004\bF\u0010GJ\u0017\u0010K\u001a\u00020J2\u0006\u0010I\u001a\u00020HH\u0014¢\u0006\u0004\bK\u0010LR\u001a\u0010I\u001a\u00020H8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bM\u0010N\u001a\u0004\bO\u0010PR\u001a\u0010V\u001a\u00020Q8\u0004X\u0084\u0004¢\u0006\f\n\u0004\bR\u0010S\u001a\u0004\bT\u0010UR\u001a\u0010[\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\bW\u0010X\u001a\u0004\bY\u0010ZR\u001a\u0010^\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\\\u0010X\u001a\u0004\b]\u0010ZR\u001a\u0010c\u001a\u00020\u00108\u0016X\u0096D¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR\"\u0010h\u001a\u00020\u00108\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bd\u0010`\u001a\u0004\be\u0010b\"\u0004\bf\u0010g¨\u0006j"}, d2 = {"Lcom/vk/superapp/api/internal/WebApiRequest;", "", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lcom/vk/api/sdk/requests/VKRequest;", "", "method", "<init>", "(Ljava/lang/String;)V", CommonConstant.KEY_ACCESS_TOKEN, AccountManagerRepositoryImpl.SECRET_ARG, "overrideAuth", "(Ljava/lang/String;Ljava/lang/String;)Lcom/vk/superapp/api/internal/WebApiRequest;", "Lcom/vk/dto/common/id/UserId;", BlockstoreDeleteReceiver.PARAM_USER_ID, "withAccessTokenOf", "(Lcom/vk/dto/common/id/UserId;)Lcom/vk/superapp/api/internal/WebApiRequest;", "", "allow", "allowDeprecatedPhotoFields", "(Z)Lcom/vk/superapp/api/internal/WebApiRequest;", "remove", "forceRemoveAccessToken", "token", "setSuperappToken", "(Ljava/lang/String;)Lcom/vk/superapp/api/internal/WebApiRequest;", "Lcom/vk/superapp/api/core/WebPersistentRequest;", "toWebPersistentRequest", "()Lcom/vk/superapp/api/core/WebPersistentRequest;", "Lio/reactivex/rxjava3/annotations/NonNull;", "execSync", "()Ljava/lang/Object;", "isSkip", "skipValidation", "name", "value", RemoteMessageConst.MessageBody.PARAM, "", "(Ljava/lang/String;I)Lcom/vk/superapp/api/internal/WebApiRequest;", "", "(Ljava/lang/String;J)Lcom/vk/superapp/api/internal/WebApiRequest;", "(Ljava/lang/String;Lcom/vk/dto/common/id/UserId;)Lcom/vk/superapp/api/internal/WebApiRequest;", "", "", "values", "(Ljava/lang/CharSequence;[Ljava/lang/Object;)Lcom/vk/superapp/api/internal/WebApiRequest;", "", "(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Lcom/vk/superapp/api/internal/WebApiRequest;", "", "(Ljava/lang/CharSequence;[I)Lcom/vk/superapp/api/internal/WebApiRequest;", "(Ljava/lang/String;Z)Lcom/vk/superapp/api/internal/WebApiRequest;", "setCacheControl", "allowNoAuth", "()Lcom/vk/superapp/api/internal/WebApiRequest;", "setAnonymous", "isMultipleTokens", "setIsMultipleTokens", "forceRemoveAuth", "force", "forceAnonymous", "Lcom/vk/superapp/api/internal/WebApiThreadHolder;", "threadHolder", "Lio/reactivex/rxjava3/core/Observable;", "toUiObservable", "(Lcom/vk/superapp/api/internal/WebApiThreadHolder;)Lio/reactivex/rxjava3/core/Observable;", "Lio/reactivex/rxjava3/core/Single;", "toUiSingle", "(Lcom/vk/superapp/api/internal/WebApiThreadHolder;)Lio/reactivex/rxjava3/core/Single;", "toBgSingle", "Lcom/vk/api/sdk/VKApiManager;", "manager", "onExecute", "(Lcom/vk/api/sdk/VKApiManager;)Ljava/lang/Object;", "Lcom/vk/api/sdk/VKApiConfig;", "config", "Lcom/vk/api/external/InternalMethodCall$Builder;", "createBaseCallBuilder", "(Lcom/vk/api/sdk/VKApiConfig;)Lcom/vk/api/external/InternalMethodCall$Builder;", "ipakvmoca", "Lcom/vk/api/sdk/VKApiConfig;", "getConfig", "()Lcom/vk/api/sdk/VKApiConfig;", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "ipakvmocb", "Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "getExecutor", "()Lcom/vk/api/sdk/okhttp/OkHttpExecutor;", "executor", "ipakvmocc", "Ljava/lang/String;", "getApiUrl", "()Ljava/lang/String;", "apiUrl", "ipakvmocd", "getApiVersion", "apiVersion", "ipakvmoce", "Z", "getAllowVerification", "()Z", "allowVerification", "ipakvmocf", "getPersistent", "setPersistent", "(Z)V", "persistent", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebApiRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebApiRequest.kt\ncom/vk/superapp/api/internal/WebApiRequest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,227:1\n1#2:228\n1869#3,2:229\n*S KotlinDebug\n*F\n+ 1 WebApiRequest.kt\ncom/vk/superapp/api/internal/WebApiRequest\n*L\n190#1:229,2\n*E\n"})
public class WebApiRequest<T> extends VKRequest<T> {
    public static final int ERROR_IO = -1;

    /* JADX INFO: renamed from: ipakvmoca, reason: from kotlin metadata */
    @NotNull
    private final VKApiConfig config;

    /* JADX INFO: renamed from: ipakvmocb, reason: from kotlin metadata */
    @NotNull
    private final OkHttpExecutor executor;

    /* JADX INFO: renamed from: ipakvmocc, reason: from kotlin metadata */
    @NotNull
    private final String apiUrl;

    /* JADX INFO: renamed from: ipakvmocd, reason: from kotlin metadata */
    @NotNull
    private final String apiVersion;

    /* JADX INFO: renamed from: ipakvmoce, reason: from kotlin metadata */
    private final boolean allowVerification;

    /* JADX INFO: renamed from: ipakvmocf, reason: from kotlin metadata */
    private boolean persistent;

    @Nullable
    private String ipakvmocg;

    @Nullable
    private String ipakvmoch;

    @Nullable
    private UserId ipakvmoci;
    private boolean ipakvmocj;
    private boolean ipakvmock;
    private boolean ipakvmocl;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String[] ipakvmocm = {"access_token", "sig", Logger.METHOD_V, "method"};

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u000b8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/vk/superapp/api/internal/WebApiRequest$Companion;", "", "<init>", "()V", "Landroid/content/Context;", "context", "", "method", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "createIOError", "(Landroid/content/Context;Ljava/lang/String;)Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "", "ERROR_IO", "I", "", "FORBIDDEN_PARAMS", "[Ljava/lang/String;", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nWebApiRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebApiRequest.kt\ncom/vk/superapp/api/internal/WebApiRequest$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,227:1\n13472#2:228\n13473#2:231\n1869#3,2:229\n*S KotlinDebug\n*F\n+ 1 WebApiRequest.kt\ncom/vk/superapp/api/internal/WebApiRequest$Companion\n*L\n202#1:228\n202#1:231\n205#1:229,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final void access$verifyRequestValid(Companion companion, String str, Map map) {
            companion.getClass();
            for (String str2 : WebApiRequest.ipakvmocm) {
                if (map.containsKey(str2)) {
                    StringBuilder sb2 = new StringBuilder();
                    for (Map.Entry entry : map.entrySet()) {
                        sb2.append((String) entry.getKey());
                        sb2.append("=");
                        sb2.append((String) entry.getValue());
                        sb2.append(",");
                    }
                    sb2.deleteCharAt(sb2.length() - 1);
                    throw new IllegalArgumentException("You shouldn't pass " + str2 + " as a request parameter. Method: " + str + ". Params: " + ((Object) sb2));
                }
            }
        }

        @NotNull
        public final VKApiExecutionException createIOError(@NotNull Context context, @NotNull String method) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(method, "method");
            String string = context.getString(R.string.vk_common_network_error);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return new VKApiExecutionException(-1, method, true, string, null, null, null, null, 0, null, null, null, 4080, null);
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public WebApiRequest(@NotNull String method) {
        super(method, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(method, "method");
        SuperappApiCore superappApiCore = SuperappApiCore.INSTANCE;
        VKApiConfig apiConfig$api_release = superappApiCore.getApiConfig$api_release();
        this.config = apiConfig$api_release;
        this.executor = superappApiCore.getApiManager().getExecutor();
        this.apiUrl = superappApiCore.getApiUrl();
        this.apiVersion = apiConfig$api_release.getVersion();
        this.allowVerification = true;
        getParams().put("lang", apiConfig$api_release.getLang());
        getParams().put("device_id", apiConfig$api_release.getDeviceId().getValue());
    }

    public static /* synthetic */ Single toBgSingle$default(WebApiRequest webApiRequest, WebApiThreadHolder webApiThreadHolder, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toBgSingle");
        }
        if ((i10 & 1) != 0) {
            webApiThreadHolder = null;
        }
        return webApiRequest.toBgSingle(webApiThreadHolder);
    }

    public static /* synthetic */ Observable toUiObservable$default(WebApiRequest webApiRequest, WebApiThreadHolder webApiThreadHolder, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toUiObservable");
        }
        if ((i10 & 1) != 0) {
            webApiThreadHolder = null;
        }
        return webApiRequest.toUiObservable(webApiThreadHolder);
    }

    public static /* synthetic */ Single toUiSingle$default(WebApiRequest webApiRequest, WebApiThreadHolder webApiThreadHolder, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toUiSingle");
        }
        if ((i10 & 1) != 0) {
            webApiThreadHolder = null;
        }
        return webApiRequest.toUiSingle(webApiThreadHolder);
    }

    @NotNull
    public final WebApiRequest<T> allowDeprecatedPhotoFields(boolean allow) {
        this.ipakvmocj = allow;
        return this;
    }

    @Nullable
    public final T execSync() {
        try {
            return (T) ApiCommandExtKt.toObservable(this, SuperappApiCore.INSTANCE.getApiManager(), new WebApiThreadHolder(), getMethod(), getPersistent(), this).blockingFirst();
        } catch (Exception unused) {
            return null;
        }
    }

    @NotNull
    public final WebApiRequest<T> forceRemoveAccessToken(boolean remove) {
        this.ipakvmock = remove;
        return this;
    }

    public boolean getAllowVerification() {
        return this.allowVerification;
    }

    @NotNull
    public String getApiUrl() {
        return this.apiUrl;
    }

    @NotNull
    public String getApiVersion() {
        return this.apiVersion;
    }

    @NotNull
    protected final VKApiConfig getConfig() {
        return this.config;
    }

    @NotNull
    protected final OkHttpExecutor getExecutor() {
        return this.executor;
    }

    public boolean getPersistent() {
        return this.persistent;
    }

    @Override // com.vk.api.sdk.requests.VKRequest, com.vk.api.sdk.internal.ApiCommand
    @NotNull
    protected final T onExecute(@NotNull VKApiManager manager) throws InterruptedException, VKApiException, IOException {
        Intrinsics.checkNotNullParameter(manager, "manager");
        String value = manager.getConfig().getExternalDeviceId().getValue();
        if (value != null) {
            getParams().put(RegistrationStatParamsFactory.EXTERNAL_DEVICE_ID, value);
        }
        return (T) manager.execute(createBaseCallBuilder(manager.getConfig()).accessToken(this.ipakvmocg).secret(this.ipakvmoch).withAccessTokenOf(this.ipakvmoci).url(getApiUrl()).method(getMethod()).cacheControl(getCacheControl()).args(getParams()).version(getApiVersion()).setAnonymous(getIsAnonymous()).allowNoAuth(getAllowNoAuth() || getParams().get(SharedKt.PARAM_CLIENT_SECRET) != null).setEndpointPath(getEndpointPath()).setIsMultipleTokens(getIsMultipleTokens()).forceRemoveAuth(getForceRemoveAuth()).forceAnonymous(getForceAnonymous()).skipValidation(this.ipakvmocl).allowDeprecatedPhotoFields(this.ipakvmocj).build(), this);
    }

    @NotNull
    public final WebApiRequest<T> overrideAuth(@Nullable String accessToken, @Nullable String secret) {
        this.ipakvmocg = accessToken;
        this.ipakvmoch = secret;
        return this;
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull String name, @Nullable String value) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (value != null) {
            getParams().put(name, value);
        }
        return this;
    }

    public void setPersistent(boolean z10) {
        this.persistent = z10;
    }

    @NotNull
    public final WebApiRequest<T> setSuperappToken(@NotNull String token) {
        Intrinsics.checkNotNullParameter(token, "token");
        forceRemoveAccessToken(true);
        allowNoAuth();
        setAnonymous(true);
        param("super_app_token", token);
        return this;
    }

    @NotNull
    public final WebApiRequest<T> skipValidation(boolean isSkip) {
        this.ipakvmocl = isSkip;
        return this;
    }

    @JvmOverloads
    @NotNull
    public final Single<T> toBgSingle() {
        return toBgSingle$default(this, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final Observable<T> toUiObservable() {
        return toUiObservable$default(this, null, 1, null);
    }

    @JvmOverloads
    @NotNull
    public final Single<T> toUiSingle() {
        return toUiSingle$default(this, null, 1, null);
    }

    @NotNull
    public final WebPersistentRequest toWebPersistentRequest() {
        return new WebPersistentRequest(getMethod(), getParams(), null, 4, null);
    }

    @NotNull
    public final WebApiRequest<T> withAccessTokenOf(@NotNull UserId userId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        this.ipakvmoci = userId;
        return this;
    }

    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public WebApiRequest<T> allowNoAuth() {
        super.allowNoAuth();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public InternalMethodCall.Builder createBaseCallBuilder(@NotNull VKApiConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return new InternalMethodCall.Builder().forceRemoveAccessToken(this.ipakvmock);
    }

    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public WebApiRequest<T> forceAnonymous(boolean force) {
        super.forceAnonymous(force);
        return this;
    }

    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public WebApiRequest<T> forceRemoveAuth(boolean remove) {
        super.forceRemoveAuth(remove);
        return this;
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull String name, int value) {
        Intrinsics.checkNotNullParameter(name, "name");
        getParams().put(name, String.valueOf(value));
        return this;
    }

    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public WebApiRequest<T> setAnonymous(boolean allow) {
        super.setAnonymous(allow);
        return this;
    }

    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public WebApiRequest<T> setCacheControl(@Nullable String value) {
        super.setCacheControl(value);
        return this;
    }

    @Override // com.vk.api.sdk.requests.VKRequest
    @NotNull
    public WebApiRequest<T> setIsMultipleTokens(boolean isMultipleTokens) {
        super.setIsMultipleTokens(isMultipleTokens);
        return this;
    }

    @JvmOverloads
    @NotNull
    public Single<T> toBgSingle(@Nullable WebApiThreadHolder threadHolder) {
        if (getAllowVerification()) {
            Companion.access$verifyRequestValid(INSTANCE, getMethod(), getParams());
        }
        Single<T> singleSingleOrError = ApiCommandExtKt.toBgObservable(this, SuperappApiCore.INSTANCE.getApiManager(), threadHolder, getMethod(), getPersistent(), this).singleOrError();
        Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "singleOrError(...)");
        return singleSingleOrError;
    }

    @JvmOverloads
    @NotNull
    public Observable<T> toUiObservable(@Nullable WebApiThreadHolder threadHolder) {
        if (getAllowVerification()) {
            Companion.access$verifyRequestValid(INSTANCE, getMethod(), getParams());
        }
        return ApiCommandExtKt.toUiObservable(this, SuperappApiCore.INSTANCE.getApiManager(), threadHolder, getMethod(), getPersistent(), this);
    }

    @JvmOverloads
    @NotNull
    public Single<T> toUiSingle(@Nullable WebApiThreadHolder threadHolder) {
        Single<T> singleSingleOrError = toUiObservable(threadHolder).singleOrError();
        Intrinsics.checkNotNullExpressionValue(singleSingleOrError, "singleOrError(...)");
        return singleSingleOrError;
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull String name, long value) {
        Intrinsics.checkNotNullParameter(name, "name");
        getParams().put(name, String.valueOf(value));
        return this;
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull String name, @NotNull UserId value) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(value, "value");
        getParams().put(name, value.toString());
        return this;
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull CharSequence name, @NotNull Object[] values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return param(name.toString(), ArraysKt.joinToString$default(values, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull CharSequence name, @NotNull Iterable<?> values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return param(name.toString(), CollectionsKt.joinToString$default(values, ",", null, null, 0, null, null, 62, null));
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull CharSequence name, @NotNull int[] values) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(values, "values");
        return param(name.toString(), ArraysKt.joinToString$default(values, (CharSequence) ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    @NotNull
    public final WebApiRequest<T> param(@NotNull String name, boolean value) {
        Intrinsics.checkNotNullParameter(name, "name");
        getParams().put(name, value ? "1" : PaginationHelper.DEFAULT_NEXT_FROM);
        return this;
    }
}
