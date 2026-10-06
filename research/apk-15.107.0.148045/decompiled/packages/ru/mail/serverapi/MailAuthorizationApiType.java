package ru.mail.serverapi;

import ru.mail.serverapi.retrofit.session.MpopSession;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public abstract class MailAuthorizationApiType {
    private static final /* synthetic */ MailAuthorizationApiType[] $VALUES = $values();
    public static final MailAuthorizationApiType LEGACY;
    public static final MailAuthorizationApiType LEGACY_MPOP;
    public static final MailAuthorizationApiType TORNADO;
    public static final MailAuthorizationApiType TORNADO_MPOP;

    /* JADX INFO: renamed from: ru.mail.serverapi.MailAuthorizationApiType$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass1 extends MailAuthorizationApiType {
        @Override // ru.mail.serverapi.MailAuthorizationApiType
        public <T> T create(Factory<T> factory) {
            return factory.legacy();
        }

        private AnonymousClass1(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.serverapi.MailAuthorizationApiType$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass2 extends MailAuthorizationApiType {
        @Override // ru.mail.serverapi.MailAuthorizationApiType
        public <T> T create(Factory<T> factory) {
            return factory.tornado();
        }

        private AnonymousClass2(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.serverapi.MailAuthorizationApiType$3, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass3 extends MailAuthorizationApiType {
        @Override // ru.mail.serverapi.MailAuthorizationApiType
        public <T> T create(Factory<T> factory) {
            return factory.tornadoMpop();
        }

        private AnonymousClass3(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: renamed from: ru.mail.serverapi.MailAuthorizationApiType$4, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass4 extends MailAuthorizationApiType {
        @Override // ru.mail.serverapi.MailAuthorizationApiType
        public <T> T create(Factory<T> factory) {
            return factory.legacyMpop();
        }

        private AnonymousClass4(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public interface Factory<T> {
        T legacy();

        T legacyMpop();

        T tornado();

        T tornadoMpop();
    }

    private static /* synthetic */ MailAuthorizationApiType[] $values() {
        return new MailAuthorizationApiType[]{LEGACY, TORNADO, TORNADO_MPOP, LEGACY_MPOP};
    }

    static {
        LEGACY = new AnonymousClass1(MpopSession.NAME_LEGACY, 0);
        TORNADO = new AnonymousClass2("TORNADO", 1);
        TORNADO_MPOP = new AnonymousClass3(MpopSession.NAME_TORNADO_MPOP, 2);
        LEGACY_MPOP = new AnonymousClass4("LEGACY_MPOP", 3);
    }

    public static MailAuthorizationApiType valueOf(String str) {
        return (MailAuthorizationApiType) Enum.valueOf(MailAuthorizationApiType.class, str);
    }

    public static MailAuthorizationApiType[] values() {
        return (MailAuthorizationApiType[]) $VALUES.clone();
    }

    public abstract <T> T create(Factory<T> factory);

    private MailAuthorizationApiType(String str, int i10) {
        super(str, i10);
    }
}
