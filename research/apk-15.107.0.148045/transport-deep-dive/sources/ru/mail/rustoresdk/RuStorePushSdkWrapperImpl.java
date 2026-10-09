package ru.mail.rustoresdk;

import com.google.android.gms.ads.RequestConfiguration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import ru.mail.rustoresdk.RuStorePushSdkWrapperImpl;
import ru.rustore.sdk.core.tasks.OnFailureListener;
import ru.rustore.sdk.core.tasks.OnSuccessListener;
import ru.rustore.sdk.core.tasks.Task;
import ru.rustore.sdk.pushclient.RuStorePushClient;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J9\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0000\u0010\n*\u00020\u000b*\b\u0012\u0004\u0012\u0002H\n0\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lru/mail/rustoresdk/RuStorePushSdkWrapperImpl;", "Lru/mail/rustoresdk/RuStorePushSdkWrapper;", "<init>", "()V", "getToken", "", "deleteToken", "", "awaitTask", "Lkotlin/Result;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "", "Lru/rustore/sdk/core/tasks/Task;", "timeout", "", "unit", "Ljava/util/concurrent/TimeUnit;", "awaitTask-0E7RQCE", "(Lru/rustore/sdk/core/tasks/Task;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "Companion", "rustore-sdk-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class RuStorePushSdkWrapperImpl implements RuStorePushSdkWrapper {
    private static final long TASK_TIMEOUT_SECONDS = 30;

    /* JADX INFO: renamed from: awaitTask-0E7RQCE, reason: not valid java name */
    private final <T> Object m15800awaitTask0E7RQCE(Task<T> task, long j10, TimeUnit timeUnit) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference();
        final AtomicReference atomicReference2 = new AtomicReference();
        task.addOnSuccessListener(new OnSuccessListener() { // from class: bf.b
            @Override // ru.rustore.sdk.core.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                RuStorePushSdkWrapperImpl.awaitTask_0E7RQCE$lambda$0(atomicReference, countDownLatch, obj);
            }
        });
        task.addOnFailureListener(new OnFailureListener() { // from class: bf.c
            @Override // ru.rustore.sdk.core.tasks.OnFailureListener
            public final void onFailure(Throwable th2) {
                RuStorePushSdkWrapperImpl.awaitTask_0E7RQCE$lambda$1(atomicReference2, countDownLatch, th2);
            }
        });
        countDownLatch.await(j10, timeUnit);
        Object obj = atomicReference.get();
        if (obj != null) {
            return Result.m13123constructorimpl(obj);
        }
        Throwable runtimeException = (Throwable) atomicReference2.get();
        if (runtimeException == null) {
            runtimeException = new RuntimeException("Timeout exceeded while waiting task: " + j10 + StringUtils.SPACE + timeUnit);
        }
        Result.Companion companion = Result.INSTANCE;
        return Result.m13123constructorimpl(ResultKt.createFailure(runtimeException));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void awaitTask_0E7RQCE$lambda$0(AtomicReference atomicReference, CountDownLatch countDownLatch, Object result) {
        Intrinsics.checkNotNullParameter(result, "result");
        atomicReference.set(result);
        countDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void awaitTask_0E7RQCE$lambda$1(AtomicReference atomicReference, CountDownLatch countDownLatch, Throwable e10) {
        Intrinsics.checkNotNullParameter(e10, "e");
        atomicReference.set(e10);
        countDownLatch.countDown();
    }

    @Override // ru.mail.rustoresdk.RuStorePushSdkWrapper
    public boolean deleteToken() {
        return Result.m13129isSuccessimpl(m15800awaitTask0E7RQCE(RuStorePushClient.f102575a.deleteToken(), 30L, TimeUnit.SECONDS));
    }

    @Override // ru.mail.rustoresdk.RuStorePushSdkWrapper
    @Nullable
    public String getToken() throws InterruptedException {
        Object objM15800awaitTask0E7RQCE = m15800awaitTask0E7RQCE(RuStorePushClient.f102575a.getToken(), 30L, TimeUnit.SECONDS);
        if (Result.m13128isFailureimpl(objM15800awaitTask0E7RQCE)) {
            objM15800awaitTask0E7RQCE = null;
        }
        return (String) objM15800awaitTask0E7RQCE;
    }
}
