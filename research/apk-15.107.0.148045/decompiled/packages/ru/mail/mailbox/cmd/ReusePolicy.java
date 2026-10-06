package ru.mail.mailbox.cmd;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class ReusePolicy {
    public abstract Class<? extends CacheController> getCacheControllerClass();

    public abstract Object getReuseKey();

    /* JADX INFO: compiled from: ProGuard */
    public static class ByCommand extends ReusePolicy {
        private final Class<? extends CacheController> mCacheControllerClass;
        private final Command<?, ?> mCommand;

        public ByCommand(Command<?, ?> command) {
            this.mCommand = command;
            this.mCacheControllerClass = DefaultCacheController.class;
        }

        @Override // ru.mail.mailbox.cmd.ReusePolicy
        public Class<? extends CacheController> getCacheControllerClass() {
            return this.mCacheControllerClass;
        }

        @Override // ru.mail.mailbox.cmd.ReusePolicy
        public Object getReuseKey() {
            return this.mCommand;
        }

        public ByCommand(Command<?, ?> command, Class<? extends CacheController> cls) {
            this.mCommand = command;
            this.mCacheControllerClass = cls;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Unique extends ReusePolicy {
        @Override // ru.mail.mailbox.cmd.ReusePolicy
        public Class<? extends CacheController> getCacheControllerClass() {
            return DefaultCacheController.class;
        }

        @Override // ru.mail.mailbox.cmd.ReusePolicy
        public Object getReuseKey() {
            return this;
        }
    }
}
