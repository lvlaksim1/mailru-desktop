package ru.mail.ui.auth.universal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.vk.auth.DefaultAuthRouter;
import com.vk.auth.ui.consent.VkConsentScreenBottomSheetFragment;
import com.vk.auth.ui.fastloginbutton.VkFastLoginButton;
import com.vk.superapp.SuperappKit;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import java.util.EnumMap;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAnalyticsKt;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.auth.AuthMessageCallback;
import ru.mail.auth.Authenticator;
import ru.mail.auth.BaseAuthActivity;
import ru.mail.auth.BaseToolbarActivity;
import ru.mail.auth.EmailServiceResources;
import ru.mail.auth.ErrorDelegate;
import ru.mail.auth.ForceVkidAuthListener;
import ru.mail.auth.LoginActivity;
import ru.mail.auth.MailAccountConstants;
import ru.mail.auth.MailLoginFragment;
import ru.mail.auth.Message;
import ru.mail.auth.VKAuthenticator;
import ru.mail.auth.bind.SocialLoginInfoHolder;
import ru.mail.auth.loginactivity.CallbackHolder;
import ru.mail.auth.logscollector.LongClickCounterListener;
import ru.mail.auth.webview.CustomTabWebAuthNHelper;
import ru.mail.authorizationsdk.feature.socialauth.domain.SocialAuthInitMode;
import ru.mail.authorizesdk.data.request.common.constants.AuthenticatorConstantsClass;
import ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig;
import ru.mail.authorizesdk.domain.models.SocialAuthConfig;
import ru.mail.authorizesdk.domain.models.SocialAuthModuleConfig;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.credentialsexchanger.FromScreen;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.credentialsexchanger.data.entity.Account;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelper;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkEmailHelperHolder;
import ru.mail.credentialsexchanger.unblockvkusers.RestoreVkidCallback;
import ru.mail.kit.routing.Router;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.R;
import ru.mail.march.navigation.ExtensionsKt;
import ru.mail.march.viewmodel.ViewModelObtainerKt;
import ru.mail.news_feed.util.analytics.NewsAnalyticsHandler;
import ru.mail.portal.app.adapter.di.Portal;
import ru.mail.social.auth.RestoreVkidStartSource;
import ru.mail.social.auth.presentation.AutoLoginProvider;
import ru.mail.social_auth.domain.RestoreVkEmailNavigator;
import ru.mail.social_auth.domain.SocialAuthDelegate;
import ru.mail.social_auth.presentation.SocialAuthSdk;
import ru.mail.ui.SocialAuthButtonsView;
import ru.mail.ui.VkAuthAgreementsView;
import ru.mail.ui.auth.MailRuLoginActivity;
import ru.mail.ui.auth.MailTwoStepLoginScreenFragment;
import ru.mail.ui.auth.ScreenState;
import ru.mail.ui.auth.TwoStepAuthPresenter;
import ru.mail.ui.auth.universal.authDesign.ChangeThemeResolver;
import ru.mail.ui.auth.universal.esia.EsiaAuthViewModel;
import ru.mail.ui.auth.universal.esia.EsiaFlowProvider;
import ru.mail.ui.auth.universal.esia.UserCancelEsiaAuthDelegate;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.AuthMessageCallbackWrapper;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthInteractor;
import ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel;
import ru.mail.ui.auth.vkidbindinlogin.domain.DomainUtilsKt;
import ru.mail.ui.dialogs.GoogleAccountPermissionDialogResult;
import ru.mail.ui.utils.UiExtensionsKt;
import ru.mail.uikit.drawable.BackgroundTheme;
import ru.mail.util.BuildVariantHelper;
import ru.mail.util.SakErrorToastShowDelegate;
import ru.mail.util.log.Log;
import ru.mail.util.shared_prefs.SharedPreferencesProvider;
import ru.mail.utils.CastUtils;
import ru.mail.utils.ClickUtilsKt;
import ru.mail.utils.KeyboardVisibilityHelper;
import ru.mail.utils.UtilExtensionsKt;
import ru.mail.utils.feature.reversed.matching.MatchingExtKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000Ì\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0007\u0018\u0000 Ø\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b:\fÓ\u0001Ô\u0001Õ\u0001Ö\u0001×\u0001Ø\u0001B\u0007¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010r\u001a\u00020s2\b\u0010t\u001a\u0004\u0018\u00010uH\u0016J\u0010\u0010v\u001a\u00020s2\u0006\u0010w\u001a\u00020uH\u0016J$\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020{2\b\u0010|\u001a\u0004\u0018\u00010}2\b\u0010t\u001a\u0004\u0018\u00010uH\u0017J\u0019\u0010~\u001a\u00020s2\u0006\u0010\u007f\u001a\u00020y2\u0007\u0010j\u001a\u00030\u0080\u0001H\u0002J\u0015\u0010\u0081\u0001\u001a\u0004\u0018\u00010y2\b\u0010\u007f\u001a\u0004\u0018\u00010yH\u0014J\u0012\u0010\u0082\u0001\u001a\u00020s2\u0007\u0010\u0083\u0001\u001a\u00020\fH\u0014J\t\u0010\u0084\u0001\u001a\u00020sH\u0002J\t\u0010\u0085\u0001\u001a\u00020sH\u0002J\u001b\u0010\u0086\u0001\u001a\u00020s2\u0006\u0010\u007f\u001a\u00020y2\b\u0010t\u001a\u0004\u0018\u00010uH\u0016J\u0013\u0010\u0087\u0001\u001a\u00020s2\b\u0010\u0088\u0001\u001a\u00030\u0089\u0001H\u0002J\t\u0010\u008a\u0001\u001a\u00020sH\u0016J\t\u0010\u008b\u0001\u001a\u00020\fH\u0014J\t\u0010\u008c\u0001\u001a\u00020sH\u0016J\t\u0010\u008d\u0001\u001a\u00020sH\u0016J'\u0010\u008e\u0001\u001a\u00020s2\u0007\u0010\u008f\u0001\u001a\u00020\u000e2\u0007\u0010\u0090\u0001\u001a\u00020\u000e2\n\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u0092\u0001H\u0016J\t\u0010\u0093\u0001\u001a\u00020sH\u0014J\t\u0010\u0094\u0001\u001a\u00020\fH\u0002J\t\u0010\u0095\u0001\u001a\u00020\fH\u0002J\t\u0010\u0096\u0001\u001a\u00020sH\u0002J\u0011\u0010\u0097\u0001\u001a\u00020s2\u0006\u0010\u007f\u001a\u00020yH\u0002J\u0012\u0010\u0098\u0001\u001a\u00020s2\u0007\u0010\u0099\u0001\u001a\u00020\fH\u0002J\t\u0010\u009a\u0001\u001a\u00020sH\u0002J\u0011\u0010\u009b\u0001\u001a\u00020s2\u0006\u0010\u007f\u001a\u00020yH\u0002J\t\u0010\u009c\u0001\u001a\u00020sH\u0002J\u001e\u0010\u009d\u0001\u001a\u00020s2\n\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009f\u00012\u0007\u0010 \u0001\u001a\u00020\fH\u0014J\t\u0010¡\u0001\u001a\u00020sH\u0002J\u001e\u0010¢\u0001\u001a\u00020s2\n\u0010\u009e\u0001\u001a\u0005\u0018\u00010\u009f\u00012\u0007\u0010£\u0001\u001a\u00020\fH\u0014J\t\u0010¤\u0001\u001a\u00020\"H\u0014J\u0013\u0010¥\u0001\u001a\u00020s2\b\u0010¦\u0001\u001a\u00030\u009f\u0001H\u0016J\t\u0010§\u0001\u001a\u00020sH\u0014J&\u0010¨\u0001\u001a\u00020s2\u0007\u0010©\u0001\u001a\u00020\u000e2\b\u0010ª\u0001\u001a\u00030\u009f\u00012\b\u0010«\u0001\u001a\u00030¬\u0001H\u0016J\u001d\u0010\u00ad\u0001\u001a\u00020s2\b\u0010ª\u0001\u001a\u00030\u009f\u00012\b\u0010«\u0001\u001a\u00030¬\u0001H\u0002J\n\u0010®\u0001\u001a\u00030¯\u0001H\u0002J\n\u0010°\u0001\u001a\u00030¯\u0001H\u0002J\u0013\u0010±\u0001\u001a\u00020s2\b\u0010²\u0001\u001a\u00030\u009f\u0001H\u0002J\t\u0010³\u0001\u001a\u00020sH\u0016J\t\u0010´\u0001\u001a\u00020sH\u0016J\u0013\u0010µ\u0001\u001a\u00020s2\b\u0010¶\u0001\u001a\u00030·\u0001H\u0016J\u0013\u0010¸\u0001\u001a\u00020s2\b\u0010¶\u0001\u001a\u00030¹\u0001H\u0016J\u0013\u0010º\u0001\u001a\u00020s2\b\u0010¦\u0001\u001a\u00030\u009f\u0001H\u0016J\u0013\u0010»\u0001\u001a\u00020s2\b\u0010¦\u0001\u001a\u00030\u009f\u0001H\u0016J\u001d\u0010¼\u0001\u001a\u00020s2\b\u0010¦\u0001\u001a\u00030\u009f\u00012\b\u0010½\u0001\u001a\u00030\u009f\u0001H\u0002J\u001d\u0010¾\u0001\u001a\u00020s2\b\u0010¦\u0001\u001a\u00030\u009f\u00012\b\u0010½\u0001\u001a\u00030\u009f\u0001H\u0002J\u001d\u0010¿\u0001\u001a\u00020s2\b\u0010¦\u0001\u001a\u00030\u009f\u00012\b\u0010½\u0001\u001a\u00030\u009f\u0001H\u0002J\t\u0010À\u0001\u001a\u00020sH\u0002J\t\u0010Á\u0001\u001a\u00020\fH\u0002J\u0013\u0010Â\u0001\u001a\u00020s2\b\u0010Ã\u0001\u001a\u00030Ä\u0001H\u0016J\u0013\u0010Å\u0001\u001a\u00020s2\b\u0010Ã\u0001\u001a\u00030Ä\u0001H\u0002J\u0013\u0010Æ\u0001\u001a\u00020s2\b\u0010Ã\u0001\u001a\u00030Ä\u0001H\u0016J\u0014\u0010Ç\u0001\u001a\u00020s2\t\u0010È\u0001\u001a\u0004\u0018\u00010uH\u0014J\u0015\u0010É\u0001\u001a\u00020s2\n\u0010Ê\u0001\u001a\u0005\u0018\u00010\u009f\u0001H\u0016J\t\u0010Ë\u0001\u001a\u00020sH\u0016J\u0014\u0010Ì\u0001\u001a\u00020s2\t\u0010Í\u0001\u001a\u0004\u0018\u00010uH\u0016J\t\u0010Î\u0001\u001a\u00020sH\u0014J\t\u0010Ï\u0001\u001a\u00020sH\u0016J\t\u0010Ð\u0001\u001a\u00020sH\u0002J\t\u0010Ñ\u0001\u001a\u00020sH\u0002J\t\u0010Ò\u0001\u001a\u00020\fH\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u000e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b#\u0010$R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000200X\u0082.¢\u0006\u0002\n\u0000R\u0010\u00102\u001a\u0004\u0018\u000103X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000207X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u000209X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010:\u001a\u00020;8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001e\u0010@\u001a\u00020A8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER\u001e\u0010F\u001a\u00020G8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u001e\u0010L\u001a\u00020M8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001e\u0010R\u001a\u00020S8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001e\u0010X\u001a\u00020Y8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001e\u0010^\u001a\u00020_8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u001e\u0010d\u001a\u00020e8\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u000e\u0010j\u001a\u00020kX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020mX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010n\u001a\u00020oX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020qX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006Ù\u0001"}, d2 = {"Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment;", "Lru/mail/ui/auth/universal/UniversalLoginScreenFragment;", "Lru/mail/ui/auth/universal/LogoClickListener;", "Lru/mail/ui/auth/universal/OneTapRegViewModel$View;", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/ForceVKIDAuthViewModel$View;", "Lru/mail/ui/auth/universal/SmallLoginButtonView$Listener;", "Lru/mail/ui/auth/universal/esia/EsiaAuthViewModel$View;", "Lru/mail/auth/ForceVkidAuthListener;", "Lru/mail/ui/auth/universal/esia/EsiaFlowProvider;", "<init>", "()V", "isAutologinAlreadyExecuted", "", "autologinSuccessCount", "", "isXmailMigrationFromNotLogin", "isHideUiOnStart", "logoIcon", "Landroid/widget/ImageView;", "backButton", "helpIcon", "logoRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "root", "Landroid/widget/FrameLayout;", "<set-?>", "offset", "getOffset", "()I", "setOffset", "(I)V", "offset$delegate", "Lkotlin/properties/ReadWriteProperty;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lru/mail/utils/KeyboardVisibilityHelper$KeyboardVisibilityListener;", "getListener", "()Lru/mail/utils/KeyboardVisibilityHelper$KeyboardVisibilityListener;", "listener$delegate", "Lkotlin/Lazy;", "restoreHelper", "Lru/mail/credentialsexchanger/unblockvkusers/RestoreVkEmailHelper;", "fullAgreementsTexts", "Lru/mail/ui/VkAuthAgreementsView;", "forceAuthByVKIDButton", "Lcom/vk/auth/ui/fastloginbutton/VkFastLoginButton;", "forceVKIDAuthButtonViewStub", "Landroid/view/ViewStub;", "defaultAuthButtonsContainer", "Landroid/widget/LinearLayout;", "defaultAuthButtons", "oneTapRegViewModel", "Lru/mail/ui/auth/universal/OneTapRegViewModel;", "esiaAuthViewModel", "Lru/mail/ui/auth/universal/esia/EsiaAuthViewModel;", "dataProvider", "Lru/mail/ui/auth/universal/LogoDataProvider;", "changeThemeResolver", "Lru/mail/ui/auth/universal/authDesign/ChangeThemeResolver;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "getAnalytics", "()Lru/mail/analytics/MailAppAnalytics;", "setAnalytics", "(Lru/mail/analytics/MailAppAnalytics;)V", "analyticsKt", "Lru/mail/analytics/MailAnalyticsKt;", "getAnalyticsKt", "()Lru/mail/analytics/MailAnalyticsKt;", "setAnalyticsKt", "(Lru/mail/analytics/MailAnalyticsKt;)V", "userCancelEsiaAuthDelegate", "Lru/mail/ui/auth/universal/esia/UserCancelEsiaAuthDelegate;", "getUserCancelEsiaAuthDelegate", "()Lru/mail/ui/auth/universal/esia/UserCancelEsiaAuthDelegate;", "setUserCancelEsiaAuthDelegate", "(Lru/mail/ui/auth/universal/esia/UserCancelEsiaAuthDelegate;)V", "configRepository", "Lru/mail/config/ConfigurationRepository;", "getConfigRepository", "()Lru/mail/config/ConfigurationRepository;", "setConfigRepository", "(Lru/mail/config/ConfigurationRepository;)V", "sharedPreferencesProvider", "Lru/mail/util/shared_prefs/SharedPreferencesProvider;", "getSharedPreferencesProvider", "()Lru/mail/util/shared_prefs/SharedPreferencesProvider;", "setSharedPreferencesProvider", "(Lru/mail/util/shared_prefs/SharedPreferencesProvider;)V", "mUserBoundByVKIDDelegate", "Lru/mail/ui/auth/universal/UserBoundByVKIDDelegate;", "getMUserBoundByVKIDDelegate", "()Lru/mail/ui/auth/universal/UserBoundByVKIDDelegate;", "setMUserBoundByVKIDDelegate", "(Lru/mail/ui/auth/universal/UserBoundByVKIDDelegate;)V", "newAuthorizationConfig", "Lru/mail/authorizesdk/domain/models/NewAuthorizationSdkConfig;", "getNewAuthorizationConfig", "()Lru/mail/authorizesdk/domain/models/NewAuthorizationSdkConfig;", "setNewAuthorizationConfig", "(Lru/mail/authorizesdk/domain/models/NewAuthorizationSdkConfig;)V", "errorToastShowDelegate", "Lru/mail/util/SakErrorToastShowDelegate;", "getErrorToastShowDelegate", "()Lru/mail/util/SakErrorToastShowDelegate;", "setErrorToastShowDelegate", "(Lru/mail/util/SakErrorToastShowDelegate;)V", "config", "Lru/mail/config/Configuration;", "forceVKIDAuthViewModel", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/ForceVKIDAuthViewModel;", "authDelegate", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/AuthMessageCallbackWrapper;", "actionDownTime", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "setUpRegistrationExperiments", Promotion.ACTION_VIEW, "Lru/mail/mailapp/DTOConfiguration$Config$RegistrationExperiments;", "getCreateEmailLayout", "setRestorePwdViewEnabled", "forceDismiss", "hideRestorePasswordButtonIfNeed", "initHelpIcon", "onViewCreated", "handleOnTouchEvent", "event", "Landroid/view/MotionEvent;", "onResume", "canFocusLoginView", "onStop", "onDestroyView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClickOnProcessToSecondStep", "isShowingQrPromo", "isShowingFullscreenError", "initForceAuthByVKID", "initViews", "initVKID", "isFromSocialBind", "openQrLoginPromo", "initLogosAdapter", "scrollToCenter", "showAuthErrorAsToast", "errorMessage", "", "isLong", "showUiIfError", "onShowAuthError", "isNeedShowToast", "getKeyboardListener", "startEsiaFlow", "email", "initScreens", "onLogoClick", NewsAnalyticsHandler.PARAM_POSITION, "service", "theme", "Lru/mail/uikit/drawable/BackgroundTheme;", "onListLogoClicked", "getUniversalLoginScreen", "Lru/mail/ui/auth/ScreenState;", "getUniversalPasswordScreen", "changeThemeIfValid", "text", "setDefaultUi", "setErrorAuthUi", "startAuth", "state", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/ForceVKIDAuthInteractor$VkIdAuthState$ReadyForLoginState;", "startRecovery", "Lru/mail/ui/auth/universal/forceauthorizationbyvkid/ForceVKIDAuthInteractor$VkIdAuthState$GetBlockedAuthDataState;", "startGettingAuthDataAgain", "setPersonalVKIDAuthUi", "openRestoreFragment", "failUrl", "startNewRecovery", "startOldRecovery", "openDefaultVKIDAuth", "isVkDataAutofillAvailable", "onButtonClicked", "fromScreen", "Lru/mail/credentialsexchanger/FromScreen;", "startEsiaAuthFlowInNewSdk", "onButtonShown", "onAuthSucceeded", "options", "showRestorePasswordScreen", "loginForRestore", "onDestroy", "onBadAuth", "result", "onClickRegistration", "proceedToSecondStep", "launchAutoLogin", "startAutoLogin", "isVKIDAutologinAvailable", "LeelooUniversalPasswordScreen", "LeelooUniversalPasswordWithSmsScreen", "LeelooUniversalPasswordWithoutRestoreScreen", "LeelooUniversalLoginScreen", "LeelooUniversalTextWatcher", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
@SourceDebugExtension({"SMAP\nLeelooUniversalLoginScreenFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LeelooUniversalLoginScreenFragment.kt\nru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,1120:1\n257#2,2:1121\n257#2,2:1123\n*S KotlinDebug\n*F\n+ 1 LeelooUniversalLoginScreenFragment.kt\nru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment\n*L\n374#1:1121,2\n380#1:1123,2\n*E\n"})
public final class LeelooUniversalLoginScreenFragment extends Hilt_LeelooUniversalLoginScreenFragment implements LogoClickListener, OneTapRegViewModel.View, ForceVKIDAuthViewModel.View, SmallLoginButtonView.Listener, EsiaAuthViewModel.View, ForceVkidAuthListener, EsiaFlowProvider {

