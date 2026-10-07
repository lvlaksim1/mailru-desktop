package ru.mail.authorizationsdk.feature.secondfactor.presentation;

import android.content.Context;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.FunctionKeyMeta;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.res.StringResources_androidKt;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
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
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.feature.common.webview.CommonWebViewClient;
import ru.mail.authorizationsdk.feature.common.webview.LoadPageAgainState;
import ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.compose.component.toolbar.SmallToolbarKt;
import ru.mail.compose.component.toolbar.ToolbarConfig;
import ru.mail.compose.component.webview.WebViewCommonKt;
import ru.mail.compose.theme.CustomColors;
import ru.mail.compose.theme.MailThemeKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a_\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010H\u0001¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"SecondFactorScreen", "", "analytics", "Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;", "url", "", "isRecaptchaMode", "", "webViewClient", "Lru/mail/authorizationsdk/feature/common/webview/CommonWebViewClient;", "isNeedLoadPageAgain", "Lru/mail/authorizationsdk/feature/common/webview/LoadPageAgainState;", "isScaleDisabled", "onBackClick", "Lkotlin/Function0;", "onWebViewReady", "Lkotlin/Function1;", "Landroid/content/Context;", "(Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;Ljava/lang/String;ZLru/mail/authorizationsdk/feature/common/webview/CommonWebViewClient;Lru/mail/authorizationsdk/feature/common/webview/LoadPageAgainState;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "authorizationsdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSecondFactorScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SecondFactorScreen.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondFactorScreenKt\n+ 2 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 3 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,104:1\n1047#2,6:105\n1047#2,6:111\n1047#2,6:117\n1047#2,6:123\n1047#2,6:132\n75#3:129\n75#3:130\n75#3:131\n1#4:138\n*S KotlinDebug\n*F\n+ 1 SecondFactorScreen.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondFactorScreenKt\n*L\n36#1:105,6\n37#1:111,6\n46#1:117,6\n48#1:123,6\n73#1:132,6\n69#1:129\n70#1:130\n71#1:131\n*E\n"})
public final class SecondFactorScreenKt {

