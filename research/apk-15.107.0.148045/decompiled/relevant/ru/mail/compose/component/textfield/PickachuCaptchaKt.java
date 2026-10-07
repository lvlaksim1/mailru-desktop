package ru.mail.compose.component.textfield;

import androidx.annotation.DrawableRes;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldDefaults;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.tooling.preview.Preview;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.compose.component.divider.Line1DpDividerKt;
import ru.mail.compose.component.text.HeaderTextKt;
import ru.mail.compose.component.textfield.PickachuCaptchaKt;
import ru.mail.compose.modifier.NodeTestModifierKt;
import ru.mail.compose.theme.CustomColors;
import ru.mail.compose.theme.CustomInterTypography;
import ru.mail.compose.theme.CustomTypography;
import ru.mail.compose.theme.MailTheme;
import ru.mail.compose.theme.MailThemeKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u007f\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00032\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\b\b\u0001\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0013\u001a\u0015\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0016H\u0003¢\u0006\u0002\u0010\u0017\u001a\r\u0010\u0018\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u0019\"\u000e\u0010\u001a\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0003X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"PikachuCaptcha", "", "title", "", "textFieldDesc", "isTest", "", "isError", "textFieldPlaceHolder", "isLoading", "initCode", "onCodeChange", "Lkotlin/Function1;", "captchaBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;", "onRetryBtnClick", "Lkotlin/Function0;", "retryBtnIconRes", "", "(Ljava/lang/String;Ljava/lang/String;ZZLjava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/ImageBitmap;Lkotlin/jvm/functions/Function0;ILandroidx/compose/runtime/Composer;III)V", "ProgressBar", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "PreviewProgressBar", "(Landroidx/compose/runtime/Composer;I)V", PickachuCaptchaKt.PIK_CAPTCHA_IMG_TAG, PickachuCaptchaKt.PIK_CAPTCHA_RETRY_BTN_TAG, PickachuCaptchaKt.PIK_CAPTCHA_ET_TAG, "compose-design-system_release"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPickachuCaptcha.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PickachuCaptcha.kt\nru/mail/compose/component/textfield/PickachuCaptchaKt\n+ 2 Dp.kt\nandroidx/compose/ui/unit/DpKt\n+ 3 Row.kt\nandroidx/compose/foundation/layout/RowKt\n+ 4 Layout.kt\nandroidx/compose/ui/layout/LayoutKt\n+ 5 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 6 Composer.kt\nandroidx/compose/runtime/Updater\n+ 7 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 8 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 9 Box.kt\nandroidx/compose/foundation/layout/BoxKt\n*L\n1#1,186:1\n113#2:187\n113#2:228\n113#2:229\n113#2:244\n113#2:291\n113#2:342\n113#2:343\n113#2:348\n113#2:349\n99#3:188\n96#3,9:189\n106#3:243\n99#3:245\n96#3,9:246\n106#3:305\n80#4,6:198\n87#4,3:213\n90#4,2:222\n94#4:242\n80#4,6:255\n87#4,3:270\n90#4,2:279\n94#4:304\n80#4,6:315\n87#4,3:330\n90#4,2:339\n94#4:346\n391#5,9:204\n400#5:224\n401#5,2:240\n391#5,9:261\n400#5:281\n401#5,2:302\n391#5,9:321\n400#5:341\n401#5,2:344\n4360#6,6:216\n4360#6,6:273\n4360#6,6:333\n1282#7,3:225\n1285#7,3:230\n1282#7,6:233\n1282#7,6:285\n1282#7,6:296\n75#8:239\n75#8:282\n75#8:283\n75#8:284\n75#8:292\n75#8:293\n75#8:294\n75#8:295\n75#8:350\n70#9:306\n68#9,8:307\n77#9:347\n*S KotlinDebug\n*F\n+ 1 PickachuCaptcha.kt\nru/mail/compose/component/textfield/PickachuCaptchaKt\n*L\n62#1:187\n67#1:228\n68#1:229\n104#1:244\n133#1:291\n166#1:342\n168#1:343\n179#1:348\n180#1:349\n58#1:188\n58#1:189,9\n58#1:243\n100#1:245\n100#1:246,9\n100#1:305\n58#1:198,6\n58#1:213,3\n58#1:222,2\n58#1:242\n100#1:255,6\n100#1:270,3\n100#1:279,2\n100#1:304\n161#1:315,6\n161#1:330,3\n161#1:339,2\n161#1:346\n58#1:204,9\n58#1:224\n58#1:240,2\n100#1:261,9\n100#1:281\n100#1:302,2\n161#1:321,9\n161#1:341\n161#1:344,2\n58#1:216,6\n100#1:273,6\n161#1:333,6\n65#1:225,3\n65#1:230,3\n87#1:233,6\n122#1:285,6\n129#1:296,6\n92#1:239\n114#1:282\n117#1:283\n119#1:284\n137#1:292\n138#1:293\n139#1:294\n146#1:295\n152#1:350\n161#1:306\n161#1:307,8\n161#1:347\n*E\n"})
public final class PickachuCaptchaKt {

