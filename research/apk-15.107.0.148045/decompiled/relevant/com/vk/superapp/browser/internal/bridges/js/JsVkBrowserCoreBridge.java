package com.vk.superapp.browser.internal.bridges.js;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebView;
import androidx.annotation.MainThread;
import androidx.fragment.app.FragmentActivity;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.vk.api.sdk.VKHost;
import com.vk.auth.api.models.AuthResult;
import com.vk.auth.internal.AuthLibBridge;
import com.vk.auth.main.AuthCallback;
import com.vk.auth.main.AuthLib;
import com.vk.auth.main.SignUpData;
import com.vk.auth.oauth.VkOAuthConnectionResult;
import com.vk.auth.oauth.VkOAuthService;
import com.vk.auth.oauth.di.OAuthComponent;
import com.vk.auth.oauth.di.VerificationOAuthManager;
import com.vk.auth.oauth.model.AdditionalOauthAuthResult;
import com.vk.auth.oauth.secure.DefaultSecureDataGenerator;
import com.vk.auth.validation.VkPhoneValidationCompleteResult;
import com.vk.auth.validation.VkPhoneValidationErrorReason;
import com.vk.auth.validation.VkPhoneValidationManager;
import com.vk.auth.validation.VkValidatePhoneInfo;
import com.vk.core.extensions.JsonObjectExtKt;
import com.vk.core.extensions.KotlinStringExtKt;
import com.vk.core.extensions.UriExtKt;
import com.vk.core.util.OsUtil;
import com.vk.di.context.DiContextKt;
import com.vk.di.internal.context.RootTypedDiContext;
import com.vk.external.miniapp.net.app.WebImage;
import com.vk.navigation.ActivityResulter;
import com.vk.navigation.ResulterProvider;
import com.vk.passkey.PasskeySignUpDelegateImpl;
import com.vk.permission.PermissionHelper;
import com.vk.superapp.analytics.di.SakAnalyticsComponent;
import com.vk.superapp.api.contract.SuperappApi;
import com.vk.superapp.api.core.SuperappApiCore;
import com.vk.superapp.api.dto.app.ResolvingResult;
import com.vk.superapp.api.dto.auth.PasskeyBeginResult;
import com.vk.superapp.api.dto.auth.VkAuthCredentials;
import com.vk.superapp.api.dto.auth.validatephonecheck.AuthValidatePhoneCheckResponse;
import com.vk.superapp.auth.js.bridge.api.JsAuthDelegate;
import com.vk.superapp.auth.js.bridge.api.JsMultiaccountDelegate;
import com.vk.superapp.auth.js.bridge.api.di.JsAuthDelegateComponent;
import com.vk.superapp.base.js.bridge.RefreshablePresenterDelegateKt;
import com.vk.superapp.base.js.bridge.data.VkUiCloseData;
import com.vk.superapp.base.js.bridge.delegate.LocalTokenDelegate;
import com.vk.superapp.base.js.bridge.delegate.LocalTokenDelegateStub;
import com.vk.superapp.bridges.LogoutReason;
import com.vk.superapp.bridges.SuperappBridgesKt;
import com.vk.superapp.bridges.SuperappUiRouterBridge;
import com.vk.superapp.bridges.dto.AuthData;
import com.vk.superapp.bridges.dto.VkAlertData;
import com.vk.superapp.bridges.features.SuperappBrowserFeaturesBridge;
import com.vk.superapp.bridges.features.SuperappFeature;
import com.vk.superapp.bridges.js.JsBrowserBridge;
import com.vk.superapp.browser.R;
import com.vk.superapp.browser.internal.bridges.BaseWebBridge;
import com.vk.superapp.browser.internal.bridges.BridgeUtils;
import com.vk.superapp.browser.internal.bridges.JsApiMethodType;
import com.vk.superapp.browser.internal.bridges.MethodScope;
import com.vk.superapp.browser.internal.bridges.WebAppBridge;
import com.vk.superapp.browser.internal.bridges.js.features.FullScreenLoaderDelegate;
import com.vk.superapp.browser.internal.bridges.js.features.JsMultiaccountDelegateSdkImpl;
import com.vk.superapp.browser.internal.bridges.js.features.JsNativePaymentsDelegate;
import com.vk.superapp.browser.internal.bridges.js.features.JsOpenExternalLinkRepository;
import com.vk.superapp.browser.internal.bridges.js.features.JsShowActionMenuDelegate;
import com.vk.superapp.browser.internal.bridges.js.features.JsVkPayCheckoutDelegate;
import com.vk.superapp.browser.internal.delegates.VkUiBrowserPresenter;
import com.vk.superapp.browser.internal.delegates.VkUiBrowserView;
import com.vk.superapp.browser.internal.utils.WebAppAutoDisposableKt;
import com.vk.superapp.browser.internal.utils.WebAppUtils;
import com.vk.superapp.browser.internal.utils.WebClients;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import com.vk.superapp.browser.ui.callback.OnWebCallback;
import com.vk.superapp.browser.utils.JsVkBrowserCoreBridgeExtKt;
import com.vk.superapp.browser.utils.SuperAppDownloadUtils;
import com.vk.superapp.browser.utils.VkUiRxStoryBoxEvent;
import com.vk.superapp.browser.utils.VkUiRxStoryFailed;
import com.vk.superapp.browser.utils.VkUiRxStoryFinish;
import com.vk.superapp.common.js.bridge.api.JsCommonDelegate;
import com.vk.superapp.common.js.bridge.api.di.JsCommonDelegateComponent;
import com.vk.superapp.common.js.bridge.api.di.JsCommonDelegateConfig;
import com.vk.superapp.common.js.bridge.api.di.JsCommonDelegateFactory;
import com.vk.superapp.core.errors.VkAppsErrors;
import com.vk.superapp.core.extensions.RxExtKt;
import com.vk.superapp.core.utils.ThreadUtils;
import com.vk.superapp.libverify.js.bridge.api.JsLibverifyBridge;
import com.vk.superapp.libverify.js.bridge.api.di.JsLibverifyDelegateComponent;
import com.vk.superapp.navigation.api.VkBridgeAnalytics;
import com.vk.superapp.vkclient.js.bridge.api.JsVkclientDelegate;
import com.vk.superapp.vkclient.js.bridge.api.di.JsVkclientDelegateComponent;
import com.vk.superapp.vkclient.js.bridge.api.di.JsVkclientDelegateConfig;
import com.vk.superapp.vkclient.js.bridge.api.di.JsVkclientDelegateFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.data.cmd.server.AttachLinkLoadCommand;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b'\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\b&\u0018\u00002\u00020\u00012\u00020\u0002:\u0002\u0099\u0001B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\n\u0010\u000bJ\r\u0010\f\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0012\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0014\u0010\rJ!\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u0010H\u0014¢\u0006\u0004\b\u0018\u0010\u0019J\u001f\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001bH\u0014¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010!\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b!\u0010\"J\u0019\u0010#\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b#\u0010\"J\u0017\u0010$\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b$\u0010\"J\u0017\u0010%\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b%\u0010\"J\u0019\u0010&\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b&\u0010\"J\u0017\u0010'\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b'\u0010\"J\u0019\u0010(\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b(\u0010\"J\u000f\u0010*\u001a\u00020)H\u0016¢\u0006\u0004\b*\u0010+J\u0019\u0010,\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b,\u0010\"J\u0019\u0010-\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b-\u0010\"J\u0017\u0010.\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b.\u0010\"J\u0019\u0010/\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b/\u0010\"J\u0019\u00100\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b0\u0010\"J\u0019\u00101\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b1\u0010\"J\u0019\u00102\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b2\u0010\"J\u0019\u00103\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b3\u0010\"J\u0019\u00104\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b4\u0010\"J\u0019\u00105\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b5\u0010\"J\u0019\u00106\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b6\u0010\"J\u0019\u00107\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b7\u0010\"J\u0017\u00108\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b8\u0010\"J\u0017\u00109\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b9\u0010\"J\u0019\u0010:\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b:\u0010\"J\u0019\u0010;\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b;\u0010\"J\u0019\u0010<\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\b<\u0010\"J\u0017\u0010=\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b=\u0010\"J\u000f\u0010>\u001a\u00020\tH&¢\u0006\u0004\b>\u0010\rJ\u0017\u0010?\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b?\u0010\"J\u0017\u0010@\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u001fH\u0017¢\u0006\u0004\b@\u0010\"J\u0019\u0010A\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bA\u0010\"J\u0019\u0010B\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bB\u0010\"J\u0019\u0010C\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bC\u0010\"J\u0019\u0010D\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bD\u0010\"J\u0019\u0010E\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bE\u0010\"J\u0019\u0010F\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bF\u0010\"J\u0019\u0010G\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bG\u0010\"J\u0019\u0010H\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bH\u0010\"J\u0019\u0010I\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bI\u0010\"J\u0019\u0010J\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bJ\u0010\"J\u0019\u0010K\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0007¢\u0006\u0004\bK\u0010\"J\u0019\u0010L\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bL\u0010\"J\u0019\u0010M\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bM\u0010\"J\u0019\u0010N\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bN\u0010\"J\u0019\u0010O\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bO\u0010\"J\u0019\u0010P\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0017¢\u0006\u0004\bP\u0010\"J\u000f\u0010R\u001a\u00020QH\u0014¢\u0006\u0004\bR\u0010SJ\u0019\u0010T\u001a\u00020\t2\b\u0010 \u001a\u0004\u0018\u00010\u001fH\u0004¢\u0006\u0004\bT\u0010\"J\u001f\u0010W\u001a\u00020\t2\u0006\u0010 \u001a\u00020U2\u0006\u0010\b\u001a\u00020VH\u0014¢\u0006\u0004\bW\u0010XR$\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010\u0006R\u001b\u0010c\u001a\u00020^8@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR\"\u0010j\u001a\u00020\u00108\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bd\u0010e\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR$\u0010\b\u001a\u0004\u0018\u00010\u00078\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bm\u0010n\"\u0004\bo\u0010\u000bR\u001b\u0010t\u001a\u00020p8TX\u0094\u0084\u0002¢\u0006\f\n\u0004\bq\u0010`\u001a\u0004\br\u0010sR#\u0010y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001f0u8DX\u0084\u0084\u0002¢\u0006\f\n\u0004\bv\u0010`\u001a\u0004\bw\u0010xR \u0010\u007f\u001a\b\u0012\u0004\u0012\u00020{0z8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b|\u0010`\u001a\u0004\b}\u0010~R$\u0010\u0083\u0001\u001a\t\u0012\u0005\u0012\u00030\u0080\u00010z8\u0004X\u0084\u0004¢\u0006\u000e\n\u0005\b\u0081\u0001\u0010`\u001a\u0005\b\u0082\u0001\u0010~R$\u0010\u0087\u0001\u001a\t\u0012\u0005\u0012\u00030\u0084\u00010z8\u0004X\u0084\u0004¢\u0006\u000e\n\u0005\b\u0085\u0001\u0010`\u001a\u0005\b\u0086\u0001\u0010~R\u0018\u0010\u008b\u0001\u001a\u00030\u0088\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001R \u0010\u0090\u0001\u001a\u00020{8FX\u0086\u0084\u0002¢\u0006\u0010\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001*\u0006\b\u008e\u0001\u0010\u008f\u0001R!\u0010\u0094\u0001\u001a\u00030\u0080\u00018FX\u0086\u0084\u0002¢\u0006\u0010\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001*\u0006\b\u0093\u0001\u0010\u008f\u0001R!\u0010\u0098\u0001\u001a\u00030\u0084\u00018@X\u0080\u0084\u0002¢\u0006\u0010\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001*\u0006\b\u0097\u0001\u0010\u008f\u0001¨\u0006\u009a\u0001"}, d2 = {"Lcom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge;", "Lcom/vk/superapp/browser/internal/bridges/js/JsAndroidBridge;", "Lcom/vk/superapp/bridges/js/JsBrowserBridge;", "Lcom/vk/superapp/browser/internal/delegates/VkUiBrowserPresenter;", "presenter", "<init>", "(Lcom/vk/superapp/browser/internal/delegates/VkUiBrowserPresenter;)V", "Lcom/vk/superapp/browser/ui/callback/OnWebCallback;", "callback", "", "setOnWebCallback", "(Lcom/vk/superapp/browser/ui/callback/OnWebCallback;)V", "onResume", "()V", "Lcom/vk/auth/api/models/AuthResult;", "authResult", "", "keepAlive", "onAuth", "(Lcom/vk/auth/api/models/AuthResult;Z)V", "release", "Lcom/vk/superapp/base/js/bridge/data/VkUiCloseData$Status;", "closeData", "force", "onWebAppClose", "(Lcom/vk/superapp/base/js/bridge/data/VkUiCloseData$Status;Z)V", "closeParent", "Lcom/vk/superapp/api/dto/app/ResolvingResult;", "result", "handleOpenAppEvent", "(ZLcom/vk/superapp/api/dto/app/ResolvingResult;)V", "", "data", "VKWebAppShowImages", "(Ljava/lang/String;)V", "VKWebAppOpenPackage", "VKWebAppAuthPauseRequests", "VKWebAppAuthByExchangeToken", "VKWebAppGetRestoreHash", "VKWebAppAuthResumeRequests", "VKWebAppForceLogout", "Lcom/vk/superapp/bridges/LogoutReason;", "getLogoutReason", "()Lcom/vk/superapp/bridges/LogoutReason;", "VKWebAppValidatePhone", "VKWebAppIsPasskeyAvailable", "VKWebAppRegisterPasskey", "VKWebAppOpenMultiaccountSwitcher", "VKWebAppIsMultiaccountAvailable", "VKWebAppRelatedPinCodeChanged", "VKWebAppConfirmUserByService", "VKWebAppLibverifyStart", "VKWebAppLibverifySupported", "VKWebAppLibverifyCheck", "VKWebAppLibverifyResend", "VKWebAppLibverifyCancel", "VKWebAppUsersSearch", "VKWebAppOpenExternalLink", "VKWebAppCustomMessage", "VKWebAppOpenPayForm", "VKWebAppAuthRestore", "VKWebAppUserDeactivated", "updateConfig", "VKWebAppShowQR", "VKWebAppDownloadFile", "VKWebAppVKPayCheckout", "VKWebAppAccelerometerStart", "VKWebAppAccelerometerStop", "VKWebAppGyroscopeStart", "VKWebAppGyroscopeStop", "VKWebAppDeviceMotionStart", "VKWebAppDeviceMotionStop", "VKWebAppShowOrderBox", "VKWebAppShowGoodOrderBox", "VKWebAppShowSubscriptionBox", "VKWebAppSwipeToClose", "VKWebAppShowActionMenu", "VKWebAppIsNativePaymentEnabled", "VKWebAppVerifyUserByService", "VKWebAppVerifyUserServicesInfo", "VKWebAppSaveCredentials", "Lcom/vk/superapp/base/js/bridge/delegate/LocalTokenDelegate;", "provideLocalTokenDelegate", "()Lcom/vk/superapp/base/js/bridge/delegate/LocalTokenDelegate;", "handleVKWebAppAlert", "Lcom/vk/superapp/bridges/dto/VkAlertData;", "Lcom/vk/superapp/bridges/SuperappUiRouterBridge$OnAlertClickCallback;", "showInternalAlert", "(Lcom/vk/superapp/bridges/dto/VkAlertData;Lcom/vk/superapp/bridges/SuperappUiRouterBridge$OnAlertClickCallback;)V", "resworbkvmocl", "Lcom/vk/superapp/browser/internal/delegates/VkUiBrowserPresenter;", "getPresenter", "()Lcom/vk/superapp/browser/internal/delegates/VkUiBrowserPresenter;", "setPresenter", "Lcom/vk/superapp/browser/internal/bridges/js/features/FullScreenLoaderDelegate;", "resworbkvmocp", "Lkotlin/Lazy;", "getFullScreenLoaderDelegate$browser_release", "()Lcom/vk/superapp/browser/internal/bridges/js/features/FullScreenLoaderDelegate;", "fullScreenLoaderDelegate", "resworbkvmocq", "Z", "getNeedUpdateConfig", "()Z", "setNeedUpdateConfig", "(Z)V", "needUpdateConfig", "resworbkvmocr", "Lcom/vk/superapp/browser/ui/callback/OnWebCallback;", "getCallback", "()Lcom/vk/superapp/browser/ui/callback/OnWebCallback;", "setCallback", "Lcom/vk/superapp/auth/js/bridge/api/JsMultiaccountDelegate;", "resworbkvmocs", "getMultiAccountDelegate", "()Lcom/vk/superapp/auth/js/bridge/api/JsMultiaccountDelegate;", "multiAccountDelegate", "", "resworbkvmoct", "getSupportedOauthProviders", "()Ljava/util/List;", "supportedOauthProviders", "Lkotlin/Lazy;", "Lcom/vk/superapp/common/js/bridge/api/JsCommonDelegate;", "resworbkvmocu", "getJsCommonDelegateLazy", "()Lkotlin/Lazy;", "jsCommonDelegateLazy", "Lcom/vk/superapp/vkclient/js/bridge/api/JsVkclientDelegate;", "resworbkvmocv", "getJsVkclientDelegateLazy", "jsVkclientDelegateLazy", "Lcom/vk/superapp/auth/js/bridge/api/JsAuthDelegate;", "resworbkvmocw", "getJsAuthDelegateLazy", "jsAuthDelegateLazy", "Lcom/vk/di/internal/context/RootTypedDiContext;", "getDiContext", "()Lcom/vk/di/internal/context/RootTypedDiContext;", "diContext", "getJsCommonDelegate", "()Lcom/vk/superapp/common/js/bridge/api/JsCommonDelegate;", "getJsCommonDelegate$delegate", "(Lcom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge;)Ljava/lang/Object;", "jsCommonDelegate", "getJsVkclientDelegate", "()Lcom/vk/superapp/vkclient/js/bridge/api/JsVkclientDelegate;", "getJsVkclientDelegate$delegate", "jsVkclientDelegate", "getJsAuthDelegate$browser_release", "()Lcom/vk/superapp/auth/js/bridge/api/JsAuthDelegate;", "getJsAuthDelegate$browser_release$delegate", "jsAuthDelegate", "resworbkvmoca", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nJsVkBrowserCoreBridge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsVkBrowserCoreBridge.kt\ncom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,911:1\n1563#2:912\n1634#2,3:913\n*S KotlinDebug\n*F\n+ 1 JsVkBrowserCoreBridge.kt\ncom/vk/superapp/browser/internal/bridges/js/JsVkBrowserCoreBridge\n*L\n134#1:912\n134#1:913,3\n*E\n"})
public abstract class JsVkBrowserCoreBridge extends JsAndroidBridge implements JsBrowserBridge {

