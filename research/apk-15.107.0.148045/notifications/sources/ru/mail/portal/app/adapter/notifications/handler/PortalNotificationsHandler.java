package ru.mail.portal.app.adapter.notifications.handler;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.portal.app.adapter.notifications.PortalPushButton;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\bf\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016J¥\u0001\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u00052\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0002\u0010\u0015¨\u0006\u0017À\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/handler/PortalNotificationsHandler;", "", "handlePush", "", "app", "", "title", "body", "deepLink", "buttons", "", "Lru/mail/portal/app/adapter/notifications/PortalPushButton;", "notificationId", "", "pushCampaign", "emailFromPush", "imgUrl", "imgType", "langFilter", "openUrl", "summaryTextFromPayload", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "Companion", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PortalNotificationsHandler {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @NotNull
    public static final String NOTIFICATION_ID_QUERY_PARAM_NAME = "notification_id";

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/portal/app/adapter/notifications/handler/PortalNotificationsHandler$Companion;", "", "<init>", "()V", "NOTIFICATION_ID_QUERY_PARAM_NAME", "", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        @NotNull
        public static final String NOTIFICATION_ID_QUERY_PARAM_NAME = "notification_id";

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void handlePush$default(PortalNotificationsHandler portalNotificationsHandler, String str, String str2, String str3, String str4, List list, Integer num, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: handlePush");
        }
        portalNotificationsHandler.handlePush(str, str2, (i10 & 4) != 0 ? null : str3, str4, (i10 & 16) != 0 ? null : list, (i10 & 32) != 0 ? null : num, (i10 & 64) != 0 ? null : str5, (i10 & 128) != 0 ? null : str6, (i10 & 256) != 0 ? null : str7, (i10 & 512) != 0 ? null : str8, (i10 & 1024) != 0 ? null : str9, (i10 & 2048) != 0 ? null : str10, (i10 & 4096) != 0 ? null : str11);
    }

    void handlePush(@Nullable String app, @NotNull String title, @Nullable String body, @NotNull String deepLink, @Nullable List<PortalPushButton> buttons, @Nullable Integer notificationId, @Nullable String pushCampaign, @Nullable String emailFromPush, @Nullable String imgUrl, @Nullable String imgType, @Nullable String langFilter, @Nullable String openUrl, @Nullable String summaryTextFromPayload);
}
