package com.vk.auth.verification.url;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.vk.accountmanager.data.AccountManagerRepositoryImpl;
import com.vk.api.sdk.utils.ApiExtKt;
import com.vk.auth.base.BaseAuthFragment;
import com.vk.auth.common.R;
import com.vk.dto.common.id.UserIdKt;
import com.vk.superapp.api.states.VkAuthState;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0017\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0019\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\n\u001a\u00020\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\n\u0010\u000bJ-\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J!\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00102\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H\u0017¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Lcom/vk/auth/verification/url/UrlCheckFragment;", "Lcom/vk/auth/base/BaseAuthFragment;", "Lcom/vk/auth/verification/url/UrlCheckPresenter;", "<init>", "()V", "Landroid/os/Bundle;", "savedInstanceState", "", "onCreate", "(Landroid/os/Bundle;)V", "createPresenter", "(Landroid/os/Bundle;)Lcom/vk/auth/verification/url/UrlCheckPresenter;", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;", Promotion.ACTION_VIEW, "onViewCreated", "(Landroid/view/View;Landroid/os/Bundle;)V", "", "lock", "setUiLocked", "(Z)V", "Companion", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class UrlCheckFragment extends BaseAuthFragment<UrlCheckPresenter> {
    private String erochtuakvmocm;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/vk/auth/verification/url/UrlCheckFragment$Companion;", "", "<init>", "()V", "KEY_AUTH_STATE", "", "KEY_URL", "createArgs", "Landroid/os/Bundle;", "authState", "Lcom/vk/superapp/api/states/VkAuthState;", "url", "common_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Bundle createArgs(@NotNull VkAuthState authState, @NotNull String url) {
            Intrinsics.checkNotNullParameter(authState, "authState");
            Intrinsics.checkNotNullParameter(url, "url");
            Bundle bundle = new Bundle(2);
            bundle.putParcelable("authState", authState);
            bundle.putString("url", url);
            return bundle;
        }

        private Companion() {
        }
    }

    public static final /* synthetic */ UrlCheckPresenter access$getPresenter(UrlCheckFragment urlCheckFragment) {
        return urlCheckFragment.getPresenter();
    }

    @Override // com.vk.auth.base.BaseAuthFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        String string = arguments != null ? arguments.getString("url") : null;
        Intrinsics.checkNotNull(string);
        this.erochtuakvmocm = string;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.vk_auth_check_url_fragment, container, false);
    }

    @Override // com.vk.auth.base.BaseAuthFragment, androidx.fragment.app.Fragment
    @SuppressLint({"SetJavaScriptEnabled"})
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        WebView webView = (WebView) view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);
        webView.setWebViewClient(new WebViewClient() { // from class: com.vk.auth.verification.url.UrlCheckFragment.onViewCreated.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view2, String url) {
                FragmentActivity activity;
                Long longOrNull;
                Intrinsics.checkNotNullParameter(url, "url");
                Uri uri = Uri.parse(StringsKt.replace$default(url, '#', '?', false, 4, (Object) null));
                Intrinsics.checkNotNull(uri);
                if (!ApiExtKt.isOAuthBlank(uri)) {
                    return false;
                }
                boolean zAreEqual = Intrinsics.areEqual(uri.getQueryParameter("success"), "1");
                String queryParameter = uri.getQueryParameter("access_token");
                String queryParameter2 = uri.getQueryParameter(AccountManagerRepositoryImpl.SECRET_ARG);
                String queryParameter3 = uri.getQueryParameter("user_id");
                UrlCheckFragment.access$getPresenter(UrlCheckFragment.this).onCheckCompleted(zAreEqual, queryParameter, queryParameter2, (queryParameter3 == null || (longOrNull = StringsKt.toLongOrNull(queryParameter3)) == null) ? null : UserIdKt.toUserId(longOrNull.longValue()));
                if (zAreEqual || (activity = UrlCheckFragment.this.getActivity()) == null) {
                    return true;
                }
                activity.onBackPressed();
                return true;
            }
        });
        String str = this.erochtuakvmocm;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("url");
            str = null;
        }
        webView.loadUrl(str);
    }

    @Override // com.vk.auth.base.BaseAuthFragment
    @NotNull
    public UrlCheckPresenter createPresenter(@Nullable Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        VkAuthState vkAuthState = arguments != null ? (VkAuthState) arguments.getParcelable("authState") : null;
        Intrinsics.checkNotNull(vkAuthState);
        return new UrlCheckPresenter(vkAuthState);
    }

    @Override // com.vk.auth.base.AuthView
    public void setUiLocked(boolean lock) {
    }
}
