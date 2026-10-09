package ru.mail.settings.notifications.portal.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.google.android.gms.analytics.ecommerce.Promotion;
import dagger.hilt.android.AndroidEntryPoint;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.deviceinfo.DeviceInfo;
import ru.mail.mailapp.R;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.portal.app.adapter.TabAppAdapter;
import ru.mail.portal.app.adapter.notifications.tags.Tag;
import ru.mail.portal.kit.PortalKit;
import ru.mail.settings.notifications.portal.data.TagModel;
import ru.mail.settings.notifications.portal.di.PortalAppNotificationsViewModelAssistedFactory;
import ru.mail.settings.notifications.portal.ui.viewmodel.PortalAppNotificationsViewModel;
import ru.mail.uikit.view.SwitchPreferenceView;
import ru.mail.util.log.Log;
import ru.mail.utils.BundleUtilsKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 .2\u00020\u0001:\u0001.B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u001a\u0010!\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\n\u0010#\u001a\u0004\u0018\u00010$H\u0002J\b\u0010%\u001a\u00020\u0013H\u0002J\u001a\u0010&\u001a\u0004\u0018\u00010\u00132\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*H\u0002J\u0012\u0010+\u001a\u00020\u001e2\b\b\u0001\u0010,\u001a\u00020-H\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lru/mail/settings/notifications/portal/ui/PortalAppNotificationsFragment;", "Lru/mail/ui/fragments/mailbox/AbstractAccessFragment;", "<init>", "()V", "assistedFactory", "Lru/mail/settings/notifications/portal/di/PortalAppNotificationsViewModelAssistedFactory;", "getAssistedFactory", "()Lru/mail/settings/notifications/portal/di/PortalAppNotificationsViewModelAssistedFactory;", "setAssistedFactory", "(Lru/mail/settings/notifications/portal/di/PortalAppNotificationsViewModelAssistedFactory;)V", "viewModel", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel;", "getViewModel", "()Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "tagsHashMap", "", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "Lru/mail/uikit/view/SwitchPreferenceView;", "container", "Landroid/view/ViewGroup;", "allNotificationsTagView", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "savedInstanceState", "Landroid/os/Bundle;", "applyState", "", "state", "Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$State;", "onViewCreated", Promotion.ACTION_VIEW, "getApp", "Lru/mail/portal/app/adapter/TabAppAdapter;", "createMainSwitchView", "createTagView", DeviceInfo.PARAM_KEY_MODEL, "Lru/mail/settings/notifications/portal/data/TagModel;", "isEnabled", "", "setToolbarTitle", "titleRes", "", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@AndroidEntryPoint
@SourceDebugExtension({"SMAP\nPortalAppNotificationsFragment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalAppNotificationsFragment.kt\nru/mail/settings/notifications/portal/ui/PortalAppNotificationsFragment\n+ 2 FragmentViewModelLazy.kt\nandroidx/fragment/app/FragmentViewModelLazyKt\n*L\n1#1,154:1\n106#2,15:155\n*S KotlinDebug\n*F\n+ 1 PortalAppNotificationsFragment.kt\nru/mail/settings/notifications/portal/ui/PortalAppNotificationsFragment\n*L\n31#1:155,15\n*E\n"})
public final class PortalAppNotificationsFragment extends Hilt_PortalAppNotificationsFragment {

    @NotNull
    private static final String APP_ID_EXTRA = "app_id";
    private SwitchPreferenceView allNotificationsTagView;

