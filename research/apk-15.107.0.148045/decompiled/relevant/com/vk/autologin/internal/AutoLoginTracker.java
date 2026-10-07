package com.vk.autologin.internal;

import com.vk.autologin.VkAutoLoginCallback;
import com.vk.registration.funnels.RegistrationFunnelsTracker;
import com.vk.stat.sak.scheme.SchemeStatSak;
import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.authorizationsdk.feature.google.common.analytics.GoogleErrorDescriptions;
import ru.mail.network.NetworkCommand;
import ru.mail.sanselan.ImageInfo;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0003J\u0017\u0010\b\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\r\u0010\n\u001a\u00020\u0004¢\u0006\u0004\b\n\u0010\u0003J\u0015\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eR$\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R$\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00168\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lcom/vk/autologin/internal/AutoLoginTracker;", "", "<init>", "()V", "", "start", "", "providerAppId", "putProvider", "(Ljava/lang/Integer;)V", "putReasonByProvider", "Lcom/vk/autologin/internal/AutoLoginState$Error;", "type", "sendError", "(Lcom/vk/autologin/internal/AutoLoginState$Error;)V", "", "value", "lpminigolotuakvmocd", "Ljava/lang/String;", "getLastAutoLoginId", "()Ljava/lang/String;", "lastAutoLoginId", "", "lpminigolotuakvmoce", "J", "getStartTime", "()J", "startTime", "lpminigolotuakvmoca", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class AutoLoginTracker {

    @Nullable
    private Integer lpminigolotuakvmocc;

    /* JADX INFO: renamed from: lpminigolotuakvmoce, reason: from kotlin metadata */
    private long startTime;

    @NotNull
    private final Stack<Integer> lpminigolotuakvmoca = new Stack<>();
    private final int lpminigolotuakvmocb = -1;

    /* JADX INFO: renamed from: lpminigolotuakvmocd, reason: from kotlin metadata */
    @NotNull
    private String lastAutoLoginId = "";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[VkAutoLoginCallback.Fail.values().length];
            try {
                iArr[VkAutoLoginCallback.Fail.AlreadyAuthorized.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[VkAutoLoginCallback.Fail.Delayed.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[VkAutoLoginCallback.Fail.NotFoundUsers.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[VkAutoLoginCallback.Fail.AccessDenied.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[VkAutoLoginCallback.Fail.Captcha.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[VkAutoLoginCallback.Fail.Error.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[VkAutoLoginCallback.Fail.Cancel.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: ProGuard */
    private static final class lpminigolotuakvmoca {
        public static final lpminigolotuakvmoca lpminigolotuakvmocb;
        public static final lpminigolotuakvmoca lpminigolotuakvmocc;
        public static final lpminigolotuakvmoca lpminigolotuakvmocd;
        public static final lpminigolotuakvmoca lpminigolotuakvmoce;
        public static final lpminigolotuakvmoca lpminigolotuakvmocf;
        public static final lpminigolotuakvmoca lpminigolotuakvmocg;

        @NotNull
        private final String lpminigolotuakvmoca;

        static {
            lpminigolotuakvmoca lpminigolotuakvmocaVar = new lpminigolotuakvmoca(GoogleErrorDescriptions.NETWORK_ERROR, 0, "network_error");
            lpminigolotuakvmocb = lpminigolotuakvmocaVar;
            lpminigolotuakvmoca lpminigolotuakvmocaVar2 = new lpminigolotuakvmoca("Captcha", 1, "captcha");
            lpminigolotuakvmocc = lpminigolotuakvmocaVar2;
            lpminigolotuakvmoca lpminigolotuakvmocaVar3 = new lpminigolotuakvmoca("NoToken", 2, "no_token");
            lpminigolotuakvmocd = lpminigolotuakvmocaVar3;
            lpminigolotuakvmoca lpminigolotuakvmocaVar4 = new lpminigolotuakvmoca("TimeLimit", 3, "time_limit");
            lpminigolotuakvmoce = lpminigolotuakvmocaVar4;
            lpminigolotuakvmoca lpminigolotuakvmocaVar5 = new lpminigolotuakvmoca(ImageInfo.COMPRESSION_ALGORITHM_UNKNOWN, 4, "unknown");
            lpminigolotuakvmocf = lpminigolotuakvmocaVar5;
            lpminigolotuakvmoca lpminigolotuakvmocaVar6 = new lpminigolotuakvmoca("AccessDenied", 5, "access_denied");
            lpminigolotuakvmocg = lpminigolotuakvmocaVar6;
            EnumEntriesKt.enumEntries(new lpminigolotuakvmoca[]{lpminigolotuakvmocaVar, lpminigolotuakvmocaVar2, lpminigolotuakvmocaVar3, lpminigolotuakvmocaVar4, lpminigolotuakvmocaVar5, lpminigolotuakvmocaVar6});
        }

        private lpminigolotuakvmoca(String str, int i10, String str2) {
            super(str, i10);
            this.lpminigolotuakvmoca = str2;
        }

        @NotNull
        public final String lpminigolotuakvmoca() {
            return this.lpminigolotuakvmoca;
        }
    }

    private final String lpminigolotuakvmoca(lpminigolotuakvmoca lpminigolotuakvmocaVar) {
        ArrayList arrayList = new ArrayList();
        boolean zContains = ArraysKt.contains(new lpminigolotuakvmoca[]{lpminigolotuakvmoca.lpminigolotuakvmocg, lpminigolotuakvmoca.lpminigolotuakvmocc, lpminigolotuakvmoca.lpminigolotuakvmocb, lpminigolotuakvmoca.lpminigolotuakvmocf}, lpminigolotuakvmocaVar);
        if (this.lpminigolotuakvmoca.empty() || zContains) {
            Integer num = zContains ? this.lpminigolotuakvmocc : null;
            arrayList.add(TuplesKt.to(Integer.valueOf(num != null ? num.intValue() : this.lpminigolotuakvmocb), lpminigolotuakvmocaVar.lpminigolotuakvmoca()));
        }
        while (!this.lpminigolotuakvmoca.empty()) {
            arrayList.add(TuplesKt.to(this.lpminigolotuakvmoca.pop(), lpminigolotuakvmoca.lpminigolotuakvmocg.lpminigolotuakvmoca()));
        }
        return CollectionsKt.joinToString$default(arrayList, ",", NetworkCommand.URL_PATH_PARAM_PREFIX, NetworkCommand.URL_PATH_PARAM_SUFFIX, 0, null, new Function1() { // from class: com.vk.autologin.internal.j
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AutoLoginTracker.lpminigolotuakvmoca((Pair) obj);
            }
        }, 24, null);
    }

    @NotNull
    public final String getLastAutoLoginId() {
        return this.lastAutoLoginId;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final void putProvider(@Nullable Integer providerAppId) {
        this.lpminigolotuakvmocc = providerAppId;
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        this.lastAutoLoginId = string;
    }

    public final void putReasonByProvider() {
        Integer num = this.lpminigolotuakvmocc;
        if (num != null) {
            this.lpminigolotuakvmoca.push(Integer.valueOf(num.intValue()));
            this.lpminigolotuakvmocc = null;
        }
    }

    public final void sendError(@NotNull AutoLoginState.Error type) {
        lpminigolotuakvmoca lpminigolotuakvmocaVar;
        Intrinsics.checkNotNullParameter(type, "type");
        long jCurrentTimeMillis = System.currentTimeMillis();
        switch (WhenMappings.$EnumSwitchMapping$0[type.getReason().ordinal()]) {
            case 1:
            case 7:
                lpminigolotuakvmocaVar = null;
                break;
            case 2:
                lpminigolotuakvmocaVar = lpminigolotuakvmoca.lpminigolotuakvmoce;
                break;
            case 3:
                lpminigolotuakvmocaVar = lpminigolotuakvmoca.lpminigolotuakvmocd;
                break;
            case 4:
                lpminigolotuakvmocaVar = lpminigolotuakvmoca.lpminigolotuakvmocg;
                break;
            case 5:
                lpminigolotuakvmocaVar = lpminigolotuakvmoca.lpminigolotuakvmocc;
                break;
            case 6:
                Integer codeError = type.getCodeError();
                lpminigolotuakvmocaVar = (codeError == null || codeError.intValue() != -1) ? lpminigolotuakvmoca.lpminigolotuakvmocf : lpminigolotuakvmoca.lpminigolotuakvmocb;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (lpminigolotuakvmocaVar != null) {
            RegistrationFunnelsTracker.event$default(RegistrationFunnelsTracker.INSTANCE, SchemeStatSak.TypeRegistrationItem.EventType.UNAVAILABLE_AUTH_BY_AUTOLOGIN, CollectionsKt.arrayListOf(new SchemeStatSak.RegistrationFieldItem(SchemeStatSak.RegistrationFieldItem.Name.REASON, "", "", lpminigolotuakvmoca(lpminigolotuakvmocaVar)), new SchemeStatSak.RegistrationFieldItem(SchemeStatSak.RegistrationFieldItem.Name.AUTOLOGIN_ID, "", "", this.lastAutoLoginId), new SchemeStatSak.RegistrationFieldItem(SchemeStatSak.RegistrationFieldItem.Name.EVENT_DURATION, String.valueOf(this.startTime), String.valueOf(jCurrentTimeMillis), null, 8, null)), null, null, null, null, null, null, 252, null);
        }
    }

    public final void start() {
        this.startTime = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence lpminigolotuakvmoca(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return "\"" + ((Number) it.getFirst()).intValue() + "\":\"" + ((String) it.getSecond()) + '\"';
    }
}