    @NotNull
    private static final String AUTOLOGIN_SUCCESS_COUNT = "AUTOLOGIN_SUCCESS_COUNT";

    @NotNull
    private static final String IS_AUTOLOGIN_ALREADY_EXECUTED = "IS_AUTOLOGIN_ALREADY_EXECUTED";

    @Inject
    public MailAppAnalytics analytics;

    @Inject
    public MailAnalyticsKt analyticsKt;
    private AuthMessageCallbackWrapper authDelegate;
    private int autologinSuccessCount;

    @Nullable
    private ImageView backButton;
    private ChangeThemeResolver changeThemeResolver;
    private Configuration config;

    @Inject
    public ConfigurationRepository configRepository;
    private LogoDataProvider dataProvider;
    private LinearLayout defaultAuthButtons;
    private LinearLayout defaultAuthButtonsContainer;

    @Inject
    public SakErrorToastShowDelegate errorToastShowDelegate;

    @Nullable
    private EsiaAuthViewModel esiaAuthViewModel;

    @Nullable
    private VkFastLoginButton forceAuthByVKIDButton;
    private ViewStub forceVKIDAuthButtonViewStub;
    private ForceVKIDAuthViewModel forceVKIDAuthViewModel;
    private VkAuthAgreementsView fullAgreementsTexts;

    @Nullable
    private ImageView helpIcon;
    private boolean isAutologinAlreadyExecuted;
    private boolean isHideUiOnStart;
    private boolean isXmailMigrationFromNotLogin;

    @Nullable
    private ImageView logoIcon;

    @Nullable
    private RecyclerView logoRecyclerView;

    @Inject
    public UserBoundByVKIDDelegate mUserBoundByVKIDDelegate;

    @Inject
    public NewAuthorizationSdkConfig newAuthorizationConfig;

    @Nullable
    private OneTapRegViewModel oneTapRegViewModel;

    @Nullable
    private RestoreVkEmailHelper restoreHelper;

    @Nullable
    private FrameLayout root;

    @Inject
    public SharedPreferencesProvider sharedPreferencesProvider;

