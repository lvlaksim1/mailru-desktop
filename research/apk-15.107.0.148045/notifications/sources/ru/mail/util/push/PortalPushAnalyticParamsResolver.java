package ru.mail.util.push;

import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.config.section.PortalConfigDto;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lru/mail/util/push/PortalPushAnalyticParamsResolver;", "", "config", "Lru/mail/config/section/PortalConfigDto$NotificationsDto;", "<init>", "(Lru/mail/config/section/PortalConfigDto$NotificationsDto;)V", "resolve", "", "", "uri", "Landroid/net/Uri;", "buttons", "", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPortalPushAnalyticParamsResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PortalPushAnalyticParamsResolver.kt\nru/mail/util/push/PortalPushAnalyticParamsResolver\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,34:1\n1869#2,2:35\n1878#2,3:37\n1#3:40\n*S KotlinDebug\n*F\n+ 1 PortalPushAnalyticParamsResolver.kt\nru/mail/util/push/PortalPushAnalyticParamsResolver\n*L\n12#1:35,2\n20#1:37,3\n*E\n"})
public final class PortalPushAnalyticParamsResolver {
    public static final int $stable = 8;

    @NotNull
    private final PortalConfigDto.NotificationsDto config;

    public PortalPushAnalyticParamsResolver(@NotNull PortalConfigDto.NotificationsDto config) {
        Intrinsics.checkNotNullParameter(config, "config");
        this.config = config;
    }

    @NotNull
    public final Map<String, String> resolve(@NotNull Uri uri, @Nullable List<PortalPushButton> buttons) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Intrinsics.checkNotNullExpressionValue(queryParameterNames, "getQueryParameterNames(...)");
        for (String str : queryParameterNames) {
            String queryParameter = uri.getQueryParameter(str);
            if (queryParameter != null) {
                PortalConfigDto.NotificationsDto notificationsDto = this.config;
                Intrinsics.checkNotNull(str);
                if (notificationsDto.isParameterEnabledForSendInAnalytic(str)) {
                    linkedHashMap.put(str, queryParameter);
                }
            }
        }
        if (buttons != null) {
            int i10 = 0;
            for (Object obj : buttons) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                PortalPushButton portalPushButton = (PortalPushButton) obj;
                linkedHashMap.put("button_" + i11 + "_title", portalPushButton.getTitle());
                linkedHashMap.put("button_" + i11 + "_path", Uri.parse(portalPushButton.getDeepLink()).getPath());
                i10 = i11;
            }
        }
        PortalConfigDto.NotificationsDto.ExperimentDto experiment = this.config.getExperiment();
        if (!experiment.isDefined()) {
            experiment = null;
        }
        if (experiment != null) {
            linkedHashMap.put("sound_enabled", String.valueOf(experiment.isSoundEnabled()));
            linkedHashMap.put("vibration_enabled", String.valueOf(experiment.isVibrationEnabled()));
            linkedHashMap.put("channel_importance", experiment.getImportance());
            linkedHashMap.put("experiment_id", experiment.getExperimentId());
        }
        return MapsKt.toMap(linkedHashMap);
    }
}