    @Inject
    public PortalAppNotificationsViewModelAssistedFactory assistedFactory;
    private ViewGroup container;
    private Map<Tag, SwitchPreferenceView> tagsHashMap;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy viewModel;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("PortalAppNotificationsFragment");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/settings/notifications/portal/ui/PortalAppNotificationsFragment$Companion;", "", "<init>", "()V", "APP_ID_EXTRA", "", "LOG", "Lru/mail/util/log/Log;", "newInstance", "Lru/mail/settings/notifications/portal/ui/PortalAppNotificationsFragment;", "appId", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final PortalAppNotificationsFragment newInstance(@NotNull String appId) {
            Intrinsics.checkNotNullParameter(appId, "appId");
            PortalAppNotificationsFragment portalAppNotificationsFragment = new PortalAppNotificationsFragment();
            portalAppNotificationsFragment.setArguments(BundleUtilsKt.bundleOf(TuplesKt.to("app_id", appId)));
            return portalAppNotificationsFragment;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: ru.mail.settings.notifications.portal.ui.PortalAppNotificationsFragment$onViewCreated$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<PortalAppNotificationsViewModel.State, Unit> {
        AnonymousClass1(Object obj) {
            super(1, obj, PortalAppNotificationsFragment.class, "applyState", "applyState(Lru/mail/settings/notifications/portal/ui/viewmodel/PortalAppNotificationsViewModel$State;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PortalAppNotificationsViewModel.State state) {
            invoke2(state);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PortalAppNotificationsViewModel.State p10) {
            Intrinsics.checkNotNullParameter(p10, "p0");
            ((PortalAppNotificationsFragment) this.receiver).applyState(p10);
        }
    }

    public PortalAppNotificationsFragment() {
        Function0 function0 = new Function0() { // from class: ru.mail.settings.notifications.portal.ui.c
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return PortalAppNotificationsFragment.viewModel_delegate$lambda$0(this.f97200a);
            }
        };
        final Function0<Fragment> function1 = new Function0<Fragment>() { // from class: ru.mail.settings.notifications.portal.ui.PortalAppNotificationsFragment$special$$inlined$viewModels$default$1
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
        final Lazy lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<ViewModelStoreOwner>() { // from class: ru.mail.settings.notifications.portal.ui.PortalAppNotificationsFragment$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStoreOwner invoke() {
                return (ViewModelStoreOwner) function1.invoke();
            }
        });
        final Function0 function2 = null;
        this.viewModel = FragmentViewModelLazyKt.createViewModelLazy(this, Reflection.getOrCreateKotlinClass(PortalAppNotificationsViewModel.class), new Function0<ViewModelStore>() { // from class: ru.mail.settings.notifications.portal.ui.PortalAppNotificationsFragment$special$$inlined$viewModels$default$3
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final ViewModelStore invoke() {
                return FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy).getViewModelStore();
            }
        }, new Function0<CreationExtras>() { // from class: ru.mail.settings.notifications.portal.ui.PortalAppNotificationsFragment$special$$inlined$viewModels$default$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function3 = function2;
                if (function3 != null && (creationExtras = (CreationExtras) function3.invoke()) != null) {
                    return creationExtras;
                }
                ViewModelStoreOwner viewModelStoreOwnerM8759viewModels$lambda1 = FragmentViewModelLazyKt.m8759viewModels$lambda1(lazy);
                HasDefaultViewModelProviderFactory hasDefaultViewModelProviderFactory = viewModelStoreOwnerM8759viewModels$lambda1 instanceof HasDefaultViewModelProviderFactory ? (HasDefaultViewModelProviderFactory) viewModelStoreOwnerM8759viewModels$lambda1 : null;
                return hasDefaultViewModelProviderFactory != null ? hasDefaultViewModelProviderFactory.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE;
            }
        }, function0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void applyState(PortalAppNotificationsViewModel.State state) {
        SwitchPreferenceView switchPreferenceView = this.allNotificationsTagView;
        if (switchPreferenceView == null || this.container == null) {
            return;
        }
        if (switchPreferenceView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allNotificationsTagView");
            switchPreferenceView = null;
        }
        switchPreferenceView.setChecked(state.isMainSwitchChecked());
        for (TagModel tagModel : state.getTagsList()) {
            Map<Tag, SwitchPreferenceView> map = this.tagsHashMap;
            if (map == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tagsHashMap");
                map = null;
            }
            if (map.keySet().contains(tagModel.getTag())) {
                Map<Tag, SwitchPreferenceView> map2 = this.tagsHashMap;
                if (map2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tagsHashMap");
                    map2 = null;
                }
                SwitchPreferenceView switchPreferenceView2 = map2.get(tagModel.getTag());
                if (switchPreferenceView2 != null) {
                    switchPreferenceView2.setChecked(tagModel.isChecked());
                }
                if (switchPreferenceView2 != null) {
                    switchPreferenceView2.setEnabled(tagModel.isEnabled());
                }
            } else {
                SwitchPreferenceView switchPreferenceViewCreateTagView = createTagView(tagModel, state.isMainSwitchChecked());
                if (switchPreferenceViewCreateTagView == null) {
                    return;
                }
                Map<Tag, SwitchPreferenceView> map3 = this.tagsHashMap;
                if (map3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("tagsHashMap");
                    map3 = null;
                }
                map3.put(tagModel.getTag(), switchPreferenceViewCreateTagView);
                ViewGroup viewGroup = this.container;
                if (viewGroup == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("container");
                    viewGroup = null;
                }
                viewGroup.addView(switchPreferenceViewCreateTagView);
            }
        }
    }

    private final SwitchPreferenceView createMainSwitchView() {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        SwitchPreferenceView switchPreferenceView = new SwitchPreferenceView(fragmentActivityRequireActivity);
        String string = getString(R.string.portal_app_all_notifications_switch);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        switchPreferenceView.setTitle(string);
        switchPreferenceView.setClickable(true);
        switchPreferenceView.setOnCheckedChangeListener(new Function1() { // from class: ru.mail.settings.notifications.portal.ui.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PortalAppNotificationsFragment.createMainSwitchView$lambda$0$0(this.f97197a, ((Boolean) obj).booleanValue());
            }
        });
        return switchPreferenceView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createMainSwitchView$lambda$0$0(PortalAppNotificationsFragment portalAppNotificationsFragment, boolean z10) {
        portalAppNotificationsFragment.getViewModel().getHandler().invoke(new PortalAppNotificationsViewModel.Event.MainTagSwitchClicked(z10));
        return Unit.INSTANCE;
    }

    private final SwitchPreferenceView createTagView(final TagModel model, boolean isEnabled) {
        Tag tag = model.getTag();
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        String title = tag.getTitle(contextRequireContext);
        if (StringsKt.isBlank(title)) {
            LOG.w("Could not create title for tag: " + model.getTag());
            return null;
        }
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity(...)");
        SwitchPreferenceView switchPreferenceView = new SwitchPreferenceView(fragmentActivityRequireActivity);
        switchPreferenceView.setTitle(title);
        switchPreferenceView.setChecked(model.isChecked());
        switchPreferenceView.setEnabled(isEnabled);
        switchPreferenceView.setOnCheckedChangeListener(new Function1() { // from class: ru.mail.settings.notifications.portal.ui.b
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PortalAppNotificationsFragment.createTagView$lambda$0$0(this.f97198a, model, ((Boolean) obj).booleanValue());
            }
        });
        return switchPreferenceView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit createTagView$lambda$0$0(PortalAppNotificationsFragment portalAppNotificationsFragment, TagModel tagModel, boolean z10) {
        portalAppNotificationsFragment.getViewModel().getHandler().invoke(new PortalAppNotificationsViewModel.Event.TagSwitchClicked(tagModel, z10));
        return Unit.INSTANCE;
    }

    private final TabAppAdapter getApp() {
        Object next;
        String string = requireArguments().getString("app_id");
        Collection<TabAppAdapter> collectionValues = PortalKit.getRepository().getAllTabApps().values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        Iterator<T> it = collectionValues.iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!StringsKt.equals(((TabAppAdapter) next).getAppUniqueId(), string, true));
        TabAppAdapter tabAppAdapter = (TabAppAdapter) next;
        if (tabAppAdapter == null) {
            LOG.e("App with ID = " + string + " not found");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
        return tabAppAdapter;
    }

    private final PortalAppNotificationsViewModel getViewModel() {
        return (PortalAppNotificationsViewModel) this.viewModel.getValue();
    }

    private final void setToolbarTitle(@StringRes int titleRes) {
        ActionBar supportActionBar;
        FragmentActivity activity = getActivity();
        AppCompatActivity appCompatActivity = activity instanceof AppCompatActivity ? (AppCompatActivity) activity : null;
        if (appCompatActivity == null || (supportActionBar = appCompatActivity.getSupportActionBar()) == null) {
            return;
        }
        supportActionBar.setTitle(titleRes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ViewModelProvider.Factory viewModel_delegate$lambda$0(PortalAppNotificationsFragment portalAppNotificationsFragment) {
        String string = portalAppNotificationsFragment.requireArguments().getString("app_id");
        if (string == null) {
            string = "";
        }
        return PortalAppNotificationsViewModel.INSTANCE.providesFactory(portalAppNotificationsFragment.getAssistedFactory(), string);
    }

    @NotNull
    public final PortalAppNotificationsViewModelAssistedFactory getAssistedFactory() {
        PortalAppNotificationsViewModelAssistedFactory portalAppNotificationsViewModelAssistedFactory = this.assistedFactory;
        if (portalAppNotificationsViewModelAssistedFactory != null) {
            return portalAppNotificationsViewModelAssistedFactory;
        }
        Intrinsics.throwUninitializedPropertyAccessException("assistedFactory");
        return null;
    }

    @Override // androidx.fragment.app.Fragment
    @NotNull
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R.layout.portal_app_notifications_fragment, container, false);
        View viewFindViewById = viewInflate.findViewById(R.id.container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.container = (ViewGroup) viewFindViewById;
        this.tagsHashMap = new LinkedHashMap();
        this.allNotificationsTagView = createMainSwitchView();
        ViewGroup viewGroup = this.container;
        SwitchPreferenceView switchPreferenceView = null;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("container");
            viewGroup = null;
        }
        SwitchPreferenceView switchPreferenceView2 = this.allNotificationsTagView;
        if (switchPreferenceView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("allNotificationsTagView");
        } else {
            switchPreferenceView = switchPreferenceView2;
        }
        viewGroup.addView(switchPreferenceView);
        Intrinsics.checkNotNull(viewInflate);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        TabAppAdapter app = getApp();
        if (app != null) {
            setToolbarTitle(app.getAppNameResId());
        }
        ExtensionsKt.collectOnLifecycle(this, getViewModel().getState(), new AnonymousClass1(this));
    }

    public final void setAssistedFactory(@NotNull PortalAppNotificationsViewModelAssistedFactory portalAppNotificationsViewModelAssistedFactory) {
        Intrinsics.checkNotNullParameter(portalAppNotificationsViewModelAssistedFactory, "<set-?>");
        this.assistedFactory = portalAppNotificationsViewModelAssistedFactory;
    }
}