    @Inject
    public UserCancelEsiaAuthDelegate userCancelEsiaAuthDelegate;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(LeelooUniversalLoginScreenFragment.class, "offset", "getOffset()I", 0))};
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("LeelooUniversalLoginScreenFragment");

    /* JADX INFO: renamed from: offset$delegate, reason: from kotlin metadata */
    @NotNull
    private final ReadWriteProperty offset = Delegates.INSTANCE.notNull();

    /* JADX INFO: renamed from: listener$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy listener = LazyKt.lazy(new Function0() { // from class: ru.mail.ui.auth.universal.k
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return LeelooUniversalLoginScreenFragment.listener_delegate$lambda$0(this.f98248a);
        }
    });
    private long actionDownTime = LongCompanionObject.MAX_VALUE;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u000bH\u0014J\b\u0010\u0011\u001a\u00020\u0006H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0005\u0010\u0007¨\u0006\u0012"}, d2 = {"Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalLoginScreen;", "Lru/mail/ui/auth/universal/UniversalLoginScreenFragment$UniversalLoginScreen;", "Lru/mail/ui/auth/universal/UniversalLoginScreenFragment;", "<init>", "(Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment;)V", "isSocialLoginEnabled", "", "()Z", "isSocialLoginEnabled$delegate", "Lkotlin/Lazy;", "apply", "", "fragment", "Landroidx/fragment/app/Fragment;", "getTextWatcher", "Landroid/text/TextWatcher;", "showKeyboardOnLoginScreen", "isPersonalVKIDAuthUiShown", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nLeelooUniversalLoginScreenFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LeelooUniversalLoginScreenFragment.kt\nru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalLoginScreen\n+ 2 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,1120:1\n255#2:1121\n*S KotlinDebug\n*F\n+ 1 LeelooUniversalLoginScreenFragment.kt\nru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalLoginScreen\n*L\n817#1:1121\n*E\n"})
    public final class LeelooUniversalLoginScreen extends UniversalLoginScreenFragment.UniversalLoginScreen {

        /* JADX INFO: renamed from: isSocialLoginEnabled$delegate, reason: from kotlin metadata */
        @NotNull
        private final Lazy isSocialLoginEnabled;

        public LeelooUniversalLoginScreen() {
            super();
            this.isSocialLoginEnabled = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: ru.mail.ui.auth.universal.m
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Boolean.valueOf(LeelooUniversalLoginScreenFragment.LeelooUniversalLoginScreen.isSocialLoginEnabled_delegate$lambda$0(leelooUniversalLoginScreenFragment));
                }
            });
        }

        private final boolean isPersonalVKIDAuthUiShown() {
            if (LeelooUniversalLoginScreenFragment.this.fullAgreementsTexts == null) {
                return false;
            }
            VkAuthAgreementsView vkAuthAgreementsView = LeelooUniversalLoginScreenFragment.this.fullAgreementsTexts;
            if (vkAuthAgreementsView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fullAgreementsTexts");
                vkAuthAgreementsView = null;
            }
            return vkAuthAgreementsView.getVisibility() == 0;
        }

        private final boolean isSocialLoginEnabled() {
            return ((Boolean) this.isSocialLoginEnabled.getValue()).booleanValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean isSocialLoginEnabled_delegate$lambda$0(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment) {
            Configuration configuration = leelooUniversalLoginScreenFragment.config;
            if (configuration == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
                configuration = null;
            }
            Configuration.SocialLoginConfig socialLoginConfig = configuration.getSocialLoginConfig();
            return (BuildVariantHelper.isMailRu() || BuildVariantHelper.isVK()) && !leelooUniversalLoginScreenFragment.isFromSocialBind() && socialLoginConfig.isVKConnectLoginEnabled() && socialLoginConfig.isSuperAppkitEnabled();
        }

        @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment.UniversalLoginScreen, ru.mail.ui.auth.MailTwoStepLoginScreenFragment.LoginScreen, ru.mail.ui.auth.ScreenState
        public void apply(@NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            super.apply(fragment);
            SocialAuthButtonsView socialAuthButtonsView = LeelooUniversalLoginScreenFragment.this.getSocialAuthButtonsView();
            if (socialAuthButtonsView != null) {
                socialAuthButtonsView.setVisibility((!isSocialLoginEnabled() || isPersonalVKIDAuthUiShown()) ? 8 : 0);
            }
            LeelooUniversalLoginScreenFragment.this.hideRestorePasswordButtonIfNeed();
            LeelooUniversalLoginScreenFragment.this.initHelpIcon();
        }

        @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment.UniversalLoginScreen
        @NotNull
        protected TextWatcher getTextWatcher() {
            return LeelooUniversalLoginScreenFragment.this.new LeelooUniversalTextWatcher();
        }

        @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment.LoginScreen
        protected void showKeyboardOnLoginScreen() {
            Configuration configuration = LeelooUniversalLoginScreenFragment.this.config;
            if (configuration == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
                configuration = null;
            }
            if (configuration.isHideKeyboardOnLoginScreen()) {
                return;
            }
            super.showKeyboardOnLoginScreen();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalPasswordScreen;", "Lru/mail/ui/auth/MailTwoStepLoginScreenFragment$PasswordScreen;", "Lru/mail/ui/auth/MailTwoStepLoginScreenFragment;", "<init>", "(Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment;)V", "apply", "", "fragment", "Landroidx/fragment/app/Fragment;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class LeelooUniversalPasswordScreen extends MailTwoStepLoginScreenFragment.PasswordScreen {
        public LeelooUniversalPasswordScreen() {
            super();
        }

        @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment.PasswordScreen, ru.mail.ui.auth.ScreenState
        public void apply(@NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            super.apply(fragment);
            LeelooUniversalLoginScreenFragment.this.getSocialAuthButtonsView().setVisibility(8);
            View restorePasswordOnPassScreen = LeelooUniversalLoginScreenFragment.this.getRestorePasswordOnPassScreen();
            if (restorePasswordOnPassScreen != null) {
                restorePasswordOnPassScreen.setVisibility(0);
            }
            ((MailTwoStepLoginScreenFragment) LeelooUniversalLoginScreenFragment.this).mKeyboardVisibilityHelper.setKeyboardVisibilityListener(LeelooUniversalLoginScreenFragment.this.getKeyboardListener());
            ImageView imageView = LeelooUniversalLoginScreenFragment.this.helpIcon;
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            LeelooUniversalLoginScreenFragment.this.hideRestorePasswordButtonIfNeed();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalPasswordWithSmsScreen;", "Lru/mail/ui/auth/MailTwoStepLoginScreenFragment$PasswordWithSmsScreen;", "Lru/mail/ui/auth/MailTwoStepLoginScreenFragment;", "<init>", "(Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment;)V", "apply", "", "fragment", "Landroidx/fragment/app/Fragment;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class LeelooUniversalPasswordWithSmsScreen extends MailTwoStepLoginScreenFragment.PasswordWithSmsScreen {
        public LeelooUniversalPasswordWithSmsScreen() {
            super();
        }

        @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment.PasswordWithSmsScreen, ru.mail.ui.auth.MailTwoStepLoginScreenFragment.PasswordScreen, ru.mail.ui.auth.ScreenState
        public void apply(@NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            super.apply(fragment);
            LeelooUniversalLoginScreenFragment.this.getSocialAuthButtonsView().setVisibility(8);
            View restorePasswordOnPassScreen = LeelooUniversalLoginScreenFragment.this.getRestorePasswordOnPassScreen();
            if (restorePasswordOnPassScreen != null) {
                restorePasswordOnPassScreen.setVisibility(0);
            }
            LeelooUniversalLoginScreenFragment.this.hideRestorePasswordButtonIfNeed();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalPasswordWithoutRestoreScreen;", "Lru/mail/ui/auth/MailTwoStepLoginScreenFragment$PasswordWithoutRestore;", "Lru/mail/ui/auth/MailTwoStepLoginScreenFragment;", "<init>", "(Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment;)V", "apply", "", "fragment", "Landroidx/fragment/app/Fragment;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class LeelooUniversalPasswordWithoutRestoreScreen extends MailTwoStepLoginScreenFragment.PasswordWithoutRestore {
        public LeelooUniversalPasswordWithoutRestoreScreen() {
            super();
        }

        @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment.PasswordWithoutRestore, ru.mail.ui.auth.MailTwoStepLoginScreenFragment.PasswordScreen, ru.mail.ui.auth.ScreenState
        public void apply(@NotNull Fragment fragment) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            super.apply(fragment);
            LeelooUniversalLoginScreenFragment.this.getSocialAuthButtonsView().setVisibility(8);
            View restorePasswordOnPassScreen = LeelooUniversalLoginScreenFragment.this.getRestorePasswordOnPassScreen();
            if (restorePasswordOnPassScreen != null) {
                restorePasswordOnPassScreen.setVisibility(8);
            }
            LeelooUniversalLoginScreenFragment.this.hideRestorePasswordButtonIfNeed();
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J*\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\nH\u0016J\u0012\u0010\r\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u000eH\u0016¨\u0006\u000f"}, d2 = {"Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment$LeelooUniversalTextWatcher;", "Lru/mail/ui/auth/universal/UniversalLoginScreenFragment$UniversalTextWatcher;", "Lru/mail/ui/auth/universal/UniversalLoginScreenFragment;", "<init>", "(Lru/mail/ui/auth/universal/LeelooUniversalLoginScreenFragment;)V", "onTextChanged", "", "s", "", "start", "", "before", "count", "afterTextChanged", "Landroid/text/Editable;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public final class LeelooUniversalTextWatcher extends UniversalLoginScreenFragment.UniversalTextWatcher {
        public LeelooUniversalTextWatcher() {
            super();
        }

        @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment.UniversalTextWatcher, android.text.TextWatcher
        public void afterTextChanged(@Nullable Editable s10) {
            Configuration configuration = null;
            if (!LeelooUniversalLoginScreenFragment.this.isFromSocialBind()) {
                ForceVKIDAuthViewModel forceVKIDAuthViewModel = LeelooUniversalLoginScreenFragment.this.forceVKIDAuthViewModel;
                if (forceVKIDAuthViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
                    forceVKIDAuthViewModel = null;
                }
                forceVKIDAuthViewModel.checkEnteredEmail(String.valueOf(s10));
            }
            Configuration configuration2 = LeelooUniversalLoginScreenFragment.this.config;
            if (configuration2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
            } else {
                configuration = configuration2;
            }
            SocialAuthModuleConfig socialAuthModuleConfig = configuration.getNewAuthSdkConfig().getSocialAuthModuleConfig();
            SocialAuthConfig socialAuthConfig = LeelooUniversalLoginScreenFragment.this.getNewAuthorizationConfig().getSocialAuthConfig();
            if (socialAuthModuleConfig.isSocialAuthModuleEnable() || socialAuthConfig.isInitEnabled()) {
                SocialAuthDelegate.INSTANCE.setEnteredEmail(String.valueOf(s10));
            } else if (LeelooUniversalLoginScreenFragment.this.getActivity() instanceof LoginActivity) {
                FragmentActivity activity = LeelooUniversalLoginScreenFragment.this.getActivity();
                Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type ru.mail.auth.LoginActivity");
                ((LoginActivity) activity).mVKConnectSignInDelegate.onEmailEntered(String.valueOf(s10));
            }
        }

        @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment.UniversalTextWatcher, android.text.TextWatcher
        public void onTextChanged(@Nullable CharSequence s10, int start, int before, int count) {
            super.onTextChanged(s10, start, before, count);
            if (s10 != null) {
                LeelooUniversalLoginScreenFragment.this.changeThemeIfValid(s10.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeThemeIfValid(String text) {
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) text, '@', 0, false, 6, (Object) null);
        if (iIndexOf$default != -1) {
            String strSubstring = text.substring(iIndexOf$default, text.length());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
            LogoDataProvider logoDataProvider = this.dataProvider;
            ChangeThemeResolver changeThemeResolver = null;
            if (logoDataProvider == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
                logoDataProvider = null;
            }
            BackgroundTheme backgroundThemeValidate = logoDataProvider.validate(strSubstring);
            ChangeThemeResolver changeThemeResolver2 = this.changeThemeResolver;
            if (changeThemeResolver2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("changeThemeResolver");
            } else {
                changeThemeResolver = changeThemeResolver2;
            }
            changeThemeResolver.changeTheme(backgroundThemeValidate);
        }
    }

    private final KeyboardVisibilityHelper.KeyboardVisibilityListener getListener() {
        return (KeyboardVisibilityHelper.KeyboardVisibilityListener) this.listener.getValue();
    }

    private final int getOffset() {
        return ((Number) this.offset.getValue(this, $$delegatedProperties[0])).intValue();
    }

    private final ScreenState getUniversalLoginScreen() {
        return new LeelooUniversalLoginScreen();
    }

    private final ScreenState getUniversalPasswordScreen() {
        return new LeelooUniversalPasswordScreen();
    }

    private final void handleOnTouchEvent(MotionEvent event) {
        LongClickCounterListener longClickCounterListener;
        int actionMasked = event.getActionMasked();
        if (actionMasked == 0) {
            this.actionDownTime = System.currentTimeMillis();
            KeyEventDispatcher.Component activity = getActivity();
            longClickCounterListener = activity instanceof LongClickCounterListener ? (LongClickCounterListener) activity : null;
            if (longClickCounterListener != null) {
                longClickCounterListener.startLongClickCounter();
                return;
            }
            return;
        }
        if (actionMasked != 1) {
            if (actionMasked != 3) {
                return;
            }
            KeyEventDispatcher.Component activity2 = getActivity();
            longClickCounterListener = activity2 instanceof LongClickCounterListener ? (LongClickCounterListener) activity2 : null;
            if (longClickCounterListener != null) {
                longClickCounterListener.cancelLongClickCounter();
            }
            this.actionDownTime = LongCompanionObject.MAX_VALUE;
            return;
        }
        if (System.currentTimeMillis() - this.actionDownTime < ViewConfiguration.getLongPressTimeout()) {
            getAnalytics().logMailClick();
            if (this.mFirstStepLayout.getVisibility() != 8) {
                EmailServiceResources.MailServiceResources mailServiceResources = EmailServiceResources.MailServiceResources.MAILRU;
                String defaultDomain = mailServiceResources.getDefaultDomain();
                Intrinsics.checkNotNullExpressionValue(defaultDomain, "getDefaultDomain(...)");
                BackgroundTheme theme = mailServiceResources.getTheme();
                Intrinsics.checkNotNullExpressionValue(theme, "getTheme(...)");
                onListLogoClicked(defaultDomain, theme);
            }
        }
        KeyEventDispatcher.Component activity3 = getActivity();
        longClickCounterListener = activity3 instanceof LongClickCounterListener ? (LongClickCounterListener) activity3 : null;
        if (longClickCounterListener != null) {
            longClickCounterListener.cancelLongClickCounter();
        }
        this.actionDownTime = LongCompanionObject.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideRestorePasswordButtonIfNeed() {
        View view;
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        if (!configuration.getRegistrationExpsConfig().getLoginForgotPasswordHidden() || (view = this.mRestorePassword) == null) {
            return;
        }
        view.setVisibility(8);
    }

    private final void initForceAuthByVKID() {
        Configuration configuration = this.config;
        VkAuthAgreementsView vkAuthAgreementsView = null;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        boolean zIsEnableForceAuthByVKID = configuration.isEnableForceAuthByVKID();
        Configuration configuration2 = this.config;
        if (configuration2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration2 = null;
        }
        boolean zIsVKConnectLoginEnabled = configuration2.getSocialLoginConfig().isVKConnectLoginEnabled();
        if (zIsEnableForceAuthByVKID && zIsVKConnectLoginEnabled && SuperappKit.isInitialized()) {
            ViewStub viewStub = this.forceVKIDAuthButtonViewStub;
            if (viewStub == null) {
                Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthButtonViewStub");
                viewStub = null;
            }
            View viewInflate = viewStub.inflate();
            Intrinsics.checkNotNull(viewInflate, "null cannot be cast to non-null type com.vk.auth.ui.fastloginbutton.VkFastLoginButton");
            final VkFastLoginButton vkFastLoginButton = (VkFastLoginButton) viewInflate;
            this.forceAuthByVKIDButton = vkFastLoginButton;
            if (vkFastLoginButton != null) {
                vkFastLoginButton.setTextGetter(new VkFastLoginButton.TextGetter() { // from class: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.initForceAuthByVKID.1
                    @Override // com.vk.auth.ui.fastloginbutton.VkFastLoginButton.TextGetter
                    public String getActionText(Context context, String firstName, String lastName, VkFastLoginButton.ActionTextSize actionTextSize) {
                        Intrinsics.checkNotNullParameter(context, "context");
                        Intrinsics.checkNotNullParameter(firstName, "firstName");
                        Intrinsics.checkNotNullParameter(lastName, "lastName");
                        Intrinsics.checkNotNullParameter(actionTextSize, "actionTextSize");
                        String actionText = super.getActionText(context, firstName, lastName, actionTextSize);
                        VkAuthAgreementsView vkAuthAgreementsView2 = LeelooUniversalLoginScreenFragment.this.fullAgreementsTexts;
                        VkAuthAgreementsView vkAuthAgreementsView3 = null;
                        if (vkAuthAgreementsView2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("fullAgreementsTexts");
                            vkAuthAgreementsView2 = null;
                        }
                        vkAuthAgreementsView2.initView(actionText);
                        VkAuthAgreementsView vkAuthAgreementsView4 = LeelooUniversalLoginScreenFragment.this.fullAgreementsTexts;
                        if (vkAuthAgreementsView4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("fullAgreementsTexts");
                        } else {
                            vkAuthAgreementsView3 = vkAuthAgreementsView4;
                        }
                        vkAuthAgreementsView3.setWhiteStyle();
                        return actionText;
                    }
                });
                VkAuthAgreementsView vkAuthAgreementsView2 = this.fullAgreementsTexts;
                if (vkAuthAgreementsView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fullAgreementsTexts");
                } else {
                    vkAuthAgreementsView = vkAuthAgreementsView2;
                }
                vkAuthAgreementsView.attachListener(new VkAuthAgreementsView.VkAuthAgreementsListener() { // from class: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.initForceAuthByVKID.2
                    @Override // ru.mail.ui.VkAuthAgreementsView.VkAuthAgreementsListener
                    public void onTransmittedDataClick() {
                        String avatarUrl = vkFastLoginButton.getAvatarUrl();
                        if (!this.getNewAuthorizationConfig().getSocialAuthConfig().isInitEnabled()) {
                            VkConsentScreenBottomSheetFragment vkConsentScreenBottomSheetFragmentNewInstance = VkConsentScreenBottomSheetFragment.INSTANCE.newInstance(avatarUrl);
                            BaseToolbarActivity.hideKeyboard(this.requireActivity());
                            FragmentManager supportFragmentManager = this.requireActivity().getSupportFragmentManager();
                            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
                            vkConsentScreenBottomSheetFragmentNewInstance.show(supportFragmentManager, "VkConsentScreenBottomSheetFragment");
                            return;
                        }
                        SocialAuthInitMode.OnTransmittedDataClick onTransmittedDataClick = new SocialAuthInitMode.OnTransmittedDataClick(avatarUrl);
                        FragmentActivity activity = this.getActivity();
                        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
                        if (loginActivity != null) {
                            loginActivity.startSocialAuth(onTransmittedDataClick);
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initHelpIcon() {
        Configuration configuration = this.config;
        Configuration configuration2 = null;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        if (!configuration.isHelpInAuthScreenEnabled() || isFromSocialBind()) {
            ImageView imageView = this.helpIcon;
            if (imageView != null) {
                imageView.setVisibility(8);
                return;
            }
            return;
        }
        Configuration configuration3 = this.config;
        if (configuration3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        } else {
            configuration2 = configuration3;
        }
        final String str = configuration2.getUniversalToPortalPaths().get("settings/help");
        ImageView imageView2 = this.helpIcon;
        if (imageView2 != null) {
            imageView2.setVisibility(str == null || str.length() == 0 ? 8 : 0);
            ClickUtilsKt.setDebouncedListener$default(imageView2, 0L, new Function1() { // from class: ru.mail.ui.auth.universal.d
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LeelooUniversalLoginScreenFragment.initHelpIcon$lambda$0$0(str, (View) obj);
                }
            }, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initHelpIcon$lambda$0$0(String str, View it) {
        Intrinsics.checkNotNullParameter(it, "it");
        Router router = Portal.router();
        Uri uri = Uri.parse("portal://" + str);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
        Router.route$default(router, uri, null, 2, null);
        return Unit.INSTANCE;
    }

    private final void initLogosAdapter(View view) {
        View viewFindViewById = view.findViewById(R.id.login_title_image_layout);
        if (viewFindViewById != null) {
            getAccessibilityDelegate().initDomainSelector(viewFindViewById);
        }
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.logo_recycleview);
        this.logoRecyclerView = recyclerView;
        LogoDataProvider logoDataProvider = null;
        if (recyclerView != null) {
            recyclerView.setLayerType(1, null);
        }
        ArrayList arrayList = new ArrayList();
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        arrayList.addAll(configuration.getHideLoginServicesConfig().getServices());
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        Configuration configuration2 = this.config;
        if (configuration2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration2 = null;
        }
        this.dataProvider = new LogoDataProvider(contextRequireContext, arrayList, configuration2.getLoginServicesGmailConfig(), new Function0() { // from class: ru.mail.ui.auth.universal.f
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LeelooUniversalLoginScreenFragment.initLogosAdapter$lambda$1(this.f98239a);
            }
        });
        LogoDataProvider logoDataProvider2 = this.dataProvider;
        if (logoDataProvider2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
        } else {
            logoDataProvider = logoDataProvider2;
        }
        Context contextRequireContext2 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext2, "requireContext(...)");
        HorizontalLogoAdapter horizontalLogoAdapter = new HorizontalLogoAdapter(logoDataProvider, this, contextRequireContext2);
        RecyclerView recyclerView2 = this.logoRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(horizontalLogoAdapter);
        }
        Context contextRequireContext3 = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext3, "requireContext(...)");
        CenterLayoutManager centerLayoutManager = new CenterLayoutManager(contextRequireContext3, 0, false);
        RecyclerView recyclerView3 = this.logoRecyclerView;
        if (recyclerView3 != null) {
            recyclerView3.setLayoutManager(centerLayoutManager);
        }
        if (isFromSocialBind()) {
            RecyclerView recyclerView4 = this.logoRecyclerView;
            if (recyclerView4 != null) {
                recyclerView4.setVisibility(8);
            }
            ((FrameLayout) view.findViewById(R.id.login_input_layout)).setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.leeloo_login_top));
        }
        scrollToCenter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initLogosAdapter$lambda$1(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment) {
        FragmentActivity activity = leelooUniversalLoginScreenFragment.getActivity();
        Configuration configuration = null;
        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
        String extraFrom = loginActivity != null ? loginActivity.getExtraFrom() : null;
        MailAppAnalytics analytics = leelooUniversalLoginScreenFragment.getAnalytics();
        Configuration configuration2 = leelooUniversalLoginScreenFragment.config;
        if (configuration2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        } else {
            configuration = configuration2;
        }
        analytics.onGmailServiceShow(configuration.getLoginServicesGmailConfig().getUseNewImageLogo(), extraFrom);
        return Unit.INSTANCE;
    }

    private final void initVKID(boolean isFromSocialBind) {
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        DTOConfiguration.Config.EsiaConfig esiaConfig = configuration.getEsiaConfig();
        Configuration configuration2 = this.config;
        if (configuration2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration2 = null;
        }
        DTOConfiguration.Config.LoginButtonGmail loginButtonGmailConfig = configuration2.getLoginButtonGmailConfig();
        Configuration configuration3 = this.config;
        if (configuration3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration3 = null;
        }
        final Configuration.SocialLoginConfig socialLoginConfig = configuration3.getSocialLoginConfig();
        if (socialLoginConfig.isVKConnectLoginEnabled() && SuperappKit.isInitialized() && !isFromSocialBind) {
            Bundle arguments = getArguments();
            if (Intrinsics.areEqual(arguments != null ? Boolean.valueOf(arguments.getBoolean(Authenticator.IS_VK_SIGN_IN_DELEGATE_DISABLED)) : null, Boolean.TRUE)) {
                SocialAuthSdk.INSTANCE.setUnauthorized();
            } else {
                VKAuthenticator.Companion companion = VKAuthenticator.INSTANCE;
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                CredentialsExchanger.INSTANCE.setUnauthorized(companion.getMailRuClientId(contextRequireContext));
                if (getActivity() instanceof LoginActivity) {
                    FragmentActivity activity = getActivity();
                    Intrinsics.checkNotNull(activity, "null cannot be cast to non-null type ru.mail.auth.LoginActivity");
                    ((LoginActivity) activity).createVKConnectDelegate();
                }
            }
        }
        boolean zHasSystemFeature = requireContext().getPackageManager().hasSystemFeature("android.hardware.camera.any");
        SocialAuthButtonsView socialAuthButtonsView = getSocialAuthButtonsView();
        Intrinsics.checkNotNullExpressionValue(socialAuthButtonsView, "getSocialAuthButtonsView(...)");
        boolean z10 = false;
        boolean z11 = socialLoginConfig.isVKConnectLoginEnabled() && !isFromSocialBind;
        boolean zIsInitialized = SuperappKit.isInitialized();
        boolean zIsOneTapEnabled = socialLoginConfig.isOneTapEnabled();
        FromScreen fromScreen = FromScreen.LOGIN;
        MailAppAnalytics analytics = getAnalytics();
        boolean enabledLogin = esiaConfig.getEnabledLogin();
        Configuration configuration4 = this.config;
        if (configuration4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration4 = null;
        }
        if (configuration4.getQrLoginConfig().getEnabled() && zHasSystemFeature) {
            z10 = true;
        }
        socialAuthButtonsView.initVkView((29472 & 1) != 0 ? false : z11, (29472 & 2) != 0 ? false : zIsInitialized, (29472 & 4) != 0 ? false : zIsOneTapEnabled, (29472 & 8) != 0 ? FromScreen.LOGIN : fromScreen, (29472 & 16) != 0 ? null : analytics, (29472 & 32) != 0 ? false : enabledLogin, (29472 & 64) != 0 ? false : false, (29472 & 128) != 0 ? null : this, (29472 & 256) != 0 ? false : z10, (29472 & 512) != 0 ? null : new View.OnClickListener() { // from class: ru.mail.ui.auth.universal.l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LeelooUniversalLoginScreenFragment.initVKID$lambda$0(this.f98249a, view);
            }
        }, (29472 & 1024) != 0 ? false : false, getNewAuthorizationConfig().getSocialAuthConfig().isInitEnabled(), (29472 & 4096) != 0 ? 
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0103: INVOKE 
              (r0v3 'socialAuthButtonsView' ru.mail.ui.SocialAuthButtonsView)
              (wrap boolean:?: TERNARY null = ((wrap int:0x0002: ARITH (29472 int) & (1 int) A[WRAPPED]) != (0 int)) ? false : (r1v2 'z11' boolean))
              (wrap boolean:?: TERNARY null = ((wrap int:0x000b: ARITH (29472 int) & (2 int) A[WRAPPED]) != (0 int)) ? false : (r2v1 'zIsInitialized' boolean))
              (wrap boolean:?: TERNARY null = ((wrap int:0x0013: ARITH (29472 int) & (4 int) A[WRAPPED]) != (0 int)) ? false : (r3v3 'zIsOneTapEnabled' boolean))
              (wrap ru.mail.credentialsexchanger.FromScreen:?: TERNARY null = ((wrap int:0x001b: ARITH (29472 int) & (8 int) A[WRAPPED]) != (0 int)) ? (wrap ??:0x0021: SGET  A[WRAPPED] (LINE:1) ru.mail.credentialsexchanger.FromScreen.LOGIN ru.mail.credentialsexchanger.FromScreen) : (r4v3 'fromScreen' ru.mail.credentialsexchanger.FromScreen))
              (wrap ru.mail.analytics.MailAppAnalytics:?: TERNARY null = ((wrap int:0x0025: ARITH (29472 int) & (16 int) A[WRAPPED]) != (0 int)) ? (null ru.mail.analytics.MailAppAnalytics) : (r5v4 'analytics' ru.mail.analytics.MailAppAnalytics))
              (wrap boolean:?: TERNARY null = ((wrap int:0x002e: ARITH (29472 int) & (32 int) A[WRAPPED]) != (0 int)) ? false : (r6v2 'enabledLogin' boolean))
              (wrap boolean:?: TERNARY null = ((wrap int:0x0036: ARITH (29472 int) & (64 int) A[WRAPPED]) != (0 int)) ? false : false)
              (wrap ru.mail.ui.auth.universal.SmallLoginButtonView$Listener:?: TERNARY null = ((wrap int:0x003e: ARITH (29472 int) & (128 int) A[WRAPPED]) != (0 int)) ? (null ru.mail.ui.auth.universal.SmallLoginButtonView$Listener) : (r21v0 'this' ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment A[IMMUTABLE_TYPE, THIS]))
              (wrap boolean:?: TERNARY null = ((wrap int:0x0046: ARITH (29472 int) & (256 int) A[WRAPPED]) != (0 int)) ? false : (r9v1 'z10' boolean))
              (wrap android.view.View$OnClickListener:?: TERNARY null = ((wrap int:0x004e: ARITH (29472 int) & (512 int) A[WRAPPED]) != (0 int)) ? (null android.view.View$OnClickListener) : (wrap android.view.View$OnClickListener:0x00d6: CONSTRUCTOR (r21v0 'this' ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment A[DONT_INLINE, IMMUTABLE_TYPE, THIS]) A[MD:(ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment):void (m), WRAPPED] (LINE:215) call: ru.mail.ui.auth.universal.l.<init>(ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment):void type: CONSTRUCTOR))
              (wrap boolean:?: TERNARY null = ((wrap int:0x0056: ARITH (29472 int) & (1024 int) A[WRAPPED]) != (0 int)) ? false : false)
              (wrap boolean:0x00e1: INVOKE 
              (wrap ru.mail.authorizesdk.domain.models.SocialAuthConfig:0x00dd: INVOKE 
              (wrap ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig:0x00d9: INVOKE (r21v0 'this' ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment A[IMMUTABLE_TYPE, THIS]) VIRTUAL call: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.getNewAuthorizationConfig():ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig A[MD:():ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig (m), WRAPPED] (LINE:218))
             VIRTUAL call: ru.mail.authorizesdk.domain.models.NewAuthorizationSdkConfig.getSocialAuthConfig():ru.mail.authorizesdk.domain.models.SocialAuthConfig A[MD:():ru.mail.authorizesdk.domain.models.SocialAuthConfig (m), WRAPPED] (LINE:222))
             VIRTUAL call: ru.mail.authorizesdk.domain.models.SocialAuthConfig.isInitEnabled():boolean A[MD:():boolean (m), WRAPPED] (LINE:226))
              (wrap kotlin.jvm.functions.Function1:?: TERNARY null = ((wrap int:0x005e: ARITH (29472 int) & (4096 int) A[WRAPPED]) != (0 int)) ? (wrap ??:0x0067: CONSTRUCTOR  A[MD:():void (m), WRAPPED] (LINE:2) call: ru.mail.ui.j1.<init>():void type: CONSTRUCTOR) : (wrap kotlin.jvm.functions.Function1:0x00e8: CONSTRUCTOR (r21v0 'this' ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment A[DONT_INLINE, IMMUTABLE_TYPE, THIS]) A[MD:(ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment):void (m), WRAPPED] (LINE:233) call: ru.mail.ui.auth.universal.c.<init>(ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment):void type: CONSTRUCTOR))
              (wrap boolean:?: TERNARY null = ((wrap int:0x006c: ARITH (29472 int) & (8192 int) A[WRAPPED]) != (0 int)) ? false : (wrap boolean:0x00eb: INVOKE (r3v2 'loginButtonGmailConfig' ru.mail.mailapp.DTOConfiguration$Config$LoginButtonGmail) INTERFACE call: ru.mail.mailapp.DTOConfiguration.Config.LoginButtonGmail.getEnabled():boolean A[MD:():boolean (m), WRAPPED] (LINE:236)))
              (wrap ru.mail.ui.auth.universal.SmallLoginButtonView$Listener:?: TERNARY null = ((wrap int:0x0075: ARITH (29472 int) & (16384 int) A[WRAPPED]) != (0 int)) ? (null ru.mail.ui.auth.universal.SmallLoginButtonView$Listener) : (wrap ru.mail.ui.auth.universal.SmallLoginButtonView$Listener:0x00f1: CONSTRUCTOR (r21v0 'this' ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment A[IMMUTABLE_TYPE, THIS]) A[MD:(ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment):void (m), WRAPPED] (LINE:242) call: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.initVKID.3.<init>(ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment):void type: CONSTRUCTOR))
              (wrap ru.mail.util.SakErrorToastShowDelegate:0x00f4: INVOKE (r21v0 'this' ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment A[IMMUTABLE_TYPE, THIS]) VIRTUAL call: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.getErrorToastShowDelegate():ru.mail.util.SakErrorToastShowDelegate A[MD:():ru.mail.util.SakErrorToastShowDelegate (m), WRAPPED] (LINE:245))
             VIRTUAL call: ru.mail.ui.SocialAuthButtonsView.initVkView(boolean, boolean, boolean, ru.mail.credentialsexchanger.FromScreen, ru.mail.analytics.MailAppAnalytics, boolean, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, boolean, android.view.View$OnClickListener, boolean, boolean, kotlin.jvm.functions.Function1, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, ru.mail.util.SakErrorToastShowDelegate):void A[MD:(boolean, boolean, boolean, ru.mail.credentialsexchanger.FromScreen, ru.mail.analytics.MailAppAnalytics, boolean, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, boolean, android.view.View$OnClickListener, boolean, boolean, kotlin.jvm.functions.Function1<? super ru.mail.authorizationsdk.feature.socialauth.domain.SocialAuthInitMode, kotlin.Unit>, boolean, ru.mail.ui.auth.universal.SmallLoginButtonView$Listener, ru.mail.util.SakErrorToastShowDelegate):void (m)] (LINE:3) in method: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.initVKID(boolean):void, file: classes16.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
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
            	... 15 more
            */
        /*
            Method dump skipped, instruction units count: 277
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.initVKID(boolean):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initVKID$lambda$0(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment, View view) {
        leelooUniversalLoginScreenFragment.getAnalyticsKt().onQrLoginStarted();
        leelooUniversalLoginScreenFragment.openQrLoginPromo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initVKID$lambda$1(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment, SocialAuthInitMode mode) {
        Intrinsics.checkNotNullParameter(mode, "mode");
        FragmentActivity activity = leelooUniversalLoginScreenFragment.getActivity();
        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
        if (loginActivity != null) {
            loginActivity.startSocialAuth(mode);
        }
        return Unit.INSTANCE;
    }

    private final void initViews(View view) {
        View viewFindViewById = view.findViewById(R.id.full_agreements_force_auth);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.fullAgreementsTexts = (VkAuthAgreementsView) viewFindViewById;
        View viewFindViewById2 = view.findViewById(R.id.force_vkid_auth_stub);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
        this.forceVKIDAuthButtonViewStub = (ViewStub) viewFindViewById2;
        View viewFindViewById3 = view.findViewById(R.id.two_step_login_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(...)");
        LinearLayout linearLayout = (LinearLayout) viewFindViewById3;
        this.defaultAuthButtonsContainer = linearLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultAuthButtonsContainer");
            linearLayout = null;
        }
        View viewFindViewById4 = linearLayout.findViewById(R.id.default_auth_buttons);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(...)");
        this.defaultAuthButtons = (LinearLayout) viewFindViewById4;
    }

    private final boolean isShowingFullscreenError() {
        QrPromoLoginController qrPromoLoginController = new QrPromoLoginController();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        return qrPromoLoginController.isShowingError(fragmentActivityRequireActivity);
    }

    private final boolean isShowingQrPromo() {
        QrPromoLoginController qrPromoLoginController = new QrPromoLoginController();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        return qrPromoLoginController.isShowingPromo(fragmentActivityRequireActivity);
    }

    private final boolean isVKIDAutologinAvailable() {
        String login;
        String login2;
        boolean zIsVkEmailForwardingEnabled = getNewAuthorizationConfig().getSocialAuthConfig().isVkEmailForwardingEnabled();
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        boolean zIsVkIdAutoLoginEnabled = configuration.getSocialLoginConfig().isVkIdAutoLoginEnabled();
        boolean z10 = !zIsVkEmailForwardingEnabled || (login2 = getLogin()) == null || login2.length() == 0;
        boolean z11 = !getNewAuthorizationConfig().isVkBindInLoginEnable() || !getNewAuthorizationConfig().getSocialAuthConfig().isInitEnabled() || (login = getLogin()) == null || login.length() == 0;
        boolean z12 = this.isUserLoggedOutByHimself;
        Bundle arguments = getArguments();
        boolean z13 = arguments != null && MatchingExtKt.isFromVkApp(arguments);
        FragmentActivity activity = getActivity();
        MailRuLoginActivity mailRuLoginActivity = activity instanceof MailRuLoginActivity ? (MailRuLoginActivity) activity : null;
        return zIsVkIdAutoLoginEnabled && z11 && z10 && !z12 && !z13 && (mailRuLoginActivity != null && !mailRuLoginActivity.isDeeplinkOpened()) && isAdded() && CommonDataManager.from(getThemedContext()).getAccounts().isEmpty() && !getNewAuthorizationConfig().isAnyLoginEnabled();
    }

    private final boolean isVkDataAutofillAvailable() {
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        Configuration.SocialLoginConfig socialLoginConfig = configuration.getSocialLoginConfig();
        return socialLoginConfig.isVkDataAutofillEnabled() && !socialLoginConfig.isVkRegWithOnlyEmail();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchAutoLogin() {
        if (isVKIDAutologinAvailable()) {
            if (!SuperappKit.isInitialized()) {
                SakErrorToastShowDelegate errorToastShowDelegate = getErrorToastShowDelegate();
                Context contextRequireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                errorToastShowDelegate.showToast(contextRequireContext, SakErrorToastShowDelegate.Place.Autologin);
                return;
            }
            Fragment fragmentFindFragmentByTag = getParentFragmentManager().findFragmentByTag(MailRuLoginActivity.GOOGLE_ACCOUNT_DIALOG_TAG);
            if (fragmentFindFragmentByTag == null || !fragmentFindFragmentByTag.isVisible()) {
                startAutoLogin();
            } else {
                ExtensionsKt.setFragmentResultListener(this, GoogleAccountPermissionDialogResult.INSTANCE, new Function1() { // from class: ru.mail.ui.auth.universal.i
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LeelooUniversalLoginScreenFragment.launchAutoLogin$lambda$0(this.f98245a, (Unit) obj);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit launchAutoLogin$lambda$0(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        leelooUniversalLoginScreenFragment.startAutoLogin();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LogoAlphaKeyboardListener listener_delegate$lambda$0(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment) {
        return new LogoAlphaKeyboardListener(leelooUniversalLoginScreenFragment.backButton, leelooUniversalLoginScreenFragment.logoIcon);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreateView$lambda$1(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNull(motionEvent);
        leelooUniversalLoginScreenFragment.handleOnTouchEvent(motionEvent);
        return true;
    }

    private final void onListLogoClicked(String service, BackgroundTheme theme) {
        ChangeThemeResolver changeThemeResolver = this.changeThemeResolver;
        if (changeThemeResolver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("changeThemeResolver");
            changeThemeResolver = null;
        }
        changeThemeResolver.changeTheme(theme);
        String string = this.mLoginView.getText().toString();
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) string, '@', 0, false, 6, (Object) null);
        if (iIndexOf$default != -1) {
            string = StringsKt.replaceRange((CharSequence) string, new IntRange(iIndexOf$default, string.length() - 1), (CharSequence) "").toString();
        }
        int length = string.length();
        this.mLoginView.setText(string + service);
        this.mLoginView.setSelection(length);
        showKeyboard(this.mLoginView);
    }

    private final void openDefaultVKIDAuth() {
        getSocialAuthButtonsView().performDefaultVKIDClick();
    }

    private final void openQrLoginPromo() {
        BaseToolbarActivity.hideKeyboard(getActivity());
        QrPromoLoginController qrPromoLoginController = new QrPromoLoginController();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        qrPromoLoginController.showPromo(fragmentActivityRequireActivity);
    }

    private final void openRestoreFragment(String email, String failUrl) {
        boolean zIsInitEnabled = getNewAuthorizationConfig().getSocialAuthConfig().isInitEnabled();
        boolean zIsRecoveryEnabled = getNewAuthorizationConfig().getSocialAuthConfig().isRecoveryEnabled();
        if (zIsInitEnabled && zIsRecoveryEnabled) {
            startNewRecovery(email, failUrl);
        } else {
            startOldRecovery(email, failUrl);
        }
    }

    private final void scrollToCenter() {
        LogoDataProvider logoDataProvider = this.dataProvider;
        if (logoDataProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
            logoDataProvider = null;
        }
        int size = 1073741823 % logoDataProvider.getData().size();
        int i10 = 1073741823 - size;
        RecyclerView recyclerView = this.logoRecyclerView;
        if (recyclerView != null) {
            recyclerView.scrollToPosition(1073741822 - size);
        }
        RecyclerView recyclerView2 = this.logoRecyclerView;
        if (recyclerView2 != null) {
            recyclerView2.smoothScrollToPosition(i10);
        }
    }

    private final void setOffset(int i10) {
        this.offset.setValue(this, $$delegatedProperties[0], Integer.valueOf(i10));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setPersonalVKIDAuthUi$lambda$0(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment, String str, View view) {
        leelooUniversalLoginScreenFragment.getAnalytics().onForceVKIDAuthClicked();
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = null;
        leelooUniversalLoginScreenFragment.showAuthProgress(null);
        ForceVKIDAuthViewModel forceVKIDAuthViewModel2 = leelooUniversalLoginScreenFragment.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
        } else {
            forceVKIDAuthViewModel = forceVKIDAuthViewModel2;
        }
        forceVKIDAuthViewModel.getEmailAuthData(str);
    }

    private final void setUpRegistrationExperiments(View view, DTOConfiguration.Config.RegistrationExperiments config) {
        if (config.getLoginForgotPasswordHidden()) {
            this.mRestorePassword.setVisibility(8);
            View viewFindViewById = view.findViewById(R.id.add_account_container);
            ViewGroup.LayoutParams layoutParams = viewFindViewById.getLayoutParams();
            FrameLayout.LayoutParams layoutParams2 = layoutParams instanceof FrameLayout.LayoutParams ? (FrameLayout.LayoutParams) layoutParams : null;
            if (layoutParams2 != null) {
                layoutParams2.gravity = 17;
            }
            if (layoutParams2 != null) {
                layoutParams2.height = viewFindViewById.getResources().getDimensionPixelSize(R.dimen.leeloo_login_screen_btn_height);
            }
            View viewFindViewById2 = view.findViewById(R.id.bottom_container);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(...)");
            UiExtensionsKt.setMargins$default(viewFindViewById2, 0, 0, 0, 0, 13, null);
        }
        if (config.getLoginForgotPasswordHiddenWithNewCreateButton()) {
            view.findViewById(R.id.bottom_container).setVisibility(8);
            view.findViewById(R.id.bottom_container_exp).setVisibility(0);
        }
    }

    private final void showUiIfError() {
        if (this.isHideUiOnStart) {
            this.isHideUiOnStart = false;
            View view = getView();
            if (view != null) {
                view.setVisibility(0);
            }
        }
    }

    private final void startAutoLogin() {
        showAutoLoginProgress();
        FragmentActivity activity = getActivity();
        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
        if (loginActivity != null) {
            loginActivity.startVKAutoLogin(new Function1() { // from class: ru.mail.ui.auth.universal.b
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LeelooUniversalLoginScreenFragment.startAutoLogin$lambda$0(this.f98230a, obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startAutoLogin$lambda$0(LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment, Object obj) {
        if ((obj instanceof AutoLoginProvider.Success) && leelooUniversalLoginScreenFragment.getNewAuthorizationConfig().isABExperiment()) {
            leelooUniversalLoginScreenFragment.autologinSuccessCount++;
            leelooUniversalLoginScreenFragment.getAnalyticsKt().onAutologinInExperimentStart(leelooUniversalLoginScreenFragment.autologinSuccessCount);
        }
        leelooUniversalLoginScreenFragment.dismissProgress();
        return Unit.INSTANCE;
    }

    private final void startEsiaAuthFlowInNewSdk(FromScreen fromScreen) {
        if (!getNewAuthorizationConfig().getSocialAuthConfig().isEsiaEnabled()) {
            EsiaAuthViewModel esiaAuthViewModel = this.esiaAuthViewModel;
            if (esiaAuthViewModel != null) {
                esiaAuthViewModel.onEsiaAuthClicked(fromScreen);
                return;
            }
            return;
        }
        SocialAuthInitMode.EsiaAuthClick esiaAuthClick = new SocialAuthInitMode.EsiaAuthClick(fromScreen);
        FragmentActivity activity = getActivity();
        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
        if (loginActivity != null) {
            loginActivity.startSocialAuth(esiaAuthClick);
        }
    }

    private final void startNewRecovery(String email, String failUrl) {
        FragmentActivity activity = getActivity();
        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
        if (loginActivity != null) {
            loginActivity.startVkEmailRecoveryFlow(email, failUrl);
        }
    }

    private final void startOldRecovery(String email, String failUrl) {
        RestoreVkEmailHelper restoreVkEmailHelper = this.restoreHelper;
        if (restoreVkEmailHelper != null) {
            restoreVkEmailHelper.showGoToRestoreFragment(getNewAuthorizationConfig().getSocialAuthConfig().isRestoreVkidInChoiceAndForceAuthFlowOldAuthEnabled(), email, failUrl, true, new RestoreVkidCallback() { // from class: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.startOldRecovery.1
                @Override // ru.mail.credentialsexchanger.unblockvkusers.RestoreVkidCallback
                public void onRestoreVkidRequested(String email2) {
                    Intrinsics.checkNotNullParameter(email2, "email");
                    FragmentActivity activity = LeelooUniversalLoginScreenFragment.this.getActivity();
                    LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
                    if (loginActivity != null) {
                        loginActivity.startRestoreVkidOld(email2, RestoreVkidStartSource.FORCE_VK_AUTH);
                    }
                }
            });
        }
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected boolean canFocusLoginView() {
        if (isShowingQrPromo() || isShowingFullscreenError()) {
            return false;
        }
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        return !configuration.isHideKeyboardOnLoginScreen();
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
    public final MailAnalyticsKt getAnalyticsKt() {
        MailAnalyticsKt mailAnalyticsKt = this.analyticsKt;
        if (mailAnalyticsKt != null) {
            return mailAnalyticsKt;
        }
        Intrinsics.throwUninitializedPropertyAccessException("analyticsKt");
        return null;
    }

    @NotNull
    public final ConfigurationRepository getConfigRepository() {
        ConfigurationRepository configurationRepository = this.configRepository;
        if (configurationRepository != null) {
            return configurationRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("configRepository");
        return null;
    }

    @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment
    @Nullable
    protected View getCreateEmailLayout(@Nullable View view) {
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        if (!configuration.getRegistrationExpsConfig().getLoginForgotPasswordHiddenWithNewCreateButton()) {
            return super.getCreateEmailLayout(view);
        }
        if (view != null) {
            return view.findViewById(R.id.add_account_container_exp);
        }
        return null;
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

    @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment
    @NotNull
    protected KeyboardVisibilityHelper.KeyboardVisibilityListener getKeyboardListener() {
        return getListener();
    }

    @NotNull
    public final UserBoundByVKIDDelegate getMUserBoundByVKIDDelegate() {
        UserBoundByVKIDDelegate userBoundByVKIDDelegate = this.mUserBoundByVKIDDelegate;
        if (userBoundByVKIDDelegate != null) {
            return userBoundByVKIDDelegate;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mUserBoundByVKIDDelegate");
        return null;
    }

    @NotNull
    public final NewAuthorizationSdkConfig getNewAuthorizationConfig() {
        NewAuthorizationSdkConfig newAuthorizationSdkConfig = this.newAuthorizationConfig;
        if (newAuthorizationSdkConfig != null) {
            return newAuthorizationSdkConfig;
        }
        Intrinsics.throwUninitializedPropertyAccessException("newAuthorizationConfig");
        return null;
    }

    @NotNull
    public final SharedPreferencesProvider getSharedPreferencesProvider() {
        SharedPreferencesProvider sharedPreferencesProvider = this.sharedPreferencesProvider;
        if (sharedPreferencesProvider != null) {
            return sharedPreferencesProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sharedPreferencesProvider");
        return null;
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

    @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment, ru.mail.ui.auth.MailTwoStepLoginScreenFragment
    protected void initScreens() {
        EnumMap<TwoStepAuthPresenter.View.Step, ScreenState> mScreens = this.mScreens;
        Intrinsics.checkNotNullExpressionValue(mScreens, "mScreens");
        mScreens.put(TwoStepAuthPresenter.View.Step.PASSWORD, getUniversalPasswordScreen());
        EnumMap<TwoStepAuthPresenter.View.Step, ScreenState> mScreens2 = this.mScreens;
        Intrinsics.checkNotNullExpressionValue(mScreens2, "mScreens");
        mScreens2.put(TwoStepAuthPresenter.View.Step.PASSWORD_WITHOUT_RESTORE, new LeelooUniversalPasswordWithoutRestoreScreen());
        EnumMap<TwoStepAuthPresenter.View.Step, ScreenState> mScreens3 = this.mScreens;
        Intrinsics.checkNotNullExpressionValue(mScreens3, "mScreens");
        mScreens3.put(TwoStepAuthPresenter.View.Step.PASSWORD_WITH_SMS, new LeelooUniversalPasswordWithSmsScreen());
        EnumMap<TwoStepAuthPresenter.View.Step, ScreenState> mScreens4 = this.mScreens;
        Intrinsics.checkNotNullExpressionValue(mScreens4, "mScreens");
        mScreens4.put(TwoStepAuthPresenter.View.Step.LOGIN, getUniversalLoginScreen());
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            showUiIfError();
        }
    }

    @Override // ru.mail.auth.BaseAuthFragment
    protected void onAuthSucceeded(@Nullable Bundle options) {
        super.onAuthSucceeded(options);
        if (isFromSocialBind()) {
            String stringBindType = getStringBindType();
            if (!Intrinsics.areEqual(stringBindType, CredentialsExchanger.STRING_VK_BIND)) {
                if (Intrinsics.areEqual(stringBindType, CredentialsExchanger.STRING_ESIA_BIND)) {
                    getAnalytics().onSuccessEsiaBind();
                    return;
                }
                return;
            } else {
                getMUserBoundByVKIDDelegate().setBindState(UserBoundByVKIDDelegate.BindState.Success.INSTANCE);
                MailAppAnalytics analytics = getAnalytics();
                String login = getLogin();
                Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
                analytics.onSuccessVkBind(login, "auth");
                return;
            }
        }
        SocialLoginInfoHolder.BindState bindState = SocialLoginInfoHolder.getBindState();
        if (bindState != null) {
            Configuration configuration = this.config;
            if (configuration == null) {
                Intrinsics.throwUninitializedPropertyAccessException("config");
                configuration = null;
            }
            if (DomainUtilsKt.isVkBindInLogin(bindState, configuration.getSocialLoginConfig())) {
                getMUserBoundByVKIDDelegate().setBindState(UserBoundByVKIDDelegate.BindState.Success.INSTANCE);
                MailAppAnalytics analytics2 = getAnalytics();
                String login2 = getLogin();
                Intrinsics.checkNotNullExpressionValue(login2, "getLogin(...)");
                analytics2.onSuccessVkBind(login2, "VKID_BIND_IN_LOGIN");
            }
        }
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, ru.mail.auth.BaseAuthFragment
    public void onBadAuth(@Nullable Bundle result) {
        if (result == null || !Intrinsics.areEqual("QR_LOGIN", result.getString(MailLoginFragment.EXTRA_ACCOUNT_TYPE))) {
            super.onBadAuth(result);
            return;
        }
        hideKeyboard();
        QrPromoLoginController qrPromoLoginController = new QrPromoLoginController();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        qrPromoLoginController.showError(fragmentActivityRequireActivity);
        getAnalyticsKt().onQrLoginAuthError();
    }

    @Override // ru.mail.ui.auth.universal.SmallLoginButtonView.Listener
    public void onButtonClicked(@NotNull FromScreen fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        getAnalytics().onClickEsiaButton(fromScreen.name());
        LOG.d("Esia button clicked. Screen = " + fromScreen.name());
        if (getNewAuthorizationConfig().getSocialAuthConfig().isInitEnabled()) {
            startEsiaAuthFlowInNewSdk(fromScreen);
            return;
        }
        EsiaAuthViewModel esiaAuthViewModel = this.esiaAuthViewModel;
        if (esiaAuthViewModel != null) {
            esiaAuthViewModel.onEsiaAuthClicked(fromScreen);
        }
    }

    @Override // ru.mail.ui.auth.universal.SmallLoginButtonView.Listener
    public void onButtonShown(@NotNull FromScreen fromScreen) {
        Intrinsics.checkNotNullParameter(fromScreen, "fromScreen");
        LOG.d("Esia button shown. Screen = " + fromScreen.name());
        getAnalytics().onShowEsiaButton(fromScreen.name());
    }

    @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment, ru.mail.ui.auth.MailTwoStepLoginScreenFragment
    protected void onClickOnProcessToSecondStep() {
        if (isFromSocialBind()) {
            ForceVKIDAuthViewModel forceVKIDAuthViewModel = this.forceVKIDAuthViewModel;
            if (forceVKIDAuthViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
                forceVKIDAuthViewModel = null;
            }
            String login = getLogin();
            Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
            if (forceVKIDAuthViewModel.isEmailInList(login)) {
                showAuthError(getString(R.string.error_code_603_vk));
                return;
            }
        }
        super.onClickOnProcessToSecondStep();
    }

    @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment
    protected void onClickRegistration() {
        this.mCreateEmailLayout.setEnabled(false);
        if (!this.mSocialAuthButtonsView.getIsUserLoaded() || !isVkDataAutofillAvailable()) {
            super.onClickRegistration();
            return;
        }
        showEmptyProgress(null);
        OneTapRegViewModel oneTapRegViewModel = this.oneTapRegViewModel;
        if (oneTapRegViewModel != null) {
            oneTapRegViewModel.startRegWithVkId(CredentialsExchanger.SocialBindType.VK_WITHOUT_BIND);
        }
    }

    @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment, ru.mail.ui.auth.MailTwoStepLoginScreenFragment, ru.mail.auth.BaseLoginScreenFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        String string;
        super.onCreate(savedInstanceState);
        boolean z10 = false;
        this.isAutologinAlreadyExecuted = savedInstanceState != null ? savedInstanceState.getBoolean(IS_AUTOLOGIN_ALREADY_EXECUTED) : false;
        this.autologinSuccessCount = savedInstanceState != null ? savedInstanceState.getInt(AUTOLOGIN_SUCCESS_COUNT, 0) : 0;
        ConfigurationWithRawData configuration = getConfigRepository().getConfiguration();
        Intrinsics.checkNotNullExpressionValue(configuration, "getConfiguration(...)");
        this.config = configuration;
        Object objCheckedCastTo = CastUtils.checkedCastTo(requireActivity(), AuthMessageCallback.class);
        Intrinsics.checkNotNullExpressionValue(objCheckedCastTo, "checkedCastTo(...)");
        this.authDelegate = new AuthMessageCallbackWrapper((AuthMessageCallback) objCheckedCastTo);
        this.forceVKIDAuthViewModel = (ForceVKIDAuthViewModel) ViewModelObtainerKt.obtainViewModel(this, ForceVKIDAuthViewModel.class, this);
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        this.esiaAuthViewModel = (EsiaAuthViewModel) new ViewModelProvider(fragmentActivityRequireActivity).get(EsiaAuthViewModel.class);
        if (isVkDataAutofillAvailable()) {
            this.oneTapRegViewModel = (OneTapRegViewModel) ViewModelObtainerKt.obtainViewModel(this, OneTapRegViewModel.class, this);
        }
        if (savedInstanceState != null) {
            z10 = savedInstanceState.getBoolean(Authenticator.IS_HIDE_UI_ON_START, false);
        } else {
            Bundle arguments = getArguments();
            if (arguments != null) {
                z10 = arguments.getBoolean(Authenticator.IS_HIDE_UI_ON_START, false);
            }
        }
        this.isHideUiOnStart = z10;
        if (savedInstanceState == null || (string = savedInstanceState.getString("login_extra_xmail_migration_from")) == null) {
            Bundle arguments2 = getArguments();
            string = arguments2 != null ? arguments2.getString("login_extra_xmail_migration_from", "") : null;
        }
        boolean zIsXmailMigrationExceptLogin = MailAccountConstants.isXmailMigrationExceptLogin(string);
        this.isXmailMigrationFromNotLogin = zIsXmailMigrationExceptLogin;
        if (zIsXmailMigrationExceptLogin) {
            this.isHideUiOnStart = true;
        }
    }

    @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment, ru.mail.ui.auth.MailTwoStepLoginScreenFragment, ru.mail.auth.BaseLoginScreenFragment, androidx.fragment.app.Fragment
    @SuppressLint({"ClickableViewAccessibility"})
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RestoreVkEmailHelper helper;
        ImageView imageView;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Configuration configuration = this.config;
        Configuration configuration2 = null;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        SocialAuthModuleConfig socialAuthModuleConfig = configuration.getNewAuthSdkConfig().getSocialAuthModuleConfig();
        SocialAuthConfig socialAuthConfig = getNewAuthorizationConfig().getSocialAuthConfig();
        if (socialAuthModuleConfig.isSocialAuthModuleEnable() || socialAuthConfig.isInitEnabled()) {
            FragmentActivity fragmentActivityRequireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
            helper = new RestoreVkEmailNavigator(fragmentActivityRequireActivity, container).getHelper();
        } else {
            KeyEventDispatcher.Component activity = getActivity();
            RestoreVkEmailHelperHolder restoreVkEmailHelperHolder = activity instanceof RestoreVkEmailHelperHolder ? (RestoreVkEmailHelperHolder) activity : null;
            helper = restoreVkEmailHelperHolder != null ? restoreVkEmailHelperHolder.getRestoreVkEmailHelper() : null;
        }
        this.restoreHelper = helper;
        View viewOnCreateView = super.onCreateView(inflater, container, savedInstanceState);
        Intrinsics.checkNotNull(viewOnCreateView);
        this.logoIcon = (ImageView) viewOnCreateView.findViewById(R.id.icon);
        this.backButton = (ImageView) viewOnCreateView.findViewById(R.id.back_button);
        this.helpIcon = (ImageView) viewOnCreateView.findViewById(R.id.help_button);
        this.root = (FrameLayout) viewOnCreateView.findViewById(R.id.container);
        setOffset(requireContext().getResources().getDimensionPixelSize(R.dimen.leeloo_logo_login_recycleview_offset));
        Object objCheckedCastTo = CastUtils.checkedCastTo(getActivity(), ChangeThemeResolver.class);
        Intrinsics.checkNotNullExpressionValue(objCheckedCastTo, "checkedCastTo(...)");
        this.changeThemeResolver = (ChangeThemeResolver) objCheckedCastTo;
        ImageView imageView2 = this.backButton;
        if (imageView2 != null) {
            imageView2.setColorFilter(ContextCompat.getColor(requireActivity(), R.color.colorIconPrimary), PorterDuff.Mode.MULTIPLY);
        }
        if (!BuildVariantHelper.isOnPremise()) {
            initLogosAdapter(viewOnCreateView);
        }
        if (BuildVariantHelper.isMailRu() && (imageView = this.logoIcon) != null) {
            imageView.setImageResource(R.drawable.ic_vkid_rumail_logo_big);
        }
        ImageView imageView3 = this.backButton;
        if (imageView3 != null) {
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: ru.mail.ui.auth.universal.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f98243a.onToolbarBack();
                }
            });
        }
        ImageView imageView4 = this.logoIcon;
        if (imageView4 != null) {
            imageView4.setOnTouchListener(new View.OnTouchListener() { // from class: ru.mail.ui.auth.universal.h
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return LeelooUniversalLoginScreenFragment.onCreateView$lambda$1(this.f98244a, view, motionEvent);
                }
            });
        }
        initViews(viewOnCreateView);
        initVKID(isFromSocialBind());
        initForceAuthByVKID();
        viewOnCreateView.setVisibility(this.isHideUiOnStart ? 4 : 0);
        Configuration configuration3 = this.config;
        if (configuration3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        } else {
            configuration2 = configuration3;
        }
        setUpRegistrationExperiments(viewOnCreateView, configuration2.getRegistrationExpsConfig());
        CallbackHolder.INSTANCE.setWebAuthNCallback(new CustomTabWebAuthNHelper.WebAuthNCallback() { // from class: ru.mail.ui.auth.universal.LeelooUniversalLoginScreenFragment.onCreateView.3
            @Override // ru.mail.auth.webview.CustomTabWebAuthNHelper.WebAuthNCallback
            public void onResult(CustomTabWebAuthNHelper.WebAuthNAuthResult result) {
                Intrinsics.checkNotNullParameter(result, "result");
                if (!(result instanceof CustomTabWebAuthNHelper.WebAuthNAuthResult.Success)) {
                    if (result instanceof CustomTabWebAuthNHelper.WebAuthNAuthResult.Fail) {
                        LeelooUniversalLoginScreenFragment.this.getAnalytics().onFailedRedirectWebAuthNAuthorization(((CustomTabWebAuthNHelper.WebAuthNAuthResult.Fail) result).getError());
                        CallbackHolder.INSTANCE.setBackFromChromeTabs(true);
                        LeelooUniversalLoginScreenFragment leelooUniversalLoginScreenFragment = LeelooUniversalLoginScreenFragment.this;
                        leelooUniversalLoginScreenFragment.showStep(leelooUniversalLoginScreenFragment.getLogin(), TwoStepAuthPresenter.View.Step.PASSWORD);
                        return;
                    }
                    return;
                }
                LeelooUniversalLoginScreenFragment.this.getAnalytics().onSuccessWebAuthNAuthorization();
                String token = ((CustomTabWebAuthNHelper.WebAuthNAuthResult.Success) result).getToken();
                Context contextRequireContext = LeelooUniversalLoginScreenFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
                String strCreateUriFromResources = UtilExtensionsKt.createUriFromResources(contextRequireContext, R.string.swa_default_scheme, R.string.swa_default_host, "/cgi-bin/auth?Login=" + LeelooUniversalLoginScreenFragment.this.getLogin() + "&token=" + token);
                Bundle bundle = new Bundle();
                bundle.putString("authAccount", LeelooUniversalLoginScreenFragment.this.getLogin());
                bundle.putString("password", token);
                Bundle bundle2 = new Bundle();
                bundle2.putString(MailAccountConstants.LOGIN_EXTRA_CGI_BIN_AUTH_URL, strCreateUriFromResources);
                bundle2.putString(MailAccountConstants.LOGIN_EXTRA_CGI_BIN_AUTH_EMAIL, LeelooUniversalLoginScreenFragment.this.getLogin());
                bundle.putString(AuthenticatorConstantsClass.MAILRU_ACCOUNT_TYPE, Authenticator.Type.WEB_AUTH_N.toString());
                bundle.putBundle(BaseAuthActivity.EXTRA_BUNDLE, bundle2);
                LeelooUniversalLoginScreenFragment.this.getAuthCallBack().onMessageHandle(new Message(Message.Id.AUTHENTICATE, bundle));
                CallbackHolder.INSTANCE.setBackFromChromeTabs(false);
            }
        });
        initHelpIcon();
        return viewOnCreateView;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        SocialAuthButtonsView socialAuthButtonsView = this.mSocialAuthButtonsView;
        if (socialAuthButtonsView != null) {
            socialAuthButtonsView.detachListener();
        }
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = this.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
            forceVKIDAuthViewModel = null;
        }
        forceVKIDAuthViewModel.resetInteractorState();
    }

    @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.restoreHelper = null;
        SocialAuthButtonsView socialAuthButtonsView = getSocialAuthButtonsView();
        if (socialAuthButtonsView != null) {
            socialAuthButtonsView.onDestroy();
        }
        CallbackHolder callbackHolder = CallbackHolder.INSTANCE;
        callbackHolder.clear();
        callbackHolder.setWasRotated(true);
    }

    @Override // ru.mail.ui.auth.universal.LogoClickListener
    public void onLogoClick(int position, @NotNull String service, @NotNull BackgroundTheme theme) {
        Intrinsics.checkNotNullParameter(service, "service");
        Intrinsics.checkNotNullParameter(theme, "theme");
        RecyclerView recyclerView = this.logoRecyclerView;
        if (recyclerView != null) {
            recyclerView.smoothScrollToPosition(position);
        }
        FragmentActivity activity = getActivity();
        LoginActivity loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
        getAnalytics().logoListClick(service, loginActivity != null ? loginActivity.getExtraFrom() : null);
        onListLogoClicked(service, theme);
    }

    @Override // ru.mail.ui.auth.universal.UniversalLoginScreenFragment, ru.mail.ui.auth.MailTwoStepLoginScreenFragment, ru.mail.auth.BaseLoginScreenFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = this.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
            forceVKIDAuthViewModel = null;
        }
        forceVKIDAuthViewModel.getVKIDEmailsListAgain();
        getUserCancelEsiaAuthDelegate().trackUserCancelledEsiaAuth(DefaultAuthRouter.KEY_LOGIN);
        this.mCreateEmailLayout.setEnabled(true);
        SocialAuthButtonsView socialAuthButtonsView = getSocialAuthButtonsView();
        Intrinsics.checkNotNullExpressionValue(socialAuthButtonsView, "getSocialAuthButtonsView(...)");
        SocialAuthButtonsView.onResume$default(socialAuthButtonsView, null, 1, null);
        CallbackHolder callbackHolder = CallbackHolder.INSTANCE;
        if (callbackHolder.isBackFromChromeTabs() && !callbackHolder.getWasRotated()) {
            proceedToSecondStep();
            getAnalytics().onWebAuthNAuthNAuthorizationClosed();
            return;
        }
        callbackHolder.setWasRotated(false);
        if (this.isAutologinAlreadyExecuted && getNewAuthorizationConfig().isOldAutologinRunOnlyOnceEnabled()) {
            return;
        }
        this.isAutologinAlreadyExecuted = true;
        View view = getView();
        if (view != null) {
            view.post(new Runnable() { // from class: ru.mail.ui.auth.universal.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f98233a.launchAutoLogin();
                }
            });
        }
    }

    @Override // ru.mail.ui.auth.MailTwoStepLoginScreenFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NotNull Bundle outState) {
        Intrinsics.checkNotNullParameter(outState, "outState");
        super.onSaveInstanceState(outState);
        outState.putBoolean(Authenticator.IS_HIDE_UI_ON_START, this.isHideUiOnStart);
        outState.putBoolean(IS_AUTOLOGIN_ALREADY_EXECUTED, this.isAutologinAlreadyExecuted);
        outState.putInt(AUTOLOGIN_SUCCESS_COUNT, this.autologinSuccessCount);
        outState.putBoolean("login_extra_xmail_migration_from", this.isXmailMigrationFromNotLogin);
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, ru.mail.auth.BaseAuthFragment
    protected void onShowAuthError(@Nullable String errorMessage, boolean isNeedShowToast) {
        if (this.isXmailMigrationFromNotLogin && isNeedShowToast && errorMessage != null) {
            Toast.makeText(getActivity(), errorMessage, 0).show();
        }
        showUiIfError();
        this.mPasswordErrorDelegate.showError(errorMessage);
        this.mLoginErrorDelegate.showError(errorMessage);
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, ru.mail.auth.BaseAuthFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getSocialAuthButtonsView().onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null || TextUtils.isEmpty(this.mLoginExtra)) {
            return;
        }
        String mLoginExtra = this.mLoginExtra;
        Intrinsics.checkNotNullExpressionValue(mLoginExtra, "mLoginExtra");
        changeThemeIfValid(mLoginExtra);
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = this.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
            forceVKIDAuthViewModel = null;
        }
        String mLoginExtra2 = this.mLoginExtra;
        Intrinsics.checkNotNullExpressionValue(mLoginExtra2, "mLoginExtra");
        forceVKIDAuthViewModel.setCheckEmailAfterLoadEmailsList(mLoginExtra2);
    }

    @Override // ru.mail.auth.ForceVkidAuthListener
    public void proceedToSecondStep() {
        Bundle arguments = getArguments();
        this.mTwoStepAuthPresenter.onProceedToSecondStep(getLogin(), getFrom(), true, arguments != null ? arguments.getBoolean(Authenticator.SHOULD_CHECK_LOGIN_STATUS, true) : true);
    }

    public final void setAnalytics(@NotNull MailAppAnalytics mailAppAnalytics) {
        Intrinsics.checkNotNullParameter(mailAppAnalytics, "<set-?>");
        this.analytics = mailAppAnalytics;
    }

    public final void setAnalyticsKt(@NotNull MailAnalyticsKt mailAnalyticsKt) {
        Intrinsics.checkNotNullParameter(mailAnalyticsKt, "<set-?>");
        this.analyticsKt = mailAnalyticsKt;
    }

    public final void setConfigRepository(@NotNull ConfigurationRepository configurationRepository) {
        Intrinsics.checkNotNullParameter(configurationRepository, "<set-?>");
        this.configRepository = configurationRepository;
    }

    @Override // ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel.View
    public void setDefaultUi() {
        getSocialAuthButtonsView().setVisibility(isFromSocialBind() ? 8 : 0);
        LinearLayout linearLayout = this.defaultAuthButtons;
        VkAuthAgreementsView vkAuthAgreementsView = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultAuthButtons");
            linearLayout = null;
        }
        linearLayout.setVisibility(0);
        VkFastLoginButton vkFastLoginButton = this.forceAuthByVKIDButton;
        if (vkFastLoginButton != null) {
            vkFastLoginButton.setVisibility(8);
        }
        VkAuthAgreementsView vkAuthAgreementsView2 = this.fullAgreementsTexts;
        if (vkAuthAgreementsView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullAgreementsTexts");
        } else {
            vkAuthAgreementsView = vkAuthAgreementsView2;
        }
        vkAuthAgreementsView.setVisibility(8);
    }

    @Override // ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel.View
    public void setErrorAuthUi() {
        dismissProgress();
        openDefaultVKIDAuth();
    }

    public final void setErrorToastShowDelegate(@NotNull SakErrorToastShowDelegate sakErrorToastShowDelegate) {
        Intrinsics.checkNotNullParameter(sakErrorToastShowDelegate, "<set-?>");
        this.errorToastShowDelegate = sakErrorToastShowDelegate;
    }

    public final void setMUserBoundByVKIDDelegate(@NotNull UserBoundByVKIDDelegate userBoundByVKIDDelegate) {
        Intrinsics.checkNotNullParameter(userBoundByVKIDDelegate, "<set-?>");
        this.mUserBoundByVKIDDelegate = userBoundByVKIDDelegate;
    }

    public final void setNewAuthorizationConfig(@NotNull NewAuthorizationSdkConfig newAuthorizationSdkConfig) {
        Intrinsics.checkNotNullParameter(newAuthorizationSdkConfig, "<set-?>");
        this.newAuthorizationConfig = newAuthorizationSdkConfig;
    }

    @Override // ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel.View
    public void setPersonalVKIDAuthUi(@NotNull final String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        getSocialAuthButtonsView().setVisibility(8);
        LinearLayout linearLayout = this.defaultAuthButtons;
        VkAuthAgreementsView vkAuthAgreementsView = null;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultAuthButtons");
            linearLayout = null;
        }
        linearLayout.setVisibility(8);
        VkFastLoginButton vkFastLoginButton = this.forceAuthByVKIDButton;
        if (vkFastLoginButton != null) {
            vkFastLoginButton.setVisibility(0);
        }
        VkAuthAgreementsView vkAuthAgreementsView2 = this.fullAgreementsTexts;
        if (vkAuthAgreementsView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fullAgreementsTexts");
        } else {
            vkAuthAgreementsView = vkAuthAgreementsView2;
        }
        vkAuthAgreementsView.setVisibility(0);
        VkFastLoginButton vkFastLoginButton2 = this.forceAuthByVKIDButton;
        if (vkFastLoginButton2 != null) {
            vkFastLoginButton2.setOnClickListener(new View.OnClickListener() { // from class: ru.mail.ui.auth.universal.j
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LeelooUniversalLoginScreenFragment.setPersonalVKIDAuthUi$lambda$0(this.f98246a, email, view);
                }
            });
        }
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected void setRestorePwdViewEnabled(boolean forceDismiss) {
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        if (configuration.getRegistrationExpsConfig().getLoginForgotPasswordHidden()) {
            this.mRestorePassword.setVisibility(8);
        } else {
            super.setRestorePwdViewEnabled(forceDismiss);
        }
    }

    public final void setSharedPreferencesProvider(@NotNull SharedPreferencesProvider sharedPreferencesProvider) {
        Intrinsics.checkNotNullParameter(sharedPreferencesProvider, "<set-?>");
        this.sharedPreferencesProvider = sharedPreferencesProvider;
    }

    public final void setUserCancelEsiaAuthDelegate(@NotNull UserCancelEsiaAuthDelegate userCancelEsiaAuthDelegate) {
        Intrinsics.checkNotNullParameter(userCancelEsiaAuthDelegate, "<set-?>");
        this.userCancelEsiaAuthDelegate = userCancelEsiaAuthDelegate;
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment
    protected void showAuthErrorAsToast(@Nullable String errorMessage, boolean isLong) {
        showUiIfError();
        notifyActivityOnError();
        ErrorDelegate errorDelegate = this.mPasswordErrorDelegate;
        if (errorDelegate != null) {
            errorDelegate.showError(errorMessage);
        }
        ErrorDelegate errorDelegate2 = this.mLoginErrorDelegate;
        if (errorDelegate2 != null) {
            errorDelegate2.showError(errorMessage);
        }
    }

    @Override // ru.mail.auth.BaseLoginScreenFragment, ru.mail.ui.auth.TwoStepAuthPresenter.View
    public void showRestorePasswordScreen(@Nullable String loginForRestore) {
        LoginActivity loginActivity;
        Log log = LOG;
        log.d("click restore password");
        SocialAuthConfig socialAuthConfig = getNewAuthorizationConfig().getSocialAuthConfig();
        if (socialAuthConfig.isRestoreVkidInOldAuthEnabled()) {
            log.d("initiate old flow");
            FragmentActivity activity = getActivity();
            loginActivity = activity instanceof LoginActivity ? (LoginActivity) activity : null;
            if (loginActivity != null) {
                loginActivity.startRestoreVkidOld(loginForRestore, RestoreVkidStartSource.FORGET_PASSWORD);
                return;
            }
            return;
        }
        if (!socialAuthConfig.isRestoreVkidEnabled()) {
            log.d("initiate default flow");
            super.showRestorePasswordScreen(loginForRestore);
            return;
        }
        log.d("initiate new flow");
        FragmentActivity activity2 = getActivity();
        loginActivity = activity2 instanceof LoginActivity ? (LoginActivity) activity2 : null;
        if (loginActivity != null) {
            loginActivity.startRestoreVkid(loginForRestore, RestoreVkidStartSource.FORGET_PASSWORD);
        }
    }

    @Override // ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel.View
    public void startAuth(@NotNull ForceVKIDAuthInteractor.VkIdAuthState.ReadyForLoginState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        getAnalytics().onSuccessForceVKIDAuth();
        AuthMessageCallbackWrapper authMessageCallbackWrapper = this.authDelegate;
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = null;
        if (authMessageCallbackWrapper == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authDelegate");
            authMessageCallbackWrapper = null;
        }
        Account accountMapForLogin = state.getAccount().mapForLogin();
        String silentToken = state.getSilentToken();
        Configuration configuration = this.config;
        if (configuration == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
            configuration = null;
        }
        authMessageCallbackWrapper.startAuth(accountMapForLogin, silentToken, configuration.getSocialLoginConfig().isResetSoftVkidBindEnabled());
        ForceVKIDAuthViewModel forceVKIDAuthViewModel2 = this.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
        } else {
            forceVKIDAuthViewModel = forceVKIDAuthViewModel2;
        }
        forceVKIDAuthViewModel.resetInteractorState();
    }

    @Override // ru.mail.ui.auth.universal.esia.EsiaFlowProvider
    public void startEsiaFlow(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        onButtonClicked(FromScreen.LOGIN);
    }

    @Override // ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel.View
    public void startGettingAuthDataAgain(@NotNull String email) {
        Intrinsics.checkNotNullParameter(email, "email");
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = this.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
            forceVKIDAuthViewModel = null;
        }
        forceVKIDAuthViewModel.getEmailAuthData(email);
    }

    @Override // ru.mail.ui.auth.universal.forceauthorizationbyvkid.ForceVKIDAuthViewModel.View
    public void startRecovery(@NotNull ForceVKIDAuthInteractor.VkIdAuthState.GetBlockedAuthDataState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        openRestoreFragment(state.getAccount().getLogin(), state.getAccount().getFailUrl());
        this.mLoginView.setText("");
        ForceVKIDAuthViewModel forceVKIDAuthViewModel = this.forceVKIDAuthViewModel;
        if (forceVKIDAuthViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("forceVKIDAuthViewModel");
            forceVKIDAuthViewModel = null;
        }
        forceVKIDAuthViewModel.resetInteractorState();
    }
}