    @NotNull
    private static final Long[] resworbkvmocz = {7058363L, 7787819L};

    /* JADX INFO: renamed from: resworbkvmocl, reason: from kotlin metadata */
    @Nullable
    private VkUiBrowserPresenter presenter;

    @NotNull
    private final Lazy resworbkvmocm;

    @NotNull
    private final Lazy resworbkvmocn;

    @NotNull
    private final Lazy resworbkvmoco;

    /* JADX INFO: renamed from: resworbkvmocp, reason: from kotlin metadata */
    @NotNull
    private final Lazy fullScreenLoaderDelegate;

    /* JADX INFO: renamed from: resworbkvmocq, reason: from kotlin metadata */
    private boolean needUpdateConfig;

    /* JADX INFO: renamed from: resworbkvmocr, reason: from kotlin metadata */
    @Nullable
    private OnWebCallback callback;

    /* JADX INFO: renamed from: resworbkvmocs, reason: from kotlin metadata */
    @NotNull
    private final Lazy multiAccountDelegate;

    /* JADX INFO: renamed from: resworbkvmoct, reason: from kotlin metadata */
    @NotNull
    private final Lazy supportedOauthProviders;

    /* JADX INFO: renamed from: resworbkvmocu, reason: from kotlin metadata */
    @NotNull
    private final Lazy<JsCommonDelegate> jsCommonDelegateLazy;

