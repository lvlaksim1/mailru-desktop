package ru.mail.portal.app.adapter.notifications.tags;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorageFactory;", "", "<init>", "()V", "createStorage", "Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorage;", "context", "Landroid/content/Context;", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AppTagsStorageFactory {

    @NotNull
    public static final AppTagsStorageFactory INSTANCE = new AppTagsStorageFactory();

    private AppTagsStorageFactory() {
    }

    @NotNull
    public final AppTagsStorage createStorage(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new SharedPrefsAppTagsStorage(context);
    }
}
