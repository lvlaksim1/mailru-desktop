package ru.mail.settings.notifications.portal.ui;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.flags.FragmentGetContextFix;
import dagger.hilt.android.internal.managers.FragmentComponentManager;
import dagger.hilt.internal.GeneratedComponentManagerHolder;
import dagger.hilt.internal.Preconditions;
import dagger.hilt.internal.UnsafeCasts;
import ru.mail.ui.fragments.mailbox.AbstractAccessFragment;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public abstract class Hilt_PortalAppNotificationsFragment extends AbstractAccessFragment {
    private ContextWrapper componentContext;
    private boolean disableGetContextFix = false;
    private boolean injected = false;

    private void initializeComponentContext() {
        if (this.componentContext == null) {
            this.componentContext = FragmentComponentManager.createContextWrapper(super.getGolaidnoissimrepkvmocc(), this);
            this.disableGetContextFix = FragmentGetContextFix.isFragmentGetContextFixDisabled(super.getGolaidnoissimrepkvmocc());
        }
    }

    @Override // ru.mail.ui.fragments.mailbox.Hilt_AbstractAccessFragmentCore, ru.mail.ui.fragments.mailbox.BaseMailFragment, ru.mail.ui.fragments.mailbox.Hilt_BaseMailFragment, androidx.fragment.app.Fragment
    /* JADX INFO: renamed from: getContext */
    public Context getGolaidnoissimrepkvmocc() {
        if (super.getGolaidnoissimrepkvmocc() == null && !this.disableGetContextFix) {
            return null;
        }
        initializeComponentContext();
        return this.componentContext;
    }

    @Override // ru.mail.ui.fragments.mailbox.Hilt_BaseMailFragment, androidx.fragment.app.Fragment, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public /* bridge */ /* synthetic */ ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return super.getDefaultViewModelProviderFactory();
    }

    @Override // ru.mail.ui.fragments.mailbox.Hilt_AbstractAccessFragmentCore, ru.mail.ui.fragments.mailbox.Hilt_BaseMailFragment
    protected void inject() {
        if (this.injected) {
            return;
        }
        this.injected = true;
        ((PortalAppNotificationsFragment_GeneratedInjector) ((GeneratedComponentManagerHolder) UnsafeCasts.unsafeCast(this)).generatedComponent()).injectPortalAppNotificationsFragment((PortalAppNotificationsFragment) UnsafeCasts.unsafeCast(this));
    }

    @Override // ru.mail.ui.fragments.mailbox.AbstractAccessFragmentCore, ru.mail.ui.fragments.mailbox.Hilt_AbstractAccessFragmentCore, ru.mail.ui.fragments.mailbox.BaseMailFragment, ru.mail.ui.fragments.mailbox.Hilt_BaseMailFragment, androidx.fragment.app.Fragment
    @CallSuper
    public void onAttach(Context context) {
        super.onAttach(context);
        initializeComponentContext();
        inject();
    }

    @Override // ru.mail.ui.fragments.mailbox.Hilt_AbstractAccessFragmentCore, ru.mail.ui.fragments.mailbox.Hilt_BaseMailFragment, androidx.fragment.app.Fragment
    public LayoutInflater onGetLayoutInflater(Bundle bundle) {
        LayoutInflater layoutInflaterOnGetLayoutInflater = super.onGetLayoutInflater(bundle);
        return layoutInflaterOnGetLayoutInflater.cloneInContext(FragmentComponentManager.createContextWrapper(layoutInflaterOnGetLayoutInflater, this));
    }

    @Override // ru.mail.ui.fragments.mailbox.Hilt_AbstractAccessFragmentCore, ru.mail.ui.fragments.mailbox.Hilt_BaseMailFragment, androidx.fragment.app.Fragment
    @CallSuper
    @MainThread
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ContextWrapper contextWrapper = this.componentContext;
        Preconditions.checkState(contextWrapper == null || FragmentComponentManager.findActivity(contextWrapper) == activity, "onAttach called multiple times with different Context! Hilt Fragments should not be retained.", new Object[0]);
        initializeComponentContext();
        inject();
    }
}
