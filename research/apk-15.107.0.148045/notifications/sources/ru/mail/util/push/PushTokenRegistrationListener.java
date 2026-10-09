package ru.mail.util.push;

import android.content.Context;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class PushTokenRegistrationListener implements PushMessagesTransport.RegistrationListener {
    private final Context mContext;
    private final boolean mInitSdk;
    private final PushMessagesTransport mTransport;

    public PushTokenRegistrationListener(Context context, boolean z10, PushMessagesTransport pushMessagesTransport) {
        this.mContext = context;
        this.mInitSdk = z10;
        this.mTransport = pushMessagesTransport;
        if (pushMessagesTransport.isRegistered()) {
            onRegistered();
        }
    }

    private boolean isCheckingPushTokenEnabled() {
        return !this.mInitSdk;
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onCannotRegister(Exception exc) {
        if (exc instanceof PushMessagesTransport.BadTokenException) {
            this.mTransport.register();
        }
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onRegistered() {
        if (isCheckingPushTokenEnabled()) {
            PushTokenAlarmReceiver.startChecking(this.mContext);
        } else {
            PushTokenAlarmReceiver.cancelChecking(this.mContext);
        }
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onUnregistered() {
        PushTokenAlarmReceiver.cancelChecking(this.mContext);
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onCannotUnregister(Exception exc) {
    }
}
