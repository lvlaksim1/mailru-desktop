package ru.mail.libverify.platform.firebase.b;

import android.content.Context;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.firebase.FirebaseCoreService;
import ru.mail.libverify.platform.firebase.b.b;
import ru.mail.libverify.platform.gcm.IdException;
import ru.mail.libverify.platform.gcm.IdProviderService;
import ru.mail.libverify.platform.utils.StringUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public final class b implements IdProviderService {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    public final ILog f87705a;

    public b(@NotNull ILog log) {
        Intrinsics.checkNotNullParameter(log, "log");
        this.f87705a = log;
    }

    public static final void a(IdProviderService.IdProviderCallback callback, Task task) {
        Intrinsics.checkNotNullParameter(callback, "$callback");
        Intrinsics.checkNotNullParameter(task, "task");
        if (task.isSuccessful()) {
            Object result = task.getResult();
            Intrinsics.checkNotNullExpressionValue(result, "getResult(...)");
            callback.onIdProviderCallback((String) result);
        } else {
            Exception exception = task.getException();
            if (exception == null) {
                exception = new Exception();
            }
            callback.onException(exception);
        }
    }

    @Override // ru.mail.libverify.platform.gcm.IdProviderService
    public final void deleteId(@NotNull Context context) throws IdException {
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            a(context);
            FirebaseMessaging.getInstance().deleteToken();
        } catch (Throwable th2) {
            throw new IdException(th2);
        }
    }

    @Override // ru.mail.libverify.platform.gcm.IdProviderService
    public final void getId(@NotNull Context context, @NotNull String scope, @NotNull final IdProviderService.IdProviderCallback callback) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scope, "scope");
        Intrinsics.checkNotNullParameter(callback, "callback");
        try {
            ((FirebaseMessaging) a(context).get(FirebaseMessaging.class)).getToken().addOnCompleteListener(new OnCompleteListener() { // from class: bd.a
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    b.a(callback, task);
                }
            });
        } catch (Throwable th2) {
            callback.onException(th2);
        }
    }

    public final FirebaseApp a(Context context) {
        try {
            FirebaseApp firebaseApp = FirebaseApp.getInstance("libverify");
            Intrinsics.checkNotNullExpressionValue(firebaseApp, "getInstance(...)");
            return firebaseApp;
        } catch (IllegalStateException e10) {
            this.f87705a.v("id provider", "get firebase app instance " + e10.getMessage());
            FirebaseOptions.Builder builder = new FirebaseOptions.Builder();
            StringUtils stringUtils = StringUtils.INSTANCE;
            FirebaseApp firebaseAppInitializeApp = FirebaseApp.initializeApp(context, builder.setGcmSenderId(stringUtils.decodeBase64(FirebaseCoreService.SENDER_ID)).setApplicationId(stringUtils.decodeBase64("MToyOTcxMDkwMzYzNDk6YW5kcm9pZDpiNzJlNGVkMGZmY2RkYTM5")).setApiKey(stringUtils.decodeBase64("QUl6YVN5QTUwclhhU0xZSWV3MWtidHlHX09MUnBVRlNpN2xWZEE0")).setProjectId(stringUtils.decodeBase64("Z2VuaWFsLXVuaW9uLTkxODA5")).build(), "libverify");
            Intrinsics.checkNotNullExpressionValue(firebaseAppInitializeApp, "initializeApp(...)");
            return firebaseAppInitializeApp;
        }
    }
}
