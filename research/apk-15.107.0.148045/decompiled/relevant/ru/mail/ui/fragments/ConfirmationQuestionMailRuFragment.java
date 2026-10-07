package ru.mail.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.color.MaterialColors;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.ArrayList;
import javax.inject.Inject;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mailapp.R;
import ru.mail.registration.ui.ConfirmationActivity;
import ru.mail.registration.ui.LoadCaptchaDelegate;
import ru.mail.ui.auth.universal.authDesign.AuthDesignFactory;
import ru.mail.util.TagsInjectorImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@AndroidEntryPoint
public class ConfirmationQuestionMailRuFragment extends Hilt_ConfirmationQuestionMailRuFragment {

    @Inject
    DTOConfiguration.Config.RegRebrandingConfig rebrandingConfig;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configureToolbar$0(View view) {
        requireActivity().onBackPressed();
    }

    public static ConfirmationQuestionMailRuFragment newInstance(ConfirmationActivity.CaptchaQuestionAnalyticsFlow captchaQuestionAnalyticsFlow) {
        ConfirmationQuestionMailRuFragment confirmationQuestionMailRuFragment = new ConfirmationQuestionMailRuFragment();
        Bundle bundle = new Bundle();
        bundle.putString("captcha_flow", captchaQuestionAnalyticsFlow.toString());
        confirmationQuestionMailRuFragment.setArguments(bundle);
        return confirmationQuestionMailRuFragment;
    }

    @Override // ru.mail.registration.ui.ConfirmationQuestionFragment
    protected void configureToolbar(View view) {
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        if (toolbar != null) {
            if (this.rebrandingConfig.getRedesignEnabled()) {
                toolbar.setTitleTextColor(MaterialColors.getColor(toolbar, R.attr.vkuiColorTextPrimary));
                toolbar.setNavigationIcon(R.drawable.ic_left);
            } else {
                toolbar.setTitle(getString(R.string.registration_title));
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: ru.mail.ui.fragments.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f98597a.lambda$configureToolbar$0(view2);
                }
            });
        }
    }

    @Override // ru.mail.registration.ui.ConfirmationQuestionFragment
    protected int getConfirmCapchaLayout() {
        return new AuthDesignFactory(getActivity()).getRegistrationActivityDesign().getConfirmCapchaLayout();
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationQuestionMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationQuestionFragment, androidx.fragment.app.Fragment
    /* JADX INFO: renamed from: getContext */
    public /* bridge */ /* synthetic */ Context getResworbkvmocaf() {
        return super.getResworbkvmocaf();
    }

    @Override // ru.mail.registration.ui.Hilt_ConfirmationQuestionFragment, androidx.fragment.app.Fragment, androidx.lifecycle.HasDefaultViewModelProviderFactory
    public /* bridge */ /* synthetic */ ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        return super.getDefaultViewModelProviderFactory();
    }

    @Override // ru.mail.registration.ui.ConfirmationQuestionFragment, ru.mail.registration.ui.LoadCaptchaDelegate.LoadCaptchaCallback
    public void loadCaptchaSuccess(LoadCaptchaDelegate.CaptchaResult captchaResult) {
        setmMrcuCookie(captchaResult.getCookie());
        setCodeEditTextTag(captchaResult.getCookie(), captchaResult.getxCaptchaId());
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationQuestionMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationQuestionFragment, ru.mail.registration.ui.BaseAccountDataFragment, androidx.fragment.app.Fragment
    @CallSuper
    @MainThread
    public /* bridge */ /* synthetic */ void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationQuestionMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationQuestionFragment, androidx.fragment.app.Fragment
    public /* bridge */ /* synthetic */ LayoutInflater onGetLayoutInflater(Bundle bundle) {
        return super.onGetLayoutInflater(bundle);
    }

    public void setCodeEditTextTag(String str, String str2) {
        TagsInjectorImpl tagsInjectorImpl = new TagsInjectorImpl();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        tagsInjectorImpl.injectTags(getCaptchaCodeEditText(), arrayList);
    }

    @Override // ru.mail.ui.fragments.Hilt_ConfirmationQuestionMailRuFragment, ru.mail.registration.ui.Hilt_ConfirmationQuestionFragment, androidx.fragment.app.Fragment
    @CallSuper
    public /* bridge */ /* synthetic */ void onAttach(Context context) {
        super.onAttach(context);
    }
}
