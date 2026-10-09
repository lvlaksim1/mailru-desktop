package ru.mail.settings.notifications.portal.ui;

import android.content.Context;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.UnsafeCasts;
import ru.mail.ui.BaseMailActivity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class Hilt_PortalAppNotificationsActivity extends BaseMailActivity {
    private boolean injected = false;

    Hilt_PortalAppNotificationsActivity() {
        _initHiltInternal();
    }

    private void _initHiltInternal() {
        addOnContextAvailableListener(new OnContextAvailableListener() { // from class: ru.mail.settings.notifications.portal.ui.Hilt_PortalAppNotificationsActivity.1
            @Override // androidx.activity.contextaware.OnContextAvailableListener
            public void onContextAvailable(Context context) {
                Hilt_PortalAppNotificationsActivity.this.inject();
            }
        });
    }

    @Override // ru.mail.ui.Hilt_BaseMailActivity, androidx.activity.ComponentActivity, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public /* bridge */ /* synthetic */ ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return super.getDefaultViewModelProviderFactory();
    }

    @Override // ru.mail.ui.Hilt_BaseMailActivity
    protected void inject() {
        if (this.injected) {
            return;
        }
        this.injected = true;
        ((PortalAppNotificationsActivity_GeneratedInjector) ((GeneratedComponentManagerHolder) UnsafeCasts.unsafeCast(this)).generatedComponent()).injectPortalAppNotificationsActivity((PortalAppNotificationsActivity) UnsafeCasts.unsafeCast(this));
    }
}
