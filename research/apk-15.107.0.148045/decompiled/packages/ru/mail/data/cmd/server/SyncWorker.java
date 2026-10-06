package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.hilt.work.HiltWorker;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkerParameters;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.auth.am.AccountManagerDelegate;
import ru.mail.dependencies.SyncAdapterEntryPoint;
import ru.mail.logic.sync.BaseMailSdkWorker;
import ru.mail.logic.sync.PrefetcherSyncAdapterDelegate;
import ru.mail.logic.sync.SyncByWorkerImpl;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushProcessor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@HiltWorker
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u001d\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u000e\u001a\u00020\u000fH\u0096@¢\u0006\u0002\u0010\u0010J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lru/mail/data/cmd/server/SyncWorker;", "Lru/mail/logic/sync/BaseMailSdkWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "<init>", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "syncAdapterDelegate", "Lru/mail/logic/sync/PrefetcherSyncAdapterDelegate;", "getSyncAdapterDelegate", "()Lru/mail/logic/sync/PrefetcherSyncAdapterDelegate;", "syncAdapterDelegate$delegate", "Lkotlin/Lazy;", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retrieveBundle", "Landroid/os/Bundle;", "retrieveAccount", "Landroid/accounts/Account;", "bundle", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSyncWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SyncWorker.kt\nru/mail/data/cmd/server/SyncWorker\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,149:1\n222#2:150\n*S KotlinDebug\n*F\n+ 1 SyncWorker.kt\nru/mail/data/cmd/server/SyncWorker\n*L\n80#1:150\n*E\n"})
public final class SyncWorker extends BaseMailSdkWorker {

    @NotNull
    private static final String ACCOUNT = "MAIL_SYNC_WORKER_ACCOUNT";

    @NotNull
    private static final String BUNDLE = "MAIL_SYNC_WORKER_BUNDLE";

    @NotNull
    private static final String WORKER_ID = "MAIL_SYNC_WORKER_ID";

    @NotNull
    private final Context context;

    /* JADX INFO: renamed from: syncAdapterDelegate$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy syncAdapterDelegate;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("SyncWorker");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0017B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0011H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0016\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lru/mail/data/cmd/server/SyncWorker$Companion;", "", "<init>", "()V", "LOG", "Lru/mail/util/log/Log;", "WORKER_ID", "", "BUNDLE", "ACCOUNT", "start", "", "context", "Landroid/content/Context;", "account", "Landroid/accounts/Account;", PushProcessor.DATAKEY_EXTRAS, "Landroid/os/Bundle;", "bundleToBytes", "", "bundle", "bytesToBundle", "bytes", "SyncWorkerAccount", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSyncWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SyncWorker.kt\nru/mail/data/cmd/server/SyncWorker$Companion\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 OneTimeWorkRequest.kt\nandroidx/work/OneTimeWorkRequestKt\n*L\n1#1,149:1\n205#2:150\n105#3:151\n*S KotlinDebug\n*F\n+ 1 SyncWorker.kt\nru/mail/data/cmd/server/SyncWorker$Companion\n*L\n105#1:150\n119#1:151\n*E\n"})
    public static final class Companion {

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0083\b\u0018\u0000  2\u00020\u0001:\u0002\u001f B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B/\b\u0010\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u0005\u0010\u000bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\bHÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J%\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006!"}, d2 = {"Lru/mail/data/cmd/server/SyncWorker$Companion$SyncWorkerAccount;", "", "type", "", "name", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "seen0", "", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getType", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$mails_release", "$serializer", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @Serializable
        static final /* data */ class SyncWorkerAccount {

            /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
            @NotNull
            public static final Companion INSTANCE = new Companion(null);

            @NotNull
            private final String name;

            @NotNull
            private final String type;

            /* JADX INFO: compiled from: ProGuard */
            @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lru/mail/data/cmd/server/SyncWorker$Companion$SyncWorkerAccount$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lru/mail/data/cmd/server/SyncWorker$Companion$SyncWorkerAccount;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
            public static final class Companion {
                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                @NotNull
                public final KSerializer<SyncWorkerAccount> serializer() {
                    return SyncWorker$Companion$SyncWorkerAccount$$serializer.INSTANCE;
                }

                private Companion() {
                }
            }

            public /* synthetic */ SyncWorkerAccount(int i10, String str, String str2, SerializationConstructorMarker serializationConstructorMarker) {
                if (3 != (i10 & 3)) {
                    PluginExceptionsKt.throwMissingFieldException(i10, 3, SyncWorker$Companion$SyncWorkerAccount$$serializer.INSTANCE.getDescriptor());
                }
                this.type = str;
                this.name = str2;
            }

            public static /* synthetic */ SyncWorkerAccount copy$default(SyncWorkerAccount syncWorkerAccount, String str, String str2, int i10, Object obj) {
                if ((i10 & 1) != 0) {
                    str = syncWorkerAccount.type;
                }
                if ((i10 & 2) != 0) {
                    str2 = syncWorkerAccount.name;
                }
                return syncWorkerAccount.copy(str, str2);
            }

            @JvmStatic
            public static final /* synthetic */ void write$Self$mails_release(SyncWorkerAccount self, CompositeEncoder output, SerialDescriptor serialDesc) {
                output.encodeStringElement(serialDesc, 0, self.type);
                output.encodeStringElement(serialDesc, 1, self.name);
            }

            @NotNull
            /* JADX INFO: renamed from: component1, reason: from getter */
            public final String getType() {
                return this.type;
            }

            @NotNull
            /* JADX INFO: renamed from: component2, reason: from getter */
            public final String getName() {
                return this.name;
            }

            @NotNull
            public final SyncWorkerAccount copy(@NotNull String type, @NotNull String name) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(name, "name");
                return new SyncWorkerAccount(type, name);
            }

