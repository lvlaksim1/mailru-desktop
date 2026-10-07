package ru.mail.auth.webview;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.Authenticator.R;
import ru.mail.auth.loginactivity.CallbackHolder;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00112\u00020\u0001:\u0003\u0011\u0012\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lru/mail/auth/webview/CustomTabWebAuthNHelper;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "launchUrl", "", "url", "", "configureCustomTabUi", "Landroidx/browser/customtabs/CustomTabsIntent$Builder;", "tabBuilder", "makeToolbarColorParam", "Landroidx/browser/customtabs/CustomTabColorSchemeParams;", "color", "", "Companion", "WebAuthNAuthResult", "WebAuthNCallback", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomTabWebAuthNHelper {

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("CustomTabWebAuthNHelper");

    @NotNull
    private final Context context;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult;", "", "<init>", "()V", "Success", "Fail", "Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult$Fail;", "Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult$Success;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static abstract class WebAuthNAuthResult {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult$Fail;", "Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult;", "error", "", "<init>", "(Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Fail extends WebAuthNAuthResult {

            @NotNull
            private final String error;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Fail(@NotNull String error) {
                super(null);
                Intrinsics.checkNotNullParameter(error, "error");
                this.error = error;
            }

            @NotNull
            public final String getError() {
                return this.error;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult$Success;", "Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult;", "token", "", "<init>", "(Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final class Success extends WebAuthNAuthResult {

            @NotNull
            private final String token;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Success(@NotNull String token) {
                super(null);
                Intrinsics.checkNotNullParameter(token, "token");
                this.token = token;
            }

            @NotNull
            public final String getToken() {
                return this.token;
            }
        }

        public /* synthetic */ WebAuthNAuthResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private WebAuthNAuthResult() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes15.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNCallback;", "", "onResult", "", "result", "Lru/mail/auth/webview/CustomTabWebAuthNHelper$WebAuthNAuthResult;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface WebAuthNCallback {
        void onResult(@NotNull WebAuthNAuthResult result);
    }

    public CustomTabWebAuthNHelper(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final CustomTabsIntent.Builder configureCustomTabUi(CustomTabsIntent.Builder tabBuilder, Context context) {
        tabBuilder.setDefaultColorSchemeParams(makeToolbarColorParam(R.color.custom_tab_toolbar));
        tabBuilder.setShowTitle(true);
        tabBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(context.getResources(), ru.mail.ui.R.drawable.ic_ui_action_back));
        tabBuilder.setShareState(2);
        tabBuilder.setStartAnimations(context, ru.mail.ui.R.anim.activity_open_in, ru.mail.ui.R.anim.activity_exit_in);
        tabBuilder.setExitAnimations(context, ru.mail.ui.R.anim.activity_open_out, ru.mail.ui.R.anim.activity_exit_out);
        return tabBuilder;
    }

    private final CustomTabColorSchemeParams makeToolbarColorParam(int color) {
        CustomTabColorSchemeParams customTabColorSchemeParamsBuild = new CustomTabColorSchemeParams.Builder().setToolbarColor(ContextCompat.getColor(this.context, color)).build();
        Intrinsics.checkNotNullExpressionValue(customTabColorSchemeParamsBuild, "build(...)");
        return customTabColorSchemeParamsBuild;
    }

    public final void launchUrl(@NotNull String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        LOG.d("Start WebAuthN auth in custom tab flow");
        CustomTabsIntent customTabsIntentBuild = configureCustomTabUi(new CustomTabsIntent.Builder(), this.context).build();
        Intrinsics.checkNotNullExpressionValue(customTabsIntentBuild, "build(...)");
        Uri uri = Uri.parse(url);
        customTabsIntentBuild.intent.setPackage(CustomTabHelper.getPackageNameToUse(this.context, CustomTabHelper.DefaultIntentFactory.getInstance(uri).makeIntent()));
        CallbackHolder.INSTANCE.setBackFromChromeTabs(true);
        try {
            customTabsIntentBuild.launchUrl(this.context, uri);
        } catch (Exception e10) {
            LOG.d("Can't open WebAuthN auth url. Exception " + e10);
        }
    }
}
