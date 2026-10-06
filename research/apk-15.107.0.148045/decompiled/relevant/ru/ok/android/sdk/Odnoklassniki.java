package ru.ok.android.sdk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.support.api.entity.core.CommonCode;
import java.io.IOException;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.ok.android.sdk.util.OkAuthType;
import ru.ok.android.sdk.util.OkPayment;
import ru.ok.android.sdk.util.OkRequestUtil;
import ru.ok.android.sdk.util.Utils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\b\u0016\u0018\u0000 a2\u00020\u0001:\u0001aB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u000e\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u0006\u0010'\u001a\u00020$J\u000e\u0010(\u001a\u00020\t2\u0006\u0010)\u001a\u00020*J\u000e\u0010+\u001a\u00020\t2\u0006\u0010)\u001a\u00020*J\u000e\u0010,\u001a\u00020\t2\u0006\u0010)\u001a\u00020*J\u000e\u0010-\u001a\u00020\t2\u0006\u0010)\u001a\u00020*J\u000e\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020*J\u001a\u00100\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u00101\u001a\u0004\u0018\u00010\u0005J\u0018\u00102\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u00103\u001a\u000204J*\u00105\u001a\u00020\t2\u0006\u0010/\u001a\u00020*2\u0006\u00106\u001a\u00020*2\n\b\u0001\u00107\u001a\u0004\u0018\u0001082\u0006\u0010%\u001a\u00020&J*\u00109\u001a\u00020\t2\u0006\u0010/\u001a\u00020*2\u0006\u00106\u001a\u00020*2\n\b\u0001\u00107\u001a\u0004\u0018\u0001082\u0006\u0010%\u001a\u00020&J\b\u0010:\u001a\u00020$H\u0002J&\u0010;\u001a\u00020$2\u0006\u0010<\u001a\u00020=2\u0016\b\u0002\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010?J&\u0010@\u001a\u00020$2\u0006\u0010<\u001a\u00020=2\u0016\b\u0002\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010?J>\u0010A\u001a\u00020$2\u0006\u0010<\u001a\u00020=2\u000e\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020D0C2\u0014\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010?2\u0006\u0010)\u001a\u00020*H\u0002J6\u0010E\u001a\u00020$2\u0006\u0010<\u001a\u00020=2\u0006\u0010F\u001a\u00020\u00052\u0006\u0010G\u001a\u00020\t2\u0016\b\u0002\u0010>\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010?J\u001e\u0010H\u001a\u00020$2\u0006\u0010I\u001a\u00020\u00052\u0006\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020LJ8\u0010/\u001a\u0004\u0018\u00010\u00052\u0006\u0010M\u001a\u00020\u00052\u0016\b\u0002\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010O2\u000e\b\u0002\u0010P\u001a\b\u0012\u0004\u0012\u00020R0QJ>\u0010/\u001a\u00020\t2\u0006\u0010M\u001a\u00020\u00052\u0016\b\u0002\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010O2\u000e\b\u0002\u0010P\u001a\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010%\u001a\u00020&JP\u0010S\u001a\u0014\u0012\u0004\u0012\u00020U\u0012\u0004\u0012\u00020U\u0012\u0004\u0012\u00020U0T2\u0006\u0010M\u001a\u00020\u00052\u0016\b\u0002\u0010N\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010O2\u000e\b\u0002\u0010P\u001a\b\u0012\u0004\u0012\u00020R0Q2\u0006\u0010%\u001a\u00020&J9\u0010V\u001a\u00020$2\u0006\u0010<\u001a\u00020=2\b\b\u0001\u0010W\u001a\u00020\u00052\u0006\u0010X\u001a\u00020Y2\u0012\u0010Z\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050[\"\u00020\u0005¢\u0006\u0002\u0010\\J\u001c\u0010]\u001a\u00020$2\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050^H\u0002J\u001a\u0010_\u001a\u0002042\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010`\u001a\u0004\u0018\u00010\u0005H\u0002R\u0014\u0010\b\u001a\u00020\tX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000b\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0012\"\u0004\b\u001b\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0012\"\u0004\b\"\u0010\u0018¨\u0006b"}, d2 = {"Lru/ok/android/sdk/Odnoklassniki;", "", "context", "Landroid/content/Context;", "id", "", "key", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V", "allowDebugOkSso", "", "getAllowDebugOkSso", "()Z", "allowWidgetRetry", "getAllowWidgetRetry", "setAllowWidgetRetry", "(Z)V", "appId", "getAppId", "()Ljava/lang/String;", "appKey", "getAppKey", "mAccessToken", "getMAccessToken", "setMAccessToken", "(Ljava/lang/String;)V", "mSessionSecretKey", "getMSessionSecretKey", "setMSessionSecretKey", "okPayment", "Lru/ok/android/sdk/util/OkPayment;", "getOkPayment", "()Lru/ok/android/sdk/util/OkPayment;", "sdkToken", "getSdkToken", "setSdkToken", "checkValidTokens", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/ok/android/sdk/OkListener;", "clearTokens", "isActivityRequestInvite", "requestCode", "", "isActivityRequestOAuth", "isActivityRequestPost", "isActivityRequestSuggest", "isActivityRequestViral", Event.Companion.Network.Fail.REQUEST_TAG, "notifyFailed", "error", "notifySuccess", "json", "Lorg/json/JSONObject;", "onActivityResultResult", "result", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "onAuthActivityResult", "onValidSessionAppeared", "performAppInvite", "activity", "Landroid/app/Activity;", "args", "Ljava/util/HashMap;", "performAppSuggest", "performAppSuggestInvite", "clazz", "Ljava/lang/Class;", "Lru/ok/android/sdk/AbstractWidgetActivity;", "performPosting", "attachment", "userTextEnabled", "reportPayment", "trxId", "amount", FirebaseAnalytics.Param.CURRENCY, "Ljava/util/Currency;", "method", "params", "", "mode", "", "Lru/ok/android/sdk/OkRequestMode;", "requestAsync", "Landroid/os/AsyncTask;", "Ljava/lang/Void;", "requestAuthorization", "redirectUri", "authType", "Lru/ok/android/sdk/util/OkAuthType;", SharedKt.PARAM_SCOPES, "", "(Landroid/app/Activity;Ljava/lang/String;Lru/ok/android/sdk/util/OkAuthType;[Ljava/lang/String;)V", "signParameters", "", "toJson", "value", "Companion", "odnoklassniki-android-sdk_release"}, k = 1, mv = {1, 1, 15})
public class Odnoklassniki {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @SuppressLint({"StaticFieldLeak"})
    @Nullable
    private static volatile Odnoklassniki sOdnoklassniki;
    private final boolean allowDebugOkSso;
    private boolean allowWidgetRetry;

