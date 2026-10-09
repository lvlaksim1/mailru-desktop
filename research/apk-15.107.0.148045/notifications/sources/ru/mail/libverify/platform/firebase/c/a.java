package ru.mail.libverify.platform.firebase.c;

import android.content.Context;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.platform.core.ILog;
import ru.mail.libverify.platform.firebase.FirebaseCoreService;
import ru.mail.libverify.platform.sms.SmsRetrieverPlatformManager;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public final class a implements SmsRetrieverPlatformManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public Task<Void> f87706a;

    public static final void a(Runnable success, Task it) {
        Intrinsics.checkNotNullParameter(success, "$success");
        Intrinsics.checkNotNullParameter(it, "it");
        success.run();
    }

    @Override // ru.mail.libverify.platform.sms.SmsRetrieverPlatformManager
    public final void checkSmsRetrieverTask(@NotNull Context context, @NotNull final Runnable success, @NotNull final Function1<? super Exception, Unit> failure) {
        Task<Void> taskAddOnCompleteListener;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(failure, "failure");
        FirebaseCoreService.INSTANCE.getClass();
        ILog iLogA = FirebaseCoreService.Companion.a();
        if (this.f87706a != null) {
            iLogA.d("FirebaseSmsRetrieverPlatformManager", "SmsRetrieverClient has been already subscribed");
            return;
        }
        try {
            SmsRetrieverClient client = SmsRetriever.getClient(context);
            Intrinsics.checkNotNullExpressionValue(client, "getClient(...)");
            iLogA.d("FirebaseSmsRetrieverPlatformManager", "SmsRetrieverClient started");
            Task<Void> taskStartSmsRetriever = client.startSmsRetriever();
            this.f87706a = taskStartSmsRetriever;
            if (taskStartSmsRetriever == null || (taskAddOnCompleteListener = taskStartSmsRetriever.addOnCompleteListener(new OnCompleteListener() { // from class: cd.a
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    ru.mail.libverify.platform.firebase.c.a.a(success, task);
                }
            })) == null) {
                return;
            }
            taskAddOnCompleteListener.addOnFailureListener(new OnFailureListener() { // from class: cd.b
                @Override // com.google.android.gms.tasks.OnFailureListener
                public final void onFailure(Exception exc) {
                    ru.mail.libverify.platform.firebase.c.a.a(this.f14621a, failure, exc);
                }
            });
        } catch (Throwable th2) {
            iLogA.e("FirebaseSmsRetrieverPlatformManager", "SmsRetrieverClient init error", th2);
        }
    }

    @Override // ru.mail.libverify.platform.sms.SmsRetrieverPlatformManager
    public final void onSmsRetrieverSmsReceived(int i10, @NotNull String smsText, @NotNull Runnable success, @NotNull Runnable timeout) {
        Intrinsics.checkNotNullParameter(smsText, "smsText");
        Intrinsics.checkNotNullParameter(success, "success");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        FirebaseCoreService.INSTANCE.getClass();
        FirebaseCoreService.Companion.a().v("FirebaseSmsRetrieverPlatformManager", "received status: " + CommonStatusCodes.getStatusCodeString(i10) + " with sms text: " + smsText);
        this.f87706a = null;
        if (i10 == 0) {
            success.run();
        } else {
            if (i10 != 15) {
                return;
            }
            timeout.run();
        }
    }

    public static final void a(a this$0, Function1 failure, Exception it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(failure, "$failure");
        Intrinsics.checkNotNullParameter(it, "it");
        this$0.f87706a = null;
        failure.invoke(it);
    }
}
