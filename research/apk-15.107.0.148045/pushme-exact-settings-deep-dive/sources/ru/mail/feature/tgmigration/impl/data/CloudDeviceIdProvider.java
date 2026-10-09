package ru.mail.feature.tgmigration.impl.data;

import android.content.Context;
import android.provider.Settings;
import androidx.compose.runtime.internal.StabilityInferred;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.data.cmd.server.ad.RbParams;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0013\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\u0006\u001a\u00020\u0007H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lru/mail/feature/tgmigration/impl/data/CloudDeviceIdProvider;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "invoke", "", "Companion", "feature-tg-migration-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CloudDeviceIdProvider {

    @NotNull
    private static final String MAIL_POSTFIX = "_mail";

    @NotNull
    private final Context context;

    @NotNull
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lru/mail/feature/tgmigration/impl/data/CloudDeviceIdProvider$Companion;", "", "<init>", "()V", "MAIL_POSTFIX", "", "feature-tg-migration-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Inject
    public CloudDeviceIdProvider(@ApplicationContext @NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    @NotNull
    public final String invoke() {
        return Settings.Secure.getString(this.context.getContentResolver(), RbParams.Default.URL_PARAM_KEY_ANDROID_ID) + MAIL_POSTFIX;
    }
}
