package ru.ok.android.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.calendar.widget.utils.Constants;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.ok.android.sdk.util.OkRequestUtil;
import ru.ok.android.sdk.util.Utils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0006\b \u0018\u00002\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\u0018\u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020\f2\u0006\u0010+\u001a\u00020,H\u0016J \u0010-\u001a\u00020\u00052\u0016\b\u0002\u0010.\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010/H\u0004J\u0010\u00100\u001a\u00020&2\u0006\u00101\u001a\u00020\u0005H\u0004J\u0012\u00102\u001a\u00020&2\b\u00103\u001a\u0004\u0018\u00010\u0005H$R \u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\u00058DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¤\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\n\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\n\"\u0004\b\u0017\u0010\u0014R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0005X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\n\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u001b\u001a\u00020\u001cX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u00020\u00058DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\nR\u0012\u0010#\u001a\u00020\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\n¨\u00065"}, d2 = {"Lru/ok/android/sdk/AbstractWidgetActivity;", "Landroid/app/Activity;", "()V", "args", "Ljava/util/HashMap;", "", "getArgs", "()Ljava/util/HashMap;", "baseUrl", "getBaseUrl", "()Ljava/lang/String;", "cancelledMessageId", "", "getCancelledMessageId", "()I", "layoutId", "getLayoutId", "mAccessToken", "getMAccessToken", "setMAccessToken", "(Ljava/lang/String;)V", "mAppId", "getMAppId", "setMAppId", "mSessionSecretKey", "getMSessionSecretKey", "setMSessionSecretKey", "retryAllowed", "", "getRetryAllowed", "()Z", "setRetryAllowed", "(Z)V", "returnUrl", "getReturnUrl", Constants.WIDGET_ID_PARAMETER, "getWidgetId", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "prepareUrl", "options", "", "processError", "error", "processResult", "result", "OkWidgetViewClient", "odnoklassniki-android-sdk_release"}, k = 1, mv = {1, 1, 15})
public abstract class AbstractWidgetActivity extends Activity {
    private HashMap _$_findViewCache;

    @Nullable
    private String mAccessToken;

    @Nullable
    private String mAppId;

    @Nullable
    private String mSessionSecretKey;

