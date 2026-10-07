package ru.mail.authorizationsdk.navigation.auth;

import android.os.Bundle;
import androidx.compose.runtime.Composable;
import androidx.compose.runtime.ComposableTarget;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.FunctionKeyMeta;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import androidx.navigation.NavBackStackEntry;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import ru.mail.authorizationsdk.di.AuthorizeSdkComponent;
import ru.mail.authorizationsdk.di.viewmodel.DaggerViewModelHelper;
import ru.mail.authorizationsdk.external.config.flavor.FlavorConfig;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDRouteKt;
import ru.mail.authorizationsdk.feature.beforerecovery.presentation.BeforeRecoveryVKIDViewModel;
import ru.mail.authorizationsdk.feature.bindemail.presentation.BindEmailRouteKt;
import ru.mail.authorizationsdk.feature.bindemail.presentation.BindEmailViewModel;
import ru.mail.authorizationsdk.feature.captcha.LudwigCaptchaRouteKt;
import ru.mail.authorizationsdk.feature.captcha.WebCaptchaComposeViewModel;
import ru.mail.authorizationsdk.feature.changepassword.presentation.ChangePasswordRouteKt;
import ru.mail.authorizationsdk.feature.changepassword.presentation.ChangePasswordViewModel;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerRouteKt;
import ru.mail.authorizationsdk.feature.customserver.presentation.CustomServerViewModel;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneRouteKt;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneViewModel;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccMigrationRouteKt;
import ru.mail.authorizationsdk.feature.externalmigration.presentation.ExternalAccMigrationViewModel;
import ru.mail.authorizationsdk.feature.google.nativelib.presentation.GoogleAssistedFactory;
import ru.mail.authorizationsdk.feature.google.nativelib.presentation.GoogleRouteKt;
import ru.mail.authorizationsdk.feature.google.nativelib.presentation.GoogleViewModel;
import ru.mail.authorizationsdk.feature.google.web.presentation.GoogleWebAuthAssistedFactory;
import ru.mail.authorizationsdk.feature.google.web.presentation.GoogleWebAuthRouteKt;
import ru.mail.authorizationsdk.feature.google.web.presentation.GoogleWebAuthViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.CloudLoginRouteKt;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.CloudLoginViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.createcloud.CreateCloudRouteKt;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloud.createcloud.CreateCloudViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloudvk.CloudLoginVKRouteKt;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.cloudvk.CloudLoginVKViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.mail.LoginRouteKt;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.mail.LoginViewModel;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.LoginVKRouteKt;
import ru.mail.authorizationsdk.feature.login.presentation.flavor.vkmail.LoginVKViewModel;
import ru.mail.authorizationsdk.feature.loginbindflow.presentation.LoginBindFlowRouteKt;
import ru.mail.authorizationsdk.feature.loginbindflow.presentation.LoginBindFlowViewModel;
import ru.mail.authorizationsdk.feature.mrim.MrimDialogViewModel;
import ru.mail.authorizationsdk.feature.mrim.MrimRouteKt;
import ru.mail.authorizationsdk.feature.ok.OKLoginRouteKt;
import ru.mail.authorizationsdk.feature.ok.presentation.OKLoginViewModel;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeRouteKt;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeViewModel;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookAssistedFactory;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookRouteKt;
import ru.mail.authorizationsdk.feature.outlook.presentation.OutlookViewModel;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordRouteKt;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordViewModel;
import ru.mail.authorizationsdk.feature.phone.accountlist.presentation.AccountListRouteKt;
import ru.mail.authorizationsdk.feature.phone.accountlist.presentation.AccountListViewModel;
import ru.mail.authorizationsdk.feature.phone.codereceivetype.presentation.CodeReceiveTypeBottomSheetsKt;
import ru.mail.authorizationsdk.feature.phone.codereceivetype.presentation.CodeReceivedTypeBottomSheetViewModel;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeRouteKt;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeViewModel;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeRouteKt;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeViewModel;
import ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccRouteKt;
import ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccViewModel;
import ru.mail.authorizationsdk.feature.phone.notreceivedcode.presentation.NotReceivedCodeBottomSheetKt;
import ru.mail.authorizationsdk.feature.phone.notreceivedcode.presentation.NotReceivedCodeBottomSheetViewModel;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainRouteKt;
import ru.mail.authorizationsdk.feature.registration.presentation.RegistrationMainViewModel;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordRouteKt;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordViewModel;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVKRouteKt;
import ru.mail.authorizationsdk.feature.restorevkpassword.presentation.RestoreVkViewModel;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondFactorRouteKt;
import ru.mail.authorizationsdk.feature.secondfactor.presentation.SecondStepViewModel;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.presentation.ChoiceAccountRouteKt;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.presentation.ChoiceAccountViewModel;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.presentation.EsiaRouteKt;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.presentation.EsiaViewModel;
import ru.mail.authorizationsdk.feature.sso.presentation.SSORouteKt;
import ru.mail.authorizationsdk.feature.sso.presentation.SSOViewModel;
import ru.mail.authorizationsdk.feature.unblockuser.presentation.UnblockUserRouteKt;
import ru.mail.authorizationsdk.feature.unblockuser.presentation.UnblockUserViewModel;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginRouteKt;
import ru.mail.authorizationsdk.feature.vkbindavailable.presentation.VkBindInLoginViewModel;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.WrongVkidAccountRouteKt;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.WrongVkidAccountViewModel;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordRouteKt;
import ru.mail.authorizationsdk.feature.vkpassword.presentation.VkPasswordViewModel;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooRouteKt;
import ru.mail.authorizationsdk.feature.yahoo.presentation.YahooViewModel;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexAssistedFactory;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexRouteKt;
import ru.mail.authorizationsdk.feature.yandex.presentation.YandexViewModel;
import ru.mail.authorizationsdk.feature.yandexhelp.YandexHelpRouteKt;
import ru.mail.authorizationsdk.feature.yandexhelp.YandexHelpViewModel;
import ru.mail.authorizationsdk.navigation.DestBase;
import ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nAuthNavGraph.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AuthNavGraph.kt\nru/mail/authorizationsdk/navigation/auth/ComposableSingletons$AuthNavGraphKt\n+ 2 DestBase.kt\nru/mail/authorizationsdk/navigation/DestBaseKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1379:1\n75#2,5:1380\n80#2:1386\n75#2,5:1387\n80#2:1393\n76#2:1394\n75#2,5:1395\n80#2:1401\n76#2:1402\n75#2,5:1403\n80#2:1409\n76#2:1410\n75#2,5:1411\n80#2:1417\n76#2:1418\n75#2,5:1419\n80#2:1425\n76#2:1426\n75#2,5:1427\n80#2:1433\n76#2:1434\n75#2,5:1435\n80#2:1441\n76#2:1442\n75#2,5:1443\n80#2:1449\n76#2:1450\n75#2,5:1451\n80#2:1457\n76#2:1458\n75#2,5:1459\n80#2:1465\n76#2:1466\n75#2,5:1467\n80#2:1473\n76#2:1474\n75#2,5:1475\n80#2:1481\n76#2:1482\n75#2,5:1483\n80#2:1489\n76#2:1490\n75#2,5:1491\n80#2:1497\n76#2:1498\n75#2,5:1499\n80#2:1505\n76#2:1506\n75#2,5:1507\n80#2:1513\n76#2:1514\n75#2,5:1515\n80#2:1521\n76#2:1522\n75#2,5:1523\n80#2:1529\n76#2:1530\n75#2,5:1531\n80#2:1537\n76#2:1538\n75#2,5:1539\n80#2:1545\n76#2:1546\n75#2,5:1547\n80#2:1553\n76#2:1554\n75#2,5:1555\n80#2:1561\n76#2:1562\n75#2,5:1563\n80#2:1569\n76#2:1570\n75#2,5:1571\n80#2:1577\n76#2:1578\n75#2,5:1579\n80#2:1585\n76#2:1586\n75#2,5:1587\n80#2:1593\n76#2:1594\n75#2,5:1595\n80#2:1601\n76#2:1602\n75#2,5:1603\n80#2:1609\n76#2:1610\n75#2,5:1611\n80#2:1617\n76#2:1618\n75#2,5:1619\n80#2:1625\n76#2:1626\n75#2,5:1627\n80#2:1633\n76#2:1634\n75#2,5:1635\n80#2:1641\n76#2:1642\n75#2,5:1643\n80#2:1649\n76#2:1650\n75#2,5:1651\n80#2:1657\n76#2:1658\n75#2,5:1659\n80#2:1665\n76#2:1666\n75#2,5:1667\n80#2:1673\n76#2:1674\n75#2,5:1675\n80#2:1681\n76#2:1682\n75#2,5:1683\n80#2:1689\n76#2:1690\n75#2,5:1691\n80#2:1697\n76#2:1698\n75#2,5:1699\n80#2:1705\n76#2:1706\n75#2,5:1707\n80#2:1713\n76#2:1714\n75#2,5:1715\n80#2:1721\n76#2:1722\n75#2,5:1723\n80#2:1729\n76#2:1730\n75#2,5:1731\n80#2:1737\n76#2:1738\n75#2,5:1739\n80#2:1745\n76#2:1746\n1#3:1385\n1#3:1392\n1#3:1400\n1#3:1408\n1#3:1416\n1#3:1424\n1#3:1432\n1#3:1440\n1#3:1448\n1#3:1456\n1#3:1464\n1#3:1472\n1#3:1480\n1#3:1488\n1#3:1496\n1#3:1504\n1#3:1512\n1#3:1520\n1#3:1528\n1#3:1536\n1#3:1544\n1#3:1552\n1#3:1560\n1#3:1568\n1#3:1576\n1#3:1584\n1#3:1592\n1#3:1600\n1#3:1608\n1#3:1616\n1#3:1624\n1#3:1632\n1#3:1640\n1#3:1648\n1#3:1656\n1#3:1664\n1#3:1672\n1#3:1680\n1#3:1688\n1#3:1696\n1#3:1704\n1#3:1712\n1#3:1720\n1#3:1728\n1#3:1736\n1#3:1744\n*S KotlinDebug\n*F\n+ 1 AuthNavGraph.kt\nru/mail/authorizationsdk/navigation/auth/ComposableSingletons$AuthNavGraphKt\n*L\n272#1:1380,5\n272#1:1386\n281#1:1387,5\n281#1:1393\n281#1:1394\n290#1:1395,5\n290#1:1401\n290#1:1402\n336#1:1403,5\n336#1:1409\n336#1:1410\n382#1:1411,5\n382#1:1417\n382#1:1418\n391#1:1419,5\n391#1:1425\n391#1:1426\n403#1:1427,5\n403#1:1433\n403#1:1434\n451#1:1435,5\n451#1:1441\n451#1:1442\n460#1:1443,5\n460#1:1449\n460#1:1450\n472#1:1451,5\n472#1:1457\n472#1:1458\n513#1:1459,5\n513#1:1465\n513#1:1466\n557#1:1467,5\n557#1:1473\n557#1:1474\n586#1:1475,5\n586#1:1481\n586#1:1482\n610#1:1483,5\n610#1:1489\n610#1:1490\n629#1:1491,5\n629#1:1497\n629#1:1498\n648#1:1499,5\n648#1:1505\n648#1:1506\n677#1:1507,5\n677#1:1513\n677#1:1514\n697#1:1515,5\n697#1:1521\n697#1:1522\n716#1:1523,5\n716#1:1529\n716#1:1530\n735#1:1531,5\n735#1:1537\n735#1:1538\n754#1:1539,5\n754#1:1545\n754#1:1546\n773#1:1547,5\n773#1:1553\n773#1:1554\n786#1:1555,5\n786#1:1561\n786#1:1562\n799#1:1563,5\n799#1:1569\n799#1:1570\n823#1:1571,5\n823#1:1577\n823#1:1578\n847#1:1579,5\n847#1:1585\n847#1:1586\n885#1:1587,5\n885#1:1593\n885#1:1594\n909#1:1595,5\n909#1:1601\n909#1:1602\n933#1:1603,5\n933#1:1609\n933#1:1610\n962#1:1611,5\n962#1:1617\n962#1:1618\n981#1:1619,5\n981#1:1625\n981#1:1626\n1021#1:1627,5\n1021#1:1633\n1021#1:1634\n1045#1:1635,5\n1045#1:1641\n1045#1:1642\n1064#1:1643,5\n1064#1:1649\n1064#1:1650\n1077#1:1651,5\n1077#1:1657\n1077#1:1658\n1090#1:1659,5\n1090#1:1665\n1090#1:1666\n1113#1:1667,5\n1113#1:1673\n1113#1:1674\n1172#1:1675,5\n1172#1:1681\n1172#1:1682\n1186#1:1683,5\n1186#1:1689\n1186#1:1690\n1205#1:1691,5\n1205#1:1697\n1205#1:1698\n1220#1:1699,5\n1220#1:1705\n1220#1:1706\n1244#1:1707,5\n1244#1:1713\n1244#1:1714\n1257#1:1715,5\n1257#1:1721\n1257#1:1722\n1276#1:1723,5\n1276#1:1729\n1276#1:1730\n1325#1:1731,5\n1325#1:1737\n1325#1:1738\n1365#1:1739,5\n1365#1:1745\n1365#1:1746\n272#1:1385\n281#1:1392\n290#1:1400\n336#1:1408\n382#1:1416\n391#1:1424\n403#1:1432\n451#1:1440\n460#1:1448\n472#1:1456\n513#1:1464\n557#1:1472\n586#1:1480\n610#1:1488\n629#1:1496\n648#1:1504\n677#1:1512\n697#1:1520\n716#1:1528\n735#1:1536\n754#1:1544\n773#1:1552\n786#1:1560\n799#1:1568\n823#1:1576\n847#1:1584\n885#1:1592\n909#1:1600\n933#1:1608\n962#1:1616\n981#1:1624\n1021#1:1632\n1045#1:1640\n1064#1:1648\n1077#1:1656\n1090#1:1664\n1113#1:1672\n1172#1:1680\n1186#1:1688\n1205#1:1696\n1220#1:1704\n1244#1:1712\n1257#1:1720\n1276#1:1728\n1325#1:1736\n1365#1:1744\n*E\n"})
public final class ComposableSingletons$AuthNavGraphKt {

