package ru.mail.portal.app.adapter.notifications.tags;

import android.content.Context;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/tags/Tag;", "", "getTitle", "", "context", "Landroid/content/Context;", "getIdForPusher", "", "convertToJSON", "Lorg/json/JSONObject;", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface Tag {
    @NotNull
    JSONObject convertToJSON();

    int getIdForPusher();

    @NotNull
    String getTitle(@NotNull Context context);
}
