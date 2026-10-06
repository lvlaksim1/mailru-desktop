package com.vk.api.sdk.auth;

import android.os.Bundle;
import com.vk.api.sdk.VKApiConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B+\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/vk/api/sdk/auth/VKAuthParams;", "", "appId", "", "redirectUrl", "", "scope", "", "Lcom/vk/api/sdk/auth/VKScope;", "<init>", "(ILjava/lang/String;Ljava/util/Collection;)V", "getAppId", "()I", "getRedirectUrl", "()Ljava/lang/String;", "", "toBundle", "Landroid/os/Bundle;", "toExtraBundle", "getScopeString", "Companion", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVKAuthParams.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKAuthParams.kt\ncom/vk/api/sdk/auth/VKAuthParams\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,83:1\n1563#2:84\n1634#2,3:85\n*S KotlinDebug\n*F\n+ 1 VKAuthParams.kt\ncom/vk/api/sdk/auth/VKAuthParams\n*L\n44#1:84\n44#1:85,3\n*E\n"})
public final class VKAuthParams {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String DEFAULT_REDIRECT_URL = "https://" + VKApiConfig.INSTANCE.getDEFAULT_OAUTH_WEB_DOMAIN() + "/blank.html";

    @NotNull
    private static final String VK_APP_ID_KEY = "vk_app_id";

    @NotNull
    private static final String VK_APP_REDIRECT_URL_KEY = "vk_app_redirect_url";

    @NotNull
    private static final String VK_APP_SCOPE_KEY = "vk_app_scope";

    @NotNull
    private static final String VK_EXTRA_CLIENT_ID = "client_id";

    @NotNull
    private static final String VK_EXTRA_REDIRECT_URL = "redirect_url";

    @NotNull
    private static final String VK_EXTRA_REVOKE = "revoke";

    @NotNull
    private static final String VK_EXTRA_SCOPE = "scope";
    private final int appId;

    @NotNull
    private final String redirectUrl;

    @NotNull
    private final Set<VKScope> scope;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, d2 = {"Lcom/vk/api/sdk/auth/VKAuthParams$Companion;", "", "<init>", "()V", "VK_APP_ID_KEY", "", "VK_APP_SCOPE_KEY", "VK_APP_REDIRECT_URL_KEY", "VK_EXTRA_CLIENT_ID", "VK_EXTRA_REVOKE", "VK_EXTRA_SCOPE", "VK_EXTRA_REDIRECT_URL", "DEFAULT_REDIRECT_URL", "getDEFAULT_REDIRECT_URL", "()Ljava/lang/String;", "fromBundle", "Lcom/vk/api/sdk/auth/VKAuthParams;", "bundle", "Landroid/os/Bundle;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nVKAuthParams.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VKAuthParams.kt\ncom/vk/api/sdk/auth/VKAuthParams$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,83:1\n1563#2:84\n1634#2,3:85\n*S KotlinDebug\n*F\n+ 1 VKAuthParams.kt\ncom/vk/api/sdk/auth/VKAuthParams$Companion\n*L\n77#1:84\n77#1:85,3\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final VKAuthParams fromBundle(@Nullable Bundle bundle) {
            Set setEmptySet;
            if (bundle == null) {
                return null;
            }
            int i10 = bundle.getInt(VKAuthParams.VK_APP_ID_KEY);
            ArrayList<String> stringArrayList = bundle.getStringArrayList(VKAuthParams.VK_APP_SCOPE_KEY);
            if (stringArrayList != null) {
                setEmptySet = new ArrayList(CollectionsKt.collectionSizeOrDefault(stringArrayList, 10));
                for (String str : stringArrayList) {
                    Intrinsics.checkNotNull(str);
                    setEmptySet.add(VKScope.valueOf(str));
                }
            } else {
                setEmptySet = SetsKt.emptySet();
            }
            String string = bundle.getString(VKAuthParams.VK_APP_REDIRECT_URL_KEY, getDEFAULT_REDIRECT_URL());
            Intrinsics.checkNotNull(string);
            return new VKAuthParams(i10, string, setEmptySet);
        }

        @NotNull
        public final String getDEFAULT_REDIRECT_URL() {
            return VKAuthParams.DEFAULT_REDIRECT_URL;
        }

        private Companion() {
        }
    }

    @JvmOverloads
    public VKAuthParams(int i10) {
        this(i10, null, null, 6, null);
    }

    public final int getAppId() {
        return this.appId;
    }

    @NotNull
    public final String getRedirectUrl() {
        return this.redirectUrl;
    }

    @NotNull
    public final String getScopeString() {
        return CollectionsKt.joinToString$default(this.scope, ",", null, null, 0, null, null, 62, null);
    }

    @NotNull
    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(VK_APP_ID_KEY, this.appId);
        Set<VKScope> set = this.scope;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(((VKScope) it.next()).name());
        }
        bundle.putStringArrayList(VK_APP_SCOPE_KEY, new ArrayList<>(arrayList));
        bundle.putString(VK_APP_REDIRECT_URL_KEY, this.redirectUrl);
        return bundle;
    }

    @NotNull
    public final Bundle toExtraBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt("client_id", this.appId);
        bundle.putBoolean(VK_EXTRA_REVOKE, true);
        bundle.putString("scope", CollectionsKt.joinToString$default(this.scope, ",", null, null, 0, null, null, 62, null));
        bundle.putString(VK_EXTRA_REDIRECT_URL, this.redirectUrl);
        return bundle;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public VKAuthParams(int i10, @NotNull String redirectUrl) {
        this(i10, redirectUrl, null, 4, null);
        Intrinsics.checkNotNullParameter(redirectUrl, "redirectUrl");
    }

    @JvmOverloads
    public VKAuthParams(int i10, @NotNull String redirectUrl, @NotNull Collection<? extends VKScope> scope) {
        Intrinsics.checkNotNullParameter(redirectUrl, "redirectUrl");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.appId = i10;
        this.redirectUrl = redirectUrl;
        if (i10 != 0) {
            this.scope = new HashSet(scope);
            return;
        }
        throw new IllegalStateException("AppId is empty! Find out how to get your appId at https://vk.com/dev/access_token");
    }

    public /* synthetic */ VKAuthParams(int i10, String str, Set set, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this(i10, (i11 & 2) != 0 ? DEFAULT_REDIRECT_URL : str, (i11 & 4) != 0 ? SetsKt.emptySet() : set);
    }
}
