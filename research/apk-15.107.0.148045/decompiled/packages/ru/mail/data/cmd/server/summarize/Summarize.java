package ru.mail.data.cmd.server.summarize;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/summarize/Summarize;", "", "text", "", "rate", "Lru/mail/data/cmd/server/summarize/SummarizeRate;", "<init>", "(Ljava/lang/String;Lru/mail/data/cmd/server/summarize/SummarizeRate;)V", "getText", "()Ljava/lang/String;", "getRate", "()Lru/mail/data/cmd/server/summarize/SummarizeRate;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Summarize {

    @Nullable
    private final SummarizeRate rate;

    @NotNull
    private final String text;

    public Summarize(@NotNull String text, @Nullable SummarizeRate summarizeRate) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.rate = summarizeRate;
    }

    public static /* synthetic */ Summarize copy$default(Summarize summarize, String str, SummarizeRate summarizeRate, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = summarize.text;
        }
        if ((i10 & 2) != 0) {
            summarizeRate = summarize.rate;
        }
        return summarize.copy(str, summarizeRate);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getText() {
        return this.text;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SummarizeRate getRate() {
        return this.rate;
    }

    @NotNull
    public final Summarize copy(@NotNull String text, @Nullable SummarizeRate rate) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new Summarize(text, rate);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Summarize)) {
            return false;
        }
        Summarize summarize = (Summarize) other;
        return Intrinsics.areEqual(this.text, summarize.text) && this.rate == summarize.rate;
    }

    @Nullable
    public final SummarizeRate getRate() {
        return this.rate;
    }

    @NotNull
    public final String getText() {
        return this.text;
    }

    public int hashCode() {
        int iHashCode = this.text.hashCode() * 31;
        SummarizeRate summarizeRate = this.rate;
        return iHashCode + (summarizeRate == null ? 0 : summarizeRate.hashCode());
    }

    @NotNull
    public String toString() {
        return "Summarize(text=" + this.text + ", rate=" + this.rate + ")";
    }

    public /* synthetic */ Summarize(String str, SummarizeRate summarizeRate, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? null : summarizeRate);
    }
}
