package ru.mail.auth.webview;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.vk.auth.ui.fastlogin.VkFastLoginBottomSheetFragment;
import com.vk.auth.ui.fastlogin.VkFastLoginView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\u0014\u0010\t\u001a\u00020\u00072\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00070\nJ\u0016\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lru/mail/auth/webview/CustomVKFastLoginViewFragment;", "Lcom/vk/auth/ui/fastlogin/VkFastLoginBottomSheetFragment;", "<init>", "()V", "callback", "Lcom/vk/auth/ui/fastlogin/VkFastLoginView$FastLoginViewCallback;", "onResume", "", "onDestroy", "setAlternativeAuthCallback", "Lkotlin/Function0;", "showAllowingStateLoss", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "tag", "", "Builder", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CustomVKFastLoginViewFragment extends VkFastLoginBottomSheetFragment {

    @Nullable
    private VkFastLoginView.FastLoginViewCallback callback;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"Lru/mail/auth/webview/CustomVKFastLoginViewFragment$Builder;", "Lcom/vk/auth/ui/fastlogin/VkFastLoginBottomSheetFragment$Builder;", "<init>", "()V", "createEmptyFragment", "Lcom/vk/auth/ui/fastlogin/VkFastLoginBottomSheetFragment;", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Builder extends VkFastLoginBottomSheetFragment.Builder {
        @Override // com.vk.auth.ui.fastlogin.VkFastLoginBottomSheetFragment.Builder
        @NotNull
        protected VkFastLoginBottomSheetFragment createEmptyFragment() {
            return new CustomVKFastLoginViewFragment();
        }
    }

    @Override // com.vk.auth.ui.fastlogin.VkFastLoginBottomSheetFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.callback = null;
    }

    @Override // com.vk.auth.ui.fastlogin.VkFastLoginBottomSheetFragment, com.vk.superapp.ui.VkBaseModalBottomSheet, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        VkFastLoginView.FastLoginViewCallback fastLoginViewCallback = this.callback;
        if (fastLoginViewCallback != null) {
            getFastLoginView().setCallback(fastLoginViewCallback);
            getFastLoginView().setAnotherWayAuth(true);
        }
    }

    public final void setAlternativeAuthCallback(@NotNull final Function0<Unit> callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = new VkFastLoginView.FastLoginViewCallback() { // from class: ru.mail.auth.webview.CustomVKFastLoginViewFragment.setAlternativeAuthCallback.1
            @Override // com.vk.auth.ui.fastlogin.VkFastLoginView.FastLoginViewCallback
            public void onAnotherWayToLogin() {
                callback.invoke();
                this.dismissAllowingStateLoss();
            }
        };
    }

    public final void showAllowingStateLoss(@NotNull FragmentManager fragmentManager, @NotNull String tag) {
        Intrinsics.checkNotNullParameter(fragmentManager, "fragmentManager");
        Intrinsics.checkNotNullParameter(tag, "tag");
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        fragmentTransactionBeginTransaction.add(this, tag);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }
}
