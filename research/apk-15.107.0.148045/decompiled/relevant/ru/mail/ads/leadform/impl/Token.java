package ru.mail.ads.leadform.impl;

import com.squareup.moshi.Json;
import com.squareup.moshi.JsonClass;
import com.vk.superapp.browser.ui.VkUiActivityResultDelegate;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@JsonClass(generateAdapter = true)
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\n\u001a\u00020\u0003H\u0016J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lru/mail/ads/leadform/impl/Token;", "", "token", "", "requestId", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "getRequestId", "toString", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "feature-ads-leadform-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Token {

    @Nullable
    private final String requestId;

    @NotNull
    private final String token;

    public Token(@Json(name = "access_token") @NotNull String token, @Json(name = VkUiActivityResultDelegate.KEY_REQUEST_ID) @Nullable String str) {
        Intrinsics.checkNotNullParameter(token, "token");
        this.token = token;
        this.requestId = str;
    }

    public static /* synthetic */ Token copy$default(Token token, String str, String str2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            str = token.token;
        }
        if ((i10 & 2) != 0) {
            str2 = token.requestId;
        }
        return token.copy(str, str2);
    }

    @NotNull
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getToken() {
        return this.token;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getRequestId() {
        return this.requestId;
    }

    @NotNull
    public final Token copy(@Json(name = "access_token") @NotNull String token, @Json(name = VkUiActivityResultDelegate.KEY_REQUEST_ID) @Nullable String requestId) {
        Intrinsics.checkNotNullParameter(token, "token");
        return new Token(token, requestId);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Token)) {
            return false;
        }
        Token token = (Token) other;
        return Intrinsics.areEqual(this.token, token.token) && Intrinsics.areEqual(this.requestId, token.requestId);
    }

    @Nullable
    public final String getRequestId() {
        return this.requestId;
    }

    @NotNull
    public final String getToken() {
        return this.token;
    }

    public int hashCode() {
        int iHashCode = this.token.hashCode() * 31;
        String str = this.requestId;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    @NotNull
    public String toString() {
        return "Token(requestId=" + this.requestId + ", token=***)";
    }

    public /* synthetic */ Token(String str, String str2, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i10 & 2) != 0 ? null : str2);
    }
}
