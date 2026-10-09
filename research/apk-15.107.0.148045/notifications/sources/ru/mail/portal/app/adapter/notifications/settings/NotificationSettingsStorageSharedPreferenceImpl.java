package ru.mail.portal.app.adapter.notifications.settings;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\tH\u0016J\u0010\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lru/mail/portal/app/adapter/notifications/settings/NotificationSettingsStorageSharedPreferenceImpl;", "Lru/mail/portal/app/adapter/notifications/settings/NotificationSettingsStorage;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "sharedPreference", "Landroid/content/SharedPreferences;", "areNotificationsEnabledInApplicationSettings", "", "key", "", "defaultValue", "setNotificationsEnabledInApplicationSettings", "", "enabled", "getSharedPreferencesName", "getSharedPreferencesMode", "", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NotificationSettingsStorageSharedPreferenceImpl implements NotificationSettingsStorage {

    @NotNull
    private final SharedPreferences sharedPreference;

    public NotificationSettingsStorageSharedPreferenceImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences sharedPreferences = context.getSharedPreferences(getSharedPreferencesName(context), getSharedPreferencesMode());
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.sharedPreference = sharedPreferences;
    }

    private final int getSharedPreferencesMode() {
        return 0;
    }

    private final String getSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.NotificationSettingsStorage
    public boolean areNotificationsEnabledInApplicationSettings(@NotNull String key, boolean defaultValue) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.sharedPreference.getBoolean(key, defaultValue);
    }

    @Override // ru.mail.portal.app.adapter.notifications.settings.NotificationSettingsStorage
    public void setNotificationsEnabledInApplicationSettings(@NotNull String key, boolean enabled) {
        Intrinsics.checkNotNullParameter(key, "key");
        this.sharedPreference.edit().putBoolean(key, enabled).apply();
    }
}
