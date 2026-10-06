package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class CommandStatus<V> {
    private final V mData;

    /* JADX INFO: compiled from: ProGuard */
    public static class CANCELLED<V> extends CommandStatus<V> {
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR<V> extends CommandStatus<V> {
        public ERROR() {
        }

        public ERROR(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    public static class NOT_COMPLETED extends CommandStatus<Void> {
        public NOT_COMPLETED() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NOT_EXECUTED<V> extends CommandStatus<V> {
        public NOT_EXECUTED() {
        }

        public NOT_EXECUTED(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class NOT_MODIFIED<V> extends OK<V> {
        public NOT_MODIFIED(V v10) {
            super(v10);
        }

        public NOT_MODIFIED() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class OK<V> extends CommandStatus<V> {
        public OK() {
        }

        public OK(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class SIMPLE_ERROR<V> extends ERROR<V> {
        public SIMPLE_ERROR(V v10) {
            super(v10);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    public static class UNSUPPORTED_OPERATION extends ERROR<Void> {
    }

    protected CommandStatus() {
        this.mData = null;
    }

    public V getData() {
        V v10 = this.mData;
        if (v10 != null) {
            return v10;
        }
        throw new IllegalStateException("requested data == null !");
    }

    public boolean hasData() {
        return this.mData != null;
    }

    public String toString() {
        return "class= " + getClass().getSimpleName() + " data= " + String.valueOf(this.mData);
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class ERROR_WITH_STATUS_CODE extends ERROR<Integer> {
        private boolean isOk0;

        public ERROR_WITH_STATUS_CODE(int i10) {
            super(Integer.valueOf(i10));
            this.isOk0 = false;
        }

        public boolean isOk0() {
            return this.isOk0;
        }

        public ERROR_WITH_STATUS_CODE(int i10, boolean z10) {
            super(Integer.valueOf(i10));
            this.isOk0 = z10;
        }
    }

    protected CommandStatus(V v10) {
        this.mData = v10;
    }
}
