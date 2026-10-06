package ru.mail.data.cmd.server.summarize;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000bJ$\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeDto;", "", ErrorBundle.SUMMARY_ENTRY, "", "finished", "", "<init>", "(Ljava/lang/String;Ljava/lang/Boolean;)V", "getSummary", "()Ljava/lang/String;", "getFinished", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Boolean;)Lru/mail/data/cmd/server/summarize/SummarizeDto;", "equals", "other", "hashCode", "", "toString", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SummarizeDto {

    @Nullable
    private final Boolean finished;

    @NotNull
    private final String summary;

    public SummarizeDto(@NotNull String summary, @Nullable Boolean bool) {
        Intrinsics.checkNotNullParameter(summary, "summary");
        this.summary = summary;
        this.finished = bool;
    }

    public static /* synthetic */ SummarizeDto copy$default(SummarizeDto summarizeDto, String str, Boolean bool, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = summarizeDto.summary;
        }
        if ((i10 & 2) != 0) {
            bool = summarizeDto.finished;
        }
        return summarizeDto.copy(str, bool);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getSummary() {
        return this.summary;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Boolean getFinished() {
        return this.finished;
    }

    @NotNull
    public final SummarizeDto copy(@NotNull String summary, @Nullable Boolean finished) {
        Intrinsics.checkNotNullParameter(summary, "summary");
        return new SummarizeDto(summary, finished);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SummarizeDto)) {
            return false;
        }
        SummarizeDto summarizeDto = (SummarizeDto) other;
        return Intrinsics.areEqual(this.summary, summarizeDto.summary) && Intrinsics.areEqual(this.finished, summarizeDto.finished);
    }

    @Nullable
    public final Boolean getFinished() {
        return this.finished;
    }

    @NotNull
    public final String getSummary() {
        return this.summary;
    }

    public int hashCode() {
        int iHashCode = this.summary.hashCode() * 31;
        Boolean bool = this.finished;
        return iHashCode + (bool == null ? 0 : bool.hashCode());
    }

    @NotNull
    public String toString() {
        return "SummarizeDto(summary=" + this.summary + ", finished=" + this.finished + ")";
    }

    public /* synthetic */ SummarizeDto(String str, Boolean bool, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? Boolean.TRUE : bool);
    }
}