            public boolean equals(@Nullable Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SyncWorkerAccount)) {
                    return false;
                }
                SyncWorkerAccount syncWorkerAccount = (SyncWorkerAccount) other;
                return Intrinsics.areEqual(this.type, syncWorkerAccount.type) && Intrinsics.areEqual(this.name, syncWorkerAccount.name);
            }

            @NotNull
            public final String getName() {
                return this.name;
            }

            @NotNull
            public final String getType() {
                return this.type;
            }

            public int hashCode() {
                return (this.type.hashCode() * 31) + this.name.hashCode();
            }

            @NotNull
            public String toString() {
                return "SyncWorkerAccount(type=" + this.type + ", name=" + this.name + ")";
            }

            public SyncWorkerAccount(@NotNull String type, @NotNull String name) {
                Intrinsics.checkNotNullParameter(type, "type");
                Intrinsics.checkNotNullParameter(name, "name");
                this.type = type;
                this.name = name;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final byte[] bundleToBytes(Bundle bundle) {
            Parcel parcelObtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(parcelObtain, "obtain(...)");
            parcelObtain.writeBundle(bundle);
            byte[] bArrMarshall = parcelObtain.marshall();
            parcelObtain.recycle();
            Intrinsics.checkNotNull(bArrMarshall);
            return bArrMarshall;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Bundle bytesToBundle(byte[] bytes) {
            Parcel parcelObtain = Parcel.obtain();
            Intrinsics.checkNotNullExpressionValue(parcelObtain, "obtain(...)");
            parcelObtain.unmarshall(bytes, 0, bytes.length);
            parcelObtain.setDataPosition(0);
            Bundle bundle = parcelObtain.readBundle(SyncByWorkerImpl.class.getClassLoader());
            parcelObtain.recycle();
            return bundle;
        }

        public final void start(@NotNull Context context, @NotNull Account account, @NotNull Bundle extras) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(account, "account");
            Intrinsics.checkNotNullParameter(extras, "extras");
            Json.Companion r10 = Json.INSTANCE;
            String type = account.type;
            Intrinsics.checkNotNullExpressionValue(type, "type");
            String name = account.name;
            Intrinsics.checkNotNullExpressionValue(name, "name");
            SyncWorkerAccount syncWorkerAccount = new SyncWorkerAccount(type, name);
            r10.getSerializersModule();
            extras.putString(SyncWorker.ACCOUNT, r10.encodeToString(SyncWorkerAccount.INSTANCE.serializer(), syncWorkerAccount));
            WorkManager.INSTANCE.getInstance(context).enqueueUniqueWork(SyncWorker.WORKER_ID, ExistingWorkPolicy.REPLACE, new OneTimeWorkRequest.Builder((Class<? extends ListenableWorker>) SyncWorker.class).setInputData(new Data.Builder().putByteArray(SyncWorker.BUNDLE, bundleToBytes(extras)).build()).build());
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @AssistedInject
    public SyncWorker(@Assisted @NotNull Context context, @Assisted @NotNull WorkerParameters workerParams) {
        super(context, workerParams);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(workerParams, "workerParams");
        this.context = context;
        this.syncAdapterDelegate = LazyKt.lazy(new Function0() { // from class: ru.mail.data.cmd.server.s
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SyncWorker.syncAdapterDelegate_delegate$lambda$0(this.f85220a);
            }
        });
    }

    private final PrefetcherSyncAdapterDelegate getSyncAdapterDelegate() {
        return (PrefetcherSyncAdapterDelegate) this.syncAdapterDelegate.getValue();
    }

    private final Account retrieveAccount(Bundle bundle) {
        String string = bundle.getString(ACCOUNT);
        if (string == null) {
            return null;
        }
        Json.Companion r10 = Json.INSTANCE;
        r10.getSerializersModule();
        Companion.SyncWorkerAccount syncWorkerAccount = (Companion.SyncWorkerAccount) r10.decodeFromString(Companion.SyncWorkerAccount.INSTANCE.serializer(), string);
        return new Account(syncWorkerAccount.getName(), syncWorkerAccount.getType());
    }

    private final Bundle retrieveBundle() {
        byte[] byteArray = getInputData().getByteArray(BUNDLE);
        if (byteArray == null) {
            return null;
        }
        return INSTANCE.bytesToBundle(byteArray);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefetcherSyncAdapterDelegate syncAdapterDelegate_delegate$lambda$0(SyncWorker syncWorker) {
        return SyncAdapterEntryPoint.INSTANCE.provideAccountManagerSyncConfig(syncWorker.context);
    }

    @Override // androidx.work.CoroutineWorker
    @Nullable
    public Object doWork(@NotNull Continuation<? super ListenableWorker.Result> continuation) {
        Object objM13123constructorimpl;
        Account account;
        Log log = LOG;
        log.v("starts");
        try {
            Result.Companion companion = Result.INSTANCE;
            AccountManagerDelegate accountManagerDelegate = MailSdkEntryPoint.INSTANCE.accountManagerDelegate(this.context);
            Bundle bundleRetrieveBundle = retrieveBundle();
            if (bundleRetrieveBundle == null) {
                log.v("no bundle present");
                ListenableWorker.Result resultFailure = ListenableWorker.Result.failure();
                Intrinsics.checkNotNullExpressionValue(resultFailure, "failure(...)");
                return resultFailure;
            }
            Account accountRetrieveAccount = retrieveAccount(bundleRetrieveBundle);
            if (accountRetrieveAccount == null) {
                log.v("account is null");
                ListenableWorker.Result resultFailure2 = ListenableWorker.Result.failure();
                Intrinsics.checkNotNullExpressionValue(resultFailure2, "failure(...)");
                return resultFailure2;
            }
            Account[] accounts = accountManagerDelegate.getAccounts();
            int length = accounts.length;
            int i10 = 0;
            while (true) {
                if (i10 >= length) {
                    account = null;
                    break;
                }
                account = accounts[i10];
                if (Intrinsics.areEqual(account.name, accountRetrieveAccount.name) && Intrinsics.areEqual(account.type, accountRetrieveAccount.type)) {
                    break;
                }
                i10++;
            }
            if (account != null) {
                getSyncAdapterDelegate().onPerformSync(accountRetrieveAccount, bundleRetrieveBundle);
                objM13123constructorimpl = Result.m13123constructorimpl(ListenableWorker.Result.success());
                Throwable thM13126exceptionOrNullimpl = Result.m13126exceptionOrNullimpl(objM13123constructorimpl);
                if (thM13126exceptionOrNullimpl != null) {
                    LOG.e("failure on sync", thM13126exceptionOrNullimpl);
                }
                return Result.m13128isFailureimpl(objM13123constructorimpl) ? ListenableWorker.Result.failure() : objM13123constructorimpl;
            }
            LOG.v(accountRetrieveAccount + " not found in AccountManager");
            ListenableWorker.Result resultFailure3 = ListenableWorker.Result.failure();
            Intrinsics.checkNotNullExpressionValue(resultFailure3, "failure(...)");
            return resultFailure3;
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.INSTANCE;
            objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
        }
    }
}
