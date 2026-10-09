package ru.mail.portal.app.adapter.notifications.tags;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0018\u0010\n\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H&J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004H&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lru/mail/portal/app/adapter/notifications/tags/AppTagsStorage;", "", "getDisabledTagIds", "", "", "appId", "", "saveDisabledTags", "", "tagIds", "setTagEnabled", "tagId", "setTagDisabled", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AppTagsStorage {
    @NotNull
    Set<Integer> getDisabledTagIds(@NotNull String appId);

    void saveDisabledTags(@NotNull String appId, @NotNull Set<Integer> tagIds);

    void setTagDisabled(@NotNull String appId, int tagId);

    void setTagEnabled(@NotNull String appId, int tagId);
}
