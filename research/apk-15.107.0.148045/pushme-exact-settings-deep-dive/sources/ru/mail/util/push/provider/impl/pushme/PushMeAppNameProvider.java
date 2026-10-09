package ru.mail.util.push.provider.impl.pushme;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.util.push.PusherApplicationType;
import ru.mail.util.push.provider.PusherAppNameProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lru/mail/util/push/provider/impl/pushme/PushMeAppNameProvider;", "Lru/mail/util/push/provider/PusherAppNameProvider;", "<init>", "()V", "getPusherAppName", "", "app", "Lru/mail/util/push/PusherApplicationType;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMeAppNameProvider implements PusherAppNameProvider {
    public static final int $stable = 0;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PusherApplicationType.values().length];
            try {
                iArr[PusherApplicationType.MAIL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PusherApplicationType.CALENDAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PusherApplicationType.TODO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PusherApplicationType.ADDRESS_BOOK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[PusherApplicationType.PULSE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[PusherApplicationType.CLOUD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[PusherApplicationType.MORE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[PusherApplicationType.BONUS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[PusherApplicationType.MAIN_PAGE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[PusherApplicationType.WALLET.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // ru.mail.util.push.provider.PusherAppNameProvider
    @NotNull
    public String getPusherAppName(@NotNull PusherApplicationType app) {
        Intrinsics.checkNotNullParameter(app, "app");
        switch (WhenMappings.$EnumSwitchMapping$0[app.ordinal()]) {
            case 1:
                return BuildConfigVariablesHolder.pusherAppName;
            case 2:
                return "calendar_superapp";
            case 3:
                return "todo_superapp";
            case 4:
                return "addressbook_superapp";
            case 5:
                return "pulse_superapp";
            case 6:
                return "cloud_superapp";
            case 7:
                return "more_superapp";
            case 8:
                return "bonus_superapp";
            case 9:
                return "dash_superapp";
            case 10:
                return "wallet_superapp";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
