package ru.mail.serverapi;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.PoolConstants;
import ru.mail.network.AccountAndIDParams;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class MailCommandStatus {

    /* JADX INFO: compiled from: ProGuard */
    public static class ATTEMPTS_EXCEEDED extends CommandStatus.ERROR<Integer> {
        public ATTEMPTS_EXCEEDED(Integer num) {
            super(num);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class EMPTY_RESULT_ERROR extends CommandStatus.ERROR<Void> {
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_ATTACH_NOT_FOUND extends CommandStatus.ERROR<Void> {
        public ERROR_ATTACH_NOT_FOUND() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_CLOUD_IS_FULL extends CommandStatus.ERROR<Void> {
        public ERROR_CLOUD_IS_FULL() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_DATE_RANGE extends CommandStatus.SIMPLE_ERROR<Void> {
        public ERROR_DATE_RANGE() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_FOLDER_NOT_EXIST extends CommandStatus.ERROR<Long> {
        public ERROR_FOLDER_NOT_EXIST(Long l10) {
            super(l10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class EXPANDED_SIMPLE_ERROR<V> extends CommandStatus.SIMPLE_ERROR<V> {
        private final String mId;

        public String getId() {
            return this.mId;
        }

        private EXPANDED_SIMPLE_ERROR(String str, V v10) {
            super(v10);
            this.mId = str;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class FAILED_BACKEND_QUOTE<F> extends EXPANDED_SIMPLE_ERROR<F> {
        public FAILED_BACKEND_QUOTE(String str, F f10) {
            super(str, f10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class IMAP_ACTIVATION_NOT_READY extends CommandStatus.ERROR<Void> {
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class INVALID_SEND_DATE<V> extends EXPANDED_SIMPLE_ERROR<V> {
        private INVALID_SEND_DATE(String str, V v10) {
            super(str, v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class INVALID_THREAD extends CommandStatus.ERROR<String> {
        public INVALID_THREAD() {
            super(null);
        }

        public INVALID_THREAD(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MESSAGE_NOT_EXIST extends CommandStatus.ERROR<Void> {
        public MESSAGE_NOT_EXIST() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class MESSAGE_NOT_IN_THREAD extends CommandStatus.ERROR<String> {
        public MESSAGE_NOT_IN_THREAD(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NO_AUTH_BIND_REQUIRED<T> extends NetworkCommandStatus.NO_AUTH<T> {
        public NO_AUTH_BIND_REQUIRED(NoAuthInfo noAuthInfo) {
            super(noAuthInfo);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NO_AUTH_TWO_STEP_REQUIRED<T> extends NetworkCommandStatus.NO_AUTH<T> {
        public NO_AUTH_TWO_STEP_REQUIRED(NoAuthInfo noAuthInfo) {
            super(noAuthInfo);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NO_BODY extends CommandStatus.ERROR<Void> {
        public NO_BODY() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NO_HEADER extends CommandStatus.ERROR<Void> {
        public NO_HEADER() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NO_MSG extends CommandStatus.ERROR<AccountAndIDParams<String>> {
        public NO_MSG(AccountAndIDParams accountAndIDParams) {
            super(accountAndIDParams);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class QR_TOKEN_NOT_FOUND extends CommandStatus.SIMPLE_ERROR<Void> {
        public QR_TOKEN_NOT_FOUND() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class STORAGE_UNAVAILABLE extends CommandStatus.ERROR<Exception> {
        public STORAGE_UNAVAILABLE(Exception exc) {
            super(exc);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class SWITCH_TO_IMAP extends CommandStatus.ERROR<String> {
        public SWITCH_TO_IMAP(@Nullable String str) {
            super(str);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    public static abstract class SimpleErrorStatusFactory {
        private static final /* synthetic */ SimpleErrorStatusFactory[] $VALUES = $values();
        public static final SimpleErrorStatusFactory DEFAULT;
        public static final SimpleErrorStatusFactory FAILED_BACKEND_QUOTE;
        public static final SimpleErrorStatusFactory INVALID_SEND_DATE;

        /* JADX INFO: renamed from: ru.mail.serverapi.MailCommandStatus$SimpleErrorStatusFactory$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes11.dex */
        final enum AnonymousClass1 extends SimpleErrorStatusFactory {
            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }

            @Override // ru.mail.serverapi.MailCommandStatus.SimpleErrorStatusFactory
            public <D> INVALID_SEND_DATE<D> getStatus(D d10) {
                return new INVALID_SEND_DATE<>(name(), d10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.serverapi.MailCommandStatus$SimpleErrorStatusFactory$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes11.dex */
        final enum AnonymousClass2 extends SimpleErrorStatusFactory {
            @Override // ru.mail.serverapi.MailCommandStatus.SimpleErrorStatusFactory
            public <D> EXPANDED_SIMPLE_ERROR<D> getStatus(D d10) {
                return new FAILED_BACKEND_QUOTE(name(), d10);
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.serverapi.MailCommandStatus$SimpleErrorStatusFactory$3, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        /* JADX INFO: loaded from: classes11.dex */
        final enum AnonymousClass3 extends SimpleErrorStatusFactory {
            @Override // ru.mail.serverapi.MailCommandStatus.SimpleErrorStatusFactory
            public <D> EXPANDED_SIMPLE_ERROR<D> getStatus(D d10) {
                return new EXPANDED_SIMPLE_ERROR<>(name(), d10);
            }

            private AnonymousClass3(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ SimpleErrorStatusFactory[] $values() {
            return new SimpleErrorStatusFactory[]{INVALID_SEND_DATE, FAILED_BACKEND_QUOTE, DEFAULT};
        }

        static {
            INVALID_SEND_DATE = new AnonymousClass1("INVALID_SEND_DATE", 0);
            FAILED_BACKEND_QUOTE = new AnonymousClass2("FAILED_BACKEND_QUOTE", 1);
            DEFAULT = new AnonymousClass3(PoolConstants.DEFAULT, 2);
        }

        public static SimpleErrorStatusFactory get(String str) {
            for (SimpleErrorStatusFactory simpleErrorStatusFactory : values()) {
                if (TextUtils.equals(str, simpleErrorStatusFactory.name())) {
                    return simpleErrorStatusFactory;
                }
            }
            return DEFAULT;
        }

        public static SimpleErrorStatusFactory valueOf(String str) {
            return (SimpleErrorStatusFactory) Enum.valueOf(SimpleErrorStatusFactory.class, str);
        }

        public static SimpleErrorStatusFactory[] values() {
            return (SimpleErrorStatusFactory[]) $VALUES.clone();
        }

        public abstract <D> EXPANDED_SIMPLE_ERROR<D> getStatus(D d10);

        private SimpleErrorStatusFactory(String str, int i10) {
            super(str, i10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class THREAD_NOT_EXIST extends CommandStatus.ERROR<String> {
        public THREAD_NOT_EXIST() {
            super(null);
        }

        public THREAD_NOT_EXIST(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class WAIT_AND_RETRY extends EXPANDED_SIMPLE_ERROR<Long> {
        public WAIT_AND_RETRY(String str, long j10) {
            super(str, Long.valueOf(j10));
        }
    }
}
