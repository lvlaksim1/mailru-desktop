package ru.mail.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.material.color.MaterialColors;
import com.huawei.hms.support.api.entity.core.CommonCode;
import com.vk.auth.main.VkClientAuthLib;
import com.vk.auth.ui.fastlogin.VkFastLoginBottomSheetFragment;
import com.vk.superapp.SuperappKit;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.AnalyticsRegFormHelper;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.auth.BaseToolbarActivity;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.VKAuthenticator;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.request.AuthorizeRequestCommand;
import ru.mail.auth.request.AuthorizeResult;
import ru.mail.auth.request.MigrateToPostConfig;
import ru.mail.auth.util.DomainUtils;
import ru.mail.auth.webview.CustomVKFastLoginViewFragment;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.cloud.app.data.openapi.File;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.credentialsexchanger.FromScreen;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.data.entity.Account;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelperHolder;
import ru.mail.data.cmd.server.RegMailRuCmd;
import ru.mail.locator.Locator;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.R;
import ru.mail.mailbox.cmd.CompleteObserver;
import ru.mail.mailbox.cmd.Schedulers;
import ru.mail.march.interactor.InteractorObtainers;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.march.viewmodel.ViewModelObtainerKt;
import ru.mail.registration.request.SocialAuthKnownFields;
import ru.mail.registration.ui.AccountData;
import ru.mail.registration.ui.ConfirmationActivity;
import ru.mail.registration.ui.CustomProgress;
import ru.mail.registration.ui.DefaultSignUpDelegate;
import ru.mail.registration.ui.ErrorStatus;
import ru.mail.registration.ui.ErrorValue;
import ru.mail.registration.ui.RegistrationMailRuFragment;
import ru.mail.registration.ui.SignUpDelegate;
import ru.mail.social.auth.presentation.UiManagerHolder;
import ru.mail.ui.RequestCode;
import ru.mail.ui.SocialAuthButtonsView;
import ru.mail.ui.auth.universal.SmallLoginButtonView;
import ru.mail.ui.auth.universal.authDesign.AuthDesignFactory;
import ru.mail.ui.auth.universal.esia.EsiaAuthViewModel;
import ru.mail.ui.auth.universal.esia.UserCancelEsiaAuthDelegate;
import ru.mail.ui.auth.universal.registration.LeelooRegistrationViewModel;
import ru.mail.ui.auth.universal.registration.network.GeneratePasswordRequest;
import ru.mail.ui.registration.ConfirmationMailRuActivity;
import ru.mail.ui.registration.MailRuRegistrationActivity;
import ru.mail.ui.registration.RegistrationFragmentsConductor;
import ru.mail.ui.utils.UiExtensionsKt;
import ru.mail.ui.webview.help.HelpNonAuthorizedActivity;
import ru.mail.uikit.view.FontTextView;
import ru.mail.util.BuildVariantHelper;
import ru.mail.util.SakErrorToastShowDelegate;
import ru.mail.util.log.Log;
import ru.mail.util.relocation.LicenseAgreementConfigRepository;
import ru.mail.utils.RandomStringGenerator;
import ru.mail.utils.UtilExtensionsKt;
import ru.mail.widget.RegCheckAutoCompleteTextView;
import ru.mail.widget.RegView;
import ru.mail.widget.RegViewInterface;
import ru.ok.android.utils.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000È\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u0000 É\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005:\u0002É\u0001B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010M\u001a\u00020N2\b\u0010O\u001a\u0004\u0018\u00010PH\u0016J\b\u0010V\u001a\u00020NH\u0014J\b\u0010W\u001a\u00020RH\u0016J\u001c\u0010X\u001a\u00020N2\b\u0010Y\u001a\u0004\u0018\u00010Z2\b\u0010[\u001a\u0004\u0018\u00010ZH\u0016J\u0018\u0010\\\u001a\u00020N2\u000e\u0010]\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010_0^H\u0016J\b\u0010`\u001a\u00020NH\u0016J\b\u0010a\u001a\u00020bH\u0014J\b\u0010c\u001a\u00020NH\u0016J\b\u0010d\u001a\u00020ZH\u0016J&\u0010e\u001a\u0004\u0018\u00010f2\u0006\u0010g\u001a\u00020h2\b\u0010i\u001a\u0004\u0018\u00010j2\b\u0010O\u001a\u0004\u0018\u00010PH\u0016J\u001a\u0010k\u001a\u00020N2\u0006\u0010l\u001a\u00020f2\b\u0010m\u001a\u0004\u0018\u00010PH\u0016J\u0010\u0010n\u001a\u00020N2\u0006\u0010l\u001a\u00020fH\u0002J\u001e\u0010o\u001a\b\u0012\u0004\u0012\u00020Z0p2\u000e\u0010q\u001a\n\u0012\u0004\u0012\u00020Z\u0018\u00010rH\u0014J\u0012\u0010s\u001a\u00020N2\b\u0010t\u001a\u0004\u0018\u00010fH\u0014J\b\u0010u\u001a\u00020vH\u0014J\u0010\u0010w\u001a\u00020N2\u0006\u0010l\u001a\u00020fH\u0002J\u0018\u0010x\u001a\u00020N2\u0006\u0010l\u001a\u00020f2\u0006\u0010G\u001a\u00020yH\u0014J\b\u0010z\u001a\u00020NH\u0002J\r\u0010{\u001a\u00020bH\u0014¢\u0006\u0002\u0010|J\b\u0010}\u001a\u00020NH\u0002J\u0018\u0010~\u001a\u00020N2\u000e\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020Z\u0018\u00010rH\u0014J\u0012\u0010\u0080\u0001\u001a\u00020N2\u0007\u0010\u0081\u0001\u001a\u00020ZH\u0014J\u0011\u0010\u0082\u0001\u001a\u00020N2\u0006\u0010l\u001a\u00020fH\u0002J\t\u0010\u0083\u0001\u001a\u00020NH\u0002J\u0011\u0010\u0084\u0001\u001a\u00020N2\u0006\u0010l\u001a\u00020fH\u0002J\t\u0010\u0085\u0001\u001a\u00020\tH\u0014J\u0014\u0010\u0086\u0001\u001a\u00020N2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010ZH\u0014J\u0012\u0010\u0088\u0001\u001a\u00020N2\u0007\u0010\u0089\u0001\u001a\u00020fH\u0002J\u0011\u0010\u008a\u0001\u001a\u00020N2\u0006\u0010l\u001a\u00020fH\u0002J\t\u0010\u008b\u0001\u001a\u00020NH\u0004J\t\u0010\u008c\u0001\u001a\u00020NH\u0004J\u001d\u0010\u008d\u0001\u001a\u00020N2\u0007\u0010\u008e\u0001\u001a\u00020\t2\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010ZH\u0014J\t\u0010\u0090\u0001\u001a\u00020NH\u0016J\t\u0010\u0091\u0001\u001a\u00020NH\u0016J\t\u0010\u0092\u0001\u001a\u00020\tH\u0014J\u0013\u0010\u0093\u0001\u001a\u00020N2\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0002J\u0010\u0010\u0096\u0001\u001a\u00020Z2\u0007\u0010\u0097\u0001\u001a\u00020ZJ\t\u0010\u0098\u0001\u001a\u00020bH\u0014J\t\u0010\u0099\u0001\u001a\u00020\tH\u0002J\t\u0010\u009a\u0001\u001a\u00020ZH\u0014J\u0013\u0010\u009b\u0001\u001a\u00020N2\b\u0010\u009c\u0001\u001a\u00030\u009d\u0001H\u0014J'\u0010\u009e\u0001\u001a\u00020N2\u0007\u0010\u009f\u0001\u001a\u00020b2\u0007\u0010 \u0001\u001a\u00020b2\n\u0010¡\u0001\u001a\u0005\u0018\u00010\u009d\u0001H\u0016J\t\u0010\u0086\u0001\u001a\u00020NH\u0016J\u001d\u0010¢\u0001\u001a\u00020N2\b\u0010£\u0001\u001a\u00030¤\u00012\b\u0010¥\u0001\u001a\u00030¦\u0001H\u0016J\t\u0010§\u0001\u001a\u00020NH\u0016J\t\u0010¨\u0001\u001a\u00020NH\u0016J\u001c\u0010©\u0001\u001a\u00020N2\b\u0010ª\u0001\u001a\u00030«\u00012\u0007\u0010¬\u0001\u001a\u00020\tH\u0016J$\u0010\u00ad\u0001\u001a\u00020N2\u0019\u0010\u00ad\u0001\u001a\u0014\u0012\u0004\u0012\u00020Z0®\u0001j\t\u0012\u0004\u0012\u00020Z`¯\u0001H\u0016J\u0013\u0010°\u0001\u001a\u00020N2\b\u0010±\u0001\u001a\u00030²\u0001H\u0016J\t\u0010³\u0001\u001a\u00020NH\u0016J\t\u0010´\u0001\u001a\u00020NH\u0016J\u0016\u0010µ\u0001\u001a\u000f\u0012\u0004\u0012\u00020Z\u0012\u0004\u0012\u00020b0¶\u0001H\u0014J$\u0010·\u0001\u001a\u00020N2\u0019\u0010\u00ad\u0001\u001a\u0014\u0012\u0004\u0012\u00020Z0®\u0001j\t\u0012\u0004\u0012\u00020Z`¯\u0001H\u0004J\t\u0010¸\u0001\u001a\u00020\tH\u0014J\t\u0010¹\u0001\u001a\u00020\tH\u0014J;\u0010º\u0001\u001a\u00020N2\b\u0010»\u0001\u001a\u00030¼\u00012\b\u0010½\u0001\u001a\u00030¾\u00012\t\u0010¿\u0001\u001a\u0004\u0018\u00010Z2\b\u0010À\u0001\u001a\u00030Á\u00012\u0007\u0010\u008f\u0001\u001a\u00020ZH\u0016J'\u0010Â\u0001\u001a\u00020N2\u0007\u0010¿\u0001\u001a\u00020Z2\n\u0010»\u0001\u001a\u0005\u0018\u00010²\u00012\u0007\u0010\u008f\u0001\u001a\u00020ZH\u0016J$\u0010Ã\u0001\u001a\u00030¼\u00012\f\b\u0002\u0010»\u0001\u001a\u0005\u0018\u00010¼\u00012\n\b\u0002\u0010½\u0001\u001a\u00030¾\u0001H\u0004J\u0013\u0010Ä\u0001\u001a\u00020N2\b\u0010Å\u0001\u001a\u00030Æ\u0001H\u0016J\u0013\u0010Ç\u0001\u001a\u00020N2\b\u0010Å\u0001\u001a\u00030Æ\u0001H\u0016J\t\u0010È\u0001\u001a\u00020NH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020#8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010(\u001a\u00020)8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001e\u0010.\u001a\u00020/8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R$\u00104\u001a\b\u0012\u0004\u0012\u000206058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001e\u0010;\u001a\u00020<8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001e\u0010A\u001a\u00020B8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\u00020HX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001b\u0010Q\u001a\u00020R8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bU\u0010\u001f\u001a\u0004\bS\u0010T¨\u0006Ê\u0001"}, d2 = {"Lru/mail/ui/fragments/RegistrationLibverifyFragment;", "Lru/mail/ui/fragments/mailbox/RegistrationSafetyVerifyMailRuFragment;", "Lru/mail/ui/fragments/SocialRegistrationContract$View;", "Lru/mail/ui/auth/universal/SmallLoginButtonView$Listener;", "Lru/mail/ui/auth/universal/esia/EsiaAuthViewModel$View;", "Lru/mail/registration/ui/SignUpDelegate$SignupResultReceiver;", "<init>", "()V", "mNeedUseLibverify", "", "presenter", "Lru/mail/ui/fragments/SocialRegistrationContract$Presenter;", "progressDialog", "Lru/mail/registration/ui/CustomProgress;", "vkButtonView", "Lru/mail/ui/SocialAuthButtonsView;", "getVkButtonView", "()Lru/mail/ui/SocialAuthButtonsView;", "setVkButtonView", "(Lru/mail/ui/SocialAuthButtonsView;)V", "isSkipVkcReg", "()Z", "setSkipVkcReg", "(Z)V", "esiaAuthViewModel", "Lru/mail/ui/auth/universal/esia/EsiaAuthViewModel;", "leelooRegistrationViewModel", "Lru/mail/ui/auth/universal/registration/LeelooRegistrationViewModel;", "getLeelooRegistrationViewModel", "()Lru/mail/ui/auth/universal/registration/LeelooRegistrationViewModel;", "leelooRegistrationViewModel$delegate", "Lkotlin/Lazy;", "generateStrongPasswordButton", "Lru/mail/uikit/view/FontTextView;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "getAnalytics", "()Lru/mail/analytics/MailAppAnalytics;", "setAnalytics", "(Lru/mail/analytics/MailAppAnalytics;)V", "userCancelEsiaAuthDelegate", "Lru/mail/ui/auth/universal/esia/UserCancelEsiaAuthDelegate;", "getUserCancelEsiaAuthDelegate", "()Lru/mail/ui/auth/universal/esia/UserCancelEsiaAuthDelegate;", "setUserCancelEsiaAuthDelegate", "(Lru/mail/ui/auth/universal/esia/UserCancelEsiaAuthDelegate;)V", "configurationRepository", "Lru/mail/config/ConfigurationRepository;", "getConfigurationRepository", "()Lru/mail/config/ConfigurationRepository;", "setConfigurationRepository", "(Lru/mail/config/ConfigurationRepository;)V", "licenseAgreementConfigRepository", "Ldagger/Lazy;", "Lru/mail/util/relocation/LicenseAgreementConfigRepository;", "getLicenseAgreementConfigRepository", "()Ldagger/Lazy;", "setLicenseAgreementConfigRepository", "(Ldagger/Lazy;)V", "analyticsRegFromHelper", "Lru/mail/analytics/AnalyticsRegFormHelper;", "getAnalyticsRegFromHelper", "()Lru/mail/analytics/AnalyticsRegFormHelper;", "setAnalyticsRegFromHelper", "(Lru/mail/analytics/AnalyticsRegFormHelper;)V", "errorToastShowDelegate", "Lru/mail/util/SakErrorToastShowDelegate;", "getErrorToastShowDelegate", "()Lru/mail/util/SakErrorToastShowDelegate;", "setErrorToastShowDelegate", "(Lru/mail/util/SakErrorToastShowDelegate;)V", "config", "Lru/mail/config/Configuration;", "getConfig", "()Lru/mail/config/Configuration;", "setConfig", "(Lru/mail/config/Configuration;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "signupDelegate", "Lru/mail/registration/ui/SignUpDelegate;", "getSignupDelegate", "()Lru/mail/registration/ui/SignUpDelegate;", "signupDelegate$delegate", "startCreatingAccount", "createSignUpDelegate", "onSignupOk", "regId", "", "recaptchaSiteKey", "onSignupError", "valueList", "", "Lru/mail/registration/ui/ErrorValue;", "onSignupCancelled", "getLayoutId", "", "onResume", "getVKAuthFlowSource", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onViewCreated", Promotion.ACTION_VIEW, "saveInstanceState", "checkRedesign", "createSuggestsAdapter", "Landroid/widget/ArrayAdapter;", "items", "", "doOnRegFormFocusChange", Logger.METHOD_V, "getFieldFlowAnalytics", "Lru/mail/analytics/AnalyticsRegFormHelper$FieldFlow;", "findViews", "setUpRegistrationExperiments", "Lru/mail/mailapp/DTOConfiguration$Config$RegistrationExperiments;", "setUpGeneratePasswordButton", "getPasswordContainerVisibility", "()Ljava/lang/Integer;", "initIntentExtras", "showAltEmails", "emails", "selectEmail", "selectedEmail", "setUpSpecificViews", "observeStates", "configureToolbar", "isRedesignEnabled", "showError", "error", "configureSocialRegistration", "root", "setWhiteSpaceRemovalListener", "initVKID", "initSocialButtons", "startConfirmationActivity", "isUserStartedVkIdRegistrationWithFullData", "vkToken", "onStop", "onDestroyView", "shouldInitSocialFlow", "replaceUrlSpan", "textView", "Landroid/widget/TextView;", "appendSendMeAdsParam", "uriToAdd", "getLicenseTextResId", "useNewEulaStrings", "getAgreementUrl", "putExtrasInConfirmationIntent", CommonCode.Resolution.HAS_RESOLUTION_FROM_APK, "Landroid/content/Intent;", "onActivityResult", "requestCode", "resultCode", "data", "onAuthDataReady", "eAccount", "Lru/mail/credentialsexchanger/data/entity/Account;", "authorizeResult", "Lru/mail/auth/request/AuthorizeResult;", "showProgress", "hideProgress", "showFragment", "fragment", "Landroidx/fragment/app/Fragment;", "isReplace", "hideFields", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "fillValues", "fieldValues", "Lru/mail/registration/request/SocialAuthKnownFields$KnownFieldValues;", "hideVKConnectButton", "onAnotherWayToLogin", "getFieldsToViewId", "", "showFields", "isVkRegWithFullData", "isVkRegWithOnlyEmail", "openVKIDDoregistration", "knownFields", "Lru/mail/registration/request/SocialAuthKnownFields;", "regFrom", "Lru/mail/ui/fragments/SocialRegistrationContract$Presenter$RegFrom;", "token", "type", "Lru/mail/credentialsexchanger/core/CredentialsExchanger$SocialBindType;", "registerByVKID", "getCurrentSocialAuthFields", "onButtonClicked", "fromScreen", "Lru/mail/credentialsexchanger/FromScreen;", "onButtonShown", "onDestroy", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
@SourceDebugExtension({"SMAP\nRegistrationLibverifyFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RegistrationLibverifyFragment.kt\nru/mail/ui/fragments/RegistrationLibverifyFragment\n+ 2 FragmentViewModelLazy.kt\nandroidx/fragment/app/FragmentViewModelLazyKt\n+ 3 View.kt\nandroidx/core/view/ViewKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 TextView.kt\nandroidx/core/widget/TextViewKt\n+ 6 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 7 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1010:1\n106#2,15:1011\n176#3,2:1026\n1869#4,2:1028\n1617#4,9:1047\n1869#4:1056\n1870#4:1058\n1626#4:1059\n1869#4,2:1060\n1617#4,9:1062\n1869#4:1071\n1870#4:1073\n1626#4:1074\n1869#4,2:1075\n55#5,12:1030\n84#5,3:1042\n13805#6,2:1045\n1#7:1057\n1#7:1072\n*S KotlinDebug\n*F\n+ 1 RegistrationLibverifyFragment.kt\nru/mail/ui/fragments/RegistrationLibverifyFragment\n*L\n124#1:1011,15\n292#1:1026,2\n308#1:1028,2\n783#1:1047,9\n783#1:1056\n783#1:1058\n783#1:1059\n785#1:1060,2\n867#1:1062,9\n867#1:1071\n867#1:1073\n867#1:1074\n869#1:1075,2\n513#1:1030,12\n513#1:1042,3\n645#1:1045,2\n783#1:1057\n867#1:1072\n*E\n"})
public class RegistrationLibverifyFragment extends Hilt_RegistrationLibverifyFragment implements SocialRegistrationContract.View, SmallLoginButtonView.Listener, EsiaAuthViewModel.View, SignUpDelegate.SignupResultReceiver {

    @NotNull
    public static final String EXTRA_NEED_SOCIAL_REGISTRATION = "need_social_reg";

    @NotNull
    public static final String EXTRA_NEED_TOOLBAR = "need_toolbar";

    @NotNull
    public static final String EXTRA_NEED_USE_LIB_VERIFY = "need_use_lib_verify";

    @NotNull
    public static final String EXTRA_SEND_ME_ADS = "send_me_ads";

    @NotNull
    private static final String VKID_REG_FLOW_SOURCE = "mail_registration_flow";

    @NotNull
    private static final String VKID_SIGN_IN_PROMO_TAG = "VKID_SIGN_IN_PROMO_TAG";

    @Inject
    public MailAppAnalytics analytics;

    @Inject
    public AnalyticsRegFormHelper analyticsRegFromHelper;
    protected Configuration config;

    @Inject
    public ConfigurationRepository configurationRepository;

    @Inject
    public SakErrorToastShowDelegate errorToastShowDelegate;

    @Nullable
    private EsiaAuthViewModel esiaAuthViewModel;

    @Nullable
    private FontTextView generateStrongPasswordButton;
    private boolean isSkipVkcReg;

    /* JADX INFO: renamed from: leelooRegistrationViewModel$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy leelooRegistrationViewModel;

    @Inject
    public dagger.Lazy<LicenseAgreementConfigRepository> licenseAgreementConfigRepository;
    private boolean mNeedUseLibverify;

    @Nullable
    private SocialRegistrationContract.Presenter presenter;

    @Nullable
    private CustomProgress progressDialog;

    /* JADX INFO: renamed from: signupDelegate$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy signupDelegate;

    @Inject
    public UserCancelEsiaAuthDelegate userCancelEsiaAuthDelegate;

    @Nullable
    private SocialAuthButtonsView vkButtonView;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("RegistrationLibverifyFragment");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/ui/fragments/RegistrationLibverifyFragment$Companion;", "", "<init>", "()V", "EXTRA_NEED_USE_LIB_VERIFY", "", "EXTRA_SEND_ME_ADS", "EXTRA_NEED_SOCIAL_REGISTRATION", "EXTRA_NEED_TOOLBAR", RegistrationLibverifyFragment.VKID_SIGN_IN_PROMO_TAG, "VKID_REG_FLOW_SOURCE", "LOG", "Lru/mail/util/log/Log;", "newInstance", "Landroidx/fragment/app/Fragment;", "signupToken", "knownFields", "Lru/mail/registration/request/SocialAuthKnownFields;", "vkAccessToken", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ Fragment newInstance$default(Companion companion, String str, SocialAuthKnownFields socialAuthKnownFields, String str2, int i10, Object obj) {
            if ((i10 & 4) != 0) {
                str2 = null;
            }
            return companion.newInstance(str, socialAuthKnownFields, str2);
        }

        @JvmStatic
        @NotNull
        public final Fragment newInstance(@Nullable String signupToken, @Nullable SocialAuthKnownFields knownFields, @Nullable String vkAccessToken) {
            Bundle bundle = new Bundle();
            bundle.putString(MailRuRegistrationActivity.EXTRA_SIGNUP_TOKEN, signupToken);
            bundle.putString(RegistrationMailRuFragment.REG_FRAG_VK_TOKEN_KEY, vkAccessToken);
            bundle.putSerializable(MailRuRegistrationActivity.EXTRA_KNOWN_FIELDS, knownFields);
            RegistrationLibverifyFragment registrationLibverifyFragment = new RegistrationLibverifyFragment();
            registrationLibverifyFragment.setArguments(bundle);
            return registrationLibverifyFragment;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[CredentialsExchanger.SocialBindType.values().length];
            try {
                iArr[CredentialsExchanger.SocialBindType.VK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[CredentialsExchanger.SocialBindType.VK_WITHOUT_BIND.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public RegistrationLibverifyFragment() {
        final Function0<Fragment> function0 = new Function0<Fragment>() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Fragment invoke() {
                return this;
            }
        };
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function0.invoke();
            }
        });
        final Function0 function1 = null;
        this.leelooRegistrationViewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(LeelooRegistrationViewModel.class), new Function0<ViewModelStore>() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                return FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy).getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function2 = function1;
                if (function2 != null && (creationExtras = (CreationExtras) function2.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM8759viewModels$lambda1 = FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM8759viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM8759viewModels$lambda1 : null;
                return hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$special$$inlined$viewModels$default$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelProvider.Factory invoke() {
                ViewModelProvider.Factory defaultViewModelProviderFactory;
                ViewModelStoreOwner viewModelStoreOwnerM8759viewModels$lambda1 = FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM8759viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM8759viewModels$lambda1 : null;
                return (hasDefaultViewModelProviderFactory == null || (defaultViewModelProviderFactory = hasDefaultViewModelProviderFactory.getDefaultViewModelProviderFactory()) == null) ? this.getDefaultViewModelProviderFactory() : defaultViewModelProviderFactory;
            }
        });
        this.signupDelegate = UtilExtensionsKt.lazyUnsafe(new Function0() { // from class: ru.mail.ui.fragments.m
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f98607a.createSignUpDelegate();
            }
        });
    }

    private final void checkRedesign(View view) {
        if (isRedesignEnabled()) {
            Typeface font = ResourcesCompat.getFont(requireContext(), R.font.inter_regular);
            Typeface font2 = ResourcesCompat.getFont(requireContext(), R.font.vk_sans_display_regular);
            Context context = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            int iDpToPx = UiExtensionsKt.dpToPx(16, context);
            RegCheckAutoCompleteTextView regCheckAutoCompleteTextView = (RegCheckAutoCompleteTextView) view.findViewById(R.id.email);
            regCheckAutoCompleteTextView.setDropDownBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_reg_round_suggests));
            regCheckAutoCompleteTextView.setDropDownAnchor(R.id.reg_email);
            ((Spinner) view.findViewById(R.id.domains)).setPopupBackgroundDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.bg_reg_round_suggests));
            ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.form_container);
            viewGroup.setBackground(new ColorDrawable(0));
            Intrinsics.checkNotNull(viewGroup);
            UiExtensionsKt.setMargins$default(viewGroup, iDpToPx, 0, iDpToPx, 0, 10, null);
            Button button = (Button) view.findViewById(R.id.next);
            button.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.white_btn_primary_selector));
            button.setTypeface(font2);
            ViewGroup viewGroup2 = (ViewGroup) view.findViewById(R.id.reg_email);
            Intrinsics.checkNotNull(viewGroup2);
            viewGroup2.setPadding(0, 0, 0, 0);
            UiExtensionsKt.setMargins$default(viewGroup2, iDpToPx, 0, iDpToPx, 0, 10, null);
            View viewFindViewById = view.findViewById(R.id.username);
            TextView textView = viewFindViewById instanceof TextView ? (TextView) viewFindViewById : null;
            if (textView != null) {
                textView.setTypeface(font);
            }
            View viewFindViewById2 = view.findViewById(R.id.last_name);
            TextView textView2 = viewFindViewById2 instanceof TextView ? (TextView) viewFindViewById2 : null;
            if (textView2 != null) {
                textView2.setTypeface(font);
            }
            View viewFindViewById3 = view.findViewById(R.id.birth);
            TextView textView3 = viewFindViewById3 instanceof TextView ? (TextView) viewFindViewById3 : null;
            if (textView3 != null) {
                textView3.setTypeface(font);
            }
            View viewFindViewById4 = view.findViewById(R.id.email);
            TextView textView4 = viewFindViewById4 instanceof TextView ? (TextView) viewFindViewById4 : null;
            if (textView4 != null) {
                textView4.setTypeface(font);
            }
            View viewFindViewById5 = view.findViewById(R.id.password);
            TextView textView5 = viewFindViewById5 instanceof TextView ? (TextView) viewFindViewById5 : null;
            if (textView5 != null) {
                textView5.setTypeface(font);
            }
            View viewFindViewById6 = view.findViewById(R.id.agreement_text);
            TextView textView6 = viewFindViewById6 instanceof TextView ? (TextView) viewFindViewById6 : null;
            if (textView6 != null) {
                textView6.setTypeface(font);
            }
            View viewFindViewById7 = view.findViewById(R.id.first_name_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(...)");
            View viewFindViewById8 = view.findViewById(R.id.second_name_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(...)");
            View viewFindViewById9 = view.findViewById(R.id.birthday_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(...)");
            View viewFindViewById10 = view.findViewById(R.id.gender_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(...)");
            View viewFindViewById11 = view.findViewById(R.id.reg_email);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "findViewById(...)");
            View viewFindViewById12 = view.findViewById(R.id.password_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "findViewById(...)");
            for (View view2 : CollectionsKt.listOf((Object[]) new View[]{viewFindViewById7, viewFindViewById8, viewFindViewById9, viewFindViewById10, viewFindViewById11, viewFindViewById12})) {
                Context context2 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                UiExtensionsKt.setMargins$default(view2, 0, 0, 0, UiExtensionsKt.dpToPx(12, context2), 7, null);
            }
        }
    }

    private final void configureSocialRegistration(View root) {
        this.vkButtonView = (SocialAuthButtonsView) root.findViewById(R.id.vkAuthButtonViewReg);
        SocialRegistrationInteractor socialRegistrationInteractor = (SocialRegistrationInteractor) InteractorObtainers.INSTANCE.from(this).obtain(SocialRegistrationInteractor.class, new Function0() { // from class: ru.mail.ui.fragments.n
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RegistrationLibverifyFragment.configureSocialRegistration$lambda$0(this.f100015a);
            }
        });
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = Bundle.EMPTY;
        }
        Bundle bundle = arguments;
        Intrinsics.checkNotNull(bundle);
        CommonDataManager commonDataManagerFrom = CommonDataManager.from(getThemedContext());
        Intrinsics.checkNotNullExpressionValue(commonDataManagerFrom, "from(...)");
        AccountManagerWrapper accountManagerWrapper = Authenticator.getAccountManagerWrapper(getThemedContext());
        Intrinsics.checkNotNullExpressionValue(accountManagerWrapper, "getAccountManagerWrapper(...)");
        Configuration.SocialLoginConfig socialLoginConfig = getConfig().getSocialLoginConfig();
        KeyEventDispatcher.Component activity = getActivity();
        RestoreVkEmailHelperHolder restoreVkEmailHelperHolder = activity instanceof RestoreVkEmailHelperHolder ? (RestoreVkEmailHelperHolder) activity : null;
        this.presenter = new SocialRegistrationPresenter(socialRegistrationInteractor, this, bundle, commonDataManagerFrom, accountManagerWrapper, socialLoginConfig, restoreVkEmailHelperHolder != null ? restoreVkEmailHelperHolder.getRestoreVkEmailHelper() : null);
        if (shouldInitSocialFlow()) {
            initVKID();
            if (getConfig().getSocialLoginConfig().isVKConnectSignupEnabled() || getConfig().getEsiaConfig().getEnabledRegistration()) {
                initSocialButtons();
            }
        }
        SocialRegistrationContract.Presenter presenter = this.presenter;
        if (presenter != null) {
            presenter.onCreate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SocialRegistrationInteractor configureSocialRegistration$lambda$0(RegistrationLibverifyFragment registrationLibverifyFragment) {
        Context contextRequireContext = registrationLibverifyFragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        MigrateToPostConfig migrateToPostConfig = AuthenticatorConfig.getInstance().getMigrateToPostConfig();
        Intrinsics.checkNotNullExpressionValue(migrateToPostConfig, "getMigrateToPostConfig(...)");
        return new SocialRegistrationInteractor(contextRequireContext, migrateToPostConfig);
    }

    private final void configureToolbar(View view) {
        Drawable drawableMutate;
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (isRedesignEnabled()) {
                toolbar.setTitleTextColor(MaterialColors.getColor(toolbar, R.attr.vkuiColorTextPrimary));
                Resources resources = getResources();
                FragmentActivity activity = getActivity();
                drawableMutate = ResourcesCompat.getDrawable(resources, R.drawable.ic_left, activity != null ? activity.getTheme() : null);
            } else {
                Drawable navigationIcon = toolbar.getNavigationIcon();
                if (navigationIcon == null || (drawableMutate = navigationIcon.mutate()) == null) {
                    drawableMutate = null;
                } else {
                    DrawableCompat.setTint(drawableMutate, ResourcesCompat.getColor(getResources(), R.color.action_bar_text, requireActivity().getTheme()));
                }
            }
            toolbar.setNavigationIcon(drawableMutate);
            FragmentActivity activity2 = getActivity();
            BaseToolbarActivity baseToolbarActivity = activity2 instanceof BaseToolbarActivity ? (BaseToolbarActivity) activity2 : null;
            if (baseToolbarActivity != null) {
                baseToolbarActivity.setSupportActionBar(toolbar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean doOnRegFormFocusChange$lambda$0(View view, RegistrationLibverifyFragment registrationLibverifyFragment) {
        switch (view.getId()) {
            case R.id.birth /* 2131362254 */:
                return registrationLibverifyFragment.getBirthday().getTimeInMillis() == 0;
            case R.id.email /* 2131363013 */:
                String login = registrationLibverifyFragment.getLogin();
                Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
                return login.length() == 0;
            case R.id.gender_radio_group /* 2131363410 */:
                return registrationLibverifyFragment.getSex() == null;
            case R.id.last_name /* 2131363712 */:
                CharSequence text = registrationLibverifyFragment.getSecondName().getText();
                Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
                return text.length() == 0;
            case R.id.password /* 2131364353 */:
                String password = registrationLibverifyFragment.getPassword();
                Intrinsics.checkNotNullExpressionValue(password, "getPassword(...)");
                return password.length() == 0;
            case R.id.username /* 2131365666 */:
                CharSequence text2 = registrationLibverifyFragment.getFirstName().getText();
                Intrinsics.checkNotNullExpressionValue(text2, "getText(...)");
                return text2.length() == 0;
            default:
                return true;
        }
    }

    private final void findViews(View view) {
        this.generateStrongPasswordButton = (FontTextView) view.findViewById(R.id.generate_strong_password);
    }

    public static /* synthetic */ SocialAuthKnownFields getCurrentSocialAuthFields$default(RegistrationLibverifyFragment registrationLibverifyFragment, SocialAuthKnownFields socialAuthKnownFields, SocialRegistrationContract.Presenter.RegFrom regFrom, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCurrentSocialAuthFields");
        }
        if ((i10 & 1) != 0) {
            socialAuthKnownFields = null;
        }
        if ((i10 & 2) != 0) {
            regFrom = SocialRegistrationContract.Presenter.RegFrom.DEFAULT;
        }
        return registrationLibverifyFragment.getCurrentSocialAuthFields(socialAuthKnownFields, regFrom);
    }

    private final LeelooRegistrationViewModel getLeelooRegistrationViewModel() {
        return (LeelooRegistrationViewModel) this.leelooRegistrationViewModel.getValue();
    }

    private final SignUpDelegate getSignupDelegate() {
        return (SignUpDelegate) this.signupDelegate.getValue();
    }

    private final void initIntentExtras() {
        Intent intent;
        Bundle extras;
        FragmentActivity activity = getActivity();
        if (activity == null || (intent = activity.getIntent()) == null || (extras = intent.getExtras()) == null) {
            return;
        }
        String string = extras.getString(MailAccountConstants.EXTRA_EMAIL_FOR_SIGNUP, "");
        Intrinsics.checkNotNull(string);
        if (StringsKt.isBlank(string)) {
            return;
        }
        getLoginView().setText(DomainUtils.getLogin(string));
        setDomain("@" + DomainUtils.getDomain(string));
    }

    @JvmStatic
    @NotNull
    public static final Fragment newInstance(@Nullable String str, @Nullable SocialAuthKnownFields socialAuthKnownFields, @Nullable String str2) {
        return INSTANCE.newInstance(str, socialAuthKnownFields, str2);
    }

    private final void observeStates() {
        ExtensionsKt.collectOnLifecycle(this, getLeelooRegistrationViewModel().getGeneratePasswordState(), new Function1() { // from class: ru.mail.ui.fragments.o
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RegistrationLibverifyFragment.observeStates$lambda$0(this.f100016a, (String) obj);
            }
        });
        ExtensionsKt.collectOnLifecycle(this, getLeelooRegistrationViewModel().getToastState(), new Function1() { // from class: ru.mail.ui.fragments.p
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return RegistrationLibverifyFragment.observeStates$lambda$1(this.f100017a, (GeneratePasswordRequest.GeneratePasswordErrors) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit observeStates$lambda$0(RegistrationLibverifyFragment registrationLibverifyFragment, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!StringsKt.isBlank(it)) {
            registrationLibverifyFragment.mPassword.setText(it);
            EditText editText = registrationLibverifyFragment.mPassword;
            editText.setSelection(editText.length());
            registrationLibverifyFragment.mIconShowPassword.setChecked(true);
        }
        FontTextView fontTextView = registrationLibverifyFragment.generateStrongPasswordButton;
        if (fontTextView != null) {
            fontTextView.setEnabled(true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit observeStates$lambda$1(RegistrationLibverifyFragment registrationLibverifyFragment, GeneratePasswordRequest.GeneratePasswordErrors it) {
        Intrinsics.checkNotNullParameter(it, "it");
        registrationLibverifyFragment.showError(registrationLibverifyFragment.getString(R.string.network_error));
        FontTextView fontTextView = registrationLibverifyFragment.generateStrongPasswordButton;
        if (fontTextView != null) {
            fontTextView.setEnabled(true);
        }
        return Unit.INSTANCE;
    }

    private final void replaceUrlSpan(TextView textView) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(textView.getText());
        Object[] spans = spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), URLSpan.class);
        Intrinsics.checkNotNullExpressionValue(spans, "getSpans(...)");
        for (Object obj : spans) {
            final URLSpan uRLSpan = (URLSpan) obj;
            int spanStart = spannableStringBuilder.getSpanStart(uRLSpan);
            int spanEnd = spannableStringBuilder.getSpanEnd(uRLSpan);
            spannableStringBuilder.removeSpan(uRLSpan);
            final String url = uRLSpan.getURL();
            spannableStringBuilder.setSpan(new URLSpan(url) { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$replaceUrlSpan$1$1$1
                @Override // android.text.style.URLSpan, android.text.style.ClickableSpan
                public void onClick(View widget) {
                    Intrinsics.checkNotNullParameter(widget, "widget");
                    FragmentActivity activity = this.this$0.getActivity();
                    if (activity != null) {
                        RegistrationLibverifyFragment registrationLibverifyFragment = this.this$0;
                        URLSpan uRLSpan2 = uRLSpan;
                        Intent intent = new Intent(activity, (Class<?>) HelpNonAuthorizedActivity.class);
                        String url2 = uRLSpan2.getURL();
                        Intrinsics.checkNotNullExpressionValue(url2, "getURL(...)");
                        intent.putExtra("extra_url", registrationLibverifyFragment.appendSendMeAdsParam(url2));
                        registrationLibverifyFragment.startActivityForResult(intent, RequestCode.OPEN_HELP_SCREEN_WEBVIEW.id());
                    }
                }
            }, spanStart, spanEnd, 0);
        }
        textView.setText(spannableStringBuilder);
    }

    private final void setUpGeneratePasswordButton() {
        int iIntValue = getPasswordContainerVisibility().intValue();
        boolean generatePasswordEnabled = getConfigurationRepository().getConfiguration().getRegistrationExpsConfig().getGeneratePasswordEnabled();
        if (!generatePasswordEnabled || iIntValue != 0) {
            LOG.d(this + ". Generate password button is on the screen = " + (this.generateStrongPasswordButton != null) + ". Generate password enabled in config = " + generatePasswordEnabled + ". Password container visibility = " + iIntValue + ".");
            return;
        }
        FontTextView fontTextView = this.generateStrongPasswordButton;
        if (fontTextView != null) {
            fontTextView.setVisibility(0);
        }
        if (this.generateStrongPasswordButton != null) {
            getAnalytics().onGeneratePasswordButtonShown();
        }
        LOG.d(this + ". Generate password button is on the screen = " + (this.generateStrongPasswordButton != null) + ". Generate password enabled in config = " + generatePasswordEnabled + ". Password container visibility = " + iIntValue + ".");
        FontTextView fontTextView2 = this.generateStrongPasswordButton;
        if (fontTextView2 != null) {
            fontTextView2.setOnClickListener(new View.OnClickListener() { // from class: ru.mail.ui.fragments.s
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RegistrationLibverifyFragment.setUpGeneratePasswordButton$lambda$0(this.f100045a, view);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUpGeneratePasswordButton$lambda$0(RegistrationLibverifyFragment registrationLibverifyFragment, View view) {
        FontTextView fontTextView = registrationLibverifyFragment.generateStrongPasswordButton;
        if (fontTextView != null) {
            fontTextView.setEnabled(false);
        }
        String string = registrationLibverifyFragment.getResources().getConfiguration().locale.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        registrationLibverifyFragment.getLeelooRegistrationViewModel().onGeneratePasswordClicked(string);
        registrationLibverifyFragment.getAnalytics().onGeneratePasswordButtonClicked();
        LOG.d("Generate password button clicked. Current locale = " + string);
    }

    private final void setUpSpecificViews(View view) {
        Bundle arguments = getArguments();
        if (arguments != null ? arguments.getBoolean(EXTRA_NEED_TOOLBAR, true) : true) {
            configureToolbar(view);
        }
        Bundle arguments2 = getArguments();
        if (arguments2 != null ? arguments2.getBoolean(EXTRA_NEED_SOCIAL_REGISTRATION, true) : true) {
            configureSocialRegistration(view);
        }
        if (getConfig().isEmailWhiteSpaceProhibitionEnabled()) {
            setWhiteSpaceRemovalListener(view);
        }
    }

    private final void setWhiteSpaceRemovalListener(View view) {
        final RegCheckAutoCompleteTextView regCheckAutoCompleteTextView = (RegCheckAutoCompleteTextView) view.findViewById(R.id.email);
        Intrinsics.checkNotNull(regCheckAutoCompleteTextView);
        regCheckAutoCompleteTextView.addTextChangedListener(new TextWatcher() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment$setWhiteSpaceRemovalListener$$inlined$addTextChangedListener$default$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s10) {
                String strReplace$default = StringsKt.replace$default(String.valueOf(s10), StringUtils.SPACE, "", false, 4, (Object) null);
                if (Intrinsics.areEqual(String.valueOf(s10), strReplace$default)) {
                    return;
                }
                regCheckAutoCompleteTextView.setText(strReplace$default);
                regCheckAutoCompleteTextView.setSelection(strReplace$default.length());
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void showFragment$lambda$0(boolean z10, FragmentActivity fragmentActivity, Fragment fragment) {
        if (z10) {
            ((RegistrationFragmentsConductor) fragmentActivity).replaceFragment(fragment);
        } else {
            ((RegistrationFragmentsConductor) fragmentActivity).addFragment(fragment);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startConfirmationActivity$lambda$0(RegistrationLibverifyFragment registrationLibverifyFragment) {
        registrationLibverifyFragment.onAnotherWayToLogin();
        return Unit.INSTANCE;
    }

    private final boolean useNewEulaStrings() {
        return getConfig().getUseNewEulaStrings();
    }

    @NotNull
    public final String appendSendMeAdsParam(@NotNull String uriToAdd) {
        Intrinsics.checkNotNullParameter(uriToAdd, "uriToAdd");
        String string = Uri.parse(uriToAdd).buildUpon().appendQueryParameter("signupid", RandomStringGenerator.generateString(12)).appendQueryParameter("sent_me_ads", String.valueOf(getAccountData().isSendMeAds())).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @NotNull
    public SignUpDelegate createSignUpDelegate() {
        MigrateToPostConfig migrateToPostConfig = AuthenticatorConfig.getInstance().getMigrateToPostConfig();
        Intrinsics.checkNotNullExpressionValue(migrateToPostConfig, "getMigrateToPostConfig(...)");
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        return new DefaultSignUpDelegate(fragmentActivityRequireActivity, this, getAccountData(), migrateToPostConfig);
    }

    @Override // ru.mail.registration.ui.AbstractRegistrationFragment
    @NotNull
    protected ArrayAdapter<String> createSuggestsAdapter(@Nullable List<String> items) {
        Context contextRequireContext = requireContext();
        if (items == null) {
            items = CollectionsKt.emptyList();
        }
        return new ArrayAdapter<>(contextRequireContext, R.layout.reg_email_dropdown_item, R.id.text, items);
    }

    @Override // ru.mail.registration.ui.AbstractRegistrationFragment
    protected void doOnRegFormFocusChange(@Nullable final View v10) {
        if (v10 == null || !getConfig().isRegFormAnalyticsEnabled()) {
            return;
        }
        AnalyticsRegFormHelper analyticsRegFromHelper = getAnalyticsRegFromHelper();
        int id2 = v10.getId();
        String login = getLogin();
        Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
        analyticsRegFromHelper.onFocusChange(id2, login, getFieldFlowAnalytics(), new Function0() { // from class: ru.mail.ui.fragments.r
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(RegistrationLibverifyFragment.doOnRegFormFocusChange$lambda$0(v10, this));
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0079  */
    public void fillValues(@NotNull SocialAuthKnownFields.KnownFieldValues fieldValues) {
        String gender;
        Intrinsics.checkNotNullParameter(fieldValues, "fieldValues");
        String firstName = fieldValues.getFirstName();
        if (firstName != null && firstName.length() != 0) {
            getFirstName().setText(fieldValues.getFirstName());
        }
        String lastName = fieldValues.getLastName();
        if (lastName != null && lastName.length() != 0) {
            getSecondName().setText(fieldValues.getLastName());
        }
        String gender2 = fieldValues.getGender();
        if (gender2 != null && gender2.length() != 0 && (gender = fieldValues.getGender()) != null) {
            int iHashCode = gender.hashCode();
            if (iHashCode != 102) {
                if (iHashCode != 109) {
                    if (iHashCode == 119 && gender.equals("w")) {
                        setSex(AccountData.Sex.WOMAN);
                    }
                } else if (gender.equals("m")) {
                    setSex(AccountData.Sex.MAN);
                }
            } else if (gender.equals(File.TYPE_FILE)) {
                setSex(AccountData.Sex.WOMAN);
            }
        }
        if (!Intrinsics.areEqual(fieldValues.getBirthday(), new SocialAuthKnownFields.Birthday(0, 0, 0, 7, null))) {
            setBirthday(new GregorianCalendar(fieldValues.getBirthday().getYear(), fieldValues.getBirthday().getMonth() - 1, fieldValues.getBirthday().getDay()));
        }
        CredentialsExchanger.SocialBindType bindType = SocialLoginInfoHolder.getBindType();
        int i10 = bindType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[bindType.ordinal()];
        if (i10 == 1) {
            getAnalytics().onRegFilledVkIdData();
            return;
        }
        if (i10 != 2) {
            return;
        }
        getRegistrationTerms().setVisibility(0);
        TextView registrationTerms = getRegistrationTerms();
        Context resworbkvmocaf = getThemedContext();
        registrationTerms.setText(resworbkvmocaf != null ? resworbkvmocaf.getString(R.string.vk_id_signup_autocomplete_data_terms) : null);
        getAnalytics().onRegFilledVkIdData();
    }

    @Override // ru.mail.registration.ui.AbstractRegistrationFragment
    @NotNull
    protected String getAgreementUrl() {
        String agreementUrl = getLicenseAgreementConfigRepository().get().getAgreementUrl();
        if (!TextUtils.isEmpty(agreementUrl)) {
            return agreementUrl;
        }
        String agreementUrl2 = super.getAgreementUrl();
        Intrinsics.checkNotNullExpressionValue(agreementUrl2, "getAgreementUrl(...)");
        return agreementUrl2;
    }

    @NotNull
    public final MailAppAnalytics getAnalytics() {
        MailAppAnalytics mailAppAnalytics = this.analytics;
        if (mailAppAnalytics != null) {
            return mailAppAnalytics;
        }
        Intrinsics.throwUninitializedPropertyAccessException("analytics");
        return null;
    }

    @NotNull
    public final AnalyticsRegFormHelper getAnalyticsRegFromHelper() {
        AnalyticsRegFormHelper analyticsRegFormHelper = this.analyticsRegFromHelper;
        if (analyticsRegFormHelper != null) {
            return analyticsRegFormHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("analyticsRegFromHelper");
        return null;
    }

    @NotNull
    protected final Configuration getConfig() {
        Configuration configuration = this.config;
        if (configuration != null) {
            return configuration;
        }
        Intrinsics.throwUninitializedPropertyAccessException("config");
        return null;
    }

    @NotNull
    public final ConfigurationRepository getConfigurationRepository() {
        ConfigurationRepository configurationRepository = this.configurationRepository;
        if (configurationRepository != null) {
            return configurationRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("configurationRepository");
        return null;
    }

    @NotNull
    protected final SocialAuthKnownFields getCurrentSocialAuthFields(@Nullable SocialAuthKnownFields knownFields, @NotNull SocialRegistrationContract.Presenter.RegFrom regFrom) {
        String stringValue;
        Intrinsics.checkNotNullParameter(regFrom, "regFrom");
        if (knownFields != null && regFrom != SocialRegistrationContract.Presenter.RegFrom.NEXT_BTN) {
            return new SocialAuthKnownFields(CollectionsKt.arrayListOf("phone"), new SocialAuthKnownFields.KnownFieldValues(knownFields.getFieldValues().getFirstName(), knownFields.getFieldValues().getLastName(), knownFields.getFieldValues().getBirthday(), knownFields.getFieldValues().getGender(), getLogin(), getDomain()));
        }
        String string = getFirstName().getText().toString();
        String string2 = getSecondName().getText().toString();
        SocialAuthKnownFields.Birthday birthday = new SocialAuthKnownFields.Birthday(getBirthday().get(5), getBirthday().get(2) + 1, getBirthday().get(1));
        AccountData.Sex sex = getSex();
        return new SocialAuthKnownFields(CollectionsKt.arrayListOf("phone"), new SocialAuthKnownFields.KnownFieldValues(string, string2, birthday, (sex == null || (stringValue = sex.getStringValue()) == null) ? null : String.valueOf(stringValue.charAt(0)), getLogin(), getDomain()));
    }

    @NotNull
    public final SakErrorToastShowDelegate getErrorToastShowDelegate() {
        SakErrorToastShowDelegate sakErrorToastShowDelegate = this.errorToastShowDelegate;
        if (sakErrorToastShowDelegate != null) {
            return sakErrorToastShowDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("errorToastShowDelegate");
        return null;
    }

    @NotNull
    protected AnalyticsRegFormHelper.FieldFlow getFieldFlowAnalytics() {
        return AnalyticsRegFormHelper.FieldFlow.ADULT;
    }

    @NotNull
    protected Map<String, Integer> getFieldsToViewId() {
        return MapsKt.mapOf(TuplesKt.to("first_name", Integer.valueOf(R.id.first_name_container)), TuplesKt.to("last_name", Integer.valueOf(R.id.second_name_container)), TuplesKt.to("birthday", Integer.valueOf(R.id.birthday_container)), TuplesKt.to("gender", Integer.valueOf(R.id.gender_container)), TuplesKt.to("password", Integer.valueOf(R.id.password_container)), TuplesKt.to(SocialAuthKnownFields.GENERATE_PASSWORD, Integer.valueOf(R.id.generate_strong_password)), TuplesKt.to("email", Integer.valueOf(R.id.reg_email)));
    }

    @Override // ru.mail.registration.ui.AbstractRegistrationFragment
    protected int getLayoutId() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        return new AuthDesignFactory(fragmentActivityRequireActivity).getRegistrationActivityDesign().getRegCreateAccountFragmentLayout();
    }

    @NotNull
    public final dagger.Lazy<LicenseAgreementConfigRepository> getLicenseAgreementConfigRepository() {
        dagger.Lazy<LicenseAgreementConfigRepository> lazy = this.licenseAgreementConfigRepository;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("licenseAgreementConfigRepository");
        return null;
    }

    @Override // ru.mail.registration.ui.AbstractRegistrationFragment
    protected int getLicenseTextResId() {
        return useNewEulaStrings() ? R.string.signup_finish_lisence_agreement_text_new : super.getLicenseTextResId();
    }

    @Override // ru.mail.registration.ui.RegistrationMailRuFragment
    @NotNull
    protected Integer getPasswordContainerVisibility() {
        LinearLayout linearLayout;
        View view = getView();
        return Integer.valueOf((view == null || (linearLayout = (LinearLayout) view.findViewById(R.id.password_container)) == null) ? 8 : linearLayout.getVisibility());
    }

    @NotNull
    public final UserCancelEsiaAuthDelegate getUserCancelEsiaAuthDelegate() {
        UserCancelEsiaAuthDelegate userCancelEsiaAuthDelegate = this.userCancelEsiaAuthDelegate;
        if (userCancelEsiaAuthDelegate != null) {
            return userCancelEsiaAuthDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("userCancelEsiaAuthDelegate");
        return null;
    }

    @NotNull
    public String getVKAuthFlowSource() {
        return VKID_REG_FLOW_SOURCE;
    }

    @Nullable
    protected final SocialAuthButtonsView getVkButtonView() {
        return this.vkButtonView;
    }

    @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
    public void hideFields(@NotNull ArrayList<String> hideFields) {
        View viewFindViewById;
        Intrinsics.checkNotNullParameter(hideFields, "hideFields");
        Map<String, Integer> fieldsToViewId = getFieldsToViewId();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = hideFields.iterator();
        while (it.hasNext()) {
            Integer num = fieldsToViewId.get((String) it.next());
            if (num != null) {
                arrayList.add(num);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            int iIntValue = ((Number) it2.next()).intValue();
            View view = getView();
            if (view != null && (viewFindViewById = view.findViewById(iIntValue)) != null) {
                viewFindViewById.setVisibility(8);
            }
        }
    }

    @Override // ru.mail.ui.fragments.SocialRegistrationContract.View, ru.mail.ui.auth.universal.esia.EsiaAuthViewModel.View
    public void hideProgress() {
        CustomProgress customProgress = this.progressDialog;
        if (customProgress != null) {
            customProgress.dismiss();
        }
    }

    @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
    public void hideVKConnectButton() {
        SocialAuthButtonsView socialAuthButtonsView = this.vkButtonView;
        if (socialAuthButtonsView != null) {
            socialAuthButtonsView.setVisibility(8);
        }
    }

    protected final void initSocialButtons() {
        Configuration.SocialLoginConfig socialLoginConfig;
        Configuration.SocialLoginConfig socialLoginConfig2 = getConfig().getSocialLoginConfig();
        DTOConfiguration.Config.EsiaConfig esiaConfig = getConfig().getEsiaConfig();
        SocialAuthButtonsView socialAuthButtonsView = this.vkButtonView;
        if (socialAuthButtonsView != null) {
            socialLoginConfig = socialLoginConfig2;
            socialAuthButtonsView.initVkView((29472 & 1) != 0 ? false : socialLoginConfig2.isVKConnectSignupEnabled(), (29472 & 2) != 0 ? false : SuperappKit.isInitialized(), (29472 & 4) != 0 ? false : socialLoginConfig2.isOneTapEnabled(), (29472 & 8) != 0 ? FromScreen.LOGIN : FromScreen.REGISTRATION, (29472 & 16) != 0 ? null : getAnalytics(), (29472 & 32) != 0 ? false : false, (29472 & 64) != 0 ? false : esiaConfig.getEnabledRegistration(), (29472 & 128) != 0 ? null : this, (29472 & 256) != 0 ? false : false, (29472 & 512) != 0 ? null : null, (29472 & 1024) != 0 ? false : isRedesignEnabled(), false, (29472 & 4096) != 0 ? 
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004f: INVOKE 
                  (r0v2 'socialAuthButtonsView' ru.mail.ui.SocialAuthButtonsView)
                  (wrap boolean:?: TERNARY null = ((wrap int:0x0002: ARITH (29472 int) & (1 int) A[WRAPPED]) != (0 int)) ? false : (wrap boolean:0x0018: INVOKE (r0v1 'socialLoginConfig2' ru.mail.config.Configuration$SocialLoginConfig) VIRTUAL call: ru.mail.config.Configuration.SocialLoginConfig.isVKConnectSignupEnabled():boolean A[MD:():boolean (m), WRAPPED] (LINE:25)))
                  (wrap boolean:?: TERNARY null = ((wrap int:0x000b: ARITH (29472 int) & (2 int) A[WRAPPED]) != (0 int)) ? false : (wrap boolean:0x001d: INVOKE  STATIC call: com.vk.superapp.SuperappKit.isInitialized():boolean A[MD:():boolean (m), WRAPPED] (LINE:30)))
                  (wrap boolean:?: TERNARY null = ((wrap int:0x0013: ARITH (29472 int) & (4 int) A[WRAPPED]) != (0 int)) ? false : (wrap boolean:0x0022: INVOKE (r0v1 'socialLoginConfig2' ru.mail.config.Configuration$SocialLoginConfig) VIRTUAL call: ru.mail.config.Configuration.SocialLoginConfig.isOneTapEnabled():boolean A[MD:():boolean (m), WRAPPED] (LINE:35)))
                  (wrap ru.mail.credentialsexchanger.FromScreen:?: TERNARY null = ((wrap int:0x001b: ARITH (29472 int) & (8 int) A[WRAPPED]) != (0 int)) ? (wrap ??:0x0021: SGET  A[WRAPPED] (LINE:1) ru.mail.credentialsexchanger.FromScreen.LOGIN ru.mail.credentialsexchanger.FromScreen) : (wrap ru.mail.credentialsexchanger.FromScreen:0x0027: SGET  A[WRAPPED] (LINE:40) ru.mail.credentialsexchanger.FromScreen.REGISTRATION ru.mail.credentialsexchanger.FromScreen))
                  (wrap ru.mail.analytics.MailAppAnalytics:?: TERNARY null = ((wrap int:0x0025: ARITH (29472 int) & (16 int) A[WRAPPED]) != (0 int)) ? (null ru.mail.analytics.MailAppAnalytics) : (wrap ru.mail.analytics.MailAppAnalytics:0x002a: INVOKE (r21v0 'this' ru.mail.ui.fragments.RegistrationLibverifyFragment A[IMMUTABLE_TYPE, THIS]) VIRTUAL call: ru.mail.ui.fragments.RegistrationLibverifyFragment.getAnalytics():ru.mail.analytics.MailAppAnalytics A[MD:():ru.mail.analytics.MailAppAnalytics (m), WRAPPED] (LINE:43)))
                  (wrap boolean:?: TERNARY null = ((wrap int:0x002e: ARITH (29472 int) & (32 int) A[WRAPPED]) != (0 int)) ? false : false)
                  (wrap boolean:?: TERNARY null = ((wrap int:0x0036: ARITH (29472 int) & (64 int) A[WRAPPED]) != (0 int)) ? false : (wrap boolean:0x002e: INVOKE (r1v1 'esiaConfig' ru.mail.mailapp.DTOConfiguration$Config$EsiaConfig) INTERFACE call: ru.mail.mailapp.DTOConfiguration.Config.EsiaConfig.getEnabledRegistration():boolean A[MD:():boolean (m), WRAPPED] (LINE:47)))
                  (wrap ru.mail.ui.auth.universal.SmallLoginButtonView$Listener:?: TERNARY null = ((wrap int:0x003e: ARITH (29472 int) & (128 int) A[WRAPPED]) != (0 int)) ? (null ru.mail.ui.auth.universal.SmallLoginButtonView$Listener) : (r21v0 'this' ru.mail.ui.fragments.RegistrationLibverifyFragment A[IMMUTABLE_TYPE, THIS]))
                  (wrap boolean:?: TERNARY null = ((wrap int:0x0046: ARITH (29472 int) & (256 int) A[WRAPPED]) != (0 int)) ? false : false)
                  (wrap android.view.View$OnClickListener:?: TERNARY null = ((wrap int:0x004e: ARITH (29472 int) & (512 int) A[WRAPPED]) != (0 int)) ? (null android.view.View$OnClickListener) : (null android.view.View$OnClickListener))
                  (wrap boolean:?: TERNARY null = ((wrap int:0x0056: ARITH (29472 int) & (1024 int) A[WRAPPED]) != (0 int)) ? false : (wrap boolean:0x0032: INVOKE (r21v0 'this' ru.mail.ui.fragments.RegistrationLibverifyFragment A[IMMUTABLE_TYPE, THIS]) VIRTUAL call: ru.mail.ui.fragments.RegistrationLibverifyFragment.isRedesignEnabled():boolean A[MD:():boolean (m), WRAPPED] (LINE:51)))
                  false
                  (wrap kotlin.jvm.functions.Function1:?: TERNARY null = ((wrap int:0x005e: ARITH (29472 int) & (4096 int) A[WRAPPED]) != (0 int)) ? (wrap ??:0x0067: CONSTRUCTOR  A[MD:():void (m), WRAPPED] (LINE:2) call: ru.mail.ui.j1.<init>():void type: CONSTRUCTOR) : (null kotlin.jvm.functions.Function1))
                  (wrap boolean:?: TERNARY null = ((wrap int:0x006c: ARITH (29472 int) & (8192 int) A[WRAPPED]) != (0 int)) ? false : false)
                  (wrap ru.mail.ui.auth.universal.SmallLoginButtonView$Listener:?: TERNARY null = ((wrap int:0x0075: ARITH (29472 int) & (16384 int) A[WRAPPED]) != (0 int)) ? (null ru.mail.ui.auth.universal.SmallLoginButtonView$Listener) : (null ru.mail.ui.auth.universal.SmallLoginButtonView$Listener))
                  (wrap ru.mail.util.SakErrorToastShowDelegate:0x0036: INVOKE (r21v0 'this' ru.mail.ui.fragments.RegistrationLibverifyFragment A[IMMUTABLE_TYPE, THIS]) VIRTUAL call: ru.mail.ui.fragments.RegistrationLibverifyFragment.getErrorToastShowDelegate():ru.mail.util.SakErrorToastShowDelegate A[MD:():ru.mail.util.SakErrorToastShowDelegate (m), WRAPPED] (LINE:55))
                 VIRTUAL call: ru.mail.ui.SocialAuthButtonsView.initVkView(boolean, boolean, boolean, ru.mail.credentialsexchanger.FromScreen, ru.mail.analytics.MailAppAnalytics, boolean, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, boolean, android.view.View$OnClickListener, boolean, boolean, kotlin.jvm.functions.Function1, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, ru.mail.util.SakErrorToastShowDelegate):void A[MD:(boolean, boolean, boolean, ru.mail.credentialsexchanger.FromScreen, ru.mail.analytics.MailAppAnalytics, boolean, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, boolean, android.view.View$OnClickListener, boolean, boolean, kotlin.jvm.functions.Function1<? super ru.mail.authorizationsdk.feature.socialauth.domain.SocialAuthInitMode, kotlin.Unit>, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, ru.mail.util.SakErrorToastShowDelegate):void (m)] (LINE:3) in method: ru.mail.ui.fragments.RegistrationLibverifyFragment.initSocialButtons():void, file: classes13.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: ru.mail.ui.j1, state: NOT_LOADED
                	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:1187)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:536)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                	... 21 more
                */
            /*
                this = this;
                r8 = r21
                ru.mail.config.Configuration r0 = r8.getConfig()
                ru.mail.config.Configuration$SocialLoginConfig r0 = r0.getSocialLoginConfig()
                ru.mail.config.Configuration r1 = r8.getConfig()
                ru.mail.mailapp.DTOConfiguration$Config$EsiaConfig r1 = r1.getEsiaConfig()
                r2 = r0
                ru.mail.ui.SocialAuthButtonsView r0 = r8.vkButtonView
                if (r0 == 0) goto L53
                r3 = r1
                boolean r1 = r2.isVKConnectSignupEnabled()
                r4 = r2
                boolean r2 = com.vk.superapp.SuperappKit.isInitialized()
                r5 = r3
                boolean r3 = r4.isOneTapEnabled()
                r6 = r4
                ru.mail.credentialsexchanger.FromScreen r4 = ru.mail.credentialsexchanger.FromScreen.REGISTRATION
                r7 = r5
                ru.mail.analytics.MailAppAnalytics r5 = r8.getAnalytics()
                boolean r7 = r7.getEnabledRegistration()
                boolean r11 = r8.isRedesignEnabled()
                ru.mail.util.SakErrorToastShowDelegate r16 = r8.getErrorToastShowDelegate()
                r17 = 29472(0x7320, float:4.1299E-41)
                r18 = 0
                r9 = r6
                r6 = 0
                r10 = r9
                r9 = 0
                r12 = r10
                r10 = 0
                r13 = r12
                r12 = 0
                r14 = r13
                r13 = 0
                r15 = r14
                r14 = 0
                r19 = r15
                r15 = 0
                r20 = r19
                ru.mail.ui.SocialAuthButtonsView.initVkView$default(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
                goto L55
            L53:
                r20 = r2
            L55:
                ru.mail.ui.SocialAuthButtonsView r0 = r8.vkButtonView
                if (r0 == 0) goto L63
                ru.mail.ui.fragments.RegistrationLibverifyFragment$initSocialButtons$1 r1 = new ru.mail.ui.fragments.RegistrationLibverifyFragment$initSocialButtons$1
                r15 = r20
                r1.<init>()
                r0.attachListener(r1)
            L63:
                ru.mail.ui.SocialAuthButtonsView r0 = r8.vkButtonView
                if (r0 == 0) goto L6b
                r1 = 0
                r0.setVisibility(r1)
            L6b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: ru.mail.ui.fragments.RegistrationLibverifyFragment.initSocialButtons():void");
        }

        protected final void initVKID() {
            VKAuthenticator.Companion companion = VKAuthenticator.INSTANCE;
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
            CredentialsExchanger.INSTANCE.setUnauthorized(companion.getMailRuClientId(contextRequireContext));
            if (isVkRegWithFullData()) {
                hideFields(CollectionsKt.arrayListOf("password", SocialAuthKnownFields.GENERATE_PASSWORD));
            }
            if (isVkRegWithOnlyEmail()) {
                hideFields(CollectionsKt.arrayListOf("password", SocialAuthKnownFields.GENERATE_PASSWORD, "phone", "birthday", "first_name", "last_name", "gender"));
                hideVKConnectButton();
            }
        }

        @Override // ru.mail.registration.ui.AbstractRegistrationFragment
        protected boolean isRedesignEnabled() {
            return getConfig().getRegRebrandingConfig().getRedesignEnabled();
        }

        /* JADX INFO: renamed from: isSkipVkcReg, reason: from getter */
        protected final boolean getIsSkipVkcReg() {
            return this.isSkipVkcReg;
        }

        protected boolean isVkRegWithFullData() {
            Configuration.SocialLoginConfig socialLoginConfig = getConfig().getSocialLoginConfig();
            return (!socialLoginConfig.isVkRegWithFullData() || this.isSkipVkcReg || socialLoginConfig.isVkRegWithOnlyEmail()) ? false : true;
        }

        protected boolean isVkRegWithOnlyEmail() {
            Configuration.SocialLoginConfig socialLoginConfig = getConfig().getSocialLoginConfig();
            return (!socialLoginConfig.isVkRegWithOnlyEmail() || this.isSkipVkcReg || socialLoginConfig.isVkRegWithFullData()) ? false : true;
        }

        @Override // ru.mail.registration.ui.RegistrationMailRuFragment, ru.mail.registration.ui.AbstractRegistrationFragment, androidx.fragment.app.Fragment
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RequestCode.OPEN_HELP_SCREEN_WEBVIEW.id() && resultCode == -1 && data != null) {
                getAccountData().setSendMeAds(data.getBooleanExtra(EXTRA_SEND_ME_ADS, true));
            }
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
        public void onAnotherWayToLogin() {
            showFields(CollectionsKt.arrayListOf("password", "phone", "birthday", "first_name", "last_name", "gender"));
            setUpGeneratePasswordButton();
            getAccountData().setSignupPrepareToken(null);
            this.isSkipVkcReg = true;
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
        public void onAuthDataReady(@NotNull Account eAccount, @NotNull AuthorizeResult authorizeResult) {
            Intrinsics.checkNotNullParameter(eAccount, "eAccount");
            Intrinsics.checkNotNullParameter(authorizeResult, "authorizeResult");
            Bundle bundle = new Bundle();
            bundle.putBoolean("isImmediateAuth", true);
            if (authorizeResult instanceof AuthorizeRequestCommand.OAuthTokensResult) {
                AuthorizeRequestCommand.OAuthTokensResult oAuthTokensResult = (AuthorizeRequestCommand.OAuthTokensResult) authorizeResult;
                bundle.putString(MailAccountConstants.AUTHTOKEN_TYPE_OAUTH_REFRESH, oAuthTokensResult.getRefreshToken());
                bundle.putString("ru.mail.oauth2.access", oAuthTokensResult.getAccessToken());
            } else if (authorizeResult instanceof AuthorizeRequestCommand.MpopCookieResult) {
                bundle.putString("authtoken", ((AuthorizeRequestCommand.MpopCookieResult) authorizeResult).getMpopCookie());
            }
            bundle.putString("authAccount", eAccount.getLogin());
            bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.VK_CONNECT.toString());
            bundle.putString("account_key_first_name", eAccount.getFirstName());
            bundle.putString("account_key_last_name", eAccount.getLastName());
            Intent intent = new Intent(requireContext(), (Class<?>) ConfirmationMailRuActivity.class);
            intent.putExtras(bundle);
            intent.putExtra(ConfirmationActivity.CONFIRM_ACT_VK_TOKEN_KEY, this.vkToken);
            startActivity(intent);
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }

        @Override // ru.mail.ui.auth.universal.SmallLoginButtonView.Listener
        public void onButtonClicked(@NotNull FromScreen fromScreen) {
            Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
            getAnalytics().onClickEsiaButton(fromScreen.name());
            LOG.d("Esia button clicked. Screen = " + fromScreen.name());
            EsiaAuthViewModel esiaAuthViewModel = this.esiaAuthViewModel;
            if (esiaAuthViewModel != null) {
                esiaAuthViewModel.onEsiaAuthClicked(fromScreen);
            }
            SocialRegistrationContract.Presenter presenter = this.presenter;
            if (presenter != null) {
                presenter.onSocialButtonReg();
            }
        }

        @Override // ru.mail.ui.auth.universal.SmallLoginButtonView.Listener
        public void onButtonShown(@NotNull FromScreen fromScreen) {
            Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
            LOG.d("Esia button clicked. Screen = " + fromScreen.name());
            getAnalytics().onShowEsiaButton(fromScreen.name());
        }

        @Override // androidx.fragment.app.Fragment
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ConfigurationWithRawData configuration = getConfigurationRepository().getConfiguration();
            Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
            setConfig(configuration);
            this.esiaAuthViewModel = (EsiaAuthViewModel) ViewModelObtainerKt.obtainViewModel(this, EsiaAuthViewModel.class, this);
        }

        @Override // ru.mail.ui.fragments.mailbox.RegistrationSafetyVerifyMailRuFragment, ru.mail.registration.ui.RegistrationMailRuFragment, ru.mail.registration.ui.AbstractRegistrationFragment, androidx.fragment.app.Fragment
        @Nullable
        public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            Intrinsics.checkNotNullParameter(inflater, "inflater");
            View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
            TextView textView = viewOnCreateView != null ? (TextView) viewOnCreateView.findViewById(R.id.agreement_text) : null;
            Intrinsics.checkNotNull(textView);
            replaceUrlSpan(textView);
            return viewOnCreateView;
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            SocialAuthButtonsView socialAuthButtonsView = this.vkButtonView;
            if (socialAuthButtonsView != null) {
                socialAuthButtonsView.detachListener();
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onDestroyView() {
            super.onDestroyView();
            SocialRegistrationContract.Presenter presenter = this.presenter;
            if (presenter != null) {
                presenter.onDestroy();
            }
            SocialAuthButtonsView socialAuthButtonsView = this.vkButtonView;
            if (socialAuthButtonsView != null) {
                socialAuthButtonsView.onDestroy();
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onResume() {
            FragmentActivity activity;
            super.onResume();
            getUserCancelEsiaAuthDelegate().trackUserCancelledEsiaAuth("REGISTRATION");
            SocialAuthButtonsView socialAuthButtonsView = this.vkButtonView;
            if (socialAuthButtonsView != null) {
                socialAuthButtonsView.onResume(FromScreen.REGISTRATION);
            }
            if (isRedesignEnabled() && (activity = getActivity()) != null) {
                activity.setTitle(R.string.reg_activity_title);
            }
            VkClientAuthLib.INSTANCE.setFlowSource(getVKAuthFlowSource());
        }

        @Override // ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
        public void onSignupCancelled() {
            if (isAdded()) {
                stopProgress();
            }
        }

        @Override // ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
        public void onSignupError(@NotNull List<? extends ErrorValue> valueList) {
            Intrinsics.checkNotNullParameter(valueList, "valueList");
            stopProgress();
            showResErrors(valueList);
        }

        @Override // ru.mail.registration.ui.SignUpDelegate.SignupResultReceiver
        public void onSignupOk(@Nullable String regId, @Nullable String recaptchaSiteKey) {
            if (isAdded()) {
                hideErrorContainer();
                hideErrorViews();
                stopProgress();
                this.mNeedUseLibverify = getConfig().isLibverifyEnabled();
                Bundle arguments = getArguments();
                String string = arguments != null ? arguments.getString(RegistrationMailRuFragment.REG_FRAG_VK_TOKEN_KEY, "") : null;
                startConfirmationActivity(false, string != null ? string : "");
            }
        }

        @Override // androidx.fragment.app.Fragment
        public void onStop() {
            super.onStop();
            SocialAuthButtonsView socialAuthButtonsView = this.vkButtonView;
            if (socialAuthButtonsView != null) {
                socialAuthButtonsView.onStop();
            }
        }

        @Override // ru.mail.ui.fragments.mailbox.RegistrationSafetyVerifyMailRuFragment, androidx.fragment.app.Fragment
        public void onViewCreated(@NotNull View view, @Nullable Bundle saveInstanceState) {
            Intrinsics.checkNotNullParameter(view, "view");
            super.onViewCreated(view, saveInstanceState);
            findViews(view);
            setUpSpecificViews(view);
            setUpRegistrationExperiments(view, getConfigurationRepository().getConfiguration().getRegistrationExpsConfig());
            initIntentExtras();
            observeStates();
            checkRedesign(view);
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
        public void openVKIDDoregistration(@NotNull SocialAuthKnownFields knownFields, @NotNull SocialRegistrationContract.Presenter.RegFrom regFrom, @Nullable String token, @NotNull CredentialsExchanger.SocialBindType type, @NotNull String vkToken) {
            Intrinsics.checkNotNullParameter(knownFields, "knownFields");
            Intrinsics.checkNotNullParameter(regFrom, "regFrom");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(vkToken, "vkToken");
            SocialAuthKnownFields currentSocialAuthFields = getCurrentSocialAuthFields(knownFields, regFrom);
            this.isSkipVkcReg = true;
            showFragment(AfterVkcRegistrationLibverifyFragment.INSTANCE.newInstance(token, currentSocialAuthFields, !knownFields.isFilled(), type, vkToken), true);
            getAnalytics().onVkcLoginFromDefaultReg();
        }

        @Override // ru.mail.registration.ui.AbstractRegistrationFragment
        protected void putExtrasInConfirmationIntent(@NotNull Intent intent) {
            Intrinsics.checkNotNullParameter(intent, "intent");
            super.putExtrasInConfirmationIntent(intent);
            intent.putExtra(EXTRA_NEED_USE_LIB_VERIFY, this.mNeedUseLibverify);
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
        public void registerByVKID(@NotNull String token, @Nullable SocialAuthKnownFields.KnownFieldValues knownFields, @NotNull String vkToken) {
            Intrinsics.checkNotNullParameter(token, "token");
            Intrinsics.checkNotNullParameter(vkToken, "vkToken");
            getAccountData().setSignupPrepareToken(token);
            if (getConfig().getRegistrationExpsConfig().getOnlyEmail()) {
                String firstName = knownFields != null ? knownFields.getFirstName() : null;
                String lastName = knownFields != null ? knownFields.getLastName() : null;
                if (firstName == null || firstName.length() == 0 || lastName == null || lastName.length() == 0) {
                    getAnalytics().onVKIDNameOrLastNameEmpty();
                }
                getFirstName().setText(firstName);
                getSecondName().setText(lastName);
            }
            if (!AuthenticatorConfig.getInstance().isChildRegistrationFixEnabled()) {
                requireActivity().getIntent().putExtra(RegistrationMailRuFragment.USER_STARTED_VKID_REGISTRATION_WITH_FULL_DATA, true);
            }
            super.startConfirmationActivity(Boolean.TRUE, vkToken);
        }

        @Override // ru.mail.registration.ui.AbstractRegistrationFragment
        protected void selectEmail(@NotNull String selectedEmail) {
            Intrinsics.checkNotNullParameter(selectedEmail, "selectedEmail");
            super.selectEmail(selectedEmail);
            String str = (String) CollectionsKt.getOrNull(new Regex("@").split(selectedEmail, 0), 1);
            if (str != null) {
                setDomain("@" + str);
            }
        }

        public final void setAnalytics(@NotNull MailAppAnalytics mailAppAnalytics) {
            Intrinsics.checkNotNullParameter(mailAppAnalytics, "<set-?>");
            this.analytics = mailAppAnalytics;
        }

        public final void setAnalyticsRegFromHelper(@NotNull AnalyticsRegFormHelper analyticsRegFormHelper) {
            Intrinsics.checkNotNullParameter(analyticsRegFormHelper, "<set-?>");
            this.analyticsRegFromHelper = analyticsRegFormHelper;
        }

        protected final void setConfig(@NotNull Configuration configuration) {
            Intrinsics.checkNotNullParameter(configuration, "<set-?>");
            this.config = configuration;
        }

        public final void setConfigurationRepository(@NotNull ConfigurationRepository configurationRepository) {
            Intrinsics.checkNotNullParameter(configurationRepository, "<set-?>");
            this.configurationRepository = configurationRepository;
        }

        public final void setErrorToastShowDelegate(@NotNull SakErrorToastShowDelegate sakErrorToastShowDelegate) {
            Intrinsics.checkNotNullParameter(sakErrorToastShowDelegate, "<set-?>");
            this.errorToastShowDelegate = sakErrorToastShowDelegate;
        }

        public final void setLicenseAgreementConfigRepository(@NotNull dagger.Lazy<LicenseAgreementConfigRepository> lazy) {
            Intrinsics.checkNotNullParameter(lazy, "<set-?>");
            this.licenseAgreementConfigRepository = lazy;
        }

        protected final void setSkipVkcReg(boolean z10) {
            this.isSkipVkcReg = z10;
        }

        protected void setUpRegistrationExperiments(@NotNull View view, @NotNull DTOConfiguration.Config.RegistrationExperiments config) {
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(config, "config");
            if (config.getGenderHidden()) {
                RegViewInterface regViewInterface = this.mViewGender;
                RegView regView = regViewInterface instanceof RegView ? (RegView) regViewInterface : null;
                if (regView != null) {
                    regView.setVisibility(8);
                }
            }
            if (config.getEulaCheckboxEnabled()) {
                this.mUserAgreementCheckBox.setChecked(true);
            }
            setUpGeneratePasswordButton();
        }

        public final void setUserCancelEsiaAuthDelegate(@NotNull UserCancelEsiaAuthDelegate userCancelEsiaAuthDelegate) {
            Intrinsics.checkNotNullParameter(userCancelEsiaAuthDelegate, "<set-?>");
            this.userCancelEsiaAuthDelegate = userCancelEsiaAuthDelegate;
        }

        protected final void setVkButtonView(@Nullable SocialAuthButtonsView socialAuthButtonsView) {
            this.vkButtonView = socialAuthButtonsView;
        }

        protected boolean shouldInitSocialFlow() {
            return (BuildVariantHelper.isMailRu() || BuildVariantHelper.isVK()) && !SocialLoginInfoHolder.isCurrentlyBindingEmail() && getConfig().getSocialLoginConfig().isSuperAppkitEnabled() && SuperappKit.isInitialized();
        }

        @Override // ru.mail.registration.ui.AbstractRegistrationFragment
        protected void showAltEmails(@Nullable List<String> emails) {
            super.showAltEmails(emails);
            List<String> list = emails;
            if (list == null || list.isEmpty() || !getConfig().getRegistrationExpsConfig().getAutofillRegEmail()) {
                return;
            }
            CharSequence text = getLoginView().getText();
            Intrinsics.checkNotNullExpressionValue(text, "getText(...)");
            if (text.length() == 0) {
                selectEmail(emails.get(0));
            }
        }

        @Override // ru.mail.registration.ui.AbstractRegistrationFragment
        protected void showError(@Nullable String error) {
            if (!isRedesignEnabled()) {
                Toast.makeText(requireContext(), error, 0).show();
                return;
            }
            FragmentActivity activity = getActivity();
            MailRuRegistrationActivity mailRuRegistrationActivity = activity instanceof MailRuRegistrationActivity ? (MailRuRegistrationActivity) activity : null;
            if (mailRuRegistrationActivity != null) {
                mailRuRegistrationActivity.showSnackBar(getString(R.string.network_error));
            }
        }

        protected final void showFields(@NotNull ArrayList<String> hideFields) {
            View viewFindViewById;
            Intrinsics.checkNotNullParameter(hideFields, "hideFields");
            Map<String, Integer> fieldsToViewId = getFieldsToViewId();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = hideFields.iterator();
            while (it.hasNext()) {
                Integer num = fieldsToViewId.get((String) it.next());
                if (num != null) {
                    arrayList.add(num);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                int iIntValue = ((Number) it2.next()).intValue();
                View view = getView();
                if (view != null && (viewFindViewById = view.findViewById(iIntValue)) != null) {
                    viewFindViewById.setVisibility(0);
                }
            }
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
        public void showFragment(@NotNull final Fragment fragment, final boolean isReplace) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            final FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
            if (fragmentActivityRequireActivity instanceof RegistrationFragmentsConductor) {
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: ru.mail.ui.fragments.q
                    @Override // java.lang.Runnable
                    public final void run() {
                        RegistrationLibverifyFragment.showFragment$lambda$0(isReplace, fragmentActivityRequireActivity, fragment);
                    }
                });
            }
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View, ru.mail.ui.auth.universal.esia.EsiaAuthViewModel.View
        public void showProgress() {
            if (this.progressDialog == null) {
                CustomProgress customProgress = new CustomProgress(getActivity());
                customProgress.getTextView().setText(R.string.progress_auth);
                this.progressDialog = customProgress;
            }
            CustomProgress customProgress2 = this.progressDialog;
            if (customProgress2 != null) {
                customProgress2.show();
            }
        }

        @Override // ru.mail.registration.ui.RegistrationMailRuFragment
        public /* bridge */ /* synthetic */ void startConfirmationActivity(Boolean bool, String str) {
            startConfirmationActivity(bool.booleanValue(), str);
        }

        @Override // ru.mail.registration.ui.RegistrationMailRuFragment
        protected void startCreatingAccount() {
            boolean is12144Enabled = AuthenticatorConfig.getInstance().getMigrateToPostConfig().getIs12144Enabled();
            if (getConfig().isRegServerValidationPasswordEnabled()) {
                getSignupDelegate().setAccountData(getAccountData());
                getSignupDelegate().performCodeSignup(false);
            } else {
                final RegMailRuCmd regMailRuCmd = new RegMailRuCmd(getThemedContext(), getAccountData(), is12144Enabled);
                regMailRuCmd.execute((RequestArbiter) Locator.INSTANCE.locate(getThemedContext(), RequestArbiter.class)).observe(Schedulers.mainThread(), new CompleteObserver<Object>() { // from class: ru.mail.ui.fragments.RegistrationLibverifyFragment.startCreatingAccount.1
                    @Override // ru.mail.mailbox.cmd.CompleteObserver
                    public void onComplete() {
                        if (RegistrationLibverifyFragment.this.isAdded()) {
                            RegistrationLibverifyFragment.this.stopProgress();
                            Object result = regMailRuCmd.getResult();
                            Intrinsics.checkNotNull(result, "null cannot be cast to non-null type ru.mail.data.cmd.server.RegMailRuCmd.Result");
                            RegMailRuCmd.Result result2 = (RegMailRuCmd.Result) result;
                            if (result2.getCheckEmailResult() != null && result2.getCheckEmailResult().isEmailExist()) {
                                RegistrationLibverifyFragment registrationLibverifyFragment = RegistrationLibverifyFragment.this;
                                registrationLibverifyFragment.setEmailExistsError(registrationLibverifyFragment.getString(R.string.reg_err_email_already_exists));
                            } else {
                                RegistrationLibverifyFragment.this.mNeedUseLibverify = result2.isNeedUseLibverify();
                                RegistrationLibverifyFragment registrationLibverifyFragment2 = RegistrationLibverifyFragment.this;
                                registrationLibverifyFragment2.startConfirmationActivity(false, ((RegistrationMailRuFragment) registrationLibverifyFragment2).vkToken);
                            }
                        }
                    }
                });
            }
        }

        protected void startConfirmationActivity(boolean isUserStartedVkIdRegistrationWithFullData, @Nullable String vkToken) {
            if (!isVkRegWithFullData() && !isVkRegWithOnlyEmail()) {
                super.startConfirmationActivity(Boolean.valueOf(isUserStartedVkIdRegistrationWithFullData), vkToken);
                return;
            }
            if (isAdded()) {
                UiManagerHolder.INSTANCE.turnOnChangeUiForOneAuthFlow();
                BaseToolbarActivity.hideKeyboard(getActivity());
                VkFastLoginBottomSheetFragment vkFastLoginBottomSheetFragmentCreate = new CustomVKFastLoginViewFragment.Builder().setServiceRegistrationFlow(true).create();
                Intrinsics.checkNotNull(vkFastLoginBottomSheetFragmentCreate, "null cannot be cast to non-null type ru.mail.auth.webview.CustomVKFastLoginViewFragment");
                CustomVKFastLoginViewFragment customVKFastLoginViewFragment = (CustomVKFastLoginViewFragment) vkFastLoginBottomSheetFragmentCreate;
                if (getConfig().getSocialLoginConfig().isVkRegSkipEnabled()) {
                    customVKFastLoginViewFragment.setAlternativeAuthCallback(new Function0() { // from class: ru.mail.ui.fragments.l
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RegistrationLibverifyFragment.startConfirmationActivity$lambda$0(this.f98606a);
                        }
                    });
                }
                FragmentManager supportFragmentManager = requireActivity().getSupportFragmentManager();
                Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                customVKFastLoginViewFragment.showAllowingStateLoss(supportFragmentManager, VKID_SIGN_IN_PROMO_TAG);
                SocialRegistrationContract.Presenter presenter = this.presenter;
                if (presenter != null) {
                    presenter.onNextButtonReg();
                }
            }
        }

        @Override // ru.mail.ui.fragments.SocialRegistrationContract.View
        public void showError() {
            ErrorStatus errorStatus = ErrorStatus.SERVERERROR;
            showResErrors(CollectionsKt.listOf(new ErrorValue(errorStatus, getString(errorStatus.getErrorMsg()))));
        }
    }
