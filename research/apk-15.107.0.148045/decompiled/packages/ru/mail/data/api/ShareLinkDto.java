package ru.mail.data.api;

import androidx.compose.runtime.internal.StabilityInferred;
import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lru/mail/data/api/ShareLinkDto;", "", "link", "", "<init>", "(Ljava/lang/String;)V", "getLink", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ShareLinkDto {
    public static final int $stable = 0;

    @NotNull
    private final String link;

    public ShareLinkDto(@NotNull String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        this.link = link;
    }

    public static /* synthetic */ ShareLinkDto copy$default(ShareLinkDto shareLinkDto, String str, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = shareLinkDto.link;
        }
        return shareLinkDto.copy(str);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getLink() {
        return this.link;
    }

    @NotNull
    public final ShareLinkDto copy(@NotNull String link) {
        Intrinsics.checkNotNullParameter(link, "link");
        return new ShareLinkDto(link);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ShareLinkDto) && Intrinsics.areEqual(this.link, ((ShareLinkDto) other).link);
    }

    @NotNull
    public final String getLink() {
        return this.link;
    }

    public int hashCode() {
        return this.link.hashCode();
    }

    @NotNull
    public String toString() {
        return "ShareLinkDto(link=" + this.link + ")";
    }
}
