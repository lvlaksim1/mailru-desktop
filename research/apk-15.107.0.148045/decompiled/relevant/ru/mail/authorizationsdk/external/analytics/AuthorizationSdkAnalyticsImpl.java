package ru.mail.authorizationsdk.external.analytics;

import androidx.annotation.Size;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics;
import ru.mail.authorizationsdk.external.analytics.common.DebugAutologinAnalytics;
import ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthRequestAnalytics;
import ru.mail.authorizationsdk.feature.authactivity.vk.VkIdAuthAnalytics;
import ru.mail.authorizationsdk.feature.beforerecovery.analytics.BeforeRecoveryVKIDAnalytics;
import ru.mail.authorizationsdk.feature.bindemail.analytics.EsiaBindEmailAnalytics;
import ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents;
import ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics;
import ru.mail.authorizationsdk.feature.customserver.analytics.CustomServerAnalytics;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneAnalytics;
import ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneResult;
import ru.mail.authorizationsdk.feature.externalmigration.analytics.ExternalAccMigrationAnalytics;
import ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics;
import ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents;
import ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents;
import ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics;
import ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics;
import ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics;
import ru.mail.authorizationsdk.feature.mrim.MrimAnalytics;
import ru.mail.authorizationsdk.feature.onetimecode.OneTimeCodeResult;
import ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents;
import ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics;
import ru.mail.authorizationsdk.feature.password.presentation.PasswordAnalytics;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeAnalytics;
import ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeResult;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeAnalytics;
import ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeResult;
import ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics;
import ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics;
import ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics;
import ru.mail.authorizationsdk.feature.restorepassword.presentation.RestorePasswordViewModel;
import ru.mail.authorizationsdk.feature.restorevkpassword.analytics.RestoreVkAnalytics;
import ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics;
import ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics;
import ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics;
import ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics;
import ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics;
import ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics;
import ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreAnalytics;
import ru.mail.authorizationsdk.feature.vkbindavailable.analytics.VkBindInLoginAnalytics;
import ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.analytics.VkFragmentSupportAnalytics;
import ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.analytics.WrongVkidAccountAnalytics;
import ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics;
import ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics;
import ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics;
import ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics;
import ru.mail.cloud.app.viewer.ui.ViewerActivity;
import ru.mail.credentialsexchanger.analytics.AnalyticsConstants;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.utils.client.interceptor.retry.RequestDurationAnalytics;
import ru.mail.social.auth.RestoreVkidStartSource;
import ru.mail.util.log.LogFilter;
import ru.ok.android.api.methods.batch.execute.BatchApiRequest;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000¤\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b(\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010$\n\u0002\bK\n\u0002\u0010\t\n\u0002\bE\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\bv\n\u0002\u0018\u0002\n\u0002\b<\b\u0007\u0018\u0000 þ\u00032\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000b2\u00020\f2\u00020\r2\u00020\u000e2\u00020\u000f2\u00020\u00102\u00020\u00112\u00020\u00122\u00020\u00132\u00020\u00142\u00020\u00152\u00020\u00162\u00020\u00172\u00020\u00182\u00020\u00192\u00020\u001a2\u00020\u001b2\u00020\u001c2\u00020\u001d2\u00020\u001e2\u00020\u001f2\u00020 2\u00020!2\u00020\"2\u00020#2\u00020$2\u00020%2\u00020&2\u00020'2\u00020(2\u00020)2\u00020*2\u00020+2\u00020,2\u00020-:\u0002þ\u0003B\u001d\u0012\u0006\u0010.\u001a\u00020/\u0012\f\u00100\u001a\b\u0012\u0004\u0012\u00020201¢\u0006\u0004\b3\u00104J\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0016J\u0010\u00109\u001a\u0002062\u0006\u0010:\u001a\u000208H\u0016J\u0010\u0010;\u001a\u0002062\u0006\u00107\u001a\u000208H\u0016J\u0012\u0010<\u001a\u0002062\b\u0010=\u001a\u0004\u0018\u000108H\u0016J\u0010\u0010>\u001a\u0002062\u0006\u0010?\u001a\u00020@H\u0016J$\u0010A\u001a\u0002062\u0006\u0010B\u001a\u0002082\b\u0010C\u001a\u0004\u0018\u0001082\b\u0010D\u001a\u0004\u0018\u000108H\u0016J\b\u0010E\u001a\u000206H\u0016J\u0010\u0010F\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\b\u0010H\u001a\u000206H\u0016J\b\u0010I\u001a\u000206H\u0016J\b\u0010J\u001a\u000206H\u0016J\u0018\u0010K\u001a\u0002062\u0006\u0010L\u001a\u00020M2\u0006\u0010C\u001a\u000208H\u0016J\u0018\u0010N\u001a\u0002062\u0006\u0010O\u001a\u0002082\u0006\u0010C\u001a\u000208H\u0016J\u0010\u0010P\u001a\u0002062\u0006\u0010Q\u001a\u000208H\u0016J\u0010\u0010R\u001a\u0002062\u0006\u0010Q\u001a\u000208H\u0016J\u0018\u0010S\u001a\u0002062\u0006\u0010Q\u001a\u0002082\u0006\u0010T\u001a\u00020MH\u0016J\b\u0010U\u001a\u000206H\u0016J\u0010\u0010V\u001a\u0002062\u0006\u0010:\u001a\u000208H\u0016J\u0018\u0010W\u001a\u0002062\u0006\u0010X\u001a\u0002082\u0006\u0010C\u001a\u000208H\u0016J\b\u0010Y\u001a\u000206H\u0016J\u0010\u0010Z\u001a\u0002062\u0006\u0010Q\u001a\u000208H\u0016J\u0010\u0010[\u001a\u0002062\u0006\u0010Q\u001a\u000208H\u0016J\u0018\u0010\\\u001a\u0002062\u0006\u0010Q\u001a\u0002082\u0006\u0010]\u001a\u00020MH\u0016J\u0010\u0010^\u001a\u0002062\u0006\u0010Q\u001a\u000208H\u0016J\b\u0010_\u001a\u000206H\u0016J\b\u0010`\u001a\u000206H\u0016J\b\u0010a\u001a\u000206H\u0016J\b\u0010b\u001a\u000206H\u0016J\b\u0010c\u001a\u000206H\u0016J\b\u0010d\u001a\u000206H\u0016J\u0017\u0010e\u001a\u0002062\b\u0010f\u001a\u0004\u0018\u00010@H\u0016¢\u0006\u0002\u0010gJ\b\u0010h\u001a\u000206H\u0016J\b\u0010i\u001a\u000206H\u0016J\b\u0010j\u001a\u000206H\u0016J\u0010\u0010k\u001a\u0002062\u0006\u0010l\u001a\u000208H\u0016J\b\u0010m\u001a\u000206H\u0016J\b\u0010n\u001a\u000206H\u0016J\b\u0010o\u001a\u000206H\u0016J\b\u0010p\u001a\u000206H\u0016J\b\u0010q\u001a\u000206H\u0016J\b\u0010r\u001a\u000206H\u0016J,\u0010s\u001a\u0002062\u0006\u0010t\u001a\u0002082\u0006\u0010u\u001a\u00020v2\u0012\u0010w\u001a\u000e\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u0002080xH\u0016J\b\u0010y\u001a\u000206H\u0016J\u0010\u0010z\u001a\u0002062\u0006\u0010{\u001a\u00020@H\u0016J\b\u0010|\u001a\u000206H\u0016J\b\u0010}\u001a\u000206H\u0016J\b\u0010~\u001a\u000206H\u0016J\b\u0010\u007f\u001a\u000206H\u0016J\t\u0010\u0080\u0001\u001a\u000206H\u0016J\t\u0010\u0081\u0001\u001a\u000206H\u0016J\t\u0010\u0082\u0001\u001a\u000206H\u0016J\t\u0010\u0083\u0001\u001a\u000206H\u0016J\u0011\u0010\u0084\u0001\u001a\u0002062\u0006\u0010Q\u001a\u000208H\u0016J\u001a\u0010\u0085\u0001\u001a\u0002062\u0006\u0010Q\u001a\u0002082\u0007\u0010\u0086\u0001\u001a\u00020MH\u0016J\u0012\u0010\u0087\u0001\u001a\u0002062\u0007\u0010\u0088\u0001\u001a\u000208H\u0016J6\u0010\u0089\u0001\u001a\u0002062\u0007\u0010\u008a\u0001\u001a\u00020M2\u0007\u0010\u008b\u0001\u001a\u00020M2\u0006\u0010B\u001a\u0002082\b\u0010D\u001a\u0004\u0018\u0001082\u0007\u0010\u008c\u0001\u001a\u00020MH\u0016J\u001a\u0010\u008d\u0001\u001a\u0002062\u0006\u0010B\u001a\u0002082\u0007\u0010\u008e\u0001\u001a\u00020MH\u0016J\u0011\u0010\u008f\u0001\u001a\u0002062\u0006\u0010B\u001a\u000208H\u0016J\u001a\u0010\u0090\u0001\u001a\u0002062\u0006\u0010B\u001a\u0002082\u0007\u0010\u0088\u0001\u001a\u000208H\u0016J\t\u0010\u0091\u0001\u001a\u000206H\u0016J\t\u0010\u0092\u0001\u001a\u000206H\u0016J!\u0010\u0093\u0001\u001a\u0002062\u0006\u0010B\u001a\u0002082\u0006\u0010C\u001a\u0002082\u0006\u0010D\u001a\u000208H\u0016J\u001a\u0010\u0094\u0001\u001a\u0002062\u0006\u0010B\u001a\u0002082\u0007\u0010\u0095\u0001\u001a\u00020MH\u0016J\u0013\u0010\u0096\u0001\u001a\u0002062\b\u0010=\u001a\u0004\u0018\u000108H\u0016J\t\u0010\u0097\u0001\u001a\u000206H\u0016J\u0011\u0010\u0098\u0001\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010\u0099\u0001\u001a\u000206H\u0016J\t\u0010\u009a\u0001\u001a\u000206H\u0016J\t\u0010\u009b\u0001\u001a\u000206H\u0016J\u001a\u0010\u009c\u0001\u001a\u0002062\u0006\u0010G\u001a\u0002082\u0007\u0010\u009d\u0001\u001a\u000208H\u0016J\t\u0010\u009e\u0001\u001a\u000206H\u0016J\t\u0010\u009f\u0001\u001a\u000206H\u0016J\t\u0010 \u0001\u001a\u000206H\u0016J\t\u0010¡\u0001\u001a\u000206H\u0016J\t\u0010¢\u0001\u001a\u000206H\u0016J\u0012\u0010£\u0001\u001a\u0002062\u0007\u0010¤\u0001\u001a\u000208H\u0016J\u0012\u0010¥\u0001\u001a\u0002062\u0007\u0010¦\u0001\u001a\u000208H\u0016J\u0012\u0010§\u0001\u001a\u0002062\u0007\u0010¨\u0001\u001a\u000208H\u0016J\t\u0010©\u0001\u001a\u000206H\u0016J\t\u0010ª\u0001\u001a\u000206H\u0016J\t\u0010«\u0001\u001a\u000206H\u0016J\u0019\u0010¬\u0001\u001a\u0002062\u0006\u0010u\u001a\u0002082\u0006\u0010B\u001a\u000208H\u0016J\t\u0010\u00ad\u0001\u001a\u000206H\u0016J\t\u0010®\u0001\u001a\u000206H\u0016J\u0012\u0010¯\u0001\u001a\u0002062\u0007\u0010\u009d\u0001\u001a\u000208H\u0016J\u0012\u0010°\u0001\u001a\u0002062\u0007\u0010±\u0001\u001a\u00020MH\u0016J\u001c\u0010²\u0001\u001a\u0002062\u0007\u0010±\u0001\u001a\u00020M2\b\u0010D\u001a\u0004\u0018\u000108H\u0016J\u0012\u0010³\u0001\u001a\u0002062\u0007\u0010´\u0001\u001a\u00020MH\u0016J\u0011\u0010µ\u0001\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010¶\u0001\u001a\u000206H\u0016J\u0012\u0010·\u0001\u001a\u0002062\u0007\u0010\u009d\u0001\u001a\u000208H\u0016J\u0012\u0010¸\u0001\u001a\u0002062\u0007\u0010\u009d\u0001\u001a\u000208H\u0016J\u0012\u0010¹\u0001\u001a\u0002062\u0007\u0010\u009d\u0001\u001a\u000208H\u0016J\u0011\u0010º\u0001\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u0011\u0010»\u0001\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u0011\u0010¼\u0001\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u0012\u0010½\u0001\u001a\u0002062\u0007\u0010\u0088\u0001\u001a\u000208H\u0016J\u0011\u0010¾\u0001\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0011\u0010¿\u0001\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u001c\u0010À\u0001\u001a\u0002062\b\b\u0001\u0010u\u001a\u0002082\u0007\u0010Á\u0001\u001a\u000208H\u0016J\u001c\u0010Â\u0001\u001a\u0002062\b\u0010Ã\u0001\u001a\u00030Ä\u00012\u0007\u0010Å\u0001\u001a\u000208H\u0016J\u001c\u0010Æ\u0001\u001a\u0002062\b\u0010Ç\u0001\u001a\u00030Ä\u00012\u0007\u0010È\u0001\u001a\u00020MH\u0016J\u001a\u0010É\u0001\u001a\u0002062\u0006\u0010u\u001a\u0002082\u0007\u0010È\u0001\u001a\u00020MH\u0016J\t\u0010Ê\u0001\u001a\u000206H\u0016J\t\u0010Ë\u0001\u001a\u000206H\u0016J\u001c\u0010Ì\u0001\u001a\u0002062\b\u0010Ç\u0001\u001a\u00030Ä\u00012\u0007\u0010È\u0001\u001a\u00020MH\u0016J\t\u0010Í\u0001\u001a\u000206H\u0016J\t\u0010Î\u0001\u001a\u000206H\u0016J\t\u0010Ï\u0001\u001a\u000206H\u0016J\u0012\u0010Ð\u0001\u001a\u0002062\u0007\u0010Ñ\u0001\u001a\u000208H\u0016J\t\u0010Ò\u0001\u001a\u000206H\u0016J\t\u0010Ó\u0001\u001a\u000206H\u0016J\t\u0010Ô\u0001\u001a\u000206H\u0016J\u0012\u0010Õ\u0001\u001a\u0002062\u0007\u0010Ö\u0001\u001a\u000208H\u0016J\u0012\u0010×\u0001\u001a\u0002062\u0007\u0010Ö\u0001\u001a\u000208H\u0016J\u0012\u0010Ø\u0001\u001a\u0002062\u0007\u0010Ö\u0001\u001a\u000208H\u0016J\u0012\u0010Ù\u0001\u001a\u0002062\u0007\u0010Ö\u0001\u001a\u000208H\u0016J\u0012\u0010Ú\u0001\u001a\u0002062\u0007\u0010Ö\u0001\u001a\u000208H\u0016J\u0012\u0010Û\u0001\u001a\u0002062\u0007\u0010Ö\u0001\u001a\u000208H\u0016J\u0011\u0010Ü\u0001\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0011\u0010Ý\u0001\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010Þ\u0001\u001a\u000206H\u0016J\t\u0010ß\u0001\u001a\u000206H\u0016J\t\u0010à\u0001\u001a\u000206H\u0016J\t\u0010á\u0001\u001a\u000206H\u0016J\t\u0010â\u0001\u001a\u000206H\u0016J\t\u0010ã\u0001\u001a\u000206H\u0016J\u0011\u0010ä\u0001\u001a\u0002062\u0006\u0010f\u001a\u00020@H\u0016J\u001b\u0010å\u0001\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020M2\u0007\u0010ç\u0001\u001a\u000208H\u0016J\t\u0010è\u0001\u001a\u000206H\u0016J\t\u0010é\u0001\u001a\u000206H\u0016J\t\u0010ê\u0001\u001a\u000206H\u0016J\u0012\u0010ë\u0001\u001a\u0002062\u0007\u0010ì\u0001\u001a\u000208H\u0016J\t\u0010í\u0001\u001a\u000206H\u0016J\t\u0010î\u0001\u001a\u000206H\u0016J\t\u0010ï\u0001\u001a\u000206H\u0016J\t\u0010ð\u0001\u001a\u000206H\u0016J\t\u0010ñ\u0001\u001a\u000206H\u0016J\t\u0010ò\u0001\u001a\u000206H\u0016J\t\u0010ó\u0001\u001a\u000206H\u0016J\u0012\u0010ô\u0001\u001a\u0002062\u0007\u0010õ\u0001\u001a\u000208H\u0016J\t\u0010ö\u0001\u001a\u000206H\u0016J\t\u0010÷\u0001\u001a\u000206H\u0016J\t\u0010ø\u0001\u001a\u000206H\u0016J\t\u0010ù\u0001\u001a\u000206H\u0016J\t\u0010ú\u0001\u001a\u000206H\u0016J\u001b\u0010û\u0001\u001a\u0002062\u0007\u0010ç\u0001\u001a\u00020M2\u0007\u0010ü\u0001\u001a\u00020MH\u0016J\u001b\u0010ý\u0001\u001a\u0002062\u0007\u0010ç\u0001\u001a\u00020M2\u0007\u0010ü\u0001\u001a\u00020MH\u0016J\u001b\u0010þ\u0001\u001a\u0002062\u0007\u0010ç\u0001\u001a\u00020M2\u0007\u0010ü\u0001\u001a\u00020MH\u0016J\u001b\u0010ÿ\u0001\u001a\u0002062\u0007\u0010ç\u0001\u001a\u00020M2\u0007\u0010ü\u0001\u001a\u00020MH\u0016J\t\u0010\u0080\u0002\u001a\u000206H\u0016J\u001b\u0010\u0081\u0002\u001a\u0002062\u0007\u0010ç\u0001\u001a\u00020M2\u0007\u0010ü\u0001\u001a\u00020MH\u0016J\u001b\u0010\u0082\u0002\u001a\u0002062\u0007\u0010ç\u0001\u001a\u00020M2\u0007\u0010ü\u0001\u001a\u00020MH\u0016J\u001a\u0010\u0083\u0002\u001a\u0002062\u0007\u0010Á\u0001\u001a\u0002082\u0006\u0010D\u001a\u000208H\u0016J\u001a\u0010\u0084\u0002\u001a\u0002062\u0007\u0010Á\u0001\u001a\u0002082\u0006\u0010D\u001a\u000208H\u0016J\u001a\u0010\u0085\u0002\u001a\u0002062\u0007\u0010Á\u0001\u001a\u0002082\u0006\u0010D\u001a\u000208H\u0016J\u001c\u0010\u0086\u0002\u001a\u0002062\t\u0010\u0087\u0002\u001a\u0004\u0018\u0001082\u0006\u0010f\u001a\u000208H\u0016J\u0011\u0010\u0088\u0002\u001a\u0002062\u0006\u0010f\u001a\u000208H\u0016J\u0017\u0010\u0089\u0002\u001a\u0002062\f\u0010u\u001a\b0\u008a\u0002j\u0003`\u008b\u0002H\u0016J\u0011\u0010\u008c\u0002\u001a\u0002062\u0006\u0010=\u001a\u000208H\u0016J\t\u0010\u008d\u0002\u001a\u000206H\u0016J\t\u0010\u008e\u0002\u001a\u000206H\u0016J\t\u0010\u008f\u0002\u001a\u000206H\u0016J\t\u0010\u0090\u0002\u001a\u000206H\u0016J\t\u0010\u0091\u0002\u001a\u000206H\u0016J\t\u0010\u0092\u0002\u001a\u000206H\u0016J\t\u0010\u0093\u0002\u001a\u000206H\u0016J\u0011\u0010\u0094\u0002\u001a\u0002062\u0006\u0010f\u001a\u00020@H\u0016J\t\u0010\u0095\u0002\u001a\u000206H\u0016J\t\u0010\u0096\u0002\u001a\u000206H\u0016J\t\u0010\u0097\u0002\u001a\u000206H\u0016J\t\u0010\u0098\u0002\u001a\u000206H\u0016J\t\u0010\u0099\u0002\u001a\u000206H\u0016J\u0011\u0010\u009a\u0002\u001a\u0002062\u0006\u0010f\u001a\u00020@H\u0016J\t\u0010\u009b\u0002\u001a\u000206H\u0016J\t\u0010\u009c\u0002\u001a\u000206H\u0016J\t\u0010\u009d\u0002\u001a\u000206H\u0016J\t\u0010\u009e\u0002\u001a\u000206H\u0016J\t\u0010\u009f\u0002\u001a\u000206H\u0016J\u0011\u0010 \u0002\u001a\u0002062\u0006\u0010f\u001a\u00020@H\u0016J\u001d\u0010¡\u0002\u001a\u0002062\u0007\u0010¢\u0002\u001a\u0002082\t\u0010£\u0002\u001a\u0004\u0018\u000108H\u0016J\t\u0010¤\u0002\u001a\u000206H\u0016J\u0011\u0010\u0094\u0002\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\t\u0010¥\u0002\u001a\u000206H\u0016J\t\u0010¦\u0002\u001a\u000206H\u0016J\t\u0010§\u0002\u001a\u000206H\u0016J\t\u0010¨\u0002\u001a\u000206H\u0016J\u0011\u0010©\u0002\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010ª\u0002\u001a\u000206H\u0016J\t\u0010«\u0002\u001a\u000206H\u0016J\t\u0010¬\u0002\u001a\u000206H\u0016J\t\u0010\u00ad\u0002\u001a\u000206H\u0016J\t\u0010®\u0002\u001a\u000206H\u0016J\t\u0010¯\u0002\u001a\u000206H\u0016J\u0011\u0010°\u0002\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010±\u0002\u001a\u000206H\u0016J\u0011\u0010²\u0002\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0012\u0010³\u0002\u001a\u0002062\u0007\u0010´\u0002\u001a\u000208H\u0016J\t\u0010µ\u0002\u001a\u000206H\u0016J\u0011\u0010¶\u0002\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u0013\u0010·\u0002\u001a\u0002062\b\u0010¸\u0002\u001a\u00030¹\u0002H\u0016J\t\u0010º\u0002\u001a\u000206H\u0016J\t\u0010»\u0002\u001a\u000206H\u0016J\u0011\u0010¼\u0002\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\t\u0010½\u0002\u001a\u000206H\u0016J\u001a\u0010¾\u0002\u001a\u0002062\u0006\u0010X\u001a\u0002082\u0007\u0010¿\u0002\u001a\u000208H\u0016J\t\u0010À\u0002\u001a\u000206H\u0016J\t\u0010Á\u0002\u001a\u000206H\u0016J\u0011\u0010Â\u0002\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\t\u0010Ã\u0002\u001a\u000206H\u0016J\t\u0010Ä\u0002\u001a\u000206H\u0016J\u0011\u0010Å\u0002\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u0011\u0010Æ\u0002\u001a\u0002062\u0006\u0010:\u001a\u000208H\u0016J\t\u0010Ç\u0002\u001a\u000206H\u0016J\t\u0010È\u0002\u001a\u000206H\u0016J\u0011\u0010É\u0002\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\u0013\u0010Ê\u0002\u001a\u0002062\b\u0010Ë\u0002\u001a\u00030Ì\u0002H\u0016J\u0012\u0010Í\u0002\u001a\u0002062\u0007\u0010Î\u0002\u001a\u00020MH\u0016J\t\u0010Ï\u0002\u001a\u000206H\u0016J\t\u0010Ð\u0002\u001a\u000206H\u0016J\t\u0010Ñ\u0002\u001a\u000206H\u0016J\t\u0010Ò\u0002\u001a\u000206H\u0016J\u0014\u0010Ó\u0002\u001a\u0002062\t\u0010¿\u0002\u001a\u0004\u0018\u000108H\u0016J\t\u0010Ô\u0002\u001a\u000206H\u0016J\t\u0010Õ\u0002\u001a\u000206H\u0016J\t\u0010Ö\u0002\u001a\u000206H\u0016J\u001a\u0010×\u0002\u001a\u0002062\u0007\u0010Ø\u0002\u001a\u0002082\u0006\u0010B\u001a\u000208H\u0002J\u0019\u0010¯\u0001\u001a\u0002062\u0006\u0010u\u001a\u0002082\u0006\u0010B\u001a\u000208H\u0002J\t\u0010Ù\u0002\u001a\u000206H\u0002J\t\u0010Ú\u0002\u001a\u000206H\u0002J\t\u0010Û\u0002\u001a\u000206H\u0002J+\u0010Ü\u0002\u001a\u0002062\u0007\u0010Ý\u0002\u001a\u0002082\u0017\b\u0002\u0010Þ\u0002\u001a\u0010\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000208\u0018\u00010xH\u0002J)\u0010ß\u0002\u001a\u0002062\u0007\u0010Ý\u0002\u001a\u0002082\u0017\b\u0002\u0010Þ\u0002\u001a\u0010\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u000208\u0018\u00010xJ\t\u0010à\u0002\u001a\u000206H\u0016J\t\u0010á\u0002\u001a\u000206H\u0016J\t\u0010â\u0002\u001a\u000206H\u0016J\t\u0010ã\u0002\u001a\u000206H\u0016J\u0011\u0010ä\u0002\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010å\u0002\u001a\u000206H\u0016J\t\u0010æ\u0002\u001a\u000206H\u0016J\t\u0010ç\u0002\u001a\u000206H\u0016J\u0011\u0010è\u0002\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010é\u0002\u001a\u000206H\u0016J\t\u0010ê\u0002\u001a\u000206H\u0016J\t\u0010ë\u0002\u001a\u000206H\u0016J\t\u0010ì\u0002\u001a\u000206H\u0016J\t\u0010í\u0002\u001a\u000206H\u0016J\t\u0010î\u0002\u001a\u000206H\u0016J\t\u0010ï\u0002\u001a\u000206H\u0016J\t\u0010ð\u0002\u001a\u000206H\u0016J\t\u0010ñ\u0002\u001a\u000206H\u0016J\t\u0010ò\u0002\u001a\u000206H\u0016J\t\u0010ó\u0002\u001a\u000206H\u0016J\t\u0010ô\u0002\u001a\u000206H\u0016J\u0012\u0010õ\u0002\u001a\u0002062\u0007\u0010ö\u0002\u001a\u00020MH\u0016J\u0011\u0010÷\u0002\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J-\u0010ø\u0002\u001a\u0002062\u0007\u0010ù\u0002\u001a\u0002082\u0007\u0010ú\u0002\u001a\u0002082\u0007\u0010û\u0002\u001a\u0002082\u0007\u0010ü\u0002\u001a\u000208H\u0016J-\u0010ý\u0002\u001a\u0002062\u0007\u0010ù\u0002\u001a\u0002082\u0007\u0010ú\u0002\u001a\u0002082\u0007\u0010û\u0002\u001a\u0002082\u0007\u0010ü\u0002\u001a\u000208H\u0016J\u0011\u0010þ\u0002\u001a\u0002062\u0006\u0010B\u001a\u000208H\u0016J\u0011\u0010ÿ\u0002\u001a\u0002062\u0006\u0010B\u001a\u000208H\u0016J\u0011\u0010\u0080\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010\u0081\u0003\u001a\u000206H\u0016J\t\u0010\u0082\u0003\u001a\u000206H\u0016J\t\u0010\u0083\u0003\u001a\u000206H\u0016J\t\u0010\u0084\u0003\u001a\u000206H\u0016J\t\u0010\u0085\u0003\u001a\u000206H\u0016J\t\u0010\u0086\u0003\u001a\u000206H\u0016J\t\u0010\u0087\u0003\u001a\u000206H\u0016J\t\u0010\u0088\u0003\u001a\u000206H\u0016J\t\u0010\u0089\u0003\u001a\u000206H\u0016J\t\u0010\u008a\u0003\u001a\u000206H\u0016J\t\u0010\u008b\u0003\u001a\u000206H\u0016J\t\u0010\u008c\u0003\u001a\u000206H\u0016J\t\u0010\u008d\u0003\u001a\u000206H\u0016J\t\u0010\u008e\u0003\u001a\u000206H\u0016J\u0011\u0010\u008f\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010\u0090\u0003\u001a\u000206H\u0016J\t\u0010\u0091\u0003\u001a\u000206H\u0016J\t\u0010\u0092\u0003\u001a\u000206H\u0016J\t\u0010\u0093\u0003\u001a\u000206H\u0016J\t\u0010\u0094\u0003\u001a\u000206H\u0016J\t\u0010\u0095\u0003\u001a\u000206H\u0016J\u0011\u0010\u0096\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010\u0097\u0003\u001a\u000206H\u0016J\t\u0010\u0098\u0003\u001a\u000206H\u0016J\t\u0010\u0099\u0003\u001a\u000206H\u0016J\t\u0010\u009a\u0003\u001a\u000206H\u0016J\t\u0010\u009b\u0003\u001a\u000206H\u0016J\u0012\u0010\u009c\u0003\u001a\u0002062\u0007\u0010\u009d\u0003\u001a\u00020@H\u0016J\t\u0010\u009e\u0003\u001a\u000206H\u0016J\t\u0010\u009f\u0003\u001a\u000206H\u0016J\u0011\u0010 \u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010¡\u0003\u001a\u000206H\u0016J\t\u0010¢\u0003\u001a\u000206H\u0016J\u0011\u0010£\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010¤\u0003\u001a\u000206H\u0016J\t\u0010¥\u0003\u001a\u000206H\u0016J\t\u0010¦\u0003\u001a\u000206H\u0016J\t\u0010§\u0003\u001a\u000206H\u0016J\t\u0010¨\u0003\u001a\u000206H\u0016J\t\u0010©\u0003\u001a\u000206H\u0016J\t\u0010ª\u0003\u001a\u000206H\u0016J\t\u0010«\u0003\u001a\u000206H\u0016J\u0011\u0010¬\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010\u00ad\u0003\u001a\u000206H\u0016J\t\u0010®\u0003\u001a\u000206H\u0016J\t\u0010¯\u0003\u001a\u000206H\u0016J\t\u0010°\u0003\u001a\u000206H\u0016J\u001b\u0010±\u0003\u001a\u0002062\b\b\u0001\u0010u\u001a\u0002082\u0006\u0010:\u001a\u000208H\u0016J\u0011\u0010²\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010³\u0003\u001a\u000206H\u0016J\u0011\u0010´\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0011\u0010µ\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0012\u0010¶\u0003\u001a\u0002062\u0007\u0010·\u0003\u001a\u000208H\u0016J\u001a\u0010¸\u0003\u001a\u0002062\u0007\u0010·\u0003\u001a\u0002082\u0006\u0010C\u001a\u000208H\u0016J\t\u0010¹\u0003\u001a\u000206H\u0016J$\u0010º\u0003\u001a\u0002062\u0007\u0010»\u0003\u001a\u00020M2\u0007\u0010¼\u0003\u001a\u00020M2\u0007\u0010½\u0003\u001a\u000208H\u0016J$\u0010¾\u0003\u001a\u0002062\u0007\u0010»\u0003\u001a\u00020M2\u0007\u0010¼\u0003\u001a\u00020M2\u0007\u0010½\u0003\u001a\u000208H\u0016J$\u0010¿\u0003\u001a\u0002062\u0007\u0010»\u0003\u001a\u00020M2\u0007\u0010¼\u0003\u001a\u00020M2\u0007\u0010½\u0003\u001a\u000208H\u0016J\u001b\u0010À\u0003\u001a\u0002062\u0007\u0010¼\u0003\u001a\u00020M2\u0007\u0010½\u0003\u001a\u000208H\u0016J\u0011\u0010Á\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u001b\u0010Â\u0003\u001a\u0002062\u0007\u0010u\u001a\u00030Ã\u00032\u0007\u0010·\u0003\u001a\u000208H\u0016J\u0012\u0010Ä\u0003\u001a\u0002062\u0007\u0010Á\u0001\u001a\u000208H\u0016J\t\u0010Å\u0003\u001a\u000206H\u0016J\u0012\u0010Æ\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\u0011\u0010È\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0012\u0010É\u0003\u001a\u0002062\u0007\u0010Ê\u0003\u001a\u000208H\u0016J\t\u0010Ë\u0003\u001a\u000206H\u0016J\u0012\u0010Ì\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\u0012\u0010Í\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\u001b\u0010Î\u0003\u001a\u0002062\u0007\u0010Ï\u0003\u001a\u0002082\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\u0012\u0010Ð\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\t\u0010Ñ\u0003\u001a\u000206H\u0016J\u001a\u0010Ò\u0003\u001a\u0002062\u0006\u0010D\u001a\u0002082\u0007\u0010Ó\u0003\u001a\u000208H\u0016J\u0012\u0010Ô\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\t\u0010Õ\u0003\u001a\u000206H\u0016J\u0011\u0010Ö\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010×\u0003\u001a\u000206H\u0016J\t\u0010Ø\u0003\u001a\u000206H\u0016J\t\u0010Ù\u0003\u001a\u000206H\u0016J\u0011\u0010Ú\u0003\u001a\u0002062\u0006\u0010u\u001a\u000208H\u0016J\t\u0010Û\u0003\u001a\u000206H\u0016J\u001b\u0010Ü\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020M2\u0007\u0010Á\u0001\u001a\u000208H\u0016J\u001a\u0010¾\u0001\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020M2\u0006\u0010G\u001a\u000208H\u0016J\u0012\u0010Ý\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\u001b\u0010Þ\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020M2\u0007\u0010\u009d\u0003\u001a\u000208H\u0016J\u0012\u0010ß\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\u0012\u0010à\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\u0012\u0010á\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\u0012\u0010â\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\t\u0010ã\u0003\u001a\u000206H\u0016J\t\u0010ä\u0003\u001a\u000206H\u0016J\u0012\u0010å\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\u0012\u0010æ\u0003\u001a\u0002062\u0007\u0010æ\u0001\u001a\u00020MH\u0016J\t\u0010ç\u0003\u001a\u000206H\u0016J\u0011\u0010è\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0012\u0010é\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u000208H\u0016J\u001a\u0010ê\u0003\u001a\u0002062\u0007\u0010Ç\u0003\u001a\u0002082\u0006\u0010G\u001a\u000208H\u0016J\t\u0010ë\u0003\u001a\u000206H\u0016J\u0012\u0010ì\u0003\u001a\u0002062\u0007\u0010Á\u0001\u001a\u000208H\u0016J\u0011\u0010í\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\u0012\u0010î\u0003\u001a\u0002062\u0007\u0010ï\u0003\u001a\u000208H\u0016J\u0011\u0010ð\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010ñ\u0003\u001a\u000206H\u0016J\u0011\u0010ò\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010ó\u0003\u001a\u000206H\u0016J\t\u0010ô\u0003\u001a\u000206H\u0016J\u0011\u0010õ\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016J\t\u0010ö\u0003\u001a\u000206H\u0016J\t\u0010÷\u0003\u001a\u000206H\u0016J\t\u0010ø\u0003\u001a\u000206H\u0016J\t\u0010ù\u0003\u001a\u000206H\u0016J\t\u0010ú\u0003\u001a\u000206H\u0016J\t\u0010û\u0003\u001a\u000206H\u0016J\t\u0010ü\u0003\u001a\u000206H\u0016J\u0011\u0010ý\u0003\u001a\u0002062\u0006\u0010G\u001a\u000208H\u0016R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006ÿ\u0003"}, d2 = {"Lru/mail/authorizationsdk/external/analytics/AuthorizationSdkAnalyticsImpl;", "Lru/mail/authorizationsdk/feature/authactivity/AuthActivityAnalytics;", "Lru/mail/network/utils/client/interceptor/retry/RequestDurationAnalytics;", "Lru/mail/authorizationsdk/feature/authactivity/domain/authdelegate/AuthRequestAnalytics;", "Lru/mail/authorizationsdk/feature/captcha/analytics/LudwigCaptchaAnalyticEvents;", "Lru/mail/authorizationsdk/feature/onetimecode/analytics/OneTimeCodeAnalyticEvents;", "Lru/mail/authorizationsdk/feature/google/nativelib/analytics/GoogleNativeAnalyticEvents;", "Lru/mail/authorizationsdk/feature/google/web/analytics/GoogleWebAnalyticEvents;", "Lru/mail/authorizationsdk/feature/google/common/analytics/GoogleAnalytics;", "Lru/mail/authorizationsdk/feature/secondfactor/analytics/SecondFactorAnalytics;", "Lru/mail/authorizationsdk/feature/externalmigration/analytics/ExternalAccMigrationAnalytics;", "Lru/mail/authorizationsdk/feature/yahoo/analytics/YahooAnalytics;", "Lru/mail/authorizationsdk/feature/yandex/analytics/YandexAnalytics;", "Lru/mail/authorizationsdk/feature/mrim/MrimAnalytics;", "Lru/mail/authorizationsdk/feature/outlook/analytics/OutlookAnalytics;", "Lru/mail/authorizationsdk/feature/imaplocal/analytics/ImapLocalAnalytics;", "Lru/mail/authorizationsdk/feature/customserver/analytics/CustomServerAnalytics;", "Lru/mail/authorizationsdk/external/analytics/common/CommonAnalytics;", "Lru/mail/authorizationsdk/feature/login/analytics/LoginAnalytics;", "Lru/mail/authorizationsdk/feature/loginbindflow/analytics/LoginBindFlowAnalytics;", "Lru/mail/authorizationsdk/feature/forcevkid/analytics/ForceVKIDAnalytics;", "Lru/mail/authorizationsdk/feature/yandexhelp/analytics/YandexHelpAnalytics;", "Lru/mail/authorizationsdk/feature/restorepassword/analytics/RestorePasswordAnalytics;", "Lru/mail/authorizationsdk/feature/utilfeature/sessionrestore/model/SessionRestoreAnalytics;", "Lru/mail/authorizationsdk/feature/sso/analytics/SSOAnalytics;", "Lru/mail/authorizationsdk/feature/password/presentation/PasswordAnalytics;", "Lru/mail/authorizationsdk/feature/vkpassword/analytics/VkPasswordAnalytics;", "Lru/mail/authorizationsdk/feature/socialauth/analytics/SocialAuthAnalytics;", "Lru/mail/authorizationsdk/feature/registration/RegistrationAnalytics;", "Lru/mail/authorizationsdk/feature/beforerecovery/analytics/BeforeRecoveryVKIDAnalytics;", "Lru/mail/authorizationsdk/feature/enterphone/presentation/EnterPhoneAnalytics;", "Lru/mail/authorizationsdk/feature/unblockuser/analytics/UnblockUserAnalytics;", "Lru/mail/authorizationsdk/feature/vkbindavailable/analytics/VkBindInLoginAnalytics;", "Lru/mail/authorizationsdk/feature/socialauth/choicescreen/analytics/ChoiceAccAnalytics;", "Lru/mail/authorizationsdk/feature/socialauth/esiascreen/analytics/EsiaAnalytics;", "Lru/mail/authorizationsdk/external/analytics/common/DebugGoogleAnalytics;", "Lru/mail/authorizationsdk/external/analytics/common/DebugAutologinAnalytics;", "Lru/mail/authorizationsdk/feature/restorevkpassword/analytics/RestoreVkAnalytics;", "Lru/mail/authorizationsdk/feature/bindemail/analytics/EsiaBindEmailAnalytics;", "Lru/mail/authorizationsdk/feature/vkid/screens/wrongvkidaccount/analytics/WrongVkidAccountAnalytics;", "Lru/mail/authorizationsdk/feature/vkid/screens/vkfragmentsupport/analytics/VkFragmentSupportAnalytics;", "Lru/mail/authorizationsdk/feature/authactivity/vk/VkIdAuthAnalytics;", "Lru/mail/authorizationsdk/feature/phone/entercode/presentation/EnterPhoneCodeAnalytics;", "Lru/mail/authorizationsdk/feature/phone/enteremailcode/presentation/EnterEmailCodeAnalytics;", "Lru/mail/authorizationsdk/feature/phone/enteremailcodeafterlistacc/presentation/EnterEmailCodeAfterListAccAnalytics;", "Lru/mail/authorizationsdk/feature/changepassword/analytics/ChangePasswordAnalytics;", "externalAnalyticsConsumer", "Lru/mail/authorizationsdk/external/analytics/AuthAnalyticsSdk;", ViewerActivity.FILTER, "Lkotlin/Lazy;", "Lru/mail/util/log/LogFilter;", "<init>", "(Lru/mail/authorizationsdk/external/analytics/AuthAnalyticsSdk;Lkotlin/Lazy;)V", "showPassAuth", "", "mailService", "", "showStartScreenOnWrongNav", "screen", "serviceTypeOnCustomServerScreen", "sendDomainSuggestionFlowAnalytics", "flow", "sendDomainClickedAnalytics", "index", "", "loginActionSignIn", "step", "from", "login", "onShowLoginScreen", "loginResult", "result", "onQrLoginStarted", "onQrLoginAuthError", "logMailClick", "onGmailServiceShow", "isUseNewImageLogo", "", "logoListClick", "service", "onClickEsiaButton", "fromScreen", "onShowEsiaButton", "onShowOneTapSignin", "isOneTapEnabled", "onSuccessForceVKIDAuth", "onVKIDAuthCancel", "onSuccessVkBind", "email", "onSuccessEsiaBind", "onClickGmailLoginButton", "onShowGmailLoginButton", "onVKIDNextButtonClick", "userLoaded", "onVkIdButtonShown", "onForceVKIDShown", "onForceVKIDClick", "onForceVKIDNoCredentialsError", "onForceVKIDNoFailUrlError", "onForceVKIDUnknownError", "onForceVKIDNetworkError", "onForceVKIDApiError", "swaStatus", "(Ljava/lang/Integer;)V", "onForceVKIDEmailsSuccessfullyFetched", "onForceVKIDEmailsFetchingError", "onForceVKIDRefreshPreflightToken", "onForceVKIDBlockedUser", "blockReason", "imageClickedAnalytic", "sendLoginDomainSuggested", "sendLoginEditStarted", "phoneOnInputCreateCloudScreenShow", "phoneOnInputCreateCloudScreenClickCreateCloud", "phoneOnInputCreateCloudScreenWrongState", "phoneAuthError", "screenName", "error", "", "supportReport", "", "phoneOnInputAccountListScreenShow", "phoneOnInputAccountListScreenCountsAccounts", "count", "phoneOnInputAccountListScreenClickAccount", "phoneOnInputAccountListScreenClickCreateAccount", "phoneOnInputAccountListScreenClickAnotherAccount", "phoneOnInputAccountListScreenAccountCreated", "phoneOnInputNotFoundAccountScreenShow", "phoneOnInputNotFoundAccountScreenClickCreateCloud", "phoneOnInputNotFoundAccountScreenClickOtherAccount", "phoneOnInputAccountListScreenWrongState", "loginCheck", "loginViewPassword", "isChecked", "loginActionOAuthLogin", "type", "loginActionRestorePassword", "wasPasswordError", "withEmail", "blockedWithoutPhone", "createMailAccActionClick", "isEmailFilled", "loginActionMissClick", "authNavigationBackAction", "oneTimeCodeActionClick", "sendLoginBindEditStarted", "loginBindFlowActionSignIn", "createMailAccBindFlowActionClick", "isValidEmail", "sendBindFlDomainSuggestionFlowAnalytics", "onShowLoginBindFlowScreen", "loginBindFlowResult", "logBindFlowMailClick", "sendLoginBindFlDomainSuggested", "alreadyLinkedError", "loginBindFlowPushAuthWrongResult", "domain", "loginActionImmediateCodeAuth", "onStartVkPasswordAuthorization", "externalAuthCheckError", "onStartSSOAuthorization", "loginActionSSOOAuth", "onCloseLoginWithoutResult", "userFlow", "onBadSDKResultFromLogin", "nonSuccessResult", "onLoginAlternativeMode", "authMode", "loginActionVkPasswordOAuth", "loginActionVkIdBindAvailableAuth", "loginActionVkEmailForwardingAuth", "commonLoginError", "commonLoginActionCheck", "commonLoginSignIn", "loginError", "loginFocusLogin", "hasFocus", "loginFocusPassword", "manualSettingsLoginView", "isLocalImapFlow", "customServerScreenClosed", "onSuccessConnect", "startImapAuthTry", "saveExternalDomainProvider", "errorNoProvider", BatchApiRequest.FIELD_NAME_ON_ERROR, "errorCantParseFileConfig", "errorCantParseOmicronConfig", "onSuccessAuth", "onSdkResult", "onRequestMapperError", "wrongSdkWork", "mode", "sendRequestDurationAnalyticsIfEnabled", "durationMilliseconds", "", "requestName", "captchaCanceledByUser", "durationMls", "isDomStorageEnabled", "captchaCriticalError", "captchaShowErrorBadConnection", "captchaShowed", "captchaSuccessDone", "updateWebViewDialogNegativeButtonClicked", "updateWebViewDialogPositiveButtonClicked", "updateWebViewDialogShowed", "onWebViewError", "errorDescription", "oneTimeCodeUpdateWebViewDialogShowed", "oneTimeCodeUpdateWebViewDialogNegative", "oneTimeCodeUpdateWebViewDialogPositive", "oneTimeCodeWebViewShowed", "resource", "oneTimeCodeWebViewClose", "oneTimeCodeSuccess", "oneTimeCodeSwitchToPass", "oneTimeCodeFail", "oneTimeCodeError", "onOneTimeCodeResult", "googleScreenClosed", "unknownGoogleError", "migrantRegistrationRequired", "googleScreenNetworkError", "googleEmailEmptyError", "googleImapFailed", "googleMrimError", "googleApiError", "startGoogleScreen", "isAGroup", "isXmail", "openGoogleAuth", "startGoogleSignInActivity", "googleSignInSuccess", "googleSignInError", "errorMsg", "googleSignInProgress", "googleResultCancelled", "googleSignInRequiredResolution", "startGoogleResolution", "googleResolutionResultSuccess", "googleNoResolutionShowErrorDialog", "googleErrorDialogClosed", "googleSwitchToWebView", "reason", "googleAuthClosed", "secondFactorUpdateWebViewDialogShowed", "secondFactorUpdateWebViewDialogNegative", "secondFactorUpdateWebViewDialogPositive", "secondFactorWebViewLoadingFailed", "secondFactorWebViewShowed", "isNpcMode", "secondFactorWebViewClose", "secondFactorSuccess", "secondFactorSwitchToPass", "secondFactorSwitchToRecovery", "secondFactorFail", "secondFactorError", "externalAccountScreenShow", "externalAccountScreenResult", "externalAccountScreenBadInitialization", "customFailedCgiBinAuth", "prefix", "failedCgiBinAuth", "netErrorCgiBinAuth", "Ljava/lang/Exception;", "Lkotlin/Exception;", "sendCgiBinAuthSecStepFlow", "restorePasswordUpdateWebViewDialogShowed", "restorePasswordUpdateWebViewDialogNegative", "restorePasswordUpdateWebViewDialogPositive", "yahooUpdateWebViewDialogShowed", "yahooUpdateWebViewDialogNegative", "yahooUpdateWebViewDialogPositive", "yahooMrimError", "yahooApiError", "yandexUpdateWebViewDialogShowed", "yandexUpdateWebViewDialogNegative", "yandexUpdateWebViewDialogPositive", "yandexImapFailed", "yandexMrimError", "yandexApiError", "outlookUpdateWebViewDialogShowed", "outlookUpdateWebViewDialogNegative", "outlookUpdateWebViewDialogPositive", "outlookImapFailed", "outlookMrimError", "outlookApiError", "loadingOAuthWebViewScreen", "status", "errorMessage", "yahooOnOauthRequestEmailEmptyError", "yahooImapFailed", "outlookOnOauthRequestEmailEmptyError", "yandexOnOauthRequestEmailEmptyError", "yandexOpenScreen", "yandexScreenClosed", "yandexScreenNetworkError", "unknownYandexError", "outlookScreenNetworkError", "unknownOutlookError", "yahooScreenNetworkError", "yahooScreenOpen", "yahooScreenClosed", "outlookScreenOpen", "outlookScreenClosed", "startEmailAuthBySocial", "stringToken", "onSkipRegistrationWithVkc", "onReflectError", "onVkHack", "event", "Lru/mail/authorizationsdk/feature/socialauth/analytics/SocialAuthAnalytics$VkFastButtonAnalytics;", "onStartVkAuth", "onVkAuthSuccess", "onVkAuthError", "onStartVkEmailForwarding", "onVkEmailForwardingError", "msg", "onStartVkAutologin", "onVkAutologinSuccess", "onVkAutologinError", "onStartForceVkAuth", "onForceVkAuthSuccess", "onForceVkAuthError", "onForceVkAuthCanceled", "onStartEsiaAuth", "onEsiaAuthSuccess", "onEsiaAuthError", "onRestoreVkidStart", "source", "Lru/mail/social/auth/RestoreVkidStartSource;", "onRestoreVkidOpenMailRestore", "isRebind", "onRestoreVkidOpenVkRestore", "onRestoreVkidOpenAuth", "onRestoreVkidSuccessVkAuth", "onRestoreVkidCancel", "onRestoreVkidError", "onVkEmailForwardingSuccess", "onVkEmailForwardingGoToPassword", "onVkEmailForwardingCancel", "passAuthView", "serviceType", "updWebViewDialogShowed", "updWebViewDialogNegative", "updWebViewDialogPositive", "track", "name", "params", "socialAuthTrack", "onYandexHelpScreenShown", "onYandexHelpScreenBackBtnClicked", "onYandexHelpScreenGoToLoginClicked", "onYandexHelpScreenInstructionBtnClicked", "yandexHelpScreenClosed", "restorePasswordNetworkError", "restorePasswordScreenShowed", "restorePasswordWebViewLoadingFailed", "restorePasswordScreenClosed", "restorePasswordSuccessRebind", "onSSOUpdateWebViewDialogShowed", "onSSOUpdateWebViewClickNegative", "onSSOUpdateWebViewClickPositive", "onSSOWebViewShowed", "onSSOWebViewClose", "onSSORedirectSuccess", "onSSORedirectSuccessTokenIsNull", "onSSORedirectFail", "onSSORedirectInternalError", "onSSOPageError", "onSSOPageFinished", "onSSOPageStarted", "needLoadAgain", "ssoScreenClosed", "sendRestoreScheduledAnalytic", "timeTillShow", "restoreType", "isRestore", "moreThanOne", "sendAnalyticRestoreShown", "passwordScreenShowed", "passwordScreenSdkShowed", "passwordScreenClosed", "ssoWebViewLoadingFailed", "onVkPasswordUpdateWebViewDialogShowed", "onVkPasswordUpdateWebViewClickNegative", "onVkPasswordUpdateWebViewClickPositive", "onVkPasswordWebViewShowed", "onVkPasswordWebViewClose", "onVkPasswordRedirectSuccess", "onVkPasswordRedirectSuccessTokenIsNull", "onVkPasswordRedirectFail", "onVkPasswordRedirectInternalError", "onVkPasswordRedirectToRecovery", "onVkPasswordPageError", "onVkPasswordPageFinished", "onVkPasswordPageStarted", "vkPasswordScreenClosed", "vkPasswordWebViewLoadingFailed", "openGoogleWebAuth", "googleWebAuthActivityNotFound", "googleWebAuthStarted", "googleWebAuthNoDataFromGoogle", "googleWebAuthSuccessful", "googleWebAuthGetSuccessResp", "googleWebAuthClosed", "googleWebAuthNetworkError", "googleWebAuthServerError", "googleWebAuthCancelled", "googleWebAuthPermissionsDenied", "googleWebAuthError", "errorCode", "onBeforeRecoveryClickRestore", "onBeforeRecoveryBackClick", "onBeforeRecoveryScreenClosed", "onUnblockUserGoToChangePassword", "onUnblockUserLoadingFailed", "onUnblockUserScreenClosed", "onUnblockUserUpdateWebViewShown", "onUnblockUserUpdateWebViewClickNegative", "onUnblockUserUpdateWebViewClickPositive", "onChangePasswordStarted", "onChangePasswordFailedToStart", "onChangePasswordSuccess", "onChangePasswordCancelled", "onChangePasswordNotAuthorizedError", "onChangePasswordScreenClosed", "onClickUnblock", "onShow", "onDismiss", "couldNotOpenBrowser", "wrongSdkWorkReg", "onResultMainReg", "onStartRegFlow", "registrationResult", "onSubmitResultReg", "registrationNextClick", "regForm", "onOpenRegistration", "onLeaveRegistration", "onVkBindInLoginShow", "isPasswordBtnVisible", "isResetPasswordWarningVisible", "userEmail", "onVkBindInLoginClose", "onVkBindInLoginContinueClick", "onVkBindInLoginPasswordClick", "onEnterPhoneResult", "onRegistrationError", "Lru/mail/authorizationsdk/feature/registration/RegistrationAnalytics$RegistrationError;", "onStartChoiceAccScreen", "onAnyNetworkError", "onOldChoiceFragmentView", "bindType", "onChoiceAccScreenResult", "onForceVkIdChoiceAuthError", "screenType", "onEsiaAccLimitError", "onChoiceAction", "onChoiceActionSettings", "onChoiceActionBind", "bindAction", "onSocialAccAuth", "agTokenIsEmptyError", "onForceVkIdAccountClick", "emailList", "onChoiceActionUnblock", "onEsiaScreenShow", "onEsiaResult", "onEsiaUpdateDialogShow", "onEsiaUpdateWebViewDialogPositiveButtonClk", "onEsiaUpdateWebViewDialogNegativeButtonClk", "esiaAuthFailed", "esiaAuthSuccess", "onStartAuthMode", "onFirstStepSuccess", "onFirstStepError", "onBackClick", "onMigrantRegisterReq", "wrongWork", "oauthGoogleReq", "onStopShowAutologinByDelay", "onTryShowAutologin", "onShowAutologinChoice", "onSuccess", "startRestoreVkScreen", "restoreVkResult", "onShowBindEmailScreen", "onBindEmailChooseResult", "onShowWrongVkIdScreen", "showVkFastLoginScreen", "onVkFragmentResult", "onNoneModeError", "previousMode", "onVkIdAuthDelegateResult", "onOpenEnterPhoneCodeScreen", "onResultEnterPhoneCodeScreen", "onOpenEnterPhoneCodeScreenWrongState", "onOpenEnterEmailCodeScreen", "onResultEnterEmailCodeScreen", "onOpenEnterEmailCodeScreenWrongState", "onEnterEmailCodeAfterAccListScreenShown", "onEnterEmailCodeAfterAccountListWrong", "onEnterEmailCodeAfterAccountListNextClicked", "onEnterEmailCodeAfterAccountListError", "onEnterEmailCodeAfterAccountListNotReceived", "onEnterEmailCodeAfterAccountListAnotherClicked", "onEnterEmailCodeAfterAccountListResult", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AuthorizationSdkAnalyticsImpl implements AuthActivityAnalytics, RequestDurationAnalytics, AuthRequestAnalytics, LudwigCaptchaAnalyticEvents, OneTimeCodeAnalyticEvents, GoogleNativeAnalyticEvents, GoogleWebAnalyticEvents, GoogleAnalytics, SecondFactorAnalytics, ExternalAccMigrationAnalytics, YahooAnalytics, YandexAnalytics, MrimAnalytics, OutlookAnalytics, ImapLocalAnalytics, CustomServerAnalytics, CommonAnalytics, LoginAnalytics, LoginBindFlowAnalytics, ForceVKIDAnalytics, YandexHelpAnalytics, RestorePasswordAnalytics, SessionRestoreAnalytics, SSOAnalytics, PasswordAnalytics, VkPasswordAnalytics, SocialAuthAnalytics, RegistrationAnalytics, BeforeRecoveryVKIDAnalytics, EnterPhoneAnalytics, UnblockUserAnalytics, VkBindInLoginAnalytics, ChoiceAccAnalytics, EsiaAnalytics, DebugGoogleAnalytics, DebugAutologinAnalytics, RestoreVkAnalytics, EsiaBindEmailAnalytics, WrongVkidAccountAnalytics, VkFragmentSupportAnalytics, VkIdAuthAnalytics, EnterPhoneCodeAnalytics, EnterEmailCodeAnalytics, EnterEmailCodeAfterListAccAnalytics, ChangePasswordAnalytics {
    public static final int $stable = 0;

    @NotNull
    private static final String BIND_TYPE = "BindType";

    @NotNull
    private static final String EVENT_UPDATE_WEB_VIEW_DIALOG = "Upd_WView_Dial";

    @NotNull
    private static final String NEW_AUTH_SDK = "new_auth_sdk";

    @NotNull
    private static final String PARAM_ACTION = "action";

    @NotNull
    private static final String PARAM_AUTH_TYPE = "auth_type";

    @NotNull
    private static final String PARAM_DOMAIN = "domain";

    @NotNull
    public static final String PARAM_EVENT = "event";

    @NotNull
    private static final String PARAM_MODE = "mode";

    @NotNull
    private static final String PARAM_RESULT = "result";

    @NotNull
    private static final String PARAM_USER_FLOW = "flow";

    @NotNull
    private final AuthAnalyticsSdk externalAnalyticsConsumer;

    @NotNull
    private final Lazy<LogFilter> filter;

    public AuthorizationSdkAnalyticsImpl(@NotNull AuthAnalyticsSdk externalAnalyticsConsumer, @NotNull Lazy<LogFilter> filter) {
        Intrinsics.checkNotNullParameter(externalAnalyticsConsumer, "externalAnalyticsConsumer");
        Intrinsics.checkNotNullParameter(filter, "filter");
        this.externalAnalyticsConsumer = externalAnalyticsConsumer;
        this.filter = filter;
    }

    private final void passAuthView(String serviceType, String step) {
        track("PassAuth_View", MapsKt.mapOf(TuplesKt.to("serviceType", serviceType), TuplesKt.to("step", step)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void socialAuthTrack$default(AuthorizationSdkAnalyticsImpl authorizationSdkAnalyticsImpl, String str, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = null;
        }
        authorizationSdkAnalyticsImpl.socialAuthTrack(str, map);
    }

    private final void track(String name, Map<String, String> params) {
        Map<String, String> linkedHashMap;
        if (params == null || (linkedHashMap = MapsKt.toMutableMap(params)) == null) {
            linkedHashMap = new LinkedHashMap<>();
        }
        linkedHashMap.put(NEW_AUTH_SDK, "true");
        this.externalAnalyticsConsumer.onAnalyticEvent(name, linkedHashMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void track$default(AuthorizationSdkAnalyticsImpl authorizationSdkAnalyticsImpl, String str, Map map, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            map = null;
        }
        authorizationSdkAnalyticsImpl.track(str, map);
    }

    private final void updWebViewDialogNegative() {
        track(EVENT_UPDATE_WEB_VIEW_DIALOG, MapsKt.mapOf(TuplesKt.to("action", "negative")));
    }

    private final void updWebViewDialogPositive() {
        track(EVENT_UPDATE_WEB_VIEW_DIALOG, MapsKt.mapOf(TuplesKt.to("action", "positive")));
    }

    private final void updWebViewDialogShowed() {
        track(EVENT_UPDATE_WEB_VIEW_DIALOG, MapsKt.mapOf(TuplesKt.to("action", "show")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void agTokenIsEmptyError() {
        track$default(this, "ChoiceAccAgTokenIsEmpty", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void alreadyLinkedError() {
        track$default(this, "Login_BindFl_AlreadyBind", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void authNavigationBackAction(@NotNull String step, @NotNull String type) {
        Intrinsics.checkNotNullParameter(step, "step");
        Intrinsics.checkNotNullParameter(type, "type");
        track("AuthNavigationBack_Action", MapsKt.mapOf(TuplesKt.to("step", step), TuplesKt.to("type", type)));
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void captchaCanceledByUser(long durationMls, boolean isDomStorageEnabled) {
        track("LudwigEvent_captcha_canceled", MapsKt.mapOf(TuplesKt.to("duration_mls", String.valueOf(durationMls)), TuplesKt.to("is_dom_storage_enabled", String.valueOf(isDomStorageEnabled))));
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void captchaCriticalError(@NotNull String error, boolean isDomStorageEnabled) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("LudwigEvent_critical_error", MapsKt.mapOf(TuplesKt.to("error", error), TuplesKt.to("is_dom_storage_enabled", String.valueOf(isDomStorageEnabled))));
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void captchaShowErrorBadConnection() {
        track$default(this, "LudwigEvent_bad_network_connection", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void captchaShowed() {
        track$default(this, "LudwigEvent_captcha_show", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void captchaSuccessDone(long durationMls, boolean isDomStorageEnabled) {
        track("LudwigEvent_captcha_success_done", MapsKt.mapOf(TuplesKt.to("duration_mls", String.valueOf(durationMls)), TuplesKt.to("is_dom_storage_enabled", String.valueOf(isDomStorageEnabled))));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void commonLoginActionCheck() {
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "Check")));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void commonLoginError(@NotNull String error, @NotNull String step) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(step, "step");
        loginError(error, step);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void commonLoginSignIn() {
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "signin"), TuplesKt.to("step", "1step")));
    }

    @Override // ru.mail.authorizationsdk.feature.mrim.MrimAnalytics
    public void couldNotOpenBrowser() {
        track$default(this, "MrimDialogCouldNotOpenBrowser", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void createMailAccActionClick(@NotNull String step, boolean isEmailFilled) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("CreateMailAcc", MapsKt.mapOf(TuplesKt.to("Action", "click"), TuplesKt.to("step", step), TuplesKt.to("email_filled", String.valueOf(isEmailFilled))));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void createMailAccBindFlowActionClick(@NotNull String step, boolean isValidEmail) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("BindFlowCreateMailAcc", MapsKt.mapOf(TuplesKt.to("Action", "click"), TuplesKt.to("step", step), TuplesKt.to("email_filled", String.valueOf(isValidEmail))));
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthRequestAnalytics
    public void customFailedCgiBinAuth(@Nullable String prefix, @NotNull String swaStatus) {
        Intrinsics.checkNotNullParameter(swaStatus, "swaStatus");
        if (prefix == null) {
            prefix = "";
        }
        track("CgiBinA_CusErr" + prefix, MapsKt.mapOf(TuplesKt.to("swaStatus", swaStatus)));
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.analytics.CustomServerAnalytics
    public void customServerScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("CustomServerScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void errorCantParseFileConfig(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("ImapLocalErrParseFileConf", MapsKt.mapOf(TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void errorCantParseOmicronConfig(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("ImapLocalErrParseOmicronConf", MapsKt.mapOf(TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void errorNoProvider(@NotNull String domain) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        track("ImapLocalErrNoProvider", MapsKt.mapOf(TuplesKt.to("domain", domain)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void esiaAuthFailed(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("Esia_Auth_Failed_Event", MapsKt.mapOf(TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void esiaAuthSuccess() {
        track$default(this, "Esia_Auth_Success_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.externalmigration.analytics.ExternalAccMigrationAnalytics
    public void externalAccountScreenBadInitialization(@NotNull String mode, @NotNull String login) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(login, "login");
        track("Xmail_Popup_Bad_initialization", MapsKt.mapOf(TuplesKt.to("type", mode), TuplesKt.to("login_email", login)));
    }

    @Override // ru.mail.authorizationsdk.feature.externalmigration.analytics.ExternalAccMigrationAnalytics
    public void externalAccountScreenResult(@NotNull String mode, @NotNull String login) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(login, "login");
        track("Xmail_Popup_Next_Click", MapsKt.mapOf(TuplesKt.to("type", mode), TuplesKt.to("login_email", login)));
    }

    @Override // ru.mail.authorizationsdk.feature.externalmigration.analytics.ExternalAccMigrationAnalytics
    public void externalAccountScreenShow(@NotNull String mode, @NotNull String login) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(login, "login");
        track("Xmail_Popup_Show", MapsKt.mapOf(TuplesKt.to("type", mode), TuplesKt.to("login_email", login)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void externalAuthCheckError() {
        track$default(this, "ExternalAuthProhibitCheckError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthRequestAnalytics
    public void failedCgiBinAuth(@NotNull String swaStatus) {
        Intrinsics.checkNotNullParameter(swaStatus, "swaStatus");
        track("CgiBinAuth_Error", MapsKt.mapOf(TuplesKt.to("swaStatus", swaStatus)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void googleApiError(int swaStatus) {
        track("GoogleScrApiError", MapsKt.mapOf(TuplesKt.to("code", String.valueOf(swaStatus))));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleAuthClosed() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.CLOSED)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void googleEmailEmptyError() {
        track$default(this, "GoogleScrEmailEmptyError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleErrorDialogClosed() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.ERROR_DIALOG_CLOSED)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void googleImapFailed() {
        track$default(this, "GoogleScrImapError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void googleMrimError() {
        track$default(this, "GoogleScrMrimError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleNoResolutionShowErrorDialog() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.ERROR_DIALOG_START)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleResolutionResultSuccess() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.RESOLUTION_RESULT_SUCCESS)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleResultCancelled() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.SIGN_IN_CANCELED)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void googleScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("GoogleScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void googleScreenNetworkError() {
        track$default(this, "GoogleScrNetworkError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleSignInError(@NotNull String errorMsg) {
        Intrinsics.checkNotNullParameter(errorMsg, "errorMsg");
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.SIGN_IN_ERROR), TuplesKt.to("error_msg", errorMsg)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleSignInProgress() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.SIGN_IN_PROGRESS)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleSignInRequiredResolution() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.SIGN_IN_REQUIRED_RESOLUTION)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleSignInSuccess() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.SIGN_IN_SUCCESS)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void googleSwitchToWebView(@NotNull String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.SWITCH_TO_WEB_AUTH), TuplesKt.to("reason", reason)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthActivityNotFound() {
        track$default(this, "GoogleWebAuthActivityNotFound", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthCancelled() {
        track$default(this, "GoogleWebAuthCancelled", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthClosed() {
        track$default(this, "GoogleWebAuthClosed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthError(int errorCode) {
        track("GoogleWebAuthError", MapsKt.mapOf(TuplesKt.to("error_code", String.valueOf(errorCode))));
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthGetSuccessResp(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("GoogleWebGetSuccessResp", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthNetworkError() {
        track$default(this, "GoogleWebAuthNetworkError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthNoDataFromGoogle() {
        track$default(this, "GoogleWebAuthNoDataFromGoogle", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthPermissionsDenied() {
        track$default(this, "GoogleWebAuthPermissionsDenied", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthServerError() {
        track$default(this, "GoogleWebAuthServerError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthStarted() {
        track$default(this, "GoogleWebAuthStarted", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void googleWebAuthSuccessful() {
        track$default(this, "GoogleWebAuthSuccessful", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void imageClickedAnalytic() {
        track$default(this, "Login_Image_Clicked_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics, ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics, ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void loadingOAuthWebViewScreen(@NotNull String status, @Nullable String errorMessage) {
        Intrinsics.checkNotNullParameter(status, "status");
        Pair pair = TuplesKt.to(AnalyticsConstants.KEY.STATUS, status);
        if (errorMessage == null) {
            errorMessage = "";
        }
        track("WebView_Auth_Screen_Loading_Result", MapsKt.mapOf(pair, TuplesKt.to("Error_Message", errorMessage)));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void logBindFlowMailClick() {
        track$default(this, "Logo_BindFl_Mail_Click_Action", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void logMailClick() {
        track$default(this, "Logo_Mail_Click_Action", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionImmediateCodeAuth() {
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("action", "ImmediateCodeAuth")));
    }

    @Override // ru.mail.authorizationsdk.feature.password.presentation.PasswordAnalytics
    public void loginActionMissClick(@NotNull String step) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "missclick"), TuplesKt.to("step", step)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionOAuthLogin(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("action", "OAuthLogin"), TuplesKt.to("provider", type)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void loginActionRestorePassword(boolean wasPasswordError, boolean withEmail, @NotNull String step, @Nullable String login, boolean blockedWithoutPhone) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "RestorePassword"), TuplesKt.to("passwError", String.valueOf(wasPasswordError)), TuplesKt.to("withemail", String.valueOf(withEmail)), TuplesKt.to("step", step), TuplesKt.to("login_email", String.valueOf(login)), TuplesKt.to("blockedWithoutPhone", String.valueOf(blockedWithoutPhone))));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionSSOOAuth() {
        track("Login_SSO_Action", MapsKt.mapOf(TuplesKt.to("action", "LoginSSO")));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionSignIn(@NotNull String step, @Nullable String from, @Nullable String login) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "signin"), TuplesKt.to("step", step), TuplesKt.to("from", String.valueOf(from)), TuplesKt.to("login_email", String.valueOf(login))));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionVkEmailForwardingAuth() {
        track("Login_VkEmailForwarding_Action", MapsKt.mapOf(TuplesKt.to("action", "LoginVkEmailForwarding")));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionVkIdBindAvailableAuth() {
        track("Login_VkIdBindAvailable_Action", MapsKt.mapOf(TuplesKt.to("action", "VkIdBindAvailable")));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginActionVkPasswordOAuth() {
        track("Login_VkPassword_Action", MapsKt.mapOf(TuplesKt.to("action", "LoginVkPassword")));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void loginBindFlowActionSignIn(@NotNull String step, @NotNull String from, @NotNull String login) {
        Intrinsics.checkNotNullParameter(step, "step");
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(login, "login");
        track("Login_Bind_Action", MapsKt.mapOf(TuplesKt.to("Action", "signin"), TuplesKt.to("step", step), TuplesKt.to("from", from), TuplesKt.to("login_email", login)));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void loginBindFlowPushAuthWrongResult(@NotNull String result, @NotNull String domain) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(domain, "domain");
        track("Login_BindFl_PushAuth_Error", MapsKt.mapOf(TuplesKt.to("result", result), TuplesKt.to("domain", domain)));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void loginBindFlowResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("LoginBindResult", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void loginCheck(@NotNull String fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "Check"), TuplesKt.to("screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void loginError(@NotNull String domain) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        track("Login_Error", MapsKt.mapOf(TuplesKt.to("Error", "Error"), TuplesKt.to("Domain_name", domain)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void loginFocusLogin(boolean hasFocus) {
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "Focus_Login"), TuplesKt.to("State", String.valueOf(hasFocus))));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void loginFocusPassword(boolean hasFocus, @Nullable String login) {
        Pair pair = TuplesKt.to("Action", "Focus_Password");
        Pair pair2 = TuplesKt.to("State", String.valueOf(hasFocus));
        if (login == null) {
            login = "";
        }
        track("Login_Action", MapsKt.mapOf(pair, pair2, TuplesKt.to("login_email", login)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void loginResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("Login_Result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void loginViewPassword(@NotNull String fromScreen, boolean isChecked) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("Login_Action", MapsKt.mapOf(TuplesKt.to("Action", "View_Password"), TuplesKt.to("State", String.valueOf(isChecked)), TuplesKt.to("Screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void logoListClick(@NotNull String service, @NotNull String from) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(from, "from");
        track("Logo_List_Click_Action", MapsKt.mapOf(TuplesKt.to("service", service), TuplesKt.to("from", from)));
    }

    @Override // ru.mail.authorizationsdk.feature.customserver.analytics.CustomServerAnalytics
    public void manualSettingsLoginView(boolean isLocalImapFlow) {
        track("Login_ManualSettings_View", MapsKt.mapOf(TuplesKt.to("is_local_imap", String.valueOf(isLocalImapFlow))));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void migrantRegistrationRequired() {
        track$default(this, "GoogleScrMigrantRegistrationRequired", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthRequestAnalytics
    public void netErrorCgiBinAuth(@NotNull Exception error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("CgiBinAuth_NetError", MapsKt.mapOf(TuplesKt.to("error", error.toString())));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void oauthGoogleReq(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_oauth_google" : "b_gr_oauth_google", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onAnyNetworkError() {
        track$default(this, "ChoiceAccountNetError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void onBackClick(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_back_cl" : "b_gr_back_cl", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void onBadSDKResultFromLogin(@NotNull String nonSuccessResult) {
        Intrinsics.checkNotNullParameter(nonSuccessResult, "nonSuccessResult");
        track("LoginBadRes", MapsKt.mapOf(TuplesKt.to("result", nonSuccessResult)));
    }

    @Override // ru.mail.authorizationsdk.feature.beforerecovery.analytics.BeforeRecoveryVKIDAnalytics
    public void onBeforeRecoveryBackClick() {
        track$default(this, "BeforeRecovery_BackClick", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.beforerecovery.analytics.BeforeRecoveryVKIDAnalytics
    public void onBeforeRecoveryClickRestore() {
        track$default(this, "BeforeRecovery_ClickRestore", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.beforerecovery.analytics.BeforeRecoveryVKIDAnalytics
    public void onBeforeRecoveryScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("BeforeRecovery_ScreenClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.bindemail.analytics.EsiaBindEmailAnalytics
    public void onBindEmailChooseResult(@NotNull String bindType, @NotNull String result) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        Intrinsics.checkNotNullParameter(result, "result");
        track((Intrinsics.areEqual(bindType, "esia") ? "ESIA" : "VK") + "BindEmailChooseScr", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics
    public void onChangePasswordCancelled() {
        track$default(this, "ChangePassword_Cancelled", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics
    public void onChangePasswordFailedToStart() {
        track$default(this, "ChangePassword_FailedToStart", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics
    public void onChangePasswordNotAuthorizedError() {
        track$default(this, "ChangePassword_NotAuthorizedError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics
    public void onChangePasswordScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("ChangePassword_ScreenClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics
    public void onChangePasswordStarted() {
        track$default(this, "ChangePassword_Started", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.changepassword.analytics.ChangePasswordAnalytics
    public void onChangePasswordSuccess() {
        track$default(this, "ChangePassword_Success", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onChoiceAccScreenResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("ChoiceAccountResult", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onChoiceAction(@NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track(AnalyticsConstants.EVENT.CHOICE_ACTION, MapsKt.mapOf(TuplesKt.to("BindType", bindType), TuplesKt.to("action", "login")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onChoiceActionBind(@NotNull String bindAction, @NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindAction, "bindAction");
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track(AnalyticsConstants.EVENT.CHOICE_ACTION, MapsKt.mapOf(TuplesKt.to("BindType", bindType), TuplesKt.to("action", bindAction)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onChoiceActionSettings(@NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track(AnalyticsConstants.EVENT.CHOICE_ACTION, MapsKt.mapOf(TuplesKt.to("BindType", bindType), TuplesKt.to("action", "settings")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onChoiceActionUnblock(@NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track(AnalyticsConstants.EVENT.CHOICE_ACTION, MapsKt.mapOf(TuplesKt.to("BindType", bindType), TuplesKt.to("action", "unblock")));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onClickEsiaButton(@NotNull String fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("ClickEsiaButton_Action", MapsKt.mapOf(TuplesKt.to("screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onClickGmailLoginButton(@NotNull String fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("ClickGmailLoginButton", MapsKt.mapOf(TuplesKt.to("screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.feature.mrim.MrimAnalytics
    public void onClickUnblock() {
        track$default(this, "MrimDialog_clickUnblock", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void onCloseLoginWithoutResult(@NotNull String userFlow) {
        Intrinsics.checkNotNullParameter(userFlow, "userFlow");
        track("LoginCloseNoRes", MapsKt.mapOf(TuplesKt.to("flow", userFlow)));
    }

    @Override // ru.mail.authorizationsdk.feature.mrim.MrimAnalytics
    public void onDismiss() {
        track$default(this, "MrimDialog_dismiss", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccListScreenShown() {
        track$default(this, "OpenEnterEmailCodeAfterAccountList", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccountListAnotherClicked() {
        track$default(this, "EnterEmailCodeAfterAccountListAnother", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccountListError() {
        track$default(this, "EnterEmailCodeAfterAccountListError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccountListNextClicked() {
        track$default(this, "EnterEmailCodeAfterAccountListNext", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccountListNotReceived() {
        track$default(this, "EnterEmailCodeAfterAccountListNotReceived", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccountListResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("EnterEmailCodeAfterAccountListResult", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcodeafterlistacc.presentation.EnterEmailCodeAfterListAccAnalytics
    public void onEnterEmailCodeAfterAccountListWrong() {
        track$default(this, "EnterEmailCodeAfterAccountListWrong", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.enterphone.presentation.EnterPhoneAnalytics
    public void onEnterPhoneResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track(EnterPhoneResult.RESULT_KEY, MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void onError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("ImapLocalErrNoProvider", MapsKt.mapOf(TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onEsiaAccLimitError() {
        track$default(this, "EsiaAccountAccountLimitError_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onEsiaAuthError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("EsiaAuth", MapsKt.mapOf(TuplesKt.to("event", "error"), TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onEsiaAuthSuccess() {
        track("EsiaAuth", MapsKt.mapOf(TuplesKt.to("event", "success")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void onEsiaResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("On_Esia_Result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void onEsiaScreenShow() {
        track$default(this, "On_Esia_Screen_Show", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void onEsiaUpdateDialogShow() {
        track(AnalyticsConstants.EVENT.WEB_VIEW_ERROR, MapsKt.mapOf(TuplesKt.to("action", "init_error")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void onEsiaUpdateWebViewDialogNegativeButtonClk() {
        track(AnalyticsConstants.EVENT.WEB_VIEW_ERROR, MapsKt.mapOf(TuplesKt.to("action", "cancel_update")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.esiascreen.analytics.EsiaAnalytics
    public void onEsiaUpdateWebViewDialogPositiveButtonClk() {
        track(AnalyticsConstants.EVENT.WEB_VIEW_ERROR, MapsKt.mapOf(TuplesKt.to("action", "confirm_update")));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void onFirstStepError(boolean isAGroup, @NotNull String errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        track(isAGroup ? "a_gr_first_step_error" : "b_gr_first_step_error", MapsKt.mapOf(TuplesKt.to("error", errorCode)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void onFirstStepSuccess(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_google_first_step_success" : "b_gr_google_first_step_success", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDApiError(@Nullable Integer swaStatus) {
        track("ForceVKID_ApiError", MapsKt.mapOf(TuplesKt.to("code", String.valueOf(swaStatus))));
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDBlockedUser(@NotNull String blockReason) {
        Intrinsics.checkNotNullParameter(blockReason, "blockReason");
        track$default(this, "ForceVKID_BlockedUser", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDClick() {
        track$default(this, "ForceVKID_Click", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDEmailsFetchingError() {
        track$default(this, "ForceVKID_EmailsFetchingError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDEmailsSuccessfullyFetched() {
        track$default(this, "ForceVKID_EmailsFetched", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDNetworkError() {
        track$default(this, "ForceVKID_NetworkError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDNoCredentialsError() {
        track$default(this, "ForceVKID_NoCredentials", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDNoFailUrlError() {
        track$default(this, "ForceVKID_NoFailUrl", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDRefreshPreflightToken() {
        track$default(this, "ForceVKID_RefreshPreflightToken", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDShown() {
        track$default(this, "ForceVKID_Shown", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.forcevkid.analytics.ForceVKIDAnalytics
    public void onForceVKIDUnknownError() {
        track$default(this, "ForceVKID_UnknownError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onForceVkAuthCanceled(@NotNull String screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        track("CancelVKIDAuth_Event", MapsKt.mapOf(TuplesKt.to("screen", screen)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onForceVkAuthError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("VkForce", MapsKt.mapOf(TuplesKt.to("event", "error"), TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onForceVkAuthSuccess() {
        track("VkForce", MapsKt.mapOf(TuplesKt.to("event", "success")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onForceVkIdAccountClick(@NotNull String login, @NotNull String emailList) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(emailList, "emailList");
        track("ForceVkidAuth_OtherVkid_emailclick", MapsKt.mapOf(TuplesKt.to("click_email", login), TuplesKt.to("email_list", emailList)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onForceVkIdChoiceAuthError(@NotNull String screenType) {
        Intrinsics.checkNotNullParameter(screenType, "screenType");
        track("ForceVkidAuth_Error", MapsKt.mapOf(TuplesKt.to("screen_type", screenType)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onGmailServiceShow(boolean isUseNewImageLogo, @NotNull String from) {
        Intrinsics.checkNotNullParameter(from, "from");
        track("GmailServiceShow", MapsKt.mapOf(TuplesKt.to("isImageLogo", String.valueOf(isUseNewImageLogo)), TuplesKt.to("from", from)));
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void onLeaveRegistration() {
        track("Registration_View", MapsKt.mapOf(TuplesKt.to("Action", "Leave")));
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void onLoginAlternativeMode(@NotNull String authMode) {
        Intrinsics.checkNotNullParameter(authMode, "authMode");
        track("LoginAltMode", MapsKt.mapOf(TuplesKt.to("mode", authMode)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void onMigrantRegisterReq(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_migrant_reg" : "b_gr_migrant_reg", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.vk.VkIdAuthAnalytics
    public void onNoneModeError(@NotNull String previousMode) {
        Intrinsics.checkNotNullParameter(previousMode, "previousMode");
        track("vkIdAuthNone", MapsKt.mapOf(TuplesKt.to("mode", previousMode)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onOldChoiceFragmentView(@NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track("Choice_Fragment_View", MapsKt.mapOf(TuplesKt.to("BindType", bindType)));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void onOneTimeCodeResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track(OneTimeCodeResult.RESULT_KEY, MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeAnalytics
    public void onOpenEnterEmailCodeScreen() {
        track$default(this, "OpenEnterEmailCodeSc", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeAnalytics
    public void onOpenEnterEmailCodeScreenWrongState() {
        track$default(this, "EnterEmailCodeScrWrong", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeAnalytics
    public void onOpenEnterPhoneCodeScreen() {
        track$default(this, "OpenEnterPhoneCodeSc", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeAnalytics
    public void onOpenEnterPhoneCodeScreenWrongState() {
        track$default(this, "EnterPhoneCodeScrWrong", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void onOpenRegistration(@NotNull String regForm, @NotNull String from) {
        Intrinsics.checkNotNullParameter(regForm, "regForm");
        Intrinsics.checkNotNullParameter(from, "from");
        track("Registration_View", MapsKt.mapOf(TuplesKt.to("Action", "Open"), TuplesKt.to("RegForm", regForm), TuplesKt.to("From", from)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onQrLoginAuthError() {
        track$default(this, "QrLoginAuth", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onQrLoginStarted() {
        track$default(this, "QrAuthenticationStart", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onReflectError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("VkSignInReflection_Error", MapsKt.mapOf(TuplesKt.to("msg", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void onRegistrationError(@NotNull RegistrationAnalytics.RegistrationError error, @NotNull String regForm) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(regForm, "regForm");
        track("Registration_Error", MapsKt.mapOf(TuplesKt.to("Error", error.getValue()), TuplesKt.to("RegForm", regForm), TuplesKt.to("Place", "Registration")));
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void onRequestMapperError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("auth_sdk_request_map_err", MapsKt.mapOf(TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidCancel() {
        track$default(this, "RestoreVkidCancel", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidError(@Nullable String msg) {
        track("RestoreVkidError", MapsKt.mapOf(TuplesKt.to("msg", String.valueOf(msg))));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidOpenAuth() {
        track$default(this, "RestoreVkidOpenAuth", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidOpenMailRestore(boolean isRebind) {
        track("RestoreVkidOpenMailRestore", MapsKt.mapOf(TuplesKt.to(RestorePasswordViewModel.IS_REBIND, String.valueOf(isRebind))));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidOpenVkRestore() {
        track$default(this, "RestoreVkidOpenVkRestore", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidStart(@NotNull RestoreVkidStartSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        track("RestoreVkidStart", MapsKt.mapOf(TuplesKt.to("source", source.name())));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onRestoreVkidSuccessVkAuth() {
        track$default(this, "RestoreVkidSuccessVkAuth", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.phone.enteremailcode.presentation.EnterEmailCodeAnalytics
    public void onResultEnterEmailCodeScreen(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track(EnterEmailCodeResult.RESULT_KEY, MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.phone.entercode.presentation.EnterPhoneCodeAnalytics
    public void onResultEnterPhoneCodeScreen(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track(EnterPhoneCodeResult.RESULT_KEY, MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void onResultMainReg(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("reg_sdk_result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOPageError() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "page_error")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOPageFinished() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "page_finish")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOPageStarted(boolean needLoadAgain) {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "page_start"), TuplesKt.to("needLoadAgain", String.valueOf(needLoadAgain))));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSORedirectFail() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "redirect_fail")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSORedirectInternalError() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "internal_error")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSORedirectSuccess() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "redirect_success")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSORedirectSuccessTokenIsNull() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "ag_token_null")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOUpdateWebViewClickNegative() {
        track("Upd_SSO_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "update_wv_negative")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOUpdateWebViewClickPositive() {
        track("Upd_SSO_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "update_wv_positive")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOUpdateWebViewDialogShowed() {
        track("Upd_SSO_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "update_wv_show")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOWebViewClose() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "close")));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void onSSOWebViewShowed() {
        track("SSO_Event", MapsKt.mapOf(TuplesKt.to("event", "show")));
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void onSdkResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("auth_sdk_result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.mrim.MrimAnalytics
    public void onShow() {
        track$default(this, "MrimDialog_show", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugAutologinAnalytics
    public void onShowAutologinChoice(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_autologin_show" : "b_gr_autologin_show", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.bindemail.analytics.EsiaBindEmailAnalytics
    public void onShowBindEmailScreen(@NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track("BindEmailScr_Show", MapsKt.mapOf(TuplesKt.to("BindType", bindType)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onShowEsiaButton(@NotNull String fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("ShowEsiaButton", MapsKt.mapOf(TuplesKt.to("screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onShowGmailLoginButton(@NotNull String fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("ShowGmailLoginButton", MapsKt.mapOf(TuplesKt.to("screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void onShowLoginBindFlowScreen() {
        track$default(this, "ShowLoginBindFlowScreen", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onShowLoginScreen() {
        track$default(this, "ShowLoginScreen", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onShowOneTapSignin(@NotNull String fromScreen, boolean isOneTapEnabled) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("ShowOneTapSignin", MapsKt.mapOf(TuplesKt.to("From", fromScreen), TuplesKt.to("OneTap", String.valueOf(isOneTapEnabled))));
    }

    @Override // ru.mail.authorizationsdk.feature.vkid.screens.wrongvkidaccount.analytics.WrongVkidAccountAnalytics
    public void onShowWrongVkIdScreen() {
        track$default(this, "WrongVkIdAcc", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onSkipRegistrationWithVkc() {
        track$default(this, "VkSocialAuthenticationSkipped_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onSocialAccAuth(@NotNull String bindType) {
        Intrinsics.checkNotNullParameter(bindType, "bindType");
        track("Start_Email_Auth_By_Social_Event", MapsKt.mapOf(TuplesKt.to("BindType", bindType)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void onStartAuthMode(boolean isAGroup, @NotNull String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        track(isAGroup ? "a_gr_start_mode" : "b_gr_start_mode", MapsKt.mapOf(TuplesKt.to("mode", mode)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.choicescreen.analytics.ChoiceAccAnalytics
    public void onStartChoiceAccScreen(@NotNull String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        track("ChoiceAccountStart", MapsKt.mapOf(TuplesKt.to("mode", mode)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onStartEsiaAuth() {
        track("EsiaAuth", MapsKt.mapOf(TuplesKt.to("event", "start")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onStartForceVkAuth() {
        track("VkForce", MapsKt.mapOf(TuplesKt.to("event", "start")));
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void onStartRegFlow() {
        track$default(this, "reg_sdk_start", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void onStartSSOAuthorization() {
        track$default(this, "SSOAuthorizationStarted", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onStartVkAuth() {
        track("VkAuth", MapsKt.mapOf(TuplesKt.to("event", "start")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onStartVkAutologin() {
        track("VkAutologin", MapsKt.mapOf(TuplesKt.to("event", "start")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onStartVkEmailForwarding() {
        track$default(this, "VkEmailForwarding_Event", null, 2, null);
        track$default(this, "VkEmailForwarding_sdk_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void onStartVkPasswordAuthorization() {
        track$default(this, "VkPasswordAuthorizationStarted", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugAutologinAnalytics
    public void onStopShowAutologinByDelay() {
        track$default(this, "b_gr_autologin_stop_delay", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void onSubmitResultReg(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("Reg_Result_Submit", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugAutologinAnalytics
    public void onSuccess(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_autologin_success" : "b_gr_autologin_success", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void onSuccessAuth(@NotNull String type) {
        Intrinsics.checkNotNullParameter(type, "type");
        track("auth_sdk_succ_auth", MapsKt.mapOf(TuplesKt.to("auth_type", type)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void onSuccessConnect() {
        track$default(this, "ImapLocalSuccessAuth", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onSuccessEsiaBind() {
        track$default(this, "SuccessEsiaBind", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onSuccessForceVKIDAuth() {
        track$default(this, "Force_VKID_Auth_Success_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onSuccessVkBind(@NotNull String email, @NotNull String from) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(from, "from");
        track("VkBindSuccess_Result", MapsKt.mapOf(TuplesKt.to("auth_email", email), TuplesKt.to("from", from)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugAutologinAnalytics
    public void onTryShowAutologin() {
        track$default(this, "b_gr_autologin_try_show", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics
    public void onUnblockUserGoToChangePassword() {
        track$default(this, "new_social_auth_sdk_open_vk_change_pass", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics
    public void onUnblockUserLoadingFailed() {
        track$default(this, "new_social_auth_sdk_loading_failed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics
    public void onUnblockUserScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("new_social_auth_sdk_screen_closed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics
    public void onUnblockUserUpdateWebViewClickNegative() {
        track$default(this, "new_social_auth_sdk_update_negative", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics
    public void onUnblockUserUpdateWebViewClickPositive() {
        track$default(this, "new_social_auth_sdk_update_positive", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.unblockuser.analytics.UnblockUserAnalytics
    public void onUnblockUserUpdateWebViewShown() {
        track$default(this, "new_social_auth_sdk_update_shown", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onVKIDAuthCancel(@NotNull String screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        track("Force_VKID_Auth_Success_Event", MapsKt.mapOf(TuplesKt.to("screen", screen)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onVKIDNextButtonClick(@NotNull String fromScreen, boolean userLoaded) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("VKIDNextButtonClick", MapsKt.mapOf(TuplesKt.to("fromScreen", fromScreen), TuplesKt.to("userLoaded", String.valueOf(userLoaded))));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkAuthError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("VkAuth", MapsKt.mapOf(TuplesKt.to("event", "error"), TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkAuthSuccess() {
        track("VkAuth", MapsKt.mapOf(TuplesKt.to("event", "success")));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkAutologinError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("VkAutologin", MapsKt.mapOf(TuplesKt.to("event", "error"), TuplesKt.to("error", error)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkAutologinSuccess() {
        track("VkAutologin", MapsKt.mapOf(TuplesKt.to("event", "success")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkbindavailable.analytics.VkBindInLoginAnalytics
    public void onVkBindInLoginClose(boolean isPasswordBtnVisible, boolean isResetPasswordWarningVisible, @NotNull String userEmail) {
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        track("VkBindInLoginClose_Event", MapsKt.mapOf(TuplesKt.to("auth_email", userEmail), TuplesKt.to("password_reset_warning_visible", String.valueOf(isResetPasswordWarningVisible)), TuplesKt.to("password_btn_visible", String.valueOf(isPasswordBtnVisible))));
    }

    @Override // ru.mail.authorizationsdk.feature.vkbindavailable.analytics.VkBindInLoginAnalytics
    public void onVkBindInLoginContinueClick(boolean isPasswordBtnVisible, boolean isResetPasswordWarningVisible, @NotNull String userEmail) {
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        track("VkBindInLoginContinueClick_Action", MapsKt.mapOf(TuplesKt.to("auth_email", userEmail), TuplesKt.to("password_reset_warning_visible", String.valueOf(isResetPasswordWarningVisible)), TuplesKt.to("password_btn_visible", String.valueOf(isPasswordBtnVisible))));
    }

    @Override // ru.mail.authorizationsdk.feature.vkbindavailable.analytics.VkBindInLoginAnalytics
    public void onVkBindInLoginPasswordClick(boolean isResetPasswordWarningVisible, @NotNull String userEmail) {
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        track("VkBindInLoginPasswordClick_Action", MapsKt.mapOf(TuplesKt.to("auth_email", userEmail), TuplesKt.to("password_reset_warning_visible", String.valueOf(isResetPasswordWarningVisible))));
    }

    @Override // ru.mail.authorizationsdk.feature.vkbindavailable.analytics.VkBindInLoginAnalytics
    public void onVkBindInLoginShow(boolean isPasswordBtnVisible, boolean isResetPasswordWarningVisible, @NotNull String userEmail) {
        Intrinsics.checkNotNullParameter(userEmail, "userEmail");
        track("VkBindInLoginShow_Event", MapsKt.mapOf(TuplesKt.to("auth_email", userEmail), TuplesKt.to("password_reset_warning_visible", String.valueOf(isResetPasswordWarningVisible)), TuplesKt.to("password_btn_visible", String.valueOf(isPasswordBtnVisible))));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkEmailForwardingCancel() {
        track$default(this, "VkEmailForwarding_cancel_Event", null, 2, null);
        track$default(this, "VkEmailForwarding_cancel_sdk_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkEmailForwardingError(@NotNull String email, @NotNull String msg) {
        Intrinsics.checkNotNullParameter(email, "email");
        Intrinsics.checkNotNullParameter(msg, "msg");
        track("VkEmailForwarding_Error", MapsKt.mapOf(TuplesKt.to("msg", msg), TuplesKt.to("forwarding_email", email)));
        track("VkEmailForwarding_sdk_Error", MapsKt.mapOf(TuplesKt.to("msg", msg), TuplesKt.to("forwarding_email", email)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkEmailForwardingGoToPassword() {
        track$default(this, "VkEmailForwarding_to_pass_Result", null, 2, null);
        track$default(this, "VkEmailForwarding_to_pass_sdk_Result", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkEmailForwardingSuccess() {
        track$default(this, "VkEmailForwarding_success_Result", null, 2, null);
        track$default(this, "VkEmailForwarding_success_sdk_Result", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.analytics.VkFragmentSupportAnalytics
    public void onVkFragmentResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("onVkFragmentResult", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void onVkHack(@NotNull SocialAuthAnalytics.VkFastButtonAnalytics event) {
        Intrinsics.checkNotNullParameter(event, "event");
        track("VkHack", MapsKt.mapOf(TuplesKt.to("event", event.getEvent())));
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.vk.VkIdAuthAnalytics
    public void onVkIdAuthDelegateResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("vkIdAuthDelegateResult", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void onVkIdButtonShown(@NotNull String fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        track("ShowVKIDButton", MapsKt.mapOf(TuplesKt.to("screen", fromScreen)));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordPageError() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "page_error")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordPageFinished() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "page_finish")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordPageStarted() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "page_start")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordRedirectFail() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "redirect_fail")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordRedirectInternalError() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "internal_error")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordRedirectSuccess() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "redirect_success")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordRedirectSuccessTokenIsNull() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "ag_token_null")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordRedirectToRecovery() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "redirect_to_recovery")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordUpdateWebViewClickNegative() {
        track("Upd_VkPassword_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "update_wv_negative")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordUpdateWebViewClickPositive() {
        track("Upd_VkPassword_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "update_wv_positive")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordUpdateWebViewDialogShowed() {
        track("Upd_VkPassword_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "update_wv_show")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordWebViewClose() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "close")));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void onVkPasswordWebViewShowed() {
        track("VkPassword_Event", MapsKt.mapOf(TuplesKt.to("event", "show")));
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void onWebViewError(@NotNull String errorDescription) {
        Intrinsics.checkNotNullParameter(errorDescription, "errorDescription");
        track("LudwigEvent_webview_error", MapsKt.mapOf(TuplesKt.to("error_description", errorDescription)));
    }

    @Override // ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics
    public void onYandexHelpScreenBackBtnClicked() {
        track$default(this, "YandexHelpScreenBackBtnClicked_Action", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics
    public void onYandexHelpScreenGoToLoginClicked() {
        track$default(this, "YandexHelpScreenGoToLoginClicked_Action", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics
    public void onYandexHelpScreenInstructionBtnClicked() {
        track$default(this, "YandexHelpScreenGoToInstructionClicked_Action", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics
    public void onYandexHelpScreenShown() {
        track$default(this, "YandexHelpScreenShown_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void oneTimeCodeActionClick() {
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "click")));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeError(@NotNull String resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "error"), TuplesKt.to("source", resource)));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeFail(@NotNull String resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "fail"), TuplesKt.to("source", resource)));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeSuccess(@NotNull String resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "success"), TuplesKt.to("source", resource)));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeSwitchToPass(@NotNull String resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "switch_to_pass"), TuplesKt.to("source", resource)));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeUpdateWebViewDialogNegative() {
        track("OneTimeCode_UpdateWebViewDialog", MapsKt.mapOf(TuplesKt.to("action", "negative")));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeUpdateWebViewDialogPositive() {
        track("OneTimeCode_UpdateWebViewDialog", MapsKt.mapOf(TuplesKt.to("action", "positive")));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeUpdateWebViewDialogShowed() {
        track("OneTimeCode_UpdateWebViewDialog", MapsKt.mapOf(TuplesKt.to("action", "showed")));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeWebViewClose(@NotNull String resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "webview_close"), TuplesKt.to("source", resource)));
    }

    @Override // ru.mail.authorizationsdk.feature.onetimecode.analytics.OneTimeCodeAnalyticEvents
    public void oneTimeCodeWebViewShowed(@NotNull String resource) {
        Intrinsics.checkNotNullParameter(resource, "resource");
        track("OneTimeCode_Action", MapsKt.mapOf(TuplesKt.to("action", "webview_showed"), TuplesKt.to("source", resource)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void openGoogleAuth() {
        track$default(this, "GoogleAuth_View", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.web.analytics.GoogleWebAnalyticEvents
    public void openGoogleWebAuth() {
        track$default(this, "GoogleWebAuthOpen", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookApiError(int swaStatus) {
        track("OutlookScrApiError", MapsKt.mapOf(TuplesKt.to("code", String.valueOf(swaStatus))));
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookImapFailed() {
        track$default(this, "OutlookScrImapError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookMrimError() {
        track$default(this, "OutlookScrMrimError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookOnOauthRequestEmailEmptyError() {
        track$default(this, "OutlookOauthRequestEmailEmpty_Error", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("OutlookScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookScreenNetworkError() {
        track$default(this, "OutlookScrNetworkError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookScreenOpen() {
        track$default(this, "OutlookScrOpen", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookUpdateWebViewDialogNegative() {
        updWebViewDialogNegative();
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookUpdateWebViewDialogPositive() {
        updWebViewDialogPositive();
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void outlookUpdateWebViewDialogShowed() {
        updWebViewDialogShowed();
    }

    @Override // ru.mail.authorizationsdk.feature.password.presentation.PasswordAnalytics
    public void passwordScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("Password_result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.password.presentation.PasswordAnalytics
    public void passwordScreenSdkShowed(@NotNull String step) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("PassAuth_Sdk_View", MapsKt.mapOf(TuplesKt.to("step", step)));
    }

    @Override // ru.mail.authorizationsdk.feature.password.presentation.PasswordAnalytics
    public void passwordScreenShowed(@NotNull String step) {
        Intrinsics.checkNotNullParameter(step, "step");
        track("PassAuth_View", MapsKt.mapOf(TuplesKt.to("step", step)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneAuthError(@NotNull String screenName, @NotNull Throwable error, @NotNull Map<String, String> supportReport) {
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(supportReport, "supportReport");
        track("PhoneAuthError", MapsKt.plus(supportReport, MapsKt.mapOf(TuplesKt.to("screen", screenName), TuplesKt.to("error", error.getClass().getSimpleName()))));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenAccountCreated() {
        track$default(this, "PhoneInputAccountListScreenAccountCreated", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenClickAccount() {
        track$default(this, "PhoneInputAccountListScreenClickAccount", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenClickAnotherAccount() {
        track$default(this, "PhoneInputAccountListScreenClickAnotherAccount", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenClickCreateAccount() {
        track$default(this, "PhoneInputAccountListScreenClickCreateAccount", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenCountsAccounts(int count) {
        track("PhoneInputAccountListScreenCountsAccounts", MapsKt.mapOf(TuplesKt.to("count", String.valueOf(count))));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenShow() {
        track$default(this, "PhoneInputAccountListScreenShow", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputAccountListScreenWrongState() {
        track$default(this, "PhoneInputAccountListScreenWrongState", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputCreateCloudScreenClickCreateCloud() {
        track$default(this, "PhoneInputCreateCloudScreenClickCreateCloud", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputCreateCloudScreenShow() {
        track$default(this, "PhoneInputCreateCloudScreenShow", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputCreateCloudScreenWrongState() {
        track$default(this, "PhoneInputCreateCloudScreenWrongState", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputNotFoundAccountScreenClickCreateCloud() {
        track$default(this, "PhoneInputNotFoundAccountScreenClickCreateCloud", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputNotFoundAccountScreenClickOtherAccount() {
        track$default(this, "PhoneInputNotFoundAccountScreenClickOtherAccount", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void phoneOnInputNotFoundAccountScreenShow() {
        track$default(this, "PhoneInputNotFoundAccountScreenShow", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void registrationNextClick(@NotNull String regForm) {
        Intrinsics.checkNotNullParameter(regForm, "regForm");
        track("Registration_Action", MapsKt.mapOf(TuplesKt.to("Action", "RegistrationNextClick"), TuplesKt.to("RegForm", regForm)));
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void registrationResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("Reg_Result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordNetworkError() {
        track$default(this, "RestorePasswordScrNetError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("RestorePasswordClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordScreenShowed() {
        track$default(this, "RestorePasswordScrShow", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordSuccessRebind() {
        track$default(this, "RestorePasswordSuccessRebind", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordUpdateWebViewDialogNegative() {
        updWebViewDialogNegative();
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordUpdateWebViewDialogPositive() {
        updWebViewDialogPositive();
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordUpdateWebViewDialogShowed() {
        updWebViewDialogShowed();
    }

    @Override // ru.mail.authorizationsdk.feature.restorepassword.analytics.RestorePasswordAnalytics
    public void restorePasswordWebViewLoadingFailed() {
        track$default(this, "RestorePassScr_webview_loading_failed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.restorevkpassword.analytics.RestoreVkAnalytics
    public void restoreVkResult(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("Restore_Vk_Result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void saveExternalDomainProvider(@NotNull String domain) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        track("ImapLocalSaveExternProvider", MapsKt.mapOf(TuplesKt.to("domain", domain)));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorError(boolean isXmail, boolean isNpcMode) {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "error"), TuplesKt.to("is_xmail", String.valueOf(isXmail)), TuplesKt.to("is_npc", String.valueOf(isNpcMode))));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorFail(boolean isXmail, boolean isNpcMode) {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "fail"), TuplesKt.to("is_xmail", String.valueOf(isXmail)), TuplesKt.to("is_npc", String.valueOf(isNpcMode))));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorSuccess(boolean isXmail, boolean isNpcMode) {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "success"), TuplesKt.to("is_xmail", String.valueOf(isXmail)), TuplesKt.to("is_npc", String.valueOf(isNpcMode))));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorSwitchToPass(boolean isXmail, boolean isNpcMode) {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "switch_to_pass"), TuplesKt.to("is_xmail", String.valueOf(isXmail)), TuplesKt.to("is_npc", String.valueOf(isNpcMode))));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorSwitchToRecovery() {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "switch_to_recovery")));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorUpdateWebViewDialogNegative() {
        track("SecondFact_Upd_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "negative")));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorUpdateWebViewDialogPositive() {
        track("SecondFact_Upd_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "positive")));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorUpdateWebViewDialogShowed() {
        track("SecondFact_Upd_WView_Dial", MapsKt.mapOf(TuplesKt.to("action", "show")));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorWebViewClose(boolean isXmail, boolean isNpcMode) {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "close"), TuplesKt.to("is_xmail", String.valueOf(isXmail)), TuplesKt.to("is_npc", String.valueOf(isNpcMode))));
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorWebViewLoadingFailed() {
        track$default(this, "SecondFact_webview_loading_failed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.secondfactor.analytics.SecondFactorAnalytics
    public void secondFactorWebViewShowed(boolean isXmail, boolean isNpcMode) {
        track("SecondFact_Action", MapsKt.mapOf(TuplesKt.to("action", "show"), TuplesKt.to("is_xmail", String.valueOf(isXmail)), TuplesKt.to("is_npc", String.valueOf(isNpcMode))));
    }

    @Override // ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreAnalytics
    public void sendAnalyticRestoreShown(@NotNull String timeTillShow, @NotNull String restoreType, @NotNull String isRestore, @NotNull String moreThanOne) {
        Intrinsics.checkNotNullParameter(timeTillShow, "timeTillShow");
        Intrinsics.checkNotNullParameter(restoreType, "restoreType");
        Intrinsics.checkNotNullParameter(isRestore, "isRestore");
        Intrinsics.checkNotNullParameter(moreThanOne, "moreThanOne");
        track("RestoreNotificationShown_Action", MapsKt.mapOf(TuplesKt.to("TimeTillNotify", timeTillShow), TuplesKt.to("RestoreType", restoreType), TuplesKt.to("IsRestore", isRestore), TuplesKt.to("HasActiveAcc", moreThanOne)));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void sendBindFlDomainSuggestionFlowAnalytics(@Nullable String flow) {
        track("LoginBindFl_Domain_Suggestion_Flow_Event", MapsKt.mapOf(TuplesKt.to("flow", String.valueOf(flow))));
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.domain.authdelegate.AuthRequestAnalytics
    public void sendCgiBinAuthSecStepFlow(@NotNull String flow) {
        Intrinsics.checkNotNullParameter(flow, "flow");
        track("CgiBinAuthSecStepFlow", MapsKt.mapOf(TuplesKt.to("flow", flow)));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void sendDomainClickedAnalytics(int index) {
        track("Login_Domain_Clicked_Action", MapsKt.mapOf(TuplesKt.to("indexInConfig", String.valueOf(index))));
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void sendDomainSuggestionFlowAnalytics(@Nullable String flow) {
        track("Login_Domain_Suggestion_Flow_Event", MapsKt.mapOf(TuplesKt.to("flow", String.valueOf(flow))));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void sendLoginBindEditStarted() {
        track("Login_Bind_Edit_Started_Event", MapsKt.mapOf(TuplesKt.to("action", "click")));
    }

    @Override // ru.mail.authorizationsdk.feature.loginbindflow.analytics.LoginBindFlowAnalytics
    public void sendLoginBindFlDomainSuggested() {
        track$default(this, "Login_BindFl_Domain_Suggested_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void sendLoginDomainSuggested() {
        track$default(this, "Login_Domain_Suggested_Event", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.login.analytics.LoginAnalytics
    public void sendLoginEditStarted() {
        track$default(this, "Login_Edit_Started_Event", null, 2, null);
    }

    @Override // ru.mail.network.utils.client.interceptor.retry.RequestDurationAnalytics
    public void sendRequestDurationAnalyticsIfEnabled(long durationMilliseconds, @NotNull String requestName) {
        Intrinsics.checkNotNullParameter(requestName, "requestName");
        track("RequestDuration_Event", MapsKt.mapOf(TuplesKt.to("durationMilliseconds", String.valueOf(durationMilliseconds)), TuplesKt.to(Event.Companion.Network.Fail.REQUEST_TAG, requestName)));
    }

    @Override // ru.mail.authorizationsdk.feature.utilfeature.sessionrestore.model.SessionRestoreAnalytics
    public void sendRestoreScheduledAnalytic(@NotNull String timeTillShow, @NotNull String restoreType, @NotNull String isRestore, @NotNull String moreThanOne) {
        Intrinsics.checkNotNullParameter(timeTillShow, "timeTillShow");
        Intrinsics.checkNotNullParameter(restoreType, "restoreType");
        Intrinsics.checkNotNullParameter(isRestore, "isRestore");
        Intrinsics.checkNotNullParameter(moreThanOne, "moreThanOne");
        track("RestoreScheduled_Action", MapsKt.mapOf(TuplesKt.to("TimeTillNotify", timeTillShow), TuplesKt.to("RestoreType", restoreType), TuplesKt.to("IsRestore", isRestore), TuplesKt.to("HasActiveAcc", moreThanOne)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void serviceTypeOnCustomServerScreen(@NotNull String mailService) {
        Intrinsics.checkNotNullParameter(mailService, "mailService");
        track("ServiceTypeOnCustomScreen", MapsKt.mapOf(TuplesKt.to("serviceType", mailService)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void showPassAuth(@NotNull String mailService) {
        Intrinsics.checkNotNullParameter(mailService, "mailService");
        passAuthView(mailService, "1step");
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.CommonAnalytics
    public void showStartScreenOnWrongNav(@NotNull String screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        track("showStartOnWrongNav", MapsKt.mapOf(TuplesKt.to("screen", screen)));
    }

    @Override // ru.mail.authorizationsdk.feature.vkid.screens.vkfragmentsupport.analytics.VkFragmentSupportAnalytics
    public void showVkFastLoginScreen(@NotNull String mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        track("onShowVkFragment", MapsKt.mapOf(TuplesKt.to("mode", mode)));
    }

    public final void socialAuthTrack(@NotNull String name, @Nullable Map<String, String> params) {
        Intrinsics.checkNotNullParameter(name, "name");
        track(name, params);
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void ssoScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("SSOScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.sso.analytics.SSOAnalytics
    public void ssoWebViewLoadingFailed() {
        track$default(this, "SsoScr_webview_loading_failed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.socialauth.analytics.SocialAuthAnalytics
    public void startEmailAuthBySocial(@NotNull String stringToken) {
        Intrinsics.checkNotNullParameter(stringToken, "stringToken");
        track("Start_Email_Auth_By_Social_Event", MapsKt.mapOf(TuplesKt.to("BindType", stringToken)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void startGoogleResolution() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.RESOLUTION_START)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void startGoogleScreen(boolean isAGroup, @NotNull String isXmail) {
        Intrinsics.checkNotNullParameter(isXmail, "isXmail");
        track(isAGroup ? "a_gr_start_google" : "b_gr_start_google", MapsKt.mapOf(TuplesKt.to("is_xmail", isXmail)));
    }

    @Override // ru.mail.authorizationsdk.feature.google.nativelib.analytics.GoogleNativeAnalyticEvents
    public void startGoogleSignInActivity() {
        track("GoogleAuth", MapsKt.mapOf(TuplesKt.to("action", GoogleNativeAnalyticEvents.Companion.GoogleActions.START_SIGN_IN_ACTIVITY)));
    }

    @Override // ru.mail.authorizationsdk.feature.imaplocal.analytics.ImapLocalAnalytics
    public void startImapAuthTry(@NotNull String domain) {
        Intrinsics.checkNotNullParameter(domain, "domain");
        track("ImapLocalTryAuth", MapsKt.mapOf(TuplesKt.to("domain", domain)));
    }

    @Override // ru.mail.authorizationsdk.feature.restorevkpassword.analytics.RestoreVkAnalytics
    public void startRestoreVkScreen() {
        track$default(this, "Restore_Vk_Scr_start", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.google.common.analytics.GoogleAnalytics
    public void unknownGoogleError() {
        track$default(this, "GoogleScrUnknownError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.outlook.analytics.OutlookAnalytics
    public void unknownOutlookError() {
        track$default(this, "OutlookScrUnknownError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void unknownYandexError() {
        track$default(this, "YandexScrUnknownError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void updateWebViewDialogNegativeButtonClicked() {
        track$default(this, "LudwigEvent_webview_upd_dial_negative", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void updateWebViewDialogPositiveButtonClicked() {
        track$default(this, "LudwigEvent_webview_upd_dial_positive", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.captcha.analytics.LudwigCaptchaAnalyticEvents
    public void updateWebViewDialogShowed() {
        track$default(this, "LudwigEvent_webview_upd_dial_show", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void vkPasswordScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("VkPasswordScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.vkpassword.analytics.VkPasswordAnalytics
    public void vkPasswordWebViewLoadingFailed() {
        track$default(this, "VkPassword_webview_loading_failed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.authactivity.AuthActivityAnalytics
    public void wrongSdkWork(@Size(max = 30, min = 0) @NotNull String error, @NotNull String mode) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(mode, "mode");
        track("auth_sdk_wrong_sdk_work", MapsKt.mapOf(TuplesKt.to("error", this.filter.getValue().filter(error)), TuplesKt.to("mode", this.filter.getValue().filter(mode))));
    }

    @Override // ru.mail.authorizationsdk.feature.registration.RegistrationAnalytics
    public void wrongSdkWorkReg(@Size(max = 30, min = 0) @NotNull String error, @NotNull String screen) {
        Intrinsics.checkNotNullParameter(error, "error");
        Intrinsics.checkNotNullParameter(screen, "screen");
        track("reg_sdk_wrong_sdk_work", MapsKt.mapOf(TuplesKt.to("Error", error), TuplesKt.to("Screen", screen)));
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void wrongWork(boolean isAGroup) {
        track$default(this, isAGroup ? "a_gr_wrong" : "b_gr_wrong", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooApiError(int swaStatus) {
        track("YahooScrApiError", MapsKt.mapOf(TuplesKt.to("code", String.valueOf(swaStatus))));
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooImapFailed() {
        track$default(this, "YahooImapFailed", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooMrimError() {
        track$default(this, "YahooScrMrimError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooOnOauthRequestEmailEmptyError() {
        track$default(this, "YahooOauthRequestEmailEmpty_Error", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("YahooScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooScreenNetworkError() {
        track$default(this, "YahooScrNetworkError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooScreenOpen() {
        track$default(this, "YahooScrOpen", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooUpdateWebViewDialogNegative() {
        updWebViewDialogNegative();
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooUpdateWebViewDialogPositive() {
        updWebViewDialogPositive();
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooUpdateWebViewDialogShowed() {
        updWebViewDialogShowed();
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexApiError(int swaStatus) {
        track("YandexScrApiError", MapsKt.mapOf(TuplesKt.to("code", String.valueOf(swaStatus))));
    }

    @Override // ru.mail.authorizationsdk.feature.yandexhelp.analytics.YandexHelpAnalytics
    public void yandexHelpScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("YandexHelpScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexImapFailed() {
        track$default(this, "YandexScrImapError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexMrimError() {
        track$default(this, "YandexScrMrimError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexOnOauthRequestEmailEmptyError() {
        track$default(this, "YandexOauthRequestEmailEmpty_Error", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexOpenScreen() {
        track$default(this, "YandexOpenScreen", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexScreenClosed(@NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track("YandexScrClosed", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexScreenNetworkError() {
        track$default(this, "YandexScrNetworkError", null, 2, null);
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexUpdateWebViewDialogNegative() {
        updWebViewDialogNegative();
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexUpdateWebViewDialogPositive() {
        updWebViewDialogPositive();
    }

    @Override // ru.mail.authorizationsdk.feature.yandex.analytics.YandexAnalytics
    public void yandexUpdateWebViewDialogShowed() {
        updWebViewDialogShowed();
    }

    @Override // ru.mail.authorizationsdk.external.analytics.common.DebugGoogleAnalytics
    public void onSdkResult(boolean isAGroup, @NotNull String result) {
        Intrinsics.checkNotNullParameter(result, "result");
        track(isAGroup ? "a_gr_result" : "b_gr_result", MapsKt.mapOf(TuplesKt.to("result", result)));
    }

    @Override // ru.mail.authorizationsdk.feature.yahoo.analytics.YahooAnalytics
    public void yahooApiError(@NotNull String error) {
        Intrinsics.checkNotNullParameter(error, "error");
        track("YahooApiError", MapsKt.mapOf(TuplesKt.to("Error_Message", error)));
    }

    private final void loginError(String error, String step) {
        track("Login_Error", MapsKt.mapOf(TuplesKt.to("Error", error), TuplesKt.to("step", step)));
    }
}
