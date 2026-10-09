package ru.mail.util.push;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.vk.commonid.CommonIdProvider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.core.di.AppCoreModuleEntryPoint;
import ru.mail.mailbox.cmd.CancelledException;
import ru.mail.mailbox.cmd.ObservableFuture;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.mail.util.push.notifier.PushMessageReceivedNotifier;
import ru.mail.util.push.notifier.PushTokenRefreshedNotifier;
import ru.mail.util.push.provider.PushInfoProvider;
import ru.mail.util.push.token.PushTokenManager;
import ru.mail.util.push.updater.PushUpdater;
import ru.mail.utils.safeutils.Handler;
import ru.mail.utils.safeutils.PackageManagerUtil;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public abstract class PushMessagesTransport implements PushTokenRefreshedNotifier.Listener, PushMessageReceivedNotifier.Listener, PushInfoProvider {
    private static final long DEFAULT_HANDLERS_WAIT_TIMEOUT_SECONDS = 20;
    private static final String HANDLERS_WAIT_TIMEOUT_KEY = "push_handlers_wait_timeout_seconds";
    private static final Log LOG = Log.getLog("PushMessagesTransport");
    private static final long MAX_HANDLERS_WAIT_TIMEOUT_SECONDS = 300;
    private static final Formats.ParamFormat TOKEN_LOG_FORMAT;
    private static final String TOKEN_LOG_PREFIX = "token";
    private static final LogFilter sLogFilter;
    private final AvailabilityChecker mAvailabilityChecker;
    private final Context mContext;
    private final PackageManagerUtil.RequestInitiator mPackageManager;
    private volatile boolean mPerformingRequest;
    private final PushTokenManager mPushTokenManager;
    private final Set<PushMessagesEventHandler> mPushMessageEventHandlers = new HashSet();
    private final Set<RegistrationListener> mRegistrationListeners = new HashSet();
    private final SortedSet<Integer> mUniquePushIds = new TreeSet();
    private final Object mPushDeliveryLock = new Object();
    private final PushUpdater mPushUpdater = createPushUpdater();

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    public static class BadTokenException extends RuntimeException {
        public BadTokenException() {
            super("Token had been reseted after creating");
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    private static class CallDroppedException extends RuntimeException {
        public CallDroppedException() {
            super("Call dropped because of another request being performed");
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public interface PushMessagesEventHandler {
        List<ObservableFuture<Void>> handlePushMessagesReceived(List<PushMessage> list);
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    private static class RegisterAsyncTask extends AsyncTask<String, Void, Exception> {
        private final PushMessagesTransport mPushMessagesTransport;

        /* JADX INFO: compiled from: ProGuard */
        private class RegisterHandler implements Handler<PackageManager, Exception> {
            public RegisterHandler() {
            }

            @Override // ru.mail.utils.safeutils.Handler
            public Exception call(PackageManager packageManager) {
                return RegisterAsyncTask.this.performRegister();
            }
        }

        RegisterAsyncTask(PushMessagesTransport pushMessagesTransport) {
            this.mPushMessagesTransport = pushMessagesTransport;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Nullable
        public Exception performRegister() {
            try {
                refreshSdkDeviceId();
                this.mPushMessagesTransport.mPushTokenManager.saveToken(this.mPushMessagesTransport.getPushKitWrapper().getPushTokenFromPushKit());
                return null;
            } catch (Exception e10) {
                PushMessagesTransport.LOG.e("Failed to performRegister", e10);
                return e10;
            }
        }

        private void refreshSdkDeviceId() {
            try {
                CommonIdProvider.sync(this.mPushMessagesTransport.getContext(), 10000L);
                PushMessagesTransport.LOG.i("SDK device ID has been synced");
            } catch (Exception e10) {
                PushMessagesTransport.LOG.e("Failed to sync SDK device ID", e10);
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mPushMessagesTransport.mPerformingRequest = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(String... strArr) {
            return (Exception) this.mPushMessagesTransport.mPackageManager.doWithPackageManager(new RegisterHandler()).onError(new ThrowableExceptionHandler()).perform();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Exception exc) {
            this.mPushMessagesTransport.mPerformingRequest = false;
            String token = this.mPushMessagesTransport.mPushTokenManager.getToken();
            PushMessagesTransport.LOG.d("onPostExecute, new " + PushMessagesTransport.sLogFilter.filter(PushMessagesTransport.TOKEN_LOG_FORMAT.getFormattedMsg(token)));
            if (exc != null) {
                this.mPushMessagesTransport.notifyCannotRegister(exc);
            } else if (TextUtils.isEmpty(token)) {
                this.mPushMessagesTransport.notifyCannotRegister(new BadTokenException());
            } else {
                this.mPushMessagesTransport.notifyRegistered();
                PushMessagesTransport.LOG.d("onPostExecute, notifyRegistered");
            }
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    private static class ThrowableExceptionHandler implements Handler<Throwable, Exception> {
        private ThrowableExceptionHandler() {
        }

        @Override // ru.mail.utils.safeutils.Handler
        public Exception call(Throwable th2) {
            return new Exception(th2);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    private static class UnregisterAsyncTask extends AsyncTask<Void, Void, Exception> {
        private final PushMessagesTransport mPushMessagesTransport;

        /* JADX INFO: compiled from: ProGuard */
        class UnregisterHandler implements Handler<PackageManager, Exception> {
            UnregisterHandler() {
            }

            @Override // ru.mail.utils.safeutils.Handler
            public Exception call(PackageManager packageManager) {
                return UnregisterAsyncTask.this.performUnregister();
            }
        }

        UnregisterAsyncTask(PushMessagesTransport pushMessagesTransport) {
            this.mPushMessagesTransport = pushMessagesTransport;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Nullable
        public Exception performUnregister() {
            try {
                this.mPushMessagesTransport.mPushTokenManager.clearPushToken();
                this.mPushMessagesTransport.mPushTokenManager.clearExpiredToken();
                this.mPushMessagesTransport.getPushKitWrapper().deleteToken();
                return null;
            } catch (Exception e10) {
                return e10;
            }
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            this.mPushMessagesTransport.mPerformingRequest = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Exception doInBackground(Void... voidArr) {
            return (Exception) this.mPushMessagesTransport.mPackageManager.doWithPackageManager(new UnregisterHandler()).onError(new ThrowableExceptionHandler()).perform();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Exception exc) {
            this.mPushMessagesTransport.mPerformingRequest = false;
            if (exc != null) {
                this.mPushMessagesTransport.notifyCannotUnregister(exc);
            } else {
                this.mPushMessagesTransport.notifyUnregistered();
                PushMessagesTransport.LOG.d("onPostExecute, notifyUnregistered");
            }
        }
    }

    static {
        Formats.ParamFormat paramFormatNewUrlFormat = Formats.newUrlFormat("token");
        TOKEN_LOG_FORMAT = paramFormatNewUrlFormat;
        sLogFilter = new LogFilter(paramFormatNewUrlFormat);
    }

    public PushMessagesTransport(@NonNull Context context, @NonNull PushTokenRefreshedNotifier pushTokenRefreshedNotifier, @NonNull PushMessageReceivedNotifier pushMessageReceivedNotifier, @NonNull PushTokenManager pushTokenManager, @NonNull AvailabilityChecker availabilityChecker) {
        this.mContext = context;
        this.mPushTokenManager = pushTokenManager;
        this.mAvailabilityChecker = availabilityChecker;
        this.mPackageManager = PackageManagerUtil.from(context);
        pushTokenRefreshedNotifier.addListener(this, getPushType());
        pushMessageReceivedNotifier.addListener(this, getPushType(), true);
    }

    private void collectUniquePushIds(List<PushMessage> list) {
        Iterator<PushMessage> it = list.iterator();
        while (it.hasNext()) {
            this.mUniquePushIds.add(Integer.valueOf(it.next().getEventId()));
        }
    }

    private PushUpdater createPushUpdater() {
        return getPushFactory().createUpdater(getContext(), this);
    }

    private long getHandlersWaitTimeoutSeconds() {
        return AppCoreModuleEntryPoint.configRetriever(getContext()).getLong(HANDLERS_WAIT_TIMEOUT_KEY, 20L);
    }

    private ArrayList<RegistrationListener> getRegistrationListeners() {
        return new ArrayList<>(this.mRegistrationListeners);
    }

    @VisibleForTesting
    static long resolveHandlersWaitTimeoutMillis(long j10) {
        if (j10 == 0) {
            return 0L;
        }
        return TimeUnit.SECONDS.toMillis(j10 < 0 ? 20L : Math.min(j10, 300L));
    }

    private void waitAllHandlers(List<ObservableFuture<Void>> list) {
        long jResolveHandlersWaitTimeoutMillis = resolveHandlersWaitTimeoutMillis(getHandlersWaitTimeoutSeconds());
        long jElapsedRealtime = jResolveHandlersWaitTimeoutMillis > 0 ? SystemClock.elapsedRealtime() + jResolveHandlersWaitTimeoutMillis : 0L;
        for (ObservableFuture<Void> observableFuture : list) {
            if (jElapsedRealtime > 0) {
                try {
                    observableFuture.getOrThrow(Math.max(jElapsedRealtime - SystemClock.elapsedRealtime(), 0L), TimeUnit.MILLISECONDS);
                } catch (CancelledException e10) {
                    LOG.e("Unable to handling push message", e10);
                } catch (InterruptedException e11) {
                    Thread.currentThread().interrupt();
                    LOG.e("Unable to handling push message", e11);
                    return;
                } catch (ExecutionException e12) {
                    e = e12;
                    LOG.e("Unable to handling push message", e);
                } catch (TimeoutException e13) {
                    e = e13;
                    LOG.e("Unable to handling push message", e);
                }
            } else {
                observableFuture.getOrThrow();
            }
        }
    }

    public void addListener(PushMessagesEventHandler pushMessagesEventHandler) {
        this.mPushMessageEventHandlers.add(pushMessagesEventHandler);
    }

    public void clearExpiredToken() {
        this.mPushTokenManager.clearExpiredToken();
    }

    public AvailabilityChecker getAvailabilityChecker() {
        return this.mAvailabilityChecker;
    }

    protected final Context getContext() {
        return this.mContext;
    }

    @Override // ru.mail.util.push.provider.PushInfoProvider
    @Nullable
    public String getExpiredPushToken() {
        return this.mPushTokenManager.getExpiredToken();
    }

    @Override // ru.mail.util.push.provider.PushInfoProvider
    @NonNull
    public SortedSet<Integer> getLastUniquePushIds() {
        return this.mUniquePushIds;
    }

    protected abstract PushFactory getPushFactory();

    public abstract PushKitWrapper getPushKitWrapper();

    protected abstract PushType getPushMessageType();

    @Override // ru.mail.util.push.provider.PushInfoProvider
    @Nullable
    public String getPushToken() {
        return this.mPushTokenManager.getToken();
    }

    @Override // ru.mail.util.push.provider.PushInfoProvider
    @NotNull
    public PushType getPushType() {
        return getPushMessageType();
    }

    public List<ObservableFuture<Void>> handlePushMessagesReceiveEvent(List<PushMessage> list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = new ArrayList(this.mPushMessageEventHandlers).iterator();
        while (it.hasNext()) {
            arrayList.addAll(((PushMessagesEventHandler) it.next()).handlePushMessagesReceived(list));
        }
        return arrayList;
    }

    @Override // ru.mail.util.push.provider.PushInfoProvider
    public boolean isPushSdkEnabledForAllApps() {
        return true;
    }

    public boolean isRegistered() {
        return !TextUtils.isEmpty(getPushToken());
    }

    public void launchPushUpdater() {
        this.mPushUpdater.update();
    }

    protected void notifyCannotRegister(Exception exc) {
        Iterator<RegistrationListener> it = getRegistrationListeners().iterator();
        while (it.hasNext()) {
            it.next().onCannotRegister(exc);
        }
        MailAppDependencies.analytics(getContext()).sendPushRegisterFailAnalytics(exc);
    }

    protected void notifyCannotUnregister(Exception exc) {
        Iterator<RegistrationListener> it = getRegistrationListeners().iterator();
        while (it.hasNext()) {
            it.next().onCannotUnregister(exc);
        }
        MailAppDependencies.analytics(getContext()).sendPushUnregisterFailAnalytics(exc);
    }

    protected void notifyPushIntentReceived(Map<String, String> map, @Nullable Long l10) {
        synchronized (this.mPushDeliveryLock) {
            List<PushMessage> listMakePushMessages = PushProcessor.makePushMessages(getContext(), map, l10);
            this.mUniquePushIds.clear();
            collectUniquePushIds(listMakePushMessages);
            waitAllHandlers(handlePushMessagesReceiveEvent(listMakePushMessages));
        }
    }

    protected void notifyRegistered() {
        Iterator<RegistrationListener> it = getRegistrationListeners().iterator();
        while (it.hasNext()) {
            it.next().onRegistered();
        }
        MailAppDependencies.analytics(getContext()).sendPushRegisterSuccessAnalytics();
    }

    protected void notifyUnregistered() {
        Iterator<RegistrationListener> it = getRegistrationListeners().iterator();
        while (it.hasNext()) {
            it.next().onUnregistered();
        }
        MailAppDependencies.analytics(getContext()).sendPushUnregisterSuccessAnalytics();
    }

    @Override // ru.mail.util.push.notifier.PushMessageReceivedNotifier.Listener
    public void onMessageReceived(@NotNull Map<String, String> map, @Nullable String str, @Nullable Long l10) {
        notifyPushIntentReceived(map, l10);
    }

    @Override // ru.mail.util.push.notifier.PushTokenRefreshedNotifier.Listener
    public void onNewToken(@NotNull String str) {
        this.mPushTokenManager.clearPushToken();
        launchPushUpdater();
    }

    @WorkerThread
    public synchronized boolean register() {
        if (this.mPerformingRequest) {
            notifyCannotRegister(new CallDroppedException());
            return false;
        }
        new RegisterAsyncTask(this).execute(new String[0]);
        return true;
    }

    public void removeListener(PushMessagesEventHandler pushMessagesEventHandler) {
        this.mPushMessageEventHandlers.remove(pushMessagesEventHandler);
    }

    @WorkerThread
    public synchronized void unregister() {
        try {
            if (this.mPerformingRequest) {
                notifyCannotUnregister(new CallDroppedException());
            } else {
                new UnregisterAsyncTask(this).execute(new Void[0]);
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    public void addListener(RegistrationListener registrationListener) {
        this.mRegistrationListeners.add(registrationListener);
    }

    public void removeListener(RegistrationListener registrationListener) {
        this.mRegistrationListeners.remove(registrationListener);
    }

    /* JADX INFO: compiled from: ProGuard */
    public interface RegistrationListener {
        default void onRegistered() {
        }

        default void onUnregistered() {
        }

        default void onCannotRegister(Exception exc) {
        }

        default void onCannotUnregister(Exception exc) {
        }
    }
}
