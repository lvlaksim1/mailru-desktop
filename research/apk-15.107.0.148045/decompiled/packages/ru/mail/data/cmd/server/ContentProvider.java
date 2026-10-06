package ru.mail.data.cmd.server;

import android.os.Bundle;
import ru.mail.logic.sync.OfflineSyncWorker;
import ru.mail.logic.sync.PollLocalPushesCommand;
import ru.mail.logic.sync.PollLocalPushesWorker;
import ru.mail.logic.sync.SyncSystemContactsWorker;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.utils.serialization.SerializableBundle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public abstract class ContentProvider {
    private final String mAuthority;
    private final String mSyncAction;
    public static final ContentProvider OFFLINE = new AnonymousClass1("OFFLINE", 0, BuildConfigVariablesHolder.offlineContentProviderAuthority, BuildConfigVariablesHolder.contentProviderActionSyncOffline);
    public static final ContentProvider CONTACTS_SYSTEM = new AnonymousClass2("CONTACTS_SYSTEM", 1, "com.android.contacts", BuildConfigVariablesHolder.contentProviderActionSyncContactsSystem);
    public static final ContentProvider LOCAL_PUSHES = new AnonymousClass3("LOCAL_PUSHES", 2, PollLocalPushesCommand.AUTHORITY, BuildConfigVariablesHolder.contentProviderActionSyncLocalPushes);
    private static final /* synthetic */ ContentProvider[] $VALUES = $values();

    /* JADX INFO: renamed from: ru.mail.data.cmd.server.ContentProvider$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass1 extends ContentProvider {
        @Override // ru.mail.data.cmd.server.ContentProvider
        public WorkRequest.Builder getWorkBuilder(Bundle bundle) {
            OfflineSyncWorker.Params params = new OfflineSyncWorker.Params();
            params.setExtra(new SerializableBundle(bundle));
            return new WorkRequest.Builder(OfflineSyncWorker.class, getWorkUniqueId()).data(params.toData());
        }

        @Override // ru.mail.data.cmd.server.ContentProvider
        public String getWorkUniqueId() {
            return OfflineSyncWorker.uniqueId;
        }

        private AnonymousClass1(String str, int i10, String str2, String str3) {
            super(str, i10, str2, str3);
        }
    }

    /* JADX INFO: renamed from: ru.mail.data.cmd.server.ContentProvider$2, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass2 extends ContentProvider {
        @Override // ru.mail.data.cmd.server.ContentProvider
        public WorkRequest.Builder getWorkBuilder(Bundle bundle) {
            SyncSystemContactsWorker.Params params = new SyncSystemContactsWorker.Params();
            params.setExtra(new SerializableBundle(bundle));
            return new WorkRequest.Builder(SyncSystemContactsWorker.class, getWorkUniqueId()).data(params.toData());
        }

        @Override // ru.mail.data.cmd.server.ContentProvider
        public String getWorkUniqueId() {
            return SyncSystemContactsWorker.uniqueId;
        }

        private AnonymousClass2(String str, int i10, String str2, String str3) {
            super(str, i10, str2, str3);
        }
    }

    /* JADX INFO: renamed from: ru.mail.data.cmd.server.ContentProvider$3, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    final enum AnonymousClass3 extends ContentProvider {
        @Override // ru.mail.data.cmd.server.ContentProvider
        public WorkRequest.Builder getWorkBuilder(Bundle bundle) {
            WorkRequest.Builder builder = new WorkRequest.Builder(PollLocalPushesWorker.class, getWorkUniqueId());
            builder.existingWorkRule(WorkRequest.ExistingWorkRule.KEEP);
            return builder;
        }

        @Override // ru.mail.data.cmd.server.ContentProvider
        public String getWorkUniqueId() {
            return PollLocalPushesWorker.uniqueId;
        }

        private AnonymousClass3(String str, int i10, String str2, String str3) {
            super(str, i10, str2, str3);
        }
    }

    private static /* synthetic */ ContentProvider[] $values() {
        return new ContentProvider[]{OFFLINE, CONTACTS_SYSTEM, LOCAL_PUSHES};
    }

    private static boolean contains(String str) {
        for (ContentProvider contentProvider : values()) {
            if (contentProvider.mAuthority.equals(str)) {
                return true;
            }
        }
        return false;
    }

    static boolean isJobSchedulingStrategyAllowed(String str) {
        return contains(str);
    }

    public static ContentProvider obtainProvider(String str) {
        for (ContentProvider contentProvider : values()) {
            if (contentProvider.mAuthority.equals(str)) {
                return contentProvider;
            }
        }
        throw new IllegalArgumentException("not found value for authority : " + str);
    }

    public static ContentProvider valueOf(String str) {
        return (ContentProvider) Enum.valueOf(ContentProvider.class, str);
    }

    public static ContentProvider[] values() {
        return (ContentProvider[]) $VALUES.clone();
    }

    public String getSyncAction() {
        return this.mSyncAction;
    }

    public abstract WorkRequest.Builder getWorkBuilder(Bundle bundle);

    public abstract String getWorkUniqueId();

    private ContentProvider(String str, int i10, String str2, String str3) {
        super(str, i10);
        this.mAuthority = str2;
        this.mSyncAction = str3;
    }
}
