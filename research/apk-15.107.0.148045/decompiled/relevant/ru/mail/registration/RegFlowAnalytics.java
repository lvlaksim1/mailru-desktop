package ru.mail.registration;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
public class RegFlowAnalytics {
    private static final /* synthetic */ RegFlowAnalytics[] $VALUES = $values();
    public static final RegFlowAnalytics CAPTCHA;
    public static final RegFlowAnalytics NO_PHONE;
    public static final RegFlowAnalytics PHONE;
    public static final RegFlowAnalytics PHONE_RECAPTCHA;
    public static final RegFlowAnalytics RECAPTCHA;
    public static final RegFlowAnalytics VKCONNECT;

    /* JADX INFO: renamed from: ru.mail.registration.RegFlowAnalytics$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass1 extends RegFlowAnalytics {
        @Override // java.lang.Enum
        public String toString() {
            return "phone";
        }

        private AnonymousClass1(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.registration.RegFlowAnalytics$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass2 extends RegFlowAnalytics {
        @Override // java.lang.Enum
        public String toString() {
            return "nophone";
        }

        private AnonymousClass2(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.registration.RegFlowAnalytics$3, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass3 extends RegFlowAnalytics {
        @Override // java.lang.Enum
        public String toString() {
            return "captcha";
        }

        private AnonymousClass3(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.registration.RegFlowAnalytics$4, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass4 extends RegFlowAnalytics {
        @Override // java.lang.Enum
        public String toString() {
            return "recaptcha";
        }

        private AnonymousClass4(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.registration.RegFlowAnalytics$5, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass5 extends RegFlowAnalytics {
        @Override // java.lang.Enum
        public String toString() {
            return "phonerecaptcha";
        }

        private AnonymousClass5(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.registration.RegFlowAnalytics$6, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass6 extends RegFlowAnalytics {
        @Override // java.lang.Enum
        public String toString() {
            return "vkconnect";
        }

        private AnonymousClass6(String str, int i10) {
            super(str, i10);
        }
    }

    private static /* synthetic */ RegFlowAnalytics[] $values() {
        return new RegFlowAnalytics[]{PHONE, NO_PHONE, CAPTCHA, RECAPTCHA, PHONE_RECAPTCHA, VKCONNECT};
    }

    static {
        PHONE = new AnonymousClass1("PHONE", 0);
        NO_PHONE = new AnonymousClass2("NO_PHONE", 1);
        CAPTCHA = new AnonymousClass3("CAPTCHA", 2);
        RECAPTCHA = new AnonymousClass4("RECAPTCHA", 3);
        PHONE_RECAPTCHA = new AnonymousClass5("PHONE_RECAPTCHA", 4);
        VKCONNECT = new AnonymousClass6("VKCONNECT", 5);
    }

    public static RegFlowAnalytics valueOf(String str) {
        return (RegFlowAnalytics) Enum.valueOf(RegFlowAnalytics.class, str);
    }

    public static RegFlowAnalytics[] values() {
        return (RegFlowAnalytics[]) $VALUES.clone();
    }

    private RegFlowAnalytics(String str, int i10) {
        super(str, i10);
    }
}
