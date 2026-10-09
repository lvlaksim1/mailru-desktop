package ru.mail.util.push;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.locator.Locator;
import ru.mail.pin.PinValidationService;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lru/mail/util/push/DefaultSafeDisplayNotificationChecker;", "Lru/mail/util/push/SafeDisplayNotificationChecker;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "applicationContext", "kotlin.jvm.PlatformType", "pinValidationService", "Lru/mail/pin/PinValidationService;", "getPinValidationService", "()Lru/mail/pin/PinValidationService;", "pinValidationService$delegate", "Lkotlin/Lazy;", "isSafelyDisplayNotification", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DefaultSafeDisplayNotificationChecker implements SafeDisplayNotificationChecker {
    public static final int $stable = 8;
    private final Context applicationContext;

    /* JADX INFO: renamed from: pinValidationService$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pinValidationService;

    public DefaultSafeDisplayNotificationChecker(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.applicationContext = context.getApplicationContext();
        this.pinValidationService = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: ru.mail.util.push.e
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return DefaultSafeDisplayNotificationChecker.pinValidationService_delegate$lambda$0(this.f101056a);
            }
        });
    }

    private final PinValidationService getPinValidationService() {
        return (PinValidationService) this.pinValidationService.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PinValidationService pinValidationService_delegate$lambda$0(DefaultSafeDisplayNotificationChecker defaultSafeDisplayNotificationChecker) {
        return (PinValidationService) Locator.INSTANCE.locate(defaultSafeDisplayNotificationChecker.applicationContext, PinValidationService.class);
    }

    @Override // ru.mail.util.push.SafeDisplayNotificationChecker
    public boolean isSafelyDisplayNotification() {
        Context applicationContext = this.applicationContext;
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        if (PinEnabledResolver.isPinEnabled(applicationContext) && !getPinValidationService().isPinEntered()) {
            Context applicationContext2 = this.applicationContext;
            Intrinsics.checkNotNullExpressionValue(applicationContext2, "applicationContext");
            if (!PinEnabledResolver.shouldShowNotificationContent(applicationContext2)) {
                return true;
            }
        }
        return false;
    }
}
