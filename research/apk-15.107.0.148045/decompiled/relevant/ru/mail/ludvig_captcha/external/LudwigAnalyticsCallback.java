package ru.mail.ludvig_captcha.external;

import androidx.annotation.Size;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/ludvig_captcha/external/LudwigAnalyticsCallback;", "", "onAnalyticEvent", "", "eventName", "", "params", "", "ludvig-captcha_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LudwigAnalyticsCallback {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void onAnalyticEvent$default(LudwigAnalyticsCallback ludwigAnalyticsCallback, String str, Map map, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onAnalyticEvent");
        }
        if ((i10 & 2) != 0) {
            map = null;
        }
        ludwigAnalyticsCallback.onAnalyticEvent(str, map);
    }

    void onAnalyticEvent(@Size(max = 40, min = 1) @NotNull String eventName, @Nullable Map<String, String> params);
}
