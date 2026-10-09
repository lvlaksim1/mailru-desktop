package ru.mail.util.push;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.gcm.GcmPushFactory;
import ru.mail.util.push.huawei.HmsPushFactory;
import ru.mail.util.push.stub.StubbedPushFactory;
import ru.mail.util.push.vkpns.VkpnsPushFactory;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"createPushFactory", "Lru/mail/util/push/PushFactory;", "Lru/mail/util/push/PushType;", "mail-app_mail_ruRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class PushFactoryCreatorKt {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushType.values().length];
            try {
                iArr[PushType.GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushType.HMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PushType.VKPNS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[PushType.STUB.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @NotNull
    public static final PushFactory createPushFactory(@NotNull PushType pushType) {
        Intrinsics.checkNotNullParameter(pushType, "<this>");
        int i10 = WhenMappings.$EnumSwitchMapping$0[pushType.ordinal()];
        if (i10 == 1) {
            return new GcmPushFactory();
        }
        if (i10 == 2) {
            return new HmsPushFactory();
        }
        if (i10 == 3) {
            return new VkpnsPushFactory();
        }
        if (i10 == 4) {
            return new StubbedPushFactory();
        }
        throw new NoWhenBranchMatchedException();
    }
}
