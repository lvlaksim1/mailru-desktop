package ru.mail.settings.notifications.portal.ui;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.ActionBar;
import androidx.compose.runtime.internal.StabilityInferred;
import dagger.hilt.android.AndroidEntryPoint;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.MailApplication;
import ru.mail.android_utils.ViewUtilsKt;
import ru.mail.kotlett.spec.DivActionSpec;
import ru.mail.mailapp.R;
import ru.mail.snackbar.SnackbarParams;
import ru.mail.snackbar.SnackbarUpdater;
import ru.mail.ui.CustomToolbar;
import ru.mail.ui.fragments.view.statusbar.StatusBarConfigurator;
import ru.mail.ui.fragments.view.toolbar.base.ThemeConfigToolbarConfigurationCreator;
import ru.mail.ui.fragments.view.toolbar.base.ToolbarConfigurator;
import ru.mail.ui.fragments.view.toolbar.base.ToolbarManager;
import ru.mail.ui.fragments.view.toolbar.theme.ThemeToolbarConfiguration;
import ru.mail.ui.snackbar.MailSnackbarUpdaterImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001c2\u00020\u00012\u00020\u0002:\u0001\u001cB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\b\u0010\f\u001a\u00020\tH\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\tH\u0002J\b\u0010\u001b\u001a\u00020\tH\u0002R\u000e\u0010\u0005\u001a\u00020\u0002X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lru/mail/settings/notifications/portal/ui/PortalAppNotificationsActivity;", "Lru/mail/ui/BaseMailActivity;", "Lru/mail/snackbar/SnackbarUpdater;", "<init>", "()V", "snackbarUpdater", "toolbar", "Lru/mail/ui/CustomToolbar;", "onCreate", "", "saveInstanceState", "Landroid/os/Bundle;", "onStop", "showSnackbar", "", "params", "Lru/mail/snackbar/SnackbarParams;", "hide", "update", "oldState", "newState", "getAppIdExtra", "", "onOptionsItemSelected", DivActionSpec.Scroll.PARAM_ITEM_INDEX, "Landroid/view/MenuItem;", "initToolbar", "syncPortalAppsChanges", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
public final class PortalAppNotificationsActivity extends Hilt_PortalAppNotificationsActivity implements SnackbarUpdater {

    @NotNull
    private static final String APP_ID_EXTRA = "app_id";
    private SnackbarUpdater snackbarUpdater;
    private CustomToolbar toolbar;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/settings/notifications/portal/ui/PortalAppNotificationsActivity$Companion;", "", "<init>", "()V", "APP_ID_EXTRA", "", "createIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "appId", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent createIntent(@NotNull Context context, @NotNull String appId) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(appId, "appId");
            Intent intent = new Intent(context, (Class<?>) PortalAppNotificationsActivity.class);
            intent.putExtra("app_id", appId);
            return intent;
        }

        private Companion() {
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent createIntent(@NotNull Context context, @NotNull String str) {
        return INSTANCE.createIntent(context, str);
    }

    private final String getAppIdExtra() {
        String stringExtra = getIntent().getStringExtra("app_id");
        if (stringExtra != null) {
            return stringExtra;
        }
        throw new IllegalStateException("Extra is missing");
    }

    private final void initToolbar() {
        StatusBarConfigurator.applyDefault(this);
        View viewFindViewById = findViewById(R.id.toolbar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.toolbar = (CustomToolbar) viewFindViewById;
        ThemeToolbarConfiguration themeToolbarConfigurationCreate = new ThemeConfigToolbarConfigurationCreator(getApplicationContext()).create();
        CustomToolbar customToolbar = this.toolbar;
        CustomToolbar customToolbar2 = null;
        if (customToolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
            customToolbar = null;
        }
        setSupportActionBar(customToolbar);
        ToolbarConfigurator toolbarConfigurator = new ToolbarConfigurator();
        CustomToolbar customToolbar3 = this.toolbar;
        if (customToolbar3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolbar");
        } else {
            customToolbar2 = customToolbar3;
        }
        ToolbarManager toolbarManagerConfigureToolbar = toolbarConfigurator.configureToolbar(this, customToolbar2, themeToolbarConfigurationCreate);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(toolbarManagerConfigureToolbar.getToolbarConfiguration().getActionBackDrawableResId());
        }
    }

    private final void syncPortalAppsChanges() {
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type ru.mail.MailApplication");
        ((MailApplication) application).getPushComponent().getPusherTransport().syncPortalAppsIfNeeded();
    }

    @Override // ru.mail.snackbar.SnackbarUpdater
    public void hide(@NotNull SnackbarParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        SnackbarUpdater snackbarUpdater = this.snackbarUpdater;
        if (snackbarUpdater == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbarUpdater");
            snackbarUpdater = null;
        }
        snackbarUpdater.hide(params);
    }

    @Override // ru.mail.ui.BaseMailActivity, ru.mail.ui.Hilt_BaseMailActivity, ru.mail.ui.AnalyticActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        ViewUtilsKt.handleInsetsByDecorView$default(this, false, false, 3, null);
        setContentView(R.layout.portal_app_notifications_activity);
        initToolbar();
        this.snackbarUpdater = new MailSnackbarUpdaterImpl((ViewGroup) findViewById(R.id.coordinator_layout), LayoutInflater.from(this), this);
        if (saveInstanceState == null) {
            addFragment(R.id.fragment_container, PortalAppNotificationsFragment.INSTANCE.newInstance(getAppIdExtra()));
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onBackPressed();
        return true;
    }

    @Override // ru.mail.ui.BaseMailActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        syncPortalAppsChanges();
    }

    @Override // ru.mail.snackbar.SnackbarUpdater
    public boolean showSnackbar(@NotNull SnackbarParams params) {
        Intrinsics.checkNotNullParameter(params, "params");
        SnackbarUpdater snackbarUpdater = this.snackbarUpdater;
        if (snackbarUpdater == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbarUpdater");
            snackbarUpdater = null;
        }
        snackbarUpdater.showSnackbar(params);
        return true;
    }

    @Override // ru.mail.snackbar.SnackbarUpdater
    public void update(@NotNull SnackbarParams oldState, @NotNull SnackbarParams newState) {
        Intrinsics.checkNotNullParameter(oldState, "oldState");
        Intrinsics.checkNotNullParameter(newState, "newState");
        SnackbarUpdater snackbarUpdater = this.snackbarUpdater;
        if (snackbarUpdater == null) {
            Intrinsics.throwUninitializedPropertyAccessException("snackbarUpdater");
            snackbarUpdater = null;
        }
        snackbarUpdater.update(oldState, newState);
    }
}
