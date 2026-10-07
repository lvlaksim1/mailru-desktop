package ru.mail.auth.ludwig;

import android.os.Bundle;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0005H\u0007J\u001d\u0010\u000f\u001a\u00020\u00102\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\u0010\u0010\u0014\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J\u0018\u0010\u0015\u001a\u00020\u00102\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lru/mail/auth/ludwig/LudwigParams;", "", "<init>", "()V", "LUDWIG_TOKEN_BODY_PARAM_KEY", "", "LUDWIG_ENABLE_BODY_PARAM_KEY", "LUDWIG_ENABLE_BODY_PARAM_VALUE", LudwigParams.SECOND_STEP_REQUIRED_LUDWIG_TOKEN, LudwigParams.SECOND_STEP_REQUIRED_IS_LUDWIG_ENABLED, "getLudwigParams", "", "isLudwigEnabled", "", "ludwigToken", "putIsLudwigEnabled", "", "bundle", "Landroid/os/Bundle;", "(Ljava/lang/Boolean;Landroid/os/Bundle;)V", "getIsLudwigEnabled", "putLudwigToken", "getLudwigToken", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nLudwigParams.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LudwigParams.kt\nru/mail/auth/ludwig/LudwigParams\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,39:1\n1#2:40\n*E\n"})
public final class LudwigParams {

    @NotNull
    public static final LudwigParams INSTANCE = new LudwigParams();

    @NotNull
    private static final String LUDWIG_ENABLE_BODY_PARAM_KEY = "ludwig";

    @NotNull
    private static final String LUDWIG_ENABLE_BODY_PARAM_VALUE = "1";

    @NotNull
    private static final String LUDWIG_TOKEN_BODY_PARAM_KEY = "ludwig_token";

    @NotNull
    private static final String SECOND_STEP_REQUIRED_IS_LUDWIG_ENABLED = "SECOND_STEP_REQUIRED_IS_LUDWIG_ENABLED";

    @NotNull
    private static final String SECOND_STEP_REQUIRED_LUDWIG_TOKEN = "SECOND_STEP_REQUIRED_LUDWIG_TOKEN";

    private LudwigParams() {
    }

    public static /* synthetic */ Map getLudwigParams$default(LudwigParams ludwigParams, boolean z10, String str, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            str = null;
        }
        return ludwigParams.getLudwigParams(z10, str);
    }

    public final boolean getIsLudwigEnabled(@Nullable Bundle bundle) {
        if (bundle != null) {
            return bundle.getBoolean(SECOND_STEP_REQUIRED_IS_LUDWIG_ENABLED);
        }
        return false;
    }

    @javax.annotation.Nullable
    @Nullable
    public final Map<String, String> getLudwigParams(boolean isLudwigEnabled, @Nullable String ludwigToken) {
        if (isLudwigEnabled) {
            return (ludwigToken == null || ludwigToken.length() == 0) ? MapsKt.mapOf(TuplesKt.to(LUDWIG_ENABLE_BODY_PARAM_KEY, "1")) : MapsKt.mapOf(TuplesKt.to("ludwig_token", ludwigToken));
        }
        return null;
    }

    @Nullable
    public final String getLudwigToken(@Nullable Bundle bundle) {
        if (bundle != null) {
            return bundle.getString(SECOND_STEP_REQUIRED_LUDWIG_TOKEN);
        }
        return null;
    }

    public final void putIsLudwigEnabled(@Nullable Boolean isLudwigEnabled, @NotNull Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        if (isLudwigEnabled != null) {
            bundle.putBoolean(SECOND_STEP_REQUIRED_IS_LUDWIG_ENABLED, isLudwigEnabled.booleanValue());
        }
    }

    public final void putLudwigToken(@Nullable String ludwigToken, @NotNull Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        if (ludwigToken != null) {
            bundle.putString(SECOND_STEP_REQUIRED_LUDWIG_TOKEN, ludwigToken);
        }
    }
}
