package ru.mail.data.cmd.server;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.auth.Authenticator;
import ru.mail.data.dao.AuthorityProvider;
import ru.mail.domain.AddressBookConfig;
import ru.mail.locator.Locator;
import ru.mail.logic.sync.AddressBookUpdater;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.march.internal.work.WorkRequest;
import ru.mail.march.internal.work.WorkScheduler;
import ru.mail.sdk.MailSdkEntryPoint;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class RequestSyncCommand extends SyncControlCommand<Params, CommandStatus<?>> {
    public static final String BUNDLE_ID = "bundle_id";
    private static final Log LOG = Log.getLog("RequestSyncCommand");
    private static final AtomicInteger sId = new AtomicInteger(0);

    /* JADX INFO: compiled from: ProGuard */
    public static class Params {
        public static final String BUNDLE_ACCOUNT = "bundle_account";
        private static final int NO_PERIODICITY = 0;
        private final String mAuthority;
        private final Bundle mExtras;
        private final int mPeriodicitySeconds;

        public Params(Account account, String str, Bundle bundle) {
            this(account, str, bundle, 0);
        }

        public boolean equalBundles(Bundle bundle, Bundle bundle2) {
            if (bundle.size() != bundle2.size()) {
                return false;
            }
            HashSet<String> hashSet = new HashSet(bundle.keySet());
            hashSet.addAll(bundle2.keySet());
            for (String str : hashSet) {
                if (bundle.containsKey(str) && bundle2.containsKey(str)) {
                    Object obj = bundle.get(str);
                    Object obj2 = bundle2.get(str);
                    if ((obj instanceof Bundle) && (obj2 instanceof Bundle) && !equalBundles((Bundle) obj, (Bundle) obj2)) {
                        return false;
                    }
                    if (obj == null) {
                        if (obj2 != null) {
                            return false;
                        }
                    } else if (!obj.equals(obj2)) {
                    }
                }
                return false;
            }
            return true;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Params params = (Params) obj;
            if (this.mPeriodicitySeconds == params.mPeriodicitySeconds && this.mAuthority.equals(params.mAuthority)) {
                return equalBundles(this.mExtras, params.mExtras);
            }
            return false;
        }

        public void forceSync(boolean z10) {
            this.mExtras.putBoolean("force", true);
            if (z10) {
                this.mExtras.putBoolean("expedited", true);
            }
        }

        @NonNull
        @NotNull
        public Account getAccount() {
            return (Account) this.mExtras.getParcelable(BUNDLE_ACCOUNT);
        }

        public int getPeriodicity() {
            return this.mPeriodicitySeconds;
        }

        public int hashCode() {
            return (((this.mAuthority.hashCode() * 31) + hashCodeBundle(this.mExtras)) * 31) + this.mPeriodicitySeconds;
        }

        public int hashCodeBundle(Bundle bundle) {
            int i10;
            int iHashCodeBundle;
            Iterator<String> it = bundle.keySet().iterator();
            int i11 = 0;
            while (it.hasNext()) {
                Object obj = bundle.get(it.next());
                if (obj instanceof Bundle) {
                    i10 = i11 * 31;
                    iHashCodeBundle = hashCodeBundle((Bundle) obj);
                } else if (obj != null) {
                    i10 = i11 * 31;
                    iHashCodeBundle = obj.hashCode();
                }
                i11 = i10 + iHashCodeBundle;
            }
            return i11;
        }

        public boolean isPeriodical() {
            return this.mPeriodicitySeconds != 0;
        }

        public String toString() {
            return "Params{mAuthority='" + this.mAuthority + "', mExtras=" + this.mExtras + ", mPeriodicitySeconds=" + this.mPeriodicitySeconds + AbstractJsonLexerKt.END_OBJ;
        }

        public Params(Account account, String str, Bundle bundle, int i10) {
            this.mAuthority = str;
            this.mExtras = bundle;
            this.mPeriodicitySeconds = i10;
            if (account != null) {
                bundle.putParcelable(BUNDLE_ACCOUNT, account);
            }
        }
    }

    public RequestSyncCommand(Context context, Params params) {
        super(params, context);
    }

    private Bundle putTaskId(Bundle bundle, int i10) {
        Bundle bundle2 = new Bundle(bundle);
        bundle2.putInt("bundle_id", i10);
        return bundle2;
    }

    private void requestSync() {
        LOG.d("request sync strategy authority : " + getParams().mAuthority);
        Authenticator.getAccountManagerWrapper(this.mContext).requestSync(getParams().getAccount(), getParams().mAuthority, getParams().mExtras);
    }

    private void submitJob() {
        ContentProvider contentProviderObtainProvider = ContentProvider.obtainProvider(getParams().mAuthority);
        int iIncrementAndGet = sId.incrementAndGet();
        WorkRequest.Builder builderConstraints = contentProviderObtainProvider.getWorkBuilder(putTaskId(getParams().mExtras, iIncrementAndGet)).constraints(WorkRequest.Constraints.NETWORK);
        if (getParams().isPeriodical()) {
            builderConstraints.period(getParams().getPeriodicity(), TimeUnit.SECONDS);
            LOG.d(String.format("Periodical task with id %d scheduled for authority %s with period of %dms", Integer.valueOf(iIncrementAndGet), getParams().mAuthority, Integer.valueOf(getParams().getPeriodicity())));
        } else {
            LOG.d(String.format("Single task with id %d scheduled for authority %s", Integer.valueOf(iIncrementAndGet), getParams().mAuthority));
        }
        LOG.d("submitJob() on WorkScheduler");
        ((WorkScheduler) Locator.locate(this.mContext, WorkScheduler.class)).schedule(builderConstraints.getRequest());
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NonNull
    protected CommandExecutor selectCodeExecutor(ExecutorSelector executorSelector) {
        return executorSelector.getSingleCommandExecutor("IPC");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    public CommandStatus<?> onExecute(ExecutorSelector executorSelector) {
        String str = getParams().mAuthority;
        boolean zEquals = str.equals(AuthorityProvider.getContactsContentProviderAuthority(this.mContext));
        AddressBookConfig companion = AddressBookConfig.INSTANCE.getInstance();
        if (!zEquals || !AddressBookUpdater.useAddressBookUpdaterDirectly || companion == null) {
            if (ContentProvider.isJobSchedulingStrategyAllowed(str)) {
                submitJob();
            } else {
                requestSync();
            }
            return new CommandStatus.OK();
        }
        AddressBookUpdater addressBookUpdater = MailSdkEntryPoint.addressBookUpdater(this.mContext);
        Account account = getParams().getAccount();
        if (addressBookUpdater.needUpdateAddressBook(account)) {
            addressBookUpdater.update(companion, account);
        }
        return new CommandStatus.OK();
    }
}
