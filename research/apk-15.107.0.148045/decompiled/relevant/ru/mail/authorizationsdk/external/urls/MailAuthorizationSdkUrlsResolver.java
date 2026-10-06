package ru.mail.authorizationsdk.external.urls;

import androidx.annotation.StringRes;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.android_utils.extension.ContextKt;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.external.urls.MailAuthorizationSdkUrlsResolver;
import ru.mail.authorizationsdk.feature.oidcdiscovery.domain.OidcDiscoveryLocalUseCase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b`\n\u0002\u0010\b\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010j\u001a\u00020\u000b2\b\b\u0001\u0010k\u001a\u00020l2\b\b\u0001\u0010m\u001a\u00020lH\u0002J&\u0010n\u001a\u00020\u000b2\b\b\u0001\u0010o\u001a\u00020l2\b\b\u0001\u0010p\u001a\u00020l2\b\b\u0001\u0010q\u001a\u00020lH\u0002J\u001c\u0010r\u001a\u00020\u000b2\b\b\u0001\u0010q\u001a\u00020l2\b\b\u0001\u0010s\u001a\u00020lH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001b\u0010\u0010\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0011\u0010\rR\u001b\u0010\u0013\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u000f\u001a\u0004\b\u0014\u0010\rR\u001b\u0010\u0016\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u000f\u001a\u0004\b\u0017\u0010\rR\u001b\u0010\u0019\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001a\u0010\rR\u001b\u0010\u001c\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u000f\u001a\u0004\b\u001d\u0010\rR\u001b\u0010\u001f\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u000f\u001a\u0004\b \u0010\rR\u001b\u0010\"\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b$\u0010\u000f\u001a\u0004\b#\u0010\rR\u001b\u0010%\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u000f\u001a\u0004\b&\u0010\rR\u001b\u0010(\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b*\u0010\u000f\u001a\u0004\b)\u0010\rR\u001b\u0010+\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b-\u0010\u000f\u001a\u0004\b,\u0010\rR\u001b\u0010.\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b0\u0010\u000f\u001a\u0004\b/\u0010\rR\u001b\u00101\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b3\u0010\u000f\u001a\u0004\b2\u0010\rR\u001b\u00104\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b6\u0010\u000f\u001a\u0004\b5\u0010\rR\u001b\u00107\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b9\u0010\u000f\u001a\u0004\b8\u0010\rR\u001b\u0010:\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b<\u0010\u000f\u001a\u0004\b;\u0010\rR\u001b\u0010=\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b?\u0010\u000f\u001a\u0004\b>\u0010\rR\u001b\u0010@\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bB\u0010\u000f\u001a\u0004\bA\u0010\rR\u001b\u0010C\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bE\u0010\u000f\u001a\u0004\bD\u0010\rR\u001b\u0010F\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bH\u0010\u000f\u001a\u0004\bG\u0010\rR\u001b\u0010I\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bK\u0010\u000f\u001a\u0004\bJ\u0010\rR\u001b\u0010L\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bN\u0010\u000f\u001a\u0004\bM\u0010\rR\u001b\u0010O\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bQ\u0010\u000f\u001a\u0004\bP\u0010\rR\u001b\u0010R\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\u000f\u001a\u0004\bS\u0010\rR\u001b\u0010U\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bW\u0010\u000f\u001a\u0004\bV\u0010\rR\u001b\u0010X\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bZ\u0010\u000f\u001a\u0004\bY\u0010\rR\u001b\u0010[\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b]\u0010\u000f\u001a\u0004\b\\\u0010\rR\u001b\u0010^\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b`\u0010\u000f\u001a\u0004\b_\u0010\rR\u001b\u0010a\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bc\u0010\u000f\u001a\u0004\bb\u0010\rR\u001b\u0010d\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bf\u0010\u000f\u001a\u0004\be\u0010\rR\u001b\u0010g\u001a\u00020\u000b8VX\u0096\u0084\u0002¢\u0006\f\n\u0004\bi\u0010\u000f\u001a\u0004\bh\u0010\r¨\u0006t"}, d2 = {"Lru/mail/authorizationsdk/external/urls/MailAuthorizationSdkUrlsResolver;", "Lru/mail/authorizationsdk/external/urls/AuthorizationSdkUrlsResolver;", "resources", "Lru/mail/android_utils/wrapper/Resources;", "isMiniMail", "", "oidcDiscoveryLocalUseCase", "Lru/mail/authorizationsdk/feature/oidcdiscovery/domain/OidcDiscoveryLocalUseCase;", "<init>", "(Lru/mail/android_utils/wrapper/Resources;ZLru/mail/authorizationsdk/feature/oidcdiscovery/domain/OidcDiscoveryLocalUseCase;)V", "captchaHost", "", "getCaptchaHost", "()Ljava/lang/String;", "captchaHost$delegate", "Lkotlin/Lazy;", "authSwaUrl", "getAuthSwaUrl", "authSwaUrl$delegate", "authMailHost", "getAuthMailHost", "authMailHost$delegate", "authMailHostWithScheme", "getAuthMailHostWithScheme", "authMailHostWithScheme$delegate", "authMailUrl", "getAuthMailUrl", "authMailUrl$delegate", "prodAuthMailUrl", "getProdAuthMailUrl", "prodAuthMailUrl$delegate", "oAuthMailUrl", "getOAuthMailUrl", "oAuthMailUrl$delegate", "avatarUrl", "getAvatarUrl", "avatarUrl$delegate", "swaUrl", "getSwaUrl", "swaUrl$delegate", "oneTimeCodeUrl", "getOneTimeCodeUrl", "oneTimeCodeUrl$delegate", "doregCaptchaUrl", "getDoregCaptchaUrl", "doregCaptchaUrl$delegate", "restorePasswordUrl", "getRestorePasswordUrl", "restorePasswordUrl$delegate", "accountUrl", "getAccountUrl", "accountUrl$delegate", "yahooApiUrl", "getYahooApiUrl", "yahooApiUrl$delegate", "yahooMailRedirectUrl", "getYahooMailRedirectUrl", "yahooMailRedirectUrl$delegate", "yahooAuthServerUrl", "getYahooAuthServerUrl", "yahooAuthServerUrl$delegate", "yahooTokenServerUrl", "getYahooTokenServerUrl", "yahooTokenServerUrl$delegate", "yandexApiUrl", "getYandexApiUrl", "yandexApiUrl$delegate", "yandexMailRedirectUrl", "getYandexMailRedirectUrl", "yandexMailRedirectUrl$delegate", "yandexAuthServerUrl", "getYandexAuthServerUrl", "yandexAuthServerUrl$delegate", "yandexTokenServerUrl", "getYandexTokenServerUrl", "yandexTokenServerUrl$delegate", "outlookApiUrl", "getOutlookApiUrl", "outlookApiUrl$delegate", "outlookMailRedirectUrl", "getOutlookMailRedirectUrl", "outlookMailRedirectUrl$delegate", "outlookAuthServerUrl", "getOutlookAuthServerUrl", "outlookAuthServerUrl$delegate", "outlookTokenServerUrl", "getOutlookTokenServerUrl", "outlookTokenServerUrl$delegate", "googleApiUrl", "getGoogleApiUrl", "googleApiUrl$delegate", "googleMailRedirectUrl", "getGoogleMailRedirectUrl", "googleMailRedirectUrl$delegate", "googleAuthServerUrl", "getGoogleAuthServerUrl", "googleAuthServerUrl$delegate", "googleTokenServerUrl", "getGoogleTokenServerUrl", "googleTokenServerUrl$delegate", "ludwigUrl", "getLudwigUrl", "ludwigUrl$delegate", "captchaImitationHost", "getCaptchaImitationHost", "captchaImitationHost$delegate", "buildMiniMailOrDefaultUrl", "defaultUrlRes", "", "miniMailUrlRes", "buildMiniMailOrDefaultHostWithScheme", "defaultHostRes", "miniMailHostRes", "schemeRes", "buildUrl", "hostRes", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MailAuthorizationSdkUrlsResolver implements AuthorizationSdkUrlsResolver {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: accountUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy accountUrl;

    /* JADX INFO: renamed from: authMailHost$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy authMailHost;

    /* JADX INFO: renamed from: authMailHostWithScheme$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy authMailHostWithScheme;

    /* JADX INFO: renamed from: authMailUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy authMailUrl;

    /* JADX INFO: renamed from: authSwaUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy authSwaUrl;

    /* JADX INFO: renamed from: avatarUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy avatarUrl;

    /* JADX INFO: renamed from: captchaHost$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy captchaHost;

    /* JADX INFO: renamed from: captchaImitationHost$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy captchaImitationHost;

    /* JADX INFO: renamed from: doregCaptchaUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy doregCaptchaUrl;

    /* JADX INFO: renamed from: googleApiUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy googleApiUrl;

    /* JADX INFO: renamed from: googleAuthServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy googleAuthServerUrl;

    /* JADX INFO: renamed from: googleMailRedirectUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy googleMailRedirectUrl;

    /* JADX INFO: renamed from: googleTokenServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy googleTokenServerUrl;
    private final boolean isMiniMail;

    /* JADX INFO: renamed from: ludwigUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy ludwigUrl;

    /* JADX INFO: renamed from: oAuthMailUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy oAuthMailUrl;

    @NotNull
    private final OidcDiscoveryLocalUseCase oidcDiscoveryLocalUseCase;

    /* JADX INFO: renamed from: oneTimeCodeUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy oneTimeCodeUrl;

    /* JADX INFO: renamed from: outlookApiUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy outlookApiUrl;

    /* JADX INFO: renamed from: outlookAuthServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy outlookAuthServerUrl;

    /* JADX INFO: renamed from: outlookMailRedirectUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy outlookMailRedirectUrl;

    /* JADX INFO: renamed from: outlookTokenServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy outlookTokenServerUrl;

    /* JADX INFO: renamed from: prodAuthMailUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy prodAuthMailUrl;

    @NotNull
    private final Resources resources;

    /* JADX INFO: renamed from: restorePasswordUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy restorePasswordUrl;

    /* JADX INFO: renamed from: swaUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy swaUrl;

    /* JADX INFO: renamed from: yahooApiUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yahooApiUrl;

    /* JADX INFO: renamed from: yahooAuthServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yahooAuthServerUrl;

    /* JADX INFO: renamed from: yahooMailRedirectUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yahooMailRedirectUrl;

    /* JADX INFO: renamed from: yahooTokenServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yahooTokenServerUrl;

    /* JADX INFO: renamed from: yandexApiUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yandexApiUrl;

    /* JADX INFO: renamed from: yandexAuthServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yandexAuthServerUrl;

    /* JADX INFO: renamed from: yandexMailRedirectUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yandexMailRedirectUrl;

    /* JADX INFO: renamed from: yandexTokenServerUrl$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy yandexTokenServerUrl;

    public MailAuthorizationSdkUrlsResolver(@NotNull Resources resources, boolean z10, @NotNull OidcDiscoveryLocalUseCase oidcDiscoveryLocalUseCase) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        Intrinsics.checkNotNullParameter(oidcDiscoveryLocalUseCase, "oidcDiscoveryLocalUseCase");
        this.resources = resources;
        this.isMiniMail = z10;
        this.oidcDiscoveryLocalUseCase = oidcDiscoveryLocalUseCase;
        this.captchaHost = LazyKt.lazy(new Function0() { // from class: g8.a
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.captchaHost_delegate$lambda$0(this.f64413a);
            }
        });
        this.authSwaUrl = LazyKt.lazy(new Function0() { // from class: g8.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.authSwaUrl_delegate$lambda$0(this.f64417a);
            }
        });
        this.authMailHost = LazyKt.lazy(new Function0() { // from class: g8.o
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.authMailHost_delegate$lambda$0(this.f64432a);
            }
        });
        this.authMailHostWithScheme = LazyKt.lazy(new Function0() { // from class: g8.q
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.authMailHostWithScheme_delegate$lambda$0(this.f64434a);
            }
        });
        this.authMailUrl = LazyKt.lazy(new Function0() { // from class: g8.r
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.authMailUrl_delegate$lambda$0(this.f64435a);
            }
        });
        this.prodAuthMailUrl = LazyKt.lazy(new Function0() { // from class: g8.s
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.prodAuthMailUrl_delegate$lambda$0(this.f64436a);
            }
        });
        this.oAuthMailUrl = LazyKt.lazy(new Function0() { // from class: g8.t
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.oAuthMailUrl_delegate$lambda$0(this.f64437a);
            }
        });
        this.avatarUrl = LazyKt.lazy(new Function0() { // from class: g8.u
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.avatarUrl_delegate$lambda$0(this.f64438a);
            }
        });
        this.swaUrl = LazyKt.lazy(new Function0() { // from class: g8.v
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.swaUrl_delegate$lambda$0(this.f64439a);
            }
        });
        this.oneTimeCodeUrl = LazyKt.lazy(new Function0() { // from class: g8.x
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.oneTimeCodeUrl_delegate$lambda$0(this.f64441a);
            }
        });
        this.doregCaptchaUrl = LazyKt.lazy(new Function0() { // from class: g8.l
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.doregCaptchaUrl_delegate$lambda$0(this.f64429a);
            }
        });
        this.restorePasswordUrl = LazyKt.lazy(new Function0() { // from class: g8.w
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.restorePasswordUrl_delegate$lambda$0(this.f64440a);
            }
        });
        this.accountUrl = LazyKt.lazy(new Function0() { // from class: g8.y
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.accountUrl_delegate$lambda$0(this.f64442a);
            }
        });
        this.yahooApiUrl = LazyKt.lazy(new Function0() { // from class: g8.z
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yahooApiUrl_delegate$lambda$0(this.f64443a);
            }
        });
        this.yahooMailRedirectUrl = LazyKt.lazy(new Function0() { // from class: g8.a0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yahooMailRedirectUrl_delegate$lambda$0(this.f64414a);
            }
        });
        this.yahooAuthServerUrl = LazyKt.lazy(new Function0() { // from class: g8.b0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yahooAuthServerUrl_delegate$lambda$0(this.f64416a);
            }
        });
        this.yahooTokenServerUrl = LazyKt.lazy(new Function0() { // from class: g8.c0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yahooTokenServerUrl_delegate$lambda$0(this.f64418a);
            }
        });
        this.yandexApiUrl = LazyKt.lazy(new Function0() { // from class: g8.d0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yandexApiUrl_delegate$lambda$0(this.f64420a);
            }
        });
        this.yandexMailRedirectUrl = LazyKt.lazy(new Function0() { // from class: g8.e0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yandexMailRedirectUrl_delegate$lambda$0(this.f64422a);
            }
        });
        this.yandexAuthServerUrl = LazyKt.lazy(new Function0() { // from class: g8.b
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yandexAuthServerUrl_delegate$lambda$0(this.f64415a);
            }
        });
        this.yandexTokenServerUrl = LazyKt.lazy(new Function0() { // from class: g8.d
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.yandexTokenServerUrl_delegate$lambda$0(this.f64419a);
            }
        });
        this.outlookApiUrl = LazyKt.lazy(new Function0() { // from class: g8.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.outlookApiUrl_delegate$lambda$0(this.f64421a);
            }
        });
        this.outlookMailRedirectUrl = LazyKt.lazy(new Function0() { // from class: g8.f
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.outlookMailRedirectUrl_delegate$lambda$0(this.f64423a);
            }
        });
        this.outlookAuthServerUrl = LazyKt.lazy(new Function0() { // from class: g8.g
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.outlookAuthServerUrl_delegate$lambda$0(this.f64424a);
            }
        });
        this.outlookTokenServerUrl = LazyKt.lazy(new Function0() { // from class: g8.h
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.outlookTokenServerUrl_delegate$lambda$0(this.f64425a);
            }
        });
        this.googleApiUrl = LazyKt.lazy(new Function0() { // from class: g8.i
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.googleApiUrl_delegate$lambda$0(this.f64426a);
            }
        });
        this.googleMailRedirectUrl = LazyKt.lazy(new Function0() { // from class: g8.j
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.googleMailRedirectUrl_delegate$lambda$0(this.f64427a);
            }
        });
        this.googleAuthServerUrl = LazyKt.lazy(new Function0() { // from class: g8.k
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.googleAuthServerUrl_delegate$lambda$0(this.f64428a);
            }
        });
        this.googleTokenServerUrl = LazyKt.lazy(new Function0() { // from class: g8.m
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.googleTokenServerUrl_delegate$lambda$0(this.f64430a);
            }
        });
        this.ludwigUrl = LazyKt.lazy(new Function0() { // from class: g8.n
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.ludwigUrl_delegate$lambda$0(this.f64431a);
            }
        });
        this.captchaImitationHost = LazyKt.lazy(new Function0() { // from class: g8.p
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MailAuthorizationSdkUrlsResolver.captchaImitationHost_delegate$lambda$0(this.f64433a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String accountUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_account_default_host, R.string.sdk_account_default_test_host, R.string.sdk_account_default_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String authMailHostWithScheme_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_auth_mail_host, R.string.sdk_auth_mail_test_host, R.string.sdk_auth_mail_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String authMailHost_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultUrl(R.string.sdk_auth_mail_host, R.string.sdk_auth_mail_test_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String authMailUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_auth_mail_host, R.string.sdk_auth_mail_test_host, R.string.sdk_auth_mail_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String authSwaUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_auth_default_host, R.string.sdk_auth_test_default_host, R.string.sdk_auth_default_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String avatarUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_avatar_default_host, R.string.sdk_avatar_test_default_host, R.string.sdk_avatar_default_scheme);
    }

    private final String buildMiniMailOrDefaultHostWithScheme(@StringRes int defaultHostRes, @StringRes int miniMailHostRes, @StringRes int schemeRes) {
        if (this.isMiniMail) {
            defaultHostRes = miniMailHostRes;
        }
        return buildUrl(schemeRes, defaultHostRes);
    }

    private final String buildMiniMailOrDefaultUrl(@StringRes int defaultUrlRes, @StringRes int miniMailUrlRes) {
        Resources resources = this.resources;
        if (this.isMiniMail) {
            defaultUrlRes = miniMailUrlRes;
        }
        return resources.getString(defaultUrlRes);
    }

    private final String buildUrl(@StringRes int schemeRes, @StringRes int hostRes) {
        return ContextKt.createUrlFromResources$default(this.resources, schemeRes, hostRes, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String captchaHost_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultUrl(R.string.sdk_auth_capcha_host, R.string.sdk_auth_test_capcha_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String captchaImitationHost_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultUrl(R.string.sdk_captcha_imitation_default_url, R.string.sdk_captcha_imitation_test_url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String doregCaptchaUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_doreg_captcha_default_host, R.string.sdk_doreg_captcha_default_test_host, R.string.sdk_doreg_captcha_default_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String googleApiUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_google_default_scheme, R.string.sdk_google_api_default_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String googleAuthServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_google_web_auth_server_scheme, R.string.sdk_for_google_web_auth_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String googleMailRedirectUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_for_google_web_mailru_redirect_host, R.string.sdk_for_google_web_mailru_test_redirect_host, R.string.sdk_for_google_web_mailru_redirect_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String googleTokenServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_google_web_token_server_scheme, R.string.sdk_for_google_web_token_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ludwigUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultUrl(R.string.sdk_ludwig_default_url, R.string.sdk_ludwig_test_url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String oAuthMailUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.oidcDiscoveryLocalUseCase.getOAuthScheme() + "://" + mailAuthorizationSdkUrlsResolver.oidcDiscoveryLocalUseCase.getOAuthHost();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String oneTimeCodeUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultUrl(R.string.sdk_one_time_code_default_url, R.string.sdk_one_time_code_test_url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String outlookApiUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_outlook_default_scheme, R.string.sdk_outlook_default_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String outlookAuthServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_outlook_auth_server_scheme, R.string.sdk_for_outlook_auth_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String outlookMailRedirectUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_for_outlook_mailru_redirect_host, R.string.sdk_for_outlook_mailru_test_redirect_host, R.string.sdk_for_outlook_mailru_redirect_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String outlookTokenServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_outlook_token_server_scheme, R.string.sdk_for_outlook_token_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String prodAuthMailUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_yahoo_default_scheme, R.string.sdk_auth_mail_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String restorePasswordUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultUrl(R.string.sdk_restore_password_url, R.string.sdk_restore_test_password_url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String swaUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_swa_default_host, R.string.sdk_swa_test_def_host, R.string.sdk_swa_default_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yahooApiUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_yahoo_default_scheme, R.string.sdk_yahoo_social_api_default_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yahooAuthServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_yahoo_auth_server_scheme, R.string.sdk_for_yahoo_auth_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yahooMailRedirectUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_for_yahoo_mailru_redirect_host, R.string.sdk_for_yahoo_mailru_test_redirect_host, R.string.sdk_for_yahoo_mailru_redirect_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yahooTokenServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_yahoo_token_server_scheme, R.string.sdk_for_yahoo_token_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yandexApiUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_yandex_default_scheme, R.string.sdk_yandex_api_default_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yandexAuthServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_yandex_auth_server_scheme, R.string.sdk_for_yandex_auth_server_host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yandexMailRedirectUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildMiniMailOrDefaultHostWithScheme(R.string.sdk_for_yandex_mailru_redirect_host, R.string.sdk_for_yandex_mailru_test_redirect_host, R.string.sdk_for_yandex_mailru_redirect_scheme);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String yandexTokenServerUrl_delegate$lambda$0(MailAuthorizationSdkUrlsResolver mailAuthorizationSdkUrlsResolver) {
        return mailAuthorizationSdkUrlsResolver.buildUrl(R.string.sdk_for_yandex_token_server_scheme, R.string.sdk_for_yandex_token_server_host);
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getAccountUrl() {
        return (String) this.accountUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getAuthMailHost() {
        return (String) this.authMailHost.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getAuthMailHostWithScheme() {
        return (String) this.authMailHostWithScheme.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getAuthMailUrl() {
        return (String) this.authMailUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getAuthSwaUrl() {
        return (String) this.authSwaUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getAvatarUrl() {
        return (String) this.avatarUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getCaptchaHost() {
        return (String) this.captchaHost.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getCaptchaImitationHost() {
        return (String) this.captchaImitationHost.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getDoregCaptchaUrl() {
        return (String) this.doregCaptchaUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getGoogleApiUrl() {
        return (String) this.googleApiUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getGoogleAuthServerUrl() {
        return (String) this.googleAuthServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getGoogleMailRedirectUrl() {
        return (String) this.googleMailRedirectUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getGoogleTokenServerUrl() {
        return (String) this.googleTokenServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getLudwigUrl() {
        return (String) this.ludwigUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getOAuthMailUrl() {
        return (String) this.oAuthMailUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getOneTimeCodeUrl() {
        return (String) this.oneTimeCodeUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getOutlookApiUrl() {
        return (String) this.outlookApiUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getOutlookAuthServerUrl() {
        return (String) this.outlookAuthServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getOutlookMailRedirectUrl() {
        return (String) this.outlookMailRedirectUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getOutlookTokenServerUrl() {
        return (String) this.outlookTokenServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getProdAuthMailUrl() {
        return (String) this.prodAuthMailUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getRestorePasswordUrl() {
        return (String) this.restorePasswordUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getSwaUrl() {
        return (String) this.swaUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYahooApiUrl() {
        return (String) this.yahooApiUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYahooAuthServerUrl() {
        return (String) this.yahooAuthServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYahooMailRedirectUrl() {
        return (String) this.yahooMailRedirectUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYahooTokenServerUrl() {
        return (String) this.yahooTokenServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYandexApiUrl() {
        return (String) this.yandexApiUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYandexAuthServerUrl() {
        return (String) this.yandexAuthServerUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYandexMailRedirectUrl() {
        return (String) this.yandexMailRedirectUrl.getValue();
    }

    @Override // ru.mail.authorizationsdk.external.urls.AuthorizationSdkUrlsResolver
    @NotNull
    public String getYandexTokenServerUrl() {
        return (String) this.yandexTokenServerUrl.getValue();
    }
}