    @NotNull
    private final HashMap<String, String> args = new HashMap<>();
    private boolean retryAllowed = true;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\fH\u0016¨\u0006\u0016"}, d2 = {"Lru/ok/android/sdk/AbstractWidgetActivity$OkWidgetViewClient;", "Lru/ok/android/sdk/OkWebViewClient;", "context", "Landroid/content/Context;", "(Lru/ok/android/sdk/AbstractWidgetActivity;Landroid/content/Context;)V", "onReceivedError", "", Promotion.ACTION_VIEW, "Landroid/webkit/WebView;", "errorCode", "", "description", "", "failingUrl", "onReceivedSslError", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/webkit/SslErrorHandler;", "error", "Landroid/net/http/SslError;", "shouldOverrideUrlLoading", "", "url", "odnoklassniki-android-sdk_release"}, k = 1, mv = {1, 1, 15})
    public final class OkWidgetViewClient extends OkWebViewClient {
        final /* synthetic */ AbstractWidgetActivity this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OkWidgetViewClient(@NotNull AbstractWidgetActivity abstractWidgetActivity, Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.this$0 = abstractWidgetActivity;
        }

        @Override // ru.ok.android.sdk.OkWebViewClient, android.webkit.WebViewClient
        public void onReceivedError(@NotNull WebView view, int errorCode, @NotNull String description, @NotNull String failingUrl) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(description, "description");
            Intrinsics.checkParameterIsNotNull(failingUrl, "failingUrl");
            super.onReceivedError(view, errorCode, description, failingUrl);
            this.this$0.processError(getErrorMessage(errorCode));
        }

        @Override // ru.ok.android.sdk.OkWebViewClient, android.webkit.WebViewClient
        public void onReceivedSslError(@NotNull WebView view, @NotNull SslErrorHandler handler, @NotNull SslError error) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(handler, "handler");
            Intrinsics.checkParameterIsNotNull(error, "error");
            super.onReceivedSslError(view, handler, error);
            this.this$0.processError(getErrorMessage(error));
        }

        @Override // ru.ok.android.sdk.OkWebViewClient, android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(@NotNull WebView view, @NotNull String url) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            Intrinsics.checkParameterIsNotNull(url, "url");
            if (!StringsKt.startsWith$default(url, this.this$0.getReturnUrl(), false, 2, (Object) null)) {
                return super.shouldOverrideUrlLoading(view, url);
            }
            this.this$0.processResult(OkRequestUtil.getUrlParameters(url).getString("result"));
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* synthetic */ String prepareUrl$default(AbstractWidgetActivity abstractWidgetActivity, Map map, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: prepareUrl");
        }
        if ((i10 & 1) != 0) {
            map = null;
        }
        return abstractWidgetActivity.prepareUrl(map);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    public View _$_findCachedViewById(int i10) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i10));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i10);
        this._$_findViewCache.put(Integer.valueOf(i10), viewFindViewById);
        return viewFindViewById;
    }

    @NotNull
    protected final HashMap<String, String> getArgs() {
        return this.args;
    }

    @NotNull
    protected final String getBaseUrl() {
        return "https://connect.ok.ru/dk?st.cmd=" + getWidgetId() + "&st.access_token=" + this.mAccessToken + "&st.app=" + this.mAppId + "&st.return=" + getReturnUrl();
    }

    protected abstract int getCancelledMessageId();

    protected int getLayoutId() {
        return R.layout.oksdk_webview_activity;
    }

    @Nullable
    protected final String getMAccessToken() {
        return this.mAccessToken;
    }

    @Nullable
    protected final String getMAppId() {
        return this.mAppId;
    }

    @Nullable
    protected final String getMSessionSecretKey() {
        return this.mSessionSecretKey;
    }

    protected final boolean getRetryAllowed() {
        return this.retryAllowed;
    }

    @NotNull
    protected final String getReturnUrl() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append("okwidget://");
        String widgetId = getWidgetId();
        if (widgetId == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = widgetId.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        sb2.append(lowerCase);
        return sb2.toString();
    }

    @NotNull
    protected abstract String getWidgetId();

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.args.clear();
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.mAppId = extras.getString("appId");
            this.mAccessToken = extras.getString("access_token");
            this.mSessionSecretKey = extras.getString("session_secret_key");
            if (extras.containsKey(SharedKt.PARAM_WIDGET_ARGS)) {
                Serializable serializable = extras.getSerializable(SharedKt.PARAM_WIDGET_ARGS);
                if (!(serializable instanceof HashMap)) {
                    serializable = null;
                }
                HashMap map = (HashMap) serializable;
                if (map != null) {
                    this.args.putAll(map);
                }
            }
            if (extras.containsKey(SharedKt.PARAM_WIDGET_RETRY_ALLOWED)) {
                this.retryAllowed = extras.getBoolean(SharedKt.PARAM_WIDGET_RETRY_ALLOWED, true);
            }
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, @NotNull KeyEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (4 != keyCode) {
            return false;
        }
        String string = getString(getCancelledMessageId());
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(cancelledMessageId)");
        processError(string);
        return true;
    }

    @NotNull
    protected final String prepareUrl(@Nullable Map<String, String> options) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry<String, String> entry : this.args.entrySet()) {
            treeMap.put(entry.getKey(), entry.getValue());
        }
        treeMap.put("st.return", getReturnUrl());
        StringBuilder sb2 = new StringBuilder(200);
        StringBuilder sb3 = new StringBuilder(getBaseUrl());
        for (Map.Entry entry2 : treeMap.entrySet()) {
            String str = (String) entry2.getKey();
            String str2 = (String) entry2.getValue();
            if (ArraysKt.contains(SharedKt.getWIDGET_SIGNED_ARGS(), str)) {
                sb2.append(str);
                sb2.append('=');
                sb2.append(str2);
            }
            if (!Intrinsics.areEqual(str, "st.return")) {
                sb3.append(Typography.amp);
                sb3.append(str);
                sb3.append('=');
                sb3.append(OkRequestUtil.encode(str2));
            }
        }
        String md5 = Utils.INSTANCE.toMD5(sb2.toString() + this.mSessionSecretKey);
        if (options == null) {
            options = AbstractWidgetActivityKt.DEFAULT_OPTIONS;
        }
        for (Map.Entry<String, String> entry3 : options.entrySet()) {
            String key = entry3.getKey();
            String value = entry3.getValue();
            sb3.append(Typography.amp);
            sb3.append(key);
            sb3.append('=');
            sb3.append(value);
        }
        sb3.append("&st.signature=");
        sb3.append(md5);
        String string = sb3.toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "url.toString()");
        return string;
    }

    protected final void processError(@NotNull final String error) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        if (!this.retryAllowed) {
            processResult(error);
            return;
        }
        try {
            new AlertDialog.Builder(this).setMessage(error).setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() { // from class: ru.ok.android.sdk.AbstractWidgetActivity.processError.1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    ((WebView) AbstractWidgetActivity.this.findViewById(R.id.web_view)).loadUrl(AbstractWidgetActivity.prepareUrl$default(AbstractWidgetActivity.this, null, 1, null));
                }
            }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: ru.ok.android.sdk.AbstractWidgetActivity.processError.2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    AbstractWidgetActivity.this.processResult(error);
                }
            }).show();
        } catch (RuntimeException unused) {
            processResult(error);
        }
    }

    protected abstract void processResult(@Nullable String result);

    protected final void setMAccessToken(@Nullable String str) {
        this.mAccessToken = str;
    }

    protected final void setMAppId(@Nullable String str) {
        this.mAppId = str;
    }

    protected final void setMSessionSecretKey(@Nullable String str) {
        this.mSessionSecretKey = str;
    }

    protected final void setRetryAllowed(boolean z10) {
        this.retryAllowed = z10;
    }
}