    /* JADX INFO: renamed from: resworbkvmocv, reason: from kotlin metadata */
    @NotNull
    private final Lazy<JsVkclientDelegate> jsVkclientDelegateLazy;

    /* JADX INFO: renamed from: resworbkvmocw, reason: from kotlin metadata */
    @NotNull
    private final Lazy<JsAuthDelegate> jsAuthDelegateLazy;

    @NotNull
    private final Lazy resworbkvmocx;

    @NotNull
    private final Lazy resworbkvmocy;

    /* JADX INFO: compiled from: ProGuard */
    static final /* synthetic */ class resworbkvmocc extends FunctionReferenceImpl implements Function0<VkAuthCredentials> {
        resworbkvmocc(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
            super(0, jsVkBrowserCoreBridge, JsVkBrowserCoreBridge.class, "getAuthCredentials", "getAuthCredentials()Lcom/vk/superapp/api/dto/auth/VkAuthCredentials;", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        /* JADX INFO: renamed from: resworbkvmoca, reason: merged with bridge method [inline-methods] */
        public final VkAuthCredentials invoke() {
            return ((JsVkBrowserCoreBridge) this.receiver).getAuthCredentials();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    static final /* synthetic */ class resworbkvmocd extends FunctionReferenceImpl implements Function2<AuthResult, Boolean, Unit> {
        resworbkvmocd(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
            super(2, jsVkBrowserCoreBridge, JsVkBrowserCoreBridge.class, "onAuth", "onAuth(Lcom/vk/auth/api/models/AuthResult;Z)V", 0);
        }

        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Unit invoke(AuthResult authResult, Boolean bool) {
            resworbkvmoca(authResult, bool.booleanValue());
            return Unit.INSTANCE;
        }

        public final void resworbkvmoca(AuthResult p10, boolean z10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            ((JsVkBrowserCoreBridge) this.receiver).onAuth(p10, z10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    static final /* synthetic */ class resworbkvmoce extends FunctionReferenceImpl implements Function0<AuthData> {
        resworbkvmoce(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
            super(0, jsVkBrowserCoreBridge, JsVkBrowserCoreBridge.class, "getAuth", "getAuth()Lcom/vk/superapp/bridges/dto/AuthData;", 0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final AuthData invoke() {
            return ((JsVkBrowserCoreBridge) this.receiver).getAuth();
        }
    }

    public JsVkBrowserCoreBridge(@Nullable VkUiBrowserPresenter vkUiBrowserPresenter) {
        super((vkUiBrowserPresenter == null || !vkUiBrowserPresenter.isInternal()) ? MethodScope.PUBLIC : MethodScope.INTERNAL);
        this.presenter = vkUiBrowserPresenter;
        this.resworbkvmocm = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.i2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoco(this.f52244a);
            }
        });
        this.resworbkvmocn = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.l2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoci(this.f52265a);
            }
        });
        this.resworbkvmoco = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.m2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocm(this.f52271a);
            }
        });
        this.fullScreenLoaderDelegate = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.e1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoca(this.f52092a);
            }
        });
        this.multiAccountDelegate = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.f1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoca();
            }
        });
        this.supportedOauthProviders = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.g1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocn(this.f52232a);
            }
        });
        this.jsCommonDelegateLazy = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.h1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocf(this.f52238a);
            }
        });
        this.jsVkclientDelegateLazy = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.i1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoch(this.f52243a);
            }
        });
        this.jsAuthDelegateLazy = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.j1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocc(this.f52249a);
            }
        });
        this.resworbkvmocx = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.k1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocg(this.f52254a);
            }
        });
        this.resworbkvmocy = LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.j2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocj(this.f52250a);
            }
        });
        LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.k2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(JsVkBrowserCoreBridge.resworbkvmocb(this.f52255a));
            }
        });
    }

    public static final void access$handleEvents(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, VkUiRxStoryBoxEvent vkUiRxStoryBoxEvent) {
        VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
        if (presenter == null || presenter.getAppId() != vkUiRxStoryBoxEvent.getAppId() || vkUiRxStoryBoxEvent.getRequestId().length() == 0) {
            return;
        }
        if (vkUiRxStoryBoxEvent instanceof VkUiRxStoryFinish) {
            VkUiRxStoryFinish vkUiRxStoryFinish = (VkUiRxStoryFinish) vkUiRxStoryBoxEvent;
            jsVkBrowserCoreBridge.getJsVkclientDelegate().handleStoryBoxFinish(vkUiRxStoryFinish.getStoryId(), vkUiRxStoryFinish.getStoryOwnerId());
        } else {
            if (!(vkUiRxStoryBoxEvent instanceof VkUiRxStoryFailed)) {
                throw new NoWhenBranchMatchedException();
            }
            jsVkBrowserCoreBridge.getJsVkclientDelegate().handleStoryBoxFailed(((VkUiRxStoryFailed) vkUiRxStoryBoxEvent).getDescription());
        }
    }

    public static final JSONObject access$validatePhoneJson(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, boolean z10) {
        jsVkBrowserCoreBridge.getClass();
        return new JSONObject().put("phone_validated", z10);
    }

    public static /* synthetic */ void onWebAppClose$default(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, VkUiCloseData.Status status, boolean z10, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onWebAppClose");
        }
        if ((i10 & 2) != 0) {
            z10 = false;
        }
        jsVkBrowserCoreBridge.onWebAppClose(status, z10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FullScreenLoaderDelegate resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return new FullScreenLoaderDelegate(new MutablePropertyReference0Impl(jsVkBrowserCoreBridge) { // from class: com.vk.superapp.browser.internal.bridges.js.JsVkBrowserCoreBridge.resworbkvmocb
            {
                super(jsVkBrowserCoreBridge, JsVkBrowserCoreBridge.class, "presenter", "getPresenter()Lcom/vk/superapp/browser/internal/delegates/VkUiBrowserPresenter;", 0);
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KProperty0
            public final Object get() {
                return ((JsVkBrowserCoreBridge) this.receiver).getPresenter();
            }

            @Override // kotlin.jvm.internal.MutablePropertyReference0Impl, kotlin.reflect.KMutableProperty0
            public final void set(Object obj) {
                ((JsVkBrowserCoreBridge) this.receiver).setPresenter((VkUiBrowserPresenter) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean resworbkvmocb(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        Long[] lArr = resworbkvmocz;
        VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
        return ArraysKt.contains(lArr, presenter != null ? Long.valueOf(presenter.getAppId()) : null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsAuthDelegate resworbkvmocc(final JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return ((JsAuthDelegateComponent) jsVkBrowserCoreBridge.getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(JsAuthDelegateComponent.class))).getJsAuthDelegateFactory().create(jsVkBrowserCoreBridge.getPresenter(), jsVkBrowserCoreBridge, new JsAuthDelegate.Callbacks(new resworbkvmocc(jsVkBrowserCoreBridge), new resworbkvmocd(jsVkBrowserCoreBridge), new resworbkvmoce(jsVkBrowserCoreBridge), jsVkBrowserCoreBridge.getFullScreenLoaderDelegate$browser_release(), new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.d1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoce(this.f52087a);
            }
        }), jsVkBrowserCoreBridge.provideLocalTokenDelegate(), jsVkBrowserCoreBridge.getMultiAccountDelegate(), LazyKt.lazy(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.o1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocd(this.f52278a);
            }
        }), ((SakAnalyticsComponent) jsVkBrowserCoreBridge.getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(SakAnalyticsComponent.class))).getCredentialsAnalytics());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VerificationOAuthManager resworbkvmocd(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return ((OAuthComponent) jsVkBrowserCoreBridge.getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(OAuthComponent.class))).getVerificationOAuthManager();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String resworbkvmoce(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        WebView webView;
        WebClients.Holder webViewHolder = jsVkBrowserCoreBridge.getWebViewHolder();
        if (webViewHolder == null || (webView = webViewHolder.getWebView()) == null) {
            return null;
        }
        return webView.getUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsCommonDelegate resworbkvmocf(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return jsVkBrowserCoreBridge.resworbkvmocb();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsLibverifyBridge resworbkvmocg(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return ((JsLibverifyDelegateComponent) jsVkBrowserCoreBridge.getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(JsLibverifyDelegateComponent.class))).getJsLibverifyBridgeFactory().create(jsVkBrowserCoreBridge);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsVkclientDelegate resworbkvmoch(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return jsVkBrowserCoreBridge.resworbkvmocd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsNativePaymentsDelegate resworbkvmoci(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return new JsNativePaymentsDelegate(jsVkBrowserCoreBridge);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final resworbkvmoca resworbkvmocj(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return jsVkBrowserCoreBridge.new resworbkvmoca();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Context resworbkvmock(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return jsVkBrowserCoreBridge.getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Context resworbkvmocl(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return jsVkBrowserCoreBridge.getContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsShowActionMenuDelegate resworbkvmocm(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return new JsShowActionMenuDelegate(jsVkBrowserCoreBridge.getPresenter(), jsVkBrowserCoreBridge);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List resworbkvmocn(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        List<VkOAuthService> addedDependencies = ((OAuthComponent) jsVkBrowserCoreBridge.getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(OAuthComponent.class))).getOAuthManager().getAddedDependencies();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(addedDependencies, 10));
        Iterator<T> it = addedDependencies.iterator();
        while (it.hasNext()) {
            arrayList.add(((VkOAuthService) it.next()).getServiceName());
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsVkPayCheckoutDelegate resworbkvmoco(JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        return new JsVkPayCheckoutDelegate(jsVkBrowserCoreBridge);
    }

    @Override // com.vk.superapp.bridges.js.JsHardwareBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppAccelerometerStart(@Nullable String data) {
        getJsCommonDelegate().VKWebAppAccelerometerStart(data);
    }

    @Override // com.vk.superapp.bridges.js.JsHardwareBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppAccelerometerStop(@Nullable String data) {
        getJsCommonDelegate().VKWebAppAccelerometerStop(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppAuthByExchangeToken(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        getJsAuthDelegate$browser_release().VKWebAppAuthByExchangeToken(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppAuthPauseRequests(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        getJsAuthDelegate$browser_release().VKWebAppAuthPauseRequests(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppAuthRestore(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppAuthRestore(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppAuthResumeRequests(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        getJsAuthDelegate$browser_release().VKWebAppAuthResumeRequests(data);
        getFullScreenLoaderDelegate$browser_release().dismissLoader();
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppConfirmUserByService(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppConfirmUserByService(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppCustomMessage(@Nullable String data) {
        getJsCommonDelegate().VKWebAppCustomMessage(data);
    }

    @Override // com.vk.superapp.bridges.js.JsHardwareBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppDeviceMotionStart(@Nullable String data) {
        getJsCommonDelegate().VKWebAppDeviceMotionStart(data);
    }

    @Override // com.vk.superapp.bridges.js.JsHardwareBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppDeviceMotionStop(@Nullable String data) {
        getJsCommonDelegate().VKWebAppDeviceMotionStop(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppDownloadFile(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (onJsApiCalled(JsApiMethodType.DOWNLOAD_FILE, data)) {
            try {
                JSONObject jSONObject = new JSONObject(data);
                final String strOptString = jSONObject.optString(VkUiActivityResultDelegate.KEY_REQUEST_ID);
                final String string = jSONObject.getString("url");
                final String string2 = jSONObject.getString(AttachLinkLoadCommand.FILE_NAME);
                final Context context = getContext();
                if (context != null) {
                    ThreadUtils.runUiThread$default(null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.q1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return JsVkBrowserCoreBridge.resworbkvmoca(context, string2, this, string, strOptString);
                        }
                    }, 1, null);
                }
            } catch (Exception unused) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(this, JsApiMethodType.DOWNLOAD_FILE, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            }
        }
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppForceLogout(@Nullable String data) {
        VkUiBrowserView view;
        Function1<VkUiCloseData, Unit> closer;
        if (onJsApiCalled(JsApiMethodType.FORCE_LOGOUT, data)) {
            boolean zOptBoolean = data != null ? new JSONObject(data).optBoolean("show_login_password_screen") : false;
            VkUiBrowserPresenter presenter = getPresenter();
            if (presenter == null || (view = presenter.getResworbkvmocs()) == null || (closer = view.getCloser()) == null) {
                return;
            }
            closer.invoke(new VkUiCloseData.Logout(zOptBoolean, getAuth().getAccessToken(), getAuth().getUtilityTokens(), false, 8, null));
        }
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppGetRestoreHash(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppGetRestoreHash(data);
    }

    @Override // com.vk.superapp.bridges.js.JsHardwareBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppGyroscopeStart(@Nullable String data) {
        getJsCommonDelegate().VKWebAppGyroscopeStart(data);
    }

    @Override // com.vk.superapp.bridges.js.JsHardwareBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppGyroscopeStop(@Nullable String data) {
        getJsCommonDelegate().VKWebAppGyroscopeStop(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppIsMultiaccountAvailable(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppIsMultiaccountAvailable(data);
    }

    @Override // com.vk.superapp.bridges.js.JsNativePaymentBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppIsNativePaymentEnabled(@Nullable String data) {
        ((JsNativePaymentsDelegate) this.resworbkvmocn.getValue()).delegateVKWebAppIsNativePaymentEnabled(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppIsPasskeyAvailable(@Nullable String data) {
        getJsCommonDelegate().VKWebAppIsPasskeyAvailable(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppLibverifyCancel(@Nullable String data) {
        ((JsLibverifyBridge) this.resworbkvmocx.getValue()).VKWebAppLibverifyCancel(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppLibverifyCheck(@Nullable String data) {
        ((JsLibverifyBridge) this.resworbkvmocx.getValue()).VKWebAppLibverifyCheck(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppLibverifyResend(@Nullable String data) {
        ((JsLibverifyBridge) this.resworbkvmocx.getValue()).VKWebAppLibverifyResend(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppLibverifyStart(@Nullable String data) {
        ((JsLibverifyBridge) this.resworbkvmocx.getValue()).VKWebAppLibverifyStart(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppLibverifySupported(@Nullable String data) {
        ((JsLibverifyBridge) this.resworbkvmocx.getValue()).VKWebAppLibverifySupported(data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @android.webkit.JavascriptInterface
    public void VKWebAppOpenExternalLink(@NotNull String data) {
        List<Integer> listEmptyList;
        VkBridgeAnalytics bridgeAnalytics;
        SuperappFeature externalUrlMiniappsFeature;
        JSONObject jsonValue;
        JSONArray jSONArrayOptJSONArray;
        Intrinsics.checkNotNullParameter(data, "data");
        if (onJsApiCalled(JsApiMethodType.OPEN_EXTERNAL_LINK, data)) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            try {
                SuperappBrowserFeaturesBridge superappBrowserFeatures = SuperappBridgesKt.getSuperappBrowserFeatures();
                if (superappBrowserFeatures == null || (externalUrlMiniappsFeature = superappBrowserFeatures.getExternalUrlMiniappsFeature()) == null || (jsonValue = externalUrlMiniappsFeature.getJsonValue()) == null || (jSONArrayOptJSONArray = jsonValue.optJSONArray("app_ids")) == null || (listEmptyList = JsonObjectExtKt.toIntList(jSONArrayOptJSONArray)) == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                VkUiBrowserPresenter presenter = getPresenter();
                if (!listEmptyList.contains(Integer.valueOf(presenter != null ? (int) presenter.getAppId() : -1))) {
                    linkedHashMap.put("app_supported", Boolean.FALSE);
                    JsVkBrowserCoreBridgeExtKt.sendEventFailedWithState(this, VkAppsErrors.Client.ACCESS_DENIED, linkedHashMap);
                    return;
                }
                Boolean bool = Boolean.TRUE;
                linkedHashMap.put("app_supported", bool);
                try {
                    String codeChallenge = new DefaultSecureDataGenerator(null, 1, 0 == true ? 1 : 0).generate().getCodeChallenge();
                    linkedHashMap.put("csrf_created", bool);
                    String optStringOrNull = JsonObjectExtKt.getOptStringOrNull(new JSONObject(data), "url");
                    if (optStringOrNull == null) {
                        linkedHashMap.put("url_component_created_initially", Boolean.FALSE);
                        JsVkBrowserCoreBridgeExtKt.sendEventFailedWithState(this, VkAppsErrors.Client.INVALID_PARAMS, linkedHashMap);
                        return;
                    }
                    linkedHashMap.put("url_component_created_initially", bool);
                    Uri uriBuild = UriExtKt.toUri(optStringOrNull).buildUpon().appendQueryParameter("vk_state", codeChallenge).build();
                    linkedHashMap.put("url_created_with_csrf", bool);
                    Intent intentAddFlags = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, uriBuild).addCategory("android.intent.category.BROWSABLE").addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                    Intrinsics.checkNotNullExpressionValue(intentAddFlags, "addFlags(...)");
                    if (intentAddFlags.resolveActivity(getAppContext().getPackageManager()) == null) {
                        linkedHashMap.put("return_by_deeplink", Boolean.FALSE);
                        JsVkBrowserCoreBridgeExtKt.sendEventFailedWithState(this, VkAppsErrors.Client.UNKNOWN_ERROR, linkedHashMap);
                        return;
                    }
                    JsOpenExternalLinkRepository.Companion companion = JsOpenExternalLinkRepository.INSTANCE;
                    companion.clear();
                    companion.init(this, getPresenter(), codeChallenge, linkedHashMap);
                    VkUiBrowserPresenter presenter2 = getPresenter();
                    if (presenter2 != null && (bridgeAnalytics = presenter2.getBridgeAnalytics()) != null) {
                        bridgeAnalytics.trackRegistrationEvent(VkBridgeAnalytics.RegistrationEvent.EXTERNAL_LINK_MINIAPP_OPEN);
                    }
                    Context context = getContext();
                    if (context != null) {
                        context.startActivity(intentAddFlags);
                    }
                } catch (Exception unused) {
                    linkedHashMap.put("csrf_created", Boolean.FALSE);
                    JsVkBrowserCoreBridgeExtKt.sendEventFailedWithState(this, VkAppsErrors.Client.UNKNOWN_ERROR, linkedHashMap);
                }
            } catch (JSONException unused2) {
                JsVkBrowserCoreBridgeExtKt.sendEventFailedWithState(this, VkAppsErrors.Client.UNKNOWN_ERROR, linkedHashMap);
            }
        }
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppOpenMultiaccountSwitcher(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppOpenMultiaccountSwitcher(data);
    }

    @android.webkit.JavascriptInterface
    public void VKWebAppOpenPackage(@Nullable String data) {
        JsApiMethodType jsApiMethodType = JsApiMethodType.OPEN_PACKAGE;
        if (onJsApiCalled(jsApiMethodType, data)) {
            String strOptString = data != null ? new JSONObject(data).optString("package") : null;
            if (strOptString == null || StringsKt.isBlank(strOptString) || !WebAppUtils.INSTANCE.openPackageOrPlayMarket(getAppContext(), strOptString, true)) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(this, jsApiMethodType, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            } else {
                WebAppBridge.DefaultImpls.sendEventSuccess$default(this, jsApiMethodType, BaseWebBridge.INSTANCE.createSuccessData(), null, null, 12, null);
            }
        }
    }

    @Override // com.vk.superapp.bridges.js.JsVkPayBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppOpenPayForm(@Nullable String data) {
        new PayFormHandler(this, (JsVkPayCheckoutDelegate) this.resworbkvmocm.getValue()).handle(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppRegisterPasskey(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (onJsApiCalled(JsApiMethodType.REGISTER_PASSKEY, data)) {
            ((resworbkvmoca) this.resworbkvmocy.getValue()).resworbkvmoca(data);
        }
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppRelatedPinCodeChanged(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppRelatedPinCodeChanged(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppSaveCredentials(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppSaveCredentials(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppShowActionMenu(@Nullable String data) {
        if (onJsApiCalled(JsApiMethodType.SHOW_ACTION_MENU, data)) {
            ((JsShowActionMenuDelegate) this.resworbkvmoco.getValue()).delegateShowActionMenu(data);
        }
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppShowGoodOrderBox(@Nullable String data) {
        getJsVkclientDelegate().VKWebAppShowGoodOrderBox(data);
    }

    @android.webkit.JavascriptInterface
    public void VKWebAppShowImages(@Nullable String data) {
        JsApiMethodType jsApiMethodType = JsApiMethodType.SHOW_IMAGES;
        if (onJsApiCalled(jsApiMethodType, data)) {
            try {
                JSONObject jSONObject = new JSONObject(data);
                final List<WebImage> imageUrls = BridgeUtils.INSTANCE.parseImageUrls(jSONObject.optJSONArray("images"));
                if (imageUrls.isEmpty()) {
                    WebAppBridge.DefaultImpls.sendEventFailed$default(this, jsApiMethodType, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
                } else {
                    final int iOptInt = jSONObject.optInt("start_index");
                    runUiThread$browser_release(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.x1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return JsVkBrowserCoreBridge.resworbkvmoca(iOptInt, imageUrls, this);
                        }
                    });
                }
            } catch (Throwable unused) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(this, JsApiMethodType.SHOW_IMAGES, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            }
        }
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppShowOrderBox(@Nullable String data) {
        getJsVkclientDelegate().VKWebAppShowOrderBox(data);
    }

    @Override // com.vk.superapp.bridges.js.JsNavigationBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppShowQR(@NotNull final String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (onJsApiCalled(JsApiMethodType.SHOW_QR, data)) {
            runUiThread$browser_release(new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.r1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return JsVkBrowserCoreBridge.resworbkvmoca(data, this);
                }
            });
        }
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppShowSubscriptionBox(@Nullable String data) {
        getJsVkclientDelegate().VKWebAppShowSubscriptionBox(data);
    }

    @android.webkit.JavascriptInterface
    public final void VKWebAppSwipeToClose(@Nullable String data) {
        if (onJsApiCalled(JsApiMethodType.SWIPE_TO_CLOSE, data)) {
            try {
                final boolean z10 = new JSONObject(data).getBoolean("enabled");
                ThreadUtils.runUiThread$default(null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.n1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return JsVkBrowserCoreBridge.resworbkvmoca(this.f52273a, z10);
                    }
                }, 1, null);
            } catch (Throwable unused) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(this, JsApiMethodType.SWIPE_TO_CLOSE, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            }
        }
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppUserDeactivated(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        getJsAuthDelegate$browser_release().VKWebAppUserDeactivated(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppUsersSearch(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        JsApiMethodType jsApiMethodType = JsApiMethodType.USERS_SEARCH;
        if (onJsApiCalled(jsApiMethodType, data)) {
            final String strOptString = new JSONObject(data).optString("access_token");
            Intrinsics.checkNotNull(strOptString);
            if (strOptString.length() == 0) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(this, jsApiMethodType, VkAppsErrors.Client.MISSING_PARAMS, null, null, null, null, 60, null);
            } else {
                ThreadUtils.runUiThread$default(null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.p1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return JsVkBrowserCoreBridge.resworbkvmocb(strOptString, this);
                    }
                }, 1, null);
            }
        }
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppVKPayCheckout(@Nullable String data) {
        ((JsVkPayCheckoutDelegate) this.resworbkvmocm.getValue()).delegateVKWebAppVkPayCheckout(data);
    }

    @Override // com.vk.superapp.bridges.js.JsAuthBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppValidatePhone(@Nullable String data) {
        if (onJsApiCalled(JsApiMethodType.VALIDATE_PHONE, data)) {
            SuperappApi.VkAuth auth = SuperappBridgesKt.getSuperappApi().getAuth();
            VkUiBrowserPresenter presenter = getPresenter();
            Single<AuthValidatePhoneCheckResponse> singleValidatePhoneCheck = auth.validatePhoneCheck(false, presenter != null ? Long.valueOf(presenter.getAppId()) : null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.b2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return JsVkBrowserCoreBridge.resworbkvmoca(this.f52079a, (AuthValidatePhoneCheckResponse) obj);
                }
            };
            Consumer<? super AuthValidatePhoneCheckResponse> consumer = new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.c2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    JsVkBrowserCoreBridge.resworbkvmoca(function1, obj);
                }
            };
            final Function1 function2 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.d2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return JsVkBrowserCoreBridge.resworbkvmoca(this.f52088a, (Throwable) obj);
                }
            };
            Disposable disposableSubscribe = singleValidatePhoneCheck.subscribe(consumer, new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.e2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    JsVkBrowserCoreBridge.resworbkvmocb(function2, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
            RxExtKt.disposeOnApplicationFinish(disposableSubscribe);
        }
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppVerifyUserByService(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppVerifyUserByService(data);
    }

    @Override // com.vk.superapp.bridges.js.JsBrowserBridge
    @android.webkit.JavascriptInterface
    public void VKWebAppVerifyUserServicesInfo(@Nullable String data) {
        getJsAuthDelegate$browser_release().VKWebAppVerifyUserServicesInfo(data);
    }

    @Nullable
    protected final OnWebCallback getCallback() {
        return this.callback;
    }

    @NotNull
    protected final RootTypedDiContext getDiContext() {
        Context applicationContext = SuperappApiCore.INSTANCE.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        return DiContextKt.getDiContext((Application) applicationContext);
    }

    @NotNull
    public final FullScreenLoaderDelegate getFullScreenLoaderDelegate$browser_release() {
        return (FullScreenLoaderDelegate) this.fullScreenLoaderDelegate.getValue();
    }

    @NotNull
    public final JsAuthDelegate getJsAuthDelegate$browser_release() {
        return this.jsAuthDelegateLazy.getValue();
    }

    @NotNull
    protected final Lazy<JsAuthDelegate> getJsAuthDelegateLazy() {
        return this.jsAuthDelegateLazy;
    }

    @NotNull
    public final JsCommonDelegate getJsCommonDelegate() {
        return this.jsCommonDelegateLazy.getValue();
    }

    @NotNull
    protected final Lazy<JsCommonDelegate> getJsCommonDelegateLazy() {
        return this.jsCommonDelegateLazy;
    }

    @NotNull
    public final JsVkclientDelegate getJsVkclientDelegate() {
        return this.jsVkclientDelegateLazy.getValue();
    }

    @NotNull
    protected final Lazy<JsVkclientDelegate> getJsVkclientDelegateLazy() {
        return this.jsVkclientDelegateLazy;
    }

    @NotNull
    public LogoutReason getLogoutReason() {
        return LogoutReason.VK_UI;
    }

    @NotNull
    protected JsMultiaccountDelegate getMultiAccountDelegate() {
        return (JsMultiaccountDelegate) this.multiAccountDelegate.getValue();
    }

    protected final boolean getNeedUpdateConfig() {
        return this.needUpdateConfig;
    }

    @Nullable
    public VkUiBrowserPresenter getPresenter() {
        return this.presenter;
    }

    @NotNull
    protected final List<String> getSupportedOauthProviders() {
        return (List) this.supportedOauthProviders.getValue();
    }

    protected void handleOpenAppEvent(final boolean closeParent, @NotNull ResolvingResult result) {
        Intrinsics.checkNotNullParameter(result, "result");
        SuperappUiRouterBridge.DefaultImpls.openWebApp$default(SuperappBridgesKt.getSuperappUiRouter(), result.getApp(), result.getEmbeddedUrl(), result.getGroupId(), 107, new SuperappUiRouterBridge.OpenCallback() { // from class: com.vk.superapp.browser.internal.bridges.js.JsVkBrowserCoreBridge.handleOpenAppEvent.1
            @Override // com.vk.superapp.bridges.SuperappUiRouterBridge.OpenCallback
            public void onBackground() {
                JsVkBrowserCoreBridge.this.getJsCommonDelegate().sendOpenAppInactiveScreenError();
            }

            @Override // com.vk.superapp.bridges.SuperappUiRouterBridge.OpenCallback
            public void onScreenFailed() {
                JsVkBrowserCoreBridge.this.getJsCommonDelegate().sendOpenAppInvalidParamsError();
            }

            @Override // com.vk.superapp.bridges.SuperappUiRouterBridge.OpenCallback
            public void onSuccess() {
                JsVkBrowserCoreBridge.this.getJsCommonDelegate().sendOpenAppSuccess();
                if (closeParent) {
                    JsVkBrowserCoreBridge.this.onWebAppClose(VkUiCloseData.Status.INSTANCE.empty(), true);
                }
            }
        }, null, 32, null);
    }

    protected final void handleVKWebAppAlert(@Nullable String data) {
        getJsCommonDelegate().VKWebAppAlert(data);
    }

    @Override // com.vk.superapp.browser.internal.bridges.js.JsAndroidBridge
    protected void onAuth(@NotNull AuthResult authResult, boolean keepAlive) {
        VkUiBrowserPresenter presenter;
        VkUiBrowserView view;
        Function1<VkUiCloseData, Unit> closer;
        Intrinsics.checkNotNullParameter(authResult, "authResult");
        if (keepAlive || (presenter = getPresenter()) == null || (view = presenter.getResworbkvmocs()) == null || (closer = view.getCloser()) == null) {
            return;
        }
        closer.invoke(new VkUiCloseData.Auth(authResult));
    }

    public final void onResume() {
        if (this.needUpdateConfig) {
            updateConfig();
        }
    }

    protected void onWebAppClose(@NotNull final VkUiCloseData.Status closeData, boolean force) {
        Intrinsics.checkNotNullParameter(closeData, "closeData");
        String text = closeData.getText();
        if (!StringsKt.isBlank(text)) {
            SuperappBridgesKt.getSuperappUiRouter().showToast(text);
        }
        ThreadUtils.runUiThread$default(null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.w1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoca(this.f52317a, closeData);
            }
        }, 1, null);
    }

    @NotNull
    protected LocalTokenDelegate provideLocalTokenDelegate() {
        return new LocalTokenDelegateStub();
    }

    @Override // com.vk.superapp.browser.internal.bridges.BaseWebBridge, com.vk.superapp.browser.internal.bridges.WebAppBridge
    public void release() {
        this.callback = null;
        setPresenter(null);
        setWebViewHolder(null);
        RefreshablePresenterDelegateKt.releaseIfInitialized(this.jsAuthDelegateLazy);
        RefreshablePresenterDelegateKt.releaseIfInitialized(this.jsCommonDelegateLazy);
        RefreshablePresenterDelegateKt.releaseIfInitialized(this.jsVkclientDelegateLazy);
        JsOpenExternalLinkRepository.INSTANCE.clear();
    }

    protected final void setCallback(@Nullable OnWebCallback onWebCallback) {
        this.callback = onWebCallback;
    }

    protected final void setNeedUpdateConfig(boolean z10) {
        this.needUpdateConfig = z10;
    }

    public final void setOnWebCallback(@NotNull OnWebCallback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    public void setPresenter(@Nullable VkUiBrowserPresenter vkUiBrowserPresenter) {
        this.presenter = vkUiBrowserPresenter;
    }

    protected void showInternalAlert(@NotNull VkAlertData data, @NotNull SuperappUiRouterBridge.OnAlertClickCallback callback) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(callback, "callback");
    }

    public abstract void updateConfig();

    /* JADX INFO: Access modifiers changed from: private */
    public static final JsMultiaccountDelegateSdkImpl resworbkvmoca() {
        return new JsMultiaccountDelegateSdkImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocb(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, VkUiCloseData.Status status) {
        VkUiBrowserView view;
        Function1<VkUiCloseData, Unit> closer;
        VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
        if (presenter != null && (view = presenter.getResworbkvmocs()) != null && (closer = view.getCloser()) != null) {
            closer.invoke(status);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmocb(String str, JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        SuperappUiRouterBridge superappUiRouter = SuperappBridgesKt.getSuperappUiRouter();
        Intrinsics.checkNotNull(str);
        if (!superappUiRouter.openSearchRestoreUsers(str)) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.USERS_SEARCH, VkAppsErrors.Client.INACTIVE_SCREEN, null, null, null, null, 60, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocd(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    private final JsVkclientDelegate resworbkvmocd() {
        JsVkclientDelegateFactory jsVkclientDelegateFactory = ((JsVkclientDelegateComponent) getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(JsVkclientDelegateComponent.class))).getJsVkclientDelegateFactory();
        VkUiBrowserPresenter presenter = getPresenter();
        VkUiBrowserPresenter presenter2 = getPresenter();
        return jsVkclientDelegateFactory.create(new JsVkclientDelegateConfig(presenter, this, presenter2 != null ? presenter2.getAnalytics() : null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.f2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocl(this.f52098a);
            }
        }, new JsVkBrowserCoreBridge$getCommonBridgeDelegateCallback$1(this)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(int i10, List list, JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        if (i10 >= 0 && i10 < list.size()) {
            if (SuperappBridgesKt.getSuperappUiRouter().openImageViewer(i10, list)) {
                WebAppBridge.DefaultImpls.sendEventSuccess$default(jsVkBrowserCoreBridge, JsApiMethodType.SHOW_IMAGES, BaseWebBridge.INSTANCE.createSuccessData(), null, null, 12, null);
            } else {
                WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.SHOW_IMAGES, VkAppsErrors.Client.UNKNOWN_ERROR, null, null, null, null, 60, null);
            }
        } else {
            WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.SHOW_IMAGES, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmocb(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, Throwable th2) {
        JsApiMethodType jsApiMethodType = JsApiMethodType.DOWNLOAD_FILE;
        Intrinsics.checkNotNull(th2);
        WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, jsApiMethodType, th2, null, 4, null);
        return Unit.INSTANCE;
    }

    private final JsCommonDelegate resworbkvmocb() {
        JsCommonDelegateFactory jsCommonDelegateFactory = ((JsCommonDelegateComponent) getDiContext().obtainComponent(Reflection.getOrCreateKotlinClass(JsCommonDelegateComponent.class))).getJsCommonDelegateFactory();
        VkUiBrowserPresenter presenter = getPresenter();
        VkUiBrowserPresenter presenter2 = getPresenter();
        return jsCommonDelegateFactory.create(new JsCommonDelegateConfig(presenter, this, presenter2 != null ? presenter2.getAnalytics() : null, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.y1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmocc();
            }
        }, new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.a2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmock(this.f52069a);
            }
        }, new JsVkBrowserCoreBridge$getCommonBridgeDelegateCallback$1(this)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    final class resworbkvmoca {

        @Nullable
        private ActivityResulter resworbkvmoca;

        /* JADX INFO: renamed from: com.vk.superapp.browser.internal.bridges.js.JsVkBrowserCoreBridge$resworbkvmoca$resworbkvmoca, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ProGuard */
        private final class C0243resworbkvmoca implements ActivityResulter {
            public C0243resworbkvmoca() {
            }

            @Override // com.vk.navigation.ActivityResulter
            public final void onActivityResult(int i10, int i11, @Nullable Intent intent) {
                VkUiBrowserView view;
                Activity activity;
                VkUiBrowserPresenter presenter = JsVkBrowserCoreBridge.this.getPresenter();
                if (presenter == null || (view = presenter.getResworbkvmocs()) == null || (activity = view.activity()) == null) {
                    return;
                }
                resworbkvmoca resworbkvmocaVar = resworbkvmoca.this;
                new PasskeySignUpDelegateImpl(new JsVkBrowserCoreBridge$PasskeyHelper$createRegistrationCallback$1(JsVkBrowserCoreBridge.this, resworbkvmocaVar)).onActivityResult(activity, i10, i11, intent);
            }
        }

        public resworbkvmoca() {
        }

        public final void resworbkvmoca(@NotNull String data) {
            VkUiBrowserView view;
            Activity activity;
            VkUiBrowserView view2;
            ComponentCallbacks2 componentCallbacks2Activity;
            Intrinsics.checkNotNullParameter(data, "data");
            VkUiBrowserPresenter presenter = JsVkBrowserCoreBridge.this.getPresenter();
            if (presenter == null || (view = presenter.getResworbkvmocs()) == null || (activity = view.activity()) == null) {
                return;
            }
            try {
                String strOptString = new JSONObject(data).optString(PasskeyBeginResult.PASSKEY_DATA_KEY);
                PasskeySignUpDelegateImpl passkeySignUpDelegateImpl = new PasskeySignUpDelegateImpl(new JsVkBrowserCoreBridge$PasskeyHelper$createRegistrationCallback$1(JsVkBrowserCoreBridge.this, this));
                VkUiBrowserPresenter presenter2 = JsVkBrowserCoreBridge.this.getPresenter();
                if (presenter2 != null && (view2 = presenter2.getResworbkvmocs()) != null && (componentCallbacks2Activity = view2.activity()) != null) {
                    C0243resworbkvmoca c0243resworbkvmoca = new C0243resworbkvmoca();
                    this.resworbkvmoca = c0243resworbkvmoca;
                    ResulterProvider resulterProvider = componentCallbacks2Activity instanceof ResulterProvider ? (ResulterProvider) componentCallbacks2Activity : null;
                    if (resulterProvider != null) {
                        resulterProvider.registerActivityResult(c0243resworbkvmoca);
                    }
                }
                Intrinsics.checkNotNull(strOptString);
                passkeySignUpDelegateImpl.registerPasskey(activity, strOptString);
            } catch (JSONException unused) {
                WebAppBridge.DefaultImpls.sendEventFailed$default(JsVkBrowserCoreBridge.this, JsApiMethodType.REGISTER_PASSKEY, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
            }
        }

        public static final void resworbkvmoca(resworbkvmoca resworbkvmocaVar) {
            VkUiBrowserPresenter presenter;
            VkUiBrowserView view;
            ComponentCallbacks2 componentCallbacks2Activity;
            if (resworbkvmocaVar.resworbkvmoca == null || (presenter = JsVkBrowserCoreBridge.this.getPresenter()) == null || (view = presenter.getResworbkvmocs()) == null || (componentCallbacks2Activity = view.activity()) == null) {
                return;
            }
            ResulterProvider resulterProvider = componentCallbacks2Activity instanceof ResulterProvider ? (ResulterProvider) componentCallbacks2Activity : null;
            if (resulterProvider != null) {
                resulterProvider.unregisterActivityResult(resworbkvmocaVar.resworbkvmoca);
            }
            resworbkvmocaVar.resworbkvmoca = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(final JsVkBrowserCoreBridge jsVkBrowserCoreBridge, AuthValidatePhoneCheckResponse authValidatePhoneCheckResponse) throws JSONException {
        VkUiBrowserView view;
        Activity activity;
        VkUiBrowserView view2;
        CompositeDisposable resworbkvmocq;
        VkValidatePhoneInfo.Companion companion = VkValidatePhoneInfo.INSTANCE;
        Intrinsics.checkNotNull(authValidatePhoneCheckResponse);
        VkValidatePhoneInfo vkValidatePhoneInfoFromCheckResponse = companion.fromCheckResponse(authValidatePhoneCheckResponse);
        if (vkValidatePhoneInfoFromCheckResponse instanceof VkValidatePhoneInfo.Skip) {
            JsApiMethodType jsApiMethodType = JsApiMethodType.VALIDATE_PHONE;
            jsVkBrowserCoreBridge.getClass();
            JSONObject jSONObjectPut = new JSONObject().put("phone_validated", true);
            Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "validatePhoneJson(...)");
            WebAppBridge.DefaultImpls.sendEventSuccess$default(jsVkBrowserCoreBridge, jsApiMethodType, jSONObjectPut, null, null, 12, null);
        } else if (Intrinsics.areEqual(vkValidatePhoneInfoFromCheckResponse, VkValidatePhoneInfo.Unknown.INSTANCE)) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.VALIDATE_PHONE, VkAppsErrors.Client.UNKNOWN_ERROR, null, null, null, null, 60, null);
        } else {
            VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
            if (presenter != null && (view = presenter.getResworbkvmocs()) != null && (activity = view.activity()) != null) {
                AuthLib.INSTANCE.addAuthCallback(new AuthCallback() { // from class: com.vk.superapp.browser.internal.bridges.js.JsVkBrowserCoreBridge$VKWebAppValidatePhone$1$1$1
                    @Override // com.vk.auth.main.AuthCallback
                    @MainThread
                    public void onAccessApproved(String str) {
                        AuthCallback.DefaultImpls.onAccessApproved(this, str);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    @MainThread
                    public void onAccessFlowCancel() {
                        AuthCallback.DefaultImpls.onAccessFlowCancel(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    @Deprecated(message = "Please don't use this method. Use [VkSilentTokenExchanger] for getting SilentAuthSource.")
                    public void onAdditionalOAuthAuth(AdditionalOauthAuthResult additionalOauthAuthResult) {
                        AuthCallback.DefaultImpls.onAdditionalOAuthAuth(this, additionalOauthAuthResult);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onAdditionalSignUpError() {
                        AuthCallback.DefaultImpls.onAdditionalSignUpError(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onAuth(AuthResult authResult) {
                        AuthCallback.DefaultImpls.onAuth(this, authResult);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    @MainThread
                    public void onCancel() {
                        AuthCallback.DefaultImpls.onCancel(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onEmailSignUpError() {
                        AuthCallback.DefaultImpls.onEmailSignUpError(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onOAuthConnectResult(VkOAuthConnectionResult vkOAuthConnectionResult) {
                        AuthCallback.DefaultImpls.onOAuthConnectResult(this, vkOAuthConnectionResult);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onPhoneValidationCompleted(VkPhoneValidationCompleteResult result) throws JSONException {
                        Intrinsics.checkNotNullParameter(result, "result");
                        AuthLib.INSTANCE.removeAuthCallback(this);
                        JSONObject jSONObjectAccess$validatePhoneJson = JsVkBrowserCoreBridge.access$validatePhoneJson(this.resworbkvmoca, true);
                        if (KotlinStringExtKt.isNotEmpty(result.getPhone())) {
                            jSONObjectAccess$validatePhoneJson.put("phone", result.getPhone());
                        }
                        JsVkBrowserCoreBridge jsVkBrowserCoreBridge2 = this.resworbkvmoca;
                        JsApiMethodType jsApiMethodType2 = JsApiMethodType.VALIDATE_PHONE;
                        Intrinsics.checkNotNull(jSONObjectAccess$validatePhoneJson);
                        WebAppBridge.DefaultImpls.sendEventSuccess$default(jsVkBrowserCoreBridge2, jsApiMethodType2, jSONObjectAccess$validatePhoneJson, null, null, 12, null);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onPhoneValidationError(VkPhoneValidationErrorReason reason) {
                        Intrinsics.checkNotNullParameter(reason, "reason");
                        AuthLib.INSTANCE.removeAuthCallback(this);
                        JsVkBrowserCoreBridge jsVkBrowserCoreBridge2 = this.resworbkvmoca;
                        JsApiMethodType jsApiMethodType2 = JsApiMethodType.VALIDATE_PHONE;
                        JSONObject jSONObjectAccess$validatePhoneJson = JsVkBrowserCoreBridge.access$validatePhoneJson(jsVkBrowserCoreBridge2, false);
                        Intrinsics.checkNotNullExpressionValue(jSONObjectAccess$validatePhoneJson, "access$validatePhoneJson(...)");
                        WebAppBridge.DefaultImpls.sendEventSuccess$default(jsVkBrowserCoreBridge2, jsApiMethodType2, jSONObjectAccess$validatePhoneJson, null, null, 12, null);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onRestoreBannedUserError() {
                        AuthCallback.DefaultImpls.onRestoreBannedUserError(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onRestoreDeactivatedUserError() {
                        AuthCallback.DefaultImpls.onRestoreDeactivatedUserError(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onSignUp(long j10, SignUpData signUpData) {
                        AuthCallback.DefaultImpls.onSignUp(this, j10, signUpData);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    public void onValidatePhoneError() {
                        AuthCallback.DefaultImpls.onValidatePhoneError(this);
                    }

                    @Override // com.vk.auth.main.AuthCallback
                    @MainThread
                    public void onCancel(Bundle bundle) {
                        AuthCallback.DefaultImpls.onCancel(this, bundle);
                    }
                });
                Disposable disposableVerifyUserPhone$default = VkPhoneValidationManager.verifyUserPhone$default(AuthLibBridge.INSTANCE.getPhoneValidationManager(), (FragmentActivity) activity, vkValidatePhoneInfoFromCheckResponse, true, false, null, null, 56, null);
                VkUiBrowserPresenter presenter2 = jsVkBrowserCoreBridge.getPresenter();
                if (presenter2 != null && (view2 = presenter2.getResworbkvmocs()) != null && (resworbkvmocq = view2.getResworbkvmocq()) != null) {
                    resworbkvmocq.add(disposableVerifyUserPhone$default);
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocc(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String resworbkvmocc() {
        return VKHost.getHost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, Throwable th2) {
        JsApiMethodType jsApiMethodType = JsApiMethodType.VALIDATE_PHONE;
        Intrinsics.checkNotNull(th2);
        WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, jsApiMethodType, th2, null, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(String str, JsVkBrowserCoreBridge jsVkBrowserCoreBridge) {
        VkUiBrowserView view;
        try {
            JSONObject jSONObject = new JSONObject(str);
            VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
            if (presenter != null && (view = presenter.getResworbkvmocs()) != null) {
                String strOptString = jSONObject.optString("text", "");
                Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                String strOptString2 = jSONObject.optString("title", "");
                Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(...)");
                view.openQr(strOptString, strOptString2, jSONObject.optString("logoUrl"));
            }
        } catch (Exception unused) {
            WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.SHOW_QR, VkAppsErrors.Client.INVALID_PARAMS, null, null, null, null, 60, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(final Context context, final String str, final JsVkBrowserCoreBridge jsVkBrowserCoreBridge, final String str2, final String str3) {
        String string = context.getResources().getString(R.string.vk_apps_download_message, str);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        new AlertDialog.Builder(jsVkBrowserCoreBridge.getContext()).setTitle(R.string.vk_apps_download).setMessage(string).setPositiveButton(R.string.vk_apps_download_ok, new DialogInterface.OnClickListener() { // from class: com.vk.superapp.browser.internal.bridges.js.z1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                JsVkBrowserCoreBridge.resworbkvmoca(this.f52328a, context, str2, str, str3, dialogInterface, i10);
            }
        }).setNegativeButton(R.string.vk_apps_download_cancel, new DialogInterface.OnClickListener() { // from class: com.vk.superapp.browser.internal.bridges.js.g2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                JsVkBrowserCoreBridge.resworbkvmoca(this.f52233a, dialogInterface, i10);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.vk.superapp.browser.internal.bridges.js.h2
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                JsVkBrowserCoreBridge.resworbkvmoca(this.f52239a, dialogInterface);
            }
        }).show();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, Context context, String str, String str2, String str3, DialogInterface dialogInterface, int i10) {
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNull(str2);
        Intrinsics.checkNotNull(str3);
        jsVkBrowserCoreBridge.resworbkvmoca(context, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, DialogInterface dialogInterface, int i10) {
        WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.DOWNLOAD_FILE, VkAppsErrors.Client.USER_DENIED, null, null, null, null, 60, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, DialogInterface dialogInterface) {
        WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.DOWNLOAD_FILE, VkAppsErrors.Client.USER_DENIED, null, null, null, null, 60, null);
    }

    private final void resworbkvmoca(final Context context, final String str, final String str2, final String str3) {
        Function0<Unit> function0 = new Function0() { // from class: com.vk.superapp.browser.internal.bridges.js.l1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsVkBrowserCoreBridge.resworbkvmoca(str, context, str2, this, str3);
            }
        };
        if (OsUtil.isAtLeastUpsideDownCake()) {
            function0.invoke();
            return;
        }
        PermissionHelper permissionHelper = PermissionHelper.INSTANCE;
        String[] storagePermissions = permissionHelper.getStoragePermissions();
        int i10 = com.vk.permission.R.string.vk_permissions_storage;
        permissionHelper.checkAndRequestPermissionsWithCallback(context, storagePermissions, i10, i10, function0, new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.m1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsVkBrowserCoreBridge.resworbkvmoca(this.f52270a, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(String str, Context context, String str2, final JsVkBrowserCoreBridge jsVkBrowserCoreBridge, final String str3) {
        Observable<Pair<Boolean, Integer>> observableAndThen;
        if (URLUtil.isValidUrl(str)) {
            observableAndThen = SuperAppDownloadUtils.INSTANCE.downloadFile(context, str, str2);
        } else {
            observableAndThen = SuperAppDownloadUtils.INSTANCE.saveBase64(context, str, str2).andThen(Observable.just(TuplesKt.to(Boolean.TRUE, 100)));
        }
        final Function1 function1 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.s1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsVkBrowserCoreBridge.resworbkvmoca(this.f52299a, str3, (Pair) obj);
            }
        };
        Consumer<? super Pair<Boolean, Integer>> consumer = new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.t1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                JsVkBrowserCoreBridge.resworbkvmocc(function1, obj);
            }
        };
        final Function1 function2 = new Function1() { // from class: com.vk.superapp.browser.internal.bridges.js.u1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JsVkBrowserCoreBridge.resworbkvmocb(this.f52309a, (Throwable) obj);
            }
        };
        Disposable disposableSubscribe = observableAndThen.subscribe(consumer, new Consumer() { // from class: com.vk.superapp.browser.internal.bridges.js.v1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                JsVkBrowserCoreBridge.resworbkvmocd(function2, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
        WebAppAutoDisposableKt.disposeOnDestroyOf(disposableSubscribe, presenter != null ? presenter.getResworbkvmocs() : null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, String str, Pair pair) {
        if (((Boolean) pair.getFirst()).booleanValue()) {
            WebAppBridge.DefaultImpls.sendEventSuccess$default(jsVkBrowserCoreBridge, JsApiMethodType.DOWNLOAD_FILE, BaseWebBridge.INSTANCE.createSuccessData(), str, null, 8, null);
        } else {
            WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.DOWNLOAD_FILE, VkAppsErrors.Client.UNKNOWN_ERROR, String.valueOf(((Number) pair.getSecond()).intValue()), null, null, null, 56, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        WebAppBridge.DefaultImpls.sendEventFailed$default(jsVkBrowserCoreBridge, JsApiMethodType.DOWNLOAD_FILE, VkAppsErrors.Client.USER_DENIED, null, null, null, null, 60, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(JsVkBrowserCoreBridge jsVkBrowserCoreBridge, boolean z10) throws JSONException {
        VkUiBrowserPresenter presenter = jsVkBrowserCoreBridge.getPresenter();
        VkUiBrowserView view = presenter != null ? presenter.getResworbkvmocs() : null;
        if (view != null) {
            boolean swipeToCloseEnabled = view.setSwipeToCloseEnabled(z10);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("result", swipeToCloseEnabled);
            WebAppBridge.DefaultImpls.sendEventSuccess$default(jsVkBrowserCoreBridge, JsApiMethodType.SWIPE_TO_CLOSE, jSONObject, null, null, 12, null);
        } else {
            jsVkBrowserCoreBridge.sendEventFailed(JsApiMethodType.SWIPE_TO_CLOSE);
        }
        return Unit.INSTANCE;
    }
}
