package com.vk.id.captcha.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.vk.id.captcha.a.a;
import com.vk.id.captcha.api.listener.VKCaptchaResultListener;
import com.vk.id.captcha.web.VKCaptchaWebViewActivity;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b<\u0010\u0004J\r\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0017\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\fH\u0007¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0017\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0015H\u0007¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001a\u001a\u00020\u00022\b\u0010\u0019\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\u001a\u0010\u001bJ!\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0017\u0010 \u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\fH\u0007¢\u0006\u0004\b \u0010!R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00100\"8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b#\u0010$R$\u0010%\u001a\u0004\u0018\u00010\u00158\u0001@\u0001X\u0081\u000e¢\u0006\u0012\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001b\u00100\u001a\u00020+8AX\u0081\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/R\"\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\"8\u0001X\u0081\u0004¢\u0006\f\n\u0004\b1\u0010$\u001a\u0004\b2\u00103R$\u00104\u001a\u0004\u0018\u00010\f8\u0001@\u0001X\u0081\u000e¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u0010!R$\u00109\u001a\u0004\u0018\u00010\f8\u0001@\u0001X\u0081\u000e¢\u0006\u0012\n\u0004\b9\u00105\u001a\u0004\b:\u00107\"\u0004\b;\u0010!"}, d2 = {"Lcom/vk/id/captcha/api/VKCaptcha;", "", "", "closeCaptcha", "()V", "Lcom/vk/id/captcha/a;", "state", "closeCaptcha$captcha_release", "(Lcom/vk/id/captcha/a;)V", "Ljava/util/Locale;", "getLocale", "()Ljava/util/Locale;", "", "domain", "getToken", "(Ljava/lang/String;)Ljava/lang/String;", "Landroid/content/Context;", "context", "init", "(Landroid/content/Context;)V", "redirectUri", "Lcom/vk/id/captcha/api/listener/VKCaptchaResultListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "openCaptcha", "(Ljava/lang/String;Ljava/lang/String;Lcom/vk/id/captcha/api/listener/VKCaptchaResultListener;)V", "locale", "setLocale", "(Ljava/util/Locale;)V", "token", "setResult$captcha_release", "(Ljava/lang/String;Ljava/lang/String;)V", "userAgent", "setUserAgent", "(Ljava/lang/String;)V", "Ljava/util/concurrent/atomic/AtomicReference;", "appContext", "Ljava/util/concurrent/atomic/AtomicReference;", "captchaListener", "Lcom/vk/id/captcha/api/listener/VKCaptchaResultListener;", "getCaptchaListener$captcha_release", "()Lcom/vk/id/captcha/api/listener/VKCaptchaResultListener;", "setCaptchaListener$captcha_release", "(Lcom/vk/id/captcha/api/listener/VKCaptchaResultListener;)V", "Lcom/vk/id/captcha/a/a;", "captchaStorage$delegate", "Lkotlin/Lazy;", "getCaptchaStorage$captcha_release", "()Lcom/vk/id/captcha/a/a;", "captchaStorage", "internalLocale", "getInternalLocale$captcha_release", "()Ljava/util/concurrent/atomic/AtomicReference;", "lastDomain", "Ljava/lang/String;", "getLastDomain$captcha_release", "()Ljava/lang/String;", "setLastDomain$captcha_release", "lastRedirectUri", "getLastRedirectUri$captcha_release", "setLastRedirectUri$captcha_release", "<init>"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VKCaptcha {

    @Nullable
    private static volatile VKCaptchaResultListener captchaListener;

    @Nullable
    private static volatile String lastDomain;

    @Nullable
    private static volatile String lastRedirectUri;

    @NotNull
    public static final VKCaptcha INSTANCE = new VKCaptcha();

    /* JADX INFO: renamed from: captchaStorage$delegate, reason: from kotlin metadata */
    @NotNull
    private static final Lazy captchaStorage = LazyKt.lazy(new Function0<a>() { // from class: com.vk.id.captcha.api.VKCaptcha$captchaStorage$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final a invoke() {
            return new a();
        }
    });

    @NotNull
    private static final AtomicReference<Context> appContext = new AtomicReference<>();

    @NotNull
    private static final AtomicReference<Locale> internalLocale = new AtomicReference<>(null);

    private VKCaptcha() {
    }

    private final Locale getLocale() {
        Locale locale = internalLocale.get();
        if (locale != null) {
            return locale;
        }
        Context context = appContext.get();
        if (context == null) {
            throw new IllegalStateException("Required value was null.");
        }
        Locale locale2 = context.getResources().getConfiguration().getLocales().get(0);
        Intrinsics.checkNotNullExpressionValue(locale2, "");
        return locale2;
    }

    public final void closeCaptcha() {
        closeCaptcha$captcha_release(com.vk.id.captcha.a.C0196a.INSTANCE);
    }

    public final void closeCaptcha$captcha_release(@NotNull com.vk.id.captcha.a state) {
        Intrinsics.checkNotNullParameter(state, "");
        com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
        com.vk.id.captcha.b.a.Companion.a().a().a();
        VKCaptchaKt.setResult(state);
    }

    @JvmName(name = "getCaptchaListener$captcha_release")
    @Nullable
    public final VKCaptchaResultListener getCaptchaListener$captcha_release() {
        return captchaListener;
    }

    @JvmName(name = "getCaptchaStorage$captcha_release")
    @NotNull
    public final a getCaptchaStorage$captcha_release() {
        return (a) captchaStorage.getValue();
    }

    @JvmName(name = "getInternalLocale$captcha_release")
    @NotNull
    public final AtomicReference<Locale> getInternalLocale$captcha_release() {
        return internalLocale;
    }

    @JvmName(name = "getLastDomain$captcha_release")
    @Nullable
    public final String getLastDomain$captcha_release() {
        return lastDomain;
    }

    @JvmName(name = "getLastRedirectUri$captcha_release")
    @Nullable
    public final String getLastRedirectUri$captcha_release() {
        return lastRedirectUri;
    }

    @Nullable
    public final String getToken(@NotNull String domain) {
        Intrinsics.checkNotNullParameter(domain, "");
        return getCaptchaStorage$captcha_release().a(domain);
    }

    public final void init(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        appContext.set(context.getApplicationContext());
        com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "");
        com.vk.id.captcha.b.a.Companion.a(applicationContext);
    }

    @SuppressLint({"UseKtx"})
    public final void openCaptcha(@NotNull String domain, @NotNull String redirectUri, @NotNull VKCaptchaResultListener listener) {
        Intrinsics.checkNotNullParameter(domain, "");
        Intrinsics.checkNotNullParameter(redirectUri, "");
        Intrinsics.checkNotNullParameter(listener, "");
        String language = getLocale().getLanguage();
        int i10 = 3;
        if (language != null) {
            int iHashCode = language.hashCode();
            if (iHashCode != 3201) {
                if (iHashCode == 3241) {
                    language.equals("en");
                } else if (iHashCode != 3246) {
                    if (iHashCode != 3276) {
                        if (iHashCode != 3580) {
                            if (iHashCode != 3651) {
                                if (iHashCode != 3710) {
                                    if (iHashCode == 3734 && language.equals("uk")) {
                                        i10 = 1;
                                    }
                                } else if (language.equals("tr")) {
                                    i10 = 82;
                                }
                            } else if (language.equals("ru")) {
                                i10 = 0;
                            }
                        } else if (language.equals("pl")) {
                            i10 = 15;
                        }
                    } else if (language.equals("fr")) {
                        i10 = 16;
                    }
                } else if (language.equals("es")) {
                    i10 = 4;
                }
            } else if (language.equals("de")) {
                i10 = 6;
            }
        }
        String string = Uri.parse(redirectUri).buildUpon().appendQueryParameter("lang_id", String.valueOf(i10)).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        synchronized (this) {
            captchaListener = listener;
            lastDomain = domain;
            lastRedirectUri = string;
            com.vk.id.captcha.b.a.Companion companion = com.vk.id.captcha.b.a.INSTANCE;
            com.vk.id.captcha.b.a.Companion.a().a(true);
            Unit unit = Unit.INSTANCE;
        }
        Context context = appContext.get();
        if (context == null) {
            throw new IllegalStateException("Required value was null.");
        }
        Context context2 = context;
        Intent intent = new Intent(context2, (Class<?>) VKCaptchaWebViewActivity.class);
        intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
        intent.putExtra(VKCaptchaKt.VK_CAPTCHA_URL_KEY, string);
        intent.putExtra(VKCaptchaKt.VK_CAPTCHA_CHALLENGE_DOMAIN_URL_KEY, domain);
        context2.startActivity(intent);
    }

    @JvmName(name = "setCaptchaListener$captcha_release")
    public final void setCaptchaListener$captcha_release(@Nullable VKCaptchaResultListener vKCaptchaResultListener) {
        captchaListener = vKCaptchaResultListener;
    }

    @JvmName(name = "setLastDomain$captcha_release")
    public final void setLastDomain$captcha_release(@Nullable String str) {
        lastDomain = str;
    }

    @JvmName(name = "setLastRedirectUri$captcha_release")
    public final void setLastRedirectUri$captcha_release(@Nullable String str) {
        lastRedirectUri = str;
    }

    public final void setLocale(@Nullable Locale locale) {
        internalLocale.set(locale);
    }

    public final void setResult$captcha_release(@NotNull String token, @Nullable String domain) {
        Intrinsics.checkNotNullParameter(token, "");
        VKCaptchaKt.setResult(new com.vk.id.captcha.a.d(token, domain));
    }

    @Deprecated(message = "No longer necessary and is now noop. Will be removed in version 1.0.0", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public final void setUserAgent(@NotNull String userAgent) {
        Intrinsics.checkNotNullParameter(userAgent, "");
    }
}
