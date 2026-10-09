package ru.mail.setup;

import android.app.Application;
import android.os.Looper;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import java.util.Collection;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Descriptions;
import ru.mail.locator.Locator;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogCollector;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.component.PushComponent;
import ru.mail.util.push.gcm.PushAsserterThrowable;
import ru.mail.util.push.gcm.PushExceptionHandler;
import ru.mail.utils.analytics.SessionTracker;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class SetUpExceptionHandler implements SetUp {
    public static /* synthetic */ boolean a(Application application, Thread thread, Throwable th2) {
        boolean z10 = (th2 instanceof RemoteException) || (th2.getCause() instanceof RemoteException);
        boolean z11 = thread == Looper.getMainLooper().getThread();
        LogCollector logCollector = (LogCollector) Locator.from(application).locate(LogCollector.class);
        if (z10) {
            AsserterFactory.createAsserter(((AsserterConfigFactory) Locator.locate(application, AsserterConfigFactory.class)).createAsserterConfiguration("set_up_exception_handler_asserter")).fail("RemoteException on thread: " + thread.getName() + " which is MainThread: " + z11, th2, Descriptions.logs(logCollector));
        }
        return z10 && !z11;
    }

    private PushMessagesTransport getPrimaryPushTransport(Application application) {
        Collection<PushMessagesTransport> pushMessagesTransports = ((PushComponent) Locator.from(application).locate(PushComponent.class)).getPushMessagesTransports();
        for (PushMessagesTransport pushMessagesTransport : pushMessagesTransports) {
            if (pushMessagesTransport.getPushType() != PushType.VKPNS) {
                return pushMessagesTransport;
            }
        }
        throw new IllegalStateException("Could not find primary push transport, total size: " + pushMessagesTransports.size());
    }

    @Override // ru.mail.setup.SetUp
    public void setUp(@NonNull final Application application) {
        PushMessagesTransport primaryPushTransport = getPrimaryPushTransport(application);
        Thread.setDefaultUncaughtExceptionHandler(SessionTracker.from(application).createExceptionHandler(PushExceptionHandler.INSTANCE.createPushExceptionHandler(Log.createUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()), new PushAsserterThrowable(application, primaryPushTransport)), new SessionTracker.FilterThrowable() { // from class: ru.mail.setup.p2
            @Override // ru.mail.utils.analytics.SessionTracker.FilterThrowable
            public final boolean shouldFilter(Thread thread, Throwable th2) {
                return SetUpExceptionHandler.a(application, thread, th2);
            }
        }));
    }
}
