package ru.mail.util.push;

import android.content.Context;
import org.jetbrains.annotations.NotNull;
import ru.mail.util.push.updater.DefaultPushUpdater;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class GcmAvailableStatusListener implements PushMessagesTransport.RegistrationListener {
    private static final String KEY_GCM_ERROR = "gcm_error";
    private final Context mContext;
    private final DefaultPushUpdater.PushHistory mPushHistory;

    public GcmAvailableStatusListener(@NotNull Context context) {
        this.mContext = context;
        this.mPushHistory = new SingleFlagHistory(context, KEY_GCM_ERROR);
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onCannotRegister(Exception exc) {
        if (exc instanceof SecurityException) {
            if (!this.mPushHistory.hasRecord() || this.mPushHistory.wasAvailableLastTime()) {
                NotificationHandler.from(this.mContext).showErrorNotification(this.mContext.getString(ru.mail.mailapp.R.string.error), this.mContext.getString(ru.mail.mailapp.R.string.gcm_registration_error));
                this.mPushHistory.storeCurrentState(false);
            }
        }
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onCannotUnregister(Exception exc) {
        if (exc instanceof SecurityException) {
            if (!this.mPushHistory.hasRecord() || this.mPushHistory.wasAvailableLastTime()) {
                NotificationHandler.from(this.mContext).showErrorNotification(this.mContext.getString(ru.mail.mailapp.R.string.error), this.mContext.getString(ru.mail.mailapp.R.string.gcm_unregistration_error));
                this.mPushHistory.storeCurrentState(false);
            }
        }
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onRegistered() {
        this.mPushHistory.storeCurrentState(true);
    }

    @Override // ru.mail.util.push.PushMessagesTransport.RegistrationListener
    public void onUnregistered() {
        this.mPushHistory.storeCurrentState(true);
    }
}
