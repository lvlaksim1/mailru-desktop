package ru.mail.util.push;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0003H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lru/mail/util/push/AppAdapterPusherApplicationConverter;", "", "convertFromAppId", "Lru/mail/util/push/PusherApplicationType;", "appId", "", "convertToAppId", "type", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AppAdapterPusherApplicationConverter {
    @Nullable
    PusherApplicationType convertFromAppId(@NotNull String appId);

    @Nullable
    String convertToAppId(@NotNull PusherApplicationType type);
}