    @NotNull
    public static final String PIK_CAPTCHA_ET_TAG = "PIK_CAPTCHA_ET_TAG";

    @NotNull
    public static final String PIK_CAPTCHA_IMG_TAG = "PIK_CAPTCHA_IMG_TAG";

    @NotNull
    public static final String PIK_CAPTCHA_RETRY_BTN_TAG = "PIK_CAPTCHA_RETRY_BTN_TAG";

    /* JADX WARN: Code duplicated, block: B:100:0x0135  */
    /* JADX WARN: Code duplicated, block: B:102:0x0139  */
    /* JADX WARN: Code duplicated, block: B:103:0x013c  */
    /* JADX WARN: Code duplicated, block: B:106:0x0144  */
    /* JADX WARN: Code duplicated, block: B:109:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:112:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:113:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:118:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:121:0x021b  */
    /* JADX WARN: Code duplicated, block: B:124:0x023b  */
    /* JADX WARN: Code duplicated, block: B:126:0x0243  */
    /* JADX WARN: Code duplicated, block: B:127:0x0270  */
    /* JADX WARN: Code duplicated, block: B:130:0x02b1  */
    /* JADX WARN: Code duplicated, block: B:131:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:136:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:138:0x0309  */
    /* JADX WARN: Code duplicated, block: B:141:0x0367  */
    /* JADX WARN: Code duplicated, block: B:144:0x0373  */
    /* JADX WARN: Code duplicated, block: B:145:0x0377  */
    /* JADX WARN: Code duplicated, block: B:150:0x03a4  */
    /* JADX WARN: Code duplicated, block: B:153:0x0420  */
    /* JADX WARN: Code duplicated, block: B:154:0x0438  */
    /* JADX WARN: Code duplicated, block: B:157:0x0495  */
    /* JADX WARN: Code duplicated, block: B:158:0x04a1  */
    /* JADX WARN: Code duplicated, block: B:161:0x05c1  */
    /* JADX WARN: Code duplicated, block: B:162:0x05c3  */
    /* JADX WARN: Code duplicated, block: B:169:0x05d5  */
    /* JADX WARN: Code duplicated, block: B:172:0x062a  */
    /* JADX WARN: Code duplicated, block: B:174:0x0630  */
    /* JADX WARN: Code duplicated, block: B:177:0x063f  */
    /* JADX WARN: Code duplicated, block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x007e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0084  */
    /* JADX WARN: Code duplicated, block: B:33:0x0087  */
    /* JADX WARN: Code duplicated, block: B:37:0x008e  */
    /* JADX WARN: Code duplicated, block: B:39:0x0094  */
    /* JADX WARN: Code duplicated, block: B:40:0x0097  */
    /* JADX WARN: Code duplicated, block: B:44:0x009f  */
    /* JADX WARN: Code duplicated, block: B:46:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:47:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:51:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:53:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:54:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:60:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:61:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:65:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:66:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:68:0x00de  */
    /* JADX WARN: Code duplicated, block: B:70:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:77:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:78:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:82:0x0102  */
    /* JADX WARN: Code duplicated, block: B:84:0x0108  */
    /* JADX WARN: Code duplicated, block: B:85:0x010b  */
    /* JADX WARN: Code duplicated, block: B:87:0x0112  */
    /* JADX WARN: Code duplicated, block: B:98:0x0131 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:99:0x0133  */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    public static final void PikachuCaptcha(@NotNull final String title, @NotNull final String textFieldDesc, boolean z10, final boolean z11, @NotNull String textFieldPlaceHolder, final boolean z12, @NotNull String str, @NotNull final Function1<? super String, Unit> onCodeChange, @Nullable ImageBitmap imageBitmap, @NotNull final Function0<Unit> onRetryBtnClick, @DrawableRes final int i10, @Nullable Composer composer, final int i11, final int i12, final int i13) {
        int i14;
        int i15;
        int i16;
        int i17;
        Composer composer2;
        final String str2;
        boolean z13;
        final ImageBitmap imageBitmap2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        boolean z14;
        ImageBitmap imageBitmap3;
        int i18;
        boolean z15;
        int i19;
        Modifier.Companion companion;
        int iHashCode;
        Function0<ComposeUiNode> constructor;
        Composer composerM4589constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Object objRememberedValue;
        Composer.Companion companion2;
        Modifier modifier;
        boolean z16;
        int iHashCode2;
        Function0<ComposeUiNode> constructor2;
        Composer composerM4589constructorimpl2;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2;
        long jM15450getTextSecondary0d7_KjU;
        Object objRememberedValue2;
        Object obj;
        final MutableState mutableState;
        boolean z17;
        Object objRememberedValue3;
        boolean z18;
        Object objRememberedValue4;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        String initCode = str;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(textFieldDesc, "textFieldDesc");
        Intrinsics.checkNotNullParameter(textFieldPlaceHolder, "textFieldPlaceHolder");
        Intrinsics.checkNotNullParameter(initCode, "initCode");
        Intrinsics.checkNotNullParameter(onCodeChange, "onCodeChange");
        Intrinsics.checkNotNullParameter(onRetryBtnClick, "onRetryBtnClick");
        Composer composerStartRestartGroup = composer.startRestartGroup(-2127141057);
        if ((i11 & 6) == 0) {
            i14 = (composerStartRestartGroup.changed(title) ? 4 : 2) | i11;
        } else {
            i14 = i11;
        }
        if ((i11 & 48) == 0) {
            i14 |= composerStartRestartGroup.changed(textFieldDesc) ? 32 : 16;
        }
        int i27 = i13 & 4;
        if (i27 == 0) {
            if ((i11 & KyberEngine.KyberPolyBytes) == 0) {
                i14 |= composerStartRestartGroup.changed(z10) ? 256 : 128;
            }
            if ((i11 & 3072) == 0) {
                if (composerStartRestartGroup.changed(z11)) {
                    i26 = 2048;
                } else {
                    i26 = 1024;
                }
                i14 |= i26;
            }
            if ((i11 & 24576) == 0) {
                if (composerStartRestartGroup.changed(textFieldPlaceHolder)) {
                    i25 = 16384;
                } else {
                    i25 = 8192;
                }
                i14 |= i25;
            }
            if ((196608 & i11) == 0) {
                if (composerStartRestartGroup.changed(z12)) {
                    i24 = 131072;
                } else {
                    i24 = ArrayPool.STANDARD_BUFFER_SIZE_BYTES;
                }
                i14 |= i24;
            }
            if ((1572864 & i11) == 0) {
                if (composerStartRestartGroup.changed(initCode)) {
                    i23 = 1048576;
                } else {
                    i23 = 524288;
                }
                i14 |= i23;
            }
            if ((12582912 & i11) == 0) {
                if (composerStartRestartGroup.changedInstance(onCodeChange)) {
                    i22 = RemoteFilesRepository.BYTE_ARRAY_SIZE;
                } else {
                    i22 = 4194304;
                }
                i14 |= i22;
            }
            i15 = i13 & 256;
            if (i15 != 0) {
                i14 |= 100663296;
            } else if ((i11 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(imageBitmap)) {
                    i16 = 67108864;
                } else {
                    i16 = MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
                }
                i14 |= i16;
            }
            if ((i11 & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(onRetryBtnClick)) {
                    i21 = SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING;
                } else {
                    i21 = SQLiteDatabase.CREATE_IF_NECESSARY;
                }
                i14 |= i21;
            }
            if ((i12 & 6) == 0) {
                if (composerStartRestartGroup.changed(i10)) {
                    i20 = 4;
                } else {
                    i20 = 2;
                }
                i17 = i12 | i20;
            } else {
                i17 = i12;
            }
            if (composerStartRestartGroup.shouldExecute((i14 & 306783379) == 306783378 || (i17 & 3) != 2, i14 & 1)) {
                if (i27 != 0) {
                    z14 = false;
                } else {
                    z14 = z10;
                }
                if (i15 != 0) {
                    imageBitmap3 = null;
                } else {
                    imageBitmap3 = imageBitmap;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-2127141057, i14, i17, "ru.mail.compose.component.textfield.PikachuCaptcha (PickachuCaptcha.kt:55)");
                }
                i18 = i17;
                z15 = z14;
                i19 = i14;
                HeaderTextKt.m15268HeaderTextVG8dh0g(title, null, 0.0f, 0L, 0L, null, 0, null, composerStartRestartGroup, i14 & 14, 254);
                companion = Modifier.INSTANCE;
                float f10 = 16;
                float f11 = 0;
                Modifier modifierM1015paddingVpY3zN4 = PaddingKt.m1015paddingVpY3zN4(SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), null, false, 3, null), Dp.m8268constructorimpl(f10), Dp.m8268constructorimpl(f11));
                Alignment.Companion companion3 = Alignment.INSTANCE;
                Alignment.Vertical centerVertically = companion3.getCenterVertically();
                Arrangement arrangement = Arrangement.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), centerVertically, composerStartRestartGroup, 48);
                iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1015paddingVpY3zN4);
                ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
                constructor = companion4.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM4589constructorimpl = Updater.m4589constructorimpl(composerStartRestartGroup);
                Updater.m4597setimpl(composerM4589constructorimpl, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
                Updater.m4597setimpl(composerM4589constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion4.getSetCompositeKeyHash();
                if (composerM4589constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4589constructorimpl.rememberedValue(), Integer.valueOf(iHashCode))) {
                    composerM4589constructorimpl.updateRememberedValue(Integer.valueOf(iHashCode));
                    composerM4589constructorimpl.apply(Integer.valueOf(iHashCode), setCompositeKeyHash);
                }
                Updater.m4597setimpl(composerM4589constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion2 = Composer.INSTANCE;
                if (objRememberedValue == companion2.getEmpty()) {
                    objRememberedValue = SizeKt.m1062height3ABfNKs(SizeKt.m1081width3ABfNKs(companion, Dp.m8268constructorimpl(160)), Dp.m8268constructorimpl(64));
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                modifier = (Modifier) objRememberedValue;
                if (z12) {
                    z16 = z15;
                    imageBitmap3 = imageBitmap3;
                    composerStartRestartGroup.startReplaceGroup(1692297682);
                    ProgressBar(modifier, composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(1691365853);
                    if (imageBitmap3 != null) {
                        composerStartRestartGroup.startReplaceGroup(1691401751);
                        z16 = z15;
                        ImageKt.m339Image5hnEew(imageBitmap3, "captcha", NodeTestModifierKt.nodeTestModifier(modifier, z16, PIK_CAPTCHA_IMG_TAG), null, null, 0.0f, null, 0, composerStartRestartGroup, ((i19 >> 24) & 14) | 48, 248);
                        composerStartRestartGroup.endReplaceGroup();
                    } else {
                        z16 = z15;
                        composerStartRestartGroup.startReplaceGroup(1691647209);
                        BoxKt.Box(BackgroundKt.m282backgroundbw27NRU$default(modifier, Color.INSTANCE.m5493getTransparent0d7_KjU(), null, 2, null), composerStartRestartGroup, 6);
                        composerStartRestartGroup.endReplaceGroup();
                    }
                    Painter painterPainterResource = PainterResources_androidKt.painterResource(i10, composerStartRestartGroup, i18 & 14);
                    Modifier modifierNodeTestModifier = NodeTestModifierKt.nodeTestModifier(SizeKt.wrapContentHeight$default(SizeKt.wrapContentWidth$default(companion, null, false, 3, null), null, false, 3, null), z16, PIK_CAPTCHA_RETRY_BTN_TAG);
                    if ((i19 & 1879048192) == 536870912) {
                        z18 = true;
                    } else {
                        z18 = false;
                    }
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (z18 || objRememberedValue4 == companion2.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: ab.k
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return PickachuCaptchaKt.PikachuCaptcha$lambda$0$1$0(onRetryBtnClick);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    ImageKt.Image(painterPainterResource, "RetryBtn", ClickableKt.m317clickableoSLSa3U$default(modifierNodeTestModifier, false, null, null, null, (Function0) objRememberedValue4, 15, null), (Alignment) null, (ContentScale) null, 0.0f, ColorFilter.Companion.m5499tintxETnrds$default(ColorFilter.INSTANCE, ((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15417getContrastPrimary0d7_KjU(), 0, 2, null), composerStartRestartGroup, 48, 56);
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endNode();
                boolean z19 = z16;
                Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composerStartRestartGroup, 0, 3);
                Modifier modifierM1015paddingVpY3zN5 = PaddingKt.m1015paddingVpY3zN4(SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), null, false, 3, null), Dp.m8268constructorimpl(f10), Dp.m8268constructorimpl(f11));
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(arrangement.getStart(), companion3.getCenterVertically(), composerStartRestartGroup, 48);
                iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1015paddingVpY3zN5);
                constructor2 = companion4.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM4589constructorimpl2 = Updater.m4589constructorimpl(composerStartRestartGroup);
                Updater.m4597setimpl(composerM4589constructorimpl2, measurePolicyRowMeasurePolicy2, companion4.getSetMeasurePolicy());
                Updater.m4597setimpl(composerM4589constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = companion4.getSetCompositeKeyHash();
                if (composerM4589constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM4589constructorimpl2.rememberedValue(), Integer.valueOf(iHashCode2))) {
                    composerM4589constructorimpl2.updateRememberedValue(Integer.valueOf(iHashCode2));
                    composerM4589constructorimpl2.apply(Integer.valueOf(iHashCode2), setCompositeKeyHash2);
                }
                Updater.m4597setimpl(composerM4589constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
                Modifier modifierWrapContentHeight$default = SizeKt.wrapContentHeight$default(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, null), null, false, 3, null);
                TextStyle textStyle = new TextStyle(0L, TextUnitKt.getSp(14), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, ((CustomTypography) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomTypography())).getRegular().getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777181, (DefaultConstructorMarker) null);
                if (z11) {
                    composerStartRestartGroup.startReplaceGroup(-52882750);
                    jM15450getTextSecondary0d7_KjU = ((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15448getTextNegative0d7_KjU();
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-52807327);
                    jM15450getTextSecondary0d7_KjU = ((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15450getTextSecondary0d7_KjU();
                    composerStartRestartGroup.endReplaceGroup();
                }
                TextKt.m3323Text4IGK_g(textFieldDesc, modifierWrapContentHeight$default, jM15450getTextSecondary0d7_KjU, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, textStyle, composerStartRestartGroup, (i19 >> 3) & 14, 0, 65528);
                composer2 = composerStartRestartGroup;
                objRememberedValue2 = composer2.rememberedValue();
                if (objRememberedValue2 == companion2.getEmpty()) {
                    initCode = str;
                    obj = null;
                    objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initCode, null, 2, null);
                    composer2.updateRememberedValue(objRememberedValue2);
                } else {
                    initCode = str;
                    obj = null;
                }
                mutableState = (MutableState) objRememberedValue2;
                z13 = z19;
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(NodeTestModifierKt.nodeTestModifier(RowScope.weight$default(rowScopeInstance, companion, 4.0f, false, 2, null), z13, PIK_CAPTCHA_ET_TAG), 0.0f, 1, obj);
                String str3 = (String) mutableState.getValue();
                RoundedCornerShape roundedCornerShapeM1318RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1318RoundedCornerShape0680j_4(Dp.m8268constructorimpl(f11));
                TextFieldDefaults textFieldDefaults = TextFieldDefaults.INSTANCE;
                Color.Companion companion5 = Color.INSTANCE;
                TextFieldColors textFieldColorsM3305colors0hiis_0 = textFieldDefaults.m3305colors0hiis_0(((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU(), ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU(), 0L, 0L, companion5.m5493getTransparent0d7_KjU(), companion5.m5493getTransparent0d7_KjU(), 0L, 0L, ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU(), 0L, null, companion5.m5493getTransparent0d7_KjU(), companion5.m5493getTransparent0d7_KjU(), companion5.m5493getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer2, 221184, 3504, 0, 0, 3072, 2147469004, 4095);
                TextStyle textStyle2 = new TextStyle(0L, TextUnitKt.getSp(16), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, ((CustomInterTypography) composer2.consume(MailThemeKt.getLocalCustomInterTypography())).getRegular().getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777181, (DefaultConstructorMarker) null);
                if ((i19 & 29360128) == 8388608) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                objRememberedValue3 = composer2.rememberedValue();
                if (z17 || objRememberedValue3 == companion2.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: ab.l
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PickachuCaptchaKt.PikachuCaptcha$lambda$1$1$0(onCodeChange, mutableState, (String) obj2);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue3);
                }
                Function1 function1 = (Function1) objRememberedValue3;
                str2 = textFieldPlaceHolder;
                TextFieldKt.TextField(str3, (Function1<? super String, Unit>) function1, modifierFillMaxWidth$default, false, false, textStyle2, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1151829547, true, new Function2() { // from class: ab.m
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return PickachuCaptchaKt.PikachuCaptcha$lambda$1$2(str2, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer2, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1318RoundedCornerShape0680j_4, textFieldColorsM3305colors0hiis_0, composer2, 12582912, 12582912, 0, 1965912);
                composer2.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                imageBitmap2 = imageBitmap3;
            } else {
                composer2 = composerStartRestartGroup;
                str2 = textFieldPlaceHolder;
                composer2.skipToGroupEnd();
                z13 = z10;
                imageBitmap2 = imageBitmap;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final String str4 = initCode;
                final boolean z20 = z13;
                final String str5 = str2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ab.n
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return PickachuCaptchaKt.PikachuCaptcha$lambda$2(title, textFieldDesc, z20, z11, str5, z12, str4, onCodeChange, imageBitmap2, onRetryBtnClick, i10, i11, i12, i13, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i14 |= KyberEngine.KyberPolyBytes;
        if ((i11 & 3072) == 0) {
            if (composerStartRestartGroup.changed(z11)) {
                i26 = 2048;
            } else {
                i26 = 1024;
            }
            i14 |= i26;
        }
        if ((i11 & 24576) == 0) {
            if (composerStartRestartGroup.changed(textFieldPlaceHolder)) {
                i25 = 16384;
            } else {
                i25 = 8192;
            }
            i14 |= i25;
        }
        if ((196608 & i11) == 0) {
            if (composerStartRestartGroup.changed(z12)) {
                i24 = 131072;
            } else {
                i24 = ArrayPool.STANDARD_BUFFER_SIZE_BYTES;
            }
            i14 |= i24;
        }
        if ((1572864 & i11) == 0) {
            if (composerStartRestartGroup.changed(initCode)) {
                i23 = 1048576;
            } else {
                i23 = 524288;
            }
            i14 |= i23;
        }
        if ((12582912 & i11) == 0) {
            if (composerStartRestartGroup.changedInstance(onCodeChange)) {
                i22 = RemoteFilesRepository.BYTE_ARRAY_SIZE;
            } else {
                i22 = 4194304;
            }
            i14 |= i22;
        }
        i15 = i13 & 256;
        if (i15 != 0) {
            i14 |= 100663296;
        } else if ((i11 & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(imageBitmap)) {
                i16 = 67108864;
            } else {
                i16 = MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
            }
            i14 |= i16;
        }
        if ((i11 & 805306368) == 0) {
            if (composerStartRestartGroup.changedInstance(onRetryBtnClick)) {
                i21 = SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING;
            } else {
                i21 = SQLiteDatabase.CREATE_IF_NECESSARY;
            }
            i14 |= i21;
        }
        if ((i12 & 6) == 0) {
            if (composerStartRestartGroup.changed(i10)) {
                i20 = 4;
            } else {
                i20 = 2;
            }
            i17 = i12 | i20;
        } else {
            i17 = i12;
        }
        if (composerStartRestartGroup.shouldExecute((i14 & 306783379) == 306783378 || (i17 & 3) != 2, i14 & 1)) {
            if (i27 != 0) {
                z14 = false;
            } else {
                z14 = z10;
            }
            if (i15 != 0) {
                imageBitmap3 = null;
            } else {
                imageBitmap3 = imageBitmap;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2127141057, i14, i17, "ru.mail.compose.component.textfield.PikachuCaptcha (PickachuCaptcha.kt:55)");
            }
            i18 = i17;
            z15 = z14;
            i19 = i14;
            HeaderTextKt.m15268HeaderTextVG8dh0g(title, null, 0.0f, 0L, 0L, null, 0, null, composerStartRestartGroup, i14 & 14, 254);
            companion = Modifier.INSTANCE;
            float f12 = 16;
            float f13 = 0;
            Modifier modifierM1015paddingVpY3zN6 = PaddingKt.m1015paddingVpY3zN4(SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), null, false, 3, null), Dp.m8268constructorimpl(f12), Dp.m8268constructorimpl(f13));
            Alignment.Companion companion6 = Alignment.INSTANCE;
            Alignment.Vertical centerVertically2 = companion6.getCenterVertically();
            Arrangement arrangement2 = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(arrangement2.getStart(), centerVertically2, composerStartRestartGroup, 48);
            iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1015paddingVpY3zN6);
            ComposeUiNode.Companion companion7 = ComposeUiNode.INSTANCE;
            constructor = companion7.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM4589constructorimpl = Updater.m4589constructorimpl(composerStartRestartGroup);
            Updater.m4597setimpl(composerM4589constructorimpl, measurePolicyRowMeasurePolicy3, companion7.getSetMeasurePolicy());
            Updater.m4597setimpl(composerM4589constructorimpl, currentCompositionLocalMap3, companion7.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion7.getSetCompositeKeyHash();
            if (composerM4589constructorimpl.getInserting()) {
                composerM4589constructorimpl.updateRememberedValue(Integer.valueOf(iHashCode));
                composerM4589constructorimpl.apply(Integer.valueOf(iHashCode), setCompositeKeyHash);
            } else {
                composerM4589constructorimpl.updateRememberedValue(Integer.valueOf(iHashCode));
                composerM4589constructorimpl.apply(Integer.valueOf(iHashCode), setCompositeKeyHash);
            }
            Updater.m4597setimpl(composerM4589constructorimpl, modifierMaterializeModifier3, companion7.getSetModifier());
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion2 = Composer.INSTANCE;
            if (objRememberedValue == companion2.getEmpty()) {
                objRememberedValue = SizeKt.m1062height3ABfNKs(SizeKt.m1081width3ABfNKs(companion, Dp.m8268constructorimpl(160)), Dp.m8268constructorimpl(64));
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            modifier = (Modifier) objRememberedValue;
            if (z12) {
                composerStartRestartGroup.startReplaceGroup(1691365853);
                if (imageBitmap3 != null) {
                    composerStartRestartGroup.startReplaceGroup(1691401751);
                    z16 = z15;
                    ImageKt.m339Image5hnEew(imageBitmap3, "captcha", NodeTestModifierKt.nodeTestModifier(modifier, z16, PIK_CAPTCHA_IMG_TAG), null, null, 0.0f, null, 0, composerStartRestartGroup, ((i19 >> 24) & 14) | 48, 248);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    z16 = z15;
                    composerStartRestartGroup.startReplaceGroup(1691647209);
                    BoxKt.Box(BackgroundKt.m282backgroundbw27NRU$default(modifier, Color.INSTANCE.m5493getTransparent0d7_KjU(), null, 2, null), composerStartRestartGroup, 6);
                    composerStartRestartGroup.endReplaceGroup();
                }
                Painter painterPainterResource2 = PainterResources_androidKt.painterResource(i10, composerStartRestartGroup, i18 & 14);
                Modifier modifierNodeTestModifier2 = NodeTestModifierKt.nodeTestModifier(SizeKt.wrapContentHeight$default(SizeKt.wrapContentWidth$default(companion, null, false, 3, null), null, false, 3, null), z16, PIK_CAPTCHA_RETRY_BTN_TAG);
                if ((i19 & 1879048192) == 536870912) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (z18) {
                    objRememberedValue4 = new Function0() { // from class: ab.k
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PickachuCaptchaKt.PikachuCaptcha$lambda$0$1$0(onRetryBtnClick);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                } else {
                    objRememberedValue4 = new Function0() { // from class: ab.k
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return PickachuCaptchaKt.PikachuCaptcha$lambda$0$1$0(onRetryBtnClick);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                ImageKt.Image(painterPainterResource2, "RetryBtn", ClickableKt.m317clickableoSLSa3U$default(modifierNodeTestModifier2, false, null, null, null, (Function0) objRememberedValue4, 15, null), (Alignment) null, (ContentScale) null, 0.0f, ColorFilter.Companion.m5499tintxETnrds$default(ColorFilter.INSTANCE, ((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15417getContrastPrimary0d7_KjU(), 0, 2, null), composerStartRestartGroup, 48, 56);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                z16 = z15;
                imageBitmap3 = imageBitmap3;
                composerStartRestartGroup.startReplaceGroup(1692297682);
                ProgressBar(modifier, composerStartRestartGroup, 6);
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            boolean z110 = z16;
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composerStartRestartGroup, 0, 3);
            Modifier modifierM1015paddingVpY3zN7 = PaddingKt.m1015paddingVpY3zN4(SizeKt.wrapContentHeight$default(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, null), null, false, 3, null), Dp.m8268constructorimpl(f12), Dp.m8268constructorimpl(f13));
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(arrangement2.getStart(), companion6.getCenterVertically(), composerStartRestartGroup, 48);
            iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM1015paddingVpY3zN7);
            constructor2 = companion7.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM4589constructorimpl2 = Updater.m4589constructorimpl(composerStartRestartGroup);
            Updater.m4597setimpl(composerM4589constructorimpl2, measurePolicyRowMeasurePolicy4, companion7.getSetMeasurePolicy());
            Updater.m4597setimpl(composerM4589constructorimpl2, currentCompositionLocalMap4, companion7.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = companion7.getSetCompositeKeyHash();
            if (composerM4589constructorimpl2.getInserting()) {
                composerM4589constructorimpl2.updateRememberedValue(Integer.valueOf(iHashCode2));
                composerM4589constructorimpl2.apply(Integer.valueOf(iHashCode2), setCompositeKeyHash2);
            } else {
                composerM4589constructorimpl2.updateRememberedValue(Integer.valueOf(iHashCode2));
                composerM4589constructorimpl2.apply(Integer.valueOf(iHashCode2), setCompositeKeyHash2);
            }
            Updater.m4597setimpl(composerM4589constructorimpl2, modifierMaterializeModifier4, companion7.getSetModifier());
            Modifier modifierWrapContentHeight$default2 = SizeKt.wrapContentHeight$default(RowScope.weight$default(rowScopeInstance2, companion, 1.0f, false, 2, null), null, false, 3, null);
            TextStyle textStyle3 = new TextStyle(0L, TextUnitKt.getSp(14), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, ((CustomTypography) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomTypography())).getRegular().getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777181, (DefaultConstructorMarker) null);
            if (z11) {
                composerStartRestartGroup.startReplaceGroup(-52882750);
                jM15450getTextSecondary0d7_KjU = ((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15448getTextNegative0d7_KjU();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-52807327);
                jM15450getTextSecondary0d7_KjU = ((CustomColors) composerStartRestartGroup.consume(MailThemeKt.getLocalCustomColors())).m15450getTextSecondary0d7_KjU();
                composerStartRestartGroup.endReplaceGroup();
            }
            TextKt.m3323Text4IGK_g(textFieldDesc, modifierWrapContentHeight$default2, jM15450getTextSecondary0d7_KjU, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, textStyle3, composerStartRestartGroup, (i19 >> 3) & 14, 0, 65528);
            composer2 = composerStartRestartGroup;
            objRememberedValue2 = composer2.rememberedValue();
            if (objRememberedValue2 == companion2.getEmpty()) {
                initCode = str;
                obj = null;
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initCode, null, 2, null);
                composer2.updateRememberedValue(objRememberedValue2);
            } else {
                initCode = str;
                obj = null;
            }
            mutableState = (MutableState) objRememberedValue2;
            z13 = z110;
            Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(NodeTestModifierKt.nodeTestModifier(RowScope.weight$default(rowScopeInstance2, companion, 4.0f, false, 2, null), z13, PIK_CAPTCHA_ET_TAG), 0.0f, 1, obj);
            String str6 = (String) mutableState.getValue();
            RoundedCornerShape roundedCornerShapeM1318RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m1318RoundedCornerShape0680j_4(Dp.m8268constructorimpl(f13));
            TextFieldDefaults textFieldDefaults2 = TextFieldDefaults.INSTANCE;
            Color.Companion companion8 = Color.INSTANCE;
            TextFieldColors textFieldColorsM3305colors0hiis_1 = textFieldDefaults2.m3305colors0hiis_0(((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU(), ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU(), 0L, 0L, companion8.m5493getTransparent0d7_KjU(), companion8.m5493getTransparent0d7_KjU(), 0L, 0L, ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU(), 0L, null, companion8.m5493getTransparent0d7_KjU(), companion8.m5493getTransparent0d7_KjU(), companion8.m5493getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer2, 221184, 3504, 0, 0, 3072, 2147469004, 4095);
            TextStyle textStyle4 = new TextStyle(0L, TextUnitKt.getSp(16), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, ((CustomInterTypography) composer2.consume(MailThemeKt.getLocalCustomInterTypography())).getRegular().getFontFamily(), (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, 0L, (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16777181, (DefaultConstructorMarker) null);
            if ((i19 & 29360128) == 8388608) {
                z17 = true;
            } else {
                z17 = false;
            }
            objRememberedValue3 = composer2.rememberedValue();
            if (z17) {
                objRememberedValue3 = new Function1() { // from class: ab.l
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return PickachuCaptchaKt.PikachuCaptcha$lambda$1$1$0(onCodeChange, mutableState, (String) obj2);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function1() { // from class: ab.l
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return PickachuCaptchaKt.PikachuCaptcha$lambda$1$1$0(onCodeChange, mutableState, (String) obj2);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            }
            Function1 function2 = (Function1) objRememberedValue3;
            str2 = textFieldPlaceHolder;
            TextFieldKt.TextField(str6, (Function1<? super String, Unit>) function2, modifierFillMaxWidth$default2, false, false, textStyle4, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1151829547, true, new Function2() { // from class: ab.m
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PickachuCaptchaKt.PikachuCaptcha$lambda$1$2(str2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer2, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) roundedCornerShapeM1318RoundedCornerShape0680j_5, textFieldColorsM3305colors0hiis_1, composer2, 12582912, 12582912, 0, 1965912);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            imageBitmap2 = imageBitmap3;
        } else {
            composer2 = composerStartRestartGroup;
            str2 = textFieldPlaceHolder;
            composer2.skipToGroupEnd();
            z13 = z10;
            imageBitmap2 = imageBitmap;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final String str7 = initCode;
            final boolean z21 = z13;
            final String str8 = str2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ab.n
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return PickachuCaptchaKt.PikachuCaptcha$lambda$2(title, textFieldDesc, z21, z11, str8, z12, str7, onCodeChange, imageBitmap2, onRetryBtnClick, i10, i11, i12, i13, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PikachuCaptcha$lambda$0$1$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PikachuCaptcha$lambda$1$1$0(Function1 function1, MutableState mutableState, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        function1.invoke(it);
        mutableState.setValue(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    public static final Unit PikachuCaptcha$lambda$1$2(String str, Composer composer, int i10) {
        if (composer.shouldExecute((i10 & 3) != 2, i10 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1151829547, i10, -1, "ru.mail.compose.component.textfield.PikachuCaptcha.<anonymous>.<anonymous> (PickachuCaptcha.kt:149)");
            }
            TextKt.m3323Text4IGK_g(str, (Modifier) null, ((CustomColors) composer.consume(MailThemeKt.getLocalCustomColors())).m15450getTextSecondary0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 0, 0, 131066);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PikachuCaptcha$lambda$2(String str, String str2, boolean z10, boolean z11, String str3, boolean z12, String str4, Function1 function1, ImageBitmap imageBitmap, Function0 function0, int i10, int i11, int i12, int i13, Composer composer, int i14) {
        PikachuCaptcha(str, str2, z10, z11, str3, z12, str4, function1, imageBitmap, function0, i10, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), RecomposeScopeImplKt.updateChangedFlags(i12), i13);
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    @Preview
    private static final void PreviewProgressBar(Composer composer, final int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-267361084);
        if (composerStartRestartGroup.shouldExecute(i10 != 0, i10 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-267361084, i10, -1, "ru.mail.compose.component.textfield.PreviewProgressBar (PickachuCaptcha.kt:175)");
            }
            ProgressBar(SizeKt.m1062height3ABfNKs(SizeKt.m1081width3ABfNKs(Modifier.INSTANCE, Dp.m8268constructorimpl(160)), Dp.m8268constructorimpl(64)), composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ab.j
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PickachuCaptchaKt.PreviewProgressBar$lambda$0(i10, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit PreviewProgressBar$lambda$0(int i10, Composer composer, int i11) {
        PreviewProgressBar(composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return Unit.INSTANCE;
    }

    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @Composable
    private static final void ProgressBar(final Modifier modifier, Composer composer, final int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1444810375);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if (composerStartRestartGroup.shouldExecute((i11 & 3) != 2, i11 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1444810375, i11, -1, "ru.mail.compose.component.textfield.ProgressBar (PickachuCaptcha.kt:159)");
            }
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getCenter(), false);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM4589constructorimpl = Updater.m4589constructorimpl(composerStartRestartGroup);
            Updater.m4597setimpl(composerM4589constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.m4597setimpl(composerM4589constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
            if (composerM4589constructorimpl.getInserting() || !Intrinsics.areEqual(composerM4589constructorimpl.rememberedValue(), Integer.valueOf(iHashCode))) {
                composerM4589constructorimpl.updateRememberedValue(Integer.valueOf(iHashCode));
                composerM4589constructorimpl.apply(Integer.valueOf(iHashCode), setCompositeKeyHash);
            }
            Updater.m4597setimpl(composerM4589constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ProgressIndicatorKt.m2986CircularProgressIndicatorLxG7B9w(SizeKt.m1076size3ABfNKs(Modifier.INSTANCE, Dp.m8268constructorimpl(40)), MailTheme.INSTANCE.getColors(composerStartRestartGroup, 6).m15417getContrastPrimary0d7_KjU(), Dp.m8268constructorimpl(1), 0L, 0, composerStartRestartGroup, 390, 24);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ab.o
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return PickachuCaptchaKt.ProgressBar$lambda$1(modifier, i10, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ProgressBar$lambda$1(Modifier modifier, int i10, Composer composer, int i11) {
        ProgressBar(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1));
        return Unit.INSTANCE;
    }
}
