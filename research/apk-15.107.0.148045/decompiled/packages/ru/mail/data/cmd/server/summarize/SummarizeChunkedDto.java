package ru.mail.data.cmd.server.summarize;

import com.squareup.moshi.JsonClass;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeChunkedDto;", "", "status", "", "body", "Lru/mail/data/cmd/server/summarize/SummarizeChunkDto;", "<init>", "(ILru/mail/data/cmd/server/summarize/SummarizeChunkDto;)V", "getStatus", "()I", "getBody", "()Lru/mail/data/cmd/server/summarize/SummarizeChunkDto;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SummarizeChunkedDto {

    @NotNull
    private final SummarizeChunkDto body;
    private final int status;

    public SummarizeChunkedDto(int i10, @NotNull SummarizeChunkDto body) {
        Intrinsics.checkNotNullParameter(body, "body");
        this.status = i10;
        this.body = body;
    }

    public static /* synthetic */ SummarizeChunkedDto copy$default(SummarizeChunkedDto summarizeChunkedDto, int i10, SummarizeChunkDto summarizeChunkDto, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = summarizeChunkedDto.status;
        }
        if ((i11 & 2) != 0) {
            summarizeChunkDto = summarizeChunkedDto.body;
        }
        return summarizeChunkedDto.copy(i10, summarizeChunkDto);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getStatus() {
        return this.status;
    }

    @NotNull
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final SummarizeChunkDto getBody() {
        return this.body;
    }

    @NotNull
    public final SummarizeChunkedDto copy(int status, @NotNull SummarizeChunkDto body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return new SummarizeChunkedDto(status, body);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SummarizeChunkedDto)) {
            return false;
        }
        SummarizeChunkedDto summarizeChunkedDto = (SummarizeChunkedDto) other;
        return this.status == summarizeChunkedDto.status && Intrinsics.areEqual(this.body, summarizeChunkedDto.body);
    }

    @NotNull
    public final SummarizeChunkDto getBody() {
        return this.body;
    }

    public final int getStatus() {
        return this.status;
    }

    public int hashCode() {
        return (Integer.hashCode(this.status) * 31) + this.body.hashCode();
    }

    @NotNull
    public String toString() {
        return "SummarizeChunkedDto(status=" + this.status + ", body=" + this.body + ")";
    }
}