    @NotNull
    private final String appId;

    @NotNull
    private final String appKey;
    private final Context context;

    @Nullable
    private String mAccessToken;

    @Nullable
    private String mSessionSecretKey;

    @NotNull
    private final OkPayment okPayment;

    @Nullable
    private String sdkToken;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0007J\b\u0010\u0013\u001a\u00020\u0014H\u0007J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u001a\u0010\u0003\u001a\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R&\u0010\b\u001a\u0004\u0018\u00010\u00048\u0004@\u0004X\u0085\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lru/ok/android/sdk/Odnoklassniki$Companion;", "", "()V", "instance", "Lru/ok/android/sdk/Odnoklassniki;", "instance$annotations", "getInstance", "()Lru/ok/android/sdk/Odnoklassniki;", "sOdnoklassniki", "sOdnoklassniki$annotations", "getSOdnoklassniki", "setSOdnoklassniki", "(Lru/ok/android/sdk/Odnoklassniki;)V", "createInstance", "context", "Landroid/content/Context;", "appId", "", "appKey", "hasInstance", "", "of", "odnoklassniki-android-sdk_release"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        @NotNull
        public final Odnoklassniki createInstance(@NotNull Context context, @NotNull String appId, @NotNull String appKey) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(appId, "appId");
            Intrinsics.checkParameterIsNotNull(appKey, "appKey");
            if (StringsKt.isBlank(appId) || StringsKt.isBlank(appKey)) {
                throw new IllegalArgumentException(context.getString(R.string.no_application_data));
            }
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
            return new Odnoklassniki(applicationContext, appId, appKey);
        }

        @NotNull
        public final Odnoklassniki getInstance() {
            Companion companion = Odnoklassniki.INSTANCE;
            if (companion.getSOdnoklassniki() == null) {
                throw new IllegalStateException("No instance available. Odnoklassniki.createInstance() needs to be called before Odnoklassniki.of()");
            }
            Odnoklassniki sOdnoklassniki = companion.getSOdnoklassniki();
            if (sOdnoklassniki == null) {
                Intrinsics.throwNpe();
            }
            return sOdnoklassniki;
        }

        @Nullable
        protected final Odnoklassniki getSOdnoklassniki() {
            return Odnoklassniki.sOdnoklassniki;
        }

        @JvmStatic
        public final boolean hasInstance() {
            return getSOdnoklassniki() != null;
        }

        @NotNull
        public final Odnoklassniki of(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Odnoklassniki sOdnoklassniki = getSOdnoklassniki();
            if (sOdnoklassniki != null) {
                return sOdnoklassniki;
            }
            return new Odnoklassniki(context, null, null, 6, null);
        }

        protected final void setSOdnoklassniki(@Nullable Odnoklassniki odnoklassniki) {
            Odnoklassniki.sOdnoklassniki = odnoklassniki;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "Use of(context) for safe access")
        @JvmStatic
        public static /* synthetic */ void instance$annotations() {
        }

        @JvmStatic
        protected static /* synthetic */ void sOdnoklassniki$annotations() {
        }
    }

    public Odnoklassniki(@NotNull Context context, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.allowWidgetRetry = true;
        if (str == null || str2 == null) {
            Pair<String, String> appInfo = TokenStore.INSTANCE.getAppInfo(context);
            String strComponent1 = appInfo.component1();
            String strComponent2 = appInfo.component2();
            if (strComponent1 == null || strComponent2 == null) {
                throw new IllegalStateException("No instance available. Odnoklassniki.createInstance() needs to be called");
            }
            this.appId = strComponent1;
            this.appKey = strComponent2;
        } else {
            this.appId = str;
            this.appKey = str2;
            TokenStore.INSTANCE.setAppInfo(context, str, str2);
        }
        this.mAccessToken = TokenStore.getStoredAccessToken(context);
        this.mSessionSecretKey = TokenStore.getStoredSessionSecretKey(context);
        this.sdkToken = TokenStore.getSdkToken(context);
        this.okPayment = new OkPayment(context);
        sOdnoklassniki = this;
    }

    @JvmStatic
    @NotNull
    public static final Odnoklassniki createInstance(@NotNull Context context, @NotNull String str, @NotNull String str2) {
        return INSTANCE.createInstance(context, str, str2);
    }

    @NotNull
    public static final Odnoklassniki getInstance() {
        return INSTANCE.getInstance();
    }

    @Nullable
    protected static final Odnoklassniki getSOdnoklassniki() {
        return sOdnoklassniki;
    }

    @JvmStatic
    public static final boolean hasInstance() {
        return INSTANCE.hasInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onValidSessionAppeared() {
        this.okPayment.init();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void performAppInvite$default(Odnoklassniki odnoklassniki, Activity activity, HashMap map, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: performAppInvite");
        }
        if ((i10 & 2) != 0) {
            map = null;
        }
        odnoklassniki.performAppInvite(activity, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void performAppSuggest$default(Odnoklassniki odnoklassniki, Activity activity, HashMap map, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: performAppSuggest");
        }
        if ((i10 & 2) != 0) {
            map = null;
        }
        odnoklassniki.performAppSuggest(activity, map);
    }

    private final void performAppSuggestInvite(Activity activity, Class<? extends AbstractWidgetActivity> clazz, HashMap<String, String> args, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtra("appId", this.appId);
        intent.putExtra("access_token", this.mAccessToken);
        intent.putExtra(SharedKt.PARAM_WIDGET_RETRY_ALLOWED, this.allowWidgetRetry);
        intent.putExtra("session_secret_key", this.mSessionSecretKey);
        intent.putExtra(SharedKt.PARAM_WIDGET_ARGS, args);
        activity.startActivityForResult(intent, requestCode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void performPosting$default(Odnoklassniki odnoklassniki, Activity activity, String str, boolean z10, HashMap map, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: performPosting");
        }
        if ((i10 & 8) != 0) {
            map = null;
        }
        odnoklassniki.performPosting(activity, str, z10, map);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public static /* synthetic */ String request$default(Odnoklassniki odnoklassniki, String str, Map map, Set set, int i10, Object obj) throws IOException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
        }
        if ((i10 & 2) != 0) {
            map = null;
        }
        if ((i10 & 4) != 0) {
            set = OkRequestMode.INSTANCE.getDEFAULT();
        }
        return odnoklassniki.request(str, map, set);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ AsyncTask requestAsync$default(Odnoklassniki odnoklassniki, String str, Map map, Set set, OkListener okListener, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestAsync");
        }
        if ((i10 & 2) != 0) {
            map = null;
        }
        if ((i10 & 4) != 0) {
            set = OkRequestMode.INSTANCE.getDEFAULT();
        }
        return odnoklassniki.requestAsync(str, map, set, okListener);
    }

    protected static final void setSOdnoklassniki(@Nullable Odnoklassniki odnoklassniki) {
        sOdnoklassniki = odnoklassniki;
    }

    private final void signParameters(Map<String, String> params) {
        StringBuilder sb2 = new StringBuilder(100);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb2.append(entry.getKey());
            sb2.append('=');
            sb2.append(entry.getValue());
        }
        String string = sb2.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "sb.toString()");
        params.put("sig", Utils.INSTANCE.toMD5(string + this.mSessionSecretKey));
    }

    private final JSONObject toJson(String key, String value) {
        try {
            JSONObject jSONObjectPut = new JSONObject().put(key, value);
            Intrinsics.checkExpressionValueIsNotNull(jSONObjectPut, "JSONObject().put(key, value)");
            return jSONObjectPut;
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    public final void checkValidTokens(@NotNull final OkListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (this.mAccessToken == null || this.mSessionSecretKey == null) {
            notifyFailed(listener, this.context.getString(R.string.no_valid_token));
        } else {
            new Thread(new Runnable() { // from class: ru.ok.android.sdk.Odnoklassniki.checkValidTokens.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        String strRequest$default = Odnoklassniki.request$default(Odnoklassniki.this, "users.getLoggedInUser", null, null, 6, null);
                        if (strRequest$default != null && strRequest$default.length() > 2) {
                            String strSubstring = strRequest$default.substring(1, strRequest$default.length() - 1);
                            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            if (TextUtils.isDigitsOnly(strSubstring)) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("access_token", Odnoklassniki.this.getMAccessToken());
                                    jSONObject.put("session_secret_key", Odnoklassniki.this.getMSessionSecretKey());
                                    jSONObject.put(SharedKt.PARAM_LOGGED_IN_USER, strRequest$default);
                                } catch (JSONException unused) {
                                }
                                Odnoklassniki.this.onValidSessionAppeared();
                                Odnoklassniki.this.notifySuccess(listener, jSONObject);
                                return;
                            }
                        }
                        try {
                            JSONObject jSONObject2 = new JSONObject(strRequest$default);
                            if (jSONObject2.has("error_msg")) {
                                Odnoklassniki.this.notifyFailed(listener, jSONObject2.getString("error_msg"));
                                return;
                            }
                        } catch (JSONException unused2) {
                        }
                        Odnoklassniki.this.notifyFailed(listener, strRequest$default);
                    } catch (IOException e10) {
                        Odnoklassniki.this.notifyFailed(listener, e10.getMessage());
                    }
                }
            }).start();
        }
    }

    public final void clearTokens() {
        this.mAccessToken = null;
        this.mSessionSecretKey = null;
        this.sdkToken = null;
        TokenStore.removeStoredTokens(this.context);
        CookieManager.getInstance().removeAllCookies(null);
    }

    public boolean getAllowDebugOkSso() {
        return this.allowDebugOkSso;
    }

    public final boolean getAllowWidgetRetry() {
        return this.allowWidgetRetry;
    }

    @NotNull
    protected final String getAppId() {
        return this.appId;
    }

    @NotNull
    protected final String getAppKey() {
        return this.appKey;
    }

    @Nullable
    public final String getMAccessToken() {
        return this.mAccessToken;
    }

    @Nullable
    public final String getMSessionSecretKey() {
        return this.mSessionSecretKey;
    }

    @NotNull
    protected final OkPayment getOkPayment() {
        return this.okPayment;
    }

    @Nullable
    public final String getSdkToken() {
        return this.sdkToken;
    }

    public final boolean isActivityRequestInvite(int requestCode) {
        return requestCode == 22892;
    }

    public final boolean isActivityRequestOAuth(int requestCode) {
        return requestCode == 22890;
    }

    public final boolean isActivityRequestPost(int requestCode) {
        return requestCode == 22891;
    }

    public final boolean isActivityRequestSuggest(int requestCode) {
        return requestCode == 22893;
    }

    public final boolean isActivityRequestViral(int request) {
        return isActivityRequestPost(request) || isActivityRequestInvite(request) || isActivityRequestSuggest(request);
    }

    public final void notifyFailed(@Nullable final OkListener listener, @Nullable final String error) {
        if (listener != null) {
            Utils.INSTANCE.executeOnMain(new Runnable() { // from class: ru.ok.android.sdk.Odnoklassniki.notifyFailed.1
                @Override // java.lang.Runnable
                public final void run() {
                    listener.onError(error);
                }
            });
        }
    }

    public final void notifySuccess(@Nullable final OkListener listener, @NotNull final JSONObject json) {
        Intrinsics.checkParameterIsNotNull(json, "json");
        if (listener != null) {
            Utils.INSTANCE.executeOnMain(new Runnable() { // from class: ru.ok.android.sdk.Odnoklassniki.notifySuccess.1
                @Override // java.lang.Runnable
                public final void run() {
                    listener.onSuccess(json);
                }
            });
        }
    }

    public final boolean onActivityResultResult(int request, int result, @androidx.annotation.Nullable @Nullable Intent intent, @NotNull OkListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (!isActivityRequestViral(request)) {
            return false;
        }
        if (intent == null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SharedKt.PARAM_ACTIVITY_RESULT, result);
            } catch (JSONException unused) {
            }
            listener.onError(jSONObject.toString());
            return true;
        }
        if (intent.hasExtra("error")) {
            listener.onError(intent.getStringExtra("error"));
            return true;
        }
        try {
            listener.onSuccess(new JSONObject(intent.getStringExtra("result")));
            return true;
        } catch (JSONException unused2) {
            listener.onError(intent.getStringExtra("result"));
            return true;
        }
    }

    public final boolean onAuthActivityResult(int request, int result, @androidx.annotation.Nullable @Nullable Intent intent, @NotNull OkListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        if (!isActivityRequestOAuth(request)) {
            return false;
        }
        if (intent == null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(SharedKt.PARAM_ACTIVITY_RESULT, result);
            } catch (JSONException unused) {
            }
            listener.onError(jSONObject.toString());
            return true;
        }
        String stringExtra = intent.getStringExtra("access_token");
        if (stringExtra == null) {
            String stringExtra2 = intent.getStringExtra("error");
            if (result == 3 && (listener instanceof OkAuthListener)) {
                ((OkAuthListener) listener).onCancel(stringExtra2);
                return true;
            }
            listener.onError(stringExtra2);
            return true;
        }
        String stringExtra3 = intent.getStringExtra("session_secret_key");
        String stringExtra4 = intent.getStringExtra("refresh_token");
        long longExtra = intent.getLongExtra("expires_in", 0L);
        this.mAccessToken = stringExtra;
        if (stringExtra3 == null) {
            stringExtra3 = stringExtra4;
        }
        this.mSessionSecretKey = stringExtra3;
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("access_token", this.mAccessToken);
            jSONObject2.put("session_secret_key", this.mSessionSecretKey);
            if (longExtra > 0) {
                jSONObject2.put("expires_in", longExtra);
            }
        } catch (JSONException unused2) {
        }
        onValidSessionAppeared();
        listener.onSuccess(jSONObject2);
        return true;
    }

    public final void performAppInvite(@NotNull Activity activity, @Nullable HashMap<String, String> args) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        performAppSuggestInvite(activity, OkAppInviteActivity.class, args, SharedKt.OK_INVITING_REQUEST_CODE);
    }

    public final void performAppSuggest(@NotNull Activity activity, @Nullable HashMap<String, String> args) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        performAppSuggestInvite(activity, OkAppSuggestActivity.class, args, SharedKt.OK_SUGGESTING_REQUEST_CODE);
    }

    public final void performPosting(@NotNull Activity activity, @NotNull String attachment, boolean userTextEnabled, @Nullable HashMap<String, String> args) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(attachment, "attachment");
        Intent intent = new Intent(activity, (Class<?>) OkPostingActivity.class);
        intent.putExtra("appId", this.appId);
        intent.putExtra("attachment", attachment);
        intent.putExtra("access_token", this.mAccessToken);
        intent.putExtra(SharedKt.PARAM_WIDGET_ARGS, args);
        intent.putExtra(SharedKt.PARAM_WIDGET_RETRY_ALLOWED, this.allowWidgetRetry);
        intent.putExtra("session_secret_key", this.mSessionSecretKey);
        intent.putExtra(SharedKt.PARAM_USER_TEXT_ENABLE, userTextEnabled);
        activity.startActivityForResult(intent, SharedKt.OK_POSTING_REQUEST_CODE);
    }

    public final void reportPayment(@NotNull String trxId, @NotNull String amount, @NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(trxId, "trxId");
        Intrinsics.checkParameterIsNotNull(amount, "amount");
        Intrinsics.checkParameterIsNotNull(currency, "currency");
        this.okPayment.report(trxId, amount, currency);
    }

    @Nullable
    public final String request(@NotNull String method, @Nullable Map<String, String> params, @NotNull Set<? extends OkRequestMode> mode) throws IOException {
        String str;
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        if (TextUtils.isEmpty(method)) {
            throw new IllegalArgumentException(this.context.getString(R.string.api_method_cant_be_empty));
        }
        TreeMap treeMap = new TreeMap();
        if (params != null && !params.isEmpty()) {
            treeMap.putAll(params);
        }
        treeMap.put("application_key", this.appKey);
        treeMap.put("method", method);
        if (!mode.contains(OkRequestMode.NO_PLATFORM_REPORTING)) {
            treeMap.put("platform", SharedKt.APP_PLATFORM);
        }
        if (mode.contains(OkRequestMode.SDK_SESSION)) {
            String str2 = this.sdkToken;
            if (str2 == null) {
                throw new IllegalArgumentException("SDK token is required for method call, have not forget to call sdkInit?");
            }
            treeMap.put("sdkToken", str2);
        }
        if (mode.contains(OkRequestMode.SIGNED) && (str = this.mAccessToken) != null && str.length() != 0) {
            signParameters(treeMap);
            String str3 = this.mAccessToken;
            if (str3 == null) {
                Intrinsics.throwNpe();
            }
            treeMap.put("access_token", str3);
        }
        return OkRequestUtil.executeRequest(treeMap);
    }

    @NotNull
    public final AsyncTask<Void, Void, Void> requestAsync(@NotNull final String method, @Nullable final Map<String, String> params, @NotNull final Set<? extends OkRequestMode> mode, @NotNull final OkListener listener) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AsyncTask<Void, Void, Void> asyncTaskExecute = new AsyncTask<Void, Void, Void>() { // from class: ru.ok.android.sdk.Odnoklassniki$requestAsync$task$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            @Nullable
            public Void doInBackground(@NotNull Void... parameters) {
                Intrinsics.checkParameterIsNotNull(parameters, "parameters");
                this.this$0.request(method, params, mode, listener);
                return null;
            }
        }.execute(new Void[0]);
        Intrinsics.checkExpressionValueIsNotNull(asyncTaskExecute, "task.execute()");
        return asyncTaskExecute;
    }

    public final void requestAuthorization(@NotNull Activity activity, @androidx.annotation.Nullable @NotNull String redirectUri, @NotNull OkAuthType authType, @NotNull String... scopes) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(redirectUri, "redirectUri");
        Intrinsics.checkParameterIsNotNull(authType, "authType");
        Intrinsics.checkParameterIsNotNull(scopes, "scopes");
        Intent intent = new Intent(activity, (Class<?>) OkAuthActivity.class);
        intent.putExtra("client_id", this.appId);
        intent.putExtra("application_key", this.appKey);
        intent.putExtra("redirect_uri", redirectUri);
        intent.putExtra(SharedKt.PARAM_AUTH_TYPE, authType);
        intent.putExtra(SharedKt.PARAM_SCOPES, scopes);
        activity.startActivityForResult(intent, SharedKt.OK_AUTH_REQUEST_CODE);
    }

    public final void setAllowWidgetRetry(boolean z10) {
        this.allowWidgetRetry = z10;
    }

    public final void setMAccessToken(@Nullable String str) {
        this.mAccessToken = str;
    }

    public final void setMSessionSecretKey(@Nullable String str) {
        this.mSessionSecretKey = str;
    }

    public final void setSdkToken(@Nullable String str) {
        this.sdkToken = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ boolean request$default(Odnoklassniki odnoklassniki, String str, Map map, Set set, OkListener okListener, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
        }
        if ((i10 & 2) != 0) {
            map = null;
        }
        if ((i10 & 4) != 0) {
            set = OkRequestMode.INSTANCE.getDEFAULT();
        }
        return odnoklassniki.request(str, map, set, okListener);
    }

    public /* synthetic */ Odnoklassniki(Context context, String str, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i10 & 2) != 0 ? null : str, (i10 & 4) != 0 ? null : str2);
    }

    public final boolean request(@NotNull String method, @Nullable Map<String, String> params, @NotNull Set<? extends OkRequestMode> mode, @NotNull OkListener listener) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        try {
            String strRequest = request(method, params, mode);
            try {
                JSONObject jSONObject = new JSONObject(strRequest);
                if (jSONObject.has("error_msg")) {
                    notifyFailed(listener, jSONObject.optString("error_msg"));
                    return false;
                }
                notifySuccess(listener, jSONObject);
                return true;
            } catch (JSONException unused) {
                notifySuccess(listener, toJson("result", strRequest));
                return true;
            }
        } catch (IOException e10) {
            notifyFailed(listener, toJson(OkListenerKt.KEY_EXCEPTION, e10.getMessage()).toString());
            return false;
        }
    }
}
