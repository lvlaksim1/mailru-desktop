package ru.mail.util.push.gcm;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.setup.action.FirebaseAppStartup;
import ru.mail.util.log.Log;
import ru.mail.util.push.AvailabilityCheckResult;
import ru.mail.util.push.PushKitWrapper;
import ru.mail.utils.FirebaseAppInitializer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002J5\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00120\u0011\"\u0004\b\u0000\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00120\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lru/mail/util/push/gcm/GcmDevPushKitWrapper;", "Lru/mail/util/push/PushKitWrapper;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "availabilityChecker", "Lru/mail/util/push/gcm/GCMAvailabilityChecker;", "getPushTokenFromPushKit", "", "deleteToken", "", "checkForAvailability", "Lru/mail/util/push/AvailabilityCheckResult;", "getMessaging", "Lcom/google/firebase/messaging/FirebaseMessaging;", "await", "Lkotlin/Result;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lcom/google/android/gms/tasks/Task;", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "await-0E7RQCE", "(Lcom/google/android/gms/tasks/Task;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGcmDevPushKitWrapper.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GcmDevPushKitWrapper.kt\nru/mail/util/push/gcm/GcmDevPushKitWrapper\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,66:1\n1#2:67\n*E\n"})
public final class GcmDevPushKitWrapper implements PushKitWrapper {
    private static final long DEFAULT_TIMEOUT_IN_SECONDS = 30;

    @NotNull
    private final GCMAvailabilityChecker availabilityChecker;

    @NotNull
    private final Context context;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("GcmDevPushKitWrapper");

    public GcmDevPushKitWrapper(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.availabilityChecker = new GCMAvailabilityChecker(context);
    }

    /* JADX INFO: renamed from: await-0E7RQCE, reason: not valid java name */
    private final <T> Object m15886await0E7RQCE(Task<T> task, long j10, TimeUnit timeUnit) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Function1 function1 = new Function1() { // from class: ru.mail.util.push.gcm.a
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return GcmDevPushKitWrapper.await_0E7RQCE$lambda$0(objectRef, obj);
            }
        };
        task.addOnSuccessListener(new OnSuccessListener() { // from class: ru.mail.util.push.gcm.b
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                function1.invoke(obj);
            }
        });
        task.addOnFailureListener(new OnFailureListener() { // from class: ru.mail.util.push.gcm.c
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                GcmDevPushKitWrapper.await_0E7RQCE$lambda$2(objectRef2, exc);
            }
        });
        task.addOnCompleteListener(new OnCompleteListener() { // from class: ru.mail.util.push.gcm.d
            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task2) {
                GcmDevPushKitWrapper.await_0E7RQCE$lambda$3(countDownLatch, task2);
            }
        });
        countDownLatch.await(j10, timeUnit);
        T t10 = objectRef.element;
        if (t10 != null) {
            return Result.m13123constructorimpl(t10);
        }
        Throwable runtimeException = (Throwable) objectRef2.element;
        if (runtimeException == null) {
            runtimeException = new RuntimeException("Timeout exceeded while waiting task: " + j10 + StringUtils.SPACE + timeUnit);
        }
        Result.Companion companion = Result.INSTANCE;
        return Result.m13123constructorimpl(ResultKt.createFailure(runtimeException));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit await_0E7RQCE$lambda$0(Ref.ObjectRef objectRef, Object obj) {
        objectRef.element = obj;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void await_0E7RQCE$lambda$2(Ref.ObjectRef objectRef, Exception e10) {
        Intrinsics.checkNotNullParameter(e10, "e");
        objectRef.element = e10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void await_0E7RQCE$lambda$3(CountDownLatch countDownLatch, Task it) {
        Intrinsics.checkNotNullParameter(it, "it");
        countDownLatch.countDown();
    }

    private final FirebaseMessaging getMessaging() {
        FirebaseAppInitializer.INSTANCE.awaitInitialized(this.context);
        Object obj = FirebaseApp.getInstance(FirebaseAppStartup.DEV_PUSH_FIREBASE_APP_NAME).get(FirebaseMessaging.class);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        return (FirebaseMessaging) obj;
    }

    @Override // ru.mail.util.push.AvailabilityChecker
    @NotNull
    public AvailabilityCheckResult checkForAvailability() {
        return this.availabilityChecker.checkForAvailability();
    }

    @Override // ru.mail.util.push.PushKitWrapper
    public void deleteToken() throws InterruptedException {
        Log log = LOG;
        log.d("Deleting push token from push kit...");
        Task<Void> taskDeleteToken = getMessaging().deleteToken();
        Intrinsics.checkNotNullExpressionValue(taskDeleteToken, "deleteToken(...)");
        m15886await0E7RQCE(taskDeleteToken, 30L, TimeUnit.SECONDS);
        log.d("Deleting push token from push kit has finished");
    }

    @Override // ru.mail.util.push.PushKitWrapper
    @Nullable
    public String getPushTokenFromPushKit() throws InterruptedException {
        Log log = LOG;
        log.d("Getting push token from push kit...");
        Task<String> token = getMessaging().getToken();
        Intrinsics.checkNotNullExpressionValue(token, "getToken(...)");
        Object objM15886await0E7RQCE = m15886await0E7RQCE(token, 30L, TimeUnit.SECONDS);
        if (Result.m13128isFailureimpl(objM15886await0E7RQCE)) {
            log.e("Getting push token from push kit failed", Result.m13126exceptionOrNullimpl(objM15886await0E7RQCE));
            return null;
        }
        if (Result.m13128isFailureimpl(objM15886await0E7RQCE)) {
            objM15886await0E7RQCE = null;
        }
        String str = (String) objM15886await0E7RQCE;
        if (str == null) {
            return null;
        }
        log.d("Got push token from dev GCM, is it null or blank: " + StringsKt.isBlank(str));
        return str;
    }
}