    /* JADX INFO: renamed from: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorScreenKt$SecondFactorScreen$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorScreenKt$SecondFactorScreen$2", f = "SecondFactorScreen.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LoadPageAgainState $isNeedLoadPageAgain;
        final /* synthetic */ String $url;
        final /* synthetic */ Ref.ObjectRef<WebView> $webView;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LoadPageAgainState loadPageAgainState, Ref.ObjectRef<WebView> objectRef, String str, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$isNeedLoadPageAgain = loadPageAgainState;
            this.$webView = objectRef;
            this.$url = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$isNeedLoadPageAgain, this.$webView, this.$url, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i10 = this.label;
            if (i10 == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowFilterNotNull = FlowKt.filterNotNull(this.$isNeedLoadPageAgain.isNeedLoadPageAgain());
                final Ref.ObjectRef<WebView> objectRef = this.$webView;
                final String str = this.$url;
                FlowCollector flowCollector = new FlowCollector() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorScreenKt.SecondFactorScreen.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Boolean) obj2).booleanValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(boolean z10, Continuation<? super Unit> continuation) {
                        WebView webView;
                        if (z10 && (webView = objectRef.element) != null) {
                            webView.loadUrl(str);
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flowFilterNotNull.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 4092, key = 419565086, startOffset = 1156)
    @Composable
    public static final void SecondFactorScreen(@NotNull final SecondFactorAnalytics analytics, @NotNull final String url, final boolean z10, @NotNull final CommonWebViewClient webViewClient, @NotNull final LoadPageAgainState isNeedLoadPageAgain, final boolean z11, @NotNull final Function0<Unit> onBackClick, @NotNull final Function1<? super Context, Unit> onWebViewReady, @Nullable Composer composer, final int i10) {
        int i11;
        Composer composer2;
        Intrinsics.checkNotNullParameter(analytics, "analytics");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(webViewClient, "webViewClient");
        Intrinsics.checkNotNullParameter(isNeedLoadPageAgain, "isNeedLoadPageAgain");
        Intrinsics.checkNotNullParameter(onBackClick, "onBackClick");
        Intrinsics.checkNotNullParameter(onWebViewReady, "onWebViewReady");
        Composer composerStartRestartGroup = composer.startRestartGroup(419565086);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(analytics) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(url) ? 32 : 16;
        }
        if ((i10 & KyberEngine.KyberPolyBytes) == 0) {
            i11 |= composerStartRestartGroup.changed(z10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changed(webViewClient) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(isNeedLoadPageAgain) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(z11) ? 131072 : ArrayPool.STANDARD_BUFFER_SIZE_BYTES;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(onBackClick) ? 1048576 : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(onWebViewReady) ? RemoteFilesRepository.BYTE_ARRAY_SIZE : 4194304;
        }
        if (composerStartRestartGroup.shouldExecute((4793491 & i11) != 4793490, i11 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(419565086, i11, -1, "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorScreen (SecondFactorScreen.kt:34)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.h
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SecondFactorScreenKt.SecondFactorScreen$lambda$0$0(analytics);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final Function0 function0 = (Function0) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.i
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SecondFactorScreenKt.SecondFactorScreen$lambda$1$0(z10, (PaddingValues) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final Function1 function1 = (Function1) objRememberedValue2;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.j
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SecondFactorScreenKt.SecondFactorScreen$lambda$2$0(analytics);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final Function0 function2 = (Function0) objRememberedValue3;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.k
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SecondFactorScreenKt.SecondFactorScreen$lambda$3$0(analytics, onBackClick);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final Function0 function3 = (Function0) objRememberedValue4;
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            BackHandlerKt.BackHandler(false, new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.l
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SecondFactorScreenKt.SecondFactorScreen$lambda$4(objectRef, onBackClick);
                }
            }, composerStartRestartGroup, 0, 1);
            EffectsKt.LaunchedEffect(Unit.INSTANCE, new AnonymousClass2(isNeedLoadPageAgain, objectRef, url, null), composerStartRestartGroup, 6);
            String strStringResource = StringResources_androidKt.stringResource(R.string.code_auth_webview_title, composerStartRestartGroup, 0);
            ToolbarConfig toolbarConfig = new ToolbarConfig(Integer.valueOf(R.drawable.ic_left), Color.m5448boximpl(((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU()), Color.m5448boximpl(((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU()), Color.m5448boximpl(((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15404getBgColor0d7_KjU()), null);
            boolean z12 = (i11 & 3670016) == 1048576;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.m
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SecondFactorScreenKt.SecondFactorScreen$lambda$5$0(onBackClick);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            composer2 = composerStartRestartGroup;
            SmallToolbarKt.SmallTopAppBar(strStringResource, toolbarConfig, null, false, (Function0) objRememberedValue5, ComposableLambdaKt.rememberComposableLambda(-357676717, true, new Function3() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.n
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SecondFactorScreenKt.SecondFactorScreen$lambda$6(function1, webViewClient, function0, function3, function2, objectRef, onWebViewReady, z11, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composer2, 196608, 12);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.o
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SecondFactorScreenKt.SecondFactorScreen$lambda$7(analytics, url, z10, webViewClient, isNeedLoadPageAgain, z11, onBackClick, onWebViewReady, i10, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorScreen$lambda$0$0(SecondFactorAnalytics secondFactorAnalytics) {
        secondFactorAnalytics.secondFactorUpdateWebViewDialogShowed();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier SecondFactorScreen$lambda$1$0(boolean z10, PaddingValues it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Modifier modifierConsumeWindowInsets = WindowInsetsPaddingKt.consumeWindowInsets(PaddingKt.m1018paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, it.getTop(), 0.0f, 0.0f, 13, null), it);
        return !z10 ? WindowInsetsPadding_androidKt.imePadding(modifierConsumeWindowInsets) : modifierConsumeWindowInsets;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorScreen$lambda$2$0(SecondFactorAnalytics secondFactorAnalytics) {
        secondFactorAnalytics.secondFactorUpdateWebViewDialogPositive();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorScreen$lambda$3$0(SecondFactorAnalytics secondFactorAnalytics, Function0 function0) {
        secondFactorAnalytics.secondFactorUpdateWebViewDialogNegative();
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit SecondFactorScreen$lambda$4(Ref.ObjectRef objectRef, Function0 function0) {
        WebView webView = (WebView) objectRef.element;
        if (webView == null || !webView.canGoBack()) {
            function0.invoke();
        } else {
            WebView webView2 = (WebView) objectRef.element;
            if (webView2 != null) {
                webView2.goBack();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorScreen$lambda$5$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 4090, key = -357676717, startOffset = 2970)
    @Composable
    public static final Unit SecondFactorScreen$lambda$6(Function1 function1, CommonWebViewClient commonWebViewClient, Function0 function0, Function0 function2, Function0 function3, final Ref.ObjectRef objectRef, final Function1 function4, final boolean z10, PaddingValues it, Composer composer, int i10) {
        int i11;
        Intrinsics.checkNotNullParameter(it, "it");
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer.changed(it) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if (composer.shouldExecute((i11 & 19) != 18, i11 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-357676717, i11, -1, "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorScreen.<anonymous> (SecondFactorScreen.kt:74)");
            }
            WebViewCommonKt.WebViewCommon((Modifier) function1.invoke(it), commonWebViewClient, new Function1() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.p
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SecondFactorScreenKt.SecondFactorScreen$lambda$6$0(objectRef, function4, z10, (WebView) obj);
                }
            }, ComposableSingletons$SecondFactorScreenKt.INSTANCE.m14773getLambda$1350548698$authorizationsdk_release(), new Function1() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.q
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SecondFactorScreenKt.SecondFactorScreen$lambda$6$1(objectRef, (View) obj);
                }
            }, function0, function2, function3, null, composer, 14355456, 256);
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
    public static final Unit SecondFactorScreen$lambda$6$0(Ref.ObjectRef objectRef, Function1 function1, boolean z10, WebView WebViewCommon) {
        Intrinsics.checkNotNullParameter(WebViewCommon, "$this$WebViewCommon");
        WebSettings settings = WebViewCommon.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setTextZoom(100);
        settings.setSupportZoom(false);
        if (z10) {
            WebViewCommon.setInitialScale(1);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
        }
        objectRef.element = WebViewCommon;
        Context context = WebViewCommon.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        function1.invoke(context);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit SecondFactorScreen$lambda$6$1(Ref.ObjectRef objectRef, View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        if ((view instanceof WebView ? (WebView) view : null) != null) {
            objectRef.element = view;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorScreen$lambda$7(SecondFactorAnalytics secondFactorAnalytics, String str, boolean z10, CommonWebViewClient commonWebViewClient, LoadPageAgainState loadPageAgainState, boolean z11, Function0 function0, Function1 function1, int i10, Composer composer, int i11) {
        SecondFactorScreen(secondFactorAnalytics, str, z10, commonWebViewClient, loadPageAgainState, z11, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return Unit.INSTANCE;
    }
}
