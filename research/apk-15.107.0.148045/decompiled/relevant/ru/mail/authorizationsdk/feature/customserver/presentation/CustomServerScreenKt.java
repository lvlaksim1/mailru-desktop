package ru.mail.authorizationsdk.feature.customserver.presentation;

import android.annotation.SuppressLint;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.DividerKt;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.FunctionKeyMeta;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.res.StringResources_androidKt;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.FlowExtKt;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.PikachuCaptchaProvider;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.error.ServerParamsErrorsProvider;
import ru.mail.authorizationsdk.feature.customserver.presentation.delegates.params.ServerParamsProvider;
import ru.mail.authorizationsdk.ui.kit.progressbar.AuthFlowProgressBarKt;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.compose.component.button.MailSansDispButtonKt;
import ru.mail.compose.component.choice.ChoiceWithTitleKt;
import ru.mail.compose.component.divider.Line1DpDividerKt;
import ru.mail.compose.component.text.ErrorHighlightTextKt;
import ru.mail.compose.component.text.HeaderTextAllCapsKt;
import ru.mail.compose.component.text.HeaderTextKt;
import ru.mail.compose.component.textfield.HostTextFieldKt;
import ru.mail.compose.component.textfield.PickachuCaptchaKt;
import ru.mail.compose.component.textfield.PortTextFieldKt;
import ru.mail.compose.component.textfield.email.EmailSuggestionsProvider;
import ru.mail.compose.component.textfield.email.EmailSuggestionsTextFieldKt;
import ru.mail.compose.component.textfield.password.PasswordTextFieldKt;
import ru.mail.compose.component.toolbar.SmallToolbarKt;
import ru.mail.compose.component.toolbar.ToolbarConfig;
import ru.mail.compose.component.util.scrollbar.ScrollBarConfig;
import ru.mail.compose.component.util.scrollbar.ScrollbarKt;
import ru.mail.compose.modifier.NodeTestModifierKt;
import ru.mail.compose.theme.CustomColors;
import ru.mail.compose.theme.MailThemeKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\u001a¹\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00052\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u001a\"\u000e\u0010\u001b\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001d\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001e\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010 \u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010!\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010\"\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010#\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000\"\u000e\u0010$\u001a\u00020\u0011X\u0086T¢\u0006\u0002\n\u0000¨\u0006%²\u0006\n\u0010&\u001a\u00020\u000fX\u008a\u0084\u0002²\u0006\n\u0010'\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010(\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010)\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010*\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010+\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010,\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010-\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u0010.\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010/\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00100\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00101\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00102\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00103\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00104\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00106\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\n\u00107\u001a\u00020\u0005X\u008a\u0084\u0002²\u0006\f\u00108\u001a\u0004\u0018\u000109X\u008a\u0084\u0002"}, d2 = {"CustomServerScreen", "", "loginSuggestions", "Lru/mail/compose/component/textfield/email/EmailSuggestionsProvider;", "isImapOnly", "", "onPasswordVisibilityChange", "Lkotlin/Function1;", "serverParamsProvider", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/params/ServerParamsProvider;", "serverParamsErrorsProvider", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/error/ServerParamsErrorsProvider;", "pikachuCaptchaProvider", "Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaProvider;", "onEmailChange", "Landroidx/compose/ui/text/input/TextFieldValue;", "onChooseSuggest", "", "onDismissSuggests", "Lkotlin/Function0;", "commonAnalytics", "Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;", "isLoading", "onBackClick", "sendServerParams", "isTest", "(Lru/mail/compose/component/textfield/email/EmailSuggestionsProvider;ZLkotlin/jvm/functions/Function1;Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/params/ServerParamsProvider;Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/error/ServerParamsErrorsProvider;Lru/mail/authorizationsdk/feature/customserver/presentation/delegates/PikachuCaptchaProvider;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;III)V", CustomServerScreenKt.USER_LOGIN_FIELD_TAG, CustomServerScreenKt.IMAP_SIGN_IN_BUTTON_TAG, CustomServerScreenKt.POP_PROTOCOL_BTN_TAG, CustomServerScreenKt.IMAP_PROTOCOL_BTN_TAG, CustomServerScreenKt.INCOME_HOST_ET_TAG, CustomServerScreenKt.OUTCOME_HOST_ET_TAG, CustomServerScreenKt.INCOME_PORT_ET_TAG, CustomServerScreenKt.OUTCOME_PORT_ET_TAG, CustomServerScreenKt.INCOME_SSL_BTN_TAG, "OUTCOME_SSL_BTN_TAG", "authorizationsdk_release", "email", "isImapProtocol", "incomeHost", "incomePort", "isIncomeSSLEnabled", "outcomeHost", "outcomePort", "isOutcomeSSLEnabled", "errorMessage", "isIncomeHostError", "isIncomePortError", "isIncomeSSLError", "isOutgoingHostError", "isOutgoingPortError", "isOutgoingSSLError", "isPikachuCaptchaCodeError", "isNeedShowCaptcha", "isCaptchaLoading", "captchaBitmap", "Landroidx/compose/ui/graphics/ImageBitmap;"}, k = 2, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCustomServerScreen.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CustomServerScreen.kt\nru/mail/authorizationsdk/feature/customserver/presentation/CustomServerScreenKt\n+ 2 Composer.kt\nandroidx/compose/runtime/ComposerKt\n+ 3 CompositionLocal.kt\nandroidx/compose/runtime/CompositionLocal\n+ 4 SnapshotState.kt\nandroidx/compose/runtime/SnapshotStateKt__SnapshotStateKt\n+ 5 Dp.kt\nandroidx/compose/ui/unit/DpKt\n+ 6 Dp.kt\nandroidx/compose/ui/unit/Dp\n+ 7 Column.kt\nandroidx/compose/foundation/layout/ColumnKt\n+ 8 Layout.kt\nandroidx/compose/ui/layout/LayoutKt\n+ 9 Composables.kt\nandroidx/compose/runtime/ComposablesKt\n+ 10 Row.kt\nandroidx/compose/foundation/layout/RowKt\n*L\n1#1,382:1\n1047#2,6:383\n1047#2,6:389\n1047#2,6:395\n1047#2,6:401\n1047#2,6:407\n1047#2,6:413\n1047#2,6:422\n1047#2,6:518\n1047#2,6:560\n1047#2,6:571\n1047#2,6:581\n1047#2,6:587\n1047#2,6:593\n1047#2,6:599\n1047#2,6:605\n1047#2,6:611\n75#3:419\n75#3:420\n75#3:421\n75#3:449\n75#3:484\n75#3:558\n75#3:559\n75#3:567\n75#3:569\n75#3:570\n85#4:428\n85#4:429\n85#4:430\n85#4:431\n85#4:432\n85#4:433\n85#4:434\n85#4:435\n85#4:436\n85#4:437\n85#4:438\n85#4:439\n85#4:440\n85#4:441\n85#4:442\n85#4:443\n85#4:444\n85#4:445\n85#4:446\n118#5:447\n118#5:450\n118#5:483\n118#5:485\n118#5:524\n118#5:557\n118#5:566\n118#5:568\n118#5:617\n118#5:618\n49#6:448\n87#7:451\n84#7,9:452\n87#7:486\n84#7,9:487\n94#7:622\n94#7:626\n81#8,6:461\n88#8,6:476\n81#8,6:496\n88#8,6:511\n81#8,6:535\n88#8,6:550\n96#8:579\n96#8:621\n96#8:625\n402#9,9:467\n411#9:482\n402#9,9:502\n411#9:517\n402#9,9:541\n411#9:556\n412#9,2:577\n412#9,2:619\n412#9,2:623\n99#10:525\n96#10,9:526\n106#10:580\n*S KotlinDebug\n*F\n+ 1 CustomServerScreen.kt\nru/mail/authorizationsdk/feature/customserver/presentation/CustomServerScreenKt\n*L\n81#1:383,6\n84#1:389,6\n116#1:395,6\n117#1:401,6\n120#1:407,6\n123#1:413,6\n134#1:422,6\n187#1:518,6\n214#1:560,6\n244#1:571,6\n273#1:581,6\n284#1:587,6\n296#1:593,6\n309#1:599,6\n320#1:605,6\n333#1:611,6\n129#1:419\n130#1:420\n131#1:421\n144#1:449\n153#1:484\n221#1:558\n223#1:559\n236#1:567\n251#1:569\n253#1:570\n91#1:428\n92#1:429\n93#1:430\n94#1:431\n95#1:432\n96#1:433\n97#1:434\n98#1:435\n101#1:436\n102#1:437\n103#1:438\n104#1:439\n105#1:440\n106#1:441\n107#1:442\n109#1:443\n112#1:444\n113#1:445\n114#1:446\n139#1:447\n145#1:450\n151#1:483\n154#1:485\n206#1:524\n216#1:557\n235#1:566\n245#1:568\n355#1:617\n356#1:618\n139#1:448\n136#1:451\n136#1:452,9\n149#1:486\n149#1:487,9\n149#1:622\n136#1:626\n136#1:461,6\n136#1:476,6\n149#1:496,6\n149#1:511,6\n203#1:535,6\n203#1:550,6\n203#1:579\n149#1:621\n136#1:625\n136#1:467,9\n136#1:482\n149#1:502,9\n149#1:517\n203#1:541,9\n203#1:556\n203#1:577,2\n149#1:619,2\n136#1:623,2\n203#1:525\n203#1:526,9\n203#1:580\n*E\n"})
public final class CustomServerScreenKt {

