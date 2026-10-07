package ru.mail.authorizationsdk.feature.secondfactor.presentation;

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
import androidx.compose.runtime.internal.FunctionKeyMeta;
import androidx.compose.ui.graphics.ColorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.ui.kit.statusbar.ChangeStatusBarColorKt;
import ru.mail.authorizationsdk.ui.utils.DebounceKt;
import ru.mail.compose.theme.CustomColors;
import ru.mail.compose.theme.MailThemeKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a1\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u00010\u0005H\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"SecondFactorRoute", "", "viewModel", "Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel;", "onResult", "Lkotlin/Function2;", "", "Landroid/os/Bundle;", "(Lru/mail/authorizationsdk/feature/secondfactor/presentation/SecondStepViewModel;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "authorizationsdk_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSecondFactorRoute.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SecondFactorRoute.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondFactorRouteKt\n+ 2 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 3 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 4 Effects.kt\nandroidx/compose/runtime/DisposableEffectScope\n*L\n1#1,47:1\n1047#2,6:48\n1047#2,6:54\n1047#2,6:61\n1047#2,6:67\n75#3:60\n68#4,5:73\n*S KotlinDebug\n*F\n+ 1 SecondFactorRoute.kt\nru/mail/authorizationsdk/feature/secondfactor/presentation/SecondFactorRouteKt\n*L\n20#1:48,6\n21#1:54,6\n28#1:61,6\n35#1:67,6\n24#1:60\n35#1:73,5\n*E\n"})
public final class SecondFactorRouteKt {
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 1619, key = -574129052, startOffset = 530)
    @Composable
    public static final void SecondFactorRoute(@NotNull final SecondStepViewModel viewModel, @NotNull final Function2<? super String, ? super Bundle, Unit> onResult, @Nullable Composer composer, final int i10) {
        int i11;
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Composer composerStartRestartGroup = composer.startRestartGroup(-574129052);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(viewModel) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(onResult) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i11 & 19) != 18, i11 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-574129052, i11, -1, "ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorRoute (SecondFactorRoute.kt:17)");
            }
            int i12 = i11 & 14;
            boolean z10 = i12 == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.b
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return SecondFactorRouteKt.SecondFactorRoute$lambda$0$0(viewModel);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function0 function0 = (Function0) objRememberedValue;
            boolean z11 = i12 == 4;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z11 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.c
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SecondFactorRouteKt.SecondFactorRoute$lambda$1$0(viewModel, (Context) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Function1 function1 = (Function1) objRememberedValue2;
            ChangeStatusBarColorKt.ChangeStatusAndNavBarColor(ColorKt.m5511toArgb8_81llA(((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15404getBgColor0d7_KjU()), false, viewModel.getStatusNavBarHelper(), composerStartRestartGroup, 0, 2);
            boolean z12 = (i12 == 4) | ((i11 & 112) == 32);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z12 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new SecondFactorRouteKt$SecondFactorRoute$1$1(viewModel, onResult, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            EffectsKt.LaunchedEffect(viewModel, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue3, composerStartRestartGroup, i12);
            boolean z13 = i12 == 4;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z13 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.d
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SecondFactorRouteKt.SecondFactorRoute$lambda$3$0(viewModel, (DisposableEffectScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.DisposableEffect(viewModel, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, i12);
            SecondFactorScreenKt.SecondFactorScreen(viewModel.getAnalytics(), viewModel.getMainUrl(), viewModel.getIsRecaptchaMode(), viewModel.getWebViewClient(), viewModel, viewModel.getConfig().isScaleDisabled(), function0, function1, composerStartRestartGroup, (i11 << 12) & 57344);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.e
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SecondFactorRouteKt.SecondFactorRoute$lambda$4(viewModel, onResult, i10, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorRoute$lambda$0$0(final SecondStepViewModel secondStepViewModel) {
        DebounceKt.click(new Function0() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.f
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SecondFactorRouteKt.SecondFactorRoute$lambda$0$0$0(secondStepViewModel);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorRoute$lambda$0$0$0(SecondStepViewModel secondStepViewModel) {
        secondStepViewModel.onBackClick();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorRoute$lambda$1$0(SecondStepViewModel secondStepViewModel, Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        secondStepViewModel.onWebViewReady(ctx);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DisposableEffectResult SecondFactorRoute$lambda$3$0(final SecondStepViewModel secondStepViewModel, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        return new DisposableEffectResult() { // from class: ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorRouteKt$SecondFactorRoute$lambda$3$0$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                secondStepViewModel.onDestroy();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SecondFactorRoute$lambda$4(SecondStepViewModel secondStepViewModel, Function2 function2, int i10, Composer composer, int i11) {
        SecondFactorRoute(secondStepViewModel, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return Unit.INSTANCE;
    }
}
