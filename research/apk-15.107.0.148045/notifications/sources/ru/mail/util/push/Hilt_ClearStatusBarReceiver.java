package ru.mail.util.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.CallSuper;
import dagger.hilt.android.internal.OnReceiveBytecodeInjectionMarker;
import dagger.hilt.android.internal.managers.BroadcastReceiverComponentManager;
import dagger.hilt.internal.UnsafeCasts;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@OnReceiveBytecodeInjectionMarker
public abstract class Hilt_ClearStatusBarReceiver extends BroadcastReceiver {
    private volatile boolean injected = false;
    private final Object injectedLock = new Object();

    protected void inject(Context context) {
        if (this.injected) {
            return;
        }
        synchronized (this.injectedLock) {
            try {
                if (!this.injected) {
                    ((ClearStatusBarReceiver_GeneratedInjector) BroadcastReceiverComponentManager.generatedComponent(context)).injectClearStatusBarReceiver((ClearStatusBarReceiver) UnsafeCasts.unsafeCast(this));
                    this.injected = true;
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    @CallSuper
    public void onReceive(Context context, Intent intent) {
        inject(context);
    }
}
