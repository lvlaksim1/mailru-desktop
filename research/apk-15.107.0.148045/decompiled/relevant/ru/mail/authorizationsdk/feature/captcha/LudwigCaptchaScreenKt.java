package ru.mail.authorizationsdk.feature.captcha;

import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.FunctionKeyMeta;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.vk.superapp.api.internal.requests.utils.WebRequestHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.http2.Http2Connection;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaConfig;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaEvent;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaMediator;
import ru.mail.authorizationsdk.ui.kit.progressbar.AuthFlowProgressBarKt;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.compose.component.webview.WebViewCommonKt;
import ru.mail.compose.theme.ColorsKt;
import ru.mail.r7editor.impl.presentation.webview.R7WebViewConfigInjector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a{\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0001¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"LudwigCaptchaScreen", "", "isLoading", "", "onInitLocalPage", "Lkotlin/Function1;", "Landroid/content/Context;", "state", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState;", "isDomStorageEnabled", "isTextZoomDisabled", "webViewClient", "Landroid/webkit/WebViewClient;", "imitationHost", "", "webViewCaptchaAnalytics", "Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "onEvent", "Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaEvent;", "ludochkaConfig", "Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;", "(ZLkotlin/jvm/functions/Function1;Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel$WebCaptchaState;ZZLandroid/webkit/WebViewClient;Ljava/lang/String;Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;Lkotlin/jvm/functions/Function1;Lru/mail/authorizationsdk/feature/captcha/ludochka/LudochkaConfig;Landroidx/compose/runtime/Composer;II)V", "authorizationsdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLudwigCaptchaScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LudwigCaptchaScreen.kt\nru/mail/authorizationsdk/feature/captcha/LudwigCaptchaScreenKt\n+ 2 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 3 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,86:1\n1047#2,6:87\n1047#2,6:94\n1047#2,6:100\n1047#2,6:106\n75#3:93\n1#4:112\n*S KotlinDebug\n*F\n+ 1 LudwigCaptchaScreen.kt\nru/mail/authorizationsdk/feature/captcha/LudwigCaptchaScreenKt\n*L\n32#1:87,6\n74#1:94,6\n77#1:100,6\n81#1:106,6\n33#1:93\n*E\n"})
public final class LudwigCaptchaScreenKt {

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaScreenKt$LudwigCaptchaScreen$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaScreenKt$LudwigCaptchaScreen$2", f = "LudwigCaptchaScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $imitationHost;
        final /* synthetic */ WebCaptchaComposeViewModel.WebCaptchaState $state;
        final /* synthetic */ Ref.ObjectRef<WebView> $webViewSaved;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(WebCaptchaComposeViewModel.WebCaptchaState webCaptchaState, Ref.ObjectRef<WebView> objectRef, String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$state = webCaptchaState;
            this.$webViewSaved = objectRef;
            this.$imitationHost = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$state, this.$webViewSaved, this.$imitationHost, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            WebView webView;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            WebCaptchaComposeViewModel.WebCaptchaState webCaptchaState = this.$state;
            if ((webCaptchaState instanceof WebCaptchaComposeViewModel.WebCaptchaState.LoadedLocalPage) && (webView = this.$webViewSaved.element) != null) {
                webView.loadDataWithBaseURL(this.$imitationHost, ((WebCaptchaComposeViewModel.WebCaptchaState.LoadedLocalPage) webCaptchaState).getUrl(), WebRequestHelper.MIME_HTML, R7WebViewConfigInjector.UTF_8, null);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 3282, key = 1985237892, startOffset = 910)
    @Composable
    public static final void LudwigCaptchaScreen(final boolean z10, @NotNull final Function1<? super Context, Unit> onInitLocalPage, @Nullable final WebCaptchaComposeViewModel.WebCaptchaState webCaptchaState, final boolean z11, final boolean z12, @NotNull final WebViewClient webViewClient, @NotNull final String imitationHost, @Nullable LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents, @NotNull final Function1<? super LudochkaEvent, Unit> onEvent, @NotNull final LudochkaConfig ludochkaConfig, @Nullable Composer composer, final int i10, final int i11) {
        int i12;
        final boolean z13;
        final boolean z14;
        final LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents2;
        Intrinsics.checkNotNullParameter(onInitLocalPage, "onInitLocalPage");
        Intrinsics.checkNotNullParameter(webViewClient, "webViewClient");
        Intrinsics.checkNotNullParameter(imitationHost, "imitationHost");
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
        Intrinsics.checkNotNullParameter(ludochkaConfig, "ludochkaConfig");
        Composer composerStartRestartGroup = composer.startRestartGroup(1985237892);
        if ((i10 & 6) == 0) {
            i12 = (composerStartRestartGroup.changed(z10) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(onInitLocalPage) ? 32 : 16;
        }
        if ((i10 & KyberEngine.KyberPolyBytes) == 0) {
            i12 |= composerStartRestartGroup.changed(webCaptchaState) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            z13 = z11;
            i12 |= composerStartRestartGroup.changed(z13) ? 2048 : 1024;
        } else {
            z13 = z11;
        }
        if ((i10 & 24576) == 0) {
            z14 = z12;
            i12 |= composerStartRestartGroup.changed(z14) ? 16384 : 8192;
        } else {
            z14 = z12;
        }
        if ((196608 & i10) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(webViewClient) ? 131072 : ArrayPool.STANDARD_BUFFER_SIZE_BYTES;
        }
        if ((1572864 & i10) == 0) {
            i12 |= composerStartRestartGroup.changed(imitationHost) ? 1048576 : 524288;
        }
        int i13 = i11 & 128;
        int i14 = 12582912;
        if (i13 != 0) {
            i12 |= i14;
        } else if ((i10 & 12582912) == 0) {
            i14 = (i10 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) == 0 ? composerStartRestartGroup.changed(ludwigCaptchaAnalyticEvents) : composerStartRestartGroup.changedInstance(ludwigCaptchaAnalyticEvents) ? RemoteFilesRepository.BYTE_ARRAY_SIZE : 4194304;
            i12 |= i14;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(onEvent) ? 67108864 : MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= composerStartRestartGroup.changed(ludochkaConfig) ? SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING : SQLiteDatabase.CREATE_IF_NECESSARY;
        }
        if (composerStartRestartGroup.shouldExecute((i12 & 306783379) != 306783378, i12 & 1)) {
            final LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents3 = i13 != 0 ? null : ludwigCaptchaAnalyticEvents;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1985237892, i12, -1, "ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaScreen (LudwigCaptchaScreen.kt:30)");
            }
            int i15 = i12 & 234881024;
            boolean z15 = i15 == 67108864;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z15 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ru.mail.authorizationsdk.feature.captcha.e
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$0$0(onEvent);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            BackHandlerKt.BackHandler(false, (Function0) objRememberedValue, composerStartRestartGroup, 0, 1);
            final Context context = (Context) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalContext());
            int i16 = i12;
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            EffectsKt.LaunchedEffect(webCaptchaState, new AnonymousClass2(webCaptchaState, objectRef, imitationHost, null), composerStartRestartGroup, (i16 >> 6) & 14);
            Function1 function1 = new Function1() { // from class: ru.mail.authorizationsdk.feature.captcha.f
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$1(ludochkaConfig, onEvent, onInitLocalPage, context, objectRef, z13, z14, (WebView) obj);
                }
            };
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1620909649, true, new Function3() { // from class: ru.mail.authorizationsdk.feature.captcha.g
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$2(z10, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54);
            Function1 function2 = new Function1() { // from class: ru.mail.authorizationsdk.feature.captcha.h
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$3(objectRef, (View) obj);
                }
            };
            int i17 = i16 & 29360128;
            boolean z16 = i17 == 8388608 || ((i16 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 && composerStartRestartGroup.changedInstance(ludwigCaptchaAnalyticEvents3));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z16 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: ru.mail.authorizationsdk.feature.captcha.i
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$4$0(ludwigCaptchaAnalyticEvents3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function0 function0 = (Function0) objRememberedValue2;
            boolean z17 = (i15 == 67108864) | (i17 == 8388608 || ((i16 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 && composerStartRestartGroup.changedInstance(ludwigCaptchaAnalyticEvents3)));
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z17 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: ru.mail.authorizationsdk.feature.captcha.j
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$5$0(ludwigCaptchaAnalyticEvents3, onEvent);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Function0 function3 = (Function0) objRememberedValue3;
            boolean z18 = i17 == 8388608 || ((i16 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 && composerStartRestartGroup.changedInstance(ludwigCaptchaAnalyticEvents3));
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z18 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: ru.mail.authorizationsdk.feature.captcha.k
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$6$0(ludwigCaptchaAnalyticEvents3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents4 = ludwigCaptchaAnalyticEvents3;
            WebViewCommonKt.WebViewCommon(null, webViewClient, function1, composableLambdaRememberComposableLambda, function2, function0, function3, (Function0) objRememberedValue4, null, composerStartRestartGroup, ((i16 >> 12) & 112) | 3072, 257);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ludwigCaptchaAnalyticEvents2 = ludwigCaptchaAnalyticEvents4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            ludwigCaptchaAnalyticEvents2 = ludwigCaptchaAnalyticEvents;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ru.mail.authorizationsdk.feature.captcha.l
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LudwigCaptchaScreenKt.LudwigCaptchaScreen$lambda$7(z10, onInitLocalPage, webCaptchaState, z11, z12, webViewClient, imitationHost, ludwigCaptchaAnalyticEvents2, onEvent, ludochkaConfig, i10, i11, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaScreen$lambda$0$0(Function1 function1) {
        function1.invoke(LudochkaEvent.Cancel.INSTANCE);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit LudwigCaptchaScreen$lambda$1(LudochkaConfig ludochkaConfig, Function1 function1, Function1 function2, Context context, Ref.ObjectRef objectRef, boolean z10, boolean z11, WebView WebViewCommon) {
        Intrinsics.checkNotNullParameter(WebViewCommon, "$this$WebViewCommon");
        WebSettings settings = WebViewCommon.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setDomStorageEnabled(z10);
        if (z11) {
            settings.setTextZoom(100);
            settings.setSupportZoom(false);
            WebViewCommon.setInitialScale(1);
            WebViewCommon.getSettings().setUseWideViewPort(true);
            WebViewCommon.getSettings().setLoadWithOverviewMode(true);
        }
        WebViewCommon.addJavascriptInterface(new LudochkaMediator(ludochkaConfig, new LudwigCaptchaScreenKt$sam$ru_mail_authorizationsdk_feature_captcha_ludochka_LudochkaEventListener$0(function1)), "LudochkaMediator");
        function2.invoke(context);
        objectRef.element = WebViewCommon;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 2755, key = 1620909649, startOffset = 2529)
    @Composable
    public static final Unit LudwigCaptchaScreen$lambda$2(boolean z10, Modifier it, Composer composer, int i10) {
        Composer composer2;
        Intrinsics.checkNotNullParameter(it, "it");
        if (composer.shouldExecute((i10 & 17) != 16, i10 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1620909649, i10, -1, "ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaScreen.<anonymous> (LudwigCaptchaScreen.kt:65)");
            }
            if (z10) {
                composer.startReplaceGroup(202078998);
                composer2 = composer;
                AuthFlowProgressBarKt.m14817AuthFlowProgressBarvc5YOHI(ColorsKt.getColorTextPrimaryLight(), 0L, false, null, 5000L, composer2, 24576, 14);
            } else {
                composer2 = composer;
                composer2.startReplaceGroup(199534673);
            }
            composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit LudwigCaptchaScreen$lambda$3(Ref.ObjectRef objectRef, View it) {
        T t10;
        WebView webView;
        Intrinsics.checkNotNullParameter(it, "it");
        if (it instanceof WebView) {
            webView = (WebView) it;
        } else {
            t10 = 0;
        }
        if (t10 != 0) {
            t10 = webView;
            objectRef.element = t10;
        }
        t10 = webView;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaScreen$lambda$4$0(LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents) {
        if (ludwigCaptchaAnalyticEvents != null) {
            ludwigCaptchaAnalyticEvents.updateWebViewDialogShowed();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaScreen$lambda$5$0(LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents, Function1 function1) {
        if (ludwigCaptchaAnalyticEvents != null) {
            ludwigCaptchaAnalyticEvents.updateWebViewDialogNegativeButtonClicked();
        }
        function1.invoke(LudochkaEvent.Cancel.INSTANCE);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaScreen$lambda$6$0(LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents) {
        if (ludwigCaptchaAnalyticEvents != null) {
            ludwigCaptchaAnalyticEvents.updateWebViewDialogPositiveButtonClicked();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaScreen$lambda$7(boolean z10, Function1 function1, WebCaptchaComposeViewModel.WebCaptchaState webCaptchaState, boolean z11, boolean z12, WebViewClient webViewClient, String str, LudwigCaptchaAnalyticEvents ludwigCaptchaAnalyticEvents, Function1 function2, LudochkaConfig ludochkaConfig, int i10, int i11, Composer composer, int i12) {
        LudwigCaptchaScreen(z10, function1, webCaptchaState, z11, z12, webViewClient, str, ludwigCaptchaAnalyticEvents, function2, ludochkaConfig, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return Unit.INSTANCE;
    }
}