    @NotNull
    public static final String IMAP_PROTOCOL_BTN_TAG = "IMAP_PROTOCOL_BTN_TAG";

    @NotNull
    public static final String IMAP_SIGN_IN_BUTTON_TAG = "IMAP_SIGN_IN_BUTTON_TAG";

    @NotNull
    public static final String INCOME_HOST_ET_TAG = "INCOME_HOST_ET_TAG";

    @NotNull
    public static final String INCOME_PORT_ET_TAG = "INCOME_PORT_ET_TAG";

    @NotNull
    public static final String INCOME_SSL_BTN_TAG = "INCOME_SSL_BTN_TAG";

    @NotNull
    public static final String OUTCOME_HOST_ET_TAG = "OUTCOME_HOST_ET_TAG";

    @NotNull
    public static final String OUTCOME_PORT_ET_TAG = "OUTCOME_PORT_ET_TAG";

    @NotNull
    public static final String OUTCOME_SSL_BTN_TAG = "OUTCOME_SSL_TAG";

    @NotNull
    public static final String POP_PROTOCOL_BTN_TAG = "POP_PROTOCOL_BTN_TAG";

    @NotNull
    public static final String USER_LOGIN_FIELD_TAG = "USER_LOGIN_FIELD_TAG";

    /* JADX WARN: Code duplicated, block: B:101:0x0167  */
    /* JADX WARN: Code duplicated, block: B:103:0x016f  */
    /* JADX WARN: Code duplicated, block: B:106:0x0176  */
    /* JADX WARN: Code duplicated, block: B:113:0x018e  */
    /* JADX WARN: Code duplicated, block: B:116:0x0197 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:117:0x0199  */
    /* JADX WARN: Code duplicated, block: B:118:0x019b  */
    /* JADX WARN: Code duplicated, block: B:120:0x019f  */
    /* JADX WARN: Code duplicated, block: B:121:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:124:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:127:0x01b8  */
    /* JADX WARN: Code duplicated, block: B:129:0x01bc  */
    /* JADX WARN: Code duplicated, block: B:134:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:137:0x01e7  */
    /* JADX WARN: Code duplicated, block: B:138:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:143:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:146:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:147:0x02ce  */
    /* JADX WARN: Code duplicated, block: B:150:0x02de  */
    /* JADX WARN: Code duplicated, block: B:153:0x02f4  */
    /* JADX WARN: Code duplicated, block: B:156:0x030a  */
    /* JADX WARN: Code duplicated, block: B:159:0x0362  */
    /* JADX WARN: Code duplicated, block: B:160:0x0364  */
    /* JADX WARN: Code duplicated, block: B:165:0x0371  */
    /* JADX WARN: Code duplicated, block: B:168:0x03e8  */
    /* JADX WARN: Code duplicated, block: B:170:0x03ef  */
    /* JADX WARN: Code duplicated, block: B:173:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:175:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x012f  */
    /* JADX WARN: Code duplicated, block: B:81:0x0135  */
    /* JADX WARN: Code duplicated, block: B:83:0x013a  */
    /* JADX WARN: Code duplicated, block: B:86:0x0140  */
    /* JADX WARN: Code duplicated, block: B:88:0x0146  */
    /* JADX WARN: Code duplicated, block: B:92:0x014e  */
    /* JADX WARN: Code duplicated, block: B:94:0x0154  */
    /* JADX WARN: Code duplicated, block: B:98:0x015e  */
    /* JADX WARN: Code duplicated, block: B:99:0x0161  */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 17987, key = -1077340530, startOffset = 3396)
    @SuppressLint({"StateFlowValueCalledInComposition"})
    @Composable
    public static final void CustomServerScreen(@NotNull final EmailSuggestionsProvider loginSuggestions, final boolean z10, @NotNull final Function1<? super Boolean, Unit> onPasswordVisibilityChange, @NotNull final ServerParamsProvider serverParamsProvider, @NotNull final ServerParamsErrorsProvider serverParamsErrorsProvider, @NotNull final PikachuCaptchaProvider pikachuCaptchaProvider, @NotNull final Function1<? super TextFieldValue, Unit> onEmailChange, @NotNull final Function1<? super String, Unit> onChooseSuggest, @NotNull final Function0<Unit> onDismissSuggests, @Nullable CommonAnalytics commonAnalytics, final boolean z11, @NotNull final Function0<Unit> onBackClick, @NotNull final Function0<Unit> sendServerParams, boolean z12, @Nullable Composer composer, final int i10, final int i11, final int i12) {
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        boolean z13;
        Composer composer2;
        final CommonAnalytics commonAnalytics2;
        final boolean z14;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final CommonAnalytics commonAnalytics3;
        boolean z15;
        int i19;
        boolean z16;
        Object objRememberedValue;
        final ScrollState scrollStateRememberScrollState;
        boolean z17;
        boolean zChanged;
        Object objRememberedValue2;
        final State stateCollectAsStateWithLifecycle;
        Object objRememberedValue3;
        Composer.Companion companion;
        Composer composer3;
        Object objRememberedValue4;
        Object objRememberedValue5;
        Object objRememberedValue6;
        boolean z18;
        Object objRememberedValue7;
        Intrinsics.checkNotNullParameter(loginSuggestions, "loginSuggestions");
        Intrinsics.checkNotNullParameter(onPasswordVisibilityChange, "onPasswordVisibilityChange");
        Intrinsics.checkNotNullParameter(serverParamsProvider, "serverParamsProvider");
        Intrinsics.checkNotNullParameter(serverParamsErrorsProvider, "serverParamsErrorsProvider");
        Intrinsics.checkNotNullParameter(pikachuCaptchaProvider, "pikachuCaptchaProvider");
        Intrinsics.checkNotNullParameter(onEmailChange, "onEmailChange");
        Intrinsics.checkNotNullParameter(onChooseSuggest, "onChooseSuggest");
        Intrinsics.checkNotNullParameter(onDismissSuggests, "onDismissSuggests");
        Intrinsics.checkNotNullParameter(onBackClick, "onBackClick");
        Intrinsics.checkNotNullParameter(sendServerParams, "sendServerParams");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1077340530);
        if ((i10 & 6) == 0) {
            i13 = (composerStartRestartGroup.changed(loginSuggestions) ? 4 : 2) | i10;
        } else {
            i13 = i10;
        }
        if ((i10 & 48) == 0) {
            i13 |= composerStartRestartGroup.changed(z10) ? 32 : 16;
        }
        if ((i10 & KyberEngine.KyberPolyBytes) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(onPasswordVisibilityChange) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i13 |= composerStartRestartGroup.changed(serverParamsProvider) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i13 |= composerStartRestartGroup.changed(serverParamsErrorsProvider) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i13 |= composerStartRestartGroup.changed(pikachuCaptchaProvider) ? 131072 : ArrayPool.STANDARD_BUFFER_SIZE_BYTES;
        }
        if ((i10 & 1572864) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(onEmailChange) ? 1048576 : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(onChooseSuggest) ? RemoteFilesRepository.BYTE_ARRAY_SIZE : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i13 |= composerStartRestartGroup.changedInstance(onDismissSuggests) ? 67108864 : MediaHttpDownloader.MAXIMUM_CHUNK_SIZE;
        }
        int i20 = i12 & 512;
        if (i20 == 0) {
            if ((i10 & 805306368) == 0) {
                i13 |= composerStartRestartGroup.changed(commonAnalytics) ? SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING : SQLiteDatabase.CREATE_IF_NECESSARY;
            }
            if ((i11 & 6) == 0) {
                i14 = i11 | (composerStartRestartGroup.changed(z11) ? 4 : 2);
            } else {
                i14 = i11;
            }
            if ((i11 & 48) == 0) {
                i14 |= composerStartRestartGroup.changedInstance(onBackClick) ? 32 : 16;
            }
            if ((i11 & KyberEngine.KyberPolyBytes) == 0) {
                i14 |= composerStartRestartGroup.changedInstance(sendServerParams) ? 256 : 128;
            }
            i15 = i14;
            i16 = i12 & 8192;
            if (i16 != 0) {
                i18 = i15 | 3072;
            } else {
                i17 = i15;
                if ((i11 & 3072) != 0) {
                    i17 |= composerStartRestartGroup.changed(z12) ? 2048 : 1024;
                }
                i18 = i17;
            }
            if ((i13 & 306783379) == 306783378 || (i18 & 1171) != 1170) {
                z13 = true;
            } else {
                z13 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z13, i13 & 1)) {
                if (i20 != 0) {
                    commonAnalytics3 = null;
                } else {
                    commonAnalytics3 = commonAnalytics;
                }
                if (i16 != 0) {
                    z15 = false;
                } else {
                    z15 = z12;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1077340530, i13, i18, "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerScreen (CustomServerScreen.kt:79)");
                }
                i19 = i18 & 112;
                if (i19 == 32) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                int i21 = i18;
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z16 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.q
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CustomServerScreenKt.CustomServerScreen$lambda$0$0(onBackClick);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                BackHandlerKt.BackHandler(false, (Function0) objRememberedValue, composerStartRestartGroup, 0, 1);
                scrollStateRememberScrollState = ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1);
                if ((57344 & i13) == 16384) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                zChanged = composerStartRestartGroup.changed(scrollStateRememberScrollState) | z17;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new CustomServerScreenKt$CustomServerScreen$2$1(serverParamsErrorsProvider, scrollStateRememberScrollState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(serverParamsProvider, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, (i13 >> 9) & 14);
                stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getEmail(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.isImapProtocol(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getIncomeHost(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle4 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getIncomePort(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle5 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.isIncomeSSLEnabled(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle6 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getOutcomeHost(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle7 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getOutcomePort(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle8 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.isOutcomeSSLEnabled(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle9 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.getMainErrorMessage(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle10 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isIncomeHostError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle11 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isIncomePortError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle12 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isIncomeSSLError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle13 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isOutgoingHostError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle14 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isOutgoingPortError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle15 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isOutgoingSSLError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle16 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isPikachuCaptchaCodeError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle17 = FlowExtKt.collectAsStateWithLifecycle(pikachuCaptchaProvider.isNeedShowCaptcha(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle18 = FlowExtKt.collectAsStateWithLifecycle(pikachuCaptchaProvider.isCaptchaLoading(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                final State stateCollectAsStateWithLifecycle19 = FlowExtKt.collectAsStateWithLifecycle(pikachuCaptchaProvider.getCaptchaBitmap(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                companion = Composer.INSTANCE;
                if (objRememberedValue3 == companion.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.y
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return CustomServerScreenKt.CustomServerScreen$lambda$21$0(pikachuCaptchaProvider, (String) obj);
                        }
                    };
                    composer3 = composerStartRestartGroup;
                    composer3.updateRememberedValue(objRememberedValue3);
                } else {
                    composer3 = composerStartRestartGroup;
                }
                final Function1 function1 = (Function1) objRememberedValue3;
                objRememberedValue4 = composer3.rememberedValue();
                if (objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.z
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CustomServerScreenKt.CustomServerScreen$lambda$22$0(pikachuCaptchaProvider);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue4);
                }
                final Function0 function0 = (Function0) objRememberedValue4;
                objRememberedValue5 = composer3.rememberedValue();
                if (objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.a0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return CustomServerScreenKt.CustomServerScreen$lambda$23$0(commonAnalytics3, ((Boolean) obj).booleanValue());
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue5);
                }
                final Function1 function2 = (Function1) objRememberedValue5;
                objRememberedValue6 = composer3.rememberedValue();
                if (objRememberedValue6 == companion.getEmpty()) {
                    objRememberedValue6 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.b0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return CustomServerScreenKt.CustomServerScreen$lambda$24$0(commonAnalytics3, stateCollectAsStateWithLifecycle, ((Boolean) obj).booleanValue());
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue6);
                }
                final Function1 function3 = (Function1) objRememberedValue6;
                String strStringResource = StringResources_androidKt.stringResource(R.string.add_your_email, composer3, 0);
                ToolbarConfig toolbarConfig = new ToolbarConfig(Integer.valueOf(R.drawable.ic_left), Color.m5448boximpl(((CustomColors) composer3.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU()), Color.m5448boximpl(((CustomColors) composer3.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU()), Color.m5448boximpl(((CustomColors) composer3.consume(MailThemeKt.getLocalCustomColors())).m15404getBgColor0d7_KjU()), null);
                if (i19 == 32) {
                    z18 = true;
                } else {
                    z18 = false;
                }
                objRememberedValue7 = composer3.rememberedValue();
                if (z18 || objRememberedValue7 == companion.getEmpty()) {
                    objRememberedValue7 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.c0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CustomServerScreenKt.CustomServerScreen$lambda$25$0(onBackClick);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue7);
                }
                Function0 function4 = (Function0) objRememberedValue7;
                Composer composer4 = composer3;
                final boolean z19 = z15;
                CommonAnalytics commonAnalytics4 = commonAnalytics3;
                SmallToolbarKt.SmallTopAppBar(strStringResource, toolbarConfig, null, z19, function4, ComposableLambdaKt.rememberComposableLambda(1111554115, true, new Function3() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.d0
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26(scrollStateRememberScrollState, z11, z19, serverParamsProvider, loginSuggestions, onEmailChange, onChooseSuggest, onDismissSuggests, function2, function3, onPasswordVisibilityChange, pikachuCaptchaProvider, function1, function0, sendServerParams, stateCollectAsStateWithLifecycle9, z10, stateCollectAsStateWithLifecycle2, stateCollectAsStateWithLifecycle3, stateCollectAsStateWithLifecycle10, stateCollectAsStateWithLifecycle4, stateCollectAsStateWithLifecycle11, stateCollectAsStateWithLifecycle12, stateCollectAsStateWithLifecycle5, stateCollectAsStateWithLifecycle6, stateCollectAsStateWithLifecycle13, stateCollectAsStateWithLifecycle7, stateCollectAsStateWithLifecycle14, stateCollectAsStateWithLifecycle15, stateCollectAsStateWithLifecycle8, stateCollectAsStateWithLifecycle17, stateCollectAsStateWithLifecycle16, stateCollectAsStateWithLifecycle18, stateCollectAsStateWithLifecycle19, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                }, composer4, 54), composer4, (i21 & 7168) | 196608, 4);
                composer2 = composer4;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z14 = z19;
                commonAnalytics2 = commonAnalytics4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                commonAnalytics2 = commonAnalytics;
                z14 = z12;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.e0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$27(loginSuggestions, z10, onPasswordVisibilityChange, serverParamsProvider, serverParamsErrorsProvider, pikachuCaptchaProvider, onEmailChange, onChooseSuggest, onDismissSuggests, commonAnalytics2, z11, onBackClick, sendServerParams, z14, i10, i11, i12, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i13 |= 805306368;
        if ((i11 & 6) == 0) {
            i14 = i11 | (composerStartRestartGroup.changed(z11) ? 4 : 2);
        } else {
            i14 = i11;
        }
        if ((i11 & 48) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(onBackClick) ? 32 : 16;
        }
        if ((i11 & KyberEngine.KyberPolyBytes) == 0) {
            i14 |= composerStartRestartGroup.changedInstance(sendServerParams) ? 256 : 128;
        }
        i15 = i14;
        i16 = i12 & 8192;
        if (i16 != 0) {
            i18 = i15 | 3072;
        } else {
            i17 = i15;
            if ((i11 & 3072) != 0) {
                i17 |= composerStartRestartGroup.changed(z12) ? 2048 : 1024;
            }
            i18 = i17;
        }
        if ((i13 & 306783379) == 306783378) {
            z13 = true;
        } else {
            z13 = true;
        }
        if (composerStartRestartGroup.shouldExecute(z13, i13 & 1)) {
            if (i20 != 0) {
                commonAnalytics3 = null;
            } else {
                commonAnalytics3 = commonAnalytics;
            }
            if (i16 != 0) {
                z15 = false;
            } else {
                z15 = z12;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1077340530, i13, i18, "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerScreen (CustomServerScreen.kt:79)");
            }
            i19 = i18 & 112;
            if (i19 == 32) {
                z16 = true;
            } else {
                z16 = false;
            }
            int i22 = i18;
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z16) {
                objRememberedValue = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.q
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CustomServerScreenKt.CustomServerScreen$lambda$0$0(onBackClick);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.q
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CustomServerScreenKt.CustomServerScreen$lambda$0$0(onBackClick);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            BackHandlerKt.BackHandler(false, (Function0) objRememberedValue, composerStartRestartGroup, 0, 1);
            scrollStateRememberScrollState = ScrollKt.rememberScrollState(0, composerStartRestartGroup, 0, 1);
            if ((57344 & i13) == 16384) {
                z17 = true;
            } else {
                z17 = false;
            }
            zChanged = composerStartRestartGroup.changed(scrollStateRememberScrollState) | z17;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue2 = new CustomServerScreenKt$CustomServerScreen$2$1(serverParamsErrorsProvider, scrollStateRememberScrollState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new CustomServerScreenKt$CustomServerScreen$2$1(serverParamsErrorsProvider, scrollStateRememberScrollState, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(serverParamsProvider, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue2, composerStartRestartGroup, (i13 >> 9) & 14);
            stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getEmail(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle20 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.isImapProtocol(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle21 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getIncomeHost(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle22 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getIncomePort(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle23 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.isIncomeSSLEnabled(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle24 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getOutcomeHost(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle25 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.getOutcomePort(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle26 = FlowExtKt.collectAsStateWithLifecycle(serverParamsProvider.isOutcomeSSLEnabled(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle27 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.getMainErrorMessage(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle110 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isIncomeHostError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle111 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isIncomePortError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle112 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isIncomeSSLError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle113 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isOutgoingHostError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle114 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isOutgoingPortError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle115 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isOutgoingSSLError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle116 = FlowExtKt.collectAsStateWithLifecycle(serverParamsErrorsProvider.isPikachuCaptchaCodeError(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle117 = FlowExtKt.collectAsStateWithLifecycle(pikachuCaptchaProvider.isNeedShowCaptcha(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle118 = FlowExtKt.collectAsStateWithLifecycle(pikachuCaptchaProvider.isCaptchaLoading(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            final State stateCollectAsStateWithLifecycle119 = FlowExtKt.collectAsStateWithLifecycle(pikachuCaptchaProvider.getCaptchaBitmap(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            companion = Composer.INSTANCE;
            if (objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.y
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$21$0(pikachuCaptchaProvider, (String) obj);
                    }
                };
                composer3 = composerStartRestartGroup;
                composer3.updateRememberedValue(objRememberedValue3);
            } else {
                composer3 = composerStartRestartGroup;
            }
            final Function1 function5 = (Function1) objRememberedValue3;
            objRememberedValue4 = composer3.rememberedValue();
            if (objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.z
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CustomServerScreenKt.CustomServerScreen$lambda$22$0(pikachuCaptchaProvider);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue4);
            }
            final Function0 function6 = (Function0) objRememberedValue4;
            objRememberedValue5 = composer3.rememberedValue();
            if (objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.a0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$23$0(commonAnalytics3, ((Boolean) obj).booleanValue());
                    }
                };
                composer3.updateRememberedValue(objRememberedValue5);
            }
            final Function1 function7 = (Function1) objRememberedValue5;
            objRememberedValue6 = composer3.rememberedValue();
            if (objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.b0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$24$0(commonAnalytics3, stateCollectAsStateWithLifecycle, ((Boolean) obj).booleanValue());
                    }
                };
                composer3.updateRememberedValue(objRememberedValue6);
            }
            final Function1 function8 = (Function1) objRememberedValue6;
            String strStringResource2 = StringResources_androidKt.stringResource(R.string.add_your_email, composer3, 0);
            ToolbarConfig toolbarConfig2 = new ToolbarConfig(Integer.valueOf(R.drawable.ic_left), Color.m5448boximpl(((CustomColors) composer3.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU()), Color.m5448boximpl(((CustomColors) composer3.consume(MailThemeKt.getLocalCustomColors())).m15449getTextPrimary0d7_KjU()), Color.m5448boximpl(((CustomColors) composer3.consume(MailThemeKt.getLocalCustomColors())).m15404getBgColor0d7_KjU()), null);
            if (i19 == 32) {
                z18 = true;
            } else {
                z18 = false;
            }
            objRememberedValue7 = composer3.rememberedValue();
            if (z18) {
                objRememberedValue7 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.c0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CustomServerScreenKt.CustomServerScreen$lambda$25$0(onBackClick);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue7);
            } else {
                objRememberedValue7 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.c0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CustomServerScreenKt.CustomServerScreen$lambda$25$0(onBackClick);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue7);
            }
            Function0 function9 = (Function0) objRememberedValue7;
            Composer composer5 = composer3;
            final boolean z110 = z15;
            CommonAnalytics commonAnalytics5 = commonAnalytics3;
            SmallToolbarKt.SmallTopAppBar(strStringResource2, toolbarConfig2, null, z110, function9, ComposableLambdaKt.rememberComposableLambda(1111554115, true, new Function3() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.d0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return CustomServerScreenKt.CustomServerScreen$lambda$26(scrollStateRememberScrollState, z11, z110, serverParamsProvider, loginSuggestions, onEmailChange, onChooseSuggest, onDismissSuggests, function7, function8, onPasswordVisibilityChange, pikachuCaptchaProvider, function5, function6, sendServerParams, stateCollectAsStateWithLifecycle27, z10, stateCollectAsStateWithLifecycle20, stateCollectAsStateWithLifecycle21, stateCollectAsStateWithLifecycle110, stateCollectAsStateWithLifecycle22, stateCollectAsStateWithLifecycle111, stateCollectAsStateWithLifecycle112, stateCollectAsStateWithLifecycle23, stateCollectAsStateWithLifecycle24, stateCollectAsStateWithLifecycle113, stateCollectAsStateWithLifecycle25, stateCollectAsStateWithLifecycle114, stateCollectAsStateWithLifecycle115, stateCollectAsStateWithLifecycle26, stateCollectAsStateWithLifecycle117, stateCollectAsStateWithLifecycle116, stateCollectAsStateWithLifecycle118, stateCollectAsStateWithLifecycle119, (PaddingValues) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composer5, 54), composer5, (i22 & 7168) | 196608, 4);
            composer2 = composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z14 = z110;
            commonAnalytics2 = commonAnalytics5;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            commonAnalytics2 = commonAnalytics;
            z14 = z12;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.e0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return CustomServerScreenKt.CustomServerScreen$lambda$27(loginSuggestions, z10, onPasswordVisibilityChange, serverParamsProvider, serverParamsErrorsProvider, pikachuCaptchaProvider, onEmailChange, onChooseSuggest, onDismissSuggests, commonAnalytics2, z11, onBackClick, sendServerParams, z14, i10, i11, i12, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$0$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    private static final String CustomServerScreen$lambda$10(State<String> state) {
        return state.getValue();
    }

    private static final boolean CustomServerScreen$lambda$11(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$12(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$13(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$14(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$15(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$16(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$17(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$18(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CustomServerScreen$lambda$19(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final TextFieldValue CustomServerScreen$lambda$2(State<TextFieldValue> state) {
        return state.getValue();
    }

    private static final ImageBitmap CustomServerScreen$lambda$20(State<? extends ImageBitmap> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$21$0(PikachuCaptchaProvider pikachuCaptchaProvider, String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        pikachuCaptchaProvider.onUpdateCaptchaCode(code);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$22$0(PikachuCaptchaProvider pikachuCaptchaProvider) {
        pikachuCaptchaProvider.needNewCaptchaCode();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$23$0(CommonAnalytics commonAnalytics, boolean z10) {
        if (commonAnalytics != null) {
            commonAnalytics.loginFocusLogin(z10);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$24$0(CommonAnalytics commonAnalytics, State state, boolean z10) {
        if (commonAnalytics != null) {
            commonAnalytics.loginFocusPassword(z10, CustomServerScreen$lambda$2(state).getText());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$25$0(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 17985, key = 1111554115, startOffset = 7075)
    @Composable
    public static final Unit CustomServerScreen$lambda$26(ScrollState scrollState, boolean z10, boolean z11, final ServerParamsProvider serverParamsProvider, EmailSuggestionsProvider emailSuggestionsProvider, Function1 function1, Function1 function2, Function0 function0, Function1 function3, Function1 function4, Function1 function5, PikachuCaptchaProvider pikachuCaptchaProvider, Function1 function6, Function0 function7, Function0 function8, State state, boolean z12, State state2, State state3, State state4, State state5, State state6, State state7, State state8, State state9, State state10, State state11, State state12, State state13, State state14, State state15, State state16, State state17, State state18, PaddingValues it, Composer composer, int i10) {
        int i11;
        boolean z13;
        int i12;
        Modifier.Companion companion;
        RowScopeInstance rowScopeInstance;
        float f10;
        Object obj;
        long jM15450getTextSecondary0d7_KjU;
        boolean z14;
        long jM15450getTextSecondary0d7_KjU2;
        Intrinsics.checkNotNullParameter(it, "it");
        if ((i10 & 6) == 0) {
            i11 = i10 | (composer.changed(it) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if (composer.shouldExecute((i11 & 19) != 18, i11 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1111554115, i11, -1, "ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerScreen.<anonymous> (CustomServerScreen.kt:135)");
            }
            Modifier.Companion companion2 = Modifier.INSTANCE;
            float f11 = 4;
            Modifier modifierVerticalScrollWithScrollbar$default = ScrollbarKt.verticalScrollWithScrollbar$default(SizeKt.fillMaxSize$default(PaddingKt.m1018paddingqDBjuR0$default(WindowInsetsPadding_androidKt.imePadding(companion2), 0.0f, Dp.m8268constructorimpl(it.getTop() + Dp.m8268constructorimpl(f11)), 0.0f, 0.0f, 13, null), 0.0f, 1, null), scrollState, false, null, false, new ScrollBarConfig(0.0f, ((CustomColors) composer.consume(MailThemeKt.getLocalCustomColors())).m15398getBackgroundSecondary0d7_KjU(), null, null, PaddingKt.m1010PaddingValuesa9UjIt4(Dp.m8268constructorimpl(f11), Dp.m8268constructorimpl(f11), Dp.m8268constructorimpl(f11), Dp.m8268constructorimpl(f11)), 13, null), 14, null);
            Arrangement arrangement = Arrangement.INSTANCE;
            Arrangement.Vertical top = arrangement.getTop();
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, companion3.getStart(), composer, 0);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierVerticalScrollWithScrollbar$default);
            ComposeUiNode.Companion companion4 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion4.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM4589constructorimpl = Updater.m4589constructorimpl(composer);
            Updater.m4597setimpl(composerM4589constructorimpl, measurePolicyColumnMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m4597setimpl(composerM4589constructorimpl, currentCompositionLocalMap, companion4.getSetResolvedCompositionLocals());
            Updater.m4597setimpl(composerM4589constructorimpl, Integer.valueOf(iHashCode), companion4.getSetCompositeKeyHash());
            Updater.m4595reconcileimpl(composerM4589constructorimpl, companion4.getApplyOnDeactivatedNodeAssertion());
            Updater.m4597setimpl(composerM4589constructorimpl, modifierMaterializeModifier, companion4.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            float f12 = 22;
            float f13 = 8;
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(BackgroundKt.m281backgroundbw27NRU(PaddingKt.m1017paddingqDBjuR0(companion2, Dp.m8268constructorimpl(f12), Dp.m8268constructorimpl(f13), Dp.m8268constructorimpl(f12), Dp.m8268constructorimpl(f13)), ((CustomColors) composer.consume(MailThemeKt.getLocalCustomColors())).m15398getBackgroundSecondary0d7_KjU(), RoundedCornerShapeKt.m1318RoundedCornerShape0680j_4(Dp.m8268constructorimpl(12))), 0.0f, 1, null);
            MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion3.getStart(), composer, 0);
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor2 = companion4.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM4589constructorimpl2 = Updater.m4589constructorimpl(composer);
            Updater.m4597setimpl(composerM4589constructorimpl2, measurePolicyColumnMeasurePolicy2, companion4.getSetMeasurePolicy());
            Updater.m4597setimpl(composerM4589constructorimpl2, currentCompositionLocalMap2, companion4.getSetResolvedCompositionLocals());
            Updater.m4597setimpl(composerM4589constructorimpl2, Integer.valueOf(iHashCode2), companion4.getSetCompositeKeyHash());
            Updater.m4595reconcileimpl(composerM4589constructorimpl2, companion4.getApplyOnDeactivatedNodeAssertion());
            Updater.m4597setimpl(composerM4589constructorimpl2, modifierMaterializeModifier2, companion4.getSetModifier());
            HeaderTextAllCapsKt.HeaderTextAllCaps(StringResources_androidKt.stringResource(R.string.other_mail, composer, 0), composer, 0);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            Modifier modifierNodeTestModifier = NodeTestModifierKt.nodeTestModifier(companion2, z11, USER_LOGIN_FIELD_TAG);
            ImeAction.Companion companion5 = ImeAction.INSTANCE;
            int iM7885getNexteUduSuo = companion5.m7885getNexteUduSuo();
            Color.Companion companion6 = Color.INSTANCE;
            long jM5493getTransparent0d7_KjU = companion6.m5493getTransparent0d7_KjU();
            long jM5493getTransparent0d7_KjU2 = companion6.m5493getTransparent0d7_KjU();
            ComposableSingletons$CustomServerScreenKt composableSingletons$CustomServerScreenKt = ComposableSingletons$CustomServerScreenKt.INSTANCE;
            EmailSuggestionsTextFieldKt.m15279EmailSuggestionsTextFieldUNqB0sA(modifierNodeTestModifier, serverParamsProvider, emailSuggestionsProvider, null, null, function1, function2, function0, null, function3, 96, iM7885getNexteUduSuo, jM5493getTransparent0d7_KjU, jM5493getTransparent0d7_KjU2, composableSingletons$CustomServerScreenKt.getLambda$272678371$authorizationsdk_release(), composableSingletons$CustomServerScreenKt.m14722getLambda$1115780572$authorizationsdk_release(), false, false, composer, 805306368, 224694, 196888);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            String value = serverParamsProvider.getPassword().getValue();
            long jM5493getTransparent0d7_KjU3 = companion6.m5493getTransparent0d7_KjU();
            boolean zChanged = composer.changed(serverParamsProvider);
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.f0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$0$0(serverParamsProvider, (String) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            PasswordTextFieldKt.m15281PasswordTextFieldOkTjGUA(null, value, false, function4, function5, (Function1) objRememberedValue, null, jM5493getTransparent0d7_KjU3, composableSingletons$CustomServerScreenKt.getLambda$1094951352$authorizationsdk_release(), composableSingletons$CustomServerScreenKt.m14723getLambda$1156694279$authorizationsdk_release(), null, null, null, composer, 918555648, 0, 7237);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            HeaderTextKt.m15268HeaderTextVG8dh0g(StringResources_androidKt.stringResource(R.string.please_check_server, composer, 0), null, 0.0f, 0L, 0L, null, 0, null, composer, 0, 254);
            Composer composer2 = composer;
            if (CustomServerScreen$lambda$10(state).length() > 0) {
                composer2.startReplaceGroup(-1167932565);
                ErrorHighlightTextKt.m15267ErrorHighlightText5fiNW4Q(CustomServerScreen$lambda$10(state), 0L, z11, composer2, 0, 2);
                z13 = z11;
            } else {
                z13 = z11;
                composer2.startReplaceGroup(-1178044021);
            }
            composer2.endReplaceGroup();
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer2, 0, 3);
            Modifier modifierM1062height3ABfNKs = SizeKt.m1062height3ABfNKs(SizeKt.fillMaxWidth$default(companion2, 0.0f, 1, null), Dp.m8268constructorimpl(48));
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), companion3.getTop(), composer2, 0);
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierM1062height3ABfNKs);
            Function0<ComposeUiNode> constructor3 = companion4.getConstructor();
            if (composer2.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor3);
            } else {
                composer2.useNode();
            }
            Composer composerM4589constructorimpl3 = Updater.m4589constructorimpl(composer2);
            Updater.m4597setimpl(composerM4589constructorimpl3, measurePolicyRowMeasurePolicy, companion4.getSetMeasurePolicy());
            Updater.m4597setimpl(composerM4589constructorimpl3, currentCompositionLocalMap3, companion4.getSetResolvedCompositionLocals());
            Updater.m4597setimpl(composerM4589constructorimpl3, Integer.valueOf(iHashCode3), companion4.getSetCompositeKeyHash());
            Updater.m4595reconcileimpl(composerM4589constructorimpl3, companion4.getApplyOnDeactivatedNodeAssertion());
            Updater.m4597setimpl(composerM4589constructorimpl3, modifierMaterializeModifier3, companion4.getSetModifier());
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            if (z12) {
                i12 = 1;
                companion = companion2;
                rowScopeInstance = rowScopeInstance2;
                f10 = 0.0f;
                obj = null;
                composer2.startReplaceGroup(774944527);
            } else {
                composer2.startReplaceGroup(785426464);
                rowScopeInstance = rowScopeInstance2;
                companion = companion2;
                Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(NodeTestModifierKt.nodeTestModifier(RowScope.weight$default(rowScopeInstance2, companion2, 1.0f, false, 2, null), z13, POP_PROTOCOL_BTN_TAG), 0.0f, 1, null);
                RoundedCornerShape roundedCornerShapeM1318RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1318RoundedCornerShape0680j_4(Dp.m8268constructorimpl(0));
                ButtonDefaults buttonDefaults = ButtonDefaults.INSTANCE;
                long jM5493getTransparent0d7_KjU4 = companion6.m5493getTransparent0d7_KjU();
                if (CustomServerScreen$lambda$3(state2)) {
                    composer2.startReplaceGroup(786191296);
                    jM15450getTextSecondary0d7_KjU2 = ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15450getTextSecondary0d7_KjU();
                    composer2.endReplaceGroup();
                } else {
                    composer2.startReplaceGroup(786073310);
                    jM15450getTextSecondary0d7_KjU2 = ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15417getContrastPrimary0d7_KjU();
                    composer2.endReplaceGroup();
                }
                f10 = 0.0f;
                ButtonColors buttonColorsM2381buttonColorsro_MJ88 = buttonDefaults.m2381buttonColorsro_MJ88(jM5493getTransparent0d7_KjU4, jM15450getTextSecondary0d7_KjU2, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 12);
                boolean zChanged2 = composer2.changed(serverParamsProvider);
                Object objRememberedValue2 = composer2.rememberedValue();
                if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.g0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$1$0$0(serverParamsProvider);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue2);
                }
                ButtonKt.Button((Function0) objRememberedValue2, modifierFillMaxHeight$default, false, roundedCornerShapeM1318RoundedCornerShape0680j_4, buttonColorsM2381buttonColorsro_MJ88, null, null, null, null, composableSingletons$CustomServerScreenKt.getLambda$607861064$authorizationsdk_release(), composer, 807075840, 388);
                composer2 = composer;
                i12 = 1;
                obj = null;
                DividerKt.m2654Divider9IZ8Weo(SizeKt.m1081width3ABfNKs(SizeKt.fillMaxHeight$default(companion, 0.0f, 1, null), Dp.m8268constructorimpl(1)), 0.0f, ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15435getMailDivider0d7_KjU(), composer2, 6, 2);
            }
            composer2.endReplaceGroup();
            Modifier modifierFillMaxHeight$default2 = SizeKt.fillMaxHeight$default(NodeTestModifierKt.nodeTestModifier(RowScope.weight$default(rowScopeInstance, companion, 1.0f, false, 2, null), z13, IMAP_PROTOCOL_BTN_TAG), f10, i12, obj);
            RoundedCornerShape roundedCornerShapeM1318RoundedCornerShape0680j_5 = RoundedCornerShapeKt.m1318RoundedCornerShape0680j_4(Dp.m8268constructorimpl(0));
            ButtonDefaults buttonDefaults2 = ButtonDefaults.INSTANCE;
            long jM5493getTransparent0d7_KjU5 = companion6.m5493getTransparent0d7_KjU();
            if (CustomServerScreen$lambda$3(state2)) {
                composer2.startReplaceGroup(787562054);
                jM15450getTextSecondary0d7_KjU = ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15417getContrastPrimary0d7_KjU();
                composer2.endReplaceGroup();
            } else {
                composer2.startReplaceGroup(787672104);
                jM15450getTextSecondary0d7_KjU = ((CustomColors) composer2.consume(MailThemeKt.getLocalCustomColors())).m15450getTextSecondary0d7_KjU();
                composer2.endReplaceGroup();
            }
            ButtonColors buttonColorsM2381buttonColorsro_MJ89 = buttonDefaults2.m2381buttonColorsro_MJ88(jM5493getTransparent0d7_KjU5, jM15450getTextSecondary0d7_KjU, 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 6, 12);
            boolean zChanged3 = composer2.changed(serverParamsProvider);
            Object objRememberedValue3 = composer2.rememberedValue();
            if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.r
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$1$1$0(serverParamsProvider);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue3);
            }
            ButtonKt.Button((Function0) objRememberedValue3, modifierFillMaxHeight$default2, false, roundedCornerShapeM1318RoundedCornerShape0680j_5, buttonColorsM2381buttonColorsro_MJ89, null, null, null, null, composableSingletons$CustomServerScreenKt.m14724getLambda$880978941$authorizationsdk_release(), composer2, 807075840, 388);
            composer.endNode();
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            HeaderTextKt.m15268HeaderTextVG8dh0g(StringResources_androidKt.stringResource(R.string.incoming_server, composer, 0), null, 0.0f, 0L, 0L, null, 0, null, composer, 0, 254);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            int i13 = R.string.host;
            String strStringResource = StringResources_androidKt.stringResource(i13, composer, 0);
            String strCustomServerScreen$lambda$4 = CustomServerScreen$lambda$4(state3);
            boolean zCustomServerScreen$lambda$11 = CustomServerScreen$lambda$11(state4);
            int iM7885getNexteUduSuo2 = companion5.m7885getNexteUduSuo();
            boolean zChanged4 = composer.changed(serverParamsProvider);
            Object objRememberedValue4 = composer.rememberedValue();
            if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.s
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$2$0(serverParamsProvider, (String) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            HostTextFieldKt.m15274HostTextField7FxtGnE(strStringResource, strCustomServerScreen$lambda$4, zCustomServerScreen$lambda$11, iM7885getNexteUduSuo2, INCOME_HOST_ET_TAG, z11, (Function1) objRememberedValue4, composer, 27648, 0);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            int i14 = R.string.port;
            String strStringResource2 = StringResources_androidKt.stringResource(i14, composer, 0);
            String strCustomServerScreen$lambda$5 = CustomServerScreen$lambda$5(state5);
            boolean zCustomServerScreen$lambda$12 = CustomServerScreen$lambda$12(state6);
            int iM7885getNexteUduSuo3 = companion5.m7885getNexteUduSuo();
            boolean zChanged5 = composer.changed(serverParamsProvider);
            Object objRememberedValue5 = composer.rememberedValue();
            if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.t
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$3$0(serverParamsProvider, (String) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            PortTextFieldKt.m15276PortTextField7FxtGnE(strStringResource2, strCustomServerScreen$lambda$5, zCustomServerScreen$lambda$12, iM7885getNexteUduSuo3, INCOME_PORT_ET_TAG, z11, (Function1) objRememberedValue5, composer, 27648, 0);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            int i15 = R.string.ssl;
            String strStringResource3 = StringResources_androidKt.stringResource(i15, composer, 0);
            boolean zCustomServerScreen$lambda$13 = CustomServerScreen$lambda$13(state7);
            int i16 = R.string.ssl_on;
            String strStringResource4 = StringResources_androidKt.stringResource(i16, composer, 0);
            int i17 = R.string.ssl_off;
            String strStringResource5 = StringResources_androidKt.stringResource(i17, composer, 0);
            boolean zCustomServerScreen$lambda$6 = CustomServerScreen$lambda$6(state8);
            boolean zChanged6 = composer.changed(serverParamsProvider);
            Object objRememberedValue6 = composer.rememberedValue();
            if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue6 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.u
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$4$0(serverParamsProvider, ((Boolean) obj2).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue6);
            }
            ChoiceWithTitleKt.ChoiceWithTitle(strStringResource3, zCustomServerScreen$lambda$13, strStringResource4, strStringResource5, zCustomServerScreen$lambda$6, INCOME_SSL_BTN_TAG, z11, (Function1) objRememberedValue6, composer, 196608, 0);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            Modifier.Companion companion7 = companion;
            HeaderTextKt.m15268HeaderTextVG8dh0g(StringResources_androidKt.stringResource(R.string.outgoing_server, composer, 0), null, 0.0f, 0L, 0L, null, 0, null, composer, 0, 254);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            String strStringResource6 = StringResources_androidKt.stringResource(i13, composer, 0);
            String strCustomServerScreen$lambda$7 = CustomServerScreen$lambda$7(state9);
            boolean zCustomServerScreen$lambda$14 = CustomServerScreen$lambda$14(state10);
            int iM7885getNexteUduSuo4 = companion5.m7885getNexteUduSuo();
            boolean zChanged7 = composer.changed(serverParamsProvider);
            Object objRememberedValue7 = composer.rememberedValue();
            if (zChanged7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.v
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$5$0(serverParamsProvider, (String) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue7);
            }
            HostTextFieldKt.m15274HostTextField7FxtGnE(strStringResource6, strCustomServerScreen$lambda$7, zCustomServerScreen$lambda$14, iM7885getNexteUduSuo4, OUTCOME_HOST_ET_TAG, z11, (Function1) objRememberedValue7, composer, 27648, 0);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            String strStringResource7 = StringResources_androidKt.stringResource(i14, composer, 0);
            String strCustomServerScreen$lambda$8 = CustomServerScreen$lambda$8(state11);
            boolean zCustomServerScreen$lambda$15 = CustomServerScreen$lambda$15(state12);
            int iM7885getNexteUduSuo5 = companion5.m7885getNexteUduSuo();
            boolean zChanged8 = composer.changed(serverParamsProvider);
            Object objRememberedValue8 = composer.rememberedValue();
            if (zChanged8 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.w
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$6$0(serverParamsProvider, (String) obj2);
                    }
                };
                composer.updateRememberedValue(objRememberedValue8);
            }
            PortTextFieldKt.m15276PortTextField7FxtGnE(strStringResource7, strCustomServerScreen$lambda$8, zCustomServerScreen$lambda$15, iM7885getNexteUduSuo5, OUTCOME_PORT_ET_TAG, z11, (Function1) objRememberedValue8, composer, 27648, 0);
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            String strStringResource8 = StringResources_androidKt.stringResource(i15, composer, 0);
            boolean zCustomServerScreen$lambda$16 = CustomServerScreen$lambda$16(state13);
            String strStringResource9 = StringResources_androidKt.stringResource(i16, composer, 0);
            String strStringResource10 = StringResources_androidKt.stringResource(i17, composer, 0);
            boolean zCustomServerScreen$lambda$9 = CustomServerScreen$lambda$9(state14);
            boolean zChanged9 = composer.changed(serverParamsProvider);
            Object objRememberedValue9 = composer.rememberedValue();
            if (zChanged9 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue9 = new Function1() { // from class: ru.mail.authorizationsdk.feature.customserver.presentation.x
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CustomServerScreenKt.CustomServerScreen$lambda$26$0$0$7$0(serverParamsProvider, ((Boolean) obj2).booleanValue());
                    }
                };
                composer.updateRememberedValue(objRememberedValue9);
            }
            ChoiceWithTitleKt.ChoiceWithTitle(strStringResource8, zCustomServerScreen$lambda$16, strStringResource9, strStringResource10, zCustomServerScreen$lambda$9, OUTCOME_SSL_BTN_TAG, z11, (Function1) objRememberedValue9, composer, 196608, 0);
            if (CustomServerScreen$lambda$18(state15)) {
                composer.startReplaceGroup(-1161637736);
                PickachuCaptchaKt.PikachuCaptcha(StringResources_androidKt.stringResource(R.string.enter_protection_code, composer, 0), StringResources_androidKt.stringResource(R.string.code, composer, 0), z11, CustomServerScreen$lambda$17(state16), StringResources_androidKt.stringResource(R.string.hint_required, composer, 0), CustomServerScreen$lambda$19(state17), pikachuCaptchaProvider.getCaptchaCode().getValue(), function6, CustomServerScreen$lambda$20(state18), function7, R.drawable.ic_refresh, composer, 817889280, 0, 0);
                z14 = z11;
            } else {
                z14 = z11;
                composer.startReplaceGroup(-1178044021);
            }
            composer.endReplaceGroup();
            Line1DpDividerKt.m15260Line1DpDivideriJQMabo(null, 0L, composer, 0, 3);
            float f14 = 16;
            MailSansDispButtonKt.MailSansDisplayButton(NodeTestModifierKt.nodeTestModifier(SizeKt.m1062height3ABfNKs(PaddingKt.m1015paddingVpY3zN4(SizeKt.fillMaxWidth$default(companion7, 0.0f, 1, null), Dp.m8268constructorimpl(f14), Dp.m8268constructorimpl(f14)), Dp.m8268constructorimpl(44)), z14, IMAP_SIGN_IN_BUTTON_TAG), StringResources_androidKt.stringResource(R.string.sign_in, composer, 0), null, function8, composer, 0, 4);
            composer.endNode();
            composer.endNode();
            if (z10) {
                composer.startReplaceGroup(-1243005183);
                AuthFlowProgressBarKt.m14817AuthFlowProgressBarvc5YOHI(0L, 0L, false, null, 5000L, composer, 24576, 15);
            } else {
                composer.startReplaceGroup(103768415);
            }
            composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$0$0(ServerParamsProvider serverParamsProvider, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        serverParamsProvider.onChangePassword(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$1$0$0(ServerParamsProvider serverParamsProvider) {
        serverParamsProvider.onChangeProtocol(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$1$1$0(ServerParamsProvider serverParamsProvider) {
        serverParamsProvider.onChangeProtocol(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$2$0(ServerParamsProvider serverParamsProvider, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        serverParamsProvider.onChangeIncomeHost(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$3$0(ServerParamsProvider serverParamsProvider, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        serverParamsProvider.onChangeIncomePort(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$4$0(ServerParamsProvider serverParamsProvider, boolean z10) {
        serverParamsProvider.onChangeIncomeSSL(z10);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$5$0(ServerParamsProvider serverParamsProvider, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        serverParamsProvider.onChangeOutcomeHost(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$6$0(ServerParamsProvider serverParamsProvider, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        serverParamsProvider.onChangeOutcomePort(it);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$26$0$0$7$0(ServerParamsProvider serverParamsProvider, boolean z10) {
        serverParamsProvider.onChangeOutcomeSSL(z10);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit CustomServerScreen$lambda$27(EmailSuggestionsProvider emailSuggestionsProvider, boolean z10, Function1 function1, ServerParamsProvider serverParamsProvider, ServerParamsErrorsProvider serverParamsErrorsProvider, PikachuCaptchaProvider pikachuCaptchaProvider, Function1 function2, Function1 function3, Function0 function0, CommonAnalytics commonAnalytics, boolean z11, Function0 function4, Function0 function5, boolean z12, int i10, int i11, int i12, Composer composer, int i13) {
        CustomServerScreen(emailSuggestionsProvider, z10, function1, serverParamsProvider, serverParamsErrorsProvider, pikachuCaptchaProvider, function2, function3, function0, commonAnalytics, z11, function4, function5, z12, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), RecomposeScopeImplKt.updateChangedFlags(i11), i12);
        return Unit.INSTANCE;
    }

    private static final boolean CustomServerScreen$lambda$3(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final String CustomServerScreen$lambda$4(State<String> state) {
        return state.getValue();
    }

    private static final String CustomServerScreen$lambda$5(State<String> state) {
        return state.getValue();
    }

    private static final boolean CustomServerScreen$lambda$6(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final String CustomServerScreen$lambda$7(State<String> state) {
        return state.getValue();
    }

    private static final String CustomServerScreen$lambda$8(State<String> state) {
        return state.getValue();
    }

    private static final boolean CustomServerScreen$lambda$9(State<Boolean> state) {
        return state.getValue().booleanValue();
    }
}
