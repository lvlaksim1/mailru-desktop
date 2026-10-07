package ru.mail.authorizationsdk.feature.captcha;

import android.content.Context;
import android.os.Bundle;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.FunctionKeyMeta;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.captcha.ludochka.LudochkaEvent;
import ru.mail.authorizationsdk.ui.kit.statusbar.ChangeStatusBarColorKt;
import ru.mail.compose.theme.CustomColors;
import ru.mail.compose.theme.MailThemeKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"LudwigCaptchaRoute", "", "viewModel", "Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel;", "onResult", "Lkotlin/Function2;", "", "Landroid/os/Bundle;", "(Lru/mail/authorizationsdk/feature/captcha/WebCaptchaComposeViewModel;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "authorizationsdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLudwigCaptchaRoute.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LudwigCaptchaRoute.kt\nru/mail/authorizationsdk/feature/captcha/LudwigCaptchaRouteKt\n+ 2 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 3 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 4 Effects.kt\nandroidx/compose/runtime/DisposableEffectScope\n*L\n1#1,57:1\n1047#2,6:58\n1047#2,6:64\n1047#2,6:71\n1047#2,6:77\n75#3:70\n68#4,5:83\n*S KotlinDebug\n*F\n+ 1 LudwigCaptchaRoute.kt\nru/mail/authorizationsdk/feature/captcha/LudwigCaptchaRouteKt\n*L\n25#1:58,6\n28#1:64,6\n35#1:71,6\n42#1:77,6\n31#1:70\n42#1:83,5\n*E\n"})
public final class LudwigCaptchaRouteKt {
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 2037, key = -1695676847, startOffset = 628)
    @Composable
    public static final void LudwigCaptchaRoute(@NotNull final WebCaptchaComposeViewModel viewModel, @NotNull final Function2<? super String, ? super Bundle, Unit> onResult, @Nullable Composer composer, final int i10) {
        int i11;
        Composer composer2;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1695676847);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(viewModel) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(onResult) ? 32 : 16;
        }
        int i12 = i11;
        if (composerStartRestartGroup.shouldExecute((i12 & 19) != 18, i12 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1695676847, i12, -1, "ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaRoute (LudwigCaptchaRoute.kt:19)");
            }
            State stateCollectAsState = SnapshotStateKt.collectAsState(viewModel.isLoading(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsState2 = SnapshotStateKt.collectAsState(viewModel.getWebCaptchaState(), null, composerStartRestartGroup, 0, 1);
            int i13 = i12 & 14;
            boolean z10 = i13 == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: ru.mail.authorizationsdk.feature.captcha.a
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LudwigCaptchaRouteKt.LudwigCaptchaRoute$lambda$0$0(viewModel, (LudochkaEvent) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            boolean z11 = i13 == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: ru.mail.authorizationsdk.feature.captcha.b
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LudwigCaptchaRouteKt.LudwigCaptchaRoute$lambda$1$0(viewModel, (Context) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function1 function2 = (Function1) objRememberedValue2;
            ChangeStatusBarColorKt.ChangeStatusAndNavBarColor(ColorKt.m5511toArgb8_81llA(((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15404getBgColor0d7_KjU()), false, viewModel.getStatusNavBarHelper(), composerStartRestartGroup, 0, 2);
            boolean z12 = (i13 == 4) | ((i12 & 112) == 32);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new LudwigCaptchaRouteKt$LudwigCaptchaRoute$1$1(viewModel, onResult, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.LaunchedEffect(viewModel, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue3, composerStartRestartGroup, i13);
            boolean z13 = i13 == 4;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z13 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: ru.mail.authorizationsdk.feature.captcha.c
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LudwigCaptchaRouteKt.LudwigCaptchaRoute$lambda$3$0(viewModel, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.DisposableEffect(viewModel, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, i13);
            LudwigCaptchaScreenKt.LudwigCaptchaScreen(((Boolean) stateCollectAsState.getValue()).booleanValue(), function2, (WebCaptchaComposeViewModel.WebCaptchaState) stateCollectAsState2.getValue(), viewModel.getConfig().isDomStorageEnabled(), viewModel.getConfig().isTextZoomEnabled(), viewModel.getWebViewClient(), viewModel.getImitationHost(), viewModel.getAnalytics(), function1, viewModel.getLudochkaConfig(), composerStartRestartGroup, 0, 0);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ru.mail.authorizationsdk.feature.captcha.d
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LudwigCaptchaRouteKt.LudwigCaptchaRoute$lambda$4(viewModel, onResult, i10, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaRoute$lambda$0$0(WebCaptchaComposeViewModel webCaptchaComposeViewModel, LudochkaEvent it) {
        Intrinsics.checkNotNullParameter(it, "it");
        webCaptchaComposeViewModel.onEvent(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaRoute$lambda$1$0(WebCaptchaComposeViewModel webCaptchaComposeViewModel, Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        webCaptchaComposeViewModel.loadingPageFromFile(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult LudwigCaptchaRoute$lambda$3$0(final WebCaptchaComposeViewModel webCaptchaComposeViewModel, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaRouteKt$LudwigCaptchaRoute$lambda$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                webCaptchaComposeViewModel.onDetach();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LudwigCaptchaRoute$lambda$4(WebCaptchaComposeViewModel webCaptchaComposeViewModel, Function2 function2, int i10, Composer composer, int i11) {
        LudwigCaptchaRoute(webCaptchaComposeViewModel, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return Unit.INSTANCE;
    }
}
