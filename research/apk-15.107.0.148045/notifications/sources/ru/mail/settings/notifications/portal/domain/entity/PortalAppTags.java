package ru.mail.settings.notifications.portal.domain.entity;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.settings.notifications.portal.data.TagModel;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lru/mail/settings/notifications/portal/domain/entity/PortalAppTags;", "", "mainSwitchChecked", "", "tagsList", "", "Lru/mail/settings/notifications/portal/data/TagModel;", "<init>", "(ZLjava/util/List;)V", "getMainSwitchChecked", "()Z", "getTagsList", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class PortalAppTags {
    public static final int $stable = 8;
    private final boolean mainSwitchChecked;

    @NotNull
    private final List<TagModel> tagsList;

    public PortalAppTags(boolean z10, @NotNull List<TagModel> tagsList) {
        Intrinsics.checkNotNullParameter(tagsList, "tagsList");
        this.mainSwitchChecked = z10;
        this.tagsList = tagsList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PortalAppTags copy$default(PortalAppTags portalAppTags, boolean z10, List list, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z10 = portalAppTags.mainSwitchChecked;
        }
        if ((i10 & 2) != 0) {
            list = portalAppTags.tagsList;
        }
        return portalAppTags.copy(z10, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getMainSwitchChecked() {
        return this.mainSwitchChecked;
    }

    @NotNull
    public final List<TagModel> component2() {
        return this.tagsList;
    }

    @NotNull
    public final PortalAppTags copy(boolean mainSwitchChecked, @NotNull List<TagModel> tagsList) {
        Intrinsics.checkNotNullParameter(tagsList, "tagsList");
        return new PortalAppTags(mainSwitchChecked, tagsList);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PortalAppTags)) {
            return false;
        }
        PortalAppTags portalAppTags = (PortalAppTags) other;
        return this.mainSwitchChecked == portalAppTags.mainSwitchChecked && Intrinsics.areEqual(this.tagsList, portalAppTags.tagsList);
    }

    public final boolean getMainSwitchChecked() {
        return this.mainSwitchChecked;
    }

    @NotNull
    public final List<TagModel> getTagsList() {
        return this.tagsList;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.mainSwitchChecked) * 31) + this.tagsList.hashCode();
    }

    @NotNull
    public String toString() {
        return "PortalAppTags(mainSwitchChecked=" + this.mainSwitchChecked + ", tagsList=" + this.tagsList + ")";
    }
}