    @NotNull
    public static final ComposableSingletons$AuthNavGraphKt INSTANCE = new ComposableSingletons$AuthNavGraphKt();

    /* JADX INFO: renamed from: lambda$-1142700295, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f230lambda$1142700295 = ComposableLambdaKt.composableLambdaInstance(-1142700295, false, new Function5() { // from class: d9.q
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1142700295$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$63607566 = ComposableLambdaKt.composableLambdaInstance(63607566, false, new Function5() { // from class: d9.s
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_63607566$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1144860306, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f231lambda$1144860306 = ComposableLambdaKt.composableLambdaInstance(-1144860306, false, new Function5() { // from class: d9.e0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1144860306$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-374890813, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f245lambda$374890813 = ComposableLambdaKt.composableLambdaInstance(-374890813, false, new Function5() { // from class: d9.q0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__374890813$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$352927164 = ComposableLambdaKt.composableLambdaInstance(352927164, false, new Function5() { // from class: d9.t0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_352927164$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1467042083 = ComposableLambdaKt.composableLambdaInstance(1467042083, false, new Function5() { // from class: d9.u0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1467042083$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1973063282, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f239lambda$1973063282 = ComposableLambdaKt.composableLambdaInstance(-1973063282, false, new Function5() { // from class: d9.v0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1973063282$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1220307097 = ComposableLambdaKt.composableLambdaInstance(1220307097, false, new Function5() { // from class: d9.w0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1220307097$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1374966269 = ComposableLambdaKt.composableLambdaInstance(1374966269, false, new Function5() { // from class: d9.y0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1374966269$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1555034211, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f234lambda$1555034211 = ComposableLambdaKt.composableLambdaInstance(-1555034211, false, new Function5() { // from class: d9.z0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1555034211$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2007266504, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f240lambda$2007266504 = ComposableLambdaKt.composableLambdaInstance(-2007266504, false, new Function5() { // from class: d9.b0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__2007266504$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-339650729, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f243lambda$339650729 = ComposableLambdaKt.composableLambdaInstance(-339650729, false, new Function5() { // from class: d9.m0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__339650729$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-166951433, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f235lambda$166951433 = ComposableLambdaKt.composableLambdaInstance(-166951433, false, new Function5() { // from class: d9.x0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__166951433$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$641629222 = ComposableLambdaKt.composableLambdaInstance(641629222, false, new Function5() { // from class: d9.a1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_641629222$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$853049318 = ComposableLambdaKt.composableLambdaInstance(853049318, false, new Function5() { // from class: d9.b1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_853049318$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1705611137, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f236lambda$1705611137 = ComposableLambdaKt.composableLambdaInstance(-1705611137, false, new Function5() { // from class: d9.c1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1705611137$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-225966363, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f242lambda$225966363 = ComposableLambdaKt.composableLambdaInstance(-225966363, false, new Function5() { // from class: d9.d1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__225966363$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1152472717, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f232lambda$1152472717 = ComposableLambdaKt.composableLambdaInstance(-1152472717, false, new Function5() { // from class: d9.e1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1152472717$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1226346809, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f233lambda$1226346809 = ComposableLambdaKt.composableLambdaInstance(-1226346809, false, new Function5() { // from class: d9.f1
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1226346809$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-913761348, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f249lambda$913761348 = ComposableLambdaKt.composableLambdaInstance(-913761348, false, new Function5() { // from class: d9.r
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__913761348$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1790925428 = ComposableLambdaKt.composableLambdaInstance(1790925428, false, new Function5() { // from class: d9.t
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1790925428$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-657298296, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f247lambda$657298296 = ComposableLambdaKt.composableLambdaInstance(-657298296, false, new Function5() { // from class: d9.u
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__657298296$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$428446215 = ComposableLambdaKt.composableLambdaInstance(428446215, false, new Function5() { // from class: d9.v
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_428446215$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-2075530305, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f241lambda$2075530305 = ComposableLambdaKt.composableLambdaInstance(-2075530305, false, new Function5() { // from class: d9.w
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__2075530305$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$304668778 = ComposableLambdaKt.composableLambdaInstance(304668778, false, new Function5() { // from class: d9.x
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_304668778$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1542213347 = ComposableLambdaKt.composableLambdaInstance(1542213347, false, new Function5() { // from class: d9.y
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1542213347$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1671114291 = ComposableLambdaKt.composableLambdaInstance(1671114291, false, new Function5() { // from class: d9.z
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1671114291$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1896364312 = ComposableLambdaKt.composableLambdaInstance(1896364312, false, new Function5() { // from class: d9.a0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1896364312$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-365309972, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f244lambda$365309972 = ComposableLambdaKt.composableLambdaInstance(-365309972, false, new Function5() { // from class: d9.c0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__365309972$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-802796635, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f248lambda$802796635 = ComposableLambdaKt.composableLambdaInstance(-802796635, false, new Function5() { // from class: d9.d0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__802796635$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1242947365 = ComposableLambdaKt.composableLambdaInstance(1242947365, false, new Function5() { // from class: d9.f0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1242947365$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$149357675 = ComposableLambdaKt.composableLambdaInstance(149357675, false, new Function5() { // from class: d9.g0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_149357675$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1547073217 = ComposableLambdaKt.composableLambdaInstance(1547073217, false, new Function5() { // from class: d9.h0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1547073217$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$491320532 = ComposableLambdaKt.composableLambdaInstance(491320532, false, new Function5() { // from class: d9.i0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_491320532$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1565047870 = ComposableLambdaKt.composableLambdaInstance(1565047870, false, new Function5() { // from class: d9.j0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1565047870$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-932136858, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f250lambda$932136858 = ComposableLambdaKt.composableLambdaInstance(-932136858, false, new Function5() { // from class: d9.k0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__932136858$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-58225748, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f246lambda$58225748 = ComposableLambdaKt.composableLambdaInstance(-58225748, false, new Function5() { // from class: d9.l0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__58225748$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1446084511 = ComposableLambdaKt.composableLambdaInstance(1446084511, false, new Function5() { // from class: d9.n0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1446084511$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-186721473, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f238lambda$186721473 = ComposableLambdaKt.composableLambdaInstance(-186721473, false, new Function5() { // from class: d9.o0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__186721473$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$1561613677 = ComposableLambdaKt.composableLambdaInstance(1561613677, false, new Function5() { // from class: d9.p0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_1561613677$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: renamed from: lambda$-1833743794, reason: not valid java name */
    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> f237lambda$1833743794 = ComposableLambdaKt.composableLambdaInstance(-1833743794, false, new Function5() { // from class: d9.r0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda__1833743794$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    @NotNull
    private static Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> lambda$129949973 = ComposableLambdaKt.composableLambdaInstance(129949973, false, new Function5() { // from class: d9.s0
        @Override // kotlin.jvm.functions.Function5
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
            return ComposableSingletons$AuthNavGraphKt.lambda_129949973$lambda$0((NavBackStackEntry) obj, (Function2) obj2, (AuthorizeSdkComponent) obj3, (Composer) obj4, ((Integer) obj5).intValue());
        }
    });

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 31659, key = 1220307097, startOffset = 31338)
    @Composable
    public static final Unit lambda_1220307097$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1220307097, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1220307097.<anonymous> (AuthNavGraph.kt:609)");
        }
        RestoreVkViewModel.Factory restoreVkiDViewModel = sdkComponent.getRestoreVkiDViewModel();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        RestoreVKRouteKt.RestoreVKRoute((RestoreVkViewModel) ViewModelKt.viewModel(RestoreVkViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, restoreVkiDViewModel), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 49338, key = 1242947365, startOffset = 49003)
    @Composable
    public static final Unit lambda_1242947365$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1242947365, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1242947365.<anonymous> (AuthNavGraph.kt:1112)");
        }
        VkBindInLoginViewModel.Factory vkBindInLoginViewModelFactory = sdkComponent.getVkBindInLoginViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        VkBindInLoginRouteKt.VkBindInLoginRoute((VkBindInLoginViewModel) ViewModelKt.viewModel(VkBindInLoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, vkBindInLoginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionKeyMeta(endOffset = 58692, key = 129949973, startOffset = 58681)
    @Composable
    public static final Unit lambda_129949973$lambda$0(NavBackStackEntry navBackStackEntry, Function2 unused$var$, AuthorizeSdkComponent unused$var$2, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(unused$var$, "$unused$var$");
        Intrinsics.checkNotNullParameter(unused$var$2, "$unused$var$");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(129949973, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$129949973.<anonymous> (AuthNavGraph.kt:1376)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 32284, key = 1374966269, startOffset = 31973)
    @Composable
    public static final Unit lambda_1374966269$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1374966269, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1374966269.<anonymous> (AuthNavGraph.kt:628)");
        }
        WebCaptchaComposeViewModel.Factory webCaptchaViewModelFactory = sdkComponent.getWebCaptchaViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        LudwigCaptchaRouteKt.LudwigCaptchaRoute((WebCaptchaComposeViewModel) ViewModelKt.viewModel(WebCaptchaComposeViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, webCaptchaViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 55354, key = 1446084511, startOffset = 54993)
    @Composable
    public static final Unit lambda_1446084511$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1446084511, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1446084511.<anonymous> (AuthNavGraph.kt:1275)");
        }
        EnterEmailCodeAfterListAccViewModel.Factory enterEmailCodeAfterListAccViewModelFactory = sdkComponent.getEnterEmailCodeAfterListAccViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        EnterEmailCodeAfterListAccRouteKt.EnterEmailCodeAfterListAccRoute((EnterEmailCodeAfterListAccViewModel) ViewModelKt.viewModel(EnterEmailCodeAfterListAccViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, enterEmailCodeAfterListAccViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 29822, key = 1467042083, startOffset = 29488)
    @Composable
    public static final Unit lambda_1467042083$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1467042083, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1467042083.<anonymous> (AuthNavGraph.kt:556)");
        }
        LoginBindFlowViewModel.Factory loginBindFlowViewModelFactory = sdkComponent.getLoginBindFlowViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        LoginBindFlowRouteKt.LoginBindFlowRoute((LoginBindFlowViewModel) ViewModelKt.viewModel(LoginBindFlowViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, loginBindFlowViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 51594, key = 149357675, startOffset = 51264)
    @Composable
    public static final Unit lambda_149357675$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(149357675, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$149357675.<anonymous> (AuthNavGraph.kt:1171)");
        }
        ChoiceAccountViewModel.Factory choiceAccViewModelFactory = sdkComponent.getChoiceAccViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        ChoiceAccountRouteKt.ChoiceAccountRoute((ChoiceAccountViewModel) ViewModelKt.viewModel(ChoiceAccountViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, choiceAccViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 46008, key = 1542213347, startOffset = 45675)
    @Composable
    public static final Unit lambda_1542213347$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1542213347, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1542213347.<anonymous> (AuthNavGraph.kt:1020)");
        }
        CustomServerViewModel.Factory customServerViewModelFactory = sdkComponent.getCustomServerViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        CustomServerRouteKt.CustomServerRoute((CustomServerViewModel) ViewModelKt.viewModel(CustomServerViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, customServerViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 52078, key = 1547073217, startOffset = 51749)
    @Composable
    public static final Unit lambda_1547073217$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1547073217, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1547073217.<anonymous> (AuthNavGraph.kt:1185)");
        }
        EnterPhoneViewModel.Factory enterPhoneViewModelFactory = sdkComponent.getEnterPhoneViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        EnterPhoneRouteKt.EnterPhoneRoute((EnterPhoneViewModel) ViewModelKt.viewModel(EnterPhoneViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, enterPhoneViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 57367, key = 1561613677, startOffset = 57292)
    @Composable
    public static final Unit lambda_1561613677$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent unused$var$, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(unused$var$, "$unused$var$");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1561613677, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1561613677.<anonymous> (AuthNavGraph.kt:1335)");
        }
        CodeReceiveTypeBottomSheetsKt.CodeReceiveTypeFromEmailBottomSheet(onResult, composer, (i10 >> 3) & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 53259, key = 1565047870, startOffset = 52904)
    @Composable
    public static final Unit lambda_1565047870$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1565047870, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1565047870.<anonymous> (AuthNavGraph.kt:1217)");
        }
        CreateCloudViewModel.Factory createCloudViewModelFactory = sdkComponent.getCreateCloudViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        CreateCloudRouteKt.CreateCloudRoute(onResult, (CreateCloudViewModel) ViewModelKt.viewModel(CreateCloudViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, createCloudViewModelFactory), (CreationExtras) null, composer, 0, 22), composer, (i10 >> 3) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 46897, key = 1671114291, startOffset = 46552)
    @Composable
    public static final Unit lambda_1671114291$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1671114291, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1671114291.<anonymous> (AuthNavGraph.kt:1044)");
        }
        BeforeRecoveryVKIDViewModel.Factory beforeRecoveryVKIDViewModelFactory = sdkComponent.getBeforeRecoveryVKIDViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        BeforeRecoveryVKIDRouteKt.BeforeRecoveryVKIDRoute((BeforeRecoveryVKIDViewModel) ViewModelKt.viewModel(BeforeRecoveryVKIDViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, beforeRecoveryVKIDViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 41351, key = 1790925428, startOffset = 41019)
    @Composable
    public static final Unit lambda_1790925428$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1790925428, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1790925428.<anonymous> (AuthNavGraph.kt:884)");
        }
        SecondStepViewModel.Factory secondFactorViewModelFactory = sdkComponent.getSecondFactorViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        SecondFactorRouteKt.SecondFactorRoute((SecondStepViewModel) ViewModelKt.viewModel(SecondStepViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, secondFactorViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 47558, key = 1896364312, startOffset = 47227)
    @Composable
    public static final Unit lambda_1896364312$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1896364312, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$1896364312.<anonymous> (AuthNavGraph.kt:1063)");
        }
        UnblockUserViewModel.Factory unblockUserViewModelFactory = sdkComponent.getUnblockUserViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        UnblockUserRouteKt.UnblockUserRoute((UnblockUserViewModel) ViewModelKt.viewModel(UnblockUserViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, unblockUserViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 44600, key = 304668778, startOffset = 44277)
    @Composable
    public static final Unit lambda_304668778$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(304668778, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$304668778.<anonymous> (AuthNavGraph.kt:980)");
        }
        OutlookAssistedFactory outlookViewModelFactory = sdkComponent.getOutlookViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        OutlookRouteKt.OutlookRoute((OutlookViewModel) ViewModelKt.viewModel(OutlookViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, outlookViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 28228, key = 352927164, startOffset = 27904)
    @Composable
    public static final Unit lambda_352927164$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(352927164, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$352927164.<anonymous> (AuthNavGraph.kt:512)");
        }
        PasswordViewModel.Factory passwordViewModelFactory = sdkComponent.getPasswordViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        PasswordRouteKt.PasswordRoute((PasswordViewModel) ViewModelKt.viewModel(PasswordViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, passwordViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 42936, key = 428446215, startOffset = 42607)
    @Composable
    public static final Unit lambda_428446215$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(428446215, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$428446215.<anonymous> (AuthNavGraph.kt:932)");
        }
        VkPasswordViewModel.Factory vkPasswordViewModelFactory = sdkComponent.getVkPasswordViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        VkPasswordRouteKt.VkPasswordRoute((VkPasswordViewModel) ViewModelKt.viewModel(VkPasswordViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, vkPasswordViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 52758, key = 491320532, startOffset = 52421)
    @Composable
    public static final Unit lambda_491320532$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(491320532, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$491320532.<anonymous> (AuthNavGraph.kt:1204)");
        }
        EnterPhoneCodeViewModel.Factory enterPhoneCodeViewModelFactory = sdkComponent.getEnterPhoneCodeViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        EnterPhoneCodeRouteKt.EnterPhoneCodeRoute((EnterPhoneCodeViewModel) ViewModelKt.viewModel(EnterPhoneCodeViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, enterPhoneCodeViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 21700, key = 63607566, startOffset = 21367)
    @Composable
    public static final Unit lambda_63607566$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(63607566, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$63607566.<anonymous> (AuthNavGraph.kt:335)");
        }
        CloudLoginVKViewModel.Factory cloudLoginVKViewModelFactory = sdkComponent.getCloudLoginVKViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        CloudLoginVKRouteKt.CloudLoginVkRoute((CloudLoginVKViewModel) ViewModelKt.viewModel(CloudLoginVKViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, cloudLoginVKViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 35951, key = 641629222, startOffset = 35630)
    @Composable
    public static final Unit lambda_641629222$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(641629222, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$641629222.<anonymous> (AuthNavGraph.kt:734)");
        }
        YandexAssistedFactory yandexViewModelFactory = sdkComponent.getYandexViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        YandexRouteKt.YandexRoute((YandexViewModel) ViewModelKt.viewModel(YandexViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, yandexViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 36613, key = 853049318, startOffset = 36283)
    @Composable
    public static final Unit lambda_853049318$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(853049318, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$853049318.<anonymous> (AuthNavGraph.kt:753)");
        }
        MrimDialogViewModel.Factory userBlockedDialogViewModelFactory = sdkComponent.getUserBlockedDialogViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        MrimRouteKt.MrimRoute((MrimDialogViewModel) ViewModelKt.viewModel(MrimDialogViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, userBlockedDialogViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 20111, key = -1142700295, startOffset = 18821)
    @Composable
    public static final Unit lambda__1142700295$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1142700295, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1142700295.<anonymous> (AuthNavGraph.kt:269)");
        }
        FlavorConfig flavorConfig = sdkComponent.getFlavorConfig();
        if (Intrinsics.areEqual(flavorConfig, FlavorConfig.Mail.INSTANCE)) {
            composer.startReplaceGroup(-273573240);
            LoginVKViewModel.Factory loginVKViewModelFactory = sdkComponent.getLoginVKViewModelFactory();
            SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
            Bundle arguments = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
            savedStateHandle.set(DestBase.PARAMS, arguments);
            Unit unit = Unit.INSTANCE;
            LoginVKRouteKt.LoginVkRoute((LoginVKViewModel) ViewModelKt.viewModel(LoginVKViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, loginVKViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        } else if (Intrinsics.areEqual(flavorConfig, FlavorConfig.VkMail.INSTANCE)) {
            composer.startReplaceGroup(-273182392);
            LoginVKViewModel.Factory loginVKViewModelFactory2 = sdkComponent.getLoginVKViewModelFactory();
            SavedStateHandle savedStateHandle2 = navBackStackEntry.getSavedStateHandle();
            Bundle arguments2 = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper2 = DaggerViewModelHelper.INSTANCE;
            savedStateHandle2.set(DestBase.PARAMS, arguments2);
            Unit unit2 = Unit.INSTANCE;
            LoginVKRouteKt.LoginVkRoute((LoginVKViewModel) ViewModelKt.viewModel(LoginVKViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper2.create(savedStateHandle2, loginVKViewModelFactory2), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        } else {
            if (!(flavorConfig instanceof FlavorConfig.Cloud)) {
                composer.startReplaceGroup(1376646801);
                composer.endReplaceGroup();
                throw new NoWhenBranchMatchedException();
            }
            composer.startReplaceGroup(-272789374);
            CloudLoginViewModel.Factory cloudLoginViewModelFactory = sdkComponent.getCloudLoginViewModelFactory();
            SavedStateHandle savedStateHandle3 = navBackStackEntry.getSavedStateHandle();
            Bundle arguments3 = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper3 = DaggerViewModelHelper.INSTANCE;
            savedStateHandle3.set(DestBase.PARAMS, arguments3);
            Unit unit3 = Unit.INSTANCE;
            CloudLoginRouteKt.CloudLoginRoute((CloudLoginViewModel) ViewModelKt.viewModel(CloudLoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper3.create(savedStateHandle3, cloudLoginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 24282, key = -1144860306, startOffset = 22929)
    @Composable
    public static final Unit lambda__1144860306$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1144860306, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1144860306.<anonymous> (AuthNavGraph.kt:379)");
        }
        FlavorConfig flavorConfig = sdkComponent.getFlavorConfig();
        if (Intrinsics.areEqual(flavorConfig, FlavorConfig.Mail.INSTANCE)) {
            composer.startReplaceGroup(-197951625);
            LoginViewModel.Factory loginViewModelFactory = sdkComponent.getLoginViewModelFactory();
            SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
            Bundle arguments = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
            savedStateHandle.set(DestBase.PARAMS, arguments);
            Unit unit = Unit.INSTANCE;
            LoginRouteKt.LoginRoute((LoginViewModel) ViewModelKt.viewModel(LoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, loginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        } else if (flavorConfig instanceof FlavorConfig.Cloud) {
            composer.startReplaceGroup(-197560250);
            CloudLoginViewModel.Factory cloudLoginViewModelFactory = sdkComponent.getCloudLoginViewModelFactory();
            SavedStateHandle savedStateHandle2 = navBackStackEntry.getSavedStateHandle();
            Bundle arguments2 = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper2 = DaggerViewModelHelper.INSTANCE;
            savedStateHandle2.set(DestBase.PARAMS, arguments2);
            Unit unit2 = Unit.INSTANCE;
            CloudLoginRouteKt.CloudLoginRoute((CloudLoginViewModel) ViewModelKt.viewModel(CloudLoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper2.create(savedStateHandle2, cloudLoginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        } else {
            if (!Intrinsics.areEqual(flavorConfig, FlavorConfig.VkMail.INSTANCE)) {
                composer.startReplaceGroup(-699123707);
                composer.endReplaceGroup();
                throw new NoWhenBranchMatchedException();
            }
            composer.startReplaceGroup(-197095529);
            LoginViewModel.Factory loginViewModelFactory2 = sdkComponent.getLoginViewModelFactory();
            SavedStateHandle savedStateHandle3 = navBackStackEntry.getSavedStateHandle();
            Bundle arguments3 = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper3 = DaggerViewModelHelper.INSTANCE;
            savedStateHandle3.set(DestBase.PARAMS, arguments3);
            Unit unit3 = Unit.INSTANCE;
            LoginRouteKt.LoginRoute((LoginViewModel) ViewModelKt.viewModel(LoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper3.create(savedStateHandle3, loginViewModelFactory2), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionKeyMeta(endOffset = 38241, key = -1152472717, startOffset = 37924)
    @Composable
    public static final Unit lambda__1152472717$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent component, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(component, "component");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1152472717, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1152472717.<anonymous> (AuthNavGraph.kt:798)");
        }
        OKLoginViewModel.Factory oKLoginViewModelFactory = component.getOKLoginViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        OKLoginRouteKt.OKLoginRoute((OKLoginViewModel) ViewModelKt.viewModel(OKLoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, oKLoginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 39095, key = -1226346809, startOffset = 38764)
    @Composable
    public static final Unit lambda__1226346809$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1226346809, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1226346809.<anonymous> (AuthNavGraph.kt:822)");
        }
        GoogleAssistedFactory googleNativeViewModelFactory = sdkComponent.getGoogleNativeViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        GoogleRouteKt.GoogleAuthRoute((GoogleViewModel) ViewModelKt.viewModel(GoogleViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, googleNativeViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 32914, key = -1555034211, startOffset = 32618)
    @Composable
    public static final Unit lambda__1555034211$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1555034211, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1555034211.<anonymous> (AuthNavGraph.kt:647)");
        }
        EsiaViewModel.Factory esiaViewModelFactory = sdkComponent.getEsiaViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        EsiaRouteKt.EsiaRoute((EsiaViewModel) ViewModelKt.viewModel(EsiaViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, esiaViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 35313, key = -166951433, startOffset = 34994)
    @Composable
    public static final Unit lambda__166951433$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-166951433, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-166951433.<anonymous> (AuthNavGraph.kt:715)");
        }
        YahooViewModel.Factory yahooViewModelFactory = sdkComponent.getYahooViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        YahooRouteKt.YahooRoute((YahooViewModel) ViewModelKt.viewModel(YahooViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, yahooViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 37321, key = -1705611137, startOffset = 36980)
    @Composable
    public static final Unit lambda__1705611137$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1705611137, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1705611137.<anonymous> (AuthNavGraph.kt:772)");
        }
        WrongVkidAccountViewModel.Factory wrongVkidAccountViewModelFactory = sdkComponent.getWrongVkidAccountViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        WrongVkidAccountRouteKt.WrongVkidAccountRoute((WrongVkidAccountViewModel) ViewModelKt.viewModel(WrongVkidAccountViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, wrongVkidAccountViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 58586, key = -1833743794, startOffset = 58215)
    @Composable
    public static final Unit lambda__1833743794$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1833743794, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1833743794.<anonymous> (AuthNavGraph.kt:1363)");
        }
        NotReceivedCodeBottomSheetViewModel.Factory notReceivedCodeBtmSheetViewModel = sdkComponent.getNotReceivedCodeBtmSheetViewModel();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        NotReceivedCodeBottomSheetKt.NotReceivedCodeBottomSheet((NotReceivedCodeBottomSheetViewModel) ViewModelKt.viewModel(NotReceivedCodeBottomSheetViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, notReceivedCodeBtmSheetViewModel), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 57118, key = -186721473, startOffset = 56768)
    @Composable
    public static final Unit lambda__186721473$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-186721473, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-186721473.<anonymous> (AuthNavGraph.kt:1324)");
        }
        CodeReceivedTypeBottomSheetViewModel.Factory codeReceivedTypeBottomSheetViewModel = sdkComponent.getCodeReceivedTypeBottomSheetViewModel();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        CodeReceiveTypeBottomSheetsKt.CodeReceiveTypeBottomSheet((CodeReceivedTypeBottomSheetViewModel) ViewModelKt.viewModel(CodeReceivedTypeBottomSheetViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, codeReceivedTypeBottomSheetViewModel), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 30836, key = -1973063282, startOffset = 30505)
    @Composable
    public static final Unit lambda__1973063282$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1973063282, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-1973063282.<anonymous> (AuthNavGraph.kt:585)");
        }
        RestorePasswordViewModel.Factory restorePasswordViewModel = sdkComponent.getRestorePasswordViewModel();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        RestorePasswordRouteKt.RestorePasswordRoute((RestorePasswordViewModel) ViewModelKt.viewModel(RestorePasswordViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, restorePasswordViewModel), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 33920, key = -2007266504, startOffset = 33593)
    @Composable
    public static final Unit lambda__2007266504$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2007266504, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-2007266504.<anonymous> (AuthNavGraph.kt:676)");
        }
        BindEmailViewModel.Factory bindEmailViewModelFactory = sdkComponent.getBindEmailViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        BindEmailRouteKt.BindEmailRoute((BindEmailViewModel) ViewModelKt.viewModel(BindEmailViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, bindEmailViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 43956, key = -2075530305, startOffset = 43617)
    @Composable
    public static final Unit lambda__2075530305$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2075530305, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-2075530305.<anonymous> (AuthNavGraph.kt:961)");
        }
        ExternalAccMigrationViewModel.Factory externalAccMigrationFactory = sdkComponent.getExternalAccMigrationFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        ExternalAccMigrationRouteKt.ExternalAccMigrationRoute((ExternalAccMigrationViewModel) ViewModelKt.viewModel(ExternalAccMigrationViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, externalAccMigrationFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 37790, key = -225966363, startOffset = 37467)
    @Composable
    public static final Unit lambda__225966363$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent component, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(component, "component");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-225966363, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-225966363.<anonymous> (AuthNavGraph.kt:785)");
        }
        YandexHelpViewModel.Factory yandexHelpViewModelFactory = component.getYandexHelpViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        YandexHelpRouteKt.YandexHelpRoute((YandexHelpViewModel) ViewModelKt.viewModel(YandexHelpViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, yandexHelpViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 34680, key = -339650729, startOffset = 34349)
    @Composable
    public static final Unit lambda__339650729$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-339650729, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-339650729.<anonymous> (AuthNavGraph.kt:696)");
        }
        OneTimeCodeViewModel.Factory oneTimeCodeViewModelFactory = sdkComponent.getOneTimeCodeViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        OneTimeCodeRouteKt.OneTimeCodeRoute((OneTimeCodeViewModel) ViewModelKt.viewModel(OneTimeCodeViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, oneTimeCodeViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @FunctionKeyMeta(endOffset = 48052, key = -365309972, startOffset = 47715)
    @Composable
    public static final Unit lambda__365309972$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-365309972, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-365309972.<anonymous> (AuthNavGraph.kt:1076)");
        }
        ChangePasswordViewModel.Factory changePasswordViewModelFactory = sdkComponent.getChangePasswordViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        ChangePasswordRouteKt.ChangePasswordRoute((ChangePasswordViewModel) ViewModelKt.viewModel(ChangePasswordViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, changePasswordViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 26879, key = -374890813, startOffset = 25526)
    @Composable
    public static final Unit lambda__374890813$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-374890813, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-374890813.<anonymous> (AuthNavGraph.kt:448)");
        }
        FlavorConfig flavorConfig = sdkComponent.getFlavorConfig();
        if (Intrinsics.areEqual(flavorConfig, FlavorConfig.Mail.INSTANCE)) {
            composer.startReplaceGroup(-1457113598);
            LoginViewModel.Factory loginViewModelFactory = sdkComponent.getLoginViewModelFactory();
            SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
            Bundle arguments = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
            savedStateHandle.set(DestBase.PARAMS, arguments);
            Unit unit = Unit.INSTANCE;
            LoginRouteKt.LoginRoute((LoginViewModel) ViewModelKt.viewModel(LoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, loginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        } else if (flavorConfig instanceof FlavorConfig.Cloud) {
            composer.startReplaceGroup(-1456722223);
            CloudLoginViewModel.Factory cloudLoginViewModelFactory = sdkComponent.getCloudLoginViewModelFactory();
            SavedStateHandle savedStateHandle2 = navBackStackEntry.getSavedStateHandle();
            Bundle arguments2 = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper2 = DaggerViewModelHelper.INSTANCE;
            savedStateHandle2.set(DestBase.PARAMS, arguments2);
            Unit unit2 = Unit.INSTANCE;
            CloudLoginRouteKt.CloudLoginRoute((CloudLoginViewModel) ViewModelKt.viewModel(CloudLoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper2.create(savedStateHandle2, cloudLoginViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        } else {
            if (!Intrinsics.areEqual(flavorConfig, FlavorConfig.VkMail.INSTANCE)) {
                composer.startReplaceGroup(507184154);
                composer.endReplaceGroup();
                throw new NoWhenBranchMatchedException();
            }
            composer.startReplaceGroup(-1456257502);
            LoginViewModel.Factory loginViewModelFactory2 = sdkComponent.getLoginViewModelFactory();
            SavedStateHandle savedStateHandle3 = navBackStackEntry.getSavedStateHandle();
            Bundle arguments3 = navBackStackEntry.getArguments();
            DaggerViewModelHelper daggerViewModelHelper3 = DaggerViewModelHelper.INSTANCE;
            savedStateHandle3.set(DestBase.PARAMS, arguments3);
            Unit unit3 = Unit.INSTANCE;
            LoginRouteKt.LoginRoute((LoginViewModel) ViewModelKt.viewModel(LoginViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper3.create(savedStateHandle3, loginViewModelFactory2), (CreationExtras) null, composer, 0, 22), onResult, null, composer, i10 & 112, 4);
            composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 54607, key = -58225748, startOffset = 54276)
    @Composable
    public static final Unit lambda__58225748$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-58225748, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-58225748.<anonymous> (AuthNavGraph.kt:1256)");
        }
        AccountListViewModel.Factory accountListViewModelFactory = sdkComponent.getAccountListViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        AccountListRouteKt.AccountListRoute((AccountListViewModel) ViewModelKt.viewModel(AccountListViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, accountListViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 42118, key = -657298296, startOffset = 41803)
    @Composable
    public static final Unit lambda__657298296$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-657298296, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-657298296.<anonymous> (AuthNavGraph.kt:908)");
        }
        SSOViewModel.Factory sSOViewModelFactory = sdkComponent.getSSOViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        SSORouteKt.SSORoute((SSOViewModel) ViewModelKt.viewModel(SSOViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, sSOViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 48651, key = -802796635, startOffset = 48222)
    @Composable
    public static final Unit lambda__802796635$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-802796635, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-802796635.<anonymous> (AuthNavGraph.kt:1089)");
        }
        RegistrationMainViewModel.Factory registrationMainViewModelFactory = sdkComponent.getRegistrationMainViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        RegistrationMainRouteKt.RegistrationMainRoute(onResult, sdkComponent, (RegistrationMainViewModel) ViewModelKt.viewModel(RegistrationMainViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, registrationMainViewModelFactory), (CreationExtras) null, composer, 0, 22), composer, (i10 >> 3) & 126);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 39951, key = -913761348, startOffset = 39616)
    @Composable
    public static final Unit lambda__913761348$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-913761348, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-913761348.<anonymous> (AuthNavGraph.kt:846)");
        }
        GoogleWebAuthAssistedFactory googleWebAuthViewModelFactory = sdkComponent.getGoogleWebAuthViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        GoogleWebAuthRouteKt.GoogleWebAuthRoute((GoogleWebAuthViewModel) ViewModelKt.viewModel(GoogleWebAuthViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, googleWebAuthViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @ComposableTarget(applier = "androidx.compose.ui.UiComposable")
    @FunctionKeyMeta(endOffset = 54127, key = -932136858, startOffset = 53790)
    @Composable
    public static final Unit lambda__932136858$lambda$0(NavBackStackEntry navBackStackEntry, Function2 onResult, AuthorizeSdkComponent sdkComponent, Composer composer, int i10) {
        Intrinsics.checkNotNullParameter(navBackStackEntry, "<this>");
        Intrinsics.checkNotNullParameter(onResult, "onResult");
        Intrinsics.checkNotNullParameter(sdkComponent, "sdkComponent");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-932136858, i10, -1, "ru.mail.authorizationsdk.navigation.auth.ComposableSingletons$AuthNavGraphKt.lambda$-932136858.<anonymous> (AuthNavGraph.kt:1243)");
        }
        EnterEmailCodeViewModel.Factory enterEmailCodeViewModelFactory = sdkComponent.getEnterEmailCodeViewModelFactory();
        SavedStateHandle savedStateHandle = navBackStackEntry.getSavedStateHandle();
        Bundle arguments = navBackStackEntry.getArguments();
        DaggerViewModelHelper daggerViewModelHelper = DaggerViewModelHelper.INSTANCE;
        savedStateHandle.set(DestBase.PARAMS, arguments);
        Unit unit = Unit.INSTANCE;
        EnterEmailCodeRouteKt.EnterEmailCodeRoute((EnterEmailCodeViewModel) ViewModelKt.viewModel(EnterEmailCodeViewModel.class, (ViewModelStoreOwner) null, (String) null, daggerViewModelHelper.create(savedStateHandle, enterEmailCodeViewModelFactory), (CreationExtras) null, composer, 0, 22), onResult, composer, i10 & 112);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1142700295$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14785getLambda$1142700295$authorizationsdk_release() {
        return f230lambda$1142700295;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1144860306$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14786getLambda$1144860306$authorizationsdk_release() {
        return f231lambda$1144860306;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1152472717$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14787getLambda$1152472717$authorizationsdk_release() {
        return f232lambda$1152472717;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1226346809$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14788getLambda$1226346809$authorizationsdk_release() {
        return f233lambda$1226346809;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1555034211$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14789getLambda$1555034211$authorizationsdk_release() {
        return f234lambda$1555034211;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-166951433$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14790getLambda$166951433$authorizationsdk_release() {
        return f235lambda$166951433;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1705611137$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14791getLambda$1705611137$authorizationsdk_release() {
        return f236lambda$1705611137;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1833743794$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14792getLambda$1833743794$authorizationsdk_release() {
        return f237lambda$1833743794;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-186721473$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14793getLambda$186721473$authorizationsdk_release() {
        return f238lambda$186721473;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-1973063282$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14794getLambda$1973063282$authorizationsdk_release() {
        return f239lambda$1973063282;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-2007266504$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14795getLambda$2007266504$authorizationsdk_release() {
        return f240lambda$2007266504;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-2075530305$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14796getLambda$2075530305$authorizationsdk_release() {
        return f241lambda$2075530305;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-225966363$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14797getLambda$225966363$authorizationsdk_release() {
        return f242lambda$225966363;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-339650729$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14798getLambda$339650729$authorizationsdk_release() {
        return f243lambda$339650729;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-365309972$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14799getLambda$365309972$authorizationsdk_release() {
        return f244lambda$365309972;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-374890813$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14800getLambda$374890813$authorizationsdk_release() {
        return f245lambda$374890813;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-58225748$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14801getLambda$58225748$authorizationsdk_release() {
        return f246lambda$58225748;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-657298296$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14802getLambda$657298296$authorizationsdk_release() {
        return f247lambda$657298296;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-802796635$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14803getLambda$802796635$authorizationsdk_release() {
        return f248lambda$802796635;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-913761348$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14804getLambda$913761348$authorizationsdk_release() {
        return f249lambda$913761348;
    }

    @NotNull
    /* JADX INFO: renamed from: getLambda$-932136858$authorizationsdk_release, reason: not valid java name */
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> m14805getLambda$932136858$authorizationsdk_release() {
        return f250lambda$932136858;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1220307097$authorizationsdk_release() {
        return lambda$1220307097;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1242947365$authorizationsdk_release() {
        return lambda$1242947365;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$129949973$authorizationsdk_release() {
        return lambda$129949973;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1374966269$authorizationsdk_release() {
        return lambda$1374966269;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1446084511$authorizationsdk_release() {
        return lambda$1446084511;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1467042083$authorizationsdk_release() {
        return lambda$1467042083;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$149357675$authorizationsdk_release() {
        return lambda$149357675;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1542213347$authorizationsdk_release() {
        return lambda$1542213347;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1547073217$authorizationsdk_release() {
        return lambda$1547073217;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1561613677$authorizationsdk_release() {
        return lambda$1561613677;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1565047870$authorizationsdk_release() {
        return lambda$1565047870;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1671114291$authorizationsdk_release() {
        return lambda$1671114291;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1790925428$authorizationsdk_release() {
        return lambda$1790925428;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$1896364312$authorizationsdk_release() {
        return lambda$1896364312;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$304668778$authorizationsdk_release() {
        return lambda$304668778;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$352927164$authorizationsdk_release() {
        return lambda$352927164;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$428446215$authorizationsdk_release() {
        return lambda$428446215;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$491320532$authorizationsdk_release() {
        return lambda$491320532;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$63607566$authorizationsdk_release() {
        return lambda$63607566;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$641629222$authorizationsdk_release() {
        return lambda$641629222;
    }

    @NotNull
    public final Function5<NavBackStackEntry, Function2<? super String, ? super Bundle, Unit>, AuthorizeSdkComponent, Composer, Integer, Unit> getLambda$853049318$authorizationsdk_release() {
        return lambda$853049318;
    }
}
