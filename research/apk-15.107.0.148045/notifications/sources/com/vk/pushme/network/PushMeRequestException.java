package com.vk.pushme.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/vk/pushme/network/PushMeRequestException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "httpStatusCode", "", "<init>", "(Ljava/lang/String;I)V", "getMessage", "()Ljava/lang/String;", "getHttpStatusCode", "()I", "push-me-network_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PushMeRequestException extends Exception {
    private final int httpStatusCode;

    @NotNull
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PushMeRequestException(@NotNull String message, int i10) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        this.message = message;
        this.httpStatusCode = i10;
    }

    public final int getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override // java.lang.Throwable
    @NotNull
    public String getMessage() {
        return this.message;
    }
}
