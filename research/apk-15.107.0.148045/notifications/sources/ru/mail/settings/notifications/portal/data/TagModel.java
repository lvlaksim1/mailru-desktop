package ru.mail.settings.notifications.portal.data;

import androidx.compose.runtime.internal.StabilityInferred;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.portal.app.adapter.notifications.tags.Tag;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/settings/notifications/portal/data/TagModel;", "", "tag", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "isChecked", "", "isEnabled", "<init>", "(Lru/mail/portal/app/adapter/notifications/tags/Tag;ZZ)V", "getTag", "()Lru/mail/portal/app/adapter/notifications/tags/Tag;", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TagModel {
    public static final int $stable = 8;
    private final boolean isChecked;
    private final boolean isEnabled;

    @NotNull
    private final Tag tag;

    public TagModel(@NotNull Tag tag, boolean z10, boolean z11) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        this.tag = tag;
        this.isChecked = z10;
        this.isEnabled = z11;
    }

    public static /* synthetic */ TagModel copy$default(TagModel tagModel, Tag tag, boolean z10, boolean z11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            tag = tagModel.tag;
        }
        if ((i10 & 2) != 0) {
            z10 = tagModel.isChecked;
        }
        if ((i10 & 4) != 0) {
            z11 = tagModel.isEnabled;
        }
        return tagModel.copy(tag, z10, z11);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final Tag getTag() {
        return this.tag;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final boolean getIsChecked() {
        return this.isChecked;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getIsEnabled() {
        return this.isEnabled;
    }

    @NotNull
    public final TagModel copy(@NotNull Tag tag, boolean isChecked, boolean isEnabled) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return new TagModel(tag, isChecked, isEnabled);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TagModel)) {
            return false;
        }
        TagModel tagModel = (TagModel) other;
        return Intrinsics.areEqual(this.tag, tagModel.tag) && this.isChecked == tagModel.isChecked && this.isEnabled == tagModel.isEnabled;
    }

    @NotNull
    public final Tag getTag() {
        return this.tag;
    }

    public int hashCode() {
        return (((this.tag.hashCode() * 31) + Boolean.hashCode(this.isChecked)) * 31) + Boolean.hashCode(this.isEnabled);
    }

    public final boolean isChecked() {
        return this.isChecked;
    }

    public final boolean isEnabled() {
        return this.isEnabled;
    }

    @NotNull
    public String toString() {
        return "TagModel(tag=" + this.tag + ", isChecked=" + this.isChecked + ", isEnabled=" + this.isEnabled + ")";
    }
}
