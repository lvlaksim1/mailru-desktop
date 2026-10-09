package ru.mail.util.push;

import android.content.Context;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.components.SingletonComponent;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.march.internal.work.WorkScheduler;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@EntryPoint
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/util/push/HandlePushEntryPoint;", "", "workScheduler", "Lru/mail/march/internal/work/WorkScheduler;", "mailAppAnalytics", "Lru/mail/analytics/MailAppAnalytics;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@InstallIn({SingletonComponent.class})
public interface HandlePushEntryPoint {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\f"}, d2 = {"Lru/mail/util/push/HandlePushEntryPoint$Companion;", "", "<init>", "()V", "workScheduler", "Lru/mail/march/internal/work/WorkScheduler;", "context", "Landroid/content/Context;", "analytics", "Lru/mail/analytics/MailAppAnalytics;", "entryPoint", "Lru/mail/util/push/HandlePushEntryPoint;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        private final HandlePushEntryPoint entryPoint(Context context) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (HandlePushEntryPoint) EntryPointAccessors.fromApplication(applicationContext, HandlePushEntryPoint.class);
        }

        @JvmStatic
        @NotNull
        public final MailAppAnalytics analytics(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return entryPoint(context).mailAppAnalytics();
        }

        @JvmStatic
        @NotNull
        public final WorkScheduler workScheduler(@NotNull Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return entryPoint(context).workScheduler();
        }
    }

    @JvmStatic
    @NotNull
    static MailAppAnalytics analytics(@NotNull Context context) {
        return INSTANCE.analytics(context);
    }

    @JvmStatic
    @NotNull
    static WorkScheduler workScheduler(@NotNull Context context) {
        return INSTANCE.workScheduler(context);
    }

    @NotNull
    MailAppAnalytics mailAppAnalytics();

    @NotNull
    WorkScheduler workScheduler();
}
